package com.tron.wallet.business.transfer.multisignature;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.databinding.ItemTokenListBinding;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
public class AcitveListAdapter extends RecyclerView.Adapter<ViewHolder> {
    private ArrayList<String> list;

    public AcitveListAdapter(ArrayList<String> arrayList) {
        this.list = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_token_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tokenName.setText(this.list.get(i));
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public static class ViewHolder extends BaseHolder {
        ItemTokenListBinding binding;
        TextView tokenId;
        TextView tokenName;

        public ViewHolder(View view) {
            super(view);
            ItemTokenListBinding bind = ItemTokenListBinding.bind(view);
            this.binding = bind;
            this.tokenName = bind.tokenName;
            this.tokenId = this.binding.tokenId;
        }
    }
}
