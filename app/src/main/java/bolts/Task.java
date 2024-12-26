package bolts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
public class Task<TResult> {
    private static volatile UnobservedExceptionHandler unobservedExceptionHandler;
    private boolean cancelled;
    private boolean complete;
    private Exception error;
    private boolean errorHasBeenObserved;
    private TResult result;
    private UnobservedErrorNotifier unobservedErrorNotifier;
    public static final ExecutorService BACKGROUND_EXECUTOR = BoltsExecutors.background();
    private static final Executor IMMEDIATE_EXECUTOR = BoltsExecutors.immediate();
    public static final Executor UI_THREAD_EXECUTOR = AndroidExecutors.uiThread();
    private static Task<?> TASK_NULL = new Task<>((Object) null);
    private static Task<Boolean> TASK_TRUE = new Task<>(true);
    private static Task<Boolean> TASK_FALSE = new Task<>(false);
    private static Task<?> TASK_CANCELLED = new Task<>(true);
    private final Object lock = new Object();
    private List<Continuation<TResult, Void>> continuations = new ArrayList();

    public interface UnobservedExceptionHandler {
        void unobservedException(Task<?> task, UnobservedTaskException unobservedTaskException);
    }

    public static <TResult> Task<TResult> cancelled() {
        return (Task<TResult>) TASK_CANCELLED;
    }

    public static UnobservedExceptionHandler getUnobservedExceptionHandler() {
        return unobservedExceptionHandler;
    }

    public static void setUnobservedExceptionHandler(UnobservedExceptionHandler unobservedExceptionHandler2) {
        unobservedExceptionHandler = unobservedExceptionHandler2;
    }

    public <TOut> Task<TOut> cast() {
        return this;
    }

    public Task() {
    }

    private Task(TResult tresult) {
        trySetResult(tresult);
    }

    private Task(boolean z) {
        if (z) {
            trySetCancelled();
        } else {
            trySetResult(null);
        }
    }

    public static <TResult> Task<TResult>.TaskCompletionSource create() {
        return new TaskCompletionSource();
    }

    public boolean isCompleted() {
        boolean z;
        synchronized (this.lock) {
            z = this.complete;
        }
        return z;
    }

    public boolean isCancelled() {
        boolean z;
        synchronized (this.lock) {
            z = this.cancelled;
        }
        return z;
    }

    public boolean isFaulted() {
        boolean z;
        synchronized (this.lock) {
            z = getError() != null;
        }
        return z;
    }

    public TResult getResult() {
        TResult tresult;
        synchronized (this.lock) {
            tresult = this.result;
        }
        return tresult;
    }

    public Exception getError() {
        Exception exc;
        synchronized (this.lock) {
            if (this.error != null) {
                this.errorHasBeenObserved = true;
                UnobservedErrorNotifier unobservedErrorNotifier = this.unobservedErrorNotifier;
                if (unobservedErrorNotifier != null) {
                    unobservedErrorNotifier.setObserved();
                    this.unobservedErrorNotifier = null;
                }
            }
            exc = this.error;
        }
        return exc;
    }

    public void waitForCompletion() throws InterruptedException {
        synchronized (this.lock) {
            if (!isCompleted()) {
                this.lock.wait();
            }
        }
    }

    public boolean waitForCompletion(long j, TimeUnit timeUnit) throws InterruptedException {
        boolean isCompleted;
        synchronized (this.lock) {
            if (!isCompleted()) {
                this.lock.wait(timeUnit.toMillis(j));
            }
            isCompleted = isCompleted();
        }
        return isCompleted;
    }

    public static <TResult> Task<TResult> forResult(TResult tresult) {
        if (tresult == null) {
            return (Task<TResult>) TASK_NULL;
        }
        if (tresult instanceof Boolean) {
            return ((Boolean) tresult).booleanValue() ? (Task<TResult>) TASK_TRUE : (Task<TResult>) TASK_FALSE;
        }
        bolts.TaskCompletionSource taskCompletionSource = new bolts.TaskCompletionSource();
        taskCompletionSource.setResult(tresult);
        return taskCompletionSource.getTask();
    }

    public static <TResult> Task<TResult> forError(Exception exc) {
        bolts.TaskCompletionSource taskCompletionSource = new bolts.TaskCompletionSource();
        taskCompletionSource.setError(exc);
        return taskCompletionSource.getTask();
    }

