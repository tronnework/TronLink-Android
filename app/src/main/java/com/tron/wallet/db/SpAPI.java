package com.tron.wallet.db;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.tron.tron_base.BuildConfig;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.SpUtils;
import com.tron.wallet.business.addassets2.bean.nft.NftTransferOutput;
import com.tron.wallet.business.tabdapp.bean.DappLocalSearchBean;
import com.tron.wallet.business.tabdapp.browser.BrowserConstant;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserTabIndexBean;
import com.tron.wallet.business.tabdapp.dappsearch.DappSearchPresenter;
import com.tron.wallet.business.tabmy.myhome.settings.bean.MainNodeOutput;
import com.tron.wallet.business.tabswap.bean.AppStatusOutput;
import com.tron.wallet.business.walletmanager.backup.record.BackupRecordBean;
import com.tron.wallet.common.bean.AppLanguageOutput;
import com.tron.wallet.common.bean.ChainBean;
import com.tron.wallet.common.bean.DecimalsBean;
import com.tron.wallet.common.bean.RecentSendBean;
import com.tron.wallet.common.bean.RecentTransferAddressBean;
import com.tron.wallet.common.bean.token.TransactionHistoryBean;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.GsonUtils;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.cli.HelpFormatter;
import org.tron.net.bean.MessagePermission;
import org.tron.walletserver.DuplicateNameException;
public enum SpAPI {
    THIS;
    
    public static final String CREATE_SHIELD_WALLET_NAME_INDEX = "create_sheidle_wallet__index";
    public static final String CREATE_WALLET_NAME_INDEX = "create_wallet__index";
    public static final String IMPORT_COLD_WALLET_NAME_INDEX = "import_cold_wallet_index";
    public static final String IMPORT_SHIELD_WALLET_NAME_INDEX = "import_sheidle_wallet__index";
    public static final String IMPORT_WALLET_NAME_INDEX = "import_wallet__index";
    public static final String LEDGER_WALLET_NAME_INDEX = "ledger_wallet__index";
    public static final String OBSERVATION_SHIELD_WALLET_NAME_INDEX = "observation_sheidle_wallet__index";
    public static final String OBSERVATION_WALLET_NAME_INDEX = "observation_wallet__index";
    public static final String SAMSUNG_WALLET_NAME_INDEX = "Samsung_wallet__index";
    public static final String SET_TIPS_NO_MORE_SHOW_KEYSTORE = "set_tips_no_more_show_keystore";
    public static final String SET_TIPS_NO_MORE_SHOW_MNEMONIC = "set_tips_no_more_show_mnemonic";
    public static final String SET_TIPS_NO_MORE_SHOW_PRIVATE_KEY = "set_tips_no_more_show_privete_key";
    private static final String TAG = "SpAPI";
    String F_TRONIP = "f_Tron";
    String F_TRONKEY = "f_TronKey";
    String F_TRON_NEW = "f_Tron_3.8.0";
    int ID_IPkey = R.string.ip_key;
    int ID_ClientId = R.string.client_id_key;
    int ID_PORTkey = R.string.port_key;
    int ID_SolIPkey = R.string.ip_sol_key;
    int ID_SolPORTkey = R.string.port_sol_key;
    int ID_ShieldFee = R.string.port_sol_key;
    int ID_LAST_MSG_ID = R.string.last_msg_id_key;
    int ID_DappToMainFee = R.string.dapp_to_main_fee_key;
    int ID_PassWordKey = R.string.pwd_key;
    int ID_PrivatedKey = R.string.priv_key;
    int ID_PublicdKey = R.string.pub_key;
    int ID_WalletsKey = R.string.wallets_key;
    int ID_SelectedWalletKey = R.string.selected_wallet_key;
    int ID_PriceKey = R.string.price_key;
    int ID_LanguageKey = R.string.language_key;
    int ID_is_Customfullkey = R.string.is_customfull_key;
    int ID_is_customsolkey = R.string.is_custom_sol_key;
    int ID_customfullkey = R.string.customfull_key;
    int ID_customsolkey = R.string.customsol_key;
    int ID_is_DappChain_Customfullkey = R.string.is_DappChain_customfull_key;
    int ID_is_DappChain_customsolkey = R.string.is_DappChain_custom_sol_key;
    int ID_DappChain_customfullkey = R.string.DappChain_customfull_key;
    int ID_DappChain_customsolkey = R.string.DappChain_customsol_key;
    int ID_BackupKey = R.string.backup_key;
    int ID_IsFirstMakrt = R.string.is_first_market;
    int ID_IsFirstUSDT = R.string.is_first_usdt;
    int ID_IsColdWallet = R.string.is_cold_wallet_key;
    int ID_IsFirst = R.string.is_first;
    int ID_FullNode = R.string.fullnodes_key;
    int ID_FullNode_All = R.string.fullnodes_all_key;
    int ID_SolNode = R.string.solnodes_key;
    int ID_isTest = R.string.test_key;
    int ID_PRICE = R.string.new_price_key;
    int ID_USD_RMB = R.string.usd_to_rmb;
    int ID_isCold = R.string.clod_key;
    int ID_latest_version = R.string.latest_version_key;
    int ID_Splash_png = R.string.splash_png_key;
    int ID_setTrc10Key = R.string.set_trc10_key;
    int ID_setTrc20Key = R.string.set_trc20_key;
    int ID_setTrc10AllKey = R.string.set_alltrc10_key;
    int ID_setTrc20AllKey = R.string.set_alltrc20_key;
    int ID_setHasAccountKey = R.string.set_hasaccount_key;
    int ID_setAssetsListKey = R.string.set_assetslist_key;
    int ID_enTeleUrlKey = R.string.en_teleurl_key;
    int ID_zhTeleUrlKey = R.string.zh_teleurl_key;
    int ID_twitterurlKey = R.string.twitterurl_key;
    int ID_wechaturlKey = R.string.wechaturl_key;
    int ID_walletColorKey = R.string.wallet_color_key;
    int ID_update_version_key = R.string.update_version_key;
    int ID_node_key = R.string.node_key;
    int ID_all_chain_key = R.string.all_chain_key;
    int ID_last_all_chain_key = R.string.last_all_chain_key;
    int ID_mainNodeListKey = R.string.set_mainnodelist_key;
    int ID_CustomeNodeListKey = R.string.set_customnodelist_key;
    int ID_isFirstVoteFreezekey = R.string.is_first_vote_freeze_key;
    int ID_pwdInputKey = R.string.is_pwd_input_key;
    int ID_walletAddressKey = R.string.wallet_address_key;
    int ID_privKey = R.string.priv_key;
    int ID_localDappSearchKey = R.string.local_dapp_search_key;
    int ID_coldNoNetTipClosed = R.string.cold_nonet_tip_closed;
    int ID_chainNameKey = R.string.chain_name_key;
    int ID_test_version_key = R.string.test_version_key;
    int ID_last_test_version_key = R.string.last_test_version_key;
    int ID_NoticeKey = R.string.notice_key;
    int ID_NotNileChainNoticeTimes = R.string.not_nile_chain_notice_times;
    int ID_block_time_interval_key = R.string.block_time_interval_key;
    int ID_recent_transfer_address_Key = R.string.recent_transfer_address_key;
    int ID_recent_send_address_Key = R.string.recent_send_address_key;
    String ID_Firebase_Token = "id_firebase_token";
    String ID_Firebase_Notification_id = "id_firebase_notification_id";
    String ID_reset_wallet_flag = "ID_reset_wallet_flag";
    String ID_update_time = "id_update_time";
    String SYSTEM_TRONSCAN_URL = "system_tronscan_url";
    String SYSTEM_TRONSCAN_DAPP_URL = "system_tronscan_dapp_url";
    String SYSTEM_USING_CRT_2020 = "system_crt_2020";
    String ID_IS_Insert_channel = "is_insert_channel";
    String MNEMONIC_WARNING_CONFIRMED = "mnemonic_warning_confirmed";
    String MNEMONIC_WALLET_PATH = "mnemonic_wallet_path";
    String ID_Watch_Wallet_Index = "id_watch_wallet_index";
    String BANDWIDTH_PER_TRANSCATION = "BandwidthPerTranscation";
    String ENERGY_PER_TRANSCATION = "EnergyPerTranscation";
    String SERVER_USER_PREFER = "server_user_prefer";
    String ID_RECEIVE_NOTIFICATION = AppContextUtil.getContext().getResources().getString(R.string.receive_notification_key);
    String HAS_READ_USER_AGREEMENT = "has_read_user_agreement";
    String HAS_SHOW_SAFE_CHANNEL_POP = "has_show_the_safe_channel_pop";
    String HAS_CHANGED_HD = "has_changed_hd_key";
    String HAS_HD_POP_SHOWED = "has_hd_update_showed";
    String MAX_DELEGATE_LOCK_PERIOD = "maxDelegateLockPeriod";
    String HAS_SHOW_THIRD_ADDRESS_POP = "has_show_third_address_pop";
    String HAS_SHOW_UNKNOWN_ADDRESS_POP = "has_show_unknown_address_pop";
    String AUTO_SERVER = "auto_select_server";
    String KEY_RESOURCES_INTRO_SHOW_FLAG = "key_resources_intro_show_flag";
    String KEY_WALLET_SORT_TYPE = "wallet_sort_Type";
    String KEY_KeyBoard_Margin_TYPE = "keyboard_margin_type";
    String KEY_REAL_WATCH_TYPE = "real_watch_type";
    String KEY_FILTER_SMALL_VALUE = "all_detail_filter_small_value";
    String KEY_FILTER_SMALL_VALUE_THRESHOLD = "trx_detail_filter_small_value_threshold";
    String KEY_FINANCE_IS_SHOW = "key_finance_is_show";
    String KEY_FINANCE_US_URL = "key_finance_us_url";
    String KEY_FINANCE_SINGAPORE_URL = "key_finance_singapore_url";
    String KEY_FINANCE_IS_ALL_ACCOUNT = "key_finance_is_all_account";
    String KEY_FINANCE_TAB_NEW = "key_finance_tab_new";
    String KEY_STAKE_V2_NEW = "key_stake_v2_new";
    String KEY_SAFETY_CHECK_NEW = "key_safety_check_new";
    String KEY_STAKE_HOME_POP = "key_stake_home_pop";
    String KEY_STAKE_VERSION = "key_stake_version";
    String KEY_STAKE_EXPIRE_DAY = "key_stake_expire_day";
    String KEY_WALLET_STAKED = "key_wallet_staked";
    String WALLET_STAKED = "wallet_staked";
    String KEY_WALLET_INTRO_HAS_SHOW = "key_wallet_intro_has_show";
    String KEY_SEC_ASK_DONE = "key_sec_ask_done";
    String KEY_SEC_ASK_CLOSE_TIME = "key_sec_ask_close_time";
    String KEY_CREATE_ACCOUNT_INDEX = "key_create_account_index";
    String KEY_GENERATE_ACCOUNT_NAME = "key_generate_account_name";
    String KEY_WATCH_WALLET_REMINDER_TIME = "watch_wallet_reminder_time";
    String KEY_RECENTLY_WALLET = "key_recently_wallet";
    String LAST_ONE_BACKUP_RECORD = "the_last_one_backup_record";
    String LAST_IP_LOCATION = "the_last_ip_location";
    String KEY_SHIELD_MAINLAND_NOTICE_REMINDER = "key_shield_mainland_notice_reminder";
    private int ID_Samsung_SEED_HASH = R.string.samsung_seed_hash;
    private int ID_Samsung_HD_MAPPING = R.string.samsung_hd_mapping;
    private int ID_TRANSACTION_LOCALHISTORY_KEY = R.string.transaction_localhistory_key;
    private int ID_NFT_TRANSACTION_LOCALHISTORY_KEY = R.string.transaction_localhistory_key;
    private int ID_Decimals = R.string.shield20_decimals;
    private int ID_COLD_WALLET_SELECT = R.string.cold_wallet_select;
    private int ID_DAPP_JS = R.string.dappjs_key;
    private int ID_DAPP_SUN_JS = R.string.dappjs_sun_key;
    private int ID_DAPP_JS_URL = R.string.dappjs_url_key;
    private int ID_DAPP_SUN_JS_URL = R.string.dappjs_sun_url_key;
    private int ID_DAPP_JS_OUTPUT = R.string.dappjs_output_key;
    private int ID_QR_MULTI_IMAGE = R.string.qr_multi_image;
    private String KEY_CURRENT_VERSION = "key_current_version";
    private String KEY_APP_STATUS = "key_app_status";
    private String KEY_APP_Language = "key_app_language";
    private String KEY_PUSH_MSG = "key_push_msg";

