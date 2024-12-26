package com.tron.wallet.common.utils;

import android.view.View;
public interface Action<T extends View> {
    void apply(T t, int i);
}
