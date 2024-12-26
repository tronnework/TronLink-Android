package com.tron.wallet.common.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.wallet.R;
import com.tron.wallet.common.utils.UIUtils;
public class NoNetView extends RelativeLayout {
    private ImageView ivIcon;
    private View ivLoading;
    private View llRoot;
    public View rlReload;
    private boolean showLoadingAnim;
    private TextView tvDesc;
    public TextView tvReload;

    public NoNetView(Context context) {
        this(context, null);
    }

    public NoNetView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NoNetView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.NoNetView);
        Drawable drawable = obtainStyledAttributes.getDrawable(5);
        obtainStyledAttributes.getString(9);
        int resourceId = obtainStyledAttributes.getResourceId(9, -1);
        String string = obtainStyledAttributes.getString(1);
        boolean z = obtainStyledAttributes.getBoolean(11, true);
        Drawable drawable2 = obtainStyledAttributes.getDrawable(0);
        float dimension = obtainStyledAttributes.getDimension(8, UIUtils.dip2px(getContext(), 120.0f));
        float dimension2 = obtainStyledAttributes.getDimension(6, UIUtils.dip2px(getContext(), 120.0f));
        float dimension3 = obtainStyledAttributes.getDimension(4, 14.0f);
        int color = obtainStyledAttributes.getColor(3, getContext().getResources().getColor(com.tronlinkpro.wallet.R.color.gray_9B));
        int color2 = obtainStyledAttributes.getColor(2, getContext().getResources().getColor(com.tronlinkpro.wallet.R.color.black_02_30));
        float dimension4 = obtainStyledAttributes.getDimension(7, UIUtils.dip2px(getContext(), 20.0f));
        float dimension5 = obtainStyledAttributes.getDimension(10, UIUtils.dip2px(getContext(), 8.0f));
        this.showLoadingAnim = obtainStyledAttributes.getBoolean(12, true);
        obtainStyledAttributes.recycle();
        LayoutInflater.from(context).inflate(com.tronlinkpro.wallet.R.layout.nonet_view, (ViewGroup) this, true);
        this.llRoot = findViewById(com.tronlinkpro.wallet.R.id.ll_no_net);
        this.tvDesc = (TextView) findViewById(com.tronlinkpro.wallet.R.id.net_error);
        this.tvReload = (TextView) findViewById(com.tronlinkpro.wallet.R.id.tv_reload);
        this.ivIcon = (ImageView) findViewById(com.tronlinkpro.wallet.R.id.iv_icon);
        this.ivLoading = findViewById(com.tronlinkpro.wallet.R.id.bt_loadding);
        this.rlReload = findViewById(com.tronlinkpro.wallet.R.id.rl_reload);
        if (drawable != null) {
            this.ivIcon.setImageDrawable(drawable);
            ViewGroup.LayoutParams layoutParams = this.ivIcon.getLayoutParams();
            layoutParams.width = (int) dimension;
            layoutParams.height = (int) dimension2;
            this.ivIcon.setLayoutParams(layoutParams);
        }
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.tvDesc.getLayoutParams();
        layoutParams2.topMargin = (int) dimension4;
        this.tvDesc.setLayoutParams(layoutParams2);
        LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) this.rlReload.getLayoutParams();
        layoutParams3.topMargin = (int) dimension5;
        this.rlReload.setLayoutParams(layoutParams3);
        if (resourceId > 0) {
            setReloadDescription(resourceId);
            this.tvDesc.setTextColor(color);
            this.tvDesc.setTextSize(dimension3);
        }
        if (!TextUtils.isEmpty(string)) {
            this.tvReload.setText(string);
            this.tvReload.setTextColor(color2);
        }
        setReloadable(z);
        if (!z || drawable2 == null) {
            return;
        }
        this.rlReload.setBackground(drawable2);
    }

    public void setReloadable(boolean z) {
        if (z) {
            this.rlReload.setVisibility(View.VISIBLE);
        } else {
            this.rlReload.setVisibility(View.GONE);
        }
    }

    @Override
    public void setVisibility(int i) {
        super.setVisibility(i);
        updateLoadingState(i == 0);
    }

    public void updateLoadingState(boolean z) {
        Animation animation = this.ivLoading.getAnimation();
        if (animation != null) {
            animation.cancel();
        }
        if (z) {
            this.ivLoading.setVisibility(View.GONE);
            this.tvReload.setVisibility(View.VISIBLE);
        }
    }

    public NoNetView setIcon(int i) {
        ImageView imageView = this.ivIcon;
        if (imageView != null) {
            imageView.setImageResource(i);
        }
        return this;
    }

    public NoNetView setReloadText(int i) {
        TextView textView = this.tvReload;
        if (textView != null) {
            textView.setText(i);
        }
        return this;
    }

    public NoNetView setReloadDescription(int i) {
        TextView textView = this.tvDesc;
        if (textView != null) {
            textView.setText(i);
            if (i == com.tronlinkpro.wallet.R.string.web_unaccess) {
                this.tvDesc.setMaxWidth(UIUtils.dip2px(getContext(), 220.0f));
            } else {
                this.tvDesc.setMaxWidth(Integer.MAX_VALUE);
            }
        }
        return this;
    }

    public NoNetView setOnReloadClickListener(final View.OnClickListener onClickListener) {
        View view = this.rlReload;
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view2) {
                    lambda$setOnReloadClickListener$1(onClickListener, view2);
                }
            });
        }
        return this;
    }

    public void lambda$setOnReloadClickListener$1(final View.OnClickListener onClickListener, View view) {
        if (!this.showLoadingAnim) {
            onClickListener.onClick(this.rlReload);
            return;
        }
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(800L);
        rotateAnimation.setRepeatCount(-1);
        this.tvReload.setVisibility(View.GONE);
        this.ivLoading.setVisibility(View.VISIBLE);
        this.ivLoading.startAnimation(rotateAnimation);
        postDelayed(new Runnable() {
            @Override
            public final void run() {
                lambda$setOnReloadClickListener$0(onClickListener);
            }
        }, 500L);
    }

    public void lambda$setOnReloadClickListener$0(View.OnClickListener onClickListener) {
        onClickListener.onClick(this.rlReload);
    }

    public NoNetView setOnReloadClickListenerWithOutAnimation(final View.OnClickListener onClickListener) {
        View view = this.rlReload;
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view2) {
                    lambda$setOnReloadClickListenerWithOutAnimation$2(onClickListener, view2);
                }
            });
        }
        return this;
    }

    public void lambda$setOnReloadClickListenerWithOutAnimation$2(View.OnClickListener onClickListener, View view) {
        onClickListener.onClick(this.rlReload);
    }

    public void showLoading() {
        View view = this.ivLoading;
        if (view != null) {
            Animation animation = view.getAnimation();
            if (animation != null) {
                animation.cancel();
                this.ivLoading.clearAnimation();
            }
            RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
            rotateAnimation.setDuration(800L);
            rotateAnimation.setRepeatCount(-1);
            this.tvReload.setVisibility(View.GONE);
            this.ivLoading.setVisibility(View.VISIBLE);
            this.ivLoading.startAnimation(rotateAnimation);
        }
    }

    public void hideLoading() {
        if (this.rlReload != null) {
            Animation animation = this.ivLoading.getAnimation();
            if (animation != null) {
                animation.cancel();
                this.ivLoading.clearAnimation();
            }
            this.tvReload.setVisibility(View.VISIBLE);
            this.ivLoading.setVisibility(View.GONE);
        }
    }

    public void setReloadMarginTop(float f) {
        View view = this.rlReload;
        if (view == null || !(view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.rlReload.getLayoutParams();
        marginLayoutParams.topMargin = UIUtils.dip2px(f);
        this.rlReload.setLayoutParams(marginLayoutParams);
    }

    public void setInnerTopMargin(int i) {
        View view = this.llRoot;
        if (view == null || !(view.getLayoutParams() instanceof RelativeLayout.LayoutParams)) {
            return;
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.llRoot.getLayoutParams();
        layoutParams.removeRule(13);
        layoutParams.topMargin = i;
    }
}
