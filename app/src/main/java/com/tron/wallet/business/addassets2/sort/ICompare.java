package com.tron.wallet.business.addassets2.sort;

import android.text.TextUtils;
import com.tron.wallet.common.bean.token.TokenBean;
public interface ICompare {
    int compare(TokenBean tokenBean, TokenBean tokenBean2);

    String getName(TokenBean tokenBean);

    public final class -CC {
        public static String $default$getName(ICompare _this, TokenBean tokenBean) {
            if (TextUtils.isEmpty(tokenBean.getShortName())) {
                return tokenBean.getName() == null ? "" : tokenBean.getName();
            }
            return tokenBean.getShortName();
        }
    }
}