    public static Task<Void> delay(long j) {
        return delay(j, BoltsExecutors.scheduled(), null);
    }

    public static Task<Void> delay(long j, CancellationToken cancellationToken) {
        return delay(j, BoltsExecutors.scheduled(), cancellationToken);
    }

    static Task<Void> delay(long j, ScheduledExecutorService scheduledExecutorService, CancellationToken cancellationToken) {
        if (cancellationToken == null || !cancellationToken.isCancellationRequested()) {
            if (j <= 0) {
                return forResult(null);
            }
            final bolts.TaskCompletionSource taskCompletionSource = new bolts.TaskCompletionSource();
            final ScheduledFuture<?> schedule = scheduledExecutorService.schedule(new Runnable() {
                @Override
                public void run() {
                    bolts.TaskCompletionSource.this.trySetResult(null);
                }
            }, j, TimeUnit.MILLISECONDS);
            if (cancellationToken != null) {
                cancellationToken.register(new Runnable() {
                    @Override
                    public void run() {
                        schedule.cancel(true);
                        taskCompletionSource.trySetCancelled();
                    }
                });
            }
            return taskCompletionSource.getTask();
        }
        return cancelled();
    }

    public Task<Void> makeVoid() {
        return continueWithTask((Continuation<TResult, Task<Void>>) new Continuation<TResult, Task<Void>>() {
            @Override
            public Task<Void> then(Task<TResult> task) throws Exception {
                if (task.isCancelled()) {
                    return Task.cancelled();
                }
                if (task.isFaulted()) {
                    return Task.forError(task.getError());
                }
                return Task.forResult(null);
            }
        });
    }

    public static <TResult> Task<TResult> callInBackground(Callable<TResult> callable) {
        return call(callable, BACKGROUND_EXECUTOR, null);
    }

    public static <TResult> Task<TResult> callInBackground(Callable<TResult> callable, CancellationToken cancellationToken) {
        return call(callable, BACKGROUND_EXECUTOR, cancellationToken);
    }

    public static <TResult> Task<TResult> call(Callable<TResult> callable, Executor executor) {
        return call(callable, executor, null);
    }

