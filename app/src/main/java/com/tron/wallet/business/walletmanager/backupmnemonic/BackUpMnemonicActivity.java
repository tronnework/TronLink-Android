package com.tron.wallet.business.walletmanager.backupmnemonic;

import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.FragmentTransaction;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.wallet.business.walletmanager.CreateSuccessActivity;
import com.tron.wallet.business.walletmanager.backupmnemonic.fg.CreateWalletAnimationFragment;
import com.tron.wallet.business.walletmanager.backupmnemonic.fg.ShowMnemonicFragment;
import com.tron.wallet.business.walletmanager.backupmnemonic.fg.ShowMnemonicPreviousFragment;
import com.tron.wallet.business.walletmanager.createwallet.view.TimelineView;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.interfaces.PopWindowCallback;
import com.tron.wallet.common.utils.PopupWindowUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.AcBackupMnemonicBinding;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
public class BackUpMnemonicActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    public static final String IS_FROM_SUCCESS_AGAIN = "is_from_success_again";
    public static final String IS_SHOW_TIMELINE_VIEW = "is_show_timeline_view";
    private AcBackupMnemonicBinding binding;
    private CreateWalletAnimationFragment createWalletAnimationFragment;
    private boolean fromSuccessAgain;
    boolean isShielded;
    private boolean isShowTimeLineView;
    String mnemonics;
    private Page page = Page.BackUpGif;
    private ShowMnemonicFragment showMnemonicFragment;
    private ShowMnemonicPreviousFragment showMnemonicPreviousFragment;
    TimelineView timelineView;
    String walletName;

    @Override
    protected boolean keepSecureFlag() {
        return true;
    }

    public static void start(Context context, String str, String str2) {
        startFromCreateOrNoT(context, str, str2, false, false);
    }

    public static void startFromCreateOrNoT(Context context, String str, String str2, boolean z, boolean z2) {
        Intent intent = new Intent(context, BackUpMnemonicActivity.class);
        intent.putExtra(TronConfig.WALLET_extra, str);
        intent.putExtra(TronConfig.WALLET_DATA, str2);
        intent.putExtra("is_show_timeline_view", z);
        intent.putExtra(IS_FROM_SUCCESS_AGAIN, z2);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        getWindow().setFlags(8192, 8192);
        AcBackupMnemonicBinding inflate = AcBackupMnemonicBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 1);
        mappingId();
        setHeaderBar(getString(R.string.back_mnemonic));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.timelineView = this.binding.timeLineLayout;
    }

    @Override
    protected void processData() {
        this.mnemonics = getIntent().getStringExtra(TronConfig.WALLET_extra);
        this.walletName = getIntent().getStringExtra(TronConfig.WALLET_DATA);
        this.isShielded = getIntent().getBooleanExtra(TronConfig.WALLET_DATA2, false);
        this.isShowTimeLineView = getIntent().getBooleanExtra("is_show_timeline_view", false);
        this.fromSuccessAgain = getIntent().getBooleanExtra(IS_FROM_SUCCESS_AGAIN, false);
        this.timelineView.setStepTwo();
        this.timelineView.setWhiteBg();
        this.createWalletAnimationFragment = new CreateWalletAnimationFragment();
        this.showMnemonicFragment = ShowMnemonicFragment.getInstance(this.mnemonics, this.walletName, this.isShowTimeLineView);
        this.showMnemonicPreviousFragment = ShowMnemonicPreviousFragment.getInstance(this.isShowTimeLineView);
        if (!this.isShowTimeLineView) {
            this.timelineView.setVisibility(View.GONE);
        }
        if (this.isShowTimeLineView) {
            JumpTo(Page.BackUpShowPrevious);
            this.timelineView.setStepTwoFinished();
        } else if (SpAPI.THIS.isBackUp(this.walletName)) {
            JumpTo(Page.BackUpShowPrevious);
        } else {
            JumpTo(Page.BackUpGif);
        }
    }

    public static class fun2 {
        static final int[] $SwitchMap$com$tron$wallet$business$walletmanager$backupmnemonic$Page;

        static {
            int[] iArr = new int[Page.values().length];
            $SwitchMap$com$tron$wallet$business$walletmanager$backupmnemonic$Page = iArr;
            try {
                iArr[Page.BackUpGif.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$walletmanager$backupmnemonic$Page[Page.BackUpShowPrevious.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$walletmanager$backupmnemonic$Page[Page.BackUpShow.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public void JumpTo(Page page) {
        this.page = page;
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        int i = fun2.$SwitchMap$com$tron$wallet$business$walletmanager$backupmnemonic$Page[page.ordinal()];
        if (i == 1) {
            beginTransaction.replace(R.id.frame, this.createWalletAnimationFragment);
        } else if (i == 2) {
            beginTransaction.replace(R.id.frame, this.showMnemonicPreviousFragment);
        } else if (i == 3) {
            beginTransaction.replace(R.id.frame, this.showMnemonicFragment);
        }
        beginTransaction.commit();
    }

    @Override
    public void onLeftButtonClick() {
        onBackPressed();
    }

    private void jumpSecurityPopWindow() {
        PopupWindowUtil.jumpSecurityPopWindow(this, new PopWindowCallback() {
            @Override
            public void cancel() {
            }

            @Override
            public void continueDo() {
                BackUpMnemonicActivity backUpMnemonicActivity = BackUpMnemonicActivity.this;
                CreateSuccessActivity.start(backUpMnemonicActivity, backUpMnemonicActivity.mnemonics, walletName, false);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (this.isShowTimeLineView) {
            jumpSecurityPopWindow();
        } else if (this.fromSuccessAgain) {
            toMain();
        } else {
            finish();
        }
    }

    private void toMain() {
        UIUtils.toMain(this.mContext);
        exit();
    }
}
