package com.tron.wallet.common.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.NumUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.LayoutFeeResourceDetailBinding;
import com.tronlinkpro.wallet.R;
public class FeeResourceDetailView extends FrameLayout {
    LayoutFeeResourceDetailBinding binding;
    View rlFee;
    View rlMemoFee;
    View rlMultiFee;
    RelativeLayout rlResourceBw;
    View rlResourceEnergy;
    TextView tvActiveAccount;
    TextView tvBandwidthInTrx;
    TextView tvBwCount;
    TextView tvEnergyCount;
    TextView tvEnergyInTrx;
    TextView tvLeftActivateAccount;
    TextView tvLeftMemoFee;
    TextView tvMemoFee;
    TextView tvMultiFee;
    TextView tvMultiName;

    public FeeResourceDetailView(Context context) {
        this(context, null);
    }

    public FeeResourceDetailView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FeeResourceDetailView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_fee_resource_detail, (ViewGroup) this, false);
        addView(inflate, new FrameLayout.LayoutParams(-1, -2));
        this.binding = LayoutFeeResourceDetailBinding.bind(inflate);
        mappingId();
    }

    private void mappingId() {
        this.rlMultiFee = this.binding.rlMultiFee;
        this.tvMultiName = this.binding.tvLeftMultiFee;
        this.tvMultiFee = this.binding.tvRightMultiFee;
        this.tvActiveAccount = this.binding.tvRightActiveAccount;
        this.tvLeftActivateAccount = this.binding.tvLeftActiveAccount;
        this.rlMemoFee = this.binding.rlMemoFee;
        this.tvMemoFee = this.binding.tvRightMemoFee;
        this.tvLeftMemoFee = this.binding.tvLeftMemoFee;
        this.tvBandwidthInTrx = this.binding.tvRightDeductBw;
        this.tvBwCount = this.binding.tvLeftDeductBw;
        this.tvEnergyCount = this.binding.tvLeftDeductEnergy;
        this.tvEnergyInTrx = this.binding.tvRightDeductEnergy;
        this.rlResourceBw = this.binding.rlResourceBandwidth;
        this.rlResourceEnergy = this.binding.rlResourceEnergy;
        this.rlFee = this.binding.rlFee;
    }

    public void bindData(com.tron.wallet.business.confirm.parambuilder.bean.BaseParam r11, com.tron.wallet.business.confirm.fg.bean.ResourceConsumedBean r12, java.lang.Number[][] r13) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.common.components.FeeResourceDetailView.bindData(com.tron.wallet.business.confirm.parambuilder.bean.BaseParam, com.tron.wallet.business.confirm.fg.bean.ResourceConsumedBean, java.lang.Number[][]):void");
    }

    private void bindKeyValue(Number[] numberArr, boolean z, int i, View view, TextView... textViewArr) {
        String formatNumber3Truncate;
        if (BigDecimalUtils.lessThanOrEqual(numberArr[0], 0)) {
            view.setVisibility(View.GONE);
            return;
        }
        view.setVisibility(View.VISIBLE);
        try {
            formatNumber3Truncate = NumUtils.numFormatToK(numberArr[0].longValue());
        } catch (Throwable th) {
            LogUtils.e(th);
            formatNumber3Truncate = StringTronUtil.formatNumber3Truncate(numberArr[0]);
        }
        textViewArr[0].setText(getContext().getString(i, formatNumber3Truncate));
        TextView textView = textViewArr[1];
        Object[] objArr = new Object[2];
        objArr[0] = z ? "≈" : "";
        objArr[1] = StringTronUtil.formatNumberTruncateNoDots(Double.valueOf(numberArr[1].doubleValue()), 5);
        textView.setText(String.format("%s%s TRX", objArr));
    }
}
