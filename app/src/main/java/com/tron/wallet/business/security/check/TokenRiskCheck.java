package com.tron.wallet.business.security.check;

import android.text.TextUtils;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.security.ExecuteStatusEnum;
import com.tron.wallet.business.security.SecurityResult;
import com.tron.wallet.business.security.interfaces.SecurityInterface;
import com.tron.wallet.business.security.interfaces.SecurityResultInterface;
import com.tron.wallet.business.security.tokencheck.bean.TokenCheckBean;
import com.tron.wallet.business.security.tokencheck.bean.TokenCheckResultBean;
import com.tron.wallet.business.security.tokencheck.controller.IgnoreTokenManager;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class TokenRiskCheck implements SecurityInterface {
    private ExecuteStatusEnum executeStatusEnum = ExecuteStatusEnum.NotStart;
    private SecurityResult securityResult;
    private SecurityResultInterface securityResultInterface;

    @Override
    public ExecuteStatusEnum getExecuteStatus() {
        return this.executeStatusEnum;
    }

    @Override
    public SecurityResult getResult() {
        return this.securityResult;
    }

    @Override
    public void securityOnDestroy() {
    }

    @Override
    public void setSecurityResultInterface(SecurityResultInterface securityResultInterface) {
        this.securityResultInterface = securityResultInterface;
    }

    private void check() {
        this.securityResult = new SecurityResult();
        this.executeStatusEnum = ExecuteStatusEnum.Detecting;
        queryTokenSecurityList(WalletUtils.getSelectedWallet().getAddress()).subscribe(new IObserver(new ICallback<TokenCheckResultBean>() {
            @Override
            public void onSubscribe(Disposable disposable) {
            }

            @Override
            public void onResponse(String str, TokenCheckResultBean tokenCheckResultBean) {
                LogUtils.e("queryTokenSecurityList", tokenCheckResultBean.toString());
                if (tokenCheckResultBean == null) {
                    executeStatusEnum = ExecuteStatusEnum.Error;
                } else {
                    executeStatusEnum = ExecuteStatusEnum.Success;
                }
                securityResult.setTokenCheckData(handleResultWithIagnore(tokenCheckResultBean));
                securityResult.setExecuteStatusEnum(executeStatusEnum);
                setRiskNum(tokenCheckResultBean);
                if (securityResult == null || securityResultInterface == null) {
                    return;
                }
                securityResultInterface.onThreadReturnResult(securityResult);
            }

            @Override
            public void onFailure(String str, String str2) {
                executeStatusEnum = ExecuteStatusEnum.Error;
                securityResult.setExecuteStatusEnum(executeStatusEnum);
                if (securityResultInterface != null) {
                    securityResultInterface.onThreadReturnResult(securityResult);
                }
            }
        }, "queryTokenSecurityList"));
    }

    public TokenCheckResultBean handleResultWithIagnore(TokenCheckResultBean tokenCheckResultBean) {
        List<TokenCheckBean> queryAll = IgnoreTokenManager.getInstance().queryAll();
        if (queryAll != null && queryAll.size() != 0) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            Iterator<TokenCheckBean> it = tokenCheckResultBean.getData().getHighRiskList().iterator();
            while (true) {
                boolean z = false;
                if (!it.hasNext()) {
                    break;
                }
                TokenCheckBean next = it.next();
                for (TokenCheckBean tokenCheckBean : queryAll) {
                    if (TextUtils.equals(next.getContractAddress(), tokenCheckBean.getContractAddress())) {
                        z = true;
                    }
                }
                if (!z) {
                    arrayList.add(next);
                }
            }
            for (TokenCheckBean tokenCheckBean2 : tokenCheckResultBean.getData().getMediumRiskList()) {
                boolean z2 = false;
                for (TokenCheckBean tokenCheckBean3 : queryAll) {
                    if (TextUtils.equals(tokenCheckBean2.getContractAddress(), tokenCheckBean3.getContractAddress())) {
                        z2 = true;
                    }
                }
                if (!z2) {
                    arrayList2.add(tokenCheckBean2);
                }
            }
            tokenCheckResultBean.getData().getHighRiskList().clear();
            tokenCheckResultBean.getData().getMediumRiskList().clear();
            tokenCheckResultBean.getData().setHighRiskList(arrayList);
            tokenCheckResultBean.getData().setMediumRiskList(arrayList2);
        }
        return tokenCheckResultBean;
    }

    @Override
    public void securityStart() {
        check();
    }

    @Override
    public void securityStop() {
        if (this.executeStatusEnum == ExecuteStatusEnum.Success) {
            return;
        }
        this.executeStatusEnum = ExecuteStatusEnum.Suspend;
    }

    public Observable<TokenCheckResultBean> queryTokenSecurityList(String str) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).queryTokenSecurityList(str).compose(RxSchedulers.io_io());
    }

    public void setRiskNum(TokenCheckResultBean tokenCheckResultBean) {
        if (tokenCheckResultBean == null || tokenCheckResultBean.getData() == null || tokenCheckResultBean.getData().getHighRiskList() == null || tokenCheckResultBean.getData().getMediumRiskList() == null) {
            return;
        }
        int size = tokenCheckResultBean.getData().getHighRiskList().size();
        int size2 = tokenCheckResultBean.getData().getMediumRiskList().size();
        this.securityResult.setRiskNum(size);
        this.securityResult.setWaringNum(size2);
    }
}
