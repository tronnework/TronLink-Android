package com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.addassets2.bean.nft.NftItemInfoOutput;
import com.tron.wallet.business.tabdapp.bean.DappProjectIOutput;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.DappConfirmNewContract;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
import okhttp3.RequestBody;
public class DappConfirmNewModel implements DappConfirmNewContract.Model {
    @Override
    public Observable<DappProjectIOutput> getProjectInfo(String str, String str2) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getProjectInfo(str, str2).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<Object> addDappRecord(RequestBody requestBody) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).addDappRecord(requestBody).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<NftItemInfoOutput> getCollectionInfo(String str, String str2, String str3) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getCollectionInfo(str, str2, str3).compose(RxSchedulers.io_main());
    }
}
