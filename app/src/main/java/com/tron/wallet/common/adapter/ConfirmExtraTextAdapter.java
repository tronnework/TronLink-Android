package com.tron.wallet.common.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.common.bean.ConfirmExtraText;
import com.tron.wallet.databinding.ItemConfirmExtraBinding;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class ConfirmExtraTextAdapter extends RecyclerView.Adapter<ViewHolder> {
    private ConfirmExtraText confirmExtraText;
    public List<ConfirmExtraText> mLists;
    private int textGravity;

    public ConfirmExtraTextAdapter(List<ConfirmExtraText> list) {
        this(list, 8388629);
    }

    public ConfirmExtraTextAdapter(List<ConfirmExtraText> list, int i) {
        this.mLists = list;
        this.textGravity = i;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_confirm_extra, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        List<ConfirmExtraText> list = this.mLists;
        if (list == null || list.size() <= i) {
            return;
        }
        this.confirmExtraText = this.mLists.get(i);
        viewHolder.tvLeft.setText(this.confirmExtraText.getLeft());
        viewHolder.tvRight.setText(this.confirmExtraText.getRight());
        viewHolder.tvRight.setGravity(this.textGravity);
    }

    @Override
    public int getItemCount() {
        List<ConfirmExtraText> list = this.mLists;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public static class ViewHolder extends BaseHolder {
        ItemConfirmExtraBinding binding;
        TextView tvLeft;
        TextView tvRight;

        public ViewHolder(View view) {
            super(view);
            ItemConfirmExtraBinding bind = ItemConfirmExtraBinding.bind(view);
            this.binding = bind;
            this.tvLeft = bind.tvLeft;
            this.tvRight = this.binding.tvRight;
        }
    }
}
