package com.tron.wallet.business.voteconfirm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.wallet.databinding.FgVoteWithdrawBinding;
public class VoteWithDrawFragment extends BaseFragment<EmptyPresenter, EmptyModel> {
    private FgVoteWithdrawBinding binding;
    Button btSend;
    LinearLayout ivCloseTwo;
    RelativeLayout root;
    TextView tvAddress;
    TextView tvReceive;
    TextView tvResource;

    @Override
    protected void processData() {
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setType(0);
        FgVoteWithdrawBinding inflate = FgVoteWithdrawBinding.inflate(layoutInflater, viewGroup, false);
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
        this.ivCloseTwo = this.binding.ivCloseTwo;
        this.tvAddress = this.binding.tvAddress;
        this.tvReceive = this.binding.tvReceive;
        this.tvResource = this.binding.tvResource;
        this.btSend = this.binding.btSend;
        this.root = this.binding.root;
    }
}
