package com.tron.wallet.business.reminder;

import android.content.Context;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.interfaces.XPopupCallback;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.bean.asset.AssetsHomeData;
import com.tron.wallet.business.addassets2.repository.KVGlobalController;
import com.tron.wallet.business.reminder.LargeAssetsReminder;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.db.Controller.AssetsHomeDataDaoManager;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletType;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.Date;
import org.tron.walletserver.Wallet;
public class LargeAssetsReminder extends BaseReminder {
    public static final String ID = "LargeAssetsReminder";
    private static final int LARGE_ASSETS_AMOUNT = 100000;
    private static final long TIME_INTERVAL = 259200000;
    private String address;
    private boolean unneededRemind;

    public static void lambda$saveLatestRemindTime$1(Boolean bool) throws Exception {
    }

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
                KVGlobalController.getInstance().setValue("NoMoreLargeAssetsRemind", "true");
                observer.onNext(true);
                observer.onComplete();
            }
        }).subscribeOn(Schedulers.io()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                LargeAssetsReminder.lambda$saveNoMoreRemind$0((Boolean) obj);
            }
        });
    }

    private boolean noMoreRemind() {
        String value = KVGlobalController.getInstance().getValue("NoMoreLargeAssetsRemind");
        return value != null && value.equals("true");
    }

    private void saveLatestRemindTime(final long j) {
        Observable.unsafeCreate(new ObservableSource<Boolean>() {
            @Override
            public void subscribe(Observer<? super Boolean> observer) {
                KVGlobalController kVGlobalController = KVGlobalController.getInstance();
                kVGlobalController.setValue("LatestLargeAssetsRemindTime", "" + j);
                observer.onNext(true);
                observer.onComplete();
            }
        }).subscribeOn(Schedulers.io()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                LargeAssetsReminder.lambda$saveLatestRemindTime$1((Boolean) obj);
            }
        });
    }

    private boolean outTime() {
        String value = KVGlobalController.getInstance().getValue("LatestLargeAssetsRemindTime");
        if (value == null) {
            return true;
        }
        try {
            return Long.valueOf(value).longValue() + TIME_INTERVAL < new Date().getTime();
        } catch (Exception e) {
            SentryUtil.captureException(e);
            return false;
        }
    }

    private boolean isBiggerThanRemindLimit(String str) {
        double usdPrice;
        AssetsHomeData addAssetsHomeData = AssetsHomeDataDaoManager.getAddAssetsHomeData(AppContextUtil.getmApplication(), str);
        if (addAssetsHomeData != null) {
            if (addAssetsHomeData.price != null && !BigDecimalUtils.equalsZero((Number) Double.valueOf(addAssetsHomeData.price.priceUSD))) {
                usdPrice = addAssetsHomeData.price.priceUSD;
            } else {
                usdPrice = SpAPI.THIS.getUsdPrice();
            }
            return addAssetsHomeData.totalTRX * usdPrice >= 100000.0d;
        }
        return false;
    }

    @Override
    public void start() {
        ReminderState reminderState;
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        if (selectedWallet != null && WalletType.getType(selectedWallet) == 1 && selectedWallet.getCreateType() != 7) {
            this.address = selectedWallet.getAddress();
            if (!noMoreRemind()) {
                boolean isBiggerThanRemindLimit = isBiggerThanRemindLimit(selectedWallet.getAddress());
                LogUtils.d(ID, "isBiggerThanRemindLimit:" + isBiggerThanRemindLimit);
                if (isBiggerThanRemindLimit) {
                    isBiggerThanRemindLimit = outTime();
                    LogUtils.d(ID, "outTime:" + isBiggerThanRemindLimit);
                }
                if (isBiggerThanRemindLimit) {
                    reminderState = ReminderState.NEED_SHOW;
                    setState(reminderState);
                }
            }
        }
        reminderState = ReminderState.UNNEEDED_SHOW;
        setState(reminderState);
    }

    @Override
    public void show(final Context context) {
        super.show(context);
        ConfirmCustomPopupView.Builder info = ConfirmCustomPopupView.getBuilder(context).setTitle(context.getResources().getString(R.string.security_alert)).setInfo(context.getResources().getString(R.string.security_assets_too_big_info));
        info.setInfo1(Html.fromHtml("<u>" + context.getResources().getString(R.string.cold_wallet_how_to_use) + "</u>")).setConfirmText(context.getResources().getString(R.string.i_know_ok)).setShowCancelBtn(false).setShowTip(true).setAutoDismissOutSide(false).setPopupCallback(new fun3(context)).setConfirmListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$show$2(context, view);
            }
        }).build().show();
        saveLatestRemindTime(new Date().getTime());
    }

    public class fun3 implements XPopupCallback {
        final Context val$context;

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
        public void onDrag(BasePopupView basePopupView, int i, float f, boolean z) {
        }

        @Override
        public void onKeyBoardStateChanged(BasePopupView basePopupView, int i) {
        }

        fun3(Context context) {
            this.val$context = context;
        }

        @Override
        public void onCreated(BasePopupView basePopupView) {
            View findViewById = basePopupView.findViewById(R.id.info_1);
            final Context context = this.val$context;
            findViewById.setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    UIUtils.toColdWallet(context);
                }
            });
            final View findViewById2 = basePopupView.findViewById(R.id.ll_tip);
            findViewById2.setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    LargeAssetsReminder.3.this.lambda$onCreated$1(findViewById2, view);
                }
            });
        }

        public void lambda$onCreated$1(View view, View view2) {
            LargeAssetsReminder largeAssetsReminder = LargeAssetsReminder.this;
            largeAssetsReminder.unneededRemind = !largeAssetsReminder.unneededRemind;
            view.setSelected(unneededRemind);
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
    }

    public void lambda$show$2(Context context, View view) {
        if (this.unneededRemind) {
            saveNoMoreRemind();
        }
        FirebaseAnalytics.getInstance(context).logEvent("Click_large_assets_reminder_confirm", null);
    }

    @Override
    public void syncState(String str, ReminderState reminderState) {
        super.syncState(str, reminderState);
        if (UpdateReminder.ID.equals(str) && reminderState == ReminderState.HAS_SHOW) {
            long updateTime = SpAPI.THIS.getUpdateTime();
            long time = new Date().getTime();
            if (updateTime == 0 || time - updateTime > 172800000) {
                saveLatestRemindTime(time - 172800000);
            }
            setState(ReminderState.UNNEEDED_SHOW);
        }
    }
}
