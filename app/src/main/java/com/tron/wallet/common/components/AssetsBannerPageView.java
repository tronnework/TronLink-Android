package com.tron.wallet.common.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager2.widget.ViewPager2;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tencent.bugly.BuglyStrategy;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.UIUtils;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.List;
public class AssetsBannerPageView extends FrameLayout {
    private static final int ITEM_PER_PAGE = 4;
    private BannerViewAdapter adapter;
    private boolean initialized;
    private ViewGroup llIndicator;

    public boolean isInitialized() {
        return this.initialized;
    }

    public void setInitialized(boolean z) {
        this.initialized = z;
    }

    public AssetsBannerPageView(Context context) {
        this(context, null);
    }

    public AssetsBannerPageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AssetsBannerPageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.initialized = false;
        initView(context);
    }

    public void initBannerData(List<BannerViewInnerItem> list) {
        int i;
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = list.size() % 4;
        int i2 = 0;
        if (size > 0) {
            for (int i3 = 0; i3 < 4 - size; i3++) {
                list.add(new BannerViewInnerItem());
            }
        }
        ArrayList arrayList = new ArrayList();
        while (i2 < list.size() / 4) {
            ArrayList arrayList2 = new ArrayList();
            int i4 = i2 * 4;
            while (true) {
                i = i2 + 1;
                if (i4 < i * 4) {
                    arrayList2.add(list.get(i4));
                    i4++;
                }
            }
            arrayList.add(new BannerViewItem(arrayList2));
            i2 = i;
        }
        BannerViewAdapter bannerViewAdapter = this.adapter;
        if (bannerViewAdapter != null) {
            bannerViewAdapter.setNewData(arrayList);
        }
        initIndicators(arrayList.size());
    }

    private void initIndicators(int i) {
        ViewGroup viewGroup;
        if (i > 0 && (viewGroup = this.llIndicator) != null) {
            viewGroup.removeAllViews();
            for (int i2 = 0; i2 < i; i2++) {
                this.llIndicator.addView(new ImageView(getContext()), new LinearLayout.LayoutParams(UIUtils.dip2px(10.0f), UIUtils.dip2px(2.0f)));
            }
            updateIndicatorsColor(0);
        }
    }

    public void updateIndicatorsColor(int i) {
        int childCount;
        ViewGroup viewGroup = this.llIndicator;
        if (viewGroup == null || (childCount = viewGroup.getChildCount()) == 0) {
            return;
        }
        int i2 = 0;
        while (i2 < childCount) {
            this.llIndicator.getChildAt(i2).setBackgroundColor(getContext().getResources().getColor(i2 == i ? R.color.black_23 : R.color.gray_eb));
            i2++;
        }
    }

    private void initView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_assets_banner, (ViewGroup) this, false);
        addView(inflate, new FrameLayout.LayoutParams(-1, -1));
        ViewPager2 viewPager2 = (ViewPager2) inflate.findViewById(R.id.banner_view_pager);
        this.llIndicator = (ViewGroup) inflate.findViewById(R.id.ll_indicator);
        BannerViewAdapter bannerViewAdapter = new BannerViewAdapter();
        this.adapter = bannerViewAdapter;
        viewPager2.setAdapter(bannerViewAdapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int i) {
                updateIndicatorsColor(i);
            }
        });
    }

    public static class BannerViewInnerItem {
        private int icon;
        private boolean isNew;
        private View.OnClickListener onClickListener;
        private int title;

        public boolean isNew() {
            return this.isNew;
        }

        public void setNew(boolean z) {
            this.isNew = z;
        }

        public BannerViewInnerItem() {
        }

        public BannerViewInnerItem(int i, int i2, View.OnClickListener onClickListener) {
            this(i, i2, false, onClickListener);
        }

        public BannerViewInnerItem(int i, int i2, boolean z, View.OnClickListener onClickListener) {
            this.title = i;
            this.icon = i2;
            this.isNew = z;
            this.onClickListener = onClickListener;
        }
    }

    public static class BannerViewItem {
        private List<BannerViewInnerItem> items;

        public BannerViewItem(List<BannerViewInnerItem> list) {
            new ArrayList(4);
            this.items = list;
        }
    }

    public static class BannerViewHolder extends BaseViewHolder {
        public BannerViewHolder(View view) {
            super(view);
        }

        void onBind(BannerViewItem bannerViewItem) {
            int childCount;
            if ((this.itemView instanceof ViewGroup) && (childCount = ((ViewGroup) this.itemView).getChildCount()) == 4) {
                for (int i = 0; i < childCount; i++) {
                    bindSingleItem(bannerViewItem, i);
                }
            }
        }

        private void bindSingleItem(BannerViewItem bannerViewItem, int i) {
            View childAt = ((ViewGroup) this.itemView).getChildAt(i);
            final BannerViewInnerItem bannerViewInnerItem = (BannerViewInnerItem) bannerViewItem.items.get(i);
            int i2 = bannerViewInnerItem.title;
            if (i2 <= 0) {
                childAt.setVisibility(View.INVISIBLE);
                childAt.setEnabled(false);
                return;
            }
            childAt.setVisibility(View.VISIBLE);
            childAt.setEnabled(true);
            final View findViewById = childAt.findViewById(R.id.iv_new_added);
            ((TextView) childAt.findViewById(R.id.tv_title)).setText(i2);
            ((ImageView) childAt.findViewById(R.id.iv_icon)).setImageResource(bannerViewInnerItem.icon);
            findViewById.setVisibility(bannerViewInnerItem.isNew ? 0 : 4);
            if (bannerViewInnerItem.onClickListener != null) {
                childAt.setOnClickListener(new NoDoubleClickListener2() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        if (bannerViewInnerItem.isNew) {
                            bannerViewInnerItem.setNew(false);
                            findViewById.setVisibility(View.INVISIBLE);
                        }
                        bannerViewInnerItem.onClickListener.onClick(view);
                    }
                });
            }
        }
    }

    public static class BannerViewAdapter extends BaseQuickAdapter<BannerViewItem, BannerViewHolder> {
        public BannerViewAdapter() {
            super((int) R.layout.item_view_assets_banner);
        }

        @Override
        public void convert(BannerViewHolder bannerViewHolder, BannerViewItem bannerViewItem) {
            bannerViewHolder.onBind(bannerViewItem);
        }

        @Override
        public BannerViewHolder onCreateDefViewHolder(ViewGroup viewGroup, int i) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) super.onCreateDefViewHolder(viewGroup, i);
            if (bannerViewHolder.itemView instanceof ConstraintLayout) {
                for (int i2 = 0; i2 < 4; i2++) {
                    View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_include_assets_banner_item, (ViewGroup) bannerViewHolder.itemView, false);
                    inflate.setId(BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH + i2);
                    ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(-2, -2);
                    layoutParams.width = -2;
                    layoutParams.height = -2;
                    layoutParams.bottomToBottom = 0;
                    if (i2 == 0) {
                        layoutParams.horizontalChainStyle = 1;
                        layoutParams.leftToLeft = 0;
                        layoutParams.rightToLeft = 100001 + i2;
                    } else if (i2 == 3) {
                        layoutParams.rightToRight = 0;
                        layoutParams.leftToRight = 99999 + i2;
                    } else {
                        layoutParams.leftToRight = 99999 + i2;
                        layoutParams.rightToLeft = 100001 + i2;
                    }
                    ((ViewGroup) bannerViewHolder.itemView).addView(inflate, layoutParams);
                }
                return bannerViewHolder;
            }
            return bannerViewHolder;
        }
    }
}
