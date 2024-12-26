package com.tron.wallet.common.components.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.utils.UIUtils;
public class AppbarViewBehavior extends CoordinatorLayout.Behavior<View> {
    private AppBarLayout.BaseOnOffsetChangedListener baseOnOffsetChangedListener;
    private BounceInterpolator interpolator;
    private Context mContext;
    private float offset;
    private float top;

    public void setTop(float f) {
        this.top = f;
    }

    public AppbarViewBehavior() {
        this.interpolator = new BounceInterpolator();
        this.top = 55.0f;
    }

    public AppbarViewBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.interpolator = new BounceInterpolator();
        this.top = 55.0f;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, View view, View view2) {
        if (view2 instanceof AppBarLayout) {
            if (this.baseOnOffsetChangedListener == null) {
                AppBarLayout.BaseOnOffsetChangedListener baseOnOffsetChangedListener = new AppBarLayout.BaseOnOffsetChangedListener() {
                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                        offset = i;
                    }
                };
                this.baseOnOffsetChangedListener = baseOnOffsetChangedListener;
                ((AppBarLayout) view2).addOnOffsetChangedListener(baseOnOffsetChangedListener);
                return true;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, View view, View view2) {
        if (view2 instanceof AppBarLayout) {
            LogUtils.d("ImageBehavior", "dependency:Y:" + view2.getY());
            LogUtils.d("ImageBehavior", "dependency.getHeight():" + view2.getHeight());
            view.setY(view2.getY() + ((float) UIUtils.dip2px(this.top)));
            float abs = 1.0f - (Math.abs(this.offset) / ((float) (((AppBarLayout) view2).getTotalScrollRange() / 3)));
            LogUtils.d("ImageBehavior", "alpha:" + abs);
            view.setAlpha(abs < 1.0f ? abs : 1.0f);
            return true;
        }
        return true;
    }
}
