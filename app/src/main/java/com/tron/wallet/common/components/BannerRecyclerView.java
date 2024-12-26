package com.tron.wallet.common.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.components.ptr.PtrFrameLayout;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayout;
public class BannerRecyclerView extends FrameLayout implements LifecycleObserver {
    private static final int PERIOD = 6000;
    private final ViewPager2 bannerViewPager;
    private boolean isDragging;
    private boolean isPlay;
    private final Runnable looper;

    public boolean isDragging() {
        return this.isDragging;
    }

    public BannerRecyclerView(Context context) {
        this(context, null);
    }

    public BannerRecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BannerRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isPlay = false;
        ViewPager2 viewPager2 = new ViewPager2(getContext());
        this.bannerViewPager = viewPager2;
        addView(viewPager2, new FrameLayout.LayoutParams(-1, -1));
        this.looper = new Runnable() {
            @Override
            public void run() {
                bannerViewPager.setCurrentItem(bannerViewPager.getCurrentItem() + 1);
                postDelayed(this, 6000L);
                LogUtils.w("BannerView", "looper called");
            }
        };
        viewPager2.getChildAt(0).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                boolean lambda$new$0;
                lambda$new$0 = lambda$new$0(view, motionEvent);
                return lambda$new$0;
            }
        });
    }

    public boolean lambda$new$0(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 || motionEvent.getAction() == 2) {
            this.isDragging = true;
            stopRolling();
        } else if (motionEvent.getAction() == 3 || motionEvent.getAction() == 1) {
            this.isDragging = false;
            startRolling();
        }
        return false;
    }

    public void setOnPageChangeCallback(ViewPager2.OnPageChangeCallback onPageChangeCallback) {
        this.bannerViewPager.registerOnPageChangeCallback(onPageChangeCallback);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.bannerViewPager.setAdapter(adapter);
    }

    public void attachToPtrFrameLayout(PtrFrameLayout ptrFrameLayout) {
        ptrFrameLayout.disableWhenHorizontalMove(true);
        if (ptrFrameLayout instanceof PtrHTFrameLayout) {
            ((PtrHTFrameLayout) ptrFrameLayout).setViewPager(this.bannerViewPager);
        }
    }

    public void startRolling() {
        if (this.isPlay) {
            return;
        }
        this.isPlay = true;
        postDelayed(this.looper, 6000L);
    }

    public void stopRolling() {
        if (this.isPlay) {
            this.isPlay = false;
            removeCallbacks(this.looper);
            LogUtils.w("BannerView", "stop auto rolling");
        }
    }

    @Override
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            startRolling();
        } else {
            stopRolling();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        startRolling();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        stopRolling();
    }
}
