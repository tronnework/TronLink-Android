package com.tron.wallet.business.stakev2.stake.pop.stakev1;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.util.Consumer;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.stakev2.stake.pop.stakev1.StakeDetailResourceAdapter;
import com.tron.wallet.business.stakev2.stake.pop.stakev1.StakeDetailResourceBean;
import com.tron.wallet.common.utils.DateUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.PopupWindowUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.ItemStakeDetailResourceHolderBinding;
import com.tronlinkpro.wallet.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
public class StakeDetailResourceHolder extends BaseViewHolder {
    ItemStakeDetailResourceHolderBinding binding;
    private final SimpleDateFormat dateFormat;
    View groupDivider;
    ImageView ivIcon;
    ImageView ivIconDivider;
    TextView tvAddress;
    TextView tvBalance;
    TextView tvExpireTime;
    TextView tvGroupDivider;
    TextView tvName;
    TextView tvTitle;
    TextView tvUnfreeze;

    private int[] parseState(int i) {
        return new int[]{(-65536) & i, i & 65535};
    }

    public StakeDetailResourceHolder(View view) {
        super(view);
        mappingId(view);
        this.dateFormat = new SimpleDateFormat(DateUtils.DATE_FORMAT_ZH, Locale.getDefault());
    }

    public void mappingId(View view) {
        ItemStakeDetailResourceHolderBinding bind = ItemStakeDetailResourceHolderBinding.bind(view);
        this.binding = bind;
        this.ivIcon = bind.ivIcon;
        this.tvTitle = this.binding.tvTitle;
        this.tvBalance = this.binding.tvBalance;
        this.tvAddress = this.binding.tvAddress;
        this.groupDivider = this.binding.groupDivider.rlGroupRoot;
        this.ivIconDivider = this.binding.groupDivider.ivIconDivider;
        this.tvGroupDivider = this.binding.groupDivider.tvGroupDivider;
        this.tvExpireTime = this.binding.tvExpireTime;
        this.tvName = this.binding.tvName;
        this.tvUnfreeze = this.binding.tvUnfreeze;
    }

