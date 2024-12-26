package com.tron.wallet.business.nft;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.tron_base.frame.utils.StatusBarUtils;
import com.tron.wallet.business.addassets2.AssetsConfig;
import com.tron.wallet.business.addassets2.bean.nft.NftItemInfo;
import com.tron.wallet.business.addassets2.bean.nft.NftItemInfoOutput;
import com.tron.wallet.business.nft.contract.NftItemPresenter;
import com.tron.wallet.business.reminder.BackupReminder;
import com.tron.wallet.business.transfer.selectaddress.SelectSendAddressActivity;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.ExpandableTextView;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.interfaces.CheckAccountActivatedCallback;
import com.tron.wallet.common.utils.AccountUtils;
import com.tron.wallet.common.utils.ComponentUtils;
import com.tron.wallet.common.utils.ImageUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.ActivityNftTokenItemDetailBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import org.tron.walletserver.Wallet;
public class NftTokenItemDetailActivity extends BaseActivity<NftItemPresenter, EmptyModel> {
    private static final String KEY_NFT_ITEM = "NFT_ITEM";
    private static final String KEY_SHORT_NAME = "SHORT_NAME";
    private static final String KEY_TOKEN_IMAGE = "NFT_TOKEN_IMAGE";
    private static final String KEY_TOKEN_NAME = "NFT_TOKEN_NAME";
    private static final String KEY_WALLET_ADDRESS = "WALLET_ADDRESS";
    private static final String TAG = "NftItemDetail";
    private ActivityNftTokenItemDetailBinding binding;
    Button btnTransfer;
    SimpleDraweeView imageView;
    LinearLayout llLeft;
    private RxManager mRxManager = new RxManager();
    private String mWalletAddress;
    private NftItemInfo nftItem;
    private String nftTokenImage;
    private String shortName;
    private TokenBean tokenBean;
    private String tokenName;
    ExpandableTextView tvIntro;
    TextView tvName;
    TextView tvNotSupportTransferTag;
    TextView tvTokenId;

    public static void start(Context context, NftItemInfo nftItemInfo, String str, String str2, String str3, TokenBean tokenBean, String str4) {
        ComponentUtils.startActivity(context, NftTokenItemDetailActivity.class, new Pair(KEY_NFT_ITEM, nftItemInfo), new Pair("WALLET_ADDRESS", str), new Pair("SHORT_NAME", str2), new Pair(KEY_TOKEN_NAME, str3), new Pair(KEY_TOKEN_IMAGE, str4), new Pair(AssetsConfig.TOKEN_BEAN, tokenBean));
    }

