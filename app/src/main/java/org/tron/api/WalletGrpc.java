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
import org.tron.protos.contract.AccountContract;
import org.tron.protos.contract.AssetIssueContractOuterClass;
import org.tron.protos.contract.BalanceContract;
import org.tron.protos.contract.ExchangeContract;
import org.tron.protos.contract.MarketContract;
import org.tron.protos.contract.ProposalContract;
import org.tron.protos.contract.ShieldContract;
import org.tron.protos.contract.SmartContractOuterClass;
import org.tron.protos.contract.StorageContract;
import org.tron.protos.contract.WitnessContract;
public final class WalletGrpc {
    private static final int METHODID_ACCOUNT_PERMISSION_UPDATE = 104;
    private static final int METHODID_BROADCAST_TRANSACTION = 6;
    private static final int METHODID_BUY_STORAGE = 45;
    private static final int METHODID_BUY_STORAGE_BYTES = 46;
    private static final int METHODID_CANCEL_ALL_UNFREEZE_V2 = 39;
    private static final int METHODID_CLEAR_CONTRACT_ABI = 83;
    private static final int METHODID_CREATE_ACCOUNT = 18;
    private static final int METHODID_CREATE_ACCOUNT2 = 19;
    private static final int METHODID_CREATE_ASSET_ISSUE = 14;
    private static final int METHODID_CREATE_ASSET_ISSUE2 = 15;
    private static final int METHODID_CREATE_COMMON_TRANSACTION = 136;
    private static final int METHODID_CREATE_SHIELDED_CONTRACT_PARAMETERS = 130;
    private static final int METHODID_CREATE_SHIELDED_CONTRACT_PARAMETERS_WITHOUT_ASK = 131;
    private static final int METHODID_CREATE_SHIELDED_TRANSACTION = 111;
    private static final int METHODID_CREATE_SHIELDED_TRANSACTION_WITHOUT_SPEND_AUTH_SIG = 126;
    private static final int METHODID_CREATE_SHIELD_NULLIFIER = 129;
    private static final int METHODID_CREATE_SPEND_AUTH_SIG = 128;
    private static final int METHODID_CREATE_TRANSACTION = 4;
    private static final int METHODID_CREATE_TRANSACTION2 = 5;
    private static final int METHODID_CREATE_WITNESS = 20;
    private static final int METHODID_CREATE_WITNESS2 = 21;
    private static final int METHODID_DELEGATE_RESOURCE = 37;
    private static final int METHODID_DEPLOY_CONTRACT = 77;
    private static final int METHODID_ESTIMATE_ENERGY = 82;
    private static final int METHODID_EXCHANGE_CREATE = 48;
    private static final int METHODID_EXCHANGE_INJECT = 49;
    private static final int METHODID_EXCHANGE_TRANSACTION = 51;
    private static final int METHODID_EXCHANGE_WITHDRAW = 50;
    private static final int METHODID_FREEZE_BALANCE = 26;
    private static final int METHODID_FREEZE_BALANCE2 = 27;
    private static final int METHODID_FREEZE_BALANCE_V2 = 28;
    private static final int METHODID_GET_ACCOUNT = 0;
    private static final int METHODID_GET_ACCOUNT_BALANCE = 2;
    private static final int METHODID_GET_ACCOUNT_BY_ID = 1;
    private static final int METHODID_GET_ACCOUNT_NET = 61;
    private static final int METHODID_GET_ACCOUNT_RESOURCE = 62;
    private static final int METHODID_GET_AK_FROM_ASK = 118;
    private static final int METHODID_GET_ASSET_ISSUE_BY_ACCOUNT = 60;
    private static final int METHODID_GET_ASSET_ISSUE_BY_ID = 65;
    private static final int METHODID_GET_ASSET_ISSUE_BY_NAME = 63;
    private static final int METHODID_GET_ASSET_ISSUE_LIST = 99;
    private static final int METHODID_GET_ASSET_ISSUE_LIST_BY_NAME = 64;
    private static final int METHODID_GET_AVAILABLE_UNFREEZE_COUNT = 90;
    private static final int METHODID_GET_BLOCK = 142;
    private static final int METHODID_GET_BLOCK_BALANCE_TRACE = 3;
    private static final int METHODID_GET_BLOCK_BY_ID = 71;
    private static final int METHODID_GET_BLOCK_BY_LATEST_NUM = 74;
    private static final int METHODID_GET_BLOCK_BY_LATEST_NUM2 = 75;
    private static final int METHODID_GET_BLOCK_BY_LIMIT_NEXT = 72;
    private static final int METHODID_GET_BLOCK_BY_LIMIT_NEXT2 = 73;
    private static final int METHODID_GET_BLOCK_BY_NUM = 68;
    private static final int METHODID_GET_BLOCK_BY_NUM2 = 69;
    private static final int METHODID_GET_BROKERAGE_INFO = 109;
    private static final int METHODID_GET_BURN_TRX = 138;
    private static final int METHODID_GET_CAN_DELEGATED_MAX_SIZE = 89;
    private static final int METHODID_GET_CAN_WITHDRAW_UNFREEZE_AMOUNT = 91;
    private static final int METHODID_GET_CHAIN_PARAMETERS = 98;
    private static final int METHODID_GET_CONTRACT = 78;
    private static final int METHODID_GET_CONTRACT_INFO = 79;
    private static final int METHODID_GET_DELEGATED_RESOURCE = 85;
    private static final int METHODID_GET_DELEGATED_RESOURCE_ACCOUNT_INDEX = 87;
    private static final int METHODID_GET_DELEGATED_RESOURCE_ACCOUNT_INDEX_V2 = 88;
    private static final int METHODID_GET_DELEGATED_RESOURCE_V2 = 86;
    private static final int METHODID_GET_DIVERSIFIER = 121;
    private static final int METHODID_GET_EXCHANGE_BY_ID = 97;
    private static final int METHODID_GET_EXPANDED_SPENDING_KEY = 117;
    private static final int METHODID_GET_INCOMING_VIEWING_KEY = 120;
    private static final int METHODID_GET_MARKET_ORDER_BY_ACCOUNT = 55;
    private static final int METHODID_GET_MARKET_ORDER_BY_ID = 54;
    private static final int METHODID_GET_MARKET_ORDER_LIST_BY_PAIR = 57;
    private static final int METHODID_GET_MARKET_PAIR_LIST = 58;
    private static final int METHODID_GET_MARKET_PRICE_BY_PAIR = 56;
    private static final int METHODID_GET_MERKLE_TREE_VOUCHER_INFO = 112;
    private static final int METHODID_GET_NEW_SHIELDED_ADDRESS = 122;
    private static final int METHODID_GET_NEXT_MAINTENANCE_TIME = 102;
    private static final int METHODID_GET_NK_FROM_NSK = 119;
    private static final int METHODID_GET_NODE_INFO = 107;
    private static final int METHODID_GET_NOW_BLOCK = 66;
    private static final int METHODID_GET_NOW_BLOCK2 = 67;
    private static final int METHODID_GET_PAGINATED_ASSET_ISSUE_LIST = 100;
    private static final int METHODID_GET_PAGINATED_EXCHANGE_LIST = 96;
    private static final int METHODID_GET_PAGINATED_PROPOSAL_LIST = 93;
    private static final int METHODID_GET_PENDING_SIZE = 141;
    private static final int METHODID_GET_PROPOSAL_BY_ID = 94;
    private static final int METHODID_GET_RCM = 124;
    private static final int METHODID_GET_REWARD_INFO = 108;
    private static final int METHODID_GET_SHIELD_TRANSACTION_HASH = 127;
    private static final int METHODID_GET_SPENDING_KEY = 116;
    private static final int METHODID_GET_TRANSACTION_APPROVED_LIST = 106;
    private static final int METHODID_GET_TRANSACTION_BY_ID = 76;
    private static final int METHODID_GET_TRANSACTION_COUNT_BY_BLOCK_NUM = 70;
    private static final int METHODID_GET_TRANSACTION_FROM_PENDING = 139;
    private static final int METHODID_GET_TRANSACTION_INFO_BY_BLOCK_NUM = 137;
    private static final int METHODID_GET_TRANSACTION_INFO_BY_ID = 103;
    private static final int METHODID_GET_TRANSACTION_LIST_FROM_PENDING = 140;
    private static final int METHODID_GET_TRANSACTION_SIGN_WEIGHT = 105;
    private static final int METHODID_GET_TRIGGER_INPUT_FOR_SHIELDED_TRC20CONTRACT = 135;
    private static final int METHODID_GET_ZEN_PAYMENT_ADDRESS = 123;
    private static final int METHODID_IS_SHIELDED_TRC20CONTRACT_NOTE_SPENT = 134;
    private static final int METHODID_IS_SPEND = 125;
    private static final int METHODID_LIST_EXCHANGES = 95;
    private static final int METHODID_LIST_NODES = 59;
    private static final int METHODID_LIST_PROPOSALS = 92;
    private static final int METHODID_LIST_WITNESSES = 84;
    private static final int METHODID_MARKET_CANCEL_ORDER = 53;
    private static final int METHODID_MARKET_SELL_ASSET = 52;
    private static final int METHODID_PARTICIPATE_ASSET_ISSUE = 24;
    private static final int METHODID_PARTICIPATE_ASSET_ISSUE2 = 25;
    private static final int METHODID_PROPOSAL_APPROVE = 43;
    private static final int METHODID_PROPOSAL_CREATE = 42;
    private static final int METHODID_PROPOSAL_DELETE = 44;
    private static final int METHODID_SCAN_AND_MARK_NOTE_BY_IVK = 114;
    private static final int METHODID_SCAN_NOTE_BY_IVK = 113;
    private static final int METHODID_SCAN_NOTE_BY_OVK = 115;
    private static final int METHODID_SCAN_SHIELDED_TRC20NOTES_BY_IVK = 132;
    private static final int METHODID_SCAN_SHIELDED_TRC20NOTES_BY_OVK = 133;
    private static final int METHODID_SELL_STORAGE = 47;
    private static final int METHODID_SET_ACCOUNT_ID = 8;
    private static final int METHODID_TOTAL_TRANSACTION = 101;
    private static final int METHODID_TRANSFER_ASSET = 22;
    private static final int METHODID_TRANSFER_ASSET2 = 23;
    private static final int METHODID_TRIGGER_CONSTANT_CONTRACT = 81;
    private static final int METHODID_TRIGGER_CONTRACT = 80;
    private static final int METHODID_UNFREEZE_ASSET = 32;
    private static final int METHODID_UNFREEZE_ASSET2 = 33;
    private static final int METHODID_UNFREEZE_BALANCE = 29;
    private static final int METHODID_UNFREEZE_BALANCE2 = 30;
    private static final int METHODID_UNFREEZE_BALANCE_V2 = 31;
    private static final int METHODID_UN_DELEGATE_RESOURCE = 38;
    private static final int METHODID_UPDATE_ACCOUNT = 7;
    private static final int METHODID_UPDATE_ACCOUNT2 = 9;
    private static final int METHODID_UPDATE_ASSET = 40;
    private static final int METHODID_UPDATE_ASSET2 = 41;
    private static final int METHODID_UPDATE_BROKERAGE = 110;
    private static final int METHODID_UPDATE_ENERGY_LIMIT = 12;
    private static final int METHODID_UPDATE_SETTING = 11;
    private static final int METHODID_UPDATE_WITNESS = 16;
    private static final int METHODID_UPDATE_WITNESS2 = 17;
    private static final int METHODID_VOTE_WITNESS_ACCOUNT = 10;
    private static final int METHODID_VOTE_WITNESS_ACCOUNT2 = 13;
    private static final int METHODID_WITHDRAW_BALANCE = 34;
    private static final int METHODID_WITHDRAW_BALANCE2 = 35;
    private static final int METHODID_WITHDRAW_EXPIRE_UNFREEZE = 36;
    public static final String SERVICE_NAME = "protocol.Wallet";
    private static volatile MethodDescriptor<AccountContract.AccountPermissionUpdateContract, GrpcAPI.TransactionExtention> getAccountPermissionUpdateMethod;
    private static volatile MethodDescriptor<Protocol.Transaction, GrpcAPI.Return> getBroadcastTransactionMethod;
    private static volatile MethodDescriptor<StorageContract.BuyStorageBytesContract, GrpcAPI.TransactionExtention> getBuyStorageBytesMethod;
    private static volatile MethodDescriptor<StorageContract.BuyStorageContract, GrpcAPI.TransactionExtention> getBuyStorageMethod;
    private static volatile MethodDescriptor<BalanceContract.CancelAllUnfreezeV2Contract, GrpcAPI.TransactionExtention> getCancelAllUnfreezeV2Method;
    private static volatile MethodDescriptor<SmartContractOuterClass.ClearABIContract, GrpcAPI.TransactionExtention> getClearContractABIMethod;
    private static volatile MethodDescriptor<AccountContract.AccountCreateContract, GrpcAPI.TransactionExtention> getCreateAccount2Method;
    private static volatile MethodDescriptor<AccountContract.AccountCreateContract, Protocol.Transaction> getCreateAccountMethod;
    private static volatile MethodDescriptor<AssetIssueContractOuterClass.AssetIssueContract, GrpcAPI.TransactionExtention> getCreateAssetIssue2Method;
    private static volatile MethodDescriptor<AssetIssueContractOuterClass.AssetIssueContract, Protocol.Transaction> getCreateAssetIssueMethod;
    private static volatile MethodDescriptor<Protocol.Transaction, GrpcAPI.TransactionExtention> getCreateCommonTransactionMethod;
    private static volatile MethodDescriptor<GrpcAPI.NfParameters, GrpcAPI.BytesMessage> getCreateShieldNullifierMethod;
    private static volatile MethodDescriptor<GrpcAPI.PrivateShieldedTRC20Parameters, GrpcAPI.ShieldedTRC20Parameters> getCreateShieldedContractParametersMethod;
    private static volatile MethodDescriptor<GrpcAPI.PrivateShieldedTRC20ParametersWithoutAsk, GrpcAPI.ShieldedTRC20Parameters> getCreateShieldedContractParametersWithoutAskMethod;
    private static volatile MethodDescriptor<GrpcAPI.PrivateParameters, GrpcAPI.TransactionExtention> getCreateShieldedTransactionMethod;
    private static volatile MethodDescriptor<GrpcAPI.PrivateParametersWithoutAsk, GrpcAPI.TransactionExtention> getCreateShieldedTransactionWithoutSpendAuthSigMethod;
    private static volatile MethodDescriptor<GrpcAPI.SpendAuthSigParameters, GrpcAPI.BytesMessage> getCreateSpendAuthSigMethod;
    private static volatile MethodDescriptor<BalanceContract.TransferContract, GrpcAPI.TransactionExtention> getCreateTransaction2Method;
    private static volatile MethodDescriptor<BalanceContract.TransferContract, Protocol.Transaction> getCreateTransactionMethod;
    private static volatile MethodDescriptor<WitnessContract.WitnessCreateContract, GrpcAPI.TransactionExtention> getCreateWitness2Method;
    private static volatile MethodDescriptor<WitnessContract.WitnessCreateContract, Protocol.Transaction> getCreateWitnessMethod;
    private static volatile MethodDescriptor<BalanceContract.DelegateResourceContract, GrpcAPI.TransactionExtention> getDelegateResourceMethod;
    private static volatile MethodDescriptor<SmartContractOuterClass.CreateSmartContract, GrpcAPI.TransactionExtention> getDeployContractMethod;
    private static volatile MethodDescriptor<SmartContractOuterClass.TriggerSmartContract, GrpcAPI.EstimateEnergyMessage> getEstimateEnergyMethod;
    private static volatile MethodDescriptor<ExchangeContract.ExchangeCreateContract, GrpcAPI.TransactionExtention> getExchangeCreateMethod;
    private static volatile MethodDescriptor<ExchangeContract.ExchangeInjectContract, GrpcAPI.TransactionExtention> getExchangeInjectMethod;
    private static volatile MethodDescriptor<ExchangeContract.ExchangeTransactionContract, GrpcAPI.TransactionExtention> getExchangeTransactionMethod;
    private static volatile MethodDescriptor<ExchangeContract.ExchangeWithdrawContract, GrpcAPI.TransactionExtention> getExchangeWithdrawMethod;
    private static volatile MethodDescriptor<BalanceContract.FreezeBalanceContract, GrpcAPI.TransactionExtention> getFreezeBalance2Method;
    private static volatile MethodDescriptor<BalanceContract.FreezeBalanceContract, Protocol.Transaction> getFreezeBalanceMethod;
    private static volatile MethodDescriptor<BalanceContract.FreezeBalanceV2Contract, GrpcAPI.TransactionExtention> getFreezeBalanceV2Method;
    private static volatile MethodDescriptor<BalanceContract.AccountBalanceRequest, BalanceContract.AccountBalanceResponse> getGetAccountBalanceMethod;
    private static volatile MethodDescriptor<Protocol.Account, Protocol.Account> getGetAccountByIdMethod;
    private static volatile MethodDescriptor<Protocol.Account, Protocol.Account> getGetAccountMethod;
    private static volatile MethodDescriptor<Protocol.Account, GrpcAPI.AccountNetMessage> getGetAccountNetMethod;
    private static volatile MethodDescriptor<Protocol.Account, GrpcAPI.AccountResourceMessage> getGetAccountResourceMethod;
    private static volatile MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.BytesMessage> getGetAkFromAskMethod;
    private static volatile MethodDescriptor<Protocol.Account, GrpcAPI.AssetIssueList> getGetAssetIssueByAccountMethod;
    private static volatile MethodDescriptor<GrpcAPI.BytesMessage, AssetIssueContractOuterClass.AssetIssueContract> getGetAssetIssueByIdMethod;
    private static volatile MethodDescriptor<GrpcAPI.BytesMessage, AssetIssueContractOuterClass.AssetIssueContract> getGetAssetIssueByNameMethod;
    private static volatile MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.AssetIssueList> getGetAssetIssueListByNameMethod;
    private static volatile MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.AssetIssueList> getGetAssetIssueListMethod;
    private static volatile MethodDescriptor<GrpcAPI.GetAvailableUnfreezeCountRequestMessage, GrpcAPI.GetAvailableUnfreezeCountResponseMessage> getGetAvailableUnfreezeCountMethod;
    private static volatile MethodDescriptor<BalanceContract.BlockBalanceTrace.BlockIdentifier, BalanceContract.BlockBalanceTrace> getGetBlockBalanceTraceMethod;
    private static volatile MethodDescriptor<GrpcAPI.BytesMessage, Protocol.Block> getGetBlockByIdMethod;
    private static volatile MethodDescriptor<GrpcAPI.NumberMessage, GrpcAPI.BlockListExtention> getGetBlockByLatestNum2Method;
    private static volatile MethodDescriptor<GrpcAPI.NumberMessage, GrpcAPI.BlockList> getGetBlockByLatestNumMethod;
    private static volatile MethodDescriptor<GrpcAPI.BlockLimit, GrpcAPI.BlockListExtention> getGetBlockByLimitNext2Method;
    private static volatile MethodDescriptor<GrpcAPI.BlockLimit, GrpcAPI.BlockList> getGetBlockByLimitNextMethod;
    private static volatile MethodDescriptor<GrpcAPI.NumberMessage, GrpcAPI.BlockExtention> getGetBlockByNum2Method;
    private static volatile MethodDescriptor<GrpcAPI.NumberMessage, Protocol.Block> getGetBlockByNumMethod;
    private static volatile MethodDescriptor<GrpcAPI.BlockReq, GrpcAPI.BlockExtention> getGetBlockMethod;
    private static volatile MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.NumberMessage> getGetBrokerageInfoMethod;
    private static volatile MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.NumberMessage> getGetBurnTrxMethod;
    private static volatile MethodDescriptor<GrpcAPI.CanDelegatedMaxSizeRequestMessage, GrpcAPI.CanDelegatedMaxSizeResponseMessage> getGetCanDelegatedMaxSizeMethod;
    private static volatile MethodDescriptor<GrpcAPI.CanWithdrawUnfreezeAmountRequestMessage, GrpcAPI.CanWithdrawUnfreezeAmountResponseMessage> getGetCanWithdrawUnfreezeAmountMethod;
    private static volatile MethodDescriptor<GrpcAPI.EmptyMessage, Protocol.ChainParameters> getGetChainParametersMethod;
    private static volatile MethodDescriptor<GrpcAPI.BytesMessage, SmartContractOuterClass.SmartContractDataWrapper> getGetContractInfoMethod;
    private static volatile MethodDescriptor<GrpcAPI.BytesMessage, SmartContractOuterClass.SmartContract> getGetContractMethod;
    private static volatile MethodDescriptor<GrpcAPI.BytesMessage, Protocol.DelegatedResourceAccountIndex> getGetDelegatedResourceAccountIndexMethod;
    private static volatile MethodDescriptor<GrpcAPI.BytesMessage, Protocol.DelegatedResourceAccountIndex> getGetDelegatedResourceAccountIndexV2Method;
    private static volatile MethodDescriptor<GrpcAPI.DelegatedResourceMessage, GrpcAPI.DelegatedResourceList> getGetDelegatedResourceMethod;
    private static volatile MethodDescriptor<GrpcAPI.DelegatedResourceMessage, GrpcAPI.DelegatedResourceList> getGetDelegatedResourceV2Method;
    private static volatile MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.DiversifierMessage> getGetDiversifierMethod;
    private static volatile MethodDescriptor<GrpcAPI.BytesMessage, Protocol.Exchange> getGetExchangeByIdMethod;
    private static volatile MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.ExpandedSpendingKeyMessage> getGetExpandedSpendingKeyMethod;
    private static volatile MethodDescriptor<GrpcAPI.ViewingKeyMessage, GrpcAPI.IncomingViewingKeyMessage> getGetIncomingViewingKeyMethod;
    private static volatile MethodDescriptor<GrpcAPI.BytesMessage, Protocol.MarketOrderList> getGetMarketOrderByAccountMethod;
    private static volatile MethodDescriptor<GrpcAPI.BytesMessage, Protocol.MarketOrder> getGetMarketOrderByIdMethod;
    private static volatile MethodDescriptor<Protocol.MarketOrderPair, Protocol.MarketOrderList> getGetMarketOrderListByPairMethod;
    private static volatile MethodDescriptor<GrpcAPI.EmptyMessage, Protocol.MarketOrderPairList> getGetMarketPairListMethod;
    private static volatile MethodDescriptor<Protocol.MarketOrderPair, Protocol.MarketPriceList> getGetMarketPriceByPairMethod;
    private static volatile MethodDescriptor<ShieldContract.OutputPointInfo, ShieldContract.IncrementalMerkleVoucherInfo> getGetMerkleTreeVoucherInfoMethod;
    private static volatile MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.ShieldedAddressInfo> getGetNewShieldedAddressMethod;
    private static volatile MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.NumberMessage> getGetNextMaintenanceTimeMethod;
    private static volatile MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.BytesMessage> getGetNkFromNskMethod;
    private static volatile MethodDescriptor<GrpcAPI.EmptyMessage, Protocol.NodeInfo> getGetNodeInfoMethod;
    private static volatile MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.BlockExtention> getGetNowBlock2Method;
    private static volatile MethodDescriptor<GrpcAPI.EmptyMessage, Protocol.Block> getGetNowBlockMethod;
    private static volatile MethodDescriptor<GrpcAPI.PaginatedMessage, GrpcAPI.AssetIssueList> getGetPaginatedAssetIssueListMethod;
    private static volatile MethodDescriptor<GrpcAPI.PaginatedMessage, GrpcAPI.ExchangeList> getGetPaginatedExchangeListMethod;
    private static volatile MethodDescriptor<GrpcAPI.PaginatedMessage, GrpcAPI.ProposalList> getGetPaginatedProposalListMethod;
    private static volatile MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.NumberMessage> getGetPendingSizeMethod;
    private static volatile MethodDescriptor<GrpcAPI.BytesMessage, Protocol.Proposal> getGetProposalByIdMethod;
    private static volatile MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.BytesMessage> getGetRcmMethod;
    private static volatile MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.NumberMessage> getGetRewardInfoMethod;
    private static volatile MethodDescriptor<Protocol.Transaction, GrpcAPI.BytesMessage> getGetShieldTransactionHashMethod;
    private static volatile MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.BytesMessage> getGetSpendingKeyMethod;
    private static volatile MethodDescriptor<Protocol.Transaction, GrpcAPI.TransactionApprovedList> getGetTransactionApprovedListMethod;
    private static volatile MethodDescriptor<GrpcAPI.BytesMessage, Protocol.Transaction> getGetTransactionByIdMethod;
    private static volatile MethodDescriptor<GrpcAPI.NumberMessage, GrpcAPI.NumberMessage> getGetTransactionCountByBlockNumMethod;
    private static volatile MethodDescriptor<GrpcAPI.BytesMessage, Protocol.Transaction> getGetTransactionFromPendingMethod;
    private static volatile MethodDescriptor<GrpcAPI.NumberMessage, GrpcAPI.TransactionInfoList> getGetTransactionInfoByBlockNumMethod;
    private static volatile MethodDescriptor<GrpcAPI.BytesMessage, Protocol.TransactionInfo> getGetTransactionInfoByIdMethod;
    private static volatile MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.TransactionIdList> getGetTransactionListFromPendingMethod;
    private static volatile MethodDescriptor<Protocol.Transaction, GrpcAPI.TransactionSignWeight> getGetTransactionSignWeightMethod;
    private static volatile MethodDescriptor<GrpcAPI.ShieldedTRC20TriggerContractParameters, GrpcAPI.BytesMessage> getGetTriggerInputForShieldedTRC20ContractMethod;
    private static volatile MethodDescriptor<GrpcAPI.IncomingViewingKeyDiversifierMessage, GrpcAPI.PaymentAddressMessage> getGetZenPaymentAddressMethod;
    private static volatile MethodDescriptor<GrpcAPI.NfTRC20Parameters, GrpcAPI.NullifierResult> getIsShieldedTRC20ContractNoteSpentMethod;
    private static volatile MethodDescriptor<GrpcAPI.NoteParameters, GrpcAPI.SpendResult> getIsSpendMethod;
    private static volatile MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.ExchangeList> getListExchangesMethod;
    private static volatile MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.NodeList> getListNodesMethod;
    private static volatile MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.ProposalList> getListProposalsMethod;
    private static volatile MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.WitnessList> getListWitnessesMethod;
    private static volatile MethodDescriptor<MarketContract.MarketCancelOrderContract, GrpcAPI.TransactionExtention> getMarketCancelOrderMethod;
    private static volatile MethodDescriptor<MarketContract.MarketSellAssetContract, GrpcAPI.TransactionExtention> getMarketSellAssetMethod;
    private static volatile MethodDescriptor<AssetIssueContractOuterClass.ParticipateAssetIssueContract, GrpcAPI.TransactionExtention> getParticipateAssetIssue2Method;
    private static volatile MethodDescriptor<AssetIssueContractOuterClass.ParticipateAssetIssueContract, Protocol.Transaction> getParticipateAssetIssueMethod;
    private static volatile MethodDescriptor<ProposalContract.ProposalApproveContract, GrpcAPI.TransactionExtention> getProposalApproveMethod;
    private static volatile MethodDescriptor<ProposalContract.ProposalCreateContract, GrpcAPI.TransactionExtention> getProposalCreateMethod;
    private static volatile MethodDescriptor<ProposalContract.ProposalDeleteContract, GrpcAPI.TransactionExtention> getProposalDeleteMethod;
    private static volatile MethodDescriptor<GrpcAPI.IvkDecryptAndMarkParameters, GrpcAPI.DecryptNotesMarked> getScanAndMarkNoteByIvkMethod;
    private static volatile MethodDescriptor<GrpcAPI.IvkDecryptParameters, GrpcAPI.DecryptNotes> getScanNoteByIvkMethod;
    private static volatile MethodDescriptor<GrpcAPI.OvkDecryptParameters, GrpcAPI.DecryptNotes> getScanNoteByOvkMethod;
    private static volatile MethodDescriptor<GrpcAPI.IvkDecryptTRC20Parameters, GrpcAPI.DecryptNotesTRC20> getScanShieldedTRC20NotesByIvkMethod;
    private static volatile MethodDescriptor<GrpcAPI.OvkDecryptTRC20Parameters, GrpcAPI.DecryptNotesTRC20> getScanShieldedTRC20NotesByOvkMethod;
    private static volatile MethodDescriptor<StorageContract.SellStorageContract, GrpcAPI.TransactionExtention> getSellStorageMethod;
    private static volatile MethodDescriptor<AccountContract.SetAccountIdContract, Protocol.Transaction> getSetAccountIdMethod;
    private static volatile MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.NumberMessage> getTotalTransactionMethod;
    private static volatile MethodDescriptor<AssetIssueContractOuterClass.TransferAssetContract, GrpcAPI.TransactionExtention> getTransferAsset2Method;
    private static volatile MethodDescriptor<AssetIssueContractOuterClass.TransferAssetContract, Protocol.Transaction> getTransferAssetMethod;
    private static volatile MethodDescriptor<SmartContractOuterClass.TriggerSmartContract, GrpcAPI.TransactionExtention> getTriggerConstantContractMethod;
    private static volatile MethodDescriptor<SmartContractOuterClass.TriggerSmartContract, GrpcAPI.TransactionExtention> getTriggerContractMethod;
    private static volatile MethodDescriptor<BalanceContract.UnDelegateResourceContract, GrpcAPI.TransactionExtention> getUnDelegateResourceMethod;
    private static volatile MethodDescriptor<AssetIssueContractOuterClass.UnfreezeAssetContract, GrpcAPI.TransactionExtention> getUnfreezeAsset2Method;
    private static volatile MethodDescriptor<AssetIssueContractOuterClass.UnfreezeAssetContract, Protocol.Transaction> getUnfreezeAssetMethod;
    private static volatile MethodDescriptor<BalanceContract.UnfreezeBalanceContract, GrpcAPI.TransactionExtention> getUnfreezeBalance2Method;
    private static volatile MethodDescriptor<BalanceContract.UnfreezeBalanceContract, Protocol.Transaction> getUnfreezeBalanceMethod;
    private static volatile MethodDescriptor<BalanceContract.UnfreezeBalanceV2Contract, GrpcAPI.TransactionExtention> getUnfreezeBalanceV2Method;
    private static volatile MethodDescriptor<AccountContract.AccountUpdateContract, GrpcAPI.TransactionExtention> getUpdateAccount2Method;
    private static volatile MethodDescriptor<AccountContract.AccountUpdateContract, Protocol.Transaction> getUpdateAccountMethod;
    private static volatile MethodDescriptor<AssetIssueContractOuterClass.UpdateAssetContract, GrpcAPI.TransactionExtention> getUpdateAsset2Method;
    private static volatile MethodDescriptor<AssetIssueContractOuterClass.UpdateAssetContract, Protocol.Transaction> getUpdateAssetMethod;
    private static volatile MethodDescriptor<StorageContract.UpdateBrokerageContract, GrpcAPI.TransactionExtention> getUpdateBrokerageMethod;
    private static volatile MethodDescriptor<SmartContractOuterClass.UpdateEnergyLimitContract, GrpcAPI.TransactionExtention> getUpdateEnergyLimitMethod;
    private static volatile MethodDescriptor<SmartContractOuterClass.UpdateSettingContract, GrpcAPI.TransactionExtention> getUpdateSettingMethod;
    private static volatile MethodDescriptor<WitnessContract.WitnessUpdateContract, GrpcAPI.TransactionExtention> getUpdateWitness2Method;
    private static volatile MethodDescriptor<WitnessContract.WitnessUpdateContract, Protocol.Transaction> getUpdateWitnessMethod;
    private static volatile MethodDescriptor<WitnessContract.VoteWitnessContract, GrpcAPI.TransactionExtention> getVoteWitnessAccount2Method;
    private static volatile MethodDescriptor<WitnessContract.VoteWitnessContract, Protocol.Transaction> getVoteWitnessAccountMethod;
    private static volatile MethodDescriptor<BalanceContract.WithdrawBalanceContract, GrpcAPI.TransactionExtention> getWithdrawBalance2Method;
    private static volatile MethodDescriptor<BalanceContract.WithdrawBalanceContract, Protocol.Transaction> getWithdrawBalanceMethod;
    private static volatile MethodDescriptor<BalanceContract.WithdrawExpireUnfreezeContract, GrpcAPI.TransactionExtention> getWithdrawExpireUnfreezeMethod;
    private static volatile ServiceDescriptor serviceDescriptor;
    @Deprecated
    public static final MethodDescriptor<Protocol.Account, Protocol.Account> METHOD_GET_ACCOUNT = getGetAccountMethod();
    @Deprecated
    public static final MethodDescriptor<Protocol.Account, Protocol.Account> METHOD_GET_ACCOUNT_BY_ID = getGetAccountByIdMethod();
    @Deprecated
    public static final MethodDescriptor<BalanceContract.AccountBalanceRequest, BalanceContract.AccountBalanceResponse> METHOD_GET_ACCOUNT_BALANCE = getGetAccountBalanceMethod();
    @Deprecated
    public static final MethodDescriptor<BalanceContract.BlockBalanceTrace.BlockIdentifier, BalanceContract.BlockBalanceTrace> METHOD_GET_BLOCK_BALANCE_TRACE = getGetBlockBalanceTraceMethod();
    @Deprecated
    public static final MethodDescriptor<BalanceContract.TransferContract, Protocol.Transaction> METHOD_CREATE_TRANSACTION = getCreateTransactionMethod();
    @Deprecated
    public static final MethodDescriptor<BalanceContract.TransferContract, GrpcAPI.TransactionExtention> METHOD_CREATE_TRANSACTION2 = getCreateTransaction2Method();
    @Deprecated
    public static final MethodDescriptor<Protocol.Transaction, GrpcAPI.Return> METHOD_BROADCAST_TRANSACTION = getBroadcastTransactionMethod();
    @Deprecated
    public static final MethodDescriptor<AccountContract.AccountUpdateContract, Protocol.Transaction> METHOD_UPDATE_ACCOUNT = getUpdateAccountMethod();
    @Deprecated
    public static final MethodDescriptor<AccountContract.SetAccountIdContract, Protocol.Transaction> METHOD_SET_ACCOUNT_ID = getSetAccountIdMethod();
    @Deprecated
    public static final MethodDescriptor<AccountContract.AccountUpdateContract, GrpcAPI.TransactionExtention> METHOD_UPDATE_ACCOUNT2 = getUpdateAccount2Method();
    @Deprecated
    public static final MethodDescriptor<WitnessContract.VoteWitnessContract, Protocol.Transaction> METHOD_VOTE_WITNESS_ACCOUNT = getVoteWitnessAccountMethod();
    @Deprecated
    public static final MethodDescriptor<SmartContractOuterClass.UpdateSettingContract, GrpcAPI.TransactionExtention> METHOD_UPDATE_SETTING = getUpdateSettingMethod();
    @Deprecated
    public static final MethodDescriptor<SmartContractOuterClass.UpdateEnergyLimitContract, GrpcAPI.TransactionExtention> METHOD_UPDATE_ENERGY_LIMIT = getUpdateEnergyLimitMethod();
    @Deprecated
    public static final MethodDescriptor<WitnessContract.VoteWitnessContract, GrpcAPI.TransactionExtention> METHOD_VOTE_WITNESS_ACCOUNT2 = getVoteWitnessAccount2Method();
    @Deprecated
    public static final MethodDescriptor<AssetIssueContractOuterClass.AssetIssueContract, Protocol.Transaction> METHOD_CREATE_ASSET_ISSUE = getCreateAssetIssueMethod();
    @Deprecated
    public static final MethodDescriptor<AssetIssueContractOuterClass.AssetIssueContract, GrpcAPI.TransactionExtention> METHOD_CREATE_ASSET_ISSUE2 = getCreateAssetIssue2Method();
    @Deprecated
    public static final MethodDescriptor<WitnessContract.WitnessUpdateContract, Protocol.Transaction> METHOD_UPDATE_WITNESS = getUpdateWitnessMethod();
    @Deprecated
    public static final MethodDescriptor<WitnessContract.WitnessUpdateContract, GrpcAPI.TransactionExtention> METHOD_UPDATE_WITNESS2 = getUpdateWitness2Method();
    @Deprecated
    public static final MethodDescriptor<AccountContract.AccountCreateContract, Protocol.Transaction> METHOD_CREATE_ACCOUNT = getCreateAccountMethod();
    @Deprecated
    public static final MethodDescriptor<AccountContract.AccountCreateContract, GrpcAPI.TransactionExtention> METHOD_CREATE_ACCOUNT2 = getCreateAccount2Method();
    @Deprecated
    public static final MethodDescriptor<WitnessContract.WitnessCreateContract, Protocol.Transaction> METHOD_CREATE_WITNESS = getCreateWitnessMethod();
    @Deprecated
    public static final MethodDescriptor<WitnessContract.WitnessCreateContract, GrpcAPI.TransactionExtention> METHOD_CREATE_WITNESS2 = getCreateWitness2Method();
    @Deprecated
    public static final MethodDescriptor<AssetIssueContractOuterClass.TransferAssetContract, Protocol.Transaction> METHOD_TRANSFER_ASSET = getTransferAssetMethod();
    @Deprecated
    public static final MethodDescriptor<AssetIssueContractOuterClass.TransferAssetContract, GrpcAPI.TransactionExtention> METHOD_TRANSFER_ASSET2 = getTransferAsset2Method();
    @Deprecated
    public static final MethodDescriptor<AssetIssueContractOuterClass.ParticipateAssetIssueContract, Protocol.Transaction> METHOD_PARTICIPATE_ASSET_ISSUE = getParticipateAssetIssueMethod();
    @Deprecated
    public static final MethodDescriptor<AssetIssueContractOuterClass.ParticipateAssetIssueContract, GrpcAPI.TransactionExtention> METHOD_PARTICIPATE_ASSET_ISSUE2 = getParticipateAssetIssue2Method();
    @Deprecated
    public static final MethodDescriptor<BalanceContract.FreezeBalanceContract, Protocol.Transaction> METHOD_FREEZE_BALANCE = getFreezeBalanceMethod();
    @Deprecated
    public static final MethodDescriptor<BalanceContract.FreezeBalanceContract, GrpcAPI.TransactionExtention> METHOD_FREEZE_BALANCE2 = getFreezeBalance2Method();
    @Deprecated
    public static final MethodDescriptor<BalanceContract.FreezeBalanceV2Contract, GrpcAPI.TransactionExtention> METHOD_FREEZE_BALANCE_V2 = getFreezeBalanceV2Method();
    @Deprecated
    public static final MethodDescriptor<BalanceContract.UnfreezeBalanceContract, Protocol.Transaction> METHOD_UNFREEZE_BALANCE = getUnfreezeBalanceMethod();
    @Deprecated
    public static final MethodDescriptor<BalanceContract.UnfreezeBalanceContract, GrpcAPI.TransactionExtention> METHOD_UNFREEZE_BALANCE2 = getUnfreezeBalance2Method();
    @Deprecated
    public static final MethodDescriptor<BalanceContract.UnfreezeBalanceV2Contract, GrpcAPI.TransactionExtention> METHOD_UNFREEZE_BALANCE_V2 = getUnfreezeBalanceV2Method();
    @Deprecated
    public static final MethodDescriptor<AssetIssueContractOuterClass.UnfreezeAssetContract, Protocol.Transaction> METHOD_UNFREEZE_ASSET = getUnfreezeAssetMethod();
    @Deprecated
    public static final MethodDescriptor<AssetIssueContractOuterClass.UnfreezeAssetContract, GrpcAPI.TransactionExtention> METHOD_UNFREEZE_ASSET2 = getUnfreezeAsset2Method();
    @Deprecated
    public static final MethodDescriptor<BalanceContract.WithdrawBalanceContract, Protocol.Transaction> METHOD_WITHDRAW_BALANCE = getWithdrawBalanceMethod();
    @Deprecated
    public static final MethodDescriptor<BalanceContract.WithdrawBalanceContract, GrpcAPI.TransactionExtention> METHOD_WITHDRAW_BALANCE2 = getWithdrawBalance2Method();
    @Deprecated
    public static final MethodDescriptor<BalanceContract.WithdrawExpireUnfreezeContract, GrpcAPI.TransactionExtention> METHOD_WITHDRAW_EXPIRE_UNFREEZE = getWithdrawExpireUnfreezeMethod();
    @Deprecated
    public static final MethodDescriptor<BalanceContract.DelegateResourceContract, GrpcAPI.TransactionExtention> METHOD_DELEGATE_RESOURCE = getDelegateResourceMethod();
    @Deprecated
    public static final MethodDescriptor<BalanceContract.UnDelegateResourceContract, GrpcAPI.TransactionExtention> METHOD_UN_DELEGATE_RESOURCE = getUnDelegateResourceMethod();
    @Deprecated
    public static final MethodDescriptor<BalanceContract.CancelAllUnfreezeV2Contract, GrpcAPI.TransactionExtention> METHOD_CANCEL_ALL_UNFREEZE_V2 = getCancelAllUnfreezeV2Method();
    @Deprecated
    public static final MethodDescriptor<AssetIssueContractOuterClass.UpdateAssetContract, Protocol.Transaction> METHOD_UPDATE_ASSET = getUpdateAssetMethod();
    @Deprecated
    public static final MethodDescriptor<AssetIssueContractOuterClass.UpdateAssetContract, GrpcAPI.TransactionExtention> METHOD_UPDATE_ASSET2 = getUpdateAsset2Method();
    @Deprecated
    public static final MethodDescriptor<ProposalContract.ProposalCreateContract, GrpcAPI.TransactionExtention> METHOD_PROPOSAL_CREATE = getProposalCreateMethod();
    @Deprecated
    public static final MethodDescriptor<ProposalContract.ProposalApproveContract, GrpcAPI.TransactionExtention> METHOD_PROPOSAL_APPROVE = getProposalApproveMethod();
    @Deprecated
    public static final MethodDescriptor<ProposalContract.ProposalDeleteContract, GrpcAPI.TransactionExtention> METHOD_PROPOSAL_DELETE = getProposalDeleteMethod();
    @Deprecated
    public static final MethodDescriptor<StorageContract.BuyStorageContract, GrpcAPI.TransactionExtention> METHOD_BUY_STORAGE = getBuyStorageMethod();
    @Deprecated
    public static final MethodDescriptor<StorageContract.BuyStorageBytesContract, GrpcAPI.TransactionExtention> METHOD_BUY_STORAGE_BYTES = getBuyStorageBytesMethod();
    @Deprecated
    public static final MethodDescriptor<StorageContract.SellStorageContract, GrpcAPI.TransactionExtention> METHOD_SELL_STORAGE = getSellStorageMethod();
    @Deprecated
    public static final MethodDescriptor<ExchangeContract.ExchangeCreateContract, GrpcAPI.TransactionExtention> METHOD_EXCHANGE_CREATE = getExchangeCreateMethod();
    @Deprecated
    public static final MethodDescriptor<ExchangeContract.ExchangeInjectContract, GrpcAPI.TransactionExtention> METHOD_EXCHANGE_INJECT = getExchangeInjectMethod();
    @Deprecated
    public static final MethodDescriptor<ExchangeContract.ExchangeWithdrawContract, GrpcAPI.TransactionExtention> METHOD_EXCHANGE_WITHDRAW = getExchangeWithdrawMethod();
    @Deprecated
    public static final MethodDescriptor<ExchangeContract.ExchangeTransactionContract, GrpcAPI.TransactionExtention> METHOD_EXCHANGE_TRANSACTION = getExchangeTransactionMethod();
    @Deprecated
    public static final MethodDescriptor<MarketContract.MarketSellAssetContract, GrpcAPI.TransactionExtention> METHOD_MARKET_SELL_ASSET = getMarketSellAssetMethod();
    @Deprecated
    public static final MethodDescriptor<MarketContract.MarketCancelOrderContract, GrpcAPI.TransactionExtention> METHOD_MARKET_CANCEL_ORDER = getMarketCancelOrderMethod();
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
    public static final MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.NodeList> METHOD_LIST_NODES = getListNodesMethod();
    @Deprecated
    public static final MethodDescriptor<Protocol.Account, GrpcAPI.AssetIssueList> METHOD_GET_ASSET_ISSUE_BY_ACCOUNT = getGetAssetIssueByAccountMethod();
    @Deprecated
    public static final MethodDescriptor<Protocol.Account, GrpcAPI.AccountNetMessage> METHOD_GET_ACCOUNT_NET = getGetAccountNetMethod();
    @Deprecated
    public static final MethodDescriptor<Protocol.Account, GrpcAPI.AccountResourceMessage> METHOD_GET_ACCOUNT_RESOURCE = getGetAccountResourceMethod();
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
    public static final MethodDescriptor<GrpcAPI.BytesMessage, Protocol.Block> METHOD_GET_BLOCK_BY_ID = getGetBlockByIdMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.BlockLimit, GrpcAPI.BlockList> METHOD_GET_BLOCK_BY_LIMIT_NEXT = getGetBlockByLimitNextMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.BlockLimit, GrpcAPI.BlockListExtention> METHOD_GET_BLOCK_BY_LIMIT_NEXT2 = getGetBlockByLimitNext2Method();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.NumberMessage, GrpcAPI.BlockList> METHOD_GET_BLOCK_BY_LATEST_NUM = getGetBlockByLatestNumMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.NumberMessage, GrpcAPI.BlockListExtention> METHOD_GET_BLOCK_BY_LATEST_NUM2 = getGetBlockByLatestNum2Method();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.BytesMessage, Protocol.Transaction> METHOD_GET_TRANSACTION_BY_ID = getGetTransactionByIdMethod();
    @Deprecated
    public static final MethodDescriptor<SmartContractOuterClass.CreateSmartContract, GrpcAPI.TransactionExtention> METHOD_DEPLOY_CONTRACT = getDeployContractMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.BytesMessage, SmartContractOuterClass.SmartContract> METHOD_GET_CONTRACT = getGetContractMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.BytesMessage, SmartContractOuterClass.SmartContractDataWrapper> METHOD_GET_CONTRACT_INFO = getGetContractInfoMethod();
    @Deprecated
    public static final MethodDescriptor<SmartContractOuterClass.TriggerSmartContract, GrpcAPI.TransactionExtention> METHOD_TRIGGER_CONTRACT = getTriggerContractMethod();
    @Deprecated
    public static final MethodDescriptor<SmartContractOuterClass.TriggerSmartContract, GrpcAPI.TransactionExtention> METHOD_TRIGGER_CONSTANT_CONTRACT = getTriggerConstantContractMethod();
    @Deprecated
    public static final MethodDescriptor<SmartContractOuterClass.TriggerSmartContract, GrpcAPI.EstimateEnergyMessage> METHOD_ESTIMATE_ENERGY = getEstimateEnergyMethod();
    @Deprecated
    public static final MethodDescriptor<SmartContractOuterClass.ClearABIContract, GrpcAPI.TransactionExtention> METHOD_CLEAR_CONTRACT_ABI = getClearContractABIMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.WitnessList> METHOD_LIST_WITNESSES = getListWitnessesMethod();
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
    public static final MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.ProposalList> METHOD_LIST_PROPOSALS = getListProposalsMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.PaginatedMessage, GrpcAPI.ProposalList> METHOD_GET_PAGINATED_PROPOSAL_LIST = getGetPaginatedProposalListMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.BytesMessage, Protocol.Proposal> METHOD_GET_PROPOSAL_BY_ID = getGetProposalByIdMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.ExchangeList> METHOD_LIST_EXCHANGES = getListExchangesMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.PaginatedMessage, GrpcAPI.ExchangeList> METHOD_GET_PAGINATED_EXCHANGE_LIST = getGetPaginatedExchangeListMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.BytesMessage, Protocol.Exchange> METHOD_GET_EXCHANGE_BY_ID = getGetExchangeByIdMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.EmptyMessage, Protocol.ChainParameters> METHOD_GET_CHAIN_PARAMETERS = getGetChainParametersMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.AssetIssueList> METHOD_GET_ASSET_ISSUE_LIST = getGetAssetIssueListMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.PaginatedMessage, GrpcAPI.AssetIssueList> METHOD_GET_PAGINATED_ASSET_ISSUE_LIST = getGetPaginatedAssetIssueListMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.NumberMessage> METHOD_TOTAL_TRANSACTION = getTotalTransactionMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.NumberMessage> METHOD_GET_NEXT_MAINTENANCE_TIME = getGetNextMaintenanceTimeMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.BytesMessage, Protocol.TransactionInfo> METHOD_GET_TRANSACTION_INFO_BY_ID = getGetTransactionInfoByIdMethod();
    @Deprecated
    public static final MethodDescriptor<AccountContract.AccountPermissionUpdateContract, GrpcAPI.TransactionExtention> METHOD_ACCOUNT_PERMISSION_UPDATE = getAccountPermissionUpdateMethod();
    @Deprecated
    public static final MethodDescriptor<Protocol.Transaction, GrpcAPI.TransactionSignWeight> METHOD_GET_TRANSACTION_SIGN_WEIGHT = getGetTransactionSignWeightMethod();
    @Deprecated
    public static final MethodDescriptor<Protocol.Transaction, GrpcAPI.TransactionApprovedList> METHOD_GET_TRANSACTION_APPROVED_LIST = getGetTransactionApprovedListMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.EmptyMessage, Protocol.NodeInfo> METHOD_GET_NODE_INFO = getGetNodeInfoMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.NumberMessage> METHOD_GET_REWARD_INFO = getGetRewardInfoMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.NumberMessage> METHOD_GET_BROKERAGE_INFO = getGetBrokerageInfoMethod();
    @Deprecated
    public static final MethodDescriptor<StorageContract.UpdateBrokerageContract, GrpcAPI.TransactionExtention> METHOD_UPDATE_BROKERAGE = getUpdateBrokerageMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.PrivateParameters, GrpcAPI.TransactionExtention> METHOD_CREATE_SHIELDED_TRANSACTION = getCreateShieldedTransactionMethod();
    @Deprecated
    public static final MethodDescriptor<ShieldContract.OutputPointInfo, ShieldContract.IncrementalMerkleVoucherInfo> METHOD_GET_MERKLE_TREE_VOUCHER_INFO = getGetMerkleTreeVoucherInfoMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.IvkDecryptParameters, GrpcAPI.DecryptNotes> METHOD_SCAN_NOTE_BY_IVK = getScanNoteByIvkMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.IvkDecryptAndMarkParameters, GrpcAPI.DecryptNotesMarked> METHOD_SCAN_AND_MARK_NOTE_BY_IVK = getScanAndMarkNoteByIvkMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.OvkDecryptParameters, GrpcAPI.DecryptNotes> METHOD_SCAN_NOTE_BY_OVK = getScanNoteByOvkMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.BytesMessage> METHOD_GET_SPENDING_KEY = getGetSpendingKeyMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.ExpandedSpendingKeyMessage> METHOD_GET_EXPANDED_SPENDING_KEY = getGetExpandedSpendingKeyMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.BytesMessage> METHOD_GET_AK_FROM_ASK = getGetAkFromAskMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.BytesMessage> METHOD_GET_NK_FROM_NSK = getGetNkFromNskMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.ViewingKeyMessage, GrpcAPI.IncomingViewingKeyMessage> METHOD_GET_INCOMING_VIEWING_KEY = getGetIncomingViewingKeyMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.DiversifierMessage> METHOD_GET_DIVERSIFIER = getGetDiversifierMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.ShieldedAddressInfo> METHOD_GET_NEW_SHIELDED_ADDRESS = getGetNewShieldedAddressMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.IncomingViewingKeyDiversifierMessage, GrpcAPI.PaymentAddressMessage> METHOD_GET_ZEN_PAYMENT_ADDRESS = getGetZenPaymentAddressMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.BytesMessage> METHOD_GET_RCM = getGetRcmMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.NoteParameters, GrpcAPI.SpendResult> METHOD_IS_SPEND = getIsSpendMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.PrivateParametersWithoutAsk, GrpcAPI.TransactionExtention> METHOD_CREATE_SHIELDED_TRANSACTION_WITHOUT_SPEND_AUTH_SIG = getCreateShieldedTransactionWithoutSpendAuthSigMethod();
    @Deprecated
    public static final MethodDescriptor<Protocol.Transaction, GrpcAPI.BytesMessage> METHOD_GET_SHIELD_TRANSACTION_HASH = getGetShieldTransactionHashMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.SpendAuthSigParameters, GrpcAPI.BytesMessage> METHOD_CREATE_SPEND_AUTH_SIG = getCreateSpendAuthSigMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.NfParameters, GrpcAPI.BytesMessage> METHOD_CREATE_SHIELD_NULLIFIER = getCreateShieldNullifierMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.PrivateShieldedTRC20Parameters, GrpcAPI.ShieldedTRC20Parameters> METHOD_CREATE_SHIELDED_CONTRACT_PARAMETERS = getCreateShieldedContractParametersMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.PrivateShieldedTRC20ParametersWithoutAsk, GrpcAPI.ShieldedTRC20Parameters> METHOD_CREATE_SHIELDED_CONTRACT_PARAMETERS_WITHOUT_ASK = getCreateShieldedContractParametersWithoutAskMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.IvkDecryptTRC20Parameters, GrpcAPI.DecryptNotesTRC20> METHOD_SCAN_SHIELDED_TRC20NOTES_BY_IVK = getScanShieldedTRC20NotesByIvkMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.OvkDecryptTRC20Parameters, GrpcAPI.DecryptNotesTRC20> METHOD_SCAN_SHIELDED_TRC20NOTES_BY_OVK = getScanShieldedTRC20NotesByOvkMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.NfTRC20Parameters, GrpcAPI.NullifierResult> METHOD_IS_SHIELDED_TRC20CONTRACT_NOTE_SPENT = getIsShieldedTRC20ContractNoteSpentMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.ShieldedTRC20TriggerContractParameters, GrpcAPI.BytesMessage> METHOD_GET_TRIGGER_INPUT_FOR_SHIELDED_TRC20CONTRACT = getGetTriggerInputForShieldedTRC20ContractMethod();
    @Deprecated
    public static final MethodDescriptor<Protocol.Transaction, GrpcAPI.TransactionExtention> METHOD_CREATE_COMMON_TRANSACTION = getCreateCommonTransactionMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.NumberMessage, GrpcAPI.TransactionInfoList> METHOD_GET_TRANSACTION_INFO_BY_BLOCK_NUM = getGetTransactionInfoByBlockNumMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.NumberMessage> METHOD_GET_BURN_TRX = getGetBurnTrxMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.BytesMessage, Protocol.Transaction> METHOD_GET_TRANSACTION_FROM_PENDING = getGetTransactionFromPendingMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.TransactionIdList> METHOD_GET_TRANSACTION_LIST_FROM_PENDING = getGetTransactionListFromPendingMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.NumberMessage> METHOD_GET_PENDING_SIZE = getGetPendingSizeMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.BlockReq, GrpcAPI.BlockExtention> METHOD_GET_BLOCK = getGetBlockMethod();

