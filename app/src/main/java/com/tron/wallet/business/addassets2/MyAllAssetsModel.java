package com.tron.wallet.business.addassets2;

import com.tencent.bugly.Bugly;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.addassets2.MyAllAssetsContract;
import com.tron.wallet.business.addassets2.bean.AssetsData;
import com.tron.wallet.business.addassets2.repository.KVController;
import com.tron.wallet.business.addassets2.repository.NewAssetsController;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
public class MyAllAssetsModel implements MyAllAssetsContract.Model {
    @Override
    public AssetsData getNewAssets(String str) {
        return NewAssetsController.getInstance().getNewAssets(str);
    }

    @Override
    public Observable<Boolean> setSortFilterStatue(String str, final boolean z, final boolean z2) {
        return Observable.just(str).map(new Function() {
            @Override
            public final Object apply(Object obj) {
                return MyAllAssetsModel.lambda$setSortFilterStatue$0(z, z2, (String) obj);
            }
        }).compose(RxSchedulers.io_main());
    }

    public static Boolean lambda$setSortFilterStatue$0(boolean z, boolean z2, String str) throws Exception {
        KVController kVController = KVController.getInstance();
        kVController.setValue(str + "-hideLittleAssets", z ? "true" : Bugly.SDK_IS_DEV);
        KVController kVController2 = KVController.getInstance();
        kVController2.setValue(str + "-hideScamTokens", z2 ? "true" : Bugly.SDK_IS_DEV);
        return true;
    }
}
