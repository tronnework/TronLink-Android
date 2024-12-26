package com.tron.wallet.business.tabmy.proposals;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.arasthel.asyncjob.AsyncJob;
import com.google.protobuf.ByteString;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.parambuilder.utils.ParamBuildUtils;
import com.tron.wallet.business.tabmy.proposals.ProposalsContract;
import com.tron.wallet.business.tabmy.proposals.makeproposal.MakeProposalActivity;
import com.tron.wallet.business.tabmy.proposals.myproposals.MyProposalsActivity;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.adapter.proposal.ProposalsAdapter;
import com.tron.wallet.common.components.RecyclerViewUtil;
import com.tron.wallet.common.components.dialog.CustomDialog;
import com.tron.wallet.common.components.ptr.PtrDefaultHandler;
import com.tron.wallet.common.components.ptr.PtrFrameLayout;
import com.tron.wallet.common.components.ptr.PtrHandler;
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
import java.util.Iterator;
import java.util.List;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.walletserver.ConnectErrorException;
import org.tron.walletserver.Wallet;
public class ProposalsPresenter extends ProposalsContract.Presenter {
    private static final String TAG = "ProposalsPresenter";
    private Protocol.Account account;
    private List<Protocol.Proposal> approvedProposalsList;
    private String currentSelectAddress;
    private CustomDialog dialog;
    private List<Protocol.Proposal> mSearchList;
    private Wallet mWallet;
    private LinearLayoutManager manager;
    private List<Protocol.Proposal> myProposalsList;
    private HashMap<String, WitnessOutput.DataBean> nameMap;
    private ProposalsAdapter.ProposalClickListener proposalListener;
    private ProposalsAdapter proposalsAdapter;
    private List<Protocol.Proposal> proposalsAllList;
    private RecyclerViewUtil recyclerViewUtil;
    private List<Protocol.Proposal> votingProposalList;
    private List<WitnessOutput.DataBean> witnessList;
    private String sort = "-number";
    private int currentType = 0;
    private String editInput = "";
    private boolean hasPer = true;

    @Override
    protected String getSelectAddress() {
        return this.currentSelectAddress;
    }

    @Override
    protected void onStart() {
        this.myProposalsList = new ArrayList();
        this.approvedProposalsList = new ArrayList();
        this.votingProposalList = new ArrayList();
        this.mSearchList = new ArrayList();
        this.witnessList = new ArrayList();
        this.proposalsAllList = new ArrayList();
        this.nameMap = new HashMap<>();
    }

