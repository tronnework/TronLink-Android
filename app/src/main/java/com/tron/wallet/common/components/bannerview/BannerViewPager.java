package com.tron.wallet.common.components.bannerview;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;
import com.tron.wallet.common.components.bannerview.BaseViewHolder;
import com.tron.wallet.common.components.bannerview.manager.BannerManager;
import com.tron.wallet.common.components.bannerview.manager.BannerOptions;
import com.tron.wallet.common.components.bannerview.provider.ScrollDurationManger;
import com.tron.wallet.common.components.bannerview.provider.ViewStyleSetter;
import com.tron.wallet.common.components.bannerview.transform.ScaleInTransformer;
import com.tron.wallet.common.components.bannerview.utils.BannerUtils;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.List;
public class BannerViewPager<T, VH extends BaseViewHolder<T>> extends RelativeLayout {
    private int currentPosition;
    private boolean isCustomIndicator;
    private boolean isLooping;
    private BannerManager mBannerManager;
    private BaseBannerAdapter<T, VH> mBannerPagerAdapter;
    private CompositePageTransformer mCompositePageTransformer;
    private ViewPager2.PageTransformer mDefaultPageTransformer;
    private Handler mHandler;
    private RelativeLayout mIndicatorLayout;
    private IIndicator mIndicatorView;
    private MarginPageTransformer mMarginPageTransformer;
    private ViewPager2.OnPageChangeCallback mOnPageChangeCallback;
    private OnPageClickListener mOnPageClickListener;
    private Runnable mRunnable;
    private ViewPager2 mViewPager;
    private ViewPager2.OnPageChangeCallback onPageChangeCallback;
    private int startX;
    private int startY;

    public interface OnPageClickListener {
        void onPageClick(int i);
    }

    private void initIndicator(IIndicator iIndicator) {
        this.mIndicatorView = iIndicator;
    }

    private void initIndicatorGravity() {
    }

    private void initIndicatorViewMargin() {
    }

    private void setIndicatorValues(List<T> list) {
    }

    public BaseBannerAdapter<T, VH> getAdapter() {
        return this.mBannerPagerAdapter;
    }

    public int getCurrentItem() {
        return this.currentPosition;
    }

    public ViewPager2 getViewpager() {
        return this.mViewPager;
    }

    public BannerViewPager<T, VH> registerOnPageChangeCallback(ViewPager2.OnPageChangeCallback onPageChangeCallback) {
        this.onPageChangeCallback = onPageChangeCallback;
        return this;
    }

    public BannerViewPager<T, VH> setAdapter(BaseBannerAdapter<T, VH> baseBannerAdapter) {
        this.mBannerPagerAdapter = baseBannerAdapter;
        return this;
    }

    public BannerViewPager<T, VH> setOnPageClickListener(OnPageClickListener onPageClickListener) {
        this.mOnPageClickListener = onPageClickListener;
        return this;
    }

    public BannerViewPager(Context context) {
        this(context, null);
    }

