package com.tron.wallet.business.tronpower.unstake;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.confirm.parambuilder.BaseConfirmTransParamBuilder;
import com.tron.wallet.business.tronpower.unstake.adapter.UnStakeResourceBean;
import io.reactivex.Observable;
import java.util.Collection;
import java.util.List;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
public interface UnStakeContract {

    public interface Model extends BaseModel {
        BaseConfirmTransParamBuilder buildTransactionParam(Protocol.Account account, String str, boolean z, GrpcAPI.AccountResourceMessage accountResourceMessage, Collection<UnStakeResourceBean> collection);

        Observable<List<UnStakeResourceBean>> requestStakeResourceForOthers(String str, int i, int i2);

        Observable<List<UnStakeResourceBean>> requestStakeResourceForSelf(Protocol.Account account);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract boolean checkOwnerPermission(Protocol.Account account);

        abstract Observable<Protocol.Account> ensureAccount(boolean z, Protocol.Account account, String str);

        abstract void enterConfirm(boolean z);

        abstract void onItemSelectStateChanged(UnStakeResourceBean unStakeResourceBean);

        abstract void requestMore();

        abstract void requestStakeResource(Protocol.Account account);

        abstract void reset();

        abstract void setControllerAddress(String str);
    }

    public interface View extends BaseView {
        void updateResourceResult(boolean z, boolean z2, int i, List<UnStakeResourceBean> list);

        void updateSelectedResources(int i, long j);
    }
}
