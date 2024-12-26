package com.tron.wallet.business.migrate.component;
public interface MigrateConfig {
    public static final String ACTION_MIGRATE_FROM_PRO = "com.tron.wallet.action_migrate_wallet";
    public static final String ACTION_MIGRATE_TO_GLOBAL = "com.tronlink.global.action_migrate_wallet";
    public static final String APP_ID_GLOBAL = "com.tronlink.global";
    public static final String APP_ID_PRO = "com.tronlinkpro.wallet";
    public static final String CACHE_FILE = "migrate_cache";
    public static final String CALLED_FROM_MIGRATE = "key_called_from_migrate";
    public static final String INTENT_KEY_RESULT = "key_migrate_result";
    public static final String KEY_CACHE_FILE = "key_cache_file";
    public static final String KEY_MIGRATE_FILE_URI = "key_migrate_file_uri";
    public static final String KEY_RESULT_FILE = "key_result_file";
    public static final long MIGRATE_SUPPORT_MIN_VERSION_GLOBAL = 2012221230;
    public static final long MIGRATE_SUPPORT_MIN_VERSION_PRO = 2012221230;
    public static final long MIGRATE_SUPPORT_WATCH_COLD_MIN = 2012221238;
    public static final long MIGRATE_SUPPORT_WATCH_COLD_MIN_GLOBAL = 2012221238;
    public static final String PROVIDER_AUTH_PRO = "com.tronlinkpro.wallet.fileprovider";
    public static final String RESULT_FILE = "migrate_result";
    public static final String ZENDESK_EN = "https://tronlinkorg.zendesk.com/hc/en-us/articles/11912766025497";
    public static final String ZENDESK_ZH = "https://tronlinkorg.zendesk.com/hc/zh-cn/articles/11912766025497";
}
