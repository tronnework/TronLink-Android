package com.tron.wallet.business.tronpower.stake.adapter;

import android.widget.CompoundButton;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tron.wallet.db.bean.AddressDao;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class SelectAddressAdapter extends BaseQuickAdapter<AddressDao, SelectAddressItemHolder> {
    private OnCheckedChangedListener listener;

    public interface OnCheckedChangedListener {
        void onCheckChanged(int i, boolean z);
    }

    public void setOnCheckChangedListener(OnCheckedChangedListener onCheckedChangedListener) {
        this.listener = onCheckedChangedListener;
    }

    public SelectAddressAdapter(List<AddressDao> list) {
        super((int) R.layout.item_select_address);
        this.mData = list;
    }

    @Override
    public void convert(final SelectAddressItemHolder selectAddressItemHolder, final AddressDao addressDao) {
        selectAddressItemHolder.onBind(addressDao);
        selectAddressItemHolder.cbCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                lambda$convert$0(selectAddressItemHolder, addressDao, compoundButton, z);
            }
        });
    }

    public void lambda$convert$0(SelectAddressItemHolder selectAddressItemHolder, AddressDao addressDao, CompoundButton compoundButton, boolean z) {
        if (selectAddressItemHolder.cbCheck.isEnabled()) {
            for (int i = 0; i < getData().size(); i++) {
                getData().get(i).setSelected(false);
            }
            addressDao.setSelected(z);
            notifyDataSetChanged();
            OnCheckedChangedListener onCheckedChangedListener = this.listener;
            if (onCheckedChangedListener != null) {
                onCheckedChangedListener.onCheckChanged(selectAddressItemHolder.getLayoutPosition(), z);
            }
        }
    }
}
