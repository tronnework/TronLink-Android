package com.tron.wallet.business.walletmanager.backup;

import android.os.Bundle;
import com.tron.wallet.common.config.TronConfig;
public class ShieldInfoBean {
    String addr;
    String ak;
    String ask;
    String ivk;
    String nk;
    String nsk;
    String ovk;
    String pubKey;
    String sk;

    public void prepareData(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.pubKey = bundle.getString(TronConfig.PK_PUB_KEY);
        this.sk = bundle.getString(TronConfig.PK_SK);
        this.ask = bundle.getString(TronConfig.PK_ASK);
        this.nsk = bundle.getString(TronConfig.PK_NSK);
        this.nk = bundle.getString(TronConfig.PK_NK);
        this.ak = bundle.getString(TronConfig.PK_AK);
        this.ivk = bundle.getString(TronConfig.PK_IVK);
        this.ovk = bundle.getString(TronConfig.PK_OVK);
        this.addr = bundle.getString("shield_address");
    }
}
