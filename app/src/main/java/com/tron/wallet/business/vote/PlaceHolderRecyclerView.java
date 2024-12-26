package com.tron.wallet.business.vote;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.R;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.common.utils.UIUtils;
public class PlaceHolderRecyclerView extends RecyclerView {
    private static final int DEFAULT_HOLDER_COUNT = 8;
    private static final int DEFAULT_HOLDER_LAYOUT = 2131558859;
    private boolean drawBackground;
    private int holderCount;
    private int itemViewLayoutId;

    public PlaceHolderRecyclerView(Context context) {
        this(context, null);
    }

    public PlaceHolderRecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PlaceHolderRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        getAttributes(context, attributeSet, i);
        setLayoutManager(new WrapContentLinearLayoutManager(getContext(), 1, false));
        setAdapter(new PHAdapter(this.holderCount, this.itemViewLayoutId, this.drawBackground));
    }

    private void getAttributes(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PlaceHolderRecyclerView);
        this.holderCount = obtainStyledAttributes.getInteger(1, 8);
        this.itemViewLayoutId = obtainStyledAttributes.getResourceId(2, com.tronlinkpro.wallet.R.layout.item_new_vote_content_placeholder);
        this.drawBackground = obtainStyledAttributes.getBoolean(0, true);
        if (this.itemViewLayoutId <= 0) {
            this.itemViewLayoutId = com.tronlinkpro.wallet.R.layout.item_new_vote_content_placeholder;
        }
        obtainStyledAttributes.recycle();
    }

    private static class PHAdapter extends RecyclerView.Adapter {
        private final boolean drawBg;
        private final int holderCount;
        private final int itemViewLayoutId;

        @Override
        public int getItemCount() {
            return this.holderCount;
        }

        public PHAdapter(int i, int i2, boolean z) {
            this.holderCount = i;
            this.itemViewLayoutId = i2;
            this.drawBg = z;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new PlaceHolderVH(View.inflate(viewGroup.getContext(), this.itemViewLayoutId, null), this.drawBg);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            ((PlaceHolderVH) viewHolder).onBind(getItemCount());
        }
    }

    public static class PlaceHolderVH extends BaseHolder {
        private final boolean drawBg;

        public PlaceHolderVH(View view, boolean z) {
            super(view);
            this.drawBg = z;
        }

        public void onBind(int i) {
            if (this.drawBg) {
                setBackground(i);
            }
            startAnimal();
        }

        private void startAnimal() {
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.5f);
            alphaAnimation.setDuration(1000L);
            alphaAnimation.setRepeatMode(2);
            alphaAnimation.setRepeatCount(Integer.MAX_VALUE);
            this.itemView.startAnimation(alphaAnimation);
        }

        public void setBackground(int i) {
            int dip2px;
            int dip2px2;
            int i2;
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) this.itemView.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new RecyclerView.LayoutParams(-1, -2);
            }
            if (getAdapterPosition() == 0) {
                this.itemView.setBackgroundResource(com.tronlinkpro.wallet.R.drawable.ripple_shadow1);
                dip2px2 = UIUtils.dip2px(40.0f);
                i2 = UIUtils.dip2px(15.0f);
                dip2px = UIUtils.dip2px(34.0f);
                layoutParams.topMargin = UIUtils.dip2px(10.0f) * (-1);
                layoutParams.bottomMargin = 0;
            } else if (getAdapterPosition() == i - 1) {
                this.itemView.setBackgroundResource(com.tronlinkpro.wallet.R.drawable.ripple_shadow3);
                dip2px2 = UIUtils.dip2px(20.0f);
                i2 = UIUtils.dip2px(40.0f);
                dip2px = UIUtils.dip2px(34.0f);
                layoutParams.bottomMargin = UIUtils.dip2px(20.0f) * (-1);
                layoutParams.topMargin = 0;
            } else {
                this.itemView.setBackgroundResource(com.tronlinkpro.wallet.R.drawable.ripple_shadow2);
                dip2px = UIUtils.dip2px(34.0f);
                dip2px2 = UIUtils.dip2px(20.0f);
                layoutParams.topMargin = 0;
                layoutParams.bottomMargin = 0;
                i2 = dip2px2;
            }
            this.itemView.setPadding(dip2px, dip2px2, dip2px, i2);
            this.itemView.setLayoutParams(layoutParams);
        }
    }
}
