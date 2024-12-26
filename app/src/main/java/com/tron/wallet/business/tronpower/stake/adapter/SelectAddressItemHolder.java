package com.tron.wallet.business.tronpower.stake.adapter;

import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatCheckBox;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.wallet.db.bean.AddressDao;
import com.tronlinkpro.wallet.R;
public class SelectAddressItemHolder extends BaseViewHolder {
    public AppCompatCheckBox cbCheck;
    TextView tvAddress;
    TextView tvName;

    public SelectAddressItemHolder(View view) {
        super(view);
        this.cbCheck = (AppCompatCheckBox) view.findViewById(R.id.iv_select_tag);
        this.tvAddress = (TextView) view.findViewById(R.id.tv_address);
        this.tvName = (TextView) view.findViewById(R.id.tv_name);
        this.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view2) {
                lambda$new$0(view2);
            }
        });
    }

    public void lambda$new$0(View view) {
        AppCompatCheckBox appCompatCheckBox = this.cbCheck;
        appCompatCheckBox.setChecked(!appCompatCheckBox.isChecked());
    }

    public void onBind(AddressDao addressDao) {
        this.cbCheck.setOnCheckedChangeListener(null);
        this.tvAddress.setText(addressDao.getAddress());
        this.tvName.setText(addressDao.getName());
        if (addressDao.isSelected()) {
            this.cbCheck.setChecked(true);
        } else {
            this.cbCheck.setChecked(false);
        }
    }
}
