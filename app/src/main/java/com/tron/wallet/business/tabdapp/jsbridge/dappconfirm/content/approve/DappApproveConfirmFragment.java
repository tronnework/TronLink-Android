package com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.approve;

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
import com.tron.wallet.business.tabdapp.bean.DappProjectInfoBean;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.DappConfirmNewActivity;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.approve.DappApproveConfirmContract;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.approve.one.DappApproveConfirmFragmentOne;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.approve.two.DappApproveConfirmFragmentTwo;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.PopupWindowUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.FgDappConfirmApproveBinding;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import org.tron.protos.Protocol;
public class DappApproveConfirmFragment extends BaseConfirmFragment<DappApproveConfirmPresenter, DappApproveConfirmModel> implements DappApproveConfirmContract.View {
    RelativeLayout approveLayout;
    TextView approveLayoutTips;
    private FgDappConfirmApproveBinding binding;
    String contractTypeString;
    private DappDetailParam detailParam;
    DappApproveConfirmFragmentOne fragmentOne;
    DappApproveConfirmFragmentTwo fragmentTwo;
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
    private NftItemInfo nftInfo;
    private NumberFormat numberFormat;
    private int resTitleId;
    TokenBean tokenBean;
    TextView tvApproveInfo;
    TextView tvApproveProject;
    TextView tvApproveTokenName;
    TextView tvApprovedAddress;
    TextView tvTitle;
    TextView tvTradeAddress;

