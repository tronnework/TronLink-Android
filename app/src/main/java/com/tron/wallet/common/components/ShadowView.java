package com.tron.wallet.common.components;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.core.view.ViewCompat;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.utils.SentryUtil;
public class ShadowView extends RelativeLayout {
    private static final String TAG = "ShadowLayout";
    private int mHeight;
    private int mPadding;
    private Paint mPaintShadow;
    private View mRootView;
    private int mRound;
    private Bitmap mShadowBitmap;
    private int mWidth;

    public ShadowView setRound(int i) {
        this.mRound = i;
        return this;
    }

    private void init() {
        Paint paint = new Paint(1);
        this.mPaintShadow = paint;
        paint.setColorFilter(new PorterDuffColorFilter(ViewCompat.MEASURED_STATE_MASK, PorterDuff.Mode.SRC_IN));
        this.mPaintShadow.setAlpha(12);
        setLayerType(1, null);
        setWillNotDraw(false);
    }

    public ShadowView(Context context) {
        super(context, null);
        this.mPadding = 0;
        this.mRound = dp2px(4.0f);
    }

    public ShadowView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ShadowView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mPadding = 0;
        this.mRound = dp2px(4.0f);
        init();
    }

    public void initView() {
        try {
            this.mRootView.post(new Runnable() {
                @Override
                public void run() {
                    ViewGroup viewGroup = (ViewGroup) mRootView.getParent();
                    if (viewGroup != null) {
                        RelativeLayout relativeLayout = new RelativeLayout(getContext());
                        viewGroup.addView(relativeLayout, mRootView.getLayoutParams());
                        viewGroup.removeView(mRootView);
                        ViewGroup.LayoutParams layoutParams = mRootView.getLayoutParams();
                        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(layoutParams);
                        layoutParams2.setMargins(mRootView.getPaddingLeft(), mRootView.getPaddingTop(), mRootView.getPaddingRight(), mRootView.getPaddingBottom());
                        relativeLayout.addView(ShadowView.this, layoutParams2);
                        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(layoutParams);
                        layoutParams3.setMargins(mPadding, mPadding, mPadding, mPadding);
                        relativeLayout.addView(mRootView, layoutParams3);
                        mPaintShadow = new Paint(1);
                        mPaintShadow.setColorFilter(new PorterDuffColorFilter(1973547, PorterDuff.Mode.SRC_IN));
                        mPaintShadow.setAlpha(12);
                        setLayerType(1, null);
                    }
                }
            });
        } catch (Throwable th) {
            LogUtils.e(TAG, "errShadowLayout=====" + th.getMessage());
            SentryUtil.captureException(th);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LogUtils.i(TAG, "==================onDraw");
        try {
            Bitmap bitmap = this.mShadowBitmap;
            if (bitmap == null) {
                drawShadow(canvas);
            } else {
                canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
            }
        } catch (Throwable th) {
            SentryUtil.captureException(th);
            LogUtils.e(TAG, "errOnDraw=====" + th.getMessage());
        }
    }

    private void drawShadow(Canvas canvas) {
        Bitmap createBitmap = Bitmap.createBitmap(this.mWidth, this.mHeight, Bitmap.Config.ALPHA_8);
        this.mShadowBitmap = createBitmap;
        createBitmap.eraseColor(0);
        Canvas canvas2 = new Canvas(this.mShadowBitmap);
        Path path = new Path();
        int i = this.mPadding;
        path.addRoundRect(new RectF(i, i, this.mWidth - i, this.mHeight - i), dp2px(this.mRound), dp2px(this.mRound), Path.Direction.CW);
        canvas2.drawPath(path, this.mPaintShadow);
        RenderScript create = RenderScript.create(getContext());
        ScriptIntrinsicBlur create2 = ScriptIntrinsicBlur.create(create, Element.U8(create));
        Allocation createFromBitmap = Allocation.createFromBitmap(create, this.mShadowBitmap);
        Allocation createTyped = Allocation.createTyped(create, createFromBitmap.getType());
        create2.setRadius(dp2px(30.0f));
        create2.setInput(createFromBitmap);
        create2.forEach(createTyped);
        createTyped.copyTo(this.mShadowBitmap);
        createFromBitmap.destroy();
        createTyped.destroy();
        canvas.drawBitmap(this.mShadowBitmap, 0.0f, 0.0f, (Paint) null);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        try {
            Bitmap bitmap = this.mShadowBitmap;
            if (bitmap != null) {
                bitmap.recycle();
                this.mShadowBitmap = null;
            }
        } catch (Throwable th) {
            SentryUtil.captureException(th);
            LogUtils.e(TAG, th.getMessage());
        }
    }

    public int dp2px(float f) {
        return (int) TypedValue.applyDimension(1, f, getContext().getResources().getDisplayMetrics());
    }

    public ShadowView setmPadding(int i) {
        if (i >= 10) {
            this.mPadding = dp2px(i);
        }
        return this;
    }

    @Override
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mWidth = i;
        this.mHeight = i2;
    }
}
