package com.tron.wallet.common.components;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.ItemErrorviewBinding;
import com.tronlinkpro.wallet.R;
import javax.annotation.Nullable;
public class ErrorEdiTextLayout extends RelativeLayout {
    ItemErrorviewBinding binding;
    private View inflate;
    EditText mEditText;
    private LinearLayout mInputFrame;
    RelativeLayout relativeLayout;
    TextView tvError;

    public ErrorEdiTextLayout(Context context) {
        this(context, null);
    }

    public ErrorEdiTextLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ErrorEdiTextLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initialize(context, attributeSet);
    }

    private void initialize(Context context, AttributeSet attributeSet) {
        LinearLayout linearLayout = new LinearLayout(context);
        this.mInputFrame = linearLayout;
        linearLayout.setOrientation(1);
        View inflate = View.inflate(context, R.layout.item_errorview, null);
        this.inflate = inflate;
        inflate.setPadding(0, UIUtils.dip2px(8.0f), 0, 0);
        this.binding = ItemErrorviewBinding.bind(this.inflate);
        mappingId();
        String string = context.obtainStyledAttributes(attributeSet, com.tron.wallet.R.styleable.ErrorEdiTextLayout).getString(0);
        if (!TextUtils.isEmpty(string)) {
            this.tvError.setText(string);
        }
        this.mInputFrame.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        addView(this.mInputFrame);
    }

    private void mappingId() {
        this.tvError = this.binding.tvError;
    }

    public void showErrorWithoutBackground() {
        if (this.mEditText != null) {
            this.inflate.setVisibility(View.VISIBLE);
        }
    }

    public void showError3WithoutBackground() {
        if (this.relativeLayout != null) {
            this.inflate.setVisibility(View.VISIBLE);
            this.relativeLayout.setBackground(null);
        }
    }

    public void showError3BgNoChange() {
        if (this.relativeLayout != null) {
            this.inflate.setVisibility(View.VISIBLE);
        }
    }

    public void showError() {
        if (this.mEditText != null) {
            this.inflate.setVisibility(View.VISIBLE);
            this.mEditText.setBackgroundResource(R.drawable.roundborder_ff4646_radius);
        }
    }

    public void showError2() {
        EditText editText = this.mEditText;
        if (editText != null) {
            editText.setBackgroundResource(R.drawable.roundborder_ff4646_radius);
        }
    }

    public void showError3() {
        if (this.relativeLayout != null) {
            this.inflate.setVisibility(View.VISIBLE);
            this.relativeLayout.setBackgroundResource(R.drawable.roundborder_ff4646_radius);
        }
    }

    public void showError3WithOutSetBG() {
        if (this.relativeLayout != null) {
            this.inflate.setVisibility(View.VISIBLE);
        }
    }

    public void showError4() {
        if (this.relativeLayout != null) {
            this.inflate.setVisibility(View.VISIBLE);
            this.relativeLayout.setBackgroundResource(R.drawable.roundborder_eeeeee);
        }
    }

    public void hideError3() {
        if (this.relativeLayout != null) {
            this.inflate.setVisibility(View.GONE);
            this.relativeLayout.setBackgroundResource(R.drawable.roundborder_eeeeee);
        }
    }

    public void hideError3WithOutSetBG() {
        if (this.relativeLayout != null) {
            this.inflate.setVisibility(View.GONE);
        }
    }

    public void hideError() {
        if (this.mEditText != null) {
            this.inflate.setVisibility(View.GONE);
            this.mEditText.setBackgroundResource(R.drawable.roundborder_eeeeee);
        }
    }

    public void hideErrorWithoutBackground() {
        if (this.mEditText == null && this.relativeLayout == null) {
            return;
        }
        this.inflate.setVisibility(View.GONE);
    }

    public void showError_Node() {
        if (this.mEditText != null) {
            this.inflate.setVisibility(View.VISIBLE);
            this.mEditText.setBackgroundResource(R.drawable.roundborder_021c31_6);
        }
    }

    public void hideError_Node() {
        if (this.mEditText != null) {
            this.inflate.setVisibility(View.GONE);
            this.mEditText.setBackgroundResource(R.drawable.roundborder_021c31_6);
        }
    }

    public void setTextError(@Nullable String str) {
        if (this.mEditText != null) {
            this.tvError.setText(str);
        }
    }

    public void setTextError3(@Nullable String str) {
        if (this.relativeLayout != null) {
            this.tvError.setText(str);
        }
    }

    public void setTextError3(@Nullable int i) {
        if (this.relativeLayout != null) {
            this.tvError.setText(i);
        }
    }

    public void setTextError(@Nullable int i) {
        if (this.mEditText != null) {
            this.tvError.setText(i);
        }
    }

    public int getErrorVisibity() {
        View view = this.inflate;
        if (view != null) {
            return view.getVisibility();
        }
        return 8;
    }

    @Override
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (view instanceof EditText) {
            this.mEditText = (EditText) view;
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(layoutParams);
            layoutParams2.gravity = (layoutParams2.gravity & (-113)) | 16;
            this.mInputFrame.addView(view, layoutParams2);
            this.mInputFrame.addView(this.inflate, -1);
        } else if (view instanceof RelativeLayout) {
            this.relativeLayout = (RelativeLayout) view;
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(layoutParams);
            layoutParams3.gravity = (layoutParams3.gravity & (-113)) | 16;
            this.mInputFrame.addView(view, layoutParams3);
            this.mInputFrame.addView(this.inflate, -1);
        } else {
            super.addView(view, i, layoutParams);
        }
    }
}
