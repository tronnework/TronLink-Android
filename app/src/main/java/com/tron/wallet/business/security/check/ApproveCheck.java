package com.tron.wallet.business.security.check;

import androidx.core.util.Pair;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.security.ExecuteStatusEnum;
import com.tron.wallet.business.security.SecurityResult;
import com.tron.wallet.business.security.approvecheck.ApproveCheckModel;
import com.tron.wallet.business.security.approvecheck.ApproveListManager;
import com.tron.wallet.business.security.approvecheck.bean.ApproveListDatabeanOutput;
import com.tron.wallet.business.security.interfaces.SecurityInterface;
import com.tron.wallet.business.security.interfaces.SecurityResultInterface;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
public class ApproveCheck implements SecurityInterface {
    private ExecuteStatusEnum executeStatusEnum = ExecuteStatusEnum.NotStart;
    RxManager rxManager = new RxManager();
    private SecurityResult securityResult;
    SecurityResultInterface securityResultInterface;

    @Override
    public ExecuteStatusEnum getExecuteStatus() {
        return this.executeStatusEnum;
    }

    @Override
    public SecurityResult getResult() {
        return this.securityResult;
    }

    @Override
    public void setSecurityResultInterface(SecurityResultInterface securityResultInterface) {
        this.securityResultInterface = securityResultInterface;
    }

    @Override
    public void securityStart() {
        this.securityResult = new SecurityResult();
        this.executeStatusEnum = ExecuteStatusEnum.Detecting;
        this.rxManager.on(Event.UPDATE_APPROVE_COUNT, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$securityStart$0(obj);
            }
        });
        check();
    }

    public void lambda$securityStart$0(Object obj) throws Exception {
        ApproveListDatabeanOutput data = ApproveListManager.getInstance().getData();
        this.securityResult.setRiskNum(Integer.parseInt(data.getData().getApproveAddrCount()) + Integer.parseInt(data.getData().getApproveContractCount()));
        this.securityResult.setAuthorizationCheckData(new SecurityResult.AuthorizationCheckData(data));
        SecurityResultInterface securityResultInterface = this.securityResultInterface;
        if (securityResultInterface != null) {
            securityResultInterface.onThreadReturnResult(this.securityResult);
        }
    }

    @Override
    public void securityStop() {
        if (this.executeStatusEnum == ExecuteStatusEnum.Success) {
            return;
        }
        this.executeStatusEnum = ExecuteStatusEnum.Suspend;
    }

    @Override
    public void securityOnDestroy() {
        this.rxManager.removeDisposableByKey("queryApproveList");
        ApproveListManager.getInstance().clearData();
    }

    private void check() {
        String address = WalletUtils.getSelectedWallet().getAddress();
        ApproveCheckModel approveCheckModel = new ApproveCheckModel();
        Observable.zip(approveCheckModel.requestApproveList("token", address), approveCheckModel.requestApproveList("project", address), new BiFunction() {
            @Override
            public final Object apply(Object obj, Object obj2) {
                return ApproveCheck.lambda$check$1((ApproveListDatabeanOutput) obj, (ApproveListDatabeanOutput) obj2);
            }
        }).compose(RxSchedulers2.io_main()).subscribe(new IObserver(new ICallback<Pair<ApproveListDatabeanOutput, ApproveListDatabeanOutput>>() {
            @Override
            public void onResponse(String str, Pair<ApproveListDatabeanOutput, ApproveListDatabeanOutput> pair) {
                if (pair == null && pair.first != null && pair.second != null) {
                    executeStatusEnum = ExecuteStatusEnum.Error;
                    return;
                }
                executeStatusEnum = ExecuteStatusEnum.Success;
                ApproveListDatabeanOutput approveListDatabeanOutput = pair.first;
                ApproveListDatabeanOutput approveListDatabeanOutput2 = pair.second;
                if (approveListDatabeanOutput2 != null && approveListDatabeanOutput2.getData() != null && approveListDatabeanOutput2.getData().getProjects() != null) {
                    approveListDatabeanOutput.getData().setProjects(approveListDatabeanOutput2.getData().getProjects());
                }
                securityResult = new SecurityResult();
                ApproveListManager.getInstance().initData(approveListDatabeanOutput);
                securityResult.setRiskNum(Integer.parseInt(approveListDatabeanOutput.getData().getApproveAddrCount()) + Integer.parseInt(approveListDatabeanOutput.getData().getApproveContractCount()));
                securityResult.setAuthorizationCheckData(new SecurityResult.AuthorizationCheckData(approveListDatabeanOutput));
                if (securityResultInterface != null) {
                    securityResultInterface.onThreadReturnResult(securityResult);
                }
            }

            @Override
            public void onFailure(String str, String str2) {
                executeStatusEnum = ExecuteStatusEnum.Error;
                securityResult.setExecuteStatusEnum(executeStatusEnum);
                if (securityResultInterface != null) {
                    securityResultInterface.onThreadReturnResult(securityResult);
                }
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                rxManager.add("queryApproveList", disposable);
            }
        }, "queryApproveList"));
    }

    public static Pair lambda$check$1(ApproveListDatabeanOutput approveListDatabeanOutput, ApproveListDatabeanOutput approveListDatabeanOutput2) throws Exception {
        return new Pair(approveListDatabeanOutput, approveListDatabeanOutput2);
    }

    private Observable<ApproveListDatabeanOutput> queryApproveList(String str) {
        return new ApproveCheckModel().requestApproveList("token", str);
    }
}
