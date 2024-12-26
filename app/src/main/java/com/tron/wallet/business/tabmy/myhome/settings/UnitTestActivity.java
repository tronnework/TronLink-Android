package com.tron.wallet.business.tabmy.myhome.settings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.maintab.MainTabActivity;
import com.tron.wallet.business.security.ResultStatusEnum;
import com.tron.wallet.business.security.check.emulator.EmulatorCheckUtils;
import com.tron.wallet.business.security.tokencheck.TokenCheckActivity;
import com.tron.wallet.business.tabmy.advancedfeatures.HorizontalOptionView;
import com.tron.wallet.business.tabmy.myhome.SwitchVersionActivity;
import com.tron.wallet.business.tabmy.myhome.settings.UnitTestActivity;
import com.tron.wallet.business.tabmy.myhome.settings.unittest.GetTrxTestFragment;
import com.tron.wallet.business.tabmy.myhome.settings.unittest.UnInstallHelperFragment;
import com.tron.wallet.business.tabmy.myhome.settings.unittest.WalletChangeFragment;
import com.tron.wallet.business.upgraded.HdUpdateActivity;
import com.tron.wallet.common.config.TronSetting;
import com.tron.wallet.common.config.UpgradeParamSetting;
import com.tron.wallet.common.utils.GsonUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.ToastUtil;
import com.tron.wallet.databinding.AcTestBinding;
import com.tron.wallet.db.Controller.HDTronWalletController;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;
import org.tron.walletserver.Wallet;
import org.tron.walletserver.WalletPath;
public class UnitTestActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    private AcTestBinding binding;
    HorizontalOptionView openTronWebDisplay;
    RelativeLayout rlHexDecode;
    RelativeLayout rlNodeSpeedTest;
    RelativeLayout rlShieldToken;
    RelativeLayout rlSqliteData;
    View subFragmentView;
    LinearLayout titleLeft;
    TextView tvCheckResult;
    TextView tvSwitchCold;

    public void showSubFragment(Fragment fragment) {
    }

    @Override
    protected void setLayout() {
        AcTestBinding inflate = AcTestBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        FrameLayout root = inflate.getRoot();
        mappingId();
        setClick();
        setView(root, 0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.rlNodeSpeedTest = this.binding.nodeSpeed;
        this.titleLeft = this.binding.llCommonLeft;
        this.rlHexDecode = this.binding.hexDecode;
        this.rlSqliteData = this.binding.sqliteData;
        this.rlShieldToken = this.binding.shieldToken;
        this.tvSwitchCold = this.binding.switchCold;
        this.openTronWebDisplay = this.binding.openTronwebDisplay;
        this.subFragmentView = this.binding.frameSubContent;
        this.tvCheckResult = this.binding.checkEmulatorResult;
    }

    @Override
    protected void processData() {
        this.tvSwitchCold.setText(SpAPI.THIS.isCold() ? "switch to hot wallet" : "switch to cold wallet");
        this.openTronWebDisplay.setTitle(TronSetting.tronWebDisplay ? "close 5497" : "open 5497");
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        if (this.subFragmentView.getVisibility() == 0) {
            this.subFragmentView.setVisibility(View.GONE);
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        onLeftButtonClick();
    }

    public class fun1 extends NoDoubleClickListener2 {
        fun1() {
        }

        @Override
        protected void onNoDoubleClick(View view) {
            switch (view.getId()) {
                case R.id.change_wallet_type:
                    showSubFragment(new WalletChangeFragment());
                    return;
                case R.id.check_token:
                    go(TokenCheckActivity.class);
                    return;
                case R.id.cold:
                    boolean isCold = SpAPI.THIS.isCold();
                    if (isCold) {
                        ToastUtil.getInstance().showToast((Activity) UnitTestActivity.this, "currently switch to hot wallet");
                    } else {
                        ToastUtil.getInstance().showToast((Activity) UnitTestActivity.this, "currently switch to cold wallet");
                    }
                    SpAPI.THIS.setCold(!isCold);
                    finish();
                    Intent intent = new Intent(UnitTestActivity.this, MainTabActivity.class);
                    intent.setFlags(268468224);
                    startActivity(intent);
                    return;
                case R.id.common_ui:
                    Context context = mContext;
                    final TextView textView = tvCheckResult;
                    Objects.requireNonNull(textView);
                    ResultStatusEnum checkEmulator = EmulatorCheckUtils.checkEmulator(context, new EmulatorCheckUtils.OnEmulatorCheckCallback() {
                        @Override
                        public final void onEmulatorResult(String str) {
                            textView.setText(str);
                        }
                    });
                    UnitTestActivity unitTestActivity = UnitTestActivity.this;
                    unitTestActivity.toast("检查模拟器 " + checkEmulator);
                    return;
                case R.id.get_trx:
                    showSubFragment(new GetTrxTestFragment());
                    return;
                case R.id.ll_common_left:
                    finish();
                    return;
                case R.id.open_tronweb_display:
                    TronSetting.tronWebDisplay = !TronSetting.tronWebDisplay;
                    openTronWebDisplay.setTitle(TronSetting.tronWebDisplay ? "close 5497" : "open 5497");
                    return;
                case R.id.switch_version:
                    go(SwitchVersionActivity.class);
                    return;
                case R.id.uninstall:
                    showSubFragment(new UnInstallHelperFragment());
                    return;
                case R.id.upgrade_hd:
                    go(HdUpdateActivity.class);
                    return;
                case R.id.upgrade_hd_create:
                    showLoadingDialog();
                    runOnThreeThread(new OnBackground() {
                        @Override
                        public final void doOnBackground() {
                            UnitTestActivity.1.this.lambda$onNoDoubleClick$1();
                        }
                    });
                    return;
                case R.id.upgrade_hd_reset:
                    UpgradeParamSetting.debugResetStatus();
                    return;
                default:
                    return;
            }
        }

        public void lambda$onNoDoubleClick$1() {
            ArrayList arrayList = new ArrayList();
            Wallet wallet = new Wallet(true);
            WalletPath createDefault = WalletPath.createDefault();
            wallet.setWalletName("abcdefgTest");
            wallet.setMnemonicPath(GsonUtils.toGsonString(createDefault));
            wallet.setCreateTime(System.currentTimeMillis());
            String address = wallet.getAddress();
            String mnemonic = wallet.getMnemonic();
            arrayList.add(wallet);
            int i = 0;
            while (i < 3) {
                i++;
                Wallet wallet2 = new Wallet(mnemonic, WalletPath.createDefault(i));
                wallet2.setWalletName("abcdefgTest" + i);
                wallet2.setCreateTime(System.currentTimeMillis());
                arrayList.add(wallet2);
            }
            List<String> saveWallet = WalletUtils.saveWallet(arrayList, "Aa12345678");
            LogUtils.i("upgrade_hd_create_size:" + saveWallet.size());
            boolean insertWallets = HDTronWalletController.insertWallets(arrayList, address);
            LogUtils.i("upgrade_hd_create_size:" + insertWallets);
            runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    UnitTestActivity.1.this.lambda$onNoDoubleClick$0();
                }
            });
        }

        public void lambda$onNoDoubleClick$0() {
            dismissLoadingDialog();
        }
    }

    public void setClick() {
        1 r0 = new fun1();
        this.binding.nodeSpeed.setOnClickListener(r0);
        this.binding.hexDecode.setOnClickListener(r0);
        this.binding.sqliteData.setOnClickListener(r0);
        this.binding.shieldToken.setOnClickListener(r0);
        this.binding.kexuejia.setOnClickListener(r0);
        this.binding.uninstall.setOnClickListener(r0);
        this.binding.newaddassets.setOnClickListener(r0);
        this.binding.nft.setOnClickListener(r0);
        this.binding.qrScan.setOnClickListener(r0);
        this.binding.qrScan2.setOnClickListener(r0);
        this.binding.cold.setOnClickListener(r0);
        this.binding.changeAddress.setOnClickListener(r0);
        this.binding.verify.setOnClickListener(r0);
        this.binding.costomView.setOnClickListener(r0);
        this.binding.switchVersion.setOnClickListener(r0);
        this.binding.bip32.setOnClickListener(r0);
        this.binding.lottie.setOnClickListener(r0);
        this.binding.collapsing.setOnClickListener(r0);
        this.binding.getTrx.setOnClickListener(r0);
        this.binding.showCircleProgress.setOnClickListener(r0);
        this.binding.enterUnstake.setOnClickListener(r0);
        this.binding.eip712.setOnClickListener(r0);
        this.binding.changeWalletType.setOnClickListener(r0);
        this.binding.stake20.setOnClickListener(r0);
        this.binding.openTronwebDisplay.setOnClickListener(r0);
        this.binding.delegate.setOnClickListener(r0);
        this.binding.upgradeHd.setOnClickListener(r0);
        this.binding.upgradeHdCreate.setOnClickListener(r0);
        this.binding.upgradeHdReset.setOnClickListener(r0);
        this.binding.checkToken.setOnClickListener(r0);
        this.binding.commonUi.setOnClickListener(r0);
    }
}
