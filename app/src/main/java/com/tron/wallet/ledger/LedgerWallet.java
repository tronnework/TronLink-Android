package com.tron.wallet.ledger;

import android.text.TextUtils;
import org.tron.walletserver.Wallet;
public class LedgerWallet {
    public static boolean isLedger(Wallet wallet) {
        return wallet != null && 8 == wallet.getCreateType();
    }

    public Wallet createWallet(Builder builder) {
        Wallet wallet = new Wallet();
        wallet.setAddress(builder.address);
        wallet.setWatchOnly(true);
        wallet.setCreateType(8);
        if (builder.pathJson != null) {
            wallet.setMnemonicPath(builder.pathJson);
        }
        wallet.setWalletName(builder.name);
        wallet.setColor(builder.color);
        wallet.setCreateTime(builder.createTime);
        return wallet;
    }

    public static class Builder {
        private String address = "";
        private int color;
        private long createTime;
        private String name;
        private String pathJson;

        public Builder setAddress(String str) {
            this.address = str;
            return this;
        }

        public Builder setColor(int i) {
            this.color = i;
            return this;
        }

        public Builder setCreateTime(long j) {
            this.createTime = j;
            return this;
        }

        public Builder setName(String str) {
            if (str == null) {
                this.name = "";
            } else {
                this.name = str;
            }
            return this;
        }

        public Builder setPathJson(String str) {
            if (TextUtils.isEmpty(str)) {
                str = "{}";
            }
            this.pathJson = str;
            return this;
        }

        public Wallet build() {
            return new LedgerWallet().createWallet(this);
        }
    }
}
