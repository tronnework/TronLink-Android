package com.tron.wallet.business.tronpower.unstake.adapter;

import android.os.CountDownTimer;
import android.view.ViewGroup;
import androidx.core.util.Consumer;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.components.CustomLoadMoreView;
import com.tronlinkpro.wallet.R;
import java.util.Collection;
import java.util.List;
public class UnStakeResourceAdapter extends BaseQuickAdapter<UnStakeResourceBean, BaseViewHolder> {
    private CountDownTimer countDownTimer;
    private int groupIndex;
    private Consumer<UnStakeResourceBean> itemClickCallback;

    public void setItemClickCallback(Consumer<UnStakeResourceBean> consumer) {
        this.itemClickCallback = consumer;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List list) {
        onBindViewHolder((BaseViewHolder) viewHolder, i, (List<Object>) list);
    }

    public UnStakeResourceAdapter() {
        super((int) R.layout.item_unstake_resource_holder);
        setLoadMoreView(new CustomLoadMoreView());
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder, UnStakeResourceBean unStakeResourceBean) {
        if (baseViewHolder instanceof UnStakeResourceHolder) {
            ((UnStakeResourceHolder) baseViewHolder).onBind(unStakeResourceBean, this.groupIndex, this, this.itemClickCallback);
        }
    }

    @Override
    public BaseViewHolder onCreateDefViewHolder(ViewGroup viewGroup, int i) {
        return new UnStakeResourceHolder(getItemView(this.mLayoutResId, viewGroup));
    }

    public void addData(int i, List<UnStakeResourceBean> list, int i2) {
        addData(i, (Collection) list);
        this.groupIndex = i2;
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
        LogUtils.w("UnStakeAdapter", "count down timer start");
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            LogUtils.w("UnStakeAdapter", "count down timer cancel");
        }
    }

    public void onBindViewHolder(BaseViewHolder baseViewHolder, int i, List<Object> list) {
        if (list.isEmpty()) {
            super.onBindViewHolder((UnStakeResourceAdapter) baseViewHolder, i);
            return;
        }
        UnStakeResourceBean item = getItem(i);
        if (item != null && (list.get(0) instanceof Long) && (baseViewHolder instanceof UnStakeResourceHolder)) {
            ((UnStakeResourceHolder) baseViewHolder).bindExpireTimePayload(item);
        }
    }

    public void updateTimeCounter() {
        RecyclerView recyclerView = getRecyclerView();
        if (recyclerView != null && recyclerView.getScrollState() == 0) {
            notifyItemRangeChanged(0, getData().size(), 0L);
        }
    }
}
