package com.tron.wallet.business.walletmanager.pairwallet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.wallet.business.addassets2.WatchWalletVerifier;
import com.tron.wallet.business.walletmanager.detail.WalletDetailActivity;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.databinding.ActivityPairColdWalletExplainBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.functions.Consumer;
import org.tron.walletserver.Wallet;
public class PairColdWalletExplainActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    private ActivityPairColdWalletExplainBinding binding;
    TextView tvAddress;
    TextView tvGo;
    TextView tvWalletName;
    private boolean verified = false;

    public static void start(Context context) {
        ((Activity) context).startActivityForResult(new Intent(context, PairColdWalletExplainActivity.class), TronConfig.WATCH_UPDATE_COLD_REQUEST_CODE);
    }

    @Override
    protected void setLayout() {
        ActivityPairColdWalletExplainBinding inflate = ActivityPairColdWalletExplainBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 1);
        setHeaderBar(getString(R.string.pair_cold_wallet));
        mappingId();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.tvWalletName = this.binding.tvWalletName;
        this.tvAddress = this.binding.tvWalletAddress;
        this.tvGo = this.binding.btnGo;
    }

    @Override
    protected void processData() {
        AnalyticsHelper.logEvent(AnalyticsHelper.PairWatchColdWallet.WATCH_COLD_EDU_SHOW);
        this.tvWalletName.setText(WalletUtils.getSelectedWallet().getWalletName());
        this.tvAddress.setText(WalletUtils.getSelectedWallet().getAddress());
        this.tvGo.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.PairWatchColdWallet.WATCH_COLD_EDU_NEXT);
                WatchWalletVerifier.getInstance().confirmVerifyDirectly(mContext, WalletUtils.getSelectedWallet().getAddress());
            }
        });
        bindRxEvent();
    }

    private void bindRxEvent() {
        ((EmptyPresenter) this.mPresenter).mRxManager.on(Event.WATCH_WALLET_VERIFY_RESULT, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$bindRxEvent$0(obj);
            }
        });
    }

    public void lambda$bindRxEvent$0(Object obj) throws Exception {
        Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
        if (selectedPublicWallet == null || !selectedPublicWallet.isWatchOnly()) {
            return;
        }
        WatchWalletVerifier.WalletVerifyResult walletVerifyResult = (WatchWalletVerifier.WalletVerifyResult) obj;
        if (walletVerifyResult.isResult()) {
            if (walletVerifyResult.getAddress() == null || !TextUtils.equals(selectedPublicWallet.getAddress(), walletVerifyResult.getAddress())) {
                toast(getString(R.string.no_address));
                return;
            }
            WalletUtils.updateWatchWalletType(selectedPublicWallet.getWalletName(), 9);
            Intent intent = new Intent();
            intent.putExtra(WalletDetailActivity.WATCH_UPDATE_COLD_RESULT_SUCCESS, walletVerifyResult.isResult());
            setResult(-1, intent);
            this.verified = walletVerifyResult.isResult();
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        handleBack();
        super.onBackPressed();
    }

    private void handleBack() {
        if (this.verified) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(WalletDetailActivity.WATCH_UPDATE_COLD_RESULT_SUCCESS, false);
        setResult(-1, intent);
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }
}
