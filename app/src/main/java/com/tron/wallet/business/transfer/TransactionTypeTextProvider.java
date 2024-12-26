package com.tron.wallet.business.transfer;

import com.alibaba.fastjson.parser.JSONLexer;
import com.tron.wallet.business.customtokens.bean.CustomTokenStatus;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tronlinkpro.wallet.R;
import kotlin.text.Typography;
import org.apache.commons.lang3.CharUtils;
public class TransactionTypeTextProvider {
    public static String convert(int i) {
        if (i == -1) {
            return "";
        }
        if (i != 48) {
            if (i != 49) {
                if (i != 400) {
                    if (i != 500) {
                        switch (i) {
                            case 0:
                                return "AccountCreateContract";
                            case 1:
                                return "TransferContract";
                            case 2:
                                return "TransferAssetContract";
                            case 3:
                                return "VoteAssetContract";
                            case 4:
                                return "VoteWitnessContract";
                            case 5:
                                return "WitnessCreateContract";
                            case 6:
                                return "AssetIssueContract";
                            default:
                                switch (i) {
                                    case 8:
                                        return "WitnessUpdateContract";
                                    case 9:
                                        return "ParticipateAssetIssueContract";
                                    case 10:
                                        return "AccountUpdateContract";
                                    case 11:
                                        return "FreezeBalanceContract";
                                    case 12:
                                        return "UnfreezeBalanceContract";
                                    case 13:
                                        return "WithdrawBalanceContract";
                                    case 14:
                                        return "UnfreezeAssetContract";
                                    case 15:
                                        return "UpdateAssetContract";
                                    case 16:
                                        return "ProposalCreateContract";
                                    case 17:
                                        return "ProposalApproveContract";
                                    case 18:
                                        return "ProposalDeleteContract";
                                    case 19:
                                        return "SetAccountIdContract";
                                    case 20:
                                        return "CustomContract";
                                    default:
                                        switch (i) {
                                            case 30:
                                                return "CreateSmartContract";
                                            case 31:
                                                return "TriggerSmartContract";
                                            case 32:
                                                return "GetContract";
                                            case 33:
                                                return "UpdateSettingContract";
                                            default:
                                                switch (i) {
                                                    case 41:
                                                        return "ExchangeCreateContract";
                                                    case 42:
                                                        return "ExchangeInjectContract";
                                                    case 43:
                                                        return "ExchangeWithdrawContract";
                                                    case 44:
                                                        return "ExchangeTransactionContract";
                                                    case 45:
                                                        return "UpdateEnergyLimitContract";
                                                    case 46:
                                                        return "AccountPermissionUpdateContract";
                                                    default:
                                                        switch (i) {
                                                            case 51:
                                                                return "ShieldedTransferContract";
                                                            case 52:
                                                                return "MarketSellAssetContract";
                                                            case 53:
                                                                return "MarketCancelOrderContract";
                                                            case 54:
                                                                return "FreezeBalanceV2Contract";
                                                            case 55:
                                                                return "UnfreezeBalanceV2Contract";
                                                            case 56:
                                                                return "WithdrawExpireUnfreezeContract";
                                                            case 57:
                                                                return "DelegateResourceContract";
                                                            case 58:
                                                                return "UnDelegateResourceContract";
                                                            case 59:
                                                                return "CancelAllUnfreezeV2Contract";
                                                            default:
                                                                return "";
                                                        }
                                                }
                                        }
                                }
                        }
                    }
                    return "CancelAllUnfreezeV2WithdrawContract";
                }
                return "UnfreezeV2WithdrawContract";
            }
            return "UpdateBrokerageContract";
        }
        return "ClearABIContract";
    }

    public static int provider(int i, String str) {
        return provider(convert(i), str);
    }

