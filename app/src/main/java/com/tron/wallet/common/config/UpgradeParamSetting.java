package com.tron.wallet.common.config;

import com.tron.wallet.db.SpAPI;
public class UpgradeParamSetting {

    public enum Type {
        NotUpgrade,
        Show,
        Hide
    }

    private static Type getUpgradeStatus() {
        int showChangedHdEdit = SpAPI.THIS.showChangedHdEdit();
        if (showChangedHdEdit == 2) {
            return Type.Show;
        }
        if (showChangedHdEdit == 3) {
            return Type.Hide;
        }
        return Type.NotUpgrade;
    }

    public static boolean showChangedHdEdit() {
        return getUpgradeStatus() == Type.Show;
    }

    public static boolean notNeedUpgrade() {
        return getUpgradeStatus() == Type.NotUpgrade;
    }

    public static void setStatusShow() {
        SpAPI.THIS.setShowChangedHdEdit(2);
    }

    public static void setStatusHide() {
        SpAPI.THIS.setShowChangedHdEdit(3);
    }

    public static void debugResetStatus() {
        SpAPI.THIS.setShowChangedHdEdit(1);
    }
}
