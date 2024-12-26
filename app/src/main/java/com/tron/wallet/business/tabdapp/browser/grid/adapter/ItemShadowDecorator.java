package com.tron.wallet.business.tabdapp.browser.grid.adapter;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
public class ItemShadowDecorator extends RecyclerView.ItemDecoration {
    private final boolean mCastShadowForTransparentBackgroundItem;
    private final NinePatchDrawable mShadowDrawable;
    private final Rect mShadowPadding;

    public ItemShadowDecorator(NinePatchDrawable ninePatchDrawable) {
        this(ninePatchDrawable, true);
    }

    public ItemShadowDecorator(NinePatchDrawable ninePatchDrawable, boolean z) {
        Rect rect = new Rect();
        this.mShadowPadding = rect;
        this.mShadowDrawable = ninePatchDrawable;
        ninePatchDrawable.getPadding(rect);
        this.mCastShadowForTransparentBackgroundItem = z;
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        int childCount = recyclerView.getChildCount();
        if (childCount == 0) {
            return;
        }
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            if (shouldDrawDropShadow(childAt)) {
                int translationX = (int) (childAt.getTranslationX() + 0.5f);
                int translationY = (int) (childAt.getTranslationY() + 0.5f);
                int left = childAt.getLeft() - this.mShadowPadding.left;
                int right = childAt.getRight() + this.mShadowPadding.right;
                this.mShadowDrawable.setBounds(left + translationX, (childAt.getTop() - this.mShadowPadding.top) + translationY, right + translationX, childAt.getBottom() + this.mShadowPadding.bottom + translationY);
                this.mShadowDrawable.draw(canvas);
            }
        }
    }

    private boolean shouldDrawDropShadow(View view) {
        Drawable background;
        if (view.getVisibility() == 0 && view.getAlpha() == 1.0f && (background = view.getBackground()) != null) {
            return (!this.mCastShadowForTransparentBackgroundItem && (background instanceof ColorDrawable) && ((ColorDrawable) background).getAlpha() == 0) ? false : true;
        }
        return false;
    }

    @Override
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        rect.set(0, 0, 0, 0);
    }
}