    private WalletGrpc() {
    }

    public static MethodDescriptor<Protocol.Account, Protocol.Account> getGetAccountMethod() {
        MethodDescriptor<Protocol.Account, Protocol.Account> methodDescriptor = getGetAccountMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetAccountMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetAccount")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(Protocol.Account.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.Account.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetAccount")).build();
                    getGetAccountMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<Protocol.Account, Protocol.Account> getGetAccountByIdMethod() {
        MethodDescriptor<Protocol.Account, Protocol.Account> methodDescriptor = getGetAccountByIdMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetAccountByIdMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetAccountById")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(Protocol.Account.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.Account.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetAccountById")).build();
                    getGetAccountByIdMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<BalanceContract.AccountBalanceRequest, BalanceContract.AccountBalanceResponse> getGetAccountBalanceMethod() {
        MethodDescriptor<BalanceContract.AccountBalanceRequest, BalanceContract.AccountBalanceResponse> methodDescriptor = getGetAccountBalanceMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetAccountBalanceMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetAccountBalance")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(BalanceContract.AccountBalanceRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(BalanceContract.AccountBalanceResponse.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetAccountBalance")).build();
                    getGetAccountBalanceMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<BalanceContract.BlockBalanceTrace.BlockIdentifier, BalanceContract.BlockBalanceTrace> getGetBlockBalanceTraceMethod() {
        MethodDescriptor<BalanceContract.BlockBalanceTrace.BlockIdentifier, BalanceContract.BlockBalanceTrace> methodDescriptor = getGetBlockBalanceTraceMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetBlockBalanceTraceMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetBlockBalanceTrace")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(BalanceContract.BlockBalanceTrace.BlockIdentifier.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(BalanceContract.BlockBalanceTrace.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetBlockBalanceTrace")).build();
                    getGetBlockBalanceTraceMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<BalanceContract.TransferContract, Protocol.Transaction> getCreateTransactionMethod() {
        MethodDescriptor<BalanceContract.TransferContract, Protocol.Transaction> methodDescriptor = getCreateTransactionMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getCreateTransactionMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "CreateTransaction")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(BalanceContract.TransferContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.Transaction.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("CreateTransaction")).build();
                    getCreateTransactionMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<BalanceContract.TransferContract, GrpcAPI.TransactionExtention> getCreateTransaction2Method() {
        MethodDescriptor<BalanceContract.TransferContract, GrpcAPI.TransactionExtention> methodDescriptor = getCreateTransaction2Method;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getCreateTransaction2Method;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "CreateTransaction2")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(BalanceContract.TransferContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("CreateTransaction2")).build();
                    getCreateTransaction2Method = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<Protocol.Transaction, GrpcAPI.Return> getBroadcastTransactionMethod() {
        MethodDescriptor<Protocol.Transaction, GrpcAPI.Return> methodDescriptor = getBroadcastTransactionMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getBroadcastTransactionMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "BroadcastTransaction")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(Protocol.Transaction.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.Return.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("BroadcastTransaction")).build();
                    getBroadcastTransactionMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<AccountContract.AccountUpdateContract, Protocol.Transaction> getUpdateAccountMethod() {
        MethodDescriptor<AccountContract.AccountUpdateContract, Protocol.Transaction> methodDescriptor = getUpdateAccountMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getUpdateAccountMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "UpdateAccount")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(AccountContract.AccountUpdateContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.Transaction.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("UpdateAccount")).build();
                    getUpdateAccountMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<AccountContract.SetAccountIdContract, Protocol.Transaction> getSetAccountIdMethod() {
        MethodDescriptor<AccountContract.SetAccountIdContract, Protocol.Transaction> methodDescriptor = getSetAccountIdMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getSetAccountIdMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "SetAccountId")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(AccountContract.SetAccountIdContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.Transaction.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("SetAccountId")).build();
                    getSetAccountIdMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<AccountContract.AccountUpdateContract, GrpcAPI.TransactionExtention> getUpdateAccount2Method() {
        MethodDescriptor<AccountContract.AccountUpdateContract, GrpcAPI.TransactionExtention> methodDescriptor = getUpdateAccount2Method;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getUpdateAccount2Method;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "UpdateAccount2")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(AccountContract.AccountUpdateContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("UpdateAccount2")).build();
                    getUpdateAccount2Method = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<WitnessContract.VoteWitnessContract, Protocol.Transaction> getVoteWitnessAccountMethod() {
        MethodDescriptor<WitnessContract.VoteWitnessContract, Protocol.Transaction> methodDescriptor = getVoteWitnessAccountMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getVoteWitnessAccountMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "VoteWitnessAccount")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(WitnessContract.VoteWitnessContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.Transaction.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("VoteWitnessAccount")).build();
                    getVoteWitnessAccountMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<SmartContractOuterClass.UpdateSettingContract, GrpcAPI.TransactionExtention> getUpdateSettingMethod() {
        MethodDescriptor<SmartContractOuterClass.UpdateSettingContract, GrpcAPI.TransactionExtention> methodDescriptor = getUpdateSettingMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getUpdateSettingMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "UpdateSetting")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(SmartContractOuterClass.UpdateSettingContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("UpdateSetting")).build();
                    getUpdateSettingMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<SmartContractOuterClass.UpdateEnergyLimitContract, GrpcAPI.TransactionExtention> getUpdateEnergyLimitMethod() {
        MethodDescriptor<SmartContractOuterClass.UpdateEnergyLimitContract, GrpcAPI.TransactionExtention> methodDescriptor = getUpdateEnergyLimitMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getUpdateEnergyLimitMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "UpdateEnergyLimit")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(SmartContractOuterClass.UpdateEnergyLimitContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("UpdateEnergyLimit")).build();
                    getUpdateEnergyLimitMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<WitnessContract.VoteWitnessContract, GrpcAPI.TransactionExtention> getVoteWitnessAccount2Method() {
        MethodDescriptor<WitnessContract.VoteWitnessContract, GrpcAPI.TransactionExtention> methodDescriptor = getVoteWitnessAccount2Method;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getVoteWitnessAccount2Method;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "VoteWitnessAccount2")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(WitnessContract.VoteWitnessContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("VoteWitnessAccount2")).build();
                    getVoteWitnessAccount2Method = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<AssetIssueContractOuterClass.AssetIssueContract, Protocol.Transaction> getCreateAssetIssueMethod() {
        MethodDescriptor<AssetIssueContractOuterClass.AssetIssueContract, Protocol.Transaction> methodDescriptor = getCreateAssetIssueMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getCreateAssetIssueMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "CreateAssetIssue")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(AssetIssueContractOuterClass.AssetIssueContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.Transaction.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("CreateAssetIssue")).build();
                    getCreateAssetIssueMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<AssetIssueContractOuterClass.AssetIssueContract, GrpcAPI.TransactionExtention> getCreateAssetIssue2Method() {
        MethodDescriptor<AssetIssueContractOuterClass.AssetIssueContract, GrpcAPI.TransactionExtention> methodDescriptor = getCreateAssetIssue2Method;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getCreateAssetIssue2Method;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "CreateAssetIssue2")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(AssetIssueContractOuterClass.AssetIssueContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("CreateAssetIssue2")).build();
                    getCreateAssetIssue2Method = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<WitnessContract.WitnessUpdateContract, Protocol.Transaction> getUpdateWitnessMethod() {
        MethodDescriptor<WitnessContract.WitnessUpdateContract, Protocol.Transaction> methodDescriptor = getUpdateWitnessMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getUpdateWitnessMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "UpdateWitness")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(WitnessContract.WitnessUpdateContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.Transaction.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("UpdateWitness")).build();
                    getUpdateWitnessMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<WitnessContract.WitnessUpdateContract, GrpcAPI.TransactionExtention> getUpdateWitness2Method() {
        MethodDescriptor<WitnessContract.WitnessUpdateContract, GrpcAPI.TransactionExtention> methodDescriptor = getUpdateWitness2Method;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getUpdateWitness2Method;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "UpdateWitness2")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(WitnessContract.WitnessUpdateContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("UpdateWitness2")).build();
                    getUpdateWitness2Method = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<AccountContract.AccountCreateContract, Protocol.Transaction> getCreateAccountMethod() {
        MethodDescriptor<AccountContract.AccountCreateContract, Protocol.Transaction> methodDescriptor = getCreateAccountMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getCreateAccountMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "CreateAccount")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(AccountContract.AccountCreateContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.Transaction.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("CreateAccount")).build();
                    getCreateAccountMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<AccountContract.AccountCreateContract, GrpcAPI.TransactionExtention> getCreateAccount2Method() {
        MethodDescriptor<AccountContract.AccountCreateContract, GrpcAPI.TransactionExtention> methodDescriptor = getCreateAccount2Method;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getCreateAccount2Method;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "CreateAccount2")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(AccountContract.AccountCreateContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("CreateAccount2")).build();
                    getCreateAccount2Method = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<WitnessContract.WitnessCreateContract, Protocol.Transaction> getCreateWitnessMethod() {
        MethodDescriptor<WitnessContract.WitnessCreateContract, Protocol.Transaction> methodDescriptor = getCreateWitnessMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getCreateWitnessMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "CreateWitness")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(WitnessContract.WitnessCreateContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.Transaction.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("CreateWitness")).build();
                    getCreateWitnessMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<WitnessContract.WitnessCreateContract, GrpcAPI.TransactionExtention> getCreateWitness2Method() {
        MethodDescriptor<WitnessContract.WitnessCreateContract, GrpcAPI.TransactionExtention> methodDescriptor = getCreateWitness2Method;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getCreateWitness2Method;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "CreateWitness2")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(WitnessContract.WitnessCreateContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("CreateWitness2")).build();
                    getCreateWitness2Method = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<AssetIssueContractOuterClass.TransferAssetContract, Protocol.Transaction> getTransferAssetMethod() {
        MethodDescriptor<AssetIssueContractOuterClass.TransferAssetContract, Protocol.Transaction> methodDescriptor = getTransferAssetMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getTransferAssetMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "TransferAsset")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(AssetIssueContractOuterClass.TransferAssetContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.Transaction.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("TransferAsset")).build();
                    getTransferAssetMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<AssetIssueContractOuterClass.TransferAssetContract, GrpcAPI.TransactionExtention> getTransferAsset2Method() {
        MethodDescriptor<AssetIssueContractOuterClass.TransferAssetContract, GrpcAPI.TransactionExtention> methodDescriptor = getTransferAsset2Method;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getTransferAsset2Method;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "TransferAsset2")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(AssetIssueContractOuterClass.TransferAssetContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("TransferAsset2")).build();
                    getTransferAsset2Method = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<AssetIssueContractOuterClass.ParticipateAssetIssueContract, Protocol.Transaction> getParticipateAssetIssueMethod() {
        MethodDescriptor<AssetIssueContractOuterClass.ParticipateAssetIssueContract, Protocol.Transaction> methodDescriptor = getParticipateAssetIssueMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getParticipateAssetIssueMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "ParticipateAssetIssue")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(AssetIssueContractOuterClass.ParticipateAssetIssueContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.Transaction.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("ParticipateAssetIssue")).build();
                    getParticipateAssetIssueMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<AssetIssueContractOuterClass.ParticipateAssetIssueContract, GrpcAPI.TransactionExtention> getParticipateAssetIssue2Method() {
        MethodDescriptor<AssetIssueContractOuterClass.ParticipateAssetIssueContract, GrpcAPI.TransactionExtention> methodDescriptor = getParticipateAssetIssue2Method;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getParticipateAssetIssue2Method;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "ParticipateAssetIssue2")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(AssetIssueContractOuterClass.ParticipateAssetIssueContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("ParticipateAssetIssue2")).build();
                    getParticipateAssetIssue2Method = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<BalanceContract.FreezeBalanceContract, Protocol.Transaction> getFreezeBalanceMethod() {
        MethodDescriptor<BalanceContract.FreezeBalanceContract, Protocol.Transaction> methodDescriptor = getFreezeBalanceMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getFreezeBalanceMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "FreezeBalance")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(BalanceContract.FreezeBalanceContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.Transaction.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("FreezeBalance")).build();
                    getFreezeBalanceMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<BalanceContract.FreezeBalanceContract, GrpcAPI.TransactionExtention> getFreezeBalance2Method() {
        MethodDescriptor<BalanceContract.FreezeBalanceContract, GrpcAPI.TransactionExtention> methodDescriptor = getFreezeBalance2Method;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getFreezeBalance2Method;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "FreezeBalance2")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(BalanceContract.FreezeBalanceContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("FreezeBalance2")).build();
                    getFreezeBalance2Method = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<BalanceContract.FreezeBalanceV2Contract, GrpcAPI.TransactionExtention> getFreezeBalanceV2Method() {
        MethodDescriptor<BalanceContract.FreezeBalanceV2Contract, GrpcAPI.TransactionExtention> methodDescriptor = getFreezeBalanceV2Method;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getFreezeBalanceV2Method;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "FreezeBalanceV2")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(BalanceContract.FreezeBalanceV2Contract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("FreezeBalanceV2")).build();
                    getFreezeBalanceV2Method = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<BalanceContract.UnfreezeBalanceContract, Protocol.Transaction> getUnfreezeBalanceMethod() {
        MethodDescriptor<BalanceContract.UnfreezeBalanceContract, Protocol.Transaction> methodDescriptor = getUnfreezeBalanceMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getUnfreezeBalanceMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "UnfreezeBalance")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(BalanceContract.UnfreezeBalanceContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.Transaction.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("UnfreezeBalance")).build();
                    getUnfreezeBalanceMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<BalanceContract.UnfreezeBalanceContract, GrpcAPI.TransactionExtention> getUnfreezeBalance2Method() {
        MethodDescriptor<BalanceContract.UnfreezeBalanceContract, GrpcAPI.TransactionExtention> methodDescriptor = getUnfreezeBalance2Method;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getUnfreezeBalance2Method;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "UnfreezeBalance2")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(BalanceContract.UnfreezeBalanceContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("UnfreezeBalance2")).build();
                    getUnfreezeBalance2Method = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<BalanceContract.UnfreezeBalanceV2Contract, GrpcAPI.TransactionExtention> getUnfreezeBalanceV2Method() {
        MethodDescriptor<BalanceContract.UnfreezeBalanceV2Contract, GrpcAPI.TransactionExtention> methodDescriptor = getUnfreezeBalanceV2Method;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getUnfreezeBalanceV2Method;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "UnfreezeBalanceV2")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(BalanceContract.UnfreezeBalanceV2Contract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("UnfreezeBalanceV2")).build();
                    getUnfreezeBalanceV2Method = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<AssetIssueContractOuterClass.UnfreezeAssetContract, Protocol.Transaction> getUnfreezeAssetMethod() {
        MethodDescriptor<AssetIssueContractOuterClass.UnfreezeAssetContract, Protocol.Transaction> methodDescriptor = getUnfreezeAssetMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getUnfreezeAssetMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "UnfreezeAsset")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(AssetIssueContractOuterClass.UnfreezeAssetContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.Transaction.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("UnfreezeAsset")).build();
                    getUnfreezeAssetMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<AssetIssueContractOuterClass.UnfreezeAssetContract, GrpcAPI.TransactionExtention> getUnfreezeAsset2Method() {
        MethodDescriptor<AssetIssueContractOuterClass.UnfreezeAssetContract, GrpcAPI.TransactionExtention> methodDescriptor = getUnfreezeAsset2Method;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getUnfreezeAsset2Method;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "UnfreezeAsset2")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(AssetIssueContractOuterClass.UnfreezeAssetContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("UnfreezeAsset2")).build();
                    getUnfreezeAsset2Method = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<BalanceContract.WithdrawBalanceContract, Protocol.Transaction> getWithdrawBalanceMethod() {
        MethodDescriptor<BalanceContract.WithdrawBalanceContract, Protocol.Transaction> methodDescriptor = getWithdrawBalanceMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getWithdrawBalanceMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "WithdrawBalance")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(BalanceContract.WithdrawBalanceContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.Transaction.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("WithdrawBalance")).build();
                    getWithdrawBalanceMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<BalanceContract.WithdrawBalanceContract, GrpcAPI.TransactionExtention> getWithdrawBalance2Method() {
        MethodDescriptor<BalanceContract.WithdrawBalanceContract, GrpcAPI.TransactionExtention> methodDescriptor = getWithdrawBalance2Method;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getWithdrawBalance2Method;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "WithdrawBalance2")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(BalanceContract.WithdrawBalanceContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("WithdrawBalance2")).build();
                    getWithdrawBalance2Method = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<BalanceContract.WithdrawExpireUnfreezeContract, GrpcAPI.TransactionExtention> getWithdrawExpireUnfreezeMethod() {
        MethodDescriptor<BalanceContract.WithdrawExpireUnfreezeContract, GrpcAPI.TransactionExtention> methodDescriptor = getWithdrawExpireUnfreezeMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getWithdrawExpireUnfreezeMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "WithdrawExpireUnfreeze")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(BalanceContract.WithdrawExpireUnfreezeContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("WithdrawExpireUnfreeze")).build();
                    getWithdrawExpireUnfreezeMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<BalanceContract.DelegateResourceContract, GrpcAPI.TransactionExtention> getDelegateResourceMethod() {
        MethodDescriptor<BalanceContract.DelegateResourceContract, GrpcAPI.TransactionExtention> methodDescriptor = getDelegateResourceMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getDelegateResourceMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "DelegateResource")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(BalanceContract.DelegateResourceContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("DelegateResource")).build();
                    getDelegateResourceMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<BalanceContract.UnDelegateResourceContract, GrpcAPI.TransactionExtention> getUnDelegateResourceMethod() {
        MethodDescriptor<BalanceContract.UnDelegateResourceContract, GrpcAPI.TransactionExtention> methodDescriptor = getUnDelegateResourceMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getUnDelegateResourceMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "UnDelegateResource")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(BalanceContract.UnDelegateResourceContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("UnDelegateResource")).build();
                    getUnDelegateResourceMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<BalanceContract.CancelAllUnfreezeV2Contract, GrpcAPI.TransactionExtention> getCancelAllUnfreezeV2Method() {
        MethodDescriptor<BalanceContract.CancelAllUnfreezeV2Contract, GrpcAPI.TransactionExtention> methodDescriptor = getCancelAllUnfreezeV2Method;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getCancelAllUnfreezeV2Method;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "CancelAllUnfreezeV2")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(BalanceContract.CancelAllUnfreezeV2Contract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("CancelAllUnfreezeV2")).build();
                    getCancelAllUnfreezeV2Method = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<AssetIssueContractOuterClass.UpdateAssetContract, Protocol.Transaction> getUpdateAssetMethod() {
        MethodDescriptor<AssetIssueContractOuterClass.UpdateAssetContract, Protocol.Transaction> methodDescriptor = getUpdateAssetMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getUpdateAssetMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "UpdateAsset")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(AssetIssueContractOuterClass.UpdateAssetContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.Transaction.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("UpdateAsset")).build();
                    getUpdateAssetMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<AssetIssueContractOuterClass.UpdateAssetContract, GrpcAPI.TransactionExtention> getUpdateAsset2Method() {
        MethodDescriptor<AssetIssueContractOuterClass.UpdateAssetContract, GrpcAPI.TransactionExtention> methodDescriptor = getUpdateAsset2Method;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getUpdateAsset2Method;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "UpdateAsset2")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(AssetIssueContractOuterClass.UpdateAssetContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("UpdateAsset2")).build();
                    getUpdateAsset2Method = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<ProposalContract.ProposalCreateContract, GrpcAPI.TransactionExtention> getProposalCreateMethod() {
        MethodDescriptor<ProposalContract.ProposalCreateContract, GrpcAPI.TransactionExtention> methodDescriptor = getProposalCreateMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getProposalCreateMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "ProposalCreate")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(ProposalContract.ProposalCreateContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("ProposalCreate")).build();
                    getProposalCreateMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<ProposalContract.ProposalApproveContract, GrpcAPI.TransactionExtention> getProposalApproveMethod() {
        MethodDescriptor<ProposalContract.ProposalApproveContract, GrpcAPI.TransactionExtention> methodDescriptor = getProposalApproveMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getProposalApproveMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "ProposalApprove")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(ProposalContract.ProposalApproveContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("ProposalApprove")).build();
                    getProposalApproveMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<ProposalContract.ProposalDeleteContract, GrpcAPI.TransactionExtention> getProposalDeleteMethod() {
        MethodDescriptor<ProposalContract.ProposalDeleteContract, GrpcAPI.TransactionExtention> methodDescriptor = getProposalDeleteMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getProposalDeleteMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "ProposalDelete")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(ProposalContract.ProposalDeleteContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("ProposalDelete")).build();
                    getProposalDeleteMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<StorageContract.BuyStorageContract, GrpcAPI.TransactionExtention> getBuyStorageMethod() {
        MethodDescriptor<StorageContract.BuyStorageContract, GrpcAPI.TransactionExtention> methodDescriptor = getBuyStorageMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getBuyStorageMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "BuyStorage")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(StorageContract.BuyStorageContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("BuyStorage")).build();
                    getBuyStorageMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<StorageContract.BuyStorageBytesContract, GrpcAPI.TransactionExtention> getBuyStorageBytesMethod() {
        MethodDescriptor<StorageContract.BuyStorageBytesContract, GrpcAPI.TransactionExtention> methodDescriptor = getBuyStorageBytesMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getBuyStorageBytesMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "BuyStorageBytes")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(StorageContract.BuyStorageBytesContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("BuyStorageBytes")).build();
                    getBuyStorageBytesMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<StorageContract.SellStorageContract, GrpcAPI.TransactionExtention> getSellStorageMethod() {
        MethodDescriptor<StorageContract.SellStorageContract, GrpcAPI.TransactionExtention> methodDescriptor = getSellStorageMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getSellStorageMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "SellStorage")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(StorageContract.SellStorageContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("SellStorage")).build();
                    getSellStorageMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<ExchangeContract.ExchangeCreateContract, GrpcAPI.TransactionExtention> getExchangeCreateMethod() {
        MethodDescriptor<ExchangeContract.ExchangeCreateContract, GrpcAPI.TransactionExtention> methodDescriptor = getExchangeCreateMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getExchangeCreateMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "ExchangeCreate")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(ExchangeContract.ExchangeCreateContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("ExchangeCreate")).build();
                    getExchangeCreateMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<ExchangeContract.ExchangeInjectContract, GrpcAPI.TransactionExtention> getExchangeInjectMethod() {
        MethodDescriptor<ExchangeContract.ExchangeInjectContract, GrpcAPI.TransactionExtention> methodDescriptor = getExchangeInjectMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getExchangeInjectMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "ExchangeInject")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(ExchangeContract.ExchangeInjectContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("ExchangeInject")).build();
                    getExchangeInjectMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<ExchangeContract.ExchangeWithdrawContract, GrpcAPI.TransactionExtention> getExchangeWithdrawMethod() {
        MethodDescriptor<ExchangeContract.ExchangeWithdrawContract, GrpcAPI.TransactionExtention> methodDescriptor = getExchangeWithdrawMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getExchangeWithdrawMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "ExchangeWithdraw")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(ExchangeContract.ExchangeWithdrawContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("ExchangeWithdraw")).build();
                    getExchangeWithdrawMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<ExchangeContract.ExchangeTransactionContract, GrpcAPI.TransactionExtention> getExchangeTransactionMethod() {
        MethodDescriptor<ExchangeContract.ExchangeTransactionContract, GrpcAPI.TransactionExtention> methodDescriptor = getExchangeTransactionMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getExchangeTransactionMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "ExchangeTransaction")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(ExchangeContract.ExchangeTransactionContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("ExchangeTransaction")).build();
                    getExchangeTransactionMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<MarketContract.MarketSellAssetContract, GrpcAPI.TransactionExtention> getMarketSellAssetMethod() {
        MethodDescriptor<MarketContract.MarketSellAssetContract, GrpcAPI.TransactionExtention> methodDescriptor = getMarketSellAssetMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getMarketSellAssetMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "MarketSellAsset")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(MarketContract.MarketSellAssetContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("MarketSellAsset")).build();
                    getMarketSellAssetMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<MarketContract.MarketCancelOrderContract, GrpcAPI.TransactionExtention> getMarketCancelOrderMethod() {
        MethodDescriptor<MarketContract.MarketCancelOrderContract, GrpcAPI.TransactionExtention> methodDescriptor = getMarketCancelOrderMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getMarketCancelOrderMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "MarketCancelOrder")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(MarketContract.MarketCancelOrderContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("MarketCancelOrder")).build();
                    getMarketCancelOrderMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BytesMessage, Protocol.MarketOrder> getGetMarketOrderByIdMethod() {
        MethodDescriptor<GrpcAPI.BytesMessage, Protocol.MarketOrder> methodDescriptor = getGetMarketOrderByIdMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetMarketOrderByIdMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetMarketOrderById")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.MarketOrder.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetMarketOrderById")).build();
                    getGetMarketOrderByIdMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BytesMessage, Protocol.MarketOrderList> getGetMarketOrderByAccountMethod() {
        MethodDescriptor<GrpcAPI.BytesMessage, Protocol.MarketOrderList> methodDescriptor = getGetMarketOrderByAccountMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetMarketOrderByAccountMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetMarketOrderByAccount")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.MarketOrderList.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetMarketOrderByAccount")).build();
                    getGetMarketOrderByAccountMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<Protocol.MarketOrderPair, Protocol.MarketPriceList> getGetMarketPriceByPairMethod() {
        MethodDescriptor<Protocol.MarketOrderPair, Protocol.MarketPriceList> methodDescriptor = getGetMarketPriceByPairMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetMarketPriceByPairMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetMarketPriceByPair")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(Protocol.MarketOrderPair.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.MarketPriceList.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetMarketPriceByPair")).build();
                    getGetMarketPriceByPairMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<Protocol.MarketOrderPair, Protocol.MarketOrderList> getGetMarketOrderListByPairMethod() {
        MethodDescriptor<Protocol.MarketOrderPair, Protocol.MarketOrderList> methodDescriptor = getGetMarketOrderListByPairMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetMarketOrderListByPairMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetMarketOrderListByPair")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(Protocol.MarketOrderPair.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.MarketOrderList.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetMarketOrderListByPair")).build();
                    getGetMarketOrderListByPairMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.EmptyMessage, Protocol.MarketOrderPairList> getGetMarketPairListMethod() {
        MethodDescriptor<GrpcAPI.EmptyMessage, Protocol.MarketOrderPairList> methodDescriptor = getGetMarketPairListMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetMarketPairListMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetMarketPairList")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.EmptyMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.MarketOrderPairList.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetMarketPairList")).build();
                    getGetMarketPairListMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.NodeList> getListNodesMethod() {
        MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.NodeList> methodDescriptor = getListNodesMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getListNodesMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "ListNodes")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.EmptyMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.NodeList.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("ListNodes")).build();
                    getListNodesMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<Protocol.Account, GrpcAPI.AssetIssueList> getGetAssetIssueByAccountMethod() {
        MethodDescriptor<Protocol.Account, GrpcAPI.AssetIssueList> methodDescriptor = getGetAssetIssueByAccountMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetAssetIssueByAccountMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetAssetIssueByAccount")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(Protocol.Account.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.AssetIssueList.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetAssetIssueByAccount")).build();
                    getGetAssetIssueByAccountMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<Protocol.Account, GrpcAPI.AccountNetMessage> getGetAccountNetMethod() {
        MethodDescriptor<Protocol.Account, GrpcAPI.AccountNetMessage> methodDescriptor = getGetAccountNetMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetAccountNetMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetAccountNet")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(Protocol.Account.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.AccountNetMessage.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetAccountNet")).build();
                    getGetAccountNetMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<Protocol.Account, GrpcAPI.AccountResourceMessage> getGetAccountResourceMethod() {
        MethodDescriptor<Protocol.Account, GrpcAPI.AccountResourceMessage> methodDescriptor = getGetAccountResourceMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetAccountResourceMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetAccountResource")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(Protocol.Account.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.AccountResourceMessage.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetAccountResource")).build();
                    getGetAccountResourceMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BytesMessage, AssetIssueContractOuterClass.AssetIssueContract> getGetAssetIssueByNameMethod() {
        MethodDescriptor<GrpcAPI.BytesMessage, AssetIssueContractOuterClass.AssetIssueContract> methodDescriptor = getGetAssetIssueByNameMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetAssetIssueByNameMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetAssetIssueByName")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(AssetIssueContractOuterClass.AssetIssueContract.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetAssetIssueByName")).build();
                    getGetAssetIssueByNameMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.AssetIssueList> getGetAssetIssueListByNameMethod() {
        MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.AssetIssueList> methodDescriptor = getGetAssetIssueListByNameMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetAssetIssueListByNameMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetAssetIssueListByName")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.AssetIssueList.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetAssetIssueListByName")).build();
                    getGetAssetIssueListByNameMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BytesMessage, AssetIssueContractOuterClass.AssetIssueContract> getGetAssetIssueByIdMethod() {
        MethodDescriptor<GrpcAPI.BytesMessage, AssetIssueContractOuterClass.AssetIssueContract> methodDescriptor = getGetAssetIssueByIdMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetAssetIssueByIdMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetAssetIssueById")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(AssetIssueContractOuterClass.AssetIssueContract.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetAssetIssueById")).build();
                    getGetAssetIssueByIdMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.EmptyMessage, Protocol.Block> getGetNowBlockMethod() {
        MethodDescriptor<GrpcAPI.EmptyMessage, Protocol.Block> methodDescriptor = getGetNowBlockMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetNowBlockMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetNowBlock")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.EmptyMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.Block.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetNowBlock")).build();
                    getGetNowBlockMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.BlockExtention> getGetNowBlock2Method() {
        MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.BlockExtention> methodDescriptor = getGetNowBlock2Method;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetNowBlock2Method;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetNowBlock2")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.EmptyMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.BlockExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetNowBlock2")).build();
                    getGetNowBlock2Method = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.NumberMessage, Protocol.Block> getGetBlockByNumMethod() {
        MethodDescriptor<GrpcAPI.NumberMessage, Protocol.Block> methodDescriptor = getGetBlockByNumMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetBlockByNumMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetBlockByNum")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.NumberMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.Block.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetBlockByNum")).build();
                    getGetBlockByNumMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.NumberMessage, GrpcAPI.BlockExtention> getGetBlockByNum2Method() {
        MethodDescriptor<GrpcAPI.NumberMessage, GrpcAPI.BlockExtention> methodDescriptor = getGetBlockByNum2Method;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetBlockByNum2Method;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetBlockByNum2")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.NumberMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.BlockExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetBlockByNum2")).build();
                    getGetBlockByNum2Method = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.NumberMessage, GrpcAPI.NumberMessage> getGetTransactionCountByBlockNumMethod() {
        MethodDescriptor<GrpcAPI.NumberMessage, GrpcAPI.NumberMessage> methodDescriptor = getGetTransactionCountByBlockNumMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetTransactionCountByBlockNumMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetTransactionCountByBlockNum")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.NumberMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.NumberMessage.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetTransactionCountByBlockNum")).build();
                    getGetTransactionCountByBlockNumMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BytesMessage, Protocol.Block> getGetBlockByIdMethod() {
        MethodDescriptor<GrpcAPI.BytesMessage, Protocol.Block> methodDescriptor = getGetBlockByIdMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetBlockByIdMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetBlockById")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.Block.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetBlockById")).build();
                    getGetBlockByIdMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BlockLimit, GrpcAPI.BlockList> getGetBlockByLimitNextMethod() {
        MethodDescriptor<GrpcAPI.BlockLimit, GrpcAPI.BlockList> methodDescriptor = getGetBlockByLimitNextMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetBlockByLimitNextMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetBlockByLimitNext")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BlockLimit.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.BlockList.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetBlockByLimitNext")).build();
                    getGetBlockByLimitNextMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BlockLimit, GrpcAPI.BlockListExtention> getGetBlockByLimitNext2Method() {
        MethodDescriptor<GrpcAPI.BlockLimit, GrpcAPI.BlockListExtention> methodDescriptor = getGetBlockByLimitNext2Method;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetBlockByLimitNext2Method;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetBlockByLimitNext2")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BlockLimit.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.BlockListExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetBlockByLimitNext2")).build();
                    getGetBlockByLimitNext2Method = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.NumberMessage, GrpcAPI.BlockList> getGetBlockByLatestNumMethod() {
        MethodDescriptor<GrpcAPI.NumberMessage, GrpcAPI.BlockList> methodDescriptor = getGetBlockByLatestNumMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetBlockByLatestNumMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetBlockByLatestNum")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.NumberMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.BlockList.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetBlockByLatestNum")).build();
                    getGetBlockByLatestNumMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.NumberMessage, GrpcAPI.BlockListExtention> getGetBlockByLatestNum2Method() {
        MethodDescriptor<GrpcAPI.NumberMessage, GrpcAPI.BlockListExtention> methodDescriptor = getGetBlockByLatestNum2Method;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetBlockByLatestNum2Method;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetBlockByLatestNum2")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.NumberMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.BlockListExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetBlockByLatestNum2")).build();
                    getGetBlockByLatestNum2Method = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BytesMessage, Protocol.Transaction> getGetTransactionByIdMethod() {
        MethodDescriptor<GrpcAPI.BytesMessage, Protocol.Transaction> methodDescriptor = getGetTransactionByIdMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetTransactionByIdMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetTransactionById")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.Transaction.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetTransactionById")).build();
                    getGetTransactionByIdMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<SmartContractOuterClass.CreateSmartContract, GrpcAPI.TransactionExtention> getDeployContractMethod() {
        MethodDescriptor<SmartContractOuterClass.CreateSmartContract, GrpcAPI.TransactionExtention> methodDescriptor = getDeployContractMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getDeployContractMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "DeployContract")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(SmartContractOuterClass.CreateSmartContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("DeployContract")).build();
                    getDeployContractMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BytesMessage, SmartContractOuterClass.SmartContract> getGetContractMethod() {
        MethodDescriptor<GrpcAPI.BytesMessage, SmartContractOuterClass.SmartContract> methodDescriptor = getGetContractMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetContractMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetContract")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(SmartContractOuterClass.SmartContract.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetContract")).build();
                    getGetContractMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BytesMessage, SmartContractOuterClass.SmartContractDataWrapper> getGetContractInfoMethod() {
        MethodDescriptor<GrpcAPI.BytesMessage, SmartContractOuterClass.SmartContractDataWrapper> methodDescriptor = getGetContractInfoMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetContractInfoMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetContractInfo")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(SmartContractOuterClass.SmartContractDataWrapper.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetContractInfo")).build();
                    getGetContractInfoMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<SmartContractOuterClass.TriggerSmartContract, GrpcAPI.TransactionExtention> getTriggerContractMethod() {
        MethodDescriptor<SmartContractOuterClass.TriggerSmartContract, GrpcAPI.TransactionExtention> methodDescriptor = getTriggerContractMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getTriggerContractMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "TriggerContract")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(SmartContractOuterClass.TriggerSmartContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("TriggerContract")).build();
                    getTriggerContractMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<SmartContractOuterClass.TriggerSmartContract, GrpcAPI.TransactionExtention> getTriggerConstantContractMethod() {
        MethodDescriptor<SmartContractOuterClass.TriggerSmartContract, GrpcAPI.TransactionExtention> methodDescriptor = getTriggerConstantContractMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getTriggerConstantContractMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "TriggerConstantContract")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(SmartContractOuterClass.TriggerSmartContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("TriggerConstantContract")).build();
                    getTriggerConstantContractMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<SmartContractOuterClass.TriggerSmartContract, GrpcAPI.EstimateEnergyMessage> getEstimateEnergyMethod() {
        MethodDescriptor<SmartContractOuterClass.TriggerSmartContract, GrpcAPI.EstimateEnergyMessage> methodDescriptor = getEstimateEnergyMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getEstimateEnergyMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "EstimateEnergy")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(SmartContractOuterClass.TriggerSmartContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.EstimateEnergyMessage.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("EstimateEnergy")).build();
                    getEstimateEnergyMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<SmartContractOuterClass.ClearABIContract, GrpcAPI.TransactionExtention> getClearContractABIMethod() {
        MethodDescriptor<SmartContractOuterClass.ClearABIContract, GrpcAPI.TransactionExtention> methodDescriptor = getClearContractABIMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getClearContractABIMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "ClearContractABI")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(SmartContractOuterClass.ClearABIContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("ClearContractABI")).build();
                    getClearContractABIMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.WitnessList> getListWitnessesMethod() {
        MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.WitnessList> methodDescriptor = getListWitnessesMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getListWitnessesMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "ListWitnesses")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.EmptyMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.WitnessList.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("ListWitnesses")).build();
                    getListWitnessesMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.DelegatedResourceMessage, GrpcAPI.DelegatedResourceList> getGetDelegatedResourceMethod() {
        MethodDescriptor<GrpcAPI.DelegatedResourceMessage, GrpcAPI.DelegatedResourceList> methodDescriptor = getGetDelegatedResourceMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetDelegatedResourceMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetDelegatedResource")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.DelegatedResourceMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.DelegatedResourceList.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetDelegatedResource")).build();
                    getGetDelegatedResourceMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.DelegatedResourceMessage, GrpcAPI.DelegatedResourceList> getGetDelegatedResourceV2Method() {
        MethodDescriptor<GrpcAPI.DelegatedResourceMessage, GrpcAPI.DelegatedResourceList> methodDescriptor = getGetDelegatedResourceV2Method;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetDelegatedResourceV2Method;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetDelegatedResourceV2")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.DelegatedResourceMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.DelegatedResourceList.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetDelegatedResourceV2")).build();
                    getGetDelegatedResourceV2Method = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BytesMessage, Protocol.DelegatedResourceAccountIndex> getGetDelegatedResourceAccountIndexMethod() {
        MethodDescriptor<GrpcAPI.BytesMessage, Protocol.DelegatedResourceAccountIndex> methodDescriptor = getGetDelegatedResourceAccountIndexMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetDelegatedResourceAccountIndexMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetDelegatedResourceAccountIndex")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.DelegatedResourceAccountIndex.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetDelegatedResourceAccountIndex")).build();
                    getGetDelegatedResourceAccountIndexMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BytesMessage, Protocol.DelegatedResourceAccountIndex> getGetDelegatedResourceAccountIndexV2Method() {
        MethodDescriptor<GrpcAPI.BytesMessage, Protocol.DelegatedResourceAccountIndex> methodDescriptor = getGetDelegatedResourceAccountIndexV2Method;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetDelegatedResourceAccountIndexV2Method;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetDelegatedResourceAccountIndexV2")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.DelegatedResourceAccountIndex.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetDelegatedResourceAccountIndexV2")).build();
                    getGetDelegatedResourceAccountIndexV2Method = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.CanDelegatedMaxSizeRequestMessage, GrpcAPI.CanDelegatedMaxSizeResponseMessage> getGetCanDelegatedMaxSizeMethod() {
        MethodDescriptor<GrpcAPI.CanDelegatedMaxSizeRequestMessage, GrpcAPI.CanDelegatedMaxSizeResponseMessage> methodDescriptor = getGetCanDelegatedMaxSizeMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetCanDelegatedMaxSizeMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetCanDelegatedMaxSize")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.CanDelegatedMaxSizeRequestMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.CanDelegatedMaxSizeResponseMessage.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetCanDelegatedMaxSize")).build();
                    getGetCanDelegatedMaxSizeMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.GetAvailableUnfreezeCountRequestMessage, GrpcAPI.GetAvailableUnfreezeCountResponseMessage> getGetAvailableUnfreezeCountMethod() {
        MethodDescriptor<GrpcAPI.GetAvailableUnfreezeCountRequestMessage, GrpcAPI.GetAvailableUnfreezeCountResponseMessage> methodDescriptor = getGetAvailableUnfreezeCountMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetAvailableUnfreezeCountMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetAvailableUnfreezeCount")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.GetAvailableUnfreezeCountRequestMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.GetAvailableUnfreezeCountResponseMessage.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetAvailableUnfreezeCount")).build();
                    getGetAvailableUnfreezeCountMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.CanWithdrawUnfreezeAmountRequestMessage, GrpcAPI.CanWithdrawUnfreezeAmountResponseMessage> getGetCanWithdrawUnfreezeAmountMethod() {
        MethodDescriptor<GrpcAPI.CanWithdrawUnfreezeAmountRequestMessage, GrpcAPI.CanWithdrawUnfreezeAmountResponseMessage> methodDescriptor = getGetCanWithdrawUnfreezeAmountMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetCanWithdrawUnfreezeAmountMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetCanWithdrawUnfreezeAmount")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.CanWithdrawUnfreezeAmountRequestMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.CanWithdrawUnfreezeAmountResponseMessage.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetCanWithdrawUnfreezeAmount")).build();
                    getGetCanWithdrawUnfreezeAmountMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.ProposalList> getListProposalsMethod() {
        MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.ProposalList> methodDescriptor = getListProposalsMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getListProposalsMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "ListProposals")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.EmptyMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.ProposalList.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("ListProposals")).build();
                    getListProposalsMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.PaginatedMessage, GrpcAPI.ProposalList> getGetPaginatedProposalListMethod() {
        MethodDescriptor<GrpcAPI.PaginatedMessage, GrpcAPI.ProposalList> methodDescriptor = getGetPaginatedProposalListMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetPaginatedProposalListMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetPaginatedProposalList")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.PaginatedMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.ProposalList.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetPaginatedProposalList")).build();
                    getGetPaginatedProposalListMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BytesMessage, Protocol.Proposal> getGetProposalByIdMethod() {
        MethodDescriptor<GrpcAPI.BytesMessage, Protocol.Proposal> methodDescriptor = getGetProposalByIdMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetProposalByIdMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetProposalById")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.Proposal.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetProposalById")).build();
                    getGetProposalByIdMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.ExchangeList> getListExchangesMethod() {
        MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.ExchangeList> methodDescriptor = getListExchangesMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getListExchangesMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "ListExchanges")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.EmptyMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.ExchangeList.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("ListExchanges")).build();
                    getListExchangesMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.PaginatedMessage, GrpcAPI.ExchangeList> getGetPaginatedExchangeListMethod() {
        MethodDescriptor<GrpcAPI.PaginatedMessage, GrpcAPI.ExchangeList> methodDescriptor = getGetPaginatedExchangeListMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetPaginatedExchangeListMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetPaginatedExchangeList")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.PaginatedMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.ExchangeList.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetPaginatedExchangeList")).build();
                    getGetPaginatedExchangeListMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BytesMessage, Protocol.Exchange> getGetExchangeByIdMethod() {
        MethodDescriptor<GrpcAPI.BytesMessage, Protocol.Exchange> methodDescriptor = getGetExchangeByIdMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetExchangeByIdMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetExchangeById")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.Exchange.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetExchangeById")).build();
                    getGetExchangeByIdMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.EmptyMessage, Protocol.ChainParameters> getGetChainParametersMethod() {
        MethodDescriptor<GrpcAPI.EmptyMessage, Protocol.ChainParameters> methodDescriptor = getGetChainParametersMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetChainParametersMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetChainParameters")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.EmptyMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.ChainParameters.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetChainParameters")).build();
                    getGetChainParametersMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.AssetIssueList> getGetAssetIssueListMethod() {
        MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.AssetIssueList> methodDescriptor = getGetAssetIssueListMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetAssetIssueListMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetAssetIssueList")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.EmptyMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.AssetIssueList.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetAssetIssueList")).build();
                    getGetAssetIssueListMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.PaginatedMessage, GrpcAPI.AssetIssueList> getGetPaginatedAssetIssueListMethod() {
        MethodDescriptor<GrpcAPI.PaginatedMessage, GrpcAPI.AssetIssueList> methodDescriptor = getGetPaginatedAssetIssueListMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetPaginatedAssetIssueListMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetPaginatedAssetIssueList")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.PaginatedMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.AssetIssueList.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetPaginatedAssetIssueList")).build();
                    getGetPaginatedAssetIssueListMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.NumberMessage> getTotalTransactionMethod() {
        MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.NumberMessage> methodDescriptor = getTotalTransactionMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getTotalTransactionMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "TotalTransaction")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.EmptyMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.NumberMessage.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("TotalTransaction")).build();
                    getTotalTransactionMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.NumberMessage> getGetNextMaintenanceTimeMethod() {
        MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.NumberMessage> methodDescriptor = getGetNextMaintenanceTimeMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetNextMaintenanceTimeMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetNextMaintenanceTime")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.EmptyMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.NumberMessage.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetNextMaintenanceTime")).build();
                    getGetNextMaintenanceTimeMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BytesMessage, Protocol.TransactionInfo> getGetTransactionInfoByIdMethod() {
        MethodDescriptor<GrpcAPI.BytesMessage, Protocol.TransactionInfo> methodDescriptor = getGetTransactionInfoByIdMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetTransactionInfoByIdMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetTransactionInfoById")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.TransactionInfo.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetTransactionInfoById")).build();
                    getGetTransactionInfoByIdMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<AccountContract.AccountPermissionUpdateContract, GrpcAPI.TransactionExtention> getAccountPermissionUpdateMethod() {
        MethodDescriptor<AccountContract.AccountPermissionUpdateContract, GrpcAPI.TransactionExtention> methodDescriptor = getAccountPermissionUpdateMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getAccountPermissionUpdateMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "AccountPermissionUpdate")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(AccountContract.AccountPermissionUpdateContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("AccountPermissionUpdate")).build();
                    getAccountPermissionUpdateMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<Protocol.Transaction, GrpcAPI.TransactionSignWeight> getGetTransactionSignWeightMethod() {
        MethodDescriptor<Protocol.Transaction, GrpcAPI.TransactionSignWeight> methodDescriptor = getGetTransactionSignWeightMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetTransactionSignWeightMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetTransactionSignWeight")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(Protocol.Transaction.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionSignWeight.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetTransactionSignWeight")).build();
                    getGetTransactionSignWeightMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<Protocol.Transaction, GrpcAPI.TransactionApprovedList> getGetTransactionApprovedListMethod() {
        MethodDescriptor<Protocol.Transaction, GrpcAPI.TransactionApprovedList> methodDescriptor = getGetTransactionApprovedListMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetTransactionApprovedListMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetTransactionApprovedList")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(Protocol.Transaction.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionApprovedList.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetTransactionApprovedList")).build();
                    getGetTransactionApprovedListMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.EmptyMessage, Protocol.NodeInfo> getGetNodeInfoMethod() {
        MethodDescriptor<GrpcAPI.EmptyMessage, Protocol.NodeInfo> methodDescriptor = getGetNodeInfoMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetNodeInfoMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetNodeInfo")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.EmptyMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.NodeInfo.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetNodeInfo")).build();
                    getGetNodeInfoMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.NumberMessage> getGetRewardInfoMethod() {
        MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.NumberMessage> methodDescriptor = getGetRewardInfoMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetRewardInfoMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetRewardInfo")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.NumberMessage.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetRewardInfo")).build();
                    getGetRewardInfoMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.NumberMessage> getGetBrokerageInfoMethod() {
        MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.NumberMessage> methodDescriptor = getGetBrokerageInfoMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetBrokerageInfoMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetBrokerageInfo")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.NumberMessage.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetBrokerageInfo")).build();
                    getGetBrokerageInfoMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<StorageContract.UpdateBrokerageContract, GrpcAPI.TransactionExtention> getUpdateBrokerageMethod() {
        MethodDescriptor<StorageContract.UpdateBrokerageContract, GrpcAPI.TransactionExtention> methodDescriptor = getUpdateBrokerageMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getUpdateBrokerageMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "UpdateBrokerage")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(StorageContract.UpdateBrokerageContract.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("UpdateBrokerage")).build();
                    getUpdateBrokerageMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.PrivateParameters, GrpcAPI.TransactionExtention> getCreateShieldedTransactionMethod() {
        MethodDescriptor<GrpcAPI.PrivateParameters, GrpcAPI.TransactionExtention> methodDescriptor = getCreateShieldedTransactionMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getCreateShieldedTransactionMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "CreateShieldedTransaction")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.PrivateParameters.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("CreateShieldedTransaction")).build();
                    getCreateShieldedTransactionMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<ShieldContract.OutputPointInfo, ShieldContract.IncrementalMerkleVoucherInfo> getGetMerkleTreeVoucherInfoMethod() {
        MethodDescriptor<ShieldContract.OutputPointInfo, ShieldContract.IncrementalMerkleVoucherInfo> methodDescriptor = getGetMerkleTreeVoucherInfoMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetMerkleTreeVoucherInfoMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetMerkleTreeVoucherInfo")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(ShieldContract.OutputPointInfo.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(ShieldContract.IncrementalMerkleVoucherInfo.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetMerkleTreeVoucherInfo")).build();
                    getGetMerkleTreeVoucherInfoMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.IvkDecryptParameters, GrpcAPI.DecryptNotes> getScanNoteByIvkMethod() {
        MethodDescriptor<GrpcAPI.IvkDecryptParameters, GrpcAPI.DecryptNotes> methodDescriptor = getScanNoteByIvkMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getScanNoteByIvkMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "ScanNoteByIvk")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.IvkDecryptParameters.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.DecryptNotes.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("ScanNoteByIvk")).build();
                    getScanNoteByIvkMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.IvkDecryptAndMarkParameters, GrpcAPI.DecryptNotesMarked> getScanAndMarkNoteByIvkMethod() {
        MethodDescriptor<GrpcAPI.IvkDecryptAndMarkParameters, GrpcAPI.DecryptNotesMarked> methodDescriptor = getScanAndMarkNoteByIvkMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getScanAndMarkNoteByIvkMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "ScanAndMarkNoteByIvk")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.IvkDecryptAndMarkParameters.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.DecryptNotesMarked.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("ScanAndMarkNoteByIvk")).build();
                    getScanAndMarkNoteByIvkMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.OvkDecryptParameters, GrpcAPI.DecryptNotes> getScanNoteByOvkMethod() {
        MethodDescriptor<GrpcAPI.OvkDecryptParameters, GrpcAPI.DecryptNotes> methodDescriptor = getScanNoteByOvkMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getScanNoteByOvkMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "ScanNoteByOvk")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.OvkDecryptParameters.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.DecryptNotes.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("ScanNoteByOvk")).build();
                    getScanNoteByOvkMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.BytesMessage> getGetSpendingKeyMethod() {
        MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.BytesMessage> methodDescriptor = getGetSpendingKeyMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetSpendingKeyMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetSpendingKey")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.EmptyMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetSpendingKey")).build();
                    getGetSpendingKeyMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.ExpandedSpendingKeyMessage> getGetExpandedSpendingKeyMethod() {
        MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.ExpandedSpendingKeyMessage> methodDescriptor = getGetExpandedSpendingKeyMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetExpandedSpendingKeyMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetExpandedSpendingKey")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.ExpandedSpendingKeyMessage.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetExpandedSpendingKey")).build();
                    getGetExpandedSpendingKeyMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.BytesMessage> getGetAkFromAskMethod() {
        MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.BytesMessage> methodDescriptor = getGetAkFromAskMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetAkFromAskMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetAkFromAsk")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetAkFromAsk")).build();
                    getGetAkFromAskMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.BytesMessage> getGetNkFromNskMethod() {
        MethodDescriptor<GrpcAPI.BytesMessage, GrpcAPI.BytesMessage> methodDescriptor = getGetNkFromNskMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetNkFromNskMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetNkFromNsk")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetNkFromNsk")).build();
                    getGetNkFromNskMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.ViewingKeyMessage, GrpcAPI.IncomingViewingKeyMessage> getGetIncomingViewingKeyMethod() {
        MethodDescriptor<GrpcAPI.ViewingKeyMessage, GrpcAPI.IncomingViewingKeyMessage> methodDescriptor = getGetIncomingViewingKeyMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetIncomingViewingKeyMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetIncomingViewingKey")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.ViewingKeyMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.IncomingViewingKeyMessage.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetIncomingViewingKey")).build();
                    getGetIncomingViewingKeyMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.DiversifierMessage> getGetDiversifierMethod() {
        MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.DiversifierMessage> methodDescriptor = getGetDiversifierMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetDiversifierMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetDiversifier")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.EmptyMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.DiversifierMessage.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetDiversifier")).build();
                    getGetDiversifierMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.ShieldedAddressInfo> getGetNewShieldedAddressMethod() {
        MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.ShieldedAddressInfo> methodDescriptor = getGetNewShieldedAddressMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetNewShieldedAddressMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetNewShieldedAddress")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.EmptyMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.ShieldedAddressInfo.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetNewShieldedAddress")).build();
                    getGetNewShieldedAddressMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.IncomingViewingKeyDiversifierMessage, GrpcAPI.PaymentAddressMessage> getGetZenPaymentAddressMethod() {
        MethodDescriptor<GrpcAPI.IncomingViewingKeyDiversifierMessage, GrpcAPI.PaymentAddressMessage> methodDescriptor = getGetZenPaymentAddressMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetZenPaymentAddressMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetZenPaymentAddress")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.IncomingViewingKeyDiversifierMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.PaymentAddressMessage.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetZenPaymentAddress")).build();
                    getGetZenPaymentAddressMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.BytesMessage> getGetRcmMethod() {
        MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.BytesMessage> methodDescriptor = getGetRcmMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetRcmMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetRcm")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.EmptyMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetRcm")).build();
                    getGetRcmMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.NoteParameters, GrpcAPI.SpendResult> getIsSpendMethod() {
        MethodDescriptor<GrpcAPI.NoteParameters, GrpcAPI.SpendResult> methodDescriptor = getIsSpendMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getIsSpendMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "IsSpend")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.NoteParameters.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.SpendResult.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("IsSpend")).build();
                    getIsSpendMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.PrivateParametersWithoutAsk, GrpcAPI.TransactionExtention> getCreateShieldedTransactionWithoutSpendAuthSigMethod() {
        MethodDescriptor<GrpcAPI.PrivateParametersWithoutAsk, GrpcAPI.TransactionExtention> methodDescriptor = getCreateShieldedTransactionWithoutSpendAuthSigMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getCreateShieldedTransactionWithoutSpendAuthSigMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "CreateShieldedTransactionWithoutSpendAuthSig")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.PrivateParametersWithoutAsk.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("CreateShieldedTransactionWithoutSpendAuthSig")).build();
                    getCreateShieldedTransactionWithoutSpendAuthSigMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<Protocol.Transaction, GrpcAPI.BytesMessage> getGetShieldTransactionHashMethod() {
        MethodDescriptor<Protocol.Transaction, GrpcAPI.BytesMessage> methodDescriptor = getGetShieldTransactionHashMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetShieldTransactionHashMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetShieldTransactionHash")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(Protocol.Transaction.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetShieldTransactionHash")).build();
                    getGetShieldTransactionHashMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.SpendAuthSigParameters, GrpcAPI.BytesMessage> getCreateSpendAuthSigMethod() {
        MethodDescriptor<GrpcAPI.SpendAuthSigParameters, GrpcAPI.BytesMessage> methodDescriptor = getCreateSpendAuthSigMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getCreateSpendAuthSigMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "CreateSpendAuthSig")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.SpendAuthSigParameters.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("CreateSpendAuthSig")).build();
                    getCreateSpendAuthSigMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.NfParameters, GrpcAPI.BytesMessage> getCreateShieldNullifierMethod() {
        MethodDescriptor<GrpcAPI.NfParameters, GrpcAPI.BytesMessage> methodDescriptor = getCreateShieldNullifierMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getCreateShieldNullifierMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "CreateShieldNullifier")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.NfParameters.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("CreateShieldNullifier")).build();
                    getCreateShieldNullifierMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.PrivateShieldedTRC20Parameters, GrpcAPI.ShieldedTRC20Parameters> getCreateShieldedContractParametersMethod() {
        MethodDescriptor<GrpcAPI.PrivateShieldedTRC20Parameters, GrpcAPI.ShieldedTRC20Parameters> methodDescriptor = getCreateShieldedContractParametersMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getCreateShieldedContractParametersMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "CreateShieldedContractParameters")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.PrivateShieldedTRC20Parameters.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.ShieldedTRC20Parameters.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("CreateShieldedContractParameters")).build();
                    getCreateShieldedContractParametersMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.PrivateShieldedTRC20ParametersWithoutAsk, GrpcAPI.ShieldedTRC20Parameters> getCreateShieldedContractParametersWithoutAskMethod() {
        MethodDescriptor<GrpcAPI.PrivateShieldedTRC20ParametersWithoutAsk, GrpcAPI.ShieldedTRC20Parameters> methodDescriptor = getCreateShieldedContractParametersWithoutAskMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getCreateShieldedContractParametersWithoutAskMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "CreateShieldedContractParametersWithoutAsk")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.PrivateShieldedTRC20ParametersWithoutAsk.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.ShieldedTRC20Parameters.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("CreateShieldedContractParametersWithoutAsk")).build();
                    getCreateShieldedContractParametersWithoutAskMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.IvkDecryptTRC20Parameters, GrpcAPI.DecryptNotesTRC20> getScanShieldedTRC20NotesByIvkMethod() {
        MethodDescriptor<GrpcAPI.IvkDecryptTRC20Parameters, GrpcAPI.DecryptNotesTRC20> methodDescriptor = getScanShieldedTRC20NotesByIvkMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getScanShieldedTRC20NotesByIvkMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "ScanShieldedTRC20NotesByIvk")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.IvkDecryptTRC20Parameters.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.DecryptNotesTRC20.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("ScanShieldedTRC20NotesByIvk")).build();
                    getScanShieldedTRC20NotesByIvkMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.OvkDecryptTRC20Parameters, GrpcAPI.DecryptNotesTRC20> getScanShieldedTRC20NotesByOvkMethod() {
        MethodDescriptor<GrpcAPI.OvkDecryptTRC20Parameters, GrpcAPI.DecryptNotesTRC20> methodDescriptor = getScanShieldedTRC20NotesByOvkMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getScanShieldedTRC20NotesByOvkMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "ScanShieldedTRC20NotesByOvk")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.OvkDecryptTRC20Parameters.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.DecryptNotesTRC20.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("ScanShieldedTRC20NotesByOvk")).build();
                    getScanShieldedTRC20NotesByOvkMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.NfTRC20Parameters, GrpcAPI.NullifierResult> getIsShieldedTRC20ContractNoteSpentMethod() {
        MethodDescriptor<GrpcAPI.NfTRC20Parameters, GrpcAPI.NullifierResult> methodDescriptor = getIsShieldedTRC20ContractNoteSpentMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getIsShieldedTRC20ContractNoteSpentMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "IsShieldedTRC20ContractNoteSpent")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.NfTRC20Parameters.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.NullifierResult.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("IsShieldedTRC20ContractNoteSpent")).build();
                    getIsShieldedTRC20ContractNoteSpentMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.ShieldedTRC20TriggerContractParameters, GrpcAPI.BytesMessage> getGetTriggerInputForShieldedTRC20ContractMethod() {
        MethodDescriptor<GrpcAPI.ShieldedTRC20TriggerContractParameters, GrpcAPI.BytesMessage> methodDescriptor = getGetTriggerInputForShieldedTRC20ContractMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetTriggerInputForShieldedTRC20ContractMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetTriggerInputForShieldedTRC20Contract")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.ShieldedTRC20TriggerContractParameters.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetTriggerInputForShieldedTRC20Contract")).build();
                    getGetTriggerInputForShieldedTRC20ContractMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<Protocol.Transaction, GrpcAPI.TransactionExtention> getCreateCommonTransactionMethod() {
        MethodDescriptor<Protocol.Transaction, GrpcAPI.TransactionExtention> methodDescriptor = getCreateCommonTransactionMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getCreateCommonTransactionMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "CreateCommonTransaction")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(Protocol.Transaction.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("CreateCommonTransaction")).build();
                    getCreateCommonTransactionMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.NumberMessage, GrpcAPI.TransactionInfoList> getGetTransactionInfoByBlockNumMethod() {
        MethodDescriptor<GrpcAPI.NumberMessage, GrpcAPI.TransactionInfoList> methodDescriptor = getGetTransactionInfoByBlockNumMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetTransactionInfoByBlockNumMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetTransactionInfoByBlockNum")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.NumberMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionInfoList.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetTransactionInfoByBlockNum")).build();
                    getGetTransactionInfoByBlockNumMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.NumberMessage> getGetBurnTrxMethod() {
        MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.NumberMessage> methodDescriptor = getGetBurnTrxMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetBurnTrxMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetBurnTrx")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.EmptyMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.NumberMessage.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetBurnTrx")).build();
                    getGetBurnTrxMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BytesMessage, Protocol.Transaction> getGetTransactionFromPendingMethod() {
        MethodDescriptor<GrpcAPI.BytesMessage, Protocol.Transaction> methodDescriptor = getGetTransactionFromPendingMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetTransactionFromPendingMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetTransactionFromPending")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BytesMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.Transaction.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetTransactionFromPending")).build();
                    getGetTransactionFromPendingMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.TransactionIdList> getGetTransactionListFromPendingMethod() {
        MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.TransactionIdList> methodDescriptor = getGetTransactionListFromPendingMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetTransactionListFromPendingMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetTransactionListFromPending")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.EmptyMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionIdList.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetTransactionListFromPending")).build();
                    getGetTransactionListFromPendingMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.NumberMessage> getGetPendingSizeMethod() {
        MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.NumberMessage> methodDescriptor = getGetPendingSizeMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetPendingSizeMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetPendingSize")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.EmptyMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.NumberMessage.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetPendingSize")).build();
                    getGetPendingSizeMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.BlockReq, GrpcAPI.BlockExtention> getGetBlockMethod() {
        MethodDescriptor<GrpcAPI.BlockReq, GrpcAPI.BlockExtention> methodDescriptor = getGetBlockMethod;
        if (methodDescriptor == null) {
            synchronized (WalletGrpc.class) {
                methodDescriptor = getGetBlockMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetBlock")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.BlockReq.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.BlockExtention.getDefaultInstance())).setSchemaDescriptor(new WalletMethodDescriptorSupplier("GetBlock")).build();
                    getGetBlockMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static WalletStub newStub(Channel channel) {
        return new WalletStub(channel);
    }

    public static WalletBlockingStub newBlockingStub(Channel channel) {
        return new WalletBlockingStub(channel);
    }

    public static WalletFutureStub newFutureStub(Channel channel) {
        return new WalletFutureStub(channel);
    }

    public static abstract class WalletImplBase implements BindableService {
        public void getAccount(Protocol.Account account, StreamObserver<Protocol.Account> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetAccountMethod(), streamObserver);
        }

        public void getAccountById(Protocol.Account account, StreamObserver<Protocol.Account> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetAccountByIdMethod(), streamObserver);
        }

        public void getAccountBalance(BalanceContract.AccountBalanceRequest accountBalanceRequest, StreamObserver<BalanceContract.AccountBalanceResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetAccountBalanceMethod(), streamObserver);
        }

        public void getBlockBalanceTrace(BalanceContract.BlockBalanceTrace.BlockIdentifier blockIdentifier, StreamObserver<BalanceContract.BlockBalanceTrace> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetBlockBalanceTraceMethod(), streamObserver);
        }

        public void createTransaction(BalanceContract.TransferContract transferContract, StreamObserver<Protocol.Transaction> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getCreateTransactionMethod(), streamObserver);
        }

        public void createTransaction2(BalanceContract.TransferContract transferContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getCreateTransaction2Method(), streamObserver);
        }

        public void broadcastTransaction(Protocol.Transaction transaction, StreamObserver<GrpcAPI.Return> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getBroadcastTransactionMethod(), streamObserver);
        }

        public void updateAccount(AccountContract.AccountUpdateContract accountUpdateContract, StreamObserver<Protocol.Transaction> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getUpdateAccountMethod(), streamObserver);
        }

        public void setAccountId(AccountContract.SetAccountIdContract setAccountIdContract, StreamObserver<Protocol.Transaction> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getSetAccountIdMethod(), streamObserver);
        }

        public void updateAccount2(AccountContract.AccountUpdateContract accountUpdateContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getUpdateAccount2Method(), streamObserver);
        }

        public void voteWitnessAccount(WitnessContract.VoteWitnessContract voteWitnessContract, StreamObserver<Protocol.Transaction> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getVoteWitnessAccountMethod(), streamObserver);
        }

        public void updateSetting(SmartContractOuterClass.UpdateSettingContract updateSettingContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getUpdateSettingMethod(), streamObserver);
        }

        public void updateEnergyLimit(SmartContractOuterClass.UpdateEnergyLimitContract updateEnergyLimitContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getUpdateEnergyLimitMethod(), streamObserver);
        }

        public void voteWitnessAccount2(WitnessContract.VoteWitnessContract voteWitnessContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getVoteWitnessAccount2Method(), streamObserver);
        }

        public void createAssetIssue(AssetIssueContractOuterClass.AssetIssueContract assetIssueContract, StreamObserver<Protocol.Transaction> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getCreateAssetIssueMethod(), streamObserver);
        }

        public void createAssetIssue2(AssetIssueContractOuterClass.AssetIssueContract assetIssueContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getCreateAssetIssue2Method(), streamObserver);
        }

        public void updateWitness(WitnessContract.WitnessUpdateContract witnessUpdateContract, StreamObserver<Protocol.Transaction> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getUpdateWitnessMethod(), streamObserver);
        }

        public void updateWitness2(WitnessContract.WitnessUpdateContract witnessUpdateContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getUpdateWitness2Method(), streamObserver);
        }

        public void createAccount(AccountContract.AccountCreateContract accountCreateContract, StreamObserver<Protocol.Transaction> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getCreateAccountMethod(), streamObserver);
        }

        public void createAccount2(AccountContract.AccountCreateContract accountCreateContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getCreateAccount2Method(), streamObserver);
        }

        public void createWitness(WitnessContract.WitnessCreateContract witnessCreateContract, StreamObserver<Protocol.Transaction> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getCreateWitnessMethod(), streamObserver);
        }

        public void createWitness2(WitnessContract.WitnessCreateContract witnessCreateContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getCreateWitness2Method(), streamObserver);
        }

        public void transferAsset(AssetIssueContractOuterClass.TransferAssetContract transferAssetContract, StreamObserver<Protocol.Transaction> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getTransferAssetMethod(), streamObserver);
        }

        public void transferAsset2(AssetIssueContractOuterClass.TransferAssetContract transferAssetContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getTransferAsset2Method(), streamObserver);
        }

        public void participateAssetIssue(AssetIssueContractOuterClass.ParticipateAssetIssueContract participateAssetIssueContract, StreamObserver<Protocol.Transaction> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getParticipateAssetIssueMethod(), streamObserver);
        }

        public void participateAssetIssue2(AssetIssueContractOuterClass.ParticipateAssetIssueContract participateAssetIssueContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getParticipateAssetIssue2Method(), streamObserver);
        }

        public void freezeBalance(BalanceContract.FreezeBalanceContract freezeBalanceContract, StreamObserver<Protocol.Transaction> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getFreezeBalanceMethod(), streamObserver);
        }

        public void freezeBalance2(BalanceContract.FreezeBalanceContract freezeBalanceContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getFreezeBalance2Method(), streamObserver);
        }

        public void freezeBalanceV2(BalanceContract.FreezeBalanceV2Contract freezeBalanceV2Contract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getFreezeBalanceV2Method(), streamObserver);
        }

        public void unfreezeBalance(BalanceContract.UnfreezeBalanceContract unfreezeBalanceContract, StreamObserver<Protocol.Transaction> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getUnfreezeBalanceMethod(), streamObserver);
        }

        public void unfreezeBalance2(BalanceContract.UnfreezeBalanceContract unfreezeBalanceContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getUnfreezeBalance2Method(), streamObserver);
        }

        public void unfreezeBalanceV2(BalanceContract.UnfreezeBalanceV2Contract unfreezeBalanceV2Contract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getUnfreezeBalanceV2Method(), streamObserver);
        }

        public void unfreezeAsset(AssetIssueContractOuterClass.UnfreezeAssetContract unfreezeAssetContract, StreamObserver<Protocol.Transaction> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getUnfreezeAssetMethod(), streamObserver);
        }

        public void unfreezeAsset2(AssetIssueContractOuterClass.UnfreezeAssetContract unfreezeAssetContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getUnfreezeAsset2Method(), streamObserver);
        }

        public void withdrawBalance(BalanceContract.WithdrawBalanceContract withdrawBalanceContract, StreamObserver<Protocol.Transaction> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getWithdrawBalanceMethod(), streamObserver);
        }

        public void withdrawBalance2(BalanceContract.WithdrawBalanceContract withdrawBalanceContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getWithdrawBalance2Method(), streamObserver);
        }

        public void withdrawExpireUnfreeze(BalanceContract.WithdrawExpireUnfreezeContract withdrawExpireUnfreezeContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getWithdrawExpireUnfreezeMethod(), streamObserver);
        }

        public void delegateResource(BalanceContract.DelegateResourceContract delegateResourceContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getDelegateResourceMethod(), streamObserver);
        }

        public void unDelegateResource(BalanceContract.UnDelegateResourceContract unDelegateResourceContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getUnDelegateResourceMethod(), streamObserver);
        }

        public void cancelAllUnfreezeV2(BalanceContract.CancelAllUnfreezeV2Contract cancelAllUnfreezeV2Contract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getCancelAllUnfreezeV2Method(), streamObserver);
        }

        public void updateAsset(AssetIssueContractOuterClass.UpdateAssetContract updateAssetContract, StreamObserver<Protocol.Transaction> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getUpdateAssetMethod(), streamObserver);
        }

        public void updateAsset2(AssetIssueContractOuterClass.UpdateAssetContract updateAssetContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getUpdateAsset2Method(), streamObserver);
        }

        public void proposalCreate(ProposalContract.ProposalCreateContract proposalCreateContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getProposalCreateMethod(), streamObserver);
        }

        public void proposalApprove(ProposalContract.ProposalApproveContract proposalApproveContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getProposalApproveMethod(), streamObserver);
        }

        public void proposalDelete(ProposalContract.ProposalDeleteContract proposalDeleteContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getProposalDeleteMethod(), streamObserver);
        }

        public void buyStorage(StorageContract.BuyStorageContract buyStorageContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getBuyStorageMethod(), streamObserver);
        }

        public void buyStorageBytes(StorageContract.BuyStorageBytesContract buyStorageBytesContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getBuyStorageBytesMethod(), streamObserver);
        }

        public void sellStorage(StorageContract.SellStorageContract sellStorageContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getSellStorageMethod(), streamObserver);
        }

        public void exchangeCreate(ExchangeContract.ExchangeCreateContract exchangeCreateContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getExchangeCreateMethod(), streamObserver);
        }

        public void exchangeInject(ExchangeContract.ExchangeInjectContract exchangeInjectContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getExchangeInjectMethod(), streamObserver);
        }

        public void exchangeWithdraw(ExchangeContract.ExchangeWithdrawContract exchangeWithdrawContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getExchangeWithdrawMethod(), streamObserver);
        }

        public void exchangeTransaction(ExchangeContract.ExchangeTransactionContract exchangeTransactionContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getExchangeTransactionMethod(), streamObserver);
        }

        public void marketSellAsset(MarketContract.MarketSellAssetContract marketSellAssetContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getMarketSellAssetMethod(), streamObserver);
        }

        public void marketCancelOrder(MarketContract.MarketCancelOrderContract marketCancelOrderContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getMarketCancelOrderMethod(), streamObserver);
        }

        public void getMarketOrderById(GrpcAPI.BytesMessage bytesMessage, StreamObserver<Protocol.MarketOrder> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetMarketOrderByIdMethod(), streamObserver);
        }

        public void getMarketOrderByAccount(GrpcAPI.BytesMessage bytesMessage, StreamObserver<Protocol.MarketOrderList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetMarketOrderByAccountMethod(), streamObserver);
        }

        public void getMarketPriceByPair(Protocol.MarketOrderPair marketOrderPair, StreamObserver<Protocol.MarketPriceList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetMarketPriceByPairMethod(), streamObserver);
        }

        public void getMarketOrderListByPair(Protocol.MarketOrderPair marketOrderPair, StreamObserver<Protocol.MarketOrderList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetMarketOrderListByPairMethod(), streamObserver);
        }

        public void getMarketPairList(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<Protocol.MarketOrderPairList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetMarketPairListMethod(), streamObserver);
        }

        public void listNodes(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.NodeList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getListNodesMethod(), streamObserver);
        }

        public void getAssetIssueByAccount(Protocol.Account account, StreamObserver<GrpcAPI.AssetIssueList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetAssetIssueByAccountMethod(), streamObserver);
        }

        public void getAccountNet(Protocol.Account account, StreamObserver<GrpcAPI.AccountNetMessage> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetAccountNetMethod(), streamObserver);
        }

        public void getAccountResource(Protocol.Account account, StreamObserver<GrpcAPI.AccountResourceMessage> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetAccountResourceMethod(), streamObserver);
        }

        public void getAssetIssueByName(GrpcAPI.BytesMessage bytesMessage, StreamObserver<AssetIssueContractOuterClass.AssetIssueContract> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetAssetIssueByNameMethod(), streamObserver);
        }

        public void getAssetIssueListByName(GrpcAPI.BytesMessage bytesMessage, StreamObserver<GrpcAPI.AssetIssueList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetAssetIssueListByNameMethod(), streamObserver);
        }

        public void getAssetIssueById(GrpcAPI.BytesMessage bytesMessage, StreamObserver<AssetIssueContractOuterClass.AssetIssueContract> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetAssetIssueByIdMethod(), streamObserver);
        }

        public void getNowBlock(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<Protocol.Block> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetNowBlockMethod(), streamObserver);
        }

        public void getNowBlock2(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.BlockExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetNowBlock2Method(), streamObserver);
        }

        public void getBlockByNum(GrpcAPI.NumberMessage numberMessage, StreamObserver<Protocol.Block> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetBlockByNumMethod(), streamObserver);
        }

        public void getBlockByNum2(GrpcAPI.NumberMessage numberMessage, StreamObserver<GrpcAPI.BlockExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetBlockByNum2Method(), streamObserver);
        }

        public void getTransactionCountByBlockNum(GrpcAPI.NumberMessage numberMessage, StreamObserver<GrpcAPI.NumberMessage> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetTransactionCountByBlockNumMethod(), streamObserver);
        }

        public void getBlockById(GrpcAPI.BytesMessage bytesMessage, StreamObserver<Protocol.Block> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetBlockByIdMethod(), streamObserver);
        }

        public void getBlockByLimitNext(GrpcAPI.BlockLimit blockLimit, StreamObserver<GrpcAPI.BlockList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetBlockByLimitNextMethod(), streamObserver);
        }

        public void getBlockByLimitNext2(GrpcAPI.BlockLimit blockLimit, StreamObserver<GrpcAPI.BlockListExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetBlockByLimitNext2Method(), streamObserver);
        }

        public void getBlockByLatestNum(GrpcAPI.NumberMessage numberMessage, StreamObserver<GrpcAPI.BlockList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetBlockByLatestNumMethod(), streamObserver);
        }

        public void getBlockByLatestNum2(GrpcAPI.NumberMessage numberMessage, StreamObserver<GrpcAPI.BlockListExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetBlockByLatestNum2Method(), streamObserver);
        }

        public void getTransactionById(GrpcAPI.BytesMessage bytesMessage, StreamObserver<Protocol.Transaction> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetTransactionByIdMethod(), streamObserver);
        }

        public void deployContract(SmartContractOuterClass.CreateSmartContract createSmartContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getDeployContractMethod(), streamObserver);
        }

        public void getContract(GrpcAPI.BytesMessage bytesMessage, StreamObserver<SmartContractOuterClass.SmartContract> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetContractMethod(), streamObserver);
        }

        public void getContractInfo(GrpcAPI.BytesMessage bytesMessage, StreamObserver<SmartContractOuterClass.SmartContractDataWrapper> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetContractInfoMethod(), streamObserver);
        }

        public void triggerContract(SmartContractOuterClass.TriggerSmartContract triggerSmartContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getTriggerContractMethod(), streamObserver);
        }

        public void triggerConstantContract(SmartContractOuterClass.TriggerSmartContract triggerSmartContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getTriggerConstantContractMethod(), streamObserver);
        }

        public void estimateEnergy(SmartContractOuterClass.TriggerSmartContract triggerSmartContract, StreamObserver<GrpcAPI.EstimateEnergyMessage> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getEstimateEnergyMethod(), streamObserver);
        }

        public void clearContractABI(SmartContractOuterClass.ClearABIContract clearABIContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getClearContractABIMethod(), streamObserver);
        }

        public void listWitnesses(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.WitnessList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getListWitnessesMethod(), streamObserver);
        }

        public void getDelegatedResource(GrpcAPI.DelegatedResourceMessage delegatedResourceMessage, StreamObserver<GrpcAPI.DelegatedResourceList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetDelegatedResourceMethod(), streamObserver);
        }

        public void getDelegatedResourceV2(GrpcAPI.DelegatedResourceMessage delegatedResourceMessage, StreamObserver<GrpcAPI.DelegatedResourceList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetDelegatedResourceV2Method(), streamObserver);
        }

        public void getDelegatedResourceAccountIndex(GrpcAPI.BytesMessage bytesMessage, StreamObserver<Protocol.DelegatedResourceAccountIndex> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetDelegatedResourceAccountIndexMethod(), streamObserver);
        }

        public void getDelegatedResourceAccountIndexV2(GrpcAPI.BytesMessage bytesMessage, StreamObserver<Protocol.DelegatedResourceAccountIndex> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetDelegatedResourceAccountIndexV2Method(), streamObserver);
        }

        public void getCanDelegatedMaxSize(GrpcAPI.CanDelegatedMaxSizeRequestMessage canDelegatedMaxSizeRequestMessage, StreamObserver<GrpcAPI.CanDelegatedMaxSizeResponseMessage> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetCanDelegatedMaxSizeMethod(), streamObserver);
        }

        public void getAvailableUnfreezeCount(GrpcAPI.GetAvailableUnfreezeCountRequestMessage getAvailableUnfreezeCountRequestMessage, StreamObserver<GrpcAPI.GetAvailableUnfreezeCountResponseMessage> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetAvailableUnfreezeCountMethod(), streamObserver);
        }

        public void getCanWithdrawUnfreezeAmount(GrpcAPI.CanWithdrawUnfreezeAmountRequestMessage canWithdrawUnfreezeAmountRequestMessage, StreamObserver<GrpcAPI.CanWithdrawUnfreezeAmountResponseMessage> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetCanWithdrawUnfreezeAmountMethod(), streamObserver);
        }

        public void listProposals(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.ProposalList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getListProposalsMethod(), streamObserver);
        }

        public void getPaginatedProposalList(GrpcAPI.PaginatedMessage paginatedMessage, StreamObserver<GrpcAPI.ProposalList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetPaginatedProposalListMethod(), streamObserver);
        }

        public void getProposalById(GrpcAPI.BytesMessage bytesMessage, StreamObserver<Protocol.Proposal> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetProposalByIdMethod(), streamObserver);
        }

        public void listExchanges(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.ExchangeList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getListExchangesMethod(), streamObserver);
        }

        public void getPaginatedExchangeList(GrpcAPI.PaginatedMessage paginatedMessage, StreamObserver<GrpcAPI.ExchangeList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetPaginatedExchangeListMethod(), streamObserver);
        }

        public void getExchangeById(GrpcAPI.BytesMessage bytesMessage, StreamObserver<Protocol.Exchange> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetExchangeByIdMethod(), streamObserver);
        }

        public void getChainParameters(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<Protocol.ChainParameters> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetChainParametersMethod(), streamObserver);
        }

        public void getAssetIssueList(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.AssetIssueList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetAssetIssueListMethod(), streamObserver);
        }

        public void getPaginatedAssetIssueList(GrpcAPI.PaginatedMessage paginatedMessage, StreamObserver<GrpcAPI.AssetIssueList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetPaginatedAssetIssueListMethod(), streamObserver);
        }

        public void totalTransaction(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.NumberMessage> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getTotalTransactionMethod(), streamObserver);
        }

        public void getNextMaintenanceTime(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.NumberMessage> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetNextMaintenanceTimeMethod(), streamObserver);
        }

        public void getTransactionInfoById(GrpcAPI.BytesMessage bytesMessage, StreamObserver<Protocol.TransactionInfo> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetTransactionInfoByIdMethod(), streamObserver);
        }

        public void accountPermissionUpdate(AccountContract.AccountPermissionUpdateContract accountPermissionUpdateContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getAccountPermissionUpdateMethod(), streamObserver);
        }

        public void getTransactionSignWeight(Protocol.Transaction transaction, StreamObserver<GrpcAPI.TransactionSignWeight> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetTransactionSignWeightMethod(), streamObserver);
        }

        public void getTransactionApprovedList(Protocol.Transaction transaction, StreamObserver<GrpcAPI.TransactionApprovedList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetTransactionApprovedListMethod(), streamObserver);
        }

        public void getNodeInfo(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<Protocol.NodeInfo> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetNodeInfoMethod(), streamObserver);
        }

        public void getRewardInfo(GrpcAPI.BytesMessage bytesMessage, StreamObserver<GrpcAPI.NumberMessage> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetRewardInfoMethod(), streamObserver);
        }

        public void getBrokerageInfo(GrpcAPI.BytesMessage bytesMessage, StreamObserver<GrpcAPI.NumberMessage> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetBrokerageInfoMethod(), streamObserver);
        }

        public void updateBrokerage(StorageContract.UpdateBrokerageContract updateBrokerageContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getUpdateBrokerageMethod(), streamObserver);
        }

        public void createShieldedTransaction(GrpcAPI.PrivateParameters privateParameters, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getCreateShieldedTransactionMethod(), streamObserver);
        }

        public void getMerkleTreeVoucherInfo(ShieldContract.OutputPointInfo outputPointInfo, StreamObserver<ShieldContract.IncrementalMerkleVoucherInfo> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetMerkleTreeVoucherInfoMethod(), streamObserver);
        }

        public void scanNoteByIvk(GrpcAPI.IvkDecryptParameters ivkDecryptParameters, StreamObserver<GrpcAPI.DecryptNotes> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getScanNoteByIvkMethod(), streamObserver);
        }

        public void scanAndMarkNoteByIvk(GrpcAPI.IvkDecryptAndMarkParameters ivkDecryptAndMarkParameters, StreamObserver<GrpcAPI.DecryptNotesMarked> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getScanAndMarkNoteByIvkMethod(), streamObserver);
        }

        public void scanNoteByOvk(GrpcAPI.OvkDecryptParameters ovkDecryptParameters, StreamObserver<GrpcAPI.DecryptNotes> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getScanNoteByOvkMethod(), streamObserver);
        }

        public void getSpendingKey(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.BytesMessage> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetSpendingKeyMethod(), streamObserver);
        }

        public void getExpandedSpendingKey(GrpcAPI.BytesMessage bytesMessage, StreamObserver<GrpcAPI.ExpandedSpendingKeyMessage> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetExpandedSpendingKeyMethod(), streamObserver);
        }

        public void getAkFromAsk(GrpcAPI.BytesMessage bytesMessage, StreamObserver<GrpcAPI.BytesMessage> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetAkFromAskMethod(), streamObserver);
        }

        public void getNkFromNsk(GrpcAPI.BytesMessage bytesMessage, StreamObserver<GrpcAPI.BytesMessage> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetNkFromNskMethod(), streamObserver);
        }

        public void getIncomingViewingKey(GrpcAPI.ViewingKeyMessage viewingKeyMessage, StreamObserver<GrpcAPI.IncomingViewingKeyMessage> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetIncomingViewingKeyMethod(), streamObserver);
        }

        public void getDiversifier(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.DiversifierMessage> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetDiversifierMethod(), streamObserver);
        }

        public void getNewShieldedAddress(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.ShieldedAddressInfo> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetNewShieldedAddressMethod(), streamObserver);
        }

        public void getZenPaymentAddress(GrpcAPI.IncomingViewingKeyDiversifierMessage incomingViewingKeyDiversifierMessage, StreamObserver<GrpcAPI.PaymentAddressMessage> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetZenPaymentAddressMethod(), streamObserver);
        }

        public void getRcm(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.BytesMessage> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetRcmMethod(), streamObserver);
        }

        public void isSpend(GrpcAPI.NoteParameters noteParameters, StreamObserver<GrpcAPI.SpendResult> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getIsSpendMethod(), streamObserver);
        }

        public void createShieldedTransactionWithoutSpendAuthSig(GrpcAPI.PrivateParametersWithoutAsk privateParametersWithoutAsk, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getCreateShieldedTransactionWithoutSpendAuthSigMethod(), streamObserver);
        }

        public void getShieldTransactionHash(Protocol.Transaction transaction, StreamObserver<GrpcAPI.BytesMessage> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetShieldTransactionHashMethod(), streamObserver);
        }

        public void createSpendAuthSig(GrpcAPI.SpendAuthSigParameters spendAuthSigParameters, StreamObserver<GrpcAPI.BytesMessage> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getCreateSpendAuthSigMethod(), streamObserver);
        }

        public void createShieldNullifier(GrpcAPI.NfParameters nfParameters, StreamObserver<GrpcAPI.BytesMessage> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getCreateShieldNullifierMethod(), streamObserver);
        }

        public void createShieldedContractParameters(GrpcAPI.PrivateShieldedTRC20Parameters privateShieldedTRC20Parameters, StreamObserver<GrpcAPI.ShieldedTRC20Parameters> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getCreateShieldedContractParametersMethod(), streamObserver);
        }

        public void createShieldedContractParametersWithoutAsk(GrpcAPI.PrivateShieldedTRC20ParametersWithoutAsk privateShieldedTRC20ParametersWithoutAsk, StreamObserver<GrpcAPI.ShieldedTRC20Parameters> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getCreateShieldedContractParametersWithoutAskMethod(), streamObserver);
        }

        public void scanShieldedTRC20NotesByIvk(GrpcAPI.IvkDecryptTRC20Parameters ivkDecryptTRC20Parameters, StreamObserver<GrpcAPI.DecryptNotesTRC20> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getScanShieldedTRC20NotesByIvkMethod(), streamObserver);
        }

        public void scanShieldedTRC20NotesByOvk(GrpcAPI.OvkDecryptTRC20Parameters ovkDecryptTRC20Parameters, StreamObserver<GrpcAPI.DecryptNotesTRC20> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getScanShieldedTRC20NotesByOvkMethod(), streamObserver);
        }

        public void isShieldedTRC20ContractNoteSpent(GrpcAPI.NfTRC20Parameters nfTRC20Parameters, StreamObserver<GrpcAPI.NullifierResult> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getIsShieldedTRC20ContractNoteSpentMethod(), streamObserver);
        }

        public void getTriggerInputForShieldedTRC20Contract(GrpcAPI.ShieldedTRC20TriggerContractParameters shieldedTRC20TriggerContractParameters, StreamObserver<GrpcAPI.BytesMessage> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetTriggerInputForShieldedTRC20ContractMethod(), streamObserver);
        }

        public void createCommonTransaction(Protocol.Transaction transaction, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getCreateCommonTransactionMethod(), streamObserver);
        }

        public void getTransactionInfoByBlockNum(GrpcAPI.NumberMessage numberMessage, StreamObserver<GrpcAPI.TransactionInfoList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetTransactionInfoByBlockNumMethod(), streamObserver);
        }

        public void getBurnTrx(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.NumberMessage> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetBurnTrxMethod(), streamObserver);
        }

        public void getTransactionFromPending(GrpcAPI.BytesMessage bytesMessage, StreamObserver<Protocol.Transaction> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetTransactionFromPendingMethod(), streamObserver);
        }

        public void getTransactionListFromPending(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.TransactionIdList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetTransactionListFromPendingMethod(), streamObserver);
        }

        public void getPendingSize(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.NumberMessage> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetPendingSizeMethod(), streamObserver);
        }

        public void getBlock(GrpcAPI.BlockReq blockReq, StreamObserver<GrpcAPI.BlockExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletGrpc.getGetBlockMethod(), streamObserver);
        }

        @Override
        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(WalletGrpc.getServiceDescriptor()).addMethod(WalletGrpc.getGetAccountMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 0))).addMethod(WalletGrpc.getGetAccountByIdMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 1))).addMethod(WalletGrpc.getGetAccountBalanceMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 2))).addMethod(WalletGrpc.getGetBlockBalanceTraceMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 3))).addMethod(WalletGrpc.getCreateTransactionMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 4))).addMethod(WalletGrpc.getCreateTransaction2Method(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 5))).addMethod(WalletGrpc.getBroadcastTransactionMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 6))).addMethod(WalletGrpc.getUpdateAccountMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 7))).addMethod(WalletGrpc.getSetAccountIdMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 8))).addMethod(WalletGrpc.getUpdateAccount2Method(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 9))).addMethod(WalletGrpc.getVoteWitnessAccountMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 10))).addMethod(WalletGrpc.getUpdateSettingMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 11))).addMethod(WalletGrpc.getUpdateEnergyLimitMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 12))).addMethod(WalletGrpc.getVoteWitnessAccount2Method(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 13))).addMethod(WalletGrpc.getCreateAssetIssueMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 14))).addMethod(WalletGrpc.getCreateAssetIssue2Method(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 15))).addMethod(WalletGrpc.getUpdateWitnessMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 16))).addMethod(WalletGrpc.getUpdateWitness2Method(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 17))).addMethod(WalletGrpc.getCreateAccountMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 18))).addMethod(WalletGrpc.getCreateAccount2Method(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 19))).addMethod(WalletGrpc.getCreateWitnessMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 20))).addMethod(WalletGrpc.getCreateWitness2Method(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 21))).addMethod(WalletGrpc.getTransferAssetMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 22))).addMethod(WalletGrpc.getTransferAsset2Method(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 23))).addMethod(WalletGrpc.getParticipateAssetIssueMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 24))).addMethod(WalletGrpc.getParticipateAssetIssue2Method(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 25))).addMethod(WalletGrpc.getFreezeBalanceMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 26))).addMethod(WalletGrpc.getFreezeBalance2Method(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 27))).addMethod(WalletGrpc.getFreezeBalanceV2Method(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 28))).addMethod(WalletGrpc.getUnfreezeBalanceMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 29))).addMethod(WalletGrpc.getUnfreezeBalance2Method(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 30))).addMethod(WalletGrpc.getUnfreezeBalanceV2Method(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 31))).addMethod(WalletGrpc.getUnfreezeAssetMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 32))).addMethod(WalletGrpc.getUnfreezeAsset2Method(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 33))).addMethod(WalletGrpc.getWithdrawBalanceMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 34))).addMethod(WalletGrpc.getWithdrawBalance2Method(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 35))).addMethod(WalletGrpc.getWithdrawExpireUnfreezeMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 36))).addMethod(WalletGrpc.getDelegateResourceMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 37))).addMethod(WalletGrpc.getUnDelegateResourceMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 38))).addMethod(WalletGrpc.getCancelAllUnfreezeV2Method(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 39))).addMethod(WalletGrpc.getUpdateAssetMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 40))).addMethod(WalletGrpc.getUpdateAsset2Method(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 41))).addMethod(WalletGrpc.getProposalCreateMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 42))).addMethod(WalletGrpc.getProposalApproveMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 43))).addMethod(WalletGrpc.getProposalDeleteMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 44))).addMethod(WalletGrpc.getBuyStorageMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 45))).addMethod(WalletGrpc.getBuyStorageBytesMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 46))).addMethod(WalletGrpc.getSellStorageMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 47))).addMethod(WalletGrpc.getExchangeCreateMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 48))).addMethod(WalletGrpc.getExchangeInjectMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 49))).addMethod(WalletGrpc.getExchangeWithdrawMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 50))).addMethod(WalletGrpc.getExchangeTransactionMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 51))).addMethod(WalletGrpc.getMarketSellAssetMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 52))).addMethod(WalletGrpc.getMarketCancelOrderMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 53))).addMethod(WalletGrpc.getGetMarketOrderByIdMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 54))).addMethod(WalletGrpc.getGetMarketOrderByAccountMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 55))).addMethod(WalletGrpc.getGetMarketPriceByPairMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 56))).addMethod(WalletGrpc.getGetMarketOrderListByPairMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 57))).addMethod(WalletGrpc.getGetMarketPairListMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 58))).addMethod(WalletGrpc.getListNodesMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 59))).addMethod(WalletGrpc.getGetAssetIssueByAccountMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 60))).addMethod(WalletGrpc.getGetAccountNetMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 61))).addMethod(WalletGrpc.getGetAccountResourceMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 62))).addMethod(WalletGrpc.getGetAssetIssueByNameMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 63))).addMethod(WalletGrpc.getGetAssetIssueListByNameMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 64))).addMethod(WalletGrpc.getGetAssetIssueByIdMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 65))).addMethod(WalletGrpc.getGetNowBlockMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 66))).addMethod(WalletGrpc.getGetNowBlock2Method(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 67))).addMethod(WalletGrpc.getGetBlockByNumMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 68))).addMethod(WalletGrpc.getGetBlockByNum2Method(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 69))).addMethod(WalletGrpc.getGetTransactionCountByBlockNumMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 70))).addMethod(WalletGrpc.getGetBlockByIdMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 71))).addMethod(WalletGrpc.getGetBlockByLimitNextMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 72))).addMethod(WalletGrpc.getGetBlockByLimitNext2Method(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 73))).addMethod(WalletGrpc.getGetBlockByLatestNumMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 74))).addMethod(WalletGrpc.getGetBlockByLatestNum2Method(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 75))).addMethod(WalletGrpc.getGetTransactionByIdMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 76))).addMethod(WalletGrpc.getDeployContractMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 77))).addMethod(WalletGrpc.getGetContractMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 78))).addMethod(WalletGrpc.getGetContractInfoMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 79))).addMethod(WalletGrpc.getTriggerContractMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 80))).addMethod(WalletGrpc.getTriggerConstantContractMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 81))).addMethod(WalletGrpc.getEstimateEnergyMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 82))).addMethod(WalletGrpc.getClearContractABIMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 83))).addMethod(WalletGrpc.getListWitnessesMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 84))).addMethod(WalletGrpc.getGetDelegatedResourceMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 85))).addMethod(WalletGrpc.getGetDelegatedResourceV2Method(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 86))).addMethod(WalletGrpc.getGetDelegatedResourceAccountIndexMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 87))).addMethod(WalletGrpc.getGetDelegatedResourceAccountIndexV2Method(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 88))).addMethod(WalletGrpc.getGetCanDelegatedMaxSizeMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 89))).addMethod(WalletGrpc.getGetAvailableUnfreezeCountMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 90))).addMethod(WalletGrpc.getGetCanWithdrawUnfreezeAmountMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 91))).addMethod(WalletGrpc.getListProposalsMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 92))).addMethod(WalletGrpc.getGetPaginatedProposalListMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 93))).addMethod(WalletGrpc.getGetProposalByIdMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 94))).addMethod(WalletGrpc.getListExchangesMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 95))).addMethod(WalletGrpc.getGetPaginatedExchangeListMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 96))).addMethod(WalletGrpc.getGetExchangeByIdMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 97))).addMethod(WalletGrpc.getGetChainParametersMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 98))).addMethod(WalletGrpc.getGetAssetIssueListMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 99))).addMethod(WalletGrpc.getGetPaginatedAssetIssueListMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 100))).addMethod(WalletGrpc.getTotalTransactionMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 101))).addMethod(WalletGrpc.getGetNextMaintenanceTimeMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 102))).addMethod(WalletGrpc.getGetTransactionInfoByIdMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 103))).addMethod(WalletGrpc.getAccountPermissionUpdateMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 104))).addMethod(WalletGrpc.getGetTransactionSignWeightMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 105))).addMethod(WalletGrpc.getGetTransactionApprovedListMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 106))).addMethod(WalletGrpc.getGetNodeInfoMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 107))).addMethod(WalletGrpc.getGetRewardInfoMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 108))).addMethod(WalletGrpc.getGetBrokerageInfoMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 109))).addMethod(WalletGrpc.getUpdateBrokerageMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 110))).addMethod(WalletGrpc.getCreateShieldedTransactionMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 111))).addMethod(WalletGrpc.getGetMerkleTreeVoucherInfoMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 112))).addMethod(WalletGrpc.getScanNoteByIvkMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 113))).addMethod(WalletGrpc.getScanAndMarkNoteByIvkMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 114))).addMethod(WalletGrpc.getScanNoteByOvkMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 115))).addMethod(WalletGrpc.getGetSpendingKeyMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 116))).addMethod(WalletGrpc.getGetExpandedSpendingKeyMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 117))).addMethod(WalletGrpc.getGetAkFromAskMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 118))).addMethod(WalletGrpc.getGetNkFromNskMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 119))).addMethod(WalletGrpc.getGetIncomingViewingKeyMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 120))).addMethod(WalletGrpc.getGetDiversifierMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 121))).addMethod(WalletGrpc.getGetNewShieldedAddressMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 122))).addMethod(WalletGrpc.getGetZenPaymentAddressMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 123))).addMethod(WalletGrpc.getGetRcmMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 124))).addMethod(WalletGrpc.getIsSpendMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 125))).addMethod(WalletGrpc.getCreateShieldedTransactionWithoutSpendAuthSigMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 126))).addMethod(WalletGrpc.getGetShieldTransactionHashMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 127))).addMethod(WalletGrpc.getCreateSpendAuthSigMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 128))).addMethod(WalletGrpc.getCreateShieldNullifierMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, WalletGrpc.METHODID_CREATE_SHIELD_NULLIFIER))).addMethod(WalletGrpc.getCreateShieldedContractParametersMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, WalletGrpc.METHODID_CREATE_SHIELDED_CONTRACT_PARAMETERS))).addMethod(WalletGrpc.getCreateShieldedContractParametersWithoutAskMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, WalletGrpc.METHODID_CREATE_SHIELDED_CONTRACT_PARAMETERS_WITHOUT_ASK))).addMethod(WalletGrpc.getScanShieldedTRC20NotesByIvkMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, WalletGrpc.METHODID_SCAN_SHIELDED_TRC20NOTES_BY_IVK))).addMethod(WalletGrpc.getScanShieldedTRC20NotesByOvkMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, WalletGrpc.METHODID_SCAN_SHIELDED_TRC20NOTES_BY_OVK))).addMethod(WalletGrpc.getIsShieldedTRC20ContractNoteSpentMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, WalletGrpc.METHODID_IS_SHIELDED_TRC20CONTRACT_NOTE_SPENT))).addMethod(WalletGrpc.getGetTriggerInputForShieldedTRC20ContractMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, WalletGrpc.METHODID_GET_TRIGGER_INPUT_FOR_SHIELDED_TRC20CONTRACT))).addMethod(WalletGrpc.getCreateCommonTransactionMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, WalletGrpc.METHODID_CREATE_COMMON_TRANSACTION))).addMethod(WalletGrpc.getGetTransactionInfoByBlockNumMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, WalletGrpc.METHODID_GET_TRANSACTION_INFO_BY_BLOCK_NUM))).addMethod(WalletGrpc.getGetBurnTrxMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, WalletGrpc.METHODID_GET_BURN_TRX))).addMethod(WalletGrpc.getGetTransactionFromPendingMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, WalletGrpc.METHODID_GET_TRANSACTION_FROM_PENDING))).addMethod(WalletGrpc.getGetTransactionListFromPendingMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, WalletGrpc.METHODID_GET_TRANSACTION_LIST_FROM_PENDING))).addMethod(WalletGrpc.getGetPendingSizeMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, WalletGrpc.METHODID_GET_PENDING_SIZE))).addMethod(WalletGrpc.getGetBlockMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, WalletGrpc.METHODID_GET_BLOCK))).build();
        }
    }

    public static final class WalletStub extends AbstractStub<WalletStub> {
        private WalletStub(Channel channel) {
            super(channel);
        }

        private WalletStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        public WalletStub build(Channel channel, CallOptions callOptions) {
            return new WalletStub(channel, callOptions);
        }

        public void getAccount(Protocol.Account account, StreamObserver<Protocol.Account> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetAccountMethod(), getCallOptions()), account, streamObserver);
        }

        public void getAccountById(Protocol.Account account, StreamObserver<Protocol.Account> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetAccountByIdMethod(), getCallOptions()), account, streamObserver);
        }

        public void getAccountBalance(BalanceContract.AccountBalanceRequest accountBalanceRequest, StreamObserver<BalanceContract.AccountBalanceResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetAccountBalanceMethod(), getCallOptions()), accountBalanceRequest, streamObserver);
        }

        public void getBlockBalanceTrace(BalanceContract.BlockBalanceTrace.BlockIdentifier blockIdentifier, StreamObserver<BalanceContract.BlockBalanceTrace> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetBlockBalanceTraceMethod(), getCallOptions()), blockIdentifier, streamObserver);
        }

        public void createTransaction(BalanceContract.TransferContract transferContract, StreamObserver<Protocol.Transaction> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getCreateTransactionMethod(), getCallOptions()), transferContract, streamObserver);
        }

        public void createTransaction2(BalanceContract.TransferContract transferContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getCreateTransaction2Method(), getCallOptions()), transferContract, streamObserver);
        }

        public void broadcastTransaction(Protocol.Transaction transaction, StreamObserver<GrpcAPI.Return> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getBroadcastTransactionMethod(), getCallOptions()), transaction, streamObserver);
        }

        public void updateAccount(AccountContract.AccountUpdateContract accountUpdateContract, StreamObserver<Protocol.Transaction> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getUpdateAccountMethod(), getCallOptions()), accountUpdateContract, streamObserver);
        }

        public void setAccountId(AccountContract.SetAccountIdContract setAccountIdContract, StreamObserver<Protocol.Transaction> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getSetAccountIdMethod(), getCallOptions()), setAccountIdContract, streamObserver);
        }

        public void updateAccount2(AccountContract.AccountUpdateContract accountUpdateContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getUpdateAccount2Method(), getCallOptions()), accountUpdateContract, streamObserver);
        }

        public void voteWitnessAccount(WitnessContract.VoteWitnessContract voteWitnessContract, StreamObserver<Protocol.Transaction> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getVoteWitnessAccountMethod(), getCallOptions()), voteWitnessContract, streamObserver);
        }

        public void updateSetting(SmartContractOuterClass.UpdateSettingContract updateSettingContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getUpdateSettingMethod(), getCallOptions()), updateSettingContract, streamObserver);
        }

        public void updateEnergyLimit(SmartContractOuterClass.UpdateEnergyLimitContract updateEnergyLimitContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getUpdateEnergyLimitMethod(), getCallOptions()), updateEnergyLimitContract, streamObserver);
        }

        public void voteWitnessAccount2(WitnessContract.VoteWitnessContract voteWitnessContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getVoteWitnessAccount2Method(), getCallOptions()), voteWitnessContract, streamObserver);
        }

        public void createAssetIssue(AssetIssueContractOuterClass.AssetIssueContract assetIssueContract, StreamObserver<Protocol.Transaction> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getCreateAssetIssueMethod(), getCallOptions()), assetIssueContract, streamObserver);
        }

        public void createAssetIssue2(AssetIssueContractOuterClass.AssetIssueContract assetIssueContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getCreateAssetIssue2Method(), getCallOptions()), assetIssueContract, streamObserver);
        }

        public void updateWitness(WitnessContract.WitnessUpdateContract witnessUpdateContract, StreamObserver<Protocol.Transaction> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getUpdateWitnessMethod(), getCallOptions()), witnessUpdateContract, streamObserver);
        }

        public void updateWitness2(WitnessContract.WitnessUpdateContract witnessUpdateContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getUpdateWitness2Method(), getCallOptions()), witnessUpdateContract, streamObserver);
        }

        public void createAccount(AccountContract.AccountCreateContract accountCreateContract, StreamObserver<Protocol.Transaction> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getCreateAccountMethod(), getCallOptions()), accountCreateContract, streamObserver);
        }

        public void createAccount2(AccountContract.AccountCreateContract accountCreateContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getCreateAccount2Method(), getCallOptions()), accountCreateContract, streamObserver);
        }

        public void createWitness(WitnessContract.WitnessCreateContract witnessCreateContract, StreamObserver<Protocol.Transaction> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getCreateWitnessMethod(), getCallOptions()), witnessCreateContract, streamObserver);
        }

        public void createWitness2(WitnessContract.WitnessCreateContract witnessCreateContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getCreateWitness2Method(), getCallOptions()), witnessCreateContract, streamObserver);
        }

        public void transferAsset(AssetIssueContractOuterClass.TransferAssetContract transferAssetContract, StreamObserver<Protocol.Transaction> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getTransferAssetMethod(), getCallOptions()), transferAssetContract, streamObserver);
        }

        public void transferAsset2(AssetIssueContractOuterClass.TransferAssetContract transferAssetContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getTransferAsset2Method(), getCallOptions()), transferAssetContract, streamObserver);
        }

        public void participateAssetIssue(AssetIssueContractOuterClass.ParticipateAssetIssueContract participateAssetIssueContract, StreamObserver<Protocol.Transaction> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getParticipateAssetIssueMethod(), getCallOptions()), participateAssetIssueContract, streamObserver);
        }

        public void participateAssetIssue2(AssetIssueContractOuterClass.ParticipateAssetIssueContract participateAssetIssueContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getParticipateAssetIssue2Method(), getCallOptions()), participateAssetIssueContract, streamObserver);
        }

        public void freezeBalance(BalanceContract.FreezeBalanceContract freezeBalanceContract, StreamObserver<Protocol.Transaction> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getFreezeBalanceMethod(), getCallOptions()), freezeBalanceContract, streamObserver);
        }

        public void freezeBalance2(BalanceContract.FreezeBalanceContract freezeBalanceContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getFreezeBalance2Method(), getCallOptions()), freezeBalanceContract, streamObserver);
        }

        public void freezeBalanceV2(BalanceContract.FreezeBalanceV2Contract freezeBalanceV2Contract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getFreezeBalanceV2Method(), getCallOptions()), freezeBalanceV2Contract, streamObserver);
        }

        public void unfreezeBalance(BalanceContract.UnfreezeBalanceContract unfreezeBalanceContract, StreamObserver<Protocol.Transaction> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getUnfreezeBalanceMethod(), getCallOptions()), unfreezeBalanceContract, streamObserver);
        }

        public void unfreezeBalance2(BalanceContract.UnfreezeBalanceContract unfreezeBalanceContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getUnfreezeBalance2Method(), getCallOptions()), unfreezeBalanceContract, streamObserver);
        }

        public void unfreezeBalanceV2(BalanceContract.UnfreezeBalanceV2Contract unfreezeBalanceV2Contract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getUnfreezeBalanceV2Method(), getCallOptions()), unfreezeBalanceV2Contract, streamObserver);
        }

        public void unfreezeAsset(AssetIssueContractOuterClass.UnfreezeAssetContract unfreezeAssetContract, StreamObserver<Protocol.Transaction> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getUnfreezeAssetMethod(), getCallOptions()), unfreezeAssetContract, streamObserver);
        }

        public void unfreezeAsset2(AssetIssueContractOuterClass.UnfreezeAssetContract unfreezeAssetContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getUnfreezeAsset2Method(), getCallOptions()), unfreezeAssetContract, streamObserver);
        }

        public void withdrawBalance(BalanceContract.WithdrawBalanceContract withdrawBalanceContract, StreamObserver<Protocol.Transaction> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getWithdrawBalanceMethod(), getCallOptions()), withdrawBalanceContract, streamObserver);
        }

        public void withdrawBalance2(BalanceContract.WithdrawBalanceContract withdrawBalanceContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getWithdrawBalance2Method(), getCallOptions()), withdrawBalanceContract, streamObserver);
        }

        public void withdrawExpireUnfreeze(BalanceContract.WithdrawExpireUnfreezeContract withdrawExpireUnfreezeContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getWithdrawExpireUnfreezeMethod(), getCallOptions()), withdrawExpireUnfreezeContract, streamObserver);
        }

        public void delegateResource(BalanceContract.DelegateResourceContract delegateResourceContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getDelegateResourceMethod(), getCallOptions()), delegateResourceContract, streamObserver);
        }

        public void unDelegateResource(BalanceContract.UnDelegateResourceContract unDelegateResourceContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getUnDelegateResourceMethod(), getCallOptions()), unDelegateResourceContract, streamObserver);
        }

        public void cancelAllUnfreezeV2(BalanceContract.CancelAllUnfreezeV2Contract cancelAllUnfreezeV2Contract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getCancelAllUnfreezeV2Method(), getCallOptions()), cancelAllUnfreezeV2Contract, streamObserver);
        }

        public void updateAsset(AssetIssueContractOuterClass.UpdateAssetContract updateAssetContract, StreamObserver<Protocol.Transaction> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getUpdateAssetMethod(), getCallOptions()), updateAssetContract, streamObserver);
        }

        public void updateAsset2(AssetIssueContractOuterClass.UpdateAssetContract updateAssetContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getUpdateAsset2Method(), getCallOptions()), updateAssetContract, streamObserver);
        }

        public void proposalCreate(ProposalContract.ProposalCreateContract proposalCreateContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getProposalCreateMethod(), getCallOptions()), proposalCreateContract, streamObserver);
        }

        public void proposalApprove(ProposalContract.ProposalApproveContract proposalApproveContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getProposalApproveMethod(), getCallOptions()), proposalApproveContract, streamObserver);
        }

        public void proposalDelete(ProposalContract.ProposalDeleteContract proposalDeleteContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getProposalDeleteMethod(), getCallOptions()), proposalDeleteContract, streamObserver);
        }

        public void buyStorage(StorageContract.BuyStorageContract buyStorageContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getBuyStorageMethod(), getCallOptions()), buyStorageContract, streamObserver);
        }

        public void buyStorageBytes(StorageContract.BuyStorageBytesContract buyStorageBytesContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getBuyStorageBytesMethod(), getCallOptions()), buyStorageBytesContract, streamObserver);
        }

        public void sellStorage(StorageContract.SellStorageContract sellStorageContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getSellStorageMethod(), getCallOptions()), sellStorageContract, streamObserver);
        }

        public void exchangeCreate(ExchangeContract.ExchangeCreateContract exchangeCreateContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getExchangeCreateMethod(), getCallOptions()), exchangeCreateContract, streamObserver);
        }

        public void exchangeInject(ExchangeContract.ExchangeInjectContract exchangeInjectContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getExchangeInjectMethod(), getCallOptions()), exchangeInjectContract, streamObserver);
        }

        public void exchangeWithdraw(ExchangeContract.ExchangeWithdrawContract exchangeWithdrawContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getExchangeWithdrawMethod(), getCallOptions()), exchangeWithdrawContract, streamObserver);
        }

        public void exchangeTransaction(ExchangeContract.ExchangeTransactionContract exchangeTransactionContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getExchangeTransactionMethod(), getCallOptions()), exchangeTransactionContract, streamObserver);
        }

        public void marketSellAsset(MarketContract.MarketSellAssetContract marketSellAssetContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getMarketSellAssetMethod(), getCallOptions()), marketSellAssetContract, streamObserver);
        }

        public void marketCancelOrder(MarketContract.MarketCancelOrderContract marketCancelOrderContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getMarketCancelOrderMethod(), getCallOptions()), marketCancelOrderContract, streamObserver);
        }

        public void getMarketOrderById(GrpcAPI.BytesMessage bytesMessage, StreamObserver<Protocol.MarketOrder> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetMarketOrderByIdMethod(), getCallOptions()), bytesMessage, streamObserver);
        }

        public void getMarketOrderByAccount(GrpcAPI.BytesMessage bytesMessage, StreamObserver<Protocol.MarketOrderList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetMarketOrderByAccountMethod(), getCallOptions()), bytesMessage, streamObserver);
        }

        public void getMarketPriceByPair(Protocol.MarketOrderPair marketOrderPair, StreamObserver<Protocol.MarketPriceList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetMarketPriceByPairMethod(), getCallOptions()), marketOrderPair, streamObserver);
        }

        public void getMarketOrderListByPair(Protocol.MarketOrderPair marketOrderPair, StreamObserver<Protocol.MarketOrderList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetMarketOrderListByPairMethod(), getCallOptions()), marketOrderPair, streamObserver);
        }

        public void getMarketPairList(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<Protocol.MarketOrderPairList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetMarketPairListMethod(), getCallOptions()), emptyMessage, streamObserver);
        }

        public void listNodes(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.NodeList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getListNodesMethod(), getCallOptions()), emptyMessage, streamObserver);
        }

        public void getAssetIssueByAccount(Protocol.Account account, StreamObserver<GrpcAPI.AssetIssueList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetAssetIssueByAccountMethod(), getCallOptions()), account, streamObserver);
        }

        public void getAccountNet(Protocol.Account account, StreamObserver<GrpcAPI.AccountNetMessage> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetAccountNetMethod(), getCallOptions()), account, streamObserver);
        }

        public void getAccountResource(Protocol.Account account, StreamObserver<GrpcAPI.AccountResourceMessage> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetAccountResourceMethod(), getCallOptions()), account, streamObserver);
        }

        public void getAssetIssueByName(GrpcAPI.BytesMessage bytesMessage, StreamObserver<AssetIssueContractOuterClass.AssetIssueContract> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetAssetIssueByNameMethod(), getCallOptions()), bytesMessage, streamObserver);
        }

        public void getAssetIssueListByName(GrpcAPI.BytesMessage bytesMessage, StreamObserver<GrpcAPI.AssetIssueList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetAssetIssueListByNameMethod(), getCallOptions()), bytesMessage, streamObserver);
        }

        public void getAssetIssueById(GrpcAPI.BytesMessage bytesMessage, StreamObserver<AssetIssueContractOuterClass.AssetIssueContract> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetAssetIssueByIdMethod(), getCallOptions()), bytesMessage, streamObserver);
        }

        public void getNowBlock(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<Protocol.Block> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetNowBlockMethod(), getCallOptions()), emptyMessage, streamObserver);
        }

        public void getNowBlock2(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.BlockExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetNowBlock2Method(), getCallOptions()), emptyMessage, streamObserver);
        }

        public void getBlockByNum(GrpcAPI.NumberMessage numberMessage, StreamObserver<Protocol.Block> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetBlockByNumMethod(), getCallOptions()), numberMessage, streamObserver);
        }

        public void getBlockByNum2(GrpcAPI.NumberMessage numberMessage, StreamObserver<GrpcAPI.BlockExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetBlockByNum2Method(), getCallOptions()), numberMessage, streamObserver);
        }

        public void getTransactionCountByBlockNum(GrpcAPI.NumberMessage numberMessage, StreamObserver<GrpcAPI.NumberMessage> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetTransactionCountByBlockNumMethod(), getCallOptions()), numberMessage, streamObserver);
        }

        public void getBlockById(GrpcAPI.BytesMessage bytesMessage, StreamObserver<Protocol.Block> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetBlockByIdMethod(), getCallOptions()), bytesMessage, streamObserver);
        }

        public void getBlockByLimitNext(GrpcAPI.BlockLimit blockLimit, StreamObserver<GrpcAPI.BlockList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetBlockByLimitNextMethod(), getCallOptions()), blockLimit, streamObserver);
        }

        public void getBlockByLimitNext2(GrpcAPI.BlockLimit blockLimit, StreamObserver<GrpcAPI.BlockListExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetBlockByLimitNext2Method(), getCallOptions()), blockLimit, streamObserver);
        }

        public void getBlockByLatestNum(GrpcAPI.NumberMessage numberMessage, StreamObserver<GrpcAPI.BlockList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetBlockByLatestNumMethod(), getCallOptions()), numberMessage, streamObserver);
        }

        public void getBlockByLatestNum2(GrpcAPI.NumberMessage numberMessage, StreamObserver<GrpcAPI.BlockListExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetBlockByLatestNum2Method(), getCallOptions()), numberMessage, streamObserver);
        }

        public void getTransactionById(GrpcAPI.BytesMessage bytesMessage, StreamObserver<Protocol.Transaction> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetTransactionByIdMethod(), getCallOptions()), bytesMessage, streamObserver);
        }

        public void deployContract(SmartContractOuterClass.CreateSmartContract createSmartContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getDeployContractMethod(), getCallOptions()), createSmartContract, streamObserver);
        }

        public void getContract(GrpcAPI.BytesMessage bytesMessage, StreamObserver<SmartContractOuterClass.SmartContract> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetContractMethod(), getCallOptions()), bytesMessage, streamObserver);
        }

        public void getContractInfo(GrpcAPI.BytesMessage bytesMessage, StreamObserver<SmartContractOuterClass.SmartContractDataWrapper> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetContractInfoMethod(), getCallOptions()), bytesMessage, streamObserver);
        }

        public void triggerContract(SmartContractOuterClass.TriggerSmartContract triggerSmartContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getTriggerContractMethod(), getCallOptions()), triggerSmartContract, streamObserver);
        }

        public void triggerConstantContract(SmartContractOuterClass.TriggerSmartContract triggerSmartContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getTriggerConstantContractMethod(), getCallOptions()), triggerSmartContract, streamObserver);
        }

        public void estimateEnergy(SmartContractOuterClass.TriggerSmartContract triggerSmartContract, StreamObserver<GrpcAPI.EstimateEnergyMessage> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getEstimateEnergyMethod(), getCallOptions()), triggerSmartContract, streamObserver);
        }

        public void clearContractABI(SmartContractOuterClass.ClearABIContract clearABIContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getClearContractABIMethod(), getCallOptions()), clearABIContract, streamObserver);
        }

        public void listWitnesses(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.WitnessList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getListWitnessesMethod(), getCallOptions()), emptyMessage, streamObserver);
        }

        public void getDelegatedResource(GrpcAPI.DelegatedResourceMessage delegatedResourceMessage, StreamObserver<GrpcAPI.DelegatedResourceList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetDelegatedResourceMethod(), getCallOptions()), delegatedResourceMessage, streamObserver);
        }

        public void getDelegatedResourceV2(GrpcAPI.DelegatedResourceMessage delegatedResourceMessage, StreamObserver<GrpcAPI.DelegatedResourceList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetDelegatedResourceV2Method(), getCallOptions()), delegatedResourceMessage, streamObserver);
        }

        public void getDelegatedResourceAccountIndex(GrpcAPI.BytesMessage bytesMessage, StreamObserver<Protocol.DelegatedResourceAccountIndex> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetDelegatedResourceAccountIndexMethod(), getCallOptions()), bytesMessage, streamObserver);
        }

        public void getDelegatedResourceAccountIndexV2(GrpcAPI.BytesMessage bytesMessage, StreamObserver<Protocol.DelegatedResourceAccountIndex> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetDelegatedResourceAccountIndexV2Method(), getCallOptions()), bytesMessage, streamObserver);
        }

        public void getCanDelegatedMaxSize(GrpcAPI.CanDelegatedMaxSizeRequestMessage canDelegatedMaxSizeRequestMessage, StreamObserver<GrpcAPI.CanDelegatedMaxSizeResponseMessage> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetCanDelegatedMaxSizeMethod(), getCallOptions()), canDelegatedMaxSizeRequestMessage, streamObserver);
        }

        public void getAvailableUnfreezeCount(GrpcAPI.GetAvailableUnfreezeCountRequestMessage getAvailableUnfreezeCountRequestMessage, StreamObserver<GrpcAPI.GetAvailableUnfreezeCountResponseMessage> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetAvailableUnfreezeCountMethod(), getCallOptions()), getAvailableUnfreezeCountRequestMessage, streamObserver);
        }

        public void getCanWithdrawUnfreezeAmount(GrpcAPI.CanWithdrawUnfreezeAmountRequestMessage canWithdrawUnfreezeAmountRequestMessage, StreamObserver<GrpcAPI.CanWithdrawUnfreezeAmountResponseMessage> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetCanWithdrawUnfreezeAmountMethod(), getCallOptions()), canWithdrawUnfreezeAmountRequestMessage, streamObserver);
        }

        public void listProposals(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.ProposalList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getListProposalsMethod(), getCallOptions()), emptyMessage, streamObserver);
        }

        public void getPaginatedProposalList(GrpcAPI.PaginatedMessage paginatedMessage, StreamObserver<GrpcAPI.ProposalList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetPaginatedProposalListMethod(), getCallOptions()), paginatedMessage, streamObserver);
        }

        public void getProposalById(GrpcAPI.BytesMessage bytesMessage, StreamObserver<Protocol.Proposal> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetProposalByIdMethod(), getCallOptions()), bytesMessage, streamObserver);
        }

        public void listExchanges(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.ExchangeList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getListExchangesMethod(), getCallOptions()), emptyMessage, streamObserver);
        }

        public void getPaginatedExchangeList(GrpcAPI.PaginatedMessage paginatedMessage, StreamObserver<GrpcAPI.ExchangeList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetPaginatedExchangeListMethod(), getCallOptions()), paginatedMessage, streamObserver);
        }

        public void getExchangeById(GrpcAPI.BytesMessage bytesMessage, StreamObserver<Protocol.Exchange> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetExchangeByIdMethod(), getCallOptions()), bytesMessage, streamObserver);
        }

        public void getChainParameters(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<Protocol.ChainParameters> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetChainParametersMethod(), getCallOptions()), emptyMessage, streamObserver);
        }

        public void getAssetIssueList(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.AssetIssueList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetAssetIssueListMethod(), getCallOptions()), emptyMessage, streamObserver);
        }

        public void getPaginatedAssetIssueList(GrpcAPI.PaginatedMessage paginatedMessage, StreamObserver<GrpcAPI.AssetIssueList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetPaginatedAssetIssueListMethod(), getCallOptions()), paginatedMessage, streamObserver);
        }

        public void totalTransaction(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.NumberMessage> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getTotalTransactionMethod(), getCallOptions()), emptyMessage, streamObserver);
        }

        public void getNextMaintenanceTime(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.NumberMessage> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetNextMaintenanceTimeMethod(), getCallOptions()), emptyMessage, streamObserver);
        }

        public void getTransactionInfoById(GrpcAPI.BytesMessage bytesMessage, StreamObserver<Protocol.TransactionInfo> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetTransactionInfoByIdMethod(), getCallOptions()), bytesMessage, streamObserver);
        }

        public void accountPermissionUpdate(AccountContract.AccountPermissionUpdateContract accountPermissionUpdateContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getAccountPermissionUpdateMethod(), getCallOptions()), accountPermissionUpdateContract, streamObserver);
        }

        public void getTransactionSignWeight(Protocol.Transaction transaction, StreamObserver<GrpcAPI.TransactionSignWeight> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetTransactionSignWeightMethod(), getCallOptions()), transaction, streamObserver);
        }

        public void getTransactionApprovedList(Protocol.Transaction transaction, StreamObserver<GrpcAPI.TransactionApprovedList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetTransactionApprovedListMethod(), getCallOptions()), transaction, streamObserver);
        }

        public void getNodeInfo(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<Protocol.NodeInfo> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetNodeInfoMethod(), getCallOptions()), emptyMessage, streamObserver);
        }

        public void getRewardInfo(GrpcAPI.BytesMessage bytesMessage, StreamObserver<GrpcAPI.NumberMessage> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetRewardInfoMethod(), getCallOptions()), bytesMessage, streamObserver);
        }

        public void getBrokerageInfo(GrpcAPI.BytesMessage bytesMessage, StreamObserver<GrpcAPI.NumberMessage> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetBrokerageInfoMethod(), getCallOptions()), bytesMessage, streamObserver);
        }

        public void updateBrokerage(StorageContract.UpdateBrokerageContract updateBrokerageContract, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getUpdateBrokerageMethod(), getCallOptions()), updateBrokerageContract, streamObserver);
        }

        public void createShieldedTransaction(GrpcAPI.PrivateParameters privateParameters, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getCreateShieldedTransactionMethod(), getCallOptions()), privateParameters, streamObserver);
        }

        public void getMerkleTreeVoucherInfo(ShieldContract.OutputPointInfo outputPointInfo, StreamObserver<ShieldContract.IncrementalMerkleVoucherInfo> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetMerkleTreeVoucherInfoMethod(), getCallOptions()), outputPointInfo, streamObserver);
        }

        public void scanNoteByIvk(GrpcAPI.IvkDecryptParameters ivkDecryptParameters, StreamObserver<GrpcAPI.DecryptNotes> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getScanNoteByIvkMethod(), getCallOptions()), ivkDecryptParameters, streamObserver);
        }

        public void scanAndMarkNoteByIvk(GrpcAPI.IvkDecryptAndMarkParameters ivkDecryptAndMarkParameters, StreamObserver<GrpcAPI.DecryptNotesMarked> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getScanAndMarkNoteByIvkMethod(), getCallOptions()), ivkDecryptAndMarkParameters, streamObserver);
        }

        public void scanNoteByOvk(GrpcAPI.OvkDecryptParameters ovkDecryptParameters, StreamObserver<GrpcAPI.DecryptNotes> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getScanNoteByOvkMethod(), getCallOptions()), ovkDecryptParameters, streamObserver);
        }

        public void getSpendingKey(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.BytesMessage> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetSpendingKeyMethod(), getCallOptions()), emptyMessage, streamObserver);
        }

        public void getExpandedSpendingKey(GrpcAPI.BytesMessage bytesMessage, StreamObserver<GrpcAPI.ExpandedSpendingKeyMessage> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetExpandedSpendingKeyMethod(), getCallOptions()), bytesMessage, streamObserver);
        }

        public void getAkFromAsk(GrpcAPI.BytesMessage bytesMessage, StreamObserver<GrpcAPI.BytesMessage> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetAkFromAskMethod(), getCallOptions()), bytesMessage, streamObserver);
        }

        public void getNkFromNsk(GrpcAPI.BytesMessage bytesMessage, StreamObserver<GrpcAPI.BytesMessage> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetNkFromNskMethod(), getCallOptions()), bytesMessage, streamObserver);
        }

        public void getIncomingViewingKey(GrpcAPI.ViewingKeyMessage viewingKeyMessage, StreamObserver<GrpcAPI.IncomingViewingKeyMessage> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetIncomingViewingKeyMethod(), getCallOptions()), viewingKeyMessage, streamObserver);
        }

        public void getDiversifier(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.DiversifierMessage> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetDiversifierMethod(), getCallOptions()), emptyMessage, streamObserver);
        }

        public void getNewShieldedAddress(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.ShieldedAddressInfo> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetNewShieldedAddressMethod(), getCallOptions()), emptyMessage, streamObserver);
        }

        public void getZenPaymentAddress(GrpcAPI.IncomingViewingKeyDiversifierMessage incomingViewingKeyDiversifierMessage, StreamObserver<GrpcAPI.PaymentAddressMessage> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetZenPaymentAddressMethod(), getCallOptions()), incomingViewingKeyDiversifierMessage, streamObserver);
        }

        public void getRcm(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.BytesMessage> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetRcmMethod(), getCallOptions()), emptyMessage, streamObserver);
        }

        public void isSpend(GrpcAPI.NoteParameters noteParameters, StreamObserver<GrpcAPI.SpendResult> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getIsSpendMethod(), getCallOptions()), noteParameters, streamObserver);
        }

        public void createShieldedTransactionWithoutSpendAuthSig(GrpcAPI.PrivateParametersWithoutAsk privateParametersWithoutAsk, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getCreateShieldedTransactionWithoutSpendAuthSigMethod(), getCallOptions()), privateParametersWithoutAsk, streamObserver);
        }

        public void getShieldTransactionHash(Protocol.Transaction transaction, StreamObserver<GrpcAPI.BytesMessage> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetShieldTransactionHashMethod(), getCallOptions()), transaction, streamObserver);
        }

        public void createSpendAuthSig(GrpcAPI.SpendAuthSigParameters spendAuthSigParameters, StreamObserver<GrpcAPI.BytesMessage> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getCreateSpendAuthSigMethod(), getCallOptions()), spendAuthSigParameters, streamObserver);
        }

        public void createShieldNullifier(GrpcAPI.NfParameters nfParameters, StreamObserver<GrpcAPI.BytesMessage> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getCreateShieldNullifierMethod(), getCallOptions()), nfParameters, streamObserver);
        }

        public void createShieldedContractParameters(GrpcAPI.PrivateShieldedTRC20Parameters privateShieldedTRC20Parameters, StreamObserver<GrpcAPI.ShieldedTRC20Parameters> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getCreateShieldedContractParametersMethod(), getCallOptions()), privateShieldedTRC20Parameters, streamObserver);
        }

        public void createShieldedContractParametersWithoutAsk(GrpcAPI.PrivateShieldedTRC20ParametersWithoutAsk privateShieldedTRC20ParametersWithoutAsk, StreamObserver<GrpcAPI.ShieldedTRC20Parameters> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getCreateShieldedContractParametersWithoutAskMethod(), getCallOptions()), privateShieldedTRC20ParametersWithoutAsk, streamObserver);
        }

        public void scanShieldedTRC20NotesByIvk(GrpcAPI.IvkDecryptTRC20Parameters ivkDecryptTRC20Parameters, StreamObserver<GrpcAPI.DecryptNotesTRC20> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getScanShieldedTRC20NotesByIvkMethod(), getCallOptions()), ivkDecryptTRC20Parameters, streamObserver);
        }

        public void scanShieldedTRC20NotesByOvk(GrpcAPI.OvkDecryptTRC20Parameters ovkDecryptTRC20Parameters, StreamObserver<GrpcAPI.DecryptNotesTRC20> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getScanShieldedTRC20NotesByOvkMethod(), getCallOptions()), ovkDecryptTRC20Parameters, streamObserver);
        }

        public void isShieldedTRC20ContractNoteSpent(GrpcAPI.NfTRC20Parameters nfTRC20Parameters, StreamObserver<GrpcAPI.NullifierResult> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getIsShieldedTRC20ContractNoteSpentMethod(), getCallOptions()), nfTRC20Parameters, streamObserver);
        }

        public void getTriggerInputForShieldedTRC20Contract(GrpcAPI.ShieldedTRC20TriggerContractParameters shieldedTRC20TriggerContractParameters, StreamObserver<GrpcAPI.BytesMessage> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetTriggerInputForShieldedTRC20ContractMethod(), getCallOptions()), shieldedTRC20TriggerContractParameters, streamObserver);
        }

        public void createCommonTransaction(Protocol.Transaction transaction, StreamObserver<GrpcAPI.TransactionExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getCreateCommonTransactionMethod(), getCallOptions()), transaction, streamObserver);
        }

        public void getTransactionInfoByBlockNum(GrpcAPI.NumberMessage numberMessage, StreamObserver<GrpcAPI.TransactionInfoList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetTransactionInfoByBlockNumMethod(), getCallOptions()), numberMessage, streamObserver);
        }

        public void getBurnTrx(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.NumberMessage> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetBurnTrxMethod(), getCallOptions()), emptyMessage, streamObserver);
        }

        public void getTransactionFromPending(GrpcAPI.BytesMessage bytesMessage, StreamObserver<Protocol.Transaction> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetTransactionFromPendingMethod(), getCallOptions()), bytesMessage, streamObserver);
        }

        public void getTransactionListFromPending(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.TransactionIdList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetTransactionListFromPendingMethod(), getCallOptions()), emptyMessage, streamObserver);
        }

        public void getPendingSize(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.NumberMessage> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetPendingSizeMethod(), getCallOptions()), emptyMessage, streamObserver);
        }

        public void getBlock(GrpcAPI.BlockReq blockReq, StreamObserver<GrpcAPI.BlockExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletGrpc.getGetBlockMethod(), getCallOptions()), blockReq, streamObserver);
        }
    }

    public static final class WalletBlockingStub extends AbstractStub<WalletBlockingStub> {
        private WalletBlockingStub(Channel channel) {
            super(channel);
        }

        private WalletBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        public WalletBlockingStub build(Channel channel, CallOptions callOptions) {
            return new WalletBlockingStub(channel, callOptions);
        }

        public Protocol.Account getAccount(Protocol.Account account) {
            return (Protocol.Account) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetAccountMethod(), getCallOptions(), account);
        }

        public Protocol.Account getAccountById(Protocol.Account account) {
            return (Protocol.Account) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetAccountByIdMethod(), getCallOptions(), account);
        }

        public BalanceContract.AccountBalanceResponse getAccountBalance(BalanceContract.AccountBalanceRequest accountBalanceRequest) {
            return (BalanceContract.AccountBalanceResponse) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetAccountBalanceMethod(), getCallOptions(), accountBalanceRequest);
        }

        public BalanceContract.BlockBalanceTrace getBlockBalanceTrace(BalanceContract.BlockBalanceTrace.BlockIdentifier blockIdentifier) {
            return (BalanceContract.BlockBalanceTrace) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetBlockBalanceTraceMethod(), getCallOptions(), blockIdentifier);
        }

        public Protocol.Transaction createTransaction(BalanceContract.TransferContract transferContract) {
            return (Protocol.Transaction) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getCreateTransactionMethod(), getCallOptions(), transferContract);
        }

        public GrpcAPI.TransactionExtention createTransaction2(BalanceContract.TransferContract transferContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getCreateTransaction2Method(), getCallOptions(), transferContract);
        }

        public GrpcAPI.Return broadcastTransaction(Protocol.Transaction transaction) {
            return (GrpcAPI.Return) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getBroadcastTransactionMethod(), getCallOptions(), transaction);
        }

        public Protocol.Transaction updateAccount(AccountContract.AccountUpdateContract accountUpdateContract) {
            return (Protocol.Transaction) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getUpdateAccountMethod(), getCallOptions(), accountUpdateContract);
        }

        public Protocol.Transaction setAccountId(AccountContract.SetAccountIdContract setAccountIdContract) {
            return (Protocol.Transaction) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getSetAccountIdMethod(), getCallOptions(), setAccountIdContract);
        }

        public GrpcAPI.TransactionExtention updateAccount2(AccountContract.AccountUpdateContract accountUpdateContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getUpdateAccount2Method(), getCallOptions(), accountUpdateContract);
        }

        public Protocol.Transaction voteWitnessAccount(WitnessContract.VoteWitnessContract voteWitnessContract) {
            return (Protocol.Transaction) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getVoteWitnessAccountMethod(), getCallOptions(), voteWitnessContract);
        }

        public GrpcAPI.TransactionExtention updateSetting(SmartContractOuterClass.UpdateSettingContract updateSettingContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getUpdateSettingMethod(), getCallOptions(), updateSettingContract);
        }

        public GrpcAPI.TransactionExtention updateEnergyLimit(SmartContractOuterClass.UpdateEnergyLimitContract updateEnergyLimitContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getUpdateEnergyLimitMethod(), getCallOptions(), updateEnergyLimitContract);
        }

        public GrpcAPI.TransactionExtention voteWitnessAccount2(WitnessContract.VoteWitnessContract voteWitnessContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getVoteWitnessAccount2Method(), getCallOptions(), voteWitnessContract);
        }

        public Protocol.Transaction createAssetIssue(AssetIssueContractOuterClass.AssetIssueContract assetIssueContract) {
            return (Protocol.Transaction) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getCreateAssetIssueMethod(), getCallOptions(), assetIssueContract);
        }

        public GrpcAPI.TransactionExtention createAssetIssue2(AssetIssueContractOuterClass.AssetIssueContract assetIssueContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getCreateAssetIssue2Method(), getCallOptions(), assetIssueContract);
        }

        public Protocol.Transaction updateWitness(WitnessContract.WitnessUpdateContract witnessUpdateContract) {
            return (Protocol.Transaction) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getUpdateWitnessMethod(), getCallOptions(), witnessUpdateContract);
        }

        public GrpcAPI.TransactionExtention updateWitness2(WitnessContract.WitnessUpdateContract witnessUpdateContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getUpdateWitness2Method(), getCallOptions(), witnessUpdateContract);
        }

        public Protocol.Transaction createAccount(AccountContract.AccountCreateContract accountCreateContract) {
            return (Protocol.Transaction) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getCreateAccountMethod(), getCallOptions(), accountCreateContract);
        }

        public GrpcAPI.TransactionExtention createAccount2(AccountContract.AccountCreateContract accountCreateContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getCreateAccount2Method(), getCallOptions(), accountCreateContract);
        }

        public Protocol.Transaction createWitness(WitnessContract.WitnessCreateContract witnessCreateContract) {
            return (Protocol.Transaction) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getCreateWitnessMethod(), getCallOptions(), witnessCreateContract);
        }

        public GrpcAPI.TransactionExtention createWitness2(WitnessContract.WitnessCreateContract witnessCreateContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getCreateWitness2Method(), getCallOptions(), witnessCreateContract);
        }

        public Protocol.Transaction transferAsset(AssetIssueContractOuterClass.TransferAssetContract transferAssetContract) {
            return (Protocol.Transaction) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getTransferAssetMethod(), getCallOptions(), transferAssetContract);
        }

        public GrpcAPI.TransactionExtention transferAsset2(AssetIssueContractOuterClass.TransferAssetContract transferAssetContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getTransferAsset2Method(), getCallOptions(), transferAssetContract);
        }

        public Protocol.Transaction participateAssetIssue(AssetIssueContractOuterClass.ParticipateAssetIssueContract participateAssetIssueContract) {
            return (Protocol.Transaction) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getParticipateAssetIssueMethod(), getCallOptions(), participateAssetIssueContract);
        }

        public GrpcAPI.TransactionExtention participateAssetIssue2(AssetIssueContractOuterClass.ParticipateAssetIssueContract participateAssetIssueContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getParticipateAssetIssue2Method(), getCallOptions(), participateAssetIssueContract);
        }

        public Protocol.Transaction freezeBalance(BalanceContract.FreezeBalanceContract freezeBalanceContract) {
            return (Protocol.Transaction) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getFreezeBalanceMethod(), getCallOptions(), freezeBalanceContract);
        }

        public GrpcAPI.TransactionExtention freezeBalance2(BalanceContract.FreezeBalanceContract freezeBalanceContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getFreezeBalance2Method(), getCallOptions(), freezeBalanceContract);
        }

        public GrpcAPI.TransactionExtention freezeBalanceV2(BalanceContract.FreezeBalanceV2Contract freezeBalanceV2Contract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getFreezeBalanceV2Method(), getCallOptions(), freezeBalanceV2Contract);
        }

        public Protocol.Transaction unfreezeBalance(BalanceContract.UnfreezeBalanceContract unfreezeBalanceContract) {
            return (Protocol.Transaction) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getUnfreezeBalanceMethod(), getCallOptions(), unfreezeBalanceContract);
        }

        public GrpcAPI.TransactionExtention unfreezeBalance2(BalanceContract.UnfreezeBalanceContract unfreezeBalanceContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getUnfreezeBalance2Method(), getCallOptions(), unfreezeBalanceContract);
        }

        public GrpcAPI.TransactionExtention unfreezeBalanceV2(BalanceContract.UnfreezeBalanceV2Contract unfreezeBalanceV2Contract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getUnfreezeBalanceV2Method(), getCallOptions(), unfreezeBalanceV2Contract);
        }

        public Protocol.Transaction unfreezeAsset(AssetIssueContractOuterClass.UnfreezeAssetContract unfreezeAssetContract) {
            return (Protocol.Transaction) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getUnfreezeAssetMethod(), getCallOptions(), unfreezeAssetContract);
        }

        public GrpcAPI.TransactionExtention unfreezeAsset2(AssetIssueContractOuterClass.UnfreezeAssetContract unfreezeAssetContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getUnfreezeAsset2Method(), getCallOptions(), unfreezeAssetContract);
        }

        public Protocol.Transaction withdrawBalance(BalanceContract.WithdrawBalanceContract withdrawBalanceContract) {
            return (Protocol.Transaction) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getWithdrawBalanceMethod(), getCallOptions(), withdrawBalanceContract);
        }

        public GrpcAPI.TransactionExtention withdrawBalance2(BalanceContract.WithdrawBalanceContract withdrawBalanceContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getWithdrawBalance2Method(), getCallOptions(), withdrawBalanceContract);
        }

        public GrpcAPI.TransactionExtention withdrawExpireUnfreeze(BalanceContract.WithdrawExpireUnfreezeContract withdrawExpireUnfreezeContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getWithdrawExpireUnfreezeMethod(), getCallOptions(), withdrawExpireUnfreezeContract);
        }

        public GrpcAPI.TransactionExtention delegateResource(BalanceContract.DelegateResourceContract delegateResourceContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getDelegateResourceMethod(), getCallOptions(), delegateResourceContract);
        }

        public GrpcAPI.TransactionExtention unDelegateResource(BalanceContract.UnDelegateResourceContract unDelegateResourceContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getUnDelegateResourceMethod(), getCallOptions(), unDelegateResourceContract);
        }

        public GrpcAPI.TransactionExtention cancelAllUnfreezeV2(BalanceContract.CancelAllUnfreezeV2Contract cancelAllUnfreezeV2Contract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getCancelAllUnfreezeV2Method(), getCallOptions(), cancelAllUnfreezeV2Contract);
        }

        public Protocol.Transaction updateAsset(AssetIssueContractOuterClass.UpdateAssetContract updateAssetContract) {
            return (Protocol.Transaction) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getUpdateAssetMethod(), getCallOptions(), updateAssetContract);
        }

        public GrpcAPI.TransactionExtention updateAsset2(AssetIssueContractOuterClass.UpdateAssetContract updateAssetContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getUpdateAsset2Method(), getCallOptions(), updateAssetContract);
        }

        public GrpcAPI.TransactionExtention proposalCreate(ProposalContract.ProposalCreateContract proposalCreateContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getProposalCreateMethod(), getCallOptions(), proposalCreateContract);
        }

        public GrpcAPI.TransactionExtention proposalApprove(ProposalContract.ProposalApproveContract proposalApproveContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getProposalApproveMethod(), getCallOptions(), proposalApproveContract);
        }

        public GrpcAPI.TransactionExtention proposalDelete(ProposalContract.ProposalDeleteContract proposalDeleteContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getProposalDeleteMethod(), getCallOptions(), proposalDeleteContract);
        }

        public GrpcAPI.TransactionExtention buyStorage(StorageContract.BuyStorageContract buyStorageContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getBuyStorageMethod(), getCallOptions(), buyStorageContract);
        }

        public GrpcAPI.TransactionExtention buyStorageBytes(StorageContract.BuyStorageBytesContract buyStorageBytesContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getBuyStorageBytesMethod(), getCallOptions(), buyStorageBytesContract);
        }

        public GrpcAPI.TransactionExtention sellStorage(StorageContract.SellStorageContract sellStorageContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getSellStorageMethod(), getCallOptions(), sellStorageContract);
        }

        public GrpcAPI.TransactionExtention exchangeCreate(ExchangeContract.ExchangeCreateContract exchangeCreateContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getExchangeCreateMethod(), getCallOptions(), exchangeCreateContract);
        }

        public GrpcAPI.TransactionExtention exchangeInject(ExchangeContract.ExchangeInjectContract exchangeInjectContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getExchangeInjectMethod(), getCallOptions(), exchangeInjectContract);
        }

        public GrpcAPI.TransactionExtention exchangeWithdraw(ExchangeContract.ExchangeWithdrawContract exchangeWithdrawContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getExchangeWithdrawMethod(), getCallOptions(), exchangeWithdrawContract);
        }

        public GrpcAPI.TransactionExtention exchangeTransaction(ExchangeContract.ExchangeTransactionContract exchangeTransactionContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getExchangeTransactionMethod(), getCallOptions(), exchangeTransactionContract);
        }

        public GrpcAPI.TransactionExtention marketSellAsset(MarketContract.MarketSellAssetContract marketSellAssetContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getMarketSellAssetMethod(), getCallOptions(), marketSellAssetContract);
        }

        public GrpcAPI.TransactionExtention marketCancelOrder(MarketContract.MarketCancelOrderContract marketCancelOrderContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getMarketCancelOrderMethod(), getCallOptions(), marketCancelOrderContract);
        }

        public Protocol.MarketOrder getMarketOrderById(GrpcAPI.BytesMessage bytesMessage) {
            return (Protocol.MarketOrder) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetMarketOrderByIdMethod(), getCallOptions(), bytesMessage);
        }

        public Protocol.MarketOrderList getMarketOrderByAccount(GrpcAPI.BytesMessage bytesMessage) {
            return (Protocol.MarketOrderList) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetMarketOrderByAccountMethod(), getCallOptions(), bytesMessage);
        }

        public Protocol.MarketPriceList getMarketPriceByPair(Protocol.MarketOrderPair marketOrderPair) {
            return (Protocol.MarketPriceList) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetMarketPriceByPairMethod(), getCallOptions(), marketOrderPair);
        }

        public Protocol.MarketOrderList getMarketOrderListByPair(Protocol.MarketOrderPair marketOrderPair) {
            return (Protocol.MarketOrderList) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetMarketOrderListByPairMethod(), getCallOptions(), marketOrderPair);
        }

        public Protocol.MarketOrderPairList getMarketPairList(GrpcAPI.EmptyMessage emptyMessage) {
            return (Protocol.MarketOrderPairList) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetMarketPairListMethod(), getCallOptions(), emptyMessage);
        }

        public GrpcAPI.NodeList listNodes(GrpcAPI.EmptyMessage emptyMessage) {
            return (GrpcAPI.NodeList) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getListNodesMethod(), getCallOptions(), emptyMessage);
        }

        public GrpcAPI.AssetIssueList getAssetIssueByAccount(Protocol.Account account) {
            return (GrpcAPI.AssetIssueList) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetAssetIssueByAccountMethod(), getCallOptions(), account);
        }

        public GrpcAPI.AccountNetMessage getAccountNet(Protocol.Account account) {
            return (GrpcAPI.AccountNetMessage) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetAccountNetMethod(), getCallOptions(), account);
        }

        public GrpcAPI.AccountResourceMessage getAccountResource(Protocol.Account account) {
            return (GrpcAPI.AccountResourceMessage) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetAccountResourceMethod(), getCallOptions(), account);
        }

        public AssetIssueContractOuterClass.AssetIssueContract getAssetIssueByName(GrpcAPI.BytesMessage bytesMessage) {
            return (AssetIssueContractOuterClass.AssetIssueContract) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetAssetIssueByNameMethod(), getCallOptions(), bytesMessage);
        }

        public GrpcAPI.AssetIssueList getAssetIssueListByName(GrpcAPI.BytesMessage bytesMessage) {
            return (GrpcAPI.AssetIssueList) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetAssetIssueListByNameMethod(), getCallOptions(), bytesMessage);
        }

        public AssetIssueContractOuterClass.AssetIssueContract getAssetIssueById(GrpcAPI.BytesMessage bytesMessage) {
            return (AssetIssueContractOuterClass.AssetIssueContract) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetAssetIssueByIdMethod(), getCallOptions(), bytesMessage);
        }

        public Protocol.Block getNowBlock(GrpcAPI.EmptyMessage emptyMessage) {
            return (Protocol.Block) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetNowBlockMethod(), getCallOptions(), emptyMessage);
        }

        public GrpcAPI.BlockExtention getNowBlock2(GrpcAPI.EmptyMessage emptyMessage) {
            return (GrpcAPI.BlockExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetNowBlock2Method(), getCallOptions(), emptyMessage);
        }

        public Protocol.Block getBlockByNum(GrpcAPI.NumberMessage numberMessage) {
            return (Protocol.Block) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetBlockByNumMethod(), getCallOptions(), numberMessage);
        }

        public GrpcAPI.BlockExtention getBlockByNum2(GrpcAPI.NumberMessage numberMessage) {
            return (GrpcAPI.BlockExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetBlockByNum2Method(), getCallOptions(), numberMessage);
        }

        public GrpcAPI.NumberMessage getTransactionCountByBlockNum(GrpcAPI.NumberMessage numberMessage) {
            return (GrpcAPI.NumberMessage) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetTransactionCountByBlockNumMethod(), getCallOptions(), numberMessage);
        }

        public Protocol.Block getBlockById(GrpcAPI.BytesMessage bytesMessage) {
            return (Protocol.Block) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetBlockByIdMethod(), getCallOptions(), bytesMessage);
        }

        public GrpcAPI.BlockList getBlockByLimitNext(GrpcAPI.BlockLimit blockLimit) {
            return (GrpcAPI.BlockList) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetBlockByLimitNextMethod(), getCallOptions(), blockLimit);
        }

        public GrpcAPI.BlockListExtention getBlockByLimitNext2(GrpcAPI.BlockLimit blockLimit) {
            return (GrpcAPI.BlockListExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetBlockByLimitNext2Method(), getCallOptions(), blockLimit);
        }

        public GrpcAPI.BlockList getBlockByLatestNum(GrpcAPI.NumberMessage numberMessage) {
            return (GrpcAPI.BlockList) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetBlockByLatestNumMethod(), getCallOptions(), numberMessage);
        }

        public GrpcAPI.BlockListExtention getBlockByLatestNum2(GrpcAPI.NumberMessage numberMessage) {
            return (GrpcAPI.BlockListExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetBlockByLatestNum2Method(), getCallOptions(), numberMessage);
        }

        public Protocol.Transaction getTransactionById(GrpcAPI.BytesMessage bytesMessage) {
            return (Protocol.Transaction) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetTransactionByIdMethod(), getCallOptions(), bytesMessage);
        }

        public GrpcAPI.TransactionExtention deployContract(SmartContractOuterClass.CreateSmartContract createSmartContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getDeployContractMethod(), getCallOptions(), createSmartContract);
        }

        public SmartContractOuterClass.SmartContract getContract(GrpcAPI.BytesMessage bytesMessage) {
            return (SmartContractOuterClass.SmartContract) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetContractMethod(), getCallOptions(), bytesMessage);
        }

        public SmartContractOuterClass.SmartContractDataWrapper getContractInfo(GrpcAPI.BytesMessage bytesMessage) {
            return (SmartContractOuterClass.SmartContractDataWrapper) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetContractInfoMethod(), getCallOptions(), bytesMessage);
        }

        public GrpcAPI.TransactionExtention triggerContract(SmartContractOuterClass.TriggerSmartContract triggerSmartContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getTriggerContractMethod(), getCallOptions(), triggerSmartContract);
        }

        public GrpcAPI.TransactionExtention triggerConstantContract(SmartContractOuterClass.TriggerSmartContract triggerSmartContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getTriggerConstantContractMethod(), getCallOptions(), triggerSmartContract);
        }

        public GrpcAPI.EstimateEnergyMessage estimateEnergy(SmartContractOuterClass.TriggerSmartContract triggerSmartContract) {
            return (GrpcAPI.EstimateEnergyMessage) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getEstimateEnergyMethod(), getCallOptions(), triggerSmartContract);
        }

        public GrpcAPI.TransactionExtention clearContractABI(SmartContractOuterClass.ClearABIContract clearABIContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getClearContractABIMethod(), getCallOptions(), clearABIContract);
        }

        public GrpcAPI.WitnessList listWitnesses(GrpcAPI.EmptyMessage emptyMessage) {
            return (GrpcAPI.WitnessList) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getListWitnessesMethod(), getCallOptions(), emptyMessage);
        }

        public GrpcAPI.DelegatedResourceList getDelegatedResource(GrpcAPI.DelegatedResourceMessage delegatedResourceMessage) {
            return (GrpcAPI.DelegatedResourceList) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetDelegatedResourceMethod(), getCallOptions(), delegatedResourceMessage);
        }

        public GrpcAPI.DelegatedResourceList getDelegatedResourceV2(GrpcAPI.DelegatedResourceMessage delegatedResourceMessage) {
            return (GrpcAPI.DelegatedResourceList) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetDelegatedResourceV2Method(), getCallOptions(), delegatedResourceMessage);
        }

        public Protocol.DelegatedResourceAccountIndex getDelegatedResourceAccountIndex(GrpcAPI.BytesMessage bytesMessage) {
            return (Protocol.DelegatedResourceAccountIndex) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetDelegatedResourceAccountIndexMethod(), getCallOptions(), bytesMessage);
        }

        public Protocol.DelegatedResourceAccountIndex getDelegatedResourceAccountIndexV2(GrpcAPI.BytesMessage bytesMessage) {
            return (Protocol.DelegatedResourceAccountIndex) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetDelegatedResourceAccountIndexV2Method(), getCallOptions(), bytesMessage);
        }

        public GrpcAPI.CanDelegatedMaxSizeResponseMessage getCanDelegatedMaxSize(GrpcAPI.CanDelegatedMaxSizeRequestMessage canDelegatedMaxSizeRequestMessage) {
            return (GrpcAPI.CanDelegatedMaxSizeResponseMessage) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetCanDelegatedMaxSizeMethod(), getCallOptions(), canDelegatedMaxSizeRequestMessage);
        }

        public GrpcAPI.GetAvailableUnfreezeCountResponseMessage getAvailableUnfreezeCount(GrpcAPI.GetAvailableUnfreezeCountRequestMessage getAvailableUnfreezeCountRequestMessage) {
            return (GrpcAPI.GetAvailableUnfreezeCountResponseMessage) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetAvailableUnfreezeCountMethod(), getCallOptions(), getAvailableUnfreezeCountRequestMessage);
        }

        public GrpcAPI.CanWithdrawUnfreezeAmountResponseMessage getCanWithdrawUnfreezeAmount(GrpcAPI.CanWithdrawUnfreezeAmountRequestMessage canWithdrawUnfreezeAmountRequestMessage) {
            return (GrpcAPI.CanWithdrawUnfreezeAmountResponseMessage) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetCanWithdrawUnfreezeAmountMethod(), getCallOptions(), canWithdrawUnfreezeAmountRequestMessage);
        }

        public GrpcAPI.ProposalList listProposals(GrpcAPI.EmptyMessage emptyMessage) {
            return (GrpcAPI.ProposalList) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getListProposalsMethod(), getCallOptions(), emptyMessage);
        }

        public GrpcAPI.ProposalList getPaginatedProposalList(GrpcAPI.PaginatedMessage paginatedMessage) {
            return (GrpcAPI.ProposalList) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetPaginatedProposalListMethod(), getCallOptions(), paginatedMessage);
        }

        public Protocol.Proposal getProposalById(GrpcAPI.BytesMessage bytesMessage) {
            return (Protocol.Proposal) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetProposalByIdMethod(), getCallOptions(), bytesMessage);
        }

        public GrpcAPI.ExchangeList listExchanges(GrpcAPI.EmptyMessage emptyMessage) {
            return (GrpcAPI.ExchangeList) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getListExchangesMethod(), getCallOptions(), emptyMessage);
        }

        public GrpcAPI.ExchangeList getPaginatedExchangeList(GrpcAPI.PaginatedMessage paginatedMessage) {
            return (GrpcAPI.ExchangeList) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetPaginatedExchangeListMethod(), getCallOptions(), paginatedMessage);
        }

        public Protocol.Exchange getExchangeById(GrpcAPI.BytesMessage bytesMessage) {
            return (Protocol.Exchange) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetExchangeByIdMethod(), getCallOptions(), bytesMessage);
        }

        public Protocol.ChainParameters getChainParameters(GrpcAPI.EmptyMessage emptyMessage) {
            return (Protocol.ChainParameters) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetChainParametersMethod(), getCallOptions(), emptyMessage);
        }

        public GrpcAPI.AssetIssueList getAssetIssueList(GrpcAPI.EmptyMessage emptyMessage) {
            return (GrpcAPI.AssetIssueList) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetAssetIssueListMethod(), getCallOptions(), emptyMessage);
        }

        public GrpcAPI.AssetIssueList getPaginatedAssetIssueList(GrpcAPI.PaginatedMessage paginatedMessage) {
            return (GrpcAPI.AssetIssueList) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetPaginatedAssetIssueListMethod(), getCallOptions(), paginatedMessage);
        }

        public GrpcAPI.NumberMessage totalTransaction(GrpcAPI.EmptyMessage emptyMessage) {
            return (GrpcAPI.NumberMessage) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getTotalTransactionMethod(), getCallOptions(), emptyMessage);
        }

        public GrpcAPI.NumberMessage getNextMaintenanceTime(GrpcAPI.EmptyMessage emptyMessage) {
            return (GrpcAPI.NumberMessage) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetNextMaintenanceTimeMethod(), getCallOptions(), emptyMessage);
        }

        public Protocol.TransactionInfo getTransactionInfoById(GrpcAPI.BytesMessage bytesMessage) {
            return (Protocol.TransactionInfo) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetTransactionInfoByIdMethod(), getCallOptions(), bytesMessage);
        }

        public GrpcAPI.TransactionExtention accountPermissionUpdate(AccountContract.AccountPermissionUpdateContract accountPermissionUpdateContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getAccountPermissionUpdateMethod(), getCallOptions(), accountPermissionUpdateContract);
        }

        public GrpcAPI.TransactionSignWeight getTransactionSignWeight(Protocol.Transaction transaction) {
            return (GrpcAPI.TransactionSignWeight) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetTransactionSignWeightMethod(), getCallOptions(), transaction);
        }

        public GrpcAPI.TransactionApprovedList getTransactionApprovedList(Protocol.Transaction transaction) {
            return (GrpcAPI.TransactionApprovedList) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetTransactionApprovedListMethod(), getCallOptions(), transaction);
        }

        public Protocol.NodeInfo getNodeInfo(GrpcAPI.EmptyMessage emptyMessage) {
            return (Protocol.NodeInfo) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetNodeInfoMethod(), getCallOptions(), emptyMessage);
        }

        public GrpcAPI.NumberMessage getRewardInfo(GrpcAPI.BytesMessage bytesMessage) {
            return (GrpcAPI.NumberMessage) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetRewardInfoMethod(), getCallOptions(), bytesMessage);
        }

        public GrpcAPI.NumberMessage getBrokerageInfo(GrpcAPI.BytesMessage bytesMessage) {
            return (GrpcAPI.NumberMessage) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetBrokerageInfoMethod(), getCallOptions(), bytesMessage);
        }

        public GrpcAPI.TransactionExtention updateBrokerage(StorageContract.UpdateBrokerageContract updateBrokerageContract) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getUpdateBrokerageMethod(), getCallOptions(), updateBrokerageContract);
        }

        public GrpcAPI.TransactionExtention createShieldedTransaction(GrpcAPI.PrivateParameters privateParameters) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getCreateShieldedTransactionMethod(), getCallOptions(), privateParameters);
        }

        public ShieldContract.IncrementalMerkleVoucherInfo getMerkleTreeVoucherInfo(ShieldContract.OutputPointInfo outputPointInfo) {
            return (ShieldContract.IncrementalMerkleVoucherInfo) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetMerkleTreeVoucherInfoMethod(), getCallOptions(), outputPointInfo);
        }

        public GrpcAPI.DecryptNotes scanNoteByIvk(GrpcAPI.IvkDecryptParameters ivkDecryptParameters) {
            return (GrpcAPI.DecryptNotes) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getScanNoteByIvkMethod(), getCallOptions(), ivkDecryptParameters);
        }

        public GrpcAPI.DecryptNotesMarked scanAndMarkNoteByIvk(GrpcAPI.IvkDecryptAndMarkParameters ivkDecryptAndMarkParameters) {
            return (GrpcAPI.DecryptNotesMarked) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getScanAndMarkNoteByIvkMethod(), getCallOptions(), ivkDecryptAndMarkParameters);
        }

        public GrpcAPI.DecryptNotes scanNoteByOvk(GrpcAPI.OvkDecryptParameters ovkDecryptParameters) {
            return (GrpcAPI.DecryptNotes) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getScanNoteByOvkMethod(), getCallOptions(), ovkDecryptParameters);
        }

        public GrpcAPI.BytesMessage getSpendingKey(GrpcAPI.EmptyMessage emptyMessage) {
            return (GrpcAPI.BytesMessage) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetSpendingKeyMethod(), getCallOptions(), emptyMessage);
        }

        public GrpcAPI.ExpandedSpendingKeyMessage getExpandedSpendingKey(GrpcAPI.BytesMessage bytesMessage) {
            return (GrpcAPI.ExpandedSpendingKeyMessage) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetExpandedSpendingKeyMethod(), getCallOptions(), bytesMessage);
        }

        public GrpcAPI.BytesMessage getAkFromAsk(GrpcAPI.BytesMessage bytesMessage) {
            return (GrpcAPI.BytesMessage) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetAkFromAskMethod(), getCallOptions(), bytesMessage);
        }

        public GrpcAPI.BytesMessage getNkFromNsk(GrpcAPI.BytesMessage bytesMessage) {
            return (GrpcAPI.BytesMessage) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetNkFromNskMethod(), getCallOptions(), bytesMessage);
        }

        public GrpcAPI.IncomingViewingKeyMessage getIncomingViewingKey(GrpcAPI.ViewingKeyMessage viewingKeyMessage) {
            return (GrpcAPI.IncomingViewingKeyMessage) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetIncomingViewingKeyMethod(), getCallOptions(), viewingKeyMessage);
        }

        public GrpcAPI.DiversifierMessage getDiversifier(GrpcAPI.EmptyMessage emptyMessage) {
            return (GrpcAPI.DiversifierMessage) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetDiversifierMethod(), getCallOptions(), emptyMessage);
        }

        public GrpcAPI.ShieldedAddressInfo getNewShieldedAddress(GrpcAPI.EmptyMessage emptyMessage) {
            return (GrpcAPI.ShieldedAddressInfo) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetNewShieldedAddressMethod(), getCallOptions(), emptyMessage);
        }

        public GrpcAPI.PaymentAddressMessage getZenPaymentAddress(GrpcAPI.IncomingViewingKeyDiversifierMessage incomingViewingKeyDiversifierMessage) {
            return (GrpcAPI.PaymentAddressMessage) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetZenPaymentAddressMethod(), getCallOptions(), incomingViewingKeyDiversifierMessage);
        }

        public GrpcAPI.BytesMessage getRcm(GrpcAPI.EmptyMessage emptyMessage) {
            return (GrpcAPI.BytesMessage) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetRcmMethod(), getCallOptions(), emptyMessage);
        }

        public GrpcAPI.SpendResult isSpend(GrpcAPI.NoteParameters noteParameters) {
            return (GrpcAPI.SpendResult) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getIsSpendMethod(), getCallOptions(), noteParameters);
        }

        public GrpcAPI.TransactionExtention createShieldedTransactionWithoutSpendAuthSig(GrpcAPI.PrivateParametersWithoutAsk privateParametersWithoutAsk) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getCreateShieldedTransactionWithoutSpendAuthSigMethod(), getCallOptions(), privateParametersWithoutAsk);
        }

        public GrpcAPI.BytesMessage getShieldTransactionHash(Protocol.Transaction transaction) {
            return (GrpcAPI.BytesMessage) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetShieldTransactionHashMethod(), getCallOptions(), transaction);
        }

        public GrpcAPI.BytesMessage createSpendAuthSig(GrpcAPI.SpendAuthSigParameters spendAuthSigParameters) {
            return (GrpcAPI.BytesMessage) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getCreateSpendAuthSigMethod(), getCallOptions(), spendAuthSigParameters);
        }

        public GrpcAPI.BytesMessage createShieldNullifier(GrpcAPI.NfParameters nfParameters) {
            return (GrpcAPI.BytesMessage) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getCreateShieldNullifierMethod(), getCallOptions(), nfParameters);
        }

        public GrpcAPI.ShieldedTRC20Parameters createShieldedContractParameters(GrpcAPI.PrivateShieldedTRC20Parameters privateShieldedTRC20Parameters) {
            return (GrpcAPI.ShieldedTRC20Parameters) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getCreateShieldedContractParametersMethod(), getCallOptions(), privateShieldedTRC20Parameters);
        }

        public GrpcAPI.ShieldedTRC20Parameters createShieldedContractParametersWithoutAsk(GrpcAPI.PrivateShieldedTRC20ParametersWithoutAsk privateShieldedTRC20ParametersWithoutAsk) {
            return (GrpcAPI.ShieldedTRC20Parameters) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getCreateShieldedContractParametersWithoutAskMethod(), getCallOptions(), privateShieldedTRC20ParametersWithoutAsk);
        }

        public GrpcAPI.DecryptNotesTRC20 scanShieldedTRC20NotesByIvk(GrpcAPI.IvkDecryptTRC20Parameters ivkDecryptTRC20Parameters) {
            return (GrpcAPI.DecryptNotesTRC20) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getScanShieldedTRC20NotesByIvkMethod(), getCallOptions(), ivkDecryptTRC20Parameters);
        }

        public GrpcAPI.DecryptNotesTRC20 scanShieldedTRC20NotesByOvk(GrpcAPI.OvkDecryptTRC20Parameters ovkDecryptTRC20Parameters) {
            return (GrpcAPI.DecryptNotesTRC20) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getScanShieldedTRC20NotesByOvkMethod(), getCallOptions(), ovkDecryptTRC20Parameters);
        }

        public GrpcAPI.NullifierResult isShieldedTRC20ContractNoteSpent(GrpcAPI.NfTRC20Parameters nfTRC20Parameters) {
            return (GrpcAPI.NullifierResult) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getIsShieldedTRC20ContractNoteSpentMethod(), getCallOptions(), nfTRC20Parameters);
        }

        public GrpcAPI.BytesMessage getTriggerInputForShieldedTRC20Contract(GrpcAPI.ShieldedTRC20TriggerContractParameters shieldedTRC20TriggerContractParameters) {
            return (GrpcAPI.BytesMessage) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetTriggerInputForShieldedTRC20ContractMethod(), getCallOptions(), shieldedTRC20TriggerContractParameters);
        }

        public GrpcAPI.TransactionExtention createCommonTransaction(Protocol.Transaction transaction) {
            return (GrpcAPI.TransactionExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getCreateCommonTransactionMethod(), getCallOptions(), transaction);
        }

        public GrpcAPI.TransactionInfoList getTransactionInfoByBlockNum(GrpcAPI.NumberMessage numberMessage) {
            return (GrpcAPI.TransactionInfoList) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetTransactionInfoByBlockNumMethod(), getCallOptions(), numberMessage);
        }

        public GrpcAPI.NumberMessage getBurnTrx(GrpcAPI.EmptyMessage emptyMessage) {
            return (GrpcAPI.NumberMessage) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetBurnTrxMethod(), getCallOptions(), emptyMessage);
        }

        public Protocol.Transaction getTransactionFromPending(GrpcAPI.BytesMessage bytesMessage) {
            return (Protocol.Transaction) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetTransactionFromPendingMethod(), getCallOptions(), bytesMessage);
        }

        public GrpcAPI.TransactionIdList getTransactionListFromPending(GrpcAPI.EmptyMessage emptyMessage) {
            return (GrpcAPI.TransactionIdList) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetTransactionListFromPendingMethod(), getCallOptions(), emptyMessage);
        }

        public GrpcAPI.NumberMessage getPendingSize(GrpcAPI.EmptyMessage emptyMessage) {
            return (GrpcAPI.NumberMessage) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetPendingSizeMethod(), getCallOptions(), emptyMessage);
        }

        public GrpcAPI.BlockExtention getBlock(GrpcAPI.BlockReq blockReq) {
            return (GrpcAPI.BlockExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletGrpc.getGetBlockMethod(), getCallOptions(), blockReq);
        }
    }

    public static final class WalletFutureStub extends AbstractStub<WalletFutureStub> {
        private WalletFutureStub(Channel channel) {
            super(channel);
        }

        private WalletFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        public WalletFutureStub build(Channel channel, CallOptions callOptions) {
            return new WalletFutureStub(channel, callOptions);
        }

        public ListenableFuture<Protocol.Account> getAccount(Protocol.Account account) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetAccountMethod(), getCallOptions()), account);
        }

        public ListenableFuture<Protocol.Account> getAccountById(Protocol.Account account) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetAccountByIdMethod(), getCallOptions()), account);
        }

        public ListenableFuture<BalanceContract.AccountBalanceResponse> getAccountBalance(BalanceContract.AccountBalanceRequest accountBalanceRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetAccountBalanceMethod(), getCallOptions()), accountBalanceRequest);
        }

        public ListenableFuture<BalanceContract.BlockBalanceTrace> getBlockBalanceTrace(BalanceContract.BlockBalanceTrace.BlockIdentifier blockIdentifier) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetBlockBalanceTraceMethod(), getCallOptions()), blockIdentifier);
        }

        public ListenableFuture<Protocol.Transaction> createTransaction(BalanceContract.TransferContract transferContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getCreateTransactionMethod(), getCallOptions()), transferContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> createTransaction2(BalanceContract.TransferContract transferContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getCreateTransaction2Method(), getCallOptions()), transferContract);
        }

        public ListenableFuture<GrpcAPI.Return> broadcastTransaction(Protocol.Transaction transaction) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getBroadcastTransactionMethod(), getCallOptions()), transaction);
        }

        public ListenableFuture<Protocol.Transaction> updateAccount(AccountContract.AccountUpdateContract accountUpdateContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getUpdateAccountMethod(), getCallOptions()), accountUpdateContract);
        }

        public ListenableFuture<Protocol.Transaction> setAccountId(AccountContract.SetAccountIdContract setAccountIdContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getSetAccountIdMethod(), getCallOptions()), setAccountIdContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> updateAccount2(AccountContract.AccountUpdateContract accountUpdateContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getUpdateAccount2Method(), getCallOptions()), accountUpdateContract);
        }

        public ListenableFuture<Protocol.Transaction> voteWitnessAccount(WitnessContract.VoteWitnessContract voteWitnessContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getVoteWitnessAccountMethod(), getCallOptions()), voteWitnessContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> updateSetting(SmartContractOuterClass.UpdateSettingContract updateSettingContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getUpdateSettingMethod(), getCallOptions()), updateSettingContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> updateEnergyLimit(SmartContractOuterClass.UpdateEnergyLimitContract updateEnergyLimitContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getUpdateEnergyLimitMethod(), getCallOptions()), updateEnergyLimitContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> voteWitnessAccount2(WitnessContract.VoteWitnessContract voteWitnessContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getVoteWitnessAccount2Method(), getCallOptions()), voteWitnessContract);
        }

        public ListenableFuture<Protocol.Transaction> createAssetIssue(AssetIssueContractOuterClass.AssetIssueContract assetIssueContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getCreateAssetIssueMethod(), getCallOptions()), assetIssueContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> createAssetIssue2(AssetIssueContractOuterClass.AssetIssueContract assetIssueContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getCreateAssetIssue2Method(), getCallOptions()), assetIssueContract);
        }

        public ListenableFuture<Protocol.Transaction> updateWitness(WitnessContract.WitnessUpdateContract witnessUpdateContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getUpdateWitnessMethod(), getCallOptions()), witnessUpdateContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> updateWitness2(WitnessContract.WitnessUpdateContract witnessUpdateContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getUpdateWitness2Method(), getCallOptions()), witnessUpdateContract);
        }

        public ListenableFuture<Protocol.Transaction> createAccount(AccountContract.AccountCreateContract accountCreateContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getCreateAccountMethod(), getCallOptions()), accountCreateContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> createAccount2(AccountContract.AccountCreateContract accountCreateContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getCreateAccount2Method(), getCallOptions()), accountCreateContract);
        }

        public ListenableFuture<Protocol.Transaction> createWitness(WitnessContract.WitnessCreateContract witnessCreateContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getCreateWitnessMethod(), getCallOptions()), witnessCreateContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> createWitness2(WitnessContract.WitnessCreateContract witnessCreateContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getCreateWitness2Method(), getCallOptions()), witnessCreateContract);
        }

        public ListenableFuture<Protocol.Transaction> transferAsset(AssetIssueContractOuterClass.TransferAssetContract transferAssetContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getTransferAssetMethod(), getCallOptions()), transferAssetContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> transferAsset2(AssetIssueContractOuterClass.TransferAssetContract transferAssetContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getTransferAsset2Method(), getCallOptions()), transferAssetContract);
        }

        public ListenableFuture<Protocol.Transaction> participateAssetIssue(AssetIssueContractOuterClass.ParticipateAssetIssueContract participateAssetIssueContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getParticipateAssetIssueMethod(), getCallOptions()), participateAssetIssueContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> participateAssetIssue2(AssetIssueContractOuterClass.ParticipateAssetIssueContract participateAssetIssueContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getParticipateAssetIssue2Method(), getCallOptions()), participateAssetIssueContract);
        }

        public ListenableFuture<Protocol.Transaction> freezeBalance(BalanceContract.FreezeBalanceContract freezeBalanceContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getFreezeBalanceMethod(), getCallOptions()), freezeBalanceContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> freezeBalance2(BalanceContract.FreezeBalanceContract freezeBalanceContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getFreezeBalance2Method(), getCallOptions()), freezeBalanceContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> freezeBalanceV2(BalanceContract.FreezeBalanceV2Contract freezeBalanceV2Contract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getFreezeBalanceV2Method(), getCallOptions()), freezeBalanceV2Contract);
        }

        public ListenableFuture<Protocol.Transaction> unfreezeBalance(BalanceContract.UnfreezeBalanceContract unfreezeBalanceContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getUnfreezeBalanceMethod(), getCallOptions()), unfreezeBalanceContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> unfreezeBalance2(BalanceContract.UnfreezeBalanceContract unfreezeBalanceContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getUnfreezeBalance2Method(), getCallOptions()), unfreezeBalanceContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> unfreezeBalanceV2(BalanceContract.UnfreezeBalanceV2Contract unfreezeBalanceV2Contract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getUnfreezeBalanceV2Method(), getCallOptions()), unfreezeBalanceV2Contract);
        }

        public ListenableFuture<Protocol.Transaction> unfreezeAsset(AssetIssueContractOuterClass.UnfreezeAssetContract unfreezeAssetContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getUnfreezeAssetMethod(), getCallOptions()), unfreezeAssetContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> unfreezeAsset2(AssetIssueContractOuterClass.UnfreezeAssetContract unfreezeAssetContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getUnfreezeAsset2Method(), getCallOptions()), unfreezeAssetContract);
        }

        public ListenableFuture<Protocol.Transaction> withdrawBalance(BalanceContract.WithdrawBalanceContract withdrawBalanceContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getWithdrawBalanceMethod(), getCallOptions()), withdrawBalanceContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> withdrawBalance2(BalanceContract.WithdrawBalanceContract withdrawBalanceContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getWithdrawBalance2Method(), getCallOptions()), withdrawBalanceContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> withdrawExpireUnfreeze(BalanceContract.WithdrawExpireUnfreezeContract withdrawExpireUnfreezeContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getWithdrawExpireUnfreezeMethod(), getCallOptions()), withdrawExpireUnfreezeContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> delegateResource(BalanceContract.DelegateResourceContract delegateResourceContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getDelegateResourceMethod(), getCallOptions()), delegateResourceContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> unDelegateResource(BalanceContract.UnDelegateResourceContract unDelegateResourceContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getUnDelegateResourceMethod(), getCallOptions()), unDelegateResourceContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> cancelAllUnfreezeV2(BalanceContract.CancelAllUnfreezeV2Contract cancelAllUnfreezeV2Contract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getCancelAllUnfreezeV2Method(), getCallOptions()), cancelAllUnfreezeV2Contract);
        }

        public ListenableFuture<Protocol.Transaction> updateAsset(AssetIssueContractOuterClass.UpdateAssetContract updateAssetContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getUpdateAssetMethod(), getCallOptions()), updateAssetContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> updateAsset2(AssetIssueContractOuterClass.UpdateAssetContract updateAssetContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getUpdateAsset2Method(), getCallOptions()), updateAssetContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> proposalCreate(ProposalContract.ProposalCreateContract proposalCreateContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getProposalCreateMethod(), getCallOptions()), proposalCreateContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> proposalApprove(ProposalContract.ProposalApproveContract proposalApproveContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getProposalApproveMethod(), getCallOptions()), proposalApproveContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> proposalDelete(ProposalContract.ProposalDeleteContract proposalDeleteContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getProposalDeleteMethod(), getCallOptions()), proposalDeleteContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> buyStorage(StorageContract.BuyStorageContract buyStorageContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getBuyStorageMethod(), getCallOptions()), buyStorageContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> buyStorageBytes(StorageContract.BuyStorageBytesContract buyStorageBytesContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getBuyStorageBytesMethod(), getCallOptions()), buyStorageBytesContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> sellStorage(StorageContract.SellStorageContract sellStorageContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getSellStorageMethod(), getCallOptions()), sellStorageContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> exchangeCreate(ExchangeContract.ExchangeCreateContract exchangeCreateContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getExchangeCreateMethod(), getCallOptions()), exchangeCreateContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> exchangeInject(ExchangeContract.ExchangeInjectContract exchangeInjectContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getExchangeInjectMethod(), getCallOptions()), exchangeInjectContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> exchangeWithdraw(ExchangeContract.ExchangeWithdrawContract exchangeWithdrawContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getExchangeWithdrawMethod(), getCallOptions()), exchangeWithdrawContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> exchangeTransaction(ExchangeContract.ExchangeTransactionContract exchangeTransactionContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getExchangeTransactionMethod(), getCallOptions()), exchangeTransactionContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> marketSellAsset(MarketContract.MarketSellAssetContract marketSellAssetContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getMarketSellAssetMethod(), getCallOptions()), marketSellAssetContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> marketCancelOrder(MarketContract.MarketCancelOrderContract marketCancelOrderContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getMarketCancelOrderMethod(), getCallOptions()), marketCancelOrderContract);
        }

        public ListenableFuture<Protocol.MarketOrder> getMarketOrderById(GrpcAPI.BytesMessage bytesMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetMarketOrderByIdMethod(), getCallOptions()), bytesMessage);
        }

        public ListenableFuture<Protocol.MarketOrderList> getMarketOrderByAccount(GrpcAPI.BytesMessage bytesMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetMarketOrderByAccountMethod(), getCallOptions()), bytesMessage);
        }

        public ListenableFuture<Protocol.MarketPriceList> getMarketPriceByPair(Protocol.MarketOrderPair marketOrderPair) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetMarketPriceByPairMethod(), getCallOptions()), marketOrderPair);
        }

        public ListenableFuture<Protocol.MarketOrderList> getMarketOrderListByPair(Protocol.MarketOrderPair marketOrderPair) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetMarketOrderListByPairMethod(), getCallOptions()), marketOrderPair);
        }

        public ListenableFuture<Protocol.MarketOrderPairList> getMarketPairList(GrpcAPI.EmptyMessage emptyMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetMarketPairListMethod(), getCallOptions()), emptyMessage);
        }

        public ListenableFuture<GrpcAPI.NodeList> listNodes(GrpcAPI.EmptyMessage emptyMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getListNodesMethod(), getCallOptions()), emptyMessage);
        }

        public ListenableFuture<GrpcAPI.AssetIssueList> getAssetIssueByAccount(Protocol.Account account) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetAssetIssueByAccountMethod(), getCallOptions()), account);
        }

        public ListenableFuture<GrpcAPI.AccountNetMessage> getAccountNet(Protocol.Account account) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetAccountNetMethod(), getCallOptions()), account);
        }

        public ListenableFuture<GrpcAPI.AccountResourceMessage> getAccountResource(Protocol.Account account) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetAccountResourceMethod(), getCallOptions()), account);
        }

        public ListenableFuture<AssetIssueContractOuterClass.AssetIssueContract> getAssetIssueByName(GrpcAPI.BytesMessage bytesMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetAssetIssueByNameMethod(), getCallOptions()), bytesMessage);
        }

        public ListenableFuture<GrpcAPI.AssetIssueList> getAssetIssueListByName(GrpcAPI.BytesMessage bytesMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetAssetIssueListByNameMethod(), getCallOptions()), bytesMessage);
        }

        public ListenableFuture<AssetIssueContractOuterClass.AssetIssueContract> getAssetIssueById(GrpcAPI.BytesMessage bytesMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetAssetIssueByIdMethod(), getCallOptions()), bytesMessage);
        }

        public ListenableFuture<Protocol.Block> getNowBlock(GrpcAPI.EmptyMessage emptyMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetNowBlockMethod(), getCallOptions()), emptyMessage);
        }

        public ListenableFuture<GrpcAPI.BlockExtention> getNowBlock2(GrpcAPI.EmptyMessage emptyMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetNowBlock2Method(), getCallOptions()), emptyMessage);
        }

        public ListenableFuture<Protocol.Block> getBlockByNum(GrpcAPI.NumberMessage numberMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetBlockByNumMethod(), getCallOptions()), numberMessage);
        }

        public ListenableFuture<GrpcAPI.BlockExtention> getBlockByNum2(GrpcAPI.NumberMessage numberMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetBlockByNum2Method(), getCallOptions()), numberMessage);
        }

        public ListenableFuture<GrpcAPI.NumberMessage> getTransactionCountByBlockNum(GrpcAPI.NumberMessage numberMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetTransactionCountByBlockNumMethod(), getCallOptions()), numberMessage);
        }

        public ListenableFuture<Protocol.Block> getBlockById(GrpcAPI.BytesMessage bytesMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetBlockByIdMethod(), getCallOptions()), bytesMessage);
        }

        public ListenableFuture<GrpcAPI.BlockList> getBlockByLimitNext(GrpcAPI.BlockLimit blockLimit) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetBlockByLimitNextMethod(), getCallOptions()), blockLimit);
        }

        public ListenableFuture<GrpcAPI.BlockListExtention> getBlockByLimitNext2(GrpcAPI.BlockLimit blockLimit) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetBlockByLimitNext2Method(), getCallOptions()), blockLimit);
        }

        public ListenableFuture<GrpcAPI.BlockList> getBlockByLatestNum(GrpcAPI.NumberMessage numberMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetBlockByLatestNumMethod(), getCallOptions()), numberMessage);
        }

        public ListenableFuture<GrpcAPI.BlockListExtention> getBlockByLatestNum2(GrpcAPI.NumberMessage numberMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetBlockByLatestNum2Method(), getCallOptions()), numberMessage);
        }

        public ListenableFuture<Protocol.Transaction> getTransactionById(GrpcAPI.BytesMessage bytesMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetTransactionByIdMethod(), getCallOptions()), bytesMessage);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> deployContract(SmartContractOuterClass.CreateSmartContract createSmartContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getDeployContractMethod(), getCallOptions()), createSmartContract);
        }

        public ListenableFuture<SmartContractOuterClass.SmartContract> getContract(GrpcAPI.BytesMessage bytesMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetContractMethod(), getCallOptions()), bytesMessage);
        }

        public ListenableFuture<SmartContractOuterClass.SmartContractDataWrapper> getContractInfo(GrpcAPI.BytesMessage bytesMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetContractInfoMethod(), getCallOptions()), bytesMessage);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> triggerContract(SmartContractOuterClass.TriggerSmartContract triggerSmartContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getTriggerContractMethod(), getCallOptions()), triggerSmartContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> triggerConstantContract(SmartContractOuterClass.TriggerSmartContract triggerSmartContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getTriggerConstantContractMethod(), getCallOptions()), triggerSmartContract);
        }

        public ListenableFuture<GrpcAPI.EstimateEnergyMessage> estimateEnergy(SmartContractOuterClass.TriggerSmartContract triggerSmartContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getEstimateEnergyMethod(), getCallOptions()), triggerSmartContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> clearContractABI(SmartContractOuterClass.ClearABIContract clearABIContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getClearContractABIMethod(), getCallOptions()), clearABIContract);
        }

        public ListenableFuture<GrpcAPI.WitnessList> listWitnesses(GrpcAPI.EmptyMessage emptyMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getListWitnessesMethod(), getCallOptions()), emptyMessage);
        }

        public ListenableFuture<GrpcAPI.DelegatedResourceList> getDelegatedResource(GrpcAPI.DelegatedResourceMessage delegatedResourceMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetDelegatedResourceMethod(), getCallOptions()), delegatedResourceMessage);
        }

        public ListenableFuture<GrpcAPI.DelegatedResourceList> getDelegatedResourceV2(GrpcAPI.DelegatedResourceMessage delegatedResourceMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetDelegatedResourceV2Method(), getCallOptions()), delegatedResourceMessage);
        }

        public ListenableFuture<Protocol.DelegatedResourceAccountIndex> getDelegatedResourceAccountIndex(GrpcAPI.BytesMessage bytesMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetDelegatedResourceAccountIndexMethod(), getCallOptions()), bytesMessage);
        }

        public ListenableFuture<Protocol.DelegatedResourceAccountIndex> getDelegatedResourceAccountIndexV2(GrpcAPI.BytesMessage bytesMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetDelegatedResourceAccountIndexV2Method(), getCallOptions()), bytesMessage);
        }

        public ListenableFuture<GrpcAPI.CanDelegatedMaxSizeResponseMessage> getCanDelegatedMaxSize(GrpcAPI.CanDelegatedMaxSizeRequestMessage canDelegatedMaxSizeRequestMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetCanDelegatedMaxSizeMethod(), getCallOptions()), canDelegatedMaxSizeRequestMessage);
        }

        public ListenableFuture<GrpcAPI.GetAvailableUnfreezeCountResponseMessage> getAvailableUnfreezeCount(GrpcAPI.GetAvailableUnfreezeCountRequestMessage getAvailableUnfreezeCountRequestMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetAvailableUnfreezeCountMethod(), getCallOptions()), getAvailableUnfreezeCountRequestMessage);
        }

        public ListenableFuture<GrpcAPI.CanWithdrawUnfreezeAmountResponseMessage> getCanWithdrawUnfreezeAmount(GrpcAPI.CanWithdrawUnfreezeAmountRequestMessage canWithdrawUnfreezeAmountRequestMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetCanWithdrawUnfreezeAmountMethod(), getCallOptions()), canWithdrawUnfreezeAmountRequestMessage);
        }

        public ListenableFuture<GrpcAPI.ProposalList> listProposals(GrpcAPI.EmptyMessage emptyMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getListProposalsMethod(), getCallOptions()), emptyMessage);
        }

        public ListenableFuture<GrpcAPI.ProposalList> getPaginatedProposalList(GrpcAPI.PaginatedMessage paginatedMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetPaginatedProposalListMethod(), getCallOptions()), paginatedMessage);
        }

        public ListenableFuture<Protocol.Proposal> getProposalById(GrpcAPI.BytesMessage bytesMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetProposalByIdMethod(), getCallOptions()), bytesMessage);
        }

        public ListenableFuture<GrpcAPI.ExchangeList> listExchanges(GrpcAPI.EmptyMessage emptyMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getListExchangesMethod(), getCallOptions()), emptyMessage);
        }

        public ListenableFuture<GrpcAPI.ExchangeList> getPaginatedExchangeList(GrpcAPI.PaginatedMessage paginatedMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetPaginatedExchangeListMethod(), getCallOptions()), paginatedMessage);
        }

        public ListenableFuture<Protocol.Exchange> getExchangeById(GrpcAPI.BytesMessage bytesMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetExchangeByIdMethod(), getCallOptions()), bytesMessage);
        }

        public ListenableFuture<Protocol.ChainParameters> getChainParameters(GrpcAPI.EmptyMessage emptyMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetChainParametersMethod(), getCallOptions()), emptyMessage);
        }

        public ListenableFuture<GrpcAPI.AssetIssueList> getAssetIssueList(GrpcAPI.EmptyMessage emptyMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetAssetIssueListMethod(), getCallOptions()), emptyMessage);
        }

        public ListenableFuture<GrpcAPI.AssetIssueList> getPaginatedAssetIssueList(GrpcAPI.PaginatedMessage paginatedMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetPaginatedAssetIssueListMethod(), getCallOptions()), paginatedMessage);
        }

        public ListenableFuture<GrpcAPI.NumberMessage> totalTransaction(GrpcAPI.EmptyMessage emptyMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getTotalTransactionMethod(), getCallOptions()), emptyMessage);
        }

        public ListenableFuture<GrpcAPI.NumberMessage> getNextMaintenanceTime(GrpcAPI.EmptyMessage emptyMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetNextMaintenanceTimeMethod(), getCallOptions()), emptyMessage);
        }

        public ListenableFuture<Protocol.TransactionInfo> getTransactionInfoById(GrpcAPI.BytesMessage bytesMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetTransactionInfoByIdMethod(), getCallOptions()), bytesMessage);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> accountPermissionUpdate(AccountContract.AccountPermissionUpdateContract accountPermissionUpdateContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getAccountPermissionUpdateMethod(), getCallOptions()), accountPermissionUpdateContract);
        }

        public ListenableFuture<GrpcAPI.TransactionSignWeight> getTransactionSignWeight(Protocol.Transaction transaction) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetTransactionSignWeightMethod(), getCallOptions()), transaction);
        }

        public ListenableFuture<GrpcAPI.TransactionApprovedList> getTransactionApprovedList(Protocol.Transaction transaction) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetTransactionApprovedListMethod(), getCallOptions()), transaction);
        }

        public ListenableFuture<Protocol.NodeInfo> getNodeInfo(GrpcAPI.EmptyMessage emptyMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetNodeInfoMethod(), getCallOptions()), emptyMessage);
        }

        public ListenableFuture<GrpcAPI.NumberMessage> getRewardInfo(GrpcAPI.BytesMessage bytesMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetRewardInfoMethod(), getCallOptions()), bytesMessage);
        }

        public ListenableFuture<GrpcAPI.NumberMessage> getBrokerageInfo(GrpcAPI.BytesMessage bytesMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetBrokerageInfoMethod(), getCallOptions()), bytesMessage);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> updateBrokerage(StorageContract.UpdateBrokerageContract updateBrokerageContract) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getUpdateBrokerageMethod(), getCallOptions()), updateBrokerageContract);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> createShieldedTransaction(GrpcAPI.PrivateParameters privateParameters) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getCreateShieldedTransactionMethod(), getCallOptions()), privateParameters);
        }

        public ListenableFuture<ShieldContract.IncrementalMerkleVoucherInfo> getMerkleTreeVoucherInfo(ShieldContract.OutputPointInfo outputPointInfo) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetMerkleTreeVoucherInfoMethod(), getCallOptions()), outputPointInfo);
        }

        public ListenableFuture<GrpcAPI.DecryptNotes> scanNoteByIvk(GrpcAPI.IvkDecryptParameters ivkDecryptParameters) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getScanNoteByIvkMethod(), getCallOptions()), ivkDecryptParameters);
        }

        public ListenableFuture<GrpcAPI.DecryptNotesMarked> scanAndMarkNoteByIvk(GrpcAPI.IvkDecryptAndMarkParameters ivkDecryptAndMarkParameters) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getScanAndMarkNoteByIvkMethod(), getCallOptions()), ivkDecryptAndMarkParameters);
        }

        public ListenableFuture<GrpcAPI.DecryptNotes> scanNoteByOvk(GrpcAPI.OvkDecryptParameters ovkDecryptParameters) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getScanNoteByOvkMethod(), getCallOptions()), ovkDecryptParameters);
        }

        public ListenableFuture<GrpcAPI.BytesMessage> getSpendingKey(GrpcAPI.EmptyMessage emptyMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetSpendingKeyMethod(), getCallOptions()), emptyMessage);
        }

        public ListenableFuture<GrpcAPI.ExpandedSpendingKeyMessage> getExpandedSpendingKey(GrpcAPI.BytesMessage bytesMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetExpandedSpendingKeyMethod(), getCallOptions()), bytesMessage);
        }

        public ListenableFuture<GrpcAPI.BytesMessage> getAkFromAsk(GrpcAPI.BytesMessage bytesMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetAkFromAskMethod(), getCallOptions()), bytesMessage);
        }

        public ListenableFuture<GrpcAPI.BytesMessage> getNkFromNsk(GrpcAPI.BytesMessage bytesMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetNkFromNskMethod(), getCallOptions()), bytesMessage);
        }

        public ListenableFuture<GrpcAPI.IncomingViewingKeyMessage> getIncomingViewingKey(GrpcAPI.ViewingKeyMessage viewingKeyMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetIncomingViewingKeyMethod(), getCallOptions()), viewingKeyMessage);
        }

        public ListenableFuture<GrpcAPI.DiversifierMessage> getDiversifier(GrpcAPI.EmptyMessage emptyMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetDiversifierMethod(), getCallOptions()), emptyMessage);
        }

        public ListenableFuture<GrpcAPI.ShieldedAddressInfo> getNewShieldedAddress(GrpcAPI.EmptyMessage emptyMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetNewShieldedAddressMethod(), getCallOptions()), emptyMessage);
        }

        public ListenableFuture<GrpcAPI.PaymentAddressMessage> getZenPaymentAddress(GrpcAPI.IncomingViewingKeyDiversifierMessage incomingViewingKeyDiversifierMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetZenPaymentAddressMethod(), getCallOptions()), incomingViewingKeyDiversifierMessage);
        }

        public ListenableFuture<GrpcAPI.BytesMessage> getRcm(GrpcAPI.EmptyMessage emptyMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetRcmMethod(), getCallOptions()), emptyMessage);
        }

        public ListenableFuture<GrpcAPI.SpendResult> isSpend(GrpcAPI.NoteParameters noteParameters) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getIsSpendMethod(), getCallOptions()), noteParameters);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> createShieldedTransactionWithoutSpendAuthSig(GrpcAPI.PrivateParametersWithoutAsk privateParametersWithoutAsk) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getCreateShieldedTransactionWithoutSpendAuthSigMethod(), getCallOptions()), privateParametersWithoutAsk);
        }

        public ListenableFuture<GrpcAPI.BytesMessage> getShieldTransactionHash(Protocol.Transaction transaction) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetShieldTransactionHashMethod(), getCallOptions()), transaction);
        }

        public ListenableFuture<GrpcAPI.BytesMessage> createSpendAuthSig(GrpcAPI.SpendAuthSigParameters spendAuthSigParameters) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getCreateSpendAuthSigMethod(), getCallOptions()), spendAuthSigParameters);
        }

        public ListenableFuture<GrpcAPI.BytesMessage> createShieldNullifier(GrpcAPI.NfParameters nfParameters) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getCreateShieldNullifierMethod(), getCallOptions()), nfParameters);
        }

        public ListenableFuture<GrpcAPI.ShieldedTRC20Parameters> createShieldedContractParameters(GrpcAPI.PrivateShieldedTRC20Parameters privateShieldedTRC20Parameters) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getCreateShieldedContractParametersMethod(), getCallOptions()), privateShieldedTRC20Parameters);
        }

        public ListenableFuture<GrpcAPI.ShieldedTRC20Parameters> createShieldedContractParametersWithoutAsk(GrpcAPI.PrivateShieldedTRC20ParametersWithoutAsk privateShieldedTRC20ParametersWithoutAsk) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getCreateShieldedContractParametersWithoutAskMethod(), getCallOptions()), privateShieldedTRC20ParametersWithoutAsk);
        }

        public ListenableFuture<GrpcAPI.DecryptNotesTRC20> scanShieldedTRC20NotesByIvk(GrpcAPI.IvkDecryptTRC20Parameters ivkDecryptTRC20Parameters) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getScanShieldedTRC20NotesByIvkMethod(), getCallOptions()), ivkDecryptTRC20Parameters);
        }

        public ListenableFuture<GrpcAPI.DecryptNotesTRC20> scanShieldedTRC20NotesByOvk(GrpcAPI.OvkDecryptTRC20Parameters ovkDecryptTRC20Parameters) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getScanShieldedTRC20NotesByOvkMethod(), getCallOptions()), ovkDecryptTRC20Parameters);
        }

        public ListenableFuture<GrpcAPI.NullifierResult> isShieldedTRC20ContractNoteSpent(GrpcAPI.NfTRC20Parameters nfTRC20Parameters) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getIsShieldedTRC20ContractNoteSpentMethod(), getCallOptions()), nfTRC20Parameters);
        }

        public ListenableFuture<GrpcAPI.BytesMessage> getTriggerInputForShieldedTRC20Contract(GrpcAPI.ShieldedTRC20TriggerContractParameters shieldedTRC20TriggerContractParameters) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetTriggerInputForShieldedTRC20ContractMethod(), getCallOptions()), shieldedTRC20TriggerContractParameters);
        }

        public ListenableFuture<GrpcAPI.TransactionExtention> createCommonTransaction(Protocol.Transaction transaction) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getCreateCommonTransactionMethod(), getCallOptions()), transaction);
        }

        public ListenableFuture<GrpcAPI.TransactionInfoList> getTransactionInfoByBlockNum(GrpcAPI.NumberMessage numberMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetTransactionInfoByBlockNumMethod(), getCallOptions()), numberMessage);
        }

        public ListenableFuture<GrpcAPI.NumberMessage> getBurnTrx(GrpcAPI.EmptyMessage emptyMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetBurnTrxMethod(), getCallOptions()), emptyMessage);
        }

        public ListenableFuture<Protocol.Transaction> getTransactionFromPending(GrpcAPI.BytesMessage bytesMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetTransactionFromPendingMethod(), getCallOptions()), bytesMessage);
        }

        public ListenableFuture<GrpcAPI.TransactionIdList> getTransactionListFromPending(GrpcAPI.EmptyMessage emptyMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetTransactionListFromPendingMethod(), getCallOptions()), emptyMessage);
        }

        public ListenableFuture<GrpcAPI.NumberMessage> getPendingSize(GrpcAPI.EmptyMessage emptyMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetPendingSizeMethod(), getCallOptions()), emptyMessage);
        }

        public ListenableFuture<GrpcAPI.BlockExtention> getBlock(GrpcAPI.BlockReq blockReq) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletGrpc.getGetBlockMethod(), getCallOptions()), blockReq);
        }
    }

    private static final class MethodHandlers<Req, Resp> implements ServerCalls.UnaryMethod<Req, Resp>, ServerCalls.ServerStreamingMethod<Req, Resp>, ServerCalls.ClientStreamingMethod<Req, Resp>, ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final WalletImplBase serviceImpl;

        MethodHandlers(WalletImplBase walletImplBase, int i) {
            this.serviceImpl = walletImplBase;
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
                    this.serviceImpl.getAccountBalance((BalanceContract.AccountBalanceRequest) req, streamObserver);
                    return;
                case 3:
                    this.serviceImpl.getBlockBalanceTrace((BalanceContract.BlockBalanceTrace.BlockIdentifier) req, streamObserver);
                    return;
                case 4:
                    this.serviceImpl.createTransaction((BalanceContract.TransferContract) req, streamObserver);
                    return;
                case 5:
                    this.serviceImpl.createTransaction2((BalanceContract.TransferContract) req, streamObserver);
                    return;
                case 6:
                    this.serviceImpl.broadcastTransaction((Protocol.Transaction) req, streamObserver);
                    return;
                case 7:
                    this.serviceImpl.updateAccount((AccountContract.AccountUpdateContract) req, streamObserver);
                    return;
                case 8:
                    this.serviceImpl.setAccountId((AccountContract.SetAccountIdContract) req, streamObserver);
                    return;
                case 9:
                    this.serviceImpl.updateAccount2((AccountContract.AccountUpdateContract) req, streamObserver);
                    return;
                case 10:
                    this.serviceImpl.voteWitnessAccount((WitnessContract.VoteWitnessContract) req, streamObserver);
                    return;
                case 11:
                    this.serviceImpl.updateSetting((SmartContractOuterClass.UpdateSettingContract) req, streamObserver);
                    return;
                case 12:
                    this.serviceImpl.updateEnergyLimit((SmartContractOuterClass.UpdateEnergyLimitContract) req, streamObserver);
                    return;
                case 13:
                    this.serviceImpl.voteWitnessAccount2((WitnessContract.VoteWitnessContract) req, streamObserver);
                    return;
                case 14:
                    this.serviceImpl.createAssetIssue((AssetIssueContractOuterClass.AssetIssueContract) req, streamObserver);
                    return;
                case 15:
                    this.serviceImpl.createAssetIssue2((AssetIssueContractOuterClass.AssetIssueContract) req, streamObserver);
                    return;
                case 16:
                    this.serviceImpl.updateWitness((WitnessContract.WitnessUpdateContract) req, streamObserver);
                    return;
                case 17:
                    this.serviceImpl.updateWitness2((WitnessContract.WitnessUpdateContract) req, streamObserver);
                    return;
                case 18:
                    this.serviceImpl.createAccount((AccountContract.AccountCreateContract) req, streamObserver);
                    return;
                case 19:
                    this.serviceImpl.createAccount2((AccountContract.AccountCreateContract) req, streamObserver);
                    return;
                case 20:
                    this.serviceImpl.createWitness((WitnessContract.WitnessCreateContract) req, streamObserver);
                    return;
                case 21:
                    this.serviceImpl.createWitness2((WitnessContract.WitnessCreateContract) req, streamObserver);
                    return;
                case 22:
                    this.serviceImpl.transferAsset((AssetIssueContractOuterClass.TransferAssetContract) req, streamObserver);
                    return;
                case 23:
                    this.serviceImpl.transferAsset2((AssetIssueContractOuterClass.TransferAssetContract) req, streamObserver);
                    return;
                case 24:
                    this.serviceImpl.participateAssetIssue((AssetIssueContractOuterClass.ParticipateAssetIssueContract) req, streamObserver);
                    return;
                case 25:
                    this.serviceImpl.participateAssetIssue2((AssetIssueContractOuterClass.ParticipateAssetIssueContract) req, streamObserver);
                    return;
                case 26:
                    this.serviceImpl.freezeBalance((BalanceContract.FreezeBalanceContract) req, streamObserver);
                    return;
                case 27:
                    this.serviceImpl.freezeBalance2((BalanceContract.FreezeBalanceContract) req, streamObserver);
                    return;
                case 28:
                    this.serviceImpl.freezeBalanceV2((BalanceContract.FreezeBalanceV2Contract) req, streamObserver);
                    return;
                case 29:
                    this.serviceImpl.unfreezeBalance((BalanceContract.UnfreezeBalanceContract) req, streamObserver);
                    return;
                case 30:
                    this.serviceImpl.unfreezeBalance2((BalanceContract.UnfreezeBalanceContract) req, streamObserver);
                    return;
                case 31:
                    this.serviceImpl.unfreezeBalanceV2((BalanceContract.UnfreezeBalanceV2Contract) req, streamObserver);
                    return;
                case 32:
                    this.serviceImpl.unfreezeAsset((AssetIssueContractOuterClass.UnfreezeAssetContract) req, streamObserver);
                    return;
                case 33:
                    this.serviceImpl.unfreezeAsset2((AssetIssueContractOuterClass.UnfreezeAssetContract) req, streamObserver);
                    return;
                case 34:
                    this.serviceImpl.withdrawBalance((BalanceContract.WithdrawBalanceContract) req, streamObserver);
                    return;
                case 35:
                    this.serviceImpl.withdrawBalance2((BalanceContract.WithdrawBalanceContract) req, streamObserver);
                    return;
                case 36:
                    this.serviceImpl.withdrawExpireUnfreeze((BalanceContract.WithdrawExpireUnfreezeContract) req, streamObserver);
                    return;
                case 37:
                    this.serviceImpl.delegateResource((BalanceContract.DelegateResourceContract) req, streamObserver);
                    return;
                case 38:
                    this.serviceImpl.unDelegateResource((BalanceContract.UnDelegateResourceContract) req, streamObserver);
                    return;
                case 39:
                    this.serviceImpl.cancelAllUnfreezeV2((BalanceContract.CancelAllUnfreezeV2Contract) req, streamObserver);
                    return;
                case 40:
                    this.serviceImpl.updateAsset((AssetIssueContractOuterClass.UpdateAssetContract) req, streamObserver);
                    return;
                case 41:
                    this.serviceImpl.updateAsset2((AssetIssueContractOuterClass.UpdateAssetContract) req, streamObserver);
                    return;
                case 42:
                    this.serviceImpl.proposalCreate((ProposalContract.ProposalCreateContract) req, streamObserver);
                    return;
                case 43:
                    this.serviceImpl.proposalApprove((ProposalContract.ProposalApproveContract) req, streamObserver);
                    return;
                case 44:
                    this.serviceImpl.proposalDelete((ProposalContract.ProposalDeleteContract) req, streamObserver);
                    return;
                case 45:
                    this.serviceImpl.buyStorage((StorageContract.BuyStorageContract) req, streamObserver);
                    return;
                case 46:
                    this.serviceImpl.buyStorageBytes((StorageContract.BuyStorageBytesContract) req, streamObserver);
                    return;
                case 47:
                    this.serviceImpl.sellStorage((StorageContract.SellStorageContract) req, streamObserver);
                    return;
                case 48:
                    this.serviceImpl.exchangeCreate((ExchangeContract.ExchangeCreateContract) req, streamObserver);
                    return;
                case 49:
                    this.serviceImpl.exchangeInject((ExchangeContract.ExchangeInjectContract) req, streamObserver);
                    return;
                case 50:
                    this.serviceImpl.exchangeWithdraw((ExchangeContract.ExchangeWithdrawContract) req, streamObserver);
                    return;
                case 51:
                    this.serviceImpl.exchangeTransaction((ExchangeContract.ExchangeTransactionContract) req, streamObserver);
                    return;
                case 52:
                    this.serviceImpl.marketSellAsset((MarketContract.MarketSellAssetContract) req, streamObserver);
                    return;
                case 53:
                    this.serviceImpl.marketCancelOrder((MarketContract.MarketCancelOrderContract) req, streamObserver);
                    return;
                case 54:
                    this.serviceImpl.getMarketOrderById((GrpcAPI.BytesMessage) req, streamObserver);
                    return;
                case 55:
                    this.serviceImpl.getMarketOrderByAccount((GrpcAPI.BytesMessage) req, streamObserver);
                    return;
                case 56:
                    this.serviceImpl.getMarketPriceByPair((Protocol.MarketOrderPair) req, streamObserver);
                    return;
                case 57:
                    this.serviceImpl.getMarketOrderListByPair((Protocol.MarketOrderPair) req, streamObserver);
                    return;
                case 58:
                    this.serviceImpl.getMarketPairList((GrpcAPI.EmptyMessage) req, streamObserver);
                    return;
                case 59:
                    this.serviceImpl.listNodes((GrpcAPI.EmptyMessage) req, streamObserver);
                    return;
                case 60:
                    this.serviceImpl.getAssetIssueByAccount((Protocol.Account) req, streamObserver);
                    return;
                case 61:
                    this.serviceImpl.getAccountNet((Protocol.Account) req, streamObserver);
                    return;
                case 62:
                    this.serviceImpl.getAccountResource((Protocol.Account) req, streamObserver);
                    return;
                case 63:
                    this.serviceImpl.getAssetIssueByName((GrpcAPI.BytesMessage) req, streamObserver);
                    return;
                case 64:
                    this.serviceImpl.getAssetIssueListByName((GrpcAPI.BytesMessage) req, streamObserver);
                    return;
                case 65:
                    this.serviceImpl.getAssetIssueById((GrpcAPI.BytesMessage) req, streamObserver);
                    return;
                case 66:
                    this.serviceImpl.getNowBlock((GrpcAPI.EmptyMessage) req, streamObserver);
                    return;
                case 67:
                    this.serviceImpl.getNowBlock2((GrpcAPI.EmptyMessage) req, streamObserver);
                    return;
                case 68:
                    this.serviceImpl.getBlockByNum((GrpcAPI.NumberMessage) req, streamObserver);
                    return;
                case 69:
                    this.serviceImpl.getBlockByNum2((GrpcAPI.NumberMessage) req, streamObserver);
                    return;
                case 70:
                    this.serviceImpl.getTransactionCountByBlockNum((GrpcAPI.NumberMessage) req, streamObserver);
                    return;
                case 71:
                    this.serviceImpl.getBlockById((GrpcAPI.BytesMessage) req, streamObserver);
                    return;
                case 72:
                    this.serviceImpl.getBlockByLimitNext((GrpcAPI.BlockLimit) req, streamObserver);
                    return;
                case 73:
                    this.serviceImpl.getBlockByLimitNext2((GrpcAPI.BlockLimit) req, streamObserver);
                    return;
                case 74:
                    this.serviceImpl.getBlockByLatestNum((GrpcAPI.NumberMessage) req, streamObserver);
                    return;
                case 75:
                    this.serviceImpl.getBlockByLatestNum2((GrpcAPI.NumberMessage) req, streamObserver);
                    return;
                case 76:
                    this.serviceImpl.getTransactionById((GrpcAPI.BytesMessage) req, streamObserver);
                    return;
                case 77:
                    this.serviceImpl.deployContract((SmartContractOuterClass.CreateSmartContract) req, streamObserver);
                    return;
                case 78:
                    this.serviceImpl.getContract((GrpcAPI.BytesMessage) req, streamObserver);
                    return;
                case 79:
                    this.serviceImpl.getContractInfo((GrpcAPI.BytesMessage) req, streamObserver);
                    return;
                case 80:
                    this.serviceImpl.triggerContract((SmartContractOuterClass.TriggerSmartContract) req, streamObserver);
                    return;
                case 81:
                    this.serviceImpl.triggerConstantContract((SmartContractOuterClass.TriggerSmartContract) req, streamObserver);
                    return;
                case 82:
                    this.serviceImpl.estimateEnergy((SmartContractOuterClass.TriggerSmartContract) req, streamObserver);
                    return;
                case 83:
                    this.serviceImpl.clearContractABI((SmartContractOuterClass.ClearABIContract) req, streamObserver);
                    return;
                case 84:
                    this.serviceImpl.listWitnesses((GrpcAPI.EmptyMessage) req, streamObserver);
                    return;
                case 85:
                    this.serviceImpl.getDelegatedResource((GrpcAPI.DelegatedResourceMessage) req, streamObserver);
                    return;
                case 86:
                    this.serviceImpl.getDelegatedResourceV2((GrpcAPI.DelegatedResourceMessage) req, streamObserver);
                    return;
                case 87:
                    this.serviceImpl.getDelegatedResourceAccountIndex((GrpcAPI.BytesMessage) req, streamObserver);
                    return;
                case 88:
                    this.serviceImpl.getDelegatedResourceAccountIndexV2((GrpcAPI.BytesMessage) req, streamObserver);
                    return;
                case 89:
                    this.serviceImpl.getCanDelegatedMaxSize((GrpcAPI.CanDelegatedMaxSizeRequestMessage) req, streamObserver);
                    return;
                case 90:
                    this.serviceImpl.getAvailableUnfreezeCount((GrpcAPI.GetAvailableUnfreezeCountRequestMessage) req, streamObserver);
                    return;
                case 91:
                    this.serviceImpl.getCanWithdrawUnfreezeAmount((GrpcAPI.CanWithdrawUnfreezeAmountRequestMessage) req, streamObserver);
                    return;
                case 92:
                    this.serviceImpl.listProposals((GrpcAPI.EmptyMessage) req, streamObserver);
                    return;
                case 93:
                    this.serviceImpl.getPaginatedProposalList((GrpcAPI.PaginatedMessage) req, streamObserver);
                    return;
                case 94:
                    this.serviceImpl.getProposalById((GrpcAPI.BytesMessage) req, streamObserver);
                    return;
                case 95:
                    this.serviceImpl.listExchanges((GrpcAPI.EmptyMessage) req, streamObserver);
                    return;
                case 96:
                    this.serviceImpl.getPaginatedExchangeList((GrpcAPI.PaginatedMessage) req, streamObserver);
                    return;
                case 97:
                    this.serviceImpl.getExchangeById((GrpcAPI.BytesMessage) req, streamObserver);
                    return;
                case 98:
                    this.serviceImpl.getChainParameters((GrpcAPI.EmptyMessage) req, streamObserver);
                    return;
                case 99:
                    this.serviceImpl.getAssetIssueList((GrpcAPI.EmptyMessage) req, streamObserver);
                    return;
                case 100:
                    this.serviceImpl.getPaginatedAssetIssueList((GrpcAPI.PaginatedMessage) req, streamObserver);
                    return;
                case 101:
                    this.serviceImpl.totalTransaction((GrpcAPI.EmptyMessage) req, streamObserver);
                    return;
                case 102:
                    this.serviceImpl.getNextMaintenanceTime((GrpcAPI.EmptyMessage) req, streamObserver);
                    return;
                case 103:
                    this.serviceImpl.getTransactionInfoById((GrpcAPI.BytesMessage) req, streamObserver);
                    return;
                case 104:
                    this.serviceImpl.accountPermissionUpdate((AccountContract.AccountPermissionUpdateContract) req, streamObserver);
                    return;
                case 105:
                    this.serviceImpl.getTransactionSignWeight((Protocol.Transaction) req, streamObserver);
                    return;
                case 106:
                    this.serviceImpl.getTransactionApprovedList((Protocol.Transaction) req, streamObserver);
                    return;
                case 107:
                    this.serviceImpl.getNodeInfo((GrpcAPI.EmptyMessage) req, streamObserver);
                    return;
                case 108:
                    this.serviceImpl.getRewardInfo((GrpcAPI.BytesMessage) req, streamObserver);
                    return;
                case 109:
                    this.serviceImpl.getBrokerageInfo((GrpcAPI.BytesMessage) req, streamObserver);
                    return;
                case 110:
                    this.serviceImpl.updateBrokerage((StorageContract.UpdateBrokerageContract) req, streamObserver);
                    return;
                case 111:
                    this.serviceImpl.createShieldedTransaction((GrpcAPI.PrivateParameters) req, streamObserver);
                    return;
                case 112:
                    this.serviceImpl.getMerkleTreeVoucherInfo((ShieldContract.OutputPointInfo) req, streamObserver);
                    return;
                case 113:
                    this.serviceImpl.scanNoteByIvk((GrpcAPI.IvkDecryptParameters) req, streamObserver);
                    return;
                case 114:
                    this.serviceImpl.scanAndMarkNoteByIvk((GrpcAPI.IvkDecryptAndMarkParameters) req, streamObserver);
                    return;
                case 115:
                    this.serviceImpl.scanNoteByOvk((GrpcAPI.OvkDecryptParameters) req, streamObserver);
                    return;
                case 116:
                    this.serviceImpl.getSpendingKey((GrpcAPI.EmptyMessage) req, streamObserver);
                    return;
                case 117:
                    this.serviceImpl.getExpandedSpendingKey((GrpcAPI.BytesMessage) req, streamObserver);
                    return;
                case 118:
                    this.serviceImpl.getAkFromAsk((GrpcAPI.BytesMessage) req, streamObserver);
                    return;
                case 119:
                    this.serviceImpl.getNkFromNsk((GrpcAPI.BytesMessage) req, streamObserver);
                    return;
                case 120:
                    this.serviceImpl.getIncomingViewingKey((GrpcAPI.ViewingKeyMessage) req, streamObserver);
                    return;
                case 121:
                    this.serviceImpl.getDiversifier((GrpcAPI.EmptyMessage) req, streamObserver);
                    return;
                case 122:
                    this.serviceImpl.getNewShieldedAddress((GrpcAPI.EmptyMessage) req, streamObserver);
                    return;
                case 123:
                    this.serviceImpl.getZenPaymentAddress((GrpcAPI.IncomingViewingKeyDiversifierMessage) req, streamObserver);
                    return;
                case 124:
                    this.serviceImpl.getRcm((GrpcAPI.EmptyMessage) req, streamObserver);
                    return;
                case 125:
                    this.serviceImpl.isSpend((GrpcAPI.NoteParameters) req, streamObserver);
                    return;
                case 126:
                    this.serviceImpl.createShieldedTransactionWithoutSpendAuthSig((GrpcAPI.PrivateParametersWithoutAsk) req, streamObserver);
                    return;
                case 127:
                    this.serviceImpl.getShieldTransactionHash((Protocol.Transaction) req, streamObserver);
                    return;
                case 128:
                    this.serviceImpl.createSpendAuthSig((GrpcAPI.SpendAuthSigParameters) req, streamObserver);
                    return;
                case WalletGrpc.METHODID_CREATE_SHIELD_NULLIFIER:
                    this.serviceImpl.createShieldNullifier((GrpcAPI.NfParameters) req, streamObserver);
                    return;
                case WalletGrpc.METHODID_CREATE_SHIELDED_CONTRACT_PARAMETERS:
                    this.serviceImpl.createShieldedContractParameters((GrpcAPI.PrivateShieldedTRC20Parameters) req, streamObserver);
                    return;
                case WalletGrpc.METHODID_CREATE_SHIELDED_CONTRACT_PARAMETERS_WITHOUT_ASK:
                    this.serviceImpl.createShieldedContractParametersWithoutAsk((GrpcAPI.PrivateShieldedTRC20ParametersWithoutAsk) req, streamObserver);
                    return;
                case WalletGrpc.METHODID_SCAN_SHIELDED_TRC20NOTES_BY_IVK:
                    this.serviceImpl.scanShieldedTRC20NotesByIvk((GrpcAPI.IvkDecryptTRC20Parameters) req, streamObserver);
                    return;
                case WalletGrpc.METHODID_SCAN_SHIELDED_TRC20NOTES_BY_OVK:
                    this.serviceImpl.scanShieldedTRC20NotesByOvk((GrpcAPI.OvkDecryptTRC20Parameters) req, streamObserver);
                    return;
                case WalletGrpc.METHODID_IS_SHIELDED_TRC20CONTRACT_NOTE_SPENT:
                    this.serviceImpl.isShieldedTRC20ContractNoteSpent((GrpcAPI.NfTRC20Parameters) req, streamObserver);
                    return;
                case WalletGrpc.METHODID_GET_TRIGGER_INPUT_FOR_SHIELDED_TRC20CONTRACT:
                    this.serviceImpl.getTriggerInputForShieldedTRC20Contract((GrpcAPI.ShieldedTRC20TriggerContractParameters) req, streamObserver);
                    return;
                case WalletGrpc.METHODID_CREATE_COMMON_TRANSACTION:
                    this.serviceImpl.createCommonTransaction((Protocol.Transaction) req, streamObserver);
                    return;
                case WalletGrpc.METHODID_GET_TRANSACTION_INFO_BY_BLOCK_NUM:
                    this.serviceImpl.getTransactionInfoByBlockNum((GrpcAPI.NumberMessage) req, streamObserver);
                    return;
                case WalletGrpc.METHODID_GET_BURN_TRX:
                    this.serviceImpl.getBurnTrx((GrpcAPI.EmptyMessage) req, streamObserver);
                    return;
                case WalletGrpc.METHODID_GET_TRANSACTION_FROM_PENDING:
                    this.serviceImpl.getTransactionFromPending((GrpcAPI.BytesMessage) req, streamObserver);
                    return;
                case WalletGrpc.METHODID_GET_TRANSACTION_LIST_FROM_PENDING:
                    this.serviceImpl.getTransactionListFromPending((GrpcAPI.EmptyMessage) req, streamObserver);
                    return;
                case WalletGrpc.METHODID_GET_PENDING_SIZE:
                    this.serviceImpl.getPendingSize((GrpcAPI.EmptyMessage) req, streamObserver);
                    return;
                case WalletGrpc.METHODID_GET_BLOCK:
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

    private static abstract class WalletBaseDescriptorSupplier implements ProtoFileDescriptorSupplier, ProtoServiceDescriptorSupplier {
        WalletBaseDescriptorSupplier() {
        }

        @Override
        public Descriptors.FileDescriptor getFileDescriptor() {
            return GrpcAPI.getDescriptor();
        }

        @Override
        public Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("Wallet");
        }
    }

    public static final class WalletFileDescriptorSupplier extends WalletBaseDescriptorSupplier {
        WalletFileDescriptorSupplier() {
        }
    }

    public static final class WalletMethodDescriptorSupplier extends WalletBaseDescriptorSupplier implements ProtoMethodDescriptorSupplier {
        private final String methodName;

        WalletMethodDescriptorSupplier(String str) {
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
            synchronized (WalletGrpc.class) {
                serviceDescriptor2 = serviceDescriptor;
                if (serviceDescriptor2 == null) {
                    serviceDescriptor2 = ServiceDescriptor.newBuilder(SERVICE_NAME).setSchemaDescriptor(new WalletFileDescriptorSupplier()).addMethod(getGetAccountMethod()).addMethod(getGetAccountByIdMethod()).addMethod(getGetAccountBalanceMethod()).addMethod(getGetBlockBalanceTraceMethod()).addMethod(getCreateTransactionMethod()).addMethod(getCreateTransaction2Method()).addMethod(getBroadcastTransactionMethod()).addMethod(getUpdateAccountMethod()).addMethod(getSetAccountIdMethod()).addMethod(getUpdateAccount2Method()).addMethod(getVoteWitnessAccountMethod()).addMethod(getUpdateSettingMethod()).addMethod(getUpdateEnergyLimitMethod()).addMethod(getVoteWitnessAccount2Method()).addMethod(getCreateAssetIssueMethod()).addMethod(getCreateAssetIssue2Method()).addMethod(getUpdateWitnessMethod()).addMethod(getUpdateWitness2Method()).addMethod(getCreateAccountMethod()).addMethod(getCreateAccount2Method()).addMethod(getCreateWitnessMethod()).addMethod(getCreateWitness2Method()).addMethod(getTransferAssetMethod()).addMethod(getTransferAsset2Method()).addMethod(getParticipateAssetIssueMethod()).addMethod(getParticipateAssetIssue2Method()).addMethod(getFreezeBalanceMethod()).addMethod(getFreezeBalance2Method()).addMethod(getFreezeBalanceV2Method()).addMethod(getUnfreezeBalanceMethod()).addMethod(getUnfreezeBalance2Method()).addMethod(getUnfreezeBalanceV2Method()).addMethod(getUnfreezeAssetMethod()).addMethod(getUnfreezeAsset2Method()).addMethod(getWithdrawBalanceMethod()).addMethod(getWithdrawBalance2Method()).addMethod(getWithdrawExpireUnfreezeMethod()).addMethod(getDelegateResourceMethod()).addMethod(getUnDelegateResourceMethod()).addMethod(getCancelAllUnfreezeV2Method()).addMethod(getUpdateAssetMethod()).addMethod(getUpdateAsset2Method()).addMethod(getProposalCreateMethod()).addMethod(getProposalApproveMethod()).addMethod(getProposalDeleteMethod()).addMethod(getBuyStorageMethod()).addMethod(getBuyStorageBytesMethod()).addMethod(getSellStorageMethod()).addMethod(getExchangeCreateMethod()).addMethod(getExchangeInjectMethod()).addMethod(getExchangeWithdrawMethod()).addMethod(getExchangeTransactionMethod()).addMethod(getMarketSellAssetMethod()).addMethod(getMarketCancelOrderMethod()).addMethod(getGetMarketOrderByIdMethod()).addMethod(getGetMarketOrderByAccountMethod()).addMethod(getGetMarketPriceByPairMethod()).addMethod(getGetMarketOrderListByPairMethod()).addMethod(getGetMarketPairListMethod()).addMethod(getListNodesMethod()).addMethod(getGetAssetIssueByAccountMethod()).addMethod(getGetAccountNetMethod()).addMethod(getGetAccountResourceMethod()).addMethod(getGetAssetIssueByNameMethod()).addMethod(getGetAssetIssueListByNameMethod()).addMethod(getGetAssetIssueByIdMethod()).addMethod(getGetNowBlockMethod()).addMethod(getGetNowBlock2Method()).addMethod(getGetBlockByNumMethod()).addMethod(getGetBlockByNum2Method()).addMethod(getGetTransactionCountByBlockNumMethod()).addMethod(getGetBlockByIdMethod()).addMethod(getGetBlockByLimitNextMethod()).addMethod(getGetBlockByLimitNext2Method()).addMethod(getGetBlockByLatestNumMethod()).addMethod(getGetBlockByLatestNum2Method()).addMethod(getGetTransactionByIdMethod()).addMethod(getDeployContractMethod()).addMethod(getGetContractMethod()).addMethod(getGetContractInfoMethod()).addMethod(getTriggerContractMethod()).addMethod(getTriggerConstantContractMethod()).addMethod(getEstimateEnergyMethod()).addMethod(getClearContractABIMethod()).addMethod(getListWitnessesMethod()).addMethod(getGetDelegatedResourceMethod()).addMethod(getGetDelegatedResourceV2Method()).addMethod(getGetDelegatedResourceAccountIndexMethod()).addMethod(getGetDelegatedResourceAccountIndexV2Method()).addMethod(getGetCanDelegatedMaxSizeMethod()).addMethod(getGetAvailableUnfreezeCountMethod()).addMethod(getGetCanWithdrawUnfreezeAmountMethod()).addMethod(getListProposalsMethod()).addMethod(getGetPaginatedProposalListMethod()).addMethod(getGetProposalByIdMethod()).addMethod(getListExchangesMethod()).addMethod(getGetPaginatedExchangeListMethod()).addMethod(getGetExchangeByIdMethod()).addMethod(getGetChainParametersMethod()).addMethod(getGetAssetIssueListMethod()).addMethod(getGetPaginatedAssetIssueListMethod()).addMethod(getTotalTransactionMethod()).addMethod(getGetNextMaintenanceTimeMethod()).addMethod(getGetTransactionInfoByIdMethod()).addMethod(getAccountPermissionUpdateMethod()).addMethod(getGetTransactionSignWeightMethod()).addMethod(getGetTransactionApprovedListMethod()).addMethod(getGetNodeInfoMethod()).addMethod(getGetRewardInfoMethod()).addMethod(getGetBrokerageInfoMethod()).addMethod(getUpdateBrokerageMethod()).addMethod(getCreateShieldedTransactionMethod()).addMethod(getGetMerkleTreeVoucherInfoMethod()).addMethod(getScanNoteByIvkMethod()).addMethod(getScanAndMarkNoteByIvkMethod()).addMethod(getScanNoteByOvkMethod()).addMethod(getGetSpendingKeyMethod()).addMethod(getGetExpandedSpendingKeyMethod()).addMethod(getGetAkFromAskMethod()).addMethod(getGetNkFromNskMethod()).addMethod(getGetIncomingViewingKeyMethod()).addMethod(getGetDiversifierMethod()).addMethod(getGetNewShieldedAddressMethod()).addMethod(getGetZenPaymentAddressMethod()).addMethod(getGetRcmMethod()).addMethod(getIsSpendMethod()).addMethod(getCreateShieldedTransactionWithoutSpendAuthSigMethod()).addMethod(getGetShieldTransactionHashMethod()).addMethod(getCreateSpendAuthSigMethod()).addMethod(getCreateShieldNullifierMethod()).addMethod(getCreateShieldedContractParametersMethod()).addMethod(getCreateShieldedContractParametersWithoutAskMethod()).addMethod(getScanShieldedTRC20NotesByIvkMethod()).addMethod(getScanShieldedTRC20NotesByOvkMethod()).addMethod(getIsShieldedTRC20ContractNoteSpentMethod()).addMethod(getGetTriggerInputForShieldedTRC20ContractMethod()).addMethod(getCreateCommonTransactionMethod()).addMethod(getGetTransactionInfoByBlockNumMethod()).addMethod(getGetBurnTrxMethod()).addMethod(getGetTransactionFromPendingMethod()).addMethod(getGetTransactionListFromPendingMethod()).addMethod(getGetPendingSizeMethod()).addMethod(getGetBlockMethod()).build();
                    serviceDescriptor = serviceDescriptor2;
                }
            }
        }
        return serviceDescriptor2;
    }
}
