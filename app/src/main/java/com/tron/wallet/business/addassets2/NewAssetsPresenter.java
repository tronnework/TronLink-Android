package com.tron.wallet.business.addassets2;

import android.content.Context;
import android.content.SharedPreferences;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.NewAssetsContract;
import com.tron.wallet.business.addassets2.bean.AssetsData;
import com.tron.wallet.business.addassets2.bean.FollowAssetsOutput;
import com.tron.wallet.business.transfer.TokenDetailActivity;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.rb.RB;
import com.tron.wallet.db.wallet.WalletUtils;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class NewAssetsPresenter extends NewAssetsContract.Presenter {
    private static final String KEY_FIRST_ENTER = "key_first_enter";
    private static final String KEY_SP = "new_asset_first_enter";
    private static final String TAG = "NewAssetsPresenter";
    private String address;
    private Context context;
    private List<TokenBean> newTokens;

    @Override
    protected void onStart() {
        this.context = ((NewAssetsContract.View) this.mView).getIContext();
        this.address = WalletUtils.getSelectedWallet().getAddress();
        this.mRxManager.on(RB.RB_PRICE, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$0(obj);
            }
        });
    }

    public void lambda$onStart$0(Object obj) throws Exception {
        List<TokenBean> list = this.newTokens;
        if (list == null || list.size() <= 0) {
            return;
        }
        ((NewAssetsContract.View) this.mView).updateAssetsPrice();
    }

    @Override
    public void getNewAssets() {
        AssetsData newAssets = ((NewAssetsContract.Model) this.mModel).getNewAssets(this.address);
        if (newAssets != null && newAssets.getCount() > 0) {
            LogUtils.d(TAG, "new assets count:" + newAssets.getCount());
            this.newTokens = new ArrayList(newAssets.getTokens());
            this.mRxManager.add(Observable.just(this.newTokens).map(new Function() {
                @Override
                public final Object apply(Object obj) {
                    return AssetsManager.getInstance().refineFollowAssetsState((List) obj);
                }
            }).compose(RxSchedulers.io_main()).subscribe(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    lambda$getNewAssets$2((List) obj);
                }
            }));
        } else {
            ((NewAssetsContract.View) this.mView).showNoDataView();
        }
        ((NewAssetsContract.View) this.mView).updateComplete();
    }

    public void lambda$getNewAssets$2(List list) throws Exception {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            TokenBean tokenBean = (TokenBean) it.next();
            if (tokenBean.getIsInAssets()) {
                tokenBean.isSelected = true;
            }
        }
        ((NewAssetsContract.View) this.mView).updateAssets(list);
    }

    @Override
    public void ignoreAllNewAssets() {
        List<TokenBean> list = this.newTokens;
        if (list == null || list.size() == 0) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (TokenBean tokenBean : this.newTokens) {
            if (!tokenBean.isSelected) {
                arrayList.add(tokenBean);
            }
        }
        if (arrayList.size() == 0) {
            ((NewAssetsContract.View) this.mView).showAssetsNoIgnore();
            return;
        }
        ((NewAssetsContract.View) this.mView).showNoDataView();
        ((NewAssetsContract.Model) this.mModel).followAssets(null, arrayList).subscribe(new IObserver(new ICallback<FollowAssetsOutput>() {
            @Override
            public void onResponse(String str, FollowAssetsOutput followAssetsOutput) {
                StringBuilder sb = new StringBuilder("unFollowAssets result:");
                sb.append(followAssetsOutput != null ? Boolean.valueOf(followAssetsOutput.isData()) : "null");
                LogUtils.d(NewAssetsPresenter.TAG, sb.toString());
            }

            @Override
            public void onFailure(String str, String str2) {
                LogUtils.d(NewAssetsPresenter.TAG, "unFollowAssets result:" + str2);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "ignoreAllNewAssets"));
        ((NewAssetsContract.Model) this.mModel).clearNewAssets(this.address);
        this.mRxManager.post(Event.ASSETS_NEW, 0);
    }

    @Override
    public void followAssets(TokenBean tokenBean, int i) {
        if (tokenBean.isSelected || this.address == null) {
            return;
        }
        tokenBean.isSelected = true;
        ArrayList arrayList = new ArrayList();
        arrayList.add(tokenBean);
        ((NewAssetsContract.Model) this.mModel).followAssets(arrayList, null).subscribe(new IObserver(new ICallback<FollowAssetsOutput>() {
            @Override
            public void onResponse(String str, FollowAssetsOutput followAssetsOutput) {
                StringBuilder sb = new StringBuilder("unFollowAssets result:");
                sb.append(followAssetsOutput != null ? Boolean.valueOf(followAssetsOutput.isData()) : "null");
                LogUtils.d(NewAssetsPresenter.TAG, sb.toString());
            }

            @Override
            public void onFailure(String str, String str2) {
                LogUtils.d(NewAssetsPresenter.TAG, "unFollowAssets result:" + str2);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "ignoreAllNewAssets"));
        ((NewAssetsContract.View) this.mView).showAssetsAddedView();
        ((NewAssetsContract.View) this.mView).updateAssetsState(tokenBean, i);
        ((NewAssetsContract.Model) this.mModel).removeNewAssets(this.address, tokenBean);
        this.mRxManager.post(Event.ASSETS_NEW, 0);
    }

    @Override
    public void showAssetsDetail(TokenBean tokenBean) {
        TokenDetailActivity.start(((NewAssetsContract.View) this.mView).getIContext(), tokenBean);
    }

    @Override
    public boolean isFirstEnter() {
        Context context = this.context;
        if (context == null) {
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_SP, 0);
        boolean z = sharedPreferences.getBoolean(KEY_FIRST_ENTER, true);
        if (z) {
            sharedPreferences.edit().putBoolean(KEY_FIRST_ENTER, false).apply();
        }
        return z;
    }
}
