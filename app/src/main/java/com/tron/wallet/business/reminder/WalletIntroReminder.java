package com.tron.wallet.business.reminder;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.core.CenterPopupView;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.interfaces.XPopupCallback;
import com.lxj.xpopup.util.XPopupUtils;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.wallet.business.addassets2.repository.KVGlobalController;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
public class WalletIntroReminder extends BaseReminder {
    public static final String ID = "WalletIntroReminder";

    public static void lambda$saveNoMoreRemind$0(Boolean bool) throws Exception {
    }

    @Override
    public String getId() {
        return ID;
    }

    private void saveNoMoreRemind() {
        Observable.unsafeCreate(new ObservableSource<Boolean>() {
            @Override
            public void subscribe(Observer<? super Boolean> observer) {
                KVGlobalController.getInstance().setValue("NoMoreWalletIntroRemind", "true");
                observer.onNext(true);
                observer.onComplete();
            }
        }).subscribeOn(Schedulers.io()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                WalletIntroReminder.lambda$saveNoMoreRemind$0((Boolean) obj);
            }
        });
    }

    private boolean noMoreRemind() {
        String value = KVGlobalController.getInstance().getValue("NoMoreWalletIntroRemind");
        return value != null && value.equals("true");
    }

    @Override
    public void start() {
        setState((SpAPI.THIS.isCold() || !(noMoreRemind() ^ true)) ? ReminderState.UNNEEDED_SHOW : ReminderState.NEED_SHOW);
    }

    @Override
    public void show(final Context context) {
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
                ((Button) findViewById(R.id.btn_cancel)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AnalyticsHelper.logEvent(AnalyticsHelper.MyPage.CLICK_PROFILE_HELP);
                        CommonWebActivityV3.start(context, IRequest.getWalletGuideUrl(), "", 1, true);
                        dismiss();
                    }
                });
                ((Button) findViewById(R.id.btn_confirm)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });
            }
        }).show();
        saveNoMoreRemind();
    }
}
