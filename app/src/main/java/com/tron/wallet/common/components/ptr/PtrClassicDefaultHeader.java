package com.tron.wallet.common.components.ptr;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.tron.wallet.R;
import com.tron.wallet.common.components.ptr.indicator.PtrIndicator;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
public class PtrClassicDefaultHeader extends FrameLayout implements PtrUIHandler {
    private static final String KEY_SharedPreferences = "cube_ptr_classic_last_update";
    private static SimpleDateFormat sDataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    protected RotateAnimation mFlipAnimation;
    private TextView mLastUpdateTextView;
    private long mLastUpdateTime;
    private String mLastUpdateTimeKey;
    private LastUpdateTimeUpdater mLastUpdateTimeUpdater;
    private ImageView mProgressBar;
    protected RotateAnimation mReverseFlipAnimation;
    private int mRotateAniTime;
    private View mRotateView;
    private boolean mShouldShowLastUpdate;
    protected TextView mTitleTextView;

    public PtrClassicDefaultHeader(Context context) {
        super(context);
        this.mRotateAniTime = 150;
        this.mLastUpdateTime = -1L;
        this.mLastUpdateTimeUpdater = new LastUpdateTimeUpdater();
        initViews(null);
    }

    public PtrClassicDefaultHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRotateAniTime = 150;
        this.mLastUpdateTime = -1L;
        this.mLastUpdateTimeUpdater = new LastUpdateTimeUpdater();
        initViews(attributeSet);
    }

    public PtrClassicDefaultHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mRotateAniTime = 150;
        this.mLastUpdateTime = -1L;
        this.mLastUpdateTimeUpdater = new LastUpdateTimeUpdater();
        initViews(attributeSet);
    }

    protected void initViews(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.PtrClassicHeader, 0, 0);
        if (obtainStyledAttributes != null) {
            this.mRotateAniTime = obtainStyledAttributes.getInt(0, this.mRotateAniTime);
        }
        buildAnimation();
        View inflate = LayoutInflater.from(getContext()).inflate(com.tronlinkpro.wallet.R.layout.cube_ptr_classic_default_header, this);
        this.mRotateView = inflate.findViewById(com.tronlinkpro.wallet.R.id.ptr_classic_header_rotate_view);
        this.mTitleTextView = (TextView) inflate.findViewById(com.tronlinkpro.wallet.R.id.ptr_classic_header_rotate_view_header_title);
        this.mLastUpdateTextView = (TextView) inflate.findViewById(com.tronlinkpro.wallet.R.id.ptr_classic_header_rotate_view_header_last_update);
        this.mProgressBar = (ImageView) inflate.findViewById(com.tronlinkpro.wallet.R.id.ptr_classic_header_rotate_view_progressbar);
        resetView();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        LastUpdateTimeUpdater lastUpdateTimeUpdater = this.mLastUpdateTimeUpdater;
        if (lastUpdateTimeUpdater != null) {
            lastUpdateTimeUpdater.stop();
        }
    }

    public void setRotateAniTime(int i) {
        if (i == this.mRotateAniTime || i == 0) {
            return;
        }
        this.mRotateAniTime = i;
        buildAnimation();
    }

    public void setLastUpdateTimeKey(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mLastUpdateTimeKey = str;
    }

    public void setLastUpdateTimeRelateObject(Object obj) {
        setLastUpdateTimeKey(obj.getClass().getName() + "header");
    }

    protected void buildAnimation() {
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, -180.0f, 1, 0.5f, 1, 0.5f);
        this.mFlipAnimation = rotateAnimation;
        rotateAnimation.setInterpolator(new LinearInterpolator());
        this.mFlipAnimation.setDuration(this.mRotateAniTime);
        this.mFlipAnimation.setFillAfter(true);
        RotateAnimation rotateAnimation2 = new RotateAnimation(-180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.mReverseFlipAnimation = rotateAnimation2;
        rotateAnimation2.setInterpolator(new LinearInterpolator());
        this.mReverseFlipAnimation.setDuration(this.mRotateAniTime);
        this.mReverseFlipAnimation.setFillAfter(true);
    }

    private void resetView() {
        hideRotateView();
        stopAnimal();
    }

    private void startAnimal() {
        ((AnimationDrawable) this.mProgressBar.getDrawable()).start();
        this.mProgressBar.setVisibility(View.VISIBLE);
    }

    private void stopAnimal() {
        ((AnimationDrawable) this.mProgressBar.getDrawable()).selectDrawable(0);
        ((AnimationDrawable) this.mProgressBar.getDrawable()).stop();
        this.mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideRotateView() {
        this.mRotateView.clearAnimation();
        this.mRotateView.setVisibility(View.GONE);
    }

    @Override
    public void onUIReset(PtrFrameLayout ptrFrameLayout) {
        resetView();
        this.mShouldShowLastUpdate = true;
        tryUpdateLastUpdateTime();
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout ptrFrameLayout) {
        this.mShouldShowLastUpdate = true;
        tryUpdateLastUpdateTime();
        this.mLastUpdateTimeUpdater.start();
        stopAnimal();
        this.mRotateView.setVisibility(View.GONE);
        this.mTitleTextView.setVisibility(View.VISIBLE);
        if (ptrFrameLayout.isPullToRefresh()) {
            this.mTitleTextView.setText(getResources().getString(com.tronlinkpro.wallet.R.string.cube_ptr_pull_down_to_refresh));
        } else {
            this.mTitleTextView.setText(getResources().getString(com.tronlinkpro.wallet.R.string.cube_ptr_pull_down));
        }
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout ptrFrameLayout) {
        this.mShouldShowLastUpdate = false;
        hideRotateView();
        startAnimal();
        this.mTitleTextView.setVisibility(View.VISIBLE);
        this.mTitleTextView.setText(com.tronlinkpro.wallet.R.string.cube_ptr_refreshing);
        tryUpdateLastUpdateTime();
        this.mLastUpdateTimeUpdater.stop();
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout ptrFrameLayout, boolean z) {
        if (z) {
            hideRotateView();
            stopAnimal();
            this.mTitleTextView.setVisibility(View.VISIBLE);
            this.mTitleTextView.setText(getResources().getString(com.tronlinkpro.wallet.R.string.cube_ptr_refresh_complete));
            SharedPreferences sharedPreferences = getContext().getSharedPreferences(KEY_SharedPreferences, 0);
            if (TextUtils.isEmpty(this.mLastUpdateTimeKey)) {
                return;
            }
            this.mLastUpdateTime = new Date().getTime();
            sharedPreferences.edit().putLong(this.mLastUpdateTimeKey, this.mLastUpdateTime).commit();
        }
    }

    public void tryUpdateLastUpdateTime() {
        if (TextUtils.isEmpty(this.mLastUpdateTimeKey) || !this.mShouldShowLastUpdate) {
            this.mLastUpdateTextView.setVisibility(View.GONE);
            return;
        }
        String lastUpdateTime = getLastUpdateTime();
        if (TextUtils.isEmpty(lastUpdateTime)) {
            this.mLastUpdateTextView.setVisibility(View.GONE);
            return;
        }
        this.mLastUpdateTextView.setVisibility(View.VISIBLE);
        this.mLastUpdateTextView.setText(lastUpdateTime);
    }

    private String getLastUpdateTime() {
        if (this.mLastUpdateTime == -1 && !TextUtils.isEmpty(this.mLastUpdateTimeKey)) {
            this.mLastUpdateTime = getContext().getSharedPreferences(KEY_SharedPreferences, 0).getLong(this.mLastUpdateTimeKey, -1L);
        }
        if (this.mLastUpdateTime == -1) {
            return null;
        }
        long time = new Date().getTime() - this.mLastUpdateTime;
        int i = (int) (time / 1000);
        if (time >= 0 && i > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(getContext().getString(com.tronlinkpro.wallet.R.string.cube_ptr_last_update));
            if (i < 60) {
                sb.append(i + getContext().getString(com.tronlinkpro.wallet.R.string.cube_ptr_seconds_ago));
            } else {
                int i2 = i / 60;
                if (i2 > 60) {
                    int i3 = i2 / 60;
                    if (i3 > 24) {
                        sb.append(sDataFormat.format(new Date(this.mLastUpdateTime)));
                    } else {
                        sb.append(i3 + getContext().getString(com.tronlinkpro.wallet.R.string.cube_ptr_hours_ago));
                    }
                } else {
                    sb.append(i2 + getContext().getString(com.tronlinkpro.wallet.R.string.cube_ptr_minutes_ago));
                }
            }
            return sb.toString();
        }
        return null;
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout ptrFrameLayout, boolean z, byte b, PtrIndicator ptrIndicator) {
        int offsetToRefresh = ptrFrameLayout.getOffsetToRefresh();
        int currentPosY = ptrIndicator.getCurrentPosY();
        int lastPosY = ptrIndicator.getLastPosY();
        if (currentPosY < offsetToRefresh && lastPosY >= offsetToRefresh) {
            if (z && b == 2) {
                crossRotateLineFromBottomUnderTouch(ptrFrameLayout);
                View view = this.mRotateView;
                if (view != null) {
                    view.clearAnimation();
                    this.mRotateView.startAnimation(this.mReverseFlipAnimation);
                }
            }
        } else if (currentPosY <= offsetToRefresh || lastPosY > offsetToRefresh || !z || b != 2) {
        } else {
            crossRotateLineFromTopUnderTouch(ptrFrameLayout);
            View view2 = this.mRotateView;
            if (view2 != null) {
                view2.clearAnimation();
                this.mRotateView.startAnimation(this.mFlipAnimation);
            }
        }
    }

    private void crossRotateLineFromTopUnderTouch(PtrFrameLayout ptrFrameLayout) {
        if (ptrFrameLayout.isPullToRefresh()) {
            return;
        }
        this.mTitleTextView.setVisibility(View.VISIBLE);
        this.mTitleTextView.setText(com.tronlinkpro.wallet.R.string.cube_ptr_release_to_refresh);
    }

    private void crossRotateLineFromBottomUnderTouch(PtrFrameLayout ptrFrameLayout) {
        this.mTitleTextView.setVisibility(View.VISIBLE);
        if (ptrFrameLayout.isPullToRefresh()) {
            this.mTitleTextView.setText(getResources().getString(com.tronlinkpro.wallet.R.string.cube_ptr_pull_down_to_refresh));
        } else {
            this.mTitleTextView.setText(getResources().getString(com.tronlinkpro.wallet.R.string.cube_ptr_pull_down));
        }
    }

    private class LastUpdateTimeUpdater implements Runnable {
        private boolean mRunning;

        private LastUpdateTimeUpdater() {
            this.mRunning = false;
        }

        public void start() {
            if (TextUtils.isEmpty(mLastUpdateTimeKey)) {
                return;
            }
            this.mRunning = true;
            run();
        }

        public void stop() {
            this.mRunning = false;
            removeCallbacks(this);
        }

        @Override
        public void run() {
            tryUpdateLastUpdateTime();
            if (this.mRunning) {
                postDelayed(this, 1000L);
            }
        }
    }
}
