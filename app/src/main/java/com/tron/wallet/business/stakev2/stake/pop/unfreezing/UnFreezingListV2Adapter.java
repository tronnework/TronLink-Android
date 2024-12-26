package com.tron.wallet.business.stakev2.stake.pop.unfreezing;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.wallet.business.stakev2.stake.pop.unfreezing.UnFreezingResourceBean;
import com.tron.wallet.common.utils.DateUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.ItemUnfreezingResourceHolderBinding;
import com.tronlinkpro.wallet.R;
public class UnFreezingListV2Adapter extends BaseQuickAdapter<UnFreezingResourceBean, SearchHolder> {
    private CountDownTimer countDownTimer;

    public UnFreezingListV2Adapter() {
        super((int) R.layout.item_unfreezing_resource_holder);
    }

    @Override
    public void convert(SearchHolder searchHolder, UnFreezingResourceBean unFreezingResourceBean) {
        searchHolder.onBind(unFreezingResourceBean);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        final boolean[] zArr = {true};
        CountDownTimer countDownTimer = new CountDownTimer(2147483647L, 60000) {
            @Override
            public void onFinish() {
            }

            @Override
            public void onTick(long j) {
                boolean[] zArr2 = zArr;
                if (zArr2[0]) {
                    zArr2[0] = false;
                } else {
                    updateTimeCounter();
                }
            }
        };
        this.countDownTimer = countDownTimer;
        countDownTimer.start();
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    public static class SearchHolder extends BaseViewHolder {
        private ItemUnfreezingResourceHolderBinding binding;
        ImageView ivIcon;
        TextView tvAddress;
        TextView tvBalance;
        TextView tvDeadlineTime;
        TextView tvExpireTime;
        TextView tvName;
        TextView tvTitle;

        public SearchHolder(View view) {
            super(view);
            mappingId(view);
        }

        public void mappingId(View view) {
            ItemUnfreezingResourceHolderBinding bind = ItemUnfreezingResourceHolderBinding.bind(view);
            this.ivIcon = bind.ivIcon;
            this.tvTitle = bind.tvTitle;
            this.tvBalance = bind.tvBalance;
            this.tvName = bind.tvName;
            this.tvAddress = bind.tvAddress;
            this.tvExpireTime = bind.tvExpireTime;
            this.tvDeadlineTime = bind.tvExpireDeadline;
        }

        public void onBind(UnFreezingResourceBean unFreezingResourceBean) {
            boolean equals = unFreezingResourceBean.getType().equals(UnFreezingResourceBean.Type.ENERGY);
            this.ivIcon.setImageResource(equals ? R.mipmap.icon_stake_energy : R.mipmap.icon_unstake_bandwidth);
            this.tvTitle.setText(equals ? R.string.energy : R.string.bandwidth);
            this.tvBalance.setText(String.format("%s TRX", StringTronUtil.formatNumber6TruncatePlainScientific(unFreezingResourceBean.trxCount)));
            long expiredTime = unFreezingResourceBean.getExpiredTime();
            if (expiredTime > System.currentTimeMillis()) {
                this.tvExpireTime.setText(parseExpireTime(this.itemView.getContext(), expiredTime));
                this.tvExpireTime.setVisibility(View.VISIBLE);
                this.tvDeadlineTime.setText(DateUtils.diffLanguageDateHHMM(expiredTime));
                this.tvDeadlineTime.setVisibility(View.VISIBLE);
            } else {
                this.tvExpireTime.setVisibility(View.VISIBLE);
                this.tvExpireTime.setText(this.itemView.getContext().getString(R.string.available_for_withdraw));
                this.tvDeadlineTime.setVisibility(View.GONE);
            }
            this.tvName.setVisibility(View.GONE);
            this.tvAddress.setVisibility(View.GONE);
        }

        private String parseExpireTime(Context context, long j) {
            double currentTimeMillis = j - System.currentTimeMillis();
            return currentTimeMillis >= 8.64E7d ? context.getString(R.string.after_can_withdraw_days, Integer.valueOf((int) Math.ceil(currentTimeMillis / 8.64E7d))) : currentTimeMillis >= 3600000.0d ? context.getString(R.string.after_can_withdraw_hours, Integer.valueOf((int) Math.ceil(currentTimeMillis / 3600000.0d))) : currentTimeMillis <= 60000.0d ? context.getString(R.string.withdraw_within_one_minute) : context.getString(R.string.after_can_withdraw_minutes, Integer.valueOf((int) Math.ceil(currentTimeMillis / 60000.0d)));
        }
    }

    public void updateTimeCounter() {
        RecyclerView recyclerView = getRecyclerView();
        if (recyclerView != null) {
            if (recyclerView.getScrollState() != 0) {
                return;
            }
            notifyDataSetChanged();
            return;
        }
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
