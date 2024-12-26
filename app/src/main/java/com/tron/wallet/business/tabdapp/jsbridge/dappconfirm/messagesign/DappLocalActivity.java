package com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.messagesign;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import androidx.fragment.app.FragmentTransaction;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.fg.component.GlobalConfirmButton;
import com.tron.wallet.business.confirm.fg.component.GlobalTitleHeaderView;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.DappParam;
import com.tron.wallet.business.confirm.parambuilder.utils.ParamBuildUtils;
import com.tron.wallet.business.confirm.sign.SignTransactionNewActivity;
import com.tron.wallet.business.pull.PullConstants;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.old.DappConfirmContract;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.old.DappConfirmModel;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.old.DappConfirmPresenter;
import com.tron.wallet.common.bean.QrBean;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.ErrorView;
import com.tron.wallet.common.components.xtablayout.XTabLayout;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.PasswordInputUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.AcLocaldappBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.io.Serializable;
import java.util.ArrayList;
import org.tron.common.bip32.Numeric;
import org.tron.common.crypto.StructuredDataEncoder;
import org.tron.common.utils.ByteArray;
import org.tron.walletserver.Wallet;
public class DappLocalActivity extends BaseActivity<DappConfirmPresenter, DappConfirmModel> implements DappConfirmContract.View {
    public static final String CANCEL_KEY = "cancle";
    public static final String SIGN_KEY = "sign_key";
    public static final String SIGN_TYPE_KEY = "sign_type_key";
    public static final String UNSIGN_KEY = "unsign";
    public static final String URL = "url";
    public static final String WALLET_NAME_KEY = "wallet_name_key";
    private String awaitSignString;
    private AcLocaldappBinding binding;
    GlobalConfirmButton buttonConfirm;
    private DAppLocalContentFragment contentFragment;
    GlobalTitleHeaderView headerView;
    private DAppLocalMetaDataFragment metaDataFragment;
    ViewGroup rootView;
    private SignMessageType signMessageType;
    private String signStrType;
    private String signString;
    XTabLayout tabLayout;
    private String unSignString;
    View view;
    private Wallet wallet;
    private String webUrl;
    private int ledgerSignRequestCode = 1238;
    private int dappMessageSignRequestCode = 1239;

    public enum SignMessageType implements Serializable {
        V1,
        V2_Str,
        V2_Hex,
        V2_BytesArray
    }

    @Override
    public Intent getIItent() {
        return getIntent();
    }

