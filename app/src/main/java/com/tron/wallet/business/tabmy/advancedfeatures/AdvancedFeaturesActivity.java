package com.tron.wallet.business.tabmy.advancedfeatures;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.wallet.business.tabmy.ConvertMnemonicActivity;
import com.tron.wallet.business.tabmy.advancedfeatures.export.ExportWatchWalletActivity;
import com.tron.wallet.business.tabmy.myhome.DappBrowserActivity;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.business.web.WebOptions;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.ActivityAdvancedFeaturesBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import org.tron.walletserver.Wallet;
public class AdvancedFeaturesActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    private ActivityAdvancedFeaturesBinding binding;
    HorizontalOptionView commitProposalView;
    HorizontalOptionView convertView;
    HorizontalOptionView dappView;
    View exportWallet;

    @Override
    protected void setLayout() {
        ActivityAdvancedFeaturesBinding inflate = ActivityAdvancedFeaturesBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        LinearLayout root = inflate.getRoot();
        mappingId();
        setClick();
        setView(root, 1);
        setHeaderBar(getString(R.string.advanced_features));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.commitProposalView = this.binding.commitProposal;
        this.dappView = this.binding.dapp;
        this.convertView = this.binding.convertTool;
        this.exportWallet = this.binding.exportWallet;
    }

    @Override
    protected void processData() {
        if (SpAPI.THIS.isCold()) {
            this.commitProposalView.setVisibility(View.GONE);
            this.exportWallet.setVisibility(View.GONE);
        }
        if (IRequest.isNile() || IRequest.isShasta()) {
            this.commitProposalView.setVisibility(View.GONE);
        }
    }

    public void setClick() {
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                switch (view.getId()) {
                    case R.id.commit_proposal:
                        AnalyticsHelper.logEvent(AnalyticsHelper.AdvancedFeatures.CLICK_ADVANCED_FEATURES_COMMITTEE_PROPOSAL);
                        doCommitProposal();
                        return;
                    case R.id.convert_tool:
                        AnalyticsHelper.logEvent(AnalyticsHelper.AdvancedFeatures.CLICK_ADVANCED_FEATURES_MNEMONIC_CONVERSION_TOOL);
                        go(ConvertMnemonicActivity.class);
                        return;
                    case R.id.dapp:
                        AnalyticsHelper.logEvent(AnalyticsHelper.AdvancedFeatures.CLICK_ADVANCED_FEATURES_DAPP_TESTING_TOOL);
                        go(DappBrowserActivity.class);
                        return;
                    case R.id.export_wallet:
                        AnalyticsHelper.logEvent(AnalyticsHelper.AdvancedFeatures.CLICK_ADVANCED_FEATURES_EXPORT_WALLET);
                        go(ExportWatchWalletActivity.class);
                        return;
                    default:
                        return;
                }
            }
        };
        this.binding.commitProposal.setOnClickListener(noDoubleClickListener2);
        this.binding.dapp.setOnClickListener(noDoubleClickListener2);
        this.binding.convertTool.setOnClickListener(noDoubleClickListener2);
        this.binding.exportWallet.setOnClickListener(noDoubleClickListener2);
    }

    public void doCommitProposal() {
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        if (selectedWallet != null && selectedWallet.isShieldedWallet()) {
            toast(getString(R.string.shield_wallet_can_not_use_this_account));
            return;
        }
        String resString = StringTronUtil.getResString(R.string.committee_proposals);
        CommonWebActivityV3.start((Context) this, IRequest.getProposalsUrl(), resString, true, new WebOptions.WebOptionsBuild().addCode(-2).addTitle(resString).build());
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        AnalyticsHelper.logEvent(AnalyticsHelper.AdvancedFeatures.CLICK_ADVANCED_FEATURES_CLICK_BACK);
        finish();
    }
}
