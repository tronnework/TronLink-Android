package com.tron.wallet.common.components.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.tron.tron_base.frame.utils.LogUtils;
public class ImageButtonBehavior extends CoordinatorLayout.Behavior<ImageView> {
    private BounceInterpolator interpolator;
    private Context mContext;

    public ImageButtonBehavior() {
        this.interpolator = new BounceInterpolator();
    }

    public ImageButtonBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.interpolator = new BounceInterpolator();
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, ImageView imageView, View view) {
        return view instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, ImageView imageView, View view) {
        LogUtils.i("ImageButtonBehavior", "dependency:Y:" + view.getY());
        LogUtils.i("ImageButtonBehavior", "dependency.getHeight():" + view.getHeight());
        float y = view.getY() + ((float) (view.getHeight() / 2));
        LogUtils.i("ImageButtonBehavior", "Y:" + y);
        imageView.setY(90.0f);
        view.getY();
        imageView.setAlpha(1.0f);
        return true;
    }
}
