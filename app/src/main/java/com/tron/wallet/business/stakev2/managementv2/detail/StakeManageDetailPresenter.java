package com.tron.wallet.business.stakev2.managementv2.detail;

import android.util.Pair;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.stakev2.managementv2.ResourcesBean;
import com.tron.wallet.business.stakev2.managementv2.detail.StakeManageDetailContract;
import com.tron.wallet.business.stakev2.managementv2.detail.bean.StakeResourceDetailForMeOutput;
import com.tron.wallet.business.stakev2.managementv2.detail.bean.StakeResourceDetailOutput;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.net.rpc.TronAPI;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function3;
import java.util.HashMap;
import java.util.concurrent.Callable;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
public class StakeManageDetailPresenter extends StakeManageDetailContract.Presenter {
    private Protocol.Account account;
    private GrpcAPI.AccountResourceMessage accountResourceMessage;
    private long maxSize;

    public static void lambda$onStart$0(Object obj) throws Exception {
    }

    public Protocol.Account getAccount() {
        return this.account;
    }

    public long getMaxSize() {
        return this.maxSize;
    }

    @Override
    protected void onStart() {
        this.mRxManager.on(Event.VOTE_TO_MULTI_VOTE, new Consumer() {
            @Override
            public final void accept(Object obj) {
                StakeManageDetailPresenter.lambda$onStart$0(obj);
            }
        });
    }

    public void getDelegateAvailableAmount(final int i, final String str) {
        Observable.combineLatest(Observable.fromCallable(new Callable<Protocol.Account>() {
            @Override
            public Protocol.Account call() throws Exception {
                byte[] decodeFromBase58Check = StringTronUtil.decodeFromBase58Check(str);
                account = TronAPI.queryAccount(decodeFromBase58Check, false);
                return account;
            }
        }), Observable.fromCallable(new Callable<GrpcAPI.AccountResourceMessage>() {
            @Override
            public GrpcAPI.AccountResourceMessage call() throws Exception {
                byte[] decodeFromBase58Check = StringTronUtil.decodeFromBase58Check(str);
                accountResourceMessage = TronAPI.getAccountRes(decodeFromBase58Check);
                return accountResourceMessage;
            }
        }), Observable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                GrpcAPI.CanDelegatedMaxSizeResponseMessage canDelegatedMaxSize = TronAPI.getCanDelegatedMaxSize(i == 0 ? 1 : 0, str);
                maxSize = canDelegatedMaxSize.getMaxSize() / 1000000;
                return Long.valueOf(canDelegatedMaxSize.getMaxSize());
            }
        }), new Function3<Protocol.Account, GrpcAPI.AccountResourceMessage, Long, Pair>() {
            @Override
            public Pair apply(Protocol.Account account, GrpcAPI.AccountResourceMessage accountResourceMessage, Long l) throws Exception {
                long stakedTrxToBandwidth;
                ResourcesBean buildV2 = ResourcesBean.buildV2(account, accountResourceMessage);
                long energyDelegatedToOther = i == 0 ? buildV2.getEnergyDelegatedToOther() : buildV2.getBandwidthDelegatedToOther();
                if (i == 0) {
                    stakedTrxToBandwidth = ResourcesBean.stakedTrxToEnergy(accountResourceMessage, l.longValue() / 1000000);
                } else {
                    stakedTrxToBandwidth = ResourcesBean.stakedTrxToBandwidth(accountResourceMessage, l.longValue() / 1000000);
                }
                return new Pair(Long.valueOf(energyDelegatedToOther), Long.valueOf(stakedTrxToBandwidth));
            }
        }).compose(RxSchedulers.io_main()).subscribe(new Consumer<Pair>() {
            @Override
            public void accept(Pair pair) throws Exception {
                long longValue = ((Long) pair.first).longValue();
                long longValue2 = ((Long) pair.second).longValue();
                if (mView != 0) {
                    if (i == 0) {
                        ((StakeManageDetailContract.View) mView).updateUI(longValue2, longValue);
                    } else {
                        ((StakeManageDetailContract.View) mView).updateUI(longValue2, longValue);
                    }
                }
            }
        });
    }

    @Override
    public void loadDataStakeForOther(String str, int i, boolean z, final long j) {
        ((StakeManageDetailContract.Model) this.mModel).getStakeResourceForOther(str, i, z, j).subscribe(new IObserver(new ICallback<StakeResourceDetailOutput>() {
            @Override
            public void onSubscribe(Disposable disposable) {
            }

            @Override
            public void onResponse(String str2, StakeResourceDetailOutput stakeResourceDetailOutput) {
                ((StakeManageDetailContract.View) mView).updateStakeForOtherList(j, stakeResourceDetailOutput);
            }

            @Override
            public void onFailure(String str2, String str3) {
                ((StakeManageDetailContract.View) mView).updateStakeListFail(j);
            }
        }, ""));
    }

    @Override
    void loadDataStakeForMe(String str, int i, int i2) {
        ((StakeManageDetailContract.Model) this.mModel).getStakeResourceForMe(str, i, i2).subscribe(new IObserver(new ICallback<StakeResourceDetailForMeOutput>() {
            @Override
            public void onSubscribe(Disposable disposable) {
            }

            @Override
            public void onResponse(String str2, StakeResourceDetailForMeOutput stakeResourceDetailForMeOutput) {
                ((StakeManageDetailContract.View) mView).updateStakeForMeList(stakeResourceDetailForMeOutput);
            }

            @Override
            public void onFailure(String str2, String str3) {
                ((StakeManageDetailContract.View) mView).updateStakeListFail(-1L);
            }
        }, ""));
    }

    @Override
    public void getAllAddresses() {
        ((StakeManageDetailContract.Model) this.mModel).getAllAddress().compose(RxSchedulers.io_main()).subscribe(new Consumer<HashMap<String, NameAddressExtraBean>>() {
            @Override
            public void accept(HashMap<String, NameAddressExtraBean> hashMap) throws Exception {
                ((StakeManageDetailContract.View) mView).setAddressMap(hashMap);
            }
        });
    }
}
