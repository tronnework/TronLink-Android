package com.tron.tron_base.frame.net;

import com.google.gson.GsonBuilder;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.config.Event;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
public class IRequest extends HostUtil {
    public static final int SERVER_CONNECTED = 1;
    public static final int SERVER_REBUILD = 3;
    public static final int SERVER_SET_AUTO = 4;
    public static final int SERVER_TRY_SWITCH = 2;
    private static Retrofit instance;

    public enum NET_ENVIRONMENT {
        RELEASE,
        PRE_RELEASE,
        TEST,
        NILETEST,
        SHASTA
    }

    public static void initBase(String str, String str2) {
        tronscanBaseUrl = str;
        tronscanDappBaseUrl = str2;
    }

    public static boolean isTest() {
        return ENVIRONMENT == NET_ENVIRONMENT.TEST;
    }

    public static boolean isShasta() {
        return ENVIRONMENT == NET_ENVIRONMENT.SHASTA;
    }

    public static boolean isRelease() {
        return ENVIRONMENT == NET_ENVIRONMENT.RELEASE;
    }

    public static boolean isNile() {
        return ENVIRONMENT == NET_ENVIRONMENT.NILETEST;
    }

    public static boolean isPrerelease() {
        return NET_ENVIRONMENT.PRE_RELEASE == ENVIRONMENT;
    }

    public static String getCrt() {
        return IRequestConstant.RELEASE_CRT_2021;
    }

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

    public static void onServerChanged() {
        if (instance != null) {
            synchronized (IRequest.class) {
                LogUtils.d(Event.SWITCH_SERVER);
                instance = getRetrofit();
            }
        }
    }

    public static <T> T getAPI(Class<T> cls) {
        return (T) getInstance().create(cls);
    }

    private static Retrofit getRetrofit() {
        return new Retrofit.Builder().client(OkHttpManager.getInstance().build()).baseUrl(getTronLinkHost()).addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().registerTypeAdapterFactory(new NumberTypeAdapterFactory()).create())).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
    }
}
