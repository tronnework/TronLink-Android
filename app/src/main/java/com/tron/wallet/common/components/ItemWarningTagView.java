package com.tron.wallet.common.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.wallet.R;
import com.tron.wallet.common.utils.TouchDelegateUtils;
public class ItemWarningTagView extends RelativeLayout {
    private View closeView;
    private OnClickTagListener onClickListener;
    private View rlScam;
    private ImageView tagIcon;
    private TextView tagText;

    public interface OnClickTagListener {
        void onClose();

        void onTap();
    }

    public void setOnClickListener(OnClickTagListener onClickTagListener) {
        this.onClickListener = onClickTagListener;
    }

    public ItemWarningTagView(Context context) {
        this(context, null);
    }

    public ItemWarningTagView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ItemWarningTagView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.WarningTagView);
        String string = obtainStyledAttributes.getString(2);
        int color = obtainStyledAttributes.getColor(0, 0);
        Drawable drawable = obtainStyledAttributes.getDrawable(1);
        obtainStyledAttributes.recycle();
        View inflate = View.inflate(context, com.tronlinkpro.wallet.R.layout.item_warning_tag, null);
        addView(inflate, new RelativeLayout.LayoutParams(-1, -2));
        this.rlScam = inflate.findViewById(com.tronlinkpro.wallet.R.id.rl_scam);
        ImageView imageView = (ImageView) inflate.findViewById(com.tronlinkpro.wallet.R.id.iv_scam_notice);
        this.tagIcon = imageView;
        if (drawable != null) {
            imageView.setImageDrawable(drawable);
        } else {
            imageView.setVisibility(View.GONE);
        }
        TextView textView = (TextView) inflate.findViewById(com.tronlinkpro.wallet.R.id.tv_scam_notice);
        this.tagText = textView;
        if (color != 0) {
            textView.setTextColor(color);
        }
        if (string != null) {
            this.tagText.setText(string);
        }
        this.tagText.setSelected(true);
        this.tagText.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$new$0(view);
            }
        });
        TouchDelegateUtils.expandViewTouchDelegate(this.tagText, 10, 10, 0, 10);
        View findViewById = inflate.findViewById(com.tronlinkpro.wallet.R.id.iv_scam_close);
        this.closeView = findViewById;
        findViewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$new$1(view);
            }
        });
        TouchDelegateUtils.expandViewTouchDelegate(this.closeView, 2, 10, 10, 10);
    }

    public void lambda$new$0(View view) {
        OnClickTagListener onClickTagListener = this.onClickListener;
        if (onClickTagListener != null) {
            onClickTagListener.onTap();
        }
    }

    public void lambda$new$1(View view) {
        OnClickTagListener onClickTagListener = this.onClickListener;
        if (onClickTagListener != null) {
            onClickTagListener.onClose();
        }
    }

    public void setInfo(String str, int i, Drawable drawable, boolean z) {
        if (str != null) {
            this.tagText.setText(str);
        }
        if (i != 0) {
            this.tagText.setTextColor(i);
        }
        if (drawable != null) {
            this.tagIcon.setImageDrawable(drawable);
            this.tagIcon.setVisibility(View.VISIBLE);
        } else {
            this.tagIcon.setVisibility(View.GONE);
        }
        if (z) {
            this.closeView.setVisibility(View.VISIBLE);
        }
    }

    public void setInfo(SpannableString spannableString, int i, Drawable drawable, Drawable drawable2, boolean z) {
        if (spannableString != null) {
            this.tagText.setMovementMethod(LinkMovementMethod.getInstance());
            this.tagText.setText(spannableString);
            this.tagText.setHighlightColor(0);
        }
        if (drawable2 != null) {
            this.rlScam.setBackground(drawable2);
        }
        if (i != 0) {
            this.tagText.setTextColor(i);
        }
        if (drawable != null) {
            this.tagIcon.setImageDrawable(drawable);
            this.tagIcon.setVisibility(View.VISIBLE);
        } else {
            this.tagIcon.setVisibility(View.GONE);
        }
        if (z) {
            return;
        }
        this.closeView.setVisibility(View.GONE);
    }

    public void setLayoutPadding(int i, int i2, int i3, int i4) {
        View view = this.rlScam;
        if (view != null) {
            view.setPadding(i, i2, i3, i4);
        }
    }

    public void setLayoutDrawable(Drawable drawable) {
        if (drawable != null) {
            this.rlScam.setBackground(drawable);
        }
    }
}
