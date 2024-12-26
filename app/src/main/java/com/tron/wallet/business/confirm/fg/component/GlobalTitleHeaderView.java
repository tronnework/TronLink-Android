package com.tron.wallet.business.confirm.fg.component;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lxj.xpopup.util.XPopupUtils;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.AssetsConfig;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.fg.BaseConfirmFragment;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.DappDetailParam;
import com.tron.wallet.business.confirm.parambuilder.bean.NFTParam;
import com.tron.wallet.business.confirm.parambuilder.bean.TransferParam;
import com.tron.wallet.business.stakev2.managementv2.ResourcesBean;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.messagesign.DappLocalActivity;
import com.tron.wallet.common.bean.ConfirmExtraTitle;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.DashUnderLineTextView;
import com.tron.wallet.common.components.ItemWarningTagView;
import com.tron.wallet.common.components.MultiLineTextPopupView;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NumUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TokenItemUtil;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.LayoutGlobalTitleHeaderBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.NetUtil;
import com.tronlinkpro.wallet.R;
import org.tron.api.GrpcAPI;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class GlobalTitleHeaderView extends FrameLayout implements BaseConfirmFragment.AccountCallback {
    public RelativeLayout approveLayout;
    private LayoutGlobalTitleHeaderBinding binding;
    DashUnderLineTextView dashLine;
    public LinearLayout ivClose;
    public SimpleDraweeView ivIcon;
    ImageView ivOfficialImage;
    SimpleDraweeView ivSubTitleIcon;
    ImageView ivTransferImage;
    private BaseParam param;
    ItemWarningTagView rlScam;
    public RelativeLayout rlTopTwo;
    TextView tvChainName;
    TextView tvConfirmTitle;
    TextView tvErrorHint;
    TextView tvInfoSubtitle;
    TextView tvInfoSubtitleDetail;
    TextView tvInfoTitle;
    TextView tvScamNotice;
    TextView tvWalletName;

    public GlobalTitleHeaderView(Context context) {
        this(context, null);
    }

    public GlobalTitleHeaderView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GlobalTitleHeaderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View inflate = View.inflate(context, R.layout.layout_global_title_header, null);
        addView(inflate, new FrameLayout.LayoutParams(-1, -1));
        mappingId(inflate);
        setClick();
        TouchDelegateUtils.expandViewTouchDelegate(this.tvWalletName, 20);
        this.dashLine.setEnableUnderDashLine(true);
    }

    public void mappingId(View view) {
        LayoutGlobalTitleHeaderBinding bind = LayoutGlobalTitleHeaderBinding.bind(view);
        this.binding = bind;
        this.tvWalletName = bind.tvWalletName;
        this.tvChainName = this.binding.tvChainName;
        this.ivIcon = this.binding.ivIcon;
        this.ivTransferImage = this.binding.ivIconTag;
        this.ivOfficialImage = this.binding.ivOfficialImage;
        this.tvScamNotice = this.binding.tvScamNotice;
        this.tvConfirmTitle = this.binding.tvConfirmTitle;
        this.tvInfoTitle = this.binding.tvInfoTitle;
        this.tvErrorHint = this.binding.errorHint;
        this.ivSubTitleIcon = this.binding.ivSubTitleIcon;
        this.tvInfoSubtitle = this.binding.tvInfoSubtitle;
        this.tvInfoSubtitleDetail = this.binding.tvInfoSubtitleDetail;
        this.dashLine = this.binding.dashLine;
        this.rlScam = this.binding.rlScam;
        this.approveLayout = this.binding.approveLayout;
        this.ivClose = this.binding.ivClose;
    }

    public void bindData(BaseParam baseParam) {
        if (baseParam == null) {
            return;
        }
        this.param = baseParam;
        this.tvChainName.setText(NetUtil.getNetName());
        ConfirmExtraTitle confirmExtraTitle = baseParam.getConfirmExtraTitle();
        if (confirmExtraTitle != null && confirmExtraTitle.getDesTitle() != null) {
            this.tvConfirmTitle.setText(confirmExtraTitle.getDesTitle());
        }
        InfoTitle infoTitle = baseParam.getInfoTitle();
        if (infoTitle != null) {
            this.tvInfoTitle.setText(infoTitle.getTitle());
            if (TextUtils.isEmpty(infoTitle.getSubTitle())) {
                this.tvInfoSubtitle.setVisibility(View.GONE);
            } else {
                this.tvInfoSubtitle.setText(infoTitle.getSubTitle());
                if (!TextUtils.isEmpty(infoTitle.getSubTitleDetail())) {
                    this.tvInfoSubtitleDetail.setVisibility(View.VISIBLE);
                    this.tvInfoSubtitleDetail.setText(infoTitle.getSubTitleDetail());
                }
            }
        }
        String headerHint = baseParam.getHeaderHint();
        if (!StringTronUtil.isEmpty(headerHint)) {
            this.tvErrorHint.setText(headerHint);
            this.tvErrorHint.setVisibility(View.VISIBLE);
        } else {
            this.tvErrorHint.setVisibility(View.GONE);
        }
        try {
            String owner = TransactionUtils.getOwner(Protocol.Transaction.parseFrom(baseParam.getTransactionBean().getBytes().get(0)));
            String nameByAddress = Utils.getNameByAddress(owner, false);
            if (TextUtils.isEmpty(nameByAddress)) {
                nameByAddress = StringTronUtil.indentAddress(owner);
            }
            this.tvWalletName.setText(nameByAddress);
        } catch (Exception e) {
            LogUtils.e(e);
            Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
            if (selectedPublicWallet != null && !TextUtils.isEmpty(selectedPublicWallet.getWalletName())) {
                this.tvWalletName.setText(selectedPublicWallet.getWalletName());
            } else {
                this.tvWalletName.setText("");
            }
        }
        int iconResource = baseParam.getIconResource();
        if (iconResource > 0) {
            this.ivIcon.setImageResource(iconResource);
        }
        if (baseParam instanceof TransferParam) {
            TransferParam transferParam = (TransferParam) baseParam;
            TokenBean tokenBean = transferParam.getTokenBean();
            GenericDraweeHierarchy hierarchy = this.ivIcon.getHierarchy();
            hierarchy.setFailureImage(AssetsConfig.getTokenDefaultLogoIconId(tokenBean));
            hierarchy.setPlaceholderImage(AssetsConfig.getTokenDefaultLogoIconId(tokenBean));
            if (tokenBean != null && tokenBean.isShield()) {
                this.ivIcon.setImageResource(R.mipmap.trz_icon);
            }
            this.ivTransferImage.setVisibility(View.VISIBLE);
            TokenBean tokenBean2 = transferParam.getTokenBean();
            setScamStatus(tokenBean);
            if (tokenBean2 != null && tokenBean2.isOfficial == 1) {
                this.ivOfficialImage.setVisibility(View.VISIBLE);
            } else {
                this.ivOfficialImage.setVisibility(View.GONE);
            }
        } else if (baseParam instanceof NFTParam) {
            NFTParam nFTParam = (NFTParam) baseParam;
            TokenBean tokenBean3 = nFTParam.getTokenBean();
            GenericDraweeHierarchy hierarchy2 = this.ivIcon.getHierarchy();
            hierarchy2.setFailureImage(AssetsConfig.getTokenDefaultLogoIconId(tokenBean3));
            hierarchy2.setPlaceholderImage(AssetsConfig.getTokenDefaultLogoIconId(tokenBean3));
            setScamStatus(tokenBean3);
            this.ivTransferImage.setVisibility(View.VISIBLE);
            this.ivIcon.setImageURI(nFTParam.getNftTokenImage());
            TextView textView = this.tvInfoTitle;
            textView.setText(nFTParam.getTokenShortName() + "  " + getContext().getString(infoTitle.getTitle()));
            this.ivSubTitleIcon.setVisibility(View.VISIBLE);
            this.ivSubTitleIcon.setImageURI(nFTParam.getNftImage());
            this.ivOfficialImage.setVisibility(View.GONE);
        } else if (baseParam instanceof DappDetailParam) {
            DappDetailParam dappDetailParam = (DappDetailParam) baseParam;
            TokenBean tokenBean4 = dappDetailParam.getTokenBean();
            GenericDraweeHierarchy hierarchy3 = this.ivIcon.getHierarchy();
            hierarchy3.setFailureImage(AssetsConfig.getTokenDefaultLogoIconId(tokenBean4));
            hierarchy3.setPlaceholderImage(AssetsConfig.getTokenDefaultLogoIconId(tokenBean4));
            setScamStatus(tokenBean4);
            if (tokenBean4 != null && tokenBean4.isOfficial == 1) {
                int tokenActionType = dappDetailParam.getTokenActionType();
                if (tokenActionType == 0 || tokenActionType == 2) {
                    this.ivOfficialImage.setVisibility(View.VISIBLE);
                }
            } else {
                this.ivOfficialImage.setVisibility(View.GONE);
            }
            if (dappDetailParam.isTransfer()) {
                this.ivTransferImage.setVisibility(View.VISIBLE);
            } else {
                this.ivTransferImage.setVisibility(View.GONE);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.ivIcon.getLayoutParams();
                layoutParams.leftMargin = 0;
                this.ivIcon.setLayoutParams(layoutParams);
            }
            if (dappDetailParam.getTokenActionType() == 2) {
                if (dappDetailParam.getTokenBean() != null && !StringTronUtil.isNullOrEmpty(dappDetailParam.getTokenBean().getShortName())) {
                    String shortName = dappDetailParam.getTokenBean().getShortName();
                    TextView textView2 = this.tvInfoTitle;
                    textView2.setText(shortName + "   " + getContext().getString(infoTitle.getTitle()));
                }
                this.ivSubTitleIcon.setVisibility(View.VISIBLE);
            }
        } else {
            GenericDraweeHierarchy hierarchy4 = this.ivIcon.getHierarchy();
            hierarchy4.setFailureImage(R.mipmap.ic_token_default);
            hierarchy4.setPlaceholderImage(R.mipmap.ic_token_default);
            this.ivTransferImage.setVisibility(View.GONE);
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.ivIcon.getLayoutParams();
            layoutParams2.leftMargin = 0;
            this.ivIcon.setLayoutParams(layoutParams2);
        }
    }

    private void setScamStatus(TokenBean tokenBean) {
        TokenItemUtil.initScamToDetailView(getContext(), this.rlScam, tokenBean);
        this.rlScam.setLayoutDrawable(getContext().getDrawable(R.color.transparent));
        this.rlScam.setLayoutPadding(UIUtils.dip2px(15.0f), UIUtils.dip2px(6.0f), UIUtils.dip2px(15.0f), 0);
    }

    public void setClick() {
        this.binding.ivClose.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                Context context = getContext();
                if (context instanceof ConfirmTransactionNewActivity) {
                    ((ConfirmTransactionNewActivity) context).backUp(1);
                } else if (context instanceof BaseActivity) {
                    ((BaseActivity) context).exit();
                } else if (context instanceof DappLocalActivity) {
                    ((DappLocalActivity) context).exit();
                }
            }
        });
    }

    @Override
    public void onRefreshAccountComplete(boolean z, Protocol.Transaction transaction, Pair<Protocol.Account, GrpcAPI.AccountResourceMessage> pair) {
        if (!z || pair == null || pair.first == null || pair.second == null) {
            return;
        }
        this.tvWalletName.setEnabled(true);
        final ResourcesBean build = ResourcesBean.build((Protocol.Account) pair.first, (GrpcAPI.AccountResourceMessage) pair.second);
        final long balance = ((Protocol.Account) pair.first).getBalance();
        this.tvWalletName.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                Context context = view.getContext();
                MultiLineTextPopupView.Builder requiredWidth = new MultiLineTextPopupView.Builder().setAnchor(view).setItemPadding(UIUtils.dip2px(5.0f)).setRequiredWidth(XPopupUtils.getScreenWidth(context) - (UIUtils.dip2px(20.0f) * 2));
                requiredWidth.addKeyValue(context.getString(R.string.confirm_header_account_balance) + ":", "");
                if (balance >= 0) {
                    requiredWidth.addKeyValue(context.getString(R.string.confirm_header_trx_balance), StringTronUtil.formatNumber6Truncate(BigDecimalUtils.div_(Long.valueOf(balance), Double.valueOf(Math.pow(10.0d, 6.0d)))));
                }
                if (build.getBandwidthUsable() >= 0) {
                    requiredWidth.addKeyValue(context.getString(R.string.bandwidth), NumUtils.numFormatToK(build.getBandwidthUsable()));
                }
                if (build.getEnergyUsable() >= 0) {
                    requiredWidth.addKeyValue(context.getString(R.string.energy), NumUtils.numFormatToK(build.getEnergyUsable()));
                }
                requiredWidth.build(context).show();
                logEvent(AnalyticsHelper.TransactionConfirmPopup.CLICK_CONFIRM_PAGE_ACCOUNT_BALANCE);
            }
        });
    }

    public void logEvent(String str) {
        try {
            AnalyticsHelper.logEventFormat(str, Integer.valueOf(Protocol.Transaction.parseFrom(this.param.getTransactionBean().getBytes().get(0)).getRawData().getContract(0).getType().equals(Protocol.Transaction.Contract.ContractType.TriggerSmartContract) ? 2 : 1));
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void hideWalletNameLine() {
        this.dashLine.setVisibility(View.GONE);
    }
}
