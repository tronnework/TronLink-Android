package com.tron.wallet.business.pull.component;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.tron.wallet.business.pull.PullParam;
import com.tron.wallet.business.pull.PullResult;
import com.tron.wallet.business.pull.PullResultCode;
import com.tron.wallet.business.pull.PullResultHelper;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.components.ErrorView;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tronlinkpro.wallet.R;
public abstract class PullDetailView {
    protected Context activity;
    protected TextView btnCancel;
    protected TextView btnConfirm;
    protected FrameLayout contentExtend;
    protected ErrorView contentTip;
    protected FrameLayout headerExtend;
    protected ImageView headerIcon;
    protected ErrorView headerTip;
    protected TextView rightBtn;
    protected TextView title;

    public abstract PullResultCode checkDataValid();

    public void deInit() {
    }

    public abstract void init();

    public void initAction(TextView textView, TextView textView2) {
        this.btnConfirm = textView;
        this.btnCancel = textView2;
    }

    public void initContent(FrameLayout frameLayout, ErrorView errorView) {
        this.contentExtend = frameLayout;
        this.contentTip = errorView;
    }

    public void initHeader(TextView textView, ImageView imageView, TextView textView2, FrameLayout frameLayout, ErrorView errorView) {
        this.rightBtn = textView;
        this.headerIcon = imageView;
        this.title = textView2;
        this.headerExtend = frameLayout;
        this.headerTip = errorView;
    }

    public PullDetailView(Context context) {
        this.activity = context;
    }

    public void requestQuit(final PullParam pullParam) {
        ConfirmCustomPopupView.getBuilder(this.activity).setTitle(this.activity.getString(R.string.pull_quit)).setTitleSize(16).setTitleBold(false).setConfirmText(this.activity.getResources().getString(R.string.confirm)).setConfirmListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$requestQuit$0(pullParam, view);
            }
        }).setAutoDismissOutSide(false).build().show();
    }

    public void lambda$requestQuit$0(PullParam pullParam, View view) {
        ((AppCompatActivity) this.activity).moveTaskToBack(true);
        ((AppCompatActivity) this.activity).finish();
        PullResult pullResult = new PullResult();
        pullResult.setActionId(pullParam.getActionId());
        pullResult.setCode(PullResultCode.FAIL_CANCEL.getCode());
        pullResult.setMessage(PullResultCode.FAIL_CANCEL.getMessage());
        PullResultHelper.callBackToDApp(pullParam.getCallbackUrl(), pullResult);
    }

    public void setBackToDApp(PullResult pullResult) {
        this.btnConfirm.setText(this.activity.getString(R.string.pull_back_to_dapp));
        this.btnConfirm.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                ((AppCompatActivity) activity).moveTaskToBack(true);
                ((AppCompatActivity) activity).finish();
            }
        });
    }
}
