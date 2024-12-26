package com.tron.wallet.common.utils;

import java.io.File;
public class FileUtils {
    public static void rmoveFile(String str) {
        new File(str).delete();
    }
}
