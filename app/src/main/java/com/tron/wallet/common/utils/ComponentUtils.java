package com.tron.wallet.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Pair;
import java.io.Serializable;
public class ComponentUtils {
    @SafeVarargs
    public static void startActivity(Context context, Class<? extends Activity> cls, Pair<String, Object>... pairArr) {
        Intent intent = new Intent(context, cls);
        if (pairArr != null && pairArr.length > 0) {
            for (Pair<String, Object> pair : pairArr) {
                if (pair.second instanceof Serializable) {
                    intent.putExtra((String) pair.first, (Serializable) pair.second);
                } else if (pair.second instanceof Parcelable) {
                    intent.putExtra((String) pair.first, (Parcelable) pair.second);
                }
            }
        }
        context.startActivity(intent);
    }

    public static void startAndFinishSelf(Activity activity, Class<? extends Activity> cls, boolean z, Pair<String, ? extends Serializable>... pairArr) {
        Intent intent = new Intent(activity, cls);
        if (pairArr != null && pairArr.length > 0) {
            for (Pair<String, ? extends Serializable> pair : pairArr) {
                intent.putExtra((String) pair.first, (Serializable) pair.second);
            }
        }
        if (z) {
            intent.setFlags(268468224);
        }
        activity.startActivity(intent);
        activity.finish();
    }

    public static boolean isActivityForeground(android.content.Context r4) {
        


return true;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.common.utils.ComponentUtils.isActivityForeground(android.content.Context):boolean");
    }
}