    public static int providerForStakeV2(int i, String str) {
        String convert = convert(i);
        convert.hashCode();
        char c = 65535;
        switch (convert.hashCode()) {
            case -703089577:
                if (convert.equals("FreezeBalanceContract")) {
                    c = 0;
                    break;
                }
                break;
            case -651921570:
                if (convert.equals("UnfreezeBalanceContract")) {
                    c = 1;
                    break;
                }
                break;
            case -82956877:
                if (convert.equals("FreezeBalanceV2Contract")) {
                    c = 2;
                    break;
                }
                break;
            case 1844857594:
                if (convert.equals("UnfreezeBalanceV2Contract")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return R.string.freeze;
            case 1:
                return R.string.tron_power_unlock;
            case 2:
                return R.string.stake_2;
            case 3:
                return R.string.unstake_2;
            default:
                return provider(convert, str);
        }
    }

    public static int providerForStakeV2ByContractType(String str, String str2) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -703089577:
                if (str.equals("FreezeBalanceContract")) {
                    c = 0;
                    break;
                }
                break;
            case -651921570:
                if (str.equals("UnfreezeBalanceContract")) {
                    c = 1;
                    break;
                }
                break;
            case -82956877:
                if (str.equals("FreezeBalanceV2Contract")) {
                    c = 2;
                    break;
                }
                break;
            case 1844857594:
                if (str.equals("UnfreezeBalanceV2Contract")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return R.string.freeze;
            case 1:
                return R.string.tron_power_unlock;
            case 2:
                return R.string.stake_2;
            case 3:
                return R.string.unstake_2;
            default:
                return provider(str, str2);
        }
    }

