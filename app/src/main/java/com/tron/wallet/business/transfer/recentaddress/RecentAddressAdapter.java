package com.tron.wallet.business.transfer.recentaddress;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.common.bean.NameAddressBean;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TextDotUtils;
import com.tron.wallet.databinding.ItemRecentAddressSingleTextviewBinding;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
public class RecentAddressAdapter extends RecyclerView.Adapter<ViewHolder> {
    private ArrayList<NameAddressBean> addressDataBeans;
    private Context mContext;

    public RecentAddressAdapter(Context context, ArrayList<NameAddressBean> arrayList) {
        this.addressDataBeans = arrayList;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recent_address_single_textview, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        NameAddressBean nameAddressBean = this.addressDataBeans.get(i);
        if (StringTronUtil.isNullOrEmpty(nameAddressBean.getAddress())) {
            return;
        }
        TextDotUtils.getTextDisplayWithMidDot(viewHolder.display, nameAddressBean.getName(), nameAddressBean.getAddress(), false);
    }

    @Override
    public int getItemCount() {
        ArrayList<NameAddressBean> arrayList = this.addressDataBeans;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public void notifyData(ArrayList<NameAddressBean> arrayList) {
        this.addressDataBeans = arrayList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends BaseHolder {
        ItemRecentAddressSingleTextviewBinding binding;
        TextView display;

        public ViewHolder(View view) {
            super(view);
            ItemRecentAddressSingleTextviewBinding bind = ItemRecentAddressSingleTextviewBinding.bind(view);
            this.binding = bind;
            this.display = bind.tv;
        }
    }
}
