package com.tron.wallet.business.stakev2.stake.pop.stakev1;

import android.content.Context;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.wallet.business.confirm.parambuilder.BaseConfirmTransParamBuilder;
import io.reactivex.Observable;
import java.util.Collection;
import java.util.List;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
public class StakeV2PopContract {

    interface IView {
        Context getIContext();

        void updateResourceResult(boolean z, boolean z2, List<StakeDetailResourceBean> list);
    }

    interface Model extends BaseModel {
        BaseConfirmTransParamBuilder buildTransactionParam(Protocol.Account account, String str, boolean z, GrpcAPI.AccountResourceMessage accountResourceMessage, Collection<StakeDetailResourceBean> collection);

        Observable<List<StakeDetailResourceBean>> requestStakeResourceForOthers(String str, int i, int i2);

        Observable<List<StakeDetailResourceBean>> requestStakeResourceForSelf(Protocol.Account account);
    }

    public static abstract class Presenter extends BasePresenter<Model, IView> {
        abstract boolean checkOwnerPermission(Protocol.Account account);

        public abstract Observable<Protocol.Account> ensureAccount(boolean z, Protocol.Account account, String str);

        public abstract void enterConfirm(boolean z, StakeDetailResourceBean stakeDetailResourceBean);

        public abstract void requestMore();

        public abstract void requestStakeResource(Protocol.Account account);
    }
}
