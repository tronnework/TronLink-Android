package com.tron.wallet.business.vote.superdetail;

import android.text.TextUtils;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.confirm.parambuilder.BaseConfirmTransParamBuilder;
import com.tron.wallet.business.confirm.parambuilder.bean.VoteParam;
import com.tron.wallet.business.confirm.parambuilder.utils.ParamBuildUtils;
import com.tron.wallet.business.tabmy.proposals.bean.SRDetailBean;
import com.tron.wallet.business.vote.bean.SrTotalVotesResponse;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.business.vote.superdetail.SuperDetailContract;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.DataStatHelper;
import com.tron.wallet.common.utils.SamsungMultisignUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tron.wallet.net.HttpAPI;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import java.util.HashMap;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class SuperDetailModel implements SuperDetailContract.Model {
    @Override
    public Observable<SRDetailBean> getSrDetail(String str) {
        return Observable.just(new SRDetailBean());
    }

    @Override
    public Observable<SrTotalVotesResponse> getSrTotalVotes(String str) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getSrTotalVotes(1, 1, str).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<WitnessOutput> getWitnessList(String str, int i, int i2, int i3, int i4, boolean z) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getWitnessList(i, i2, i, i4, str, z ? 1 : 0).compose(RxSchedulers2.io_main());
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
    public BaseConfirmTransParamBuilder getCancelVoteParamBuilder(boolean z, long j, long j2, String str, Wallet wallet, Protocol.Account account, HashMap<String, String> hashMap, HashMap<String, String> hashMap2, HashMap<String, String> hashMap3, long j3, String str2, DataStatHelper.StatAction statAction) throws Exception {
        GrpcAPI.TransactionExtention createVoteWitnessTransaction2 = TronAPI.createVoteWitnessTransaction2(StringTronUtil.decodeFromBase58Check(str), hashMap3);
        boolean z2 = !z && WalletUtils.checkHaveOwnerPermissions(wallet.getAddress(), account.getOwnerPermission());
        if (createVoteWitnessTransaction2 != null && createVoteWitnessTransaction2.hasResult()) {
            Protocol.Transaction transaction = createVoteWitnessTransaction2.getTransaction();
            if (!TextUtils.isEmpty(transaction.toString())) {
                TronConfig.currentPwdType = 9;
                BaseConfirmTransParamBuilder voteTransactionParamBuilder = ParamBuildUtils.getVoteTransactionParamBuilder(hashMap, hashMap2, z2, 1, transaction, 1, str, j3, str2, statAction);
                VoteParam voteParam = (VoteParam) voteTransactionParamBuilder.param;
                voteParam.setVotesAvailable(j2);
                voteParam.setVotesCancelled(j);
                voteTransactionParamBuilder.param = voteParam;
                return voteTransactionParamBuilder;
            }
        }
        throw new Exception(StringTronUtil.getResString(R.string.could_not_parse_transaction));
    }
}
