package com.tron.wallet.common.components;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.animator.EmptyAnimator;
import com.lxj.xpopup.core.AttachPopupView;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.util.XPopupUtils;
import com.tron.wallet.common.bean.ConfirmExtraTextClickable;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.List;
public class MultiLineTextPopupView extends AttachPopupView {
    private static final int defaultTranslateX = 35;
    private final Builder builder;
    private View ivArrowDown;
    private int overflow;
    private RecyclerView rvContent;
    private int translationY;

    public enum LayoutMode {
        NORMAL,
        CENTER_HORIZONTAL
    }

    @Override
    public int getImplLayoutId() {
        return R.layout.popup_multi_line_text;
    }

    public MultiLineTextPopupView(Context context, Builder builder) {
        super(context);
        this.overflow = 10;
        this.builder = builder;
    }

    @Override
    public int getPopupWidth() {
        if (this.builder.requiredWidth == -2 || this.builder.requiredWidth == -1) {
            return getMaxWidth();
        }
        return this.builder.requiredWidth;
    }

    @Override
    public int getMaxWidth() {
        return (int) (XPopupUtils.getScreenWidth(getContext()) * 0.8f);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.ivArrowDown = findViewById(R.id.iv_arrow);
        if (!this.builder.showArrow) {
            this.ivArrowDown.setVisibility(View.GONE);
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_texts);
        this.rvContent = recyclerView;
        recyclerView.setAdapter(new ContentAdapter(KVHolder.ITEM_LAYOUT, this.builder.getData()));
        this.rvContent.setLayoutManager(new WrapContentLinearLayoutManager(getContext()));
        if (this.builder.itemPadding > 0) {
            this.rvContent.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView2, RecyclerView.State state) {
                    super.getItemOffsets(rect, view, recyclerView2, state);
                    if (recyclerView2.getChildAdapterPosition(view) > 0) {
                        rect.top = builder.itemPadding;
                    }
                }
            });
        }
    }

    @Override
    public void onShow() {
        super.onShow();
        if (this.builder.autoDismissAfter <= 0) {
            return;
        }
        postOnAnimationDelayed(new Runnable() {
            @Override
            public final void run() {
                dismiss();
            }
        }, this.builder.autoDismissAfter);
    }

    @Override
    public void doAttach() {
        int screenHeight;
        int i;
        final int dp2px = XPopupUtils.dp2px(getContext(), 15.0f);
        float screenHeight2 = XPopupUtils.getScreenHeight(getContext());
        this.overflow = XPopupUtils.dp2px(getContext(), 10.0f);
        int[] iArr = new int[2];
        this.popupInfo.atView.getLocationOnScreen(iArr);
        int i2 = iArr[0];
        final Rect rect = new Rect(i2, iArr[1], this.popupInfo.atView.getMeasuredWidth() + i2, iArr[1] + this.popupInfo.atView.getMeasuredHeight());
        if (this.builder.preferredShowUp) {
            this.isShowUp = true;
        } else if (rect.bottom + getPopupContentView().getMeasuredHeight() > screenHeight2) {
            this.isShowUp = (rect.top + rect.bottom) / 2 > XPopupUtils.getScreenHeight(getContext()) / 2;
        } else {
            this.isShowUp = false;
        }
        ViewGroup.LayoutParams layoutParams = getPopupContentView().getLayoutParams();
        if (this.isShowUp) {
            screenHeight = rect.top - XPopupUtils.getStatusBarHeight(getHostWindow());
            i = this.overflow;
        } else {
            screenHeight = XPopupUtils.getScreenHeight(getContext()) - rect.bottom;
            i = this.overflow;
        }
        int i3 = screenHeight - i;
        if (getPopupContentView().getMeasuredHeight() > i3) {
            layoutParams.height = i3;
        }
        layoutParams.width = getPopupWidth();
        getPopupContentView().setLayoutParams(layoutParams);
        ViewGroup.LayoutParams layoutParams2 = getPopupImplView().getLayoutParams();
        layoutParams2.width = -1;
        getPopupImplView().setLayoutParams(layoutParams2);
        if (this.defaultOffsetY <= 0) {
            this.defaultOffsetY = XPopupUtils.dp2px(getContext(), 2.0f);
        }
        getPopupContentView().post(new Runnable() {
            @Override
            public final void run() {
                lambda$doAttach$0(rect, dp2px);
            }
        });
    }

    public void lambda$doAttach$0(Rect rect, int i) {
        int i2;
        if (this.isShowUp) {
            this.translationY = (rect.top - getPopupContentView().getMeasuredHeight()) - this.defaultOffsetY;
        } else {
            this.translationY = rect.bottom + this.defaultOffsetY;
        }
        if (LayoutMode.CENTER_HORIZONTAL.equals(this.builder.mode)) {
            i = (XPopupUtils.getScreenWidth(getContext()) - getPopupContentView().getMeasuredWidth()) / 2;
        } else if (getPopupContentView().getMeasuredWidth() < XPopupUtils.getScreenWidth(getContext()) - (i * 2)) {
            if (this.popupInfo.offsetX == 0) {
                i2 = ((rect.left + this.popupInfo.atView.getMeasuredWidth()) - getPopupContentView().getMeasuredWidth()) + i;
            } else {
                i2 = this.popupInfo.offsetX;
            }
            int screenWidth = XPopupUtils.getScreenWidth(getContext()) - i;
            if (i2 >= i) {
                i = getPopupContentView().getMeasuredWidth() + i2 > screenWidth ? screenWidth - getPopupContentView().getMeasuredWidth() : i2;
            }
        }
        getPopupContentView().setTranslationX(i);
        getPopupContentView().setTranslationY(this.translationY);
        if (this.builder.showArrow) {
            translateArrowAndRelayout(this.isShowUp);
        }
        initAndStartAnimation();
    }

    private void translateArrowAndRelayout(boolean z) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.ivArrowDown.getLayoutParams();
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.rvContent.getLayoutParams();
        if (!z) {
            layoutParams.removeRule(3);
            this.ivArrowDown.setRotation(180.0f);
            layoutParams2.addRule(3, this.ivArrowDown.getId());
        } else {
            layoutParams2.removeRule(3);
            layoutParams.addRule(3, this.rvContent.getId());
        }
        this.ivArrowDown.setLayoutParams(layoutParams);
        this.rvContent.setLayoutParams(layoutParams2);
        int[] iArr = new int[2];
        View view = this.popupInfo.atView;
        view.getLocationInWindow(iArr);
        int[] iArr2 = new int[2];
        this.ivArrowDown.getLocationInWindow(iArr2);
        this.ivArrowDown.setTranslationX(Math.max(35, (iArr[0] - iArr2[0]) + (view.getMeasuredWidth() / 2) + (z ? 0 : this.ivArrowDown.getMeasuredWidth())));
    }

    public static class Builder {
        private View anchor;
        private long autoDismissAfter;
        private int itemPadding;
        private int offsetX;
        private int offsetY;
        private List<ConfirmExtraTextClickable> data = new ArrayList();
        private int requiredWidth = -1;
        private boolean preferredShowUp = false;
        private boolean showArrow = true;
        private boolean dismissOutTouch = true;
        private boolean clickThrough = false;
        private LayoutMode mode = LayoutMode.NORMAL;

        public View getAnchor() {
            return this.anchor;
        }

        public List<ConfirmExtraTextClickable> getData() {
            return this.data;
        }

        public int getItemPadding() {
            return this.itemPadding;
        }

        public int getRequiredWidth() {
            return this.requiredWidth;
        }

        public boolean isPreferredShowUp() {
            return this.preferredShowUp;
        }

        public Builder setAnchor(View view) {
            this.anchor = view;
            return this;
        }

        public Builder setAutoDismissAfter(long j) {
            this.autoDismissAfter = j;
            return this;
        }

        public Builder setClickThrough(boolean z) {
            this.clickThrough = z;
            return this;
        }

        public Builder setData(List<ConfirmExtraTextClickable> list) {
            this.data = list;
            return this;
        }

        public Builder setDismissOutTouch(boolean z) {
            this.dismissOutTouch = z;
            return this;
        }

        public Builder setItemPadding(int i) {
            this.itemPadding = i;
            return this;
        }

        public Builder setMode(LayoutMode layoutMode) {
            this.mode = layoutMode;
            return this;
        }

        public Builder setOffsetX(int i) {
            this.offsetX = i;
            return this;
        }

        public Builder setOffsetY(int i) {
            this.offsetY = i;
            return this;
        }

        public Builder setPreferredShowUp(boolean z) {
            this.preferredShowUp = z;
            return this;
        }

        public Builder setRequiredWidth(int i) {
            this.requiredWidth = i;
            return this;
        }

        public Builder setShowArrow(boolean z) {
            this.showArrow = z;
            return this;
        }

        public Builder addKeyValue(CharSequence charSequence, CharSequence charSequence2) {
            ConfirmExtraTextClickable confirmExtraTextClickable = new ConfirmExtraTextClickable();
            confirmExtraTextClickable.setLeft(charSequence);
            confirmExtraTextClickable.setRight(charSequence2);
            this.data.add(confirmExtraTextClickable);
            return this;
        }

        public Builder addItem(ConfirmExtraTextClickable confirmExtraTextClickable) {
            if (confirmExtraTextClickable != null) {
                this.data.add(confirmExtraTextClickable);
            }
            return this;
        }

        public Builder addKeyValue(CharSequence charSequence, CharSequence charSequence2, int i, int i2, View.OnClickListener onClickListener) {
            ConfirmExtraTextClickable confirmExtraTextClickable = new ConfirmExtraTextClickable();
            confirmExtraTextClickable.setLeft(charSequence);
            confirmExtraTextClickable.setRight(charSequence2);
            confirmExtraTextClickable.setClickable(true);
            confirmExtraTextClickable.setTextGravity(i);
            confirmExtraTextClickable.setRightActionIcon(i2);
            confirmExtraTextClickable.setRightIconClickListener(onClickListener);
            this.data.add(confirmExtraTextClickable);
            return this;
        }

        public BasePopupView build(Context context) {
            MultiLineTextPopupView multiLineTextPopupView = new MultiLineTextPopupView(context, this);
            return new XPopup.Builder(context).dismissOnTouchOutside(Boolean.valueOf(this.dismissOutTouch)).dismissOnBackPressed(true).atView(this.anchor).offsetX(this.offsetX).offsetY(this.offsetY).hasShadowBg(false).hasBlurBg(false).customAnimator(new EmptyAnimator(multiLineTextPopupView, 200)).asCustom(multiLineTextPopupView);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.builder.clickThrough) {
            this.dialog.dispatchTouchEvent(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    private static class ContentAdapter extends BaseQuickAdapter<ConfirmExtraTextClickable, KVHolder> {
        public ContentAdapter(int i, List<ConfirmExtraTextClickable> list) {
            super(i, list);
        }

        @Override
        public void convert(KVHolder kVHolder, ConfirmExtraTextClickable confirmExtraTextClickable) {
            kVHolder.onBind(confirmExtraTextClickable);
        }
    }

    public static class KVHolder extends BaseViewHolder {
        public static int ITEM_LAYOUT = 2131558877;
        private final ImageView ivCopy;
        private final TextView tvLeft;
        private final TextView tvRight;

        public KVHolder(View view) {
            super(view);
            TextView textView = (TextView) view.findViewById(R.id.tv_left);
            this.tvLeft = textView;
            this.tvRight = (TextView) view.findViewById(R.id.tv_right);
            this.ivCopy = (ImageView) this.itemView.findViewById(R.id.iv_copy);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }

        public void onBind(ConfirmExtraTextClickable confirmExtraTextClickable) {
            this.tvLeft.setText(confirmExtraTextClickable.getLeft() == null ? "" : confirmExtraTextClickable.getLeft());
            this.tvRight.setText(confirmExtraTextClickable.getRight() != null ? confirmExtraTextClickable.getRight() : "");
            this.ivCopy.setVisibility(View.GONE);
            this.tvRight.setGravity(confirmExtraTextClickable.getTextGravity());
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.tvRight.getLayoutParams();
            if (layoutParams.leftMargin != (!TextUtils.isEmpty(confirmExtraTextClickable.getLeft()) ? XPopupUtils.dp2px(this.itemView.getContext(), 16.0f) : 0)) {
                this.tvRight.setLayoutParams(layoutParams);
            }
            if (confirmExtraTextClickable.getOnItemClickListener() != null) {
                this.itemView.setOnClickListener(confirmExtraTextClickable.getOnItemClickListener());
            }
            if (!confirmExtraTextClickable.isClickable() || confirmExtraTextClickable.getRightActionIcon() <= 0) {
                return;
            }
            this.ivCopy.setImageResource(confirmExtraTextClickable.getRightActionIcon());
            this.ivCopy.setVisibility(View.VISIBLE);
            if (confirmExtraTextClickable.getRightIconClickListener() != null) {
                this.ivCopy.setOnClickListener(confirmExtraTextClickable.getRightIconClickListener());
            }
        }
    }
}
