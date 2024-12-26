package com.tron.wallet.common.components.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.appbar.AppBarLayout;
import com.tron.tron_base.frame.utils.LogUtils;
public class ImageBehavior extends CoordinatorLayout.Behavior<SimpleDraweeView> {
    private AppBarLayout.BaseOnOffsetChangedListener baseOnOffsetChangedListener;
    private BounceInterpolator interpolator;
    private Context mContext;
    private float offset;

    public ImageBehavior() {
        this.interpolator = new BounceInterpolator();
    }

    public ImageBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.interpolator = new BounceInterpolator();
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, SimpleDraweeView simpleDraweeView, View view) {
        if (view instanceof AppBarLayout) {
            if (this.baseOnOffsetChangedListener == null) {
                AppBarLayout.BaseOnOffsetChangedListener baseOnOffsetChangedListener = new AppBarLayout.BaseOnOffsetChangedListener() {
                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                        offset = i;
                        LogUtils.d("ImageBehavior", "offset:" + offset);
                    }
                };
                this.baseOnOffsetChangedListener = baseOnOffsetChangedListener;
                ((AppBarLayout) view).addOnOffsetChangedListener(baseOnOffsetChangedListener);
                return true;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, SimpleDraweeView simpleDraweeView, View view) {
        int height = view.getHeight() / 2;
        if (view instanceof AppBarLayout) {
            LogUtils.d("ImageBehavior", "dependency:Y:" + view.getY());
            LogUtils.d("ImageBehavior", "dependency.getHeight():" + view.getHeight());
            simpleDraweeView.setY(view.getY() + ((float) height));
            view.getY();
            float abs = 1.0f - (Math.abs(this.offset) / (((AppBarLayout) view).getTotalScrollRange() / 2));
            LogUtils.d("ImageBehavior", "alpha:" + abs);
            simpleDraweeView.setAlpha(abs < 1.0f ? abs : 1.0f);
            return true;
        }
        return true;
    }
}
