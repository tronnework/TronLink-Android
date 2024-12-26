package com.tron.wallet.common.components.xtablayout;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.core.util.Pools;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TextViewCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.R;
import com.tron.wallet.common.components.xtablayout.ValueAnimatorCompat;
import com.tron.wallet.common.utils.CustomFontUtils;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.common.utils.UIUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class XTabLayout extends HorizontalScrollView {
    private static final int ANIMATION_DURATION = 300;
    private static final int DEFAULT_GAP_TEXT_ICON = 8;
    private static final int DEFAULT_HEIGHT = 48;
    private static final int DEFAULT_HEIGHT_WITH_TEXT_ICON = 72;
    private static final int FIXED_WRAP_GUTTER_MIN = 16;
    public static final int GRAVITY_CENTER = 1;
    public static final int GRAVITY_FILL = 0;
    private static final int INVALID_WIDTH = -1;
    public static final int MODE_FIXED = 1;
    public static final int MODE_SCROLLABLE = 0;
    private static final int MOTION_NON_ADJACENT_OFFSET = 24;
    private static final int SELECTED_TAB_ADD_WIDTH = 20;
    private static final int TAB_MIN_WIDTH_MARGIN = 40;
    private static final Pools.Pool<Tab> sTabPool = new Pools.SynchronizedPool(16);
    private int dividerColor;
    private Drawable dividerDrawable;
    private int dividerGravity;
    private int dividerHeight;
    private int dividerWidth;
    private int mContentGravity;
    private int mContentInsetStart;
    private int mMode;
    private OnTabSelectedListener mOnTabSelectedListener;
    private List<OnTabSelectedListener> mOnTabSelectedListenerList;
    private TabLayoutOnPageChangeListener mPageChangeListener;
    private PagerAdapter mPagerAdapter;
    private DataSetObserver mPagerAdapterObserver;
    private final int mRequestedTabMaxWidth;
    private final int mRequestedTabMinWidth;
    private ValueAnimatorCompat mScrollAnimator;
    private final int mScrollableTabMinWidth;
    private Tab mSelectedTab;
    private int mTabGravity;
    private int mTabMaxWidth;
    private int mTabPaddingBottom;
    private int mTabPaddingEnd;
    private int mTabPaddingStart;
    private int mTabPaddingTop;
    private float mTabSelectedTextSize;
    private final SlidingTabStrip mTabStrip;
    private int mTabTextAppearance;
    private ColorStateList mTabTextColors;
    private float mTabTextMultiLineSize;
    private float mTabTextSize;
    private final Pools.Pool<TabView> mTabViewPool;
    private final ArrayList<Tab> mTabs;
    private ViewPager mViewPager;
    private final int xTabBackgroundColor;
    private int xTabDisplayNum;
    private boolean xTabDividerWidthWidthText;
    private final int xTabSelectedBackgroundColor;
    private Drawable xTabSelectedTextBackground;
    private boolean xTabTextAllCaps;
    private Drawable xTabTextBackground;
    private boolean xTabTextBold;
    private boolean xTabTextSelectedBold;

    public interface IXTabPagerAdapter {
        View getPageCustomView(int i, ViewGroup viewGroup);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    public interface OnTabSelectedListener {
        void onTabReselected(Tab tab);

        void onTabSelected(Tab tab);

        void onTabUnselected(Tab tab);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TabGravity {
    }

    public int getTabMaxWidth() {
        return this.mTabMaxWidth;
    }

    public int getTabGravity() {
        return this.mTabGravity;
    }

    public int getTabMode() {
        return this.mMode;
    }

    public ColorStateList getTabTextColors() {
        return this.mTabTextColors;
    }

    public void setAllCaps(boolean z) {
        this.xTabTextAllCaps = z;
    }

    public void setDivider(Drawable drawable) {
        this.dividerDrawable = drawable;
    }

    public void setOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        this.mOnTabSelectedListener = onTabSelectedListener;
    }

    public void setxTabDisplayNum(int i) {
        this.xTabDisplayNum = i;
    }

    public XTabLayout(Context context) {
        this(context, null);
    }

    public XTabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public XTabLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mTabs = new ArrayList<>();
        this.mTabViewPool = new Pools.SimplePool(12);
        this.xTabTextAllCaps = false;
        this.xTabDividerWidthWidthText = false;
        this.mTabTextSize = 0.0f;
        this.mTabSelectedTextSize = 0.0f;
        this.mTabMaxWidth = Integer.MAX_VALUE;
        this.mOnTabSelectedListenerList = new ArrayList();
        ThemeUtils.checkAppCompatTheme(context);
        setHorizontalScrollBarEnabled(false);
        SlidingTabStrip slidingTabStrip = new SlidingTabStrip(context);
        this.mTabStrip = slidingTabStrip;
        super.addView(slidingTabStrip, 0, new FrameLayout.LayoutParams(-2, -1));
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.XTabLayout, i, 2131886694);
        slidingTabStrip.setSelectedIndicatorHeight(obtainStyledAttributes.getDimensionPixelSize(11, dpToPx(2)));
        slidingTabStrip.setmSelectedIndicatorWidth(obtainStyledAttributes.getDimensionPixelSize(12, 0));
        slidingTabStrip.setSelectedIndicatorColor(obtainStyledAttributes.getColor(10, 0));
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(16, 0);
        this.mTabPaddingBottom = dimensionPixelSize;
        this.mTabPaddingEnd = dimensionPixelSize;
        this.mTabPaddingTop = dimensionPixelSize;
        this.mTabPaddingStart = dimensionPixelSize;
        this.mTabPaddingStart = obtainStyledAttributes.getDimensionPixelSize(19, dimensionPixelSize);
        this.mTabPaddingTop = obtainStyledAttributes.getDimensionPixelSize(20, this.mTabPaddingTop);
        this.mTabPaddingEnd = obtainStyledAttributes.getDimensionPixelSize(18, this.mTabPaddingEnd);
        this.mTabPaddingBottom = obtainStyledAttributes.getDimensionPixelSize(17, this.mTabPaddingBottom);
        this.xTabTextAllCaps = obtainStyledAttributes.getBoolean(26, false);
        this.mTabTextAppearance = obtainStyledAttributes.getResourceId(27, 2131886463);
        this.mTabTextSize = obtainStyledAttributes.getDimensionPixelSize(32, 0);
        this.xTabTextBold = obtainStyledAttributes.getBoolean(29, false);
        this.mTabSelectedTextSize = obtainStyledAttributes.getDimensionPixelSize(24, 0);
        this.xTabTextSelectedBold = obtainStyledAttributes.getBoolean(31, false);
        this.xTabTextBackground = obtainStyledAttributes.getDrawable(28);
        this.xTabSelectedTextBackground = obtainStyledAttributes.getDrawable(22);
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(this.mTabTextAppearance, com.google.android.material.R.styleable.TextAppearance);
        try {
            if (this.mTabTextSize == 0.0f) {
                this.mTabTextSize = obtainStyledAttributes2.getDimensionPixelSize(0, 0);
            }
            this.mTabTextColors = obtainStyledAttributes2.getColorStateList(3);
            obtainStyledAttributes2.recycle();
            if (obtainStyledAttributes.hasValue(30)) {
                this.mTabTextColors = obtainStyledAttributes.getColorStateList(30);
            }
            if (obtainStyledAttributes.hasValue(23)) {
                this.mTabTextColors = createColorStateList(this.mTabTextColors.getDefaultColor(), obtainStyledAttributes.getColor(23, 0));
            }
            this.xTabDisplayNum = obtainStyledAttributes.getInt(3, 0);
            this.mRequestedTabMinWidth = obtainStyledAttributes.getDimensionPixelSize(14, -1);
            this.mRequestedTabMaxWidth = obtainStyledAttributes.getDimensionPixelSize(13, -1);
            this.xTabBackgroundColor = obtainStyledAttributes.getColor(0, 0);
            this.xTabSelectedBackgroundColor = obtainStyledAttributes.getColor(21, 0);
            this.mContentInsetStart = obtainStyledAttributes.getDimensionPixelSize(2, 0);
            this.mMode = obtainStyledAttributes.getInt(15, 1);
            this.mTabGravity = obtainStyledAttributes.getInt(9, 0);
            this.mContentGravity = obtainStyledAttributes.getInt(1, 0);
            this.dividerWidth = obtainStyledAttributes.getDimensionPixelSize(7, 0);
            this.dividerHeight = obtainStyledAttributes.getDimensionPixelSize(6, 0);
            this.dividerColor = obtainStyledAttributes.getColor(4, ViewCompat.MEASURED_STATE_MASK);
            this.dividerGravity = obtainStyledAttributes.getInteger(5, 1);
            this.xTabDividerWidthWidthText = obtainStyledAttributes.getBoolean(8, false);
            obtainStyledAttributes.recycle();
            Resources resources = getResources();
            this.mTabTextMultiLineSize = resources.getDimensionPixelSize(com.tronlinkpro.wallet.R.dimen.design_tab_text_size_2line);
            this.mScrollableTabMinWidth = resources.getDimensionPixelSize(com.tronlinkpro.wallet.R.dimen.design_tab_scrollable_min_width);
            applyModeAndGravity();
            addDivider();
        } catch (Throwable th) {
            obtainStyledAttributes2.recycle();
            throw th;
        }
    }

    private static ColorStateList createColorStateList(int i, int i2) {
        return new ColorStateList(new int[][]{SELECTED_STATE_SET, EMPTY_STATE_SET}, new int[]{i2, i});
    }

    private void addDivider() {
        post(new Runnable() {
            @Override
            public void run() {
                if (dividerWidth > 0) {
                    LinearLayout linearLayout = (LinearLayout) getChildAt(0);
                    linearLayout.setShowDividers(2);
                    DividerDrawable dividerDrawable = new DividerDrawable(getContext());
                    dividerDrawable.setDividerSize(dividerWidth, dividerHeight);
                    dividerDrawable.setColor(dividerColor);
                    dividerDrawable.setGravity(dividerGravity);
                    linearLayout.setDividerDrawable(dividerDrawable);
                } else if (dividerDrawable != null) {
                    LinearLayout linearLayout2 = (LinearLayout) getChildAt(0);
                    linearLayout2.setShowDividers(2);
                    linearLayout2.setDividerDrawable(dividerDrawable);
                }
            }
        });
    }

    public void setDividerSize(int i, int i2) {
        this.dividerWidth = i;
        this.dividerHeight = i2;
        addDivider();
    }

    public void setDividerColor(int i) {
        this.dividerColor = i;
        addDivider();
    }

    public void setDividerGravity(int i) {
        this.dividerGravity = i;
        addDivider();
    }

    public void setSelectedTabIndicatorColor(int i) {
        this.mTabStrip.setSelectedIndicatorColor(i);
    }

    public void setSelectedTabIndicatorHeight(int i) {
        this.mTabStrip.setSelectedIndicatorHeight(i);
    }

    public void setScrollPosition(int i, float f, boolean z) {
        setScrollPosition(i, f, z, true);
    }

    public void setScrollPosition(int i, float f, boolean z, boolean z2) {
        int round = Math.round(i + f);
        if (round < 0 || round >= this.mTabStrip.getChildCount()) {
            return;
        }
        if (z2) {
            this.mTabStrip.setIndicatorPositionFromTabPosition(i, f);
        }
        ValueAnimatorCompat valueAnimatorCompat = this.mScrollAnimator;
        if (valueAnimatorCompat != null && valueAnimatorCompat.isRunning()) {
            this.mScrollAnimator.cancel();
        }
        scrollTo(calculateScrollXForTab(i, f), 0);
        if (z) {
            setSelectedTabView(round);
        }
    }

    private float getScrollPosition() {
        return this.mTabStrip.getIndicatorPosition();
    }

    public void addTab(Tab tab) {
        addTab(tab, this.mTabs.isEmpty());
    }

    public void addTab(Tab tab, int i) {
        addTab(tab, i, this.mTabs.isEmpty());
    }

    public void addTab(Tab tab, boolean z) {
        if (tab.mParent != this) {
            throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
        }
        addTabView(tab, z);
        configureTab(tab, this.mTabs.size());
        if (z) {
            tab.select();
        }
    }

    public void addTab(Tab tab, int i, boolean z) {
        if (tab.mParent != this) {
            throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
        }
        addTabView(tab, i, z);
        configureTab(tab, i);
        if (z) {
            tab.select();
        }
    }

    private void addTabFromItemView(TabItem tabItem) {
        Tab newTab = newTab();
        if (tabItem.mText != null) {
            newTab.setText(tabItem.mText);
        }
        if (tabItem.mIcon != null) {
            newTab.setIcon(tabItem.mIcon);
        }
        if (tabItem.mCustomLayout != 0) {
            newTab.setCustomView(tabItem.mCustomLayout);
        }
        addTab(newTab);
    }

    public void addOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        this.mOnTabSelectedListenerList.add(onTabSelectedListener);
    }

    public Tab newTab() {
        Tab acquire = sTabPool.acquire();
        if (acquire == null) {
            acquire = new Tab();
        }
        acquire.mParent = this;
        acquire.mView = createTabView(acquire);
        return acquire;
    }

    public int getTabCount() {
        return this.mTabs.size();
    }

    public Tab getTabAt(int i) {
        return this.mTabs.get(i);
    }

    public int getSelectedTabPosition() {
        Tab tab = this.mSelectedTab;
        if (tab != null) {
            return tab.getPosition();
        }
        return -1;
    }

    public void removeTab(Tab tab) {
        if (tab.mParent != this) {
            throw new IllegalArgumentException("Tab does not belong to this TabLayout.");
        }
        removeTabAt(tab.getPosition());
    }

    public void removeTabAt(int i) {
        Tab tab = this.mSelectedTab;
        int position = tab != null ? tab.getPosition() : 0;
        removeTabViewAt(i);
        Tab remove = this.mTabs.remove(i);
        if (remove != null) {
            remove.reset();
            sTabPool.release(remove);
        }
        int size = this.mTabs.size();
        for (int i2 = i; i2 < size; i2++) {
            this.mTabs.get(i2).setPosition(i2);
        }
        if (position == i) {
            selectTab(this.mTabs.isEmpty() ? null : this.mTabs.get(Math.max(0, i - 1)));
        }
    }

    public void removeAllTabs() {
        for (int childCount = this.mTabStrip.getChildCount() - 1; childCount >= 0; childCount--) {
            removeTabViewAt(childCount);
        }
        Iterator<Tab> it = this.mTabs.iterator();
        while (it.hasNext()) {
            Tab next = it.next();
            it.remove();
            next.reset();
            sTabPool.release(next);
        }
        this.mSelectedTab = null;
    }

    public void setTabMode(int i) {
        if (i != this.mMode) {
            this.mMode = i;
            applyModeAndGravity();
        }
    }

    public void setTabGravity(int i) {
        if (this.mTabGravity != i) {
            this.mTabGravity = i;
            applyModeAndGravity();
        }
    }

    public void setTabTextColors(ColorStateList colorStateList) {
        if (this.mTabTextColors != colorStateList) {
            this.mTabTextColors = colorStateList;
            updateAllTabs();
        }
    }

    public void setTabTextColors(int i, int i2) {
        setTabTextColors(createColorStateList(i, i2));
    }

    public void setupWithViewPager(ViewPager viewPager) {
        TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener;
        ViewPager viewPager2 = this.mViewPager;
        if (viewPager2 != null && (tabLayoutOnPageChangeListener = this.mPageChangeListener) != null) {
            viewPager2.removeOnPageChangeListener(tabLayoutOnPageChangeListener);
        }
        if (viewPager != null) {
            PagerAdapter adapter = viewPager.getAdapter();
            if (adapter == null) {
                throw new IllegalArgumentException("ViewPager does not have a PagerAdapter set");
            }
            this.mViewPager = viewPager;
            if (this.mPageChangeListener == null) {
                this.mPageChangeListener = new TabLayoutOnPageChangeListener(this);
            }
            this.mPageChangeListener.reset();
            viewPager.addOnPageChangeListener(this.mPageChangeListener);
            setOnTabSelectedListener(new ViewPagerOnTabSelectedListener(viewPager));
            setPagerAdapter(adapter, true);
            return;
        }
        this.mViewPager = null;
        setOnTabSelectedListener(null);
        setPagerAdapter(null, true);
    }

    @Deprecated
    public void setTabsFromPagerAdapter(PagerAdapter pagerAdapter) {
        setPagerAdapter(pagerAdapter, false);
    }

    @Override
    public boolean shouldDelayChildPressedState() {
        return getTabScrollRange() > 0;
    }

    private int getTabScrollRange() {
        return Math.max(0, ((this.mTabStrip.getWidth() - getWidth()) - getPaddingLeft()) - getPaddingRight());
    }

    private void setPagerAdapter(PagerAdapter pagerAdapter, boolean z) {
        DataSetObserver dataSetObserver;
        PagerAdapter pagerAdapter2 = this.mPagerAdapter;
        if (pagerAdapter2 != null && (dataSetObserver = this.mPagerAdapterObserver) != null) {
            pagerAdapter2.unregisterDataSetObserver(dataSetObserver);
        }
        this.mPagerAdapter = pagerAdapter;
        if (z && pagerAdapter != null) {
            if (this.mPagerAdapterObserver == null) {
                this.mPagerAdapterObserver = new PagerAdapterObserver();
            }
            pagerAdapter.registerDataSetObserver(this.mPagerAdapterObserver);
        }
        populateFromPagerAdapter();
    }

    public void populateFromPagerAdapter() {
        int currentItem;
        Tab text;
        removeAllTabs();
        PagerAdapter pagerAdapter = this.mPagerAdapter;
        if (pagerAdapter != null) {
            int count = pagerAdapter.getCount();
            PagerAdapter pagerAdapter2 = this.mPagerAdapter;
            IXTabPagerAdapter iXTabPagerAdapter = pagerAdapter2 instanceof IXTabPagerAdapter ? (IXTabPagerAdapter) pagerAdapter2 : null;
            for (int i = 0; i < count; i++) {
                if (iXTabPagerAdapter != null) {
                    text = newTab();
                    text.setCustomView(iXTabPagerAdapter.getPageCustomView(i, text.mView));
                } else {
                    text = newTab().setText(this.mPagerAdapter.getPageTitle(i));
                }
                addTab(text, false);
            }
            ViewPager viewPager = this.mViewPager;
            if (viewPager == null || count <= 0 || (currentItem = viewPager.getCurrentItem()) == getSelectedTabPosition() || currentItem >= getTabCount()) {
                return;
            }
            selectTab(getTabAt(currentItem));
            return;
        }
        removeAllTabs();
    }

    private void updateAllTabs() {
        int size = this.mTabs.size();
        for (int i = 0; i < size; i++) {
            this.mTabs.get(i).updateView();
        }
    }

    private TabView createTabView(Tab tab) {
        Pools.Pool<TabView> pool = this.mTabViewPool;
        TabView acquire = pool != null ? pool.acquire() : null;
        if (acquire == null) {
            acquire = new TabView(getContext());
        }
        acquire.setTab(tab);
        acquire.setFocusable(true);
        acquire.setMinimumWidth(getTabMinWidth());
        return acquire;
    }

    private void configureTab(Tab tab, int i) {
        tab.setPosition(i);
        this.mTabs.add(i, tab);
        int size = this.mTabs.size();
        while (true) {
            i++;
            if (i >= size) {
                return;
            }
            this.mTabs.get(i).setPosition(i);
        }
    }

    private void addTabView(Tab tab, boolean z) {
        final TabView tabView = tab.mView;
        if (this.mTabSelectedTextSize != 0.0f) {
            tabView.post(new Runnable() {
                @Override
                public void run() {
                    int width = tabView.getWidth();
                    String text = tabView.getText();
                    if (TextUtils.isEmpty(text)) {
                        return;
                    }
                    Paint paint = new Paint();
                    paint.setTypeface(CustomFontUtils.getTypeface(getContext(), 1));
                    paint.setTextSize(mTabSelectedTextSize);
                    Rect rect = new Rect();
                    paint.getTextBounds(text, 0, text.length(), rect);
                    if (width - rect.width() < dpToPx(20)) {
                        int width2 = rect.width() + dpToPx(20);
                        ViewGroup.LayoutParams layoutParams = tabView.getLayoutParams();
                        layoutParams.width = width2;
                        tabView.setLayoutParams(layoutParams);
                    }
                }
            });
        }
        this.mTabStrip.addView(tabView, createLayoutParamsForTabs());
        if (z) {
            tabView.setSelected(true);
        }
    }

    private void addTabView(Tab tab, int i, boolean z) {
        TabView tabView = tab.mView;
        this.mTabStrip.addView(tabView, i, createLayoutParamsForTabs());
        if (z) {
            tabView.setSelected(true);
        }
        int dip2px = UIUtils.dip2px(5.0f);
        TouchDelegateUtils.expandViewTouchDelegate(tabView, dip2px, dip2px, dip2px, dip2px);
    }

    @Override
    public void addView(View view) {
        addViewInternal(view);
    }

    @Override
    public void addView(View view, int i) {
        addViewInternal(view);
    }

    @Override
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        addViewInternal(view);
    }

    @Override
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        addViewInternal(view);
    }

    private void addViewInternal(View view) {
        if (view instanceof TabItem) {
            addTabFromItemView((TabItem) view);
            return;
        }
        throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
    }

    private LinearLayout.LayoutParams createLayoutParamsForTabs() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
        updateTabViewLayoutParams(layoutParams);
        return layoutParams;
    }

    private void updateTabViewLayoutParams(LinearLayout.LayoutParams layoutParams) {
        if (this.mMode == 1 && this.mTabGravity == 0) {
            layoutParams.width = 0;
            layoutParams.weight = 1.0f;
            return;
        }
        layoutParams.width = -2;
        layoutParams.weight = 0.0f;
    }

    public int dpToPx(int i) {
        return Math.round(getResources().getDisplayMetrics().density * i);
    }

    @Override
    protected void onMeasure(int i, int i2) {
        int dpToPx = dpToPx(getDefaultHeight()) + getPaddingTop() + getPaddingBottom();
        int mode = View.MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE) {
            i2 = View.MeasureSpec.makeMeasureSpec(Math.min(dpToPx, View.MeasureSpec.getSize(i2)), MeasureSpec.AT_MOST);
        } else if (mode == 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(dpToPx, MeasureSpec.AT_MOST);
        }
        int size = View.MeasureSpec.getSize(i);
        if (View.MeasureSpec.getMode(i) != 0) {
            LogUtils.w("BBB", "specWidth:" + size);
            PagerAdapter pagerAdapter = this.mPagerAdapter;
            if (pagerAdapter == null || this.xTabDisplayNum == 0) {
                int i3 = this.mRequestedTabMaxWidth;
                if (i3 > 0) {
                    this.mTabMaxWidth = i3;
                } else {
                    if (getTabCount() > 1) {
                        size -= dpToPx(40);
                    }
                    this.mTabMaxWidth = size;
                }
            } else if (pagerAdapter.getCount() == 1 || this.xTabDisplayNum == 1) {
                this.mTabMaxWidth = ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
            } else {
                int i4 = this.mRequestedTabMaxWidth;
                if (i4 <= 0) {
                    i4 = size - dpToPx(40);
                }
                this.mTabMaxWidth = i4;
            }
        }
        super.onMeasure(i, i2);
        if (getChildCount() == 1) {
            View childAt = getChildAt(0);
            int i5 = this.mMode;
            if (i5 == 0) {
                if (childAt.getMeasuredWidth() >= getMeasuredWidth()) {
                    return;
                }
            } else if (i5 != 1 || childAt.getMeasuredWidth() == getMeasuredWidth()) {
                return;
            }
            childAt.measure(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), MeasureSpec.AT_MOST), getChildMeasureSpec(i2, getPaddingTop() + getPaddingBottom(), childAt.getLayoutParams().height));
        }
    }

    private void removeTabViewAt(int i) {
        TabView tabView = (TabView) this.mTabStrip.getChildAt(i);
        this.mTabStrip.removeViewAt(i);
        if (tabView != null) {
            tabView.reset();
            this.mTabViewPool.release(tabView);
        }
        requestLayout();
    }

    private void animateToTab(int i) {
        if (i == -1) {
            return;
        }
        if (getWindowToken() == null || !ViewCompat.isLaidOut(this) || this.mTabStrip.childrenNeedLayout()) {
            setScrollPosition(i, 0.0f, true);
            return;
        }
        int scrollX = getScrollX();
        int calculateScrollXForTab = calculateScrollXForTab(i, 0.0f);
        if (scrollX != calculateScrollXForTab) {
            if (this.mScrollAnimator == null) {
                ValueAnimatorCompat createAnimator = ViewUtils.createAnimator();
                this.mScrollAnimator = createAnimator;
                createAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
                this.mScrollAnimator.setDuration(300);
                this.mScrollAnimator.setUpdateListener(new ValueAnimatorCompat.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimatorCompat valueAnimatorCompat) {
                        scrollTo(valueAnimatorCompat.getAnimatedIntValue(), 0);
                    }
                });
            }
            this.mScrollAnimator.setIntValues(scrollX, calculateScrollXForTab);
            this.mScrollAnimator.start();
        }
        this.mTabStrip.animateIndicatorToPosition(i, 300);
    }

    private void setSelectedTabView(int i) {
        int childCount = this.mTabStrip.getChildCount();
        if (i >= childCount || this.mTabStrip.getChildAt(i).isSelected()) {
            return;
        }
        int i2 = 0;
        while (i2 < childCount) {
            this.mTabStrip.getChildAt(i2).setSelected(i2 == i);
            i2++;
        }
    }

    void selectTab(Tab tab) {
        selectTab(tab, true);
    }

    void selectTab(Tab tab, boolean z) {
        OnTabSelectedListener onTabSelectedListener;
        OnTabSelectedListener onTabSelectedListener2;
        Tab tab2 = this.mSelectedTab;
        if (tab2 == tab) {
            if (tab2 != null) {
                OnTabSelectedListener onTabSelectedListener3 = this.mOnTabSelectedListener;
                if (onTabSelectedListener3 != null) {
                    onTabSelectedListener3.onTabReselected(tab2);
                }
                for (OnTabSelectedListener onTabSelectedListener4 : this.mOnTabSelectedListenerList) {
                    onTabSelectedListener4.onTabReselected(this.mSelectedTab);
                }
                animateToTab(tab.getPosition());
                return;
            }
            return;
        }
        if (z) {
            int position = tab != null ? tab.getPosition() : -1;
            if (position != -1) {
                setSelectedTabView(position);
            }
            Tab tab3 = this.mSelectedTab;
            if ((tab3 == null || tab3.getPosition() == -1) && position != -1) {
                setScrollPosition(position, 0.0f, true);
            } else {
                animateToTab(position);
            }
        }
        Tab tab4 = this.mSelectedTab;
        if (tab4 != null && (onTabSelectedListener2 = this.mOnTabSelectedListener) != null) {
            onTabSelectedListener2.onTabUnselected(tab4);
        }
        for (OnTabSelectedListener onTabSelectedListener5 : this.mOnTabSelectedListenerList) {
            onTabSelectedListener5.onTabUnselected(this.mSelectedTab);
        }
        this.mSelectedTab = tab;
        if (tab != null && (onTabSelectedListener = this.mOnTabSelectedListener) != null) {
            onTabSelectedListener.onTabSelected(tab);
        }
        for (OnTabSelectedListener onTabSelectedListener6 : this.mOnTabSelectedListenerList) {
            onTabSelectedListener6.onTabSelected(this.mSelectedTab);
        }
    }

    private int calculateScrollXForTab(int i, float f) {
        if (this.mMode == 0) {
            View childAt = this.mTabStrip.getChildAt(i);
            int i2 = i + 1;
            View childAt2 = i2 < this.mTabStrip.getChildCount() ? this.mTabStrip.getChildAt(i2) : null;
            return ((childAt.getLeft() + ((int) ((((childAt != null ? childAt.getWidth() : 0) + (childAt2 != null ? childAt2.getWidth() : 0)) * f) * 0.5f))) + (childAt.getWidth() / 2)) - (getWidth() / 2);
        }
        return 0;
    }

    private void applyModeAndGravity() {
        ViewCompat.setPaddingRelative(this.mTabStrip, this.mMode == 0 ? Math.max(0, this.mContentInsetStart - this.mTabPaddingStart) : 0, 0, 0, 0);
        int i = this.mMode;
        if (i == 0) {
            this.mTabStrip.setGravity(GravityCompat.START);
        } else if (i == 1) {
            this.mTabStrip.setGravity(1);
        }
        updateTabViews(true);
    }

    public void updateTabViews(boolean z) {
        for (int i = 0; i < this.mTabStrip.getChildCount(); i++) {
            View childAt = this.mTabStrip.getChildAt(i);
            childAt.setMinimumWidth(getTabMinWidth());
            updateTabViewLayoutParams((LinearLayout.LayoutParams) childAt.getLayoutParams());
            if (z) {
                childAt.requestLayout();
            }
        }
    }

    private int getDefaultHeight() {
        int size = this.mTabs.size();
        for (int i = 0; i < size; i++) {
            Tab tab = this.mTabs.get(i);
            if (tab != null && tab.getIcon() != null && !TextUtils.isEmpty(tab.getText())) {
                return 72;
            }
        }
        return 48;
    }

    private int getTabMinWidth() {
        if (this.mPagerAdapter != null && this.xTabDisplayNum != 0) {
            WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
            if (this.mPagerAdapter.getCount() == 1 || this.xTabDisplayNum == 1) {
                return windowManager.getDefaultDisplay().getWidth();
            }
            if (this.mPagerAdapter.getCount() < this.xTabDisplayNum) {
                return windowManager.getDefaultDisplay().getWidth() / this.mPagerAdapter.getCount();
            }
            return windowManager.getDefaultDisplay().getWidth() / this.xTabDisplayNum;
        } else if (this.xTabDisplayNum != 0) {
            return ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth() / this.xTabDisplayNum;
        } else {
            int i = this.mRequestedTabMinWidth;
            if (i != -1) {
                return i;
            }
            if (this.mMode == 0) {
                return this.mScrollableTabMinWidth;
            }
            return 0;
        }
    }

    @Override
    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return generateDefaultLayoutParams();
    }

    public static final class Tab {
        public static final int INVALID_POSITION = -1;
        private CharSequence mContentDesc;
        private View mCustomView;
        private Drawable mIcon;
        private XTabLayout mParent;
        private int mPosition;
        private Object mTag;
        private CharSequence mText;
        private TabView mView;

        public void reset() {
            this.mParent = null;
            this.mView = null;
            this.mTag = null;
            this.mIcon = null;
            this.mText = null;
            this.mContentDesc = null;
            this.mPosition = -1;
            this.mCustomView = null;
        }

        public CharSequence getContentDescription() {
            return this.mContentDesc;
        }

        public View getCustomView() {
            return this.mCustomView;
        }

        public Drawable getIcon() {
            return this.mIcon;
        }

        public int getPosition() {
            return this.mPosition;
        }

        public Object getTag() {
            return this.mTag;
        }

        public CharSequence getText() {
            return this.mText;
        }

        void setPosition(int i) {
            this.mPosition = i;
        }

        public Tab setTag(Object obj) {
            this.mTag = obj;
            return this;
        }

        private Tab() {
            this.mPosition = -1;
        }

        public int getTextWidth() {
            return this.mView.getTextWidth();
        }

        public Tab setCustomView(View view) {
            this.mCustomView = view;
            updateView();
            return this;
        }

        public Tab setCustomView(int i) {
            return setCustomView(LayoutInflater.from(this.mView.getContext()).inflate(i, (ViewGroup) this.mView, false));
        }

        public Tab setIcon(Drawable drawable) {
            this.mIcon = drawable;
            updateView();
            return this;
        }

        public Tab setIcon(int i) {
            if (this.mParent == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            return setIcon(AppCompatDrawableManager.get().getDrawable(this.mParent.getContext(), i));
        }

        public Tab setText(CharSequence charSequence) {
            this.mText = charSequence;
            updateView();
            return this;
        }

        public Tab setText(int i) {
            XTabLayout xTabLayout = this.mParent;
            if (xTabLayout == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            return setText(xTabLayout.getResources().getText(i));
        }

        public void select() {
            XTabLayout xTabLayout = this.mParent;
            if (xTabLayout == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            xTabLayout.selectTab(this);
        }

        public boolean isSelected() {
            XTabLayout xTabLayout = this.mParent;
            if (xTabLayout != null) {
                return xTabLayout.getSelectedTabPosition() == this.mPosition;
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        public Tab setContentDescription(int i) {
            XTabLayout xTabLayout = this.mParent;
            if (xTabLayout == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            return setContentDescription(xTabLayout.getResources().getText(i));
        }

        public Tab setContentDescription(CharSequence charSequence) {
            this.mContentDesc = charSequence;
            updateView();
            return this;
        }

        public void updateView() {
            TabView tabView = this.mView;
            if (tabView != null) {
                tabView.update();
            }
        }
    }

    public static class TabLayoutOnPageChangeListener implements ViewPager.OnPageChangeListener {
        private int mPreviousScrollState;
        private int mScrollState;
        private final WeakReference<XTabLayout> mTabLayoutRef;

        public void reset() {
            this.mScrollState = 0;
            this.mPreviousScrollState = 0;
        }

        @Override
        public void onPageScrollStateChanged(int i) {
            this.mPreviousScrollState = this.mScrollState;
            this.mScrollState = i;
        }

        public TabLayoutOnPageChangeListener(XTabLayout xTabLayout) {
            this.mTabLayoutRef = new WeakReference<>(xTabLayout);
        }

        @Override
        public void onPageScrolled(int i, float f, int i2) {
            XTabLayout xTabLayout = this.mTabLayoutRef.get();
            if (xTabLayout != null) {
                int i3 = this.mScrollState;
                boolean z = false;
                xTabLayout.setScrollPosition(i, f, i3 != 2 || this.mPreviousScrollState == 1, (i3 == 2 && this.mPreviousScrollState == 0) ? true : true);
            }
        }

        @Override
        public void onPageSelected(int i) {
            XTabLayout xTabLayout = this.mTabLayoutRef.get();
            if (xTabLayout == null || xTabLayout.getSelectedTabPosition() == i) {
                return;
            }
            int i2 = this.mScrollState;
            xTabLayout.selectTab(xTabLayout.getTabAt(i), i2 == 0 || (i2 == 2 && this.mPreviousScrollState == 0));
        }
    }

    public static class ViewPagerOnTabSelectedListener implements OnTabSelectedListener {
        private final ViewPager mViewPager;

        @Override
        public void onTabReselected(Tab tab) {
        }

        @Override
        public void onTabUnselected(Tab tab) {
        }

        public ViewPagerOnTabSelectedListener(ViewPager viewPager) {
            this.mViewPager = viewPager;
        }

        @Override
        public void onTabSelected(Tab tab) {
            this.mViewPager.setCurrentItem(tab.getPosition());
        }
    }

    public class TabView extends LinearLayout implements View.OnLongClickListener {
        private ImageView mCustomIconView;
        private TextView mCustomTextView;
        private View mCustomView;
        private int mDefaultMaxLines;
        private ImageView mIconView;
        private Tab mTab;
        private TextView mTextView;

        public Tab getTab() {
            return this.mTab;
        }

        public TabView(Context context) {
            super(context);
            this.mDefaultMaxLines = 1;
            ViewCompat.setPaddingRelative(this, mTabPaddingStart, mTabPaddingTop, mTabPaddingEnd, mTabPaddingBottom);
            if (mContentGravity == 1) {
                setGravity(3);
            } else {
                setGravity(17);
            }
            setOrientation(1);
            setClickable(true);
        }

        public String getText() {
            return this.mTextView.getText().toString();
        }

        public int getTextWidth() {
            if (TextUtils.isEmpty(this.mTextView.getText().toString())) {
                return 0;
            }
            Rect rect = new Rect();
            String charSequence = this.mTextView.getText().toString();
            this.mTextView.getPaint().getTextBounds(charSequence, 0, charSequence.length(), rect);
            return rect.width();
        }

        public int getTextHeight() {
            if (TextUtils.isEmpty(this.mTextView.getText().toString())) {
                return 0;
            }
            Rect rect = new Rect();
            String charSequence = this.mTextView.getText().toString();
            this.mTextView.getPaint().getTextBounds(charSequence, 0, charSequence.length(), rect);
            return rect.height();
        }

        @Override
        public boolean performClick() {
            boolean performClick = super.performClick();
            Tab tab = this.mTab;
            if (tab != null) {
                tab.select();
                return true;
            }
            return performClick;
        }

        @Override
        public void setSelected(boolean z) {
            boolean z2 = isSelected() != z;
            super.setSelected(z);
            if (!z) {
                if (xTabBackgroundColor != 0) {
                    setBackgroundColor(xTabBackgroundColor);
                }
                this.mTextView.setTextSize(0, mTabTextSize);
                if (xTabTextBold) {
                    this.mTextView.setTypeface(CustomFontUtils.getTypeface(getContext(), 1));
                } else {
                    this.mTextView.setTypeface(CustomFontUtils.getTypeface(getContext(), 0));
                }
                if (xTabTextBackground != null) {
                    this.mTextView.setBackground(xTabTextBackground);
                }
                View view = this.mCustomView;
                if (view != null && (view instanceof ViewGroup)) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    for (int i = 0; i < viewGroup.getChildCount(); i++) {
                        View childAt = viewGroup.getChildAt(i);
                        if (childAt instanceof TextView) {
                            if (xTabTextBold) {
                                ((TextView) childAt).setTypeface(CustomFontUtils.getTypeface(getContext(), 1));
                            } else {
                                ((TextView) childAt).setTypeface(CustomFontUtils.getTypeface(getContext(), 0));
                            }
                        }
                    }
                }
            }
            if (z2 && z) {
                if (xTabSelectedBackgroundColor != 0) {
                    setBackgroundColor(xTabSelectedBackgroundColor);
                }
                sendAccessibilityEvent(4);
                TextView textView = this.mTextView;
                if (textView != null) {
                    textView.setSelected(z);
                    if (mTabSelectedTextSize != 0.0f) {
                        this.mTextView.setTextSize(0, mTabSelectedTextSize);
                        if (xTabTextSelectedBold) {
                            this.mTextView.setTypeface(CustomFontUtils.getTypeface(getContext(), 1));
                        } else {
                            this.mTextView.setTypeface(CustomFontUtils.getTypeface(getContext(), 0));
                        }
                    }
                    if (xTabSelectedTextBackground != null) {
                        this.mTextView.setBackground(xTabSelectedTextBackground);
                    }
                }
                ImageView imageView = this.mIconView;
                if (imageView != null) {
                    imageView.setSelected(z);
                }
                View view2 = this.mCustomView;
                if (view2 == null || !(view2 instanceof ViewGroup)) {
                    return;
                }
                ViewGroup viewGroup2 = (ViewGroup) view2;
                for (int i2 = 0; i2 < viewGroup2.getChildCount(); i2++) {
                    View childAt2 = viewGroup2.getChildAt(i2);
                    if (childAt2 instanceof TextView) {
                        if (xTabTextSelectedBold) {
                            ((TextView) childAt2).setTypeface(CustomFontUtils.getTypeface(getContext(), 1));
                        } else {
                            ((TextView) childAt2).setTypeface(CustomFontUtils.getTypeface(getContext(), 0));
                        }
                    }
                }
            }
        }

        @Override
        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName(ActionBar.Tab.class.getName());
        }

        @Override
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName(ActionBar.Tab.class.getName());
        }

        @Override
        public void onMeasure(int i, int i2) {
            Layout layout;
            int size = View.MeasureSpec.getSize(i);
            int mode = View.MeasureSpec.getMode(i);
            int tabMaxWidth = getTabMaxWidth();
            if (tabMaxWidth > 0 && (mode == 0 || size > tabMaxWidth)) {
                i = View.MeasureSpec.makeMeasureSpec(mTabMaxWidth, Integer.MIN_VALUE);
            }
            super.onMeasure(i, i2);
            if (this.mTextView != null) {
                getResources();
                float f = mTabTextSize;
                int i3 = this.mDefaultMaxLines;
                ImageView imageView = this.mIconView;
                if (imageView == null || imageView.getVisibility() != 0) {
                    TextView textView = this.mTextView;
                    if (textView != null && textView.getLineCount() > 1) {
                        f = mTabTextMultiLineSize;
                    }
                } else {
                    i3 = 1;
                }
                float textSize = this.mTextView.getTextSize();
                int lineCount = this.mTextView.getLineCount();
                int maxLines = TextViewCompat.getMaxLines(this.mTextView);
                int i4 = (f > textSize ? 1 : (f == textSize ? 0 : -1));
                if (i4 != 0 || (maxLines >= 0 && i3 != maxLines)) {
                    if (mMode != 1 || i4 <= 0 || lineCount != 1 || ((layout = this.mTextView.getLayout()) != null && approximateLineWidth(layout, 0, f) <= layout.getWidth())) {
                        if (this.mTextView.isSelected() && mTabSelectedTextSize != 0.0f) {
                            this.mTextView.setTextSize(0, mTabSelectedTextSize);
                        } else {
                            this.mTextView.setTextSize(0, mTabTextSize);
                        }
                        this.mTextView.setMaxLines(i3);
                        super.onMeasure(i, i2);
                    }
                }
            }
        }

        public void reset() {
            setTab(null);
            setSelected(false);
        }

        final void update() {
            Tab tab = this.mTab;
            View customView = tab != null ? tab.getCustomView() : null;
            if (customView != null) {
                ViewParent parent = customView.getParent();
                if (parent != this) {
                    if (parent != null) {
                        ((ViewGroup) parent).removeView(customView);
                    }
                    addView(customView);
                }
                this.mCustomView = customView;
                TextView textView = this.mTextView;
                if (textView != null) {
                    textView.setVisibility(View.GONE);
                }
                ImageView imageView = this.mIconView;
                if (imageView != null) {
                    imageView.setVisibility(View.GONE);
                    this.mIconView.setImageDrawable(null);
                }
                TextView textView2 = (TextView) customView.findViewById(16908308);
                this.mCustomTextView = textView2;
                if (textView2 != null) {
                    this.mDefaultMaxLines = TextViewCompat.getMaxLines(textView2);
                }
                this.mCustomIconView = (ImageView) customView.findViewById(16908294);
            } else {
                View view = this.mCustomView;
                if (view != null) {
                    removeView(view);
                    this.mCustomView = null;
                }
                this.mCustomTextView = null;
                this.mCustomIconView = null;
            }
            if (this.mCustomView != null) {
                TextView textView3 = this.mCustomTextView;
                if (textView3 == null && this.mCustomIconView == null) {
                    return;
                }
                updateTextAndIcon(textView3, this.mCustomIconView);
                return;
            }
            if (this.mIconView == null) {
                ImageView imageView2 = (ImageView) LayoutInflater.from(getContext()).inflate(com.tronlinkpro.wallet.R.layout.design_layout_tab_icon, (ViewGroup) this, false);
                addView(imageView2, 0);
                this.mIconView = imageView2;
            }
            if (this.mTextView == null) {
                TextView textView4 = (TextView) LayoutInflater.from(getContext()).inflate(com.tronlinkpro.wallet.R.layout.design_layout_tab_text, (ViewGroup) this, false);
                addView(textView4);
                this.mTextView = textView4;
            }
            if (mTabTextColors != null) {
                this.mTextView.setTextColor(mTabTextColors);
            }
            updateTextAndIcon(this.mTextView, this.mIconView);
        }

        private void updateTextAndIcon(TextView textView, ImageView imageView) {
            Tab tab = this.mTab;
            Drawable icon = tab != null ? tab.getIcon() : null;
            Tab tab2 = this.mTab;
            CharSequence text = tab2 != null ? tab2.getText() : null;
            Tab tab3 = this.mTab;
            CharSequence contentDescription = tab3 != null ? tab3.getContentDescription() : null;
            if (imageView != null) {
                if (icon != null) {
                    imageView.setImageDrawable(icon);
                    imageView.setVisibility(View.VISIBLE);
                    setVisibility(View.VISIBLE);
                } else {
                    imageView.setVisibility(View.GONE);
                    imageView.setImageDrawable(null);
                }
                imageView.setContentDescription(contentDescription);
            }
            boolean z = !TextUtils.isEmpty(text);
            if (textView != null) {
                if (z) {
                    textView.setAllCaps(xTabTextAllCaps);
                    textView.setText(text);
                    textView.setVisibility(View.VISIBLE);
                    setVisibility(View.VISIBLE);
                } else {
                    textView.setVisibility(View.GONE);
                    textView.setText((CharSequence) null);
                }
                textView.setContentDescription(contentDescription);
            }
            if (imageView != null) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
                int dpToPx = (z && imageView.getVisibility() == 0) ? dpToPx(8) : 0;
                if (dpToPx != marginLayoutParams.bottomMargin) {
                    marginLayoutParams.bottomMargin = dpToPx;
                    imageView.requestLayout();
                }
            }
            if (!z && !TextUtils.isEmpty(contentDescription)) {
                setOnLongClickListener(this);
                return;
            }
            setOnLongClickListener(null);
            setLongClickable(false);
        }

        @Override
        public boolean onLongClick(View view) {
            int[] iArr = new int[2];
            getLocationOnScreen(iArr);
            Context context = getContext();
            int width = getWidth();
            int height = getHeight();
            int i = context.getResources().getDisplayMetrics().widthPixels;
            Toast makeText = Toast.makeText(context, this.mTab.getContentDescription(), 0);
            makeText.setGravity(49, (iArr[0] + (width / 2)) - (i / 2), height);
            makeText.show();
            return true;
        }

        public void setTab(Tab tab) {
            if (tab != this.mTab) {
                this.mTab = tab;
                update();
            }
        }

        private float approximateLineWidth(Layout layout, int i, float f) {
            return layout.getLineWidth(i) * (f / layout.getPaint().getTextSize());
        }
    }

    public class SlidingTabStrip extends LinearLayout {
        private ValueAnimatorCompat mIndicatorAnimator;
        private int mIndicatorLeft;
        private int mIndicatorRight;
        private int mSelectedIndicatorHeight;
        private final Paint mSelectedIndicatorPaint;
        private int mSelectedIndicatorWidth;
        private int mSelectedPosition;
        private float mSelectionOffset;

        float getIndicatorPosition() {
            return this.mSelectedPosition + this.mSelectionOffset;
        }

        public int getmSelectedIndicatorWidth() {
            return this.mSelectedIndicatorWidth;
        }

        SlidingTabStrip(Context context) {
            super(context);
            this.mSelectedPosition = -1;
            this.mIndicatorLeft = -1;
            this.mIndicatorRight = -1;
            setWillNotDraw(false);
            this.mSelectedIndicatorPaint = new Paint();
        }

        void setSelectedIndicatorColor(int i) {
            if (this.mSelectedIndicatorPaint.getColor() != i) {
                this.mSelectedIndicatorPaint.setColor(i);
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        void setSelectedIndicatorHeight(int i) {
            if (this.mSelectedIndicatorHeight != i) {
                this.mSelectedIndicatorHeight = i;
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        void setmSelectedIndicatorWidth(int i) {
            if (this.mSelectedIndicatorWidth != i) {
                this.mSelectedIndicatorWidth = i;
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        boolean childrenNeedLayout() {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (getChildAt(i).getWidth() <= 0) {
                    return true;
                }
            }
            return false;
        }

        void setIndicatorPositionFromTabPosition(int i, float f) {
            ValueAnimatorCompat valueAnimatorCompat = this.mIndicatorAnimator;
            if (valueAnimatorCompat != null && valueAnimatorCompat.isRunning()) {
                this.mIndicatorAnimator.cancel();
            }
            this.mSelectedPosition = i;
            this.mSelectionOffset = f;
            updateIndicatorPosition();
        }

        @Override
        protected void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            if (View.MeasureSpec.getMode(i) == MeasureSpec.AT_MOST && mMode == 1 && mTabGravity == 1) {
                int childCount = getChildCount();
                int i3 = 0;
                for (int i4 = 0; i4 < childCount; i4++) {
                    View childAt = getChildAt(i4);
                    if (childAt.getVisibility() == 0) {
                        i3 = Math.max(i3, childAt.getMeasuredWidth());
                    }
                }
                if (i3 <= 0) {
                    return;
                }
                if (i3 * childCount <= getMeasuredWidth() - (dpToPx(16) * 2)) {
                    boolean z = false;
                    for (int i5 = 0; i5 < childCount; i5++) {
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getChildAt(i5).getLayoutParams();
                        if (layoutParams.width != i3 || layoutParams.weight != 0.0f) {
                            layoutParams.width = i3;
                            layoutParams.weight = 0.0f;
                            z = true;
                        }
                    }
                    if (!z) {
                        return;
                    }
                } else {
                    mTabGravity = 0;
                    updateTabViews(false);
                }
                super.onMeasure(i, i2);
            }
        }

        @Override
        protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            ValueAnimatorCompat valueAnimatorCompat = this.mIndicatorAnimator;
            if (valueAnimatorCompat != null && valueAnimatorCompat.isRunning()) {
                this.mIndicatorAnimator.cancel();
                animateIndicatorToPosition(this.mSelectedPosition, Math.round((1.0f - this.mIndicatorAnimator.getAnimatedFraction()) * ((float) this.mIndicatorAnimator.getDuration())));
                return;
            }
            updateIndicatorPosition();
        }

        private void updateIndicatorPosition() {
            int i;
            int i2;
            int i3;
            int i4;
            View childAt = getChildAt(this.mSelectedPosition);
            if (childAt == null || childAt.getWidth() <= 0) {
                i = -1;
                i2 = -1;
            } else {
                i2 = childAt.getLeft();
                i = childAt.getRight();
                if (this.mSelectedIndicatorWidth == 0 && !xTabDividerWidthWidthText) {
                    this.mSelectedIndicatorWidth = 16843039;
                }
                int i5 = this.mSelectedIndicatorWidth;
                if (i5 == 0 || (i4 = this.mIndicatorRight - this.mIndicatorLeft) <= i5) {
                    i3 = 0;
                } else {
                    i3 = (i4 - i5) / 2;
                    i2 += i3;
                    i -= i3;
                }
                if (this.mSelectionOffset > 0.0f && this.mSelectedPosition < getChildCount() - 1) {
                    View childAt2 = getChildAt(this.mSelectedPosition + 1);
                    int left = childAt2.getLeft() + i3;
                    int right = childAt2.getRight() - i3;
                    float f = this.mSelectionOffset;
                    i2 = (int) ((left * f) + ((1.0f - f) * i2));
                    i = (int) ((right * f) + ((1.0f - f) * i));
                }
            }
            setIndicatorPosition(i2, i);
        }

        public void setIndicatorPosition(int i, int i2) {
            int i3 = i + mTabPaddingStart;
            int i4 = i2 - mTabPaddingEnd;
            if (i3 == this.mIndicatorLeft && i4 == this.mIndicatorRight) {
                return;
            }
            this.mIndicatorLeft = i3;
            this.mIndicatorRight = i4;
            ViewCompat.postInvalidateOnAnimation(this);
        }

        void animateIndicatorToPosition(final int i, int i2) {
            final int i3;
            final int i4;
            ValueAnimatorCompat valueAnimatorCompat = this.mIndicatorAnimator;
            if (valueAnimatorCompat != null && valueAnimatorCompat.isRunning()) {
                this.mIndicatorAnimator.cancel();
            }
            boolean z = ViewCompat.getLayoutDirection(this) == 1;
            View childAt = getChildAt(i);
            if (childAt == null) {
                updateIndicatorPosition();
                return;
            }
            final int left = childAt.getLeft();
            final int right = childAt.getRight();
            if (Math.abs(i - this.mSelectedPosition) <= 1) {
                i3 = this.mIndicatorLeft;
                i4 = this.mIndicatorRight;
            } else {
                int dpToPx = dpToPx(24);
                i3 = (i >= this.mSelectedPosition ? !z : z) ? left - dpToPx : dpToPx + right;
                i4 = i3;
            }
            if (i3 == left && i4 == right) {
                return;
            }
            ValueAnimatorCompat createAnimator = ViewUtils.createAnimator();
            this.mIndicatorAnimator = createAnimator;
            createAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            createAnimator.setDuration(i2);
            createAnimator.setFloatValues(0.0f, 1.0f);
            createAnimator.setUpdateListener(new ValueAnimatorCompat.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimatorCompat valueAnimatorCompat2) {
                    float animatedFraction = valueAnimatorCompat2.getAnimatedFraction();
                    SlidingTabStrip.this.setIndicatorPosition(AnimationUtils.lerp(i3, left, animatedFraction), AnimationUtils.lerp(i4, right, animatedFraction));
                }
            });
            createAnimator.setListener(new ValueAnimatorCompat.AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(ValueAnimatorCompat valueAnimatorCompat2) {
                    SlidingTabStrip.this.mSelectedPosition = i;
                    SlidingTabStrip.this.mSelectionOffset = 0.0f;
                }
            });
            createAnimator.start();
        }

        @Override
        public void draw(Canvas canvas) {
            int i;
            super.draw(canvas);
            int i2 = this.mIndicatorLeft;
            if (i2 < 0 || (i = this.mIndicatorRight) <= i2) {
                return;
            }
            int i3 = i - i2;
            if (this.mSelectedIndicatorWidth != 0 && !xTabDividerWidthWidthText) {
                int i4 = this.mSelectedIndicatorWidth;
                if (i3 > i4) {
                    this.mIndicatorLeft += (i3 - i4) / 2;
                    this.mIndicatorRight -= (i3 - i4) / 2;
                }
            } else if (i3 > mSelectedTab.getTextWidth()) {
                if (mContentGravity == 1) {
                    int i5 = this.mIndicatorLeft + 2;
                    this.mIndicatorLeft = i5;
                    this.mIndicatorRight = i5 + mSelectedTab.getTextWidth();
                } else if (mSelectedTab.mCustomView == null) {
                    this.mIndicatorLeft += (i3 - mSelectedTab.getTextWidth()) / 2;
                    this.mIndicatorRight -= (i3 - mSelectedTab.getTextWidth()) / 2;
                } else {
                    int width = (i3 - mSelectedTab.mCustomView.getWidth()) / 2;
                    this.mIndicatorLeft += width;
                    this.mIndicatorRight -= width;
                }
            }
            int height = getHeight();
            int i6 = this.mSelectedIndicatorHeight;
            canvas.drawArc(new RectF(this.mIndicatorLeft, height - i6, this.mIndicatorLeft + i6, getHeight()), 90.0f, 180.0f, false, this.mSelectedIndicatorPaint);
            canvas.drawArc(new RectF(this.mIndicatorRight - this.mSelectedIndicatorHeight, getHeight() - this.mSelectedIndicatorHeight, this.mIndicatorRight, getHeight()), -90.0f, 180.0f, false, this.mSelectedIndicatorPaint);
            float f = this.mIndicatorLeft + (this.mSelectedIndicatorHeight / 2);
            int height2 = getHeight();
            int i7 = this.mSelectedIndicatorHeight;
            canvas.drawRect(f, height2 - i7, this.mIndicatorRight - (i7 / 2), getHeight(), this.mSelectedIndicatorPaint);
        }
    }

    public class PagerAdapterObserver extends DataSetObserver {
        private PagerAdapterObserver() {
        }

        @Override
        public void onChanged() {
            populateFromPagerAdapter();
        }

        @Override
        public void onInvalidated() {
            populateFromPagerAdapter();
        }
    }
}
