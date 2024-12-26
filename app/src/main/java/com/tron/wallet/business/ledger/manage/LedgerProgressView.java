package com.tron.wallet.business.ledger.manage;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tron.wallet.common.utils.UIUtils;
import com.tronlinkpro.wallet.R;
public class LedgerProgressView extends RelativeLayout {
    private Animation animation;
    private SimpleDraweeView loading_view;
    private String name;
    private STATUS status;
    private TextView tvTips;

    public enum STATUS {
        LOADING,
        UNLOCK,
        OPEN,
        CONFIRM
    }

    public void setEquipmentName(String str) {
        this.name = str;
    }

    public LedgerProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(getContext()).inflate(R.layout.ledger_loading_bar, (ViewGroup) this, true);
        this.loading_view = (SimpleDraweeView) findViewById(R.id.tv_image);
        this.tvTips = (TextView) findViewById(R.id.tv_tips);
    }

    private void startLoadingView() {
        this.tvTips.setText(String.format(getResources().getString(R.string.connecting_to_ledger), this.name));
        this.loading_view.setImageResource(R.mipmap.circle_loading_50);
        ((RelativeLayout.LayoutParams) this.loading_view.getLayoutParams()).height = UIUtils.dip2px(50.0f);
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        this.animation = rotateAnimation;
        rotateAnimation.setDuration(1000L);
        this.animation.setFillAfter(true);
        this.animation.setRepeatCount(2);
        this.loading_view.startAnimation(this.animation);
        this.animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (status == STATUS.LOADING) {
                    setStatus(STATUS.UNLOCK);
                }
            }
        });
    }

    private Uri getUriFromDrawableRes(int i) {
        return new Uri.Builder().scheme(UriUtil.LOCAL_RESOURCE_SCHEME).path(String.valueOf(i)).build();
    }

    public void startOpenLoadingView() {
        this.tvTips.setText(String.format(getResources().getString(R.string.turn_on_tron_on_ledger), this.name));
        AbstractDraweeController build = Fresco.newDraweeControllerBuilder().setUri(getUriFromDrawableRes(R.drawable.ledger_open)).setAutoPlayAnimations(true).build();
        if (this.loading_view.hasController()) {
            this.loading_view.setController(null);
        }
        this.loading_view.setController(build);
    }

    private void startUnLockLoadingView() {
        this.tvTips.setText(getResources().getString(R.string.turn_on_and_unlock_your_ledger));
        AbstractDraweeController build = Fresco.newDraweeControllerBuilder().setUri(getUriFromDrawableRes(R.drawable.ledger_unlock)).setAutoPlayAnimations(true).build();
        if (this.loading_view.hasController()) {
            this.loading_view.setController(null);
        }
        this.loading_view.setController(build);
    }

    private void startConfirmLoadingView() {
        this.tvTips.setText(getResources().getString(R.string.confirm_on_tron_app_on_ledger));
        AbstractDraweeController build = Fresco.newDraweeControllerBuilder().setUri(getUriFromDrawableRes(R.drawable.ledger_confirm)).setAutoPlayAnimations(true).build();
        if (this.loading_view.hasController()) {
            this.loading_view.setController(null);
        }
        this.loading_view.setController(build);
    }

    public void setStatus(STATUS status) {
        this.status = status;
        ((RelativeLayout.LayoutParams) this.loading_view.getLayoutParams()).height = UIUtils.dip2px(40.0f);
        int i = fun2.$SwitchMap$com$tron$wallet$business$ledger$manage$LedgerProgressView$STATUS[status.ordinal()];
        if (i == 1) {
            startLoadingView();
        } else if (i == 2) {
            Animation animation = this.animation;
            if (animation != null) {
                animation.cancel();
            }
            startUnLockLoadingView();
        } else if (i == 3) {
            Animation animation2 = this.animation;
            if (animation2 != null) {
                animation2.cancel();
            }
            startOpenLoadingView();
        } else if (i != 4) {
        } else {
            Animation animation3 = this.animation;
            if (animation3 != null) {
                animation3.cancel();
            }
            startConfirmLoadingView();
        }
    }

    public static class fun2 {
        static final int[] $SwitchMap$com$tron$wallet$business$ledger$manage$LedgerProgressView$STATUS;

        static {
            int[] iArr = new int[STATUS.values().length];
            $SwitchMap$com$tron$wallet$business$ledger$manage$LedgerProgressView$STATUS = iArr;
            try {
                iArr[STATUS.LOADING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$ledger$manage$LedgerProgressView$STATUS[STATUS.UNLOCK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$ledger$manage$LedgerProgressView$STATUS[STATUS.OPEN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$ledger$manage$LedgerProgressView$STATUS[STATUS.CONFIRM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }
}
