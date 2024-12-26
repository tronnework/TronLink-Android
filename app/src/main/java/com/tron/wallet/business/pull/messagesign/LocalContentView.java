package com.tron.wallet.business.pull.messagesign;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import com.alibaba.fastjson.JSON;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.pull.IntentResult;
import com.tron.wallet.business.pull.PullAction;
import com.tron.wallet.business.pull.PullConstants;
import com.tron.wallet.business.pull.PullResultCode;
import com.tron.wallet.business.pull.PullResultHelper;
import com.tron.wallet.business.pull.component.PullDetailView;
import com.tron.wallet.business.pull.sign.SignParam;
import com.tron.wallet.business.walletmanager.pairwallet.PairColdWalletDialog;
import com.tron.wallet.common.components.ErrorView;
import com.tron.wallet.common.utils.AddressNameUtils;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.PullItemLocalBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import org.tron.common.crypto.StructuredDataEncoder;
import org.tron.walletserver.Wallet;
public class LocalContentView extends PullDetailView {
    private final String TAG;
    private PullAction action;
    private ArrayList<String> addressParseList;
    private PullItemLocalBinding binding;
    private Disposable getAddressDisposable;
    private final ActivityResultLauncher<Intent> lanchConfirm;
    private SignParam localContentParam;
    private LocalContentResult localContentResult;
    private Context mContext;
    private String messageString;
    private String signType;
    LinearLayout tvMessageContent;
    TextView tvPullAddress;
    TextView tvPullAddressName;
    private String unSignString;
    LinearLayout verticalScrollViewLayout;

    public LocalContentView(Context context, SignParam signParam, PullAction pullAction) {
        super(context);
        this.TAG = "TriggerSmartContractView";
        this.mContext = context;
        this.localContentParam = signParam;
        this.action = pullAction;
        this.lanchConfirm = ((BaseActivity) context).registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult activityResult) {
                Intent data = activityResult.getData();
                int resultCode = activityResult.getResultCode();
                LogUtils.d("TriggerSmartContractView", "onActivityResult  TriggerSmartContractView  " + resultCode);
                if (data != null) {
                    String stringExtra = data.getStringExtra(IntentResult.ResultKey);
                    String stringExtra2 = data.getStringExtra(IntentResult.SignedMessage);
                    LogUtils.d("TriggerSmartContractView", "onActivityResult  " + stringExtra + "  " + stringExtra2);
                    if (StringTronUtil.equals(stringExtra, IntentResult.Succeeded)) {
                        localSignSuccess(stringExtra2);
                    } else if (StringTronUtil.equals(stringExtra, IntentResult.Failed)) {
                        localSignFailed();
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
        this.headerIcon.setImageResource(R.mipmap.ic_pull_request_local);
    }

    @Override
    public void initContent(FrameLayout frameLayout, ErrorView errorView) {
        super.initContent(frameLayout, errorView);
        if (WalletUtils.getSelectedPublicWallet().isSamsungWallet()) {
            View inflate = View.inflate(this.activity, R.layout.pull_item_header_local, null);
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.li_pull_title_error);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.iv_pull_title_error);
            TextView textView = (TextView) inflate.findViewById(R.id.tv_pull_title_error);
            if (this.localContentParam.getSignType().equals(PullConstants.SIGN_TYPED_DATA)) {
                textView.setText(this.mContext.getResources().getString(R.string.pull_sign_712_not_support_samsung));
            } else {
                textView.setText(this.mContext.getResources().getString(R.string.pull_sign_hex_not_support_samsung));
            }
            this.headerExtend.addView(inflate, new FrameLayout.LayoutParams(-1, -2));
            this.headerExtend.setVisibility(View.VISIBLE);
        }
        this.contentExtend.setVisibility(View.VISIBLE);
        View inflate2 = View.inflate(this.activity, R.layout.pull_item_local, null);
        this.binding = PullItemLocalBinding.bind(inflate2);
        mappingId();
        this.contentExtend.addView(inflate2, new FrameLayout.LayoutParams(-1, -2));
    }

