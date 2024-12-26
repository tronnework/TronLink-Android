package com.tron.wallet.business.tokendetail.mvp;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.tokendetail.mvp.ProjectIntroductionContract;
import com.tron.wallet.common.bean.token.Trc20DetailBean;
import com.tron.wallet.common.config.M;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
public class ProjectIntroductionModel implements ProjectIntroductionContract.Model {
    @Override
    public Observable<TokenSecureInfoOutput> getTokenSecurityInfo(String str, int i) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getTokenSecurityInfo(i, str).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<Trc20DetailBean> getTokenDetail(String str, String str2) {
        if (M.M_TRC20.equals(str)) {
            return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getTRC20Detail(str2).compose(RxSchedulers.io_main());
        }
        HttpAPI httpAPI = (HttpAPI) IRequest.getAPI(HttpAPI.class);
        if (StringTronUtil.isEmpty(str2)) {
            str2 = "0";
        }
        return httpAPI.getTRC10Detail(str2).compose(RxSchedulers.io_main());
    }
}
