package com.tron.tron_base.frame.net;

import com.samsung.android.sdk.coldwallet.Params;
import com.tron.tron_base.R;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.SpUtils;
import com.tron.tron_base.frame.utils.StringTronUtil;
import com.tron.wallet.business.security.approvecheck.ApprovedListFragment;
public class HostUtil {
    public static final int DEFAULT_VERSION;
    public static final IRequest.NET_ENVIRONMENT ENVIRONMENT;
    public static String tronscanBaseUrl = "";
    public static String tronscanDappBaseUrl = "";

    static {
        int intValue = ((Integer) SpUtils.getParam("f_Tron", AppContextUtil.getContext(), AppContextUtil.getContext().getString(R.string.test_version_key), 1)).intValue();
        DEFAULT_VERSION = intValue;
        if (intValue == 1) {
            ENVIRONMENT = IRequest.NET_ENVIRONMENT.RELEASE;
        } else if (intValue == 2) {
            ENVIRONMENT = IRequest.NET_ENVIRONMENT.PRE_RELEASE;
        } else if (intValue == 3) {
            ENVIRONMENT = IRequest.NET_ENVIRONMENT.TEST;
        } else if (intValue == 4) {
            ENVIRONMENT = IRequest.NET_ENVIRONMENT.NILETEST;
        } else if (intValue == 5) {
            ENVIRONMENT = IRequest.NET_ENVIRONMENT.SHASTA;
        } else {
            ENVIRONMENT = IRequest.NET_ENVIRONMENT.RELEASE;
        }
    }

    public static String getSocketHost() {
        IRequest.NET_ENVIRONMENT net_environment = ENVIRONMENT;
        if (net_environment == IRequest.NET_ENVIRONMENT.RELEASE) {
            if (((Integer) SpUtils.getParam("f_Tron", AppContextUtil.getContext(), "server_user_prefer", 0)).intValue() == 1) {
                return IRequestConstant.SOCKET_HOST_VPN;
            }
            return IRequestConstant.SOCKET_HOST;
        } else if (net_environment == IRequest.NET_ENVIRONMENT.NILETEST) {
            if (((Integer) SpUtils.getParam("f_Tron", AppContextUtil.getContext(), "server_user_prefer", 0)).intValue() == 1) {
                return IRequestConstant.SOCKET_HOST_VPN_NILE;
            }
            return IRequestConstant.SOCKET_NILE_TEST;
        } else if (net_environment == IRequest.NET_ENVIRONMENT.PRE_RELEASE) {
            return IRequestConstant.SOCKET_PRE;
        } else {
            if (net_environment == IRequest.NET_ENVIRONMENT.SHASTA) {
                return IRequestConstant.SOCKET_SHASTA_TEST;
            }
            int intValue = ((Integer) SpUtils.getParam("f_Tron", AppContextUtil.getContext(), AppContextUtil.getContext().getString(R.string.test_version_key), Integer.valueOf(DEFAULT_VERSION))).intValue();
            return intValue > 2 ? intValue == 4 ? IRequestConstant.SOCKET_NILE_TEST : IRequestConstant.SOCKET_TEST : intValue == 1 ? IRequestConstant.SOCKET_HOST : IRequestConstant.SOCKET_PRE;
        }
    }

    public static String getFinancialHost() {
        IRequest.NET_ENVIRONMENT net_environment = ENVIRONMENT;
        if (net_environment == IRequest.NET_ENVIRONMENT.RELEASE) {
            if (((Integer) SpUtils.getParam("f_Tron", AppContextUtil.getContext(), "server_user_prefer", 0)).intValue() == 1) {
                return IRequestConstant.FINANCIAL_HOST_VPN;
            }
            return IRequestConstant.FINANCIAL_HOST;
        } else if (net_environment == IRequest.NET_ENVIRONMENT.TEST) {
            if (((Integer) SpUtils.getParam("f_Tron", AppContextUtil.getContext(), "server_user_prefer", 0)).intValue() == 1) {
                return IRequestConstant.FINANCIAL_HOST_NILE;
            }
            return IRequestConstant.FINANCIAL_HOST_NILE;
        } else {
            return IRequestConstant.FINANCIAL_HOST;
        }
    }

