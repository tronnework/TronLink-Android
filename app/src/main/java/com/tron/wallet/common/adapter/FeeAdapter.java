package com.tron.wallet.common.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.common.bean.FeeBean;
import com.tron.wallet.databinding.FeeItemBinding;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class FeeAdapter extends RecyclerView.Adapter<FeeHolder> {
    private List<FeeBean> feeBeanList;
    private Context mContext;

    public FeeAdapter(Context context, List<FeeBean> list) {
        this.mContext = context;
        this.feeBeanList = list;
    }

    @Override
    public FeeHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new FeeHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fee_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(FeeHolder feeHolder, int i) {
        FeeBean feeBean = this.feeBeanList.get(i);
        feeHolder.tvName.setText(feeBean.title);
        feeHolder.tvAmount.setText(feeBean.amount);
    }

    @Override
    public int getItemCount() {
        List<FeeBean> list = this.feeBeanList;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class FeeHolder extends BaseHolder {
        FeeItemBinding binding;
        TextView tvAmount;
        TextView tvName;

        public FeeHolder(View view) {
            super(view);
            FeeItemBinding bind = FeeItemBinding.bind(view);
            this.binding = bind;
            this.tvName = bind.tvName;
            this.tvAmount = this.binding.tvAmount;
        }
    }
}
