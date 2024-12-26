package com.tron.wallet.business.nft.nfttransactiondetail;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lxj.xpopup.core.BasePopupView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.business.addassets2.bean.nft.NftItemInfo;
import com.tron.wallet.business.addassets2.bean.nft.NftItemInfoOutput;
import com.tron.wallet.business.confirm.fg.component.TransactionFeeResourceView;
import com.tron.wallet.business.customtokens.bean.CustomTokenStatus;
import com.tron.wallet.business.message.TransactionMessage;
import com.tron.wallet.business.message.TransactionMessageStatusBean;
import com.tron.wallet.business.nft.nfttransactiondetail.NftTransactionContract;
import com.tron.wallet.business.nft.nfttransactiondetail.NftTransactionDetailActivity;
import com.tron.wallet.business.pull.PullConstants;
import com.tron.wallet.business.transfer.TransactionTypeTextProvider;
import com.tron.wallet.business.transfer.share.ShareHelper;
import com.tron.wallet.business.transfer.share.UrlParamBean;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.common.bean.RiskAccountOutput;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.bean.token.TransactionHistoryBean;
import com.tron.wallet.common.bean.token.TransactionInfoBean;
import com.tron.wallet.common.components.AddToAddressBookPopView;
import com.tron.wallet.common.components.AddressBottomView;
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
import com.tron.wallet.common.utils.DateUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.PermissionHelper;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.common.utils.addressscam.AddressManager;
import com.tron.wallet.databinding.ActivityNftTransactionDetailBinding;
import com.tron.wallet.databinding.NftHistoryDetailTitleContentBinding;
import com.tron.wallet.db.Controller.AddressController;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.HttpAPI;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.text.NumberFormat;
import org.apache.commons.cli.HelpFormatter;
import org.tron.protos.Protocol;
public class NftTransactionDetailActivity extends BaseActivity<NftTransactionPresenter, NftTransactionModel> implements PermissionInterface, NftTransactionContract.View {
    private static final String TAG = "NftTransactionDetailActivity";
    private static String TRONSCAN_DEFAULT_IMAGE_URL = "https://static.tronscan.org/production/upload/logo/default.png";
    private String address;
    private PopupWindow apprpovePopupWindow;
    private ActivityNftTransactionDetailBinding binding;
    private Object block;
    private NftHistoryDetailTitleContentBinding contentBinding;
    private View copyView;
    public String hash;
    View ivScamSendTag;
    View ivScamTag;
    ImageView ivState;
    private boolean mIsFromMessageCenter;
    private TransactionMessage mMessage;
    private PermissionHelper mPermissionHelper;
    private int mPosition;
    private NumberFormat numberFormat;
    private PopupWindow popupWindow;
    private long refBlockNum;
    private RxManager rxManager;
    private String shareFilePath;
    private BasePopupView tipsWindow;
    private TokenBean token;
    TransactionFeeResourceView transactionFeeView;
    private TransactionInfoBean transactionInfo;
    private Protocol.TransactionInfo transactionInfoById;
    RelativeLayout transactionMenuLayout;
    private TransactionHistoryBean transferOutput;
    TextView tvRaWalletName;
    TextView tvSaWalletName;
    private String url;
    private long fee = 0;
    private boolean hasSa = true;
    private boolean hasRa = true;

    @Override
    public int getPermissionsRequestCode() {
        return ErrorConstant.triggerError;
    }

    public static void start(Context context, TransactionHistoryBean transactionHistoryBean, TokenBean tokenBean) {
        Intent intent = new Intent(context, NftTransactionDetailActivity.class);
        intent.putExtra("toBean", transactionHistoryBean);
        intent.putExtra("tbBean", tokenBean);
        context.startActivity(intent);
    }

