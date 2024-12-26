package com.tron.wallet.business.vote.component;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.wallet.business.mutil.bean.MultiSignPermissionData;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.business.vote.component.VoteSelectSRContract;
import com.tron.wallet.business.vote.fastvote.FastVoteActivity;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.components.VoteSortPopupView;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayout;
import com.tron.wallet.common.config.CommonBundleKeys;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.DataStatHelper;
import com.tron.wallet.common.utils.KeyboardUtil;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.SpannableUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TextDotUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.ActivityVoteSelectSrBinding;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.List;
import org.tron.protos.Protocol;
public class VoteSelectSRActivity extends BaseActivity<VoteSelectSRPresenter, VoteSelectSRModel> implements VoteSelectSRContract.View {
    private Protocol.Account account;
    private ActivityVoteSelectSrBinding binding;
    Button btnNext;
    EditText etSearch;
    ImageView ivClear;
    ImageView ivSort;
    LinearLayout liClearAll;
    LinearLayout liNoEnoughVote;
    NoNetView mNoNetView;
    View placeHolderView;
    PtrHTFrameLayout ptrHTFrameLayout;
    RecyclerView recyclerView;
    RelativeLayout rlNoda;
    private String selectAddress;
    private String selectName;
    private ArrayList<WitnessOutput.DataBean> top3APYWitness;
    private long totalVotingRights;
    TextView tvCancel;
    TextView tvMultiSignWarning;
    TextView tvSelectedCount;
    TextView tvVotesCount;
    private ArrayList<WitnessOutput.DataBean> witnesses;
    private ArrayList<WitnessOutput.DataBean> witnessesAlreadyList;
    private int sortType = 5;
    private boolean isFromMultisig = false;
    private boolean isFromStakeSuccess = false;
    private boolean filterMyVotes = false;
    private DataStatHelper.StatAction statAction = DataStatHelper.StatAction.Vote;
    private NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
        @Override
        protected void onNoDoubleClick(View view) {
            switch (view.getId()) {
                case R.id.bt_next:
                    AnalyticsHelper.logEvent(getLogMultiSignTag() + 3);
                    if (StringTronUtil.isEmpty(tvSelectedCount.getText().toString())) {
                        return;
                    }
                    int parseInt = Integer.parseInt(tvSelectedCount.getText().toString());
                    if (((VoteSelectSRPresenter) mPresenter).isLedger() && parseInt > 5) {
                        VoteSelectSRActivity voteSelectSRActivity = VoteSelectSRActivity.this;
                        voteSelectSRActivity.toast(voteSelectSRActivity.getString(R.string.vote_ledger_less_than_five));
                        return;
                    } else if (parseInt > 30) {
                        VoteSelectSRActivity voteSelectSRActivity2 = VoteSelectSRActivity.this;
                        voteSelectSRActivity2.toast(voteSelectSRActivity2.getString(R.string.vote_sr_less_than_30));
                        return;
                    } else if (totalVotingRights < parseInt) {
                        showNoEnoughVotes(true);
                        btnNext.setEnabled(false);
                        return;
                    } else {
                        ((VoteSelectSRPresenter) mPresenter).vote();
                        return;
                    }
                case R.id.iv_clear:
                    etSearch.setText("");
                    return;
                case R.id.iv_sort:
                    showSortDialog();
                    return;
                case R.id.li_clear_all:
                    ((VoteSelectSRPresenter) mPresenter).reset();
                    AnalyticsHelper.logEvent(getLogMultiSignTag() + 2);
                    return;
                case R.id.tv_cancel:
                    etSearch.setText("");
                    etSearch.clearFocus();
                    KeyboardUtil.closeKeybord(VoteSelectSRActivity.this);
                    return;
                default:
                    return;
            }
        }
    };

    public String getLogMultiSignTag() {
        return this.isFromMultisig ? AnalyticsHelper.VoteSelectSR.MULTI_SIGN : AnalyticsHelper.VoteSelectSR.SINGLE_SIGN;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public EditText getEtSearch() {
        return this.etSearch;
    }

    @Override
    public ImageView getIvClear() {
        return this.ivClear;
    }

    @Override
    public ImageView getIvSort() {
        return this.ivSort;
    }

    @Override
    public PtrHTFrameLayout getPl() {
        return this.ptrHTFrameLayout;
    }

    @Override
    public RecyclerView getRv() {
        return this.recyclerView;
    }

    @Override
    public DataStatHelper.StatAction getStatAction() {
        return this.statAction;
    }

    @Override
    public TextView getTotalVotes() {
        return this.tvVotesCount;
    }

    @Override
    public TextView getTvCancelSearch() {
        return this.tvCancel;
    }

    @Override
    public TextView getVoteSRCount() {
        return this.tvSelectedCount;
    }

    public static void start(Context context, Protocol.Account account, boolean z, String str, String str2, MultiSignPermissionData multiSignPermissionData, int i, long j, DataStatHelper.StatAction statAction) {
        start(context, account, z, str, str2, multiSignPermissionData, i, j, false, statAction);
    }

    public static void start(Context context, Protocol.Account account, boolean z, String str, String str2, MultiSignPermissionData multiSignPermissionData, int i, long j, boolean z2, DataStatHelper.StatAction statAction) {
        Intent intent = new Intent(context, VoteSelectSRActivity.class);
        intent.putExtra("key_account", account);
        intent.putExtra("key_controller_address", str);
        intent.putExtra("key_controller_name", str2);
        intent.putExtra(CommonBundleKeys.KEY_FROM_MULTI_SIGN, z);
        intent.putExtra(CommonBundleKeys.KEY_PERMISSION_DATA, multiSignPermissionData);
        intent.putExtra(CommonBundleKeys.KEY_SORT_INDEX, i);
        intent.putExtra(CommonBundleKeys.KEY_ALL_MY_VOTE_RIGHTS, j);
        intent.putExtra(CommonBundleKeys.KEY_IS_FROM_STAKE_SUCCESS, z2);
        intent.putExtra(TronConfig.StatAction_Key, statAction);
        context.startActivity(intent);
    }

    public static void start(Context context, Protocol.Account account, boolean z, String str, String str2, List<WitnessOutput.DataBean> list, List<WitnessOutput.DataBean> list2, List<WitnessOutput.DataBean> list3, MultiSignPermissionData multiSignPermissionData, boolean z2, int i, long j, DataStatHelper.StatAction statAction) {
        Intent intent = new Intent(context, VoteSelectSRActivity.class);
        intent.putExtra("key_account", account);
        intent.putExtra("key_controller_address", str);
        intent.putExtra("key_controller_name", str2);
        intent.putExtra(CommonBundleKeys.KEY_FROM_MULTI_SIGN, z);
        intent.putExtra(CommonBundleKeys.KEY_PERMISSION_DATA, multiSignPermissionData);
        intent.putParcelableArrayListExtra(CommonBundleKeys.KEY_ALREADY_VOTED_WITNESSES, (ArrayList) list3);
        intent.putParcelableArrayListExtra(CommonBundleKeys.KEY_SELECTED_WITNESSES, (ArrayList) list);
        intent.putParcelableArrayListExtra(CommonBundleKeys.KEY_APY_TOP3_WITNESSES, (ArrayList) list2);
        intent.putExtra(CommonBundleKeys.KEY_SORT_INDEX, i);
        intent.putExtra(CommonBundleKeys.KEY_ALL_MY_VOTE_RIGHTS, j);
        intent.putExtra(TronConfig.StatAction_Key, statAction);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        ActivityVoteSelectSrBinding inflate = ActivityVoteSelectSrBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 1);
        mappingId();
        setHeaderBar(getResources().getString(R.string.vote));
        getHeaderHolder().ivCommonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$setLayout$0(view);
            }
        });
        setCommonTitle2(getString(R.string.step_1_2));
        getHeaderHolder().tvCommonTitle.setTextSize(20.0f);
        getHeaderHolder().tvCommonRight.setVisibility(View.GONE);
        setCommonRight2(getString(R.string.vote_easy));
        getHeaderHolder().tvCommonRight2.setPadding(UIUtils.dip2px(16.0f), UIUtils.dip2px(5.0f), UIUtils.dip2px(16.0f), UIUtils.dip2px(5.0f));
        getHeaderHolder().tvCommonRight2.setTextColor(getResources().getColor(R.color.white));
        getHeaderHolder().tvCommonRight2.setBackgroundResource(R.drawable.roundborder_232c41);
        getHeaderHolder().tvCommonRight2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (top3APYWitness == null) {
                    top3APYWitness = new ArrayList();
                    if (witnesses != null && witnesses != null && witnesses.size() > 3) {
                        for (int i = 0; i < 3; i++) {
                            top3APYWitness.add((WitnessOutput.DataBean) witnesses.get(i));
                        }
                    }
                }
                AnalyticsHelper.logEvent(getLogMultiSignTag() + 4);
                VoteSelectSRActivity voteSelectSRActivity = VoteSelectSRActivity.this;
                FastVoteActivity.startActivity(voteSelectSRActivity, voteSelectSRActivity.account, top3APYWitness, 0, ((VoteSelectSRPresenter) mPresenter).getMyAvailableVotes(), ((VoteSelectSRPresenter) mPresenter).getTotalVotes(), ((VoteSelectSRPresenter) mPresenter).getVotedWitnesses(), selectAddress, selectName, isFromMultisig, statAction);
            }
        });
    }

    public void lambda$setLayout$0(View view) {
        onLeftButtonClick();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.etSearch = this.binding.etSearch;
        this.liNoEnoughVote = this.binding.liNoEnoughVote;
        this.ivSort = this.binding.ivSort;
        this.ivClear = this.binding.ivClear;
        this.tvCancel = this.binding.tvCancel;
        this.ptrHTFrameLayout = this.binding.plFrame;
        this.recyclerView = this.binding.rcList;
        this.placeHolderView = this.binding.placeHolderView;
        this.tvSelectedCount = this.binding.tvSelectedCount;
        this.liClearAll = this.binding.liClearAll;
        this.tvVotesCount = this.binding.voteCount;
        this.btnNext = this.binding.btNext;
        this.rlNoda = this.binding.rlNoda;
        this.mNoNetView = this.binding.llError;
        this.tvMultiSignWarning = this.binding.tvMultiWarning;
    }

    @Override
    protected void processData() {
        this.account = (Protocol.Account) getIntent().getSerializableExtra("key_account");
        this.selectAddress = getIntent().getStringExtra("key_controller_address");
        this.witnesses = getIntent().getParcelableArrayListExtra(CommonBundleKeys.KEY_SELECTED_WITNESSES);
        this.witnessesAlreadyList = getIntent().getParcelableArrayListExtra(CommonBundleKeys.KEY_ALREADY_VOTED_WITNESSES);
        this.top3APYWitness = getIntent().getParcelableArrayListExtra(CommonBundleKeys.KEY_APY_TOP3_WITNESSES);
        this.isFromMultisig = getIntent().getBooleanExtra(CommonBundleKeys.KEY_FROM_MULTI_SIGN, false);
        this.isFromStakeSuccess = getIntent().getBooleanExtra(CommonBundleKeys.KEY_IS_FROM_STAKE_SUCCESS, false);
        this.totalVotingRights = getIntent().getLongExtra(CommonBundleKeys.KEY_ALL_MY_VOTE_RIGHTS, 0L);
        this.statAction = (DataStatHelper.StatAction) getIntent().getSerializableExtra(TronConfig.StatAction_Key);
        if (this.totalVotingRights < 3 || ((VoteSelectSRPresenter) this.mPresenter).getVotedWitnesses() == null) {
            getHeaderHolder().tvCommonRight2.setVisibility(View.GONE);
        }
        showPlaceHolder(true);
        ((VoteSelectSRPresenter) this.mPresenter).addSome(this.selectAddress, this.account, this.witnesses);
        this.mNoNetView.setOnReloadClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$processData$1(view);
            }
        });
        if (this.isFromMultisig) {
            setHeaderBar(getResources().getString(R.string.multi_sign_vote_title));
        }
        this.tvSelectedCount.setText("0");
        if (this.isFromMultisig) {
            String stringExtra = getIntent().getStringExtra("key_controller_name");
            this.selectName = stringExtra;
            final String str = this.selectAddress;
            if (!TextUtils.isEmpty(stringExtra)) {
                str = String.format("%s (%s)", this.selectName, str);
            }
            this.tvMultiSignWarning.setText(getString(R.string.multi_vote_controller_tips, new Object[]{str}));
            this.tvMultiSignWarning.post(new Runnable() {
                @Override
                public final void run() {
                    lambda$processData$2(str);
                }
            });
            this.tvMultiSignWarning.setVisibility(View.VISIBLE);
        }
        ArrayList<WitnessOutput.DataBean> arrayList = this.top3APYWitness;
        if (arrayList == null || arrayList.size() < 3) {
            getHeaderHolder().tvCommonRight2.setVisibility(View.GONE);
            ((VoteSelectSRPresenter) this.mPresenter).getTop3Data();
        }
        this.liNoEnoughVote.setVisibility(View.GONE);
        addClickListener();
    }

    public void lambda$processData$1(View view) {
        ((VoteSelectSRPresenter) this.mPresenter).addSome(this.selectAddress, this.account, null);
    }

    public void lambda$processData$2(String str) {
        String[] autoSplitText = TextDotUtils.autoSplitText(this.tvMultiSignWarning, str);
        this.tvMultiSignWarning.setText(SpannableUtils.getTextColorSpannable(autoSplitText[0], autoSplitText[1], getResources().getColor(R.color.black_23)));
    }

    public void addClickListener() {
        this.liClearAll.setOnClickListener(this.noDoubleClickListener);
        this.btnNext.setOnClickListener(this.noDoubleClickListener);
        this.tvCancel.setOnClickListener(this.noDoubleClickListener);
        this.ivSort.setOnClickListener(this.noDoubleClickListener);
        this.ivClear.setOnClickListener(this.noDoubleClickListener);
    }

    @Override
    public void showLoading(boolean z) {
        if (isDestroyed()) {
            return;
        }
        if (z) {
            showLoadingDialog(getString(R.string.loading));
        } else {
            dismissLoadingDialog();
        }
    }

    @Override
    public void showOrHideNoData(boolean z) {
        if (z) {
            this.rlNoda.setVisibility(View.VISIBLE);
        } else {
            this.rlNoda.setVisibility(View.GONE);
        }
    }

    @Override
    public void showOrHideNoNet(boolean z) {
        this.mNoNetView.updateLoadingState(true);
        NoNetView noNetView = this.mNoNetView;
        if (noNetView != null) {
            if (z) {
                if (noNetView.getVisibility() == 0) {
                    return;
                }
                this.mNoNetView.setVisibility(View.VISIBLE);
            } else if (noNetView.getVisibility() == 8) {
            } else {
                this.mNoNetView.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onSRSelectedChanged(int i) {
        TextView textView = this.tvSelectedCount;
        textView.setText(i + "");
        if (((VoteSelectSRPresenter) this.mPresenter).isLedger() && i > 5) {
            toast(getString(R.string.vote_ledger_less_than_five));
        }
        if (i > 30) {
            toast(getString(R.string.vote_sr_less_than_30));
        }
        if (this.totalVotingRights != ((VoteSelectSRPresenter) this.mPresenter).getTotalVotes()) {
            this.totalVotingRights = ((VoteSelectSRPresenter) this.mPresenter).getTotalVotes();
        }
        long j = this.totalVotingRights;
        if (j < 1 || j < i) {
            this.btnNext.setEnabled(false);
            showNoEnoughVotes(true);
            return;
        }
        showNoEnoughVotes(false);
        if (i > 0) {
            this.liClearAll.setEnabled(true);
            this.btnNext.setEnabled(true);
            return;
        }
        this.liClearAll.setEnabled(false);
        this.btnNext.setEnabled(false);
    }

    @Override
    public void updateBtnEnabled(boolean z) {
        this.btnNext.setEnabled(z);
    }

    @Override
    public void showPlaceHolder(boolean z) {
        if (z) {
            this.recyclerView.setVisibility(View.GONE);
            this.placeHolderView.setVisibility(View.VISIBLE);
            this.rlNoda.setVisibility(View.GONE);
            return;
        }
        this.placeHolderView.setVisibility(View.GONE);
        this.rlNoda.setVisibility(View.GONE);
        this.recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoEnoughVotes(boolean z) {
        this.liNoEnoughVote.setVisibility(z ? View.VISIBLE : View.GONE);
        if (z) {
            this.btnNext.setEnabled(false);
        }
    }

    @Override
    public void updateAlreadyVotedList() {
        if (((VoteSelectSRPresenter) this.mPresenter).getVotedWitnesses() == null || this.top3APYWitness == null || this.totalVotingRights < 3) {
            return;
        }
        getHeaderHolder().tvCommonRight2.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateTop3ApyList(List<WitnessOutput.DataBean> list) {
        if (list != null) {
            this.top3APYWitness = new ArrayList<>();
            if (list.size() > 3) {
                this.top3APYWitness.addAll(list.subList(0, 3));
            } else {
                this.top3APYWitness.addAll(list);
            }
            if (((VoteSelectSRPresenter) this.mPresenter).getVotedWitnesses() == null || this.totalVotingRights < 3) {
                return;
            }
            getHeaderHolder().tvCommonRight2.setVisibility(View.VISIBLE);
        }
    }

    public void showSortDialog() {
        VoteSortPopupView.showUp(this, this.sortType, this.filterMyVotes, ((VoteSelectSRPresenter) this.mPresenter).myVotedCount(), new VoteSortPopupView.OnSelectChangedListener() {
            @Override
            public final void onSelectChanged(boolean z, int i) {
                lambda$showSortDialog$3(z, i);
            }
        });
    }

    public void lambda$showSortDialog$3(boolean z, int i) {
        if (this.mPresenter == 0) {
            return;
        }
        this.filterMyVotes = z;
        this.sortType = i;
        ((VoteSelectSRPresenter) this.mPresenter).sortRefresh(this.sortType, this.filterMyVotes);
    }

    @Override
    public void onLeftButtonClick() {
        AnalyticsHelper.logEvent(getLogMultiSignTag() + 0);
        finish();
    }

    @Override
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            AnalyticsHelper.logEvent(getLogMultiSignTag() + 0);
        }
        return super.onKeyDown(i, keyEvent);
    }
}
