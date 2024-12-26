package com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
public class TransactionMetadataView extends FrameLayout {
    Button btnConfirm;

    public TransactionMetadataView(Context context) {
        super(context);
    }

    public TransactionMetadataView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TransactionMetadataView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View inflate = View.inflate(context, R.layout.layout_transaction_metadata_fragment, null);
        this.btnConfirm = (Button) inflate.findViewById(R.id.btn_confirm);
        addView(inflate, new FrameLayout.LayoutParams(-1, -1));
    }

    public TransactionMetadataView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void bindData(BaseParam baseParam) {
        try {
            if (WalletUtils.getSelectedPublicWallet().getCreateType() == 8 && baseParam.hasOwnerPermission()) {
                this.btnConfirm.setText(R.string.request_from_ledger);
            } else {
                this.btnConfirm.setText(R.string.confirm);
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }
}
