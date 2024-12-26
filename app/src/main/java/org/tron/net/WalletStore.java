package org.tron.net;
public class WalletStore {
    private String mnemonic;
    private String mnemonicEncrypted;
    private String privateKeyEncrypted;
    private String walletAddress;
    private String walletName;

    public String getMnemonic() {
        return this.mnemonic;
    }

    public String getMnemonicEncrypted() {
        return this.mnemonicEncrypted;
    }

    public String getPrivateKeyEncrypted() {
        return this.privateKeyEncrypted;
    }

    public String getWalletAddress() {
        return this.walletAddress;
    }

    public String getWalletName() {
        return this.walletName;
    }

    public void setMnemonic(String str) {
        this.mnemonic = str;
    }

    public void setMnemonicEncrypted(String str) {
        this.mnemonicEncrypted = str;
    }

    public void setPrivateKeyEncrypted(String str) {
        this.privateKeyEncrypted = str;
    }

    public void setWalletAddress(String str) {
        this.walletAddress = str;
    }

    public void setWalletName(String str) {
        this.walletName = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof WalletStore;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof WalletStore) {
            WalletStore walletStore = (WalletStore) obj;
            if (walletStore.canEqual(this)) {
                String walletAddress = getWalletAddress();
                String walletAddress2 = walletStore.getWalletAddress();
                if (walletAddress != null ? walletAddress.equals(walletAddress2) : walletAddress2 == null) {
                    String walletName = getWalletName();
                    String walletName2 = walletStore.getWalletName();
                    if (walletName != null ? walletName.equals(walletName2) : walletName2 == null) {
                        String privateKeyEncrypted = getPrivateKeyEncrypted();
                        String privateKeyEncrypted2 = walletStore.getPrivateKeyEncrypted();
                        if (privateKeyEncrypted != null ? privateKeyEncrypted.equals(privateKeyEncrypted2) : privateKeyEncrypted2 == null) {
                            String mnemonicEncrypted = getMnemonicEncrypted();
                            String mnemonicEncrypted2 = walletStore.getMnemonicEncrypted();
                            if (mnemonicEncrypted != null ? mnemonicEncrypted.equals(mnemonicEncrypted2) : mnemonicEncrypted2 == null) {
                                String mnemonic = getMnemonic();
                                String mnemonic2 = walletStore.getMnemonic();
                                return mnemonic != null ? mnemonic.equals(mnemonic2) : mnemonic2 == null;
                            }
                            return false;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        String walletAddress = getWalletAddress();
        int hashCode = walletAddress == null ? 43 : walletAddress.hashCode();
        String walletName = getWalletName();
        int hashCode2 = ((hashCode + 59) * 59) + (walletName == null ? 43 : walletName.hashCode());
        String privateKeyEncrypted = getPrivateKeyEncrypted();
        int hashCode3 = (hashCode2 * 59) + (privateKeyEncrypted == null ? 43 : privateKeyEncrypted.hashCode());
        String mnemonicEncrypted = getMnemonicEncrypted();
        int hashCode4 = (hashCode3 * 59) + (mnemonicEncrypted == null ? 43 : mnemonicEncrypted.hashCode());
        String mnemonic = getMnemonic();
        return (hashCode4 * 59) + (mnemonic != null ? mnemonic.hashCode() : 43);
    }

    public String toString() {
        return "WalletStore(walletAddress=" + getWalletAddress() + ", walletName=" + getWalletName() + ", privateKeyEncrypted=" + getPrivateKeyEncrypted() + ", mnemonicEncrypted=" + getMnemonicEncrypted() + ", mnemonic=" + getMnemonic() + ")";
    }
}