    private void cancel() {
        Intent intent = new Intent();
        intent.putExtra("cancle", true);
        setResult(-1, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        cancel();
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

    public static void start(Activity activity, String str, String str2, String str3) {
        start(activity, str, str2, SignMessageType.V1, str3);
    }

    public static void start(Activity activity, String str, String str2, SignMessageType signMessageType, String str3) {
        Intent intent = new Intent(activity, DappLocalActivity.class);
        intent.putExtra("url", str);
        intent.putExtra("unsign", str2);
        intent.putExtra(WALLET_NAME_KEY, str3);
        intent.putExtra(SIGN_TYPE_KEY, signMessageType);
        activity.startActivityForResult(intent, TronConfig.TODAPPLOCALCONFIRM);
        activity.overridePendingTransition(R.anim.activity_open, 0);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.activity_close);
    }

    @Override
    protected void setLayout() {
        AcLocaldappBinding inflate = AcLocaldappBinding.inflate(getLayoutInflater());
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
        this.rootView = this.binding.root;
        this.view = this.binding.content;
        this.headerView = this.binding.headerView;
        this.tabLayout = this.binding.tablayout;
        this.buttonConfirm = this.binding.buttonConfirm;
    }

    @Override
    protected void processData() {
        getData();
        setHeaderButtonView();
        checkMessage();
        this.view.getViewTreeObserver().addOnGlobalLayoutListener(new OnViewGlobalLayoutListener(this.view, UIUtils.dip2px(580.0f)));
    }

    public void checkMessage() {
        String str = this.unSignString;
        if (str.startsWith("0x")) {
            str = str.replaceFirst("0x", "");
        }
        if (this.signMessageType == SignMessageType.V2_BytesArray) {
            initContentFragment(DAppLocalContentFragment.TYPE_SIGN_BYTES_ARRAY);
        } else if (this.signMessageType == SignMessageType.V2_Str) {
            initContentFragment(DAppLocalContentFragment.TYPE_SIGN_STRING);
        } else if (this.signMessageType == SignMessageType.V2_Hex) {
            initContentFragment("TYPE_SIGN_HEX");
        } else if (StringTronUtil.isHexString(str)) {
            initContentFragment("TYPE_SIGN_HEX");
        } else {
            initTabLayout();
            chainIdLogEvent();
        }
        this.buttonConfirm.setEnabled(true);
        this.buttonConfirm.setupCustomConfirmButtonEvent(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                onNext();
            }
        });
        if (8 == this.wallet.getCreateType()) {
            addLedgerHeader(getString(R.string.confirm_ledger_open_warning));
        } else if (this.wallet.isWatchOnly() && this.signStrType.equals(DappParam.Type_712)) {
            addLedgerHeader(getString(R.string.error_712_cold));
        }
    }

    private void chainIdLogEvent() {
        boolean Equal;
        String str;
        try {
            String asString = ((JsonObject) new Gson().fromJson(this.unSignString,  JsonObject.class)).get("domain").getAsJsonObject().get(PullConstants.CHAIN_ID).getAsString();
            if (StringTronUtil.isEmpty(asString)) {
                AnalyticsHelper.logEvent(AnalyticsHelper.DeepLinkPage.ENTER_DEEPLINK_712_SIGN_SHOW_NO_CHAIN_ID);
                return;
            }
            if (IRequest.isNile()) {
                Equal = BigDecimalUtils.Equal(asString, TronConfig.NILE_TEST_NET_TIP_712_CHAIN_ID);
            } else {
                if (IRequest.isRelease()) {
                    Equal = BigDecimalUtils.Equal(asString, TronConfig.MAIN_NET_TIP_712_CHAIN_ID);
                }
                str = AnalyticsHelper.DeepLinkPage.ENTER_DEEPLINK_712_SIGN_SHOW_DIFFERENT_CHAIN_ID;
                AnalyticsHelper.logEvent(str);
            }
            if (Equal) {
                str = AnalyticsHelper.DeepLinkPage.ENTER_DEEPLINK_712_SIGN_SHOW_SAME_CHAIN_ID;
                AnalyticsHelper.logEvent(str);
            }
            str = AnalyticsHelper.DeepLinkPage.ENTER_DEEPLINK_712_SIGN_SHOW_DIFFERENT_CHAIN_ID;
            AnalyticsHelper.logEvent(str);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    private void initContentFragment(String str) {
        this.signStrType = DappParam.Type_Common;
        this.tabLayout.setVisibility(View.GONE);
        String str2 = this.unSignString;
        this.awaitSignString = str2;
        this.contentFragment = DAppLocalContentFragment.getInstance(str2, str);
        getSupportFragmentManager().beginTransaction().replace(R.id.content, this.contentFragment).commitAllowingStateLoss();
    }

    public void getData() {
        Intent iItent = getIItent();
        this.unSignString = iItent.getStringExtra("unsign");
        this.wallet = WalletUtils.getWallet(iItent.getStringExtra(WALLET_NAME_KEY));
        this.webUrl = iItent.getStringExtra("url");
        this.signMessageType = (SignMessageType) iItent.getSerializableExtra(SIGN_TYPE_KEY);
    }

    public void initTabLayout() {
        try {
            StructuredDataEncoder structuredDataEncoder = new StructuredDataEncoder(this.unSignString);
            byte[] hashStructuredData = structuredDataEncoder.hashStructuredData();
            ArrayList<String> parseAddressList = structuredDataEncoder.getParseAddressList();
            String jSONString = JSON.toJSONString(structuredDataEncoder.getMessage());
            this.awaitSignString = Numeric.toHexString(hashStructuredData);
            LogUtils.i("awaitSignString:" + this.awaitSignString);
            String hexString = Numeric.toHexString(structuredDataEncoder.hashDomain());
            String hexString2 = Numeric.toHexString(structuredDataEncoder.hashMessage());
            this.tabLayout.setVisibility(View.VISIBLE);
            XTabLayout xTabLayout = this.tabLayout;
            xTabLayout.addTab(xTabLayout.newTab().setText(R.string.dapp_detail));
            XTabLayout xTabLayout2 = this.tabLayout;
            xTabLayout2.addTab(xTabLayout2.newTab().setText(R.string.dapp_metadata));
            this.tabLayout.setOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
                @Override
                public void onTabReselected(XTabLayout.Tab tab) {
                }

                @Override
                public void onTabUnselected(XTabLayout.Tab tab) {
                }

                @Override
                public void onTabSelected(XTabLayout.Tab tab) {
                    FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
                    LogUtils.e("onTabSelected", String.valueOf(tab.getPosition()));
                    int position = tab.getPosition();
                    if (position == 0) {
                        if (contentFragment != null) {
                            beginTransaction.show(contentFragment).hide(metaDataFragment);
                            beginTransaction.disallowAddToBackStack();
                            beginTransaction.commitAllowingStateLoss();
                        }
                    } else if (position == 1 && metaDataFragment != null) {
                        beginTransaction.show(metaDataFragment).hide(contentFragment);
                        beginTransaction.disallowAddToBackStack();
                        beginTransaction.commitAllowingStateLoss();
                    }
                }
            });
            this.signStrType = DappParam.Type_712;
            this.contentFragment = DAppLocalContentFragment.getInstance(this.unSignString, jSONString, "TYPE_SIGN_712", parseAddressList);
            this.metaDataFragment = DAppLocalMetaDataFragment.getInstance(hexString, hexString2, ByteArray.toHexString(this.unSignString.getBytes()));
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            DAppLocalContentFragment dAppLocalContentFragment = this.contentFragment;
            if (dAppLocalContentFragment == null || this.metaDataFragment == null) {
                return;
            }
            beginTransaction.add(R.id.content, dAppLocalContentFragment, "0");
            beginTransaction.add(R.id.content, this.metaDataFragment, "1");
            beginTransaction.show(this.contentFragment).hide(this.metaDataFragment);
            beginTransaction.disallowAddToBackStack();
            beginTransaction.commitAllowingStateLoss();
        } catch (Exception e) {
            LogUtils.e(e);
            initContentFragment("TYPE_SIGN_NON_HEX");
        }
    }

    private void setHeaderButtonView() {
        BaseParam baseParam = new BaseParam();
        baseParam.setHasOwnerPermission(true);
        baseParam.setTitle(R.string.confirmtransaction, R.string.confirmtransaction);
        baseParam.addIconResource(R.mipmap.iocn_dapp_confirm_string_sign);
        baseParam.addInfoTitle(R.string.dapp_confirm_message_title, String.format(getString(R.string.dapp_confirm_message_sub_title), this.webUrl));
        this.headerView.bindData(baseParam);
        this.headerView.hideWalletNameLine();
        this.buttonConfirm.onBind(baseParam);
        this.buttonConfirm.cancelLoading();
    }

    private void addLedgerHeader(String str) {
        final int dip2px = UIUtils.dip2px(40.0f);
        final RelativeLayout relativeLayout = new RelativeLayout(this.mContext);
        relativeLayout.setBackgroundResource(R.mipmap.bg_confirm_ledger);
        ErrorView errorView = new ErrorView(this.mContext);
        errorView.setText((CharSequence) str, true);
        errorView.updateWarning(ErrorView.Level.WARNING);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, dip2px);
        layoutParams.topMargin = UIUtils.dip2px(11.0f);
        layoutParams.leftMargin = UIUtils.dip2px(20.0f);
        errorView.setTextSize(12.0f);
        errorView.setLayoutParams(layoutParams);
        final View childAt = this.rootView.getChildAt(0);
        this.rootView.post(new Runnable() {
            @Override
            public final void run() {
                DappLocalActivity.lambda$addLedgerHeader$0(dip2px, childAt, relativeLayout);
            }
        });
        relativeLayout.addView(errorView);
        this.rootView.addView(relativeLayout, 0);
    }

    public static void lambda$addLedgerHeader$0(int i, View view, RelativeLayout relativeLayout) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, i + view.getMeasuredHeight());
        layoutParams.addRule(12);
        relativeLayout.setLayoutParams(layoutParams);
    }

    public void onNext() {
        int createType = this.wallet.getCreateType();
        if (this.wallet.isWatchOnly() && 8 != createType) {
            obToSignTransaction();
            return;
        }
        int i = this.wallet.getCreateType() == 8 ? this.ledgerSignRequestCode : this.dappMessageSignRequestCode;
        int toConfirmType = getToConfirmType();
        this.rootView.setVisibility(View.GONE);
        ConfirmTransactionNewActivity.startForResult(this, ParamBuildUtils.getDappMessageParamBuilder(this.awaitSignString, this.signStrType, toConfirmType), i);
    }

    private int getToConfirmType() {
        if (DappParam.Type_712.equals(this.signStrType)) {
            return 17;
        }
        if (this.signMessageType == SignMessageType.V2_Str) {
            return 102;
        }
        if (this.signMessageType == SignMessageType.V2_Hex) {
            return 103;
        }
        return this.signMessageType == SignMessageType.V2_BytesArray ? 104 : 16;
    }

    private int getToObType() {
        if (DappParam.Type_712.equals(this.signStrType)) {
            return 101;
        }
        if (this.signMessageType == SignMessageType.V2_Str) {
            return 102;
        }
        if (this.signMessageType == SignMessageType.V2_Hex) {
            return 103;
        }
        return this.signMessageType == SignMessageType.V2_BytesArray ? 104 : 98;
    }

    private void rewardSignTransaction() {
        runOnUiThread(new Runnable() {
            @Override
            public final void run() {
                lambda$rewardSignTransaction$1();
            }
        });
    }

    public void lambda$rewardSignTransaction$1() {
        if (StringTronUtil.isEmpty(this.signString)) {
            return;
        }
        PasswordInputUtils.updateSuccessPwd(this.mContext, this.wallet.getWalletName(), 8);
        Intent intent = new Intent();
        intent.putExtra("sign_key", this.signString);
        setResult(-1, intent);
        finish();
    }

    private void obToSignTransaction() {
        try {
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.awaitSignString);
            QrBean qrBean = new QrBean();
            qrBean.setHexList(arrayList);
            qrBean.setType(getToObType());
            qrBean.setAddress(WalletUtils.getSelectedPublicWallet().getAddress());
            SignTransactionNewActivity.start(this, qrBean, new TokenBean(), TronConfig.OB_W);
        } catch (Exception unused) {
            cancel();
        }
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent != null && i2 == 2019) {
            if (intent.getBooleanExtra("cancle", false)) {
                cancel();
                return;
            }
            try {
                this.signString = ((QrBean) new Gson().fromJson(intent.getStringExtra(TronConfig.QR_CODE_DATA),  QrBean.class)).getHexList().get(0);
                rewardSignTransaction();
            } catch (Exception e) {
                LogUtils.e(e);
                ToastError(StringTronUtil.getResString(R.string.parsererror));
            }
        } else if (intent != null && i == this.ledgerSignRequestCode) {
            this.signString = intent.getStringExtra("SIGNSTRING");
            rewardSignTransaction();
        } else if (intent != null && i == this.dappMessageSignRequestCode) {
            String stringExtra = intent.getStringExtra("SIGNSTRING");
            this.signString = stringExtra;
            LogUtils.i("DAppSignMessage1", stringExtra);
            rewardSignTransaction();
        } else {
            cancel();
        }
    }

    @Override
    public void exit() {
        cancel();
    }

    class OnViewGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
        private int maxHeight;
        private View view;

        public OnViewGlobalLayoutListener(View view, int i) {
            this.view = view;
            this.maxHeight = i;
        }

        @Override
        public void onGlobalLayout() {
            if (this.view.getHeight() > this.maxHeight) {
                this.view.getLayoutParams().height = this.maxHeight;
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.view.getLayoutParams();
                layoutParams.height = this.maxHeight;
                this.view.setLayoutParams(layoutParams);
            }
        }
    }
}
