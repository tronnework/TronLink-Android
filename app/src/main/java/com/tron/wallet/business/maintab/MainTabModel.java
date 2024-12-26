package com.tron.wallet.business.maintab;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.maintab.MainTabContract;
import com.tron.wallet.business.maintab.bean.DeviceInfoBean;
import com.tron.wallet.business.tabdapp.jsbridge.BlackResultListBean;
import com.tron.wallet.common.bean.DappJsOutput;
import com.tron.wallet.common.bean.InfoBean;
import com.tron.wallet.common.bean.NoticeRemindBean;
import com.tron.wallet.common.bean.Result;
import com.tron.wallet.common.bean.update.UpdateOutput;
import com.tron.wallet.common.bean.user.SystemConfigOutput;
import com.tron.wallet.db.bean.DappAuthorizedProjectBean;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
import java.util.List;
import okhttp3.RequestBody;
public class MainTabModel implements MainTabContract.Model {
    @Override
    public Observable<UpdateOutput> update() {
        Observable<UpdateOutput> updateOnLine;
        if (IRequest.isNile() || IRequest.isShasta()) {
            updateOnLine = ((HttpAPI) IRequest.getAPI(HttpAPI.class)).updateOnLine();
        } else {
            updateOnLine = ((HttpAPI) IRequest.getAPI(HttpAPI.class)).update();
        }
        return updateOnLine.compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<InfoBean> getInfo() {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getInfo().compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<InfoBean> deal(RequestBody requestBody) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).deal(requestBody).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<NoticeRemindBean> getNoticeRemind() {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getNoticeRemind().compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<SystemConfigOutput> getSystemConfig() {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getSystemConfig().compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<DeviceInfoBean> sendDeviceInfo(String str, String str2, String str3, String str4) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).sendDeviceInfo(str, str2, str3, str4).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<DappJsOutput> getDappJs() {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getDappJs().compose(RxSchedulers.io_io());
    }

    @Override
    public Observable<Result<List<DappAuthorizedProjectBean>>> getDappAuthorizedProject() {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getDappAuthorizedProject().compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BlackResultListBean> getBlackList() {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).requestBlackList();
    }
}
