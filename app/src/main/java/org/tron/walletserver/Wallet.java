package org.tron.walletserver;

import java.io.Serializable;
import java.math.BigInteger;
import org.tron.common.bip32.Bip32ECKeyPair;
import org.tron.common.crypto.ECKey;
import org.tron.common.crypto.MnemonicUtils;
import org.tron.common.utils.ByteArray;
import org.tron.common.utils.GsonFormatUtils;
import org.tron.common.utils.Utils;
public class Wallet implements Comparable<Wallet>, Serializable {
    private static final String TAG = "Wallet";
    private static final long serialVersionUID = Wallet.class.hashCode();
    public String address;
    private int color;
    private long createTime;
    private int createType;
    private String encPassword;
    private byte[] encPrivateKey;
    private String iconRes;
    private boolean isBackUp;
    private boolean isSamsungWallet;
    private boolean isShieldedWallet;
    private boolean isWatchOnly;
    private String keyStore;
    private ECKey mECKey;
    private String mnemonic;
    private int mnemonicLength;
    private String mnemonicPath;
    private byte[] privateKeyBytes33;
    private byte[] publicKey;
    private String seedHash;
    private String walletName;

    public int getColor() {
        return this.color;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public int getCreateType() {
        return this.createType;
    }

    public ECKey getECKey() {
        return this.mECKey;
    }

    public String getEncryptedPassword() {
        return this.encPassword;
    }

    public byte[] getEncryptedPrivateKey() {
        return this.encPrivateKey;
    }

    public String getIconRes() {
        return this.iconRes;
    }

    public String getKeyStore() {
        return this.keyStore;
    }

    public String getMnemonic() {
        return this.mnemonic;
    }

    public int getMnemonicLength() {
        return this.mnemonicLength;
    }

    public String getMnemonicPathString() {
        return this.mnemonicPath;
    }

    public byte[] getPrivateKeyBytes33() {
        return this.privateKeyBytes33;
    }

    public String getSeedHash() {
        return this.seedHash;
    }

    public String getWalletName() {
        return this.walletName;
    }

    public boolean isBackUp() {
        return this.isBackUp;
    }

    public boolean isLedgerHDWallet() {
        return this.createType == 8;
    }

    public boolean isSamsungWallet() {
        return this.createType == 7;
    }

    public boolean isShieldedWallet() {
        return this.isShieldedWallet;
    }

    public boolean isWatchOnly() {
        return this.isWatchOnly;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setBackUp(boolean z) {
        this.isBackUp = z;
    }

    public void setColor(int i) {
        this.color = i;
    }

    public void setCreateTime(long j) {
        this.createTime = j;
    }

    public void setCreateType(int i) {
        this.createType = i;
    }

    public void setEncryptedPassword(String str) {
        this.encPassword = str;
    }

    public void setEncryptedPrivateKey(byte[] bArr) {
        this.encPrivateKey = bArr;
    }

    public void setIconRes(String str) {
        this.iconRes = str;
    }

    public void setKeyStore(String str) {
        this.keyStore = str;
    }

    public void setMnemonic(String str) {
        this.mnemonic = str;
    }

    public void setMnemonicLength(int i) {
        this.mnemonicLength = i;
    }

    public void setMnemonicPath(String str) {
        this.mnemonicPath = str;
    }

    public void setPublicKey(byte[] bArr) {
        this.publicKey = bArr;
    }

    public void setSamsungWallet(boolean z) {
        this.isSamsungWallet = z;
    }

    public void setSeedHash(String str) {
        this.seedHash = str;
    }

    public void setShieldedWallet(boolean z) {
        this.isShieldedWallet = z;
    }

    public void setWalletName(String str) {
        this.walletName = str;
    }

    public void setWatchOnly(boolean z) {
        this.isWatchOnly = z;
    }

    public Wallet() {
        this.mECKey = null;
        this.isWatchOnly = false;
        this.isShieldedWallet = false;
        this.walletName = "";
        this.encPassword = "";
        this.address = "";
        this.createType = -1;
        this.color = -1;
        this.isSamsungWallet = false;
    }

    public Wallet(boolean z) {
        this.mECKey = null;
        this.isWatchOnly = false;
        this.isShieldedWallet = false;
        this.walletName = "";
        this.encPassword = "";
        this.address = "";
        this.createType = -1;
        this.color = -1;
        this.isSamsungWallet = false;
        if (z) {
            byte[] bArr = new byte[16];
            Utils.getRandom().nextBytes(bArr);
            String generateMnemonic = MnemonicUtils.generateMnemonic(bArr);
            this.mnemonic = generateMnemonic;
            byte[] privateKeyBytes33 = generateBip44KeyPair(Bip32ECKeyPair.generateKeyPair(MnemonicUtils.generateSeed(generateMnemonic, null))).getPrivateKeyBytes33();
            this.privateKeyBytes33 = privateKeyBytes33;
            this.mECKey = ECKey.fromPrivate(privateKeyBytes33);
        }
    }

    public Wallet(I_TYPE i_type, String str) {
        this.mECKey = null;
        this.isWatchOnly = false;
        this.isShieldedWallet = false;
        this.walletName = "";
        this.encPassword = "";
        this.address = "";
        this.createType = -1;
        this.color = -1;
        this.isSamsungWallet = false;
        if (i_type == I_TYPE.PRIVATE) {
            generateKeyForPrivateKey(str);
            this.privateKeyBytes33 = ByteArray.fromHexString(str);
        } else if (i_type == I_TYPE.MNEMONIC) {
            generateKeyForMnemonic(str);
        }
    }

    public Wallet(String str, WalletPath walletPath) {
        this.mECKey = null;
        this.isWatchOnly = false;
        this.isShieldedWallet = false;
        this.walletName = "";
        this.encPassword = "";
        this.address = "";
        this.createType = -1;
        this.color = -1;
        this.isSamsungWallet = false;
        if (AddressUtil.isEmpty(str) || walletPath == null) {
            return;
        }
        generateKeyForMnemonic(str, walletPath.purpose, walletPath.coinType, walletPath.account, walletPath.change, walletPath.accountIndex);
        ECKey eCKey = this.mECKey;
        if (eCKey == null || !eCKey.hasPrivKey()) {
            return;
        }
        setMnemonicPath(GsonFormatUtils.toGsonString(walletPath));
        setMnemonic(str);
    }

    public boolean isOpen() {
        ECKey eCKey = this.mECKey;
        return (eCKey == null || eCKey.getPrivKeyBytes() == null) ? false : true;
    }

    public byte[] getPublicKey() {
        ECKey eCKey = this.mECKey;
        return eCKey != null ? eCKey.getPubKey() : this.publicKey;
    }

    public byte[] getPrivateKey() {
        ECKey eCKey = this.mECKey;
        if (eCKey != null) {
            return eCKey.getPrivKeyBytes();
        }
        return null;
    }

    public void generateKeyForPrivateKey(String str) {
        ECKey eCKey = null;
        if (str == null || str.isEmpty()) {
            this.mECKey = null;
            return;
        }
        try {
            eCKey = ECKey.fromPrivate(new BigInteger(str, 16));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.mECKey = eCKey;
    }

    public void generateKeyForMnemonic(String str) {
        generateKeyForMnemonic(str, 44, 195, 0, 0, 0);
    }

    public void generateKeyForMnemonic(String str, int i, int i2, int i3, int i4, int i5) {
        ECKey eCKey = null;
        if (str == null || str.isEmpty()) {
            this.mECKey = null;
            return;
        }
        try {
            byte[] privateKeyBytes33 = generateBip44KeyPair(Bip32ECKeyPair.generateKeyPair(MnemonicUtils.generateSeed(str, null)), i, i2, i3, i4, i5).getPrivateKeyBytes33();
            this.privateKeyBytes33 = privateKeyBytes33;
            eCKey = ECKey.fromPrivate(privateKeyBytes33);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.mECKey = eCKey;
    }

    public boolean isWatchCold() {
        return this.isWatchOnly && getCreateType() == 9;
    }

    public boolean isWatchNotPaired() {
        return (!this.isWatchOnly || getCreateType() == 8 || getCreateType() == 9 || getCreateType() == 7) ? false : true;
    }

    public String getAddress() {
        if (this.mECKey == null || !AddressUtil.isEmpty(this.address)) {
            return (this.publicKey == null || !AddressUtil.isEmpty(this.address)) ? this.address : AddressUtil.encode58Check(ECKey.fromPublicOnly(this.publicKey).getAddress());
        }
        return AddressUtil.encode58Check(this.mECKey.getAddress());
    }

    public byte[] getDecode58CheckAddress() {
        if (this.mECKey != null && AddressUtil.isEmpty(this.address)) {
            return this.mECKey.getAddress();
        }
        if (this.publicKey != null && AddressUtil.isEmpty(this.address)) {
            return ECKey.fromPublicOnly(this.publicKey).getAddress();
        }
        return AddressUtil.decode58Check(this.address);
    }

    public Bip32ECKeyPair generateBip44KeyPair(Bip32ECKeyPair bip32ECKeyPair, int i, int i2, int i3, int i4, int i5) {
        return Bip32ECKeyPair.deriveKeyPair(bip32ECKeyPair, new int[]{i | Integer.MIN_VALUE, i2 | Integer.MIN_VALUE, i3 | Integer.MIN_VALUE, i4, i5});
    }

    public Bip32ECKeyPair generateBip44KeyPair(Bip32ECKeyPair bip32ECKeyPair) {
        return generateBip44KeyPair(bip32ECKeyPair, 44, 195, 0, 0, 0);
    }

    @Override
    public int compareTo(Wallet wallet) {
        if (this.createTime == wallet.getCreateTime()) {
            return 0;
        }
        return this.createTime > wallet.getCreateTime() ? 1 : -1;
    }
}
