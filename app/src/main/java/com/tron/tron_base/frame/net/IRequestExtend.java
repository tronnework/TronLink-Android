package com.tron.tron_base.frame.net;

import com.google.gson.GsonBuilder;
import com.tron.tron_base.frame.net.IRequest;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
public class IRequestExtend {
    private static Retrofit instance;

    private static Retrofit getInstance() {
        if (instance == null) {
            synchronized (IRequest.class) {
                if (instance == null) {
                    instance = getRetrofit();
                }
            }
        }
        return instance;
    }

    public static <T> T getAPI(Class<T> cls) {
        return (T) getInstance().create(cls);
    }

    private static Retrofit getRetrofit() {
        return new Retrofit.Builder().client(OkHttpManager.getInstance().build()).baseUrl(getHost()).addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().registerTypeAdapterFactory(new NumberTypeAdapterFactory()).create())).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
    }

    public static class fun1 {
        static final int[] $SwitchMap$com$tron$tron_base$frame$net$IRequest$NET_ENVIRONMENT;

        static {
            int[] iArr = new int[IRequest.NET_ENVIRONMENT.values().length];
            $SwitchMap$com$tron$tron_base$frame$net$IRequest$NET_ENVIRONMENT = iArr;
            try {
                iArr[IRequest.NET_ENVIRONMENT.NILETEST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tron$tron_base$frame$net$IRequest$NET_ENVIRONMENT[IRequest.NET_ENVIRONMENT.TEST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tron$tron_base$frame$net$IRequest$NET_ENVIRONMENT[IRequest.NET_ENVIRONMENT.SHASTA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$tron$tron_base$frame$net$IRequest$NET_ENVIRONMENT[IRequest.NET_ENVIRONMENT.RELEASE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$tron$tron_base$frame$net$IRequest$NET_ENVIRONMENT[IRequest.NET_ENVIRONMENT.PRE_RELEASE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private static String getHost() {
        int i = fun1.$SwitchMap$com$tron$tron_base$frame$net$IRequest$NET_ENVIRONMENT[HostUtil.ENVIRONMENT.ordinal()];
        return (i == 1 || i == 2) ? "https://nileapi.tronscan.org/" : i != 3 ? "https://apilist.tronscanapi.com/" : "https://shastaapi.tronscan.org/";
    }
}
