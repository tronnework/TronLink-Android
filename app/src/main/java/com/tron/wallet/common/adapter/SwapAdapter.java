package com.tron.wallet.common.adapter;

import android.content.Context;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.tabswap.bean.SwapInfoOutput;
import com.tron.wallet.business.tabswap.bean.SwapTokenBean;
import com.tron.wallet.business.tabswap.bean.SwapTokenInfoBean;
import com.tron.wallet.common.adapter.holder.swap.BaseSwapAdapterHolder;
import com.tron.wallet.common.adapter.holder.swap.NoRecordHolder;
import com.tron.wallet.common.adapter.holder.swap.RecordListHolder;
import com.tron.wallet.common.adapter.holder.swap.SwapInstantHolder;
import com.tron.wallet.common.adapter.holder.swap.TitleRecordHolder;
import com.tron.wallet.common.adapter.holder.swap.TokenHolder;
import com.tron.wallet.common.adapter.holder.swap.TokenInfoHolder;
import com.tron.wallet.common.adapter.holder.swap.ViewTokenHolder;
import com.tron.wallet.db.bean.JustSwapBean;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.List;
public class SwapAdapter extends RecyclerView.Adapter<BaseSwapAdapterHolder> {
    public static final int COUNT_EXTRA = 5;
    private static final int TYPE_NO_RECORD = 6;
    private static final int TYPE_RECORD_LIST = 5;
    private static final int TYPE_RECORD_LOADING = 7;
    private static final int TYPE_SWAP_INSTANT = 3;
    private static final int TYPE_TITLE_RECORD = 4;
    private static final int TYPE_TOKEN = 0;
    private static final int TYPE_TOKEN_INFO = 2;
    private static final int TYPE_VIEW_TOKEN = 1;
    private Pair<SwapTokenBean, SwapTokenBean> headerTokens;
    private TokenHolder.OnAmountChangedListener onAmountChangedCallback;
    private View.OnClickListener onClickSwapRouter;
    private View.OnClickListener onConfirmButtonClick;
    private TokenHolder.OnTokenChangedCallback onTokenChangedCallback;
    private List<JustSwapBean> records;
    private int routerIndex;
    private final RxManager rxManager;
    private SwapTokenInfoBean swapInfo;
    private SwapInstantHolder swapInstantHolder;
    private List<SwapInfoOutput.InfoData> swapRouterList;
    private TokenHolder tokenHolder;
    private TokenInfoHolder tokenInfoHolder;
    private boolean infoVisible = false;
    private boolean hasMoreRecord = false;
    private boolean btnEnabled = false;

    @Override
    public void onBindViewHolder(BaseSwapAdapterHolder baseSwapAdapterHolder, int i) {
    }

    public void setOnAmountChangedCallback(TokenHolder.OnAmountChangedListener onAmountChangedListener) {
        this.onAmountChangedCallback = onAmountChangedListener;
    }

    public void setOnConfirmButtonClickListener(View.OnClickListener onClickListener) {
        this.onConfirmButtonClick = onClickListener;
    }

    public void setOnTokenChangedListener(TokenHolder.OnTokenChangedCallback onTokenChangedCallback) {
        this.onTokenChangedCallback = onTokenChangedCallback;
    }

    @Override
    public void onBindViewHolder(BaseSwapAdapterHolder baseSwapAdapterHolder, int i, List list) {
        onBindViewHolder(baseSwapAdapterHolder, i, (List<Object>) list);
    }

    public SwapAdapter(RxManager rxManager) {
        this.rxManager = rxManager;
    }

