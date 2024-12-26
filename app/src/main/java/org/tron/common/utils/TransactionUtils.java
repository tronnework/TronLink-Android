package org.tron.common.utils;

import com.google.common.base.Ascii;
import com.google.common.primitives.Bytes;
import com.google.protobuf.Any;
import com.google.protobuf.ByteString;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import java.security.SignatureException;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.bouncycastle.util.encoders.Hex;
import org.tron.api.GrpcAPI;
import org.tron.common.bip32.ECKeyPair;
import org.tron.common.bip32.Numeric;
import org.tron.common.bip32.Sign;
import org.tron.common.crypto.ECKey;
import org.tron.common.crypto.Hash;
import org.tron.common.exceptions.ContractValidateException;
import org.tron.common.exceptions.ZksnarkException;
import org.tron.common.utils.abi.TronException;
import org.tron.protos.Protocol;
import org.tron.protos.contract.AccountContract;
import org.tron.protos.contract.AssetIssueContractOuterClass;
import org.tron.protos.contract.BalanceContract;
import org.tron.protos.contract.ExchangeContract;
import org.tron.protos.contract.ProposalContract;
import org.tron.protos.contract.ShieldContract;
import org.tron.protos.contract.SmartContractOuterClass;
import org.tron.protos.contract.StorageContract;
import org.tron.protos.contract.VoteAssetContractOuterClass;
import org.tron.protos.contract.WitnessContract;
import org.tron.walletserver.AddressUtil;
public class TransactionUtils {
    static final boolean $assertionsDisabled = false;

    public static String getHash(Protocol.Transaction transaction) {
        return Hex.toHexString(Hash.sha256(transaction.getRawData().toByteArray()));
    }

    public static <T extends MessageLite> T unpackContract(Protocol.Transaction.Contract contract, Class<T> cls) throws InvalidProtocolBufferException {
        return (T) Internal.getDefaultInstance(cls).getParserForType().parseFrom(contract.getParameter().getValue());
    }

    public static Protocol.Transaction setReference(Protocol.Transaction transaction, Protocol.Block block) {
        long number = block.getBlockHeader().getRawData().getNumber();
        return transaction.toBuilder().setRawData(transaction.getRawData().toBuilder().setRefBlockHash(ByteString.copyFrom(ByteArray.subArray(getBlockHash(block).getBytes(), 8, 16))).setRefBlockBytes(ByteString.copyFrom(ByteArray.subArray(ByteArray.fromLong(number), 6, 8))).build()).build();
    }

    public static Sha256Hash getBlockHash(Protocol.Block block) {
        return Sha256Hash.of(block.getBlockHeader().getRawData().toByteArray());
    }

    public static String getTransactionHash(Protocol.Transaction transaction) {
        return ByteArray.toHexString(Sha256Hash.hash(transaction.getRawData().toByteArray()));
    }

    public static String getShieldTransactionHash(Protocol.Transaction transaction, String str) throws ContractValidateException, ZksnarkException {
        List<Protocol.Transaction.Contract> contractList = transaction.getRawData().getContractList();
        if (contractList == null || contractList.size() == 0) {
            throw new ContractValidateException("Contract is null");
        }
        if (contractList.get(0).getType() != Protocol.Transaction.Contract.ContractType.ShieldedTransferContract) {
            throw new ContractValidateException("Not a shielded transaction");
        }
        return ByteArray.toHexString(getShieldTransactionHashIgnoreTypeException(transaction, str));
    }

    public static class fun1 {
        static final int[] $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType;

