package com.tron.wallet.business.tabmy.dealsign.signwait;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.common.adapter.DealSignAdapter;
import com.tron.wallet.common.bean.DealSignOutput;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.databinding.FgSignWaitBinding;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.List;
public class SignWaitFragment extends BaseFragment<EmptyPresenter, EmptyModel> {
    public static final String STATE_KEY = "STATE_KEY";
    private FgSignWaitBinding binding;
    private DealSignAdapter dealSignAdapter;
    private LinearLayoutManager layoutManager;
    View noNetContainer;
    NoNetView noNetView;
    RecyclerView rcList;
    private RxManager rxManager;
    private long showingTime = 0;
    private boolean socketState;
    private int state;
    private List<DealSignOutput.DataBeanX.DataBean> waitList;
    private String walletName;

    @Override
    protected void processData() {
        this.rxManager = new RxManager();
        LogUtils.d("alex", "fragment process data");
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.state = arguments.getInt("STATE_KEY");
            this.socketState = arguments.getBoolean(TronConfig.socket_state, false);
            this.walletName = arguments.getString(TronConfig.deal_wallet_name);
        }
        this.waitList = new ArrayList();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext, 1, false);
        this.layoutManager = linearLayoutManager;
        this.rcList.setLayoutManager(linearLayoutManager);
        DealSignAdapter dealSignAdapter = new DealSignAdapter(this.mContext, this.waitList, this.state, this.walletName);
        this.dealSignAdapter = dealSignAdapter;
        dealSignAdapter.setEnableLoadMore(false);
        this.rcList.setAdapter(this.dealSignAdapter);
        this.noNetView.setOnReloadClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestReconnect();
            }
        });
        LogUtils.d("alex", "the socket state is " + this.socketState);
    }

    public void requestReconnect() {
        RxManager rxManager = this.rxManager;
        if (rxManager != null) {
            rxManager.post(Event.RequestMutilSocketReConnect, Integer.valueOf(this.state));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        showLoadingPage(this.mContext.getString(R.string.loading));
        requestReconnect();
        this.showingTime = System.currentTimeMillis();
    }

    @Override
    public void showErrorPage() {
        RecyclerView recyclerView = this.rcList;
        if (recyclerView != null) {
            recyclerView.setVisibility(View.GONE);
            this.noNetView.updateLoadingState(true);
            this.noNetContainer.setVisibility(View.VISIBLE);
        }
    }

    public void refreshData(List<DealSignOutput.DataBeanX.DataBean> list) {
        LogUtils.d("alex", "sign waiting refresh data");
        if (System.currentTimeMillis() - this.showingTime > 100) {
            LogUtils.d("alex", "sign waiting refresh data  > 100");
            dismissLoadingPage();
            dismissLoadingDialog();
            this.waitList = list;
            if (list == null || list.size() == 0) {
                showNoDatasPage();
                return;
            }
            DealSignAdapter dealSignAdapter = this.dealSignAdapter;
            if (dealSignAdapter == null) {
                DealSignAdapter dealSignAdapter2 = new DealSignAdapter(this.mContext, this.waitList, this.state, this.walletName);
                this.dealSignAdapter = dealSignAdapter2;
                RecyclerView recyclerView = this.rcList;
                if (recyclerView != null) {
                    recyclerView.setAdapter(dealSignAdapter2);
                }
            } else {
                dealSignAdapter.notifyData1(this.waitList);
            }
            RecyclerView recyclerView2 = this.rcList;
            if (recyclerView2 == null || recyclerView2.getVisibility() == 0) {
                return;
            }
            this.noNetContainer.setVisibility(View.GONE);
            this.rcList.setVisibility(View.VISIBLE);
        }
    }

    public static SignWaitFragment newInstance(int i, boolean z, String str) {
        SignWaitFragment signWaitFragment = new SignWaitFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("STATE_KEY", i);
        bundle.putBoolean(TronConfig.socket_state, z);
        bundle.putString(TronConfig.deal_wallet_name, str);
        signWaitFragment.setArguments(bundle);
        return signWaitFragment;
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgSignWaitBinding inflate = FgSignWaitBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        LinearLayout root = inflate.getRoot();
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
        this.noNetContainer = this.binding.noNet;
        this.noNetView = this.binding.noNetView;
    }
}
