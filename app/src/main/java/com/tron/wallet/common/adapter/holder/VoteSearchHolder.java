package com.tron.wallet.common.adapter.holder;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.alibaba.fastjson.asm.Opcodes;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.business.vote.adapter.VoteItemAdapter;
import com.tron.wallet.common.interfaces.OnSeletedListener;
import com.tron.wallet.common.utils.Common1PopWindow;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import org.tron.walletserver.Wallet;
public class VoteSearchHolder extends BaseHolder {
    TextView countDownTime;
    EditText etSearch;
    ImageView ivSearch;
    LinearLayout llVoteSelect;
    private Context mContext;
    private int mCurrentType;
    private PopupWindow mPopWindow;
    private VoteItemAdapter.OnWitnessClickListener mWitnessListener;
    private boolean otherChange;
    TextView reset;
    RelativeLayout rlSearch;
    RelativeLayout rlSearchContent;
    RelativeLayout rlVote;
    private TextWatcher textWatcher;
    TextView tvVoteRole;
    private String walletName;

    public void setOnWitnessClick(VoteItemAdapter.OnWitnessClickListener onWitnessClickListener) {
        this.mWitnessListener = onWitnessClickListener;
    }

    public VoteSearchHolder(View view, Context context, VoteItemAdapter.OnWitnessClickListener onWitnessClickListener, int i) {
        super(view);
        this.otherChange = false;
        mappingId(view);
        this.mWitnessListener = onWitnessClickListener;
        this.mContext = context;
        this.mCurrentType = i;
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        this.walletName = selectedWallet == null ? selectedWallet.getWalletName() : "";
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                if (otherChange || mWitnessListener == null) {
                    return;
                }
                mWitnessListener.onEditTextChange(etSearch.getText().toString());
            }
        };
        this.textWatcher = textWatcher;
        this.etSearch.addTextChangedListener(textWatcher);
        this.rlVote.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view2) {
                if (mWitnessListener != null) {
                    mWitnessListener.voteClick();
                }
            }
        });
        this.llVoteSelect.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view2) {
                showSelectRole();
            }
        });
        this.reset.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view2) {
                if (mWitnessListener != null) {
                    mWitnessListener.resetClick();
                }
            }
        });
    }

    public void bindHolder(String str, String str2) {
        this.countDownTime.setText(str);
        if (str2 != null && !this.etSearch.getText().toString().equals(str2)) {
            this.otherChange = true;
            this.etSearch.setText(str2);
            this.otherChange = false;
        }
        this.tvVoteRole.setText(this.mCurrentType == 0 ? R.string.all_candidates : R.string.i_voted);
    }

    public void updateSearchInput(String str) {
        if (str == null || this.etSearch.getText().toString().equals(str)) {
            return;
        }
        this.otherChange = true;
        this.etSearch.setText(str);
        this.otherChange = false;
    }

    public void updateCurrentType(int i) {
        this.mCurrentType = i;
        this.tvVoteRole.setText(i == 0 ? R.string.all_candidates : R.string.i_voted);
    }

    public void updateCountTime(String str) {
        this.countDownTime.setText(str);
    }

    public void showSelectRole() {
        UIUtils.hideSoftKeyBoard((BaseActivity) this.mContext);
        initPop();
        this.mPopWindow.showAsDropDown(this.llVoteSelect);
    }

    private void initPop() {
        this.mPopWindow = new Common1PopWindow(this.mContext, this.llVoteSelect, this.mCurrentType, new OnSeletedListener() {
            @Override
            public void onSeleted(int i) {
                tvVoteRole.setText(i == 0 ? R.string.all_candidates : R.string.i_voted);
                mCurrentType = i;
                mPopWindow.dismiss();
                if (mWitnessListener != null) {
                    mWitnessListener.onSeleted(mCurrentType);
                }
            }
        }, this.mContext.getResources().getString(R.string.all_candidates), this.mContext.getResources().getString(R.string.i_voted), Opcodes.RET);
    }

    private void mappingId(View view) {
        this.countDownTime = (TextView) view.findViewById(R.id.count_down_time);
        this.reset = (TextView) view.findViewById(R.id.reset);
        this.rlVote = (RelativeLayout) view.findViewById(R.id.rl_vote);
        this.tvVoteRole = (TextView) view.findViewById(R.id.tv_vote_role);
        this.llVoteSelect = (LinearLayout) view.findViewById(R.id.ll_vote_select);
        this.etSearch = (EditText) view.findViewById(R.id.et_search);
        this.ivSearch = (ImageView) view.findViewById(R.id.iv_search);
        this.rlSearch = (RelativeLayout) view.findViewById(R.id.rl_search);
        this.rlSearchContent = (RelativeLayout) view.findViewById(R.id.rl_search_content);
    }
}
