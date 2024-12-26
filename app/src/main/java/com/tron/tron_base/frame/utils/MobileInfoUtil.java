package com.tron.tron_base.frame.utils;

import com.tron.tron_base.R;
import java.util.UUID;
public class MobileInfoUtil {
    public static String getMacAddress() {
        String str = (String) SpUtils.getParam("f_TronKey", AppContextUtil.getContext(), AppContextUtil.getContext().getString(R.string.mac_address), "");
        if (StringTronUtil.isEmpty(str)) {
            if (StringTronUtil.isEmpty(str)) {
                str = UUID.randomUUID().toString();
            }
            SpUtils.setParam("f_TronKey", AppContextUtil.getContext(), AppContextUtil.getContext().getString(R.string.mac_address), str);
            return str;
        }
        return str;
    }
}
