package com.tron.wallet.business.vote.fastvote;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.business.vote.fastvote.FastVoteContract;
import com.tron.wallet.business.vote.fastvote.VoteDataHolder;
import com.tron.wallet.business.vote.fastvote.adapter.WitnessEasyListAdapter;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.common.config.CommonBundleKeys;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.DataStatHelper;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class FastVotePresenter extends FastVoteContract.Presenter {
    private HashMap<String, String> addressWeightMap;
    private Context context;
    private boolean isFromMultiSign;
    private WitnessEasyListAdapter listAdapter;
    long mAvailableVotes;
    long mTotalVotes;
    private Wallet mWallet;
    private HashMap<String, String> nameWeightMap;
    private HashMap<String, String> oldVoteWeightMap;
    private String selectAddress;
    private VoteDataHolder voteDataHolder;
    private boolean isFromStakeSuccess = false;
    private boolean isExpandOtherVoted = false;
    private DataStatHelper.StatAction statAction = DataStatHelper.StatAction.Vote;

    public interface VoteCountChangeListener {
        void onEtClick();

        void onVoteCountChanged(String str, long j, long j2, String str2);
    }

    public interface VotedTitleClickListener {
        void invisibilityChange();
    }

    public String getLogMultiSignTag() {
        return this.isFromMultiSign ? AnalyticsHelper.VotePage.MULTI_SIGN : AnalyticsHelper.VotePage.SINGLE_SIGN;
    }

    @Override
    public String getCurrentAddress() {
        return this.selectAddress;
    }

    @Override
    protected void onStart() {
        this.context = ((FastVoteContract.View) this.mView).getIContext();
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        this.mWallet = selectedWallet;
        if (selectedWallet == null || selectedWallet.getAddress() == null) {
            ((FastVoteContract.View) this.mView).toast(((FastVoteContract.View) this.mView).getIContext().getString(R.string.time_out));
            ((FastVoteContract.View) this.mView).exit();
        }
        initRx();
        Context context = this.context;
        if (context instanceof Activity) {
            this.selectAddress = ((Activity) context).getIntent().getStringExtra("key_controller_address");
            this.isFromMultiSign = ((Activity) this.context).getIntent().getBooleanExtra(CommonBundleKeys.KEY_FROM_MULTI_SIGN, false);
            this.isFromStakeSuccess = ((Activity) this.context).getIntent().getBooleanExtra(CommonBundleKeys.KEY_IS_FROM_STAKE_SUCCESS, false);
            this.statAction = (DataStatHelper.StatAction) ((Activity) this.context).getIntent().getSerializableExtra(TronConfig.StatAction_Key);
        }
    }

    private void initRx() {
        this.mRxManager.on(Event.BackToVoteHome, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRx$0(obj);
            }
        });
        this.mRxManager.on(Event.BroadcastSuccess, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRx$1(obj);
            }
        });
        this.mRxManager.on(Event.BroadcastSuccess2, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRx$2(obj);
            }
        });
    }

    public void lambda$initRx$0(Object obj) throws Exception {
        ((FastVoteContract.View) this.mView).exit();
    }

    public void lambda$initRx$1(Object obj) throws Exception {
        ((FastVoteContract.View) this.mView).exit();
    }

    public void lambda$initRx$2(Object obj) throws Exception {
        ((FastVoteContract.View) this.mView).exit();
    }

    @Override
    public void init(long j, long j2) {
        this.mAvailableVotes = j;
        this.mTotalVotes = j2;
        this.addressWeightMap = new HashMap<>();
        this.nameWeightMap = new HashMap<>();
        this.oldVoteWeightMap = new HashMap<>();
        long j3 = this.mTotalVotes;
        this.listAdapter = new WitnessEasyListAdapter(((FastVoteContract.View) this.mView).getIContext(), this.isFromMultiSign, this.selectAddress, new HashMap(), j, this.mTotalVotes, (j3 / 10) + (j3 % 10 > 0 ? 1 : 0), new VoteCountChangeListener() {
            @Override
            public void onVoteCountChanged(String str, long j4, long j5, String str2) {
                LogUtils.e("onVoteCountChanged");
                LogUtils.e("onVoteCountChanged oldCount：" + ((String) addressWeightMap.get(str)));
                if (j4 == 0) {
                    addressWeightMap.remove(str);
                    nameWeightMap.remove(str);
                } else {
                    addressWeightMap.put(str, String.valueOf(j4));
                    nameWeightMap.put(str, str2);
                }
                long j6 = j5 - j4;
                if (j6 > 0) {
                    mAvailableVotes += j6;
                } else {
                    long j7 = j4 - j5;
                    if (j7 > 0) {
                        mAvailableVotes -= j7;
                    }
                }
                if (mAvailableVotes < 0) {
                    ((FastVoteContract.View) mView).updateVoteCountTips(true);
                } else {
                    ((FastVoteContract.View) mView).updateVoteCountTips(false);
                }
                ((FastVoteContract.View) mView).updateVoteCountLayout(mAvailableVotes);
                ((FastVoteContract.View) mView).updateVoteApr(listAdapter.getData(), mTotalVotes - mAvailableVotes);
                listAdapter.allVotes = mAvailableVotes;
            }

            @Override
            public void onEtClick() {
                AnalyticsHelper.AssetPage.logEvent(((FastVoteContract.View) mView).getLogPageTag() + getLogMultiSignTag() + 1);
            }
        }, new VotedTitleClickListener() {
            @Override
            public void invisibilityChange() {
                AnalyticsHelper.AssetPage.logEvent(((FastVoteContract.View) mView).getLogPageTag() + getLogMultiSignTag() + 5);
                FastVotePresenter fastVotePresenter = FastVotePresenter.this;
                fastVotePresenter.isExpandOtherVoted = fastVotePresenter.isExpandOtherVoted ^ true;
                showVotedListView();
            }
        });
        ((FastVoteContract.View) this.mView).getRv().setAdapter(this.listAdapter);
        ((FastVoteContract.View) this.mView).getRv().setLayoutManager(new WrapContentLinearLayoutManager(((FastVoteContract.View) this.mView).getIContext()));
        ((FastVoteContract.View) this.mView).getRv().addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect rect, int i, RecyclerView recyclerView) {
                rect.bottom = UIUtils.dip2px(12.0f);
            }
        });
    }

    public void showVotedListView() {
        for (int i = 1; i < this.listAdapter.getData().size(); i++) {
            this.listAdapter.getData().get(i).setVisibility(!this.listAdapter.getData().get(i).visibility);
        }
        WitnessEasyListAdapter witnessEasyListAdapter = this.listAdapter;
        witnessEasyListAdapter.notifyItemRangeChanged(2, witnessEasyListAdapter.getData().size());
    }

    @Override
    public void ClearVoteData(int i) {
        if (i == 2 && !this.isExpandOtherVoted) {
            VoteWitnessBean voteWitnessBean = this.listAdapter.getData().get(0);
            long longValue = this.mAvailableVotes + Long.valueOf(voteWitnessBean.getVoteCount()).longValue();
            this.mAvailableVotes = longValue;
            this.listAdapter.allVotes = longValue;
            voteWitnessBean.setVoteCount("0");
            this.addressWeightMap.remove(voteWitnessBean.getDataBean().getAddress());
            this.nameWeightMap.remove(voteWitnessBean.getDataBean().getAddress());
            this.listAdapter.notifyItemRangeChanged(0, 1);
            ((FastVoteContract.View) this.mView).updateVoteCountLayout(this.mAvailableVotes);
            ((FastVoteContract.View) this.mView).updateVoteApr(this.listAdapter.getData(), this.mTotalVotes - this.mAvailableVotes);
            if (this.mAvailableVotes >= 0) {
                ((FastVoteContract.View) this.mView).updateVoteCountTips(false);
                return;
            }
            return;
        }
        for (int i2 = 0; i2 < this.listAdapter.getData().size(); i2++) {
            this.listAdapter.getData().get(i2).setVoteCount("0");
        }
        this.addressWeightMap.clear();
        this.nameWeightMap.clear();
        long j = this.mTotalVotes;
        this.mAvailableVotes = j;
        this.listAdapter.allVotes = j;
        WitnessEasyListAdapter witnessEasyListAdapter = this.listAdapter;
        witnessEasyListAdapter.notifyItemRangeChanged(0, witnessEasyListAdapter.getData().size());
        ((FastVoteContract.View) this.mView).updateVoteCountLayout(this.mAvailableVotes);
        ((FastVoteContract.View) this.mView).updateVoteApr(this.listAdapter.getData(), 0L);
        ((FastVoteContract.View) this.mView).updateVoteCountTips(false);
    }

    @Override
    public void send(Protocol.Account account, int i, String str, VoteDataHolder.ViewCallback viewCallback) {
        if (this.mAvailableVotes == this.mTotalVotes) {
            ((FastVoteActivity) ((FastVoteContract.View) this.mView).getIContext()).dismissLoadingDialog();
            ((FastVoteContract.View) this.mView).showAlertPopWindow();
        } else if (TextUtils.isEmpty(this.selectAddress)) {
            ((FastVoteActivity) ((FastVoteContract.View) this.mView).getIContext()).dismissLoadingDialog();
        } else {
            try {
                if (this.voteDataHolder == null) {
                    this.voteDataHolder = new VoteDataHolder(this.selectAddress, account, viewCallback, this.listAdapter.getData().get(0).getDataBean().getName(), this.isFromMultiSign, this.isFromStakeSuccess);
                }
                if (compareVoteMap()) {
                    ((FastVoteActivity) ((FastVoteContract.View) this.mView).getIContext()).dismissLoadingDialog();
                    ((FastVoteContract.View) this.mView).toast(((FastVoteContract.View) this.mView).getIContext().getString(R.string.vote_same_as_current));
                    return;
                }
                this.voteDataHolder.asyncVote(this.addressWeightMap, this.nameWeightMap, (i == 0 && StringTronUtil.isEmpty(this.addressWeightMap.get(this.listAdapter.getData().get(0).getDataBean().getAddress()))) ? 3 : i, this.statAction, str);
            } catch (Throwable th) {
                ((FastVoteActivity) ((FastVoteContract.View) this.mView).getIContext()).dismissLoadingDialog();
                SentryUtil.captureException(th);
                try {
                    ((FastVoteContract.View) this.mView).runOnUIThread(new OnMainThread() {
                        @Override
                        public final void doInUIThread() {
                            lambda$send$3();
                        }
                    });
                } catch (Throwable th2) {
                    ((FastVoteActivity) ((FastVoteContract.View) this.mView).getIContext()).dismissLoadingDialog();
                    th2.printStackTrace();
                }
            }
        }
    }

    public void lambda$send$3() {
        ((FastVoteContract.View) this.mView).toast(((FastVoteContract.View) this.mView).getIContext().getString(R.string.time_out));
    }

    private boolean compareVoteMap() {
        if (this.addressWeightMap.size() != this.oldVoteWeightMap.size()) {
            return false;
        }
        for (Map.Entry<String, String> entry : this.addressWeightMap.entrySet()) {
            if (!StringTronUtil.equals(entry.getValue(), this.oldVoteWeightMap.get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void updateRvList(ArrayList<VoteWitnessBean> arrayList, long j, ArrayList<WitnessOutput.DataBean> arrayList2, long j2) {
        this.mAvailableVotes = j2;
        this.listAdapter.allVotes = j2;
        this.listAdapter.setNewData(arrayList);
        this.listAdapter.notifyDataSetChanged();
        ((FastVoteContract.View) this.mView).updateVoteApr(this.listAdapter.getData(), j - j2);
        ((FastVoteContract.View) this.mView).updateVoteCountLayout(this.mAvailableVotes);
        initAddressHashMap(arrayList, arrayList2);
    }

    private void initAddressHashMap(ArrayList<VoteWitnessBean> arrayList, ArrayList<WitnessOutput.DataBean> arrayList2) {
        Iterator<VoteWitnessBean> it = arrayList.iterator();
        while (it.hasNext()) {
            VoteWitnessBean next = it.next();
            if (!next.isTitle() && !StringTronUtil.isEmpty(next.voteCount) && Long.valueOf(next.voteCount).longValue() > 0) {
                this.addressWeightMap.put(next.getDataBean().getAddress(), next.voteCount);
                this.nameWeightMap.put(next.getDataBean().getAddress(), next.getDataBean().getName());
            }
        }
        Iterator<WitnessOutput.DataBean> it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            WitnessOutput.DataBean next2 = it2.next();
            if (!StringTronUtil.isEmpty(next2.getVoted())) {
                this.oldVoteWeightMap.put(next2.getAddress(), next2.getVoted());
            }
        }
    }
}