    public static String getWebSocketHost() {
        IRequest.NET_ENVIRONMENT net_environment = ENVIRONMENT;
        if (net_environment == IRequest.NET_ENVIRONMENT.RELEASE) {
            if (((Integer) SpUtils.getParam("f_Tron", AppContextUtil.getContext(), "server_user_prefer", 0)).intValue() == 1) {
                return IRequestConstant.SOCKET_HOST_VPN;
            }
            return IRequestConstant.SOCKET_HOST_WSS;
        } else if (net_environment == IRequest.NET_ENVIRONMENT.NILETEST) {
            if (((Integer) SpUtils.getParam("f_Tron", AppContextUtil.getContext(), "server_user_prefer", 0)).intValue() == 1) {
                return IRequestConstant.SOCKET_HOST_VPN_NILE;
            }
            return IRequestConstant.SOCKET_NILE_TEST_WSS;
        } else if (net_environment == IRequest.NET_ENVIRONMENT.PRE_RELEASE) {
            return IRequestConstant.SOCKET_PRE_WSS;
        } else {
            if (net_environment == IRequest.NET_ENVIRONMENT.SHASTA) {
                return IRequestConstant.SOCKET_SHASTA_TEST_WSS;
            }
            int intValue = ((Integer) SpUtils.getParam("f_Tron", AppContextUtil.getContext(), AppContextUtil.getContext().getString(R.string.test_version_key), Integer.valueOf(DEFAULT_VERSION))).intValue();
            return intValue > 2 ? intValue == 4 ? IRequestConstant.SOCKET_NILE_TEST_WSS : IRequestConstant.SOCKET_TEST_WSS : intValue == 1 ? IRequestConstant.SOCKET_HOST_WSS : IRequestConstant.SOCKET_PRE_WSS;
        }
    }

    public static String getTronLinkHost() {
        return getHost();
    }

    static class fun1 {
        static final int[] $SwitchMap$com$tron$tron_base$frame$net$IRequest$NET_ENVIRONMENT;

