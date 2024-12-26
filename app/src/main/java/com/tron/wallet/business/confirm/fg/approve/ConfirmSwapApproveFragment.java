package com.tron.wallet.business.confirm.fg.approve;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.bean.nft.NftItemInfo;
import com.tron.wallet.business.addassets2.bean.nft.NftItemInfoOutput;
import com.tron.wallet.business.confirm.fg.BaseConfirmFragment;
import com.tron.wallet.business.confirm.fg.approve.ConfirmSwapApproveContract;
import com.tron.wallet.business.confirm.fg.bean.SwapApproveParam;
import com.tron.wallet.business.confirm.fg.component.ArgumentKeys;
import com.tron.wallet.business.confirm.fg.component.GlobalConfirmButton;
import com.tron.wallet.business.confirm.fg.component.GlobalFeeResourceView;
import com.tron.wallet.business.confirm.fg.component.GlobalTitleHeaderView;
import com.tron.wallet.business.tabdapp.bean.DappProjectInfoBean;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.FgApproveConfirmBinding;
import com.tron.wallet.net.HttpAPI;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.disposables.Disposable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
public class ConfirmSwapApproveFragment extends BaseConfirmFragment<ConfirmSwapApprovePresenter, ConfirmSwapApproveModel> implements ConfirmSwapApproveContract.View {
    View amountEditView;
    RelativeLayout approveLayout;
    TextView approveLayoutTips;
    TextView approvedAddress;
    private FgApproveConfirmBinding binding;
    GlobalConfirmButton confirmButton;
    String contractAddress;
    String contractTypeString;
    GlobalTitleHeaderView headerView;
    SimpleDraweeView ivApproveIcon;
    ImageView ivBack;
    SimpleDraweeView ivIcon;
    ImageView ivIconTag;
    SimpleDraweeView ivSubTitleIcon;
    private NumberFormat numberFormat;
    private SwapApproveParam param;
    GlobalFeeResourceView resourceView;
    RelativeLayout rlAmount;
    RelativeLayout rlApproveAmount;
    RelativeLayout rlContract;
    RelativeLayout rlNote;
    RelativeLayout rlTokenId;
    RelativeLayout rlTopTitleLayout;
    private SwapApproveParam swapApproveParam;
    TokenBean tokenBean;
    TextView tradedAddress;
    TextView tvAmount;
    TextView tvApproveAmount;
    TextView tvApproveInfo;
    TextView tvApproveTokenName;
    TextView tvContractAddress;
    TextView tvName;
    TextView tvNoLimit;
    TextView tvNote;
    TextView tvTokenId;
    TextView tvUnit;

    public void setParam(SwapApproveParam swapApproveParam) {
        this.param = swapApproveParam;
    }

