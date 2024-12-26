package com.tron.wallet.business.tabmy.proposals.proposaldetail;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.arasthel.asyncjob.AsyncJob;
import com.google.protobuf.ByteString;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.parambuilder.utils.ParamBuildUtils;
import com.tron.wallet.business.tabmy.proposals.proposaldetail.ProposalsDetailContract;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.components.ZFlowLayout;
import com.tron.wallet.common.components.dialog.CustomDialog;
import com.tron.wallet.common.config.Event;
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
public class ProposalsDetailPresenter extends ProposalsDetailContract.Presenter {
    private Protocol.Account account;
    private String currentAddress;
    private CustomDialog dialog;
    private String fromType;
    private boolean hasAgree;
    private boolean hasPer = true;
    private String mProposalId;
    private Wallet mWallet;
    private List<WitnessOutput.DataBean> mWitnessList;
    private Protocol.Proposal proposal;
    private List<WitnessOutput.DataBean> witnessList;
    private HashMap<String, WitnessOutput.DataBean> witnessMap;

    public static void lambda$initRx$2(Object obj) throws Exception {
    }

    @Override
    public String getCurrentAddress() {
        return this.currentAddress;
    }

    @Override
    public String getFromType() {
        return this.fromType;
    }

    @Override
    public boolean isHasAgree() {
        return this.hasAgree;
    }

    @Override
    protected void onStart() {
        this.witnessList = new ArrayList();
    }

