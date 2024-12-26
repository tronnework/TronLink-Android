package com.tron.wallet.business.voteconfirm;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.databinding.ActivityVoteConfirmBinding;
import com.tronlinkpro.wallet.R;
import io.reactivex.functions.Consumer;
import org.tron.protos.Protocol;
public class VoteConfirmActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    public static final String KEY_ACCOUNT = "key_account";
    public static final String KEY_ALL_VOTES = "allVoteCount";
    public static final String KEY_DATA = "data";
    public static final String KEY_SELECT_ADDRESS = "key_select_address";
    public static final String KEY_TYPE = "type";
    public static final String KEY_WITNESS_OUTPUT = "key_witness_output";
    public static final int VOTE = 0;
    public static final int VOTE_BATCH = 3;
    public static final int VOTE_BATCH_CANCEL = 4;
    public static final int VOTE_CANCEL = 1;
    public static final int VOTE_UPDATE = 2;
    public static final int VOTE_WITHDRAW = 5;
    private ActivityVoteConfirmBinding binding;
    ImageView btLoadding;
    FrameLayout flfFragment;
    private BaseFragment fragment;
    View llNoNet;
    private FragmentManager mFragmentManager;
    TextView netError;
    RelativeLayout rlReload;
    RelativeLayout root;
    TextView tvName;
    TextView tvReload;

    @Override
    protected void setLayout() {
        ActivityVoteConfirmBinding inflate = ActivityVoteConfirmBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        setView(root, 0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.flfFragment = this.binding.fragment;
        this.netError = this.binding.llNoNet.netError;
        this.tvReload = this.binding.llNoNet.tvReload;
        this.btLoadding = this.binding.llNoNet.btLoadding;
        this.rlReload = this.binding.llNoNet.rlReload;
        this.llNoNet = this.binding.llNoNet.getRoot();
        this.tvName = this.binding.llNoData.tvName;
        this.root = this.binding.root;
    }

    @Override
    protected void processData() {
        this.mFragmentManager = getSupportFragmentManager();
        int intExtra = getIntent().getIntExtra("type", 0);
        WitnessOutput.DataBean dataBean = (WitnessOutput.DataBean) getIntent().getParcelableExtra("data");
        long longExtra = getIntent().getLongExtra(KEY_ALL_VOTES, 0L);
        WitnessOutput witnessOutput = (WitnessOutput) getIntent().getParcelableExtra("key_witness_output");
        Protocol.Account account = (Protocol.Account) getIntent().getSerializableExtra("key_account");
        String stringExtra = getIntent().getStringExtra("key_select_address");
        TronConfig.currentPwdType = 9;
        if (intExtra == 0 || intExtra == 1 || intExtra == 2) {
            this.fragment = SingleVoteFragment.get(this.mContext, account, witnessOutput, intExtra, dataBean, longExtra, stringExtra);
        } else if (intExtra == 3 || intExtra == 4) {
            this.fragment = new VoteListFragment();
        } else if (intExtra == 5) {
            this.fragment = new VoteWithDrawFragment();
        }
        if (this.fragment != null) {
            this.mFragmentManager.beginTransaction().replace(R.id.fragment, this.fragment).commit();
        }
        ((EmptyPresenter) this.mPresenter).mRxManager.on(Event.BroadcastSuccess, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$processData$0(obj);
            }
        });
    }

    public void lambda$processData$0(Object obj) throws Exception {
        exit();
    }

    @Override
    public void onCreate1(Bundle bundle) {
        super.onCreate1(bundle);
        if (Build.VERSION.SDK_INT == 26 || Build.VERSION.SDK_INT == 27) {
            setRequestedOrientation(2);
        } else {
            setRequestedOrientation(1);
        }
    }

    public void hideCurrentFragment(boolean z) {
        FragmentManager fragmentManager;
        if (this.fragment == null || (fragmentManager = this.mFragmentManager) == null) {
            return;
        }
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        if (this.fragment.isAdded()) {
            if (z) {
                beginTransaction.hide(this.fragment);
            } else {
                beginTransaction.show(this.fragment);
            }
            beginTransaction.commit();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        hideCurrentFragment(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        hideCurrentFragment(false);
    }
}
