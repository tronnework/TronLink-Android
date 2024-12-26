package com.tron.wallet.common.adapter.user;

import androidx.fragment.app.FragmentActivity;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.wallet.common.adapter.base.BaseFragmentAdapter;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.List;
public class ImportFragmentAdapter extends BaseFragmentAdapter {
    List<String> listTitle;
    List<BaseFragment> mList;

    public ImportFragmentAdapter(FragmentActivity fragmentActivity, List<BaseFragment> list) {
        super(fragmentActivity);
        ArrayList arrayList = new ArrayList();
        this.listTitle = arrayList;
        this.mList = list;
        arrayList.add(StringTronUtil.getResString(R.string.mnemonic_import));
        this.listTitle.add(StringTronUtil.getResString(R.string.privatekey_import));
        this.listTitle.add(StringTronUtil.getResString(R.string.keystore_import));
        this.listTitle.add(StringTronUtil.getResString(R.string.observe_import1));
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
