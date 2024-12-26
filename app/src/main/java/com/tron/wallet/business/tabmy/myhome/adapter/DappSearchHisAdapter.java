package com.tron.wallet.business.tabmy.myhome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.wallet.business.tabdapp.bean.DappLocalSearchBean;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class DappSearchHisAdapter extends RecyclerView.Adapter {
    public static final int BOTTOM = 1;
    public static final int TOP = 0;
    private DappSearchHisBottomHolder dappSearchHisBottomHolder;
    private DappSearchHisTopHolder dappSearchHisTopHolder;
    private Context mContext;
    private List<DappLocalSearchBean.DataBean> mLists;

    @Override
    public int getItemViewType(int i) {
        return (i != 0 && i == 1) ? 1 : 0;
    }

    public DappSearchHisAdapter(List<DappLocalSearchBean.DataBean> list, Context context) {
        this.mLists = list;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 0) {
            DappSearchHisTopHolder dappSearchHisTopHolder = new DappSearchHisTopHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_dapp_search_top, (ViewGroup) null), this.mContext);
            this.dappSearchHisTopHolder = dappSearchHisTopHolder;
            return dappSearchHisTopHolder;
        } else if (i != 1) {
            return null;
        } else {
            DappSearchHisBottomHolder dappSearchHisBottomHolder = new DappSearchHisBottomHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_dapp_search_bottom, viewGroup, false));
            this.dappSearchHisBottomHolder = dappSearchHisBottomHolder;
            return dappSearchHisBottomHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof DappSearchHisTopHolder) {
            ((DappSearchHisTopHolder) viewHolder).onBind(this.mLists);
        }
    }

    @Override
    public int getItemCount() {
        List<DappLocalSearchBean.DataBean> list = this.mLists;
        if (list == null) {
            return 0;
        }
        return list.size() > 1 ? 2 : 1;
    }

    public void notifyData(List<DappLocalSearchBean.DataBean> list) {
        this.mLists = list;
        notifyDataSetChanged();
    }

    public void onDestroy() {
        DappSearchHisTopHolder dappSearchHisTopHolder = this.dappSearchHisTopHolder;
        if (dappSearchHisTopHolder != null) {
            dappSearchHisTopHolder.onDestroy();
        }
        DappSearchHisBottomHolder dappSearchHisBottomHolder = this.dappSearchHisBottomHolder;
        if (dappSearchHisBottomHolder != null) {
            dappSearchHisBottomHolder.onDestroy();
        }
    }
}
