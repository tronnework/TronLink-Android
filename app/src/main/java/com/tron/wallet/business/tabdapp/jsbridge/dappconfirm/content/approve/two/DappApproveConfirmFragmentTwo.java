package com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.approve.two;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.fragment.app.FragmentTransaction;
import com.google.protobuf.InvalidProtocolBufferException;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.fg.BaseApproveFragment;
import com.tron.wallet.business.confirm.fg.component.ArgumentKeys;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.DappDetailParam;
import com.tron.wallet.business.confirm.parambuilder.bean.DappMetadataParam;
import com.tron.wallet.business.pull.dappconfirm.content.DeepLinkDappConfirmActivity;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.DappConfirmNewActivity;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.approve.DappApproveConfirmFragment;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.approve.DappApproveTransactionDetailFragment;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.approve.two.DappApproveConfirmTwoContract;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.tab.TransactionMetadataFragment;
import com.tron.wallet.common.components.xtablayout.XTabLayout;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.transaction.TriggerUtils;
import com.tron.wallet.databinding.FgDappConfirmApprove2Binding;
import com.tronlinkpro.wallet.R;
import org.tron.protos.Protocol;
public class DappApproveConfirmFragmentTwo extends BaseApproveFragment<DappApproveConfirmTwoPresenter, DappApproveConfirmTwoModel> implements DappApproveConfirmTwoContract.View, XTabLayout.OnTabSelectedListener {
    private FgDappConfirmApprove2Binding binding;
    DappApproveTransactionDetailFragment detailFragment;
    private DappDetailParam detailParam;
    private DappMetadataParam metaParam;
    TransactionMetadataFragment metadataFragment;
    XTabLayout tabLayout;

    @Override
    public void onTabReselected(XTabLayout.Tab tab) {
    }

    @Override
    public void onTabUnselected(XTabLayout.Tab tab) {
    }

    public static DappApproveConfirmFragmentTwo getInstance(BaseParam baseParam, BaseParam baseParam2) {
        DappApproveConfirmFragmentTwo dappApproveConfirmFragmentTwo = new DappApproveConfirmFragmentTwo();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ArgumentKeys.KEY_PARAM_DETAIL, baseParam);
        bundle.putParcelable(ArgumentKeys.KEY_PARAM_META, baseParam2);
        bundle.putParcelable(ArgumentKeys.KEY_PARAM, baseParam2);
        dappApproveConfirmFragmentTwo.setArguments(bundle);
        return dappApproveConfirmFragmentTwo;
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
        this.detailFragment = DappApproveTransactionDetailFragment.getInstance(this.detailParam);
        FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
        if (this.metadataFragment != null) {
            beginTransaction.add(R.id.content, this.detailFragment, "0");
            beginTransaction.add(R.id.content, this.metadataFragment, "1");
            beginTransaction.show(this.detailFragment).hide(this.metadataFragment);
            beginTransaction.disallowAddToBackStack();
            beginTransaction.commit();
        }
        try {
            if (getActivity() instanceof DappConfirmNewActivity) {
                addAccountCallback(this.detailFragment, ((DappConfirmNewActivity) getActivity()).getConfirmBtn());
            } else if (getActivity() instanceof DeepLinkDappConfirmActivity) {
                addAccountCallback(this.detailFragment, ((DeepLinkDappConfirmActivity) getActivity()).getConfirmBtn());
            }
            ensureAccount();
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgDappConfirmApprove2Binding inflate = FgDappConfirmApprove2Binding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId() {
        this.tabLayout = this.binding.tablayout;
    }

    @Override
    public void onTabSelected(XTabLayout.Tab tab) {
        TransactionMetadataFragment transactionMetadataFragment;
        FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
        LogUtils.e("onTabSelected", String.valueOf(tab.getPosition()));
        int position = tab.getPosition();
        if (position == 0) {
            DappApproveTransactionDetailFragment dappApproveTransactionDetailFragment = this.detailFragment;
            if (dappApproveTransactionDetailFragment != null) {
                beginTransaction.show(dappApproveTransactionDetailFragment).hide(this.metadataFragment);
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

    public void updateMetaData(String str, String str2) {
        try {
            Protocol.Transaction parseFrom = Protocol.Transaction.parseFrom(this.metaParam.getTransactionBean().getBytes().get(0));
            this.metaParam.clearThenAddTransaction(parseFrom);
            this.metaParam.addTransaction(TriggerUtils.replaceApproveAmount(parseFrom, str, str2));
            this.metaParam.getTriggerData().getParameterMap().put("1", str2);
            this.metadataFragment.setParam(this.metaParam);
        } catch (InvalidProtocolBufferException e) {
            LogUtils.e(e);
        }
    }

    public void updateDetail(DappDetailParam dappDetailParam, boolean z) {
        dappDetailParam.setDetail(z);
        this.detailFragment.setParam(dappDetailParam);
        this.detailFragment.updateDetail(dappDetailParam);
        ensureAccount();
    }

    @Override
    public void switchToOne() {
        ((DappApproveConfirmFragment) getParentFragment()).switchOne();
    }
}
