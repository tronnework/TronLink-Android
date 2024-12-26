package com.tron.wallet.common.components.frescoimage.metadata;

import com.tron.wallet.common.utils.SentryUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
public final class ImageInfoExtractor {
    private static final int ANIMATED_WEBP_MASK = 2;
    public static final int TYPE_ANIMATED_WEBP = 2;
    public static final int TYPE_GIF = 1;
    public static final int TYPE_STILL_IMAGE = 0;
    public static final int TYPE_STILL_WEBP = 3;

    public static String typeName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "STILL_IMAGE" : "STILL_WEBP" : "ANIMATED_WEBP" : "GIF";
    }

    private ImageInfoExtractor() {
    }

    public static int getImageType(File file) {
        int i = 0;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[20];
            int read = fileInputStream.read(bArr);
            if (read >= 3 && isGifHeader(bArr)) {
                i = 1;
            } else if (read >= 12 && isWebpHeader(bArr)) {
                if (read >= 17 && isExtendedWebp(bArr)) {
                    i = 2;
                    if ((bArr[16] & 2) != 0) {
                    }
                }
                i = 3;
            }
            fileInputStream.close();
        } catch (IOException e) {
            SentryUtil.captureException(e);
        }
        return i;
    }

    private static boolean isGifHeader(byte[] bArr) {
        return bArr[0] == 71 && bArr[1] == 73 && bArr[2] == 70;
    }

    private static boolean isWebpHeader(byte[] bArr) {
        return bArr[0] == 82 && bArr[1] == 73 && bArr[2] == 70 && bArr[3] == 70 && bArr[8] == 87 && bArr[9] == 69 && bArr[10] == 66 && bArr[11] == 80;
    }

    private static boolean isExtendedWebp(byte[] bArr) {
        return bArr[12] == 86 && bArr[13] == 80 && bArr[14] == 56 && bArr[15] == 88;
    }
}
