package com.tron.wallet.net.rpc;

import android.text.TextUtils;
import com.google.protobuf.ByteString;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.tabmy.myhome.settings.bean.MainNodeOutput;
import com.tron.wallet.common.bean.ChainBean;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.FailUtils;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.grpc.ManagedChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.tron.api.GrpcAPI;
import org.tron.api.WalletExtensionGrpc;
import org.tron.api.WalletGrpc;
import org.tron.common.utils.ByteArray;
import org.tron.protos.Protocol;
import org.tron.protos.contract.AccountContract;
import org.tron.protos.contract.AssetIssueContractOuterClass;
import org.tron.protos.contract.BalanceContract;
import org.tron.protos.contract.ExchangeContract;
import org.tron.protos.contract.ProposalContract;
import org.tron.protos.contract.ShieldContract;
import org.tron.protos.contract.SmartContractOuterClass;
import org.tron.protos.contract.StorageContract;
import org.tron.protos.contract.WitnessContract;
import org.tron.walletserver.ConnectErrorException;
import org.tron.walletserver.Wallet;
public class GrpcClient {
    private ChainBean currentBean;
    private volatile String currentFullnode;
    private int errorFullCount;
    private int fCount;
    private String[] fullNodeArray;
    private final RxManager rxManager;
    private String[] tempFullNodeArray;
    private String TagName = "IGrpcClient";
    private ManagedChannel channelFull = null;
    private WalletGrpc.WalletBlockingStub blockingStubFull = null;
    private WalletExtensionGrpc.WalletExtensionBlockingStub blockingStubExtension = null;
    private boolean isMainChain = true;
    boolean isConnected = true;

    public String getCurrentFullNode() {
        return this.currentFullnode;
    }

    public GrpcClient() {
        this.errorFullCount = 0;
        LogUtils.i("IGrpcClient", "GrpcClient ");
        this.rxManager = new RxManager();
        this.fCount = 3;
        this.errorFullCount = 0;
        String str = this.TagName;
        LogUtils.i(str, "errorCount GrpcClient zero:  " + this.errorFullCount + "  ");
        initArray();
    }

    private void initArray() {
        MainNodeOutput.DataBean dataBean;
        LogUtils.i(this.TagName, "initArray:");
        if (SpAPI.THIS.getCurrentChain() != null && !StringTronUtil.isEmpty(SpAPI.THIS.getCurrentChain().fullNode) && SpAPI.THIS.getMainNodeList() != null) {
            LogUtils.i(this.TagName, "getCurrentChain  getMainNodeList  not null ");
            ChainBean currentChain = SpAPI.THIS.getCurrentChain();
            this.currentBean = currentChain;
            if (currentChain != null && currentChain.isMainChain) {
                this.isMainChain = true;
                dataBean = SpAPI.THIS.getMainNodeList().get("MainChain");
            } else {
                this.isMainChain = false;
                dataBean = SpAPI.THIS.getMainNodeList().get(TronConfig.DApp_CHAIN_NAME);
            }
            if (dataBean != null && dataBean.fullNode.size() != 0) {
                this.fullNodeArray = new String[dataBean.fullNode.size()];
                dataBean.fullNode.toArray(this.fullNodeArray);
                String str = this.TagName;
                LogUtils.i(str, "fullNodeArray   " + this.fullNodeArray.length);
            }
        }
        synchronized (this) {
            String[] strArr = this.fullNodeArray;
            if (strArr == null || strArr.length == 0) {
                int testVersion = SpAPI.THIS.getTestVersion();
                if (testVersion == 1) {
                    this.fullNodeArray = AppContextUtil.getContext().getResources().getStringArray(R.array.main_node_list);
                } else if (testVersion == 2) {
                    this.fullNodeArray = AppContextUtil.getContext().getResources().getStringArray(R.array.prorelease_node_list);
                } else if (testVersion == 3) {
                    this.fullNodeArray = AppContextUtil.getContext().getResources().getStringArray(R.array.test_node_list);
                } else if (testVersion == 4) {
                    this.fullNodeArray = AppContextUtil.getContext().getResources().getStringArray(R.array.nile_node_list);
                } else if (testVersion == 5) {
                    this.fullNodeArray = AppContextUtil.getContext().getResources().getStringArray(R.array.shasta_node_list);
                }
            }
        }
    }