    @Override
    public BaseSwapAdapterHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int itemViewType = getItemViewType(i);
        if (itemViewType == 0) {
            TokenHolder tokenHolder = new TokenHolder(context);
            this.tokenHolder = tokenHolder;
            tokenHolder.setRxManager(this.rxManager);
            this.tokenHolder.setAmountChangedCallback(this.onAmountChangedCallback);
            this.tokenHolder.setTokenChangedCallback(this.onTokenChangedCallback);
            return this.tokenHolder;
        } else if (itemViewType != 1) {
            if (itemViewType == 2) {
                TokenInfoHolder tokenInfoHolder = new TokenInfoHolder(context);
                this.tokenInfoHolder = tokenInfoHolder;
                return tokenInfoHolder;
            } else if (itemViewType == 3) {
                SwapInstantHolder swapInstantHolder = new SwapInstantHolder(context, R.layout.swap_instant_swap);
                this.swapInstantHolder = swapInstantHolder;
                swapInstantHolder.setRxManager(this.rxManager);
                return this.swapInstantHolder;
            } else if (itemViewType != 4) {
                if (itemViewType == 6) {
                    return new NoRecordHolder(context, R.layout.swap_no_record);
                }
                return new RecordListHolder(context);
            } else {
                return new TitleRecordHolder(context, R.layout.swap_title_record);
            }
        } else {
            return new ViewTokenHolder(context);
        }
    }

    @Override
    public int getItemCount() {
        List<JustSwapBean> list = this.records;
        if (list == null) {
            return 5;
        }
        if (list.isEmpty()) {
            return 6;
        }
        return this.records.size() + 5;
    }

    @Override
    public int getItemViewType(int i) {
        if (i != 0) {
            int i2 = 1;
            if (i != 1) {
                i2 = 2;
                if (i != 2) {
                    i2 = 3;
                    if (i != 3) {
                        i2 = 4;
                        if (i != 4) {
                            List<JustSwapBean> list = this.records;
                            return (list == null || list.isEmpty()) ? 6 : 5;
                        }
                    }
                }
            }
            return i2;
        }
        return 0;
    }

    public void onBindViewHolder(BaseSwapAdapterHolder baseSwapAdapterHolder, int i, List<Object> list) {
        baseSwapAdapterHolder.onBind();
        switch (getItemViewType(i)) {
            case 0:
                if (!list.isEmpty()) {
                    try {
                        ((TokenHolder) baseSwapAdapterHolder).notifyPayloads(list);
                        return;
                    } catch (Throwable th) {
                        LogUtils.e(th);
                        baseSwapAdapterHolder.onBind(this.headerTokens);
                        return;
                    }
                }
                baseSwapAdapterHolder.onBind(this.headerTokens);
                return;
            case 1:
                baseSwapAdapterHolder.onBind(this.headerTokens);
                return;
            case 2:
                if (list.isEmpty()) {
                    baseSwapAdapterHolder.onBind(this.headerTokens, this.swapInfo, this.swapRouterList, this.routerIndex, this.onClickSwapRouter, this.infoVisible);
                    return;
                }
                try {
                    ((TokenInfoHolder) baseSwapAdapterHolder).notifyPayLoads(list);
                    return;
                } catch (Throwable th2) {
                    th2.printStackTrace();
                    return;
                }
            case 3:
                baseSwapAdapterHolder.onBindButton(this.btnEnabled, this.onConfirmButtonClick);
                return;
            case 4:
                baseSwapAdapterHolder.onBind(this.hasMoreRecord);
                return;
            case 5:
                RecordListHolder recordListHolder = (RecordListHolder) baseSwapAdapterHolder;
                List<JustSwapBean> list2 = this.records;
                if (list2 == null || list2.isEmpty()) {
                    return;
                }
                int i2 = i - 5;
                recordListHolder.onBind(this.records.get(i2), i2, this.records.size());
                return;
            case 6:
                ((NoRecordHolder) baseSwapAdapterHolder).onBind();
                return;
            default:
                return;
        }
    }

    public void notifyTokenHeader(Pair<SwapTokenBean, SwapTokenBean> pair, boolean z) {
        this.headerTokens = pair;
        this.infoVisible = z;
        notifyItemRangeChanged(0, 4);
    }

    public void notifySwapValue(int i, String str, String str2) {
        ((SwapTokenBean) this.headerTokens.first).setInputAmount(str);
        ((SwapTokenBean) this.headerTokens.second).setInputAmount(str2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(3);
        arrayList.add(Integer.valueOf(i));
        arrayList.add(str);
        arrayList.add(str2);
        notifyItemChanged(0, arrayList);
    }

    public void notifyRecords(List<JustSwapBean> list, boolean z) {
        int size;
        this.hasMoreRecord = z;
        notifyItemChanged(4);
        List<JustSwapBean> list2 = this.records;
        if (list2 == null) {
            this.records = new ArrayList();
            size = 0;
        } else {
            size = list2.size();
            this.records.clear();
        }
        if (list != null) {
            notifyItemRangeRemoved(5, size);
            this.records.addAll(list);
            notifyItemRangeChanged(5, this.records.size());
        }
    }

    public int getRecordsCount() {
        List<JustSwapBean> list = this.records;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public void notifySwapTokenInfo(SwapTokenInfoBean swapTokenInfoBean, List<SwapInfoOutput.InfoData> list, int i, View.OnClickListener onClickListener, boolean z) {
        this.swapInfo = swapTokenInfoBean;
        this.swapRouterList = list;
        this.routerIndex = i;
        this.onClickSwapRouter = onClickListener;
        this.infoVisible = z;
        notifyItemChanged(2);
    }

    public void notifySwapInfoVisible(boolean z) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(Boolean.valueOf(z));
        notifyItemChanged(2, arrayList);
    }

    public void notifySwapInfoLoadingState(boolean z) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(2);
        arrayList.add(Boolean.valueOf(z));
        notifyItemChanged(2, arrayList);
    }

    public void updateBalance(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(str);
        arrayList.add(str2);
        notifyItemChanged(0, arrayList);
    }

    public void updateButtonEnabled(boolean z) {
        this.btnEnabled = z;
        notifyItemChanged(3);
    }
}
