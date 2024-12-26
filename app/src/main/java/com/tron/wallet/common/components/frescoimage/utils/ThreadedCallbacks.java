package com.tron.wallet.common.components.frescoimage.utils;

import android.os.Handler;
import android.os.Looper;
import com.tron.tron_base.frame.utils.LogUtils;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
public final class ThreadedCallbacks implements InvocationHandler {
    private final Handler mHandler;
    private final Object mTarget;
    private static final Object NON_SENSE = new Object();
    private static final Handler MAIN_HANDLER = new Handler(Looper.getMainLooper());

    private ThreadedCallbacks(Handler handler, Object obj) {
        this.mHandler = handler;
        this.mTarget = obj;
    }

    public static <T> T create(Class<T> cls, T t) {
        return (T) create(MAIN_HANDLER, cls, t);
    }

    public static <T> T create(Handler handler, Class<T> cls, T t) {
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new ThreadedCallbacks(handler, t));
    }

    @Override
    public Object invoke(Object obj, final Method method, final Object[] objArr) throws Throwable {
        if (!method.getReturnType().equals(Void.TYPE)) {
            throw new RuntimeException("Method should return void: " + method);
        }
        if (Looper.myLooper() == this.mHandler.getLooper()) {
            method.invoke(this.mTarget, objArr);
        } else {
            this.mHandler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        method.invoke(mTarget, objArr);
                    } catch (Exception e) {
                        LogUtils.e(e);
                    }
                }
            });
        }
        return NON_SENSE;
    }
}
