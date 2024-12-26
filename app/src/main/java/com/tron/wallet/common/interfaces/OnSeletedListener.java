package com.tron.wallet.common.interfaces;

import android.view.View;
import android.widget.PopupWindow;
public interface OnSeletedListener {

    public interface OnPopupSelectedListener extends OnSeletedListener {

        public final class -CC {
            public static void $default$onScamViewClickListener(OnPopupSelectedListener _this, PopupWindow popupWindow, View view) {
            }

            public static void $default$onViewClickListener(OnPopupSelectedListener _this, PopupWindow popupWindow, View view) {
            }
        }

        void onScamViewClickListener(PopupWindow popupWindow, View view);

        void onSelected(PopupWindow popupWindow, int i);

        void onViewClickListener(PopupWindow popupWindow, View view);
    }

    void onSeleted(int i);
}
