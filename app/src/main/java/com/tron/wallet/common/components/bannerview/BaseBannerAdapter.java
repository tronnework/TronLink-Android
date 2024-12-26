package com.tron.wallet.common.components.bannerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.wallet.common.components.bannerview.BannerViewPager;
import com.tron.wallet.common.components.bannerview.BaseViewHolder;
import com.tron.wallet.common.components.bannerview.utils.BannerUtils;
import java.util.ArrayList;
import java.util.List;
public abstract class BaseBannerAdapter<T, VH extends BaseViewHolder<T>> extends RecyclerView.Adapter<VH> {
    static final int MAX_VALUE = 500;
    private boolean isCanLoop;
    protected List<T> mList = new ArrayList();
    private BannerViewPager.OnPageClickListener mPageClickListener;

    public abstract VH createViewHolder(View view, int i);

    public List<T> getData() {
        return this.mList;
    }

    public abstract int getLayoutId(int i);

    protected int getViewType(int i) {
        return 0;
    }

    protected abstract void onBind(VH vh, T t, int i, int i2);

    public void setCanLoop(boolean z) {
        this.isCanLoop = z;
    }

    public void setPageClickListener(BannerViewPager.OnPageClickListener onPageClickListener) {
        this.mPageClickListener = onPageClickListener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        onBindViewHolder((BaseBannerAdapter<T, VH>) ((BaseViewHolder) viewHolder), i);
    }

    @Override
    public final VH onCreateViewHolder(ViewGroup viewGroup, int i) {
        return createViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(getLayoutId(i), viewGroup, false), i);
    }

    public final void onBindViewHolder(VH vh, final int i) {
        int realPosition = BannerUtils.getRealPosition(this.isCanLoop, i, this.mList.size());
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPageClickListener != null) {
                    mPageClickListener.onPageClick(BannerUtils.getRealPosition(isCanLoop, i, mList.size()));
                }
            }
        });
        onBind(vh, this.mList.get(realPosition), realPosition, this.mList.size());
    }

    @Override
    public final int getItemViewType(int i) {
        return getViewType(BannerUtils.getRealPosition(this.isCanLoop, i, this.mList.size()));
    }

    @Override
    public final int getItemCount() {
        if (!this.isCanLoop || this.mList.size() <= 1) {
            return this.mList.size();
        }
        return 500;
    }

    public void setData(List<T> list) {
        if (list != null) {
            this.mList.clear();
            this.mList.addAll(list);
        }
    }

    public int getListSize() {
        return this.mList.size();
    }
}
