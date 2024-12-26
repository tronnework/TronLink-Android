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
import com.tron.wallet.business.security.approvecheck.ApprovedListFragment;
import com.tron.wallet.business.security.approvecheck.CancelApproveClickListener;
import com.tron.wallet.business.security.approvecheck.bean.ApproveListDatabeanOutput;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.common.utils.AddressMapUtils;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.DateUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.ToastUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.ItemApproveRiskTokenBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.cli.HelpFormatter;
import org.tron.walletserver.Wallet;
public class ApproveTokenItemAdapter extends BaseQuickAdapter<ApproveListDatabeanOutput.ApproveListDatabean.TokensBean.ProjectListBean, BaseViewHolder> {
    private CancelApproveClickListener cancelApproveClickListener;
    private ApproveListDatabeanOutput.ApproveListDatabean.TokensBean tokensBean;
    private Wallet wallet;

    public ApproveTokenItemAdapter(int i, List<ApproveListDatabeanOutput.ApproveListDatabean.TokensBean.ProjectListBean> list, CancelApproveClickListener cancelApproveClickListener, ApproveListDatabeanOutput.ApproveListDatabean.TokensBean tokensBean) {
        super(i, list);
        this.tokensBean = tokensBean;
        this.cancelApproveClickListener = cancelApproveClickListener;
        this.wallet = WalletUtils.getSelectedWallet();
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder, ApproveListDatabeanOutput.ApproveListDatabean.TokensBean.ProjectListBean projectListBean) {
        if (baseViewHolder instanceof ApprovedTokenDataHolder) {
            ((ApprovedTokenDataHolder) baseViewHolder).onBind(this.mContext, projectListBean);
        }
    }

    @Override
    public BaseViewHolder onCreateDefViewHolder(ViewGroup viewGroup, int i) {
        return new ApprovedTokenDataHolder(getItemView(this.mLayoutResId, viewGroup));
    }

    public class ApprovedTokenDataHolder extends BaseViewHolder {
        private ItemApproveRiskTokenBinding binding;
        Button btnCancelAuth;
        ImageView ivAddressCopy;
        ImageView ivTokenAddressCopy;
        TextView tokenContractTag;
        TextView tvAccountRiskAmount;
        TextView tvAccountRiskAmountLabel;
        TextView tvAddress;
        TextView tvAuthTime;
        TextView tvProjectName;
        TextView tvQuantity;
        TextView tvTokenAddress;

        public ApprovedTokenDataHolder(View view) {
            super(view);
            mappingId(view);
        }

        private void mappingId(View view) {
            ItemApproveRiskTokenBinding bind = ItemApproveRiskTokenBinding.bind(view);
            this.binding = bind;
            this.btnCancelAuth = bind.btnCancelAuthorization;
            this.tvProjectName = this.binding.tvProjectName;
            this.tvAddress = this.binding.tvAccountAddress;
            this.ivAddressCopy = this.binding.icAccountAddressCopy;
            this.tvQuantity = this.binding.tvAuthorizationQuantity;
            this.tvAuthTime = this.binding.tvAuthorizationTime;
            this.tvAccountRiskAmount = this.binding.tvAccountRiskAmount;
            this.tvAccountRiskAmountLabel = this.binding.tvAccountRiskAmountLabel;
            this.tokenContractTag = this.binding.tokenContractTag;
            this.tvTokenAddress = this.binding.tvTokenAddress;
            this.ivTokenAddressCopy = this.binding.ivTokenAddressCopy;
        }