    public void onBind(final StakeDetailResourceBean stakeDetailResourceBean, int i, StakeDetailResourceAdapter stakeDetailResourceAdapter, final StakeDetailResourceAdapter.OnUnFreezeBtnClickListener onUnFreezeBtnClickListener) {
        setGroupDivider(stakeDetailResourceBean, i);
        boolean equals = stakeDetailResourceBean.getType().equals(StakeDetailResourceBean.Type.ENERGY);
        this.ivIcon.setImageResource(equals ? R.mipmap.icon_stake_energy : R.mipmap.icon_unstake_bandwidth);
        this.tvTitle.setText(equals ? R.string.energy : R.string.bandwidth);
        this.tvBalance.setText(String.format("%s TRX", StringTronUtil.formatNumber6Truncate(Long.valueOf(stakeDetailResourceBean.trxCount))));
        if (stakeDetailResourceBean.getGroup().equals(StakeDetailResourceBean.Group.SELF) || TextUtils.isEmpty(stakeDetailResourceBean.getAddress())) {
            this.tvName.setVisibility(View.GONE);
            this.tvAddress.setVisibility(View.GONE);
        } else {
            this.tvAddress.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(stakeDetailResourceBean.getName())) {
                this.tvName.setVisibility(View.VISIBLE);
                this.tvName.setText(stakeDetailResourceBean.getName());
                TextView textView = this.tvAddress;
                textView.setText("(" + stakeDetailResourceBean.getAddress() + ")");
            } else {
                this.tvAddress.setText(stakeDetailResourceBean.getAddress());
                this.tvName.setVisibility(View.GONE);
            }
        }
        setTextWithState(stakeDetailResourceBean.getState(), stakeDetailResourceBean.getExpiredTime());
        this.tvUnfreeze.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                onUnFreezeBtnClickListener.onUnfreezeClick(stakeDetailResourceBean);
            }
        });
    }

    private View.OnClickListener getIconClickListener(final StakeDetailResourceAdapter stakeDetailResourceAdapter) {
        return new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$getIconClickListener$0(stakeDetailResourceAdapter, view);
            }
        };
    }

    public void lambda$getIconClickListener$0(StakeDetailResourceAdapter stakeDetailResourceAdapter, View view) {
        StakeDetailResourceBean clickedItem = getClickedItem(stakeDetailResourceAdapter);
        if (clickedItem == null) {
            return;
        }
        int[] parseState = parseState(clickedItem.getState());
        int i = parseState[0];
        int i2 = parseState[1];
        if (i != 196608) {
            if (i == 131072 || i2 != 1) {
                this.itemView.callOnClick();
                return;
            }
            return;
        }
        try {
            PopupWindowUtil.showPopupText(view.getContext(), String.format(view.getContext().getString(R.string.unstake_expired_time), this.dateFormat.format(new Date(clickedItem.getExpiredTime()))), view, true);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    private StakeDetailResourceBean getClickedItem(StakeDetailResourceAdapter stakeDetailResourceAdapter) {
        int absoluteAdapterPosition;
        if (stakeDetailResourceAdapter != null && (absoluteAdapterPosition = getAbsoluteAdapterPosition() - stakeDetailResourceAdapter.getHeaderLayoutCount()) >= 0 && absoluteAdapterPosition < stakeDetailResourceAdapter.getData().size()) {
            return stakeDetailResourceAdapter.getData().get(absoluteAdapterPosition);
        }
        return null;
    }

    private View.OnClickListener getItemViewClickListener(final StakeDetailResourceAdapter stakeDetailResourceAdapter, final Consumer<StakeDetailResourceBean> consumer) {
        return new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$getItemViewClickListener$1(stakeDetailResourceAdapter, consumer, view);
            }
        };
    }

    public void lambda$getItemViewClickListener$1(StakeDetailResourceAdapter stakeDetailResourceAdapter, Consumer consumer, View view) {
        StakeDetailResourceBean clickedItem = getClickedItem(stakeDetailResourceAdapter);
        if (clickedItem == null) {
            return;
        }
        int[] parseState = parseState(clickedItem.getState());
        int i = parseState[0];
        int i2 = parseState[1];
        if (i == 196608) {
            return;
        }
        boolean z = i2 == 1;
        if (i == 131072) {
            onItemStateChanged(65536, clickedItem);
        } else if (i == 65536 && !z) {
            onItemStateChanged(131072, clickedItem);
        }
        if (consumer != null) {
            consumer.accept(clickedItem);
        }
    }

    private void setGroupDivider(StakeDetailResourceBean stakeDetailResourceBean, int i) {
        if (getAbsoluteAdapterPosition() != 0 && getAbsoluteAdapterPosition() != i) {
            this.groupDivider.setVisibility(View.GONE);
            return;
        }
        this.groupDivider.setVisibility(View.VISIBLE);
        boolean equals = stakeDetailResourceBean.getGroup().equals(StakeDetailResourceBean.Group.SELF);
        this.ivIconDivider.setImageResource(equals ? R.mipmap.icon_unstake_item_group_self : R.mipmap.icon_unstake_item_group_other);
        this.tvGroupDivider.setText(equals ? R.string.unstake_group_for_self : R.string.unstake_group_for_other);
    }

    private void onItemStateChanged(int i, StakeDetailResourceBean stakeDetailResourceBean) {
        stakeDetailResourceBean.setState(i);
        if (getBindingAdapter() != null) {
            getBindingAdapter().notifyItemChanged(getAbsoluteAdapterPosition());
        }
    }

    private void setImageWithState(int i, ImageView imageView) {
        int[] parseState = parseState(i);
        int i2 = parseState[0];
        int i3 = parseState[1];
        imageView.setVisibility(View.VISIBLE);
        this.tvExpireTime.setVisibility(View.GONE);
        if (i2 == 131072) {
            imageView.setImageResource(R.mipmap.ic_unstake_selected);
        } else if (i2 == 196608) {
            imageView.setVisibility(View.GONE);
            this.tvExpireTime.setVisibility(View.VISIBLE);
        } else if (i2 == 65536) {
            imageView.setImageResource(R.mipmap.ic_unstake_normal);
            if (i3 == 1) {
                imageView.setVisibility(View.GONE);
            }
        }
    }

    private void setTextWithState(int i, long j) {
        int[] parseState = parseState(i);
        int i2 = parseState[0];
        boolean z = parseState[1] == 1;
        boolean z2 = i2 == 196608;
        Context context = this.itemView.getContext();
        if (z2 || (z && i2 != 131072)) {
            int color = context.getResources().getColor(R.color.gray_9B);
            this.tvTitle.setTextColor(color);
            this.tvBalance.setTextColor(color);
            this.tvAddress.setTextColor(color);
        } else {
            this.tvTitle.setTextColor(context.getResources().getColor(R.color.black_23));
            this.tvBalance.setTextColor(context.getResources().getColor(R.color.black_6d));
            this.tvAddress.setTextColor(context.getResources().getColor(R.color.gray_9B));
        }
        if (z2) {
            this.tvExpireTime.setText(parseExpireTime(this.itemView.getContext(), j));
        }
    }

    public void bindExpireTimePayload(StakeDetailResourceBean stakeDetailResourceBean) {
        if (stakeDetailResourceBean != null && parseState(stakeDetailResourceBean.state)[0] == 196608 && this.tvExpireTime.getVisibility() == 0) {
            long expiredTime = stakeDetailResourceBean.getExpiredTime();
            if (expiredTime - System.currentTimeMillis() <= 0) {
                stakeDetailResourceBean.setState((stakeDetailResourceBean.getState() & 65535) + 65536);
                setTextWithState(stakeDetailResourceBean.getState(), expiredTime);
                return;
            }
            String parseExpireTime = parseExpireTime(this.itemView.getContext(), expiredTime);
            this.tvExpireTime.setText(parseExpireTime);
            LogUtils.w("UnStakeAdapter", "bindExpireTimePayload " + parseExpireTime);
        }
    }

    private String parseExpireTime(Context context, long j) {
        long currentTimeMillis = j - System.currentTimeMillis();
        long j2 = 3600000;
        if (currentTimeMillis >= j2) {
            return context.getString(R.string.unstake_count_down_0, Integer.valueOf((int) (currentTimeMillis / j2)), Integer.valueOf((int) ((currentTimeMillis % j2) / 60000)));
        }
        long j3 = 60000;
        return currentTimeMillis >= j3 ? context.getString(R.string.unstake_count_down_1, Integer.valueOf((int) (currentTimeMillis / j3))) : context.getString(R.string.unstake_count_down_2);
    }
}
