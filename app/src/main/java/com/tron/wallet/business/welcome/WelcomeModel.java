package com.tron.wallet.business.welcome;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.tabmy.myhome.settings.bean.MainNodeOutput;
import com.tron.wallet.business.tabmy.myhome.settings.bean.NodeOutput;
import com.tron.wallet.business.welcome.WelcomeContract;
import com.tron.wallet.common.bean.user.SplashOutput;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
import okhttp3.RequestBody;
public class WelcomeModel implements WelcomeContract.Model {
    @Override
    public Observable<NodeOutput> getNodes(RequestBody requestBody) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getNodes(requestBody).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<MainNodeOutput> getMainNodes(String str, RequestBody requestBody) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getMainNodes(str, requestBody).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<SplashOutput> getSplashPage(String str, String str2) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getSplashPage(str, str2).compose(RxSchedulers.io_main());
    }
}
