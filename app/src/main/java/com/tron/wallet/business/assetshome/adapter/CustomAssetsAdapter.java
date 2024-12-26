package com.tron.wallet.business.assetshome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.wallet.common.adapter.holder.CustomViewHolder;
import com.tron.wallet.common.bean.Token;
import com.tron.wallet.common.bean.token.TRC20Output;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class CustomAssetsAdapter extends RecyclerView.Adapter {
    public static final int TYPE_10_1 = 1;
    public static final int TYPE_10_2 = 2;
    public static final int TYPE_20_1 = 3;
    public static final int TYPE_20_2 = 4;
    public Context context;
    public List<Token> trc10Big;
    public List<Token> trc10Small;
    public List<TRC20Output> trc20Big;
    public List<TRC20Output> trc20Small;

    @Override
    public int getItemCount() {
        return 4;
    }

    public CustomAssetsAdapter(Context context, List<Token> list, List<Token> list2, List<TRC20Output> list3, List<TRC20Output> list4) {
        this.trc10Big = list;
        this.trc10Small = list2;
        this.trc20Big = list3;
        this.trc20Small = list4;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new CustomViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_customassets, viewGroup, false), i, this.context);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        CustomViewHolder customViewHolder = (CustomViewHolder) viewHolder;
        customViewHolder.updateWallsetName();
        if (i == 0) {
            List<Token> list = this.trc10Small;
            if (list == null || list.size() == 0) {
                customViewHolder.showLine();
            }
            customViewHolder.bindTrc10Big(this.trc10Big);
        } else if (i == 1) {
            List<Token> list2 = this.trc10Small;
            if (list2 != null && list2.size() != 0) {
                customViewHolder.showLine();
            }
            customViewHolder.bindTrc10Small(this.trc10Small);
        } else if (i == 2) {
            customViewHolder.bindTrc20Big(this.trc20Big);
        } else if (i != 3) {
        } else {
            customViewHolder.bindTrc20Small(this.trc20Small);
        }
    }

    @Override
    public int getItemViewType(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return super.getItemViewType(i);
                    }
                    return 4;
                }
                return 3;
            }
            return 2;
        }
        return 1;
    }

    public void nitifyData(List<Token> list, List<Token> list2, List<TRC20Output> list3, List<TRC20Output> list4) {
        this.trc10Big = list;
        this.trc10Small = list2;
        this.trc20Big = list3;
        this.trc20Small = list4;
        notifyDataSetChanged();
    }
}
