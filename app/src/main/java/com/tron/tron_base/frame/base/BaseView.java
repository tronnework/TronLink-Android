package com.tron.tron_base.frame.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
public interface BaseView {

    public final class -CC {
        public static void $default$onKeyDownChild(BaseView _this, int i, KeyEvent keyEvent) {
        }
    }

    void ToastAsBottom(int i);

    void ToastError();

    void ToastError(int i);

    void ToastError(String str);

    void ToastSuc(int i);

    void ToastSuc(String str);

    void ToastWar(int i);

    void dismissLoading();

    void dismissLoadingDialog();

    void dismissLoadingPage();

    void exit();

    Context getIContext();

    void go(Intent intent);

    void go(Intent intent, Class cls);

    void go(Class cls);

    void go(Class cls, Bundle bundle);

    void goForResult(Intent intent, int i);

    void goForResult(Intent intent, Class cls, int i);

    void goForResult(Class cls, int i);

    void goForResult(Class cls, int i, Bundle bundle);

    void hideLoadingPageDialog();

    void noCancleDialog(boolean z);

    void onKeyDownChild(int i, KeyEvent keyEvent);

    void runOnThreeThread(OnBackground onBackground);

    void runOnUIThread(OnMainThread onMainThread);

    void setEmptyView(int i, ViewGroup viewGroup);

    void showErrorPage();

    void showErrorPage(int i, String str);

    void showErrorPage(String str);

    void showLoading(String str);

    void showLoadingDialog();

    void showLoadingPage(String str);

    void showNoDatasPage();

    void showNoDatasPage(int i, String str);

    void showNoDatasPage(String str);

    void toast(String str);

    void toast(String str, int i);
}
