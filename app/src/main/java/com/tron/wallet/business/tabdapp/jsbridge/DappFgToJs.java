package com.tron.wallet.business.tabdapp.jsbridge;

import android.app.Activity;
import android.webkit.JavascriptInterface;
import com.google.gson.Gson;
import com.tron.wallet.common.components.MyWebView;
import com.tron.wallet.common.utils.StringTronUtil;
public class DappFgToJs extends BaseAndroidtoJs {
    private Gson gson;
    private OnClickListener onClickListener;

    interface OnClickListener {
        void onHistoryClick(HistoryData historyData);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public DappFgToJs(Activity activity, String str, MyWebView myWebView) {
        super(activity, null, str, myWebView);
        this.gson = new Gson();
    }

    @JavascriptInterface
    public void clickHistory(String str) {
        if (StringTronUtil.isEmpty(str)) {
            return;
        }
        try {
            HistoryData historyData = (HistoryData) this.gson.fromJson(str,  HistoryData.class);
            OnClickListener onClickListener = this.onClickListener;
            if (onClickListener != null) {
                onClickListener.onHistoryClick(historyData);
            }
        } catch (Exception unused) {
        }
    }

    class HistoryData {
        public String imgUrl;
        public String link;
        public String title;

        HistoryData() {
        }
    }
}
