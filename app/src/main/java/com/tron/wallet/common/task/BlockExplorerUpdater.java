package com.tron.wallet.common.task;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.arasthel.asyncjob.AsyncJob;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.utils.rb.RbUtil;
import com.tron.wallet.net.rpc.TronAPI;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.protos.contract.AssetIssueContractOuterClass;
public class BlockExplorerUpdater {
    private static List<Protocol.Account> mAccounts;
    private static AccountsUpdaterRunnable mAccountsUpdaterRunnable;
    private static BlockchainUpdaterRunnable mBlockchainUpdaterRunnable;
    private static List<Protocol.Block> mBlocks;
    private static Context mContext;
    private static ExecutorService mExecutorService;
    private static Map<UpdateTask, Long> mIntervals;
    private static List<GrpcAPI.Node> mNodes;
    private static NodesUpdaterRunnable mNodesUpdaterRunnable;
    private static Map<UpdateTask, Boolean> mRunning;
    private static Map<UpdateTask, Boolean> mSingleShot;
    private static Handler mTaskHandler;
    private static List<AssetIssueContractOuterClass.AssetIssueContract> mTokens;
    private static TokensUpdaterRunnable mTokensUpdaterRunnable;
    private static List<Protocol.Transaction> mTransactions;
    private static List<Protocol.Witness> mWitnesses;
    private static WitnessesUpdaterRunnable mWitnessesUpdaterRunnable;

    public enum UpdateTask {
        Blockchain,
        Nodes,
        Witnesses,
        Tokens,
        Accounts
    }

    public static List<Protocol.Account> getAccounts() {
        return mAccounts;
    }

    public static List<Protocol.Block> getBlocks() {
        return mBlocks;
    }

    public static List<GrpcAPI.Node> getNodes() {
        return mNodes;
    }

    public static List<AssetIssueContractOuterClass.AssetIssueContract> getTokens() {
        return mTokens;
    }

    public static List<Protocol.Transaction> getTransactions() {
        return mTransactions;
    }

    public static List<Protocol.Witness> getWitnesses() {
        return mWitnesses;
    }

    public static void init(Context context, Map<UpdateTask, Long> map) {
        if (mContext == null) {
            mContext = context;
            mIntervals = map;
            HashMap hashMap = new HashMap();
            mRunning = hashMap;
            hashMap.put(UpdateTask.Blockchain, false);
            mRunning.put(UpdateTask.Nodes, false);
            mRunning.put(UpdateTask.Witnesses, false);
            mRunning.put(UpdateTask.Tokens, false);
            mRunning.put(UpdateTask.Accounts, false);
            HashMap hashMap2 = new HashMap();
            mSingleShot = hashMap2;
            hashMap2.put(UpdateTask.Blockchain, false);
            mSingleShot.put(UpdateTask.Nodes, false);
            mSingleShot.put(UpdateTask.Witnesses, false);
            mSingleShot.put(UpdateTask.Tokens, false);
            mSingleShot.put(UpdateTask.Accounts, false);
            mBlocks = Collections.synchronizedList(new LinkedList());
            mTransactions = Collections.synchronizedList(new LinkedList());
            mWitnesses = Collections.synchronizedList(new LinkedList());
            mNodes = Collections.synchronizedList(new LinkedList());
            mTokens = Collections.synchronizedList(new LinkedList());
            mAccounts = Collections.synchronizedList(new LinkedList());
            mTaskHandler = new Handler(Looper.getMainLooper());
            mBlockchainUpdaterRunnable = new BlockchainUpdaterRunnable();
            mNodesUpdaterRunnable = new NodesUpdaterRunnable();
            mWitnessesUpdaterRunnable = new WitnessesUpdaterRunnable();
            mTokensUpdaterRunnable = new TokensUpdaterRunnable();
            mAccountsUpdaterRunnable = new AccountsUpdaterRunnable();
            mExecutorService = Executors.newFixedThreadPool(2);
        }
    }

    public static void start(UpdateTask updateTask) {
        stop(updateTask);
        mRunning.put(updateTask, true);
        mTaskHandler.post(getRunnableOfTask(updateTask));
    }