    @Override
    public void initAction(TextView textView, TextView textView2) {
        super.initAction(textView, textView2);
        if (!WalletUtils.getSelectedPublicWallet().isSamsungWallet()) {
            this.btnConfirm.setText(this.activity.getString(R.string.pull_next_step));
            this.btnConfirm.setOnClickListener(new NoDoubleClickListener2() {
                @Override
                protected void onNoDoubleClick(View view) {
                    Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
                    if (selectedPublicWallet != null && selectedPublicWallet.isWatchNotPaired()) {
                        PairColdWalletDialog.showUp(view.getContext(), null);
                    } else {
                        doConfirmTransaction();
                    }
                }
            });
            this.btnCancel.setText(this.activity.getString(R.string.cancel));
            this.btnCancel.setOnClickListener(new NoDoubleClickListener2() {
                @Override
                protected void onNoDoubleClick(View view) {
                    LocalContentView localContentView = LocalContentView.this;
                    localContentView.requestQuit(localContentView.localContentParam);
                }
            });
            return;
        }
        this.btnCancel.setVisibility(View.GONE);
        this.btnConfirm.setText(this.activity.getString(R.string.pull_back_to_dapp));
        notSuppotSamsung();
    }

    public void doConfirmTransaction() {
        if (this.localContentParam.getSignType().equals(PullConstants.SIGN_TYPED_DATA)) {
            AnalyticsHelper.logEvent(AnalyticsHelper.DeepLinkPage.CLICK_DEEPLINK_REQUEST_712_SIGN);
        } else {
            AnalyticsHelper.logEvent(AnalyticsHelper.DeepLinkPage.CLICK_DEEPLINK_REQUEST_MESSAGE_SIGN);
        }
        this.lanchConfirm.launch(DeepLinkDappLocalActivity.startForResultIntent((Activity) this.mContext, this.localContentParam.getUrl(), this.unSignString, WalletUtils.getSelectedWallet().getWalletName()));
    }

    @Override
    public void init() {
        this.unSignString = this.localContentParam.getMessage();
        asyncFormatAddress(this.localContentParam.getLoginAddress());
        TypeDataParser typeDataParser = new TypeDataParser();
        if (this.localContentParam.getSignType().equals(PullConstants.SIGN_TYPED_DATA)) {
            AnalyticsHelper.logEvent(AnalyticsHelper.DeepLinkPage.ENTER_DEEPLINK_REQUEST_712_SIGN);
            this.title.setText(this.activity.getString(R.string.pull_sign_712));
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.verticalScrollViewLayout.getLayoutParams();
            layoutParams.height = UIUtils.dip2px(146.0f);
            this.verticalScrollViewLayout.setLayoutParams(layoutParams);
            try {
                StructuredDataEncoder structuredDataEncoder = new StructuredDataEncoder(this.unSignString);
                structuredDataEncoder.hashStructuredData();
                this.messageString = JSON.toJSONString(structuredDataEncoder.getMessage());
                ArrayList<String> parseAddressList = structuredDataEncoder.getParseAddressList();
                this.addressParseList = parseAddressList;
                for (TextView textView : typeDataParser.parserString(this.messageString, parseAddressList)) {
                    this.tvMessageContent.addView(textView);
                }
                return;
            } catch (Exception e) {
                LogUtils.e(e);
                return;
            }
        }
        AnalyticsHelper.logEvent(AnalyticsHelper.DeepLinkPage.ENTER_DEEPLINK_REQUEST_MESSAGE_SIGN);
        this.messageString = this.localContentParam.getMessage();
        if (this.localContentParam.getSignType().equals(PullConstants.SIGN_STR)) {
            this.title.setText(this.activity.getString(R.string.pull_sign_hex));
            TextView newTextView = typeDataParser.getNewTextView();
            newTextView.setText(this.unSignString);
            newTextView.setTextColor(this.mContext.getResources().getColor(R.color.black_23));
            this.tvMessageContent.addView(newTextView);
        }
    }

