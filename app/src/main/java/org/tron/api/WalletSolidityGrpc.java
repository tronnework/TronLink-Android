package org.tron.api;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.Descriptors;
import io.grpc.BindableService;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.MethodDescriptor;
import io.grpc.ServerServiceDefinition;
import io.grpc.ServiceDescriptor;
import io.grpc.protobuf.ProtoFileDescriptorSupplier;
import io.grpc.protobuf.ProtoMethodDescriptorSupplier;
import io.grpc.protobuf.ProtoServiceDescriptorSupplier;
import io.grpc.protobuf.ProtoUtils;
import io.grpc.stub.AbstractStub;
import io.grpc.stub.ClientCalls;
import io.grpc.stub.ServerCalls;
import io.grpc.stub.StreamObserver;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.protos.contract.AssetIssueContractOuterClass;
import org.tron.protos.contract.ShieldContract;
import org.tron.protos.contract.SmartContractOuterClass;
public final class WalletSolidityGrpc {
    private static final int METHODID_ESTIMATE_ENERGY = 35;
    private static final int METHODID_GET_ACCOUNT = 0;
    private static final int METHODID_GET_ACCOUNT_BY_ID = 1;
    private static final int METHODID_GET_ASSET_ISSUE_BY_ID = 7;
    private static final int METHODID_GET_ASSET_ISSUE_BY_NAME = 5;
    private static final int METHODID_GET_ASSET_ISSUE_LIST = 3;
    private static final int METHODID_GET_ASSET_ISSUE_LIST_BY_NAME = 6;
    private static final int METHODID_GET_AVAILABLE_UNFREEZE_COUNT = 18;
    private static final int METHODID_GET_BLOCK = 43;
    private static final int METHODID_GET_BLOCK_BY_NUM = 10;
    private static final int METHODID_GET_BLOCK_BY_NUM2 = 11;
    private static final int METHODID_GET_BROKERAGE_INFO = 33;
    private static final int METHODID_GET_BURN_TRX = 42;
    private static final int METHODID_GET_CAN_DELEGATED_MAX_SIZE = 17;
    private static final int METHODID_GET_CAN_WITHDRAW_UNFREEZE_AMOUNT = 19;
    private static final int METHODID_GET_DELEGATED_RESOURCE = 13;
    private static final int METHODID_GET_DELEGATED_RESOURCE_ACCOUNT_INDEX = 15;
    private static final int METHODID_GET_DELEGATED_RESOURCE_ACCOUNT_INDEX_V2 = 16;
    private static final int METHODID_GET_DELEGATED_RESOURCE_V2 = 14;
    private static final int METHODID_GET_EXCHANGE_BY_ID = 20;
    private static final int METHODID_GET_MARKET_ORDER_BY_ACCOUNT = 38;
    private static final int METHODID_GET_MARKET_ORDER_BY_ID = 37;
    private static final int METHODID_GET_MARKET_ORDER_LIST_BY_PAIR = 40;
    private static final int METHODID_GET_MARKET_PAIR_LIST = 41;
    private static final int METHODID_GET_MARKET_PRICE_BY_PAIR = 39;
    private static final int METHODID_GET_MERKLE_TREE_VOUCHER_INFO = 24;
    private static final int METHODID_GET_NOW_BLOCK = 8;
    private static final int METHODID_GET_NOW_BLOCK2 = 9;
    private static final int METHODID_GET_PAGINATED_ASSET_ISSUE_LIST = 4;
    private static final int METHODID_GET_REWARD_INFO = 32;
    private static final int METHODID_GET_TRANSACTION_BY_ID = 22;
    private static final int METHODID_GET_TRANSACTION_COUNT_BY_BLOCK_NUM = 12;
    private static final int METHODID_GET_TRANSACTION_INFO_BY_BLOCK_NUM = 36;
    private static final int METHODID_GET_TRANSACTION_INFO_BY_ID = 23;
    private static final int METHODID_IS_SHIELDED_TRC20CONTRACT_NOTE_SPENT = 31;
    private static final int METHODID_IS_SPEND = 28;
    private static final int METHODID_LIST_EXCHANGES = 21;
    private static final int METHODID_LIST_WITNESSES = 2;
    private static final int METHODID_SCAN_AND_MARK_NOTE_BY_IVK = 26;
    private static final int METHODID_SCAN_NOTE_BY_IVK = 25;
    private static final int METHODID_SCAN_NOTE_BY_OVK = 27;
    private static final int METHODID_SCAN_SHIELDED_TRC20NOTES_BY_IVK = 29;
    private static final int METHODID_SCAN_SHIELDED_TRC20NOTES_BY_OVK = 30;
    private static final int METHODID_TRIGGER_CONSTANT_CONTRACT = 34;
    public static final String SERVICE_NAME = "protocol.WalletSolidity";
    private static volatile MethodDescriptor<SmartContractOuterClass.TriggerSmartContract, GrpcAPI.EstimateEnergyMessage> getEstimateEnergyMethod;
    private static volatile MethodDescriptor<Protocol.Account, Protocol.Account> getGetAccountByIdMethod;
    private static volatile MethodDescriptor<Protocol.Account, Protocol.Account> getGetAccountMethod;
    private static volatile MethodDescriptor<GrpcAPI.BytesMessage, AssetIssueContractOuterClass.AssetIssueContract> getGetAssetIssueByIdMethod;
    private static volatile MethodDescriptor<GrpcAPI.BytesMessage, AssetIssueContractOuterClass.AssetIssueContract> getGetAssetIssueByNameMethod;
    private static volatile MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.AssetIssueList> getGetAssetIssueListByNameMethod;
    private static volatile MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.AssetIssueList> getGetAssetIssueListMethod;
    private static volatile MethodDescriptor<GrpcAPI.GetAvailableUnfreezeCountRequestMessage, GrpcAPI.GetAvailableUnfreezeCountResponseMessage> getGetAvailableUnfreezeCountMethod;
    private static volatile MethodDescriptor<GrpcAPI.NumberMessage, GrpcAPI.BlockExtention> getGetBlockByNum2Method;
    private static volatile MethodDescriptor<GrpcAPI.NumberMessage, Protocol.Block> getGetBlockByNumMethod;
    private static volatile MethodDescriptor<GrpcAPI.BlockReq, GrpcAPI.BlockExtention> getGetBlockMethod;
    private static volatile MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.NumberMessage> getGetBrokerageInfoMethod;
    private static volatile MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.NumberMessage> getGetBurnTrxMethod;
    private static volatile MethodDescriptor<GrpcAPI.CanDelegatedMaxSizeRequestMessage, GrpcAPI.CanDelegatedMaxSizeResponseMessage> getGetCanDelegatedMaxSizeMethod;
    private static volatile MethodDescriptor<GrpcAPI.CanWithdrawUnfreezeAmountRequestMessage, GrpcAPI.CanWithdrawUnfreezeAmountResponseMessage> getGetCanWithdrawUnfreezeAmountMethod;
    private static volatile MethodDescriptor<GrpcAPI.BytesMessage, Protocol.DelegatedResourceAccountIndex> getGetDelegatedResourceAccountIndexMethod;
    private static volatile MethodDescriptor<GrpcAPI.BytesMessage, Protocol.DelegatedResourceAccountIndex> getGetDelegatedResourceAccountIndexV2Method;
    private static volatile MethodDescriptor<GrpcAPI.DelegatedResourceMessage, GrpcAPI.DelegatedResourceList> getGetDelegatedResourceMethod;
    private static volatile MethodDescriptor<GrpcAPI.DelegatedResourceMessage, GrpcAPI.DelegatedResourceList> getGetDelegatedResourceV2Method;
    private static volatile MethodDescriptor<GrpcAPI.BytesMessage, Protocol.Exchange> getGetExchangeByIdMethod;
    private static volatile MethodDescriptor<GrpcAPI.BytesMessage, Protocol.MarketOrderList> getGetMarketOrderByAccountMethod;
    private static volatile MethodDescriptor<GrpcAPI.BytesMessage, Protocol.MarketOrder> getGetMarketOrderByIdMethod;
    private static volatile MethodDescriptor<Protocol.MarketOrderPair, Protocol.MarketOrderList> getGetMarketOrderListByPairMethod;
    private static volatile MethodDescriptor<GrpcAPI.EmptyMessage, Protocol.MarketOrderPairList> getGetMarketPairListMethod;
    private static volatile MethodDescriptor<Protocol.MarketOrderPair, Protocol.MarketPriceList> getGetMarketPriceByPairMethod;
    private static volatile MethodDescriptor<ShieldContract.OutputPointInfo, ShieldContract.IncrementalMerkleVoucherInfo> getGetMerkleTreeVoucherInfoMethod;
    private static volatile MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.BlockExtention> getGetNowBlock2Method;
    private static volatile MethodDescriptor<GrpcAPI.EmptyMessage, Protocol.Block> getGetNowBlockMethod;
    private static volatile MethodDescriptor<GrpcAPI.PaginatedMessage, GrpcAPI.AssetIssueList> getGetPaginatedAssetIssueListMethod;
    private static volatile MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.NumberMessage> getGetRewardInfoMethod;
    private static volatile MethodDescriptor<GrpcAPI.BytesMessage, Protocol.Transaction> getGetTransactionByIdMethod;
    private static volatile MethodDescriptor<GrpcAPI.NumberMessage, GrpcAPI.NumberMessage> getGetTransactionCountByBlockNumMethod;
    private static volatile MethodDescriptor<GrpcAPI.NumberMessage, GrpcAPI.TransactionInfoList> getGetTransactionInfoByBlockNumMethod;
    private static volatile MethodDescriptor<GrpcAPI.BytesMessage, Protocol.TransactionInfo> getGetTransactionInfoByIdMethod;
    private static volatile MethodDescriptor<GrpcAPI.NfTRC20Parameters, GrpcAPI.NullifierResult> getIsShieldedTRC20ContractNoteSpentMethod;
    private static volatile MethodDescriptor<GrpcAPI.NoteParameters, GrpcAPI.SpendResult> getIsSpendMethod;
    private static volatile MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.ExchangeList> getListExchangesMethod;
    private static volatile MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.WitnessList> getListWitnessesMethod;
    private static volatile MethodDescriptor<GrpcAPI.IvkDecryptAndMarkParameters, GrpcAPI.DecryptNotesMarked> getScanAndMarkNoteByIvkMethod;
    private static volatile MethodDescriptor<GrpcAPI.IvkDecryptParameters, GrpcAPI.DecryptNotes> getScanNoteByIvkMethod;
    private static volatile MethodDescriptor<GrpcAPI.OvkDecryptParameters, GrpcAPI.DecryptNotes> getScanNoteByOvkMethod;
    private static volatile MethodDescriptor<GrpcAPI.IvkDecryptTRC20Parameters, GrpcAPI.DecryptNotesTRC20> getScanShieldedTRC20NotesByIvkMethod;
    private static volatile MethodDescriptor<GrpcAPI.OvkDecryptTRC20Parameters, GrpcAPI.DecryptNotesTRC20> getScanShieldedTRC20NotesByOvkMethod;
    private static volatile MethodDescriptor<SmartContractOuterClass.TriggerSmartContract, GrpcAPI.TransactionExtention> getTriggerConstantContractMethod;
    private static volatile ServiceDescriptor serviceDescriptor;
    @Deprecated
    public static final MethodDescriptor<Protocol.Account, Protocol.Account> METHOD_GET_ACCOUNT = getGetAccountMethod();
    @Deprecated
    public static final MethodDescriptor<Protocol.Account, Protocol.Account> METHOD_GET_ACCOUNT_BY_ID = getGetAccountByIdMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.WitnessList> METHOD_LIST_WITNESSES = getListWitnessesMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.AssetIssueList> METHOD_GET_ASSET_ISSUE_LIST = getGetAssetIssueListMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.PaginatedMessage, GrpcAPI.AssetIssueList> METHOD_GET_PAGINATED_ASSET_ISSUE_LIST = getGetPaginatedAssetIssueListMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.BytesMessage, AssetIssueContractOuterClass.AssetIssueContract> METHOD_GET_ASSET_ISSUE_BY_NAME = getGetAssetIssueByNameMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.AssetIssueList> METHOD_GET_ASSET_ISSUE_LIST_BY_NAME = getGetAssetIssueListByNameMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.BytesMessage, AssetIssueContractOuterClass.AssetIssueContract> METHOD_GET_ASSET_ISSUE_BY_ID = getGetAssetIssueByIdMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.EmptyMessage, Protocol.Block> METHOD_GET_NOW_BLOCK = getGetNowBlockMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.BlockExtention> METHOD_GET_NOW_BLOCK2 = getGetNowBlock2Method();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.NumberMessage, Protocol.Block> METHOD_GET_BLOCK_BY_NUM = getGetBlockByNumMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.NumberMessage, GrpcAPI.BlockExtention> METHOD_GET_BLOCK_BY_NUM2 = getGetBlockByNum2Method();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.NumberMessage, GrpcAPI.NumberMessage> METHOD_GET_TRANSACTION_COUNT_BY_BLOCK_NUM = getGetTransactionCountByBlockNumMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.DelegatedResourceMessage, GrpcAPI.DelegatedResourceList> METHOD_GET_DELEGATED_RESOURCE = getGetDelegatedResourceMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.DelegatedResourceMessage, GrpcAPI.DelegatedResourceList> METHOD_GET_DELEGATED_RESOURCE_V2 = getGetDelegatedResourceV2Method();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.BytesMessage, Protocol.DelegatedResourceAccountIndex> METHOD_GET_DELEGATED_RESOURCE_ACCOUNT_INDEX = getGetDelegatedResourceAccountIndexMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.BytesMessage, Protocol.DelegatedResourceAccountIndex> METHOD_GET_DELEGATED_RESOURCE_ACCOUNT_INDEX_V2 = getGetDelegatedResourceAccountIndexV2Method();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.CanDelegatedMaxSizeRequestMessage, GrpcAPI.CanDelegatedMaxSizeResponseMessage> METHOD_GET_CAN_DELEGATED_MAX_SIZE = getGetCanDelegatedMaxSizeMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.GetAvailableUnfreezeCountRequestMessage, GrpcAPI.GetAvailableUnfreezeCountResponseMessage> METHOD_GET_AVAILABLE_UNFREEZE_COUNT = getGetAvailableUnfreezeCountMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.CanWithdrawUnfreezeAmountRequestMessage, GrpcAPI.CanWithdrawUnfreezeAmountResponseMessage> METHOD_GET_CAN_WITHDRAW_UNFREEZE_AMOUNT = getGetCanWithdrawUnfreezeAmountMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.BytesMessage, Protocol.Exchange> METHOD_GET_EXCHANGE_BY_ID = getGetExchangeByIdMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.ExchangeList> METHOD_LIST_EXCHANGES = getListExchangesMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.BytesMessage, Protocol.Transaction> METHOD_GET_TRANSACTION_BY_ID = getGetTransactionByIdMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.BytesMessage, Protocol.TransactionInfo> METHOD_GET_TRANSACTION_INFO_BY_ID = getGetTransactionInfoByIdMethod();
    @Deprecated
    public static final MethodDescriptor<ShieldContract.OutputPointInfo, ShieldContract.IncrementalMerkleVoucherInfo> METHOD_GET_MERKLE_TREE_VOUCHER_INFO = getGetMerkleTreeVoucherInfoMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.IvkDecryptParameters, GrpcAPI.DecryptNotes> METHOD_SCAN_NOTE_BY_IVK = getScanNoteByIvkMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.IvkDecryptAndMarkParameters, GrpcAPI.DecryptNotesMarked> METHOD_SCAN_AND_MARK_NOTE_BY_IVK = getScanAndMarkNoteByIvkMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.OvkDecryptParameters, GrpcAPI.DecryptNotes> METHOD_SCAN_NOTE_BY_OVK = getScanNoteByOvkMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.NoteParameters, GrpcAPI.SpendResult> METHOD_IS_SPEND = getIsSpendMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.IvkDecryptTRC20Parameters, GrpcAPI.DecryptNotesTRC20> METHOD_SCAN_SHIELDED_TRC20NOTES_BY_IVK = getScanShieldedTRC20NotesByIvkMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.OvkDecryptTRC20Parameters, GrpcAPI.DecryptNotesTRC20> METHOD_SCAN_SHIELDED_TRC20NOTES_BY_OVK = getScanShieldedTRC20NotesByOvkMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.NfTRC20Parameters, GrpcAPI.NullifierResult> METHOD_IS_SHIELDED_TRC20CONTRACT_NOTE_SPENT = getIsShieldedTRC20ContractNoteSpentMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.NumberMessage> METHOD_GET_REWARD_INFO = getGetRewardInfoMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.NumberMessage> METHOD_GET_BROKERAGE_INFO = getGetBrokerageInfoMethod();
    @Deprecated
    public static final MethodDescriptor<SmartContractOuterClass.TriggerSmartContract, GrpcAPI.TransactionExtention> METHOD_TRIGGER_CONSTANT_CONTRACT = getTriggerConstantContractMethod();
    @Deprecated
    public static final MethodDescriptor<SmartContractOuterClass.TriggerSmartContract, GrpcAPI.EstimateEnergyMessage> METHOD_ESTIMATE_ENERGY = getEstimateEnergyMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.NumberMessage, GrpcAPI.TransactionInfoList> METHOD_GET_TRANSACTION_INFO_BY_BLOCK_NUM = getGetTransactionInfoByBlockNumMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.BytesMessage, Protocol.MarketOrder> METHOD_GET_MARKET_ORDER_BY_ID = getGetMarketOrderByIdMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.BytesMessage, Protocol.MarketOrderList> METHOD_GET_MARKET_ORDER_BY_ACCOUNT = getGetMarketOrderByAccountMethod();
    @Deprecated
    public static final MethodDescriptor<Protocol.MarketOrderPair, Protocol.MarketPriceList> METHOD_GET_MARKET_PRICE_BY_PAIR = getGetMarketPriceByPairMethod();
    @Deprecated
    public static final MethodDescriptor<Protocol.MarketOrderPair, Protocol.MarketOrderList> METHOD_GET_MARKET_ORDER_LIST_BY_PAIR = getGetMarketOrderListByPairMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.EmptyMessage, Protocol.MarketOrderPairList> METHOD_GET_MARKET_PAIR_LIST = getGetMarketPairListMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.NumberMessage> METHOD_GET_BURN_TRX = getGetBurnTrxMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.BlockReq, GrpcAPI.BlockExtention> METHOD_GET_BLOCK = getGetBlockMethod();

