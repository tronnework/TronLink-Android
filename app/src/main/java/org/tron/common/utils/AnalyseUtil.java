package org.tron.common.utils;
public class AnalyseUtil {
    private static Analyser instance;

    public interface Analyser {
        void log(Throwable th);
    }

    public static void init(Analyser analyser) {
        instance = analyser;
    }

    private AnalyseUtil() {
    }

    public static void log(Throwable th) {
        Analyser analyser = instance;
        if (analyser != null) {
            analyser.log(th);
        }
    }
}
