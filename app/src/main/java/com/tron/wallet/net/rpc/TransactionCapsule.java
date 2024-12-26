package com.tron.wallet.net.rpc;

import com.google.protobuf.Any;
import com.google.protobuf.ByteString;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.StringTronUtil;
import com.tronlinkpro.wallet.R;
import org.tron.api.GrpcAPI;
import org.tron.common.utils.ByteArray;
import org.tron.protos.Protocol;
public class TransactionCapsule {
    public static long TRANSACTION_DEFAULT_EXPIRATION_TIME = 60000;
    public static long TRANSACTION_DEFAULT_EXPIRATION_TIME_120 = 120000;

    public static GrpcAPI.TransactionExtention createLocalTransaction(Protocol.Transaction.Contract.ContractType contractType, Any any) {
        GrpcAPI.TransactionExtention.Builder newBuilder = GrpcAPI.TransactionExtention.newBuilder();
        try {
            GrpcAPI.BlockExtention block = getBlock(0);
            byte[] fromLong = ByteArray.fromLong(block.getBlockHeader().getRawData().getNumber());
            byte[] byteArray = block.getBlockid().toByteArray();
            long timestamp = block.getBlockHeader().getRawData().getTimestamp() + TRANSACTION_DEFAULT_EXPIRATION_TIME;
            Protocol.Transaction.Builder newBuilder2 = Protocol.Transaction.newBuilder();
            Protocol.Transaction.raw.Builder newBuilder3 = Protocol.Transaction.raw.newBuilder();
            newBuilder3.addContract(Protocol.Transaction.Contract.newBuilder().setType(contractType).setParameter(any));
            newBuilder3.setTimestamp(block.getBlockHeader().getRawData().getTimestamp());
            newBuilder3.setExpiration(timestamp);
            newBuilder3.setRefBlockHash(ByteString.copyFrom(ByteArray.subArray(byteArray, 8, 16)));
            newBuilder3.setRefBlockBytes(ByteString.copyFrom(ByteArray.subArray(fromLong, 6, 8)));
            newBuilder.setTransaction(newBuilder2.setRawData(newBuilder3.build()).build());
            GrpcAPI.Return.Builder newBuilder4 = GrpcAPI.Return.newBuilder();
            newBuilder4.setResult(true).setCode(GrpcAPI.Return.response_code.SUCCESS);
            newBuilder.setResult(newBuilder4);
            LogUtils.i("TransactionCapsule", "createLocalTransaction:" + contractType);
            return newBuilder.build();
        } catch (Exception e) {
            LogUtils.e(e);
            ByteString copyFromUtf8 = ByteString.copyFromUtf8(StringTronUtil.getResString(R.string.broadcast_fail));
            GrpcAPI.Return.Builder newBuilder5 = GrpcAPI.Return.newBuilder();
            newBuilder5.setResult(false).setCode(GrpcAPI.Return.response_code.SERVER_BUSY).setMessage(copyFromUtf8);
            newBuilder.setResult(newBuilder5);
            LogUtils.i("TransactionCapsule", "createLocalTransaction:" + contractType);
            return newBuilder.build();
        }
    }

    public static GrpcAPI.BlockExtention getBlock(int i) {
        if (i == 3) {
            return null;
        }
        GrpcAPI.BlockExtention nowBlock2 = TronAPI.getNowBlock2();
        try {
            ByteArray.fromLong(nowBlock2.getBlockHeader().getRawData().getNumber());
            nowBlock2.getBlockid().toByteArray();
            nowBlock2.getBlockHeader().getRawData().getTimestamp();
            return nowBlock2;
        } catch (Exception e) {
            LogUtils.e(e);
            return getBlock(i + 1);
        }
    }
}
