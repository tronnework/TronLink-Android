package com.tron.wallet.business.addassets2.sort;

import com.tron.wallet.common.bean.token.TokenBean;
import java.util.List;
public abstract class TokenSortBase implements ITokenSort {
    public List<TokenBean> convertTokensList(List<TokenBean> list, List<TokenBean> list2) {
        if (list != null && list.size() > 0 && list2 != null) {
            list2.clear();
            for (int i = 0; i < list.size(); i++) {
                list2.add(list.get(i));
            }
        }
        return list2;
    }

    public int preCompare(TokenBean tokenBean, TokenBean tokenBean2) {
        if (tokenBean.getType() == 0) {
            return -1;
        }
        if (tokenBean2.getType() == 0) {
            return 1;
        }
        if (tokenBean.getTop() != 2 || tokenBean2.getTop() == 2) {
            return (tokenBean.getTop() == 2 || tokenBean2.getTop() != 2) ? 0 : 1;
        }
        return -1;
    }
}
