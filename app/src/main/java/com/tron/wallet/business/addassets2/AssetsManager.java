package com.tron.wallet.business.addassets2;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxBus;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.addassets2.AssetsManager;
import com.tron.wallet.business.addassets2.bean.AssetsData;
import com.tron.wallet.business.addassets2.bean.AssetsDataOutput;
import com.tron.wallet.business.addassets2.bean.AssetsQueryOutput;
import com.tron.wallet.business.addassets2.bean.DelMyAssetsInput;
import com.tron.wallet.business.addassets2.bean.DelMyAssetsOutput;
import com.tron.wallet.business.addassets2.bean.FollowAssetsInput;
import com.tron.wallet.business.addassets2.bean.FollowAssetsOutput;
import com.tron.wallet.business.addassets2.bean.NftDataOutput;
import com.tron.wallet.business.addassets2.bean.TokenSortType;
import com.tron.wallet.business.addassets2.bean.asset.AssetsHomeData;
import com.tron.wallet.business.addassets2.bean.asset.AssetsHomeOutput;
import com.tron.wallet.business.addassets2.bean.asset.RecommendAssetsHomeOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftAssetOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftTokenBean;
import com.tron.wallet.business.addassets2.repository.FollowAssetsSortListController;
import com.tron.wallet.business.addassets2.repository.HotAssetsController;
import com.tron.wallet.business.addassets2.repository.KVController;
import com.tron.wallet.business.addassets2.repository.MyAssetsController;
import com.tron.wallet.business.addassets2.repository.MyCollectionsController;
import com.tron.wallet.business.addassets2.repository.NewAssetsController;
import com.tron.wallet.business.addassets2.sort.TokenSortByName;
import com.tron.wallet.business.addassets2.sort.TokenSortByUser;
import com.tron.wallet.business.addassets2.sort.TokenSortByValue;
import com.tron.wallet.business.nft.dao.NftTokenBeanController;
import com.tron.wallet.business.walletmanager.selectwallet.controller.RecentlyWalletController;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.task.PriceUpdater;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.rb.RB;
import com.tron.wallet.db.Controller.AssetsHomeDataDaoManager;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletType;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.bouncycastle.util.encoders.Hex;
import org.tron.walletserver.Wallet;
public class AssetsManager {
    private static final String TAG = "AssetsManager";
    private static AssetsManager instance;
    private boolean hasGotNewAssets;
    private RxManager rxManager = new RxManager();
    private String selectedAddress;
    private int walletType;

    public String getSelectedAddress() {
        return this.selectedAddress;
    }

