package com.tron.wallet.common.components;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.core.BottomPopupView;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.tabswap.bean.SwapInfoOutput;
import com.tron.wallet.business.tabswap.utils.SwapFeeHelper;
import com.tron.wallet.business.tabswap.utils.SwapTokenIconHelper;
import com.tron.wallet.business.tabswap.utils.SwapTokenSymbolHelper;
import com.tron.wallet.common.components.flowlayout.FlowLayout;
import com.tron.wallet.common.components.flowlayout.TagAdapter;
import com.tron.wallet.common.components.flowlayout.TagFlowLayout;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.functions.Consumer;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
public class SwapRouterDialog extends BottomPopupView {
    private static WeakReference<BasePopupView> popupViewRef;
    private final Consumer<Integer> onSelectNewRouter;
    private int selectedIndex;
    private final SwapInfoOutput swapInfoOutput;

    @Override
    public int getImplLayoutId() {
        return R.layout.layout_swap_router_dialog;
    }

    public SwapRouterDialog(Context context, int i, SwapInfoOutput swapInfoOutput, Consumer<Integer> consumer) {
        super(context);
        this.swapInfoOutput = swapInfoOutput;
        this.onSelectNewRouter = consumer;
        this.selectedIndex = i;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_router);
        recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getContext(), 1, false));
        final RouterAdapter routerAdapter = new RouterAdapter(this.swapInfoOutput.getData(), this.selectedIndex);
        routerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                lambda$onCreate$1(routerAdapter, baseQuickAdapter, view, i);
            }
        });
        recyclerView.setAdapter(routerAdapter);
        findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$onCreate$2(view);
            }
        });
    }

    public void lambda$onCreate$1(RouterAdapter routerAdapter, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        if (this.selectedIndex == i) {
            return;
        }
        this.selectedIndex = i;
        routerAdapter.updateSelectedIndex(i);
        Consumer<Integer> consumer = this.onSelectNewRouter;
        if (consumer != null) {
            try {
                consumer.accept(Integer.valueOf(i));
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
        view.postDelayed(new Runnable() {
            @Override
            public final void run() {
                lambda$onCreate$0();
            }
        }, 500L);
    }

    public void lambda$onCreate$0() {
        dismiss();
    }

    public void lambda$onCreate$2(View view) {
        dismiss();
    }

    @Override
    public int getPopupHeight() {
        return (int) (UIUtils.getScreenHeight(getContext()) * 0.6f);
    }

    public static void showDialog(Context context, int i, SwapInfoOutput swapInfoOutput, Consumer<Integer> consumer) {
        popupViewRef = new WeakReference<>(new XPopup.Builder(context).enableDrag(false).asCustom(new SwapRouterDialog(context, i, swapInfoOutput, consumer)).show());
    }

    public static void dismissDialog() {
        BasePopupView basePopupView;
        WeakReference<BasePopupView> weakReference = popupViewRef;
        if (weakReference == null || (basePopupView = weakReference.get()) == null || basePopupView.isDismiss()) {
            return;
        }
        basePopupView.dismiss();
    }

    public static class RouterTag {
        String fee;
        Object iconUrl;
        String poolVersion;
        String symbol;
        String tokenAddress;
        boolean trx2Wtrx;

        private RouterTag() {
            this.trx2Wtrx = false;
        }
    }

    public static class RouterItemHolder extends BaseViewHolder {
        TagFlowLayout flowLayout;
        private final ImageView ivSelect;
        private final TextView tvOutputAmount;
        private final TextView tvSwapOut;
        private final TextView tvSymbol;
        private final TextView tvTitle;

        public RouterItemHolder(View view) {
            super(view);
            this.flowLayout = (TagFlowLayout) view.findViewById(R.id.fl_router);
            this.tvSwapOut = (TextView) view.findViewById(R.id.tv_swap_out);
            this.tvOutputAmount = (TextView) view.findViewById(R.id.tv_output_amount);
            this.tvSymbol = (TextView) view.findViewById(R.id.tv_symbol);
            this.tvTitle = (TextView) view.findViewById(R.id.tv_title);
            this.ivSelect = (ImageView) view.findViewById(R.id.iv_select);
        }

        void updateSelectedState(boolean z) {
            this.ivSelect.setImageResource(z ? R.mipmap.iv_selected : R.mipmap.ic_select_normal);
        }

        void onBind(SwapInfoOutput.InfoData infoData, int i) {
            this.tvOutputAmount.setText(StringTronUtil.plainScientificNotation(BigDecimalUtils.toBigDecimal(infoData.getAmountOut())));
            this.tvSymbol.setText(infoData.getSymbols().get(infoData.getSymbols().size() - 1));
            updateSelectedState(i == getAbsoluteAdapterPosition());
            int absoluteAdapterPosition = getAbsoluteAdapterPosition();
            if (absoluteAdapterPosition == 0) {
                this.tvTitle.setText(R.string.swap_max_price);
            } else if (absoluteAdapterPosition == 1) {
                this.tvTitle.setText(R.string.swap_middle_price);
            } else if (absoluteAdapterPosition == 2) {
                this.tvTitle.setText(R.string.swap_min_price);
            } else {
                this.tvTitle.setText("");
            }
            ArrayList arrayList = new ArrayList();
            List<String> symbols = infoData.getSymbols();
            for (int i2 = 0; i2 < symbols.size(); i2++) {
                RouterTag routerTag = new RouterTag();
                routerTag.tokenAddress = infoData.getTokens().get(i2);
                routerTag.symbol = symbols.get(i2);
                routerTag.symbol = SwapTokenSymbolHelper.getSymbol(routerTag.symbol, routerTag.tokenAddress);
                if (i2 < symbols.size() - 1) {
                    routerTag.poolVersion = infoData.getPoolVersions().get(i2);
                    String upperCase = symbols.get(i2).toUpperCase();
                    String upperCase2 = symbols.get(i2 + 1).toUpperCase();
                    if ((TextUtils.equals("TRX", upperCase) && TextUtils.equals("WTRX", upperCase2)) || (TextUtils.equals("TRX", upperCase2) && TextUtils.equals("WTRX", upperCase))) {
                        routerTag.fee = "0";
                        routerTag.trx2Wtrx = true;
                    } else if (TextUtils.equals(routerTag.poolVersion, "v3")) {
                        routerTag.fee = StringTronUtil.formatNumber6Truncate(BigDecimalUtils.div_(infoData.getPoolFees().get(i2), 10000)) + "%";
                    } else {
                        routerTag.fee = SwapFeeHelper.getPoolFee(routerTag.poolVersion);
                        if (TextUtils.equals("0", routerTag.fee)) {
                            routerTag.fee = "0%";
                        }
                    }
                }
                routerTag.iconUrl = SwapTokenIconHelper.getInstance().findTokenIcon(this.itemView.getContext(), routerTag.symbol, routerTag.tokenAddress);
                arrayList.add(routerTag);
            }
            this.flowLayout.setAdapter(new RouterTagAdapter(arrayList));
        }
    }

    public static class RouterAdapter extends BaseQuickAdapter<SwapInfoOutput.InfoData, RouterItemHolder> {
        private int selectedIndex;

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List list) {
            onBindViewHolder((RouterItemHolder) viewHolder, i, (List<Object>) list);
        }

        public RouterAdapter(List<SwapInfoOutput.InfoData> list, int i) {
            super(R.layout.layout_item_swap_router, list);
            this.selectedIndex = i;
        }

        void updateSelectedIndex(int i) {
            this.selectedIndex = i;
            notifyItemRangeChanged(0, getItemCount(), Integer.valueOf(this.selectedIndex));
        }

        public void onBindViewHolder(RouterItemHolder routerItemHolder, int i, List<Object> list) {
            int intValue;
            if (list.isEmpty()) {
                super.onBindViewHolder((RouterAdapter) routerItemHolder, i);
                return;
            }
            if ((list.get(0) instanceof Integer) && (intValue = ((Integer) list.get(0)).intValue()) < getItemCount()) {
                routerItemHolder.updateSelectedState(intValue == routerItemHolder.getAbsoluteAdapterPosition());
            }
        }

        @Override
        public void convert(RouterItemHolder routerItemHolder, SwapInfoOutput.InfoData infoData) {
            routerItemHolder.onBind(infoData, this.selectedIndex);
        }
    }

    public static class RouterTagAdapter extends TagAdapter<RouterTag> {
        public RouterTagAdapter(List<RouterTag> list) {
            super(list);
        }

        @Override
        public View getView(FlowLayout flowLayout, int i, RouterTag routerTag) {
            RouterTag item = getItem(i);
            View inflate = LayoutInflater.from(flowLayout.getContext()).inflate(R.layout.swap_router_flowlayout_tag, (ViewGroup) flowLayout, false);
            TextView textView = (TextView) inflate.findViewById(R.id.tv_symbol);
            if (TextUtils.isEmpty(item.fee) || TextUtils.equals("0", item.fee)) {
                textView.setText(item.symbol);
            } else {
                textView.setText(item.symbol + " -" + item.fee);
            }
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) inflate.findViewById(R.id.iv_logo);
            if (item.iconUrl instanceof Integer) {
                simpleDraweeView.setImageResource(((Integer) item.iconUrl).intValue());
            } else if ((item.iconUrl instanceof String) && !TextUtils.isEmpty((String) item.iconUrl)) {
                simpleDraweeView.setImageURI(Uri.parse((String) item.iconUrl));
            } else {
                simpleDraweeView.setImageResource(R.mipmap.ic_token_default);
            }
            boolean z = i >= getCount() - 1;
            inflate.findViewById(R.id.iv_arrow_right).setVisibility(z ? View.GONE : View.VISIBLE);
            TextView textView2 = (TextView) inflate.findViewById(R.id.tv_pool_version);
            if (!z && !item.trx2Wtrx) {
                textView2.setVisibility(View.VISIBLE);
                textView2.setText(item.poolVersion);
            } else {
                textView2.setVisibility(View.GONE);
            }
            return inflate;
        }
    }
}
