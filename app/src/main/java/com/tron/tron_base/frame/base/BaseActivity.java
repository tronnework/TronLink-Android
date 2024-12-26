package com.tron.tron_base.frame.base;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tron.tron_base.R;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnHeaderClickListener;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.StatusBarUtils;
import com.tron.tron_base.frame.utils.StringTronUtil;
import com.tron.tron_base.frame.utils.ThreadPoolManager;
import com.tron.tron_base.frame.utils.TypeUtil;
import com.tron.tron_base.frame.view.HeaderLayout;
import com.tron.tron_base.frame.view.IToast;
import com.tron.tron_base.frame.view.LoadingDialog;
import com.tron.wallet.business.tabdapp.browser.BrowserConstant;
public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends AppCompatActivity implements BaseView {
    protected static final int TYPE_BLUE_HEADER = 6;
    protected static final int TYPE_HEADER = 1;
    protected static final int TYPE_HEADER_PROGRESS = 3;
    protected static final int TYPE_NOHEADER_STATUABAR = 4;
    protected static final int TYPE_NOHEADER_STATUABAR_LIGHT = 5;
    protected static final int TYPE_NORMAL = 0;
    protected static final int TYPE_PROGRESS = 2;
    private static HomeReceiver homeReceiver;
    private HeaderLayout headerLayout;
    protected LoadingDialog loadingDialog;
    public FirebaseAnalytics mFirebaseAnalytics;
    public E mModel;
    public T mPresenter;
    private int bgColor = ViewCompat.MEASURED_SIZE_MASK;
    private int bgImgResId = 0;
    protected boolean isFront = false;
    private OnHeaderClickListener onHeaderClickListener = new OnHeaderClickListener() {
        @Override
        public void onClickLeftButton() {
            onLeftButtonClick();
        }

        @Override
        public void onClickRightButton() {
            onRightButtonClick();
        }

        @Override
        public void onClickReLoadButton() {
            onReLoadButtonClick();
        }

        @Override
        public void onClickCloseButton() {
            onCloseButtonClick();
        }

        @Override
        public void onClickRefreshButton() {
            onRefreshButtonClick();
        }

        @Override
        public void onClickRightTv() {
            onRightTextClick();
        }
    };
    protected Context mContext = this;

    public HeaderLayout getHeaderLayout() {
        return this.headerLayout;
    }

    @Override
    public Context getIContext() {
        return this.mContext;
    }

    public LoadingDialog getLoadingDialog() {
        return this.loadingDialog;
    }

    public void hideHeaderLeftArrow() {
    }

    public boolean isFront() {
        return this.isFront;
    }

    protected boolean keepSecureFlag() {
        return false;
    }

    protected void onCloseButtonClick() {
    }

    public void onCreate1(Bundle bundle) {
    }

    public void onCreate2(Bundle bundle) {
    }

    @Override
    public void onKeyDownChild(int i, KeyEvent keyEvent) {
        BaseView.-CC.$default$onKeyDownChild(this, i, keyEvent);
    }

    public void onLeftButtonClick() {
    }

    public void onReLoadButtonClick() {
    }

    public void onRefreshButtonClick() {
    }

    public void onRightButtonClick() {
    }

    public void onRightTextClick() {
    }

    protected abstract void processData();

    public void setBackground(int i, int i2) {
        this.bgColor = i;
        this.bgImgResId = i2;
    }

    @Override
    public void setEmptyView(int i, ViewGroup viewGroup) {
    }

    protected abstract void setLayout();

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(LanguageUtils.setLocal(context));
    }

    @Override
    public void onCreate(Bundle bundle) {
        T t;
        Window window = getWindow();
        window.clearFlags(67108864);
        window.getDecorView().setSystemUiVisibility(BrowserConstant.WEBVIEW_LINK_LONGPRESS);
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(0);
        super.onCreate(bundle);
        setLayout();
        onCreate1(bundle);
        this.mContext = this;
        this.mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        this.mPresenter = (T) TypeUtil.getT(this, 0);
        E e = (E) TypeUtil.getT(this, 1);
        this.mModel = e;
        if ((this instanceof BaseView) && (t = this.mPresenter) != null) {
            t.setVM(this, e);
        }
        StatusBarUtils.setLightStatusBar(this, true);
        onCreate2(bundle);
        processData();
        LanguageUtils.setLocal(this.mContext);
        LanguageUtils.setApplicationLanguage(getApplicationContext());
        initHomeReceiver();
    }

    public void initHomeReceiver() {
        if (homeReceiver == null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
            homeReceiver = new HomeReceiver();
            intentFilter.setPriority(1000);
            if (Build.VERSION.SDK_INT >= 26) {
                getApplicationContext().registerReceiver(homeReceiver, intentFilter, 2);
            } else {
                getApplicationContext().registerReceiver(homeReceiver, intentFilter);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.isFront = true;
        homeReceiver.setActivity(this);
        FirebaseAnalytics firebaseAnalytics = this.mFirebaseAnalytics;
        firebaseAnalytics.setCurrentScreen(this, "CurrentScreen: " + getClass().getSimpleName(), null);
        if (keepSecureFlag()) {
            return;
        }
        getWindow().clearFlags(8192);
    }

    @Override
    public void onPause() {
        super.onPause();
        this.isFront = false;
    }

    public void setView(View view, int i) {
        if (i != 0) {
            HeaderLayout headerLayout = new HeaderLayout(this, view, i, this.bgImgResId, this.bgColor);
            this.headerLayout = headerLayout;
            setContentView(headerLayout);
        } else if (this.bgImgResId <= 0) {
            setContentView(view);
        } else {
            HeaderLayout headerLayout2 = new HeaderLayout(this, view, i, this.bgImgResId, this.bgColor);
            this.headerLayout = headerLayout2;
            setContentView(headerLayout2);
        }
        HeaderLayout headerLayout3 = this.headerLayout;
        if (headerLayout3 != null) {
            headerLayout3.setHeaderClickListener(this.onHeaderClickListener);
        }
    }

    public void setTitle(String str) {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.setTitle(str);
        }
    }

    public void setMiddleTtitle(String str) {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.setMiddleTitle(str);
        }
    }

    public void setRightBold() {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.setRightBold();
        }
    }

    public void setCommonTitle2(String str) {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.setCommonTitle2(str);
        }
    }

    public void setCommonRight2(String str) {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.setCommonRightTitle2(str);
        }
    }

    public void showOrHideHeader(boolean z) {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout == null) {
            return;
        }
        if (z) {
            headerLayout.showOrHideHeader(true);
        } else {
            headerLayout.showOrHideHeader(false);
        }
    }

    public void setHeaderBar(String str) {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.setHeaderBar(str);
        }
    }

    public void setRefreshBar(int i) {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.setRightBar(i);
        }
    }

    public void setFavoriteBar(int i) {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.setRightBarFavorite(i);
        }
    }

    public void setHeaderBarAndRightTv(String str, String str2) {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.setHeaderBarAndRightTv(str, str2);
        }
    }

    public void setRightTextShow(boolean z) {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.setRightTextShow(z);
        }
    }

    public void setCloseBar(int i) {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.setLeftBar(i);
        }
    }

    public void setHeaderBarAndRight(String str) {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.setHeaderBar(str, "");
        }
    }

    public void setHeaderBarAndRightImage(String str) {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.setHeaderBar(str, 0);
        }
    }

    public void setHeaderBarAndRightImage(String str, int i) {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.setHeaderBar(str, i);
        }
    }

    public void setHeaderBarAndRightImageSpe(String str, int i) {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.setHeaderBarSpe(str, i);
        }
    }

    public void setHeaderBar(String str, String str2) {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.setHeaderBar(str, str2);
        }
    }

    public void setHeaderBar(String str, String str2, int i) {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.setHeaderBar(str, str2);
            this.headerLayout.getHeaderHolder().tvCommonRight.setTextColor(i);
        }
    }

    public void hideHeaderBarCommonRight() {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.getHeaderHolder().tvCommonRight.setVisibility(View.GONE);
        }
    }

    public void setWhiteHeaderBar(String str, String str2) {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.setHeaderBar(str, str2);
        }
    }

    public void setHeaderBar(String str, String str2, String str3) {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.setHeaderBar(str, str2, str3);
        }
    }

    public View getHeaderBar() {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            return headerLayout.getHeadBar();
        }
        return null;
    }

    public HeaderLayout.HeaderHolder getHeaderHolder() {
        return this.headerLayout.getHeaderHolder();
    }

    public void hideHeaderLeftButton() {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.hideHeaderLeftButton();
        }
    }

    public void showLoadingPage() {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.showLoadingPage();
        }
    }

    @Override
    public void showLoadingPage(String str) {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.showLoadingPage(str);
        }
    }

    @Override
    public void hideLoadingPageDialog() {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.hideLoadingPageDialog();
        }
    }

    @Override
    public void dismissLoadingPage() {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.dismissLoadingPage();
        }
    }

    @Override
    public void showErrorPage() {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.showErrorPage();
        }
    }

    @Override
    public void showErrorPage(String str) {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.showErrorPage(0, str);
        }
    }

    @Override
    public void showErrorPage(int i, String str) {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.showErrorPage(i, str);
        }
    }

    @Override
    public void showNoDatasPage() {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.showNoDatasPage();
        }
    }

    @Override
    public void showNoDatasPage(String str) {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.showNoDatasPage(0, str);
        }
    }

    @Override
    public void showNoDatasPage(int i, String str) {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.showNoDatasPage(i, str);
        }
    }

    @Override
    public void showLoadingDialog() {
        LoadingDialog loadingDialog = this.loadingDialog;
        if (loadingDialog == null || loadingDialog.getContext() != this.mContext) {
            this.loadingDialog = new LoadingDialog(this.mContext);
        }
        try {
            this.loadingDialog.show(getString(R.string.loading));
        } catch (WindowManager.BadTokenException unused) {
        }
    }

    public void showLoadingDialog(boolean z) {
        LoadingDialog loadingDialog = this.loadingDialog;
        if (loadingDialog == null || loadingDialog.getContext() != this.mContext) {
            this.loadingDialog = new LoadingDialog(this.mContext, z);
        }
        try {
            this.loadingDialog.show(getString(R.string.loading));
        } catch (WindowManager.BadTokenException unused) {
        }
    }

    public void showLoadingDialog(boolean z, boolean z2) {
        LoadingDialog loadingDialog = this.loadingDialog;
        if (loadingDialog == null || loadingDialog.getContext() != this.mContext) {
            this.loadingDialog = new LoadingDialog(this.mContext, z, z2);
        }
        try {
            this.loadingDialog.show(getString(R.string.loading));
        } catch (WindowManager.BadTokenException unused) {
        }
    }

    public void showLoadingDialog(boolean z, boolean z2, String str) {
        LoadingDialog loadingDialog = this.loadingDialog;
        if (loadingDialog == null || loadingDialog.getContext() != this.mContext) {
            this.loadingDialog = new LoadingDialog(this.mContext, z, z2);
        }
        try {
            this.loadingDialog.show(str);
        } catch (WindowManager.BadTokenException unused) {
        }
    }

    public void showLoadingDialog(String str) {
        LoadingDialog loadingDialog = this.loadingDialog;
        if (loadingDialog == null || loadingDialog.getContext() != this.mContext) {
            this.loadingDialog = new LoadingDialog(this.mContext);
        }
        try {
            this.loadingDialog.show(str);
        } catch (WindowManager.BadTokenException unused) {
        }
    }

    @Override
    public void dismissLoadingDialog() {
        LoadingDialog loadingDialog = this.loadingDialog;
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    public void Toast(final String str) {
        if (StringTronUtil.isNullOrEmpty(str)) {
            return;
        }
        runOnUiThread(new Runnable() {
            @Override
            public final void run() {
                IToast.getIToast().setImage(R.mipmap.toast_warn).show(str);
            }
        });
    }

    public void Toast(final int i) {
        runOnUiThread(new Runnable() {
            @Override
            public final void run() {
                IToast.getIToast().setImage(R.mipmap.toast_warn).show(i);
            }
        });
    }

    @Override
    public void ToastAsBottom(final int i) {
        runOnUiThread(new Runnable() {
            @Override
            public final void run() {
                IToast.getIToast().setImage(R.mipmap.toast_warn).showAsBottomn(i);
            }
        });
    }

    protected void ToastAsBottom(final int i, final int i2) {
        runOnUiThread(new Runnable() {
            @Override
            public final void run() {
                IToast.getIToast().setImage(R.mipmap.toast_warn).showAsBottomnDouble(i, i2);
            }
        });
    }

    @Override
    public void ToastSuc(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public final void run() {
                IToast.getIToast().setImage(R.mipmap.toast_suc).show(str);
            }
        });
    }

    @Override
    public void ToastSuc(final int i) {
        runOnUiThread(new Runnable() {
            @Override
            public final void run() {
                IToast.getIToast().setImage(R.mipmap.toast_suc).show(i);
            }
        });
    }

    @Override
    public void ToastError() {
        ToastError(R.string.network_busy);
    }

    @Override
    public void ToastError(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public final void run() {
                IToast.getIToast().setImage(R.mipmap.toast_error).show(str);
            }
        });
    }

    @Override
    public void ToastError(final int i) {
        runOnUiThread(new Runnable() {
            @Override
            public final void run() {
                IToast.getIToast().setImage(R.mipmap.toast_error).show(i);
            }
        });
    }

    @Override
    public void ToastWar(final int i) {
        runOnUiThread(new Runnable() {
            @Override
            public final void run() {
                IToast.getIToast().setImage(R.mipmap.toast_warn).show(i);
            }
        });
    }

    @Override
    public void showLoading(String str) {
        showLoadingDialog(str);
    }

    @Override
    public void dismissLoading() {
        dismissLoadingDialog();
    }

    @Override
    public void go(Class cls) {
        startActivity(new Intent(this, cls));
    }

    @Override
    public void go(Class cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void go(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void goForResult(Class cls, int i) {
        startActivityForResult(new Intent(this, cls), i);
    }

    @Override
    public void goForResult(Class cls, int i, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(bundle);
        startActivityForResult(intent, i, bundle);
    }

    @Override
    public void exit() {
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dismissLoadingDialog();
        if (this.mPresenter != null) {
            LogUtils.i("super:onDestroy:" + this.mPresenter.getClass().getSimpleName());
            this.mPresenter.onDestroy();
            this.mPresenter = null;
        }
    }

    @Override
    public void goForResult(Intent intent, int i) {
        startActivityForResult(intent, i);
    }

    @Override
    public void toast(final String str) {
        if (StringTronUtil.isNullOrEmpty(str)) {
            return;
        }
        runOnUiThread(new Runnable() {
            @Override
            public final void run() {
                IToast.getIToast().setImage(R.mipmap.toast_warn).show(str);
            }
        });
    }

    @Override
    public void toast(final String str, final int i) {
        if (StringTronUtil.isNullOrEmpty(str)) {
            return;
        }
        runOnUiThread(new Runnable() {
            @Override
            public final void run() {
                IToast.getImageIToast().show(str, i);
            }
        });
    }

    @Override
    public void go(Intent intent, Class cls) {
        intent.setClass(this, cls);
        startActivity(intent);
    }

    @Override
    public void goForResult(Intent intent, Class cls, int i) {
        intent.setClass(this, cls);
        startActivityForResult(intent, i);
    }

    @Override
    public void noCancleDialog(boolean z) {
        this.loadingDialog.setCanceledOnTouchOutside(z);
    }

    @Override
    public void runOnUIThread(final OnMainThread onMainThread) {
        runOnUiThread(new Runnable() {
            @Override
            public final void run() {
                BaseActivity.lambda$runOnUIThread$11(OnMainThread.this);
            }
        });
    }

    public static void lambda$runOnUIThread$11(OnMainThread onMainThread) {
        if (onMainThread != null) {
            onMainThread.doInUIThread();
        }
    }

    @Override
    public void runOnThreeThread(final OnBackground onBackground) {
        ThreadPoolManager.newInstance().addExecuteTask(new Runnable() {
            @Override
            public final void run() {
                BaseActivity.lambda$runOnThreeThread$12(OnBackground.this);
            }
        }, true);
    }

    public static void lambda$runOnThreeThread$12(OnBackground onBackground) {
        if (onBackground != null) {
            onBackground.doOnBackground();
        }
    }

    public void setBackgroundAlpha(float f) {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = f;
        getWindow().setAttributes(attributes);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        T t = this.mPresenter;
        if (t != null) {
            t.onBackPressed();
        }
    }
}
