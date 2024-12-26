package com.tron.wallet.business.transfer;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.lxj.xpopup.core.BasePopupView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.business.confirm.fg.component.TransactionFeeResourceView;
import com.tron.wallet.business.confirm.fg.component.TransactionResourceView;
import com.tron.wallet.business.customtokens.bean.CustomTokenStatus;
import com.tron.wallet.business.message.TransactionMessage;
import com.tron.wallet.business.message.TransactionMessageStatusBean;
import com.tron.wallet.business.pull.PullConstants;
import com.tron.wallet.business.stakev2.bean.DelegatedResourceOutput;
import com.tron.wallet.business.transfer.TransactionDetailActivity;
import com.tron.wallet.business.transfer.TransactionDetailContract;
import com.tron.wallet.business.transfer.share.ShareHelper;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.common.bean.RiskAccountOutput;
import com.tron.wallet.common.bean.token.OfficialType;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.bean.token.TransactionHistoryBean;
import com.tron.wallet.common.bean.token.TransactionInfoBean;
import com.tron.wallet.common.components.AddToAddressBookPopView;
import com.tron.wallet.common.components.AddressBottomView;
import com.tron.wallet.common.components.CenterSpaceImageSpan;
import com.tron.wallet.common.components.MultiLineTextPopupView;
import com.tron.wallet.common.components.ShareBottomView;
import com.tron.wallet.common.config.ContractReturnMessage;
import com.tron.wallet.common.config.ErrorConstant;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.interfaces.PermissionInterface;
import com.tron.wallet.common.utils.Action;
import com.tron.wallet.common.utils.AddressMapUtils;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.DateUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.NumUtils;
import com.tron.wallet.common.utils.PermissionHelper;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TokenItemUtil;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.common.utils.addressscam.AddressManager;
import com.tron.wallet.databinding.ActivityTransactionDetailBinding;
import com.tron.wallet.db.Controller.AddressController;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.functions.Consumer;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import org.apache.commons.cli.HelpFormatter;
import org.bouncycastle.util.encoders.Hex;
import org.slf4j.Marker;
public class TransactionDetailActivity extends BaseActivity<TransactionDetailPresenter, TransactionDetailModel> implements TransactionDetailContract.View, PermissionInterface {
    private static final String SCAM_URL = "https://slowmist.medium.com/slowmist-another-airdrop-scam-but-with-a-twist-1666e01b6a6c";
    private static final String SCAM_URL_0 = "https://slowmist.medium.com/slowmist-be-wary-of-the-transferfrom-zero-transfer-scam-c64ba0e3bc4d";
    private static final String TAG = "TransactionDetailActivity";
    private String address;
    private String amount;
    View amountLine;
    private PopupWindow apprpovePopupWindow;
    ActivityTransactionDetailBinding binding;
    private Object block;
    private View copyView;
    public String hash;
    ImageView ivCode;
    ImageView ivContractType;
    View ivRaCopy;
    ImageView ivRaSc;
    ImageView ivRightIcon;
    View ivSaCopy;
    ImageView ivSaSc;
    ImageView ivScam;
    View ivScamSendTag;
    View ivScamTag;
    ImageView ivSignCopy1;
    ImageView ivSignCopy2;
    ImageView ivSignCopy3;
    ImageView ivSignCopy4;
    ImageView ivSignCopy5;
    LinearLayout liTvAmount;
    View llCode;
    View llCodeCopy;
    LinearLayout llContent;
    View llHash;
    LinearLayout llNote;
    View llRa;
    View llSa;
    View llScroll;
    private boolean mIsFromMessageCenter;
    private TransactionMessage mMessage;
    private PermissionHelper mPermissionHelper;
    private int mPosition;
    private long mPrecision;
    private NumberFormat numberFormat;
    private PopupWindow popupWindow;
    View raTitleLayout;
    private RxManager rxManager;
    View saTitleLayout;
    LinearLayout scamlayout;
    private String shareFilePath;
    LinearLayout signAccountLayout;
    View signAddressLayout1;
    View signAddressLayout2;
    View signAddressLayout3;
    View signAddressLayout4;
    View signAddressLayout5;
    TextView signTextView1;
    TextView signTextView1Name;
    TextView signTextView2;
    TextView signTextView2Name;
    TextView signTextView3;
    TextView signTextView3Name;
    TextView signTextView4;
    TextView signTextView4Name;
    TextView signTextView5;
    TextView signTextView5Name;
    RelativeLayout stateLayout;
    private BasePopupView tipsWindow;
    private TokenBean token;
    TransactionFeeResourceView transactionFeeView;
    private TransactionInfoBean transactionInfo;
    LinearLayout transactionLayout;
    RelativeLayout transactionMenuLayout;
    TransactionResourceView transactionResourceView;
    private TransactionHistoryBean transferOutput;
    TextView tvAmount;
    TextView tvApprovedAmount;
    TextView tvApprovedAmountTitle;
    TextView tvBn;
    TextView tvBnTitle;
    TextView tvContractType;
    TextView tvContractTypeTop;
    TextView tvNote;
    TextView tvRa;
    TextView tvRaTitle;
    TextView tvRaWalletName;
    TextView tvReasonFailure;
    TextView tvReasonFailureTitle;
    TextView tvResource;
    TextView tvResourceTitle;
    TextView tvSa;
    TextView tvSaTitle;
    TextView tvSaWalletName;
    TextView tvScamContent;
    TextView tvState;
    TextView tvTime;
    TextView tvTimeTitle;
    TextView tvTn;
    TextView tvVdd;
    private String url;
    private long fee = 0;
    private boolean hasSa = true;
    private boolean hasRa = true;
    String realEventType = "";

    interface TriggerContractType {
        public static final int APPROVAL = 502;
        public static final int TRC20_TRANSFER = 500;
        public static final int TRC721_TRANSFER = 501;
    }

    @Override
    public int getPermissionsRequestCode() {
        return ErrorConstant.triggerError;
    }

    public static void start(Context context, TransactionHistoryBean transactionHistoryBean, TokenBean tokenBean, String str) {
        Intent intent = new Intent(context, TransactionDetailActivity.class);
        intent.putExtra("toBean", transactionHistoryBean);
        intent.putExtra("tbBean", tokenBean);
        intent.putExtra("amount", str);
        context.startActivity(intent);
    }

