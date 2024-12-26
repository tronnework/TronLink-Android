package com.tron.wallet.business.migrate;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.maintab.MainTabActivity;
import com.tron.wallet.business.migrate.component.MigrateConfig;
import com.tron.wallet.business.welcome.EmptyWalletActivity;
import com.tron.wallet.business.welcome.WelcomeActivity;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.databinding.ActivityMigrateInBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
public class MigrateInActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    private ActivityMigrateInBinding binding;
    View rootView;

    @Override
    protected void setLayout() {
        ActivityMigrateInBinding inflate = ActivityMigrateInBinding.inflate(getLayoutInflater());
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
        this.rootView = this.binding.root;
    }

    @Override
    protected void processData() {
        LogUtils.w("MigrateInActivity", "pacakge name : com.tronlinkpro.wallet");
        if (TextUtils.equals("com.tronlinkpro.wallet", MigrateConfig.APP_ID_GLOBAL)) {
            handleWalletGlobal();
        } else if (TextUtils.equals("com.tronlinkpro.wallet", "com.tronlinkpro.wallet")) {
            handleWalletPro();
        }
    }

    private void handleWalletPro() {
        Intent intent;
        if (!WalletUtils.getAllWallets().isEmpty()) {
            if (isAppRunning()) {
                intent = new Intent(getIntent());
                intent.setComponent(new ComponentName(this, MigrateActivity.class));
                intent.addFlags(67108864);
            } else {
                intent = new Intent(getIntent());
                intent.setComponent(new ComponentName(this, WelcomeActivity.class));
            }
            startActivity(intent);
            finish();
            return;
        }
        new ConfirmCustomPopupView.Builder(this).setTitleSize(16).setTitle(getString(R.string.app_pro_state_init)).setShowCancelBtn(false).setConfirmText(getString(R.string.migrate_ok)).setConfirmListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                Intent intent2 = new Intent(view.getContext(), EmptyWalletActivity.class);
                intent2.addFlags(67108864);
                startActivity(intent2);
                finish();
            }
        }).build().show();
    }

    private void handleWalletGlobal() {
        Intent intent;
        if (WalletUtils.getAllWallets().isEmpty()) {
            if (isAppRunning()) {
                intent = new Intent(getIntent());
                intent.setComponent(new ComponentName(this, EmptyWalletActivity.class));
            } else {
                intent = new Intent(getIntent());
                intent.setComponent(new ComponentName(this, WelcomeActivity.class));
            }
            startActivity(intent);
            finish();
            return;
        }
        this.rootView.setBackgroundColor(getResources().getColor(R.color.gray_232c41_70));
        new ConfirmCustomPopupView.Builder(this).setTitleSize(16).setTitle(getString(R.string.migrate_data)).setInfo(getString(R.string.migrate_init_state_warning)).setShowCancelBtn(false).setConfirmText(getString(R.string.migrate_ok)).setConfirmListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                enterMainActivity();
                finish();
            }
        }).build().show();
    }

    private boolean isAppRunning() {
        int i;
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= 23) {
            i = activityManager.getAppTasks().get(0).getTaskInfo().numActivities;
        } else {
            i = activityManager.getRunningTasks(1).get(0).numActivities;
        }
        return i > 1;
    }

    public void enterMainActivity() {
        Intent intent = new Intent(this, MainTabActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(32768);
        startActivity(intent);
    }
}
