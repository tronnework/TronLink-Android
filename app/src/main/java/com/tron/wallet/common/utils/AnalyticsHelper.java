package com.tron.wallet.common.utils;

import android.content.Context;
import android.os.Bundle;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.AssetsConfig;
import com.tron.wallet.business.stakev2.ResState;
import com.tron.wallet.common.config.FeeReporting;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
public class AnalyticsHelper {

    public static class About {
        public static final String CLICK_ABOUT_CLICK_BACK = "about_1";
        public static final String CLICK_ABOUT_NEW_VERSION_DISCOVERED = "about_2";
        public static final String CLICK_ABOUT_NEW_VERSION_UPDATE = "about_3";
    }

    public static class AddWalletPage {
        public static final String CLICK_ADD_WALLET_PAGE_COLD_PAIR = "addWalletPage_8";
        public static final String CLICK_ADD_WALLET_PAGE_CREATE = "addWalletPage_6";
        public static final String CLICK_ADD_WALLET_PAGE_GENERATE = "addWalletPage_2";
        public static final String CLICK_ADD_WALLET_PAGE_IMPORT = "addWalletPage_3";
        public static final String CLICK_ADD_WALLET_PAGE_IMPORT_KEYSTORE = "addWalletPage_4";
        public static final String CLICK_ADD_WALLET_PAGE_IMPORT_MNE = "addWalletPage_3";
        public static final String CLICK_ADD_WALLET_PAGE_IMPORT_MNE_HD = "addWalletPage_2";
        public static final String CLICK_ADD_WALLET_PAGE_IMPORT_PRI_KEY = "addWalletPage_1";
        public static final String CLICK_ADD_WALLET_PAGE_IMPORT_WATCH = "addWalletPage_5";
        public static final String CLICK_ADD_WALLET_PAGE_LEDGER = "addWalletPage_7";
        public static final String CLICK_ADD_WALLET_PAGE_SAMSUNG = "addWalletPage_6";
        public static final String CLICK_ADD_WALLET_PAGE_WATCH = "addWalletPage_4";
        public static final String ENTER_ADD_WALLET_PAGE = "addWalletPage";
    }

    public static class AdvancedFeatures {
        public static final String CLICK_ADVANCED_FEATURES_CLICK_BACK = "AdvancedFeatures_1";
        public static final String CLICK_ADVANCED_FEATURES_COMMITTEE_PROPOSAL = "AdvancedFeatures_2";
        public static final String CLICK_ADVANCED_FEATURES_DAPP_TESTING_TOOL = "AdvancedFeatures_3";
        public static final String CLICK_ADVANCED_FEATURES_EXPORT_WALLET = "AdvancedFeatures_5";
        public static final String CLICK_ADVANCED_FEATURES_MNEMONIC_CONVERSION_TOOL = "AdvancedFeatures_4";
    }

    public static class BackHistoryPage {
        public static final String HOMEPAGE_BACKUP_WALLET = "homepage_backup_wallet";
        public static final String HOMEPAGE_BACKUP_WALLET_CLOSE = "homepage_backup_wallet_1";
        public static final String HOMEPAGE_BACKUP_WALLET_OPEN = "homepage_backup_wallet_2";
        public static final String MY_WALLET_BACKUP_HISTORY = "mywallet_backup_history";
        public static final String WALLET_DETAILS_BACKUP_HISTORY = "wallet_details_backup_history";
    }

    public static class BrowserPage {
        public static final String CLICK_BROWSER_PAGE_BACK_BTN = "browser_1";
        public static final String CLICK_BROWSER_PAGE_CLOSE_BTN = "browser_2";
        public static final String CLICK_BROWSER_PAGE_FRESH = "browser_4";
        public static final String CLICK_BROWSER_PAGE_MORE_ACTION = "browser_5";
        public static final String CLICK_BROWSER_PAGE_MORE_ACTION_BROWSER = "browser_52";
        public static final String CLICK_BROWSER_PAGE_MORE_ACTION_CANCEL = "browser_53";
        public static final String CLICK_BROWSER_PAGE_MORE_ACTION_COPY_LINK = "browser_51";
        public static final String CLICK_BROWSER_PAGE_SLIDE_BACK = "browser_3";
        public static final String ENTER_BROWSER_PAGE = "browser";
    }

    public static class BrowserVisit {
        public static final String CLICK_BROWSER_BACK = "browservisit_click_1";
        public static final String CLICK_BROWSER_BANNER = "browserbanner_click";
        public static final String CLICK_BROWSER_HOME = "browservisit_click_2";
        public static final String ENTER_BROWSER_BANNER_SHOW = "browserbanner_show";
        public static final String ENTER_BROWSER_VISIT = "browservisit_show";
        public static final String ENTER_DAPP_HOME = "browserindex_show";
        public static final String KEY_CLICK_BANNER_NAME = "banner_name";
        public static final String KEY_DAPP_NAME = "title";
        public static final String KEY_DAPP_URL = "url";
    }

    public static class ChangeNetwork {
        public static final String CLICK_CHANGE_NETWORK_CLICK_BACK = "ChangeNetwork_1";
        public static final String CLICK_CHANGE_NETWORK_CLICK_ON_DAPP_CHAIN_MAINNET = "ChangeNetwork_4";
        public static final String CLICK_CHANGE_NETWORK_CLICK_ON_THE_SHASTA_TESTNET = "ChangeNetwork_3";
        public static final String CLICK_CHANGE_NETWORK_CLICK_ON_TRON_MAINNET = "ChangeNetwork_2";
    }

    public static class ChangeNetwork_d {
        public static final String CLICK_CHANGE_NETWORK_D_CLICK_CANCEL = "ChangeNetwork_d_1";
        public static final String CLICK_CHANGE_NETWORK_D_CLICK_TO_CONFIRM_THE_SWITCH = "ChangeNetwork_d_2";
    }

    public static class ChangeNetwork_s {
        public static final String CLICK_CHANGE_NETWORK_S_CLICK_CANCEL = "ChangeNetwork_s_1";
        public static final String CLICK_CHANGE_NETWORK_S_CLICK_TO_CONFIRM_THE_SWITCH = "ChangeNetwork_s_2";
    }

    public static class ChangeNode {
        public static final String CLICK_CHANGE_NODE_ADD_CUSTOM_NODE = "ChangeNode_2";
        public static final String CLICK_CHANGE_NODE_CLICK_BACK = "ChangeNode_1";
    }

    public static class ChangeServer {
        public static final String CLICK_CHANGE_SERVER_CLICK_BACK = "ChangeServer_1";
    }

    public static class ChoosePathPage {
        public static final String CLICK_CHOOSE_ADDRESS_PAGE_CLOSE = "Choose_other_addr_2";
        public static final String CLICK_CHOOSE_ADDRESS_PAGE_CONFIRM = "Choose_other_addr_3";
        public static final String CLICK_CHOOSE_ADDRESS_PAGE_SELECT = "Choose_other_addr_4";
        public static final String CLICK_CHOOSE_ADDRESS_PAGE_SET_CUSTOM_PATH = "Choose_other_addr_1";
        public static final String CLICK_CUSTOM_PATH_PAGE_CONFIRM = "Custom_Path_1";
        public static final String ENTER_CHOOSE_ADDRESS_PAGE = "Choose_other_addr";
        public static final String ENTER_CUSTOM_PATH_PAGE = "Custom_Path";
    }

    public static class ConfirmResult {
        public static final String CLICK_STAKE_RESULT_FAILED_0 = "stakeResultFailed_0";
        public static final String CLICK_STAKE_RESULT_FAILED_1 = "stakeResultFailed_1";
        public static final String CLICK_STAKE_RESULT_SUCCEEDED_0 = "stakeResultSucceeded_0";
        public static final String CLICK_STAKE_RESULT_SUCCEEDED_1 = "stakeResultSucceeded_1";
        public static final String CLICK_STAKE_RESULT_SUCCEEDED_CHOOSE_OTHER_SRs = "vote_guide_click_1";
        public static final String CLICK_STAKE_RESULT_SUCCEEDED_GIVE_UP_PROFIT = "vote_guide_click_3";
        public static final String CLICK_STAKE_RESULT_SUCCEEDED_VOTE_SUCCESS = "vote_success_click_1";
        public static final String CLICK_STAKE_RESULT_SUCCEEDED_VOTE_SUCCESS_PAGE = "vote_success_show";
        public static final String CLICK_STAKE_RESULT_SUCCEEDED_VOTE_TO_PROFIT = "vote_guide_click_2";
        public static final String CLICK_TRANSFER_RESULT_FAILED_0 = "transferResultFailed_0";
        public static final String CLICK_TRANSFER_RESULT_FAILED_1 = "transferResultFailed_1";
        public static final String CLICK_TRANSFER_RESULT_SUCCEEDED_0 = "transferResultSucceeded_0";
        public static final String CLICK_TRANSFER_RESULT_SUCCEEDED_1 = "transferResultSucceeded_1";
        public static final String CLICK_UNSTAKE_RESULT_FAILED = "unStakeResultFailed";
        public static final String CLICK_UNSTAKE_RESULT_FAILED_0 = "unStakeResultFailed_0";
        public static final String CLICK_UNSTAKE_RESULT_FAILED_1 = "unStakeResultFailed_1";
        public static final String CLICK_UNSTAKE_RESULT_FAILED_PART = "unStakeResultFailed_part";
        public static final String CLICK_UNSTAKE_RESULT_SUCCEEDED_0 = "unStakeResultSucceeded_0";
        public static final String CLICK_UNSTAKE_RESULT_SUCCEEDED_1 = "unStakeResultSucceeded_1";
        public static final String CLICK_VOTE_INCOME_RESULT_SUCCEEDED_0 = "voteInComeResultSucceeded_0";
        public static final String CLICK_VOTE_INCOME_RESULT_SUCCEEDED_1 = "voteInComeResultSucceeded_1";
        public static final String CLICK_VOTE_RESULT_FAILED_0 = "voteResultFailed_0";
        public static final String CLICK_VOTE_RESULT_FAILED_1 = "voteResultFailed_1";
        public static final String ENTER_STAKE_RESULT_FAILED = "stakeResultFailed";
        public static final String ENTER_STAKE_RESULT_SUCCEEDED = "stakeResultSucceeded";
        public static final String ENTER_TRANSFER_RESULT_FAILED = "transferResultFailed";
        public static final String ENTER_TRANSFER_RESULT_SUCCEEDED = "transferResultSucceeded";
    }

    public static class ConfirmTransactionPage {
        public static final String ENTER_CONFIRM_PAGE = "TransactionPop_show";
    }

    public static class CreateAccountPage {
        public static final String CLICK_CREATE_ACCOUNT_PAGE_CHANGE_ADDRESS = "CreateLinked_More_2";
        public static final String CLICK_CREATE_ACCOUNT_PAGE_MORE = "CreateLinked_More_1";
        public static final String CLICK_CREATE_ACCOUNT_PAGE_SWITCH_HD_WALLET = "CreateLinked_More_3";
        public static final String CLICK_CREATE_ACCOUNT_PAGE_SWITCH_HD_WALLET_CLOSE = "Switch_HD_Wallet_2";
        public static final String CLICK_CREATE_ACCOUNT_PAGE_SWITCH_HD_WALLET_CONFIRM = "Switch_HD_Wallet_1";
        public static final String ENTER_CREATE_ACCOUNT_PAGE = "CreateLinked";
        public static final String ENTER_CREATE_ACCOUNT_PAGE_SWITCH_HD_WALLET = "Switch_HD_Wallet";
    }

    public static class CustomTokenPage {
        public static final String CLICK_CUSTOM_TOKEN_CONFIRM_PAGE_BACK = "CustomTokenPage_11";
        public static final String CLICK_CUSTOM_TOKEN_CONFIRM_PAGE_CONFIRM = "CustomTokenPage_12";
        public static final String CLICK_CUSTOM_TOKEN_PAGE_DETAILS = "CustomTokenPage_1";
        public static final String CLICK_CUSTOM_TOKEN_PAGE_EDIT_CONTRACT_ADDRESS = "CustomTokenPage_3";
        public static final String CLICK_CUSTOM_TOKEN_PAGE_EDIT_NAME = "CustomTokenPage_5";
        public static final String CLICK_CUSTOM_TOKEN_PAGE_EDIT_SYMBOL = "CustomTokenPage_4";
        public static final String CLICK_CUSTOM_TOKEN_PAGE_NEXT = "CustomTokenPage_6";
        public static final String CLICK_CUSTOM_TOKEN_PAGE_SCAN = "CustomTokenPage_2";
        public static final String CLICK_CUSTOM_TOKEN_UPDATE_CANCEL = "CustomTokenUpdate_3";
        public static final String CLICK_CUSTOM_TOKEN_UPDATE_CONFIRM = "CustomTokenUpdate_2";
        public static final String CLICK_CUSTOM_TOKEN_UPDATE_TIP = "CustomTokenUpdate_1";
        public static final String CLICK_MY_ASSETS_CUSTOM_TOKEN = "CustomTokenGate_2";
        public static final String CLICK_SEARCH_ASSETS_CUSTOM_TOKEN = "CustomTokenGate_1";
    }

