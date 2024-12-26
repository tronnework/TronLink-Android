package com.tron.wallet.common.adapter.holder;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.common.util.Hex;
import com.google.protobuf.InvalidProtocolBufferException;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.business.confirm.core.ConfirmMultiSignVoteActivity;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.parambuilder.BaseConfirmTransParamBuilder;
import com.tron.wallet.business.confirm.parambuilder.utils.ParamBuildUtils;
import com.tron.wallet.business.migrate.MigrateActivityExternalSyntheticLambda2;
import com.tron.wallet.business.tabmy.dealsign.DealSignParamUtils;
import com.tron.wallet.business.tabmy.proposals.makeproposal.MakeProposalActivity;
import com.tron.wallet.business.tabmy.proposals.proposaldetail.ProposalsDetailActivity;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.adapter.DealSignAddressAdapter;
import com.tron.wallet.common.bean.AllTransferOutput;
import com.tron.wallet.common.bean.DealSignOutput;
import com.tron.wallet.common.components.countdownview.CountdownView;
import com.tron.wallet.common.components.dialog.CustomDialog;
import com.tron.wallet.common.config.T;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AddressNameUtils;
import com.tron.wallet.common.utils.DateUtils;
import com.tron.wallet.common.utils.DrawableUtil;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.common.utils.transaction.TriggerUtils;
import com.tron.wallet.databinding.ItemDealSignBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.bean.AssetIssueContractDao;
import com.tron.wallet.db.dao.DaoUtils;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.LedgerWallet;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tronlinkpro.wallet.R;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.functions.Consumer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.CharUtils;
import org.tron.common.utils.JsonFormat;
import org.tron.common.utils.TransactionDataUtils;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
import org.tron.protos.contract.AssetIssueContractOuterClass;
import org.tron.protos.contract.BalanceContract;
import org.tron.protos.contract.Common;
import org.tron.protos.contract.ProposalContract;
import org.tron.protos.contract.SmartContractOuterClass;
import org.tron.walletserver.TriggerData;
import org.tron.walletserver.Wallet;
public class DealSignHolder extends BaseViewHolder implements View.OnClickListener {
    TextView allSignCount;
    ItemDealSignBinding binding;
    Button btSign;
    TextView currentSignCount;
    CountdownView cvCountdownView;
    ImageView icProgress;
    TextView initiator;
    private boolean isOpen;
    View llCountdown;
    View llNote;
    View llReceive;
    View llTranSuccessTime;
    View llType;
    View ll_transactionHash;
    private DealSignOutput.DataBeanX.DataBean mBean;
    private Context mContext;
    private int mListSize;
    private int mState;
    private String mWalletName;
    TextView needSignCount;
    private NumberFormat numberFormat;
    private int position;
    ProgressBar progressBar;
    TextView receivables;
    TextView resourceType;
    View rlOriginator;
    RelativeLayout rlProgressState;
    LinearLayout rootView;
    RecyclerView rvSignAddress;
    View signFailure;
    TextView timeRemain;
    private Protocol.Transaction transaction;
    Button transactionActon;
    TextView transactionFrom;
    TextView transactionHash;
    TextView transactionTime;
    TextView transactionTo;
    TextView tv1;
    TextView tvNote;
    TextView tvNoteMore;
    TextView tvSubFrom;
    TextView tvSubTo;
    TextView tvTransContent;
    TextView tvTransType;

    private String getAccountUpdateFee(DealSignOutput.DataBeanX.DataBean dataBean) {
        return "100 TRX";
    }

    public CountdownView getCvCountdownView() {
        return this.cvCountdownView;
    }

