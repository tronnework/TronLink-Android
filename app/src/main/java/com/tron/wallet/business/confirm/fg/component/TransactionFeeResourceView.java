package com.tron.wallet.business.confirm.fg.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.transfer.TransactionTypeTextProvider;
import com.tron.wallet.common.bean.token.TransactionInfoBean;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.LayoutTransactionFeeResourceBinding;
import com.tronlinkpro.wallet.R;
import java.text.NumberFormat;
import java.util.Locale;
public class TransactionFeeResourceView extends FrameLayout {
    private LayoutTransactionFeeResourceBinding binding;
    View burnBandView;
    View burnEnergyView;
    LinearLayout feeContentLayout;
    LinearLayout feeLayout;
    ImageView ivArrow;
    ImageView ivTips;
    private Context mContext;
    public View rlAccountUpdatePermission;
    public View rlActiveAccount;
    public View rlCreateBancorTransaction;
    public View rlCreateRepresentatives;
    public View rlMemoFee;
    public View rlMultisignTransaction;
    public View rlTRC10Issue;
    private boolean showResourceView;
    TextView tvBandResource;
    TextView tvEnergyResource;
    public TextView tvFee;
    public TextView tvRightAccountUpdatePermission;
    public TextView tvRightActiveAccount;
    public TextView tvRightCreateBancorTransaction;
    public TextView tvRightCreateRepresentatives;
    public TextView tvRightMemoFee;
    public TextView tvRightMultisignTransaction;
    public TextView tvRightTRC10Issue;

    public TransactionFeeResourceView(Context context) {
        this(context, null);
    }

    public void mappingId(View view) {
        LayoutTransactionFeeResourceBinding bind = LayoutTransactionFeeResourceBinding.bind(view);
        this.binding = bind;
        this.tvFee = bind.tvFee;
        this.feeLayout = this.binding.llFee;
        this.tvBandResource = this.binding.tvRightBurnForBand;
        this.tvEnergyResource = this.binding.tvRightBurnForEnergy;
        this.burnEnergyView = this.binding.rlBurnForEnergy;
        this.burnBandView = this.binding.rlBurnForBand;
        this.ivTips = this.binding.ivTips;
        this.ivArrow = this.binding.ivArrowRight;
        this.feeContentLayout = this.binding.feeContent;
        this.rlAccountUpdatePermission = this.binding.rlAccountUpdatePermission;
        this.tvRightAccountUpdatePermission = this.binding.tvRightAccountUpdatePermission;
        this.rlActiveAccount = this.binding.rlActiveAccount;
        this.tvRightActiveAccount = this.binding.tvRightActiveAccount;
        this.rlMemoFee = this.binding.rlMemoFee;
        this.tvRightMemoFee = this.binding.tvRightMemoFee;
        this.rlCreateRepresentatives = this.binding.rlCreateRepresentatives;
        this.tvRightCreateRepresentatives = this.binding.tvRightCreateRepresentatives;
        this.rlTRC10Issue = this.binding.rlTRC10Issue;
        this.tvRightTRC10Issue = this.binding.tvRightTRC10Issue;
        this.rlMultisignTransaction = this.binding.rlMultisignTransaction;
        this.tvRightMultisignTransaction = this.binding.tvRightMultisignTransaction;
        this.rlCreateBancorTransaction = this.binding.rlCreateBancorTransaction;
        this.tvRightCreateBancorTransaction = this.binding.tvRightCreateBancorTransaction;
    }