    public static <TResult> Task<TResult> call(final Callable<TResult> callable, Executor executor, final CancellationToken cancellationToken) {
        final bolts.TaskCompletionSource taskCompletionSource = new bolts.TaskCompletionSource();
        try {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    CancellationToken cancellationToken2 = CancellationToken.this;
                    if (cancellationToken2 != null && cancellationToken2.isCancellationRequested()) {
                        taskCompletionSource.setCancelled();
                        return;
                    }
                    try {
                        taskCompletionSource.setResult(callable.call());
                    } catch (CancellationException unused) {
                        taskCompletionSource.setCancelled();
                    } catch (Exception e) {
                        taskCompletionSource.setError(e);
                    }
                }
            });
        } catch (Exception e) {
            taskCompletionSource.setError(new ExecutorException(e));
        }
        return taskCompletionSource.getTask();
    }

    public static <TResult> Task<TResult> call(Callable<TResult> callable) {
        return call(callable, IMMEDIATE_EXECUTOR, null);
    }

    public static <TResult> Task<TResult> call(Callable<TResult> callable, CancellationToken cancellationToken) {
        return call(callable, IMMEDIATE_EXECUTOR, cancellationToken);
    }

    public static <TResult> Task<Task<TResult>> whenAnyResult(Collection<? extends Task<TResult>> collection) {
        if (collection.size() == 0) {
            return forResult(null);
        }
        final bolts.TaskCompletionSource taskCompletionSource = new bolts.TaskCompletionSource();
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        for (Task<TResult> task : collection) {
            task.continueWith((Continuation<TResult, Void>) new Continuation<TResult, Void>() {
                @Override
                public Void then(Task<TResult> task2) {
                    if (atomicBoolean.compareAndSet(false, true)) {
                        taskCompletionSource.setResult(task2);
                        return null;
                    }
                    task2.getError();
                    return null;
                }
            });
        }
        return taskCompletionSource.getTask();
    }

    public static Task<Task<?>> whenAny(Collection<? extends Task<?>> collection) {
        if (collection.size() == 0) {
            return forResult(null);
        }
        final bolts.TaskCompletionSource taskCompletionSource = new bolts.TaskCompletionSource();
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        for (Task<?> task : collection) {
            task.continueWith(new Continuation<Object, Void>() {
                @Override
                public Void then(Task<Object> task2) {
                    if (atomicBoolean.compareAndSet(false, true)) {
                        taskCompletionSource.setResult(task2);
                        return null;
                    }
                    task2.getError();
                    return null;
                }
            });
        }
        return taskCompletionSource.getTask();
    }

    public static <TResult> Task<List<TResult>> whenAllResult(final Collection<? extends Task<TResult>> collection) {
        return (Task<List<TResult>>) whenAll(collection).onSuccess((Continuation<Void, List<TResult>>) new Continuation<Void, List<TResult>>() {
            @Override
            public List<TResult> then(Task<Void> task) throws Exception {
                if (collection.size() == 0) {
                    return Collections.emptyList();
                }
                ArrayList arrayList = new ArrayList();
                for (Task task2 : collection) {
                    arrayList.add(task2.getResult());
                }
                return arrayList;
            }
        });
    }

    public static Task<Void> whenAll(Collection<? extends Task<?>> collection) {
        if (collection.size() == 0) {
            return forResult(null);
        }
        final bolts.TaskCompletionSource taskCompletionSource = new bolts.TaskCompletionSource();
        final ArrayList arrayList = new ArrayList();
        final Object obj = new Object();
        final AtomicInteger atomicInteger = new AtomicInteger(collection.size());
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        for (Task<?> task : collection) {
            task.continueWith(new Continuation<Object, Void>() {
                @Override
                public Void then(Task<Object> task2) {
                    if (task2.isFaulted()) {
                        synchronized (obj) {
                            arrayList.add(task2.getError());
                        }
                    }
                    if (task2.isCancelled()) {
                        atomicBoolean.set(true);
                    }
                    if (atomicInteger.decrementAndGet() == 0) {
                        if (arrayList.size() != 0) {
                            if (arrayList.size() == 1) {
                                taskCompletionSource.setError((Exception) arrayList.get(0));
                            } else {
                                taskCompletionSource.setError(new AggregateException(String.format("There were %d exceptions.", Integer.valueOf(arrayList.size())), arrayList));
                            }
                        } else if (atomicBoolean.get()) {
                            taskCompletionSource.setCancelled();
                        } else {
                            taskCompletionSource.setResult(null);
                        }
                    }
                    return null;
                }
            });
        }
        return taskCompletionSource.getTask();
    }

    public Task<Void> continueWhile(Callable<Boolean> callable, Continuation<Void, Task<Void>> continuation) {
        return continueWhile(callable, continuation, IMMEDIATE_EXECUTOR, null);
    }

    public Task<Void> continueWhile(Callable<Boolean> callable, Continuation<Void, Task<Void>> continuation, CancellationToken cancellationToken) {
        return continueWhile(callable, continuation, IMMEDIATE_EXECUTOR, cancellationToken);
    }

    public Task<Void> continueWhile(Callable<Boolean> callable, Continuation<Void, Task<Void>> continuation, Executor executor) {
        return continueWhile(callable, continuation, executor, null);
    }

    public Task<Void> continueWhile(final Callable<Boolean> callable, final Continuation<Void, Task<Void>> continuation, final Executor executor, final CancellationToken cancellationToken) {
        final Capture capture = new Capture();
        capture.set(new Continuation<Void, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Void> task) throws Exception {
                CancellationToken cancellationToken2 = cancellationToken;
                if (cancellationToken2 != null && cancellationToken2.isCancellationRequested()) {
                    return Task.cancelled();
                }
                if (((Boolean) callable.call()).booleanValue()) {
                    return Task.forResult(null).onSuccessTask(continuation, executor).onSuccessTask((Continuation) capture.get(), executor);
                }
                return Task.forResult(null);
            }
        });
        return makeVoid().continueWithTask((Continuation) capture.get(), executor);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWith(Continuation<TResult, TContinuationResult> continuation, Executor executor) {
        return continueWith(continuation, executor, null);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWith(final Continuation<TResult, TContinuationResult> continuation, final Executor executor, final CancellationToken cancellationToken) {
        boolean isCompleted;
        final bolts.TaskCompletionSource taskCompletionSource = new bolts.TaskCompletionSource();
        synchronized (this.lock) {
            isCompleted = isCompleted();
            if (!isCompleted) {
                this.continuations.add(new Continuation<TResult, Void>() {
                    @Override
                    public Void then(Task<TResult> task) {
                        Task.completeImmediately(taskCompletionSource, continuation, task, executor, cancellationToken);
                        return null;
                    }
                });
            }
        }
        if (isCompleted) {
            completeImmediately(taskCompletionSource, continuation, this, executor, cancellationToken);
        }
        return taskCompletionSource.getTask();
    }

    public <TContinuationResult> Task<TContinuationResult> continueWith(Continuation<TResult, TContinuationResult> continuation) {
        return continueWith(continuation, IMMEDIATE_EXECUTOR, null);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWith(Continuation<TResult, TContinuationResult> continuation, CancellationToken cancellationToken) {
        return continueWith(continuation, IMMEDIATE_EXECUTOR, cancellationToken);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWithTask(Continuation<TResult, Task<TContinuationResult>> continuation, Executor executor) {
        return continueWithTask(continuation, executor, null);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWithTask(final Continuation<TResult, Task<TContinuationResult>> continuation, final Executor executor, final CancellationToken cancellationToken) {
        boolean isCompleted;
        final bolts.TaskCompletionSource taskCompletionSource = new bolts.TaskCompletionSource();
        synchronized (this.lock) {
            isCompleted = isCompleted();
            if (!isCompleted) {
                this.continuations.add(new Continuation<TResult, Void>() {
                    @Override
                    public Void then(Task<TResult> task) {
                        Task.completeAfterTask(taskCompletionSource, continuation, task, executor, cancellationToken);
                        return null;
                    }
                });
            }
        }
        if (isCompleted) {
            completeAfterTask(taskCompletionSource, continuation, this, executor, cancellationToken);
        }
        return taskCompletionSource.getTask();
    }

    public <TContinuationResult> Task<TContinuationResult> continueWithTask(Continuation<TResult, Task<TContinuationResult>> continuation) {
        return continueWithTask(continuation, IMMEDIATE_EXECUTOR, null);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWithTask(Continuation<TResult, Task<TContinuationResult>> continuation, CancellationToken cancellationToken) {
        return continueWithTask(continuation, IMMEDIATE_EXECUTOR, cancellationToken);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccess(Continuation<TResult, TContinuationResult> continuation, Executor executor) {
        return onSuccess(continuation, executor, null);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccess(final Continuation<TResult, TContinuationResult> continuation, Executor executor, final CancellationToken cancellationToken) {
        return continueWithTask(new Continuation<TResult, Task<TContinuationResult>>() {
            @Override
            public Task<TContinuationResult> then(Task<TResult> task) {
                CancellationToken cancellationToken2 = cancellationToken;
                if (cancellationToken2 != null && cancellationToken2.isCancellationRequested()) {
                    return Task.cancelled();
                }
                if (task.isFaulted()) {
                    return Task.forError(task.getError());
                }
                if (task.isCancelled()) {
                    return Task.cancelled();
                }
                return task.continueWith(continuation);
            }
        }, executor);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccess(Continuation<TResult, TContinuationResult> continuation) {
        return onSuccess(continuation, IMMEDIATE_EXECUTOR, null);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccess(Continuation<TResult, TContinuationResult> continuation, CancellationToken cancellationToken) {
        return onSuccess(continuation, IMMEDIATE_EXECUTOR, cancellationToken);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(Continuation<TResult, Task<TContinuationResult>> continuation, Executor executor) {
        return onSuccessTask(continuation, executor, null);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(final Continuation<TResult, Task<TContinuationResult>> continuation, Executor executor, final CancellationToken cancellationToken) {
        return continueWithTask(new Continuation<TResult, Task<TContinuationResult>>() {
            @Override
            public Task<TContinuationResult> then(Task<TResult> task) {
                CancellationToken cancellationToken2 = cancellationToken;
                if (cancellationToken2 != null && cancellationToken2.isCancellationRequested()) {
                    return Task.cancelled();
                }
                if (task.isFaulted()) {
                    return Task.forError(task.getError());
                }
                if (task.isCancelled()) {
                    return Task.cancelled();
                }
                return task.continueWithTask(continuation);
            }
        }, executor);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(Continuation<TResult, Task<TContinuationResult>> continuation) {
        return onSuccessTask(continuation, IMMEDIATE_EXECUTOR);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(Continuation<TResult, Task<TContinuationResult>> continuation, CancellationToken cancellationToken) {
        return onSuccessTask(continuation, IMMEDIATE_EXECUTOR, cancellationToken);
    }

    public static <TContinuationResult, TResult> void completeImmediately(final bolts.TaskCompletionSource<TContinuationResult> taskCompletionSource, final Continuation<TResult, TContinuationResult> continuation, final Task<TResult> task, Executor executor, final CancellationToken cancellationToken) {
        try {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    CancellationToken cancellationToken2 = CancellationToken.this;
                    if (cancellationToken2 != null && cancellationToken2.isCancellationRequested()) {
                        taskCompletionSource.setCancelled();
                        return;
                    }
                    try {
                        taskCompletionSource.setResult(continuation.then(task));
                    } catch (CancellationException unused) {
                        taskCompletionSource.setCancelled();
                    } catch (Exception e) {
                        taskCompletionSource.setError(e);
                    }
                }
            });
        } catch (Exception e) {
            taskCompletionSource.setError(new ExecutorException(e));
        }
    }

    public static <TContinuationResult, TResult> void completeAfterTask(final bolts.TaskCompletionSource<TContinuationResult> taskCompletionSource, final Continuation<TResult, Task<TContinuationResult>> continuation, final Task<TResult> task, Executor executor, final CancellationToken cancellationToken) {
        try {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    CancellationToken cancellationToken2 = CancellationToken.this;
                    if (cancellationToken2 != null && cancellationToken2.isCancellationRequested()) {
                        taskCompletionSource.setCancelled();
                        return;
                    }
                    try {
                        Task task2 = (Task) continuation.then(task);
                        if (task2 == null) {
                            taskCompletionSource.setResult(null);
                        } else {
                            task2.continueWith(new Continuation<TContinuationResult, Void>() {
                                @Override
                                public Void then(Task<TContinuationResult> task3) {
                                    if (CancellationToken.this != null && CancellationToken.this.isCancellationRequested()) {
                                        taskCompletionSource.setCancelled();
                                        return null;
                                    }
                                    if (task3.isCancelled()) {
                                        taskCompletionSource.setCancelled();
                                    } else if (task3.isFaulted()) {
                                        taskCompletionSource.setError(task3.getError());
                                    } else {
                                        taskCompletionSource.setResult(task3.getResult());
                                    }
                                    return null;
                                }
                            });
                        }
                    } catch (CancellationException unused) {
                        taskCompletionSource.setCancelled();
                    } catch (Exception e) {
                        taskCompletionSource.setError(e);
                    }
                }
            });
        } catch (Exception e) {
            taskCompletionSource.setError(new ExecutorException(e));
        }
    }

    private void runContinuations() {
        synchronized (this.lock) {
            for (Continuation<TResult, Void> continuation : this.continuations) {
                try {
                    continuation.then(this);
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new RuntimeException(e2);
                }
            }
            this.continuations = null;
        }
    }

    public boolean trySetCancelled() {
        synchronized (this.lock) {
            if (this.complete) {
                return false;
            }
            this.complete = true;
            this.cancelled = true;
            this.lock.notifyAll();
            runContinuations();
            return true;
        }
    }

    public boolean trySetResult(TResult tresult) {
        synchronized (this.lock) {
            if (this.complete) {
                return false;
            }
            this.complete = true;
            this.result = tresult;
            this.lock.notifyAll();
            runContinuations();
            return true;
        }
    }

    public boolean trySetError(Exception exc) {
        synchronized (this.lock) {
            if (this.complete) {
                return false;
            }
            this.complete = true;
            this.error = exc;
            this.errorHasBeenObserved = false;
            this.lock.notifyAll();
            runContinuations();
            if (!this.errorHasBeenObserved && getUnobservedExceptionHandler() != null) {
                this.unobservedErrorNotifier = new UnobservedErrorNotifier(this);
            }
            return true;
        }
    }

    public class TaskCompletionSource extends bolts.TaskCompletionSource<TResult> {
        TaskCompletionSource() {
        }
    }
}
