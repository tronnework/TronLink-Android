package com.tron.wallet.common.utils;

import android.content.Context;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.rpc.TronAPI;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
public class BandwidthUtils {
    public GrpcAPI.AccountResourceMessage accountNetMessage;

    public static boolean isEnoughBandwidth(Context context, long j) {
        GrpcAPI.AccountResourceMessage accountRes = WalletUtils.getAccountRes(context, WalletUtils.getSelectedWallet().getWalletName());
        return accountRes.getNetLimit() - accountRes.getNetUsed() >= j || accountRes.getFreeNetLimit() - accountRes.getFreeNetUsed() >= j;
    }

    public static boolean isFreezedBandwidthEnough(Context context, long j) {
        GrpcAPI.AccountResourceMessage accountRes = WalletUtils.getAccountRes(context, WalletUtils.getSelectedWallet().getWalletName());
        return (accountRes.getNetLimit() - accountRes.getNetUsed()) - j > 0;
    }

    public static boolean isFreezeBandwidthEnough(Context context, GrpcAPI.AccountResourceMessage accountResourceMessage, long j) {
        if (accountResourceMessage == null) {
            accountResourceMessage = WalletUtils.getAccountRes(context, WalletUtils.getSelectedWallet().getWalletName());
        }
        return (accountResourceMessage.getNetLimit() - accountResourceMessage.getNetUsed()) - j > 0;
    }

    public static boolean isBandwidthEnough(Context context, GrpcAPI.AccountResourceMessage accountResourceMessage, long j) {
        if (accountResourceMessage == null) {
            accountResourceMessage = WalletUtils.getAccountRes(context, WalletUtils.getSelectedWallet().getWalletName());
        }
        if (accountResourceMessage == null) {
            return false;
        }
        return (accountResourceMessage.getNetLimit() - accountResourceMessage.getNetUsed()) - j > 0 || (accountResourceMessage.getFreeNetLimit() - accountResourceMessage.getFreeNetUsed()) - j > 0;
    }

    public static boolean isFreezedBandwidthEnough(Context context, String str, long j) {
        GrpcAPI.AccountResourceMessage accountRes = TronAPI.getAccountRes(StringTronUtil.decodeFromBase58Check(str));
        return accountRes != null && (accountRes.getNetLimit() - accountRes.getNetUsed()) - j > 0;
    }

    public static long getFreeBandwidth(Context context, String str) {
        GrpcAPI.AccountResourceMessage accountRes = WalletUtils.getAccountRes(context, str);
        if (accountRes != null) {
            return accountRes.getFreeNetLimit() - accountRes.getFreeNetUsed();
        }
        return 0L;
    }

    public static long getBandwidthCost(Protocol.Transaction transaction) {
        try {
            if (transaction.getRawData() != null && transaction.getRawData().getContractCount() != 0 && transaction.getRawData().getContract(0) != null) {
                return transaction.getSerializedSize() + ((transaction.getSignatureList() == null || transaction.getSignatureList().size() < 1) ? 135 : 200);
            }
            return 0L;
        } catch (Exception unused) {
            return 0L;
        }
    }
}
