package com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.protobuf.InvalidProtocolBufferException;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.fg.component.DappConfirmButton;
import com.tron.wallet.business.confirm.parambuilder.bean.DappDetailParam;
import com.tron.wallet.business.confirm.parambuilder.bean.DappMetadataParam;
import com.tron.wallet.business.confirm.parambuilder.utils.ParamBuildUtils;
import com.tron.wallet.business.confirm.sign.SignTransactionNewActivity;
import com.tron.wallet.business.samsung.SamsungSDKWrapper;
import com.tron.wallet.business.tabdapp.bean.DappConfirmInput;
import com.tron.wallet.business.tabdapp.bean.DappProjectInfoBean;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.component.ConfirmModel;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.DappConfirmNewActivity;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.DappConfirmNewContract;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.approve.DappApproveConfirmFragment;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.transfer.DappTransferConfirmFragment;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.triggersmartcontract.DappTriggerSmartContractConfirmFragment;
import com.tron.wallet.common.bean.ChainBean;
import com.tron.wallet.common.bean.QrBean;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.FailUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.PasswordInputUtils;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.transaction.TriggerUtils;
import com.tron.wallet.databinding.PopDappNewBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.disposables.Disposable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.bouncycastle.util.encoders.Hex;
import org.tron.common.utils.ByteArray;
import org.tron.common.utils.JsonFormat;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
import org.tron.protos.contract.SmartContractOuterClass;
import org.tron.walletserver.TriggerData;
import org.tron.walletserver.Wallet;
public class DappConfirmNewActivity extends BaseActivity<DappConfirmNewPresenter, DappConfirmNewModel> implements DappConfirmNewContract.View {
    public static final String ABI = "ABI";
    public static final String CANCLE_KEY = "cancle";
    public static final String CODE_KEY = "code";
    public static final String CURRENTMODEL_KEY = "currentmodel";
    public static final String FUNCTION_SELECTOR = "FUNCTION_SELECTOR";
    public static final String HASPASSWORD_KEY = "hasPassword";
    public static final String ICON_KEY = "icon";
    public static final String PASSWORD_KEY = "password";
    public static final String SELECTEDMODEL_KEY = "selectedModel";
    public static final String SITE_KEY = "site";
    public static final String TITLE_KEY = "title";
    public static final String TRANSACTIONBYTE_KEY = "transactionByte";
    public static final String TRANSACTIONSTRING_KEY = "transactionString";
    public static final String WALLETNAME_KEY = "walletName";
    private String abiString;
    private String approveAmount;
    ConstraintLayout approveTipsLayout;
    private PopDappNewBinding binding;
    DappConfirmButton btnConfirm;
    Button cancle;
    Fragment contentFragment;
    private ConfirmModel currentModel;
    private String function_selector;
    Button go;
    private boolean hasPassword;
    private String icon;
    Intent intent;
    private boolean isApprove;
    private boolean isApproveDetail;
    View llConfirmLayout;
    LinearLayout llSelected;
    private NumberFormat numberFormat;
    private String password;
    Button quickBt;
    TextView quickText1;
    TextView quickText2;
    RelativeLayout rootview;
    Button safeBt;
    TextView safeText1;
    TextView safeText2;
    private boolean selectedModel;
    private String sign;
    private String signClear;
    private String site;
    private String title;
    private String toAddress;
    private String tokenName;
    private Protocol.Transaction transaction;
    private TriggerData triggerData;
    private Wallet wallet;
    Button whiteBt;
    TextView whiteText1;
    TextView whiteText2;
    private int whiteCode = -2;
    private int ledgerSignRequestCode = 1238;
    private int generalSignRequestCode = 1237;
    private DappDetailParam dappDetailParam = null;
    private DappMetadataParam dappMetadataParam = null;

    public DappConfirmButton getConfirmBtn() {
        return this.btnConfirm;
    }

