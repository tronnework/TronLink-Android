package com.tron.wallet.business.assetshome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.wallet.business.addassets2.bean.TokenSortType;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.ItemVoteSortBinding;
import com.tronlinkpro.wallet.R;
public class AssetSortAdapter extends RecyclerView.Adapter {
    private Context context;
    private itemClick itemClick;
    private int selectedPosition;
    private String[] sortTypeStrings;

    public interface itemClick {
        void onClick(int i, boolean z);
    }

    public void setItemClick(itemClick itemclick) {
        this.itemClick = itemclick;
    }

    public AssetSortAdapter(Context context, int i) {
        this.selectedPosition = i;
        this.context = context;
        if (LanguageUtils.languageZH(context)) {
            this.sortTypeStrings = context.getResources().getStringArray(R.array.asset_sort_zh);
        } else {
            this.sortTypeStrings = context.getResources().getStringArray(R.array.asset_sort_en);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_vote_sort, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        final ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        viewHolder2.iv.setImageResource(R.mipmap.ic_check_unselect);
        if (this.selectedPosition == viewHolder2.getAdapterPosition()) {
            viewHolder2.iv.setImageResource(R.mipmap.ic_check_selected);
        } else {
            viewHolder2.iv.setImageResource(R.mipmap.ic_check_unselect);
        }
        viewHolder2.tv.setText(this.sortTypeStrings[viewHolder2.getAdapterPosition()]);
        viewHolder2.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$onBindViewHolder$0(viewHolder2, view);
            }
        });
        boolean z = TokenSortType.SORT_BY_USER.getValue() == viewHolder2.getAbsoluteAdapterPosition();
        viewHolder2.sortByUser.setVisibility(z ? View.VISIBLE : View.GONE);
        viewHolder2.sortByUser.setEnabled(z);
        viewHolder2.sortByUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$onBindViewHolder$1(viewHolder2, view);
            }
        });
    }

    public void lambda$onBindViewHolder$0(ViewHolder viewHolder, View view) {
        this.selectedPosition = viewHolder.getAdapterPosition();
        itemClick itemclick = this.itemClick;
        if (itemclick != null) {
            itemclick.onClick(viewHolder.getAdapterPosition(), false);
        }
        notifyDataSetChanged();
    }

    public void lambda$onBindViewHolder$1(ViewHolder viewHolder, View view) {
        itemClick itemclick = this.itemClick;
        if (itemclick != null) {
            itemclick.onClick(viewHolder.getAbsoluteAdapterPosition(), true);
        }
    }

    @Override
    public int getItemCount() {
        return this.sortTypeStrings.length;
    }

    public class ViewHolder extends BaseHolder {
        private ItemVoteSortBinding binding;
        ImageView iv;
        TextView sortByUser;
        TextView tv;

        public ViewHolder(View view) {
            super(view);
            mappingId(view);
            int dip2px = UIUtils.dip2px(10.0f);
            int dip2px2 = UIUtils.dip2px(5.0f);
            TouchDelegateUtils.expandViewTouchDelegate(this.sortByUser, dip2px, dip2px2, dip2px, dip2px2);
        }

        public void mappingId(View view) {
            ItemVoteSortBinding bind = ItemVoteSortBinding.bind(view);
            this.binding = bind;
            this.tv = bind.tv;
            this.iv = this.binding.iv;
            this.sortByUser = this.binding.tvSortByUser;
        }
    }
}
