package com.tron.wallet.business.tabmy.proposals.makeproposal;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.wallet.business.tabmy.proposals.makeproposal.MakeProposalContract;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.AcMakeProposalsBinding;
import com.tronlinkpro.wallet.R;
import java.util.HashMap;
public class MakeProposalActivity extends BaseActivity<MakeProposalPresenter, MakeProposalModel> implements MakeProposalContract.View {
    public static final String DEALPARAMETERS = "DEALPARAMETERS";
    public static final String FROMTYPE = "FROMTYPE";
    public static final String SELECTADDRESS = "SELECTADDRESS";
    public static final String TYPE_DEAL = "TYPE_DEAL";
    private AcMakeProposalsBinding binding;
    LinearLayout llContent;
    RecyclerView rcLayout;
    TextView tvConfirm;
    TextView tvTitle;
    private String typeFrom;

    @Override
    public RecyclerView getRc() {
        return this.rcLayout;
    }

    @Override
    protected void setLayout() {
        AcMakeProposalsBinding inflate = AcMakeProposalsBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        setView(root, 3);
        Intent intent = getIntent();
        if (intent != null) {
            this.typeFrom = intent.getStringExtra(FROMTYPE);
        }
        if (!StringTronUtil.isEmpty(this.typeFrom) && this.typeFrom.equals(TYPE_DEAL)) {
            setHeaderBar(getString(R.string.proposed_parameters));
        } else {
            setHeaderBar(getString(R.string.make_proposal));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.rcLayout = this.binding.rcLayout;
        this.tvConfirm = this.binding.tvConfirm;
        this.llContent = this.binding.llContent;
        this.tvTitle = this.binding.tvTitle;
    }

    @Override
    protected void processData() {
        if (!StringTronUtil.isEmpty(this.typeFrom) && this.typeFrom.equals(TYPE_DEAL)) {
            this.tvTitle.setVisibility(View.GONE);
        } else {
            this.tvTitle.setVisibility(View.VISIBLE);
        }
        ((MakeProposalPresenter) this.mPresenter).addSome();
        ((MakeProposalPresenter) this.mPresenter).getData();
        this.tvConfirm.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                ((MakeProposalPresenter) mPresenter).confirm();
            }
        });
    }

    public static void start(Context context, String str) {
        Intent intent = new Intent(context, MakeProposalActivity.class);
        intent.putExtra(SELECTADDRESS, str);
        context.startActivity(intent);
    }

    public static void start(Context context, String str, HashMap<Long, Long> hashMap, String str2) {
        Intent intent = new Intent(context, MakeProposalActivity.class);
        intent.putExtra(SELECTADDRESS, str);
        intent.putExtra(DEALPARAMETERS, hashMap);
        intent.putExtra(FROMTYPE, str2);
        context.startActivity(intent);
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }

    @Override
    public void showLoading(boolean z) {
        if (z) {
            showLoading(getString(R.string.loading));
        } else {
            dismissLoading();
        }
    }

    @Override
    public Intent getIIntent() {
        return getIntent();
    }

    @Override
    public void showOrHideCreate(boolean z) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.llContent.getLayoutParams();
        if (z) {
            this.tvConfirm.setVisibility(View.VISIBLE);
            layoutParams.bottomMargin = UIUtils.dip2px(this, 60.0f);
        } else {
            this.tvConfirm.setVisibility(View.GONE);
            layoutParams.bottomMargin = UIUtils.dip2px(this, 10.0f);
        }
        this.llContent.setLayoutParams(layoutParams);
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            finish();
        }
    }
}
