package com.tron.wallet.business.pull;

import com.alibaba.fastjson.JSON;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import com.tron.tron_base.frame.net.OkHttpManager;
import com.tron.tron_base.frame.utils.LogUtils;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
public class PullResultHelper {
    public static void callBackToDApp(String str, PullResult pullResult) {
        callBackToDApp(str, JSON.toJSONString(pullResult), (PullResultInterface) null);
    }

    public static void callBackToDApp(String str, String str2) {
        callBackToDApp(str, str2, (PullResultInterface) null);
    }

    public static void callBackToDApp(String str, PullResult pullResult, PullResultInterface pullResultInterface) {
        callBackToDApp(str, JSON.toJSONString(pullResult), pullResultInterface);
    }

    public static void callBackToDApp(String str, String str2, final PullResultInterface pullResultInterface) {
        LogUtils.d("Pull.Activity", str2);
        FirebasePerfOkHttpClient.enqueue(OkHttpManager.getInstance().build().newCall(new Request.Builder().url(str).post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), str2)).build()), new Callback() {
            @Override
            public void onFailure(Call call, IOException iOException) {
                PullResultInterface pullResultInterface2 = PullResultInterface.this;
                if (pullResultInterface2 != null) {
                    pullResultInterface2.onDAppResultFailure();
                }
                LogUtils.e(iOException);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    response.close();
                    PullResultInterface pullResultInterface2 = PullResultInterface.this;
                    if (pullResultInterface2 != null) {
                        pullResultInterface2.onDAppResultResponse();
                    }
                    LogUtils.d("Pull.Activity", "code:" + response.code());
                } catch (Exception e) {
                    LogUtils.e(e);
                }
            }
        });
    }
}
