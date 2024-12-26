package com.tron.wallet.common.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.ItemIndicator1Binding;
import com.tronlinkpro.wallet.R;
public class IndicatorAdapter extends RecyclerView.Adapter<ViewHolder> {
    public int selectedPosition = 0;
    public int size;

    @Override
    public int getItemCount() {
        return this.size;
    }

    public IndicatorAdapter(int i) {
        this.size = i;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_indicator1, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) viewHolder.indicator.getLayoutParams();
        if (this.selectedPosition == i) {
            viewHolder.indicator.setBackgroundResource(R.drawable.roundborder_021c31_60);
            marginLayoutParams.height = UIUtils.dip2px(10.0f);
        } else {
            int i2 = this.size;
            if (i2 > 20) {
                if (i == 0) {
                    viewHolder.indicator.setBackgroundResource(R.drawable.roundborder_021c31_7);
                } else if (i == i2 - 1) {
                    viewHolder.indicator.setBackgroundResource(R.drawable.roundborder_021c31_8);
                } else {
                    viewHolder.indicator.setBackgroundResource(R.color.black_02_10);
                }
                int i3 = this.size;
                if (i3 > 40) {
                    marginLayoutParams.height = UIUtils.dip2px(160 / i3);
                } else {
                    marginLayoutParams.height = UIUtils.dip2px(4.0f);
                }
            } else {
                viewHolder.indicator.setBackgroundResource(R.drawable.roundborder_021c31_4);
                marginLayoutParams.height = UIUtils.dip2px(4.0f);
            }
        }
        if (this.size > 20) {
            marginLayoutParams.setMargins(0, 0, 0, 0);
        } else {
            marginLayoutParams.setMargins(0, 0, 0, UIUtils.dip2px(4.0f));
        }
        viewHolder.indicator.setLayoutParams(marginLayoutParams);
    }

    public static class ViewHolder extends BaseHolder {
        ItemIndicator1Binding binding;
        View indicator;

        public ViewHolder(View view) {
            super(view);
            ItemIndicator1Binding bind = ItemIndicator1Binding.bind(view);
            this.binding = bind;
            this.indicator = bind.indicator;
        }
    }

    public void notifyPosition(int i) {
        this.selectedPosition = i;
        notifyDataSetChanged();
    }
}
