package com.tron.wallet.business.tabmy.dealsign;

import android.text.TextUtils;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.fg.component.Utils;
import com.tron.wallet.business.confirm.parambuilder.BaseConfirmTransParamBuilder;
import com.tron.wallet.business.confirm.parambuilder.bean.FreezeUnFreezeParam;
import com.tron.wallet.business.confirm.parambuilder.bean.TransferParam;
import com.tron.wallet.business.confirm.parambuilder.utils.ParamBuildUtils;
import com.tron.wallet.common.bean.ConfirmExtraText;
import com.tron.wallet.common.bean.DealSignOutput;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.config.M;
import com.tron.wallet.common.utils.AddressNameUtils;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.DataStatHelper;
import com.tron.wallet.common.utils.ProposalUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TronPowerUtils;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.tron.api.GrpcAPI;
import org.tron.common.utils.JsonFormat;
import org.tron.common.utils.TransactionDataUtils;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
import org.tron.protos.contract.ProposalContract;
import org.tron.protos.contract.SmartContractOuterClass;
import org.tron.walletserver.ConnectErrorException;
import org.tron.walletserver.TriggerData;
import org.tron.walletserver.Wallet;
public class DealSignParamUtils {
    public static com.tron.wallet.business.confirm.parambuilder.BaseConfirmTransParamBuilder getTransferParamBuilder(com.tron.wallet.common.bean.DealSignOutput.DataBeanX.DataBean r12, java.lang.String r13, org.tron.protos.Protocol.Transaction r14) throws java.lang.Exception {
        


return null;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.tabmy.dealsign.DealSignParamUtils.getTransferParamBuilder(com.tron.wallet.common.bean.DealSignOutput$DataBeanX$DataBean, java.lang.String, org.tron.protos.Protocol$Transaction):com.tron.wallet.business.confirm.parambuilder.BaseConfirmTransParamBuilder");
    }

