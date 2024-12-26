package com.tron.wallet.common.utils;

import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
public class DrawableUtil {
    private final int LEFT = 0;
    private final int RIGHT = 2;
    private OnDrawableListener listener;
    private final View.OnClickListener mClickListener;
    private View.OnTouchListener mOnTouchListener;
    private TextView mTextView;

    public interface OnDrawableListener {
        void onLeft(View view, Drawable drawable);

        void onRight(View view, Drawable drawable);
    }

    public DrawableUtil(TextView textView, OnDrawableListener onDrawableListener, View.OnClickListener onClickListener) {
        View.OnTouchListener onTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action != 0) {
                    if (action == 1 && listener != null) {
                        Drawable drawable = mTextView.getCompoundDrawables()[0];
                        if (drawable != null && motionEvent.getRawX() <= mTextView.getLeft() + drawable.getBounds().width()) {
                            listener.onLeft(view, drawable);
                            return true;
                        }
                        Drawable drawable2 = mTextView.getCompoundDrawables()[2];
                        if (drawable2 != null && motionEvent.getRawX() >= mTextView.getRight() - drawable2.getBounds().width()) {
                            listener.onRight(view, drawable2);
                            return true;
                        } else if (mClickListener != null) {
                            mClickListener.onClick(view);
                            return true;
                        }
                    }
                    return false;
                }
                return true;
            }
        };
        this.mOnTouchListener = onTouchListener;
        this.mTextView = textView;
        textView.setOnTouchListener(onTouchListener);
        this.listener = onDrawableListener;
        this.mClickListener = onClickListener;
    }
}
