package com.tron.wallet.business.addassets2;

import android.text.TextUtils;
import android.util.SparseArray;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.HomeAssetsContract;
import com.tron.wallet.business.addassets2.bean.FollowAssetsOutput;
import com.tron.wallet.business.addassets2.bean.TokenSortBean;
import com.tron.wallet.business.addassets2.bean.TokenSortType;
import com.tron.wallet.business.addassets2.bean.asset.AssetsHomeData;
import com.tron.wallet.business.addassets2.bean.asset.AssetsHomeOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftAssetOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftTokenBean;
import com.tron.wallet.business.nft.NftTokenListActivity;
import com.tron.wallet.business.nft.dao.NftTokenBeanController;
import com.tron.wallet.business.transfer.TokenDetailActivity;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.rb.RB;
import com.tron.wallet.db.Controller.AssetsHomeDataDaoManager;
import com.tron.wallet.db.Controller.TRXAccountBalanceController;
import com.tron.wallet.db.wallet.WalletUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
public class HomeAssetsPresenter extends HomeAssetsContract.Presenter {
    private static final String TAG = "HomeAssetsPresenter";
    private String address;
    private DataAction dataAction;
    private boolean isHomeAssets;
    private TokenSortType sortType;
    private CopyOnWriteArrayList<TokenBean> tokens = new CopyOnWriteArrayList<>();
    private int initSortType = -1;

    public interface DataAction {
        void getFollowAssets(int i);

        void requestFollowAssets(boolean z);

        void showAssetsDetail(TokenBean tokenBean);
    }

