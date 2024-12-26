package com.tron.wallet.business.transfer.selecttoken;

import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.bean.AssetsQueryOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftItemInfo;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.parambuilder.bean.TransferParam;
import com.tron.wallet.business.confirm.parambuilder.utils.ParamBuildUtils;
import com.tron.wallet.business.transfer.selecttoken.TransferSelectTokenContract;
import com.tron.wallet.common.bean.RecentSendBean;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.M;
import com.tron.wallet.common.config.T;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.SamsungMultisignUtils;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.Controller.RecentSendController;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class TransferSelectTokenPresenter extends TransferSelectTokenContract.Presenter {
    private static final String TAG = "TransferSelectTokenPresenter";
    private boolean isMultiSign;
    private Protocol.Account receiveAccount;
    private Wallet selectedWallet;
    private Protocol.Account sendAccount;
    private GrpcAPI.AccountResourceMessage sendAccountResource;
    private TokenBean tokenBean;
    private TransferParam transferParam;

    @Override
    public GrpcAPI.AccountResourceMessage getSendAccountResource() {
        return this.sendAccountResource;
    }

    @Override
    public Wallet getWallet() {
        return this.selectedWallet;
    }

    @Override
    public void setMultiSign(boolean z) {
        this.isMultiSign = z;
    }

    @Override
    public void setTokenBean(TokenBean tokenBean) {
        this.tokenBean = tokenBean;
    }

    @Override
    protected void onStart() {
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        this.selectedWallet = selectedWallet;
        if (selectedWallet == null) {
            ((TransferSelectTokenContract.View) this.mView).exit();
            return;
        }
        this.mRxManager.on(Event.TRANSFER_INNER, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$0(obj);
            }
        });
        this.mRxManager.on(Event.BroadcastSuccess, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$1(obj);
            }
        });
        this.mRxManager.on(Event.BackToHome, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$2(obj);
            }
        });
    }

    public void lambda$onStart$0(Object obj) throws Exception {
        if (this.mView != 0) {
            ((TransferSelectTokenContract.View) this.mView).exit();
        }
    }

    public void lambda$onStart$1(Object obj) throws Exception {
        if (this.mView != 0) {
            ((TransferSelectTokenContract.View) this.mView).exit();
        }
    }

    public void lambda$onStart$2(Object obj) throws Exception {
        if (this.mView != 0) {
            ((TransferSelectTokenContract.View) this.mView).exit();
        }
    }

    @Override
    public void start() {
        if (this.selectedWallet.getAddress().equals(this.transferParam.fromAddress)) {
            this.sendAccountResource = WalletUtils.getAccountRes(((TransferSelectTokenContract.View) this.mView).getIContext(), this.selectedWallet.getWalletName());
            ((TransferSelectTokenContract.View) this.mView).setSendAccountResource(this.sendAccountResource);
        }
        if (this.sendAccount == null || this.receiveAccount == null) {
            ((TransferSelectTokenContract.View) this.mView).runOnThreeThread(new OnBackground() {
                @Override
                public final void doOnBackground() {
                    lambda$start$3();
                }
            });
        }
        ((TransferSelectTokenContract.Model) this.mModel).getAccountResource(this.transferParam.fromAddress).subscribe(new IObserver(new ICallback<GrpcAPI.AccountResourceMessage>() {
            @Override
            public void onResponse(String str, GrpcAPI.AccountResourceMessage accountResourceMessage) {
                if (accountResourceMessage != null) {
                    sendAccountResource = accountResourceMessage;
                    ((TransferSelectTokenContract.View) mView).setSendAccountResource(accountResourceMessage);
                }
            }

            @Override
            public void onFailure(String str, String str2) {
                LogUtils.d(TransferSelectTokenPresenter.TAG, str + ":" + str2);
                ((TransferSelectTokenContract.View) mView).toast(((TransferSelectTokenContract.View) mView).getIContext().getString(R.string.time_out));
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "getAccountResource"));
    }

    public void lambda$start$3() {
        if (this.sendAccount == null) {
            try {
                this.sendAccount = TronAPI.queryAccount(StringTronUtil.decode58Check(this.transferParam.getFromAddress()), false);
            } catch (Throwable th) {
                LogUtils.e(th);
            }
        }
        if (this.receiveAccount == null) {
            try {
                this.receiveAccount = TronAPI.queryAccount(StringTronUtil.decode58Check(this.transferParam.getToAddress()), false);
            } catch (Throwable th2) {
                LogUtils.e(th2);
            }
        }
    }

    @Override
    public void setParam(TransferParam transferParam) {
        this.transferParam = transferParam;
        this.sendAccount = T.sendAccount;
        this.receiveAccount = T.receiveAccount;
    }

    @Override
    public void send(final String str, final BigDecimal bigDecimal, final NftItemInfo nftItemInfo) {
        TronConfig.currentPwdType = 11;
        final byte[] decodeFromBase58Check = StringTronUtil.decodeFromBase58Check(this.transferParam.getFromAddress());
        final byte[] decodeFromBase58Check2 = StringTronUtil.decodeFromBase58Check(this.transferParam.getToAddress());
        ((TransferSelectTokenContract.View) this.mView).runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$send$5(decodeFromBase58Check2, decodeFromBase58Check, bigDecimal, nftItemInfo, str);
            }
        });
    }

    public void lambda$send$5(byte[] r19, byte[] r20, java.math.BigDecimal r21, final com.tron.wallet.business.addassets2.bean.nft.NftItemInfo r22, final java.lang.String r23) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.transfer.selecttoken.TransferSelectTokenPresenter.lambda$send$5(byte[], byte[], java.math.BigDecimal, com.tron.wallet.business.addassets2.bean.nft.NftItemInfo, java.lang.String):void");
    }

    public void lambda$send$4(boolean z, Protocol.Transaction transaction, NftItemInfo nftItemInfo, String str, GrpcAPI.TransactionExtention transactionExtention) {
        if ((this.selectedWallet.isSamsungWallet() && !z) || (SamsungMultisignUtils.isSamsungWallet(this.transferParam.getFromAddress()) && this.sendAccount != null && SamsungMultisignUtils.isSamsungMultiOwner(this.transferParam.getFromAddress(), this.sendAccount.getOwnerPermission()))) {
            if (this.mView != 0) {
                ((TransferSelectTokenContract.View) this.mView).setSendResult(false, R.string.no_samsung_to_shield);
                return;
            }
            return;
        }
        ((TransferSelectTokenContract.View) this.mView).setSendResult(true, 0);
        if (transaction != null) {
            if (!transaction.toString().equals("") && this.sendAccount != null) {
                Wallet wallet = this.selectedWallet;
                if (this.tokenBean.getType() == 5) {
                    ConfirmTransactionNewActivity.startActivity(((TransferSelectTokenContract.View) this.mView).getIContext(), ParamBuildUtils.getCollectibleTransactionParamBuilder(z, this.isMultiSign, transaction, this.tokenBean, nftItemInfo.getAssetId(), this.tokenBean.getName(), this.tokenBean.getShortName(), this.tokenBean.getLogoUrl(), this.transferParam.getFromAddress(), this.transferParam.getToAddress(), str, this.tokenBean.getContractAddress(), StringTronUtil.isEmpty(nftItemInfo.getName()) ? "" : nftItemInfo.getName(), nftItemInfo.getImageUrl(), 0L), wallet != null && wallet.getCreateType() == 7);
                    return;
                } else {
                    ConfirmTransactionNewActivity.startActivity(((TransferSelectTokenContract.View) this.mView).getIContext(), ParamBuildUtils.getTransferTransactionParamBuilder(getTokenTypeStr(), this.transferParam.getFromAddress(), this.transferParam.getAccountActive(), this.tokenBean, z, this.isMultiSign, transaction, false, null, 0L), wallet != null && wallet.getCreateType() == 7);
                    return;
                }
            }
        }
        try {
            ((TransferSelectTokenContract.View) this.mView).ToastSuc(transactionExtention.getResult().getMessage().toString(Charset.forName("utf-8")));
        } catch (Exception unused) {
        }
    }

    @Override
    public void getDefaultNftItem(String str) {
        ((TransferSelectTokenContract.Model) this.mModel).getDefaultNftItemInfo(this.transferParam.getFromAddress(), str).subscribe(new IObserver(new ICallback<NftItemInfo>() {
            @Override
            public void onResponse(String str2, NftItemInfo nftItemInfo) {
                if (nftItemInfo != null && tokenBean.getContractAddress().equals(nftItemInfo.getTokenAddress())) {
                    ((TransferSelectTokenContract.View) mView).updateDefaultNftItemInfo(nftItemInfo);
                } else {
                    ((TransferSelectTokenContract.View) mView).updateDefaultNftItemInfo(null);
                }
            }

            @Override
            public void onFailure(String str2, String str3) {
                LogUtils.d(TransferSelectTokenPresenter.TAG, "" + str2 + ":" + str3);
                ((TransferSelectTokenContract.View) mView).updateDefaultNftItemInfo(null);
                ((TransferSelectTokenContract.View) mView).toast(((TransferSelectTokenContract.View) mView).getIContext().getString(R.string.time_out));
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "getDefaultNftItem"));
    }

    @Override
    public Observable<AssetsQueryOutput> queryAssets(String str, int i, String str2) {
        return ((TransferSelectTokenContract.Model) this.mModel).queryAssets(str, i, str2);
    }

    private long mul(double d, double d2) {
        return new BigDecimal(Double.toString(d)).multiply(new BigDecimal(Double.toString(d2))).longValue();
    }

    private void addRecentReceiveAddress() {
        try {
            RecentSendBean recentSendBean = new RecentSendBean();
            recentSendBean.setSendAddress(this.transferParam.getFromAddress());
            recentSendBean.setReceiverAddress(this.transferParam.getToAddress());
            recentSendBean.setTimestamp(Long.valueOf(System.currentTimeMillis()));
            RecentSendController.getInstance().insertOrReplace(recentSendBean);
        } catch (Throwable th) {
            SentryUtil.captureException(th);
        }
    }

    private String getTokenTypeStr() {
        if (this.tokenBean.getType() == 0) {
            return M.M_TRX;
        }
        if (this.tokenBean.getType() == 1) {
            return M.M_TRC10;
        }
        this.tokenBean.getType();
        return M.M_TRC20;
    }
}
