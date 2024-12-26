package com.tron.wallet.common.utils;

import android.text.Editable;
import android.text.Selection;
import com.tron.wallet.db.wallet.WalletUtils;
import java.util.Set;
public class WalletChecker {
    public static boolean duplicateName(String str) {
        return WalletUtils.existWallet(str);
    }

    public static Editable truncateIfNeed(Editable editable) {
        String trim = editable.toString().trim();
        int selectionEnd = Selection.getSelectionEnd(editable);
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i >= trim.length()) {
                break;
            }
            char charAt = trim.charAt(i);
            i2 = (charAt < ' ' || charAt > 'z') ? i2 + 2 : i2 + 1;
            if (i2 > 28) {
                String substring = trim.substring(0, i);
                editable.clear();
                editable.append((CharSequence) substring);
                if (selectionEnd > editable.length()) {
                    selectionEnd = editable.length();
                }
                Selection.setSelection(editable, selectionEnd);
            } else {
                i++;
            }
        }
        return editable;
    }

    public static boolean isCharValid(CharSequence charSequence) {
        return StringTronUtil.validataLegalString2(charSequence.toString());
    }

    public static boolean hasImported(Set<String> set, String str) {
        return set != null && set.contains(str);
    }
}
