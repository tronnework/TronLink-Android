package com.tron.wallet.business.confirm.fg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.parambuilder.bean.TransferParam;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.config.AccountFeeManager;
import com.tron.wallet.common.config.T;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.BandwidthUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.FgConfirmTransferBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.text.NumberFormat;
import java.util.Locale;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
import org.tron.protos.contract.AssetIssueContractOuterClass;
import org.tron.protos.contract.BalanceContract;
import org.tron.protos.contract.ShieldContract;
import org.tron.protos.contract.SmartContractOuterClass;
public class ConfirmTransferFragment extends BaseFragment<EmptyPresenter, EmptyModel> {
    private String amount;
    private TransferParam baseParam;
    private FgConfirmTransferBinding binding;
    Button btGo;
    FrameLayout flRoot;
    LinearLayout llEnergy;
    LinearLayout llMiddle;
    RelativeLayout rlAddress;
    RelativeLayout rlFromAddress;
    RelativeLayout rlNote;
    ViewGroup serviceChargeLayout;
    TextView tvAddress;
    TextView tvFee;
    TextView tvFeeAmountBw;
    TextView tvFromAddress;
    TextView tvNoBandwidth;
    TextView tvNoEnergy;
    TextView tvNote;
    TextView tvRealMoney;
    private int walletType;

    public void setParam(TransferParam transferParam) {
        this.baseParam = transferParam;
    }

