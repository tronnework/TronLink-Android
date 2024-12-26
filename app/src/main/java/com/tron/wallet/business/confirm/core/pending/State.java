package com.tron.wallet.business.confirm.core.pending;

import java.io.Serializable;
public enum State implements Serializable {
    Success,
    Fail,
    Load,
    TimeOut
}
