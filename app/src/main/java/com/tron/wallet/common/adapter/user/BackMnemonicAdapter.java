package com.tron.wallet.common.adapter.user;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.databinding.ItemTextBgBinding;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class BackMnemonicAdapter extends RecyclerView.Adapter<ViewHolder> {
    public List<String> mList;

    public BackMnemonicAdapter(List<String> list) {
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_text_bg, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.text.setText(this.mList.get(i));
        TextView textView = viewHolder.position;
        textView.setText((i + 1) + "");
    }

    @Override
    public int getItemCount() {
        List<String> list = this.mList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public class ViewHolder extends BaseHolder {
        ItemTextBgBinding binding;
        TextView position;
        TextView text;

        public ViewHolder(View view) {
            super(view);
            ItemTextBgBinding bind = ItemTextBgBinding.bind(view);
            this.binding = bind;
            this.text = bind.text;
            this.position = this.binding.position;
        }
    }
}