    public static int provider(String str, String str2) {
        if (StringTronUtil.isEmpty(str)) {
            return R.string.alltransfer29;
        }
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1705044092:
                if (str.equals("WithdrawBalanceContract")) {
                    c = 0;
                    break;
                }
                break;
            case -1656305420:
                if (str.equals("MarketSellAssetContract")) {
                    c = 1;
                    break;
                }
                break;
            case -1632790850:
                if (str.equals("UnDelegateResourceContract")) {
                    c = 2;
                    break;
                }
                break;
            case -1520674133:
                if (str.equals("WithdrawExpireUnfreezeContract")) {
                    c = 3;
                    break;
                }
                break;
            case -1485407205:
                if (str.equals("AssetIssueContract")) {
                    c = 4;
                    break;
                }
                break;
            case -1485013112:
                if (str.equals("UnfreezeV2WithdrawContract")) {
                    c = 5;
                    break;
                }
                break;
            case -1048760864:
                if (str.equals("ProposalCreateContract")) {
                    c = 6;
                    break;
                }
                break;
            case -845990611:
                if (str.equals("ClearABIContract")) {
                    c = 7;
                    break;
                }
                break;
            case -703089577:
                if (str.equals("FreezeBalanceContract")) {
                    c = '\b';
                    break;
                }
                break;
            case -651921570:
                if (str.equals("UnfreezeBalanceContract")) {
                    c = '\t';
                    break;
                }
                break;
            case -611256278:
                if (str.equals("MarketCancelOrderContract")) {
                    c = '\n';
                    break;
                }
                break;
            case -492394392:
                if (str.equals("AccountUpdateContract")) {
                    c = 11;
                    break;
                }
                break;
            case -453939044:
                if (str.equals("UpdateEnergyLimitContract")) {
                    c = '\f';
                    break;
                }
                break;
            case -439997029:
                if (str.equals("AccountCreateContract")) {
                    c = CharUtils.CR;
                    break;
                }
                break;
            case -243778867:
                if (str.equals("ExchangeTransactionContract")) {
                    c = 14;
                    break;
                }
                break;
            case -219262664:
                if (str.equals("SetAccountIdContract")) {
                    c = 15;
                    break;
                }
                break;
            case -82956877:
                if (str.equals("FreezeBalanceV2Contract")) {
                    c = 16;
                    break;
                }
                break;
            case -2225434:
                if (str.equals("ExchangeInjectContract")) {
                    c = 17;
                    break;
                }
                break;
            case 16441433:
                if (str.equals("ParticipateAssetIssueContract")) {
                    c = 18;
                    break;
                }
                break;
            case 39138117:
                if (str.equals("CancelAllUnfreezeV2Contract")) {
                    c = 19;
                    break;
                }
                break;
            case 180125197:
                if (str.equals("ProposalApproveContract")) {
                    c = 20;
                    break;
                }
                break;
            case 270660495:
                if (str.equals("ProposalDeleteContract")) {
                    c = 21;
                    break;
                }
                break;
            case 611663823:
                if (str.equals("UpdateBrokerageContract")) {
                    c = 22;
                    break;
                }
                break;
            case 706457047:
                if (str.equals("TransferAssetContract")) {
                    c = 23;
                    break;
                }
                break;
            case 710366781:
                if (str.equals("TransferContract")) {
                    c = 24;
                    break;
                }
                break;
            case 762691543:
                if (str.equals("AccountPermissionUpdateContract")) {
                    c = 25;
                    break;
                }
                break;
            case 885253893:
                if (str.equals("DelegateResourceContract")) {
                    c = JSONLexer.EOI;
                    break;
                }
                break;
            case 1147615065:
                if (str.equals("UpdateSettingContract")) {
                    c = 27;
                    break;
                }
                break;
            case 1156978031:
                if (str.equals("CancelAllUnfreezeV2WithdrawContract")) {
                    c = 28;
                    break;
                }
                break;
            case 1190060069:
                if (str.equals("ShieldedTransferContract")) {
                    c = 29;
                    break;
                }
                break;
            case 1252602738:
                if (str.equals("UnfreezeAssetContract")) {
                    c = 30;
                    break;
                }
                break;
            case 1286958708:
                if (str.equals("WitnessUpdateContract")) {
                    c = 31;
                    break;
                }
                break;
            case 1339356071:
                if (str.equals("WitnessCreateContract")) {
                    c = ' ';
                    break;
                }
                break;
            case 1392453279:
                if (str.equals("ExchangeWithdrawContract")) {
                    c = '!';
                    break;
                }
                break;
            case 1421429571:
                if (str.equals("TriggerSmartContract")) {
                    c = Typography.quote;
                    break;
                }
                break;
            case 1532035647:
                if (str.equals("CreateSmartContract")) {
                    c = '#';
                    break;
                }
                break;
            case 1699052801:
                if (str.equals("VoteWitnessContract")) {
                    c = '$';
                    break;
                }
                break;
            case 1844857594:
                if (str.equals("UnfreezeBalanceV2Contract")) {
                    c = '%';
                    break;
                }
                break;
            case 1892384185:
                if (str.equals("UpdateAssetContract")) {
                    c = Typography.amp;
                    break;
                }
                break;
            case 2022818456:
                if (str.equals("FeeContract")) {
                    c = '\'';
                    break;
                }
                break;
            case 2106222417:
                if (str.equals("ExchangeCreateContract")) {
                    c = '(';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return R.string.claim_vote_rewards;
            case 1:
                return R.string.market_sell_asset_contract;
            case 2:
                return R.string.resource_recycle;
            case 3:
            case 5:
            case 28:
                return R.string.withdraw_expire_unfreeze;
            case 4:
                return R.string.TRC10_issue;
            case 6:
                return R.string.create_proposal;
            case 7:
                return R.string.clear_ABI_contract;
            case '\b':
                return R.string.TRXFreeze;
            case '\t':
                return R.string.TRXUnfreeze;
            case '\n':
                return R.string.cancel_order;
            case 11:
                return R.string.account_update_name;
            case '\f':
                return R.string.update_energy_limit;
            case '\r':
                return R.string.account_create;
            case 14:
                return R.string.bancor_transaction;
            case 15:
                return R.string.set_up_account_id;
            case 16:
                return R.string.trans_type_stake_v2;
            case 17:
                return R.string.bancor_jnject;
            case 18:
                return R.string.TRC10_particiate;
            case 19:
                return R.string.cancel_all_unfreeze;
            case 20:
                return R.string.approve_proposal;
            case 21:
                return R.string.revoke_proposal;
            case 22:
                return R.string.update_representatives_brokerage;
            case 23:
                return R.string.TRC10_transfer;
            case 24:
                return R.string.Transfer;
            case 25:
                return R.string.account_update_permission;
            case 26:
                return R.string.resource_delegate;
            case 27:
                return R.string.update_setting_contract;
            case 29:
                return R.string.transfer_trz;
            case 30:
                return R.string.TRC10_unfreeze;
            case 31:
                return R.string.update_representatives_info;
            case ' ':
                return R.string.create_representatives;
            case '!':
                return R.string.bancor_withdraw;
            case '\"':
                return "Transfer".equals(str2) ? R.string.TRC20_Transfer_t : CustomTokenStatus.APPROVE_EVENT.equals(str2) ? R.string.TRC20_Transfer_approve : "721Transfer".equals(str2) ? R.string.TRC721_Transfer_t : "721Approval".equals(str2) ? R.string.TRC721_Transfer_approve : R.string.TRC20_Transfer;
            case '#':
                return R.string.create_smart_contract;
            case '$':
                return R.string.Vote;
            case '%':
                return R.string.trans_type_unstake_v2;
            case '&':
                return R.string.TRC10_update_parameters;
            case '\'':
                return R.string.transfer_fee;
            case '(':
                return R.string.bancor_create;
            default:
                return R.string.alltransfer29;
        }
    }
}
