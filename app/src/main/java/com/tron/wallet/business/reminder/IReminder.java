package com.tron.wallet.business.reminder;

import android.content.Context;
public interface IReminder {
    void asyncHandleOnStateChanged();

    String getId();

    int getPriority();

    ReminderState getState();

    void setPriority(int i);

    void setState(ReminderState reminderState);

    void show(Context context);

    void start();

    void syncState(String str, ReminderState reminderState);
}
