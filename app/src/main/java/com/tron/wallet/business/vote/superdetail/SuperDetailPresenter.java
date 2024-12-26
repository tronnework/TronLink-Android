package com.tron.wallet.business.vote.superdetail;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.core.CenterPopupView;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.util.XPopupUtils;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.StringTronUtil;
import com.tron.wallet.business.confirm.parambuilder.BaseConfirmTransParamBuilder;
import com.tron.wallet.business.tabmy.proposals.bean.SRDetailBean;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.business.vote.fastvote.FastVoteActivity;
import com.tron.wallet.business.vote.superdetail.SuperDetailContract;
import com.tron.wallet.business.vote.util.ProfitCalculator;
import com.tron.wallet.common.config.CommonBundleKeys;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.DataStatHelper;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.VoteAprCalculator;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class SuperDetailPresenter extends SuperDetailContract.Presenter {
    private Protocol.Account account;
    private long allVoteRightCount;
    private String currentAddress;
    private String currentControlName;
    private boolean fromMultiSign;
    private Wallet mWallet;
    private long myVotedCount;
    private List<WitnessOutput.DataBean> myVotedWitness;
    private BasePopupView popupView;
    private String srAddress;
    private DataStatHelper.StatAction statAction = DataStatHelper.StatAction.Vote;
    private WitnessOutput.DataBean witness;
    private WitnessOutput witnessOutput;

    interface Action0<R> {
        R run() throws Exception;
    }

    @Override
    protected void onStart() {
        this.mRxManager.on(Event.BackToVoteHome, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$0(obj);
            }
        });
        this.mRxManager.on(Event.BroadcastSuccess, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$1(obj);
            }
        });
    }

    public void lambda$onStart$0(Object obj) throws Exception {
        if (this.mView != 0) {
            ((SuperDetailContract.View) this.mView).exit();
        }
    }

    public void lambda$onStart$1(Object obj) throws Exception {
        if (this.mView != 0) {
            ((SuperDetailContract.View) this.mView).exit();
        }
    }

    @Override
    public void getData() {
        if (this.witness == null) {
            return;
        }
        Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$getData$2(observableEmitter);
            }
        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$getData$3((SRDetailBean) obj);
            }
        });
    }

    public void lambda$getData$2(ObservableEmitter observableEmitter) throws Exception {
        SRDetailBean sRDetailBean = new SRDetailBean();
        sRDetailBean.setCode(1);
        sRDetailBean.setAddress(this.witness.getAddress());
        sRDetailBean.setLinkUrl(this.witness.getUrl());
        sRDetailBean.setName(this.witness.getName());
        sRDetailBean.setProfit(String.valueOf(100 - this.witness.getBrokerage()));
        sRDetailBean.setRank((int) this.witness.getRealTimeRanking());
        sRDetailBean.setVotePercent(this.witness.getVotesPercentage());
        sRDetailBean.setVotes(this.witness.getRealTimeVotes());
        sRDetailBean.setBlocks(this.witness.getProduced_total());
        sRDetailBean.setYield(VoteAprCalculator.formatAprPercent(Math.floor(ProfitCalculator.getWitnessProfit(this.allVoteRightCount, this.witness) * 100.0d) / 100.0d) + "%");
        observableEmitter.onNext(sRDetailBean);
    }

    public void lambda$getData$3(SRDetailBean sRDetailBean) throws Exception {
        if (sRDetailBean == null || sRDetailBean.getCode() < 0) {
            SentryUtil.captureException(new Exception("Failed to get SR detail!"));
        }
        if (this.mView != 0) {
            ((SuperDetailContract.View) this.mView).updateUI(sRDetailBean);
        }
    }

    @Override
    public void init(Protocol.Account account, WitnessOutput witnessOutput, WitnessOutput.DataBean dataBean) {
        Context iContext = ((SuperDetailContract.View) this.mView).getIContext();
        this.account = account;
        this.witnessOutput = witnessOutput;
        this.witness = dataBean;
        if (iContext instanceof Activity) {
            Activity activity = (Activity) iContext;
            this.allVoteRightCount = activity.getIntent().getLongExtra("key_all_votes", 0L);
            this.currentAddress = activity.getIntent().getStringExtra("key_select_address");
            this.currentControlName = activity.getIntent().getStringExtra("key_controller_name");
            this.fromMultiSign = activity.getIntent().getBooleanExtra(CommonBundleKeys.KEY_FROM_MULTI_SIGN, false);
            this.myVotedWitness = activity.getIntent().getParcelableArrayListExtra(CommonBundleKeys.KEY_ALREADY_VOTED_WITNESSES);
            this.mWallet = WalletUtils.getWalletForAddress(this.currentAddress);
            this.myVotedCount = activity.getIntent().getIntExtra(SuperDetailActivity.KEY_VOTED_COUNT, 0);
            this.statAction = (DataStatHelper.StatAction) activity.getIntent().getSerializableExtra(TronConfig.StatAction_Key);
        }
    }

    @Override
    public void vote() {
        vote(0);
    }

    @Override
    public void voteCancel() {
        vote(1);
    }

    @Override
    public void voteUpdate() {
        vote(2);
    }

    private void vote(int i) {
        List<WitnessOutput.DataBean> list;
        if (i == 1) {
            if (this.myVotedCount == 1 && (list = this.myVotedWitness) != null && list.size() == 1 && this.myVotedWitness.contains(this.witness)) {
                showDialog();
                return;
            } else {
                cancelVote(this.fromMultiSign, this.allVoteRightCount, this.currentAddress, this.account);
                return;
            }
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int i2 = 0;
        while (true) {
            if (i2 >= this.witnessOutput.getData().size()) {
                break;
            }
            if (!StringTronUtil.isEmpty(this.witnessOutput.getData().get(i2).getVoted())) {
                if ((StringTronUtil.isEmpty(this.witnessOutput.getData().get(i2).getVoted()) ? 0L : Long.parseLong(this.witnessOutput.getData().get(i2).getVoted())) > 0) {
                    arrayList.add(this.witnessOutput.getData().get(i2));
                }
            }
            i2++;
        }
        arrayList2.add(this.witness);
        FastVoteActivity.startActivity(((SuperDetailContract.View) this.mView).getIContext(), this.account, arrayList2, 2, StringTronUtil.isEmpty(this.witness.getVoted()) ? 0L : Long.parseLong(this.witness.getVoted()), this.allVoteRightCount, (ArrayList) this.myVotedWitness, this.currentAddress, this.currentControlName, this.fromMultiSign, this.statAction);
    }

    private void showDialog() {
        BasePopupView asCustom = new XPopup.Builder(((SuperDetailContract.View) this.mView).getIContext()).maxWidth(XPopupUtils.getScreenWidth(((SuperDetailContract.View) this.mView).getIContext())).dismissOnTouchOutside(false).popupAnimation(PopupAnimation.NoAnimation).asCustom(new CenterPopupView(((SuperDetailContract.View) this.mView).getIContext()) {
            @Override
            public int getImplLayoutId() {
                return R.layout.confirm_got_dialog;
            }

            @Override
            public void onCreate() {
                super.onCreate();
                ((TextView) findViewById(R.id.content)).setText(R.string.vote_min_count_1);
                Button button = (Button) findViewById(R.id.btn_confirm);
                button.setText(R.string.i_know);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupView.dismiss();
                    }
                });
            }
        });
        this.popupView = asCustom;
        asCustom.show();
    }

    private void cancelVote(final boolean z, final long j, final String str, final Protocol.Account account) {
        ((SuperDetailContract.View) this.mView).showLoadingDialog();
        rxWrap(((SuperDetailContract.Model) this.mModel).getWitnessList(str, 30, 1, 0, 5, true).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$cancelVote$5;
                lambda$cancelVote$5 = lambda$cancelVote$5(account, j, z, str, (WitnessOutput) obj);
                return lambda$cancelVote$5;
            }
        }), new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$cancelVote$6((BaseConfirmTransParamBuilder) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$cancelVote$7((String) obj);
            }
        }, "requestCancel");
    }

    public ObservableSource lambda$cancelVote$5(final Protocol.Account account, final long j, final boolean z, final String str, final WitnessOutput witnessOutput) throws Exception {
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$cancelVote$4(account, j, witnessOutput, z, str, observableEmitter);
            }
        });
    }

    public void lambda$cancelVote$4(Protocol.Account account, long j, WitnessOutput witnessOutput, boolean z, String str, ObservableEmitter observableEmitter) throws Exception {
        long j2;
        long j3;
        Iterator<String> it;
        Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
        String checkCancelVoteError = ((SuperDetailContract.Model) this.mModel).checkCancelVoteError(account, selectedPublicWallet);
        if (!TextUtils.isEmpty(checkCancelVoteError)) {
            observableEmitter.onError(new Exception(checkCancelVoteError));
        } else {
            HashMap<String, String> hashMap = new HashMap<>();
            HashMap<String, String> hashMap2 = new HashMap<>();
            HashMap<String, String> hashMap3 = new HashMap<>();
            if (account != null) {
                for (Protocol.Vote vote : account.getVotesList()) {
                    hashMap3.put(com.tron.wallet.common.utils.StringTronUtil.encode58Check(vote.getVoteAddress().toByteArray()), String.valueOf(vote.getVoteCount()));
                }
            }
            if (!(hashMap3.size() <= 1)) {
                hashMap3.remove(this.witness.getAddress());
                long parseLong = Long.parseLong(this.witness.getVoted());
                Iterator<String> it2 = hashMap3.keySet().iterator();
                j3 = j;
                while (it2.hasNext()) {
                    String next = it2.next();
                    int i = 0;
                    while (i < witnessOutput.getData().size()) {
                        WitnessOutput.DataBean dataBean = witnessOutput.getData().get(i);
                        if (next.equals(dataBean.getAddress())) {
                            it = it2;
                            hashMap.put(dataBean.getAddress(), dataBean.getName());
                            hashMap2.put(dataBean.getAddress(), dataBean.getVoted());
                            j3 -= Long.parseLong(dataBean.getVoted());
                        } else {
                            it = it2;
                        }
                        i++;
                        it2 = it;
                    }
                }
                j2 = parseLong;
            } else {
                hashMap3.put(this.witness.getAddress(), String.valueOf(1));
                hashMap.put(this.witness.getAddress(), this.witness.getName());
                long parseLong2 = Long.parseLong(this.witness.getVoted()) - 1;
                if (parseLong2 < 0) {
                    parseLong2 = 0;
                }
                hashMap2.put(this.witness.getAddress(), String.valueOf(1));
                j2 = parseLong2;
                j3 = j - 1;
            }
            BigDecimalUtils.sum_(new BigDecimal(0), this.witness.getVoted());
            observableEmitter.onNext(((SuperDetailContract.Model) this.mModel).getCancelVoteParamBuilder(z, j2, j3, str, selectedPublicWallet, account, hashMap, hashMap2, hashMap3, j2, this.witness.getName(), this.statAction));
        }
        observableEmitter.onComplete();
    }

    public void lambda$cancelVote$6(BaseConfirmTransParamBuilder baseConfirmTransParamBuilder) throws Exception {
        ((SuperDetailContract.View) this.mView).onRequestCancelVoteComplete(true, null, baseConfirmTransParamBuilder);
    }

    public void lambda$cancelVote$7(String str) throws Exception {
        ((SuperDetailContract.View) this.mView).onRequestCancelVoteComplete(false, str, null);
    }

    private <R> void rxWrap(Observable<R> observable, final Consumer<R> consumer, final Consumer<String> consumer2, String str) {
        observable.subscribe(new IObserver(new ICallback<R>() {
            @Override
            public void onResponse(String str2, R r) {
                try {
                    consumer.accept(r);
                } catch (Exception e) {
                    LogUtils.e(e);
                    try {
                        consumer2.accept(e.getMessage());
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(String str2, String str3) {
                try {
                    consumer2.accept(str3);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                addDisposable(disposable);
            }
        }, str));
    }
}
