package com.tron.wallet.business.assetshome;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.bugly.Bugly;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.AssetsManager;
import com.tron.wallet.business.addassets2.HomeAssetsModel;
import com.tron.wallet.business.addassets2.bean.asset.AssetsHomeData;
import com.tron.wallet.business.addassets2.bean.asset.AssetsHomeOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftAssetOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftTokenBean;
import com.tron.wallet.business.addassets2.repository.KVController;
import com.tron.wallet.business.assetshome.AssetsContract;
import com.tron.wallet.business.nft.dao.DbController;
import com.tron.wallet.db.Controller.TRXAccountBalanceController;
import com.tron.wallet.db.bean.TRXAccountBalanceBean;
import com.tron.wallet.db.greendao.NftTokenBeanDao;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.greenrobot.greendao.query.WhereCondition;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class AssetsModel implements AssetsContract.Model {
    private final HomeAssetsModel homeAssetsModel = new HomeAssetsModel();

    @Override
    public Observable<AssetsHomeOutput> getAssetsHomeData(String str) {
        return AssetsManager.getInstance().requestFollowAssets(str);
    }

    @Override
    public Observable<NftAssetOutput> getNftTokens(Context context, String str) {
        return Observable.unsafeCreate(new ObservableSource() {
            @Override
            public final void subscribe(Observer observer) {
                AssetsModel.lambda$getNftTokens$0(observer);
            }
        }).compose(RxSchedulers.io_main());
    }

    public static void lambda$getNftTokens$0(Observer observer) {
        List<NftTokenBean> sortedFollowCollections = AssetsManager.getInstance().getSortedFollowCollections();
        NftAssetOutput nftAssetOutput = new NftAssetOutput();
        nftAssetOutput.setData(sortedFollowCollections);
        observer.onNext(nftAssetOutput);
        observer.onComplete();
    }

    @Override
    public void saveNftTokens(Context context, String str, List<NftTokenBean> list) {
        for (NftTokenBean nftTokenBean : list) {
            nftTokenBean.setWalletAddress(str);
        }
        DbController<?> dbController = DbController.getInstance(context, NftTokenBean.class);
        dbController.removeAll(new WhereCondition[]{NftTokenBeanDao.Properties.WalletAddress.eq(str)});
        dbController.insertOrReplace((List<?>) list, new ICallback<Boolean>() {
            @Override
            public void onSubscribe(Disposable disposable) {
            }

            @Override
            public void onResponse(String str2, Boolean bool) {
                LogUtils.w("AssetsModel", "insert nft tokens successfully");
            }

            @Override
            public void onFailure(String str2, String str3) {
                LogUtils.w("AssetsModel", "insert nft tokens failed");
            }
        });
    }

    @Override
    public Observable<NftAssetOutput> requestNftTokens(String str) {
        return AssetsManager.getInstance().requestUserCollections(str);
    }

    @Override
    public boolean getHideLittleAssets(String str) {
        KVController kVController = KVController.getInstance();
        String value = kVController.getValue(str + "-hideLittleAssets");
        return value != null && value.equals("true");
    }

    @Override
    public boolean getHideScamTokenAssets(String str) {
        KVController kVController = KVController.getInstance();
        String value = kVController.getValue(str + "-hideScamTokens");
        return value != null && value.equals("true");
    }

    @Override
    public Observable<Boolean> setHideLittleAssets(String str, final boolean z) {
        return Observable.just(str).map(new Function() {
            @Override
            public final Object apply(Object obj) {
                return AssetsModel.lambda$setHideLittleAssets$1(z, (String) obj);
            }
        }).compose(RxSchedulers.io_main());
    }

    public static Boolean lambda$setHideLittleAssets$1(boolean z, String str) throws Exception {
        KVController kVController = KVController.getInstance();
        kVController.setValue(str + "-hideLittleAssets", z ? "true" : Bugly.SDK_IS_DEV);
        return true;
    }

    @Override
    public Observable<Boolean> setHideScamTokenAssets(String str, final boolean z) {
        return Observable.just(str).map(new Function() {
            @Override
            public final Object apply(Object obj) {
                return AssetsModel.lambda$setHideScamTokenAssets$2(z, (String) obj);
            }
        }).compose(RxSchedulers.io_main());
    }

    public static Boolean lambda$setHideScamTokenAssets$2(boolean z, String str) throws Exception {
        KVController kVController = KVController.getInstance();
        kVController.setValue(str + "-hideScamTokens", z ? "true" : Bugly.SDK_IS_DEV);
        return true;
    }

    @Override
    public Observable<AssetsHomeData> getLocalData(final Wallet wallet, final Protocol.Account account) {
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                AssetsModel.lambda$getLocalData$3(Wallet.this, account, observableEmitter);
            }
        }).compose(RxSchedulers.io_main());
    }

    public static void lambda$getLocalData$3(Wallet wallet, Protocol.Account account, ObservableEmitter observableEmitter) throws Exception {
        AssetsHomeData sortedFollowAssets = AssetsManager.getInstance().getSortedFollowAssets();
        if (sortedFollowAssets == null) {
            sortedFollowAssets = new AssetsHomeData();
            sortedFollowAssets.address = wallet.getAddress();
            sortedFollowAssets.totalTRX = account.getBalance() / 1000000.0d;
            sortedFollowAssets.token = new CopyOnWriteArrayList<>();
        }
        List<TRXAccountBalanceBean> queryAll = TRXAccountBalanceController.getInstance(AppContextUtil.getContext()).queryAll();
        if (queryAll != null) {
            Iterator<TRXAccountBalanceBean> it = queryAll.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                TRXAccountBalanceBean next = it.next();
                if (TextUtils.equals(wallet.address, next.getAddress())) {
                    sortedFollowAssets.address = next.getAddress();
                    sortedFollowAssets.totalTRX = next.getTotalTrx();
                    break;
                }
            }
        }
        observableEmitter.onNext(sortedFollowAssets);
        observableEmitter.onComplete();
    }
}
