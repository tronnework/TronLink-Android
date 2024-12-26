package com.tron.wallet.business.tabmy.advancedfeatures;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.tron.wallet.R;
import com.tron.wallet.common.utils.CustomFontUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
public class HorizontalOptionView extends RelativeLayout {
    private LinearLayout contentLayout;
    private boolean hasUnderline;
    private int iconResource;
    private boolean isBold;
    private ImageView mArrow;
    private String mSubTitle;
    private int mSubTitleColor;
    private float mSubTitleSize;
    private String mTitle;
    private int mTitleColor;
    private float mTitleSize;
    private TextView mTvSubTitle;
    private TextView mTvTitle;
    private ImageView mUnderline;

    public HorizontalOptionView(Context context) {
        this(context, null);
    }

    public HorizontalOptionView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HorizontalOptionView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public HorizontalOptionView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.HorizontalOptionView, i, i2);
        this.mTitle = obtainStyledAttributes.getString(4);
        this.mSubTitle = obtainStyledAttributes.getString(1);
        this.hasUnderline = obtainStyledAttributes.getBoolean(8, false);
        this.mTitleColor = obtainStyledAttributes.getColor(5, ViewCompat.MEASURED_STATE_MASK);
        this.mTitleSize = obtainStyledAttributes.getDimension(6, UIUtils.dip2px(getContext(), 14.0f));
        this.mSubTitleColor = obtainStyledAttributes.getColor(2, -7829368);
        this.mSubTitleSize = obtainStyledAttributes.getDimension(3, UIUtils.dip2px(getContext(), 14.0f));
        this.isBold = obtainStyledAttributes.getBoolean(7, true);
        this.iconResource = obtainStyledAttributes.getResourceId(0, -1);
        obtainStyledAttributes.recycle();
        setGravity(15);
        initChildren();
    }

    private void initChildren() {
        this.contentLayout = new LinearLayout(getContext());
        addTitle();
        if (!StringTronUtil.isNullOrEmpty(this.mSubTitle)) {
            addSubTitle();
        }
        addContentLayout();
        addRightArrow();
        if (this.hasUnderline) {
            addUnderline();
        }
    }

    private void addSubTitle() {
        Context context;
        this.mTvSubTitle = new TextView(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.topMargin = UIUtils.dip2px(8.0f);
        this.contentLayout.addView(this.mTvSubTitle, layoutParams);
        this.mTvSubTitle.setGravity(3);
        this.mTvSubTitle.setText(this.mSubTitle);
        int i = 0;
        this.mTvSubTitle.setTextSize(0, this.mSubTitleSize);
        this.mTvSubTitle.setTextColor(this.mSubTitleColor);
        TextView textView = this.mTvSubTitle;
        if (this.isBold) {
            context = getContext();
            i = 1;
        } else {
            context = getContext();
        }
        textView.setTypeface(CustomFontUtils.getTypeface(context, i));
    }

    private void addUnderline() {
        this.mUnderline = new ImageView(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, UIUtils.dip2px(getContext(), 1.0f));
        layoutParams.addRule(12);
        addView(this.mUnderline, layoutParams);
        this.mUnderline.setBackgroundColor(getContext().getResources().getColor(com.tronlinkpro.wallet.R.color.gray_b3_15));
    }

    private void addRightArrow() {
        this.mArrow = new ImageView(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(11);
        layoutParams.addRule(15);
        addView(this.mArrow, layoutParams);
        ImageView imageView = this.mArrow;
        int i = this.iconResource;
        if (i <= 0) {
            i = com.tronlinkpro.wallet.R.mipmap.right_arrow_new;
        }
        imageView.setImageResource(i);
    }

    private void addTitle() {
        Context context;
        this.mTvTitle = new TextView(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15);
        this.contentLayout.addView(this.mTvTitle, layoutParams);
        this.mTvTitle.setGravity(17);
        this.mTvTitle.setText(this.mTitle);
        int i = 0;
        this.mTvTitle.setTextSize(0, this.mTitleSize);
        this.mTvTitle.setTextColor(this.mTitleColor);
        TextView textView = this.mTvTitle;
        if (this.isBold) {
            context = getContext();
            i = 1;
        } else {
            context = getContext();
        }
        textView.setTypeface(CustomFontUtils.getTypeface(context, i));
    }

    private void addContentLayout() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15);
        addView(this.contentLayout, layoutParams);
        this.contentLayout.setOrientation(1);
    }

    public void setTitle(String str) {
        TextView textView = this.mTvTitle;
        if (textView == null) {
            return;
        }
        textView.setText(str);
    }
}
