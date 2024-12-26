package com.tron.wallet.business.tabmy.myhome.adapter;

import android.view.View;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.rb.RB;
public class DappSearchHisBottomHolder extends BaseHolder {
    private final RxManager rxManager;

    public DappSearchHisBottomHolder(View view) {
        super(view);
        this.rxManager = new RxManager();
        view.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view2) {
                rxManager.post(RB.RB_DAPP_CLEAR, "111");
            }
        });
    }

    public void onDestroy() {
        RxManager rxManager = this.rxManager;
        if (rxManager != null) {
            rxManager.clear();
        }
    }
}