    public String getCurChainName() {
        return "MainChain";
    }

    public boolean getCurIsMainChain() {
        return true;
    }

    SpAPI() {
    }

    public int getServerUserPrefer() {
        return ((Integer) SpUtils.getParam(this.F_TRONIP, AppContextUtil.getContext(), this.SERVER_USER_PREFER, 0)).intValue();
    }

    public void setServerUserPrefer(int i) {
        SpUtils.setParam(this.F_TRONIP, AppContextUtil.getContext(), this.SERVER_USER_PREFER, Integer.valueOf(i));
    }

    public int getSYSTEM_USING_CRT_2020() {
        return ((Integer) SpUtils.getParam(this.F_TRONIP, AppContextUtil.getContext(), this.SYSTEM_USING_CRT_2020, 0)).intValue();
    }

    public void setSYSTEM_USING_CRT_2020(int i) {
        SpUtils.setParam(this.F_TRONIP, AppContextUtil.getContext(), this.SYSTEM_USING_CRT_2020, Integer.valueOf(i));
    }

    public String getSystemTronDappUrl() {
        return (String) SpUtils.getParam(this.F_TRONIP, AppContextUtil.getContext(), this.SYSTEM_TRONSCAN_DAPP_URL, "");
    }

    public void setSystemTronDappUrl(String str) {
        SpUtils.setParam(this.F_TRONIP, AppContextUtil.getContext(), this.SYSTEM_TRONSCAN_DAPP_URL, str);
    }

    public void setSystemTronscanUrl(String str) {
        SpUtils.setParam(this.F_TRONIP, AppContextUtil.getContext(), this.SYSTEM_TRONSCAN_URL, str);
    }

    public String getSytemTronscanUrl() {
        return (String) SpUtils.getParam(this.F_TRONIP, AppContextUtil.getContext(), this.SYSTEM_TRONSCAN_URL, "");
    }

    public void removeAll(String str) {
        SpUtils.removeAll(str, AppContextUtil.getContext());
    }

