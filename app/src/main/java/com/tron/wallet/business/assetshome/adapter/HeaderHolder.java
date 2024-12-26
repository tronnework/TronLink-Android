package com.tron.wallet.business.assetshome.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.business.addassets2.bean.asset.AssetsHomeData;
import com.tron.wallet.business.addassets2.bean.asset.AssetsHomePriceBean;
import com.tron.wallet.business.assetshome.AssetsAnimatorHelper;
import com.tron.wallet.business.assetshome.listener.HiddenSwitchListener;
import com.tron.wallet.business.maintab.MainTabActivity;
import com.tron.wallet.business.mutil.MultiSelectAddressActivity;
import com.tron.wallet.business.mutil.bean.MultiSourcePageEnum;
import com.tron.wallet.business.receive.ReceiveActivity;
import com.tron.wallet.business.reminder.BackupReminder;
import com.tron.wallet.business.security.home.SecurityHomeActivity;
import com.tron.wallet.business.stakev2.stake.StakeHomeActivity;
import com.tron.wallet.business.transfer.selectaddress.SelectSendAddressActivity;
import com.tron.wallet.business.tronpower.management.ResourceManagementActivity;
import com.tron.wallet.business.tronpower.stake.StakeTRXActivity;
import com.tron.wallet.business.vote.component.VoteMainActivity;
import com.tron.wallet.business.walletmanager.backup.record.BackupRecordActivity;
import com.tron.wallet.business.walletmanager.backup.record.BackupRecordBean;
import com.tron.wallet.business.walletmanager.detail.WalletDetailActivity;
import com.tron.wallet.business.walletmanager.pairwallet.PairColdWalletExplainActivity;
import com.tron.wallet.common.adapter.holder.swap.TokenHolder;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.AssetsBannerPageView;
import com.tron.wallet.common.components.MultiSignPopupTextView;
import com.tron.wallet.common.components.WhiteEnergyProgressView;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.config.TronSetting;
import com.tron.wallet.common.interfaces.CheckAccountActivatedCallback;
import com.tron.wallet.common.utils.AccountUtils;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.DataStatHelper;
import com.tron.wallet.common.utils.DateUtils;
import com.tron.wallet.common.utils.DensityUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.NumUtils;
import com.tron.wallet.common.utils.OwnerPermissionCheckUtils;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.common.utils.WalletTypeUtils;
import com.tron.wallet.databinding.ItemAssetsHeaderBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletType;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import j$.util.Objects;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class HeaderHolder extends BaseHolder {
    private static final String LINE_FEED_SYMBOL = "\n";
    private static final String SPLIT_SYMBOL = "/";
    AssetsBannerPageView assetsBannerView;
    View backupLayoutTip;
    private ItemAssetsHeaderBinding binding;
    private boolean hiddenStatusChanged;
    private boolean hidePrivacy;
    private boolean isFirstLoaded;
    ImageView ivAssetStatusSwitch;
    View ivBackupClose;
    ImageView ivCopy;
    View ivLoading;
    View ivSeparator;
    View ivWatchClose;
    View llEnergyView;
    View llWatchTip;
    private Protocol.Account mAccount;
    private BackupRecordBean mBackupRecordBean;
    TextView mBrandWidthProgressTv;
    WhiteEnergyProgressView mBrandWidthProgressView;
    View mBrandWidthView;
    protected Context mContext;
    private AssetsHomeData mData;
    TextView mEnergyProgressTv;
    WhiteEnergyProgressView mEnergyProgressView;
    private HiddenSwitchListener mHiddenSwitchListener;
    TextView mMoneyValueTv;
    private NumberFormat mNumebrFormat;
    TextView mTrxValueTv;
    private Wallet mWallet;
    View mainBackground;
    RelativeLayout rl_watch;
    private boolean showBackTip;
    private String srAddress;
    TextView tvAddress;
    TextView tvBackupTip;
    MultiSignPopupTextView tvFlagMultiSign;
    TextView tvTrx;
    TextView tvWatchReminderTip;
    TextView tvWatchTag;

    public void setHiddenChangedListener(boolean z, HiddenSwitchListener hiddenSwitchListener) {
        this.hidePrivacy = z;
        this.mHiddenSwitchListener = hiddenSwitchListener;
    }

    public HeaderHolder(View view) {
        super(view);
        this.hidePrivacy = false;
        this.srAddress = "";
        this.isFirstLoaded = true;
        this.showBackTip = false;
        mappingId(view);
        setClick();
        this.mContext = view.getContext();
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.mNumebrFormat = numberInstance;
        numberInstance.setMaximumFractionDigits(2);
        refreshMenu();
    }

    public void mappingId(View view) {
        ItemAssetsHeaderBinding bind = ItemAssetsHeaderBinding.bind(view);
        this.binding = bind;
        this.mainBackground = bind.topCard;
        this.mTrxValueTv = this.binding.tvTrxValue;
        this.mMoneyValueTv = this.binding.tvMoneyValue;
        this.rl_watch = this.binding.rlWatch;
        this.tvWatchTag = this.binding.tvWatchTag;
        this.mEnergyProgressTv = this.binding.tvEnergyProgress;
        this.mBrandWidthProgressTv = this.binding.tvBandwidthProgress;
        this.mEnergyProgressView = this.binding.progressEnergy;
        this.mBrandWidthProgressView = this.binding.progressBrandwidth;
        this.mBrandWidthView = this.binding.rlBandwidth;
        this.llEnergyView = this.binding.llEnergy;
        this.ivLoading = this.binding.ivLoading;
        this.ivAssetStatusSwitch = this.binding.assetStatus;
        this.tvAddress = this.binding.tvAddress;
        this.ivCopy = this.binding.ivCopy;
        this.tvFlagMultiSign = this.binding.tvFlagMultiSign;
        this.tvTrx = this.binding.tvTrx;
        this.llWatchTip = this.binding.llWatchTip;
        this.backupLayoutTip = this.binding.backupTipLayout;
        this.ivWatchClose = this.binding.ivWatchClose;
        this.ivBackupClose = this.binding.ivBackupClose;
        this.tvWatchReminderTip = this.binding.tvWatchReminderTip;
        this.tvBackupTip = this.binding.tvBackUpTip;
        this.ivSeparator = this.binding.ivSeparator;
        this.assetsBannerView = this.binding.assetsBannerView;
    }

    public void bindHolder(Wallet wallet, AssetsHomeData assetsHomeData, Protocol.Account account, GrpcAPI.AccountResourceMessage accountResourceMessage, boolean z, BackupRecordBean backupRecordBean) {
        double d;
        BigDecimal mul_;
        this.hiddenStatusChanged = this.hidePrivacy != z;
        this.hidePrivacy = z;
        this.mWallet = wallet;
        this.mData = assetsHomeData;
        this.mAccount = account;
        this.mBackupRecordBean = backupRecordBean;
        if (this.isFirstLoaded) {
            this.isFirstLoaded = false;
            this.showBackTip = false;
            showPriceLoading(true);
        }
        Wallet wallet2 = this.mWallet;
        if (wallet2 != null) {
            if (wallet2.isSamsungWallet()) {
                this.mainBackground.setBackgroundResource(R.drawable.bg_main_card_sumsung);
            } else if (this.mWallet.isLedgerHDWallet()) {
                this.mainBackground.setBackgroundResource(R.drawable.bg_main_card_ledger);
            } else if (this.mWallet.isWatchCold()) {
                this.mainBackground.setBackgroundResource(R.drawable.bg_main_card_watch_cold);
            } else if (this.mWallet.isWatchOnly()) {
                this.mainBackground.setBackgroundResource(R.drawable.bg_main_card_watch);
            } else {
                this.mainBackground.setBackgroundResource(R.drawable.bg_main_card_hot);
            }
            if (!this.mWallet.isShieldedWallet()) {
                this.tvFlagMultiSign.attachAccount(this.mAccount, null);
            }
            this.tvFlagMultiSign.setWallet(this.mWallet);
        }
        this.ivSeparator.setVisibility(View.VISIBLE);
        Wallet wallet3 = this.mWallet;
        if (wallet3 != null) {
            if (wallet3.isWatchNotPaired()) {
                this.assetsBannerView.setVisibility(View.GONE);
                if (WalletType.showWatchWalletReminderTip(this.mWallet)) {
                    this.llWatchTip.setVisibility(View.VISIBLE);
                    setWatchWalletReminderTip();
                } else {
                    this.llWatchTip.setVisibility(View.GONE);
                    this.ivSeparator.setVisibility(View.GONE);
                }
            } else {
                this.assetsBannerView.setVisibility(View.VISIBLE);
                this.llWatchTip.setVisibility(View.GONE);
            }
        }
        setBackupRecordReminderTip();
        this.mTrxValueTv.getLayoutParams().width = -2;
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(this.mTrxValueTv, 14, 28, 1, 2);
        if (this.mData != null) {
            boolean isUsdPrice = SpAPI.THIS.isUsdPrice();
            if (this.mData.price != null) {
                AssetsHomePriceBean assetsHomePriceBean = this.mData.price;
                d = isUsdPrice ? assetsHomePriceBean.priceUSD : assetsHomePriceBean.priceCny;
                if (BigDecimalUtils.equalsZero((Number) Double.valueOf(d))) {
                    d = SpAPI.THIS.getPrice();
                }
            } else {
                d = 0.0d;
            }
            if (this.mData.totalTRX >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && this.mData.getToken() != null && this.mData.getToken().size() != 0) {
                if (this.hidePrivacy) {
                    this.mTrxValueTv.setText(this.mContext.getResources().getString(R.string.asset_hidden_string));
                    this.tvTrx.setVisibility(View.GONE);
                } else {
                    this.mTrxValueTv.setText(StringTronUtil.formatNumber3Truncate(Double.valueOf(this.mData.totalTRX)));
                    this.tvTrx.setVisibility(View.VISIBLE);
                }
                try {
                    if (this.hidePrivacy) {
                        this.mMoneyValueTv.setText(this.mContext.getResources().getString(R.string.asset_hidden_string));
                    } else {
                        if (isUsdPrice && !StringTronUtil.isEmpty(this.mData.totalUsd)) {
                            mul_ = BigDecimalUtils.toBigDecimal(this.mData.totalUsd);
                        } else if (!isUsdPrice && !StringTronUtil.isEmpty(this.mData.totalCny)) {
                            mul_ = BigDecimalUtils.toBigDecimal(this.mData.totalCny);
                        } else {
                            mul_ = BigDecimalUtils.mul_(Double.valueOf(this.mData.totalTRX), Double.valueOf(d));
                        }
                        this.mMoneyValueTv.setText(StringTronUtil.formatPrice3(mul_));
                    }
                } catch (Exception e) {
                    SentryUtil.captureException(e);
                }
            } else if (z) {
                this.mTrxValueTv.setText(this.mContext.getResources().getString(R.string.asset_hidden_string));
                this.mMoneyValueTv.setText(this.mContext.getResources().getString(R.string.asset_hidden_string));
                this.tvTrx.setVisibility(View.GONE);
            } else {
                this.mTrxValueTv.setText(TokenHolder.PLACE_HOLDER);
                this.mMoneyValueTv.setText("≈- - ");
                this.tvTrx.setVisibility(View.VISIBLE);
            }
        }
        if (this.hidePrivacy) {
            this.ivAssetStatusSwitch.setImageResource(R.mipmap.asset_hidden);
        } else {
            this.ivAssetStatusSwitch.setImageResource(R.mipmap.asset_show);
        }
        TouchDelegateUtils.expandViewTouchDelegate(this.ivAssetStatusSwitch, 10);
        String address = wallet.getAddress();
        this.srAddress = address;
        this.tvAddress.setText(address);
        TouchDelegateUtils.expandViewTouchDelegate(this.ivCopy, 10);
        refreshMenu();
        initListener();
        eneryAndBrand(account, assetsHomeData, accountResourceMessage);
    }

    private List<AssetsBannerPageView.BannerViewInnerItem> buildBannerData() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new AssetsBannerPageView.BannerViewInnerItem(R.string.asset_send, R.mipmap.ic_send, new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$buildBannerData$0(view);
            }
        }));
        arrayList.add(new AssetsBannerPageView.BannerViewInnerItem(R.string.asset_receive, R.mipmap.ic_receive, new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$buildBannerData$1(view);
            }
        }));
        if (SpAPI.THIS.getCurIsMainChain()) {
            arrayList.add(new AssetsBannerPageView.BannerViewInnerItem(R.string.vote, R.mipmap.ic_asset_header_vote, new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    lambda$buildBannerData$2(view);
                }
            }));
        }
        final boolean z = true;
        final boolean z2 = TronSetting.stakeV2 && SpAPI.THIS.getHasShowStakeV2New() == 0;
        arrayList.add(new AssetsBannerPageView.BannerViewInnerItem(R.string.freeze, R.mipmap.ic_freeze_unfreeze, z2, new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$buildBannerData$3(z2, view);
            }
        }));
        z = (!SpAPI.THIS.isNewSafetyCheck() || WalletTypeUtils.isOnlyWatch(this.mWallet) || SpAPI.THIS.isCold()) ? false : false;
        arrayList.add(new AssetsBannerPageView.BannerViewInnerItem(R.string.home_safety_detection, R.mipmap.ic_safety_check, z, new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$buildBannerData$4(z, view);
            }
        }));
        if (!SpAPI.THIS.isShasta()) {
            arrayList.add(new AssetsBannerPageView.BannerViewInnerItem(R.string.swap, R.mipmap.ic_just_swap, new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    lambda$buildBannerData$5(view);
                }
            }));
        }
        return arrayList;
    }

    public void lambda$buildBannerData$0(View view) {
        clickSendLogic();
    }

    public void lambda$buildBannerData$1(View view) {
        doReceiveLogic();
    }

    public void lambda$buildBannerData$2(View view) {
        clickVoteLogic();
    }

    public void lambda$buildBannerData$3(boolean z, View view) {
        clickDoFreezeLogic(z);
    }

    public void lambda$buildBannerData$4(boolean z, View view) {
        doSafetyCheckLogic(z);
    }

    public void lambda$buildBannerData$5(View view) {
        RecyclerView.Adapter<? extends RecyclerView.ViewHolder> bindingAdapter = getBindingAdapter();
        if (bindingAdapter instanceof AssetsHeaderAdapter) {
            ((AssetsHeaderAdapter) bindingAdapter).getSwapClickListener().onClick(view);
        }
    }

    private void doSafetyCheckLogic(boolean z) {
        this.mContext.startActivity(new Intent(this.mContext, SecurityHomeActivity.class));
        if (z) {
            SpAPI.THIS.setHasShowSafetyCheckNew();
        }
    }

    private void setWatchWalletReminderTip() {
        String string = this.mContext.getResources().getString(R.string.watch_wallet_reminder_tip);
        String string2 = this.mContext.getResources().getString(R.string.watch_wallet_request_pair);
        SpannableString spannableString = new SpannableString(string2);
        spannableString.setSpan(new ForegroundColorSpan(this.mContext.getResources().getColor(R.color.orange_FFA9)), 0, string2.length(), 33);
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(mContext.getResources().getColor(R.color.orange_FFA9));
                textPaint.setUnderlineText(false);
            }

            @Override
            public void onClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.PairWatchColdWallet.WATCH_COLD_PAIRWIN_CLICK);
                PairColdWalletExplainActivity.start(mContext);
            }
        }, 0, string2.length(), 33);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
        spannableStringBuilder.append((CharSequence) spannableString);
        this.tvWatchReminderTip.setText(spannableStringBuilder);
        this.tvWatchReminderTip.setMovementMethod(LinkMovementMethod.getInstance());
        AnalyticsHelper.logEvent(AnalyticsHelper.PairWatchColdWallet.WATCH_COLD_PAIRWIN_SHOW);
    }

    public void setBackupRecordReminderTip() {
        String str;
        if (this.showBackTip) {
            this.backupLayoutTip.setVisibility(View.GONE);
            return;
        }
        BackupRecordBean backupLastOne = SpAPI.THIS.getBackupLastOne();
        this.mBackupRecordBean = backupLastOne;
        if (backupLastOne == null || (!DateUtils.isIn48Hours(backupLastOne.getBackupStamp()) && this.mBackupRecordBean.getHasShow())) {
            this.backupLayoutTip.setVisibility(View.GONE);
            return;
        }
        String string = this.mContext.getResources().getString(R.string.home_backup_tips_format);
        String walletName = this.mBackupRecordBean.getWalletName();
        Wallet walletForAddress = WalletUtils.getWalletForAddress(this.mBackupRecordBean.getWalletAddress());
        if (walletForAddress != null) {
            walletName = walletForAddress.getWalletName();
        }
        int i = this.mBackupRecordBean.backupRecordTYpe;
        if (i == 0) {
            str = "「" + this.mContext.getResources().getString(R.string.back_up_mnemonic) + "」";
        } else if (i == 1) {
            str = "「" + this.mContext.getResources().getString(R.string.back_up_private_key) + "」";
        } else if (i != 2) {
            str = "";
        } else {
            str = "「" + this.mContext.getResources().getString(R.string.back_up_keystore) + "」";
        }
        try {
            String format = String.format(string, walletName, DateUtils.longToString(this.mBackupRecordBean.getBackupStamp(), DateUtils.DATE_TO_STRING_DETAIAL_PATTERN), str);
            String str2 = " " + this.mContext.getResources().getString(R.string.home_backup_tips_more_detail);
            SpannableString spannableString = new SpannableString(str2);
            spannableString.setSpan(new ForegroundColorSpan(this.mContext.getResources().getColor(R.color.blue_3c)), 0, str2.length(), 33);
            spannableString.setSpan(new ClickableSpan() {
                @Override
                public void updateDrawState(TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setColor(mContext.getResources().getColor(R.color.blue_3c));
                    textPaint.setUnderlineText(false);
                }

                @Override
                public void onClick(View view) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.BackHistoryPage.HOMEPAGE_BACKUP_WALLET_OPEN);
                    BackupRecordActivity.start(mContext);
                }
            }, 0, str2.length(), 33);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(format);
            spannableStringBuilder.append((CharSequence) spannableString);
            this.tvBackupTip.setText(spannableStringBuilder);
            this.tvBackupTip.setMovementMethod(LinkMovementMethod.getInstance());
            this.backupLayoutTip.setVisibility(View.VISIBLE);
            if (!this.mBackupRecordBean.getHasShow()) {
                this.mBackupRecordBean.setHasShow(true);
                SpAPI.THIS.setBackupLastOne(this.mBackupRecordBean);
            }
            if (WalletType.showWatchWalletReminderTip(this.mWallet)) {
                this.backupLayoutTip.setVisibility(View.GONE);
                this.llWatchTip.setVisibility(View.VISIBLE);
            }
        } catch (ParseException e) {
            LogUtils.e(e);
        }
    }

    public void showPriceLoading(boolean z) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.ivLoading, "rotation", 0.0f, 360.0f);
        ofFloat.setRepeatCount(-1);
        ofFloat.setDuration(1200L);
        ofFloat.setRepeatMode(1);
        if (z) {
            if (this.ivLoading.getVisibility() != 0) {
                this.ivLoading.setVisibility(View.VISIBLE);
            }
            ofFloat.start();
        } else if (this.ivLoading.getVisibility() == 0) {
            this.ivLoading.setVisibility(View.GONE);
        }
    }

    private void refreshMenu() {
        try {
            if (this.assetsBannerView.isInitialized()) {
                return;
            }
            this.assetsBannerView.initBannerData(buildBannerData());
            this.assetsBannerView.setInitialized(true);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void setClick() {
        NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id != R.id.iv_copy) {
                    if (id == R.id.top_card) {
                        if (mWallet != null) {
                            WalletDetailActivity.startActivity(mContext, mWallet.getWalletName(), false);
                            return;
                        }
                        return;
                    } else if (id != R.id.tv_address) {
                        return;
                    }
                }
                if (!BackupReminder.isWalletBackup(mWallet)) {
                    BackupReminder.showBackupPopup(mContext, mWallet, null);
                } else {
                    HeaderHolder headerHolder = HeaderHolder.this;
                    headerHolder.copyAndToast(headerHolder.mWallet != null ? mWallet.getAddress() : tvAddress.getText().toString());
                }
                AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.AssetPage.CLICK_ASSET_PAGE_COPY_ADDRESS);
            }
        };
        this.binding.ivCopy.setOnClickListener(noDoubleClickListener);
        this.binding.topCard.setOnClickListener(noDoubleClickListener);
        this.binding.tvAddress.setOnClickListener(noDoubleClickListener);
    }

    public void copyAndToast(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        UIUtils.copy(str);
        ((MainTabActivity) this.itemView.getContext()).forceShowAgain(this.itemView.getContext().getString(R.string.address_already_copy));
    }

    public void eneryAndBrand(Protocol.Account account, AssetsHomeData assetsHomeData, GrpcAPI.AccountResourceMessage accountResourceMessage) {
        float f;
        float f2;
        if (assetsHomeData == null || assetsHomeData.totalTRX < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || assetsHomeData.getToken() == null || assetsHomeData.getToken().size() == 0) {
            SpannableString spannableString = new SpannableString("- -/- -");
            if (spannableString.length() >= 18) {
                spannableString = new SpannableString("- -\n/- -");
            }
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(this.mContext.getResources().getColor(R.color.white));
            AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(DensityUtils.dp2px(this.mContext, 11.0f));
            StyleSpan styleSpan = new StyleSpan(1);
            spannableString.setSpan(foregroundColorSpan, 0, TokenHolder.PLACE_HOLDER.length(), 17);
            spannableString.setSpan(absoluteSizeSpan, 0, TokenHolder.PLACE_HOLDER.length(), 17);
            spannableString.setSpan(" " + styleSpan, 0, TokenHolder.PLACE_HOLDER.length(), 17);
            this.mBrandWidthProgressTv.setText(spannableString);
            this.mEnergyProgressTv.setText(spannableString);
            f = 0.0f;
            f2 = 0.0f;
        } else if (accountResourceMessage == null || account == null) {
            f2 = 0.8f;
            f = 0.2f;
        } else {
            long netLimit = accountResourceMessage.getNetLimit() + accountResourceMessage.getFreeNetLimit();
            accountResourceMessage.getNetUsed();
            accountResourceMessage.getFreeNetUsed();
            long freeNetLimit = accountResourceMessage.getFreeNetLimit() - accountResourceMessage.getFreeNetUsed();
            if (freeNetLimit < 0) {
                freeNetLimit = 0;
            }
            long netLimit2 = accountResourceMessage.getNetLimit() - accountResourceMessage.getNetUsed();
            if (netLimit2 < 0) {
                netLimit2 = 0;
            }
            long j = freeNetLimit + netLimit2;
            if (j < 0) {
                j = 0;
            }
            f = netLimit != 0 ? ((float) j) / ((float) netLimit) : 0.0f;
            ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(this.mContext.getResources().getColor(R.color.white));
            AbsoluteSizeSpan absoluteSizeSpan2 = new AbsoluteSizeSpan(DensityUtils.dp2px(this.mContext, 11.0f));
            StyleSpan styleSpan2 = new StyleSpan(0);
            String numFormatToK = NumUtils.numFormatToK(j);
            SpannableString spannableString2 = new SpannableString(numFormatToK + SPLIT_SYMBOL + NumUtils.numFormatToK(netLimit));
            if (spannableString2.length() >= 18) {
                spannableString2 = new SpannableString(numFormatToK + "\n/" + NumUtils.numFormatToK(netLimit));
            }
            spannableString2.setSpan(foregroundColorSpan2, 0, String.valueOf(numFormatToK).length(), 17);
            spannableString2.setSpan(absoluteSizeSpan2, 0, String.valueOf(numFormatToK).length(), 17);
            spannableString2.setSpan(styleSpan2, 0, String.valueOf(numFormatToK).length(), 17);
            this.mBrandWidthProgressTv.setText(spannableString2);
            long energyLimit = accountResourceMessage.getEnergyLimit();
            long energyUsed = energyLimit - accountResourceMessage.getEnergyUsed();
            float f3 = energyLimit != 0 ? ((float) energyUsed) / ((float) energyLimit) : 0.0f;
            String numFormatToK2 = NumUtils.numFormatToK(energyUsed >= 0 ? energyUsed : 0L);
            SpannableString spannableString3 = new SpannableString(numFormatToK2 + SPLIT_SYMBOL + NumUtils.numFormatToK(energyLimit));
            if (spannableString3.length() >= 18) {
                spannableString3 = new SpannableString(numFormatToK2 + "\n/" + NumUtils.numFormatToK(energyLimit));
                this.mEnergyProgressTv.setGravity(3);
            } else {
                this.mEnergyProgressTv.setGravity(17);
            }
            spannableString3.setSpan(foregroundColorSpan2, 0, numFormatToK2.length(), 17);
            spannableString3.setSpan(absoluteSizeSpan2, 0, numFormatToK2.length(), 17);
            spannableString3.setSpan(styleSpan2, 0, numFormatToK2.length(), 17);
            this.mEnergyProgressTv.setText(spannableString3);
            f2 = f3;
        }
        this.mEnergyProgressView.setShouldStartAnimate(AssetsAnimatorHelper.shouldStartAnimator("WhiteEnergyProgressView" + this.mEnergyProgressView.getId()));
        this.mEnergyProgressView.setProgressValue(f2);
        this.mBrandWidthProgressView.setShouldStartAnimate(AssetsAnimatorHelper.shouldStartAnimator("WhiteEnergyProgressView" + this.mBrandWidthProgressView.getId()));
        this.mBrandWidthProgressView.setProgressValue(f);
        Wallet wallet = this.mWallet;
        if (wallet != null && wallet.isWatchNotPaired()) {
            this.mBrandWidthProgressTv.setCompoundDrawables(null, null, null, null);
            this.mEnergyProgressTv.setCompoundDrawables(null, null, null, null);
            return;
        }
        Drawable drawable = this.mContext.getResources().getDrawable(R.mipmap.ic_home_resource_arrow);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        this.mBrandWidthProgressTv.setCompoundDrawables(null, null, drawable, null);
        this.mEnergyProgressTv.setCompoundDrawables(null, null, drawable, null);
    }

    private void clickVoteLogic() {
        VoteMainActivity.startWithCheckOwnerPermission(this.mContext, this.mAccount, this.srAddress);
        AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.AssetPage.CLICK_ASSET_PAGE_VOTE);
        AnalyticsHelper.logEvent(AnalyticsHelper.VotingGate.CLICK_HOME_PAGE_VOTE);
    }

    private void clickDoFreezeLogic(boolean z) {
        if (TronSetting.stakeV2) {
            Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
            if (selectedPublicWallet != null) {
                if (AccountUtils.checkAccountIsNotActivated(WalletUtils.getAccount(this.mContext, selectedPublicWallet.getWalletName()))) {
                    OwnerPermissionCheckUtils.showStakeNotActivatedPopup(this.mContext, R.string.stake_account_unactive, new Consumer() {
                        @Override
                        public final void accept(Object obj) {
                            lambda$clickDoFreezeLogic$6((Void) obj);
                        }

                        public Consumer andThen(Consumer consumer) {
                            return Objects.requireNonNull(consumer);
                        }
                    });
                } else {
                    StakeHomeActivity.start(this.mContext, null, false, DataStatHelper.StatAction.Stake, this.mWallet.getAddress(), null);
                }
            }
        } else {
            StakeTRXActivity.startWithCheckOwnerPermission(this.mContext, this.mAccount, DataStatHelper.StatAction.Stake, this.srAddress);
        }
        if (z) {
            SpAPI.THIS.setHasShowStakeV2New();
        }
        AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.StakeGate.CLICK_HOME_PAGE_STAKE);
    }

    public void lambda$clickDoFreezeLogic$6(Void r2) {
        MultiSelectAddressActivity.start(this.mContext, MultiSourcePageEnum.StakeV2);
    }

    private void doReceiveLogic() {
        receivables();
        AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.AssetPage.CLICK_ASSET_PAGE_RECEIVE);
    }

    public void clickResourceLogic() {
        Wallet wallet = this.mWallet;
        if (wallet == null || !wallet.isWatchNotPaired()) {
            ResourceManagementActivity.start(this.mContext, 0);
            AnalyticsHelper.logEvent(AnalyticsHelper.ResourceGate.CLICK_HOME_PAGE_ENERGY_BANDWIDTH);
            AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.AssetPage.CLICK_ASSET_PAGE_ENERGY);
        }
    }

    private void clickSendLogic() {
        if (!BackupReminder.isWalletBackup(this.mWallet)) {
            Context context = this.mContext;
            BackupReminder.showBackupPopup(context, this.mWallet, TronConfig.type_main, context.getResources().getString(R.string.backup_tip_for_send));
        } else if (!TronConfig.hasNet) {
            IToast.getIToast().setImage(R.mipmap.toast_error).show(R.string.time_out);
        } else {
            AccountUtils.getInstance().checkAccountIsActivatedFromLocal(this.mContext, new CheckAccountActivatedCallback() {
                @Override
                public void isActivated() {
                    if (mWallet != null && mWallet.isShieldedWallet() && (mAccount == null || mAccount.getBalance() < 0)) {
                        IToast.getIToast().show(R.string.wait_to_finished);
                        return;
                    }
                    TokenBean tokenBean = new TokenBean();
                    tokenBean.setType(0);
                    tokenBean.setName("TRX");
                    tokenBean.setShortName("TRX");
                    if (mData != null && mData.getToken().size() > 0) {
                        tokenBean = mData.getToken().get(0);
                    }
                    int i = tokenBean.type;
                    SelectSendAddressActivity.startCommon(mContext, tokenBean, mAccount, null);
                    AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.AssetPage.CLICK_ASSET_PAGE_TRANSFER);
                }
            }, null);
        }
    }

    private void receivables() {
        if (!BackupReminder.isWalletBackup(this.mWallet)) {
            BackupReminder.showBackupPopup(this.mContext, this.mWallet, TronConfig.type_main);
        } else {
            goReceive();
        }
    }

    private void goReceive() {
        this.mContext.startActivity(new Intent(this.mContext, ReceiveActivity.class));
    }

    private void initListener() {
        this.ivAssetStatusSwitch.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (hidePrivacy) {
                    AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.AssetPage.CLICK_ASSET_PAGE_DISPLAY_ASSET);
                } else {
                    AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.AssetPage.CLICK_ASSET_PAGE_HIDE_ASSET);
                }
                mHiddenSwitchListener.onSwitch(!hidePrivacy);
            }
        });
        this.llEnergyView.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                clickResourceLogic();
            }
        });
        TouchDelegateUtils.expandViewTouchDelegate(this.llEnergyView, 10, 10, 10, 16);
        this.mBrandWidthView.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (mWallet == null || !mWallet.isWatchNotPaired()) {
                    ResourceManagementActivity.start(mContext, 1);
                    AnalyticsHelper.logEvent(AnalyticsHelper.ResourceGate.CLICK_HOME_PAGE_ENERGY_BANDWIDTH);
                    AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.AssetPage.CLICK_ASSET_PAGE_BANDWIDTH);
                }
            }
        });
        TouchDelegateUtils.expandViewTouchDelegate(this.mBrandWidthView, 10, 10, 10, 16);
        this.ivWatchClose.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                llWatchTip.setVisibility(View.GONE);
                ivSeparator.setVisibility(View.GONE);
                WalletType.setShowWatchWalletReminderTip(mWallet);
                setBackupRecordReminderTip();
            }
        });
        this.ivBackupClose.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.BackHistoryPage.HOMEPAGE_BACKUP_WALLET_CLOSE);
                backupLayoutTip.setVisibility(View.GONE);
                showBackTip = true;
            }
        });
    }
}