    public static class DAppBookMarkEvent {
        public static final String CLICK_CLOSE_BACK = "DappBookMark_2_10";
        public static final String CLICK_FAV = "DappBookMark_2_9";
        public static final String CLICK_LEFT_SWIPE = "DappBookMark_2_5";
        public static final String CLICK_LEFT_SWIPE_DELTE = "DappBookMark_2_6";
        public static final String CLICK_LONG_CLICK = "DappBookMark_2_7";
        public static final String CLICK_LONG_CLICK_DELTEE = "DappBookMark_2_8";
        public static final String CLICK_MOVE = "DappBookMark_2_4";
        public static final String DAPP_HISTORY_TAG = "DappBookMark_2_";
    }

    public static class DAppConnectPage {
        public static final int BLACK_URL = 2;
        public static final String CLICK_CONNECT_CONFIRM_PAGE_ALLOW = "DAppConnectPop_%d_3";
        public static final String CLICK_CONNECT_CONFIRM_PAGE_CLOSE_BTN = "DAppConnectPop_%d_1";
        public static final String CLICK_CONNECT_CONFIRM_PAGE_DISALLOW = "DAppConnectPop_%d_2";
        public static final String CLICK_CONNECT_CONFIRM_PAGE_ONLY_ONCE_ALLOW = "DAppConnectPop_%d_5";
        public static final String CLICK_CONNECT_CONFIRM_PAGE_ONLY_ONCE_ALLOW_IN_POP = "DAppConnectPop_%d_6";
        public static final String CLICK_CONNECT_CONFIRM_PAGE_ONLY_ONCE_CANCEL_IN_POP = "DAppConnectPop_%d_7";
        public static final String CLICK_CONNECT_CONFIRM_PAGE_TIPS = "DAppConnectPop_%d_4";
        public static final String CLICK_CONNECT_MANAGER_PAGE = "DAppConnectManage_1";
        public static final String CLICK_CONNECT_MANAGER_PAGE_ALL = "DAppConnectManage_2";
        public static final String CLICK_CONNECT_MANAGER_PAGE_ITEM = "DAppConnectManage_3";
        public static final String CLICK_EDIT_APPROVE_AMOUNT_CANCEL = "edit_approve_amount_2";
        public static final String CLICK_EDIT_APPROVE_AMOUNT_CONFIRM = "edit_approve_amount_1";
        public static final int NOT_BLACK_URL = 1;
    }

    public static class DAppHistoryEvent {
        public static final String CLICK_BACK = "DappHistory_2_9";
        public static final String CLICK_CLEAR_ALL = "DappHistory_2_10";
        public static final String CLICK_CLEAR_ALL_CONFIRM = "DappHistory_2_10";
        public static final String CLICK_ITEM = "DappHistory_2_8";
        public static final String CLICK_LEFT_SWIPE = "DappHistory_2_4";
        public static final String CLICK_LEFT_SWIPE_DELETE = "DappHistory_2_5";
        public static final String CLICK_LONG_CLICK = "DappHistory_2_6";
        public static final String CLICK_LONG_CLICK_DELTEE = "DappHistory_2_7";
        public static final String DAPP_MENU_TAG = "DappHistory_2_";
    }

    public static class DAppMain {
        public static final String CLICK_BACK = "DAppMain_4";
        public static final String CLICK_DAPP_IN_ALL = "DAppMain_10";
        public static final String CLICK_DAPP_IN_DEFI = "DAppMain_11";
        public static final String CLICK_DAPP_IN_GAME = "DAppMain_12";
        public static final String CLICK_DAPP_IN_OTHER = "DAppMain_13";
        public static final String CLICK_FORWARD = "DAppMain_5";
        public static final String CLICK_HOME = "DAppMain_8";
        public static final String CLICK_MORE = "DAppMain_6";
        public static final String CLICK_MOST_VISIT = "DAppMain_9";
        public static final String CLICK_MULTI_TABS = "DAppMain_7";
        public static final String CLICK_SEARCH = "DAppMain_2";
        public static final String CLICK_SWITCH_WALLET = "DAppMain_3";
        public static final String CLICK_TITLE = "DAppMain_1";
        public static final String DAPP_MAIN_TAG = "DAppMain_";
    }

    public static class DAppSearchEvent {
        public static final String CLICK_DO_GOOGLE_SEARCH = "DAppSearchEvent11";
        public static final String CLICK_DO_TRONSCAN_SEARCH = "DAppSearchEvent12";
        public static final String CLICK_SEARCH_CANCEL = "DAppSearchEvent3";
        public static final String CLICK_SEARCH_EDIT = "DAppSearchEvent1";
        public static final String CLICK_SEARCH_EDIT_DELETE = "DAppSearchEvent2";
        public static final String CLICK_SEARCH_HISTORY_CLEAR = "DAppSearchEvent5";
        public static final String CLICK_SEARCH_HISTORY_ITEM = "DAppSearchEvent4";
        public static final String CLICK_SEARCH_RESULT_ITEM = "DAppSearchEvent13";
        public static final String CLICK_VISITED_HISTORY = "DAppSearchEvent6";
        public static final String CLICK_VISITED_HISTORY_CLEAR = "DAppSearchEvent7";
        public static final String CLICK_VISITED_HISTORY_CLEAR_CANCEL = "DAppSearchEvent10";
        public static final String CLICK_VISITED_HISTORY_CLEAR_CONFIRM = "DAppSearchEvent9";
        public static final String CLICK_VISITED_HISTORY_DELETE = "DAppSearchEvent8";
        public static final String DAPP_SEARCH_EVENT = "DAppSearchEvent";
    }

    public static class DAppTabsEvent {
        public static final String CLICK_BACK = "DappTabs8";
        public static final String CLICK_CLEAR = "DappTabs5";
        public static final String CLICK_CLEAR_CONFIRM = "DappTabs6";
        public static final String CLICK_DELETE = "DappTabs4";
        public static final String CLICK_FAVORITES = "DappTabs3";
        public static final String CLICK_History = "DappTabs2";
        public static final String CLICK_NEW_TAB = "DappTabs7";
        public static final String CLICK_SELECT_TAB = "DappTabs9";
        public static final String CLICK_TABs = "DappTabs1";
        public static final String DAPP_TABS_TAG = "DappTabs";
    }

    public static class DAppWebEvent {
        public static final String CLICK_BACK = "DAppWebEvent_4";
        public static final String CLICK_FORWARD = "DAppWebEvent_5";
        public static final String CLICK_HOME = "DAppWebEvent_8";
        public static final String CLICK_MORE = "DAppWebEvent_6";
        public static final String CLICK_MULTI_TABS = "DAppWebEvent_7";
        public static final String CLICK_SEARCH = "DAppWebEvent_2";
        public static final String CLICK_SWITCH_WALLET = "DAppWebEvent_3";
        public static final String CLICK_TOP_SEARCH = "DAppWebEvent_1";
        public static final String DAPP_WEB_EVENT = "DAppWebEvent_";
        public static final String LONG_PRESS_COPY = "DAppWebEvent_10";
        public static final String LONG_PRESS_LINK = "DAppWebEvent_9";
        public static final String LONG_PRESS_OPEN = "DAppWebEvent_11";
        public static final String LONG_PRESS_OPEN_NEW_TAB = "DAppWebEvent_12";
        public static final String LONG_PRESS_OPEN_OUTSIDE = "DAppWebEvent_13";
        public static final String PRESS_ENABLE_IMMERSIVE = "DAppWebEvent_14";
        public static final String PRESS_QUIT_IMMERSIVE = "DAppWebEvent_15";
    }

    public static class DappMenu {
        public static final String CLICK_BOOKMARK = "DAppMenu_3";
        public static final String CLICK_BOOKMARK_MANAGER = "DAppMenu_5";
        public static final String CLICK_CLOSE_TAB = "DAppMenu_10";
        public static final String CLICK_CONNECT_MANAGER = "DAppMenu_7";
        public static final String CLICK_HISTORY = "DAppMenu_4";
        public static final String CLICK_NEW_TAB = "DAppMenu_1";
        public static final String CLICK_OPEN_OUTSIDE = "DAppMenu_9";
        public static final String CLICK_REFRESH = "DAppMenu_2";
        public static final String CLICK_REPORT = "browser_report_show";
        public static final String CLICK_REPORT_BACK = "browser_report_show2";
        public static final String CLICK_REPORT_BACK_MAIN = "browser_report_show3";
        public static final String CLICK_REPORT_SUBMIT = "browser_report_show1";
        public static final String CLICK_SHARE = "DAppMenu_8";
        public static final String CLICK_SWITCH_WALLET = "DAppMenu_6";
        public static final String DAPP_MENU_TAG = "DAppMenu_";
    }

    public static class DeepLinkPage {
        public static final String CLICK_DEEPLINK_LOGIN_CANCEL = "deeplink_click_cancel";
        public static final String CLICK_DEEPLINK_LOGIN_CONFIRM = "deeplink_click_Confirm";
        public static final String CLICK_DEEPLINK_LOGIN_OPEN = "deeplink_click_tronlogin";
        public static final String CLICK_DEEPLINK_REQUEST_712_SIGN = "deeplink_request_clicknext_712sign";
        public static final String CLICK_DEEPLINK_REQUEST_CLICKNEXT_TRANSFER = "deeplink_request_clicknext_transfer";
        public static final String CLICK_DEEPLINK_REQUEST_CONTRACT = "deeplink_request_clicknext_contract";
        public static final String CLICK_DEEPLINK_REQUEST_MESSAGE_SIGN = "deeplink_request_clicknext_Message_sign";
        public static final String DEEPLINK_LOGINED = "deeplink_logined";
        public static final String DEEPLINK_POP_ERROR_DATA = "deeplink_Pop_errordata";
        public static final String DEEPLINK_POP_SWITCH_NET = "deeplink_Pop_Switchnet";
        public static final String ENTER_DEEPLINK_712_SIGN_SHOW_DIFFERENT_CHAIN_ID = "eip712_sign_show_2";
        public static final String ENTER_DEEPLINK_712_SIGN_SHOW_NO_CHAIN_ID = "eip712_sign_show_3";
        public static final String ENTER_DEEPLINK_712_SIGN_SHOW_SAME_CHAIN_ID = "eip712_sign_show_1";
        public static final String ENTER_DEEPLINK_LOGIN = "deeplink_login_page";
        public static final String ENTER_DEEPLINK_REQUEST_712_SIGN = "deeplink_request_712sign";
        public static final String ENTER_DEEPLINK_REQUEST_CONTRACT = "deeplink_request_contract";
        public static final String ENTER_DEEPLINK_REQUEST_FAIL_712_SIGN = "deeplink_request_fail_712sign";
        public static final String ENTER_DEEPLINK_REQUEST_FAIL_CONTRACT = "deeplink_request_fail_contract";
        public static final String ENTER_DEEPLINK_REQUEST_FAIL_MESSAGE_SIGN = "deeplink_request_fail_Message_sign";
        public static final String ENTER_DEEPLINK_REQUEST_MESSAGE_SIGN = "deeplink_request_Message_sign";
        public static final String ENTER_DEEPLINK_REQUEST_SUCCESS_712_SIGN = "deeplink_request_succeed_712sign";
        public static final String ENTER_DEEPLINK_REQUEST_SUCCESS_CONTRACT = "deeplink_request_succeed_contract";
        public static final String ENTER_DEEPLINK_REQUEST_SUCCESS_MESSAGE_SIGN = "deeplink_request_succeed_Message_sign";
        public static final String PV_DEEPLINK_REQUEST_FAIL_TRANSFER = "deeplink_request_fail_transfer";
        public static final String PV_DEEPLINK_REQUEST_SUCCEED_TRANSFER = "deeplink_request_succeed_transfer";
        public static final String PV_DEEPLINK_REQUEST_TRANSFER = "deeplink_request_transfer";
    }

