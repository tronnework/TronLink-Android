package com.tron.wallet.common.components;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import com.tron.wallet.common.components.flowlayout.FlowLayout;
import com.tron.wallet.common.components.flowlayout.TagAdapter;
import com.tron.wallet.common.components.flowlayout.TagFlowLayout;
import com.tron.wallet.common.utils.CustomFontUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class ErrorBottomLayout extends LinearLayout {
    private ErrorType errorType;
    private TagFlowLayout tabFlowLayout;
    private TextView tvError;

    public enum ErrorType {
        ERROR,
        WARNING,
        NORMAL,
        NONE
    }

    public ErrorType getErrorType() {
        return this.errorType;
    }

    public ErrorBottomLayout(Context context) {
        this(context, null);
    }

    public ErrorBottomLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ErrorBottomLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initialize(context, attributeSet);
    }

    private void initialize(Context context, AttributeSet attributeSet) {
        TextView textView = new TextView(context);
        this.tvError = textView;
        textView.setTypeface(CustomFontUtils.getTypeface(getContext(), 0));
        this.tvError.setTextSize(1, 12.0f);
        this.tvError.setLineSpacing(UIUtils.dip2px(getContext(), 2.0f), 1.0f);
        this.tvError.setGravity(16);
        this.tvError.setVisibility(View.GONE);
        this.errorType = ErrorType.NONE;
    }

    public void setSupportErrorTabs(List<String> list) {
        if (this.tabFlowLayout == null) {
            TagFlowLayout tagFlowLayout = new TagFlowLayout(getContext());
            this.tabFlowLayout = tagFlowLayout;
            tagFlowLayout.setMaxSelectCount(-1);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.gravity = 16;
            layoutParams.leftMargin = UIUtils.dip2px(getContext(), -4.0f);
            layoutParams.topMargin = UIUtils.dip2px(getContext(), 4.0f);
            super.addView(this.tabFlowLayout, -1, layoutParams);
        }
        if (list == null || list.size() == 0) {
            this.tabFlowLayout.setVisibility(View.GONE);
            return;
        }
        this.tabFlowLayout.setVisibility(View.VISIBLE);
        this.tabFlowLayout.setAdapter(new TagAdapter<String>(list) {
            @Override
            public View getView(FlowLayout flowLayout, int i, String str) {
                TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.tv_err_tab, (ViewGroup) tabFlowLayout, false);
                FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
                int dip2px = UIUtils.dip2px(getContext(), 4.0f);
                layoutParams2.setMargins(dip2px, dip2px, dip2px, dip2px);
                textView.setLayoutParams(layoutParams2);
                textView.setText(str);
                return textView;
            }
        });
    }

    public void clearError() {
        this.errorType = ErrorType.NONE;
        this.tvError.setVisibility(View.GONE);
    }

    static class fun2 {
        static final int[] $SwitchMap$com$tron$wallet$common$components$ErrorBottomLayout$ErrorType;

        static {
            int[] iArr = new int[ErrorType.values().length];
            $SwitchMap$com$tron$wallet$common$components$ErrorBottomLayout$ErrorType = iArr;
            try {
                iArr[ErrorType.ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tron$wallet$common$components$ErrorBottomLayout$ErrorType[ErrorType.WARNING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tron$wallet$common$components$ErrorBottomLayout$ErrorType[ErrorType.NORMAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$tron$wallet$common$components$ErrorBottomLayout$ErrorType[ErrorType.NONE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public void setError(ErrorType errorType, String str) {
        int i;
        int i2;
        this.errorType = errorType;
        int i3 = fun2.$SwitchMap$com$tron$wallet$common$components$ErrorBottomLayout$ErrorType[errorType.ordinal()];
        if (i3 == 1) {
            this.tvError.setVisibility(View.VISIBLE);
            i = R.color.red_ec;
            i2 = R.mipmap.ic_error_text;
        } else if (i3 != 2) {
            if (i3 == 3) {
                this.tvError.setVisibility(View.VISIBLE);
            } else if (i3 == 4) {
                this.tvError.setVisibility(View.GONE);
                return;
            }
            i = R.color.blue_3c;
            i2 = 0;
        } else {
            this.tvError.setVisibility(View.VISIBLE);
            i = R.color.orange_FFA9;
            i2 = R.mipmap.ic_warning_text;
        }
        this.tvError.setTextColor(getResources().getColor(i));
        if (i2 == 0) {
            this.tvError.setText(str);
            return;
        }
        SpannableString spannableString = new SpannableString("   " + str);
        Drawable drawable = AppCompatResources.getDrawable(getContext(), i2);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            spannableString.setSpan(new ImageSpan(drawable, 2), 0, 2, 18);
            this.tvError.setText(spannableString);
        }
    }

    @Override
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        if (view instanceof ViewGroup) {
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
            layoutParams2.gravity = 16;
            layoutParams2.topMargin = UIUtils.dip2px(getContext(), 8.0f);
            super.addView(this.tvError, -1, layoutParams2);
        }
    }
}
