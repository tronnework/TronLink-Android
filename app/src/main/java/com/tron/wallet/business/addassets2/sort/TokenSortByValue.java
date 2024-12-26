package com.tron.wallet.business.addassets2.sort;

import com.tron.wallet.common.bean.Price;
import com.tron.wallet.common.bean.token.TokenBean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class TokenSortByValue extends TokenSortBase {
    private ICompare nameComparator = new NameComparator();
    private ICompare valueComparator = new ValueComparator();

    public TokenSortByValue() {
    }

    public TokenSortByValue(Price price) {
    }

    @Override
    public List<TokenBean> sort(List<TokenBean> list) {
        if (list != null && list.size() > 1) {
            ArrayList arrayList = new ArrayList();
            convertTokensList(list, arrayList);
            Collections.sort(arrayList, new Comparator<TokenBean>() {
                @Override
                public int compare(TokenBean tokenBean, TokenBean tokenBean2) {
                    int preCompare = preCompare(tokenBean, tokenBean2);
                    if (preCompare != 0) {
                        return preCompare;
                    }
                    int compare = valueComparator.compare(tokenBean, tokenBean2);
                    return compare != 0 ? compare : nameComparator.compare(tokenBean, tokenBean2);
                }
            });
            convertTokensList(arrayList, list);
        }
        return list;
    }
}
