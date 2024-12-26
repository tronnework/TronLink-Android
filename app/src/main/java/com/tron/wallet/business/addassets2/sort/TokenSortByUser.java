package com.tron.wallet.business.addassets2.sort;

import com.tron.wallet.business.addassets2.bean.TokenSortBean;
import com.tron.wallet.common.bean.token.TokenBean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class TokenSortByUser extends TokenSortBase {
    private List<TokenSortBean> sortList;
    private ICompare nameComparator = new NameComparator();
    private ICompare valueComparator = new ValueComparator();

    public TokenSortByUser(List<TokenSortBean> list) {
        this.sortList = list;
    }

    public com.tron.wallet.business.addassets2.bean.TokenSortBean findTokenSortBean(com.tron.wallet.common.bean.token.TokenBean r5) {
        


return null;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.addassets2.sort.TokenSortByUser.findTokenSortBean(com.tron.wallet.common.bean.token.TokenBean):com.tron.wallet.business.addassets2.bean.TokenSortBean");
    }

    @Override
    public List<TokenBean> sort(List<TokenBean> list) {
        if (list == null || list.size() <= 1) {
            return list;
        }
        List<TokenSortBean> list2 = this.sortList;
        if (list2 == null || list2.size() <= 1) {
            return new TokenSortByValue().sort(list);
        }
        ArrayList arrayList = new ArrayList();
        convertTokensList(list, arrayList);
        Collections.sort(arrayList, new Comparator<TokenBean>() {
            @Override
            public int compare(TokenBean tokenBean, TokenBean tokenBean2) {
                int preCompare = preCompare(tokenBean, tokenBean2);
                if (preCompare != 0) {
                    return preCompare;
                }
                TokenSortBean findTokenSortBean = findTokenSortBean(tokenBean);
                TokenSortBean findTokenSortBean2 = findTokenSortBean(tokenBean2);
                if (findTokenSortBean.getIndex() > findTokenSortBean2.getIndex()) {
                    return 1;
                }
                if (findTokenSortBean.getIndex() == findTokenSortBean2.getIndex()) {
                    int compare = valueComparator.compare(tokenBean, tokenBean2);
                    return compare != 0 ? compare : nameComparator.compare(tokenBean, tokenBean2);
                }
                return -1;
            }
        });
        convertTokensList(arrayList, list);
        return list;
    }
}