    public static class FinancePage {
        public static final String FINANCE_PAGE_SELECT_ACCOUNT_POPUP_SHOW = "finance_myasset_switch_pop";
        public static final String FINANCE_PAGE_SORT_POPUP_APY_CLICK = "finance_sortord_pop_APY";
        public static final String FINANCE_PAGE_SORT_POPUP_ASSETS_CLICK = "finance_sortord_pop_assets";
        public static final String FINANCE_PAGE_SORT_POPUP_RECOMMEND_CLICK = "finance_sortord_pop_recommend";
        public static final String FINANCE_PAGE_SORT_POPUP_SHOW = "finance_sortord_pop";
        public static final String FINANCE_PAGE_SORT_POPUP_TVL_CLICK = "finance_sortord_pop_TVL";
        public static final String FINANCE_PAGE_SWITCH_ACCOUNT_POPUP_SHOW = "finance_switch_show";
    }

    public static class HDUpgradePage {
        public static final String CHANGE_HD_DESCRIPTION_CLICK_CANCEL = "changeHD_description_click_1";
        public static final String CHANGE_HD_DESCRIPTION_CLICK_NEXT = "changeHD_description_click_2";
        public static final String CHANGE_HD_DESCRIPTION_SHOW = "changeHD_description_show";
        public static final String CHANGE_HD_FAILURE_CLICK_AGAIN = "changeHD_failure_click_1";
        public static final String CHANGE_HD_FAILURE_CLICK_CANCEL = "changeHD_failure_click_2";
        public static final String CHANGE_HD_FAILURE_SHOW = "changeHD_failure_show";
        public static final String CHANGE_HD_LIST_CLICK = "changeHD_description_show";
        public static final String CHANGE_HD_LIST_SHOW = "changeHD_list_show";
        public static final String CHANGE_HD_SUCCESS_CLICK = "changeHD_success_click";
        public static final String CHANGE_HD_SUCCESS_SHOW = "changeHD_success_show";
        public static final String HD_EXPLAIN = "HD_explain";
        public static final String HD_EXPLAIN_DOC = "HD_explain_doc";
    }

    public static class HomePage {
        public static final String CLICK_HOME_PAGE_TAB_ASSET = "tabNavi_1";
        public static final String CLICK_HOME_PAGE_TAB_DAPP = "tabNavi_3";
        public static final String CLICK_HOME_PAGE_TAB_MARKET = "tabNavi_2";
        public static final String CLICK_HOME_PAGE_TAB_MY = "tabNavi_4";
        public static final String ENTER_HOME_PAGE_TAB_ASSET = "index_1";
        public static final String ENTER_HOME_PAGE_TAB_DAPP = "index_3";
        public static final String ENTER_HOME_PAGE_TAB_MARKET = "index_2";
        public static final String ENTER_HOME_PAGE_TAB_MY = "index_4";
    }

    public static class LedgerPage {
        public static final String CLICK_LEDGER_CONFIRM_TRANSACTION_ADD_NEW_DEVICE = "Click_ConfirmTransaction_add_new_device";
        public static final String CLICK_LEDGER_CONFIRM_TRANSACTION_NAME = "Click_ConfirmTransaction_name";
        public static final String CLICK_LEDGER_FLOW_CHANGE_ADDRESS = "Click_LedgerFlow_Change_Address";
        public static final String CLICK_LEDGER_FLOW_TUTORIAL = "Click_LedgerFlow_Tutorial";
        public static final String CLICK_LEDGER_MANAGE_ADD_NEW_DEVICE = "Click_LedgerManage_add_new_device";
        public static final String CLICK_LEDGER_MANAGE_DISCONNECT = "Click_LedgerManage_disconnect";
        public static final String CLICK_LEDGER_MANAGE_EDIT_REMOVE = "Click_LedgerManage_edit_remove";
        public static final String CLICK_LEDGER_MANAGE_IMPORT_ADDRESS = "Click_LedgerManage_import_address";
        public static final String CLICK_LEDGER_MANAGE_NAME = "Click_LedgerManage_name";
        public static final String CLICK_LEDGER_MANAGE_REMOVE = "Click_LedgerManage_remove";
        public static final String CLICK_LEDGER_MANAGE_TUTORIAL = "Click_LedgerManage_tutorial";
        public static final String ENTER_LEDGER_FLOW_ADD_NEW_DEVICE = "Enter_LedgerFlow_Add_New_Device";
        public static final String ENTER_LEDGER_FLOW_IMPORT_ADDRESS = "Enter_LedgerFlow_Import_Address";
        public static final String ENTER_LEDGER_SEARCH_PAGE = "LedgerFlow_1";
        public static final String ENTER_LEDGER_SELECT_ADDRESS_PAGE = "LedgerFlow_2";
    }

    public static class MarketPage {
        public static final String CLICK_MARKET_PAGE_SWAP_BANNER_JUSTSWAP = "market-swap_22";
        public static final String CLICK_MARKET_PAGE_SWAP_BANNER_SUN = "market-swap_23";
        public static final String CLICK_MARKET_PAGE_SWAP_BANNER_VOTING_REWARD = "market-swap_21";
        public static final String CLICK_MARKET_PAGE_SWAP_FAVORITE_TAB = "market-swap_24";
        public static final String CLICK_MARKET_PAGE_SWAP_MARKETS = "market-swap_20";
        public static final String CLICK_MARKET_PAGE_SWAP_SEARCH = "market-swap_27";
        public static final String CLICK_MARKET_PAGE_SWAP_SWAP = "market-swap_10";
        public static final String CLICK_MARKET_PAGE_SWAP_SWAP_CONFIRM = "market-swap_11";
        public static final String CLICK_MARKET_PAGE_SWAP_TRX_TAB = "market-swap_25";
        public static final String CLICK_MARKET_PAGE_SWAP_USDT_TAB = "market-swap_26";
    }

    public static class MultiSelectAddress {
        public static final String CLICK_MULTI_SELECT_ADDRESS_BACK = "MultiSelectAddress_%d_1";
        public static final String CLICK_MULTI_SELECT_ADDRESS_NEXT = "MultiSelectAddress_%d_5";
        public static final String CLICK_MULTI_SELECT_ADDRESS_PASTE = "MultiSelectAddress_%d_4";
        public static final String CLICK_MULTI_SELECT_ADDRESS_SCAN = "MultiSelectAddress_%d_3";
        public static final String CLICK_MULTI_SELECT_ADDRESS_TUTORIAL = "MultiSelectAddress_%d_2";
    }

    public static class MultiSignUnStakeStep2 {
        public static final String CLICK_BACK = "MultisigUnstaking（2_2)_1";
        public static final String CLICK_CONFIRM = "MultisigUnstaking（2_2)_2";
        public static final String CLICK_FOR_OTHER = "MultisigUnstaking（2_2)_4";
        public static final String CLICK_FOR_SELF = "MultisigUnstaking（2_2)_3";
        public static final String START_TAG = "MultisigUnstaking（2_2)_";
    }

    public static class MultiSignature {
        public static final String CLICK_MULTI_SIGNATURE_POP = "MultiSignature_2";
        public static final String CLICK_MULTI_SIGNATURE_TRANSFER_PAGE = "MultiSignature_1";
    }

    public static class MultisigStaking {
        public static final String BASE_TAG = "MultisigStakingGate";
        public static final String CLICK_DIALOG = "MultisigStakingGate1";
        public static final String CLICK_RIGHT_TEXT = "MultisigStakingGate2";
    }

    public static class MultisigUnstaking {
        public static final String BASE_TAG = "MultisigUnstaking_";
        public static final String CLICK_DIALOG = "MultisigUnstaking_1";
        public static final String CLICK_RIGHT_TEXT = "MultisigUnstaking_2";
    }

    public static class MyPage {
        public static final String CLICK_PROFILE_ABOUT = "profilePage_11";
        public static final String CLICK_PROFILE_ADDRESS_BOOK = "profilePage_5";
        public static final String CLICK_PROFILE_ADVANCED_FEATURES = "profilePage_8";
        public static final String CLICK_PROFILE_ANNOUNCEMENT = "profilePage_9";
        public static final String CLICK_PROFILE_FRIEND_INVITATION = "profilePage_7";
        public static final String CLICK_PROFILE_HELP = "wallet_guide_show";
        public static final String CLICK_PROFILE_HISTORY = "profilePage_4";
        public static final String CLICK_PROFILE_LEDGER = "profilePage_3";
        public static final String CLICK_PROFILE_SETTING = "profilePage_6";
        public static final String CLICK_PROFILE_SHIELD_WALLET_MANAGEMENT = "profilePage_2";
        public static final String CLICK_PROFILE_WALLET_MANAGEMENT = "profilePage_1";
    }

    public static class OneKeyExport {
        public static final String CLICK_EXPORT_ACTION = "onekeyExport_action";
        public static final String CLICK_IMPORT_ACTION = "onekeyimport_action";
        public static final String CLICK_IMPORT_FAIL_OK = "onekeyimport_failpop_click_0";
        public static final String CLICK_IMPORT_FAIL_RETRY = "onekeyimport_failpop_click_1";
        public static final String ENTER_EXPORT_PAGE = "onekeyExport_page";
        public static final String ENTER_IMPORT_FAIL = "onekeyimport_failpop";
        public static final String ENTER_IMPORT_PAGE = "onekeyimport_page";
        public static final String ENTER_IMPORT_SUCCESS = "onekeyimport_succeedpop";
        public static final String ENTER_SUB_FAIL = "onekeyExport_result_pop_1";
        public static final String ENTER_SUB_SUCCESS = "onekeyExport_result_pop_0";
    }

    public static class OtherPage {
        public static final String ENTER_FREEZE_PAGE = "freezePage";
        public static final String ENTER_RECEIVE_PAGE = "receiveTokenPage";
        public static final String ENTER_VOTE_PAGE = "votePage";
    }

    public static class PageStateConfig {
        public static final int PRI_WALLET = 1;
        public static final int SMART_CONTRACT = 2;
        public static final int SYSTEM_CONTRACT = 1;
        public static final int TOKEN_10_20 = 0;
        public static final int TOKEN_721 = 1;
        public static final int WATCH_WALLET = 2;
    }

    public static class PairWatchColdWallet {
        public static final String ADD_COLD_WALLET_PAGE_CLICK_NEXT = "add_coldwallet_next";
        public static final String ADD_COLD_WALLET_PAGE_SHOW = "add_coldwallet_page";
        public static final String WALLET_MANEGE_COLD_WALLET_PAIR_CLICK = "pair_coldwallet";
        public static final String WATCH_COLD_EDU_NEXT = "watch_cold_edu_next";
        public static final String WATCH_COLD_EDU_SHOW = "watch_cold_edu_show";
        public static final String WATCH_COLD_PAIRWIN_CLICK = "watch_cold_pairwin_click";
        public static final String WATCH_COLD_PAIRWIN_SHOW = "watch_cold_pairwin_show";
        public static final String WATCH_COLD_PAIR_POP_CLICK_CONFIRM = "watch_cold_pairpop_confirm";
        public static final String WATCH_COLD_PAIR_POP_CLICK_PAIR = "watch_cold_pairpop_pair";
        public static final String WATCH_COLD_PAIR_POP_SHOW = "watch_cold_pairpop_show";
    }

    public static class RadioPage {
        public static final String ENTER_RADIO_PAGE = "RadioPage_";
        public static final String ENTER_RADIO_PAGE_1 = "RadioPage_1";
        public static final String ENTER_RADIO_PAGE_2 = "RadioPage_2";
        public static final String ENTER_RADIO_PAGE_3 = "RadioPage_3";
        public static final String ENTER_RADIO_PAGE_4 = "RadioPage_4";
        public static final String ENTER_RADIO_PAGE_5 = "RadioPage_5";
        public static final String ENTER_RADIO_PAGE_6 = "RadioPage_6";
        public static final String ENTER_RADIO_PAGE_7 = "RadioPage_7";
    }

    public static class ReceivePage {
        public static final String CLICK_ADDRESS_COPY = "receive_1";
        public static final String CLICK_SAVE_QR = "receive_2";
        public static final String CLICK_SHARE_QR = "receive_3";
    }

    public static class ResourceDelegatePopPage {
        public static final String BANDWIDTH_DELEGATED_CLICK_TO_RECLAIM = "bandwidthagency__recycle_click_1";
        public static final String BANDWIDTH_DELEGATED_TO_OTHER_PAGE = "bandwidth_agency_show";
        public static final String ENERGY_DELEGATED_CLICK_TO_RECLAIM = "energyagency_recycle_click_1";
        public static final String ENERGY_DELEGATED_TO_OTHER_PAGE = "energy_agency_show";
    }

