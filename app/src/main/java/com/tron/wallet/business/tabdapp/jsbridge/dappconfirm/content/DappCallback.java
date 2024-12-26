package com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content;

import android.util.Pair;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
public interface DappCallback {
    void onRefreshAccountComplete(boolean z, Protocol.Transaction transaction, Pair<Protocol.Account, GrpcAPI.AccountResourceMessage> pair);
}
