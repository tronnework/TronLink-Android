package com.tron.wallet.net;

import com.tron.wallet.common.config.Event;
public class NetMessageType {
    public static final int ACK = 10000;
    public static final int BASIC_PARAM = 0;
    public static final int NEW_ASSETS = 1;
    public static final int TRANSFER_RECEIVE = 3;
    public static final int TRANSFER_SEND = 2;

    public static String getEvent(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "" : Event.WS_MSG_TRANSFER_RECEIVE : Event.WS_MSG_TRANSFER_SEND : Event.WS_MSG_NEW_ASSETS;
    }

    public static int getTransactionTypeByMsg(int i) {
        return (i != 2 && i == 3) ? 1 : 0;
    }
}
