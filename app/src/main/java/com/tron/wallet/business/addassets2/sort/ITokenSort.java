package com.tron.wallet.business.addassets2.sort;

import com.tron.wallet.common.bean.token.TokenBean;
import java.util.List;
public interface ITokenSort {
    List<TokenBean> sort(List<TokenBean> list);
}
