package com.tron.wallet.business.tokendetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.view.GravityCompat;
import com.google.protobuf.ByteString;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.addassets2.AssetsConfig;
import com.tron.wallet.business.mutil.MultiSelectAddressActivity;
import com.tron.wallet.business.mutil.bean.MultiSourcePageEnum;
import com.tron.wallet.business.stakev2.managementv2.ResourcesBean;
import com.tron.wallet.business.stakev2.stake.StakeHomeActivity;
import com.tron.wallet.business.tabmarket.home.LazyLoadFragment;
import com.tron.wallet.business.tokendetail.TokenDetailHeaderFragment;
import com.tron.wallet.business.tokendetail.mvp.TokenDetailHeaderContract;
import com.tron.wallet.business.tokendetail.mvp.TokenDetailHeaderPresenter;
import com.tron.wallet.business.tronpower.stake.StakeTRXActivity;
import com.tron.wallet.common.adapter.holder.swap.TokenHolder;
import com.tron.wallet.common.bean.Price;
import com.tron.wallet.common.bean.token.OfficialType;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.TokenLogoDraweeView;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.M;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.config.TronSetting;
import com.tron.wallet.common.task.PriceUpdater;
import com.tron.wallet.common.utils.AccountUtils;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.DataStatHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.OwnerPermissionCheckUtils;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.ItemTransferTopBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import j$.util.Objects;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import org.slf4j.Marker;
import org.tron.protos.Protocol;
import org.tron.walletserver.ConnectErrorException;
import org.tron.walletserver.Wallet;
public class TokenDetailHeaderFragment extends LazyLoadFragment<TokenDetailHeaderPresenter, EmptyModel> implements TokenDetailHeaderContract.View {
    private Protocol.Account account;
    private double balance;
    private ItemTransferTopBinding binding;
    private long freezed;
    private List<ByteString> fromAccountsList;
    ImageView icTokenRightArrow;
    private boolean isFromTransfer;
    private boolean isMapping;
    private String isTrx;
    View ivOfficialView;
    LinearLayout liHeadRoot;
    LinearLayout llHorPrice;
    private Context mContext;
    ViewGroup mPriceTrxSpanLayout;
    private double mPriceUsdOrCny;
    private double pow;
    TextView price;
    RelativeLayout rlCurrentPrice;
    RelativeLayout rlTokenIcon;
    private String s;
    private TokenBean token;
    private double totalBalance;
    TokenLogoDraweeView trx;
    TextView trxPrice;
    TextView tvBalance;
    TextView tvCount;
    TextView tvCurrentPrice;
    TextView tvFreezeAmout;
    TextView tvNoteDetail;
    TextView tvPrice;
    TextView tvSearnings;
    TextView tvYearnings;
    private byte[] address = null;
    private boolean isFirst = true;
    private boolean isFirstShowTrx = true;
    private Wallet selectedWallet = WalletUtils.getSelectedWallet();
    private final NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
    private final NumberFormat mNumberFormat = NumberFormat.getNumberInstance(Locale.US);
    private final Price trx_price = PriceUpdater.getTRX_price();
    private RxManager rxManager = new RxManager();

    @Override
    public TokenBean getToken() {
        return this.token;
    }

    @Override
    public String getTokenType() {
        return this.isTrx;
    }

    @Override
    public void showPriceLoading(boolean z) {
    }

    @Override
    protected void firstLoad() {
        refresh();
    }

    @Override
    protected void refreshLoad() {
        refresh();
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        bindHolder();
    }

