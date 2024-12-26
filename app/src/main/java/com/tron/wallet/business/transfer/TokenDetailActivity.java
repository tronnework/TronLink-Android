package com.tron.wallet.business.transfer;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewpager2.widget.ViewPager2;
import com.arasthel.asyncjob.AsyncJob;
import com.google.android.material.appbar.AppBarLayout;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.utils.StatusBarUtils;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.business.addassets2.AssetsConfig;
import com.tron.wallet.business.mutil.MultiSelectAddressActivity;
import com.tron.wallet.business.reminder.BackupReminder;
import com.tron.wallet.business.tokendetail.ProjectIntroductionActivity;
import com.tron.wallet.business.transfer.TokenDetailContract;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.common.bean.token.OfficialType;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.components.ItemWarningTagView;
import com.tron.wallet.common.components.MultiLineTextPopupView;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayoutV2;
import com.tron.wallet.common.components.qr.ScannerActivity;
import com.tron.wallet.common.components.xtablayout.XTabLayoutV2;
import com.tron.wallet.common.config.M;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.interfaces.CheckAccountActivatedCallback;
import com.tron.wallet.common.interfaces.PermissionInterface;
import com.tron.wallet.common.utils.AccountUtils;
import com.tron.wallet.common.utils.AddressMapUtils;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.DensityUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.PermissionHelper;
import com.tron.wallet.common.utils.TokenItemUtil;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.AcTransferBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.LedgerWallet;
import com.tronlinkpro.wallet.R;
import java.math.BigDecimal;
import java.util.HashMap;
import org.tron.walletserver.Wallet;
public class TokenDetailActivity extends BaseActivity<TokenDetailPresenter, TokenDetailModel> implements TokenDetailContract.View, PermissionInterface {
    AppBarLayout appBarLayout;
    AcTransferBinding binding;
    View contentRootView;
    private boolean customTokenSyncing;
    private boolean filterSmallValue;
    ImageView ivFilterSmallValueTip;
    ImageView ivToggleSmallValue;
    ImageView ivType;
    LinearLayout liSmallVallSwitch;
    LinearLayout llDeposit;
    View loadingView;
    private HashMap<String, NameAddressExtraBean> mAddressMap = new HashMap<>();
    LinearLayout mBottomBarLayout;
    View mBottomView;
    LinearLayout mMarketLayout;
    View mMenuLine1;
    View mMenuLine2;
    private PermissionHelper mPermissionHelper;
    View mReceiveView;
    View mTransferView;
    CoordinatorLayout nestedLayout;
    ImageView progressView;
    PtrHTFrameLayoutV2 ptrView;
    ItemWarningTagView rlScam;
    private TokenBean tokenBean;
    TextView tvToggleSmallValue;
    TextView tvType;
    ViewPager2 vpContent;
    XTabLayoutV2 xTablayout;

    public static void lambda$showPop$2(View view) {
    }

    public HashMap<String, NameAddressExtraBean> getAllAddress() {
        return this.mAddressMap;
    }

    @Override
    public AppBarLayout getAppBarLayout() {
        return this.appBarLayout;
    }

    @Override
    public PtrHTFrameLayoutV2 getFrameLayout() {
        return this.ptrView;
    }

    @Override
    public int getPermissionsRequestCode() {
        return 2001;
    }

    @Override
    public ViewPager2 getViewPager() {
        return this.vpContent;
    }

    @Override
    public XTabLayoutV2 getXTablayout() {
        return this.xTablayout;
    }

    @Override
    public void setFilterOptionWidth(int i) {
    }

    @Override
    public void setFilterSmallValueEnable(boolean z) {
    }

    @Override
    public void setStatusBarBackgroundColor(int i) {
    }