    public BannerViewPager(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BannerViewPager(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mHandler = new Handler();
        this.mRunnable = new Runnable() {
            @Override
            public void run() {
                handlePosition();
            }
        };
        this.mOnPageChangeCallback = new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int i2, float f, int i3) {
                super.onPageScrolled(i2, f, i3);
                int listSize = mBannerPagerAdapter.getListSize();
                int realPosition = BannerUtils.getRealPosition(isCanLoop(), i2, listSize);
                if (listSize > 0) {
                    if (onPageChangeCallback != null) {
                        onPageChangeCallback.onPageScrolled(realPosition, f, i3);
                    }
                    IIndicator unused = mIndicatorView;
                }
            }

            @Override
            public void onPageSelected(int i2) {
                super.onPageSelected(i2);
                int listSize = mBannerPagerAdapter.getListSize();
                BannerViewPager bannerViewPager = BannerViewPager.this;
                bannerViewPager.currentPosition = BannerUtils.getRealPosition(bannerViewPager.isCanLoop(), i2, listSize);
                if ((listSize > 0 && isCanLoop() && i2 == 0) || i2 == 499) {
                    BannerViewPager bannerViewPager2 = BannerViewPager.this;
                    bannerViewPager2.resetCurrentItem(bannerViewPager2.currentPosition);
                }
                if (onPageChangeCallback != null) {
                    onPageChangeCallback.onPageSelected(currentPosition);
                }
                IIndicator unused = mIndicatorView;
            }

            @Override
            public void onPageScrollStateChanged(int i2) {
                super.onPageScrollStateChanged(i2);
                IIndicator unused = mIndicatorView;
                if (onPageChangeCallback != null) {
                    onPageChangeCallback.onPageScrollStateChanged(i2);
                }
            }
        };
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        this.mCompositePageTransformer = new CompositePageTransformer();
        BannerManager bannerManager = new BannerManager();
        this.mBannerManager = bannerManager;
        bannerManager.initAttrs(context, attributeSet);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.bvp_layout, this);
        ViewPager2 viewPager2 = (ViewPager2) findViewById(R.id.vp_main);
        this.mViewPager = viewPager2;
        viewPager2.setPageTransformer(this.mCompositePageTransformer);
    }

    @Override
    protected void onDetachedFromWindow() {
        stopLoop();
        super.onDetachedFromWindow();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startLoop();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.isLooping = true;
            stopLoop();
        } else if (action == 1 || action == 3 || action == 4) {
            this.isLooping = false;
            startLoop();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override
    public boolean onInterceptTouchEvent(android.view.MotionEvent r7) {
        


return true;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.common.components.bannerview.BannerViewPager.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    private void onVerticalActionMove(int i, int i2, int i3) {
        if (i3 <= i2) {
            if (i2 > i3) {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
        } else if (isCanLoop()) {
            getParent().requestDisallowInterceptTouchEvent(true);
        } else {
            int i4 = this.currentPosition;
            if (i4 == 0 && i - this.startY > 0) {
                getParent().requestDisallowInterceptTouchEvent(false);
            } else if (i4 == getData().size() - 1 && i - this.startY < 0) {
                getParent().requestDisallowInterceptTouchEvent(false);
            } else {
                getParent().requestDisallowInterceptTouchEvent(true);
            }
        }
    }

    private void onHorizontalActionMove(int i, int i2, int i3) {
        if (i2 <= i3) {
            if (i3 > i2) {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
        } else if (isCanLoop()) {
            getParent().requestDisallowInterceptTouchEvent(true);
        } else {
            int i4 = this.currentPosition;
            if (i4 == 0 && i - this.startX > 0) {
                getParent().requestDisallowInterceptTouchEvent(false);
            } else if (i4 == getData().size() - 1 && i - this.startX < 0) {
                getParent().requestDisallowInterceptTouchEvent(false);
            } else {
                getParent().requestDisallowInterceptTouchEvent(true);
            }
        }
    }

    public void handlePosition() {
        if (this.mBannerPagerAdapter.getListSize() <= 1 || !isAutoPlay()) {
            return;
        }
        ViewPager2 viewPager2 = this.mViewPager;
        viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        this.mHandler.postDelayed(this.mRunnable, getInterval());
    }

    private void initBannerData() {
        List<T> data = this.mBannerPagerAdapter.getData();
        if (data != null) {
            setupViewPager(data);
            initRoundCorner();
        }
    }

    private void initRoundCorner() {
        int roundRectRadius = this.mBannerManager.getBannerOptions().getRoundRectRadius();
        if (roundRectRadius > 0) {
            new ViewStyleSetter(this).setRoundRect(roundRectRadius);
        }
    }

    private void setupViewPager(List<T> list) {
        if (this.mBannerPagerAdapter == null) {
            throw new NullPointerException("You must set adapter for BannerViewPager");
        }
        BannerOptions bannerOptions = this.mBannerManager.getBannerOptions();
        if (bannerOptions.getScrollDuration() != 0) {
            ScrollDurationManger.reflectLayoutManager(this.mViewPager, bannerOptions.getScrollDuration());
        }
        if (bannerOptions.getRightRevealWidth() != -1000 || bannerOptions.getLeftRevealWidth() != -1000) {
            RecyclerView recyclerView = (RecyclerView) this.mViewPager.getChildAt(0);
            int orientation = bannerOptions.getOrientation();
            int pageMargin = bannerOptions.getPageMargin() + bannerOptions.getRightRevealWidth();
            int pageMargin2 = bannerOptions.getPageMargin() + bannerOptions.getLeftRevealWidth();
            if (orientation == 0) {
                recyclerView.setPadding(pageMargin2, 0, pageMargin, 0);
            } else if (orientation == 1) {
                recyclerView.setPadding(0, pageMargin2, 0, pageMargin);
            }
            recyclerView.setClipToPadding(false);
        }
        this.currentPosition = 0;
        this.mBannerPagerAdapter.setCanLoop(isCanLoop());
        this.mBannerPagerAdapter.setPageClickListener(this.mOnPageClickListener);
        this.mViewPager.setAdapter(this.mBannerPagerAdapter);
        if (list.size() > 1 && isCanLoop()) {
            this.mViewPager.setCurrentItem(251 - (250 % list.size()), false);
        }
        this.mViewPager.unregisterOnPageChangeCallback(this.mOnPageChangeCallback);
        this.mViewPager.registerOnPageChangeCallback(this.mOnPageChangeCallback);
        this.mViewPager.setOrientation(bannerOptions.getOrientation());
        this.mViewPager.setOffscreenPageLimit(bannerOptions.getOffScreenPageLimit());
        initPageStyle();
        startLoop();
    }

    private void initPageStyle() {
        int pageStyle = this.mBannerManager.getBannerOptions().getPageStyle();
        if (pageStyle == 4) {
            setMultiPageStyle(true, this.mBannerManager.getBannerOptions().getPageScale());
        } else if (pageStyle != 8) {
        } else {
            setMultiPageStyle(false, this.mBannerManager.getBannerOptions().getPageScale());
        }
    }

    private void setMultiPageStyle(boolean z, float f) {
        ViewPager2.PageTransformer pageTransformer = this.mDefaultPageTransformer;
        if (pageTransformer != null) {
            this.mCompositePageTransformer.removeTransformer(pageTransformer);
        }
        if (!z) {
            this.mDefaultPageTransformer = new ScaleInTransformer(f);
        }
        addPageTransformer(this.mDefaultPageTransformer);
    }

    public void resetCurrentItem(int i) {
        if (isCanLoop() && this.mBannerPagerAdapter.getListSize() > 1) {
            this.mViewPager.setCurrentItem((251 - (250 % this.mBannerPagerAdapter.getListSize())) + i, false);
        } else {
            this.mViewPager.setCurrentItem(i, false);
        }
    }

    private int getInterval() {
        return this.mBannerManager.getBannerOptions().getInterval();
    }

    private boolean isAutoPlay() {
        return this.mBannerManager.getBannerOptions().isAutoPlay();
    }

    public boolean isCanLoop() {
        return this.mBannerManager.getBannerOptions().isCanLoop();
    }

    public List<T> getData() {
        return this.mBannerPagerAdapter.getData();
    }

    public void startLoop() {
        BaseBannerAdapter<T, VH> baseBannerAdapter;
        if (this.isLooping || !isAutoPlay() || (baseBannerAdapter = this.mBannerPagerAdapter) == null || baseBannerAdapter.getListSize() <= 1) {
            return;
        }
        this.mHandler.postDelayed(this.mRunnable, getInterval());
        this.isLooping = true;
    }

    public void stopLoop() {
        if (this.isLooping) {
            this.mHandler.removeCallbacks(this.mRunnable);
            this.isLooping = false;
        }
    }

    public BannerViewPager<T, VH> setRoundCorner(int i) {
        this.mBannerManager.getBannerOptions().setRoundRectRadius(i);
        return this;
    }

    public BannerViewPager<T, VH> setRoundRect(int i) {
        setRoundCorner(i);
        return this;
    }

    public BannerViewPager<T, VH> setAutoPlay(boolean z) {
        this.mBannerManager.getBannerOptions().setAutoPlay(z);
        if (isAutoPlay()) {
            this.mBannerManager.getBannerOptions().setCanLoop(true);
        }
        return this;
    }

    public BannerViewPager<T, VH> setCanLoop(boolean z) {
        this.mBannerManager.getBannerOptions().setCanLoop(z);
        if (!z) {
            this.mBannerManager.getBannerOptions().setAutoPlay(false);
        }
        return this;
    }

    public BannerViewPager<T, VH> setInterval(int i) {
        this.mBannerManager.getBannerOptions().setInterval(i);
        return this;
    }

    public BannerViewPager<T, VH> setPageTransformer(ViewPager2.PageTransformer pageTransformer) {
        if (pageTransformer != null) {
            this.mViewPager.setPageTransformer(pageTransformer);
        }
        return this;
    }

    public BannerViewPager<T, VH> addPageTransformer(ViewPager2.PageTransformer pageTransformer) {
        if (pageTransformer != null) {
            this.mCompositePageTransformer.addTransformer(pageTransformer);
        }
        return this;
    }

    public void removeTransformer(ViewPager2.PageTransformer pageTransformer) {
        if (pageTransformer != null) {
            this.mCompositePageTransformer.removeTransformer(pageTransformer);
        }
    }

    public void removeDefaultPageTransformer() {
        ViewPager2.PageTransformer pageTransformer = this.mDefaultPageTransformer;
        if (pageTransformer != null) {
            this.mCompositePageTransformer.removeTransformer(pageTransformer);
        }
    }

    public void removeMarginPageTransformer() {
        MarginPageTransformer marginPageTransformer = this.mMarginPageTransformer;
        if (marginPageTransformer != null) {
            this.mCompositePageTransformer.removeTransformer(marginPageTransformer);
        }
    }

    public BannerViewPager<T, VH> setPageMargin(int i) {
        this.mBannerManager.getBannerOptions().setPageMargin(i);
        MarginPageTransformer marginPageTransformer = this.mMarginPageTransformer;
        if (marginPageTransformer != null) {
            this.mCompositePageTransformer.removeTransformer(marginPageTransformer);
        }
        MarginPageTransformer marginPageTransformer2 = new MarginPageTransformer(i);
        this.mMarginPageTransformer = marginPageTransformer2;
        this.mCompositePageTransformer.addTransformer(marginPageTransformer2);
        return this;
    }

    public BannerViewPager<T, VH> setScrollDuration(int i) {
        this.mBannerManager.getBannerOptions().setScrollDuration(i);
        return this;
    }

    public BannerViewPager<T, VH> setIndicatorVisibility(int i) {
        this.mBannerManager.getBannerOptions().setIndicatorVisibility(i);
        return this;
    }

    public BannerViewPager<T, VH> setIndicatorGravity(int i) {
        this.mBannerManager.getBannerOptions().setIndicatorGravity(i);
        return this;
    }

    public void create(List<T> list) {
        BaseBannerAdapter<T, VH> baseBannerAdapter = this.mBannerPagerAdapter;
        if (baseBannerAdapter == null) {
            throw new NullPointerException("You must set adapter for BannerViewPager");
        }
        baseBannerAdapter.setData(list);
        initBannerData();
    }

    public void create() {
        create(new ArrayList());
    }

    public BannerViewPager<T, VH> setOrientation(int i) {
        this.mBannerManager.getBannerOptions().setOrientation(i);
        return this;
    }

    public void refreshData(List<T> list) {
        if (list == null || this.mBannerPagerAdapter == null) {
            return;
        }
        stopLoop();
        this.mBannerPagerAdapter.setData(list);
        this.mBannerPagerAdapter.notifyDataSetChanged();
        resetCurrentItem(getCurrentItem());
        setIndicatorValues(list);
        startLoop();
    }

    public void setCurrentItem(int i) {
        if (isCanLoop() && this.mBannerPagerAdapter.getListSize() > 1) {
            int currentItem = this.mViewPager.getCurrentItem();
            int listSize = this.mBannerPagerAdapter.getListSize();
            int realPosition = BannerUtils.getRealPosition(isCanLoop(), currentItem, this.mBannerPagerAdapter.getListSize());
            if (currentItem != i) {
                if (i == 0 && realPosition == listSize - 1) {
                    this.mViewPager.setCurrentItem(currentItem + 1);
                } else if (realPosition == 0 && i == listSize - 1) {
                    this.mViewPager.setCurrentItem(currentItem - 1);
                } else {
                    this.mViewPager.setCurrentItem((i - realPosition) + currentItem);
                }
                this.mViewPager.setCurrentItem(currentItem + (i - realPosition));
                return;
            }
            return;
        }
        this.mViewPager.setCurrentItem(i);
    }

    public void setCurrentItem(int i, boolean z) {
        if (isCanLoop() && this.mBannerPagerAdapter.getListSize() > 1) {
            int listSize = this.mBannerPagerAdapter.getListSize();
            int currentItem = this.mViewPager.getCurrentItem();
            int realPosition = BannerUtils.getRealPosition(isCanLoop(), currentItem, listSize);
            if (currentItem != i) {
                if (i == 0 && realPosition == listSize - 1) {
                    this.mViewPager.setCurrentItem(currentItem + 1, z);
                    return;
                } else if (realPosition == 0 && i == listSize - 1) {
                    this.mViewPager.setCurrentItem(currentItem - 1, z);
                    return;
                } else {
                    this.mViewPager.setCurrentItem(currentItem + (i - realPosition), z);
                    return;
                }
            }
            return;
        }
        this.mViewPager.setCurrentItem(i, z);
    }

    public BannerViewPager<T, VH> setPageStyle(int i) {
        return setPageStyle(i, 0.85f);
    }

    public BannerViewPager<T, VH> setPageStyle(int i, float f) {
        this.mBannerManager.getBannerOptions().setPageStyle(i);
        this.mBannerManager.getBannerOptions().setPageScale(f);
        return this;
    }

    public BannerViewPager<T, VH> setRevealWidth(int i) {
        setRevealWidth(i, i);
        return this;
    }

    public BannerViewPager<T, VH> setRevealWidth(int i, int i2) {
        this.mBannerManager.getBannerOptions().setRightRevealWidth(i2);
        this.mBannerManager.getBannerOptions().setLeftRevealWidth(i);
        return this;
    }

    public BannerViewPager<T, VH> setOffScreenPageLimit(int i) {
        this.mBannerManager.getBannerOptions().setOffScreenPageLimit(i);
        return this;
    }

    public BannerViewPager<T, VH> setIndicatorMargin(int i, int i2, int i3, int i4) {
        this.mBannerManager.getBannerOptions().setIndicatorMargin(i, i2, i3, i4);
        return this;
    }

    public BannerViewPager<T, VH> setUserInputEnabled(boolean z) {
        this.mBannerManager.getBannerOptions().setUserInputEnabled(z);
        this.mViewPager.setUserInputEnabled(z);
        return this;
    }

    @Deprecated
    public BannerViewPager<T, VH> showIndicator(boolean z) {
        this.mIndicatorLayout.setVisibility(z ? View.VISIBLE : View.GONE);
        return this;
    }

    @Deprecated
    public BannerViewPager<T, VH> disableTouchScroll(boolean z) {
        this.mBannerManager.getBannerOptions().setUserInputEnabled(!z);
        return this;
    }
}
