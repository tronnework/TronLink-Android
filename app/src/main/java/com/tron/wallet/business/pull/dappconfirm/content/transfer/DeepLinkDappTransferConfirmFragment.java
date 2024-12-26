package com.tron.wallet.business.pull.dappconfirm.content.transfer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.fragment.app.FragmentTransaction;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tron.tron_base.frame.interfaces.OnBackground;
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
import com.tron.wallet.business.confirm.fg.component.ArgumentKeys;
import com.tron.wallet.business.confirm.fg.component.GlobalTitleHeaderView;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.DappDetailParam;
import com.tron.wallet.business.confirm.parambuilder.bean.DappMetadataParam;
import com.tron.wallet.business.pull.dappconfirm.content.DeepLinkDappConfirmActivity;
import com.tron.wallet.business.pull.dappconfirm.content.transfer.DeepLinkDappTransferConfirmContract;
import com.tron.wallet.business.pull.dappconfirm.tab.DeepLinkTransactionMetadataFragment;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.xtablayout.XTabLayout;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.FgDappConfirmSystemContractBinding;
import com.tron.wallet.net.HttpAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.disposables.Disposable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import org.tron.protos.Protocol;
public class DeepLinkDappTransferConfirmFragment extends BaseConfirmFragment<DeepLinkDappTransferConfirmPresenter, DeepLinkDappTransferConfirmModel> implements DeepLinkDappTransferConfirmContract.View, XTabLayout.OnTabSelectedListener {
    private FgDappConfirmSystemContractBinding binding;
    String contractTypeString;
    DeepLinkDappTransferTransactionDetailFragment detailFragment;
    private DappDetailParam detailParam;
    GlobalTitleHeaderView headerView;
    LinearLayout ivClose;
    SimpleDraweeView ivIcon;
    ImageView ivIconTag;
    SimpleDraweeView ivSubTitleIcon;
    LinearLayout llRoot;
    private DappMetadataParam metaParam;
    DeepLinkTransactionMetadataFragment metadataFragment;
    private NftItemInfo nftInfo;
    private NumberFormat numberFormat;
    private int resTitleId;
    XTabLayout tabLayout;
    TokenBean tokenBean;

    @Override
    public void onTabReselected(XTabLayout.Tab tab) {
    }

    @Override
    public void onTabUnselected(XTabLayout.Tab tab) {
    }

