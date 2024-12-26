package com.tron.wallet.common.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.tron.wallet.business.tabmy.allhistory.TrxAllHistoryFragment;
import com.tron.wallet.common.adapter.base.BaseFragmentAdapter;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.List;
public class HistoryFragmentAdapter extends BaseFragmentAdapter {
    List<String> listTitle;
    List<TrxAllHistoryFragment> mList;

    public HistoryFragmentAdapter(FragmentActivity fragmentActivity, List<TrxAllHistoryFragment> list) {
        super(fragmentActivity);
        ArrayList arrayList = new ArrayList();
        this.listTitle = arrayList;
        this.mList = list;
        arrayList.add(StringTronUtil.getResString(R.string.transfer_all));
        this.listTitle.add(StringTronUtil.getResString(R.string.transfer_sent));
        this.listTitle.add(StringTronUtil.getResString(R.string.transfer_receive));
    }

    @Override
    public Fragment createFragment(int i) {
        return this.mList.get(i);
    }

    @Override
    public int getItemCount() {
        List<TrxAllHistoryFragment> list = this.mList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int i) {
        return this.listTitle.get(i);
    }
}
