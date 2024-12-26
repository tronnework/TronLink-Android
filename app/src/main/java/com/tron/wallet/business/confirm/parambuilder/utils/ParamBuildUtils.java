package com.tron.wallet.business.confirm.parambuilder.utils;

import android.text.TextUtils;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.fg.bean.CancelApproveParam;
import com.tron.wallet.business.confirm.fg.bean.SwapApproveParam;
import com.tron.wallet.business.confirm.fg.component.Utils;
import com.tron.wallet.business.confirm.parambuilder.BaseConfirmTransParamBuilder;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.CancelUnStakeParam;
import com.tron.wallet.business.confirm.parambuilder.bean.DappDetailParam;
import com.tron.wallet.business.confirm.parambuilder.bean.DappMetadataParam;
import com.tron.wallet.business.confirm.parambuilder.bean.DappParam;
import com.tron.wallet.business.confirm.parambuilder.bean.DelegatedResourceParam;
import com.tron.wallet.business.confirm.parambuilder.bean.DepositWithDrawParam;
import com.tron.wallet.business.confirm.parambuilder.bean.FreezeUnFreezeParam;
import com.tron.wallet.business.confirm.parambuilder.bean.ManagePermissionGroupParam;
import com.tron.wallet.business.confirm.parambuilder.bean.NFTParam;
import com.tron.wallet.business.confirm.parambuilder.bean.ParticipateMultisignParam;
import com.tron.wallet.business.confirm.parambuilder.bean.ProfitParam;
import com.tron.wallet.business.confirm.parambuilder.bean.StakeAndVoteParam;
import com.tron.wallet.business.confirm.parambuilder.bean.SwapParam;
import com.tron.wallet.business.confirm.parambuilder.bean.TransferParam;
import com.tron.wallet.business.confirm.parambuilder.bean.UnexpiredProfitParam;
import com.tron.wallet.business.confirm.parambuilder.bean.VoteParam;
import com.tron.wallet.business.confirm.parambuilder.bean.WithDrawDelegatedResourceParam;
import com.tron.wallet.business.tabdapp.bean.DappProjectInfoBean;
import com.tron.wallet.business.tabdapp.jsbridge.finance.beans.VoteBean;
import com.tron.wallet.business.tabswap.bean.SwapConfirmBean;
import com.tron.wallet.common.bean.ConfirmExtraText;
import com.tron.wallet.common.bean.DealSignOutput;
import com.tron.wallet.common.bean.QrBean;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.config.T;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.DataStatHelper;
import com.tron.wallet.common.utils.NumUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tronlinkpro.wallet.R;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
import org.tron.protos.contract.SmartContractOuterClass;
import org.tron.walletserver.TriggerData;
public class ParamBuildUtils {
    public static BaseConfirmTransParamBuilder getTransferTransactionParamBuilder(String str, String str2, boolean z, TokenBean tokenBean, boolean z2, boolean z3, Protocol.Transaction transaction, boolean z4, byte[] bArr) {
        return getTransferTransactionParamBuilder(str, str2, z, tokenBean, z2, z3, transaction, z4, bArr, 0L);
    }

    public static BaseConfirmTransParamBuilder getTransferTransactionParamBuilder(String str, String str2, boolean z, TokenBean tokenBean, boolean z2, boolean z3, Protocol.Transaction transaction, boolean z4, byte[] bArr, long j) {
        return getTransferTransactionParamBuilder(str, str2, z, tokenBean, z2, z3, transaction, z4, bArr, 0L, BaseParam.PageFrom.Local);
    }

