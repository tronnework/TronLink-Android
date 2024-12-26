package com.tron.wallet.business.addassets2;

import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.MyAssetsContract;
import com.tron.wallet.business.addassets2.MyAssetsPresenter;
import com.tron.wallet.business.addassets2.bean.AssetsData;
import com.tron.wallet.business.addassets2.bean.AssetsDataOutput;
import com.tron.wallet.business.addassets2.bean.DelMyAssetsOutput;
import com.tron.wallet.business.addassets2.bean.FollowAssetsOutput;
import com.tron.wallet.business.addassets2.bean.NftDataOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftTokenBean;
import com.tron.wallet.business.assetshome.SortHelper;
import com.tron.wallet.business.nft.NftTokenListActivity;
import com.tron.wallet.business.transfer.TokenDetailActivity;
import com.tron.wallet.common.bean.token.OfficialType;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.rb.RB;
import com.tron.wallet.db.wallet.WalletUtils;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class MyAssetsPresenter extends MyAssetsContract.Presenter {
    private static final String TAG = "MyAssetsPresenter";
    private String address;
    private DataAction dataAction;
    private volatile boolean hasAssets;
    private boolean hideScamTokems;
    private boolean hideZeroAssets;
    private Disposable ignoreAllAssetsDisposable;
    private List<TokenBean> tokens = new ArrayList();
    private NewAssetsModel newAssetsModel = new NewAssetsModel();

    public interface DataAction {
        void getAssets();

        int getNewAssets();

        void requestAssets(boolean z);

        void showAssetsDetail(TokenBean tokenBean);
    }

    @Override
    protected void onStart() {
        this.address = WalletUtils.getSelectedWallet().getAddress();
        this.mRxManager.on(RB.RB_PRICE, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$0(obj);
            }
        });
        this.mRxManager.on(Event.NET_CHANGE, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$1(obj);
            }
        });
        this.mRxManager.on(Event.ASSETS_NEW, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$2(obj);
            }
        });
        this.hideZeroAssets = SortHelper.get().getHideLittleAssets();
        this.hideScamTokems = SortHelper.get().isHideScamToken();
    }

    public void lambda$onStart$0(Object obj) throws Exception {
        if (this.hasAssets) {
            ((MyAssetsContract.View) this.mView).updateAssetsPrice();
        }
    }

    public void lambda$onStart$1(Object obj) throws Exception {
        if (TronConfig.hasNet) {
            requestMyAssets();
        }
    }

    public void lambda$onStart$2(Object obj) throws Exception {
        if (obj instanceof Integer) {
            this.dataAction.getNewAssets();
            this.dataAction.getAssets();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ignoreAllNewAssets();
    }

    public void updateMyAssetsView(List<TokenBean> list) {
        this.mRxManager.add(Observable.just(list).map(new Function() {
            @Override
            public final Object apply(Object obj) {
                return AssetsManager.getInstance().refineFollowAssetsState((List) obj);
            }
        }).compose(RxSchedulers.io_main()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$updateMyAssetsView$4((List) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                MyAssetsPresenter.lambda$updateMyAssetsView$5((Throwable) obj);
            }
        }));
    }

    public void lambda$updateMyAssetsView$4(List list) throws Exception {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            TokenBean tokenBean = (TokenBean) it.next();
            if (tokenBean.getIsInAssets()) {
                tokenBean.isSelected = true;
            } else {
                tokenBean.isSelected = false;
            }
            if (this.hideZeroAssets) {
                if (tokenBean.getType() == 0 || tokenBean.getTop() == 2 || !AssetsConfig.isSmallAsset(tokenBean)) {
                    if (!OfficialType.isScamOrUnSafeToken(tokenBean)) {
                        arrayList.add(tokenBean);
                    }
                }
            } else if (this.hideScamTokems) {
                if (!OfficialType.isScamOrUnSafeToken(tokenBean)) {
                    arrayList.add(tokenBean);
                }
            } else {
                arrayList.add(tokenBean);
            }
        }
        ((MyAssetsContract.View) this.mView).updateMyAssets(arrayList);
    }

    public static void lambda$updateMyAssetsView$5(Throwable th) throws Exception {
        LogUtils.e(th);
        SentryUtil.captureException(th);
    }

    @Override
    public void requestMyAssets() {
        this.dataAction.requestAssets(true);
    }

    @Override
    public void requestMyAssets(boolean z) {
        this.dataAction.requestAssets(z);
    }

    @Override
    public void setIsAssets(boolean z) {
        if (z) {
            this.dataAction = new MyAssetsDataAction();
        } else {
            this.dataAction = new MyCollectionsDataAction();
        }
    }

    @Override
    public void filterAndSort(boolean z, boolean z2) {
        this.hideZeroAssets = z;
        this.hideScamTokems = z2;
        if (this.hasAssets) {
            updateMyAssetsView(this.tokens);
        }
    }

    @Override
    public void hideZeroAssets(boolean z) {
        this.hideZeroAssets = z;
        if (this.hasAssets) {
            updateMyAssetsView(this.tokens);
        }
    }

    @Override
    public void getMyAssets() {
        this.dataAction.getNewAssets();
        this.dataAction.getAssets();
    }

    @Override
    public void ignoreAllNewAssets() {
        if (this.tokens == null || !WatchWalletVerifier.getInstance().getWatchWalletHasOwnerPermission()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (TokenBean tokenBean : this.tokens) {
            if (tokenBean.isNewAsset() && !tokenBean.isSelected) {
                arrayList.add(tokenBean);
            }
        }
        if (this.dataAction.getNewAssets() > 0) {
            this.newAssetsModel.clearNewAssets(this.address);
            this.mRxManager.post(Event.ASSETS_NEW, 0);
        }
        if (arrayList.size() == 0) {
            return;
        }
        ((MyAssetsContract.Model) this.mModel).followAssets(null, arrayList).subscribe(new IObserver(new ICallback<FollowAssetsOutput>() {
            @Override
            public void onResponse(String str, FollowAssetsOutput followAssetsOutput) {
                StringBuilder sb = new StringBuilder("unFollowAssets result:");
                sb.append(followAssetsOutput != null ? Boolean.valueOf(followAssetsOutput.isData()) : "null");
                LogUtils.d(MyAssetsPresenter.TAG, sb.toString());
                if (ignoreAllAssetsDisposable == null || ignoreAllAssetsDisposable.isDisposed()) {
                    return;
                }
                ignoreAllAssetsDisposable.dispose();
                ignoreAllAssetsDisposable = null;
            }

            @Override
            public void onFailure(String str, String str2) {
                LogUtils.d(MyAssetsPresenter.TAG, "unFollowAssets result:" + str2);
                if (ignoreAllAssetsDisposable == null || ignoreAllAssetsDisposable.isDisposed()) {
                    return;
                }
                ignoreAllAssetsDisposable.dispose();
                ignoreAllAssetsDisposable = null;
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                ignoreAllAssetsDisposable = disposable;
            }
        }, "ignoreAllNewAssets"));
    }

    @Override
    public void followAssets(TokenBean tokenBean, int i) {
        if (tokenBean.isSelected || this.address == null) {
            return;
        }
        tokenBean.isSelected = true;
        ArrayList arrayList = new ArrayList();
        arrayList.add(tokenBean);
        ((MyAssetsContract.Model) this.mModel).followAssets(arrayList, null).subscribe(new IObserver(new ICallback<FollowAssetsOutput>() {
            @Override
            public void onResponse(String str, FollowAssetsOutput followAssetsOutput) {
                StringBuilder sb = new StringBuilder("unFollowAssets result:");
                sb.append(followAssetsOutput != null ? Boolean.valueOf(followAssetsOutput.isData()) : "null");
                LogUtils.d(MyAssetsPresenter.TAG, sb.toString());
            }

            @Override
            public void onFailure(String str, String str2) {
                LogUtils.d(MyAssetsPresenter.TAG, "unFollowAssets result:" + str2);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "followAssets"));
        ((MyAssetsContract.View) this.mView).showAssetsAddedView();
        ((MyAssetsContract.View) this.mView).updateAssetsFollowState(tokenBean, i, false);
        if (tokenBean.isNewAsset()) {
            this.newAssetsModel.removeNewAssets(this.address, tokenBean);
            this.mRxManager.post(Event.ASSETS_NEW, tokenBean);
        }
    }

    @Override
    public void unFollowAssets(TokenBean tokenBean, int i) {
        if (!tokenBean.isSelected || this.address == null) {
            return;
        }
        tokenBean.isSelected = false;
        ArrayList arrayList = new ArrayList();
        arrayList.add(tokenBean);
        ((MyAssetsContract.Model) this.mModel).followAssets(null, arrayList).subscribe(new IObserver(new ICallback<FollowAssetsOutput>() {
            @Override
            public void onResponse(String str, FollowAssetsOutput followAssetsOutput) {
                StringBuilder sb = new StringBuilder("unFollowAssets result:");
                sb.append(followAssetsOutput != null ? Boolean.valueOf(followAssetsOutput.isData()) : "null");
                LogUtils.d(MyAssetsPresenter.TAG, sb.toString());
            }

            @Override
            public void onFailure(String str, String str2) {
                LogUtils.d(MyAssetsPresenter.TAG, "unFollowAssets result:" + str2);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "unFollowAssets"));
        ((MyAssetsContract.View) this.mView).updateAssetsFollowState(tokenBean, i, false);
        if (tokenBean.isNewAsset()) {
            this.newAssetsModel.removeNewAssets(this.address, tokenBean);
            this.mRxManager.post(Event.ASSETS_NEW, tokenBean);
        }
        ((MyAssetsContract.Model) this.mModel).removeTokenSortBean(this.address, tokenBean);
    }

    @Override
    public void deleteAssets(TokenBean tokenBean, int i) {
        if (tokenBean == null || this.address == null) {
            return;
        }
        ((MyAssetsContract.View) this.mView).updateAssetsFollowState(tokenBean, i, true);
        if (tokenBean.isNewAsset()) {
            this.newAssetsModel.removeNewAssets(this.address, tokenBean);
            this.mRxManager.post(Event.ASSETS_NEW, tokenBean);
        }
        if (tokenBean.getType() == 5) {
            ArrayList arrayList = new ArrayList();
            arrayList.add((tokenBean.getExtraData() == null || !(tokenBean.getExtraData() instanceof NftTokenBean)) ? NftTokenBean.covertFromTokenBean(tokenBean) : (NftTokenBean) tokenBean.getExtraData());
            ((MyAssetsContract.Model) this.mModel).requestDelMyCollections(arrayList).subscribe(new IObserver(new ICallback<DelMyAssetsOutput>() {
                @Override
                public void onResponse(String str, DelMyAssetsOutput delMyAssetsOutput) {
                    LogUtils.d(MyAssetsPresenter.TAG, "result:" + delMyAssetsOutput);
                }

                @Override
                public void onFailure(String str, String str2) {
                    LogUtils.d(MyAssetsPresenter.TAG, "result:" + str2);
                }

                @Override
                public void onSubscribe(Disposable disposable) {
                    mRxManager.add(disposable);
                }
            }, "deleteAssets"));
            return;
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(tokenBean);
        ((MyAssetsContract.Model) this.mModel).requestDelMyAssets(arrayList2).subscribe(new IObserver(new ICallback<DelMyAssetsOutput>() {
            @Override
            public void onFailure(String str, String str2) {
            }

            @Override
            public void onResponse(String str, DelMyAssetsOutput delMyAssetsOutput) {
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "deleteAssets"));
    }

    @Override
    public void showAssetsDetail(TokenBean tokenBean) {
        this.dataAction.showAssetsDetail(tokenBean);
    }

    public class MyAssetsDataAction implements DataAction {
        private List<TokenBean> newAssets;

        private MyAssetsDataAction() {
            this.newAssets = new ArrayList();
        }

        public List<TokenBean> filterMyTokens(List<TokenBean> list) {
            Iterator<TokenBean> it = list.iterator();
            while (it.hasNext()) {
                TokenBean next = it.next();
                Iterator<TokenBean> it2 = this.newAssets.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    } else if (AssetsManager.sameTokenBean(next, it2.next())) {
                        it.remove();
                        break;
                    }
                }
            }
            return list;
        }

        @Override
        public int getNewAssets() {
            AssetsData newAssets = newAssetsModel.getNewAssets(address);
            if (newAssets != null && newAssets.getTokens() != null) {
                this.newAssets = newAssets.getTokens();
            }
            return this.newAssets.size();
        }

        @Override
        public void showAssetsDetail(TokenBean tokenBean) {
            TokenDetailActivity.start(((MyAssetsContract.View) mView).getIContext(), tokenBean);
        }

        @Override
        public void getAssets() {
            ((MyAssetsContract.Model) mModel).getMyAssets().subscribe(new IObserver(new ICallback<AssetsData>() {
                @Override
                public void onFailure(String str, String str2) {
                }

                @Override
                public void onResponse(String str, AssetsData assetsData) {
                    if (assetsData == null || assetsData.getCount() <= 0 || assetsData.getTokens() == null) {
                        return;
                    }
                    hasAssets = true;
                    tokens.clear();
                    tokens.addAll(MyAssetsDataAction.this.newAssets);
                    tokens.addAll(MyAssetsDataAction.this.filterMyTokens(assetsData.getTokens()));
                    updateMyAssetsView(tokens);
                }

                @Override
                public void onSubscribe(Disposable disposable) {
                    mRxManager.add(disposable);
                }
            }, "getMyAssets"));
        }

        public class fun2 implements ICallback<AssetsDataOutput> {
            final boolean val$background;

            fun2(boolean z) {
                this.val$background = z;
            }

            @Override
            public void onResponse(String str, AssetsDataOutput assetsDataOutput) {
                LogUtils.d(MyAssetsPresenter.TAG, "requestMyAssets:" + assetsDataOutput.getCode());
                ((MyAssetsContract.View) mView).dismissLoadingPage();
                ((MyAssetsContract.View) mView).updateComplete(true);
                if (assetsDataOutput.getCode() == 0) {
                    if (assetsDataOutput.getData() == null || assetsDataOutput.getData().getCount() <= 0) {
                        ((MyAssetsContract.View) mView).showNoDatasPage();
                    }
                    mRxManager.add(((MyAssetsContract.Model) mModel).saveMyAssets(assetsDataOutput.getData()).subscribe(new Consumer() {
                        @Override
                        public final void accept(Object obj) {
                            MyAssetsPresenter.MyAssetsDataAction.2.this.lambda$onResponse$0((Boolean) obj);
                        }
                    }, new Consumer() {
                        @Override
                        public final void accept(Object obj) {
                            MyAssetsPresenter.MyAssetsDataAction.2.lambda$onResponse$1((Throwable) obj);
                        }
                    }));
                }
            }

            public void lambda$onResponse$0(Boolean bool) throws Exception {
                LogUtils.d(MyAssetsPresenter.TAG, "save myAssets to Db:" + bool);
                MyAssetsDataAction.this.getAssets();
            }

            public static void lambda$onResponse$1(Throwable th) throws Exception {
                LogUtils.e(th);
                SentryUtil.captureException(th);
            }

            @Override
            public void onFailure(String str, String str2) {
                LogUtils.d(MyAssetsPresenter.TAG, "requestMyAssets:" + str2);
                if (!hasAssets) {
                    ((MyAssetsContract.View) mView).showNoNetView();
                }
                if (this.val$background) {
                    return;
                }
                ((MyAssetsContract.View) mView).updateComplete(false);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }

        @Override
        public void requestAssets(boolean z) {
            ((MyAssetsContract.Model) mModel).requestMyAssets().subscribe(new IObserver(new fun2(z), "requestMyAssets"));
        }
    }

    public class MyCollectionsDataAction implements DataAction {
        private List<NftTokenBean> newNftTokens;
        private List<NftTokenBean> nftTokenBeans;

        private MyCollectionsDataAction() {
            this.nftTokenBeans = new ArrayList();
            this.newNftTokens = new ArrayList();
        }

        @Override
        public int getNewAssets() {
            AssetsData newAssets = newAssetsModel.getNewAssets(address);
            if (newAssets != null && newAssets.getTrc721Tokens() != null) {
                this.newNftTokens = newAssets.getTrc721Tokens();
            }
            return this.newNftTokens.size();
        }

        public List<TokenBean> transferNftTokenBeanToTokenBean(List<NftTokenBean> list) {
            ArrayList arrayList = new ArrayList();
            if (list != null) {
                for (NftTokenBean nftTokenBean : list) {
                    arrayList.add(nftTokenBean.convertToTokenBean());
                }
            }
            return arrayList;
        }

        public List<NftTokenBean> filterMyTokens(List<NftTokenBean> list) {
            Iterator<NftTokenBean> it = list.iterator();
            while (it.hasNext()) {
                NftTokenBean next = it.next();
                Iterator<NftTokenBean> it2 = this.newNftTokens.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        NftTokenBean next2 = it2.next();
                        if (!StringTronUtil.isEmpty(next.getContractAddress()) && next.getContractAddress().equals(next2.getContractAddress())) {
                            it.remove();
                            break;
                        }
                    }
                }
            }
            return list;
        }

        @Override
        public void showAssetsDetail(TokenBean tokenBean) {
            NftTokenListActivity.start(((MyAssetsContract.View) mView).getIContext(), address, tokenBean);
        }

        @Override
        public void getAssets() {
            ((MyAssetsContract.Model) mModel).getMyCollections().subscribe(new IObserver(new ICallback<List<NftTokenBean>>() {
                @Override
                public void onFailure(String str, String str2) {
                }

                @Override
                public void onResponse(String str, List<NftTokenBean> list) {
                    if (list == null || list.size() <= 0) {
                        return;
                    }
                    hasAssets = true;
                    MyCollectionsDataAction.this.nftTokenBeans.clear();
                    MyCollectionsDataAction.this.nftTokenBeans.addAll(MyCollectionsDataAction.this.newNftTokens);
                    MyCollectionsDataAction.this.nftTokenBeans.addAll(MyCollectionsDataAction.this.filterMyTokens(list));
                    tokens.clear();
                    List list2 = tokens;
                    MyCollectionsDataAction myCollectionsDataAction = MyCollectionsDataAction.this;
                    list2.addAll(myCollectionsDataAction.transferNftTokenBeanToTokenBean(myCollectionsDataAction.nftTokenBeans));
                    updateMyAssetsView(tokens);
                }

                @Override
                public void onSubscribe(Disposable disposable) {
                    mRxManager.add(disposable);
                }
            }, "getMyCollections"));
        }

        public class fun2 implements ICallback<NftDataOutput> {
            final boolean val$background;

            fun2(boolean z) {
                this.val$background = z;
            }

            @Override
            public void onResponse(String str, NftDataOutput nftDataOutput) {
                ((MyAssetsContract.View) mView).dismissLoadingPage();
                ((MyAssetsContract.View) mView).updateComplete(true);
                if (nftDataOutput == null || nftDataOutput.getCode() != 0) {
                    return;
                }
                if (nftDataOutput.getData() == null || nftDataOutput.getData().getCount() <= 0) {
                    ((MyAssetsContract.View) mView).showNoDatasPage();
                }
                mRxManager.add(((MyAssetsContract.Model) mModel).saveMyCollections(nftDataOutput.getData() != null ? nftDataOutput.getData().getToken() : new ArrayList<>()).subscribe(new Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        MyAssetsPresenter.MyCollectionsDataAction.2.this.lambda$onResponse$0((Boolean) obj);
                    }
                }, new Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        MyAssetsPresenter.MyCollectionsDataAction.2.lambda$onResponse$1((Throwable) obj);
                    }
                }));
            }

            public void lambda$onResponse$0(Boolean bool) throws Exception {
                LogUtils.d(MyAssetsPresenter.TAG, "save myAssets to Db:" + bool);
                MyCollectionsDataAction.this.getAssets();
            }

            public static void lambda$onResponse$1(Throwable th) throws Exception {
                LogUtils.e(th);
                SentryUtil.captureException(th);
            }

            @Override
            public void onFailure(String str, String str2) {
                LogUtils.d(MyAssetsPresenter.TAG, "requestMyAssets:" + str2);
                if (!hasAssets) {
                    ((MyAssetsContract.View) mView).showNoNetView();
                }
                if (this.val$background) {
                    return;
                }
                ((MyAssetsContract.View) mView).updateComplete(false);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }

        @Override
        public void requestAssets(boolean z) {
            ((MyAssetsContract.Model) mModel).requestMyCollections().subscribe(new IObserver(new fun2(z), "requestMyCollections"));
        }
    }
}
