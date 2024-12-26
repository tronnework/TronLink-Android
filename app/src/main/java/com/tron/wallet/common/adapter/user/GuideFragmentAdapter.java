package com.tron.wallet.common.adapter.user;

import androidx.fragment.app.FragmentActivity;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.wallet.common.adapter.base.BaseFragmentAdapter;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.List;
public class GuideFragmentAdapter extends BaseFragmentAdapter {
    List<String> listTitle;
    List<BaseFragment> mList;

    public GuideFragmentAdapter(FragmentActivity fragmentActivity, List<BaseFragment> list) {
        super(fragmentActivity);
        ArrayList arrayList = new ArrayList();
        this.listTitle = arrayList;
        this.mList = list;
        arrayList.add(StringTronUtil.getResString(R.string.transfer_all));
        this.listTitle.add(StringTronUtil.getResString(R.string.faq));
        this.listTitle.add(StringTronUtil.getResString(R.string.user_guide2));
    }

    @Override
    public BaseFragment createFragment(int i) {
        return this.mList.get(i);
    }

    @Override
    public int getItemCount() {
        List<BaseFragment> list = this.mList;
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
