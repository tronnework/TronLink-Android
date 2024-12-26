package com.tron.wallet.business.nft.contract;

import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.bean.nft.NftDetailInput;
import com.tron.wallet.business.addassets2.bean.nft.NftDetailOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftInfoOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftItemInfo;
import com.tron.wallet.business.addassets2.bean.nft.NftTypeInfo;
import com.tron.wallet.business.customtokens.CustomTokensModel;
import com.tron.wallet.business.customtokens.bean.CustomTokenBean;
import com.tron.wallet.business.customtokens.bean.CustomTokenStatus;
import com.tron.wallet.business.customtokens.bean.QueryCustomTokenOutput;
import com.tron.wallet.business.customtokens.bean.SyncCustomTokenOutput;
import com.tron.wallet.business.nft.contract.Contract;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.tron.walletserver.Wallet;
public class NftPresenter extends Contract.Presenter {
    private static final int INIT_PAGE_INDEX = 0;
    private static final int PAGE_SIZE = 10;
    private static final String TAG = "NftPresenter";
    private NftInfoOutput currentResult;
    private AtomicInteger pageIndex = new AtomicInteger(0);
    private volatile boolean isLoading = false;
    private volatile boolean loadCacheSuccess = false;

    @Override
    protected void onStart() {
        this.mRxManager.on(Event.BroadcastSuccess, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$0(obj);
            }
        });
        this.mRxManager.on(Event.BackToHome, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$1(obj);
            }
        });
    }

    public void lambda$onStart$0(Object obj) throws Exception {
        ((Contract.View) this.mView).onBroadcastSuccess(obj);
    }

    public void lambda$onStart$1(Object obj) throws Exception {
        ((Contract.View) this.mView).exit();
    }

    @Override
    public void getNftTokenInfo(String str, String str2) {
        if (this.pageIndex.get() == 0) {
            getDbData(str, str2);
        }
        getNetworkData(str, str2);
    }

    @Override
    public void refresh(String str, String str2) {
        if (this.isLoading) {
            return;
        }
        this.pageIndex.set(0);
        getNftTokenInfo(str, str2);
    }

    @Override
    public void syncCustomToken(String str, TokenBean tokenBean) {
        new CustomTokensModel().syncCustomToken(str, tokenBean.getContractAddress()).subscribe(new IObserver(new ICallback<SyncCustomTokenOutput>() {
            @Override
            public void onResponse(String str2, SyncCustomTokenOutput syncCustomTokenOutput) {
                if (syncCustomTokenOutput != null) {
                    ((Contract.View) mView).updateTokenInfo(syncCustomTokenOutput.getData());
                } else {
                    ((Contract.View) mView).updateTokenInfo(null);
                }
            }

            @Override
            public void onFailure(String str2, String str3) {
                ((Contract.View) mView).updateTokenInfo(null);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "syncCustomToken"));
    }

    @Override
    public void getCustomTokenInfo(String str, TokenBean tokenBean) {
        if (tokenBean.getTokenStatus() != 0) {
            CustomTokensModel customTokensModel = new CustomTokensModel();
            Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
            if (selectedPublicWallet != null) {
                customTokensModel.queryCustomToken(selectedPublicWallet.getAddress(), tokenBean.getContractAddress()).subscribe(new IObserver(new ICallback<QueryCustomTokenOutput>() {
                    @Override
                    public void onResponse(String str2, QueryCustomTokenOutput queryCustomTokenOutput) {
                        String[] split;
                        if (queryCustomTokenOutput == null || queryCustomTokenOutput.getData() == null) {
                            return;
                        }
                        CustomTokenBean data = queryCustomTokenOutput.getData();
                        ArrayList arrayList = new ArrayList();
                        String noFunctions = data.getNoFunctions();
                        if (!StringTronUtil.isEmpty(noFunctions) && (split = noFunctions.trim().split(",")) != null) {
                            for (String str3 : split) {
                                int noFunctionErrorStrId = CustomTokenStatus.getNoFunctionErrorStrId(str3);
                                if (noFunctionErrorStrId != 0) {
                                    arrayList.add(((Contract.View) mView).getIContext().getResources().getString(noFunctionErrorStrId));
                                }
                            }
                        }
                        ((Contract.View) mView).updateTokenNoFunctions(arrayList);
                    }

                    @Override
                    public void onFailure(String str2, String str3) {
                        ((Contract.View) mView).updateTokenNoFunctions(null);
                    }

                    @Override
                    public void onSubscribe(Disposable disposable) {
                        mRxManager.add(disposable);
                    }
                }, "getCustomTokenInfo"));
            }
        }
    }

    private void getDbData(String str, String str2) {
        ((Contract.Model) this.mModel).getNftTokenInfo(((Contract.View) this.mView).getIContext(), str, str2).subscribe(new IObserver(new ICallback<NftInfoOutput>() {
            @Override
            public void onResponse(String str3, NftInfoOutput nftInfoOutput) {
                if (nftInfoOutput != null && nftInfoOutput.isValidResponse() && nftInfoOutput.getData().getCollectionInfoList().size() > 0) {
                    ((Contract.View) mView).onGetTokenInfo(nftInfoOutput, true, false);
                    ((Contract.View) mView).loadMoreComplete();
                    loadCacheSuccess = true;
                    LogUtils.d(NftPresenter.TAG, "getDbData-> response:\n" + nftInfoOutput);
                    return;
                }
                onFailure(str3, "Failed to get nft cached token info.");
            }

            @Override
            public void onFailure(String str3, String str4) {
                loadCacheSuccess = false;
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, ""));
    }

    private void getNetworkData(final String str, String str2) {
        LogUtils.w(TAG, "called: getNetworkData; pageIndex = " + this.pageIndex.get());
        this.isLoading = true;
        ((Contract.Model) this.mModel).requestNftTokenInfo(str, str2, this.pageIndex.get(), 10).flatMap(new Function<NftInfoOutput, Observable<NftDetailOutput>>() {
            @Override
            public Observable<NftDetailOutput> apply(NftInfoOutput nftInfoOutput) {
                return requestNftItemInfos(str, nftInfoOutput);
            }
        }).flatMap(new Function<NftDetailOutput, Observable<NftInfoOutput>>() {
            @Override
            public Observable<NftInfoOutput> apply(NftDetailOutput nftDetailOutput) {
                return parseNftItemInfos(nftDetailOutput);
            }
        }).subscribe(new IObserver(new ICallback<NftInfoOutput>() {
            @Override
            public void onResponse(String str3, NftInfoOutput nftInfoOutput) {
                if (nftInfoOutput != null && nftInfoOutput.isValidResponse()) {
                    ((Contract.View) mView).onGetTokenInfo(nftInfoOutput, pageIndex.get() == 0, true);
                    if (nftInfoOutput.getData().getCollectionInfoList().isEmpty()) {
                        ((Contract.View) mView).loadMoreEnd();
                    } else {
                        ((Contract.View) mView).loadMoreComplete();
                    }
                    NftTypeInfo data = nftInfoOutput.getData();
                    data.setWalletAddress(str);
                    data.setCollectionInfoList(((Contract.View) mView).getCurrentData());
                    for (NftItemInfo nftItemInfo : data.getCollectionInfoList()) {
                        nftItemInfo.setWalletAddress(str);
                    }
                    LogUtils.w(NftPresenter.TAG, String.format("getNetworkData -> response: \n%s \n pageIndex = %d", nftInfoOutput, Integer.valueOf(pageIndex.get())));
                    ((Contract.Model) mModel).saveNftToken(((Contract.View) mView).getIContext(), data, new ICallback<Boolean>() {
                        @Override
                        public void onFailure(String str4, String str5) {
                        }

                        @Override
                        public void onResponse(String str4, Boolean bool) {
                        }

                        @Override
                        public void onSubscribe(Disposable disposable) {
                            mRxManager.add(disposable);
                        }
                    });
                    pageIndex.getAndIncrement();
                    isLoading = false;
                    return;
                }
                onFailure(str3, "Failed to request nft token info.");
            }

            @Override
            public void onFailure(String str3, String str4) {
                if (pageIndex.get() == 0 && !loadCacheSuccess) {
                    ((Contract.View) mView).showNoDatasPage();
                } else if (!loadCacheSuccess) {
                    ((Contract.View) mView).loadMoreFailed();
                } else {
                    ((Contract.View) mView).loadMoreEnd();
                }
                ((Contract.View) mView).toast(((Contract.View) mView).getIContext().getString(R.string.time_out));
                isLoading = false;
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, ""));
    }

    public Observable<NftDetailOutput> requestNftItemInfos(String str, NftInfoOutput nftInfoOutput) {
        if (nftInfoOutput == null || !nftInfoOutput.isValidResponse()) {
            NftDetailOutput nftDetailOutput = new NftDetailOutput();
            nftDetailOutput.setCode(0);
            nftDetailOutput.setMessage("Empty params");
            nftDetailOutput.setData(new ArrayList());
            NftInfoOutput nftInfoOutput2 = new NftInfoOutput();
            this.currentResult = nftInfoOutput2;
            nftInfoOutput2.setCode(0);
            this.currentResult.setData(NftTypeInfo.buildDefault());
            this.currentResult.setMessage("Failed to parse nft item infos");
            return Observable.just(nftDetailOutput);
        }
        this.currentResult = nftInfoOutput;
        ArrayList arrayList = new ArrayList();
        for (NftItemInfo nftItemInfo : nftInfoOutput.getData().getCollectionInfoList()) {
            if (com.tron.tron_base.frame.utils.StringTronUtil.isEmpty(nftItemInfo.getName(), nftItemInfo.getIntro())) {
                arrayList.add(nftItemInfo.getAssetId());
            }
        }
        NftDetailInput nftDetailInput = new NftDetailInput();
        nftDetailInput.setAddress(str);
        nftDetailInput.setTokenAddress(nftInfoOutput.getData().getTokenAddress());
        nftDetailInput.setAssetIdList(arrayList);
        return ((Contract.Model) this.mModel).getNftItemInfos(str, RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSON.toJSONString(nftDetailInput)));
    }

    public Observable<NftInfoOutput> parseNftItemInfos(NftDetailOutput nftDetailOutput) {
        if (!nftDetailOutput.isValid()) {
            return Observable.just(this.currentResult);
        }
        for (NftItemInfo nftItemInfo : nftDetailOutput.getData()) {
            List<NftItemInfo> collectionInfoList = this.currentResult.getData().getCollectionInfoList();
            for (int i = 0; i < collectionInfoList.size(); i++) {
                if (TextUtils.equals(nftItemInfo.getAssetId(), collectionInfoList.get(i).getAssetId())) {
                    collectionInfoList.set(i, nftItemInfo);
                }
            }
        }
        this.currentResult.setCode(nftDetailOutput.getCode());
        this.currentResult.setMessage(nftDetailOutput.getMessage());
        return Observable.just(this.currentResult);
    }
}
