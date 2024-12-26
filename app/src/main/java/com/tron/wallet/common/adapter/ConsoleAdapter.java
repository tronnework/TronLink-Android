package com.tron.wallet.common.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.common.bean.ColdFailLogBean;
import com.tron.wallet.databinding.ItemConsoleBinding;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class ConsoleAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<ColdFailLogBean> data;
    private Context mContext;

    public ConsoleAdapter(Context context, List<ColdFailLogBean> list) {
        this.mContext = context;
        this.data = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_console, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(com.tron.wallet.common.adapter.ConsoleAdapter.ViewHolder r8, int r9) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.common.adapter.ConsoleAdapter.onBindViewHolder(com.tron.wallet.common.adapter.ConsoleAdapter$ViewHolder, int):void");
    }

    public void notify(List<ColdFailLogBean> list) {
        this.data = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        List<ColdFailLogBean> list = this.data;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public static class ViewHolder extends BaseHolder {
        ItemConsoleBinding binding;
        TextView tvContent;

        public ViewHolder(View view) {
            super(view);
            ItemConsoleBinding bind = ItemConsoleBinding.bind(view);
            this.binding = bind;
            this.tvContent = bind.tvContent;
        }
    }
}
