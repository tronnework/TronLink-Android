package com.tron.wallet.business.addassets2.sort;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.tron.wallet.business.addassets2.sort.ICompare;
import com.tron.wallet.common.bean.token.OfficialType;
import com.tron.wallet.common.bean.token.TokenBean;
public class ValueComparator implements ICompare {
    private int compareNum(double d, double d2) {
        if (d > d2) {
            return -1;
        }
        return d < d2 ? 1 : 0;
    }

    @Override
    public String getName(TokenBean tokenBean) {
        return ICompare.-CC.$default$getName(this, tokenBean);
    }

    @Override
    public int compare(TokenBean tokenBean, TokenBean tokenBean2) {
        if (OfficialType.isScamOrUnSafeToken(tokenBean) && OfficialType.isScamOrUnSafeToken(tokenBean2)) {
            return 0;
        }
        if (OfficialType.isScamOrUnSafeToken(tokenBean)) {
            return 1;
        }
        if (OfficialType.isScamOrUnSafeToken(tokenBean2)) {
            return -1;
        }
        if (tokenBean.getBalance() != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && tokenBean2.getBalance() != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            if (tokenBean.getTrxCount() > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && tokenBean2.getTrxCount() > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                return compareNum(tokenBean.getTrxCount(), tokenBean2.getTrxCount());
            }
            if (tokenBean.getTrxCount() > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                return -1;
            }
            if (tokenBean2.getTrxCount() > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                return 1;
            }
            return compareNum(tokenBean.getBalance(), tokenBean2.getBalance());
        } else if (tokenBean.getBalance() != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            return -1;
        } else {
            if (tokenBean2.getBalance() != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                return 1;
            }
            if (tokenBean.getPrice() <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || tokenBean2.getPrice() > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                return (tokenBean.getPrice() > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || tokenBean2.getPrice() <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) ? 0 : 1;
            }
            return -1;
        }
    }
}
