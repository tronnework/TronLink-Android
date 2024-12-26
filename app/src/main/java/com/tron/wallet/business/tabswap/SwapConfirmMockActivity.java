package com.tron.wallet.business.tabswap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.alibaba.fastjson.JSON;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.parambuilder.BaseConfirmTransParamBuilder;
import com.tron.wallet.business.confirm.parambuilder.utils.ParamBuildUtils;
import com.tron.wallet.business.tabswap.SwapConfirmMockContract;
import com.tron.wallet.business.tabswap.bean.SwapConfirmBean;
import com.tron.wallet.business.tabswap.bean.SwapInfoOutput;
import com.tron.wallet.business.tabswap.bean.SwapTokenBean;
import com.tron.wallet.business.tabswap.bean.SwapTokenInfoBean;
import com.tron.wallet.common.utils.ToastUtil;
import com.tron.wallet.databinding.ActivitySwapConfirmMockBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.util.List;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class SwapConfirmMockActivity extends BaseActivity<SwapConfirmMockPresenter, SwapConfirmMockModel> implements SwapConfirmMockContract.View {
    private static final String KEY_CONTRACT_ADDRESS = "key_contract_address";
    private static final String KEY_ROUTER_INDEX = "key_router_index";
    private static final String KEY_SWAP_OUTPUT = "key_swap_output";
    private static final String KEY_TOKEN_FROM = "key_token_from";
    private static final String KEY_TOKEN_TO = "key_token_to";
    public static final int REQUEST_CODE_APPROVE = 17;
    private static final int REQUEST_CODE_SWAP = 18;
    private ActivitySwapConfirmMockBinding binding;
    private String contractAddress;
    private String selectAddress;
    private int selectedRouterIndex;
    private Wallet selectedWallet;
    private SwapInfoOutput swapInfoOutput;
    private SwapTokenBean tokenFrom;
    private SwapTokenBean tokenTo;

    public void mappingId() {
    }

    public static void start(Context context, String str, int i, String str2, String str3, String str4) {
        Intent intent = new Intent(context, SwapConfirmMockActivity.class);
        intent.putExtra(KEY_SWAP_OUTPUT, str);
        intent.putExtra(KEY_ROUTER_INDEX, i);
        intent.putExtra(KEY_TOKEN_FROM, str2);
        intent.putExtra(KEY_TOKEN_TO, str3);
        intent.putExtra(KEY_CONTRACT_ADDRESS, str4);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        ActivitySwapConfirmMockBinding inflate = ActivitySwapConfirmMockBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 0);
        mappingId();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    @Override
    protected void processData() {
        String stringExtra = getIntent().getStringExtra(KEY_SWAP_OUTPUT);
        String stringExtra2 = getIntent().getStringExtra(KEY_TOKEN_FROM);
        String stringExtra3 = getIntent().getStringExtra(KEY_TOKEN_TO);
        this.contractAddress = getIntent().getStringExtra(KEY_CONTRACT_ADDRESS);
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        this.selectedWallet = selectedWallet;
        this.selectAddress = selectedWallet.getAddress();
        this.selectedRouterIndex = getIntent().getIntExtra(KEY_ROUTER_INDEX, 0);
        try {
            this.swapInfoOutput = (SwapInfoOutput) JSON.parseObject(stringExtra, SwapInfoOutput.class);
            this.tokenFrom = (SwapTokenBean) JSON.parseObject(stringExtra2, SwapTokenBean.class);
            this.tokenTo = (SwapTokenBean) JSON.parseObject(stringExtra3, SwapTokenBean.class);
        } catch (Exception e) {
            LogUtils.e(e);
        }
        ((SwapConfirmMockPresenter) this.mPresenter).approve("-1", this.contractAddress, this.tokenFrom.getToken(), WalletUtils.getSelectedWallet().getAddress());
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 17) {
            if (i == 18) {
                finish();
            } else {
                finish();
            }
        } else if (i2 == 0) {
            finish();
        } else if (i2 == -1) {
            requestCheckAppoved();
        } else {
            finish();
        }
    }

    private void requestCheckAppoved() {
        showLoadingDialog(getString(R.string.swap_querying));
        ((SwapConfirmMockPresenter) this.mPresenter).requestCheckApproved(this.tokenFrom, this.selectAddress);
    }

    @Override
    public void requestSwap() {
        Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$requestSwap$0(observableEmitter);
            }
        }).compose(RxSchedulers.io_main()).subscribe(new Observer<BaseConfirmTransParamBuilder>() {
            @Override
            public void onComplete() {
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                ((SwapConfirmMockPresenter) mPresenter).addDisposable(disposable);
            }

            @Override
            public void onNext(BaseConfirmTransParamBuilder baseConfirmTransParamBuilder) {
                SwapConfirmMockActivity swapConfirmMockActivity = SwapConfirmMockActivity.this;
                ConfirmTransactionNewActivity.startForResult(swapConfirmMockActivity, baseConfirmTransParamBuilder, 18, swapConfirmMockActivity.selectedWallet.isSamsungWallet());
                dismissLoadingDialog();
            }

            @Override
            public void onError(Throwable th) {
                if (th.getMessage() != null) {
                    ToastUtil.getInstance().showToast((Activity) SwapConfirmMockActivity.this, th.getMessage() != null ? th.getMessage() : getString(R.string.create_transaction_fail));
                }
                dismissLoadingDialog();
            }
        });
    }

    public void lambda$requestSwap$0(ObservableEmitter observableEmitter) throws Exception {
        Protocol.Account account = WalletUtils.getAccount(this, this.selectedWallet.getWalletName());
        if (account == null || account.toString().length() == 0) {
            observableEmitter.onError(new Exception(getString(R.string.swap_not_support_inactive)));
            return;
        }
        boolean z = account != null && WalletUtils.checkHaveOwnerPermissions(this.selectAddress, account.getOwnerPermission());
        if (!z) {
            observableEmitter.onError(new Exception(getString(R.string.err_swap_no_permission)));
            return;
        }
        List<Protocol.Transaction> submitSwap = ((SwapConfirmMockModel) this.mModel).submitSwap(this.swapInfoOutput, this.selectedRouterIndex, this.tokenFrom, this.tokenTo, this.selectedWallet);
        if (submitSwap.isEmpty()) {
            observableEmitter.onError(new Exception(getString(R.string.create_transaction_fail)));
            return;
        }
        submitSwap.get(submitSwap.size() - 1);
        SwapInfoOutput.InfoData infoData = this.swapInfoOutput.getData().get(this.selectedRouterIndex);
        SwapConfirmBean fromSwapTokenInfo = SwapConfirmBean.fromSwapTokenInfo(SwapTokenInfoBean.fromSwapInfoOutput(infoData));
        fromSwapTokenInfo.setTokenFrom(this.tokenFrom);
        fromSwapTokenInfo.setTokenTo(this.tokenTo);
        fromSwapTokenInfo.setAmountLeft(infoData.getAmountIn());
        fromSwapTokenInfo.setAmountRight(infoData.getAmountOut());
        observableEmitter.onNext(ParamBuildUtils.getSwapParamBuilder(z, submitSwap, fromSwapTokenInfo));
        observableEmitter.onComplete();
    }

    @Override
    public void startConfirmApprove(GrpcAPI.TransactionExtention transactionExtention) {
        try {
            ConfirmTransactionNewActivity.startForResult(this, ParamBuildUtils.getApproveParamBuilder(transactionExtention.getTransaction(), WalletUtils.getSelectedWallet().getAddress(), "0", SwapTokenBean.toTokenBean(this.tokenFrom), "TriggerSmartContract", 38, 1, null, this.contractAddress, null, R.string.confirmtransaction), 17, this.selectedWallet.getCreateType() == 7);
        } catch (Exception e) {
            LogUtils.e(e);
            ToastUtil.getInstance().showToast((Activity) this, getString(R.string.swap_approve_failed));
            finish();
        }
    }
}
