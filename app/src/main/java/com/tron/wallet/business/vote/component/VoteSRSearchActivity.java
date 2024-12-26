package com.tron.wallet.business.vote.component;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.business.vote.component.VoteSRSearchContract;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.config.CommonBundleKeys;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.DataStatHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.databinding.ActivityVoteSearchSrBinding;
import com.tronlinkpro.wallet.R;
import j$.util.DesugarArrays;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.tron.protos.Protocol;
public class VoteSRSearchActivity extends BaseActivity<VoteSRSearchPresenter, VoteSRSearchModel> implements VoteSRSearchContract.View {
    public static final String WITNESS_LIST = "WITNESS_LIST";
    private Protocol.Account account;
    private ActivityVoteSearchSrBinding binding;
    EditText etSearch;
    private boolean isFromMultisign;
    ImageView ivClear;
    View llSearch;
    NoNetView mNoDataView;
    LinearLayout mNoResultLayout;
    LinearLayout mSRResultLayout;
    RecyclerView mSearchResultRecyclerView;
    LinearLayout mTopSRLayout;
    RecyclerView mTopSRRecyclerView;
    private ArrayList<WitnessOutput.DataBean> myVotedWitness;
    NoNetView netErrorView;
    View placeHolder;
    private String selectAddress;
    private DataStatHelper.StatAction statAction = DataStatHelper.StatAction.Vote;
    TextView tvCancel;
    private ArrayList<WitnessOutput.DataBean> witnesses;

    @Override
    public void beforeLoad() {
    }

    @Override
    public ImageView getIvClear() {
        return this.ivClear;
    }

    @Override
    public RecyclerView getRecycleView() {
        return this.mSearchResultRecyclerView;
    }

    @Override
    public EditText getSearchEt() {
        return this.etSearch;
    }

    @Override
    public DataStatHelper.StatAction getStatAction() {
        return this.statAction;
    }

    @Override
    public RecyclerView getTopSRRecycleView() {
        return this.mTopSRRecyclerView;
    }

    @Override
    public void showNoNetError(boolean z) {
    }

    @Override
    public void updateUI(boolean z, boolean z2, List<WitnessOutput.DataBean> list, List<WitnessOutput.DataBean> list2) {
    }

    @Override
    public boolean visible() {
        return false;
    }