    public static void start(Context context, TokenBean tokenBean, double d) {
        Intent intent = new Intent(context, TokenDetailActivity.class);
        if (tokenBean.type == 0) {
            intent.putExtra(TronConfig.isTRX, M.M_TRX);
            intent.putExtra(TronConfig.TITLE, "TRX");
            tokenBean.setName("TRX");
            tokenBean.setShortName("TRX");
        } else if (tokenBean.type == 1) {
            intent.putExtra(TronConfig.isTRX, M.M_TRC10);
            intent.putExtra(TronConfig.TITLE, tokenBean.shortName.equals("") ? tokenBean.name : tokenBean.shortName);
        } else if (tokenBean.type == 4) {
            intent.putExtra(TronConfig.isTRX, M.M_TRZ);
            intent.putExtra(TronConfig.TITLE, "TRZ");
            tokenBean.setName("TRZ");
            tokenBean.setShortName("TRZ");
        } else if (tokenBean.type == 2) {
            intent.putExtra(TronConfig.isTRX, M.M_TRC20);
        }
        intent.putExtra(TronConfig.TRC, tokenBean);
        intent.putExtra(TronConfig.PriceUsdOrCny, d);
        context.startActivity(intent);
    }

    public static void start(Context context, TokenBean tokenBean) {
        Intent intent = new Intent(context, TokenDetailActivity.class);
        if (tokenBean.type == 0) {
            intent.putExtra(TronConfig.isTRX, M.M_TRX);
            intent.putExtra(TronConfig.TITLE, "TRX");
            tokenBean.setName("TRX");
        } else if (tokenBean.type == 1) {
            intent.putExtra(TronConfig.isTRX, M.M_TRC10);
            intent.putExtra(TronConfig.TITLE, tokenBean.shortName.equals("") ? tokenBean.name : tokenBean.shortName);
        } else if (tokenBean.type == 2) {
            intent.putExtra(TronConfig.isTRX, M.M_TRC20);
        } else if (tokenBean.type == 4) {
            intent.putExtra(TronConfig.isTRX, M.M_TRZ);
            intent.putExtra(TronConfig.TITLE, "TRZ");
            tokenBean.setName("TRZ");
            tokenBean.setShortName("TRZ");
        }
        intent.putExtra(TronConfig.TRC, tokenBean);
        context.startActivity(intent);
    }