    @Override
    protected void processData() {
        if (this.mPresenter != 0) {
            ((TokenDetailHeaderPresenter) this.mPresenter).addSome();
            showPriceLoading(true);
            ((TokenDetailHeaderPresenter) this.mPresenter).addTimer();
        }
        this.rlTokenIcon.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                long j;
                Intent intent = new Intent(mContext, ProjectIntroductionActivity.class);
                intent.putExtra(TronConfig.isTRX, isTrx);
                intent.putExtra(TronConfig.TITLE, token.shortName.equals("") ? token.name : token.shortName);
                intent.putExtra(TronConfig.TRC, token);
                if (token == null || token.getId() == null) {
                    j = 0;
                } else {
                    j = Long.parseLong(token.getId().equals("") ? "0" : token.getId());
                }
                intent.putExtra(TronConfig.ID, j);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ItemTransferTopBinding inflate = ItemTransferTopBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        LinearLayout root = inflate.getRoot();
        setType(0);
        mappingId();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId() {
        this.trx = this.binding.trx;
        this.rlTokenIcon = this.binding.rlTokenIcon;
        this.tvCount = this.binding.tvCount;
        this.tvNoteDetail = this.binding.tvNoteDetail;
        this.price = this.binding.price;
        this.trxPrice = this.binding.tvPriceTrx;
        this.llHorPrice = this.binding.llPrice;
        this.tvPrice = this.binding.tvPrice;
        this.tvBalance = this.binding.tvBalance;
        this.tvFreezeAmout = this.binding.tvFreezeAmout;
        this.mPriceTrxSpanLayout = this.binding.rlPriceTrx;
        this.icTokenRightArrow = this.binding.icTokenRightArrow;
        this.tvYearnings = this.binding.tvYearnings;
        this.tvSearnings = this.binding.tvSumearnings;
        this.liHeadRoot = this.binding.tokenHeaderRoot;
        this.ivOfficialView = this.binding.ivOfficialImage;
        this.rlCurrentPrice = this.binding.rlCurrentPrice;
        this.tvCurrentPrice = this.binding.tvCurrentPrice;
    }

    public void init(Context context, String str, TokenBean tokenBean, double d, boolean z, boolean z2) {
        this.isTrx = str;
        this.token = tokenBean;
        this.mContext = context;
        this.mPriceUsdOrCny = d;
        this.isMapping = z;
        this.isFromTransfer = true;
        try {
            Wallet wallet = this.selectedWallet;
            if (wallet == null || !wallet.isShieldedWallet()) {
                Wallet wallet2 = this.selectedWallet;
                this.address = StringTronUtil.decodeFromBase58Check(wallet2 == null ? "" : wallet2.getAddress());
            }
            Wallet wallet3 = this.selectedWallet;
            if (wallet3 != null) {
                this.account = WalletUtils.getAccount(this.mContext, wallet3.getWalletName());
            }
        } catch (Exception e) {
            SentryUtil.captureException(e);
            LogUtils.e(e);
        }
        TokenBean tokenBean2 = this.token;
        if (tokenBean2 != null && tokenBean2.getPrecision() != 0) {
            this.numberFormat.setMaximumFractionDigits(this.token.getPrecision());
        } else {
            this.numberFormat.setMaximumFractionDigits(6);
        }
    }

    public void updateTokenInfo(TokenBean tokenBean) {
        this.isFromTransfer = true;
        this.token = tokenBean;
        if (tokenBean != null && !StringTronUtil.isEmpty(tokenBean.logoUrl) && !this.token.logoUrl.endsWith("svg")) {
            this.trx.setTokenImage(this.token);
        } else {
            this.trx.setImageResource(AssetsConfig.getTokenDefaultLogoIconId(this.token));
        }
        refresh();
    }

    public void update(TokenBean tokenBean, String str) {
        this.isTrx = str;
        this.token = tokenBean;
        this.selectedWallet = WalletUtils.getSelectedWallet();
        if (tokenBean != null && tokenBean.getPrecision() != 0) {
            this.numberFormat.setMaximumFractionDigits(tokenBean.getPrecision());
        } else {
            this.numberFormat.setMaximumFractionDigits(6);
        }
    }

    @Override
    public void bindHolder() {
        TokenBean tokenBean;
        TokenBean tokenBean2;
        if (!isAdded() || (tokenBean = this.token) == null) {
            return;
        }
        if (tokenBean != null && tokenBean.inActivity && TronConfig.hasNet) {
            this.mNumberFormat.setMaximumFractionDigits(3);
            this.pow = Math.pow(10.0d, this.token.precision);
            this.tvYearnings.setText(Marker.ANY_NON_NULL_MARKER + this.mNumberFormat.format(this.token.yesterdayEarnings / this.pow));
            this.tvSearnings.setText(Marker.ANY_NON_NULL_MARKER + this.mNumberFormat.format(this.token.totalEarnings / this.pow));
        }
        TokenBean tokenBean3 = this.token;
        if (tokenBean3 != null) {
            this.ivOfficialView.setVisibility(tokenBean3.getIsOfficial() == 1 ? View.VISIBLE : View.GONE);
        }
        if (M.M_TRX.equals(this.isTrx)) {
            this.llHorPrice.setVisibility(View.GONE);
            this.mPriceTrxSpanLayout.setVisibility(View.VISIBLE);
            this.tvPrice.setVisibility(View.VISIBLE);
            this.trx.setImageResource(R.mipmap.trx);
            if (this.isFirstShowTrx) {
                this.isFirstShowTrx = false;
            }
            showTRXTokenInfo();
            String str = this.trx_price.getPrice() + "";
            if (StringTronUtil.isEmpty(str)) {
                TextView textView = this.tvCurrentPrice;
                StringBuilder sb = new StringBuilder();
                sb.append(SpAPI.THIS.isUsdPrice() ? "$" : Html.fromHtml("&yen;"));
                sb.append("0");
                textView.setText(sb.toString());
            } else {
                double doubleValue = new BigDecimal(str).doubleValue();
                if (doubleValue < 1.0E-6d) {
                    TextView textView2 = this.tvCurrentPrice;
                    StringBuilder sb2 = new StringBuilder("<");
                    sb2.append(SpAPI.THIS.isUsdPrice() ? "$" : Html.fromHtml("&yen;"));
                    sb2.append("0.000001");
                    textView2.setText(sb2.toString());
                } else {
                    TextView textView3 = this.tvCurrentPrice;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(SpAPI.THIS.isUsdPrice() ? "$" : Html.fromHtml("&yen;"));
                    sb3.append(StringTronUtil.formatNumber6TruncatePlainScientific(doubleValue));
                    textView3.setText(sb3.toString());
                }
            }
            if (this.selectedWallet.isWatchNotPaired()) {
                this.icTokenRightArrow.setVisibility(View.GONE);
            } else {
                this.mPriceTrxSpanLayout.setOnClickListener(new fun2());
            }
        } else if (M.M_TRC10.equals(this.isTrx) || M.M_TRZ.equals(this.isTrx)) {
            this.mPriceTrxSpanLayout.setVisibility(View.GONE);
            this.llHorPrice.setVisibility(View.VISIBLE);
            this.tvPrice.setVisibility(View.GONE);
            this.trx.setTokenImage(this.token);
            this.tvCount.setText(StringTronUtil.plainScientificNotation(new BigDecimal(this.token.getBalanceStr())));
            if (this.token.isShield()) {
                this.trx.setImageResource(R.mipmap.trz_icon);
            }
            this.trxPrice.setText(StringTronUtil.formatNumber3Truncate(Double.valueOf(this.token.trxCount)) + "\tTRX");
            if (this.trx_price.getPrice() > 0.0f) {
                BigDecimal tokenTotalPriceNumber = AssetsConfig.getTokenTotalPriceNumber(this.token, this.trx_price.getPrice());
                if (!BigDecimalUtils.equalsZero((Number) Double.valueOf(this.token.getTrxCount() * this.trx_price.getPrice())) && BigDecimalUtils.MoreThan("0.000001", tokenTotalPriceNumber)) {
                    this.price.setText(StringTronUtil.formatPriceLessThan(Double.valueOf(1.0E-6d)));
                } else {
                    this.price.setText(StringTronUtil.formatPrice3(tokenTotalPriceNumber));
                }
            } else {
                this.price.setText(TokenHolder.PLACE_HOLDER);
            }
            if (OfficialType.isScamOrUnSafeToken(this.token)) {
                this.rlCurrentPrice.setVisibility(View.GONE);
                this.llHorPrice.setVisibility(View.GONE);
            }
            String usdPrice = SpAPI.THIS.isUsdPrice() ? this.token.getUsdPrice() : this.token.getCnyPrice();
            if (StringTronUtil.isEmpty(usdPrice)) {
                TextView textView4 = this.tvCurrentPrice;
                StringBuilder sb4 = new StringBuilder();
                sb4.append(SpAPI.THIS.isUsdPrice() ? "$" : Html.fromHtml("&yen;"));
                sb4.append("0");
                textView4.setText(sb4.toString());
            } else {
                double doubleValue2 = new BigDecimal(usdPrice).doubleValue();
                if (doubleValue2 < 1.0E-6d) {
                    TextView textView5 = this.tvCurrentPrice;
                    StringBuilder sb5 = new StringBuilder("<");
                    sb5.append(SpAPI.THIS.isUsdPrice() ? "$" : Html.fromHtml("&yen;"));
                    sb5.append("0.000001");
                    textView5.setText(sb5.toString());
                } else {
                    TextView textView6 = this.tvCurrentPrice;
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append(SpAPI.THIS.isUsdPrice() ? "$" : Html.fromHtml("&yen;"));
                    sb6.append(StringTronUtil.formatNumber6TruncatePlainScientific(doubleValue2));
                    textView6.setText(sb6.toString());
                }
            }
            this.tvNoteDetail.setVisibility(View.GONE);
        } else {
            this.mPriceTrxSpanLayout.setVisibility(View.GONE);
            this.llHorPrice.setVisibility(View.VISIBLE);
            this.tvPrice.setVisibility(View.GONE);
            TokenBean tokenBean4 = this.token;
            if (tokenBean4 != null && !StringTronUtil.isEmpty(tokenBean4.logoUrl) && !this.token.logoUrl.endsWith("svg")) {
                this.trx.setTokenImage(this.token);
            } else {
                this.trx.setImageResource(AssetsConfig.getTokenDefaultLogoIconId(this.token));
            }
            String usdPrice2 = SpAPI.THIS.isUsdPrice() ? this.token.getUsdPrice() : this.token.getCnyPrice();
            if (StringTronUtil.isEmpty(usdPrice2)) {
                TextView textView7 = this.tvCurrentPrice;
                StringBuilder sb7 = new StringBuilder();
                sb7.append(SpAPI.THIS.isUsdPrice() ? "$" : Html.fromHtml("&yen;"));
                sb7.append("0");
                textView7.setText(sb7.toString());
            } else {
                double doubleValue3 = new BigDecimal(usdPrice2).doubleValue();
                if (doubleValue3 < 1.0E-6d) {
                    TextView textView8 = this.tvCurrentPrice;
                    StringBuilder sb8 = new StringBuilder("<");
                    sb8.append(SpAPI.THIS.isUsdPrice() ? "$" : Html.fromHtml("&yen;"));
                    sb8.append("0.000001");
                    textView8.setText(sb8.toString());
                } else {
                    TextView textView9 = this.tvCurrentPrice;
                    StringBuilder sb9 = new StringBuilder();
                    sb9.append(SpAPI.THIS.isUsdPrice() ? "$" : Html.fromHtml("&yen;"));
                    sb9.append(StringTronUtil.formatNumber6TruncatePlainScientific(doubleValue3));
                    textView9.setText(sb9.toString());
                }
            }
            if (this.token != null) {
                this.tvCount.setText(StringTronUtil.plainScientificNotation(new BigDecimal(StringTronUtil.isNullOrEmpty(this.token.getBalanceStr()) ? "0" : this.token.getBalanceStr())));
                this.trxPrice.setText(String.format("%s\tTRX", StringTronUtil.formatNumber3Truncate(Double.valueOf(this.token.trxCount))));
            } else {
                this.tvCount.setText("");
                this.trxPrice.setText("");
            }
            if (OfficialType.isScamOrUnSafeToken(this.token)) {
                this.rlCurrentPrice.setVisibility(View.GONE);
                this.llHorPrice.setVisibility(View.GONE);
            }
            this.price.setVisibility(View.VISIBLE);
            if (this.trx_price.getPrice() > 0.0f && (tokenBean2 = this.token) != null) {
                BigDecimal tokenTotalPriceNumber2 = AssetsConfig.getTokenTotalPriceNumber(tokenBean2, this.trx_price.getPrice());
                if (!BigDecimalUtils.equalsZero((Number) Double.valueOf(this.token.getTrxCount() * this.trx_price.getPrice())) && BigDecimalUtils.MoreThan("0.001", tokenTotalPriceNumber2)) {
                    this.price.setText(StringTronUtil.formatPriceLessThan(Double.valueOf(0.001d)));
                } else {
                    this.price.setText(StringTronUtil.formatPrice3(tokenTotalPriceNumber2));
                }
                this.price.post(new Runnable() {
                    @Override
                    public final void run() {
                        lambda$bindHolder$0();
                    }
                });
                return;
            }
            this.price.setText(TokenHolder.PLACE_HOLDER);
        }
    }

    public class fun2 extends NoDoubleClickListener2 {
        fun2() {
        }

        @Override
        protected void onNoDoubleClick(View view) {
            AnalyticsHelper.logEvent(AnalyticsHelper.ResourceGate.CLICK_TOKEN_DETAIL_PAGE_STAKED);
            AnalyticsHelper.logEventFormat(AnalyticsHelper.TokenDetails.CLICK_STAKED, Integer.valueOf(getTypeIndex()));
            if (TronSetting.stakeV2) {
                Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
                if (selectedPublicWallet != null) {
                    if (AccountUtils.checkAccountIsNotActivated(WalletUtils.getAccount(mContext, selectedPublicWallet.getWalletName()))) {
                        OwnerPermissionCheckUtils.showStakeNotActivatedPopup(mContext, R.string.stake_account_unactive, new Consumer() {
                            @Override
                            public final void accept(Object obj) {
                                TokenDetailHeaderFragment.2.this.lambda$onNoDoubleClick$0((Void) obj);
                            }

                            public Consumer andThen(Consumer consumer) {
                                return Objects.requireNonNull(consumer);
                            }
                        });
                        return;
                    } else {
                        StakeHomeActivity.start(mContext, null, false, DataStatHelper.StatAction.Stake, selectedWallet.getAddress(), null);
                        return;
                    }
                }
                return;
            }
            StakeTRXActivity.startWithCheckOwnerPermission(mContext, account, DataStatHelper.StatAction.Stake, selectedWallet.getAddress());
        }

        public void lambda$onNoDoubleClick$0(Void r2) {
            MultiSelectAddressActivity.start(mContext, MultiSourcePageEnum.StakeV2);
        }
    }

    public void lambda$bindHolder$0() {
        this.price.setGravity((this.price.getLineCount() > 1 ? GravityCompat.END : GravityCompat.START) | 16);
    }

    public int getTypeIndex() {
        String str = this.isTrx;
        if (str != null) {
            if (M.M_TRX.equals(str)) {
                return 1;
            }
            return M.M_TRC10.equals(this.isTrx) ? 2 : 3;
        }
        return 0;
    }

    private void showTRXTokenInfo() {
        TokenBean tokenBean;
        if (!M.M_TRX.equals(this.isTrx) || (tokenBean = this.token) == null) {
            return;
        }
        try {
            this.balance = tokenBean.getBalance();
            this.totalBalance = this.token.getTotalBalance();
            TextView textView = this.trxPrice;
            textView.setText(StringTronUtil.formatNumber3Truncate(Double.valueOf(this.totalBalance)) + "\tTRX");
            this.tvCount.setText(this.numberFormat.format(this.totalBalance));
            this.price.setVisibility(View.VISIBLE);
            if (this.trx_price.getPrice() > 0.0f) {
                this.tvPrice.setText(StringTronUtil.formatPrice3(Double.valueOf(this.totalBalance * this.trx_price.getPrice())));
            } else {
                this.tvPrice.setText(StringTronUtil.formatPrice3(Double.valueOf(this.totalBalance * SpAPI.THIS.getPrice())));
            }
            this.tvBalance.setText(this.numberFormat.format(this.balance));
            this.freezed = 0L;
            ResourcesBean resourcesBean = new ResourcesBean();
            ResourcesBean.buildStakedTrx(resourcesBean, this.account);
            long stakedSun = resourcesBean.getStakedSun();
            this.freezed = stakedSun;
            this.tvFreezeAmout.setText(this.numberFormat.format(stakedSun / 1000000));
        } catch (Exception e) {
            SentryUtil.captureException(e);
        }
    }

    public void query() {
        ((BaseActivity) this.mContext).runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$query$2();
            }
        });
    }

    public void lambda$query$2() {
        final boolean z = false;
        try {
            this.account = TronAPI.queryAccount(StringTronUtil.decodeFromBase58Check(this.selectedWallet.getAddress()), false);
            z = true;
        } catch (ConnectErrorException e) {
            LogUtils.e(e);
            this.isFromTransfer = false;
        }
        ((BaseActivity) this.mContext).runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$query$1(z);
            }
        });
    }

    public void lambda$query$1(boolean z) {
        Protocol.Account account;
        if (z && (account = this.account) != null && account.toString().length() != 0) {
            this.token.balance = BigDecimalUtils.div(this.account.getBalance(), 1000000.0d, 6);
            this.balance = BigDecimalUtils.div(this.account.getBalance(), 1000000.0d, 6);
            this.token.totalBalance = BigDecimalUtils.div(this.account.getBalance() + this.freezed, 1000000.0d, 6);
            RxManager rxManager = this.rxManager;
            rxManager.post(Event.BALANCE, this.token.balance + "");
        }
        showPriceLoading(false);
        bindHolder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.rxManager.clear();
    }

    public void updateUI() {
        try {
            this.balance = BigDecimalUtils.div(this.account.getBalance(), 1000000.0d, 6);
            this.totalBalance = BigDecimalUtils.div(this.account.getBalance() + this.freezed, 1000000.0d, 6);
            TextView textView = this.trxPrice;
            textView.setText(StringTronUtil.formatNumber3Truncate(Double.valueOf(this.totalBalance)) + "\tTRX");
            this.tvCount.setText(StringTronUtil.plainScientificNotation(Double.valueOf(this.totalBalance)));
            this.price.setVisibility(View.VISIBLE);
            if (this.trx_price.getPrice() > 0.0f) {
                this.tvPrice.setText(StringTronUtil.formatPrice3(Double.valueOf(this.totalBalance * this.trx_price.getPrice())));
            }
            this.tvBalance.setText(this.numberFormat.format(this.balance));
            this.tvFreezeAmout.setText(this.numberFormat.format(this.freezed / 1000000));
            showPriceLoading(false);
        } catch (Exception e) {
            SentryUtil.captureException(e);
        }
    }

    @Override
    public void refresh() {
        this.price.setVisibility(View.VISIBLE);
        if (M.M_TRX.equals(this.isTrx)) {
            query();
        }
        if (!M.M_TRX.equals(this.isTrx)) {
            searchDbBalance();
        }
        if (this.isFirst) {
            this.isFirst = false;
        }
    }

    public void searchDbBalance() {
        getReleaseData();
    }

    public void getReleaseData() {
        if (this.mPresenter != 0) {
            ((TokenDetailHeaderPresenter) this.mPresenter).requestHomeAssets();
        }
    }

    @Override
    public boolean isIDestroyed() {
        try {
            return getActivity().isDestroyed();
        } catch (Exception e) {
            LogUtils.e(e);
            return true;
        }
    }
}
