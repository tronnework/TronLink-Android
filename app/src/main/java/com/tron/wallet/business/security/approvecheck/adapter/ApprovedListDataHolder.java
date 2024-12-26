package com.tron.wallet.business.security.approvecheck.adapter;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.wallet.business.security.approvecheck.CancelApproveClickListener;
import com.tron.wallet.business.security.approvecheck.bean.ApproveListDatabeanOutput;
import com.tron.wallet.common.components.MultiLineTextPopupView;
import com.tron.wallet.common.components.TokenLogoDraweeView;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.ItemApproveRiskBinding;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
import java.math.BigDecimal;
import java.text.NumberFormat;
import org.apache.commons.cli.HelpFormatter;
public class ApprovedListDataHolder extends BaseViewHolder {
    private static final String TYPE_TOKEN = "token";
    private ItemApproveRiskBinding binding;
    View divider;
    TokenLogoDraweeView iconToken;
    ImageView ivArrow;
    ImageView ivContractCopy;
    ImageView ivUnrecorderTips;
    View namePlaceVolderView;
    private final NumberFormat numberFormat;
    RecyclerView recyclerView;
    RelativeLayout rlHeader;
    TextView tvAmount;
    TextView tvBalanceLabel;
    TextView tvContract;
    TextView tvContractLabel;
    TextView tvContractTag;
    TextView tvRiskAmount;
    TextView tvTokenName;
    TextView tvUnknowContract;

    public ApprovedListDataHolder(View view) {
        super(view);
        mappingId(view);
        NumberFormat numberFormat = NumberFormat.getInstance();
        this.numberFormat = numberFormat;
        numberFormat.setMaximumFractionDigits(6);
    }

    private void mappingId(View view) {
        ItemApproveRiskBinding bind = ItemApproveRiskBinding.bind(view);
        this.binding = bind;
        this.iconToken = bind.iconToken;
        this.tvTokenName = this.binding.tvTokenName;
        this.tvContractTag = this.binding.tvContractTag;
        this.tvBalanceLabel = this.binding.tvBalanceTitle;
        this.tvAmount = this.binding.tvAmount;
        this.tvContractLabel = this.binding.tvContractLabel;
        this.tvContract = this.binding.tvContract;
        this.ivContractCopy = this.binding.ivContractCopy;
        this.ivArrow = this.binding.ivArrow;
        this.tvRiskAmount = this.binding.tvRiskAmount;
        this.recyclerView = this.binding.recyclerView;
        this.ivUnrecorderTips = this.binding.ivUnrecorderTips;
        this.tvUnknowContract = this.binding.tvUnknowContract;
        this.divider = this.binding.divider;
        this.namePlaceVolderView = this.binding.placeHolderView;
        this.rlHeader = this.binding.rlHeader;
    }

