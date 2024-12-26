package com.tron.wallet.business.welcome;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.lxj.xpopup.XPopup;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.SignatureManager;
import com.tron.tron_base.frame.utils.ChannelUtils;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.wallet.business.maintab.MainTabActivity;
import com.tron.wallet.business.message.MessageCenterActivity;
import com.tron.wallet.business.migrate.MigrateActivity;
import com.tron.wallet.business.migrate.component.MigrateConfig;
import com.tron.wallet.business.pull.PullActivity;
import com.tron.wallet.business.welcome.WelcomeContract;
import com.tron.wallet.common.bean.AppLanguageOutput;
import com.tron.wallet.common.components.SimpleDraweeViewGif;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.config.UpgradeParamSetting;
import com.tron.wallet.common.interfaces.PermissionInterface;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.PermissionHelper;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.databinding.AcWelcomeBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.util.List;
import java.util.Set;
public class WelcomeActivity extends BaseActivity<WelcomePresenter, WelcomeModel> implements PermissionInterface, WelcomeContract.View {
    private AcWelcomeBinding binding;
    ImageView iv;
    private PermissionHelper mPermissionHelper;
    ProgressBar progressBar;
    SimpleDraweeViewGif simpleDraweeView;
    TextView sloganTextView;
    TextView tvProgress;

    @Override
    public String[] getPermissions() {
        return new String[0];
    }

    @Override
    public int getPermissionsRequestCode() {
        return 1000;
    }

    @Override
    public ImageView getRoot() {
        return this.iv;
    }

    @Override
    public SimpleDraweeView getSimpleDraweeView() {
        return this.simpleDraweeView;
    }

    @Override
    public TextView getSloganTextView() {
        return this.sloganTextView;
    }

    @Override
    protected void setLayout() {
        AcWelcomeBinding inflate = AcWelcomeBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 0);
        mappingId();
    }

    public void mappingId() {
        this.iv = this.binding.iv;
        this.simpleDraweeView = this.binding.simpleDraweeView;
        this.sloganTextView = this.binding.tvSlogan;
        this.progressBar = this.binding.progressBar;
        this.tvProgress = this.binding.tvProgress;
    }

    @Override
    protected void processData() {
        initLanguageSetting();
        LanguageUtils.setLocal(this.mContext);
        LanguageUtils.setApplicationLanguage(getApplicationContext());
        ((WelcomePresenter) this.mPresenter).setImage();
        ((WelcomePresenter) this.mPresenter).getNodes();
        ((WelcomePresenter) this.mPresenter).downLoadImage();
        PermissionHelper permissionHelper = new PermissionHelper(this, this);
        this.mPermissionHelper = permissionHelper;
        permissionHelper.requestPermissions();
        ((WelcomePresenter) this.mPresenter).onStart();
        ((WelcomePresenter) this.mPresenter).init();
        SpAPI.THIS.setNotNileChainNoticeTimes(0);
        FirebaseAnalytics.getInstance(this).setUserProperty(SignatureManager.Constants.channel, ChannelUtils.getGoogleAnalyticsChannel());
        AnalyticsHelper.logEvent(AnalyticsHelper.StartPage.ENTER_WELCOME_PAGE);
        XPopup.setShadowBgColor(getResources().getColor(R.color.gray_232c41_70));
    }

    private void initLanguageSetting() {
        try {
            AppLanguageOutput onLineAppLanguage = SpAPI.THIS.getOnLineAppLanguage();
            if (onLineAppLanguage != null) {
                List<String> currency = onLineAppLanguage.getCurrency();
                List<String> language = onLineAppLanguage.getLanguage();
                if (currency != null && currency.contains("cny") && language != null && language.contains("cn")) {
                    TronConfig.languageLocalConfig = 4;
                    SpAPI.THIS.setLanguage("1");
                    SpAPI.THIS.setIsUsdPrice(true);
                } else if (currency != null && currency.contains("cny")) {
                    TronConfig.languageLocalConfig = 1;
                    SpAPI.THIS.setIsUsdPrice(true);
                } else if (language != null && language.contains("cn")) {
                    TronConfig.languageLocalConfig = 2;
                    SpAPI.THIS.setLanguage("1");
                } else {
                    TronConfig.languageLocalConfig = 0;
                }
            }
        } catch (Throwable th) {
            SentryUtil.captureException(th);
        }
    }

    @Override
    public void requestPermissionsSuccess() {
        if (!UpgradeParamSetting.notNeedUpgrade()) {
            doNext(true, System.currentTimeMillis());
        } else {
            ((WelcomePresenter) this.mPresenter).upgradeHd();
        }
    }

    @Override
    public void requestPermissionsFail() {
        ToastError(R.string.error_permission1);
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (this.mPermissionHelper.requestPermissionsResult(i, strArr, iArr)) {
            return;
        }
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    public void finishWelcome() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        boolean z = extras != null && extras.containsKey("fireData");
        boolean curIsMainChain = true ^ SpAPI.THIS.getCurIsMainChain();
        if (z && !curIsMainChain) {
            Intent intent2 = new Intent(getIContext(), MessageCenterActivity.class);
            intent2.addFlags(67108864);
            startActivity(intent2);
        } else {
            String action = intent.getAction();
            if (PullActivity.ACTION.equals(action)) {
                Set<String> publicWalletNames = WalletUtils.getPublicWalletNames();
                if (publicWalletNames == null || publicWalletNames.size() == 0) {
                    go(EmptyWalletActivity.class);
                    Intent intent3 = new Intent(getIntent());
                    intent3.setComponent(new ComponentName(this, EmptyWalletActivity.class));
                    startActivity(intent3);
                } else {
                    Intent intent4 = new Intent(intent);
                    intent4.setClass(this, MainTabActivity.class);
                    go(intent4);
                }
            } else if (TextUtils.equals(action, MigrateConfig.ACTION_MIGRATE_TO_GLOBAL)) {
                Intent intent5 = new Intent(getIntent());
                if (TextUtils.equals("com.tronlinkpro.wallet", MigrateConfig.APP_ID_GLOBAL)) {
                    intent5.setComponent(new ComponentName(this, EmptyWalletActivity.class));
                } else if (TextUtils.equals("com.tronlinkpro.wallet", "com.tronlinkpro.wallet")) {
                    intent5.setComponent(new ComponentName(this, MigrateActivity.class));
                }
                startActivity(intent5);
            } else {
                go(EmptyWalletActivity.class);
            }
        }
        finish();
    }

    @Override
    public void showDefaultGif() {
        this.simpleDraweeView.setGif(R.drawable.welcome, 1, 100L, null);
        this.iv.setVisibility(View.GONE);
        this.simpleDraweeView.setVisibility(View.VISIBLE);
        this.simpleDraweeView.setVisibility(View.VISIBLE);
        this.sloganTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void doNext(boolean z, long j) {
        if (z) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    finishWelcome();
                }
            }, 1100 - (System.currentTimeMillis() - j));
        } else {
            finishWelcome();
        }
    }

    @Override
    public void doNext() {
        finishWelcome();
    }

    @Override
    public void showProgress(final int i) {
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$showProgress$0(i);
            }
        });
    }

    public void lambda$showProgress$0(int i) {
        this.tvProgress.setVisibility(View.VISIBLE);
        this.progressBar.setVisibility(View.VISIBLE);
        TextView textView = this.tvProgress;
        String string = getString(R.string.wallet_make_updating);
        textView.setText(String.format(string, i + "%"));
        this.progressBar.setProgress(i);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }
}
