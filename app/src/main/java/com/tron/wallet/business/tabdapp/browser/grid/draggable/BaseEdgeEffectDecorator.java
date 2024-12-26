package com.tron.wallet.business.tabdapp.browser.grid.draggable;

import android.graphics.Canvas;
import android.widget.EdgeEffect;
import androidx.core.view.ViewCompat;
import androidx.core.widget.EdgeEffectCompat;
import androidx.recyclerview.widget.RecyclerView;
public abstract class BaseEdgeEffectDecorator extends RecyclerView.ItemDecoration {
    protected static final int EDGE_BOTTOM = 3;
    protected static final int EDGE_LEFT = 0;
    protected static final int EDGE_RIGHT = 2;
    protected static final int EDGE_TOP = 1;
    private EdgeEffect mGlow1;
    private int mGlow1Dir;
    private EdgeEffect mGlow2;
    private int mGlow2Dir;
    private RecyclerView mRecyclerView;
    private boolean mStarted;

    protected abstract int getEdgeDirection(int i);

    public BaseEdgeEffectDecorator(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
    }

    @Override
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        EdgeEffect edgeEffect = this.mGlow1;
        boolean drawGlow = edgeEffect != null ? drawGlow(canvas, recyclerView, this.mGlow1Dir, edgeEffect) : false;
        EdgeEffect edgeEffect2 = this.mGlow2;
        if (edgeEffect2 != null) {
            drawGlow |= drawGlow(canvas, recyclerView, this.mGlow2Dir, edgeEffect2);
        }
        if (drawGlow) {
            ViewCompat.postInvalidateOnAnimation(recyclerView);
        }
    }

    private static boolean drawGlow(Canvas canvas, RecyclerView recyclerView, int i, EdgeEffect edgeEffect) {
        if (edgeEffect.isFinished()) {
            return false;
        }
        int save = canvas.save();
        boolean clipToPadding = getClipToPadding(recyclerView);
        if (i == 0) {
            canvas.rotate(-90.0f);
            if (clipToPadding) {
                canvas.translate((-recyclerView.getHeight()) + recyclerView.getPaddingTop(), recyclerView.getPaddingLeft());
            } else {
                canvas.translate(-recyclerView.getHeight(), 0.0f);
            }
        } else if (i != 1) {
            if (i == 2) {
                canvas.rotate(90.0f);
                if (clipToPadding) {
                    canvas.translate(recyclerView.getPaddingTop(), (-recyclerView.getWidth()) + recyclerView.getPaddingRight());
                } else {
                    canvas.translate(0.0f, -recyclerView.getWidth());
                }
            } else if (i == 3) {
                canvas.rotate(180.0f);
                if (clipToPadding) {
                    canvas.translate((-recyclerView.getWidth()) + recyclerView.getPaddingRight(), (-recyclerView.getHeight()) + recyclerView.getPaddingBottom());
                } else {
                    canvas.translate(-recyclerView.getWidth(), -recyclerView.getHeight());
                }
            }
        } else if (clipToPadding) {
            canvas.translate(recyclerView.getPaddingLeft(), recyclerView.getPaddingTop());
        }
        boolean draw = edgeEffect.draw(canvas);
        canvas.restoreToCount(save);
        return draw;
    }

    public void start() {
        if (this.mStarted) {
            return;
        }
        this.mGlow1Dir = getEdgeDirection(0);
        this.mGlow2Dir = getEdgeDirection(1);
        this.mRecyclerView.addItemDecoration(this);
        this.mStarted = true;
    }

    public void finish() {
        if (this.mStarted) {
            this.mRecyclerView.removeItemDecoration(this);
        }
        releaseBothGlows();
        this.mRecyclerView = null;
        this.mStarted = false;
    }

    public void pullFirstEdge(float f) {
        ensureGlow1(this.mRecyclerView);
        EdgeEffectCompat.onPull(this.mGlow1, f, 0.5f);
        ViewCompat.postInvalidateOnAnimation(this.mRecyclerView);
    }

    public void pullSecondEdge(float f) {
        ensureGlow2(this.mRecyclerView);
        EdgeEffectCompat.onPull(this.mGlow2, f, 0.5f);
        ViewCompat.postInvalidateOnAnimation(this.mRecyclerView);
    }

    public void releaseBothGlows() {
        boolean z;
        EdgeEffect edgeEffect = this.mGlow1;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            z = this.mGlow1.isFinished();
        } else {
            z = false;
        }
        EdgeEffect edgeEffect2 = this.mGlow2;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            z |= this.mGlow2.isFinished();
        }
        if (z) {
            ViewCompat.postInvalidateOnAnimation(this.mRecyclerView);
        }
    }

    private void ensureGlow1(RecyclerView recyclerView) {
        if (this.mGlow1 == null) {
            this.mGlow1 = new EdgeEffect(recyclerView.getContext());
        }
        updateGlowSize(recyclerView, this.mGlow1, this.mGlow1Dir);
    }

    private void ensureGlow2(RecyclerView recyclerView) {
        if (this.mGlow2 == null) {
            this.mGlow2 = new EdgeEffect(recyclerView.getContext());
        }
        updateGlowSize(recyclerView, this.mGlow2, this.mGlow2Dir);
    }

    private static void updateGlowSize(RecyclerView recyclerView, EdgeEffect edgeEffect, int i) {
        int measuredWidth = recyclerView.getMeasuredWidth();
        int measuredHeight = recyclerView.getMeasuredHeight();
        if (getClipToPadding(recyclerView)) {
            measuredWidth -= recyclerView.getPaddingLeft() + recyclerView.getPaddingRight();
            measuredHeight -= recyclerView.getPaddingTop() + recyclerView.getPaddingBottom();
        }
        int max = Math.max(0, measuredWidth);
        int max2 = Math.max(0, measuredHeight);
        if (i == 0 || i == 2) {
            max = max2;
            max2 = max;
        }
        edgeEffect.setSize(max, max2);
    }

    private static boolean getClipToPadding(RecyclerView recyclerView) {
        return recyclerView.getLayoutManager().getClipToPadding();
    }

    public void reorderToTop() {
        if (this.mStarted) {
            this.mRecyclerView.removeItemDecoration(this);
            this.mRecyclerView.addItemDecoration(this);
        }
    }
}
