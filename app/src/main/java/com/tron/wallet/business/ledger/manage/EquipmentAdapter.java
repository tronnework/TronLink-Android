package com.tron.wallet.business.ledger.manage;

import android.content.Context;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tronlinkpro.wallet.R;
public class EquipmentAdapter extends BaseQuickAdapter<EquipmentBean, EquipmentItemHolder> {
    private EquipmentItemListener listener;
    private Context mContext;
    private TYPE type;

    public enum TYPE {
        MANAGE,
        TRANSFER,
        SEARCH
    }

    public EquipmentAdapter(Context context, TYPE type, EquipmentItemListener equipmentItemListener) {
        super((int) R.layout.item_equipment);
        this.mContext = context;
        this.listener = equipmentItemListener;
        this.type = type;
    }

    @Override
    public void convert(final EquipmentItemHolder equipmentItemHolder, final EquipmentBean equipmentBean) {
        equipmentItemHolder.onBind(this.mContext, equipmentBean, this.type);
        equipmentItemHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (equipmentBean.isConnected()) {
                    return;
                }
                listener.itemClick(equipmentItemHolder.getAdapterPosition(), equipmentBean);
            }
        });
        equipmentItemHolder.tvImportAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.importAddress(equipmentBean.getDevice());
            }
        });
        equipmentItemHolder.rl_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.edit(equipmentItemHolder.getLayoutPosition());
            }
        });
        equipmentItemHolder.tvDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.disconnectEquipment(equipmentBean.getDevice(), equipmentItemHolder.getLayoutPosition());
            }
        });
    }
}
