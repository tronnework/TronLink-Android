package com.tron.wallet.common.utils.progressmanager;

import android.os.Handler;
import android.os.SystemClock;
import java.io.IOException;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;
public class ProgressRequestBody extends RequestBody {
    private BufferedSink mBufferedSink;
    protected final RequestBody mDelegate;
    protected Handler mHandler;
    protected final ProgressListener[] mListeners;
    protected final ProgressInfo mProgressInfo = new ProgressInfo(System.currentTimeMillis());
    protected int mRefreshTime;

    public ProgressRequestBody(Handler handler, RequestBody requestBody, List<ProgressListener> list, int i) {
        this.mDelegate = requestBody;
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
        try {
            return this.mDelegate.contentLength();
        } catch (IOException e) {
            e.printStackTrace();
            return -1L;
        }
    }

    @Override
    public void writeTo(BufferedSink bufferedSink) throws IOException {
        if (this.mBufferedSink == null) {
            this.mBufferedSink = Okio.buffer(new CountingSink(bufferedSink));
        }
        try {
            this.mDelegate.writeTo(this.mBufferedSink);
            this.mBufferedSink.flush();
        } catch (IOException e) {
            e.printStackTrace();
            int i = 0;
            while (true) {
                ProgressListener[] progressListenerArr = this.mListeners;
                if (i >= progressListenerArr.length) {
                    break;
                }
                progressListenerArr[i].onError(this.mProgressInfo.getId(), e);
                i++;
            }
            throw e;
        }
    }

    protected final class CountingSink extends ForwardingSink {
        private long lastRefreshTime;
        private long tempSize;
        private long totalBytesRead;

        public CountingSink(Sink sink) {
            super(sink);
            this.totalBytesRead = 0L;
            this.lastRefreshTime = 0L;
            this.tempSize = 0L;
        }

        @Override
        public void write(Buffer buffer, long j) throws IOException {
            try {
                super.write(buffer, j);
                if (mProgressInfo.getContentLength() == 0) {
                    mProgressInfo.setContentLength(contentLength());
                }
                this.totalBytesRead += j;
                this.tempSize += j;
                if (mListeners != null) {
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    if (elapsedRealtime - this.lastRefreshTime >= mRefreshTime || this.totalBytesRead == mProgressInfo.getContentLength()) {
                        long j2 = this.tempSize;
                        final long j3 = this.totalBytesRead;
                        final long j4 = elapsedRealtime - this.lastRefreshTime;
                        int i = 0;
                        while (i < mListeners.length) {
                            final ProgressListener progressListener = mListeners[i];
                            final long j5 = j2;
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressInfo.setEachBytes(j5);
                                    mProgressInfo.setCurrentbytes(j3);
                                    mProgressInfo.setIntervalTime(j4);
                                    mProgressInfo.setFinish(j3 == mProgressInfo.getContentLength());
                                    progressListener.onProgress(mProgressInfo);
                                }
                            });
                            i++;
                            j2 = j2;
                        }
                        this.lastRefreshTime = elapsedRealtime;
                        this.tempSize = 0L;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                for (int i2 = 0; i2 < mListeners.length; i2++) {
                    mListeners[i2].onError(mProgressInfo.getId(), e);
                }
                throw e;
            }
        }
    }
}
