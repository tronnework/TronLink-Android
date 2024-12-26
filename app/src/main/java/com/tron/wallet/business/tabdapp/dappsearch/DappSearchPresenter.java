package com.tron.wallet.business.tabdapp.dappsearch;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import android.webkit.URLUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.SpUtils;
import com.tron.tron_base.frame.utils.ThreadPoolManager;
import com.tron.wallet.business.tabdapp.bean.DappHotBean;
import com.tron.wallet.business.tabdapp.bean.DappSearchBean;
import com.tron.wallet.business.tabdapp.dappsearch.DappSearchContract;
import com.tron.wallet.common.utils.ChainUtil;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
public class DappSearchPresenter extends DappSearchContract.Presenter {
    private static final String REG_URL = "^(https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*.[-a-zA-Z0-9+&@#/%=~_|]";
    private static Pattern mUrlPattern = Pattern.compile(REG_URL);
    private final String DAPP_HISTORY_LIST = "dapp_history_list";
    private final String FILE_NAME = "tag_history_search";

    @Override
    void addSome() {
    }

    @Override
    protected void onStart() {
    }

    @Override
    void doSearch() {
        ((DappSearchContract.Model) this.mModel).doSearch(((DappSearchContract.View) this.mView).getContent()).subscribe(new IObserver(new ICallback<DappSearchBean>() {
            @Override
            public void onResponse(String str, DappSearchBean dappSearchBean) {
                ((DappSearchContract.View) mView).doSuccess(dappSearchBean);
            }

            @Override
            public void onFailure(String str, String str2) {
                ((DappSearchContract.View) mView).doFailure(1);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "3"));
    }

    @Override
    void doHotList() {
        ((DappSearchContract.Model) this.mModel).getDappHotList().subscribe(new IObserver(new ICallback<DappHotBean>() {
            @Override
            public void onResponse(String str, DappHotBean dappHotBean) {
                ((DappSearchContract.View) mView).doSuccess(dappHotBean, 0);
            }

            @Override
            public void onFailure(String str, String str2) {
                ((DappSearchContract.View) mView).doFailure(0);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "2"));
    }

    public static boolean checkUrlValid(String str) {
        try {
            String trim = str.trim();
            if (!trim.startsWith(ChainUtil.Request_HTTP) && !trim.startsWith("https://")) {
                trim = "https://" + trim;
            }
            return Boolean.valueOf(URLUtil.isValidUrl(trim) && Patterns.WEB_URL.matcher(trim).matches()).booleanValue();
        } catch (Exception e) {
            LogUtils.e(e);
            return false;
        }
    }

    public static boolean isUrlValid(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String fixedUrl = getFixedUrl(str);
        return mUrlPattern.matcher(fixedUrl).matches() && fixedUrl.contains(ThreadPoolManager.DOT);
    }

    public static String getFixedUrl(String str) {
        String trim = str.trim();
        if (trim.startsWith(ChainUtil.Request_HTTP) || trim.startsWith("https://")) {
            return trim;
        }
        return "https://" + trim;
    }

    public List<String> getHistoryList(Context context) {
        String str = (String) SpUtils.getParam("tag_history_search", context, "dapp_history_list", "");
        if (str == null || str.length() == 0) {
            return new ArrayList();
        }
        return (List) new Gson().fromJson(str, new TypeToken<List<String>>() {
        }.getType());
    }

    public void setHistoryList(Context context, String str) {
        List<String> historyList = getHistoryList(context);
        historyList.remove(str);
        if (historyList.size() > 15) {
            historyList.remove(historyList.size() - 1);
        }
        Collections.reverse(historyList);
        historyList.add(str);
        Collections.reverse(historyList);
        SpUtils.setParam("tag_history_search", context, "dapp_history_list", new Gson().toJson(historyList));
    }

    public void clearHistoryList(Context context) {
        SpUtils.remove("tag_history_search", context, "dapp_history_list");
    }
}