    public void setEditApproveAmountParam(String str, String str2) {
        this.toAddress = str;
        this.approveAmount = str2;
    }

    @Override
    public void onCreate1(Bundle bundle) {
        super.onCreate1(bundle);
        if (Build.VERSION.SDK_INT == 26 || Build.VERSION.SDK_INT == 27) {
            setRequestedOrientation(2);
        } else {
            setRequestedOrientation(1);
        }
    }

    @Override
    protected void setLayout() {
        PopDappNewBinding inflate = PopDappNewBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 0);
        mappingId();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.safeBt = this.binding.safeBt;
        this.safeText1 = this.binding.safeText1;
        this.safeText2 = this.binding.safeText2;
        this.quickBt = this.binding.quickBt;
        this.quickText1 = this.binding.quickText1;
        this.quickText2 = this.binding.quickText2;
        this.whiteBt = this.binding.whiteBt;
        this.whiteText1 = this.binding.whiteText1;
        this.whiteText2 = this.binding.whiteText2;
        this.cancle = this.binding.cancle;
        this.go = this.binding.go;
        this.llSelected = this.binding.llSelected;
        this.btnConfirm = this.binding.ok;
        this.llConfirmLayout = this.binding.llConfirmLayout;
        this.approveTipsLayout = this.binding.llApproveTips;
        this.rootview = this.binding.rootview;
    }

    @Override
    protected void processData() {
        Intent iItent = getIItent();
        this.title = iItent.getStringExtra("title");
        this.site = iItent.getStringExtra("site");
        this.icon = iItent.getStringExtra("icon");
        this.password = iItent.getStringExtra("password");
        this.selectedModel = iItent.getBooleanExtra("selectedModel", false);
        this.hasPassword = iItent.getBooleanExtra("hasPassword", false);
        this.whiteCode = iItent.getIntExtra("code", -2);
        this.function_selector = iItent.getStringExtra("FUNCTION_SELECTOR");
        this.abiString = iItent.getStringExtra("ABI");
        try {
            this.currentModel = (ConfirmModel) iItent.getSerializableExtra("currentmodel");
            this.transaction = Protocol.Transaction.parseFrom(iItent.getByteArrayExtra("transactionByte"));
        } catch (Exception e) {
            SentryUtil.captureException(e);
            LogUtils.e(e);
        }
        Wallet wallet = WalletUtils.getWallet(iItent.getStringExtra("walletName"));
        this.wallet = wallet;
        if (wallet == null) {
            return;
        }
        if (wallet.getCreateType() == 7) {
            byte[] byteArrayExtra = iItent.getByteArrayExtra("transactionByte");
            try {
                WalletUtils.printTransaction(Protocol.Transaction.parseFrom(byteArrayExtra));
            } catch (InvalidProtocolBufferException e2) {
                LogUtils.e(e2);
            }
            if (StringTronUtil.isEmpty(SamsungSDKWrapper.checkSeedHashEmpty(this, false))) {
                FailUtils.showSamsungKeystoreUnAvailableDialog(this, 1);
                return;
            }
            if (!SamsungSDKWrapper.checkSeedHashEmpty(this, false).equals(SpAPI.THIS.getSamsung_SEED_HASH())) {
                FailUtils.showSamsungKeystoreUnAvailableDialog(this, 2);
                return;
            } else {
                SamsungSDKWrapper.startSign(this, this.wallet.getAddress(), byteArrayExtra, new fun1());
                return;
            }
        }
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.numberFormat = numberInstance;
        numberInstance.setMaximumFractionDigits(7);
        this.tokenName = "\tTRX";
        initData(this.title, this.icon);
        showLoadingDialog(false, false);
        runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$processData$1();
            }
        });
        this.llSelected.setClickable(false);
    }

    public class fun1 implements SamsungSDKWrapper.SignCallBack {
        fun1() {
        }

        @Override
        public void onSignSuccess(byte[] bArr) {
            try {
                sign = WalletUtils.printTransaction(Protocol.Transaction.parseFrom(bArr));
                intent = new Intent();
                intent.putExtra("hasPassword", false);
                intent.putExtra("password", "");
                intent.putExtra("selectedModel", true);
                intent.putExtra("currentmodel", ConfirmModel.SAFE);
                intent.putExtra("transactionString", sign);
                DappConfirmNewActivity dappConfirmNewActivity = DappConfirmNewActivity.this;
                dappConfirmNewActivity.setResult(-1, dappConfirmNewActivity.intent);
                finish();
            } catch (InvalidProtocolBufferException e) {
                LogUtils.e(e);
            }
        }

        @Override
        public void onFailure(String str) {
            LogUtils.d("Samsung-Keystore", "StartSign failure: startVerifySeed error:" + str);
            if (str != null) {
                runOnUIThread(new OnMainThread() {
                    @Override
                    public final void doInUIThread() {
                        DappConfirmNewActivity.1.this.lambda$onFailure$0();
                    }
                });
            }
            cancle();
        }

        public void lambda$onFailure$0() {
            DappConfirmNewActivity dappConfirmNewActivity = DappConfirmNewActivity.this;
            Toast.makeText(dappConfirmNewActivity, dappConfirmNewActivity.getString(R.string.sign_fail), Toast.LENGTH_LONG).show();
        }
    }

    public static class fun4 {
        static final int[] $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType;

        static {
            int[] iArr = new int[Protocol.Transaction.Contract.ContractType.values().length];
            $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType = iArr;
            try {
                iArr[Protocol.Transaction.Contract.ContractType.TriggerSmartContract.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.TransferContract.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.TransferAssetContract.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public void lambda$processData$1() {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.DappConfirmNewActivity.lambda$processData$1():void");
    }

    public void lambda$processData$0() {
        dismissLoadingDialog();
        showEnterPassword();
    }

    private void triggerContent(String str, TokenBean tokenBean, String str2, String str3, SmartContractOuterClass.TriggerSmartContract triggerSmartContract, int i) {
        String format = this.numberFormat.format(triggerSmartContract.getCallValue() / 1000000.0d);
        String encode58Check = StringTronUtil.encode58Check(triggerSmartContract.getOwnerAddress().toByteArray());
        String str4 = new String(this.transaction.getRawData().getData().toByteArray());
        int number = this.transaction.getRawData().getContract(0).getType().getNumber();
        String str5 = TriggerUtils.isTransferFrom(triggerSmartContract) ? this.triggerData.getParameterMap().get("1") : encode58Check;
        DappDetailParam dappDetailParam = (DappDetailParam) ParamBuildUtils.getDappDetailParamBuilder(this.transaction, encode58Check, str5, format, tokenBean, str, str4, null, number, i, this.triggerData, str2, triggerSmartContract, -1).get();
        this.dappDetailParam = dappDetailParam;
        if (i == 0 || i == 2) {
            dappDetailParam.setTransfer(true);
        }
        DappMetadataParam dappMetadataParam = (DappMetadataParam) ParamBuildUtils.getDappMetadataParamBuilder(this.transaction, format, encode58Check, str5, str, this.triggerData).get();
        this.dappMetadataParam = dappMetadataParam;
        buildFragmentFromTokenType(i, this.dappDetailParam, dappMetadataParam);
    }

    private void buildFragmentFromTokenType(int i, DappDetailParam dappDetailParam, DappMetadataParam dappMetadataParam) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3 && i != 4 && i != 990 && i != 993) {
                        this.contentFragment = DappTriggerSmartContractConfirmFragment.getInstance(dappDetailParam, dappMetadataParam);
                        return;
                    }
                }
            }
            this.isApprove = true;
            dappDetailParam.setApprove(true);
            return;
        }
        this.contentFragment = DappTransferConfirmFragment.getInstance(dappDetailParam, dappMetadataParam);
    }

    public static void start(Activity activity, String str, String str2, Protocol.Transaction transaction, boolean z, Wallet wallet, ConfirmModel confirmModel, boolean z2, String str3, String str4, int i, String str5, String str6) {
        Intent intent = new Intent(activity, DappConfirmNewActivity.class);
        intent.putExtra("title", str);
        intent.putExtra("site", str2);
        intent.putExtra("transactionByte", transaction.toByteArray());
        intent.putExtra("selectedModel", z);
        intent.putExtra("walletName", wallet.getWalletName());
        intent.putExtra("currentmodel", confirmModel);
        intent.putExtra("hasPassword", z2);
        intent.putExtra("password", str4);
        intent.putExtra("icon", str3);
        intent.putExtra("code", i);
        intent.putExtra("FUNCTION_SELECTOR", str5);
        intent.putExtra("ABI", str6);
        activity.startActivityForResult(intent, TronConfig.TODAPPCONFIRM);
        activity.overridePendingTransition(R.anim.activity_open, 0);
    }

    public void initData(String str, String str2) {
        if (StringTronUtil.isEmpty(str)) {
            str = getString(R.string.dapp_action);
        }
        this.safeText1.setText(getString(R.string.dapp4, new Object[]{str}));
        this.quickText1.setText(getString(R.string.dapp13));
        this.whiteText1.setText(getString(R.string.dapp16));
        if (StringTronUtil.isEmpty(str2)) {
            return;
        }
        str2.endsWith("svg");
    }

    private String handleTitle(String str) {
        if (StringTronUtil.isNullOrEmpty(str)) {
            if (this.site.length() > 30) {
                return this.site.substring(0, 30) + "...";
            }
            return this.site;
        }
        return str;
    }

    private void obToSignTransaction() {
        try {
            ArrayList arrayList = new ArrayList();
            arrayList.add(ByteArray.toHexString(this.transaction.toByteArray()));
            QrBean qrBean = new QrBean();
            qrBean.setHexList(arrayList);
            qrBean.setType(14);
            qrBean.setAddress(this.wallet.getAddress());
            SignTransactionNewActivity.start(this, qrBean, new TokenBean(), TronConfig.OB_W);
        } catch (Exception unused) {
            cancle();
        }
    }

    private byte[] changeTime(byte[] bArr) {
        try {
            Protocol.Transaction parseFrom = Protocol.Transaction.parseFrom(bArr);
            Protocol.Transaction.Builder builder = parseFrom.toBuilder();
            Protocol.Transaction.raw.Builder builder2 = parseFrom.getRawData().toBuilder();
            builder2.setContract(0, parseFrom.getRawData().getContract(0).toBuilder().build());
            builder2.setExpiration(System.currentTimeMillis() + 300000);
            builder.setRawData(builder2.build());
            return builder.build().toByteArray();
        } catch (InvalidProtocolBufferException e) {
            LogUtils.e(e);
            return bArr;
        }
    }

    private void rewardSignTransaction() {
        runOnUiThread(new Runnable() {
            @Override
            public final void run() {
                lambda$rewardSignTransaction$2();
            }
        });
    }

    public void lambda$rewardSignTransaction$2() {
        if (StringTronUtil.isEmpty(this.sign)) {
            return;
        }
        String printTransaction = WalletUtils.printTransaction(this.transaction);
        this.signClear = printTransaction;
        addDappRecord(printTransaction, this.title, this.site);
        if (this.currentModel == ConfirmModel.QUICK) {
            this.hasPassword = true;
        } else if (this.currentModel == ConfirmModel.SAFE) {
            this.password = null;
            this.hasPassword = false;
        } else {
            this.hasPassword = false;
        }
        this.btnConfirm.setEnabled(true);
        PasswordInputUtils.updateSuccessPwd(this.mContext, this.wallet.getWalletName(), 8);
        Intent intent = new Intent();
        this.intent = intent;
        intent.putExtra("hasPassword", this.hasPassword);
        this.intent.putExtra("password", this.password);
        this.intent.putExtra("selectedModel", this.selectedModel);
        this.intent.putExtra("currentmodel", this.currentModel);
        this.intent.putExtra("transactionString", this.sign);
        setResult(-1, this.intent);
        finish();
    }

    public void addDappRecord(String str, String str2, String str3) {
        try {
            DappConfirmInput dappConfirmInput = new DappConfirmInput();
            dappConfirmInput.transactionString = str;
            dappConfirmInput.dappName = str2;
            dappConfirmInput.dappUrl = str3;
            ((DappConfirmNewModel) this.mModel).addDappRecord(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(dappConfirmInput))).subscribe(new IObserver(new ICallback<Object>() {
                @Override
                public void onFailure(String str4, String str5) {
                }

                @Override
                public void onResponse(String str4, Object obj) {
                }

                @Override
                public void onSubscribe(Disposable disposable) {
                }
            }, "addDappRecord"));
        } catch (Exception unused) {
        }
    }

    private String getSign(Protocol.Transaction transaction, String str) throws Exception {
        JSONObject parseObject = JSONObject.parseObject(JsonFormat.printToString(transaction));
        Protocol.Transaction.Builder newBuilder = Protocol.Transaction.newBuilder();
        JsonFormat.merge(parseObject.toJSONString(), newBuilder);
        Wallet wallet = WalletUtils.getWallet(this.wallet.getWalletName(), str);
        Protocol.Transaction timestamp = TransactionUtils.setTimestamp(newBuilder.build());
        ChainBean currentChain = SpAPI.THIS.getCurrentChain();
        return WalletUtils.printTransaction(TransactionUtils.sign(timestamp, wallet.getECKey(), currentChain == null ? null : ByteArray.fromHexString(currentChain.chainId), currentChain == null || currentChain.isMainChain));
    }

    private void setV(boolean z, View... viewArr) {
        if (viewArr == null || viewArr.length == 0) {
            return;
        }
        for (View view : viewArr) {
            if (z) {
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.GONE);
            }
        }
    }

    public void showEnterPassword() {
        if (this.contentFragment != null) {
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.replace(R.id.confirm_content, this.contentFragment);
            beginTransaction.disallowAddToBackStack();
            beginTransaction.commitAllowingStateLoss();
        }
        setV(false, this.llSelected);
        setV(true, this.llConfirmLayout);
        this.btnConfirm.postDelayed(new Runnable() {
            @Override
            public final void run() {
                lambda$showEnterPassword$3();
            }
        }, 200L);
    }

    public void lambda$showEnterPassword$3() {
        this.approveTipsLayout.setVisibility(View.VISIBLE);
        this.btnConfirm.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateApproveConfirmFragment(DappProjectInfoBean dappProjectInfoBean, boolean z, TokenBean tokenBean) {
        this.dappDetailParam.setTokenBean(tokenBean);
        this.dappDetailParam.setDappProjectInfoBean(dappProjectInfoBean);
        this.dappDetailParam.setAccount(z);
        if (dappProjectInfoBean.isWhite()) {
            this.isApproveDetail = true;
        } else {
            this.isApproveDetail = false;
        }
        this.dappDetailParam.setDetail(this.isApproveDetail);
        this.contentFragment = DappApproveConfirmFragment.getInstance(this.dappDetailParam, this.dappMetadataParam);
        this.btnConfirm.setupCustomConfirmButtonEvent(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                nextInApprove();
            }
        });
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$updateApproveConfirmFragment$4();
            }
        });
    }

    public void lambda$updateApproveConfirmFragment$4() {
        dismissLoadingDialog();
        showEnterPassword();
    }

    @Override
    public Intent getIItent() {
        return getIntent();
    }

    public void cancle() {
        Intent intent = new Intent();
        intent.putExtra("cancle", true);
        intent.putExtra("selectedModel", this.selectedModel);
        intent.putExtra("currentmodel", this.currentModel);
        intent.putExtra("hasPassword", this.hasPassword);
        intent.putExtra("password", this.password);
        setResult(-1, intent);
        finish();
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent != null && i2 == 2019) {
            if (intent.getBooleanExtra("cancle", false)) {
                cancle();
            } else {
                try {
                    List<String> hexList = ((QrBean) new Gson().fromJson(intent.getStringExtra(TronConfig.QR_CODE_DATA),  QrBean.class)).getHexList();
                    ArrayList arrayList = new ArrayList();
                    if (hexList != null && hexList.size() > 0) {
                        for (String str : hexList) {
                            arrayList.add(Protocol.Transaction.parseFrom(Hex.decode(str)));
                        }
                    }
                    this.sign = WalletUtils.printTransaction((Protocol.Transaction) arrayList.get(0));
                    rewardSignTransaction();
                } catch (Exception e) {
                    LogUtils.e(e);
                    ToastError(StringTronUtil.getResString(R.string.parsererror));
                }
            }
        } else {
            if (intent == null || i != this.generalSignRequestCode) {
                if (intent != null && i == this.ledgerSignRequestCode) {
                    this.sign = intent.getStringExtra("SIGNSTRING");
                    rewardSignTransaction();
                    return;
                }
                cancle();
                return;
            }
            try {
                String stringExtra = intent.getStringExtra("PASSWORD");
                this.password = stringExtra;
                this.sign = getSign(this.transaction, stringExtra);
                rewardSignTransaction();
            } catch (Throwable th) {
                LogUtils.e(th);
                SentryUtil.captureException(th);
            }
        }
    }

    @Override
    public void onBackPressed() {
        cancle();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.activity_close);
    }

    public boolean isUnlimited(String str, TokenBean tokenBean) {
        return BigDecimalUtils.MoreThan(new BigDecimal(str), new BigDecimal(Math.pow(10.0d, (tokenBean != null ? tokenBean.getPrecision() : 0) + 18)));
    }

    public void setButtonStatus(boolean z) {
        this.btnConfirm.setEnabled(z);
    }

    public void jumpToNext() {
        if (this.isApprove && !this.isApproveDetail) {
            nextInApprove();
            return;
        }
        int createType = this.wallet.getCreateType();
        if (this.wallet.isWatchOnly() && 8 != createType) {
            obToSignTransaction();
            return;
        }
        try {
            AnalyticsHelper.logEventFormat(AnalyticsHelper.TransactionConfirmPopup.CLICK_CONFIRM_PAGE_OUTER, Integer.valueOf(this.transaction.getRawData().getContract(0).getType().equals(Protocol.Transaction.Contract.ContractType.TriggerSmartContract) ? 2 : 1));
        } catch (Exception e) {
            LogUtils.e(e);
        }
        int i = this.wallet.getCreateType() == 8 ? this.ledgerSignRequestCode : this.generalSignRequestCode;
        this.rootview.setVisibility(View.GONE);
        if (!StringTronUtil.isEmpty(this.toAddress) && !StringTronUtil.isEmpty(this.approveAmount)) {
            this.transaction = TriggerUtils.replaceApproveAmount(this.transaction, this.toAddress, this.approveAmount);
        }
        ConfirmTransactionNewActivity.startForResult(this, ParamBuildUtils.getDappParamBuilder(this.transaction, this.password), i);
    }

    public void nextInApprove() {
        ((DappApproveConfirmFragment) this.contentFragment).next();
        this.isApproveDetail = true;
        getConfirmBtn().onBind(this.dappDetailParam);
    }

    public void switchApproveOne() {
        this.isApproveDetail = false;
        this.dappDetailParam.setDetail(false);
        getConfirmBtn().onBind(this.dappDetailParam);
    }
}
