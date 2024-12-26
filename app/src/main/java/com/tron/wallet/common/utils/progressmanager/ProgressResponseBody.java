package com.tron.wallet.common.utils.progressmanager;

import android.os.Handler;
import android.os.SystemClock;
import java.io.IOException;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;
public class ProgressResponseBody extends ResponseBody {
    private BufferedSource mBufferedSource;
    protected final ResponseBody mDelegate;
    protected Handler mHandler;
    protected final ProgressListener[] mListeners;
    protected final ProgressInfo mProgressInfo = new ProgressInfo(System.currentTimeMillis());
    protected int mRefreshTime;

    public ProgressResponseBody(Handler handler, ResponseBody responseBody, List<ProgressListener> list, int i) {
        this.mDelegate = responseBody;
        this.mListeners = (ProgressListener[]) list.toArray(new ProgressListener[list.size()]);
        this.mHandler = handler;
        this.mRefreshTime = i;
    }

    @Override
    public MediaType contentType() {
        return this.mDelegate.contentType();
    }

    @Override
    public long contentLength() {
        return this.mDelegate.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (this.mBufferedSource == null) {
            this.mBufferedSource = Okio.buffer(source(this.mDelegate.source()));
        }
        return this.mBufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            private long totalBytesRead = 0;
            private long lastRefreshTime = 0;
            private long tempSize = 0;

            @Override
            public long read(Buffer buffer, long j) throws IOException {
                1 r12 = this;
                try {
                    long read = super.read(buffer, j);
                    if (mProgressInfo.getContentLength() == 0) {
                        mProgressInfo.setContentLength(contentLength());
                    }
                    int i = (read > (-1L) ? 1 : (read == (-1L) ? 0 : -1));
                    r12.totalBytesRead += i != 0 ? read : 0L;
                    r12.tempSize += i != 0 ? read : 0L;
                    if (mListeners != null) {
                        long elapsedRealtime = SystemClock.elapsedRealtime();
                        if (elapsedRealtime - r12.lastRefreshTime >= mRefreshTime || i == 0 || r12.totalBytesRead == mProgressInfo.getContentLength()) {
                            final long j2 = r12.tempSize;
                            long j3 = r12.totalBytesRead;
                            final long j4 = elapsedRealtime - r12.lastRefreshTime;
                            int i2 = 0;
                            while (i2 < mListeners.length) {
                                final ProgressListener progressListener = mListeners[i2];
                                final long j5 = j3;
                                final long j6 = read;
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        mProgressInfo.setEachBytes(j6 != -1 ? j2 : -1L);
                                        mProgressInfo.setCurrentbytes(j5);
                                        mProgressInfo.setIntervalTime(j4);
                                        mProgressInfo.setFinish(j6 == -1 && j5 == mProgressInfo.getContentLength());
                                        progressListener.onProgress(mProgressInfo);
                                    }
                                });
                                i2++;
                                r12 = this;
                                elapsedRealtime = elapsedRealtime;
                                j3 = j5;
                                read = read;
                            }
                            1 r1 = r12;
                            long j7 = read;
                            r1.lastRefreshTime = elapsedRealtime;
                            r1.tempSize = 0L;
                            return j7;
                        }
                    }
                    return read;
                } catch (IOException e) {
                    e.printStackTrace();
                    for (int i3 = 0; i3 < mListeners.length; i3++) {
                        mListeners[i3].onError(mProgressInfo.getId(), e);
                    }
                    throw e;
                }
            }
        };
    }
}
