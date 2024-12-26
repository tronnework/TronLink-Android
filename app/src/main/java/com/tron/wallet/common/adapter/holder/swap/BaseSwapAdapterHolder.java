package com.tron.wallet.common.adapter.holder.swap;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.tabswap.bean.SwapInfoOutput;
import com.tron.wallet.business.tabswap.bean.SwapTokenBean;
import com.tron.wallet.business.tabswap.bean.SwapTokenInfoBean;
import com.tron.wallet.db.bean.JustSwapBean;
import java.util.List;
public class BaseSwapAdapterHolder extends RecyclerView.ViewHolder {
    protected Context context;
    protected RxManager rxManager;

    public void onBind() {
    }

    public void onBind(Pair<SwapTokenBean, SwapTokenBean> pair) {
    }

    public void onBind(Pair<SwapTokenBean, SwapTokenBean> pair, SwapTokenInfoBean swapTokenInfoBean, List<SwapInfoOutput.InfoData> list, int i, View.OnClickListener onClickListener, boolean z) {
    }

    public void onBind(JustSwapBean justSwapBean, int i, int i2) {
    }

    public void onBind(boolean z) {
    }

    public void onBindButton(boolean z, View.OnClickListener onClickListener) {
    }

    public void setRxManager(RxManager rxManager) {
        this.rxManager = rxManager;
    }

    public BaseSwapAdapterHolder(Context context, int i) {
        this(LayoutInflater.from(context).inflate(i, (ViewGroup) null));
        this.context = context;
    }

    public BaseSwapAdapterHolder(View view) {
        super(view);
    }
}
