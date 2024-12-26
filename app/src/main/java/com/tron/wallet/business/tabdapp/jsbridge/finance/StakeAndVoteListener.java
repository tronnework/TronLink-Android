package com.tron.wallet.business.tabdapp.jsbridge.finance;

import org.tron.protos.Protocol;
public interface StakeAndVoteListener {
    void onResponse(Protocol.Transaction transaction, Protocol.Transaction transaction2);
}
