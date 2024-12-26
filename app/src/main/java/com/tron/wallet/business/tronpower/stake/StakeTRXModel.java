package com.tron.wallet.business.tronpower.stake;

import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.wallet.business.tronpower.stake.StakeTRXContract;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.rpc.TronAPI;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class StakeTRXModel implements StakeTRXContract.Model {
    @Override
    public Observable<GrpcAPI.AccountResourceMessage> getAccountResourceMessage() {
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                StakeTRXModel.lambda$getAccountResourceMessage$0(observableEmitter);
            }
        });
    }

    public static void lambda$getAccountResourceMessage$0(ObservableEmitter observableEmitter) throws Exception {
        Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
        GrpcAPI.AccountResourceMessage accountRes = WalletUtils.getAccountRes(AppContextUtil.getContext(), selectedPublicWallet.getWalletName());
        if (accountRes != null && accountRes.getTotalNetLimit() != 0) {
            observableEmitter.onNext(accountRes);
        } else {
            observableEmitter.onNext(TronAPI.getAccountRes(selectedPublicWallet.getDecode58CheckAddress()));
        }
    }

    @Override
    public Observable<Protocol.Account> getAccount(final Protocol.Account account, final String str) {
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                StakeTRXModel.lambda$getAccount$1(Protocol.Account.this, str, observableEmitter);
            }
        });
    }

    public static void lambda$getAccount$1(Protocol.Account account, String str, ObservableEmitter observableEmitter) throws Exception {
        Protocol.Account account2;
        if (account == null) {
            Protocol.Account queryAccount = TronAPI.queryAccount(StringTronUtil.decode58Check(str), false);
            if (queryAccount != null) {
                observableEmitter.onNext(queryAccount);
                return;
            }
            Wallet walletForAddress = WalletUtils.getWalletForAddress(str);
            if (walletForAddress == null || StringTronUtil.isEmpty(walletForAddress.getWalletName()) || (account2 = WalletUtils.getAccount(AppContextUtil.getContext(), walletForAddress.getWalletName())) == null) {
                return;
            }
            observableEmitter.onNext(account2);
            return;
        }
        observableEmitter.onNext(account);
    }
}
