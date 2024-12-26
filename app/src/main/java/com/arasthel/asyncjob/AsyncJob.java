package com.arasthel.asyncjob;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
public class AsyncJob<JobResult> {
    private static Handler uiHandler = new Handler(Looper.getMainLooper());
    private AsyncAction actionInBackground;
    private AsyncResultAction actionOnMainThread;
    private FutureTask asyncFutureTask;
    private Thread asyncThread;
    private ExecutorService executorService;
    private JobResult result;

    public interface AsyncAction<ActionResult> {
        ActionResult doAsync();
    }

    public interface AsyncResultAction<ActionResult> {
        void onResult(ActionResult actionresult);
    }

    public interface OnBackgroundJob {
        void doOnBackground();
    }

    public interface OnMainThreadJob {
        void doInUIThread();
    }

    public AsyncAction getActionInBackground() {
        return this.actionInBackground;
    }

    public AsyncResultAction getActionOnResult() {
        return this.actionOnMainThread;
    }

    public ExecutorService getExecutorService() {
        return this.executorService;
    }

    public void setActionInBackground(AsyncAction asyncAction) {
        this.actionInBackground = asyncAction;
    }

    public void setActionOnResult(AsyncResultAction asyncResultAction) {
        this.actionOnMainThread = asyncResultAction;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public static void doOnMainThread(final OnMainThreadJob onMainThreadJob) {
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                OnMainThreadJob.this.doInUIThread();
            }
        });
    }

    public static void doInBackground(final OnBackgroundJob onBackgroundJob) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OnBackgroundJob.this.doOnBackground();
            }
        }).start();
    }

    public static FutureTask doInBackground(final OnBackgroundJob onBackgroundJob, ExecutorService executorService) {
        return (FutureTask) executorService.submit(new Runnable() {
            @Override
            public void run() {
                OnBackgroundJob.this.doOnBackground();
            }
        });
    }

    public void start() {
        if (this.actionInBackground != null) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    AsyncJob asyncJob = AsyncJob.this;
                    asyncJob.result = asyncJob.actionInBackground.doAsync();
                    onResult();
                }
            };
            if (getExecutorService() != null) {
                this.asyncFutureTask = (FutureTask) getExecutorService().submit(runnable);
                return;
            }
            Thread thread = new Thread(runnable);
            this.asyncThread = thread;
            thread.start();
        }
    }

    public void cancel() {
        if (this.actionInBackground != null) {
            if (this.executorService != null) {
                this.asyncFutureTask.cancel(true);
            } else {
                this.asyncThread.interrupt();
            }
        }
    }

    public void onResult() {
        if (this.actionOnMainThread != null) {
            uiHandler.post(new Runnable() {
                @Override
                public void run() {
                    actionOnMainThread.onResult(result);
                }
            });
        }
    }

    public static class AsyncJobBuilder<JobResult> {
        private AsyncAction<JobResult> asyncAction;
        private AsyncResultAction asyncResultAction;
        private ExecutorService executor;

        public AsyncJobBuilder<JobResult> doInBackground(AsyncAction<JobResult> asyncAction) {
            this.asyncAction = asyncAction;
            return this;
        }

        public AsyncJobBuilder<JobResult> doWhenFinished(AsyncResultAction asyncResultAction) {
            this.asyncResultAction = asyncResultAction;
            return this;
        }

        public AsyncJobBuilder<JobResult> withExecutor(ExecutorService executorService) {
            this.executor = executorService;
            return this;
        }

        public AsyncJob<JobResult> create() {
            AsyncJob<JobResult> asyncJob = new AsyncJob<>();
            asyncJob.setActionInBackground(this.asyncAction);
            asyncJob.setActionOnResult(this.asyncResultAction);
            asyncJob.setExecutorService(this.executor);
            return asyncJob;
        }
    }
}
