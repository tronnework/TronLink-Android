package com.tron.wallet.business.tabmy.myhome.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.wallet.business.tabdapp.bean.DappLocalSearchBean;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.ItemIDappSearchHisBinding;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class DappSearchHisInnerAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<DappLocalSearchBean.DataBean> mLists;

    public DappSearchHisInnerAdapter(List<DappLocalSearchBean.DataBean> list) {
        this.mLists = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_i_dapp_search_his, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        List<DappLocalSearchBean.DataBean> list = this.mLists;
        if (list == null || list.size() == 0) {
            return;
        }
        DappLocalSearchBean.DataBean dataBean = this.mLists.get(i);
        if (i == 0) {
            viewHolder.ivLeft.setBackgroundResource(R.mipmap.dapp_hot);
            viewHolder.tvDapp.setTextColor(AppContextUtil.getContext().getResources().getColor(R.color.blue_13));
            viewHolder.rlContent.setPadding(0, UIUtils.dip2px(10.0f), 0, UIUtils.dip2px(16.0f));
        } else {
            viewHolder.tvDapp.setTextColor(AppContextUtil.getContext().getResources().getColor(R.color.black_50));
            viewHolder.ivLeft.setBackgroundResource(R.mipmap.dapp_his);
            viewHolder.rlContent.setPadding(0, UIUtils.dip2px(16.0f), 0, UIUtils.dip2px(16.0f));
        }
        viewHolder.tvDapp.setText(dataBean.url);
    }

    @Override
    public int getItemCount() {
        List<DappLocalSearchBean.DataBean> list = this.mLists;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void notifyData(List<DappLocalSearchBean.DataBean> list) {
        this.mLists = list;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends BaseHolder {
        private ItemIDappSearchHisBinding binding;
        ImageView ivLeft;
        RelativeLayout rlContent;
        TextView tvDapp;
        View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            mappingId(view);
        }

        public void mappingId(View view) {
            ItemIDappSearchHisBinding bind = ItemIDappSearchHisBinding.bind(view);
            this.ivLeft = bind.ivLeft;
            this.tvDapp = bind.tvDapp;
            this.rlContent = bind.rlCentent;
        }
    }
}
