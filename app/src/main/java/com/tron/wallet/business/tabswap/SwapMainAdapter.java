package com.tron.wallet.business.tabswap;

import android.content.res.Resources;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.tron.wallet.common.adapter.base.BaseFragmentAdapter;
import java.util.List;
public class SwapMainAdapter extends BaseFragmentAdapter {
    private FragmentActivity fragmentActivity;
    private List<Fragment> fragments;
    private int[] pageTitles;

    public SwapMainAdapter(FragmentActivity fragmentActivity, List<Fragment> list, int[] iArr) {
        super(fragmentActivity);
        this.fragmentActivity = fragmentActivity;
        this.fragments = list;
        this.pageTitles = iArr;
    }

    @Override
    public int getItemCount() {
        return this.fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int i) {
        Resources resources = this.fragmentActivity.getResources();
        int[] iArr = this.pageTitles;
        return resources.getString(iArr[i % iArr.length]);
    }

    @Override
    public Fragment createFragment(int i) {
        return this.fragments.get(i);
    }
}
