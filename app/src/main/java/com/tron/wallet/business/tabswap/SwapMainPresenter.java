package com.tron.wallet.business.tabswap;

import com.google.gson.Gson;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.tabswap.SwapMainContract;
import com.tron.wallet.business.tabswap.SwapMainFragment;
import com.tron.wallet.business.tabswap.bean.AppStatusInput;
import com.tron.wallet.business.tabswap.bean.AppStatusOutput;
import com.tron.wallet.common.bean.Result2;
import com.tron.wallet.common.utils.GsonUtils;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.tron.walletserver.Wallet;
public class SwapMainPresenter extends SwapMainContract.Presenter {
    @Override
    protected void onStart() {
    }

    public void getStatus() {
        final AppStatusOutput appStatus = SpAPI.THIS.getAppStatus();
        if (appStatus.isHideFinancialTab() && !appStatus.isMainland()) {
            LogUtils.i("AppStatusOutput-appStatus:" + GsonUtils.toGsonString(appStatus));
            ((SwapMainContract.View) this.mView).dismissLoadingPage();
            ((SwapMainContract.View) this.mView).showPage(SwapMainFragment.TabStatus.HideFinancialTab, SwapMainFragment.PageStatus.NoMask);
            return;
        }
        AppStatusInput appStatusInput = new AppStatusInput();
        appStatusInput.setFina(true);
        appStatusInput.setMainland(true);
        appStatusInput.setWalletAddress(getAllNativeWallets());
        final String json = new Gson().toJson(appStatusInput);
        ((SwapMainContract.Model) this.mModel).getStatus(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)).subscribe(new IObserver(new ICallback<Result2<AppStatusOutput>>() {
            @Override
            public void onResponse(String str, Result2<AppStatusOutput> result2) {
                LogUtils.i("AppStatusOutput:" + json + GsonUtils.toGsonString(result2));
                ((SwapMainContract.View) mView).dismissLoadingPage();
                if (result2 != null && result2.getCode() == 0 && result2.getData() != null) {
                    if (result2.getData().isFina()) {
                        if (result2.getData().isMainland()) {
                            ((SwapMainContract.View) mView).showPage(SwapMainFragment.TabStatus.Normal, SwapMainFragment.PageStatus.ShowMask);
                        } else {
                            ((SwapMainContract.View) mView).showPage(SwapMainFragment.TabStatus.Normal, SwapMainFragment.PageStatus.NoMask);
                        }
                    } else {
                        result2.getData().setHideFinancialTab(true);
                        if (result2.getData().isMainland()) {
                            ((SwapMainContract.View) mView).showPage(SwapMainFragment.TabStatus.HideFinancialTab, SwapMainFragment.PageStatus.ShowMask);
                        } else {
                            ((SwapMainContract.View) mView).showPage(SwapMainFragment.TabStatus.HideFinancialTab, SwapMainFragment.PageStatus.NoMask);
                        }
                    }
                    SpAPI.THIS.setAppStatus(result2.getData());
                    return;
                }
                ((SwapMainContract.View) mView).showPage(SwapMainFragment.TabStatus.Normal, SwapMainFragment.PageStatus.NoMask);
            }

            @Override
            public void onFailure(String str, String str2) {
                ((SwapMainContract.View) mView).dismissLoadingPage();
                if (appStatus.isFina() && !appStatus.isHideFinancialTab()) {
                    if (appStatus.isMainland()) {
                        ((SwapMainContract.View) mView).showPage(SwapMainFragment.TabStatus.Normal, SwapMainFragment.PageStatus.ShowMask);
                    } else {
                        ((SwapMainContract.View) mView).showPage(SwapMainFragment.TabStatus.HideFinancialTab, SwapMainFragment.PageStatus.NoMask);
                    }
                } else if (appStatus.isMainland()) {
                    ((SwapMainContract.View) mView).showPage(SwapMainFragment.TabStatus.HideFinancialTab, SwapMainFragment.PageStatus.ShowMask);
                } else {
                    ((SwapMainContract.View) mView).showPage(SwapMainFragment.TabStatus.HideFinancialTab, SwapMainFragment.PageStatus.NoMask);
                }
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                addDisposable(disposable);
            }
        }, "getAppStatus"));
    }

    private List<String> getAllNativeWallets() {
        ArrayList arrayList = new ArrayList();
        for (String str : WalletUtils.getWalletNames()) {
            Wallet wallet = WalletUtils.getWallet(str);
            if (wallet != null && wallet.getAddress() != null && !wallet.isShieldedWallet() && !wallet.isWatchNotPaired()) {
                arrayList.add(wallet.getAddress());
            }
        }
        return arrayList;
    }
}
