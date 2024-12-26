package com.tron.wallet.common.components.popupwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;
public class AutoSizeTextPopupWindow extends PopupWindow {
    private TextView contentView;
    private int paddingBottom;
    private int paddingLeft;
    private int paddingRight;
    private int paddingTop;

    public interface OnPopupWindowClickListener {
        void onClick(PopupWindow popupWindow, View view);
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.paddingLeft = i;
        this.paddingTop = i2;
        this.paddingRight = i3;
        this.paddingBottom = i4;
    }

    public AutoSizeTextPopupWindow(Context context) {
        baseSet();
        initContentTextView(context);
    }

    private void initContentTextView(Context context) {
        TextView textView = new TextView(context);
        this.contentView = textView;
        textView.setGravity(17);
        this.contentView.setTextColor(-1);
        this.contentView.setTextSize(14.0f);
        setContentView(this.contentView);
    }

    private void baseSet() {
        setOutsideTouchable(true);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable());
    }

    public void setTextColor(int i) {
        TextView textView = this.contentView;
        if (textView != null) {
            textView.setTextColor(i);
        }
    }

    public void setTextSize(float f) {
        TextView textView = this.contentView;
        if (textView != null) {
            textView.setTextSize(f);
        }
    }

    public void setText(int i) {
        TextView textView = this.contentView;
        if (textView != null) {
            textView.setText(i);
        }
    }

    public void setBackground(int i) {
        TextView textView = this.contentView;
        if (textView != null) {
            textView.setBackgroundResource(i);
        }
    }

    public void showAtAnchorRight(View view) {
        TextView textView = this.contentView;
        if (textView == null) {
            return;
        }
        textView.measure(0, 0);
        setWidth(this.paddingLeft + this.contentView.getMeasuredWidth() + this.paddingRight);
        setHeight(this.paddingTop + this.contentView.getMeasuredHeight() + this.paddingBottom);
        showAsDropDown(view, view.getMeasuredWidth() >> 1, -(view.getMeasuredHeight() >> 1));
    }

    public void setOnPopupWindowClickListener(final OnPopupWindowClickListener onPopupWindowClickListener) {
        TextView textView = this.contentView;
        if (textView == null || onPopupWindowClickListener == null) {
            return;
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$setOnPopupWindowClickListener$0(onPopupWindowClickListener, view);
            }
        });
    }

    public void lambda$setOnPopupWindowClickListener$0(OnPopupWindowClickListener onPopupWindowClickListener, View view) {
        onPopupWindowClickListener.onClick(this, view);
    }
}