    public static void start(Context context, TokenBean tokenBean, int i) {
        Intent intent = new Intent(context, TokenDetailActivity.class);
        if (tokenBean.type == 0) {
            intent.putExtra(TronConfig.isTRX, M.M_TRX);
            intent.putExtra(TronConfig.TITLE, "TRX");
            tokenBean.setName("TRX");
        } else if (tokenBean.type == 1) {
            intent.putExtra(TronConfig.isTRX, M.M_TRC10);
            intent.putExtra(TronConfig.TITLE, tokenBean.shortName.equals("") ? tokenBean.name : tokenBean.shortName);
        } else if (tokenBean.type == 2) {
            intent.putExtra(TronConfig.isTRX, M.M_TRC20);
        }
        intent.putExtra(TronConfig.TRC, tokenBean);
        intent.putExtra("from", i);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        AcTransferBinding inflate = AcTransferBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        setClick();
        setView(root, 1);
        StatusBarUtils.setLightStatusBar(this, false);
        getHeaderHolder().tvCommonTitle2.setTextSize(2, 12.0f);
        getHeaderHolder().tvCommonTitle2.setPadding(UIUtils.dip2px(8.0f), 0, UIUtils.dip2px(8.0f), 0);
        this.filterSmallValue = SpAPI.THIS.getFilterSmallValue();
        TokenBean tokenBean = this.tokenBean;
        if ((tokenBean != null && BigDecimalUtils.lessThanOrEqual(tokenBean.getUsdPrice(), BigDecimal.ZERO)) || OfficialType.isScamOrUnSafeToken(this.tokenBean)) {
            this.filterSmallValue = false;
        }
        if (M.M_TRZ.equals(getIntent().getStringExtra(TronConfig.isTRX))) {
            setHeaderBar(getIntent().getStringExtra(TronConfig.TITLE));
        } else if (M.M_TRX.equals(getIntent().getStringExtra(TronConfig.isTRX))) {
            setHeaderBar(getIntent().getStringExtra(TronConfig.TITLE));
            getHeaderHolder().rlRight.setVisibility(View.VISIBLE);
            getHeaderHolder().tvCommonTitle2.setVisibility(View.GONE);
            getHeaderHolder().ivQr.setVisibility(View.VISIBLE);
            getHeaderHolder().ivQr.setImageDrawable(getDrawable(R.drawable.ripple_introdiction));
        } else if (M.M_TRC10.equals(getIntent().getStringExtra(TronConfig.isTRX))) {
            setHeaderBar(getIntent().getStringExtra(TronConfig.TITLE));
            getHeaderHolder().rlRight.setVisibility(View.VISIBLE);
            getHeaderHolder().ivQr.setVisibility(View.VISIBLE);
            getHeaderHolder().ivQr.setImageDrawable(getDrawable(R.drawable.ripple_introdiction));
            getHeaderHolder().tvCommonTitle2.setVisibility(View.VISIBLE);
            getHeaderHolder().tvCommonTitle2.setText(R.string.TRC10);
            getHeaderHolder().tvCommonTitle2.setTextColor(this.mContext.getResources().getColor(R.color.blue_3c));
            getHeaderHolder().tvCommonTitle2.setBackgroundResource(R.drawable.roundborder_blue3c_10);
        } else {
            setHeaderBar(getIntent().getStringExtra(TronConfig.TITLE));
            getHeaderHolder().rlRight.setVisibility(View.VISIBLE);
            getHeaderHolder().ivQr.setVisibility(View.VISIBLE);
            getHeaderHolder().ivQr.setImageDrawable(getDrawable(R.drawable.ripple_introdiction));
            getHeaderHolder().tvCommonTitle2.setVisibility(View.VISIBLE);
            getHeaderHolder().tvCommonTitle2.setText(R.string.TRC20);
            getHeaderHolder().tvCommonTitle2.setTextColor(this.mContext.getResources().getColor(R.color.green_57));
            getHeaderHolder().tvCommonTitle2.setBackgroundResource(R.drawable.roundborder_57bfad_10);
        }
        getHeaderHolder().tvCommonTitle.setMaxLines(1);
        getHeaderHolder().tvCommonTitle.setMaxEms(5);
        getHeaderHolder().tvCommonTitle.setEllipsize(TextUtils.TruncateAt.END);
        TokenBean tokenBean2 = (TokenBean) getIntent().getParcelableExtra(TronConfig.TRC);
        this.tokenBean = tokenBean2;
        getHeaderHolder().ivCommonTitle2.setVisibility(View.GONE);
        TokenItemUtil.initNationalFlagView(this.mContext, getHeaderHolder().ivCommonTitle2, tokenBean2.getNational(), TextUtils.isEmpty(tokenBean2.getShortName()) ? tokenBean2.getName() : tokenBean2.getShortName());
    }

