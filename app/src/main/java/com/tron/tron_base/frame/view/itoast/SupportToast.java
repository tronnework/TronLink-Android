package com.tron.tron_base.frame.view.itoast;

import android.app.Application;
final class SupportToast extends BaseToast {
    private final ToastHelper mToastHelper;

    public SupportToast(Application application) {
        super(application);
        this.mToastHelper = new ToastHelper(this, application);
    }

    @Override
    public void show() {
        this.mToastHelper.show();
    }

    @Override
    public void cancel() {
        this.mToastHelper.cancel();
    }
}
