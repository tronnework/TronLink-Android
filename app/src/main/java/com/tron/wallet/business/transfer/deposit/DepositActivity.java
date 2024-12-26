package com.tron.wallet.business.transfer.deposit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.protobuf.ByteString;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.parambuilder.utils.ParamBuildUtils;
import com.tron.wallet.business.transfer.deposit.DepositContract;
import com.tron.wallet.common.bean.ChainBean;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.CurrencyEditText;
import com.tron.wallet.common.components.ErrorEdiTextLayout;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.M;
import com.tron.wallet.common.config.T;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.DensityUtils;
import com.tron.wallet.common.utils.FailUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.SamsungMultisignUtils;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.transaction.TriggerUtils;
import com.tron.wallet.databinding.AcDepositBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.LedgerWallet;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.reactivex.functions.Consumer;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.tron.api.GrpcAPI;
import org.tron.api.WalletExtensionGrpc;
import org.tron.api.WalletGrpc;
import org.tron.api.WalletSolidityGrpc;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
import org.tron.walletserver.ConnectErrorException;
import org.tron.walletserver.Wallet;
public class DepositActivity extends BaseActivity<DepositPresenter, DepositModel> implements DepositContract.View {
    public static String TOKEN_DATA = "TOKEN_DATA";
    private Protocol.Account account;
    private Protocol.Account accountPer;
    private List<ChainBean> allChainJson;
    private BigDecimal balance;
    private BigDecimal bigCount;
    private AcDepositBinding binding;
    private WalletExtensionGrpc.WalletExtensionBlockingStub blockingStubExtension;
    private WalletGrpc.WalletBlockingStub blockingStubFull;
    private WalletSolidityGrpc.WalletSolidityBlockingStub blockingStubSolidity;
    Button btGo;
    private ManagedChannel channelFull;
    private ManagedChannel channelSolidity;
    private ChainBean currentChain;
    ErrorEdiTextLayout eetAmout;
    CurrencyEditText etCount;
    ImageView ivBa;
    private ImageView ivQr;
    ImageView ivRa;
    ImageView ivSa;
    LinearLayout llBalanceAmout;
    LinearLayout llChainName;
    LinearLayout llSendAddress;
    LinearLayout llTopOne;
    LinearLayout llTopThree;
    LinearLayout llTopTr;
    private double mBalance;
    private long mDappToMainFee;
    private TokenBean mToken;
    private Wallet mWallet;
    private ChainBean mainChain;
    private String name;
    private NumberFormat numberFormat;
    private PopupWindow popupWindow;
    private double pow;
    private RxManager rxManager;
    private ChainBean sideChain;
    TextView tvAmountLine;
    TextView tvAmountName;
    TextView tvBalance;
    TextView tvChainName;
    TextView tvContent;
    TextView tvDepositType;
    TextView tvLineV;
    TextView tvMax;
    TextView tvName;
    TextView tvTokenid;
    TextView tvTypeAmount;
    TextView tvTypeChain;
    private String isTrx = "";
    private String addressType = "";
    private BigDecimal bigZero = new BigDecimal(0);
    private boolean go = true;

    public static void start(Context context, TokenBean tokenBean) {
        Intent intent = new Intent(context, DepositActivity.class);
        intent.putExtra(TOKEN_DATA, tokenBean);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        AcDepositBinding inflate = AcDepositBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        LinearLayout root = inflate.getRoot();
        mappingId();
        setClick();
        setView(root, 1);
        this.ivQr = getHeaderHolder().ivQr;
    }