    @Override
    protected void setLayout() {
        ActivityNftTokenItemDetailBinding inflate = ActivityNftTokenItemDetailBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 0);
        StatusBarUtils.setLightStatusBar(this, true);
        StatusBarUtils.setStatusBarColor(this, R.color.gray_f3f4f8);
        mappingId();
    }

    public void mappingId() {
        this.imageView = this.binding.image;
        this.tvName = this.binding.tvName;
        this.tvTokenId = this.binding.tvTokenId;
        this.tvIntro = this.binding.tvIntroduction;
        this.llLeft = this.binding.btnBack;
        this.btnTransfer = this.binding.btnTransfer;
        this.tvNotSupportTransferTag = this.binding.notTransferTag;
    }

    @Override
    protected void processData() {
        this.nftItem = (NftItemInfo) getIntent().getParcelableExtra(KEY_NFT_ITEM);
        this.mWalletAddress = getIntent().getStringExtra("WALLET_ADDRESS");
        this.shortName = getIntent().getStringExtra("SHORT_NAME");
        this.tokenBean = (TokenBean) getIntent().getParcelableExtra(AssetsConfig.TOKEN_BEAN);
        this.nftTokenImage = getIntent().getStringExtra(KEY_TOKEN_IMAGE);
        this.tokenName = getIntent().getStringExtra(KEY_TOKEN_NAME);
        setHeaderBar(getIntent().getStringExtra(this.nftItem.getAssetId()));
        updateUI(this.nftItem);
        setTransferButton();
        this.btnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$processData$0(view);
            }
        });
        Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
        if (selectedPublicWallet != null && selectedPublicWallet.isWatchNotPaired()) {
            this.btnTransfer.setVisibility(View.GONE);
        }
        this.llLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$processData$1(view);
            }
        });
        initRxEvent();
    }

    public void lambda$processData$0(View view) {
        if (this.nftItem == null) {
            return;
        }
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        if (!BackupReminder.isWalletBackup(selectedWallet)) {
            BackupReminder.showBackupPopup(this.mContext, selectedWallet, "", this.mContext.getResources().getString(R.string.backup_tip_for_send));
        } else if (AssetsConfig.isCustomTokenAndNotSupportTransfer(this.tokenBean)) {
            toast(getResources().getString(R.string.token_not_support_transfer));
        } else if (!TronConfig.hasNet) {
            toast(getString(R.string.time_out));
        } else {
            AccountUtils.getInstance().checkAccountIsActivatedFromLocalWithTokenBean(this, new CheckAccountActivatedCallback() {
                @Override
                public void isActivated() {
                    Bundle bundle = new Bundle();
                    bundle.putString("token_address", nftItem.getTokenAddress());
                    FirebaseAnalytics.getInstance(mContext).logEvent("Click_nft_transfer", bundle);
                    NftTokenItemDetailActivity nftTokenItemDetailActivity = NftTokenItemDetailActivity.this;
                    SelectSendAddressActivity.startForNft(nftTokenItemDetailActivity, nftTokenItemDetailActivity.tokenBean, null, nftItem);
                }
            }, null, this.tokenBean);
        }
    }

    public void lambda$processData$1(View view) {
        exit();
    }

    private void setTransferButton() {
        if (this.tokenBean.getTransferStatus()) {
            return;
        }
        this.btnTransfer.setVisibility(View.GONE);
        this.tvNotSupportTransferTag.setVisibility(View.VISIBLE);
    }

    private void initRxEvent() {
        this.mRxManager.on(Event.BroadcastSuccess, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRxEvent$2(obj);
            }
        });
        this.mRxManager.on(Event.BackToHome, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRxEvent$3(obj);
            }
        });
    }

    public void lambda$initRxEvent$2(Object obj) throws Exception {
        this.mRxManager.clear();
        finish();
    }

    public void lambda$initRxEvent$3(Object obj) throws Exception {
        LogUtils.i("NftItemPresenter-BackToHome");
        finish();
    }

    public void updateUI(NftItemInfo nftItemInfo) {
        if (nftItemInfo == null) {
            return;
        }
        this.nftItem = nftItemInfo;
        if (TextUtils.isEmpty(nftItemInfo.getName())) {
            this.tvName.setText(String.format("#%s", nftItemInfo.getAssetId()));
            this.tvTokenId.setVisibility(View.GONE);
        } else {
            this.tvTokenId.setVisibility(View.VISIBLE);
            this.tvName.setText(nftItemInfo.getName());
            this.tvTokenId.setText(String.format("#%s", nftItemInfo.getAssetId()));
        }
        if (TextUtils.isEmpty(nftItemInfo.getIntro())) {
            nftItemInfo.setIntro(getString(R.string.no_intro));
        }
        this.tvIntro.setOriginalText(nftItemInfo.getIntro());
        ImageUtils.loadImageWithHeightThreshold(this.imageView, nftItemInfo.getImageUrl(), UIUtils.dip2px(200.0f), UIUtils.dip2px(300.0f));
    }

    private void requestTokenItemInfo() {
        ((NftItemPresenter) this.mPresenter).refreshTokenItemInfo(this.mWalletAddress, this.nftItem, new ICallback<NftItemInfoOutput>() {
            @Override
            public void onResponse(String str, NftItemInfoOutput nftItemInfoOutput) {
                LogUtils.w(NftTokenItemDetailActivity.TAG, String.format("requestTokenItemInfo sucess, %s", nftItemInfoOutput.toString()));
                updateUI(nftItemInfoOutput.getData());
            }

            @Override
            public void onFailure(String str, String str2) {
                NftTokenItemDetailActivity nftTokenItemDetailActivity = NftTokenItemDetailActivity.this;
                nftTokenItemDetailActivity.toast(nftTokenItemDetailActivity.getString(R.string.time_out));
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxManager rxManager = this.mRxManager;
        if (rxManager != null) {
            rxManager.clear();
        }
    }
}
