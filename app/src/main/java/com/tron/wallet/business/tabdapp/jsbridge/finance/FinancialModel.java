package com.tron.wallet.business.tabdapp.jsbridge.finance;

import android.os.Handler;
import android.text.TextUtils;
import android.webkit.WebView;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.gson.Gson;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.core.pending.fg.stake.StakeSuccessModel;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.StakeAndVoteParam;
import com.tron.wallet.business.confirm.parambuilder.utils.ParamBuildUtils;
import com.tron.wallet.business.finance.bean.FinanceDataBean;
import com.tron.wallet.business.finance.bean.MyFinancialTokenListInput;
import com.tron.wallet.business.finance.bean.Result;
import com.tron.wallet.business.finance.mvp.FinanceModel;
import com.tron.wallet.business.stakev2.managementv2.ResourcesBean;
import com.tron.wallet.business.tabdapp.jsbridge.finance.beans.FinacialParam;
import com.tron.wallet.business.tabdapp.jsbridge.finance.beans.FinancialListH5InputParam;
import com.tron.wallet.business.tabdapp.jsbridge.finance.beans.FinancialListParam;
import com.tron.wallet.business.tabdapp.jsbridge.finance.beans.StakeAndVoteOutput;
import com.tron.wallet.business.tabdapp.jsbridge.finance.beans.VoteBean;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.AddressNameUtils;
import com.tron.wallet.common.utils.DataStatHelper;
import com.tron.wallet.common.utils.GsonUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.rpc.TronAPI;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import j$.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.tron.api.GrpcAPI;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
import org.tron.protos.contract.Common;
import org.tron.protos.contract.WitnessContract;
import org.tron.walletserver.Wallet;
public class FinancialModel {
    private Protocol.Account account;
    private Handler handler;
    private FinanceModel model = new FinanceModel();
    private RxManager rxManager = new RxManager();
    private StakeSuccessModel stakeSuccessModel;
    private WebView webView;

    public FinancialModel(WebView webView, Handler handler) {
        this.webView = webView;
        this.handler = handler;
    }

    public void clear() {
        RxManager rxManager = this.rxManager;
        if (rxManager != null) {
            rxManager.clear();
        }
    }

    public void addDisposable(Disposable disposable) {
        RxManager rxManager = this.rxManager;
        if (rxManager != null) {
            rxManager.add(disposable);
        }
    }

    public boolean checkParamsEmpty(String str, String str2) {
        if (StringTronUtil.isEmpty(str)) {
            loadCancelJs(str2);
            return true;
        }
        return false;
    }

    public void loadJs(String str, Result result) {
        final String str2 = "javascript:" + str + "('" + GsonUtils.toGsonString(result) + "')";
        this.webView.post(new Runnable() {
            @Override
            public final void run() {
                lambda$loadJs$0(str2);
            }
        });
    }

    public void lambda$loadJs$0(String str) {
        this.webView.loadUrl(str);
    }

    public void loadJs(String str, String str2) {
        final String str3 = "javascript:" + str + "('" + str2 + "')";
        WebView webView = this.webView;
        if (webView != null) {
            webView.post(new Runnable() {
                @Override
                public final void run() {
                    lambda$loadJs$1(str3);
                }
            });
        }
    }

    public void lambda$loadJs$1(String str) {
        WebView webView = this.webView;
        if (webView != null) {
            webView.loadUrl(str);
        }
    }

    public void loadCancelJs(String str) {
        final String str2 = "javascript:" + str + "('cancel')";
        WebView webView = this.webView;
        if (webView != null) {
            webView.post(new Runnable() {
                @Override
                public final void run() {
                    lambda$loadCancelJs$2(str2);
                }
            });
        }
    }

    public void lambda$loadCancelJs$2(String str) {
        WebView webView = this.webView;
        if (webView != null) {
            webView.loadUrl(str);
        }
    }