    public void setClick() {
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id != R.id.bt_go) {
                    if (id != R.id.tv_max) {
                        return;
                    }
                    CurrencyEditText currencyEditText = etCount;
                    currencyEditText.setText(balance + "");
                } else if (SamsungMultisignUtils.isSamsungWallet(mWallet.getAddress())) {
                    DepositActivity depositActivity = DepositActivity.this;
                    depositActivity.toast(depositActivity.getString(R.string.no_samsung_to_shield));
                } else if (currentChain.isMainChain) {
                    goDeposit();
                } else if (LedgerWallet.isLedger(mWallet)) {
                    DepositActivity depositActivity2 = DepositActivity.this;
                    depositActivity2.toast(depositActivity2.getString(R.string.ledger_not_support_on_dappchain));
                } else if (mWallet.isWatchOnly()) {
                    DepositActivity depositActivity3 = DepositActivity.this;
                    depositActivity3.toast(depositActivity3.getString(R.string.no_support));
                } else {
                    goWithdraw();
                }
            }
        };
        this.binding.tvMax.setOnClickListener(noDoubleClickListener2);
        this.binding.btGo.setOnClickListener(noDoubleClickListener2);
    }

    private void mappingId() {
        this.tvName = this.binding.tvName;
        this.ivSa = this.binding.ivSa;
        this.llTopTr = this.binding.llTopTr;
        this.tvTokenid = this.binding.tvTokenid;
        this.llSendAddress = this.binding.llSendAddress;
        this.ivRa = this.binding.ivRa;
        this.llTopOne = this.binding.llTopOne;
        this.tvChainName = this.binding.tvChainName;
        this.llChainName = this.binding.llChainName;
        this.ivBa = this.binding.ivBa;
        this.llTopThree = this.binding.llTopThree;
        this.tvAmountLine = this.binding.tvAmountLine;
        this.tvBalance = this.binding.tvBalance;
        this.etCount = this.binding.etCount;
        this.tvMax = this.binding.tvMax;
        this.tvLineV = this.binding.tvLineV;
        this.eetAmout = this.binding.eetAmout;
        this.llBalanceAmout = this.binding.llBalanceAmout;
        this.btGo = this.binding.btGo;
        this.tvAmountName = this.binding.tvAmountName;
        this.tvDepositType = this.binding.tvDepositType;
        this.tvTypeChain = this.binding.tvTypeChain;
        this.tvTypeAmount = this.binding.tvTypeAmount;
        this.tvContent = this.binding.tvContent;
    }

    @Override
    protected void processData() {
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        this.mWallet = selectedWallet;
        this.mBalance = WalletUtils.getAccount(this, selectedWallet.getWalletName()).getBalance() / 1000000.0d;
        this.currentChain = SpAPI.THIS.getCurrentChain();
        this.allChainJson = SpAPI.THIS.getAllChainJson();
        addManager();
        List<ChainBean> list = this.allChainJson;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < this.allChainJson.size(); i++) {
                if (this.allChainJson.get(i).isMainChain) {
                    this.mainChain = this.allChainJson.get(i);
                } else {
                    this.sideChain = this.allChainJson.get(i);
                }
            }
        }
        this.mToken = (TokenBean) getIntent().getParcelableExtra(TOKEN_DATA);
        toType();
        toUpdateBalance();
        addWatch();
        getDappToMainFee();
        runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$processData$0();
            }
        });
    }

    public void lambda$processData$0() {
        try {
            this.accountPer = TronAPI.queryAccount(StringTronUtil.decodeFromBase58Check(this.mWallet.getAddress()), false);
        } catch (ConnectErrorException e) {
            LogUtils.e(e);
        }
    }

    private void getDappToMainFee() {
        ((DepositPresenter) this.mPresenter).getDappToMainFee();
    }

    private void addManager() {
        RxManager rxManager = new RxManager();
        this.rxManager = rxManager;
        rxManager.on(Event.BroadcastSuccess, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$addManager$1(obj);
            }
        });
    }

    public void lambda$addManager$1(Object obj) throws Exception {
        finish();
    }

    private void addWatch() {
        this.etCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (M.M_TRX.equals(isTrx)) {
                    showEngryFee(editable.toString());
                }
                eetAmout.hideError3();
                if (TextUtils.isEmpty(editable)) {
                    btGo.setEnabled(false);
                } else {
                    btGo.setEnabled(true);
                }
            }
        });
    }

    private void toType() {
        TokenBean tokenBean = this.mToken;
        if (tokenBean != null) {
            int type = tokenBean.getType();
            if (type == 0) {
                this.isTrx = M.M_TRX;
                this.addressType = "TRX";
            } else if (type == 1) {
                this.isTrx = M.M_TRC10;
                this.addressType = "";
                this.tvDepositType.setText(String.format(getString(R.string.token_id_of_trc10), this.addressType));
            } else if (type == 2) {
                this.isTrx = M.M_TRC20;
                this.addressType = "";
                this.tvDepositType.setText(String.format(getString(R.string.contract_address_of_type), this.addressType));
            }
        }
        if (this.currentChain.isMainChain) {
            setHeaderBarAndRightImage(getString(R.string.deposit), R.mipmap.note_black);
            this.tvTypeChain.setText(getString(R.string.deposit_to));
            this.tvTypeAmount.setText(getString(R.string.amount_of_deposit));
            this.btGo.setText(R.string.deposit);
            return;
        }
        setHeaderBarAndRightImage(getString(R.string.withdraw), R.mipmap.note_black);
        this.tvTypeChain.setText(R.string.withdraw_to);
        this.tvTypeAmount.setText(getString(R.string.amount_of_withdraw));
        this.btGo.setText(R.string.withdraw);
    }

    private void toUpdateBalance() {
        String format;
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.numberFormat = numberInstance;
        numberInstance.setGroupingUsed(false);
        TokenBean tokenBean = this.mToken;
        if (tokenBean != null && tokenBean.getPrecision() != 0) {
            this.numberFormat.setMaximumFractionDigits(this.mToken.getPrecision());
        } else {
            this.numberFormat.setMaximumFractionDigits(6);
        }
        if (M.M_TRC10.equals(this.isTrx) || M.M_TRC20.equals(this.isTrx)) {
            if (this.mToken.precision == 0) {
                this.etCount.setInputType(2);
            } else {
                this.etCount.setInputType(8194);
                this.etCount.setDOTValue(this.mToken.precision);
            }
        } else {
            this.etCount.setInputType(8194);
            this.etCount.setDOTValue(6);
        }
        if (M.M_TRX.equals(this.isTrx)) {
            this.llTopTr.setVisibility(View.GONE);
            this.llSendAddress.setVisibility(View.GONE);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.llTopOne.getLayoutParams();
            layoutParams.setMargins(DensityUtils.dp2px(this, 23.0f), DensityUtils.dp2px(this, 51.0f), 0, 0);
            this.llTopOne.setLayoutParams(layoutParams);
            this.balance = this.mToken.getBalanceBigDecimal();
            this.tvName.setText("TRX");
            this.tvAmountName.setText("TRX");
            this.name = "TRX";
        } else if (M.M_TRC10.equals(this.isTrx)) {
            this.pow = Math.pow(10.0d, this.mToken.getPrecision());
            this.balance = this.mToken.getBalanceBigDecimal();
            this.tvName.setText(this.mToken.getShortName().equals("") ? this.mToken.getName() : this.mToken.getShortName());
            String name = this.mToken.getShortName().equals("") ? this.mToken.getName() : this.mToken.getShortName();
            this.name = name;
            this.tvAmountName.setText(name);
            this.tvTokenid.setText("" + this.mToken.getId());
        } else {
            this.balance = this.mToken.getBalanceBigDecimal();
            this.tvName.setText(StringTronUtil.isEmpty(this.mToken.getShortName()) ? this.mToken.name : this.mToken.getShortName());
            String shortName = StringTronUtil.isEmpty(this.mToken.getShortName()) ? this.mToken.name : this.mToken.getShortName();
            this.name = shortName;
            this.tvAmountName.setText(shortName);
            this.tvTokenid.setText(this.mToken.contractAddress);
        }
        String string = getString(R.string.amount_of_deposit2);
        ChainBean chainBean = this.currentChain;
        if (chainBean == null || chainBean.isMainChain) {
            format = String.format(string, this.numberFormat.format(this.balance), this.name, getString(R.string.deposit3));
            ChainBean chainBean2 = this.sideChain;
            if (chainBean2 != null) {
                this.tvChainName.setText(chainBean2.chainName);
            }
        } else {
            ChainBean chainBean3 = this.mainChain;
            if (chainBean3 != null) {
                this.tvChainName.setText(chainBean3.chainName);
            }
            if (M.M_TRX.equals(this.isTrx)) {
                double doubleValue = this.balance.doubleValue() - this.mDappToMainFee;
                if (doubleValue < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                    doubleValue = 0.0d;
                }
                format = String.format(string, this.numberFormat.format(doubleValue), this.name, getString(R.string.withdraw2));
            } else {
                format = String.format(string, this.numberFormat.format(this.balance), this.name, getString(R.string.withdraw2));
            }
        }
        this.tvBalance.setText(format);
    }

    @Override
    public void onRightButtonClick() {
        super.onRightButtonClick();
        showPop();
    }

    private void showPop() {
        View inflate = getLayoutInflater().inflate(R.layout.deposit_pop, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_content);
        if (this.currentChain.isMainChain) {
            textView.setText(R.string.dialog_deposit);
        } else {
            textView.setText(R.string.dialog_withdraw);
        }
        if (this.popupWindow == null) {
            PopupWindow popupWindow = new PopupWindow(inflate, -2, -2, true);
            this.popupWindow = popupWindow;
            popupWindow.setOutsideTouchable(true);
        }
        if (this.popupWindow.isShowing()) {
            this.popupWindow.dismiss();
            return;
        }
        this.popupWindow.getContentView().measure(0, 0);
        this.popupWindow.showAsDropDown(this.ivQr, this.ivQr.getWidth() - this.popupWindow.getContentView().getMeasuredWidth(), 20);
    }

    public void goWithdraw() {
        this.btGo.setText(R.string.sending);
        this.btGo.setEnabled(false);
        runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$goWithdraw$3();
            }
        });
    }

    public void lambda$goWithdraw$3() {
        byte[] decodeFromBase58Check = StringTronUtil.decodeFromBase58Check(this.mWallet.getAddress());
        try {
            connetFullNode(SpAPI.THIS.getMainNodeList().get("MainChain").fullNode.get(0));
            this.account = queryAccount(decodeFromBase58Check, false);
        } catch (ConnectErrorException e) {
            LogUtils.e(e);
        }
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$goWithdraw$2();
            }
        });
    }

    public void lambda$goWithdraw$2() {
        Protocol.Account account = this.account;
        if (account == null || account.toString().length() == 0) {
            IToast.getIToast().showLong(getString(R.string.no_active));
            this.btGo.setText(R.string.withdraw);
            this.btGo.setEnabled(true);
            return;
        }
        goWithdrawTwo();
    }

    private long getFeeLimit() {
        return TronAPI.getFeeLimit();
    }

    private void goWithdrawTwo() {
        this.bigCount = this.bigZero;
        this.go = true;
        try {
            this.bigCount = new BigDecimal(StringTronUtil.getText(this.etCount));
            T.amount = StringTronUtil.getText(this.etCount);
            if (M.M_TRX.equals(this.isTrx)) {
                long j = this.mDappToMainFee;
                if (j != 0) {
                    this.bigCount = BigDecimalUtils.sum_(this.bigCount, Double.valueOf(BigDecimalUtils.div(Long.valueOf(j), Double.valueOf(1000000.0d))));
                }
            }
            if (BigDecimalUtils.MoreThan(this.bigCount, this.balance)) {
                if (M.M_TRX.equals(this.isTrx)) {
                    this.eetAmout.setTextError3(R.string.error_price);
                    this.eetAmout.showError3();
                    this.go = false;
                } else {
                    this.eetAmout.setTextError3(R.string.error_price);
                    this.eetAmout.showError3();
                    this.go = false;
                }
            } else if (BigDecimalUtils.LessThan(this.bigCount, this.bigZero)) {
                if (M.M_TRX.equals(this.isTrx)) {
                    this.eetAmout.setTextError3(getString(R.string.no_less));
                } else {
                    this.eetAmout.setTextError3(String.format(getString(R.string.tansfer3), getResources().getString(R.string.withdraw2)));
                }
                this.eetAmout.showError3();
                this.go = false;
            } else if (M.M_TRX.equals(this.isTrx) && BigDecimalUtils.LessThan((Object) this.bigCount, (Object) 10)) {
                this.eetAmout.setTextError3(getString(R.string.no_less));
                this.eetAmout.showError3();
                this.go = false;
            }
            if (!this.go) {
                this.btGo.setText(R.string.withdraw);
                this.btGo.setEnabled(true);
                return;
            }
            runOnThreeThread(new OnBackground() {
                @Override
                public final void doOnBackground() {
                    lambda$goWithdrawTwo$5();
                }
            });
        } catch (Exception unused) {
            this.eetAmout.setTextError3(R.string.input_error);
            this.eetAmout.showError3();
        }
    }

    public void lambda$goWithdrawTwo$5() {
        final Protocol.Transaction transaction = null;
        try {
            if (M.M_TRX.equals(this.isTrx)) {
                if (this.sideChain != null) {
                    GrpcAPI.TransactionExtention withdrawTrx = TriggerUtils.withdrawTrx(StringTronUtil.decodeFromBase58Check(this.mWallet.getAddress()), this.sideChain.getSideChainContract(), BigDecimalUtils.mul_(this.bigCount, Double.valueOf(1000000.0d)).longValue(), getFeeLimit());
                    if (withdrawTrx.hasResult()) {
                        transaction = withdrawTrx.getTransaction();
                    } else {
                        Toast(R.string.create_transaction_fail);
                    }
                } else {
                    Toast(R.string.create_transaction_fail);
                }
            } else if (M.M_TRC10.equals(this.isTrx)) {
                if (this.sideChain != null) {
                    GrpcAPI.TransactionExtention withdrawTrc10 = TriggerUtils.withdrawTrc10(StringTronUtil.decodeFromBase58Check(this.mWallet.getAddress()), this.sideChain.getSideChainContract(), this.mToken.getId(), BigDecimalUtils.mul_(this.bigCount, Double.valueOf(this.pow)).longValue(), this.mDappToMainFee, getFeeLimit());
                    if (withdrawTrc10.hasResult()) {
                        transaction = withdrawTrc10.getTransaction();
                    } else {
                        Toast(R.string.create_transaction_fail);
                    }
                } else {
                    Toast(R.string.create_transaction_fail);
                }
            } else if (M.M_TRC20.equals(this.isTrx)) {
                GrpcAPI.TransactionExtention withdrawTrc20 = TriggerUtils.withdrawTrc20(StringTronUtil.decodeFromBase58Check(this.mWallet.getAddress()), this.mToken.getContractAddress(), BigDecimalUtils.mul_(this.bigCount, Double.valueOf(Math.pow(10.0d, this.mToken.getPrecision()))).setScale(0, 4).toString(), this.mDappToMainFee, getFeeLimit());
                if (withdrawTrc20.hasResult()) {
                    transaction = withdrawTrc20.getTransaction();
                } else {
                    Toast(R.string.create_transaction_fail);
                }
            }
        } catch (Exception unused) {
        }
        if (transaction != null && WalletUtils.getSelectedWallet().isWatchOnly()) {
            transaction = setTime(3, transaction);
        }
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$goWithdrawTwo$4(transaction);
            }
        });
    }

    public void lambda$goWithdrawTwo$4(Protocol.Transaction transaction) {
        String str;
        this.btGo.setEnabled(true);
        this.btGo.setText(R.string.withdraw);
        if (transaction != null) {
            try {
                Protocol.Account account = this.accountPer;
                boolean checkHaveOwnerPermissions = (account == null || account.toString().length() <= 0) ? true : WalletUtils.checkHaveOwnerPermissions(this.mWallet.getAddress(), this.accountPer.getOwnerPermission());
                if (M.M_TRX.equals(this.isTrx)) {
                    str = this.numberFormat.format(BigDecimalUtils.sub_(this.bigCount, Double.valueOf(BigDecimalUtils.div(Long.valueOf(this.mDappToMainFee), Double.valueOf(1000000.0d))))) + "\t" + this.mToken.name;
                } else if (this.mToken.shortName != null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(this.numberFormat.format(this.bigCount));
                    sb.append("\t");
                    sb.append(this.mToken.shortName.equals("") ? this.mToken.name : this.mToken.shortName);
                    str = sb.toString();
                } else {
                    str = this.numberFormat.format(Double.valueOf(T.amount)) + "\t" + this.mToken.name;
                }
                long bandwidthCost = TransactionUtils.bandwidthCost(transaction);
                Bundle bundle = new Bundle();
                bundle.putString(NotificationCompat.CATEGORY_EVENT, this.mToken.name);
                bundle.putString("value", str);
                this.mFirebaseAnalytics.logEvent("Sidechain_Withdraw", bundle);
                ConfirmTransactionNewActivity.startActivity(this, ParamBuildUtils.getWithDrawTransactionParamBuilder(checkHaveOwnerPermissions, this.isTrx, str, bandwidthCost, 8, this.mToken, transaction), this.mWallet.isSamsungWallet());
                return;
            } catch (Exception e) {
                LogUtils.e(e);
                SentryUtil.captureException(e);
                return;
            }
        }
        Toast(R.string.create_transaction_fail);
    }

    public void goDeposit() {
        this.bigCount = this.bigZero;
        this.go = true;
        try {
            this.bigCount = new BigDecimal(StringTronUtil.getText(this.etCount));
            T.amount = StringTronUtil.getText(this.etCount);
            if (BigDecimalUtils.Equal(this.bigCount, this.bigZero) && !M.M_TRX.equals(this.isTrx)) {
                this.eetAmout.setTextError3(String.format(getString(R.string.tansfer3), getResources().getString(R.string.deposit3)));
                this.eetAmout.showError3();
                return;
            }
            if (BigDecimalUtils.MoreThan(this.bigCount, this.balance)) {
                this.eetAmout.setTextError3(R.string.error_price);
                this.eetAmout.showError3();
                this.go = false;
            } else if (BigDecimalUtils.LessThan(this.bigCount, this.bigZero)) {
                if (M.M_TRX.equals(this.isTrx)) {
                    this.eetAmout.setTextError3(getString(R.string.no_less_10));
                } else {
                    this.eetAmout.setTextError3(String.format(getString(R.string.tansfer3), getResources().getString(R.string.deposit3)));
                }
                this.eetAmout.showError3();
                this.go = false;
            } else if (M.M_TRX.equals(this.isTrx) && BigDecimalUtils.LessThan((Object) this.bigCount, (Object) 10)) {
                this.eetAmout.setTextError3(getString(R.string.no_less_10));
                this.eetAmout.showError3();
                this.go = false;
            }
            if (this.go) {
                this.btGo.setEnabled(false);
                this.btGo.setText(R.string.sending);
                runOnThreeThread(new OnBackground() {
                    @Override
                    public final void doOnBackground() {
                        lambda$goDeposit$7();
                    }
                });
            }
        } catch (Exception unused) {
            this.eetAmout.setTextError3(getString(R.string.input_error));
            this.eetAmout.showError3();
        }
    }

    public void lambda$goDeposit$7() {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.transfer.deposit.DepositActivity.lambda$goDeposit$7():void");
    }

    public void lambda$goDeposit$6(org.tron.protos.Protocol.Transaction r14, org.tron.protos.Protocol.Transaction r15) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.transfer.deposit.DepositActivity.lambda$goDeposit$6(org.tron.protos.Protocol$Transaction, org.tron.protos.Protocol$Transaction):void");
    }

    private Protocol.Transaction setTime(int i, Protocol.Transaction transaction) {
        Protocol.Transaction.Builder builder = transaction.toBuilder();
        Protocol.Transaction.raw.Builder builder2 = transaction.getRawData().toBuilder();
        builder2.setExpiration(System.currentTimeMillis() + (i * 60000));
        builder.setRawData(builder2.build());
        return builder.build();
    }

    @Override
    public void onDestroy() {
        this.rxManager.clear();
        super.onDestroy();
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }

    public void connetSolidityNode(String str) {
        try {
            if (StringUtils.isEmpty(str)) {
                return;
            }
            ManagedChannel build = ManagedChannelBuilder.forTarget(str).usePlaintext().build();
            this.channelSolidity = build;
            this.blockingStubSolidity = WalletSolidityGrpc.newBlockingStub(build);
            this.blockingStubExtension = WalletExtensionGrpc.newBlockingStub(this.channelSolidity);
        } catch (Exception e) {
            try {
                Wallet selectedWallet = WalletUtils.getSelectedWallet();
                if (selectedWallet != null) {
                    String str2 = selectedWallet.address;
                    FailUtils.sendFail("SolidityNode connect exception", "transaction is null", str2, e.toString() + " ");
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public Protocol.Account queryAccount(byte[] bArr, boolean z) throws ConnectErrorException {
        try {
            Protocol.Account build = Protocol.Account.newBuilder().setAddress(ByteString.copyFrom(bArr)).build();
            WalletSolidityGrpc.WalletSolidityBlockingStub walletSolidityBlockingStub = this.blockingStubSolidity;
            if (walletSolidityBlockingStub != null && z) {
                return walletSolidityBlockingStub.getAccount(build);
            }
            return this.blockingStubFull.getAccount(build);
        } catch (Exception unused) {
            if (!z) {
                throw new ConnectErrorException("FullNode-queryAccount  error ");
            }
            throw new ConnectErrorException("SolodityNode-queryAccount  error ");
        }
    }

    public void connetFullNode(String str) {
        try {
            if (StringUtils.isEmpty(str)) {
                return;
            }
            ManagedChannel build = ManagedChannelBuilder.forTarget(str).usePlaintext().build();
            this.channelFull = build;
            this.blockingStubFull = WalletGrpc.newBlockingStub(build);
        } catch (Exception e) {
            try {
                Wallet selectedWallet = WalletUtils.getSelectedWallet();
                if (selectedWallet != null) {
                    String str2 = selectedWallet.address;
                    FailUtils.sendFail("FullNode connect exception", "transaction is null", str2, e.toString() + " ");
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override
    public void setDappToMainFee(long j) {
        this.mDappToMainFee = j;
        this.tvContent.setText(R.string.fee_10);
        if (this.mDappToMainFee > 0) {
            this.tvContent.setVisibility(View.VISIBLE);
        } else {
            this.tvContent.setVisibility(View.GONE);
        }
    }

    public void showEngryFee(String str) {
        if (this.currentChain.isMainChain) {
            this.tvContent.setText(R.string.withdraw_lest_1TRX2);
        } else {
            this.tvContent.setText(R.string.withdraw_lest_1TRX);
        }
        if ("".equals(str) || " ".equals(str)) {
            return;
        }
        if (!StringTronUtil.isEmpty(str) && BigDecimalUtils.sub_(this.balance, str).doubleValue() < 1.0d && BigDecimalUtils.lessThanOrEqual(str, this.balance)) {
            this.tvContent.setVisibility(View.VISIBLE);
        } else {
            this.tvContent.setVisibility(View.GONE);
        }
    }
}
