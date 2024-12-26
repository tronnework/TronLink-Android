package com.tron.wallet.business.confirm;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.databinding.ItemNodeTypeBinding;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.HashMap;
public class NodeTypeListAdapter extends RecyclerView.Adapter<ViewHolder> {
    private OnItemClickListener itemClickListener;
    private HashMap<String, Boolean> mEnableMap;
    private ArrayList<String> mNodeTypeList;

    public interface OnItemClickListener {
        void onItemClick(View view, int i);
    }

    public void setItemClickListener(OnItemClickListener onItemClickListener) {
        this.itemClickListener = onItemClickListener;
    }

    public NodeTypeListAdapter(ArrayList<String> arrayList, HashMap<String, Boolean> hashMap) {
        this.mNodeTypeList = arrayList;
        this.mEnableMap = hashMap;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_node_type, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        String str = this.mNodeTypeList.get(i);
        viewHolder.nodeType.setText(str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (this.mEnableMap.get(str).booleanValue()) {
            viewHolder.nodeType.setTextColor(viewHolder.nodeType.getContext().getResources().getColor(R.color.gray_02));
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClick(view, i);
                }
            });
            return;
        }
        viewHolder.nodeType.setTextColor(viewHolder.nodeType.getContext().getResources().getColor(R.color.gray_02_50));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClick(view, i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.mNodeTypeList.size();
    }

    public static class ViewHolder extends BaseHolder {
        private ItemNodeTypeBinding binding;
        TextView nodeType;

        public ViewHolder(View view) {
            super(view);
            mappingId(view);
        }

        public void mappingId(View view) {
            ItemNodeTypeBinding bind = ItemNodeTypeBinding.bind(view);
            this.binding = bind;
            this.nodeType = bind.tvNodeType;
        }
    }
}
