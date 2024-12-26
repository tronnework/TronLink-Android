package com.tron.wallet.common.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.common.bean.Token;
import com.tron.wallet.common.bean.token.TRC20Output;
import com.tron.wallet.common.components.SwitchView;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.ItemICustomassetsBinding;
import com.tronlinkpro.wallet.R;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
public class CustomInnerAssetsAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final NumberFormat numberFormat;
    private TRC20Output output;
    private double pow;
    private Token token;
    public List<Token> trc10List;
    public List<TRC20Output> trc20List;
    public int type;

    public CustomInnerAssetsAdapter(int i, List<Token> list, List<TRC20Output> list2) {
        this.type = i;
        this.trc10List = list;
        this.trc20List = list2;
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.numberFormat = numberInstance;
        numberInstance.setMaximumFractionDigits(7);
    }

    public void setTrc10List(List<Token> list) {
        this.trc10List = list;
        notifyDataSetChanged();
    }

    public void setTrc20List(List<TRC20Output> list) {
        this.trc20List = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_i_customassets, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        int i2 = this.type;
        if (i2 == 1 || i2 == 2) {
            this.token = this.trc10List.get(i);
            if (i == 0 && this.type == 1) {
                viewHolder.image.setImageResource(R.mipmap.trx);
                viewHolder.switchview.setVisibility(View.GONE);
                viewHolder.tvAmount.setText(this.numberFormat.format(this.token.getTrxAmount()));
            } else {
                viewHolder.image.setImageResource(R.mipmap.ic_token_default);
                viewHolder.switchview.setVisibility(View.VISIBLE);
                viewHolder.tvAmount.setText(this.numberFormat.format(this.token.getAmount()));
            }
            viewHolder.tvName.setText(this.token.getName());
            viewHolder.switchview.setOpened(this.token.isSelected());
            if (this.type == 2) {
                viewHolder.switchview.setVisibility(View.GONE);
            }
        } else if (i2 == 3 || i2 == 4) {
            TRC20Output tRC20Output = this.trc20List.get(i);
            this.output = tRC20Output;
            this.pow = Math.pow(10.0d, tRC20Output.decimals);
            if (StringTronUtil.isEmpty(this.output.icon_url)) {
                viewHolder.image.setImageResource(R.mipmap.ic_token_default);
            } else {
                viewHolder.image.setImageURI(this.output.icon_url);
            }
            if (StringTronUtil.isEmpty(this.output.symbol)) {
                viewHolder.tvName.setText(this.output.name);
            } else {
                viewHolder.tvName.setText(this.output.symbol);
            }
            viewHolder.switchview.setOpened(this.output.isSelected);
            viewHolder.tvAmount.setText(this.numberFormat.format(this.output.amount / this.pow));
            if (this.type == 4) {
                viewHolder.switchview.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        List<TRC20Output> list;
        int i = this.type;
        if (i == 1 || i == 2) {
            List<Token> list2 = this.trc10List;
            if (list2 == null) {
                return 0;
            }
            return list2.size();
        } else if ((i == 3 || i == 4) && (list = this.trc20List) != null) {
            return list.size();
        } else {
            return 0;
        }
    }

    public static class ViewHolder extends BaseHolder {
        ItemICustomassetsBinding binding;
        SimpleDraweeView image;
        SwitchView switchview;
        TextView tvAmount;
        TextView tvName;

        public ViewHolder(View view) {
            super(view);
            ItemICustomassetsBinding bind = ItemICustomassetsBinding.bind(view);
            this.binding = bind;
            this.image = bind.image;
            this.tvName = this.binding.tvName;
            this.tvAmount = this.binding.tvAmount;
            this.switchview = this.binding.switchview;
        }
    }
}
