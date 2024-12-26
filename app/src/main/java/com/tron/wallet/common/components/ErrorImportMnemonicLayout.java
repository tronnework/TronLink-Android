package com.tron.wallet.common.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.ItemErrorviewBinding;
import com.tronlinkpro.wallet.R;
import javax.annotation.Nullable;
public class ErrorImportMnemonicLayout extends RelativeLayout {
    ItemErrorviewBinding binding;
    private View inflate;
    private LinearLayout mInputFrame;
    RelativeLayout relativeLayout;
    TextView tvError;

    private void initialize(Context context, AttributeSet attributeSet) {
        LinearLayout linearLayout = new LinearLayout(context);
        this.mInputFrame = linearLayout;
        linearLayout.setOrientation(1);
        View inflate = View.inflate(context, R.layout.item_errorview, null);
        this.inflate = inflate;
        inflate.setPadding(50, 50, 50, 50);
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

    public void showError(@Nullable String str) {
        if (this.relativeLayout != null) {
            this.inflate.setVisibility(View.VISIBLE);
            this.tvError.setText(str);
        }
    }

    public void hideError() {
        if (this.relativeLayout != null) {
            this.inflate.setVisibility(View.GONE);
        }
    }

    @Override
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (view instanceof RelativeLayout) {
            this.relativeLayout = (RelativeLayout) view;
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(layoutParams);
            layoutParams2.gravity = (layoutParams2.gravity & (-113)) | 16;
            this.mInputFrame.addView(view, layoutParams2);
            this.mInputFrame.addView(this.inflate, -1);
            return;
        }
        super.addView(view, i, layoutParams);
    }

    public ErrorImportMnemonicLayout(Context context) {
        this(context, null);
    }

    public ErrorImportMnemonicLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ErrorImportMnemonicLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initialize(context, attributeSet);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        if (isFocused()) {
            paint.setColor(-65536);
        } else {
            paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        }
        canvas.drawColor(0);
        paint.setStrokeWidth(3.0f);
        paint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        float f = 30;
        float f2 = 60;
        path.moveTo(f, f2);
        float f3 = 90;
        path.addArc(f, f, f3, f3, 180.0f, 90.0f);
        path.lineTo(UIUtils.dip2px(3.0f) + 60, f);
        path.moveTo(160, f);
        path.lineTo(getWidth() - 60, f);
        path.addArc(getWidth() - 90, f, getWidth() - 30, f3, 270.0f, 90.0f);
        path.lineTo(getWidth() - 30, getHeight() - 60);
        path.addArc(getWidth() - 90, getHeight() - 90, getWidth() - 30, getHeight() - 30, 0.0f, 90.0f);
        path.lineTo(f2, getHeight() - 30);
        path.addArc(f, getHeight() - 90, f3, getHeight() - 30, 90.0f, 90.0f);
        path.lineTo(f, f2);
        canvas.drawPath(path, paint);
        super.onDraw(canvas);
    }
}
