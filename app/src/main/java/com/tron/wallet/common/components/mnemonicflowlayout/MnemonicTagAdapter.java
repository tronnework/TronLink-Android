package com.tron.wallet.common.components.mnemonicflowlayout;

import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.tron.common.bip39.BIP39;
public abstract class MnemonicTagAdapter {
    private ArrayList<String> englishLists;
    private OnDataChangedListener mOnDataChangedListener;
    private List<String> mTagDatas;
    private List<String> mTagWrongDatas;

    public interface OnDataChangedListener {
        void addItemView();

        void onChanged();

        void removeItemView(int i);
    }

    public List<String> getDatas() {
        return this.mTagDatas;
    }

    public abstract View getInputView(FlowLayout flowLayout);

    public abstract TextView getView(FlowLayout flowLayout, int i, String str);

    public List<String> getmTagWrongDatas() {
        return this.mTagWrongDatas;
    }

    public void setOnDataChangedListener(OnDataChangedListener onDataChangedListener) {
        this.mOnDataChangedListener = onDataChangedListener;
    }

    public MnemonicTagAdapter(List<String> list) {
        this.mTagDatas = list;
    }

    public void remove(int i) {
        if (i > this.mTagDatas.size() - 1) {
            return;
        }
        this.mTagDatas.remove(i);
    }

    public void removeWrongData(String str) {
        this.mTagWrongDatas.remove(str);
    }

    public void add(String str) {
        if (!this.englishLists.contains(str) || this.mTagDatas.contains(str)) {
            this.mTagWrongDatas.add(str);
        }
        this.mTagDatas.add(str);
    }

    public void clearWrongDatas() {
        List<String> list = this.mTagWrongDatas;
        if (list != null) {
            list.clear();
        } else {
            this.mTagWrongDatas = new ArrayList();
        }
    }

    public void addAll(List<String> list) {
        this.mTagDatas.addAll(list);
        notifyDataChanged();
    }

    public MnemonicTagAdapter(String[] strArr) {
        this.mTagDatas = new ArrayList(Arrays.asList(strArr));
        this.mTagWrongDatas = new ArrayList();
        this.englishLists = new ArrayList<>(Arrays.asList(BIP39.english));
    }

    public int getCount() {
        List<String> list = this.mTagDatas;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void resetData(List<String> list) {
        this.mTagDatas = list;
        clearWrongDatas();
        notifyDataChanged();
    }

    public void notifyDataChanged() {
        OnDataChangedListener onDataChangedListener = this.mOnDataChangedListener;
        if (onDataChangedListener != null) {
            onDataChangedListener.onChanged();
        }
    }

    public String getItem(int i) {
        if (i > this.mTagDatas.size() - 1) {
            return null;
        }
        return this.mTagDatas.get(i);
    }

    public String formatDataString() {
        return getDatas().toString().replaceAll(",", "").replace("[", "").replace("]", "");
    }
}
