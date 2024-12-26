package com.tron.wallet.common.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.widget.AppCompatEditText;
import com.tron.wallet.R;
import com.tron.wallet.common.utils.UIUtils;
public class CommonTitleDescriptionEditView extends AppCompatEditText {
    private static final String TAG = "CommonTitleDescriptionEditView";
    private int baseHeight;
    private int bgColor;
    private Bitmap bitmap;
    private Bitmap bitmapActivite;
    private Bitmap bitmapEmpty;
    private Bitmap bitmapUnActivite;
    int drawableId;
    int drawableUnActiviteId;
    private int editContentGravity;
    int emptyDrawableId;
    private String hintDescription;
    private int hintDescriptionHeight;
    private int hintDescriptionTextColor;
    private float hintDescriptionTextSize;
    private String hintTitle;
    private boolean hintTitleBold;
    private int hintTitleTextColor;
    private float hintTitleTextSize;
    boolean isClearText;
    private boolean isExFocused;
    boolean isSetDrawableRight;
    private boolean isShowRightImage;
    private int leftEmptyMargin;
    private int leftLinePadding;
    private float marginTitle;
    private int paddingBottom;
    private Rect rect;
    private int rectBGColor;
    private int rectBGColorActivite;
    int rectMargin;
    int rectMarginTop;
    RightDrawableClick rightDrawableClick;
    private int rightEmptyMargin;
    private float roundedCornerRadius;
    private int strokeColor;
    private float strokeWidth;

    public interface RightDrawableClick {
        void onRightDrawableClick(View view);
    }

    public String getHintDescription() {
        return this.hintDescription;
    }

    public String getHintTitle() {
        return this.hintTitle;
    }

    public RightDrawableClick getRightDrawableClick() {
        return this.rightDrawableClick;
    }

    public boolean isExFocused() {
        return this.isExFocused;
    }

    public boolean isShowRightImage() {
        return this.isShowRightImage;
    }

    public void setExFocused(boolean z) {
        this.isExFocused = z;
    }

    public void setHintDescription(String str) {
        this.hintDescription = str;
    }

    public void setHintTitle(String str) {
        this.hintTitle = str;
    }

    public void setRightDrawableClick(RightDrawableClick rightDrawableClick) {
        this.rightDrawableClick = rightDrawableClick;
    }

    public void setRightImage(int i) {
        if (i == 0) {
            this.bitmap = this.bitmapEmpty;
        } else if (i == 1) {
            this.bitmap = this.bitmapActivite;
        } else if (i == 2) {
            this.bitmap = this.bitmapUnActivite;
        }
    }

    public void setShowRightImage(boolean z) {
        this.isShowRightImage = z;
    }

    public CommonTitleDescriptionEditView(Context context) {
        super(context);
        this.baseHeight = 0;
        this.leftLinePadding = 35;
        this.leftEmptyMargin = 10;
        this.rightEmptyMargin = 50;
        this.isClearText = true;
        this.isExFocused = false;
        this.isShowRightImage = true;
        this.rectMargin = 1;
        this.rectMarginTop = 15;
        initialize(context, null);
    }

