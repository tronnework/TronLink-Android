package com.tron.wallet.common.utils.rb;

import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.common.bean.Price;
public enum RbUtil {
    THIS;
    
    RxManager mRxManager = new RxManager();

    RbUtil() {
    }

    public void sendPrice(Price price) {
        this.mRxManager.post(RB.RB_PRICE, price);
    }

    public void sendTime() {
        this.mRxManager.post(RB.RB_TIME, "33");
    }

    public void sendBlockchain() {
        this.mRxManager.post(RB.RB_BLOCKCHAIN, "");
    }

    public void sendAccounts() {
        this.mRxManager.postSticky(RB.RB_ACCOUNTS, "111");
    }

    public void sendShieldAccounts() {
        this.mRxManager.post(RB.RB_SHIELD_ACCOUNTS, "111");
    }

    public void sendUpdateShieldAccounts() {
        this.mRxManager.postSticky(RB.RB_UPDATE_SHIELD_ACCOUNTS, "111");
    }

    public void sendNodes() {
        this.mRxManager.post(RB.RB_NODES, "");
    }

    public void sendSyncBlock(String str) {
        this.mRxManager.post(RB.RB_SYNC_BLOCK, str);
    }

    public void sendWitnesses() {
        this.mRxManager.post(RB.RB_WITNESSES, "");
    }

    public void sendTokens() {
        this.mRxManager.post(RB.RB_TOKENS, "");
    }

    public void clean() {
        RxManager rxManager = this.mRxManager;
        if (rxManager != null) {
            rxManager.clear();
        }
    }
}
