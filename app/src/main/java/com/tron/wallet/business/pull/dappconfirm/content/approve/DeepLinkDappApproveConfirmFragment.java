package com.tron.wallet.business.pull.dappconfirm.content.approve;

import android.app.Activity;
import android.content.Context;
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
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
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
import com.tron.wallet.business.pull.dappconfirm.content.DeepLinkDappConfirmActivity;
import com.tron.wallet.business.pull.dappconfirm.content.approve.DeepLinkDappApproveConfirmContract;
import com.tron.wallet.business.pull.dappconfirm.tab.DeepLinkTransactionMetadataFragment;
import com.tron.wallet.business.tabdapp.bean.DappProjectInfoBean;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.xtablayout.XTabLayout;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.PopupWindowUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.FgDappConfirmSystemContractBinding;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import java.text.NumberFormat;
import java.util.Locale;
import org.tron.protos.Protocol;
public class DeepLinkDappApproveConfirmFragment extends BaseConfirmFragment<DeepLinkDappApproveConfirmPresenter, DeepLinkDappApproveConfirmModel> implements DeepLinkDappApproveConfirmContract.View, XTabLayout.OnTabSelectedListener {
    RelativeLayout approveLayout;
    TextView approveLayoutTips;
    private FgDappConfirmSystemContractBinding binding;
    String contractTypeString;
    DeepLinkDappApproveTransactionDetailFragment detailFragment;
    private DappDetailParam detailParam;
    GlobalTitleHeaderView headerView;
    ImageView ivAddressPop;
    SimpleDraweeView ivApproveIcon;
    LinearLayout ivClose;
    SimpleDraweeView ivIcon;
    ImageView ivIconTag;
    ImageView ivSc;
    SimpleDraweeView ivSubTitleIcon;
    LinearLayout llRoot;
    private DappMetadataParam metaParam;
    DeepLinkTransactionMetadataFragment metadataFragment;
    private NftItemInfo nftInfo;
    private NumberFormat numberFormat;
    private int resTitleId;
    XTabLayout tabLayout;
    TokenBean tokenBean;
    TextView tvApproveInfo;
    TextView tvApproveProject;
    TextView tvApproveTokenName;
    TextView tvApprovedAddress;
    TextView tvTitle;
    TextView tvTradeAddress;

    @Override
    public void onTabReselected(XTabLayout.Tab tab) {
    }

    @Override
    public void onTabUnselected(XTabLayout.Tab tab) {
    }

