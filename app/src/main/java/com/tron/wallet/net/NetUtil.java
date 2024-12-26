package com.tron.wallet.net;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tronlinkpro.wallet.R;
public class NetUtil {
    public static String getNetName() {
        if (IRequest.isNile() || IRequest.isTest()) {
            return AppContextUtil.getContext().getString(R.string.nile_net);
        }
        if (IRequest.isShasta()) {
            return AppContextUtil.getContext().getString(R.string.shasta_net);
        }
        return AppContextUtil.getContext().getString(R.string.main_chain);
    }
}