    public static void stop(UpdateTask updateTask) {
        mRunning.put(updateTask, false);
        mTaskHandler.removeCallbacks(getRunnableOfTask(updateTask));
    }

    public static void stopAll() {
        mRunning.put(UpdateTask.Blockchain, false);
        mRunning.put(UpdateTask.Nodes, false);
        mRunning.put(UpdateTask.Witnesses, false);
        mRunning.put(UpdateTask.Tokens, false);
        mRunning.put(UpdateTask.Accounts, false);
        mTaskHandler.removeCallbacks(null);
    }

    public static void singleShot(UpdateTask updateTask, boolean z) {
        mSingleShot.put(updateTask, true);
        if (z) {
            mTaskHandler.post(getRunnableOfTask(updateTask));
        } else {
            mTaskHandler.postDelayed(getRunnableOfTask(updateTask), mIntervals.containsKey(updateTask) ? mIntervals.get(updateTask).longValue() : 0L);
        }
    }

    public static Runnable getRunnableOfTask(UpdateTask updateTask) {
        int i = fun1.$SwitchMap$com$tron$wallet$common$task$BlockExplorerUpdater$UpdateTask[updateTask.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        if (i != 5) {
                            return null;
                        }
                        return mAccountsUpdaterRunnable;
                    }
                    return mTokensUpdaterRunnable;
                }
                return mWitnessesUpdaterRunnable;
            }
            return mNodesUpdaterRunnable;
        }
        return mBlockchainUpdaterRunnable;
    }

    public static class BlockchainUpdaterRunnable implements Runnable {
        private BlockchainUpdaterRunnable() {
        }

        @Override
        public void run() {
            AsyncJob.doInBackground(new AsyncJob.OnBackgroundJob() {
                @Override
                public void doOnBackground() {
                    if (BlockExplorerUpdater.mContext != null) {
                        int i = 50;
                        boolean z = false;
                        while (!z && i > 0) {
                            try {
                                GrpcAPI.BlockList blockByLatestNum = TronAPI.getBlockByLatestNum(i);
                                if (blockByLatestNum != null) {
                                    BlockExplorerUpdater.mBlocks.clear();
                                    BlockExplorerUpdater.mBlocks.addAll(blockByLatestNum.getBlockList());
                                    Collections.sort(BlockExplorerUpdater.mBlocks, new Comparator<Protocol.Block>() {
                                        @Override
                                        public int compare(Protocol.Block block, Protocol.Block block2) {
                                            return Long.compare(block.getBlockHeader().getRawData().getNumber(), block2.getBlockHeader().getRawData().getNumber());
                                        }
                                    });
                                }
                                BlockExplorerUpdater.mTransactions.clear();
                                for (Protocol.Block block : BlockExplorerUpdater.mBlocks) {
                                    BlockExplorerUpdater.mTransactions.addAll(block.getTransactionsList());
                                }
                                z = true;
                            } catch (StatusRuntimeException e) {
                                LogUtils.e(e);
                                int i2 = fun1.$SwitchMap$io$grpc$Status$Code[e.getStatus().getCode().ordinal()];
                                if (i2 == 1) {
                                    i -= 2;
                                } else if (i2 == 2) {
                                    i = 0;
                                }
                            } catch (Exception e2) {
                                LogUtils.e(e2);
                                i = 0;
                            }
                        }
                    }
                    AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                        @Override
                        public void doInUIThread() {
                            RbUtil.THIS.sendBlockchain();
                            if (((Boolean) BlockExplorerUpdater.mRunning.get(UpdateTask.Blockchain)).booleanValue()) {
                                BlockExplorerUpdater.mTaskHandler.removeCallbacks(BlockExplorerUpdater.getRunnableOfTask(UpdateTask.Blockchain));
                                BlockExplorerUpdater.mTaskHandler.postDelayed(BlockExplorerUpdater.mBlockchainUpdaterRunnable, ((Long) BlockExplorerUpdater.mIntervals.get(UpdateTask.Blockchain)).longValue());
                            }
                            BlockExplorerUpdater.mSingleShot.put(UpdateTask.Blockchain, false);
                        }
                    });
                }
            }, BlockExplorerUpdater.mExecutorService);
        }
    }

    public static class fun1 {
        static final int[] $SwitchMap$com$tron$wallet$common$task$BlockExplorerUpdater$UpdateTask;
        static final int[] $SwitchMap$io$grpc$Status$Code;

        static {
            int[] iArr = new int[Status.Code.values().length];
            $SwitchMap$io$grpc$Status$Code = iArr;
            try {
                iArr[Status.Code.RESOURCE_EXHAUSTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$grpc$Status$Code[Status.Code.UNAVAILABLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[UpdateTask.values().length];
            $SwitchMap$com$tron$wallet$common$task$BlockExplorerUpdater$UpdateTask = iArr2;
            try {
                iArr2[UpdateTask.Blockchain.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$tron$wallet$common$task$BlockExplorerUpdater$UpdateTask[UpdateTask.Nodes.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$tron$wallet$common$task$BlockExplorerUpdater$UpdateTask[UpdateTask.Witnesses.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$tron$wallet$common$task$BlockExplorerUpdater$UpdateTask[UpdateTask.Tokens.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$tron$wallet$common$task$BlockExplorerUpdater$UpdateTask[UpdateTask.Accounts.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public static class NodesUpdaterRunnable implements Runnable {
        private NodesUpdaterRunnable() {
        }

        @Override
        public void run() {
            AsyncJob.doInBackground(new AsyncJob.OnBackgroundJob() {
                @Override
                public void doOnBackground() {
                    if (BlockExplorerUpdater.mContext != null) {
                        try {
                            GrpcAPI.NodeList listNodes = TronAPI.listNodes();
                            if (listNodes != null) {
                                BlockExplorerUpdater.mNodes.clear();
                                BlockExplorerUpdater.mNodes.addAll(listNodes.getNodesList());
                            }
                        } catch (Exception e) {
                            LogUtils.e(e);
                        }
                    }
                    AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                        @Override
                        public void doInUIThread() {
                            RbUtil.THIS.sendNodes();
                            if (((Boolean) BlockExplorerUpdater.mRunning.get(UpdateTask.Nodes)).booleanValue()) {
                                BlockExplorerUpdater.mTaskHandler.removeCallbacks(BlockExplorerUpdater.getRunnableOfTask(UpdateTask.Nodes));
                                BlockExplorerUpdater.mTaskHandler.postDelayed(BlockExplorerUpdater.mNodesUpdaterRunnable, ((Long) BlockExplorerUpdater.mIntervals.get(UpdateTask.Nodes)).longValue());
                            }
                            BlockExplorerUpdater.mSingleShot.put(UpdateTask.Nodes, false);
                        }
                    });
                }
            }, BlockExplorerUpdater.mExecutorService);
        }
    }

    public static class WitnessesUpdaterRunnable implements Runnable {
        private WitnessesUpdaterRunnable() {
        }

        @Override
        public void run() {
            AsyncJob.doInBackground(new AsyncJob.OnBackgroundJob() {
                @Override
                public void doOnBackground() {
                    if (BlockExplorerUpdater.mContext != null) {
                        try {
                            GrpcAPI.WitnessList listWitnesses = TronAPI.listWitnesses();
                            if (listWitnesses != null) {
                                BlockExplorerUpdater.mWitnesses.clear();
                                BlockExplorerUpdater.mWitnesses.addAll(listWitnesses.getWitnessesList());
                            }
                        } catch (Exception e) {
                            LogUtils.e(e);
                        }
                    }
                    AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                        @Override
                        public void doInUIThread() {
                            RbUtil.THIS.sendWitnesses();
                            if (((Boolean) BlockExplorerUpdater.mRunning.get(UpdateTask.Witnesses)).booleanValue()) {
                                BlockExplorerUpdater.mTaskHandler.removeCallbacks(BlockExplorerUpdater.getRunnableOfTask(UpdateTask.Witnesses));
                                BlockExplorerUpdater.mTaskHandler.postDelayed(BlockExplorerUpdater.mWitnessesUpdaterRunnable, ((Long) BlockExplorerUpdater.mIntervals.get(UpdateTask.Witnesses)).longValue());
                            }
                            BlockExplorerUpdater.mSingleShot.put(UpdateTask.Witnesses, false);
                        }
                    });
                }
            }, BlockExplorerUpdater.mExecutorService);
        }
    }

    public static class TokensUpdaterRunnable implements Runnable {
        private TokensUpdaterRunnable() {
        }

        @Override
        public void run() {
            AsyncJob.doInBackground(new AsyncJob.OnBackgroundJob() {
                @Override
                public void doOnBackground() {
                    if (BlockExplorerUpdater.mContext != null) {
                        try {
                            GrpcAPI.AssetIssueList assetIssueList = TronAPI.getAssetIssueList();
                            if (assetIssueList != null) {
                                BlockExplorerUpdater.mTokens.clear();
                                BlockExplorerUpdater.mTokens.addAll(assetIssueList.getAssetIssueList());
                                Collections.sort(BlockExplorerUpdater.mTokens, new Comparator<AssetIssueContractOuterClass.AssetIssueContract>() {
                                    @Override
                                    public int compare(AssetIssueContractOuterClass.AssetIssueContract assetIssueContract, AssetIssueContractOuterClass.AssetIssueContract assetIssueContract2) {
                                        return assetIssueContract.getName().toStringUtf8().compareTo(assetIssueContract2.getName().toStringUtf8());
                                    }
                                });
                            }
                        } catch (Exception e) {
                            LogUtils.e(e);
                        }
                    }
                    AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                        @Override
                        public void doInUIThread() {
                            RbUtil.THIS.sendTokens();
                            if (((Boolean) BlockExplorerUpdater.mRunning.get(UpdateTask.Tokens)).booleanValue()) {
                                BlockExplorerUpdater.mTaskHandler.removeCallbacks(BlockExplorerUpdater.getRunnableOfTask(UpdateTask.Tokens));
                                BlockExplorerUpdater.mTaskHandler.postDelayed(BlockExplorerUpdater.mTokensUpdaterRunnable, ((Long) BlockExplorerUpdater.mIntervals.get(UpdateTask.Tokens)).longValue());
                            }
                            BlockExplorerUpdater.mSingleShot.put(UpdateTask.Tokens, false);
                        }
                    });
                }
            }, BlockExplorerUpdater.mExecutorService);
        }
    }

    public static class AccountsUpdaterRunnable implements Runnable {
        private AccountsUpdaterRunnable() {
        }

        @Override
        public void run() {
            AsyncJob.doInBackground(new AsyncJob.OnBackgroundJob() {
                @Override
                public void doOnBackground() {
                    Context unused = BlockExplorerUpdater.mContext;
                    AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                        @Override
                        public void doInUIThread() {
                            RbUtil.THIS.sendAccounts();
                            if (((Boolean) BlockExplorerUpdater.mRunning.get(UpdateTask.Accounts)).booleanValue()) {
                                BlockExplorerUpdater.mTaskHandler.removeCallbacks(BlockExplorerUpdater.getRunnableOfTask(UpdateTask.Accounts));
                                BlockExplorerUpdater.mTaskHandler.postDelayed(BlockExplorerUpdater.mAccountsUpdaterRunnable, ((Long) BlockExplorerUpdater.mIntervals.get(UpdateTask.Accounts)).longValue());
                            }
                            BlockExplorerUpdater.mSingleShot.put(UpdateTask.Accounts, false);
                        }
                    });
                }
            }, BlockExplorerUpdater.mExecutorService);
        }
    }

    public static boolean isRunning(UpdateTask updateTask) {
        return mRunning.get(updateTask).booleanValue();
    }

    public static boolean isSingleShotting(UpdateTask updateTask) {
        return mSingleShot.get(updateTask).booleanValue();
    }
}
