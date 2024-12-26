package com.tron.wallet.business.vote.component;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.parambuilder.BaseConfirmTransParamBuilder;
import com.tron.wallet.business.mutil.MultiSelectAddressActivity;
import com.tron.wallet.business.mutil.bean.MultiSignPermissionData;
import com.tron.wallet.business.mutil.bean.MultiSourcePageEnum;
import com.tron.wallet.business.tronpower.stake.StakeTRXActivity;
import com.tron.wallet.business.vote.VoteHeaderFragment;
import com.tron.wallet.business.vote.adapter.WitnessListAdapter;
import com.tron.wallet.business.vote.bean.FastAprBean;
import com.tron.wallet.business.vote.bean.VoteHeaderBean;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.business.vote.component.Contract;
import com.tron.wallet.business.vote.component.VoteMainActivity;
import com.tron.wallet.business.vote.fastvote.FastVoteActivity;
import com.tron.wallet.business.vote.superdetail.SuperDetailActivity;
import com.tron.wallet.common.components.CenterPopupInstructionView;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.components.VoteSortPopupView;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.common.components.ptr.PtrDefaultHandler;
import com.tron.wallet.common.components.ptr.PtrFrameLayout;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayout;
import com.tron.wallet.common.components.ptr.PtrHandler;
import com.tron.wallet.common.config.CommonBundleKeys;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.DataStatHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.OwnerPermissionCheckUtils;
import com.tron.wallet.common.utils.PopupWindowUtil;
import com.tron.wallet.common.utils.SpannableUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TextDotUtils;
import com.tron.wallet.common.utils.VoteAprCalculator;
import com.tron.wallet.databinding.ActivityVoteBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import j$.util.Collection;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.function.Consumer;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class VoteMainActivity extends BaseActivity<VoteMainPresenter, VoteMainModel> implements Contract.View, Contract.OnPullToRefreshCallback {
    private static final String TAG = "VoteActivity";
    private long alreadyVotedNumber;
    private boolean appBarCollapse;
    AppBarLayout appBarLayout;
    View aprTipView;
    private ActivityVoteBinding binding;
    TextView btnCancelAll;
    TextView btnVote;
    private Protocol.Account controllerAccount;
    private String controllerAddress;
    private String controllerName;
    FragmentContainerView fragmentView;
    boolean fromMultiSign;
    private VoteHeaderFragment headerFragment;
    ViewGroup headerLayout;
    View ivBack;
    View ivLoading;
    View ivTips;
    private WitnessListAdapter listAdapter;
    View llSort;
    MultiSignPermissionData permissionData;
    PtrHTFrameLayout ptrView;
    ViewGroup rootView;
    RecyclerView rvContent;
    View searchView;
    View searchViewLayout;
    private Wallet selectWallet;
    private ArrayList<WitnessOutput.DataBean> top3Witnesses;
    private long totalVotingRights;
    TextView tvAprTip;
    TextView tvMainTitle;
    TextView tvMultiSign;
    TextView tvMultiSignWarning;
    private ConfirmCustomPopupView unableCancelDialog;
    private boolean filterMyVotes = false;
    private int sortType = 5;
    private final ArrayList<Contract.OnPullToRefreshCallback> onPullToRefreshCallbacks = new ArrayList<>();
    private boolean reloadAccount = false;
    private DataStatHelper.StatAction statAction = DataStatHelper.StatAction.Vote;
    private boolean scrollToTop = false;
    private final NoDoubleClickListener clickListener = new fun4();

    public String getLogMultiSignTag() {
        return "votersPage_";
    }

    @Override
    public DataStatHelper.StatAction getStatAction() {
        return this.statAction;
    }

    public static void startWithCheckOwnerPermission(final Context context, final Protocol.Account account, final String str) {
        OwnerPermissionCheckUtils.checkWithPopup(context, account, new int[]{R.string.vote_not_activated_popup, R.string.vote_by_multi_sign}, new int[]{R.string.vote_no_owner_permission, R.string.vote_by_multi_sign}, new Consumer() {
            @Override
            public final void accept(Object obj) {
                Void r4 = (Void) obj;
                VoteMainActivity.start(context, account, false, str, null, null);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                Void r2 = (Void) obj;
                MultiSelectAddressActivity.start(context, MultiSourcePageEnum.Vote);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
    }

    public static void start(Context context, Protocol.Account account, boolean z, String str, String str2, MultiSignPermissionData multiSignPermissionData) {
        Intent intent = new Intent(context, VoteMainActivity.class);
        intent.putExtra("key_account", account);
        intent.putExtra("key_controller_address", str);
        intent.putExtra(CommonBundleKeys.KEY_FROM_MULTI_SIGN, z);
        intent.putExtra("key_controller_name", str2);
        intent.putExtra(CommonBundleKeys.KEY_PERMISSION_DATA, multiSignPermissionData);
        context.startActivity(intent);
    }

    public static void startWithCheckOwnerPermissionByFinance(final Context context, final Protocol.Account account, final String str, final DataStatHelper.StatAction statAction) {
        OwnerPermissionCheckUtils.checkWithPopup(context, account, new int[]{R.string.vote_not_activated_popup, R.string.vote_by_multi_sign}, new int[]{R.string.vote_no_owner_permission, R.string.vote_by_multi_sign}, new Consumer() {
            @Override
            public final void accept(Object obj) {
                Void r5 = (Void) obj;
                VoteMainActivity.startByFinance(context, account, false, str, null, null, statAction);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                Void r3 = (Void) obj;
                MultiSelectAddressActivity.start(context, MultiSourcePageEnum.Vote, statAction);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
    }

    public static void startByFinance(Context context, Protocol.Account account, boolean z, String str, String str2, MultiSignPermissionData multiSignPermissionData, DataStatHelper.StatAction statAction) {
        Intent intent = new Intent(context, VoteMainActivity.class);
        intent.putExtra("key_account", account);
        intent.putExtra("key_controller_address", str);
        intent.putExtra(CommonBundleKeys.KEY_FROM_MULTI_SIGN, z);
        intent.putExtra("key_controller_name", str2);
        intent.putExtra(CommonBundleKeys.KEY_PERMISSION_DATA, multiSignPermissionData);
        intent.putExtra(TronConfig.StatAction_Key, statAction);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        ActivityVoteBinding inflate = ActivityVoteBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 0);
        mappingId();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.appBarLayout = this.binding.appbarLayout;
        this.fragmentView = this.binding.frameHeader;
        this.rvContent = this.binding.layoutVoteList.rvContent;
        this.searchView = this.binding.llSearchView.getRoot();
        this.searchViewLayout = this.binding.rlSearch;
        this.tvMultiSign = this.binding.layoutCommonHeader.tvMultiSign;
        this.btnVote = this.binding.layoutCommonHeader.btnVoteToolBar;
        this.headerLayout = this.binding.layoutCommonHeader.headerLayout;
        this.rootView = this.binding.root;
        this.ivLoading = this.binding.layoutVoteList.ivLoading;
        this.ptrView = this.binding.ptrView;
        this.btnCancelAll = this.binding.btnCancelAll;
        this.tvMultiSignWarning = this.binding.tvMultiWarning;
        this.llSort = this.binding.llSort;
        this.ivTips = this.binding.layoutCommonHeader.ivTips;
        this.ivBack = this.binding.layoutCommonHeader.ivBack;
        this.tvMainTitle = this.binding.layoutCommonHeader.tvMainTitle;
        this.aprTipView = this.binding.aprTipLayout;
        this.tvAprTip = this.binding.tvAprTip;
    }

    @Override
    protected void processData() {
        parseIntentArgs();
        initViews();
        initEvents();
        onPullToRefresh();
        gaLog(AnalyticsHelper.VoteMainPage.ENTER_VOTE_HOME);
    }

    private void parseIntentArgs() {
        this.controllerAccount = (Protocol.Account) getIntent().getSerializableExtra("key_account");
        this.fromMultiSign = getIntent().getBooleanExtra(CommonBundleKeys.KEY_FROM_MULTI_SIGN, false);
        this.controllerAddress = getIntent().getStringExtra("key_controller_address");
        this.permissionData = (MultiSignPermissionData) getIntent().getParcelableExtra(CommonBundleKeys.KEY_PERMISSION_DATA);
        this.statAction = (DataStatHelper.StatAction) getIntent().getSerializableExtra(TronConfig.StatAction_Key);
        if (this.fromMultiSign) {
            ((VoteMainPresenter) this.mPresenter).mRxManager.post(Event.VOTE_TO_MULTI_VOTE, "");
        }
        this.selectWallet = WalletUtils.getSelectedPublicWallet();
        if (!this.fromMultiSign && TextUtils.isEmpty(this.controllerAddress)) {
            Wallet wallet = this.selectWallet;
            this.controllerAddress = wallet != null ? wallet.getAddress() : "";
        } else if (this.fromMultiSign) {
            String stringExtra = getIntent().getStringExtra("key_controller_name");
            this.controllerName = stringExtra;
            final String str = this.controllerAddress;
            if (!TextUtils.isEmpty(stringExtra)) {
                str = String.format("%s (%s)", this.controllerName, str);
            }
            this.tvMultiSignWarning.setText(getString(R.string.multi_vote_controller_tips, new Object[]{str}));
            this.tvMultiSignWarning.post(new Runnable() {
                @Override
                public final void run() {
                    lambda$parseIntentArgs$4(str);
                }
            });
            this.tvMultiSignWarning.setVisibility(View.VISIBLE);
            this.tvMultiSign.setVisibility(View.GONE);
            this.tvMainTitle.setText(R.string.vote_title_main_multisign);
            this.ivTips.setVisibility(View.GONE);
        }
    }

    public void lambda$parseIntentArgs$4(String str) {
        String[] autoSplitText = TextDotUtils.autoSplitText(this.tvMultiSignWarning, str);
        this.tvMultiSignWarning.setText(SpannableUtils.getTextColorSpannable(autoSplitText[0], autoSplitText[1], getResources().getColor(R.color.black_23)));
    }

    private void initViews() {
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(this.fragmentView.getId());
        if (findFragmentById instanceof VoteHeaderFragment) {
            VoteHeaderFragment voteHeaderFragment = (VoteHeaderFragment) findFragmentById;
            this.headerFragment = voteHeaderFragment;
            voteHeaderFragment.setHeaderViewClickCallback(new VoteHeaderFragment.OnHeaderViewClickCallback() {
                @Override
                public void onClickVote() {
                    enterVotingPage();
                }

                @Override
                public void onClickPromote() {
                    VoteMainActivity voteMainActivity = VoteMainActivity.this;
                    voteMainActivity.gaLog(getLogMultiSignTag() + 2);
                    if (top3Witnesses == null) {
                        top3Witnesses = new ArrayList();
                    }
                    if (hasVotePermission()) {
                        FastVoteActivity.startActivity(getIContext(), controllerAccount, top3Witnesses, 0, totalVotingRights - alreadyVotedNumber, totalVotingRights, ((VoteMainPresenter) mPresenter).getCachedMyVotedWitness(), controllerAddress, controllerName, fromMultiSign, statAction);
                    }
                }

                @Override
                public void onClickGetProfit(double d) {
                    VoteMainActivity voteMainActivity = VoteMainActivity.this;
                    voteMainActivity.gaLog(getLogMultiSignTag() + 4);
                    if (hasProfitPermission()) {
                        ((VoteMainPresenter) mPresenter).requestGetProfit(controllerAddress, controllerAccount, d);
                    } else {
                        onRequestProfitComplete(false, -1);
                    }
                }

                @Override
                public void onClickStake() {
                    VoteMainActivity voteMainActivity = VoteMainActivity.this;
                    voteMainActivity.gaLog(getLogMultiSignTag() + 3);
                    DataStatHelper.StatAction statAction = DataStatHelper.StatAction.Stake;
                    if (statAction != null && DataStatHelper.StatAction.FinanceVote == statAction) {
                        statAction = DataStatHelper.StatAction.FinanceStake;
                    }
                    StakeTRXActivity.start(mContext, controllerAccount, statAction);
                }

                @Override
                public void onClickTips(View view) {
                    PopupWindowUtil.showPopupText(mContext, StringTronUtil.getResString(R.string.vote_tips), view, false);
                }
            });
        }
        this.appBarLayout.addOnOffsetChangedListener(getOffsetChangedListener());
        WitnessListAdapter witnessListAdapter = new WitnessListAdapter(getIContext());
        this.listAdapter = witnessListAdapter;
        this.rvContent.setAdapter(witnessListAdapter);
        this.rvContent.setLayoutManager(new WrapContentLinearLayoutManager(getIContext()));
        this.listAdapter.setOnItemClickListener(new WitnessListAdapter.DebouncedItemClickListener() {
            @Override
            protected void onDebouncedClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                VoteMainActivity voteMainActivity = VoteMainActivity.this;
                voteMainActivity.gaLog(getLogMultiSignTag() + 11);
                WitnessOutput.DataBean item = listAdapter.getItem(i);
                if (item == null) {
                    return;
                }
                WitnessOutput witnessOutput = new WitnessOutput();
                witnessOutput.setData(listAdapter.getData());
                SuperDetailActivity.start(getIContext(), controllerAccount, witnessOutput, BigDecimalUtils.toBigDecimal(item.getVoted()).intValue(), totalVotingRights, permissionData, listAdapter.getData().get(i), ((VoteMainPresenter) mPresenter).getCachedMyVotedWitness(), (int) item.getRealTimeRanking(), controllerName, controllerAddress, fromMultiSign, totalVotingRights > 0, statAction);
            }
        });
        this.listAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public final void onLoadMoreRequested() {
                lambda$initViews$5();
            }
        }, this.rvContent);
        this.onPullToRefreshCallbacks.add(this);
        this.onPullToRefreshCallbacks.add(this.headerFragment);
        this.ptrView.setPtrHandler(new fun3());
    }

    public void lambda$initViews$5() {
        requestRefreshOrLoadMore(false);
    }

    public class fun3 implements PtrHandler {
        fun3() {
        }

        @Override
        public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
            if (appBarCollapse) {
                return false;
            }
            return PtrDefaultHandler.checkContentCanBePulledDown(ptrFrameLayout, rvContent, view2);
        }

        @Override
        public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
            Collection.-EL.stream(onPullToRefreshCallbacks).forEach(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    VoteMainActivity.3.lambda$onRefreshBegin$0((Contract.OnPullToRefreshCallback) obj);
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
        }

        public static void lambda$onRefreshBegin$0(Contract.OnPullToRefreshCallback onPullToRefreshCallback) {
            try {
                onPullToRefreshCallback.onPullToRefresh();
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
    }

    public void initAprTipLayout(double d) {
        if (this.fromMultiSign || this.selectWallet == null) {
            return;
        }
        String lastVoteApr = SpAPI.THIS.getLastVoteApr(this.selectWallet.getAddress());
        if (StringTronUtil.isEmpty(lastVoteApr)) {
            this.aprTipView.setVisibility(View.GONE);
        } else if (BigDecimalUtils.MoreThan(BigDecimalUtils.sub_(lastVoteApr, Double.valueOf(d)), Double.valueOf(0.5d))) {
            this.aprTipView.setVisibility(View.VISIBLE);
            TextView textView = this.tvAprTip;
            String string = getString(R.string.apr_tips_format);
            textView.setText(String.format(string, "" + VoteAprCalculator.formatAprPercent(BigDecimalUtils.sub_(lastVoteApr, Double.valueOf(d)).doubleValue()) + "%"));
        }
    }

    private void initEvents() {
        this.btnCancelAll.setOnClickListener(this.clickListener);
        this.ivTips.setOnClickListener(this.clickListener);
        this.searchView.setOnClickListener(this.clickListener);
        this.ivBack.setOnClickListener(this.clickListener);
        this.tvMultiSign.setOnClickListener(this.clickListener);
        this.llSort.setOnClickListener(this.clickListener);
        this.btnVote.setOnClickListener(this.clickListener);
    }

    public class fun4 extends NoDoubleClickListener {
        fun4() {
        }

        @Override
        protected void onNoDoubleClick(View view) {
            int id = view.getId();
            if (id == R.id.iv_tips) {
                CenterPopupInstructionView.showUp(getIContext());
            } else if (id == R.id.ll_search_view) {
                enterSearch();
            } else if (id == R.id.iv_back) {
                exit();
            } else if (id == R.id.tv_multi_sign) {
                MultiSelectAddressActivity.start(getIContext(), MultiSourcePageEnum.Vote);
                VoteMainActivity voteMainActivity = VoteMainActivity.this;
                voteMainActivity.gaLog(getLogMultiSignTag() + 0);
            } else if (id == R.id.ll_sort) {
                VoteMainActivity voteMainActivity2 = VoteMainActivity.this;
                voteMainActivity2.gaLog(getLogMultiSignTag() + 6);
                VoteSortPopupView.showUp(getIContext(), sortType, filterMyVotes, alreadyVotedNumber, new VoteSortPopupView.OnSelectChangedListener() {
                    @Override
                    public final void onSelectChanged(boolean z, int i) {
                        VoteMainActivity.4.this.lambda$onNoDoubleClick$0(z, i);
                    }
                });
            } else if (id == R.id.btn_vote_tool_bar) {
                enterVotingPage();
            } else if (id == R.id.btn_cancel_all) {
                VoteMainActivity voteMainActivity3 = VoteMainActivity.this;
                voteMainActivity3.gaLog(getLogMultiSignTag() + 5);
                if (mPresenter == 0) {
                    return;
                }
                if (alreadyVotedNumber == 1) {
                    showUnableCancelDialog();
                } else if (hasVotePermission()) {
                    showLoadingDialog();
                    ((VoteMainPresenter) mPresenter).requestCancelAllVotes(fromMultiSign, totalVotingRights, controllerAddress, controllerAccount);
                }
            }
        }

        public void lambda$onNoDoubleClick$0(boolean z, int i) {
            if (z != filterMyVotes) {
                VoteMainActivity voteMainActivity = VoteMainActivity.this;
                voteMainActivity.gaLog(getLogMultiSignTag() + 10);
            } else if (i == 5) {
                VoteMainActivity voteMainActivity2 = VoteMainActivity.this;
                voteMainActivity2.gaLog(getLogMultiSignTag() + 7);
            } else if (i == 6) {
                VoteMainActivity voteMainActivity3 = VoteMainActivity.this;
                voteMainActivity3.gaLog(getLogMultiSignTag() + 8);
            } else if (i == 7) {
                VoteMainActivity voteMainActivity4 = VoteMainActivity.this;
                voteMainActivity4.gaLog(getLogMultiSignTag() + 9);
            }
            filterMyVotes = z;
            sortType = i;
            if (mPresenter == 0) {
                return;
            }
            scrollToTop = true;
            requestRefreshOrLoadMore(true);
        }
    }

    public void showUnableCancelDialog() {
        if (this.unableCancelDialog == null) {
            this.unableCancelDialog = ConfirmCustomPopupView.getBuilder(this).setTitleSize(16).setTitleBold(false).setTitle(getString(R.string.vote_unable_cancel_all)).setShowCancelBtn(false).setConfirmText(getString(R.string.confirm)).build();
        }
        if (this.unableCancelDialog.isShow()) {
            return;
        }
        this.unableCancelDialog.show();
    }

    public void enterSearch() {
        Intent intent = new Intent(this, VoteSRSearchActivity.class);
        ActivityOptionsCompat makeSceneTransitionAnimation = ActivityOptionsCompat.makeSceneTransitionAnimation(this, new Pair(findViewById(R.id.ll_search_view), "llSearch"));
        intent.putParcelableArrayListExtra(VoteSRSearchActivity.WITNESS_LIST, (ArrayList) this.listAdapter.getData());
        intent.putExtra("key_account", this.controllerAccount);
        intent.putExtra("key_controller_address", this.controllerAddress);
        intent.putExtra("key_controller_name", this.controllerName);
        intent.putExtra(CommonBundleKeys.KEY_FROM_MULTI_SIGN, this.fromMultiSign);
        intent.putExtra(CommonBundleKeys.KEY_PERMISSION_DATA, this.permissionData);
        intent.putParcelableArrayListExtra(CommonBundleKeys.KEY_ALREADY_VOTED_WITNESSES, ((VoteMainPresenter) this.mPresenter).getCachedMyVotedWitness());
        intent.putExtra(CommonBundleKeys.KEY_MY_AVAILABEL_VOTE, this.totalVotingRights - this.alreadyVotedNumber);
        intent.putExtra(CommonBundleKeys.KEY_ALL_MY_VOTE_RIGHTS, this.totalVotingRights);
        intent.putExtra(TronConfig.StatAction_Key, this.statAction);
        intent.putParcelableArrayListExtra(CommonBundleKeys.KEY_SELECTED_WITNESSES, new ArrayList<>());
        ActivityCompat.startActivity(this, intent, makeSceneTransitionAnimation.toBundle());
    }

    private AppBarLayout.BaseOnOffsetChangedListener<AppBarLayout> getOffsetChangedListener() {
        return new AppBarLayout.BaseOnOffsetChangedListener() {
            @Override
            public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                lambda$getOffsetChangedListener$6(appBarLayout, i);
            }
        };
    }

    public void lambda$getOffsetChangedListener$6(AppBarLayout appBarLayout, int i) {
        this.appBarCollapse = i != 0;
        int totalScrollRange = appBarLayout.getTotalScrollRange();
        float min = Math.abs(i) == totalScrollRange ? 0.0f : Math.min(1.0f, (((i << 1) + totalScrollRange) / totalScrollRange) + 1.0f);
        this.fragmentView.setAlpha(min);
        this.tvMultiSign.setAlpha(min);
        if (this.btnVote.getVisibility() == 0) {
            this.btnVote.setAlpha(1.0f - min);
        }
        boolean z = min <= 0.9f;
        if (!this.fromMultiSign) {
            this.tvMultiSign.setVisibility(z ? View.GONE : View.VISIBLE);
        }
        if (z && this.searchView.getVisibility() == 8) {
            this.searchView.setVisibility(View.VISIBLE);
            this.btnVote.setVisibility(View.VISIBLE);
            this.headerLayout.setBackgroundResource(R.color.gray_F7F);
            this.rootView.setBackgroundResource(R.color.gray_F7F);
            this.searchViewLayout.setBackgroundResource(R.color.gray_F7F);
        } else if (z || this.searchView.getVisibility() != 0) {
        } else {
            this.searchView.setVisibility(View.GONE);
            this.btnVote.setVisibility(View.GONE);
            this.headerLayout.setBackgroundResource(R.color.white);
            this.rootView.setBackgroundResource(R.color.white);
            this.searchViewLayout.setBackgroundResource(R.color.white);
        }
    }

    public void requestRefreshOrLoadMore(boolean z) {
        if (z) {
            this.ivLoading.setVisibility(View.VISIBLE);
            this.rvContent.setVisibility(View.GONE);
            this.btnCancelAll.setEnabled(false);
            this.llSort.setEnabled(false);
        }
        String str = this.controllerAddress;
        boolean z2 = this.filterMyVotes;
        ((VoteMainPresenter) this.mPresenter).requestWitnessList(z, str, z2 ? 1 : 0, this.sortType);
    }

    @Override
    public void onRequestWitnessSuccess(boolean z, WitnessOutput witnessOutput) {
        this.btnCancelAll.setEnabled(true);
        this.llSort.setEnabled(true);
        WitnessListAdapter witnessListAdapter = this.listAdapter;
        if (witnessListAdapter == null) {
            this.scrollToTop = false;
            return;
        }
        witnessListAdapter.updateData(z, witnessOutput.getData());
        if (this.scrollToTop) {
            this.rvContent.smoothScrollToPosition(0);
            this.scrollToTop = false;
        }
        this.rvContent.setVisibility(View.VISIBLE);
        this.ivLoading.setVisibility(View.GONE);
        this.ptrView.refreshComplete();
    }

    @Override
    public void onRequestFail(String str) {
        toast(getString(R.string.network_unusable));
    }

    @Override
    public void onRequestAccountSuccess(Protocol.Account account) {
        this.controllerAccount = account;
        if (account != null) {
            ((VoteMainPresenter) this.mPresenter).getVotingData(this.reloadAccount, this.controllerAccount, this.controllerAddress);
        }
        this.reloadAccount = true;
    }

    @Override
    public void onVotingDataSuccess(VoteHeaderBean voteHeaderBean) {
        VoteHeaderFragment voteHeaderFragment = this.headerFragment;
        if (voteHeaderFragment != null) {
            voteHeaderFragment.bindHeaderData(voteHeaderBean, this.fromMultiSign);
        }
        this.totalVotingRights = voteHeaderBean.getTotalVotingRights();
        this.alreadyVotedNumber = voteHeaderBean.getAlreadyVotedNumber();
        this.btnCancelAll.setVisibility(voteHeaderBean.getAlreadyVotedNumber() == 0 ? View.GONE : View.VISIBLE);
        this.btnVote.setEnabled(voteHeaderBean.getTotalVotingRights() > 0);
    }

    @Override
    public void onRequestProfitNumberComplete(double d, boolean z, String str) {
        if (!z) {
            onRequestFail(str);
        }
        VoteHeaderFragment voteHeaderFragment = this.headerFragment;
        if (voteHeaderFragment != null) {
            if (z) {
                voteHeaderFragment.updateProfit(d);
            }
            this.headerFragment.enableProfitButton(true);
        }
    }

    @Override
    public void onRequestAprComplete(FastAprBean fastAprBean) {
        VoteHeaderFragment voteHeaderFragment = this.headerFragment;
        if (voteHeaderFragment != null) {
            voteHeaderFragment.updateApr(fastAprBean);
        }
        this.top3Witnesses = new ArrayList<>(fastAprBean.getTop3Witnesses());
    }

    @Override
    public void onRequestProfitComplete(boolean z, int i) {
        dismissLoadingDialog();
        if (!z && i > 0) {
            toast(getString(i));
        }
        VoteHeaderFragment voteHeaderFragment = this.headerFragment;
        if (voteHeaderFragment != null) {
            voteHeaderFragment.enableProfitButton(true);
        }
    }

    @Override
    public void onRequestCancelVoteComplete(boolean z, String str, BaseConfirmTransParamBuilder baseConfirmTransParamBuilder) {
        if (z && baseConfirmTransParamBuilder != null) {
            Wallet wallet = this.selectWallet;
            ConfirmTransactionNewActivity.startActivity(this, baseConfirmTransParamBuilder, wallet != null && wallet.isSamsungWallet());
        } else if (!TextUtils.isEmpty(str)) {
            toast(str);
        }
        dismissLoadingDialog();
    }

    public void enterVotingPage() {
        gaLog(getLogMultiSignTag() + 1);
        if (hasVotePermission()) {
            try {
                VoteSelectSRActivity.start(getIContext(), this.controllerAccount, this.fromMultiSign, this.controllerAddress, this.controllerName, this.listAdapter.getData(), this.top3Witnesses, ((VoteMainPresenter) this.mPresenter).getCachedMyVotedWitness(), this.permissionData, this.filterMyVotes, this.sortType, this.totalVotingRights, this.statAction);
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
    }

    @Override
    public void onPullToRefresh() {
        ((VoteMainPresenter) this.mPresenter).ensureAccount(this.controllerAccount, this.controllerAddress);
        ((VoteMainPresenter) this.mPresenter).requestProfitNumber(this.controllerAddress);
        requestRefreshOrLoadMore(true);
        ((VoteMainPresenter) this.mPresenter).requestApr(this.controllerAddress);
    }

    public boolean hasVotePermission() {
        Wallet wallet;
        MultiSignPermissionData multiSignPermissionData = this.permissionData;
        boolean z = multiSignPermissionData == null || multiSignPermissionData.isVoteWitnessPermission();
        if (!z) {
            toast(getString(R.string.vote_no_vote_permission));
        } else if (this.fromMultiSign && (wallet = this.selectWallet) != null && wallet.isSamsungWallet()) {
            toast(getString(R.string.transaction_type_not_support));
            return false;
        }
        return z;
    }

    public boolean hasProfitPermission() {
        Wallet wallet;
        MultiSignPermissionData multiSignPermissionData = this.permissionData;
        boolean z = multiSignPermissionData == null || multiSignPermissionData.isWithdrawBalancePermission();
        if (!z) {
            toast(getString(R.string.vote_no_withdraw_permission));
        } else if (this.fromMultiSign && (wallet = this.selectWallet) != null && wallet.isSamsungWallet()) {
            toast(getString(R.string.transaction_type_not_support));
            return false;
        }
        return z;
    }

    public void gaLog(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("is_multivoter", this.fromMultiSign ? AnalyticsHelper.VoteMainPage.PARAM_VALUE_MULTI : AnalyticsHelper.VoteMainPage.PARAM_VALUE_SINGLE);
        AnalyticsHelper.logEvent(str, bundle);
    }
}
