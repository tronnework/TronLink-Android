package com.tron.wallet.business.nft.nfttransactiondetail;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import io.reactivex.Observable;
public interface NftTransactionContract {

    public interface Model extends BaseModel {
        Observable<NameAddressResultBean> getNameByAddress(String str);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getNameByAddressRa(String str);

        public abstract void getNameByAddressSa(String str);
    }

    public interface View extends BaseView {
        void updateRaAddressByName(NameAddressExtraBean nameAddressExtraBean);

        void updateSaAddressByName(NameAddressExtraBean nameAddressExtraBean);
    }
}
