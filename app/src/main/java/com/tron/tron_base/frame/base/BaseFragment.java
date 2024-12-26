package com.tron.tron_base.frame.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
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
public abstract class BaseFragment<T extends BasePresenter, E extends BaseModel> extends Fragment implements BaseView {
    protected static final int TYPE_BLUE_HEADER = 6;
    protected static final int TYPE_HEADER = 1;
    protected static final int TYPE_HEADER_PROGRESS = 3;
    protected static final int TYPE_NOHEADER_STATUSBAR = 4;
    protected static final int TYPE_NOHEADER_STATUSBAR_PROGRESS = 5;
    protected static final int TYPE_NORMAL = 0;
    protected static final int TYPE_PINK_BLUE_BACK = 7;
    protected static final int TYPE_PROGRESS = 2;
    protected View contentView;
    protected HeaderLayout headerLayout;
    private LoadingDialog loadingDialog;
    private FrameLayout mEmptyLayout;
    private FirebaseAnalytics mFirebaseAnalytics;
    public E mModel;
    public T mPresenter;
    private View view;
    protected int TYPE = 0;
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
    protected Activity mContext = getActivity();

    @Override
    public Context getIContext() {
        return this.mContext;
    }

    public LoadingDialog getLoadingDialog() {
        return this.loadingDialog;
    }

    public void hideHeaderLeftArrow() {
    }

    protected void onCloseButtonClick() {
    }

    @Override
    public void onKeyDownChild(int i, KeyEvent keyEvent) {
        BaseView.-CC.$default$onKeyDownChild(this, i, keyEvent);
    }

    public void onLeftButtonClick() {
    }

    public void onReLoadButtonClick() {
    }

    protected void onRefreshButtonClick() {
    }

    protected void onRightButtonClick() {
    }

    public void onRightTextClick() {
    }

    protected abstract void processData();

    protected void processData(Bundle bundle) {
    }

