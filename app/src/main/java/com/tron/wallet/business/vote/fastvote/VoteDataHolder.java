package com.tron.wallet.business.vote.fastvote;

import com.tron.wallet.common.utils.DataStatHelper;
import com.tron.wallet.db.wallet.WalletUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class VoteDataHolder {
    private Protocol.Account account;
    private final ViewCallback callback;
    private final String controllerAddress;
    private boolean isFromMultiSign;
    private boolean isFromStakeSuccess;
    private String voteSrName;
    private int type = 3;
    private final Wallet selectedWallet = WalletUtils.getSelectedWallet();

    public static abstract class ViewCallback {
        public void onVoteComplete() {
        }

        public void onVoteError(int i) {
        }

        public void onVoteErrorMessage(String str) {
        }
    }

    public static ObservableSource lambda$asyncVote$1(Object obj) throws Exception {
        return null;
    }

    public VoteDataHolder(String str, Protocol.Account account, ViewCallback viewCallback, String str2, boolean z, boolean z2) {
        this.callback = viewCallback;
        this.controllerAddress = str;
        this.account = account;
        this.voteSrName = str2;
        this.isFromMultiSign = z;
        this.isFromStakeSuccess = z2;
    }

    public void asyncVote(final HashMap<String, String> hashMap, final HashMap<String, String> hashMap2, int i, final DataStatHelper.StatAction statAction, final String str) {
        this.type = i;
        Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$asyncVote$0(hashMap, hashMap2, statAction, str, observableEmitter);
            }
        }).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                return VoteDataHolder.lambda$asyncVote$1(obj);
            }
        }).subscribeOn(Schedulers.io()).onErrorResumeNext(new Observable<Object>() {
            @Override
            protected void subscribeActual(Observer<? super Object> observer) {
                observer.onError(new Exception());
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable disposable) {
            }

            @Override
            public void onNext(Object obj) {
                if (callback != null) {
                    callback.onVoteComplete();
                }
            }

            @Override
            public void onError(Throwable th) {
                if (callback != null) {
                    callback.onVoteErrorMessage(th.getMessage());
                }
            }

            @Override
            public void onComplete() {
                if (callback != null) {
                    callback.onVoteComplete();
                }
            }
        });
    }

    public void lambda$asyncVote$0(HashMap hashMap, HashMap hashMap2, DataStatHelper.StatAction statAction, String str, ObservableEmitter observableEmitter) throws Exception {
        vote(hashMap, hashMap2, this.type, statAction, str);
    }

    public void vote(java.util.HashMap<java.lang.String, java.lang.String> r25, java.util.HashMap<java.lang.String, java.lang.String> r26, int r27, com.tron.wallet.common.utils.DataStatHelper.StatAction r28, java.lang.String r29) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.vote.fastvote.VoteDataHolder.vote(java.util.HashMap, java.util.HashMap, int, com.tron.wallet.common.utils.DataStatHelper$StatAction, java.lang.String):void");
    }
}