        static {
            int[] iArr = new int[IRequest.NET_ENVIRONMENT.values().length];
            $SwitchMap$com$tron$tron_base$frame$net$IRequest$NET_ENVIRONMENT = iArr;
            try {
                iArr[IRequest.NET_ENVIRONMENT.PRE_RELEASE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tron$tron_base$frame$net$IRequest$NET_ENVIRONMENT[IRequest.NET_ENVIRONMENT.RELEASE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tron$tron_base$frame$net$IRequest$NET_ENVIRONMENT[IRequest.NET_ENVIRONMENT.NILETEST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$tron$tron_base$frame$net$IRequest$NET_ENVIRONMENT[IRequest.NET_ENVIRONMENT.TEST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$tron$tron_base$frame$net$IRequest$NET_ENVIRONMENT[IRequest.NET_ENVIRONMENT.SHASTA.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public static String getShieldNoteUrl() {
        int i = fun1.$SwitchMap$com$tron$tron_base$frame$net$IRequest$NET_ENVIRONMENT[ENVIRONMENT.ordinal()];
        if (i == 1 || i == 2) {
            return IRequestConstant.WebSocket_SHIELD_NOTE_ONLINE;
        }
        if (i == 3) {
            return IRequestConstant.WebSocket_SHIELD_NOTE_NILETEST;
        }
        return IRequestConstant.WebSocket_SHIELD_NOTE_SHASTATEST;
    }

    public static String getTronScanMainRelease() {
        if (tronscanBaseUrl.length() > 0) {
            String str = tronscanBaseUrl + "/#/transaction/";
            LogUtils.d("alex", "MainChain url " + str);
            return str;
        }
        LogUtils.d("alex", "MainChain url " + IRequestConstant.TRONSCAN_MAINCHAIN_RELEASE);
        return IRequestConstant.TRONSCAN_MAINCHAIN_RELEASE;
    }

    public static String getTronScanDappChainRelease() {
        if (tronscanBaseUrl.length() > 0) {
            String str = tronscanBaseUrl + "/#/transaction/";
            LogUtils.d("alex", "DappChain url " + str);
            return str;
        }
        LogUtils.d("alex", "DappChain url " + IRequestConstant.TRONSCAN_DAPPCHAIN_RELEASE);
        return IRequestConstant.TRONSCAN_DAPPCHAIN_RELEASE;
    }

    public static String getTronScanMainContract() {
        if (tronscanBaseUrl.length() > 0) {
            return tronscanBaseUrl + "/#/contract/";
        }
        return IRequestConstant.TRONSCAN_MAINCHAIN_CONTRACT;
    }

    public static String getTronScanDappContract() {
        if (tronscanDappBaseUrl.length() > 0) {
            return tronscanDappBaseUrl + "/#/contract/";
        }
        return IRequestConstant.TRONSCAN_DAPPCHAIN_CONTRACT;
    }

    public static String getTronScanApproveAll() {
        if (tronscanBaseUrl.length() > 0) {
            return tronscanBaseUrl + "/#/address/";
        }
        return IRequestConstant.TRONSCAN_MAINCHAIN_CONTRACT;
    }

    public static String getTronScanDappApproveAll() {
        if (tronscanDappBaseUrl.length() > 0) {
            return tronscanDappBaseUrl + "/#/address/";
        }
        return IRequestConstant.TRONSCAN_DAPPCHAIN_CONTRACT;
    }

    public static String getTronScanTokenReport() {
        if (tronscanBaseUrl.length() > 0) {
            return tronscanBaseUrl + "/#/tools/contactUs?feedbackType=risk&source=token";
        }
        return null;
    }

    public static String getTronScanMainToken10() {
        if (tronscanBaseUrl.length() > 0) {
            return tronscanBaseUrl + "/#/token/";
        }
        return IRequestConstant.TRONSCAN_MAINCHAIN_TOEKN10_CONTRACT;
    }

    public static String getTronScanDappToken10() {
        if (tronscanDappBaseUrl.length() > 0) {
            return tronscanDappBaseUrl + "/#/token/";
        }
        return IRequestConstant.TRONSCAN_DAPPCHAIN_TOEKN10_CONTRACT;
    }

    public static String getTronScanMainToken20() {
        if (tronscanBaseUrl.length() > 0) {
            return tronscanBaseUrl + "/#/token20/";
        }
        return IRequestConstant.TRONSCAN_MAINCHAIN_TOEKN20_CONTRACT;
    }

    public static String getTronScanDAPPToken20() {
        if (tronscanDappBaseUrl.length() > 0) {
            return tronscanDappBaseUrl + "/#/token20/";
        }
        return IRequestConstant.TRONSCAN_DAPPCHAIN_TOEKN10_CONTRACT;
    }

    protected static String getHost() {
        String str;
        int intValue = ((Integer) SpUtils.getParam("f_Tron", AppContextUtil.getContext(), "server_user_prefer", 0)).intValue();
        IRequest.NET_ENVIRONMENT net_environment = ENVIRONMENT;
        if (net_environment == IRequest.NET_ENVIRONMENT.RELEASE) {
            if (intValue == 0) {
                return IRequestConstant.HOME_HOST;
            }
            return IRequestConstant.HOME_HOST_VPN;
        } else if (net_environment == IRequest.NET_ENVIRONMENT.NILETEST) {
            if (intValue == 0) {
                return IRequestConstant.NILETEST_HOST;
            }
            return IRequestConstant.HOME_HOST_VPN_NILE;
        } else if (net_environment == IRequest.NET_ENVIRONMENT.PRE_RELEASE) {
            return IRequestConstant.PRE_HOST;
        } else {
            if (net_environment == IRequest.NET_ENVIRONMENT.SHASTA) {
                return IRequestConstant.HOME_SHASTA_HOST;
            }
            if (net_environment == IRequest.NET_ENVIRONMENT.TEST) {
                return IRequestConstant.TEST_HOST;
            }
            int intValue2 = ((Integer) SpUtils.getParam("f_Tron", AppContextUtil.getContext(), AppContextUtil.getContext().getString(R.string.test_version_key), Integer.valueOf(DEFAULT_VERSION))).intValue();
            String str2 = IRequestConstant.HOME_HOST;
            if (intValue == 0) {
                str = IRequestConstant.HOME_HOST;
            } else {
                str = IRequestConstant.HOME_HOST_VPN;
            }
            return intValue2 > 2 ? intValue2 > 3 ? IRequestConstant.NILETEST_HOST : IRequestConstant.TEST_HOST : intValue2 == 1 ? str : IRequestConstant.PRE_HOST;
        }
    }

    public static String getDappUrl() {
        IRequest.NET_ENVIRONMENT net_environment = ENVIRONMENT;
        if (net_environment == IRequest.NET_ENVIRONMENT.RELEASE) {
            return IRequestConstant.DAPP_RELEASE;
        }
        if (net_environment == IRequest.NET_ENVIRONMENT.PRE_RELEASE) {
            return IRequestConstant.DAPP_TEST;
        }
        return ((Integer) SpUtils.getParam("f_Tron", AppContextUtil.getContext(), AppContextUtil.getContext().getString(R.string.test_version_key), Integer.valueOf(DEFAULT_VERSION))).intValue() == 1 ? IRequestConstant.DAPP_RELEASE : IRequestConstant.DAPP_TEST;
    }

    public static String getContractUrl(String str) {
        IRequest.NET_ENVIRONMENT net_environment = ENVIRONMENT;
        if (net_environment == IRequest.NET_ENVIRONMENT.NILETEST) {
            return "https://nile.tronscan.org/#/contract/" + str;
        } else if (net_environment == IRequest.NET_ENVIRONMENT.RELEASE) {
            return "https://tronscan.org/#/contract/" + str;
        } else {
            return "";
        }
    }

    public static String getAccountUrl(String str) {
        IRequest.NET_ENVIRONMENT net_environment = ENVIRONMENT;
        if (net_environment == IRequest.NET_ENVIRONMENT.NILETEST || net_environment == IRequest.NET_ENVIRONMENT.TEST) {
            return "https://nile.tronscan.org/#/address/" + str;
        } else if (net_environment == IRequest.NET_ENVIRONMENT.SHASTA) {
            return "https://shasta.tronscan.org/#/address/" + str;
        } else if (net_environment == IRequest.NET_ENVIRONMENT.RELEASE || net_environment == IRequest.NET_ENVIRONMENT.PRE_RELEASE) {
            return "https://tronscan.org/#/address/" + str;
        } else {
            return "";
        }
    }

    public static String getTRC10Url(String str) {
        IRequest.NET_ENVIRONMENT net_environment = ENVIRONMENT;
        if (net_environment == IRequest.NET_ENVIRONMENT.NILETEST || net_environment == IRequest.NET_ENVIRONMENT.TEST) {
            return "https://nile.tronscan.org/#/token/" + str;
        } else if (net_environment == IRequest.NET_ENVIRONMENT.SHASTA) {
            return "https://shasta.tronscan.org/#/token/" + str;
        } else if (net_environment == IRequest.NET_ENVIRONMENT.RELEASE || net_environment == IRequest.NET_ENVIRONMENT.PRE_RELEASE) {
            return "https://tronscan.org/#/token/" + str;
        } else {
            return "";
        }
    }

    public static String getTronscanDappUrl() {
        IRequest.NET_ENVIRONMENT net_environment = ENVIRONMENT;
        if (net_environment == IRequest.NET_ENVIRONMENT.RELEASE) {
            return getTronScanDappChainRelease();
        }
        if (net_environment == IRequest.NET_ENVIRONMENT.PRE_RELEASE) {
            return IRequestConstant.TRONSCAN_HOST_DAPPCHAIN_PRE;
        }
        int intValue = ((Integer) SpUtils.getParam("f_Tron", AppContextUtil.getContext(), AppContextUtil.getContext().getString(R.string.test_version_key), Integer.valueOf(DEFAULT_VERSION))).intValue();
        if (intValue > 2) {
            return IRequestConstant.TRONSCAN_HOST_MAINCHAIN_NILE;
        }
        return intValue == 1 ? getTronScanDappChainRelease() : IRequestConstant.TRONSCAN_HOST_DAPPCHAIN_PRE;
    }

    public static String getTronscanMainUrl() {
        if (IRequest.isRelease() || IRequest.isPrerelease()) {
            return getTronScanMainRelease();
        }
        if (IRequest.isShasta()) {
            return IRequestConstant.TRONSCAN_HOST_MAINCHAIN_SHASTA;
        }
        return IRequestConstant.TRONSCAN_HOST_MAINCHAIN_NILE;
    }

    public static String getTronscan20TokenIntroduceUrl() {
        IRequest.NET_ENVIRONMENT net_environment = ENVIRONMENT;
        return (net_environment == IRequest.NET_ENVIRONMENT.RELEASE || net_environment == IRequest.NET_ENVIRONMENT.PRE_RELEASE) ? IRequestConstant.TOKEN_20_INTRODUCE_URL : (net_environment == IRequest.NET_ENVIRONMENT.NILETEST || net_environment == IRequest.NET_ENVIRONMENT.TEST) ? IRequestConstant.TOKEN_20_INTRODUCE_URL_NILE : net_environment == IRequest.NET_ENVIRONMENT.SHASTA ? IRequestConstant.TOKEN_20_INTRODUCE_URL_SHASTA : "";
    }

    public static String getTokenRecordUrl() {
        IRequest.NET_ENVIRONMENT net_environment = ENVIRONMENT;
        return net_environment == IRequest.NET_ENVIRONMENT.RELEASE ? IRequestConstant.TOKEN_RECORD_URL : net_environment == IRequest.NET_ENVIRONMENT.NILETEST ? IRequestConstant.TOKEN_RECORD_NILE : "";
    }

    public static String getApproveListUrl(String str) {
        IRequest.NET_ENVIRONMENT net_environment = ENVIRONMENT;
        return (net_environment == IRequest.NET_ENVIRONMENT.RELEASE || net_environment == IRequest.NET_ENVIRONMENT.PRE_RELEASE) ? String.format(IRequestConstant.APPROVE_M_MAIN_CHAIN_RELEASE, str) : (net_environment == IRequest.NET_ENVIRONMENT.NILETEST || net_environment == IRequest.NET_ENVIRONMENT.TEST) ? String.format(IRequestConstant.APPROVE_N_NILE_HAIN_RELEASE, str) : net_environment == IRequest.NET_ENVIRONMENT.SHASTA ? String.format(IRequestConstant.APPROVE_N_NILE_HAIN_SHASTA, str) : "";
    }

    public static String getShareUrl() {
        IRequest.NET_ENVIRONMENT net_environment = ENVIRONMENT;
        if (net_environment == IRequest.NET_ENVIRONMENT.RELEASE || net_environment == IRequest.NET_ENVIRONMENT.PRE_RELEASE) {
            return IRequestConstant.SHARE_BASE_URL;
        }
        return IRequestConstant.SHARE_BASE_URL_TEST;
    }

    public static String getMultiSignTransferHelpUrl() {
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return IRequestConstant.Multi_Sign_Help_Transfer_ZH;
        }
        return IRequestConstant.Multi_Sign_Help_Transfer_EN;
    }

    public static String getDappReportUrl() {
        IRequest.NET_ENVIRONMENT net_environment = ENVIRONMENT;
        if (net_environment == IRequest.NET_ENVIRONMENT.RELEASE || net_environment == IRequest.NET_ENVIRONMENT.PRE_RELEASE) {
            return IRequestConstant.DAPP_REPORT_URL;
        }
        return IRequestConstant.DAPP_REPORT_URL_NILE;
    }

    public static boolean isDAppReportUrl(String str) {
        try {
            if (StringTronUtil.getHost(str).startsWith(StringTronUtil.getHost(getDappReportUrl()))) {
                return str.endsWith("report");
            }
            return false;
        } catch (Exception e) {
            LogUtils.e(e);
            return false;
        }
    }

    public static String getMultiSignStakeHelpUrl() {
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return IRequestConstant.Multi_Sign_Help_Stake_ZH;
        }
        return IRequestConstant.Multi_Sign_Help_Stake_EN;
    }

    public static String getMultiSignUnStakeHelpUrl() {
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return IRequestConstant.Multi_Sign_Help_UnStake_ZH;
        }
        return IRequestConstant.Multi_Sign_Help_UnStake_EN;
    }

    public static String getAboutStakeV2HelpUrl() {
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return IRequestConstant.Help_StakeV2_About_ZH;
        }
        return IRequestConstant.Help_StakeV2_About_EN;
    }

    public static String getMultiSignStakeV2HelpUrl() {
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return IRequestConstant.Multi_Sign_Help_StakeV2_Stake_ZH;
        }
        return IRequestConstant.Multi_Sign_Help_StakeV2_Stake_EN;
    }

    public static String getMultiSignStakeV2ResourcesHelpUrl() {
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return IRequestConstant.Multi_Sign_Help_StakeV2_Resources_ZH;
        }
        return IRequestConstant.Multi_Sign_Help_StakeV2_Resources_EN;
    }

    public static String getMultiSignVoteHelpUrl() {
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return IRequestConstant.Multi_Sign_Help_Vote_ZH;
        }
        return IRequestConstant.Multi_Sign_Help_Vote_EN;
    }