    public static BaseConfirmTransParamBuilder getTransferParamBuilder(DealSignOutput.DataBeanX.DataBean dataBean, String str, String str2, boolean z, Protocol.Transaction transaction, long j) throws Exception {
        BaseConfirmTransParamBuilder baseConfirmTransParamBuilder = new BaseConfirmTransParamBuilder();
        TransferParam transferParam = new TransferParam();
        transferParam.setType(1);
        transferParam.addTransaction(transaction);
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        numberInstance.setMaximumFractionDigits(7);
        transferParam.fromAddress = str2;
        if (dataBean != null && dataBean.trc20Info != null && TextUtils.equals(dataBean.contractType, "TriggerSmartContract")) {
            SmartContractOuterClass.TriggerSmartContract triggerSmartContract = (SmartContractOuterClass.TriggerSmartContract) TransactionUtils.unpackContract(transaction.getRawData().getContract(0), SmartContractOuterClass.TriggerSmartContract.class);
            transferParam.fromAddress = StringTronUtil.encode58Check(triggerSmartContract.getOwnerAddress().toByteArray());
            if (triggerSmartContract != null) {
                try {
                    TriggerData transferData = TransactionDataUtils.getInstance().getTransferData(triggerSmartContract);
                    if (transferData != null && !transferData.getParameterMap().isEmpty()) {
                        transferParam.toAddress = transferData.getParameterMap().get("0");
                    }
                } catch (JsonFormat.ParseException e) {
                    LogUtils.e(e);
                }
            }
            transferParam.amount = numberInstance.format(BigDecimalUtils.div_(Long.valueOf(triggerSmartContract.getCallValue()), Double.valueOf(Math.pow(10.0d, dataBean.trc20Info.decimals))));
            transferParam.tokenType = M.M_TRC20;
            TokenBean tokenBean = new TokenBean();
            tokenBean.setType(2);
            if (dataBean.trc20Info != null) {
                tokenBean.setShortName(dataBean.trc20Info.name);
                tokenBean.setPrecision(dataBean.trc20Info.decimals);
            }
            transferParam.tokenBean = tokenBean;
        }
        transferParam.tokenBean = null;
        transferParam.tokenType = M.M_TRC20;
        transferParam.setEnergyUsed(j);
        transferParam.setAlpha(null);
        transferParam.setShield(false);
        transferParam.setTitle(R.string.confirmtransaction, R.string.et_input_password);
        transferParam.setPageIndex(1);
        transferParam.setHasOwnerPermission(false);
        transferParam.setIsFromDealSign(1);
        transferParam.setAccountActive(z);
        transferParam.setOverThreshold(isOverThreshold(dataBean, str));
        baseConfirmTransParamBuilder.setParam(transferParam);
        return baseConfirmTransParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getMakeProposalParamBuilder(DealSignOutput.DataBeanX.DataBean dataBean, String str, boolean z, Protocol.Transaction transaction) throws Exception {
        BaseConfirmTransParamBuilder approvalProposalTransactionParamBuilder;
        Map<Long, Long> parametersMap = ((ProposalContract.ProposalCreateContract) TransactionUtils.unpackContract(transaction.getRawData().getContract(0), ProposalContract.ProposalCreateContract.class)).getParametersMap();
        HashMap hashMap = new HashMap();
        for (Map.Entry<Long, Long> entry : parametersMap.entrySet()) {
            hashMap.put(Long.valueOf(entry.getKey().longValue()), ProposalUtils.getProposalContent(entry.getKey().longValue(), entry.getValue().longValue()));
        }
        if (TextUtils.equals(dataBean.contractType, "ProposalCreateContract")) {
            approvalProposalTransactionParamBuilder = ParamBuildUtils.getMakeProposalTransactionParamBuilder(true, hashMap, transaction);
        } else {
            approvalProposalTransactionParamBuilder = ParamBuildUtils.getApprovalProposalTransactionParamBuilder(true, z ? R.mipmap.ic_confirm_approve_proposal : R.mipmap.ic_confirm_cancel_approval, z ? R.string.confirm_approve_proposal2 : R.string.cancel_approve_proposal2, transaction, dataBean.contractData.proposal_id, hashMap);
        }
        approvalProposalTransactionParamBuilder.param.setActives(true);
        approvalProposalTransactionParamBuilder.param.setIsFromDealSign(1);
        approvalProposalTransactionParamBuilder.param.setOverThreshold(isOverThreshold(dataBean, str));
        return approvalProposalTransactionParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getDeleteProposalParamBuilder(DealSignOutput.DataBeanX.DataBean dataBean, String str, Protocol.Transaction transaction) throws Exception {
        BaseConfirmTransParamBuilder approvalProposalTransactionParamBuilder = ParamBuildUtils.getApprovalProposalTransactionParamBuilder(true, R.mipmap.ic_confirm_delete_proposal, R.string.revoke_proposal2, transaction, dataBean.contractData.proposal_id, new HashMap());
        approvalProposalTransactionParamBuilder.param.setActives(true);
        approvalProposalTransactionParamBuilder.param.setIsFromDealSign(1);
        approvalProposalTransactionParamBuilder.param.setOverThreshold(isOverThreshold(dataBean, str));
        return approvalProposalTransactionParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getFreezeResourceParamBuilder(DealSignOutput.DataBeanX.DataBean dataBean, String str, boolean z, Protocol.Transaction transaction) throws ConnectErrorException {
        BigDecimal expectGetPower;
        NumberFormat.getInstance(Locale.US).setMaximumFractionDigits(7);
        BigDecimal div_ = BigDecimalUtils.div_(Long.valueOf(dataBean.contractData.frozen_balance), Double.valueOf(1000000.0d));
        String str2 = StringTronUtil.formatNumber6Truncate(div_) + " TRX";
        String str3 = dataBean.contractData.owner_address;
        Wallet walletForAddress = WalletUtils.getWalletForAddress(str3);
        String str4 = dataBean.contractData.receiver_address;
        if (str4 == null) {
            str4 = str3;
        }
        String nameByAddress = Utils.getNameByAddress(str4);
        String format = !TextUtils.isEmpty(nameByAddress) ? String.format("%s\n(%s)", nameByAddress, str4) : str4;
        byte[] decode58Check = StringTronUtil.decode58Check(str3);
        Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
        boolean equals = selectedPublicWallet != null ? TextUtils.equals(selectedPublicWallet.getAddress(), str4) : true;
        GrpcAPI.AccountResourceMessage accountRes = walletForAddress != null ? WalletUtils.getAccountRes(AppContextUtil.getContext(), walletForAddress.getWalletName()) : null;
        if (accountRes == null) {
            accountRes = TronAPI.getAccountRes(decode58Check);
        }
        if (z) {
            expectGetPower = TronPowerUtils.expectGetPower(accountRes.getTotalNetWeight(), accountRes.getTotalNetLimit(), div_);
        } else {
            expectGetPower = TronPowerUtils.expectGetPower(accountRes.getTotalEnergyWeight(), accountRes.getTotalEnergyLimit(), div_);
        }
        String formatNumber6Truncate = StringTronUtil.formatNumber6Truncate(expectGetPower);
        Protocol.Account account = walletForAddress != null ? WalletUtils.getAccount(AppContextUtil.getContext(), walletForAddress.getWalletName()) : null;
        if (account == null) {
            account = TronAPI.queryAccount(decode58Check, false);
        }
        BaseConfirmTransParamBuilder freezeTransactionParamBuilder = ParamBuildUtils.getFreezeTransactionParamBuilder(z, true, equals, account == null ? 0L : account.getBalance(), div_.doubleValue(), formatNumber6Truncate, format, str2, DataStatHelper.StatAction.Stake, transaction);
        freezeTransactionParamBuilder.param.setActives(true);
        freezeTransactionParamBuilder.param.setIsFromDealSign(1);
        freezeTransactionParamBuilder.param.setOverThreshold(isOverThreshold(dataBean, str));
        return freezeTransactionParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getUnfreezeResourceParamBuilder(DealSignOutput.DataBeanX.DataBean dataBean, String str, boolean z, String str2, List<Protocol.Transaction> list) {
        FreezeUnFreezeParam freezeUnFreezeParam = new FreezeUnFreezeParam();
        freezeUnFreezeParam.setDoFreezeType(z ? 1 : 0);
        if (list.size() > 0) {
            for (Protocol.Transaction transaction : list) {
                freezeUnFreezeParam.addTransaction(transaction);
            }
        }
        freezeUnFreezeParam.setPageIndex(1);
        freezeUnFreezeParam.setType(2);
        freezeUnFreezeParam.setHasOwnerPermission(true);
        freezeUnFreezeParam.setTitle(R.string.confirmtransaction, 0, R.string.et_input_password);
        freezeUnFreezeParam.addIconResource(R.mipmap.ic_confirm_unstake);
        freezeUnFreezeParam.addInfoTitle(R.string.tron_power_unlock, "");
        String nameByAddress = AddressNameUtils.getInstance().getNameByAddress(str2);
        if (!TextUtils.isEmpty(nameByAddress)) {
            str2 = String.format("%s\n(%s)", nameByAddress, str2);
        }
        freezeUnFreezeParam.addExtraText(R.string.resource_unfreeze_account, str2);
        freezeUnFreezeParam.setActives(true);
        ConfirmExtraText confirmExtraText = new ConfirmExtraText();
        confirmExtraText.setLeft(StringTronUtil.getResString(R.string.contract_type));
        confirmExtraText.setRight(StringTronUtil.getResString(R.string.multisign_transaction));
        freezeUnFreezeParam.getTextLists().add(0, confirmExtraText);
        BaseConfirmTransParamBuilder baseConfirmTransParamBuilder = new BaseConfirmTransParamBuilder();
        baseConfirmTransParamBuilder.setParam(freezeUnFreezeParam);
        baseConfirmTransParamBuilder.param.setIsFromDealSign(1);
        baseConfirmTransParamBuilder.param.setOverThreshold(isOverThreshold(dataBean, str));
        return baseConfirmTransParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getProfitParamBuilder(DealSignOutput.DataBeanX.DataBean dataBean, String str, Protocol.Transaction transaction, String str2) {
        BaseConfirmTransParamBuilder profitParamBuilder = ParamBuildUtils.getProfitParamBuilder(true, str2, -1.0d, transaction);
        profitParamBuilder.param.setIsFromDealSign(1);
        profitParamBuilder.param.setActives(true);
        profitParamBuilder.param.setOverThreshold(isOverThreshold(dataBean, str));
        return profitParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getAccountPermissionUpdateParamBuilder(DealSignOutput.DataBeanX.DataBean dataBean, String str, Protocol.Transaction transaction) {
        BaseConfirmTransParamBuilder permissionUpdateTransactionParamBuilder = ParamBuildUtils.getPermissionUpdateTransactionParamBuilder(true, str, transaction, 0, -1);
        permissionUpdateTransactionParamBuilder.param.setActives(true);
        permissionUpdateTransactionParamBuilder.param.setIsFromDealSign(1);
        permissionUpdateTransactionParamBuilder.param.setOverThreshold(isOverThreshold(dataBean, str));
        return permissionUpdateTransactionParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getWitnessVote(DealSignOutput.DataBeanX.DataBean dataBean, String str, Protocol.Transaction transaction) {
        Wallet wallet = WalletUtils.getWallet(str);
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        if (wallet != null) {
            str = wallet.getAddress();
        }
        BaseConfirmTransParamBuilder voteTransactionParamBuilder = ParamBuildUtils.getVoteTransactionParamBuilder(hashMap, hashMap2, true, 1, transaction, 3, str, 0L, "", DataStatHelper.StatAction.Vote);
        voteTransactionParamBuilder.param.setActives(true);
        voteTransactionParamBuilder.param.setIsFromDealSign(1);
        voteTransactionParamBuilder.param.setOverThreshold(isOverThreshold(wallet != null ? wallet.getAddress() : "", dataBean));
        return voteTransactionParamBuilder;
    }

    public static boolean isOverThreshold(DealSignOutput.DataBeanX.DataBean dataBean, String str) {
        Wallet wallet = WalletUtils.getWallet(str);
        if (wallet == null) {
            return false;
        }
        return isOverThreshold(wallet.getAddress(), dataBean);
    }

    public static boolean isOverThreshold(String str, DealSignOutput.DataBeanX.DataBean dataBean) {
        if (str == null) {
            return false;
        }
        int i = 0;
        for (int i2 = 0; i2 < dataBean.signatureProgress.size(); i2++) {
            DealSignOutput.DataBeanX.DataBean.SignatureProgressBean signatureProgressBean = dataBean.signatureProgress.get(i2);
            if (signatureProgressBean.isSign == 1) {
                i += signatureProgressBean.weight;
            }
            if (signatureProgressBean.address.equals(str)) {
                i += signatureProgressBean.weight;
            }
        }
        return i >= dataBean.threshold;
    }
}
