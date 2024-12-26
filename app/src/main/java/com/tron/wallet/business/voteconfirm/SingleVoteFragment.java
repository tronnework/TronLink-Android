package com.tron.wallet.business.voteconfirm;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.parambuilder.bean.VoteParam;
import com.tron.wallet.business.tronpower.stake.StakeTRXActivity;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.business.vote.util.BaseTextWatcher;
import com.tron.wallet.business.voteconfirm.SingleVoteContract;
import com.tron.wallet.common.components.ErrorEdiTextLayout;
import com.tron.wallet.common.components.dialog.Common5Dialog;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.BatchVoteFooterResourceBinding;
import com.tron.wallet.databinding.FgVoteComfirmBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.LedgerWallet;
import com.tronlinkpro.wallet.R;
import java.util.HashMap;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class SingleVoteFragment extends BaseFragment<VoteConfirmPresenter, EmptyModel> implements SingleVoteContract.View {
    public static final String KEY_ACCOUNT = "key_account";
    public static final String KEY_ADDRESS = "key_address";
    public static final String KEY_ALL_VOTES = "key_all_votes";
    public static final String KEY_DATA = "key_data";
    public static final String KEY_WITNESS_OUTPUT = "key_witness_output";
    public static final String TYPE = "type";
    public static final int VOTE = 0;
    public static final int VOTE_CANCEL = 1;
    public static final int VOTE_UPDATE = 2;
    private Protocol.Account account;
    private long allVotes;
    private VoteParam baseParam;
    private FgVoteComfirmBinding binding;
    Button btSend;
    private long currentCycleAllVote;
    private Common5Dialog dialog;
    ErrorEdiTextLayout eetContent;
    EditText etNewPassword;
    LinearLayout ivCloseTwo;
    LinearLayout llCancelVoteError;
    RelativeLayout rlEstimated;
    RelativeLayout rlFirst;
    RelativeLayout rlSecond;
    RelativeLayout rlThird;
    RelativeLayout root;
    private BaseTextWatcher textWatcher;
    TextView tvAvailable;
    TextView tvCancelAll;
    TextView tvCost;
    TextView tvEstimated;
    TextView tvFeeCost;
    TextView tvGetVote;
    TextView tvLeftAvailable;
    TextView tvTitle;
    TextView tvVoteOrNot;
    TextView tvVoted;
    private long voteCount;
    private boolean voteIsSending;
    private HashMap<String, String> voteMap;
    private Wallet wallet;
    private String walletAddress;
    private String walletName;
    private WitnessOutput.DataBean witness;
    private WitnessOutput witnessOutput;
    public int type = 0;
    private long availableVote = 0;
    private long mVotedCount = 0;

    public static BaseFragment get(Context context, Protocol.Account account, WitnessOutput witnessOutput, int i, WitnessOutput.DataBean dataBean, long j, String str) {
        SingleVoteFragment singleVoteFragment = new SingleVoteFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("key_witness_output", witnessOutput);
        bundle.putSerializable("key_account", account);
        bundle.putInt("type", i);
        bundle.putParcelable("key_data", dataBean);
        bundle.putLong("key_all_votes", j);
        bundle.putString(KEY_ADDRESS, str);
        singleVoteFragment.setArguments(bundle);
        return singleVoteFragment;
    }

    @Override
    protected void processData() {
        if (this.voteMap == null) {
            this.voteMap = new HashMap<>();
        }
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.type = arguments.getInt("type", 0);
            this.witness = (WitnessOutput.DataBean) arguments.getParcelable("key_data");
            this.currentCycleAllVote = arguments.getLong("key_all_votes");
            this.witnessOutput = (WitnessOutput) arguments.getParcelable("key_witness_output");
            this.account = (Protocol.Account) arguments.getSerializable("key_account");
            this.walletAddress = arguments.getString(KEY_ADDRESS);
        }
        if (TextUtils.isEmpty(this.walletAddress)) {
            this.wallet = WalletUtils.getSelectedWallet();
        } else {
            this.wallet = WalletUtils.getWalletForAddress(this.walletAddress);
        }
        VoteConfirmPresenter voteConfirmPresenter = (VoteConfirmPresenter) this.mPresenter;
        Wallet wallet = this.wallet;
        voteConfirmPresenter.getData(wallet == null ? this.walletAddress : wallet.getAddress(), this.account, this.witnessOutput, this.witness, this.currentCycleAllVote, false);
        initUI(this.type);
        ininListener(this.type);
    }

    private void ininListener(final int i) {
        BaseTextWatcher baseTextWatcher = new BaseTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                long j = 0;
                try {
                    if (!TextUtils.isEmpty(editable.toString())) {
                        j = Long.parseLong(editable.toString());
                    }
                } catch (NumberFormatException e) {
                    LogUtils.e(e);
                }
                checkInputNum(j);
                int i2 = i;
                if (i2 == 1) {
                    j = -j;
                } else if (i2 == 2) {
                    j -= mVotedCount;
                }
                TextView textView = tvEstimated;
                textView.setText((Math.floor((((VoteConfirmPresenter) mPresenter).getYield(j) * 100.0d) * 100.0d) / 100.0d) + "%");
            }
        };
        this.textWatcher = baseTextWatcher;
        this.etNewPassword.addTextChangedListener(baseTextWatcher);
        if (Build.VERSION.SDK_INT >= 23) {
            this.etNewPassword.setCustomInsertionActionModeCallback(new ActionMode.Callback() {
                @Override
                public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                    return false;
                }

                @Override
                public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode actionMode) {
                }

                @Override
                public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                    return false;
                }
            });
        }
    }

    private void initUI(int i) {
        TextView textView = this.tvEstimated;
        textView.setText((Math.floor((((VoteConfirmPresenter) this.mPresenter).getYield(0L) * 100.0d) * 100.0d) / 100.0d) + "%");
        if (i == 0) {
            this.tvTitle.setText(R.string.vote);
            this.rlSecond.setVisibility(View.GONE);
            this.tvCancelAll.setVisibility(View.GONE);
        } else if (i == 1) {
            this.tvCancelAll.setVisibility(View.VISIBLE);
            this.tvGetVote.setVisibility(View.GONE);
            this.tvTitle.setText(R.string.vote_vancel_vote_lowercase);
            this.rlThird.setVisibility(View.GONE);
            this.rlEstimated.setVisibility(View.GONE);
            this.etNewPassword.setHint(R.string.vote_input_cancel_hint);
        } else if (i == 2) {
            this.rlSecond.setVisibility(View.GONE);
            this.tvCancelAll.setVisibility(View.GONE);
            this.tvTitle.setText(R.string.vote_update_vote_lowercase);
        }
        runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$initUI$1();
            }
        });
        initCostView();
    }

    public void lambda$initUI$1() {
        final boolean z;
        final boolean z2;
        try {
            Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
            z = WalletUtils.checkHaveOwnerPermissions(selectedPublicWallet.getAddress(), this.account.getOwnerPermission());
            z2 = LedgerWallet.isLedger(selectedPublicWallet);
        } catch (Exception unused) {
            z = true;
            z2 = false;
        }
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$initUI$0(z2, z);
            }
        });
    }

    public void lambda$initUI$0(boolean z, boolean z2) {
        if (z && z2) {
            Button button = this.btSend;
            if (button != null) {
                button.setText(R.string.request_from_ledger);
                return;
            }
            return;
        }
        Button button2 = this.btSend;
        if (button2 != null) {
            button2.setText(R.string.confirm);
        }
    }

    private void initCostView() {
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        if (selectedWallet == null) {
            return;
        }
        GrpcAPI.AccountResourceMessage accountRes = WalletUtils.getAccountRes(getContext(), selectedWallet.getWalletName());
        if ((accountRes.getNetLimit() + accountRes.getFreeNetLimit()) - (accountRes.getNetUsed() + accountRes.getFreeNetUsed()) < 350) {
            TextView textView = this.tvFeeCost;
            textView.setText("≈" + StringTronUtil.formatNumber6Truncate(Double.valueOf(TronConfig.feeBandWidth * 350.0d)) + " TRX");
            TextView textView2 = this.tvCost;
            textView2.setText("0 " + getString(R.string.bandwidth));
            return;
        }
        TextView textView3 = this.tvCost;
        textView3.setText("≈350 " + getString(R.string.bandwidth));
    }

    public void checkInputNum(long j) {
        this.voteCount = j;
        int i = this.type;
        if (i == 0) {
            if (j == 0) {
                this.eetContent.setTextError3(R.string.vote_can_not_0);
                this.eetContent.showError3BgNoChange();
                this.btSend.setEnabled(false);
            } else if (j > this.availableVote) {
                this.eetContent.setTextError3(R.string.vote_not_enough_vote);
                this.eetContent.showError3BgNoChange();
                this.btSend.setEnabled(false);
            } else {
                this.eetContent.hideError3();
                this.btSend.setEnabled(true);
            }
        } else if (i != 1) {
            if (i != 2) {
                return;
            }
            if (j == 0 && this.allVotes - this.availableVote == this.mVotedCount) {
                this.eetContent.setTextError3(R.string.vote_the_minmum_amount);
                this.eetContent.showError3BgNoChange();
                this.btSend.setEnabled(false);
            } else if (j > this.availableVote + this.mVotedCount) {
                this.eetContent.setTextError3(R.string.vote_not_enough_vote);
                this.eetContent.showError3BgNoChange();
                this.btSend.setEnabled(false);
            } else {
                this.eetContent.hideError3();
                this.btSend.setEnabled(true);
            }
        } else if (j == 0) {
            this.eetContent.setTextError3(R.string.vote_cancellation_not_0);
            this.eetContent.showError3BgNoChange();
            this.btSend.setEnabled(false);
        } else if (j > this.mVotedCount) {
            this.eetContent.setTextError3(R.string.vote_cancel_amount_exceed);
            this.eetContent.showError3BgNoChange();
            this.btSend.setEnabled(false);
        } else if (j >= this.allVotes - this.availableVote) {
            this.eetContent.setTextError3(R.string.vote_the_minmum_amount);
            this.eetContent.showError3BgNoChange();
            this.btSend.setEnabled(false);
        } else {
            this.eetContent.hideError3();
            this.btSend.setEnabled(true);
        }
    }

    public void setClick() {
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                switch (view.getId()) {
                    case R.id.bt_send:
                        if (voteIsSending) {
                            return;
                        }
                        ((VoteConfirmPresenter) mPresenter).vote(voteCount, type);
                        return;
                    case R.id.iv_close_two:
                        exit();
                        return;
                    case R.id.tv_address:
                        goFreezeAct();
                        return;
                    case R.id.tv_cancel_all:
                        if (type == 1) {
                            etNewPassword.removeTextChangedListener(textWatcher);
                            etNewPassword.setText(String.valueOf(mVotedCount));
                            etNewPassword.addTextChangedListener(textWatcher);
                            SingleVoteFragment singleVoteFragment = SingleVoteFragment.this;
                            singleVoteFragment.checkInputNum(singleVoteFragment.mVotedCount);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        };
        this.binding.btSend.setOnClickListener(noDoubleClickListener2);
        this.binding.ivCloseTwo.setOnClickListener(noDoubleClickListener2);
        this.binding.tvAddress.setOnClickListener(noDoubleClickListener2);
        this.binding.tvCancelAll.setOnClickListener(noDoubleClickListener2);
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setType(0);
        FgVoteComfirmBinding inflate = FgVoteComfirmBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId(BatchVoteFooterResourceBinding.bind(root));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId(BatchVoteFooterResourceBinding batchVoteFooterResourceBinding) {
        this.ivCloseTwo = this.binding.ivCloseTwo;
        this.tvTitle = this.binding.tvTitle;
        this.tvGetVote = this.binding.tvAddress;
        this.rlFirst = this.binding.rlFirst;
        this.tvVoted = this.binding.tvReceive;
        this.rlSecond = this.binding.rlSecond;
        this.tvAvailable = this.binding.tvResource;
        this.rlThird = this.binding.rlThird;
        this.etNewPassword = this.binding.etVoteAmount;
        this.eetContent = this.binding.eetContent;
        this.tvEstimated = this.binding.tvEstimated;
        this.rlEstimated = this.binding.rlEstimated;
        this.btSend = this.binding.btSend;
        this.root = this.binding.root;
        this.tvCancelAll = this.binding.tvCancelAll;
        this.llCancelVoteError = this.binding.llCancelVoteError;
        this.tvLeftAvailable = this.binding.tvLeftAvailable;
        this.tvVoteOrNot = this.binding.tvLeftReceive;
        this.tvFeeCost = batchVoteFooterResourceBinding.tvFeeCost;
        this.tvCost = batchVoteFooterResourceBinding.tvCost;
    }

    public void goFreezeAct() {
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        this.walletName = selectedWallet == null ? selectedWallet.getWalletName() : "";
        if (SpAPI.THIS.isFirstVoteFreeze(this.walletName)) {
            showDialog();
        } else {
            StakeTRXActivity.start(this.mContext, this.account);
        }
    }

    private void showDialog() {
        SpAPI.THIS.setIsFirstVoteFreeze(this.walletName, false);
        Common5Dialog cancleBt = new Common5Dialog(this.mContext).setTitle(R.string.tips).setContent(R.string.vote_freeze_tips).setBtListener(R.string.do_freezing, new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showDialog$2(view);
            }
        }).setCancleBt(R.string.temporarily, new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showDialog$3(view);
            }
        });
        this.dialog = cancleBt;
        cancleBt.show();
    }

    public void lambda$showDialog$2(View view) {
        StakeTRXActivity.start(this.mContext, this.account);
        this.dialog.dismiss();
    }

    public void lambda$showDialog$3(View view) {
        this.dialog.dismiss();
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        setClick();
        return onCreateView;
    }

    @Override
    public void updateVoteCount(long j, long j2) {
        this.tvAvailable.setText(String.valueOf(this.mVotedCount + j2));
        this.availableVote = j2;
        this.allVotes = j;
    }

    @Override
    public void updateVotedNumber(final long j) {
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$updateVotedNumber$4(j);
            }
        });
    }

    public void lambda$updateVotedNumber$4(long j) {
        this.mVotedCount = j;
        if (isVisible()) {
            this.tvVoted.setText(String.valueOf(j));
            if (this.type == 2) {
                this.tvAvailable.setText(String.valueOf(this.availableVote + this.mVotedCount));
                this.etNewPassword.removeTextChangedListener(this.textWatcher);
                this.etNewPassword.addTextChangedListener(this.textWatcher);
            }
        }
    }

    @Override
    public void showNoNetError() {
        if (isVisible()) {
            toast(getString(R.string.net_error));
        }
    }

    @Override
    public void showLoading(boolean z) {
        if (getActivity() == null || getActivity().isDestroyed() || getActivity().isFinishing()) {
            return;
        }
        this.voteIsSending = z;
        try {
            if (z) {
                showLoadingDialog(getString(R.string.loading));
            } else {
                dismissLoadingDialog();
            }
        } catch (Exception e) {
            SentryUtil.captureException(e);
        }
    }
}
