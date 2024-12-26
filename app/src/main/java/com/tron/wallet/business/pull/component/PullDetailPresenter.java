package com.tron.wallet.business.pull.component;

import android.content.Intent;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.pull.PullAction;
import com.tron.wallet.business.pull.PullConstants;
import com.tron.wallet.business.pull.PullParam;
import com.tron.wallet.business.pull.component.PullDetailContract;
import com.tron.wallet.business.pull.exception.NoFromAddressView;
import com.tron.wallet.business.pull.login.LoginParam;
import com.tron.wallet.business.pull.login.LoginView;
import com.tron.wallet.business.pull.messagesign.LocalContentView;
import com.tron.wallet.business.pull.sign.SignParam;
import com.tron.wallet.business.pull.transfer.TransferParam;
import com.tron.wallet.business.pull.transfer.TransferView;
import com.tron.wallet.business.pull.triggersmartcontract.TriggerSmartContractView;
import com.tron.wallet.business.walletmanager.selectwallet.controller.RecentlyWalletController;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.ToastUtil;
import com.tron.wallet.common.utils.transaction.TriggerUtils;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.util.Map;
import org.tron.common.utils.TransactionDataUtils;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
import org.tron.protos.contract.AssetIssueContractOuterClass;
import org.tron.protos.contract.BalanceContract;
import org.tron.protos.contract.SmartContractOuterClass;
import org.tron.walletserver.TriggerData;
import org.tron.walletserver.Wallet;
public class PullDetailPresenter extends PullDetailContract.Presenter {
    private SignTransactionParseResult signTransactionParseResult;
    private TransferView transferView;

    public enum SignTransactionParseResult {
        SignTransfer,
        SignTrigger,
        SignSystemContract,
        Other
    }

    @Override
    public SignTransactionParseResult getSignTransactionParseResult() {
        return this.signTransactionParseResult;
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
    }

    @Override
    protected void onStart() {
    }

    @Override
    public void setParam(PullAction pullAction, PullParam pullParam) {
        Wallet walletForAddress;
        if (pullAction != PullAction.ACTION_LOGIN && (walletForAddress = WalletUtils.getWalletForAddress(pullParam.getLoginAddress())) != null && !walletForAddress.getAddress().equals(WalletUtils.getSelectedWallet().getAddress())) {
            RecentlyWalletController.setRecentlyWallet(walletForAddress);
            WalletUtils.setSelectedWallet(walletForAddress.getWalletName());
            ToastUtil.getInstance().show(((PullDetailContract.View) this.mView).getIContext(), R.string.pull_change_wallet);
        }
        int i = fun1.$SwitchMap$com$tron$wallet$business$pull$PullAction[pullAction.ordinal()];
        if (i == 1) {
            ((PullDetailContract.View) this.mView).setDetailView(new LoginView(((PullDetailContract.View) this.mView).getIContext(), (LoginParam) pullParam));
        } else if (i != 2) {
            if (i != 3) {
                return;
            }
            setTransferParam(pullParam);
        } else if (this.signTransactionParseResult == SignTransactionParseResult.Other) {
            if (TextUtils.isEmpty(pullParam.getLoginAddress())) {
                return;
            }
            if (WalletUtils.getWalletForAddress(pullParam.getLoginAddress()) == null) {
                ((PullDetailContract.View) this.mView).setDetailView(new NoFromAddressView(((PullDetailContract.View) this.mView).getIContext(), pullParam));
                ((PullDetailContract.View) this.mView).showInvalidAccountView(pullParam.getLoginAddress());
                return;
            }
            ((PullDetailContract.View) this.mView).setDetailView(new LocalContentView(((PullDetailContract.View) this.mView).getIContext(), (SignParam) pullParam, pullAction));
        } else if (this.signTransactionParseResult == SignTransactionParseResult.SignTransfer) {
            setTransferParam(pullParam);
        } else if (this.signTransactionParseResult != SignTransactionParseResult.SignTrigger || TextUtils.isEmpty(pullParam.getLoginAddress())) {
        } else {
            if (WalletUtils.getWalletForAddress(pullParam.getLoginAddress()) == null) {
                ((PullDetailContract.View) this.mView).setDetailView(new NoFromAddressView(((PullDetailContract.View) this.mView).getIContext(), pullParam));
                ((PullDetailContract.View) this.mView).showInvalidAccountView(pullParam.getLoginAddress());
                return;
            }
            ((PullDetailContract.View) this.mView).setDetailView(new TriggerSmartContractView(((PullDetailContract.View) this.mView).getIContext(), (SignParam) pullParam));
        }
    }

    static class fun1 {
        static final int[] $SwitchMap$com$tron$wallet$business$pull$PullAction;