    public static void start(Context context, TransactionHistoryBean transactionHistoryBean, TokenBean tokenBean, boolean z, TransactionMessage transactionMessage, int i) {
        Intent intent = new Intent(context, NftTransactionDetailActivity.class);
        intent.putExtra("toBean", transactionHistoryBean);
        intent.putExtra("tbBean", tokenBean);
        intent.putExtra("isFromMessageCenter", z);
        intent.putExtra(PullConstants.RESULT_MESSAGE, transactionMessage);
        intent.putExtra("position", i);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        ActivityNftTransactionDetailBinding inflate = ActivityNftTransactionDetailBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        this.contentBinding = NftHistoryDetailTitleContentBinding.bind(inflate.getRoot().findViewById(R.id.include_content));
        setView(this.binding.getRoot(), 3);
        setHeaderBar(getString(R.string.td));
        mappingId();
        setClick();
        getHeaderHolder().ivCommonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$setLayout$0(view);
            }
        });
        getHeaderHolder().tvCommonTitle.setTextSize(22.0f);
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
            NftTransactionDetailActivity nftTransactionDetailActivity = NftTransactionDetailActivity.this;
            if (!nftTransactionDetailActivity.supportShareUrl(nftTransactionDetailActivity.transferOutput)) {
                prepareShareImage();
                return;
            }
            AnalyticsHelper.logEvent(AnalyticsHelper.TokenDetailsPage.CLICK_NFT_TRANSACTION_DETAIL_PAGE_SHARE);
            ShareBottomView.showPop(view.getContext(), new Action() {
                @Override
                public final void apply(View view2, int i) {
                    NftTransactionDetailActivity.1.this.lambda$onNoDoubleClick$0(view2, i);
                }
            }, new Action() {
                @Override
                public final void apply(View view2, int i) {
                    NftTransactionDetailActivity.1.this.lambda$onNoDoubleClick$1(view2, i);
                }
            });
        }

        public void lambda$onNoDoubleClick$0(View view, int i) {
            AnalyticsHelper.logEvent(AnalyticsHelper.TokenDetailsPage.CLICK_NFT_TRANSACTION_DETAIL_PAGE_SHARE_LINK);
            UrlParamBean walletAddress = new UrlParamBean().setHash(transferOutput.hash).setLanCode(getResources().getConfiguration().locale.getLanguage()).setWalletAddress(WalletUtils.getSelectedWallet().getAddress());
            if (transferOutput.getRevert() == 1 || (!TransactionMessage.TYPE_SUCCESS.equals(transferOutput.getContract_ret()) && !StringTronUtil.isEmpty(transferOutput.getContract_ret()))) {
                binding.rlState.setBackgroundResource(R.mipmap.transaction_xxx);
                walletAddress.setDecimals(String.valueOf(token.precision)).setTokenName(token.getShortName());
            } else {
                walletAddress.setEventType(transferOutput.getEvent_type());
            }
            String buildUrl = walletAddress.buildUrl();
            LogUtils.w("TransactionDetailActivity", "onNoDoubleClick: url = " + buildUrl);
            ShareHelper.getInstance().shareUrl(NftTransactionDetailActivity.this, buildUrl);
        }

        public void lambda$onNoDoubleClick$1(View view, int i) {
            AnalyticsHelper.logEvent(AnalyticsHelper.TokenDetailsPage.CLICK_NFT_TRANSACTION_DETAIL_PAGE_SHARE_IMAGE);
            prepareShareImage();
        }
    }

    @Override
    public void onRefreshButtonClick() {
        super.onRefreshButtonClick();
    }

    @Override
    protected void processData() {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.nft.nfttransactiondetail.NftTransactionDetailActivity.processData():void");
    }

    public void mappingId() {
        this.tvSaWalletName = this.binding.tvWalletNameSa;
        this.tvRaWalletName = this.binding.tvWalletNameRa;
        this.ivState = this.binding.rlState;
        this.transactionMenuLayout = this.binding.rlTransferMenu;
        this.transactionFeeView = this.binding.resourceInfoView2;
        this.ivScamSendTag = this.binding.scamTagSa;
        this.ivScamTag = this.binding.scamTag;
    }

    public void toUi() {
        String str;
        String sb;
        String contract_ret = this.transferOutput.getContract_ret();
        String event_type = this.transferOutput.getEvent_type();
        int revert = this.transferOutput.getRevert();
        if (CustomTokenStatus.APPROVE_EVENT.equals(event_type)) {
            this.binding.tvTitle.setText(getResources().getString(R.string.contract_approved));
        } else if (this.transferOutput.getMark().equals(HelpFormatter.DEFAULT_OPT_PREFIX)) {
            this.binding.tvTitle.setText(R.string.nft_send);
        } else {
            this.binding.tvTitle.setText(R.string.nft_received);
        }
        if (!StringTronUtil.isNullOrEmpty(this.token.getLogoUrl()) && !this.token.getLogoUrl().equals(TRONSCAN_DEFAULT_IMAGE_URL)) {
            this.contentBinding.ivLogo1.setImageURI(this.token.getLogoUrl());
        }
        TextView textView = this.contentBinding.name;
        if (StringTronUtil.isNullOrEmpty(this.token.shortName)) {
            sb = "";
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.token.shortName);
            if (StringTronUtil.isNullOrEmpty(this.token.getName())) {
                str = "";
            } else {
                str = "(" + this.token.getName() + ")";
            }
            sb2.append(str);
            sb = sb2.toString();
        }
        textView.setText(sb);
        if (this.transferOutput.getRevert() == 1 || (!TransactionMessage.TYPE_SUCCESS.equals(contract_ret) && !StringTronUtil.isEmpty(contract_ret))) {
            this.ivState.setBackgroundResource(R.mipmap.transaction_xxx);
        } else {
            this.ivState.setBackgroundResource(R.mipmap.green_receive);
        }
        if (!StringTronUtil.isNullOrEmpty(this.transferOutput.getContract_type())) {
            event_type.hashCode();
            if (event_type.equals(CustomTokenStatus.APPROVE_EVENT)) {
                this.binding.tvContractType.setText(R.string.TRC721_Transfer_approve);
            } else if (event_type.equals("Transfer")) {
                this.binding.tvContractType.setText(R.string.TRC721_Transfer_t);
            }
        } else {
            this.binding.tvContractType.setText(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
        }
        if (CustomTokenStatus.APPROVE_EVENT.equals(event_type)) {
            this.binding.ivContractType.setVisibility(View.VISIBLE);
        } else {
            this.binding.ivContractType.setVisibility(View.GONE);
        }
        if (revert == 1 || (!TransactionMessage.TYPE_SUCCESS.equals(contract_ret) && !StringTronUtil.isEmpty(contract_ret))) {
            this.binding.llFailReason.setVisibility(View.VISIBLE);
            if (revert == 1) {
                this.binding.tvReasonFailure.setText(ContractReturnMessage.getMessageResource(TransactionMessage.TYPE_REVERT));
            } else {
                this.binding.tvReasonFailure.setText(ContractReturnMessage.getMessageResource(contract_ret));
            }
        }
        this.binding.tvTn.setText(this.hash);
        if (this.refBlockNum == 0 && this.transferOutput.getBlock() != 0) {
            if (WalletUtils.getSelectedWallet().isShieldedWallet()) {
                this.binding.tvBn.setText(this.transferOutput.getBlock() + "");
            } else {
                this.binding.tvBn.setText(this.transferOutput.getBlock() + "");
            }
        } else {
            this.binding.tvBn.setText(this.refBlockNum + "");
        }
        if (this.transferOutput.from == null) {
            this.binding.tvSa.setText(this.address);
            this.binding.tvRa.setText(this.transferOutput.contract_address);
        }
        if (this.binding.tvSa.getText() == null || (this.binding.tvSa.getText() != null && this.binding.tvSa.getText().equals(HelpFormatter.DEFAULT_OPT_PREFIX))) {
            this.binding.ivSaCopy.setVisibility(View.GONE);
            this.hasSa = false;
        }
        if (this.binding.tvRa.getText() == null || (this.binding.tvRa.getText() != null && this.binding.tvRa.getText().equals(HelpFormatter.DEFAULT_OPT_PREFIX))) {
            this.binding.ivRaCopy.setVisibility(View.GONE);
            this.hasRa = false;
        }
        if (this.transferOutput.block_timestamp == 0 && this.transactionInfo != null) {
            this.binding.tvTime.setText(DateUtils.diffLanguageDate(this.transferOutput.block_timestamp == 0 ? this.transactionInfo.getTimestamp() : this.transferOutput.block_timestamp));
        } else {
            this.binding.tvTime.setText(DateUtils.diffLanguageDate(this.transferOutput.block_timestamp));
        }
        this.url = getUrl();
        this.binding.ivCode.setImageBitmap(WalletUtils.strToQRHasLogo(this.url, UIUtils.dip2px(120.0f), UIUtils.dip2px(120.0f), ((BitmapDrawable) getResources().getDrawable(R.mipmap.iv_transcan)).getBitmap()));
        TouchDelegateUtils.expandViewTouchDelegate(this.binding.ivContractType, UIUtils.dip2px(20.0f), UIUtils.dip2px(20.0f), UIUtils.dip2px(20.0f), UIUtils.dip2px(20.0f));
        TouchDelegateUtils.expandViewTouchDelegate(this.binding.llCodeCopy, UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f));
        checkAddressScam(this.transferOutput.from, this.ivScamSendTag);
        checkAddressScam(this.transferOutput.to, this.ivScamTag);
    }

    public String getUrl() {
        String str;
        if (SpAPI.THIS.getCurrentChain() != null && SpAPI.THIS.getCurrentChain().isMainChain) {
            str = TronConfig.TRONSCANHOST_MAINCHAIN + this.hash;
        } else {
            str = TronConfig.TRONSCANHOST_DAPPCHAIN + this.hash;
        }
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return str + "?lang=zh";
        }
        return str + "?lang=en";
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }

    public void getBlock() {
        runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$getBlock$2();
            }
        });
    }

    public void lambda$getBlock$2() {
        this.transactionInfoById = TronAPI.getTransactionInfoById(this.hash);
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$getBlock$1();
            }
        });
    }

    public void lambda$getBlock$1() {
        dismissLoadingPage();
        Protocol.TransactionInfo transactionInfo = this.transactionInfoById;
        if (transactionInfo != null) {
            this.refBlockNum = transactionInfo.getBlockNumber();
        }
        toUi();
    }

    public void setClick() {
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                switch (view.getId()) {
                    case R.id.iv_contract_type:
                        NftTransactionDetailActivity nftTransactionDetailActivity = NftTransactionDetailActivity.this;
                        nftTransactionDetailActivity.showPopZero(R.string.approve_text, nftTransactionDetailActivity.binding.ivContractType, true);
                        return;
                    case R.id.iv_ra_copy:
                        if (hasRa) {
                            UIUtils.copy(binding.tvRa.getTag().toString());
                            NftTransactionDetailActivity nftTransactionDetailActivity2 = NftTransactionDetailActivity.this;
                            nftTransactionDetailActivity2.toast(nftTransactionDetailActivity2.getString(R.string.copy_susscess));
                            return;
                        }
                        return;
                    case R.id.iv_sa_copy:
                        if (hasSa) {
                            UIUtils.copy(binding.tvSa.getTag().toString());
                            NftTransactionDetailActivity nftTransactionDetailActivity3 = NftTransactionDetailActivity.this;
                            nftTransactionDetailActivity3.toast(nftTransactionDetailActivity3.getString(R.string.copy_susscess));
                            return;
                        }
                        return;
                    case R.id.ll_code_copy:
                        UIUtils.copy(getUrl());
                        NftTransactionDetailActivity nftTransactionDetailActivity4 = NftTransactionDetailActivity.this;
                        nftTransactionDetailActivity4.toast(getUrl() + " " + getString(R.string.copy_susscess));
                        AnalyticsHelper.logEvent(AnalyticsHelper.TokenDetailsPage.CLICK_NFT_TRANSACTION_DETAIL_PAGE_COPY_LINK);
                        return;
                    case R.id.ll_hash:
                        UIUtils.copy(binding.tvTn.getText().toString());
                        NftTransactionDetailActivity nftTransactionDetailActivity5 = NftTransactionDetailActivity.this;
                        nftTransactionDetailActivity5.toast(nftTransactionDetailActivity5.getString(R.string.copy_susscess));
                        return;
                    case R.id.tv_bn:
                        UIUtils.copy(binding.tvBn.getText().toString());
                        NftTransactionDetailActivity nftTransactionDetailActivity6 = NftTransactionDetailActivity.this;
                        nftTransactionDetailActivity6.toast(nftTransactionDetailActivity6.getString(R.string.copy_susscess));
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
        this.binding.ivRightIcon.setOnClickListener(noDoubleClickListener2);
        this.binding.rlTransferMenu.setOnClickListener(noDoubleClickListener2);
        this.binding.tvBn.setOnClickListener(noDoubleClickListener2);
        this.binding.ivContractType.setOnClickListener(noDoubleClickListener2);
        this.binding.ivSaCopy.setOnClickListener(noDoubleClickListener2);
        this.binding.ivRaCopy.setOnClickListener(noDoubleClickListener2);
        this.binding.tvSa.setOnClickListener(noDoubleClickListener2);
        this.binding.tvRa.setOnClickListener(noDoubleClickListener2);
        this.binding.llHash.setOnClickListener(noDoubleClickListener2);
        this.binding.llCodeCopy.setOnClickListener(noDoubleClickListener2);
    }

    public void clickTvSa() {
        if (this.hasSa) {
            UIUtils.copy(this.binding.tvSa.getTag().toString());
            toast(getString(R.string.copy_susscess));
        }
        if (StringTronUtil.equals(WalletUtils.getSelectedWallet().getAddress(), this.binding.tvSa.getTag().toString())) {
            return;
        }
        AddressBottomView.showPop(this, this.binding.tvSa.getTag().toString(), new Action() {
            @Override
            public final void apply(View view, int i) {
                lambda$clickTvSa$4(view, i);
            }
        }, new Action() {
            @Override
            public final void apply(View view, int i) {
                lambda$clickTvSa$5(view, i);
            }
        });
    }

    public void lambda$clickTvSa$4(View view, int i) {
        if (AddressController.getInstance(this).isAddressCountMax()) {
            runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$clickTvSa$3();
                }
            });
        } else {
            AddToAddressBookPopView.showUp(this, (String) this.binding.tvSa.getTag(), getAddressBookCallback(this.binding.tvSa, this.tvSaWalletName));
        }
    }

    public void lambda$clickTvSa$3() {
        toast(getString(R.string.address_no_add));
    }

    public void lambda$clickTvSa$5(View view, int i) {
        CommonWebActivityV3.start(this.mContext, getAccountUrl((String) this.binding.tvSa.getTag()), this.mContext.getString(isAddressSC((String) this.binding.tvSa.getTag()) ? R.string.sc_details : R.string.account_details), 1, true);
    }

    public void clickTvRa() {
        if (this.hasRa) {
            UIUtils.copy(this.binding.tvRa.getTag().toString());
            toast(getString(R.string.copy_susscess));
        }
        if (StringTronUtil.equals(WalletUtils.getSelectedWallet().getAddress(), this.binding.tvRa.getTag().toString())) {
            return;
        }
        AddressBottomView.showPop(this, this.binding.tvRa.getTag().toString(), new Action() {
            @Override
            public final void apply(View view, int i) {
                lambda$clickTvRa$7(view, i);
            }
        }, new Action() {
            @Override
            public final void apply(View view, int i) {
                lambda$clickTvRa$8(view, i);
            }
        });
    }

    public void lambda$clickTvRa$7(View view, int i) {
        if (AddressController.getInstance(this).isAddressCountMax()) {
            runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$clickTvRa$6();
                }
            });
        } else {
            AddToAddressBookPopView.showUp(this, (String) this.binding.tvRa.getTag(), getAddressBookCallback(this.binding.tvRa, this.tvRaWalletName));
        }
    }

    public void lambda$clickTvRa$6() {
        toast(getString(R.string.address_no_add));
    }

    public void lambda$clickTvRa$8(View view, int i) {
        CommonWebActivityV3.start(this.mContext, getAccountUrl((String) this.binding.tvRa.getTag()), this.mContext.getString(isAddressSC((String) this.binding.tvRa.getTag()) ? R.string.sc_details : R.string.account_details), 1, true);
    }

    private void showAsTop(PopupWindow popupWindow, View view, View view2) {
        view.measure(0, 0);
        int measuredHeight = view.getMeasuredHeight();
        view.getMeasuredWidth();
        int[] iArr = new int[2];
        view2.getLocationOnScreen(iArr);
        popupWindow.showAtLocation(view2, 0, iArr[0], (iArr[1] - measuredHeight) - 20);
    }

    public void getFee(final String str) {
        runOnThreeThread(new OnBackground() {
            @Override
            public void doOnBackground() {
                ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getTrasactionInfo(str).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<TransactionInfoBean>() {
                    @Override
                    public void onFailure(String str2, String str3) {
                    }

                    @Override
                    public void onResponse(String str2, TransactionInfoBean transactionInfoBean) {
                        transactionInfo = transactionInfoBean;
                        transferOutput.setContract_type(TransactionTypeTextProvider.convert(transactionInfoBean.getContractType()));
                        if (mIsFromMessageCenter) {
                            if ((mMessage.getRevert() == 1) != transactionInfoBean.isRevert()) {
                                transferOutput.setContract_ret(transactionInfoBean.getContractRet());
                                transferOutput.setRevert(transactionInfoBean.isRevert() ? 1 : 0);
                                mMessage.setRevert(transactionInfoBean.isRevert() ? 1 : 0);
                                mMessage.setContract_ret(transactionInfoBean.getContractRet());
                                if (rxManager != null) {
                                    rxManager.post(Event.MSG_CENTER_STATUS_UPDATE, new TransactionMessageStatusBean(mMessage, mPosition));
                                }
                            }
                        }
                        toUi();
                        if (transferOutput.getContract_type() != null && transferOutput.getContract_type().equals("ShieldedTransferContract")) {
                            TextView textView = binding.tvResource;
                            textView.setText(("0\t" + getString(R.string.bandwidth)) + "\t\t" + ("0\t" + getString(R.string.energy)));
                            String str3 = transferOutput.memo;
                            if (str3 != null && !str3.equals("")) {
                                binding.llNote.setVisibility(View.VISIBLE);
                                binding.tvNote.setVisibility(View.VISIBLE);
                                binding.tvNote.setText(str3);
                            }
                        } else {
                            TextView textView2 = binding.tvResource;
                            textView2.setText((transactionInfoBean.getCost().getNet_usage() + "\t" + getString(R.string.bandwidth)) + "\t\t\t" + (transactionInfoBean.getCost().getEnergy_usage() + "\t" + getString(R.string.energy)));
                            String data = transactionInfoBean.getData();
                            if (data != null && !data.equals("")) {
                                binding.llNote.setVisibility(View.VISIBLE);
                                binding.tvNote.setVisibility(View.VISIBLE);
                                binding.tvNote.setText(data);
                            }
                        }
                        transactionFeeView.bindData(transactionInfoBean);
                        transactionFeeView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onSubscribe(Disposable disposable) {
                        rxManager.add(disposable);
                    }
                }, ""));
            }
        });
    }

    public void getCollectionInfo(final String str, final String str2, final String str3) {
        runOnThreeThread(new OnBackground() {
            @Override
            public void doOnBackground() {
                ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getCollectionInfo(str, str2, str3).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<NftItemInfoOutput>() {
                    @Override
                    public void onFailure(String str4, String str5) {
                    }

                    @Override
                    public void onResponse(String str4, NftItemInfoOutput nftItemInfoOutput) {
                        updateCollectionInfoUI(nftItemInfoOutput.getData());
                    }

                    @Override
                    public void onSubscribe(Disposable disposable) {
                        rxManager.add(disposable);
                    }
                }, "getCollectionInfo"));
            }
        });
    }

    public void updateCollectionInfoUI(NftItemInfo nftItemInfo) {
        if (nftItemInfo != null) {
            this.contentBinding.ivLogo2.setImageURI(nftItemInfo.getLogoUrl());
            TextView textView = this.contentBinding.assetId;
            textView.setText("#" + nftItemInfo.getAssetId());
            if (StringTronUtil.isNullOrEmpty(nftItemInfo.getName())) {
                this.contentBinding.nftName.setVisibility(View.GONE);
                return;
            }
            this.contentBinding.nftName.setVisibility(View.VISIBLE);
            this.contentBinding.nftName.setText(nftItemInfo.getName());
            return;
        }
        TextView textView2 = this.contentBinding.nftName;
        textView2.setText("#" + this.token.getId());
        this.contentBinding.nftName.setVisibility(View.GONE);
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

    private void cloneViewContent(View view, ViewGroup viewGroup) {
        View findViewById;
        View findViewById2;
        int id = viewGroup.getId();
        if (id > 0 && (findViewById2 = view.findViewById(id)) != null) {
            findViewById2.setVisibility(viewGroup.getVisibility());
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
                            ((TextView) findViewById).setText(((TextView) childAt).getText());
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
            View inflate = LayoutInflater.from(this).inflate(R.layout.activity_nft_transaction_detail, (ViewGroup) null);
            this.copyView = inflate;
            ((ViewGroup) findViewById(16908290)).addView(inflate, 0);
        }
        View findViewById = this.copyView.findViewById(R.id.ll_scroll);
        cloneViewContent(findViewById, this.binding.llContent);
        ((TransactionFeeResourceView) this.copyView.findViewById(R.id.resource_info_view2)).setViewForShare(this.transactionInfo);
        ((TextView) this.copyView.findViewById(R.id.tv_sa)).setSingleLine(false);
        this.copyView.findViewById(R.id.iv_sa_copy).setVisibility(View.GONE);
        ((TextView) this.copyView.findViewById(R.id.tv_ra)).setSingleLine(false);
        this.copyView.findViewById(R.id.iv_ra_copy).setVisibility(View.GONE);
        this.copyView.findViewById(R.id.iv_contract_type).setVisibility(View.GONE);
        ((TextView) this.copyView.findViewById(R.id.tv_tn)).setSingleLine(false);
        this.copyView.findViewById(R.id.iv_hash_copy).setVisibility(View.GONE);
        this.copyView.findViewById(R.id.ll_code_copy).setVisibility(View.GONE);
        this.copyView.post(new fun8(findViewById, this.copyView.findViewById(R.id.ll_content)));
    }

    public class fun8 implements Runnable {
        final View val$content;
        final View val$scrollView;

        fun8(View view, View view2) {
            this.val$scrollView = view;
            this.val$content = view2;
        }

        @Override
        public void run() {
            ShareHelper.getInstance().shareImage(NftTransactionDetailActivity.this, this.val$scrollView, this.val$content, new io.reactivex.functions.Action() {
                @Override
                public final void run() {
                    NftTransactionDetailActivity.8.this.lambda$run$0();
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
            requestPermissionsSuccess();
        }
    }

    public boolean supportShareUrl(TransactionHistoryBean transactionHistoryBean) {
        if (SpAPI.THIS.getCurrentChain() == null || SpAPI.THIS.getCurrentChain().isMainChain) {
            TokenBean tokenBean = this.token;
            if ((tokenBean != null && tokenBean.getTokenStatus() != 0) || CustomTokenStatus.APPROVE_EVENT.equals(transactionHistoryBean.getEvent_type()) || this.transferOutput.getRevert() == 1) {
                return false;
            }
            if (TransactionMessage.TYPE_SUCCESS.equals(transactionHistoryBean.getContract_ret()) || StringTronUtil.isEmpty(transactionHistoryBean.getContract_ret())) {
                String contract_type = transactionHistoryBean.getContract_type();
                return TextUtils.equals(contract_type, Protocol.Transaction.Contract.ContractType.TransferContract.name()) || TextUtils.equals(contract_type, Protocol.Transaction.Contract.ContractType.TransferAssetContract.name()) || (TextUtils.equals(contract_type, Protocol.Transaction.Contract.ContractType.TriggerSmartContract.name()) && supportShareTrc721(transactionHistoryBean.getEvent_type()));
            }
            return false;
        }
        return false;
    }

    private boolean supportShareTrc721(String str) {
        return TextUtils.equals(str, "Transfer") || TextUtils.equals(str, CustomTokenStatus.APPROVE_EVENT);
    }

    private void setAddress(TextView textView, TextView textView2, String str, NameAddressExtraBean nameAddressExtraBean) {
        if (StringTronUtil.equals(WalletUtils.getSelectedWallet().getAddress(), str)) {
            textView.setTextColor(getResources().getColor(R.color.black_23));
        } else {
            textView.setTextColor(getResources().getColor(R.color.blue_3c));
        }
        if (nameAddressExtraBean != null) {
            textView2.setText(nameAddressExtraBean.getName());
            textView2.setVisibility(View.VISIBLE);
            textView.setText("(" + str + ")");
        } else {
            textView2.setVisibility(View.GONE);
            textView.setText(str);
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

    @Override
    public void updateSaAddressByName(NameAddressExtraBean nameAddressExtraBean) {
        setAddress(this.binding.tvSa, this.tvSaWalletName, this.transferOutput.from, nameAddressExtraBean);
    }

    @Override
    public void updateRaAddressByName(NameAddressExtraBean nameAddressExtraBean) {
        setAddress(this.binding.tvRa, this.tvRaWalletName, this.transferOutput.to, nameAddressExtraBean);
    }

    AddToAddressBookPopView.AddressBookChangeCallback getAddressBookCallback(final TextView textView, final TextView textView2) {
        return new AddToAddressBookPopView.AddressBookChangeCallback() {
            @Override
            public final void onAddressBookChanged(String str, String str2, String str3) {
                lambda$getAddressBookCallback$10(textView, textView2, str, str2, str3);
            }
        };
    }

    public void lambda$getAddressBookCallback$10(TextView textView, TextView textView2, String str, String str2, String str3) {
        toast(getString(R.string.saved_successfully));
        NameAddressExtraBean nameAddressExtraBean = new NameAddressExtraBean();
        nameAddressExtraBean.setAddress(str);
        nameAddressExtraBean.setExtra(str3);
        nameAddressExtraBean.setName(str2);
        AddressMapUtils.getInstance().addANameAddressExtraBean(str, nameAddressExtraBean);
        setAddress(textView, textView2, str, nameAddressExtraBean);
    }

    private String getAccountUrl(String str) {
        String accountUrl = IRequest.getAccountUrl(str);
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return accountUrl + "?lang=zh";
        }
        return accountUrl + "?lang=en";
    }

    private boolean isAddressSC(String str) {
        TransactionInfoBean transactionInfoBean = this.transactionInfo;
        return (transactionInfoBean == null || transactionInfoBean.getContract_map() == null || this.transactionInfo.getContract_map().get(str) == null || !this.transactionInfo.getContract_map().get(str).booleanValue()) ? false : true;
    }

    private void checkAddressScam(String str, final View view) {
        if (StringTronUtil.isNullOrEmpty(str)) {
            return;
        }
        AddressManager.checkSingleAddressScam(str).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$checkAddressScam$11(view, (RiskAccountOutput) obj);
            }
        });
    }

    public void lambda$checkAddressScam$11(View view, RiskAccountOutput riskAccountOutput) throws Exception {
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
}
