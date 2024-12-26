package com.tron.wallet.business.addassets2.repository;

import com.tron.wallet.business.addassets2.bean.AssetsData;
import com.tron.wallet.business.addassets2.bean.nft.NftTokenBean;
import com.tron.wallet.common.bean.token.TokenBean;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
public class NewAssetsController {
    private static NewAssetsController instance;
    private Map<String, AssetsData> newAssets = new HashMap();

    private NewAssetsController() {
    }

    public static NewAssetsController getInstance() {
        if (instance == null) {
            synchronized (NewAssetsController.class) {
                if (instance == null) {
                    instance = new NewAssetsController();
                }
            }
        }
        return instance;
    }

    public int getNewAssetsCount(String str) {
        AssetsData assetsData = this.newAssets.get(str);
        if (assetsData != null) {
            return assetsData.getCount() + assetsData.getTrc721Count();
        }
        return 0;
    }

    public void removeNewAssets(String str, TokenBean tokenBean) {
        AssetsData assetsData = this.newAssets.get(str);
        if (tokenBean.getType() != 5) {
            assetsData.setCount(assetsData.getCount() - 1);
            Iterator<TokenBean> it = assetsData.getTokens().iterator();
            while (it.hasNext()) {
                if (it.next() == tokenBean) {
                    it.remove();
                    return;
                }
            }
            return;
        }
        assetsData.setTrc721Count(assetsData.getTrc721Count() - 1);
        Iterator<NftTokenBean> it2 = assetsData.getTrc721Tokens().iterator();
        while (it2.hasNext()) {
            if (it2.next() == ((NftTokenBean) tokenBean.getExtraData())) {
                it2.remove();
                return;
            }
        }
    }

    public AssetsData getNewAssets(String str) {
        return this.newAssets.get(str);
    }

    public synchronized void setNewAssets(String str, AssetsData assetsData) {
        if (assetsData != null) {
            if (assetsData.getTokens() != null) {
                for (TokenBean tokenBean : assetsData.getTokens()) {
                    tokenBean.setNewAsset(true);
                }
            }
            if (assetsData.getTrc721Tokens() != null) {
                for (NftTokenBean nftTokenBean : assetsData.getTrc721Tokens()) {
                    nftTokenBean.setNew(true);
                }
            }
        }
        this.newAssets.put(str, assetsData);
    }

    public synchronized void clear() {
        this.newAssets.clear();
    }

    public synchronized void clear(String str) {
        this.newAssets.remove(str);
    }
}
