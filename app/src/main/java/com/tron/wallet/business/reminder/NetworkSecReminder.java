package com.tron.wallet.business.reminder;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.core.CenterPopupView;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.interfaces.XPopupCallback;
import com.lxj.xpopup.util.XPopupUtils;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
public class NetworkSecReminder extends BaseReminder {
    public static final String ID = "NetworkSecReminder";

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public void start() {
        setState((SpAPI.THIS.isCold() || !canShow()) ? ReminderState.UNNEEDED_SHOW : ReminderState.NEED_SHOW);
    }

    @Override
    public void show(Context context) {
        super.show(context);
        new XPopup.Builder(context).maxWidth(XPopupUtils.getScreenWidth(context)).dismissOnTouchOutside(false).popupAnimation(PopupAnimation.NoAnimation).setPopupCallback(new XPopupCallback() {
            @Override
            public void beforeDismiss(BasePopupView basePopupView) {
            }

            @Override
            public void beforeShow(BasePopupView basePopupView) {
            }

            @Override
            public boolean onBackPressed(BasePopupView basePopupView) {
                return false;
            }

            @Override
            public void onClickOutside(BasePopupView basePopupView) {
            }

            @Override
            public void onCreated(BasePopupView basePopupView) {
            }

            @Override
            public void onDrag(BasePopupView basePopupView, int i, float f, boolean z) {
            }

            @Override
            public void onKeyBoardStateChanged(BasePopupView basePopupView, int i) {
            }

            @Override
            public void onShow(final BasePopupView basePopupView) {
                basePopupView.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View view, int i, KeyEvent keyEvent) {
                        if (i == 4 && keyEvent.getAction() == 0) {
                            basePopupView.dismiss();
                            return true;
                        }
                        return false;
                    }
                });
            }

            @Override
            public void onDismiss(BasePopupView basePopupView) {
                setState(ReminderState.HAS_SHOW);
            }
        }).asCustom(new CenterPopupView(context) {
            @Override
            public int getImplLayoutId() {
                return R.layout.popup_view_with_title_icon;
            }

            @Override
            public void onCreate() {
                super.onCreate();
                ((ImageView) findViewById(R.id.iv_icon)).setImageResource(R.mipmap.ic_network_sec_title);
                ((TextView) findViewById(R.id.tv_title)).setText(R.string.secure_warning);
                ((TextView) findViewById(R.id.tv_info)).setText(R.string.network_sec_tip);
                ((Button) findViewById(R.id.btn_cancel)).setVisibility(View.GONE);
                Button button = (Button) findViewById(R.id.btn_confirm);
                button.setText(R.string.network_sec_tip_ok);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });
            }
        }).show();
    }

    private boolean canShow() {
        WifiInfo connectionInfo;
        int currentSecurityType;
        try {
            Context applicationContext = AppContextUtil.getContext().getApplicationContext();
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable() || activeNetworkInfo.getType() != 1 || (connectionInfo = ((WifiManager) applicationContext.getSystemService(Context.WIFI_SERVICE)).getConnectionInfo()) == null || Build.VERSION.SDK_INT < 31) {
                return false;
            }
            currentSecurityType = connectionInfo.getCurrentSecurityType();
            return currentSecurityType == 0 || currentSecurityType == 1;
        } catch (Throwable th) {
            LogUtils.e(th);
            return false;
        }
    }
}
