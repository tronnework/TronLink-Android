package com.tron.wallet.common.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.ViewCompat;
import com.lxj.xpopup.util.XPopupUtils;
public class DashUnderLineTextView extends AppCompatTextView {
    private DashPathEffect dashPathEffect;
    private final float[] dashWidths;
    private boolean enableUnderDashLine;
    private Paint paint;
    private Path path;
    private final int size;

    public boolean isEnableUnderDashLine() {
        return this.enableUnderDashLine;
    }

    public DashUnderLineTextView(Context context) {
        super(context);
        this.enableUnderDashLine = false;
        int dp2px = XPopupUtils.dp2px(getContext(), 2.0f);
        this.size = dp2px;
        this.dashWidths = new float[]{dp2px, dp2px};
    }

    public DashUnderLineTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.enableUnderDashLine = false;
        int dp2px = XPopupUtils.dp2px(getContext(), 2.0f);
        this.size = dp2px;
        this.dashWidths = new float[]{dp2px, dp2px};
    }

    public DashUnderLineTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.enableUnderDashLine = false;
        int dp2px = XPopupUtils.dp2px(getContext(), 2.0f);
        this.size = dp2px;
        this.dashWidths = new float[]{dp2px, dp2px};
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.enableUnderDashLine) {
            canvas.save();
            if (this.paint == null) {
                this.paint = new Paint();
            }
            if (this.dashPathEffect == null) {
                this.dashPathEffect = new DashPathEffect(this.dashWidths, 0.0f);
            }
            if (this.path == null) {
                this.path = new Path();
            }
            float measuredHeight = getMeasuredHeight();
            this.path.reset();
            this.path.moveTo(0.0f, measuredHeight);
            this.path.lineTo(getMeasuredWidth(), measuredHeight);
            this.paint.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.paint.setStyle(Paint.Style.STROKE);
            this.paint.setStrokeWidth(this.size);
            this.paint.setPathEffect(this.dashPathEffect);
            this.paint.setAntiAlias(true);
            canvas.drawPath(this.path, this.paint);
            canvas.restore();
        }
    }

    public void setEnableUnderDashLine(boolean z) {
        this.enableUnderDashLine = z;
        invalidate();
    }
}