    public TransactionFeeResourceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TransactionFeeResourceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.showResourceView = false;
        this.mContext = context;
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_transaction_fee_resource, (ViewGroup) this, false);
        addView(inflate, new FrameLayout.LayoutParams(-1, -2));
        mappingId(inflate);
        inflate.setBackground(null);
        this.feeContentLayout.setBackground(getResources().getDrawable(R.drawable.roundborder_ebedf0_12));
        this.feeContentLayout.setVisibility(View.GONE);
    }

    public void bindData(TransactionInfoBean transactionInfoBean) {
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        numberInstance.setMaximumFractionDigits(7);
        TransactionInfoBean.CostBean cost = transactionInfoBean.getCost();
        if (cost.getMulti_sign_fee() > 0) {
            this.rlMultisignTransaction.setVisibility(View.VISIBLE);
            TextView textView = this.tvRightMultisignTransaction;
            textView.setText(numberInstance.format(cost.getMulti_sign_fee() / 1000000.0d) + "\tTRX");
        }
        if (cost.getAccount_create_fee() > 0) {
            this.rlActiveAccount.setVisibility(View.VISIBLE);
            TextView textView2 = this.tvRightActiveAccount;
            textView2.setText(numberInstance.format(cost.getAccount_create_fee() / 1000000.0d) + "\tTRX");
        }
        if (cost.getUpdate_account_permission_fee() > 0) {
            this.rlAccountUpdatePermission.setVisibility(View.VISIBLE);
            TextView textView3 = this.tvRightAccountUpdatePermission;
            textView3.setText(numberInstance.format(cost.getUpdate_account_permission_fee() / 1000000.0d) + "\tTRX");
        }
        if (cost.getWitness_create_fee() > 0) {
            this.rlCreateRepresentatives.setVisibility(View.VISIBLE);
            TextView textView4 = this.tvRightCreateRepresentatives;
            textView4.setText(numberInstance.format(cost.getWitness_create_fee() / 1000000.0d) + "\tTRX");
        }
        if (cost.getMemoFee() > 0) {
            this.rlMemoFee.setVisibility(View.VISIBLE);
            TextView textView5 = this.tvRightMemoFee;
            textView5.setText(numberInstance.format(cost.getMemoFee() / 1000000.0d) + "\tTRX");
        }
        long net_fee = cost.getNet_fee() + cost.getEnergy_fee() + cost.getAccount_create_fee() + cost.getMulti_sign_fee() + cost.getUpdate_account_permission_fee() + cost.getWitness_create_fee() + cost.getMemoFee();
        String convert = TransactionTypeTextProvider.convert(transactionInfoBean.getContractType());
        if (cost.getFee() > 0 && "AssetIssueContract".equals(convert)) {
            this.rlTRC10Issue.setVisibility(View.VISIBLE);
            TextView textView6 = this.tvRightTRC10Issue;
            textView6.setText(numberInstance.format(cost.getFee() / 1000000.0d) + "\tTRX");
            net_fee += (long) cost.getFee();
        }
        if (cost.getFee() > 0 && "ExchangeCreateContract".equals(convert)) {
            this.rlCreateBancorTransaction.setVisibility(View.VISIBLE);
            TextView textView7 = this.tvRightCreateBancorTransaction;
            textView7.setText("" + numberInstance.format(cost.getFee() / 1000000.0d) + "\tTRX");
            net_fee += (long) cost.getFee();
        }
        if (cost.getEnergy_fee() > 0) {
            this.burnEnergyView.setVisibility(View.VISIBLE);
            this.tvEnergyResource.setText(String.format("%s TRX", StringTronUtil.formatNumber6Truncate(Double.valueOf(cost.getEnergy_fee() / 1000000.0d))));
        }
        if (cost.getNet_fee() > 0) {
            this.burnBandView.setVisibility(View.VISIBLE);
            this.tvBandResource.setText(String.format("%s TRX", StringTronUtil.formatNumber6Truncate(Double.valueOf(cost.getNet_fee() / 1000000.0d))));
        }
        TextView textView8 = this.tvFee;
        textView8.setText(numberInstance.format(net_fee / 1000000.0d) + "\tTRX");
        this.feeLayout.setVisibility(View.VISIBLE);
        if (net_fee == 0) {
            this.ivArrow.setVisibility(View.INVISIBLE);
        } else {
            this.feeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    lambda$bindData$0(view);
                }
            });
        }
        this.ivTips.setVisibility(View.GONE);
    }

    public void lambda$bindData$0(View view) {
        boolean z = !this.showResourceView;
        this.showResourceView = z;
        try {
            if (z) {
                this.ivArrow.setImageResource(R.mipmap.ic_arrow_detail_up);
                this.feeContentLayout.setVisibility(View.VISIBLE);
            } else {
                this.ivArrow.setImageResource(R.mipmap.ic_arrow_detail_down);
                this.feeContentLayout.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void setViewForShare(TransactionInfoBean transactionInfoBean) {
        TransactionInfoBean.CostBean cost = transactionInfoBean.getCost();
        this.ivArrow.setVisibility(View.INVISIBLE);
        if (cost.getNet_fee() + cost.getEnergy_fee() + cost.getAccount_create_fee() + cost.getMulti_sign_fee() + cost.getUpdate_account_permission_fee() + cost.getWitness_create_fee() + cost.getMemoFee() > 0) {
            this.feeContentLayout.setVisibility(View.VISIBLE);
            bindData(transactionInfoBean);
        }
    }
}
