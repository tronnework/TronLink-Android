package j$.com.android.tools.r8;

import sun.misc.Unsafe;
public abstract class DesugarVarHandle$ExternalSyntheticBackportWithForwarding0 {
    public static boolean m(Unsafe unsafe, Object obj, long j, Object obj2, Object obj3) {
        while (!unsafe.compareAndSwapObject(obj, j, obj2, obj3)) {
            if (unsafe.getObject(obj, j) != obj2) {
                return false;
            }
        }
        return true;
    }
}
