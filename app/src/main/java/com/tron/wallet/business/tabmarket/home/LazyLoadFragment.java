package com.tron.wallet.business.tabmarket.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
public abstract class LazyLoadFragment<T extends BasePresenter, E extends BaseModel> extends BaseFragment<T, E> {
    protected boolean isFirstLoad = true;
    protected boolean isVisible;
    private View mRootView;

    protected void firstLoad() {
    }

    public void onInvisible() {
        this.isVisible = false;
    }

    protected void refreshLoad() {
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = super.onCreateView(layoutInflater, viewGroup, bundle);
        }
        return this.mRootView;
    }

    public void onVisible() {
        this.isVisible = true;
        if (!this.isFirstLoad) {
            refreshLoad();
            return;
        }
        firstLoad();
        this.isFirstLoad = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        onVisible();
    }

    @Override
    public void onPause() {
        super.onPause();
        onInvisible();
    }
}
