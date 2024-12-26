package com.tron.wallet.business.confirm.fg;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.fg.ConfirmVoteFragment;
import com.tron.wallet.business.confirm.parambuilder.bean.VoteParam;
import com.tron.wallet.business.vote.adapter.VoteWitnessContractAdapter;
import com.tron.wallet.common.bean.QrBean;
import com.tron.wallet.common.components.dialog.CustomDialog;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.BandwidthUtils;
import com.tron.wallet.common.utils.DensityUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.FgConfirmVoteBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.tron.api.GrpcAPI;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
import org.tron.protos.contract.WitnessContract;
import org.tron.walletserver.Wallet;
public class ConfirmVoteFragment extends BaseFragment<EmptyPresenter, EmptyModel> {
    private VoteParam baseParam;
    private FgConfirmVoteBinding binding;
    private Dialog bottomDialog;
    Button btGo;
    private GsonBuilder builder;
    private VoteWitnessContractAdapter contractAdapter;
    private CustomDialog dialog;
    private Gson gson;
    private WitnessContract.VoteWitnessContract mContract;
    private byte[] mTransactionBytes;
    private Protocol.Transaction mTransactionUnsigned;
    private Wallet mWallet;
    RecyclerView rcList;
    RelativeLayout rlTop;
    private RxManager rxManager;
    TextView tvAddress;
    TextView tvCostBandwidth;
    TextView tvEmpty;
    TextView tvNote;
    TextView tvSumvote;
    private HashMap<String, String> voteMap;
    private HashMap<String, String> voteMap2;
    private List<WitnessContract.VoteWitnessContract.Vote> votesList;
    private int walletType;
    private long sum = 0;
    private QrBean qrBean = new QrBean();
    private ArrayList<String> list = new ArrayList<>();
    private int position = 0;

    public void setParam(VoteParam voteParam) {
        this.baseParam = voteParam;
    }

