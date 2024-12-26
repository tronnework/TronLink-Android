package com.tron.wallet.business.vote.component;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.wallet.business.mutil.bean.MultiSignPermissionData;
import com.tron.wallet.business.vote.adapter.VoteSelectSRAdapter;
import com.tron.wallet.business.vote.adapter.WitnessListAdapter;
import com.tron.wallet.business.vote.bean.SearchWitnessResult;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.business.vote.component.VoteSRSearchContract;
import com.tron.wallet.business.vote.superdetail.SuperDetailActivity;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.common.config.CommonBundleKeys;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.interfaces.VoteSRSelectedChangeListener;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.KeyboardUtil;
import com.tron.wallet.common.utils.ToastUtil;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class VoteSRSearchPresenter extends VoteSRSearchContract.Presenter {
    public long availableVoteRight;
    private String constraint;
    private int dataType;
    public Protocol.Account mAccount;
    private Handler mHandler;
    private WitnessOutput mTopWitnessOutput;
    private List<WitnessOutput.DataBean> mTopWitnessesList;
    private HashMap<String, String> mVotes;
    private List<WitnessOutput.DataBean> mWitnessesList;
    private ArrayList<WitnessOutput.DataBean> myVotedWitness;
    private MultiSignPermissionData permissionData;
    private VoteSelectSRAdapter searchAdapter;
    public String selectAddress;
    public String selectedName;
    private Wallet selectedWallet;
    private boolean showFilter;
    private volatile VoteSRSearchContract.Presenter.State state;
    private TextWatcher textWatcher;
    private WitnessListAdapter topWitnessAdapter;
    public long totalVotingRights;
    private WitnessOutput witnessOutput;
    int pageSize = 20;
    volatile int pageIndex = 0;
    int sortType = 6;
    int hasAll = 0;
    private boolean hasPending = false;
    public volatile boolean shouldUpdateAccount = false;

    @Override
    public String getCurrentAddress() {
        return this.selectAddress;
    }

    @Override
    public VoteSRSearchContract.Presenter.State getState() {
        return this.state;
    }

    @Override
    public void addSome(final Protocol.Account account, final String str, ArrayList<WitnessOutput.DataBean> arrayList, final boolean z) {
        this.mTopWitnessesList = arrayList;
        this.selectAddress = str;
        this.mAccount = account;
        WitnessListAdapter witnessListAdapter = new WitnessListAdapter(((VoteSRSearchContract.View) this.mView).getIContext());
        this.topWitnessAdapter = witnessListAdapter;
        witnessListAdapter.bindToRecyclerView(((VoteSRSearchContract.View) this.mView).getTopSRRecycleView());
        ((VoteSRSearchContract.View) this.mView).getTopSRRecycleView().setLayoutManager(new WrapContentLinearLayoutManager(((VoteSRSearchContract.View) this.mView).getIContext()));
        ((VoteSRSearchContract.View) this.mView).getTopSRRecycleView().setAdapter(this.topWitnessAdapter);
        this.topWitnessAdapter.updateData(false, this.mTopWitnessesList);
        this.topWitnessAdapter.setOnItemClickListener(new WitnessListAdapter.DebouncedItemClickListener() {
            @Override
            public void onDebouncedClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                SuperDetailActivity.start(((VoteSRSearchContract.View) mView).getIContext(), account, mTopWitnessOutput, BigDecimalUtils.toBigDecimal(((WitnessOutput.DataBean) mTopWitnessesList.get(i)).getVoted()).intValue(), totalVotingRights, permissionData, (WitnessOutput.DataBean) mTopWitnessesList.get(i), myVotedWitness, (int) ((WitnessOutput.DataBean) mTopWitnessesList.get(i)).getRealTimeRanking(), selectedName, str, z, totalVotingRights > 0, ((VoteSRSearchContract.View) mView).getStatAction());
            }
        });
        ((VoteSRSearchContract.View) this.mView).getSearchEt().addTextChangedListener(this.textWatcher);
        ((VoteSRSearchContract.View) this.mView).getSearchEt().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean z2) {
                if (z2 && TextUtils.isEmpty(((VoteSRSearchContract.View) mView).getSearchEt().getText())) {
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(0), 2L);
                }
            }
        });
        ((VoteSRSearchContract.View) this.mView).getSearchEt().requestFocus();
        ((VoteSRSearchContract.View) this.mView).getIvClear().setVisibility(View.GONE);
        KeyboardUtil.showSoftInput(((VoteSRSearchContract.View) this.mView).getIContext(), ((VoteSRSearchContract.View) this.mView).getSearchEt());
        if (z) {
            this.selectedName = ((Activity) ((VoteSRSearchContract.View) this.mView).getIContext()).getIntent().getStringExtra("key_controller_name");
            this.permissionData = (MultiSignPermissionData) ((Activity) ((VoteSRSearchContract.View) this.mView).getIContext()).getIntent().getParcelableExtra(CommonBundleKeys.KEY_PERMISSION_DATA);
        }
        ((VoteSRSearchContract.View) this.mView).getRecycleView().setLayoutManager(new LinearLayoutManager(((VoteSRSearchContract.View) this.mView).getIContext(), 1, false));
        Wallet walletForAddress = WalletUtils.getWalletForAddress(str);
        VoteSelectSRAdapter voteSelectSRAdapter = new VoteSelectSRAdapter(((VoteSRSearchContract.View) this.mView).getIContext(), this.mAccount, str, false, walletForAddress == null ? false : walletForAddress.isLedgerHDWallet(), this.mWitnessesList, new ArrayList(), new VoteSRSelectedChangeListener() {
            @Override
            public void onVoteSRSelected(String str2, WitnessOutput.DataBean dataBean, boolean z2) {
            }

            @Override
            public void onVoteSRUnSelected(String str2, WitnessOutput.DataBean dataBean) {
            }

            @Override
            public void onVoteSRClicked(String str2, WitnessOutput.DataBean dataBean) {
                SuperDetailActivity.start(((VoteSRSearchContract.View) mView).getIContext(), account, witnessOutput, BigDecimalUtils.toBigDecimal(dataBean.getVoted()).intValue(), totalVotingRights, permissionData, dataBean, myVotedWitness, (int) dataBean.getRealTimeRanking(), selectedName, str, z, totalVotingRights > 0, ((VoteSRSearchContract.View) mView).getStatAction());
            }
        });
        this.searchAdapter = voteSelectSRAdapter;
        voteSelectSRAdapter.updateData(false, this.mWitnessesList);
        this.searchAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public final void onLoadMoreRequested() {
                lambda$addSome$0();
            }
        }, ((VoteSRSearchContract.View) this.mView).getRecycleView());
        ((VoteSRSearchContract.View) this.mView).getRecycleView().setAdapter(this.searchAdapter);
        List<WitnessOutput.DataBean> list = this.mTopWitnessesList;
        if (list == null || list.isEmpty()) {
            getWitnessList();
        }
    }

    @Override
    protected void onStart() {
        this.mWitnessesList = new ArrayList();
        this.mVotes = new HashMap<>();
        this.textWatcher = new TextWatcher() {
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
                    ((VoteSRSearchContract.View) mView).getIvClear().setVisibility(View.VISIBLE);
                } else {
                    ((VoteSRSearchContract.View) mView).getIvClear().setVisibility(View.GONE);
                }
                if (editable.toString().equals(this.lastInput)) {
                    return;
                }
                this.lastInput = editable.toString();
                Message obtainMessage = mHandler.obtainMessage(1);
                obtainMessage.obj = editable.toString();
                mHandler.sendMessageDelayed(obtainMessage, 2L);
            }
        };
        this.mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (mView == 0) {
                    return;
                }
                if (message.what == 1) {
                    if ((message.obj instanceof String) && TextUtils.equals((CharSequence) message.obj, ((VoteSRSearchContract.View) mView).getSearchEt().getText().toString())) {
                        search(message.obj.toString().toLowerCase().trim());
                    }
                } else if (message.what != 0 || mView == 0) {
                } else {
                    ((VoteSRSearchContract.View) mView).beforeLoad();
                }
            }
        };
        this.mRxManager.on(Event.BackToVoteHome, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$1(obj);
            }
        });
        this.mRxManager.on(Event.BroadcastSuccess, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$2(obj);
            }
        });
        if (((VoteSRSearchContract.View) this.mView).getIContext() instanceof Activity) {
            this.availableVoteRight = ((Activity) ((VoteSRSearchContract.View) this.mView).getIContext()).getIntent().getLongExtra(CommonBundleKeys.KEY_MY_AVAILABEL_VOTE, 0L);
            this.totalVotingRights = ((Activity) ((VoteSRSearchContract.View) this.mView).getIContext()).getIntent().getLongExtra(CommonBundleKeys.KEY_ALL_MY_VOTE_RIGHTS, 0L);
            this.myVotedWitness = ((Activity) ((VoteSRSearchContract.View) this.mView).getIContext()).getIntent().getParcelableArrayListExtra(CommonBundleKeys.KEY_ALREADY_VOTED_WITNESSES);
        }
    }

    public void lambda$onStart$1(Object obj) throws Exception {
        if (this.mView != 0) {
            ((VoteSRSearchContract.View) this.mView).exit();
        }
    }

    public void lambda$onStart$2(Object obj) throws Exception {
        if (this.mView != 0) {
            ((VoteSRSearchContract.View) this.mView).exit();
        }
    }

    public void search(final String str) {
        this.constraint = str;
        this.showFilter = true;
        if (TextUtils.isEmpty(str.trim()) || TextUtils.isEmpty(((VoteSRSearchContract.View) this.mView).getSearchEt().getText().toString().trim())) {
            this.showFilter = false;
            this.pageIndex = 0;
            this.mWitnessesList.clear();
            this.searchAdapter.updateSearchFilter(false, "");
            setState(VoteSRSearchContract.Presenter.State.STATE_IDLE);
            getData();
        } else if (!TronConfig.hasNet) {
            ((VoteSRSearchContract.View) this.mView).showNoNetError(true);
        } else {
            setState(VoteSRSearchContract.Presenter.State.PLACE_HOLDER);
            ((VoteSRSearchContract.Model) this.mModel).search(str, this.sortType, this.selectAddress).subscribe(new IObserver(new ICallback<SearchWitnessResult>() {
                @Override
                public void onResponse(String str2, SearchWitnessResult searchWitnessResult) {
                    if (searchWitnessResult == null || searchWitnessResult.getData() == null || searchWitnessResult.getData().getData() == null || TextUtils.isEmpty(((VoteSRSearchContract.View) mView).getSearchEt().getText().toString())) {
                        return;
                    }
                    witnessOutput = searchWitnessResult.getData();
                    mWitnessesList.clear();
                    VoteSRSearchPresenter voteSRSearchPresenter = VoteSRSearchPresenter.this;
                    voteSRSearchPresenter.mWitnessesList = voteSRSearchPresenter.witnessOutput.getData();
                    searchAdapter.updateSearchFilter(true, str.toLowerCase());
                    setState(VoteSRSearchContract.Presenter.State.NORMAL);
                    updateUI(true, 2);
                }

                @Override
                public void onFailure(String str2, String str3) {
                    updateUI(false, 2);
                    setState(VoteSRSearchContract.Presenter.State.ERROR);
                }

                @Override
                public void onSubscribe(Disposable disposable) {
                    mRxManager.add(disposable);
                }
            }, "VoteContentPresenter-search"));
        }
    }

    public void getData() {
        if (TextUtils.isEmpty(((VoteSRSearchContract.View) this.mView).getSearchEt().getText().toString())) {
            WitnessListAdapter witnessListAdapter = this.topWitnessAdapter;
            if (witnessListAdapter != null && witnessListAdapter.getData() != null && this.topWitnessAdapter.getData().size() > 0) {
                setState(VoteSRSearchContract.Presenter.State.STATE_IDLE);
                return;
            } else {
                getWitnessList();
                return;
            }
        }
        search(((VoteSRSearchContract.View) this.mView).getSearchEt().getText().toString());
    }

    public void getWitnessList() {
        setState(VoteSRSearchContract.Presenter.State.PLACE_HOLDER);
        ((VoteSRSearchContract.Model) this.mModel).getWitnessList(3, 1, this.hasAll, 7, this.selectAddress).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                Observable just;
                just = Observable.just((WitnessOutput) obj);
                return just;
            }
        }).subscribe(new IObserver(new ICallback<WitnessOutput>() {
            @Override
            public void onResponse(String str, WitnessOutput witnessOutput) {
                if (witnessOutput != null) {
                    mTopWitnessOutput = witnessOutput;
                    mTopWitnessesList.clear();
                    mTopWitnessesList.addAll(witnessOutput.getData());
                    updateUI(true);
                    if (!mWitnessesList.isEmpty()) {
                        topWitnessAdapter.loadMoreComplete();
                        pageIndex++;
                    } else {
                        topWitnessAdapter.loadMoreEnd();
                    }
                    setState(VoteSRSearchContract.Presenter.State.STATE_IDLE);
                }
            }

            @Override
            public void onFailure(String str, String str2) {
                mTopWitnessesList.clear();
                ToastUtil.getInstance().show(((VoteSRSearchContract.View) mView).getIContext(), ((VoteSRSearchContract.View) mView).getIContext().getResources().getString(R.string.time_out));
                updateUI(false);
                topWitnessAdapter.loadMoreFail();
                setState(VoteSRSearchContract.Presenter.State.ERROR);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "VoteContentPresenter-getWitnessList"));
    }

    public void updateUI(boolean z) {
        updateUI(z, 1);
    }

    public void updateUI(boolean z, int i) {
        ((VoteSRSearchContract.View) this.mView).getRecycleView().getScrollState();
        ((VoteSRSearchContract.View) this.mView).showNoNetError(false);
        if (((VoteSRSearchContract.View) this.mView).getRecycleView().getVisibility() != 0) {
            ((VoteSRSearchContract.View) this.mView).getRecycleView().setVisibility(View.VISIBLE);
        }
        if (i == 1) {
            if (isSearching()) {
                return;
            }
            this.topWitnessAdapter.updateData(true, this.mTopWitnessesList);
            if (this.pageIndex <= 0 || this.mTopWitnessesList.size() > 0) {
                ((VoteSRSearchContract.View) this.mView).updateUI(this.topWitnessAdapter.getData().size() > 0, z, this.topWitnessAdapter.getData(), null);
            }
        } else if (i == 2) {
            this.searchAdapter.notifyData(this.mVotes, this.mWitnessesList, true);
            ((VoteSRSearchContract.View) this.mView).updateSearchUI(z, this.mWitnessesList.isEmpty());
        }
        this.hasPending = false;
    }

    @Override
    public void setState(VoteSRSearchContract.Presenter.State state) {
        this.state = state;
        ((VoteSRSearchContract.View) this.mView).updateState(state);
    }

    @Override
    void getData(int i, int i2) {
        this.dataType = i;
        this.sortType = i2;
        getData();
    }

    @Override
    void refresh() {
        if (!TextUtils.isEmpty(this.constraint)) {
            search(this.constraint);
        } else {
            refreshWithSort(true);
        }
    }

    @Override
    public void lambda$addSome$0() {
        if (getState() == VoteSRSearchContract.Presenter.State.STATE_LOADING) {
            return;
        }
        if (TextUtils.isEmpty(((VoteSRSearchContract.View) this.mView).getSearchEt().getText().toString())) {
            fixPageSize();
            getWitnessList();
            return;
        }
        VoteSelectSRAdapter voteSelectSRAdapter = this.searchAdapter;
        if (voteSelectSRAdapter != null) {
            voteSelectSRAdapter.loadMoreEnd();
        }
    }

    void refreshWithSort(boolean z) {
        if (getState() == VoteSRSearchContract.Presenter.State.STATE_LOADING) {
            return;
        }
        if (!TronConfig.hasNet) {
            ((VoteSRSearchContract.View) this.mView).showNoNetError(true);
            return;
        }
        this.pageIndex = 0;
        if (!z) {
            this.sortType = 3;
        }
        this.mWitnessesList.clear();
        this.showFilter = false;
        getData();
    }

    private boolean isSearching() {
        return !TextUtils.isEmpty(((VoteSRSearchContract.View) this.mView).getSearchEt().getText());
    }

    public void updateSearchViewVisibility(boolean z) {
        EditText searchEt = ((VoteSRSearchContract.View) this.mView).getSearchEt();
        if (searchEt != null && z) {
            if (searchEt.getVisibility() == 0) {
                searchEt.setVisibility(View.GONE);
                searchEt.removeTextChangedListener(this.textWatcher);
                if (TextUtils.isEmpty(searchEt.getText().toString())) {
                    return;
                }
                searchEt.setText("");
                this.showFilter = false;
                this.constraint = "";
                ((VoteSRSearchContract.View) this.mView).beforeLoad();
                refresh();
                return;
            }
            searchEt.setVisibility(View.VISIBLE);
            searchEt.addTextChangedListener(this.textWatcher);
        }
    }

    private void fixPageSize() {
        VoteSelectSRAdapter voteSelectSRAdapter = this.searchAdapter;
        if (voteSelectSRAdapter == null) {
            return;
        }
        int itemCount = voteSelectSRAdapter.getItemCount() - 1;
        int i = this.pageSize;
        int i2 = itemCount / i;
        if (itemCount % i > 0) {
            i2++;
        }
        this.pageIndex = i2;
    }

    private Observable<WitnessOutput> getEmpty() {
        WitnessOutput witnessOutput = new WitnessOutput();
        witnessOutput.setData(new ArrayList());
        return Observable.just(witnessOutput);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacks(null);
        }
    }
}
