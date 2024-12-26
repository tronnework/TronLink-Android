package com.squareup.okhttp.internal.framed;

import java.io.IOException;
import java.util.List;
import okio.BufferedSource;
public interface PushObserver {
    public static final PushObserver CANCEL = new PushObserver() {
        @Override
        public boolean onHeaders(int i, List<Header> list, boolean z) {
            return true;
        }

        @Override
        public boolean onRequest(int i, List<Header> list) {
            return true;
        }

        @Override
        public void onReset(int i, ErrorCode errorCode) {
        }

        @Override
        public boolean onData(int i, BufferedSource bufferedSource, int i2, boolean z) throws IOException {
            bufferedSource.skip(i2);
            return true;
        }
    };

    boolean onData(int i, BufferedSource bufferedSource, int i2, boolean z) throws IOException;

    boolean onHeaders(int i, List<Header> list, boolean z);

    boolean onRequest(int i, List<Header> list);

    void onReset(int i, ErrorCode errorCode);
}
