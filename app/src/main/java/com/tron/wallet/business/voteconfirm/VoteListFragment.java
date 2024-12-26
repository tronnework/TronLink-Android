package com.tron.wallet.business.voteconfirm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.wallet.common.adapter.ConfirmExtraTextAdapter;
import com.tron.wallet.databinding.FgVoteComfirmListBinding;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
public class VoteListFragment extends BaseFragment<EmptyPresenter, EmptyModel> {
    private FgVoteComfirmListBinding binding;
    Button btSend;
    RecyclerView rcView;
    TextView tvAddress;
    TextView tvTotal;

    @Override
    protected void processData() {
        setHeaderBar(getString(R.string.vote_batch_vote));
        ConfirmExtraTextAdapter confirmExtraTextAdapter = new ConfirmExtraTextAdapter(new ArrayList());
        this.rcView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.rcView.setAdapter(confirmExtraTextAdapter);
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setType(1);
        FgVoteComfirmListBinding inflate = FgVoteComfirmListBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId() {
        this.tvAddress = this.binding.tvAddress;
        this.tvTotal = this.binding.tvTotal;
        this.rcView = this.binding.rcView;
        this.btSend = this.binding.btSend;
    }
}
