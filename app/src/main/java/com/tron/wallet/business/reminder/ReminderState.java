package com.tron.wallet.business.reminder;
public enum ReminderState {
    INIT,
    CHECKING,
    CHECKING_TIMEOUT,
    CHECKING_DONE,
    NEED_SHOW,
    SHOWING,
    HAS_SHOW,
    UNNEEDED_SHOW
}
