package com.tron.tron_base.frame.net;

import android.os.Build;
import com.tron.tron_base.R;
import com.tron.tron_base.frame.net.SignatureManager;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.ChannelUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.SpUtils;
import com.tron.wallet.business.migrate.component.MigrateConfig;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
public class OkHttpManager {
    private static final int DEFAULT_TIMEOUT = 8;
    private static final String TAG = "OkHttpManager";
    private static OkHttpManager mOkhttpManager;
    private InputStream mTrustCertificate;
    private SignatureManager signatureManager = new SignatureManager();

    public InputStream getTrustCertificates() {
        return this.mTrustCertificate;
    }

    public void setTrustCertificates(InputStream inputStream) {
        this.mTrustCertificate = inputStream;
    }

    public static OkHttpManager getInstance() {
        if (mOkhttpManager == null) {
            mOkhttpManager = new OkHttpManager();
        }
        return mOkhttpManager;
    }

    private static TrustManager[] getDefaultTrustManager() {
        return new TrustManager[]{new X509TrustManager() {
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            @Override
            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
                LogUtils.v("");
            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
                LogUtils.v("");
            }
        }};
    }

    private static Request.Builder getBuilder(Interceptor.Chain chain) {
        String str;
        String str2;
        String str3 = "null";
        Request request = chain.request();
        String str4 = (String) SpUtils.getParam("f_TronKey", AppContextUtil.getContext(), AppContextUtil.getContext().getString(R.string.language_key), "1");
        String str5 = (String) SpUtils.getParam("f_Tron", AppContextUtil.getContext(), AppContextUtil.getContext().getString(R.string.chain_name_key), "MainChain");
        try {
            str = AppContextUtil.getContext().getPackageName();
        } catch (Exception e) {
            LogUtils.e(e);
            str = AppContextUtil.mIsGooGlePlay ? AppContextUtil.mIsGlobal ? MigrateConfig.APP_ID_GLOBAL : "com.tronlinkpro.wallet" : "com.tronlink.wallet";
        }
        try {
            str2 = Build.VERSION.RELEASE;
        } catch (Exception e2) {
            e = e2;
            str2 = "null";
        }
        try {
            str3 = Build.MODEL;
        } catch (Exception e3) {
            e = e3;
            LogUtils.e(e);
            return request.newBuilder().addHeader(SignatureManager.Constants.System, "Android").addHeader(SignatureManager.Constants.Version, VersionNested.version).addHeader("deviceName", str3).addHeader("osVersion", str2).addHeader(SignatureManager.Constants.Lang, str4).addHeader(SignatureManager.Constants.channel, ChannelUtils.getGoogleAnalyticsChannel()).addHeader(SignatureManager.Constants.chain, str5).addHeader(SignatureManager.Constants.packageName, str).addHeader("env", "prod").addHeader(SignatureManager.Constants.ts, String.valueOf(System.currentTimeMillis()));
        }
        return request.newBuilder().addHeader(SignatureManager.Constants.System, "Android").addHeader(SignatureManager.Constants.Version, VersionNested.version).addHeader("deviceName", str3).addHeader("osVersion", str2).addHeader(SignatureManager.Constants.Lang, str4).addHeader(SignatureManager.Constants.channel, ChannelUtils.getGoogleAnalyticsChannel()).addHeader(SignatureManager.Constants.chain, str5).addHeader(SignatureManager.Constants.packageName, str).addHeader("env", "prod").addHeader(SignatureManager.Constants.ts, String.valueOf(System.currentTimeMillis()));
    }

    private KeyStore newEmptyKeyStore(char[] cArr) throws GeneralSecurityException {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, cArr);
            return keyStore;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    private X509TrustManager trustManagerForCertificates() throws GeneralSecurityException {
        Collection<? extends Certificate> collection;
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        LogUtils.d(TAG, "start generateCertificates");
        try {
            collection = certificateFactory.generateCertificates(AppContextUtil.getContext().getAssets().open(IRequest.getCrt()));
        } catch (IOException e) {
            LogUtils.e(e);
            collection = null;
        }
        if (collection.isEmpty()) {
            LogUtils.d(TAG, "is empty");
            throw new IllegalArgumentException("expected non-empty set of trusted certificates");
        }
        LogUtils.d(TAG, "not empty");
        char[] charArray = "password".toCharArray();
        KeyStore newEmptyKeyStore = newEmptyKeyStore(charArray);
        int i = 0;
        for (Certificate certificate : collection) {
            int i2 = i + 1;
            newEmptyKeyStore.setCertificateEntry(Integer.toString(i), certificate);
            i = i2;
        }
        KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm()).init(newEmptyKeyStore, charArray);
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(newEmptyKeyStore);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers.length == 1) {
            TrustManager trustManager = trustManagers[0];
            if (trustManager instanceof X509TrustManager) {
                return (X509TrustManager) trustManager;
            }
        }
        throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
    }

    public OkHttpClient build() {
        if (getTrustCertificates() != null) {
            try {
                TrustManager[] defaultTrustManager = getDefaultTrustManager();
                X509TrustManager x509TrustManager = (X509TrustManager) defaultTrustManager[0];
                SSLContext sSLContext = SSLContext.getInstance("TLS");
                sSLContext.init(null, defaultTrustManager, null);
                SSLSocketFactory socketFactory = sSLContext.getSocketFactory();
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
                OkHttpClient.Builder addInterceptor = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).addInterceptor(new HttpExceptionInterceptor()).connectTimeout(8L, TimeUnit.SECONDS).writeTimeout(8L, TimeUnit.SECONDS).readTimeout(8L, TimeUnit.SECONDS).addInterceptor(new Interceptor() {
                    @Override
                    public final Response intercept(Interceptor.Chain chain) {
                        Response lambda$build$0;
                        lambda$build$0 = lambda$build$0(chain);
                        return lambda$build$0;
                    }
                });
                addInterceptor.sslSocketFactory(socketFactory, x509TrustManager);
                return addInterceptor.build();
            } catch (GeneralSecurityException e) {
                throw new RuntimeException(e);
            }
        }
        return new OkHttpClient.Builder().build();
    }

    public Response lambda$build$0(Interceptor.Chain chain) throws IOException {
        return chain.proceed(this.signatureManager.addSignature(getBuilder(chain).build()));
    }

    public static class VersionNested {
        static String version;

        private VersionNested() {
        }

        static {
            try {
                version = AppContextUtil.getContext().getPackageManager().getPackageInfo(AppContextUtil.getContext().getPackageName(), 0).versionName;
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
    }

    private class DomainSwitchInterceptor implements Interceptor {
        private DomainSwitchInterceptor() {
        }

        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            return chain.proceed(buildRequest(chain));
        }

        private Request buildRequest(Interceptor.Chain chain) {
            Request request = chain.request();
            HttpUrl url = request.url();
            String header = request.header("domain");
            if (header == null || header.length() <= 0) {
                return request;
            }
            HttpUrl parse = HttpUrl.parse(header);
            HttpUrl build = url.newBuilder().scheme(parse.scheme()).host(parse.host()).port(parse.port()).build();
            Request.Builder newBuilder = request.newBuilder();
            newBuilder.url(build);
            return newBuilder.build();
        }
    }
}