    @Override
    protected void processData() {
        VoteParam voteParam = this.baseParam;
        if (voteParam == null || voteParam.getConfirmExtraTitle() == null) {
            return;
        }
        setHeaderBar(this.baseParam.getConfirmExtraTitle().getDesTitle());
        GsonBuilder gsonBuilder = new GsonBuilder();
        this.builder = gsonBuilder;
        this.gson = gsonBuilder.create();
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        this.mWallet = selectedWallet;
        try {
            int createType = selectedWallet.getCreateType();
            this.walletType = createType;
            if (createType == 8 && this.baseParam.hasOwnerPermission()) {
                this.btGo.setText(R.string.request_from_ledger);
            } else {
                this.btGo.setText(R.string.confirm);
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
        this.voteMap = this.baseParam.getNameWeightMap();
        this.voteMap2 = this.baseParam.getAddressWeightMap();
        if (this.voteMap == null) {
            this.voteMap = new HashMap<>();
        }
        int showVotingTetail = this.baseParam.getShowVotingTetail();
        if (showVotingTetail < 0) {
            this.btGo.setVisibility(View.GONE);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.bottomMargin = DensityUtils.dp2px(getIContext(), 10.0f);
            layoutParams.topMargin = DensityUtils.dp2px(getIContext(), 10.0f);
            this.tvNote.setLayoutParams(layoutParams);
        }
        try {
            byte[] bArr = this.baseParam.getTransactionBean().getBytes().get(0);
            this.mTransactionBytes = bArr;
            Protocol.Transaction parseFrom = Protocol.Transaction.parseFrom(bArr);
            this.mTransactionUnsigned = parseFrom;
            this.mContract = (WitnessContract.VoteWitnessContract) TransactionUtils.unpackContract(parseFrom.getRawData().getContract(0), WitnessContract.VoteWitnessContract.class);
        } catch (Exception e2) {
            SentryUtil.captureException(e2);
            LogUtils.e(e2);
        }
        if (this.mContract != null) {
            this.rcList.setLayoutManager(new LinearLayoutManager(this.mContext, 1, false));
            this.votesList = new ArrayList(this.mContract.getVotesList());
            this.contractAdapter = new VoteWitnessContractAdapter(this.mContext, this.votesList, this.voteMap);
            this.tvAddress.setText(StringTronUtil.encode58Check(this.mContract.getOwnerAddress().toByteArray()));
            if (showVotingTetail < 0) {
                this.contractAdapter.showVotingTetail(true);
            }
            this.rcList.setAdapter(this.contractAdapter);
            for (int i = 0; i < this.mContract.getVotesList().size(); i++) {
                this.sum = this.mContract.getVotesList().get(i).getVoteCount() + this.sum;
            }
        }
        this.tvSumvote.setText(String.valueOf(this.sum));
        TextView textView = this.tvCostBandwidth;
        textView.setText(BandwidthUtils.getBandwidthCost(this.mTransactionUnsigned) + getString(R.string.bandwidth));
        RxManager rxManager = new RxManager();
        this.rxManager = rxManager;
        rxManager.on(Event.BroadcastSuccess, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$processData$0(obj);
            }
        });
        this.rxManager.on(Event.DELETE_EVENT, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$processData$1(obj);
            }
        });
        initDialog();
        updateSum();
    }

    public void lambda$processData$0(Object obj) throws Exception {
        exit();
    }

    public void lambda$processData$1(Object obj) throws Exception {
        this.position = ((Integer) obj).intValue();
        showDeleteDialog();
    }

    private void initDialog() {
        CustomDialog.Builder builder = new CustomDialog.Builder(this.mContext);
        this.dialog = builder.style(R.style.loading_dialog).cancelTouchout(true).heightDimenRes(-2).widthDimenRes(-1).setGravity(17).view(R.layout.delete_dialog).build();
        View view = builder.getView();
        ((TextView) view.findViewById(R.id.tv_title)).setText(R.string.delete5);
        ((TextView) view.findViewById(R.id.tv_content)).setText(R.string.delete6);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.ck);
        ((TextView) view.findViewById(R.id.tv_cancle)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                dialog.dismiss();
            }
        });
        ((TextView) view.findViewById(R.id.tv_ok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                dialog.dismiss();
                if (position != -1) {
                    voteMap2.remove(StringTronUtil.encode58Check(((WitnessContract.VoteWitnessContract.Vote) votesList.get(position)).getVoteAddress().toByteArray()));
                    votesList.remove(position);
                } else {
                    votesList.clear();
                    voteMap2.clear();
                }
                contractAdapter.notifyData(votesList);
                updateSum();
            }
        });
    }

    public void updateSum() {
        this.sum = 0L;
        if (this.votesList != null) {
            for (int i = 0; i < this.votesList.size(); i++) {
                this.sum = ((int) this.votesList.get(i).getVoteCount()) + this.sum;
            }
            this.tvSumvote.setText(String.valueOf(this.sum));
            if (this.votesList.size() == 0) {
                this.tvEmpty.setVisibility(View.VISIBLE);
                getHeaderHolder().tvBgRight.setVisibility(View.GONE);
                this.btGo.setEnabled(false);
                return;
            }
            this.tvEmpty.setVisibility(View.GONE);
            return;
        }
        this.tvEmpty.setVisibility(View.VISIBLE);
        getHeaderHolder().tvBgRight.setVisibility(View.GONE);
        this.btGo.setEnabled(false);
    }

    private void showDeleteDialog() {
        this.dialog.show();
    }

    public class fun3 extends NoDoubleClickListener {
        fun3() {
        }

        @Override
        protected void onNoDoubleClick(View view) {
            if (view.getId() != R.id.bt_go) {
                return;
            }
            btGo.setEnabled(false);
            TronConfig.currentPwdType = 9;
            if (baseParam.getAddressWeightMap().size() != voteMap2.size()) {
                showLoadingDialog();
                runOnThreeThread(new OnBackground() {
                    @Override
                    public final void doOnBackground() {
                        ConfirmVoteFragment.3.this.lambda$onNoDoubleClick$0();
                    }
                });
                return;
            }
            send();
        }

        public void lambda$onNoDoubleClick$0() {
            try {
                GrpcAPI.TransactionExtention createVoteWitnessTransaction2 = TronAPI.createVoteWitnessTransaction2(StringTronUtil.decodeFromBase58Check(mWallet.getAddress()), voteMap2);
                if (TransactionUtils.checkTransactionEmpty(createVoteWitnessTransaction2)) {
                    ToastSuc(R.string.create_transaction_fail);
                    return;
                }
                baseParam.clearThenAddTransaction(createVoteWitnessTransaction2.getTransaction());
                ((ConfirmTransactionNewActivity) getActivity()).updateParam(baseParam);
                send();
            } catch (Exception e) {
                SentryUtil.captureException(e);
                btGo.setEnabled(true);
            }
        }
    }

    public void setClick() {
        this.binding.btGo.setOnClickListener(new fun3());
    }

    public void send() {
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$send$2();
            }
        });
    }

    public void lambda$send$2() {
        this.btGo.setEnabled(true);
        dismissLoadingDialog();
        ConfirmTransactionNewActivity confirmTransactionNewActivity = (ConfirmTransactionNewActivity) getActivity();
        if (this.baseParam.hasOwnerPermission()) {
            confirmTransactionNewActivity.JumpkTo(3);
        } else {
            confirmTransactionNewActivity.JumpkTo(2);
        }
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        exit();
    }

    @Override
    public void onDestroy() {
        RxManager rxManager = this.rxManager;
        if (rxManager != null) {
            rxManager.clear();
        }
        super.onDestroy();
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setType(1);
        FgConfirmVoteBinding inflate = FgConfirmVoteBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        LinearLayout root = inflate.getRoot();
        mappingId();
        setClick();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId() {
        this.rcList = this.binding.rcList;
        this.tvSumvote = this.binding.tvSumvote;
        this.tvCostBandwidth = this.binding.tvCostBandwidth;
        this.tvNote = this.binding.tvNote;
        this.tvAddress = this.binding.tvAddress;
        this.btGo = this.binding.btGo;
        this.rlTop = this.binding.rlTop;
        this.tvEmpty = this.binding.tvEmpty;
    }
}
