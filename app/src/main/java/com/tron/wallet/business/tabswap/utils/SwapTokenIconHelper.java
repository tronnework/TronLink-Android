package com.tron.wallet.business.tabswap.utils;

import android.content.Context;
import android.text.TextUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.tabswap.bean.SwapWhiteListOutput;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.db.Controller.AssetsHomeDataDaoManager;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.util.HashMap;
import java.util.Map;
import org.tron.walletserver.Wallet;
public class SwapTokenIconHelper {
    private static SwapTokenIconHelper instance;
    private final Map<String, String> symbolIcons = new HashMap();

    private SwapTokenIconHelper() {
    }

    public static SwapTokenIconHelper getInstance() {
        if (instance == null) {
            synchronized (SwapTokenIconHelper.class) {
                instance = new SwapTokenIconHelper();
            }
        }
        return instance;
    }

    public Object findTokenIcon(Context context, String str, String str2) {
        if (TextUtils.equals(str.toUpperCase(), "TRX")) {
            return Integer.valueOf((int) R.mipmap.trx);
        }
        if (this.symbolIcons.containsKey(str2)) {
            return this.symbolIcons.get(str2);
        }
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        if (selectedWallet == null) {
            return "";
        }
        try {
            SwapWhiteListOutput read = SwapCacheUtils.getInstance().read(context);
            if (read != null && read.getTokens() != null) {
                for (SwapWhiteListOutput.Data data : read.getTokens()) {
                    if (TextUtils.equals(data.getAddress(), str2)) {
                        this.symbolIcons.put(str2, data.getLogoURI());
                        return data.getLogoURI();
                    }
                }
            }
            TokenBean token = AssetsHomeDataDaoManager.getToken(selectedWallet.getAddress(), str2);
            if (token == null) {
                return "";
            }
            String logoUrl = token.getLogoUrl();
            this.symbolIcons.put(str2, logoUrl);
            return logoUrl;
        } catch (Exception e) {
            LogUtils.e(e);
            return "";
        }
    }
}