    public static DeepLinkDappTransferConfirmFragment getInstance(BaseParam baseParam, BaseParam baseParam2) {
        DeepLinkDappTransferConfirmFragment deepLinkDappTransferConfirmFragment = new DeepLinkDappTransferConfirmFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ArgumentKeys.KEY_PARAM_DETAIL, baseParam);
        bundle.putParcelable(ArgumentKeys.KEY_PARAM_META, baseParam2);
        bundle.putParcelable(ArgumentKeys.KEY_PARAM, baseParam2);
        deepLinkDappTransferConfirmFragment.setArguments(bundle);
        return deepLinkDappTransferConfirmFragment;
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
        this.detailFragment = DeepLinkDappTransferTransactionDetailFragment.getInstance(this.detailParam);
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
        char c = 65535;
        switch (str.hashCode()) {
            case 706457047:
                if (str.equals("TransferAssetContract")) {
                    c = 0;
                    break;
                }
                break;
            case 710366781:
                if (str.equals("TransferContract")) {
                    c = 1;
                    break;
                }
                break;
            case 1421429571:
                if (str.equals("TriggerSmartContract")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                this.ivIconTag.setVisibility(View.VISIBLE);
                this.ivIcon.setImageResource(R.mipmap.icon_confirm_transfer);
                TokenBean tokenBean = this.tokenBean;
                if (tokenBean != null) {
                    this.ivIcon.setImageURI(tokenBean.getLogoUrl());
                    DappDetailParam dappDetailParam = this.detailParam;
                    dappDetailParam.addInfoTitle(R.string.asset_transfer, this.detailParam.getAmount() + " " + this.tokenBean.getShortName());
                    break;
                } else {
                    this.detailParam.addIconResource(R.mipmap.icon_confirm_token_default);
                    DappDetailParam dappDetailParam2 = this.detailParam;
                    dappDetailParam2.addInfoTitle(R.string.asset_transfer, dappDetailParam2.getAmount());
                    break;
                }
            case 1:
                this.ivIconTag.setVisibility(View.VISIBLE);
                this.detailParam.addIconResource(R.mipmap.trx);
                this.resTitleId = R.string.asset_transfer;
                DappDetailParam dappDetailParam3 = this.detailParam;
                dappDetailParam3.addInfoTitle(R.string.asset_transfer, this.detailParam.getAmount() + " TRX");
                break;
            case 2:
                int tokenActionType = this.detailParam.getTokenActionType();
                LogUtils.e("tokenActionType  ", "tokenActionType=" + tokenActionType);
                this.ivIconTag.setVisibility(View.GONE);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.ivIcon.getLayoutParams();
                layoutParams.leftMargin = 0;
                this.ivIcon.setLayoutParams(layoutParams);
                if (tokenActionType == 0) {
                    this.ivIconTag.setVisibility(View.VISIBLE);
                    layoutParams.leftMargin = -13;
                    this.ivIcon.setLayoutParams(layoutParams);
                    String str2 = this.detailParam.getTriggerData().getParameterMap().get("1");
                    TokenBean tokenBean2 = this.tokenBean;
                    if (tokenBean2 != null) {
                        this.ivIcon.setImageURI(tokenBean2.getLogoUrl());
                        this.numberFormat.setMaximumFractionDigits(this.tokenBean.getPrecision());
                        BigDecimal bigDecimal = new BigDecimal(Math.pow(10.0d, this.tokenBean.getPrecision()));
                        String format = this.numberFormat.format(BigDecimalUtils.div(new BigDecimal(str2), bigDecimal));
                        DappDetailParam dappDetailParam4 = this.detailParam;
                        dappDetailParam4.addInfoTitle(R.string.asset_transfer, format + " " + this.tokenBean.getShortName());
                        break;
                    } else {
                        this.detailParam.addInfoTitle(R.string.asset_transfer, str2);
                        break;
                    }
                } else if (tokenActionType == 2) {
                    this.ivIconTag.setVisibility(View.VISIBLE);
                    layoutParams.leftMargin = -13;
                    this.ivIcon.setLayoutParams(layoutParams);
                    final String str3 = this.detailParam.getTriggerData().getParameterMap().get("2");
                    TokenBean tokenBean3 = this.tokenBean;
                    if (tokenBean3 != null) {
                        this.ivIcon.setImageURI(tokenBean3.getLogoUrl());
                    }
                    GenericDraweeHierarchy hierarchy = this.ivSubTitleIcon.getHierarchy();
                    hierarchy.setFailureImage(R.mipmap.ic_nft_default_2);
                    hierarchy.setPlaceholderImage(R.mipmap.ic_nft_default_2);
                    if (!StringTronUtil.isNullOrEmpty(str3)) {
                        DappDetailParam dappDetailParam5 = this.detailParam;
                        dappDetailParam5.addInfoTitle(R.string.asset_transfer, "#" + str3);
                        runOnThreeThread(new OnBackground() {
                            @Override
                            public final void doOnBackground() {
                                lambda$bindDataToUI$0(str3);
                            }
                        });
                        break;
                    } else {
                        this.detailParam.addInfoTitle(R.string.asset_transfer, "");
                        break;
                    }
                }
                break;
        }
        this.headerView.bindData(this.detailParam);
    }

    public void lambda$bindDataToUI$0(String str) {
        getCollectionInfo(this.detailParam.getOwnerAddress(), this.detailParam.getContractAddress(), str);
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
            DeepLinkDappTransferTransactionDetailFragment deepLinkDappTransferTransactionDetailFragment = this.detailFragment;
            if (deepLinkDappTransferTransactionDetailFragment != null) {
                beginTransaction.show(deepLinkDappTransferTransactionDetailFragment).hide(this.metadataFragment);
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
        this.ivClose.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                try {
                    AnalyticsHelper.logEventFormat(AnalyticsHelper.TransactionConfirmPopup.CLICK_CONFIRM_PAGE_BACK, Integer.valueOf(detailParam.getContractType() == Protocol.Transaction.Contract.ContractType.TriggerSmartContract.getNumber() ? 2 : 1));
                } catch (Exception e) {
                    LogUtils.e(e);
                }
                ((DeepLinkDappConfirmActivity) getActivity()).cancle();
            }
        });
    }

    private String getContractUrl(String str) {
        String contractUrl = IRequest.getContractUrl(str);
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return contractUrl + "?lang=zh";
        }
        return contractUrl + "?lang=en";
    }

    private String getTokenUrl(String str) {
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
        if (nftItemInfo == null) {
            return;
        }
        this.nftInfo = nftItemInfo;
        if (!StringTronUtil.isNullOrEmpty(nftItemInfo.getLogoUrl())) {
            this.ivSubTitleIcon.setImageURI(nftItemInfo.getLogoUrl());
        }
        String str = this.detailParam.getTriggerData().getParameterMap().get("2");
        String name = !StringTronUtil.isNullOrEmpty(nftItemInfo.getName()) ? nftItemInfo.getName() : "";
        DappDetailParam dappDetailParam = this.detailParam;
        dappDetailParam.addInfoTitle(R.string.asset_transfer, "#" + str + " " + name);
        this.headerView.bindData(this.detailParam);
    }

    public void mappingId() {
        this.llRoot = this.binding.root;
        this.ivClose = (LinearLayout) this.binding.getRoot().findViewById(R.id.iv_close);
        this.headerView = this.binding.headerView;
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
