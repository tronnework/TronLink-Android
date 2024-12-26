package com.tron.wallet.business.mutil;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.wallet.business.mutil.bean.MultiAddressOutput;
import com.tron.wallet.business.mutil.bean.MultiSourcePageEnum;
import com.tron.wallet.common.components.ErrorView;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tronlinkpro.wallet.R;
public class ResStringProvider {
    private static String CancelUnStake;
    private static String NotHave;
    private static String StakeV2;
    private static String UnStakeV1;
    private static String UnStakeV2;
    private static String WithDraw;
    private static String onlyHave;
    private MultiSourcePageEnum multiSourcePageEnum;

    public ResStringProvider(MultiSourcePageEnum multiSourcePageEnum) {
        this.multiSourcePageEnum = multiSourcePageEnum;
        onlyHave = StringTronUtil.getResString(R.string.do_only_have_multi_permission);
        NotHave = StringTronUtil.getResString(R.string.do_not_have_multi_permission);
        UnStakeV2 = StringTronUtil.getResString(R.string.unstake_2);
        UnStakeV1 = StringTronUtil.getResString(R.string.unstake_1);
        StakeV2 = StringTronUtil.getResString(R.string.stake_v2);
        WithDraw = StringTronUtil.getResString(R.string.withdraw_expire_unfreeze);
        CancelUnStake = StringTronUtil.getResString(R.string.cancel_all_unstake_stream);
    }

    public static ResStringProvider init(MultiSourcePageEnum multiSourcePageEnum) {
        return new ResStringProvider(multiSourcePageEnum);
    }

    static class fun1 {
        static final int[] $SwitchMap$com$tron$wallet$business$mutil$bean$MultiSourcePageEnum;

