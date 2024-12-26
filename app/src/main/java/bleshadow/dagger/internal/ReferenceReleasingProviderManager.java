package bleshadow.dagger.internal;

import bleshadow.dagger.releasablereferences.ReleasableReferenceManager;
import java.lang.annotation.Annotation;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
public final class ReferenceReleasingProviderManager implements ReleasableReferenceManager {
    private final Queue<WeakReference<ReferenceReleasingProvider<?>>> providers = new ConcurrentLinkedQueue();
    private final Class<? extends Annotation> scope;

    public enum Operation {
        RELEASE {
            @Override
            void execute(ReferenceReleasingProvider<?> provider) {
                provider.releaseStrongReference();
            }
        },
        RESTORE {
            @Override
            void execute(ReferenceReleasingProvider<?> provider) {
                provider.restoreStrongReference();
            }
        };

        abstract void execute(ReferenceReleasingProvider<?> provider);
    }

    @Override
    public Class<? extends Annotation> scope() {
        return this.scope;
    }

    public ReferenceReleasingProviderManager(Class<? extends Annotation> scope) {
        this.scope = (Class) Preconditions.checkNotNull(scope);
    }

    public void addProvider(ReferenceReleasingProvider<?> provider) {
        this.providers.add(new WeakReference<>(provider));
    }

    @Override
    public void releaseStrongReferences() {
        execute(Operation.RELEASE);
    }

    @Override
    public void restoreStrongReferences() {
        execute(Operation.RESTORE);
    }

    private void execute(Operation operation) {
        Iterator<WeakReference<ReferenceReleasingProvider<?>>> it = this.providers.iterator();
        while (it.hasNext()) {
            ReferenceReleasingProvider<?> referenceReleasingProvider = it.next().get();
            if (referenceReleasingProvider == null) {
                it.remove();
            } else {
                operation.execute(referenceReleasingProvider);
            }
        }
    }
}
