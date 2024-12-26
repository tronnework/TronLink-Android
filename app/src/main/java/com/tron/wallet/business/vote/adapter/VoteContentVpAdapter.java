package com.tron.wallet.business.vote.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.tron.wallet.common.adapter.base.BaseFragmentAdapter;
import java.util.List;
public class VoteContentVpAdapter extends BaseFragmentAdapter {
    private List<Fragment> fragmentList;
    private List<String> stringList;

    public VoteContentVpAdapter(FragmentActivity fragmentActivity, List<Fragment> list, List<String> list2) {
        super(fragmentActivity);
        this.fragmentList = list;
        this.stringList = list2;
    }

    @Override
    public Fragment createFragment(int i) {
        return this.fragmentList.get(i);
    }

    @Override
    public int getItemCount() {
        return this.fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int i) {
        return this.stringList.get(i);
    }
}
