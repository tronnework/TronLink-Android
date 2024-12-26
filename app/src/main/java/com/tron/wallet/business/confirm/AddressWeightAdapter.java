package com.tron.wallet.business.confirm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.ItemAddressWeightBinding;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import org.tron.protos.Protocol;
public class AddressWeightAdapter extends RecyclerView.Adapter<ViewHolder> {
    private String mCurWalletAddress;
    private ArrayList<Protocol.Key> mKeyList;

    public AddressWeightAdapter(ArrayList<Protocol.Key> arrayList, String str) {
        this.mKeyList = arrayList;
        this.mCurWalletAddress = str;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_address_weight, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Protocol.Key key = this.mKeyList.get(i);
        String encode58Check = StringTronUtil.encode58Check(key.getAddress().toByteArray());
        viewHolder.addressTv.setText(encode58Check);
        if (encode58Check != null && encode58Check.equals(this.mCurWalletAddress)) {
            viewHolder.addressTv.setTextColor(viewHolder.itemView.getContext().getResources().getColor(R.color.blue_3c));
            viewHolder.weightTv.setTextColor(viewHolder.itemView.getContext().getResources().getColor(R.color.black_23));
        } else {
            viewHolder.addressTv.setTextColor(viewHolder.itemView.getContext().getResources().getColor(R.color.gray_9B));
            viewHolder.weightTv.setTextColor(viewHolder.itemView.getContext().getResources().getColor(R.color.gray_9B));
        }
        viewHolder.weightTv.setText(String.valueOf(key.getWeight()));
    }

    @Override
    public int getItemCount() {
        return this.mKeyList.size();
    }

    public static class ViewHolder extends BaseHolder {
        TextView addressTv;
        private ItemAddressWeightBinding binding;
        TextView weightTv;

        public ViewHolder(View view) {
            super(view);
            mappingId(view);
        }

        public void mappingId(View view) {
            ItemAddressWeightBinding bind = ItemAddressWeightBinding.bind(view);
            this.binding = bind;
            this.addressTv = bind.tvAddress;
            this.weightTv = this.binding.tvWeight;
        }
    }
}
