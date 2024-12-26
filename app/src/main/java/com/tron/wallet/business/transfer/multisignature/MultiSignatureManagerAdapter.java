package com.tron.wallet.business.transfer.multisignature;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.adapter.base.BaseFragmentAdapter;
import java.util.ArrayList;
import java.util.List;
public class MultiSignatureManagerAdapter extends BaseFragmentAdapter {
    private final List<MsManagerFragment> fragments;
    private final List<Long> mFragmentHashCodes;

    @Override
    public CharSequence getPageTitle(int i) {
        return null;
    }

    public MultiSignatureManagerAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.fragments = new ArrayList();
        this.mFragmentHashCodes = new ArrayList();
    }

    @Override
    public Fragment createFragment(int i) {
        LogUtils.i("MultiSign", "getItem(position):" + i + " and fragments.get(position)'s name:" + this.fragments.get(i).toString());
        return this.fragments.get(i);
    }

    @Override
    public int getItemCount() {
        return this.fragments.size();
    }

    public void updateFragments(List<MsManagerFragment> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = this.fragments.size();
        this.fragments.clear();
        notifyItemRangeRemoved(0, size);
        this.fragments.addAll(list);
        this.mFragmentHashCodes.clear();
        for (int i = 0; i < this.fragments.size(); i++) {
            this.mFragmentHashCodes.add(Long.valueOf(this.fragments.get(i).hashCode()));
        }
        notifyItemRangeInserted(0, this.fragments.size());
    }

    @Override
    public long getItemId(int i) {
        return this.fragments.get(i).hashCode();
    }

    @Override
    public boolean containsItem(long j) {
        return this.mFragmentHashCodes.contains(Long.valueOf(j));
    }
}
