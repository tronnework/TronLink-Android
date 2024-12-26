package com.tron.wallet.common.utils;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.wallet.common.interfaces.OnSeletedListener;
import com.tronlinkpro.wallet.R;
public class Common1PopWindow extends PopupWindow {
    private int popupHeight;
    private int popupWidth;

    public Common1PopWindow(Context context, View view, int i, final OnSeletedListener onSeletedListener, String str, String str2, int i2) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.pop_vote_select, (ViewGroup) null);
        setContentView(inflate);
        float f = i2;
        setWidth(DensityUtils.dp2px(context, f));
        setHeight(-2);
        setOutsideTouchable(true);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable());
        setClippingEnabled(false);
        RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.rl_all);
        RelativeLayout relativeLayout2 = (RelativeLayout) inflate.findViewById(R.id.rl_me);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.iv_all);
        ImageView imageView2 = (ImageView) inflate.findViewById(R.id.iv_me);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_all);
        TextView textView2 = (TextView) inflate.findViewById(R.id.tv_me);
        if (!TextUtils.isEmpty(str)) {
            textView.setText(str);
        }
        if (!TextUtils.isEmpty(str2)) {
            textView2.setText(str2);
        }
        if (i == 0) {
            imageView.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.GONE);
        } else {
            imageView.setVisibility(View.GONE);
            imageView2.setVisibility(View.VISIBLE);
        }
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                OnSeletedListener onSeletedListener2 = onSeletedListener;
                if (onSeletedListener2 != null) {
                    onSeletedListener2.onSeleted(0);
                }
            }
        });
        relativeLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                OnSeletedListener onSeletedListener2 = onSeletedListener;
                if (onSeletedListener2 != null) {
                    onSeletedListener2.onSeleted(1);
                }
            }
        });
        inflate.measure(0, 0);
        this.popupHeight = inflate.getMeasuredHeight();
        this.popupWidth = DensityUtils.dp2px(context, f);
    }

    public void showUp(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        showAtLocation(view, 0, iArr[0] - (this.popupWidth / 2), iArr[1] - this.popupHeight);
    }

    public void showUp2(View view, int i) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        showAtLocation(view, 0, iArr[0], (iArr[1] - this.popupHeight) - i);
    }
}