    public String getIP() {
        return (String) SpUtils.getParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_IPkey), "");
    }

    public void setIP(String str) {
        SpUtils.setParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_IPkey), str);
    }

    public String getClientId() {
        return (String) SpUtils.getParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_ClientId), "");
    }

    public void setClientId(String str) {
        SpUtils.setParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_ClientId), str);
    }

    public int getPort() {
        return ((Integer) SpUtils.getParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_PORTkey), 0)).intValue();
    }

    public void setPort(int i) {
        SpUtils.setParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_PORTkey), Integer.valueOf(i));
    }

    public int getSolPort() {
        return ((Integer) SpUtils.getParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_SolPORTkey), 0)).intValue();
    }

    public void setSolPort(int i) {
        SpUtils.setParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_SolPORTkey), Integer.valueOf(i));
    }

    public long getShieldFee() {
        return ((Long) SpUtils.getParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_ShieldFee), 1000000L)).longValue();
    }

    public void setShieldFee(long j) {
        SpUtils.setParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_ShieldFee), Long.valueOf(j));
    }

    public void removeIp() {
        SpUtils.remove(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_IPkey));
    }

    public void removePort() {
        SpUtils.remove(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_PORTkey));
    }

    public void removeSolIp() {
        SpUtils.remove(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_SolIPkey));
    }

    public void removeSolPort() {
        SpUtils.remove(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_SolPORTkey));
    }

    public String getPublicKey(String str) {
        return (String) SpUtils.getParam(str, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_PublicdKey), "");
    }

    public String getPassWord(String str) {
        return (String) SpUtils.getParam(str, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_PassWordKey), "");
    }

    public boolean getResetWalletsPwdFlag() {
        return ((Boolean) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), this.ID_reset_wallet_flag, false)).booleanValue();
    }

    public void setResetWalletsPwdFlag(boolean z) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), this.ID_reset_wallet_flag, Boolean.valueOf(z));
    }

    public Set<String> getAllWallets() {
        return new HashSet((Set) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_WalletsKey), new HashSet()));
    }

    public void setWallet(String str) throws DuplicateNameException {
        Set<String> allWallets = getAllWallets();
        if (str != null && str.contains("/")) {
            throw new DuplicateNameException("Wallet name has path separator");
        }
        if (allWallets != null && !allWallets.contains(str)) {
            allWallets.add(str);
            SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_WalletsKey), allWallets);
            return;
        }
        throw new DuplicateNameException("Wallet name already exist");
    }

    public void removeWallet(String str) throws DuplicateNameException {
        if (str.equals(getSelectedWallet())) {
            SpUtils.remove(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_SelectedWalletKey));
        }
        Set<String> allWallets = getAllWallets();
        if (allWallets != null && allWallets.contains(str)) {
            allWallets.remove(str);
            removeAll(str);
        }
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_WalletsKey), allWallets);
    }

    public void setWallet(String str, String str2) throws DuplicateNameException {
        Set<String> allWallets = getAllWallets();
        if (allWallets != null && allWallets.contains(str2)) {
            allWallets.remove(str2);
            removeAll(str2);
        }
        if (allWallets != null && !allWallets.contains(str)) {
            allWallets.add(str);
            if (getSelectedWallet().equals(str2)) {
                setSelectedWallet(str);
            }
            SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_WalletsKey), allWallets);
            return;
        }
        throw new DuplicateNameException("Wallet name already exist");
    }

    public boolean existWallet(String str) {
        Set<String> allWallets = getAllWallets();
        return allWallets != null && allWallets.contains(str);
    }

    public boolean isColdWallet(String str) {
        return ((Boolean) SpUtils.getParam(str, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_IsColdWallet), false)).booleanValue();
    }

    public String getSelectedWallet() {
        String str = (String) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_SelectedWalletKey), "");
        Set<String> allWallets = getAllWallets();
        return ((!StringTronUtil.isEmpty(str) && allWallets.contains(str)) || allWallets == null || allWallets.size() == 0) ? str : allWallets.iterator().next();
    }

    public void setSelectedWallet(String str) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_SelectedWalletKey), str);
    }

    public void setIsUsdPrice(boolean z) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_PriceKey), Boolean.valueOf(z));
    }

    public boolean isUsdPrice() {
        return ((Boolean) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_PriceKey), true)).booleanValue();
    }

    public void setLanguage(String str) {
        LogUtils.d(TAG, "setLanguage:" + str);
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_LanguageKey), str);
    }

    public String useLanguage() {
        LogUtils.d(TAG, "useLanguage:" + ((String) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_LanguageKey), "")));
        return (String) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_LanguageKey), "");
    }

    public void setIsCustomFull(boolean z) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_is_Customfullkey), Boolean.valueOf(z));
    }

    public boolean isCustomFull() {
        return ((Boolean) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_is_Customfullkey), false)).booleanValue();
    }

    public void setCustomFullNode(String str) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_customfullkey), str);
    }

    public String getCustomeFull() {
        try {
            return (String) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_customfullkey), "");
        } catch (Exception e) {
            SentryUtil.captureException(e);
            return "";
        }
    }

    public void setIsDappChainCustomFull(String str, boolean z) {
        String str2 = this.F_TRONKEY;
        Context context = AppContextUtil.getContext();
        SpUtils.setParam(str2, context, AppContextUtil.getContext().getString(this.ID_is_DappChain_Customfullkey) + str, Boolean.valueOf(z));
    }

    public boolean isDappCustomFull() {
        return isDappCustomFull(IRequest.ENVIRONMENT.toString());
    }

    public boolean isDappCustomFull(String str) {
        String str2 = this.F_TRONKEY;
        Context context = AppContextUtil.getContext();
        return ((Boolean) SpUtils.getParam(str2, context, AppContextUtil.getContext().getString(this.ID_is_DappChain_Customfullkey) + str, false)).booleanValue();
    }

    public void setDappCustomFullNode(String str, String str2) {
        String str3 = this.F_TRONKEY;
        Context context = AppContextUtil.getContext();
        SpUtils.setParam(str3, context, AppContextUtil.getContext().getString(this.ID_DappChain_customfullkey) + str, str2);
    }

    public String getDappCustomeFull() {
        return getDappCustomeFull(IRequest.ENVIRONMENT.toString());
    }

    public String getDappCustomeFull(String str) {
        String str2 = this.F_TRONKEY;
        Context context = AppContextUtil.getContext();
        return (String) SpUtils.getParam(str2, context, AppContextUtil.getContext().getString(this.ID_DappChain_customfullkey) + str, "");
    }

    public void setIsDappCustomSol(boolean z) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_is_DappChain_customsolkey), Boolean.valueOf(z));
    }

    public boolean isDappChainCustomSol() {
        return ((Boolean) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_is_DappChain_customsolkey), false)).booleanValue();
    }

    public String getDappChainCustomeSolidity() {
        return (String) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_DappChain_customsolkey), "");
    }

    public void setDappChainCustomSolidity(String str) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_DappChain_customsolkey), str);
    }

    public boolean isBackUp(String str) {
        return ((Boolean) SpUtils.getParam(str, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_BackupKey), false)).booleanValue();
    }

    public void setBackUp(String str, boolean z) {
        SpUtils.setParam(str, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_BackupKey), Boolean.valueOf(z));
    }

    public boolean isFirst() {
        return ((Boolean) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_IsFirst), true)).booleanValue();
    }

    public boolean isAccept() {
        return ((Boolean) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_IsFirst), true)).booleanValue();
    }

    public void setIsAccept(boolean z) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_IsFirst), Boolean.valueOf(z));
    }

    public boolean isFirstMarket() {
        return ((Boolean) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_IsFirstMakrt), true)).booleanValue();
    }

    public boolean isClickUSDT() {
        return ((Boolean) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_IsFirstUSDT), false)).booleanValue();
    }

    public void setIsFirst(boolean z) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_IsFirst), Boolean.valueOf(z));
    }

    public void setIsFirstMarket(boolean z) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_IsFirstMakrt), Boolean.valueOf(z));
    }

    public void setIsClickUSDT(boolean z) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_IsFirstUSDT), Boolean.valueOf(z));
    }

    public List<String> getFullNodesAll() {
        ArrayList arrayList = new ArrayList();
        String str = (String) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_FullNode_All), "");
        return !TextUtils.isEmpty(str) ? (List) new Gson().fromJson(str, new TypeToken<List<String>>() {
        }.getType()) : arrayList;
    }

    public void setFullNodesAll(List<String> list) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_FullNode_All), new Gson().toJson(list));
    }

    public List<String> getFullNodes() {
        ArrayList arrayList = new ArrayList();
        String str = (String) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_FullNode), "");
        return !TextUtils.isEmpty(str) ? (List) new Gson().fromJson(str, new TypeToken<List<String>>() {
        }.getType()) : arrayList;
    }

    public void setFullNodes(List<String> list) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_FullNode), new Gson().toJson(list));
    }

    public List<String> getSolNodes() {
        ArrayList arrayList = new ArrayList();
        for (String str : (Set) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_SolNode), new HashSet())) {
            arrayList.add(str);
        }
        return arrayList;
    }

    public void setSolNodes(List<String> list) {
        HashSet hashSet = new HashSet();
        for (String str : list) {
            hashSet.add(str);
        }
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_SolNode), hashSet);
    }

    public boolean isShasta() {
        return ((Boolean) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_isTest), false)).booleanValue();
    }

    public void setShasta(boolean z) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_isTest), Boolean.valueOf(z));
    }

    public long getUpdateTime() {
        return ((Long) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), this.ID_update_time, 0L)).longValue();
    }

    public void setUpdateTime(long j) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), this.ID_update_time, Long.valueOf(j));
    }

    public float getUsdToRmb() {
        return ((Float) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_USD_RMB), Float.valueOf(0.0f))).floatValue();
    }

    public void setUsdToRmb(float f) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_USD_RMB), Float.valueOf(f));
    }

    public float getCnyPrice() {
        String string = AppContextUtil.getContext().getString(this.ID_PRICE);
        return ((Float) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), string + "Cny", Float.valueOf(0.0f))).floatValue();
    }

    public void setCnyPrice(float f) {
        String str = this.F_TRONKEY;
        Context context = AppContextUtil.getContext();
        SpUtils.setParam(str, context, AppContextUtil.getContext().getString(this.ID_PRICE) + "Cny", Float.valueOf(f));
    }

    public float getUsdPrice() {
        String string = AppContextUtil.getContext().getString(this.ID_PRICE);
        return ((Float) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), string + "Usd", Float.valueOf(0.0f))).floatValue();
    }

    public void setUsdPrice(float f) {
        String str = this.F_TRONKEY;
        Context context = AppContextUtil.getContext();
        SpUtils.setParam(str, context, AppContextUtil.getContext().getString(this.ID_PRICE) + "Usd", Float.valueOf(f));
    }

    public float getPrice() {
        String str;
        String string = AppContextUtil.getContext().getString(this.ID_PRICE);
        if (isUsdPrice()) {
            str = string + "Usd";
        } else {
            str = string + "Cny";
        }
        return ((Float) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), str, Float.valueOf(0.0f))).floatValue();
    }

    public boolean isCold() {
        return ((Boolean) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_isCold), false)).booleanValue();
    }

    public void setCold(boolean z) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_isCold), Boolean.valueOf(z));
    }

    public boolean isLastestClick(String str) {
        return ((Boolean) SpUtils.getParam(str, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_latest_version), false)).booleanValue();
    }

    public void setLastestClick(String str, boolean z) {
        SpUtils.setParam(str, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_latest_version), Boolean.valueOf(z));
    }

    public String getSplashPng() {
        LogUtils.e("splashPngEmpty", AppContextUtil.getContext().getString(this.ID_Splash_png));
        LogUtils.e("splashPngEmpty", "==" + ((String) SpUtils.getParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_Splash_png), "")));
        return (String) SpUtils.getParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_Splash_png), "");
    }

    public void setSplashPng(String str) {
        SpUtils.setParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_Splash_png), str);
    }

    public void setTrc10List(String str, List<String> list) {
        HashSet hashSet = new HashSet();
        for (String str2 : list) {
            hashSet.add(str2);
        }
        SpUtils.setParam(str, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_setTrc10Key), hashSet);
    }

    public void setTrc10List(String str, String str2) {
        List<String> trc10List = getTrc10List(str);
        if (trc10List.contains(str2)) {
            return;
        }
        trc10List.add(str2);
        HashSet hashSet = new HashSet();
        for (String str3 : trc10List) {
            hashSet.add(str3);
        }
        SpUtils.setParam(str, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_setTrc10Key), hashSet);
    }

    public void removeTrc10(String str, String str2) {
        List<String> trc10List = getTrc10List(str);
        if (trc10List.contains(str2)) {
            trc10List.remove(str2);
            HashSet hashSet = new HashSet();
            for (String str3 : trc10List) {
                hashSet.add(str3);
            }
            SpUtils.setParam(str, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_setTrc10Key), hashSet);
        }
    }

    public List<String> getTrc10List(String str) {
        ArrayList arrayList = new ArrayList();
        for (String str2 : (Set) SpUtils.getParam(str, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_setTrc10Key), new HashSet())) {
            arrayList.add(str2);
        }
        return arrayList;
    }

    public void setTrc20List(String str, List<String> list) {
        HashSet hashSet = new HashSet();
        for (String str2 : list) {
            hashSet.add(str2);
        }
        SpUtils.setParam(str, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_setTrc20Key), hashSet);
    }

    public void setTrc20List(String str, String str2) {
        List<String> trc20List = getTrc20List(str);
        if (trc20List.contains(str2)) {
            return;
        }
        trc20List.add(str2);
        HashSet hashSet = new HashSet();
        for (String str3 : trc20List) {
            hashSet.add(str3);
        }
        SpUtils.setParam(str, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_setTrc20Key), hashSet);
    }

    public void removeTrc20(String str, String str2) {
        List<String> trc20List = getTrc20List(str);
        if (trc20List.contains(str2)) {
            trc20List.remove(str2);
            HashSet hashSet = new HashSet();
            for (String str3 : trc20List) {
                hashSet.add(str3);
            }
            SpUtils.setParam(str, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_setTrc20Key), hashSet);
        }
    }

    public List<String> getTrc20List(String str) {
        ArrayList arrayList = new ArrayList();
        for (String str2 : (Set) SpUtils.getParam(str, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_setTrc20Key), new HashSet())) {
            arrayList.add(str2);
        }
        return arrayList;
    }

    public void setTrc20All(String str, boolean z) {
        SpUtils.setParam(str, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_setTrc20AllKey), Boolean.valueOf(z));
    }

    public boolean getTrc20All(String str) {
        return ((Boolean) SpUtils.getParam(str, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_setTrc20AllKey), true)).booleanValue();
    }

    public void setTrc10All(String str, boolean z) {
        SpUtils.setParam(str, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_setTrc10AllKey), Boolean.valueOf(z));
    }

    public boolean getTrc10All(String str) {
        return ((Boolean) SpUtils.getParam(str, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_setTrc10AllKey), true)).booleanValue();
    }

    public void setHasAccount(String str, boolean z) {
        SpUtils.setParam(str, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_setHasAccountKey), Boolean.valueOf(z));
    }

    public boolean getHasAccount(String str) {
        return ((Boolean) SpUtils.getParam(str, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_setHasAccountKey), false)).booleanValue();
    }

    public Map<String, String> getAssetsList() {
        HashMap hashMap = new HashMap();
        Set<String> set = (Set) SpUtils.getParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_setAssetsListKey), new HashSet());
        if (set.size() != 0) {
            for (String str : set) {
                String[] split = str.split("&&&");
                hashMap.put(split[0], split[1]);
            }
        }
        return hashMap;
    }

    public void setAssetsList(List<String> list) {
        HashSet hashSet = new HashSet();
        for (String str : list) {
            hashSet.add(str);
        }
        SpUtils.setParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_setAssetsListKey), hashSet);
    }

    public String getEnTeleUrl() {
        return (String) SpUtils.getParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_enTeleUrlKey), "");
    }

    public void setEnTeleUrl(String str) {
        SpUtils.setParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_enTeleUrlKey), str);
    }

    public String getZhTeleUrl() {
        return (String) SpUtils.getParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_zhTeleUrlKey), "");
    }

    public void setZhTeleUrl(String str) {
        SpUtils.setParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_zhTeleUrlKey), str);
    }

    public String getTwitterUrl() {
        return (String) SpUtils.getParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_twitterurlKey), "");
    }

    public void setTwitterUrl(String str) {
        SpUtils.setParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_twitterurlKey), str);
    }

    public String getWechatUrl() {
        return (String) SpUtils.getParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_wechaturlKey), "");
    }

    public void setWechatUrl(String str) {
        SpUtils.setParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_wechaturlKey), str);
    }

    public int getWalletColor(String str) {
        return ((Integer) SpUtils.getParam(str, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_walletColorKey), -1)).intValue();
    }

    public void saveWalletColor(String str, int i) {
        SpUtils.setParam(str, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_walletColorKey), Integer.valueOf(i));
    }

    public boolean isFirstVoteFreeze(String str) {
        return ((Boolean) SpUtils.getParam(str, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_isFirstVoteFreezekey), true)).booleanValue();
    }

    public void setIsFirstVoteFreeze(String str, boolean z) {
        SpUtils.setParam(str, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_isFirstVoteFreezekey), Boolean.valueOf(z));
    }

    public boolean hasShowTips(String str, String str2) {
        return ((Boolean) SpUtils.getParam(str, AppContextUtil.getContext(), str2, false)).booleanValue();
    }

    public void setShowTips(String str, String str2, boolean z) {
        SpUtils.setParam(str, AppContextUtil.getContext(), str2, Boolean.valueOf(z));
    }

    public String getUpdateJson() {
        return (String) SpUtils.getParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_update_version_key), "");
    }

    public void setUpdateJson(String str) {
        SpUtils.setParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_update_version_key), str);
    }

    public String getPwdInputJson(String str) {
        return (String) SpUtils.getParam(str, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_pwdInputKey), "");
    }

    public void setPwdInputJson(String str, String str2) {
        SpUtils.setParam(str, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_pwdInputKey), str2);
    }

    public String getWalletAddress(String str) {
        return (String) SpUtils.getParam(str, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_walletAddressKey), "");
    }

    public void setWalletAddress(String str, String str2) {
        SpUtils.setParam(str, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_walletAddressKey), str2);
    }

    public void setDappName(String str) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), str, true);
    }

    public boolean getDappName(String str) {
        return ((Boolean) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), str, false)).booleanValue();
    }

    public boolean hasPrivatekey(String str) {
        return ((Boolean) SpUtils.getParam(str, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_PrivatedKey), false)).booleanValue();
    }

    public DappLocalSearchBean getLocalDappSearch() {
        String str = (String) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_localDappSearchKey), "");
        if (StringTronUtil.isEmpty(str)) {
            return null;
        }
        return (DappLocalSearchBean) new Gson().fromJson(str,  DappLocalSearchBean.class);
    }

    public void setLocalDappSearch(DappLocalSearchBean dappLocalSearchBean) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_localDappSearchKey), new Gson().toJson(dappLocalSearchBean));
    }

    public boolean getNoNetIsClosed() {
        return ((Boolean) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_coldNoNetTipClosed), false)).booleanValue();
    }

    public void setNoNetIsClosed(boolean z) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_coldNoNetTipClosed), Boolean.valueOf(z));
    }

    public int getNotNileChainNoticeTimes() {
        return ((Integer) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_NotNileChainNoticeTimes), 0)).intValue();
    }

    public void setNotNileChainNoticeTimes(int i) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_NotNileChainNoticeTimes), Integer.valueOf(i));
    }

    public List<ChainBean> getAllChainJson() {
        String str = (String) SpUtils.getParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_all_chain_key), "");
        if (!StringTronUtil.isEmpty(str)) {
            try {
                return (List) new Gson().fromJson(str, new TypeToken<List<ChainBean>>() {
                }.getType());
            } catch (JsonSyntaxException e) {
                LogUtils.e(e);
            }
        }
        return null;
    }

    public void setAllChainJson(List<ChainBean> list) {
        LogUtils.d("IGrpcdClient", "setAllChainJson  ");
        if (list == null || list.size() == 0) {
            SpUtils.setParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_all_chain_key), "");
            return;
        }
        String json = new Gson().toJson(list);
        if (StringTronUtil.isEmpty(json)) {
            return;
        }
        SpUtils.setParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_all_chain_key), json);
    }

    public List<ChainBean> getLastAllChainJson() {
        String str = (String) SpUtils.getParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_last_all_chain_key), "");
        if (!StringTronUtil.isEmpty(str)) {
            try {
                return (List) new Gson().fromJson(str, new TypeToken<List<ChainBean>>() {
                }.getType());
            } catch (JsonSyntaxException e) {
                LogUtils.e(e);
            }
        }
        return null;
    }

    public void setLastAllChainJson(List<ChainBean> list) {
        LogUtils.d("IGrpcdClient", "setLastAllChainJson  ");
        if (list == null || list.size() == 0) {
            SpUtils.setParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_last_all_chain_key), "");
            return;
        }
        String json = new Gson().toJson(list);
        if (StringTronUtil.isEmpty(json)) {
            return;
        }
        SpUtils.setParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_last_all_chain_key), json);
    }

    public ChainBean getCurrentChain() {
        ChainBean chainBean;
        String str = (String) SpUtils.getParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_all_chain_key), "");
        if (!StringTronUtil.isEmpty(str)) {
            try {
                List list = (List) new Gson().fromJson(str, new TypeToken<List<ChainBean>>() {
                }.getType());
                if (list != null && list.size() > 0) {
                    chainBean = null;
                    for (int i = 0; i < list.size(); i++) {
                        if (((ChainBean) list.get(i)).isMainChain) {
                            chainBean = (ChainBean) list.get(i);
                        }
                    }
                } else {
                    chainBean = new ChainBean();
                    chainBean.isMainChain = true;
                    chainBean.isSelect = true;
                    chainBean.chainName = "MainChain";
                }
            } catch (JsonSyntaxException e) {
                LogUtils.e(e);
                chainBean = new ChainBean();
                chainBean.isMainChain = true;
                chainBean.isSelect = true;
                chainBean.chainName = "MainChain";
            }
        } else {
            chainBean = new ChainBean();
            chainBean.isMainChain = true;
            chainBean.isSelect = true;
            chainBean.chainName = "MainChain";
        }
        if (chainBean == null) {
            chainBean = new ChainBean();
            chainBean.isMainChain = true;
            chainBean.isSelect = true;
            chainBean.chainName = "MainChain";
        }
        LogUtils.d("IGrpcdClient", "getCurrentChain  " + chainBean.isMainChain);
        return chainBean;
    }

    public Map<String, MainNodeOutput.DataBean> getMainNodeList() {
        HashMap hashMap = new HashMap();
        String str = (String) SpUtils.getParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_mainNodeListKey), "");
        if (!StringTronUtil.isEmpty(str)) {
            try {
                List<MainNodeOutput.DataBean> list = (List) new Gson().fromJson(str, new TypeToken<List<MainNodeOutput.DataBean>>() {
                }.getType());
                if (list != null && list.size() > 0) {
                    for (MainNodeOutput.DataBean dataBean : list) {
                        if (dataBean != null) {
                            if (dataBean.isMainChain == 1) {
                                hashMap.put("MainChain", dataBean);
                            } else {
                                hashMap.put(dataBean.chainName, dataBean);
                            }
                        }
                    }
                }
            } catch (JsonSyntaxException e) {
                LogUtils.e(e);
            }
        }
        return hashMap;
    }

    public void setMainNodeList(List<MainNodeOutput.DataBean> list) {
        if (list == null || list.size() == 0) {
            SpUtils.setParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_mainNodeListKey), null);
            return;
        }
        String json = new Gson().toJson(list);
        LogUtils.d("NodeSpeednodeListJson", json);
        if (StringTronUtil.isEmpty(json)) {
            return;
        }
        SpUtils.setParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_mainNodeListKey), json);
    }

    public Map<String, MainNodeOutput.DataBean> getCustomNodeList(String str) {
        String str2 = BuildConfig.API_HOST.equals(str) ? "" : "_" + str;
        HashMap hashMap = new HashMap();
        String str3 = (String) SpUtils.getParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_CustomeNodeListKey) + str2, "");
        if (!StringTronUtil.isEmpty(str3)) {
            try {
                List<MainNodeOutput.DataBean> list = (List) new Gson().fromJson(str3, new TypeToken<List<MainNodeOutput.DataBean>>() {
                }.getType());
                if (list != null && list.size() > 0) {
                    for (MainNodeOutput.DataBean dataBean : list) {
                        if (dataBean != null) {
                            if (dataBean.isMainChain == 1) {
                                hashMap.put("MainChain", dataBean);
                            } else {
                                hashMap.put(dataBean.chainName, dataBean);
                            }
                        }
                    }
                }
            } catch (JsonSyntaxException e) {
                LogUtils.e(e);
            }
        }
        return hashMap;
    }

    public String getCurrentChainId() {
        ChainBean currentChain = getCurrentChain();
        return (currentChain == null || currentChain.chainId == null) ? "" : currentChain.chainId;
    }

    public void setCustomNodeList(String str, List<MainNodeOutput.DataBean> list) {
        String str2;
        if (list == null || list.size() == 0) {
            return;
        }
        if (BuildConfig.API_HOST.equals(str)) {
            str2 = "";
        } else {
            str2 = "_" + str;
        }
        String json = new Gson().toJson(list);
        if (StringTronUtil.isEmpty(json)) {
            return;
        }
        SpUtils.setParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_CustomeNodeListKey) + str2, json);
    }

    public String getCurrentChainName() {
        return getCurChainName();
    }

    public void setCurrentChainName(String str) {
        LogUtils.d("IGrpcdClient", "setCurrentChainName  " + str);
        setCurChainName(str);
    }

    public void setCurChainName(String str) {
        if (str.equals("")) {
            str = "MainChain";
        }
        SpUtils.setParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_chainNameKey), str);
    }

    public int getTestVersion() {
        return ((Integer) SpUtils.getParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_test_version_key), 1)).intValue();
    }

    public void setTestVersion(int i) {
        SpUtils.setParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_test_version_key), Integer.valueOf(i));
    }

    public int getLastTestVersion() {
        return ((Integer) SpUtils.getParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_last_test_version_key), 1)).intValue();
    }

    public void setLastTestVersion(int i) {
        SpUtils.setParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_last_test_version_key), Integer.valueOf(i));
    }

    public List<String> getNoticeList() {
        ArrayList arrayList = new ArrayList();
        for (String str : (Set) SpUtils.getParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_NoticeKey), new HashSet())) {
            arrayList.add(str);
        }
        return arrayList;
    }

    public void setNoticeList(List<String> list) {
        HashSet hashSet = new HashSet();
        for (String str : list) {
            hashSet.add(str);
        }
        SpUtils.setParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_NoticeKey), hashSet);
    }

    public void setNoticeList(String str) {
        List<String> noticeList = getNoticeList();
        if (noticeList.contains(str)) {
            return;
        }
        noticeList.add(str);
        HashSet hashSet = new HashSet();
        for (String str2 : noticeList) {
            hashSet.add(str2);
        }
        SpUtils.setParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_NoticeKey), hashSet);
    }

    public String getSamsung_SEED_HASH() {
        String str = (String) SpUtils.getParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_Samsung_SEED_HASH), "");
        if (StringTronUtil.isEmpty(str)) {
            return null;
        }
        return str;
    }

    public void setSamsung_SEED_HASH(String str) {
        SpUtils.setParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_Samsung_SEED_HASH), str);
    }

    public Map<String, String> getSamsung_HD_MAPPING() {
        return SpUtils.getHashMapData(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_Samsung_HD_MAPPING));
    }

    public void setSamsung_HD_MAPPING(Map<String, String> map) {
        SpUtils.putHashMapData(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_Samsung_HD_MAPPING), map);
    }

    public long getDappToMainFee() {
        return ((Long) SpUtils.getParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_DappToMainFee), 0L)).longValue();
    }

    public void setDappToMainFee(long j) {
        SpUtils.setParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_DappToMainFee), Long.valueOf(j));
    }

    public String getMarketBannerMapping() {
        Object param = SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(R.string.market_banner_cache), "");
        return !(param instanceof String) ? "" : (String) param;
    }

    public void setMarketBannerMapping(String str) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(R.string.market_banner_cache), str);
    }

    public long getBlockTimeInterval() {
        return ((Long) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_block_time_interval_key), 0L)).longValue();
    }

    public void setBlockTimeInterval(long j) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_block_time_interval_key), Long.valueOf(j));
    }

    public void addTransactionLocalHistory(String str, TransactionHistoryBean transactionHistoryBean) {
        Map<String, TransactionHistoryBean> transactionLocalHistory = getTransactionLocalHistory();
        if (transactionLocalHistory == null) {
            transactionLocalHistory = new HashMap<>();
        }
        if (!transactionLocalHistory.containsKey(str)) {
            transactionLocalHistory.put(str, transactionHistoryBean);
        }
        setTransactionLocalHistory(transactionLocalHistory);
    }

    public void addNftTransactionLocalHistory(String str, NftTransferOutput.NftTransfer nftTransfer) {
        Map<String, NftTransferOutput.NftTransfer> nftTransactionLocalHistory = getNftTransactionLocalHistory();
        if (nftTransactionLocalHistory == null) {
            nftTransactionLocalHistory = new HashMap<>();
        }
        if (!nftTransactionLocalHistory.containsKey(str)) {
            nftTransactionLocalHistory.put(str, nftTransfer);
        }
        setNftTransactionLocalHistory(nftTransactionLocalHistory);
    }

    public void removeTransactionLocalHistory(String str) {
        Map<String, TransactionHistoryBean> transactionLocalHistory = getTransactionLocalHistory();
        if (transactionLocalHistory == null || transactionLocalHistory.size() == 0 || !transactionLocalHistory.containsKey(str)) {
            return;
        }
        transactionLocalHistory.remove(str);
        setTransactionLocalHistory(transactionLocalHistory);
    }

    public void removeNftTransactionLocalHistory(String str) {
        Map<String, NftTransferOutput.NftTransfer> nftTransactionLocalHistory = getNftTransactionLocalHistory();
        if (nftTransactionLocalHistory == null || nftTransactionLocalHistory.size() == 0 || !nftTransactionLocalHistory.containsKey(str)) {
            return;
        }
        nftTransactionLocalHistory.remove(str);
        setNftTransactionLocalHistory(nftTransactionLocalHistory);
    }

    public Map<String, TransactionHistoryBean> getTransactionLocalHistory() {
        Map map;
        HashMap hashMap = new HashMap();
        String str = this.F_TRON_NEW;
        Context context = AppContextUtil.getContext();
        Object param = SpUtils.getParam(str, context, AppContextUtil.getContext().getString(this.ID_TRANSACTION_LOCALHISTORY_KEY) + "_" + getEnvironmentString(), "");
        if (param instanceof String) {
            try {
                map = (Map) JSONObject.parse((String) param);
            } catch (Exception e) {
                LogUtils.e(e);
            }
            if (map != null && map.size() != 0) {
                for (String str2 : map.keySet()) {
                    try {
                        hashMap.put(str2, (TransactionHistoryBean) ((JSONObject) map.get(str2)).toJavaObject( TransactionHistoryBean.class));
                    } catch (Exception e2) {
                        LogUtils.e(e2);
                    }
                }
                return hashMap;
            }
            return hashMap;
        }
        return hashMap;
    }

    public void setTransactionLocalHistory(Map<String, TransactionHistoryBean> map) {
        try {
            String gsonString = GsonUtils.toGsonString(map);
            String str = this.F_TRON_NEW;
            Context context = AppContextUtil.getContext();
            SpUtils.setParam(str, context, AppContextUtil.getContext().getString(this.ID_TRANSACTION_LOCALHISTORY_KEY) + "_" + getEnvironmentString(), gsonString);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public Map<String, NftTransferOutput.NftTransfer> getNftTransactionLocalHistory() {
        Map map;
        HashMap hashMap = new HashMap();
        String str = this.F_TRON_NEW;
        Context context = AppContextUtil.getContext();
        Object param = SpUtils.getParam(str, context, AppContextUtil.getContext().getString(this.ID_NFT_TRANSACTION_LOCALHISTORY_KEY) + "_" + getEnvironmentString(), "");
        if (param instanceof String) {
            try {
                map = (Map) JSONObject.parse((String) param);
            } catch (Exception e) {
                LogUtils.e(e);
            }
            if (map != null && map.size() != 0) {
                for (String str2 : map.keySet()) {
                    try {
                        hashMap.put(str2, (NftTransferOutput.NftTransfer) ((JSONObject) map.get(str2)).toJavaObject( NftTransferOutput.NftTransfer.class));
                    } catch (Exception e2) {
                        LogUtils.e(e2);
                    }
                }
                return hashMap;
            }
            return hashMap;
        }
        return hashMap;
    }

    public void setNftTransactionLocalHistory(Map<String, NftTransferOutput.NftTransfer> map) {
        try {
            String gsonString = GsonUtils.toGsonString(map);
            String str = this.F_TRON_NEW;
            Context context = AppContextUtil.getContext();
            SpUtils.setParam(str, context, AppContextUtil.getContext().getString(this.ID_NFT_TRANSACTION_LOCALHISTORY_KEY) + "_" + getEnvironmentString(), gsonString);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public List<DecimalsBean> get20Decimals() {
        String str = (String) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_Decimals), "");
        if (!StringTronUtil.isEmpty(str)) {
            try {
                return (List) new Gson().fromJson(str, new TypeToken<List<DecimalsBean>>() {
                }.getType());
            } catch (JsonSyntaxException e) {
                LogUtils.e(e);
            }
        }
        return null;
    }

    public void set20Decimals(List<DecimalsBean> list) {
        if (list == null || list.size() == 0) {
            SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_Decimals), "");
            return;
        }
        String json = new Gson().toJson(list);
        if (StringTronUtil.isEmpty(json)) {
            return;
        }
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_Decimals), json);
    }

    public String getColdWalletSelected() {
        String str = (String) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_COLD_WALLET_SELECT), "");
        return StringTronUtil.isEmpty(str) ? getSelectedWallet() : str;
    }

    public void setColdWalletSelected(String str) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_COLD_WALLET_SELECT), str);
    }

    public void removeColdWalletSelected() {
        SpUtils.remove(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_COLD_WALLET_SELECT));
    }

    public void setDappjsString(String str) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_DAPP_JS), str);
    }

    public String getDappJsString() {
        return (String) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_DAPP_JS), "");
    }

    public String getTronDappJs() {
        return (String) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_DAPP_JS_URL), "");
    }

    public void setTronDappJs(String str) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_DAPP_JS_URL), str);
    }

    public void setDappjsString_SUN(String str) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_DAPP_SUN_JS), str);
    }

    public String getDappJsString_SUN() {
        return (String) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_DAPP_SUN_JS), "");
    }

    public String getDappJSOutput() {
        String str = this.F_TRONKEY;
        Context context = AppContextUtil.getContext();
        return (String) SpUtils.getParam(str, context, AppContextUtil.getContext().getString(this.ID_DAPP_JS_OUTPUT) + getEnvironmentString(), "");
    }

    public void setDappJSOutput(String str) {
        String str2 = this.F_TRONKEY;
        Context context = AppContextUtil.getContext();
        SpUtils.setParam(str2, context, AppContextUtil.getContext().getString(this.ID_DAPP_JS_OUTPUT) + getEnvironmentString(), str);
    }

    public void setDappjsUrl_SUN(String str) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_DAPP_SUN_JS_URL), str);
    }

    public String getDappJsUrl_SUN() {
        return (String) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_DAPP_SUN_JS_URL), "");
    }

    public boolean getQRMultiImageIsOpen() {
        return ((Boolean) SpUtils.getParam(this.F_TRON_NEW, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_QR_MULTI_IMAGE), true)).booleanValue();
    }

    public void setQRMultiImageIsOpen(boolean z) {
        SpUtils.setParam(this.F_TRON_NEW, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_QR_MULTI_IMAGE), Boolean.valueOf(z));
    }

    public RecentTransferAddressBean getRecentTransferAddresses() {
        String str = (String) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_recent_transfer_address_Key), "");
        if (StringTronUtil.isEmpty(str)) {
            return null;
        }
        return (RecentTransferAddressBean) new Gson().fromJson(str,  RecentTransferAddressBean.class);
    }

    @Deprecated
    public void setRecentTransferAddresses(RecentTransferAddressBean recentTransferAddressBean) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_recent_transfer_address_Key), new Gson().toJson(recentTransferAddressBean));
    }

    public List<RecentSendBean> getRecentSendAddress() {
        String str = (String) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_recent_send_address_Key), "");
        if (StringTronUtil.isEmpty(str)) {
            return null;
        }
        return JSON.parseArray(str, RecentSendBean.class);
    }

    public void setRecentSendAddress(List<RecentSendBean> list) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_recent_send_address_Key), new Gson().toJson(list));
    }

    public String getFireBaseToken() {
        return (String) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), this.ID_Firebase_Token, "");
    }

    public void setFireBaseToken(String str) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), this.ID_Firebase_Token, str);
    }

    public int getFirebaseNotificationID() {
        return ((Integer) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), this.ID_Firebase_Notification_id, 0)).intValue();
    }

    public void setFirebaseNotificationID(int i) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), this.ID_Firebase_Notification_id, Integer.valueOf(i));
    }

    public void setIsInsertChannelVersionCode(String str) {
        String str2 = (String) SpUtils.getParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.ID_IS_Insert_channel, "");
        StringBuffer stringBuffer = new StringBuffer();
        if (!StringTronUtil.isEmpty(str2)) {
            stringBuffer.append(str2);
            stringBuffer.append("\n\t");
        }
        stringBuffer.append(str);
        SpUtils.setParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.ID_IS_Insert_channel, stringBuffer.toString());
    }

    public boolean isInsertChannelVersionCode(String str) {
        String str2 = (String) SpUtils.getParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.ID_IS_Insert_channel, "");
        if (StringTronUtil.isEmpty(str2)) {
            return false;
        }
        return str2.contains(str);
    }

    public String getEnvironmentString() {
        StringBuilder sb = new StringBuilder();
        sb.append(IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.RELEASE ? "" : IRequest.ENVIRONMENT.toString());
        sb.append("_");
        sb.append(getCurrentChainName().equals("MainChain") ? "" : TronConfig.DApp_CHAIN_NAME);
        return sb.toString();
    }

    public String getCustomWalletPath() {
        String str = this.F_TRON_NEW;
        Context context = AppContextUtil.getContext();
        return (String) SpUtils.getParam(str, context, this.MNEMONIC_WALLET_PATH + HelpFormatter.DEFAULT_OPT_PREFIX + getEnvironmentString(), "");
    }

    public void setCustomWalletPath(String str) {
        String str2 = this.F_TRON_NEW;
        Context context = AppContextUtil.getContext();
        SpUtils.setParam(str2, context, this.MNEMONIC_WALLET_PATH + HelpFormatter.DEFAULT_OPT_PREFIX + getEnvironmentString(), str);
    }

    public MessagePermission getMessagePermission() {
        try {
            MessagePermission messagePermission = (MessagePermission) GsonUtils.gsonToBean((String) SpUtils.getParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.ID_RECEIVE_NOTIFICATION, ""), MessagePermission.class);
            return messagePermission == null ? new MessagePermission() : messagePermission;
        } catch (Exception unused) {
            return new MessagePermission();
        }
    }

    public void setMessagePermission(MessagePermission messagePermission) {
        try {
            SpUtils.setParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.ID_RECEIVE_NOTIFICATION, GsonUtils.toGsonString(messagePermission));
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void setWalletNameIndex(String str, int i) {
        SpUtils.setParam(this.F_TRON_NEW, AppContextUtil.getContext(), str, Integer.valueOf(i));
    }

    public int getWalletNameIndex(String str) {
        return ((Integer) SpUtils.getParam(this.F_TRON_NEW, AppContextUtil.getContext(), str, 0)).intValue();
    }

    public List<String> getRecentlyWallet() {
        List<String> list;
        try {
            list = GsonUtils.gsonToList((String) SpUtils.getParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_RECENTLY_WALLET, ""), String.class);
        } catch (Exception e) {
            LogUtils.e(e);
            list = null;
        }
        return list == null ? new ArrayList() : list;
    }

    public void setRecentlyWallet(List<String> list) {
        try {
            SpUtils.setParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_RECENTLY_WALLET, GsonUtils.toGsonString(list));
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public int getWatchWalletIndex() {
        return ((Integer) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), this.ID_Watch_Wallet_Index, 1)).intValue();
    }

    public void setWatchWalletIndex(int i) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), this.ID_Watch_Wallet_Index, Integer.valueOf(i));
    }

    public boolean hasReadUserAgreement() {
        return ((Boolean) SpUtils.getParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.HAS_READ_USER_AGREEMENT, false)).booleanValue();
    }

    public void setReadUserAgreement() {
        SpUtils.setParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.HAS_READ_USER_AGREEMENT, true);
    }

    public boolean isServerAuto() {
        return ((Boolean) SpUtils.getParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.AUTO_SERVER, true)).booleanValue();
    }

    public void setServerAuto(boolean z) {
        SpUtils.setParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.AUTO_SERVER, Boolean.valueOf(z));
    }

    public boolean getResourcesIntroShowFlag() {
        return ((Boolean) SpUtils.getParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_RESOURCES_INTRO_SHOW_FLAG, false)).booleanValue();
    }

    public void setResourcesIntroShowFlag(boolean z) {
        SpUtils.setParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_RESOURCES_INTRO_SHOW_FLAG, Boolean.valueOf(z));
    }

    public int getBandwidthPerTranscation() {
        String str = this.F_TRONKEY;
        Context context = AppContextUtil.getContext();
        return ((Integer) SpUtils.getParam(str, context, this.BANDWIDTH_PER_TRANSCATION + getEnvironmentString(), 345)).intValue();
    }

    public void setBandwidthPerTranscation(int i) {
        String str = this.F_TRONKEY;
        Context context = AppContextUtil.getContext();
        SpUtils.setParam(str, context, this.BANDWIDTH_PER_TRANSCATION + getEnvironmentString(), Integer.valueOf(i));
    }

    public int getEnergyPerTranscation() {
        String str = this.F_TRONKEY;
        Context context = AppContextUtil.getContext();
        return ((Integer) SpUtils.getParam(str, context, this.ENERGY_PER_TRANSCATION + getEnvironmentString(), Integer.valueOf((int) BrowserConstant.REQUEST_CODE_PERMISSION))).intValue();
    }

    public void setEnergyPerTranscation(int i) {
        String str = this.F_TRONKEY;
        Context context = AppContextUtil.getContext();
        SpUtils.setParam(str, context, this.ENERGY_PER_TRANSCATION + getEnvironmentString(), Integer.valueOf(i));
    }

    public int getWalletSortType() {
        return ((Integer) SpUtils.getParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_WALLET_SORT_TYPE, 1)).intValue();
    }

    public void setWalletSortType(int i) {
        SpUtils.setParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_WALLET_SORT_TYPE, Integer.valueOf(i));
    }

    public int getKeyBoardMarginHeight() {
        return ((Integer) SpUtils.getParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_KeyBoard_Margin_TYPE, 0)).intValue();
    }

    public void setKeyBoardMarginHeight(int i) {
        SpUtils.setParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_KeyBoard_Margin_TYPE, Integer.valueOf(i));
    }

    public Map<String, String> getRealWatchWalletsMap() {
        return SpUtils.getHashMapData(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_REAL_WATCH_TYPE);
    }

    public void setRealWatchWalletsMap(Map<String, String> map) {
        SpUtils.putHashMapData(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_REAL_WATCH_TYPE, map);
    }

    public Map<String, String> getWatchWalletReminderTimeMap() {
        return SpUtils.getHashMapData(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_WATCH_WALLET_REMINDER_TIME);
    }

    public void setWatchWalletReminderTimeMap(Map<String, String> map) {
        SpUtils.putHashMapData(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_WATCH_WALLET_REMINDER_TIME, map);
    }

    public boolean getMigrateWarning() {
        return ((Boolean) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), "migrate_one_click", false)).booleanValue();
    }

    public void setMigrateWarning(boolean z) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), "migrate_one_click", Boolean.valueOf(z));
    }

    public BrowserTabIndexBean getBrowserTabIndexBean() {
        try {
            return (BrowserTabIndexBean) GsonUtils.gsonToBean((String) SpUtils.getParam(this.F_TRON_NEW, AppContextUtil.getContext(), "browser_tab_index", ""), BrowserTabIndexBean.class);
        } catch (Throwable unused) {
            return new BrowserTabIndexBean();
        }
    }

    public void setBrowserTabIndexBean(BrowserTabIndexBean browserTabIndexBean) {
        SpUtils.setParam(this.F_TRON_NEW, AppContextUtil.getContext(), "browser_tab_index", browserTabIndexBean != null ? GsonUtils.toGsonString(browserTabIndexBean) : "");
    }

    public boolean getFilterSmallValue() {
        return ((Boolean) SpUtils.getParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_FILTER_SMALL_VALUE, true)).booleanValue();
    }

    public void setFilterSmallValue(boolean z) {
        SpUtils.setParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_FILTER_SMALL_VALUE, Boolean.valueOf(z));
    }

    public String getFilterSmallValueThreshold() {
        String str = this.F_TRON_NEW;
        Context context = AppContextUtil.getContext();
        return (String) SpUtils.getParam(str, context, this.KEY_FILTER_SMALL_VALUE_THRESHOLD + getEnvironmentString(), "0.5");
    }

    public void setFilterSmallValueThreshold(String str) {
        String str2 = this.F_TRON_NEW;
        Context context = AppContextUtil.getContext();
        SpUtils.setParam(str2, context, this.KEY_FILTER_SMALL_VALUE_THRESHOLD + getEnvironmentString(), str);
    }

    public int getFinanceShow() {
        String str = this.F_TRON_NEW;
        Context context = AppContextUtil.getContext();
        return ((Integer) SpUtils.getParam(str, context, this.KEY_FINANCE_IS_SHOW + getEnvironmentString(), 0)).intValue();
    }

    public void setFinanceShow(int i) {
        String str = this.F_TRON_NEW;
        Context context = AppContextUtil.getContext();
        SpUtils.setParam(str, context, this.KEY_FINANCE_IS_SHOW + getEnvironmentString(), Integer.valueOf(i));
    }

    public String getFinanceUSUrl() {
        String str = this.F_TRON_NEW;
        Context context = AppContextUtil.getContext();
        return (String) SpUtils.getParam(str, context, this.KEY_FINANCE_US_URL + getEnvironmentString(), IRequest.getFinancialHost());
    }

    public void setFinanceUSUrl(String str) {
        String str2 = this.F_TRON_NEW;
        Context context = AppContextUtil.getContext();
        SpUtils.setParam(str2, context, this.KEY_FINANCE_US_URL + getEnvironmentString(), str);
    }

    public String getFinanceSingaporeUrl() {
        return (String) SpUtils.getParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_FINANCE_SINGAPORE_URL, IRequest.getFinancialHost());
    }

    public void setFinanceSingaporeUrl(String str) {
        SpUtils.setParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_FINANCE_SINGAPORE_URL, str);
    }

    public boolean getFinanceIsAllAccount() {
        return ((Boolean) SpUtils.getParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_FINANCE_IS_ALL_ACCOUNT, false)).booleanValue();
    }

    public void setFinanceIsAllAccount(boolean z) {
        SpUtils.setParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_FINANCE_IS_ALL_ACCOUNT, Boolean.valueOf(z));
    }

    public void setHasShowMarketTabNew() {
        SpUtils.setParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_FINANCE_TAB_NEW, 1);
    }

    public int getHasShowMarketTabNew() {
        return ((Integer) SpUtils.getParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_FINANCE_TAB_NEW, 0)).intValue();
    }

    public long getCurrentVersion() {
        return ((Long) SpUtils.getParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_CURRENT_VERSION, 0L)).longValue();
    }

    public void setCurrentVersion(long j) {
        SpUtils.setParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_CURRENT_VERSION, Long.valueOf(j));
    }

    public void setHasShowStakeV2New() {
        SpUtils.setParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_STAKE_V2_NEW, 1);
    }

    public int getHasShowStakeV2New() {
        return ((Integer) SpUtils.getParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_STAKE_V2_NEW, 0)).intValue();
    }

    public void setHasShowSafetyCheckNew() {
        SpUtils.setParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_SAFETY_CHECK_NEW, 1);
    }

    public boolean isNewSafetyCheck() {
        return ((Integer) SpUtils.getParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_SAFETY_CHECK_NEW, 0)).intValue() == 0;
    }

    public void setHasShowStakeHomePop() {
        SpUtils.setParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_STAKE_HOME_POP, 1);
    }

    public int getHasShowStakeHomePop() {
        return ((Integer) SpUtils.getParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_STAKE_HOME_POP, 0)).intValue();
    }

    public int getStakeVersion() {
        String str = this.F_TRON_NEW;
        Context context = AppContextUtil.getContext();
        return ((Integer) SpUtils.getParam(str, context, this.KEY_STAKE_VERSION + getEnvironmentString(), 1)).intValue();
    }

    public void setStakeVersion(int i) {
        String str = this.F_TRON_NEW;
        Context context = AppContextUtil.getContext();
        SpUtils.setParam(str, context, this.KEY_STAKE_VERSION + getEnvironmentString(), Integer.valueOf(i));
    }

    public int getStakeExpireDay() {
        String str = this.F_TRON_NEW;
        Context context = AppContextUtil.getContext();
        return ((Integer) SpUtils.getParam(str, context, this.KEY_STAKE_EXPIRE_DAY + getEnvironmentString(), 3)).intValue();
    }

    public void setStakeExpireDay(int i) {
        String str = this.F_TRON_NEW;
        Context context = AppContextUtil.getContext();
        SpUtils.setParam(str, context, this.KEY_STAKE_EXPIRE_DAY + getEnvironmentString(), Integer.valueOf(i));
    }

    public void setWalletStaked(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(str, this.WALLET_STAKED);
        SpUtils.putHashMapData(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_WALLET_STAKED, hashMap);
    }

    public Map<String, String> getWalletStakedMap() {
        return SpUtils.getHashMapData(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_WALLET_STAKED);
    }

    public void setWalletIntroHasShow() {
        SpUtils.setParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_WALLET_INTRO_HAS_SHOW, 1);
    }

    public boolean getWalletIntroHasShow() {
        return ((Integer) SpUtils.getParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_WALLET_INTRO_HAS_SHOW, 0)).intValue() == 1;
    }

    public boolean getIsNeverShowThirdInputNotice() {
        return ((Boolean) SpUtils.getParam(this.F_TRONKEY, AppContextUtil.getContext(), "is_never_show_third_input_notice", false)).booleanValue();
    }

    public void setIsNeverShowThirdInputNotice(boolean z) {
        SpUtils.setParam(this.F_TRONKEY, AppContextUtil.getContext(), "is_never_show_third_input_notice", Boolean.valueOf(z));
    }

    public void setSecAskDone() {
        SpUtils.setParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_SEC_ASK_DONE, 1);
    }

    public boolean getSecAskDone() {
        return ((Integer) SpUtils.getParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_SEC_ASK_DONE, 0)).intValue() == 1;
    }

    public long getSecAskCloseTime() {
        return ((Long) SpUtils.getParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_SEC_ASK_CLOSE_TIME, 0L)).longValue();
    }

    public void setSecAskCloseTime(long j) {
        SpUtils.setParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_SEC_ASK_CLOSE_TIME, Long.valueOf(j));
    }

    public boolean hasShowTheSafeChannelPop() {
        return ((Boolean) SpUtils.getParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.HAS_SHOW_SAFE_CHANNEL_POP, false)).booleanValue();
    }

    public void setShowTheSafeChannelPop() {
        SpUtils.setParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.HAS_SHOW_SAFE_CHANNEL_POP, true);
    }

    public boolean isCheckedNoMoreShow(String str) {
        return ((Boolean) SpUtils.getParam(this.F_TRON_NEW, AppContextUtil.getContext(), str, false)).booleanValue();
    }

    public void setIsCheckedNoMoreShow(String str, boolean z) {
        SpUtils.setParam(this.F_TRON_NEW, AppContextUtil.getContext(), str, Boolean.valueOf(z));
    }

    public BackupRecordBean getBackupLastOne() {
        String str = (String) SpUtils.getParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.LAST_ONE_BACKUP_RECORD, "");
        try {
            if (StringTronUtil.isNullOrEmpty(str)) {
                return null;
            }
            return (BackupRecordBean) GsonUtils.gsonToBean(str, BackupRecordBean.class);
        } catch (Exception unused) {
            return null;
        }
    }

    public void setBackupLastOne(BackupRecordBean backupRecordBean) {
        try {
            SpUtils.setParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.LAST_ONE_BACKUP_RECORD, GsonUtils.toGsonString(backupRecordBean));
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public int getCreateAccountIndex() {
        return ((Integer) SpUtils.getParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_CREATE_ACCOUNT_INDEX, 0)).intValue();
    }

    public void setCreateAccountIndex(int i) {
        SpUtils.setParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_CREATE_ACCOUNT_INDEX, Integer.valueOf(i));
    }

    public String getLastGenerationAccounName() {
        return (String) SpUtils.getParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_GENERATE_ACCOUNT_NAME, "");
    }

    public void setLastGenerationAccounName(String str) {
        SpUtils.setParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_GENERATE_ACCOUNT_NAME, str);
    }

    public void setShowChangedHdEdit(int i) {
        SpUtils.setParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.HAS_CHANGED_HD, Integer.valueOf(i));
    }

    public int showChangedHdEdit() {
        return ((Integer) SpUtils.getParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.HAS_CHANGED_HD, 1)).intValue();
    }

    public void setHdPopShowed(boolean z) {
        SpUtils.setParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.HAS_HD_POP_SHOWED, Boolean.valueOf(z));
    }

    public boolean hasHdPopShowed() {
        return ((Boolean) SpUtils.getParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.HAS_HD_POP_SHOWED, false)).booleanValue();
    }

    public void setLastVoteApr(String str, String str2) {
        String str3 = this.F_TRON_NEW;
        Context context = AppContextUtil.getContext();
        SpUtils.setParam(str3, context, str + getEnvironmentString(), str2);
    }

    public String getLastVoteApr(String str) {
        String str2 = this.F_TRON_NEW;
        Context context = AppContextUtil.getContext();
        return (String) SpUtils.getParam(str2, context, str + getEnvironmentString(), "");
    }

    public void setMaxDelegateLockPeriod(long j) {
        String str = this.F_TRON_NEW;
        Context context = AppContextUtil.getContext();
        SpUtils.setParam(str, context, this.MAX_DELEGATE_LOCK_PERIOD + getEnvironmentString(), Long.valueOf(j));
    }

    public long getMaxDelegateLockPeriodDays() {
        String str = this.F_TRON_NEW;
        Context context = AppContextUtil.getContext();
        return ((Long) SpUtils.getParam(str, context, this.MAX_DELEGATE_LOCK_PERIOD + getEnvironmentString(), 0L)).longValue();
    }

    public boolean hasShowThirdAddressPopWindow(String str) {
        String host = Uri.parse(DappSearchPresenter.getFixedUrl(str)).getHost();
        String str2 = this.F_TRON_NEW;
        Context context = AppContextUtil.getContext();
        return ((Boolean) SpUtils.getParam(str2, context, this.HAS_SHOW_THIRD_ADDRESS_POP + host, false)).booleanValue();
    }

    public void setShowThirdAddressPopWindow(String str) {
        String host = Uri.parse(DappSearchPresenter.getFixedUrl(str)).getHost();
        String str2 = this.F_TRON_NEW;
        Context context = AppContextUtil.getContext();
        SpUtils.setParam(str2, context, this.HAS_SHOW_THIRD_ADDRESS_POP + host, true);
    }

    public String getIPLocation() {
        return (String) SpUtils.getParam(this.F_TRONIP, AppContextUtil.getContext(), this.LAST_IP_LOCATION, "");
    }

    public void setIPLocation(String str) {
        SpUtils.setParam(this.F_TRONIP, AppContextUtil.getContext(), this.LAST_IP_LOCATION, str);
    }

    public void setShowShieldMainlandNoticeReminder() {
        SpUtils.setParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_SHIELD_MAINLAND_NOTICE_REMINDER, true);
    }

    public boolean getShowShieldMainlandNoticeReminder() {
        return ((Boolean) SpUtils.getParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_SHIELD_MAINLAND_NOTICE_REMINDER, false)).booleanValue();
    }

    public AppStatusOutput getAppStatus() {
        String str = this.F_TRONIP;
        Context context = AppContextUtil.getContext();
        String str2 = (String) SpUtils.getParam(str, context, this.KEY_APP_STATUS + getEnvironmentString(), "");
        AppStatusOutput appStatusOutput = new AppStatusOutput();
        if (StringTronUtil.isEmpty(str2)) {
            return appStatusOutput;
        }
        try {
            AppStatusOutput appStatusOutput2 = (AppStatusOutput) GsonUtils.gsonToBean(str2, AppStatusOutput.class);
            return appStatusOutput2 != null ? appStatusOutput2 : appStatusOutput;
        } catch (Exception e) {
            LogUtils.e(e);
            return appStatusOutput;
        }
    }

    public void setAppStatus(AppStatusOutput appStatusOutput) {
        AppStatusOutput appStatus = getAppStatus();
        if (appStatus != null && appStatus.isHideFinancialTab()) {
            appStatusOutput.setHideFinancialTab(true);
        }
        if (appStatus != null && appStatus.isHideShieldManager()) {
            appStatusOutput.setHideShieldManager(true);
        }
        String gsonString = GsonUtils.toGsonString(appStatusOutput);
        String str = this.F_TRONIP;
        Context context = AppContextUtil.getContext();
        SpUtils.setParam(str, context, this.KEY_APP_STATUS + getEnvironmentString(), gsonString);
    }

    public AppLanguageOutput getOnLineAppLanguage() {
        try {
            AppLanguageOutput appLanguageOutput = (AppLanguageOutput) GsonUtils.gsonToBean((String) SpUtils.getParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_APP_Language, ""), AppLanguageOutput.class);
            if (appLanguageOutput != null) {
                return appLanguageOutput;
            }
            return null;
        } catch (Exception e) {
            LogUtils.e(e);
            return null;
        }
    }

    public void setOnLineAppLanguage(AppLanguageOutput appLanguageOutput) {
        if (IRequest.currentNetCanUpdateLanguage()) {
            SpUtils.setParam(this.F_TRON_NEW, AppContextUtil.getContext(), this.KEY_APP_Language, GsonUtils.toGsonString(appLanguageOutput));
        }
    }

    public long getLastMSGid() {
        return ((Long) SpUtils.getParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_LAST_MSG_ID), 0L)).longValue();
    }

    public void setLastMSGid(long j) {
        SpUtils.setParam(this.F_TRONIP, AppContextUtil.getContext(), AppContextUtil.getContext().getString(this.ID_LAST_MSG_ID), Long.valueOf(j));
    }

    public void setPushMessageData(String str) {
        String str2 = this.F_TRON_NEW;
        Context context = AppContextUtil.getContext();
        SpUtils.setParam(str2, context, this.KEY_PUSH_MSG + "_" + IRequest.ENVIRONMENT.name(), str);
    }

    public String getPushMessageData() {
        String str = this.F_TRON_NEW;
        Context context = AppContextUtil.getContext();
        return (String) SpUtils.getParam(str, context, this.KEY_PUSH_MSG + "_" + IRequest.ENVIRONMENT.name(), "");
    }
}
