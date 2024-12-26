package com.tron.wallet.common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.tron.tron_base.frame.utils.LogUtils;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
public class SignCheck {
    private static final String TAG = "SignCheck";
    private String cer;
    private Context context;
    private String realCer;

    public String getRealCer() {
        return this.realCer;
    }

    public void setRealCer(String str) {
        this.realCer = str;
    }

    public SignCheck(Context context) {
        this.cer = null;
        this.realCer = null;
        this.context = context;
        this.cer = getCertificateSHA1Fingerprint();
    }

    public SignCheck(Context context, String str) {
        this.cer = null;
        this.context = context;
        this.realCer = str;
        this.cer = getCertificateSHA1Fingerprint();
    }

    public String getCertificateSHA1Fingerprint() {
        PackageInfo packageInfo;
        CertificateFactory certificateFactory;
        X509Certificate x509Certificate;
        try {
            packageInfo = this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 64);
        } catch (PackageManager.NameNotFoundException e) {
            LogUtils.e(e);
            packageInfo = null;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(packageInfo.signatures[0].toByteArray());
        try {
            certificateFactory = CertificateFactory.getInstance("X509");
        } catch (Exception e2) {
            LogUtils.e(e2);
            certificateFactory = null;
        }
        try {
            x509Certificate = (X509Certificate) certificateFactory.generateCertificate(byteArrayInputStream);
        } catch (Exception e3) {
            LogUtils.e(e3);
            x509Certificate = null;
        }
        try {
            return byte2HexFormatted(MessageDigest.getInstance("SHA1").digest(x509Certificate.getEncoded()));
        } catch (NoSuchAlgorithmException e4) {
            e4.printStackTrace();
            return null;
        } catch (CertificateEncodingException e5) {
            LogUtils.e(e5);
            return null;
        }
    }

    private String byte2HexFormatted(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i]);
            int length = hexString.length();
            if (length == 1) {
                hexString = "0" + hexString;
            }
            if (length > 2) {
                hexString = hexString.substring(length - 2, length);
            }
            sb.append(hexString.toUpperCase());
            if (i < bArr.length - 1) {
                sb.append(':');
            }
        }
        return sb.toString();
    }

    public boolean check() {
        LogUtils.e(TAG, "realCer: " + this.realCer + "    ******  cer: " + this.cer.trim());
        if (this.realCer != null) {
            this.cer = this.cer.trim();
            String trim = this.realCer.trim();
            this.realCer = trim;
            return this.cer.equals(trim);
        }
        LogUtils.e(TAG, "no real signature given SHA-1 value");
        return false;
    }
}
