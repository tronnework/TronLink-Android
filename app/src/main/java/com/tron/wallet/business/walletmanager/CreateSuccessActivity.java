package com.tron.wallet.business.walletmanager;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.wallet.business.walletmanager.backupmnemonic.BackUpMnemonicActivity;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.AcCreateSuccessBinding;
import com.tronlinkpro.wallet.R;
public class CreateSuccessActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    public static final String KEY_IS_BACKED_UP = "KEY_IS_BACKED_UP";
    private AcCreateSuccessBinding binding;
    Button btAgain;
    Button btStart;
    ImageView gifOk;
    View viewBackedUp;
    View viewUnBackup;

    public static void start(Context context, String str, String str2, boolean z) {
        Intent intent = new Intent(context, CreateSuccessActivity.class);
        intent.putExtra(TronConfig.WALLET_extra, str);
        intent.putExtra(TronConfig.WALLET_DATA, str2);
        intent.putExtra(KEY_IS_BACKED_UP, z);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        AcCreateSuccessBinding inflate = AcCreateSuccessBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 0);
        mappingId();
    }

    private void mappingId() {
        this.btAgain = this.binding.btAgain;
        this.btStart = this.binding.btStart;
        this.gifOk = this.binding.gifOk;
        this.viewUnBackup = this.binding.llUnBackup;
        this.viewBackedUp = this.binding.rlBackedUp;
    }

    @Override
    protected void processData() {
        final Intent intent = getIntent();
        String stringExtra = intent.getStringExtra(TronConfig.WALLET_extra);
        if (intent.getBooleanExtra(KEY_IS_BACKED_UP, false)) {
            this.viewBackedUp.setVisibility(View.VISIBLE);
            this.viewUnBackup.setVisibility(View.GONE);
        } else {
            this.viewBackedUp.setVisibility(View.GONE);
            this.viewUnBackup.setVisibility(View.VISIBLE);
        }
        if (StringTronUtil.isEmpty(stringExtra)) {
            this.btAgain.setVisibility(View.GONE);
        }
        this.btAgain.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                BackUpMnemonicActivity.startFromCreateOrNoT(mContext, intent.getStringExtra(TronConfig.WALLET_extra), intent.getStringExtra(TronConfig.WALLET_DATA), false, true);
                exit();
            }
        });
        this.btStart.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                toMain();
            }
        });
        this.gifOk.setBackgroundResource(R.mipmap.icon_create_success);
    }

    @Override
    public void onLeftButtonClick() {
        toMain();
    }

    @Override
    public void onBackPressed() {
        toMain();
        super.onBackPressed();
    }

    public void toMain() {
        UIUtils.toMain(this.mContext);
        exit();
    }

    @Override
    public void onDestroy() {
        this.binding = null;
        super.onDestroy();
    }
}
