package com.tron.wallet.business.vote.component;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.mutil.bean.MultiSignPermissionData;
import com.tron.wallet.business.stakev2.managementv2.ResourcesBean;
import com.tron.wallet.business.tabmy.proposals.ChangeAddressActivity;
import com.tron.wallet.business.vote.adapter.VoteSelectSRAdapter;
import com.tron.wallet.business.vote.bean.SearchWitnessResult;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.business.vote.component.VoteSelectSRContract;
import com.tron.wallet.business.vote.fastvote.FastVoteActivity;
import com.tron.wallet.business.vote.superdetail.SuperDetailActivity;
import com.tron.wallet.common.components.RecyclerViewUtil;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.common.components.dialog.BatchVoteDetailDialog;
import com.tron.wallet.common.components.ptr.PtrDefaultHandler;
import com.tron.wallet.common.components.ptr.PtrFrameLayout;
import com.tron.wallet.common.components.ptr.PtrHandler;
import com.tron.wallet.common.config.CommonBundleKeys;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.interfaces.VoteSRSelectedChangeListener;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class VoteSelectSRPresenter extends VoteSelectSRContract.Presenter {
    private long allVotesCount;
    private long availableCount;
    private String controlName;
    private boolean isFromMultisig;
    private boolean isLedger;
    private Protocol.Account mAccount;
    private HashMap<String, String> mCommitVotes;
    public String mConstraint;
    private BatchVoteDetailDialog mDialog;
    private long mFrozen;
    private Handler mHandler;
    private NumberFormat mNumberFormat;
    private HashMap<String, String> mVotes;
    private Wallet mWallet;
    private LinearLayoutManager manager;
    private long myVotedCount;
    private MultiSignPermissionData permissionData;
    private RecyclerViewUtil recyclerViewUtil;
    private String selectAddress;
    private ArrayList<WitnessOutput.DataBean> selectedWitnessesList;
    private long totalMyVotes;
    private VoteSelectSRAdapter voteItemAdapter;
    private VoteSRSelectedChangeListener voteSRSelectedChangeListener;
    private List<WitnessOutput.DataBean> witnessesAlreadyList;
    private List<WitnessOutput.DataBean> witnessesList;
    private List<String> mVotedAddress = new ArrayList();
    private List<String> mAlreadyVotedAddress = new ArrayList();
    public volatile boolean showFilter = false;
    private String trxRewardStr = "0";
    private boolean isFromStakeSuccess = false;
    private boolean isChanged = false;
    private boolean isFirst = true;
    private int pageSize = 30;
    private int pageIndex = 0;
    private int sortType = 5;
    private boolean sortScrollToTop = false;
    private boolean filterMyVotes = false;
    private int hasAll = 0;
    private boolean isLoading = false;

    @Override
    protected void clear() {
    }

    @Override
    public long getMyAvailableVotes() {
        return this.availableCount;
    }

    @Override
    protected String getSelectAddress() {
        return this.selectAddress;
    }

    @Override
    public long getTotalVotes() {
        return this.totalMyVotes;
    }

    @Override
    protected void getVoteReward(String str) {
    }

    @Override
    public boolean isLedger() {
        return this.isLedger;
    }

    @Override
    public long myVotedCount() {
        return this.myVotedCount;
    }

    @Override
    protected void onStart() {
        this.selectedWitnessesList = new ArrayList<>();
        this.mCommitVotes = new HashMap<>();
        this.mVotes = new HashMap<>();
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.mNumberFormat = numberInstance;
        numberInstance.setMaximumFractionDigits(6);
        initRx();
        this.voteSRSelectedChangeListener = new VoteSRSelectedChangeListener() {
            @Override
            public void onVoteSRSelected(String str, WitnessOutput.DataBean dataBean, boolean z) {
                isChanged = true;
                if (!mVotedAddress.contains(str) && z) {
                    mVotedAddress.add(str);
                    selectedWitnessesList.add(dataBean);
                }
                ((VoteSelectSRContract.View) mView).onSRSelectedChanged(mVotedAddress.size());
            }

            @Override
            public void onVoteSRUnSelected(String str, WitnessOutput.DataBean dataBean) {
                isChanged = true;
                if (mVotedAddress.contains(str)) {
                    mVotedAddress.remove(str);
                    selectedWitnessesList.remove(dataBean);
                }
                ((VoteSelectSRContract.View) mView).onSRSelectedChanged(mVotedAddress.size());
            }

            @Override
            public void onVoteSRClicked(String str, WitnessOutput.DataBean dataBean) {
                WitnessOutput witnessOutput = new WitnessOutput();
                witnessOutput.setData(voteItemAdapter.getData());
                SuperDetailActivity.start(((VoteSelectSRContract.View) mView).getContext(), mAccount, witnessOutput, BigDecimalUtils.toBigDecimal(dataBean.getVoted()).intValue(), totalMyVotes, permissionData, dataBean, (ArrayList) witnessesAlreadyList, (int) dataBean.getRealTimeRanking(), controlName, selectAddress, isFromMultisig, totalMyVotes > 0, ((VoteSelectSRContract.View) mView).getStatAction());
            }
        };
    }

    @Override
    public void addSome(String str, Protocol.Account account, ArrayList<WitnessOutput.DataBean> arrayList) {
        this.selectAddress = str;
        this.mAccount = account;
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        this.mWallet = selectedWallet;
        this.isLedger = selectedWallet == null ? false : selectedWallet.isLedgerHDWallet();
        if (arrayList != null) {
            this.witnessesList = arrayList;
        } else if (this.witnessesList == null) {
            this.witnessesList = new ArrayList();
        }
        this.mHandler = new Handler() {
            @Override
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (((VoteSelectSRContract.View) mView).getEtSearch() != null && message.what == 0 && (message.obj instanceof String) && TextUtils.equals((CharSequence) message.obj, ((VoteSelectSRContract.View) mView).getEtSearch().getText().toString())) {
                    search(message.obj.toString().toLowerCase().trim());
                }
            }
        };
        if (((VoteSelectSRContract.View) this.mView).getContext() instanceof Activity) {
            this.isFromMultisig = ((Activity) this.mView).getIntent().getBooleanExtra(CommonBundleKeys.KEY_FROM_MULTI_SIGN, false);
            this.isFromStakeSuccess = ((Activity) this.mView).getIntent().getBooleanExtra(CommonBundleKeys.KEY_IS_FROM_STAKE_SUCCESS, false);
            this.permissionData = (MultiSignPermissionData) ((Activity) this.mView).getIntent().getParcelableExtra(CommonBundleKeys.KEY_PERMISSION_DATA);
            this.witnessesAlreadyList = ((Activity) this.mView).getIntent().getParcelableArrayListExtra(CommonBundleKeys.KEY_ALREADY_VOTED_WITNESSES);
            if (this.isFromMultisig) {
                this.controlName = ((Activity) this.mView).getIntent().getStringExtra("key_controller_name");
            }
            if (this.witnessesAlreadyList == null) {
                this.witnessesAlreadyList = new ArrayList();
            }
        }
        initView();
        updateRemain();
        getData();
    }

    public void refresh() {
        this.pageIndex = 0;
        getData();
    }

    public void sortRefresh(int i, boolean z) {
        this.sortType = i;
        this.sortScrollToTop = true;
        this.filterMyVotes = z;
        refresh();
    }

    private void initView() {
        this.manager = new WrapContentLinearLayoutManager(((VoteSelectSRContract.View) this.mView).getContext(), 1, false);
        this.voteItemAdapter = new VoteSelectSRAdapter(((VoteSelectSRContract.View) this.mView).getContext(), this.mAccount, this.selectAddress, true, this.isLedger, this.witnessesList, this.selectedWitnessesList, this.voteSRSelectedChangeListener);
        ((VoteSelectSRContract.View) this.mView).getRv().setLayoutManager(this.manager);
        this.voteItemAdapter.bindToRecyclerView(((VoteSelectSRContract.View) this.mView).getRv());
        ((VoteSelectSRContract.View) this.mView).getRv().setAdapter(this.voteItemAdapter);
        ((VoteSelectSRContract.View) this.mView).getPl().setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
                return PtrDefaultHandler.checkContentCanBePulledDown(ptrFrameLayout, ((VoteSelectSRContract.View) mView).getRv(), view2);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                updateRemain();
                if (showFilter) {
                    VoteSelectSRPresenter voteSelectSRPresenter = VoteSelectSRPresenter.this;
                    voteSelectSRPresenter.search(voteSelectSRPresenter.mConstraint);
                    return;
                }
                refresh();
            }
        });
        this.voteItemAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public final void onLoadMoreRequested() {
                lambda$initView$0();
            }
        }, ((VoteSelectSRContract.View) this.mView).getRv());
        this.voteItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if (i == voteItemAdapter.getData().size()) {
                    getData();
                }
            }
        });
        ((DefaultItemAnimator) ((VoteSelectSRContract.View) this.mView).getRv().getItemAnimator()).setSupportsChangeAnimations(false);
        ((VoteSelectSRContract.View) this.mView).getEtSearch().addTextChangedListener(new TextWatcher() {
            String lastInput = "";

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() > 0) {
                    ((VoteSelectSRContract.View) mView).getIvClear().setVisibility(View.VISIBLE);
                } else {
                    ((VoteSelectSRContract.View) mView).getIvClear().setVisibility(View.GONE);
                }
                if (editable.toString().equals(this.lastInput)) {
                    return;
                }
                this.lastInput = editable.toString();
                Message obtainMessage = mHandler.obtainMessage(0);
                obtainMessage.obj = editable.toString();
                mHandler.sendMessageDelayed(obtainMessage, 1L);
            }
        });
        ((VoteSelectSRContract.View) this.mView).getEtSearch().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    AnalyticsHelper.logEvent(isFromMultisig ? AnalyticsHelper.VoteSelectSR.MULTI_SIGN : "common_vote_batch_step_one_0_1");
                    ((VoteSelectSRContract.View) mView).getIvSort().setVisibility(View.GONE);
                    ((VoteSelectSRContract.View) mView).getTvCancelSearch().setVisibility(View.VISIBLE);
                    return;
                }
                ((VoteSelectSRContract.View) mView).getIvSort().setVisibility(View.VISIBLE);
                ((VoteSelectSRContract.View) mView).getTvCancelSearch().setVisibility(View.GONE);
            }
        });
    }

    public void lambda$initView$0() {
        if (this.showFilter) {
            return;
        }
        this.pageIndex++;
        getData();
    }

    @Override
    public void updateRemain() {
        ((VoteSelectSRContract.View) this.mView).runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$updateRemain$2();
            }
        });
    }

    public void lambda$updateRemain$2() {
        if (!StringTronUtil.isEmpty(this.selectAddress) && this.mAccount == null) {
            try {
                this.mAccount = TronAPI.queryAccount(StringTronUtil.decode58Check(this.selectAddress), false);
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
        if (this.mAccount != null) {
            this.mVotes.clear();
            this.myVotedCount = 0L;
            for (Protocol.Vote vote : this.mAccount.getVotesList()) {
                this.mVotes.put(StringTronUtil.encode58Check(vote.getVoteAddress().toByteArray()), String.valueOf(vote.getVoteCount()));
                this.myVotedCount += vote.getVoteCount();
            }
        }
        if (this.mAccount != null) {
            this.totalMyVotes = 0L;
            this.totalMyVotes = getBalance();
            this.availableCount = getBalance() - this.myVotedCount;
            ((VoteSelectSRContract.View) this.mView).runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$updateRemain$1();
                }
            });
        }
    }

    public void lambda$updateRemain$1() {
        long longExtra = ((Activity) ((VoteSelectSRContract.View) this.mView).getContext()).getIntent().getLongExtra(CommonBundleKeys.KEY_ALL_MY_VOTE_RIGHTS, 0L);
        ((VoteSelectSRContract.View) this.mView).getTotalVotes().setText(StringTronUtil.formatNumber3Truncate(Long.valueOf(this.totalMyVotes)));
        if (this.totalMyVotes == 0 && longExtra == 0) {
            ((VoteSelectSRContract.View) this.mView).showNoEnoughVotes(true);
        } else {
            ((VoteSelectSRContract.View) this.mView).showNoEnoughVotes(false);
        }
    }

    private long getBalance() {
        long frozenSun = ResourcesBean.getFrozenSun(this.mAccount);
        this.mFrozen = frozenSun;
        return frozenSun / 1000000;
    }

    private void initRx() {
        this.mRxManager.on(Event.BroadcastSuccess, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRx$3(obj);
            }
        });
        this.mRxManager.on(Event.NET_CHANGE, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRx$4(obj);
            }
        });
        this.mRxManager.on(Event.SELECTEDWALLET, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRx$5(obj);
            }
        });
        this.mRxManager.on(Event.BroadcastFail, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRx$6(obj);
            }
        });
        this.mRxManager.on(Event.BackToVoteHome, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRx$7(obj);
            }
        });
    }

    public void lambda$initRx$3(Object obj) throws Exception {
        if (this.mView != 0) {
            ((VoteSelectSRContract.View) this.mView).exit();
        }
    }

    public void lambda$initRx$4(Object obj) throws Exception {
        if (!TronConfig.hasNet) {
            if (this.voteItemAdapter.getData().isEmpty()) {
                ((VoteSelectSRContract.View) this.mView).showOrHideNoNet(true);
            } else {
                ((VoteSelectSRContract.View) this.mView).toast(((VoteSelectSRContract.View) this.mView).getContext().getString(R.string.time_out));
            }
        } else if (this.voteItemAdapter.getData().isEmpty()) {
            refresh();
        }
    }

    public void lambda$initRx$5(Object obj) throws Exception {
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        this.mWallet = selectedWallet;
        if (selectedWallet != null) {
            updateRemain();
            getData();
        }
    }

    public void lambda$initRx$6(Object obj) throws Exception {
        this.showFilter = false;
        getData();
    }

    public void lambda$initRx$7(Object obj) throws Exception {
        if (this.mView != 0) {
            ((VoteSelectSRContract.View) this.mView).exit();
        }
    }

    @Override
    protected void getData() {
        if (this.isLoading) {
            this.mRxManager.removeDisposableByKey("getWitness");
        }
        this.isLoading = true;
        if (this.pageIndex == 0) {
            ((VoteSelectSRContract.View) this.mView).showPlaceHolder(true);
        }
        ((VoteSelectSRContract.Model) this.mModel).getWitnessList(this.selectAddress, this.pageSize, this.pageIndex + 1, this.hasAll, this.sortType, this.filterMyVotes).subscribe(new IObserver(new ICallback<WitnessOutput>() {
            @Override
            public void onResponse(String str, WitnessOutput witnessOutput) {
                ((VoteSelectSRContract.View) mView).getPl().refreshComplete();
                if (witnessOutput != null) {
                    if (witnessOutput.getData().isEmpty()) {
                        if (pageIndex == 0) {
                            ((VoteSelectSRContract.View) mView).showOrHideNoData(true);
                        }
                        voteItemAdapter.loadMoreEnd();
                        return;
                    }
                    if (pageIndex == 0) {
                        ((VoteSelectSRContract.View) mView).getPl().refreshComplete();
                        voteItemAdapter.loadMoreComplete();
                    } else {
                        voteItemAdapter.loadMoreComplete();
                    }
                    ((VoteSelectSRContract.View) mView).showOrHideNoNet(false);
                    ((VoteSelectSRContract.View) mView).showOrHideNoData(false);
                    allVotesCount = witnessOutput.getTotalVotes();
                    voteItemAdapter.updateSearchFilter(showFilter, "");
                    if (witnessOutput.getData() != null && witnessOutput.getData().size() > 0) {
                        for (int i = 0; i < witnessOutput.getData().size(); i++) {
                            WitnessOutput.DataBean dataBean = witnessOutput.getData().get(i);
                            if (mVotedAddress.contains(dataBean.getAddress())) {
                                dataBean.setSelected(true);
                            }
                        }
                    }
                    if (witnessesAlreadyList == null) {
                        witnessesAlreadyList = new ArrayList();
                    }
                    if (!isChanged && isFirst && witnessOutput.getData() != null && witnessOutput.getData().size() > 0) {
                        isFirst = false;
                        for (int i2 = 0; i2 < witnessOutput.getData().size(); i2++) {
                            WitnessOutput.DataBean dataBean2 = witnessOutput.getData().get(i2);
                            if (mVotes.containsKey(dataBean2.getAddress())) {
                                dataBean2.setSelected(true);
                                if (!mVotedAddress.contains(dataBean2.getAddress())) {
                                    mVotedAddress.add(dataBean2.getAddress());
                                    selectedWitnessesList.add(dataBean2);
                                }
                                if (!witnessesAlreadyList.contains(dataBean2)) {
                                    witnessesAlreadyList.add(dataBean2);
                                }
                            }
                        }
                    }
                    ((VoteSelectSRContract.View) mView).updateAlreadyVotedList();
                    if (pageIndex == 0) {
                        witnessesList = witnessOutput.getData();
                    }
                    ((VoteSelectSRContract.View) mView).onSRSelectedChanged(mVotedAddress.size());
                    voteItemAdapter.notifyData(mVotes, witnessOutput.getData(), pageIndex == 0);
                    if (sortScrollToTop || pageIndex == 0) {
                        ((VoteSelectSRContract.View) mView).getRv().scrollToPosition(0);
                        sortScrollToTop = false;
                    }
                } else {
                    ((VoteSelectSRContract.View) mView).showOrHideNoNet(true);
                }
                ((VoteSelectSRContract.View) mView).showPlaceHolder(false);
                isLoading = false;
            }

            @Override
            public void onFailure(String str, String str2) {
                ((VoteSelectSRContract.View) mView).getPl().refreshComplete();
                if (voteItemAdapter.getData().isEmpty() || pageIndex == 0) {
                    ((VoteSelectSRContract.View) mView).showOrHideNoNet(true);
                } else {
                    ((VoteSelectSRContract.View) mView).toast(((VoteSelectSRContract.View) mView).getContext().getString(R.string.time_out));
                }
                voteItemAdapter.loadMoreFail();
                isLoading = false;
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add("getWitness", disposable);
            }
        }, "BatchVote-getData"));
    }

    @Override
    public void getTop3Data() {
        ((VoteSelectSRContract.Model) this.mModel).getWitnessList(this.selectAddress, 3, 1, this.hasAll, 6, false).subscribe(new IObserver(new ICallback<WitnessOutput>() {
            @Override
            public void onResponse(String str, WitnessOutput witnessOutput) {
                if (witnessOutput == null || witnessOutput.getData().isEmpty()) {
                    return;
                }
                ((VoteSelectSRContract.View) mView).updateTop3ApyList(witnessOutput.getData());
            }

            @Override
            public void onFailure(String str, String str2) {
                ((VoteSelectSRContract.View) mView).toast(((VoteSelectSRContract.View) mView).getContext().getString(R.string.time_out));
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "getTop3Data"));
    }

    @Override
    public ArrayList<WitnessOutput.DataBean> getVotedWitnesses() {
        return (ArrayList) this.witnessesAlreadyList;
    }

    @Override
    public void vote() {
        int i = this.sortType;
        if (i == 5) {
            Collections.sort(this.selectedWitnessesList, new Comparator<WitnessOutput.DataBean>() {
                @Override
                public int compare(WitnessOutput.DataBean dataBean, WitnessOutput.DataBean dataBean2) {
                    return Long.compare(TextUtils.isEmpty(dataBean2.getVoted()) ? 0L : Long.parseLong(dataBean2.getVoted()), TextUtils.isEmpty(dataBean.getVoted()) ? 0L : Long.parseLong(dataBean.getVoted()));
                }
            });
        } else if (i == 6) {
            Collections.sort(this.selectedWitnessesList, new Comparator<WitnessOutput.DataBean>() {
                @Override
                public int compare(WitnessOutput.DataBean dataBean, WitnessOutput.DataBean dataBean2) {
                    return Double.compare(dataBean2.getAnnualized_income(), dataBean.getAnnualized_income());
                }
            });
        } else {
            Collections.sort(this.selectedWitnessesList, new Comparator<WitnessOutput.DataBean>() {
                @Override
                public int compare(WitnessOutput.DataBean dataBean, WitnessOutput.DataBean dataBean2) {
                    return Long.compare(dataBean2.getRealTimeVotes(), dataBean.getRealTimeVotes());
                }
            });
        }
        Context context = ((VoteSelectSRContract.View) this.mView).getContext();
        Protocol.Account account = this.mAccount;
        ArrayList<WitnessOutput.DataBean> arrayList = this.selectedWitnessesList;
        FastVoteActivity.startActivity(context, account, arrayList, 1, this.availableCount, this.totalMyVotes, arrayList, this.selectAddress, this.controlName, this.isFromMultisig, this.isFromStakeSuccess, ((VoteSelectSRContract.View) this.mView).getStatAction());
    }

    @Override
    public void reset() {
        List<WitnessOutput.DataBean> data = this.voteItemAdapter.getData();
        for (int i = 0; i < data.size(); i++) {
            data.get(i).setSelected(false);
        }
        this.voteItemAdapter.setNewData(data);
        this.mVotedAddress.clear();
        this.selectedWitnessesList.clear();
        ((VoteSelectSRContract.View) this.mView).onSRSelectedChanged(this.mVotedAddress.size());
    }

    @Override
    protected void showSelectRole() {
        UIUtils.hideSoftKeyBoard((BaseActivity) ((VoteSelectSRContract.View) this.mView).getContext());
    }

    @Override
    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 != 3001 || intent == null) {
            return;
        }
        String stringExtra = intent.getStringExtra(ChangeAddressActivity.CHANGE_ADDRESS_NEW);
        if (StringTronUtil.isEmpty(stringExtra)) {
            return;
        }
        this.selectAddress = stringExtra;
        getData();
    }

    @Override
    public void search(final String str) {
        this.mConstraint = str;
        if (TextUtils.isEmpty(str)) {
            this.showFilter = false;
            this.pageIndex = 0;
            getData();
            return;
        }
        this.showFilter = true;
        this.mRxManager.removeDisposableByKey(FirebaseAnalytics.Event.SEARCH);
        ((VoteSelectSRContract.View) this.mView).showPlaceHolder(true);
        ((VoteSelectSRContract.Model) this.mModel).search(str, this.sortType, this.selectAddress).subscribe(new IObserver(new ICallback<SearchWitnessResult>() {
            @Override
            public void onResponse(String str2, SearchWitnessResult searchWitnessResult) {
                ((VoteSelectSRContract.View) mView).getPl().refreshComplete();
                voteItemAdapter.loadMoreComplete();
                if (searchWitnessResult != null) {
                    if (searchWitnessResult.getData() != null && searchWitnessResult.getData().getData() != null) {
                        voteItemAdapter.updateSearchFilter(showFilter, str);
                        if (searchWitnessResult.getData() != null && searchWitnessResult.getData().getData().size() > 0) {
                            for (int i = 0; i < searchWitnessResult.getData().getData().size(); i++) {
                                WitnessOutput.DataBean dataBean = searchWitnessResult.getData().getData().get(i);
                                if (mVotedAddress.contains(dataBean.getAddress())) {
                                    dataBean.setSelected(true);
                                }
                            }
                        }
                        if (searchWitnessResult.getData() != null && searchWitnessResult.getData().getData().size() > 0) {
                            for (int i2 = 0; i2 < searchWitnessResult.getData().getData().size(); i2++) {
                                WitnessOutput.DataBean dataBean2 = searchWitnessResult.getData().getData().get(i2);
                                if (mVotes.containsKey(dataBean2.getAddress())) {
                                    dataBean2.setSelected(true);
                                    if (!mVotedAddress.contains(dataBean2.getAddress())) {
                                        mVotedAddress.add(dataBean2.getAddress());
                                        if (!selectedWitnessesList.contains(dataBean2)) {
                                            selectedWitnessesList.add(dataBean2);
                                        }
                                    }
                                }
                            }
                        }
                        ((VoteSelectSRContract.View) mView).showPlaceHolder(false);
                        voteItemAdapter.notifySearchData(searchWitnessResult.getData().getData());
                        if (searchWitnessResult.getData().getData().isEmpty()) {
                            ((VoteSelectSRContract.View) mView).showOrHideNoData(true);
                            voteItemAdapter.loadMoreEnd(true);
                        } else {
                            ((VoteSelectSRContract.View) mView).showOrHideNoData(false);
                            voteItemAdapter.loadMoreEnd(true);
                        }
                        ((VoteSelectSRContract.View) mView).getRv().scrollToPosition(0);
                        return;
                    }
                    onFailure("", "");
                }
            }

            @Override
            public void onFailure(String str2, String str3) {
                voteItemAdapter.updateSearchFilter(showFilter, str);
                voteItemAdapter.notifySearchData(new ArrayList());
                ((VoteSelectSRContract.View) mView).getPl().refreshComplete();
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(FirebaseAnalytics.Event.SEARCH, disposable);
            }
        }, "BatchVote-search"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        clear();
    }
}
