package com.tron.wallet.business.confirm.core.ledger;

import android.app.Activity;
import android.content.Context;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.ledger.bleclient.BleErrorHelper;
import com.tron.wallet.ledger.blemodule.errors.BleError;
import com.tronlinkpro.wallet.R;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.TimeoutException;
public class LedgerErrorToast {
    private boolean quitOnError;

    private LedgerErrorToast() {
    }

    private static class Nested {
        static LedgerErrorToast instance = new LedgerErrorToast();

        private Nested() {
        }
    }

    public static LedgerErrorToast getInstance(boolean z) {
        Nested.instance.quitOnError = z;
        return Nested.instance;
    }

    public BleErrorHelper.OnGetAddressErrorHandler ToastGetAddress(Context context, BleErrorHelper.OnGetAddressErrorCallback onGetAddressErrorCallback) {
        return new BleErrorHelper.OnGetAddressErrorHandler((BleErrorHelper.OnGetAddressErrorCallback) getErrorCallback(context, BleErrorHelper.OnGetAddressErrorCallback.class, onGetAddressErrorCallback));
    }

    public BleErrorHelper.OnConnectErrorHandler ToastOpen(Context context, BleErrorHelper.OnConnectErrorCallback onConnectErrorCallback) {
        return new BleErrorHelper.OnConnectErrorHandler((BleErrorHelper.OnConnectErrorCallback) getErrorCallback(context, BleErrorHelper.OnConnectErrorCallback.class, onConnectErrorCallback));
    }

    public BleErrorHelper.OnOpenTronAppErrorHandler ToastOpenTronApp(Context context, BleErrorHelper.OnOpenTronAppErrorCallback onOpenTronAppErrorCallback) {
        return new BleErrorHelper.OnOpenTronAppErrorHandler((BleErrorHelper.OnOpenTronAppErrorCallback) getErrorCallback(context, BleErrorHelper.OnOpenTronAppErrorCallback.class, onOpenTronAppErrorCallback));
    }

    public BleErrorHelper.OnSignErrorHandler ToastSign(Context context, BleErrorHelper.OnSignErrorCallback onSignErrorCallback) {
        return new BleErrorHelper.OnSignErrorHandler((BleErrorHelper.OnSignErrorCallback) getErrorCallback(context, BleErrorHelper.OnSignErrorCallback.class, onSignErrorCallback));
    }

    public <T extends BleErrorHelper.OnErrorCallback> T getErrorCallback(final Context context, Class<T> cls, final T t) {
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() {
            @Override
            public final Object invoke(Object obj, Method method, Object[] objArr) {
                Object lambda$getErrorCallback$0;
                lambda$getErrorCallback$0 = lambda$getErrorCallback$0(context, t, obj, method, objArr);
                return lambda$getErrorCallback$0;
            }
        });
    }

    public Object lambda$getErrorCallback$0(Context context, BleErrorHelper.OnErrorCallback onErrorCallback, Object obj, Method method, Object[] objArr) throws Throwable {
        if (objArr.length == 1) {
            Object obj2 = objArr[0];
            if (obj2 instanceof BleError) {
                Toast(context, (BleError) obj2);
            }
        }
        if (onErrorCallback != null) {
            return method.invoke(onErrorCallback, objArr);
        }
        return null;
    }

    public void Toast(Context context, BleError bleError) {
        IToast.getIToast().setImage(R.mipmap.toast_warn).show(BleErrorHelper.getErrorString(context, bleError));
        if ((context instanceof Activity) && this.quitOnError) {
            ((Activity) context).finish();
        }
    }

    public void Toast(Context context, Throwable th) {
        if (th instanceof BleError) {
            IToast.getIToast().setImage(R.mipmap.toast_warn).show(BleErrorHelper.getErrorString(context, (BleError) th));
        } else if (th instanceof TimeoutException) {
            IToast.getIToast().setImage(R.mipmap.toast_warn).show(context.getResources().getString(R.string.action_timeout));
        }
        if ((context instanceof Activity) && this.quitOnError) {
            ((Activity) context).finish();
        }
    }
}
