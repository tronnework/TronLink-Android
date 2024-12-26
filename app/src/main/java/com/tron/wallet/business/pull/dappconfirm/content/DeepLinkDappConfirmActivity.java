package com.tron.wallet.business.pull.dappconfirm.content;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.protobuf.InvalidProtocolBufferException;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.fg.component.DappConfirmButton;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.DappDetailParam;
import com.tron.wallet.business.confirm.parambuilder.bean.DappMetadataParam;
import com.tron.wallet.business.confirm.parambuilder.utils.ParamBuildUtils;
import com.tron.wallet.business.confirm.sign.SignTransactionNewActivity;
import com.tron.wallet.business.pull.IntentResult;
import com.tron.wallet.business.pull.dappconfirm.content.transfer.DeepLinkDappTransferConfirmFragment;
import com.tron.wallet.business.pull.dappconfirm.content.triggersmartcontract.DeepLinkDappTriggerSmartContractConfirmFragment;
import com.tron.wallet.business.tabdapp.bean.DappProjectInfoBean;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.component.ConfirmModel;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.DappConfirmNewContract;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.DappConfirmNewModel;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.DappConfirmNewPresenter;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.approve.DappApproveConfirmFragment;
import com.tron.wallet.common.bean.QrBean;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.transaction.TriggerUtils;
import com.tron.wallet.databinding.PopDappNewBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import org.tron.common.utils.ByteArray;
import org.tron.protos.Protocol;
import org.tron.protos.contract.SmartContractOuterClass;
import org.tron.walletserver.TriggerData;
import org.tron.walletserver.Wallet;
public class DeepLinkDappConfirmActivity extends BaseActivity<DappConfirmNewPresenter, DappConfirmNewModel> implements DappConfirmNewContract.View {
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
    private FragmentTransaction beginTransaction;
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
    private RxManager mRxmanager;
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
    private int deeplinkRequestCode = 1240;
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
        this.beginTransaction = getSupportFragmentManager().beginTransaction();
        Wallet wallet = WalletUtils.getWallet(iItent.getStringExtra("walletName"));
        this.wallet = wallet;
        if (wallet == null) {
            return;
        }
        if (wallet.getCreateType() == 7) {
            this.rootview.setVisibility(View.GONE);
            ConfirmTransactionNewActivity.startForResult(this, ParamBuildUtils.getDeepLinkParamBuilder(this.transaction, this.password), this.deeplinkRequestCode, true);
            return;
        }
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.numberFormat = numberInstance;
        numberInstance.setMaximumFractionDigits(7);
        this.tokenName = "\tTRX";
        showLoadingDialog(false, false);
        runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$processData$1();
            }
        });
        this.llSelected.setClickable(false);
    }

    public static class fun2 {
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
Method not decompiled: com.tron.wallet.business.pull.dappconfirm.content.DeepLinkDappConfirmActivity.lambda$processData$1():void");
    }

    public void lambda$processData$0() {
        dismissLoadingDialog();
        showEnterPassword();
    }

    private void buildFragmentFromTokenType(int i, DappDetailParam dappDetailParam, DappMetadataParam dappMetadataParam) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3 && i != 4 && i != 990 && i != 993) {
                        this.contentFragment = DeepLinkDappTriggerSmartContractConfirmFragment.getInstance(dappDetailParam, dappMetadataParam);
                        return;
                    }
                }
            }
            this.isApprove = true;
            dappDetailParam.setApprove(true);
            return;
        }
        this.contentFragment = DeepLinkDappTransferConfirmFragment.getInstance(dappDetailParam, dappMetadataParam);
    }

    public static void startForResult(Activity activity, String str, String str2, Protocol.Transaction transaction, boolean z, Wallet wallet, ConfirmModel confirmModel, boolean z2, String str3, String str4, int i, String str5, String str6) {
        activity.startActivityForResult(getStartIntent(activity, str, str2, transaction, z, wallet, confirmModel, z2, str3, str4, i, str5, str6), 4097);
        activity.overridePendingTransition(R.anim.activity_open, 0);
    }

    public static Intent startForResultIntent(Activity activity, String str, String str2, Protocol.Transaction transaction, boolean z, Wallet wallet, ConfirmModel confirmModel, boolean z2, String str3, String str4, int i, String str5, String str6) {
        return getStartIntent(activity, str, str2, transaction, z, wallet, confirmModel, z2, str3, str4, i, str5, str6);
    }

    private static Intent getStartIntent(Activity activity, String str, String str2, Protocol.Transaction transaction, boolean z, Wallet wallet, ConfirmModel confirmModel, boolean z2, String str3, String str4, int i, String str5, String str6) {
        Intent intent = new Intent(activity, DeepLinkDappConfirmActivity.class);
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
        return intent;
    }

    public void initData(String str, String str2, String str3, String str4, String str5, Wallet wallet, Protocol.Transaction.Contract contract) {
        StringTronUtil.isEmpty(str);
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
            SignTransactionNewActivity.start2(this, qrBean, new TokenBean(), TronConfig.OB_W, BaseParam.PageFrom.DeepLink);
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
                lambda$showEnterPassword$2();
            }
        }, 200L);
    }

    public void lambda$showEnterPassword$2() {
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
                lambda$updateApproveConfirmFragment$3();
            }
        });
    }

    public void lambda$updateApproveConfirmFragment$3() {
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
        setResult(0, intent);
        finish();
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent != null && i == this.deeplinkRequestCode) {
            backToDeepLink(intent.getStringExtra(IntentResult.ResultKey), intent.getStringExtra(IntentResult.HashKey), intent.getStringExtra(IntentResult.ErrorMessageKey));
            return;
        }
        cancle();
    }

    private void backToDeepLink(String str, String str2, String str3) {
        try {
            Intent intent = new Intent();
            intent.putExtra(IntentResult.ResultKey, str);
            intent.putExtra(IntentResult.ErrorMessageKey, str3);
            intent.putExtra(IntentResult.HashKey, str2);
            setResult(-1, intent);
            exit();
        } catch (Exception e) {
            LogUtils.e(e);
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
        this.rootview.setVisibility(View.GONE);
        if (!StringTronUtil.isEmpty(this.toAddress) && !StringTronUtil.isEmpty(this.approveAmount)) {
            this.transaction = TriggerUtils.replaceApproveAmount(this.transaction, this.toAddress, this.approveAmount);
        }
        ConfirmTransactionNewActivity.startForResult(this, ParamBuildUtils.getDeepLinkParamBuilder(this.transaction, this.password), this.deeplinkRequestCode, this.wallet.isSamsungWallet());
    }

    public void nextInApprove() {
        ((DappApproveConfirmFragment) this.contentFragment).next();
        this.isApproveDetail = true;
        getConfirmBtn().onBind(this.dappDetailParam);
    }

    private void triggerContent(String str, TokenBean tokenBean, String str2, String str3, SmartContractOuterClass.TriggerSmartContract triggerSmartContract, int i) {
        String format = this.numberFormat.format(triggerSmartContract.getCallValue() / 1000000.0d);
        String encode58Check = StringTronUtil.encode58Check(triggerSmartContract.getOwnerAddress().toByteArray());
        DappDetailParam dappDetailParam = (DappDetailParam) ParamBuildUtils.getDappDetailParamBuilder(this.transaction, encode58Check, null, format, tokenBean, str, new String(this.transaction.getRawData().getData().toByteArray()), null, this.transaction.getRawData().getContract(0).getType().getNumber(), i, this.triggerData, str2, triggerSmartContract, -1).get();
        this.dappDetailParam = dappDetailParam;
        if (i == 0 || i == 2) {
            dappDetailParam.setTransfer(true);
        }
        DappMetadataParam dappMetadataParam = (DappMetadataParam) ParamBuildUtils.getDappMetadataParamBuilder(this.transaction, format, encode58Check, str3, str, this.triggerData).get();
        this.dappMetadataParam = dappMetadataParam;
        buildFragmentFromTokenType(i, this.dappDetailParam, dappMetadataParam);
    }

    public void switchApproveOne() {
        this.isApproveDetail = false;
        this.dappDetailParam.setDetail(false);
        getConfirmBtn().onBind(this.dappDetailParam);
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
}
