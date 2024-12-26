package com.tron.wallet.common.adapter;

import androidx.fragment.app.FragmentActivity;
import com.tron.wallet.business.confirm.sign.SignContentFragment;
import com.tron.wallet.common.adapter.base.BaseFragmentAdapter;
import java.util.List;
public class SignTransactionPagerAdapter extends BaseFragmentAdapter {
    List<SignContentFragment> mList;

    @Override
    public CharSequence getPageTitle(int i) {
        return null;
    }

    public SignTransactionPagerAdapter(FragmentActivity fragmentActivity, List<SignContentFragment> list) {
        super(fragmentActivity);
        this.mList = list;
    }

    @Override
    public SignContentFragment createFragment(int i) {
        return this.mList.get(i);
    }

    @Override
    public int getItemCount() {
        List<SignContentFragment> list = this.mList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }
}