    public static ConfirmSwapApproveFragment getInstance(SwapApproveParam swapApproveParam) {
        ConfirmSwapApproveFragment confirmSwapApproveFragment = new ConfirmSwapApproveFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ArgumentKeys.KEY_PARAM_DETAIL, swapApproveParam);
        bundle.putParcelable(ArgumentKeys.KEY_PARAM, swapApproveParam);
        confirmSwapApproveFragment.setArguments(bundle);
        return confirmSwapApproveFragment;
    }

    @Override
    public void processData() {
        super.processData();
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.swapApproveParam = (SwapApproveParam) arguments.getParcelable(ArgumentKeys.KEY_PARAM);
        }
        try {
            addAccountCallback(this.headerView, this.resourceView, this.confirmButton);
            bindDataToUI();
            ensureAccount();
        } catch (Exception e) {
            LogUtils.e(e);
        }
        this.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$processData$0(view);
            }
        });
    }

    public void lambda$processData$0(View view) {
        this.mContext.finish();
    }

    private void bindDataToUI() {
        SwapApproveParam swapApproveParam = this.swapApproveParam;
        if (swapApproveParam == null) {
            return;
        }
        swapApproveParam.setTitle(R.string.confirmtransaction, R.string.confirmtransaction);
        this.tokenBean = this.swapApproveParam.getTokenBean();
        this.contractTypeString = this.swapApproveParam.getContractTypeString();
        this.contractAddress = this.swapApproveParam.getContractAddress();
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.numberFormat = numberInstance;
        numberInstance.setMaximumFractionDigits(7);
        this.resourceView.bindData(this.swapApproveParam);
        this.headerView.bindData(this.swapApproveParam);
        this.rlTopTitleLayout.setVisibility(View.GONE);
        this.confirmButton.onBind(this.param);
        this.resourceView.setFeeResourceCallback(this.confirmButton);
        this.approveLayout.setVisibility(View.VISIBLE);
        this.approvedAddress.setText(this.swapApproveParam.getContractAddress());
        this.tvNoLimit.setVisibility(View.VISIBLE);
        this.tvApproveAmount.setVisibility(View.GONE);
        this.tvNoLimit.setText(getIContext().getResources().getString(R.string.unlimited));
        this.tvUnit.setText("");
        this.tvContractAddress.setText(this.tokenBean.getContractAddress());
        this.rlContract.setVisibility(View.VISIBLE);
        this.rlApproveAmount.setVisibility(View.VISIBLE);
        this.amountEditView.setVisibility(View.GONE);
        if (this.param.getTokenBean() != null && !StringTronUtil.isEmpty(this.param.getTokenBean().getShortName())) {
            this.tvName.setText(this.param.getTokenBean().getName());
        } else {
            this.tvName.setVisibility(View.GONE);
        }
        String str = this.contractTypeString;
        str.hashCode();
        if (str.equals("TriggerSmartContract")) {
            final int tokenActionType = this.swapApproveParam.getTokenActionType();
            LogUtils.e("tokenActionType  ", "tokenActionType=" + tokenActionType);
            this.ivIconTag.setVisibility(View.GONE);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.ivIcon.getLayoutParams();
            layoutParams.leftMargin = 0;
            this.ivIcon.setLayoutParams(layoutParams);
            if (tokenActionType == 1 || tokenActionType == 990 || tokenActionType == 993 || tokenActionType == 3 || tokenActionType == 4) {
                this.swapApproveParam.addIconResource(R.mipmap.icon_confirm_approve);
                final String contractAddress = this.swapApproveParam.getContractAddress();
                showLoadingDialog();
                runOnThreeThread(new OnBackground() {
                    @Override
                    public final void doOnBackground() {
                        lambda$bindDataToUI$2(contractAddress, tokenActionType);
                    }
                });
            }
        }
    }

    public void lambda$bindDataToUI$2(final String str, final int i) {
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
                    lambda$bindDataToUI$1(account, str, i);
                }
            });
        } catch (Exception e2) {
            LogUtils.e(e2);
        }
    }

    public void lambda$bindDataToUI$1(Protocol.Account account, String str, int i) {
        String str2;
        if (account == null || account.getTypeValue() == 2) {
            if (this.mPresenter != 0) {
                ((ConfirmSwapApprovePresenter) this.mPresenter).getProjectInfo(str);
            }
        } else {
            updateHeaderInfoForProject(this.swapApproveParam.getDappProjectInfoBean(), true);
        }
        TokenBean tokenBean = this.tokenBean;
        if (tokenBean != null) {
            this.tvApproveTokenName.setText(tokenBean.getShortName());
            this.ivApproveIcon.setImageURI(this.tokenBean.getLogoUrl());
            if (StringTronUtil.isEmpty(this.tokenBean.getContractAddress())) {
                return;
            }
            this.tradedAddress.setText(this.tokenBean.getContractAddress());
            this.tradedAddress.setVisibility(View.VISIBLE);
            return;
        }
        this.tvApproveTokenName.setVisibility(View.GONE);
        if (i == 3) {
            try {
                str2 = this.swapApproveParam.getTriggerData().getParameterMap().get("1");
            } catch (Exception e) {
                LogUtils.e(e);
                str2 = null;
            }
            if (StringTronUtil.isEmpty(str2)) {
                return;
            }
            TextView textView = this.tradedAddress;
            textView.setText("#" + str2);
            this.tradedAddress.setVisibility(View.VISIBLE);
        } else if (StringTronUtil.isEmpty(this.swapApproveParam.getContractAddress())) {
        } else {
            this.tradedAddress.setText(this.swapApproveParam.getContractAddress());
            this.tradedAddress.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgApproveConfirmBinding inflate = FgApproveConfirmBinding.inflate(getLayoutInflater());
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
        this.rlAmount = this.binding.rlAmount;
        this.tvAmount = this.binding.tvAmount;
        this.rlApproveAmount = this.binding.rlApproveAmount;
        this.tvApproveAmount = this.binding.tvApproveAmount;
        this.tvUnit = this.binding.unit;
        this.amountEditView = this.binding.ivAmountEdit;
        this.approvedAddress = (TextView) this.binding.getRoot().findViewById(R.id.approved_address);
        this.tvNoLimit = this.binding.tvApproveNoLimitAmount;
        this.rlContract = this.binding.rlContract;
        this.tvContractAddress = this.binding.tvContractAddress;
        this.tvName = this.binding.tvName;
        this.rlTokenId = this.binding.rlTokenId;
        this.tvTokenId = this.binding.tvTokenId;
        this.rlNote = this.binding.rlNote;
        this.tvNote = this.binding.tvNote;
        this.resourceView = this.binding.resourceView;
        this.ivBack = this.binding.ivBack;
        this.tradedAddress = (TextView) this.binding.getRoot().findViewById(R.id.traded_address);
        this.headerView = this.binding.headerView;
        this.confirmButton = this.binding.confirmApprove;
        this.approveLayout = this.binding.headerView.approveLayout;
        this.tvApproveInfo = (TextView) this.binding.getRoot().findViewById(R.id.approve_info);
        this.ivApproveIcon = (SimpleDraweeView) this.binding.getRoot().findViewById(R.id.iv_approve_icon);
        this.tvApproveTokenName = (TextView) this.binding.getRoot().findViewById(R.id.dapp_approve_token_name);
        this.approveLayoutTips = (TextView) this.binding.getRoot().findViewById(R.id.approve_layout_tips);
        this.rlTopTitleLayout = (RelativeLayout) this.binding.getRoot().findViewById(R.id.rl_top_two);
        this.ivIcon = this.binding.headerView.ivIcon;
        this.ivIconTag = (ImageView) this.binding.getRoot().findViewById(R.id.iv_icon_tag);
        this.ivSubTitleIcon = (SimpleDraweeView) this.binding.getRoot().findViewById(R.id.iv_sub_title_icon);
    }

    public void setClick() {
        NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                switch (view.getId()) {
                    case R.id.approved_address:
                        if (swapApproveParam == null || StringTronUtil.isEmpty(swapApproveParam.getContractAddress())) {
                            return;
                        }
                        Activity activity = mContext;
                        ConfirmSwapApproveFragment confirmSwapApproveFragment = ConfirmSwapApproveFragment.this;
                        CommonWebActivityV3.start((Context) activity, confirmSwapApproveFragment.getContractUrl(confirmSwapApproveFragment.swapApproveParam.getContractAddress()), mContext.getResources().getString(R.string.tronscan), -2, true);
                        return;
                    case R.id.iv_close:
                        mContext.finish();
                        return;
                    case R.id.rl_contract_address:
                    case R.id.traded_address:
                    case R.id.tv_contract_address:
                        if (swapApproveParam == null || swapApproveParam.getTokenBean() == null || StringTronUtil.isEmpty(swapApproveParam.getTokenBean().getContractAddress())) {
                            return;
                        }
                        Activity activity2 = mContext;
                        ConfirmSwapApproveFragment confirmSwapApproveFragment2 = ConfirmSwapApproveFragment.this;
                        CommonWebActivityV3.start((Context) activity2, confirmSwapApproveFragment2.getTokenUrl(confirmSwapApproveFragment2.swapApproveParam.getTokenBean().getContractAddress()), mContext.getResources().getString(R.string.tronscan), -2, true);
                        return;
                    case R.id.tv_token_id:
                        Activity activity3 = mContext;
                        ConfirmSwapApproveFragment confirmSwapApproveFragment3 = ConfirmSwapApproveFragment.this;
                        CommonWebActivityV3.start((Context) activity3, confirmSwapApproveFragment3.getTrc10Url(confirmSwapApproveFragment3.param.getAssetName()), mContext.getResources().getString(R.string.tronscan), -2, true);
                        return;
                    default:
                        return;
                }
            }
        };
        this.binding.getRoot().findViewById(R.id.iv_close).setOnClickListener(noDoubleClickListener);
        this.binding.getRoot().findViewById(R.id.approved_address).setOnClickListener(noDoubleClickListener);
        this.binding.getRoot().findViewById(R.id.traded_address).setOnClickListener(noDoubleClickListener);
        this.binding.rlContractAddress.setOnClickListener(noDoubleClickListener);
        this.binding.tvTokenId.setOnClickListener(noDoubleClickListener);
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

    private String getAccountUrl(String str) {
        String accountUrl = IRequest.getAccountUrl(str);
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return accountUrl + "?lang=zh";
        }
        return accountUrl + "?lang=en";
    }

    @Override
    public void updateHeaderInfoForProject(DappProjectInfoBean dappProjectInfoBean, boolean z) {
        String format;
        if (isAdded()) {
            if (dappProjectInfoBean == null || (dappProjectInfoBean.getProjectName() == null && dappProjectInfoBean.getProjectLogo() == null && dappProjectInfoBean.getContractAddress() == null && dappProjectInfoBean.getProjectUrl() == null)) {
                updateHeaderInfoForProject(this.swapApproveParam.getDappProjectInfoBean(), true);
                return;
            }
            dismissLoadingDialog();
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
                this.approveLayoutTips.setText(getResources().getString(R.string.approve_confirm_tips_account));
                this.approveLayoutTips.setVisibility(View.VISIBLE);
            }
            this.tvApproveInfo.setText(format);
            this.tvApproveInfo.setVisibility(View.VISIBLE);
            this.approveLayout.setVisibility(View.VISIBLE);
        }
    }

    public boolean isUnlimited(String str, TokenBean tokenBean) {
        return BigDecimalUtils.MoreThan(new BigDecimal(str), new BigDecimal(Math.pow(10.0d, (tokenBean != null ? tokenBean.getPrecision() : 0) + 18)));
    }

    public void getCollectionInfo(final String str, final String str2, final String str3) {
        runOnThreeThread(new OnBackground() {
            @Override
            public void doOnBackground() {
                ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getCollectionInfo(str, str2, str3).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<NftItemInfoOutput>() {
                    @Override
                    public void onFailure(String str4, String str5) {
                    }

                    @Override
                    public void onSubscribe(Disposable disposable) {
                    }

                    @Override
                    public void onResponse(String str4, NftItemInfoOutput nftItemInfoOutput) {
                        updateNftInfoUI(nftItemInfoOutput.getData());
                    }
                }, "getCollectionInfo"));
            }
        });
    }

    public void updateNftInfoUI(NftItemInfo nftItemInfo) {
        if (nftItemInfo == null || StringTronUtil.isNullOrEmpty(nftItemInfo.getName())) {
            return;
        }
        String str = this.param.getTriggerData().getParameterMap().get("1");
        String name = !StringTronUtil.isNullOrEmpty(nftItemInfo.getName()) ? nftItemInfo.getName() : "";
        TextView textView = this.tvApproveAmount;
        textView.setText("#" + str + " " + name);
    }

    public void onRefreshAccountComplete(boolean z, Protocol.Transaction transaction, Pair<Protocol.Account, GrpcAPI.AccountResourceMessage> pair) {
        this.resourceView.onRefreshAccountComplete(z, transaction, pair);
    }

    private String getUrl() {
        String contractUrl = IRequest.getContractUrl(this.param.getContractAddress());
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return contractUrl + "?lang=zh";
        }
        return contractUrl + "?lang=en";
    }

    public String getTrc10Url(String str) {
        String tRC10Url = IRequest.getTRC10Url(str);
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return tRC10Url + "?lang=zh";
        }
        return tRC10Url + "?lang=en";
    }
}