    @Override
    public void deInit() {
        Disposable disposable = this.getAddressDisposable;
        if (disposable != null && !disposable.isDisposed()) {
            this.getAddressDisposable.dispose();
        }
        this.binding = null;
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

    public void localSignSuccess(String str) {
        if (this.localContentParam.getSignType().equals(PullConstants.SIGN_TYPED_DATA)) {
            AnalyticsHelper.logEvent(AnalyticsHelper.DeepLinkPage.ENTER_DEEPLINK_REQUEST_SUCCESS_712_SIGN);
        } else {
            AnalyticsHelper.logEvent(AnalyticsHelper.DeepLinkPage.ENTER_DEEPLINK_REQUEST_SUCCESS_MESSAGE_SIGN);
        }
        this.headerIcon.setImageResource(R.mipmap.ic_unstake_result_success);
        this.title.setText(this.activity.getString(R.string.pull_sign_success));
        this.contentTip.setVisibility(View.GONE);
        this.btnCancel.setVisibility(View.GONE);
        this.btnConfirm.setText(this.activity.getString(R.string.pull_back_to_dapp));
        LocalContentResult localContentResult = new LocalContentResult();
        this.localContentResult = localContentResult;
        localContentResult.setActionId(this.localContentParam.getActionId());
        this.localContentResult.setCode(PullResultCode.SUCCESS.getCode());
        this.localContentResult.setMessage(PullResultCode.SUCCESS.getMessage());
        this.localContentResult.setSignedData(str);
        PullResultHelper.callBackToDApp(this.localContentParam.getCallbackUrl(), JSON.toJSONString(this.localContentResult));
        setBackToDApp(this.localContentResult);
    }

    public void localSignFailed() {
        if (this.localContentParam.getSignType().equals(PullConstants.SIGN_TYPED_DATA)) {
            AnalyticsHelper.logEvent(AnalyticsHelper.DeepLinkPage.ENTER_DEEPLINK_REQUEST_FAIL_712_SIGN);
        } else {
            AnalyticsHelper.logEvent(AnalyticsHelper.DeepLinkPage.ENTER_DEEPLINK_REQUEST_FAIL_MESSAGE_SIGN);
        }
        this.headerIcon.setImageResource(R.mipmap.ic_unstake_result_fail_all);
        this.title.setText(this.activity.getString(R.string.pull_sign_fail));
        this.contentTip.setVisibility(View.GONE);
        this.btnCancel.setVisibility(View.GONE);
        this.btnConfirm.setText(this.activity.getString(R.string.pull_back_to_dapp));
        LocalContentResult localContentResult = new LocalContentResult();
        this.localContentResult = localContentResult;
        localContentResult.setActionId(this.localContentParam.getActionId());
        this.localContentResult.setCode(PullResultCode.FAIL_TRANSACTION_BROADCAST_FAIL.getCode());
        this.localContentResult.setMessage(PullResultCode.FAIL_TRANSACTION_BROADCAST_FAIL.getMessage());
        PullResultHelper.callBackToDApp(this.localContentParam.getCallbackUrl(), JSON.toJSONString(this.localContentResult));
        setBackToDApp(this.localContentResult);
    }

    public void notSuppotSamsung() {
        LocalContentResult localContentResult = new LocalContentResult();
        this.localContentResult = localContentResult;
        localContentResult.setActionId(this.localContentParam.getActionId());
        this.localContentResult.setCode(PullResultCode.FAIL_CANCEL.getCode());
        this.localContentResult.setMessage(PullResultCode.FAIL_CANCEL.getMessage());
        PullResultHelper.callBackToDApp(this.localContentParam.getCallbackUrl(), JSON.toJSONString(this.localContentResult));
        setBackToDApp(this.localContentResult);
    }

    public void mappingId() {
        this.tvPullAddressName = this.binding.tvPullAddressName;
        this.tvPullAddress = this.binding.tvPullAddress;
        this.tvMessageContent = this.binding.messageContent;
        this.verticalScrollViewLayout = this.binding.messageContentLayout;
    }
}
