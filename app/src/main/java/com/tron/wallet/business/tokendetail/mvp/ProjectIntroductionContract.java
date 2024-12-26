package com.tron.wallet.business.tokendetail.mvp;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.tokendetail.mvp.TokenSecureInfoOutput;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.bean.token.Trc20DetailBean;
import io.reactivex.Observable;
public interface ProjectIntroductionContract {

    public interface Model extends BaseModel {
        Observable<Trc20DetailBean> getTokenDetail(String str, String str2);

        Observable<TokenSecureInfoOutput> getTokenSecurityInfo(String str, int i);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void requestTokenInfo(String str, TokenBean tokenBean, long j);
    }

    public interface View extends BaseView {
        void updateByTRC20TokenBean(Trc20DetailBean.Trc20TokensBean trc20TokensBean);

        void updateSecureInfo(TokenSecureInfoOutput.TokenSecureInfoDataBean tokenSecureInfoDataBean);

        void updateTrc10(Trc20DetailBean.Trc10TokensBean trc10TokensBean);
    }
}
