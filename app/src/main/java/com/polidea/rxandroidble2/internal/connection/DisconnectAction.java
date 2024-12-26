package com.polidea.rxandroidble2.internal.connection;

import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble2.internal.operations.DisconnectOperation;
import com.polidea.rxandroidble2.internal.serialization.ClientOperationQueue;
import io.reactivex.internal.functions.Functions;
@ConnectionScope
class DisconnectAction implements ConnectionSubscriptionWatcher {
    private final ClientOperationQueue clientOperationQueue;
    private final DisconnectOperation operationDisconnect;

    @Override
    public void onConnectionSubscribed() {
    }

    @Inject
    public DisconnectAction(ClientOperationQueue clientOperationQueue, DisconnectOperation disconnectOperation) {
        this.clientOperationQueue = clientOperationQueue;
        this.operationDisconnect = disconnectOperation;
    }

    @Override
    public void onConnectionUnsubscribed() {
        this.clientOperationQueue.queue(this.operationDisconnect).subscribe(Functions.emptyConsumer(), Functions.emptyConsumer());
    }
}
