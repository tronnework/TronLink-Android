package com.tron.wallet.business.tabswap.utils;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.SpUtils;
import com.tron.wallet.business.addassets2.bean.asset.AssetsHomeData;
import com.tron.wallet.business.tabswap.bean.SwapTokenBean;
import com.tron.wallet.business.tabswap.bean.SwapWhiteListOutput;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.db.Controller.AssetsHomeDataDaoManager;
import com.tron.wallet.db.wallet.WalletUtils;
import java.util.Iterator;
public class SwapCacheUtils {
    private static final String CACHE_FILE_NAME = "swap_token_white_list";
    private static final String CACHE_KEY = "swap_token_key";
    public static final int DEFAULT_EXPIRE = 14400000;
    private static volatile SwapCacheUtils cacheUtils = new SwapCacheUtils();

    public static SwapCacheUtils getInstance() {
        return cacheUtils;
    }

    private SwapCacheUtils() {
    }

    public void save(Context context, SwapWhiteListOutput swapWhiteListOutput) {
        if (swapWhiteListOutput == null || swapWhiteListOutput.getTokens() == null) {
            return;
        }
        CacheBean cacheBean = new CacheBean();
        cacheBean.setMainData(swapWhiteListOutput);
        cacheBean.setTimeStamp(System.currentTimeMillis());
        try {
            SpUtils.setParam(CACHE_FILE_NAME, context, CACHE_KEY, JSON.toJSONString(cacheBean));
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public SwapWhiteListOutput getSwapListWithTrx(Context context, SwapWhiteListOutput swapWhiteListOutput) {
        String address = WalletUtils.getSelectedWallet().getAddress();
        if (address == null) {
            return null;
        }
        AssetsHomeData homeData = AssetsHomeDataDaoManager.getHomeData(context, address);
        if (homeData != null) {
            Iterator<TokenBean> it = homeData.token.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                TokenBean next = it.next();
                if (next.type == 0) {
                    swapWhiteListOutput.getTokens().add(0, SwapWhiteListOutput.Data.fromSwapTokenBean(SwapTokenBean.fromTokenBean(next)));
                    break;
                }
            }
        }
        return swapWhiteListOutput;
    }

    public SwapWhiteListOutput read(Context context) {
        CacheBean readCache = readCache(context);
        if (readCache != null) {
            return readCache.getMainData();
        }
        return null;
    }

    public boolean isExpire(Context context) {
        CacheBean readCache = readCache(context);
        return readCache == null || System.currentTimeMillis() - readCache.timeStamp > 14400000;
    }

    private CacheBean readCache(Context context) {
        Object param = SpUtils.getParam(CACHE_FILE_NAME, context, CACHE_KEY, "");
        if ((param instanceof String) && !TextUtils.isEmpty((String) param)) {
            try {
                return (CacheBean) JSON.parseObject((String) param, CacheBean.class);
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
        return null;
    }

    public static class CacheBean {
        SwapWhiteListOutput mainData;
        private long timeStamp;

        public SwapWhiteListOutput getMainData() {
            return this.mainData;
        }

        public long getTimeStamp() {
            return this.timeStamp;
        }

        public void setMainData(SwapWhiteListOutput swapWhiteListOutput) {
            this.mainData = swapWhiteListOutput;
        }

        public void setTimeStamp(long j) {
            this.timeStamp = j;
        }

        private CacheBean() {
        }
    }
}