    public CommonTitleDescriptionEditView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.baseHeight = 0;
        this.leftLinePadding = 35;
        this.leftEmptyMargin = 10;
        this.rightEmptyMargin = 50;
        this.isClearText = true;
        this.isExFocused = false;
        this.isShowRightImage = true;
        this.rectMargin = 1;
        this.rectMarginTop = 15;
        initialize(context, attributeSet);
    }

    public CommonTitleDescriptionEditView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.baseHeight = 0;
        this.leftLinePadding = 35;
        this.leftEmptyMargin = 10;
        this.rightEmptyMargin = 50;
        this.isClearText = true;
        this.isExFocused = false;
        this.isShowRightImage = true;
        this.rectMargin = 1;
        this.rectMarginTop = 15;
        initialize(context, attributeSet);
    }

    private void initialize(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CommonRoundTitleEditView);
        this.roundedCornerRadius = obtainStyledAttributes.getFloat(18, 30.0f);
        this.strokeColor = obtainStyledAttributes.getColor(19, -7829368);
        this.strokeWidth = obtainStyledAttributes.getFloat(20, 10.0f);
        this.hintTitle = obtainStyledAttributes.getString(10);
        this.hintTitleBold = obtainStyledAttributes.getBoolean(12, false);
        this.hintDescription = obtainStyledAttributes.getString(7);
        this.editContentGravity = obtainStyledAttributes.getInt(1, 0);
        this.hintDescriptionTextSize = obtainStyledAttributes.getDimension(9, 32.0f);
        this.hintTitleTextSize = obtainStyledAttributes.getDimension(14, 32.0f);
        this.marginTitle = obtainStyledAttributes.getDimension(17, 0.0f);
        this.hintDescriptionTextColor = obtainStyledAttributes.getColor(8, -7829368);
        this.hintTitleTextColor = obtainStyledAttributes.getColor(13, -7829368);
        this.rectBGColor = obtainStyledAttributes.getColor(15, getResources().getColor(com.tronlinkpro.wallet.R.color.gray_eb));
        this.rectBGColorActivite = obtainStyledAttributes.getColor(16, getResources().getColor(com.tronlinkpro.wallet.R.color.black_23));
        this.bgColor = obtainStyledAttributes.getColor(0, 0);
        this.isClearText = obtainStyledAttributes.getBoolean(6, true);
        this.isSetDrawableRight = obtainStyledAttributes.getBoolean(3, true);
        this.paddingBottom = getPaddingBottom();
    }

    @Override
    protected void onMeasure(int i, int i2) {
        Bitmap bitmap;
        StaticLayout staticLayout;
        StaticLayout.Builder obtain;
        super.onMeasure(i, i2);
        if (!TextUtils.isEmpty(this.hintTitle)) {
            new TextPaint().setTextSize(this.hintTitleTextSize);
            this.baseHeight = UIUtils.dip2px(20.0f);
        }
        if (!TextUtils.isEmpty(this.hintDescription)) {
            TextPaint textPaint = new TextPaint();
            textPaint.setTextSize(this.hintDescriptionTextSize);
            Rect rect = new Rect();
            rect.set(0, 0, UIUtils.getScreenWidth(getContext()), i2);
            String str = this.hintDescription;
            textPaint.getTextBounds(str, 0, str.length(), rect);
            if (Build.VERSION.SDK_INT >= 23) {
                String str2 = this.hintDescription;
                obtain = StaticLayout.Builder.obtain(str2, 0, str2.length() - 1, textPaint, UIUtils.getScreenWidth(getContext()) - UIUtils.dip2px(60.0f));
                staticLayout = obtain.build();
                staticLayout.getLineCount();
            } else {
                staticLayout = new StaticLayout(this.hintDescription, textPaint, UIUtils.getScreenWidth(getContext()) - UIUtils.dip2px(60.0f), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
            }
            int height = this.baseHeight + staticLayout.getHeight() + UIUtils.dip2px(2.0f);
            this.baseHeight = height;
            if (height < 20) {
                this.baseHeight = height + UIUtils.dip2px(10.0f);
            }
        }
        int width = (getLineCount() == 1 && (bitmap = this.bitmap) != null && this.isShowRightImage) ? bitmap.getWidth() + UIUtils.dip2px(15.0f) : 0;
        if (this.editContentGravity == 1) {
            int dip2px = UIUtils.dip2px(15.0f);
            int dip2px2 = (int) (UIUtils.dip2px(17.5f) + this.baseHeight + this.marginTitle);
            int dip2px3 = UIUtils.dip2px(15.0f);
            if (!this.isShowRightImage) {
                width = 0;
            }
            setPadding(dip2px, dip2px2, dip2px3 + width, UIUtils.dip2px(10.0f) + this.paddingBottom);
        } else {
            int dip2px4 = UIUtils.dip2px(15.0f);
            int dip2px5 = (int) (UIUtils.dip2px(17.5f) + this.baseHeight + this.marginTitle);
            int dip2px6 = UIUtils.dip2px(15.0f);
            if (!this.isShowRightImage) {
                width = 0;
            }
            setPadding(dip2px4, dip2px5, dip2px6 + width, UIUtils.dip2px(16.0f) + this.paddingBottom);
        }
        setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight() + (TextUtils.isEmpty(this.hintDescription) ? 0 : UIUtils.dip2px(20.0f)));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        if (isFocused() || this.isExFocused) {
            paint.setColor(this.rectBGColorActivite);
        } else {
            paint.setColor(this.rectBGColor);
        }
        canvas.drawColor(0);
        paint.setStrokeWidth(3.0f);
        paint.setStyle(Paint.Style.STROKE);
        this.leftLinePadding = getPaddingLeft();
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(this.hintTitleTextSize);
        textPaint.setColor(this.hintTitleTextColor);
        textPaint.setTypeface(this.hintTitleBold ? Typeface.create(Typeface.SANS_SERIF, 1) : Typeface.create(Typeface.SANS_SERIF, 0));
        textPaint.setAntiAlias(true);
        if (!TextUtils.isEmpty(this.hintTitle)) {
            canvas.drawText(this.hintTitle, getScrollX(), UIUtils.dip2px(14.0f), textPaint);
        }
        int dip2px = UIUtils.dip2px(8.0f);
        Path path = new Path();
        Rect rect = new Rect();
        getDrawingRect(rect);
        rect.top += UIUtils.dip2px(20.0f);
        int height = getHeight() - (UIUtils.dip2px(16.0f) + this.paddingBottom);
        path.moveTo(rect.left + this.rectMargin, rect.top + this.rectMarginTop + dip2px);
        int i = dip2px * 2;
        path.addArc(rect.left + this.rectMargin, rect.top + this.rectMarginTop, rect.left + this.rectMargin + i, rect.top + this.rectMarginTop + i, 180.0f, 90.0f);
        path.lineTo(rect.left + dip2px + this.rectMargin + UIUtils.dip2px(3.0f), rect.top + this.rectMarginTop);
        path.lineTo(rect.left + dip2px + this.rectMargin + this.leftLinePadding + ((int) new StaticLayout(this.hintTitle, textPaint, UIUtils.getScreenWidth(getContext()) - UIUtils.dip2px(70.0f), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true).getLineWidth(0)), rect.top + this.rectMarginTop);
        path.lineTo(((rect.left + getWidth()) - dip2px) - this.rectMargin, rect.top + this.rectMarginTop);
        path.addArc(((rect.left + getWidth()) - i) - this.rectMargin, rect.top + this.rectMarginTop, (rect.left + getWidth()) - this.rectMargin, rect.top + this.rectMarginTop + i, 270.0f, 90.0f);
        path.lineTo((rect.left + getWidth()) - this.rectMargin, ((rect.top + height) - dip2px) - this.rectMarginTop);
        path.addArc(((rect.left + getWidth()) - this.rectMargin) - i, ((rect.top + height) - i) - this.rectMarginTop, (rect.left + getWidth()) - this.rectMargin, (rect.top + height) - this.rectMarginTop, 0.0f, 90.0f);
        path.lineTo(rect.left + this.rectMargin + dip2px, (rect.top + height) - this.rectMarginTop);
        path.addArc(rect.left + this.rectMargin, ((rect.top + height) - i) - this.rectMarginTop, rect.left + this.rectMargin + i, (rect.top + height) - this.rectMarginTop, 90.0f, 90.0f);
        path.lineTo(rect.left + this.rectMargin, rect.top + this.rectMarginTop + dip2px);
        canvas.drawPath(path, paint);
        Paint paint2 = new Paint(1);
        paint2.setFilterBitmap(true);
        paint2.setDither(true);
        paint2.setAntiAlias(true);
        paint2.setColor(-65536);
        Bitmap bitmap = this.bitmap;
        if (bitmap != null && this.isShowRightImage) {
            int width = bitmap.getWidth();
            int height2 = this.bitmap.getHeight();
            new Rect(0, 0, width, height2);
            int deleteImageTopStart = getDeleteImageTopStart();
            this.rect = new Rect(((rect.left + getWidth()) - UIUtils.dip2px(20.0f)) - width, deleteImageTopStart, (rect.left + getWidth()) - UIUtils.dip2px(15.0f), height2 + deleteImageTopStart);
            Paint paint3 = new Paint();
            paint3.setAntiAlias(true);
            paint3.setColor(-65536);
            canvas.drawBitmap(this.bitmap, ((rect.left + getWidth()) - UIUtils.dip2px(15.0f)) - width, deleteImageTopStart, paint2);
        }
        if (!TextUtils.isEmpty(this.hintDescription)) {
            textPaint.setColor(this.hintDescriptionTextColor);
            textPaint.setTextSize(this.hintDescriptionTextSize);
            textPaint.setAntiAlias(true);
            StaticLayout staticLayout = new StaticLayout(this.hintDescription, textPaint, UIUtils.getScreenWidth(getContext()) - UIUtils.dip2px(70.0f), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
            canvas.save();
            canvas.translate(rect.left + this.leftLinePadding, rect.top + UIUtils.dip2px(15.0f));
            staticLayout.draw(canvas);
            this.hintDescriptionHeight = staticLayout.getHeight();
            canvas.restore();
        }
        super.onDraw(canvas);
    }

    private int getDeleteImageTopStart() {
        int dip2px = UIUtils.dip2px(20.0f);
        Bitmap bitmap = this.bitmap;
        if (bitmap != null) {
            dip2px = bitmap.getHeight();
        }
        if (getLineCount() == 1) {
            if (this.baseHeight == 0) {
                return (getHeight() / 2) - (dip2px / 2);
            }
            return ((((getHeight() - getPaddingTop()) - getPaddingBottom()) / 2) - (dip2px / 2)) + getPaddingTop();
        }
        return (getMeasuredHeight() - getCompoundPaddingBottom()) - dip2px;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            motionEvent.getX();
            motionEvent.getY();
        } else if (action == 1) {
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            Rect rect = new Rect();
            getDrawingRect(rect);
            Rect rect2 = this.rect;
            if (rect2 != null && rect2.contains(rect.left + x, rect.top + y)) {
                RightDrawableClick rightDrawableClick = this.rightDrawableClick;
                if (rightDrawableClick != null) {
                    rightDrawableClick.onRightDrawableClick(this);
                } else if (this.isClearText) {
                    setText("");
                }
            }
        } else if (action == 2) {
            motionEvent.getX();
            motionEvent.getY();
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i + UIUtils.dip2px(15.0f), i2 + UIUtils.dip2px(15.0f), i3, i4);
    }

    public static int getTextWidth(Paint paint, String str) {
        if (str == null || str.length() <= 0) {
            return 0;
        }
        int length = str.length();
        float[] fArr = new float[length];
        paint.getTextWidths(str, fArr);
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            i = (int) (i + Math.ceil(fArr[i2]));
        }
        return i;
    }

    public void setRightImageResId(int i) {
        if (i == 0) {
            this.bitmap = null;
        } else {
            this.bitmap = ((BitmapDrawable) getResources().getDrawable(i)).getBitmap();
        }
    }
}
