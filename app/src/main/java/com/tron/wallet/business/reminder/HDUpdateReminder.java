package com.tron.wallet.business.reminder;

import android.content.Context;
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
import com.tronlinkpro.wallet.R;
public class HDUpdateReminder extends BaseReminder {
    private static boolean HD_UPDATE_SHOW = false;
    public static final String ID = "HDUpdateReminder";

    public static void setShow(boolean z) {
        HD_UPDATE_SHOW = z;
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public void start() {
        setState(HD_UPDATE_SHOW ? ReminderState.NEED_SHOW : ReminderState.UNNEEDED_SHOW);
    }

    @Override
    public void show(Context context) {
        super.show(context);
        HD_UPDATE_SHOW = false;
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
                ((ImageView) findViewById(R.id.iv_icon)).setImageResource(R.mipmap.ic_hd_update);
                TextView textView = (TextView) findViewById(R.id.tv_title);
                findViewById(R.id.tv_info).setVisibility(View.GONE);
                ((Button) findViewById(R.id.btn_cancel)).setVisibility(View.GONE);
                ((Button) findViewById(R.id.btn_confirm)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });
            }
        }).show();
    }
}
