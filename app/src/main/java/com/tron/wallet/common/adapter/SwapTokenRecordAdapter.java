package com.tron.wallet.common.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.components.CustomLoadMoreView;
import com.tron.wallet.common.components.LoadingView;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.DateUtils;
import com.tron.wallet.common.utils.DensityUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.ItemSwapTokenRecordBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.bean.JustSwapBean;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class SwapTokenRecordAdapter extends BaseQuickAdapter<JustSwapBean, ViewHolder> {
    private Context context;
    private List<JustSwapBean> list;
    private RxManager rxManager;

    public SwapTokenRecordAdapter(Context context, List<JustSwapBean> list) {
        super(R.layout.item_swap_token_record, list);
        this.context = context;
        this.list = list;
        this.rxManager = new RxManager();
        setLoadMoreView(new CustomLoadMoreView());
        setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                rxManager.post(Event.DAPPLIST_TO_WEBVIEW, getItem(i));
            }
        });
    }

    public void notifyData(List<JustSwapBean> list) {
        this.list = list;
        setNewData(list);
        notifyDataSetChanged();
    }

    @Override
    public void convert(ViewHolder viewHolder, final JustSwapBean justSwapBean) {
        int i;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewHolder.innerLayout.getLayoutParams();
        ViewGroup.LayoutParams layoutParams2 = viewHolder.rootLayout.getLayoutParams();
        if (this.list.size() == 1) {
            layoutParams.addRule(10);
            layoutParams.topMargin = DensityUtils.dp2px(this.context, 16.0f);
            layoutParams.bottomMargin = DensityUtils.dp2px(this.context, 16.0f);
            layoutParams2.height = DensityUtils.dp2px(this.context, 106.0f);
            i = R.drawable.shadow_swap_record_single;
        } else if (viewHolder.getAdapterPosition() == 0) {
            layoutParams.addRule(10);
            layoutParams.topMargin = DensityUtils.dp2px(this.context, 16.0f);
            layoutParams.bottomMargin = DensityUtils.dp2px(this.context, 5.0f);
            layoutParams2.height = DensityUtils.dp2px(this.context, 95.0f);
            i = R.drawable.shadow_swap_record_top;
        } else if (viewHolder.getAdapterPosition() == this.list.size() - 1) {
            layoutParams.addRule(10);
            layoutParams.topMargin = DensityUtils.dp2px(this.context, 5.0f);
            layoutParams.bottomMargin = DensityUtils.dp2px(this.context, 20.0f);
            layoutParams2.height = DensityUtils.dp2px(this.context, 99.0f);
            i = R.drawable.shadow_swap_record_bottom;
        } else {
            layoutParams.addRule(15);
            layoutParams.topMargin = DensityUtils.dp2px(this.context, 5.0f);
            layoutParams.bottomMargin = DensityUtils.dp2px(this.context, 5.0f);
            layoutParams2.height = DensityUtils.dp2px(this.context, 84.0f);
            i = R.drawable.shadow_swap_record_middle;
        }
        viewHolder.innerLayout.setLayoutParams(layoutParams);
        viewHolder.rootLayout.setBackgroundResource(i);
        viewHolder.rootLayout.setLayoutParams(layoutParams2);
        viewHolder.tvConsumeName.setText(justSwapBean.getSymbolIn());
        viewHolder.tvConsumeCount.setText(StringTronUtil.formatNumberTruncateNoDots(justSwapBean.getAmountIn(), BigDecimalUtils.toBigDecimal(justSwapBean.getDecimalsIn()).intValue()));
        viewHolder.tvObtainName.setText(justSwapBean.getSymbolOut());
        viewHolder.tvObtainCount.setText(StringTronUtil.formatNumberTruncateNoDots(justSwapBean.getAmountout(), BigDecimalUtils.toBigDecimal(justSwapBean.getDecimalsOut()).intValue()));
        viewHolder.tvdata.setText(DateUtils.diffLanguageDate(justSwapBean.getTimestamp()));
        if (justSwapBean.getStatus() == 1) {
            viewHolder.ll_pending.setVisibility(View.GONE);
            viewHolder.tvSuccess.setVisibility(View.VISIBLE);
            viewHolder.tvFailed.setVisibility(View.GONE);
        } else if (justSwapBean.getStatus() == 2) {
            viewHolder.tvFailed.setVisibility(View.VISIBLE);
            viewHolder.ll_pending.setVisibility(View.GONE);
            viewHolder.tvSuccess.setVisibility(View.GONE);
        } else {
            viewHolder.loadingView.updateState(LoadingView.State.LOADING);
            viewHolder.loadingView.setImageResource(R.mipmap.transfer_state_pending);
            viewHolder.tvFailed.setVisibility(View.GONE);
            viewHolder.tvSuccess.setVisibility(View.GONE);
        }
        viewHolder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$convert$0(justSwapBean, view);
            }
        });
    }

    public void lambda$convert$0(JustSwapBean justSwapBean, View view) {
        CommonWebActivityV3.start(this.mContext, getUrl(justSwapBean), this.mContext.getString(R.string.transfer_doc), -2, true);
    }

    public static class ViewHolder extends BaseViewHolder {
        ItemSwapTokenRecordBinding binding;
        View innerLayout;
        LinearLayout ll_pending;
        LoadingView loadingView;
        View rootLayout;
        TextView tvConsumeCount;
        TextView tvConsumeName;
        TextView tvFailed;
        TextView tvObtainCount;
        TextView tvObtainName;
        TextView tvSuccess;
        TextView tvdata;

        public ViewHolder(View view) {
            super(view);
            ItemSwapTokenRecordBinding bind = ItemSwapTokenRecordBinding.bind(view);
            this.binding = bind;
            this.rootLayout = bind.root;
            this.innerLayout = this.binding.rlInner;
            this.tvConsumeName = this.binding.tokenConsumeName;
            this.tvObtainName = this.binding.tokenObtainName;
            this.tvConsumeCount = this.binding.tokenConsumeCount;
            this.tvObtainCount = this.binding.tokenObtainCount;
            this.tvdata = this.binding.tvDate;
            this.tvFailed = this.binding.tvFailed;
            this.tvSuccess = this.binding.tvSuccess;
            this.ll_pending = this.binding.llPending;
            this.loadingView = this.binding.ivPending;
        }
    }

    public void onDestroy() {
        RxManager rxManager = this.rxManager;
        if (rxManager != null) {
            rxManager.clear();
        }
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