    private AssetsManager() {
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        if (selectedWallet != null) {
            this.selectedAddress = selectedWallet.getAddress();
            this.walletType = WalletType.getType(selectedWallet);
            RecentlyWalletController.setRecentlyWallet(selectedWallet);
        }
        refreshNewAssets();
        this.rxManager.on(Event.SELECTEDWALLET, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$new$0(obj);
            }
        });
        this.rxManager.on(Event.SWITCH_CHAIN, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$new$1(obj);
            }
        });
        this.rxManager.on(Event.NET_CHANGE, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$new$2(obj);
            }
        });
        this.rxManager.on(Event.WS_MSG_NEW_ASSETS, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$new$3(obj);
            }
        });
    }

    public void lambda$new$0(Object obj) throws Exception {
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        if (selectedWallet == null || selectedWallet.getAddress().equals(this.selectedAddress)) {
            return;
        }
        this.selectedAddress = selectedWallet.getAddress();
        this.walletType = WalletType.getType(selectedWallet);
        refreshNewAssets();
    }

    public void lambda$new$1(Object obj) throws Exception {
        AssetsConfig.setIsMainChain(SpAPI.THIS.getCurIsMainChain());
        NewAssetsController.getInstance().clear();
        refreshNewAssets();
    }

    public void lambda$new$2(Object obj) throws Exception {
        if (this.hasGotNewAssets) {
            return;
        }
        refreshNewAssets();
    }

    public void lambda$new$3(Object obj) throws Exception {
        refreshNewAssets();
    }

    public static AssetsManager getInstance() {
        if (instance == null) {
            synchronized (AssetsManager.class) {
                if (instance == null) {
                    instance = new AssetsManager();
                }
            }
        }
        return instance;
    }

    public static List<TokenBean> transferNftTokenBeanToTokenBean(List<NftTokenBean> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (NftTokenBean nftTokenBean : list) {
                arrayList.add(nftTokenBean.convertToTokenBean());
            }
        }
        return arrayList;
    }

    public static boolean sameTokenBean(TokenBean tokenBean, TokenBean tokenBean2) {
        if (tokenBean == null || tokenBean2 == null || tokenBean.type != tokenBean2.type) {
            return false;
        }
        if (tokenBean.type == 0 && tokenBean2.type == 0) {
            return true;
        }
        if (!TextUtils.isEmpty(tokenBean.contractAddress) && !TextUtils.isEmpty(tokenBean2.contractAddress)) {
            return TextUtils.equals(tokenBean.contractAddress, tokenBean2.getContractAddress());
        }
        return TextUtils.equals(tokenBean.id, tokenBean2.id);
    }

    public String getHexAddress() {
        return getHexAddress(this.selectedAddress);
    }

    private String getHexAddress(String str) {
        byte[] decode58Check;
        if (StringTronUtil.isEmpty(str) || (decode58Check = StringTronUtil.decode58Check(str)) == null) {
            return null;
        }
        return Hex.toHexString(decode58Check);
    }

    public void refreshNewAssets() {
        LogUtils.d(TAG, "requestNewAssets");
        if (getHexAddress() == null) {
            return;
        }
        this.hasGotNewAssets = false;
        requestNewAssets().subscribe(new IObserver(new ICallback<AssetsDataOutput>() {
            @Override
            public void onSubscribe(Disposable disposable) {
            }

            @Override
            public void onResponse(String str, AssetsDataOutput assetsDataOutput) {
                if (assetsDataOutput == null || assetsDataOutput.getCode() != 0) {
                    return;
                }
                hasGotNewAssets = true;
                AssetsData data = assetsDataOutput.getData();
                StringBuilder sb = new StringBuilder("requestNewAssets: ");
                sb.append(data == null ? 0 : data.getCount());
                LogUtils.d(AssetsManager.TAG, sb.toString());
                if (data != null) {
                    NewAssetsController.getInstance().setNewAssets(selectedAddress, data);
                    rxManager.post(Event.ASSETS_NEW, Integer.valueOf(data.getCount()));
                }
            }

            @Override
            public void onFailure(String str, String str2) {
                LogUtils.d(AssetsManager.TAG, "requestNewAssets fail: " + str + ":" + str2);
            }
        }, "requestNewAssets"));
    }

    public Observable<AssetsDataOutput> requestNewAssets() {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).requestNewAssets(getHexAddress()).compose(RxSchedulers.io_main());
    }

    public Observable<AssetsHomeOutput> requestFollowAssets() {
        return requestFollowAssets(this.selectedAddress);
    }

    public Observable<AssetsHomeOutput> requestFollowAssets(final String str) {
        String str2 = this.selectedAddress;
        if (str2 != null && str2.equals(str)) {
            return Observable.unsafeCreate(new ObservableSource<Boolean>() {
                @Override
                public void subscribe(Observer<? super Boolean> observer) {
                    observer.onNext(Boolean.valueOf(AssetsHomeDataDaoManager.hasPending(selectedAddress)));
                    observer.onComplete();
                }
            }).subscribeOn(Schedulers.io()).concatMap(new Function() {
                @Override
                public final Object apply(Object obj) {
                    ObservableSource lambda$requestFollowAssets$4;
                    lambda$requestFollowAssets$4 = lambda$requestFollowAssets$4((Boolean) obj);
                    return lambda$requestFollowAssets$4;
                }
            }).concatMap(new Function() {
                @Override
                public final Object apply(Object obj) {
                    ObservableSource lambda$requestFollowAssets$5;
                    lambda$requestFollowAssets$5 = lambda$requestFollowAssets$5(str, (FollowAssetsOutput) obj);
                    return lambda$requestFollowAssets$5;
                }
            }).compose(RxSchedulers.io_main());
        }
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).requestFollowAssets(getHexAddress(str), "v2").compose(RxSchedulers.io_main());
    }

    public ObservableSource lambda$requestFollowAssets$4(Boolean bool) throws Exception {
        List<TokenBean> tokenList;
        FollowAssetsOutput followAssetsOutput = new FollowAssetsOutput();
        followAssetsOutput.setCode(0);
        followAssetsOutput.setData(true);
        Observable just = Observable.just(followAssetsOutput);
        if (!bool.booleanValue() || (tokenList = AssetsHomeDataDaoManager.getTokenList(AppContextUtil.getmApplication(), this.selectedAddress)) == null || tokenList.size() <= 0) {
            return just;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (TokenBean tokenBean : tokenList) {
            if (tokenBean.doDb == 1) {
                arrayList.add(tokenBean);
            } else if (tokenBean.doDb == 2) {
                arrayList2.add(tokenBean);
            }
        }
        return requestModifiedFollowAssets(arrayList, arrayList2, false);
    }

    public ObservableSource lambda$requestFollowAssets$5(String str, FollowAssetsOutput followAssetsOutput) throws Exception {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).requestFollowAssets(getHexAddress(str), "v2");
    }

    public Observable<NftAssetOutput> requestUserCollections(final String str) {
        String str2 = this.selectedAddress;
        if (str2 != null && str2.equals(str)) {
            return Observable.unsafeCreate(new ObservableSource<Boolean>() {
                @Override
                public void subscribe(Observer<? super Boolean> observer) {
                    observer.onNext(Boolean.valueOf(NftTokenBeanController.getInstance().hasPending(str)));
                    observer.onComplete();
                }
            }).subscribeOn(Schedulers.io()).concatMap(new Function() {
                @Override
                public final Object apply(Object obj) {
                    ObservableSource lambda$requestUserCollections$6;
                    lambda$requestUserCollections$6 = lambda$requestUserCollections$6(str, (Boolean) obj);
                    return lambda$requestUserCollections$6;
                }
            }).concatMap(new Function() {
                @Override
                public final Object apply(Object obj) {
                    ObservableSource lambda$requestUserCollections$7;
                    lambda$requestUserCollections$7 = lambda$requestUserCollections$7(str, (FollowAssetsOutput) obj);
                    return lambda$requestUserCollections$7;
                }
            }).compose(RxSchedulers.io_main());
        }
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getAllCollections(getHexAddress(str), "v2").compose(RxSchedulers.io_main());
    }

    public ObservableSource lambda$requestUserCollections$6(String str, Boolean bool) throws Exception {
        List<NftTokenBean> tokenList;
        FollowAssetsOutput followAssetsOutput = new FollowAssetsOutput();
        followAssetsOutput.setCode(0);
        followAssetsOutput.setData(true);
        Observable just = Observable.just(followAssetsOutput);
        if (!bool.booleanValue() || (tokenList = NftTokenBeanController.getInstance().getTokenList(AppContextUtil.getmApplication(), str)) == null || tokenList.size() <= 0) {
            return just;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (NftTokenBean nftTokenBean : tokenList) {
            if (nftTokenBean.getDoDb() == 1) {
                arrayList.add(nftTokenBean);
            } else if (nftTokenBean.getDoDb() == 2) {
                arrayList2.add(nftTokenBean);
            }
        }
        return requestModifiedFollowAssets(transferNftTokenBeanToTokenBean(arrayList), transferNftTokenBeanToTokenBean(arrayList2), false);
    }

    public ObservableSource lambda$requestUserCollections$7(String str, FollowAssetsOutput followAssetsOutput) throws Exception {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getAllCollections(getHexAddress(str), "v2");
    }

    public Observable<AssetsHomeData> getFollowAssets() {
        return Observable.unsafeCreate(new ObservableSource<AssetsHomeData>() {
            @Override
            public void subscribe(Observer<? super AssetsHomeData> observer) {
                observer.onNext(AssetsHomeDataDaoManager.getAddAssetsHomeData(AppContextUtil.getContext(), selectedAddress));
                observer.onComplete();
            }
        }).compose(RxSchedulers.io_main());
    }

    public Observable<List<NftTokenBean>> getFollowCollections() {
        return Observable.unsafeCreate(new ObservableSource<List<NftTokenBean>>() {
            @Override
            public void subscribe(Observer<? super List<NftTokenBean>> observer) {
                observer.onNext(NftTokenBeanController.getInstance().getAllAddedTokenList(selectedAddress));
                observer.onComplete();
            }
        }).compose(RxSchedulers.io_main());
    }

    public AssetsHomeData getSortedFollowAssets(TokenSortType tokenSortType) {
        AssetsHomeData addAssetsHomeData = AssetsHomeDataDaoManager.getAddAssetsHomeData(AppContextUtil.getmApplication(), this.selectedAddress);
        if (addAssetsHomeData != null) {
            if (TokenSortType.SORT_BY_USER.equals(tokenSortType)) {
                new TokenSortByUser(FollowAssetsSortListController.getInstance().getTokenSortList(this.selectedAddress)).sort(addAssetsHomeData.token);
            } else if (TokenSortType.SORT_BY_VALUE.equals(tokenSortType)) {
                new TokenSortByValue(PriceUpdater.getTRX_price()).sort(addAssetsHomeData.token);
            } else {
                new TokenSortByName().sort(addAssetsHomeData.token);
            }
        }
        return addAssetsHomeData;
    }

    public AssetsHomeData getSortedFollowAssets() {
        return getSortedFollowAssets(KVController.getInstance().getTokenSortType(this.selectedAddress));
    }

    public List<NftTokenBean> getSortedFollowCollections(TokenSortType tokenSortType) {
        List<NftTokenBean> allAddedTokenList = NftTokenBeanController.getInstance().getAllAddedTokenList(this.selectedAddress);
        if (allAddedTokenList != null) {
            List<TokenBean> transferNftTokenBeanToTokenBean = transferNftTokenBeanToTokenBean(allAddedTokenList);
            if (TokenSortType.SORT_BY_USER.equals(tokenSortType)) {
                new TokenSortByUser(FollowAssetsSortListController.getInstance().getTokenSortList(this.selectedAddress, 5)).sort(transferNftTokenBeanToTokenBean);
            } else if (TokenSortType.SORT_BY_VALUE.equals(tokenSortType)) {
                new TokenSortByValue(PriceUpdater.getTRX_price()).sort(transferNftTokenBeanToTokenBean);
            } else {
                new TokenSortByName().sort(transferNftTokenBeanToTokenBean);
            }
            allAddedTokenList.clear();
            for (TokenBean tokenBean : transferNftTokenBeanToTokenBean) {
                allAddedTokenList.add((NftTokenBean) tokenBean.getExtraData());
            }
        }
        return allAddedTokenList;
    }

    public List<NftTokenBean> getSortedFollowCollections() {
        return getSortedFollowCollections(KVController.getInstance().getTokenSortType(this.selectedAddress));
    }

    public Observable<AssetsDataOutput> requestSearchAssets(String str, int i, int i2, int i3) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).requestSearchAssets(getHexAddress(), str, i, i2, i3, "v2");
    }

    public Observable<AssetsDataOutput> requestSearchAssets(String str, String str2, int i, int i2, int i3) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).requestSearchAssets(getHexAddress(str), str2, i, i2, i3, "v2");
    }

    public Observable<AssetsQueryOutput> requestGetAsset(int i, String str) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).requestGetAsset(getHexAddress(), i, str).compose(RxSchedulers.io_main());
    }

    public Observable<AssetsQueryOutput> requestGetAsset(String str, int i, String str2) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).requestGetAsset(getHexAddress(str), i, str2).compose(RxSchedulers.io_main());
    }

    public void requestModifiedFollowAssets(Context context) {
        List<TokenBean> tokenList;
        String str = this.selectedAddress;
        if (str == null || (tokenList = AssetsHomeDataDaoManager.getTokenList(context, str)) == null || tokenList.size() <= 1) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (TokenBean tokenBean : tokenList) {
            if (tokenBean.doDb == 1) {
                arrayList.add(tokenBean);
            } else if (tokenBean.doDb == 2) {
                arrayList2.add(tokenBean);
            }
        }
        requestModifiedFollowAssets(arrayList, arrayList2, false).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<FollowAssetsOutput>() {
            @Override
            public void onFailure(String str2, String str3) {
            }

            @Override
            public void onResponse(String str2, FollowAssetsOutput followAssetsOutput) {
                if (followAssetsOutput == null || followAssetsOutput.getCode() != 0) {
                    return;
                }
                rxManager.post(RB.RB_HOMEASSET_DB, true);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                rxManager.add(disposable);
            }
        }, "requestModifiedFollowAssets"));
    }

    private void clearAssetsDbState(List<TokenBean> list, List<TokenBean> list2) {
        if (list != null && list.size() > 0) {
            ArrayList<TokenBean> arrayList = new ArrayList();
            ArrayList<NftTokenBean> arrayList2 = new ArrayList();
            for (TokenBean tokenBean : list) {
                if (tokenBean.getType() == 5) {
                    arrayList2.add((tokenBean.getExtraData() == null || !(tokenBean.getExtraData() instanceof NftTokenBean)) ? NftTokenBean.covertFromTokenBean(tokenBean) : (NftTokenBean) tokenBean.getExtraData());
                } else {
                    arrayList.add(tokenBean);
                }
            }
            if (arrayList.size() > 0) {
                for (TokenBean tokenBean2 : arrayList) {
                    tokenBean2.doDb = 0;
                    tokenBean2.ispendinguploading = 0;
                }
                AssetsHomeDataDaoManager.updateTokens(arrayList, this.selectedAddress);
            }
            if (arrayList2.size() > 0) {
                for (NftTokenBean nftTokenBean : arrayList2) {
                    nftTokenBean.setWalletAddress(this.selectedAddress);
                    nftTokenBean.setDoDb(0);
                }
                NftTokenBeanController.getInstance().updateTokens(arrayList2, this.selectedAddress);
            }
        }
        if (list2 == null || list2.size() <= 0) {
            return;
        }
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        for (TokenBean tokenBean3 : list2) {
            if (tokenBean3.getType() == 5) {
                arrayList4.add((tokenBean3.getExtraData() == null || !(tokenBean3.getExtraData() instanceof NftTokenBean)) ? NftTokenBean.covertFromTokenBean(tokenBean3) : (NftTokenBean) tokenBean3.getExtraData());
            } else {
                arrayList3.add(tokenBean3);
            }
        }
        if (arrayList3.size() > 0) {
            AssetsHomeDataDaoManager.deleteTokens(arrayList3, this.selectedAddress);
        }
        if (arrayList4.size() > 0) {
            NftTokenBeanController.getInstance().deleteTokens(arrayList4, this.selectedAddress);
        }
    }

    private void cacheAssetsDbState(List<TokenBean> list, List<TokenBean> list2) {
        if (list != null && list.size() > 0) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (TokenBean tokenBean : list) {
                if (tokenBean.getType() == 5) {
                    arrayList2.add((tokenBean.getExtraData() == null || !(tokenBean.getExtraData() instanceof NftTokenBean)) ? NftTokenBean.covertFromTokenBean(tokenBean) : (NftTokenBean) tokenBean.getExtraData());
                } else {
                    arrayList.add(tokenBean);
                }
            }
            if (arrayList.size() > 0) {
                AssetsHomeDataDaoManager.addFollowedTokens(arrayList, this.selectedAddress);
            }
            if (arrayList2.size() > 0) {
                NftTokenBeanController.getInstance().addFollowedTokens(arrayList2, this.selectedAddress);
            }
        }
        if (list2 != null && list2.size() > 0) {
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            for (TokenBean tokenBean2 : list2) {
                if (tokenBean2.getType() == 5) {
                    arrayList4.add((tokenBean2.getExtraData() == null || !(tokenBean2.getExtraData() instanceof NftTokenBean)) ? NftTokenBean.covertFromTokenBean(tokenBean2) : (NftTokenBean) tokenBean2.getExtraData());
                } else {
                    arrayList3.add(tokenBean2);
                }
            }
            if (arrayList3.size() > 0) {
                AssetsHomeDataDaoManager.updateUnFollowedTokens(arrayList3, this.selectedAddress);
            }
            if (arrayList4.size() > 0) {
                NftTokenBeanController.getInstance().updateUnFollowedTokens(arrayList4, this.selectedAddress);
            }
        }
        this.rxManager.post(RB.RB_HOMEASSET_DB, true);
    }

    public Observable<FollowAssetsOutput> requestModifiedFollowAssets(List<TokenBean> list, List<TokenBean> list2) {
        return requestModifiedFollowAssets(list, list2, true).compose(RxSchedulers.io_main());
    }

    private Observable<FollowAssetsOutput> requestModifiedFollowAssets(final List<TokenBean> list, final List<TokenBean> list2, boolean z) {
        return Observable.just(Boolean.valueOf(z)).subscribeOn(Schedulers.io()).concatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$requestModifiedFollowAssets$8;
                lambda$requestModifiedFollowAssets$8 = lambda$requestModifiedFollowAssets$8(list, list2, (Boolean) obj);
                return lambda$requestModifiedFollowAssets$8;
            }
        }).map(new Function() {
            @Override
            public final Object apply(Object obj) {
                FollowAssetsOutput lambda$requestModifiedFollowAssets$9;
                lambda$requestModifiedFollowAssets$9 = lambda$requestModifiedFollowAssets$9(list, list2, (FollowAssetsOutput) obj);
                return lambda$requestModifiedFollowAssets$9;
            }
        });
    }

    public ObservableSource lambda$requestModifiedFollowAssets$8(List list, List list2, Boolean bool) throws Exception {
        if (bool.booleanValue()) {
            cacheAssetsDbState(list, list2);
        }
        FollowAssetsInput followAssetsInput = new FollowAssetsInput();
        followAssetsInput.setAddress(getHexAddress());
        if (list != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                TokenBean tokenBean = (TokenBean) it.next();
                if (tokenBean.getType() == 1 && !TextUtils.isEmpty(tokenBean.getId())) {
                    arrayList.add(tokenBean.getId());
                } else if (tokenBean.getType() == 2 && !TextUtils.isEmpty(tokenBean.getContractAddress())) {
                    arrayList2.add(tokenBean.getContractAddress());
                } else if (tokenBean.getType() == 5 && !TextUtils.isEmpty(tokenBean.getContractAddress())) {
                    arrayList3.add(tokenBean.getContractAddress());
                }
            }
            followAssetsInput.setToken10(arrayList);
            followAssetsInput.setToken20(arrayList2);
            followAssetsInput.setToken721(arrayList3);
        }
        if (list2 != null) {
            ArrayList arrayList4 = new ArrayList();
            ArrayList arrayList5 = new ArrayList();
            ArrayList arrayList6 = new ArrayList();
            Iterator it2 = list2.iterator();
            while (it2.hasNext()) {
                TokenBean tokenBean2 = (TokenBean) it2.next();
                if (tokenBean2.getType() == 1 && !TextUtils.isEmpty(tokenBean2.getId())) {
                    arrayList4.add(tokenBean2.getId());
                } else if (tokenBean2.getType() == 2 && !TextUtils.isEmpty(tokenBean2.getContractAddress())) {
                    arrayList5.add(tokenBean2.getContractAddress());
                } else if (tokenBean2.getType() == 5 && !TextUtils.isEmpty(tokenBean2.getContractAddress())) {
                    arrayList6.add(tokenBean2.getContractAddress());
                }
            }
            followAssetsInput.setToken10Cancel(arrayList4);
            followAssetsInput.setToken20Cancel(arrayList5);
            followAssetsInput.setToken721Cancel(arrayList6);
        }
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).requestModifyFollowAssets(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(followAssetsInput)));
    }

    public FollowAssetsOutput lambda$requestModifiedFollowAssets$9(List list, List list2, FollowAssetsOutput followAssetsOutput) throws Exception {
        clearAssetsDbState(list, list2);
        return followAssetsOutput;
    }

    private boolean containsToken(List<TokenBean> list, TokenBean tokenBean) {
        if (list == null) {
            return false;
        }
        for (TokenBean tokenBean2 : list) {
            if (sameTokenBean(tokenBean2, tokenBean)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsNftToken(List<NftTokenBean> list, TokenBean tokenBean) {
        for (NftTokenBean nftTokenBean : list) {
            if (!StringTronUtil.isEmpty(nftTokenBean.getContractAddress()) && nftTokenBean.getContractAddress().equals(tokenBean.getContractAddress())) {
                return true;
            }
        }
        return false;
    }

    public void refineFollowAssetsState(List<TokenBean> list) {
        if (list == null) {
            return;
        }
        AssetsHomeData addAssetsHomeData = AssetsHomeDataDaoManager.getAddAssetsHomeData(AppContextUtil.getmApplication(), this.selectedAddress);
        List<NftTokenBean> allAddedTokenList = NftTokenBeanController.getInstance().getAllAddedTokenList(this.selectedAddress);
        boolean z = (addAssetsHomeData == null || addAssetsHomeData.token == null) ? false : true;
        boolean z2 = allAddedTokenList != null;
        for (TokenBean tokenBean : list) {
            tokenBean.isInAssets = (z && containsToken(addAssetsHomeData.token, tokenBean)) || (z2 && containsNftToken(allAddedTokenList, tokenBean));
        }
    }

    public TokenBean queryToken(String str, String str2) {
        NftTokenBean token;
        NftTokenBean token2;
        TokenBean token3 = AssetsHomeDataDaoManager.getToken(str, str2);
        if (token3 != null) {
            return token3;
        }
        if (!StringTronUtil.isEmpty(str2) && (token2 = NftTokenBeanController.getInstance().getToken(str, str2)) != null) {
            return token2.convertToTokenBean();
        }
        TokenBean token4 = MyAssetsController.getInstance().getToken(str, str2);
        return (token4 != null || StringTronUtil.isEmpty(str2) || (token = MyCollectionsController.getInstance().getToken(str, str2)) == null) ? token4 : token.convertToTokenBean();
    }

    public static class HotAssets {
        public static Observable<AssetsData> getHotAssets() {
            return Observable.unsafeCreate(new ObservableSource<AssetsData>() {
                @Override
                public void subscribe(Observer<? super AssetsData> observer) {
                    List<TokenBean> hotAssets = HotAssetsController.getInstance().getHotAssets();
                    AssetsData assetsData = new AssetsData();
                    if (hotAssets != null) {
                        AssetsManager.getInstance().refineFollowAssetsState(hotAssets);
                        assetsData.setCount(hotAssets.size());
                        assetsData.setTokens(hotAssets);
                    }
                    observer.onNext(assetsData);
                    observer.onComplete();
                }
            }).compose(RxSchedulers.io_main());
        }

        public static Observable<Boolean> saveHotAssets(AssetsData assetsData) {
            return Observable.just(assetsData).map(new Function() {
                @Override
                public final Object apply(Object obj) {
                    Boolean valueOf;
                    valueOf = Boolean.valueOf(r1 != null ? HotAssetsController.getInstance().setHotAssets(((AssetsData) obj).getTokens()) : true);
                    return valueOf;
                }
            }).compose(RxSchedulers.io_main());
        }

        public static Observable<RecommendAssetsHomeOutput> requestHotAssets() {
            return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).requestTopTokens(AssetsManager.getInstance().getHexAddress()).compose(RxSchedulers.io_main());
        }
    }

    public static class MyAssets {
        public static Observable<Boolean> saveMyAssets(final AssetsData assetsData) {
            return Observable.unsafeCreate(new ObservableSource<Boolean>() {
                @Override
                public void subscribe(Observer<? super Boolean> observer) {
                    observer.onNext(Boolean.valueOf(AssetsData.this != null ? MyAssetsController.getInstance().setMyAssets(AssetsManager.getInstance().getSelectedAddress(), AssetsData.this.getTokens()) : true));
                    observer.onComplete();
                }
            }).compose(RxSchedulers.io_main());
        }

        public static Observable<AssetsData> getMyAssets() {
            return Observable.unsafeCreate(new ObservableSource<AssetsData>() {
                @Override
                public void subscribe(Observer<? super AssetsData> observer) {
                    List<TokenBean> myAssets = MyAssetsController.getInstance().getMyAssets(AssetsManager.getInstance().getSelectedAddress());
                    AssetsData assetsData = new AssetsData();
                    if (myAssets != null) {
                        assetsData.setCount(myAssets.size());
                        assetsData.setTokens(myAssets);
                    }
                    observer.onNext(assetsData);
                    observer.onComplete();
                }
            }).compose(RxSchedulers.io_main());
        }

        public static Observable<AssetsDataOutput> requestMyAssets() {
            return Observable.fromCallable(new Callable() {
                @Override
                public final Object call() {
                    Boolean valueOf;
                    valueOf = Boolean.valueOf(MyAssetsController.getInstance().hasPending(AssetsManager.getInstance().getSelectedAddress()));
                    return valueOf;
                }
            }).subscribeOn(Schedulers.io()).concatMap(new Function() {
                @Override
                public final Object apply(Object obj) {
                    return AssetsManager.MyAssets.lambda$requestMyAssets$1((Boolean) obj);
                }
            }).concatMap(new Function() {
                @Override
                public final Object apply(Object obj) {
                    ObservableSource compose;
                    DelMyAssetsOutput delMyAssetsOutput = (DelMyAssetsOutput) obj;
                    compose = ((HttpAPI) IRequest.getAPI(HttpAPI.class)).requestMyAssets(AssetsManager.getInstance().getHexAddress()).compose(RxSchedulers.io_main());
                    return compose;
                }
            }).compose(RxSchedulers.io_main());
        }

        public static ObservableSource lambda$requestMyAssets$1(Boolean bool) throws Exception {
            List<TokenBean> pendingTokens;
            if (bool.booleanValue() && (pendingTokens = MyAssetsController.getInstance().getPendingTokens(AssetsManager.getInstance().getSelectedAddress())) != null && pendingTokens.size() > 0) {
                ArrayList arrayList = new ArrayList();
                for (TokenBean tokenBean : pendingTokens) {
                    if (tokenBean.doDb == 2) {
                        arrayList.add(tokenBean);
                    }
                }
                return requestDelMyAssets(arrayList, false);
            }
            DelMyAssetsOutput delMyAssetsOutput = new DelMyAssetsOutput();
            delMyAssetsOutput.setCode(0);
            delMyAssetsOutput.setData(true);
            return Observable.just(delMyAssetsOutput);
        }

        public static Observable<AssetsDataOutput> requestMyAssets(String str) {
            return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).requestMyAssets(str);
        }

        public static Observable<DelMyAssetsOutput> requestDelMyAssets(List<TokenBean> list) {
            return requestDelMyAssets(list, true).compose(RxSchedulers.io_main());
        }

        private static Observable<DelMyAssetsOutput> requestDelMyAssets(final List<TokenBean> list, boolean z) {
            final String selectedAddress = AssetsManager.getInstance().getSelectedAddress();
            return Observable.just(Boolean.valueOf(z)).subscribeOn(Schedulers.io()).concatMap(new Function() {
                @Override
                public final Object apply(Object obj) {
                    return AssetsManager.MyAssets.lambda$requestDelMyAssets$3(list, selectedAddress, (Boolean) obj);
                }
            }).map(new Function() {
                @Override
                public final Object apply(Object obj) {
                    return AssetsManager.MyAssets.lambda$requestDelMyAssets$4(list, selectedAddress, (DelMyAssetsOutput) obj);
                }
            });
        }

        public static ObservableSource lambda$requestDelMyAssets$3(List list, String str, Boolean bool) throws Exception {
            if (bool.booleanValue()) {
                MyAssetsController.getInstance().toDeleteTokens(list, str);
                RxBus.getInstance().post(RB.RB_HOMEASSET_DB, true);
            }
            DelMyAssetsInput delMyAssetsInput = new DelMyAssetsInput();
            delMyAssetsInput.setAddress(AssetsManager.getInstance().getHexAddress());
            if (list != null) {
                ArrayList arrayList = new ArrayList();
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    TokenBean tokenBean = (TokenBean) it.next();
                    if (tokenBean.getType() == 1 && !TextUtils.isEmpty(tokenBean.getId())) {
                        arrayList.add(tokenBean.getId());
                    } else if (tokenBean.getType() == 2 && !TextUtils.isEmpty(tokenBean.getContractAddress())) {
                        arrayList.add(tokenBean.getContractAddress());
                    }
                }
                delMyAssetsInput.setTokenDel(arrayList);
            }
            return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).requestDelAssets(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(delMyAssetsInput)));
        }

        public static DelMyAssetsOutput lambda$requestDelMyAssets$4(List list, String str, DelMyAssetsOutput delMyAssetsOutput) throws Exception {
            if (delMyAssetsOutput != null && delMyAssetsOutput.isData()) {
                MyAssetsController.getInstance().deleteTokens(list, str);
            }
            return delMyAssetsOutput;
        }
    }

    public static class MyCollections {
        public static Observable<Boolean> saveMyCollections(final List<NftTokenBean> list) {
            return Observable.unsafeCreate(new ObservableSource<Boolean>() {
                @Override
                public void subscribe(Observer<? super Boolean> observer) {
                    observer.onNext(Boolean.valueOf(list != null ? MyCollectionsController.getInstance().setMyAssets(AssetsManager.getInstance().getSelectedAddress(), list) : true));
                    observer.onComplete();
                }
            }).compose(RxSchedulers.io_main());
        }

        public static Observable<List<NftTokenBean>> getMyCollections() {
            return Observable.unsafeCreate(new ObservableSource<List<NftTokenBean>>() {
                @Override
                public void subscribe(Observer<? super List<NftTokenBean>> observer) {
                    observer.onNext(MyCollectionsController.getInstance().getMyAssets(AssetsManager.getInstance().getSelectedAddress()));
                    observer.onComplete();
                }
            }).compose(RxSchedulers.io_main());
        }

        public static Observable<NftDataOutput> requestMyCollections() {
            return Observable.fromCallable(new Callable() {
                @Override
                public final Object call() {
                    Boolean valueOf;
                    valueOf = Boolean.valueOf(MyCollectionsController.getInstance().hasPending(AssetsManager.getInstance().getSelectedAddress()));
                    return valueOf;
                }
            }).subscribeOn(Schedulers.io()).concatMap(new Function() {
                @Override
                public final Object apply(Object obj) {
                    return AssetsManager.MyCollections.lambda$requestMyCollections$1((Boolean) obj);
                }
            }).concatMap(new Function() {
                @Override
                public final Object apply(Object obj) {
                    ObservableSource compose;
                    DelMyAssetsOutput delMyAssetsOutput = (DelMyAssetsOutput) obj;
                    compose = ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getMyAllCollections(AssetsManager.getInstance().getHexAddress()).compose(RxSchedulers.io_main());
                    return compose;
                }
            }).compose(RxSchedulers.io_main());
        }

        public static ObservableSource lambda$requestMyCollections$1(Boolean bool) throws Exception {
            List<NftTokenBean> pendingTokens;
            if (bool.booleanValue() && (pendingTokens = MyCollectionsController.getInstance().getPendingTokens(AssetsManager.getInstance().getSelectedAddress())) != null && pendingTokens.size() > 0) {
                ArrayList arrayList = new ArrayList();
                for (NftTokenBean nftTokenBean : pendingTokens) {
                    if (nftTokenBean.getDoDb() == 2) {
                        arrayList.add(nftTokenBean);
                    }
                }
                return requestDelMyCollections(arrayList, false);
            }
            DelMyAssetsOutput delMyAssetsOutput = new DelMyAssetsOutput();
            delMyAssetsOutput.setCode(0);
            delMyAssetsOutput.setData(true);
            return Observable.just(delMyAssetsOutput);
        }

        public static Observable<NftDataOutput> requestMyCollections(String str) {
            return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getMyAllCollections(str);
        }

        public static Observable<DelMyAssetsOutput> requestDelMyCollections(List<NftTokenBean> list) {
            return requestDelMyCollections(list, true).compose(RxSchedulers.io_main());
        }

        private static Observable<DelMyAssetsOutput> requestDelMyCollections(final List<NftTokenBean> list, boolean z) {
            final String selectedAddress = AssetsManager.getInstance().getSelectedAddress();
            return Observable.just(Boolean.valueOf(z)).subscribeOn(Schedulers.io()).concatMap(new Function() {
                @Override
                public final Object apply(Object obj) {
                    return AssetsManager.MyCollections.lambda$requestDelMyCollections$3(list, selectedAddress, (Boolean) obj);
                }
            }).map(new Function() {
                @Override
                public final Object apply(Object obj) {
                    return AssetsManager.MyCollections.lambda$requestDelMyCollections$4(list, selectedAddress, (DelMyAssetsOutput) obj);
                }
            });
        }

        public static ObservableSource lambda$requestDelMyCollections$3(List list, String str, Boolean bool) throws Exception {
            if (bool.booleanValue()) {
                MyCollectionsController.getInstance().toDeleteTokens(list, str);
                RxBus.getInstance().post(RB.RB_HOMEASSET_DB, true);
            }
            DelMyAssetsInput delMyAssetsInput = new DelMyAssetsInput();
            delMyAssetsInput.setAddress(AssetsManager.getInstance().getHexAddress());
            if (list != null) {
                ArrayList arrayList = new ArrayList();
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    NftTokenBean nftTokenBean = (NftTokenBean) it.next();
                    if (nftTokenBean.getType() == 5 && !TextUtils.isEmpty(nftTokenBean.getContractAddress())) {
                        arrayList.add(nftTokenBean.getContractAddress());
                    }
                }
                delMyAssetsInput.setToken721Del(arrayList);
            }
            return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).requestDelAssets(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(delMyAssetsInput)));
        }

        public static DelMyAssetsOutput lambda$requestDelMyCollections$4(List list, String str, DelMyAssetsOutput delMyAssetsOutput) throws Exception {
            if (delMyAssetsOutput != null && delMyAssetsOutput.isData()) {
                MyCollectionsController.getInstance().deleteTokens(list, str);
            }
            return delMyAssetsOutput;
        }
    }
}
