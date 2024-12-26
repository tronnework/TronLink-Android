package com.tron.wallet.business.walletmanager.backupmnemonic;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.DgBackupMnemonicShowBinding;
import com.tron.wallet.db.wallet.WalletUtils;
public class ShowQRActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    private DgBackupMnemonicShowBinding binding;
    Button btCancel;
    ImageView ivQrCode;

    @Override
    public void onCreate1(Bundle bundle) {
        super.onCreate1(bundle);
        if (Build.VERSION.SDK_INT == 26 || Build.VERSION.SDK_INT == 27) {
            setRequestedOrientation(2);
        } else {
            setRequestedOrientation(1);
        }
    }

    public static void start(Context context, String str) {
        Intent intent = new Intent(context, ShowQRActivity.class);
        intent.putExtra(TronConfig.WALLET_extra, str);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        DgBackupMnemonicShowBinding inflate = DgBackupMnemonicShowBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 0);
        mappingId();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.ivQrCode = this.binding.ivQrCode;
        this.btCancel = this.binding.btCancel;
    }

    @Override
    protected void processData() {
        this.btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        this.ivQrCode.setImageBitmap(WalletUtils.strToQR(getIntent().getStringExtra(TronConfig.WALLET_extra), UIUtils.dip2px(200.0f), UIUtils.dip2px(200.0f), null));
    }
}
