package com.tron.wallet.business.tokendetail.mvp;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.tokendetail.mvp.TokenDetailContentContract;
import com.tron.wallet.common.bean.token.TransactionInfoBean;
import com.tron.wallet.common.bean.token.TransferOutput;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import java.util.HashMap;
public class TokenDetailContentModel implements TokenDetailContentContract.Model {
    @Override
    public Observable<TransferOutput> getTRXTransfer(String str, int i, int i2, long j, long j2, int i3, boolean z, boolean z2, boolean z3) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getTRXTransfer(str, i, i2, j, j2, i3, z, z2, z3, 1).flatMap(new Function<Object, Observable<TransferOutput>>() {
            @Override
            public Observable<TransferOutput> apply(Object obj) throws Exception {
                Gson gson = new Gson();
                TransferOutput transferOutput = (TransferOutput) gson.fromJson(gson.toJson(obj),  TransferOutput.class);
                transferOutput.contractsMap = new HashMap<>();
                if (obj != null) {
                    LinkedTreeMap linkedTreeMap = (LinkedTreeMap) obj;
                    if (linkedTreeMap.get("contractMap") != null) {
                        LinkedTreeMap linkedTreeMap2 = (LinkedTreeMap) linkedTreeMap.get("contractMap");
                        for (String str2 : linkedTreeMap2.keySet()) {
                            transferOutput.contractsMap.put(str2, (Boolean) linkedTreeMap2.get(str2));
                        }
                    }
                }
                return Observable.just(transferOutput);
            }
        }).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<TransferOutput> getTRX10Transfer(String str, int i, int i2, long j, long j2, int i3, boolean z, String str2, boolean z2) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getTRX10Transfer(str, i, i2, j, j2, i3, z, SpAPI.THIS.getCurIsMainChain(), z2, str2, 1).flatMap(new Function<Object, Observable<TransferOutput>>() {
            @Override
            public Observable<TransferOutput> apply(Object obj) throws Exception {
                Gson gson = new Gson();
                TransferOutput transferOutput = (TransferOutput) gson.fromJson(gson.toJson(obj),  TransferOutput.class);
                transferOutput.contractsMap = new HashMap<>();
                if (obj != null) {
                    LinkedTreeMap linkedTreeMap = (LinkedTreeMap) obj;
                    if (linkedTreeMap.get("contractMap") != null) {
                        LinkedTreeMap linkedTreeMap2 = (LinkedTreeMap) linkedTreeMap.get("contractMap");
                        for (String str3 : linkedTreeMap2.keySet()) {
                            transferOutput.contractsMap.put(str3, (Boolean) linkedTreeMap2.get(str3));
                        }
                    }
                }
                return Observable.just(transferOutput);
            }
        }).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<TransferOutput> getTRXTransfer20(String str, int i, int i2, long j, long j2, int i3, boolean z, String str2, boolean z2) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getTRX20Transfer(str, i, i2, j, j2, i3, z, SpAPI.THIS.getCurIsMainChain(), z2, str2, 1).flatMap(new Function<Object, Observable<TransferOutput>>() {
            @Override
            public Observable<TransferOutput> apply(Object obj) throws Exception {
                Gson gson = new Gson();
                TransferOutput transferOutput = (TransferOutput) gson.fromJson(gson.toJson(obj),  TransferOutput.class);
                transferOutput.contractsMap = new HashMap<>();
                if (obj != null) {
                    LinkedTreeMap linkedTreeMap = (LinkedTreeMap) obj;
                    if (linkedTreeMap.get("contractMap") != null) {
                        LinkedTreeMap linkedTreeMap2 = (LinkedTreeMap) linkedTreeMap.get("contractMap");
                        for (String str3 : linkedTreeMap2.keySet()) {
                            transferOutput.contractsMap.put(str3, (Boolean) linkedTreeMap2.get(str3));
                        }
                    }
                }
                return Observable.just(transferOutput);
            }
        }).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<TransferOutput> getWithdrawOrDeposit(String str, String str2, String str3, int i, int i2, String str4) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).withdrawOrdeposit(str, str2, str3, i, i2, str4).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<TransactionInfoBean> getTransactionInfo(String str) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getTrasactionInfo(str).compose(RxSchedulers.io_main());
    }
}
