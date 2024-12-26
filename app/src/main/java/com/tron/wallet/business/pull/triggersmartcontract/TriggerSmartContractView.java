package com.tron.wallet.business.pull.triggersmartcontract;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import com.alibaba.fastjson.JSON;
import com.google.protobuf.InvalidProtocolBufferException;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.pull.IntentResult;
import com.tron.wallet.business.pull.PullResultCode;
import com.tron.wallet.business.pull.PullResultHelper;
import com.tron.wallet.business.pull.component.PullDetailView;
import com.tron.wallet.business.pull.dappconfirm.content.DeepLinkDappConfirmActivity;
import com.tron.wallet.business.pull.sign.SignParam;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.component.ConfirmModel;
import com.tron.wallet.business.walletmanager.pairwallet.PairColdWalletDialog;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.components.CenterSpaceImageSpan;
import com.tron.wallet.common.components.ErrorView;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AddressNameUtils;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.PullItemTriggerSmartContractBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.Callable;
import org.bouncycastle.util.encoders.Hex;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
import org.tron.protos.contract.SmartContractOuterClass;
import org.tron.walletserver.Wallet;
public class TriggerSmartContractView extends PullDetailView {
    private final String TAG;
    private PullItemTriggerSmartContractBinding binding;
    private Disposable getAddressDisposable;
    View hashLayout;
    private final ActivityResultLauncher<Intent> lanchConfirm;
    private Context mContext;
    View noteLayout;
    private Protocol.Transaction transaction;
    private SignParam triggerSmartContractParam;
    private TriggerSmartContractResult triggerSmartContractResult;
    TextView tvContractAddress;
    TextView tvHash;
    TextView tvHexadecimalData;
    TextView tvNote;
    TextView tvPullAddress;
    TextView tvPullAddressName;

