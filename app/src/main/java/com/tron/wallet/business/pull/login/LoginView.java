package com.tron.wallet.business.pull.login;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.business.maintab.MainTabActivity;
import com.tron.wallet.business.pull.PullAction;
import com.tron.wallet.business.pull.PullActivity;
import com.tron.wallet.business.pull.PullConstants;
import com.tron.wallet.business.pull.PullResultCode;
import com.tron.wallet.business.pull.PullResultHelper;
import com.tron.wallet.business.pull.PullResultInterface;
import com.tron.wallet.business.pull.component.PullDetailActivity;
import com.tron.wallet.business.pull.component.PullDetailView;
import com.tron.wallet.business.pull.login.LoginView;
import com.tron.wallet.business.walletmanager.pairwallet.PairColdWalletDialog;
import com.tron.wallet.business.walletmanager.selectwallet.search.SelectWalletBottomPopup;
import com.tron.wallet.common.components.ErrorView;
import com.tron.wallet.common.utils.AddressNameUtils;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.PullItemLoginBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.Callable;
import org.tron.walletserver.Wallet;
public class LoginView extends PullDetailView {
    private String address;
    private PullItemLoginBinding binding;
    private Disposable getAddressDisposable;
    View ivPullAddress;
    private LoginParam loginParam;
    TextView tvPullAddress;
    TextView tvPullAddressName;

    public LoginView(Context context, LoginParam loginParam) {
        super(context);
        this.loginParam = loginParam;
    }

    @Override
    public PullResultCode checkDataValid() {
        return PullResultCode.SUCCESS;
    }

