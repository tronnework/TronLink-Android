package com.tron.wallet.business.walletmanager.importwallet.rv;

import android.text.TextUtils;
import android.widget.CompoundButton;
import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.walletmanager.common.AddressItem;
import com.tron.wallet.common.components.CustomLoadMoreView;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
import org.tron.walletserver.WalletPath;
public class AddressAdapter extends BaseQuickAdapter<AddressItem, AddressItemHolder> {
    private final int importType;
    private OnCheckedChangedListener listener;
    private final CustomLoadMoreView loadMoreView;

    public interface OnCheckedChangedListener {
        void onCheckChanged(CompoundButton compoundButton, int i, boolean z);
    }

    public void setOnCheckChangedListener(OnCheckedChangedListener onCheckedChangedListener) {
        this.listener = onCheckedChangedListener;
    }

    public AddressAdapter(int i) {
        super((int) R.layout.item_choose_address);
        this.importType = i;
        CustomLoadMoreView customLoadMoreView = new CustomLoadMoreView();
        this.loadMoreView = customLoadMoreView;
        customLoadMoreView.setFailOnFixedTime(false);
        setLoadMoreView(customLoadMoreView);
    }

    @Override
    public void convert(final AddressItemHolder addressItemHolder, AddressItem addressItem) {
        addressItemHolder.onBind(addressItem);
        if (this.importType == 0 && addressItemHolder.getAbsoluteAdapterPosition() == 0) {
            checkForCustomPath(addressItemHolder, addressItem);
        } else if (this.importType == 1) {
            setupImportedTips(addressItemHolder, addressItem.isHasImported());
        }
        addressItemHolder.cbCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                lambda$convert$1(addressItemHolder, compoundButton, z);
            }
        });
    }

    public void lambda$convert$1(AddressItemHolder addressItemHolder, CompoundButton compoundButton, boolean z) {
        if (addressItemHolder.cbCheck.isEnabled()) {
            OnCheckedChangedListener onCheckedChangedListener = this.listener;
            if (onCheckedChangedListener != null) {
                onCheckedChangedListener.onCheckChanged(compoundButton, addressItemHolder.getLayoutPosition(), z);
            }
            addressItemHolder.itemView.post(new Runnable() {
                @Override
                public final void run() {
                    lambda$convert$0();
                }
            });
        }
    }

    public void lambda$convert$0() {
        notifyDataSetChanged();
    }

    private void checkForCustomPath(AddressItemHolder addressItemHolder, AddressItem addressItem) {
        try {
            String customWalletPath = SpAPI.THIS.getCustomWalletPath();
            WalletPath mnemonicPath = addressItem.getMnemonicPath();
            if (!(mnemonicPath != null && mnemonicPath.account == 0 && mnemonicPath.accountIndex == 0 && mnemonicPath.change == 0) && TextUtils.equals(JSON.toJSONString(mnemonicPath), customWalletPath)) {
                addressItemHolder.updateCustomPath(true);
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    private void setupImportedTips(AddressItemHolder addressItemHolder, boolean z) {
        if (z) {
            addressItemHolder.tvImported.setVisibility(View.VISIBLE);
            addressItemHolder.tvImported.setText(R.string.address_already_imported);
            return;
        }
        addressItemHolder.tvImported.setVisibility(View.GONE);
    }

    @Override
    public void loadMoreEnd(boolean z) {
        CustomLoadMoreView customLoadMoreView = this.loadMoreView;
        if (customLoadMoreView == null) {
            return;
        }
        customLoadMoreView.setNoMoreText(R.string.load_address_no_more, 100);
        super.loadMoreEnd(z);
    }
}
