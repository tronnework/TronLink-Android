package com.tron.wallet.business.reminder;

import android.content.Context;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import java.util.LinkedHashMap;
import java.util.Map;
public class ReminderManager {
    private static final int PRIORITY_HD_UPDATE = 0;
    private static final int PRIORITY_LARGE_ASSETS = 30;
    private static final int PRIORITY_NETWORK_SEC = 40;
    private static final int PRIORITY_PUSH_MSG = 10;
    private static final int PRIORITY_ROOT_CHECKER = 5;
    private static final int PRIORITY_SHIELD_MAINLAND_NOTICE = 45;
    private static final int PRIORITY_UPDATER = 20;
    private static final int PRIORITY_WALLET_INTRO = 50;
    private static final String TAG = "ReminderManager";
    private static ReminderManager instance;
    private Context context;
    private volatile boolean destroyed;
    private Map<String, IReminder> reminderMap = new LinkedHashMap();
    private RxManager rxManager = new RxManager();
    private PublishSubject<String> subject;

    public RxManager getRxManager() {
        return this.rxManager;
    }

    private ReminderManager() {
    }

    public static ReminderManager getInstance() {
        if (instance == null) {
            synchronized (ReminderManager.class) {
                if (instance == null) {
                    instance = new ReminderManager();
                }
            }
        }
        return instance;
    }

    public void destroy() {
        this.destroyed = true;
        for (String str : this.reminderMap.keySet()) {
            this.reminderMap.get(str).setState(ReminderState.UNNEEDED_SHOW);
        }
    }

    private void checkPriorityAndShow(IReminder iReminder) {
        for (String str : this.reminderMap.keySet()) {
            IReminder iReminder2 = this.reminderMap.get(str);
            if (iReminder2.getPriority() < iReminder.getPriority() && iReminder2.getState() != ReminderState.HAS_SHOW && iReminder2.getState() != ReminderState.UNNEEDED_SHOW && iReminder2.getState() != ReminderState.CHECKING_TIMEOUT) {
                LogUtils.d(TAG, "checkPriorityAndShow: waiting: " + iReminder2.getId());
                return;
            }
        }
        LogUtils.d(TAG, "checkPriorityAndShow:" + iReminder.getId());
        iReminder.show(this.context);
    }

    private com.tron.wallet.business.reminder.IReminder findNextAvailableReminder(com.tron.wallet.business.reminder.IReminder r6) {
        


return null;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.reminder.ReminderManager.findNextAvailableReminder(com.tron.wallet.business.reminder.IReminder):com.tron.wallet.business.reminder.IReminder");
    }

    private void initReminders() {
        PublishSubject<String> create = PublishSubject.create();
        this.subject = create;
        create.flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$initReminders$0;
                lambda$initReminders$0 = lambda$initReminders$0((String) obj);
                return lambda$initReminders$0;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initReminders$1((String) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                LogUtils.d(ReminderManager.TAG, "" + ((Throwable) obj));
            }
        });
        HDUpdateReminder hDUpdateReminder = new HDUpdateReminder();
        hDUpdateReminder.setPriority(0);
        this.reminderMap.put(hDUpdateReminder.getId(), hDUpdateReminder);
        RootCheckerReminder rootCheckerReminder = new RootCheckerReminder();
        rootCheckerReminder.setPriority(5);
        this.reminderMap.put(rootCheckerReminder.getId(), rootCheckerReminder);
        PushMessageReminder pushMessageReminder = new PushMessageReminder();
        pushMessageReminder.setPriority(10);
        this.reminderMap.put(pushMessageReminder.getId(), pushMessageReminder);
        UpdateReminder updateReminder = new UpdateReminder();
        updateReminder.setPriority(20);
        this.reminderMap.put(updateReminder.getId(), updateReminder);
        LargeAssetsReminder largeAssetsReminder = new LargeAssetsReminder();
        largeAssetsReminder.setPriority(30);
        this.reminderMap.put(largeAssetsReminder.getId(), largeAssetsReminder);
        NetworkSecReminder networkSecReminder = new NetworkSecReminder();
        networkSecReminder.setPriority(40);
        this.reminderMap.put(networkSecReminder.getId(), networkSecReminder);
        ShieldMainlandNoticeReminder shieldMainlandNoticeReminder = new ShieldMainlandNoticeReminder();
        shieldMainlandNoticeReminder.setPriority(45);
        this.reminderMap.put(shieldMainlandNoticeReminder.getId(), shieldMainlandNoticeReminder);
        WalletIntroReminder walletIntroReminder = new WalletIntroReminder();
        walletIntroReminder.setPriority(50);
        this.reminderMap.put(walletIntroReminder.getId(), walletIntroReminder);
    }

    public ObservableSource lambda$initReminders$0(String str) throws Exception {
        IReminder iReminder = this.reminderMap.get(str);
        if (iReminder != null && !this.destroyed) {
            LogUtils.d(TAG, iReminder.getId() + " state:" + iReminder.getState());
            int i = fun1.$SwitchMap$com$tron$wallet$business$reminder$ReminderState[iReminder.getState().ordinal()];
            if (i == 1 || i == 4) {
                iReminder.start();
            }
            iReminder.asyncHandleOnStateChanged();
        }
        return Observable.just(str);
    }

    public void lambda$initReminders$1(String str) throws Exception {
        IReminder findNextAvailableReminder;
        IReminder iReminder = this.reminderMap.get(str);
        if (iReminder == null || this.destroyed) {
            return;
        }
        int i = fun1.$SwitchMap$com$tron$wallet$business$reminder$ReminderState[iReminder.getState().ordinal()];
        if (i == 6) {
            checkPriorityAndShow(iReminder);
        } else if ((i == 7 || i == 8) && (findNextAvailableReminder = findNextAvailableReminder(iReminder)) != null) {
            checkPriorityAndShow(findNextAvailableReminder);
        }
    }

    public static class fun1 {
        static final int[] $SwitchMap$com$tron$wallet$business$reminder$ReminderState;

        static {
            int[] iArr = new int[ReminderState.values().length];
            $SwitchMap$com$tron$wallet$business$reminder$ReminderState = iArr;
            try {
                iArr[ReminderState.INIT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$reminder$ReminderState[ReminderState.CHECKING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$reminder$ReminderState[ReminderState.CHECKING_TIMEOUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$reminder$ReminderState[ReminderState.CHECKING_DONE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$reminder$ReminderState[ReminderState.SHOWING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$reminder$ReminderState[ReminderState.NEED_SHOW.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$reminder$ReminderState[ReminderState.HAS_SHOW.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$reminder$ReminderState[ReminderState.UNNEEDED_SHOW.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public void showReminder(Context context) {
        LogUtils.d(TAG, "showReminder");
        this.context = context;
        initReminders();
        this.destroyed = false;
        for (String str : this.reminderMap.keySet()) {
            this.subject.onNext(str);
        }
    }

    public void syncState(IReminder iReminder, ReminderState reminderState) {
        LogUtils.d(TAG, iReminder.getId() + ":" + reminderState);
        for (String str : this.reminderMap.keySet()) {
            this.reminderMap.get(str).syncState(iReminder.getId(), reminderState);
        }
        this.subject.onNext(iReminder.getId());
    }
}