        static {
            int[] iArr = new int[MultiSourcePageEnum.values().length];
            $SwitchMap$com$tron$wallet$business$mutil$bean$MultiSourcePageEnum = iArr;
            try {
                iArr[MultiSourcePageEnum.Stake.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$mutil$bean$MultiSourcePageEnum[MultiSourcePageEnum.UnStake.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$mutil$bean$MultiSourcePageEnum[MultiSourcePageEnum.Vote.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$mutil$bean$MultiSourcePageEnum[MultiSourcePageEnum.Resources.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$mutil$bean$MultiSourcePageEnum[MultiSourcePageEnum.StakeV2.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public String getTitle() {
        int i = fun1.$SwitchMap$com$tron$wallet$business$mutil$bean$MultiSourcePageEnum[this.multiSourcePageEnum.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4 || i == 5) {
                        return StringTronUtil.getResString(R.string.multisig);
                    }
                    return StringTronUtil.getResString(R.string.multi_sign_transfer);
                }
                return StringTronUtil.getResString(R.string.multi_sign_vote_page_title);
            }
            return StringTronUtil.getResString(R.string.multi_sign_unstake_page_title);
        }
        return StringTronUtil.getResString(R.string.multi_sign_stake_page_title);
    }

    public String getTitleStep() {
        int i = fun1.$SwitchMap$com$tron$wallet$business$mutil$bean$MultiSourcePageEnum[this.multiSourcePageEnum.ordinal()];
        if (i != 2) {
            return (i == 3 || i == 4 || i == 5) ? "" : StringTronUtil.getResString(R.string.step_1_3);
        }
        return StringTronUtil.getResString(R.string.step_1_2);
    }

    public ErrorViewText getInputError(MultiAddressOutput multiAddressOutput) {
        ErrorViewText errorViewText = new ErrorViewText();
        int i = fun1.$SwitchMap$com$tron$wallet$business$mutil$bean$MultiSourcePageEnum[this.multiSourcePageEnum.ordinal()];
        if (i == 1) {
            errorViewText.level = ErrorView.Level.ERROR;
            errorViewText.text = StringTronUtil.getResString(R.string.do_not_have_stake_permission);
        } else if (i != 2) {
            if (i != 3) {
                if (i != 4) {
                    if (i != 5) {
                        errorViewText.level = ErrorView.Level.ERROR;
                        errorViewText.text = StringTronUtil.getResString(R.string.do_not_have_transfer_permission);
                    } else if (multiAddressOutput == null) {
                        errorViewText.level = ErrorView.Level.ERROR;
                        errorViewText.text = String.format(NotHave, mergeStakeV2ErrorText(StakeV2, UnStakeV1, UnStakeV2, WithDraw, CancelUnStake));
                    } else if (multiAddressOutput.getOwnerPermission() != null && multiAddressOutput.getOwnerPermission().isHas()) {
                        errorViewText.level = ErrorView.Level.NONE;
                        errorViewText.text = "";
                    } else if (multiAddressOutput.hasFreezeBalanceV2Permission() && multiAddressOutput.hasUnStakePermission() && multiAddressOutput.hasUnfreezeBalanceV2Permission() && multiAddressOutput.hasWithdrawExpireUnfreezePermission() && multiAddressOutput.hasCancelAllUnfreezePermission()) {
                        errorViewText.level = ErrorView.Level.NONE;
                        errorViewText.text = "";
                    } else if (!multiAddressOutput.hasFreezeBalanceV2Permission() && !multiAddressOutput.hasUnStakePermission() && !multiAddressOutput.hasUnfreezeBalanceV2Permission() && !multiAddressOutput.hasWithdrawExpireUnfreezePermission() && !multiAddressOutput.hasCancelAllUnfreezePermission()) {
                        errorViewText.level = ErrorView.Level.ERROR;
                        errorViewText.text = String.format(NotHave, mergeStakeV2ErrorText(StakeV2, UnStakeV1, UnStakeV2, WithDraw, CancelUnStake));
                    } else {
                        String str = multiAddressOutput.hasFreezeBalanceV2Permission() ? StakeV2 : "";
                        String str2 = multiAddressOutput.hasUnStakePermission() ? UnStakeV1 : "";
                        String str3 = multiAddressOutput.hasUnfreezeBalanceV2Permission() ? UnStakeV2 : "";
                        String str4 = multiAddressOutput.hasWithdrawExpireUnfreezePermission() ? WithDraw : "";
                        String str5 = multiAddressOutput.hasCancelAllUnfreezePermission() ? CancelUnStake : "";
                        errorViewText.level = ErrorView.Level.INFO;
                        errorViewText.text = String.format(onlyHave, mergeStakeV2ErrorText(str, str2, str3, str4, str5));
                    }
                } else if (multiAddressOutput == null) {
                    errorViewText.level = ErrorView.Level.ERROR;
                    String str6 = NotHave;
                    errorViewText.text = String.format(str6, StringTronUtil.getResString(R.string.resource_delegate) + languageAppend() + StringTronUtil.getResString(R.string.resource_recycle));
                } else if (multiAddressOutput.hasDelegateResourcePermission() && multiAddressOutput.hasUnDelegateResourcePermission()) {
                    errorViewText.level = ErrorView.Level.NONE;
                    errorViewText.text = "";
                } else if (multiAddressOutput.getOwnerPermission() != null && multiAddressOutput.getOwnerPermission().isHas()) {
                    errorViewText.level = ErrorView.Level.NONE;
                    errorViewText.text = "";
                } else if (multiAddressOutput.hasDelegateResourcePermission()) {
                    errorViewText.level = ErrorView.Level.INFO;
                    errorViewText.text = String.format(onlyHave, StringTronUtil.getResString(R.string.resource_delegate));
                } else if (multiAddressOutput.hasUnDelegateResourcePermission()) {
                    errorViewText.level = ErrorView.Level.INFO;
                    errorViewText.text = String.format(onlyHave, StringTronUtil.getResString(R.string.resource_recycle));
                } else {
                    errorViewText.level = ErrorView.Level.ERROR;
                    String str7 = NotHave;
                    errorViewText.text = String.format(str7, StringTronUtil.getResString(R.string.resource_delegate) + languageAppend() + StringTronUtil.getResString(R.string.resource_recycle));
                }
            } else if (multiAddressOutput == null) {
                errorViewText.level = ErrorView.Level.ERROR;
                errorViewText.text = StringTronUtil.getResString(R.string.do_not_have_vote_withdraw_permission);
            } else if (multiAddressOutput.hasVoteWitnessPermission() && multiAddressOutput.hasWithdrawBalancePermission()) {
                errorViewText.level = ErrorView.Level.NONE;
                errorViewText.text = "";
            } else if (multiAddressOutput.getOwnerPermission() != null && multiAddressOutput.getOwnerPermission().isHas()) {
                errorViewText.level = ErrorView.Level.NONE;
                errorViewText.text = "";
            } else if (multiAddressOutput.hasVoteWitnessPermission() && !multiAddressOutput.hasWithdrawBalancePermission()) {
                errorViewText.level = ErrorView.Level.INFO;
                errorViewText.text = StringTronUtil.getResString(R.string.do_not_have_withdraw_permission);
            } else if (!multiAddressOutput.hasVoteWitnessPermission() && multiAddressOutput.hasWithdrawBalancePermission()) {
                errorViewText.level = ErrorView.Level.INFO;
                errorViewText.text = StringTronUtil.getResString(R.string.do_not_have_vote_permission);
            } else {
                errorViewText.level = ErrorView.Level.ERROR;
                errorViewText.text = StringTronUtil.getResString(R.string.do_not_have_vote_withdraw_permission);
            }
        } else {
            errorViewText.level = ErrorView.Level.ERROR;
            errorViewText.text = StringTronUtil.getResString(R.string.do_not_have_unstake_permission);
        }
        return errorViewText;
    }

    public int getDataEmptyText() {
        int i = fun1.$SwitchMap$com$tron$wallet$business$mutil$bean$MultiSourcePageEnum[this.multiSourcePageEnum.ordinal()];
        return i != 1 ? i != 2 ? i != 3 ? (i == 4 || i == 5) ? R.string.multi_resources_no_account_list : R.string.no_control_multi_sign_accounts : R.string.no_control_vote_accounts : R.string.no_control_unstake_accounts : R.string.no_control_stake_accounts;
    }

    public String getTutorialUrl() {
        int i = fun1.$SwitchMap$com$tron$wallet$business$mutil$bean$MultiSourcePageEnum[this.multiSourcePageEnum.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        if (i == 5) {
                            return IRequest.getMultiSignStakeV2HelpUrl();
                        }
                        return IRequest.getMultiSignTransferHelpUrl();
                    }
                    return IRequest.getMultiSignStakeV2ResourcesHelpUrl();
                }
                return IRequest.getMultiSignVoteHelpUrl();
            }
            return IRequest.getMultiSignUnStakeHelpUrl();
        }
        return IRequest.getMultiSignStakeHelpUrl();
    }

    public int getLogEventParam() {
        int i = fun1.$SwitchMap$com$tron$wallet$business$mutil$bean$MultiSourcePageEnum[this.multiSourcePageEnum.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        return i != 5 ? 1 : 6;
                    }
                    return 5;
                }
                return 4;
            }
            return 3;
        }
        return 2;
    }

    public static class ErrorViewText {
        public ErrorView.Level level;
        public String text;

        public static ErrorViewText empty() {
            ErrorViewText errorViewText = new ErrorViewText();
            errorViewText.level = ErrorView.Level.ERROR;
            errorViewText.text = "";
            return errorViewText;
        }

        public ErrorViewText() {
        }

        public ErrorViewText(ErrorView.Level level, String str) {
            this.level = level;
            this.text = str;
        }
    }

    private String mergeStakeV2ErrorText(String... strArr) {
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        while (i < strArr.length) {
            String str = strArr[i];
            int i2 = i + 1;
            if (i2 < strArr.length) {
                String str2 = strArr[i2];
            }
            stringBuffer.append(str);
            if (!StringTronUtil.isEmpty(str) && i != strArr.length - 1) {
                stringBuffer.append(languageAppend());
            }
            i = i2;
        }
        String stringBuffer2 = stringBuffer.toString();
        if (stringBuffer2.endsWith(languageAppend())) {
            if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
                return stringBuffer2.trim().substring(0, stringBuffer2.length() - 1);
            }
            return stringBuffer2.trim().substring(0, stringBuffer2.length() - 2);
        }
        return stringBuffer2;
    }

    private String languageAppend() {
        return LanguageUtils.languageZH(AppContextUtil.getContext()) ? "、" : ", ";
    }
}