    private WalletSolidityGrpc() {
    }

    public static MethodDescriptor<Protocol.Account, Protocol.Account> getGetAccountMethod() {
        MethodDescriptor<Protocol.Account, Protocol.Account> methodDescriptor = getGetAccountMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getGetAccountMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetAccount")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(Protocol.Account.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.Account.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("GetAccount")).build();
                    getGetAccountMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<Protocol.Account, Protocol.Account> getGetAccountByIdMethod() {
        MethodDescriptor<Protocol.Account, Protocol.Account> methodDescriptor = getGetAccountByIdMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getGetAccountByIdMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetAccountById")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(Protocol.Account.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.Account.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("GetAccountById")).build();
                    getGetAccountByIdMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.WitnessList> getListWitnessesMethod() {
        MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.WitnessList> methodDescriptor = getListWitnessesMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getListWitnessesMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "ListWitnesses")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.EmptyMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.WitnessList.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("ListWitnesses")).build();
                    getListWitnessesMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.AssetIssueList> getGetAssetIssueListMethod() {
        MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.AssetIssueList> methodDescriptor = getGetAssetIssueListMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getGetAssetIssueListMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetAssetIssueList")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.EmptyMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.AssetIssueList.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("GetAssetIssueList")).build();
                    getGetAssetIssueListMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.PaginatedMessage, GrpcAPI.AssetIssueList> getGetPaginatedAssetIssueListMethod() {
        MethodDescriptor<GrpcAPI.PaginatedMessage, GrpcAPI.AssetIssueList> methodDescriptor = getGetPaginatedAssetIssueListMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getGetPaginatedAssetIssueListMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetPaginatedAssetIssueList")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.PaginatedMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.AssetIssueList.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("GetPaginatedAssetIssueList")).build();
                    getGetPaginatedAssetIssueListMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BytesMessage, AssetIssueContractOuterClass.AssetIssueContract> getGetAssetIssueByNameMethod() {
        MethodDescriptor<GrpcAPI.BytesMessage, AssetIssueContractOuterClass.AssetIssueContract> methodDescriptor = getGetAssetIssueByNameMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getGetAssetIssueByNameMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetAssetIssueByName")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(AssetIssueContractOuterClass.AssetIssueContract.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("GetAssetIssueByName")).build();
                    getGetAssetIssueByNameMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.AssetIssueList> getGetAssetIssueListByNameMethod() {
        MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.AssetIssueList> methodDescriptor = getGetAssetIssueListByNameMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getGetAssetIssueListByNameMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetAssetIssueListByName")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.AssetIssueList.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("GetAssetIssueListByName")).build();
                    getGetAssetIssueListByNameMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BytesMessage, AssetIssueContractOuterClass.AssetIssueContract> getGetAssetIssueByIdMethod() {
        MethodDescriptor<GrpcAPI.BytesMessage, AssetIssueContractOuterClass.AssetIssueContract> methodDescriptor = getGetAssetIssueByIdMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getGetAssetIssueByIdMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetAssetIssueById")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(AssetIssueContractOuterClass.AssetIssueContract.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("GetAssetIssueById")).build();
                    getGetAssetIssueByIdMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.EmptyMessage, Protocol.Block> getGetNowBlockMethod() {
        MethodDescriptor<GrpcAPI.EmptyMessage, Protocol.Block> methodDescriptor = getGetNowBlockMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getGetNowBlockMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetNowBlock")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.EmptyMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.Block.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("GetNowBlock")).build();
                    getGetNowBlockMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.BlockExtention> getGetNowBlock2Method() {
        MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.BlockExtention> methodDescriptor = getGetNowBlock2Method;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getGetNowBlock2Method;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetNowBlock2")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.EmptyMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.BlockExtention.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("GetNowBlock2")).build();
                    getGetNowBlock2Method = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.NumberMessage, Protocol.Block> getGetBlockByNumMethod() {
        MethodDescriptor<GrpcAPI.NumberMessage, Protocol.Block> methodDescriptor = getGetBlockByNumMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getGetBlockByNumMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetBlockByNum")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.NumberMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.Block.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("GetBlockByNum")).build();
                    getGetBlockByNumMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.NumberMessage, GrpcAPI.BlockExtention> getGetBlockByNum2Method() {
        MethodDescriptor<GrpcAPI.NumberMessage, GrpcAPI.BlockExtention> methodDescriptor = getGetBlockByNum2Method;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getGetBlockByNum2Method;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetBlockByNum2")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.NumberMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.BlockExtention.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("GetBlockByNum2")).build();
                    getGetBlockByNum2Method = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.NumberMessage, GrpcAPI.NumberMessage> getGetTransactionCountByBlockNumMethod() {
        MethodDescriptor<GrpcAPI.NumberMessage, GrpcAPI.NumberMessage> methodDescriptor = getGetTransactionCountByBlockNumMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getGetTransactionCountByBlockNumMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetTransactionCountByBlockNum")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.NumberMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.NumberMessage.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("GetTransactionCountByBlockNum")).build();
                    getGetTransactionCountByBlockNumMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.DelegatedResourceMessage, GrpcAPI.DelegatedResourceList> getGetDelegatedResourceMethod() {
        MethodDescriptor<GrpcAPI.DelegatedResourceMessage, GrpcAPI.DelegatedResourceList> methodDescriptor = getGetDelegatedResourceMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getGetDelegatedResourceMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetDelegatedResource")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.DelegatedResourceMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.DelegatedResourceList.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("GetDelegatedResource")).build();
                    getGetDelegatedResourceMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.DelegatedResourceMessage, GrpcAPI.DelegatedResourceList> getGetDelegatedResourceV2Method() {
        MethodDescriptor<GrpcAPI.DelegatedResourceMessage, GrpcAPI.DelegatedResourceList> methodDescriptor = getGetDelegatedResourceV2Method;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getGetDelegatedResourceV2Method;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetDelegatedResourceV2")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.DelegatedResourceMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.DelegatedResourceList.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("GetDelegatedResourceV2")).build();
                    getGetDelegatedResourceV2Method = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BytesMessage, Protocol.DelegatedResourceAccountIndex> getGetDelegatedResourceAccountIndexMethod() {
        MethodDescriptor<GrpcAPI.BytesMessage, Protocol.DelegatedResourceAccountIndex> methodDescriptor = getGetDelegatedResourceAccountIndexMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getGetDelegatedResourceAccountIndexMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetDelegatedResourceAccountIndex")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.DelegatedResourceAccountIndex.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("GetDelegatedResourceAccountIndex")).build();
                    getGetDelegatedResourceAccountIndexMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BytesMessage, Protocol.DelegatedResourceAccountIndex> getGetDelegatedResourceAccountIndexV2Method() {
        MethodDescriptor<GrpcAPI.BytesMessage, Protocol.DelegatedResourceAccountIndex> methodDescriptor = getGetDelegatedResourceAccountIndexV2Method;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getGetDelegatedResourceAccountIndexV2Method;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetDelegatedResourceAccountIndexV2")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.DelegatedResourceAccountIndex.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("GetDelegatedResourceAccountIndexV2")).build();
                    getGetDelegatedResourceAccountIndexV2Method = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.CanDelegatedMaxSizeRequestMessage, GrpcAPI.CanDelegatedMaxSizeResponseMessage> getGetCanDelegatedMaxSizeMethod() {
        MethodDescriptor<GrpcAPI.CanDelegatedMaxSizeRequestMessage, GrpcAPI.CanDelegatedMaxSizeResponseMessage> methodDescriptor = getGetCanDelegatedMaxSizeMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getGetCanDelegatedMaxSizeMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetCanDelegatedMaxSize")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.CanDelegatedMaxSizeRequestMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.CanDelegatedMaxSizeResponseMessage.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("GetCanDelegatedMaxSize")).build();
                    getGetCanDelegatedMaxSizeMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.GetAvailableUnfreezeCountRequestMessage, GrpcAPI.GetAvailableUnfreezeCountResponseMessage> getGetAvailableUnfreezeCountMethod() {
        MethodDescriptor<GrpcAPI.GetAvailableUnfreezeCountRequestMessage, GrpcAPI.GetAvailableUnfreezeCountResponseMessage> methodDescriptor = getGetAvailableUnfreezeCountMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getGetAvailableUnfreezeCountMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetAvailableUnfreezeCount")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.GetAvailableUnfreezeCountRequestMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.GetAvailableUnfreezeCountResponseMessage.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("GetAvailableUnfreezeCount")).build();
                    getGetAvailableUnfreezeCountMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.CanWithdrawUnfreezeAmountRequestMessage, GrpcAPI.CanWithdrawUnfreezeAmountResponseMessage> getGetCanWithdrawUnfreezeAmountMethod() {
        MethodDescriptor<GrpcAPI.CanWithdrawUnfreezeAmountRequestMessage, GrpcAPI.CanWithdrawUnfreezeAmountResponseMessage> methodDescriptor = getGetCanWithdrawUnfreezeAmountMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getGetCanWithdrawUnfreezeAmountMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetCanWithdrawUnfreezeAmount")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.CanWithdrawUnfreezeAmountRequestMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.CanWithdrawUnfreezeAmountResponseMessage.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("GetCanWithdrawUnfreezeAmount")).build();
                    getGetCanWithdrawUnfreezeAmountMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BytesMessage, Protocol.Exchange> getGetExchangeByIdMethod() {
        MethodDescriptor<GrpcAPI.BytesMessage, Protocol.Exchange> methodDescriptor = getGetExchangeByIdMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getGetExchangeByIdMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetExchangeById")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.Exchange.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("GetExchangeById")).build();
                    getGetExchangeByIdMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.ExchangeList> getListExchangesMethod() {
        MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.ExchangeList> methodDescriptor = getListExchangesMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getListExchangesMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "ListExchanges")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.EmptyMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.ExchangeList.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("ListExchanges")).build();
                    getListExchangesMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BytesMessage, Protocol.Transaction> getGetTransactionByIdMethod() {
        MethodDescriptor<GrpcAPI.BytesMessage, Protocol.Transaction> methodDescriptor = getGetTransactionByIdMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getGetTransactionByIdMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetTransactionById")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.Transaction.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("GetTransactionById")).build();
                    getGetTransactionByIdMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BytesMessage, Protocol.TransactionInfo> getGetTransactionInfoByIdMethod() {
        MethodDescriptor<GrpcAPI.BytesMessage, Protocol.TransactionInfo> methodDescriptor = getGetTransactionInfoByIdMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getGetTransactionInfoByIdMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetTransactionInfoById")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.TransactionInfo.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("GetTransactionInfoById")).build();
                    getGetTransactionInfoByIdMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<ShieldContract.OutputPointInfo, ShieldContract.IncrementalMerkleVoucherInfo> getGetMerkleTreeVoucherInfoMethod() {
        MethodDescriptor<ShieldContract.OutputPointInfo, ShieldContract.IncrementalMerkleVoucherInfo> methodDescriptor = getGetMerkleTreeVoucherInfoMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getGetMerkleTreeVoucherInfoMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetMerkleTreeVoucherInfo")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(ShieldContract.OutputPointInfo.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(ShieldContract.IncrementalMerkleVoucherInfo.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("GetMerkleTreeVoucherInfo")).build();
                    getGetMerkleTreeVoucherInfoMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.IvkDecryptParameters, GrpcAPI.DecryptNotes> getScanNoteByIvkMethod() {
        MethodDescriptor<GrpcAPI.IvkDecryptParameters, GrpcAPI.DecryptNotes> methodDescriptor = getScanNoteByIvkMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getScanNoteByIvkMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "ScanNoteByIvk")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.IvkDecryptParameters.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.DecryptNotes.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("ScanNoteByIvk")).build();
                    getScanNoteByIvkMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.IvkDecryptAndMarkParameters, GrpcAPI.DecryptNotesMarked> getScanAndMarkNoteByIvkMethod() {
        MethodDescriptor<GrpcAPI.IvkDecryptAndMarkParameters, GrpcAPI.DecryptNotesMarked> methodDescriptor = getScanAndMarkNoteByIvkMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getScanAndMarkNoteByIvkMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "ScanAndMarkNoteByIvk")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.IvkDecryptAndMarkParameters.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.DecryptNotesMarked.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("ScanAndMarkNoteByIvk")).build();
                    getScanAndMarkNoteByIvkMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.OvkDecryptParameters, GrpcAPI.DecryptNotes> getScanNoteByOvkMethod() {
        MethodDescriptor<GrpcAPI.OvkDecryptParameters, GrpcAPI.DecryptNotes> methodDescriptor = getScanNoteByOvkMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getScanNoteByOvkMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "ScanNoteByOvk")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.OvkDecryptParameters.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.DecryptNotes.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("ScanNoteByOvk")).build();
                    getScanNoteByOvkMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.NoteParameters, GrpcAPI.SpendResult> getIsSpendMethod() {
        MethodDescriptor<GrpcAPI.NoteParameters, GrpcAPI.SpendResult> methodDescriptor = getIsSpendMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getIsSpendMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "IsSpend")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.NoteParameters.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.SpendResult.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("IsSpend")).build();
                    getIsSpendMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.IvkDecryptTRC20Parameters, GrpcAPI.DecryptNotesTRC20> getScanShieldedTRC20NotesByIvkMethod() {
        MethodDescriptor<GrpcAPI.IvkDecryptTRC20Parameters, GrpcAPI.DecryptNotesTRC20> methodDescriptor = getScanShieldedTRC20NotesByIvkMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getScanShieldedTRC20NotesByIvkMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "ScanShieldedTRC20NotesByIvk")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.IvkDecryptTRC20Parameters.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.DecryptNotesTRC20.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("ScanShieldedTRC20NotesByIvk")).build();
                    getScanShieldedTRC20NotesByIvkMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.OvkDecryptTRC20Parameters, GrpcAPI.DecryptNotesTRC20> getScanShieldedTRC20NotesByOvkMethod() {
        MethodDescriptor<GrpcAPI.OvkDecryptTRC20Parameters, GrpcAPI.DecryptNotesTRC20> methodDescriptor = getScanShieldedTRC20NotesByOvkMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getScanShieldedTRC20NotesByOvkMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "ScanShieldedTRC20NotesByOvk")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.OvkDecryptTRC20Parameters.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.DecryptNotesTRC20.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("ScanShieldedTRC20NotesByOvk")).build();
                    getScanShieldedTRC20NotesByOvkMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.NfTRC20Parameters, GrpcAPI.NullifierResult> getIsShieldedTRC20ContractNoteSpentMethod() {
        MethodDescriptor<GrpcAPI.NfTRC20Parameters, GrpcAPI.NullifierResult> methodDescriptor = getIsShieldedTRC20ContractNoteSpentMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getIsShieldedTRC20ContractNoteSpentMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "IsShieldedTRC20ContractNoteSpent")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.NfTRC20Parameters.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.NullifierResult.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("IsShieldedTRC20ContractNoteSpent")).build();
                    getIsShieldedTRC20ContractNoteSpentMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.NumberMessage> getGetRewardInfoMethod() {
        MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.NumberMessage> methodDescriptor = getGetRewardInfoMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getGetRewardInfoMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetRewardInfo")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.NumberMessage.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("GetRewardInfo")).build();
                    getGetRewardInfoMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.NumberMessage> getGetBrokerageInfoMethod() {
        MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.NumberMessage> methodDescriptor = getGetBrokerageInfoMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getGetBrokerageInfoMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetBrokerageInfo")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.NumberMessage.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("GetBrokerageInfo")).build();
                    getGetBrokerageInfoMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<SmartContractOuterClass.TriggerSmartContract, GrpcAPI.TransactionExtention> getTriggerConstantContractMethod() {
        MethodDescriptor<SmartContractOuterClass.TriggerSmartContract, GrpcAPI.TransactionExtention> methodDescriptor = getTriggerConstantContractMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getTriggerConstantContractMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "TriggerConstantContract")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(SmartContractOuterClass.TriggerSmartContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("TriggerConstantContract")).build();
                    getTriggerConstantContractMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<SmartContractOuterClass.TriggerSmartContract, GrpcAPI.EstimateEnergyMessage> getEstimateEnergyMethod() {
        MethodDescriptor<SmartContractOuterClass.TriggerSmartContract, GrpcAPI.EstimateEnergyMessage> methodDescriptor = getEstimateEnergyMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getEstimateEnergyMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "EstimateEnergy")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(SmartContractOuterClass.TriggerSmartContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.EstimateEnergyMessage.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("EstimateEnergy")).build();
                    getEstimateEnergyMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.NumberMessage, GrpcAPI.TransactionInfoList> getGetTransactionInfoByBlockNumMethod() {
        MethodDescriptor<GrpcAPI.NumberMessage, GrpcAPI.TransactionInfoList> methodDescriptor = getGetTransactionInfoByBlockNumMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getGetTransactionInfoByBlockNumMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetTransactionInfoByBlockNum")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.NumberMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionInfoList.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("GetTransactionInfoByBlockNum")).build();
                    getGetTransactionInfoByBlockNumMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BytesMessage, Protocol.MarketOrder> getGetMarketOrderByIdMethod() {
        MethodDescriptor<GrpcAPI.BytesMessage, Protocol.MarketOrder> methodDescriptor = getGetMarketOrderByIdMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getGetMarketOrderByIdMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetMarketOrderById")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.MarketOrder.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("GetMarketOrderById")).build();
                    getGetMarketOrderByIdMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BytesMessage, Protocol.MarketOrderList> getGetMarketOrderByAccountMethod() {
        MethodDescriptor<GrpcAPI.BytesMessage, Protocol.MarketOrderList> methodDescriptor = getGetMarketOrderByAccountMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getGetMarketOrderByAccountMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetMarketOrderByAccount")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.MarketOrderList.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("GetMarketOrderByAccount")).build();
                    getGetMarketOrderByAccountMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<Protocol.MarketOrderPair, Protocol.MarketPriceList> getGetMarketPriceByPairMethod() {
        MethodDescriptor<Protocol.MarketOrderPair, Protocol.MarketPriceList> methodDescriptor = getGetMarketPriceByPairMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getGetMarketPriceByPairMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetMarketPriceByPair")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(Protocol.MarketOrderPair.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.MarketPriceList.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("GetMarketPriceByPair")).build();
                    getGetMarketPriceByPairMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<Protocol.MarketOrderPair, Protocol.MarketOrderList> getGetMarketOrderListByPairMethod() {
        MethodDescriptor<Protocol.MarketOrderPair, Protocol.MarketOrderList> methodDescriptor = getGetMarketOrderListByPairMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getGetMarketOrderListByPairMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetMarketOrderListByPair")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(Protocol.MarketOrderPair.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.MarketOrderList.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("GetMarketOrderListByPair")).build();
                    getGetMarketOrderListByPairMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.EmptyMessage, Protocol.MarketOrderPairList> getGetMarketPairListMethod() {
        MethodDescriptor<GrpcAPI.EmptyMessage, Protocol.MarketOrderPairList> methodDescriptor = getGetMarketPairListMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getGetMarketPairListMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetMarketPairList")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.EmptyMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.MarketOrderPairList.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("GetMarketPairList")).build();
                    getGetMarketPairListMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.NumberMessage> getGetBurnTrxMethod() {
        MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.NumberMessage> methodDescriptor = getGetBurnTrxMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getGetBurnTrxMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetBurnTrx")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.EmptyMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.NumberMessage.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("GetBurnTrx")).build();
                    getGetBurnTrxMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BlockReq, GrpcAPI.BlockExtention> getGetBlockMethod() {
        MethodDescriptor<GrpcAPI.BlockReq, GrpcAPI.BlockExtention> methodDescriptor = getGetBlockMethod;
        if (methodDescriptor == null) {
            synchronized (WalletSolidityGrpc.class) {
                methodDescriptor = getGetBlockMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetBlock")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BlockReq.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.BlockExtention.getDefaultInstance())).setSchemaDescriptor(new WalletSolidityMethodDescriptorSupplier("GetBlock")).build();
                    getGetBlockMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static WalletSolidityStub newStub(Channel channel) {
        return new WalletSolidityStub(channel);
    }

    public static WalletSolidityBlockingStub newBlockingStub(Channel channel) {
        return new WalletSolidityBlockingStub(channel);
    }

    public static WalletSolidityFutureStub newFutureStub(Channel channel) {
        return new WalletSolidityFutureStub(channel);
    }

    public static abstract class WalletSolidityImplBase implements BindableService {
        public void getAccount(Protocol.Account account, StreamObserver<Protocol.Account> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getGetAccountMethod(), streamObserver);
        }

        public void getAccountById(Protocol.Account account, StreamObserver<Protocol.Account> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getGetAccountByIdMethod(), streamObserver);
        }

        public void listWitnesses(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.WitnessList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getListWitnessesMethod(), streamObserver);
        }

        public void getAssetIssueList(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.AssetIssueList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getGetAssetIssueListMethod(), streamObserver);
        }

        public void getPaginatedAssetIssueList(GrpcAPI.PaginatedMessage paginatedMessage, StreamObserver<GrpcAPI.AssetIssueList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getGetPaginatedAssetIssueListMethod(), streamObserver);
        }

        public void getAssetIssueByName(GrpcAPI.BytesMessage bytesMessage, StreamObserver<AssetIssueContractOuterClass.AssetIssueContract> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getGetAssetIssueByNameMethod(), streamObserver);
        }

        public void getAssetIssueListByName(GrpcAPI.BytesMessage bytesMessage, StreamObserver<GrpcAPI.AssetIssueList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getGetAssetIssueListByNameMethod(), streamObserver);
        }

        public void getAssetIssueById(GrpcAPI.BytesMessage bytesMessage, StreamObserver<AssetIssueContractOuterClass.AssetIssueContract> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getGetAssetIssueByIdMethod(), streamObserver);
        }

        public void getNowBlock(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<Protocol.Block> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getGetNowBlockMethod(), streamObserver);
        }

        public void getNowBlock2(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.BlockExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getGetNowBlock2Method(), streamObserver);
        }

        public void getBlockByNum(GrpcAPI.NumberMessage numberMessage, StreamObserver<Protocol.Block> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getGetBlockByNumMethod(), streamObserver);
        }

        public void getBlockByNum2(GrpcAPI.NumberMessage numberMessage, StreamObserver<GrpcAPI.BlockExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getGetBlockByNum2Method(), streamObserver);
        }

        public void getTransactionCountByBlockNum(GrpcAPI.NumberMessage numberMessage, StreamObserver<GrpcAPI.NumberMessage> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getGetTransactionCountByBlockNumMethod(), streamObserver);
        }

        public void getDelegatedResource(GrpcAPI.DelegatedResourceMessage delegatedResourceMessage, StreamObserver<GrpcAPI.DelegatedResourceList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getGetDelegatedResourceMethod(), streamObserver);
        }

        public void getDelegatedResourceV2(GrpcAPI.DelegatedResourceMessage delegatedResourceMessage, StreamObserver<GrpcAPI.DelegatedResourceList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getGetDelegatedResourceV2Method(), streamObserver);
        }

        public void getDelegatedResourceAccountIndex(GrpcAPI.BytesMessage bytesMessage, StreamObserver<Protocol.DelegatedResourceAccountIndex> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getGetDelegatedResourceAccountIndexMethod(), streamObserver);
        }

        public void getDelegatedResourceAccountIndexV2(GrpcAPI.BytesMessage bytesMessage, StreamObserver<Protocol.DelegatedResourceAccountIndex> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getGetDelegatedResourceAccountIndexV2Method(), streamObserver);
        }

        public void getCanDelegatedMaxSize(GrpcAPI.CanDelegatedMaxSizeRequestMessage canDelegatedMaxSizeRequestMessage, StreamObserver<GrpcAPI.CanDelegatedMaxSizeResponseMessage> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getGetCanDelegatedMaxSizeMethod(), streamObserver);
        }

        public void getAvailableUnfreezeCount(GrpcAPI.GetAvailableUnfreezeCountRequestMessage getAvailableUnfreezeCountRequestMessage, StreamObserver<GrpcAPI.GetAvailableUnfreezeCountResponseMessage> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getGetAvailableUnfreezeCountMethod(), streamObserver);
        }

        public void getCanWithdrawUnfreezeAmount(GrpcAPI.CanWithdrawUnfreezeAmountRequestMessage canWithdrawUnfreezeAmountRequestMessage, StreamObserver<GrpcAPI.CanWithdrawUnfreezeAmountResponseMessage> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getGetCanWithdrawUnfreezeAmountMethod(), streamObserver);
        }

        public void getExchangeById(GrpcAPI.BytesMessage bytesMessage, StreamObserver<Protocol.Exchange> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getGetExchangeByIdMethod(), streamObserver);
        }

        public void listExchanges(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.ExchangeList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getListExchangesMethod(), streamObserver);
        }

        public void getTransactionById(GrpcAPI.BytesMessage bytesMessage, StreamObserver<Protocol.Transaction> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getGetTransactionByIdMethod(), streamObserver);
        }

        public void getTransactionInfoById(GrpcAPI.BytesMessage bytesMessage, StreamObserver<Protocol.TransactionInfo> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getGetTransactionInfoByIdMethod(), streamObserver);
        }

        public void getMerkleTreeVoucherInfo(ShieldContract.OutputPointInfo outputPointInfo, StreamObserver<ShieldContract.IncrementalMerkleVoucherInfo> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getGetMerkleTreeVoucherInfoMethod(), streamObserver);
        }

        public void scanNoteByIvk(GrpcAPI.IvkDecryptParameters ivkDecryptParameters, StreamObserver<GrpcAPI.DecryptNotes> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getScanNoteByIvkMethod(), streamObserver);
        }

        public void scanAndMarkNoteByIvk(GrpcAPI.IvkDecryptAndMarkParameters ivkDecryptAndMarkParameters, StreamObserver<GrpcAPI.DecryptNotesMarked> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getScanAndMarkNoteByIvkMethod(), streamObserver);
        }

