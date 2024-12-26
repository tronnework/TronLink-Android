package com.tron.wallet.common.components.ptr.iptr;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.tron.wallet.R;
import com.tron.wallet.common.components.ptr.PtrFrameLayout;
import com.tron.wallet.common.components.ptr.PtrUIHandler;
import com.tron.wallet.common.components.ptr.indicator.PtrIndicator;
import com.tron.wallet.common.utils.UIUtils;
public class HomePtrClassicHeader extends FrameLayout implements PtrUIHandler {
    private RotateAnimation an;
    private View mProgressBar;
    private int mRotateAniTime;
    private ImageView mRotateView;
    private boolean mShouldShowLastUpdate;
    private RotateAnimation shunAn;
    private onStatusListener statusListener;
    private onUIChangeListener uiChangeListener;

    public interface onStatusListener {
        void onUIRefreshBegin();

        void onUIRefreshComplete();

        void onUIRefreshPrepare();

        void onUIReset();
    }

    public interface onUIChangeListener {
        void currentPos(int i);
    }

    public void setStatusListener(onStatusListener onstatuslistener) {
        this.statusListener = onstatuslistener;
    }

    public void setUiChangeListener(onUIChangeListener onuichangelistener) {
        this.uiChangeListener = onuichangelistener;
    }

    public HomePtrClassicHeader(Context context) {
        super(context);
        this.mRotateAniTime = 150;
        initViews(null);
    }

    public HomePtrClassicHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRotateAniTime = 150;
        initViews(attributeSet);
    }

    public HomePtrClassicHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mRotateAniTime = 150;
        initViews(attributeSet);
    }

    protected void initViews(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.PtrClassicHeader, 0, 0);
        if (obtainStyledAttributes != null) {
            this.mRotateAniTime = obtainStyledAttributes.getInt(0, this.mRotateAniTime);
        }
        buildAnimation();
        ImageView imageView = (ImageView) LayoutInflater.from(getContext()).inflate(com.tronlinkpro.wallet.R.layout.cube_ptr_classic_home_header, this).findViewById(com.tronlinkpro.wallet.R.id.iv_loading);
        this.mRotateView = imageView;
        imageView.setVisibility(View.INVISIBLE);
        int statusBarHeight = UIUtils.getStatusBarHeight();
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mRotateView.getLayoutParams();
        layoutParams.setMargins(0, statusBarHeight + UIUtils.dip2px(20.0f), 0, 0);
        this.mRotateView.setLayoutParams(layoutParams);
        resetView();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        resetView();
    }

    public void setRotateAniTime(int i) {
        if (i == this.mRotateAniTime || i == 0) {
            return;
        }
        this.mRotateAniTime = i;
        buildAnimation();
    }

    private void buildAnimation() {
        this.an = showLoadingView();
        this.shunAn = showLoadingViewClockwise();
    }

    private void resetView() {
        this.mRotateView.setVisibility(View.INVISIBLE);
    }

    private void hideRotateView() {
        stopAnimal();
    }

    @Override
    public void onUIReset(PtrFrameLayout ptrFrameLayout) {
        resetView();
        this.mShouldShowLastUpdate = true;
        onStatusListener onstatuslistener = this.statusListener;
        if (onstatuslistener != null) {
            onstatuslistener.onUIReset();
        }
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout ptrFrameLayout) {
        this.mShouldShowLastUpdate = true;
        this.mRotateView.setVisibility(View.VISIBLE);
        if (this.mRotateView != null) {
            stopAnimal();
        }
        onStatusListener onstatuslistener = this.statusListener;
        if (onstatuslistener != null) {
            onstatuslistener.onUIRefreshPrepare();
        }
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout ptrFrameLayout) {
        this.mShouldShowLastUpdate = false;
        this.mRotateView.setVisibility(View.VISIBLE);
        if (this.mRotateView != null) {
            startAnimal();
        }
        onStatusListener onstatuslistener = this.statusListener;
        if (onstatuslistener != null) {
            onstatuslistener.onUIRefreshBegin();
        }
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout ptrFrameLayout, boolean z) {
        if (z) {
            hideRotateView();
            onStatusListener onstatuslistener = this.statusListener;
            if (onstatuslistener != null) {
                onstatuslistener.onUIRefreshComplete();
            }
        }
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout ptrFrameLayout, boolean z, byte b, PtrIndicator ptrIndicator) {
        int offsetToRefresh = ptrFrameLayout.getOffsetToRefresh();
        int currentPosY = ptrIndicator.getCurrentPosY();
        int lastPosY = ptrIndicator.getLastPosY();
        if (currentPosY >= offsetToRefresh || lastPosY < offsetToRefresh) {
            if (currentPosY > offsetToRefresh && lastPosY <= offsetToRefresh && z && b == 2 && this.mRotateView != null) {
                stopAnimal();
            }
        } else if (z && b == 2 && this.mRotateView != null) {
            startAnimal();
        }
        onUIChangeListener onuichangelistener = this.uiChangeListener;
        if (onuichangelistener != null) {
            onuichangelistener.currentPos(currentPosY);
        }
    }

    private void startAnimal() {
        ImageView imageView = this.mRotateView;
        if (imageView != null) {
            ((AnimationDrawable) imageView.getDrawable()).start();
            this.mRotateView.setVisibility(View.VISIBLE);
        }
    }

    private void stopAnimal() {
        ImageView imageView = this.mRotateView;
        if (imageView != null) {
            ((AnimationDrawable) imageView.getDrawable()).selectDrawable(0);
            ((AnimationDrawable) this.mRotateView.getDrawable()).stop();
            this.mRotateView.setVisibility(View.VISIBLE);
        }
    }

    public void setAnimalDraw(boolean z) {
        if (z) {
            this.mRotateView.setImageResource(com.tronlinkpro.wallet.R.drawable.loading_animlist);
        } else {
            this.mRotateView.setImageResource(com.tronlinkpro.wallet.R.drawable.loading_white_animlist);
        }
    }

    private RotateAnimation showLoadingView() {
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, -359.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setDuration(1500L);
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }
        });
        return rotateAnimation;
    }

    private RotateAnimation showLoadingViewClockwise() {
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 359.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setDuration(666L);
        return rotateAnimation;
    }
}
