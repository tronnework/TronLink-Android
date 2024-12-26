package com.tron.wallet.common.components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.wallet.common.utils.ChannelUtils;
import com.tron.wallet.databinding.MainTabViewBinding;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
public class MainTabView extends RelativeLayout implements View.OnClickListener {
    private int activeTab;
    private AnimatorSet animatorSet;
    MainTabViewBinding binding;
    private boolean expanded;
    ImageView ivAssets;
    ImageView ivDiscovery;
    ImageView ivMarket;
    ImageView ivMarketNew;
    ImageView ivMy;
    View ivRedDot;
    View llAssets;
    View llDiscovery;
    View llMarket;
    View llMy;
    private OnTabClickListener onTabClickListener;
    View tipAssets;
    View tipDiscovery;
    View tipMarket;
    View tipMy;
    TextView tvAssets;
    TextView tvDiscovery;
    TextView tvMarket;
    TextView tvMy;

    public interface OnTabClickListener {
        void onTabClick(int i);
    }

    public static class Tab {
        public static final int TAB_ASSETS = 0;
        public static final int TAB_DISCOVERY = 2;
        public static final int TAB_MARKET = 1;
        public static final int TAB_MY = 3;
    }

    public int getActiveTab() {
        return this.activeTab;
    }

    public void setOnTabClickListener(OnTabClickListener onTabClickListener) {
        this.onTabClickListener = onTabClickListener;
    }

    public MainTabView(Context context) {
        this(context, null);
    }

