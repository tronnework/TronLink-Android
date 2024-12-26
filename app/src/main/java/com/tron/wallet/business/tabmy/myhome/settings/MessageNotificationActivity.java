package com.tron.wallet.business.tabmy.myhome.settings;

import android.content.Intent;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.NotificationManagerCompat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.lxj.xpopup.core.BasePopupView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.components.SwitchButton;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.databinding.AcMessagenotificationBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.net.WebSocketManager;
import com.tronlinkpro.wallet.R;
import org.tron.net.bean.MessagePermission;
public class MessageNotificationActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    private AcMessagenotificationBinding binding;
    private MessagePermission messagePermission;
    View notificationSystem;
    private BasePopupView showPopForClosePop;
    private BasePopupView showPopForOpenHintPop;
    private BasePopupView showPopForOpenPop;
    SwitchButton switchButton;
    TextView tvHint;

    @Override
    protected void setLayout() {
        AcMessagenotificationBinding inflate = AcMessagenotificationBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        LinearLayout root = inflate.getRoot();
        mappingId();
        setView(root, 1);
        setHeaderBar(getString(R.string.message_notification));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.switchButton = this.binding.switchButton;
        this.notificationSystem = this.binding.notificationSystem;
        this.tvHint = this.binding.tvHint;
    }

    @Override
    protected void processData() {
        if (!NotificationManagerCompat.from(this.mContext).areNotificationsEnabled()) {
            showPopForOpen();
        }
        initSwitch();
        this.tvHint.setText(R.string.message_notification_open_hint_2);
        this.notificationSystem.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                toSystemNotification();
            }
        });
    }

    private void initSwitch() {
        MessagePermission messagePermission = SpAPI.THIS.getMessagePermission();
        this.messagePermission = messagePermission;
        if (messagePermission.getAccountActivityOpenStatus()) {
            this.switchButton.setChecked(true);
        } else {
            this.switchButton.setChecked(false);
        }
        this.switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public final void onCheckedChanged(SwitchButton switchButton, boolean z) {
                lambda$initSwitch$0(switchButton, z);
            }
        });
        this.switchButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                boolean lambda$initSwitch$1;
                lambda$initSwitch$1 = lambda$initSwitch$1(view, motionEvent);
                return lambda$initSwitch$1;
            }
        });
    }

    public void lambda$initSwitch$0(SwitchButton switchButton, boolean z) {
        if (!z || this.messagePermission.getAccountActivityOpenStatus()) {
            return;
        }
        showPopForOpenHint();
    }

    public boolean lambda$initSwitch$1(View view, MotionEvent motionEvent) {
        if (this.switchButton.isChecked()) {
            showPopForClose();
            return true;
        }
        showPopForOpenHint();
        return true;
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }

    public void toSystemNotification() {
        try {
            Intent intent = new Intent();
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("android.provider.extra.APP_PACKAGE", getPackageName());
            intent.putExtra("android.provider.extra.CHANNEL_ID", getApplicationInfo().uid);
            intent.putExtra("app_package", getPackageName());
            intent.putExtra("app_uid", getApplicationInfo().uid);
            startActivity(intent);
        } catch (Exception e) {
            LogUtils.e(e);
            Intent intent2 = new Intent();
            intent2.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent2.setData(Uri.fromParts("package", getPackageName(), null));
            startActivity(intent2);
        }
    }

    public void showPopForClose() {
        BasePopupView basePopupView = this.showPopForClosePop;
        if (basePopupView == null) {
            this.showPopForClosePop = ConfirmCustomPopupView.getBuilder(getIContext()).setTitle(getString(R.string.close_notifications)).setTitleSize(16).setTitleBold(true).setConfirmText(getResources().getString(R.string.cancel)).setCancelText(getString(R.string.off2)).setCancleListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    lambda$showPopForClose$2(view);
                }
            }).build().show();
        } else if (basePopupView.isShow()) {
        } else {
            this.showPopForClosePop.show();
        }
    }

    public void lambda$showPopForClose$2(View view) {
        closeSwitchButton();
        FirebaseMessaging.getInstance().deleteToken().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                SpAPI.THIS.setFireBaseToken("");
            }
        });
    }

    public void showPopForOpen() {
        BasePopupView basePopupView = this.showPopForOpenPop;
        if (basePopupView == null) {
            this.showPopForOpenPop = ConfirmCustomPopupView.getBuilder(getIContext()).setTitle(getString(R.string.require_notifications)).setTitleSize(16).setInfo(getResources().getString(R.string.require_notifications_content)).setConfirmText(getResources().getString(R.string.on2)).setCancelText(getResources().getString(R.string.cancel)).setConfirmListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    lambda$showPopForOpen$3(view);
                }
            }).build().show();
        } else if (basePopupView.isShow()) {
        } else {
            this.showPopForOpenPop.show();
        }
    }

    public void lambda$showPopForOpen$3(View view) {
        toSystemNotification();
    }

    public void showPopForOpenHint() {
        BasePopupView basePopupView = this.showPopForOpenHintPop;
        if (basePopupView == null) {
            this.showPopForOpenHintPop = ConfirmCustomPopupView.getBuilder(getIContext()).setTitleSize(16).setTitle(getString(R.string.message_notification_open_hint)).setShowCancelBtn(false).setConfirmText(getResources().getString(R.string.message_notification_open_hint_btn)).setConfirmListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    lambda$showPopForOpenHint$4(view);
                }
            }).build().show();
        } else if (basePopupView.isShow()) {
        } else {
            this.showPopForOpenHintPop.show();
        }
    }

    public void lambda$showPopForOpenHint$4(View view) {
        openSwitchButton();
    }

    public void closeSwitchButton() {
        this.switchButton.setChecked(false);
        Toast(R.string.notification_closed);
        this.messagePermission.setAccountActivityOpenStatus(false);
        SpAPI.THIS.setMessagePermission(this.messagePermission);
        WebSocketManager.getInstance().sendBasicParam(false);
    }

    public void openSwitchButton() {
        this.switchButton.setChecked(true);
        this.messagePermission.setAccountActivityOpenStatus(true);
        SpAPI.THIS.setMessagePermission(this.messagePermission);
        Toast(R.string.notification_opened);
        WebSocketManager.getInstance().sendBasicParam(true);
    }
}
