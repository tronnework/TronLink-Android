package com.tron.tron_base.frame.net;

import android.text.TextUtils;
import android.util.Base64;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.StringTronUtil;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Request;
public class SignatureManager {
    private SecureRandom secureRandom = new SecureRandom();
    private Random intRandom = new Random();

    static class Constants {
        public static final String ENCODING = "UTF-8";
        public static final String Lang = "Lang";
        public static final String MAC_NAME = "HmacSHA1";
        public static final String System = "System";
        public static final String Version = "Version";
        public static final String chain = "chain";
        public static final String channel = "channel";
        public static final String packageName = "packageName";
        public static final String secretId = "A4ADE880F46CA8D4";
        public static final String secretKey = "0F46CA8D490936A851D688F9BED151200D45G";
        public static final String ts = "ts";

        Constants() {
        }
    }

    private Map<String, String> parseParams(Request request) {
        return checkAddress(parseGetParam(request, new HashMap()));
    }

    public Map<String, String> parseGetParam(Request request, Map<String, String> map) {
        HttpUrl url = request.url();
        if (!TextUtils.isEmpty(url.encodedQuery())) {
            Set<String> queryParameterNames = url.queryParameterNames();
            Iterator<String> it = queryParameterNames.iterator();
            for (int i = 0; i < queryParameterNames.size(); i++) {
                String next = it.next();
                String queryParameter = url.queryParameter(next);
                if (!TextUtils.isEmpty(queryParameter) && queryParameter != null && !"null".equals(queryParameter) && queryParameter.length() != 0 && !"".equals(queryParameter)) {
                    map.put(next, queryParameter);
                }
            }
        }
        return map;
    }

    private Map<String, String> checkAddress(Map<String, String> map) {
        if (!map.containsKey("address")) {
            map.put("address", "");
        }
        return map;
    }

    public Request addSignature(Request request) {
        String str;
        Map<String, String> parseParams = parseParams(request);
        HttpUrl url = request.url();
        Headers headers = request.headers();
        String encodedPath = url.encodedPath();
        String valueOf = String.valueOf(getNonce());
        try {
            str = getSignature(headers.get(Constants.channel), headers.get(Constants.chain), headers.get(Constants.Lang), headers.get(Constants.System), headers.get(Constants.ts), headers.get(Constants.Version), parseParams.get("address"), valueOf, Constants.secretId, encodedPath, request.method());
        } catch (Exception e) {
            LogUtils.e(e);
            str = "";
        }
        if (StringTronUtil.isEmpty(str)) {
            return request;
        }
        return request.newBuilder().url(request.url().newBuilder().addEncodedQueryParameter("nonce", valueOf).addEncodedQueryParameter("secretId", Constants.secretId).addEncodedQueryParameter("signature", str).build()).build();
    }

    public long getNonce() {
        byte[] generateSeed = this.secureRandom.generateSeed(10);
        if (generateSeed == null || generateSeed.length == 0) {
            return this.intRandom.nextInt(10000000) + System.currentTimeMillis();
        }
        return Math.abs(new BigInteger(1, generateSeed).longValue());
    }

    public String hmacSHA1(String str, String str2) throws InvalidKeyException, NoSuchAlgorithmException {
        Mac mac = Mac.getInstance(Constants.MAC_NAME);
        mac.init(new SecretKeySpec(str.getBytes(), Constants.MAC_NAME));
        return encode(mac.doFinal(str2.getBytes()));
    }

    public String encode(byte[] bArr) {
        return Base64.encodeToString(bArr, 0).replaceAll(System.getProperty("line.separator"), "");
    }

    private String getSignature(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        LogUtils.d("xxxxx", "address+\n\t" + str7);
        TreeMap treeMap = new TreeMap();
        treeMap.put(Constants.System, str4);
        treeMap.put(Constants.Version, str6);
        treeMap.put(Constants.Lang, str3);
        treeMap.put(Constants.channel, str);
        treeMap.put(Constants.chain, str2);
        treeMap.put(Constants.ts, str5);
        treeMap.put("nonce", str8);
        treeMap.put("secretId", str9);
        treeMap.put("address", str7);
        return URLEncoder.encode(hmacSHA1(Constants.secretKey, String.format("%s%s?%s", str11, str10, makeQueryString(treeMap, "UTF-8"))), "UTF-8");
    }

    public String makeQueryString(Map<String, Object> map, String str) throws UnsupportedEncodingException {
        String str2 = "";
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append(entry.getKey());
            sb.append("=");
            Object value = entry.getValue();
            if (str != null) {
                value = URLEncoder.encode(String.valueOf(value), str);
            }
            sb.append(value);
            sb.append("&");
            str2 = sb.toString();
        }
        return str2.substring(0, str2.length() - 1);
    }

    private boolean needSign(Request request) {
        return TextUtils.equals("true", request.headers().get("Signature"));
    }
}
