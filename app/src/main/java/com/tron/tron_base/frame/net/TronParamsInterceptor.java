package com.tron.tron_base.frame.net;

import android.text.TextUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
public class TronParamsInterceptor implements Interceptor {
    List<String> headerLinesList;
    Map<String, String> headerParamsMap;
    Map<String, String> paramsMap;
    Map<String, String> queryParamsMap;

    private TronParamsInterceptor() {
        this.queryParamsMap = new HashMap();
        this.paramsMap = new HashMap();
        this.headerParamsMap = new HashMap();
        this.headerLinesList = new ArrayList();
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder newBuilder = request.newBuilder();
        Headers.Builder newBuilder2 = request.headers().newBuilder();
        if (this.headerParamsMap.size() > 0) {
            for (Map.Entry<String, String> entry : this.headerParamsMap.entrySet()) {
                newBuilder2.add(entry.getKey(), entry.getValue());
            }
            newBuilder.headers(newBuilder2.build());
        }
        if (this.headerLinesList.size() > 0) {
            for (String str : this.headerLinesList) {
                newBuilder2.add(str);
            }
            newBuilder.headers(newBuilder2.build());
        }
        if (this.queryParamsMap.size() > 0) {
            request = injectParamsIntoUrl(request.url().newBuilder(), newBuilder, this.queryParamsMap);
        }
        if (this.paramsMap.size() > 0 && canInjectIntoBody(request)) {
            FormBody.Builder builder = new FormBody.Builder();
            for (Map.Entry<String, String> entry2 : this.paramsMap.entrySet()) {
                builder.add(entry2.getKey(), entry2.getValue());
            }
            FormBody build = builder.build();
            String bodyToString = bodyToString(request.body());
            StringBuilder sb = new StringBuilder();
            sb.append(bodyToString);
            sb.append(bodyToString.length() > 0 ? "&" : "");
            sb.append(bodyToString(build));
            newBuilder.post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), sb.toString()));
        }
        return chain.proceed(newBuilder.build());
    }

    private boolean canInjectIntoBody(Request request) {
        RequestBody body;
        MediaType contentType;
        return (request == null || !TextUtils.equals(request.method(), "POST") || (body = request.body()) == null || (contentType = body.contentType()) == null || !TextUtils.equals(contentType.subtype(), "x-www-form-urlencoded")) ? false : true;
    }

    private Request injectParamsIntoUrl(HttpUrl.Builder builder, Request.Builder builder2, Map<String, String> map) {
        if (map.size() > 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                builder.addQueryParameter(entry.getKey(), entry.getValue());
            }
            builder2.url(builder.build());
            return builder2.build();
        }
        return null;
    }

    private static String bodyToString(RequestBody requestBody) {
        try {
            Buffer buffer = new Buffer();
            if (requestBody != null) {
                requestBody.writeTo(buffer);
                return buffer.readUtf8();
            }
            return "";
        } catch (IOException unused) {
            return "did not work";
        }
    }

    public static class Builder {
        TronParamsInterceptor interceptor = new TronParamsInterceptor();

        public TronParamsInterceptor build() {
            return this.interceptor;
        }

        public Builder addParam(String str, String str2) {
            this.interceptor.paramsMap.put(str, str2);
            return this;
        }

        public Builder addParamsMap(Map<String, String> map) {
            this.interceptor.paramsMap.putAll(map);
            return this;
        }

        public Builder addHeaderParam(String str, String str2) {
            this.interceptor.headerParamsMap.put(str, str2);
            return this;
        }

        public Builder addHeaderParamsMap(Map<String, String> map) {
            this.interceptor.headerParamsMap.putAll(map);
            return this;
        }

        public Builder addHeaderLine(String str) {
            if (str.indexOf(":") == -1) {
                throw new IllegalArgumentException("Unexpected header: " + str);
            }
            this.interceptor.headerLinesList.add(str);
            return this;
        }

        public Builder addHeaderLinesList(List<String> list) {
            for (String str : list) {
                if (str.indexOf(":") == -1) {
                    throw new IllegalArgumentException("Unexpected header: " + str);
                }
                this.interceptor.headerLinesList.add(str);
            }
            return this;
        }

        public Builder addQueryParam(String str, String str2) {
            this.interceptor.queryParamsMap.put(str, str2);
            return this;
        }

        public Builder addQueryParamsMap(Map<String, String> map) {
            this.interceptor.queryParamsMap.putAll(map);
            return this;
        }
    }
}
