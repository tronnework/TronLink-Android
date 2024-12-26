package com.tron.wallet.net;

import com.tron.wallet.business.confirm.fg.bean.HotInfoBean;
import com.tron.wallet.business.tabdapp.browser.bean.ScamUrlBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
public interface HttpApiExtend {
    @Headers({"Content-Type: application/json", "Accept: application/json", "TRON-PRO-API-KEY: bf1d6420-0f0a-4943-9841-6f335363871c"})
    @GET("api/security/url/data")
    Observable<ScamUrlBean> checkScamUrl();

    @Headers({"Content-Type: application/json", "Accept: application/json", "TRON-PRO-API-KEY: bf1d6420-0f0a-4943-9841-6f335363871c"})
    @GET("api/search/hot?cfrom=search")
    Observable<HotInfoBean> getHotContract();
}
