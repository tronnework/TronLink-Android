package com.tron.wallet.common.adapter.holder.swap;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.components.LoadingView;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.DateUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.ItemSwapMainRecordBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.bean.JustSwapBean;
import com.tronlinkpro.wallet.R;
public class RecordListHolder extends BaseSwapAdapterHolder {
    View innerLayout;
    LinearLayout ll_pending;
    LoadingView loadingView;
    View rootLayout;
    TextView tvConsumeCount;
    TextView tvConsumeName;
    TextView tvFailed;
    TextView tvObtainCount;
    TextView tvObtainName;
    TextView tvRate;
    TextView tvSuccess;
    TextView tvdata;

    public RecordListHolder(Context context) {
        super(context, R.layout.item_swap_main_record);
        ItemSwapMainRecordBinding bind = ItemSwapMainRecordBinding.bind(this.itemView);
        this.rootLayout = bind.root;
        this.innerLayout = bind.rlInner;
        this.tvConsumeName = bind.tokenConsumeName;
        this.tvObtainName = bind.tokenObtainName;
        this.tvConsumeCount = bind.tokenConsumeCount;
        this.tvObtainCount = bind.tokenObtainCount;
        this.tvdata = bind.tvDate;
        this.tvFailed = bind.tvFailed;
        this.tvSuccess = bind.tvSuccess;
        this.ll_pending = bind.llPending;
        this.loadingView = bind.ivPending;
        this.tvRate = bind.tvRate;
    }

    @Override
    public void onBind(final JustSwapBean justSwapBean, int i, int i2) {
        double pow;
        String formatNumberTruncateNoDots;
        super.onBind();
        this.tvConsumeName.setText(justSwapBean.getSymbolIn());
        this.tvConsumeCount.setText(StringTronUtil.formatNumberTruncateNoDots(justSwapBean.getAmountIn(), BigDecimalUtils.toBigDecimal(justSwapBean.getDecimalsIn()).intValue()));
        this.tvObtainName.setText(justSwapBean.getSymbolOut());
        this.tvObtainCount.setText(StringTronUtil.formatNumberTruncateNoDots(justSwapBean.getAmountout(), BigDecimalUtils.toBigDecimal(justSwapBean.getDecimalsOut()).intValue()));
        if (justSwapBean.getStatus() == 1) {
            this.ll_pending.setVisibility(View.INVISIBLE);
            this.tvSuccess.setVisibility(View.VISIBLE);
            this.tvFailed.setVisibility(View.GONE);
        } else if (justSwapBean.getStatus() == 2) {
            this.tvFailed.setVisibility(View.VISIBLE);
            this.ll_pending.setVisibility(View.INVISIBLE);
            this.tvSuccess.setVisibility(View.GONE);
        } else {
            this.ll_pending.setVisibility(View.VISIBLE);
            this.loadingView.updateState(LoadingView.State.LOADING);
            this.loadingView.setImageResource(R.mipmap.transfer_state_pending);
            this.tvFailed.setVisibility(View.GONE);
            this.tvSuccess.setVisibility(View.GONE);
        }
        this.tvdata.setText(DateUtils.diffLanguageDate(justSwapBean.getTimestamp()));
        String rate = justSwapBean.getRate();
        try {
            if (BigDecimalUtils.LessThan(rate, Double.valueOf(Math.pow(10.0d, BigDecimalUtils.toBigDecimal(justSwapBean.getDecimalsOut()).doubleValue() * (-1.0d))))) {
                formatNumberTruncateNoDots = "< " + StringTronUtil.plainScientificNotation(Double.valueOf(pow));
            } else {
                formatNumberTruncateNoDots = StringTronUtil.formatNumberTruncateNoDots(rate, BigDecimalUtils.toBigDecimal(justSwapBean.getDecimalsOut()).intValue());
            }
            rate = formatNumberTruncateNoDots;
        } catch (Exception e) {
            LogUtils.e(e);
        }
        this.tvRate.setText(String.format(this.context.getString(R.string.swap_record_rate), rate));
        this.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$onBind$0(justSwapBean, view);
            }
        });
    }

    public void lambda$onBind$0(JustSwapBean justSwapBean, View view) {
        CommonWebActivityV3.start(this.context, getUrl(justSwapBean), this.context.getString(R.string.transfer_doc), -2, true);
    }

    private String getUrl(JustSwapBean justSwapBean) {
        String str;
        if (SpAPI.THIS.getCurrentChain() != null && SpAPI.THIS.getCurrentChain().isMainChain) {
            str = TronConfig.TRONSCANHOST_MAINCHAIN + justSwapBean.getHash();
        } else {
            str = TronConfig.TRONSCANHOST_DAPPCHAIN + justSwapBean.getHash();
        }
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return str + "?lang=zh";
        }
        return str + "?lang=en";
    }
}
