package com.tron.wallet.business.confirm.fg.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.message.TransactionMessage;
import com.tron.wallet.business.transfer.TransactionTypeTextProvider;
import com.tron.wallet.common.bean.token.TransactionInfoBean;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.PopupWindowUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.LayoutTransactionDetailResourceBinding;
import com.tronlinkpro.wallet.R;
import io.reactivex.disposables.CompositeDisposable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import org.apache.commons.cli.HelpFormatter;
import org.slf4j.Marker;
public class TransactionResourceView extends FrameLayout {
    private LayoutTransactionDetailResourceBinding binding;
    View burnBandView;
    View burnEnergyView;
    private CompositeDisposable disposables;
    ImageView ivArrow;
    public ImageView ivFeeLoading;
    ImageView ivTips;
    public LinearLayout llOtherError;
    private Context mContext;
    LinearLayout resourceView;
    public View rlAccountUpdatePermission;
    public View rlActiveAccount;
    public View rlCreateBancorTransaction;
    public View rlCreateRepresentatives;
    public View rlFee;
    public View rlMemoFee;
    public View rlMultisignTransaction;
    public View rlTRC10Issue;
    private boolean showResourceView;
    public TextView tvAmount;
    public TextView tvAmountTitle;
    TextView tvBandResource;
    TextView tvEnergyResource;
    public TextView tvFee;
    public TextView tvFeeTitle;
    public TextView tvOtherError;
    public TextView tvRightAccountUpdatePermission;
    public TextView tvRightActiveAccount;
    public TextView tvRightCreateBancorTransaction;
    public TextView tvRightCreateRepresentatives;
    public TextView tvRightMemoFee;
    public TextView tvRightMultisignTransaction;
    public TextView tvRightTRC10Issue;

    public TransactionResourceView(Context context) {
        this(context, null);
    }

