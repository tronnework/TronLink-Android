package com.tron.wallet.common.adapter.holder;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.common.adapter.proposal.ProposalsAdapter;
import com.tron.wallet.common.interfaces.OnSeletedListener;
import com.tron.wallet.common.utils.Common1PopWindow;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.ItemProposalsHeadBinding;
import com.tronlinkpro.wallet.R;
public class ProposalsHeadHolder extends BaseHolder {
    RelativeLayout addressChange;
    private ItemProposalsHeadBinding binding;
    EditText etSearch;
    ImageView ivSearch;
    private ProposalsAdapter.ProposalClickListener listener;
    LinearLayout llApprovedProposals;
    LinearLayout llMyProposals;
    LinearLayout llProposalsSelect;
    private Context mContext;
    private int mCurrentType;
    private Common1PopWindow mPopWindow;
    RelativeLayout rlSearch;
    RelativeLayout rlSearchContent;
    private TextWatcher textWatcher;
    TextView tvAddress;
    TextView tvApprovedProposals;
    TextView tvMyProposals;
    TextView tvVoteRole;

    public ProposalsHeadHolder(View view, Context context, ProposalsAdapter.ProposalClickListener proposalClickListener, int i) {
        super(view);
        mappingId(view);
        this.mContext = context;
        this.listener = proposalClickListener;
        this.mCurrentType = i;
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                if (listener != null) {
                    listener.onEditTextChange(etSearch.getText().toString());
                }
            }
        };
        this.textWatcher = textWatcher;
        this.etSearch.addTextChangedListener(textWatcher);
        this.tvVoteRole.setText(i == 0 ? R.string.all_proposals : R.string.proposals_in_voting);
    }

    public void bindHolder(String str, String str2, String str3) {
        this.tvAddress.setText(str);
        this.tvMyProposals.setText(str2);
        this.tvApprovedProposals.setText(str3);
    }

    public void onViewClicked(View view) {
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view2) {
                switch (view2.getId()) {
                    case R.id.address_change:
                        if (listener != null) {
                            listener.changeAddClick();
                            return;
                        }
                        return;
                    case R.id.ll_approved_proposals:
                        if (listener != null) {
                            listener.approvedProClick();
                            return;
                        }
                        return;
                    case R.id.ll_my_proposals:
                        if (listener != null) {
                            listener.myProposalsClick();
                            return;
                        }
                        return;
                    case R.id.ll_vote_select:
                        showSelectRole();
                        return;
                    default:
                        return;
                }
            }
        };
        this.binding.addressChange.setOnClickListener(noDoubleClickListener2);
        this.binding.llMyProposals.setOnClickListener(noDoubleClickListener2);
        this.binding.llApprovedProposals.setOnClickListener(noDoubleClickListener2);
        this.binding.getRoot().findViewById(R.id.ll_vote_select).setOnClickListener(noDoubleClickListener2);
    }

    public void showSelectRole() {
        UIUtils.hideSoftKeyBoard((BaseActivity) this.mContext);
        initPop();
        this.mPopWindow.showAsDropDown(this.llProposalsSelect);
    }

    private void initPop() {
        this.mPopWindow = new Common1PopWindow(this.mContext, this.llProposalsSelect, this.mCurrentType, new OnSeletedListener() {
            @Override
            public void onSeleted(int i) {
                tvVoteRole.setText(i == 0 ? R.string.all_proposals : R.string.proposals_in_voting);
                mCurrentType = i;
                mPopWindow.dismiss();
                if (listener != null) {
                    listener.onSeleted(mCurrentType);
                }
            }
        }, this.mContext.getResources().getString(R.string.all_proposals), this.mContext.getResources().getString(R.string.proposals_in_voting), 200);
    }

    private void mappingId(View view) {
        this.binding = ItemProposalsHeadBinding.bind(view);
        this.tvAddress = (TextView) view.findViewById(R.id.tv_address);
        this.addressChange = (RelativeLayout) view.findViewById(R.id.address_change);
        this.llMyProposals = (LinearLayout) view.findViewById(R.id.ll_my_proposals);
        this.llApprovedProposals = (LinearLayout) view.findViewById(R.id.ll_approved_proposals);
        this.tvVoteRole = (TextView) view.findViewById(R.id.tv_vote_role);
        this.llProposalsSelect = (LinearLayout) view.findViewById(R.id.ll_vote_select);
        this.etSearch = (EditText) view.findViewById(R.id.et_search);
        this.ivSearch = (ImageView) view.findViewById(R.id.iv_search);
        this.rlSearch = (RelativeLayout) view.findViewById(R.id.rl_search);
        this.rlSearchContent = (RelativeLayout) view.findViewById(R.id.rl_search_content);
        this.tvMyProposals = (TextView) view.findViewById(R.id.tv_my_proposals);
        this.tvApprovedProposals = (TextView) view.findViewById(R.id.tv_approved_proposals);
    }
}