        public void scanNoteByOvk(GrpcAPI.OvkDecryptParameters ovkDecryptParameters, StreamObserver<GrpcAPI.DecryptNotes> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getScanNoteByOvkMethod(), streamObserver);
        }

        public void isSpend(GrpcAPI.NoteParameters noteParameters, StreamObserver<GrpcAPI.SpendResult> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getIsSpendMethod(), streamObserver);
        }

        public void scanShieldedTRC20NotesByIvk(GrpcAPI.IvkDecryptTRC20Parameters ivkDecryptTRC20Parameters, StreamObserver<GrpcAPI.DecryptNotesTRC20> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getScanShieldedTRC20NotesByIvkMethod(), streamObserver);
        }

        public void scanShieldedTRC20NotesByOvk(GrpcAPI.OvkDecryptTRC20Parameters ovkDecryptTRC20Parameters, StreamObserver<GrpcAPI.DecryptNotesTRC20> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getScanShieldedTRC20NotesByOvkMethod(), streamObserver);
        }

        public void isShieldedTRC20ContractNoteSpent(GrpcAPI.NfTRC20Parameters nfTRC20Parameters, StreamObserver<GrpcAPI.NullifierResult> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getIsShieldedTRC20ContractNoteSpentMethod(), streamObserver);
        }

        public void getRewardInfo(GrpcAPI.BytesMessage bytesMessage, StreamObserver<GrpcAPI.NumberMessage> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getGetRewardInfoMethod(), streamObserver);
        }

        public void getBrokerageInfo(GrpcAPI.BytesMessage bytesMessage, StreamObserver<GrpcAPI.NumberMessage> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getGetBrokerageInfoMethod(), streamObserver);
        }

        public void triggerConstantContract(SmartContractOuterClass.TriggerSmartContract triggerSmartContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getTriggerConstantContractMethod(), streamObserver);
        }

        public void estimateEnergy(SmartContractOuterClass.TriggerSmartContract triggerSmartContract, StreamObserver<GrpcAPI.EstimateEnergyMessage> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getEstimateEnergyMethod(), streamObserver);
        }

        public void getTransactionInfoByBlockNum(GrpcAPI.NumberMessage numberMessage, StreamObserver<GrpcAPI.TransactionInfoList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getGetTransactionInfoByBlockNumMethod(), streamObserver);
        }

        public void getMarketOrderById(GrpcAPI.BytesMessage bytesMessage, StreamObserver<Protocol.MarketOrder> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getGetMarketOrderByIdMethod(), streamObserver);
        }

        public void getMarketOrderByAccount(GrpcAPI.BytesMessage bytesMessage, StreamObserver<Protocol.MarketOrderList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getGetMarketOrderByAccountMethod(), streamObserver);
        }

        public void getMarketPriceByPair(Protocol.MarketOrderPair marketOrderPair, StreamObserver<Protocol.MarketPriceList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getGetMarketPriceByPairMethod(), streamObserver);
        }

        public void getMarketOrderListByPair(Protocol.MarketOrderPair marketOrderPair, StreamObserver<Protocol.MarketOrderList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getGetMarketOrderListByPairMethod(), streamObserver);
        }

        public void getMarketPairList(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<Protocol.MarketOrderPairList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getGetMarketPairListMethod(), streamObserver);
        }

        public void getBurnTrx(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.NumberMessage> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getGetBurnTrxMethod(), streamObserver);
        }

        public void getBlock(GrpcAPI.BlockReq blockReq, StreamObserver<GrpcAPI.BlockExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletSolidityGrpc.getGetBlockMethod(), streamObserver);
        }

        @Override
        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(WalletSolidityGrpc.getServiceDescriptor()).addMethod(WalletSolidityGrpc.getGetAccountMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 0))).addMethod(WalletSolidityGrpc.getGetAccountByIdMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 1))).addMethod(WalletSolidityGrpc.getListWitnessesMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 2))).addMethod(WalletSolidityGrpc.getGetAssetIssueListMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 3))).addMethod(WalletSolidityGrpc.getGetPaginatedAssetIssueListMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 4))).addMethod(WalletSolidityGrpc.getGetAssetIssueByNameMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 5))).addMethod(WalletSolidityGrpc.getGetAssetIssueListByNameMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 6))).addMethod(WalletSolidityGrpc.getGetAssetIssueByIdMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 7))).addMethod(WalletSolidityGrpc.getGetNowBlockMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 8))).addMethod(WalletSolidityGrpc.getGetNowBlock2Method(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 9))).addMethod(WalletSolidityGrpc.getGetBlockByNumMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 10))).addMethod(WalletSolidityGrpc.getGetBlockByNum2Method(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 11))).addMethod(WalletSolidityGrpc.getGetTransactionCountByBlockNumMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 12))).addMethod(WalletSolidityGrpc.getGetDelegatedResourceMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 13))).addMethod(WalletSolidityGrpc.getGetDelegatedResourceV2Method(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 14))).addMethod(WalletSolidityGrpc.getGetDelegatedResourceAccountIndexMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 15))).addMethod(WalletSolidityGrpc.getGetDelegatedResourceAccountIndexV2Method(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 16))).addMethod(WalletSolidityGrpc.getGetCanDelegatedMaxSizeMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 17))).addMethod(WalletSolidityGrpc.getGetAvailableUnfreezeCountMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 18))).addMethod(WalletSolidityGrpc.getGetCanWithdrawUnfreezeAmountMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 19))).addMethod(WalletSolidityGrpc.getGetExchangeByIdMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 20))).addMethod(WalletSolidityGrpc.getListExchangesMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 21))).addMethod(WalletSolidityGrpc.getGetTransactionByIdMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 22))).addMethod(WalletSolidityGrpc.getGetTransactionInfoByIdMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 23))).addMethod(WalletSolidityGrpc.getGetMerkleTreeVoucherInfoMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 24))).addMethod(WalletSolidityGrpc.getScanNoteByIvkMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 25))).addMethod(WalletSolidityGrpc.getScanAndMarkNoteByIvkMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 26))).addMethod(WalletSolidityGrpc.getScanNoteByOvkMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 27))).addMethod(WalletSolidityGrpc.getIsSpendMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 28))).addMethod(WalletSolidityGrpc.getScanShieldedTRC20NotesByIvkMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 29))).addMethod(WalletSolidityGrpc.getScanShieldedTRC20NotesByOvkMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 30))).addMethod(WalletSolidityGrpc.getIsShieldedTRC20ContractNoteSpentMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 31))).addMethod(WalletSolidityGrpc.getGetRewardInfoMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 32))).addMethod(WalletSolidityGrpc.getGetBrokerageInfoMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 33))).addMethod(WalletSolidityGrpc.getTriggerConstantContractMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 34))).addMethod(WalletSolidityGrpc.getEstimateEnergyMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 35))).addMethod(WalletSolidityGrpc.getGetTransactionInfoByBlockNumMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 36))).addMethod(WalletSolidityGrpc.getGetMarketOrderByIdMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 37))).addMethod(WalletSolidityGrpc.getGetMarketOrderByAccountMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 38))).addMethod(WalletSolidityGrpc.getGetMarketPriceByPairMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 39))).addMethod(WalletSolidityGrpc.getGetMarketOrderListByPairMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 40))).addMethod(WalletSolidityGrpc.getGetMarketPairListMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 41))).addMethod(WalletSolidityGrpc.getGetBurnTrxMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 42))).addMethod(WalletSolidityGrpc.getGetBlockMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 43))).build();
        }
    }

    public static final class WalletSolidityStub extends AbstractStub<WalletSolidityStub> {
        private WalletSolidityStub(Channel channel) {
            super(channel);
        }

        private WalletSolidityStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        public WalletSolidityStub build(Channel channel, CallOptions callOptions) {
            return new WalletSolidityStub(channel, callOptions);
        }

        public void getAccount(Protocol.Account account, StreamObserver<Protocol.Account> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetAccountMethod(), getCallOptions()), account, streamObserver);
        }

        public void getAccountById(Protocol.Account account, StreamObserver<Protocol.Account> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetAccountByIdMethod(), getCallOptions()), account, streamObserver);
        }

        public void listWitnesses(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.WitnessList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getListWitnessesMethod(), getCallOptions()), emptyMessage, streamObserver);
        }

        public void getAssetIssueList(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.AssetIssueList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetAssetIssueListMethod(), getCallOptions()), emptyMessage, streamObserver);
        }

        public void getPaginatedAssetIssueList(GrpcAPI.PaginatedMessage paginatedMessage, StreamObserver<GrpcAPI.AssetIssueList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetPaginatedAssetIssueListMethod(), getCallOptions()), paginatedMessage, streamObserver);
        }

        public void getAssetIssueByName(GrpcAPI.BytesMessage bytesMessage, StreamObserver<AssetIssueContractOuterClass.AssetIssueContract> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetAssetIssueByNameMethod(), getCallOptions()), bytesMessage, streamObserver);
        }

        public void getAssetIssueListByName(GrpcAPI.BytesMessage bytesMessage, StreamObserver<GrpcAPI.AssetIssueList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetAssetIssueListByNameMethod(), getCallOptions()), bytesMessage, streamObserver);
        }

        public void getAssetIssueById(GrpcAPI.BytesMessage bytesMessage, StreamObserver<AssetIssueContractOuterClass.AssetIssueContract> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetAssetIssueByIdMethod(), getCallOptions()), bytesMessage, streamObserver);
        }

        public void getNowBlock(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<Protocol.Block> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetNowBlockMethod(), getCallOptions()), emptyMessage, streamObserver);
        }

        public void getNowBlock2(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.BlockExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetNowBlock2Method(), getCallOptions()), emptyMessage, streamObserver);
        }

        public void getBlockByNum(GrpcAPI.NumberMessage numberMessage, StreamObserver<Protocol.Block> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetBlockByNumMethod(), getCallOptions()), numberMessage, streamObserver);
        }

        public void getBlockByNum2(GrpcAPI.NumberMessage numberMessage, StreamObserver<GrpcAPI.BlockExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetBlockByNum2Method(), getCallOptions()), numberMessage, streamObserver);
        }

        public void getTransactionCountByBlockNum(GrpcAPI.NumberMessage numberMessage, StreamObserver<GrpcAPI.NumberMessage> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetTransactionCountByBlockNumMethod(), getCallOptions()), numberMessage, streamObserver);
        }

        public void getDelegatedResource(GrpcAPI.DelegatedResourceMessage delegatedResourceMessage, StreamObserver<GrpcAPI.DelegatedResourceList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetDelegatedResourceMethod(), getCallOptions()), delegatedResourceMessage, streamObserver);
        }

        public void getDelegatedResourceV2(GrpcAPI.DelegatedResourceMessage delegatedResourceMessage, StreamObserver<GrpcAPI.DelegatedResourceList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetDelegatedResourceV2Method(), getCallOptions()), delegatedResourceMessage, streamObserver);
        }

        public void getDelegatedResourceAccountIndex(GrpcAPI.BytesMessage bytesMessage, StreamObserver<Protocol.DelegatedResourceAccountIndex> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetDelegatedResourceAccountIndexMethod(), getCallOptions()), bytesMessage, streamObserver);
        }

        public void getDelegatedResourceAccountIndexV2(GrpcAPI.BytesMessage bytesMessage, StreamObserver<Protocol.DelegatedResourceAccountIndex> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetDelegatedResourceAccountIndexV2Method(), getCallOptions()), bytesMessage, streamObserver);
        }

        public void getCanDelegatedMaxSize(GrpcAPI.CanDelegatedMaxSizeRequestMessage canDelegatedMaxSizeRequestMessage, StreamObserver<GrpcAPI.CanDelegatedMaxSizeResponseMessage> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetCanDelegatedMaxSizeMethod(), getCallOptions()), canDelegatedMaxSizeRequestMessage, streamObserver);
        }

        public void getAvailableUnfreezeCount(GrpcAPI.GetAvailableUnfreezeCountRequestMessage getAvailableUnfreezeCountRequestMessage, StreamObserver<GrpcAPI.GetAvailableUnfreezeCountResponseMessage> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetAvailableUnfreezeCountMethod(), getCallOptions()), getAvailableUnfreezeCountRequestMessage, streamObserver);
        }

        public void getCanWithdrawUnfreezeAmount(GrpcAPI.CanWithdrawUnfreezeAmountRequestMessage canWithdrawUnfreezeAmountRequestMessage, StreamObserver<GrpcAPI.CanWithdrawUnfreezeAmountResponseMessage> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetCanWithdrawUnfreezeAmountMethod(), getCallOptions()), canWithdrawUnfreezeAmountRequestMessage, streamObserver);
        }

        public void getExchangeById(GrpcAPI.BytesMessage bytesMessage, StreamObserver<Protocol.Exchange> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetExchangeByIdMethod(), getCallOptions()), bytesMessage, streamObserver);
        }

        public void listExchanges(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.ExchangeList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getListExchangesMethod(), getCallOptions()), emptyMessage, streamObserver);
        }

        public void getTransactionById(GrpcAPI.BytesMessage bytesMessage, StreamObserver<Protocol.Transaction> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetTransactionByIdMethod(), getCallOptions()), bytesMessage, streamObserver);
        }

        public void getTransactionInfoById(GrpcAPI.BytesMessage bytesMessage, StreamObserver<Protocol.TransactionInfo> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetTransactionInfoByIdMethod(), getCallOptions()), bytesMessage, streamObserver);
        }

        public void getMerkleTreeVoucherInfo(ShieldContract.OutputPointInfo outputPointInfo, StreamObserver<ShieldContract.IncrementalMerkleVoucherInfo> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetMerkleTreeVoucherInfoMethod(), getCallOptions()), outputPointInfo, streamObserver);
        }

        public void scanNoteByIvk(GrpcAPI.IvkDecryptParameters ivkDecryptParameters, StreamObserver<GrpcAPI.DecryptNotes> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getScanNoteByIvkMethod(), getCallOptions()), ivkDecryptParameters, streamObserver);
        }

        public void scanAndMarkNoteByIvk(GrpcAPI.IvkDecryptAndMarkParameters ivkDecryptAndMarkParameters, StreamObserver<GrpcAPI.DecryptNotesMarked> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getScanAndMarkNoteByIvkMethod(), getCallOptions()), ivkDecryptAndMarkParameters, streamObserver);
        }

        public void scanNoteByOvk(GrpcAPI.OvkDecryptParameters ovkDecryptParameters, StreamObserver<GrpcAPI.DecryptNotes> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getScanNoteByOvkMethod(), getCallOptions()), ovkDecryptParameters, streamObserver);
        }

        public void isSpend(GrpcAPI.NoteParameters noteParameters, StreamObserver<GrpcAPI.SpendResult> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getIsSpendMethod(), getCallOptions()), noteParameters, streamObserver);
        }

        public void scanShieldedTRC20NotesByIvk(GrpcAPI.IvkDecryptTRC20Parameters ivkDecryptTRC20Parameters, StreamObserver<GrpcAPI.DecryptNotesTRC20> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getScanShieldedTRC20NotesByIvkMethod(), getCallOptions()), ivkDecryptTRC20Parameters, streamObserver);
        }

        public void scanShieldedTRC20NotesByOvk(GrpcAPI.OvkDecryptTRC20Parameters ovkDecryptTRC20Parameters, StreamObserver<GrpcAPI.DecryptNotesTRC20> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getScanShieldedTRC20NotesByOvkMethod(), getCallOptions()), ovkDecryptTRC20Parameters, streamObserver);
        }

        public void isShieldedTRC20ContractNoteSpent(GrpcAPI.NfTRC20Parameters nfTRC20Parameters, StreamObserver<GrpcAPI.NullifierResult> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getIsShieldedTRC20ContractNoteSpentMethod(), getCallOptions()), nfTRC20Parameters, streamObserver);
        }

        public void getRewardInfo(GrpcAPI.BytesMessage bytesMessage, StreamObserver<GrpcAPI.NumberMessage> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetRewardInfoMethod(), getCallOptions()), bytesMessage, streamObserver);
        }

        public void getBrokerageInfo(GrpcAPI.BytesMessage bytesMessage, StreamObserver<GrpcAPI.NumberMessage> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetBrokerageInfoMethod(), getCallOptions()), bytesMessage, streamObserver);
        }

        public void triggerConstantContract(SmartContractOuterClass.TriggerSmartContract triggerSmartContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getTriggerConstantContractMethod(), getCallOptions()), triggerSmartContract, streamObserver);
        }

        public void estimateEnergy(SmartContractOuterClass.TriggerSmartContract triggerSmartContract, StreamObserver<GrpcAPI.EstimateEnergyMessage> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getEstimateEnergyMethod(), getCallOptions()), triggerSmartContract, streamObserver);
        }

        public void getTransactionInfoByBlockNum(GrpcAPI.NumberMessage numberMessage, StreamObserver<GrpcAPI.TransactionInfoList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetTransactionInfoByBlockNumMethod(), getCallOptions()), numberMessage, streamObserver);
        }

        public void getMarketOrderById(GrpcAPI.BytesMessage bytesMessage, StreamObserver<Protocol.MarketOrder> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetMarketOrderByIdMethod(), getCallOptions()), bytesMessage, streamObserver);
        }

        public void getMarketOrderByAccount(GrpcAPI.BytesMessage bytesMessage, StreamObserver<Protocol.MarketOrderList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetMarketOrderByAccountMethod(), getCallOptions()), bytesMessage, streamObserver);
        }

        public void getMarketPriceByPair(Protocol.MarketOrderPair marketOrderPair, StreamObserver<Protocol.MarketPriceList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetMarketPriceByPairMethod(), getCallOptions()), marketOrderPair, streamObserver);
        }

        public void getMarketOrderListByPair(Protocol.MarketOrderPair marketOrderPair, StreamObserver<Protocol.MarketOrderList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetMarketOrderListByPairMethod(), getCallOptions()), marketOrderPair, streamObserver);
        }

        public void getMarketPairList(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<Protocol.MarketOrderPairList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetMarketPairListMethod(), getCallOptions()), emptyMessage, streamObserver);
        }

        public void getBurnTrx(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.NumberMessage> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetBurnTrxMethod(), getCallOptions()), emptyMessage, streamObserver);
        }

        public void getBlock(GrpcAPI.BlockReq blockReq, StreamObserver<GrpcAPI.BlockExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetBlockMethod(), getCallOptions()), blockReq, streamObserver);
        }
    }

    public static final class WalletSolidityBlockingStub extends AbstractStub<WalletSolidityBlockingStub> {
        private WalletSolidityBlockingStub(Channel channel) {
            super(channel);
        }

        private WalletSolidityBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        public WalletSolidityBlockingStub build(Channel channel, CallOptions callOptions) {
            return new WalletSolidityBlockingStub(channel, callOptions);
        }

        public Protocol.Account getAccount(Protocol.Account account) {
            return (Protocol.Account) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getGetAccountMethod(), getCallOptions(), account);
        }

        public Protocol.Account getAccountById(Protocol.Account account) {
            return (Protocol.Account) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getGetAccountByIdMethod(), getCallOptions(), account);
        }

        public GrpcAPI.WitnessList listWitnesses(GrpcAPI.EmptyMessage emptyMessage) {
            return (GrpcAPI.WitnessList) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getListWitnessesMethod(), getCallOptions(), emptyMessage);
        }

        public GrpcAPI.AssetIssueList getAssetIssueList(GrpcAPI.EmptyMessage emptyMessage) {
            return (GrpcAPI.AssetIssueList) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getGetAssetIssueListMethod(), getCallOptions(), emptyMessage);
        }

        public GrpcAPI.AssetIssueList getPaginatedAssetIssueList(GrpcAPI.PaginatedMessage paginatedMessage) {
            return (GrpcAPI.AssetIssueList) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getGetPaginatedAssetIssueListMethod(), getCallOptions(), paginatedMessage);
        }

        public AssetIssueContractOuterClass.AssetIssueContract getAssetIssueByName(GrpcAPI.BytesMessage bytesMessage) {
            return (AssetIssueContractOuterClass.AssetIssueContract) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getGetAssetIssueByNameMethod(), getCallOptions(), bytesMessage);
        }

        public GrpcAPI.AssetIssueList getAssetIssueListByName(GrpcAPI.BytesMessage bytesMessage) {
            return (GrpcAPI.AssetIssueList) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getGetAssetIssueListByNameMethod(), getCallOptions(), bytesMessage);
        }

        public AssetIssueContractOuterClass.AssetIssueContract getAssetIssueById(GrpcAPI.BytesMessage bytesMessage) {
            return (AssetIssueContractOuterClass.AssetIssueContract) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getGetAssetIssueByIdMethod(), getCallOptions(), bytesMessage);
        }

        public Protocol.Block getNowBlock(GrpcAPI.EmptyMessage emptyMessage) {
            return (Protocol.Block) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getGetNowBlockMethod(), getCallOptions(), emptyMessage);
        }

        public GrpcAPI.BlockExtention getNowBlock2(GrpcAPI.EmptyMessage emptyMessage) {
            return (GrpcAPI.BlockExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getGetNowBlock2Method(), getCallOptions(), emptyMessage);
        }

        public Protocol.Block getBlockByNum(GrpcAPI.NumberMessage numberMessage) {
            return (Protocol.Block) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getGetBlockByNumMethod(), getCallOptions(), numberMessage);
        }

        public GrpcAPI.BlockExtention getBlockByNum2(GrpcAPI.NumberMessage numberMessage) {
            return (GrpcAPI.BlockExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getGetBlockByNum2Method(), getCallOptions(), numberMessage);
        }

        public GrpcAPI.NumberMessage getTransactionCountByBlockNum(GrpcAPI.NumberMessage numberMessage) {
            return (GrpcAPI.NumberMessage) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getGetTransactionCountByBlockNumMethod(), getCallOptions(), numberMessage);
        }

        public GrpcAPI.DelegatedResourceList getDelegatedResource(GrpcAPI.DelegatedResourceMessage delegatedResourceMessage) {
            return (GrpcAPI.DelegatedResourceList) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getGetDelegatedResourceMethod(), getCallOptions(), delegatedResourceMessage);
        }

        public GrpcAPI.DelegatedResourceList getDelegatedResourceV2(GrpcAPI.DelegatedResourceMessage delegatedResourceMessage) {
            return (GrpcAPI.DelegatedResourceList) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getGetDelegatedResourceV2Method(), getCallOptions(), delegatedResourceMessage);
        }

        public Protocol.DelegatedResourceAccountIndex getDelegatedResourceAccountIndex(GrpcAPI.BytesMessage bytesMessage) {
            return (Protocol.DelegatedResourceAccountIndex) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getGetDelegatedResourceAccountIndexMethod(), getCallOptions(), bytesMessage);
        }

        public Protocol.DelegatedResourceAccountIndex getDelegatedResourceAccountIndexV2(GrpcAPI.BytesMessage bytesMessage) {
            return (Protocol.DelegatedResourceAccountIndex) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getGetDelegatedResourceAccountIndexV2Method(), getCallOptions(), bytesMessage);
        }

        public GrpcAPI.CanDelegatedMaxSizeResponseMessage getCanDelegatedMaxSize(GrpcAPI.CanDelegatedMaxSizeRequestMessage canDelegatedMaxSizeRequestMessage) {
            return (GrpcAPI.CanDelegatedMaxSizeResponseMessage) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getGetCanDelegatedMaxSizeMethod(), getCallOptions(), canDelegatedMaxSizeRequestMessage);
        }

        public GrpcAPI.GetAvailableUnfreezeCountResponseMessage getAvailableUnfreezeCount(GrpcAPI.GetAvailableUnfreezeCountRequestMessage getAvailableUnfreezeCountRequestMessage) {
            return (GrpcAPI.GetAvailableUnfreezeCountResponseMessage) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getGetAvailableUnfreezeCountMethod(), getCallOptions(), getAvailableUnfreezeCountRequestMessage);
        }

        public GrpcAPI.CanWithdrawUnfreezeAmountResponseMessage getCanWithdrawUnfreezeAmount(GrpcAPI.CanWithdrawUnfreezeAmountRequestMessage canWithdrawUnfreezeAmountRequestMessage) {
            return (GrpcAPI.CanWithdrawUnfreezeAmountResponseMessage) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getGetCanWithdrawUnfreezeAmountMethod(), getCallOptions(), canWithdrawUnfreezeAmountRequestMessage);
        }

        public Protocol.Exchange getExchangeById(GrpcAPI.BytesMessage bytesMessage) {
            return (Protocol.Exchange) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getGetExchangeByIdMethod(), getCallOptions(), bytesMessage);
        }

        public GrpcAPI.ExchangeList listExchanges(GrpcAPI.EmptyMessage emptyMessage) {
            return (GrpcAPI.ExchangeList) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getListExchangesMethod(), getCallOptions(), emptyMessage);
        }

        public Protocol.Transaction getTransactionById(GrpcAPI.BytesMessage bytesMessage) {
            return (Protocol.Transaction) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getGetTransactionByIdMethod(), getCallOptions(), bytesMessage);
        }

        public Protocol.TransactionInfo getTransactionInfoById(GrpcAPI.BytesMessage bytesMessage) {
            return (Protocol.TransactionInfo) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getGetTransactionInfoByIdMethod(), getCallOptions(), bytesMessage);
        }

        public ShieldContract.IncrementalMerkleVoucherInfo getMerkleTreeVoucherInfo(ShieldContract.OutputPointInfo outputPointInfo) {
            return (ShieldContract.IncrementalMerkleVoucherInfo) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getGetMerkleTreeVoucherInfoMethod(), getCallOptions(), outputPointInfo);
        }

        public GrpcAPI.DecryptNotes scanNoteByIvk(GrpcAPI.IvkDecryptParameters ivkDecryptParameters) {
            return (GrpcAPI.DecryptNotes) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getScanNoteByIvkMethod(), getCallOptions(), ivkDecryptParameters);
        }

        public GrpcAPI.DecryptNotesMarked scanAndMarkNoteByIvk(GrpcAPI.IvkDecryptAndMarkParameters ivkDecryptAndMarkParameters) {
            return (GrpcAPI.DecryptNotesMarked) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getScanAndMarkNoteByIvkMethod(), getCallOptions(), ivkDecryptAndMarkParameters);
        }

        public GrpcAPI.DecryptNotes scanNoteByOvk(GrpcAPI.OvkDecryptParameters ovkDecryptParameters) {
            return (GrpcAPI.DecryptNotes) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getScanNoteByOvkMethod(), getCallOptions(), ovkDecryptParameters);
        }

        public GrpcAPI.SpendResult isSpend(GrpcAPI.NoteParameters noteParameters) {
            return (GrpcAPI.SpendResult) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getIsSpendMethod(), getCallOptions(), noteParameters);
        }

        public GrpcAPI.DecryptNotesTRC20 scanShieldedTRC20NotesByIvk(GrpcAPI.IvkDecryptTRC20Parameters ivkDecryptTRC20Parameters) {
            return (GrpcAPI.DecryptNotesTRC20) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getScanShieldedTRC20NotesByIvkMethod(), getCallOptions(), ivkDecryptTRC20Parameters);
        }

        public GrpcAPI.DecryptNotesTRC20 scanShieldedTRC20NotesByOvk(GrpcAPI.OvkDecryptTRC20Parameters ovkDecryptTRC20Parameters) {
            return (GrpcAPI.DecryptNotesTRC20) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getScanShieldedTRC20NotesByOvkMethod(), getCallOptions(), ovkDecryptTRC20Parameters);
        }

        public GrpcAPI.NullifierResult isShieldedTRC20ContractNoteSpent(GrpcAPI.NfTRC20Parameters nfTRC20Parameters) {
            return (GrpcAPI.NullifierResult) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getIsShieldedTRC20ContractNoteSpentMethod(), getCallOptions(), nfTRC20Parameters);
        }

        public GrpcAPI.NumberMessage getRewardInfo(GrpcAPI.BytesMessage bytesMessage) {
            return (GrpcAPI.NumberMessage) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getGetRewardInfoMethod(), getCallOptions(), bytesMessage);
        }

        public GrpcAPI.NumberMessage getBrokerageInfo(GrpcAPI.BytesMessage bytesMessage) {
            return (GrpcAPI.NumberMessage) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getGetBrokerageInfoMethod(), getCallOptions(), bytesMessage);
        }

        public GrpcAPI.TransactionExtention triggerConstantContract(SmartContractOuterClass.TriggerSmartContract triggerSmartContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getTriggerConstantContractMethod(), getCallOptions(), triggerSmartContract);
        }

        public GrpcAPI.EstimateEnergyMessage estimateEnergy(SmartContractOuterClass.TriggerSmartContract triggerSmartContract) {
            return (GrpcAPI.EstimateEnergyMessage) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getEstimateEnergyMethod(), getCallOptions(), triggerSmartContract);
        }

        public GrpcAPI.TransactionInfoList getTransactionInfoByBlockNum(GrpcAPI.NumberMessage numberMessage) {
            return (GrpcAPI.TransactionInfoList) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getGetTransactionInfoByBlockNumMethod(), getCallOptions(), numberMessage);
        }

        public Protocol.MarketOrder getMarketOrderById(GrpcAPI.BytesMessage bytesMessage) {
            return (Protocol.MarketOrder) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getGetMarketOrderByIdMethod(), getCallOptions(), bytesMessage);
        }

        public Protocol.MarketOrderList getMarketOrderByAccount(GrpcAPI.BytesMessage bytesMessage) {
            return (Protocol.MarketOrderList) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getGetMarketOrderByAccountMethod(), getCallOptions(), bytesMessage);
        }

        public Protocol.MarketPriceList getMarketPriceByPair(Protocol.MarketOrderPair marketOrderPair) {
            return (Protocol.MarketPriceList) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getGetMarketPriceByPairMethod(), getCallOptions(), marketOrderPair);
        }

        public Protocol.MarketOrderList getMarketOrderListByPair(Protocol.MarketOrderPair marketOrderPair) {
            return (Protocol.MarketOrderList) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getGetMarketOrderListByPairMethod(), getCallOptions(), marketOrderPair);
        }

        public Protocol.MarketOrderPairList getMarketPairList(GrpcAPI.EmptyMessage emptyMessage) {
            return (Protocol.MarketOrderPairList) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getGetMarketPairListMethod(), getCallOptions(), emptyMessage);
        }

        public GrpcAPI.NumberMessage getBurnTrx(GrpcAPI.EmptyMessage emptyMessage) {
            return (GrpcAPI.NumberMessage) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getGetBurnTrxMethod(), getCallOptions(), emptyMessage);
        }

        public GrpcAPI.BlockExtention getBlock(GrpcAPI.BlockReq blockReq) {
            return (GrpcAPI.BlockExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletSolidityGrpc.getGetBlockMethod(), getCallOptions(), blockReq);
        }
    }

    public static final class WalletSolidityFutureStub extends AbstractStub<WalletSolidityFutureStub> {
        private WalletSolidityFutureStub(Channel channel) {
            super(channel);
        }

        private WalletSolidityFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        public WalletSolidityFutureStub build(Channel channel, CallOptions callOptions) {
            return new WalletSolidityFutureStub(channel, callOptions);
        }

        public ListenableFuture<Protocol.Account> getAccount(Protocol.Account account) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetAccountMethod(), getCallOptions()), account);
        }

        public ListenableFuture<Protocol.Account> getAccountById(Protocol.Account account) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetAccountByIdMethod(), getCallOptions()), account);
        }

        public ListenableFuture<GrpcAPI.WitnessList> listWitnesses(GrpcAPI.EmptyMessage emptyMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getListWitnessesMethod(), getCallOptions()), emptyMessage);
        }

        public ListenableFuture<GrpcAPI.AssetIssueList> getAssetIssueList(GrpcAPI.EmptyMessage emptyMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetAssetIssueListMethod(), getCallOptions()), emptyMessage);
        }

        public ListenableFuture<GrpcAPI.AssetIssueList> getPaginatedAssetIssueList(GrpcAPI.PaginatedMessage paginatedMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetPaginatedAssetIssueListMethod(), getCallOptions()), paginatedMessage);
        }

        public ListenableFuture<AssetIssueContractOuterClass.AssetIssueContract> getAssetIssueByName(GrpcAPI.BytesMessage bytesMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetAssetIssueByNameMethod(), getCallOptions()), bytesMessage);
        }

        public ListenableFuture<GrpcAPI.AssetIssueList> getAssetIssueListByName(GrpcAPI.BytesMessage bytesMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetAssetIssueListByNameMethod(), getCallOptions()), bytesMessage);
        }

        public ListenableFuture<AssetIssueContractOuterClass.AssetIssueContract> getAssetIssueById(GrpcAPI.BytesMessage bytesMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetAssetIssueByIdMethod(), getCallOptions()), bytesMessage);
        }

        public ListenableFuture<Protocol.Block> getNowBlock(GrpcAPI.EmptyMessage emptyMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetNowBlockMethod(), getCallOptions()), emptyMessage);
        }

        public ListenableFuture<GrpcAPI.BlockExtention> getNowBlock2(GrpcAPI.EmptyMessage emptyMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetNowBlock2Method(), getCallOptions()), emptyMessage);
        }

        public ListenableFuture<Protocol.Block> getBlockByNum(GrpcAPI.NumberMessage numberMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetBlockByNumMethod(), getCallOptions()), numberMessage);
        }

        public ListenableFuture<GrpcAPI.BlockExtention> getBlockByNum2(GrpcAPI.NumberMessage numberMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetBlockByNum2Method(), getCallOptions()), numberMessage);
        }

        public ListenableFuture<GrpcAPI.NumberMessage> getTransactionCountByBlockNum(GrpcAPI.NumberMessage numberMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetTransactionCountByBlockNumMethod(), getCallOptions()), numberMessage);
        }

        public ListenableFuture<GrpcAPI.DelegatedResourceList> getDelegatedResource(GrpcAPI.DelegatedResourceMessage delegatedResourceMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetDelegatedResourceMethod(), getCallOptions()), delegatedResourceMessage);
        }

        public ListenableFuture<GrpcAPI.DelegatedResourceList> getDelegatedResourceV2(GrpcAPI.DelegatedResourceMessage delegatedResourceMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetDelegatedResourceV2Method(), getCallOptions()), delegatedResourceMessage);
        }

        public ListenableFuture<Protocol.DelegatedResourceAccountIndex> getDelegatedResourceAccountIndex(GrpcAPI.BytesMessage bytesMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetDelegatedResourceAccountIndexMethod(), getCallOptions()), bytesMessage);
        }

        public ListenableFuture<Protocol.DelegatedResourceAccountIndex> getDelegatedResourceAccountIndexV2(GrpcAPI.BytesMessage bytesMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetDelegatedResourceAccountIndexV2Method(), getCallOptions()), bytesMessage);
        }

        public ListenableFuture<GrpcAPI.CanDelegatedMaxSizeResponseMessage> getCanDelegatedMaxSize(GrpcAPI.CanDelegatedMaxSizeRequestMessage canDelegatedMaxSizeRequestMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetCanDelegatedMaxSizeMethod(), getCallOptions()), canDelegatedMaxSizeRequestMessage);
        }

        public ListenableFuture<GrpcAPI.GetAvailableUnfreezeCountResponseMessage> getAvailableUnfreezeCount(GrpcAPI.GetAvailableUnfreezeCountRequestMessage getAvailableUnfreezeCountRequestMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetAvailableUnfreezeCountMethod(), getCallOptions()), getAvailableUnfreezeCountRequestMessage);
        }

        public ListenableFuture<GrpcAPI.CanWithdrawUnfreezeAmountResponseMessage> getCanWithdrawUnfreezeAmount(GrpcAPI.CanWithdrawUnfreezeAmountRequestMessage canWithdrawUnfreezeAmountRequestMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetCanWithdrawUnfreezeAmountMethod(), getCallOptions()), canWithdrawUnfreezeAmountRequestMessage);
        }

        public ListenableFuture<Protocol.Exchange> getExchangeById(GrpcAPI.BytesMessage bytesMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetExchangeByIdMethod(), getCallOptions()), bytesMessage);
        }

        public ListenableFuture<GrpcAPI.ExchangeList> listExchanges(GrpcAPI.EmptyMessage emptyMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getListExchangesMethod(), getCallOptions()), emptyMessage);
        }

        public ListenableFuture<Protocol.Transaction> getTransactionById(GrpcAPI.BytesMessage bytesMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetTransactionByIdMethod(), getCallOptions()), bytesMessage);
        }

        public ListenableFuture<Protocol.TransactionInfo> getTransactionInfoById(GrpcAPI.BytesMessage bytesMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetTransactionInfoByIdMethod(), getCallOptions()), bytesMessage);
        }

        public ListenableFuture<ShieldContract.IncrementalMerkleVoucherInfo> getMerkleTreeVoucherInfo(ShieldContract.OutputPointInfo outputPointInfo) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetMerkleTreeVoucherInfoMethod(), getCallOptions()), outputPointInfo);
        }

        public ListenableFuture<GrpcAPI.DecryptNotes> scanNoteByIvk(GrpcAPI.IvkDecryptParameters ivkDecryptParameters) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getScanNoteByIvkMethod(), getCallOptions()), ivkDecryptParameters);
        }

        public ListenableFuture<GrpcAPI.DecryptNotesMarked> scanAndMarkNoteByIvk(GrpcAPI.IvkDecryptAndMarkParameters ivkDecryptAndMarkParameters) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getScanAndMarkNoteByIvkMethod(), getCallOptions()), ivkDecryptAndMarkParameters);
        }

        public ListenableFuture<GrpcAPI.DecryptNotes> scanNoteByOvk(GrpcAPI.OvkDecryptParameters ovkDecryptParameters) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getScanNoteByOvkMethod(), getCallOptions()), ovkDecryptParameters);
        }

        public ListenableFuture<GrpcAPI.SpendResult> isSpend(GrpcAPI.NoteParameters noteParameters) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getIsSpendMethod(), getCallOptions()), noteParameters);
        }

        public ListenableFuture<GrpcAPI.DecryptNotesTRC20> scanShieldedTRC20NotesByIvk(GrpcAPI.IvkDecryptTRC20Parameters ivkDecryptTRC20Parameters) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getScanShieldedTRC20NotesByIvkMethod(), getCallOptions()), ivkDecryptTRC20Parameters);
        }

        public ListenableFuture<GrpcAPI.DecryptNotesTRC20> scanShieldedTRC20NotesByOvk(GrpcAPI.OvkDecryptTRC20Parameters ovkDecryptTRC20Parameters) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getScanShieldedTRC20NotesByOvkMethod(), getCallOptions()), ovkDecryptTRC20Parameters);
        }

        public ListenableFuture<GrpcAPI.NullifierResult> isShieldedTRC20ContractNoteSpent(GrpcAPI.NfTRC20Parameters nfTRC20Parameters) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getIsShieldedTRC20ContractNoteSpentMethod(), getCallOptions()), nfTRC20Parameters);
        }

        public ListenableFuture<GrpcAPI.NumberMessage> getRewardInfo(GrpcAPI.BytesMessage bytesMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetRewardInfoMethod(), getCallOptions()), bytesMessage);
        }

        public ListenableFuture<GrpcAPI.NumberMessage> getBrokerageInfo(GrpcAPI.BytesMessage bytesMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetBrokerageInfoMethod(), getCallOptions()), bytesMessage);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> triggerConstantContract(SmartContractOuterClass.TriggerSmartContract triggerSmartContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getTriggerConstantContractMethod(), getCallOptions()), triggerSmartContract);
        }

        public ListenableFuture<GrpcAPI.EstimateEnergyMessage> estimateEnergy(SmartContractOuterClass.TriggerSmartContract triggerSmartContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getEstimateEnergyMethod(), getCallOptions()), triggerSmartContract);
        }

        public ListenableFuture<GrpcAPI.TransactionInfoList> getTransactionInfoByBlockNum(GrpcAPI.NumberMessage numberMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetTransactionInfoByBlockNumMethod(), getCallOptions()), numberMessage);
        }

        public ListenableFuture<Protocol.MarketOrder> getMarketOrderById(GrpcAPI.BytesMessage bytesMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetMarketOrderByIdMethod(), getCallOptions()), bytesMessage);
        }

        public ListenableFuture<Protocol.MarketOrderList> getMarketOrderByAccount(GrpcAPI.BytesMessage bytesMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetMarketOrderByAccountMethod(), getCallOptions()), bytesMessage);
        }

        public ListenableFuture<Protocol.MarketPriceList> getMarketPriceByPair(Protocol.MarketOrderPair marketOrderPair) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetMarketPriceByPairMethod(), getCallOptions()), marketOrderPair);
        }

        public ListenableFuture<Protocol.MarketOrderList> getMarketOrderListByPair(Protocol.MarketOrderPair marketOrderPair) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetMarketOrderListByPairMethod(), getCallOptions()), marketOrderPair);
        }

        public ListenableFuture<Protocol.MarketOrderPairList> getMarketPairList(GrpcAPI.EmptyMessage emptyMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetMarketPairListMethod(), getCallOptions()), emptyMessage);
        }

        public ListenableFuture<GrpcAPI.NumberMessage> getBurnTrx(GrpcAPI.EmptyMessage emptyMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetBurnTrxMethod(), getCallOptions()), emptyMessage);
        }

        public ListenableFuture<GrpcAPI.BlockExtention> getBlock(GrpcAPI.BlockReq blockReq) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletSolidityGrpc.getGetBlockMethod(), getCallOptions()), blockReq);
        }
    }

    private static final class MethodHandlers<Req, Resp> implements ServerCalls.UnaryMethod<Req, Resp>, ServerCalls.ServerStreamingMethod<Req, Resp>, ServerCalls.ClientStreamingMethod<Req, Resp>, ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final WalletSolidityImplBase serviceImpl;

        MethodHandlers(WalletSolidityImplBase walletSolidityImplBase, int i) {
            this.serviceImpl = walletSolidityImplBase;
            this.methodId = i;
        }

        @Override
        public void invoke(Req req, StreamObserver<Resp> streamObserver) {
            switch (this.methodId) {
                case 0:
                    this.serviceImpl.getAccount((Protocol.Account) req, streamObserver);
                    return;
                case 1:
                    this.serviceImpl.getAccountById((Protocol.Account) req, streamObserver);
                    return;
                case 2:
                    this.serviceImpl.listWitnesses((GrpcAPI.EmptyMessage) req, streamObserver);
                    return;
                case 3:
                    this.serviceImpl.getAssetIssueList((GrpcAPI.EmptyMessage) req, streamObserver);
                    return;
                case 4:
                    this.serviceImpl.getPaginatedAssetIssueList((GrpcAPI.PaginatedMessage) req, streamObserver);
                    return;
                case 5:
                    this.serviceImpl.getAssetIssueByName((GrpcAPI.BytesMessage) req, streamObserver);
                    return;
                case 6:
                    this.serviceImpl.getAssetIssueListByName((GrpcAPI.BytesMessage) req, streamObserver);
                    return;
                case 7:
                    this.serviceImpl.getAssetIssueById((GrpcAPI.BytesMessage) req, streamObserver);
                    return;
                case 8:
                    this.serviceImpl.getNowBlock((GrpcAPI.EmptyMessage) req, streamObserver);
                    return;
                case 9:
                    this.serviceImpl.getNowBlock2((GrpcAPI.EmptyMessage) req, streamObserver);
                    return;
                case 10:
                    this.serviceImpl.getBlockByNum((GrpcAPI.NumberMessage) req, streamObserver);
                    return;
                case 11:
                    this.serviceImpl.getBlockByNum2((GrpcAPI.NumberMessage) req, streamObserver);
                    return;
                case 12:
                    this.serviceImpl.getTransactionCountByBlockNum((GrpcAPI.NumberMessage) req, streamObserver);
                    return;
                case 13:
                    this.serviceImpl.getDelegatedResource((GrpcAPI.DelegatedResourceMessage) req, streamObserver);
                    return;
                case 14:
                    this.serviceImpl.getDelegatedResourceV2((GrpcAPI.DelegatedResourceMessage) req, streamObserver);
                    return;
                case 15:
                    this.serviceImpl.getDelegatedResourceAccountIndex((GrpcAPI.BytesMessage) req, streamObserver);
                    return;
                case 16:
                    this.serviceImpl.getDelegatedResourceAccountIndexV2((GrpcAPI.BytesMessage) req, streamObserver);
                    return;
                case 17:
                    this.serviceImpl.getCanDelegatedMaxSize((GrpcAPI.CanDelegatedMaxSizeRequestMessage) req, streamObserver);
                    return;
                case 18:
                    this.serviceImpl.getAvailableUnfreezeCount((GrpcAPI.GetAvailableUnfreezeCountRequestMessage) req, streamObserver);
                    return;
                case 19:
                    this.serviceImpl.getCanWithdrawUnfreezeAmount((GrpcAPI.CanWithdrawUnfreezeAmountRequestMessage) req, streamObserver);
                    return;
                case 20:
                    this.serviceImpl.getExchangeById((GrpcAPI.BytesMessage) req, streamObserver);
                    return;
                case 21:
                    this.serviceImpl.listExchanges((GrpcAPI.EmptyMessage) req, streamObserver);
                    return;
                case 22:
                    this.serviceImpl.getTransactionById((GrpcAPI.BytesMessage) req, streamObserver);
                    return;
                case 23:
                    this.serviceImpl.getTransactionInfoById((GrpcAPI.BytesMessage) req, streamObserver);
                    return;
                case 24:
                    this.serviceImpl.getMerkleTreeVoucherInfo((ShieldContract.OutputPointInfo) req, streamObserver);
                    return;
                case 25:
                    this.serviceImpl.scanNoteByIvk((GrpcAPI.IvkDecryptParameters) req, streamObserver);
                    return;
                case 26:
                    this.serviceImpl.scanAndMarkNoteByIvk((GrpcAPI.IvkDecryptAndMarkParameters) req, streamObserver);
                    return;
                case 27:
                    this.serviceImpl.scanNoteByOvk((GrpcAPI.OvkDecryptParameters) req, streamObserver);
                    return;
                case 28:
                    this.serviceImpl.isSpend((GrpcAPI.NoteParameters) req, streamObserver);
                    return;
                case 29:
                    this.serviceImpl.scanShieldedTRC20NotesByIvk((GrpcAPI.IvkDecryptTRC20Parameters) req, streamObserver);
                    return;
                case 30:
                    this.serviceImpl.scanShieldedTRC20NotesByOvk((GrpcAPI.OvkDecryptTRC20Parameters) req, streamObserver);
                    return;
                case 31:
                    this.serviceImpl.isShieldedTRC20ContractNoteSpent((GrpcAPI.NfTRC20Parameters) req, streamObserver);
                    return;
                case 32:
                    this.serviceImpl.getRewardInfo((GrpcAPI.BytesMessage) req, streamObserver);
                    return;
                case 33:
                    this.serviceImpl.getBrokerageInfo((GrpcAPI.BytesMessage) req, streamObserver);
                    return;
                case 34:
                    this.serviceImpl.triggerConstantContract((SmartContractOuterClass.TriggerSmartContract) req, streamObserver);
                    return;
                case 35:
                    this.serviceImpl.estimateEnergy((SmartContractOuterClass.TriggerSmartContract) req, streamObserver);
                    return;
                case 36:
                    this.serviceImpl.getTransactionInfoByBlockNum((GrpcAPI.NumberMessage) req, streamObserver);
                    return;
                case 37:
                    this.serviceImpl.getMarketOrderById((GrpcAPI.BytesMessage) req, streamObserver);
                    return;
                case 38:
                    this.serviceImpl.getMarketOrderByAccount((GrpcAPI.BytesMessage) req, streamObserver);
                    return;
                case 39:
                    this.serviceImpl.getMarketPriceByPair((Protocol.MarketOrderPair) req, streamObserver);
                    return;
                case 40:
                    this.serviceImpl.getMarketOrderListByPair((Protocol.MarketOrderPair) req, streamObserver);
                    return;
                case 41:
                    this.serviceImpl.getMarketPairList((GrpcAPI.EmptyMessage) req, streamObserver);
                    return;
                case 42:
                    this.serviceImpl.getBurnTrx((GrpcAPI.EmptyMessage) req, streamObserver);
                    return;
                case 43:
                    this.serviceImpl.getBlock((GrpcAPI.BlockReq) req, streamObserver);
                    return;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public StreamObserver<Req> invoke(StreamObserver<Resp> streamObserver) {
            throw new AssertionError();
        }
    }

    private static abstract class WalletSolidityBaseDescriptorSupplier implements ProtoFileDescriptorSupplier, ProtoServiceDescriptorSupplier {
        WalletSolidityBaseDescriptorSupplier() {
        }

        @Override
        public Descriptors.FileDescriptor getFileDescriptor() {
            return GrpcAPI.getDescriptor();
        }

        @Override
        public Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("WalletSolidity");
        }
    }

    public static final class WalletSolidityFileDescriptorSupplier extends WalletSolidityBaseDescriptorSupplier {
        WalletSolidityFileDescriptorSupplier() {
        }
    }

    public static final class WalletSolidityMethodDescriptorSupplier extends WalletSolidityBaseDescriptorSupplier implements ProtoMethodDescriptorSupplier {
        private final String methodName;

        WalletSolidityMethodDescriptorSupplier(String str) {
            this.methodName = str;
        }

        @Override
        public Descriptors.MethodDescriptor getMethodDescriptor() {
            return getServiceDescriptor().findMethodByName(this.methodName);
        }
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptor2 = serviceDescriptor;
        if (serviceDescriptor2 == null) {
            synchronized (WalletSolidityGrpc.class) {
                serviceDescriptor2 = serviceDescriptor;
                if (serviceDescriptor2 == null) {
                    serviceDescriptor2 = ServiceDescriptor.newBuilder(SERVICE_NAME).setSchemaDescriptor(new WalletSolidityFileDescriptorSupplier()).addMethod(getGetAccountMethod()).addMethod(getGetAccountByIdMethod()).addMethod(getListWitnessesMethod()).addMethod(getGetAssetIssueListMethod()).addMethod(getGetPaginatedAssetIssueListMethod()).addMethod(getGetAssetIssueByNameMethod()).addMethod(getGetAssetIssueListByNameMethod()).addMethod(getGetAssetIssueByIdMethod()).addMethod(getGetNowBlockMethod()).addMethod(getGetNowBlock2Method()).addMethod(getGetBlockByNumMethod()).addMethod(getGetBlockByNum2Method()).addMethod(getGetTransactionCountByBlockNumMethod()).addMethod(getGetDelegatedResourceMethod()).addMethod(getGetDelegatedResourceV2Method()).addMethod(getGetDelegatedResourceAccountIndexMethod()).addMethod(getGetDelegatedResourceAccountIndexV2Method()).addMethod(getGetCanDelegatedMaxSizeMethod()).addMethod(getGetAvailableUnfreezeCountMethod()).addMethod(getGetCanWithdrawUnfreezeAmountMethod()).addMethod(getGetExchangeByIdMethod()).addMethod(getListExchangesMethod()).addMethod(getGetTransactionByIdMethod()).addMethod(getGetTransactionInfoByIdMethod()).addMethod(getGetMerkleTreeVoucherInfoMethod()).addMethod(getScanNoteByIvkMethod()).addMethod(getScanAndMarkNoteByIvkMethod()).addMethod(getScanNoteByOvkMethod()).addMethod(getIsSpendMethod()).addMethod(getScanShieldedTRC20NotesByIvkMethod()).addMethod(getScanShieldedTRC20NotesByOvkMethod()).addMethod(getIsShieldedTRC20ContractNoteSpentMethod()).addMethod(getGetRewardInfoMethod()).addMethod(getGetBrokerageInfoMethod()).addMethod(getTriggerConstantContractMethod()).addMethod(getEstimateEnergyMethod()).addMethod(getGetTransactionInfoByBlockNumMethod()).addMethod(getGetMarketOrderByIdMethod()).addMethod(getGetMarketOrderByAccountMethod()).addMethod(getGetMarketPriceByPairMethod()).addMethod(getGetMarketOrderListByPairMethod()).addMethod(getGetMarketPairListMethod()).addMethod(getGetBurnTrxMethod()).addMethod(getGetBlockMethod()).build();
                    serviceDescriptor = serviceDescriptor2;
                }
            }
        }
        return serviceDescriptor2;
    }
}
