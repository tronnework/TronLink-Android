package com.tron.wallet.business.security.tokencheck.view;

import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.security.tokencheck.bean.TokenCheckBean;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.SpAPI;
import java.math.BigDecimal;
public class CheckTokenTotalPriceUtils {
    public static String getTokenTotalPrice(TokenCheckBean tokenCheckBean) {
        return StringTronUtil.formatPrice3(getTokenTotalPriceNumber(tokenCheckBean));
    }

    public static BigDecimal getTokenTotalPriceNumber(TokenCheckBean tokenCheckBean) {
        if (tokenCheckBean != null) {
            try {
                boolean isUsdPrice = SpAPI.THIS.isUsdPrice();
                if (isUsdPrice && !StringTronUtil.isEmpty(tokenCheckBean.getUsdPrice())) {
                    return BigDecimalUtils.mul_(tokenCheckBean.getUsdPrice(), BigDecimalUtils.toBigDecimal(tokenCheckBean.getBalanceStr()));
                }
                if (!isUsdPrice && !StringTronUtil.isEmpty(tokenCheckBean.getCnyPrice())) {
                    return BigDecimalUtils.mul_(tokenCheckBean.getCnyPrice(), BigDecimalUtils.toBigDecimal(tokenCheckBean.getBalanceStr()));
                }
            } catch (Throwable th) {
                LogUtils.e(th);
            }
        }
        return BigDecimal.ZERO;
    }
}
