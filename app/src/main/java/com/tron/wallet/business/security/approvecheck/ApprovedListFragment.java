package com.tron.wallet.business.security.approvecheck;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.StringTronUtil;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.parambuilder.utils.ParamBuildUtils;
import com.tron.wallet.business.security.approvecheck.ApproveListContract;
import com.tron.wallet.business.security.approvecheck.adapter.ApproveListAdapter;
import com.tron.wallet.business.security.approvecheck.adapter.ApproveProjectListAdapter;
import com.tron.wallet.business.security.approvecheck.bean.ApproveListDatabeanOutput;
import com.tron.wallet.business.tabmarket.home.LazyLoadFragment;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.ToastUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.FragmentApproveCheckListBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import org.tron.api.GrpcAPI;
import org.tron.walletserver.Wallet;
public class ApprovedListFragment extends LazyLoadFragment<ApproveListPresenter, ApproveListModel> implements ApproveListContract.View {
    public static final String APPROVE_ADDRESS_TYPE_ACCOUNT = "account";
    public static final String APPROVE_ADDRESS_TYPE_PROJECT = "project";
    public static final String DEFAULT_TYPE = "token";
    public static final int REQUESTCODE_CANCEL_APPROVE = 257;
    public static final String TYPE_PROJECT = "project";
    public static final String TYPE_TOKEN = "token";
    private static final String[][] blackHoleAddress = {new String[]{"TLsV52sRDL79HXGGm9yzwKibb6BeruhUzy", "T9yD14Nj9j7xAB4dbGeiX9h8unkKHxuWwb"}, new String[]{"TNJ1YHzREsf7AoKhstf627ZrbtCzTj7f55", "TDPJULRzVtzVjnBmZvfaTcTNQ2tsVi6XxQ"}};
    private ApproveListAdapter adapter;
    private ApproveProjectListAdapter adapterProject;
    private ApproveListDatabeanOutput approveListDatabeanOutput;
    private FragmentApproveCheckListBinding binding;
    ImageView ivPlaceHolder;
    View liViewMore;
    NoNetView noDataView;
    NoNetView noNetView;
    RecyclerView recyclerView;
    private String type;
    private Wallet wallet;