    @Override
    public void getData() {
        if (StringTronUtil.isEmpty(this.mProposalId) || StringTronUtil.isEmpty(this.currentAddress)) {
            return;
        }
        ((ProposalsDetailContract.View) this.mView).runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$getData$1();
            }
        });
    }

    public void lambda$getData$1() {
        try {
            this.proposal = TronAPI.getproposalbyid(this.mProposalId);
            this.account = TronAPI.queryAccount(StringTronUtil.decodeFromBase58Check(this.currentAddress), false);
        } catch (ConnectErrorException e) {
            LogUtils.e(e);
        }
        if (this.proposal != null) {
            ((ProposalsDetailContract.View) this.mView).runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$getData$0();
                }
            });
        }
    }

    @Override
    public void addSome() {
        this.mWitnessList = new ArrayList();
        this.witnessMap = new HashMap<>();
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        this.mWallet = selectedWallet;
        if (selectedWallet != null) {
            this.currentAddress = selectedWallet.address;
        }
        initRx();
        Intent iIntent = ((ProposalsDetailContract.View) this.mView).getIIntent();
        if (iIntent != null) {
            this.proposal = (Protocol.Proposal) iIntent.getSerializableExtra(ProposalsDetailActivity.PROPOSALS_DETAIL);
            this.hasAgree = iIntent.getBooleanExtra(ProposalsDetailActivity.PROPOSALS_HASAGREE, false);
            this.currentAddress = iIntent.getStringExtra(ProposalsDetailActivity.PROPOSALS_CURRENTADDRESS);
            this.mWitnessList = (List) iIntent.getSerializableExtra(ProposalsDetailActivity.WITNESS_MAP);
            this.fromType = iIntent.getStringExtra(ProposalsDetailActivity.PROPOSALS_FROMTYPE);
            this.mProposalId = iIntent.getStringExtra(ProposalsDetailActivity.PROPOSALS_ID);
        }
        if (!StringTronUtil.isEmpty(this.fromType) && this.fromType.equals(ProposalsDetailActivity.TYPE_DEAL)) {
            getData();
        } else {
            initAccount();
        }
    }

    private void initRx() {
        this.mRxManager.on(Event.BroadcastFail, new Consumer() {
            @Override
            public final void accept(Object obj) {
                ProposalsDetailPresenter.lambda$initRx$2(obj);
            }
        });
    }

    public void lambda$getData$0() {
        ((ProposalsDetailContract.Model) this.mModel).getWitnessList().subscribe(new IObserver(new ICallback<WitnessOutput>() {
            @Override
            public void onResponse(String str, WitnessOutput witnessOutput) {
                if (witnessOutput != null) {
                    witnessList = witnessOutput.getData();
                    if (witnessList == null || witnessList.size() <= 0) {
                        return;
                    }
                    ((ProposalsDetailContract.View) mView).dismissLoadingPage();
                    ((ProposalsDetailContract.View) mView).initView(account, proposal, witnessList);
                }
            }

            @Override
            public void onFailure(String str, String str2) {
                ((ProposalsDetailContract.View) mView).dismissLoadingPage();
                ((ProposalsDetailContract.View) mView).initView(account, proposal, witnessList);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "getWitnessList"));
    }

    @Override
    public void deleteProposalTranscation(final long j, final HashMap<Long, String> hashMap) {
        ((ProposalsDetailContract.View) this.mView).showLoading(((ProposalsDetailContract.View) this.mView).getIContext().getResources().getString(R.string.loading2));
        ((ProposalsDetailContract.View) this.mView).runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$deleteProposalTranscation$4(j, hashMap);
            }
        });
    }

    public void lambda$deleteProposalTranscation$4(final long j, final HashMap hashMap) {
        GrpcAPI.TransactionExtention createProposalDeleteTranscation = TronAPI.createProposalDeleteTranscation(StringTronUtil.decodeFromBase58Check(this.currentAddress), j);
        try {
            Wallet wallet = this.mWallet;
            if (wallet != null && this.account != null) {
                this.hasPer = WalletUtils.checkHaveOwnerPermissions(wallet.getAddress(), this.account.getOwnerPermission());
            }
        } catch (Exception unused) {
            this.hasPer = true;
        }
        if (createProposalDeleteTranscation != null) {
            try {
                if (createProposalDeleteTranscation.hasResult()) {
                    final Protocol.Transaction transaction = createProposalDeleteTranscation.getTransaction();
                    AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                        @Override
                        public final void doInUIThread() {
                            lambda$deleteProposalTranscation$3(transaction, j, hashMap);
                        }
                    });
                }
            } catch (Exception e) {
                LogUtils.e(e);
                ((ProposalsDetailContract.View) this.mView).ToastSuc(R.string.create_transaction_fail);
                return;
            }
        }
        ((ProposalsDetailContract.View) this.mView).ToastSuc(R.string.create_transaction_fail);
    }

    public void lambda$deleteProposalTranscation$3(Protocol.Transaction transaction, long j, HashMap hashMap) {
        ((ProposalsDetailContract.View) this.mView).dismissLoading();
        if (transaction != null && transaction.toString().length() > 0) {
            ConfirmTransactionNewActivity.startForResult((Activity) ((ProposalsDetailContract.View) this.mView).getIContext(), ParamBuildUtils.getApprovalProposalTransactionParamBuilder(this.hasPer, R.mipmap.ic_confirm_delete_proposal, R.string.revoke_proposal2, transaction, String.valueOf(j), hashMap), 1236);
        } else {
            ((ProposalsDetailContract.View) this.mView).ToastSuc(R.string.create_transaction_fail);
        }
    }

    @Override
    public void initAccount() {
        ((ProposalsDetailContract.View) this.mView).showLoadingPage(StringTronUtil.getResString(R.string.loading));
        ((ProposalsDetailContract.View) this.mView).runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$initAccount$6();
            }
        });
    }

    public void lambda$initAccount$6() {
        try {
            this.account = TronAPI.queryAccount(StringTronUtil.decodeFromBase58Check(this.currentAddress), false);
        } catch (ConnectErrorException e) {
            LogUtils.e(e);
        }
        ((ProposalsDetailContract.View) this.mView).runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$initAccount$5();
            }
        });
    }

    public void lambda$initAccount$5() {
        ((ProposalsDetailContract.View) this.mView).dismissLoadingPage();
        ((ProposalsDetailContract.View) this.mView).initView(this.account, this.proposal, this.mWitnessList);
    }

    @Override
    public void initProposalList(ZFlowLayout zFlowLayout, List<ByteString> list, boolean z, String str) {
        this.witnessMap = ((ProposalsDetailContract.View) this.mView).getWitnessMap();
        if (list == null || list.size() == 0) {
            zFlowLayout.setVisibility(View.GONE);
            return;
        }
        zFlowLayout.setVisibility(View.VISIBLE);
        int size = list.size();
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-2, -2);
        marginLayoutParams.setMargins(0, 10, 20, 10);
        zFlowLayout.removeAllViews();
        if (z) {
            TextView textView = (TextView) ((Activity) ((ProposalsDetailContract.View) this.mView).getIContext()).getLayoutInflater().inflate(R.layout.adapter_proposal_name, (ViewGroup) null).findViewById(R.id.tv_content);
            ((TextView) textView.findViewById(R.id.tv_content)).setTextColor(((ProposalsDetailContract.View) this.mView).getIContext().getResources().getColor(R.color.black_02_50));
            textView.setText(str + ":");
            zFlowLayout.addView(textView, marginLayoutParams);
        }
        for (int i = 0; i < size; i++) {
            String encode58Check = StringTronUtil.encode58Check(list.get(i).toByteArray());
            HashMap<String, WitnessOutput.DataBean> hashMap = this.witnessMap;
            final WitnessOutput.DataBean dataBean = (hashMap == null || hashMap.size() <= 0) ? null : this.witnessMap.get(encode58Check);
            View inflate = ((Activity) ((ProposalsDetailContract.View) this.mView).getIContext()).getLayoutInflater().inflate(R.layout.adapter_proposal_name, (ViewGroup) null);
            TextView textView2 = (TextView) inflate.findViewById(R.id.tv_content);
            textView2.setTextColor(((ProposalsDetailContract.View) this.mView).getIContext().getResources().getColor(R.color.blue_13));
            if (dataBean != null) {
                if (!StringTronUtil.isEmpty(dataBean.getName())) {
                    encode58Check = dataBean.getName();
                } else if (!StringTronUtil.isEmpty(dataBean.getUrl())) {
                    encode58Check = dataBean.getUrl();
                }
            }
            if (size == 1) {
                textView2.setText(encode58Check);
            } else if (i == size - 1) {
                textView2.setText(encode58Check);
            } else {
                textView2.setText(encode58Check + ",");
            }
            zFlowLayout.addView(inflate, marginLayoutParams);
            textView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    WitnessOutput.DataBean dataBean2 = dataBean;
                    if (dataBean2 == null || TextUtils.isEmpty(dataBean2.getUrl())) {
                        return;
                    }
                    CommonWebActivityV3.start(((ProposalsDetailContract.View) mView).getIContext(), dataBean.getUrl(), StringTronUtil.isEmpty(dataBean.getName()) ? "" : dataBean.getName(), -2, false);
                }
            });
        }
    }

    @Override
    public void createProposalTranscation(final long j, final boolean z, final HashMap<Long, String> hashMap) {
        ((ProposalsDetailContract.View) this.mView).showLoading(StringTronUtil.getResString(R.string.loading2));
        ((ProposalsDetailContract.View) this.mView).runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$createProposalTranscation$8(j, z, hashMap);
            }
        });
    }

    public void lambda$createProposalTranscation$8(final long j, final boolean z, final HashMap hashMap) {
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
                    ((ProposalsDetailContract.View) this.mView).runOnUIThread(new OnMainThread() {
                        @Override
                        public final void doInUIThread() {
                            lambda$createProposalTranscation$7(transaction, z, j, hashMap);
                        }
                    });
                }
            } catch (Exception e) {
                LogUtils.e(e);
                ((ProposalsDetailContract.View) this.mView).ToastSuc(R.string.create_transaction_fail);
                return;
            }
        }
        ((ProposalsDetailContract.View) this.mView).ToastSuc(R.string.create_transaction_fail);
    }

    public void lambda$createProposalTranscation$7(Protocol.Transaction transaction, boolean z, long j, HashMap hashMap) {
        ((ProposalsDetailContract.View) this.mView).dismissLoading();
        if (transaction != null && transaction.toString().length() > 0) {
            ConfirmTransactionNewActivity.startForResult((Activity) ((ProposalsDetailContract.View) this.mView).getIContext(), ParamBuildUtils.getApprovalProposalTransactionParamBuilder(this.hasPer, z ? R.mipmap.ic_confirm_approve_proposal : R.mipmap.ic_confirm_cancel_approval, z ? R.string.confirm_approve_proposal2 : R.string.cancel_approve_proposal2, transaction, String.valueOf(j), hashMap), 1235);
        } else {
            ((ProposalsDetailContract.View) this.mView).ToastSuc(R.string.create_transaction_fail);
        }
    }

    @Override
    public void showDialog(String str, final String str2, final HashMap<Long, String> hashMap) {
        CustomDialog.Builder builder = new CustomDialog.Builder(((ProposalsDetailContract.View) this.mView).getIContext());
        CustomDialog build = builder.style(R.style.loading_dialog).cancelTouchout(true).heightDimenRes(-2).widthDimenRes(-1).setGravity(17).view(R.layout.fail_dialog).build();
        this.dialog = build;
        build.setCanceledOnTouchOutside(false);
        View view = builder.getView();
        TextView textView = (TextView) view.findViewById(R.id.tv_cancle);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_ok);
        ((TextView) view.findViewById(R.id.tv_content)).setText(str);
        textView.setText(R.string.cancle);
        if (StringTronUtil.isEmpty(str2)) {
            textView2.setText(R.string.confirm);
        } else {
            textView2.setText(R.string.canfirm);
        }
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
                if (StringTronUtil.isEmpty(str2)) {
                    ProposalsDetailPresenter proposalsDetailPresenter = ProposalsDetailPresenter.this;
                    proposalsDetailPresenter.deleteProposalTranscation(proposalsDetailPresenter.proposal.getProposalId(), hashMap);
                    return;
                }
                CommonWebActivityV3.start(((ProposalsDetailContract.View) mView).getIContext(), str2, StringTronUtil.getResString(R.string.canfirm), -2, false);
            }
        });
        if (((Activity) ((ProposalsDetailContract.View) this.mView).getIContext()).isFinishing()) {
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
