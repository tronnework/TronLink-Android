package com.tron.wallet.common.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.tron.wallet.R;
import java.math.BigDecimal;
public class TronRatingBar extends LinearLayout {
    private boolean mClickable;
    private OnRatingChangeListener onRatingChangeListener;
    private int starCount;
    private Drawable starEmptyDrawable;
    private Drawable starFillDrawable;
    private Drawable starHalfDrawable;
    private float starImageSize;
    private float starPadding;
    private float starStep;
    private StepSize stepSize;

    public interface OnRatingChangeListener {
        void onRatingChange(float f);
    }

    @Override
    public void setClickable(boolean z) {
        this.mClickable = z;
    }

    public void setOnRatingChangeListener(OnRatingChangeListener onRatingChangeListener) {
        this.onRatingChangeListener = onRatingChangeListener;
    }

    public void setStarEmptyDrawable(Drawable drawable) {
        this.starEmptyDrawable = drawable;
    }

    public void setStarFillDrawable(Drawable drawable) {
        this.starFillDrawable = drawable;
    }

    public void setStarHalfDrawable(Drawable drawable) {
        this.starHalfDrawable = drawable;
    }

    public void setStarImageSize(float f) {
        this.starImageSize = f;
    }

    public void setStepSize(StepSize stepSize) {
        this.stepSize = stepSize;
    }

    public TronRatingBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOrientation(0);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RatingBar);
        this.starImageSize = obtainStyledAttributes.getDimension(5, 20.0f);
        this.starPadding = obtainStyledAttributes.getDimension(6, 10.0f);
        this.starStep = obtainStyledAttributes.getFloat(7, 1.0f);
        this.stepSize = StepSize.fromStep(obtainStyledAttributes.getInt(8, 1));
        this.starCount = obtainStyledAttributes.getInteger(1, 5);
        this.starEmptyDrawable = obtainStyledAttributes.getDrawable(2);
        this.starFillDrawable = obtainStyledAttributes.getDrawable(3);
        this.starHalfDrawable = obtainStyledAttributes.getDrawable(4);
        this.mClickable = obtainStyledAttributes.getBoolean(0, true);
        obtainStyledAttributes.recycle();
        for (int i = 0; i < this.starCount; i++) {
            final ImageView starImageView = getStarImageView();
            starImageView.setImageDrawable(this.starEmptyDrawable);
            starImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TronRatingBar tronRatingBar;
                    TronRatingBar tronRatingBar2;
                    TronRatingBar tronRatingBar3;
                    TronRatingBar tronRatingBar4;
                    if (mClickable) {
                        int i2 = (int) starStep;
                        if (new BigDecimal(Float.toString(starStep)).subtract(new BigDecimal(Integer.toString(i2))).floatValue() == 0.0f) {
                            i2--;
                        }
                        if (indexOfChild(view) > i2) {
                            setStar(tronRatingBar4.indexOfChild(view) + 1);
                        } else if (indexOfChild(view) == i2) {
                            if (stepSize == StepSize.Full) {
                                return;
                            }
                            if (starImageView.getDrawable().getCurrent().getConstantState().equals(starHalfDrawable.getConstantState())) {
                                setStar(tronRatingBar3.indexOfChild(view) + 1);
                                return;
                            }
                            setStar(tronRatingBar2.indexOfChild(view) + 0.5f);
                        } else {
                            setStar(tronRatingBar.indexOfChild(view) + 1.0f);
                        }
                    }
                }
            });
            addView(starImageView);
        }
        setStar(this.starStep);
    }

    private ImageView getStarImageView() {
        ImageView imageView = new ImageView(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Math.round(this.starImageSize), Math.round(this.starImageSize));
        layoutParams.setMargins(0, 0, Math.round(this.starPadding), 0);
        imageView.setLayoutParams(layoutParams);
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageDrawable(this.starEmptyDrawable);
        imageView.setMinimumWidth(10);
        imageView.setMaxHeight(10);
        return imageView;
    }

    public void setStar(float f) {
        OnRatingChangeListener onRatingChangeListener = this.onRatingChangeListener;
        if (onRatingChangeListener != null) {
            onRatingChangeListener.onRatingChange(f);
        }
        this.starStep = f;
        int i = (int) f;
        float floatValue = new BigDecimal(Float.toString(f)).subtract(new BigDecimal(Integer.toString(i))).floatValue();
        for (int i2 = 0; i2 < i; i2++) {
            ((ImageView) getChildAt(i2)).setImageDrawable(this.starFillDrawable);
        }
        for (int i3 = i; i3 < this.starCount; i3++) {
            ((ImageView) getChildAt(i3)).setImageDrawable(this.starEmptyDrawable);
        }
        if (floatValue > 0.0f) {
            ((ImageView) getChildAt(i)).setImageDrawable(this.starHalfDrawable);
        }
    }

    public enum StepSize {
        Half(0),
        Full(1);
        
        int step;

        StepSize(int i) {
            this.step = i;
        }

        public static StepSize fromStep(int i) {
            StepSize[] values;
            for (StepSize stepSize : values()) {
                if (stepSize.step == i) {
                    return stepSize;
                }
            }
            throw new IllegalArgumentException();
        }
    }
}
