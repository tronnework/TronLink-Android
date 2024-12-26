package com.tron.wallet.business.addassets2.repository;

import com.tron.wallet.business.addassets2.bean.KVBean;
import com.tron.wallet.business.addassets2.bean.TokenSortType;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.db.greendao.KVBeanDao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.greenrobot.greendao.query.WhereCondition;
public class KVController extends BaseController<KVBean> {
    private static final String TOKEN_SORT_TYPE = "tokenSortType";
    private static KVController instance;
    private Map<String, KVBean> cache;

    private KVController() {
        this.cache = new HashMap();
    }

    public KVController(DbManager dbManager) {
        super(dbManager);
        this.cache = new HashMap();
    }

    public static KVController getInstance() {
        if (instance == null) {
            synchronized (KVController.class) {
                if (instance == null) {
                    instance = new KVController();
                }
            }
        }
        return instance;
    }

    public String getValue(String str) {
        List list;
        try {
            KVBean kVBean = this.cache.get(str);
            if (kVBean == null && (list = this.beanDao.queryBuilder().where(KVBeanDao.Properties.Key.eq(str), new WhereCondition[0]).list()) != null && list.size() > 0) {
                kVBean = (KVBean) list.get(0);
            }
            if (kVBean != null) {
                setCache(str, kVBean);
                return kVBean.getValue();
            }
            return null;
        } catch (Exception e) {
            SentryUtil.captureException(e);
            return null;
        }
    }

    public synchronized void setValue(String str, String str2) {
        try {
            KVBean kVBean = this.cache.get(str);
            if (kVBean == null) {
                kVBean = new KVBean();
                kVBean.setKey(str);
            }
            kVBean.setValue(str2);
            if (save(kVBean)) {
                setCache(str, kVBean);
            }
        } catch (Exception e) {
            SentryUtil.captureException(e);
        }
    }

    public String getTokenSortKey(String str, int i) {
        if (i != 5) {
            return str + "-tokenSortType";
        }
        return str + "-tokenSortType-" + i;
    }

    public TokenSortType getTokenSortType(String str) {
        return getTokenSortType(str, 0);
    }

    public TokenSortType getTokenSortType(String str, int i) {
        String value = getValue(getTokenSortKey(str, i));
        if (value != null) {
            return TokenSortType.getTypeByValue(Integer.valueOf(value).intValue());
        }
        return TokenSortType.SORT_BY_USER;
    }

    public void setTokenSortType(String str, TokenSortType tokenSortType) {
        setTokenSortType(str, tokenSortType, 0);
    }

    public void setTokenSortType(String str, TokenSortType tokenSortType, int i) {
        setValue(getTokenSortKey(str, i), String.valueOf(tokenSortType.getValue()));
    }

    private synchronized void setCache(String str, KVBean kVBean) {
        this.cache.put(str, kVBean);
    }

    public synchronized void clearCache() {
        this.cache.clear();
    }
}
