package com.tron.wallet.business.reminder;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import com.arasthel.asyncjob.AsyncJob;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.interfaces.XPopupCallback;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.wallet.business.addassets2.repository.KVGlobalController;
import com.tron.wallet.business.maintab.MainTabPresenter;
import com.tron.wallet.common.bean.update.UpdateOutput;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.db.SpAPI;
import io.reactivex.functions.Consumer;
import java.util.Date;
public class UpdateReminder extends BaseReminder {
    public static final String ID = "UpdateReminder";
    private static final long TIME_INTERVAL = 259200000;
    private UpdateOutput updateData = TronConfig.updateOutput;

    @Override
    public String getId() {
        return ID;
    }

    public UpdateReminder() {
        ReminderManager.getInstance().getRxManager().on(Event.DD2, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$new$0(obj);
            }
        });
    }

    public void lambda$new$0(Object obj) throws Exception {
        if (obj != null && (obj instanceof UpdateOutput)) {
            this.updateData = (UpdateOutput) obj;
            if (getState() == ReminderState.CHECKING) {
                setState(ReminderState.CHECKING_DONE);
                return;
            }
            return;
        }
        setState(ReminderState.UNNEEDED_SHOW);
    }

    private String getLatestCheckedVersion() {
        return KVGlobalController.getInstance().getValue("UpdateReminder:version");
    }

    private void setLatestCheckedVersion(String str) {
        KVGlobalController.getInstance().setValue("UpdateReminder:version", str);
    }

    private void saveLatestRemindTime(long j) {
        SpAPI.THIS.setUpdateTime(j);
    }

    private boolean outTime() {
        long updateTime = SpAPI.THIS.getUpdateTime();
        return updateTime == 0 || updateTime + TIME_INTERVAL < new Date().getTime();
    }

    private boolean shouldShow() {
        UpdateOutput updateOutput;
        if (IRequest.isShasta() || (updateOutput = this.updateData) == null || updateOutput.code != 0 || this.updateData.data == null) {
            return false;
        }
        String latestCheckedVersion = getLatestCheckedVersion();
        if (this.updateData.data.force) {
            return true;
        }
        if (!this.updateData.data.upgrade || (latestCheckedVersion != null && latestCheckedVersion.equals(this.updateData.data.version))) {
            return this.updateData.data.upgrade && outTime();
        }
        return true;
    }

    @Override
    public void start() {
        if (IRequest.isShasta()) {
            setState(ReminderState.UNNEEDED_SHOW);
        } else if (this.updateData != null) {
            setState(shouldShow() ? ReminderState.NEED_SHOW : ReminderState.UNNEEDED_SHOW);
        } else {
            setState(ReminderState.CHECKING);
        }
    }

    @Override
    public void show(final Context context) {
        AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
            @Override
            public final void doInUIThread() {
                lambda$show$1(context);
            }
        });
    }

    public void lambda$show$1(Context context) {
        MainTabPresenter.updateHelper.showUpdateDialog(context, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReminderManager.getInstance().destroy();
            }
        }, new XPopupCallback() {
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
        });
    }

    @Override
    public void asyncHandleOnStateChanged() {
        super.asyncHandleOnStateChanged();
        if (getState() == ReminderState.HAS_SHOW) {
            saveLatestRemindTime(new Date().getTime());
            setLatestCheckedVersion(this.updateData.data.version);
        }
    }

    @Override
    public void syncState(String str, ReminderState reminderState) {
        super.syncState(str, reminderState);
        if (LargeAssetsReminder.ID.equals(str) && reminderState == ReminderState.HAS_SHOW) {
            long updateTime = SpAPI.THIS.getUpdateTime();
            long time = new Date().getTime();
            if (updateTime == 0 || time - updateTime > 172800000) {
                saveLatestRemindTime(time - 172800000);
            }
        }
    }
}
