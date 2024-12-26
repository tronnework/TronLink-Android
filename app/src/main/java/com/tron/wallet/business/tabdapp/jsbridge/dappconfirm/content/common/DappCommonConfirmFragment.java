package com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentTransaction;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.bean.nft.NftItemInfo;
import com.tron.wallet.business.confirm.fg.BaseConfirmFragment;
import com.tron.wallet.business.confirm.fg.component.ArgumentKeys;
import com.tron.wallet.business.confirm.fg.component.GlobalTitleHeaderView;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.DappDetailParam;
import com.tron.wallet.business.confirm.parambuilder.bean.DappMetadataParam;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.DappConfirmNewActivity;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.common.DappCommonConfirmContract;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.tab.TransactionMetadataFragment;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.xtablayout.XTabLayout;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.databinding.FgDappConfirmSystemContractBinding;
import com.tronlinkpro.wallet.R;
import java.text.NumberFormat;
import java.util.Locale;
import org.tron.protos.Protocol;
public class DappCommonConfirmFragment extends BaseConfirmFragment<EmptyPresenter, EmptyModel> implements DappCommonConfirmContract.View, XTabLayout.OnTabSelectedListener {
    RelativeLayout approveLayout;
    private FgDappConfirmSystemContractBinding binding;
    String contractTypeString;
    DappCommonTransactionDetailFragment detailFragment;
    private DappDetailParam detailParam;
    GlobalTitleHeaderView headerView;
    SimpleDraweeView ivApproveIcon;
    LinearLayout ivClose;
    SimpleDraweeView ivIcon;
    ImageView ivIconTag;
    SimpleDraweeView ivSubTitleIcon;
    LinearLayout llRoot;
    private DappMetadataParam metaParam;
    TransactionMetadataFragment metadataFragment;
    private NftItemInfo nftInfo;
    private NumberFormat numberFormat;
    private int resTitleId;
    XTabLayout tabLayout;
    TokenBean tokenBean;
    TextView tvApproveInfo;
    TextView tvApproveTokenName;

    @Override
    public void onTabReselected(XTabLayout.Tab tab) {
    }

    @Override
    public void onTabUnselected(XTabLayout.Tab tab) {
    }