    @Override
    public void init() {
        this.rightBtn.setVisibility(View.VISIBLE);
        this.rightBtn.setText(this.activity.getString(R.string.pull_cancel_login));
        this.rightBtn.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                LoginView loginView = LoginView.this;
                loginView.requestQuit(loginView.loginParam);
                AnalyticsHelper.logEvent(AnalyticsHelper.DeepLinkPage.CLICK_DEEPLINK_LOGIN_CANCEL);
            }
        });
        this.headerIcon.setImageResource(R.mipmap.ic_pull_login);
        this.title.setText(this.activity.getString(R.string.pull_request_login));
        this.contentExtend.setVisibility(View.VISIBLE);
        View inflate = View.inflate(this.activity, R.layout.pull_item_login, null);
        this.binding = PullItemLoginBinding.bind(inflate);
        mappingId();
        this.contentExtend.addView(inflate, new FrameLayout.LayoutParams(-1, -2));
        String address = WalletUtils.getSelectedPublicWallet().getAddress();
        this.address = address;
        asyncFormatAddress(address);
        this.ivPullAddress.setOnClickListener(new fun2());
        this.contentTip.setVisibility(View.VISIBLE);
        this.contentTip.setText((CharSequence) this.activity.getString(R.string.pull_login_tip), true);
        this.contentTip.updateWarning(ErrorView.Level.WARNING);
        if (!StringTronUtil.isEmpty(this.loginParam.getUrl())) {
            this.btnConfirm.setText(this.activity.getString(R.string.pull_login_open_dapp));
            this.btnConfirm.setOnClickListener(new NoDoubleClickListener2() {
                @Override
                protected void onNoDoubleClick(View view) {
                    openDApp();
                }
            });
            this.btnCancel.setText(this.activity.getString(R.string.pull_login_confirm));
            this.btnCancel.setOnClickListener(new NoDoubleClickListener2() {
                @Override
                protected void onNoDoubleClick(View view) {
                    Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
                    if (selectedPublicWallet != null && selectedPublicWallet.isWatchNotPaired()) {
                        PairColdWalletDialog.showUp(view.getContext(), null);
                        return;
                    }
                    AnalyticsHelper.logEvent(AnalyticsHelper.DeepLinkPage.CLICK_DEEPLINK_LOGIN_CONFIRM);
                    login();
                }
            });
        } else {
            this.btnCancel.setVisibility(View.GONE);
            this.btnConfirm.setText(this.activity.getString(R.string.pull_login_confirm));
            this.btnConfirm.setOnClickListener(new NoDoubleClickListener2() {
                @Override
                protected void onNoDoubleClick(View view) {
                    login();
                }
            });
        }
        AnalyticsHelper.logEvent(AnalyticsHelper.DeepLinkPage.ENTER_DEEPLINK_LOGIN);
    }

    public class fun2 extends NoDoubleClickListener2 {
        fun2() {
        }

        @Override
        protected void onNoDoubleClick(View view) {
            SelectWalletBottomPopup.showPopup(activity, WalletUtils.getSelectedPublicWallet(), new SelectWalletBottomPopup.OnClickListener() {
                @Override
                public final void onClick(Wallet wallet) {
                    LoginView.2.this.lambda$onNoDoubleClick$0(wallet);
                }
            }, null);
        }

        public void lambda$onNoDoubleClick$0(Wallet wallet) {
            if (wallet.getAddress().equals(address)) {
                return;
            }
            address = wallet.getAddress();
            LoginView loginView = LoginView.this;
            loginView.asyncFormatAddress(loginView.address);
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

    public void asyncFormatAddress(final String str) {
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
                tvPullAddressName.setText(str3);
                tvPullAddressName.setSingleLine(true);
                tvPullAddress.setVisibility(View.VISIBLE);
                TextView textView = tvPullAddress;
                textView.setText("(" + str + ")");
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

    public void openDApp() {
        Intent intent = new Intent(this.activity, MainTabActivity.class);
        intent.addFlags(67108864);
        intent.setAction(PullActivity.ACTION);
        intent.putExtra(PullConstants.ACTION, PullAction.ACTION_OPEN_DAPP.getAction());
        intent.putExtra("url", this.loginParam.getUrl());
        this.activity.startActivity(intent);
        LoginResult loginResult = new LoginResult();
        loginResult.setActionId(this.loginParam.getActionId());
        loginResult.setCode(PullResultCode.FAIL_OPEN_IN_TRON.getCode());
        loginResult.setMessage(PullResultCode.FAIL_OPEN_IN_TRON.getMessage());
        PullResultHelper.callBackToDApp(this.loginParam.getCallbackUrl(), loginResult);
        AnalyticsHelper.logEvent(AnalyticsHelper.DeepLinkPage.CLICK_DEEPLINK_LOGIN_OPEN);
    }

    public void login() {
        PullDetailActivity pullDetailActivity = this.activity instanceof PullDetailActivity ? (PullDetailActivity) this.activity : null;
        if (pullDetailActivity != null) {
            pullDetailActivity.showLoadingDialog();
        }
        LoginResult loginResult = new LoginResult();
        loginResult.setAddress(WalletUtils.getSelectedPublicWallet().getAddress());
        loginResult.setActionId(this.loginParam.getActionId());
        loginResult.setCode(PullResultCode.SUCCESS.getCode());
        loginResult.setMessage(PullResultCode.SUCCESS.getMessage());
        PullResultHelper.callBackToDApp(this.loginParam.getCallbackUrl(), loginResult, new fun7(pullDetailActivity, loginResult));
        AnalyticsHelper.logEvent(AnalyticsHelper.DeepLinkPage.DEEPLINK_LOGINED);
    }

    public class fun7 implements PullResultInterface {
        final PullDetailActivity val$finalPullDetailActivity;
        final LoginResult val$loginResult;

        fun7(PullDetailActivity pullDetailActivity, LoginResult loginResult) {
            this.val$finalPullDetailActivity = pullDetailActivity;
            this.val$loginResult = loginResult;
        }

        @Override
        public void onDAppResultResponse() {
            PullDetailActivity pullDetailActivity = this.val$finalPullDetailActivity;
            if (pullDetailActivity != null) {
                pullDetailActivity.dismissLoadingDialog();
            }
            TextView textView = title;
            final LoginResult loginResult = this.val$loginResult;
            textView.post(new Runnable() {
                @Override
                public final void run() {
                    LoginView.7.this.lambda$onDAppResultResponse$0(loginResult);
                }
            });
        }

        public void lambda$onDAppResultResponse$0(LoginResult loginResult) {
            rightBtn.setVisibility(View.GONE);
            headerIcon.setImageResource(R.mipmap.ic_unstake_result_success);
            title.setText(activity.getString(R.string.pull_login_success));
            ivPullAddress.setVisibility(View.GONE);
            contentTip.setVisibility(View.GONE);
            btnCancel.setVisibility(View.GONE);
            setBackToDApp(loginResult);
        }

        @Override
        public void onDAppResultFailure() {
            try {
                PullDetailActivity pullDetailActivity = this.val$finalPullDetailActivity;
                if (pullDetailActivity != null) {
                    pullDetailActivity.dismissLoadingDialog();
                }
                title.post(new Runnable() {
                    @Override
                    public final void run() {
                        IToast.getIToast().show(R.string.net_error);
                    }
                });
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
    }

    public void mappingId() {
        this.tvPullAddressName = this.binding.tvPullAddressName;
        this.tvPullAddress = this.binding.tvPullAddress;
        this.ivPullAddress = this.binding.ivPullAddress;
    }
}
