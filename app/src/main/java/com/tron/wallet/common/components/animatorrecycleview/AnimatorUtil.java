package com.tron.wallet.common.components.animatorrecycleview;

import android.animation.Animator;
public class AnimatorUtil {
    private AnimatorUtil() {
    }

    public static Animator[] concatAnimators(Animator[] animatorArr, Animator animator) {
        int length = animatorArr.length;
        Animator[] animatorArr2 = new Animator[length + 1];
        int i = 0;
        for (Animator animator2 : animatorArr) {
            animatorArr2[i] = animator2;
            i++;
        }
        animatorArr2[length] = animator;
        return animatorArr2;
    }
}