    public TriggerSmartContractView(Context context, SignParam signParam) {
        super(context);
        this.TAG = "TriggerSmartContractView";
        this.mContext = context;
        this.triggerSmartContractParam = signParam;
        this.lanchConfirm = ((BaseActivity) context).registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult activityResult) {
                Intent data = activityResult.getData();
                int resultCode = activityResult.getResultCode();
                LogUtils.d("TriggerSmartContractView", "onActivityResult  TriggerSmartContractView  " + resultCode);
                if (data != null) {
                    String stringExtra = data.getStringExtra(IntentResult.ResultKey);
                    String stringExtra2 = data.getStringExtra(IntentResult.HashKey);
                    String stringExtra3 = data.getStringExtra(IntentResult.ErrorMessageKey);
                    LogUtils.d("TriggerSmartContractView", "onActivityResult  " + stringExtra2 + "  " + stringExtra3);
                    if (StringTronUtil.equals(stringExtra, IntentResult.Succeeded)) {
                        triggerSmartContractSuccess(stringExtra2);
                    } else if (StringTronUtil.equals(stringExtra, IntentResult.Failed)) {
                        triggerSmartContractFailed();
                    }
                }
            }
        });
    }

    @Override
    public PullResultCode checkDataValid() {
        return PullResultCode.SUCCESS;
    }

    @Override
    public void initHeader(TextView textView, ImageView imageView, TextView textView2, FrameLayout frameLayout, ErrorView errorView) {
        super.initHeader(textView, imageView, textView2, frameLayout, errorView);
        this.rightBtn.setVisibility(View.GONE);
        this.headerIcon.setImageResource(R.mipmap.ic_pull_request_trigger_smart_contract);
        this.title.setText(this.activity.getString(R.string.pull_request_trigger_smart_contract));
    }

    @Override
    public void initContent(FrameLayout frameLayout, ErrorView errorView) {
        super.initContent(frameLayout, errorView);
        this.contentExtend.setVisibility(View.VISIBLE);
        View inflate = View.inflate(this.activity, R.layout.pull_item_trigger_smart_contract, null);
        this.binding = PullItemTriggerSmartContractBinding.bind(inflate);
        mappingId();
        this.contentExtend.addView(inflate, new FrameLayout.LayoutParams(-1, -2));
    }

    public void doconfirmTransaction() {
        AnalyticsHelper.logEvent(AnalyticsHelper.DeepLinkPage.CLICK_DEEPLINK_REQUEST_CONTRACT);
        this.lanchConfirm.launch(DeepLinkDappConfirmActivity.startForResultIntent((Activity) this.mContext, "", "", this.transaction, false, WalletUtils.getSelectedWallet(), ConfirmModel.SAFE, false, null, null, -2, this.triggerSmartContractParam.getMethod(), null));
    }

    @Override
    public void init() {
        SmartContractOuterClass.TriggerSmartContract triggerSmartContract;
        Protocol.Transaction packTransaction = WalletUtils.packTransaction(this.triggerSmartContractParam.getData());
        this.transaction = packTransaction;
        try {
            triggerSmartContract = (SmartContractOuterClass.TriggerSmartContract) TransactionUtils.unpackContract(packTransaction.getRawData().getContract(0), SmartContractOuterClass.TriggerSmartContract.class);
        } catch (InvalidProtocolBufferException e) {
            LogUtils.e(e);
            triggerSmartContract = null;
        }
        asyncFormatAddress(StringTronUtil.encode58Check(triggerSmartContract.getOwnerAddress().toByteArray()));
        this.tvContractAddress.setText(StringTronUtil.encode58Check(triggerSmartContract.getContractAddress().toByteArray()));
        TextView textView = new TextView(this.mContext);
        textView.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        textView.setGravity(1);
        SpannableString spannableString = new SpannableString(this.mContext.getResources().getString(R.string.trigger) + "  " + StringTronUtil.indentAddress(StringTronUtil.encode58Check(triggerSmartContract.getContractAddress().toByteArray()), 6));
        CenterSpaceImageSpan centerSpaceImageSpan = new CenterSpaceImageSpan(this.mContext, R.mipmap.deep_icon_contract, 2, UIUtils.dip2px(4.0f), UIUtils.dip2px(4.0f));
        int length = this.mContext.getResources().getString(R.string.trigger).length();
        spannableString.setSpan(centerSpaceImageSpan, length, length + 2, 18);
        textView.setText(spannableString);
        textView.setTextColor(this.mContext.getResources().getColor(R.color.black_23));
        textView.setTextSize(18.0f);
        textView.setTypeface(Typeface.defaultFromStyle(1));
        this.headerExtend.setVisibility(View.VISIBLE);
        this.headerExtend.addView(textView, new FrameLayout.LayoutParams(-1, -2));
        if (!StringTronUtil.isEmpty(new String(this.transaction.getRawData().getData().toByteArray()))) {
            this.noteLayout.setVisibility(View.VISIBLE);
            this.tvNote.setText(new String(this.transaction.getRawData().getData().toByteArray()));
        }
        this.tvHexadecimalData.setText(Hex.toHexString(this.transaction.toByteArray()));
        this.btnConfirm.setText(this.activity.getString(R.string.pull_next_step));
        this.btnConfirm.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
                if (selectedPublicWallet != null && selectedPublicWallet.isWatchNotPaired()) {
                    PairColdWalletDialog.showUp(view.getContext(), null);
                } else {
                    doconfirmTransaction();
                }
            }
        });
        this.btnCancel.setText(this.activity.getString(R.string.pull_cancel));
        this.btnCancel.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                TriggerSmartContractView triggerSmartContractView = TriggerSmartContractView.this;
                triggerSmartContractView.requestQuit(triggerSmartContractView.triggerSmartContractParam);
            }
        });
        AnalyticsHelper.logEvent(AnalyticsHelper.DeepLinkPage.ENTER_DEEPLINK_REQUEST_CONTRACT);
    }

    @Override
    public void deInit() {
        Disposable disposable = this.getAddressDisposable;
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.getAddressDisposable.dispose();
    }

    private void asyncFormatAddress(final String str) {
        Observable.fromCallable(new Callable() {
            @Override
            public final Object call() {
                String nameByAddress;
                nameByAddress = AddressNameUtils.getInstance().getNameByAddress(str, false);
                return nameByAddress;
            }
        }).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<String>() {
            @Override
            public void onResponse(String str2, String str3) {
                LogUtils.e("asyncFormatAddress:" + str2 + "result :" + str3);
                tvPullAddressName.setText(str3);
                tvPullAddressName.setSingleLine(true);
                tvPullAddress.setVisibility(View.VISIBLE);
                TextView textView = tvPullAddress;
                textView.setText("(" + str + ")");
                tvPullAddress.setTextColor(mContext.getResources().getColor(R.color.black_6d));
            }

            @Override
            public void onFailure(String str2, String str3) {
                tvPullAddressName.setText(str);
                tvPullAddressName.setSingleLine(false);
                tvPullAddress.setVisibility(View.GONE);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                if (getAddressDisposable != null && !getAddressDisposable.isDisposed()) {
                    getAddressDisposable.dispose();
                }
                getAddressDisposable = disposable;
            }
        }, "formatAddress"));
    }

    public void triggerSmartContractSuccess(final String str) {
        AnalyticsHelper.logEvent(AnalyticsHelper.DeepLinkPage.ENTER_DEEPLINK_REQUEST_SUCCESS_CONTRACT);
        this.headerIcon.setImageResource(R.mipmap.ic_unstake_result_success);
        this.title.setText(this.activity.getString(R.string.pull_trigger_success));
        this.contentTip.setVisibility(View.GONE);
        this.btnCancel.setVisibility(View.GONE);
        this.btnConfirm.setText(this.activity.getString(R.string.pull_back_to_dapp));
        this.tvHash.setText(StringTronUtil.indentAddress(str, 10));
        this.hashLayout.setVisibility(View.VISIBLE);
        this.tvHash.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                CommonWebActivityV3.start(mContext, getUrl(str), mContext.getString(R.string.transfer_doc), 1, true);
            }
        });
        TriggerSmartContractResult triggerSmartContractResult = new TriggerSmartContractResult();
        this.triggerSmartContractResult = triggerSmartContractResult;
        triggerSmartContractResult.setActionId(this.triggerSmartContractParam.getActionId());
        this.triggerSmartContractResult.setCode(PullResultCode.SUCCESS.getCode());
        this.triggerSmartContractResult.setMessage(PullResultCode.SUCCESS.getMessage());
        this.triggerSmartContractResult.setTransactionHash(str);
        PullResultHelper.callBackToDApp(this.triggerSmartContractParam.getCallbackUrl(), JSON.toJSONString(this.triggerSmartContractResult));
        setBackToDApp(this.triggerSmartContractResult);
    }

    public void triggerSmartContractFailed() {
        AnalyticsHelper.logEvent(AnalyticsHelper.DeepLinkPage.ENTER_DEEPLINK_REQUEST_FAIL_CONTRACT);
        this.headerIcon.setImageResource(R.mipmap.ic_unstake_result_fail_all);
        this.title.setText(this.activity.getString(R.string.pull_trigger_fail));
        this.contentTip.setVisibility(View.GONE);
        this.btnCancel.setVisibility(View.GONE);
        this.btnConfirm.setText(this.activity.getString(R.string.pull_back_to_dapp));
        if (this.triggerSmartContractResult != null) {
            TriggerSmartContractResult triggerSmartContractResult = new TriggerSmartContractResult();
            this.triggerSmartContractResult = triggerSmartContractResult;
            triggerSmartContractResult.setCode(PullResultCode.FAIL_TRANSACTION_BROADCAST_FAIL.getCode());
            this.triggerSmartContractResult.setMessage(PullResultCode.FAIL_TRANSACTION_BROADCAST_FAIL.getMessage());
        }
        PullResultHelper.callBackToDApp(this.triggerSmartContractParam.getCallbackUrl(), JSON.toJSONString(this.triggerSmartContractResult));
        setBackToDApp(this.triggerSmartContractResult);
    }

    public String getUrl(String str) {
        String str2;
        if (SpAPI.THIS.getCurrentChain() != null && SpAPI.THIS.getCurrentChain().isMainChain) {
            str2 = TronConfig.TRONSCANHOST_MAINCHAIN + str;
        } else {
            str2 = TronConfig.TRONSCANHOST_DAPPCHAIN + str;
        }
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return str2 + "?lang=zh";
        }
        return str2 + "?lang=en";
    }

    public void mappingId() {
        this.tvPullAddressName = this.binding.tvPullAddressName;
        this.tvPullAddress = this.binding.tvPullAddress;
        this.tvContractAddress = this.binding.tvContractAddress;
        this.noteLayout = this.binding.noteLayout;
        this.tvNote = this.binding.tvNote;
        this.hashLayout = this.binding.hashLayout;
        this.tvHash = this.binding.tvHash;
        this.tvHexadecimalData = this.binding.hexadecimalDataContent;
    }
}