    public static DappCommonConfirmFragment getInstance(BaseParam baseParam, BaseParam baseParam2) {
        DappCommonConfirmFragment dappCommonConfirmFragment = new DappCommonConfirmFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ArgumentKeys.KEY_PARAM_DETAIL, baseParam);
        bundle.putParcelable(ArgumentKeys.KEY_PARAM_META, baseParam2);
        bundle.putParcelable(ArgumentKeys.KEY_PARAM, baseParam2);
        dappCommonConfirmFragment.setArguments(bundle);
        return dappCommonConfirmFragment;
    }

    @Override
    public void processData() {
        super.processData();
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.detailParam = (DappDetailParam) arguments.getParcelable(ArgumentKeys.KEY_PARAM_DETAIL);
            this.metaParam = (DappMetadataParam) arguments.getParcelable(ArgumentKeys.KEY_PARAM_META);
        }
        XTabLayout xTabLayout = this.tabLayout;
        xTabLayout.addTab(xTabLayout.newTab().setText(R.string.dapp_detail));
        XTabLayout xTabLayout2 = this.tabLayout;
        xTabLayout2.addTab(xTabLayout2.newTab().setText(R.string.dapp_metadata));
        this.tabLayout.setOnTabSelectedListener(this);
        this.metadataFragment = TransactionMetadataFragment.getInstance(this.metaParam);
        this.detailFragment = DappCommonTransactionDetailFragment.getInstance(this.detailParam);
        FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
        if (this.metadataFragment != null) {
            beginTransaction.add(R.id.content, this.detailFragment, "0");
            beginTransaction.add(R.id.content, this.metadataFragment, "1");
            beginTransaction.show(this.detailFragment).hide(this.metadataFragment);
            beginTransaction.disallowAddToBackStack();
            beginTransaction.commit();
        }
        try {
            bindDataToUI();
            addAccountCallback(this.headerView, this.detailFragment, ((DappConfirmNewActivity) getActivity()).getConfirmBtn());
            ensureAccount();
        } catch (Exception e) {
            LogUtils.e(e);
        }
        this.llRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    private void bindDataToUI() {
        this.detailParam.setTitle(R.string.confirmtransaction, R.string.confirmtransaction);
        this.tokenBean = this.detailParam.getTokenBean();
        this.contractTypeString = this.detailParam.getContractTypeString();
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.numberFormat = numberInstance;
        numberInstance.setMaximumFractionDigits(7);
        this.detailParam.addIconResource(getSystemContractHeadIcon(this.contractTypeString));
        DappDetailParam dappDetailParam = this.detailParam;
        dappDetailParam.addInfoTitle(dappDetailParam.getTitleId(), this.contractTypeString);
        this.headerView.bindData(this.detailParam);
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgDappConfirmSystemContractBinding inflate = FgDappConfirmSystemContractBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        setClick();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId() {
        this.llRoot = this.binding.root;
        this.ivClose = (LinearLayout) this.binding.getRoot().findViewById(R.id.iv_close);
        this.headerView = this.binding.headerView;
        this.tabLayout = this.binding.tablayout;
        this.approveLayout = (RelativeLayout) this.binding.getRoot().findViewById(R.id.approve_layout);
        this.tvApproveInfo = (TextView) this.binding.getRoot().findViewById(R.id.approve_info);
        this.ivApproveIcon = (SimpleDraweeView) this.binding.getRoot().findViewById(R.id.iv_approve_icon);
        this.tvApproveTokenName = (TextView) this.binding.getRoot().findViewById(R.id.dapp_approve_token_name);
        this.ivIcon = (SimpleDraweeView) this.binding.getRoot().findViewById(R.id.iv_icon);
        this.ivIconTag = (ImageView) this.binding.getRoot().findViewById(R.id.iv_icon_tag);
        this.ivSubTitleIcon = (SimpleDraweeView) this.binding.getRoot().findViewById(R.id.iv_sub_title_icon);
    }

    @Override
    public void onTabSelected(XTabLayout.Tab tab) {
        TransactionMetadataFragment transactionMetadataFragment;
        FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
        LogUtils.e("onTabSelected", String.valueOf(tab.getPosition()));
        int position = tab.getPosition();
        if (position == 0) {
            DappCommonTransactionDetailFragment dappCommonTransactionDetailFragment = this.detailFragment;
            if (dappCommonTransactionDetailFragment != null) {
                beginTransaction.show(dappCommonTransactionDetailFragment).hide(this.metadataFragment);
                beginTransaction.disallowAddToBackStack();
                beginTransaction.commitAllowingStateLoss();
            }
        } else if (position == 1 && (transactionMetadataFragment = this.metadataFragment) != null) {
            beginTransaction.show(transactionMetadataFragment).hide(this.detailFragment);
            beginTransaction.disallowAddToBackStack();
            beginTransaction.commitAllowingStateLoss();
            try {
                AnalyticsHelper.logEventFormat(AnalyticsHelper.TransactionConfirmPopup.CLICK_CONFIRM_PAGE_META_TAB, Integer.valueOf(this.detailParam.getContractType() == Protocol.Transaction.Contract.ContractType.TriggerSmartContract.getNumber() ? 2 : 1));
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
    }

    public void setClick() {
        this.ivClose.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                try {
                    AnalyticsHelper.logEventFormat(AnalyticsHelper.TransactionConfirmPopup.CLICK_CONFIRM_PAGE_BACK, Integer.valueOf(detailParam.getContractType() == Protocol.Transaction.Contract.ContractType.TriggerSmartContract.getNumber() ? 2 : 1));
                } catch (Exception e) {
                    LogUtils.e(e);
                }
                ((DappConfirmNewActivity) getActivity()).cancle();
            }
        });
    }

    private String getTokenUrl(String str) {
        String format = String.format("%s%s", IRequest.getTronscan20TokenIntroduceUrl(), str);
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return format + "?lang=zh";
        }
        return format + "?lang=en";
    }

    private int getSystemContractHeadIcon(String str) {
        char c;
        switch (str.hashCode()) {
            case -1705044092:
                if (str.equals("WithdrawBalanceContract")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -1048760864:
                if (str.equals("ProposalCreateContract")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -703089577:
                if (str.equals("FreezeBalanceContract")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -651921570:
                if (str.equals("UnfreezeBalanceContract")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 16441433:
                if (str.equals("ParticipateAssetIssueContract")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 180125197:
                if (str.equals("ProposalApproveContract")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 270660495:
                if (str.equals("ProposalDeleteContract")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 762691543:
                if (str.equals("AccountPermissionUpdateContract")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                this.detailParam.setHeaderHint(R.string.dapp_confirm_update_permisson_error_hint);
                this.detailParam.setUpdatePermission(true);
                return R.mipmap.ic_confirm_update_permission;
            case 1:
                return R.mipmap.ic_confirm_stake;
            case 2:
                return R.mipmap.ic_confirm_unstake;
            case 3:
                return R.mipmap.ic_confirm_make_proposal;
            case 4:
                return R.mipmap.ic_confirm_delete_proposal;
            case 5:
                return R.mipmap.ic_confirm_approve_proposal;
            case 6:
                return R.mipmap.ic_confirm_withdraw_balance;
            default:
                return R.mipmap.icon_confirm_system_common;
        }
    }
}
