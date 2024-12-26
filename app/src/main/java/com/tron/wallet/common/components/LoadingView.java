package com.tron.wallet.common.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.RotateAnimation;
import androidx.appcompat.widget.AppCompatImageView;
import com.tronlinkpro.wallet.R;
public class LoadingView extends AppCompatImageView {
    private static final int DEFAULT_TIME_OUT = 5000;
    private RotateAnimation rotateAnimation;
    private State state;

    public enum State {
        LOADING,
        FAIL,
        GONE
    }

    public State getState() {
        return this.state;
    }

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LoadingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initAnimation();
        updateState(State.LOADING);
    }

    private void initAnimation() {
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        this.rotateAnimation = rotateAnimation;
        rotateAnimation.setDuration(1000L);
        this.rotateAnimation.setRepeatCount(-1);
        this.rotateAnimation.setRepeatMode(1);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public static class fun1 {
        static final int[] $SwitchMap$com$tron$wallet$common$components$LoadingView$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$com$tron$wallet$common$components$LoadingView$State = iArr;
            try {
                iArr[State.GONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tron$wallet$common$components$LoadingView$State[State.LOADING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tron$wallet$common$components$LoadingView$State[State.FAIL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public void updateState(State state) {
        this.state = state;
        int i = fun1.$SwitchMap$com$tron$wallet$common$components$LoadingView$State[state.ordinal()];
        if (i == 1) {
            setVisibility(View.GONE);
        } else if (i != 2) {
            if (i != 3) {
                return;
            }
            if (getVisibility() != 0) {
                setVisibility(View.VISIBLE);
            }
            setImageResource(R.mipmap.ic_retry_white);
            if (getAnimation() != null) {
                getAnimation().cancel();
            }
        } else {
            if (getVisibility() != 0) {
                setVisibility(View.VISIBLE);
            }
            if (getDrawable() == null) {
                setImageResource(R.mipmap.ic_loading_asset_header);
            }
            RotateAnimation rotateAnimation = this.rotateAnimation;
            if (rotateAnimation != null) {
                startAnimation(rotateAnimation);
            }
        }
    }
}