    private void setClick() {
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                switch (view.getId()) {
                    case R.id.iv_filter_small_value_tip:
                        new MultiLineTextPopupView.Builder().setAnchor(ivFilterSmallValueTip).setRequiredWidth((int) (UIUtils.getWindowWidth() * 0.7f)).addKeyValue(getString(R.string.filter_small_value_description, new Object[]{TronConfig.filterSmallValue}), "").build(getIContext()).show();
                        return;
                    case R.id.iv_toggle_small_value:
                        if (ptrView.isRefreshing() || ((TokenDetailPresenter) mPresenter).isCurrentRefresh()) {
                            return;
                        }
                        TokenDetailActivity tokenDetailActivity = TokenDetailActivity.this;
                        tokenDetailActivity.filterSmallValue = true ^ tokenDetailActivity.filterSmallValue;
                        SpAPI.THIS.setFilterSmallValue(filterSmallValue);
                        if (filterSmallValue) {
                            ivToggleSmallValue.setImageResource(R.mipmap.ic_checked_blue);
                        } else {
                            ivToggleSmallValue.setImageResource(R.mipmap.ic_uncheck_grey);
                        }
                        if (!TronConfig.hasNet) {
                            IToast.getIToast().show(R.string.time_out);
                            return;
                        } else {
                            ((TokenDetailPresenter) mPresenter).setFilterSmallValue(filterSmallValue);
                            return;
                        }
                    case R.id.ll_deposit:
                        AnalyticsHelper.logEvent(AnalyticsHelper.TokenDetailsPage.CLICK_TOKEN_DETAIL_PAGE_DEPOSIT);
                        if (LedgerWallet.isLedger(WalletUtils.getSelectedWallet())) {
                            if (SpAPI.THIS.getCurIsMainChain()) {
                                TokenDetailActivity tokenDetailActivity2 = TokenDetailActivity.this;
                                tokenDetailActivity2.toast(tokenDetailActivity2.getResources().getString(R.string.ledger_not_support_transfer_todappchain));
                                return;
                            }
                            TokenDetailActivity tokenDetailActivity3 = TokenDetailActivity.this;
                            tokenDetailActivity3.toast(tokenDetailActivity3.getResources().getString(R.string.ledger_not_support_on_dappchain));
                            return;
                        }
                        ((TokenDetailPresenter) mPresenter).doDepostit();
                        return;
                    case R.id.ll_market:
                        ((TokenDetailPresenter) mPresenter).doMarket();
                        return;
                    case R.id.ll_receive2:
                        AnalyticsHelper.logEvent(AnalyticsHelper.TokenDetailsPage.CLICK_TOKEN_DETAIL_PAGE_RECEIVE);
                        ((TokenDetailPresenter) mPresenter).doReceive();
                        return;
                    case R.id.ll_transfer2:
                        AnalyticsHelper.logEvent(AnalyticsHelper.TokenDetailsPage.CLICK_TOKEN_DETAIL_PAGE_TRANSFER);
                        if (!BackupReminder.isWalletBackup(((TokenDetailPresenter) mPresenter).getWallet())) {
                            TokenDetailActivity tokenDetailActivity4 = TokenDetailActivity.this;
                            BackupReminder.showBackupPopup(tokenDetailActivity4, ((TokenDetailPresenter) tokenDetailActivity4.mPresenter).getWallet(), TronConfig.type_receivables, getResources().getString(R.string.backup_tip_for_send));
                            return;
                        } else if (AssetsConfig.isCustomTokenAndNotSupportTransfer(((TokenDetailPresenter) mPresenter).getToken())) {
                            TokenDetailActivity tokenDetailActivity5 = TokenDetailActivity.this;
                            tokenDetailActivity5.toast(tokenDetailActivity5.getResources().getString(R.string.token_not_support_transfer));
                            return;
                        } else {
                            AccountUtils.getInstance().checkAccountIsActivatedFromLocalWithTokenBean(mContext, new CheckAccountActivatedCallback() {
                                @Override
                                public void isActivated() {
                                    ((TokenDetailPresenter) mPresenter).doTransfer();
                                }
                            }, null, ((TokenDetailPresenter) mPresenter).getToken());
                            return;
                        }
                    default:
                        return;
                }
            }
        };
        this.binding.llTransfer2.setOnClickListener(noDoubleClickListener2);
        this.binding.llReceive2.setOnClickListener(noDoubleClickListener2);
        this.binding.itemTokenDetailBottom.ivToggleSmallValue.setOnClickListener(noDoubleClickListener2);
        this.binding.llMarket.setOnClickListener(noDoubleClickListener2);
        this.binding.llDeposit.setOnClickListener(noDoubleClickListener2);
        this.binding.itemTokenDetailBottom.ivFilterSmallValueTip.setOnClickListener(noDoubleClickListener2);
    }

    private void mappingId() {
        this.mBottomBarLayout = this.binding.llBottomBar;
        this.mMarketLayout = this.binding.llMarket;
        this.mMenuLine1 = this.binding.llMenuLine1.llMenuLine;
        this.mMenuLine2 = this.binding.llMenuLine2.llMenuLine;
        this.mBottomView = this.binding.llTransferLayout;
        this.mTransferView = this.binding.llTransfer2;
        this.mReceiveView = this.binding.llReceive2;
        this.llDeposit = this.binding.llDeposit;
        this.ivType = this.binding.ivType;
        this.tvType = this.binding.tvType;
        this.ptrView = this.binding.pullToRefresh;
        this.progressView = this.binding.progressView;
        this.xTablayout = this.binding.itemTokenDetailBottom.xTablayout;
        this.vpContent = this.binding.itemTokenDetailBottom.vpContent;
        this.nestedLayout = this.binding.rcList;
        this.appBarLayout = this.binding.appbarLayout;
        this.contentRootView = this.binding.itemTokenDetailBottom.contentRoot;
        this.rlScam = this.binding.rlScam;
        this.liSmallVallSwitch = this.binding.itemTokenDetailBottom.liOption;
        this.ivToggleSmallValue = this.binding.itemTokenDetailBottom.ivToggleSmallValue;
        this.tvToggleSmallValue = this.binding.itemTokenDetailBottom.tvToggleSmallValue;
        this.ivFilterSmallValueTip = this.binding.itemTokenDetailBottom.ivFilterSmallValueTip;
        this.loadingView = this.binding.loadingview.loadingview;
    }

    public void syncCustomTokenInfo() {
        if (this.customTokenSyncing) {
            return;
        }
        ConfirmCustomPopupView.getBuilder(getIContext()).setTitle(getResources().getString(R.string.custom_token_sync_title)).setInfo(getResources().getString(R.string.custom_token_sync_info)).setConfirmText(getResources().getString(R.string.confirm)).setConfirmListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$syncCustomTokenInfo$0(view);
            }
        }).setCancleListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.CustomTokenPage.CLICK_CUSTOM_TOKEN_UPDATE_CANCEL);
            }
        }).build().show();
    }

    public void lambda$syncCustomTokenInfo$0(View view) {
        AnalyticsHelper.logEvent(AnalyticsHelper.CustomTokenPage.CLICK_CUSTOM_TOKEN_UPDATE_CONFIRM);
        this.customTokenSyncing = true;
        ((TokenDetailPresenter) this.mPresenter).syncCustomToken();
    }

    private void setWarningTagView(TokenBean tokenBean) {
        if (tokenBean != null && tokenBean.getTokenStatus() == 2) {
            this.rlScam.setVisibility(View.VISIBLE);
            this.rlScam.setInfo(getResources().getString(R.string.custom_token_has_record), getResources().getColor(R.color.white_90), null, true);
            this.rlScam.setOnClickListener(new ItemWarningTagView.OnClickTagListener() {
                @Override
                public void onClose() {
                    rlScam.setVisibility(View.GONE);
                }

                @Override
                public void onTap() {
                    AnalyticsHelper.logEvent(AnalyticsHelper.CustomTokenPage.CLICK_CUSTOM_TOKEN_UPDATE_TIP);
                    syncCustomTokenInfo();
                }
            });
        } else if (OfficialType.isScamOrUnSafeToken(this.tokenBean)) {
            TokenItemUtil.initScamToDetailView(this, this.rlScam, this.tokenBean);
        }
    }

    public void setSubTitle(String str, final String str2) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getHeaderHolder().tvCommonTitle2.getLayoutParams();
        getHeaderHolder().tvCommonTitle2.setSingleLine(true);
        getHeaderHolder().tvCommonTitle2.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        getHeaderHolder().tvCommonTitle2.setText(str);
        getHeaderHolder().tvCommonTitle2.setVisibility(View.VISIBLE);
        getHeaderHolder().tvCommonTitle2.setTextColor(getResources().getColor(R.color.white_50));
        getHeaderHolder().tvCommonTitle2.setTextSize(1, 13.0f);
        getHeaderHolder().tvCommonTitle2.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, getDrawable(R.mipmap.ic_id_copy), (Drawable) null);
        getHeaderHolder().tvCommonTitle2.setCompoundDrawablePadding(DensityUtils.dp2px(this, 2.0f));
        layoutParams.addRule(8, R.id.tv_common_title);
        layoutParams.bottomMargin = DensityUtils.dp2px(this, 5.0f);
        layoutParams.rightMargin = DensityUtils.dp2px(this, 80.0f);
        getHeaderHolder().tvCommonTitle2.setLayoutParams(layoutParams);
        getHeaderHolder().tvCommonTitle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIUtils.copy(str2);
                IToast.getIToast().show(mContext.getResources().getString(R.string.already_copy));
            }
        });
    }

    @Override
    public void hideSubTitleAndRightIcon() {
        getHeaderHolder().tvCommonTitle2.setVisibility(View.GONE);
        getHeaderHolder().rlIconRight.setVisibility(View.GONE);
    }

    @Override
    public boolean isIDestroyed() {
        return isDestroyed();
    }

    @Override
    public void updateTokenInfo(TokenBean tokenBean) {
        this.customTokenSyncing = false;
        if (tokenBean == null) {
            ToastError(getResources().getString(R.string.custom_token_sync_fail));
            return;
        }
        this.rlScam.setVisibility(View.GONE);
        setHeaderBar(tokenBean.getShortName());
    }

    @Override
    public void setAddressMap(HashMap<String, NameAddressExtraBean> hashMap) {
        AddressMapUtils.getInstance().setAddressMap(hashMap);
    }

    @Override
    public void showLoadingPage() {
        this.loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissLoadingPage() {
        this.loadingView.setVisibility(View.GONE);
    }

    @Override
    protected void processData() {
        ((TokenDetailPresenter) this.mPresenter).addSome();
        this.contentRootView.bringToFront();
        setWarningTagView(((TokenDetailPresenter) this.mPresenter).getToken());
        this.filterSmallValue = SpAPI.THIS.getFilterSmallValue();
        TokenBean tokenBean = this.tokenBean;
        if ((tokenBean != null && BigDecimalUtils.lessThanOrEqual(tokenBean.getUsdPrice(), BigDecimal.ZERO)) || OfficialType.isScamOrUnSafeToken(this.tokenBean)) {
            this.filterSmallValue = false;
        }
        if (this.filterSmallValue) {
            this.tvToggleSmallValue.setTextColor(getResources().getColor(R.color.gray_9B));
            this.ivToggleSmallValue.setImageResource(R.mipmap.ic_checked_blue);
        } else {
            this.tvToggleSmallValue.setTextColor(getResources().getColor(R.color.gray_9B));
            this.ivToggleSmallValue.setImageResource(R.mipmap.ic_uncheck_grey);
        }
        TouchDelegateUtils.expandViewTouchDelegate(this.ivToggleSmallValue, 10);
        Bundle bundle = new Bundle();
        boolean equals = M.M_TRZ.equals(getIntent().getStringExtra(TronConfig.isTRX));
        String str = TronConfig.TRC10;
        if (!equals) {
            if (M.M_TRX.equals(getIntent().getStringExtra(TronConfig.isTRX))) {
                str = "TRX";
            } else if (M.M_TRC20.equals(getIntent().getStringExtra(TronConfig.isTRX))) {
                str = TronConfig.TRC20;
            } else if (!M.M_TRC10.equals(getIntent().getStringExtra(TronConfig.isTRX))) {
                str = "";
            }
        }
        bundle.putString("token_type", str);
        if (!this.tokenBean.getTransferStatus()) {
            this.mBottomView.setVisibility(View.GONE);
        }
        TokenBean tokenBean2 = this.tokenBean;
        if ((tokenBean2 != null && BigDecimalUtils.lessThanOrEqual(tokenBean2.getUsdPrice(), BigDecimal.ZERO)) || OfficialType.isScamOrUnSafeToken(this.tokenBean)) {
            this.liSmallVallSwitch.setVisibility(View.GONE);
        }
        AnalyticsHelper.logEvent(AnalyticsHelper.TokenDetailsPage.ENTER_TOKEN_DETAIL_PAGE, bundle);
        Wallet wallet = ((TokenDetailPresenter) this.mPresenter).getWallet();
        if (wallet == null || !wallet.isWatchNotPaired()) {
            return;
        }
        this.mBottomBarLayout.setVisibility(View.GONE);
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        AnalyticsHelper.logEventFormat(AnalyticsHelper.TokenDetails.CLICK_BACK, Integer.valueOf(getTypeIndex()));
        finish();
    }

    @Override
    public int getTypeIndex() {
        if (getIntent() != null) {
            if (M.M_TRX.equals(getIntent().getStringExtra(TronConfig.isTRX))) {
                return 1;
            }
            return M.M_TRC10.equals(getIntent().getStringExtra(TronConfig.isTRX)) ? 2 : 3;
        }
        return 0;
    }

    @Override
    public void showFilterSmallValue(boolean z) {
        this.liSmallVallSwitch.setVisibility(z ? 0 : 4);
    }

    @Override
    public Intent getIIntent() {
        return getIntent();
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent == null || intent.getIntExtra("from", 0) != 1) {
            return;
        }
        TokenBean tokenBean = (TokenBean) intent.getParcelableExtra(TronConfig.TRC);
        if (tokenBean != null && !tokenBean.equals(((TokenDetailPresenter) this.mPresenter).getToken())) {
            ((TokenDetailPresenter) this.mPresenter).refreshData(intent);
            getIntent().putExtra(TronConfig.isTRX, intent.getStringExtra(TronConfig.isTRX));
            getIntent().putExtra(TronConfig.TITLE, intent.getStringExtra(TronConfig.TITLE));
            getIntent().putExtra(TronConfig.TRC, tokenBean);
            return;
        }
        ((TokenDetailPresenter) this.mPresenter).updateHeader();
        ((TokenDetailPresenter) this.mPresenter).historyTask();
    }

    @Override
    public void scan() {
        PermissionHelper permissionHelper = new PermissionHelper(this, this);
        this.mPermissionHelper = permissionHelper;
        permissionHelper.requestPermissions();
    }

    @Override
    public void refreshBottomBarForIsMarket(boolean r5, boolean r6, boolean r7) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.transfer.TokenDetailActivity.refreshBottomBarForIsMarket(boolean, boolean, boolean):void");
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        ((TokenDetailPresenter) this.mPresenter).onActivityResult(i, i2, intent);
    }

    @Override
    public String[] getPermissions() {
        return new String[]{"android.permission.CAMERA"};
    }

    @Override
    public void requestPermissionsSuccess() {
        toScan();
    }

    private void toScan() {
        ScannerActivity.start(this);
    }

    @Override
    public void requestPermissionsFail() {
        ToastError(R.string.error_permission);
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        PermissionHelper permissionHelper = this.mPermissionHelper;
        if (permissionHelper == null || !permissionHelper.requestPermissionsResult(i, strArr, iArr)) {
            super.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    @Override
    public void onRightButtonClick() {
        super.onRightButtonClick();
        Intent intent = new Intent(this.mContext, ProjectIntroductionActivity.class);
        intent.putExtra(TronConfig.isTRX, getIntent().getStringExtra(TronConfig.isTRX));
        intent.putExtra(TronConfig.TITLE, getIntent().getStringExtra(TronConfig.TITLE));
        intent.putExtra(TronConfig.TRC, ((TokenDetailPresenter) this.mPresenter).getToken());
        intent.putExtra(TronConfig.ID, ((TokenDetailPresenter) this.mPresenter).getTokenId());
        this.mContext.startActivity(intent);
    }

    private void showPop(final Context context) {
        AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
            @Override
            public final void doInUIThread() {
                ConfirmCustomPopupView.getBuilder(r0).setTitle(r0.getResources().getString(R.string.not_activated_tips)).setTitleBold(true).setTitleSize(16).setConfirmText(r0.getResources().getString(R.string.confirm)).setConfirmListener(new View.OnClickListener() {
                    @Override
                    public final void onClick(View view) {
                        TokenDetailActivity.lambda$showPop$2(view);
                    }
                }).setShowCancelBtn(true).setCancelText(r0.getResources().getString(R.string.multisig_transfer)).setCancleListener(new View.OnClickListener() {
                    @Override
                    public final void onClick(View view) {
                        r0.startActivity(new Intent(r1, MultiSelectAddressActivity.class));
                    }
                }).build().show();
            }
        });
    }
}
