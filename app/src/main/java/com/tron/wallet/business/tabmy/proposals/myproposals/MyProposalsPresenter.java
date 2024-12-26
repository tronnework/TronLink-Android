package com.tron.wallet.business.tabmy.proposals.myproposals;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.arasthel.asyncjob.AsyncJob;
import com.google.protobuf.ByteString;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.parambuilder.utils.ParamBuildUtils;
import com.tron.wallet.business.tabmy.proposals.makeproposal.MakeProposalActivity;
import com.tron.wallet.business.tabmy.proposals.myproposals.MyProposalsContract;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.adapter.proposal.ProposalsAdapter;
import com.tron.wallet.common.adapter.proposal.ProposalsListAdapter;
import com.tron.wallet.common.components.dialog.CustomDialog;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.SamsungMultisignUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.walletserver.ConnectErrorException;
import org.tron.walletserver.Wallet;
public class MyProposalsPresenter extends MyProposalsContract.Presenter {
    private static final String TAG = "MyProposalsPresenter";
    private Protocol.Account account;
    private String currentAddress;
    private CustomDialog dialog;
    private boolean hasPer = true;
    private List<Protocol.Proposal> mProposalsList;
    private Wallet mWallet;
    private LinearLayoutManager manager;
    private HashMap<String, WitnessOutput.DataBean> nameMap;
    private ProposalsAdapter.ProposalClickListener proposalListener;
    private List<Protocol.Proposal> proposalsAllList;
    private ProposalsListAdapter proposalsListAdapter;
    private List<WitnessOutput.DataBean> witnessList;

    @Override
    protected void onStart() {
        this.witnessList = new ArrayList();
        this.proposalsAllList = new ArrayList();
        this.nameMap = new HashMap<>();
    }

    @Override
    public void addSome(List<Protocol.Proposal> list, String str, List<WitnessOutput.DataBean> list2) {
        this.currentAddress = str;
        this.mProposalsList = list;
        this.witnessList = list2;
        this.mWallet = WalletUtils.getSelectedWallet();
        this.manager = new LinearLayoutManager(((MyProposalsContract.View) this.mView).getIContext(), 1, false);
        ((MyProposalsContract.View) this.mView).getRcList().setLayoutManager(this.manager);
        if (list2 != null && list2.size() > 0) {
            for (int i = 0; i < list2.size(); i++) {
                WitnessOutput.DataBean dataBean = list2.get(i);
                if (dataBean != null && !StringTronUtil.isEmpty(dataBean.getAddress())) {
                    this.nameMap.put(dataBean.getAddress(), dataBean);
                }
            }
        }
        initListener();
        initRx();
        initAccount();
    }

