package com.tron.wallet.common.utils;

import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.ThreadPoolManager;
import com.tron.wallet.db.SpAPI;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
public enum DappJs {
    THIS;
    
    public static final String AssetsTronWebFileName = "simplify-mTronWeb.js";
    public static final String TronWebVersion = "5.3.0";
    private String tronWebJS;
    private boolean tronWebWithAssetsTest = false;
    private String vConsoleJs;

    DappJs() {
    }

    public void initConsoleLocal() {
        getConsoleLocal();
    }

    public void initLocal() {
        getTronWebByLocal();
    }

    public boolean doNotNeedUpgrade(String str) {
        String[] split;
        String[] split2;
        if (StringTronUtil.isEmpty(str)) {
            return true;
        }
        if (StringTronUtil.isEmpty(TronWebVersion)) {
            return false;
        }
        if (str.equals(TronWebVersion)) {
            return true;
        }
        try {
            split = TronWebVersion.split("\\.");
            split2 = str.split("\\.");
        } catch (Exception e) {
            LogUtils.e(e);
        }
        if (split2 != null && split2.length != 0) {
            int i = 0;
            while (i < split.length) {
                if ((split.length > i ? Integer.parseInt(split[i]) : 0) > (split2.length > i ? Integer.parseInt(split2[i]) : 0)) {
                    return true;
                }
                i++;
            }
            return false;
        }
        return true;
    }

    public void updateDappJs(String str) {
        SpAPI.THIS.setDappJSOutput(str);
    }

    public void getTronWebByLocal() {
        ThreadPoolManager.newInstance().addExecuteTask(new Runnable() {
            @Override
            public final void run() {
                lambda$getTronWebByLocal$0();
            }
        });
    }

    public void lambda$getTronWebByLocal$0() {
        try {
            if (this.tronWebWithAssetsTest) {
                getTronWebWithAssets();
            } else {
                getTronWebLocal();
                if (StringTronUtil.isEmpty(this.tronWebJS)) {
                    getTronWebWithAssets();
                }
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void getTronWebByUrl(final String str, final String str2) {
        ThreadPoolManager.newInstance().addExecuteTask(new Runnable() {
            @Override
            public final void run() {
                lambda$getTronWebByUrl$1(str, str2);
            }
        });
    }

    public void lambda$getTronWebByUrl$1(String str, String str2) {
        try {
            if (this.tronWebWithAssetsTest) {
                getTronWebWithAssets();
            } else {
                getTronWebOnline(str, str2);
            }
        } catch (IOException e) {
            LogUtils.e(e);
            THIS.getTronWebByLocal();
        }
    }

    private void getTronWebOnline(String str, String str2) throws IOException {
        if (StringTronUtil.isNullOrEmpty(str)) {
            str = ExchangeUtils.TronWebJS;
        }
        LogUtils.d("MainTabPresenter", "getTronWebOnline md5:" + str + " " + str2);
        URLConnection uRLConnection = (URLConnection) FirebasePerfUrlConnection.instrument(new URL(str).openConnection());
        uRLConnection.setConnectTimeout(8000);
        uRLConnection.setReadTimeout(8000);
        InputStream inputStream = uRLConnection.getInputStream();
        byte[] bArr = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = inputStream != null ? inputStream.read(bArr) : 0;
            if (read <= 0) {
                break;
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
        this.tronWebJS = byteArrayOutputStream.toString();
        closeStream(byteArrayOutputStream);
        closeStream(inputStream);
        if (Md5Util.md5(this.tronWebJS).equals(str2)) {
            LogUtils.d("MainTabPresenter", "Md5Util.md5(tronWebJS).equals(md5)");
            updateTronWebLocal(this.tronWebJS);
            return;
        }
        LogUtils.d("MainTabPresenter", "getTronWebByLocal");
        getTronWebByLocal();
    }

    private void getTronWebLocal() {
        try {
            this.tronWebJS = SpAPI.THIS.getDappJsString();
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    private void updateTronWebLocal(String str) {
        SpAPI.THIS.setDappjsString(str);
    }

    private void getTronWebWithAssets() {
        try {
            this.tronWebJS = AssetsRawUtils.getFromAssets(AppContextUtil.getContext(), AssetsTronWebFileName);
        } catch (IOException e) {
            LogUtils.e(e);
        }
    }

    private void getConsoleLocal() {
        try {
            this.vConsoleJs = AssetsRawUtils.getFromAssets(AppContextUtil.getContext(), "vconsole.min_3.8.1.js");
        } catch (IOException e) {
            LogUtils.e(e);
        }
    }

    private void closeStream(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                LogUtils.e(e);
                SentryUtil.captureException(e);
            }
        }
    }

    public String getJsText() {
        try {
            boolean curIsMainChain = SpAPI.THIS.getCurIsMainChain();
            if (StringTronUtil.isEmpty(this.tronWebJS) && curIsMainChain) {
                getTronWebByLocal();
            }
            return "javascript:(function() {var scriptElement = document.getElementById('readability-script');var parent = document.getElementsByTagName('body').item(0);if(parent && !scriptElement) {var script = document.createElement('script');script.type = 'text/javascript';script.id = 'readability-script';script.innerHTML = " + this.tronWebJS + ";parent.appendChild(script);}})()";
        } catch (Exception unused) {
            return "";
        }
    }

    public String getvConsoleJsText() {
        if (StringTronUtil.isEmpty(this.vConsoleJs)) {
            getConsoleLocal();
        }
        return "javascript:(function() {var scriptElement = document.getElementById('readability-script2');var parent = document.getElementsByTagName('body').item(0);if(parent && !scriptElement) {var script = document.createElement('script');script.type = 'text/javascript';script.id = 'readability-script2';script.charset = 'utf-8';script.innerHTML = " + this.vConsoleJs + ";parent.appendChild(script);var html = document.getElementsByTagName('html').item(0);var script2 = document.createElement('script');script2.innerHTML = window.vConsole = new window.VConsole();html.appendChild(script2);}})()";
    }
}
