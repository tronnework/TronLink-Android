package com.tron.wallet.common.utils;

import android.content.Context;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tronlinkpro.wallet.R;
import java.text.DecimalFormat;
public class ProposalUtils {
    public static String getProposalContent(long j, long j2) {
        String str;
        Context context = AppContextUtil.getContext();
        DecimalFormat decimalFormat = new DecimalFormat("#.######");
        switch ((int) j) {
            case 0:
                double div = BigDecimalUtils.div(j2, 3600000.0d, 6);
                str = context.getString(R.string.propose_0) + context.getString(R.string.propose_to) + decimalFormat.format(div) + "" + context.getString(R.string.propose_hour);
                break;
            case 1:
                double div2 = BigDecimalUtils.div(j2, 1000000.0d, 6);
                str = context.getString(R.string.propose_1) + context.getString(R.string.propose_to) + decimalFormat.format(div2) + " TRX";
                break;
            case 2:
                double div3 = BigDecimalUtils.div(j2, 1000000.0d, 6);
                str = context.getString(R.string.propose_2) + context.getString(R.string.propose_to) + decimalFormat.format(div3) + " TRX";
                break;
            case 3:
                double div4 = BigDecimalUtils.div(j2, 1000000.0d, 6);
                str = context.getString(R.string.propose_3) + context.getString(R.string.propose_to) + decimalFormat.format(div4) + " TRX";
                break;
            case 4:
                double div5 = BigDecimalUtils.div(j2, 1000000.0d, 6);
                str = context.getString(R.string.propose_4) + context.getString(R.string.propose_to) + decimalFormat.format(div5) + " TRX";
                break;
            case 5:
                double div6 = BigDecimalUtils.div(j2, 1000000.0d, 6);
                str = context.getString(R.string.propose_5) + context.getString(R.string.propose_to) + decimalFormat.format(div6) + " TRX";
                break;
            case 6:
                double div7 = BigDecimalUtils.div(j2, 1000000.0d, 6);
                str = context.getString(R.string.propose_6) + context.getString(R.string.propose_to) + decimalFormat.format(div7) + " TRX";
                break;
            case 7:
                double div8 = BigDecimalUtils.div(j2, 1000000.0d, 6);
                str = context.getString(R.string.propose_7) + context.getString(R.string.propose_to) + decimalFormat.format(div8) + " TRX";
                break;
            case 8:
                str = context.getString(R.string.propose_8) + context.getString(R.string.propose_to) + j2 + " bandwith/byte";
                break;
            case 9:
                str = context.getString(R.string.propose_9) + context.getString(R.string.propose_to) + (j2 == 1 ? context.getString(R.string.open) : context.getString(R.string.not_open));
                break;
            case 10:
                str = context.getString(R.string.propose_10);
                break;
            case 11:
                double div9 = BigDecimalUtils.div(j2, 1000000.0d, 6);
                str = context.getString(R.string.propose_11) + context.getString(R.string.propose_to) + decimalFormat.format(div9) + " TRX";
                break;
            case 12:
                double div10 = BigDecimalUtils.div(j2, 1000000.0d, 6);
                str = context.getString(R.string.propose_12) + context.getString(R.string.propose_to) + decimalFormat.format(div10) + " TRX";
                break;
            case 13:
                str = context.getString(R.string.propose_13) + context.getString(R.string.propose_to) + j2 + " ms";
                break;
            case 14:
                String string = context.getString(R.string.propose_14);
                str = string + context.getString(R.string.propose_to) + context.getString(j2 == 1 ? R.string.allow : R.string.not_allow);
                break;
            case 15:
                String string2 = context.getString(R.string.propose_15);
                str = string2 + context.getString(R.string.propose_to) + context.getString(j2 == 1 ? R.string.allow : R.string.not_allow);
                break;
            case 16:
                String string3 = context.getString(R.string.propose_16);
                str = string3 + context.getString(R.string.propose_to) + context.getString(j2 == 1 ? R.string.allow : R.string.not_allow);
                break;
            case 17:
                str = context.getString(R.string.propose_17) + context.getString(R.string.propose_to) + j2 + "";
                break;
            case 18:
                String string4 = context.getString(R.string.propose_18);
                str = string4 + context.getString(R.string.propose_to) + context.getString(j2 == 1 ? R.string.allow : R.string.not_allow);
                break;
            case 19:
                str = context.getString(R.string.propose_19) + context.getString(R.string.propose_to) + j2 + "";
                break;
            case 20:
                String string5 = context.getString(R.string.propose_20);
                str = string5 + context.getString(R.string.propose_to) + context.getString(j2 == 1 ? R.string.allow : R.string.not_allow);
                break;
            case 21:
                String string6 = context.getString(R.string.propose_21);
                str = string6 + context.getString(R.string.propose_to) + context.getString(j2 == 1 ? R.string.allow : R.string.not_allow);
                break;
            case 22:
                double div11 = BigDecimalUtils.div(j2, 1000000.0d, 6);
                str = context.getString(R.string.propose_22) + context.getString(R.string.propose_to) + decimalFormat.format(div11) + "TRX";
                break;
            case 23:
                double div12 = BigDecimalUtils.div(j2, 1000000.0d, 6);
                str = context.getString(R.string.propose_23) + context.getString(R.string.propose_to) + decimalFormat.format(div12) + "TRX";
                break;
            case 24:
                str = context.getString(R.string.propose_24) + context.getString(R.string.propose_to) + (j2 == 1 ? context.getString(R.string.open) : context.getString(R.string.not_open));
                break;
            case 25:
                String string7 = context.getString(R.string.propose_25);
                str = string7 + context.getString(R.string.propose_to) + context.getString(j2 == 1 ? R.string.allow : R.string.not_allow);
                break;
            case 26:
                String string8 = context.getString(R.string.propose_26);
                str = string8 + context.getString(R.string.propose_to) + context.getString(j2 == 1 ? R.string.allow : R.string.not_allow);
                break;
            case 27:
                String string9 = context.getString(R.string.propose_27);
                str = string9 + context.getString(R.string.propose_to) + context.getString(j2 == 1 ? R.string.allow : R.string.not_allow);
                break;
            case 28:
            case 29:
            case 34:
            case 36:
            case 37:
            case 38:
            case 42:
            case 43:
            case 50:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 60:
            case 64:
            default:
                str = "";
                break;
            case 30:
                String string10 = context.getString(R.string.propose_30);
                str = string10 + context.getString(R.string.propose_to) + context.getString(j2 == 1 ? R.string.allow : R.string.not_allow);
                break;
            case 31:
                double div13 = BigDecimalUtils.div(j2, 1000000.0d, 6);
                str = context.getString(R.string.propose_31) + context.getString(R.string.propose_to) + decimalFormat.format(div13) + " TRX";
                break;
            case 32:
                String string11 = context.getString(R.string.propose_32);
                str = string11 + context.getString(R.string.propose_to) + context.getString(j2 == 1 ? R.string.allow : R.string.not_allow);
                break;
            case 33:
                str = context.getString(R.string.propose_33) + context.getString(R.string.propose_to) + j2;
                break;
            case 35:
                String string12 = context.getString(R.string.propose_35);
                str = string12 + context.getString(R.string.propose_to) + context.getString(j2 == 1 ? R.string.propose_ban : R.string.propose_not_ban);
                break;
            case 39:
                str = context.getString(R.string.propose_39) + context.getString(R.string.propose_to) + (j2 == 1 ? context.getString(R.string.open) : context.getString(R.string.not_open));
                break;
            case 40:
                str = context.getString(R.string.propose_40) + context.getString(R.string.propose_to) + (j2 == 1 ? context.getString(R.string.open) : context.getString(R.string.not_open));
                break;
            case 41:
                str = context.getString(R.string.propose_41) + context.getString(R.string.propose_to) + (j2 == 1 ? context.getString(R.string.open) : context.getString(R.string.not_open));
                break;
            case 44:
                String string13 = context.getString(R.string.propose_44);
                str = string13 + context.getString(R.string.propose_to) + context.getString(j2 == 1 ? R.string.allow : R.string.not_allow);
                break;
            case 45:
                double div14 = BigDecimalUtils.div(j2, 1000000.0d, 6);
                str = context.getString(R.string.propose_45) + context.getString(R.string.propose_to) + decimalFormat.format(div14) + " TRX";
                break;
            case 46:
                double div15 = BigDecimalUtils.div(j2, 1000000.0d, 6);
                str = context.getString(R.string.propose_46) + context.getString(R.string.propose_to) + decimalFormat.format(div15) + " TRX";
                break;
            case 47:
                double div16 = BigDecimalUtils.div(j2, 1000000.0d, 6);
                str = context.getString(R.string.propose_47) + context.getString(R.string.propose_to) + decimalFormat.format(div16) + " TRX";
                break;
            case 48:
                String string14 = j2 == 1 ? context.getString(R.string.open) : context.getString(R.string.not_open);
                str = context.getString(R.string.propose_48) + context.getString(R.string.propose_to) + string14;
                break;
            case 49:
                str = context.getString(R.string.propose_49) + context.getString(R.string.propose_to) + (j2 == 1 ? context.getString(R.string.open) : context.getString(R.string.not_open));
                break;
            case 51:
                str = context.getString(R.string.propose_51) + context.getString(R.string.propose_to) + (j2 == 1 ? context.getString(R.string.open) : context.getString(R.string.not_open));
                break;
            case 52:
                str = context.getString(R.string.propose_52) + context.getString(R.string.propose_to) + (j2 == 1 ? context.getString(R.string.open) : context.getString(R.string.not_open));
                break;
            case 53:
                str = context.getString(R.string.proposal_53) + context.getString(R.string.propose_to) + (j2 == 1 ? context.getString(R.string.open) : context.getString(R.string.not_open));
                break;
            case 59:
                str = context.getString(R.string.proposal_59) + context.getString(R.string.propose_to) + (j2 == 1 ? context.getString(R.string.open) : context.getString(R.string.not_open));
                break;
            case 61:
                str = context.getString(R.string.proposal_61) + context.getString(R.string.propose_to) + decimalFormat.format(BigDecimalUtils.toBigDecimal(Long.valueOf(j2))) + " Bandwidth";
                break;
            case 62:
                str = context.getString(R.string.proposal_62) + context.getString(R.string.propose_to) + decimalFormat.format(BigDecimalUtils.toBigDecimal(Long.valueOf(j2))) + " Bandwidth";
                break;
            case 63:
                str = context.getString(R.string.proposal_63) + " " + context.getString(R.string.propose_to) + " " + (j2 == 1 ? context.getString(R.string.open) : context.getString(R.string.not_open));
                break;
            case 65:
                str = context.getString(R.string.proposal_65) + " " + context.getString(R.string.propose_to) + " " + (j2 == 1 ? context.getString(R.string.open) : context.getString(R.string.not_open));
                break;
            case 66:
                str = context.getString(R.string.proposal_66) + " " + context.getString(R.string.propose_to) + " " + (j2 == 1 ? context.getString(R.string.open) : context.getString(R.string.not_open));
                break;
            case 67:
                str = context.getString(R.string.proposal_67) + " " + context.getString(R.string.propose_to) + " " + (j2 == 1 ? context.getString(R.string.open) : context.getString(R.string.not_open));
                break;
            case 68:
                double div17 = BigDecimalUtils.div(j2, 1000000.0d, 6);
                str = context.getString(R.string.proposal_68) + context.getString(R.string.propose_to) + decimalFormat.format(div17) + " TRX";
                break;
            case 69:
                str = context.getString(R.string.proposal_69) + " " + context.getString(R.string.propose_to) + " " + (j2 == 1 ? context.getString(R.string.open) : context.getString(R.string.not_open));
                break;
            case 70:
                str = context.getString(R.string.proposal_70) + context.getString(R.string.propose_to) + decimalFormat.format(j2);
                break;
            case 71:
                str = context.getString(R.string.proposal_71) + " " + context.getString(R.string.propose_to) + " " + (j2 == 1 ? context.getString(R.string.open) : context.getString(R.string.not_open));
                break;
            case 72:
                str = context.getString(R.string.proposal_72) + " " + context.getString(R.string.propose_to) + " " + (j2 == 1 ? context.getString(R.string.open) : context.getString(R.string.not_open));
                break;
            case 73:
                str = context.getString(R.string.proposal_73) + context.getString(R.string.propose_to) + decimalFormat.format(j2);
                break;
            case 74:
                str = context.getString(R.string.proposal_74) + context.getString(R.string.propose_to) + decimalFormat.format(j2);
                break;
            case 75:
                str = context.getString(R.string.proposal_75) + context.getString(R.string.propose_to) + decimalFormat.format(j2);
                break;
        }
        return str == null ? "" : str;
    }
}
