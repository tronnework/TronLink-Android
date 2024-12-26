package com.tron.wallet.business.walletmanager.pairwallet;

import android.content.Context;
import android.view.View;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.CenterPopupView;
import com.lxj.xpopup.util.XPopupUtils;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tronlinkpro.wallet.R;
public class PairColdWalletDialog extends CenterPopupView {
    private final PairResultCallback resultCallback;

    public interface PairResultCallback {
        void onPairColdResult(boolean z);
    }

    @Override
    public int getImplLayoutId() {
        return R.layout.dialog_pair_cold_wallet_warning;
    }

    public PairColdWalletDialog(Context context, PairResultCallback pairResultCallback) {
        super(context);
        this.resultCallback = pairResultCallback;
    }

    @Override
    public int getMaxWidth() {
        return XPopupUtils.getScreenWidth(getContext());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        findViewById(R.id.btn_confirm).setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.PairWatchColdWallet.WATCH_COLD_PAIR_POP_CLICK_CONFIRM);
                dismiss();
                if (resultCallback != null) {
                    resultCallback.onPairColdResult(false);
                }
            }
        });
        findViewById(R.id.ll_enter_pair).setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.PairWatchColdWallet.WATCH_COLD_PAIR_POP_CLICK_PAIR);
                PairColdWalletExplainActivity.start(view.getContext());
                dismiss();
            }
        });
    }

    public static void showUp(Context context, PairResultCallback pairResultCallback) {
        AnalyticsHelper.logEvent(AnalyticsHelper.PairWatchColdWallet.WATCH_COLD_PAIR_POP_SHOW);
        new XPopup.Builder(context).dismissOnTouchOutside(false).asCustom(new PairColdWalletDialog(context, pairResultCallback)).show();
    }
}
