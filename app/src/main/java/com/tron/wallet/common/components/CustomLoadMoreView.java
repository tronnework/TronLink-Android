package com.tron.wallet.common.components;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tronlinkpro.wallet.R;
public class CustomLoadMoreView extends LoadMoreView {
    private static final int DEFAULT_ANIM_TIME = 800;
    private Object[] noMoreTextArgs;
    private boolean failOnFixedTime = true;
    private int resNoMoreText = 0;

    @Override
    public int getLayoutId() {
        return R.layout.load_more_footer_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.no_more;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.rl_loading_failed;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.iv_loading;
    }

    public void setFailOnFixedTime(boolean z) {
        this.failOnFixedTime = z;
    }

    public void setNoMoreText(int i, Object... objArr) {
        this.resNoMoreText = i;
        this.noMoreTextArgs = objArr;
    }

    @Override
    public void convert(final BaseViewHolder baseViewHolder) {
        super.convert(baseViewHolder);
        setupNoMore(baseViewHolder);
        final View view = baseViewHolder.getView(getLoadingViewId());
        if (view != null && getLoadMoreStatus() == 2) {
            final RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
            rotateAnimation.setDuration(800L);
            rotateAnimation.setRepeatCount(this.failOnFixedTime ? 3 : Integer.MAX_VALUE);
            rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    if (getLoadMoreStatus() == 2) {
                        setLoadMoreStatus(3);
                        view.setVisibility(View.GONE);
                        baseViewHolder.getView(getLoadFailViewId()).setVisibility(View.VISIBLE);
                    }
                }
            });
            baseViewHolder.itemView.postOnAnimation(new Runnable() {
                @Override
                public final void run() {
                    lambda$convert$0(view, rotateAnimation);
                }
            });
        } else if (view != null) {
            Animation animation = view.getAnimation();
            if (animation != null) {
                animation.cancel();
            }
            baseViewHolder.setVisible(getLoadingViewId(), false);
        }
    }

    public void lambda$convert$0(View view, RotateAnimation rotateAnimation) {
        if (getLoadMoreStatus() == 2) {
            view.startAnimation(rotateAnimation);
        }
    }

    private void setupNoMore(BaseViewHolder baseViewHolder) {
        if (this.resNoMoreText <= 0) {
            return;
        }
        try {
            View view = baseViewHolder.getView(getLoadEndViewId());
            if (view instanceof TextView) {
                ((TextView) view).setText(baseViewHolder.itemView.getContext().getString(this.resNoMoreText, this.noMoreTextArgs));
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }
}
