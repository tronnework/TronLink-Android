package com.tron.wallet.common.components.pickerview.pickerview.adapter;

import com.tron.wallet.common.components.pickerview.pickerview.wheelview.adapter.WheelAdapter;
public class NumericWheelAdapter implements WheelAdapter {
    private int maxValue;
    private int minValue;

    @Override
    public int getItemsCount() {
        return (this.maxValue - this.minValue) + 1;
    }

    public NumericWheelAdapter(int i, int i2) {
        this.minValue = i;
        this.maxValue = i2;
    }

    @Override
    public Object getItem(int i) {
        if (i >= 0 && i < getItemsCount()) {
            return Integer.valueOf(this.minValue + i);
        }
        return 0;
    }

    @Override
    public int indexOf(Object obj) {
        try {
            return ((Integer) obj).intValue() - this.minValue;
        } catch (Exception unused) {
            return -1;
        }
    }
}