    protected abstract View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle);

    public void setResult(String str) {
    }

    public int setType() {
        return this.TYPE;
    }

    public void setType(int i) {
        this.TYPE = i;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mContext = activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.mContext = (Activity) context;
        }
    }

    @Override
    public void onCreate(Bundle bundle) {
        Window window = this.mContext.getWindow();
        window.clearFlags(67108864);
        window.getDecorView().setSystemUiVisibility(BrowserConstant.WEBVIEW_LINK_LONGPRESS);
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(0);
        super.onCreate(bundle);
        if (this.mContext == null) {
            this.mContext = getActivity();
        }
        this.mFirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
        this.mPresenter = (T) TypeUtil.getT(this, 0);
        this.mModel = (E) TypeUtil.getT(this, 1);
        checkT();
        StatusBarUtils.setLightStatusBar(getActivity(), true);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mFirebaseAnalytics.setCurrentScreen(this.mContext, getClass().getSimpleName(), getClass().getSimpleName());
    }

    public void checkT() {
        T t = this.mPresenter;
        if (t != null && !(t instanceof BasePresenter)) {
            throw new IllegalArgumentException("mPresenter is not instanceof BasePresenter");
        }
        E e = this.mModel;
        if (e != null && !(e instanceof BaseModel)) {
            throw new IllegalArgumentException("mModel is not instanceof BaseModel");
        }
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        T t;
        this.view = setView(setLayoutView(layoutInflater, viewGroup, bundle), setType());
        if ((this instanceof BaseView) && (t = this.mPresenter) != null) {
            t.setVM(this, this.mModel);
        }
        return this.view;
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        processData(bundle);
        processData();
        LanguageUtils.setLocal(this.mContext);
    }

    protected View setView(View view, int i) {
        if (i != 0) {
            this.headerLayout = new HeaderLayout(this.mContext, getLayoutInflater(), view, i);
        } else {
            this.contentView = view;
        }
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.setHeaderClickListener(this.onHeaderClickListener);
            return this.headerLayout;
        }
        return this.contentView;
    }

    public void setHeaderBar(String str) {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.setHeaderBar(str);
        }
    }

    public HeaderLayout.HeaderHolder getHeaderHolder() {
        return this.headerLayout.getHeaderHolder();
    }

    public void setHeaderBar(String str, String str2) {
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

    public void setRefreshBar(int i) {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.setRightBar(i);
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

    public void setCommonRight2(String str) {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.setCommonRightTitle2(str);
        }
    }

    public void setCloseBar(int i) {
        HeaderLayout headerLayout = this.headerLayout;
        if (headerLayout != null) {
            headerLayout.setLeftBar(i);
        }
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
        this.loadingDialog.show(getString(R.string.loading));
    }

    public void showLoadingDialog(String str) {
        LoadingDialog loadingDialog = this.loadingDialog;
        if (loadingDialog == null || loadingDialog.getContext() != this.mContext) {
            this.loadingDialog = new LoadingDialog(this.mContext);
        }
        this.loadingDialog.show(str);
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
        this.mContext.runOnUiThread(new Runnable() {
            @Override
            public final void run() {
                IToast.getIToast().setImage(R.mipmap.toast_warn).show(str);
            }
        });
    }

    public void Toast(final int i) {
        this.mContext.runOnUiThread(new Runnable() {
            @Override
            public final void run() {
                IToast.getIToast().setImage(R.mipmap.toast_warn).show(i);
            }
        });
    }

    @Override
    public void ToastAsBottom(final int i) {
        this.mContext.runOnUiThread(new Runnable() {
            @Override
            public final void run() {
                IToast.getIToast().setImage(R.mipmap.toast_warn).showAsBottomn(i);
            }
        });
    }

    protected void ToastAsBottom(final int i, final int i2) {
        this.mContext.runOnUiThread(new Runnable() {
            @Override
            public final void run() {
                IToast.getIToast().setImage(R.mipmap.toast_warn).showAsBottomnDouble(i, i2);
            }
        });
    }

    @Override
    public void ToastSuc(final String str) {
        this.mContext.runOnUiThread(new Runnable() {
            @Override
            public final void run() {
                IToast.getIToast().setImage(R.mipmap.toast_suc).show(str);
            }
        });
    }

    @Override
    public void ToastSuc(final int i) {
        this.mContext.runOnUiThread(new Runnable() {
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
        this.mContext.runOnUiThread(new Runnable() {
            @Override
            public final void run() {
                IToast.getIToast().setImage(R.mipmap.toast_error).show(str);
            }
        });
    }

    @Override
    public void ToastError(final int i) {
        this.mContext.runOnUiThread(new Runnable() {
            @Override
            public final void run() {
                IToast.getIToast().setImage(R.mipmap.toast_error).show(i);
            }
        });
    }

    @Override
    public void ToastWar(final int i) {
        this.mContext.runOnUiThread(new Runnable() {
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
        startActivity(new Intent(this.mContext, cls));
    }

    @Override
    public void go(Class cls, Bundle bundle) {
        Intent intent = new Intent(this.mContext, cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void go(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void goForResult(Class cls, int i) {
        startActivityForResult(new Intent(this.mContext, cls), i);
    }

    @Override
    public void goForResult(Class cls, int i, Bundle bundle) {
        Intent intent = new Intent(this.mContext, cls);
        intent.putExtras(bundle);
        this.mContext.startActivityForResult(intent, i, bundle);
    }

    @Override
    public void exit() {
        this.mContext.finish();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        dismissLoadingDialog();
        if (this.mPresenter != null) {
            LogUtils.i("super:onDestroy:" + this.mPresenter.getClass().getSimpleName());
            this.mPresenter.onDestroy();
            this.mPresenter = null;
        }
        if (this.contentView != null) {
            this.contentView = null;
        }
    }

    @Override
    public void goForResult(Intent intent, int i) {
        startActivityForResult(intent, i);
    }

    @Override
    public void toast(final String str, final int i) {
        if (StringTronUtil.isNullOrEmpty(str)) {
            return;
        }
        this.mContext.runOnUiThread(new Runnable() {
            @Override
            public final void run() {
                IToast.getImageIToast().show(str, i);
            }
        });
    }

    @Override
    public void toast(final String str) {
        if (StringTronUtil.isNullOrEmpty(str)) {
            return;
        }
        this.mContext.runOnUiThread(new Runnable() {
            @Override
            public final void run() {
                IToast.getIToast().setImage(R.mipmap.toast_warn).show(str);
            }
        });
    }

    @Override
    public void go(Intent intent, Class cls) {
        intent.setClass(this.mContext, cls);
        startActivity(intent);
    }

    @Override
    public void goForResult(Intent intent, Class cls, int i) {
        intent.setClass(this.mContext, cls);
        startActivityForResult(intent, i);
    }

    @Override
    public void noCancleDialog(boolean z) {
        this.loadingDialog.setCanceledOnTouchOutside(z);
    }

    @Override
    public void setEmptyView(int i, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false);
        if (this.mEmptyLayout == null) {
            this.mEmptyLayout = new FrameLayout(inflate.getContext());
            RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(-1, -1);
            ViewGroup.LayoutParams layoutParams2 = inflate.getLayoutParams();
            if (layoutParams2 != null) {
                layoutParams.width = layoutParams2.width;
                layoutParams.height = layoutParams2.height;
            }
            this.mEmptyLayout.setLayoutParams(layoutParams);
        }
        this.mEmptyLayout.removeAllViews();
        this.mEmptyLayout.addView(inflate);
    }

    @Override
    public void runOnUIThread(final OnMainThread onMainThread) {
        this.mContext.runOnUiThread(new Runnable() {
            @Override
            public final void run() {
                BaseFragment.lambda$runOnUIThread$11(OnMainThread.this);
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
                BaseFragment.lambda$runOnThreeThread$12(OnBackground.this);
            }
        }, true);
    }

    public static void lambda$runOnThreeThread$12(OnBackground onBackground) {
        if (onBackground != null) {
            onBackground.doOnBackground();
        }
    }
}
