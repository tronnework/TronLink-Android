package com.tron.wallet.business.confirm.core.pending.fg.cancelunstake;

import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.interfaces.SimpleCallback;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.core.pending.fg.cancelunstake.CancelUnstakeSuccessContract;
import com.tron.wallet.business.confirm.core.pending.fg.stake.StakeSuccessVoteAdapter;
import com.tron.wallet.business.confirm.parambuilder.utils.ParamBuildUtils;
import com.tron.wallet.business.mutil.MultiSelectAddressActivity;
import com.tron.wallet.business.mutil.bean.MultiSignPermissionData;
import com.tron.wallet.business.mutil.bean.MultiSourcePageEnum;
import com.tron.wallet.business.stakev2.managementv2.ResourcesBean;
import com.tron.wallet.business.tabdapp.jsbridge.finance.Keys;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.business.vote.component.VoteSelectSRActivity;
import com.tron.wallet.business.vote.fastvote.VoteWitnessBean;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.DataStatHelper;
import com.tron.wallet.common.utils.SamsungMultisignUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.LedgerWallet;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function3;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class CancelUnstakeSuccessPresenter extends CancelUnstakeSuccessContract.Presenter {
    Protocol.Account account;
    StakeSuccessVoteAdapter adapter;
    String controllerAddress;
    boolean isLoading = true;
    ResourcesBean mResourcesBean;
    MultiSignPermissionData permissionData;

    public static void lambda$showMultiSignDialog$8(View view) {
    }

    @Override
    protected void onStart() {
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        if (selectedWallet != null) {
            this.controllerAddress = selectedWallet.getAddress();
        }
        this.mRxManager.on(Event.Vote_PENDING, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$0(obj);
            }
        });
        this.mRxManager.on(Event.VOTE_SUCCESS, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$1(obj);
            }
        });
        this.mRxManager.on(Event.VOTE_TO_MULTI_VOTE, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$2(obj);
            }
        });
        this.mRxManager.on(Event.BroadcastSuccess, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$3(obj);
            }
        });
        this.mRxManager.on(Event.BroadcastSuccess2, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$4(obj);
            }
        });
    }

    public void lambda$onStart$0(Object obj) throws Exception {
        if (this.mView != 0) {
            ((CancelUnstakeSuccessContract.View) this.mView).exit();
        }
    }

    public void lambda$onStart$1(Object obj) throws Exception {
        if (this.mView != 0) {
            ((CancelUnstakeSuccessContract.View) this.mView).exit();
        }
    }

    public void lambda$onStart$2(Object obj) throws Exception {
        if (this.mView != 0) {
            ((CancelUnstakeSuccessContract.View) this.mView).exit();
        }
    }

    public void lambda$onStart$3(Object obj) throws Exception {
        if (this.mView != 0) {
            ((CancelUnstakeSuccessContract.View) this.mView).exit();
        }
    }

    public void lambda$onStart$4(Object obj) throws Exception {
        if (this.mView != 0) {
            ((CancelUnstakeSuccessContract.View) this.mView).exit();
        }
    }

    @Override
    public void initVoteData() {
        Observable.zip(((CancelUnstakeSuccessContract.Model) this.mModel).getAccount(null, this.controllerAddress), ((CancelUnstakeSuccessContract.Model) this.mModel).getAccountResourceMessage(null, this.controllerAddress), ((CancelUnstakeSuccessContract.Model) this.mModel).requestWitnessList(this.controllerAddress, 0, 6, 3, 1), new Function3() {
            @Override
            public final Object apply(Object obj, Object obj2, Object obj3) {
                Pair lambda$initVoteData$5;
                lambda$initVoteData$5 = lambda$initVoteData$5((Protocol.Account) obj, (GrpcAPI.AccountResourceMessage) obj2, (WitnessOutput) obj3);
                return lambda$initVoteData$5;
            }
        }).compose(RxSchedulers2.io_main()).subscribe(new IObserver(new ICallback<Pair<Long, ArrayList<VoteWitnessBean>>>() {
            @Override
            public void onResponse(String str, Pair<Long, ArrayList<VoteWitnessBean>> pair) {
                ((CancelUnstakeSuccessContract.View) mView).dismissLoadingDialog();
                if (pair.first != null && pair.second != null) {
                    long longValue = ((Long) pair.first).longValue();
                    ArrayList arrayList = (ArrayList) pair.second;
                    ((CancelUnstakeSuccessContract.View) mView).updateVoteApr(arrayList, longValue);
                    ((CancelUnstakeSuccessContract.View) mView).showVotes();
                    adapter = new StakeSuccessVoteAdapter(((CancelUnstakeSuccessContract.View) mView).getIContext(), arrayList);
                    ((CancelUnstakeSuccessContract.View) mView).getRecyclerView().setLayoutManager(new WrapContentLinearLayoutManager(((CancelUnstakeSuccessContract.View) mView).getIContext(), 1, false));
                    ((CancelUnstakeSuccessContract.View) mView).getRecyclerView().setAdapter(adapter);
                }
                isLoading = false;
            }

            @Override
            public void onFailure(String str, String str2) {
                ((CancelUnstakeSuccessContract.View) mView).showLoadFailed();
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "UnStakeV2Presenter_GetRes"));
    }

    public Pair lambda$initVoteData$5(Protocol.Account account, GrpcAPI.AccountResourceMessage accountResourceMessage, WitnessOutput witnessOutput) throws Exception {
        this.account = account;
        ResourcesBean buildV2 = ResourcesBean.buildV2(account, accountResourceMessage);
        this.mResourcesBean = buildV2;
        long votingTps = buildV2.getVotingTps();
        ArrayList arrayList = new ArrayList();
        if (votingTps <= 2) {
            VoteWitnessBean voteWitnessBean = new VoteWitnessBean();
            voteWitnessBean.setDataBean(witnessOutput.getData().get(0));
            voteWitnessBean.setVisibility(false);
            voteWitnessBean.setVoteCount(String.valueOf(votingTps));
            arrayList.add(voteWitnessBean);
        } else {
            long size = votingTps % witnessOutput.getData().size();
            for (int i = 0; i < witnessOutput.getData().size(); i++) {
                VoteWitnessBean voteWitnessBean2 = new VoteWitnessBean();
                voteWitnessBean2.setDataBean(witnessOutput.getData().get(i));
                voteWitnessBean2.setVisibility(false);
                long size2 = votingTps / witnessOutput.getData().size();
                if (votingTps >= 3) {
                    int i2 = (int) size;
                    if (i2 == 1) {
                        if (i != 0) {
                        }
                        size2 += size;
                    } else if (i2 == 2) {
                        if (i != 0) {
                        }
                        size2 += size;
                    }
                }
                voteWitnessBean2.setVoteCount(String.valueOf(size2));
                arrayList.add(voteWitnessBean2);
            }
        }
        return new Pair(Long.valueOf(votingTps), arrayList);
    }

    @Override
    public void VoteToProfit() {
        if (this.isLoading) {
            return;
        }
        final Wallet selectedWallet = WalletUtils.getSelectedWallet();
        final DataStatHelper.StatAction statAction = DataStatHelper.StatAction.Vote;
        final HashMap hashMap = new HashMap();
        new HashMap();
        final HashMap hashMap2 = new HashMap();
        List<VoteWitnessBean> data = this.adapter.getData();
        for (int i = 0; i < data.size(); i++) {
            VoteWitnessBean voteWitnessBean = data.get(i);
            hashMap.put(voteWitnessBean.getDataBean().getAddress(), voteWitnessBean.getVoteCount());
            hashMap2.put(voteWitnessBean.getDataBean().getAddress(), voteWitnessBean.getDataBean().getName());
        }
        if (selectedWallet != null && selectedWallet.isWatchOnly() && !SpAPI.THIS.getCurrentChain().isMainChain) {
            ((CancelUnstakeSuccessContract.View) this.mView).toast(((CancelUnstakeSuccessContract.View) this.mView).getIContext().getString(R.string.no_support));
        } else if (selectedWallet != null && SamsungMultisignUtils.isSamsungMultiOwner(this.controllerAddress, this.account.getOwnerPermission())) {
            ((CancelUnstakeSuccessContract.View) this.mView).toast(((CancelUnstakeSuccessContract.View) this.mView).getIContext().getString(R.string.no_samsung_to_shield));
        } else if (hashMap.size() > 30) {
            ((CancelUnstakeSuccessContract.View) this.mView).toast(((CancelUnstakeSuccessContract.View) this.mView).getIContext().getString(R.string.vote_sr_less_than_30));
        } else if (LedgerWallet.isLedger(selectedWallet) && hashMap.size() > 5) {
            ((CancelUnstakeSuccessContract.View) this.mView).toast(((CancelUnstakeSuccessContract.View) this.mView).getIContext().getString(R.string.vote_ledger_less_than_five));
        } else {
            ((CancelUnstakeSuccessContract.View) this.mView).showLoadingDialog();
            ((CancelUnstakeSuccessContract.View) this.mView).runOnThreeThread(new OnBackground() {
                @Override
                public final void doOnBackground() {
                    lambda$VoteToProfit$6(selectedWallet, hashMap, hashMap2, r5, r6, statAction);
                }
            });
        }
    }

    public void lambda$VoteToProfit$6(final Wallet wallet, final HashMap hashMap, final HashMap hashMap2, final int i, final String str, final DataStatHelper.StatAction statAction) {
        ((CancelUnstakeSuccessContract.Model) this.mModel).getAccount(null, this.controllerAddress).subscribe(new Consumer<Protocol.Account>() {
            @Override
            public void accept(Protocol.Account account) throws Exception {
                if (account != null) {
                    try {
                        if (!WalletUtils.checkHaveOwnerPermissions(wallet.getAddress(), account.getOwnerPermission())) {
                            ((CancelUnstakeSuccessContract.View) mView).runOnUIThread(new OnMainThread() {
                                @Override
                                public void doInUIThread() {
                                    ((CancelUnstakeSuccessContract.View) mView).dismissLoadingDialog();
                                    showMultiSignDialog();
                                }
                            });
                            return;
                        }
                    } catch (Exception e) {
                        LogUtils.e(e);
                    }
                    final GrpcAPI.TransactionExtention createVoteWitnessTransaction2 = TronAPI.createVoteWitnessTransaction2(StringTronUtil.decodeFromBase58Check(controllerAddress), hashMap);
                    ((CancelUnstakeSuccessContract.View) mView).runOnUIThread(new OnMainThread() {
                        @Override
                        public void doInUIThread() {
                            ((CancelUnstakeSuccessContract.View) mView).dismissLoadingDialog();
                            Bundle bundle = new Bundle();
                            bundle.putString(NotificationCompat.CATEGORY_EVENT, Keys.OV__vote);
                            Context context = AppContextUtil.getContext();
                            try {
                                Wallet wallet2 = wallet;
                                GrpcAPI.TransactionExtention transactionExtention = createVoteWitnessTransaction2;
                                if (transactionExtention != null && transactionExtention.hasResult() && !"".equals(createVoteWitnessTransaction2.getTransaction().toString())) {
                                    Protocol.Transaction transaction = createVoteWitnessTransaction2.getTransaction();
                                    if (transaction != null) {
                                        bundle.putString("value", "1");
                                        FirebaseAnalytics.getInstance(context).logEvent("Click_vote_button", bundle);
                                        TronConfig.currentPwdType = 9;
                                        ConfirmTransactionNewActivity.startActivity(context, ParamBuildUtils.getVoteTransactionParamBuilder(hashMap2, hashMap, true, 1, transaction, i, controllerAddress, 0L, str, true, statAction, ((CancelUnstakeSuccessContract.View) mView).getFormatApr()), wallet2.getCreateType() == 7);
                                    } else {
                                        bundle.putString("value", "0");
                                        FirebaseAnalytics.getInstance(context).logEvent("Click_vote_button", bundle);
                                        ((CancelUnstakeSuccessContract.View) mView).toast(((CancelUnstakeSuccessContract.View) mView).getIContext().getString(R.string.time_out));
                                    }
                                } else {
                                    bundle.putString("value", "0");
                                    FirebaseAnalytics.getInstance(context).logEvent("Click_vote_button", bundle);
                                    GrpcAPI.TransactionExtention transactionExtention2 = createVoteWitnessTransaction2;
                                    if (transactionExtention2 != null && transactionExtention2.hasResult() && 2 == createVoteWitnessTransaction2.getResult().getCode().getNumber()) {
                                        ((CancelUnstakeSuccessContract.View) mView).toast(((CancelUnstakeSuccessContract.View) mView).getIContext().getString(R.string.CONTRACT_VALIDATE_ERROR));
                                    } else {
                                        ((CancelUnstakeSuccessContract.View) mView).toast(((CancelUnstakeSuccessContract.View) mView).getIContext().getString(R.string.time_out));
                                    }
                                }
                            } catch (Exception e2) {
                                bundle.putString("value", "1");
                                FirebaseAnalytics.getInstance(context).logEvent("Click_vote_button", bundle);
                                LogUtils.e(e2);
                                ((CancelUnstakeSuccessContract.View) mView).toast(((CancelUnstakeSuccessContract.View) mView).getIContext().getString(R.string.time_out));
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public void startOtherVote() {
        ((CancelUnstakeSuccessContract.View) this.mView).showLoadingDialog();
        ((CancelUnstakeSuccessContract.View) this.mView).runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$startOtherVote$7();
            }
        });
    }

    public void lambda$startOtherVote$7() {
        ((CancelUnstakeSuccessContract.Model) this.mModel).getAccount(null, this.controllerAddress).subscribe(new Consumer<Protocol.Account>() {
            @Override
            public void accept(final Protocol.Account account) throws Exception {
                if (account != null) {
                    try {
                        if (!WalletUtils.checkHaveOwnerPermissions(controllerAddress, account.getOwnerPermission())) {
                            ((CancelUnstakeSuccessContract.View) mView).runOnUIThread(new OnMainThread() {
                                @Override
                                public void doInUIThread() {
                                    ((CancelUnstakeSuccessContract.View) mView).dismissLoadingDialog();
                                    showMultiSignDialog();
                                }
                            });
                            return;
                        }
                    } catch (Exception e) {
                        LogUtils.e(e);
                    }
                    final long votingTps = mResourcesBean.getVotingTps();
                    ((CancelUnstakeSuccessContract.View) mView).runOnUIThread(new OnMainThread() {
                        @Override
                        public void doInUIThread() {
                            ((CancelUnstakeSuccessContract.View) mView).dismissLoadingDialog();
                            VoteSelectSRActivity.start(((CancelUnstakeSuccessContract.View) mView).getIContext(), account, false, controllerAddress, null, permissionData, 5, votingTps, true, DataStatHelper.StatAction.Vote);
                        }
                    });
                }
            }
        });
    }

    public void showMultiSignDialog() {
        ConfirmCustomPopupView.getBuilder(((CancelUnstakeSuccessContract.View) this.mView).getIContext()).setTitle(((CancelUnstakeSuccessContract.View) this.mView).getIContext().getString(R.string.vote_no_owner_permission)).setTitleSize(16).setPopupCallback(new SimpleCallback() {
            @Override
            public void onDismiss(BasePopupView basePopupView) {
            }
        }).setConfirmText(((CancelUnstakeSuccessContract.View) this.mView).getIContext().getString(R.string.multisig)).setCancleListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                CancelUnstakeSuccessPresenter.lambda$showMultiSignDialog$8(view);
            }
        }).setConfirmListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showMultiSignDialog$9(view);
            }
        }).build().show();
    }

    public void lambda$showMultiSignDialog$9(View view) {
        checkAndEnterMultiSign();
    }

    private void checkAndEnterMultiSign() {
        enterMultiSign();
    }

    private void enterMultiSign() {
        try {
            MultiSelectAddressActivity.start(((CancelUnstakeSuccessContract.View) this.mView).getIContext(), MultiSourcePageEnum.Vote);
        } catch (Throwable th) {
            LogUtils.e(th);
        }
    }
}