    public static class ResourceGate {
        public static final String CLICK_HOME_PAGE_ENERGY_BANDWIDTH = "ResourceGate_1";
        public static final String CLICK_TOKEN_DETAIL_PAGE_STAKED = "ResourceGate_2";
    }

    public static class ResourcePage {
        public static final String CLICK_RESOURCE_PAGE_HELP = "Resource_2";
        public static final String CLICK_RESOURCE_PAGE_STAKED_DETAIL = "Resource_1";
        public static final String CLICK_RESOURCE_PAGE_TRX_HELP = "Resource_3";
        public static final String CLICK_RESOURCE_PAGE_VOTE_HELP = "Resource_4";
        public static final String ENTER_RESOURCE_PAGE = "Resource";
    }

    public static class ResourceUnDelegatePage {
        public static final String UN_DELEGATE_BAND_CLICK_100 = "Bandwidth_recycle_click_100";
        public static final String UN_DELEGATE_BAND_CLICK_25 = "Bandwidth_recycle_click_25";
        public static final String UN_DELEGATE_BAND_CLICK_50 = "Bandwidth_recycle_click_50";
        public static final String UN_DELEGATE_BAND_CLICK_75 = "Bandwidth_recycle_click_75";
        public static final String UN_DELEGATE_BAND_CLICK_BAND = "Bandwidth_recycle_click_bandwidth";
        public static final String UN_DELEGATE_BAND_CLICK_NEXT = "Bandwidth_recycle_click_recycle";
        public static final String UN_DELEGATE_BAND_CLICK_TRX = "Bandwidth_recycle_click_TRX";
        public static final String UN_DELEGATE_BAND_WIDTH_SHOW = "Bandwidth_recycle_show";
        public static final String UN_DELEGATE_ENERGY_CLICK_100 = "Energy_recycle_click_100";
        public static final String UN_DELEGATE_ENERGY_CLICK_25 = "Energy_recycle_click_25";
        public static final String UN_DELEGATE_ENERGY_CLICK_50 = "Energy_recycle_click_50";
        public static final String UN_DELEGATE_ENERGY_CLICK_75 = "Energy_recycle_click_75";
        public static final String UN_DELEGATE_ENERGY_CLICK_ENERGY = "Energy_recycle_click_energy";
        public static final String UN_DELEGATE_ENERGY_CLICK_NEXT = "Energy_recycle_click_next";
        public static final String UN_DELEGATE_ENERGY_CLICK_TRX = "Energy_recycle_click_TRX";
        public static final String UN_DELEGATE_ENERGY_SHOW = "Energy_recycle_show";
    }

    public static class SRDetail {
        public static final String BASE_MAIN_TAG = "sr_detail";
        public static final int CLICK_BACK = 0;
        public static final int CLICK_CANCEL = 3;
        public static final int CLICK_UPDATE_VOTE = 2;
        public static final int CLICK_VOTE = 1;
        public static final String UNVOTED = "sr_detail_1_";
        public static final String VOTED = "sr_detail_0_";
    }

    public static class SecurityApprovePage {
        public static final String SECURITY_APPROVE_SHOW = "security_approve_show";
        private static String SECURITY_APPROVE_CLICK = "security_approve_click";
        public static String SECURITY_APPROVE_CLICK_TIPS = SECURITY_APPROVE_CLICK + "_1";
        public static String SECURITY_APPROVE_CLICK_BY_TOKEN = SECURITY_APPROVE_CLICK + "_2";
        public static String SECURITY_APPROVE_CLICK_BY_PROJECT = SECURITY_APPROVE_CLICK + "_3";
        public static String SECURITY_APPROVE_CLICK_CANCEL_AUTH = SECURITY_APPROVE_CLICK + "_4";
        public static String SECURITY_APPROVE_CLICK_COPY_CONTRACT_ADDRESS = SECURITY_APPROVE_CLICK + "_5";
        public static String SECURITY_APPROVE_CLICK_UNKNOWN_TOKEN = SECURITY_APPROVE_CLICK + "_6";
        public static String SECURITY_APPROVE_CLICK_SHOW_UP_DOWN = SECURITY_APPROVE_CLICK + "_7";
        public static String SECURITY_APPROVE_CLICK_SHOW_ALL_APPROVED = SECURITY_APPROVE_CLICK + "_8";
    }

    public static class SecurityCancelApprovePage {
        public static final String SECURITY_CANCEL_APPROVE_SHOW = "security_approve_cancel_page";
        private static String SECURITY_CANCEL_APPROVE_CLICK = "security_approve_cancel_click";
        public static String SECURITY_CANCEL_APPROVE_CLICK_CONFIRM = SECURITY_CANCEL_APPROVE_CLICK + "_1";
        public static String SECURITY_CANCEL_APPROVE_CLICK_CANCEL = SECURITY_CANCEL_APPROVE_CLICK + "_2";
    }

    public static class SecurityEnvironmentPage {
        private static String SECURITY_ENVIRONMENT_CLICK = "security_environment_click";
        public static String SECURITY_ENVIRONMENT_CLICK_ASK = SECURITY_ENVIRONMENT_CLICK + "_1";
        public static String SECURITY_ENVIRONMENT_CLICK_ROOT = SECURITY_ENVIRONMENT_CLICK + "_2";
        public static final String SECURITY_ENVIRONMENT_SHOW = "security_environment_show";
    }

    public static class SecurityHomePage {
        public static final String SECURITY_HOME_SHOW = "security_home_show";
        private static String SECURITY_HOME_CLICK = "security_home_click";
        public static String SECURITY_HOME_CLICK_ASK = SECURITY_HOME_CLICK + "_1";
        public static String SECURITY_HOME_CLICK_CHECK = SECURITY_HOME_CLICK + "_2";
        public static String SECURITY_HOME_CLICK_ITEM_1 = SECURITY_HOME_CLICK + "_3";
        public static String SECURITY_HOME_CLICK_ITEM_2 = SECURITY_HOME_CLICK + "_4";
        public static String SECURITY_HOME_CLICK_ITEM_3 = SECURITY_HOME_CLICK + "_5";
        public static String SECURITY_HOME_CLICK_CHECK_AGAIN = SECURITY_HOME_CLICK + "_6";
    }

    public static class SecurityTokenCheckPage {
        public static final String SECURITY_TOKEN_CHECK_SHOW = "security_token_check_show";
        private static String SECURITY_TOKEN_CHECK_CLICK = "security_token_check_click";
        public static String SECURITY_TOKEN_CHECK_CLICK_TIPS = SECURITY_TOKEN_CHECK_CLICK + "_1";
        public static String SECURITY_TOKEN_CHECK_CLICK_TAB_HIGH_RISK = SECURITY_TOKEN_CHECK_CLICK + "_2";
        public static String SECURITY_TOKEN_CHECK_CLICK_TAB_MEDIUM_RISK = SECURITY_TOKEN_CHECK_CLICK + "_3";
        public static String SECURITY_TOKEN_CHECK_CLICK_TAG_SUSPICIOUS = SECURITY_TOKEN_CHECK_CLICK + "_4";
        public static String SECURITY_TOKEN_CHECK_CLICK_TAG_UNSAFE = SECURITY_TOKEN_CHECK_CLICK + "_5";
        public static String SECURITY_TOKEN_CHECK_CLICK_TAG_HAVE_BLACK_LIST = SECURITY_TOKEN_CHECK_CLICK + "_6";
        public static String SECURITY_TOKEN_CHECK_CLICK_TAG_SUPPORT_SELF_MINTING = SECURITY_TOKEN_CHECK_CLICK + "_7";
        public static String SECURITY_TOKEN_CHECK_CLICK_TAG_HAVE_PROXY_CONTRACT = SECURITY_TOKEN_CHECK_CLICK + "_8";
        public static String SECURITY_TOKEN_CHECK_CLICK_TAG_CLOSE_OPEN_SOURCE = SECURITY_TOKEN_CHECK_CLICK + "_9";
        public static String SECURITY_TOKEN_CHECK_CLICK_SEARCH = SECURITY_TOKEN_CHECK_CLICK + "_10";
        public static String SECURITY_TOKEN_CHECK_CLICK_IGNORE = SECURITY_TOKEN_CHECK_CLICK + "_11";
        public static String SECURITY_TOKEN_CHECK_CLICK_DELETE = SECURITY_TOKEN_CHECK_CLICK + "_12";
    }

    public static class SelectSendAddress {
        public static final String CLICK_ADDRESS_BOOK = "5";
        public static final String CLICK_BACK = "1";
        public static final String CLICK_MY_ACCOUNT = "6";
        public static final String CLICK_NEXT = "7";
        public static final String CLICK_PASTE = "3";
        public static final String CLICK_RECENT = "4";
        public static final String CLICK_SCAN_QR_CODE = "2";
        public static final String ENTER_COMMON = "Transfers_1";
        public static final String MULTI_PREFIX = "MultiSelectAddress_2_";
        public static final String PREFIX = "Transfers_1_";

        public static String getPrefix(boolean z) {
            return z ? MULTI_PREFIX : PREFIX;
        }
    }

    public static class Setting {
        public static final String CLICK_SETTING_CLICK_BACK = "Setting_9";
        public static final String CLICK_SETTING_CURRENCY_UNIT = "Setting_2";
        public static final String CLICK_SETTING_DAPP_CONNECTION_MANAGEMENT = "Setting_4";
        public static final String CLICK_SETTING_LANGUAGE = "Setting_1";
        public static final String CLICK_SETTING_NOTIFICATIONS = "Setting_3";
        public static final String CLICK_SETTING_QR_CODE_SPLIT = "Setting_5";
        public static final String CLICK_SETTING_SWITCH_NETWORK = "Setting_6";
        public static final String CLICK_SETTING_SWITCH_NODE = "Setting_7";
        public static final String CLICK_SETTING_SWITCH_SERVER = "Setting_8";
    }

    public static class StakeGate {
        public static final String CLICK_HOME_PAGE_STAKE = "StakeGate_1";
        public static final String CLICK_RESOURCE_STAKE = "StakeGate_2";
    }

    public static class Staking {
        public static final String BASE_1_2_TAG = "Stake_TRX（1_2）";
        public static final String BASE_2_2_TAG = "Stake_TRX（2_2）";
        public static final String BASE_2_3_TAG = "MultisigStaking2_3";
        public static final String BASE_3_3_TAG = "MultisigStaking3_3";
        public static final String CLICK_2_2_BACK = "Stake_TRX（2_2）5";
        public static final String CLICK_2_3_BACK = "MultisigStaking2_37";
        public static final String CLICK_2_3_INPUT = "MultisigStaking2_35";
        public static final String CLICK_2_3_NEXT = "MultisigStaking2_36";
        public static final String CLICK_3_3_ADDRESSBOOK = "MultisigStaking3_37";
        public static final String CLICK_3_3_BACK = "MultisigStaking3_35";
        public static final String CLICK_3_3_CHECKBOX = "MultisigStaking3_33";
        public static final String CLICK_3_3_CHOOSE_BY_SEARCH = "MultisigStaking3_39";
        public static final String CLICK_3_3_CONFIRM = "MultisigStaking3_34";
        public static final String CLICK_3_3_INPUT_DELETE_ICON = "MultisigStaking3_31";
        public static final String CLICK_3_3_INPUT_SCAN_ICON = "MultisigStaking3_32";
        public static final String CLICK_3_3_MY_ACCOUNT = "MultisigStaking3_36";
        public static final String CLICK_3_3_PASTE = "MultisigStaking3_38";
        public static final String CLICK_ADDRESSBOOK = "Stake_TRX（2_2）7";
        public static final String CLICK_BACK = "Stake_TRX（1_2）7";
        public static final String CLICK_BANDWIDTH = "MultisigStaking2_33";
        public static final String CLICK_BANDWIDTH_TAG = "Stake_TRX（1_2）3";
        public static final String CLICK_CHECKBOX = "Stake_TRX（2_2）3";
        public static final String CLICK_CHOOSE_BY_SEARCH = "Stake_TRX（2_2）9";
        public static final String CLICK_CONFIRM = "Stake_TRX（2_2）4";
        public static final String CLICK_ENERGY = "MultisigStaking2_32";
        public static final String CLICK_ENERGY_TAG = "Stake_TRX（1_2）2";
        public static final String CLICK_ESTIMATED_TO_GET = "Stake_TRX1_29";
        public static final String CLICK_INPUT = "Stake_TRX（1_2）5";
        public static final String CLICK_INPUT_DELETE_ICON = "Stake_TRX（2_2）1";
        public static final String CLICK_INPUT_SCAN_ICON = "Stake_TRX（2_2）2";
        public static final String CLICK_MY_ACCOUNT = "Stake_TRX（2_2）6";
        public static final String CLICK_MY_TAB_ACCOUNT = "Stake_TRX2_26";
        public static final String CLICK_NEXT_BTN = "Stake_TRX（1_2）6";
        public static final String CLICK_PASTE = "Stake_TRX（2_2）8";
        public static final String CLICK_PERCENT = "MultisigStaking2_34";
        public static final String CLICK_PERCENT_BUTTON = "Stake_TRX（1_2）4";
        public static final String CLICK_RESOURCE_EDIT = "Stake_TRX1_28";
        public static final String CLICK_TAB_ADDRESSBOOK = "Stake_TRX（2_2）7";
        public static final String CLICK_TITLE_2_3_QUESTION_MARK = "MultisigStaking2_31";
        public static final String CLICK_TITLE_QUESTION_MARK = "Stake_TRX（1_2）1";
        public static final String ENTER_MULTI_SIG_STAKING2_3 = "MultisigStaking2_3";
        public static final String ENTER_MULTI_SIG_STAKING3_3 = "MultisigStaking3_3";
        public static final String PV_BASE_1_2_TAG = "Stake_TRX1_2";
        public static final String PV_BASE_2_2_TAG = "Stake_TRX2_2";
    }

