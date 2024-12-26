package com.tron.wallet.common.components.pickerview.pickerview.adapter;

import com.tron.wallet.common.components.pickerview.pickerview.wheelview.adapter.WheelAdapter;
import java.util.List;
public class ArrayWheelAdapter<T> implements WheelAdapter {
    private List<T> items;

    public ArrayWheelAdapter(List<T> list) {
        this.items = list;
    }

    @Override
    public Object getItem(int i) {
        return (i < 0 || i >= this.items.size()) ? "" : this.items.get(i);
    }

    @Override
    public int getItemsCount() {
        return this.items.size();
    }

    @Override
    public int indexOf(Object obj) {
        return this.items.indexOf(obj);
    }
}
