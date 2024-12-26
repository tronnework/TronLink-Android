package com.tron.wallet.business.confirm.core.pending;

import android.view.View;
import android.view.animation.RotateAnimation;
public class AnimaUtil {
    public static void animLoadingView(View view) {
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(800L);
        rotateAnimation.setRepeatCount(Integer.MAX_VALUE);
        view.startAnimation(rotateAnimation);
    }
}