        static {
            int[] iArr = new int[PullAction.values().length];
            $SwitchMap$com$tron$wallet$business$pull$PullAction = iArr;
            try {
                iArr[PullAction.ACTION_LOGIN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$pull$PullAction[PullAction.ACTION_SIGN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$pull$PullAction[PullAction.ACTION_TRANSFER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void setTransferParam(PullParam pullParam) {
        if (pullParam instanceof TransferParam) {
            TransferParam transferParam = (TransferParam) pullParam;
            if (!TextUtils.isEmpty(transferParam.getLoginAddress()) && WalletUtils.getWalletForAddress(transferParam.getLoginAddress()) == null) {
                ((PullDetailContract.View) this.mView).setDetailView(new NoFromAddressView(((PullDetailContract.View) this.mView).getIContext(), pullParam));
                ((PullDetailContract.View) this.mView).showInvalidAccountView(transferParam.getLoginAddress());
                return;
            }
            this.transferView = new TransferView(((PullDetailContract.View) this.mView).getIContext(), transferParam);
            ((PullDetailContract.View) this.mView).setDetailView(this.transferView);
        }
    }

    @Override
    public PullParam parseSignParam(String str) {
        PullParam pullParam = (PullParam) new Gson().fromJson(str,  SignParam.class);
        SignParam signParam = (SignParam) pullParam;
        if (StringTronUtil.equals(signParam.getSignType(), PullConstants.SIGN_TRANSACTION)) {
            Protocol.Transaction packTransaction = WalletUtils.packTransaction(signParam.getData());
            SmartContractOuterClass.TriggerSmartContract triggerSmartContract = null;
            AssetIssueContractOuterClass.TransferAssetContract transferAssetContract = null;
            BalanceContract.TransferContract transferContract = null;
            if (packTransaction.getRawData().getContract(0).getType() == Protocol.Transaction.Contract.ContractType.TriggerSmartContract) {
                try {
                    triggerSmartContract = (SmartContractOuterClass.TriggerSmartContract) TransactionUtils.unpackContract(packTransaction.getRawData().getContract(0), SmartContractOuterClass.TriggerSmartContract.class);
                } catch (Exception e) {
                    LogUtils.e(e);
                }
                if (triggerSmartContract != null && TriggerUtils.deepLinkCompareMethod(triggerSmartContract, signParam.getMethod())) {
                    if (TriggerUtils.isDeepLinkTransfer(triggerSmartContract, signParam.getMethod())) {
                        this.signTransactionParseResult = SignTransactionParseResult.SignTransfer;
                        TriggerData parseDataByFun = TransactionDataUtils.getInstance().parseDataByFun(triggerSmartContract, signParam.getMethod());
                        if (parseDataByFun != null && parseDataByFun.getParameterMap() != null) {
                            if (TransactionDataUtils.transferMethod.equals(signParam.getMethod()) && parseDataByFun.getParameterMap().size() == 2) {
                                Map<String, String> parameterMap = parseDataByFun.getParameterMap();
                                signParam.setFrom(StringTronUtil.encode58Check(triggerSmartContract.getOwnerAddress().toByteArray()));
                                signParam.setLoginAddress(StringTronUtil.encode58Check(triggerSmartContract.getOwnerAddress().toByteArray()));
                                signParam.setAmount(parameterMap.get("1"));
                                signParam.setTo(parameterMap.get("0"));
                            } else if (TransactionDataUtils.transferFromMethod.equals(signParam.getMethod()) && parseDataByFun.getParameterMap().size() == 3) {
                                Map<String, String> parameterMap2 = parseDataByFun.getParameterMap();
                                signParam.setAmount(parameterMap2.get("2"));
                                signParam.setTo(parameterMap2.get("1"));
                                signParam.setFrom(parameterMap2.get("0"));
                            }
                        }
                        signParam.setContract(StringTronUtil.encode58Check(triggerSmartContract.getContractAddress().toByteArray()));
                        signParam.setMemo(new String(packTransaction.getRawData().getData().toByteArray()));
                    } else {
                        this.signTransactionParseResult = SignTransactionParseResult.SignTrigger;
                    }
                }
            } else if (packTransaction.getRawData().getContract(0).getType() == Protocol.Transaction.Contract.ContractType.TransferAssetContract) {
                this.signTransactionParseResult = SignTransactionParseResult.SignTransfer;
                try {
                    transferAssetContract = (AssetIssueContractOuterClass.TransferAssetContract) TransactionUtils.unpackContract(packTransaction.getRawData().getContract(0), AssetIssueContractOuterClass.TransferAssetContract.class);
                } catch (Exception e2) {
                    LogUtils.e(e2);
                }
                if (transferAssetContract != null) {
                    signParam.setFrom(StringTronUtil.encode58Check(transferAssetContract.getOwnerAddress().toByteArray()));
                    signParam.setLoginAddress(StringTronUtil.encode58Check(transferAssetContract.getOwnerAddress().toByteArray()));
                    signParam.setTo(StringTronUtil.encode58Check(transferAssetContract.getToAddress().toByteArray()));
                    signParam.setMemo(new String(packTransaction.getRawData().getData().toByteArray()));
                    signParam.setTokenId(new String(transferAssetContract.getAssetName().toByteArray()));
                    signParam.setAmount(String.valueOf(transferAssetContract.getAmount()));
                }
            } else if (packTransaction.getRawData().getContract(0).getType() == Protocol.Transaction.Contract.ContractType.TransferContract) {
                this.signTransactionParseResult = SignTransactionParseResult.SignTransfer;
                try {
                    transferContract = (BalanceContract.TransferContract) TransactionUtils.unpackContract(packTransaction.getRawData().getContract(0), BalanceContract.TransferContract.class);
                } catch (Exception e3) {
                    LogUtils.e(e3);
                }
                if (transferContract != null) {
                    signParam.setFrom(StringTronUtil.encode58Check(transferContract.getOwnerAddress().toByteArray()));
                    signParam.setLoginAddress(StringTronUtil.encode58Check(transferContract.getOwnerAddress().toByteArray()));
                    signParam.setTo(StringTronUtil.encode58Check(transferContract.getToAddress().toByteArray()));
                    signParam.setMemo(new String(packTransaction.getRawData().getData().toByteArray()));
                    signParam.setAmount(String.valueOf(transferContract.getAmount()));
                }
            } else {
                this.signTransactionParseResult = SignTransactionParseResult.SignSystemContract;
            }
        } else {
            this.signTransactionParseResult = SignTransactionParseResult.Other;
        }
        return pullParam;
    }
}
