package com.tron.wallet.business.reminder;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.core.CenterPopupView;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.interfaces.XPopupCallback;
import com.lxj.xpopup.util.XPopupUtils;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.tabswap.bean.AppStatusInput;
import com.tron.wallet.business.tabswap.bean.AppStatusOutput;
import com.tron.wallet.common.bean.Result2;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.net.HttpAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
public class ShieldMainlandNoticeReminder extends BaseReminder {
    public static final String ID = "ShieldMainlandNoticeReminder";
    private boolean hasChecked;

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public void start() {
        if (!SpAPI.THIS.getShowShieldMainlandNoticeReminder()) {
            AppStatusInput appStatusInput = new AppStatusInput();
            appStatusInput.setFina(false);
            appStatusInput.setMainland(true);
            ((HttpAPI) IRequest.getAPI(HttpAPI.class)).checkAppStatus(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(appStatusInput))).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<Result2<AppStatusOutput>>() {
                @Override
                public void onSubscribe(Disposable disposable) {
                }

                @Override
                public void onResponse(String str, Result2<AppStatusOutput> result2) {
                    if (result2 != null && result2.getCode() == 0 && result2.getData() != null && result2.getData().isMainland()) {
                        setState(ReminderState.NEED_SHOW);
                    } else {
                        setState(ReminderState.UNNEEDED_SHOW);
                    }
                }

                @Override
                public void onFailure(String str, String str2) {
                    setState(ReminderState.UNNEEDED_SHOW);
                }
            }, ID));
            return;
        }
        setState(ReminderState.UNNEEDED_SHOW);
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
                return R.layout.popup_view_shield_mainland_notice;
            }

            @Override
            public void onCreate() {
                super.onCreate();
                View findViewById = findViewById(R.id.ll_i_know);
                final ImageView imageView = (ImageView) findViewById(R.id.iv_select);
                final Button button = (Button) findViewById(R.id.btn_confirm);
                findViewById.setOnClickListener(new NoDoubleClickListener2() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        if (!hasChecked) {
                            imageView.setImageResource(R.mipmap.ic_notice_selected);
                            button.setEnabled(true);
                        } else {
                            imageView.setImageResource(R.mipmap.ic_notice_unselect);
                            button.setEnabled(false);
                        }
                        hasChecked = true ^ hasChecked;
                    }
                });
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SpAPI.THIS.setShowShieldMainlandNoticeReminder();
                        dismiss();
                    }
                });
            }
        }).show();
    }
}
