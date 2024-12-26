package com.tron.wallet.business.confirm.core.pending;

import android.os.Bundle;
import com.google.protobuf.InvalidProtocolBufferException;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.core.pending.ConfirmPendingContract;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.NFTParam;
import com.tron.wallet.business.confirm.parambuilder.bean.TransferParam;
import com.tron.wallet.business.message.TransactionMessage;
import com.tron.wallet.common.bean.token.TransactionInfoBean;
import com.tron.wallet.common.config.ContractReturnMessage;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.FailUtils;
import com.tron.wallet.common.utils.NftTransactionHistoryProvider;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TransactionHistoryProvider;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import j$.util.Collection;
import j$.util.Objects;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.tron.api.GrpcAPI;
import org.tron.common.utils.ByteArray;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
public class ConfirmPendingPresenter extends ConfirmPendingContract.Presenter {
    private BaseParam baseParam;
    private Disposable disposable;
    private byte[] rpcReturn;
    private long startTimeMill;
    private State state = State.Load;
    private int unsuccessfulTransactions;

    @Override
    protected void onStart() {
        this.startTimeMill = System.currentTimeMillis();
        this.mRxManager.post(Event.Vote_PENDING, "");
    }

    @Override
    public void initData() {
        Bundle iArguments = ((ConfirmPendingContract.View) this.mView).getIArguments();
        this.state = (State) iArguments.getSerializable(Keys.StateKey);
        this.rpcReturn = iArguments.getByteArray(Keys.RPCReturn);
        this.baseParam = (BaseParam) iArguments.getParcelable(Keys.BaseParam);
        this.unsuccessfulTransactions = iArguments.getInt(Keys.UnsuccessfulTransactions, 0);
        if (this.baseParam == null) {
            return;
        }
        addToLocal();
        try {
            List<byte[]> bytes = this.baseParam.getTransactionBean().getBytes();
            Protocol.Transaction parseFrom = Protocol.Transaction.parseFrom(bytes.get(bytes.size() - 1));
            Protocol.Transaction.Contract.ContractType type = parseFrom.getRawData().getContract(0).getType();
            String hash = TransactionUtils.getHash(parseFrom);
            int type2 = this.baseParam.getType();
            if ((type == Protocol.Transaction.Contract.ContractType.TriggerSmartContract || type2 == 9 || type2 == 1) && this.state == State.Success) {
                getTransactionInfo(TransactionUtils.getHash(parseFrom));
            } else if (this.state == State.Success) {
                if (type2 != 4 && type2 != 3 && type2 != 22 && type2 != 35 && type2 != 36 && type2 != 34 && type2 != 37) {
                    ((ConfirmPendingContract.View) this.mView).onSuccess(null, this.baseParam);
                    return;
                }
                Observable.timer(1500 - (System.currentTimeMillis() - this.startTimeMill), TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long l) throws Exception {
                        ((ConfirmPendingContract.View) mView).onSuccess(null, baseParam);
                    }
                });
            } else if (this.state == State.Fail || this.state == State.TimeOut) {
                removeLocalTransaction(hash);
                String str = "";
                if (this.state == State.TimeOut) {
                    str = StringTronUtil.getResString(R.string.net_time_out);
                } else if (this.state == State.Fail) {
                    str = parseFailMessage(parseReturn(this.rpcReturn));
                }
                ((ConfirmPendingContract.View) this.mView).onFail(this.baseParam, this.unsuccessfulTransactions, str);
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    private void removeLocalTransaction(String str) {
        if (this.baseParam.getType() == 21 && (this.baseParam instanceof NFTParam)) {
            NftTransactionHistoryProvider.removeLocalTransaction(str);
        } else if (this.baseParam.getType() == 1 && (this.baseParam instanceof TransferParam)) {
            TransactionHistoryProvider.removeLocalTransaction(str);
        }
    }

    private void addToLocal() {
        BaseParam baseParam = this.baseParam;
        if (baseParam == null) {
            return;
        }
        if (baseParam.getType() == 21) {
            BaseParam baseParam2 = this.baseParam;
            if (baseParam2 instanceof NFTParam) {
                try {
                    Collection.-EL.stream(baseParam2.getTransactionBean().getBytes()).forEach(new java.util.function.Consumer() {
                        @Override
                        public final void accept(Object obj) {
                            ConfirmPendingPresenter.lambda$addToLocal$0((byte[]) obj);
                        }

                        public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                            return Objects.requireNonNull(consumer);
                        }
                    });
                    return;
                } catch (Exception e) {
                    LogUtils.e(e);
                    return;
                }
            }
        }
        if (this.baseParam.getType() == 1) {
            BaseParam baseParam3 = this.baseParam;
            if ((baseParam3 instanceof TransferParam) && (baseParam3 instanceof TransferParam)) {
                try {
                    TransactionHistoryProvider.addLocalTransaction(((TransferParam) baseParam3).tokenBean, Protocol.Transaction.parseFrom(this.baseParam.getTransactionBean().getBytes().get(0)));
                } catch (Exception e2) {
                    LogUtils.e(e2);
                }
            }
        }
    }

    public static void lambda$addToLocal$0(byte[] bArr) {
        try {
            NftTransactionHistoryProvider.addLocalTransaction(Protocol.Transaction.parseFrom(bArr));
        } catch (InvalidProtocolBufferException e) {
            LogUtils.e(e);
        }
    }

    @Override
    public void getTransactionInfo(final String str) {
        Disposable subscribe = Observable.interval(3L, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long l) throws Exception {
                ((ConfirmPendingContract.Model) mModel).getTransactionInfo(str).subscribe(new IObserver(new ICallback<TransactionInfoBean>() {
                    @Override
                    public void onResponse(String str2, TransactionInfoBean transactionInfoBean) {
                        String contractRet = transactionInfoBean.getContractRet();
                        if (TransactionMessage.TYPE_SUCCESS.equals(contractRet) && !transactionInfoBean.isRevert()) {
                            state = State.Success;
                            ((ConfirmPendingContract.View) mView).onSuccess(transactionInfoBean, baseParam);
                            disposable.dispose();
                        } else if ("" == contractRet || contractRet == null) {
                        } else {
                            state = State.Fail;
                            ((ConfirmPendingContract.View) mView).onFail(baseParam, 0, StringTronUtil.getResString(ContractReturnMessage.getMessageResource(contractRet)));
                            disposable.dispose();
                        }
                    }

                    @Override
                    public void onFailure(String str2, String str3) {
                        State unused = state;
                        State state = State.Load;
                    }

                    @Override
                    public void onSubscribe(Disposable disposable) {
                        addDisposable(disposable);
                    }
                }, "getTransactionInfo"));
            }
        });
        this.disposable = subscribe;
        addDisposable(subscribe);
    }

    @Override
    public GrpcAPI.Return parseReturn(byte[] bArr) {
        if (bArr == null || ByteArray.isEmpty(bArr)) {
            return null;
        }
        try {
            return GrpcAPI.Return.parseFrom(bArr);
        } catch (Exception e) {
            LogUtils.e(e);
            return null;
        }
    }

    public String parseFailMessage(GrpcAPI.Return r1) {
        return FailUtils.getFailMessages(r1);
    }
}
