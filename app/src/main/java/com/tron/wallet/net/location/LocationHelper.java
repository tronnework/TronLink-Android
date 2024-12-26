package com.tron.wallet.net.location;

import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import com.tron.tron_base.frame.net.OkHttpManager;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.utils.SentryUtil;
import java.io.IOException;
import javax.annotation.Nonnull;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
public class LocationHelper {
    private static final String CN = "CN";
    private static final String IP_API = "http://ip-api.com/json/";
    private static volatile LocationHelper instance = null;
    public static boolean isCn = false;

    public interface LocationCallback {
        void onLocated();
    }

    private LocationHelper() {
    }

    public static LocationHelper get() {
        if (instance == null) {
            synchronized (CN) {
                instance = new LocationHelper();
            }
        }
        return instance;
    }

    public void locationByIp(@Nonnull final LocationCallback locationCallback) {
        FirebasePerfOkHttpClient.enqueue(OkHttpManager.getInstance().build().newCall(new Request.Builder().url(IP_API).build()), new Callback() {
            @Override
            public void onFailure(Call call, IOException iOException) {
                LogUtils.e(iOException);
                LocationHelper.isCn = false;
                locationCallback.onLocated();
            }

            @Override
            public void onResponse(@Nonnull Call call, @Nonnull Response response) throws IOException {
                if (response.code() >= 300 || response.code() < 200) {
                    onFailure(call, new IOException("Response code is not 2xx !"));
                    return;
                }
                if (response.body() != null) {
                    try {
                        JSONObject parseObject = JSON.parseObject(response.body().string());
                        if (parseObject != null) {
                            Object obj = parseObject.get("countryCode");
                            if (obj instanceof String) {
                                LocationHelper.isCn = TextUtils.equals((String) obj, LocationHelper.CN);
                            }
                        }
                    } catch (Exception e) {
                        SentryUtil.captureException(e);
                        LogUtils.e(e);
                    }
                }
                locationCallback.onLocated();
            }
        });
    }
}