    public TransactionResourceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TransactionResourceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.showResourceView = false;
        this.disposables = new CompositeDisposable();
        this.mContext = context;
        View inflate = View.inflate(context, R.layout.layout_transaction_detail_resource, null);
        addView(inflate, new FrameLayout.LayoutParams(-1, -1));
        mappingId(inflate);
    }

    public void mappingId(View view) {
        LayoutTransactionDetailResourceBinding bind = LayoutTransactionDetailResourceBinding.bind(view);
        this.binding = bind;
        this.ivTips = bind.resourceInfoView.ivTips;
        this.resourceView = this.binding.resourceInfoView.getRoot();
        this.ivArrow = this.binding.ivArrowRight;
        this.tvBandResource = this.binding.resourceInfoView.tvRightBurnForBand;
        this.tvEnergyResource = this.binding.resourceInfoView.tvRightBurnForEnergy;
        this.burnEnergyView = this.binding.resourceInfoView.rlBurnForEnergy;
        this.burnBandView = this.binding.resourceInfoView.rlBurnForBand;
        this.tvAmount = this.binding.tvAmount;
        this.tvAmountTitle = this.binding.tvAmountTitle;
        this.tvFee = this.binding.tvFee;
        this.rlFee = this.binding.rlFee;
        this.tvFeeTitle = this.binding.tvFeeTitle;
        this.tvOtherError = this.binding.tvErrorText;
        this.llOtherError = this.binding.llOtherError;
        this.rlAccountUpdatePermission = this.binding.resourceInfoView.rlAccountUpdatePermission;
        this.tvRightAccountUpdatePermission = this.binding.resourceInfoView.tvRightAccountUpdatePermission;
        this.rlActiveAccount = this.binding.resourceInfoView.rlActiveAccount;
        this.tvRightActiveAccount = this.binding.resourceInfoView.tvRightActiveAccount;
        this.rlCreateRepresentatives = this.binding.resourceInfoView.rlCreateRepresentatives;
        this.tvRightCreateRepresentatives = this.binding.resourceInfoView.tvRightCreateRepresentatives;
        this.rlTRC10Issue = this.binding.resourceInfoView.rlTRC10Issue;
        this.tvRightTRC10Issue = this.binding.resourceInfoView.tvRightTRC10Issue;
        this.rlMultisignTransaction = this.binding.resourceInfoView.rlMultisignTransaction;
        this.tvRightMultisignTransaction = this.binding.resourceInfoView.tvRightMultisignTransaction;
        this.rlCreateBancorTransaction = this.binding.resourceInfoView.rlCreateBancorTransaction;
        this.tvRightCreateBancorTransaction = this.binding.resourceInfoView.tvRightCreateBancorTransaction;
        this.ivFeeLoading = this.binding.ivFeeLoading;
        this.ivTips = this.binding.resourceInfoView.ivTips;
        this.resourceView = (LinearLayout) this.binding.getRoot().findViewById(R.id.resource_info_view);
        this.rlMemoFee = this.binding.getRoot().findViewById(R.id.rl_memo_fee);
        this.tvRightMemoFee = (TextView) this.binding.getRoot().findViewById(R.id.tv_right_memo_fee);
    }

    public void bindData(String str, TransactionInfoBean transactionInfoBean, String str2) {
        if (transactionInfoBean.isRevert() || !TransactionMessage.TYPE_SUCCESS.equals(transactionInfoBean.getContractRet())) {
            this.tvAmount.setTextColor(getResources().getColor(R.color.gray_9B));
        }
        if ("FreezeBalanceContract".equals(str2) || "FreezeBalanceV2Contract".equals(str2)) {
            this.tvAmountTitle.setText(R.string.stake_quantity);
            this.tvAmount.setText(str);
        }
        if ("UnfreezeV2WithdrawContract".equals(str2) || "WithdrawExpireUnfreezeContract".equals(str2)) {
            this.tvAmountTitle.setText(R.string.Transaction_withdrawUnfreeze_Amount);
            this.tvAmount.setText(str);
        }
        if ("UnfreezeBalanceContract".equals(str2) || "UnfreezeBalanceV2Contract".equals(str2)) {
            this.tvAmountTitle.setText(R.string.unstaked_quantity);
            this.tvAmount.setText(str);
        }
        if ("CancelAllUnfreezeV2Contract".equals(str2)) {
            this.tvAmountTitle.setText(R.string.cancel_all_unstake_stream_amount);
            this.tvAmount.setText(str);
        }
        if ("WithdrawBalanceContract".equals(str2)) {
            this.tvAmount.setText(str);
        } else if ("FeeContract".equals(str2)) {
            this.tvAmount.setText(str);
        } else {
            this.tvAmount.setText(str);
        }
        handleFeeView(transactionInfoBean);
        this.ivTips.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                PopupWindowUtil.showPopupText(mContext, getResources().getString(R.string.transaction_detail_fee_tips), ivTips, true);
            }
        });
    }

    private void handleFeeView(TransactionInfoBean transactionInfoBean) {
        TransactionInfoBean.CostBean cost = transactionInfoBean.getCost();
        if (cost == null) {
            this.rlFee.setVisibility(View.GONE);
            return;
        }
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        numberInstance.setMaximumFractionDigits(7);
        if (cost.getMulti_sign_fee() > 0) {
            this.rlMultisignTransaction.setVisibility(View.VISIBLE);
            TextView textView = this.tvRightMultisignTransaction;
            textView.setText(HelpFormatter.DEFAULT_OPT_PREFIX + numberInstance.format(cost.getMulti_sign_fee() / 1000000.0d) + "\tTRX");
        }
        if (cost.getAccount_create_fee() > 0) {
            this.rlActiveAccount.setVisibility(View.VISIBLE);
            TextView textView2 = this.tvRightActiveAccount;
            textView2.setText(HelpFormatter.DEFAULT_OPT_PREFIX + numberInstance.format(cost.getAccount_create_fee() / 1000000.0d) + "\tTRX");
        }
        if (cost.getUpdate_account_permission_fee() > 0) {
            this.rlAccountUpdatePermission.setVisibility(View.VISIBLE);
            TextView textView3 = this.tvRightAccountUpdatePermission;
            textView3.setText(HelpFormatter.DEFAULT_OPT_PREFIX + numberInstance.format(cost.getUpdate_account_permission_fee() / 1000000.0d) + "\tTRX");
        }
        if (cost.getWitness_create_fee() > 0) {
            this.rlCreateRepresentatives.setVisibility(View.VISIBLE);
            TextView textView4 = this.tvRightCreateRepresentatives;
            textView4.setText(HelpFormatter.DEFAULT_OPT_PREFIX + numberInstance.format(cost.getWitness_create_fee() / 1000000.0d) + "\tTRX");
        }
        if (cost.getMemoFee() > 0) {
            this.rlMemoFee.setVisibility(View.VISIBLE);
            TextView textView5 = this.tvRightMemoFee;
            textView5.setText(HelpFormatter.DEFAULT_OPT_PREFIX + numberInstance.format(cost.getMemoFee() / 1000000.0d) + "\tTRX");
        }
        long net_fee = cost.getNet_fee() + cost.getEnergy_fee() + cost.getAccount_create_fee() + cost.getMulti_sign_fee() + cost.getUpdate_account_permission_fee() + cost.getWitness_create_fee() + cost.getMemoFee();
        String convert = TransactionTypeTextProvider.convert(transactionInfoBean.getContractType());
        if (cost.getFee() > 0 && "AssetIssueContract".equals(convert)) {
            this.rlTRC10Issue.setVisibility(View.VISIBLE);
            TextView textView6 = this.tvRightTRC10Issue;
            textView6.setText(HelpFormatter.DEFAULT_OPT_PREFIX + numberInstance.format(cost.getFee() / 1000000.0d) + "\tTRX");
            net_fee += (long) cost.getFee();
        }
        if (cost.getFee() > 0 && "ExchangeCreateContract".equals(convert)) {
            this.rlCreateBancorTransaction.setVisibility(View.VISIBLE);
            TextView textView7 = this.tvRightCreateBancorTransaction;
            textView7.setText(HelpFormatter.DEFAULT_OPT_PREFIX + numberInstance.format(cost.getFee() / 1000000.0d) + "\tTRX");
            net_fee += (long) cost.getFee();
        }
        if (cost.getEnergy_fee() > 0) {
            this.burnEnergyView.setVisibility(View.VISIBLE);
            this.tvEnergyResource.setText(String.format("-%s TRX", StringTronUtil.formatNumber6Truncate(Double.valueOf(cost.getEnergy_fee() / 1000000.0d))));
        }
        if (cost.getNet_fee() > 0) {
            this.burnBandView.setVisibility(View.VISIBLE);
            this.tvBandResource.setText(String.format("-%s TRX", StringTronUtil.formatNumber6Truncate(Double.valueOf(cost.getNet_fee() / 1000000.0d))));
        }
        TextView textView8 = this.tvFee;
        textView8.setText(HelpFormatter.DEFAULT_OPT_PREFIX + numberInstance.format(net_fee / 1000000.0d) + "\tTRX");
        if (net_fee == 0) {
            this.ivArrow.setVisibility(View.GONE);
        } else {
            this.rlFee.setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    lambda$handleFeeView$0(view);
                }
            });
        }
    }

    public void lambda$handleFeeView$0(View view) {
        boolean z = !this.showResourceView;
        this.showResourceView = z;
        try {
            if (z) {
                this.ivArrow.setImageResource(R.mipmap.ic_arrow_detail_up);
                this.resourceView.setVisibility(View.VISIBLE);
            } else {
                this.ivArrow.setImageResource(R.mipmap.ic_arrow_detail_down);
                this.resourceView.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        CompositeDisposable compositeDisposable = this.disposables;
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    public void setViewForShare(String str, TransactionInfoBean transactionInfoBean, String str2) {
        this.ivArrow.setVisibility(View.INVISIBLE);
        this.ivTips.setVisibility(View.INVISIBLE);
        TransactionInfoBean.CostBean cost = transactionInfoBean.getCost();
        long net_fee = cost == null ? 0L : cost.getNet_fee() + cost.getEnergy_fee() + cost.getAccount_create_fee() + cost.getMulti_sign_fee() + cost.getUpdate_account_permission_fee() + cost.getWitness_create_fee() + cost.getMemoFee();
        this.ivArrow.setVisibility(View.INVISIBLE);
        if (net_fee > 0) {
            this.resourceView.setVisibility(View.VISIBLE);
            int contractType = transactionInfoBean.getContractType();
            if (contractType != 0 && contractType != 2 && contractType != 8 && contractType != 10 && contractType != 33 && contractType != 4 && contractType != 5 && contractType != 6 && contractType != 30 && contractType != 31 && contractType != 48 && contractType != 49 && contractType != 52 && contractType != 53) {
                switch (contractType) {
                    case 13:
                        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
                        numberInstance.setMaximumFractionDigits(7);
                        BigDecimal bigDecimal = new BigDecimal(numberInstance.format(transactionInfoBean.getInfo().getWithdraw_amount() / 1000000.0d));
                        str = Marker.ANY_NON_NULL_MARKER + bigDecimal.toPlainString() + " TRX";
                        break;
                    default:
                        switch (contractType) {
                            case 41:
                            case 42:
                            case 43:
                            case 44:
                            case 45:
                            case 46:
                                break;
                            default:
                                if (StringTronUtil.equals(str2, "FeeContract")) {
                                    str = "0 TRX";
                                    break;
                                }
                                break;
                        }
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                        str = "-0 TRX";
                        break;
                }
                bindData(str, transactionInfoBean, str2);
            }
            str = "-0 TRX";
            bindData(str, transactionInfoBean, str2);
        }
    }
}
