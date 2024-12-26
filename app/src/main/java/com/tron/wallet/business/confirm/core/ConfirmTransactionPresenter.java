package com.tron.wallet.business.confirm.core;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.google.protobuf.InvalidProtocolBufferException;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.TronApplication;
import com.tron.wallet.business.confirm.core.ConfirmTransactionContract;
import com.tron.wallet.business.confirm.core.pending.State;
import com.tron.wallet.business.confirm.fg.bean.CancelApproveParam;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.DappParam;
import com.tron.wallet.business.confirm.parambuilder.bean.ManagePermissionGroupParam;
import com.tron.wallet.business.confirm.parambuilder.bean.ParticipateMultisignParam;
import com.tron.wallet.business.confirm.parambuilder.bean.StakeAndVoteParam;
import com.tron.wallet.business.confirm.parambuilder.bean.SwapParam;
import com.tron.wallet.business.confirm.parambuilder.bean.TransferParam;
import com.tron.wallet.business.confirm.sign.SignTransactionNewActivity;
import com.tron.wallet.business.message.TransactionMessage;
import com.tron.wallet.business.pull.IntentResult;
import com.tron.wallet.business.tabdapp.jsbridge.finance.beans.StakeAndVoteOutput;
import com.tron.wallet.business.tabmy.dealsign.DealSignActivity;
import com.tron.wallet.business.tabswap.bean.SwapConfirmBean;
import com.tron.wallet.business.transfer.TokenDetailActivity;
import com.tron.wallet.common.bean.ChainBean;
import com.tron.wallet.common.bean.DealSignOutput;
import com.tron.wallet.common.bean.QrBean;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.bean.token.TransactionBean2;
import com.tron.wallet.common.bean.token.TransactionResult;
import com.tron.wallet.common.components.dialog.CustomDialog;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.task.AccountUpdater;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.DataStatHelper;
import com.tron.wallet.common.utils.FailUtils;
import com.tron.wallet.common.utils.PasswordInputUtils;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.db.Controller.JustSwapTransactionController;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.bean.JustSwapBean;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.disposables.Disposable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.bouncycastle.util.encoders.Hex;
import org.tron.api.GrpcAPI;
import org.tron.common.crypto.ECKey;
import org.tron.common.crypto.Hash;
import org.tron.common.utils.ByteArray;
import org.tron.common.utils.JsonFormat;
import org.tron.common.utils.TransactionUtils;
import org.tron.net.CipherException;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class ConfirmTransactionPresenter extends ConfirmTransactionContract.Presenter {
    private BaseParam baseParam;
    private Protocol.Transaction mTransactionUnsigned;
    private Wallet mWallet;
    private String ownerAddress;
    private StakeAndVoteOutput stakeAndVoteOutput;
    private int type;
    ArrayList<Protocol.Transaction> transactionUnSignList = new ArrayList<>();
    private int success = 0;
    private int fail = 0;
    private boolean isDestroy = false;
    private boolean resultToNewPage = false;
    private boolean broadcastTimeOut = false;

    @Override
    public Wallet getCurrentWallet() {
        return this.mWallet;
    }

    @Override
    public void onStart() {
        TokenBean tokenBean;
        BaseParam baseParam;
        this.stakeAndVoteOutput = new StakeAndVoteOutput();
        BaseParam baseParam2 = ((ConfirmTransactionContract.View) this.mView).getBaseParam();
        this.baseParam = baseParam2;
        if (baseParam2 == null) {
            ((ConfirmTransactionContract.View) this.mView).ToastError(R.string.error);
            ((ConfirmTransactionContract.View) this.mView).exit();
        } else if (!typeEquals(baseParam2.getType(), 14, 17, 101, 16, 102, 104, 103, 98) && ((baseParam = this.baseParam) == null || baseParam.getTransactionBean() == null || this.baseParam.getTransactionBean().getBytes() == null || this.baseParam.getTransactionBean().getBytes().size() == 0)) {
            ((ConfirmTransactionContract.View) this.mView).ToastError(R.string.error);
            ((ConfirmTransactionContract.View) this.mView).exit();
        } else {
            BaseParam baseParam3 = this.baseParam;
            if (baseParam3 instanceof ParticipateMultisignParam) {
                this.mWallet = WalletUtils.getWallet(((ParticipateMultisignParam) baseParam3).getWalletName());
            } else if (baseParam3 instanceof ManagePermissionGroupParam) {
                this.mWallet = WalletUtils.getWallet(((ManagePermissionGroupParam) baseParam3).getWalletName());
            } else if (baseParam3 != null && baseParam3.getQrBean() != null && !StringTronUtil.isNullOrEmpty(this.baseParam.getQrBean().getAddress())) {
                this.mWallet = WalletUtils.getWalletForAddress(this.baseParam.getQrBean().getAddress());
            } else {
                this.mWallet = WalletUtils.getSelectedWallet();
            }
            if (this.mWallet == null) {
                ((ConfirmTransactionContract.View) this.mView).ToastError(R.string.no_wallet_selected);
                ((ConfirmTransactionContract.View) this.mView).exit();
                return;
            }
            List<byte[]> bytes = this.baseParam.getTransactionBean().getBytes();
            ArrayList arrayList = new ArrayList();
            this.transactionUnSignList.clear();
            for (int i = 0; i < bytes.size(); i++) {
                byte[] bArr = bytes.get(i);
                if (this.mWallet.isWatchOnly()) {
                    this.baseParam.isActives();
                }
                arrayList.add(Hex.toHexString(bArr));
                try {
                    this.transactionUnSignList.add(Protocol.Transaction.parseFrom(bArr));
                } catch (InvalidProtocolBufferException e) {
                    LogUtils.e(e);
                }
            }
            if (bytes != null) {
                try {
                    if (bytes.size() > 0) {
                        Protocol.Transaction parseFrom = Protocol.Transaction.parseFrom(bytes.get(0));
                        this.mTransactionUnsigned = parseFrom;
                        if (parseFrom.getRawData().getContract(0).getType() == Protocol.Transaction.Contract.ContractType.AccountPermissionUpdateContract) {
                            this.ownerAddress = TransactionUtils.getOwner(this.mTransactionUnsigned);
                        }
                    }
                } catch (InvalidProtocolBufferException e2) {
                    LogUtils.e(e2);
                    SentryUtil.captureException(e2);
                }
            }
            int createType = this.mWallet.getCreateType();
            if (this.mWallet.isWatchOnly() && createType != 7 && createType != 8) {
                if (!SpAPI.THIS.getCurIsMainChain()) {
                    ((ConfirmTransactionContract.View) this.mView).ToastAsBottom(R.string.no_support);
                    ((ConfirmTransactionContract.View) this.mView).exit();
                    return;
                }
                ((ConfirmTransactionContract.View) this.mView).setRootV(false);
                QrBean qrBean = new QrBean();
                qrBean.setHexList(arrayList);
                if (((ConfirmTransactionContract.View) this.mView).isActives()) {
                    if (this.baseParam instanceof TransferParam) {
                        if (this.mWallet.getAddress().equals(this.ownerAddress)) {
                            qrBean.setType(7);
                        } else {
                            qrBean.setType(6);
                        }
                    }
                } else {
                    qrBean.setType(this.baseParam.getType());
                }
                qrBean.setAddress(this.mWallet.getAddress());
                BaseParam baseParam4 = this.baseParam;
                if (baseParam4 instanceof TransferParam) {
                    TransferParam transferParam = (TransferParam) baseParam4;
                    tokenBean = transferParam.getTokenBean();
                    if (this.mWallet.isShieldedWallet()) {
                        qrBean.setAlpha(ByteArray.toHexString(transferParam.getAlpha()));
                        qrBean.setTokenId(transferParam.getTokenId());
                    }
                } else {
                    tokenBean = null;
                }
                SignTransactionNewActivity.start((BaseActivity) ((ConfirmTransactionContract.View) this.mView).getIContext(), qrBean, tokenBean, TronConfig.OB_W);
            }
            int type = this.baseParam.getType();
            this.type = type;
            if ((type != 1 && type != 4 && type != 2 && type != 21 && type != 3 && type != 22 && type != 34 && type != 35 && type != 36 && type != 9 && type != 37) || this.baseParam.getPageFrom() == BaseParam.PageFrom.DeepLink || this.baseParam.getPageFrom() == BaseParam.PageFrom.Financial) {
                return;
            }
            this.resultToNewPage = true;
        }
    }

    private byte[] changeTime(byte[] bArr) {
        try {
            Protocol.Transaction parseFrom = Protocol.Transaction.parseFrom(bArr);
            Protocol.Transaction.Builder builder = parseFrom.toBuilder();
            Protocol.Transaction.raw.Builder builder2 = parseFrom.getRawData().toBuilder();
            builder2.setContract(0, parseFrom.getRawData().getContract(0).toBuilder().build());
            builder2.setExpiration(System.currentTimeMillis() + 300000);
            builder.setRawData(builder2.build());
            return builder.build().toByteArray();
        } catch (InvalidProtocolBufferException e) {
            LogUtils.e(e);
            return bArr;
        }
    }

    @Override
    public void send() {
        if (this.mWallet == null) {
            return;
        }
        final String str = ((ConfirmTransactionContract.View) this.mView).getpassword();
        if (StringTronUtil.isEmpty(str)) {
            ((ConfirmTransactionContract.View) this.mView).ToastError(R.string.et_null);
            return;
        }
        FirebaseAnalytics.getInstance(((ConfirmTransactionContract.View) this.mView).getIContext()).logEvent("Confirm_Password", null);
        if (PasswordInputUtils.canPwdInput(((ConfirmTransactionContract.View) this.mView).getIContext(), this.mWallet.getWalletName(), TronConfig.currentPwdType)) {
            if (!StringTronUtil.isEmpty(str)) {
                if (this.type != 5) {
                    ((ConfirmTransactionContract.View) this.mView).showLoading(StringTronUtil.getResString(R.string.sending));
                }
                final ChainBean currentChain = SpAPI.THIS.getCurrentChain();
                ((ConfirmTransactionContract.View) this.mView).setButtonEnable(false);
                ((ConfirmTransactionContract.View) this.mView).runOnThreeThread(new OnBackground() {
                    @Override
                    public final void doOnBackground() {
                        lambda$send$4(str, currentChain);
                    }
                });
                return;
            }
            PasswordInputUtils.updatePwdInput(((ConfirmTransactionContract.View) this.mView).getIContext(), this.mWallet.getWalletName(), TronConfig.currentPwdType);
            ((ConfirmTransactionContract.View) this.mView).showErrorText();
            return;
        }
        try {
            ((ConfirmTransactionContract.View) this.mView).dismissLoading();
            ((ConfirmTransactionContract.View) this.mView).showErrorText();
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void lambda$send$4(String str, ChainBean chainBean) {
        final String str2;
        try {
            this.mWallet = WalletUtils.getWallet(this.mWallet.getWalletName(), str);
            if (this.baseParam.getType() == 14) {
                ((ConfirmTransactionContract.View) this.mView).runOnUIThread(new OnMainThread() {
                    @Override
                    public void doInUIThread() {
                        finish();
                    }
                });
                return;
            }
            final ArrayList arrayList = new ArrayList();
            final ArrayList arrayList2 = new ArrayList();
            int i = 0;
            while (true) {
                boolean z = true;
                if (i >= this.transactionUnSignList.size()) {
                    break;
                }
                Protocol.Transaction transaction = this.transactionUnSignList.get(i);
                boolean isShieldedWallet = this.mWallet.isShieldedWallet();
                byte[] bArr = null;
                Protocol.Transaction sign = null;
                if (transaction.getRawData().getContract(0).getType() != Protocol.Transaction.Contract.ContractType.ShieldedTransferContract || !isShieldedWallet) {
                    Protocol.Transaction timestamp = TransactionUtils.setTimestamp(transaction);
                    ECKey eCKey = this.mWallet.getECKey();
                    if (chainBean != null) {
                        bArr = ByteArray.fromHexString(chainBean.chainId);
                    }
                    if (chainBean != null && !chainBean.isMainChain) {
                        z = false;
                    }
                    sign = TransactionUtils.sign(timestamp, eCKey, bArr, z);
                }
                if (sign != null) {
                    arrayList.add(sign);
                }
                i++;
            }
            str2 = "";
            if (typeEquals(this.baseParam.getType(), 16, 102, 98, 101, 103, 104, 17) && !StringTronUtil.isEmpty(this.baseParam.getMessage())) {
                BaseParam baseParam = this.baseParam;
                if (!DappParam.Type_712.equals(baseParam instanceof DappParam ? ((DappParam) baseParam).getSignStrType() : "") && !typeEquals(this.baseParam.getType(), 101, 17)) {
                    if (typeEquals(this.baseParam.getType(), 102)) {
                        str2 = TransactionUtils.signMessageV2(ByteArray.fromString(this.baseParam.getMessage()), this.mWallet.getECKey());
                    } else if (typeEquals(this.baseParam.getType(), 104)) {
                        str2 = TransactionUtils.signMessageV2((byte[]) new Gson().fromJson("[" + this.baseParam.getMessage() + "]",  byte[].class), this.mWallet.getECKey());
                    } else if (typeEquals(this.baseParam.getType(), 103)) {
                        str2 = TransactionUtils.signMessageV2(ByteArray.fromHexString(this.baseParam.getMessage()), this.mWallet.getECKey());
                    } else {
                        str2 = TransactionUtils.sign(this.baseParam.getMessage(), this.mWallet.getECKey());
                    }
                }
                str2 = TransactionUtils.signStructuredData(this.baseParam.getMessage(), this.mWallet.getECKey());
            }
            PasswordInputUtils.updateSuccessPwd(((ConfirmTransactionContract.View) this.mView).getIContext(), this.mWallet.getWalletName(), TronConfig.currentPwdType);
            ((ConfirmTransactionContract.View) this.mView).runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$send$0(str2, arrayList, arrayList2);
                }
            });
        } catch (IOException e) {
            PasswordInputUtils.updatePwdInput(((ConfirmTransactionContract.View) this.mView).getIContext(), this.mWallet.getWalletName(), TronConfig.currentPwdType, new PasswordInputUtils.InputOutListener() {
                @Override
                public void onOutTipsListener(String str3) {
                    ((ConfirmTransactionContract.View) mView).showErrorText(str3);
                }
            });
            LogUtils.e(e);
            ((ConfirmTransactionContract.View) this.mView).runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$send$2();
                }
            });
        } catch (CipherException e2) {
            PasswordInputUtils.updatePwdInput(((ConfirmTransactionContract.View) this.mView).getIContext(), this.mWallet.getWalletName(), TronConfig.currentPwdType, new PasswordInputUtils.InputOutListener() {
                @Override
                public void onOutTipsListener(String str3) {
                    ((ConfirmTransactionContract.View) mView).showErrorText(str3);
                }
            });
            LogUtils.e(e2);
            ((ConfirmTransactionContract.View) this.mView).showErrorText();
            ((ConfirmTransactionContract.View) this.mView).runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$send$1();
                }
            });
        } catch (Exception e3) {
            LogUtils.e(e3);
            ((ConfirmTransactionContract.View) this.mView).runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$send$3();
                }
            });
        } catch (Throwable th) {
            LogUtils.e(th);
            SentryUtil.captureException(th);
        }
    }

    public void lambda$send$0(String str, ArrayList arrayList, ArrayList arrayList2) {
        UIUtils.hideSoftKeyBoard((BaseActivity) ((ConfirmTransactionContract.View) this.mView).getIContext());
        if (SpAPI.THIS.isCold()) {
            QrBean qrBean = this.baseParam.getQrBean();
            List<String> hexList = qrBean.getHexList();
            hexList.clear();
            if (typeEquals(this.baseParam.getType(), 98, 102, 104, 103, 101) && !StringTronUtil.isEmpty(this.baseParam.getMessage())) {
                hexList.add(str);
            } else {
                for (int i = 0; i < arrayList.size(); i++) {
                    hexList.add(Hex.toHexString(((Protocol.Transaction) arrayList.get(i)).toByteArray()));
                }
                if ((arrayList == null || arrayList.size() == 0) && arrayList2 != null) {
                    for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                        hexList.add((String) arrayList2.get(i2));
                    }
                    qrBean.setcN(1);
                    qrBean.settN(1);
                }
            }
            qrBean.setHexList(hexList);
            SignTransactionNewActivity.start((BaseActivity) ((ConfirmTransactionContract.View) this.mView).getIContext(), qrBean, null, TronConfig.COLD_W);
            ((ConfirmTransactionContract.View) this.mView).exit();
            ((ConfirmTransactionContract.View) this.mView).dismissLoading();
        } else if (this.baseParam.getType() != 14) {
            if (typeEquals(this.baseParam.getType(), 16, 102, 103, 104, 17)) {
                if (this.baseParam.getPageFrom() == BaseParam.PageFrom.DeepLink) {
                    backToDeepLink(IntentResult.Succeeded, str);
                    return;
                } else {
                    returnSignMessageToDapp(str);
                    return;
                }
            }
            broadcastTransaction(0, arrayList, this.type);
        }
    }

    public void lambda$send$1() {
        ((ConfirmTransactionContract.View) this.mView).dismissLoading();
        ((ConfirmTransactionContract.View) this.mView).setButtonEnable(true);
    }

    public void lambda$send$2() {
        ((ConfirmTransactionContract.View) this.mView).dismissLoading();
        ((ConfirmTransactionContract.View) this.mView).setButtonEnable(true);
    }

    public void lambda$send$3() {
        ((ConfirmTransactionContract.View) this.mView).dismissLoading();
        ((ConfirmTransactionContract.View) this.mView).setButtonEnable(true);
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        if (intent != null && i2 == 2019) {
            if (intent.getBooleanExtra("cancle", false)) {
                ((ConfirmTransactionContract.View) this.mView).exit();
                return;
            }
            try {
                QrBean qrBean = (QrBean) new Gson().fromJson(intent.getStringExtra(TronConfig.QR_CODE_DATA),  QrBean.class);
                List<String> hexList = qrBean.getHexList();
                ArrayList arrayList = new ArrayList();
                if (hexList != null && hexList.size() > 0) {
                    for (String str : hexList) {
                        arrayList.add(Protocol.Transaction.parseFrom(Hex.decode(str)));
                    }
                }
                int type = qrBean.getType();
                if (type == 6 || type == 7) {
                    ((ConfirmTransactionContract.View) this.mView).showLoading(StringTronUtil.getResString(R.string.sending));
                    if (arrayList.size() > 0) {
                        msSend(0, arrayList, qrBean.getType());
                        return;
                    }
                    return;
                }
                ((ConfirmTransactionContract.View) this.mView).showLoading(StringTronUtil.getResString(R.string.sending));
                String transactionSignatureOwner = TransactionUtils.getTransactionSignatureOwner((Protocol.Transaction) arrayList.get(0));
                Wallet wallet = this.mWallet;
                if (wallet != null && wallet.isWatchOnly() && !StringTronUtil.isEmpty(this.mWallet.getAddress()) && this.mWallet.getAddress().equals(transactionSignatureOwner)) {
                    Map<String, String> realWatchWalletsMap = SpAPI.THIS.getRealWatchWalletsMap();
                    realWatchWalletsMap.put(this.mWallet.getAddress(), "1");
                    SpAPI.THIS.setRealWatchWalletsMap(realWatchWalletsMap);
                }
                broadcastTransaction(0, arrayList, qrBean.getType());
                return;
            } catch (Exception e) {
                LogUtils.e(e);
                ((ConfirmTransactionContract.View) this.mView).ToastError(StringTronUtil.getResString(R.string.parsererror));
                return;
            }
        }
        ((ConfirmTransactionContract.View) this.mView).exit();
    }

    public void broadcastTransaction(final int i, final List<Protocol.Transaction> list, final int i2) {
        if (i >= list.size()) {
            SentryUtil.captureMessage("IndexOutOfBounds occurs at broadcastTransaction");
            FailUtils.showFailDialog((Activity) ((ConfirmTransactionContract.View) this.mView).getIContext(), null, i2);
        } else if (((ConfirmTransactionContract.View) this.mView).isActives()) {
            msSend(i, list, i2);
        } else {
            if (this.resultToNewPage) {
                ((ConfirmTransactionContract.View) this.mView).setRootV(true);
                ((ConfirmTransactionContract.View) this.mView).dismissLoadingDialog();
                ((ConfirmTransactionContract.View) this.mView).showLoadingFragment();
            }
            ((ConfirmTransactionContract.View) this.mView).runOnThreeThread(new OnBackground() {
                @Override
                public final void doOnBackground() {
                    lambda$broadcastTransaction$7(list, i, i2);
                }
            });
        }
    }

    public void lambda$broadcastTransaction$7(final java.util.List r12, final int r13, final int r14) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.confirm.core.ConfirmTransactionPresenter.lambda$broadcastTransaction$7(java.util.List, int, int):void");
    }

    public void lambda$broadcastTransaction$6(boolean z, Protocol.Transaction transaction, int i, final List list, GrpcAPI.Return r19, final int i2, Protocol.Transaction.Contract.ContractType contractType) {
        ((ConfirmTransactionContract.View) this.mView).setButtonEnable(true);
        AccountUpdater.singleShot(0L);
        if (z) {
            this.success++;
            AnalyticsHelper.TransactionReporting.TransactionReportingApp(transaction, false);
            DataStatHelper.transactionReportingApp(transaction, false);
        } else {
            this.fail++;
        }
        if (backToFinancial(i, list.size(), z, getTxID(transaction), r19)) {
            return;
        }
        if (this.success == list.size()) {
            try {
                BaseParam baseParam = this.baseParam;
                if (baseParam instanceof SwapParam) {
                    SwapParam swapParam = (SwapParam) baseParam;
                    try {
                        String txID = getTxID(transaction);
                        storeSwapTranscation(txID, WalletUtils.getSelectedPublicWallet().getAddress(), swapParam.getSwapConfirmBean());
                        this.mRxManager.post(Event.BROADCAST_TRANSACTION, txID);
                    } catch (Exception e) {
                        LogUtils.e(e);
                    }
                }
                BaseParam baseParam2 = this.baseParam;
                if (baseParam2 instanceof CancelApproveParam) {
                    try {
                        this.mRxManager.post(Event.CANCEL_APPROVE_TRANSACTION, (CancelApproveParam) baseParam2);
                    } catch (Exception e2) {
                        LogUtils.e(e2);
                    }
                }
                BaseParam baseParam3 = this.baseParam;
                if (baseParam3 instanceof TransferParam) {
                    TransferParam transferParam = (TransferParam) baseParam3;
                    Bundle bundle = new Bundle();
                    bundle.putString(NotificationCompat.CATEGORY_EVENT, transferParam.tokenType);
                    bundle.putString("value", transferParam.amount);
                    FirebaseAnalytics.getInstance(((ConfirmTransactionContract.View) this.mView).getIContext()).logEvent("Signature_Success", bundle);
                }
            } catch (Exception e3) {
                SentryUtil.captureException(e3);
            }
            if (i2 != 5) {
                if (this.resultToNewPage) {
                    ((ConfirmTransactionContract.View) this.mView).updateLoadingFragment(r19, State.Success, 0);
                    return;
                }
                ((ConfirmTransactionContract.View) this.mView).ToastSuc(R.string.transaction_submit);
            }
            if (i2 == 5) {
                BaseParam baseParam4 = this.baseParam;
                if (baseParam4 instanceof ManagePermissionGroupParam) {
                    ManagePermissionGroupParam managePermissionGroupParam = (ManagePermissionGroupParam) baseParam4;
                    this.mRxManager.post(Event.UPDATESUCCESS, new Pair(Integer.valueOf(managePermissionGroupParam.getModifyIndex()), managePermissionGroupParam.getWalletName()));
                    this.mRxManager.post(Event.BroadcastSuccess, "");
                }
            } else if (i2 == 11) {
                this.mRxManager.post(Event.BroadcastSuccess, "MAKEPROPOSAL_TYPE");
            } else if (i2 == 12) {
                this.mRxManager.post(Event.BroadcastSuccess, "APPROVALSPROPOSAL_TYPE");
            } else {
                this.mRxManager.post(Event.BroadcastSuccess, "");
            }
            finish();
        } else if (this.fail == list.size()) {
            if (this.resultToNewPage) {
                ((ConfirmTransactionContract.View) this.mView).updateLoadingFragment(r19, this.broadcastTimeOut ? State.TimeOut : State.Fail, this.fail);
                return;
            }
            showErrorDialog(r19);
        } else if (this.success + this.fail != list.size()) {
            final int i3 = i + 1;
            final String txID2 = getTxID(transaction);
            if (contractType == Protocol.Transaction.Contract.ContractType.TriggerSmartContract) {
                ((ConfirmTransactionContract.View) this.mView).runOnThreeThread(new OnBackground() {
                    @Override
                    public final void doOnBackground() {
                        lambda$broadcastTransaction$5(txID2, i3, list, i2);
                    }
                });
            } else {
                broadcastTransaction(i3, list, i2);
            }
        } else if (this.resultToNewPage) {
            ((ConfirmTransactionContract.View) this.mView).updateLoadingFragment(r19, this.broadcastTimeOut ? State.TimeOut : State.Fail, this.fail);
            return;
        } else {
            ((ConfirmTransactionContract.View) this.mView).dismissLoading();
            ((ConfirmTransactionContract.View) this.mView).setButtonEnable(true);
            toDialog(this.fail);
        }
        AccountUpdater.singleShot(0L);
    }

    public void lambda$broadcastTransaction$5(String str, int i, List list, int i2) {
        postDelayedBroadcast(str, i, list, i2, System.currentTimeMillis());
    }

    private boolean backToFinancial(int i, int i2, boolean z, String str, GrpcAPI.Return r8) {
        if (this.baseParam.getPageFrom() == BaseParam.PageFrom.Financial) {
            if (i == 0) {
                if (z) {
                    this.stakeAndVoteOutput.setStakeHash(str);
                    this.stakeAndVoteOutput.setSuccessfullyFrozen(true);
                } else {
                    this.stakeAndVoteOutput.setSuccessfullyFrozen(false);
                    if (r8 != null && r8.getMessage() != null) {
                        this.stakeAndVoteOutput.setErrorMessage(new String(r8.getMessage().toByteArray()));
                    }
                }
            } else if (i == 1) {
                if (z) {
                    this.stakeAndVoteOutput.setSuccessfullyVoted(true);
                } else {
                    this.stakeAndVoteOutput.setSuccessfullyVoted(false);
                }
            }
            if (this.success + this.fail == i2) {
                BaseParam baseParam = this.baseParam;
                if (baseParam instanceof StakeAndVoteParam) {
                    this.stakeAndVoteOutput.setNumberForVotes(((StakeAndVoteParam) baseParam).getVoteBean().getTotalVotes());
                }
                this.mRxManager.post(Event.EVENT_STAKE_AND_VOTE_DONE, this.stakeAndVoteOutput);
                finish();
                return true;
            }
        }
        return false;
    }

    private void showErrorDialog(GrpcAPI.Return r4) {
        this.mRxManager.post(Event.BroadcastFail, "");
        ((ConfirmTransactionContract.View) this.mView).dismissLoading();
        ((ConfirmTransactionContract.View) this.mView).setButtonEnable(true);
        FailUtils.showFailDialog((Activity) ((ConfirmTransactionContract.View) this.mView).getIContext(), r4);
    }

    public void finish() {
        try {
            Intent intent = new Intent();
            intent.putExtra("Cancle", "Cancle");
            intent.putExtra("PASSWORD", ((ConfirmTransactionContract.View) this.mView).getpassword());
            ((Activity) ((ConfirmTransactionContract.View) this.mView).getIContext()).setResult(-1, intent);
            ((ConfirmTransactionContract.View) this.mView).exit();
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void backToDeepLink(String str, String str2, String str3) {
        try {
            Intent intent = new Intent();
            intent.putExtra(IntentResult.ResultKey, str);
            intent.putExtra(IntentResult.ErrorMessageKey, str3);
            intent.putExtra(IntentResult.HashKey, str2);
            ((Activity) ((ConfirmTransactionContract.View) this.mView).getIContext()).setResult(-1, intent);
            ((ConfirmTransactionContract.View) this.mView).exit();
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void backToDeepLink(String str, String str2) {
        try {
            Intent intent = new Intent();
            intent.putExtra(IntentResult.ResultKey, str);
            intent.putExtra(IntentResult.SignedMessage, str2);
            ((Activity) ((ConfirmTransactionContract.View) this.mView).getIContext()).setResult(-1, intent);
            ((ConfirmTransactionContract.View) this.mView).exit();
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    private void storeSwapTranscation(String str, String str2, SwapConfirmBean swapConfirmBean) {
        JustSwapBean justSwapBean = new JustSwapBean();
        justSwapBean.setHash(str);
        justSwapBean.setAddress(str2);
        justSwapBean.setAmountIn(swapConfirmBean.getAmountLeft());
        justSwapBean.setAmountout(swapConfirmBean.getAmountRight());
        justSwapBean.setFee(swapConfirmBean.getFee());
        justSwapBean.setContractInAddress(swapConfirmBean.getTokenFrom().getToken());
        justSwapBean.setContractOutAddress(swapConfirmBean.getTokenTo().getToken());
        justSwapBean.setDecimalsIn(String.valueOf(swapConfirmBean.getTokenFrom().getDecimal()));
        justSwapBean.setDecimalsOut(String.valueOf(swapConfirmBean.getTokenTo().getDecimal()));
        justSwapBean.setRate(swapConfirmBean.getRate());
        justSwapBean.setExchargeAddress(swapConfirmBean.getTokenFrom().getExchange());
        justSwapBean.setMethod(swapConfirmBean.getTriggerMethod());
        justSwapBean.setTimestamp(System.currentTimeMillis());
        justSwapBean.setSymbolIn(swapConfirmBean.getTokenFrom().getSymbol());
        justSwapBean.setSymbolOut(swapConfirmBean.getTokenTo().getSymbol());
        justSwapBean.setTokenAddressIn(swapConfirmBean.getTokenFrom().getToken());
        justSwapBean.setTokenAddressOut(swapConfirmBean.getTokenTo().getToken());
        justSwapBean.setPriceImpact(swapConfirmBean.getPriceImpact());
        justSwapBean.setMinReceived(swapConfirmBean.getMinReceived());
        justSwapBean.setBlockNum(0L);
        try {
            JustSwapTransactionController.getInstance().insert(justSwapBean);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void msSend(final int i, final List<Protocol.Transaction> list, final int i2) {
        Protocol.Transaction transaction = list.get(i);
        ((ConfirmTransactionContract.View) this.mView).setButtonEnable(false);
        TransactionBean2 transactionBean2 = new TransactionBean2();
        transactionBean2.setAddress(this.mWallet.getAddress());
        transactionBean2.setFunctionSelector(TronApplication.FUNCTIONSELECTOR_MAP.get(TransactionUtils.getTriggerHash(transaction)));
        transactionBean2.setTransaction((TransactionBean2.TransactionBean) new Gson().fromJson(JsonFormat.printToString(transaction),  TransactionBean2.TransactionBean.class));
        if (SpAPI.THIS.isShasta()) {
            transactionBean2.setNetType("shasta");
        } else {
            transactionBean2.setNetType("main_net");
        }
        ((ConfirmTransactionContract.Model) this.mModel).transaction(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(transactionBean2))).subscribe(new IObserver(new ICallback<TransactionResult>() {
            @Override
            public void onResponse(String str, TransactionResult transactionResult) {
                if (i < list.size() - 1 && list.size() != 1) {
                    msSend(i + 1, list, i2);
                    return;
                }
                ((ConfirmTransactionContract.View) mView).dismissLoading();
                ((ConfirmTransactionContract.View) mView).setButtonEnable(true);
                if (transactionResult.getCode() != 0) {
                    if (i2 == 13) {
                        FailUtils.showMsendErrorDialog(transactionResult.getCode(), ((ConfirmTransactionContract.View) mView).getIContext());
                        return;
                    }
                    ((ConfirmTransactionContract.View) mView).ToastError(R.string.sign_fail);
                    ((ConfirmTransactionContract.View) mView).exit();
                    return;
                }
                ((ConfirmTransactionContract.View) mView).ToastSuc(R.string.sign_success2);
                if (i2 == 7) {
                    if (ownerAddress != null && mWallet.getAddress().equals(ownerAddress)) {
                        if (baseParam instanceof TransferParam) {
                            TokenBean tokenBean = ((TransferParam) baseParam).tokenBean;
                            TokenDetailActivity.start(((ConfirmTransactionContract.View) mView).getIContext(), tokenBean);
                            mRxManager.post(Event.TRANSFER_INNER, "");
                            mRxManager.post(Event.BroadcastSuccess, tokenBean);
                        }
                    } else {
                        mRxManager.post(Event.TRANSFER_INNER, "1");
                    }
                    try {
                        TransferParam transferParam = (TransferParam) baseParam;
                        Bundle bundle = new Bundle();
                        bundle.putString(NotificationCompat.CATEGORY_EVENT, transferParam.tokenType);
                        bundle.putString("value", transferParam.amount);
                        FirebaseAnalytics.getInstance(((ConfirmTransactionContract.View) mView).getIContext()).logEvent("Multisignature_Success", bundle);
                    } catch (Exception unused) {
                    }
                } else {
                    mRxManager.post(Event.VoteClearPrevious, "");
                    mRxManager.post(Event.BroadcastSuccess, "");
                }
                if (baseParam != null && mWallet != null && (baseParam instanceof ParticipateMultisignParam)) {
                    try {
                        int threshold = ((ParticipateMultisignParam) baseParam).getThreshold();
                        List<DealSignOutput.DataBeanX.DataBean.SignatureProgressBean> progress = ((ParticipateMultisignParam) baseParam).getProgress();
                        int i3 = 0;
                        for (int i4 = 0; i4 < progress.size(); i4++) {
                            DealSignOutput.DataBeanX.DataBean.SignatureProgressBean signatureProgressBean = progress.get(i4);
                            if (signatureProgressBean.isSign == 1) {
                                i3 += signatureProgressBean.weight;
                            }
                            if (signatureProgressBean.address.equals(mWallet.getAddress())) {
                                i3 += signatureProgressBean.weight;
                            }
                        }
                        if (i3 >= threshold) {
                            DealSignActivity.start(((ConfirmTransactionContract.View) mView).getIContext(), mWallet.getWalletName(), false, 2);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    ((ConfirmTransactionContract.View) mView).exit();
                                }
                            }, 800L);
                            return;
                        }
                    } catch (Exception e) {
                        LogUtils.e(e);
                    }
                }
                if (baseParam != null && baseParam.isOverThreshold()) {
                    DealSignActivity.start(((ConfirmTransactionContract.View) mView).getIContext(), mWallet.getWalletName(), false, 2);
                } else {
                    DealSignActivity.start(((ConfirmTransactionContract.View) mView).getIContext(), mWallet.getWalletName(), false, 1);
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((ConfirmTransactionContract.View) mView).exit();
                    }
                }, 800L);
            }

            @Override
            public void onFailure(String str, String str2) {
                ((ConfirmTransactionContract.View) mView).dismissLoading();
                ((ConfirmTransactionContract.View) mView).ToastError(R.string.sign_fail);
                ((ConfirmTransactionContract.View) mView).setButtonEnable(true);
                mRxManager.post(Event.BroadcastFail, "");
                ((ConfirmTransactionContract.View) mView).exit();
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "getReleaseData"));
    }

    private void toDialog(int i) {
        CustomDialog.Builder builder = new CustomDialog.Builder(((ConfirmTransactionContract.View) this.mView).getIContext());
        final CustomDialog build = builder.style(R.style.loading_dialog).cancelTouchout(true).heightDimenRes(-2).widthDimenRes(-1).setGravity(17).view(R.layout.fail_dialog).build();
        View view = builder.getView();
        TextView textView = (TextView) view.findViewById(R.id.tv_ok2);
        ((LinearLayout) view.findViewById(R.id.ll_double)).setVisibility(View.GONE);
        textView.setVisibility(View.VISIBLE);
        ((TextView) view.findViewById(R.id.tv_content)).setText(String.format(((ConfirmTransactionContract.View) this.mView).getIContext().getString(R.string.unfreeze_fail2), Integer.valueOf(i)));
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                build.dismiss();
                mRxManager.post(Event.BroadcastFail, "");
                ((ConfirmTransactionContract.View) mView).exit();
            }
        });
        build.show();
        build.setCanceledOnTouchOutside(false);
    }

    private String getTxID(Protocol.Transaction transaction) {
        return Hex.toHexString(Hash.sha256(transaction.getRawData().toByteArray()));
    }

    private boolean typeEquals(int i, int... iArr) {
        for (int i2 : iArr) {
            if (i == i2) {
                return true;
            }
        }
        return false;
    }

    public void returnSignMessageToDapp(String str) {
        try {
            Intent intent = new Intent();
            intent.putExtra("SIGNSTRING", str);
            ((Activity) ((ConfirmTransactionContract.View) this.mView).getIContext()).setResult(-1, intent);
            ((ConfirmTransactionContract.View) this.mView).exit();
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void postDelayedBroadcast(String str, int i, List<Protocol.Transaction> list, int i2, long j) {
        if (this.isDestroy) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            LogUtils.e(e);
        }
        final Protocol.TransactionInfo transactionInfoById = TronAPI.getTransactionInfoById(str);
        if (transactionInfoById == null || transactionInfoById.toString().length() <= 0) {
            if (currentTimeMillis - j > 60000) {
                this.mRxManager.post(Event.BroadcastFail, "");
                ((ConfirmTransactionContract.View) this.mView).dismissLoading();
                ((ConfirmTransactionContract.View) this.mView).runOnUIThread(new OnMainThread() {
                    @Override
                    public final void doInUIThread() {
                        lambda$postDelayedBroadcast$10(transactionInfoById);
                    }
                });
                return;
            }
            postDelayedBroadcast(str, i, list, i2, j);
        } else if (transactionInfoById.getResult() == null || transactionInfoById.getResult() == Protocol.TransactionInfo.code.UNRECOGNIZED) {
            postDelayedBroadcast(str, i, list, i2, j);
        } else if (transactionInfoById.getResult() == Protocol.TransactionInfo.code.SUCESS) {
            if (transactionInfoById.getReceipt() != null && transactionInfoById.getReceipt().getResult() != null) {
                final String name = transactionInfoById.getReceipt().getResult().name();
                if (TransactionMessage.TYPE_SUCCESS.equals(name)) {
                    broadcastTransaction(i, list, i2);
                    return;
                } else if ("DEFAULT".equals(name)) {
                    postDelayedBroadcast(str, i, list, i2, j);
                    return;
                } else {
                    this.mRxManager.post(Event.BroadcastFail, "");
                    ((ConfirmTransactionContract.View) this.mView).dismissLoading();
                    ((ConfirmTransactionContract.View) this.mView).runOnUIThread(new OnMainThread() {
                        @Override
                        public final void doInUIThread() {
                            lambda$postDelayedBroadcast$8(name);
                        }
                    });
                    return;
                }
            }
            postDelayedBroadcast(str, i, list, i2, j);
        } else if (transactionInfoById.getResult() == Protocol.TransactionInfo.code.FAILED) {
            this.mRxManager.post(Event.BroadcastFail, "");
            ((ConfirmTransactionContract.View) this.mView).dismissLoading();
            ((ConfirmTransactionContract.View) this.mView).runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$postDelayedBroadcast$9(transactionInfoById);
                }
            });
        } else {
            postDelayedBroadcast(str, i, list, i2, j);
        }
    }

    public void lambda$postDelayedBroadcast$8(String str) {
        ((ConfirmTransactionContract.View) this.mView).toast(str);
        ((ConfirmTransactionContract.View) this.mView).exit();
    }

    public void lambda$postDelayedBroadcast$9(Protocol.TransactionInfo transactionInfo) {
        if (transactionInfo.getResMessage() != null) {
            ((ConfirmTransactionContract.View) this.mView).toast(new String(transactionInfo.getResMessage().toByteArray()));
        }
        ((ConfirmTransactionContract.View) this.mView).exit();
    }

    public void lambda$postDelayedBroadcast$10(Protocol.TransactionInfo transactionInfo) {
        if (transactionInfo != null && transactionInfo.toString().length() > 0 && transactionInfo.getResMessage() != null) {
            ((ConfirmTransactionContract.View) this.mView).toast(new String(transactionInfo.getResMessage().toByteArray()));
        } else {
            ((ConfirmTransactionContract.View) this.mView).toast(StringTronUtil.getResString(R.string.transaction_broadcast_fail));
        }
        ((ConfirmTransactionContract.View) this.mView).exit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.isDestroy = true;
    }
}