    private void initAccount() {
        ((MyProposalsContract.View) this.mView).runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$initAccount$1();
            }
        });
    }

    public void lambda$initAccount$1() {
        try {
            this.account = TronAPI.queryAccount(StringTronUtil.decodeFromBase58Check(this.currentAddress), false);
            GrpcAPI.ProposalList listProposals = TronAPI.listProposals();
            if (listProposals != null) {
                this.proposalsAllList = listProposals.getProposalsList();
            }
        } catch (ConnectErrorException e) {
            LogUtils.e(e);
        }
        ((MyProposalsContract.View) this.mView).runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$initAccount$0();
            }
        });
    }

    public void lambda$initAccount$0() {
        ((MyProposalsContract.View) this.mView).dismissLoadingPage();
        List<Protocol.Proposal> list = this.mProposalsList;
        if (list != null && list.size() > 0) {
            ((MyProposalsContract.View) this.mView).showNoDataLayout(false);
            ProposalsListAdapter proposalsListAdapter = this.proposalsListAdapter;
            if (proposalsListAdapter == null) {
                ProposalsListAdapter proposalsListAdapter2 = new ProposalsListAdapter(((MyProposalsContract.View) this.mView).getIContext(), this.mProposalsList, this.currentAddress, this.proposalListener, "");
                this.proposalsListAdapter = proposalsListAdapter2;
                proposalsListAdapter2.setNameMap(this.nameMap, this.witnessList);
                ((MyProposalsContract.View) this.mView).getRcList().setAdapter(this.proposalsListAdapter);
                return;
            }
            proposalsListAdapter.setNameMap(this.nameMap, this.witnessList);
            this.proposalsListAdapter.notifyData(this.mProposalsList, this.currentAddress, "");
            return;
        }
        ((MyProposalsContract.View) this.mView).showNoDataLayout(true);
    }

    @Override
    protected void initListener() {
        this.proposalListener = new ProposalsAdapter.ProposalClickListener() {
            @Override
            public void onSeleted(int i) {
            }

            @Override
            public void myProposalsClick() {
                LogUtils.i(MyProposalsPresenter.TAG, "myProposalsClick");
            }

            @Override
            public void approvedProClick() {
                LogUtils.i(MyProposalsPresenter.TAG, "approvedProClick");
            }

            @Override
            public void changeAddClick() {
                LogUtils.i(MyProposalsPresenter.TAG, "changeAddClick");
            }

            @Override
            public void onEditTextChange(String str) {
                LogUtils.i(MyProposalsPresenter.TAG, "onEditTextChange");
            }

            @Override
            public void onAgreeClick(Protocol.Proposal proposal, boolean z, HashMap<Long, String> hashMap) {
                if (SamsungMultisignUtils.isSamsungWallet(currentAddress) || SamsungMultisignUtils.isSamsungMultisign(currentAddress) || SamsungMultisignUtils.isSamsungMultiOwner(currentAddress, account.getOwnerPermission())) {
                    ((MyProposalsContract.View) mView).toast(((MyProposalsContract.View) mView).getIContext().getString(R.string.no_samsung_to_shield));
                } else if (account != null && account.getIsWitness()) {
                    createProposalTranscation(proposal.getProposalId(), !z, hashMap);
                } else {
                    MyProposalsPresenter myProposalsPresenter = MyProposalsPresenter.this;
                    myProposalsPresenter.showDialog(((MyProposalsContract.View) myProposalsPresenter.mView).getIContext().getResources().getString(R.string.no_committee_tip), TronConfig.getCommitUrl());
                }
            }

            @Override
            public void onTimeEnd() {
                getData();
            }
        };
    }

    @Override
    public void getData() {
        ((MyProposalsContract.View) this.mView).showLoading(true);
        ((MyProposalsContract.View) this.mView).runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$getData$3();
            }
        });
    }

    public void lambda$getData$3() {
        try {
            this.account = TronAPI.queryAccount(StringTronUtil.decodeFromBase58Check(this.currentAddress), false);
            GrpcAPI.ProposalList listProposals = TronAPI.listProposals();
            if (listProposals != null) {
                this.proposalsAllList = listProposals.getProposalsList();
            }
        } catch (ConnectErrorException e) {
            LogUtils.e(e);
        }
        if (this.mView != 0) {
            ((MyProposalsContract.View) this.mView).runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$getData$2();
                }
            });
        }
    }

    @Override
    public void makeProposal() {
        Protocol.Account account = this.account;
        if (account != null && account.getIsWitness()) {
            MakeProposalActivity.start(((MyProposalsContract.View) this.mView).getIContext(), this.currentAddress);
        } else {
            showDialog(((MyProposalsContract.View) this.mView).getIContext().getResources().getString(R.string.no_committee_tip), TronConfig.getCommitUrl());
        }
    }

    public void lambda$getData$2() {
        ((MyProposalsContract.Model) this.mModel).getWitnessList().subscribe(new IObserver(new ICallback<WitnessOutput>() {
            @Override
            public void onResponse(String str, WitnessOutput witnessOutput) {
                if (witnessOutput != null) {
                    witnessList = witnessOutput.getData();
                    if (witnessList == null || witnessList.size() <= 0) {
                        return;
                    }
                    for (int i = 0; i < witnessList.size(); i++) {
                        nameMap.put(((WitnessOutput.DataBean) witnessList.get(i)).getAddress(), (WitnessOutput.DataBean) witnessList.get(i));
                    }
                    getProposalList();
                }
            }

            @Override
            public void onFailure(String str, String str2) {
                getProposalList();
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "getWitnessList"));
    }

    public void getProposalList() {
        List<ByteString> approvalsList;
        this.mProposalsList = new ArrayList();
        String fromType = ((MyProposalsContract.View) this.mView).getFromType();
        List<Protocol.Proposal> list = this.proposalsAllList;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < this.proposalsAllList.size(); i++) {
                Protocol.Proposal proposal = this.proposalsAllList.get(i);
                if (proposal != null) {
                    String encode58Check = StringTronUtil.encode58Check(proposal.getProposerAddress().toByteArray());
                    if (fromType.equals(MyProposalsActivity.TYPE_FORM_MY_PROPOSALS)) {
                        if (!StringTronUtil.isEmpty(encode58Check) && this.currentAddress.equals(encode58Check)) {
                            this.mProposalsList.add(proposal);
                        }
                    } else if (proposal.getApprovalsList() != null && (approvalsList = proposal.getApprovalsList()) != null && approvalsList.size() > 0) {
                        for (int i2 = 0; i2 < approvalsList.size(); i2++) {
                            if (this.currentAddress.equals(StringTronUtil.encode58Check(approvalsList.get(i2).toByteArray()))) {
                                this.mProposalsList.add(proposal);
                            }
                        }
                    }
                }
            }
        }
        ((MyProposalsContract.View) this.mView).dismissLoadingPage();
        ((MyProposalsContract.View) this.mView).showLoading(false);
        List<Protocol.Proposal> list2 = this.mProposalsList;
        if (list2 == null || list2.size() == 0) {
            ((MyProposalsContract.View) this.mView).showNoDataLayout(true);
            return;
        }
        ProposalsListAdapter proposalsListAdapter = this.proposalsListAdapter;
        if (proposalsListAdapter == null) {
            ProposalsListAdapter proposalsListAdapter2 = new ProposalsListAdapter(((MyProposalsContract.View) this.mView).getIContext(), this.mProposalsList, this.currentAddress, this.proposalListener, "");
            this.proposalsListAdapter = proposalsListAdapter2;
            proposalsListAdapter2.setNameMap(this.nameMap, this.witnessList);
            ((MyProposalsContract.View) this.mView).getRcList().setAdapter(this.proposalsListAdapter);
            return;
        }
        proposalsListAdapter.setNameMap(this.nameMap, this.witnessList);
        this.proposalsListAdapter.notifyData(this.mProposalsList, this.currentAddress, "");
    }

    private void initRx() {
        this.mRxManager.on(Event.BroadcastSuccess, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRx$4(obj);
            }
        });
        this.mRxManager.on(Event.BroadcastFail, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRx$5(obj);
            }
        });
    }

    public void lambda$initRx$4(Object obj) throws Exception {
        getData();
    }

    public void lambda$initRx$5(Object obj) throws Exception {
        getData();
    }

    public void createProposalTranscation(final long j, final boolean z, final HashMap<Long, String> hashMap) {
        ((MyProposalsContract.View) this.mView).showLoading(((MyProposalsContract.View) this.mView).getIContext().getString(R.string.loading2));
        ((MyProposalsContract.View) this.mView).runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$createProposalTranscation$7(j, z, hashMap);
            }
        });
    }

    public void lambda$createProposalTranscation$7(final long j, final boolean z, final HashMap hashMap) {
        GrpcAPI.TransactionExtention createProposalApproveTranscation = TronAPI.createProposalApproveTranscation(StringTronUtil.decodeFromBase58Check(this.currentAddress), j, z);
        try {
            Wallet wallet = this.mWallet;
            if (wallet != null && this.account != null) {
                this.hasPer = WalletUtils.checkHaveOwnerPermissions(wallet.getAddress(), this.account.getOwnerPermission());
            }
        } catch (Exception unused) {
            this.hasPer = true;
        }
        if (createProposalApproveTranscation != null) {
            try {
                if (createProposalApproveTranscation.hasResult()) {
                    final Protocol.Transaction transaction = createProposalApproveTranscation.getTransaction();
                    AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                        @Override
                        public final void doInUIThread() {
                            lambda$createProposalTranscation$6(transaction, z, j, hashMap);
                        }
                    });
                }
            } catch (Exception e) {
                LogUtils.e(e);
                ((MyProposalsContract.View) this.mView).ToastSuc(R.string.create_transaction_fail);
                return;
            }
        }
        ((MyProposalsContract.View) this.mView).ToastSuc(R.string.create_transaction_fail);
    }

    public void lambda$createProposalTranscation$6(Protocol.Transaction transaction, boolean z, long j, HashMap hashMap) {
        ((MyProposalsContract.View) this.mView).showLoading(false);
        if (transaction != null && transaction.toString().length() > 0) {
            ConfirmTransactionNewActivity.startActivity(((MyProposalsContract.View) this.mView).getIContext(), ParamBuildUtils.getApprovalProposalTransactionParamBuilder(this.hasPer, z ? R.mipmap.ic_confirm_approve_proposal : R.mipmap.ic_confirm_cancel_approval, z ? R.string.confirm_approve_proposal2 : R.string.cancel_approve_proposal2, transaction, String.valueOf(j), hashMap), this.mWallet.getCreateType() == 7);
        } else {
            ((MyProposalsContract.View) this.mView).ToastSuc(R.string.create_transaction_fail);
        }
    }

    public void showDialog(String str, final String str2) {
        CustomDialog.Builder builder = new CustomDialog.Builder(((MyProposalsContract.View) this.mView).getIContext());
        CustomDialog build = builder.style(R.style.loading_dialog).cancelTouchout(true).heightDimenRes(-2).widthDimenRes(-1).setGravity(17).view(R.layout.fail_dialog).build();
        this.dialog = build;
        build.setCanceledOnTouchOutside(false);
        View view = builder.getView();
        TextView textView = (TextView) view.findViewById(R.id.tv_cancle);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_ok);
        ((TextView) view.findViewById(R.id.tv_content)).setText(str);
        textView.setText(R.string.cancle);
        textView2.setText(R.string.canfirm);
        ((LinearLayout) view.findViewById(R.id.ll_double)).setVisibility(View.VISIBLE);
        ((TextView) view.findViewById(R.id.tv_ok2)).setVisibility(View.GONE);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                dialog.dismiss();
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                dialog.dismiss();
                CommonWebActivityV3.start(((MyProposalsContract.View) mView).getIContext(), str2, StringTronUtil.getResString(R.string.canfirm), -2, false);
            }
        });
        if (((Activity) ((MyProposalsContract.View) this.mView).getIContext()).isFinishing()) {
            return;
        }
        this.dialog.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CustomDialog customDialog = this.dialog;
        if (customDialog != null) {
            customDialog.dismiss();
        }
    }
}
