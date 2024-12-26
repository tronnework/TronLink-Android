package com.tron.wallet.common.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.ItemViewBackupBinding;
import com.tronlinkpro.wallet.R;
public class BackupItemView extends ViewGroup {
    ItemViewBackupBinding binding;
    Button btnCopy;
    Button btnDisplay;
    Button btnQrCode;
    private String btnStr;
    private boolean copied;
    private String hint;
    private String key;
    private View.OnClickListener l;
    private IOnClickListener listener;
    View llWarning;
    TextView mEtContent;
    private String title;
    TextView tvDesc;
    TextView tvTitle;
    TextView tvWarning;

    public interface IOnClickListener {
        void onClickCopy(int i, String str, BackupItemView backupItemView);

        void onClickQrCode(int i, String str, BackupItemView backupItemView);

        void onWarningDialogDismiss();
    }

    public boolean getCopied() {
        return this.copied;
    }

    public void setIOnClickListener(IOnClickListener iOnClickListener) {
        this.listener = iOnClickListener;
    }

    public void lambda$new$0(View view) {
        if (this.listener == null) {
            return;
        }
        String charSequence = this.mEtContent.getText().toString();
        if (TextUtils.isEmpty(charSequence)) {
            return;
        }
        int id = view.getId();
        if (id == R.id.ll_copy) {
            this.listener.onClickCopy(getId(), charSequence, this);
        } else if (id != R.id.ll_qr_code) {
        } else {
            this.listener.onClickQrCode(getId(), charSequence, this);
        }
    }

    public BackupItemView(Context context) {
        this(context, null);
    }

    public BackupItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BackupItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.l = new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$new$0(view);
            }
        };
        View inflate = inflate(context, R.layout.item_view_backup, null);
        addView(inflate, new ViewGroup.LayoutParams(-1, -2));
        this.binding = ItemViewBackupBinding.bind(inflate);
        mappingId();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.tron.wallet.R.styleable.ShieldObserverItemView);
        this.title = obtainStyledAttributes.getString(1);
        this.hint = obtainStyledAttributes.getString(0);
        this.key = obtainStyledAttributes.getString(3);
        this.btnStr = obtainStyledAttributes.getString(2);
        obtainStyledAttributes.recycle();
        if (StringTronUtil.isEmpty(this.title)) {
            this.tvTitle.setVisibility(View.GONE);
        } else {
            this.tvTitle.setText(this.title);
        }
        if (StringTronUtil.isEmpty(this.hint)) {
            this.tvDesc.setVisibility(View.GONE);
        } else {
            this.tvDesc.setText(this.hint);
        }
        TextView textView = this.tvWarning;
        String string = getResources().getString(R.string.create_wallet_hint_6);
        Object[] objArr = new Object[1];
        String str = this.key;
        objArr[0] = str == null ? getResources().getString(R.string.private_key) : str;
        textView.setText(String.format(string, objArr));
        this.btnCopy.setOnClickListener(this.l);
        this.btnQrCode.setOnClickListener(this.l);
        String str2 = this.btnStr;
        if (str2 != null) {
            this.btnDisplay.setText(str2);
        }
        this.btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$new$1(view);
            }
        });
        updateTips(false);
    }

    public void lambda$new$1(View view) {
        this.llWarning.setVisibility(View.GONE);
        IOnClickListener iOnClickListener = this.listener;
        if (iOnClickListener != null) {
            iOnClickListener.onWarningDialogDismiss();
        }
    }

    private void mappingId() {
        this.tvTitle = this.binding.tvTitle;
        this.tvDesc = this.binding.tvDesc;
        this.mEtContent = this.binding.editText;
        this.btnCopy = this.binding.llCopy;
        this.btnQrCode = this.binding.llQrCode;
        this.llWarning = this.binding.llWarning;
        this.tvWarning = this.binding.tvWarning;
        this.btnDisplay = this.binding.btnWaring;
    }

    public void updateContentText(CharSequence charSequence) {
        this.mEtContent.setText(charSequence);
    }

    @Override
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View childAt = getChildAt(0);
        childAt.layout(0, 0, childAt.getMeasuredWidth(), childAt.getMeasuredHeight());
    }

    @Override
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        measureChildren(i, i2);
    }

    public void updateTips(boolean z) {
        this.copied = z;
        this.btnCopy.setText(z ? R.string.already_copy : R.string.copy);
    }
}