    public static String getTronscanSearchUrl(String str, int i) {
        String str2 = i != 0 ? i != 1 ? i != 2 ? "address" : Params.EXTRAS_KEY_TRANSACTION : "contract" : ApprovedListFragment.APPROVE_ADDRESS_TYPE_ACCOUNT;
        IRequest.NET_ENVIRONMENT net_environment = ENVIRONMENT;
        if (net_environment == IRequest.NET_ENVIRONMENT.NILETEST) {
            return "https://nile.tronscan.org/#/" + str2 + "/" + str;
        } else if (net_environment == IRequest.NET_ENVIRONMENT.RELEASE) {
            return "https://tronscan.org/#/" + str2 + "/" + str;
        } else {
            return "";
        }
    }

    public static String getQueryAccountResMessageAddress() {
        IRequest.NET_ENVIRONMENT net_environment = ENVIRONMENT;
        if (net_environment == IRequest.NET_ENVIRONMENT.SHASTA) {
            return IRequestConstant.ShastaNet_Query_Address;
        }
        if (net_environment == IRequest.NET_ENVIRONMENT.NILETEST || net_environment == IRequest.NET_ENVIRONMENT.TEST) {
            return IRequestConstant.NileNet_Query_Address;
        }
        return IRequestConstant.MainNet_Query_Address;
    }

