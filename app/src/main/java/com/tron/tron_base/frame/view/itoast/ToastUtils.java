package com.tron.tron_base.frame.view.itoast;

import android.app.AppOpsManager;
import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.view.itoast.style.ToastBlackStyle;
import java.lang.reflect.InvocationTargetException;
public final class ToastUtils {
    private static IToastInterceptor sInterceptor;
    private static IToastStrategy sStrategy;
    private static IToastStyle sStyle;
    private static Toast sToast;

    public static Toast getToast() {
        return sToast;
    }

    private ToastUtils() {
    }

    public static void init(Application application, IToastStyle iToastStyle) {
        initStyle(iToastStyle);
        init(application);
    }

    public static void init(Application application) {
        checkNullPointer(application);
        if (sInterceptor == null) {
            setToastInterceptor(new ToastInterceptor());
        }
        if (sStrategy == null) {
            setToastHandler(new ToastStrategy());
        }
        if (sStyle == null) {
            initStyle(new ToastBlackStyle(application));
        }
        if (!isNotificationEnabled(application)) {
            setToast(new SupportToast(application));
        } else if (Build.VERSION.SDK_INT == 25) {
            setToast(new SafeToast(application));
        } else {
            setToast(new BaseToast(application));
        }
        setView(createTextView(application.getApplicationContext()));
        setGravity(sStyle.getGravity(), sStyle.getXOffset(), sStyle.getYOffset());
    }

    public static void show(Object obj) {
        show((CharSequence) (obj != null ? obj.toString() : "null"));
    }

    public static void show(int i) {
        checkToastState();
        try {
            show(sToast.getView().getContext().getResources().getText(i));
        } catch (Resources.NotFoundException unused) {
            show((CharSequence) String.valueOf(i));
        }
    }

    public static synchronized void show(CharSequence charSequence) {
        synchronized (ToastUtils.class) {
            checkToastState();
            if (sInterceptor.intercept(sToast, charSequence)) {
                return;
            }
            sStrategy.show(charSequence);
        }
    }

    public static synchronized void forceShowAgain(CharSequence charSequence) {
        synchronized (ToastUtils.class) {
            checkToastState();
            if (sInterceptor.intercept(sToast, charSequence)) {
                return;
            }
            sStrategy.forceShowAgain(charSequence);
        }
    }

    public static synchronized void cancel() {
        synchronized (ToastUtils.class) {
            checkToastState();
            sStrategy.cancel();
        }
    }

    public static void setGravity(int i, int i2, int i3) {
        checkToastState();
        sToast.setGravity(Gravity.getAbsoluteGravity(i, sToast.getView().getResources().getConfiguration().getLayoutDirection()), i2, i3);
    }

    public static void setView(int i) {
        checkToastState();
        setView(View.inflate(sToast.getView().getContext().getApplicationContext(), i, null));
    }

    public static void setView(View view) {
        checkToastState();
        checkNullPointer(view);
        if (!(view.getContext() instanceof Application)) {
            LogUtils.i("ToastUtils setView\n:The view must be initialized using the context of the application");
        }
        Toast toast = sToast;
        if (toast != null) {
            toast.cancel();
            sToast.setView(view);
        }
    }

    public static <V extends View> V getView() {
        checkToastState();
        return (V) sToast.getView();
    }

    public static void initStyle(IToastStyle iToastStyle) {
        checkNullPointer(iToastStyle);
        sStyle = iToastStyle;
        Toast toast = sToast;
        if (toast != null) {
            toast.cancel();
            Toast toast2 = sToast;
            toast2.setView(createTextView(toast2.getView().getContext().getApplicationContext()));
            sToast.setGravity(sStyle.getGravity(), sStyle.getXOffset(), sStyle.getYOffset());
        }
    }

    public static void setToast(Toast toast) {
        checkNullPointer(toast);
        sToast = toast;
        IToastStrategy iToastStrategy = sStrategy;
        if (iToastStrategy != null) {
            iToastStrategy.bind(toast);
        }
    }

    public static void setToastHandler(IToastStrategy iToastStrategy) {
        checkNullPointer(iToastStrategy);
        sStrategy = iToastStrategy;
        Toast toast = sToast;
        if (toast != null) {
            iToastStrategy.bind(toast);
        }
    }

    public static void setToastInterceptor(IToastInterceptor iToastInterceptor) {
        checkNullPointer(iToastInterceptor);
        sInterceptor = iToastInterceptor;
    }

    private static void checkToastState() {
        if (sToast == null) {
            throw new IllegalStateException("ToastUtils has not been initialized");
        }
    }

    private static void checkNullPointer(Object obj) {
        if (obj == null) {
            throw new NullPointerException("are you ok?");
        }
    }

    private static TextView createTextView(Context context) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(sStyle.getBackgroundColor());
        gradientDrawable.setCornerRadius(sStyle.getCornerRadius());
        TextView textView = new TextView(context);
        textView.setId(16908299);
        textView.setTextColor(sStyle.getTextColor());
        textView.setTextSize(0, sStyle.getTextSize());
        textView.setPaddingRelative(sStyle.getPaddingStart(), sStyle.getPaddingTop(), sStyle.getPaddingEnd(), sStyle.getPaddingBottom());
        textView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        textView.setBackground(gradientDrawable);
        textView.setZ(sStyle.getZ());
        if (sStyle.getMaxLines() > 0) {
            textView.setMaxLines(sStyle.getMaxLines());
        }
        return textView;
    }

    private static boolean isNotificationEnabled(Context context) {
        boolean areNotificationsEnabled;
        if (Build.VERSION.SDK_INT >= 24) {
            areNotificationsEnabled = ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE)).areNotificationsEnabled();
            return areNotificationsEnabled;
        }
        AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        String packageName = context.getApplicationContext().getPackageName();
        int i = applicationInfo.uid;
        try {
            Class<?> cls = Class.forName(AppOpsManager.class.getName());
            return ((Integer) cls.getMethod("checkOpNoThrow", Integer.TYPE, Integer.TYPE, String.class).invoke(appOpsManager, Integer.valueOf(((Integer) cls.getDeclaredField("OP_POST_NOTIFICATION").get(Integer.class)).intValue()), Integer.valueOf(i), packageName)).intValue() == 0;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException | NoSuchMethodException | RuntimeException | InvocationTargetException unused) {
            return true;
        }
    }
}
