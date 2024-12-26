package com.tron.wallet.common.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.DynamicLayout;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.R;
import java.lang.reflect.Field;
import org.apache.commons.lang3.StringUtils;
public class SpreadTextView extends AppCompatTextView {
    private static final String CLASS_NAME_LISTENER_INFO = "android.view.View$ListenerInfo";
    private static final String CLASS_NAME_VIEW = "android.view.View";
    private static final String ELLIPSIS_HINT = "..";
    private static final String GAP_TO_EXPAND_HINT = " ";
    private static final String GAP_TO_SHRINK_HINT = " ";
    private static final int MAX_LINES_ON_SHRINK = 2;
    private static final boolean SHOW_TO_EXPAND_HINT = true;
    private static final boolean SHOW_TO_SHRINK_HINT = true;
    public static final int STATE_EXPAND = 1;
    public static final int STATE_SHRINK = 0;
    private static final boolean TOGGLE_ENABLE = true;
    private static final int TO_EXPAND_HINT_COLOR = -9275195;
    private static final int TO_EXPAND_HINT_COLOR_BG_PRESSED = 1436129689;
    private static final int TO_SHRINK_HINT_COLOR = -9275195;
    private static final int TO_SHRINK_HINT_COLOR_BG_PRESSED = 1436129689;
    private TextView.BufferType mBufferType;
    private int mCurrState;
    private String mEllipsisHint;
    private ExpandableClickListener mExpandableClickListener;
    private int mFutureTextViewWidth;
    private String mGapToExpandHint;
    private String mGapToShrinkHint;
    private Layout mLayout;
    private int mLayoutWidth;
    private int mMaxLinesOnShrink;
    private OnExpandListener mOnExpandListener;
    private CharSequence mOrigText;
    private boolean mShowToExpandHint;
    private boolean mShowToShrinkHint;
    private int mTextLineCount;
    private TextPaint mTextPaint;
    private String mToExpandHint;
    private int mToExpandHintColor;
    private int mToExpandHintColorBgPressed;
    private String mToShrinkHint;
    private int mToShrinkHintColor;
    private int mToShrinkHintColorBgPressed;
    private boolean mToggleEnable;
    private TouchableSpan mTouchableSpan;

    public interface OnExpandListener {
        void onExpand(SpreadTextView spreadTextView);

        void onShrink(SpreadTextView spreadTextView);
    }

    private String getContentOfString(String str) {
        return str == null ? "" : str;
    }

    public int getExpandState() {
        return this.mCurrState;
    }

    public int getmTextLineCount() {
        return this.mTextLineCount;
    }

    public void setExpandListener(OnExpandListener onExpandListener) {
        this.mOnExpandListener = onExpandListener;
    }

    public SpreadTextView(Context context) {
        super(context);
        this.mGapToExpandHint = " ";
        this.mGapToShrinkHint = " ";
        this.mToggleEnable = true;
        this.mShowToExpandHint = true;
        this.mShowToShrinkHint = true;
        this.mMaxLinesOnShrink = 2;
        this.mToExpandHintColor = -9275195;
        this.mToShrinkHintColor = -9275195;
        this.mToExpandHintColorBgPressed = 1436129689;
        this.mToShrinkHintColorBgPressed = 1436129689;
        this.mCurrState = 0;
        this.mBufferType = TextView.BufferType.NORMAL;
        this.mTextLineCount = -1;
        this.mLayoutWidth = 0;
        this.mFutureTextViewWidth = 0;
        init();
    }