    public static DappApproveConfirmFragment getInstance(BaseParam baseParam, BaseParam baseParam2) {
        DappApproveConfirmFragment dappApproveConfirmFragment = new DappApproveConfirmFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ArgumentKeys.KEY_PARAM_DETAIL, baseParam);
        bundle.putParcelable(ArgumentKeys.KEY_PARAM_META, baseParam2);
        bundle.putParcelable(ArgumentKeys.KEY_PARAM, baseParam2);
        dappApproveConfirmFragment.setArguments(bundle);
        return dappApproveConfirmFragment;
    }

    @Override
    public void processData() {
        super.processData();
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.detailParam = (DappDetailParam) arguments.getParcelable(ArgumentKeys.KEY_PARAM_DETAIL);
            this.metaParam = (DappMetadataParam) arguments.getParcelable(ArgumentKeys.KEY_PARAM_META);
        }
        this.fragmentOne = DappApproveConfirmFragmentOne.getInstance(this.detailParam, this.metaParam);
        this.fragmentTwo = DappApproveConfirmFragmentTwo.getInstance(this.detailParam, this.metaParam);
        FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
        beginTransaction.add(R.id.content, this.fragmentOne, "0");
        beginTransaction.add(R.id.content, this.fragmentTwo, "1");
        if (this.detailParam.getDappProjectInfoBean() != null && this.detailParam.getDappProjectInfoBean().isWhite()) {
            this.detailParam.setDetail(true);
            beginTransaction.show(this.fragmentTwo).hide(this.fragmentOne);
        } else {
            beginTransaction.show(this.fragmentOne).hide(this.fragmentTwo);
            if (getActivity() instanceof DappConfirmNewActivity) {
                ((DappConfirmNewActivity) getActivity()).setButtonStatus(false);
            } else if (getActivity() instanceof DeepLinkDappConfirmActivity) {
                ((DeepLinkDappConfirmActivity) getActivity()).setButtonStatus(false);
            }
        }
        beginTransaction.disallowAddToBackStack();
        beginTransaction.commit();
        try {
            bindDataToUI();
            if (getActivity() instanceof DappConfirmNewActivity) {
                addAccountCallback(this.headerView, ((DappConfirmNewActivity) getActivity()).getConfirmBtn());
            } else if (getActivity() instanceof DeepLinkDappConfirmActivity) {
                addAccountCallback(this.headerView, ((DeepLinkDappConfirmActivity) getActivity()).getConfirmBtn());
            }
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
                if (getActivity() instanceof DappConfirmNewActivity) {
                    ((DappConfirmNewActivity) getActivity()).getConfirmBtn().setApprove(true);
                    ((DappConfirmNewActivity) getActivity()).getConfirmBtn().setHasApproveTitle(false);
                } else if (getActivity() instanceof DeepLinkDappConfirmActivity) {
                    ((DeepLinkDappConfirmActivity) getActivity()).getConfirmBtn().setApprove(true);
                    ((DeepLinkDappConfirmActivity) getActivity()).getConfirmBtn().setHasApproveTitle(false);
                }
                this.detailParam.addIconResource(R.mipmap.icon_confirm_approve);
                final String str2 = this.detailParam.getTriggerData().getParameterMap().get("0");
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

    public void lambda$bindDataToUI$1(String str, final int i) {
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
                    lambda$bindDataToUI$0(account, i);
                }
            });
        } catch (Exception e2) {
            LogUtils.e(e2);
        }
    }

    public void lambda$bindDataToUI$0(Protocol.Account account, int i) {
        String str = null;
        if (account == null || account.getTypeValue() == 2) {
            if (this.mPresenter != 0) {
                updateHeaderInfoForProject(this.detailParam.getDappProjectInfoBean(), false);
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
                str = this.detailParam.getTriggerData().getParameterMap().get("1");
            } catch (Exception e) {
                LogUtils.e(e);
            }
            if (StringTronUtil.isEmpty(str)) {
                return;
            }
            TextView textView = this.tvTradeAddress;
            textView.setText("#" + str);
            this.tvTradeAddress.setVisibility(View.VISIBLE);
        } else if (StringTronUtil.isEmpty(this.detailParam.getContractAddress())) {
        } else {
            this.tvTradeAddress.setText(this.detailParam.getContractAddress());
            this.tvTradeAddress.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgDappConfirmApproveBinding inflate = FgDappConfirmApproveBinding.inflate(layoutInflater, viewGroup, false);
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
    }

    public void setClick() {
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                int i = 2;
                if (id != R.id.iv_close) {
                    if (id != R.id.traded_address) {
                        return;
                    }
                    Activity activity = mContext;
                    DappApproveConfirmFragment dappApproveConfirmFragment = DappApproveConfirmFragment.this;
                    CommonWebActivityV3.start((Context) activity, dappApproveConfirmFragment.getTokenUrl(dappApproveConfirmFragment.detailParam.getContractAddress()), mContext.getResources().getString(R.string.tronscan), -2, true);
                    try {
                        if (detailParam.getContractType() != Protocol.Transaction.Contract.ContractType.TriggerSmartContract.getNumber()) {
                            i = 1;
                        }
                        AnalyticsHelper.logEventFormat(AnalyticsHelper.TransactionConfirmPopup.CLICK_CONFIRM_PAGE_APPROVED_TOKEN_ID, Integer.valueOf(i));
                        return;
                    } catch (Exception e) {
                        LogUtils.e(e);
                        return;
                    }
                }
                try {
                    if (detailParam.getContractType() != Protocol.Transaction.Contract.ContractType.TriggerSmartContract.getNumber()) {
                        i = 1;
                    }
                    AnalyticsHelper.logEventFormat(AnalyticsHelper.TransactionConfirmPopup.CLICK_CONFIRM_PAGE_BACK, Integer.valueOf(i));
                } catch (Exception e2) {
                    LogUtils.e(e2);
                }
                if (getActivity() instanceof DappConfirmNewActivity) {
                    ((DappConfirmNewActivity) getActivity()).cancle();
                } else if (getActivity() instanceof DeepLinkDappConfirmActivity) {
                    ((DeepLinkDappConfirmActivity) getActivity()).cancle();
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
            try {
                str = this.detailParam.getTriggerData().getParameterMap().get("0");
            } catch (Exception e) {
                LogUtils.e(e);
                str = "";
            }
            if (getActivity() instanceof DappConfirmNewActivity) {
                ((DappConfirmNewActivity) getActivity()).getConfirmBtn().setHasApproveTitle(true);
            } else if (getActivity() instanceof DeepLinkDappConfirmActivity) {
                ((DeepLinkDappConfirmActivity) getActivity()).getConfirmBtn().setHasApproveTitle(true);
            }
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
                if (getActivity() instanceof DappConfirmNewActivity) {
                    ((DappConfirmNewActivity) getActivity()).getConfirmBtn().setApproveAccount(true);
                } else if (getActivity() instanceof DeepLinkDappConfirmActivity) {
                    ((DeepLinkDappConfirmActivity) getActivity()).getConfirmBtn().setApproveAccount(true);
                }
                this.approveLayoutTips.setText(getResources().getString(R.string.approve_confirm_tips_account));
                this.approveLayoutTips.setVisibility(View.VISIBLE);
            }
            this.tvApprovedAddress.setOnClickListener(new NoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    if (z) {
                        Activity activity = mContext;
                        DappApproveConfirmFragment dappApproveConfirmFragment = DappApproveConfirmFragment.this;
                        CommonWebActivityV3.start((Context) activity, dappApproveConfirmFragment.getAccountUrl(dappApproveConfirmFragment.detailParam.getTriggerData().getParameterMap().get("0")), mContext.getResources().getString(R.string.tronscan), -2, true);
                        return;
                    }
                    Activity activity2 = mContext;
                    DappApproveConfirmFragment dappApproveConfirmFragment2 = DappApproveConfirmFragment.this;
                    CommonWebActivityV3.start((Context) activity2, dappApproveConfirmFragment2.getContractUrl(dappApproveConfirmFragment2.detailParam.getTriggerData().getParameterMap().get("0")), mContext.getResources().getString(R.string.tronscan), -2, true);
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

    @Override
    public void updateApproveAmount(String str) {
        this.detailParam.getTriggerData().getParameterMap().get("0");
    }

    public void setDappProjectInfoBean(DappProjectInfoBean dappProjectInfoBean, boolean z, TokenBean tokenBean) {
        this.detailParam.setTokenBean(tokenBean);
        updateHeaderInfoForProject(dappProjectInfoBean, z);
    }

    public void next() {
        this.detailParam.setDetail(true);
        this.detailParam.setAmount(this.fragmentOne.getApproveAmount());
        String str = this.detailParam.getTriggerData().getParameterMap().get("0");
        this.detailParam.getTriggerData().getParameterMap().put("1", BigDecimalUtils.toString(this.fragmentOne.getApproveAmount()));
        this.fragmentTwo.updateDetail(this.detailParam, true);
        this.fragmentTwo.updateMetaData(str, BigDecimalUtils.toString(this.fragmentOne.getApproveAmount()));
        if (getActivity() instanceof DappConfirmNewActivity) {
            ((DappConfirmNewActivity) getActivity()).setEditApproveAmountParam(str, this.fragmentOne.getApproveAmount());
        } else if (getActivity() instanceof DeepLinkDappConfirmActivity) {
            ((DeepLinkDappConfirmActivity) getActivity()).setEditApproveAmountParam(str, this.fragmentOne.getApproveAmount());
        }
        FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
        beginTransaction.show(this.fragmentTwo).hide(this.fragmentOne);
        beginTransaction.disallowAddToBackStack();
        beginTransaction.commit();
    }

    public boolean isUnlimited(String str, TokenBean tokenBean) {
        return BigDecimalUtils.MoreThan(new BigDecimal(str), new BigDecimal(Math.pow(10.0d, (tokenBean != null ? tokenBean.getPrecision() : 0) + 18)));
    }

    @Override
    public void switchOne() {
        FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
        beginTransaction.show(this.fragmentOne).hide(this.fragmentTwo);
        this.fragmentOne.updateSelectApproveMode();
        beginTransaction.disallowAddToBackStack();
        beginTransaction.commit();
        if (getActivity() instanceof DappConfirmNewActivity) {
            ((DappConfirmNewActivity) getActivity()).switchApproveOne();
        } else if (getActivity() instanceof DeepLinkDappConfirmActivity) {
            ((DeepLinkDappConfirmActivity) getActivity()).switchApproveOne();
        }
    }
}
