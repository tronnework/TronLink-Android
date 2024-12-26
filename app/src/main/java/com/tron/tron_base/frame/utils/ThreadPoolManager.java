package com.tron.tron_base.frame.utils;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
public final class ThreadPoolManager {
    public static final String DOT = ".";
    private static final int SIZE_CORE_POOL = 15;
    private static final int SIZE_MAX_POOL = 15;
    private static final String TAG = "ThreadPoolManager";
    public static final String THREAD_NAME_SUFFIX = "-Thread";
    private static ThreadPoolManager sThreadPoolManager = new ThreadPoolManager();
    private final ThreadPoolExecutor mThreadPool = new ThreadPoolExecutor(15, 15, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());

    public static ThreadPoolManager newInstance() {
        return sThreadPoolManager;
    }

    private ThreadPoolManager() {
    }

    public void perpare() {
        if (!this.mThreadPool.isShutdown() || this.mThreadPool.prestartCoreThread()) {
            return;
        }
        this.mThreadPool.prestartAllCoreThreads();
    }

    public void addExecuteTask(Runnable runnable, boolean z) {
        if (z) {
            addExecuteTask(runnable, 5);
        } else {
            addExecuteTask(runnable, 4);
        }
    }

    public void addExecuteTask(Runnable runnable) {
        addExecuteTask(runnable, 4);
    }

    public void addExecuteTask(Runnable runnable, int i) {
        if (runnable != null) {
            this.mThreadPool.execute(new CustomRunnable(runnable, Thread.currentThread().getStackTrace().length > i ? getThreadName(Thread.currentThread().getStackTrace()[i].getClassName(), Thread.currentThread().getStackTrace()[i].getMethodName()) : null));
        }
    }

    private String getThreadName(String str, String str2) {
        return str + DOT + str2 + THREAD_NAME_SUFFIX;
    }

    public static class CustomRunnable implements Runnable {
        String name;
        Runnable runnable;

        public CustomRunnable(Runnable runnable, String str) {
            this.runnable = runnable;
            this.name = str;
        }

        @Override
        public void run() {
            Thread.currentThread().setName(this.name);
            this.runnable.run();
        }
    }

    protected boolean isTaskEnd() {
        return this.mThreadPool.getActiveCount() == 0;
    }

    public int getQueue() {
        return this.mThreadPool.getQueue().size();
    }

    public int getPoolSize() {
        return this.mThreadPool.getPoolSize();
    }

    public long getCompletedTaskCount() {
        return this.mThreadPool.getCompletedTaskCount();
    }

    public void shutdown() {
        this.mThreadPool.shutdown();
    }
}