    public static void start(Context context, TransactionHistoryBean transactionHistoryBean, TokenBean tokenBean, String str, boolean z, TransactionMessage transactionMessage, int i) {
        Intent intent = new Intent(context, TransactionDetailActivity.class);
        intent.putExtra("toBean", transactionHistoryBean);
        intent.putExtra("tbBean", tokenBean);
        intent.putExtra("amount", str);
        intent.putExtra("isFromMessageCenter", z);
        intent.putExtra(PullConstants.RESULT_MESSAGE, transactionMessage);
        intent.putExtra("position", i);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        ActivityTransactionDetailBinding inflate = ActivityTransactionDetailBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        setClick();
        setView(root, 1);
        setHeaderBar(getString(R.string.td));
        getHeaderHolder().ivCommonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$setLayout$0(view);
            }
        });
        getHeaderHolder().tvCommonTitle.setTextSize(20.0f);
        getHeaderHolder().rlRight.setVisibility(View.GONE);
        getHeaderHolder().rlRightshare.setVisibility(View.VISIBLE);
        getHeaderHolder().tvCommonRight2.setVisibility(View.GONE);
        getHeaderHolder().ivShare.setBackground(getResources().getDrawable(R.drawable.selector_bg_circle_gray));
        getHeaderHolder().ivShare.setImageResource(R.mipmap.iv_share);
        getHeaderHolder().ivShare.setOnClickListener(new fun1());
    }

    public void lambda$setLayout$0(View view) {
        onLeftButtonClick();
    }

    public class fun1 extends NoDoubleClickListener {
        fun1() {
        }

        @Override
        protected void onNoDoubleClick(View view) {
            if (transferOutput == null || token == null || transactionInfo == null) {
                return;
            }
            TransactionDetailActivity transactionDetailActivity = TransactionDetailActivity.this;
            if (!transactionDetailActivity.supportShareUrl(transactionDetailActivity.transferOutput)) {
                prepareShareImage();
                return;
            }
            if (transactionInfo != null) {
                AnalyticsHelper.logEventFormat(AnalyticsHelper.TransactionDetailsPage.CLICK_TRANSACTION_DETAIL_PAGE_SHARE, Integer.valueOf(transactionInfo.getContractType()));
            }
            ShareBottomView.showPop(view.getContext(), new Action() {
                @Override
                public final void apply(View view2, int i) {
                    TransactionDetailActivity.1.this.lambda$onNoDoubleClick$0(view2, i);
                }
            }, new Action() {
                @Override
                public final void apply(View view2, int i) {
                    TransactionDetailActivity.1.this.lambda$onNoDoubleClick$1(view2, i);
                }
            });
        }

        public void lambda$onNoDoubleClick$0(View view, int i) {
            if (transactionInfo != null) {
                AnalyticsHelper.logEventFormat(AnalyticsHelper.TransactionDetailsPage.CLICK_TRANSACTION_DETAIL_PAGE_SHARE_LINK, Integer.valueOf(transactionInfo.getContractType()));
            }
            ShareHelper shareHelper = ShareHelper.getInstance();
            TransactionDetailActivity transactionDetailActivity = TransactionDetailActivity.this;
            shareHelper.shareUrl(transactionDetailActivity, transactionDetailActivity.getShareUrl());
        }

        public void lambda$onNoDoubleClick$1(View view, int i) {
            if (transactionInfo != null) {
                AnalyticsHelper.logEventFormat(AnalyticsHelper.TransactionDetailsPage.CLICK_TRANSACTION_DETAIL_PAGE_SHARE_IMAGE, Integer.valueOf(transactionInfo.getContractType()));
            }
            prepareShareImage();
        }
    }

    private void setClick() {
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                switch (view.getId()) {
                    case R.id.iv_contract_type:
                        TransactionDetailActivity transactionDetailActivity = TransactionDetailActivity.this;
                        transactionDetailActivity.showPopZero(R.string.approve_text, transactionDetailActivity.ivContractType, true);
                        return;
                    case R.id.iv_ra_copy:
                        if (hasRa) {
                            UIUtils.copy((String) tvRa.getTag());
                            TransactionDetailActivity transactionDetailActivity2 = TransactionDetailActivity.this;
                            transactionDetailActivity2.toast(transactionDetailActivity2.getString(R.string.copy_susscess));
                            return;
                        }
                        return;
                    case R.id.iv_sa_copy:
                        if (hasSa) {
                            UIUtils.copy((String) tvSa.getTag());
                            TransactionDetailActivity transactionDetailActivity3 = TransactionDetailActivity.this;
                            transactionDetailActivity3.toast(transactionDetailActivity3.getString(R.string.copy_susscess));
                            return;
                        }
                        return;
                    case R.id.ll_code_copy:
                        UIUtils.copy(getUrl());
                        TransactionDetailActivity transactionDetailActivity4 = TransactionDetailActivity.this;
                        transactionDetailActivity4.toast(getUrl() + " " + getString(R.string.copy_susscess));
                        if (transactionInfo != null) {
                            AnalyticsHelper.logEventFormat(AnalyticsHelper.TransactionDetailsPage.CLICK_TRANSACTION_DETAIL_PAGE_COPY_LINK, Integer.valueOf(transactionInfo.getContractType()));
                            return;
                        }
                        return;
                    case R.id.ll_hash:
                        UIUtils.copy(tvTn.getText().toString());
                        TransactionDetailActivity transactionDetailActivity5 = TransactionDetailActivity.this;
                        transactionDetailActivity5.toast(transactionDetailActivity5.getString(R.string.copy_susscess));
                        return;
                    case R.id.tv_bn:
                        UIUtils.copy(tvBn.getText().toString());
                        TransactionDetailActivity transactionDetailActivity6 = TransactionDetailActivity.this;
                        transactionDetailActivity6.toast(transactionDetailActivity6.getString(R.string.copy_susscess));
                        return;
                    case R.id.tv_ra:
                        clickTvRa();
                        return;
                    case R.id.tv_sa:
                        clickTvSa();
                        return;
                    default:
                        return;
                }
            }
        };
        this.binding.tvBn.setOnClickListener(noDoubleClickListener2);
        this.binding.ivContractType.setOnClickListener(noDoubleClickListener2);
        this.binding.tvSa.setOnClickListener(noDoubleClickListener2);
        this.binding.tvRa.setOnClickListener(noDoubleClickListener2);
        this.binding.ivSaCopy.setOnClickListener(noDoubleClickListener2);
        this.binding.ivRaCopy.setOnClickListener(noDoubleClickListener2);
        this.binding.llHash.setOnClickListener(noDoubleClickListener2);
        this.binding.llCodeCopy.setOnClickListener(noDoubleClickListener2);
    }

    private void mappingId() {
        this.liTvAmount = this.binding.liTvAmount;
        this.tvAmount = this.binding.tvAmount;
        this.ivScam = this.binding.ivScam;
        this.amountLine = this.binding.tvAmountLine;
        this.tvSaTitle = this.binding.saTitle;
        this.tvSaWalletName = this.binding.tvWalletNameSa;
        this.tvSa = this.binding.tvSa;
        this.ivSaSc = this.binding.ivSaSc;
        this.ivRaSc = this.binding.ivRaSc;
        this.saTitleLayout = this.binding.llSaTitle;
        this.raTitleLayout = this.binding.llRaTitle;
        this.tvRaTitle = this.binding.raTitle;
        this.ivScamTag = this.binding.scamTag;
        this.ivScamSendTag = this.binding.scamTagSa;
        this.tvRaWalletName = this.binding.tvWalletNameRa;
        this.tvRa = this.binding.tvRa;
        this.tvTn = this.binding.tvTn;
        this.tvBn = this.binding.tvBn;
        this.tvBnTitle = this.binding.tvBnTitle;
        this.tvTime = this.binding.tvTime;
        this.ivCode = this.binding.ivCode;
        this.ivRightIcon = this.binding.ivRightIcon;
        this.tvVdd = this.binding.tvVdd;
        this.tvTimeTitle = this.binding.tvTimeTitle;
        this.ivContractType = this.binding.ivContractType;
        this.tvResourceTitle = this.binding.tvResourceTitle;
        this.tvResource = this.binding.tvResource;
        this.tvNote = this.binding.tvNote;
        this.llNote = this.binding.llNote;
        this.stateLayout = this.binding.rlState;
        this.tvState = this.binding.tvState;
        this.tvContractTypeTop = this.binding.tvContractTypeTop;
        this.tvContractType = this.binding.tvContractType;
        this.transactionResourceView = this.binding.layoutTransactionDetailResource;
        this.tvReasonFailureTitle = this.binding.tvReasonFailureTitle;
        this.tvReasonFailure = this.binding.tvReasonFailure;
        this.tvApprovedAmountTitle = this.binding.tvApprovedAmountTitle;
        this.tvApprovedAmount = this.binding.tvApprovedAmount;
        this.llSa = this.binding.llSa;
        this.llRa = this.binding.llRa;
        this.llHash = this.binding.llHash;
        this.ivSaCopy = this.binding.ivSaCopy;
        this.ivRaCopy = this.binding.ivRaCopy;
        this.llScroll = this.binding.llScroll;
        this.llContent = this.binding.llContent;
        this.llCode = this.binding.llCode;
        this.llCodeCopy = this.binding.llCodeCopy;
        this.transactionFeeView = this.binding.resourceInfoView2;
        this.transactionLayout = this.binding.transactionInfoBottom;
        this.transactionMenuLayout = this.binding.rlTransferMenu;
        this.signAccountLayout = this.binding.signAccountLayout;
        this.signTextView1Name = this.binding.tvSignAddress1Name;
        this.signTextView1 = this.binding.tvSignAddress1;
        this.signTextView2Name = this.binding.tvSignAddress2Name;
        this.signTextView2 = this.binding.tvSignAddress2;
        this.signTextView3Name = this.binding.tvSignAddress3Name;
        this.signTextView3 = this.binding.tvSignAddress3;
        this.signTextView4Name = this.binding.tvSignAddress4Name;
        this.signTextView4 = this.binding.tvSignAddress4;
        this.signTextView5Name = this.binding.tvSignAddress5Name;
        this.signTextView5 = this.binding.tvSignAddress5;
        this.ivSignCopy1 = this.binding.ivCopy1;
        this.ivSignCopy2 = this.binding.ivCopy2;
        this.ivSignCopy3 = this.binding.ivCopy3;
        this.ivSignCopy4 = this.binding.ivCopy4;
        this.ivSignCopy5 = this.binding.ivCopy5;
        this.signAddressLayout1 = this.binding.signAddressLayout1;
        this.signAddressLayout2 = this.binding.signAddressLayout2;
        this.signAddressLayout3 = this.binding.signAddressLayout3;
        this.signAddressLayout4 = this.binding.signAddressLayout4;
        this.signAddressLayout5 = this.binding.signAddressLayout5;
        this.scamlayout = this.binding.topScamLayout;
        this.tvScamContent = this.binding.tipsContent;
    }

    public java.lang.String getShareUrl() {
        


return null;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.transfer.TransactionDetailActivity.getShareUrl():java.lang.String");
    }

    @Override
    public void onRefreshButtonClick() {
        super.onRefreshButtonClick();
    }

    @Override
    protected void processData() {
        this.rxManager = new RxManager();
        this.transferOutput = (TransactionHistoryBean) getIntent().getParcelableExtra("toBean");
        this.token = (TokenBean) getIntent().getParcelableExtra("tbBean");
        this.amount = getIntent().getStringExtra("amount");
        this.mIsFromMessageCenter = getIntent().getBooleanExtra("isFromMessageCenter", false);
        this.mMessage = (TransactionMessage) getIntent().getParcelableExtra(PullConstants.RESULT_MESSAGE);
        this.mPosition = getIntent().getIntExtra("position", -1);
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.numberFormat = numberInstance;
        numberInstance.setMaximumFractionDigits(7);
        this.address = WalletUtils.getSelectedWallet().getAddress();
        showLoadingDialog();
        try {
            if (WalletUtils.getSelectedWallet().isShieldedWallet()) {
                if (this.transferOutput.transactionHash != null) {
                    if (this.transferOutput.transactionHash.length() > 50) {
                        this.hash = this.transferOutput.transactionHash;
                    } else {
                        this.hash = Hex.toHexString(Base64.decode(this.transferOutput.transactionHash, 0));
                    }
                } else {
                    this.hash = Hex.toHexString(Base64.decode(this.transferOutput.getHash(), 0));
                }
            } else {
                this.hash = TextUtils.isEmpty(this.transferOutput.getHash()) ? this.transferOutput.transactionHash : this.transferOutput.getHash();
            }
        } catch (Exception e) {
            SentryUtil.captureException(e);
        }
        ((TransactionDetailPresenter) this.mPresenter).getFee(this.hash);
        TransactionHistoryBean transactionHistoryBean = this.transferOutput;
        if (transactionHistoryBean != null && "TransferContract".equals(transactionHistoryBean.getContract_type()) && this.transferOutput.isCheatStatus()) {
            updateScam(2);
        }
        this.transactionMenuLayout.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                CommonWebActivityV3.start(mContext, url, mContext.getString(R.string.transfer_doc), 1, true);
                if (transactionInfo != null) {
                    AnalyticsHelper.logEventFormat(AnalyticsHelper.TransactionDetailsPage.CLICK_TRANSACTION_DETAIL_PAGE_VIEW_DETAILS, Integer.valueOf(transactionInfo.getContractType()));
                }
            }
        });
        this.tvSa.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                clickTvSa();
                return false;
            }
        });
        this.tvRa.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                clickTvRa();
                return false;
            }
        });
    }

    @Override
    public void updateDefaultView(TransactionInfoBean transactionInfoBean) {
        if (transactionInfoBean == null) {
            transactionInfoBean = new TransactionInfoBean();
        }
        toUi();
        transactionInfoBean.setRevert(false);
        transactionInfoBean.setContractRet(this.transferOutput.getContract_ret());
        setFeeView(transactionInfoBean);
        this.tvResource.setVisibility(View.GONE);
        this.tvResourceTitle.setVisibility(View.GONE);
    }

    @Override
    public void updateScam(int i) {
        TransactionHistoryBean transactionHistoryBean;
        CenterSpaceImageSpan centerSpaceImageSpan = new CenterSpaceImageSpan(this.mContext, R.mipmap.ic_asset_scam, 2, 0, UIUtils.dip2px(4.0f));
        if (!this.mIsFromMessageCenter && (i = this.transferOutput.getStatus()) == 0 && (transactionHistoryBean = this.transferOutput) != null && "TransferContract".equals(transactionHistoryBean.getContract_type()) && this.transferOutput.isCheatStatus()) {
            i = 2;
        }
        if (i == 1) {
            this.scamlayout.setVisibility(View.VISIBLE);
            String string = getString(R.string.transaction_detail_scam_get_more);
            String str = "00" + getResources().getString(R.string.transaction_detail_scam_tips_0) + string + " >";
            int indexOf = str.indexOf(string);
            SpannableString spannableString = new SpannableString(str);
            if (indexOf > 0) {
                spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.blue_3c)), indexOf, spannableString.length(), 33);
                spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.red_EC)), 0, indexOf, 33);
            } else {
                spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.red_EC)), 2, spannableString.length(), 33);
            }
            spannableString.setSpan(centerSpaceImageSpan, 0, 2, 17);
            this.tvScamContent.setText(spannableString);
            this.tvScamContent.setOnClickListener(new NoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    CommonWebActivityV3.start(mContext, TransactionDetailActivity.SCAM_URL_0, mContext.getString(R.string.watch_out_for_scams_doc), 1, false);
                }
            });
        } else if (i != 2) {
        } else {
            this.scamlayout.setVisibility(View.VISIBLE);
            String string2 = getString(R.string.transaction_detail_scam_get_more);
            String str2 = "00" + getResources().getString(R.string.transaction_detail_scam_tips) + string2 + " >";
            int indexOf2 = str2.indexOf(string2);
            SpannableString spannableString2 = new SpannableString(str2);
            if (indexOf2 > 0) {
                spannableString2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.blue_3c)), indexOf2, spannableString2.length(), 33);
                spannableString2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.red_EC)), 0, indexOf2, 33);
            } else {
                spannableString2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.red_EC)), 2, spannableString2.length(), 33);
            }
            spannableString2.setSpan(centerSpaceImageSpan, 0, 2, 17);
            this.tvScamContent.setText(spannableString2);
            this.tvScamContent.setOnClickListener(new NoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    CommonWebActivityV3.start(mContext, TransactionDetailActivity.SCAM_URL, mContext.getString(R.string.watch_out_for_scams_doc), 1, false);
                }
            });
        }
    }

    private void toUi() {
        TransactionInfoBean transactionInfoBean;
        TransactionInfoBean transactionInfoBean2;
        TransactionInfoBean transactionInfoBean3;
        TransactionInfoBean transactionInfoBean4;
        int type = this.token.getType();
        if (type == 1 || type == 2) {
            this.mPrecision = this.transferOutput.decimals;
        } else {
            this.mPrecision = 0L;
        }
        String contract_ret = this.transferOutput.getContract_ret();
        String event_type = this.transferOutput.getEvent_type();
        int revert = this.transferOutput.getRevert();
        if (this.transferOutput.getRevert() == 1 || (!TransactionMessage.TYPE_SUCCESS.equals(contract_ret) && !StringTronUtil.isEmpty(contract_ret))) {
            this.tvState.setText(getResources().getString(R.string.transaction_fail));
            this.tvState.setTextColor(getResources().getColor(R.color.red_ec));
            this.tvState.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.ic_transaction_fail), (Drawable) null, (Drawable) null, (Drawable) null);
        } else {
            this.tvState.setText(getResources().getString(R.string.transaction_success));
            this.tvState.setTextColor(getResources().getColor(R.color.green_57));
            this.tvState.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.ic_transaction_success), (Drawable) null, (Drawable) null, (Drawable) null);
        }
        int providerForStakeV2ByContractType = TransactionTypeTextProvider.providerForStakeV2ByContractType(this.transferOutput.getContract_type(), event_type);
        if (providerForStakeV2ByContractType != 0) {
            String string = getString(R.string.stake_for_tron_energy);
            String string2 = getString(R.string.stake_for_tron_bandwidth);
            if ("FreezeBalanceContract".equals(this.transferOutput.getContract_type()) || ((transactionInfoBean4 = this.transactionInfo) != null && transactionInfoBean4.getContractType() == 11)) {
                TransactionInfoBean transactionInfoBean5 = this.transactionInfo;
                if (transactionInfoBean5 != null) {
                    if (DelegatedResourceOutput.DelegatedResource.energy.equals(transactionInfoBean5.getContractData().getResource())) {
                        TextView textView = this.tvContractTypeTop;
                        StringBuilder sb = new StringBuilder();
                        sb.append(string);
                        sb.append(this.transferOutput.isMul() ? getString(R.string.mul_tag) : "");
                        textView.setText(sb.toString());
                        TextView textView2 = this.tvContractType;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(string);
                        sb2.append(this.transferOutput.isMul() ? getString(R.string.mul_tag) : "");
                        textView2.setText(sb2.toString());
                    } else {
                        TextView textView3 = this.tvContractTypeTop;
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(string2);
                        sb3.append(this.transferOutput.isMul() ? getString(R.string.mul_tag) : "");
                        textView3.setText(sb3.toString());
                        TextView textView4 = this.tvContractType;
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append(string2);
                        sb4.append(this.transferOutput.isMul() ? getString(R.string.mul_tag) : "");
                        textView4.setText(sb4.toString());
                    }
                } else {
                    this.tvContractTypeTop.setText(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
                    this.tvContractType.setText(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
                }
            } else if ("WithdrawBalanceContract".equals(this.transferOutput.getContract_type())) {
                TextView textView5 = this.tvContractTypeTop;
                StringBuilder sb5 = new StringBuilder();
                sb5.append(getString(R.string.claim_vote_rewards));
                sb5.append(this.transferOutput.isMul() ? getString(R.string.mul_tag) : "");
                textView5.setText(sb5.toString());
                TextView textView6 = this.tvContractType;
                StringBuilder sb6 = new StringBuilder();
                sb6.append(getString(R.string.claim_vote_rewards));
                sb6.append(this.transferOutput.isMul() ? getString(R.string.mul_tag) : "");
                textView6.setText(sb6.toString());
            } else if ("UnfreezeBalanceContract".equals(this.transferOutput.getContract_type())) {
                TextView textView7 = this.tvContractTypeTop;
                StringBuilder sb7 = new StringBuilder();
                sb7.append(getString(R.string.unstake));
                sb7.append(this.transferOutput.isMul() ? getString(R.string.mul_tag) : "");
                textView7.setText(sb7.toString());
                TextView textView8 = this.tvContractType;
                StringBuilder sb8 = new StringBuilder();
                sb8.append(getString(R.string.unstake));
                sb8.append(this.transferOutput.isMul() ? getString(R.string.mul_tag) : "");
                textView8.setText(sb8.toString());
            } else if ("FreezeBalanceV2Contract".equals(this.transferOutput.getContract_type())) {
                String string3 = getString(R.string.stake_v2_get_tron_pow_energy);
                String string4 = getString(R.string.stake_v2_get_tron_pow_bandwidth);
                TransactionInfoBean transactionInfoBean6 = this.transactionInfo;
                if (transactionInfoBean6 != null) {
                    if (DelegatedResourceOutput.DelegatedResource.energy.equals(transactionInfoBean6.getContractData().getResource())) {
                        TextView textView9 = this.tvContractTypeTop;
                        StringBuilder sb9 = new StringBuilder();
                        sb9.append(string3);
                        sb9.append(this.transferOutput.isMul() ? getString(R.string.mul_tag) : "");
                        textView9.setText(sb9.toString());
                        TextView textView10 = this.tvContractType;
                        StringBuilder sb10 = new StringBuilder();
                        sb10.append(string3);
                        sb10.append(this.transferOutput.isMul() ? getString(R.string.mul_tag) : "");
                        textView10.setText(sb10.toString());
                    } else {
                        TextView textView11 = this.tvContractTypeTop;
                        StringBuilder sb11 = new StringBuilder();
                        sb11.append(string4);
                        sb11.append(this.transferOutput.isMul() ? getString(R.string.mul_tag) : "");
                        textView11.setText(sb11.toString());
                        TextView textView12 = this.tvContractType;
                        StringBuilder sb12 = new StringBuilder();
                        sb12.append(string4);
                        sb12.append(this.transferOutput.isMul() ? getString(R.string.mul_tag) : "");
                        textView12.setText(sb12.toString());
                    }
                } else {
                    this.tvContractTypeTop.setText(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
                    this.tvContractType.setText(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
                }
            } else if ("FeeContract".equals(this.transferOutput.getContract_type())) {
                TextView textView13 = this.tvContractTypeTop;
                StringBuilder sb13 = new StringBuilder();
                sb13.append(getString(providerForStakeV2ByContractType));
                sb13.append(this.transferOutput.isMul() ? getString(R.string.mul_tag) : "");
                textView13.setText(sb13.toString());
                TransactionInfoBean transactionInfoBean7 = this.transactionInfo;
                if (transactionInfoBean7 != null) {
                    switch (transactionInfoBean7.getTriggerContractType()) {
                        case 500:
                            this.realEventType = "Transfer";
                            break;
                        case 501:
                            this.realEventType = "721Transfer";
                            break;
                        case TriggerContractType.APPROVAL:
                            if ("trc721".equals(this.transactionInfo.getContract_type())) {
                                this.realEventType = "721Approval";
                                break;
                            } else if ("trc20".equals(this.transactionInfo.getContract_type())) {
                                this.realEventType = CustomTokenStatus.APPROVE_EVENT;
                                break;
                            }
                            break;
                        default:
                            if ("trc721".equals(this.transactionInfo.getContract_type()) && this.transactionInfo.getTriggerContractType() == 0 && this.transactionInfo.getTrigger_info() != null && !StringTronUtil.isEmpty(this.transactionInfo.getTrigger_info().getMethod()) && this.transactionInfo.getTrigger_info().getMethod().contains("setApprovalForAll")) {
                                this.realEventType = "721Approval";
                                break;
                            }
                            break;
                    }
                    if (this.transactionInfo.getContractType() == 13) {
                        TextView textView14 = this.tvContractType;
                        StringBuilder sb14 = new StringBuilder();
                        sb14.append(getString(R.string.claim_vote_rewards));
                        sb14.append(this.transferOutput.isMul() ? getString(R.string.mul_tag) : "");
                        textView14.setText(sb14.toString());
                    } else if (this.transactionInfo.getContractType() == 14) {
                        TextView textView15 = this.tvContractType;
                        StringBuilder sb15 = new StringBuilder();
                        sb15.append(getString(R.string.TRC10_unfreeze));
                        sb15.append(this.transferOutput.isMul() ? getString(R.string.mul_tag) : "");
                        textView15.setText(sb15.toString());
                    } else if (this.transactionInfo.getContractType() == 31 || this.transactionInfo.getContractType() == 30) {
                        if (this.transactionInfo.getContractData().getAmount() > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                            TextView textView16 = this.tvContractTypeTop;
                            StringBuilder sb16 = new StringBuilder();
                            sb16.append(getString(providerForStakeV2ByContractType));
                            sb16.append(this.transferOutput.isMul() ? getString(R.string.mul_tag) : "");
                            textView16.setText(sb16.toString());
                        } else {
                            this.tvContractTypeTop.setText(TransactionTypeTextProvider.provider("FeeContract", ""));
                        }
                        int provider = TransactionTypeTextProvider.provider(this.transactionInfo.getContractType(), this.realEventType);
                        TextView textView17 = this.tvContractType;
                        StringBuilder sb17 = new StringBuilder();
                        sb17.append(getString(provider));
                        sb17.append(this.transferOutput.isMul() ? getString(R.string.mul_tag) : "");
                        textView17.setText(sb17.toString());
                    } else {
                        int provider2 = TransactionTypeTextProvider.provider(this.transactionInfo.getContractType(), this.realEventType);
                        TextView textView18 = this.tvContractType;
                        StringBuilder sb18 = new StringBuilder();
                        sb18.append(getString(provider2));
                        sb18.append(this.transferOutput.isMul() ? getString(R.string.mul_tag) : "");
                        textView18.setText(sb18.toString());
                    }
                }
            } else {
                TextView textView19 = this.tvContractTypeTop;
                StringBuilder sb19 = new StringBuilder();
                sb19.append(getString(providerForStakeV2ByContractType));
                sb19.append(this.transferOutput.isMul() ? getString(R.string.mul_tag) : "");
                textView19.setText(sb19.toString());
                TextView textView20 = this.tvContractType;
                StringBuilder sb20 = new StringBuilder();
                sb20.append(getString(providerForStakeV2ByContractType));
                sb20.append(this.transferOutput.isMul() ? getString(R.string.mul_tag) : "");
                textView20.setText(sb20.toString());
            }
        } else {
            this.tvContractType.setText(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
            this.tvContractTypeTop.setText(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
        }
        if (CustomTokenStatus.APPROVE_EVENT.equals(event_type)) {
            this.tvAmount.setTextColor(getResources().getColor(R.color.blue_3c));
        }
        if (revert == 1 || (!TransactionMessage.TYPE_SUCCESS.equals(contract_ret) && !StringTronUtil.isEmpty(contract_ret))) {
            this.tvReasonFailureTitle.setVisibility(View.VISIBLE);
            this.tvReasonFailure.setVisibility(View.VISIBLE);
            if (revert == 1) {
                this.tvReasonFailure.setText(ContractReturnMessage.getMessageResource(TransactionMessage.TYPE_REVERT));
            } else {
                this.tvReasonFailure.setText(ContractReturnMessage.getMessageResource(contract_ret));
            }
        }
        if ("TriggerSmartContract".equals(this.transferOutput.getContract_type())) {
            if (CustomTokenStatus.APPROVE_EVENT.equals(event_type)) {
                this.tvSaTitle.setText(R.string.transaction_owner_account);
                this.tvRaTitle.setText(R.string.smart_contract_approved);
                setAddress(this.tvSa, this.tvSaWalletName, this.ivSaSc, this.transferOutput.from);
                setAddress(this.tvRa, this.tvRaWalletName, this.ivRaSc, this.transferOutput.to);
            } else if ("Transfer".equals(event_type)) {
                this.tvSaTitle.setText(R.string.from_address);
                this.tvRaTitle.setText(R.string.to_address);
                setAddress(this.tvSa, this.tvSaWalletName, this.ivSaSc, this.transferOutput.from);
                setAddress(this.tvRa, this.tvRaWalletName, this.ivRaSc, this.transferOutput.to);
            } else {
                this.tvSaTitle.setText(R.string.transaction_owner_account);
                this.tvRaTitle.setText(R.string.smart_contract_triggered);
                setAddress(this.tvSa, this.tvSaWalletName, this.ivSaSc, this.transferOutput.from);
                setAddress(this.tvRa, this.tvRaWalletName, this.ivRaSc, this.transferOutput.to);
            }
        } else if ("WithdrawBalanceContract".equals(this.transferOutput.getContract_type()) || ((transactionInfoBean = this.transactionInfo) != null && transactionInfoBean.getContractType() == 13)) {
            this.tvSaTitle.setText(R.string.transaction_owner_account);
            if (StringTronUtil.isEmpty(this.transferOutput.from)) {
                setAddress(this.tvSa, this.tvSaWalletName, this.ivSaSc, this.transferOutput.to);
            } else {
                setAddress(this.tvSa, this.tvSaWalletName, this.ivSaSc, this.transferOutput.from);
            }
            if (StringTronUtil.isEmpty(this.transferOutput.to)) {
                setAddress(this.tvRa, this.tvRaWalletName, this.ivRaSc, this.transferOutput.from);
            } else {
                setAddress(this.tvRa, this.tvRaWalletName, this.ivRaSc, this.transferOutput.to);
            }
        } else if ("CreateSmartContract".equals(this.transferOutput.getContract_type()) && this.token.getType() == 0) {
            this.tvSaTitle.setText(R.string.transaction_owner_account);
            this.tvRaTitle.setText(R.string.create_smart_contract);
            setAddress(this.tvSa, this.tvSaWalletName, this.ivSaSc, this.transferOutput.from);
            setAddress(this.tvRa, this.tvRaWalletName, this.ivRaSc, this.transferOutput.to);
        } else if ("FreezeBalanceContract".equals(this.transferOutput.getContract_type()) || "FreezeBalanceV2Contract".equals(this.transferOutput.getContract_type()) || "CancelAllUnfreezeV2Contract".equals(this.transferOutput.getContract_type())) {
            this.tvSaTitle.setText(R.string.transaction_owner_account);
            this.tvRaTitle.setText(R.string.receive_recource_address);
            setAddress(this.tvSa, this.tvSaWalletName, this.ivSaSc, this.transferOutput.from);
            if (StringTronUtil.isEmpty(this.transferOutput.to)) {
                setAddress(this.tvRa, this.tvRaWalletName, this.ivRaSc, this.transferOutput.from);
            } else {
                setAddress(this.tvRa, this.tvRaWalletName, this.ivRaSc, this.transferOutput.to);
            }
        } else if ("UnfreezeBalanceContract".equals(this.transferOutput.getContract_type()) || "UnfreezeBalanceV2Contract".equals(this.transferOutput.getContract_type())) {
            this.tvSaTitle.setText(R.string.transaction_owner_account);
            this.tvRaTitle.setText(R.string.recycling_recource_address);
            setAddress(this.tvSa, this.tvSaWalletName, this.ivSaSc, this.transferOutput.from);
            if (StringTronUtil.isEmpty(this.transferOutput.to)) {
                if (!StringTronUtil.isEmpty(this.transferOutput.from)) {
                    setAddress(this.tvRa, this.tvRaWalletName, this.ivRaSc, this.transferOutput.from);
                }
            } else {
                setAddress(this.tvRa, this.tvRaWalletName, this.ivRaSc, this.transferOutput.to);
            }
        } else if ("WithdrawExpireUnfreezeContract".equals(this.transferOutput.getContract_type()) || "UnfreezeV2WithdrawContract".equals(this.transferOutput.getContract_type()) || "CancelAllUnfreezeV2WithdrawContract".equals(this.transferOutput.getContract_type())) {
            this.tvSaTitle.setText(R.string.transaction_owner_account);
            this.tvRaTitle.setText(R.string.to_address);
            setAddress(this.tvSa, this.tvSaWalletName, this.ivSaSc, WalletUtils.getSelectedWallet().getAddress());
            if (StringTronUtil.isEmpty(this.transferOutput.to)) {
                setAddress(this.tvRa, this.tvRaWalletName, this.ivRaSc, this.transferOutput.from);
            } else {
                setAddress(this.tvRa, this.tvRaWalletName, this.ivRaSc, this.transferOutput.to);
            }
        } else if ("AccountPermissionUpdateContract".equals(this.transferOutput.getContract_type()) || "WitnessCreateContract".equals(this.transferOutput.getContract_type()) || "AssetIssueContract".equals(this.transferOutput.getContract_type()) || "ExchangeCreateContract".equals(this.transferOutput.getContract_type()) || (((transactionInfoBean2 = this.transactionInfo) != null && transactionInfoBean2.getContractType() == 19) || ((transactionInfoBean3 = this.transactionInfo) != null && transactionInfoBean3.getContractType() == 4))) {
            this.tvSaTitle.setText(R.string.transaction_owner_account);
            this.llRa.setVisibility(View.GONE);
            this.raTitleLayout.setVisibility(View.GONE);
            setAddress(this.tvSa, this.tvSaWalletName, this.ivSaSc, this.transferOutput.from);
        } else if ("FeeContract".equals(this.transferOutput.getContract_type())) {
            TransactionInfoBean transactionInfoBean8 = this.transactionInfo;
            if ((transactionInfoBean8 != null && (transactionInfoBean8.getContractType() == 31 || this.transactionInfo.getContractType() == 33)) || this.transactionInfo.getContractType() == 48 || this.transactionInfo.getContractType() == 45) {
                this.tvSaTitle.setText(R.string.transaction_owner_account);
                this.tvRaTitle.setText(R.string.smart_contract_triggered);
                setAddress(this.tvSa, this.tvSaWalletName, this.ivSaSc, this.transferOutput.from);
                if (!StringTronUtil.isEmpty(this.transactionInfo.getContractData().getContract_address())) {
                    setAddress(this.tvRa, this.tvRaWalletName, this.ivRaSc, this.transactionInfo.getContractData().getContract_address());
                    this.llRa.setVisibility(View.VISIBLE);
                    this.raTitleLayout.setVisibility(View.VISIBLE);
                } else {
                    this.llRa.setVisibility(View.GONE);
                    this.raTitleLayout.setVisibility(View.GONE);
                }
            } else {
                TransactionInfoBean transactionInfoBean9 = this.transactionInfo;
                if (transactionInfoBean9 != null && transactionInfoBean9.getContractType() == 0) {
                    this.tvSaTitle.setText(R.string.transaction_owner_account);
                    this.tvRaTitle.setText(R.string.account_create);
                    setAddress(this.tvSa, this.tvSaWalletName, this.ivSaSc, this.transferOutput.from);
                    setAddress(this.tvRa, this.tvRaWalletName, this.ivRaSc, this.transferOutput.to);
                } else {
                    TransactionInfoBean transactionInfoBean10 = this.transactionInfo;
                    if (transactionInfoBean10 != null && transactionInfoBean10.getContractType() == 11) {
                        this.tvSaTitle.setText(R.string.transaction_owner_account);
                        this.tvRaTitle.setText(R.string.receive_recource_address);
                        setAddress(this.tvSa, this.tvSaWalletName, this.ivSaSc, WalletUtils.getSelectedWallet().getAddress());
                        if (StringTronUtil.isEmpty(this.transferOutput.to)) {
                            setAddress(this.tvRa, this.tvRaWalletName, this.ivRaSc, this.transferOutput.from);
                        } else {
                            setAddress(this.tvRa, this.tvRaWalletName, this.ivRaSc, this.transferOutput.to);
                        }
                    } else {
                        TransactionInfoBean transactionInfoBean11 = this.transactionInfo;
                        if (transactionInfoBean11 != null && transactionInfoBean11.getContractType() == 12) {
                            this.tvSaTitle.setText(R.string.transaction_owner_account);
                            this.tvRaTitle.setText(R.string.recycling_recource_address);
                            setAddress(this.tvSa, this.tvSaWalletName, this.ivSaSc, WalletUtils.getSelectedWallet().getAddress());
                            if (StringTronUtil.isEmpty(this.transferOutput.to)) {
                                setAddress(this.tvRa, this.tvRaWalletName, this.ivSaSc, this.transferOutput.from);
                            } else {
                                setAddress(this.tvRa, this.tvRaWalletName, this.ivRaSc, this.transferOutput.to);
                            }
                        } else {
                            TransactionInfoBean transactionInfoBean12 = this.transactionInfo;
                            if (transactionInfoBean12 != null && transactionInfoBean12.getContractType() == 30) {
                                this.tvSaTitle.setText(R.string.transaction_owner_account);
                                this.tvRaTitle.setText(R.string.create_smart_contract);
                                setAddress(this.tvSa, this.tvSaWalletName, this.ivSaSc, WalletUtils.getSelectedWallet().getAddress());
                                if (StringTronUtil.isEmpty(this.transferOutput.to)) {
                                    setAddress(this.tvRa, this.tvRaWalletName, this.ivRaSc, this.transferOutput.from);
                                } else {
                                    setAddress(this.tvRa, this.tvRaWalletName, this.ivRaSc, this.transferOutput.to);
                                }
                            } else {
                                TransactionInfoBean transactionInfoBean13 = this.transactionInfo;
                                if (transactionInfoBean13 != null && transactionInfoBean13.getContractType() == 57) {
                                    this.tvSaTitle.setText(R.string.transaction_owner_account);
                                    this.tvRaTitle.setText(R.string.delegate_res_account);
                                    setAddress(this.tvSa, this.tvSaWalletName, this.ivSaSc, WalletUtils.getSelectedWallet().getAddress());
                                    if (StringTronUtil.isEmpty(this.transferOutput.to)) {
                                        setAddress(this.tvRa, this.tvRaWalletName, this.ivSaSc, this.transferOutput.from);
                                    } else {
                                        setAddress(this.tvRa, this.tvRaWalletName, this.ivRaSc, this.transferOutput.to);
                                    }
                                } else {
                                    TransactionInfoBean transactionInfoBean14 = this.transactionInfo;
                                    if (transactionInfoBean14 != null && transactionInfoBean14.getContractType() == 58) {
                                        this.tvSaTitle.setText(R.string.transaction_owner_account);
                                        this.tvRaTitle.setText(R.string.recycling_recource_address);
                                        setAddress(this.tvSa, this.tvSaWalletName, this.ivSaSc, WalletUtils.getSelectedWallet().getAddress());
                                        if (StringTronUtil.isEmpty(this.transferOutput.to)) {
                                            setAddress(this.tvRa, this.tvRaWalletName, this.ivSaSc, this.transferOutput.from);
                                        } else {
                                            setAddress(this.tvRa, this.tvRaWalletName, this.ivRaSc, this.transferOutput.to);
                                        }
                                    } else {
                                        TransactionInfoBean transactionInfoBean15 = this.transactionInfo;
                                        if ((transactionInfoBean15 != null && transactionInfoBean15.getContractType() == 1) || this.transactionInfo.getContractType() == 2 || this.transactionInfo.getContractType() == 9 || this.transactionInfo.getContractType() == 30) {
                                            this.tvSaTitle.setText(R.string.from_address);
                                            this.tvRaTitle.setText(R.string.to_address);
                                        }
                                        if (!StringTronUtil.isEmpty(this.transferOutput.to)) {
                                            setAddress(this.tvRa, this.tvRaWalletName, this.ivRaSc, this.transferOutput.to);
                                            this.llRa.setVisibility(View.VISIBLE);
                                            this.raTitleLayout.setVisibility(View.VISIBLE);
                                        } else {
                                            this.llRa.setVisibility(View.GONE);
                                            this.raTitleLayout.setVisibility(View.GONE);
                                        }
                                        if (!StringTronUtil.isEmpty(this.transferOutput.from)) {
                                            setAddress(this.tvSa, this.tvSaWalletName, this.ivSaSc, this.transferOutput.from);
                                        } else {
                                            this.llSa.setVisibility(View.GONE);
                                            this.saTitleLayout.setVisibility(View.GONE);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            TransactionInfoBean transactionInfoBean16 = this.transactionInfo;
            if ((transactionInfoBean16 != null && transactionInfoBean16.getContractType() == 1) || this.transactionInfo.getContractType() == 2 || this.transactionInfo.getContractType() == 9 || this.transactionInfo.getContractType() == 30) {
                this.tvSaTitle.setText(R.string.from_address);
                this.tvRaTitle.setText(R.string.to_address);
            }
            if (!StringTronUtil.isEmpty(this.transferOutput.to)) {
                setAddress(this.tvRa, this.tvRaWalletName, this.ivRaSc, this.transferOutput.to);
                this.llRa.setVisibility(View.VISIBLE);
                this.raTitleLayout.setVisibility(View.VISIBLE);
            } else {
                this.llRa.setVisibility(View.GONE);
                this.raTitleLayout.setVisibility(View.GONE);
            }
            if (!StringTronUtil.isEmpty(this.transferOutput.from)) {
                setAddress(this.tvSa, this.tvSaWalletName, this.ivSaSc, this.transferOutput.from);
            } else {
                this.llSa.setVisibility(View.GONE);
                this.saTitleLayout.setVisibility(View.GONE);
            }
        }
        this.tvTn.setText(this.hash);
        if (this.transferOutput.from == null) {
            this.tvSa.setText(this.address);
            this.tvRa.setText(this.transferOutput.contract_address);
        }
        if (this.tvSa.getText() == null || (this.tvSa.getText() != null && this.tvSa.getText().equals(HelpFormatter.DEFAULT_OPT_PREFIX))) {
            this.ivSaCopy.setVisibility(View.GONE);
            this.hasSa = false;
        }
        if (this.tvRa.getText() == null || (this.tvRa.getText() != null && this.tvRa.getText().equals(HelpFormatter.DEFAULT_OPT_PREFIX))) {
            this.ivRaCopy.setVisibility(View.GONE);
            this.hasRa = false;
        }
        this.tvTime.setText(DateUtils.diffLanguageDate(this.transferOutput.block_timestamp == 0 ? this.transactionInfo.getTimestamp() : this.transferOutput.block_timestamp));
        this.url = getUrl();
        this.ivCode.setImageBitmap(WalletUtils.strToQRHasLogo(this.url, UIUtils.dip2px(120.0f), UIUtils.dip2px(120.0f), ((BitmapDrawable) getResources().getDrawable(R.mipmap.iv_transcan)).getBitmap()));
        TouchDelegateUtils.expandViewTouchDelegate(this.ivContractType, UIUtils.dip2px(20.0f), UIUtils.dip2px(20.0f), UIUtils.dip2px(20.0f), UIUtils.dip2px(20.0f));
        TouchDelegateUtils.expandViewTouchDelegate(this.llCodeCopy, UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f));
        TransactionInfoBean transactionInfoBean17 = this.transactionInfo;
        if (transactionInfoBean17 != null && transactionInfoBean17.getSignature_addresses() != null && this.transactionInfo.getSignature_addresses().size() > 1) {
            this.signAccountLayout.setVisibility(View.VISIBLE);
            List<?> signature_addresses = this.transactionInfo.getSignature_addresses();
            for (int i = 0; i < signature_addresses.size(); i++) {
                final String str = (String) signature_addresses.get(i);
                if (i == 0) {
                    this.signAddressLayout1.setVisibility(View.VISIBLE);
                    setAddressWithOutIcon(this.signTextView1, this.signTextView1Name, str);
                    this.signAddressLayout1.setOnClickListener(new NoDoubleClickListener() {
                        @Override
                        protected void onNoDoubleClick(View view) {
                            UIUtils.copy(str);
                            TransactionDetailActivity transactionDetailActivity = TransactionDetailActivity.this;
                            transactionDetailActivity.toast(transactionDetailActivity.getString(R.string.copy_susscess));
                        }
                    });
                }
                if (i == 1) {
                    this.signAddressLayout2.setVisibility(View.VISIBLE);
                    setAddressWithOutIcon(this.signTextView2, this.signTextView2Name, str);
                    this.signAddressLayout2.setOnClickListener(new NoDoubleClickListener() {
                        @Override
                        protected void onNoDoubleClick(View view) {
                            UIUtils.copy(str);
                            TransactionDetailActivity transactionDetailActivity = TransactionDetailActivity.this;
                            transactionDetailActivity.toast(transactionDetailActivity.getString(R.string.copy_susscess));
                        }
                    });
                }
                if (i == 2) {
                    this.signAddressLayout3.setVisibility(View.VISIBLE);
                    setAddressWithOutIcon(this.signTextView3, this.signTextView3Name, str);
                    this.signAddressLayout3.setOnClickListener(new NoDoubleClickListener() {
                        @Override
                        protected void onNoDoubleClick(View view) {
                            UIUtils.copy(str);
                            TransactionDetailActivity transactionDetailActivity = TransactionDetailActivity.this;
                            transactionDetailActivity.toast(transactionDetailActivity.getString(R.string.copy_susscess));
                        }
                    });
                }
                if (i == 3) {
                    this.signAddressLayout4.setVisibility(View.VISIBLE);
                    setAddressWithOutIcon(this.signTextView4, this.signTextView4Name, str);
                    this.signAddressLayout4.setOnClickListener(new NoDoubleClickListener() {
                        @Override
                        protected void onNoDoubleClick(View view) {
                            UIUtils.copy(str);
                            TransactionDetailActivity transactionDetailActivity = TransactionDetailActivity.this;
                            transactionDetailActivity.toast(transactionDetailActivity.getString(R.string.copy_susscess));
                        }
                    });
                }
                if (i == 4) {
                    this.signAddressLayout5.setVisibility(View.VISIBLE);
                    setAddressWithOutIcon(this.signTextView5, this.signTextView5Name, str);
                    this.signAddressLayout5.setOnClickListener(new NoDoubleClickListener() {
                        @Override
                        protected void onNoDoubleClick(View view) {
                            UIUtils.copy(str);
                            TransactionDetailActivity transactionDetailActivity = TransactionDetailActivity.this;
                            transactionDetailActivity.toast(transactionDetailActivity.getString(R.string.copy_susscess));
                        }
                    });
                }
            }
        }
        checkAddressScam((String) this.tvSa.getTag(), this.ivScamSendTag);
        checkAddressScam((String) this.tvRa.getTag(), this.ivScamTag);
    }

    private void checkAddressScam(String str, final View view) {
        if (StringTronUtil.isNullOrEmpty(str)) {
            return;
        }
        AddressManager.checkSingleAddressScam(str).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$checkAddressScam$1(view, (RiskAccountOutput) obj);
            }
        });
    }

    public void lambda$checkAddressScam$1(View view, RiskAccountOutput riskAccountOutput) throws Exception {
        if (riskAccountOutput.getData().isRisk()) {
            showScamView(view);
        }
    }

    private void showScamView(final View view) {
        view.setVisibility(View.VISIBLE);
        view.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view2) {
                tipsWindow = new MultiLineTextPopupView.Builder().setAnchor(view).setPreferredShowUp(true).setRequiredWidth(UIUtils.dip2px(243.0f)).addKeyValue(mContext.getString(R.string.risk_account_tip), "").build(mContext);
                tipsWindow.show();
            }
        });
    }

    private void setAddressWithOutIcon(TextView textView, TextView textView2, String str) {
        if (AddressMapUtils.getInstance().isContainsAddress(str)) {
            textView2.setVisibility(View.VISIBLE);
            textView2.setText(AddressMapUtils.getInstance().getNameAddress(str).getName());
            textView.setText("(" + ((Object) AddressMapUtils.getInstance().getNameAddress(str).getAddress()) + ")");
        } else {
            textView2.setVisibility(View.GONE);
            textView.setText(str);
        }
        textView.setTag(str);
    }

    public void setAddress(TextView textView, TextView textView2, ImageView imageView, String str) {
        CenterSpaceImageSpan centerSpaceImageSpan;
        String str2;
        if (StringTronUtil.equals(WalletUtils.getSelectedWallet().getAddress(), str)) {
            textView.setTextColor(getResources().getColor(R.color.black_23));
            textView2.setTextColor(getResources().getColor(R.color.black_23));
        } else {
            textView2.setTextColor(getResources().getColor(R.color.blue_3c));
            textView.setTextColor(getResources().getColor(R.color.blue_3c));
        }
        TransactionInfoBean transactionInfoBean = this.transactionInfo;
        if (transactionInfoBean != null && transactionInfoBean.getContract_map() != null && this.transactionInfo.getContract_map().get(str) != null && this.transactionInfo.getContract_map().get(str).booleanValue()) {
            imageView.setVisibility(View.VISIBLE);
            centerSpaceImageSpan = new CenterSpaceImageSpan(this.mContext, R.mipmap.sc_icon, 2, UIUtils.dip2px(4.0f), UIUtils.dip2px(4.0f));
            str2 = HelpFormatter.DEFAULT_OPT_PREFIX;
        } else {
            imageView.setVisibility(View.GONE);
            centerSpaceImageSpan = null;
            str2 = "";
        }
        if (AddressMapUtils.getInstance().isContainsAddress(str)) {
            textView2.setText(AddressMapUtils.getInstance().getNameAddress(str).getName());
            textView2.setVisibility(View.VISIBLE);
            if (centerSpaceImageSpan != null) {
                new SpannableString(str2 + "(" + str + ")").setSpan(centerSpaceImageSpan, 0, 1, 17);
                StringBuilder sb = new StringBuilder("(");
                sb.append(str);
                sb.append(")");
                textView.setText(sb.toString());
                imageView.setVisibility(View.VISIBLE);
            } else {
                imageView.setVisibility(View.GONE);
                textView.setText("(" + str + ")");
            }
        } else {
            textView2.setVisibility(View.GONE);
            if (centerSpaceImageSpan != null) {
                new SpannableString(str2 + str).setSpan(centerSpaceImageSpan, 0, 1, 17);
                textView.setText(str);
                imageView.setVisibility(View.VISIBLE);
            } else {
                textView.setText(str);
                imageView.setVisibility(View.GONE);
            }
        }
        textView.setTag(str);
    }

    private String getDisPlayNameAddress(NameAddressExtraBean nameAddressExtraBean) {
        if (nameAddressExtraBean != null) {
            if (nameAddressExtraBean.getName().length() > 12) {
                return nameAddressExtraBean.getName().toString().substring(0, 9) + "...(" + StringTronUtil.indentAddress(nameAddressExtraBean.getAddress().toString(), 6) + ")";
            }
            return nameAddressExtraBean.getName().toString() + "(" + StringTronUtil.indentAddress(nameAddressExtraBean.getAddress().toString(), 6) + ")";
        }
        return "";
    }

    public String getUrl() {
        String str = TronConfig.TRONSCANHOST_MAINCHAIN + this.hash;
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return str + "?lang=zh";
        }
        return str + "?lang=en";
    }

    private String getAccountUrl(String str) {
        String accountUrl = IRequest.getAccountUrl(str);
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return accountUrl + "?lang=zh";
        }
        return accountUrl + "?lang=en";
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }

    public void clickTvSa() {
        if (this.hasSa) {
            UIUtils.copy((String) this.tvSa.getTag());
            toast(getString(R.string.copy_susscess));
        }
        if (StringTronUtil.equals(WalletUtils.getSelectedWallet().getAddress(), this.tvSa.getTag().toString())) {
            return;
        }
        AddressBottomView.showPop(this, this.tvSa.getTag().toString(), new Action() {
            @Override
            public final void apply(View view, int i) {
                lambda$clickTvSa$3(view, i);
            }
        }, new Action() {
            @Override
            public final void apply(View view, int i) {
                lambda$clickTvSa$4(view, i);
            }
        });
    }

    public void lambda$clickTvSa$3(View view, int i) {
        if (AddressController.getInstance(this).isAddressCountMax()) {
            runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$clickTvSa$2();
                }
            });
        } else {
            AddToAddressBookPopView.showUp(this, (String) this.tvSa.getTag(), getAddressBookCallback(this.tvSa, this.tvSaWalletName, this.ivSaSc));
        }
    }

    public void lambda$clickTvSa$2() {
        toast(getString(R.string.address_no_add));
    }

    public void lambda$clickTvSa$4(View view, int i) {
        CommonWebActivityV3.start(this.mContext, getAccountUrl((String) this.tvSa.getTag()), this.mContext.getString(isAddressSC((String) this.tvSa.getTag()) ? R.string.sc_details : R.string.account_details), 1, true);
    }

    public void clickTvRa() {
        if (this.hasRa) {
            UIUtils.copy((String) this.tvRa.getTag());
            toast(getString(R.string.copy_susscess));
        }
        if (StringTronUtil.equals(WalletUtils.getSelectedWallet().getAddress(), this.tvRa.getTag().toString())) {
            return;
        }
        AddressBottomView.showPop(this, this.tvRa.getTag().toString(), new Action() {
            @Override
            public final void apply(View view, int i) {
                lambda$clickTvRa$6(view, i);
            }
        }, new Action() {
            @Override
            public final void apply(View view, int i) {
                lambda$clickTvRa$7(view, i);
            }
        });
    }

    public void lambda$clickTvRa$6(View view, int i) {
        if (AddressController.getInstance(this).isAddressCountMax()) {
            runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$clickTvRa$5();
                }
            });
        } else {
            AddToAddressBookPopView.showUp(this, (String) this.tvRa.getTag(), getAddressBookCallback(this.tvRa, this.tvRaWalletName, this.ivRaSc));
        }
    }

    public void lambda$clickTvRa$5() {
        toast(getString(R.string.address_no_add));
    }

    public void lambda$clickTvRa$7(View view, int i) {
        CommonWebActivityV3.start(this.mContext, getAccountUrl((String) this.tvRa.getTag()), this.mContext.getString(isAddressSC((String) this.tvRa.getTag()) ? R.string.sc_details : R.string.account_details), 1, true);
    }

    private void showAsTop(PopupWindow popupWindow, View view, View view2) {
        view.measure(0, 0);
        int measuredHeight = view.getMeasuredHeight();
        view.getMeasuredWidth();
        int[] iArr = new int[2];
        view2.getLocationOnScreen(iArr);
        popupWindow.showAtLocation(view2, 0, iArr[0], (iArr[1] - measuredHeight) - 20);
    }

    private void setFeeView(TransactionInfoBean transactionInfoBean) {
        String str;
        boolean z;
        BigDecimal sum_;
        if (this.token.getType() == 2) {
            this.transactionFeeView.bindData(transactionInfoBean);
            this.transactionFeeView.setVisibility(View.VISIBLE);
            this.amountLine.setVisibility(View.VISIBLE);
            if (CustomTokenStatus.APPROVE_EVENT.equals(this.transferOutput.getEvent_type())) {
                this.tvAmount.setTextColor(getResources().getColor(R.color.blue_3c));
                NumberFormat.getNumberInstance(Locale.US).setMaximumFractionDigits(7);
                BigDecimal div_ = BigDecimalUtils.div_(this.transferOutput.getApproval_amount(), Double.valueOf(Math.pow(10.0d, this.mPrecision)));
                if (isUnlimited(this.transferOutput.getApproval_amount(), this.token) || BigDecimalUtils.MoreThan(new BigDecimal(0), div_)) {
                    this.tvAmount.setText(getString(R.string.unlimited) + " " + this.token.getShortName());
                } else {
                    this.tvAmount.setText(BigDecimalUtils.toString(div_) + " " + this.token.getShortName());
                }
            } else {
                this.tvAmount.setText(getAmountWithPrecision(true));
                if (StringTronUtil.isEmpty(this.transferOutput.getMark())) {
                    this.tvAmount.setTextColor(getResources().getColor(R.color.blue_3c));
                }
            }
            if (transactionInfoBean.isRevert() || !TransactionMessage.TYPE_SUCCESS.equals(transactionInfoBean.getContractRet())) {
                this.tvAmount.setTextColor(getResources().getColor(R.color.gray_9B));
            }
            if (OfficialType.isScamOrUnSafeToken(this.token)) {
                TokenItemUtil.initUnKnownScamFlagView(getIContext(), this.ivScam, this.token, true);
            }
        } else if (this.token.getType() == 1) {
            this.transactionFeeView.bindData(transactionInfoBean);
            this.transactionFeeView.setVisibility(View.VISIBLE);
            this.tvAmount.setText(getAmountWithPrecision(true));
            this.amountLine.setVisibility(View.VISIBLE);
            if (transactionInfoBean.isRevert() || !TransactionMessage.TYPE_SUCCESS.equals(transactionInfoBean.getContractRet())) {
                this.tvAmount.setTextColor(getResources().getColor(R.color.gray_9B));
            }
            if (OfficialType.isScamOrUnSafeToken(this.token)) {
                TokenItemUtil.initUnKnownScamFlagView(getIContext(), this.ivScam, this.token, true);
            }
        } else {
            String contract_ret = this.transferOutput.getContract_ret();
            if (this.transferOutput.getRevert() == 1 || (!TransactionMessage.TYPE_SUCCESS.equals(contract_ret) && !StringTronUtil.isEmpty(contract_ret))) {
                this.tvAmount.setVisibility(View.GONE);
                this.liTvAmount.setVisibility(View.GONE);
                this.transactionResourceView.bindData(getAmountWithPrecision(true), transactionInfoBean, this.transferOutput.getContract_type());
            }
            String str2 = "";
            if ("TriggerSmartContract".equals(this.transferOutput.getContract_type())) {
                if (this.transferOutput.isInternal_transaction() || (this.mIsFromMessageCenter && this.transferOutput.getMark().equals(Marker.ANY_NON_NULL_MARKER))) {
                    this.transactionFeeView.bindData(transactionInfoBean);
                    this.transactionFeeView.setVisibility(View.VISIBLE);
                    this.amountLine.setVisibility(View.VISIBLE);
                    this.tvAmount.setText(getAmountWithPrecision(true));
                    return;
                }
                this.transactionResourceView.setVisibility(View.VISIBLE);
                this.transactionResourceView.bindData(getAmountWithPrecision(true), transactionInfoBean, this.transferOutput.getContract_type());
                if (this.fee == 0 || !this.transferOutput.from.equals(WalletUtils.getSelectedWallet().getAddress())) {
                    z = true;
                    this.tvAmount.setText(getAmountWithPrecision(true));
                } else {
                    BigDecimal bigDecimal = new BigDecimal(this.transferOutput.getMark() + getAmountWithPrecision(false));
                    StringBuilder sb = new StringBuilder(HelpFormatter.DEFAULT_OPT_PREFIX);
                    sb.append(this.numberFormat.format(((double) this.fee) / 1000000.0d));
                    if (BigDecimalUtils.moreThanOrEqual(BigDecimalUtils.sum_(bigDecimal, new BigDecimal(sb.toString())), new BigDecimal(0))) {
                        str2 = Marker.ANY_NON_NULL_MARKER;
                    } else {
                        this.transferOutput.setMark(HelpFormatter.DEFAULT_OPT_PREFIX);
                    }
                    if (this.transferOutput.getMark().equals(HelpFormatter.DEFAULT_OPT_PREFIX)) {
                        this.tvAmount.setTextColor(getResources().getColor(R.color.red_EC));
                    } else {
                        this.tvAmount.setTextColor(getResources().getColor(R.color.green_57));
                    }
                    this.tvAmount.setText(str2 + StringTronUtil.plainScientificNotation(sum_) + "\tTRX");
                    z = true;
                }
                if (OfficialType.isScamOrUnSafeToken(this.token)) {
                    TokenItemUtil.initUnKnownScamFlagView(getIContext(), this.ivScam, this.token, z);
                }
            } else if ("WithdrawBalanceContract".equals(this.transferOutput.getContract_type())) {
                this.transactionResourceView.setVisibility(View.VISIBLE);
                this.transactionFeeView.setVisibility(View.GONE);
                this.transactionResourceView.bindData(Marker.ANY_NON_NULL_MARKER + StringTronUtil.plainScientificNotation(BigDecimalUtils.toBigDecimal(getAmountWithPrecision(false))) + " TRX", transactionInfoBean, this.transferOutput.getContract_type());
                BigDecimal bigDecimal2 = new BigDecimal(getAmountWithPrecision(false));
                BigDecimal bigDecimal3 = new BigDecimal(this.numberFormat.format(((double) this.fee) / 1000000.0d));
                if (BigDecimalUtils.equalsZero((Number) Long.valueOf(this.fee))) {
                    this.tvAmount.setText(getAmountWithPrecision(true));
                    return;
                }
                if (BigDecimalUtils.moreThanOrEqual(BigDecimalUtils.sub_(bigDecimal2, bigDecimal3), new BigDecimal(0))) {
                    str2 = Marker.ANY_NON_NULL_MARKER;
                } else {
                    this.transferOutput.setMark(HelpFormatter.DEFAULT_OPT_PREFIX);
                }
                if (str2.equals(Marker.ANY_NON_NULL_MARKER)) {
                    this.tvAmount.setTextColor(getResources().getColor(R.color.green_57));
                } else {
                    this.tvAmount.setTextColor(getResources().getColor(R.color.red_EC));
                }
                this.tvAmount.setText(str2 + StringTronUtil.plainScientificNotation(BigDecimalUtils.sub_(bigDecimal2, bigDecimal3)) + "\tTRX");
            } else if (this.transferOutput.getMark().equals(Marker.ANY_NON_NULL_MARKER) && !"UnfreezeBalanceContract".equals(this.transferOutput.getContract_type())) {
                this.tvAmount.setText(getAmountWithPrecision(true));
                this.transactionResourceView.setVisibility(View.GONE);
                if (this.transferOutput.getMark().equals(Marker.ANY_NON_NULL_MARKER)) {
                    this.transactionFeeView.bindData(transactionInfoBean);
                    this.transactionFeeView.setVisibility(View.VISIBLE);
                    this.amountLine.setVisibility(View.VISIBLE);
                }
            } else {
                this.transactionFeeView.setVisibility(View.GONE);
                this.transactionResourceView.setVisibility(View.VISIBLE);
                if ("FeeContract".equals(this.transferOutput.getContract_type())) {
                    this.tvAmount.setText(getAmountWithPrecision(true));
                    int contractType = this.transactionInfo.getContractType();
                    if (contractType != 0 && contractType != 2 && contractType != 4 && contractType != 8 && contractType != 10 && contractType != 33 && contractType != 30) {
                        if (contractType == 31) {
                            if (this.transactionInfo.getContractData().getAmount() > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                                this.transactionResourceView.bindData(getAmountWithPrecision(true), transactionInfoBean, this.transferOutput.getContract_type());
                                return;
                            } else {
                                this.transactionResourceView.bindData("-0 TRX", transactionInfoBean, this.transferOutput.getContract_type());
                                return;
                            }
                        } else if (contractType != 48 && contractType != 49 && contractType != 52 && contractType != 53) {
                            if (contractType == 57 || contractType == 58) {
                                this.transactionResourceView.bindData("0 TRX", transactionInfoBean, this.transferOutput.getContract_type());
                                return;
                            }
                            switch (contractType) {
                                case 13:
                                case 14:
                                case 15:
                                case 16:
                                case 17:
                                case 18:
                                case 19:
                                    break;
                                default:
                                    switch (contractType) {
                                        case 41:
                                        case 42:
                                        case 43:
                                        case 44:
                                        case 45:
                                            break;
                                        default:
                                            this.transactionResourceView.bindData(getAmountWithPrecision(true), transactionInfoBean, this.transferOutput.getContract_type());
                                            return;
                                    }
                            }
                        }
                    }
                    this.transactionResourceView.bindData("-0 TRX", transactionInfoBean, this.transferOutput.getContract_type());
                } else if ("UnfreezeBalanceContract".equals(this.transferOutput.getContract_type()) || "FreezeBalanceV2Contract".equals(this.transferOutput.getContract_type()) || "UnfreezeBalanceV2Contract".equals(this.transferOutput.getContract_type()) || "WithdrawExpireUnfreezeContract".equals(this.transferOutput.getContract_type()) || "UnfreezeV2WithdrawContract".equals(this.transferOutput.getContract_type()) || "CancelAllUnfreezeV2Contract".equals(this.transferOutput.getContract_type()) || "CancelAllUnfreezeV2WithdrawContract".equals(this.transferOutput.getContract_type())) {
                    this.tvAmount.setVisibility(View.GONE);
                    this.liTvAmount.setVisibility(View.GONE);
                    this.transactionResourceView.bindData(getAmountWithPrecision(true), transactionInfoBean, this.transferOutput.getContract_type());
                } else if ("AccountPermissionUpdateContract".equals(this.transferOutput.getContract_type()) || "WitnessCreateContract".equals(this.transferOutput.getContract_type()) || "AssetIssueContract".equals(this.transferOutput.getContract_type()) || "ExchangeCreateContract".equals(this.transferOutput.getContract_type())) {
                    this.tvAmount.setText(getAmountWithPrecision(true));
                    this.transactionResourceView.bindData("-0 TRX", transactionInfoBean, this.transferOutput.getContract_type());
                } else if (this.transferOutput.getRevert() == 1 || (!TransactionMessage.TYPE_SUCCESS.equals(contract_ret) && !StringTronUtil.isEmpty(contract_ret))) {
                    if ("TransferContract".equals(this.transferOutput.getContract_type())) {
                        this.tvAmount.setVisibility(View.GONE);
                        this.liTvAmount.setVisibility(View.GONE);
                        this.transactionResourceView.bindData(getAmountWithPrecision(true), transactionInfoBean, this.transferOutput.getContract_type());
                    }
                } else {
                    BigDecimal bigDecimal4 = new BigDecimal(this.transferOutput.getMark() + getAmountWithPrecision(false));
                    StringBuilder sb2 = new StringBuilder(HelpFormatter.DEFAULT_OPT_PREFIX);
                    sb2.append(this.numberFormat.format(((double) this.fee) / 1000000.0d));
                    BigDecimal bigDecimal5 = new BigDecimal(sb2.toString());
                    TextView textView = this.tvAmount;
                    if (this.transferOutput.getMark().equals(Marker.ANY_NON_NULL_MARKER)) {
                        str = this.transferOutput.getMark();
                    } else {
                        str = "" + StringTronUtil.plainScientificNotation(BigDecimalUtils.sum_(bigDecimal4, bigDecimal5)) + "\tTRX";
                    }
                    textView.setText(str);
                    this.transactionResourceView.bindData(getAmountWithPrecision(true), transactionInfoBean, this.transferOutput.getContract_type());
                }
            }
        }
    }

    public void showPopZero(int i, View view, boolean z) {
        View inflate = getLayoutInflater().inflate(R.layout.pop_fee_zero, (ViewGroup) null);
        PopupWindow popupWindow = this.popupWindow;
        if (popupWindow != null && popupWindow.isShowing()) {
            this.popupWindow.dismiss();
        }
        PopupWindow popupWindow2 = new PopupWindow(inflate, -2, -2, true);
        this.popupWindow = popupWindow2;
        popupWindow2.setOutsideTouchable(true);
        ((TextView) this.popupWindow.getContentView().findViewById(R.id.tv_content)).setText(i);
        if (z) {
            this.popupWindow.showAsDropDown(view, 0, 20);
        } else {
            showAsTop(this.popupWindow, inflate, view);
        }
    }

    @Override
    public void onDestroy() {
        this.rxManager.clear();
        super.onDestroy();
    }

    private String getAmountWithPrecision(boolean z) {
        String valueOf;
        BigDecimal bigDecimal;
        TokenBean tokenBean = this.token;
        if (tokenBean == null || this.transferOutput == null) {
            return "";
        }
        boolean z2 = tokenBean.type == 0;
        if (this.transferOutput.tx_status == 1 || this.transferOutput.tx_status == 2) {
            valueOf = String.valueOf(this.transferOutput.amount);
            bigDecimal = null;
        } else {
            bigDecimal = new BigDecimal(this.transferOutput.amount).divide(new BigDecimal(z2 ? 1000000.0d : Math.pow(10.0d, this.transferOutput.decimals)));
            valueOf = StringTronUtil.plainScientificNotation(bigDecimal);
        }
        if (this.transferOutput.getMark().equals(HelpFormatter.DEFAULT_OPT_PREFIX)) {
            this.tvAmount.setTextColor(getResources().getColor(R.color.red_EC));
        } else {
            this.tvAmount.setTextColor(getResources().getColor(R.color.green_57));
        }
        String contract_ret = this.transferOutput.getContract_ret();
        if (this.transferOutput.getRevert() == 1 || (!TransactionMessage.TYPE_SUCCESS.equals(contract_ret) && !StringTronUtil.isEmpty(contract_ret))) {
            this.tvAmount.setTextColor(getResources().getColor(R.color.gray_9B));
        }
        if (CustomTokenStatus.APPROVE_EVENT.equals(this.transferOutput.getEvent_type())) {
            this.tvAmount.setTextColor(getResources().getColor(R.color.blue_3c));
            NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
            numberInstance.setMaximumFractionDigits(7);
            String format = numberInstance.format(BigDecimalUtils.div_(this.transferOutput.getApproval_amount(), Double.valueOf(Math.pow(10.0d, this.mPrecision))));
            BigDecimal div_ = BigDecimalUtils.div_(this.transferOutput.getApproval_amount(), Double.valueOf(Math.pow(10.0d, this.mPrecision)));
            if (BigDecimalUtils.MoreThan(div_, new BigDecimal(Math.pow(10.0d, 9.0d))) || BigDecimalUtils.MoreThan(new BigDecimal(0), div_)) {
                TextView textView = this.tvAmount;
                textView.setText(getString(R.string.unlimited) + " " + this.token.getShortName());
            } else {
                TextView textView2 = this.tvAmount;
                textView2.setText(format + " " + this.token.getShortName());
            }
        }
        if (z) {
            Object[] objArr = new Object[2];
            objArr[0] = this.transferOutput.getMark() + valueOf;
            objArr[1] = z2 ? "TRX" : this.token.getShortName();
            return String.format("%s %s", objArr);
        }
        return bigDecimal.toString();
    }

    private void cloneViewContent(View view, ViewGroup viewGroup) {
        View findViewById;
        int id = viewGroup.getId();
        if (id > 0 && (view = view.findViewById(id)) != null) {
            view.setVisibility(viewGroup.getVisibility());
        }
        if (viewGroup.getVisibility() == 0) {
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt instanceof ViewGroup) {
                    cloneViewContent(view, (ViewGroup) childAt);
                } else {
                    int id2 = childAt.getId();
                    if (id2 > 0 && (findViewById = view.findViewById(id2)) != null) {
                        findViewById.setVisibility(childAt.getVisibility());
                        if (childAt instanceof TextView) {
                            TextView textView = (TextView) childAt;
                            TextView textView2 = (TextView) findViewById;
                            if (textView.getTag() != null && !StringTronUtil.isEmpty((String) textView.getTag())) {
                                textView2.setText((String) textView.getTag());
                            } else {
                                textView2.setText(textView.getText());
                            }
                        } else if (childAt instanceof ImageView) {
                            ImageView imageView = (ImageView) childAt;
                            ImageView imageView2 = (ImageView) findViewById;
                            if (imageView.getBackground() != null) {
                                imageView2.setBackground(imageView.getBackground());
                            }
                            if (imageView.getDrawable() != null) {
                                imageView2.setImageDrawable(imageView.getDrawable());
                            }
                        }
                    }
                }
            }
        }
    }

    private void setCopyViewAndShare() {
        if (this.copyView == null) {
            View inflate = LayoutInflater.from(this).inflate(R.layout.activity_transaction_detail, (ViewGroup) null);
            this.copyView = inflate;
            ((ViewGroup) findViewById(16908290)).addView(inflate, 0);
        }
        View findViewById = this.copyView.findViewById(R.id.ll_scroll);
        cloneViewContent(findViewById, this.llContent);
        TextView textView = (TextView) this.copyView.findViewById(R.id.tv_state);
        textView.setText(this.tvState.getText());
        textView.setTextColor(this.tvState.getTextColors());
        textView.setCompoundDrawablesWithIntrinsicBounds(this.tvState.getCompoundDrawables()[0], (Drawable) null, (Drawable) null, (Drawable) null);
        ((TextView) this.copyView.findViewById(R.id.tv_amount)).setTextColor(this.tvAmount.getTextColors());
        ((TransactionResourceView) this.copyView.findViewById(R.id.layout_transaction_detail_resource)).setViewForShare(getAmountWithPrecision(true), this.transactionInfo, this.transferOutput.getContract_type());
        ((TransactionFeeResourceView) this.copyView.findViewById(R.id.resource_info_view2)).setViewForShare(this.transactionInfo);
        TextView textView2 = (TextView) this.copyView.findViewById(R.id.tv_sa);
        textView2.setText((String) this.tvSa.getTag());
        textView2.setSingleLine(false);
        this.copyView.findViewById(R.id.iv_sa_copy).setVisibility(View.GONE);
        TextView textView3 = (TextView) this.copyView.findViewById(R.id.tv_ra);
        textView3.setText((String) this.tvRa.getTag());
        textView3.setSingleLine(false);
        this.copyView.findViewById(R.id.iv_ra_copy).setVisibility(View.GONE);
        this.copyView.findViewById(R.id.iv_contract_type).setVisibility(View.GONE);
        ((TextView) this.copyView.findViewById(R.id.tv_tn)).setSingleLine(false);
        this.copyView.findViewById(R.id.iv_hash_copy).setVisibility(View.GONE);
        this.copyView.findViewById(R.id.ll_code_copy).setVisibility(View.GONE);
        View findViewById2 = this.copyView.findViewById(R.id.ll_content);
        findViewById2.setPadding(UIUtils.dip2px(0.0f), UIUtils.dip2px(30.0f), UIUtils.dip2px(0.0f), UIUtils.dip2px(30.0f));
        findViewById2.setBackgroundResource(R.mipmap.share_bg);
        this.copyView.findViewById(R.id.iv_copy_1).setVisibility(View.GONE);
        this.copyView.findViewById(R.id.iv_copy_2).setVisibility(View.GONE);
        this.copyView.findViewById(R.id.iv_copy_3).setVisibility(View.GONE);
        this.copyView.findViewById(R.id.iv_copy_4).setVisibility(View.GONE);
        this.copyView.findViewById(R.id.iv_copy_5).setVisibility(View.GONE);
        this.copyView.post(new fun14(findViewById, findViewById2));
    }

    public class fun14 implements Runnable {
        final View val$content;
        final View val$scrollView;

        fun14(View view, View view2) {
            this.val$scrollView = view;
            this.val$content = view2;
        }

        @Override
        public void run() {
            ShareHelper.getInstance().shareImage(TransactionDetailActivity.this, this.val$scrollView, this.val$content, new io.reactivex.functions.Action() {
                @Override
                public final void run() {
                    TransactionDetailActivity.14.this.lambda$run$0();
                }
            });
        }

        public void lambda$run$0() throws Exception {
            ToastAsBottom(R.string.save_fail);
        }
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        ShareHelper.getInstance().handleActivityResult(i, i2, intent);
        super.onActivityResult(i, i2, intent);
    }

    @Override
    public String[] getPermissions() {
        return new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    }

    @Override
    public void requestPermissionsSuccess() {
        setCopyViewAndShare();
    }

    @Override
    public void requestPermissionsFail() {
        ToastError(R.string.error_permission2);
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        PermissionHelper permissionHelper = this.mPermissionHelper;
        if (permissionHelper == null || !permissionHelper.requestPermissionsResult(i, strArr, iArr)) {
            super.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    @Override
    public void toast(final String str) {
        if (StringTronUtil.isNullOrEmpty(str)) {
            return;
        }
        runOnUiThread(new Runnable() {
            @Override
            public final void run() {
                IToast.getIToast().setImage(R.mipmap.toast_warn).forceShowAgain(str);
            }
        });
    }

    public void prepareShareImage() {
        if (this.mPermissionHelper == null) {
            this.mPermissionHelper = new PermissionHelper(this, this);
        }
        if (Build.VERSION.SDK_INT < 33) {
            this.mPermissionHelper.requestPermissions();
        } else {
            setCopyViewAndShare();
        }
    }

    public boolean supportShareUrl(TransactionHistoryBean transactionHistoryBean) {
        if (SpAPI.THIS.getCurrentChain() == null || SpAPI.THIS.getCurrentChain().isMainChain) {
            TokenBean tokenBean = this.token;
            return tokenBean == null || tokenBean.getTokenStatus() == 0;
        }
        return false;
    }

    private boolean supportShare(String str) {
        return TextUtils.equals(str, "Transfer") || TextUtils.equals(str, CustomTokenStatus.APPROVE_EVENT) || TextUtils.equals(str, "721Transfer");
    }

    private boolean supportShareTrc721(String str) {
        return TextUtils.equals(str, "Transfer") || TextUtils.equals(str, CustomTokenStatus.APPROVE_EVENT);
    }

    @Override
    public void updateTokenScamFlag() {
        this.ivScam.setVisibility(View.VISIBLE);
        TokenItemUtil.initUnKnownScamFlagView(getIContext(), this.ivScam, this.token, true);
    }

    @Override
    public void updateContent(TransactionInfoBean transactionInfoBean) {
        if (transactionInfoBean == null) {
            return;
        }
        LogUtils.e("TransactionDetailActivity onResponse", transactionInfoBean.toString());
        this.transactionInfo = transactionInfoBean;
        if (!StringTronUtil.isNullOrEmpty(this.transferOutput.getContract_type()) && !"FeeContract".equals(this.transferOutput.getContract_type()) && !"UnfreezeV2WithdrawContract".equals(this.transferOutput.getContract_type()) && !"CancelAllUnfreezeV2WithdrawContract".equals(this.transferOutput.getContract_type()) && !"UnfreezeBalanceV2Contract".equals(this.transferOutput.getContract_type())) {
            this.transferOutput.setContract_type(TransactionTypeTextProvider.convert(transactionInfoBean.getContractType()));
            if (31 == transactionInfoBean.getContractType() && StringTronUtil.isEmpty(this.transferOutput.getEvent_type())) {
                this.transferOutput.setTo(transactionInfoBean.getContractData().getContract_address());
                this.transferOutput.setFrom(transactionInfoBean.getContractData().getOwner_address());
            }
        } else {
            if (StringTronUtil.isNullOrEmpty(this.transferOutput.getContract_type())) {
                this.transferOutput.setContract_type(TransactionTypeTextProvider.convert(transactionInfoBean.getContractType()));
            }
            if (1 == transactionInfoBean.getContractType() || 9 == transactionInfoBean.getContractType() || 31 == transactionInfoBean.getContractType() || 30 == transactionInfoBean.getContractType() || 13 == transactionInfoBean.getContractType() || 2 == transactionInfoBean.getContractType() || 54 == transactionInfoBean.getContractType() || 56 == transactionInfoBean.getContractType() || ((55 == transactionInfoBean.getContractType() && !"UnfreezeV2WithdrawContract".equals(this.transferOutput.getContract_type())) || (59 == transactionInfoBean.getContractType() && !"CancelAllUnfreezeV2WithdrawContract".equals(this.transferOutput.getContract_type())))) {
                LogUtils.e("TransactionDetailActivity onResponse", "ContractType = " + transactionInfoBean.getContractType());
                if (31 == transactionInfoBean.getContractType()) {
                    this.transferOutput.setTo(transactionInfoBean.getContractData().getContract_address());
                    this.transferOutput.setFrom(transactionInfoBean.getContractData().getOwner_address());
                    this.transactionInfo.getContractData().setAmount(transactionInfoBean.getContractData().getCall_value());
                } else if (13 == transactionInfoBean.getContractType()) {
                    this.transactionInfo.getContractData().setAmount(transactionInfoBean.getInfo().getWithdraw_amount());
                }
                if (this.transactionInfo.getContractData().getAmount() > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && 2 != transactionInfoBean.getContractType()) {
                    this.transferOutput.setContract_type(TransactionTypeTextProvider.convert(transactionInfoBean.getContractType()));
                    if (StringTronUtil.equals(this.transferOutput.getFrom(), WalletUtils.getSelectedWallet().getAddress())) {
                        this.transferOutput.setMark(HelpFormatter.DEFAULT_OPT_PREFIX);
                    } else {
                        this.transferOutput.setMark(Marker.ANY_NON_NULL_MARKER);
                    }
                    this.transferOutput.amount = String.valueOf(this.transactionInfo.getContractData().getAmount());
                }
            }
            if (StringTronUtil.isEmpty(this.transferOutput.to) && !StringTronUtil.isEmpty(this.transactionInfo.getToAddress())) {
                this.transferOutput.setTo(this.transactionInfo.getToAddress());
            }
            if (55 == transactionInfoBean.getContractType() || ((55 == transactionInfoBean.getContractType() && "UnfreezeV2WithdrawContract".equals(this.transferOutput.getContract_type())) || ("FeeContract".equals(this.transferOutput.getContract_type()) && 55 == transactionInfoBean.getContractType()))) {
                if ("FeeContract".equals(this.transferOutput.getContract_type())) {
                    this.transferOutput.setContract_type(TransactionTypeTextProvider.convert(transactionInfoBean.getContractType()));
                    this.transferOutput.amount = String.valueOf(transactionInfoBean.getContractData().getUnfreeze_balance());
                }
                if (StringTronUtil.isEmpty(this.transferOutput.getAmount()) || "0".equals(this.transferOutput.getAmount())) {
                    this.transferOutput.amount = String.valueOf(transactionInfoBean.getContractData().getUnfreeze_balance());
                }
                this.transferOutput.setMark("");
                this.transferOutput.from = transactionInfoBean.getContractData().getOwner_address();
                this.transferOutput.to = transactionInfoBean.getContractData().getOwner_address();
            }
            if (12 == transactionInfoBean.getContractType() || ("FeeContract".equals(this.transferOutput.getContract_type()) && 12 == transactionInfoBean.getContractType())) {
                if ("FeeContract".equals(this.transferOutput.getContract_type())) {
                    this.transferOutput.setContract_type(TransactionTypeTextProvider.convert(transactionInfoBean.getContractType()));
                    this.transferOutput.amount = String.valueOf(transactionInfoBean.getInfo().getUnfreeze_amount());
                }
                if (StringTronUtil.isEmpty(this.transferOutput.getAmount()) || "0".equals(this.transferOutput.getAmount())) {
                    this.transferOutput.amount = String.valueOf(transactionInfoBean.getInfo().getUnfreeze_amount());
                }
                this.transferOutput.setMark("");
                this.transferOutput.from = transactionInfoBean.getContractData().getOwner_address();
                this.transferOutput.to = transactionInfoBean.getContractData().getOwner_address();
            } else if ((59 == transactionInfoBean.getContractType() && "CancelAllUnfreezeV2WithdrawContract".equals(this.transferOutput.getContract_type())) || ("FeeContract".equals(this.transferOutput.getContract_type()) && 59 == transactionInfoBean.getContractType())) {
                if ("FeeContract".equals(this.transferOutput.getContract_type())) {
                    this.transferOutput.setContract_type(TransactionTypeTextProvider.convert(transactionInfoBean.getContractType()));
                    this.transferOutput.amount = String.valueOf(transactionInfoBean.getInfo().getCancelAllUnfreezeV2Amount());
                } else if (StringTronUtil.isEmpty(this.transferOutput.getAmount()) || "0".equals(this.transferOutput.getAmount())) {
                    if ("CancelAllUnfreezeV2WithdrawContract".equals(this.transferOutput.getContract_type())) {
                        this.transferOutput.amount = String.valueOf(transactionInfoBean.getInfo().getWithdraw_expire_amount());
                    } else {
                        this.transferOutput.amount = String.valueOf(transactionInfoBean.getInfo().getCancelAllUnfreezeV2Amount());
                    }
                }
                this.transferOutput.setMark("");
                this.transferOutput.from = transactionInfoBean.getContractData().getOwner_address();
                this.transferOutput.to = transactionInfoBean.getContractData().getOwner_address();
            }
            if (11 == transactionInfoBean.getContractType() || 54 == transactionInfoBean.getContractType()) {
                this.transferOutput.setContract_type(TransactionTypeTextProvider.convert(transactionInfoBean.getContractType()));
                this.transferOutput.amount = String.valueOf(this.transactionInfo.getContractData().getFrozen_balance());
                this.transferOutput.setMark("");
                if (StringTronUtil.isNullOrEmpty(this.transferOutput.from)) {
                    this.transferOutput.from = transactionInfoBean.getContractData().getOwner_address();
                }
                if (StringTronUtil.isNullOrEmpty(this.transferOutput.to)) {
                    this.transferOutput.to = transactionInfoBean.getContractData().getOwner_address();
                }
            } else if (56 == transactionInfoBean.getContractType()) {
                this.transferOutput.setContract_type(TransactionTypeTextProvider.convert(transactionInfoBean.getContractType()));
                this.transferOutput.amount = String.valueOf(this.transactionInfo.getInfo().getWithdraw_expire_amount());
                this.transferOutput.setMark("");
            }
        }
        if ("FreezeBalanceContract".equals(this.transferOutput.getContract_type())) {
            this.tvAmount.setVisibility(View.GONE);
            this.liTvAmount.setVisibility(View.GONE);
        }
        if (this.mIsFromMessageCenter) {
            if ((this.mMessage.getRevert() == 1) != transactionInfoBean.isRevert()) {
                this.transferOutput.setContract_ret(transactionInfoBean.getContractRet());
                this.transferOutput.setRevert(transactionInfoBean.isRevert() ? 1 : 0);
                this.mMessage.setRevert(transactionInfoBean.isRevert() ? 1 : 0);
                this.mMessage.setContract_ret(transactionInfoBean.getContractRet());
                RxManager rxManager = this.rxManager;
                if (rxManager != null) {
                    rxManager.post(Event.MSG_CENTER_STATUS_UPDATE, new TransactionMessageStatusBean(this.mMessage, this.mPosition));
                }
            }
        }
        if (transactionInfoBean.getSignature_addresses() != null && transactionInfoBean.getSignature_addresses().size() > 1) {
            this.transferOutput.setMul(true);
        }
        toUi();
        AnalyticsHelper.logEventFormat(AnalyticsHelper.TransactionDetailsPage.CLICK_TRANSACTION_DETAIL_PAGE_LOAD, Integer.valueOf(transactionInfoBean.getContractType()));
        updateResourceView(transactionInfoBean);
        setFeeView(transactionInfoBean);
    }

    public boolean isUnlimited(String str, TokenBean tokenBean) {
        return BigDecimalUtils.MoreThan(new BigDecimal(str), new BigDecimal(Math.pow(10.0d, (tokenBean != null ? tokenBean.getPrecision() : 0) + 18)));
    }

    AddToAddressBookPopView.AddressBookChangeCallback getAddressBookCallback(final TextView textView, final TextView textView2, final ImageView imageView) {
        return new AddToAddressBookPopView.AddressBookChangeCallback() {
            @Override
            public final void onAddressBookChanged(String str, String str2, String str3) {
                lambda$getAddressBookCallback$9(textView, textView2, imageView, str, str2, str3);
            }
        };
    }

    public void lambda$getAddressBookCallback$9(final TextView textView, final TextView textView2, final ImageView imageView, final String str, String str2, String str3) {
        toast(getString(R.string.saved_successfully));
        NameAddressExtraBean nameAddressExtraBean = new NameAddressExtraBean();
        nameAddressExtraBean.setAddress(str);
        nameAddressExtraBean.setExtra(str3);
        nameAddressExtraBean.setName(str2);
        AddressMapUtils.getInstance().addANameAddressExtraBean(str, nameAddressExtraBean);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setAddress(textView, textView2, imageView, str);
            }
        });
    }

    private boolean isAddressSC(String str) {
        TransactionInfoBean transactionInfoBean = this.transactionInfo;
        return (transactionInfoBean == null || transactionInfoBean.getContract_map() == null || this.transactionInfo.getContract_map().get(str) == null || !this.transactionInfo.getContract_map().get(str).booleanValue()) ? false : true;
    }

    private void updateResourceView(TransactionInfoBean transactionInfoBean) {
        String str;
        String str2;
        if (this.transferOutput.getContract_type() != null && this.transferOutput.getContract_type().equals("ShieldedTransferContract")) {
            this.fee = SpAPI.THIS.getShieldFee();
            Math.pow(10.0d, this.token.getPrecision());
            TextView textView = this.tvResource;
            textView.setText(("0\t" + getString(R.string.bandwidth)) + "\t\t" + ("0\t" + getString(R.string.energy)));
            this.tvResource.setVisibility(View.GONE);
            String str3 = this.transferOutput.memo;
            if (str3 == null || str3.equals("")) {
                return;
            }
            this.llNote.setVisibility(View.VISIBLE);
            this.tvNote.setVisibility(View.VISIBLE);
            this.tvNote.setText(str3);
            return;
        }
        this.fee = transactionInfoBean.getCost().getNet_fee() + transactionInfoBean.getCost().getEnergy_fee() + transactionInfoBean.getCost().getAccount_create_fee() + transactionInfoBean.getCost().getMulti_sign_fee() + transactionInfoBean.getCost().getUpdate_account_permission_fee() + transactionInfoBean.getCost().getWitness_create_fee() + transactionInfoBean.getCost().getMemoFee() + ("AssetIssueContract".equals(this.transferOutput.getContract_type()) ? transactionInfoBean.getCost().getFee() : 0);
        String convert = TransactionTypeTextProvider.convert(transactionInfoBean.getContractType());
        if (transactionInfoBean.getCost().getFee() > 0 && "AssetIssueContract".equals(convert)) {
            this.fee += transactionInfoBean.getCost().getFee();
        }
        if (transactionInfoBean.getCost().getFee() > 0 && "ExchangeCreateContract".equals(convert)) {
            this.fee += transactionInfoBean.getCost().getFee();
        }
        if (transactionInfoBean.getCost().getNet_usage() > 0) {
            str = NumUtils.amountConversion(transactionInfoBean.getCost().getNet_usage()) + "\t" + getString(R.string.bandwidth) + "\t\t\t";
        } else {
            str = "";
        }
        if (transactionInfoBean.getCost().getEnergy_usage() > 0) {
            str2 = NumUtils.amountConversion(transactionInfoBean.getCost().getEnergy_usage()) + "\t" + getString(R.string.energy);
        } else {
            str2 = "";
        }
        if (transactionInfoBean.getCost().getEnergy_usage() == 0 && transactionInfoBean.getCost().getNet_usage() == 0) {
            this.tvResource.setVisibility(View.GONE);
            this.tvResourceTitle.setVisibility(View.GONE);
        } else {
            this.tvResource.setText(str + str2);
        }
        String valueOf = String.valueOf(transactionInfoBean.getData());
        if (valueOf == null || valueOf.equals("")) {
            return;
        }
        this.llNote.setVisibility(View.VISIBLE);
        this.tvNote.setVisibility(View.VISIBLE);
        this.tvNote.setText(valueOf);
    }
}
