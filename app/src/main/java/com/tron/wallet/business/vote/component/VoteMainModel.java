package com.tron.wallet.business.vote.component;

import android.content.Context;
import android.text.TextUtils;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.parambuilder.BaseConfirmTransParamBuilder;
import com.tron.wallet.business.confirm.parambuilder.bean.ProfitParam;
import com.tron.wallet.business.confirm.parambuilder.bean.VoteParam;
import com.tron.wallet.business.confirm.parambuilder.utils.ParamBuildUtils;
import com.tron.wallet.business.mutil.bean.MultiSignPermissionData;
import com.tron.wallet.business.stakev2.managementv2.ResourcesBean;
import com.tron.wallet.business.vote.bean.RewardOutput;
import com.tron.wallet.business.vote.bean.VoteHeaderBean;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.business.vote.component.Contract;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AccountUtils;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.DataStatHelper;
import com.tron.wallet.common.utils.SamsungMultisignUtils;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.HttpAPI;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import j$.util.Collection;
import j$.util.Objects;
import java.util.HashMap;
import java.util.function.Consumer;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class VoteMainModel implements Contract.Model {
    @Override
    public Observable<WitnessOutput> requestWitnessList(String str, int i, int i2, int i3, int i4) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getWitnessList(i3, i4, 2, i2, str, i);
    }

    @Override
    public VoteHeaderBean getVotingData(boolean z, Protocol.Account account, String str) throws Exception {
        Wallet walletForAddress = WalletUtils.getWalletForAddress(str);
        GrpcAPI.AccountResourceMessage accountRes = (walletForAddress == null || z) ? null : WalletUtils.getAccountRes(AppContextUtil.getContext(), walletForAddress.getWalletName());
        if (accountRes == null) {
            accountRes = TronAPI.getAccountRes(StringTronUtil.decode58Check(str));
        }
        ResourcesBean build = ResourcesBean.build(account, accountRes);
        VoteHeaderBean voteHeaderBean = new VoteHeaderBean();
        voteHeaderBean.setTotalVotingRights(build.getVotingTps());
        voteHeaderBean.setAvailableVotingRights(build.getAvailableVotingTps());
        voteHeaderBean.setAlreadyVotedNumber(voteHeaderBean.getTotalVotingRights() - voteHeaderBean.getAvailableVotingRights());
        voteHeaderBean.setAvailableTrx(build.getTrxBalance());
        return voteHeaderBean;
    }

    @Override
    public Protocol.Account ensureAccount(Context context, Protocol.Account account, String str) throws Exception {
        Wallet walletForAddress;
        Protocol.Account queryAccount = TronAPI.queryAccount(StringTronUtil.decode58Check(str), false);
        if (!AccountUtils.isNullAccount(queryAccount)) {
            account = queryAccount;
        }
        return (!AccountUtils.isNullAccount(account) || (walletForAddress = WalletUtils.getWalletForAddress(str)) == null || TextUtils.isEmpty(walletForAddress.getWalletName())) ? account : WalletUtils.getAccount(context, walletForAddress.getWalletName());
    }

    @Override
    public double requestProfitNumber(String str) {
        try {
            GrpcAPI.NumberMessage rewardInfo = TronAPI.getRewardInfo(StringTronUtil.decodeFromBase58Check(str));
            if (rewardInfo != null) {
                long num = rewardInfo.getNum();
                return num >= 0 ? num / Math.pow(10.0d, 6.0d) : FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            }
            return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        } catch (Exception e) {
            SentryUtil.captureException(e);
            LogUtils.e(e);
            return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
    }

    @Override
    public BaseConfirmTransParamBuilder requestGetProfit(String str, String str2, MultiSignPermissionData multiSignPermissionData, Protocol.Account account, double d) throws Exception {
        GrpcAPI.TransactionExtention createWithdrawBalanceTransaction = TronAPI.createWithdrawBalanceTransaction(StringTronUtil.decodeFromBase58Check(str2));
        boolean z = multiSignPermissionData == null;
        try {
            z &= WalletUtils.checkHaveOwnerPermissions(str, account.getOwnerPermission());
        } catch (Exception e) {
            LogUtils.e(e);
        }
        if (createWithdrawBalanceTransaction == null || !createWithdrawBalanceTransaction.hasResult()) {
            return null;
        }
        Protocol.Transaction transaction = createWithdrawBalanceTransaction.getTransaction();
        if (!StringTronUtil.isEmpty(transaction.toString())) {
            BaseConfirmTransParamBuilder profitParamBuilder = ParamBuildUtils.getProfitParamBuilder(z, str2, d, transaction);
            ((ProfitParam) profitParamBuilder.param).setPermissionData(multiSignPermissionData);
            return profitParamBuilder;
        }
        return null;
    }

    @Override
    public Observable<RewardOutput> requestCanReward(String str) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).canReward(str);
    }

    @Override
    public WitnessOutput.DataBean getMaxVotedWitness(final HashMap<String, String> hashMap, final HashMap<String, String> hashMap2, WitnessOutput witnessOutput) {
        hashMap2.clear();
        hashMap.clear();
        final WitnessOutput.DataBean[] dataBeanArr = {null};
        Collection.-EL.stream(witnessOutput.getData()).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                VoteMainModel.lambda$getMaxVotedWitness$0(dataBeanArr, hashMap, hashMap2, (WitnessOutput.DataBean) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        hashMap2.put(dataBeanArr[0].getAddress(), BigDecimalUtils.sub_(dataBeanArr[0].getVoted(), 1).toPlainString());
        return dataBeanArr[0];
    }

    public static void lambda$getMaxVotedWitness$0(WitnessOutput.DataBean[] dataBeanArr, HashMap hashMap, HashMap hashMap2, WitnessOutput.DataBean dataBean) {
        try {
            WitnessOutput.DataBean dataBean2 = dataBeanArr[0];
            if (dataBean2 == null || BigDecimalUtils.LessThan(dataBean2.getVoted(), dataBean.getVoted())) {
                dataBeanArr[0] = dataBean;
            }
            hashMap.put(dataBean.getAddress(), dataBean.getName());
            hashMap2.put(dataBean.getAddress(), dataBean.getVoted());
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    @Override
    public String checkCancelVoteError(Protocol.Account account, Wallet wallet) {
        if (wallet == null) {
            return StringTronUtil.getResString(R.string.could_not_parse_transaction);
        }
        if (wallet.isWatchOnly() && !SpAPI.THIS.getCurrentChain().isMainChain) {
            return StringTronUtil.getResString(R.string.no_support);
        }
        if (SamsungMultisignUtils.isSamsungMultiOwner(wallet.getAddress(), account.getOwnerPermission())) {
            return StringTronUtil.getResString(R.string.no_samsung_to_shield);
        }
        return null;
    }

    @Override
    public BaseConfirmTransParamBuilder getCancelVoteParamBuilder(boolean z, long j, long j2, MultiSignPermissionData multiSignPermissionData, String str, Wallet wallet, Protocol.Account account, HashMap<String, String> hashMap, HashMap<String, String> hashMap2, HashMap<String, String> hashMap3, DataStatHelper.StatAction statAction) throws Exception {
        GrpcAPI.TransactionExtention createVoteWitnessTransaction2 = TronAPI.createVoteWitnessTransaction2(StringTronUtil.decodeFromBase58Check(str), hashMap3);
        boolean z2 = !z && WalletUtils.checkHaveOwnerPermissions(wallet.getAddress(), account.getOwnerPermission());
        if (createVoteWitnessTransaction2 != null && createVoteWitnessTransaction2.hasResult()) {
            Protocol.Transaction transaction = createVoteWitnessTransaction2.getTransaction();
            if (!TextUtils.isEmpty(transaction.toString())) {
                TronConfig.currentPwdType = 9;
                BaseConfirmTransParamBuilder voteTransactionParamBuilder = ParamBuildUtils.getVoteTransactionParamBuilder(hashMap, hashMap2, z2, 1, transaction, 2, str, 0L, "", statAction);
                VoteParam voteParam = (VoteParam) voteTransactionParamBuilder.param;
                voteParam.setVotesAvailable(j2);
                voteParam.setVotesCancelled(j);
                voteParam.setPermissionData(multiSignPermissionData);
                voteTransactionParamBuilder.param = voteParam;
                return voteTransactionParamBuilder;
            }
        }
        throw new Exception(StringTronUtil.getResString(R.string.could_not_parse_transaction));
    }
}
