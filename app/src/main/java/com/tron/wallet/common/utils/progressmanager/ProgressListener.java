package com.tron.wallet.common.utils.progressmanager;
public interface ProgressListener {
    void onError(long j, Exception exc);

    void onProgress(ProgressInfo progressInfo);
}
