package org.tron.net;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
public class IHttpClient {
    private static final int DEFAULT_TIMEOUT = 8;

    public static OkHttpClient.Builder getHttpClientBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(8L, TimeUnit.SECONDS).writeTimeout(8L, TimeUnit.SECONDS).readTimeout(8L, TimeUnit.SECONDS);
        builder.addInterceptor(new Interceptor() {
            @Override
            public final Response intercept(Interceptor.Chain chain) {
                Response proceed;
                proceed = chain.proceed(IHttpClient.getBuilder(chain).build());
                return proceed;
            }
        });
        return builder;
    }

    private static Request.Builder getBuilder(Interceptor.Chain chain) throws UnsupportedEncodingException {
        Request request = chain.request();
        Request.Builder addHeader = request.newBuilder().addHeader("", "");
        request.url().toString().replaceAll("base&url", "");
        return addHeader;
    }
}