    @Override
    protected void processData() {
        this.btGo.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                send();
            }
        });
        updateUI();
    }

    public void send() {
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$send$0();
            }
        });
    }

    public void lambda$send$0() {
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
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setType(0);
        FgConfirmTransferBinding inflate = FgConfirmTransferBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
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
        this.tvAddress = this.binding.tvAddress;
        this.llMiddle = this.binding.llMiddle;
        this.flRoot = this.binding.flRoot;
        this.llEnergy = this.binding.llEnergy;
        this.btGo = this.binding.btGo;
        this.tvRealMoney = this.binding.tvRealMoney;
        this.tvFeeAmountBw = this.binding.tvFeeAmountBw;
        this.tvNoBandwidth = this.binding.tvNoBandwidth;
        this.tvNoEnergy = this.binding.tvNoEnergy;
        this.tvFromAddress = this.binding.tvFromAddress;
        this.rlFromAddress = this.binding.rlFromAddress;
        this.rlAddress = this.binding.rlAddress;
        this.tvFee = this.binding.tvFee;
        this.tvNote = this.binding.tvNote;
        this.rlNote = this.binding.rlNote;
        this.serviceChargeLayout = this.binding.rlMiddleThree;
    }

    public void updateUI() {
        String encode58Check;
        String str;
        String str2;
        TransferParam transferParam = this.baseParam;
        if (transferParam == null || transferParam.getTransactionBean() == null) {
            return;
        }
        try {
            int createType = WalletUtils.getSelectedPublicWallet().getCreateType();
            this.walletType = createType;
            if (createType == 8 && this.baseParam.hasOwnerPermission()) {
                this.btGo.setText(R.string.request_from_ledger);
            } else {
                this.btGo.setText(R.string.confirm);
            }
            NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
            numberInstance.setMaximumFractionDigits(6);
            Protocol.Transaction parseFrom = Protocol.Transaction.parseFrom(this.baseParam.getTransactionBean().getBytes().get(0));
            Protocol.Transaction.Contract contract = parseFrom.getRawData().getContract(0);
            int i = fun3.$SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[contract.getType().ordinal()];
            if (i == 1) {
                BalanceContract.TransferContract transferContract = (BalanceContract.TransferContract) TransactionUtils.unpackContract(parseFrom.getRawData().getContract(0), BalanceContract.TransferContract.class);
                this.amount = numberInstance.format(transferContract.getAmount() / 1000000.0d);
                encode58Check = StringTronUtil.encode58Check(transferContract.getToAddress().toByteArray());
                str = "\tTRX";
            } else if (i == 2) {
                AssetIssueContractOuterClass.TransferAssetContract transferAssetContract = (AssetIssueContractOuterClass.TransferAssetContract) TransactionUtils.unpackContract(parseFrom.getRawData().getContract(0), AssetIssueContractOuterClass.TransferAssetContract.class);
                SpAPI.THIS.getAssetsList();
                TokenBean tokenBean = this.baseParam.getTokenBean();
                if (tokenBean != null) {
                    this.amount = numberInstance.format(transferAssetContract.getAmount() / Math.pow(10.0d, tokenBean.getPrecision()));
                    StringBuilder sb = new StringBuilder("\t");
                    sb.append(tokenBean.shortName.equals("") ? tokenBean.name : tokenBean.shortName);
                    str2 = sb.toString();
                } else {
                    str2 = "";
                }
                String str3 = str2;
                encode58Check = StringTronUtil.encode58Check(transferAssetContract.getToAddress().toByteArray());
                str = str3;
            } else if (i == 3) {
                SmartContractOuterClass.TriggerSmartContract triggerSmartContract = (SmartContractOuterClass.TriggerSmartContract) TransactionUtils.unpackContract(parseFrom.getRawData().getContract(0), SmartContractOuterClass.TriggerSmartContract.class);
                TokenBean tokenBean2 = this.baseParam.getTokenBean();
                String str4 = T.toAddress;
                numberInstance.setMaximumFractionDigits(tokenBean2.getPrecision());
                this.amount = T.amount;
                StringBuilder sb2 = new StringBuilder("\t");
                sb2.append(tokenBean2.shortName.equals("") ? tokenBean2.name : tokenBean2.shortName);
                str = sb2.toString();
                this.tvNoBandwidth.setText(R.string.trc20_transfer);
                if (!BandwidthUtils.isEnoughBandwidth(getIContext(), BandwidthUtils.getBandwidthCost(parseFrom))) {
                    this.llEnergy.setVisibility(View.VISIBLE);
                    this.tvNoEnergy.setText(R.string.trc20_transfer);
                }
                encode58Check = str4;
            } else if (i != 4) {
                str = "";
                encode58Check = str;
            } else {
                ShieldContract.ShieldedTransferContract shieldedTransferContract = (ShieldContract.ShieldedTransferContract) TransactionUtils.unpackContract(parseFrom.getRawData().getContract(0), ShieldContract.ShieldedTransferContract.class);
                this.amount = T.amount;
                encode58Check = T.toAddress;
                str = "\tTRZ";
            }
            TextView textView = this.tvRealMoney;
            textView.setText(this.amount + "  " + str);
            this.tvFromAddress.setText(this.baseParam.getFromAddress());
            this.tvAddress.setText(encode58Check);
            if (!StringTronUtil.isNullOrEmpty(T.note)) {
                this.tvNote.setText(T.note);
                this.rlNote.setVisibility(View.VISIBLE);
            } else {
                this.rlNote.setVisibility(View.GONE);
            }
            if (contract.getType() == Protocol.Transaction.Contract.ContractType.TriggerSmartContract) {
                this.llMiddle.setVisibility(View.GONE);
            }
            if (contract.getType() == Protocol.Transaction.Contract.ContractType.ShieldedTransferContract) {
                TextView textView2 = this.tvFee;
                textView2.setText(numberInstance.format(T.shieldFee) + "\tTRZ");
                this.serviceChargeLayout.setVisibility(View.GONE);
            } else {
                if (contract.getType() == Protocol.Transaction.Contract.ContractType.TriggerSmartContract) {
                    TextView textView3 = this.tvFee;
                    textView3.setText(numberInstance.format(T.shieldFee) + "\tTRX");
                } else {
                    TextView textView4 = this.tvFee;
                    textView4.setText(numberInstance.format(T.shieldFee) + "\tTRX");
                }
                if (!T.isActiveAccount && (T.shieldFee * 10.0d) % 10.0d == 1.0d) {
                    this.serviceChargeLayout.setVisibility(View.VISIBLE);
                    TextView textView5 = this.tvFeeAmountBw;
                    textView5.setText("0\t" + getString(R.string.bandwidth));
                } else {
                    TransferParam transferParam2 = this.baseParam;
                    if (transferParam2 != null && !transferParam2.getFromAddress().equals(WalletUtils.getSelectedPublicWallet().getAddress())) {
                        if (!T.isActiveAccount && ((int) ((T.shieldFee * 1000.0d) % 1000.0d)) > 0) {
                            double d = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                            if ((T.shieldFee * 10.0d) % 10.0d != 1.0d) {
                                if (this.baseParam.getTokenBean().getType() != 2) {
                                    d = AccountFeeManager.getExternActiviteFee();
                                }
                                T.shieldFee = d + 1.0d + (BandwidthUtils.getBandwidthCost(parseFrom) * TronConfig.feeBandWidth);
                                d = BandwidthUtils.getBandwidthCost(parseFrom) * TronConfig.feeBandWidth;
                            }
                            double d2 = d;
                            TextView textView6 = this.tvFee;
                            textView6.setText(numberInstance.format(T.shieldFee) + "\tTRX");
                            if (parseFrom.getRawData().getContract(0).getType() != Protocol.Transaction.Contract.ContractType.TriggerSmartContract) {
                                this.llMiddle.setVisibility(View.GONE);
                            } else {
                                this.llEnergy.setVisibility(View.VISIBLE);
                                String string = getResources().getString(R.string.no_enough_band_width);
                                numberInstance.setMaximumFractionDigits(6);
                                this.tvNoBandwidth.setText(String.format(string, numberInstance.format(d2) + ""));
                            }
                        } else if (((int) ((T.shieldFee * 100000.0d) % 10000.0d)) > 0) {
                            T.shieldFee = (BandwidthUtils.getBandwidthCost(parseFrom) * TronConfig.feeBandWidth) + 1.0d;
                            if (parseFrom.getRawData().getContract(0).getType() != Protocol.Transaction.Contract.ContractType.TriggerSmartContract) {
                                this.llMiddle.setVisibility(View.VISIBLE);
                            }
                            if (this.baseParam.getTokenBean().type >= 2) {
                                this.llEnergy.setVisibility(View.VISIBLE);
                            }
                            String string2 = getResources().getString(R.string.no_enough_band_width);
                            double bandwidthCost = BandwidthUtils.getBandwidthCost(parseFrom) * TronConfig.feeBandWidth;
                            TextView textView7 = this.tvFeeAmountBw;
                            textView7.setText("0\t" + getString(R.string.bandwidth));
                            numberInstance.setMaximumFractionDigits(6);
                            this.tvNoBandwidth.setText(String.format(string2, numberInstance.format(bandwidthCost) + ""));
                            TextView textView8 = this.tvFee;
                            textView8.setText(numberInstance.format(T.shieldFee) + "\tTRX");
                        } else {
                            this.serviceChargeLayout.setVisibility(View.VISIBLE);
                            enoughBandWidth(this.llMiddle, this.tvNoBandwidth, this.tvFeeAmountBw, parseFrom);
                        }
                    } else {
                        this.serviceChargeLayout.setVisibility(View.VISIBLE);
                        enoughBandWidth(this.llMiddle, this.tvNoBandwidth, this.tvFeeAmountBw, parseFrom);
                    }
                }
            }
            this.flRoot.getLayoutParams();
        } catch (Exception e) {
            LogUtils.e(e);
            SentryUtil.captureException(e);
            ToastError(StringTronUtil.getResString(R.string.parsererror));
        }
    }

    public static class fun3 {
        static final int[] $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType;

        static {
            int[] iArr = new int[Protocol.Transaction.Contract.ContractType.values().length];
            $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType = iArr;
            try {
                iArr[Protocol.Transaction.Contract.ContractType.TransferContract.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.TransferAssetContract.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.TriggerSmartContract.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.ShieldedTransferContract.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public void setClick() {
        this.binding.ivCloseOne.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (view.getId() != R.id.iv_close_one) {
                    return;
                }
                mContext.finish();
            }
        });
    }

    public void enoughBandWidth(LinearLayout linearLayout, TextView textView, TextView textView2, Protocol.Transaction transaction) {
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        numberInstance.setMaximumFractionDigits(6);
        if (!BandwidthUtils.isEnoughBandwidth(getIContext(), BandwidthUtils.getBandwidthCost(transaction))) {
            double bandwidthCost = BandwidthUtils.getBandwidthCost(transaction) * TronConfig.feeBandWidth;
            String string = getResources().getString(R.string.no_enough_band_width);
            String format = String.format(string, numberInstance.format(bandwidthCost) + "");
            if (transaction.getRawData().getContract(0).getType() != Protocol.Transaction.Contract.ContractType.TriggerSmartContract) {
                linearLayout.setVisibility(View.VISIBLE);
            } else {
                this.llEnergy.setVisibility(View.VISIBLE);
            }
            this.serviceChargeLayout.setVisibility(View.VISIBLE);
            textView2.setText("0\t" + getString(R.string.bandwidth));
            numberInstance.setMaximumFractionDigits(6);
            textView.setText(format);
            return;
        }
        if (transaction.getRawData().getContract(0).getType() != Protocol.Transaction.Contract.ContractType.TriggerSmartContract) {
            linearLayout.setVisibility(View.GONE);
        } else {
            this.llEnergy.setVisibility(View.VISIBLE);
        }
        textView2.setText(numberInstance.format(BandwidthUtils.getBandwidthCost(transaction)) + "\t" + getString(R.string.bandwidth));
    }
}