    public static ApprovedListFragment getInstance(String str) {
        ApprovedListFragment approvedListFragment = new ApprovedListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TronConfig.APPROVED_SORT_TYPE, str);
        approvedListFragment.setArguments(bundle);
        return approvedListFragment;
    }

    @Override
    protected void processData() {
        this.wallet = WalletUtils.getSelectedWallet();
        Bundle arguments = getArguments();
        this.type = "token";
        if (arguments != null) {
            this.type = arguments.getString(TronConfig.APPROVED_SORT_TYPE);
        }
        if ("token".equals(this.type)) {
            this.adapter = new ApproveListAdapter(getIContext(), this.type, new NoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.SecurityApprovePage.SECURITY_APPROVE_CLICK_SHOW_ALL_APPROVED);
                    if (wallet != null) {
                        UIUtils.toAccountApproveAllDetailProtocol(getIContext(), wallet.getAddress());
                    }
                }
            });
        } else {
            this.adapterProject = new ApproveProjectListAdapter(getIContext(), this.type, new NoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.SecurityApprovePage.SECURITY_APPROVE_CLICK_SHOW_ALL_APPROVED);
                    if (wallet != null) {
                        UIUtils.toAccountApproveAllDetailProtocol(getIContext(), wallet.getAddress());
                    }
                }
            });
        }
        CancelApproveClickListener cancelApproveClickListener = new CancelApproveClickListener() {
            @Override
            public void onCancelApproveClick(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
                AnalyticsHelper.logEvent(AnalyticsHelper.SecurityApprovePage.SECURITY_APPROVE_CLICK_CANCEL_AUTH);
                if ("2".equals(str6)) {
                    ((ApproveListPresenter) mPresenter).approve(str, str2, str5, str3, wallet.getAddress(), str6, str7, str8, str9, str4, str10);
                } else if (AnalyticsHelper.SelectSendAddress.CLICK_ADDRESS_BOOK.equals(str6)) {
                    ((ApproveListPresenter) mPresenter).approve(str, getCurrentBlackHoleAddress()[1], str5, str3, wallet.getAddress(), str6, str7, str8, str9, str4, str10);
                } else {
                    ((ApproveListPresenter) mPresenter).approve(StringTronUtil.isEmpty(str) ? "0" : str, str2, str5, str3, wallet.getAddress(), str6, str7, str8, str9, str4, str10);
                }
            }
        };
        if ("token".equals(this.type)) {
            this.adapter.setItemCancelApproveClickListener(cancelApproveClickListener);
        } else {
            this.adapterProject.setItemCancelApproveClickListener(cancelApproveClickListener);
        }
        this.recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getIContext(), 1, false));
        this.recyclerView.setNestedScrollingEnabled(true);
        if ("token".equals(this.type)) {
            this.recyclerView.setAdapter(this.adapter);
        } else {
            this.recyclerView.setAdapter(this.adapterProject);
        }
        this.noNetView.setOnReloadClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (TronConfig.hasNet) {
                    ((ApproveCheckActivity) getActivity()).requestList(type);
                    showLoadingView();
                    return;
                }
                showNoNetView();
            }
        });
        ApproveListDatabeanOutput approveListDatabeanOutput = this.approveListDatabeanOutput;
        if (approveListDatabeanOutput != null) {
            updateApproveList(approveListDatabeanOutput);
        }
    }

    public void showLoadingView() {
        this.recyclerView.setVisibility(View.GONE);
        this.ivPlaceHolder.setVisibility(View.VISIBLE);
        this.noNetView.setVisibility(View.GONE);
        this.noDataView.setVisibility(View.GONE);
    }

    private void showContentView() {
        this.recyclerView.setVisibility(View.VISIBLE);
        this.ivPlaceHolder.setVisibility(View.GONE);
        this.noNetView.setVisibility(View.GONE);
        this.noDataView.setVisibility(View.GONE);
    }

    private void showEmptyView() {
        this.ivPlaceHolder.setVisibility(View.GONE);
        this.noNetView.setVisibility(View.GONE);
        this.noDataView.setVisibility(View.VISIBLE);
    }

    public void showNoNetView() {
        this.recyclerView.setVisibility(View.GONE);
        this.ivPlaceHolder.setVisibility(View.GONE);
        this.noNetView.setVisibility(View.VISIBLE);
        this.noNetView.setReloadMarginTop(20.0f);
        this.noDataView.setVisibility(View.GONE);
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentApproveCheckListBinding inflate = FragmentApproveCheckListBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        setClick();
        return root;
    }

    private void setClick() {
        this.liViewMore.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (wallet != null) {
                    UIUtils.toAccountApproveAllDetailProtocol(getIContext(), wallet.getAddress());
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId() {
        this.recyclerView = this.binding.recyclerView;
        this.ivPlaceHolder = this.binding.ivPlaceHolder;
        this.noDataView = this.binding.noDataView;
        this.noNetView = this.binding.noNetView;
        this.liViewMore = this.binding.liViewMore;
    }

    public void updateList(ApproveListDatabeanOutput approveListDatabeanOutput) {
        if (this.approveListDatabeanOutput == null) {
            this.approveListDatabeanOutput = approveListDatabeanOutput;
            return;
        }
        this.approveListDatabeanOutput = approveListDatabeanOutput;
        updateApproveList(approveListDatabeanOutput);
    }

    public void updateApproveList(ApproveListDatabeanOutput approveListDatabeanOutput) {
        if (approveListDatabeanOutput != null) {
            if ("token".equals(this.type)) {
                if (approveListDatabeanOutput.getData() != null && approveListDatabeanOutput.getData().getTokens().size() > 0) {
                    this.adapter.updateData(approveListDatabeanOutput.getData().getTokens());
                    showContentView();
                    this.liViewMore.setVisibility(View.VISIBLE);
                    return;
                }
                this.adapter.updateData(new ArrayList());
                showContentView();
                this.liViewMore.setVisibility(View.GONE);
            } else if ("project".equals(this.type)) {
                if (approveListDatabeanOutput.getData() != null && approveListDatabeanOutput.getData().getProjects().size() > 0) {
                    this.adapterProject.updateData(approveListDatabeanOutput.getData().getProjects());
                    showContentView();
                    this.liViewMore.setVisibility(View.VISIBLE);
                    return;
                }
                this.adapterProject.updateData(new ArrayList());
                showContentView();
                this.liViewMore.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        dismissLoadingDialog();
    }

    public String[] getCurrentBlackHoleAddress() {
        return blackHoleAddress[0];
    }

    @Override
    public void startCancelConfirmApprove(GrpcAPI.TransactionExtention transactionExtention, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        try {
            ConfirmTransactionNewActivity.startForResult(getActivity(), ParamBuildUtils.getCancelApproveParamBuilder(transactionExtention.getTransaction(), this.wallet.getAddress(), str2, str3, str5, str6, str7, str4, str8, str9, str10, str11), 257, this.wallet.getCreateType() == 7);
        } catch (Exception e) {
            LogUtils.e(e);
            ToastUtil.getInstance().showToast((Activity) getActivity(), getString(R.string.swap_approve_failed));
        }
    }
}
