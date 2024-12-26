package com.tron.tron_base.frame.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.tron.tron_base.R;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.StringTronUtil;
import com.tron.tron_base.frame.view.itoast.ToastUtils;
public class IToast {
    private static IToast iToast;
    public ImageView iv_img;
    private Toast toast;
    private TextView tv;
    private TextView tvTop;
    private View v;

    public IToast() {
        View inflate = View.inflate(AppContextUtil.getmApplication(), R.layout.toast, null);
        this.v = inflate;
        this.iv_img = (ImageView) inflate.findViewById(R.id.iv_img);
        this.tvTop = (TextView) this.v.findViewById(R.id.tv_toast_top);
    }

    public static IToast getIToast() {
        if (iToast == null) {
            iToast = new IToast();
        }
        iToast.iv_img.setVisibility(View.GONE);
        return iToast;
    }

    public static IToast getImageIToast() {
        if (iToast == null) {
            iToast = new IToast();
        }
        iToast.iv_img.setVisibility(View.VISIBLE);
        return iToast;
    }

    public IToast setImage(int i) {
        if (iToast == null) {
            iToast = new IToast();
        }
        return iToast;
    }

    public IToast setIcon(int i) {
        if (iToast == null) {
            iToast = new IToast();
        }
        iToast.iv_img.setImageResource(i);
        iToast.iv_img.setVisibility(View.VISIBLE);
        return iToast;
    }

    public void show(String str) {
        this.tvTop.setVisibility(View.GONE);
        ToastUtils.setView(this.v);
        ToastUtils.setGravity(17, 0, 0);
        ToastUtils.show((CharSequence) str);
    }

    public void show(String str, int i) {
        this.tvTop.setVisibility(View.GONE);
        iToast.iv_img.setImageResource(i);
        iToast.iv_img.setVisibility(View.VISIBLE);
        ToastUtils.setView(this.v);
        ToastUtils.setGravity(17, 0, 0);
        ToastUtils.forceShowAgain(str);
    }

    public void showLongBottom(int i) {
        if (i == 0) {
            return;
        }
        try {
            String resString = StringTronUtil.getResString(i);
            this.tvTop.setVisibility(View.GONE);
            ToastUtils.setView(this.v);
            ToastUtils.setGravity(80, 0, dip2px(130.0f));
            ToastUtils.getToast().setDuration(1);
            ToastUtils.show((CharSequence) resString);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void showLong(String str) {
        this.tvTop.setVisibility(View.GONE);
        ToastUtils.setView(this.v);
        ToastUtils.setGravity(17, 0, 0);
        ToastUtils.getToast().setDuration(1);
        ToastUtils.show((CharSequence) str);
    }

    public void showLongBottom(String str) {
        this.tvTop.setVisibility(View.GONE);
        ToastUtils.setView(this.v);
        ToastUtils.setGravity(80, 0, dip2px(130.0f));
        ToastUtils.getToast().setDuration(1);
        ToastUtils.show((CharSequence) str);
    }

    public void show(int i) {
        this.tvTop.setVisibility(View.GONE);
        ToastUtils.setView(this.v);
        ToastUtils.setGravity(17, 0, 0);
        ToastUtils.show(i);
    }

    public void forceShowAgain(String str) {
        this.tvTop.setVisibility(View.GONE);
        ToastUtils.setView(this.v);
        ToastUtils.setGravity(17, 0, 0);
        ToastUtils.forceShowAgain(str);
    }

    public void showAsBottomn(int i) {
        this.tvTop.setVisibility(View.GONE);
        ToastUtils.setView(this.v);
        ToastUtils.setGravity(80, 0, dip2px(130.0f));
        ToastUtils.show(i);
    }

    public void showAsBottomnDouble(int i, int i2) {
        this.tvTop.setVisibility(View.VISIBLE);
        this.tvTop.setText(i);
        ToastUtils.setView(this.v);
        ToastUtils.setGravity(80, 0, dip2px(130.0f));
        ToastUtils.show(i2);
    }

    public void cancleToast() {
        Toast toast = this.toast;
        if (toast != null) {
            toast.cancel();
        }
    }

    private int dip2px(float f) {
        return (int) ((f * AppContextUtil.getContext().getResources().getDisplayMetrics().density) + 0.5f);
    }
}
