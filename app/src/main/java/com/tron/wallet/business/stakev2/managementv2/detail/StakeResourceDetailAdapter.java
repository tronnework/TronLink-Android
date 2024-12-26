package com.tron.wallet.business.stakev2.managementv2.detail;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.stakev2.managementv2.detail.bean.StakeResourceDetailBean;
import com.tron.wallet.common.components.CustomLoadMoreView;
import com.tron.wallet.common.utils.NumUtils;
import com.tronlinkpro.wallet.R;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.List;
public class StakeResourceDetailAdapter extends BaseQuickAdapter<StakeResourceDetailBean, BaseViewHolder> {
    private CountDownTimer countDownTimer;
    private boolean isLocked;
    private boolean isPop;
    private ItemReClaimClickListener itemReClaimClickListener;
    NumberFormat numberFormat;
    private int radio;
    TextView tvTotalAmount;
    TextView tvTotalTitle;

    public interface ItemReClaimClickListener {
        void onReClaimClick(StakeResourceDetailBean stakeResourceDetailBean);
    }

    public void setItemReClaimClickListener(ItemReClaimClickListener itemReClaimClickListener) {
        this.itemReClaimClickListener = itemReClaimClickListener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List list) {
        onBindViewHolder((BaseViewHolder) viewHolder, i, (List<Object>) list);
    }

    public StakeResourceDetailAdapter(Context context, boolean z, boolean z2) {
        super((int) R.layout.item_stake_resource_detail);
        this.radio = 1;
        setLoadMoreView(new CustomLoadMoreView());
        this.numberFormat = NumberFormat.getInstance();
        View inflate = LayoutInflater.from(context).inflate(R.layout.header_stake_resource_detail, (ViewGroup) null);
        this.tvTotalTitle = (TextView) inflate.findViewById(R.id.tv_total_title);
        this.tvTotalAmount = (TextView) inflate.findViewById(R.id.tv_total_amount);
        addHeaderView(inflate);
        this.isLocked = z;
        this.isPop = z2;
        if (z) {
            this.tvTotalTitle.setText(R.string.resource_total_locked);
        } else {
            this.tvTotalTitle.setText(R.string.resource_total_available);
        }
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder, StakeResourceDetailBean stakeResourceDetailBean) {
        if (baseViewHolder instanceof StakeResourceDetailHolder) {
            ((StakeResourceDetailHolder) baseViewHolder).onBind(this.mContext, stakeResourceDetailBean, this.itemReClaimClickListener, this.radio, this.isPop);
        }
    }

    @Override
    public BaseViewHolder onCreateDefViewHolder(ViewGroup viewGroup, int i) {
        return new StakeResourceDetailHolder(getItemView(this.mLayoutResId, viewGroup));
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
        LogUtils.w("StakeResourceDetailAdapter", "count down timer start");
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            LogUtils.w("StakeResourceDetailAdapter", "count down timer cancel");
        }
    }

    public void onBindViewHolder(BaseViewHolder baseViewHolder, int i, List<Object> list) {
        if (list.isEmpty()) {
            super.onBindViewHolder((StakeResourceDetailAdapter) baseViewHolder, i);
            return;
        }
        StakeResourceDetailBean item = getItem(i);
        if (item != null && (list.get(0) instanceof Long) && (baseViewHolder instanceof StakeResourceDetailHolder)) {
            ((StakeResourceDetailHolder) baseViewHolder).bindExpireTimePayload(item);
        }
    }

    public void updateTimeCounter() {
        RecyclerView recyclerView = getRecyclerView();
        if (recyclerView != null && recyclerView.getScrollState() == 0) {
            notifyItemRangeChanged(0, getData().size(), 0L);
        }
    }

    public void addDataWithHeader(String str, List<StakeResourceDetailBean> list) {
        this.tvTotalAmount.setText(NumUtils.numFormatToK(Long.parseLong(str)));
        addData((Collection) list);
    }
}
