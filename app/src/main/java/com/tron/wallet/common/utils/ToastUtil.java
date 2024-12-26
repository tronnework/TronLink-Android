package com.tron.wallet.common.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.view.itoast.ToastUtils;
import com.tronlinkpro.wallet.R;
public class ToastUtil {
    private static Toast toast;
    private static ToastUtil toastUtil;
    public ImageView iv_img;
    private TextView tv;
    private TextView tvTop;
    private View v;

    public ToastUtil() {
        View inflate = View.inflate(AppContextUtil.getmApplication(), R.layout.toast, null);
        this.v = inflate;
        this.iv_img = (ImageView) inflate.findViewById(R.id.iv_img);
        this.tvTop = (TextView) this.v.findViewById(R.id.tv_toast_top);
        this.iv_img.setVisibility(View.GONE);
        this.tvTop.setVisibility(View.GONE);
    }

    public static synchronized ToastUtil getInstance() {
        ToastUtil toastUtil2;
        synchronized (ToastUtil.class) {
            if (toastUtil == null) {
                toastUtil = new ToastUtil();
            }
            toastUtil2 = toastUtil;
        }
        return toastUtil2;
    }

    public void showToast(Context context, String str) {
        try {
            show(context, str);
        } catch (Exception e) {
            SentryUtil.captureException(e);
            LogUtils.e(e);
        }
    }

    public void showToast(Context context, int i) {
        show(context, i);
    }

    public void showToast(Activity activity, String str) {
        show(activity, str);
    }

    public void show(Context context, String str) {
        this.tvTop.setVisibility(View.GONE);
        ToastUtils.setView(this.v);
        ToastUtils.setGravity(80, 0, dip2px(130.0f));
        ToastUtils.show((CharSequence) str);
    }

    public void show(Context context, int i) {
        this.tvTop.setVisibility(View.GONE);
        ToastUtils.setView(this.v);
        ToastUtils.setGravity(80, 0, dip2px(130.0f));
        ToastUtils.show(i);
    }

    private int dip2px(float f) {
        return (int) ((f * AppContextUtil.getContext().getResources().getDisplayMetrics().density) + 0.5f);
    }
}
