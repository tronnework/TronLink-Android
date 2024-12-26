package com.tron.wallet.business.vote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.DensityUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tronlinkpro.wallet.R;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.tron.protos.contract.WitnessContract;
public class VoteWitnessContractAdapter extends RecyclerView.Adapter<ViewHolder> {
    private HashMap<String, String> hashMap;
    private Context mContext;
    List<WitnessContract.VoteWitnessContract.Vote> mLists;
    private WitnessContract.VoteWitnessContract.Vote vote;
    private boolean isShowDetails = false;
    private NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);

    public void showVotingTetail(boolean z) {
        this.isShowDetails = z;
    }

    public VoteWitnessContractAdapter(Context context, List<WitnessContract.VoteWitnessContract.Vote> list, HashMap<String, String> hashMap) {
        this.mLists = list;
        this.hashMap = hashMap;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_contract_vote, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        TextView textView = viewHolder.tvIndex;
        textView.setText((i + 1) + "");
        this.vote = this.mLists.get(i);
        viewHolder.tvAddress.setText(StringTronUtil.encode58Check(this.vote.getVoteAddress().toByteArray()));
        viewHolder.tvVote.setText(this.numberFormat.format(this.vote.getVoteCount()));
        String str = this.hashMap.get(StringTronUtil.encode58Check(this.vote.getVoteAddress().toByteArray()));
        String str2 = str != null ? str : "";
        if (this.isShowDetails) {
            viewHolder.llDelete.setVisibility(View.GONE);
        }
        viewHolder.tvName.setText(str2);
        if (this.mLists.size() == 1) {
            viewHolder.llBg.setBackgroundResource(R.drawable.ripple_createbg);
            viewHolder.llBg.setPadding(DensityUtils.dp2px(this.mContext, 0.0f), DensityUtils.dp2px(this.mContext, 34.0f), 0, DensityUtils.dp2px(this.mContext, 41.0f));
            viewHolder.llDelete.setPadding(0, DensityUtils.dp2px(this.mContext, 12.0f), DensityUtils.dp2px(this.mContext, 40.0f), 0);
        } else if (i == 0) {
            viewHolder.llBg.setBackgroundResource(R.drawable.ripple_shadow1);
            viewHolder.llBg.setPadding(0, DensityUtils.dp2px(this.mContext, 30.0f), 0, DensityUtils.dp2px(this.mContext, 4.0f));
            viewHolder.llDelete.setPadding(0, DensityUtils.dp2px(this.mContext, 15.0f), DensityUtils.dp2px(this.mContext, 40.0f), 0);
        } else if (i == this.mLists.size() - 1) {
            viewHolder.llBg.setBackgroundResource(R.drawable.ripple_shadow3);
            viewHolder.llBg.setPadding(0, DensityUtils.dp2px(this.mContext, 15.0f), 0, DensityUtils.dp2px(this.mContext, 23.0f));
            viewHolder.llDelete.setPadding(0, DensityUtils.dp2px(this.mContext, -3.0f), DensityUtils.dp2px(this.mContext, 40.0f), 0);
        } else {
            viewHolder.llBg.setBackgroundResource(R.drawable.ripple_shadow2);
            viewHolder.llBg.setPadding(0, DensityUtils.dp2px(this.mContext, 15.0f), 0, DensityUtils.dp2px(this.mContext, 4.0f));
            viewHolder.llDelete.setPadding(0, DensityUtils.dp2px(this.mContext, -3.0f), DensityUtils.dp2px(this.mContext, 40.0f), 0);
        }
        viewHolder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new RxManager().post(Event.DELETE_EVENT, Integer.valueOf(i));
            }
        });
    }

    public void notifyData(List<WitnessContract.VoteWitnessContract.Vote> list) {
        this.mLists = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        List<WitnessContract.VoteWitnessContract.Vote> list = this.mLists;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public static class ViewHolder extends BaseHolder {
        ImageView ivDelete;
        LinearLayout llBg;
        LinearLayout llDelete;
        TextView tvAddress;
        TextView tvIndex;
        TextView tvName;
        TextView tvVote;

        public ViewHolder(View view) {
            super(view);
            mappingId(view);
        }

        private void mappingId(View view) {
            this.tvAddress = (TextView) view.findViewById(R.id.tv_address);
            this.tvVote = (TextView) view.findViewById(R.id.tv_vote);
            this.tvName = (TextView) view.findViewById(R.id.tv_name);
            this.llBg = (LinearLayout) view.findViewById(R.id.ll_bg);
            this.tvIndex = (TextView) view.findViewById(R.id.tv_index);
            this.ivDelete = (ImageView) view.findViewById(R.id.iv_delete);
            this.llDelete = (LinearLayout) view.findViewById(R.id.ll_delete);
        }
    }
}
