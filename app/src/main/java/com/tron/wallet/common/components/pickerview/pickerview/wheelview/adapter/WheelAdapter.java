package com.tron.wallet.common.components.pickerview.pickerview.wheelview.adapter;
public interface WheelAdapter<T> {
    T getItem(int i);

    int getItemsCount();

    int indexOf(T t);
}
