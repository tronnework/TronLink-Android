package com.tron.wallet.business.assetshome.tipview;
public enum TipViewType {
    MULTI_SIGN("multiSign", 0),
    MNEMONIC_BACKUP("mnemonicBackup", 1),
    MULTI_PERMISSION("multiPermission", 2),
    SEC_ASK("secAsk", 3);
    
    private String id;
    private int priority;

    public String getId() {
        return this.id;
    }

    public int getPriority() {
        return this.priority;
    }

    TipViewType(String str, int i) {
        this.id = str;
        this.priority = i;
    }
}
