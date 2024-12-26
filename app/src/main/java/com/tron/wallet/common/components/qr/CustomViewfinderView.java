package com.tron.wallet.common.components.qr;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.TypedValue;
import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.ViewfinderView;
import java.util.ArrayList;
import java.util.List;
public class CustomViewfinderView extends ViewfinderView {
    public static final long CUSTOME_ANIMATION_DELAY = 16;
    public int mLineColor;
    public float mLineDepth;
    public float mLineRate;
    public LinearGradient mLinearGradient;
    public float[] mPositions;
    public int[] mScanLineColor;
    public float mScanLineDepth;
    public float mScanLineDy;
    public int mScanLinePosition;

    public CustomViewfinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mLineRate = 0.075f;
        this.mLineDepth = TypedValue.applyDimension(1, 3.0f, getResources().getDisplayMetrics());
        this.mLineColor = -15508019;
        this.mScanLinePosition = 0;
        this.mScanLineDepth = TypedValue.applyDimension(1, 2.0f, getResources().getDisplayMetrics());
        this.mScanLineDy = TypedValue.applyDimension(1, 3.0f, getResources().getDisplayMetrics());
        this.mPositions = new float[]{0.0f, 0.5f, 1.0f};
        this.mScanLineColor = new int[]{258173644, -15508019, 6515404};
    }

    @Override
    public void onDraw(Canvas canvas) {
        refreshSizes();
        if (this.framingRect == null || this.framingRect == null) {
            return;
        }
        Rect rect = this.framingRect;
        Rect rect2 = this.framingRect;
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        this.paint.setColor(this.mLineColor);
        canvas.drawRect(rect.left, rect.top, (rect.width() * this.mLineRate) + rect.left, this.mLineDepth + rect.top, this.paint);
        canvas.drawRect(rect.left, rect.top, this.mLineDepth + rect.left, (rect.height() * this.mLineRate) + rect.top, this.paint);
        canvas.drawRect(rect.right - (rect.width() * this.mLineRate), rect.top, rect.right, this.mLineDepth + rect.top, this.paint);
        canvas.drawRect(rect.right - this.mLineDepth, rect.top, rect.right, (rect.height() * this.mLineRate) + rect.top, this.paint);
        canvas.drawRect(rect.left, rect.bottom - this.mLineDepth, (rect.width() * this.mLineRate) + rect.left, rect.bottom, this.paint);
        canvas.drawRect(rect.left, rect.bottom - (rect.height() * this.mLineRate), this.mLineDepth + rect.left, rect.bottom, this.paint);
        canvas.drawRect(rect.right - (rect.width() * this.mLineRate), rect.bottom - this.mLineDepth, rect.right, rect.bottom, this.paint);
        canvas.drawRect(rect.right - this.mLineDepth, rect.bottom - (rect.height() * this.mLineRate), rect.right, rect.bottom, this.paint);
        this.paint.setColor(this.resultBitmap != null ? this.resultColor : this.maskColor);
        float f = width;
        canvas.drawRect(0.0f, 0.0f, f, rect.top, this.paint);
        canvas.drawRect(0.0f, rect.top, rect.left, rect.bottom + 1, this.paint);
        canvas.drawRect(rect.right + 1, rect.top, f, rect.bottom + 1, this.paint);
        canvas.drawRect(0.0f, rect.bottom + 1, f, height, this.paint);
        if (this.resultBitmap != null) {
            this.paint.setAlpha(160);
            canvas.drawBitmap(this.resultBitmap, (Rect) null, rect, this.paint);
        } else {
            int i = (int) (this.mScanLinePosition + this.mScanLineDy);
            this.mScanLinePosition = i;
            if (i > rect.height()) {
                this.mScanLinePosition = 0;
            }
            this.paint.setColor(this.mLineColor);
            this.mLinearGradient = new LinearGradient(rect.left, rect.top + this.mScanLinePosition, rect.right, rect.top + this.mScanLinePosition, this.mScanLineColor, this.mPositions, Shader.TileMode.CLAMP);
            this.paint.setShader(this.mLinearGradient);
            canvas.drawRect(rect.left, rect.top + this.mScanLinePosition, rect.right, this.mScanLineDepth + rect.top + this.mScanLinePosition, this.paint);
            this.paint.setShader(null);
            float width2 = rect.width() / rect2.width();
            float height2 = rect.height() / rect2.height();
            List<ResultPoint> list = this.possibleResultPoints;
            List<ResultPoint> list2 = this.lastPossibleResultPoints;
            int i2 = rect.left;
            int i3 = rect.top;
            if (list.isEmpty()) {
                this.lastPossibleResultPoints = null;
            } else {
                this.possibleResultPoints = new ArrayList(5);
                this.lastPossibleResultPoints = list;
                this.paint.setAlpha(160);
                this.paint.setColor(this.resultPointColor);
                for (ResultPoint resultPoint : list) {
                    canvas.drawCircle(((int) (resultPoint.getX() * width2)) + i2, ((int) (resultPoint.getY() * height2)) + i3, 6.0f, this.paint);
                }
            }
            if (list2 != null) {
                this.paint.setAlpha(80);
                this.paint.setColor(this.resultPointColor);
                for (ResultPoint resultPoint2 : list2) {
                    canvas.drawCircle(((int) (resultPoint2.getX() * width2)) + i2, ((int) (resultPoint2.getY() * height2)) + i3, 3.0f, this.paint);
                }
            }
        }
        postInvalidateDelayed(16L, rect.left, rect.top, rect.right, rect.bottom);
    }
}