    public void getTotalAssets(String str, final String str2) {
        FinacialParam finacialParam = new FinacialParam();
        if (str != null && ("ALL".equals(str) || "all".equals(str))) {
            finacialParam.setWalletAddress(getAllNativeWallets());
            finacialParam.setShowUsers(false);
        } else {
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            finacialParam.setWalletAddress(arrayList);
            finacialParam.setShowUsers(false);
        }
        LogUtils.d("FinacialAcToJs", GsonUtils.toGsonString(finacialParam));
        this.model.getTotalAssets(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), GsonUtils.toGsonString(finacialParam))).subscribe(new IObserver(new ICallback<FinanceDataBean>() {
            @Override
            public void onResponse(String str3, FinanceDataBean financeDataBean) {
                if (financeDataBean != null && financeDataBean.getData() != null) {
                    loadJs(str2, GsonUtils.toGsonString(financeDataBean.getData()));
                } else {
                    loadJs(str2, "");
                }
            }

            @Override
            public void onFailure(String str3, String str4) {
                loadJs(str2, "");
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                rxManager.add(disposable);
            }
        }, "getTotalAssets"));
    }

    public void getTokenFinancialList(String str, final String str2) {
        LogUtils.d("FinacialAcToJs", "options：  " + str);
        try {
            FinancialListH5InputParam financialListH5InputParam = (FinancialListH5InputParam) new Gson().fromJson(str,  FinancialListH5InputParam.class);
            FinancialListParam financialListParam = new FinancialListParam();
            financialListParam.setSort(financialListH5InputParam.getSort());
            if (financialListH5InputParam != null && ("ALL".equals(financialListH5InputParam.getWalletAddress()) || "all".equals(financialListH5InputParam.getWalletAddress()))) {
                financialListParam.setWalletAddress(getAllNativeWallets());
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(financialListH5InputParam.getWalletAddress());
                financialListParam.setWalletAddress(arrayList);
            }
            LogUtils.d("FinacialAcToJs", GsonUtils.toGsonString(financialListParam));
            this.model.getTokenFinancialList(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), GsonUtils.toGsonString(financialListParam))).subscribe(new IObserver(new ICallback<FinanceDataBean>() {
                @Override
                public void onResponse(String str3, FinanceDataBean financeDataBean) {
                    if (financeDataBean != null && financeDataBean.getData() != null) {
                        loadJs(str2, GsonUtils.toGsonString(financeDataBean.getData()));
                    } else {
                        loadJs(str2, "");
                    }
                }

                @Override
                public void onFailure(String str3, String str4) {
                    loadJs(str2, "");
                }

                @Override
                public void onSubscribe(Disposable disposable) {
                    rxManager.add(disposable);
                }
            }, "getTokenFinancialList"));
        } catch (Throwable th) {
            loadJs(str2, "");
            LogUtils.e(th);
        }
    }

    public void getMyTokenFinancialList(String str, final String str2) {
        LogUtils.d("FinacialAcToJs", "options：  " + str);
        MyFinancialTokenListInput myFinancialTokenListInput = new MyFinancialTokenListInput();
        if (str != null && ("ALL".equals(str) || "all".equals(str) || "undefined".equals(str))) {
            myFinancialTokenListInput.setWalletAddress(getAllNativeWallets());
        } else {
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            myFinancialTokenListInput.setWalletAddress(arrayList);
        }
        LogUtils.d("FinacialAcToJs", GsonUtils.toGsonString(myFinancialTokenListInput));
        this.model.getMyTokenFinancialList(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), GsonUtils.toGsonString(myFinancialTokenListInput))).subscribe(new IObserver(new ICallback<FinanceDataBean>() {
            @Override
            public void onResponse(String str3, FinanceDataBean financeDataBean) {
                if (financeDataBean != null && financeDataBean.getData() != null) {
                    loadJs(str2, GsonUtils.toGsonString(financeDataBean.getData()));
                } else {
                    loadJs(str2, "");
                }
            }

            @Override
            public void onFailure(String str3, String str4) {
                loadJs(str2, "");
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                rxManager.add(disposable);
            }
        }, "getMyTokenFinancialList"));
    }

    public void getMyFinancialProjectList(String str, final String str2) {
        MyFinancialTokenListInput myFinancialTokenListInput = new MyFinancialTokenListInput();
        if (str != null && ("ALL".equals(str) || "all".equals(str) || "undefined".equals(str))) {
            myFinancialTokenListInput.setWalletAddress(getAllNativeWallets());
        } else {
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            myFinancialTokenListInput.setWalletAddress(arrayList);
        }
        LogUtils.d("FinacialAcToJs", GsonUtils.toGsonString(myFinancialTokenListInput));
        this.model.getMyFinancialProjectList(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), GsonUtils.toGsonString(myFinancialTokenListInput))).subscribe(new IObserver(new ICallback<FinanceDataBean>() {
            @Override
            public void onResponse(String str3, FinanceDataBean financeDataBean) {
                if (financeDataBean != null && financeDataBean.getData() != null) {
                    loadJs(str2, GsonUtils.toGsonString(financeDataBean.getData()));
                } else {
                    loadJs(str2, "");
                }
            }

            @Override
            public void onFailure(String str3, String str4) {
                loadJs(str2, "");
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                rxManager.add(disposable);
            }
        }, "getMyTokenFinancialList"));
    }

    public List<String> getAllNativeWallets() {
        ArrayList arrayList = new ArrayList();
        for (String str : WalletUtils.getWalletNames()) {
            Wallet wallet = WalletUtils.getWallet(str);
            if (wallet != null && wallet.getAddress() != null && !wallet.isShieldedWallet() && !wallet.isWatchNotPaired()) {
                arrayList.add(wallet.getAddress());
            }
        }
        return arrayList;
    }

    public Observable<VoteBean> vote(final long j) {
        final Wallet selectedWallet = WalletUtils.getSelectedWallet();
        if (selectedWallet == null || StringTronUtil.isEmpty(selectedWallet.getAddress())) {
            return Observable.empty();
        }
        if (this.stakeSuccessModel == null) {
            this.stakeSuccessModel = new StakeSuccessModel();
        }
        final String address = selectedWallet.getAddress();
        return Observable.zip(this.stakeSuccessModel.getAccount(null, address), this.stakeSuccessModel.requestWitnessList(address, 0, 6, 3, 1), new BiFunction() {
            @Override
            public final Object apply(Object obj, Object obj2) {
                VoteBean lambda$vote$3;
                lambda$vote$3 = lambda$vote$3(j, address, selectedWallet, (Protocol.Account) obj, (WitnessOutput) obj2);
                return lambda$vote$3;
            }
        });
    }

    public VoteBean lambda$vote$3(long j, String str, Wallet wallet, Protocol.Account account, WitnessOutput witnessOutput) throws Exception {
        this.account = account;
        if (account == null || "".equals(account.toString()) || witnessOutput == null || witnessOutput.getData() == null || witnessOutput.getData().size() < 3) {
            return null;
        }
        long votingTps = ResourcesBean.buildVotes(new ResourcesBean(), account).getVotingTps() + j;
        HashMap<String, String> hashMap = new HashMap<>();
        HashMap<String, String> hashMap2 = new HashMap<>();
        if (votingTps <= 2) {
            hashMap.put(witnessOutput.getData().get(0).getAddress(), String.valueOf(votingTps));
            hashMap2.put(witnessOutput.getData().get(0).getAddress(), witnessOutput.getData().get(0).getName());
        } else {
            long size = votingTps % witnessOutput.getData().size();
            long size2 = votingTps / witnessOutput.getData().size();
            for (int i = 0; i < witnessOutput.getData().size(); i++) {
                String address = witnessOutput.getData().get(i).getAddress();
                String name = witnessOutput.getData().get(i).getName();
                if (size != 0 && i == 0) {
                    hashMap.put(address, String.valueOf(size2 + size));
                } else {
                    hashMap.put(address, String.valueOf(size2));
                }
                hashMap2.put(address, name);
            }
        }
        VoteBean voteBean = new VoteBean();
        voteBean.setSrcOrderedVoteMap(hashMap);
        voteBean.setSrNameMap(hashMap2);
        voteBean.setTotalVotes(votingTps);
        voteBean.setWalletAddress(str);
        voteBean.setSamsung(wallet.getCreateType() == 7);
        return voteBean;
    }

    public void stakeAndVote(final String str, VoteBean voteBean, long j, int i, long j2) {
        Protocol.Transaction replaceVoteWitnessContract;
        if (this.webView == null) {
            loadJs(str, "");
            return;
        }
        try {
            StakeAndVoteOutput stakeAndVoteOutput = new StakeAndVoteOutput();
            String walletAddress = voteBean.getWalletAddress();
            GrpcAPI.TransactionExtention freezeBalanceV2 = TronAPI.freezeBalanceV2(walletAddress, 1000000 * j, i == 0 ? Common.ResourceCode.BANDWIDTH : Common.ResourceCode.ENERGY);
            if (TransactionUtils.checkTransactionEmpty(freezeBalanceV2)) {
                stakeAndVoteOutput.setSuccessfullyFrozen(false);
                backError(str, stakeAndVoteOutput, freezeBalanceV2);
                return;
            }
            WitnessContract.VoteWitnessContract createVoteWitnessContract = TronAPI.createVoteWitnessContract(StringTronUtil.decode58Check(walletAddress), voteBean.getSrcOrderedVoteMap());
            if (voteBean.getOldTotalVotes() != 0) {
                HashMap hashMap = new HashMap();
                hashMap.put((String) Collection.-EL.stream(voteBean.getSrcOrderedVoteMap().keySet()).findFirst().get(), "1");
                GrpcAPI.TransactionExtention createVoteWitnessTransaction2 = TronAPI.createVoteWitnessTransaction2(StringTronUtil.decode58Check(walletAddress), hashMap);
                if (TransactionUtils.checkTransactionEmpty(createVoteWitnessTransaction2)) {
                    stakeAndVoteOutput.setSuccessfullyVoted(false);
                    backError(str, stakeAndVoteOutput, createVoteWitnessTransaction2);
                    return;
                }
                replaceVoteWitnessContract = TransactionUtils.replaceVoteWitnessContract(createVoteWitnessTransaction2.getTransaction(), createVoteWitnessContract);
            } else {
                replaceVoteWitnessContract = TransactionUtils.replaceVoteWitnessContract(freezeBalanceV2.getTransaction(), createVoteWitnessContract);
            }
            Protocol.Transaction transaction = replaceVoteWitnessContract;
            new StakeAndVoteParam().setPageFrom(BaseParam.PageFrom.Financial);
            String walletAddress2 = voteBean.getWalletAddress();
            String nameByAddress = AddressNameUtils.getInstance().getNameByAddress(walletAddress2);
            if (!TextUtils.isEmpty(nameByAddress)) {
                walletAddress2 = String.format(String.format("%s\n(%s)", nameByAddress, walletAddress2), new Object[0]);
            }
            String str2 = walletAddress2;
            this.rxManager.on(Event.EVENT_STAKE_AND_VOTE_DONE, new Consumer() {
                @Override
                public final void accept(Object obj) {
                    lambda$stakeAndVote$4(str, obj);
                }
            });
            ConfirmTransactionNewActivity.startActivity(this.webView.getContext(), ParamBuildUtils.getStakeAndVoteTransactionParamBuilder(i == 0, true, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, j, String.valueOf(j2), str2, voteBean, DataStatHelper.StatAction.FinanceStake, freezeBalanceV2.getTransaction(), transaction), voteBean.isSamsung());
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void lambda$stakeAndVote$4(String str, Object obj) throws Exception {
        LogUtils.i(" rxManager.on(EVENT_STAKE_AND_VOTE_DONE");
        if ((obj instanceof String) && "cancel".equals(obj)) {
            StakeAndVoteOutput stakeAndVoteOutput = new StakeAndVoteOutput();
            stakeAndVoteOutput.setErrorMessage("cancel");
            loadJs(str, GsonUtils.toGsonString(stakeAndVoteOutput));
        } else if (obj instanceof StakeAndVoteOutput) {
            loadJs(str, GsonUtils.toGsonString(obj));
        }
    }

    private void backError(String str, StakeAndVoteOutput stakeAndVoteOutput, GrpcAPI.TransactionExtention transactionExtention) {
        stakeAndVoteOutput.setErrorMessage(TransactionUtils.getErrorMessage(transactionExtention));
        loadJs(str, GsonUtils.toGsonString(stakeAndVoteOutput));
    }

    public void backStakeAndVoteError(String str) {
        StakeAndVoteOutput stakeAndVoteOutput = new StakeAndVoteOutput();
        stakeAndVoteOutput.setErrorMessage("cancel");
        loadJs(str, GsonUtils.toGsonString(stakeAndVoteOutput));
    }
}