    public static DeepLinkDappApproveConfirmFragment getInstance(BaseParam baseParam, BaseParam baseParam2) {
        DeepLinkDappApproveConfirmFragment deepLinkDappApproveConfirmFragment = new DeepLinkDappApproveConfirmFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ArgumentKeys.KEY_PARAM_DETAIL, baseParam);
        bundle.putParcelable(ArgumentKeys.KEY_PARAM_META, baseParam2);
        bundle.putParcelable(ArgumentKeys.KEY_PARAM, baseParam2);
        deepLinkDappApproveConfirmFragment.setArguments(bundle);
        return deepLinkDappApproveConfirmFragment;
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
        this.metadataFragment = DeepLinkTransactionMetadataFragment.getInstance(this.metaParam);
        this.detailFragment = DeepLinkDappApproveTransactionDetailFragment.getInstance(this.detailParam);
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
            addAccountCallback(this.headerView, this.detailFragment, ((DeepLinkDappConfirmActivity) getActivity()).getConfirmBtn());
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
        String str = this.contractTypeString;
        str.hashCode();
        if (str.equals("TriggerSmartContract")) {
            final int tokenActionType = this.detailParam.getTokenActionType();
            LogUtils.e("tokenActionType  ", "tokenActionType=" + tokenActionType);
            this.ivIconTag.setVisibility(View.GONE);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.ivIcon.getLayoutParams();
            layoutParams.leftMargin = 0;
            this.ivIcon.setLayoutParams(layoutParams);
            if (tokenActionType == 1 || tokenActionType == 990 || tokenActionType == 993 || tokenActionType == 3 || tokenActionType == 4) {
                ((DeepLinkDappConfirmActivity) getActivity()).setButtonStatus(false);
                ((DeepLinkDappConfirmActivity) getActivity()).getConfirmBtn().setApprove(true);
                ((DeepLinkDappConfirmActivity) getActivity()).getConfirmBtn().setHasApproveTitle(false);
                this.detailParam.addIconResource(R.mipmap.icon_confirm_approve);
                final String str2 = this.detailParam.getTriggerData().getParameterMap().get("0");
                showLoadingDialog();
                runOnThreeThread(new OnBackground() {
                    @Override
                    public final void doOnBackground() {
                        lambda$bindDataToUI$1(str2, tokenActionType);
                    }
                });
                this.detailParam.addInfoTitle(R.string.dapp_approve, "");
            }
        }
        this.headerView.bindData(this.detailParam);
    }

    public void lambda$bindDataToUI$1(final String str, final int i) {
        final Protocol.Account account;
        try {
            try {
                account = TronAPI.queryAccount(StringTronUtil.decodeFromBase58Check(str), false);
            } catch (Exception e) {
                LogUtils.e(e);
                account = null;
            }
            runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$bindDataToUI$0(account, str, i);
                }
            });
        } catch (Exception e2) {
            LogUtils.e(e2);
        }
    }

    public void lambda$bindDataToUI$0(Protocol.Account account, String str, int i) {
        String str2 = null;
        if (account == null || account.getTypeValue() == 2) {
            if (this.mPresenter != 0) {
                ((DeepLinkDappApproveConfirmPresenter) this.mPresenter).getProjectInfo(str);
            }
        } else {
            updateHeaderInfoForProject(null, true);
            if (!StringTronUtil.isEmpty(this.detailParam.getContractAddress())) {
                this.tvTradeAddress.setText(this.detailParam.getContractAddress());
                this.tvTradeAddress.setVisibility(View.VISIBLE);
            }
        }
        TokenBean tokenBean = this.tokenBean;
        if (tokenBean != null) {
            this.tvApproveTokenName.setText(tokenBean.getShortName());
            this.ivApproveIcon.setImageURI(this.tokenBean.getLogoUrl());
            if (StringTronUtil.isEmpty(this.detailParam.getContractAddress())) {
                return;
            }
            this.tvTradeAddress.setText(this.detailParam.getContractAddress());
            this.tvTradeAddress.setVisibility(View.VISIBLE);
            return;
        }
        this.tvApproveTokenName.setVisibility(View.GONE);
        if (i == 3) {
            try {
                str2 = this.detailParam.getTriggerData().getParameterMap().get("1");
            } catch (Exception e) {
                LogUtils.e(e);
            }
            if (StringTronUtil.isEmpty(str2)) {
                return;
            }
            TextView textView = this.tvTradeAddress;
            textView.setText("#" + str2);
            this.tvTradeAddress.setVisibility(View.VISIBLE);
        } else if (StringTronUtil.isEmpty(this.detailParam.getContractAddress())) {
        } else {
            this.tvTradeAddress.setText(this.detailParam.getContractAddress());
            this.tvTradeAddress.setVisibility(View.VISIBLE);
        }
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
    public void onTabSelected(XTabLayout.Tab tab) {
        DeepLinkTransactionMetadataFragment deepLinkTransactionMetadataFragment;
        FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
        LogUtils.e("onTabSelected", String.valueOf(tab.getPosition()));
        int position = tab.getPosition();
        if (position == 0) {
            DeepLinkDappApproveTransactionDetailFragment deepLinkDappApproveTransactionDetailFragment = this.detailFragment;
            if (deepLinkDappApproveTransactionDetailFragment != null) {
                beginTransaction.show(deepLinkDappApproveTransactionDetailFragment).hide(this.metadataFragment);
                beginTransaction.disallowAddToBackStack();
                beginTransaction.commitAllowingStateLoss();
            }
        } else if (position == 1 && (deepLinkTransactionMetadataFragment = this.metadataFragment) != null) {
            beginTransaction.show(deepLinkTransactionMetadataFragment).hide(this.detailFragment);
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
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                int i = 2;
                if (id == R.id.iv_close) {
                    try {
                        if (detailParam.getContractType() != Protocol.Transaction.Contract.ContractType.TriggerSmartContract.getNumber()) {
                            i = 1;
                        }
                        AnalyticsHelper.logEventFormat(AnalyticsHelper.TransactionConfirmPopup.CLICK_CONFIRM_PAGE_BACK, Integer.valueOf(i));
                    } catch (Exception e) {
                        LogUtils.e(e);
                    }
                    ((DeepLinkDappConfirmActivity) getActivity()).cancle();
                } else if (id != R.id.traded_address) {
                } else {
                    Activity activity = mContext;
                    DeepLinkDappApproveConfirmFragment deepLinkDappApproveConfirmFragment = DeepLinkDappApproveConfirmFragment.this;
                    CommonWebActivityV3.start((Context) activity, deepLinkDappApproveConfirmFragment.getTokenUrl(deepLinkDappApproveConfirmFragment.detailParam.getContractAddress()), mContext.getResources().getString(R.string.tronscan), -2, true);
                    try {
                        if (detailParam.getContractType() != Protocol.Transaction.Contract.ContractType.TriggerSmartContract.getNumber()) {
                            i = 1;
                        }
                        AnalyticsHelper.logEventFormat(AnalyticsHelper.TransactionConfirmPopup.CLICK_CONFIRM_PAGE_APPROVED_TOKEN_ID, Integer.valueOf(i));
                    } catch (Exception e2) {
                        LogUtils.e(e2);
                    }
                }
            }
        };
        this.ivClose.setOnClickListener(noDoubleClickListener2);
        this.binding.getRoot().findViewById(R.id.traded_address).setOnClickListener(noDoubleClickListener2);
    }

    public String getContractUrl(String str) {
        String contractUrl = IRequest.getContractUrl(str);
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return contractUrl + "?lang=zh";
        }
        return contractUrl + "?lang=en";
    }

    public String getTokenUrl(String str) {
        String format = String.format("%s%s", IRequest.getTronscan20TokenIntroduceUrl(), str);
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return format + "?lang=zh";
        }
        return format + "?lang=en";
    }

    public String getAccountUrl(String str) {
        String accountUrl = IRequest.getAccountUrl(str);
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return accountUrl + "?lang=zh";
        }
        return accountUrl + "?lang=en";
    }

    @Override
    public void updateHeaderInfoForProject(final DappProjectInfoBean dappProjectInfoBean, final boolean z) {
        String str;
        String format;
        if (isAdded()) {
            runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$updateHeaderInfoForProject$2();
                }
            });
            try {
                str = this.detailParam.getTriggerData().getParameterMap().get("0");
            } catch (Exception e) {
                LogUtils.e(e);
                str = "";
            }
            ((DeepLinkDappConfirmActivity) getActivity()).getConfirmBtn().setHasApproveTitle(true);
            ((DeepLinkDappConfirmActivity) getActivity()).getConfirmBtn().setEnabled(true);
            if (dappProjectInfoBean != null && !StringTronUtil.isEmpty(dappProjectInfoBean.getProjectName())) {
                String string = getResources().getString(R.string.dapp_approve_title_project);
                format = String.format(string, dappProjectInfoBean.getProjectName() + "");
                this.approveLayoutTips.setVisibility(View.GONE);
            } else if (!z) {
                format = String.format(getResources().getString(R.string.dapp_approve_title_project_unknown), getResources().getString(R.string.dapp_approve_unknow_project));
                this.approveLayoutTips.setTextColor(getResources().getColor(R.color.orange_FFA9));
                this.approveLayoutTips.setText(getResources().getString(R.string.approve_confirm_tips_project));
                this.approveLayoutTips.setVisibility(View.VISIBLE);
            } else {
                format = String.format(getResources().getString(R.string.dapp_approve_title_account), new Object[0]);
                this.approveLayoutTips.setTextColor(getResources().getColor(R.color.red_ec));
                ((DeepLinkDappConfirmActivity) getActivity()).getConfirmBtn().setApproveAccount(true);
                this.approveLayoutTips.setText(getResources().getString(R.string.approve_confirm_tips_account));
                this.approveLayoutTips.setVisibility(View.VISIBLE);
            }
            this.tvApprovedAddress.setOnClickListener(new NoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    if (z) {
                        Activity activity = mContext;
                        DeepLinkDappApproveConfirmFragment deepLinkDappApproveConfirmFragment = DeepLinkDappApproveConfirmFragment.this;
                        CommonWebActivityV3.start((Context) activity, deepLinkDappApproveConfirmFragment.getAccountUrl(deepLinkDappApproveConfirmFragment.detailParam.getTriggerData().getParameterMap().get("0")), mContext.getResources().getString(R.string.tronscan), -2, true);
                        return;
                    }
                    Activity activity2 = mContext;
                    DeepLinkDappApproveConfirmFragment deepLinkDappApproveConfirmFragment2 = DeepLinkDappApproveConfirmFragment.this;
                    CommonWebActivityV3.start((Context) activity2, deepLinkDappApproveConfirmFragment2.getContractUrl(deepLinkDappApproveConfirmFragment2.detailParam.getTriggerData().getParameterMap().get("0")), mContext.getResources().getString(R.string.tronscan), -2, true);
                    try {
                        AnalyticsHelper.logEventFormat(AnalyticsHelper.TransactionConfirmPopup.CLICK_CONFIRM_PAGE_APPROVED_CONTRACT, Integer.valueOf(detailParam.getContractType() == Protocol.Transaction.Contract.ContractType.TriggerSmartContract.getNumber() ? 2 : 1));
                    } catch (Exception e2) {
                        LogUtils.e(e2);
                    }
                }
            });
            if (dappProjectInfoBean != null && !StringTronUtil.isEmpty(dappProjectInfoBean.getProjectName())) {
                this.ivAddressPop.setImageResource(R.mipmap.icon_dapp_approve_white);
                this.ivAddressPop.setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        PopupWindowUtil.showPopupText(mContext, String.format(getResources().getString(R.string.dapp_approve_tips_project), dappProjectInfoBean.getProjectName()), ivAddressPop, false);
                    }
                });
            } else if (!z) {
                this.ivAddressPop.setImageResource(R.mipmap.icon_dapp_approve_unknown);
                this.ivAddressPop.setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        PopupWindowUtil.showPopupText(mContext, getResources().getString(R.string.dapp_approve_tips_known), ivAddressPop, false);
                    }
                });
            } else {
                this.ivAddressPop.setImageResource(R.mipmap.icon_dapp_approve_account);
                this.ivAddressPop.setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        PopupWindowUtil.showPopupText(mContext, getResources().getString(R.string.dapp_approve_tips_account), ivAddressPop, false);
                    }
                });
            }
            if (!z) {
                this.ivSc.setVisibility(View.VISIBLE);
            } else {
                this.ivSc.setVisibility(View.GONE);
            }
            this.tvTitle.setTextSize(18.0f);
            this.tvApproveProject.setText(format);
            this.tvApprovedAddress.setText(str);
            this.approveLayout.setVisibility(View.VISIBLE);
        }
    }

    public void lambda$updateHeaderInfoForProject$2() {
        dismissLoadingDialog();
    }

    public void mappingId() {
        this.llRoot = this.binding.root;
        this.ivClose = (LinearLayout) this.binding.getRoot().findViewById(R.id.iv_close);
        this.headerView = this.binding.headerView;
        this.approveLayout = (RelativeLayout) this.binding.getRoot().findViewById(R.id.approve_layout);
        this.tvTitle = (TextView) this.binding.getRoot().findViewById(R.id.tv_info_title);
        this.tvApproveProject = (TextView) this.binding.getRoot().findViewById(R.id.approve_project);
        this.tvApprovedAddress = (TextView) this.binding.getRoot().findViewById(R.id.approved_address);
        this.tvApproveInfo = (TextView) this.binding.getRoot().findViewById(R.id.approve_info);
        this.ivApproveIcon = (SimpleDraweeView) this.binding.getRoot().findViewById(R.id.iv_approve_icon);
        this.tvApproveTokenName = (TextView) this.binding.getRoot().findViewById(R.id.dapp_approve_token_name);
        this.tvTradeAddress = (TextView) this.binding.getRoot().findViewById(R.id.traded_address);
        this.approveLayoutTips = (TextView) this.binding.getRoot().findViewById(R.id.approve_layout_tips);
        this.ivSc = (ImageView) this.binding.getRoot().findViewById(R.id.sc_icon);
        this.ivAddressPop = (ImageView) this.binding.getRoot().findViewById(R.id.addrss_pop_icon);
        this.ivIcon = (SimpleDraweeView) this.binding.getRoot().findViewById(R.id.iv_icon);
        this.ivIconTag = (ImageView) this.binding.getRoot().findViewById(R.id.iv_icon_tag);
        this.ivSubTitleIcon = (SimpleDraweeView) this.binding.getRoot().findViewById(R.id.iv_sub_title_icon);
        this.tabLayout = this.binding.tablayout;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }
}
