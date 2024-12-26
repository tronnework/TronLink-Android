package com.tron.wallet.business.security.tokencheck.view;

import android.widget.TextView;
import com.tron.wallet.common.components.mnemonicflowlayout.FlowLayout;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import java.util.List;
public abstract class TokenTagAdapter {
    private OnDataChangedListener mOnDataChangedListener;
    private List<TokenCheckTag> mTagData;

    public interface OnDataChangedListener {
        void onChanged();
    }

    public List<TokenCheckTag> getDatas() {
        return this.mTagData;
    }

    public abstract TextView getView(FlowLayout flowLayout, int i, TokenCheckTag tokenCheckTag);

    public void setOnDataChangedListener(OnDataChangedListener onDataChangedListener) {
        this.mOnDataChangedListener = onDataChangedListener;
    }

    public TokenTagAdapter(List<TokenCheckTag> list, NoDoubleClickListener2 noDoubleClickListener2) {
        this.mTagData = list;
    }

    public void remove(int i) {
        if (i > this.mTagData.size() - 1) {
            return;
        }
        this.mTagData.remove(i);
    }

    public void addAll(List<TokenCheckTag> list) {
        this.mTagData.addAll(list);
        notifyDataChanged();
    }

    public int getCount() {
        List<TokenCheckTag> list = this.mTagData;
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

    public TokenCheckTag getItem(int i) {
        if (i > this.mTagData.size() - 1) {
            return null;
        }
        return this.mTagData.get(i);
    }
}
