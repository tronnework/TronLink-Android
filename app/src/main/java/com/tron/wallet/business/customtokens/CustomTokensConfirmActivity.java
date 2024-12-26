package com.tron.wallet.business.customtokens;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.AssetsConfig;
import com.tron.wallet.business.addassets2.MyAssetsModel;
import com.tron.wallet.business.addassets2.WatchWalletVerifier;
import com.tron.wallet.business.addassets2.adapter.AssetsListAdapter;
import com.tron.wallet.business.addassets2.bean.AssetsDataOutput;
import com.tron.wallet.business.addassets2.bean.NftDataOutput;
import com.tron.wallet.business.customtokens.CustomTokensConfirmActivity;
import com.tron.wallet.business.customtokens.bean.AddCustomTokenOutput;
import com.tron.wallet.business.customtokens.bean.CustomTokenBean;
import com.tron.wallet.business.customtokens.bean.CustomTokenStatus;
import com.tron.wallet.business.maintab.MainTabActivity;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.CustomTokenNoFunctionView;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.AcCustomTokensConfirmBinding;
import com.tronlinkpro.wallet.R;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
public class CustomTokensConfirmActivity extends BaseActivity<EmptyPresenter, CustomTokensModel> {
    private AcCustomTokensConfirmBinding binding;
    Button btnSave;
    private CustomTokenBean customTokenBean;
    private AssetsListAdapter.ViewHolder itemViewHolder;
    CustomTokenNoFunctionView llTips;
    private Disposable saveCustomTokenDisposable;
    private Disposable syncMyAssetsDisposable;
    private TokenBean tokenBean;
    View tokenView;
    View tvTipTag;

    public static void startActivity(Context context, CustomTokenBean customTokenBean) {
        Intent intent = new Intent();
        intent.putExtra(TronConfig.TRC, customTokenBean);
        intent.setClass(context, CustomTokensConfirmActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        AcCustomTokensConfirmBinding inflate = AcCustomTokensConfirmBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 1);
        setTitle(getResources().getString(R.string.custom_tokens));
        setCommonTitle2(getString(R.string.step_2_2));
        mappingId();
        setClick();
    }

    public void mappingId() {
        this.tvTipTag = this.binding.tvTipError;
        this.llTips = this.binding.llTips;
        this.tokenView = this.binding.rlTokenInfo.getRoot();
        this.btnSave = this.binding.confirm;
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        AnalyticsHelper.logEvent(AnalyticsHelper.CustomTokenPage.CLICK_CUSTOM_TOKEN_CONFIRM_PAGE_BACK);
        finish();
    }

    @Override
    protected void processData() {
        CustomTokenBean customTokenBean = (CustomTokenBean) getIntent().getParcelableExtra(TronConfig.TRC);
        this.customTokenBean = customTokenBean;
        this.tokenBean = customTokenBean.getAssetInfo();
        initTokenItemView();
        initErrorTipView();
    }

    private void initTokenItemView() {
        AssetsListAdapter.ViewHolder viewHolder = new AssetsListAdapter.ViewHolder(this.tokenView);
        this.itemViewHolder = viewHolder;
        viewHolder.itemView.setBackground(getResources().getDrawable(R.drawable.roundborder_f7f8fa_radius_10));
        this.itemViewHolder.onBind(this, this.tokenBean, 0, 0, AssetsListAdapter.TagType.SHOW_ALL, 1);
    }

    private void initErrorTipView() {
        String[] split;
        ArrayList arrayList = new ArrayList();
        String noFunctions = this.customTokenBean.getNoFunctions();
        if (!StringTronUtil.isEmpty(noFunctions) && (split = noFunctions.trim().split(",")) != null) {
            for (String str : split) {
                int noFunctionErrorStrId = CustomTokenStatus.getNoFunctionErrorStrId(str);
                if (noFunctionErrorStrId != 0) {
                    arrayList.add(getResources().getString(noFunctionErrorStrId));
                }
            }
        }
        if (arrayList.size() > 0) {
            this.tvTipTag.setVisibility(View.VISIBLE);
            this.llTips.setVisibility(View.VISIBLE);
            this.llTips.setData(arrayList);
        }
    }

    public void syncMyAssets(TokenBean tokenBean) {
        MyAssetsModel myAssetsModel = new MyAssetsModel();
        if (tokenBean != null && tokenBean.getType() == 5) {
            myAssetsModel.requestMyCollections().subscribe(new IObserver(new fun1(myAssetsModel), "requestMyCollections"));
        } else {
            myAssetsModel.requestMyAssets().subscribe(new IObserver(new fun2(myAssetsModel), "requestMyAssets"));
        }
    }

    public class fun1 implements ICallback<NftDataOutput> {
        final MyAssetsModel val$myAssetsModel;

        public static void lambda$onResponse$0(Boolean bool) throws Exception {
        }

        fun1(MyAssetsModel myAssetsModel) {
            this.val$myAssetsModel = myAssetsModel;
        }

