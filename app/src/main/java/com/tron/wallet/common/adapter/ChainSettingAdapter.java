package com.tron.wallet.common.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.common.bean.ChainBean;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.databinding.ItemChainSettingBinding;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class ChainSettingAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<ChainBean> chainBeanList;
    private IClickListener clickListener;
    private Context context;

    public interface IClickListener {
        void onButtonClick(int i);

        void onRootClick(int i);
    }

    public ChainSettingAdapter(Context context, List<ChainBean> list, IClickListener iClickListener) {
        this.chainBeanList = list;
        this.context = context;
        this.clickListener = iClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chain_setting, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        List<ChainBean> list = this.chainBeanList;
        if (list == null || list.get(i) == null) {
            return;
        }
        ChainBean chainBean = this.chainBeanList.get(i);
        if (chainBean.isSelect) {
            viewHolder.ivSelect.setBackgroundResource(R.mipmap.chain_select);
        } else {
            viewHolder.ivSelect.setBackgroundResource(R.mipmap.chain_unselect);
        }
        viewHolder.tvNodeName.setText(chainBean.chainName);
        viewHolder.tvNode.setText(chainBean.fullNode);
        viewHolder.llSelect.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (clickListener != null) {
                    clickListener.onButtonClick(i);
                }
            }
        });
        viewHolder.root.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (clickListener != null) {
                    clickListener.onRootClick(i);
                }
            }
        });
    }

    public void notify(List<ChainBean> list) {
        this.chainBeanList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        List<ChainBean> list = this.chainBeanList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public static class ViewHolder extends BaseHolder {
        ImageView back3;
        ItemChainSettingBinding binding;
        ImageView ivSelect;
        LinearLayout llSelect;
        RelativeLayout root;
        TextView tvNode;
        TextView tvNodeName;

        public ViewHolder(View view) {
            super(view);
            ItemChainSettingBinding bind = ItemChainSettingBinding.bind(view);
            this.binding = bind;
            this.ivSelect = bind.ivSelect;
            this.tvNodeName = this.binding.tvNodeName;
            this.tvNode = this.binding.tvNode;
            this.back3 = this.binding.back3;
            this.root = this.binding.root;
            this.llSelect = this.binding.llSelect;
        }
    }
}