    @Override
    public void getData() {
        ((ProposalsContract.View) this.mView).runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$getData$2();
            }
        });
    }

    public void lambda$getData$2() {
        try {
            this.account = TronAPI.queryAccount(StringTronUtil.decodeFromBase58Check(this.currentSelectAddress), false);
            GrpcAPI.ProposalList listProposals = TronAPI.listProposals();
            if (listProposals != null) {
                this.proposalsAllList = listProposals.getProposalsList();
                if ((IRequest.isTest() || IRequest.isNile()) && this.proposalsAllList.size() >= 100) {
                    this.proposalsAllList = this.proposalsAllList.subList(0, 100);
                }
            }
            ((ProposalsContract.View) this.mView).runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$getData$0();
                }
            });
        } catch (ConnectErrorException e) {
            LogUtils.e(e);
            ((ProposalsContract.View) this.mView).runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$getData$1();
                }
            });
        }
    }

    public void lambda$getData$1() {
        ((ProposalsContract.View) this.mView).dismissLoading();
        updateRecycleView();
    }

    public void lambda$getData$0() {
        ((ProposalsContract.Model) this.mModel).getWitnessList().subscribe(new IObserver(new ICallback<WitnessOutput>() {
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
                    updateRecycleView();
                    if (nameMap == null || nameMap.size() <= 0 || proposalsAdapter == null) {
                        return;
                    }
                    proposalsAdapter.notify(nameMap, witnessList);
                }
            }

            @Override
            public void onFailure(String str, String str2) {
                updateRecycleView();
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "getWitnessList"));
    }

    public void updateRecycleView() {
        List<ByteString> approvalsList;
        ((ProposalsContract.View) this.mView).getRcFrame().refreshComplete();
        try {
            List<Protocol.Proposal> list = this.proposalsAllList;
            if (list != null && list.size() > 0) {
                this.myProposalsList = new ArrayList();
                this.approvedProposalsList = new ArrayList();
                this.votingProposalList = new ArrayList();
                int size = this.proposalsAllList.size();
                for (int i = 0; i < size; i++) {
                    Protocol.Proposal proposal = this.proposalsAllList.get(i);
                    if (proposal != null) {
                        if (!proposal.getState().name().equals("APPROVED") && !proposal.getState().name().equals("CANCELED") && !proposal.getState().name().equals("DISAPPROVED")) {
                            this.votingProposalList.add(proposal);
                        }
                        String encode58Check = StringTronUtil.encode58Check(proposal.getProposerAddress().toByteArray());
                        if (!StringTronUtil.isEmpty(encode58Check) && this.currentSelectAddress.equals(encode58Check)) {
                            this.myProposalsList.add(proposal);
                        }
                        if (proposal.getApprovalsList() != null && (approvalsList = proposal.getApprovalsList()) != null && approvalsList.size() > 0) {
                            for (int i2 = 0; i2 < approvalsList.size(); i2++) {
                                if (this.currentSelectAddress.equals(StringTronUtil.encode58Check(approvalsList.get(i2).toByteArray()))) {
                                    this.approvedProposalsList.add(proposal);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
        ((ProposalsContract.View) this.mView).dismissLoading();
        onEditChange(this.editInput);
        this.recyclerViewUtil.setLoadMoreEnable(false);
    }

    @Override
    public void addSome() {
        initListener();
        initRx();
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        this.mWallet = selectedWallet;
        if (selectedWallet != null) {
            this.currentSelectAddress = selectedWallet.getAddress();
        }
        this.manager = new LinearLayoutManager(((ProposalsContract.View) this.mView).getIContext(), 1, false);
        ((ProposalsContract.View) this.mView).getRcList().setLayoutManager(this.manager);
        ((ProposalsContract.View) this.mView).getRcFrame().setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
                return PtrDefaultHandler.checkContentCanBePulledDown(ptrFrameLayout, ((ProposalsContract.View) mView).getRcList(), view2);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                onRefresh();
            }
        });
        RecyclerViewUtil recyclerViewUtil = new RecyclerViewUtil(((ProposalsContract.View) this.mView).getRcList());
        this.recyclerViewUtil = recyclerViewUtil;
        recyclerViewUtil.setRecyclerViewLoadMoreListener(new RecyclerViewUtil.RecyclerViewLoadMoreListener() {
            @Override
            public void onLoadMore() {
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            }
        });
        this.recyclerViewUtil.setLoadMoreEnable(true);
        ((DefaultItemAnimator) ((ProposalsContract.View) this.mView).getRcList().getItemAnimator()).setSupportsChangeAnimations(false);
        this.proposalsAdapter = new ProposalsAdapter(((ProposalsContract.View) this.mView).getIContext(), this.proposalListener, "0", "0", this.currentSelectAddress, this.proposalsAllList, this.currentType, this.nameMap, this.witnessList, true);
        ((ProposalsContract.View) this.mView).getRcList().setAdapter(this.proposalsAdapter);
    }

    @Override
    public void onRefresh() {
        this.recyclerViewUtil.setLoadMoreEnable(false);
        getData();
    }

    @Override
    public void makeProposal() {
        Protocol.Account account = this.account;
        if (account != null && account.getIsWitness()) {
            MakeProposalActivity.start(((ProposalsContract.View) this.mView).getIContext(), this.currentSelectAddress);
        } else if (SamsungMultisignUtils.isSamsungWallet(this.currentSelectAddress) || SamsungMultisignUtils.isSamsungMultisign(this.currentSelectAddress)) {
            ((ProposalsContract.View) this.mView).toast(((ProposalsContract.View) this.mView).getIContext().getString(R.string.no_samsung_to_shield));
        } else {
            showDialog(((ProposalsContract.View) this.mView).getIContext().getResources().getString(R.string.no_committee_tip), TronConfig.getCommitUrl());
        }
    }

    @Override
    protected void initListener() {
        this.proposalListener = new ProposalsAdapter.ProposalClickListener() {
            @Override
            public void onSeleted(int i) {
                LogUtils.i(ProposalsPresenter.TAG, "onSeleted");
                currentType = i;
                onEditTextChange(editInput);
            }

            @Override
            public void myProposalsClick() {
                MyProposalsActivity.start(((ProposalsContract.View) mView).getIContext(), MyProposalsActivity.TYPE_FORM_MY_PROPOSALS, myProposalsList, currentSelectAddress, witnessList);
            }

            @Override
            public void approvedProClick() {
                MyProposalsActivity.start(((ProposalsContract.View) mView).getIContext(), MyProposalsActivity.TYPE_FORM_APPROVED_PROPOSALS, approvedProposalsList, currentSelectAddress, witnessList);
            }

            @Override
            public void changeAddClick() {
                ChangeAddressActivity.start((BaseActivity) ((ProposalsContract.View) mView).getIContext(), StringTronUtil.getResString(R.string.change_proposal_address), StringTronUtil.getResString(R.string.proposal_address), StringTronUtil.getResString(R.string.multisignature_change_proposa), currentSelectAddress);
            }

            @Override
            public void onEditTextChange(String str) {
                editInput = str;
                onEditChange(str);
            }

            @Override
            public void onAgreeClick(Protocol.Proposal proposal, boolean z, HashMap<Long, String> hashMap) {
                if (SamsungMultisignUtils.isSamsungWallet(currentSelectAddress) || SamsungMultisignUtils.isSamsungMultisign(currentSelectAddress) || SamsungMultisignUtils.isSamsungMultiOwner(currentSelectAddress, account.getOwnerPermission())) {
                    ((ProposalsContract.View) mView).toast(((ProposalsContract.View) mView).getIContext().getString(R.string.no_samsung_to_shield));
                } else if (account != null && account.getIsWitness()) {
                    createProposalTranscation(proposal.getProposalId(), !z, hashMap);
                } else {
                    ProposalsPresenter proposalsPresenter = ProposalsPresenter.this;
                    proposalsPresenter.showDialog(((ProposalsContract.View) proposalsPresenter.mView).getIContext().getResources().getString(R.string.no_committee_tip), TronConfig.getCommitUrl());
                }
            }

            @Override
            public void onTimeEnd() {
                ((ProposalsContract.View) mView).showLoading(((ProposalsContract.View) mView).getIContext().getString(R.string.loading2));
                getData();
            }
        };
    }

    public void onEditChange(String str) {
        this.mSearchList = new ArrayList();
        if (StringTronUtil.isEmpty(str)) {
            if (this.currentType == 0) {
                this.mSearchList.addAll(this.proposalsAllList);
            } else {
                this.mSearchList.addAll(this.votingProposalList);
            }
        } else if (this.currentType == 0) {
            Iterator it = new ArrayList(this.proposalsAllList).iterator();
            while (it.hasNext()) {
                Protocol.Proposal proposal = (Protocol.Proposal) it.next();
                try {
                    if (checkFilterConditions(proposal, str)) {
                        this.mSearchList.add(proposal);
                    }
                } catch (NullPointerException unused) {
                }
            }
        } else {
            Iterator it2 = new ArrayList(this.votingProposalList).iterator();
            while (it2.hasNext()) {
                Protocol.Proposal proposal2 = (Protocol.Proposal) it2.next();
                try {
                    if (checkFilterConditions(proposal2, str)) {
                        this.mSearchList.add(proposal2);
                    }
                } catch (NullPointerException unused2) {
                }
            }
        }
        List<Protocol.Proposal> list = this.myProposalsList;
        int size = list == null ? 0 : list.size();
        List<Protocol.Proposal> list2 = this.approvedProposalsList;
        int size2 = list2 != null ? list2.size() : 0;
        ProposalsAdapter proposalsAdapter = this.proposalsAdapter;
        if (proposalsAdapter != null) {
            proposalsAdapter.notifyHeadData(this.currentSelectAddress, size + "", size2 + "");
            this.proposalsAdapter.notifyData(this.mSearchList, this.currentSelectAddress, str);
            return;
        }
        this.proposalsAdapter = new ProposalsAdapter(((ProposalsContract.View) this.mView).getIContext(), this.proposalListener, size + "", size2 + "", this.currentSelectAddress, this.mSearchList, this.currentType, this.nameMap, this.witnessList, false);
        ((ProposalsContract.View) this.mView).getRcList().setAdapter(this.proposalsAdapter);
    }

    public void showDialog(String str, final String str2) {
        CustomDialog.Builder builder = new CustomDialog.Builder(((ProposalsContract.View) this.mView).getIContext());
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
                CommonWebActivityV3.start(((ProposalsContract.View) mView).getIContext(), str2, StringTronUtil.getResString(R.string.canfirm), -2, false);
            }
        });
        if (((Activity) ((ProposalsContract.View) this.mView).getIContext()).isFinishing()) {
            return;
        }
        this.dialog.show();
    }

    public void createProposalTranscation(final long j, final boolean z, final HashMap<Long, String> hashMap) {
        ((ProposalsContract.View) this.mView).showLoading(((ProposalsContract.View) this.mView).getIContext().getString(R.string.loading2));
        ((ProposalsContract.View) this.mView).runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$createProposalTranscation$4(j, z, hashMap);
            }
        });
    }

    public void lambda$createProposalTranscation$4(final long j, final boolean z, final HashMap hashMap) {
        GrpcAPI.TransactionExtention createProposalApproveTranscation = TronAPI.createProposalApproveTranscation(StringTronUtil.decodeFromBase58Check(this.currentSelectAddress), j, z);
        try {
            this.hasPer = WalletUtils.checkHaveOwnerPermissions(this.mWallet.getAddress(), this.account.getOwnerPermission());
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
                            lambda$createProposalTranscation$3(transaction, z, j, hashMap);
                        }
                    });
                }
            } catch (Exception e) {
                LogUtils.e(e);
                ((ProposalsContract.View) this.mView).ToastSuc(R.string.create_transaction_fail);
                return;
            }
        }
        ((ProposalsContract.View) this.mView).ToastSuc(R.string.create_transaction_fail);
    }

    public void lambda$createProposalTranscation$3(Protocol.Transaction transaction, boolean z, long j, HashMap hashMap) {
        ((ProposalsContract.View) this.mView).showLoading(false);
        if (transaction.toString().length() > 0) {
            ConfirmTransactionNewActivity.startActivity(((ProposalsContract.View) this.mView).getIContext(), ParamBuildUtils.getApprovalProposalTransactionParamBuilder(this.hasPer, z ? R.mipmap.ic_confirm_approve_proposal : R.mipmap.ic_confirm_cancel_approval, z ? R.string.confirm_approve_proposal2 : R.string.cancel_approve_proposal2, transaction, String.valueOf(j), hashMap), this.mWallet.getCreateType() == 7);
        } else {
            ((ProposalsContract.View) this.mView).ToastSuc(R.string.create_transaction_fail);
        }
    }

    private boolean checkFilterConditions(Protocol.Proposal proposal, String str) {
        String str2;
        String str3;
        WitnessOutput.DataBean dataBean;
        String encode58Check = StringTronUtil.encode58Check(proposal.getProposerAddress().toByteArray());
        HashMap<String, WitnessOutput.DataBean> hashMap = this.nameMap;
        if (hashMap == null || hashMap.size() <= 0 || (dataBean = this.nameMap.get(encode58Check)) == null) {
            str2 = "";
            str3 = "";
        } else {
            str2 = dataBean.getName();
            str3 = dataBean.getUrl();
        }
        return (!StringTronUtil.isEmpty(str2) && str2.toLowerCase().contains(str.toLowerCase())) || (!StringTronUtil.isEmpty(str3) && str3.toLowerCase().contains(str.toLowerCase())) || (!StringTronUtil.isEmpty(encode58Check) && encode58Check.toLowerCase().contains(str.toLowerCase()));
    }

    private void initRx() {
        this.mRxManager.on(Event.BroadcastSuccess, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRx$5(obj);
            }
        });
        this.mRxManager.on(Event.BroadcastFail, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRx$6(obj);
            }
        });
    }

    public void lambda$initRx$5(Object obj) throws Exception {
        ((ProposalsContract.View) this.mView).showLoading(((ProposalsContract.View) this.mView).getIContext().getString(R.string.loading2));
        getData();
    }

    public void lambda$initRx$6(Object obj) throws Exception {
        ((ProposalsContract.View) this.mView).showLoading(((ProposalsContract.View) this.mView).getIContext().getString(R.string.loading2));
        getData();
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 != 3001 || intent == null) {
            return;
        }
        String stringExtra = intent.getStringExtra(ChangeAddressActivity.CHANGE_ADDRESS_NEW);
        if (StringTronUtil.isEmpty(stringExtra)) {
            return;
        }
        this.currentSelectAddress = stringExtra;
        ((ProposalsContract.View) this.mView).showLoading(((ProposalsContract.View) this.mView).getIContext().getString(R.string.loading2));
        getData();
    }
}
