package j$.util;

import java.security.AccessController;
import java.security.PrivilegedAction;
public abstract class Tripwire {
    static final boolean ENABLED = ((Boolean) AccessController.doPrivileged(new PrivilegedAction() {
        @Override
        public final Object run() {
            Boolean valueOf;
            valueOf = Boolean.valueOf(Boolean.getBoolean("org.openjdk.java.util.stream.tripwire"));
            return valueOf;
        }
    })).booleanValue();

    public static void trip(Class cls, String str) {
        throw new UnsupportedOperationException(cls + " tripwire tripped but logging not supported: " + str);
    }
}
