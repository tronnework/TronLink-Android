package com.tron.wallet.common.config;

import com.google.android.gms.common.ConnectionResult;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.wallet.common.bean.update.UpdateOutput;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.tron.protos.contract.AssetIssueContractOuterClass;
public class TronConfig {
    public static final int ACCOUNT_FROM_IMPORT_HD = 2;
    public static final int ACCOUNT_FROM_WALLET_MANAGER = 1;
    public static final long ACCOUNT_UPDATE_BACKGROUND_INTERVAL = 60000;
    public static final long ACCOUNT_UPDATE_FOREGROUND_INTERVAL = 30000;
    public static final String ADDRESS = "address";
    public static final int ADDRESSBOOK_ADD = 2025;
    public static final String ADDRESS_BOOK_SELECT = "address_book_select";
    public static final int ADDRESS_BOOK_SELECT_CODE = 2027;
    public static final int APPROVALSPROPOSAL_TYPE = 12;
    public static final String APPROVED_SORT_TYPE = "approved_sort_type";
    public static final int BANDWIDTH_COST = 70;
    public static final String BTT_NAME = "BTT";
    public static final int CANCEL_APPROVE_TYPE = 39;
    public static final int CANCEL_UNSTAKE_TYPE = 37;
    public static final String CANCLE_KEY = "cancle";
    public static final int CHANGE_ADDRESS_CODE = 3001;
    public static final String COLD_W = "COLD_W";
    public static final String COLD_WALLET_VERIFY_CONTRACT = "ColdWalletOwnerVerifyContract";
    public static final String CONTRACT_ADDRESS = "contract_address";
    public static final int CREATE_WALLET = 1;
    public static final int CUSTOMCONTRACT_TYPE = 99;
    public static final int DAPP_MESSAGE_TYPE = 16;
    public static final int DAPP_SHIELD_PARAMS_TYPE = 15;
    public static final int DAPP_STRUCTURED_DATA_MESSAGE_TYPE = 17;
    public static final int DAPP_TYPE = 14;
    public static final String DApp_CHAIN_NAME = "DAppChain";
    public static final long DEFAULT_MAX_DELEGATE_LOCK_PERIOD_NILE = 5;
    public static final long DEFAULT_MAX_DELEGATE_LOCK_PERIOD_RELEASE = 30;
    public static final long DEFAULT_MAX_DELEGATE_LOCK_PERIOD_SHASTA = 3;
    public static final int DELEGATE_TYPE = 35;
    public static final int DEPOSIT_TYPE = 8;
    public static final String DOC_1 = "DOC_1";
    public static final String DOC_2 = "DOC_2";
    public static final String DOC_Title = "DOC_Title";
    public static final String EMPTY_CREATE_WALLET = "empty_to_create_wallet";
    public static final int FREEZE_TYPE = 4;
    public static final String FROM_W = "FROM_W";
    public static final String HD_PATH_DEFAULT = "m/44'/195'/0'/0/0";
    public static String HTML_ANNOUNCEMENT_EN = null;
    public static String HTML_ANNOUNCEMENT_ZH = null;
    public static String HTML_APPROVE_CHECKDETAIL_EN = null;
    public static String HTML_APPROVE_CHECKDETAIL_ZH = null;
    private static String HTML_COMMITTEE_URL = null;
    public static String HTML_Dapp = null;
    public static String HTML_GUIDE_en = null;
    public static String HTML_GUIDE_zh = null;
    public static String HTML_HELP_en = null;
    public static String HTML_HELP_en_new = null;
    public static String HTML_HELP_zh = null;
    public static String HTML_HELP_zh_new = null;
    public static final String HTML_RatingRoles_MAINNET = "https://tronscan.org/#/tokens/rating-rule";
    public static final String HTML_RatingRoles_NILE = "https://nile.tronscan.org/?_ga=2.10910324.1350770308.1687174972-738033416.1679384183#/tokens/rating-rule";
    public static final String HTML_RatingRoles_SHASTA = "https://shasta.tronscan.org/?_ga=2.9965941.1350770308.1687174972-738033416.1679384183#/tokens/rating-rule (edited) ";
    public static String HTML_SHARE = null;
    public static final String HTML_SignEN = "https://dapp.tron.network/views/signEN.html";
    public static final String HTML_SignZH = "https://dapp.tron.network/views/signZH.html";
    public static String HTML_TOKEN_CHECK_URL_EN = null;
    public static String HTML_TOKEN_CHECK_URL_ZH = null;
    public static String HTML_TO_CUSTOM_TOKEN_EN = null;
    public static String HTML_TO_CUSTOM_TOKEN_ZH = null;
    public static String HTML_TO_ENERGY_PENALTY_DETAIL = null;
    public static String HTML_TO_ENERGY_PENALTY_DETAIL_ZH = null;
    public static String HTML_TRANSCAN_TOKEN_REPORT_URL = null;
    public static String HTML_USDT_en = null;
    public static String HTML_USDT_zh = null;
    public static String HTML_Version = null;
    public static final String HTML_howGetUsdt_EN = "https://support.tronlink.org/hc/en-us/articles/360034148772-How-to-get-TRC20-USDT-";
    public static final String HTML_howGetUsdt_ZH = "https://support.tronlink.org/hc/zh-cn/articles/360034519851-%E5%A6%82%E4%BD%95%E8%8E%B7%E5%8F%96TRC20-USDT";
    public static final String HTML_howGetWTRX_EN = "https://justswap.network/?lang=en-US#/home";
    public static final String HTML_howGetWTRX_ZH = "https://justswap.network/?lang=zh-CN#/home";
    public static String HTML_how_use_ledger_EN = null;
    public static String HTML_how_use_ledger_ZH = null;
    public static final String HTML_mutli_sign_EN = "https://support.tronlink.org/hc/en-us/articles/4843619210777";
    public static final String HTML_mutli_sign_ZH = "https://support.tronlink.org/hc/zh-cn/articles/4843619210777";
    public static final String HTML_protocolEN = "https://dapp.tron.network/views/protocolEN.html";
    public static final String HTML_protocolZH = "https://dapp.tron.network/views/protocolZH.html";
    public static final String HTML_zendesk_EN = "https://support.tronlink.org/hc/en-us/articles/4580270363929--Notes-in-Shielded-Transaction-";
    public static final String HTML_zendesk_ZH = "https://support.tronlink.org/hc/zh-cn/articles/4580270363929";
    public static final String ID = "ID";
    public static final String IMPORT_CONTENT = "IMPORT_CONTENT";
    public static final String IMPORT_MNEMONIC_PATH = "IMPORT_MNEMONIC_PATH";
    public static final String IMPORT_NAME = "IMPORT_NAME";
    public static final String IMPORT_PASSWORD = "IMPORT_PASSWORD";
    public static final int IMPORT_WALLET = 2;
    public static final String INNER_TITLE = "INNER_TITLE";
    public static final String IS_FROM_DETAIL = "is_from_detail";
    public static final String MAIN_CHAIN_NAME = "MainChain";
    public static final String MAIN_NET_TIP_712_CHAIN_ID = "728126428";
    public static final int MAKEPROPOSAL_TYPE = 11;
    public static final int MESSAGE_TYPE = 98;
    public static final String MODIFY_INDEX = "MODIFY_INDEX";
    public static final int MULTISIGN_PERMISSION_UPDATE_TYPE = 16;
    public static final String MULTI_CHOOSE_OWNER_ADDRESS = "multi_choose_owner_address";
    public static final int MULTI_SIGNATURE_COST = 200;
    public static final int NFT_TYPE = 21;
    public static final String NILE_TEST_NET_TIP_712_CHAIN_ID = "3448148188";
    public static final int NOTIFICATIONS_ALL = 1;
    public static final int NOTIFICATIONS_ER = 3;
    public static final int NOTIFICATIONS_OTHER = 4;
    public static final int NOTIFICATIONS_TRANSFER = 2;
    public static final String NOTIFICATION_IN = "is_notification_in";
    public static final String OB_W = "OB_W";
    public static final int PARTICIPATEMULTISIGN_TYPE = 13;
    public static final String PERFORM = "Android&&&";
    public static final String PK_ADDRESS = "shield_address";
    public static final String PK_AK = "shield_ak";
    public static final String PK_ASK = "shield_ask";
    public static final String PK_IVK = "shield_ivk";
    public static final String PK_NK = "shield_nk";
    public static final String PK_NSK = "shield_nsk";
    public static final String PK_OVK = "shield_ovk";
    public static final String PK_PUB_KEY = "shield_pub_key";
    public static final String PK_SK = "shield_sk";
    public static final String PRECISION = "precision";
    public static final long PRICE_UPDATE_INTERVAL = 15000;
    public static final String PageFrom = "PageFrom";
    public static final String PriceUsdOrCny = "PriceUsdOrCny";
    public static final String QR_CODE_DATA = "qr_code_data";
    public static final String RECEIVE_ADDRESS = "receive_address";
    public static int RESOURCE_BANDWIDTH = 0;
    public static int RESOURCE_ENERGY = 0;
    public static final int SAVE_ADDRESS_SUCCES = 2026;
    public static String SHARE_URL = null;
    public static final String SHIELD_ADDRESS = "shield_address";
    public static final String SHIELD_CONTRACT_ADDRESS = "shield_contract_address";
    public static final String SHIELD_DECIMALS = "shield_decimals";
    public static final String SHIELD_IS_TRC20 = "shield_is_trc20";
    public static final String SHIELD_TRC20_ADDRESS = "shield_trc20_address";
    public static final int SHIELD_TRC20_SIGN_REQUEST_CODE = 2020;
    public static final int SIGNATURE_COST = 65;
    public static final int SIGN_ALREADY = 2;
    public static final int SIGN_FAILURE = 4;
    public static final int SIGN_MESSAGE_V2_BYTES_ARRAY_TYPE = 104;
    public static final int SIGN_MESSAGE_V2_HEX_STR_TYPE = 103;
    public static final int SIGN_MESSAGE_V2_STR_TYPE = 102;
    public static final int SIGN_SUCCESS = 3;
    public static final int SIGN_WAIT = 1;
    public static final int STRUCTURED_DATA_MESSAGE_TYPE = 101;
    public static final int SWAP_APPROVE_TYPE = 38;
    public static final int SWAP_TYPE = 33;
    public static final String StatAction_Key = "StatAction_Key";
    public static final long TIME_UPDATE_INTERVAL = 1000;
    public static final String TITLE = "TITLE";
    public static final int TOCHANGENAME = 2020;
    public static final int TOCHANGEPASSWORD = 2021;
    public static final int TODAPPCONFIRM = 2022;
    public static final int TODAPPLOCALCONFIRM = 2024;
    public static final int TODAPPSEARCH = 2023;
    public static final int TODAPPZCONFIRM = 2025;
    public static final String TOKEN_DATA = "token_data";
    public static final int TOSCANNER = 2018;
    public static final int TOSELECTRELATION = 2030;
    public static final String TRANSACTION_DATA2_EXTRA = "transaction_data2_extra";
    public static final String TRANSACTION_DATA_EXTRA = "transaction_data_extra";
    public static final String TRANSACTION_DATA_EXTRA2 = "transaction_data_extra2";
    public static final String TRANSACTION_DATA_EXTRA_LIST = "transaction_data_extra_list";
    public static final String TRANSACTION_SIGNED_EXTRA = "transaction_data_extra";
    public static final int TRANSACTION_SIGN_REQUEST_CODE = 2019;
    public static final String TRANSACTION_VOTE_LIST = "transaction_vote_list";
    public static final String TRANSACTION_VOTE_LIST2 = "transaction_vote_list2";
    public static final int TRANSFER_MY_TYPE = 7;
    public static final int TRANSFER_OTHER_TYPE = 6;
    public static final int TRANSFER_RECEIVE_ADDRESS_BOOK = 2029;
    public static final int TRANSFER_TRANSFER_ADDRESS_BOOK = 2028;
    public static final int TRANSFER_TYPE = 1;
    public static final String TRC = "TRC";
    public static final String TRC10 = "TRC10";
    public static final String TRC20 = "TRC20";
    public static boolean TRC20Hide = false;
    public static final String TRONLENDING = "http://m.tronlending.org/tronLending";
    public static final String TRONSCANHOST_DAPPCHAIN;
    public static final String TRONSCANHOST_MAINCHAIN;
    public static final String TRX_DOC_0 = "TRX_DOC_0";
    public static final String TRX_DOC_1 = "TRX_DOC_1";
    public static final String TRX_DOC_2 = "TRX_DOC_2";
    public static final String TRX_DOC_3 = "TRX_DOC_3";
    public static final String TRX_DOC_4 = "TRX_DOC_4";
    public static final String TRX_DOC_5 = "TRX_DOC_5";
    public static final String TRX_DOC_6 = "TRX_DOC_6";
    public static final String TRX_DOC_SPECIAL = "TRX_DOC_SPECIAL";
    public static final int UNDELEGATE_TYPE = 36;
    public static final String UNFREEZE = "UnFreeze";
    public static final int UNFREEZE_TYPE = 2;
    public static final int UPDATE_TYPE = 5;
    public static final String URL_ZENDESK_CN = "https://support.tronlink.org/hc/zh-cn/articles/4580282574745";
    public static final String URL_ZENDESK_EN = "https://support.tronlink.org/hc/en-us/articles/4580282574745--About-Shielded-Transaction-";
    public static final int VOTE_CANCEL_TYPE = 22;
    public static final int VOTE_TYPE = 3;
    public static final int VOTE_WITHDRAW_TYPE = 9;
    public static final String WALLET_DATA = "wallet_data";
    public static final String WALLET_DATA2 = "wallet_data2";
    public static final String WALLET_NAME = "wallet_name";
    public static final String WALLET_PASSWORD = "wallet_password";
    public static final String WALLET_SELECT = "wallet_select";
    public static final String WALLET_TYPE = "wallet_type";
    public static final String WALLET_extra = "wallet_extra";
    public static final int WATCH_UPDATE_COLD_REQUEST_CODE = 2031;
    public static final int WITHDRAW_TYPE = 34;
    public static String address = null;
    public static Map<String, AssetIssueContractOuterClass.AssetIssueContract> assetIssueMap = null;
    public static double balance10_TRX = 0.0d;
    public static double balance20 = 0.0d;
    public static int bandwidthForFree = 0;
    public static String cerSha1 = null;
    public static int currentPwdType = 0;
    public static final String dapp_hot_bean = "dapp_hot_bean";
    public static final String deal_wallet_index = "deal_wallet_index";
    public static final String deal_wallet_name = "deal_wallet_name";
    public static double feeBandWidth = 0.0d;
    public static double feeEnergy = 0.0d;
    public static final long feeLimit = 225000000;
    public static final long feeLimit_swap_v3 = 500000000;
    public static String filterSmallValue = null;
    public static boolean hasNet = false;
    public static final String isTRX = "isTRX";
    public static int languageLocalConfig = 0;
    public static List<String> openList = null;
    public static String receiveNoticeData = null;
    public static final String socket_state = "socket_state";
    public static final String type_create = "type_create";
    public static final String type_main = "type_main";
    public static final int type_pwd_back_keystore = 2;
    public static final int type_pwd_back_mnemonic = 4;
    public static final int type_pwd_back_privatekey = 3;
    public static final int type_pwd_change = 10;
    public static final int type_pwd_contract = 7;
    public static final int type_pwd_dapp = 8;
    public static final int type_pwd_delete_wallet = 1;
    public static final int type_pwd_freeze = 5;
    public static final int type_pwd_transfer = 11;
    public static final int type_pwd_unfreeze = 6;
    public static final int type_pwd_vote = 9;
    public static final String type_receivables = "type_receivables";
    public static final String type_select = "type_select";
    public static final int type_unfreeze_bandwidth = 1;
    public static final int type_unfreeze_energy = 2;
    public static final String type_walletmanage = "type_walletmanage";
    public static UpdateOutput updateOutput;
    public static String walletName;

