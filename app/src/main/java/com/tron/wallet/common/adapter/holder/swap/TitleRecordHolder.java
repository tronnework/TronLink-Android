package com.tron.wallet.common.adapter.holder.swap;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.tron.wallet.business.tabswap.record.SwapRecordActivity;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tronlinkpro.wallet.R;
public class TitleRecordHolder extends BaseSwapAdapterHolder {
    TextView tvSwapMore;

    public TitleRecordHolder(final Context context, int i) {
        super(context, i);
        TextView textView = (TextView) this.itemView.findViewById(R.id.swap_more);
        this.tvSwapMore = textView;
        textView.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                SwapRecordActivity.start(context);
            }
        });
    }

    @Override
    public void onBind(boolean z) {
        if (z) {
            this.tvSwapMore.setVisibility(View.VISIBLE);
        } else {
            this.tvSwapMore.setVisibility(View.GONE);
        }
    }
}
