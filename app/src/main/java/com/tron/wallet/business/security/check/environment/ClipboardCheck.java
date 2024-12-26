package com.tron.wallet.business.security.check.environment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Build;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.wallet.business.security.ResultStatusEnum;
import com.tron.wallet.common.utils.StringTronUtil;
import org.tron.common.crypto.MnemonicUtils;
public class ClipboardCheck {
    public static ResultStatusEnum getResult() {
        ClipData primaryClip;
        CharSequence text;
        if (Build.VERSION.SDK_INT >= 23) {
            ClipboardManager clipboardManager = (ClipboardManager) AppContextUtil.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            if (clipboardManager.hasPrimaryClip() && (primaryClip = clipboardManager.getPrimaryClip()) != null && primaryClip.getItemCount() > 0) {
                for (int i = 0; i < primaryClip.getItemCount(); i++) {
                    if (primaryClip.getItemAt(i) != null && (text = primaryClip.getItemAt(i).getText()) != null) {
                        if (StringTronUtil.isPrivateKeyValid(text.toString())) {
                            return ResultStatusEnum.Waring;
                        }
                        if (MnemonicUtils.validateMnemonic(text.toString())) {
                            return ResultStatusEnum.Waring;
                        }
                    }
                }
            }
        }
        return ResultStatusEnum.Safe;
    }
}