    public SpreadTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mGapToExpandHint = " ";
        this.mGapToShrinkHint = " ";
        this.mToggleEnable = true;
        this.mShowToExpandHint = true;
        this.mShowToShrinkHint = true;
        this.mMaxLinesOnShrink = 2;
        this.mToExpandHintColor = -9275195;
        this.mToShrinkHintColor = -9275195;
        this.mToExpandHintColorBgPressed = 1436129689;
        this.mToShrinkHintColorBgPressed = 1436129689;
        this.mCurrState = 0;
        this.mBufferType = TextView.BufferType.NORMAL;
        this.mTextLineCount = -1;
        this.mLayoutWidth = 0;
        this.mFutureTextViewWidth = 0;
        initAttr(context, attributeSet);
        init();
    }

    public SpreadTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mGapToExpandHint = " ";
        this.mGapToShrinkHint = " ";
        this.mToggleEnable = true;
        this.mShowToExpandHint = true;
        this.mShowToShrinkHint = true;
        this.mMaxLinesOnShrink = 2;
        this.mToExpandHintColor = -9275195;
        this.mToShrinkHintColor = -9275195;
        this.mToExpandHintColorBgPressed = 1436129689;
        this.mToShrinkHintColorBgPressed = 1436129689;
        this.mCurrState = 0;
        this.mBufferType = TextView.BufferType.NORMAL;
        this.mTextLineCount = -1;
        this.mLayoutWidth = 0;
        this.mFutureTextViewWidth = 0;
        initAttr(context, attributeSet);
        init();
    }

    private void initAttr(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes;
        if (attributeSet == null || (obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SpreadTextView)) == null) {
            return;
        }
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = obtainStyledAttributes.getIndex(i);
            if (index == 5) {
                this.mMaxLinesOnShrink = obtainStyledAttributes.getInteger(index, 2);
            } else if (index == 0) {
                this.mEllipsisHint = obtainStyledAttributes.getString(index);
            } else if (index == 6) {
                this.mToExpandHint = obtainStyledAttributes.getString(index);
            } else if (index == 10) {
                this.mToShrinkHint = obtainStyledAttributes.getString(index);
            } else if (index == 1) {
                this.mToggleEnable = obtainStyledAttributes.getBoolean(index, true);
            } else if (index == 9) {
                this.mShowToExpandHint = obtainStyledAttributes.getBoolean(index, true);
            } else if (index == 13) {
                this.mShowToShrinkHint = obtainStyledAttributes.getBoolean(index, true);
            } else if (index == 7) {
                this.mToExpandHintColor = obtainStyledAttributes.getInteger(index, -9275195);
            } else if (index == 11) {
                this.mToShrinkHintColor = obtainStyledAttributes.getInteger(index, -9275195);
            } else if (index == 8) {
                this.mToExpandHintColorBgPressed = obtainStyledAttributes.getInteger(index, 1436129689);
            } else if (index == 12) {
                this.mToShrinkHintColorBgPressed = obtainStyledAttributes.getInteger(index, 1436129689);
            } else if (index == 4) {
                this.mCurrState = obtainStyledAttributes.getInteger(index, 0);
            } else if (index == 2) {
                this.mGapToExpandHint = obtainStyledAttributes.getString(index);
            } else if (index == 3) {
                this.mGapToShrinkHint = obtainStyledAttributes.getString(index);
            }
        }
        obtainStyledAttributes.recycle();
    }

    private void init() {
        this.mTouchableSpan = new TouchableSpan();
        setMovementMethod(new LinkTouchMovementMethod());
        if (TextUtils.isEmpty(this.mEllipsisHint)) {
            this.mEllipsisHint = ELLIPSIS_HINT;
        }
        if (TextUtils.isEmpty(this.mToExpandHint)) {
            this.mToExpandHint = getResources().getString(com.tronlinkpro.wallet.R.string.to_expand_hint);
        }
        if (TextUtils.isEmpty(this.mToShrinkHint)) {
            this.mToShrinkHint = getResources().getString(com.tronlinkpro.wallet.R.string.to_shrink_hint);
        }
        if (this.mToggleEnable) {
            ExpandableClickListener expandableClickListener = new ExpandableClickListener();
            this.mExpandableClickListener = expandableClickListener;
            setOnClickListener(expandableClickListener);
        }
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                SpreadTextView spreadTextView = SpreadTextView.this;
                spreadTextView.setTextInternal(spreadTextView.getNewTextByConfig(), mBufferType);
            }
        });
    }

    public void updateForRecyclerView(CharSequence charSequence, int i, int i2) {
        this.mFutureTextViewWidth = i;
        this.mCurrState = i2;
        setText(charSequence);
    }

    public void updateForRecyclerView(CharSequence charSequence, TextView.BufferType bufferType, int i) {
        this.mFutureTextViewWidth = i;
        setText(charSequence, bufferType);
    }

    public void updateForRecyclerView(CharSequence charSequence, int i) {
        this.mFutureTextViewWidth = i;
        setText(charSequence);
    }

    public CharSequence getNewTextByConfig() {
        String str;
        int i;
        int i2;
        int i3;
        if (TextUtils.isEmpty(this.mOrigText)) {
            return this.mOrigText;
        }
        Layout layout = getLayout();
        this.mLayout = layout;
        if (layout != null) {
            this.mLayoutWidth = layout.getWidth();
        }
        if (this.mLayoutWidth <= 0) {
            if (getWidth() == 0) {
                int i4 = this.mFutureTextViewWidth;
                if (i4 == 0) {
                    return this.mOrigText;
                }
                this.mLayoutWidth = (i4 - getPaddingLeft()) - getPaddingRight();
            } else {
                this.mLayoutWidth = (getWidth() - getPaddingLeft()) - getPaddingRight();
            }
        }
        this.mTextPaint = getPaint();
        this.mTextLineCount = -1;
        int i5 = this.mCurrState;
        if (i5 != 0) {
            if (i5 == 1 && this.mShowToShrinkHint) {
                DynamicLayout dynamicLayout = new DynamicLayout(this.mOrigText, this.mTextPaint, this.mLayoutWidth, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
                this.mLayout = dynamicLayout;
                int lineCount = dynamicLayout.getLineCount();
                this.mTextLineCount = lineCount;
                if (lineCount <= this.mMaxLinesOnShrink) {
                    return this.mOrigText;
                }
                SpannableStringBuilder append = new SpannableStringBuilder(this.mOrigText).append((CharSequence) this.mGapToShrinkHint).append((CharSequence) this.mToShrinkHint);
                append.setSpan(this.mTouchableSpan, append.length() - getLengthOfString(this.mToShrinkHint), append.length(), 33);
                return append;
            }
            return this.mOrigText;
        }
        DynamicLayout dynamicLayout2 = new DynamicLayout(this.mOrigText, this.mTextPaint, this.mLayoutWidth, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        this.mLayout = dynamicLayout2;
        int lineCount2 = dynamicLayout2.getLineCount();
        this.mTextLineCount = lineCount2;
        if (lineCount2 <= this.mMaxLinesOnShrink) {
            return this.mOrigText;
        }
        int lineEnd = getValidLayout().getLineEnd(this.mMaxLinesOnShrink - 1);
        int lineStart = getValidLayout().getLineStart(this.mMaxLinesOnShrink - 1);
        int lengthOfString = (lineEnd - getLengthOfString(this.mEllipsisHint)) - (this.mShowToExpandHint ? getLengthOfString(this.mToExpandHint) + getLengthOfString(this.mGapToExpandHint) : 0);
        if (lengthOfString > lineStart) {
            lineEnd = lengthOfString;
        }
        int width = getValidLayout().getWidth() - ((int) (this.mTextPaint.measureText(this.mOrigText.subSequence(lineStart, lineEnd).toString()) + 0.5d));
        TextPaint textPaint = this.mTextPaint;
        StringBuilder sb = new StringBuilder();
        sb.append(getContentOfString(this.mEllipsisHint));
        if (this.mShowToExpandHint) {
            str = getContentOfString(this.mToExpandHint) + getContentOfString(this.mGapToExpandHint);
        } else {
            str = "";
        }
        sb.append(str);
        float measureText = textPaint.measureText(sb.toString());
        float f = width;
        if (f > measureText) {
            int i6 = 0;
            int i7 = 0;
            while (f > i6 + measureText && (i3 = lineEnd + (i7 = i7 + 1)) <= this.mOrigText.length()) {
                i6 = (int) (this.mTextPaint.measureText(this.mOrigText.subSequence(lineEnd, i3).toString()) + 0.5d);
            }
            i = lineEnd + (i7 - 1);
        } else {
            int i8 = 0;
            int i9 = 0;
            while (i8 + width < measureText && (i2 = lineEnd + (i9 - 1)) > lineStart) {
                i8 = (int) (this.mTextPaint.measureText(this.mOrigText.subSequence(i2, lineEnd).toString()) + 0.5d);
            }
            i = lineEnd + i9;
        }
        SpannableStringBuilder append2 = new SpannableStringBuilder(removeEndLineBreak(this.mOrigText.subSequence(0, i))).append((CharSequence) this.mEllipsisHint);
        if (this.mShowToExpandHint) {
            append2.append((CharSequence) (getContentOfString(this.mGapToExpandHint) + getContentOfString(this.mToExpandHint)));
            append2.setSpan(this.mTouchableSpan, append2.length() - getLengthOfString(this.mToExpandHint), append2.length(), 33);
        }
        return append2;
    }

    private String removeEndLineBreak(CharSequence charSequence) {
        String charSequence2 = charSequence.toString();
        while (charSequence2.endsWith(StringUtils.LF)) {
            charSequence2 = charSequence2.substring(0, charSequence2.length() - 1);
        }
        return charSequence2;
    }

    private Layout getValidLayout() {
        Layout layout = this.mLayout;
        return layout != null ? layout : getLayout();
    }

    public void toggle() {
        int i = this.mCurrState;
        if (i == 0) {
            this.mCurrState = 1;
            OnExpandListener onExpandListener = this.mOnExpandListener;
            if (onExpandListener != null) {
                onExpandListener.onExpand(this);
            }
        } else if (i == 1) {
            this.mCurrState = 0;
            OnExpandListener onExpandListener2 = this.mOnExpandListener;
            if (onExpandListener2 != null) {
                onExpandListener2.onShrink(this);
            }
        }
        setTextInternal(getNewTextByConfig(), this.mBufferType);
    }

    @Override
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        this.mOrigText = charSequence;
        this.mBufferType = bufferType;
        setTextInternal(getNewTextByConfig(), bufferType);
    }

    public void setTextInternal(CharSequence charSequence, TextView.BufferType bufferType) {
        super.setText(charSequence, bufferType);
    }

    private int getLengthOfString(String str) {
        if (str == null) {
            return 0;
        }
        return str.length();
    }

    public class ExpandableClickListener implements View.OnClickListener {
        private ExpandableClickListener() {
        }

        @Override
        public void onClick(View view) {
            toggle();
        }
    }

    public View.OnClickListener getOnClickListener(View view) {
        return getOnClickListenerV14(view);
    }

    private View.OnClickListener getOnClickListenerV(View view) {
        try {
            Field declaredField = Class.forName(CLASS_NAME_VIEW).getDeclaredField("mOnClickListener");
            declaredField.setAccessible(true);
            return (View.OnClickListener) declaredField.get(view);
        } catch (Exception e) {
            LogUtils.e(e);
            return null;
        }
    }

    private View.OnClickListener getOnClickListenerV14(View view) {
        Object obj;
        try {
            Field declaredField = Class.forName(CLASS_NAME_VIEW).getDeclaredField("mListenerInfo");
            if (declaredField != null) {
                declaredField.setAccessible(true);
                obj = declaredField.get(view);
            } else {
                obj = null;
            }
            Field declaredField2 = Class.forName(CLASS_NAME_LISTENER_INFO).getDeclaredField("mOnClickListener");
            if (declaredField2 == null || obj == null) {
                return null;
            }
            declaredField2.setAccessible(true);
            return (View.OnClickListener) declaredField2.get(obj);
        } catch (Exception e) {
            LogUtils.e(e);
            return null;
        }
    }

    public class TouchableSpan extends ClickableSpan {
        private boolean mIsPressed;

        public void setPressed(boolean z) {
            this.mIsPressed = z;
        }

        private TouchableSpan() {
        }

        @Override
        public void onClick(View view) {
            if (hasOnClickListeners()) {
                SpreadTextView spreadTextView = SpreadTextView.this;
                if (spreadTextView.getOnClickListener(spreadTextView) instanceof ExpandableClickListener) {
                    return;
                }
            }
            toggle();
        }

        @Override
        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            int i = mCurrState;
            if (i == 0) {
                textPaint.setColor(mToExpandHintColor);
                textPaint.bgColor = this.mIsPressed ? mToExpandHintColorBgPressed : 0;
            } else if (i == 1) {
                textPaint.setColor(mToShrinkHintColor);
                textPaint.bgColor = this.mIsPressed ? mToShrinkHintColorBgPressed : 0;
            }
            textPaint.setUnderlineText(false);
        }
    }

    public class LinkTouchMovementMethod extends LinkMovementMethod {
        private TouchableSpan mPressedSpan;

        public LinkTouchMovementMethod() {
        }

        @Override
        public boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                TouchableSpan pressedSpan = getPressedSpan(textView, spannable, motionEvent);
                this.mPressedSpan = pressedSpan;
                if (pressedSpan != null) {
                    pressedSpan.setPressed(true);
                    Selection.setSelection(spannable, spannable.getSpanStart(this.mPressedSpan), spannable.getSpanEnd(this.mPressedSpan));
                }
            } else if (motionEvent.getAction() == 2) {
                TouchableSpan pressedSpan2 = getPressedSpan(textView, spannable, motionEvent);
                TouchableSpan touchableSpan = this.mPressedSpan;
                if (touchableSpan != null && pressedSpan2 != touchableSpan) {
                    touchableSpan.setPressed(false);
                    this.mPressedSpan = null;
                    Selection.removeSelection(spannable);
                }
            } else {
                TouchableSpan touchableSpan2 = this.mPressedSpan;
                if (touchableSpan2 != null) {
                    touchableSpan2.setPressed(false);
                    super.onTouchEvent(textView, spannable, motionEvent);
                }
                this.mPressedSpan = null;
                Selection.removeSelection(spannable);
            }
            return true;
        }

        private TouchableSpan getPressedSpan(TextView textView, Spannable spannable, MotionEvent motionEvent) {
            int x = ((int) motionEvent.getX()) - textView.getTotalPaddingLeft();
            int y = ((int) motionEvent.getY()) - textView.getTotalPaddingTop();
            int scrollX = x + textView.getScrollX();
            Layout layout = textView.getLayout();
            int offsetForHorizontal = layout.getOffsetForHorizontal(layout.getLineForVertical(y + textView.getScrollY()), scrollX);
            TouchableSpan[] touchableSpanArr = (TouchableSpan[]) spannable.getSpans(offsetForHorizontal, offsetForHorizontal, TouchableSpan.class);
            if (touchableSpanArr.length > 0) {
                return touchableSpanArr[0];
            }
            return null;
        }
    }
}
