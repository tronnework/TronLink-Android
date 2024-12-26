package com.tron.wallet.business.vote.fastvote;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.core.CenterPopupView;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.interfaces.SimpleCallback;
import com.lxj.xpopup.util.XPopupUtils;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxBus;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.mutil.MultiSelectAddressActivity;
import com.tron.wallet.business.mutil.bean.MultiSourcePageEnum;
import com.tron.wallet.business.tronpower.unstake.UnStakeActivity;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.business.vote.fastvote.FastVoteContract;
import com.tron.wallet.business.vote.fastvote.VoteDataHolder;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.config.CommonBundleKeys;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.DataStatHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.PopupWindowUtil;
import com.tron.wallet.common.utils.SpannableUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TextDotUtils;
import com.tron.wallet.common.utils.VoteAprCalculator;
import com.tron.wallet.databinding.AcVoteEasyBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class FastVoteActivity extends BaseActivity<FastVotePresenter, FastVoteModel> implements FastVoteContract.View {
    private static final String AVAILABLE_VOTES = "AVAILABLE_VOTES";
    private static final String KEY_ACCOUNT = "KEY_ACCOUNT";
    private static final String TOTAL_VOTES = "TOTAL_VOTES";
    private static final String VITED_LIST = "VITED_LIST";
    private static final String VOTE_PAGE_TYPE = "VOTE_PAGE_TYPE";
    TextView aprTextView;
    View apr_layout;
    TextView availableVoteTextView;
    long availableVotes;
    ArrayList<WitnessOutput.DataBean> beanArrayList;
    private AcVoteEasyBinding binding;
    LinearLayout clearLayout;
    RelativeLayout countLayout;
    View footerView;
    private String formatApr;
    TextView go;
    ImageView ivAprTip;
    private Protocol.Account mAccount;
    private NumberFormat numberFormat;
    private BasePopupView popupView;
    View rootView;
    RecyclerView rvContent;
    private RxManager rxManager;
    private String selectAddress;
    private String selectName;
    RelativeLayout tipsLayout;
    TextView totalVoteTextView;
    long totalVotes;
    TextView tvMultiSignWarning;
    TextView tvVoteTips;
    ArrayList<VoteWitnessBean> voteBeanList;
    ArrayList<WitnessOutput.DataBean> votedArrayList;
    LinearLayout warningLayout;
    private boolean hasPermission = true;
    private boolean isFromMalting = false;
    int votePageType = 0;
    int voteType = 0;

    public String getLogMultiSignTag() {
        return this.isFromMalting ? AnalyticsHelper.VotePage.MULTI_SIGN : AnalyticsHelper.VotePage.SINGLE_SIGN;
    }

    @Override
    public View getFooterView() {
        return this.footerView;
    }

    @Override
    public String getLogPageTag() {
        int i = this.votePageType;
        return i != 0 ? i != 2 ? AnalyticsHelper.VotePage.COMMON_VOTE_TWO_PAGE : AnalyticsHelper.VotePage.SINGLE_VOTE_PAGE : AnalyticsHelper.VotePage.FAST_VOTE_PAGE;
    }

    @Override
    public RecyclerView getRv() {
        return this.rvContent;
    }

    @Override
    protected void processData() {
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.numberFormat = numberInstance;
        numberInstance.setMaximumFractionDigits(3);
        this.mAccount = (Protocol.Account) getIntent().getSerializableExtra(KEY_ACCOUNT);
        this.isFromMalting = getIntent().getBooleanExtra(CommonBundleKeys.KEY_FROM_MULTI_SIGN, false);
        this.selectAddress = getIntent().getStringExtra("key_controller_address");
        setAccountInfo();
        this.footerView = createFooterView();
        ((FastVotePresenter) this.mPresenter).init(this.availableVotes, this.totalVotes);
        setListData();
        initEvents();
        int i = this.votePageType;
        if (i == 0) {
            this.tipsLayout.setVisibility(View.VISIBLE);
        } else if (i == 1) {
            this.tipsLayout.setVisibility(View.VISIBLE);
            this.tvVoteTips.setText(R.string.common_auto_vote_count_tips);
        }
        this.availableVoteTextView.setText(String.valueOf(this.totalVotes));
        this.totalVoteTextView.setText(String.valueOf(this.totalVotes));
        this.go.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                AnalyticsHelper.AssetPage.logEvent(getLogPageTag() + getLogMultiSignTag() + 4);
                if (!TronConfig.hasNet) {
                    FastVoteActivity fastVoteActivity = FastVoteActivity.this;
                    fastVoteActivity.toast(fastVoteActivity.getIContext().getString(R.string.time_out));
                    return;
                }
                showLoadingDialog();
                ((FastVotePresenter) mPresenter).send(mAccount, voteType, formatApr, new VoteDataHolder.ViewCallback() {
                    @Override
                    public void onVoteError(int i2) {
                        dismissLoadingDialog();
                        toast(getString(i2));
                    }

                    @Override
                    public void onVoteErrorMessage(String str) {
                        dismissLoadingDialog();
                        toast(str);
                    }

                    @Override
                    public void onVoteComplete() {
                        dismissLoadingDialog();
                    }
                });
            }
        });
        this.apr_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupWindowUtil.showPopupText(mContext, getResources().getString(R.string.vote_apr_tips), ivAprTip, false);
            }
        });
        this.clearLayout.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                AnalyticsHelper.AssetPage.logEvent(getLogPageTag() + getLogMultiSignTag() + 3);
                ((FastVotePresenter) mPresenter).ClearVoteData(votePageType);
            }
        });
        if (this.isFromMalting) {
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
                    lambda$processData$0(str);
                }
            });
            this.tvMultiSignWarning.setVisibility(View.VISIBLE);
        }
    }

    public void lambda$processData$0(String str) {
        String[] autoSplitText = TextDotUtils.autoSplitText(this.tvMultiSignWarning, str);
        this.tvMultiSignWarning.setText(SpannableUtils.getTextColorSpannable(autoSplitText[0], autoSplitText[1], getResources().getColor(R.color.black_23)));
    }

    private void setListData() {
        this.voteBeanList = new ArrayList<>();
        if (this.votePageType == 2) {
            WitnessOutput.DataBean dataBean = this.beanArrayList.get(0);
            VoteWitnessBean voteWitnessBean = new VoteWitnessBean();
            voteWitnessBean.setDataBean(dataBean);
            voteWitnessBean.setVisibility(false);
            voteWitnessBean.setVotedCount(StringTronUtil.isEmpty(dataBean.getVoted()) ? "0" : dataBean.getVoted());
            voteWitnessBean.setVoteCount(StringTronUtil.isEmpty(dataBean.getVoted()) ? "0" : dataBean.getVoted());
            this.voteBeanList.add(voteWitnessBean);
            ArrayList<WitnessOutput.DataBean> arrayList = this.votedArrayList;
            if (arrayList != null && (arrayList.size() > 1 || (this.votedArrayList.size() == 1 && !StringTronUtil.equals(dataBean.getAddress(), this.votedArrayList.get(0).getAddress())))) {
                VoteWitnessBean voteWitnessBean2 = new VoteWitnessBean();
                voteWitnessBean2.setTitle(true);
                this.voteBeanList.add(voteWitnessBean2);
                voteWitnessBean2.setVisibility(false);
            }
            Iterator<WitnessOutput.DataBean> it = this.votedArrayList.iterator();
            long j = 0;
            while (it.hasNext()) {
                WitnessOutput.DataBean next = it.next();
                j += StringTronUtil.isEmpty(next.getVoted()) ? 0L : Long.valueOf(next.getVoted()).longValue();
                if (!StringTronUtil.equals(dataBean.getAddress(), next.getAddress())) {
                    VoteWitnessBean voteWitnessBean3 = new VoteWitnessBean();
                    voteWitnessBean3.setDataBean(next);
                    voteWitnessBean3.setVisibility(true);
                    voteWitnessBean3.setVotedCount(next.getVoted());
                    voteWitnessBean3.setVoteCount(next.getVoted());
                    this.voteBeanList.add(voteWitnessBean3);
                }
            }
            ArrayList<VoteWitnessBean> arrayList2 = this.voteBeanList;
            long j2 = this.totalVotes;
            ((FastVotePresenter) this.mPresenter).updateRvList(arrayList2, j2, this.votedArrayList, j2 - j);
            return;
        }
        long size = this.totalVotes % this.beanArrayList.size();
        Iterator<WitnessOutput.DataBean> it2 = this.beanArrayList.iterator();
        while (it2.hasNext()) {
            WitnessOutput.DataBean next2 = it2.next();
            VoteWitnessBean voteWitnessBean4 = new VoteWitnessBean();
            voteWitnessBean4.setDataBean(next2);
            voteWitnessBean4.setVisibility(false);
            voteWitnessBean4.setVotedCount(next2.getVoted());
            long size2 = this.totalVotes / this.beanArrayList.size();
            if (this.votePageType == 0) {
                int i = (int) size;
                if (i == 1) {
                    if (this.voteBeanList.size() != 0) {
                    }
                    size2++;
                } else if (i == 2) {
                    if (this.voteBeanList.size() >= 2) {
                    }
                    size2++;
                }
            } else if (this.voteBeanList.size() == 0) {
                size2 += size;
            }
            voteWitnessBean4.setVoteCount(String.valueOf(size2));
            this.voteBeanList.add(voteWitnessBean4);
        }
        ((FastVotePresenter) this.mPresenter).updateRvList(this.voteBeanList, this.totalVotes, this.votedArrayList, 0L);
    }

    private void initEvents() {
        this.rxManager = ((FastVotePresenter) this.mPresenter).mRxManager;
    }

    @Override
    protected void setLayout() {
        AcVoteEasyBinding inflate = AcVoteEasyBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 1);
        mappingId();
        getHeaderHolder().ivCommonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$setLayout$1(view);
            }
        });
        getParcelableData();
        if (this.votePageType == 0) {
            if (this.isFromMalting) {
                setHeaderBar(getString(R.string.multi_sign_fast_vote_title));
            } else {
                setHeaderBar(getString(R.string.vote_easy));
            }
        } else if (this.isFromMalting) {
            setHeaderBar(getString(R.string.multi_sign_vote_title));
            if (this.votePageType == 1) {
                setCommonTitle2(getString(R.string.step_2_2));
            }
        } else {
            setHeaderBar(getString(R.string.vote));
            if (this.votePageType == 1) {
                setCommonTitle2(getString(R.string.step_2_2));
            }
        }
    }

    public void lambda$setLayout$1(View view) {
        onLeftButtonClick();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.warningLayout = this.binding.voteWarning;
        this.tipsLayout = this.binding.fastVoteTips;
        this.tvVoteTips = this.binding.tvVoteTips;
        this.rvContent = this.binding.rvContent;
        this.clearLayout = this.binding.liClearAll;
        this.go = this.binding.btNext;
        this.countLayout = this.binding.voteCountLayout;
        this.availableVoteTextView = this.binding.availableVote;
        this.totalVoteTextView = this.binding.totalVote;
        this.apr_layout = this.binding.aprLayout;
        this.ivAprTip = this.binding.ivApproveIconPop;
        this.aprTextView = this.binding.tvVotingApr;
        this.tvMultiSignWarning = this.binding.tvMultiWarning;
        this.rootView = this.binding.root;
    }

    @Override
    public void updateVoteCountLayout(final long j) {
        this.countLayout.post(new Runnable() {
            @Override
            public final void run() {
                lambda$updateVoteCountLayout$2(j);
            }
        });
    }

    public void lambda$updateVoteCountLayout$2(long j) {
        if (j < 0) {
            TextView textView = this.availableVoteTextView;
            textView.setText(StringTronUtil.formatNumber3Truncate(0) + "/");
            this.go.setEnabled(false);
        } else {
            TextView textView2 = this.availableVoteTextView;
            textView2.setText(StringTronUtil.formatNumber3Truncate(Long.valueOf(j)) + "/");
            this.go.setEnabled(true);
        }
        this.totalVoteTextView.setText(StringTronUtil.formatNumber3Truncate(Long.valueOf(this.totalVotes)));
    }

    @Override
    public void updateVoteApr(List<VoteWitnessBean> list, long j) {
        this.formatApr = BigDecimalUtils.toBigDecimal(VoteAprCalculator.calculateApr(list, j)).toPlainString();
        this.apr_layout.post(new Runnable() {
            @Override
            public final void run() {
                lambda$updateVoteApr$3();
            }
        });
    }

    public void lambda$updateVoteApr$3() {
        this.apr_layout.setVisibility(View.VISIBLE);
        this.aprTextView.setText(String.format("%s%%", VoteAprCalculator.formatAprPercent(Double.valueOf(this.formatApr).doubleValue())));
    }

    @Override
    public void updateVoteCountTips(boolean z) {
        this.warningLayout.setVisibility(z ? View.VISIBLE : View.GONE);
    }

    public void getParcelableData() {
        Intent intent = getIntent();
        this.beanArrayList = intent.getParcelableArrayListExtra(CommonBundleKeys.KEY_SELECTED_WITNESSES);
        this.votedArrayList = intent.getParcelableArrayListExtra(VITED_LIST);
        this.isFromMalting = intent.getBooleanExtra(CommonBundleKeys.KEY_FROM_MULTI_SIGN, false);
        int intExtra = intent.getIntExtra(VOTE_PAGE_TYPE, 0);
        this.votePageType = intExtra;
        if (intExtra != 2) {
            this.availableVotes = 0L;
            this.voteType = 3;
        } else {
            this.voteType = 0;
        }
        this.totalVotes = intent.getLongExtra(TOTAL_VOTES, 0L);
        ArrayList<WitnessOutput.DataBean> arrayList = this.votedArrayList;
        if (arrayList == null || arrayList.size() == 0) {
            return;
        }
        Collections.sort(this.votedArrayList, new Comparator<WitnessOutput.DataBean>() {
            @Override
            public int compare(WitnessOutput.DataBean dataBean, WitnessOutput.DataBean dataBean2) {
                return Long.compare(TextUtils.isEmpty(dataBean2.getVoted()) ? 0L : Long.parseLong(dataBean2.getVoted()), TextUtils.isEmpty(dataBean.getVoted()) ? 0L : Long.parseLong(dataBean.getVoted()));
            }
        });
    }

    public static void startActivity(Context context, Protocol.Account account, ArrayList<WitnessOutput.DataBean> arrayList, int i, long j, long j2, ArrayList<WitnessOutput.DataBean> arrayList2, String str, String str2, boolean z, DataStatHelper.StatAction statAction) {
        startActivity(context, account, arrayList, i, j, j2, arrayList2, str, str2, z, false, statAction);
    }

    public static void startActivity(Context context, Protocol.Account account, ArrayList<WitnessOutput.DataBean> arrayList, int i, long j, long j2, ArrayList<WitnessOutput.DataBean> arrayList2, String str, String str2, boolean z, boolean z2, DataStatHelper.StatAction statAction) {
        Intent intent = new Intent();
        intent.setClass(context, FastVoteActivity.class);
        if (account != null) {
            intent.putExtra(KEY_ACCOUNT, account);
        }
        intent.putExtra(VOTE_PAGE_TYPE, i);
        intent.putExtra(AVAILABLE_VOTES, j);
        intent.putExtra(TOTAL_VOTES, j2);
        intent.putExtra(CommonBundleKeys.KEY_FROM_MULTI_SIGN, z);
        intent.putExtra(CommonBundleKeys.KEY_IS_FROM_STAKE_SUCCESS, z2);
        intent.putExtra("key_controller_address", str);
        intent.putExtra("key_controller_name", str2);
        intent.putParcelableArrayListExtra(CommonBundleKeys.KEY_SELECTED_WITNESSES, arrayList);
        intent.putParcelableArrayListExtra(VITED_LIST, arrayList2);
        intent.putExtra(TronConfig.StatAction_Key, statAction);
        context.startActivity(intent);
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        AnalyticsHelper.AssetPage.logEvent(getLogPageTag() + getLogMultiSignTag() + 0);
        finish();
    }

    @Override
    public void showAlertPopWindow() {
        BasePopupView asCustom = new XPopup.Builder(this).maxWidth(XPopupUtils.getScreenWidth(this)).dismissOnTouchOutside(false).popupAnimation(PopupAnimation.NoAnimation).asCustom(new CenterPopupView(this) {
            @Override
            public int getImplLayoutId() {
                return R.layout.vote_empty_dialog;
            }

            @Override
            public void onCreate() {
                super.onCreate();
                TextView textView = (TextView) findViewById(R.id.btn_unfreeze);
                TextView textView2 = (TextView) findViewById(R.id.confirm);
                if (isFromMalting) {
                    textView.setVisibility(View.GONE);
                }
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.VotePagePop.EMPTY_POP + getLogMultiSignTag() + 1);
                        UnStakeActivity.start(getIContext(), mAccount);
                        RxBus.getInstance().post(Event.BackToVoteHome, Event.BackToVoteHome);
                        RxBus.getInstance().post(Event.VoteClearPrevious, 0);
                    }
                });
                textView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.VotePagePop.EMPTY_POP + getLogMultiSignTag() + 0);
                        dismiss();
                    }
                });
            }
        });
        this.popupView = asCustom;
        asCustom.show();
    }

    private void showMultiSignDialog() {
        ConfirmCustomPopupView.getBuilder(this.mContext).setTitle(getString(R.string.unstake_lack_owner_permission)).setTitleSize(16).setPopupCallback(new SimpleCallback() {
            @Override
            public void onDismiss(BasePopupView basePopupView) {
                exit();
            }
        }).setConfirmText(getString(R.string.unstake_multi_sign_dialog_title)).setCancleListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showMultiSignDialog$4(view);
            }
        }).setConfirmListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showMultiSignDialog$5(view);
            }
        }).build().show();
    }

    public void lambda$showMultiSignDialog$4(View view) {
        exit();
    }

    public void lambda$showMultiSignDialog$5(View view) {
        checkAndEnterMultiSignUnstake();
        AnalyticsHelper.logEvent(AnalyticsHelper.MultisigUnstaking.CLICK_DIALOG);
    }

    private void checkAndEnterMultiSignUnstake() {
        MultiSelectAddressActivity.start(this, MultiSourcePageEnum.UnStake);
    }

    private void setAccountInfo() {
        runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$setAccountInfo$6();
            }
        });
    }

    public void lambda$setAccountInfo$6() {
        try {
            Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
            if (selectedPublicWallet != null) {
                this.hasPermission = WalletUtils.checkHaveOwnerPermissions(selectedPublicWallet.getAddress(), this.mAccount.getOwnerPermission());
            }
        } catch (Exception e) {
            LogUtils.e(e);
            this.hasPermission = true;
        }
    }

    public View createFooterView() {
        return getLayoutInflater().inflate(R.layout.equipment_footer_view, (ViewGroup) this.rvContent.getParent(), false);
    }
}
