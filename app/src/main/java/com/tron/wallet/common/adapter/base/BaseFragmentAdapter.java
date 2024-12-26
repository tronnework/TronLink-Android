package com.tron.wallet.common.adapter.base;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
public abstract class BaseFragmentAdapter extends FragmentStateAdapter {
    @Override
    public abstract Fragment createFragment(int i);

    @Override
    public abstract int getItemCount();

    public abstract CharSequence getPageTitle(int i);

    public BaseFragmentAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public BaseFragmentAdapter(Fragment fragment) {
        super(fragment);
    }
}
