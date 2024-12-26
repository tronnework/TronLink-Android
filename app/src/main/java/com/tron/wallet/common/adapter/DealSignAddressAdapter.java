package com.tron.wallet.common.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.bean.DealSignOutput;
import com.tron.wallet.databinding.ItemSignAddressBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.util.List;
import org.tron.walletserver.Wallet;
public class DealSignAddressAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context mContext;
    private String mWalletName;
    private List<DealSignOutput.DataBeanX.DataBean.SignatureProgressBean> progressBeanList;

    public DealSignAddressAdapter(Context context, List<DealSignOutput.DataBeanX.DataBean.SignatureProgressBean> list, String str) {
        this.progressBeanList = list;
        this.mContext = context;
        this.mWalletName = str;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sign_address, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        DealSignOutput.DataBeanX.DataBean.SignatureProgressBean signatureProgressBean;
        List<DealSignOutput.DataBeanX.DataBean.SignatureProgressBean> list = this.progressBeanList;
        if (list == null || list.size() <= 0 || (signatureProgressBean = this.progressBeanList.get(i)) == null) {
            return;
        }
        try {
            TextView textView = viewHolder.addressWeight;
            textView.setText(signatureProgressBean.weight + "");
            Wallet wallet = WalletUtils.getWallet(this.mWalletName);
            if (!TextUtils.isEmpty(this.mWalletName) && wallet != null && signatureProgressBean.address.equals(wallet.address)) {
                TextView textView2 = viewHolder.signAddress;
                textView2.setText(signatureProgressBean.address + this.mContext.getResources().getString(R.string.address_me));
            } else {
                viewHolder.signAddress.setText(signatureProgressBean.address);
            }
            if (signatureProgressBean.isSign == 1) {
                int color = this.mContext.getResources().getColor(R.color.blue_3c);
                viewHolder.signAddress.setTextColor(color);
                viewHolder.addressWeight.setTextColor(color);
                viewHolder.signState.setImageDrawable(this.mContext.getResources().getDrawable(R.mipmap.ic_deal_sign_item_signed));
                return;
            }
            int color2 = this.mContext.getResources().getColor(R.color.gray_9B);
            viewHolder.signAddress.setTextColor(color2);
            viewHolder.addressWeight.setTextColor(color2);
            viewHolder.signState.setImageDrawable(this.mContext.getResources().getDrawable(R.mipmap.ic_deal_sign_item_signing));
        } catch (Resources.NotFoundException e) {
            LogUtils.e(e);
        }
    }

    @Override
    public int getItemCount() {
        List<DealSignOutput.DataBeanX.DataBean.SignatureProgressBean> list = this.progressBeanList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public static class ViewHolder extends BaseHolder {
        TextView addressWeight;
        ItemSignAddressBinding binding;
        TextView signAddress;
        ImageView signState;

        public ViewHolder(View view) {
            super(view);
            ItemSignAddressBinding bind = ItemSignAddressBinding.bind(view);
            this.binding = bind;
            this.signAddress = bind.signAddress;
            this.signState = this.binding.signState;
            this.addressWeight = this.binding.addressWeight;
        }
    }
}
