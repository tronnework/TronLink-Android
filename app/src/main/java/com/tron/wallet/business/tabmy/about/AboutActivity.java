package com.tron.wallet.business.tabmy.about;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.widget.TextView;
import com.arasthel.asyncjob.AsyncJob;
import com.google.gson.Gson;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.create.creatimport.UserPrivacyAgreementActivity;
import com.tron.wallet.business.tabmy.about.AboutContract;
import com.tron.wallet.business.tabmy.bean.CommunityOutput;
import com.tron.wallet.business.web.CommonWebTitleActivity;
import com.tron.wallet.common.bean.update.UpdateOutput;
import com.tron.wallet.common.components.dialog.CommonUpdateDialog;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.config.TronSetting;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.AcAboutBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.net.HttpAPI;
import com.tron.wallet.net.location.LocationHelper;
import com.tron.wallet.update.location.UpdateHelper;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
public class AboutActivity extends BaseActivity<AboutPresenter, AboutModel> implements AboutContract.View {
    private static final String TAG = "AboutActivity";
    private AcAboutBinding binding;
    private String enTelUrl;
    private CommonUpdateDialog isExit;
    private RxManager mRxManager;
    private String twitterUrl;
    private UpdateHelper updateHelper;
    private String weChatUrl;
    private String zhTelUrl;
    private boolean trigger = false;
    private String websiteUrl = TronSetting.TRON_URL;

    public static long getAppVersionCode(Context context) {
        long j;
        try {
            PackageInfo packageInfo = context.getApplicationContext().getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if (Build.VERSION.SDK_INT >= 28) {
                j = packageInfo.getLongVersionCode();
            } else {
                j = packageInfo.versionCode;
            }
            return j;
        } catch (PackageManager.NameNotFoundException e) {
            LogUtils.e("", e.getMessage());
            return 0L;
        }
    }

