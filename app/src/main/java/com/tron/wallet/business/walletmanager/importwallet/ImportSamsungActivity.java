package com.tron.wallet.business.walletmanager.importwallet;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.maintab.MainTabActivity;
import com.tron.wallet.business.samsung.SamsungSDKWrapper;
import com.tron.wallet.business.vote.util.BaseTextWatcher;
import com.tron.wallet.business.walletmanager.importwallet.ImportSamsungActivity;
import com.tron.wallet.common.components.CommonTitleDescriptionEditView;
import com.tron.wallet.common.components.SimpleDraweeViewGif;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.UserIconRandom;
import com.tron.wallet.databinding.ActivityImportSamsungBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import org.tron.walletserver.Wallet;
public class ImportSamsungActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    static final String key_address = "address";
    private ActivityImportSamsungBinding binding;
    Button btnImport;
    CommonTitleDescriptionEditView etName;
    SimpleDraweeViewGif imageTitle;
    private String samsungAddress;
    Toolbar toolbar;
    CollapsingToolbarLayout toolbarLayout;

    public static void start(Context context, String str) {
        Intent intent = new Intent(context, ImportSamsungActivity.class);
        intent.putExtra("address", str);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        setBackground(getResources().getColor(R.color.white), R.mipmap.bg_createwallet);
        ActivityImportSamsungBinding inflate = ActivityImportSamsungBinding.inflate(getLayoutInflater());
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
        this.toolbarLayout = this.binding.toolbarLayout;
        this.toolbar = this.binding.toolbar;
        this.imageTitle = this.binding.imageTitle;
        this.etName = this.binding.etNameInput;
        this.btnImport = this.binding.btnImport;
    }

    @Override
    protected void processData() {
        this.toolbarLayout.setTitle(getString(R.string.samsung_wallet));
        this.imageTitle.setGif(R.drawable.wallet_sumsung, 1);
        String stringExtra = getIntent().getStringExtra("address");
        this.samsungAddress = stringExtra;
        if (TextUtils.isEmpty(stringExtra)) {
            this.btnImport.setEnabled(false);
        }
        this.etName.addTextChangedListener(new fun1());
        this.etName.setRightDrawableClick(new CommonTitleDescriptionEditView.RightDrawableClick() {
            @Override
            public final void onRightDrawableClick(View view) {
                lambda$processData$0(view);
            }
        });
        this.etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    if (TextUtils.isEmpty(etName.toString().trim())) {
                        etName.setRightImageResId(0);
                        return;
                    } else {
                        etName.setRightImageResId(R.mipmap.input_clear);
                        return;
                    }
                }
                etName.setRightImageResId(0);
            }
        });
        this.toolbar.setNavigationOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                finish();
            }
        });
        this.btnImport.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (etName.getText() == null) {
                    return;
                }
                String trim = etName.getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    return;
                }
                String checkSeedHashEmpty = SamsungSDKWrapper.checkSeedHashEmpty(ImportSamsungActivity.this, true);
                Wallet wallet = new Wallet();
                wallet.setWalletName(trim);
                wallet.setAddress(samsungAddress);
                wallet.setCreateTime(System.currentTimeMillis());
                wallet.setWatchOnly(true);
                wallet.setCreateType(7);
                wallet.setIconRes(UserIconRandom.THIS.random());
                wallet.setSeedHash(checkSeedHashEmpty);
                wallet.setSamsungWallet(true);
                try {
                    WalletUtils.saveWatchOnly(wallet);
                    WalletUtils.setSelectedWallet(trim);
                    if (SpAPI.THIS.isCold()) {
                        SpAPI.THIS.setColdWalletSelected(trim);
                    }
                    toMain();
                } catch (Exception e) {
                    LogUtils.e(e);
                }
            }
        });
    }

    public class fun1 extends BaseTextWatcher {
        fun1() {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String trim = editable.toString().trim();
            btnImport.setEnabled(!(TextUtils.isEmpty(trim) || TextUtils.isEmpty(samsungAddress)));
            if (!TextUtils.isEmpty(trim)) {
                etName.setRightImageResId(R.mipmap.input_clear);
                etName.setRightDrawableClick(new CommonTitleDescriptionEditView.RightDrawableClick() {
                    @Override
                    public final void onRightDrawableClick(View view) {
                        ImportSamsungActivity.1.this.lambda$afterTextChanged$0(view);
                    }
                });
                return;
            }
            etName.setRightImageResId(0);
            etName.setRightDrawableClick(null);
        }

        public void lambda$afterTextChanged$0(View view) {
            etName.setText("");
        }
    }

    public void lambda$processData$0(View view) {
        this.etName.setText("");
    }

    public void toMain() {
        Intent intent = new Intent(this, MainTabActivity.class);
        intent.setFlags(67108864);
        go(intent);
        exit();
    }
}
