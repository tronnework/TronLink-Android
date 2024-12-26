package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.ResponseBody;
import okio.BufferedSource;
public final class RealResponseBody extends ResponseBody {
    private final Headers headers;
    private final BufferedSource source;

    @Override
    public BufferedSource source() {
        return this.source;
    }

    public RealResponseBody(Headers headers, BufferedSource bufferedSource) {
        this.headers = headers;
        this.source = bufferedSource;
    }

    @Override
    public MediaType contentType() {
        String str = this.headers.get("Content-Type");
        if (str != null) {
            return MediaType.parse(str);
        }
        return null;
    }

    @Override
    public long contentLength() {
        return OkHeaders.contentLength(this.headers);
    }
}
