package com.tron.wallet.business.vote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tronlinkpro.wallet.R;
public class VoteSortAdapter extends RecyclerView.Adapter {
    private Context context;
    private itemClick itemClick;
    private int seletedPosition;
    private String[] str;

    public interface itemClick {
        void onClick(int i);
    }

    public void setItemClick(itemClick itemclick) {
        this.itemClick = itemclick;
    }

    public VoteSortAdapter(Context context, int i) {
        this.seletedPosition = i;
        this.context = context;
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            this.str = context.getResources().getStringArray(R.array.vote_sort_zh);
        } else {
            this.str = context.getResources().getStringArray(R.array.vote_sort_en);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_vote_sort, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        final ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        if (this.seletedPosition == viewHolder2.getAdapterPosition()) {
            viewHolder2.tv.setTextColor(this.context.getResources().getColor(R.color.blue_13_70));
            viewHolder2.iv.setImageResource(R.mipmap.vote_sort_seleted);
        } else {
            viewHolder2.tv.setTextColor(this.context.getResources().getColor(R.color.gray_99));
            viewHolder2.iv.setImageResource(R.mipmap.vote_sort_unselet);
        }
        viewHolder2.tv.setText(this.str[viewHolder2.getAdapterPosition()]);
        viewHolder2.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$onBindViewHolder$0(viewHolder2, view);
            }
        });
    }

    public void lambda$onBindViewHolder$0(ViewHolder viewHolder, View view) {
        int adapterPosition = viewHolder.getAdapterPosition();
        this.seletedPosition = adapterPosition;
        notifyItemChanged(adapterPosition);
        this.itemClick.onClick(viewHolder.getAdapterPosition());
    }

    @Override
    public int getItemCount() {
        return this.str.length;
    }

    public class ViewHolder extends BaseHolder {
        ImageView iv;
        TextView tv;

        public ViewHolder(View view) {
            super(view);
        }

        private void mappingId(View view) {
            this.tv = (TextView) view.findViewById(R.id.tv);
            this.iv = (ImageView) view.findViewById(R.id.iv);
        }
    }
}
