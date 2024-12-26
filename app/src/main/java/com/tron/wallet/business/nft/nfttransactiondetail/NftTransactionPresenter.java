package com.tron.wallet.business.nft.nfttransactiondetail;

import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.nft.nfttransactiondetail.NftTransactionContract;
import io.reactivex.functions.Consumer;
public class NftTransactionPresenter extends NftTransactionContract.Presenter {
    @Override
    protected void onStart() {
    }

    @Override
    public void getNameByAddressSa(String str) {
        ((NftTransactionContract.Model) this.mModel).getNameByAddress(str).compose(RxSchedulers.io_main()).subscribe(new Consumer<NameAddressResultBean>() {
            @Override
            public void accept(NameAddressResultBean nameAddressResultBean) throws Exception {
                ((NftTransactionContract.View) mView).updateSaAddressByName(nameAddressResultBean.getNameAddressExtraBean());
            }
        });
    }

    @Override
    public void getNameByAddressRa(String str) {
        ((NftTransactionContract.Model) this.mModel).getNameByAddress(str).compose(RxSchedulers.io_main()).subscribe(new Consumer<NameAddressResultBean>() {
            @Override
            public void accept(NameAddressResultBean nameAddressResultBean) throws Exception {
                ((NftTransactionContract.View) mView).updateRaAddressByName(nameAddressResultBean.getNameAddressExtraBean());
            }
        });
    }
}
