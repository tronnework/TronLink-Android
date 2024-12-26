package com.tron.wallet.common.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.Layout;
import android.text.SpannableString;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatTextView;
import com.tron.wallet.R;
import org.apache.commons.lang3.StringUtils;
public class ExpandableTextView extends AppCompatTextView {
    private static final String ELLIPSE = "...  ";
    private boolean canShrink;
    private String expandSuffix;
    private boolean expanded;
    private View.OnClickListener l;
    private StaticLayout mLayout;
    private int maxLines;
    private CharSequence originalText;
    private String shrinkSuffix;
    private int suffixColor;

    public void setCanShrink(boolean z) {
        this.canShrink = z;
    }

    public void setExpandSuffix(String str) {
        this.expandSuffix = str;
    }

    public void setShrinkSuffix(String str) {
        this.shrinkSuffix = str;
    }

    public ExpandableTextView(Context context) {
        this(context, null);
    }

    public ExpandableTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ExpandableTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.expandSuffix = "...More";
        this.expanded = false;
        this.canShrink = true;
        getAttrs(context, attributeSet, i);
        this.originalText = getText();
        this.maxLines = getMaxLines();
    }

    public void setOriginalText(String str) {
        this.originalText = str;
        setText(str);
        requestLayout();
    }

    private void getAttrs(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ExpandableTextView);
        setCanShrink(obtainStyledAttributes.getBoolean(0, false));
        String string = obtainStyledAttributes.getString(1);
        setExpandSuffix(ELLIPSE + string);
        setShrinkSuffix(obtainStyledAttributes.getString(2));
        this.suffixColor = obtainStyledAttributes.getColor(3, getContext().getResources().getColor(17170444));
        obtainStyledAttributes.recycle();
    }

    @Override
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        measureText();
    }

    private void measureText() {
        StaticLayout.Builder obtain;
        StaticLayout build;
        int measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        if (Build.VERSION.SDK_INT >= 23) {
            CharSequence charSequence = this.originalText;
            obtain = StaticLayout.Builder.obtain(charSequence, 0, charSequence.length(), getPaint(), measuredWidth);
            build = obtain.build();
            this.mLayout = build;
        } else {
            this.mLayout = new StaticLayout(this.originalText, getPaint(), measuredWidth, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, true);
        }
        int lineCount = this.mLayout.getLineCount();
        if (this.expanded) {
            setMaxLines(lineCount);
            Object[] objArr = new Object[2];
            objArr[0] = this.originalText;
            objArr[1] = this.canShrink ? this.shrinkSuffix : "";
            String format = String.format("%s%s", objArr);
            setText(getSpannable(format, this.originalText.length(), format.length()));
            return;
        }
        setMaxLines(this.maxLines);
        if (lineCount <= getMaxLines()) {
            setText(this.originalText);
            return;
        }
        int lineStart = this.mLayout.getLineStart(getMaxLines() - 1);
        CharSequence charSequence2 = "";
        for (int lineEnd = this.mLayout.getLineEnd(getMaxLines() - 1); lineEnd > lineStart; lineEnd--) {
            charSequence2 = this.originalText.subSequence(lineStart, lineEnd);
            if (getPaint().measureText(charSequence2.toString() + this.expandSuffix) <= measuredWidth) {
                break;
            }
        }
        if (charSequence2.toString().contains(StringUtils.LF)) {
            charSequence2 = charSequence2.toString().replace(StringUtils.LF, "");
        }
        String format2 = String.format("%s%s%s", this.originalText.subSequence(0, lineStart), charSequence2, this.expandSuffix);
        setText(getSpannable(format2, (format2.length() - this.expandSuffix.length()) + 5, format2.length()));
    }

    public void toggleExpand(boolean z) {
        if (!this.expanded) {
            this.expanded = true;
            requestLayout();
        } else if (z) {
            this.expanded = false;
            requestLayout();
        }
    }

    private SpannableString getSpannable(String str, int i, int i2) {
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new ForegroundColorSpan(this.suffixColor), i, i2, 34);
        spannableString.setSpan(new ExpandableClickSpan(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$getSpannable$0(view);
            }
        }), i, i2, 18);
        setMovementMethod(LinkMovementMethod.getInstance());
        return spannableString;
    }

    public void lambda$getSpannable$0(View view) {
        toggleExpand(this.canShrink);
    }

    public static final class ExpandableClickSpan extends ClickableSpan {
        private final View.OnClickListener listener;

        @Override
        public void updateDrawState(TextPaint textPaint) {
        }

        public ExpandableClickSpan(View.OnClickListener onClickListener) {
            this.listener = onClickListener;
        }

        @Override
        public void onClick(View view) {
            View.OnClickListener onClickListener = this.listener;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }
}
