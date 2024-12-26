package com.tron.wallet.common.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.databinding.ItemSignTransaction2Binding;
import com.tronlinkpro.wallet.R;
public class SignTransactionBottomAdapter extends RecyclerView.Adapter<ViewHolder> {
    private int currentIndex;
    private int lastClick;
    private int size;

    @Override
    public int getItemCount() {
        return this.size;
    }

    public SignTransactionBottomAdapter(int i) {
        this.size = i;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sign_transaction_2, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (i == this.currentIndex) {
            viewHolder.rlBg.setBackgroundResource(R.mipmap.sign_selected);
        } else {
            viewHolder.rlBg.setBackgroundResource(R.mipmap.sign_unselected);
        }
        if (this.lastClick > i) {
            viewHolder.signOk.setVisibility(View.VISIBLE);
        } else {
            viewHolder.signOk.setVisibility(View.GONE);
        }
    }

    public void updateIndex(int i, int i2) {
        this.currentIndex = i;
        this.lastClick = i2;
        notifyDataSetChanged();
    }

    public class ViewHolder extends BaseHolder {
        ItemSignTransaction2Binding binding;
        RelativeLayout rlBg;
        ImageView signOk;

        public ViewHolder(View view) {
            super(view);
            ItemSignTransaction2Binding bind = ItemSignTransaction2Binding.bind(view);
            this.binding = bind;
            this.signOk = bind.signOk;
            this.rlBg = this.binding.rlBg;
        }
    }
}
