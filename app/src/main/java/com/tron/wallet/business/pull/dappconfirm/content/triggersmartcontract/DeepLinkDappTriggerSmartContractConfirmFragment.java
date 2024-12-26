package com.tron.wallet.business.pull.dappconfirm.content.triggersmartcontract;

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
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
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
import com.tron.wallet.business.pull.dappconfirm.tab.DeepLinkTransactionMetadataFragment;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.triggersmartcontract.DappTriggerSmartContractConfirmContract;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.xtablayout.XTabLayout;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.FgDappConfirmSystemContractBinding;
import com.tron.wallet.net.HttpAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.disposables.Disposable;
import java.text.NumberFormat;
import java.util.Locale;
import org.tron.protos.Protocol;
public class DeepLinkDappTriggerSmartContractConfirmFragment extends BaseConfirmFragment<DeepLinkDappTriggerSmartContractConfirmPresenter, DeepLinkDappTriggerSmartContractConfirmModel> implements DappTriggerSmartContractConfirmContract.View, XTabLayout.OnTabSelectedListener {
    RelativeLayout approveLayout;
    private FgDappConfirmSystemContractBinding binding;
    String contractTypeString;
    DeepLinkDappTriggerSmartContractDetailFragment detailFragment;
    private DappDetailParam detailParam;
    GlobalTitleHeaderView headerView;
    SimpleDraweeView ivApproveIcon;
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
    TextView tvApproveInfo;
    TextView tvApproveTokenName;

    @Override
    public void onTabReselected(XTabLayout.Tab tab) {
    }

    @Override
    public void onTabUnselected(XTabLayout.Tab tab) {
    }

    public static DeepLinkDappTriggerSmartContractConfirmFragment getInstance(BaseParam baseParam, BaseParam baseParam2) {
        DeepLinkDappTriggerSmartContractConfirmFragment deepLinkDappTriggerSmartContractConfirmFragment = new DeepLinkDappTriggerSmartContractConfirmFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ArgumentKeys.KEY_PARAM_DETAIL, baseParam);
        bundle.putParcelable(ArgumentKeys.KEY_PARAM_META, baseParam2);
        bundle.putParcelable(ArgumentKeys.KEY_PARAM, baseParam2);
        deepLinkDappTriggerSmartContractConfirmFragment.setArguments(bundle);
        return deepLinkDappTriggerSmartContractConfirmFragment;
    }

    @Override
    public void processData() {
        super.processData();
        mappingId();
        setClick();
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
        this.detailFragment = DeepLinkDappTriggerSmartContractDetailFragment.getInstance(this.detailParam);
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
        int tokenActionType = this.detailParam.getTokenActionType();
        LogUtils.e("tokenActionType  ", "tokenActionType=" + tokenActionType);
        this.ivIconTag.setVisibility(View.GONE);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.ivIcon.getLayoutParams();
        layoutParams.leftMargin = 0;
        this.ivIcon.setLayoutParams(layoutParams);
        this.detailParam.addIconResource(R.mipmap.icon_dapp_trigger_smart_contract);
        this.detailParam.addInfoTitle(R.string.dapp_trigger_title, "");
        this.headerView.bindData(this.detailParam);
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgDappConfirmSystemContractBinding inflate = FgDappConfirmSystemContractBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        return inflate.getRoot();
    }

    @Override
    public void onTabSelected(XTabLayout.Tab tab) {
        DeepLinkTransactionMetadataFragment deepLinkTransactionMetadataFragment;
        FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
        LogUtils.e("onTabSelected", String.valueOf(tab.getPosition()));
        int position = tab.getPosition();
        if (position == 0) {
            DeepLinkDappTriggerSmartContractDetailFragment deepLinkDappTriggerSmartContractDetailFragment = this.detailFragment;
            if (deepLinkDappTriggerSmartContractDetailFragment != null) {
                beginTransaction.show(deepLinkDappTriggerSmartContractDetailFragment).hide(this.metadataFragment);
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
        this.approveLayout = (RelativeLayout) this.binding.getRoot().findViewById(R.id.approve_layout);
        this.tvApproveInfo = (TextView) this.binding.getRoot().findViewById(R.id.approve_info);
        this.ivApproveIcon = (SimpleDraweeView) this.binding.getRoot().findViewById(R.id.iv_approve_icon);
        this.tvApproveTokenName = (TextView) this.binding.getRoot().findViewById(R.id.dapp_approve_token_name);
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
