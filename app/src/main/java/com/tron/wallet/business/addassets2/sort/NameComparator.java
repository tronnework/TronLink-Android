package com.tron.wallet.business.addassets2.sort;

import com.tron.wallet.business.addassets2.sort.ICompare;
import com.tron.wallet.common.bean.token.TokenBean;
public class NameComparator implements ICompare {
    @Override
    public String getName(TokenBean tokenBean) {
        return ICompare.-CC.$default$getName(this, tokenBean);
    }

    @Override
    public int compare(TokenBean tokenBean, TokenBean tokenBean2) {
        int compareToIgnoreCase = getName(tokenBean).compareToIgnoreCase(getName(tokenBean2));
        return compareToIgnoreCase == 0 ? getName(tokenBean).compareTo(getName(tokenBean2)) : compareToIgnoreCase;
    }
}