    public void onBind(Context context, ApproveListDatabeanOutput.ApproveListDatabean.TokensBean tokensBean, String str, CancelApproveClickListener cancelApproveClickListener) {
        if (StringTronUtil.isEmpty(tokensBean.getTokenSymbol()) && StringTronUtil.isEmpty(tokensBean.getTokenName())) {
            this.tvTokenName.setText("");
            this.namePlaceVolderView.setVisibility(View.GONE);
            this.iconToken.setImageURI(tokensBean.getTokenLogo());
            this.ivUnrecorderTips.setVisibility(View.VISIBLE);
            this.tvContractTag.setText("SC");
            this.tvContractTag.setBackground(context.getDrawable(R.drawable.roundborder_unknow_sc));
            this.tvContractTag.setTextColor(context.getResources().getColor(R.color.gray_6D778C));
            this.tvUnknowContract.setText(StringTronUtil.indentAddress(tokensBean.getTokenAddress(), 6));
            this.tvUnknowContract.setVisibility(View.VISIBLE);
            this.tvBalanceLabel.setVisibility(View.VISIBLE);
            this.tvAmount.setVisibility(View.VISIBLE);
            this.tvAmount.setText("0");
            if (SpAPI.THIS.isUsdPrice()) {
                this.tvRiskAmount.setText("$0");
            } else {
                TextView textView = this.tvRiskAmount;
                textView.setText(((Object) Html.fromHtml("&yen;")) + "0");
            }
            this.ivUnrecorderTips.setOnClickListener(new NoDoubleClickListener2() {
                @Override
                protected void onNoDoubleClick(View view) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.SecurityApprovePage.SECURITY_APPROVE_CLICK_UNKNOWN_TOKEN);
                    Context context2 = view.getContext();
                    new MultiLineTextPopupView.Builder().addKeyValue(context2.getString(R.string.unknow_token_to_tronscan), "").setRequiredWidth(UIUtils.dip2px(180.0f)).setPreferredShowUp(true).setMode(MultiLineTextPopupView.LayoutMode.CENTER_HORIZONTAL).setAnchor(view).setShowArrow(true).build(context2).show();
                }
            });
            this.tvContractLabel.setVisibility(View.GONE);
            this.tvContract.setVisibility(View.GONE);
            this.ivContractCopy.setVisibility(View.GONE);
        } else {
            this.ivUnrecorderTips.setVisibility(View.GONE);
            this.tvUnknowContract.setVisibility(View.GONE);
            if (StringTronUtil.isEmpty(tokensBean.getTokenLogo())) {
                this.iconToken.setImageURI(tokensBean.getTokenLogo());
            } else {
                this.iconToken.setCircularImage(tokensBean.getTokenLogo());
            }
            this.tvTokenName.setText(tokensBean.getTokenName());
            this.namePlaceVolderView.setVisibility(View.VISIBLE);
            this.tvBalanceLabel.setVisibility(View.VISIBLE);
            this.tvAmount.setVisibility(View.VISIBLE);
            this.tvContractLabel.setVisibility(View.GONE);
            this.tvContract.setVisibility(View.GONE);
            this.ivContractCopy.setVisibility(View.GONE);
            if (tokensBean.getTokenType() == 2) {
                if (SpAPI.THIS.isUsdPrice()) {
                    if (BigDecimalUtils.between(tokensBean.getTotalCnyCount(), 0, Double.valueOf(0.001d))) {
                        this.tvRiskAmount.setText("$<0.001");
                    } else {
                        TextView textView2 = this.tvRiskAmount;
                        textView2.setText("$" + StringTronUtil.formatNumber3Truncate(new BigDecimal(tokensBean.getTotalUsdCount())));
                    }
                } else if (BigDecimalUtils.between(tokensBean.getTotalCnyCount(), 0, Double.valueOf(0.001d))) {
                    TextView textView3 = this.tvRiskAmount;
                    textView3.setText(((Object) Html.fromHtml("&yen;")) + "<0.001");
                } else {
                    TextView textView4 = this.tvRiskAmount;
                    textView4.setText(((Object) Html.fromHtml("&yen;")) + StringTronUtil.formatNumber3Truncate(new BigDecimal(tokensBean.getTotalCnyCount())));
                }
            } else if (tokensBean.getTokenType() == 5) {
                this.tvRiskAmount.setText(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
            }
            if (tokensBean.getTokenType() == 2) {
                this.tvContractTag.setBackground(context.getDrawable(R.drawable.roundborder_1557_2));
                this.tvContractTag.setTextColor(context.getResources().getColor(R.color.green_57));
                if (BigDecimalUtils.between(new BigDecimal(tokensBean.getBalanceStr()), 0, Double.valueOf(1.0E-6d))) {
                    this.tvAmount.setText("<0.000001");
                } else {
                    this.tvAmount.setText(StringTronUtil.formatNumber6Truncate(new BigDecimal(tokensBean.getBalanceStr())));
                }
                this.tvRiskAmount.setVisibility(View.VISIBLE);
            } else if (tokensBean.getTokenType() == 5) {
                this.tvContractTag.setBackground(context.getDrawable(R.drawable.roundborder_ffa928_2));
                this.tvContractTag.setTextColor(context.getResources().getColor(R.color.orange_FFA9));
                this.tvContractTag.setText(context.getString(R.string.TRC721));
                this.tvAmount.setText(tokensBean.getTotalAmount());
                this.tvRiskAmount.setVisibility(View.GONE);
            } else {
                this.tvContractTag.setBackground(context.getDrawable(R.drawable.roundborder_unknow_sc));
                this.tvContractTag.setTextColor(context.getResources().getColor(R.color.gray_6D778C));
                this.tvRiskAmount.setVisibility(View.VISIBLE);
            }
        }
        this.rlHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.SecurityApprovePage.SECURITY_APPROVE_CLICK_SHOW_UP_DOWN);
                if (recyclerView.getVisibility() == 8) {
                    ivArrow.setImageResource(R.mipmap.ic_approve_arrow_up);
                    recyclerView.setVisibility(View.VISIBLE);
                    divider.setVisibility(View.GONE);
                    return;
                }
                ivArrow.setImageResource(R.mipmap.ic_approve_arrow_down);
                recyclerView.setVisibility(View.GONE);
                divider.setVisibility(View.VISIBLE);
            }
        });
        this.recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(context, 1, false));
        this.recyclerView.setAdapter(new ApproveTokenItemAdapter(R.layout.item_approve_risk_token, tokensBean.getProjectList(), cancelApproveClickListener, tokensBean));
    }
}
