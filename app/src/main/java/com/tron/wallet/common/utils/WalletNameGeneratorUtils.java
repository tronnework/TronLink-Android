package com.tron.wallet.common.utils;

import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
import java.util.Set;
import org.apache.commons.cli.HelpFormatter;
public class WalletNameGeneratorUtils {
    public static String getKey(int i, boolean z) {
        switch (i) {
            case 0:
                return z ? SpAPI.CREATE_SHIELD_WALLET_NAME_INDEX : SpAPI.CREATE_WALLET_NAME_INDEX;
            case 1:
            case 2:
            case 3:
            case 5:
            case 6:
                return z ? SpAPI.IMPORT_SHIELD_WALLET_NAME_INDEX : SpAPI.IMPORT_WALLET_NAME_INDEX;
            case 4:
                return z ? SpAPI.OBSERVATION_SHIELD_WALLET_NAME_INDEX : SpAPI.OBSERVATION_WALLET_NAME_INDEX;
            case 7:
                return SpAPI.SAMSUNG_WALLET_NAME_INDEX;
            case 8:
                return SpAPI.LEDGER_WALLET_NAME_INDEX;
            case 9:
                return SpAPI.IMPORT_COLD_WALLET_NAME_INDEX;
            default:
                return "";
        }
    }

    public static String generateForAccountName(int i) {
        String str;
        int createAccountIndex = SpAPI.THIS.getCreateAccountIndex();
        Set<String> allWallets = SpAPI.THIS.getAllWallets();
        String lastGenerationAccounName = SpAPI.THIS.getLastGenerationAccounName();
        int i2 = createAccountIndex;
        while (createAccountIndex < Integer.MAX_VALUE) {
            if (i2 == 0) {
                str = StringTronUtil.getResString(R.string.default_name_create);
            } else {
                str = StringTronUtil.getResString(R.string.default_name_create) + HelpFormatter.DEFAULT_OPT_PREFIX + i2;
            }
            LogUtils.d("CreateAccount", "CreateAccount: generateForAccountName " + str + "   " + i2);
            if (!allWallets.contains(str) && !str.equals(lastGenerationAccounName)) {
                SpAPI.THIS.setCreateAccountIndex(i2);
                return str;
            }
            i2++;
            createAccountIndex++;
        }
        return StringTronUtil.getResString(R.string.default_name_create) + HelpFormatter.DEFAULT_LONG_OPT_PREFIX + i2;
    }

    public static String generateWalletName(int i, boolean z) {
        String key = getKey(i, z);
        int index = getIndex(key);
        String name = getName(key);
        return checkName(name + HelpFormatter.DEFAULT_OPT_PREFIX + index, name, index);
    }

    private static String getName(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1815827829:
                if (str.equals(SpAPI.OBSERVATION_SHIELD_WALLET_NAME_INDEX)) {
                    c = 0;
                    break;
                }
                break;
            case -731515045:
                if (str.equals(SpAPI.CREATE_SHIELD_WALLET_NAME_INDEX)) {
                    c = 1;
                    break;
                }
                break;
            case -14557388:
                if (str.equals(SpAPI.SAMSUNG_WALLET_NAME_INDEX)) {
                    c = 2;
                    break;
                }
                break;
            case 177632580:
                if (str.equals(SpAPI.IMPORT_SHIELD_WALLET_NAME_INDEX)) {
                    c = 3;
                    break;
                }
                break;
            case 190053311:
                if (str.equals(SpAPI.IMPORT_WALLET_NAME_INDEX)) {
                    c = 4;
                    break;
                }
                break;
            case 1167492310:
                if (str.equals(SpAPI.CREATE_WALLET_NAME_INDEX)) {
                    c = 5;
                    break;
                }
                break;
            case 1706256390:
                if (str.equals(SpAPI.OBSERVATION_WALLET_NAME_INDEX)) {
                    c = 6;
                    break;
                }
                break;
            case 1780018285:
                if (str.equals(SpAPI.IMPORT_COLD_WALLET_NAME_INDEX)) {
                    c = 7;
                    break;
                }
                break;
            case 1889652355:
                if (str.equals(SpAPI.LEDGER_WALLET_NAME_INDEX)) {
                    c = '\b';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return StringTronUtil.getResString(R.string.default_name_observation_shield);
            case 1:
                return StringTronUtil.getResString(R.string.default_name_create_shield);
            case 2:
                return StringTronUtil.getResString(R.string.default_name_samsung);
            case 3:
                return StringTronUtil.getResString(R.string.default_name_import_shield);
            case 4:
                return StringTronUtil.getResString(R.string.default_name_import);
            case 5:
                return StringTronUtil.getResString(R.string.default_name_create);
            case 6:
                return StringTronUtil.getResString(R.string.default_name_observation);
            case 7:
                return StringTronUtil.getResString(R.string.cold_wallet_default_name);
            case '\b':
                return StringTronUtil.getResString(R.string.default_name_ledger);
            default:
                return null;
        }
    }

    public static int getIndex(String str) {
        return SpAPI.THIS.getWalletNameIndex(str);
    }

    private static String checkName(String str, String str2, int i) {
        Set<String> allWallets = SpAPI.THIS.getAllWallets();
        if (!(i == 0 ? allWallets.contains(str2) : allWallets.contains(str))) {
            return i == 0 ? str2 : str;
        }
        int i2 = i + 1;
        return checkName(str2 + HelpFormatter.DEFAULT_OPT_PREFIX + i2, str2, i2);
    }

    public static void finishGenerateForAccountName() {
        int createAccountIndex = SpAPI.THIS.getCreateAccountIndex() + 1;
        LogUtils.d("CreateAccount", "finishGenerateForAccountName:     " + createAccountIndex);
        SpAPI.THIS.setCreateAccountIndex(createAccountIndex);
    }

    public static void finish(int i, boolean z) {
        String key = getKey(i, z);
        SpAPI.THIS.setWalletNameIndex(key, SpAPI.THIS.getWalletNameIndex(key) + 1);
    }
}