    public DealSignHolder(View view) {
        super(view);
        this.isOpen = false;
        ItemDealSignBinding bind = ItemDealSignBinding.bind(view);
        this.binding = bind;
        this.tvTransType = bind.tvTransType;
        this.tvTransContent = this.binding.tvTransContent;
        this.transactionFrom = this.binding.transactionFrom;
        this.tvSubFrom = this.binding.tvSubFrom;
        this.initiator = this.binding.tvInitiator;
        this.receivables = this.binding.tvReceivables;
        this.transactionTo = this.binding.transactionTo;
        this.tvSubTo = this.binding.tvSubTo;
        this.transactionActon = this.binding.transactionAction;
        this.llType = this.binding.llType;
        this.resourceType = this.binding.resourceType;
        this.timeRemain = this.binding.timeRemain;
        this.cvCountdownView = this.binding.cvCountdownView;
        this.tv1 = this.binding.tv1;
        this.currentSignCount = this.binding.currentSignCount;
        this.allSignCount = this.binding.allSignCount;
        this.needSignCount = this.binding.needSignCount;
        this.icProgress = this.binding.icProgress;
        this.rlProgressState = this.binding.rlProgressState;
        this.progressBar = this.binding.progressBar;
        this.rvSignAddress = this.binding.rvSignAddress;
        this.btSign = this.binding.btSign;
        this.signFailure = this.binding.signFailure;
        this.llTranSuccessTime = this.binding.llTransactionSuccessTime;
        this.transactionTime = this.binding.transactionTime;
        this.ll_transactionHash = this.binding.llHash;
        this.transactionHash = this.binding.transactionHash;
        this.llCountdown = this.binding.llCountdown;
        this.llReceive = this.binding.llReceive;
        this.rootView = this.binding.rootView;
        this.llNote = this.binding.llNote;
        this.tvNote = this.binding.tvNote;
        this.tvNoteMore = this.binding.tvNoteMore;
        this.rlOriginator = this.binding.rlOriginator;
        this.binding.btSign.setOnClickListener(this);
        this.binding.rlProgressState.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.bt_sign) {
            sign();
        } else if (id != R.id.rl_progress_state) {
        } else {
            boolean z = !this.isOpen;
            this.isOpen = z;
            this.mBean.isOpen = z;
            closeOrOpenAddressList(this.mBean.isOpen);
            if (TronConfig.openList != null) {
                if (TronConfig.openList.contains(this.mBean.hash)) {
                    if (this.isOpen) {
                        return;
                    }
                    TronConfig.openList.remove(this.mBean.hash);
                    return;
                } else if (this.isOpen) {
                    TronConfig.openList.add(this.mBean.hash);
                    return;
                } else {
                    return;
                }
            }
            if (TronConfig.openList == null) {
                TronConfig.openList = new ArrayList();
            }
            if (this.isOpen) {
                TronConfig.openList.add(this.mBean.hash);
            }
        }
    }

    public void bindHolder(Context context, final DealSignOutput.DataBeanX.DataBean dataBean, int i, String str, int i2, int i3) {
        final String str2;
        this.mContext = context;
        this.mState = i;
        this.mBean = dataBean;
        this.mWalletName = str;
        this.position = i2;
        this.mListSize = i3;
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.numberFormat = numberInstance;
        numberInstance.setMaximumFractionDigits(7);
        initView();
        closeOrOpenAddressList(dataBean.isOpen);
        this.rvSignAddress.setLayoutManager(new LinearLayoutManager(this.mContext, 1, false));
        if (dataBean != null) {
            if (dataBean.contractData != null) {
                setAddressAndName(this.transactionFrom, this.tvSubFrom, dataBean.contractData.owner_address);
                controlContractType(dataBean);
            }
            LogUtils.i("BaseCountdown", "bean.expireTime : " + dataBean.expireTime);
            refreshTime(dataBean.expireTime);
            if (i == 3 || i == 4) {
                long transactionTimeStamp = getTransactionTimeStamp(dataBean);
                if (transactionTimeStamp > 0) {
                    try {
                        this.transactionTime.setText(DateUtils.longToString(transactionTimeStamp, DateUtils.DATE_TO_STRING_DETAIAL_PATTERN));
                    } catch (ParseException e) {
                        LogUtils.e(e);
                    }
                } else {
                    this.llTranSuccessTime.setVisibility(View.GONE);
                }
            }
            String str3 = dataBean.currentTransaction.raw_data.data;
            if (str3 != null && !str3.equals("")) {
                this.llNote.setVisibility(View.VISIBLE);
                str2 = new String(Hex.decodeHex(str3));
                this.tvNote.setText(str2);
                this.tvNoteMore.setVisibility(View.VISIBLE);
            } else {
                this.llNote.setVisibility(View.GONE);
                str2 = "";
            }
            TextView textView = this.currentSignCount;
            textView.setText(dataBean.currentWeight + "");
            TextView textView2 = this.allSignCount;
            textView2.setText("/" + dataBean.threshold);
            this.needSignCount.setText(String.format(this.mContext.getResources().getString(R.string.effect_5_vote), Integer.valueOf(dataBean.threshold)));
            this.progressBar.setMax(dataBean.threshold);
            this.progressBar.setProgress(dataBean.currentWeight);
            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String str4;
                    String str5;
                    if (SpAPI.THIS.getCurrentChain() != null && SpAPI.THIS.getCurrentChain().isMainChain) {
                        str4 = TronConfig.TRONSCANHOST_MAINCHAIN + mBean.hash;
                    } else {
                        str4 = TronConfig.TRONSCANHOST_DAPPCHAIN + mBean.hash;
                    }
                    if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
                        str5 = str4 + "?lang=zh";
                    } else {
                        str5 = str4 + "?lang=en";
                    }
                    CommonWebActivityV3.start(mContext, str5, "2".equals(SpAPI.THIS.useLanguage()) ? StringTronUtil.getResString(R.string.transfer_doc) : "Transaction Details", -2, true);
                }
            };
            this.tvNoteMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDialog(str2);
                }
            });
            new DrawableUtil(this.transactionHash, new DrawableUtil.OnDrawableListener() {
                @Override
                public void onLeft(View view, Drawable drawable) {
                }

                @Override
                public void onRight(View view, Drawable drawable) {
                    UIUtils.copy(transactionHash.getText().toString());
                    IToast.getIToast().show(mContext.getResources().getString(R.string.already_copy));
                }
            }, onClickListener);
            new DrawableUtil(this.tvSubFrom, new DrawableUtil.OnDrawableListener() {
                @Override
                public void onLeft(View view, Drawable drawable) {
                }

                @Override
                public void onRight(View view, Drawable drawable) {
                    UIUtils.copy(dataBean.contractData.owner_address);
                    IToast.getIToast().show(mContext.getResources().getString(R.string.already_copy));
                }
            }, null);
            new DrawableUtil(this.tvSubTo, new DrawableUtil.OnDrawableListener() {
                @Override
                public void onLeft(View view, Drawable drawable) {
                }

                @Override
                public void onRight(View view, Drawable drawable) {
                    UIUtils.copy(TextUtils.isEmpty(dataBean.contractData.receiver_address) ? dataBean.contractData.to_address : dataBean.contractData.receiver_address);
                    DealSignOutput.DataBeanX.DataBean dataBean2 = dataBean;
                    if (dataBean2 != null && dataBean2.contractType != null && ("TriggerSmartContract".equals(dataBean.contractType) || "CreateSmartContract".equals(dataBean.contractType) || "UpdateSettingContract".equals(dataBean.contractType) || "UpdateEnergyLimitContract".equals(dataBean.contractType) || "ClearABIContract".equals(dataBean.contractType))) {
                        UIUtils.copy(getSmartTransactionAddress());
                    } else {
                        UIUtils.copy(TextUtils.isEmpty(dataBean.contractData.receiver_address) ? dataBean.contractData.to_address : dataBean.contractData.receiver_address);
                    }
                    IToast.getIToast().show(mContext.getResources().getString(R.string.already_copy));
                }
            }, null);
            if (dataBean.signatureProgress != null) {
                this.rvSignAddress.setAdapter(new DealSignAddressAdapter(this.mContext, dataBean.signatureProgress, this.mWalletName));
            }
        }
        updateBtState(i, dataBean);
    }

    public void showDialog(String str) {
        CustomDialog.Builder builder = new CustomDialog.Builder(this.mContext);
        final CustomDialog build = builder.style(R.style.loading_dialog).cancelTouchout(true).heightDimenRes(-2).widthDimenRes(-1).setGravity(17).view(R.layout.note_dialog).build();
        View view = builder.getView();
        ((TextView) view.findViewById(R.id.tv_note)).setText(str);
        ((TextView) view.findViewById(R.id.tv_cancle)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                build.dismiss();
            }
        });
        build.show();
    }

    private long getTransactionTimeStamp(DealSignOutput.DataBeanX.DataBean dataBean) {
        if (dataBean == null || dataBean.currentTransaction == null || dataBean.currentTransaction.raw_data == null) {
            return 0L;
        }
        return dataBean.currentTransaction.raw_data.timestamp;
    }

    private void initView() {
        this.tvTransType.setText(this.mContext.getString(R.string.Transfer));
        this.tvTransContent.setText("");
        this.initiator.setText(this.mContext.getString(R.string.initiating_address));
        this.receivables.setText(this.mContext.getString(R.string.receivables));
        this.transactionActon.setText(R.string.view);
        this.transactionActon.setVisibility(View.GONE);
        this.llType.setVisibility(View.GONE);
        this.ll_transactionHash.setVisibility(View.GONE);
        this.llTranSuccessTime.setVisibility(View.GONE);
        this.llReceive.setVisibility(View.VISIBLE);
        this.transactionTo.setVisibility(View.VISIBLE);
        try {
            if (LedgerWallet.isLedger(WalletUtils.getWallet(this.mWalletName))) {
                this.btSign.setText(R.string.request_from_ledger);
            } else {
                this.btSign.setText(R.string.sign);
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void updateBtState(int i, DealSignOutput.DataBeanX.DataBean dataBean) {
        if (i == 1) {
            this.llCountdown.setVisibility(View.VISIBLE);
            this.btSign.setVisibility(View.VISIBLE);
            this.ll_transactionHash.setVisibility(View.GONE);
            this.signFailure.setVisibility(View.GONE);
            this.llTranSuccessTime.setVisibility(View.GONE);
        } else if (i == 2) {
            this.llCountdown.setVisibility(View.VISIBLE);
            this.btSign.setVisibility(View.GONE);
            this.ll_transactionHash.setVisibility(View.GONE);
            this.signFailure.setVisibility(View.VISIBLE);
            this.llTranSuccessTime.setVisibility(View.GONE);
        } else if (i == 3) {
            this.llCountdown.setVisibility(View.GONE);
            this.btSign.setVisibility(View.GONE);
            this.ll_transactionHash.setVisibility(View.VISIBLE);
            this.signFailure.setVisibility(View.GONE);
            this.transactionHash.setText(dataBean == null ? "" : dataBean.hash);
            this.llTranSuccessTime.setVisibility(View.VISIBLE);
        } else if (i == 4) {
            this.llCountdown.setVisibility(View.GONE);
            this.btSign.setVisibility(View.GONE);
            this.ll_transactionHash.setVisibility(View.GONE);
            this.signFailure.setVisibility(View.VISIBLE);
            this.llTranSuccessTime.setVisibility(View.VISIBLE);
        }
    }

    public void refreshTime(long j) {
        this.cvCountdownView.setVisibility(View.VISIBLE);
        this.timeRemain.setVisibility(View.GONE);
        this.cvCountdownView.start(j * 1000);
    }

    private void controlContractType(DealSignOutput.DataBeanX.DataBean dataBean) {
        String string;
        String str;
        String str2 = dataBean.contractType;
        this.rlOriginator.setVisibility(View.VISIBLE);
        this.transactionTo.setVisibility(View.VISIBLE);
        this.tvSubTo.setVisibility(View.VISIBLE);
        str2.hashCode();
        char c = 65535;
        switch (str2.hashCode()) {
            case -1705044092:
                if (str2.equals("WithdrawBalanceContract")) {
                    c = 0;
                    break;
                }
                break;
            case -1632790850:
                if (str2.equals("UnDelegateResourceContract")) {
                    c = 1;
                    break;
                }
                break;
            case -1520674133:
                if (str2.equals("WithdrawExpireUnfreezeContract")) {
                    c = 2;
                    break;
                }
                break;
            case -1048760864:
                if (str2.equals("ProposalCreateContract")) {
                    c = 3;
                    break;
                }
                break;
            case -845990611:
                if (str2.equals("ClearABIContract")) {
                    c = 4;
                    break;
                }
                break;
            case -703089577:
                if (str2.equals("FreezeBalanceContract")) {
                    c = 5;
                    break;
                }
                break;
            case -651921570:
                if (str2.equals("UnfreezeBalanceContract")) {
                    c = 6;
                    break;
                }
                break;
            case -453939044:
                if (str2.equals("UpdateEnergyLimitContract")) {
                    c = 7;
                    break;
                }
                break;
            case -82956877:
                if (str2.equals("FreezeBalanceV2Contract")) {
                    c = '\b';
                    break;
                }
                break;
            case 39138117:
                if (str2.equals("CancelAllUnfreezeV2Contract")) {
                    c = '\t';
                    break;
                }
                break;
            case 180125197:
                if (str2.equals("ProposalApproveContract")) {
                    c = '\n';
                    break;
                }
                break;
            case 270660495:
                if (str2.equals("ProposalDeleteContract")) {
                    c = 11;
                    break;
                }
                break;
            case 706457047:
                if (str2.equals("TransferAssetContract")) {
                    c = '\f';
                    break;
                }
                break;
            case 710366781:
                if (str2.equals("TransferContract")) {
                    c = CharUtils.CR;
                    break;
                }
                break;
            case 762691543:
                if (str2.equals("AccountPermissionUpdateContract")) {
                    c = 14;
                    break;
                }
                break;
            case 885253893:
                if (str2.equals("DelegateResourceContract")) {
                    c = 15;
                    break;
                }
                break;
            case 1147615065:
                if (str2.equals("UpdateSettingContract")) {
                    c = 16;
                    break;
                }
                break;
            case 1252602738:
                if (str2.equals("UnfreezeAssetContract")) {
                    c = 17;
                    break;
                }
                break;
            case 1421429571:
                if (str2.equals("TriggerSmartContract")) {
                    c = 18;
                    break;
                }
                break;
            case 1532035647:
                if (str2.equals("CreateSmartContract")) {
                    c = 19;
                    break;
                }
                break;
            case 1699052801:
                if (str2.equals("VoteWitnessContract")) {
                    c = 20;
                    break;
                }
                break;
            case 1844857594:
                if (str2.equals("UnfreezeBalanceV2Contract")) {
                    c = 21;
                    break;
                }
                break;
        }
        String str3 = "";
        switch (c) {
            case 0:
                str2 = this.mContext.getString(R.string.reword_withdraw);
                str3 = getWithdrawBalanceContent(dataBean);
                this.initiator.setText(R.string.withdraw_address);
                this.llReceive.setVisibility(View.GONE);
                break;
            case 1:
                str2 = this.mContext.getString(R.string.trans_type_undelegate_v2);
                str3 = this.numberFormat.format(dataBean.contractData.balance / 1000000.0d) + " TRX";
                this.initiator.setText(R.string.multi_reclaim_from);
                setAddressAndName(this.transactionFrom, this.tvSubFrom, dataBean.contractData.receiver_address);
                setAddressAndName(this.transactionTo, this.tvSubTo, dataBean.contractData.owner_address);
                this.llType.setVisibility(View.VISIBLE);
                if (getResourceTypeFromUnDelegate(dataBean) == 1) {
                    this.resourceType.setText(R.string.energy);
                } else if (getResourceTypeFromUnDelegate(dataBean) == 0) {
                    this.resourceType.setText(R.string.bandwidth);
                } else {
                    this.llType.setVisibility(View.GONE);
                    break;
                }
                break;
            case 2:
                str2 = this.mContext.getString(R.string.withdraw_expire_unfreeze);
                this.rlOriginator.setVisibility(View.GONE);
                this.receivables.setText(R.string.multi_withdraw_to);
                setAddressAndName(this.transactionTo, this.tvSubTo, dataBean.contractData.owner_address);
                break;
            case 3:
            case '\n':
            case 11:
                if (str2.equals("ProposalCreateContract")) {
                    string = this.mContext.getString(R.string.create_proposal);
                } else if (str2.equals("ProposalApproveContract")) {
                    string = isDisApproveProposal(dataBean) ? this.mContext.getString(R.string.disapprove_proposal) : this.mContext.getString(R.string.approve_proposal);
                } else {
                    string = str2.equals("ProposalDeleteContract") ? this.mContext.getString(R.string.revoke_proposal) : str2;
                }
                this.initiator.setText(this.mContext.getString(R.string.initiation_address));
                this.receivables.setText(this.mContext.getString(R.string.proposal));
                final String str4 = this.mBean.contractData.proposal_id;
                if (StringTronUtil.isEmpty(str4) && str2.equals("ProposalCreateContract")) {
                    this.transactionTo.setVisibility(View.INVISIBLE);
                    this.tvSubTo.setVisibility(View.GONE);
                    this.transactionActon.setVisibility(View.VISIBLE);
                    this.transactionActon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            try {
                                Map<Long, Long> parametersMap = ((ProposalContract.ProposalCreateContract) TransactionUtils.unpackContract(WalletUtils.packTransaction_sign(mBean.currentTransaction2).getRawData().getContract(0), ProposalContract.ProposalCreateContract.class)).getParametersMap();
                                Wallet wallet = WalletUtils.getWallet(mWalletName);
                                if (wallet != null && LedgerWallet.isLedger(wallet) && !SpAPI.THIS.getCurrentChain().isMainChain) {
                                    try {
                                        IToast.getIToast().setImage(R.mipmap.toast_error).show(R.string.ledger_not_support_on_dappchain);
                                        return;
                                    } catch (Exception e) {
                                        LogUtils.e(e);
                                        return;
                                    }
                                } else if (wallet != null && wallet.isWatchOnly() && !SpAPI.THIS.getCurrentChain().isMainChain) {
                                    try {
                                        IToast.getIToast().setImage(R.mipmap.toast_error).show(R.string.no_support);
                                        return;
                                    } catch (Exception e2) {
                                        LogUtils.e(e2);
                                        return;
                                    }
                                } else {
                                    MakeProposalActivity.start(mContext, wallet.getAddress(), new HashMap(parametersMap), MakeProposalActivity.TYPE_DEAL);
                                    return;
                                }
                            } catch (InvalidProtocolBufferException e3) {
                                LogUtils.e(e3);
                            }
                            LogUtils.e(e3);
                        }
                    });
                } else {
                    this.transactionActon.setText("#" + str4);
                    this.transactionActon.setVisibility(View.VISIBLE);
                    this.transactionTo.setVisibility(View.INVISIBLE);
                    this.tvSubTo.setVisibility(View.GONE);
                    this.transactionActon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (mBean == null || TextUtils.isEmpty(mBean.currentTransaction2)) {
                                return;
                            }
                            Protocol.Transaction packTransaction_sign = WalletUtils.packTransaction_sign(mBean.currentTransaction2);
                            if (packTransaction_sign == null) {
                                IToast.getIToast().setImage(R.mipmap.toast_error).show(R.string.create_transaction_fail);
                            } else if (packTransaction_sign == null || packTransaction_sign.getRawData() == null || packTransaction_sign.getRawData().getContractCount() == 0) {
                                IToast.getIToast().setImage(R.mipmap.toast_error).show(R.string.create_transaction_fail);
                            } else {
                                ProposalsDetailActivity.start(mContext, str4, mWalletName, ProposalsDetailActivity.TYPE_DEAL);
                            }
                        }
                    });
                }
                str2 = string;
                break;
            case 4:
            case 7:
            case 16:
            case 18:
            case 19:
                if (str2.equals("ClearABIContract")) {
                    str2 = this.mContext.getString(R.string.clear_ABI_contract);
                } else if (str2.equals("UpdateEnergyLimitContract")) {
                    str2 = this.mContext.getString(R.string.update_energy_limit);
                } else if (str2.equals("UpdateSettingContract")) {
                    str2 = this.mContext.getString(R.string.update_setting_contract);
                } else if (str2.equals("CreateSmartContract")) {
                    str2 = this.mContext.getString(R.string.create_smart_contract);
                } else {
                    str2 = this.mContext.getString(R.string.TRC20_Transfer);
                }
                String smartTransactionAddress = getSmartTransactionAddress();
                if (TextUtils.isEmpty(smartTransactionAddress)) {
                    this.llReceive.setVisibility(View.GONE);
                } else {
                    this.llReceive.setVisibility(View.VISIBLE);
                    setAddressAndName(this.transactionTo, this.tvSubTo, smartTransactionAddress);
                }
                try {
                    DealSignOutput.DataBeanX.DataBean.Trc20InfoBean trc20InfoBean = dataBean.trc20Info;
                    str = getSmartTransactionValue(dataBean.trc20Info);
                } catch (Exception e) {
                    LogUtils.e(e);
                    SentryUtil.captureException(e);
                    str = "";
                }
                LogUtils.i("DealSignHolder", "value: " + str);
                if (!TextUtils.isEmpty(str) && !str.equals("0")) {
                    str3 = str;
                }
                break;
            case 5:
                str2 = this.mContext.getString(R.string.TRXFreeze);
                str3 = this.numberFormat.format(dataBean.contractData.frozen_balance / 1000000.0d) + " TRX";
                this.initiator.setText(R.string.freeze_address);
                this.receivables.setText(R.string.receiving_address_withdot);
                this.llReceive.setVisibility(View.GONE);
                setAddressAndName(this.transactionTo, this.tvSubTo, dataBean.contractData.owner_address);
                this.llType.setVisibility(View.VISIBLE);
                if (getResourceTypeFromFreezeBalance(dataBean) == 1) {
                    this.resourceType.setText(R.string.energy);
                } else if (getResourceTypeFromFreezeBalance(dataBean) == 0) {
                    this.resourceType.setText(R.string.bandwidth);
                } else {
                    this.llType.setVisibility(View.GONE);
                }
                break;
            case 6:
                str2 = this.mContext.getString(R.string.TRXUnfreeze);
                this.initiator.setText(R.string.unstake_account);
                setAddressAndName(this.transactionFrom, this.tvSubFrom, StringTronUtil.isEmpty(dataBean.contractData.receiver_address) ? dataBean.contractData.owner_address : dataBean.contractData.receiver_address);
                setAddressAndName(this.transactionTo, this.tvSubTo, dataBean.contractData.owner_address);
                this.llType.setVisibility(View.VISIBLE);
                if (getResourceTypeFromUnFreezeBalace(dataBean) == 1) {
                    this.resourceType.setText(R.string.energy);
                } else if (getResourceTypeFromUnFreezeBalace(dataBean) == 0) {
                    this.resourceType.setText(R.string.bandwidth);
                } else {
                    this.llType.setVisibility(View.GONE);
                }
                break;
            case '\b':
                this.transactionTo.setVisibility(View.VISIBLE);
                this.tvSubTo.setVisibility(View.VISIBLE);
                this.rlOriginator.setVisibility(View.GONE);
                str2 = this.mContext.getString(R.string.trans_type_stake_v2);
                str3 = this.numberFormat.format(dataBean.contractData.frozen_balance / 1000000.0d) + " TRX";
                setAddressAndName(this.transactionTo, this.tvSubTo, dataBean.contractData.owner_address);
                this.llType.setVisibility(View.VISIBLE);
                if (getResourceTypeFromFreezeBalanceV2(dataBean) == 1) {
                    this.resourceType.setText(R.string.energy);
                } else if (getResourceTypeFromFreezeBalanceV2(dataBean) == 0) {
                    this.resourceType.setText(R.string.bandwidth);
                } else {
                    this.llType.setVisibility(View.GONE);
                }
                break;
            case '\t':
                str2 = this.mContext.getString(R.string.cancel_all_unstake_stream);
                this.initiator.setText(R.string.cancel_all_unstake_account);
                this.receivables.setText(R.string.receivables);
                setAddressAndName(this.transactionFrom, this.tvSubFrom, dataBean.contractData.owner_address);
                setAddressAndName(this.transactionTo, this.tvSubTo, dataBean.contractData.owner_address);
                break;
            case '\f':
                str2 = this.mContext.getString(R.string.TRC10_transfer);
                setAddressAndName(this.transactionTo, this.tvSubTo, TextUtils.isEmpty(dataBean.contractData.receiver_address) ? dataBean.contractData.to_address : dataBean.contractData.receiver_address);
                String str5 = dataBean.contractData.asset_name;
                int precision = getPrecision(str5);
                StringBuilder sb = new StringBuilder();
                sb.append(precision == 0 ? Long.valueOf(dataBean.contractData.amount) : this.numberFormat.format(dataBean.contractData.amount / Math.pow(10.0d, precision)));
                sb.append("");
                str3 = sb.toString();
                String str6 = null;
                try {
                    Protocol.Transaction packTransaction_sign = WalletUtils.packTransaction_sign(this.mBean.currentTransaction2);
                    this.transaction = packTransaction_sign;
                    AssetIssueContractOuterClass.TransferAssetContract transferAssetContract = (AssetIssueContractOuterClass.TransferAssetContract) TransactionUtils.unpackContract(packTransaction_sign.getRawData().getContract(0), AssetIssueContractOuterClass.TransferAssetContract.class);
                    org.bouncycastle.util.encoders.Hex.toHexString(transferAssetContract.getToAddress().toByteArray());
                    AssetIssueContractDao assetIssueContractDao = (AssetIssueContractDao) DaoUtils.getDicInstance().QueryById(Long.parseLong(new String(transferAssetContract.getAssetName().toByteArray())), AssetIssueContractDao.class);
                    str6 = assetIssueContractDao.abbr;
                    if (assetIssueContractDao.getId().equals(1002000L) && IRequest.isRelease() && SpAPI.THIS.getCurIsMainChain()) {
                        str6 = "BTTOLD";
                    }
                    double amount = transferAssetContract.getAmount();
                    if (assetIssueContractDao.getPrecision() != 0) {
                        amount /= Math.pow(10.0d, assetIssueContractDao.getPrecision());
                    }
                    this.numberFormat.format(amount);
                } catch (InvalidProtocolBufferException e2) {
                    LogUtils.e(e2);
                } catch (NullPointerException e3) {
                    SentryUtil.captureException(e3);
                }
                if (!TextUtils.isEmpty(str5)) {
                    if (!TextUtils.isEmpty(str6)) {
                        str3 = str3 + "(" + str6 + ")";
                    } else {
                        str3 = str3 + "(" + str5 + ")";
                    }
                }
                break;
            case '\r':
                str2 = this.mContext.getString(R.string.Transfer);
                str3 = this.numberFormat.format(dataBean.contractData.amount / 1000000.0d) + " TRX";
                this.llReceive.setVisibility(View.VISIBLE);
                TextView textView = this.transactionTo;
                TextView textView2 = this.tvSubTo;
                boolean isEmpty = TextUtils.isEmpty(dataBean.contractData.receiver_address);
                DealSignOutput.DataBeanX.DataBean.ContractDataBean contractDataBean = dataBean.contractData;
                setAddressAndName(textView, textView2, isEmpty ? contractDataBean.to_address : contractDataBean.receiver_address);
                break;
            case 14:
                str2 = this.mContext.getString(R.string.account_update_permission);
                str3 = getAccountUpdateFee(dataBean);
                this.initiator.setText(R.string.control_address);
                this.receivables.setText(R.string.permission_detail_updated);
                this.llReceive.setVisibility(View.GONE);
                break;
            case 15:
                this.transactionTo.setVisibility(View.VISIBLE);
                this.tvSubTo.setVisibility(View.VISIBLE);
                str2 = this.mContext.getString(R.string.trans_type_delegate_v2);
                str3 = this.numberFormat.format(dataBean.contractData.balance / 1000000.0d) + " TRX";
                this.initiator.setText(R.string.multi_delegate_from);
                setAddressAndName(this.transactionFrom, this.tvSubFrom, dataBean.contractData.owner_address);
                setAddressAndName(this.transactionTo, this.tvSubTo, dataBean.contractData.receiver_address);
                this.llType.setVisibility(View.VISIBLE);
                if (getResourceTypeFromDelegate(dataBean) == 1) {
                    this.resourceType.setText(R.string.energy);
                } else if (getResourceTypeFromDelegate(dataBean) == 0) {
                    this.resourceType.setText(R.string.bandwidth);
                } else {
                    this.llType.setVisibility(View.GONE);
                }
                break;
            case 17:
                str2 = this.mContext.getString(R.string.TRC10_unfreeze);
                this.initiator.setText(R.string.freeze_address);
                this.receivables.setText(R.string.receiving_address_withdot);
                break;
            case 20:
                str2 = this.mContext.getString(R.string.Vote);
                str3 = getVoteCount(dataBean);
                this.initiator.setText(this.mContext.getString(R.string.vote_address_withdot));
                this.receivables.setText(this.mContext.getString(R.string.vote_detail));
                this.tvSubTo.setVisibility(View.GONE);
                this.transactionTo.setVisibility(View.GONE);
                this.transactionActon.setVisibility(View.VISIBLE);
                this.transactionActon.setOnClickListener(new NoDoubleClickListener2() {
                    @Override
                    public void onNoDoubleClick(View view) {
                        if (mBean == null || TextUtils.isEmpty(mBean.currentTransaction2)) {
                            return;
                        }
                        Protocol.Transaction packTransaction_sign2 = WalletUtils.packTransaction_sign(mBean.currentTransaction2);
                        if (packTransaction_sign2 == null) {
                            IToast.getIToast().setImage(R.mipmap.toast_error).show(R.string.create_transaction_fail);
                        } else if (packTransaction_sign2 == null || packTransaction_sign2.getRawData() == null || packTransaction_sign2.getRawData().getContractCount() == 0) {
                            IToast.getIToast().setImage(R.mipmap.toast_error).show(R.string.create_transaction_fail);
                        } else {
                            ConfirmMultiSignVoteActivity.startActivity(mContext, ParamBuildUtils.getVoteTransactionParamBuilder(new HashMap(), new HashMap(), true, packTransaction_sign2));
                        }
                    }
                });
                break;
            case 21:
                this.transactionTo.setVisibility(View.VISIBLE);
                this.tvSubTo.setVisibility(View.VISIBLE);
                this.rlOriginator.setVisibility(View.GONE);
                str2 = this.mContext.getString(R.string.trans_type_unstake_v2);
                str3 = this.numberFormat.format(dataBean.contractData.unfreeze_balance / 1000000.0d) + " TRX";
                setAddressAndName(this.transactionTo, this.tvSubTo, dataBean.contractData.owner_address);
                this.llType.setVisibility(View.VISIBLE);
                if (getResourceTypeFromUnFreezeBalanceV2(dataBean) == 1) {
                    this.resourceType.setText(R.string.energy);
                } else if (getResourceTypeFromUnFreezeBalanceV2(dataBean) == 0) {
                    this.resourceType.setText(R.string.bandwidth);
                } else {
                    this.llType.setVisibility(View.GONE);
                }
                break;
        }
        this.tvTransType.setVisibility(StringTronUtil.isEmpty(str2) ? View.GONE : View.VISIBLE);
        this.tvTransContent.setVisibility(StringTronUtil.isEmpty(str3) ? View.GONE : View.VISIBLE);
        this.tvTransType.setText(str2);
        this.tvTransContent.setText(str3);
    }

    private boolean isDisApproveProposal(DealSignOutput.DataBeanX.DataBean dataBean) {
        if (dataBean == null || dataBean.currentTransaction2 == null) {
            return true;
        }
        try {
            return !((ProposalContract.ProposalApproveContract) TransactionUtils.unpackContract(WalletUtils.packTransaction_sign(dataBean.currentTransaction2).getRawData().getContract(0), ProposalContract.ProposalApproveContract.class)).getIsAddApproval();
        } catch (InvalidProtocolBufferException e) {
            LogUtils.e(e);
            return true;
        }
    }

    private String getWithdrawBalanceContent(DealSignOutput.DataBeanX.DataBean dataBean) {
        if (dataBean == null || dataBean.currentTransaction2 == null) {
            return "";
        }
        try {
            BalanceContract.WithdrawBalanceContract withdrawBalanceContract = (BalanceContract.WithdrawBalanceContract) TransactionUtils.unpackContract(WalletUtils.packTransaction_sign(dataBean.currentTransaction2).getRawData().getContract(0), BalanceContract.WithdrawBalanceContract.class);
            return "";
        } catch (InvalidProtocolBufferException e) {
            LogUtils.e(e);
            return "";
        }
    }

    private String getVoteCount(DealSignOutput.DataBeanX.DataBean dataBean) {
        if (dataBean == null || dataBean.contractData == null || dataBean.contractData.votes == null || dataBean.contractData.votes.size() == 0) {
            return " ";
        }
        int size = dataBean.contractData.votes.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            AllTransferOutput.DataBean.ContractDataBean.VotesBean votesBean = dataBean.contractData.votes.get(i2);
            if (votesBean != null && votesBean.vote_count > 0) {
                i = (int) (i + votesBean.vote_count);
            }
        }
        return i + "";
    }

    private int getResourceTypeFromFreezeBalance(DealSignOutput.DataBeanX.DataBean dataBean) {
        if (dataBean == null || dataBean.contractData == null) {
            return -1;
        }
        try {
            BalanceContract.FreezeBalanceContract freezeBalanceContract = (BalanceContract.FreezeBalanceContract) TransactionUtils.unpackContract(WalletUtils.packTransaction_sign(dataBean.currentTransaction2).getRawData().getContract(0), BalanceContract.FreezeBalanceContract.class);
            if (freezeBalanceContract.getResource() == Common.ResourceCode.BANDWIDTH) {
                return 0;
            }
            return freezeBalanceContract.getResource() == Common.ResourceCode.ENERGY ? 1 : -1;
        } catch (InvalidProtocolBufferException e) {
            LogUtils.e(e);
            return -1;
        }
    }

    private int getResourceTypeFromFreezeBalanceV2(DealSignOutput.DataBeanX.DataBean dataBean) {
        if (dataBean == null || dataBean.contractData == null) {
            return -1;
        }
        try {
            BalanceContract.FreezeBalanceV2Contract freezeBalanceV2Contract = (BalanceContract.FreezeBalanceV2Contract) TransactionUtils.unpackContract(WalletUtils.packTransaction_sign(dataBean.currentTransaction2).getRawData().getContract(0), BalanceContract.FreezeBalanceV2Contract.class);
            if (freezeBalanceV2Contract.getResource() == Common.ResourceCode.BANDWIDTH) {
                return 0;
            }
            return freezeBalanceV2Contract.getResource() == Common.ResourceCode.ENERGY ? 1 : -1;
        } catch (InvalidProtocolBufferException e) {
            LogUtils.e(e);
            return -1;
        }
    }

    private int getResourceTypeFromUnFreezeBalace(DealSignOutput.DataBeanX.DataBean dataBean) {
        if (dataBean == null || dataBean.currentTransaction2 == null) {
            return -1;
        }
        try {
            BalanceContract.UnfreezeBalanceContract unfreezeBalanceContract = (BalanceContract.UnfreezeBalanceContract) TransactionUtils.unpackContract(WalletUtils.packTransaction_sign(dataBean.currentTransaction2).getRawData().getContract(0), BalanceContract.UnfreezeBalanceContract.class);
            if (unfreezeBalanceContract.getResource() == Common.ResourceCode.BANDWIDTH) {
                return 0;
            }
            return unfreezeBalanceContract.getResource() == Common.ResourceCode.ENERGY ? 1 : -1;
        } catch (InvalidProtocolBufferException e) {
            LogUtils.e(e);
            return -1;
        }
    }

    private int getResourceTypeFromUnFreezeBalanceV2(DealSignOutput.DataBeanX.DataBean dataBean) {
        if (dataBean == null || dataBean.currentTransaction2 == null) {
            return -1;
        }
        try {
            BalanceContract.UnfreezeBalanceV2Contract unfreezeBalanceV2Contract = (BalanceContract.UnfreezeBalanceV2Contract) TransactionUtils.unpackContract(WalletUtils.packTransaction_sign(dataBean.currentTransaction2).getRawData().getContract(0), BalanceContract.UnfreezeBalanceV2Contract.class);
            if (unfreezeBalanceV2Contract.getResource() == Common.ResourceCode.BANDWIDTH) {
                return 0;
            }
            return unfreezeBalanceV2Contract.getResource() == Common.ResourceCode.ENERGY ? 1 : -1;
        } catch (InvalidProtocolBufferException e) {
            LogUtils.e(e);
            return -1;
        }
    }

    private int getResourceTypeFromDelegate(DealSignOutput.DataBeanX.DataBean dataBean) {
        if (dataBean == null || dataBean.currentTransaction2 == null) {
            return -1;
        }
        try {
            BalanceContract.DelegateResourceContract delegateResourceContract = (BalanceContract.DelegateResourceContract) TransactionUtils.unpackContract(WalletUtils.packTransaction_sign(dataBean.currentTransaction2).getRawData().getContract(0), BalanceContract.DelegateResourceContract.class);
            if (delegateResourceContract.getResource() == Common.ResourceCode.BANDWIDTH) {
                return 0;
            }
            return delegateResourceContract.getResource() == Common.ResourceCode.ENERGY ? 1 : -1;
        } catch (InvalidProtocolBufferException e) {
            LogUtils.e(e);
            return -1;
        }
    }

    private int getResourceTypeFromUnDelegate(DealSignOutput.DataBeanX.DataBean dataBean) {
        if (dataBean == null || dataBean.currentTransaction2 == null) {
            return -1;
        }
        try {
            BalanceContract.UnDelegateResourceContract unDelegateResourceContract = (BalanceContract.UnDelegateResourceContract) TransactionUtils.unpackContract(WalletUtils.packTransaction_sign(dataBean.currentTransaction2).getRawData().getContract(0), BalanceContract.UnDelegateResourceContract.class);
            if (unDelegateResourceContract.getResource() == Common.ResourceCode.BANDWIDTH) {
                return 0;
            }
            return unDelegateResourceContract.getResource() == Common.ResourceCode.ENERGY ? 1 : -1;
        } catch (InvalidProtocolBufferException e) {
            LogUtils.e(e);
            return -1;
        }
    }

    private void closeOrOpenAddressList(boolean z) {
        if (z) {
            this.icProgress.setRotation(0.0f);
            this.rvSignAddress.setVisibility(View.VISIBLE);
            return;
        }
        this.icProgress.setRotation(180.0f);
        this.rvSignAddress.setVisibility(View.GONE);
    }

    private int getPrecision(String str) {
        AssetIssueContractDao assetIssueContractDao;
        if (TextUtils.isEmpty(str) || (assetIssueContractDao = (AssetIssueContractDao) DaoUtils.getDicInstance().QueryById(Long.parseLong(str), AssetIssueContractDao.class)) == null) {
            return 0;
        }
        return assetIssueContractDao.getPrecision();
    }

    public String getSmartTransactionAddress() {
        SmartContractOuterClass.TriggerSmartContract triggerSmartContract;
        TriggerData transferData;
        String str;
        try {
            DealSignOutput.DataBeanX.DataBean dataBean = this.mBean;
            if (dataBean == null || TextUtils.isEmpty(dataBean.currentTransaction2)) {
                return "";
            }
            Protocol.Transaction packTransaction_sign = WalletUtils.packTransaction_sign(this.mBean.currentTransaction2);
            this.transaction = packTransaction_sign;
            if (packTransaction_sign == null || (triggerSmartContract = (SmartContractOuterClass.TriggerSmartContract) TransactionUtils.unpackContract(packTransaction_sign.getRawData().getContract(0), SmartContractOuterClass.TriggerSmartContract.class)) == null) {
                return "";
            }
            DealSignOutput.DataBeanX.DataBean dataBean2 = this.mBean;
            if (dataBean2 != null && dataBean2.functionSelector != null) {
                transferData = TransactionDataUtils.getInstance().parseDataByFun(triggerSmartContract, this.mBean.functionSelector);
            } else {
                transferData = TransactionDataUtils.getInstance().getTransferData(triggerSmartContract);
            }
            if (transferData == null || transferData.getParameterMap().isEmpty()) {
                return "";
            }
            if (transferData.getParameterMap().size() == 3) {
                str = transferData.getParameterMap().get("1");
            } else {
                str = transferData.getParameterMap().get("0");
            }
            return str;
        } catch (InvalidProtocolBufferException e) {
            LogUtils.e(e);
            return "";
        } catch (JsonFormat.ParseException e2) {
            LogUtils.e(e2);
            return "";
        }
    }

    private java.lang.String getSmartTransactionValue(com.tron.wallet.common.bean.DealSignOutput.DataBeanX.DataBean.Trc20InfoBean r18) {
        


return null;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.common.adapter.holder.DealSignHolder.getSmartTransactionValue(com.tron.wallet.common.bean.DealSignOutput$DataBeanX$DataBean$Trc20InfoBean):java.lang.String");
    }

    private void sign() {
        try {
            Wallet wallet = WalletUtils.getWallet(this.mWalletName);
            if (wallet != null && LedgerWallet.isLedger(wallet) && !SpAPI.THIS.getCurrentChain().isMainChain) {
                try {
                    IToast.getIToast().setImage(R.mipmap.toast_error).show(R.string.ledger_not_support_on_dappchain);
                    return;
                } catch (Exception e) {
                    LogUtils.e(e);
                    return;
                }
            } else if (wallet != null && wallet.isWatchOnly() && !SpAPI.THIS.getCurrentChain().isMainChain) {
                try {
                    IToast.getIToast().setImage(R.mipmap.toast_error).show(R.string.no_support);
                    return;
                } catch (Exception e2) {
                    LogUtils.e(e2);
                    return;
                }
            } else if (wallet != null && wallet.isSamsungWallet()) {
                try {
                    IToast.getIToast().setImage(R.mipmap.toast_error).show(R.string.no_samsung_to_shield);
                    return;
                } catch (Exception e3) {
                    LogUtils.e(e3);
                    return;
                }
            } else {
                DealSignOutput.DataBeanX.DataBean dataBean = this.mBean;
                if (dataBean != null && !TextUtils.isEmpty(dataBean.currentTransaction2)) {
                    Protocol.Transaction packTransaction_sign = WalletUtils.packTransaction_sign(this.mBean.currentTransaction2);
                    if (TransactionDataUtils.getInstance().checkFunValid(this.mBean.functionSelector, packTransaction_sign)) {
                        dealSign(packTransaction_sign);
                        return;
                    } else {
                        showCheckDialog(packTransaction_sign);
                        return;
                    }
                }
                IToast.getIToast().setImage(R.mipmap.toast_error).show(R.string.create_transaction_fail);
                return;
            }
        } catch (Exception e4) {
            IToast.getIToast().setImage(R.mipmap.toast_error).show(R.string.create_transaction_fail);
            LogUtils.e(e4);
        }
        IToast.getIToast().setImage(R.mipmap.toast_error).show(R.string.create_transaction_fail);
        LogUtils.e(e4);
    }

    public void dealSign(Protocol.Transaction transaction) {
        if (transaction == null) {
            IToast.getIToast().setImage(R.mipmap.toast_error).show(R.string.create_transaction_fail);
        } else if (transaction == null || transaction.getRawData() == null || transaction.getRawData().getContractCount() == 0) {
            IToast.getIToast().setImage(R.mipmap.toast_error).show(R.string.create_transaction_fail);
        } else {
            try {
                if (TextUtils.equals(this.mBean.contractType, "FreezeBalanceContract")) {
                    createFreezeParam(transaction, new Consumer() {
                        @Override
                        public final void accept(Object obj) {
                            lambda$dealSign$0((BaseConfirmTransParamBuilder) obj);
                        }
                    });
                } else {
                    ConfirmTransactionNewActivity.startActivity(this.mContext, getTransactionParam(transaction));
                }
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
    }

    public void lambda$dealSign$0(BaseConfirmTransParamBuilder baseConfirmTransParamBuilder) throws Exception {
        ConfirmTransactionNewActivity.startActivity(this.mContext, baseConfirmTransParamBuilder);
    }

    private void createFreezeParam(final Protocol.Transaction transaction, Consumer<BaseConfirmTransParamBuilder> consumer) {
        Single.create(new SingleOnSubscribe() {
            @Override
            public final void subscribe(SingleEmitter singleEmitter) {
                lambda$createFreezeParam$1(transaction, singleEmitter);
            }
        }).compose(RxSchedulers2.io_main_single()).subscribe(consumer, new MigrateActivityExternalSyntheticLambda2());
    }

    public void lambda$createFreezeParam$1(Protocol.Transaction transaction, SingleEmitter singleEmitter) throws Exception {
        singleEmitter.onSuccess(DealSignParamUtils.getFreezeResourceParamBuilder(this.mBean, this.mWalletName, getResourceTypeFromUnFreezeBalace(this.mBean) == 0, transaction));
    }

    private void showCheckDialog(final Protocol.Transaction transaction) {
        CustomDialog.Builder builder = new CustomDialog.Builder(this.mContext);
        final CustomDialog build = builder.style(R.style.loading_dialog).cancelTouchout(true).heightDimenRes(-2).widthDimenRes(-1).setGravity(17).view(R.layout.fail_dialog).build();
        build.setCanceledOnTouchOutside(false);
        View view = builder.getView();
        TextView textView = (TextView) view.findViewById(R.id.tv_ok);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_ok2);
        ((LinearLayout) view.findViewById(R.id.ll_double)).setVisibility(View.VISIBLE);
        textView.setText(this.mContext.getString(R.string.sign));
        ((TextView) view.findViewById(R.id.tv_content)).setText(this.mContext.getString(R.string.transactions_check));
        ((TextView) view.findViewById(R.id.tv_cancle)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                build.dismiss();
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                build.dismiss();
                dealSign(transaction);
            }
        });
        build.show();
    }

    private BaseConfirmTransParamBuilder getTransactionParam(Protocol.Transaction transaction) throws Exception {
        String str = this.mBean.contractType;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1705044092:
                if (str.equals("WithdrawBalanceContract")) {
                    c = 0;
                    break;
                }
                break;
            case -1048760864:
                if (str.equals("ProposalCreateContract")) {
                    c = 1;
                    break;
                }
                break;
            case -651921570:
                if (str.equals("UnfreezeBalanceContract")) {
                    c = 2;
                    break;
                }
                break;
            case 180125197:
                if (str.equals("ProposalApproveContract")) {
                    c = 3;
                    break;
                }
                break;
            case 270660495:
                if (str.equals("ProposalDeleteContract")) {
                    c = 4;
                    break;
                }
                break;
            case 706457047:
                if (str.equals("TransferAssetContract")) {
                    c = 5;
                    break;
                }
                break;
            case 710366781:
                if (str.equals("TransferContract")) {
                    c = 6;
                    break;
                }
                break;
            case 762691543:
                if (str.equals("AccountPermissionUpdateContract")) {
                    c = 7;
                    break;
                }
                break;
            case 1190060069:
                if (str.equals("ShieldedTransferContract")) {
                    c = '\b';
                    break;
                }
                break;
            case 1421429571:
                if (str.equals("TriggerSmartContract")) {
                    c = '\t';
                    break;
                }
                break;
            case 1699052801:
                if (str.equals("VoteWitnessContract")) {
                    c = '\n';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return DealSignParamUtils.getProfitParamBuilder(this.mBean, this.mWalletName, transaction, this.mBean.contractData.owner_address);
            case 1:
            case 3:
                DealSignOutput.DataBeanX.DataBean dataBean = this.mBean;
                return DealSignParamUtils.getMakeProposalParamBuilder(dataBean, this.mWalletName, true ^ isDisApproveProposal(dataBean), transaction);
            case 2:
                boolean z = getResourceTypeFromUnFreezeBalace(this.mBean) == 0;
                ArrayList arrayList = new ArrayList();
                arrayList.add(transaction);
                String str2 = this.mBean.contractData.receiver_address;
                if (TextUtils.isEmpty(str2)) {
                    str2 = this.mBean.contractData.owner_address;
                }
                return DealSignParamUtils.getUnfreezeResourceParamBuilder(this.mBean, this.mWalletName, z, str2, arrayList);
            case 4:
                return DealSignParamUtils.getDeleteProposalParamBuilder(this.mBean, this.mWalletName, transaction);
            case 5:
            case 6:
            case '\b':
                return DealSignParamUtils.getTransferParamBuilder(this.mBean, this.mWalletName, transaction);
            case 7:
                return DealSignParamUtils.getAccountPermissionUpdateParamBuilder(this.mBean, this.mWalletName, transaction);
            case '\t':
                TriggerUtils.parseFuncToTokenActionType(transaction, this.mBean.functionSelector, null);
                SmartContractOuterClass.TriggerSmartContract triggerSmartContract = (SmartContractOuterClass.TriggerSmartContract) TransactionUtils.unpackContract(transaction.getRawData().getContract(0), SmartContractOuterClass.TriggerSmartContract.class);
                DealSignOutput.DataBeanX.DataBean dataBean2 = this.mBean;
                if (dataBean2 != null && dataBean2.currentTransaction != null && this.mBean.currentTransaction.raw_data != null && this.mBean.currentTransaction.raw_data.data != null) {
                    T.note = new String(Hex.decodeHex(this.mBean.currentTransaction.raw_data.data));
                }
                return ParamBuildUtils.getParticipateMultisignTransactionParamBuilder(this.mWalletName, transaction, (ArrayList) this.mBean.signatureProgress, this.mBean.threshold);
            case '\n':
                return DealSignParamUtils.getWitnessVote(this.mBean, this.mWalletName, transaction);
            default:
                return ParamBuildUtils.getParticipateMultisignTransactionParamBuilder(this.mWalletName, transaction, (ArrayList) this.mBean.signatureProgress, this.mBean.threshold);
        }
    }

    private void setAddressAndName(TextView textView, TextView textView2, String str) {
        if (TextUtils.isEmpty(str) || textView == null || textView2 == null) {
            return;
        }
        String nameByAddress = AddressNameUtils.getInstance().getNameByAddress(str, false);
        if (TextUtils.isEmpty(nameByAddress)) {
            textView.setVisibility(View.GONE);
            textView2.setText(str);
            textView2.setTextColor(this.itemView.getContext().getResources().getColor(R.color.black_23));
            return;
        }
        textView.setVisibility(View.VISIBLE);
        textView.setText(nameByAddress);
        textView2.setText(str);
        textView2.setTextColor(this.itemView.getContext().getResources().getColor(R.color.gray_6D778C));
    }
}
