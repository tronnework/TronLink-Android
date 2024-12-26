package com.tron.wallet.business.stakev2;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.rpc.TronAPI;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class QueryAccountModel {
    public Observable<GrpcAPI.AccountResourceMessage> getAccountResourceMessage(final GrpcAPI.AccountResourceMessage accountResourceMessage, final String str) {
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                QueryAccountModel.lambda$getAccountResourceMessage$0(GrpcAPI.AccountResourceMessage.this, str, observableEmitter);
            }
        });
    }

    public static void lambda$getAccountResourceMessage$0(GrpcAPI.AccountResourceMessage accountResourceMessage, String str, ObservableEmitter observableEmitter) throws Exception {
        if (accountResourceMessage == null || "".equals(accountResourceMessage.toString())) {
            GrpcAPI.AccountResourceMessage accountRes = TronAPI.getAccountRes(StringTronUtil.isEmpty(str) ? IRequest.getQueryAccountResMessageAddress() : str);
            if (accountRes != null && !"".equals(accountRes.toString())) {
                observableEmitter.onNext(accountRes);
                return;
            }
            Wallet walletForAddress = WalletUtils.getWalletForAddress(str);
            if (walletForAddress == null || StringTronUtil.isEmpty(walletForAddress.getWalletName())) {
                return;
            }
            GrpcAPI.AccountResourceMessage accountRes2 = WalletUtils.getAccountRes(AppContextUtil.getContext(), walletForAddress.getWalletName());
            if (accountRes2 != null && accountRes2.getTotalNetLimit() != 0) {
                observableEmitter.onNext(accountRes2);
                return;
            } else {
                observableEmitter.onNext(GrpcAPI.AccountResourceMessage.getDefaultInstance());
                return;
            }
        }
        observableEmitter.onNext(accountResourceMessage);
    }

    public Observable<Protocol.Account> getAccount(final Protocol.Account account, final String str) {
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                QueryAccountModel.lambda$getAccount$1(Protocol.Account.this, str, observableEmitter);
            }
        });
    }

    public static void lambda$getAccount$1(Protocol.Account account, String str, ObservableEmitter observableEmitter) throws Exception {
        Protocol.Account account2;
        if (account == null || "".equals(account.toString())) {
            try {
                account2 = TronAPI.queryAccount(StringTronUtil.decode58Check(str), false);
            } catch (Exception e) {
                LogUtils.e(e);
                account2 = null;
            }
            if (account2 != null && !"".equals(account2.toString())) {
                observableEmitter.onNext(account2);
                return;
            }
            Wallet walletForAddress = WalletUtils.getWalletForAddress(str);
            if (walletForAddress == null || StringTronUtil.isEmpty(walletForAddress.getWalletName())) {
                return;
            }
            Protocol.Account account3 = WalletUtils.getAccount(AppContextUtil.getContext(), walletForAddress.getWalletName());
            if (account3 != null) {
                observableEmitter.onNext(account3);
                return;
            } else {
                observableEmitter.onNext(Protocol.Account.getDefaultInstance());
                return;
            }
        }
        observableEmitter.onNext(account);
    }
}
