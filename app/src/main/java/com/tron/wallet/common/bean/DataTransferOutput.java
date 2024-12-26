package com.tron.wallet.common.bean;

import com.tron.wallet.db.bean.HdTronRelationshipBean;
import java.util.List;
public class DataTransferOutput {
    public List<HdTronRelationshipBean> dbData;
    public String selectWalletName;
    public List<WalletData> walletData;
    public int walletNameIndex;

    public static class WalletData {
        public boolean backup_key;
        public boolean is_watch_only_setup_key;
        public boolean isshield_key;
        public int mnemonic_length;
        public String pub_key;
        public String shield__ob_key;
        public String wallet_address_key;
        public long wallet_createtime_key;
        public int wallet_createtype_key;
        public String wallet_keystore_key;
        public String wallet_mnemonicpath_key;
        public String wallet_name_key;
        public String wallet_newmnemonic_key;
    }
}
