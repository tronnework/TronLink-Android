package io.grpc.internal;

import com.google.common.base.Preconditions;
import java.util.IdentityHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
public final class SharedResourceHolder {
    static final long DESTROY_DELAY_SECONDS = 1;
    private static final SharedResourceHolder holder = new SharedResourceHolder(new ScheduledExecutorFactory() {
        @Override
        public ScheduledExecutorService createScheduledExecutor() {
            return Executors.newSingleThreadScheduledExecutor(GrpcUtil.getThreadFactory("grpc-shared-destroyer-%d", true));
        }
    });
    private ScheduledExecutorService destroyer;
    private final ScheduledExecutorFactory destroyerFactory;
    private final IdentityHashMap<Resource<?>, Instance> instances = new IdentityHashMap<>();

    public interface Resource<T> {
        void close(T t);

        T create();
    }

    public interface ScheduledExecutorFactory {
        ScheduledExecutorService createScheduledExecutor();
    }

    SharedResourceHolder(ScheduledExecutorFactory scheduledExecutorFactory) {
        this.destroyerFactory = scheduledExecutorFactory;
    }

    public static <T> T get(Resource<T> resource) {
        return (T) holder.getInternal(resource);
    }

    public static <T> T release(Resource<T> resource, T t) {
        return (T) holder.releaseInternal(resource, t);
    }

    synchronized <T> T getInternal(Resource<T> resource) {
        Instance instance;
        instance = this.instances.get(resource);
        if (instance == null) {
            instance = new Instance(resource.create());
            this.instances.put(resource, instance);
        }
        if (instance.destroyTask != null) {
            instance.destroyTask.cancel(false);
            instance.destroyTask = null;
        }
        instance.refcount++;
        return (T) instance.payload;
    }

    synchronized <T> T releaseInternal(final Resource<T> resource, final T t) {
        final Instance instance = this.instances.get(resource);
        if (instance == null) {
            throw new IllegalArgumentException("No cached instance found for " + resource);
        }
        Preconditions.checkArgument(t == instance.payload, "Releasing the wrong instance");
        Preconditions.checkState(instance.refcount > 0, "Refcount has already reached zero");
        instance.refcount--;
        if (instance.refcount == 0) {
            Preconditions.checkState(instance.destroyTask == null, "Destroy task already scheduled");
            if (this.destroyer == null) {
                this.destroyer = this.destroyerFactory.createScheduledExecutor();
            }
            instance.destroyTask = this.destroyer.schedule(new LogExceptionRunnable(new Runnable() {
                @Override
                public void run() {
                    synchronized (SharedResourceHolder.this) {
                        if (instance.refcount == 0) {
                            resource.close(t);
                            instances.remove(resource);
                            if (instances.isEmpty()) {
                                destroyer.shutdown();
                                destroyer = null;
                            }
                        }
                    }
                }
            }), DESTROY_DELAY_SECONDS, TimeUnit.SECONDS);
        }
        return null;
    }

    public static class Instance {
        ScheduledFuture<?> destroyTask;
        final Object payload;
        int refcount;

        Instance(Object obj) {
            this.payload = obj;
        }
    }
}
