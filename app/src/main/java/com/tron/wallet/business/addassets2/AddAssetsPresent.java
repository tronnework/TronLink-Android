package com.tron.wallet.business.addassets2;

import com.tron.wallet.business.addassets2.AddAssetsContract;
import com.tron.wallet.business.addassets2.bean.TokenSortType;
import com.tron.wallet.db.wallet.WalletUtils;
import io.reactivex.functions.Consumer;
public class AddAssetsPresent extends AddAssetsContract.Presenter {
    private String address;

    @Override
    protected void onStart() {
        this.address = WalletUtils.getSelectedWallet().getAddress();
    }

    @Override
    public void getAssetsSortType() {
        this.mRxManager.add(((AddAssetsContract.Model) this.mModel).getTokenSortType(this.address).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$getAssetsSortType$0((TokenSortType) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$getAssetsSortType$1((Throwable) obj);
            }
        }));
    }

    public void lambda$getAssetsSortType$0(TokenSortType tokenSortType) throws Exception {
        ((AddAssetsContract.View) this.mView).updateSortType(tokenSortType);
    }

    public void lambda$getAssetsSortType$1(Throwable th) throws Exception {
        ((AddAssetsContract.View) this.mView).updateSortType(TokenSortType.SORT_BY_VALUE);
    }
}
