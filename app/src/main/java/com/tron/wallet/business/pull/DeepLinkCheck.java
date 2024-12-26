package com.tron.wallet.business.pull;

import android.text.TextUtils;
import android.webkit.URLUtil;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.ThreadPoolManager;
import com.tron.wallet.business.pull.sign.SignParam;
import com.tron.wallet.business.pull.transfer.TransferParam;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.transaction.TriggerUtils;
import com.tron.wallet.db.wallet.WalletUtils;
import java.util.Map;
import org.tron.common.crypto.StructuredDataEncoder;
import org.tron.common.utils.TransactionDataUtils;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
import org.tron.protos.contract.AssetIssueContractOuterClass;
import org.tron.protos.contract.BalanceContract;
import org.tron.protos.contract.SmartContractOuterClass;
import org.tron.walletserver.TriggerData;
public class DeepLinkCheck {
    private static boolean isBlockWebsite(String str) {
        return false;
    }

    public static PullResultCode checkParams(PullParam pullParam) {
        PullResultCode pullResultCode = PullResultCode.SUCCESS;
        if (TextUtils.isEmpty(pullParam.getAction())) {
            return PullResultCode.FAIL_NO_ACTION;
        }
        if (TextUtils.isEmpty(pullParam.getActionId())) {
            return PullResultCode.FAIL_NO_ACTION_ID;
        }
        if (!isValidDAppUrl(pullParam.getUrl())) {
            return PullResultCode.FAIL_INVALID_URL;
        }
        if (!isValidDAppUrl(pullParam.getCallbackUrl())) {
            return PullResultCode.FAIL_INVALID_CALLBACK_URL;
        }
        if (!isValidVersion(pullParam.getVersion())) {
            return PullResultCode.FAIL_NOT_SUPPORT_VERSION;
        }
        if (!isValidChain(pullParam.getChainId())) {
            return PullResultCode.FAIL_INVALID_CHAIN_ID;
        }
        if (!isValidChain(pullParam.getChainId()) || IRequest.isRelease()) {
            return isBlockWebsite(pullParam.getUrl()) ? PullResultCode.FAIL_NOT_SUPPORT_DAPP : pullResultCode;
        }
        return PullResultCode.FAIL_NOT_SUPPORT_NET;
    }

    public static PullResultCode checkTransferParam(TransferParam transferParam) {
        if (!StringTronUtil.isAddressValid(transferParam.getFrom())) {
            return PullResultCode.FAIL_INVALID_FROM_ADDRESS;
        }
        if (!StringTronUtil.isAddressValid(transferParam.getTo())) {
            return PullResultCode.FAIL_INVALID_TO_ADDRESS;
        }
        if (transferParam.getFrom().equals(transferParam.getTo())) {
            return PullResultCode.FAIL_FROM_TO_CANNOT_BE_SAME;
        }
        if (!StringTronUtil.isAddressValid(transferParam.getLoginAddress())) {
            return PullResultCode.FAIL_INVALID_LOGIN_ADDRESS;
        }
        if (!transferParam.getFrom().equals(transferParam.getLoginAddress())) {
            return PullResultCode.FAIL_ADDRESS_MISMATCH;
        }
        if (StringTronUtil.isEmpty(transferParam.getAmount())) {
            return PullResultCode.FAIL_INVALID_AMOUNT;
        }
        if (transferParam.getAmount().contains(ThreadPoolManager.DOT)) {
            return PullResultCode.FAIL_INVALID_AMOUNT;
        }
        if (!StringTronUtil.isEmpty(transferParam.getContract()) && !StringTronUtil.isEmpty(transferParam.getTokenId())) {
            return PullResultCode.FAIL_INVALID_CONTRACT_TOKEN_TRANSFERED;
        }
        if (!StringTronUtil.isEmpty(transferParam.getContract())) {
            if (!StringTronUtil.isAddressValid(transferParam.getContract())) {
                return PullResultCode.FAIL_INVALID_CONTRACT_ADDRESS;
            }
        } else if (!StringTronUtil.isEmpty(transferParam.getTokenId())) {
            try {
                Double.parseDouble(transferParam.getTokenId());
            } catch (Exception unused) {
                return PullResultCode.FAIL_INVALID_TOKEN_ID;
            }
        }
        return PullResultCode.SUCCESS;
    }