    public static class Staking2 {
        public static final String BASE_1_2_TAG = "StakeV2_TRX（1_2）";
        public static final String BASE_2_2_TAG = "StakeV2_TRX（2_2）";
        public static final String BASE_2_3_TAG = "MultisigStakingV2_2_3";
        public static final String BASE_3_3_TAG = "MultisigStakingV2_3_3";
        public static final String CLICK_2_2_BACK = "StakeV2_TRX（2_2）5";
        public static final String CLICK_2_3_BACK = "MultisigStakingV2_2_37";
        public static final String CLICK_2_3_INPUT = "MultisigStakingV2_2_35";
        public static final String CLICK_2_3_NEXT = "MultisigStakingV2_2_36";
        public static final String CLICK_3_3_ADDRESSBOOK = "MultisigStakingV2_3_37";
        public static final String CLICK_3_3_BACK = "MultisigStakingV2_3_35";
        public static final String CLICK_3_3_CHECKBOX = "MultisigStakingV2_3_33";
        public static final String CLICK_3_3_CHOOSE_BY_SEARCH = "MultisigStakingV2_3_39";
        public static final String CLICK_3_3_CONFIRM = "MultisigStakingV2_3_34";
        public static final String CLICK_3_3_INPUT_DELETE_ICON = "MultisigStakingV2_3_31";
        public static final String CLICK_3_3_INPUT_SCAN_ICON = "MultisigStakingV2_3_32";
        public static final String CLICK_3_3_MY_ACCOUNT = "MultisigStakingV2_3_36";
        public static final String CLICK_3_3_PASTE = "MultisigStakingV2_3_38";
        public static final String CLICK_ADDRESSBOOK = "StakeV2_TRX（2_2）7";
        public static final String CLICK_BACK = "StakeV2_TRX（1_2）7";
        public static final String CLICK_BANDWIDTH = "MultisigStakingV2_2_33";
        public static final String CLICK_BANDWIDTH_TAG = "StakeV2_TRX（1_2）3";
        public static final String CLICK_CHECKBOX = "StakeV2_TRX（2_2）3";
        public static final String CLICK_CHOOSE_BY_SEARCH = "StakeV2_TRX（2_2）9";
        public static final String CLICK_CONFIRM = "StakeV2_TRX（2_2）4";
        public static final String CLICK_ENERGY = "MultisigStakingV2_2_32";
        public static final String CLICK_ENERGY_TAG = "StakeV2_TRX（1_2）2";
        public static final String CLICK_ESTIMATED_TO_GET = "StakeV2_TRX1_29";
        public static final String CLICK_INPUT = "StakeV2_TRX（1_2）5";
        public static final String CLICK_INPUT_DELETE_ICON = "StakeV2_TRX（2_2）1";
        public static final String CLICK_INPUT_SCAN_ICON = "StakeV2_TRX（2_2）2";
        public static final String CLICK_MY_ACCOUNT = "StakeV2_TRX（2_2）6";
        public static final String CLICK_MY_TAB_ACCOUNT = "StakeV2_TRX2_26";
        public static final String CLICK_NEXT_BTN = "StakeV2_TRX（1_2）6";
        public static final String CLICK_PASTE = "StakeV2_TRX（2_2）8";
        public static final String CLICK_PERCENT = "MultisigStakingV2_2_34";
        public static final String CLICK_PERCENT_BUTTON = "StakeV2_TRX（1_2）4";
        public static final String CLICK_RESOURCE_EDIT = "StakeV2_TRX1_28";
        public static final String CLICK_TAB_ADDRESSBOOK = "StakeV2_TRX（2_2）7";
        public static final String CLICK_TITLE_2_3_QUESTION_MARK = "MultisigStakingV2_2_31";
        public static final String CLICK_TITLE_QUESTION_MARK = "StakeV2_TRX（1_2）1";
        public static final String ENTER_MULTI_SIG_STAKING2_3 = "MultisigStakingV2_2_3";
        public static final String ENTER_MULTI_SIG_STAKING3_3 = "MultisigStakingV2_3_3";
        public static final String PV_BASE_1_2_TAG = "StakeV2_TRX1_2";
        public static final String PV_BASE_2_2_TAG = "StakeV2_TRX2_2";
    }

    public static class StartPage {
        public static final String CLICK_START_PAGE_COLD_PAIR_WALLET = "startPage_8";
        public static final String CLICK_START_PAGE_COLD_WALLET = "startPage_3";
        public static final String CLICK_START_PAGE_CREATE_WALLET = "startPage_1";
        public static final String CLICK_START_PAGE_IMPORT_WALLET = "startPage_2";
        public static final String CLICK_START_PAGE_LEDGER_WALLET = "startPage_5";
        public static final String CLICK_START_PAGE_MIGRATE_WALLET = "startPage_7";
        public static final String CLICK_START_PAGE_SAMSUNG_WALLET = "startPage_4";
        public static final String CLICK_START_PAGE_WATCH_WALLET = "startPage_6";
        public static final String CLICK_TERM_PAGE_AGREE = "termPage_2";
        public static final String CLICK_TERM_PAGE_DISAGREE = "termPage_1";
        public static final String ENTER_START_PAGE = "startPage";
        public static final String ENTER_TERM_PAGE = "termPage";
        public static final String ENTER_WELCOME_PAGE = "welcomePage";
    }

    public static class SuperDetailPage {
        public static final String IS_MULTI_VOTE = "is_multivoter";
        public static final String SUPER_DETAIL_SUCCESS = "sr_detail";
    }

    public static class SwitchAccountPage {
        public static final String CLICK_SELECT_ACCOUNT_PAGE_ADD = "SelectAccount_1";
        public static final String CLICK_SELECT_ACCOUNT_PAGE_BACK = "SelectAccount_0";
        public static final String CLICK_SELECT_ACCOUNT_PAGE_SORT = "SelectAccount_2";
        public static final String CLICK_SELECT_ACCOUNT_PAGE_SORT_BY_ALL = "SelectAccount_6";
        public static final String CLICK_SELECT_ACCOUNT_PAGE_SORT_BY_DEFAULT = "SelectAccount_5";
        public static final String CLICK_SELECT_ACCOUNT_PAGE_SORT_BY_TYPE = "SelectAccount_3";
        public static final String CLICK_SELECT_ACCOUNT_PAGE_SORT_BY_VALUE = "SelectAccount_4";
        public static final String CLICK_SWITCH_ACCOUNT_PAGE_CREATE_WALLET = "SwitchAccount_8";
        public static final String CLICK_SWITCH_ACCOUNT_PAGE_FLOAT_BAR = "SwitchAccount_5";
        public static final String CLICK_SWITCH_ACCOUNT_PAGE_NON_HD = "SwitchAccount_6";
        public static final String CLICK_SWITCH_ACCOUNT_PAGE_SEARCH = "SwitchAccount_3";
        public static final String CLICK_SWITCH_ACCOUNT_PAGE_SELECT = "SwitchAccount_4";
        public static final String CLICK_SWITCH_ACCOUNT_PAGE_WALLET_DETAIL = "SwitchAccount_7";
        public static final String ENTER_SWITCH_ACCOUNT_PAGE = "SwitchAccount_1";
        public static final String ENTER_SWITCH_ACCOUNT_PAGE_SHIELD = "SwitchAccount_2";
    }

    public static class SwitchWalletPage {
        public static final String CLICK_CLOSE = "SwitchWallet_2";
        public static final String CLICK_COPY = "SwitchWallet_4";
        public static final String CLICK_SEARCH = "SwitchWallet_1";
        public static final String CLICK_WALLET = "SwitchWallet_3";
        public static final String SWITCH_WALLET = "SwitchWallet_";
    }

    public static class ThirdPartyInputAlert {
        public static final String ThirdPartyInputAlert_BASE = "third_party_input_alert";
        public static final String ThirdPartyInputAlert_Continue = "third_party_input_alert2";
        public static final String ThirdPartyInputAlert_Not_remind = "third_party_input_alert3";
        public static final String ThirdPartyInputAlert_Switch_input = "third_party_input_alert1";
    }

    public static class TokenDetails {
        public static final String BASE_TAG = "TRXassetDetails";
        public static final String CLICK_ALL = "TRXassetDetails3";
        public static final String CLICK_BACK = "TRXassetDetails1";
        public static final String CLICK_IN = "TRXassetDetails5";
        public static final String CLICK_OUT = "TRXassetDetails4";
        public static final String CLICK_RECEIVE = "TRXassetDetails7";
        public static final String CLICK_STAKED = "TRXassetDetails2";
        public static final String CLICK_TRANSFER = "TRXassetDetails6";
        public static final String CLICK_WITHDRAW_DEPOSIT = "TRXassetDetails8";
    }

    public static class TokenDetailsPage {
        public static final String CLICK_NFT_DETAIL_PAGE_COPY_ADDRESS = "assetDetails_721_3";
        public static final String CLICK_NFT_DETAIL_PAGE_ITEM = "assetDetails_721_4";
        public static final String CLICK_NFT_DETAIL_PAGE_PRODUCTION_PAGE = "assetDetails_721_2";
        public static final String CLICK_NFT_DETAIL_PAGE_TRANSACTION_HISTORY = "assetDetails_721_1";
        public static final String CLICK_NFT_HISTORY_PAGE_ITEM = "TransactionHistory_721_4";
        public static final String CLICK_NFT_HISTORY_PAGE_TAB_ALL = "TransactionHistory_721_1";
        public static final String CLICK_NFT_HISTORY_PAGE_TAB_RECEIVE = "TransactionHistory_721_2";
        public static final String CLICK_NFT_HISTORY_PAGE_TAB_SEND = "TransactionHistory_721_3";
        public static final String CLICK_NFT_TRANSACTION_DETAIL_PAGE_COPY_LINK = "TransactionDetails_721_1";
        public static final String CLICK_NFT_TRANSACTION_DETAIL_PAGE_SHARE = "TransactionDetails_721_3";
        public static final String CLICK_NFT_TRANSACTION_DETAIL_PAGE_SHARE_IMAGE = "TransactionDetails_721_5";
        public static final String CLICK_NFT_TRANSACTION_DETAIL_PAGE_SHARE_LINK = "TransactionDetails_721_4";
        public static final String CLICK_NFT_TRANSACTION_DETAIL_PAGE_VIEW_DETAILS = "TransactionDetails_721_2";
        public static final String CLICK_TOKEN_DETAIL_PAGE = "assetDetails_20";
        public static final String CLICK_TOKEN_DETAIL_PAGE_DEPOSIT = "assetDetails_3";
        public static final String CLICK_TOKEN_DETAIL_PAGE_RECEIVE = "assetDetails_2";
        public static final String CLICK_TOKEN_DETAIL_PAGE_TRANSFER = "assetDetails_1";
        public static final String ENTER_NFT_DETAIL_PAGE = "assetDetails_721";
        public static final String ENTER_TOKEN_DETAIL_PAGE = "assetDetails";
    }