    @Override
    protected void setLayout() {
        AcAboutBinding inflate = AcAboutBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 1);
        setClick();
        setHeaderBar(getString(R.string.about2));
    }

    @Override
    protected void processData() {
        ((AboutPresenter) this.mPresenter).getCommunityContent();
        try {
            this.mRxManager = new RxManager();
            long appVersionCode = getAppVersionCode(this.mContext);
            TextView textView = this.binding.tvVersion;
            textView.setText(("v" + this.mContext.getPackageManager().getPackageInfo(AppContextUtil.getContext().getPackageName(), 0).versionName) + "(" + appVersionCode + ")");
        } catch (PackageManager.NameNotFoundException e) {
            LogUtils.e(e);
        }
        this.binding.tvClientId.setText(" Current equipmentClientId:\t" + SpAPI.THIS.getClientId());
    }

    @Override
    public void onResume() {
        super.onResume();
        if (TronConfig.updateOutput == null || TronConfig.updateOutput.data == null || StringTronUtil.isEmpty(TronConfig.updateOutput.data.version)) {
            return;
        }
        if ((TronConfig.updateOutput.data.upgrade || TronConfig.updateOutput.data.force) && !SpAPI.THIS.isCold()) {
            this.binding.rlNewVersionTip.setVisibility(View.VISIBLE);
        } else {
            this.binding.rlNewVersionTip.setVisibility(View.GONE);
        }
    }

    public void setClick() {
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                switch (view.getId()) {
                    case R.id.log:
                        if (SpAPI.THIS.isCold()) {
                            Toast((int) R.string.not_support_cold_wallet);
                            return;
                        } else {
                            CommonWebTitleActivity.start(mContext, TronConfig.HTML_Version, getResources().getString(R.string.version_log));
                            return;
                        }
                    case R.id.telegram:
                        if (SpAPI.THIS.isCold()) {
                            Toast((int) R.string.not_support_cold_wallet);
                            return;
                        }
                        AboutActivity aboutActivity = AboutActivity.this;
                        aboutActivity.goIntent(aboutActivity.enTelUrl);
                        return;
                    case R.id.tv_version_tip:
                        AnalyticsHelper.logEvent(AnalyticsHelper.About.CLICK_ABOUT_NEW_VERSION_DISCOVERED);
                        break;
                    case R.id.twitter:
                        if (SpAPI.THIS.isCold()) {
                            Toast((int) R.string.not_support_cold_wallet);
                            return;
                        }
                        AboutActivity aboutActivity2 = AboutActivity.this;
                        aboutActivity2.goIntent(aboutActivity2.twitterUrl);
                        return;
                    case R.id.update:
                        break;
                    case R.id.user_agreement:
                        UserPrivacyAgreementActivity.start(mContext, true);
                        return;
                    case R.id.website:
                        if (SpAPI.THIS.isCold()) {
                            Toast((int) R.string.not_support_cold_wallet);
                            return;
                        }
                        AboutActivity aboutActivity3 = AboutActivity.this;
                        aboutActivity3.goIntent(aboutActivity3.websiteUrl);
                        return;
                    default:
                        return;
                }
                AnalyticsHelper.logEvent(AnalyticsHelper.About.CLICK_ABOUT_NEW_VERSION_UPDATE);
                update();
            }
        };
        this.binding.log.setOnClickListener(noDoubleClickListener2);
        this.binding.tvVersionTip.setOnClickListener(noDoubleClickListener2);
        this.binding.update.setOnClickListener(noDoubleClickListener2);
        this.binding.userAgreement.setOnClickListener(noDoubleClickListener2);
        this.binding.twitter.setOnClickListener(noDoubleClickListener2);
        this.binding.telegram.setOnClickListener(noDoubleClickListener2);
        this.binding.website.setOnClickListener(noDoubleClickListener2);
    }

    public void update() {
        if (SpAPI.THIS.isCold()) {
            Toast(R.string.not_support_cold_wallet);
        } else if (TronConfig.updateOutput != null && TronConfig.updateOutput.data != null) {
            if (TronConfig.updateOutput.data.upgrade || TronConfig.updateOutput.data.force) {
                LocationHelper.get().locationByIp(new LocationHelper.LocationCallback() {
                    @Override
                    public final void onLocated() {
                        lambda$update$1();
                    }
                });
            } else {
                requestOnly();
            }
        } else {
            requestOnly();
        }
    }

    public void lambda$update$1() {
        AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
            @Override
            public final void doInUIThread() {
                lambda$update$0();
            }
        });
    }

    public void lambda$update$0() {
        this.updateHelper = new UpdateHelper();
        if (isDestroyed() || isFinishing()) {
            return;
        }
        this.updateHelper.showUpdateDialog(this, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        }, null);
        this.binding.rlNewVersionTip.setVisibility(View.VISIBLE);
    }

    private void requestOnly() {
        Observable<UpdateOutput> updateOnLine;
        if (this.trigger) {
            Toast(getString(R.string.update2));
            return;
        }
        showLoadingDialog();
        if (IRequest.isNile() || IRequest.isShasta()) {
            updateOnLine = ((HttpAPI) IRequest.getAPI(HttpAPI.class)).updateOnLine();
        } else {
            updateOnLine = ((HttpAPI) IRequest.getAPI(HttpAPI.class)).update();
        }
        updateOnLine.compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<UpdateOutput>() {
            @Override
            public void onResponse(String str, UpdateOutput updateOutput) {
                TronConfig.updateOutput = updateOutput;
                SpAPI.THIS.setUpdateJson(new Gson().toJson(updateOutput));
                trigger = true;
                dismissLoadingDialog();
                update();
            }

            @Override
            public void onFailure(String str, String str2) {
                trigger = false;
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "11"));
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        AnalyticsHelper.logEvent(AnalyticsHelper.About.CLICK_ABOUT_CLICK_BACK);
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        UpdateHelper updateHelper = this.updateHelper;
        if (updateHelper != null) {
            updateHelper.onPermissionResult(i, strArr, iArr);
        }
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        UpdateHelper updateHelper = this.updateHelper;
        if (updateHelper != null) {
            updateHelper.onActivityResult(i, i2, intent);
        }
    }

    @Override
    public void updateUrl(CommunityOutput.DataBean dataBean) {
        if (dataBean != null) {
            this.zhTelUrl = dataBean.telegram_cn;
            this.enTelUrl = dataBean.telegram_en;
            this.twitterUrl = dataBean.twitter;
            this.weChatUrl = dataBean.WeChat;
            return;
        }
        this.zhTelUrl = SpAPI.THIS.getZhTeleUrl();
        this.enTelUrl = SpAPI.THIS.getEnTeleUrl();
        this.twitterUrl = SpAPI.THIS.getTwitterUrl();
        this.weChatUrl = SpAPI.THIS.getWechatUrl();
    }

    @Override
    public void goIntent(String str) {
        try {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } catch (Exception e) {
            LogUtils.e(e);
            SentryUtil.captureException(e);
        }
    }
}
