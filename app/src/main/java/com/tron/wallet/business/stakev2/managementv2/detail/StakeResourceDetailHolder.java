package com.tron.wallet.business.stakev2.managementv2.detail;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.wallet.business.stakev2.managementv2.detail.StakeResourceDetailAdapter;
import com.tron.wallet.business.stakev2.managementv2.detail.bean.StakeResourceDetailBean;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.common.utils.AddressMapUtils;
import com.tron.wallet.common.utils.NumUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.databinding.ItemStakeResourceDetailBinding;
import com.tronlinkpro.wallet.R;
import java.math.BigDecimal;
import java.text.NumberFormat;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.lang3.time.DateUtils;
public class StakeResourceDetailHolder extends BaseViewHolder {
    private ItemStakeResourceDetailBinding binding;
    private NumberFormat numberFormat;
    TextView tvDeadLineTime;
    TextView tvReceiver;
    TextView tvReclaim;
    TextView tvResourceAmount;
    TextView tvResourceToTrx;

    public StakeResourceDetailHolder(View view) {
        super(view);
        mappingId(view);
        TouchDelegateUtils.expandViewTouchDelegate(this.tvReclaim, 10);
        NumberFormat numberFormat = NumberFormat.getInstance();
        this.numberFormat = numberFormat;
        numberFormat.setMaximumFractionDigits(6);
    }

    public void mappingId(View view) {
        ItemStakeResourceDetailBinding bind = ItemStakeResourceDetailBinding.bind(view);
        this.binding = bind;
        this.tvDeadLineTime = bind.tvDeadlineTime;
        this.tvReclaim = this.binding.tvResourceReclaim;
        this.tvResourceAmount = this.binding.tvResourceAmount;
        this.tvResourceToTrx = this.binding.tvResourceToTrx;
        this.tvReceiver = this.binding.tvReceiveAddress;
    }

    public void bindExpireTimePayload(StakeResourceDetailBean stakeResourceDetailBean) {
        this.tvDeadLineTime.setText(parseExpireTime(this.itemView.getContext(), stakeResourceDetailBean.getExpireTime()));
    }

    public void onBind(Context context, final StakeResourceDetailBean stakeResourceDetailBean, final StakeResourceDetailAdapter.ItemReClaimClickListener itemReClaimClickListener, int i, boolean z) {
        if (AddressMapUtils.getInstance().isContainsAddress(stakeResourceDetailBean.getReceiverAddress())) {
            this.tvReceiver.setText(getDisPlayName(AddressMapUtils.getInstance().getNameAddress(stakeResourceDetailBean.getReceiverAddress())));
        } else {
            this.tvReceiver.setText(stakeResourceDetailBean.getReceiverAddress());
        }
        if (!StringTronUtil.isEmpty(stakeResourceDetailBean.getResourceBalance())) {
            if (z) {
                TextView textView = this.tvResourceAmount;
                textView.setText(HelpFormatter.DEFAULT_OPT_PREFIX + NumUtils.numFormatToK(Long.parseLong(stakeResourceDetailBean.getResourceBalance())));
                this.tvResourceAmount.setTextColor(context.getResources().getColor(R.color.red_EC));
            } else {
                TextView textView2 = this.tvResourceAmount;
                textView2.setText("" + NumUtils.numFormatToK(Long.parseLong(stakeResourceDetailBean.getResourceBalance())));
                this.tvResourceAmount.setTextColor(context.getResources().getColor(R.color.black_23));
            }
        }
        if (!StringTronUtil.isEmpty(stakeResourceDetailBean.getBalance())) {
            this.tvResourceToTrx.setText(String.format(context.getString(R.string.resource_to_trx), this.numberFormat.format(new BigDecimal(stakeResourceDetailBean.getBalance()).divide(new BigDecimal(1000000.0d)).doubleValue())));
        }
        if (System.currentTimeMillis() > stakeResourceDetailBean.getExpireTime()) {
            this.tvDeadLineTime.setVisibility(View.GONE);
            this.tvReclaim.setVisibility(View.VISIBLE);
            this.tvReclaim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    StakeResourceDetailAdapter.ItemReClaimClickListener itemReClaimClickListener2 = itemReClaimClickListener;
                    if (itemReClaimClickListener2 != null) {
                        itemReClaimClickListener2.onReClaimClick(stakeResourceDetailBean);
                    }
                }
            });
            return;
        }
        this.tvDeadLineTime.setVisibility(View.VISIBLE);
        this.tvReclaim.setVisibility(View.GONE);
        this.tvDeadLineTime.setText(parseExpireTime(context, stakeResourceDetailBean.getExpireTime()));
    }

    private String parseExpireTime(Context context, long j) {
        long currentTimeMillis = j - System.currentTimeMillis();
        if (currentTimeMillis >= DateUtils.MILLIS_PER_DAY) {
            return context.getString(R.string.unstake_count_down_day, Integer.valueOf((int) Math.ceil(currentTimeMillis / 8.64E7d)));
        }
        if (currentTimeMillis > DateUtils.MILLIS_PER_HOUR) {
            return context.getString(R.string.unstake_count_down_hour, Integer.valueOf((int) Math.ceil(currentTimeMillis / 3600000.0d)));
        }
        double d = currentTimeMillis;
        return d >= 60000.0d ? context.getString(R.string.unstake_count_down_min, Integer.valueOf((int) Math.ceil(d / 60000.0d))) : context.getString(R.string.unstake_count_down_2);
    }

    private String getDisPlayName(NameAddressExtraBean nameAddressExtraBean) {
        if (nameAddressExtraBean != null) {
            if (nameAddressExtraBean.getName().length() > 7) {
                if (nameAddressExtraBean.getName().length() > 16) {
                    return StringTronUtil.indentAddress(nameAddressExtraBean.getName().toString(), 6) + " (" + StringTronUtil.indentAddress(nameAddressExtraBean.getAddress().toString(), 6) + ")";
                }
                return nameAddressExtraBean.getName().toString() + " (" + StringTronUtil.indentAddress(nameAddressExtraBean.getAddress().toString(), 6) + ")";
            }
            return nameAddressExtraBean.getName().toString() + " (" + StringTronUtil.indentAddress(nameAddressExtraBean.getAddress().toString(), 6) + ")";
        }
        return "";
    }
}