    public static PullResultCode checkSignParam(SignParam signParam) {
        try {
        } catch (Throwable th) {
            SentryUtil.captureException(th);
            return PullResultCode.FAIL_UNKNOW_REASON;
        }
        if (!StringTronUtil.isAddressValid(signParam.getLoginAddress())) {
            return PullResultCode.FAIL_INVALID_LOGIN_ADDRESS;
        }
        if (StringTronUtil.equals(signParam.getSignType(), PullConstants.SIGN_TRANSACTION)) {
            Protocol.Transaction packTransaction = WalletUtils.packTransaction(signParam.getData());
            if (packTransaction != null && !"".equals(packTransaction.toString())) {
                if (packTransaction.getRawData().getContract(0).getType() == Protocol.Transaction.Contract.ContractType.TriggerSmartContract) {
                    try {
                        SmartContractOuterClass.TriggerSmartContract triggerSmartContract = (SmartContractOuterClass.TriggerSmartContract) TransactionUtils.unpackContract(packTransaction.getRawData().getContract(0), SmartContractOuterClass.TriggerSmartContract.class);
                        if (triggerSmartContract != null && TriggerUtils.deepLinkCompareMethod(triggerSmartContract, signParam.getMethod())) {
                            if (TriggerUtils.isDeepLinkTransfer(triggerSmartContract, signParam.getMethod())) {
                                TriggerData parseDataByFun = TransactionDataUtils.getInstance().parseDataByFun(triggerSmartContract, signParam.getMethod());
                                if (parseDataByFun != null && parseDataByFun.getParameterMap() != null) {
                                    if (TransactionDataUtils.transferMethod.equals(signParam.getMethod()) && parseDataByFun.getParameterMap().size() == 2) {
                                        Map<String, String> parameterMap = parseDataByFun.getParameterMap();
                                        signParam.setFrom(StringTronUtil.encode58Check(triggerSmartContract.getOwnerAddress().toByteArray()));
                                        signParam.setAmount(parameterMap.get("1"));
                                        signParam.setTo(parameterMap.get("0"));
                                    } else if (TransactionDataUtils.transferFromMethod.equals(signParam.getMethod()) && parseDataByFun.getParameterMap().size() == 3) {
                                        Map<String, String> parameterMap2 = parseDataByFun.getParameterMap();
                                        signParam.setAmount(parameterMap2.get("2"));
                                        signParam.setTo(parameterMap2.get("1"));
                                        signParam.setFrom(parameterMap2.get("0"));
                                    }
                                    signParam.setContract(StringTronUtil.encode58Check(triggerSmartContract.getContractAddress().toByteArray()));
                                    signParam.setMemo(new String(packTransaction.getRawData().getData().toByteArray()));
                                    return checkTransferParam(signParam);
                                }
                                return PullResultCode.FAIL_INVALID_TRANSACTION_DATA;
                            }
                            return PullResultCode.SUCCESS;
                        }
                        return PullResultCode.FAIL_INVALID_METHOD;
                    } catch (Exception e) {
                        LogUtils.e(e);
                        return PullResultCode.FAIL_INVALID_TRANSACTION_DATA;
                    }
                } else if (packTransaction.getRawData().getContract(0).getType() == Protocol.Transaction.Contract.ContractType.TransferAssetContract) {
                    try {
                        AssetIssueContractOuterClass.TransferAssetContract transferAssetContract = (AssetIssueContractOuterClass.TransferAssetContract) TransactionUtils.unpackContract(packTransaction.getRawData().getContract(0), AssetIssueContractOuterClass.TransferAssetContract.class);
                        if (transferAssetContract != null) {
                            signParam.setFrom(StringTronUtil.encode58Check(transferAssetContract.getOwnerAddress().toByteArray()));
                            signParam.setTo(StringTronUtil.encode58Check(transferAssetContract.getToAddress().toByteArray()));
                            signParam.setMemo(new String(packTransaction.getRawData().getData().toByteArray()));
                            signParam.setTokenId(new String(transferAssetContract.getAssetName().toByteArray()));
                            signParam.setAmount(String.valueOf(transferAssetContract.getAmount()));
                            return checkTransferParam(signParam);
                        }
                    } catch (Exception e2) {
                        LogUtils.e(e2);
                    }
                    return PullResultCode.FAIL_INVALID_TRANSACTION_DATA;
                } else if (packTransaction.getRawData().getContract(0).getType() == Protocol.Transaction.Contract.ContractType.TransferContract) {
                    try {
                        BalanceContract.TransferContract transferContract = (BalanceContract.TransferContract) TransactionUtils.unpackContract(packTransaction.getRawData().getContract(0), BalanceContract.TransferContract.class);
                        if (transferContract != null) {
                            signParam.setFrom(StringTronUtil.encode58Check(transferContract.getOwnerAddress().toByteArray()));
                            signParam.setTo(StringTronUtil.encode58Check(transferContract.getToAddress().toByteArray()));
                            signParam.setMemo(new String(packTransaction.getRawData().getData().toByteArray()));
                            signParam.setAmount(String.valueOf(transferContract.getAmount()));
                            return checkTransferParam(signParam);
                        }
                    } catch (Exception e3) {
                        LogUtils.e(e3);
                    }
                    return PullResultCode.FAIL_INVALID_TRANSACTION_DATA;
                } else {
                    return PullResultCode.FAIL_NOT_SUPPORT_SYSTEM_CONTRACT;
                }
                SentryUtil.captureException(th);
                return PullResultCode.FAIL_UNKNOW_REASON;
            }
            return PullResultCode.FAIL_INVALID_TRANSACTION_DATA;
        } else if (StringTronUtil.equals(signParam.getSignType(), PullConstants.SIGN_STR)) {
            if (StringTronUtil.isEmpty(signParam.getMessage())) {
                return PullResultCode.FAIL_INVALID_MESSAGE;
            }
            return PullResultCode.SUCCESS;
        } else if (StringTronUtil.equals(signParam.getSignType(), PullConstants.SIGN_TYPED_DATA)) {
            if (checkTypedData(signParam.getMessage())) {
                return PullResultCode.SUCCESS;
            }
            return PullResultCode.FAIL_INVALID_MESSAGE;
        } else {
            return PullResultCode.FAIL_NOT_SUPPORT_ACTION;
        }
    }

    private static boolean checkTypedData(String str) {
        if (StringTronUtil.isEmpty(str)) {
            return false;
        }
        try {
            byte[] hashStructuredData = new StructuredDataEncoder(str).hashStructuredData();
            if (hashStructuredData != null) {
                if (hashStructuredData.length != 0) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            LogUtils.e(e);
            return false;
        }
    }

    private static boolean isValidChain(String str) {
        if (str != null) {
            return str.equals("0x2b6653dc") || str.equals("2b6653dc");
        }
        return false;
    }

    public static boolean isValidVersion(String str) {
        if (str != null) {
            return str.equals("v1.0") || str.equals("1.0");
        }
        return false;
    }

    public static boolean isValidDAppUrl(String str) {
        return URLUtil.isHttpUrl(str) || URLUtil.isHttpsUrl(str);
    }
}