        static {
            int[] iArr = new int[Protocol.Transaction.Contract.ContractType.values().length];
            $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType = iArr;
            try {
                iArr[Protocol.Transaction.Contract.ContractType.AccountCreateContract.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.TransferContract.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.TransferAssetContract.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.VoteAssetContract.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.VoteWitnessContract.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.WitnessCreateContract.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.AssetIssueContract.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.WitnessUpdateContract.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.ParticipateAssetIssueContract.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.AccountUpdateContract.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.FreezeBalanceContract.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.FreezeBalanceV2Contract.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.UnfreezeBalanceContract.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.UnfreezeBalanceV2Contract.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.UnfreezeAssetContract.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.WithdrawBalanceContract.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.UpdateAssetContract.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.CreateSmartContract.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.TriggerSmartContract.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.AccountPermissionUpdateContract.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.UpdateBrokerageContract.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.ProposalCreateContract.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.ProposalDeleteContract.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.ProposalApproveContract.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.DelegateResourceContract.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.UnDelegateResourceContract.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.WithdrawExpireUnfreezeContract.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.CancelAllUnfreezeV2Contract.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.ExchangeTransactionContract.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.ExchangeWithdrawContract.ordinal()] = 30;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.ExchangeInjectContract.ordinal()] = 31;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.ExchangeCreateContract.ordinal()] = 32;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.UpdateEnergyLimitContract.ordinal()] = 33;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.ClearABIContract.ordinal()] = 34;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.SetAccountIdContract.ordinal()] = 35;
            } catch (NoSuchFieldError unused35) {
            }
        }
    }

    public static byte[] getOwner(Protocol.Transaction.Contract contract) {
        ByteString ownerAddress;
        try {
            switch (fun1.$SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[contract.getType().ordinal()]) {
                case 1:
                    ownerAddress = ((AccountContract.AccountCreateContract) unpackContract(contract, AccountContract.AccountCreateContract.class)).getOwnerAddress();
                    break;
                case 2:
                    ownerAddress = ((BalanceContract.TransferContract) unpackContract(contract, BalanceContract.TransferContract.class)).getOwnerAddress();
                    break;
                case 3:
                    ownerAddress = ((AssetIssueContractOuterClass.TransferAssetContract) unpackContract(contract, AssetIssueContractOuterClass.TransferAssetContract.class)).getOwnerAddress();
                    break;
                case 4:
                    ownerAddress = ((VoteAssetContractOuterClass.VoteAssetContract) unpackContract(contract, VoteAssetContractOuterClass.VoteAssetContract.class)).getOwnerAddress();
                    break;
                case 5:
                    ownerAddress = ((WitnessContract.VoteWitnessContract) unpackContract(contract, WitnessContract.VoteWitnessContract.class)).getOwnerAddress();
                    break;
                case 6:
                    ownerAddress = ((WitnessContract.WitnessCreateContract) unpackContract(contract, WitnessContract.WitnessCreateContract.class)).getOwnerAddress();
                    break;
                case 7:
                    ownerAddress = ((AssetIssueContractOuterClass.AssetIssueContract) unpackContract(contract, AssetIssueContractOuterClass.AssetIssueContract.class)).getOwnerAddress();
                    break;
                case 8:
                    ownerAddress = ((WitnessContract.WitnessUpdateContract) unpackContract(contract, WitnessContract.WitnessUpdateContract.class)).getOwnerAddress();
                    break;
                case 9:
                    ownerAddress = ((AssetIssueContractOuterClass.ParticipateAssetIssueContract) unpackContract(contract, AssetIssueContractOuterClass.ParticipateAssetIssueContract.class)).getOwnerAddress();
                    break;
                case 10:
                    ownerAddress = ((AccountContract.AccountUpdateContract) unpackContract(contract, AccountContract.AccountUpdateContract.class)).getOwnerAddress();
                    break;
                case 11:
                    ownerAddress = ((BalanceContract.FreezeBalanceContract) unpackContract(contract, BalanceContract.FreezeBalanceContract.class)).getOwnerAddress();
                    break;
                case 12:
                    ownerAddress = ((BalanceContract.FreezeBalanceV2Contract) unpackContract(contract, BalanceContract.FreezeBalanceV2Contract.class)).getOwnerAddress();
                    break;
                case 13:
                    ownerAddress = ((BalanceContract.UnfreezeBalanceContract) unpackContract(contract, BalanceContract.UnfreezeBalanceContract.class)).getOwnerAddress();
                    break;
                case 14:
                    ownerAddress = ((BalanceContract.UnfreezeBalanceV2Contract) unpackContract(contract, BalanceContract.UnfreezeBalanceV2Contract.class)).getOwnerAddress();
                    break;
                case 15:
                    ownerAddress = ((AssetIssueContractOuterClass.UnfreezeAssetContract) unpackContract(contract, AssetIssueContractOuterClass.UnfreezeAssetContract.class)).getOwnerAddress();
                    break;
                case 16:
                    ownerAddress = ((BalanceContract.WithdrawBalanceContract) unpackContract(contract, BalanceContract.WithdrawBalanceContract.class)).getOwnerAddress();
                    break;
                case 17:
                    ownerAddress = ((AssetIssueContractOuterClass.UpdateAssetContract) unpackContract(contract, AssetIssueContractOuterClass.UpdateAssetContract.class)).getOwnerAddress();
                    break;
                case 18:
                    ownerAddress = ((SmartContractOuterClass.CreateSmartContract) unpackContract(contract, SmartContractOuterClass.CreateSmartContract.class)).getOwnerAddress();
                    break;
                case 19:
                    ownerAddress = ((SmartContractOuterClass.TriggerSmartContract) unpackContract(contract, SmartContractOuterClass.TriggerSmartContract.class)).getOwnerAddress();
                    break;
                case 20:
                    ownerAddress = ((AccountContract.AccountPermissionUpdateContract) unpackContract(contract, AccountContract.AccountPermissionUpdateContract.class)).getOwnerAddress();
                    break;
                case 21:
                    ownerAddress = ((StorageContract.UpdateBrokerageContract) unpackContract(contract, StorageContract.UpdateBrokerageContract.class)).getOwnerAddress();
                    break;
                case 22:
                    ownerAddress = ((ProposalContract.ProposalCreateContract) unpackContract(contract, ProposalContract.ProposalCreateContract.class)).getOwnerAddress();
                    break;
                case 23:
                    ownerAddress = ((ProposalContract.ProposalDeleteContract) unpackContract(contract, ProposalContract.ProposalDeleteContract.class)).getOwnerAddress();
                    break;
                case 24:
                    ownerAddress = ((ProposalContract.ProposalApproveContract) unpackContract(contract, ProposalContract.ProposalApproveContract.class)).getOwnerAddress();
                    break;
                case 25:
                    ownerAddress = ((BalanceContract.DelegateResourceContract) unpackContract(contract, BalanceContract.DelegateResourceContract.class)).getOwnerAddress();
                    break;
                case 26:
                    ownerAddress = ((BalanceContract.UnDelegateResourceContract) unpackContract(contract, BalanceContract.UnDelegateResourceContract.class)).getOwnerAddress();
                    break;
                case 27:
                    ownerAddress = ((BalanceContract.WithdrawExpireUnfreezeContract) unpackContract(contract, BalanceContract.WithdrawExpireUnfreezeContract.class)).getOwnerAddress();
                    break;
                case 28:
                    ownerAddress = ((BalanceContract.CancelAllUnfreezeV2Contract) unpackContract(contract, BalanceContract.CancelAllUnfreezeV2Contract.class)).getOwnerAddress();
                    break;
                case 29:
                    ownerAddress = ((ExchangeContract.ExchangeTransactionContract) unpackContract(contract, ExchangeContract.ExchangeTransactionContract.class)).getOwnerAddress();
                    break;
                case 30:
                    ownerAddress = ((ExchangeContract.ExchangeWithdrawContract) unpackContract(contract, ExchangeContract.ExchangeWithdrawContract.class)).getOwnerAddress();
                    break;
                case 31:
                    ownerAddress = ((ExchangeContract.ExchangeInjectContract) unpackContract(contract, ExchangeContract.ExchangeInjectContract.class)).getOwnerAddress();
                    break;
                case 32:
                    ownerAddress = ((ExchangeContract.ExchangeCreateContract) unpackContract(contract, ExchangeContract.ExchangeCreateContract.class)).getOwnerAddress();
                    break;
                case 33:
                    ownerAddress = ((SmartContractOuterClass.UpdateEnergyLimitContract) unpackContract(contract, SmartContractOuterClass.UpdateEnergyLimitContract.class)).getOwnerAddress();
                    break;
                case 34:
                    ownerAddress = ((SmartContractOuterClass.ClearABIContract) unpackContract(contract, SmartContractOuterClass.ClearABIContract.class)).getOwnerAddress();
                    break;
                case 35:
                    ownerAddress = ((AccountContract.SetAccountIdContract) unpackContract(contract, AccountContract.SetAccountIdContract.class)).getOwnerAddress();
                    break;
                default:
                    return null;
            }
            return ownerAddress.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getBase64FromByteString(ByteString byteString) {
        byte[] byteArray = byteString.substring(0, 32).toByteArray();
        byte[] byteArray2 = byteString.substring(32, 64).toByteArray();
        byte byteAt = byteString.byteAt(64);
        if (byteAt < 27) {
            byteAt = (byte) (byteAt + Ascii.ESC);
        }
        return ECKey.ECDSASignature.fromComponents(byteArray, byteArray2, byteAt).toBase64();
    }

    public static boolean validTransaction(Protocol.Transaction transaction) {
        byte[] byteArray;
        if (transaction.getRawData().getContract(0).getType() != Protocol.Transaction.Contract.ContractType.ShieldedTransferContract) {
            return validTransaction3(transaction);
        }
        try {
            ShieldContract.ShieldedTransferContract shieldedTransferContract = (ShieldContract.ShieldedTransferContract) unpackContract(transaction.getRawData().getContract(0), ShieldContract.ShieldedTransferContract.class);
            if (shieldedTransferContract.getFromAmount() != 0) {
                return validTransaction3(transaction);
            }
            List<ShieldContract.SpendDescription> spendDescriptionList = shieldedTransferContract.getSpendDescriptionList();
            return (spendDescriptionList == null || spendDescriptionList.isEmpty() || (byteArray = spendDescriptionList.get(0).getSpendAuthoritySignature().toByteArray()) == null || byteArray.length == 0) ? false : true;
        } catch (InvalidProtocolBufferException e) {
            LogUtils.e(e);
            return false;
        }
    }

    public static boolean validTransaction3(Protocol.Transaction transaction) {
        List<Protocol.Transaction.Contract> contractList = transaction.getRawData().getContractList();
        byte[] sha256 = Hash.sha256(transaction.getRawData().toByteArray());
        int signatureCount = transaction.getSignatureCount();
        if (signatureCount == 0) {
            return false;
        }
        for (int i = 0; i < signatureCount; i++) {
            try {
                getOwner(contractList.get(i));
                ECKey.signatureToAddress(sha256, getBase64FromByteString(transaction.getSignature(i)));
            } catch (SignatureException e) {
                LogUtils.e(e);
                return false;
            }
        }
        return true;
    }

    public static Protocol.Transaction sign(Protocol.Transaction transaction, ECKey eCKey) {
        Protocol.Transaction.Builder builder = transaction.toBuilder();
        byte[] sha256 = Hash.sha256(transaction.getRawData().toByteArray());
        List<Protocol.Transaction.Contract> contractList = transaction.getRawData().getContractList();
        for (int i = 0; i < contractList.size(); i++) {
            if (eCKey != null) {
                builder.addSignature(ByteString.copyFrom(eCKey.sign(sha256).toByteArray()));
            }
        }
        return builder.build();
    }

    public static Protocol.Transaction sign(Protocol.Transaction transaction, ECKey eCKey, byte[] bArr, boolean z) {
        if (transaction == null) {
            return null;
        }
        Protocol.Transaction.Builder builder = transaction.toBuilder();
        byte[] hash = Sha256Hash.hash(transaction.getRawData().toByteArray());
        if (hash == null || hash.length == 0) {
            return builder.build();
        }
        if (!z) {
            byte[] copyOf = Arrays.copyOf(hash, hash.length + bArr.length);
            System.arraycopy(bArr, 0, copyOf, hash.length, bArr.length);
            hash = Sha256Hash.hash(copyOf);
        }
        builder.addSignature(ByteString.copyFrom(eCKey.sign(hash).toByteArray()));
        return builder.build();
    }

    public static String sign(String str, ECKey eCKey) {
        byte[] fromString;
        String replaceFirst = str.replaceFirst("0x", "");
        if (AddressUtil.isHexString(replaceFirst)) {
            fromString = ByteArray.fromHexString(replaceFirst);
        } else {
            fromString = ByteArray.fromString(replaceFirst);
        }
        Sign.SignatureData signPrefixedMessage = Sign.signPrefixedMessage(fromString, ECKeyPair.create(eCKey.getPrivKey()));
        return new StringBuffer("0x").append(ByteArray.toHexString(signPrefixedMessage.getR())).append(ByteArray.toHexString(signPrefixedMessage.getS())).append(ByteArray.toHexString(new byte[]{signPrefixedMessage.getV()})).toString();
    }

    public static String signMessageV2(byte[] bArr, ECKey eCKey) {
        Sign.SignatureData signPrefixedMessageV2 = Sign.signPrefixedMessageV2(bArr, ECKeyPair.create(eCKey.getPrivKey()));
        return new StringBuffer("0x").append(ByteArray.toHexString(signPrefixedMessageV2.getR())).append(ByteArray.toHexString(signPrefixedMessageV2.getS())).append(ByteArray.toHexString(new byte[]{signPrefixedMessageV2.getV()})).toString();
    }

    public static String signStructuredData(String str, ECKey eCKey) {
        Sign.SignatureData signMessage = Sign.signMessage(Numeric.hexStringToByteArray(str), ECKeyPair.create(eCKey.getPrivKey()), false);
        return new StringBuffer("0x").append(ByteArray.toHexString(signMessage.getR())).append(ByteArray.toHexString(signMessage.getS())).append(ByteArray.toHexString(new byte[]{signMessage.getV()})).toString();
    }

    public static byte[] getMessageHash(String str) {
        byte[] fromString;
        String replaceFirst = str.replaceFirst("0x", "");
        if (AddressUtil.isHexString(replaceFirst)) {
            fromString = ByteArray.fromHexString(replaceFirst);
        } else {
            fromString = ByteArray.fromString(replaceFirst);
        }
        return Sign.getPrefixedMessageHash(fromString);
    }

    public static byte[] getMessageHashV2(byte[] bArr) {
        return Sign.getPrefixedMessageHashV2(bArr);
    }

    public static String enCodeSignature(byte[] bArr) {
        byte[] bArr2 = new byte[32];
        byte[] bArr3 = new byte[32];
        byte b = bArr[64];
        System.arraycopy(bArr, 0, bArr2, 0, 32);
        System.arraycopy(bArr, 32, bArr3, 0, 32);
        if (b < 27) {
            b = (byte) (b + Ascii.ESC);
        }
        Sign.SignatureData signatureData = new Sign.SignatureData(b, bArr2, bArr3);
        return new StringBuffer("0x").append(ByteArray.toHexString(signatureData.getR())).append(ByteArray.toHexString(signatureData.getS())).append(ByteArray.toHexString(new byte[]{signatureData.getV()})).toString();
    }

    public static Protocol.Transaction addSignature(Protocol.Transaction transaction, byte[] bArr) {
        return transaction == null ? transaction : transaction.toBuilder().addSignature(ByteString.copyFrom(bArr)).build();
    }

    public static boolean verifyMessage(String str, String str2, String str3) {
        byte[] fromString;
        if (AddressUtil.isEmpty(str, str3, str2)) {
            return false;
        }
        try {
            String replaceFirst = str.replaceFirst("0x", "");
            String replaceFirst2 = str2.replaceFirst("0x", "");
            if (AddressUtil.isHexString(replaceFirst2)) {
                fromString = ByteArray.fromHexString(replaceFirst2);
            } else {
                fromString = ByteArray.fromString(replaceFirst2);
            }
            byte[] bArr = new byte[32];
            byte[] bArr2 = new byte[32];
            byte b = fromString[64];
            System.arraycopy(fromString, 0, bArr, 0, 32);
            System.arraycopy(fromString, 32, bArr2, 0, 32);
            if (b < 27) {
                b = (byte) (b + Ascii.ESC);
            }
            ECKey.ECDSASignature fromComponents = ECKey.ECDSASignature.fromComponents(bArr, bArr2, b);
            byte[] signatureToAddress = ECKey.signatureToAddress(Hash.sha3(("\u0019TRON Signed Message:\n32" + replaceFirst).getBytes()), fromComponents);
            if (ArrayUtils.isEmpty(signatureToAddress)) {
                return false;
            }
            return Arrays.equals(signatureToAddress, AddressUtil.decode58Check(str3));
        } catch (Exception e) {
            LogUtils.e(e);
            return false;
        }
    }

    public static Protocol.Transaction setExpiration(Protocol.Transaction transaction, long j) {
        return transaction.toBuilder().setRawData(transaction.getRawData().toBuilder().setExpiration(j).build()).build();
    }

    public static Protocol.Transaction setTimestamp(Protocol.Transaction transaction) {
        return setTimestamp(transaction, 0L);
    }

    public static Protocol.Transaction setTimestamp(Protocol.Transaction transaction, long j) {
        long currentTimeMillis = System.currentTimeMillis();
        Protocol.Transaction.Builder builder = transaction.toBuilder();
        Protocol.Transaction.raw.Builder builder2 = transaction.getRawData().toBuilder();
        if (j != 0) {
            builder2.setTimestamp(currentTimeMillis);
        }
        builder.setRawData(builder2.build());
        return builder.build();
    }

    public static String getOwner(Protocol.Transaction transaction) throws InvalidProtocolBufferException {
        return (transaction == null || transaction.getRawData() == null || transaction.getRawData().getContractCount() < 1 || getOwner(transaction.getRawData().getContract(0)) == null) ? "" : AddressUtil.encode58Check(getOwner(transaction.getRawData().getContract(0)));
    }

    public static long bandwidthCost(Protocol.Transaction transaction) {
        return transaction.getSerializedSize() + 135;
    }

    public static byte[] getShieldTransactionHashIgnoreTypeException(Protocol.Transaction transaction, String str) {
        try {
            return hashShieldTransaction(transaction, str);
        } catch (Exception unused) {
            return null;
        }
    }

    public static byte[] hashShieldTransaction(Protocol.Transaction transaction, String str) throws InvalidProtocolBufferException, TronException {
        Any parameter = transaction.getRawData().getContract(0).getParameter();
        if (!parameter.is(ShieldContract.ShieldedTransferContract.class)) {
            throw new TronException("ContractValidateException:contract type error,expected type [ShieldedTransferContract],real type[" + parameter.getClass() + "]");
        }
        ShieldContract.ShieldedTransferContract shieldedTransferContract = (ShieldContract.ShieldedTransferContract) parameter.unpack(ShieldContract.ShieldedTransferContract.class);
        ShieldContract.ShieldedTransferContract.Builder newBuilder = ShieldContract.ShieldedTransferContract.newBuilder();
        newBuilder.setFromAmount(shieldedTransferContract.getFromAmount());
        newBuilder.addAllReceiveDescription(shieldedTransferContract.getReceiveDescriptionList());
        newBuilder.setToAmount(shieldedTransferContract.getToAmount());
        newBuilder.setTransparentFromAddress(shieldedTransferContract.getTransparentFromAddress());
        newBuilder.setTransparentToAddress(shieldedTransferContract.getTransparentToAddress());
        for (ShieldContract.SpendDescription spendDescription : shieldedTransferContract.getSpendDescriptionList()) {
            newBuilder.addSpendDescription(spendDescription.toBuilder().clearSpendAuthoritySignature().build());
        }
        return Sha256Hash.of(Bytes.concat(Sha256Hash.of(str.getBytes()).getBytes(), transaction.toBuilder().clearRawData().setRawData(transaction.toBuilder().getRawDataBuilder().clearContract().addContract(Protocol.Transaction.Contract.newBuilder().setType(Protocol.Transaction.Contract.ContractType.ShieldedTransferContract).setParameter(Any.pack(newBuilder.build())).build())).build().getRawData().toByteArray())).getBytes();
    }

    public static Protocol.Transaction createTransactionCapsuleWithoutValidate(Message message, Protocol.Transaction.Contract.ContractType contractType) {
        Protocol.Transaction build = Protocol.Transaction.newBuilder().setRawData(Protocol.Transaction.raw.newBuilder().addContract(Protocol.Transaction.Contract.newBuilder().setType(contractType).setParameter(Any.pack(message)).build()).build()).build();
        Protocol.BlockOrBuilder blockOrBuilder = null;
        try {
            setReference(build, null);
            blockOrBuilder.getBlockHeader();
            throw null;
        } catch (Exception e) {
            LogUtils.i("Create transaction capsule failed." + e.getMessage());
            return build;
        }
    }

    public static Protocol.Transaction addMemo(Protocol.Transaction transaction, String str) {
        if (AddressUtil.isEmpty(str) || transaction == null || transaction.toString().equals("")) {
            return transaction;
        }
        byte[] fromString = ByteArray.fromString(str);
        Protocol.Transaction.Builder builder = transaction.toBuilder();
        Protocol.Transaction.raw.Builder builder2 = transaction.getRawData().toBuilder();
        builder2.setData(ByteString.copyFrom(fromString));
        return builder.setRawData(builder2.build()).build();
    }

    public static String getTriggerHash(Protocol.Transaction transaction) {
        if (transaction != null && !"".equals(transaction.toString()) && transaction.getRawData() != null && transaction.getRawData().getContractCount() >= 1) {
            Protocol.Transaction.Contract contract = transaction.getRawData().getContract(0);
            if (contract.getType() != Protocol.Transaction.Contract.ContractType.TriggerSmartContract) {
                return "";
            }
            try {
                return ByteArray.toHexString(Hash.sha256(((SmartContractOuterClass.TriggerSmartContract) unpackContract(contract, SmartContractOuterClass.TriggerSmartContract.class)).getData().toByteArray()));
            } catch (InvalidProtocolBufferException e) {
                LogUtils.e(e);
            }
        }
        return "";
    }

    public static Protocol.Transaction createCustomContract(String str) {
        Protocol.Transaction.Builder newBuilder = Protocol.Transaction.newBuilder();
        Protocol.Transaction.raw.Builder newBuilder2 = Protocol.Transaction.raw.newBuilder();
        Protocol.Transaction.Contract.Builder newBuilder3 = Protocol.Transaction.Contract.newBuilder();
        newBuilder3.setContractName(ByteString.copyFrom(str.getBytes()));
        newBuilder2.addContract(newBuilder3.build());
        newBuilder.setRawData(newBuilder2.build());
        return newBuilder.build();
    }

    public static String getTransactionSignatureOwner(Protocol.Transaction transaction) {
        try {
            return AddressUtil.encode58Check(ECKey.signatureToAddress(Hash.sha256(transaction.getRawData().toByteArray()), getBase64FromByteString(transaction.getSignature(0))));
        } catch (Exception e) {
            LogUtils.e(e);
            return "";
        }
    }

    public static boolean checkTransactionEmpty(GrpcAPI.TransactionExtention transactionExtention) {
        return transactionExtention == null || !transactionExtention.hasTransaction() || transactionExtention.getTransaction() == null || transactionExtention.getTransaction().toString().length() <= 0 || transactionExtention.getTransaction().getRawData() == null;
    }

    public static String getErrorMessage(GrpcAPI.TransactionExtention transactionExtention) {
        return (transactionExtention == null || transactionExtention.getResult() == null || transactionExtention.getResult().getMessage() == null || transactionExtention.getResult().getMessage().isEmpty()) ? "" : new String(transactionExtention.getResult().getMessage().toByteArray());
    }

    public static Protocol.Transaction replaceVoteWitnessContract(Protocol.Transaction transaction, WitnessContract.VoteWitnessContract voteWitnessContract) {
        try {
            return transaction.toBuilder().clearRawData().setRawData(transaction.toBuilder().getRawDataBuilder().clearContract().addContract(Protocol.Transaction.Contract.newBuilder().setType(Protocol.Transaction.Contract.ContractType.VoteWitnessContract).setParameter(Any.pack(voteWitnessContract)))).build();
        } catch (Exception e) {
            LogUtils.e(e);
            return null;
        }
    }
}