    public MainTabView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MainTabView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.activeTab = 0;
        this.expanded = true;
        initTabView(context);
    }

    public void showTab(int i, boolean z) {
        if (i == 0) {
            resumeState();
            this.llAssets.setVisibility(z ? View.VISIBLE : View.GONE);
        } else if (i == 1) {
            resumeState();
            this.llMarket.setVisibility(z ? View.VISIBLE : View.GONE);
        } else if (i == 2) {
            this.llDiscovery.setVisibility(z ? View.VISIBLE : View.GONE);
        } else if (i != 3) {
        } else {
            resumeState();
            this.llMy.setVisibility(z ? View.VISIBLE : View.GONE);
        }
    }

    private void resumeState() {
        this.expanded = true;
        this.llAssets.setTranslationX(0.0f);
    }

    public void setExpanded(boolean z) {
        ObjectAnimator ofFloat;
        ObjectAnimator ofFloat2;
        if (this.expanded == z) {
            return;
        }
        this.expanded = z;
        AnimatorSet animatorSet = this.animatorSet;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        this.animatorSet = new AnimatorSet();
        if (this.expanded) {
            ofFloat = ObjectAnimator.ofFloat(this, "translationX", getWidth(), 0.0f);
            ofFloat2 = ObjectAnimator.ofFloat(this, "alpha", 0.0f, 1.0f);
        } else {
            ofFloat = ObjectAnimator.ofFloat(this, "translationX", 0.0f, getWidth());
            ofFloat2 = ObjectAnimator.ofFloat(this, "alpha", 1.0f, 0.0f);
        }
        this.animatorSet.playTogether(ofFloat, ofFloat2);
        this.animatorSet.setDuration(200L);
        this.animatorSet.start();
    }

    public void showRedDot(boolean z) {
        this.ivRedDot.setVisibility(z ? View.VISIBLE : View.GONE);
    }

    public void setTabSelected(int i) {
        if (i == 0) {
            resumeState();
            onTabClick(this.llAssets, this.tvAssets, this.tipAssets, this.ivAssets, 0);
        } else if (i == 1) {
            resumeState();
            onTabClick(this.llMarket, this.tvMarket, this.tipMarket, this.ivMarket, 1);
        } else if (i == 2) {
            onTabClick(this.llDiscovery, this.tvDiscovery, this.tipDiscovery, this.ivDiscovery, 2);
        } else if (i != 3) {
        } else {
            resumeState();
            onTabClick(this.llMy, this.tvMy, this.tipMy, this.ivMy, 3);
        }
    }

    private void initTabView(Context context) {
        View inflate = View.inflate(context, R.layout.main_tab_view, null);
        this.binding = MainTabViewBinding.bind(inflate);
        mappingId();
        initClick();
        addView(inflate, new RelativeLayout.LayoutParams(-1, -2));
        int hasShowMarketTabNew = SpAPI.THIS.getHasShowMarketTabNew();
        if (ChannelUtils.OFFICIAL.equals(ChannelUtils.getChannel(getContext())) || IRequest.isNile() || hasShowMarketTabNew == 1) {
            this.ivMarketNew.setVisibility(View.GONE);
        }
    }

    private void initClick() {
        this.binding.llTabAssets.setOnClickListener(this);
        this.binding.llTabMarket.setOnClickListener(this);
        this.binding.llTabDiscovery.setOnClickListener(this);
        this.binding.llTabMy.setOnClickListener(this);
    }

    private void mappingId() {
        this.llAssets = this.binding.llTabAssets;
        this.tvAssets = this.binding.tvAssets;
        this.tipAssets = this.binding.tipAssets;
        this.ivAssets = this.binding.ivTabAssets;
        this.llMarket = this.binding.llTabMarket;
        this.tvMarket = this.binding.tvMarket;
        this.tipMarket = this.binding.tipMarket;
        this.ivMarket = this.binding.ivTabMarket;
        this.ivMarketNew = this.binding.ivMarketNew;
        this.llDiscovery = this.binding.llTabDiscovery;
        this.tvDiscovery = this.binding.tvDiscovery;
        this.tipDiscovery = this.binding.tipDiscovery;
        this.ivDiscovery = this.binding.ivTabDiscovery;
        this.llMy = this.binding.llTabMy;
        this.tvMy = this.binding.tvMy;
        this.tipMy = this.binding.tipMy;
        this.ivMy = this.binding.ivTabMy;
        this.ivRedDot = this.binding.ivRedDot;
    }

    @Override
    public void onClick(View view) {
        ((Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE)).vibrate(60L);
        switch (view.getId()) {
            case R.id.ll_tab_assets:
                onTabClick(this.llAssets, this.tvAssets, this.tipAssets, this.ivAssets, 0);
                return;
            case R.id.ll_tab_count:
            case R.id.ll_tab_main:
            default:
                return;
            case R.id.ll_tab_discovery:
                onTabClick(this.llDiscovery, this.tvDiscovery, this.tipDiscovery, this.ivDiscovery, 2);
                return;
            case R.id.ll_tab_market:
                onTabClick(this.llMarket, this.tvMarket, this.tipMarket, this.ivMarket, 1);
                if (this.ivMarketNew.getVisibility() == 0) {
                    SpAPI.THIS.setHasShowMarketTabNew();
                    this.ivMarketNew.setVisibility(View.GONE);
                    return;
                }
                return;
            case R.id.ll_tab_my:
                onTabClick(this.llMy, this.tvMy, this.tipMy, this.ivMy, 3);
                return;
        }
    }

    private void onTabClick(View view, TextView textView, View view2, ImageView imageView, int i) {
        if (this.activeTab == i) {
            return;
        }
        OnTabClickListener onTabClickListener = this.onTabClickListener;
        if (onTabClickListener != null) {
            onTabClickListener.onTabClick(i);
        }
        int i2 = this.activeTab;
        this.activeTab = i;
        new ArrayList();
        int i3 = this.activeTab;
        int i4 = R.mipmap.ic_tab_assets_focus;
        if (i3 != 0) {
            if (i3 == 1) {
                i4 = R.mipmap.ic_tab_market_focus;
            } else if (i3 == 2) {
                i4 = R.mipmap.ic_tab_discovery_focus;
            } else if (i3 == 3) {
                i4 = R.mipmap.ic_tab_mine_focus;
            }
        }
        textView.setTextColor(getContext().getResources().getColor(R.color.black_23));
        imageView.setImageResource(i4);
        if (i2 == 0) {
            this.tvAssets.setTextColor(getContext().getResources().getColor(R.color.black_6d));
            this.ivAssets.setImageResource(R.mipmap.ic_tab_assets);
        } else if (i2 == 1) {
            this.tvMarket.setTextColor(getContext().getResources().getColor(R.color.black_6d));
            this.ivMarket.setImageResource(R.mipmap.ic_tab_market);
        } else if (i2 == 2) {
            this.tvDiscovery.setTextColor(getContext().getResources().getColor(R.color.black_6d));
            this.ivDiscovery.setImageResource(R.mipmap.ic_tab_discovery);
        } else if (i2 != 3) {
        } else {
            this.tvMy.setTextColor(getContext().getResources().getColor(R.color.black_6d));
            this.ivMy.setImageResource(R.mipmap.ic_tab_mine);
        }
    }

    private Animator getTabViewAnimator(final View view, final View view2, final View view3, final View view4) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                view2.setAlpha(floatValue);
                float f = 1.0f - floatValue;
                view.setAlpha(f);
                view4.setAlpha(f);
                view3.setAlpha(floatValue);
                View view5 = view2;
                view5.setPivotX(view5.getWidth() / 2);
                view2.setPivotY(0.0f);
                float f2 = floatValue * 0.2f;
                float f3 = 0.8f + f2;
                view2.setScaleX(f3);
                view2.setScaleY(f3);
                View view6 = view4;
                view6.setPivotX(view6.getWidth() / 2);
                view4.setPivotY(0.0f);
                float f4 = 1.0f - f2;
                view4.setScaleX(f4);
                view4.setScaleY(f4);
            }
        });
        ofFloat.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                view2.setVisibility(View.VISIBLE);
                view2.setAlpha(1.0f);
                view2.setScaleX(1.0f);
                view2.setScaleY(1.0f);
                view.setVisibility(View.INVISIBLE);
                view.setAlpha(1.0f);
                view4.setVisibility(View.INVISIBLE);
                view4.setAlpha(1.0f);
                view4.setScaleX(1.0f);
                view4.setScaleY(1.0f);
                view3.setVisibility(View.VISIBLE);
                view3.setAlpha(1.0f);
            }
        });
        return ofFloat;
    }

    private Animator getTabTipViewAnimator(View view, View view2) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        int[] iArr2 = new int[2];
        view2.getLocationInWindow(iArr2);
        return ObjectAnimator.ofFloat(view2, "translationX", iArr[0] - iArr2[0], 0.0f);
    }
}
