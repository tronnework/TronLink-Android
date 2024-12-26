package com.tron.wallet.business.pull.transfer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.alibaba.fastjson.JSON;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.addassets2.AssetsManager;
import com.tron.wallet.business.addassets2.bean.AssetsQueryOutput;
import com.tron.wallet.business.maintab.MainTabActivity;
import com.tron.wallet.business.pull.IntentResult;
import com.tron.wallet.business.pull.PullAction;
import com.tron.wallet.business.pull.PullActivity;
import com.tron.wallet.business.pull.PullConstants;
import com.tron.wallet.business.pull.PullParam;
import com.tron.wallet.business.pull.PullResultCode;
import com.tron.wallet.business.pull.PullResultHelper;
import com.tron.wallet.business.pull.component.PullDetailActivity;
import com.tron.wallet.business.pull.component.PullDetailView;
import com.tron.wallet.business.pull.dappconfirm.content.DeepLinkDappConfirmActivity;
import com.tron.wallet.business.pull.sign.SignParam;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.component.ConfirmModel;
import com.tron.wallet.business.walletmanager.pairwallet.PairColdWalletDialog;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.ErrorView;
import com.tron.wallet.common.components.MultiLineTextPopupView;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.M;
import com.tron.wallet.common.config.T;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.CustomFontUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.common.utils.transaction.TriggerUtils;
import com.tron.wallet.databinding.PullItemSignBinding;
import com.tron.wallet.db.Controller.AddressController;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import org.tron.api.GrpcAPI;
import org.tron.common.utils.JsonFormat;
import org.tron.common.utils.TransactionDataUtils;
import org.tron.common.utils.TransactionUtils;
import org.tron.net.input.TriggerContractRequest;
import org.tron.protos.Protocol;
import org.tron.protos.contract.BalanceContract;
import org.tron.protos.contract.SmartContractOuterClass;
import org.tron.walletserver.Wallet;
public class TransferView extends PullDetailView {
    private final String TAG;
    private PullItemSignBinding binding;
    private Disposable getAddressDisposable;
    private boolean isHaveError;
    ImageView ivContractAddressError;
    ImageView ivContractTag;
    ImageView ivNoteArrowRight;
    ImageView ivPullTitleError;
    ImageView ivPullTitleRightError;
    ImageView ivReceiveAddressError;
    ImageView ivToAddressContractTag;
    private final ActivityResultLauncher<Intent> lanchConfirm;
    LinearLayout liPullHash;
    LinearLayout liPullTitleError;
    LinearLayout llNote;
    private Wallet loginWallet;
    private final Context mContext;
    private NumberFormat numberFormat;
    private PullDetailActivity pullDetailActivity;
    RelativeLayout rlContract;
    RelativeLayout rlTokenId;
    RxManager rxManager;
    private TokenBean tokenBean;
    private String tokenType;
    private TransferParam transferParam;
    TransferResult transferResult;
    TextView tvContractAddress;
    TextView tvContractAddressError;
    TextView tvContractName;
    TextView tvNote;
    TextView tvPullTitle;
    TextView tvPullTitleError;
    TextView tvReceiveAddress;
    TextView tvReceiveAddressErrorTips;
    TextView tvReceiveAddressName;
    TextView tvReceiveHash;
    TextView tvSendAddress;
    TextView tvSendAddressName;
    TextView tvTokenId;
    TextView tvTokenIdError;