    public static class TokenProjectDetailPage {
        public static final String TOKEN_PROJECT_DETAIL_SHOW = "token_project_detail";
        public static final String TOKEN_REPORT_CLICK = "token_report_click";
        public static final String TOKEN_REPORT_CLICK_BACK = "token_report_click_1";
        public static final String TOKEN_REPORT_CLICK_SUBMIT = "token_report_click_2";
        public static final String TOKEN_REPORT_CLICK_TOTRONSCAN = "token_report_click_3";
        public static final String TOKEN_REPORT_SHOW = "token_report_show";
        private static String TOKEN_PROJECT_DETAIL_CLICK = "token_project_click";
        public static String TOKEN_PROJECT_DETAIL_CLICK_BACK = TOKEN_PROJECT_DETAIL_CLICK + "_1";
        public static String TOKEN_PROJECT_DETAIL_CLICK_VIEW_BROWSE = TOKEN_PROJECT_DETAIL_CLICK + "_2";
        public static String TOKEN_PROJECT_DETAIL_CLICK_REPORT = TOKEN_PROJECT_DETAIL_CLICK + "_3";
        public static String TOKEN_PROJECT_DETAIL_CLICK_POWER_BY_TRONSCAN = TOKEN_PROJECT_DETAIL_CLICK + "_4";
    }

    public static class TransactionConfirmPopup {
        public static final String CLICK_CONFIRM_PAGE_ACCOUNT_BALANCE = "TransactionPop_%d_12";
        public static final String CLICK_CONFIRM_PAGE_APPROVED_CONTRACT = "TransactionPop_%d_10";
        public static final String CLICK_CONFIRM_PAGE_APPROVED_TOKEN_ID = "TransactionPop_%d_11";
        public static final String CLICK_CONFIRM_PAGE_BACK = "TransactionPop_%d_2";
        public static final String CLICK_CONFIRM_PAGE_CHEAT_TRADE = "TransactionPop_%d_13";
        public static final String CLICK_CONFIRM_PAGE_CONFIRM = "TransactionPop_%d_3";
        public static final String CLICK_CONFIRM_PAGE_COST_RESOURCE = "TransactionPop_%d_6";
        public static final String CLICK_CONFIRM_PAGE_FEE = "TransactionPop_%d_7";
        public static final String CLICK_CONFIRM_PAGE_META_COPY = "TransactionPop_%d_5";
        public static final String CLICK_CONFIRM_PAGE_META_TAB = "TransactionPop_%d_4";
        public static final String CLICK_CONFIRM_PAGE_OUTER = "TransactionPop_%d_1";
        public static final String CLICK_CONFIRM_PAGE_SMART_CONTRACT = "TransactionPop_%d_8";
        public static final String CLICK_CONFIRM_PAGE_TOKEN_ID = "TransactionPop_%d_9";
    }

    public static class TransactionDetailsPage {
        public static final String CLICK_TRANSACTION_DETAIL_PAGE_COPY_LINK = "TransactionDetails_%s_1";
        public static final String CLICK_TRANSACTION_DETAIL_PAGE_LOAD = "TransactionDetails_%s";
        public static final String CLICK_TRANSACTION_DETAIL_PAGE_SHARE = "TransactionDetails_%s_3";
        public static final String CLICK_TRANSACTION_DETAIL_PAGE_SHARE_IMAGE = "TransactionDetails_%s_5";
        public static final String CLICK_TRANSACTION_DETAIL_PAGE_SHARE_LINK = "TransactionDetails_%s_4";
        public static final String CLICK_TRANSACTION_DETAIL_PAGE_VIEW_DETAILS = "TransactionDetails_%s_2";
    }

    public static class TransferPage {
        public static final String CLICK_TRANSFER_PAGE_ADDRESS_BOOK = "sendTokenPage_0_2";
        public static final String CLICK_TRANSFER_PAGE_ADDRESS_BOOK_NFT = "sendTokenPage_1_2";
        public static final String CLICK_TRANSFER_PAGE_ADD_NOTE = "sendTokenPage_0_1";
        public static final String CLICK_TRANSFER_PAGE_ADD_NOTE_NFT = "sendTokenPage_1_1";
        public static final String CLICK_TRANSFER_PAGE_SCAN = "sendTokenPage_0_3";
        public static final String CLICK_TRANSFER_PAGE_SCAN_NFT = "sendTokenPage_1_3";
        public static final String CLICK_TRANSFER_PAGE_SEARCH_TOKEN = "sendTokenPage_0_5";
        public static final String CLICK_TRANSFER_PAGE_SEARCH_TOKEN_NFT = "sendTokenPage_1_5";
        public static final String CLICK_TRANSFER_PAGE_SELECT_TOKEN = "sendTokenPage_0_4";
        public static final String CLICK_TRANSFER_PAGE_SELECT_TOKEN_NFT = "sendTokenPage_1_4";
        public static final String ENTER_TRANSFER_PAGE = "sendTokenPage_0";
        public static final String ENTER_TRANSFER_PAGE_NFT = "sendTokenPage_1";
    }

    public static class TransferSelectTokenPage {
        public static final String CLICK_MULTI_TRANSFER_SELECT_TOKEN_PAGE_ADD_NOTE = "MultiSelectAddress_3_7";
        public static final String CLICK_MULTI_TRANSFER_SELECT_TOKEN_PAGE_AMOUNT_MAX = "MultiSelectAddress_3_6";
        public static final String CLICK_MULTI_TRANSFER_SELECT_TOKEN_PAGE_BACK = "MultiSelectAddress_3_1";
        public static final String CLICK_MULTI_TRANSFER_SELECT_TOKEN_PAGE_CONFIRM = "MultiSelectAddress_3_2";
        public static final String CLICK_MULTI_TRANSFER_SELECT_TOKEN_PAGE_SEARCH_NFT = "MultiSelectAddress_3_5";
        public static final String CLICK_MULTI_TRANSFER_SELECT_TOKEN_PAGE_SEARCH_TOKEN = "MultiSelectAddress_3_4";
        public static final String CLICK_MULTI_TRANSFER_SELECT_TOKEN_PAGE_SELECT_TOKEN = "MultiSelectAddress_3_3";
        public static final String CLICK_TRANSFER_SELECT_TOKEN_PAGE_ADD_NOTE = "Transfers_2_7";
        public static final String CLICK_TRANSFER_SELECT_TOKEN_PAGE_AMOUNT_MAX = "Transfers_2_6";
        public static final String CLICK_TRANSFER_SELECT_TOKEN_PAGE_BACK = "Transfers_2_1";
        public static final String CLICK_TRANSFER_SELECT_TOKEN_PAGE_CONFIRM = "Transfers_2_2";
        public static final String CLICK_TRANSFER_SELECT_TOKEN_PAGE_SEARCH_NFT = "Transfers_2_5";
        public static final String CLICK_TRANSFER_SELECT_TOKEN_PAGE_SEARCH_TOKEN = "Transfers_2_4";
        public static final String CLICK_TRANSFER_SELECT_TOKEN_PAGE_SELECT_TOKEN = "Transfers_2_3";
        public static final String ENTER_TRANSFER_SELECT_TOKEN_PAGE = "Transfers_2";
    }

    public static class UnStake {
        public static final String CLICK_BACK = "UnstakeTRX_1";
        public static final String CLICK_CONFIRM = "UnstakeTRX_2";
        public static final String CLICK_FOR_OTHER = "UnstakeTRX_4";
        public static final String CLICK_FOR_SELF = "UnstakeTRX_3";
        public static final String START_TAG = "UnstakeTRX_";
    }

    public static class UnStakeGate {
        public static final String BASE_TAG = "UnstakeGate_";
        public static final String CLICK_FROM_RES_MANAGER = "UnstakeGate_2";
        public static final String CLICK_FROM_STAKE = "UnstakeGate_1";
    }

    public static class VoteMainPage {
        public static final int CLICK_CANCEL_ALL = 5;
        public static final int CLICK_MULTI_SIGN = 0;
        public static final int CLICK_PROFIT = 4;
        public static final int CLICK_PROMOTE = 2;
        public static final int CLICK_SORT = 6;
        public static final int CLICK_SORT_ALL_VOTE = 9;
        public static final int CLICK_SORT_APR = 8;
        public static final int CLICK_SORT_FILTER_VOTED = 10;
        public static final int CLICK_SORT_MY_VOTE = 7;
        public static final int CLICK_STAKE = 3;
        public static final int CLICK_VOTE = 1;
        public static final int CLICK_WITNESS_ITEM = 11;
        public static final String ENTER_VOTE_HOME = "votersPage";
        public static final String PARAM_KEY_IS_MULTI = "is_multivoter";
        public static final String PARAM_VALUE_MULTI = "multi";
        public static final String PARAM_VALUE_SINGLE = "single";
    }

    public static class VotePage {
        public static final int CLICK_BACK = 0;
        public static final int CLICK_CLEAR = 3;
        public static final int CLICK_CONFIRM_VOTE = 4;
        public static final int CLICK_INPUT = 1;
        public static final int CLICK_MINUS_PLUS = 2;
        public static final int CLICK_OTHER_SR = 5;
        public static final String COMMON_VOTE_TWO_PAGE = "common_vote_batch_step_two";
        public static final String FAST_VOTE_PAGE = "fast_vote_page";
        public static final String MULTI_SIGN = "_1_";
        public static final String SINGLE_SIGN = "_0_";
        public static final String SINGLE_VOTE_PAGE = "single_vote_page";
    }

    public static class VotePagePop {
        public static final int CLICK_KNOWN = 0;
        public static final int CLICK_UN_STAKE = 1;
        public static final String EMPTY_POP = "votes_count_can_not_be_zero";
    }

    public static class VoteResult {
        public static final String ENTER_VoteResult_CANDEL_FAILED = "voteCancelResultFailed";
        public static final String ENTER_VoteResult_FAILED = "voteResultFailed_show";
        public static final String ENTER_VoteResult_SUCCESS = "voteResultSucceeded_show";
    }

    public static class VoteSelectSR {
        public static final String BASE_MAIN_TAG = "common_vote_batch_step_one";
        public static final int CLICK_BACK = 0;
        public static final int CLICK_CLEAR_ALL = 2;
        public static final int CLICK_NEXT = 3;
        public static final int CLICK_QUICK_VOTE = 4;
        public static final int CLICK_SEARCH = 1;
        public static final String MULTI_SIGN = "common_vote_batch_step_one_1_";
        public static final String SINGLE_SIGN = "common_vote_batch_step_one_0_";
    }

    public static class VotingGate {
        public static final String CLICK_HOME_PAGE_VOTE = "VotingGate_2";
        public static final String CLICK_RESOURCE_PAGE_VOTE = "VotingGate_1";
    }

    public static class WalletManagerPage {
        public static final String CLICK_WALLET_MANAGER_PAGE_ADD_WALLET = "ManageWallet_1";
        public static final String CLICK_WALLET_MANAGER_PAGE_APPROVED = "ManageWallet_12";
        public static final String CLICK_WALLET_MANAGER_PAGE_BACKUP_KEYSTORE = "ManageWallet_11";
        public static final String CLICK_WALLET_MANAGER_PAGE_BACKUP_PRI_KEY = "ManageWallet_10";
        public static final String CLICK_WALLET_MANAGER_PAGE_COPY_ADDRESS = "ManageWallet_5";
        public static final String CLICK_WALLET_MANAGER_PAGE_DELETE = "ManageWallet_13";
        public static final String CLICK_WALLET_MANAGER_PAGE_DISPLAY_ASSET = "ManageWallet_3";
        public static final String CLICK_WALLET_MANAGER_PAGE_EXPAND_WALLETS = "ManageWallet_4";
        public static final String CLICK_WALLET_MANAGER_PAGE_HIDE_ASSET = "ManageWallet_2";
        public static final String CLICK_WALLET_MANAGER_PAGE_MULTI_SIGN = "ManageWallet_7";
        public static final String CLICK_WALLET_MANAGER_PAGE_NON_HD = "ManageWallet_16";
        public static final String CLICK_WALLET_MANAGER_PAGE_OPEN_RELATE = "ManageWallet_14";
        public static final String CLICK_WALLET_MANAGER_PAGE_OPEN_RELATE_HELP = "ManageWallet_1401";
        public static final String CLICK_WALLET_MANAGER_PAGE_PERMISSION = "ManageWallet_6";
        public static final String CLICK_WALLET_MANAGER_PAGE_RELATED_ACCOUNT = "ManageWallet_15";
        public static final String CLICK_WALLET_MANAGER_PAGE_RELATED_ACCOUNT_CLOSE = "ManageWallet_1504";
        public static final String CLICK_WALLET_MANAGER_PAGE_RELATED_ACCOUNT_COPY = "ManageWallet_1503";
        public static final String CLICK_WALLET_MANAGER_PAGE_RELATED_ACCOUNT_GENERATE = "ManageWallet_1502";
        public static final String CLICK_WALLET_MANAGER_PAGE_RELATED_ACCOUNT_HELP = "ManageWallet_1505";
        public static final String CLICK_WALLET_MANAGER_PAGE_RELATED_ACCOUNT_SELECT = "ManageWallet_1501";
        public static final String CLICK_WALLET_MANAGER_PAGE_WALLET_NAME = "ManageWallet_8";
        public static final String CLICK_WALLET_MANAGER_PAGE_WALLET_PASSWORD = "ManageWallet_9";
        public static final String ENTER_WALLET_MANAGER_BACKUP_KEYSTORE_PAGE = "showKstore";
        public static final String ENTER_WALLET_MANAGER_BACKUP_PRI_KEY_PAGE = "showPkey";
        public static final String ENTER_WALLET_MANAGER_CHANGE_NAME_PAGE = "changeWalletName";
        public static final String ENTER_WALLET_MANAGER_CHANGE_PASSWORD_PAGE = "changePW";
        public static final String ENTER_WALLET_MANAGER_MULTI_SIGN_PAGE = "multiSignature";
        public static final String ENTER_WALLET_MANAGER_PAGE = "ManageWallet";
        public static final String ENTER_WALLET_MANAGER_PERMISSION_PAGE = "accessSetting";
        public static final String EVENT_WALLET_MANAGER_DELETE_WALLET = "deleteWallet_%d";
    }