    public static String getFileNaneWithChain(String str) {
        IRequest.NET_ENVIRONMENT net_environment = ENVIRONMENT;
        if (net_environment == IRequest.NET_ENVIRONMENT.SHASTA) {
            return str + "_shasta";
        } else if (net_environment == IRequest.NET_ENVIRONMENT.NILETEST || net_environment == IRequest.NET_ENVIRONMENT.TEST) {
            return str + "_nile";
        } else {
            return str + "_mainNet";
        }
    }

    public static String getGoToJustLendUrl() {
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            IRequest.NET_ENVIRONMENT net_environment = ENVIRONMENT;
            return (net_environment == IRequest.NET_ENVIRONMENT.RELEASE || net_environment == IRequest.NET_ENVIRONMENT.PRE_RELEASE) ? IRequestConstant.GO_TO_JUSTLEND_DAO_ZH : IRequestConstant.GO_TO_JUSTLEND_DAO_ZH_NILE;
        }
        IRequest.NET_ENVIRONMENT net_environment2 = ENVIRONMENT;
        return (net_environment2 == IRequest.NET_ENVIRONMENT.RELEASE || net_environment2 == IRequest.NET_ENVIRONMENT.PRE_RELEASE) ? IRequestConstant.GO_TO_JUSTLEND_DAO_EN : IRequestConstant.GO_TO_JUSTLEND_DAO_EN_NILE;
    }

