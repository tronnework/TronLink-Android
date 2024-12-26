package com.tron.wallet.business.transfer.selecttoken;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.lxj.xpopup.util.KeyboardUtils;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.AssetsConfig;
import com.tron.wallet.business.addassets2.bean.AssetsQueryOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftItemInfo;
import com.tron.wallet.business.addassets2.selecttoken.SelectTokenBottomPopup;
import com.tron.wallet.business.confirm.parambuilder.bean.TransferParam;
import com.tron.wallet.business.mutil.bean.MultiSignPermissionData;
import com.tron.wallet.business.nft.selectitem.SelectNftItemBottomPopup;
import com.tron.wallet.business.transfer.selecttoken.TransferSelectTokenActivity;
import com.tron.wallet.business.transfer.selecttoken.TransferSelectTokenContract;
import com.tron.wallet.business.vote.util.BaseTextWatcher;
import com.tron.wallet.common.bean.token.OfficialType;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.components.CurrencyEditText;
import com.tron.wallet.common.components.ErrorBottomLayout;
import com.tron.wallet.common.components.ErrorView;
import com.tron.wallet.common.components.ItemWarningTagView;
import com.tron.wallet.common.config.AccountFeeManager;
import com.tron.wallet.common.config.T;
import com.tron.wallet.common.task.PriceUpdater;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.CustomFontUtils;
import com.tron.wallet.common.utils.ImageUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.SpannableUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TextDotUtils;
import com.tron.wallet.common.utils.TokenItemUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.common.utils.transaction.TriggerUtils;
import com.tron.wallet.databinding.AcTransferSelectTokenBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.LedgerWallet;
import com.tronlinkpro.wallet.R;
import io.reactivex.disposables.Disposable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.time.DurationKt;
import org.apache.commons.cli.HelpFormatter;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
public class TransferSelectTokenActivity extends BaseActivity<TransferSelectTokenPresenter, TransferSelectTokenModel> implements TransferSelectTokenContract.View {
    private static final String KEY_MULTI_SIGN = "multiSign";
    private static final String KEY_MULTI_SIGN_DATA = "multiSignData";
    private static final String KEY_NFT_ITEM = "nftItem";
    private static final String KEY_PARAM = "transferParam";
    private static final String KEY_SEND_ACCOUNT_NAME = "sendAccountName";
    private AmountInfo activeAmountInfo;
    AcTransferSelectTokenBinding binding;
    View btNoteRemove;
    Button btSend;
    private boolean clickedMaxAmount;
    private CollectionsItemInfo collectionsItemInfo;
    CurrencyEditText etAmount;
    EditText etNote;
    private boolean hasSelectedTokenBean;
    private boolean isEditingNote;
    private boolean isMultiSign;
    ImageView ivAddNote;
    View ivDelete;
    SimpleDraweeView ivNftItem;
    View ivOfficial;
    View ivSelect;
    SimpleDraweeView ivTokenIcon;
    View llAddNote;
    View llCollection;
    View llContent;
    ErrorBottomLayout llErrAmount;
    ErrorBottomLayout llErrCollection;
    ErrorBottomLayout llErrSelectToken;
    View llNoCollection;
    View llNote;
    View llRoot;
    View llSelectToken;
    private BigDecimal maxAmountValue;
    private MultiSignPermissionData multiSignPermissionData;
    private NftItemInfo nftItemInfo;
    private NumberFormat numberFormat;
    private ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;
    ItemWarningTagView rlScam;
    private String sendAccountName;
    private TokenAmountInfo tokenAmountInfo;
    private TokenBean tokenBean;
    private TransferParam transferParam;
    private double trxBalance;
    TextView tvBalance;
    View tvMax;
    TextView tvMultiTip;
    TextView tvName;
    TextView tvNftId;
    TextView tvNftName;
    TextView tvNoteCount;
    ErrorView tvNoteTip;
    TextView tvSymbol;
    TextView tvValue;
    private final BigDecimal BIG_ZERO = new BigDecimal(0);
    private Map<String, String> tips = new HashMap();

    public interface AmountInfo {
        void active(boolean z);

        void checkInputAmount();

        BigDecimal getAmount();

        void initUI();

        boolean isValid();
    }

    static BigDecimal -$Nest$fgetBIG_ZERO(TransferSelectTokenActivity transferSelectTokenActivity) {
        return transferSelectTokenActivity.BIG_ZERO;
    }

    public static void lambda$onSelectedToken$1() {
    }

    public static void startActivity(Context context, TransferParam transferParam, Protocol.Account account, Protocol.Account account2, boolean z, String str, MultiSignPermissionData multiSignPermissionData, NftItemInfo nftItemInfo) {
        Intent intent = new Intent();
        intent.setClass(context, TransferSelectTokenActivity.class);
        intent.putExtra(KEY_NFT_ITEM, nftItemInfo);
        intent.putExtra(KEY_PARAM, transferParam);
        intent.putExtra(KEY_MULTI_SIGN, z);
        intent.putExtra(KEY_MULTI_SIGN_DATA, multiSignPermissionData);
        intent.putExtra(KEY_SEND_ACCOUNT_NAME, str);
        T.sendAccount = account;
        T.receiveAccount = account2;
        context.startActivity(intent);
    }

