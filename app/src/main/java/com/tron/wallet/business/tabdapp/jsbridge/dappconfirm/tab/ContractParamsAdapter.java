package com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.tab;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.wallet.databinding.ItemDappContractParamsAdapterBinding;
import com.tronlinkpro.wallet.R;
import java.util.List;
import org.tron.walletserver.TriggerData;
public class ContractParamsAdapter extends BaseQuickAdapter<TriggerData.TypeValue, ViewHolder> {
    List<TriggerData.TypeValue> data;

    public ContractParamsAdapter(Context context) {
        super((int) R.layout.item_dapp_contract_params_adapter);
        this.mContext = context;
    }

    @Override
    public void convert(ViewHolder viewHolder, TriggerData.TypeValue typeValue) {
        TextView textView = viewHolder.tvType;
        textView.setText(typeValue.getType() + ":");
        viewHolder.tvValue.setText(typeValue.getValue());
    }

    public void notifyData(List<TriggerData.TypeValue> list) {
        this.data = list;
        setNewData(list);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends BaseViewHolder {
        private ItemDappContractParamsAdapterBinding binding;
        TextView tvType;
        TextView tvValue;

        public ViewHolder(View view) {
            super(view);
            mappingId(view);
        }

        public void mappingId(View view) {
            ItemDappContractParamsAdapterBinding bind = ItemDappContractParamsAdapterBinding.bind(view);
            this.tvType = bind.type;
            this.tvValue = bind.value;
        }
    }
}
