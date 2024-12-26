package com.tron.wallet.business.tabswap;

import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.tabswap.SwapConfirmMockContract;
import com.tron.wallet.business.tabswap.bean.SwapTokenBean;
import com.tron.wallet.business.tabswap.mvp.SwapModel;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.ToastUtil;
import com.tron.wallet.common.utils.transaction.TriggerUtils;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.tron.api.GrpcAPI;
import org.tron.common.utils.abi.EncodingException;
public class SwapConfirmMockPresenter extends SwapConfirmMockContract.Presenter {
    public static final String TAG = "SwapConfirmMockPresenter";
    private Disposable disposable;

    @Override
    protected void onStart() {
        this.mRxManager.on(Event.BROADCAST_TRANSACTION, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$0(obj);
            }
        });
    }

    public void lambda$onStart$0(Object obj) throws Exception {
        ((SwapConfirmMockContract.View) this.mView).exit();
    }

    @Override
    public void approve(final String str, final String str2, final String str3, final String str4) {
        Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                SwapConfirmMockPresenter.lambda$approve$1(str, str2, str3, str4, observableEmitter);
            }
        }).compose(RxSchedulers2.io_main()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$approve$2((GrpcAPI.TransactionExtention) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$approve$3((Throwable) obj);
            }
        });
    }

    public static void lambda$approve$1(String str, String str2, String str3, String str4, ObservableEmitter observableEmitter) throws Exception {
        try {
            GrpcAPI.TransactionExtention approve = TronAPI.approve(str, str2, str3, str4);
            if (!approve.hasResult() || !approve.hasTransaction() || approve.getTransaction() == null || "".equals(approve.getTransaction())) {
                return;
            }
            LogUtils.w("energy-approve:" + approve.getEnergyUsed());
            observableEmitter.onNext(approve);
        } catch (EncodingException e) {
            LogUtils.e(e);
        }
    }

    public void lambda$approve$2(GrpcAPI.TransactionExtention transactionExtention) throws Exception {
        if (transactionExtention != null) {
            ((SwapConfirmMockContract.View) this.mView).startConfirmApprove(transactionExtention);
            return;
        }
        ToastUtil.getInstance().showToast(((SwapConfirmMockContract.View) this.mView).getIContext(), ((SwapConfirmMockContract.View) this.mView).getIContext().getString(R.string.swap_approve_failed));
        ((SwapConfirmMockContract.View) this.mView).exit();
    }

    public void lambda$approve$3(Throwable th) throws Exception {
        ToastUtil.getInstance().showToast(((SwapConfirmMockContract.View) this.mView).getIContext(), ((SwapConfirmMockContract.View) this.mView).getIContext().getString(R.string.swap_approve_failed));
        ((SwapConfirmMockContract.View) this.mView).exit();
    }

    @Override
    public void requestCheckApproved(final SwapTokenBean swapTokenBean, final String str) {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        Disposable subscribe = Observable.interval(3L, TimeUnit.SECONDS).take(12L).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$requestCheckApproved$4(swapTokenBean, str, atomicBoolean, (Long) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                LogUtils.e(SwapConfirmMockPresenter.TAG, (Throwable) obj);
            }
        }, new Action() {
            @Override
            public final void run() {
                lambda$requestCheckApproved$6(atomicBoolean);
            }
        });
        this.disposable = subscribe;
        addDisposable(subscribe);
    }

    public void lambda$requestCheckApproved$4(SwapTokenBean swapTokenBean, String str, AtomicBoolean atomicBoolean, Long l) throws Exception {
        if (BigDecimalUtils.moreThanOrEqual(TriggerUtils.getApproveAmount(swapTokenBean.getToken(), str, SwapModel.getSwapContractAddress()), new BigDecimal(swapTokenBean.getInputAmount()).multiply(new BigDecimal(Math.pow(10.0d, swapTokenBean.getDecimal()))))) {
            this.disposable.dispose();
            atomicBoolean.set(true);
            ((SwapConfirmMockContract.View) this.mView).requestSwap();
        }
    }

    public void lambda$requestCheckApproved$6(AtomicBoolean atomicBoolean) throws Exception {
        if (atomicBoolean.get()) {
            return;
        }
        ToastUtil.getInstance().showToast(((SwapConfirmMockContract.View) this.mView).getIContext(), ((SwapConfirmMockContract.View) this.mView).getIContext().getString(R.string.swap_approve_failed));
        ((SwapConfirmMockContract.View) this.mView).exit();
    }
}
