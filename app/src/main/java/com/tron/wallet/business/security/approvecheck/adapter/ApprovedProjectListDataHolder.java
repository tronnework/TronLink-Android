package com.tron.wallet.business.security.approvecheck.adapter;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.business.security.approvecheck.ApprovedListFragment;
import com.tron.wallet.business.security.approvecheck.CancelApproveClickListener;
import com.tron.wallet.business.security.approvecheck.bean.ApproveListDatabeanOutput;
import com.tron.wallet.common.components.TokenLogoDraweeView;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.ItemApproveRiskBinding;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
import java.math.BigDecimal;
import java.text.NumberFormat;
public class ApprovedProjectListDataHolder extends BaseViewHolder {
    private static final String TYPE_TOKEN = "token";
    private ItemApproveRiskBinding binding;
    View divider;
    TokenLogoDraweeView iconToken;
    ImageView ivArrow;
    ImageView ivContractCopy;
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

    public ApprovedProjectListDataHolder(View view) {
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
        this.divider = this.binding.divider;
        this.rlHeader = this.binding.rlHeader;
    }

    public void onBind(final Context context, final ApproveListDatabeanOutput.ApproveListDatabean.ProjectsBean projectsBean, String str, CancelApproveClickListener cancelApproveClickListener) {
        this.iconToken.setVisibility(View.GONE);
        this.tvContractTag.setVisibility(View.GONE);
        if (ApprovedListFragment.APPROVE_ADDRESS_TYPE_ACCOUNT.equals(projectsBean.getApproveAddressType())) {
            TextView textView = this.tvContractLabel;
            textView.setText(context.getString(R.string.approve_item_account_address) + ":");
            if (projectsBean.getName() == null) {
                this.tvTokenName.setText(context.getString(R.string.approve_unknown_name));
            } else {
                this.tvTokenName.setText(projectsBean.getName());
            }
        } else {
            TextView textView2 = this.tvContractLabel;
            textView2.setText(context.getString(R.string.approve_project_contract) + ":");
            if (projectsBean.getName() == null) {
                this.tvTokenName.setText("");
            } else {
                this.tvTokenName.setText(projectsBean.getName());
            }
        }
        if (SpAPI.THIS.isUsdPrice()) {
            if (BigDecimalUtils.between(projectsBean.getTotalUsdCount(), 0, Double.valueOf(0.001d))) {
                this.tvRiskAmount.setText("$<0.001");
            } else {
                TextView textView3 = this.tvRiskAmount;
                textView3.setText("$" + StringTronUtil.formatNumber3Truncate(new BigDecimal(projectsBean.getTotalUsdCount())));
            }
        } else if (BigDecimalUtils.between(projectsBean.getTotalCnyCount(), 0, Double.valueOf(0.001d))) {
            TextView textView4 = this.tvRiskAmount;
            textView4.setText(((Object) Html.fromHtml("&yen;")) + "<0.001");
        } else {
            TextView textView5 = this.tvRiskAmount;
            textView5.setText(((Object) Html.fromHtml("&yen;")) + StringTronUtil.formatNumber3Truncate(new BigDecimal(projectsBean.getTotalCnyCount())));
        }
        this.tvBalanceLabel.setVisibility(View.GONE);
        this.tvAmount.setVisibility(View.GONE);
        this.tvContractLabel.setVisibility(View.VISIBLE);
        this.tvContract.setVisibility(View.VISIBLE);
        this.ivContractCopy.setVisibility(View.VISIBLE);
        this.tvAmount.setText(projectsBean.getTotalAmount());
        this.tvContract.setText(StringTronUtil.indentAddress(projectsBean.getProjectAddress(), 6));
        this.ivContractCopy.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                UIUtils.copy(projectsBean.getProjectAddress());
                AnalyticsHelper.logEvent(AnalyticsHelper.SecurityApprovePage.SECURITY_APPROVE_CLICK_COPY_CONTRACT_ADDRESS);
                IToast.getIToast().show(context.getResources().getString(R.string.address_copy_susscess));
            }
        });
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
        this.recyclerView.setAdapter(new ApproveProjectItemAdapter(R.layout.item_approve_risk_project, projectsBean.getTokenList(), cancelApproveClickListener, projectsBean));
    }
}
