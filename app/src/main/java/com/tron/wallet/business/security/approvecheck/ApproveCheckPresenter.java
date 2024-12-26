package com.tron.wallet.business.security.approvecheck;

import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.confirm.fg.bean.CancelApproveParam;
import com.tron.wallet.business.security.approvecheck.ApproveCheckContract;
import com.tron.wallet.business.security.approvecheck.bean.ApproveListDatabeanOutput;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.common.config.Event;
import com.tronlinkpro.wallet.R;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.HashMap;
public class ApproveCheckPresenter extends ApproveCheckContract.Presenter {
    @Override
    protected void onStart() {
        this.mRxManager.on(Event.CANCEL_APPROVE_TRANSACTION, new Consumer<Object>() {
            @Override
            public void accept(Object obj) throws Exception {
                if (obj instanceof CancelApproveParam) {
                    if (ApproveListManager.getInstance().removeApproveItemByParam((CancelApproveParam) obj)) {
                        ApproveListDatabeanOutput data = ApproveListManager.getInstance().getData();
                        ((ApproveCheckContract.View) mView).updateApproveList("token", data);
                        ((ApproveCheckContract.View) mView).updateApproveList("project", data);
                    }
                }
                mRxManager.post(Event.UPDATE_APPROVE_COUNT, obj);
            }
        });
    }

    private void getAllAddress() {
        try {
            ((ApproveCheckContract.Model) this.mModel).getAllAddress().compose(RxSchedulers.io_main()).subscribe(new Consumer<HashMap<String, NameAddressExtraBean>>() {
                @Override
                public void accept(HashMap<String, NameAddressExtraBean> hashMap) throws Exception {
                    ((ApproveCheckContract.View) mView).setAddressMap(hashMap);
                }
            });
        } catch (Exception unused) {
            ((ApproveCheckContract.View) this.mView).setAddressMap(null);
        }
    }

    @Override
    public void reQuestApproveList(final String str, String str2) {
        ((ApproveCheckContract.Model) this.mModel).requestApproveList(str, str2).subscribe(new IObserver(new ICallback<ApproveListDatabeanOutput>() {
            @Override
            public void onResponse(String str3, ApproveListDatabeanOutput approveListDatabeanOutput) {
                if (approveListDatabeanOutput != null) {
                    ((ApproveCheckContract.View) mView).updateApproveList(str, approveListDatabeanOutput);
                }
            }

            @Override
            public void onFailure(String str3, String str4) {
                ((ApproveCheckContract.View) mView).toast(((ApproveCheckContract.View) mView).getIContext().getString(R.string.time_out));
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "requestApproveList"));
    }
}
