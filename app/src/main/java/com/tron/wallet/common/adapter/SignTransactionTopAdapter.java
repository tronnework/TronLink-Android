package com.tron.wallet.common.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.databinding.ItemSignTransaction3Binding;
import com.tronlinkpro.wallet.R;
public class SignTransactionTopAdapter extends RecyclerView.Adapter<ViewHolder> {
    private int currentIndex;
    private int size;

    @Override
    public int getItemCount() {
        return this.size;
    }

    public SignTransactionTopAdapter(int i) {
        this.size = i;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sign_transaction_3, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (i == this.currentIndex) {
            viewHolder.rlBg.setBackgroundResource(R.drawable.roundborder_white);
        } else {
            viewHolder.rlBg.setBackgroundResource(R.drawable.roundborder_white20_1);
        }
        TextView textView = viewHolder.tvNumber;
        textView.setText((i + 1) + "");
    }

    public void updateIndex(int i) {
        this.currentIndex = i;
        notifyDataSetChanged();
    }

    public class ViewHolder extends BaseHolder {
        ItemSignTransaction3Binding binding;
        RelativeLayout rlBg;
        TextView tvNumber;

        public ViewHolder(View view) {
            super(view);
            ItemSignTransaction3Binding bind = ItemSignTransaction3Binding.bind(view);
            this.binding = bind;
            this.tvNumber = bind.tvNumber;
            this.rlBg = this.binding.rlBg;
        }
    }
}