    public static void logEvent(String str) {
        LogUtils.e("logEvent", str);
        logEvent(str, (Bundle) null);
    }

    public static void logEventFormat(String str, Object... objArr) {
        try {
            logEvent(String.format(str, objArr));
        } catch (Throwable th) {
            LogUtils.e(th);
            logEvent(str);
        }
    }

    public static void logEvent(String str, Bundle bundle) {
        logEvent(AppContextUtil.getmApplication(), str, bundle);
    }

    public static void logEvent(Context context, String str) {
        logEvent(context, str, null);
    }

    public static void logEvent(Context context, String str, Bundle bundle) {
        try {
            LogUtils.d("AnalyticsHelper", str);
            FirebaseAnalytics.getInstance(context).logEvent(str, bundle);
        } catch (Throwable th) {
            LogUtils.e(th);
        }
    }

    public static class AssetPage {
        public static final String CLICK_ASSET_PAGE_ADD_ASSET = "asset_%d_17";
        public static final String CLICK_ASSET_PAGE_ADD_WALLET = "asset_%d_2";
        public static final String CLICK_ASSET_PAGE_BANDWIDTH = "asset_%d_8";
        public static final String CLICK_ASSET_PAGE_CHANGE_WALLET = "asset_%d_1";
        public static final String CLICK_ASSET_PAGE_COPY_ADDRESS = "asset_%d_4";
        public static final String CLICK_ASSET_PAGE_DISPLAY_ASSET = "asset_%d_6";
        public static final String CLICK_ASSET_PAGE_DISPLAY_SMALL_BALANCE = "asset_%d_1602";
        public static final String CLICK_ASSET_PAGE_ENERGY = "asset_%d_7";
        public static final String CLICK_ASSET_PAGE_FREEZE = "asset_%d_12";
        public static final String CLICK_ASSET_PAGE_HIDE_ASSET = "asset_%d_5";
        public static final String CLICK_ASSET_PAGE_HIDE_SMALL_BALANCE = "asset_%d_1601";
        public static final String CLICK_ASSET_PAGE_NON_HD = "asset_%d_19";
        public static final String CLICK_ASSET_PAGE_RECEIVE = "asset_%d_10";
        public static final String CLICK_ASSET_PAGE_REMOVE_HOME_ASSET_CANCEL = "asset_%d_1802";
        public static final String CLICK_ASSET_PAGE_REMOVE_HOME_ASSET_CONFIRM = "asset_%d_1801";
        public static final String CLICK_ASSET_PAGE_SCAN = "asset_%d_3";
        public static final String CLICK_ASSET_PAGE_SORT = "asset_%d_16";
        public static final String CLICK_ASSET_PAGE_SORT_MANUAL = "asset_%d_1610";
        public static final String CLICK_ASSET_PAGE_SORT_NAME = "asset_%d_1605";
        public static final String CLICK_ASSET_PAGE_SORT_PREFERENCE = "asset_%d_1603";
        public static final String CLICK_ASSET_PAGE_SORT_VALUE = "asset_%d_1604";
        public static final String CLICK_ASSET_PAGE_SWAP = "asset_%d_11";
        public static final String CLICK_ASSET_PAGE_TAB_ASSET = "asset_%d_14";
        public static final String CLICK_ASSET_PAGE_TAB_COLLECTION = "asset_%d_15";
        public static final String CLICK_ASSET_PAGE_TRANSFER = "asset_%d_9";
        public static final String CLICK_ASSET_PAGE_VOTE = "asset_%d_13";
        public static final String ENTER_ASSET_PAGE_REMOVE_HOME_ASSET = "asset_%d_18";
        public static final String EXIT_ASSET_PAGE_SORT = "asset_%d_1600";
        public static final String SEC_ASK_PAGE_SHOW = "homepage_security_quiz_show";

        public static void logEvent(String str) {
            Object[] objArr = new Object[1];
            objArr[0] = Integer.valueOf(AssetsConfig.isCurrentWalletWatch() ? 2 : 1);
            AnalyticsHelper.logEventFormat(str, objArr);
        }
    }

    public static class AssetsManagerPage {
        public static final String CLICK_ASSETS_MANAGER_PAGE_ADD_ASSET = "myAsset_%d_2";
        public static final String CLICK_ASSETS_MANAGER_PAGE_DELETE_ASSET = "myAsset_%d_3";
        public static final String CLICK_ASSETS_MANAGER_PAGE_DISPLAY_SMALL_ASSET = "myAsset_%d_6";
        public static final String CLICK_ASSETS_MANAGER_PAGE_HIDE_SMALL_ASSET = "myAsset_%d_5";
        public static final String CLICK_ASSETS_MANAGER_PAGE_REMOVE_ASSET = "myAsset_%d_4";
        public static final String CLICK_ASSETS_MANAGER_PAGE_SEARCH = "myAsset_%d_1";
        public static final String ENTER_ASSETS_MANAGER_PAGE = "assetManage";

        public static void logEvent(String str) {
            Object[] objArr = new Object[1];
            objArr[0] = Integer.valueOf(AssetsConfig.isCurrentWalletWatch() ? 2 : 1);
            AnalyticsHelper.logEventFormat(str, objArr);
        }
    }

    public static class UnStakeV2 {
        public static final String BANDWIDTH = "bandwidth";
        public static final String ENERGY = "energy";
        public static final String MANAGE = "manage";
        public static final String UN_STAKE = "unstake";
        public static final String UN_STAKE_V2_PAGE = "unstakepage_";
        public static final String UN_STAKE_V2_SHOW = "unstakepage_show";
        public static final String UN_STAKE_V2_TAB_CLICK = "unstakepage_tab_click_";
        public static final String UN_STAKE_V2_TAB_CLICK_BANDWIDTH = "unstakepage_tab_click_bandwidth";
        public static final String UN_STAKE_V2_TAB_CLICK_ENERGY = "unstakepage_tab_click_energy";

        public static String getLogEventString(ResState resState) {
            return resState == ResState.Energy ? "unstakepage_energy" : resState == ResState.Bandwidth ? "unstakepage_bandwidth" : "";
        }

        public static void logEventUnStakeV2Show(boolean z) {
            Bundle bundle = new Bundle();
            bundle.putString("is_multi", z ? VoteMainPage.PARAM_VALUE_MULTI : VoteMainPage.PARAM_VALUE_SINGLE);
            AnalyticsHelper.logEvent(UN_STAKE_V2_SHOW, bundle);
        }

        public static void logEventUnStakeV2ClickTab(ResState resState) {
            if (resState == ResState.Energy) {
                AnalyticsHelper.logEvent(UN_STAKE_V2_TAB_CLICK_ENERGY);
            } else if (resState == ResState.Bandwidth) {
                AnalyticsHelper.logEvent(UN_STAKE_V2_TAB_CLICK_BANDWIDTH);
            }
        }
    }

    public static class ResourcePageV2 {
        public static final String RESOURCE_BANDWIDTH = "Resource_bandwidth";
        public static final String RESOURCE_BANDWIDTH_AGENCY = "Resource_bandwidth_agency";
        public static final String RESOURCE_BANDWIDTH_GET = "Resource_bandwidth_get";
        public static final String RESOURCE_BANDWIDTH_GIVEME = "Resource_bandwidth_giveme";
        public static final String RESOURCE_BANDWIDTH_HISAGENCY = "Resource_bandwidth_hisagency";
        public static final String RESOURCE_BANDWIDTH_LOOK = "Resource_bandwidth_look";
        public static final String RESOURCE_BANDWIDTH_MYAGENCY = "Resource_bandwidth_myagency";
        public static final String RESOURCE_BANDWIDTH_RECYCLE = "Resource_bandwidth_recycle";
        public static final String RESOURCE_ENERGY = "Resource_energy";
        public static final String RESOURCE_ENERGY_AGENCY = "Resource_energy_agency";
        public static final String RESOURCE_ENERGY_GET = "Resource_energy_get";
        public static final String RESOURCE_ENERGY_GIVEME = "Resource_energy_giveme";
        public static final String RESOURCE_ENERGY_HISAGENCY = "Resource_energy_hisagency";
        public static final String RESOURCE_ENERGY_LOOK = "Resource_energy_look";
        public static final String RESOURCE_ENERGY_MYAGENCY = "Resource_energy_myagency";
        public static final String RESOURCE_ENERGY_RECYCLE = "Resource_energy_recycle";

        public static void logMultiEvent(String str, boolean z) {
            Bundle bundle = new Bundle();
            bundle.putString("is_multi", z ? VoteMainPage.PARAM_VALUE_MULTI : VoteMainPage.PARAM_VALUE_SINGLE);
            AnalyticsHelper.logEvent(str, bundle);
        }
    }

    public static class ResourceDetailPage {
        public static final String RESOURCE_BANDWIDTH_GIVEME_SHOW = "Resource_bandwidth_giveme_show";
        public static final String RESOURCE_BANDWIDTH_HISAGENCY_SHOW = "Resource_bandwidth_hisagency_show";
        public static final String RESOURCE_BANDWIDTH_LOOKPAGE_CLICK_AGENCY = "Resource_bandwidth_lookpage_click_agency";
        public static final String RESOURCE_BANDWIDTH_LOOKPAGE_CLICK_RECYCLE = "Resource_bandwidth_lookpage_click_recycle";
        public static final String RESOURCE_BANDWIDTH_LOOKPAGE_SHOW = "Resource_bandwidth_lookpage_show";
        public static final String RESOURCE_BANDWIDTH_MYAGENCY_POP_SHOW_locked = "Resource_bandwidth_myagency_pop_show_locked";
        public static final String RESOURCE_BANDWIDTH_MYAGENCY_POP_SHOW_recycle = "Resource_bandwidth_myagency_pop_show_recycle";
        public static final String RESOURCE_ENERGY_GIVEME_SHOW = "Resource_energy_giveme_show";
        public static final String RESOURCE_ENERGY_HISAGENCY_SHOW = "Resource_energy_hisagency_show";
        public static final String RESOURCE_ENERGY_LOOKPAGE_CLICK_AGENCY = "Resource_energy_lookpage_click_agency";
        public static final String RESOURCE_ENERGY_LOOKPAGE_CLICK_RECYCLE = "Resource_energy_lookpage_click_recycle";
        public static final String RESOURCE_ENERGY_LOOKPAGE_SHOW = "Resource_energy_lookpage_show";
        public static final String RESOURCE_ENERGY_MYAGENCY_POP_SHOW__locked = "Resource_energy_myagency_pop_show_locked";
        public static final String RESOURCE_ENERGY_MYAGENCY_POP_SHOW_recycle = "Resource_energy_myagency_pop_show_recycle";
        private static final String STAKE2ACTION_BANDWIDTH_CLICK = "stake2action_energy_click_bandwidth_";
        public static final String STAKE2ACTION_BANDWIDTH_CLICK_100 = "stake2action_energy_click_bandwidth_100";
        public static final String STAKE2ACTION_BANDWIDTH_CLICK_25 = "stake2action_energy_click_bandwidth_25";
        public static final String STAKE2ACTION_BANDWIDTH_CLICK_50 = "stake2action_energy_click_bandwidth_50";
        public static final String STAKE2ACTION_BANDWIDTH_CLICK_75 = "stake2action_energy_click_bandwidth_75";
        public static final String STAKE2ACTION_BANDWIDTH_CLICK_BANDWIDTH_MODIFICATION = "stake2action_energy_click_bandwidth_bandwidthmodification";
        public static final String STAKE2ACTION_BANDWIDTH_CLICK_STAKE = "stake2action_energy_click_bandwidth_stake";
        private static final String STAKE2ACTION_ENERGY_CLICK = "stake2action_energy_click_energy_";
        public static final String STAKE2ACTION_ENERGY_CLICK_100 = "stake2action_energy_click_energy_100";
        public static final String STAKE2ACTION_ENERGY_CLICK_25 = "stake2action_energy_click_energy_25";
        public static final String STAKE2ACTION_ENERGY_CLICK_50 = "stake2action_energy_click_energy_50";
        public static final String STAKE2ACTION_ENERGY_CLICK_75 = "stake2action_energy_click_energy_75";
        public static final String STAKE2ACTION_ENERGY_CLICK_ENERGY_MODIFICATION = "stake2action_energy_click_energy_energymodification";
        public static final String STAKE2ACTION_ENERGY_CLICK_STAKE = "stake2action_energy_click_energy_stake";
        public static final String STAKE2ACTION_HEADEDU_CLICK = "stake2action_headedu_click";
        public static final String STAKE2ACTION_HEADEDU_POP_CLICK_KNOW = "stake2action_headedu_pop_click_know";
        public static final String STAKE2ACTION_HEADEDU_POP_SHOW = "stake2action_headedu_pop_show";
        public static final String STAKE2ACTION_PAGESHOW_BANDWIDTH = "stake2action_pageshow_bandwidth";
        public static final String STAKE2ACTION_PAGESHOW_ENENGY = "stake2action_pageshow_energy";