    public void connectFullNode(String str) {
        if (SpAPI.THIS.isCold()) {
            return;
        }
        LogUtils.d("IGrpcdClient", str);
        try {
            String str2 = this.TagName;
            LogUtils.i(str2, "connetFullNode:" + str);
            if (!StringUtils.isEmpty(str)) {
                ManagedChannel build = AndroidChannelBuilder.forTarget(str).usePlaintext().context(AppContextUtil.getContext()).build();
                this.channelFull = build;
                this.blockingStubFull = WalletGrpc.newBlockingStub(build);
            }
            this.rxManager.post(Event.NODE_CONNECT, "");
            List<ChainBean> allChainJson = SpAPI.THIS.getAllChainJson();
            if (allChainJson != null && allChainJson.size() > 0) {
                for (int i = 0; i < allChainJson.size(); i++) {
                    if (allChainJson.get(i).isSelect) {
                        allChainJson.get(i).fullNode = str;
                    }
                }
            } else {
                allChainJson = new ArrayList<>();
                ChainBean chainBean = new ChainBean();
                if ("MainChain".equals(SpAPI.THIS.getCurChainName())) {
                    chainBean.isMainChain = true;
                    chainBean.isSelect = true;
                    chainBean.chainName = "MainChain";
                } else {
                    chainBean.isMainChain = false;
                    chainBean.isSelect = true;
                    chainBean.chainName = TronConfig.DApp_CHAIN_NAME;
                }
                chainBean.fullNode = str;
                allChainJson.add(chainBean);
            }
            SpAPI.THIS.setAllChainJson(allChainJson);
            SpAPI.THIS.setIP(str);
            this.currentFullnode = str;
        } catch (Exception e) {
            LogUtils.e("grpc", e.toString());
            int i2 = this.fCount;
            this.fCount = i2 - 1;
            if (i2 > 0) {
                boolean isCustomFull = SpAPI.THIS.isCustomFull();
                boolean isShasta = SpAPI.THIS.isShasta();
                if (isCustomFull || isShasta) {
                    this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                    return;
                }
            }
            try {
                Wallet selectedWallet = WalletUtils.getSelectedWallet();
                if (selectedWallet != null) {
                    String str3 = selectedWallet.address;
                    FailUtils.sendFail("FullNode connect exception", "transaction is null", str3, e.toString() + " ");
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void connectFullNode() {
        LogUtils.i(this.TagName, "connetFullNode:");
        String stableFullNode = stableFullNode();
        if (checkNodeTargetLegal(stableFullNode)) {
            stableFullNode = getTargetFromLocal();
        }
        connectFullNode(stableFullNode);
    }

    private String getTargetFromLocal() {
        String[] stringArray;
        int testVersion = SpAPI.THIS.getTestVersion();
        if (testVersion == 1) {
            stringArray = AppContextUtil.getContext().getResources().getStringArray(R.array.main_node_list);
        } else if (testVersion == 2) {
            stringArray = AppContextUtil.getContext().getResources().getStringArray(R.array.prorelease_node_list);
        } else if (testVersion == 3) {
            stringArray = AppContextUtil.getContext().getResources().getStringArray(R.array.test_node_list);
        } else if (testVersion == 4) {
            stringArray = AppContextUtil.getContext().getResources().getStringArray(R.array.nile_node_list);
        } else {
            stringArray = testVersion != 5 ? null : AppContextUtil.getContext().getResources().getStringArray(R.array.shasta_node_list);
        }
        if (stringArray == null) {
            stringArray = AppContextUtil.getContext().getResources().getStringArray(R.array.main_node_list);
        }
        return stringArray[(int) (Math.random() * stringArray.length)];
    }

    private boolean checkNodeTargetLegal(String str) {
        if (!TextUtils.isEmpty(str) && str.contains(":")) {
            String[] split = str.split(":");
            return (split.length != 2 || TextUtils.isEmpty(split[0]) || TextUtils.isEmpty(split[1])) ? false : true;
        }
        return false;
    }

    public String randomFullNode() {
        LogUtils.i(this.TagName, "randomFullNode:");
        if (TronConfig.DApp_CHAIN_NAME.equals(SpAPI.THIS.getCurChainName()) && SpAPI.THIS.isDappCustomFull() && !TextUtils.isEmpty(SpAPI.THIS.getDappCustomeFull())) {
            return SpAPI.THIS.getDappCustomeFull();
        }
        if (SpAPI.THIS.isCustomFull() && !TextUtils.isEmpty(SpAPI.THIS.getCustomeFull())) {
            return SpAPI.THIS.getCustomeFull();
        }
        initArray();
        String[] strArr = this.fullNodeArray;
        if (strArr == null || strArr.length < 1) {
            return "";
        }
        int random = (int) (Math.random() * this.fullNodeArray.length);
        String str = this.TagName;
        LogUtils.i(str, "connetFullNode: fullNodeArray.length " + this.fullNodeArray.length + "  index:  " + random);
        this.currentFullnode = this.fullNodeArray[random];
        List<ChainBean> allChainJson = SpAPI.THIS.getAllChainJson();
        if (allChainJson != null && allChainJson.size() > 0) {
            for (int i = 0; i < allChainJson.size(); i++) {
                if (allChainJson.get(i).isSelect) {
                    allChainJson.get(i).fullNode = this.currentFullnode;
                }
            }
        } else {
            allChainJson = new ArrayList<>();
            ChainBean chainBean = new ChainBean();
            chainBean.isMainChain = true;
            chainBean.isSelect = true;
            chainBean.chainName = "MainChain";
            allChainJson.add(chainBean);
        }
        SpAPI.THIS.setAllChainJson(allChainJson);
        SpAPI.THIS.setIP(this.currentFullnode);
        return this.currentFullnode;
    }

    public String stableFullNode() {
        LogUtils.i(this.TagName, "stableFullNode:");
        initArray();
        String[] strArr = this.fullNodeArray;
        if (strArr == null || strArr.length < 1) {
            LogUtils.i(this.TagName, "stableFullNode:  return null");
            return "";
        }
        this.currentFullnode = strArr[0];
        List<ChainBean> allChainJson = SpAPI.THIS.getAllChainJson();
        if (allChainJson != null && allChainJson.size() > 0) {
            for (int i = 0; i < allChainJson.size(); i++) {
                if (allChainJson.get(i).isSelect) {
                    allChainJson.get(i).fullNode = this.currentFullnode;
                }
            }
        } else {
            allChainJson = new ArrayList<>();
            ChainBean chainBean = new ChainBean();
            if ("MainChain".equals(SpAPI.THIS.getCurChainName())) {
                chainBean.isMainChain = true;
                chainBean.isSelect = true;
                chainBean.chainName = "MainChain";
            } else {
                chainBean.isMainChain = false;
                chainBean.isSelect = true;
                chainBean.chainName = TronConfig.DApp_CHAIN_NAME;
            }
            String str = this.TagName;
            LogUtils.i(str, "stableFullNode:  " + this.currentFullnode);
            allChainJson.add(chainBean);
        }
        SpAPI.THIS.setAllChainJson(allChainJson);
        SpAPI.THIS.setIP(this.currentFullnode);
        return this.currentFullnode;
    }

    public void shutdown() throws InterruptedException {
        ManagedChannel managedChannel = this.channelFull;
        if (managedChannel != null) {
            managedChannel.shutdown().awaitTermination(5L, TimeUnit.SECONDS);
        }
    }

    public boolean canUseFullNode() {
        ManagedChannel managedChannel = this.channelFull;
        return (managedChannel == null || managedChannel.isShutdown() || this.channelFull.isTerminated()) ? false : true;
    }

    public Protocol.Account queryAccount(byte[] bArr, boolean z) throws ConnectErrorException {
        try {
            Protocol.Account build = Protocol.Account.newBuilder().setAddress(ByteString.copyFrom(bArr)).build();
            String str = this.TagName;
            LogUtils.i(str, "queryAccount: useSolidity " + z + " address " + new String(bArr));
            Protocol.Account account = this.blockingStubFull.withDeadlineAfter(10L, TimeUnit.SECONDS).getAccount(build);
            this.errorFullCount = 0;
            this.rxManager.post(Event.NODE_CONNECTED, 0);
            return account;
        } catch (Exception unused) {
            int i = this.errorFullCount + 1;
            this.errorFullCount = i;
            if (i > 2) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, "");
            } else {
                boolean isCustomFull = SpAPI.THIS.isCustomFull();
                boolean isShasta = SpAPI.THIS.isShasta();
                if (isCustomFull || isShasta) {
                    this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                    connectFullNode(this.currentFullnode);
                } else {
                    connectFullNode(randomFullNode());
                }
            }
            throw new ConnectErrorException("FullNode-queryAccount  error ");
        }
    }

    public Protocol.Account queryAccountById(String str) {
        try {
            return this.blockingStubFull.getAccountById(Protocol.Account.newBuilder().setAccountId(ByteString.copyFromUtf8(str)).build());
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.DelegatedResourceList queryDgList(String str, String str2) {
        try {
            return this.blockingStubFull.getDelegatedResource(GrpcAPI.DelegatedResourceMessage.newBuilder().setFromAddress(ByteString.copyFrom(str.getBytes())).setToAddress(ByteString.copyFrom(str2.getBytes())).build());
        } catch (Exception unused) {
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public Protocol.Transaction createTransaction(AccountContract.AccountUpdateContract accountUpdateContract) {
        try {
            return this.blockingStubFull.updateAccount(accountUpdateContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention createTransaction2(AccountContract.AccountUpdateContract accountUpdateContract) {
        try {
            return this.blockingStubFull.updateAccount2(accountUpdateContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public Protocol.Transaction createTransaction(AccountContract.SetAccountIdContract setAccountIdContract) {
        try {
            return this.blockingStubFull.setAccountId(setAccountIdContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public Protocol.Transaction createTransaction(AssetIssueContractOuterClass.UpdateAssetContract updateAssetContract) {
        try {
            return this.blockingStubFull.updateAsset(updateAssetContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention createTransaction2(AssetIssueContractOuterClass.UpdateAssetContract updateAssetContract) {
        try {
            return this.blockingStubFull.updateAsset2(updateAssetContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public Protocol.Transaction createTransaction(BalanceContract.TransferContract transferContract) {
        try {
            return this.blockingStubFull.createTransaction(transferContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention createTransaction2(BalanceContract.TransferContract transferContract) {
        try {
            return this.blockingStubFull.withDeadlineAfter(8L, TimeUnit.SECONDS).createTransaction2(transferContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention createTransaction(BalanceContract.FreezeBalanceContract freezeBalanceContract) {
        try {
            return this.blockingStubFull.freezeBalance2(freezeBalanceContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention createTransaction(StorageContract.BuyStorageContract buyStorageContract) {
        try {
            return this.blockingStubFull.buyStorage(buyStorageContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention createTransaction(StorageContract.BuyStorageBytesContract buyStorageBytesContract) {
        try {
            return this.blockingStubFull.buyStorageBytes(buyStorageBytesContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention createTransaction(StorageContract.SellStorageContract sellStorageContract) {
        try {
            return this.blockingStubFull.sellStorage(sellStorageContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention createTransaction2(BalanceContract.FreezeBalanceContract freezeBalanceContract) {
        try {
            return this.blockingStubFull.freezeBalance2(freezeBalanceContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public Protocol.Transaction createTransaction(BalanceContract.WithdrawBalanceContract withdrawBalanceContract) {
        try {
            return this.blockingStubFull.withdrawBalance(withdrawBalanceContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention createTransaction2(BalanceContract.WithdrawBalanceContract withdrawBalanceContract) {
        try {
            return this.blockingStubFull.withdrawBalance2(withdrawBalanceContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public Protocol.Transaction createTransaction(BalanceContract.UnfreezeBalanceContract unfreezeBalanceContract) {
        try {
            return this.blockingStubFull.unfreezeBalance(unfreezeBalanceContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention createTransaction2(BalanceContract.UnfreezeBalanceContract unfreezeBalanceContract) {
        try {
            return this.blockingStubFull.unfreezeBalance2(unfreezeBalanceContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public Protocol.Transaction createTransaction(AssetIssueContractOuterClass.UnfreezeAssetContract unfreezeAssetContract) {
        try {
            return this.blockingStubFull.unfreezeAsset(unfreezeAssetContract);
        } catch (Exception unused) {
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention createTransaction2(AssetIssueContractOuterClass.UnfreezeAssetContract unfreezeAssetContract) {
        try {
            return this.blockingStubFull.unfreezeAsset2(unfreezeAssetContract);
        } catch (Exception unused) {
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public Protocol.Transaction createTransferAssetTransaction(AssetIssueContractOuterClass.TransferAssetContract transferAssetContract) {
        try {
            return this.blockingStubFull.transferAsset(transferAssetContract);
        } catch (Exception unused) {
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention createTransferAssetTransaction2(AssetIssueContractOuterClass.TransferAssetContract transferAssetContract) {
        try {
            return this.blockingStubFull.transferAsset2(transferAssetContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public Protocol.Transaction createParticipateAssetIssueTransaction(AssetIssueContractOuterClass.ParticipateAssetIssueContract participateAssetIssueContract) {
        try {
            return this.blockingStubFull.participateAssetIssue(participateAssetIssueContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention createParticipateAssetIssueTransaction2(AssetIssueContractOuterClass.ParticipateAssetIssueContract participateAssetIssueContract) {
        try {
            return this.blockingStubFull.participateAssetIssue2(participateAssetIssueContract);
        } catch (Exception unused) {
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public Protocol.Transaction createAssetIssue(AssetIssueContractOuterClass.AssetIssueContract assetIssueContract) {
        try {
            return this.blockingStubFull.createAssetIssue(assetIssueContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention createAssetIssue2(AssetIssueContractOuterClass.AssetIssueContract assetIssueContract) {
        try {
            return this.blockingStubFull.createAssetIssue2(assetIssueContract);
        } catch (Exception unused) {
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public Protocol.Transaction voteWitnessAccount(WitnessContract.VoteWitnessContract voteWitnessContract) {
        try {
            return this.blockingStubFull.voteWitnessAccount(voteWitnessContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention voteWitnessAccount2(WitnessContract.VoteWitnessContract voteWitnessContract) {
        try {
            return this.blockingStubFull.voteWitnessAccount2(voteWitnessContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention proposalCreate(ProposalContract.ProposalCreateContract proposalCreateContract) {
        try {
            return this.blockingStubFull.proposalCreate(proposalCreateContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.ProposalList listProposals() {
        try {
            return this.blockingStubFull.listProposals(GrpcAPI.EmptyMessage.newBuilder().build());
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public Protocol.Proposal getProposal(String str) {
        try {
            return this.blockingStubFull.getProposalById(GrpcAPI.BytesMessage.newBuilder().setValue(ByteString.copyFrom(ByteArray.fromLong(Long.parseLong(str)))).build());
        } catch (NumberFormatException unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.ExchangeList listExchanges() {
        try {
            return this.blockingStubFull.listExchanges(GrpcAPI.EmptyMessage.newBuilder().build());
        } catch (Exception unused) {
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public Protocol.Exchange getExchange(String str) {
        try {
            return this.blockingStubFull.getExchangeById(GrpcAPI.BytesMessage.newBuilder().setValue(ByteString.copyFrom(ByteArray.fromLong(Long.parseLong(str)))).build());
        } catch (NumberFormatException unused) {
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public Protocol.ChainParameters getChainParameters() {
        try {
            return this.blockingStubFull.getChainParameters(GrpcAPI.EmptyMessage.newBuilder().build());
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention proposalApprove(ProposalContract.ProposalApproveContract proposalApproveContract) {
        try {
            return this.blockingStubFull.proposalApprove(proposalApproveContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention proposalDelete(ProposalContract.ProposalDeleteContract proposalDeleteContract) {
        try {
            return this.blockingStubFull.proposalDelete(proposalDeleteContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention exchangeCreate(ExchangeContract.ExchangeCreateContract exchangeCreateContract) {
        try {
            return this.blockingStubFull.exchangeCreate(exchangeCreateContract);
        } catch (Exception unused) {
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention exchangeInject(ExchangeContract.ExchangeInjectContract exchangeInjectContract) {
        try {
            return this.blockingStubFull.exchangeInject(exchangeInjectContract);
        } catch (Exception unused) {
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention exchangeWithdraw(ExchangeContract.ExchangeWithdrawContract exchangeWithdrawContract) {
        try {
            return this.blockingStubFull.exchangeWithdraw(exchangeWithdrawContract);
        } catch (Exception unused) {
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention exchangeTransaction(ExchangeContract.ExchangeTransactionContract exchangeTransactionContract) {
        try {
            return this.blockingStubFull.exchangeTransaction(exchangeTransactionContract);
        } catch (Exception unused) {
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public Protocol.Transaction createAccount(AccountContract.AccountCreateContract accountCreateContract) {
        try {
            return this.blockingStubFull.createAccount(accountCreateContract);
        } catch (Exception unused) {
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention createAccount2(AccountContract.AccountCreateContract accountCreateContract) {
        try {
            return this.blockingStubFull.createAccount2(accountCreateContract);
        } catch (Exception unused) {
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public Protocol.Transaction createWitness(WitnessContract.WitnessCreateContract witnessCreateContract) {
        try {
            return this.blockingStubFull.createWitness(witnessCreateContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention createWitness2(WitnessContract.WitnessCreateContract witnessCreateContract) {
        try {
            return this.blockingStubFull.createWitness2(witnessCreateContract);
        } catch (Exception unused) {
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public Protocol.Transaction updateWitness(WitnessContract.WitnessUpdateContract witnessUpdateContract) {
        try {
            return this.blockingStubFull.updateWitness(witnessUpdateContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention updateWitness2(WitnessContract.WitnessUpdateContract witnessUpdateContract) {
        try {
            return this.blockingStubFull.updateWitness2(witnessUpdateContract);
        } catch (Exception unused) {
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionSignWeight getTransactionSignWeight(Protocol.Transaction transaction) {
        try {
            return this.blockingStubFull.getTransactionSignWeight(transaction);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.Return broadcastTransaction(Protocol.Transaction transaction) throws ConnectErrorException {
        try {
            this.errorFullCount = 0;
            GrpcAPI.Return broadcastTransaction = this.blockingStubFull.withDeadlineAfter(10L, TimeUnit.SECONDS).broadcastTransaction(transaction);
            int i = 10;
            while (!broadcastTransaction.getResult() && broadcastTransaction.getCode() == GrpcAPI.Return.response_code.SERVER_BUSY && i > 0) {
                i--;
                broadcastTransaction = this.blockingStubFull.broadcastTransaction(transaction);
                try {
                    Thread.sleep(300L);
                } catch (InterruptedException e) {
                    SentryUtil.captureException(e);
                } catch (Exception e2) {
                    throw new ConnectErrorException(e2.getMessage());
                }
            }
            return broadcastTransaction;
        } catch (Exception e3) {
            throw new ConnectErrorException(e3.getMessage());
        }
    }

    public Protocol.Block getBlock(long j) {
        try {
            if (j < 0) {
                return this.blockingStubFull.getNowBlock(GrpcAPI.EmptyMessage.newBuilder().build());
            }
            GrpcAPI.NumberMessage.Builder newBuilder = GrpcAPI.NumberMessage.newBuilder();
            newBuilder.setNum(j);
            return this.blockingStubFull.getBlockByNum(newBuilder.build());
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public long getTransactionCountByBlockNum(long j) {
        try {
            GrpcAPI.NumberMessage.Builder newBuilder = GrpcAPI.NumberMessage.newBuilder();
            newBuilder.setNum(j);
            return this.blockingStubFull.getTransactionCountByBlockNum(newBuilder.build()).getNum();
        } catch (Exception unused) {
            connectFullNode(randomFullNode());
            return 0L;
        }
    }

    public GrpcAPI.BlockExtention getBlock2(long j) {
        try {
            if (j < 0) {
                return this.blockingStubFull.getNowBlock2(GrpcAPI.EmptyMessage.newBuilder().build());
            }
            GrpcAPI.NumberMessage.Builder newBuilder = GrpcAPI.NumberMessage.newBuilder();
            newBuilder.setNum(j);
            return this.blockingStubFull.getBlockByNum2(newBuilder.build());
        } catch (Exception unused) {
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.WitnessList listWitnesses() {
        try {
            return this.blockingStubFull.listWitnesses(GrpcAPI.EmptyMessage.newBuilder().build());
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.AssetIssueList getAssetIssueList() {
        try {
            return this.blockingStubFull.getAssetIssueList(GrpcAPI.EmptyMessage.newBuilder().build());
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.AssetIssueList getAssetIssueList(long j, long j2) {
        try {
            GrpcAPI.PaginatedMessage.Builder newBuilder = GrpcAPI.PaginatedMessage.newBuilder();
            newBuilder.setOffset(j);
            newBuilder.setLimit(j2);
            return this.blockingStubFull.getPaginatedAssetIssueList(newBuilder.build());
        } catch (Exception unused) {
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.NodeList listNodes() {
        try {
            return this.blockingStubFull.listNodes(GrpcAPI.EmptyMessage.newBuilder().build());
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.AssetIssueList getAssetIssueByAccount(byte[] bArr) {
        try {
            return this.blockingStubFull.getAssetIssueByAccount(Protocol.Account.newBuilder().setAddress(ByteString.copyFrom(bArr)).build());
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.AccountNetMessage getAccountNet(byte[] bArr) {
        try {
            return this.blockingStubFull.getAccountNet(Protocol.Account.newBuilder().setAddress(ByteString.copyFrom(bArr)).build());
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.AccountResourceMessage getAccountRes(byte[] bArr) {
        try {
            return this.blockingStubFull.getAccountResource(Protocol.Account.newBuilder().setAddress(ByteString.copyFrom(bArr)).build());
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.AccountResourceMessage getAccountResource(byte[] bArr) {
        try {
            return this.blockingStubFull.getAccountResource(Protocol.Account.newBuilder().setAddress(ByteString.copyFrom(bArr)).build());
        } catch (Exception unused) {
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public AssetIssueContractOuterClass.AssetIssueContract getAssetIssueByName(String str) {
        try {
            return this.blockingStubFull.getAssetIssueByName(GrpcAPI.BytesMessage.newBuilder().setValue(ByteString.copyFrom(str.getBytes())).build());
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.NumberMessage getTotalTransaction() {
        try {
            return this.blockingStubFull.totalTransaction(GrpcAPI.EmptyMessage.newBuilder().build());
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.NumberMessage getNextMaintenanceTime() {
        try {
            return this.blockingStubFull.getNextMaintenanceTime(GrpcAPI.EmptyMessage.newBuilder().build());
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionList getTransactionsFromThis(byte[] bArr, int i, int i2) {
        try {
            Protocol.Account build = Protocol.Account.newBuilder().setAddress(ByteString.copyFrom(bArr)).build();
            GrpcAPI.AccountPaginated.Builder newBuilder = GrpcAPI.AccountPaginated.newBuilder();
            newBuilder.setAccount(build);
            newBuilder.setOffset(i);
            newBuilder.setLimit(i2);
            return this.blockingStubExtension.getTransactionsFromThis(newBuilder.build());
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionListExtention getTransactionsFromThis2(byte[] bArr, int i, int i2) {
        try {
            Protocol.Account build = Protocol.Account.newBuilder().setAddress(ByteString.copyFrom(bArr)).build();
            GrpcAPI.AccountPaginated.Builder newBuilder = GrpcAPI.AccountPaginated.newBuilder();
            newBuilder.setAccount(build);
            newBuilder.setOffset(i);
            newBuilder.setLimit(i2);
            return this.blockingStubExtension.getTransactionsFromThis2(newBuilder.build());
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionList getTransactionsToThis(byte[] bArr, int i, int i2) {
        try {
            Protocol.Account build = Protocol.Account.newBuilder().setAddress(ByteString.copyFrom(bArr)).build();
            GrpcAPI.AccountPaginated.Builder newBuilder = GrpcAPI.AccountPaginated.newBuilder();
            newBuilder.setAccount(build);
            newBuilder.setOffset(i);
            newBuilder.setLimit(i2);
            return this.blockingStubExtension.getTransactionsToThis(newBuilder.build());
        } catch (Exception e) {
            LogUtils.e(e);
            return null;
        }
    }

    public GrpcAPI.TransactionListExtention getTransactionsToThis2(byte[] bArr, int i, int i2) {
        try {
            Protocol.Account build = Protocol.Account.newBuilder().setAddress(ByteString.copyFrom(bArr)).build();
            GrpcAPI.AccountPaginated.Builder newBuilder = GrpcAPI.AccountPaginated.newBuilder();
            newBuilder.setAccount(build);
            newBuilder.setOffset(i);
            newBuilder.setLimit(i2);
            return this.blockingStubExtension.getTransactionsToThis2(newBuilder.build());
        } catch (Exception e) {
            LogUtils.e(e);
            return null;
        }
    }

    public Protocol.Transaction getTransactionById(String str) {
        try {
            return this.blockingStubFull.getTransactionById(GrpcAPI.BytesMessage.newBuilder().setValue(ByteString.copyFrom(ByteArray.fromHexString(str))).build());
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public Protocol.TransactionInfo getTransactionInfoById(String str) {
        try {
            return this.blockingStubFull.getTransactionInfoById(GrpcAPI.BytesMessage.newBuilder().setValue(ByteString.copyFrom(ByteArray.fromHexString(str))).build());
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public Protocol.TransactionInfo getTransactionInfoByIdSo(String str) {
        try {
            return this.blockingStubFull.getTransactionInfoById(GrpcAPI.BytesMessage.newBuilder().setValue(ByteString.copyFrom(ByteArray.fromHexString(str))).build());
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public Protocol.Block getBlockById(String str) {
        try {
            return this.blockingStubFull.getBlockById(GrpcAPI.BytesMessage.newBuilder().setValue(ByteString.copyFrom(ByteArray.fromHexString(str))).build());
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.BlockList getBlockByLimitNext(long j, long j2) {
        try {
            GrpcAPI.BlockLimit.Builder newBuilder = GrpcAPI.BlockLimit.newBuilder();
            newBuilder.setStartNum(j);
            newBuilder.setEndNum(j2);
            return this.blockingStubFull.getBlockByLimitNext(newBuilder.build());
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.BlockListExtention getBlockByLimitNext2(long j, long j2) {
        try {
            GrpcAPI.BlockLimit.Builder newBuilder = GrpcAPI.BlockLimit.newBuilder();
            newBuilder.setStartNum(j);
            newBuilder.setEndNum(j2);
            return this.blockingStubFull.getBlockByLimitNext2(newBuilder.build());
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.BlockList getBlockByLatestNum(long j) {
        try {
            return this.blockingStubFull.getBlockByLatestNum(GrpcAPI.NumberMessage.newBuilder().setNum(j).build());
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.BlockListExtention getBlockByLatestNum2(long j) {
        try {
            return this.blockingStubFull.getBlockByLatestNum2(GrpcAPI.NumberMessage.newBuilder().setNum(j).build());
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention updateSetting(SmartContractOuterClass.UpdateSettingContract updateSettingContract) {
        try {
            return this.blockingStubFull.updateSetting(updateSettingContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention deployContract(SmartContractOuterClass.CreateSmartContract createSmartContract) {
        try {
            return this.blockingStubFull.deployContract(createSmartContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention triggerContract(SmartContractOuterClass.TriggerSmartContract triggerSmartContract) {
        try {
            return this.blockingStubFull.triggerContract(triggerSmartContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public SmartContractOuterClass.SmartContract getContract(byte[] bArr) {
        try {
            return this.blockingStubFull.getContract(GrpcAPI.BytesMessage.newBuilder().setValue(ByteString.copyFrom(bArr)).build());
        } catch (Exception e) {
            LogUtils.e(e);
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.DelegatedResourceList getDelegatedResource(byte[] bArr, byte[] bArr2) {
        try {
            ByteString copyFrom = ByteString.copyFrom(bArr);
            return this.blockingStubFull.getDelegatedResource(GrpcAPI.DelegatedResourceMessage.newBuilder().setFromAddress(copyFrom).setToAddress(ByteString.copyFrom(bArr2)).build());
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.DelegatedResourceList getDelegatedResourceV2(byte[] bArr, byte[] bArr2) {
        try {
            ByteString copyFrom = ByteString.copyFrom(bArr);
            return this.blockingStubFull.getDelegatedResourceV2(GrpcAPI.DelegatedResourceMessage.newBuilder().setFromAddress(copyFrom).setToAddress(ByteString.copyFrom(bArr2)).build());
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public Protocol.DelegatedResourceAccountIndex getDelegatedResourceAccountIndex(byte[] bArr) {
        try {
            return this.blockingStubFull.getDelegatedResourceAccountIndex(GrpcAPI.BytesMessage.newBuilder().setValue(ByteString.copyFrom(bArr)).build());
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention updateEnergyLimit(SmartContractOuterClass.UpdateEnergyLimitContract updateEnergyLimitContract) {
        try {
            return this.blockingStubFull.updateEnergyLimit(updateEnergyLimitContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention clearContractABI(SmartContractOuterClass.ClearABIContract clearABIContract) {
        try {
            return this.blockingStubFull.clearContractABI(clearABIContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention triggerConstantContract(SmartContractOuterClass.TriggerSmartContract triggerSmartContract) {
        try {
            return this.blockingStubFull.triggerConstantContract(triggerSmartContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention accountPermissionUpdate(AccountContract.AccountPermissionUpdateContract accountPermissionUpdateContract) {
        try {
            return this.blockingStubFull.accountPermissionUpdate(accountPermissionUpdateContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.NumberMessage getRewardInfo(byte[] bArr) {
        try {
            return this.blockingStubFull.getRewardInfo(GrpcAPI.BytesMessage.newBuilder().setValue(ByteString.copyFrom(bArr)).build());
        } catch (Exception e) {
            LogUtils.e(e);
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.NumberMessage getBrokerageInfo(byte[] bArr) {
        try {
            return this.blockingStubFull.getBrokerageInfo(GrpcAPI.BytesMessage.newBuilder().setValue(ByteString.copyFrom(bArr)).build());
        } catch (Exception e) {
            LogUtils.e(e);
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention withdrawBalance2(BalanceContract.WithdrawBalanceContract withdrawBalanceContract) {
        try {
            return this.blockingStubFull.withdrawBalance2(withdrawBalanceContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public Protocol.Block getNowBlock() {
        try {
            Protocol.Block nowBlock = this.blockingStubFull.withDeadlineAfter(5L, TimeUnit.SECONDS).getNowBlock(GrpcAPI.EmptyMessage.newBuilder().build());
            if (this.errorFullCount > 0) {
                this.rxManager.post(Event.NODE_CONNECTED, "");
                this.errorFullCount = 0;
            }
            return nowBlock;
        } catch (Exception unused) {
            int i = this.errorFullCount + 1;
            this.errorFullCount = i;
            if (i > 2) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, "");
            }
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.BlockExtention getNowBlock2() {
        try {
            GrpcAPI.BlockExtention nowBlock2 = this.blockingStubFull.withDeadlineAfter(5L, TimeUnit.SECONDS).getNowBlock2(GrpcAPI.EmptyMessage.newBuilder().build());
            if (this.errorFullCount > 0) {
                this.rxManager.post(Event.NODE_CONNECTED, "");
                this.errorFullCount = 0;
            }
            return nowBlock2;
        } catch (Exception unused) {
            int i = this.errorFullCount + 1;
            this.errorFullCount = i;
            if (i > 2) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, "");
            }
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.DecryptNotes ScanNoteByIvk(GrpcAPI.IvkDecryptParameters ivkDecryptParameters) {
        try {
            return this.blockingStubFull.scanNoteByIvk(ivkDecryptParameters);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.DecryptNotes ScanNoteByOvk(GrpcAPI.OvkDecryptParameters ovkDecryptParameters) {
        try {
            return this.blockingStubFull.scanNoteByOvk(ovkDecryptParameters);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public ShieldContract.IncrementalMerkleVoucherInfo GetMerkleTreeVoucherInfo(ShieldContract.OutputPointInfo outputPointInfo) {
        try {
            return this.blockingStubFull.getMerkleTreeVoucherInfo(outputPointInfo);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention createShieldedTransactionWithoutSpendAuthSig(GrpcAPI.PrivateParametersWithoutAsk privateParametersWithoutAsk) {
        try {
            return this.blockingStubFull.createShieldedTransactionWithoutSpendAuthSig(privateParametersWithoutAsk);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.SpendResult isSpend(GrpcAPI.NoteParameters noteParameters) {
        try {
            return this.blockingStubFull.isSpend(noteParameters);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.DecryptNotesMarked scanAndMarkNoteByIvk(GrpcAPI.IvkDecryptAndMarkParameters ivkDecryptAndMarkParameters) throws Exception {
        try {
            GrpcAPI.DecryptNotesMarked scanAndMarkNoteByIvk = this.blockingStubFull.scanAndMarkNoteByIvk(ivkDecryptAndMarkParameters);
            if (this.errorFullCount > 0) {
                this.rxManager.post(Event.NODE_CONNECTED, "");
                this.errorFullCount = 0;
            }
            return scanAndMarkNoteByIvk;
        } catch (Exception e) {
            int i = this.errorFullCount + 1;
            this.errorFullCount = i;
            if (i > 0 && i % 3 == 0) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, "");
            }
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
            } else {
                connectFullNode(randomFullNode());
            }
            throw new Exception(e);
        }
    }

    public AssetIssueContractOuterClass.AssetIssueContract getAssetIssueById(GrpcAPI.BytesMessage bytesMessage) {
        try {
            return this.blockingStubFull.getAssetIssueById(bytesMessage);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.DecryptNotesTRC20 scanShieldedTRC20NotesbyIvk(GrpcAPI.IvkDecryptTRC20Parameters ivkDecryptTRC20Parameters) {
        try {
            return this.blockingStubFull.scanShieldedTRC20NotesByIvk(ivkDecryptTRC20Parameters);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.DecryptNotesTRC20 scanShieldedTRC20NotesbyOvk(GrpcAPI.OvkDecryptTRC20Parameters ovkDecryptTRC20Parameters) {
        try {
            return this.blockingStubFull.scanShieldedTRC20NotesByOvk(ovkDecryptTRC20Parameters);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.NullifierResult isShieldedTRC20ContractNoteSpent(GrpcAPI.NfTRC20Parameters nfTRC20Parameters) {
        try {
            return this.blockingStubFull.isShieldedTRC20ContractNoteSpent(nfTRC20Parameters);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.BytesMessage getTriggerInputForShieldedTRC20Contract(GrpcAPI.ShieldedTRC20TriggerContractParameters shieldedTRC20TriggerContractParameters) {
        try {
            return this.blockingStubFull.getTriggerInputForShieldedTRC20Contract(shieldedTRC20TriggerContractParameters);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.ShieldedTRC20Parameters createShieldedContractParametersWithoutAsk(GrpcAPI.PrivateShieldedTRC20ParametersWithoutAsk privateShieldedTRC20ParametersWithoutAsk) {
        try {
            return this.blockingStubFull.createShieldedContractParametersWithoutAsk(privateShieldedTRC20ParametersWithoutAsk);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention WithdrawExpireUnfreeze(BalanceContract.WithdrawExpireUnfreezeContract withdrawExpireUnfreezeContract) {
        try {
            return this.blockingStubFull.withdrawExpireUnfreeze(withdrawExpireUnfreezeContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention delegateResource(BalanceContract.DelegateResourceContract delegateResourceContract) {
        try {
            return this.blockingStubFull.delegateResource(delegateResourceContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention unDelegateResource(BalanceContract.UnDelegateResourceContract unDelegateResourceContract) {
        try {
            return this.blockingStubFull.unDelegateResource(unDelegateResourceContract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention freezeBalanceV2(BalanceContract.FreezeBalanceV2Contract freezeBalanceV2Contract) {
        try {
            return this.blockingStubFull.freezeBalanceV2(freezeBalanceV2Contract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention unfreezeBalanceV2(BalanceContract.UnfreezeBalanceV2Contract unfreezeBalanceV2Contract) {
        try {
            return this.blockingStubFull.unfreezeBalanceV2(unfreezeBalanceV2Contract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.TransactionExtention cancelAllUnfreezeV2(BalanceContract.CancelAllUnfreezeV2Contract cancelAllUnfreezeV2Contract) {
        try {
            return this.blockingStubFull.cancelAllUnfreezeV2(cancelAllUnfreezeV2Contract);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.GetAvailableUnfreezeCountResponseMessage getAvailableUnfreezeCount(GrpcAPI.GetAvailableUnfreezeCountRequestMessage getAvailableUnfreezeCountRequestMessage) {
        try {
            return this.blockingStubFull.getAvailableUnfreezeCount(getAvailableUnfreezeCountRequestMessage);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.CanDelegatedMaxSizeResponseMessage getCanDelegatedMaxSize(GrpcAPI.CanDelegatedMaxSizeRequestMessage canDelegatedMaxSizeRequestMessage) {
        try {
            return this.blockingStubFull.getCanDelegatedMaxSize(canDelegatedMaxSizeRequestMessage);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }

    public GrpcAPI.CanWithdrawUnfreezeAmountResponseMessage getCanWithdrawUnfreezeAmount(GrpcAPI.CanWithdrawUnfreezeAmountRequestMessage canWithdrawUnfreezeAmountRequestMessage) {
        try {
            return this.blockingStubFull.getCanWithdrawUnfreezeAmount(canWithdrawUnfreezeAmountRequestMessage);
        } catch (Exception unused) {
            boolean isCustomFull = SpAPI.THIS.isCustomFull();
            boolean isShasta = SpAPI.THIS.isShasta();
            if (isCustomFull || isShasta) {
                this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                connectFullNode(this.currentFullnode);
                return null;
            }
            connectFullNode(randomFullNode());
            return null;
        }
    }
}