        public void onBind(final Context context, final ApproveListDatabeanOutput.ApproveListDatabean.TokensBean.ProjectListBean projectListBean) {
            this.btnCancelAuth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String substring;
                    String str;
                    String projectAddress;
                    String approveAddressType;
                    String tokenAddress;
                    int tokenType;
                    String tokenLogo;
                    String tokenName;
                    String trc721Id;
                    String tokenSymbol;
                    String str2;
                    String str3;
                    String str4;
                    String str5;
                    String str6;
                    String str7;
                    String str8;
                    String str9;
                    if (tokensBean.getTokenType() == 2) {
                        String projectAddress2 = projectListBean.getProjectAddress();
                        String approveAddressType2 = projectListBean.getApproveAddressType();
                        String tokenAddress2 = tokensBean.getTokenAddress();
                        tokenType = tokensBean.getTokenType();
                        String tokenLogo2 = tokensBean.getTokenLogo();
                        str2 = projectAddress2;
                        str3 = approveAddressType2;
                        str7 = "";
                        str4 = tokenAddress2;
                        str5 = tokenLogo2;
                        str6 = tokensBean.getTokenName();
                        str9 = tokensBean.getTokenSymbol();
                        str8 = "0";
                    } else {
                        if (tokensBean.getTokenType() == 5) {
                            str = tokensBean.getTrc721Id().substring(tokensBean.getTrc721Id().indexOf("_") + 1);
                            projectAddress = projectListBean.getProjectAddress();
                            approveAddressType = projectListBean.getApproveAddressType();
                            tokenAddress = tokensBean.getTokenAddress();
                            tokenType = tokensBean.getTokenType();
                            tokenLogo = tokensBean.getTokenLogo();
                            tokenName = tokensBean.getTokenName();
                            trc721Id = tokensBean.getTrc721Id();
                            tokenSymbol = tokensBean.getTokenSymbol();
                        } else {
                            if (StringTronUtil.isEmpty(tokensBean.getTrc721Id())) {
                                substring = tokensBean.getTotalAmount();
                            } else {
                                substring = tokensBean.getTrc721Id().substring(tokensBean.getTrc721Id().indexOf("_") + 1);
                            }
                            str = substring;
                            projectAddress = projectListBean.getProjectAddress();
                            approveAddressType = projectListBean.getApproveAddressType();
                            tokenAddress = tokensBean.getTokenAddress();
                            tokenType = tokensBean.getTokenType();
                            tokenLogo = tokensBean.getTokenLogo();
                            tokenName = tokensBean.getTokenName();
                            trc721Id = tokensBean.getTrc721Id();
                            tokenSymbol = tokensBean.getTokenSymbol();
                        }
                        str2 = projectAddress;
                        str3 = approveAddressType;
                        str4 = tokenAddress;
                        str5 = tokenLogo;
                        str6 = tokenName;
                        str7 = trc721Id;
                        str8 = str;
                        str9 = tokenSymbol;
                    }
                    if (cancelApproveClickListener != null) {
                        CancelApproveClickListener cancelApproveClickListener = cancelApproveClickListener;
                        cancelApproveClickListener.onCancelApproveClick(str8, str2, str2, str3, str4, tokenType + "", str6, str9, str5, str7);
                    }
                }
            });
            this.ivAddressCopy.setOnClickListener(new NoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    UIUtils.copy(wallet.getAddress());
                    ToastUtil toastUtil = ToastUtil.getInstance();
                    Context context2 = context;
                    toastUtil.showToast(context2, context2.getString(R.string.address_copy_susscess));
                }
            });
            this.tvTokenAddress.setText(StringTronUtil.indentAddress(projectListBean.getProjectAddress(), 6));
            this.ivTokenAddressCopy.setOnClickListener(new NoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    UIUtils.copy(projectListBean.getProjectAddress());
                    AnalyticsHelper.logEvent(AnalyticsHelper.SecurityApprovePage.SECURITY_APPROVE_CLICK_COPY_CONTRACT_ADDRESS);
                    ToastUtil toastUtil = ToastUtil.getInstance();
                    Context context2 = context;
                    toastUtil.showToast(context2, context2.getString(R.string.address_copy_susscess));
                }
            });
            if (ApprovedListFragment.APPROVE_ADDRESS_TYPE_ACCOUNT.equals(projectListBean.getApproveAddressType())) {
                this.tokenContractTag.setVisibility(View.GONE);
                if (StringTronUtil.isEmpty(projectListBean.getName())) {
                    this.tvProjectName.setText(context.getString(R.string.approve_unknown_name));
                    this.tvTokenAddress.setVisibility(View.VISIBLE);
                    this.tokenContractTag.setVisibility(View.GONE);
                    this.tvTokenAddress.setText(StringTronUtil.indentAddress(projectListBean.getProjectAddress(), 6));
                    this.ivTokenAddressCopy.setVisibility(View.VISIBLE);
                } else {
                    this.tvProjectName.setText(projectListBean.getName());
                    this.tvTokenAddress.setVisibility(View.VISIBLE);
                    this.ivTokenAddressCopy.setVisibility(View.VISIBLE);
                }
            } else if ("project".equals(projectListBean.getApproveAddressType())) {
                this.tokenContractTag.setVisibility(View.VISIBLE);
                this.tvTokenAddress.setVisibility(View.VISIBLE);
                this.ivTokenAddressCopy.setVisibility(View.VISIBLE);
                this.tvProjectName.setText(projectListBean.getName());
            }
            if (tokensBean.getTokenType() == 2) {
                if (projectListBean.getUnlimited().booleanValue()) {
                    this.tvQuantity.setText(context.getString(R.string.unlimited));
                } else if (BigDecimalUtils.between(projectListBean.getAmount(), 0, Double.valueOf(1.0E-6d))) {
                    this.tvQuantity.setText("<0.000001");
                } else {
                    this.tvQuantity.setText(StringTronUtil.formatNumber6Truncate(new BigDecimal(projectListBean.getAmount())));
                }
                if (AddressMapUtils.getInstance().isContainsAddress(wallet.getAddress())) {
                    this.tvAddress.setText(getDisPlayName(AddressMapUtils.getInstance().getNameAddress(wallet.getAddress())));
                } else {
                    this.tvAddress.setText(StringTronUtil.indentAddress(wallet.getAddress(), 6));
                }
                if (SpAPI.THIS.isUsdPrice()) {
                    if (BigDecimalUtils.between(projectListBean.getUsdCount(), 0, Double.valueOf(0.001d))) {
                        this.tvAccountRiskAmount.setText("$<0.001");
                    } else {
                        TextView textView = this.tvAccountRiskAmount;
                        textView.setText("$" + StringTronUtil.formatNumber3Truncate(new BigDecimal(projectListBean.getUsdCount())));
                    }
                } else if (BigDecimalUtils.between(projectListBean.getCnyCount(), 0, Double.valueOf(0.001d))) {
                    TextView textView2 = this.tvAccountRiskAmount;
                    textView2.setText(((Object) Html.fromHtml("&yen;")) + "<0.001");
                } else {
                    TextView textView3 = this.tvAccountRiskAmount;
                    textView3.setText(((Object) Html.fromHtml("&yen;")) + StringTronUtil.formatNumber3Truncate(new BigDecimal(projectListBean.getCnyCount())));
                }
            } else if (tokensBean.getTokenType() == 5) {
                String substring = tokensBean.getTrc721Id().substring(tokensBean.getTrc721Id().indexOf("_") + 1);
                TextView textView4 = this.tvQuantity;
                textView4.setText("#" + substring + " " + tokensBean.getTokenSymbol());
                if (AddressMapUtils.getInstance().isContainsAddress(wallet.getAddress())) {
                    this.tvAddress.setText(getDisPlayName(AddressMapUtils.getInstance().getNameAddress(wallet.getAddress())));
                } else {
                    this.tvAddress.setText(StringTronUtil.indentAddress(wallet.getAddress(), 6));
                }
                this.ivAddressCopy.setVisibility(View.GONE);
                this.tvAccountRiskAmount.setText(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
                this.tvAccountRiskAmount.setVisibility(View.VISIBLE);
                this.tvAccountRiskAmountLabel.setVisibility(View.VISIBLE);
            } else if (tokensBean.getTokenType() == 0) {
                if (AddressMapUtils.getInstance().isContainsAddress(wallet.getAddress())) {
                    this.tvAddress.setText(getDisPlayName(AddressMapUtils.getInstance().getNameAddress(wallet.getAddress())));
                } else {
                    this.tvAddress.setText(StringTronUtil.indentAddress(wallet.getAddress(), 6));
                }
                this.ivAddressCopy.setVisibility(View.GONE);
                if (projectListBean.getUnlimited().booleanValue()) {
                    this.tvQuantity.setText(context.getString(R.string.unlimited));
                } else {
                    this.tvQuantity.setText(StringTronUtil.formatNumber6Truncate(new BigDecimal(projectListBean.getAmount())));
                }
                if (SpAPI.THIS.isUsdPrice()) {
                    if (BigDecimalUtils.between(projectListBean.getUsdCount(), 0, Double.valueOf(0.001d))) {
                        this.tvAccountRiskAmount.setText("$<0.001");
                    } else {
                        TextView textView5 = this.tvAccountRiskAmount;
                        textView5.setText("$" + StringTronUtil.formatNumber3Truncate(new BigDecimal(projectListBean.getUsdCount())));
                    }
                } else if (BigDecimalUtils.between(projectListBean.getCnyCount(), 0, Double.valueOf(0.001d))) {
                    TextView textView6 = this.tvAccountRiskAmount;
                    textView6.setText(((Object) Html.fromHtml("&yen;")) + "<0.001");
                } else {
                    TextView textView7 = this.tvAccountRiskAmount;
                    textView7.setText(((Object) Html.fromHtml("&yen;")) + StringTronUtil.formatNumber3Truncate(new BigDecimal(projectListBean.getCnyCount())));
                }
            }
            this.tvAuthTime.setText(DateUtils.formatLanguageDate(projectListBean.getOperateTime().longValue()));
        }

        private String getDisPlayName(NameAddressExtraBean nameAddressExtraBean) {
            if (nameAddressExtraBean != null) {
                if (nameAddressExtraBean.getName().length() > 12) {
                    return nameAddressExtraBean.getName().toString().substring(0, 9) + "...(" + StringTronUtil.indentAddress(nameAddressExtraBean.getAddress().toString(), 6) + ")";
                }
                return nameAddressExtraBean.getName().toString() + "(" + StringTronUtil.indentAddress(nameAddressExtraBean.getAddress().toString(), 6) + ")";
            }
            return "";
        }
    }
}
