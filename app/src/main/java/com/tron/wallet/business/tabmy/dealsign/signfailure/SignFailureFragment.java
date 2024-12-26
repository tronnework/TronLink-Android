package com.tron.wallet.business.tabmy.dealsign.signfailure;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.tabmy.dealsign.signfailure.SignFailureContract;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.components.ptr.PtrFrameLayout;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayout;
import com.tron.wallet.databinding.FgSignFailureBinding;
public class SignFailureFragment extends BaseFragment<SignFailurePresenter, SignFailureModel> implements SignFailureContract.View {
    public static final String STATE_KEY = "STATE_KEY";
    public static final String TAG = "SignFailureFragment";
    public static final String WALLET_NAME = "WALLET_NAME";
    private String address;
    private FgSignFailureBinding binding;
    private String mWalletName;
    View noNetContainer;
    NoNetView noNetView;
    PtrHTFrameLayout rcFrame;
    RecyclerView rcList;
    private int state;

    @Override
    public String getAddress() {
        return this.address;
    }

    @Override
    public View getNoNetView() {
        return this.noNetContainer;
    }

    @Override
    public PtrFrameLayout getRcFrame() {
        return this.rcFrame;
    }

    @Override
    public RecyclerView getRcList() {
        return this.rcList;
    }

    @Override
    public int getState() {
        return this.state;
    }

    @Override
    public String getWalletName() {
        return this.mWalletName;
    }

    @Override
    protected void processData() {
        LogUtils.i(TAG, "processData");
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.state = arguments.getInt("STATE_KEY");
            this.mWalletName = arguments.getString(WALLET_NAME);
        }
        ((SignFailurePresenter) this.mPresenter).addSome();
        ((SignFailurePresenter) this.mPresenter).getData(this.state);
        this.noNetView.setOnReloadClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPresenter != 0) {
                    ((SignFailurePresenter) mPresenter).getData(state);
                }
            }
        });
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgSignFailureBinding inflate = FgSignFailureBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        setType(2);
        mappingId();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId() {
        this.rcList = this.binding.rcList;
        this.rcFrame = this.binding.rcFrame;
        this.noNetContainer = this.binding.noNet;
        this.noNetView = this.binding.noNetView;
    }

    public static SignFailureFragment newInstance(int i, String str) {
        SignFailureFragment signFailureFragment = new SignFailureFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("STATE_KEY", i);
        bundle.putString(WALLET_NAME, str);
        signFailureFragment.setArguments(bundle);
        return signFailureFragment;
    }

    @Override
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        LogUtils.i(TAG, "onHiddenChanged" + z);
        if (z) {
            return;
        }
        ((SignFailurePresenter) this.mPresenter).getData(this.state);
    }

    @Override
    public void onReLoadButtonClick() {
        super.onReLoadButtonClick();
        ((SignFailurePresenter) this.mPresenter).getData(this.state);
    }

    @Override
    public void showErrorPage() {
        PtrHTFrameLayout ptrHTFrameLayout = this.rcFrame;
        if (ptrHTFrameLayout != null) {
            ptrHTFrameLayout.setVisibility(View.GONE);
            this.noNetView.updateLoadingState(true);
            this.noNetContainer.setVisibility(View.VISIBLE);
        }
    }
}
