package com.tron.wallet.common.adapter.base;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.tron.tron_base.frame.utils.LogUtils;
public class SimpleViewPagerAdapter extends BaseFragmentAdapter {
    private final Context context;
    private final Fragment[] fragments;
    private final int[] titleResource;

    public SimpleViewPagerAdapter(FragmentActivity fragmentActivity, Fragment[] fragmentArr, int[] iArr) {
        super(fragmentActivity);
        this.fragments = fragmentArr;
        this.titleResource = iArr;
        this.context = fragmentActivity.getBaseContext();
        if (fragmentArr.length != iArr.length) {
            LogUtils.w("Title count doesn't match pagers' .Please check");
        }
    }

    @Override
    public Fragment createFragment(int i) {
        Fragment[] fragmentArr = this.fragments;
        return fragmentArr[i % fragmentArr.length];
    }

    @Override
    public int getItemCount() {
        return Math.min(this.fragments.length, this.titleResource.length);
    }

    @Override
    public CharSequence getPageTitle(int i) {
        Context context = this.context;
        int[] iArr = this.titleResource;
        return context.getString(iArr[i % iArr.length]);
    }
}