    public TransferView(Context context, final TransferParam transferParam) {
        super(context);
        this.TAG = "TransferView";
        this.isHaveError = false;
        this.mContext = context;
        this.pullDetailActivity = (PullDetailActivity) context;
        this.transferParam = transferParam;
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.numberFormat = numberInstance;
        numberInstance.setMaximumFractionDigits(7);
        RxManager rxManager = new RxManager();
        this.rxManager = rxManager;
        rxManager.on(Event.DEEPLINK_SUCCESS, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$new$0(obj);
            }
        });
        this.lanchConfirm = ((BaseActivity) context).registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult activityResult) {
                Intent data = activityResult.getData();
                int resultCode = activityResult.getResultCode();
                LogUtils.d("TransferView", "onActivityResult  TransferView  " + resultCode);
                if (resultCode != -1) {
                    transferResult = new TransferResult();
                    transferResult.setCode(PullResultCode.FAIL_CANCEL.getCode());
                    transferResult.setMessage(PullResultCode.FAIL_CANCEL.getMessage());
                    transferResult.setActionId(transferParam.getActionId());
                    transferResult.setSuccessful(false);
                } else if (data != null) {
                    String stringExtra = data.getStringExtra(IntentResult.HashKey);
                    String stringExtra2 = data.getStringExtra(IntentResult.ResultKey);
                    String stringExtra3 = data.getStringExtra(IntentResult.ErrorMessageKey);
                    LogUtils.d("TransferView", "onActivityResult  resultKey " + stringExtra2 + "  hash " + stringExtra + "  " + stringExtra3);
                    if (!TextUtils.isEmpty(stringExtra) && IntentResult.Succeeded.equals(stringExtra2)) {
                        Bundle bundle = new Bundle();
                        if (!StringTronUtil.isEmpty(tokenType)) {
                            if (M.M_TRC20.equals(tokenType)) {
                                bundle.putString("token_type", TronConfig.TRC20);
                                AnalyticsHelper.logEvent(AnalyticsHelper.DeepLinkPage.PV_DEEPLINK_REQUEST_SUCCEED_TRANSFER, bundle);
                            } else if (M.M_TRC10.equals(tokenType)) {
                                bundle.putString("token_type", TronConfig.TRC10);
                                AnalyticsHelper.logEvent(AnalyticsHelper.DeepLinkPage.PV_DEEPLINK_REQUEST_SUCCEED_TRANSFER, bundle);
                            } else if (M.M_TRX.equals(tokenType)) {
                                bundle.putString("token_type", "TRX");
                                AnalyticsHelper.logEvent(AnalyticsHelper.DeepLinkPage.PV_DEEPLINK_REQUEST_SUCCEED_TRANSFER, bundle);
                            }
                        }
                        lambda$new$0(stringExtra);
                        return;
                    }
                    Bundle bundle2 = new Bundle();
                    if (!StringTronUtil.isEmpty(tokenType)) {
                        if (M.M_TRC20.equals(tokenType)) {
                            bundle2.putString("token_type", TronConfig.TRC20);
                            AnalyticsHelper.logEvent(AnalyticsHelper.DeepLinkPage.PV_DEEPLINK_REQUEST_FAIL_TRANSFER, bundle2);
                        } else if (M.M_TRC10.equals(tokenType)) {
                            bundle2.putString("token_type", TronConfig.TRC10);
                            AnalyticsHelper.logEvent(AnalyticsHelper.DeepLinkPage.PV_DEEPLINK_REQUEST_FAIL_TRANSFER, bundle2);
                        } else if (M.M_TRX.equals(tokenType)) {
                            bundle2.putString("token_type", "TRX");
                            AnalyticsHelper.logEvent(AnalyticsHelper.DeepLinkPage.PV_DEEPLINK_REQUEST_FAIL_TRANSFER, bundle2);
                        }
                    }
                    title.setText(mContext.getString(R.string.pull_transfer_fail));
                    tvPullTitle.setVisibility(View.GONE);
                    BigDecimal div = BigDecimalUtils.div(new BigDecimal(transferParam.getAmount()), new BigDecimal(Math.pow(10.0d, tokenBean.getPrecision())));
                    numberFormat.setMaximumFractionDigits(tokenBean.getPrecision());
                    TextView textView = tvPullTitleError;
                    StringBuilder sb = new StringBuilder();
                    sb.append(numberFormat.format(div));
                    sb.append(" ");
                    sb.append(TextUtils.isEmpty(tokenBean.getShortName()) ? tokenBean.getName() : tokenBean.getShortName());
                    textView.setText(sb.toString());
                    liPullTitleError.setVisibility(View.VISIBLE);
                    tvPullTitleError.setVisibility(View.VISIBLE);
                    ivPullTitleError.setVisibility(View.GONE);
                    tvPullTitleError.setTextColor(mContext.getResources().getColor(R.color.black_23));
                    tvPullTitleError.setTextSize(1, 18.0f);
                    headerIcon.setImageResource(R.mipmap.ic_unstake_result_fail_all);
                    onTransferFailed(stringExtra3);
                }
            }
        });
    }

    @Override
    public void requestQuit(PullParam pullParam) {
        TransferResult transferResult = this.transferResult;
        if (transferResult == null) {
            super.requestQuit(pullParam);
        } else if (transferResult.isSuccessful()) {
            ((AppCompatActivity) this.activity).moveTaskToBack(true);
            ((AppCompatActivity) this.activity).finish();
        } else {
            ((AppCompatActivity) this.activity).moveTaskToBack(true);
            ((AppCompatActivity) this.activity).finish();
        }
    }

    @Override
    public PullResultCode checkDataValid() {
        return PullResultCode.SUCCESS;
    }

    @Override
    public void init() {
        this.rightBtn.setVisibility(View.GONE);
        if (this.transferParam == null) {
            return;
        }
        View inflate = View.inflate(this.activity, R.layout.pull_item_header, null);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_pull_title);
        this.tvPullTitle = textView;
        textView.setVisibility(View.GONE);
        this.liPullTitleError = (LinearLayout) inflate.findViewById(R.id.li_pull_title_error);
        this.ivPullTitleError = (ImageView) inflate.findViewById(R.id.iv_pull_title_error);
        this.tvPullTitleError = (TextView) inflate.findViewById(R.id.tv_pull_title_error);
        this.ivPullTitleRightError = (ImageView) inflate.findViewById(R.id.iv_pull_title_right_error);
        this.liPullTitleError.setVisibility(View.GONE);
        this.tokenBean = AssetsManager.getInstance().queryToken(this.transferParam.getLoginAddress(), TextUtils.isEmpty(this.transferParam.getContract()) ? this.transferParam.getTokenId() : TextUtils.isEmpty(this.transferParam.getContract()) ? null : this.transferParam.getContract());
        if ((TextUtils.isEmpty(this.transferParam.getTokenId()) || "0".equals(this.transferParam.getTokenId())) && TextUtils.isEmpty(this.transferParam.getContract())) {
            this.title.setText(this.activity.getString(R.string.pull_request_trx_sign));
            this.tokenType = M.M_TRX;
            this.title.setText(this.activity.getString(R.string.pull_request_trx_sign));
            Bundle bundle = new Bundle();
            bundle.putString("token_type", "TRX");
            AnalyticsHelper.logEvent(AnalyticsHelper.DeepLinkPage.PV_DEEPLINK_REQUEST_TRANSFER, bundle);
        } else if (TextUtils.isEmpty(this.transferParam.getContract()) && Double.parseDouble(this.transferParam.getTokenId()) > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            this.title.setText(this.activity.getString(R.string.pull_transfer_trc10));
            this.tokenType = M.M_TRC10;
            this.title.setText(this.activity.getString(R.string.pull_transfer_trc10));
            Bundle bundle2 = new Bundle();
            bundle2.putString("token_type", TronConfig.TRC10);
            AnalyticsHelper.logEvent(AnalyticsHelper.DeepLinkPage.PV_DEEPLINK_REQUEST_TRANSFER, bundle2);
        } else if (!TextUtils.isEmpty(this.transferParam.getContract())) {
            this.tokenType = M.M_TRC20;
            this.title.setText(this.activity.getString(R.string.pull_request_trc20_transfer));
            Bundle bundle3 = new Bundle();
            bundle3.putString("token_type", TronConfig.TRC20);
            AnalyticsHelper.logEvent(AnalyticsHelper.DeepLinkPage.PV_DEEPLINK_REQUEST_TRANSFER, bundle3);
        }
        this.headerIcon.setImageResource(R.mipmap.ic_pull_sign);
        this.headerExtend.setVisibility(View.VISIBLE);
        this.headerExtend.addView(inflate, new FrameLayout.LayoutParams(-1, -2));
        this.contentExtend.setVisibility(View.VISIBLE);
        View inflate2 = View.inflate(this.activity, R.layout.pull_item_sign, null);
        this.binding = PullItemSignBinding.bind(inflate2);
        mappingId();
        this.contentExtend.addView(inflate2, new FrameLayout.LayoutParams(-1, -2));
        this.contentTip.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(this.transferParam.getLoginAddress())) {
            asyncFormatSendAddress(this.transferParam.getLoginAddress());
        }
        try {
            checkIsToAddressContractAddress(this.transferParam.getTo());
        } catch (Exception e) {
            LogUtils.e(e);
        }
        if (!TextUtils.isEmpty(this.transferParam.getTo())) {
            asyncFormatReceiveAddress(this.transferParam.getTo());
        } else {
            this.isHaveError = true;
            this.tvReceiveAddress.setText(this.transferParam.getTo());
            this.tvReceiveAddress.setTextColor(this.mContext.getResources().getColor(R.color.red_EC));
            this.tvReceiveAddressErrorTips.setText(R.string.receiving_address_error);
            this.tvReceiveAddressErrorTips.setVisibility(View.VISIBLE);
            this.ivReceiveAddressError.setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(this.transferParam.getMemo())) {
            this.tvNote.setText(this.transferParam.getMemo());
        } else {
            this.llNote.setVisibility(View.GONE);
        }
        if (TextUtils.isEmpty(this.transferParam.getTokenId()) && TextUtils.isEmpty(this.transferParam.getContract())) {
            this.rlContract.setVisibility(View.GONE);
            this.rlTokenId.setVisibility(View.GONE);
        } else if (TextUtils.isEmpty(this.transferParam.getContract()) && !TextUtils.isEmpty(this.transferParam.getTokenId()) && Double.parseDouble(this.transferParam.getTokenId()) > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            this.rlContract.setVisibility(View.GONE);
            this.rlTokenId.setVisibility(View.VISIBLE);
            this.tvTokenId.setText(this.transferParam.getTokenId());
        } else if (TextUtils.isEmpty(this.transferParam.getContract())) {
            this.isHaveError = true;
        } else {
            this.rlContract.setVisibility(View.VISIBLE);
            this.rlTokenId.setVisibility(View.GONE);
            this.tvContractAddress.setText(this.transferParam.getContract());
            this.ivContractAddressError.setVisibility(View.GONE);
            checkIsValividContractAddress(this.transferParam.getContract());
            try {
                if (!StringTronUtil.isAddressValid(this.transferParam.getContract())) {
                    this.isHaveError = true;
                    this.tvContractAddress.setTextColor(this.mContext.getResources().getColor(R.color.red_EC));
                    this.ivContractTag.setVisibility(View.VISIBLE);
                    this.ivContractAddressError.setVisibility(View.VISIBLE);
                    this.tvContractAddressError.setVisibility(View.VISIBLE);
                }
            } catch (Exception e2) {
                LogUtils.e(e2);
            }
            this.ivContractTag.setVisibility(View.VISIBLE);
        }
        if (!this.isHaveError) {
            this.btnConfirm.setText(this.activity.getString(R.string.pull_next_step));
            this.btnConfirm.setOnClickListener(new NoDoubleClickListener2() {
                @Override
                protected void onNoDoubleClick(View view) {
                    Bundle bundle4 = new Bundle();
                    if (!StringTronUtil.isEmpty(tokenType)) {
                        if (M.M_TRC20.equals(tokenType)) {
                            bundle4.putString("token_type", TronConfig.TRC20);
                            AnalyticsHelper.logEvent(AnalyticsHelper.DeepLinkPage.CLICK_DEEPLINK_REQUEST_CLICKNEXT_TRANSFER, bundle4);
                        } else if (M.M_TRC10.equals(tokenType)) {
                            bundle4.putString("token_type", TronConfig.TRC10);
                            AnalyticsHelper.logEvent(AnalyticsHelper.DeepLinkPage.CLICK_DEEPLINK_REQUEST_CLICKNEXT_TRANSFER, bundle4);
                        } else if (M.M_TRX.equals(tokenType)) {
                            bundle4.putString("token_type", "TRX");
                            AnalyticsHelper.logEvent(AnalyticsHelper.DeepLinkPage.CLICK_DEEPLINK_REQUEST_CLICKNEXT_TRANSFER, bundle4);
                        }
                    }
                    Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
                    if (selectedPublicWallet != null && selectedPublicWallet.isWatchNotPaired()) {
                        PairColdWalletDialog.showUp(view.getContext(), null);
                    } else {
                        transfer();
                    }
                }
            });
            this.btnCancel.setText(this.activity.getString(R.string.cancel));
            this.btnCancel.setOnClickListener(new NoDoubleClickListener2() {
                @Override
                protected void onNoDoubleClick(View view) {
                    TransferView transferView = TransferView.this;
                    transferView.requestQuit(transferView.transferParam);
                }
            });
        } else {
            this.btnCancel.setVisibility(View.GONE);
            this.btnConfirm.setText(this.activity.getString(R.string.pull_back_to_dapp));
            this.btnConfirm.setOnClickListener(new NoDoubleClickListener2() {
                @Override
                protected void onNoDoubleClick(View view) {
                    TransferView transferView = TransferView.this;
                    transferView.requestQuit(transferView.transferParam);
                }
            });
        }
        checkToken(this.transferParam, this.tokenBean);
    }

    private void checkIsValividContractAddress(final String str) {
        Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                Protocol.Account account;
                try {
                    account = TronAPI.queryAccount(StringTronUtil.decode58Check(str), false);
                } catch (Exception e) {
                    LogUtils.e(e);
                    account = null;
                }
                if (!isNullAccount(account) && account.getType().equals(Protocol.AccountType.Contract)) {
                    return true;
                }
                return false;
            }
        }).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<Boolean>() {
            @Override
            public void onFailure(String str2, String str3) {
            }

            @Override
            public void onResponse(String str2, Boolean bool) {
                if (bool.booleanValue()) {
                    ivContractTag.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                if (getAddressDisposable != null) {
                    getAddressDisposable.isDisposed();
                }
                getAddressDisposable = disposable;
            }
        }, "checkIsValividContract"));
    }

    private void checkIsToAddressContractAddress(final String str) {
        Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                Protocol.Account account;
                try {
                    account = TronAPI.queryAccount(StringTronUtil.decode58Check(str), false);
                } catch (Exception e) {
                    LogUtils.e(e);
                    account = null;
                }
                if (!isNullAccount(account) && account.getType().equals(Protocol.AccountType.Contract)) {
                    return true;
                }
                return false;
            }
        }).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<Boolean>() {
            @Override
            public void onFailure(String str2, String str3) {
            }

            @Override
            public void onResponse(String str2, Boolean bool) {
                if (bool.booleanValue()) {
                    ivToAddressContractTag.setVisibility(View.VISIBLE);
                } else {
                    ivToAddressContractTag.setVisibility(View.GONE);
                }
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                if (getAddressDisposable != null) {
                    getAddressDisposable.isDisposed();
                }
                getAddressDisposable = disposable;
            }
        }, "checkIsValividContract"));
    }

    public boolean isNullAccount(Protocol.Account account) {
        return account == null || account.toString().length() <= 0;
    }

    private void checkToken(TransferParam transferParam, TokenBean tokenBean) {
        if (tokenBean != null) {
            updateBalanceState();
        } else {
            requestAsset();
        }
    }

    public void requestAsset() {
        PullDetailActivity pullDetailActivity = this.pullDetailActivity;
        int i = 0;
        if (pullDetailActivity != null) {
            pullDetailActivity.showLoadingDialog(false);
        }
        String str = "";
        if (!M.M_TRX.equals(this.tokenType)) {
            if (M.M_TRC10.equals(this.tokenType)) {
                str = this.transferParam.getTokenId() + "";
                i = 1;
            } else if (M.M_TRC20.equals(this.tokenType)) {
                str = this.transferParam.getContract();
                i = 2;
            }
        }
        AssetsManager.getInstance().requestGetAsset(this.transferParam.getLoginAddress(), i, str).subscribe(new IObserver(new ICallback<AssetsQueryOutput>() {
            @Override
            public void onSubscribe(Disposable disposable) {
            }

            @Override
            public void onResponse(String str2, AssetsQueryOutput assetsQueryOutput) {
                pullDetailActivity.dismissLoadingDialog();
                if (assetsQueryOutput.getCode() == 0 && assetsQueryOutput.data != null) {
                    tokenBean = assetsQueryOutput.getData();
                    updateBalanceState();
                    return;
                }
                updateBalanceStateTokenError();
            }

            @Override
            public void onFailure(String str2, String str3) {
                LogUtils.d("TransferView", "onFailure:  " + str2 + "  " + str3);
                pullDetailActivity.dismissLoadingDialog();
                updateBalanceStateUnknown();
            }
        }, "requestGetAsset"));
    }

    public void updateBalanceStateTokenError() {
        if (M.M_TRC10.equals(this.tokenType)) {
            this.tvTokenIdError.setVisibility(View.VISIBLE);
            this.tvTokenId.setTextColor(this.mContext.getResources().getColor(R.color.red_EC));
            this.headerTip.setVisibility(View.GONE);
            this.tvPullTitle.setVisibility(View.GONE);
            this.liPullTitleError.setVisibility(View.VISIBLE);
            this.ivPullTitleError.setVisibility(View.GONE);
            this.ivPullTitleRightError.setVisibility(View.VISIBLE);
            TextView textView = this.tvPullTitleError;
            textView.setText(this.transferParam.getAmount() + "");
            this.tvPullTitleError.setTextColor(this.mContext.getResources().getColor(R.color.black_23));
            this.tvPullTitleError.setTextSize(1, 18.0f);
            this.ivPullTitleRightError.setOnClickListener(new NoDoubleClickListener2() {
                @Override
                protected void onNoDoubleClick(View view) {
                    new MultiLineTextPopupView.Builder().setAnchor(view).setOffsetX(UIUtils.dip2px(25.0f)).setRequiredWidth((int) (liPullTitleError.getMeasuredWidth() * 0.8f)).addKeyValue(mContext.getResources().getString(R.string.pull_transfer_tokenid_error_tips), "").build(mContext).show();
                }
            });
        } else if (M.M_TRC20.equals(this.tokenType)) {
            this.ivContractAddressError.setVisibility(View.VISIBLE);
            this.tvContractAddress.setText(this.transferParam.getContract());
            this.ivContractAddressError.setVisibility(View.VISIBLE);
            this.tvContractAddressError.setVisibility(View.VISIBLE);
            this.liPullTitleError.setVisibility(View.VISIBLE);
            this.ivPullTitleError.setVisibility(View.GONE);
            this.ivPullTitleRightError.setVisibility(View.VISIBLE);
            TextView textView2 = this.tvPullTitleError;
            textView2.setText(this.transferParam.getAmount() + "");
            this.tvPullTitleError.setTextColor(this.mContext.getResources().getColor(R.color.black_23));
            this.tvPullTitleError.setTextSize(1, 18.0f);
            this.ivPullTitleRightError.setOnClickListener(new NoDoubleClickListener2() {
                @Override
                protected void onNoDoubleClick(View view) {
                    new MultiLineTextPopupView.Builder().setAnchor(view).setOffsetX(UIUtils.dip2px(25.0f)).setRequiredWidth((int) (liPullTitleError.getMeasuredWidth() * 0.8f)).addKeyValue(mContext.getResources().getString(R.string.pull_transfer_contract_error_tips), "").build(mContext).show();
                }
            });
        }
        this.btnCancel.setVisibility(View.GONE);
        this.btnConfirm.setText(this.activity.getString(R.string.pull_back_to_dapp));
        this.btnConfirm.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                ((AppCompatActivity) activity).moveTaskToBack(true);
                ((AppCompatActivity) activity).finish();
            }
        });
    }

    public void updateBalanceStateUnknown() {
        if (M.M_TRC10.equals(this.tokenType)) {
            this.headerTip.setVisibility(View.GONE);
            this.tvPullTitle.setVisibility(View.GONE);
            this.liPullTitleError.setVisibility(View.VISIBLE);
            this.ivPullTitleError.setVisibility(View.GONE);
            this.ivPullTitleRightError.setVisibility(View.GONE);
            TextView textView = this.tvPullTitleError;
            textView.setText(this.transferParam.getAmount() + "");
        } else if (M.M_TRC20.equals(this.tokenType)) {
            this.liPullTitleError.setVisibility(View.VISIBLE);
            this.ivPullTitleError.setVisibility(View.GONE);
            this.ivPullTitleRightError.setVisibility(View.GONE);
            TextView textView2 = this.tvPullTitleError;
            textView2.setText(this.transferParam.getAmount() + "");
        }
        this.btnConfirm.setText(this.activity.getString(R.string.pull_load_fail_retry));
        this.btnConfirm.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                requestAsset();
            }
        });
    }

    public void updateBalanceState() {
        TokenBean tokenBean = this.tokenBean;
        if (tokenBean != null) {
            if (tokenBean.getType() == 0 && this.tokenType.equals(M.M_TRX)) {
                this.tokenBean.setPrecision(6);
                this.tokenBean.setName("TRX");
            }
            BigDecimal div = BigDecimalUtils.div(new BigDecimal(this.transferParam.getAmount()), new BigDecimal(Math.pow(10.0d, this.tokenBean.getPrecision())));
            this.numberFormat.setMaximumFractionDigits(this.tokenBean.getPrecision());
            if (this.tokenBean.getIsOfficial() == -5) {
                this.headerTip.setText((CharSequence) this.mContext.getString(R.string.scam), true);
                this.headerTip.updateWarning(ErrorView.Level.ERROR);
                this.headerTip.setVisibility(View.VISIBLE);
            }
            if (BigDecimalUtils.moreThanOrEqual(new BigDecimal(this.tokenBean.balance), div)) {
                this.tvPullTitle.setVisibility(View.VISIBLE);
                this.tvPullTitle.setTypeface(CustomFontUtils.getTypeface(this.mContext, 1));
                TextView textView = this.tvPullTitle;
                StringBuilder sb = new StringBuilder();
                sb.append(this.numberFormat.format(div));
                sb.append(" ");
                sb.append(TextUtils.isEmpty(this.tokenBean.getShortName()) ? this.tokenBean.getName() : this.tokenBean.getShortName());
                textView.setText(sb.toString());
                this.tvPullTitle.setTextSize(1, 18.0f);
                return;
            }
            this.tvPullTitle.setVisibility(View.GONE);
            this.liPullTitleError.setVisibility(View.VISIBLE);
            this.tvPullTitleError.setVisibility(View.VISIBLE);
            this.tvPullTitleError.setTypeface(CustomFontUtils.getTypeface(this.mContext, 1));
            TextView textView2 = this.tvPullTitleError;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.numberFormat.format(div));
            sb2.append(" ");
            sb2.append(TextUtils.isEmpty(this.tokenBean.getShortName()) ? this.tokenBean.getName() : this.tokenBean.getShortName());
            textView2.setText(sb2.toString());
            this.tvPullTitleError.setTextColor(this.mContext.getResources().getColor(R.color.red_EC));
            this.headerTip.setText((CharSequence) this.mContext.getString(R.string.pull_transfer_insufficient_token), true);
            this.tvPullTitleError.setTextSize(1, 18.0f);
            this.headerTip.updateWarning(ErrorView.Level.ERROR);
            this.headerTip.setVisibility(View.VISIBLE);
            this.btnCancel.setVisibility(View.GONE);
            this.btnConfirm.setVisibility(View.VISIBLE);
            this.btnConfirm.setText(this.activity.getString(R.string.pull_back_to_dapp));
            this.btnConfirm.setOnClickListener(new NoDoubleClickListener2() {
                @Override
                protected void onNoDoubleClick(View view) {
                    ((AppCompatActivity) activity).moveTaskToBack(true);
                    ((AppCompatActivity) activity).finish();
                }
            });
        }
    }

    @Override
    public void deInit() {
        Disposable disposable = this.getAddressDisposable;
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.getAddressDisposable.dispose();
    }

    private void asyncFormatSendAddress(final String str) {
        Observable.fromCallable(new Callable() {
            @Override
            public final Object call() {
                return TransferView.lambda$asyncFormatSendAddress$1(str);
            }
        }).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<String>() {
            @Override
            public void onResponse(String str2, String str3) {
                if (StringTronUtil.isEmpty(str3)) {
                    tvSendAddress.setText(str);
                    tvSendAddress.setTextColor(mContext.getResources().getColor(R.color.black_23));
                    tvSendAddressName.setVisibility(View.GONE);
                    return;
                }
                tvSendAddressName.setText(str3);
                TextView textView = tvSendAddress;
                textView.setText("(" + str + ")");
                tvSendAddress.setVisibility(View.VISIBLE);
                tvSendAddressName.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(String str2, String str3) {
                tvSendAddress.setText(str);
                tvSendAddress.setVisibility(View.GONE);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                if (getAddressDisposable != null) {
                    getAddressDisposable.isDisposed();
                }
                getAddressDisposable = disposable;
            }
        }, "formatAddress"));
    }

    public static String lambda$asyncFormatSendAddress$1(String str) throws Exception {
        String nameByAddress = AddressController.getInstance(AppContextUtil.getContext()).getNameByAddress(str);
        if (nameByAddress == null) {
            Wallet walletForAddress = WalletUtils.getWalletForAddress(str);
            return walletForAddress == null ? "" : walletForAddress.getWalletName();
        }
        return nameByAddress;
    }

    private void asyncFormatReceiveAddress(final String str) {
        Observable.fromCallable(new Callable() {
            @Override
            public final Object call() {
                return TransferView.lambda$asyncFormatReceiveAddress$2(str);
            }
        }).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<String>() {
            @Override
            public void onResponse(String str2, String str3) {
                if (StringTronUtil.isEmpty(str3)) {
                    tvReceiveAddress.setText(str);
                    tvReceiveAddress.setTextColor(mContext.getResources().getColor(R.color.black_23));
                    tvReceiveAddressName.setVisibility(View.GONE);
                    return;
                }
                tvReceiveAddressName.setText(str3);
                tvReceiveAddressName.setVisibility(View.VISIBLE);
                TextView textView = tvReceiveAddress;
                textView.setText("(" + str + ")");
                tvReceiveAddress.setTextColor(mContext.getResources().getColor(R.color.gray_6D778C));
            }

            @Override
            public void onFailure(String str2, String str3) {
                tvSendAddress.setText(StringTronUtil.indentAddress(str, 6));
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                if (getAddressDisposable != null) {
                    getAddressDisposable.isDisposed();
                }
                getAddressDisposable = disposable;
            }
        }, "formatAddress"));
    }

    public static String lambda$asyncFormatReceiveAddress$2(String str) throws Exception {
        String nameByAddress = AddressController.getInstance(AppContextUtil.getContext()).getNameByAddress(str);
        if (nameByAddress == null) {
            Wallet walletForAddress = WalletUtils.getWalletForAddress(str);
            return walletForAddress == null ? "" : walletForAddress.getWalletName();
        }
        return nameByAddress;
    }

    private void openDApp() {
        Intent intent = new Intent(this.activity, MainTabActivity.class);
        intent.addFlags(67108864);
        intent.setAction(PullActivity.ACTION);
        intent.putExtra(PullConstants.ACTION, PullAction.ACTION_OPEN_DAPP.getAction());
        intent.putExtra("url", this.transferParam.getUrl());
        this.activity.startActivity(intent);
    }

    public void transfer() {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        final Protocol.Transaction[] transactionArr = {null};
        final long[] jArr = {0};
        this.btnConfirm.setEnabled(false);
        final BigDecimal bigDecimal = BigDecimalUtils.toBigDecimal(this.transferParam.getAmount());
        Observable.create(new ObservableOnSubscribe<Protocol.Transaction>() {
            @Override
            public void subscribe(ObservableEmitter<Protocol.Transaction> observableEmitter) throws Exception {
                BalanceContract.TransferContract createTransferContract;
                if (transferParam instanceof SignParam) {
                    observableEmitter.onNext(WalletUtils.packTransaction(((SignParam) transferParam).getData()));
                    return;
                }
                byte[] decodeFromBase58Check = StringTronUtil.decodeFromBase58Check(transferParam.getFrom());
                byte[] decodeFromBase58Check2 = StringTronUtil.decodeFromBase58Check(transferParam.getTo());
                if (tokenBean.getType() == 0) {
                    WalletUtils.getSelectedWallet().getWalletName();
                    GrpcAPI.TransactionExtention createTransaction4Transfer2 = TronAPI.createTransaction4Transfer2(TronAPI.createTransferContract(decodeFromBase58Check2, decodeFromBase58Check, Long.parseLong(transferParam.getAmount())));
                    if (createTransaction4Transfer2 != null && createTransaction4Transfer2.hasResult() && createTransaction4Transfer2.getTransaction().toString().length() > 0) {
                        transactionArr[0] = createTransaction4Transfer2.getTransaction();
                        T.toAddress = transferParam.getTo();
                        T.amount = BigDecimalUtils.toString(transferParam.getAmount());
                        T.note = transferParam.getMemo();
                        if (!transferParam.getMemo().equals("")) {
                            Protocol.Transaction[] transactionArr2 = transactionArr;
                            transactionArr2[0] = TransactionUtils.addMemo(transactionArr2[0], transferParam.getMemo());
                        }
                        atomicBoolean.set(true);
                        observableEmitter.onNext(transactionArr[0]);
                        return;
                    }
                    if (createTransaction4Transfer2 != null && createTransaction4Transfer2.getResult().getMessage() != null && !StringTronUtil.isNullOrEmpty(createTransaction4Transfer2.getResult().getMessage().toString())) {
                        SentryUtil.captureMessage("Transfer TRX:" + createTransaction4Transfer2.getResult().getMessage().toString(Charset.forName("utf-8")) + JsonFormat.printToString(createTransferContract));
                    }
                    observableEmitter.onError(new Throwable(mContext.getString(R.string.failed_create_transaction)));
                } else if (tokenBean.getType() == 1) {
                    long longValue = bigDecimal.longValue();
                    if (tokenBean.getPrecision() != 0) {
                        Math.pow(10.0d, tokenBean.getPrecision());
                    }
                    GrpcAPI.TransactionExtention createTransferAssetTransaction = TronAPI.createTransferAssetTransaction(TronAPI.createTransferAssetContract(decodeFromBase58Check2, (tokenBean.getId() + "").getBytes(), decodeFromBase58Check, longValue));
                    if (createTransferAssetTransaction.hasResult() && createTransferAssetTransaction.getTransaction().toString().length() > 0) {
                        transactionArr[0] = createTransferAssetTransaction.getTransaction();
                        T.toAddress = transferParam.getTo();
                        T.amount = BigDecimalUtils.toString(bigDecimal);
                        observableEmitter.onNext(transactionArr[0]);
                        return;
                    }
                    observableEmitter.onError(new Throwable(mContext.getString(R.string.failed_create_transaction)));
                } else if (tokenBean.getType() == 2) {
                    BigDecimal bigDecimal2 = bigDecimal;
                    if (tokenBean.getPrecision() != 0) {
                        BigDecimal.valueOf(Math.pow(10.0d, tokenBean.getPrecision()));
                    }
                    String contractAddress = tokenBean.getContractAddress();
                    TriggerContractRequest triggerContractRequest = new TriggerContractRequest();
                    triggerContractRequest.setMethodStr(TransactionDataUtils.transferMethod);
                    triggerContractRequest.setArgsStr(transferParam.getTo() + "," + BigDecimalUtils.toString(bigDecimal2));
                    triggerContractRequest.setFeeLimit(TronAPI.getFeeLimit());
                    triggerContractRequest.setContractAddrStr(contractAddress);
                    triggerContractRequest.setHex(false);
                    triggerContractRequest.setTokenCallValue(0L);
                    triggerContractRequest.setOwer(decodeFromBase58Check);
                    GrpcAPI.TransactionExtention triggerContract2 = TronAPI.triggerContract2(triggerContractRequest);
                    TriggerUtils.addRequestABIMethod(TransactionDataUtils.transferMethod, triggerContract2);
                    if (triggerContract2 != null && triggerContract2.hasResult()) {
                        transactionArr[0] = triggerContract2.getTransaction();
                        T.toAddress = transferParam.getTo();
                        T.amount = BigDecimalUtils.toString(bigDecimal);
                        GrpcAPI.TransactionExtention triggerConstantContractOnline = TronAPI.triggerConstantContractOnline((SmartContractOuterClass.TriggerSmartContract) TransactionUtils.unpackContract(transactionArr[0].getRawData().getContract(0), SmartContractOuterClass.TriggerSmartContract.class));
                        if (triggerConstantContractOnline != null) {
                            jArr[0] = triggerConstantContractOnline.getEnergyUsed();
                        }
                        observableEmitter.onNext(transactionArr[0]);
                        return;
                    }
                    observableEmitter.onError(new Throwable(mContext.getString(R.string.failed_create_transaction)));
                } else {
                    observableEmitter.onError(new Throwable("Unsupported Transaction Type"));
                }
            }
        }).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<Protocol.Transaction>() {
            @Override
            public void onSubscribe(Disposable disposable) {
            }

            @Override
            public void onResponse(String str, Protocol.Transaction transaction) {
                if (transaction != null) {
                    WalletUtils.getWalletForAddress(transferParam.getFrom());
                    if (!StringTronUtil.isEmpty(transferParam.getMemo())) {
                        transaction = TransactionUtils.addMemo(transaction, transferParam.getMemo());
                    }
                    lanchConfirm.launch(DeepLinkDappConfirmActivity.startForResultIntent((Activity) mContext, "", "", transaction, false, WalletUtils.getSelectedWallet(), ConfirmModel.SAFE, false, null, null, -2, TransactionDataUtils.transferMethod, null));
                    btnConfirm.setEnabled(true);
                }
            }

            @Override
            public void onFailure(String str, String str2) {
                btnConfirm.setText(R.string.pull_create_transction_fail_retry);
                btnConfirm.setEnabled(true);
                LogUtils.e("TransferView", str + "  " + str2);
            }
        }, "formatAddress"));
    }

    public void lambda$new$0(final String str) {
        this.headerIcon.setImageResource(R.mipmap.ic_unstake_result_success);
        this.title.setText(this.activity.getString(R.string.pull_transfer_success));
        this.contentTip.setVisibility(View.GONE);
        this.btnCancel.setVisibility(View.GONE);
        this.btnConfirm.setText(this.activity.getString(R.string.pull_back_to_dapp));
        this.liPullHash.setVisibility(View.VISIBLE);
        this.tvReceiveHash.setText(str);
        this.tvReceiveHash.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                String str2;
                String str3 = TronConfig.TRONSCANHOST_MAINCHAIN + str;
                if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
                    str2 = str3 + "?lang=zh";
                } else {
                    str2 = str3 + "?lang=en";
                }
                CommonWebActivityV3.start(mContext, str2, "2".equals(SpAPI.THIS.useLanguage()) ? StringTronUtil.getResString(R.string.transfer_doc) : "Transaction Details", -2, true);
            }
        });
        TransferResult transferResult = new TransferResult();
        this.transferResult = transferResult;
        transferResult.setActionId(this.transferParam.getActionId());
        this.transferResult.setCode(PullResultCode.SUCCESS.getCode());
        this.transferResult.setMessage(PullResultCode.SUCCESS.getMessage());
        this.transferResult.setTransactionHash(str);
        this.transferResult.setSuccessful(true);
        PullResultHelper.callBackToDApp(this.transferParam.getCallbackUrl(), JSON.toJSONString(this.transferResult));
        this.btnConfirm.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                ((AppCompatActivity) activity).moveTaskToBack(true);
                ((AppCompatActivity) activity).finish();
            }
        });
    }

    public void onTransferFailed(String str) {
        this.contentTip.setVisibility(View.GONE);
        this.btnCancel.setVisibility(View.GONE);
        this.btnConfirm.setText(this.activity.getString(R.string.pull_back_to_dapp));
        if (this.transferResult == null) {
            TransferResult transferResult = new TransferResult();
            this.transferResult = transferResult;
            transferResult.setSuccessful(false);
            this.transferResult.setActionId(this.transferParam.getActionId());
            this.transferResult.setCode(PullResultCode.FAIL_TRANSACTION_BROADCAST_FAIL.getCode());
            TransferResult transferResult2 = this.transferResult;
            if (StringTronUtil.isEmpty(str)) {
                str = PullResultCode.FAIL_TRANSACTION_BROADCAST_FAIL.getMessage();
            }
            transferResult2.setMessage(str);
        }
        PullResultHelper.callBackToDApp(this.transferParam.getCallbackUrl(), JSON.toJSONString(this.transferResult));
        setBackToDApp(this.transferResult);
    }

    public void mappingId() {
        this.tvSendAddress = this.binding.tvSendAddress;
        this.tvSendAddressName = this.binding.tvSendAddressName;
        this.tvReceiveAddressName = this.binding.tvReceiveAddressName;
        this.tvReceiveAddress = this.binding.tvReceiveAddress;
        this.ivToAddressContractTag = this.binding.tvReceiveAddressContractTag;
        this.llNote = this.binding.llPullNote;
        this.tvNote = this.binding.tvNote;
        this.ivNoteArrowRight = this.binding.ivArrowRight;
        this.rlContract = this.binding.rlContract;
        this.ivContractAddressError = this.binding.ivContractAddressError;
        this.tvContractAddress = this.binding.tvContractAddress;
        this.tvContractName = this.binding.tvContractName;
        this.ivContractTag = this.binding.ivContractTag;
        this.liPullHash = this.binding.llPullHash;
        this.tvReceiveHash = this.binding.tvReceiveHash;
        this.tvReceiveAddressErrorTips = this.binding.tvReceiveAddressErrorTips;
        this.ivReceiveAddressError = this.binding.ivReceiveAddressError;
        this.tvContractAddressError = this.binding.tvContractAddressErrorTips;
        this.rlTokenId = this.binding.rlTokenId;
        this.tvTokenId = this.binding.tvTokenId;
        this.tvTokenIdError = this.binding.tvTokenidErrorTips;
    }
}
