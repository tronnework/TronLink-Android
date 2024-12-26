package com.tron.wallet.business.security.approvecheck.adapter;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.wallet.business.security.approvecheck.CancelApproveClickListener;
import com.tron.wallet.business.security.approvecheck.bean.ApproveListDatabeanOutput;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.common.components.MultiLineTextPopupView;
import com.tron.wallet.common.components.TokenLogoDraweeView;
import com.tron.wallet.common.utils.AddressMapUtils;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.DateUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.ToastUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.ItemApproveRiskProjectBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.cli.HelpFormatter;
import org.tron.walletserver.Wallet;
public class ApproveProjectItemAdapter extends BaseQuickAdapter<ApproveListDatabeanOutput.ApproveListDatabean.ProjectsBean.TokenListBean, BaseViewHolder> {
    private CancelApproveClickListener itemReClaimClickListener;
    private ApproveListDatabeanOutput.ApproveListDatabean.ProjectsBean projectBean;
    private Wallet wallet;

    public ApproveProjectItemAdapter(int i, List<ApproveListDatabeanOutput.ApproveListDatabean.ProjectsBean.TokenListBean> list, CancelApproveClickListener cancelApproveClickListener, ApproveListDatabeanOutput.ApproveListDatabean.ProjectsBean projectsBean) {
        super(i, list);
        this.itemReClaimClickListener = cancelApproveClickListener;
        this.projectBean = projectsBean;
        this.wallet = WalletUtils.getSelectedWallet();
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder, ApproveListDatabeanOutput.ApproveListDatabean.ProjectsBean.TokenListBean tokenListBean) {
        if (baseViewHolder instanceof ApprovedProjectDataHolder) {
            ((ApprovedProjectDataHolder) baseViewHolder).onBind(this.mContext, tokenListBean);
        }
    }

    @Override
    public BaseViewHolder onCreateDefViewHolder(ViewGroup viewGroup, int i) {
        return new ApprovedProjectDataHolder(getItemView(this.mLayoutResId, viewGroup));
    }

    public class ApprovedProjectDataHolder extends BaseViewHolder {
        private ItemApproveRiskProjectBinding binding;
        Button btnCancelAuth;
        ImageView ivAddressCopy;
        ImageView ivProjectNameTips;
        TokenLogoDraweeView tokenLogo;
        TextView tvAccountRiskAmount;
        TextView tvAccountRiskAmountLabel;
        TextView tvAddress;
        TextView tvAddressLabel;
        TextView tvAuthTime;
        TextView tvProjectName;
        TextView tvQuantity;

        public ApprovedProjectDataHolder(View view) {
            super(view);
            mappingId(view);
        }

        private void mappingId(View view) {
            ItemApproveRiskProjectBinding bind = ItemApproveRiskProjectBinding.bind(view);
            this.binding = bind;
            this.btnCancelAuth = bind.btnCancelAuthorization;
            this.tokenLogo = this.binding.tvProjectIcon;
            this.tvProjectName = this.binding.tvProjectName;
            this.tvAddress = this.binding.tvAccountAddress;
            this.tvAddressLabel = this.binding.tvAccountAddressLabel;
            this.ivAddressCopy = this.binding.icAccountAddressCopy;
            this.tvQuantity = this.binding.tvAuthorizationQuantity;
            this.tvAuthTime = this.binding.tvAuthorizationTime;
            this.tvAccountRiskAmountLabel = this.binding.tvAccountRiskAmountLabel;
            this.tvAccountRiskAmount = this.binding.tvAccountRiskAmount;
            this.ivProjectNameTips = this.binding.ivProjectNameTips;
        }

