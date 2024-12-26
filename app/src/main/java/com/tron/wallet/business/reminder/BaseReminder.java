package com.tron.wallet.business.reminder;

import android.content.Context;
public abstract class BaseReminder implements IReminder {
    private int priority;
    private volatile ReminderState state = ReminderState.INIT;

    @Override
    public void asyncHandleOnStateChanged() {
    }

    @Override
    public int getPriority() {
        return this.priority;
    }

    @Override
    public ReminderState getState() {
        return this.state;
    }

    @Override
    public void setPriority(int i) {
        this.priority = i;
    }

    @Override
    public void syncState(String str, ReminderState reminderState) {
    }

    @Override
    public String getId() {
        return getClass().getSimpleName();
    }

    @Override
    public synchronized void setState(ReminderState reminderState) {
        this.state = reminderState;
        ReminderManager.getInstance().syncState(this, reminderState);
    }

    @Override
    public void show(Context context) {
        setState(ReminderState.SHOWING);
    }
}