        @Override
        public void onResponse(String str, NftDataOutput nftDataOutput) {
            if (nftDataOutput != null && nftDataOutput.getCode() == 0) {
                this.val$myAssetsModel.saveMyCollections(nftDataOutput.getData() != null ? nftDataOutput.getData().getToken() : new ArrayList<>()).subscribe(new Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        CustomTokensConfirmActivity.1.lambda$onResponse$0((Boolean) obj);
                    }
                }, new Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        CustomTokensConfirmActivity.1.lambda$onResponse$1((Throwable) obj);
                    }
                });
            }
            if (syncMyAssetsDisposable == null || syncMyAssetsDisposable.isDisposed()) {
                return;
            }
            syncMyAssetsDisposable.dispose();
            syncMyAssetsDisposable = null;
        }

        public static void lambda$onResponse$1(Throwable th) throws Exception {
            LogUtils.e(th);
            SentryUtil.captureException(th);
        }

        @Override
        public void onFailure(String str, String str2) {
            if (syncMyAssetsDisposable == null || syncMyAssetsDisposable.isDisposed()) {
                return;
            }
            syncMyAssetsDisposable.dispose();
            syncMyAssetsDisposable = null;
        }

        @Override
        public void onSubscribe(Disposable disposable) {
            syncMyAssetsDisposable = disposable;
        }
    }

    public class fun2 implements ICallback<AssetsDataOutput> {
        final MyAssetsModel val$myAssetsModel;

        public static void lambda$onResponse$0(Boolean bool) throws Exception {
        }

        fun2(MyAssetsModel myAssetsModel) {
            this.val$myAssetsModel = myAssetsModel;
        }

        @Override
        public void onResponse(String str, AssetsDataOutput assetsDataOutput) {
            if (assetsDataOutput != null && assetsDataOutput.getCode() == 0 && assetsDataOutput.getData() != null) {
                this.val$myAssetsModel.saveMyAssets(assetsDataOutput.getData()).subscribe(new Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        CustomTokensConfirmActivity.2.lambda$onResponse$0((Boolean) obj);
                    }
                }, new Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        CustomTokensConfirmActivity.2.lambda$onResponse$1((Throwable) obj);
                    }
                });
            }
            if (syncMyAssetsDisposable == null || syncMyAssetsDisposable.isDisposed()) {
                return;
            }
            syncMyAssetsDisposable.dispose();
            syncMyAssetsDisposable = null;
        }

        public static void lambda$onResponse$1(Throwable th) throws Exception {
            LogUtils.e(th);
            SentryUtil.captureException(th);
        }

        @Override
        public void onFailure(String str, String str2) {
            if (syncMyAssetsDisposable == null || syncMyAssetsDisposable.isDisposed()) {
                return;
            }
            syncMyAssetsDisposable.dispose();
            syncMyAssetsDisposable = null;
        }

        @Override
        public void onSubscribe(Disposable disposable) {
            syncMyAssetsDisposable = disposable;
        }
    }

    public void saveCustomToken() {
        this.btnSave.setEnabled(false);
        showLoading(getResources().getString(R.string.adding));
        ((CustomTokensModel) this.mModel).addCustomToken(this.tokenBean.getAddress(), this.tokenBean).subscribe(new IObserver(new ICallback<AddCustomTokenOutput>() {
            @Override
            public void onResponse(String str, AddCustomTokenOutput addCustomTokenOutput) {
                dismissLoading();
                if (addCustomTokenOutput != null && addCustomTokenOutput.isData()) {
                    CustomTokensConfirmActivity customTokensConfirmActivity = CustomTokensConfirmActivity.this;
                    customTokensConfirmActivity.syncMyAssets(customTokensConfirmActivity.tokenBean);
                    Intent intent = new Intent(mContext, MainTabActivity.class);
                    intent.addFlags(67108864);
                    intent.putExtra(AssetsConfig.ACTION, 1);
                    intent.putExtra(AssetsConfig.TOKEN_TYPE, tokenBean.getType());
                    intent.putExtra(AssetsConfig.TOKEN_SYMBOL, tokenBean.getShortName());
                    mContext.startActivity(intent);
                    return;
                }
                CustomTokensConfirmActivity customTokensConfirmActivity2 = CustomTokensConfirmActivity.this;
                customTokensConfirmActivity2.ToastError(customTokensConfirmActivity2.getResources().getString(R.string.add_failed_retry));
                btnSave.setEnabled(true);
            }

            @Override
            public void onFailure(String str, String str2) {
                dismissLoading();
                CustomTokensConfirmActivity customTokensConfirmActivity = CustomTokensConfirmActivity.this;
                customTokensConfirmActivity.ToastError(customTokensConfirmActivity.getResources().getString(R.string.add_failed_retry));
                btnSave.setEnabled(true);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                saveCustomTokenDisposable = disposable;
            }
        }, "saveCustomToken"));
    }

    public void setClick() {
        this.binding.confirm.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (view.getId() != R.id.confirm) {
                    return;
                }
                if (!WatchWalletVerifier.getInstance().getWatchWalletHasOwnerPermission()) {
                    WatchWalletVerifier.getInstance().confirmVerify(getIContext());
                    return;
                }
                AnalyticsHelper.logEvent(AnalyticsHelper.CustomTokenPage.CLICK_CUSTOM_TOKEN_CONFIRM_PAGE_CONFIRM);
                saveCustomToken();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Disposable disposable = this.saveCustomTokenDisposable;
        if (disposable != null && !disposable.isDisposed()) {
            this.saveCustomTokenDisposable.dispose();
        }
        this.binding = null;
    }
}
