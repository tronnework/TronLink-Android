package com.tron.wallet.business.addassets2;

import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.MyAllAssetsContract;
import com.tron.wallet.business.addassets2.bean.AssetsData;
import com.tron.wallet.business.assetshome.SortHelper;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.db.wallet.WalletUtils;
import io.reactivex.functions.Consumer;
public class MyAllAssetsPresent extends MyAllAssetsContract.Presenter {
    private static final String TAG = "MyAllAssetsPresent";
    private String address;

    public static void lambda$updateFilterStatus$2(Throwable th) throws Exception {
    }

    @Override
    public void getNewAssets() {
        AssetsData newAssets = ((MyAllAssetsContract.Model) this.mModel).getNewAssets(this.address);
        if (newAssets != null && newAssets.getCount() > 0) {
            ((MyAllAssetsContract.View) this.mView).showNewAssetsTips(true);
        } else {
            ((MyAllAssetsContract.View) this.mView).showNewAssetsTips(false);
        }
        if (newAssets != null && newAssets.getTrc721Count() > 0) {
            ((MyAllAssetsContract.View) this.mView).showNewCollectionsTips(true);
        } else {
            ((MyAllAssetsContract.View) this.mView).showNewCollectionsTips(false);
        }
    }

    @Override
    protected void onStart() {
        this.address = WalletUtils.getSelectedWallet().getAddress();
        this.mRxManager.on(Event.ASSETS_NEW, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$0(obj);
            }
        });
    }

    public void lambda$onStart$0(Object obj) throws Exception {
        getNewAssets();
    }

    @Override
    public void updateFilterStatus(final boolean z, final boolean z2) {
        SortHelper.get().setHideFiltersFlag(z, z2);
        ((MyAllAssetsContract.Model) this.mModel).setSortFilterStatue(this.address, z, z2).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                Boolean bool = (Boolean) obj;
                LogUtils.d(MyAllAssetsPresent.TAG, "setHideLittleAssets:" + z + " " + z2);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                MyAllAssetsPresent.lambda$updateFilterStatus$2((Throwable) obj);
            }
        });
    }
}
