package com.tron.wallet.business.walletmanager.importwallet.base;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.wallet.business.walletmanager.common.ImportType;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.ActivityUnableAddressBinding;
import com.tronlinkpro.wallet.R;
public class UnableGetAddressActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    private ActivityUnableAddressBinding binding;
    private Button btnRetry;
    boolean isFrommAdd = false;
    TextView tvFailTips;

    public static void start(Context context, String str, boolean z) {
        Intent intent = new Intent(context, UnableGetAddressActivity.class);
        intent.putExtra(ImportType.KEY_LEDGER_NAME, str);
        intent.putExtra("from", z);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        ActivityUnableAddressBinding inflate = ActivityUnableAddressBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        setView(root, 1);
        setCommonRight2(getString(R.string.tutorial));
        getHeaderHolder().tvCommonRight2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIUtils.toHowUseLedger(UnableGetAddressActivity.this);
            }
        });
        getHeaderHolder().tvCommonRight2.setBackground(null);
        getHeaderHolder().tvCommonRight2.setTextColor(getResources().getColor(R.color.blue_no_alpha));
        getHeaderHolder().tvCommonRight2.setTextSize(14.0f);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.tvFailTips = this.binding.ledgerNoDevice.tvTip;
        this.btnRetry = this.binding.ledgerNoDevice.btnRetry;
    }

    @Override
    protected void processData() {
        boolean booleanExtra = getIntent().getBooleanExtra("from", false);
        this.isFrommAdd = booleanExtra;
        setHeaderBar(getString(booleanExtra ? R.string.add_device_title : R.string.ledger));
        this.tvFailTips.setText(String.format(getString(R.string.unable_find_ledger), getIntent().getStringExtra(ImportType.KEY_LEDGER_NAME)));
        this.btnRetry.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onLeftButtonClick() {
        finish();
    }
}
