package com.tron.wallet.common.adapter.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.business.walletmanager.backupmnemonic.bean.MnemonicWord;
import com.tron.wallet.databinding.ItemWriteMnemonicBinding;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class WriteMnemonicAdapter extends RecyclerView.Adapter<ViewHolder> {
    private int itemWidth;
    private ClickCallBack mClickCallback;
    private Context mContext;
    List<MnemonicWord> mLists;

    public interface ClickCallBack {
        void onItemClick();
    }

    public void setClickCallback(ClickCallBack clickCallBack) {
        this.mClickCallback = clickCallBack;
    }

    public WriteMnemonicAdapter(List<MnemonicWord> list, Context context, int i) {
        this.mLists = list;
        this.mContext = context;
        this.itemWidth = i;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_write_mnemonic, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        MnemonicWord mnemonicWord = this.mLists.get(i);
        viewHolder.mItemTv.setWidth(this.itemWidth);
        viewHolder.mItemTv.setText(mnemonicWord.word);
        viewHolder.mItemTv.setSelected(mnemonicWord.selected);
        viewHolder.mItemTv.setTag(mnemonicWord);
        viewHolder.mItemTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MnemonicWord mnemonicWord2 = (MnemonicWord) view.getTag();
                if (mnemonicWord2.selected) {
                    mnemonicWord2.selected = false;
                } else {
                    resetStatus();
                    mnemonicWord2.selected = true;
                }
                if (mClickCallback != null) {
                    mClickCallback.onItemClick();
                }
                notifyDataSetChanged();
            }
        });
    }

    public void resetStatus() {
        for (MnemonicWord mnemonicWord : this.mLists) {
            mnemonicWord.selected = false;
        }
    }

    @Override
    public int getItemCount() {
        List<MnemonicWord> list = this.mLists;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public static class ViewHolder extends BaseHolder {
        ItemWriteMnemonicBinding binding;
        TextView mItemTv;

        public ViewHolder(View view) {
            super(view);
            ItemWriteMnemonicBinding bind = ItemWriteMnemonicBinding.bind(view);
            this.binding = bind;
            this.mItemTv = bind.tvItem;
        }
    }

    public void notifyData(List<MnemonicWord> list) {
        this.mLists = list;
        notifyDataSetChanged();
    }
}
