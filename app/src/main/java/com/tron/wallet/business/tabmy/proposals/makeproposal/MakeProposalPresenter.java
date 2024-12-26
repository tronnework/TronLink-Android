package com.tron.wallet.business.tabmy.proposals.makeproposal;

import android.app.Activity;
import android.content.Intent;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.arasthel.asyncjob.AsyncJob;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.parambuilder.utils.ParamBuildUtils;
import com.tron.wallet.business.tabmy.proposals.bean.MakeProposalsBean;
import com.tron.wallet.business.tabmy.proposals.makeproposal.MakeProposalContract;
import com.tron.wallet.common.adapter.proposal.MakeProposalsAdapter;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.SamsungMultisignUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.LedgerWallet;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class MakeProposalPresenter extends MakeProposalContract.Presenter {
    private static final String TAG = "MakeProposalPresenter";
    private List<MakeProposalsBean> beanList;
    private List<Protocol.ChainParameters.ChainParameter> chainParameterList;
    private MakeProposalsAdapter.ParamerChangeListener changeListener;
    private HashMap<Long, Long> dealParameters;
    private List<Long> errorProposalIds;
    private String fromType;
    private Wallet mWallet;
    private LinearLayoutManager manager;
    private HashMap<Long, Long> parameters;
    private MakeProposalsAdapter proposalsAdapter;
    private String selectAddress;
    private boolean canEdit = true;
    private final long[] proposalId = {0, 1, 2, 3, 4, 5, 6, 7, 11, 12, 13, 14, 19, 21, 22, 23, 24, 29, 31, 32, 33, 35, 39, 40, 41, 44, 45, 46, 47, 48, 49, 51, 52, 53, 59, 61, 62, 63, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75};
    private final String[] paramKey = {"getMaintenanceTimeInterval", "getAccountUpgradeCost", "getCreateAccountFee", "getTransactionFee", "getAssetIssueFee", "getWitnessPayPerBlock", "getWitnessStandbyAllowance", "getCreateNewAccountFeeInSystemContract", "getEnergyFee", "getExchangeCreateFee", "getMaxCpuTimeOfOneTx", "getAllowUpdateAccountName", "getTotalEnergyCurrentLimit", "getAllowAdaptiveEnergy", "getUpdateAccountPermissionFee", "getMultiSignFee", "getAllowProtoFilterNum", "getAdaptiveResourceLimitMultiplier", "getWitness127PayPerBlock", "getAllowTvmSolidity059", "getAdaptiveResourceLimitTargetRatio", "getForbidTransferToContract", "getAllowShieldedTRC20Transaction", "getAllowPBFT", "getAllowTvmIstanbul", "getAllowMarketTransaction", "getMarketSellFee", "getMarketCancelFee", "getMaxFeeLimit", "getAllowTransactionFeePool", "getAllowOptimizeBlackHole", "getAllowNewResourceModel", "getAllowTvmFreeze", "getAllowAccountAssetOptimization", "getAllowTvmVote", "getFreeNetLimit", "getTotalNetLimit", "getAllowTVMLondon", "getAllowHigherLimitForMaxCpuTimeOfOneTx", "getAllowAssetOptimization", "getAllowNewReward", "getMemoFee", "getAllowDelegateOptimization", "getUnfreezeDelayDays", "getAllowOptimizedReturnValueOfChainId", "getAllowDynamicEnergy", "getDynamicEnergyThreshold", "getDynamicEnergyIncreaseFactor", "getDynamicEnergyMaxFactor"};
    private final int[] type = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0};
    private final Long[] hideItems = {14L, 21L, 24L, 32L, 39L, 40L, 41L, 44L};
    private final int[] contentId = {R.string.propose_0, R.string.propose_1, R.string.propose_2, R.string.propose_3, R.string.propose_4, R.string.propose_5, R.string.propose_6, R.string.propose_7, R.string.propose_11, R.string.propose_12, R.string.propose_13, R.string.propose_14, R.string.propose_19, R.string.propose_21, R.string.propose_22, R.string.propose_23, R.string.propose_24, R.string.propose_29, R.string.propose_31, R.string.propose_32, R.string.propose_33, R.string.propose_35, R.string.propose_39, R.string.propose_40, R.string.propose_41, R.string.propose_44, R.string.propose_45, R.string.propose_46, R.string.propose_47, R.string.propose_48, R.string.propose_49, R.string.propose_51, R.string.propose_52, R.string.proposal_53, R.string.proposal_59, R.string.proposal_61, R.string.proposal_62, R.string.proposal_63, R.string.proposal_65, R.string.proposal_66, R.string.proposal_67, R.string.proposal_68, R.string.proposal_69, R.string.proposal_70, R.string.proposal_71, R.string.proposal_72, R.string.proposal_73, R.string.proposal_74, R.string.proposal_75};
    private boolean hasPer = true;

    public static void lambda$initRx$0(Object obj) throws Exception {
    }

    @Override
    protected void onStart() {
        this.beanList = new ArrayList();
        this.parameters = new HashMap<>();
        this.chainParameterList = new ArrayList();
        this.errorProposalIds = new ArrayList();
    }

    @Override
    public void addSome() {
        this.mWallet = WalletUtils.getSelectedWallet();
        initRx();
        initListener();
        Intent iIntent = ((MakeProposalContract.View) this.mView).getIIntent();
        if (iIntent != null) {
            this.selectAddress = iIntent.getStringExtra(MakeProposalActivity.SELECTADDRESS);
            this.dealParameters = (HashMap) iIntent.getSerializableExtra(MakeProposalActivity.DEALPARAMETERS);
            this.fromType = iIntent.getStringExtra(MakeProposalActivity.FROMTYPE);
        }
        if (!StringTronUtil.isEmpty(this.fromType) && this.fromType.equals(MakeProposalActivity.TYPE_DEAL)) {
            ((MakeProposalContract.View) this.mView).showOrHideCreate(false);
            this.canEdit = false;
        } else {
            ((MakeProposalContract.View) this.mView).showOrHideCreate(true);
            this.canEdit = true;
        }
        this.manager = new LinearLayoutManager(((MakeProposalContract.View) this.mView).getIContext(), 1, false);
        initLayoutData();
        ((MakeProposalContract.View) this.mView).getRc().setLayoutManager(this.manager);
        MakeProposalsAdapter makeProposalsAdapter = this.proposalsAdapter;
        if (makeProposalsAdapter == null) {
            this.proposalsAdapter = new MakeProposalsAdapter(((MakeProposalContract.View) this.mView).getIContext(), this.beanList, this.parameters, this.changeListener, this.errorProposalIds, this.canEdit);
            ((MakeProposalContract.View) this.mView).getRc().setAdapter(this.proposalsAdapter);
            return;
        }
        makeProposalsAdapter.notify(this.beanList);
    }

    private void initRx() {
        this.mRxManager.on(Event.BroadcastFail, new Consumer() {
            @Override
            public final void accept(Object obj) {
                MakeProposalPresenter.lambda$initRx$0(obj);
            }
        });
    }

    private void initListener() {
        this.changeListener = new MakeProposalsAdapter.ParamerChangeListener() {
            @Override
            public void onParamerChange(HashMap<Long, Long> hashMap) {
                parameters = hashMap;
            }

            @Override
            public void onErrorTipShow(List<Long> list) {
                errorProposalIds = list;
            }
        };
    }

    private void initLayoutData() {
        this.beanList = new ArrayList();
        for (int i = 0; i < this.contentId.length; i++) {
            MakeProposalsBean makeProposalsBean = new MakeProposalsBean();
            long j = this.proposalId[i];
            makeProposalsBean.proposalId = j;
            makeProposalsBean.key = this.paramKey[i];
            makeProposalsBean.proposalsCentent = ((MakeProposalContract.View) this.mView).getIContext().getResources().getString(this.contentId[i]);
            makeProposalsBean.type = this.type[i];
            if (j == 0) {
                makeProposalsBean.company = ((MakeProposalContract.View) this.mView).getIContext().getResources().getString(R.string.propose_hour);
            } else if (j == 8 || j == 61 || j == 62) {
                makeProposalsBean.company = "Bandwidth";
            } else if (j == 19) {
                makeProposalsBean.company = "Energy";
            } else if (j == 13) {
                makeProposalsBean.company = "ms";
            } else if (j >= 73 && j <= 75) {
                makeProposalsBean.company = "";
            } else if (j == 70) {
                makeProposalsBean.company = "";
            } else if (makeProposalsBean.type == 0) {
                makeProposalsBean.company = "TRX";
            }
            if (i == 0) {
                makeProposalsBean.isSelect = true;
            }
            this.beanList.add(makeProposalsBean);
        }
    }

    @Override
    public void confirm() {
        List<Long> list = this.errorProposalIds;
        if (list != null && list.size() > 0) {
            ((MakeProposalContract.View) this.mView).toast(((MakeProposalContract.View) this.mView).getIContext().getResources().getString(R.string.error_make_proposal));
            return;
        }
        HashMap<Long, Long> hashMap = this.parameters;
        if (hashMap == null || hashMap.size() == 0) {
            ((MakeProposalContract.View) this.mView).toast(((MakeProposalContract.View) this.mView).getIContext().getResources().getString(R.string.parameter_proposal));
        } else if (SamsungMultisignUtils.isSamsungWallet(this.selectAddress) || SamsungMultisignUtils.isSamsungMultisign(this.selectAddress)) {
            ((MakeProposalContract.View) this.mView).toast(((MakeProposalContract.View) this.mView).getIContext().getString(R.string.no_samsung_to_shield));
        } else if (LedgerWallet.isLedger(this.mWallet) && this.parameters.size() > 10) {
            ((MakeProposalContract.View) this.mView).toast(((MakeProposalContract.View) this.mView).getIContext().getString(R.string.ledger_proposal_count_limit));
        } else {
            ((MakeProposalContract.View) this.mView).showLoading(true);
            ((MakeProposalContract.View) this.mView).runOnThreeThread(new OnBackground() {
                @Override
                public final void doOnBackground() {
                    lambda$confirm$2();
                }
            });
        }
    }

    public void lambda$confirm$2() {
        GrpcAPI.TransactionExtention createProposalTransaction = TronAPI.createProposalTransaction(StringTronUtil.decodeFromBase58Check(this.selectAddress), this.parameters);
        try {
            Protocol.Account queryAccount = TronAPI.queryAccount(StringTronUtil.decodeFromBase58Check(this.selectAddress), false);
            Wallet wallet = this.mWallet;
            if (wallet != null && queryAccount != null) {
                this.hasPer = WalletUtils.checkHaveOwnerPermissions(wallet.getAddress(), queryAccount.getOwnerPermission());
            }
        } catch (Exception unused) {
            this.hasPer = true;
        }
        if (createProposalTransaction != null) {
            try {
                if (createProposalTransaction.hasResult()) {
                    final Protocol.Transaction transaction = createProposalTransaction.getTransaction();
                    final HashMap<Long, String> makeReadableParameters = makeReadableParameters();
                    AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                        @Override
                        public final void doInUIThread() {
                            lambda$confirm$1(transaction, makeReadableParameters);
                        }
                    });
                }
            } catch (Exception e) {
                LogUtils.e(e);
                ((MakeProposalContract.View) this.mView).ToastSuc(R.string.create_transaction_fail);
                return;
            }
        }
        ((MakeProposalContract.View) this.mView).ToastSuc(R.string.create_transaction_fail);
    }

    public void lambda$confirm$1(Protocol.Transaction transaction, HashMap hashMap) {
        ((MakeProposalContract.View) this.mView).showLoading(false);
        FirebaseAnalytics.getInstance(((MakeProposalContract.View) this.mView).getIContext()).logEvent("Creat_proposal", null);
        if (transaction != null && !StringTronUtil.isEmpty(transaction.toString())) {
            ConfirmTransactionNewActivity.startForResult((Activity) ((MakeProposalContract.View) this.mView).getIContext(), ParamBuildUtils.getMakeProposalTransactionParamBuilder(this.hasPer, hashMap, transaction), 1234, this.mWallet.getCreateType() == 7);
        } else {
            ((MakeProposalContract.View) this.mView).ToastSuc(R.string.create_transaction_fail);
        }
    }

    private HashMap<Long, String> makeReadableParameters() {
        String str;
        HashMap<Long, String> hashMap = new HashMap<>();
        for (Map.Entry<Long, Long> entry : this.parameters.entrySet()) {
            Long key = entry.getKey();
            Iterator<MakeProposalsBean> it = this.beanList.iterator();
            while (true) {
                if (it.hasNext()) {
                    MakeProposalsBean next = it.next();
                    if (next.proposalId == key.longValue()) {
                        if (next.type == 0) {
                            str = String.format("%s%s %s %s", next.proposalsCentent, ((MakeProposalContract.View) this.mView).getIContext().getString(R.string.propose_to), next.inputContent, next.company);
                        } else {
                            str = next.proposalsCentent;
                        }
                        hashMap.put(Long.valueOf(next.proposalId), str);
                    }
                }
            }
        }
        return hashMap;
    }

    @Override
    public void getData() {
        ((MakeProposalContract.View) this.mView).runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$getData$4();
            }
        });
    }

    public void lambda$getData$4() {
        HashMap<Long, Long> hashMap;
        Protocol.ChainParameters chainParameters = TronAPI.getChainParameters();
        if (chainParameters != null) {
            List<Protocol.ChainParameters.ChainParameter> chainParameterList = chainParameters.getChainParameterList();
            this.chainParameterList = chainParameterList;
            if (chainParameterList != null && chainParameterList.size() > 0) {
                List<MakeProposalsBean> list = this.beanList;
                if (list == null || list.size() == 0) {
                    initLayoutData();
                }
                if (this.beanList != null) {
                    List asList = Arrays.asList(this.hideItems);
                    int i = 0;
                    while (i < this.beanList.size()) {
                        MakeProposalsBean makeProposalsBean = this.beanList.get(i);
                        makeProposalsBean.inputContent = "empty";
                        makeProposalsBean.netContent = "";
                        for (int i2 = 0; i2 < this.chainParameterList.size(); i2++) {
                            Protocol.ChainParameters.ChainParameter chainParameter = this.chainParameterList.get(i2);
                            if (makeProposalsBean != null && chainParameter != null && makeProposalsBean.key.equalsIgnoreCase(chainParameter.getKey())) {
                                makeProposalsBean.value = chainParameter.getValue();
                                makeProposalsBean.selectValue = chainParameter.getValue();
                                makeProposalsBean.inputContent = "empty";
                                makeProposalsBean.netContent = "";
                            }
                        }
                        if (asList.contains(Long.valueOf(makeProposalsBean.proposalId)) && makeProposalsBean.value == 1) {
                            this.beanList.remove(i);
                            i--;
                        }
                        i++;
                    }
                    if (!StringTronUtil.isEmpty(this.fromType) && this.fromType.equals(MakeProposalActivity.TYPE_DEAL)) {
                        for (int i3 = 0; i3 < this.beanList.size(); i3++) {
                            MakeProposalsBean makeProposalsBean2 = this.beanList.get(i3);
                            if (makeProposalsBean2 != null && (hashMap = this.dealParameters) != null && hashMap.size() > 0 && this.dealParameters.containsKey(Long.valueOf(makeProposalsBean2.proposalId))) {
                                makeProposalsBean2.value = this.dealParameters.get(Long.valueOf(makeProposalsBean2.proposalId)).longValue();
                                makeProposalsBean2.selectValue = this.dealParameters.get(Long.valueOf(makeProposalsBean2.proposalId)).longValue();
                            }
                        }
                    }
                }
            }
        }
        ((MakeProposalContract.View) this.mView).runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$getData$3();
            }
        });
    }

    public void lambda$getData$3() {
        ((MakeProposalContract.View) this.mView).dismissLoadingPage();
        MakeProposalsAdapter makeProposalsAdapter = this.proposalsAdapter;
        if (makeProposalsAdapter == null) {
            this.proposalsAdapter = new MakeProposalsAdapter(((MakeProposalContract.View) this.mView).getIContext(), this.beanList, this.parameters, this.changeListener, this.errorProposalIds, this.canEdit);
            ((MakeProposalContract.View) this.mView).getRc().setAdapter(this.proposalsAdapter);
            return;
        }
        makeProposalsAdapter.notify(this.beanList);
    }
}