    @Override
    protected void setLayout() {
        ActivityVoteSearchSrBinding inflate = ActivityVoteSearchSrBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 0);
        mappingId();
        setClick();
    }

    private void setClick() {
        DesugarArrays.stream(new View[]{this.tvCancel, this.ivClear}).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$setClick$0((View) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
    }

    public void lambda$setClick$0(View view) {
        view.setOnClickListener(getOnClickListener());
    }

    private View.OnClickListener getOnClickListener() {
        return new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id == R.id.iv_clear) {
                    etSearch.setText("");
                } else if (id != R.id.tv_cancel) {
                } else {
                    finishAfterTransition();
                }
            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.mTopSRLayout = this.binding.llTopResult;
        this.mTopSRRecyclerView = this.binding.rvTopSr;
        this.mSRResultLayout = this.binding.llSrResult;
        this.mSearchResultRecyclerView = this.binding.rvSrResult;
        this.llSearch = this.binding.llSearch;
        this.etSearch = this.binding.etSearch;
        this.ivClear = this.binding.ivClear;
        this.tvCancel = this.binding.tvCancel;
        this.mNoResultLayout = this.binding.llNoresult;
        this.mNoDataView = this.binding.noDataView;
        this.netErrorView = this.binding.netErrorView;
        this.placeHolder = this.binding.placeHolder;
    }

    @Override
    protected void processData() {
        ViewCompat.setTransitionName(this.llSearch, "llSearch");
        this.account = (Protocol.Account) getIntent().getSerializableExtra("key_account");
        this.selectAddress = getIntent().getStringExtra("key_controller_address");
        this.witnesses = getIntent().getParcelableArrayListExtra(CommonBundleKeys.KEY_SELECTED_WITNESSES);
        this.isFromMultisign = getIntent().getBooleanExtra(CommonBundleKeys.KEY_FROM_MULTI_SIGN, false);
        this.myVotedWitness = getIntent().getParcelableArrayListExtra(CommonBundleKeys.KEY_ALREADY_VOTED_WITNESSES);
        this.statAction = (DataStatHelper.StatAction) getIntent().getSerializableExtra(TronConfig.StatAction_Key);
        this.etSearch.setInputType(1);
        this.etSearch.setImeOptions(6);
        updateState(VoteSRSearchContract.Presenter.State.STATE_IDLE);
        ((VoteSRSearchPresenter) this.mPresenter).addSome(this.account, this.selectAddress, this.witnesses, this.isFromMultisign);
        this.netErrorView.setOnReloadClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$processData$1(view);
            }
        });
    }

    public void lambda$processData$1(View view) {
        ((VoteSRSearchPresenter) this.mPresenter).getData();
    }

    @Override
    public void updateState(VoteSRSearchContract.Presenter.State state) {
        if (state == VoteSRSearchContract.Presenter.State.NORMAL) {
            LinearLayout linearLayout = this.mTopSRLayout;
            if (linearLayout != null) {
                linearLayout.setVisibility(View.GONE);
            }
            LinearLayout linearLayout2 = this.mSRResultLayout;
            if (linearLayout2 != null) {
                linearLayout2.setVisibility(View.VISIBLE);
            }
            NoNetView noNetView = this.netErrorView;
            if (noNetView != null) {
                noNetView.setVisibility(View.GONE);
            }
            LinearLayout linearLayout3 = this.mNoResultLayout;
            if (linearLayout3 != null) {
                linearLayout3.setVisibility(View.GONE);
            }
            View view = this.placeHolder;
            if (view != null) {
                view.setVisibility(View.GONE);
            }
        } else if (state == VoteSRSearchContract.Presenter.State.STATE_IDLE) {
            LinearLayout linearLayout4 = this.mTopSRLayout;
            if (linearLayout4 != null) {
                linearLayout4.setVisibility(View.VISIBLE);
            }
            View view2 = this.placeHolder;
            if (view2 != null) {
                view2.setVisibility(View.GONE);
            }
            NoNetView noNetView2 = this.netErrorView;
            if (noNetView2 != null) {
                noNetView2.setVisibility(View.GONE);
            }
            LinearLayout linearLayout5 = this.mNoResultLayout;
            if (linearLayout5 != null) {
                linearLayout5.setVisibility(View.GONE);
            }
            LinearLayout linearLayout6 = this.mSRResultLayout;
            if (linearLayout6 != null) {
                linearLayout6.setVisibility(View.GONE);
            }
        } else if (state == VoteSRSearchContract.Presenter.State.ERROR) {
            LinearLayout linearLayout7 = this.mTopSRLayout;
            if (linearLayout7 != null) {
                linearLayout7.setVisibility(View.GONE);
            }
            View view3 = this.placeHolder;
            if (view3 != null) {
                view3.setVisibility(View.GONE);
            }
            NoNetView noNetView3 = this.netErrorView;
            if (noNetView3 != null) {
                noNetView3.setVisibility(View.VISIBLE);
            }
            LinearLayout linearLayout8 = this.mNoResultLayout;
            if (linearLayout8 != null) {
                linearLayout8.setVisibility(View.GONE);
            }
            LinearLayout linearLayout9 = this.mSRResultLayout;
            if (linearLayout9 != null) {
                linearLayout9.setVisibility(View.GONE);
            }
        } else if (state == VoteSRSearchContract.Presenter.State.PLACE_HOLDER) {
            LinearLayout linearLayout10 = this.mTopSRLayout;
            if (linearLayout10 != null) {
                linearLayout10.setVisibility(View.GONE);
            }
            LinearLayout linearLayout11 = this.mSRResultLayout;
            if (linearLayout11 != null) {
                linearLayout11.setVisibility(View.GONE);
            }
            View view4 = this.placeHolder;
            if (view4 != null) {
                view4.setVisibility(View.VISIBLE);
            }
            NoNetView noNetView4 = this.netErrorView;
            if (noNetView4 != null) {
                noNetView4.setVisibility(View.GONE);
            }
            LinearLayout linearLayout12 = this.mNoResultLayout;
            if (linearLayout12 != null) {
                linearLayout12.setVisibility(View.GONE);
            }
        } else if (state == VoteSRSearchContract.Presenter.State.NO_DATA) {
            LinearLayout linearLayout13 = this.mTopSRLayout;
            if (linearLayout13 != null) {
                linearLayout13.setVisibility(View.GONE);
            }
            NoNetView noNetView5 = this.netErrorView;
            if (noNetView5 != null) {
                noNetView5.setVisibility(View.GONE);
            }
            LinearLayout linearLayout14 = this.mNoResultLayout;
            if (linearLayout14 != null) {
                linearLayout14.setVisibility(View.VISIBLE);
            }
            LinearLayout linearLayout15 = this.mSRResultLayout;
            if (linearLayout15 != null) {
                linearLayout15.setVisibility(View.GONE);
            }
            View view5 = this.placeHolder;
            if (view5 != null) {
                view5.setVisibility(View.GONE);
            }
        } else if (state == VoteSRSearchContract.Presenter.State.ALL_GONE) {
            LinearLayout linearLayout16 = this.mTopSRLayout;
            if (linearLayout16 != null) {
                linearLayout16.setVisibility(View.GONE);
            }
            NoNetView noNetView6 = this.netErrorView;
            if (noNetView6 != null) {
                noNetView6.setVisibility(View.GONE);
            }
            LinearLayout linearLayout17 = this.mNoResultLayout;
            if (linearLayout17 != null) {
                linearLayout17.setVisibility(View.GONE);
            }
            LinearLayout linearLayout18 = this.mSRResultLayout;
            if (linearLayout18 != null) {
                linearLayout18.setVisibility(View.GONE);
            }
            View view6 = this.placeHolder;
            if (view6 != null) {
                view6.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void updateSearchUI(boolean z, boolean z2) {
        if (z && z2) {
            updateState(VoteSRSearchContract.Presenter.State.NO_DATA);
        }
    }
}