        public void onBind(final Context context, final ApproveListDatabeanOutput.ApproveListDatabean.ProjectsBean.TokenListBean tokenListBean) {
            this.btnCancelAuth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int tokenType;
                    String tokenSymbol;
                    String str;
                    String str2;
                    String str3;
                    String str4;
                    String str5;
                    String str6;
                    String str7;
                    if (tokenListBean.getTokenType() == 5) {
                        String assetId = tokenListBean.getAssetId();
                        String projectAddress = projectBean.getProjectAddress();
                        String approveAddressType = projectBean.getApproveAddressType();
                        String tokenAddress = tokenListBean.getTokenAddress();
                        tokenType = tokenListBean.getTokenType();
                        String tokenLogo = tokenListBean.getTokenLogo();
                        String tokenName = tokenListBean.getTokenName();
                        String assetId2 = tokenListBean.getAssetId();
                        tokenSymbol = tokenListBean.getTokenSymbol();
                        str = assetId;
                        str2 = projectAddress;
                        str4 = approveAddressType;
                        str5 = tokenAddress;
                        str6 = tokenLogo;
                        str7 = tokenName;
                        str3 = assetId2;
                    } else {
                        String projectAddress2 = projectBean.getProjectAddress();
                        String approveAddressType2 = projectBean.getApproveAddressType();
                        String tokenAddress2 = tokenListBean.getTokenAddress();
                        tokenType = tokenListBean.getTokenType();
                        String tokenLogo2 = tokenListBean.getTokenLogo();
                        String tokenName2 = tokenListBean.getTokenName();
                        tokenSymbol = tokenListBean.getTokenSymbol();
                        str = "0";
                        str2 = projectAddress2;
                        str3 = "";
                        str4 = approveAddressType2;
                        str5 = tokenAddress2;
                        str6 = tokenLogo2;
                        str7 = tokenName2;
                    }
                    String str8 = tokenSymbol;
                    if (itemReClaimClickListener != null) {
                        CancelApproveClickListener cancelApproveClickListener = itemReClaimClickListener;
                        cancelApproveClickListener.onCancelApproveClick(str, str2, str2, str4, str5, tokenType + "", str7, str8, str6, str3);
                    }
                }
            });
            this.tokenLogo.setVisibility(View.VISIBLE);
            if (StringTronUtil.isEmpty(tokenListBean.getTokenLogo())) {
                this.tokenLogo.setImageURI(tokenListBean.getTokenLogo());
            } else {
                this.tokenLogo.setCircularImage(tokenListBean.getTokenLogo());
            }
            if (!StringTronUtil.isEmpty(tokenListBean.getTokenName())) {
                this.tvProjectName.setText(tokenListBean.getTokenName());
                this.ivProjectNameTips.setVisibility(View.GONE);
            } else if (!StringTronUtil.isEmpty(tokenListBean.getTokenSymbol())) {
                this.tvProjectName.setText(tokenListBean.getTokenSymbol());
                this.ivProjectNameTips.setVisibility(View.GONE);
            } else {
                this.tvProjectName.setText(StringTronUtil.indentAddress(tokenListBean.getTokenAddress(), 6));
                this.ivProjectNameTips.setVisibility(View.VISIBLE);
            }
            this.tvProjectName.setTextColor(context.getResources().getColor(R.color.red_ec));
            this.tvAddress.setText(StringTronUtil.indentAddress(tokenListBean.getTokenAddress(), 6));
            this.tvAccountRiskAmountLabel.setVisibility(View.VISIBLE);
            this.tvAddressLabel.setVisibility(View.VISIBLE);
            this.tvAddress.setVisibility(View.VISIBLE);
            if (tokenListBean.getTokenType() == 5) {
                this.tvAccountRiskAmount.setVisibility(View.VISIBLE);
                this.tvAccountRiskAmountLabel.setVisibility(View.VISIBLE);
                this.tvAccountRiskAmount.setText(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
                TextView textView = this.tvQuantity;
                textView.setText("#" + tokenListBean.getAssetId() + " " + tokenListBean.getTokenSymbol());
            } else {
                this.tvAccountRiskAmount.setVisibility(View.VISIBLE);
                this.tvAccountRiskAmountLabel.setVisibility(View.VISIBLE);
                if (SpAPI.THIS.isUsdPrice()) {
                    if (BigDecimalUtils.between(tokenListBean.getUsdCount(), 0, Double.valueOf(0.001d))) {
                        this.tvAccountRiskAmount.setText("$<0.001");
                    } else {
                        TextView textView2 = this.tvAccountRiskAmount;
                        textView2.setText("$" + StringTronUtil.formatNumber3Truncate(new BigDecimal(tokenListBean.getUsdCount())));
                    }
                } else if (BigDecimalUtils.between(tokenListBean.getUsdCount(), 0, Double.valueOf(0.001d))) {
                    this.tvAccountRiskAmount.setText("$<0.001");
                } else {
                    TextView textView3 = this.tvAccountRiskAmount;
                    textView3.setText(((Object) Html.fromHtml("&yen;")) + StringTronUtil.formatNumber3Truncate(new BigDecimal(tokenListBean.getCnyCount())));
                }
                if (!tokenListBean.getUnlimited().booleanValue()) {
                    if (BigDecimalUtils.between(tokenListBean.getAmount(), 0, Double.valueOf(1.0E-6d))) {
                        this.tvQuantity.setText("<0.000001");
                    } else {
                        this.tvQuantity.setText(StringTronUtil.formatNumber6Truncate(new BigDecimal(tokenListBean.getAmount())));
                    }
                } else {
                    this.tvQuantity.setText(context.getString(R.string.unlimited));
                }
            }
            if (AddressMapUtils.getInstance().isContainsAddress(wallet.getAddress())) {
                this.tvAddress.setText(getDisPlayName(AddressMapUtils.getInstance().getNameAddress(wallet.getAddress())));
            } else {
                this.tvAddress.setText(StringTronUtil.indentAddress(wallet.getAddress(), 6));
            }
            this.ivAddressCopy.setVisibility(View.GONE);
            this.ivAddressCopy.setOnClickListener(new NoDoubleClickListener2() {
                @Override
                protected void onNoDoubleClick(View view) {
                    UIUtils.copy(wallet.getAddress());
                    ToastUtil toastUtil = ToastUtil.getInstance();
                    Context context2 = context;
                    toastUtil.showToast(context2, context2.getString(R.string.address_copy_susscess));
                }
            });
            this.tvAuthTime.setText(DateUtils.formatLanguageDate(tokenListBean.getOperateTime().longValue()));
            this.ivProjectNameTips.setOnClickListener(new NoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    String string = context.getResources().getString(R.string.unknow_token_to_tronscan);
                    AnalyticsHelper.logEvent(AnalyticsHelper.SecurityApprovePage.SECURITY_APPROVE_CLICK_UNKNOWN_TOKEN);
                    new MultiLineTextPopupView.Builder().addKeyValue(string, "").setPreferredShowUp(true).setRequiredWidth(UIUtils.dip2px(180.0f)).setAnchor(view).setOffsetX(UIUtils.dip2px(-12.0f)).setShowArrow(true).setMode(MultiLineTextPopupView.LayoutMode.CENTER_HORIZONTAL).build(ApprovedProjectDataHolder.this.itemView.getContext()).show();
                }
            });
        }
    }

    public String getDisPlayName(NameAddressExtraBean nameAddressExtraBean) {
        if (nameAddressExtraBean != null) {
            if (nameAddressExtraBean.getName().length() > 12) {
                return nameAddressExtraBean.getName().toString().substring(0, 9) + "...(" + StringTronUtil.indentAddress(nameAddressExtraBean.getAddress().toString(), 6) + ")";
            }
            return nameAddressExtraBean.getName().toString() + "(" + StringTronUtil.indentAddress(nameAddressExtraBean.getAddress().toString(), 6) + ")";
        }
        return "";
    }
}
