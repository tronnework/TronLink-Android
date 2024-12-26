package com.tron.wallet.business.confirm.fg;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.fg.component.ArgumentKeys;
import com.tron.wallet.business.confirm.fg.component.GlobalConfirmButton;
import com.tron.wallet.business.confirm.fg.component.GlobalFeeResourceView;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.SwapParam;
import com.tron.wallet.business.tabswap.bean.SwapConfirmBean;
import com.tron.wallet.business.tabswap.bean.SwapTokenBean;
import com.tron.wallet.common.components.InfoPopupWindow;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.ImageUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.LayoutDialogSwapSubmitBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.io.Serializable;
import java.math.BigDecimal;
public class ConfirmSwapFragment extends BaseConfirmFragment<EmptyPresenter, EmptyModel> {
    public static final String KEY_DATA_BEAN = "data_bean";
    private LayoutDialogSwapSubmitBinding binding;
    GlobalConfirmButton btnSend;
    private SwapConfirmBean data;
    ImageView ivBack;
    ImageView ivClose;
    ImageView ivHelpFee;
    ImageView ivHelpMin;
    ImageView ivHelpPrice;
    SimpleDraweeView ivLogoFrom;
    SimpleDraweeView ivLogoTo;
    TextView minTips;
    private SwapParam param;
    GlobalFeeResourceView resourceView;
    private RxManager rxManager;
    TextView tvAmountFrom;
    TextView tvAmountTo;
    TextView tvFee;
    TextView tvMin;
    TextView tvMinReceived;
    TextView tvPriceImpact;
    TextView tvRate;

    public void setParam(SwapParam swapParam) {
        this.param = swapParam;
    }

