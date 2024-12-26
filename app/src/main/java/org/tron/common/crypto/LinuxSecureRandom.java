package org.tron.common.crypto;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Provider;
import java.security.SecureRandomSpi;
import java.security.Security;
public class LinuxSecureRandom extends SecureRandomSpi {
    private static final FileInputStream urandom;
    private final DataInputStream dis = new DataInputStream(urandom);

    @Override
    protected void engineSetSeed(byte[] bArr) {
    }

    private static class LinuxSecureRandomProvider extends Provider {
        public LinuxSecureRandomProvider() {
            super("LinuxSecureRandom", 1.0d, "ABIBean Linux specific random number provider that uses /dev/urandom");
            put("SecureRandom.LinuxSecureRandom", LinuxSecureRandom.class.getName());
        }
    }

    static {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File("/dev/urandom"));
            urandom = fileInputStream;
            if (fileInputStream.read() == -1) {
                throw new RuntimeException("/dev/urandom not readable?");
            }
            Security.insertProviderAt(new LinuxSecureRandomProvider(), 1);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override
    protected void engineNextBytes(byte[] bArr) {
        try {
            this.dis.readFully(bArr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected byte[] engineGenerateSeed(int i) {
        byte[] bArr = new byte[i];
        engineNextBytes(bArr);
        return bArr;
    }
}
