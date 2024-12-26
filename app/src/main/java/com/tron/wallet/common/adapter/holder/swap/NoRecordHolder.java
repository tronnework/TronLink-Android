package com.tron.wallet.common.adapter.holder.swap;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.tronlinkpro.wallet.R;
public class NoRecordHolder extends BaseSwapAdapterHolder {
    View noNetContainer;
    TextView tvNoData;

    public NoRecordHolder(Context context, int i) {
        super(context, i);
        this.noNetContainer = this.itemView.findViewById(R.id.tv_no_net);
        this.tvNoData = (TextView) this.itemView.findViewById(R.id.tv_no_data);
    }

    public NoRecordHolder(View view) {
        super(view);
    }

    @Override
    public void onBind() {
        super.onBind();
        TextView textView = this.tvNoData;
        if (textView != null) {
            textView.setVisibility(View.VISIBLE);
        }
    }
}