    public static ConfirmSwapFragment getInstance(BaseParam baseParam) {
        ConfirmSwapFragment confirmSwapFragment = new ConfirmSwapFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ArgumentKeys.KEY_PARAM, baseParam);
        confirmSwapFragment.setArguments(bundle);
        return confirmSwapFragment;
    }

    protected void bindData(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        try {
            if (WalletUtils.getSelectedPublicWallet().getCreateType() == 8 && this.param.hasOwnerPermission()) {
                this.btnSend.setText(getString(R.string.request_from_ledger));
            } else {
                this.btnSend.setText(getString(R.string.confirm));
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
        this.rxManager = new RxManager();
        Serializable serializable = bundle.getSerializable(KEY_DATA_BEAN);
        if (serializable instanceof SwapConfirmBean) {
            this.data = (SwapConfirmBean) serializable;
        }
        TextView textView = this.tvPriceImpact;
        textView.setText(BigDecimalUtils.mul_((Object) this.data.getPriceImpact(), (Object) 100).stripTrailingZeros().toPlainString() + "%");
        TextView textView2 = this.tvAmountFrom;
        textView2.setText(getStringNoDecimal(this.data.getAmountLeft(), String.valueOf(this.data.getTokenFrom().getDecimal())) + " " + this.data.getTokenFrom().getSymbol());
        TextView textView3 = this.tvAmountTo;
        textView3.setText(getStringNoDecimal(this.data.getAmountRight(), String.valueOf(this.data.getTokenTo().getDecimal())) + " " + this.data.getTokenTo().getSymbol());
        setLogo(this.ivLogoFrom, this.data.getTokenFrom());
        setLogo(this.ivLogoTo, this.data.getTokenTo());
        try {
            this.tvFee.setText(String.format("%s %s", StringTronUtil.formatNumberTruncateNoDots(new BigDecimal(this.data.getFee()), this.data.getTokenFrom().getDecimal()), this.data.getTokenFrom().getSymbol()));
            if (this.data.getDirection() == 0) {
                String rate = this.data.getRate();
                int decimal = this.data.getTokenTo().getDecimal();
                double pow = Math.pow(10.0d, decimal * (-1));
                if (BigDecimalUtils.moreThanOrEqual(rate, Double.valueOf(pow))) {
                    this.tvRate.setText(String.format("1 %s = %s %s", this.data.getTokenFrom().getSymbol(), StringTronUtil.plainScientificNotation(BigDecimalUtils.toBigDecimal(rate), decimal), this.data.getTokenTo().getSymbol()));
                } else {
                    this.tvRate.setText(String.format("1 %s < %s %s", this.data.getTokenFrom().getSymbol(), StringTronUtil.plainScientificNotation(Double.valueOf(pow)), this.data.getTokenTo().getSymbol()));
                }
                this.tvMinReceived.setText(String.format("%s %s", getStringNoDecimal(this.data.getMinReceived(), String.valueOf(this.data.getTokenTo().getDecimal())), this.data.getTokenTo().getSymbol()));
                this.tvMin.setText(R.string.swap_min_received);
                TextView textView4 = this.minTips;
                String string = getString(R.string.swap_submit_tips);
                textView4.setText(String.format(string, getStringNoDecimal(this.data.getMinReceived(), String.valueOf(this.data.getTokenTo().getDecimal())) + " " + this.data.getTokenTo().getSymbol()));
            } else {
                this.tvMin.setText(R.string.swap_max_cost);
                this.tvRate.setText(String.format("1 %s=%s %s", this.data.getTokenFrom().getSymbol(), StringTronUtil.plainScientificNotation(BigDecimalUtils.div_(1, this.data.getRate()), this.data.getTokenTo().getDecimal()), this.data.getTokenTo().getSymbol()));
                this.tvMinReceived.setText(String.format("%s %s", getStringNoDecimal(this.data.getMinReceived(), String.valueOf(this.data.getTokenFrom().getDecimal())), this.data.getTokenFrom().getSymbol()));
                TextView textView5 = this.minTips;
                String string2 = getString(R.string.swap_submit_tips_max);
                textView5.setText(String.format(string2, getStringNoDecimal(this.data.getMinReceived(), String.valueOf(this.data.getTokenFrom().getDecimal())) + " " + this.data.getTokenFrom().getSymbol()));
            }
        } catch (Exception e2) {
            LogUtils.e(e2);
        }
        this.ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$bindData$0(view);
            }
        });
        this.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$bindData$1(view);
            }
        });
        this.ivHelpFee.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$bindData$2(view);
            }
        });
        this.ivHelpMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$bindData$3(view);
            }
        });
        this.ivHelpPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$bindData$4(view);
            }
        });
        int dip2px = UIUtils.dip2px(20.0f);
        TouchDelegateUtils.expandViewTouchDelegate(this.ivBack, dip2px, dip2px, dip2px, dip2px);
        TouchDelegateUtils.expandViewTouchDelegate(this.ivHelpFee, dip2px, dip2px, dip2px, dip2px);
        TouchDelegateUtils.expandViewTouchDelegate(this.ivHelpMin, dip2px, dip2px, dip2px, dip2px);
        TouchDelegateUtils.expandViewTouchDelegate(this.ivHelpPrice, dip2px, dip2px, dip2px, dip2px);
    }

    public void lambda$bindData$0(View view) {
        this.mContext.finish();
    }

    public void lambda$bindData$1(View view) {
        this.mContext.finish();
    }

    public void lambda$bindData$2(View view) {
        InfoPopupWindow.showPop(this.ivHelpFee, R.string.swap_fee_tip);
    }

    public void lambda$bindData$3(View view) {
        InfoPopupWindow.showPop(this.ivHelpMin, R.string.swap_question_min_received);
    }

    public void lambda$bindData$4(View view) {
        InfoPopupWindow.showPop(this.ivHelpPrice, R.string.swap_question_price_impact);
    }

    private void setLogo(SimpleDraweeView simpleDraweeView, SwapTokenBean swapTokenBean) {
        if (!TextUtils.isEmpty(swapTokenBean.getLogo())) {
            ImageUtils.loadAsCircle(simpleDraweeView, swapTokenBean.getLogo());
        } else if (swapTokenBean.isTrx()) {
            if (TextUtils.equals(swapTokenBean.getSymbol(), "TRX") || TextUtils.equals(swapTokenBean.getName(), "TRX")) {
                simpleDraweeView.setImageResource(R.mipmap.trx);
            }
        }
    }

    @Override
    public void processData() {
        super.processData();
        SwapParam swapParam = this.param;
        if (swapParam == null) {
            return;
        }
        SwapConfirmBean swapConfirmBean = swapParam.getSwapConfirmBean();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_DATA_BEAN, swapConfirmBean);
        bindData(bundle);
        addAccountCallback(this.resourceView, this.btnSend);
        this.resourceView.bindData(this.param);
        this.btnSend.onBind(this.param, true, "");
        this.resourceView.setFeeResourceCallback(this.btnSend);
        ensureAccount();
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LayoutDialogSwapSubmitBinding inflate = LayoutDialogSwapSubmitBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId() {
        this.ivLogoFrom = this.binding.imageTokenFrom;
        this.ivLogoTo = this.binding.imageTokenTo;
        this.tvAmountFrom = this.binding.textTokenFrom;
        this.tvAmountTo = this.binding.textTokenTo;
        this.tvRate = this.binding.tvRateRight;
        this.tvMinReceived = this.binding.tvMinReceivedRight;
        this.tvPriceImpact = this.binding.tvPriceImpactRight;
        this.tvFee = this.binding.tvFeeRight;
        this.btnSend = this.binding.send;
        this.resourceView = this.binding.resourceView;
        this.ivBack = this.binding.ivBack;
        this.ivClose = this.binding.ivClose;
        this.minTips = this.binding.tvMinTips;
        this.tvMin = this.binding.tvMin;
        this.ivHelpMin = this.binding.ivHelpMin;
        this.ivHelpPrice = this.binding.ivHelpPrice;
        this.ivHelpFee = this.binding.ivHelpFee;
    }

    private void send() {
        this.rxManager.post(Event.SWAP_CONFIRM_SWAP, "");
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$send$5();
            }
        });
    }

    public void lambda$send$5() {
        this.btnSend.setEnabled(true);
        dismissLoadingDialog();
        ConfirmTransactionNewActivity confirmTransactionNewActivity = (ConfirmTransactionNewActivity) getActivity();
        if (this.param.hasOwnerPermission()) {
            confirmTransactionNewActivity.JumpkTo(3);
        } else {
            confirmTransactionNewActivity.JumpkTo(2);
        }
    }

    private String getStringNoDecimal(String str, String str2) {
        try {
            return StringTronUtil.plainScientificNotation(BigDecimalUtils.toBigDecimal(str), Integer.parseInt(str2));
        } catch (Exception e) {
            LogUtils.e(e);
            return "";
        }
    }
}
