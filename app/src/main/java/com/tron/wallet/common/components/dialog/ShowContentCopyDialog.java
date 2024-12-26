package com.tron.wallet.common.components.dialog;

import android.content.Context;
import android.text.SpannableString;
import android.view.View;
import android.widget.TextView;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.components.dialog.CustomDialog;
import com.tron.wallet.common.interfaces.OnConfirmListener;
import com.tron.wallet.common.interfaces.OnDismissListener;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tronlinkpro.wallet.R;
public class ShowContentCopyDialog {
    public static void showUp(Context context, String str, SpannableString spannableString, String str2, String str3, OnConfirmListener onConfirmListener, OnDismissListener onDismissListener) {
        showUp(context, str, spannableString, str2, str3, onConfirmListener, onDismissListener, false);
    }

    public static void showUp(Context context, String str, SpannableString spannableString, String str2, String str3, final OnConfirmListener onConfirmListener, final OnDismissListener onDismissListener, boolean z) {
        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        final CustomDialog build = builder.style(R.style.loading_dialog).cancelTouchout(true).heightDimenRes(-2).widthDimenRes(-1).setGravity(17).view(R.layout.dialog_asset_scan).build();
        View view = builder.getView();
        TextView textView = (TextView) view.findViewById(R.id.tv_title);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_content);
        TextView textView3 = (TextView) view.findViewById(R.id.tv_cancle);
        TextView textView4 = (TextView) view.findViewById(R.id.tv_confirm);
        if (!StringTronUtil.isEmpty(str2)) {
            textView3.setText(str2);
        }
        if (!StringTronUtil.isEmpty(str3)) {
            textView4.setText(str3);
        }
        if (StringTronUtil.isEmpty(str)) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setText(str);
        }
        if (spannableString != null) {
            textView2.setText(spannableString);
        }
        if (z) {
            textView2.setGravity(3);
        }
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view2) {
                ShowContentCopyDialog.lambda$showUp$0(OnDismissListener.this, build, view2);
            }
        });
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view2) {
                ShowContentCopyDialog.lambda$showUp$1(OnConfirmListener.this, build, view2);
            }
        });
        build.show();
    }

    public static void lambda$showUp$0(OnDismissListener onDismissListener, CustomDialog customDialog, View view) {
        try {
            try {
                onDismissListener.onClick();
            } catch (Exception e) {
                LogUtils.e(e);
            }
        } finally {
            customDialog.dismiss();
        }
    }

    public static void lambda$showUp$1(OnConfirmListener onConfirmListener, CustomDialog customDialog, View view) {
        try {
            try {
                onConfirmListener.onClick();
            } catch (Exception e) {
                LogUtils.e(e);
            }
        } finally {
            customDialog.dismiss();
        }
    }
}
