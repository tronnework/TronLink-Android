package com.tron.wallet.business.nft.contract;

import android.content.Context;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.bean.nft.NftDetailOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftInfoOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftItemInfo;
import com.tron.wallet.business.addassets2.bean.nft.NftTypeInfo;
import com.tron.wallet.business.nft.contract.Contract;
import com.tron.wallet.business.nft.dao.DbController;
import com.tron.wallet.db.greendao.NftItemInfoDao;
import com.tron.wallet.db.greendao.NftTypeInfoDao;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.util.List;
import okhttp3.RequestBody;
import org.greenrobot.greendao.query.WhereCondition;
public class NftModel implements Contract.Model {
    String TAG = "NftModel";

    @Override
    public Observable<NftInfoOutput> getNftTokenInfo(final Context context, final String str, final String str2) {
        return Observable.unsafeCreate(new ObservableSource() {
            @Override
            public final void subscribe(Observer observer) {
                NftModel.lambda$getNftTokenInfo$0(context, str2, str, observer);
            }
        });
    }

    public static void lambda$getNftTokenInfo$0(Context context, String str, String str2, Observer observer) {
        List<?> queryAll = DbController.getInstance(context, NftTypeInfo.class).queryAll(new WhereCondition[]{NftTypeInfoDao.Properties.TokenAddress.eq(str)});
        NftInfoOutput nftInfoOutput = new NftInfoOutput();
        if (queryAll.isEmpty()) {
            nftInfoOutput.setData(NftTypeInfo.buildDefault());
        } else {
            ((NftTypeInfo) queryAll.get(0)).setCollectionInfoList(DbController.getInstance(context, NftItemInfo.class).queryAll(new WhereCondition[]{NftItemInfoDao.Properties.WalletAddress.eq(str2), NftItemInfoDao.Properties.TokenAddress.eq(str)}));
            nftInfoOutput.setData((NftTypeInfo) queryAll.get(0));
        }
        observer.onNext(nftInfoOutput);
        observer.onComplete();
    }

    @Override
    public Observable<NftInfoOutput> requestNftTokenInfo(String str, String str2, int i, int i2) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getCollection(str, str2, i, i2).compose(RxSchedulers.io_main());
    }

    @Override
    public void saveNftToken(Context context, NftTypeInfo nftTypeInfo, ICallback<Boolean> iCallback) {
        DbController<?> dbController = DbController.getInstance(context, NftTypeInfo.class);
        dbController.removeAll(new WhereCondition[]{NftTypeInfoDao.Properties.TokenAddress.eq(nftTypeInfo.getTokenAddress())});
        dbController.insertOrReplace((DbController<?>) nftTypeInfo, iCallback);
        DbController<?> dbController2 = DbController.getInstance(context, NftItemInfo.class);
        dbController2.removeAll(new WhereCondition[]{NftItemInfoDao.Properties.WalletAddress.eq(nftTypeInfo.getWalletAddress()), NftItemInfoDao.Properties.TokenAddress.eq(nftTypeInfo.getTokenAddress())});
        dbController2.insertOrReplace((List<?>) nftTypeInfo.getCollectionInfoList(), new ICallback<Boolean>() {
            @Override
            public void onSubscribe(Disposable disposable) {
            }

            @Override
            public void onResponse(String str, Boolean bool) {
                LogUtils.w(TAG, "insert nft items successfully");
            }

            @Override
            public void onFailure(String str, String str2) {
                LogUtils.w(TAG, "insert nft items failed");
            }
        });
    }

    @Override
    public Observable<NftDetailOutput> getNftItemInfos(String str, RequestBody requestBody) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getCollectionInfos(str, requestBody).compose(RxSchedulers.io_main());
    }
}
