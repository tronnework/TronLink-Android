package com.tron.wallet.business.security.approvecheck;

import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.security.approvecheck.ApproveListContract;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.ToastUtil;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import org.tron.api.GrpcAPI;
import org.tron.common.utils.abi.EncodingException;
public class ApproveListPresenter extends ApproveListContract.Presenter {
    @Override
    protected void onStart() {
    }

    @Override
    public void approve(final String str, final String str2, final String str3, final String str4, final String str5, final String str6, final String str7, final String str8, final String str9, final String str10, final String str11) {
        ((ApproveListContract.View) this.mView).showLoadingDialog();
        Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$approve$0(str5, str, str2, str3, observableEmitter);
            }
        }).compose(RxSchedulers2.io_main()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$approve$1(str2, str3, str4, str6, str7, str8, str9, str10, str, str11, (GrpcAPI.TransactionExtention) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$approve$2((Throwable) obj);
            }
        });
    }

    public void lambda$approve$0(String str, String str2, String str3, String str4, ObservableEmitter observableEmitter) throws Exception {
        try {
            if (StringTronUtil.isEmpty(str)) {
                ((ApproveListContract.View) this.mView).runOnUIThread(new OnMainThread() {
                    @Override
                    public void doInUIThread() {
                        ((ApproveListContract.View) mView).dismissLoadingDialog();
                    }
                });
                return;
            }
            try {
                if (!WalletUtils.checkHaveOwnerPermissions(str, TronAPI.queryAccount(StringTronUtil.decodeFromBase58Check(str), false).getOwnerPermission())) {
                    ((ApproveListContract.View) this.mView).runOnUIThread(new OnMainThread() {
                        @Override
                        public void doInUIThread() {
                            ((ApproveListContract.View) mView).dismissLoadingDialog();
                            ToastUtil.getInstance().showToast(((ApproveListContract.View) mView).getIContext(), R.string.approve_not_support_mutil_sign);
                        }
                    });
                    return;
                }
            } catch (Exception e) {
                LogUtils.e(e);
            }
            GrpcAPI.TransactionExtention approve = TronAPI.approve(str2, str3, str4, str);
            if (!approve.hasResult() || !approve.hasTransaction() || approve.getTransaction() == null || "".equals(approve.getTransaction())) {
                return;
            }
            LogUtils.w("energy-approve:" + approve.getEnergyUsed());
            observableEmitter.onNext(approve);
        } catch (EncodingException e2) {
            LogUtils.e(e2);
        }
    }

    public void lambda$approve$1(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, GrpcAPI.TransactionExtention transactionExtention) throws Exception {
        if (transactionExtention != null) {
            ((ApproveListContract.View) this.mView).startCancelConfirmApprove(transactionExtention, str, str2, str3, str4, str5, str6, str7, str8, str, str9, str10);
            ((ApproveListContract.View) this.mView).dismissLoadingDialog();
            return;
        }
        ToastUtil.getInstance().showToast(((ApproveListContract.View) this.mView).getIContext(), ((ApproveListContract.View) this.mView).getIContext().getString(R.string.net_error));
        ((ApproveListContract.View) this.mView).dismissLoadingDialog();
    }

    public void lambda$approve$2(Throwable th) throws Exception {
        ToastUtil.getInstance().showToast(((ApproveListContract.View) this.mView).getIContext(), ((ApproveListContract.View) this.mView).getIContext().getString(R.string.net_error));
        ((ApproveListContract.View) this.mView).dismissLoadingDialog();
    }
}
