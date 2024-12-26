package com.tron.wallet.common.components.flowlayout;

import android.view.View;
import com.tron.tron_base.frame.utils.LogUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public abstract class TagAdapter<T> {
    @Deprecated
    private HashSet<Integer> mCheckedPosList = new HashSet<>();
    private OnDataChangedListener mOnDataChangedListener;
    private List<T> mTagDatas;

    public interface OnDataChangedListener {
        void onChanged();
    }

    @Deprecated
    public HashSet<Integer> getPreCheckedList() {
        return this.mCheckedPosList;
    }

    public abstract View getView(FlowLayout flowLayout, int i, T t);

    public void setOnDataChangedListener(OnDataChangedListener onDataChangedListener) {
        this.mOnDataChangedListener = onDataChangedListener;
    }

    public boolean setSelected(int i, T t) {
        return false;
    }

    public TagAdapter(List<T> list) {
        this.mTagDatas = list;
    }

    @Deprecated
    public TagAdapter(T[] tArr) {
        this.mTagDatas = new ArrayList(Arrays.asList(tArr));
    }

    @Deprecated
    public void setSelectedList(int... iArr) {
        HashSet hashSet = new HashSet();
        for (int i : iArr) {
            hashSet.add(Integer.valueOf(i));
        }
        setSelectedList(hashSet);
    }

    @Deprecated
    public void setSelectedList(Set<Integer> set) {
        this.mCheckedPosList.clear();
        if (set != null) {
            this.mCheckedPosList.addAll(set);
        }
        notifyDataChanged();
    }

    public int getCount() {
        List<T> list = this.mTagDatas;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void notifyDataChanged() {
        OnDataChangedListener onDataChangedListener = this.mOnDataChangedListener;
        if (onDataChangedListener != null) {
            onDataChangedListener.onChanged();
        }
    }

    public T getItem(int i) {
        return this.mTagDatas.get(i);
    }

    public void onSelected(int i, View view) {
        LogUtils.d("zhy", "onSelected " + i);
    }

    public void unSelected(int i, View view) {
        LogUtils.d("zhy", "unSelected " + i);
    }
}
