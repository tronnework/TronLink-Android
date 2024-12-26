package com.tron.wallet.common.utils;

import android.view.animation.TranslateAnimation;
public class AnimationUtil {
    private static final String TAG = "AnimationUtil";

    public static TranslateAnimation moveToViewRight() {
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 1.0f, 1, 0.0f, 1, 0.0f);
        translateAnimation.setDuration(300L);
        return translateAnimation;
    }

    public static TranslateAnimation moveToViewRightLocation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 1.0f, 1, 0.0f, 1, 0.0f, 1, 0.0f);
        translateAnimation.setDuration(300L);
        return translateAnimation;
    }

    public static TranslateAnimation moveToViewLeft() {
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, -1.0f, 1, 0.0f, 1, 0.0f);
        translateAnimation.setDuration(300L);
        return translateAnimation;
    }

    public static TranslateAnimation moveToViewLeftLocation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(1, -1.0f, 1, 0.0f, 1, 0.0f, 1, 0.0f);
        translateAnimation.setDuration(300L);
        return translateAnimation;
    }
}