    public static BaseConfirmTransParamBuilder getTransferTransactionParamBuilder(String str, String str2, boolean z, TokenBean tokenBean, boolean z2, boolean z3, Protocol.Transaction transaction, boolean z4, byte[] bArr, long j, BaseParam.PageFrom pageFrom) {
        BaseConfirmTransParamBuilder baseConfirmTransParamBuilder = new BaseConfirmTransParamBuilder();
        TransferParam transferParam = new TransferParam();
        transferParam.setType(1);
        transferParam.addTransaction(transaction);
        transferParam.fromAddress = str2;
        transferParam.tokenBean = tokenBean;
        transferParam.tokenType = str;
        transferParam.amount = T.amount;
        transferParam.toAddress = T.toAddress;
        transferParam.note = T.note;
        transferParam.setEnergyUsed(j);
        transferParam.setAlpha(bArr);
        if (tokenBean != null) {
            transferParam.setTokenId("" + tokenBean.getId());
        }
        transferParam.setPageFrom(pageFrom);
        transferParam.setShield(z4);
        transferParam.setTitle(R.string.confirmtransaction, R.string.et_input_password);
        transferParam.setPageIndex(1);
        transferParam.setHasOwnerPermission(z2);
        transferParam.setActives(z3);
        transferParam.setAccountActive(z);
        baseConfirmTransParamBuilder.setParam(transferParam);
        return baseConfirmTransParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getVoteTransactionParamBuilder(HashMap hashMap, HashMap hashMap2, boolean z, Protocol.Transaction transaction) {
        return getVoteTransactionParamBuilder2(hashMap, hashMap2, z, 1, transaction, 0, DataStatHelper.StatAction.Vote);
    }

    public static BaseConfirmTransParamBuilder getVoteTransactionParamBuilder(HashMap hashMap, HashMap hashMap2, boolean z, int i, Protocol.Transaction transaction, int i2, String str, long j, String str2, boolean z2, DataStatHelper.StatAction statAction, String str3) {
        VoteParam voteParam = new VoteParam();
        voteParam.setViewType(i2);
        voteParam.setNameWeightMap(hashMap);
        voteParam.setAddressWeightMap(hashMap2);
        voteParam.addTransaction(transaction);
        voteParam.addExtraText(R.string.xhdk, Long.valueOf(TransactionUtils.bandwidthCost(transaction)));
        voteParam.setTitle(R.string.confirmtransaction, 0, R.string.et_input_password);
        voteParam.setPageIndex(i);
        voteParam.setHasOwnerPermission(z);
        voteParam.setActives(!z);
        voteParam.setFromStakeSuccess(z2);
        voteParam.addIconResource(R.mipmap.ic_vote_title);
        voteParam.setFromAddress(str);
        voteParam.setSingleVoteCount(j);
        voteParam.setSingleVoteSrName(str2);
        voteParam.setStatAction(statAction);
        voteParam.setPageFrom(BaseParam.PageFrom.Local);
        if (!StringTronUtil.isNullOrEmpty(str3)) {
            voteParam.setApr(str3);
        }
        BaseConfirmTransParamBuilder baseConfirmTransParamBuilder = new BaseConfirmTransParamBuilder();
        baseConfirmTransParamBuilder.setParam(voteParam);
        return baseConfirmTransParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getVoteTransactionParamBuilder(HashMap hashMap, HashMap hashMap2, boolean z, int i, Protocol.Transaction transaction, int i2, String str, long j, String str2, DataStatHelper.StatAction statAction) {
        return getVoteTransactionParamBuilder(hashMap, hashMap2, z, i, transaction, i2, str, j, str2, false, statAction, null);
    }

    public static BaseConfirmTransParamBuilder getVoteTransactionParamBuilder2(HashMap hashMap, HashMap hashMap2, boolean z, int i, Protocol.Transaction transaction, int i2, DataStatHelper.StatAction statAction) {
        return getVoteTransactionParamBuilder(hashMap, hashMap2, z, i, transaction, i2, "", 0L, "", statAction);
    }

    public static BaseConfirmTransParamBuilder getFreezeTransactionParamBuilder(boolean z, boolean z2, boolean z3, double d, double d2, String str, String str2, String str3, DataStatHelper.StatAction statAction, Protocol.Transaction transaction) {
        FreezeUnFreezeParam freezeUnFreezeParam = new FreezeUnFreezeParam();
        freezeUnFreezeParam.setDoFreezeType(z ? 1 : 0);
        freezeUnFreezeParam.setMoney(str3);
        freezeUnFreezeParam.setType(4);
        freezeUnFreezeParam.addTransaction(transaction);
        freezeUnFreezeParam.setPageIndex(1);
        freezeUnFreezeParam.setPageFrom(BaseParam.PageFrom.Local);
        freezeUnFreezeParam.setStatAction(statAction);
        freezeUnFreezeParam.setCanUseTrxCount(d);
        freezeUnFreezeParam.setRealFreeze(Double.valueOf(d2));
        freezeUnFreezeParam.setResourceCount(str);
        if (BigDecimalUtils.MoreThan((Object) str.replaceAll(",", ""), (Object) 0)) {
            try {
                freezeUnFreezeParam.addExtraText(R.string.resource_gain, String.format("≈%s %s", StringTronUtil.formatNumber0(NumberFormat.getNumberInstance().parse(str).doubleValue()), StringTronUtil.getResString(z ? R.string.bandwidth : R.string.energy)));
            } catch (ParseException e) {
                LogUtils.e(e);
            }
        }
        freezeUnFreezeParam.addExtraText(R.string.address, str2);
        freezeUnFreezeParam.addExtraText(R.string.vote_count_gained, String.format("%s %s", StringTronUtil.formatNumber0(d2), StringTronUtil.getResString(R.string.unit_of_vote)));
        freezeUnFreezeParam.setHasOwnerPermission(z2);
        freezeUnFreezeParam.setActives(!z2);
        freezeUnFreezeParam.setTitle(R.string.confirm_freeze, 0, R.string.et_input_password);
        freezeUnFreezeParam.addIconResource(R.mipmap.ic_confirm_stake);
        Protocol.Transaction.Contract.ContractType contractType = Protocol.Transaction.Contract.ContractType.FreezeBalanceContract;
        if (transaction != null && !"".equals(transaction.toString()) && transaction.getRawData() != null && transaction.getRawData().getContractCount() > 0) {
            contractType = transaction.getRawData().getContract(0).getType();
        }
        if (contractType == Protocol.Transaction.Contract.ContractType.FreezeBalanceV2Contract) {
            freezeUnFreezeParam.addInfoTitle(R.string.stake_2, StringTronUtil.getResString(R.string.stake) + " " + str3);
            freezeUnFreezeParam.setStakeVersion(2);
        } else {
            freezeUnFreezeParam.addInfoTitle(R.string.freeze, String.format(StringTronUtil.getResString(z3 ? R.string.lock_power_for_self : R.string.lock_power_for_others), str3));
            freezeUnFreezeParam.setStakeVersion(1);
        }
        BaseConfirmTransParamBuilder baseConfirmTransParamBuilder = new BaseConfirmTransParamBuilder();
        baseConfirmTransParamBuilder.setParam(freezeUnFreezeParam);
        return baseConfirmTransParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getUnFreezeTransactionParamBuilder(int i, boolean z, FreezeUnFreezeParam.TYPE type, String[] strArr, String str, List<Protocol.Transaction> list, HashMap<String, String> hashMap, long j) {
        int i2;
        String resString;
        String format;
        FreezeUnFreezeParam freezeUnFreezeParam = new FreezeUnFreezeParam();
        freezeUnFreezeParam.setDoFreezeType(i);
        if (list.size() > 0) {
            for (Protocol.Transaction transaction : list) {
                freezeUnFreezeParam.addTransaction(transaction);
            }
        }
        freezeUnFreezeParam.setPageIndex(1);
        freezeUnFreezeParam.setType(2);
        freezeUnFreezeParam.setPageFrom(BaseParam.PageFrom.Local);
        freezeUnFreezeParam.setHasOwnerPermission(z);
        freezeUnFreezeParam.setActives(!z);
        freezeUnFreezeParam.setTitle(R.string.confirmtransaction, 0, R.string.et_input_password);
        freezeUnFreezeParam.addIconResource(R.mipmap.ic_confirm_unstake);
        if (freezeUnFreezeParam.isActives()) {
            ConfirmExtraText confirmExtraText = new ConfirmExtraText();
            confirmExtraText.setLeft(StringTronUtil.getResString(R.string.contract_type));
            confirmExtraText.setRight(StringTronUtil.getResString(R.string.multisign_transaction));
            freezeUnFreezeParam.getTextLists().add(0, confirmExtraText);
        }
        if (strArr.length == 1 && !TextUtils.isEmpty(strArr[0])) {
            freezeUnFreezeParam.addExtraText(R.string.release_resources, "≈" + strArr[0]);
            freezeUnFreezeParam.releasedResourceTypeCount = 1;
        } else if (strArr.length == 2) {
            if (TextUtils.isEmpty(strArr[0])) {
                i2 = 0;
            } else {
                freezeUnFreezeParam.addExtraText(R.string.unstake_confirm_release_energy, "≈" + strArr[0]);
                i2 = 1;
            }
            if (!TextUtils.isEmpty(strArr[1])) {
                freezeUnFreezeParam.addExtraText(R.string.unstake_confirm_release_bandwidth, "≈" + strArr[1]);
                i2++;
            }
            freezeUnFreezeParam.releasedResourceTypeCount = i2;
        }
        freezeUnFreezeParam.unfreezeResources = new ArrayList<>();
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            ConfirmExtraText confirmExtraText2 = new ConfirmExtraText();
            confirmExtraText2.setLeft(entry.getKey());
            confirmExtraText2.setRight(entry.getValue());
            freezeUnFreezeParam.unfreezeResources.add(confirmExtraText2);
        }
        if (list.get(0).getRawData().getContract(0).getType() == Protocol.Transaction.Contract.ContractType.UnfreezeBalanceV2Contract) {
            if (hashMap.size() == 1) {
                freezeUnFreezeParam.addExtraText(R.string.address, hashMap.keySet().iterator().next());
            } else if (hashMap.size() > 1) {
                freezeUnFreezeParam.addExtraText(R.string.resource_unfreeze_account, String.format(StringTronUtil.getResString(R.string.unfreeze_count_of_account), Integer.valueOf(hashMap.size())));
            }
            if (j > 0) {
                format = String.format(StringTronUtil.getResString(R.string.un_stake_v2_and_withdraw), str, Long.valueOf(j));
            } else {
                format = String.format(StringTronUtil.getResString(R.string.un_stake_v2_x_trx), str);
            }
            freezeUnFreezeParam.addInfoTitle(R.string.unstake_2, format);
            freezeUnFreezeParam.setStakeVersion(2);
        } else {
            if (hashMap.size() == 1) {
                freezeUnFreezeParam.addExtraText(R.string.resource_unfreeze_account, hashMap.keySet().iterator().next());
            } else if (hashMap.size() > 1) {
                freezeUnFreezeParam.addExtraText(R.string.resource_unfreeze_account, String.format(StringTronUtil.getResString(R.string.unfreeze_count_of_account), Integer.valueOf(hashMap.size())));
            }
            if (FreezeUnFreezeParam.TYPE.FOR_SELF.equals(type)) {
                resString = StringTronUtil.getResString(R.string.power_lockd_for_self);
            } else if (FreezeUnFreezeParam.TYPE.FOR_OTHER.equals(type)) {
                resString = StringTronUtil.getResString(R.string.power_lockd_for_others);
            } else {
                resString = FreezeUnFreezeParam.TYPE.BATCH.equals(type) ? StringTronUtil.getResString(R.string.power_unlock_batch) : "";
            }
            freezeUnFreezeParam.addInfoTitle(R.string.tron_power_unlock, String.format(resString, str));
            freezeUnFreezeParam.setStakeVersion(1);
        }
        freezeUnFreezeParam.addExtraText(R.string.reduced_vote_count, String.format("%s %s", str, StringTronUtil.getResString(R.string.unit_of_vote)));
        BaseConfirmTransParamBuilder baseConfirmTransParamBuilder = new BaseConfirmTransParamBuilder();
        baseConfirmTransParamBuilder.setParam(freezeUnFreezeParam);
        return baseConfirmTransParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getMakeProposalTransactionParamBuilder(boolean z, HashMap<Long, String> hashMap, Protocol.Transaction transaction) {
        BaseParam baseParam = new BaseParam();
        baseParam.addTransaction(transaction);
        baseParam.setPageIndex(1);
        baseParam.setTitle(R.string.confirmtransaction, 0, R.string.et_input_password);
        baseParam.addIconResource(R.mipmap.ic_confirm_make_proposal);
        baseParam.setType(11);
        baseParam.setHasOwnerPermission(z);
        baseParam.setActives(!z);
        baseParam.setPageFrom(BaseParam.PageFrom.Local);
        baseParam.addInfoTitle(R.string.create_proposal2, "");
        for (Map.Entry<Long, String> entry : hashMap.entrySet()) {
            baseParam.addExtraText(String.format("#%d", entry.getKey()), entry.getValue());
        }
        BaseConfirmTransParamBuilder baseConfirmTransParamBuilder = new BaseConfirmTransParamBuilder();
        baseConfirmTransParamBuilder.setParam(baseParam);
        return baseConfirmTransParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getApprovalProposalTransactionParamBuilder(boolean z, int i, int i2, Protocol.Transaction transaction, String str, HashMap<Long, String> hashMap) {
        BaseParam baseParam = new BaseParam();
        if (i > 0) {
            baseParam.addIconResource(i);
        }
        baseParam.addTransaction(transaction);
        baseParam.setPageIndex(1);
        baseParam.setTitle(R.string.confirmtransaction, 0, R.string.et_input_password);
        baseParam.setType(12);
        baseParam.setHasOwnerPermission(z);
        baseParam.setActives(!z);
        baseParam.setPageFrom(BaseParam.PageFrom.Local);
        for (Map.Entry<Long, String> entry : hashMap.entrySet()) {
            baseParam.addExtraText(String.format("#%d", entry.getKey()), entry.getValue());
        }
        baseParam.addInfoTitle(i2, String.format("#%s", str));
        BaseConfirmTransParamBuilder baseConfirmTransParamBuilder = new BaseConfirmTransParamBuilder();
        baseConfirmTransParamBuilder.setParam(baseParam);
        return baseConfirmTransParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getDepositTransactionParamBuilder(boolean z, String str, String str2, long j, int i, TokenBean tokenBean, Protocol.Transaction... transactionArr) {
        DepositWithDrawParam depositWithDrawParam = new DepositWithDrawParam();
        for (Protocol.Transaction transaction : transactionArr) {
            depositWithDrawParam.addTransaction(transaction);
        }
        depositWithDrawParam.setPageIndex(1);
        depositWithDrawParam.setHasOwnerPermission(z);
        depositWithDrawParam.setActives(!z);
        depositWithDrawParam.setTokenBean(tokenBean);
        depositWithDrawParam.setTokenType(str);
        depositWithDrawParam.setMoney(str2);
        depositWithDrawParam.setPageFrom(BaseParam.PageFrom.Local);
        depositWithDrawParam.setErrorHint(String.format(StringTronUtil.getResString(R.string.deposit_withdraw), StringTronUtil.getResString(R.string.deposit4)));
        depositWithDrawParam.setTitle(R.string.deposit_title1, 0, R.string.confirm_transaction_title1);
        depositWithDrawParam.setType(i);
        BaseConfirmTransParamBuilder baseConfirmTransParamBuilder = new BaseConfirmTransParamBuilder();
        baseConfirmTransParamBuilder.setParam(depositWithDrawParam);
        return baseConfirmTransParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getWithDrawTransactionParamBuilder(boolean z, String str, String str2, long j, int i, TokenBean tokenBean, Protocol.Transaction... transactionArr) {
        DepositWithDrawParam depositWithDrawParam = new DepositWithDrawParam();
        for (Protocol.Transaction transaction : transactionArr) {
            depositWithDrawParam.addTransaction(transaction);
        }
        depositWithDrawParam.setPageIndex(1);
        depositWithDrawParam.setHasOwnerPermission(z);
        depositWithDrawParam.setActives(!z);
        depositWithDrawParam.setTokenBean(tokenBean);
        depositWithDrawParam.setTokenType(str);
        depositWithDrawParam.setMoney(str2);
        depositWithDrawParam.setPageFrom(BaseParam.PageFrom.Local);
        depositWithDrawParam.setErrorHint(String.format(StringTronUtil.getResString(R.string.deposit_withdraw), StringTronUtil.getResString(R.string.withdraw3)));
        depositWithDrawParam.setTitle(R.string.withdraw_title1, 0, R.string.confirm_transaction_title1);
        depositWithDrawParam.setType(i);
        BaseConfirmTransParamBuilder baseConfirmTransParamBuilder = new BaseConfirmTransParamBuilder();
        baseConfirmTransParamBuilder.setParam(depositWithDrawParam);
        return baseConfirmTransParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getParticipateMultisignTransactionParamBuilder(String str, Protocol.Transaction transaction, ArrayList<DealSignOutput.DataBeanX.DataBean.SignatureProgressBean> arrayList, int i) {
        ParticipateMultisignParam participateMultisignParam = new ParticipateMultisignParam();
        participateMultisignParam.addTransaction(transaction);
        participateMultisignParam.setProgress(arrayList);
        participateMultisignParam.setThreshold(i);
        participateMultisignParam.setPageIndex(3);
        participateMultisignParam.setTitle(0, 0, R.string.et_input_password);
        participateMultisignParam.setType(13);
        participateMultisignParam.setHasOwnerPermission(true);
        participateMultisignParam.setActives(true);
        participateMultisignParam.setWalletName(str);
        participateMultisignParam.setPageFrom(BaseParam.PageFrom.Local);
        BaseConfirmTransParamBuilder baseConfirmTransParamBuilder = new BaseConfirmTransParamBuilder();
        baseConfirmTransParamBuilder.setParam(participateMultisignParam);
        return baseConfirmTransParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getPermissionUpdateTransactionParamBuilder(boolean z, String str, Protocol.Transaction transaction, int i, int i2) {
        ManagePermissionGroupParam managePermissionGroupParam = new ManagePermissionGroupParam();
        managePermissionGroupParam.addTransaction(transaction);
        managePermissionGroupParam.setTitle(R.string.confirmtransaction, 0, R.string.et_input_password);
        managePermissionGroupParam.setLayoutHeight(UIUtils.dip2px(450.0f));
        managePermissionGroupParam.addInfoTitle(R.string.account_update_permission, "");
        managePermissionGroupParam.addIconResource(R.mipmap.ic_confirm_update_permission);
        managePermissionGroupParam.setPageIndex(1);
        managePermissionGroupParam.setModifyIndex(i);
        managePermissionGroupParam.setHasOwnerPermission(z);
        managePermissionGroupParam.setActives(!z);
        managePermissionGroupParam.setPageFrom(BaseParam.PageFrom.Local);
        try {
            managePermissionGroupParam.setAddress(TransactionUtils.getOwner(transaction));
        } catch (Exception e) {
            LogUtils.e(e);
        }
        managePermissionGroupParam.setType(5);
        managePermissionGroupParam.setWalletName(str);
        if (i2 == 0) {
            managePermissionGroupParam.setErrorHint(R.string.owner_note, 0);
        }
        if (i2 == 1) {
            managePermissionGroupParam.setErrorHint(R.string.modify_witness_error_hint, 0);
        }
        BaseConfirmTransParamBuilder baseConfirmTransParamBuilder = new BaseConfirmTransParamBuilder();
        baseConfirmTransParamBuilder.setParam(managePermissionGroupParam);
        return baseConfirmTransParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getProfitParamBuilder(boolean z, String str, double d, Protocol.Transaction transaction) {
        ProfitParam profitParam = new ProfitParam();
        profitParam.addTransaction(transaction);
        profitParam.setLayoutHeight(UIUtils.dip2px(450.0f));
        profitParam.setPageIndex(1);
        profitParam.setTitle(R.string.confirmtransaction, 0, R.string.et_input_password);
        profitParam.setType(9);
        profitParam.setHasOwnerPermission(z);
        profitParam.setActives(!z);
        profitParam.setAmount(d);
        profitParam.setPageFrom(BaseParam.PageFrom.Local);
        profitParam.setToAddress(str);
        profitParam.addIconResource(R.mipmap.ic_confirm_withdraw_balance);
        String toAddress = profitParam.getToAddress();
        String nameByAddress = Utils.getNameByAddress(profitParam.getToAddress());
        if (!TextUtils.isEmpty(nameByAddress)) {
            toAddress = String.format("%s\n(%s)", nameByAddress, toAddress);
        }
        profitParam.addExtraText(R.string.recipient_address, toAddress);
        if (BigDecimalUtils.moreThanOrEqual(Double.valueOf(d), 0)) {
            profitParam.addInfoTitle(R.string.withdraw_reward, String.format("%s TRX", StringTronUtil.formatNumber6Truncate(Double.valueOf(d))));
        } else {
            profitParam.addInfoTitle(R.string.withdraw_reward, "");
        }
        BaseConfirmTransParamBuilder baseConfirmTransParamBuilder = new BaseConfirmTransParamBuilder();
        baseConfirmTransParamBuilder.setParam(profitParam);
        return baseConfirmTransParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getUnexpiredProfitParamBuilder(String str, String str2) {
        UnexpiredProfitParam unexpiredProfitParam = new UnexpiredProfitParam();
        unexpiredProfitParam.setType(9);
        unexpiredProfitParam.setLayoutHeight(UIUtils.dip2px(450.0f));
        unexpiredProfitParam.setPageFrom(BaseParam.PageFrom.Local);
        unexpiredProfitParam.addIconResource(R.mipmap.ic_confirm_withdraw_balance);
        unexpiredProfitParam.setTitle(R.string.confirmtransaction, 0, R.string.et_input_password);
        unexpiredProfitParam.addInfoTitle(R.string.withdraw_reward, str2 + " TRX");
        unexpiredProfitParam.addExtraText(R.string.recipient_address, String.format("%s(%s)", Utils.getNameByAddress(str), str));
        unexpiredProfitParam.setErrorHint(R.string.vote_donot_confirm, 1);
        BaseConfirmTransParamBuilder baseConfirmTransParamBuilder = new BaseConfirmTransParamBuilder();
        baseConfirmTransParamBuilder.setParam(unexpiredProfitParam);
        return baseConfirmTransParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getColdTransactionParamBuilder(QrBean qrBean, List<Protocol.Transaction> list) {
        BaseParam baseParam = new BaseParam();
        for (Protocol.Transaction transaction : list) {
            baseParam.addTransaction(transaction);
        }
        baseParam.setPageIndex(3);
        baseParam.setQrBean(qrBean);
        baseParam.setPageFrom(BaseParam.PageFrom.Local);
        baseParam.setTitle(0, 0, R.string.et_input_password);
        baseParam.setHasOwnerPermission(true);
        BaseConfirmTransParamBuilder baseConfirmTransParamBuilder = new BaseConfirmTransParamBuilder();
        baseConfirmTransParamBuilder.setParam(baseParam);
        return baseConfirmTransParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getColdTransactionParamBuilder2(QrBean qrBean, List<Protocol.Transaction> list, BaseParam.PageFrom pageFrom) {
        BaseConfirmTransParamBuilder coldTransactionParamBuilder = getColdTransactionParamBuilder(qrBean, list);
        coldTransactionParamBuilder.get().setPageFrom(pageFrom);
        return coldTransactionParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getColdSignMessageBuilder(QrBean qrBean, String str, int i) {
        BaseParam baseParam = new BaseParam();
        baseParam.setPageIndex(3);
        baseParam.setQrBean(qrBean);
        baseParam.setTitle(0, 0, R.string.et_input_password);
        baseParam.setHasOwnerPermission(true);
        baseParam.setMessage(str);
        baseParam.setType(i);
        baseParam.setPageFrom(BaseParam.PageFrom.Local);
        BaseConfirmTransParamBuilder baseConfirmTransParamBuilder = new BaseConfirmTransParamBuilder();
        baseConfirmTransParamBuilder.setParam(baseParam);
        return baseConfirmTransParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getSwapParamBuilder(boolean z, List<Protocol.Transaction> list, SwapConfirmBean swapConfirmBean) {
        SwapParam swapParam = new SwapParam();
        swapParam.setHasOwnerPermission(z);
        swapParam.setPageIndex(1);
        swapParam.setSwapConfirmBean(swapConfirmBean);
        swapParam.setType(33);
        for (Protocol.Transaction transaction : list) {
            swapParam.addTransaction(transaction);
        }
        swapParam.setPageFrom(BaseParam.PageFrom.Local);
        swapParam.setTitle(0, 0, R.string.et_input_password);
        BaseConfirmTransParamBuilder baseConfirmTransParamBuilder = new BaseConfirmTransParamBuilder();
        baseConfirmTransParamBuilder.setParam(swapParam);
        return baseConfirmTransParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getDappParamBuilder(Protocol.Transaction transaction, String str) {
        DappParam dappParam = new DappParam();
        dappParam.setPassword(str);
        dappParam.addTransaction(transaction);
        dappParam.setPageIndex(3);
        dappParam.setType(14);
        dappParam.setTitle(0, 0, R.string.et_input_password);
        BaseConfirmTransParamBuilder baseConfirmTransParamBuilder = new BaseConfirmTransParamBuilder();
        baseConfirmTransParamBuilder.setParam(dappParam);
        return baseConfirmTransParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getDeepLinkParamBuilder(Protocol.Transaction transaction, String str) {
        DappParam dappParam = new DappParam();
        dappParam.setPassword(str);
        dappParam.addTransaction(transaction);
        dappParam.setPageIndex(3);
        dappParam.setPageFrom(BaseParam.PageFrom.DeepLink);
        dappParam.setTitle(0, 0, R.string.et_input_password);
        BaseConfirmTransParamBuilder baseConfirmTransParamBuilder = new BaseConfirmTransParamBuilder();
        baseConfirmTransParamBuilder.setParam(dappParam);
        return baseConfirmTransParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getDappMessageParamBuilder(String str, String str2, int i) {
        DappParam dappParam = new DappParam();
        dappParam.setMessage(str);
        dappParam.setPageIndex(3);
        dappParam.setSignStrType(str2);
        dappParam.setType(i);
        dappParam.setTitle(0, 0, R.string.et_input_password);
        BaseConfirmTransParamBuilder baseConfirmTransParamBuilder = new BaseConfirmTransParamBuilder();
        baseConfirmTransParamBuilder.setParam(dappParam);
        return baseConfirmTransParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getDeepLinkMessageParamBuilder(String str, String str2, int i, BaseParam.PageFrom pageFrom) {
        DappParam dappParam = new DappParam();
        dappParam.setMessage(str);
        dappParam.setPageIndex(3);
        dappParam.setSignStrType(str2);
        dappParam.setType(i);
        dappParam.setPageFrom(pageFrom);
        dappParam.setTitle(0, 0, R.string.et_input_password);
        BaseConfirmTransParamBuilder baseConfirmTransParamBuilder = new BaseConfirmTransParamBuilder();
        baseConfirmTransParamBuilder.setParam(dappParam);
        return baseConfirmTransParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getDappDetailParamBuilder(Protocol.Transaction transaction, String str, String str2, String str3, TokenBean tokenBean, String str4, String str5, String str6, int i, int i2, TriggerData triggerData, String str7, SmartContractOuterClass.TriggerSmartContract triggerSmartContract, int i3) {
        DappDetailParam dappDetailParam = new DappDetailParam();
        dappDetailParam.setAmount(str3);
        dappDetailParam.setActive(true);
        dappDetailParam.addTransaction(transaction);
        dappDetailParam.setOwnerAddress(str);
        dappDetailParam.setToAddress(str2);
        dappDetailParam.setTokenBean(tokenBean);
        dappDetailParam.setContractTypeString(str4);
        dappDetailParam.setNote(str5);
        dappDetailParam.setContractType(i);
        dappDetailParam.setAssetName(str6);
        dappDetailParam.setTokenActionType(i2);
        dappDetailParam.setTriggerData(triggerData);
        dappDetailParam.setContractAddress(str7);
        dappDetailParam.setTriggerSmartContract(triggerSmartContract);
        dappDetailParam.setTitleId(i3);
        BaseConfirmTransParamBuilder baseConfirmTransParamBuilder = new BaseConfirmTransParamBuilder();
        baseConfirmTransParamBuilder.setParam(dappDetailParam);
        return baseConfirmTransParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getApproveParamBuilder(Protocol.Transaction transaction, String str, String str2, TokenBean tokenBean, String str3, int i, int i2, TriggerData triggerData, String str4, SmartContractOuterClass.TriggerSmartContract triggerSmartContract, int i3) {
        SwapApproveParam swapApproveParam = new SwapApproveParam();
        swapApproveParam.setAmount(str2);
        swapApproveParam.setPageIndex(1);
        swapApproveParam.setActive(true);
        swapApproveParam.setType(38);
        swapApproveParam.setTitle(R.string.confirmtransaction, 0, R.string.et_input_password);
        swapApproveParam.setLayoutHeight(UIUtils.dip2px(450.0f));
        swapApproveParam.addInfoTitle(R.string.dapp_approve, "");
        swapApproveParam.addIconResource(R.mipmap.icon_confirm_approve);
        swapApproveParam.setPageIndex(1);
        swapApproveParam.addTransaction(transaction);
        swapApproveParam.setOwnerAddress(str);
        swapApproveParam.setTokenBean(tokenBean);
        swapApproveParam.setContractTypeString(str3);
        swapApproveParam.setContractType(i);
        swapApproveParam.setTokenActionType(i2);
        swapApproveParam.setTriggerData(triggerData);
        swapApproveParam.setContractAddress(str4);
        swapApproveParam.setTriggerSmartContract(triggerSmartContract);
        swapApproveParam.setTitleId(i3);
        DappProjectInfoBean dappProjectInfoBean = new DappProjectInfoBean();
        dappProjectInfoBean.setProjectLogo(tokenBean.getLogoUrl());
        dappProjectInfoBean.setProjectName("SUN");
        dappProjectInfoBean.setWhite(true);
        dappProjectInfoBean.setContractAddress(str4);
        dappProjectInfoBean.setProjectUrl("suo.io");
        swapApproveParam.setDappProjectInfoBean(dappProjectInfoBean);
        BaseConfirmTransParamBuilder baseConfirmTransParamBuilder = new BaseConfirmTransParamBuilder();
        baseConfirmTransParamBuilder.setParam(swapApproveParam);
        return baseConfirmTransParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getCancelApproveParamBuilder(Protocol.Transaction transaction, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        CancelApproveParam cancelApproveParam = new CancelApproveParam();
        cancelApproveParam.setPageIndex(1);
        cancelApproveParam.setActive(true);
        cancelApproveParam.setType(39);
        cancelApproveParam.setTitle(R.string.confirmtransaction, 0, R.string.et_input_password);
        cancelApproveParam.setLayoutHeight(UIUtils.dip2px(450.0f));
        cancelApproveParam.addInfoTitle(R.string.cancel_approve, "");
        cancelApproveParam.addIconResource(R.mipmap.icon_confirm_approve);
        cancelApproveParam.setPageIndex(1);
        cancelApproveParam.addTransaction(transaction);
        cancelApproveParam.setOwnerAddress(str);
        cancelApproveParam.setTokenAddress(str2);
        cancelApproveParam.setProjectAddress(str3);
        cancelApproveParam.setTokenSymbol(str5);
        cancelApproveParam.setTokenName(str4);
        cancelApproveParam.setTokenLogo(str6);
        cancelApproveParam.setApproveAddress(str9);
        cancelApproveParam.setTokenType(str7);
        cancelApproveParam.setNum(str10);
        cancelApproveParam.setTrc721Id(str11);
        cancelApproveParam.setApproveProjectType(str8);
        BaseConfirmTransParamBuilder baseConfirmTransParamBuilder = new BaseConfirmTransParamBuilder();
        baseConfirmTransParamBuilder.setParam(cancelApproveParam);
        return baseConfirmTransParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getDappMetadataParamBuilder(Protocol.Transaction transaction, String str, String str2, String str3, String str4, TriggerData triggerData) {
        DappMetadataParam dappMetadataParam = new DappMetadataParam();
        dappMetadataParam.setAmount(str);
        dappMetadataParam.setContractMethodName(str4);
        dappMetadataParam.addTransaction(transaction);
        dappMetadataParam.setTriggerData(triggerData);
        BaseConfirmTransParamBuilder baseConfirmTransParamBuilder = new BaseConfirmTransParamBuilder();
        baseConfirmTransParamBuilder.setParam(dappMetadataParam);
        return baseConfirmTransParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getCollectibleTransactionParamBuilder(boolean z, boolean z2, Protocol.Transaction transaction, TokenBean tokenBean, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, long j) {
        NFTParam nFTParam = new NFTParam();
        nFTParam.addTransaction(transaction);
        nFTParam.setPageIndex(1);
        nFTParam.setType(21);
        nFTParam.setHasOwnerPermission(z);
        nFTParam.setActives(z2);
        nFTParam.setTitle(0, 0, R.string.et_input_password);
        nFTParam.setContractAddress(str8);
        nFTParam.setTokenId(str);
        nFTParam.setTokenBean(tokenBean);
        nFTParam.setNote(str7);
        nFTParam.setFromAddress(str5);
        nFTParam.setToAddress(str6);
        nFTParam.setNftImage(str10);
        nFTParam.setPageFrom(BaseParam.PageFrom.Local);
        nFTParam.setName(str2);
        nFTParam.setTokenShortName(str3);
        nFTParam.setNftTokenImage(str4);
        nFTParam.setShortName(str9);
        nFTParam.setEnergyUsed(j);
        BaseConfirmTransParamBuilder baseConfirmTransParamBuilder = new BaseConfirmTransParamBuilder();
        baseConfirmTransParamBuilder.setParam(nFTParam);
        return baseConfirmTransParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getCustomTransactionParamBuilder(Protocol.Transaction transaction) {
        BaseParam baseParam = new BaseParam();
        baseParam.addTransaction(transaction);
        baseParam.setPageIndex(3);
        baseParam.setPageFrom(BaseParam.PageFrom.Local);
        baseParam.setType(99);
        baseParam.setTitle(0, 0, R.string.et_input_password);
        baseParam.setHasOwnerPermission(true);
        BaseConfirmTransParamBuilder baseConfirmTransParamBuilder = new BaseConfirmTransParamBuilder();
        baseConfirmTransParamBuilder.setParam(baseParam);
        return baseConfirmTransParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getWithDrawDelegatedResourceTransactionParamBuilder(boolean z, long j, String str, Protocol.Transaction transaction) {
        WithDrawDelegatedResourceParam withDrawDelegatedResourceParam = new WithDrawDelegatedResourceParam();
        withDrawDelegatedResourceParam.addTransaction(transaction);
        withDrawDelegatedResourceParam.setHasOwnerPermission(z);
        withDrawDelegatedResourceParam.setPageIndex(1);
        withDrawDelegatedResourceParam.setActives(!z);
        withDrawDelegatedResourceParam.setType(34);
        withDrawDelegatedResourceParam.setTitle(R.string.confirmtransaction, 0, R.string.et_input_password);
        withDrawDelegatedResourceParam.setAmount(j);
        withDrawDelegatedResourceParam.setPageFrom(BaseParam.PageFrom.Local);
        withDrawDelegatedResourceParam.addExtraText(R.string.withdraw_to_address, str);
        withDrawDelegatedResourceParam.addIconResource(R.mipmap.ic_withdraw_resource);
        withDrawDelegatedResourceParam.addInfoTitle(R.string.withdraw_expire_unfreeze, StringTronUtil.getResString(R.string.withdraw_expire_unfreeze) + " " + j + " TRX");
        BaseConfirmTransParamBuilder baseConfirmTransParamBuilder = new BaseConfirmTransParamBuilder();
        baseConfirmTransParamBuilder.setParam(withDrawDelegatedResourceParam);
        return baseConfirmTransParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getDelegatedResourceTransactionParamBuilder(boolean z, String str, String str2, boolean z2, int i, long j, long j2, Protocol.Transaction transaction) {
        String format;
        String format2;
        DelegatedResourceParam delegatedResourceParam = new DelegatedResourceParam();
        delegatedResourceParam.addTransaction(transaction);
        delegatedResourceParam.setHasOwnerPermission(z);
        delegatedResourceParam.setPageIndex(1);
        delegatedResourceParam.setActives(!z);
        delegatedResourceParam.setType(z2 ? 35 : 36);
        delegatedResourceParam.setTitle(R.string.confirmtransaction, 0, R.string.et_input_password);
        delegatedResourceParam.setExtraAddress(str);
        delegatedResourceParam.setResourceType(i);
        delegatedResourceParam.setResourceBalance(j);
        delegatedResourceParam.setResourceBalanceByTrx(j2);
        delegatedResourceParam.setPageFrom(BaseParam.PageFrom.Local);
        int i2 = i == 1 ? R.string.energy : R.string.bandwidth;
        if (z2) {
            delegatedResourceParam.addExtraText(R.string.resource_delegate_type, StringTronUtil.getResString(i2));
            delegatedResourceParam.addExtraText(R.string.receiving_address, str);
            if (!StringTronUtil.isNullOrEmpty(str2)) {
                delegatedResourceParam.addExtraText(R.string.delegate_resource_lock, str2);
            }
            delegatedResourceParam.addIconResource(R.mipmap.ic_delegate_resource);
            if (i == 1) {
                format2 = String.format(StringTronUtil.getResString(R.string.delegate_x_energy), StringTronUtil.formatNumber0(j));
            } else {
                format2 = String.format(StringTronUtil.getResString(R.string.delegate_x_bandwidth), StringTronUtil.formatNumber0(j));
            }
            delegatedResourceParam.addInfoTitle(R.string.resource_delegate, format2, String.format(StringTronUtil.getResString(R.string.resource_to_trx), StringTronUtil.formatNumber0(j2)));
        } else {
            delegatedResourceParam.addExtraText(R.string.resource_reclaim_type, StringTronUtil.getResString(i2));
            delegatedResourceParam.addExtraText(R.string.reclaim_to_address, str);
            delegatedResourceParam.addIconResource(R.mipmap.ic_undelegate_resource);
            if (i == 1) {
                format = String.format(StringTronUtil.getResString(R.string.reclaim_x_energy), StringTronUtil.formatNumber0(j));
            } else {
                format = String.format(StringTronUtil.getResString(R.string.reclaim_x_bandwidth), StringTronUtil.formatNumber0(j));
            }
            delegatedResourceParam.addInfoTitle(R.string.resource_recycle, format, String.format(StringTronUtil.getResString(R.string.resource_to_trx), StringTronUtil.formatNumber0(j2)));
        }
        BaseConfirmTransParamBuilder baseConfirmTransParamBuilder = new BaseConfirmTransParamBuilder();
        baseConfirmTransParamBuilder.setParam(delegatedResourceParam);
        return baseConfirmTransParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getStakeAndVoteTransactionParamBuilder(boolean z, boolean z2, double d, long j, String str, String str2, VoteBean voteBean, DataStatHelper.StatAction statAction, Protocol.Transaction transaction, Protocol.Transaction transaction2) {
        double d2;
        StakeAndVoteParam stakeAndVoteParam = new StakeAndVoteParam();
        stakeAndVoteParam.setVoteBean(voteBean);
        stakeAndVoteParam.setDoFreezeType(z ? 1 : 0);
        stakeAndVoteParam.setMoney("" + j);
        stakeAndVoteParam.setType(4);
        stakeAndVoteParam.addTransaction(transaction);
        stakeAndVoteParam.addTransaction(transaction2);
        stakeAndVoteParam.setPageIndex(1);
        stakeAndVoteParam.setStatAction(statAction);
        stakeAndVoteParam.setCanUseTrxCount(d);
        stakeAndVoteParam.setRealFreeze(j);
        stakeAndVoteParam.setResourceCount(str);
        stakeAndVoteParam.setPageFrom(BaseParam.PageFrom.Financial);
        if (BigDecimalUtils.MoreThan((Object) str.replaceAll(",", ""), (Object) 0)) {
            try {
                stakeAndVoteParam.addExtraText(R.string.resource_gain, String.format("≈%s %s", StringTronUtil.formatNumber0(NumberFormat.getNumberInstance().parse(str).doubleValue()), StringTronUtil.getResString(z ? R.string.bandwidth : R.string.energy)));
            } catch (ParseException e) {
                LogUtils.e(e);
            }
        }
        stakeAndVoteParam.addExtraText(R.string.address, str2);
        long j2 = 0;
        for (String str3 : voteBean.getSrcOrderedVoteMap().keySet()) {
            j2 += Long.parseLong(voteBean.getSrcOrderedVoteMap().get(str3));
        }
        stakeAndVoteParam.addExtraText(R.string.transcation_comfirm_type_stake_and_vote_title_vote, String.format("%s %s", StringTronUtil.formatNumber0(j2), StringTronUtil.getResString(R.string.unit_of_vote)));
        stakeAndVoteParam.setHasOwnerPermission(z2);
        stakeAndVoteParam.setActives(!z2);
        stakeAndVoteParam.setTitle(R.string.confirm_freeze, 0, R.string.et_input_password);
        stakeAndVoteParam.addIconResource(R.mipmap.ic_confirm_stake_and_vote);
        stakeAndVoteParam.addInfoTitle(R.string.confirm_stake_and_vote, StringTronUtil.getResString(R.string.stake) + " " + StringTronUtil.formatNumber0(d2) + " TRX");
        BaseConfirmTransParamBuilder baseConfirmTransParamBuilder = new BaseConfirmTransParamBuilder();
        baseConfirmTransParamBuilder.setParam(stakeAndVoteParam);
        return baseConfirmTransParamBuilder;
    }

    public static BaseConfirmTransParamBuilder getCancelUnStakeTransactionParamBuilder(boolean z, double d, double d2, long j, long j2, String str, Protocol.Transaction transaction) {
        String format;
        CancelUnStakeParam cancelUnStakeParam = new CancelUnStakeParam();
        cancelUnStakeParam.setType(37);
        cancelUnStakeParam.addTransaction(transaction);
        cancelUnStakeParam.setPageIndex(1);
        cancelUnStakeParam.setPageFrom(BaseParam.PageFrom.Local);
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        String format2 = (i <= 0 || j2 <= 0) ? i > 0 ? String.format("≈%s %s", StringTronUtil.formatNumber0(j), StringTronUtil.getResString(R.string.energy)) : String.format("≈%s %s", StringTronUtil.formatNumber0(j2), StringTronUtil.getResString(R.string.bandwidth)) : String.format("%s %s + %s %s", NumUtils.numFormatToK(j), StringTronUtil.getResString(R.string.energy), NumUtils.numFormatToK(j2), StringTronUtil.getResString(R.string.bandwidth));
        cancelUnStakeParam.setAmount(d);
        cancelUnStakeParam.setEnergy(j);
        cancelUnStakeParam.setBandwidth(j2);
        cancelUnStakeParam.setWithDrawAmount(d2);
        cancelUnStakeParam.addExtraText(R.string.resource_gain, format2);
        cancelUnStakeParam.addExtraText(R.string.address, str);
        cancelUnStakeParam.addExtraText(R.string.vote_count_gained, String.format("%s %s", StringTronUtil.formatNumber0(d), StringTronUtil.getResString(R.string.unit_of_vote)));
        cancelUnStakeParam.setHasOwnerPermission(z);
        cancelUnStakeParam.setActives(!z);
        cancelUnStakeParam.setTitle(R.string.confirmtransaction, 0, R.string.et_input_password);
        cancelUnStakeParam.addIconResource(R.mipmap.ic_confirm_unstake);
        if (d2 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            format = String.format(StringTronUtil.getResString(R.string.cancel_unstake_amount_and_withdraw), StringTronUtil.formatNumber0(d), StringTronUtil.formatNumber0(d2));
        } else {
            format = String.format(StringTronUtil.getResString(R.string.cancel_unstake_amount), StringTronUtil.formatNumber0(d));
        }
        cancelUnStakeParam.addInfoTitle(R.string.cancel_unstake_title, format);
        BaseConfirmTransParamBuilder baseConfirmTransParamBuilder = new BaseConfirmTransParamBuilder();
        baseConfirmTransParamBuilder.setParam(cancelUnStakeParam);
        return baseConfirmTransParamBuilder;
    }
}
