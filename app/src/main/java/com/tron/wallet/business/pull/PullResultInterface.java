package com.tron.wallet.business.pull;
public interface PullResultInterface {
    void onDAppResultFailure();

    void onDAppResultResponse();
}
