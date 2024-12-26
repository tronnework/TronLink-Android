package com.tron.wallet.common.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.databinding.ItemAuthorizedDappBinding;
import com.tron.wallet.db.bean.DAppNonOfficialAuthorizedProjectBean;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class DappAuthorizedAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<DAppNonOfficialAuthorizedProjectBean> authorizedList;
    private SelectedListener selectedListener;

    public interface SelectedListener {
        void onSelectedListener(DAppNonOfficialAuthorizedProjectBean dAppNonOfficialAuthorizedProjectBean);
    }

    public DappAuthorizedAdapter(List<DAppNonOfficialAuthorizedProjectBean> list, SelectedListener selectedListener) {
        this.authorizedList = list;
        this.selectedListener = selectedListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_authorized_dapp, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        DAppNonOfficialAuthorizedProjectBean dAppNonOfficialAuthorizedProjectBean = this.authorizedList.get(viewHolder.getBindingAdapterPosition());
        viewHolder.tvAuthorizedUrl.setText(dAppNonOfficialAuthorizedProjectBean.getUrl());
        viewHolder.ivSelect.setBackgroundResource(dAppNonOfficialAuthorizedProjectBean.isSelected() ? R.mipmap.setting_select : R.mipmap.setting_unselect);
        viewHolder.ivSelect.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                DAppNonOfficialAuthorizedProjectBean dAppNonOfficialAuthorizedProjectBean2 = (DAppNonOfficialAuthorizedProjectBean) authorizedList.get(viewHolder.getBindingAdapterPosition());
                if (selectedListener != null) {
                    selectedListener.onSelectedListener(dAppNonOfficialAuthorizedProjectBean2);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        List<DAppNonOfficialAuthorizedProjectBean> list = this.authorizedList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void refreshData(List<DAppNonOfficialAuthorizedProjectBean> list) {
        this.authorizedList = list;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends BaseHolder {
        ItemAuthorizedDappBinding binding;
        ImageView ivSelect;
        TextView tvAuthorizedUrl;

        public ViewHolder(View view) {
            super(view);
            ItemAuthorizedDappBinding bind = ItemAuthorizedDappBinding.bind(view);
            this.binding = bind;
            this.tvAuthorizedUrl = bind.tvAuthorizedUrl;
            ImageView imageView = this.binding.ivSelect;
            this.ivSelect = imageView;
            TouchDelegateUtils.expandViewTouchDelegate(imageView, 10);
        }
    }
}
