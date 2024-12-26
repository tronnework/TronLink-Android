package com.tron.wallet.common.config;

import com.tron.wallet.db.SpAPI;
public class TronSetting {
    public static boolean CreateByLocal = true;
    public static final int STAKE_V1 = 1;
    public static final int STAKE_V2 = 2;
    public static String TRON_URL = "https://googleplay.tronlink.org/";
    public static long maxDelegateLockPeriodDays = 0;
    public static int stakeExpireDay = 14;
    public static boolean stakeV2 = true;
    public static boolean tronWebDisplay = false;

    public static void init() {
        stakeExpireDay = SpAPI.THIS.getStakeExpireDay();
    }
}
