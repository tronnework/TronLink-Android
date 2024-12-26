package com.tron.wallet.common.utils;

import android.content.Context;
import android.text.TextUtils;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.bean.user.InputPwdBean;
import com.tron.wallet.db.SpAPI;
import java.util.List;
public class PasswordInputUtils {
    private static final int seconds_of_1hour = 3600;

    public interface InputOutListener {
        void onOutTipsListener(String str);
    }

    public static void updatePwdInput(Context context, String str, int i) {
        updatePwdInput(context, str, i, null);
    }

    public static void updatePwdInput(android.content.Context r12, java.lang.String r13, int r14, com.tron.wallet.common.utils.PasswordInputUtils.InputOutListener r15) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.common.utils.PasswordInputUtils.updatePwdInput(android.content.Context, java.lang.String, int, com.tron.wallet.common.utils.PasswordInputUtils$InputOutListener):void");
    }

    public static void updateSuccessPwd(Context context, String str, int i) {
        if (i != 0) {
            String pwdInputJson = SpAPI.THIS.getPwdInputJson(str);
            if (TextUtils.isEmpty(pwdInputJson)) {
                return;
            }
            List list = (List) new Gson().fromJson(pwdInputJson, new TypeToken<List<InputPwdBean>>() {
            }.getType());
            if (list != null) {
                for (int i2 = 0; i2 < list.size(); i2++) {
                    InputPwdBean inputPwdBean = (InputPwdBean) list.get(i2);
                    if (inputPwdBean != null && i == inputPwdBean.type) {
                        inputPwdBean.errorTimes = 0;
                        inputPwdBean.lastInputTime = 0L;
                    }
                }
            }
            SpAPI.THIS.setPwdInputJson(str, new Gson().toJson(list));
        }
    }

    public static boolean canPwdInput(Context context, String str, int i) {
        if (i == 0) {
            i = 7;
        }
        try {
            String pwdInputJson = SpAPI.THIS.getPwdInputJson(str);
            if (TextUtils.isEmpty(pwdInputJson)) {
                return true;
            }
            List list = (List) new Gson().fromJson(pwdInputJson, new TypeToken<List<InputPwdBean>>() {
            }.getType());
            for (int i2 = 0; i2 < list.size(); i2++) {
                InputPwdBean inputPwdBean = (InputPwdBean) list.get(i2);
                if (inputPwdBean != null && i == inputPwdBean.type && inputPwdBean.errorTimes >= 6 && System.currentTimeMillis() - inputPwdBean.lastInputTime < org.apache.commons.lang3.time.DateUtils.MILLIS_PER_HOUR) {
                    return false;
                }
            }
            return true;
        } catch (JsonSyntaxException e) {
            LogUtils.e(e);
            return true;
        }
    }
}