    @Override
    public void onCreate1(Bundle bundle) {
        super.onCreate1(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.transferParam = (TransferParam) intent.getParcelableExtra(KEY_PARAM);
        this.nftItemInfo = (NftItemInfo) intent.getParcelableExtra(KEY_NFT_ITEM);
        this.isMultiSign = intent.getBooleanExtra(KEY_MULTI_SIGN, false);
        this.multiSignPermissionData = (MultiSignPermissionData) intent.getParcelableExtra(KEY_MULTI_SIGN_DATA);
        this.sendAccountName = intent.getStringExtra(KEY_SEND_ACCOUNT_NAME);
        if (this.isMultiSign) {
            if (T.sendAccount != null) {
                this.trxBalance = T.sendAccount.getBalance() / 1000000.0d;
            }
        } else if (WalletUtils.getAccount(this, WalletUtils.getSelectedWallet().getWalletName()) != null) {
            this.trxBalance = WalletUtils.getAccount(this, WalletUtils.getSelectedWallet().getWalletName()).getBalance() / 1000000.0d;
        }
    }

    @Override
    protected void setLayout() {
        Resources resources;
        int i;
        AcTransferSelectTokenBinding inflate = AcTransferSelectTokenBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        LinearLayout root = inflate.getRoot();
        mappingId();
        setClick();
        setView(root, 1);
        if (this.isMultiSign) {
            resources = getResources();
            i = R.string.transfer_multi_sign;
        } else {
            resources = getResources();
            i = R.string.send;
        }
        setHeaderBar(resources.getString(i));
        setCommonTitle2(getString(this.isMultiSign ? R.string.step_3_3 : R.string.step_2_2));
    }

    public class fun1 extends NoDoubleClickListener2 {
        fun1() {
        }

        @Override
        protected void onNoDoubleClick(View view) {
            switch (view.getId()) {
                case R.id.bt_note_remove:
                    if (!isSending() && etNote.getText().length() > 0) {
                        ConfirmCustomPopupView.getBuilder(TransferSelectTokenActivity.this).setTitle(getString(R.string.confirm_clear_input)).setTitleSize(16).setConfirmText(getString(R.string.confirm)).setConfirmListener(new View.OnClickListener() {
                            @Override
                            public final void onClick(View view2) {
                                TransferSelectTokenActivity.1.this.lambda$onNoDoubleClick$0(view2);
                            }
                        }).build().show();
                        return;
                    }
                    return;
                case R.id.bt_send:
                    onClickSend();
                    return;
                case R.id.iv_delete:
                    if (isSending()) {
                        return;
                    }
                    etAmount.setText("");
                    return;
                case R.id.ll_add_note:
                    if (isSending()) {
                        return;
                    }
                    AnalyticsHelper.logEvent(isMultiSign ? AnalyticsHelper.TransferSelectTokenPage.CLICK_MULTI_TRANSFER_SELECT_TOKEN_PAGE_ADD_NOTE : AnalyticsHelper.TransferSelectTokenPage.CLICK_TRANSFER_SELECT_TOKEN_PAGE_ADD_NOTE);
                    if (llNote.getVisibility() == 0) {
                        if (etNote.hasFocus()) {
                            KeyboardUtils.hideSoftInput(etNote);
                        }
                        llNote.setVisibility(View.GONE);
                        ivAddNote.setImageResource(R.mipmap.ic_arrow_blue_down);
                        return;
                    }
                    llNote.setVisibility(View.VISIBLE);
                    ivAddNote.setImageResource(R.mipmap.ic_arrow_blue_up);
                    return;
                case R.id.tv_max:
                    if (isSending()) {
                        return;
                    }
                    AnalyticsHelper.logEvent(isMultiSign ? AnalyticsHelper.TransferSelectTokenPage.CLICK_MULTI_TRANSFER_SELECT_TOKEN_PAGE_AMOUNT_MAX : AnalyticsHelper.TransferSelectTokenPage.CLICK_TRANSFER_SELECT_TOKEN_PAGE_AMOUNT_MAX);
                    TextDotUtils.setText_Dot(etAmount, numberFormat.format(tokenBean.getBalanceBigDecimal()));
                    clickedMaxAmount = true;
                    calculateTrxFee();
                    etAmount.setSelection(etAmount.getText().length());
                    return;
                default:
                    return;
            }
        }

        public void lambda$onNoDoubleClick$0(View view) {
            etNote.setText("");
            calculateTrxFee();
        }
    }

    private void setClick() {
        1 r0 = new fun1();
        this.binding.llAddNote.setOnClickListener(r0);
        this.binding.btNoteRemove.setOnClickListener(r0);
        this.binding.tvMax.setOnClickListener(r0);
        this.binding.btSend.setOnClickListener(r0);
        this.binding.ivDelete.setOnClickListener(r0);
    }

    private void mappingId() {
        this.llRoot = this.binding.root;
        this.llContent = this.binding.llScroll;
        this.tvMultiTip = this.binding.tvMultiTip;
        this.llErrAmount = this.binding.llErrAmount;
        this.etAmount = this.binding.etCount;
        this.tvValue = this.binding.tvValue;
        this.tvMax = this.binding.tvMax;
        this.tvBalance = this.binding.tvBalance;
        this.ivDelete = this.binding.ivDelete;
        this.llErrSelectToken = this.binding.llErrSelectToken;
        this.llSelectToken = this.binding.llToken;
        this.tvSymbol = this.binding.tvSymbol;
        this.tvName = this.binding.tvName;
        this.ivTokenIcon = this.binding.ivTokenIcon;
        this.ivOfficial = this.binding.ivOfficialImage;
        this.ivSelect = this.binding.ivRow;
        this.llErrCollection = this.binding.llErrCollection;
        this.llCollection = this.binding.llCollection;
        this.tvNftName = this.binding.tvNftName;
        this.tvNftId = this.binding.tvNftId;
        this.ivNftItem = this.binding.ivNftItem;
        this.llNoCollection = this.binding.llNoCollection;
        this.llAddNote = this.binding.llAddNote;
        this.ivAddNote = this.binding.ivAddNote;
        this.llNote = this.binding.llNote;
        this.etNote = this.binding.etNote;
        this.tvNoteCount = this.binding.tvNoteCount;
        this.btNoteRemove = this.binding.btNoteRemove;
        this.tvNoteTip = this.binding.tvNoteTip;
        this.btSend = this.binding.btSend;
        this.rlScam = this.binding.rlScam;
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        AnalyticsHelper.logEvent(this.isMultiSign ? AnalyticsHelper.TransferSelectTokenPage.CLICK_MULTI_TRANSFER_SELECT_TOKEN_PAGE_BACK : AnalyticsHelper.TransferSelectTokenPage.CLICK_TRANSFER_SELECT_TOKEN_PAGE_BACK);
        finish();
    }

    @Override
    protected void processData() {
        this.tokenAmountInfo = new TokenAmountInfo();
        CollectionsItemInfo collectionsItemInfo = new CollectionsItemInfo();
        this.collectionsItemInfo = collectionsItemInfo;
        collectionsItemInfo.setNftItemInfo(this.nftItemInfo);
        ((TransferSelectTokenPresenter) this.mPresenter).setParam(this.transferParam);
        ((TransferSelectTokenPresenter) this.mPresenter).setMultiSign(this.isMultiSign);
        initUI();
        initSelectedTokenBean();
        ((TransferSelectTokenPresenter) this.mPresenter).start();
        AnalyticsHelper.logEvent(AnalyticsHelper.TransferSelectTokenPage.ENTER_TRANSFER_SELECT_TOKEN_PAGE);
    }

    private void initUI() {
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.numberFormat = numberInstance;
        numberInstance.setGroupingUsed(false);
        this.onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            private int preHeight;

            @Override
            public void onGlobalLayout() {
                if (this.preHeight == 0) {
                    this.preHeight = llRoot.getHeight();
                    return;
                }
                Rect rect = new Rect();
                llRoot.getWindowVisibleDisplayFrame(rect);
                int i = rect.bottom - rect.top;
                if (this.preHeight - i > UIUtils.dip2px(100.0f) && etNote.hasFocus()) {
                    int[] iArr = new int[2];
                    llNote.getLocationInWindow(iArr);
                    if (iArr[1] + llNote.getHeight() > rect.bottom) {
                        llContent.scrollBy(0, (iArr[1] + llNote.getHeight()) - rect.bottom);
                    }
                } else if (i - this.preHeight > UIUtils.dip2px(100.0f)) {
                    llContent.scrollTo(0, 0);
                    activeAmountInfo.checkInputAmount();
                    calculateTrxFee();
                }
                this.preHeight = i;
            }
        };
        this.llRoot.getViewTreeObserver().addOnGlobalLayoutListener(this.onGlobalLayoutListener);
        if (this.isMultiSign) {
            this.tvMultiTip.setVisibility(View.VISIBLE);
            final String fromAddress = this.transferParam.getFromAddress();
            if (!StringTronUtil.isEmpty(this.sendAccountName)) {
                fromAddress = this.sendAccountName + " (" + fromAddress + ")";
            }
            this.tvMultiTip.setText(getString(R.string.transfer_multi_sign_tip, new Object[]{fromAddress}));
            this.tvMultiTip.post(new Runnable() {
                @Override
                public final void run() {
                    lambda$initUI$0(fromAddress);
                }
            });
        }
        this.llSelectToken.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (isSending()) {
                    return;
                }
                onSelectedToken();
            }
        });
        this.tokenAmountInfo.initUI();
        this.collectionsItemInfo.initUI();
        if (AccountFeeManager.getMemoFee() > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            NumberFormat numberInstance2 = NumberFormat.getNumberInstance(Locale.US);
            numberInstance2.setMaximumFractionDigits(6);
            this.tvNoteTip.setVisibility(View.VISIBLE);
            this.tvNoteTip.setText((CharSequence) getString(R.string.note_fee_tip, new Object[]{numberInstance2.format(BigDecimalUtils.div(Double.valueOf(AccountFeeManager.getMemoFee()), Integer.valueOf((int) DurationKt.NANOS_IN_MILLIS)))}), true);
        }
        this.etNote.addTextChangedListener(new BaseTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                if (editable.toString().length() > 0) {
                    int length = editable.toString().length();
                    SpannableString spannableString = new SpannableString(length + "/200");
                    spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.gray_9B)), 0, String.valueOf(length).length(), 33);
                    tvNoteCount.setText(spannableString);
                    tvNoteCount.setVisibility(View.VISIBLE);
                    btNoteRemove.setVisibility(View.VISIBLE);
                    return;
                }
                tvNoteCount.setVisibility(View.GONE);
                btNoteRemove.setVisibility(View.GONE);
            }
        });
        this.etNote.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean z) {
                if (!z) {
                    calculateTrxFee();
                    isEditingNote = false;
                    return;
                }
                isEditingNote = true;
            }
        });
    }

    public void lambda$initUI$0(String str) {
        String[] autoSplitText = TextDotUtils.autoSplitText(this.tvMultiTip, str);
        this.tvMultiTip.setText(SpannableUtils.getTextColorSpannable(autoSplitText[0], autoSplitText[1], getResources().getColor(R.color.black_23)));
    }

    private void initSelectedTokenBean() {
        TokenBean tokenBean = this.transferParam.getTokenBean();
        if (tokenBean == null) {
            tokenBean = new TokenBean();
            tokenBean.setType(0);
            tokenBean.setName("TRX");
            tokenBean.setShortName("TRX");
        }
        if (this.isMultiSign) {
            tokenBean.setBalance(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
            tokenBean.setBalanceStr("0");
            setSelectedTokenBean(tokenBean);
            ((TransferSelectTokenPresenter) this.mPresenter).queryAssets(this.transferParam.getFromAddress(), tokenBean.getType(), tokenBean.getType() == 1 ? tokenBean.getId() : tokenBean.getContractAddress()).subscribe(new IObserver(new ICallback<AssetsQueryOutput>() {
                @Override
                public void onResponse(String str, AssetsQueryOutput assetsQueryOutput) {
                    if (assetsQueryOutput == null || assetsQueryOutput.getData() == null || hasSelectedTokenBean) {
                        return;
                    }
                    setSelectedTokenBean(assetsQueryOutput.getData());
                }

                @Override
                public void onFailure(String str, String str2) {
                    TransferSelectTokenActivity transferSelectTokenActivity = TransferSelectTokenActivity.this;
                    transferSelectTokenActivity.toast(transferSelectTokenActivity.getResources().getString(R.string.time_out));
                }

                @Override
                public void onSubscribe(Disposable disposable) {
                    ((TransferSelectTokenPresenter) mPresenter).mRxManager.add(disposable);
                }
            }, "initSelectedTokenBean"));
            return;
        }
        setSelectedTokenBean(tokenBean);
    }

    @Override
    public void onPause() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        }
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.onGlobalLayoutListener != null) {
            this.llRoot.getViewTreeObserver().removeOnGlobalLayoutListener(this.onGlobalLayoutListener);
        }
    }

    public void onSelectedToken() {
        AnalyticsHelper.logEvent(this.isMultiSign ? AnalyticsHelper.TransferSelectTokenPage.CLICK_MULTI_TRANSFER_SELECT_TOKEN_PAGE_SELECT_TOKEN : AnalyticsHelper.TransferSelectTokenPage.CLICK_TRANSFER_SELECT_TOKEN_PAGE_SELECT_TOKEN);
        SelectTokenBottomPopup.showPopup(this, this.tokenBean, this.isMultiSign, this.multiSignPermissionData, this.transferParam.getFromAddress()).setOnDismissListener(new SelectTokenBottomPopup.OnDismissListener() {
            @Override
            public final void onDismiss() {
                TransferSelectTokenActivity.lambda$onSelectedToken$1();
            }
        }).setOnItemClickListener(new SelectTokenBottomPopup.OnClickListener() {
            @Override
            public final void onClick(TokenBean tokenBean) {
                lambda$onSelectedToken$2(tokenBean);
            }
        });
    }

    public void lambda$onSelectedToken$2(TokenBean tokenBean) {
        this.hasSelectedTokenBean = true;
        setSelectedTokenBean(tokenBean);
    }

    public boolean isSending() {
        return getResources().getString(R.string.sending).equals(this.btSend.getText());
    }

    private void setSendingState(boolean z) {
        if (z) {
            this.btSend.setEnabled(false);
            this.btSend.setText(R.string.sending);
            this.etAmount.setEnabled(false);
            this.etNote.setEnabled(false);
            return;
        }
        this.btSend.setEnabled(true);
        this.btSend.setText(R.string.send);
        this.etAmount.setEnabled(true);
        this.etNote.setEnabled(true);
    }

    public void onClickSend() {
        AnalyticsHelper.logEvent(this.isMultiSign ? AnalyticsHelper.TransferSelectTokenPage.CLICK_MULTI_TRANSFER_SELECT_TOKEN_PAGE_CONFIRM : AnalyticsHelper.TransferSelectTokenPage.CLICK_TRANSFER_SELECT_TOKEN_PAGE_CONFIRM);
        if (!this.transferParam.getAccountActive() && this.transferParam.getFromAddress().equals(WalletUtils.getSelectedPublicWallet().getAddress()) && this.tokenBean.getType() == 1 && this.trxBalance < T.shieldFee && !TriggerUtils.enoughBandwidthToCreateAccount(WalletUtils.getSelectedWallet().getWalletName(), 350L)) {
            toast(getString(R.string.i_fee));
        } else if (LedgerWallet.isLedger(WalletUtils.getSelectedWallet()) && !"MainChain".equals(SpAPI.THIS.getCurrentChainName())) {
            toast(getString(R.string.ledger_not_support_on_dappchain));
        } else if (WalletUtils.getSelectedWallet().isWatchOnly() && !"MainChain".equals(SpAPI.THIS.getCurrentChainName())) {
            toast(getString(R.string.no_support));
        } else if (AssetsConfig.isCustomTokenAndNotSupportTransfer(this.tokenBean)) {
            toast(getString(R.string.token_not_support_transfer));
        } else {
            if (this.tokenBean.getType() == 0 || this.tokenBean.getType() == 1) {
                if (this.trxBalance < T.shieldFee) {
                    toast(getString(R.string.i_fee));
                    return;
                }
            } else if (((TransferSelectTokenPresenter) this.mPresenter).getWallet().isSamsungWallet() && !SpAPI.THIS.getCurrentChain().isMainChain) {
                toast(getString(R.string.no_samsung_to_shield));
                return;
            }
            T.isActiveAccount = this.transferParam.getAccountActive();
            setSendingState(true);
            try {
                Bundle bundle = new Bundle();
                bundle.putString(NotificationCompat.CATEGORY_EVENT, this.tokenBean.name);
                bundle.putString("value", this.activeAmountInfo.getAmount().stripTrailingZeros().toPlainString());
                FirebaseAnalytics.getInstance(this).logEvent("Submit_Transaction", bundle);
            } catch (Exception e) {
                SentryUtil.captureException(e);
            }
            ((TransferSelectTokenPresenter) this.mPresenter).send(this.etNote.getText().toString(), this.activeAmountInfo.getAmount(), this.activeAmountInfo instanceof CollectionsItemInfo ? this.collectionsItemInfo.getNftItemInfo() : null);
        }
    }

    private boolean hasMultiPermissionByToken(TokenBean tokenBean) {
        int type = tokenBean.getType();
        if (type != 0) {
            if (type != 1) {
                if (type == 2 || type == 5) {
                    return this.multiSignPermissionData.isTransferTRC20Permission();
                }
                return true;
            }
            return this.multiSignPermissionData.isTransferTRC10Permission();
        }
        return this.multiSignPermissionData.isTransferTRXPermission();
    }

    public void setSelectedTokenBean(TokenBean tokenBean) {
        this.tokenBean = tokenBean;
        ((TransferSelectTokenPresenter) this.mPresenter).setTokenBean(tokenBean);
        this.llErrSelectToken.clearError();
        if (tokenBean.getType() == 5) {
            CollectionsItemInfo collectionsItemInfo = this.collectionsItemInfo;
            this.activeAmountInfo = collectionsItemInfo;
            collectionsItemInfo.active(true);
            this.tokenAmountInfo.active(false);
        } else {
            this.activeAmountInfo = this.tokenAmountInfo;
            this.collectionsItemInfo.active(false);
            this.tokenAmountInfo.active(true);
        }
        if (this.isMultiSign && this.multiSignPermissionData != null && !hasMultiPermissionByToken(tokenBean)) {
            String[] permissionTip = MultiSignPermissionData.getPermissionTip(this.multiSignPermissionData);
            if (permissionTip != null) {
                this.llErrSelectToken.setError(ErrorBottomLayout.ErrorType.ERROR, String.format(getString(R.string.transfer_multi_permission_tip), permissionTip[1], permissionTip[0]));
            }
        } else if (AssetsConfig.isCustomTokenAndNotSupportTransfer(tokenBean)) {
            this.llErrSelectToken.setError(ErrorBottomLayout.ErrorType.ERROR, getString(R.string.token_not_support_to_transfer));
        } else if (!tokenBean.getTransferStatus()) {
            this.llErrSelectToken.setError(ErrorBottomLayout.ErrorType.ERROR, getString(R.string.token_not_support_to_transfer));
        }
        TokenItemUtil.initScamToDetailView(this, this.rlScam, tokenBean);
        this.rlScam.setLayoutDrawable(getDrawable(R.color.transparent));
        this.rlScam.setLayoutPadding(0, UIUtils.dip2px(10.0f), 0, 0);
    }

    public void setTokenLogoView(TokenBean tokenBean) {
        if (TextUtils.isEmpty(tokenBean.logoUrl)) {
            ImageUtils.loadAsCircleResource(this.ivTokenIcon, AssetsConfig.getTokenDefaultLogoIconId(tokenBean));
        } else {
            ImageUtils.loadAsCircle(this.ivTokenIcon, tokenBean.getLogoUrl());
        }
        this.ivOfficial.setVisibility(tokenBean.isOfficial == 1 ? View.VISIBLE : View.GONE);
    }

    public void checkBtSendState() {
        AmountInfo amountInfo;
        this.btSend.setEnabled((this.llErrSelectToken.getErrorType() == ErrorBottomLayout.ErrorType.ERROR || (amountInfo = this.activeAmountInfo) == null || !amountInfo.isValid()) ? false : true);
    }

    private void showTrxFeeTabs(Map<String, String> map) {
        ArrayList arrayList = new ArrayList();
        if (map.get(BandWidthHelper.TRX_FEE_BANDWIDTH) != null) {
            arrayList.add(map.get(BandWidthHelper.TRX_FEE_BANDWIDTH));
        }
        if (map.get(BandWidthHelper.TRX_FEE_ACTIVE) != null) {
            arrayList.add(map.get(BandWidthHelper.TRX_FEE_ACTIVE));
        }
        if (map.get(BandWidthHelper.TRX_FEE_MULTISIGN) != null) {
            arrayList.add(map.get(BandWidthHelper.TRX_FEE_MULTISIGN));
        }
        if (map.get(BandWidthHelper.TRX_FEE_MEMO) != null) {
            arrayList.add(map.get(BandWidthHelper.TRX_FEE_MEMO));
        }
        this.llErrAmount.setSupportErrorTabs(arrayList);
    }

    private boolean isEditingNote() {
        return (this.etNote.hasFocus() || this.isEditingNote) && this.etNote.getText().length() > 0 && !this.clickedMaxAmount;
    }

    public void calculateTrxFeeForBiggerInput() {
        double checkBandwidth = BandWidthHelper.checkBandwidth(((TransferSelectTokenPresenter) this.mPresenter).getSendAccountResource(), this.transferParam, this.tokenBean, StringTronUtil.getText(this.etNote), this.activeAmountInfo.getAmount().toPlainString(), this.isMultiSign, this.tips);
        this.llErrAmount.setSupportErrorTabs(null);
        if (this.tokenBean.getType() != 0) {
            this.llErrAmount.setError(ErrorBottomLayout.ErrorType.ERROR, getString(R.string.transfer_amount_is_too_big));
            return;
        }
        BigDecimal balanceBigDecimal = this.tokenBean.getBalanceBigDecimal();
        if (BigDecimalUtils.MoreThan(Double.valueOf(checkBandwidth), this.BIG_ZERO)) {
            BigDecimal sub_ = BigDecimalUtils.sub_(balanceBigDecimal, Double.valueOf(checkBandwidth));
            if (BigDecimalUtils.MoreThan(sub_, this.BIG_ZERO)) {
                this.llErrAmount.setError(ErrorBottomLayout.ErrorType.ERROR, getString(R.string.trx_max_transfer_amount_and_fee, new Object[]{StringTronUtil.formatNumber6Truncate(sub_)}));
            } else {
                this.llErrAmount.setError(ErrorBottomLayout.ErrorType.ERROR, getString(R.string.trx_no_balance_and_fee));
            }
            showTrxFeeTabs(this.tips);
            return;
        }
        this.llErrAmount.setError(ErrorBottomLayout.ErrorType.ERROR, getString(R.string.trx_max_transfer_amount, new Object[]{StringTronUtil.formatNumber6Truncate(balanceBigDecimal)}));
    }

    public void calculateTrxFee() {
        BigDecimal amount = this.activeAmountInfo.getAmount();
        double checkBandwidth = BandWidthHelper.checkBandwidth(((TransferSelectTokenPresenter) this.mPresenter).getSendAccountResource(), this.transferParam, this.tokenBean, StringTronUtil.getText(this.etNote), amount.toPlainString(), this.isMultiSign, this.tips);
        this.llErrAmount.setSupportErrorTabs(null);
        if (this.tokenBean.getType() != 0) {
            this.maxAmountValue = null;
            if (this.llErrAmount.getErrorType() != ErrorBottomLayout.ErrorType.ERROR && BigDecimalUtils.MoreThan(amount, this.BIG_ZERO) && BigDecimalUtils.LessThan(Double.valueOf(this.trxBalance), Double.valueOf(T.shieldFee))) {
                this.llErrAmount.setError(ErrorBottomLayout.ErrorType.ERROR, getString(R.string.no_trx));
            }
        } else if (this.etAmount.getText().length() == 0) {
            this.maxAmountValue = null;
            return;
        } else {
            BigDecimal balanceBigDecimal = this.tokenBean.getBalanceBigDecimal();
            if (BigDecimalUtils.MoreThan(amount, balanceBigDecimal)) {
                if (BigDecimalUtils.MoreThan(Double.valueOf(checkBandwidth), this.BIG_ZERO)) {
                    BigDecimal sub_ = BigDecimalUtils.sub_(balanceBigDecimal, Double.valueOf(checkBandwidth));
                    if (BigDecimalUtils.MoreThan(sub_, this.BIG_ZERO)) {
                        this.llErrAmount.setError(ErrorBottomLayout.ErrorType.ERROR, getString(R.string.trx_max_transfer_amount_and_fee, new Object[]{StringTronUtil.formatNumber6Truncate(sub_)}));
                    } else {
                        this.llErrAmount.setError(ErrorBottomLayout.ErrorType.ERROR, getString(R.string.trx_no_balance_and_fee));
                    }
                    showTrxFeeTabs(this.tips);
                } else {
                    this.llErrAmount.setError(ErrorBottomLayout.ErrorType.ERROR, getString(R.string.trx_max_transfer_amount, new Object[]{StringTronUtil.formatNumber6Truncate(balanceBigDecimal)}));
                }
            } else if (BigDecimalUtils.MoreThan(this.tokenBean.getBalanceBigDecimal(), this.BIG_ZERO) && (BigDecimalUtils.MoreThan(amount, this.BIG_ZERO) || this.maxAmountValue != null)) {
                if (BigDecimalUtils.MoreThan(Double.valueOf(checkBandwidth), this.BIG_ZERO)) {
                    BigDecimal sub_2 = BigDecimalUtils.sub_(balanceBigDecimal, amount);
                    if (isEditingNote()) {
                        BigDecimal sub_3 = BigDecimalUtils.sub_(this.tokenBean.getBalanceBigDecimal(), Double.valueOf(checkBandwidth));
                        if (BigDecimalUtils.Equal(amount, this.BIG_ZERO)) {
                            if (BigDecimalUtils.MoreThan((Object) sub_3, (Object) 0)) {
                                this.llErrAmount.setError(ErrorBottomLayout.ErrorType.NORMAL, getString(R.string.trx_fee_auto_deduct));
                            } else if (BigDecimalUtils.Equal((Object) sub_3, (Object) 0)) {
                                this.llErrAmount.setError(ErrorBottomLayout.ErrorType.ERROR, getString(R.string.trx_input_more_than_zero_and_fee));
                            } else {
                                this.llErrAmount.setError(ErrorBottomLayout.ErrorType.ERROR, getString(R.string.trx_no_balance_and_fee));
                            }
                        } else if (BigDecimalUtils.LessThan(sub_2, Double.valueOf(checkBandwidth))) {
                            if (BigDecimalUtils.MoreThan(sub_3, this.BIG_ZERO)) {
                                this.llErrAmount.setError(ErrorBottomLayout.ErrorType.ERROR, getString(R.string.trx_max_transfer_amount_and_fee, new Object[]{StringTronUtil.formatNumber6Truncate(sub_3)}));
                            } else {
                                this.llErrAmount.setError(ErrorBottomLayout.ErrorType.ERROR, getString(R.string.trx_no_balance_and_fee));
                            }
                        } else {
                            this.llErrAmount.setError(ErrorBottomLayout.ErrorType.NORMAL, getString(R.string.trx_fee_auto_deduct));
                        }
                    } else if (BigDecimalUtils.Equal(this.tokenBean.getBalanceBigDecimal(), amount)) {
                        BigDecimal sub_4 = BigDecimalUtils.sub_(this.tokenBean.getBalanceBigDecimal(), Double.valueOf(checkBandwidth));
                        BigDecimal bigDecimal = BigDecimalUtils.MoreThan((Object) sub_4, (Object) 0) ? sub_4 : this.BIG_ZERO;
                        TextDotUtils.setText_Dot(this.etAmount, this.numberFormat.format(bigDecimal));
                        this.maxAmountValue = bigDecimal;
                        if (BigDecimalUtils.MoreThan((Object) bigDecimal, (Object) 0)) {
                            this.llErrAmount.setError(ErrorBottomLayout.ErrorType.NORMAL, getString(R.string.trx_fee_auto_deduct));
                        } else if (BigDecimalUtils.Equal((Object) sub_4, (Object) 0)) {
                            this.llErrAmount.setError(ErrorBottomLayout.ErrorType.ERROR, getString(R.string.trx_input_more_than_zero_and_fee));
                        } else {
                            this.llErrAmount.setError(ErrorBottomLayout.ErrorType.ERROR, getString(R.string.trx_no_balance_and_fee));
                        }
                    } else {
                        BigDecimal bigDecimal2 = this.maxAmountValue;
                        if (bigDecimal2 != null && BigDecimalUtils.Equal(bigDecimal2, amount)) {
                            BigDecimal sub_5 = BigDecimalUtils.sub_(this.tokenBean.getBalanceBigDecimal(), Double.valueOf(checkBandwidth));
                            if (BigDecimalUtils.Equal(this.maxAmountValue, this.BIG_ZERO)) {
                                if (!BigDecimalUtils.LessThan((Object) sub_5, (Object) 0)) {
                                    this.llErrAmount.setError(ErrorBottomLayout.ErrorType.ERROR, getString(R.string.trx_input_more_than_zero_and_fee));
                                } else {
                                    this.llErrAmount.setError(ErrorBottomLayout.ErrorType.ERROR, getString(R.string.trx_no_balance_and_fee));
                                }
                            } else if (!BigDecimalUtils.LessThan(sub_2, Double.valueOf(checkBandwidth))) {
                                this.llErrAmount.setError(ErrorBottomLayout.ErrorType.NORMAL, getString(R.string.trx_fee_auto_deduct));
                            } else {
                                this.llErrAmount.setError(ErrorBottomLayout.ErrorType.ERROR, getString(R.string.trx_no_balance_and_fee));
                            }
                        } else {
                            this.maxAmountValue = null;
                            if (BigDecimalUtils.LessThan(sub_2, Double.valueOf(checkBandwidth))) {
                                this.llErrAmount.setError(ErrorBottomLayout.ErrorType.ERROR, getString(R.string.trx_no_balance_and_fee));
                            } else {
                                this.llErrAmount.setError(ErrorBottomLayout.ErrorType.NORMAL, getString(R.string.trx_fee_auto_deduct));
                            }
                        }
                    }
                    showTrxFeeTabs(this.tips);
                } else {
                    this.llErrAmount.clearError();
                }
            }
        }
        this.clickedMaxAmount = false;
        checkBtSendState();
    }

    @Override
    public void setSendAccountResource(GrpcAPI.AccountResourceMessage accountResourceMessage) {
        if (this.mPresenter == 0) {
            return;
        }
        calculateTrxFee();
    }

    @Override
    public void setSendResult(boolean z, int i) {
        if (i != 0) {
            ToastSuc(i);
        }
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$setSendResult$3();
            }
        });
    }

    public void lambda$setSendResult$3() {
        setSendingState(false);
    }

    @Override
    public void updateDefaultNftItemInfo(NftItemInfo nftItemInfo) {
        if (this.activeAmountInfo instanceof CollectionsItemInfo) {
            this.collectionsItemInfo.updateNftItemUi(nftItemInfo);
        }
    }

    public class TokenAmountInfo implements AmountInfo {
        private BigDecimal amountBigDecimal;

        @Override
        public BigDecimal getAmount() {
            return this.amountBigDecimal;
        }

        private TokenAmountInfo() {
            this.amountBigDecimal = BIG_ZERO;
        }

        @Override
        public void initUI() {
            SpannableString spannableString = new SpannableString(getResources().getString(R.string.transfer_amount_hint));
            spannableString.setSpan(new StyleSpan(0), 0, spannableString.length(), 33);
            spannableString.setSpan(new AbsoluteSizeSpan(14, true), 0, spannableString.length(), 33);
            etAmount.setHint(spannableString);
            etAmount.setTypeface(CustomFontUtils.getTypeface(mContext, 0));
            etAmount.addTextChangedListener(new BaseTextWatcher() {
                @Override
                public void afterTextChanged(Editable editable) {
                    if (StringTronUtil.isEmpty(editable.toString())) {
                        etAmount.setTypeface(CustomFontUtils.getTypeface(mContext, 0));
                    } else {
                        etAmount.setTypeface(CustomFontUtils.getTypeface(mContext, 1));
                    }
                    if (StringTronUtil.isEmpty(editable.toString())) {
                        tvMax.setVisibility(View.VISIBLE);
                        ivDelete.setVisibility(View.GONE);
                    } else {
                        tvMax.setVisibility(View.GONE);
                        ivDelete.setVisibility(View.VISIBLE);
                    }
                    maxAmountValue = null;
                    try {
                        String text_EditText_Dot = TextDotUtils.getText_EditText_Dot(etAmount);
                        TokenAmountInfo.this.calculateAmountValue(text_EditText_Dot);
                        TokenAmountInfo.this.amountBigDecimal = new BigDecimal(text_EditText_Dot);
                        TokenAmountInfo tokenAmountInfo = TokenAmountInfo.this;
                        tokenAmountInfo.checkInputAmount(text_EditText_Dot, etAmount.hasFocus());
                    } catch (Throwable th) {
                        LogUtils.e(th);
                    }
                    checkBtSendState();
                }
            });
            TextDotUtils.setTextChangedListener_Dot(etAmount);
            etAmount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean z) {
                    if (!z) {
                        try {
                            TokenAmountInfo.this.checkInputAmount(TextDotUtils.getText_EditText_Dot(etAmount), false);
                            calculateTrxFee();
                            return;
                        } catch (Throwable th) {
                            LogUtils.e(th);
                            return;
                        }
                    }
                    isEditingNote = false;
                }
            });
        }

        @Override
        public void active(boolean z) {
            llErrAmount.setVisibility(z ? View.VISIBLE : View.GONE);
            if (z) {
                llErrAmount.clearError();
                String str = "";
                etAmount.setText("");
                etAmount.clearFocus();
                try {
                    setAmountNumberFormat();
                    int type = tokenBean.getType();
                    if (type == 0) {
                        tvSymbol.setText("TRX");
                        tvName.setText("");
                        ImageUtils.loadAsCircleResource(ivTokenIcon, R.mipmap.trx);
                        ivOfficial.setVisibility(View.VISIBLE);
                        TextDotUtils.setText_Dot(tvBalance, numberFormat.format(tokenBean.getBalanceBigDecimal()));
                    } else if (type == 1 || type == 2) {
                        tvSymbol.setText(tokenBean.getShortName());
                        TextView textView = tvName;
                        if (!StringTronUtil.isEmpty(tokenBean.getName())) {
                            str = "(" + tokenBean.getName() + ")";
                        }
                        textView.setText(str);
                        TransferSelectTokenActivity transferSelectTokenActivity = TransferSelectTokenActivity.this;
                        transferSelectTokenActivity.setTokenLogoView(transferSelectTokenActivity.tokenBean);
                        TextDotUtils.setText_Dot(tvBalance, numberFormat.format(tokenBean.getBalanceBigDecimal()));
                    }
                } catch (Exception e) {
                    SentryUtil.captureException(e);
                }
            }
        }

        @Override
        public boolean isValid() {
            return llErrAmount.getErrorType() != ErrorBottomLayout.ErrorType.ERROR && BigDecimalUtils.MoreThan(this.amountBigDecimal, BigDecimal.valueOf(0L));
        }

        @Override
        public void checkInputAmount() {
            try {
                checkInputAmount(TextDotUtils.getText_EditText_Dot(etAmount), false);
            } catch (Throwable th) {
                LogUtils.e(th);
            }
        }

        private void setAmountNumberFormat() {
            if (tokenBean.getPrecision() != 0) {
                numberFormat.setMaximumFractionDigits(tokenBean.getPrecision());
                etAmount.setInputType(8194);
                etAmount.setDOTValue(tokenBean.getPrecision());
                return;
            }
            numberFormat.setMaximumFractionDigits(6);
            if (tokenBean.getType() == 0) {
                etAmount.setInputType(8194);
                etAmount.setDOTValue(6);
                return;
            }
            etAmount.setInputType(2);
        }

        public void checkInputAmount(String str, boolean z) {
            llErrAmount.setSupportErrorTabs(null);
            if (StringTronUtil.isNullOrEmpty(str)) {
                llErrAmount.clearError();
                return;
            }
            BigDecimal valueOf = BigDecimal.valueOf(0L);
            try {
                BigDecimal bigDecimal = new BigDecimal(str);
                if (BigDecimalUtils.MoreThan(bigDecimal, tokenBean.getBalanceBigDecimal())) {
                    calculateTrxFeeForBiggerInput();
                } else if (!z && (BigDecimalUtils.LessThan(bigDecimal, valueOf) || BigDecimalUtils.Equal(bigDecimal, valueOf))) {
                    llErrAmount.setError(ErrorBottomLayout.ErrorType.ERROR, getString(R.string.tansfer2));
                } else {
                    llErrAmount.clearError();
                }
            } catch (Exception unused) {
                llErrAmount.setError(ErrorBottomLayout.ErrorType.ERROR, getString(R.string.input_error));
            }
        }

        public void calculateAmountValue(String str) {
            BigDecimal mul_;
            if (StringTronUtil.isNullOrEmpty(str)) {
                tvValue.setVisibility(View.GONE);
            } else if (str.equals("00")) {
                etAmount.setText("0");
            } else {
                BigDecimal bigDecimal = new BigDecimal(str);
                tvValue.setVisibility(View.VISIBLE);
                if (OfficialType.isScamOrUnSafeToken(tokenBean)) {
                    TextView textView = tvValue;
                    Object[] objArr = new Object[1];
                    objArr[0] = SpAPI.THIS.isUsdPrice() ? "$" : Html.fromHtml("&yen;");
                    textView.setText(String.format("%s0", objArr));
                } else if (tokenBean.getBalance() > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && tokenBean.getTrxCount() == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                    TextView textView2 = tvValue;
                    Object[] objArr2 = new Object[1];
                    objArr2[0] = SpAPI.THIS.isUsdPrice() ? "$" : Html.fromHtml("&yen;");
                    textView2.setText(String.format("%s -", objArr2));
                } else if (tokenBean.getType() == 0) {
                    if (!BigDecimalUtils.equalsZero((Number) Double.valueOf(bigDecimal.doubleValue() * PriceUpdater.getTRX_price().getPrice())) && BigDecimalUtils.MoreThan("0.001", Double.valueOf(bigDecimal.doubleValue() * PriceUpdater.getTRX_price().getPrice()))) {
                        tvValue.setText(StringTronUtil.formatPriceLessThan(Double.valueOf(0.001d)));
                    } else {
                        tvValue.setText(StringTronUtil.formatPrice3(Double.valueOf(bigDecimal.doubleValue() * PriceUpdater.getTRX_price().getPrice())));
                    }
                } else if (BigDecimalUtils.equalsZero((Number) Double.valueOf(tokenBean.getBalance()))) {
                    TextView textView3 = tvValue;
                    Object[] objArr3 = new Object[1];
                    objArr3[0] = SpAPI.THIS.isUsdPrice() ? "$" : Html.fromHtml("&yen;");
                    textView3.setText(String.format("%s -", objArr3));
                } else {
                    boolean isUsdPrice = SpAPI.THIS.isUsdPrice();
                    if (isUsdPrice && !StringTronUtil.isEmpty(tokenBean.getUsdPrice())) {
                        mul_ = BigDecimalUtils.mul_(bigDecimal, tokenBean.getUsdPrice());
                    } else if (!isUsdPrice && !StringTronUtil.isEmpty(tokenBean.getCnyPrice())) {
                        mul_ = BigDecimalUtils.mul_(bigDecimal, tokenBean.getCnyPrice());
                    } else {
                        mul_ = BigDecimalUtils.mul_(BigDecimalUtils.div_(BigDecimalUtils.mul_(Double.valueOf(tokenBean.getTrxCount()), Double.valueOf(PriceUpdater.getTRX_price().getPrice())), tokenBean.getBalanceBigDecimal()), bigDecimal);
                    }
                    if (BigDecimalUtils.MoreThan("0.001", mul_) && !BigDecimalUtils.equalsZero((Number) mul_)) {
                        tvValue.setText(StringTronUtil.formatPriceLessThan(Double.valueOf(0.001d)));
                    } else {
                        tvValue.setText(StringTronUtil.formatPrice3(mul_));
                    }
                }
            }
        }
    }

    public class CollectionsItemInfo implements AmountInfo {
        private NftItemInfo nftItemInfo;

        public static void lambda$initUI$0() {
        }

        @Override
        public void checkInputAmount() {
        }

        public NftItemInfo getNftItemInfo() {
            return this.nftItemInfo;
        }

        public void setNftItemInfo(NftItemInfo nftItemInfo) {
            this.nftItemInfo = nftItemInfo;
        }

        private CollectionsItemInfo() {
        }

        @Override
        public void initUI() {
            llCollection.setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    TransferSelectTokenActivity.CollectionsItemInfo.this.lambda$initUI$2(view);
                }
            });
        }

        public void lambda$initUI$2(View view) {
            if (!isSending() && BigDecimalUtils.MoreThan((Object) tokenBean.getBalanceBigDecimal(), (Object) 0)) {
                TransferSelectTokenActivity transferSelectTokenActivity = TransferSelectTokenActivity.this;
                SelectNftItemBottomPopup.showPopup(transferSelectTokenActivity, transferSelectTokenActivity.tokenBean, this.nftItemInfo, transferParam.getFromAddress(), isMultiSign).setOnDismissListener(new SelectNftItemBottomPopup.OnDismissListener() {
                    @Override
                    public final void onDismiss() {
                        TransferSelectTokenActivity.CollectionsItemInfo.lambda$initUI$0();
                    }
                }).setOnItemClickListener(new SelectNftItemBottomPopup.OnClickListener() {
                    @Override
                    public final void onClick(NftItemInfo nftItemInfo) {
                        TransferSelectTokenActivity.CollectionsItemInfo.this.lambda$initUI$1(nftItemInfo);
                    }
                });
            }
        }

        public void lambda$initUI$1(NftItemInfo nftItemInfo) {
            this.nftItemInfo = nftItemInfo;
            updateNftItemUi();
        }

        @Override
        public void active(boolean z) {
            String str;
            llErrCollection.setVisibility(z ? View.VISIBLE : View.GONE);
            if (z) {
                llErrCollection.clearError();
                tvSymbol.setText(tokenBean.getShortName());
                TextView textView = tvName;
                if (StringTronUtil.isEmpty(tokenBean.getName())) {
                    str = "";
                } else {
                    str = "(" + tokenBean.getName() + ")";
                }
                textView.setText(str);
                TransferSelectTokenActivity transferSelectTokenActivity = TransferSelectTokenActivity.this;
                transferSelectTokenActivity.setTokenLogoView(transferSelectTokenActivity.tokenBean);
                NftItemInfo nftItemInfo = this.nftItemInfo;
                if (nftItemInfo != null && nftItemInfo.getTokenAddress().equals(tokenBean.getContractAddress())) {
                    updateNftItemUi();
                    return;
                }
                this.nftItemInfo = null;
                ((TransferSelectTokenPresenter) mPresenter).getDefaultNftItem(tokenBean.getContractAddress());
                updateNftItemUi();
            }
        }

        public void updateNftItemUi() {
            llCollection.setVisibility(View.VISIBLE);
            llNoCollection.setVisibility(View.GONE);
            checkBtSendState();
            NftItemInfo nftItemInfo = this.nftItemInfo;
            if (nftItemInfo == null) {
                tvNftName.setText(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
                tvNftId.setText("(--)");
                return;
            }
            if (StringTronUtil.isEmpty(nftItemInfo.getName())) {
                TextView textView = tvNftName;
                textView.setText("# " + this.nftItemInfo.getAssetId());
                tvNftId.setText("");
            } else {
                tvNftName.setText(this.nftItemInfo.getName());
                TextView textView2 = tvNftId;
                textView2.setText("( # " + this.nftItemInfo.getAssetId() + " )");
            }
            ivNftItem.setImageURI(this.nftItemInfo.getImageUrl());
        }

        public void updateNftItemUi(NftItemInfo nftItemInfo) {
            if (nftItemInfo != null) {
                setNftItemInfo(nftItemInfo);
                updateNftItemUi();
                return;
            }
            this.nftItemInfo = null;
            llCollection.setVisibility(View.GONE);
            llNoCollection.setVisibility(View.VISIBLE);
        }

        @Override
        public BigDecimal getAmount() {
            return new BigDecimal(1);
        }

        @Override
        public boolean isValid() {
            NftItemInfo nftItemInfo = this.nftItemInfo;
            return nftItemInfo != null && nftItemInfo.getTokenAddress().equals(tokenBean.getContractAddress());
        }
    }
}