    public static String getSecAskUrl() {
        if (IRequest.isTest()) {
            return IRequestConstant.GO_TO_SEC_ASK_TEST;
        }
        return IRequestConstant.GO_TO_SEC_ASK;
    }

    public static String getWalletGuideUrl() {
        if (IRequest.isTest()) {
            return IRequestConstant.GO_TO_WALLET_GUIDE_TEST;
        }
        return IRequestConstant.GO_TO_WALLET_GUIDE;
    }

    public static String getLearnHDUrl() {
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return IRequestConstant.GO_TO_LEARN_HD_ZH;
        }
        return IRequestConstant.GO_TO_LEARN_HD_EN;
    }

    public static String getLearnRiskTronscanUrl(String str) {
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return IRequestConstant.GO_TO_LEARN_HD_ZH;
        }
        return IRequestConstant.GO_TO_LEARN_HD_EN;
    }

    public static String getTronscanAprUrl(String str) {
        IRequest.NET_ENVIRONMENT net_environment = ENVIRONMENT;
        return (net_environment == IRequest.NET_ENVIRONMENT.NILETEST || net_environment == IRequest.NET_ENVIRONMENT.TEST) ? String.format(IRequestConstant.NILE_NET_APR_URL, str) : net_environment == IRequest.NET_ENVIRONMENT.SHASTA ? String.format(IRequestConstant.SHASTA_NET_APR_URL, str) : String.format(IRequestConstant.MAIN_NET_APR_URL, str);
    }

    public static String getHowToBookDUrl() {
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return IRequestConstant.GO_TO_HOW_TO_BOOK_ZH;
        }
        return IRequestConstant.GO_TO_HOW_TO_BOOK_EN;
    }

    public static String getProposalsUrl() {
        IRequest.NET_ENVIRONMENT net_environment = ENVIRONMENT;
        if (net_environment == IRequest.NET_ENVIRONMENT.RELEASE || net_environment == IRequest.NET_ENVIRONMENT.PRE_RELEASE) {
            return IRequestConstant.MAIN_NET_PROPOSALS_URL;
        }
        if (net_environment == IRequest.NET_ENVIRONMENT.SHASTA) {
            return IRequestConstant.SHASTA_NET_PROPOSALS_URL;
        }
        return IRequestConstant.NILE_NET_PROPOSALS_URL;
    }

    public static String getIpUnderstandUrl() {
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return IRequestConstant.GO_TO_IP_UNDERSTAND_ZH;
        }
        return IRequestConstant.GO_TO_IP_UNDERSTAND_EN;
    }

    public static boolean currentNetCanUpdateLanguage() {
        IRequest.NET_ENVIRONMENT net_environment = ENVIRONMENT;
        return net_environment == IRequest.NET_ENVIRONMENT.RELEASE || net_environment == IRequest.NET_ENVIRONMENT.PRE_RELEASE || net_environment == IRequest.NET_ENVIRONMENT.TEST;
    }

    public static String getImportPopMultiUrl() {
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return IRequestConstant.GO_TO_MULTI_IMPORT_ZH;
        }
        return IRequestConstant.GO_TO_MULTI_IMPORT_EN;
    }

    public static String getSafetyAcademyUrl() {
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return IRequestConstant.GO_TO_SAFETY_ACADEMY_ZH;
        }
        return IRequestConstant.GO_TO_SAFETY_ACADEMY_EN;
    }

    public static String getSecurityCheckUrl() {
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return IRequestConstant.GO_TO_SECURITY_CHECK_ZH;
        }
        return IRequestConstant.GO_TO_SECURITY_CHECK_EN;
    }
}
