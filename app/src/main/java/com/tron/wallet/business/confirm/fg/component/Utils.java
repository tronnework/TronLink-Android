package com.tron.wallet.business.confirm.fg.component;

import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.ManagePermissionGroupParam;
import com.tron.wallet.business.confirm.parambuilder.bean.ParticipateMultisignParam;
import com.tron.wallet.common.utils.AddressNameUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.wallet.WalletUtils;
import org.tron.walletserver.Wallet;
public class Utils {
    public static String getWalletNameFromParam(BaseParam baseParam) {
        Wallet selectedWallet;
        if (baseParam instanceof ParticipateMultisignParam) {
            selectedWallet = WalletUtils.getWallet(((ParticipateMultisignParam) baseParam).getWalletName());
        } else if (baseParam instanceof ManagePermissionGroupParam) {
            selectedWallet = WalletUtils.getWallet(((ManagePermissionGroupParam) baseParam).getWalletName());
        } else if (baseParam != null && baseParam.getQrBean() != null && !StringTronUtil.isNullOrEmpty(baseParam.getQrBean().getAddress())) {
            selectedWallet = WalletUtils.getWalletForAddress(baseParam.getQrBean().getAddress());
        } else {
            selectedWallet = WalletUtils.getSelectedWallet();
        }
        return selectedWallet != null ? selectedWallet.getWalletName() : "";
    }

    public static String getNameByAddress(String str) {
        return AddressNameUtils.getInstance().getNameByAddress(str);
    }

    public static String getNameByAddress(String str, boolean z) {
        return AddressNameUtils.getInstance().getNameByAddress(str, z);
    }
}
