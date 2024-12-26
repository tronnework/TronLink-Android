package com.tron.wallet.common.utils;

import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tronlinkpro.wallet.R;
import java.util.Random;
public enum UserIconRandom {
    THIS;
    
    private String[] iconLists;
    Random random = new Random();

    UserIconRandom() {
    }

    public String random() {
        if (this.iconLists == null) {
            this.iconLists = AppContextUtil.getContext().getResources().getStringArray(R.array.icon_list);
        }
        return this.iconLists[this.random.nextInt(7)];
    }
}
