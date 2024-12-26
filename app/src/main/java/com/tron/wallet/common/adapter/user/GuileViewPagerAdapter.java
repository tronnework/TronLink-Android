package com.tron.wallet.common.adapter.user;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.wallet.common.adapter.base.BaseFragmentAdapter;
import java.util.List;
public class GuileViewPagerAdapter<T extends BaseFragment> extends BaseFragmentAdapter {
    private List<T> dataList;

    @Override
    public CharSequence getPageTitle(int i) {
        return null;
    }

    public GuileViewPagerAdapter(FragmentActivity fragmentActivity, List<T> list) {
        super(fragmentActivity);
        this.dataList = list;
    }

    @Override
    public Fragment createFragment(int i) {
        return this.dataList.get(i);
    }

    @Override
    public int getItemCount() {
        return this.dataList.size();
    }
}
