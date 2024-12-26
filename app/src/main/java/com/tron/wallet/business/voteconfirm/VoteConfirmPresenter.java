package com.tron.wallet.business.voteconfirm;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.parambuilder.utils.ParamBuildUtils;
import com.tron.wallet.business.tabdapp.jsbridge.finance.Keys;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.business.vote.util.ProfitCalculator;
import com.tron.wallet.business.voteconfirm.SingleVoteContract;
import com.tron.wallet.common.utils.SamsungMultisignUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.LedgerWallet;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class VoteConfirmPresenter extends SingleVoteContract.Presenter {
    private long allMyVote;
    private boolean bachVote;
    private boolean hasPer;
    private Protocol.Account mAccount;
    private HashMap<String, String> mCommitNameVotes;
    private HashMap<String, String> mCommitVotes;
    private long mFrozen;
    private HashMap<String, String> mOrderedCommitVotes;
    private List<WitnessOutput.DataBean> mVotedList;
    private Wallet mWallet;
    private List<WitnessOutput.DataBean> mWitnessesList;
    private long myTotalVotes;
    private RxManager rxManager;
    private String selectedAddress;
    private long totalVote;
    private WitnessOutput.DataBean witness;
    private HashMap<String, Long> votesMap = new HashMap<>();
    private long usableVotes = 0;

    @Override
    public long getUsableVotes() {
        return this.usableVotes;
    }

    @Override
    public void getData(final String str, Protocol.Account account, WitnessOutput witnessOutput, WitnessOutput.DataBean dataBean, long j, boolean z) {
        this.witness = dataBean;
        this.selectedAddress = str;
        this.totalVote = j;
        this.bachVote = z;
        this.mWallet = WalletUtils.getSelectedWallet();
        this.mWitnessesList = witnessOutput.getData();
        this.mAccount = account;
        Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$getData$0(str, observableEmitter);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$getData$1(obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$getData$2((Throwable) obj);
            }
        });
    }

    public void lambda$getData$0(String str, ObservableEmitter observableEmitter) throws Exception {
        if (this.mAccount == null) {
            try {
                this.mAccount = TronAPI.queryAccount(StringTronUtil.decodeFromBase58Check(str), false);
            } catch (Exception e) {
                LogUtils.e(e);
                Wallet walletForAddress = WalletUtils.getWalletForAddress(str);
                if (walletForAddress != null) {
                    this.mAccount = WalletUtils.getAccount(((SingleVoteContract.View) this.mView).getIContext(), walletForAddress.getWalletName());
                }
            }
        }
        if (this.mAccount != null) {
            long blance = getBlance();
            this.allMyVote = blance;
            long totalUsed = blance - getTotalUsed();
            this.usableVotes = totalUsed;
            observableEmitter.onNext(Long.valueOf(totalUsed));
            return;
        }
        observableEmitter.onError(new Exception("NetworkError"));
    }

    public void lambda$getData$1(Object obj) throws Exception {
        if (obj instanceof Long) {
            ((SingleVoteContract.View) this.mView).updateVoteCount(this.allMyVote, ((Long) obj).longValue());
        }
    }

    public void lambda$getData$2(Throwable th) throws Exception {
        ((SingleVoteContract.View) this.mView).showNoNetError();
    }

    private void updateNameVotes() {
        try {
            this.mCommitNameVotes.clear();
            this.mOrderedCommitVotes.clear();
            ArrayList arrayList = new ArrayList();
            HashMap<String, String> hashMap = this.mCommitVotes;
            if (hashMap != null && this.mWitnessesList != null) {
                for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                    int i = 0;
                    while (true) {
                        if (i < this.mWitnessesList.size()) {
                            if (this.mWitnessesList.get(i) != null) {
                                String address = this.mWitnessesList.get(i).getAddress();
                                if (!StringTronUtil.isEmpty(address) && address.equals(entry.getKey())) {
                                    this.mCommitNameVotes.put(entry.getKey(), StringTronUtil.isEmpty(this.mWitnessesList.get(i).getName()) ? this.mWitnessesList.get(i).getUrl() : this.mWitnessesList.get(i).getName());
                                    arrayList.add(this.mWitnessesList.get(i));
                                }
                            }
                            i++;
                        } else {
                            WitnessOutput.DataBean dataBean = new WitnessOutput.DataBean();
                            dataBean.setAddress(entry.getKey());
                            arrayList.add(dataBean);
                            break;
                        }
                    }
                }
            }
            if (arrayList.size() > 0) {
                try {
                    Collections.sort(arrayList, new Comparator() {
                        @Override
                        public final int compare(Object obj, Object obj2) {
                            return VoteConfirmPresenter.lambda$updateNameVotes$3((WitnessOutput.DataBean) obj, (WitnessOutput.DataBean) obj2);
                        }
                    });
                } catch (Exception e) {
                    LogUtils.e(e);
                }
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    if (this.mCommitVotes.containsKey(((WitnessOutput.DataBean) arrayList.get(i2)).getAddress())) {
                        this.mOrderedCommitVotes.put(((WitnessOutput.DataBean) arrayList.get(i2)).getAddress(), this.mCommitVotes.get(((WitnessOutput.DataBean) arrayList.get(i2)).getAddress()));
                    }
                }
                return;
            }
            this.mOrderedCommitVotes.putAll(this.mCommitVotes);
        } catch (Exception e2) {
            LogUtils.e(e2);
        }
    }

    public static int lambda$updateNameVotes$3(WitnessOutput.DataBean dataBean, WitnessOutput.DataBean dataBean2) {
        return dataBean.getId() - dataBean2.getId();
    }

    @Override
    public void vote(final long j, final int i) {
        Wallet wallet = this.mWallet;
        if (wallet != null && wallet.isWatchOnly() && !SpAPI.THIS.getCurrentChain().isMainChain) {
            ((SingleVoteContract.View) this.mView).toast(((SingleVoteContract.View) this.mView).getIContext().getString(R.string.no_support));
            return;
        }
        Wallet wallet2 = this.mWallet;
        if (wallet2 != null && SamsungMultisignUtils.isSamsungMultiOwner(wallet2.getAddress(), this.mAccount.getOwnerPermission())) {
            ((SingleVoteContract.View) this.mView).toast(((SingleVoteContract.View) this.mView).getIContext().getString(R.string.no_samsung_to_shield));
            return;
        }
        ((SingleVoteContract.View) this.mView).showLoading(true);
        ((SingleVoteContract.View) this.mView).runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$vote$8(i, j);
            }
        });
    }

    public void lambda$vote$8(final int i, final long j) {
        long j2;
        Long l;
        if (i == 0 || i == 2) {
            j2 = j;
        } else {
            j2 = (i != 1 || (l = this.votesMap.get(this.witness.getAddress())) == null) ? 0L : l.longValue() - j;
        }
        this.votesMap.put(this.witness.getAddress(), Long.valueOf(j2));
        this.mCommitVotes.put(this.witness.getAddress(), String.valueOf(j2));
        if (j2 <= 0) {
            this.votesMap.remove(this.witness.getAddress());
            this.mCommitVotes.remove(this.witness.getAddress());
        } else {
            this.votesMap.put(this.witness.getAddress(), Long.valueOf(j2));
            this.mCommitVotes.put(this.witness.getAddress(), String.valueOf(j2));
        }
        if (this.mCommitVotes.size() > 30) {
            ((SingleVoteContract.View) this.mView).runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$vote$4();
                }
            });
            return;
        }
        updateNameVotes();
        if (LedgerWallet.isLedger(this.mWallet) && this.mOrderedCommitVotes.size() > 5) {
            ((SingleVoteContract.View) this.mView).runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$vote$5();
                }
            });
            return;
        }
        System.currentTimeMillis();
        GrpcAPI.TransactionExtention createVoteWitnessTransaction2 = TronAPI.createVoteWitnessTransaction2(StringTronUtil.decodeFromBase58Check(this.selectedAddress), this.mOrderedCommitVotes);
        try {
            this.mAccount = TronAPI.queryAccount(StringTronUtil.decodeFromBase58Check(this.selectedAddress), false);
            this.hasPer = WalletUtils.checkHaveOwnerPermissions(this.mWallet.getAddress(), this.mAccount.getOwnerPermission());
        } catch (Exception unused) {
            this.hasPer = true;
        }
        final Bundle bundle = new Bundle();
        bundle.putString(NotificationCompat.CATEGORY_EVENT, Keys.OV__vote);
        if (createVoteWitnessTransaction2 != null) {
            try {
                if (createVoteWitnessTransaction2.hasResult() && !"".equals(createVoteWitnessTransaction2.getTransaction().toString())) {
                    final Protocol.Transaction transaction = createVoteWitnessTransaction2.getTransaction();
                    ((SingleVoteContract.View) this.mView).runOnUIThread(new OnMainThread() {
                        @Override
                        public final void doInUIThread() {
                            lambda$vote$6(transaction, bundle, i, j);
                        }
                    });
                }
            } catch (Exception e) {
                bundle.putString("value", "1");
                FirebaseAnalytics.getInstance(((SingleVoteContract.View) this.mView).getIContext()).logEvent("Click_vote_button", bundle);
                LogUtils.e(e);
                ((SingleVoteContract.View) this.mView).showLoading(false);
                return;
            }
        }
        bundle.putString("value", "0");
        FirebaseAnalytics.getInstance(((SingleVoteContract.View) this.mView).getIContext()).logEvent("Click_vote_button", bundle);
        ((SingleVoteContract.View) this.mView).runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$vote$7();
            }
        });
    }

    public void lambda$vote$4() {
        ((SingleVoteContract.View) this.mView).showLoading(false);
        ((SingleVoteContract.View) this.mView).toast(((SingleVoteContract.View) this.mView).getIContext().getString(R.string.vote4));
    }

    public void lambda$vote$5() {
        ((SingleVoteContract.View) this.mView).showLoading(false);
        ((SingleVoteContract.View) this.mView).toast(((SingleVoteContract.View) this.mView).getIContext().getString(R.string.ledger_vote_count_limit_error));
    }

    public void lambda$vote$6(Protocol.Transaction transaction, Bundle bundle, int i, long j) {
        if (transaction != null) {
            bundle.putString("value", "1");
            FirebaseAnalytics.getInstance(((SingleVoteContract.View) this.mView).getIContext()).logEvent("Click_vote_button", bundle);
            ((SingleVoteContract.View) this.mView).showLoading(false);
            ConfirmTransactionNewActivity.startActivity(((SingleVoteContract.View) this.mView).getIContext(), ParamBuildUtils.getVoteTransactionParamBuilder(this.mCommitNameVotes, this.mOrderedCommitVotes, this.hasPer, 1, transaction, i == 1 ? 1 : 0, this.selectedAddress, j, this.witness.getName(), null), this.mWallet.getCreateType() == 7);
            return;
        }
        bundle.putString("value", "0");
        FirebaseAnalytics.getInstance(((SingleVoteContract.View) this.mView).getIContext()).logEvent("Click_vote_button", bundle);
        ((SingleVoteContract.View) this.mView).ToastError();
        ((SingleVoteContract.View) this.mView).showLoading(false);
    }

    public void lambda$vote$7() {
        ((SingleVoteContract.View) this.mView).showLoading(false);
        ((SingleVoteContract.View) this.mView).ToastError();
    }

    @Override
    public double getYield(long j) {
        WitnessOutput.DataBean dataBean = this.witness;
        return dataBean == null ? FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE : ProfitCalculator.getVariableProfit(this.totalVote, dataBean.getRealTimeVotes(), j, this.witness.getBrokerage(), this.witness.getRealTimeRanking());
    }

    private long getBlance() {
        Protocol.Account account = this.mAccount;
        if (account == null) {
            return 0L;
        }
        this.mFrozen = 0L;
        long frozenBalance = account.getAccountResource().getFrozenBalanceForEnergy().getFrozenBalance();
        this.mFrozen = frozenBalance;
        long delegatedFrozenBalanceForBandwidth = frozenBalance + this.mAccount.getDelegatedFrozenBalanceForBandwidth();
        this.mFrozen = delegatedFrozenBalanceForBandwidth;
        this.mFrozen = delegatedFrozenBalanceForBandwidth + this.mAccount.getAccountResource().getDelegatedFrozenBalanceForEnergy();
        for (Protocol.Account.Frozen frozen : this.mAccount.getFrozenList()) {
            this.mFrozen += frozen.getFrozenBalance();
        }
        return this.mFrozen / 1000000;
    }

    private long getTotalUsed() {
        this.myTotalVotes = 0L;
        Protocol.Account account = this.mAccount;
        if (account == null) {
            return 0L;
        }
        for (Protocol.Vote vote : account.getVotesList()) {
            String encode58Check = StringTronUtil.encode58Check(vote.getVoteAddress().toByteArray());
            this.votesMap.put(encode58Check, Long.valueOf(vote.getVoteCount()));
            this.mCommitVotes.put(encode58Check, String.valueOf(vote.getVoteCount()));
            this.myTotalVotes += vote.getVoteCount();
            for (WitnessOutput.DataBean dataBean : this.mWitnessesList) {
                if (TextUtils.equals(encode58Check, dataBean.getAddress())) {
                    this.mVotedList.add(dataBean);
                }
            }
        }
        if (!this.mVotedList.isEmpty()) {
            Collections.sort(this.mVotedList, new Comparator() {
                @Override
                public final int compare(Object obj, Object obj2) {
                    return VoteConfirmPresenter.lambda$getTotalUsed$9((WitnessOutput.DataBean) obj, (WitnessOutput.DataBean) obj2);
                }
            });
        }
        if (this.mView != 0) {
            Long l = this.votesMap.get(this.witness.getAddress());
            ((SingleVoteContract.View) this.mView).updateVotedNumber(l != null ? l.longValue() : 0L);
        }
        return this.myTotalVotes;
    }

    public static int lambda$getTotalUsed$9(WitnessOutput.DataBean dataBean, WitnessOutput.DataBean dataBean2) {
        return dataBean.getId() - dataBean2.getId();
    }

    @Override
    protected void onStart() {
        this.mCommitNameVotes = new HashMap<>();
        this.mOrderedCommitVotes = new HashMap<>();
        this.mCommitNameVotes = new HashMap<>();
        this.mWitnessesList = new ArrayList();
        this.mCommitVotes = new HashMap<>();
        this.mVotedList = new ArrayList();
    }
}