    public interface DAppClassType {
        public static final int ALL = 0;
        public static final int DEFI = 4;
        public static final int GAME = 3;
        public static final int OTHER = 7;
    }

    public interface DAppQuickType {
        public static final int BOOK = 1;
        public static final int Common = 0;
    }

    static {
        address = WalletUtils.getSelectedWallet() == null ? "" : WalletUtils.getSelectedWallet().getAddress();
        TRC20Hide = false;
        feeBandWidth = 0.001d;
        feeEnergy = 2.1E-4d;
        filterSmallValue = "0.5";
        bandwidthForFree = ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED;
        languageLocalConfig = 0;
        TRONSCANHOST_MAINCHAIN = IRequest.getTronscanMainUrl();
        TRONSCANHOST_DAPPCHAIN = IRequest.getTronscanDappUrl();
        hasNet = true;
        HTML_Dapp = IRequest.getDappUrl();
        HTML_Version = "https://dapp.tronlink.org/#/versionLog";
        HTML_HELP_en = "https://support.tronlink.org/hc/en-us";
        HTML_HELP_en_new = "https://support.tronlink.org/hc/en-us/requests/new";
        HTML_HELP_zh = "https://support.tronlink.org/hc/zh-cn";
        HTML_HELP_zh_new = "https://support.tronlink.org/hc/zh-cn/requests/new";
        HTML_ANNOUNCEMENT_ZH = "https://support.tronlink.org/hc/zh-cn/categories/360001570571-%E5%85%AC%E5%91%8A%E4%B8%AD%E5%BF%83";
        HTML_ANNOUNCEMENT_EN = "https://support.tronlink.org/hc/en-us/categories/360001579171-Announcement";
        HTML_GUIDE_en = "https://support.tronlink.org/hc/en-us/categories/4579643636889";
        HTML_GUIDE_zh = "https://support.tronlink.org/hc/zh-cn/categories/4579643636889";
        HTML_SHARE = "https://dapp.tronlink.org/#/actinvite";
        HTML_USDT_zh = HTML_howGetUsdt_ZH;
        HTML_USDT_en = HTML_howGetUsdt_EN;
        HTML_how_use_ledger_ZH = "https://support.tronlink.org/hc/zh-cn/articles/4580268628377";
        HTML_how_use_ledger_EN = "https://support.tronlink.org/hc/en-us/articles/4580268628377";
        HTML_TO_CUSTOM_TOKEN_ZH = "https://support.tronlink.org/hc/zh-cn/articles/4580250299289--自定义通证功能介绍-";
        HTML_TO_CUSTOM_TOKEN_EN = "https://support.tronlink.org/hc/en-us/articles/4580250299289--Introducing-Custom-Tokens-";
        HTML_COMMITTEE_URL = "https://tronscan.org/#/sr/committee";
        HTML_TRANSCAN_TOKEN_REPORT_URL = "https://tronscan.org/#/tools/contactUs?feedbackType=risk&source=token";
        SHARE_URL = "https://dapp.tronlink.org/#/sharedapp";
        HTML_TO_ENERGY_PENALTY_DETAIL = "https://tronlinkorg.zendesk.com/hc/en-us/articles/14496201625113";
        HTML_TO_ENERGY_PENALTY_DETAIL_ZH = "https://tronlinkorg.zendesk.com/hc/zh-cn/articles/14496201625113";
        HTML_APPROVE_CHECKDETAIL_EN = "https://support.tronlink.org/hc/en-us/articles/35699926103833-%E5%A6%82%E4%BD%95%E5%AF%B9%E6%82%A8%E7%9A%84%E9%92%B1%E5%8C%85%E8%BF%9B%E8%A1%8C%E5%AE%89%E5%85%A8%E6%A3%80%E6%B5%8B";
        HTML_APPROVE_CHECKDETAIL_ZH = "https://support.tronlink.org/hc/zh-cn/articles/35699926103833-%E5%A6%82%E4%BD%95%E5%AF%B9%E6%82%A8%E7%9A%84%E9%92%B1%E5%8C%85%E8%BF%9B%E8%A1%8C%E5%AE%89%E5%85%A8%E6%A3%80%E6%B5%8B";
        HTML_TOKEN_CHECK_URL_EN = "https://support.tronlink.org/hc/en-us/articles/35699926103833-%E5%A6%82%E4%BD%95%E5%AF%B9%E6%82%A8%E7%9A%84%E9%92%B1%E5%8C%85%E8%BF%9B%E8%A1%8C%E5%AE%89%E5%85%A8%E6%A3%80%E6%B5%8B";
        HTML_TOKEN_CHECK_URL_ZH = "https://support.tronlink.org/hc/zh-cn/articles/35699926103833-%E5%A6%82%E4%BD%95%E5%AF%B9%E6%82%A8%E7%9A%84%E9%92%B1%E5%8C%85%E8%BF%9B%E8%A1%8C%E5%AE%89%E5%85%A8%E6%A3%80%E6%B5%8B";
        assetIssueMap = new HashMap();
        receiveNoticeData = "";
        cerSha1 = "D2:FE:27:94:84:CD:77:5D:CA:7A:71:BD:60:64:30:FE:76:2C:A5:9D";
        currentPwdType = 0;
        openList = new ArrayList();
        RESOURCE_ENERGY = 0;
        RESOURCE_BANDWIDTH = 1;
    }

    public static String getCommitUrl() {
        String str;
        String sytemTronscanUrl = SpAPI.THIS.getSytemTronscanUrl();
        String useLanguage = SpAPI.THIS.useLanguage();
        if (sytemTronscanUrl.length() > 0) {
            str = sytemTronscanUrl + "/#/sr/committee";
        } else {
            str = HTML_COMMITTEE_URL;
        }
        if ("2".equals(useLanguage)) {
            return str + "?lang=zh";
        }
        return str + "?lang=en";
    }
}