    @Override
    public void setInitSortType(int i) {
        this.initSortType = i;
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
        this.mRxManager.on(RB.RB_HOMEASSET_DB, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$1(obj);
            }
        });
    }

    public void lambda$onStart$0(Object obj) throws Exception {
        ((HomeAssetsContract.View) this.mView).updateAssetsPrice();
    }

    public void lambda$onStart$1(Object obj) throws Exception {
        getHomeAssets();
    }

    @Override
    public void setIsHomeAssets(boolean z) {
        this.isHomeAssets = z;
        if (z) {
            this.dataAction = new HomeAssetsDataAction();
        } else {
            this.dataAction = new HomeCollectionsDataAction();
        }
    }

    @Override
    public void getHomeAssets() {
        this.dataAction.getFollowAssets(this.initSortType);
    }

    @Override
    public void requestHomeAssets() {
        requestHomeAssets(true);
    }

    @Override
    public void requestHomeAssets(boolean z) {
        this.dataAction.requestFollowAssets(z);
    }

    @Override
    public void unFollowAssets(TokenBean tokenBean, int i) {
        if (!tokenBean.isSelected || this.address == null) {
            return;
        }
        tokenBean.isSelected = false;
        ArrayList arrayList = new ArrayList();
        arrayList.add(tokenBean);
        ((HomeAssetsContract.View) this.mView).updateAssetsFollowState(tokenBean, i);
        ((HomeAssetsContract.Model) this.mModel).followAssets(null, arrayList).subscribe(new IObserver(new ICallback<FollowAssetsOutput>() {
            @Override
            public void onResponse(String str, FollowAssetsOutput followAssetsOutput) {
                StringBuilder sb = new StringBuilder("unFollowAssets result:");
                sb.append(followAssetsOutput != null ? Boolean.valueOf(followAssetsOutput.isData()) : "null");
                LogUtils.d(HomeAssetsPresenter.TAG, sb.toString());
            }

            @Override
            public void onFailure(String str, String str2) {
                LogUtils.d(HomeAssetsPresenter.TAG, "unFollowAssets result:" + str2);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "ignoreAllNewAssets"));
        ((HomeAssetsContract.Model) this.mModel).removeTokenSortBean(this.address, tokenBean);
    }

    @Override
    public void showAssetsDetail(TokenBean tokenBean) {
        this.dataAction.showAssetsDetail(tokenBean);
    }

    private TokenSortBean buildTokenSortBean(TokenBean tokenBean, int i) {
        TokenSortBean tokenSortBean = new TokenSortBean();
        tokenSortBean.setType(this.isHomeAssets ? 0 : 5);
        tokenSortBean.setAddress(this.address);
        tokenSortBean.setIndex(i);
        if (!TextUtils.isEmpty(tokenBean.getId())) {
            tokenSortBean.setContractAddress(tokenBean.getId());
        } else if (!TextUtils.isEmpty(tokenBean.getContractAddress())) {
            tokenSortBean.setContractAddress(tokenBean.getContractAddress());
        } else {
            tokenSortBean.setContractAddress(tokenBean.getName());
        }
        return tokenSortBean;
    }

    @Override
    public void insertSortBean(SparseArray<TokenBean> sparseArray) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < sparseArray.size(); i++) {
            arrayList.add(buildTokenSortBean(sparseArray.valueAt(i), sparseArray.keyAt(i)));
        }
        ((HomeAssetsContract.Model) this.mModel).saveTokenSortBeanList(this.address, arrayList, this.isHomeAssets ? 0 : 5).subscribe(new IObserver(new ICallback<Boolean>() {
            @Override
            public void onResponse(String str, Boolean bool) {
                mRxManager.post(Event.ASSETS_SORTED, Boolean.valueOf(isHomeAssets));
            }

            @Override
            public void onFailure(String str, String str2) {
                LogUtils.d(HomeAssetsPresenter.TAG, "saveTokenSortBeanList fail: " + str);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "insertSortBean"));
    }

    @Override
    public TokenSortType getSortType() {
        TokenSortType tokenSortType = this.sortType;
        return tokenSortType == null ? TokenSortType.SORT_BY_VALUE : tokenSortType;
    }

    @Override
    public void setSortType(TokenSortType tokenSortType) {
        if (tokenSortType == this.sortType) {
            return;
        }
        this.sortType = tokenSortType;
        ((HomeAssetsContract.Model) this.mModel).setTokenSortType(this.address, tokenSortType, this.isHomeAssets ? 0 : 5).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$setSortType$2((Boolean) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                HomeAssetsPresenter.lambda$setSortType$3((Throwable) obj);
            }
        });
    }

    public void lambda$setSortType$2(Boolean bool) throws Exception {
        LogUtils.d(TAG, "saveSortType: " + bool);
        if (this.sortType != TokenSortType.SORT_BY_USER) {
            getHomeAssets();
        }
    }

    public static void lambda$setSortType$3(Throwable th) throws Exception {
        SentryUtil.captureException(th);
        LogUtils.e(th);
    }

    public class HomeAssetsDataAction implements DataAction {
        private HomeAssetsDataAction() {
        }

        @Override
        public void showAssetsDetail(TokenBean tokenBean) {
            TokenDetailActivity.start(((HomeAssetsContract.View) mView).getIContext(), tokenBean);
        }

        @Override
        public void getFollowAssets(int i) {
            ((HomeAssetsContract.Model) mModel).getFollowAssets(i).subscribe(new IObserver(new ICallback<AssetsHomeData>() {
                @Override
                public void onResponse(String str, AssetsHomeData assetsHomeData) {
                    if (mView == 0) {
                        return;
                    }
                    ((HomeAssetsContract.View) mView).updateComplete(true);
                    if (assetsHomeData != null && assetsHomeData.getToken() != null && !assetsHomeData.getToken().isEmpty()) {
                        LogUtils.w(HomeAssetsPresenter.TAG, assetsHomeData.token.size() + "");
                        tokens.clear();
                        tokens.addAll(assetsHomeData.token);
                        ((HomeAssetsContract.View) mView).updateHomeAssets(assetsHomeData.token);
                        return;
                    }
                    tokens.clear();
                    ((HomeAssetsContract.View) mView).showNoNetView();
                }

                @Override
                public void onFailure(String str, String str2) {
                    if (mView != 0) {
                        tokens.clear();
                        ((HomeAssetsContract.View) mView).updateComplete(true);
                        ((HomeAssetsContract.View) mView).showNoNetView();
                    }
                }

                @Override
                public void onSubscribe(Disposable disposable) {
                    mRxManager.add(disposable);
                }
            }, "getHomeAssets"));
        }

        @Override
        public void requestFollowAssets(final boolean z) {
            ((HomeAssetsContract.Model) mModel).requestFollowAssets(address).observeOn(Schedulers.io()).filter(new Predicate<AssetsHomeOutput>() {
                @Override
                public boolean test(AssetsHomeOutput assetsHomeOutput) {
                    if (assetsHomeOutput.code != 0 || assetsHomeOutput.data == null || assetsHomeOutput.data.token == null || assetsHomeOutput.data.token.size() <= 0) {
                        return false;
                    }
                    if (address != null) {
                        assetsHomeOutput.data.address = address;
                    }
                    try {
                        LogUtils.d("AccountBalance", "insertObject:  ");
                        TRXAccountBalanceController.getInstance(AppContextUtil.getContext()).insertObject(address, assetsHomeOutput.data.totalTRX, assetsHomeOutput.data.token.get(0).getTrxCount());
                    } catch (Exception e) {
                        LogUtils.e(e);
                    }
                    for (int i = 0; i < assetsHomeOutput.data.token.size(); i++) {
                        assetsHomeOutput.data.token.get(i).address = address;
                    }
                    AssetsHomeDataDaoManager.clearAndAdd(((HomeAssetsContract.View) mView).getIContext(), assetsHomeOutput.data, address);
                    return true;
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(new IObserver(new ICallback<AssetsHomeOutput>() {
                @Override
                public void onResponse(String str, AssetsHomeOutput assetsHomeOutput) {
                    getHomeAssets();
                }

                @Override
                public void onFailure(String str, String str2) {
                    if (tokens == null || tokens.size() == 0) {
                        ((HomeAssetsContract.View) mView).showNoNetView();
                    }
                    if (z) {
                        return;
                    }
                    ((HomeAssetsContract.View) mView).updateComplete(false);
                }

                @Override
                public void onSubscribe(Disposable disposable) {
                    mRxManager.add(disposable);
                }
            }, "requestHomeAssets"));
        }
    }

    public class HomeCollectionsDataAction implements DataAction {
        private List<NftTokenBean> nftTokenBeans;

        private HomeCollectionsDataAction() {
            this.nftTokenBeans = new ArrayList();
        }

        @Override
        public void showAssetsDetail(TokenBean tokenBean) {
            NftTokenListActivity.start(((HomeAssetsContract.View) mView).getIContext(), address, tokenBean);
        }

        @Override
        public void getFollowAssets(int i) {
            ((HomeAssetsContract.Model) mModel).getFollowCollections(i).subscribe(new IObserver(new ICallback<List<NftTokenBean>>() {
                @Override
                public void onResponse(String str, List<NftTokenBean> list) {
                    if (mView == 0) {
                        return;
                    }
                    ((HomeAssetsContract.View) mView).updateComplete(true);
                    if (list != null && list.size() > 0) {
                        LogUtils.w(HomeAssetsPresenter.TAG, list.size() + "");
                        HomeCollectionsDataAction.this.nftTokenBeans.clear();
                        HomeCollectionsDataAction.this.nftTokenBeans.addAll(list);
                        tokens.clear();
                        tokens.addAll(AssetsManager.transferNftTokenBeanToTokenBean(list));
                        ((HomeAssetsContract.View) mView).updateHomeAssets(tokens);
                        return;
                    }
                    tokens.clear();
                    ((HomeAssetsContract.View) mView).showNoNetView();
                }

                @Override
                public void onFailure(String str, String str2) {
                    if (mView != 0) {
                        HomeCollectionsDataAction.this.nftTokenBeans.clear();
                        ((HomeAssetsContract.View) mView).updateComplete(true);
                        ((HomeAssetsContract.View) mView).showNoNetView();
                    }
                }

                @Override
                public void onSubscribe(Disposable disposable) {
                    mRxManager.add(disposable);
                }
            }, "getFollowCollections"));
        }

        @Override
        public void requestFollowAssets(final boolean z) {
            ((HomeAssetsContract.Model) mModel).requestFollowCollections(address).observeOn(Schedulers.io()).filter(new Predicate<NftAssetOutput>() {
                @Override
                public boolean test(NftAssetOutput nftAssetOutput) {
                    List<NftTokenBean> data;
                    if (nftAssetOutput != null && nftAssetOutput.getCode() == 0 && (data = nftAssetOutput.getData()) != null && data.size() > 0) {
                        for (NftTokenBean nftTokenBean : data) {
                            nftTokenBean.setWalletAddress(address);
                        }
                        NftTokenBeanController.getInstance().clearAndAdd(data, address);
                        Boolean bool = Boolean.TRUE;
                        return true;
                    }
                    Boolean bool2 = Boolean.FALSE;
                    return false;
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(new IObserver(new ICallback<NftAssetOutput>() {
                @Override
                public void onResponse(String str, NftAssetOutput nftAssetOutput) {
                    getHomeAssets();
                }

                @Override
                public void onFailure(String str, String str2) {
                    if (tokens == null || tokens.size() == 0) {
                        ((HomeAssetsContract.View) mView).showNoNetView();
                    }
                    if (z) {
                        return;
                    }
                    ((HomeAssetsContract.View) mView).updateComplete(false);
                }

                @Override
                public void onSubscribe(Disposable disposable) {
                    mRxManager.add(disposable);
                }
            }, "requestHomeAssets"));
        }
    }
}
