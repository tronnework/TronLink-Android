package com.tron.wallet.business.stakev2.stake.resource;

import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.wallet.business.stakev2.stake.resource.DelegateContract;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TimeUtil;
import com.tron.wallet.net.rpc.TronAPI;
import io.reactivex.functions.Consumer;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
public class DelegatePresenter extends DelegateContract.Presenter {
    @Override
    protected void onStart() {
        TimeUtil.init(((DelegateContract.View) this.mView).getIContext());
        this.mRxManager.on(Event.BroadcastSuccess, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$0(obj);
            }
        });
    }

    public void lambda$onStart$0(Object obj) throws Exception {
        if (this.mView != 0) {
            ((DelegateContract.View) this.mView).exit();
        }
    }

    @Override
    public void getResourceDate(final String str, final String str2, final int i) {
        ((DelegateContract.View) this.mView).runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$getResourceDate$1(str, str2, i);
            }
        });
    }

    public void lambda$getResourceDate$1(String str, String str2, int i) {
        long j;
        long j2;
        GrpcAPI.DelegatedResourceList delegatedResourceV2 = TronAPI.getDelegatedResourceV2(StringTronUtil.decodeFromBase58Check(str), StringTronUtil.decodeFromBase58Check(str2));
        if (delegatedResourceV2 != null) {
            if (i == TronConfig.RESOURCE_BANDWIDTH) {
                j = 0;
                j2 = 0;
                for (Protocol.DelegatedResource delegatedResource : delegatedResourceV2.getDelegatedResourceList()) {
                    if (delegatedResource.getExpireTimeForBandwidth() > j) {
                        j = delegatedResource.getExpireTimeForBandwidth();
                    }
                    if (delegatedResource.getExpireTimeForBandwidth() > System.currentTimeMillis()) {
                        j2 += delegatedResource.getFrozenBalanceForBandwidth();
                    }
                }
            } else {
                j = 0;
                j2 = 0;
                for (Protocol.DelegatedResource delegatedResource2 : delegatedResourceV2.getDelegatedResourceList()) {
                    if (delegatedResource2.getExpireTimeForEnergy() > j) {
                        j = delegatedResource2.getExpireTimeForEnergy();
                    }
                    if (delegatedResource2.getExpireTimeForEnergy() > System.currentTimeMillis()) {
                        j2 += delegatedResource2.getFrozenBalanceForEnergy();
                    }
                }
            }
            if (j > System.currentTimeMillis()) {
                ((DelegateContract.View) this.mView).updateDelegateViewData(j, j2);
                ((DelegateContract.View) this.mView).updateLockTimeView(j, j2);
                return;
            }
            ((DelegateContract.View) this.mView).updateLockTimeView(TimeUtil.defaultThreeDayMs + System.currentTimeMillis(), 0L);
            return;
        }
        ((DelegateContract.View) this.mView).updateLockTimeView(TimeUtil.defaultThreeDayMs + System.currentTimeMillis(), 0L);
    }

    @Override
    public void revertTheLockResourceSetting() {
        ((DelegateContract.View) this.mView).getDelegateResourceLockedView().setHasError(false);
        ((DelegateContract.View) this.mView).getDelegateResourceLockedView().hideErrorView();
        long currentTimeMillis = TimeUtil.defaultThreeDayMs + System.currentTimeMillis();
        if (((DelegateContract.View) this.mView).getDelegateResourceLockedView().getLeftTime() > 0) {
            currentTimeMillis = ((DelegateContract.View) this.mView).getDelegateResourceLockedView().getLeftTime();
        }
        ((DelegateContract.View) this.mView).updateLockTimeView(currentTimeMillis, ((DelegateContract.View) this.mView).getDelegateResourceLockedView().getFrozenBalance());
        ((DelegateContract.View) this.mView).updateTheNextBtnStatus();
    }
}
