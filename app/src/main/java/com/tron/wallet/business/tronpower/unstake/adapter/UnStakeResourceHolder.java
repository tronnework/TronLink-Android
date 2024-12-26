package com.tron.wallet.business.tronpower.unstake.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.util.Consumer;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.tronpower.unstake.adapter.UnStakeResourceBean;
import com.tron.wallet.common.utils.DateUtils;
import com.tron.wallet.common.utils.PopupWindowUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tronlinkpro.wallet.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
public class UnStakeResourceHolder extends BaseViewHolder {
    private final SimpleDateFormat dateFormat;
    View groupDivider;
    ImageView ivIcon;
    ImageView ivIconDivider;
    ImageView ivSelect;
    TextView tvAddress;
    TextView tvBalance;
    TextView tvExpireTime;
    TextView tvGroupDivider;
    TextView tvName;
    TextView tvTitle;

    private int[] parseState(int i) {
        return new int[]{(-65536) & i, i & 65535};
    }

    public UnStakeResourceHolder(View view) {
        super(view);
        mappingId(view);
        this.dateFormat = new SimpleDateFormat(DateUtils.DATE_FORMAT_ZH, Locale.getDefault());
        TouchDelegateUtils.expandViewTouchDelegate(this.ivSelect, 20);
    }

    public void onBind(UnStakeResourceBean unStakeResourceBean, int i, UnStakeResourceAdapter unStakeResourceAdapter, Consumer<UnStakeResourceBean> consumer) {
        setGroupDivider(unStakeResourceBean, i);
        boolean equals = unStakeResourceBean.getType().equals(UnStakeResourceBean.Type.ENERGY);
        this.ivIcon.setImageResource(equals ? R.mipmap.icon_stake_energy : R.mipmap.icon_unstake_bandwidth);
        this.tvTitle.setText(equals ? R.string.energy : R.string.bandwidth);
        this.tvBalance.setText(String.format("%s TRX", StringTronUtil.formatNumber6Truncate(Long.valueOf(unStakeResourceBean.trxCount))));
        if (unStakeResourceBean.getGroup().equals(UnStakeResourceBean.Group.SELF) || TextUtils.isEmpty(unStakeResourceBean.getAddress())) {
            this.tvName.setVisibility(View.GONE);
            this.tvAddress.setVisibility(View.GONE);
        } else {
            this.tvAddress.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(unStakeResourceBean.getName())) {
                this.tvName.setVisibility(View.VISIBLE);
                this.tvName.setText(unStakeResourceBean.getName());
                TextView textView = this.tvAddress;
                textView.setText("(" + unStakeResourceBean.getAddress() + ")");
            } else {
                this.tvAddress.setText(unStakeResourceBean.getAddress());
                this.tvName.setVisibility(View.GONE);
            }
        }
        int state = unStakeResourceBean.getState();
        setImageWithState(state, this.ivSelect);
        setTextWithState(state, unStakeResourceBean.getExpiredTime());
        this.ivSelect.setOnClickListener(getIconClickListener(unStakeResourceAdapter));
        this.itemView.setOnClickListener(getItemViewClickListener(unStakeResourceAdapter, consumer));
    }

    private View.OnClickListener getIconClickListener(final UnStakeResourceAdapter unStakeResourceAdapter) {
        return new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$getIconClickListener$0(unStakeResourceAdapter, view);
            }
        };
    }

    public void lambda$getIconClickListener$0(UnStakeResourceAdapter unStakeResourceAdapter, View view) {
        UnStakeResourceBean clickedItem = getClickedItem(unStakeResourceAdapter);
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

    private UnStakeResourceBean getClickedItem(UnStakeResourceAdapter unStakeResourceAdapter) {
        int absoluteAdapterPosition;
        if (unStakeResourceAdapter != null && (absoluteAdapterPosition = getAbsoluteAdapterPosition() - unStakeResourceAdapter.getHeaderLayoutCount()) >= 0 && absoluteAdapterPosition < unStakeResourceAdapter.getData().size()) {
            return unStakeResourceAdapter.getData().get(absoluteAdapterPosition);
        }
        return null;
    }

    private View.OnClickListener getItemViewClickListener(final UnStakeResourceAdapter unStakeResourceAdapter, final Consumer<UnStakeResourceBean> consumer) {
        return new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$getItemViewClickListener$1(unStakeResourceAdapter, consumer, view);
            }
        };
    }

    public void lambda$getItemViewClickListener$1(UnStakeResourceAdapter unStakeResourceAdapter, Consumer consumer, View view) {
        UnStakeResourceBean clickedItem = getClickedItem(unStakeResourceAdapter);
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

    private void setGroupDivider(UnStakeResourceBean unStakeResourceBean, int i) {
        if (getAbsoluteAdapterPosition() != 0 && getAbsoluteAdapterPosition() != i) {
            this.groupDivider.setVisibility(View.GONE);
            return;
        }
        this.groupDivider.setVisibility(View.VISIBLE);
        boolean equals = unStakeResourceBean.getGroup().equals(UnStakeResourceBean.Group.SELF);
        this.ivIconDivider.setImageResource(equals ? R.mipmap.icon_unstake_item_group_self : R.mipmap.icon_unstake_item_group_other);
        this.tvGroupDivider.setText(equals ? R.string.unstake_group_for_self : R.string.unstake_group_for_other);
    }

    private void onItemStateChanged(int i, UnStakeResourceBean unStakeResourceBean) {
        unStakeResourceBean.setState(i);
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

    public void bindExpireTimePayload(UnStakeResourceBean unStakeResourceBean) {
        if (unStakeResourceBean != null && parseState(unStakeResourceBean.state)[0] == 196608 && this.tvExpireTime.getVisibility() == 0) {
            long expiredTime = unStakeResourceBean.getExpiredTime();
            if (expiredTime - System.currentTimeMillis() <= 0) {
                unStakeResourceBean.setState((unStakeResourceBean.getState() & 65535) + 65536);
                setImageWithState(unStakeResourceBean.getState(), this.ivSelect);
                setTextWithState(unStakeResourceBean.getState(), expiredTime);
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

    private void mappingId(View view) {
        this.ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
        this.tvTitle = (TextView) view.findViewById(R.id.tv_title);
        this.tvBalance = (TextView) view.findViewById(R.id.tv_balance);
        this.tvAddress = (TextView) view.findViewById(R.id.tv_address);
        this.ivSelect = (ImageView) view.findViewById(R.id.iv_select);
        this.groupDivider = view.findViewById(R.id.group_divider);
        this.ivIconDivider = (ImageView) view.findViewById(R.id.iv_icon_divider);
        this.tvGroupDivider = (TextView) view.findViewById(R.id.tv_group_divider);
        this.tvExpireTime = (TextView) view.findViewById(R.id.tv_expire_time);
        this.tvName = (TextView) view.findViewById(R.id.tv_Name);
    }
}
