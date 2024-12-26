package com.tron.wallet.common.utils;

import com.google.gson.Gson;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.tabswap.bean.AppStatusInput;
import com.tron.wallet.business.tabswap.bean.AppStatusOutput;
import com.tron.wallet.common.bean.Result2;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
public class AppStatusUtils {
    public static void appStatusRequest(RxManager rxManager) {
        request(rxManager);
    }

    public static void request(final RxManager rxManager) {
        LogUtils.i("AppStatusUtils", "request AppStatus");
        AppStatusInput appStatusInput = new AppStatusInput();
        appStatusInput.setFina(false);
        appStatusInput.setMainland(true);
        ((HttpAPI) IRequest.getAPI(HttpAPI.class)).checkAppStatus(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(appStatusInput))).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<Result2<AppStatusOutput>>() {
            @Override
            public void onFailure(String str, String str2) {
            }

            @Override
            public void onResponse(String str, Result2<AppStatusOutput> result2) {
                if (result2 == null || result2.getCode() != 0 || result2.getData() == null) {
                    return;
                }
                result2.getData().setHideShieldManager(true);
                SpAPI.THIS.setAppStatus(result2.getData());
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                RxManager rxManager2 = RxManager.this;
                if (rxManager2 != null) {
                    rxManager2.add(disposable);
                }
            }
        }, "appStatusRequest"));
    }
}