        public static void logMultiEvent(String str, boolean z) {
            Bundle bundle = new Bundle();
            bundle.putString("is_multi", z ? VoteMainPage.PARAM_VALUE_MULTI : VoteMainPage.PARAM_VALUE_SINGLE);
            AnalyticsHelper.logEvent(str, bundle);
        }
    }

    public static class StakeHomePage {
        public static final String STAKE_NO_PAGE_KNOWSTAKE_CLICK = "stake_na_pageclick_knowstake2";
        public static final String STAKE_NO_PAGE_MUL_CLICK = "stake_na_pageclick_multiplesign";
        public static final String STAKE_NO_PAGE_QUIDESTAKE_CLICK = "stake_na_pageclick_guidedstake";
        public static final String STAKE_NO_PAGE_SHOW = "stake_na_pageshow";
        public static final String STAKE_V1_PAGE_GOVOTE_CLICK_ = "stake1_pageclick_govote";
        public static final String STAKE_V1_PAGE_RESOURCE_CLICK = "stake1_pageclick_Resource";
        public static final String STAKE_V1_PAGE_SHOW = "stake1_pageshow";
        public static final String STAKE_V1_PAGE_STAKE_CLICK_ = "stake1_pageclick_Stake";
        public static final String STAKE_V1_PAGE_UNSTAKE_CLICK_ = "stake1_pageclick_Unstake";
        public static final String STAKE_V1_PAGE_VOTE_CLICK_ = "stake1_pageclick_Vote";
        public static final String STAKE_V1_UNSTAKE_MY_BAND_POP_CLICK = "stake1_unstake_click_mybandwidth";
        public static final String STAKE_V1_UNSTAKE_MY_ENERGY_POP_CLICK = "stake1_unstake_click_myenergy";
        public static final String STAKE_V1_UNSTAKE_OTHERS_BAND_POP_CLICK = "stake1_unstake_click_hisbandwidth";
        public static final String STAKE_V1_UNSTAKE_OTHERS_ENERGY_POP_CLICK = "stake1_unstake_click_hisenergy";
        public static final String STAKE_V1_UNSTAKE_POP_SHOW = "stake1_unstake_show";
        public static final String STAKE_V1_V2_PAGE_GOVOTE_CLICK = "stake1_2_pageclick_govote";
        public static final String STAKE_V1_V2_PAGE_KNOW_STAKE_V2_CLICK = "stake1_2_pageclick_knowstake2";
        public static final String STAKE_V1_V2_PAGE_MUl_CLICK = "stake1_2_pageclick_multiplesign";
        public static final String STAKE_V1_V2_PAGE_RESOURCE_CLICK = "stake1_2_pageclick_Resource";
        public static final String STAKE_V1_V2_PAGE_SHOW = "stake1_2_pageshow";
        public static final String STAKE_V1_V2_PAGE_STAKE_CLICK = "stake1_2_pageclick_stake";
        public static final String STAKE_V1_V2_PAGE_STAKE_V1_CLICK = "stake1_2_pageclick_stake1";
        public static final String STAKE_V1_V2_PAGE_UNFREEZEING_CLICK = "stake1_2_pageclick_unlocking";
        public static final String STAKE_V1_V2_PAGE_UNSTAKE_CLICK = "stake1_2_pageclick_unstake";
        public static final String STAKE_V1_V2_PAGE_VOTE_CLICK = "stake1_2_pageclick_Vote";
        public static final String STAKE_V1_V2_PAGE_WITHDRAW_CLICK = "stake1_2_pageclick_withdrawal";
        public static final String STAKE_V1_V2_POP_SHOW = "stake1_2_unstakepop_show";
        public static final String STAKE_V1_V2_POP_V1_CLICK = "stake1_2_unstakepop_click_1";
        public static final String STAKE_V1_V2_POP_V2_CLICK = "stake1_2_unstakepop_click_2";
        public static final String STAKE_V2_PAGE_GOVOTE_CLICK = "stake2_pageclick_govote";
        public static final String STAKE_V2_PAGE_KNOW_STAKE_V2_CLICK = "stake2_pageclick_knowstake2";
        public static final String STAKE_V2_PAGE_MUl_CLICK = "stake2_pageclick_multiplesign";
        public static final String STAKE_V2_PAGE_RESOURCE_CLICK = "stake2_pageclick_Resource";
        public static final String STAKE_V2_PAGE_SHOW = "stake2_pageshow";
        public static final String STAKE_V2_PAGE_STAKE_CLICK = "stake2_pageclick_stake";
        public static final String STAKE_V2_PAGE_UNFREEZEING_CLICK = "stake2_pageclick_unlocking";
        public static final String STAKE_V2_PAGE_UNSTAKE_CLICK = "stake2_pageclick_unstake";
        public static final String STAKE_V2_PAGE_VOTE_CLICK = "stake2_pageclick_Vote";
        public static final String STAKE_V2_PAGE_WITHDRAW_CLICK = "stake2_pageclick_withdrawal";
        public static final String STAKE_V2_POP_FIRST_SHOW = "stake2_pop_show";
        public static final String STAKE_V2_POP_KNOW_CLICK = "stake2_pop_know";
        public static final String STAKE_V2_POP_MORE_CLICK = "stake2_pop_more";

        public static void logMultiEvent(String str, boolean z) {
            Bundle bundle = new Bundle();
            bundle.putString("is_multi", z ? VoteMainPage.PARAM_VALUE_MULTI : VoteMainPage.PARAM_VALUE_SINGLE);
            AnalyticsHelper.logEvent(str, bundle);
        }
    }

    public static class ResourceDelegatePage {
        public static final String DELEGATE_BANDWIDTH_CLICK_ACCOUNT = "Bandwidth_agency1_click_My_account";
        public static final String DELEGATE_BANDWIDTH_CLICK_BOOK = "Bandwidth_agency1_click_Address_book";
        public static final String DELEGATE_BANDWIDTH_CLICK_NEXT = "Bandwidth_agency1_click_next";
        public static final String DELEGATE_BANDWIDTH_CLICK_PASTE = "Bandwidth_agency1_click_paste";
        public static final String DELEGATE_BANDWIDTH_CLICK_RECENT = "Bandwidth_agency1_click_Recentagency";
        public static final String DELEGATE_BANDWIDTH_CLICK_SCAN = "Bandwidth_agency1_click_scan";
        public static final String DELEGATE_BAND_2_CLICK_100 = "Bandwidth_agency2_click_100";
        public static final String DELEGATE_BAND_2_CLICK_25 = "Bandwidth_agency2_click_25";
        public static final String DELEGATE_BAND_2_CLICK_50 = "Bandwidth_agency2_click_50";
        public static final String DELEGATE_BAND_2_CLICK_75 = "Bandwidth_agency2_click_75";
        public static final String DELEGATE_BAND_2_CLICK_BAND = "Bandwidth_agency2_click_energy";
        public static final String DELEGATE_BAND_2_CLICK_LOCK = "Bandwidth_agency2_click_proxylocking";
        public static final String DELEGATE_BAND_2_CLICK_NEXT = "Bandwidth_agency2_click_next";
        public static final String DELEGATE_BAND_2_CLICK_TRX = "Bandwidth_agency2_click_TRX";
        public static final String DELEGATE_BAND_WIDTH_1_SHOW = "Bandwidth_agency1_show";
        public static final String DELEGATE_BAND_WIDTH_2_SHOW = "Bandwidth_agency2_show";
        public static final String DELEGATE_ENERGY_1_SHOW = "Energy_agency1_show";
        public static final String DELEGATE_ENERGY_2_CLICK_100 = "Energy_agency2_click_100";
        public static final String DELEGATE_ENERGY_2_CLICK_25 = "Energy_agency2_click_25";
        public static final String DELEGATE_ENERGY_2_CLICK_50 = "Energy_agency2_click_50";
        public static final String DELEGATE_ENERGY_2_CLICK_75 = "Energy_agency2_click_75";
        public static final String DELEGATE_ENERGY_2_CLICK_ENERGY = "Energy_agency2_click_energy";
        public static final String DELEGATE_ENERGY_2_CLICK_LOCK = "Energy_agency2_click_proxylocking";
        public static final String DELEGATE_ENERGY_2_CLICK_NEXT = "Energy_agency2_click_next";
        public static final String DELEGATE_ENERGY_2_CLICK_TRX = "Energy_agency2_click_TRX";
        public static final String DELEGATE_ENERGY_2_SHOW = "Energy_agency2_show";
        public static final String DELEGATE_ENERGY_CLICK_ACCOUNT = "Energy_agency1_click_My_account";
        public static final String DELEGATE_ENERGY_CLICK_BOOK = "Energy_agency1_click_Address_book";
        public static final String DELEGATE_ENERGY_CLICK_NEXT = "Energy_agency1_click_next";
        public static final String DELEGATE_ENERGY_CLICK_PASTE = "Energy_agency1_click_paste";
        public static final String DELEGATE_ENERGY_CLICK_RECENT = "Energy_agency1_click_Recentagency";
        public static final String DELEGATE_ENERGY_CLICK_SCAN = "Energy_agency1_click_scan";

        public static void logMultiEvent(String str, boolean z) {
            Bundle bundle = new Bundle();
            bundle.putString("is_multi", z ? VoteMainPage.PARAM_VALUE_MULTI : VoteMainPage.PARAM_VALUE_SINGLE);
            AnalyticsHelper.logEvent(str, bundle);
        }
    }

    public static class TransactionReporting {
        public static final String TRANSACTION_CONSUMPTION_APP = "Transaction_consumption_1";
        public static final String TRANSACTION_CONSUMPTION_BASE = "Transaction_consumption";
        public static final String TRANSACTION_CONSUMPTION_DApp = "Transaction_consumption_2";
        public static final String TRANSACTION_NUMBER_APP = "transaction_number_1";
        public static final String TRANSACTION_NUMBER_BASE = "transaction_number";
        public static final String TRANSACTION_NUMBER_DApp = "transaction_number_2";

        public static void TransactionReportingApp(Protocol.Transaction transaction, boolean z) {
            String str;
            if (!IRequest.isRelease() || transaction == null) {
                return;
            }
            Bundle bundle = new Bundle();
            try {
                String hash = TransactionUtils.getHash(transaction);
                Protocol.Transaction.Contract.ContractType type = transaction.getRawData().getContract(0).getType();
                bundle.putDouble("consume_TRX_number", FeeReporting.getFee(hash));
                String str2 = "_" + type.getNumber();
                if (z) {
                    str = TRANSACTION_NUMBER_DApp + str2;
                } else {
                    str = TRANSACTION_NUMBER_APP + str2;
                }
                String str3 = z ? TRANSACTION_CONSUMPTION_DApp : TRANSACTION_CONSUMPTION_APP;
                AnalyticsHelper.logEvent(str, bundle);
                AnalyticsHelper.logEvent(str3, bundle);
                FeeReporting.removeItem(hash);
                LogUtils.i("TransactionReportingApp:" + hash);
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
    }
}
