package com.tron.tron_base.frame.net;

import com.tron.tron_base.frame.utils.LogUtils;
import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSource;
public class TronHttpLoggingInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private volatile Level level;
    private final Logger logger;

    public enum Level {
        NONE,
        BASIC,
        HEADERS,
        BODY
    }

    public interface Logger {
        public static final Logger DEFAULT = new Logger() {
            @Override
            public void log(String str) {
                Platform.get().log(str, 4, null);
            }
        };

        void log(String str);
    }

    public Level getLevel() {
        return this.level;
    }

    public TronHttpLoggingInterceptor() {
        this(Logger.DEFAULT);
    }

    public TronHttpLoggingInterceptor(Logger logger) {
        this.level = Level.NONE;
        this.logger = logger;
    }

    public TronHttpLoggingInterceptor setLevel(Level level) {
        if (level != null) {
            this.level = level;
            return this;
        }
        throw new NullPointerException("level == null. Use Level.NONE instead.");
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        String str;
        long j;
        String str2;
        Headers headers;
        Level level = this.level;
        Request request = chain.request();
        if (level == Level.NONE) {
            return chain.proceed(request);
        }
        boolean z = level == Level.BODY;
        boolean z2 = z || level == Level.HEADERS;
        RequestBody body = request.body();
        boolean z3 = body != null;
        Connection connection = chain.connection();
        StringBuilder sb = new StringBuilder("--> ");
        sb.append(request.method());
        sb.append(' ');
        sb.append(request.url());
        sb.append(connection != null ? " " + connection.protocol() : "");
        String sb2 = sb.toString();
        if (z2 || !z3) {
            str = " ";
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(sb2);
            sb3.append(" (");
            str = " ";
            sb3.append(body.contentLength());
            sb3.append("-byte body)");
            sb2 = sb3.toString();
        }
        LogUtils.i(sb2);
        if (z2) {
            if (z3) {
                if (body.contentType() != null) {
                    LogUtils.i("Content-Type: " + body.contentType());
                }
                if (body.contentLength() != -1) {
                    LogUtils.i("Content-Length: " + body.contentLength());
                }
            }
            Headers headers2 = request.headers();
            int size = headers2.size();
            int i = 0;
            while (i < size) {
                String name = headers2.name(i);
                int i2 = size;
                if (!"Content-Type".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name)) {
                    LogUtils.i(name + ": " + headers2.value(i));
                }
                i++;
                size = i2;
            }
            if (!z || !z3) {
                LogUtils.i("--> END " + request.method());
            } else if (bodyEncoded(request.headers())) {
                LogUtils.i("--> END " + request.method() + " (encoded body omitted)");
            } else {
                Buffer buffer = new Buffer();
                body.writeTo(buffer);
                Charset charset = UTF8;
                MediaType contentType = body.contentType();
                if (contentType != null) {
                    charset = contentType.charset(charset);
                }
                LogUtils.i("");
                if (isPlaintext(buffer)) {
                    LogUtils.i(buffer.readString(charset));
                    LogUtils.i("--> END " + request.method() + " (" + body.contentLength() + "-byte body)");
                } else {
                    LogUtils.i("--> END " + request.method() + " (binary " + body.contentLength() + "-byte body omitted)");
                }
            }
        }
        long nanoTime = System.nanoTime();
        try {
            Response proceed = chain.proceed(request);
            long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime);
            ResponseBody body2 = proceed.body();
            long contentLength = body2.contentLength();
            String str3 = contentLength != -1 ? contentLength + "-byte" : "unknown-length";
            StringBuilder sb4 = new StringBuilder("<-- ");
            sb4.append(proceed.code());
            if (proceed.message().isEmpty()) {
                str2 = "";
                j = contentLength;
            } else {
                j = contentLength;
                str2 = str + proceed.message();
            }
            sb4.append(str2);
            sb4.append(' ');
            sb4.append(proceed.request().url());
            sb4.append(" (");
            sb4.append(millis);
            sb4.append("ms");
            sb4.append(z2 ? "" : ", " + str3 + " body");
            sb4.append(')');
            LogUtils.i(sb4.toString());
            if (z2) {
                int size2 = proceed.headers().size();
                for (int i3 = 0; i3 < size2; i3++) {
                    LogUtils.i(headers.name(i3) + ": " + headers.value(i3));
                }
                if (!z || !HttpHeaders.hasBody(proceed)) {
                    LogUtils.i("<-- END HTTP");
                } else if (bodyEncoded(proceed.headers())) {
                    LogUtils.i("<-- END HTTP (encoded body omitted)");
                } else {
                    BufferedSource source = body2.source();
                    source.request(Long.MAX_VALUE);
                    Buffer buffer2 = source.buffer();
                    Charset charset2 = UTF8;
                    MediaType contentType2 = body2.contentType();
                    if (contentType2 != null) {
                        charset2 = contentType2.charset(charset2);
                    }
                    if (!isPlaintext(buffer2)) {
                        LogUtils.i("");
                        LogUtils.i("<-- END HTTP (binary " + buffer2.size() + "-byte body omitted)");
                        return proceed;
                    }
                    if (j != 0) {
                        LogUtils.i("");
                        LogUtils.i(buffer2.clone().readString(charset2));
                    }
                    LogUtils.i("<-- END HTTP (" + buffer2.size() + "-byte body)");
                }
            }
            return proceed;
        } catch (Exception e) {
            LogUtils.i("<-- HTTP FAILED: " + e);
            throw e;
        }
    }

    static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer buffer2 = new Buffer();
            buffer.copyTo(buffer2, 0L, buffer.size() < 64 ? buffer.size() : 64L);
            for (int i = 0; i < 16; i++) {
                if (buffer2.exhausted()) {
                    return true;
                }
                int readUtf8CodePoint = buffer2.readUtf8CodePoint();
                if (Character.isISOControl(readUtf8CodePoint) && !Character.isWhitespace(readUtf8CodePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }

    private boolean bodyEncoded(Headers headers) {
        String str = headers.get(com.google.common.net.HttpHeaders.CONTENT_ENCODING);
        return (str == null || str.equalsIgnoreCase("identity")) ? false : true;
    }
}
