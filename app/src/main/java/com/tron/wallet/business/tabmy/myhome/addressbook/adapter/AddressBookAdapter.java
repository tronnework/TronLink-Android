package com.tron.wallet.business.tabmy.myhome.addressbook.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.lxj.xpopup.core.BasePopupView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.business.tabmy.myhome.addressbook.AddAddressActivity;
import com.tron.wallet.business.tabmy.myhome.addressbook.AddressBookActivity;
import com.tron.wallet.common.components.MultiLineTextPopupView;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.ItemAddressBookBinding;
import com.tron.wallet.db.bean.AddressDao;
import com.tronlinkpro.wallet.R;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class AddressBookAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<AddressDao> addressBookList;
    private Context mContext;
    private String mTypeFrom;
    private HashMap<String, Map<String, Boolean>> map;

    public AddressBookAdapter(Context context, List<AddressDao> list, String str) {
        this.mContext = context;
        this.addressBookList = list;
        this.mTypeFrom = str;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_address_book, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        List<AddressDao> list;
        if (viewHolder == null || (list = this.addressBookList) == null || list.size() <= 0) {
            return;
        }
        final AddressDao addressDao = this.addressBookList.get(i);
        if (addressDao != null && addressDao != null) {
            viewHolder.tvAddressName.setText(StringTronUtil.isEmpty(addressDao.name) ? "" : addressDao.name);
            viewHolder.tvAddress.setText(StringTronUtil.isEmpty(addressDao.address) ? "" : addressDao.address);
            if (StringTronUtil.isEmpty(addressDao.description)) {
                viewHolder.tvDescription.setVisibility(View.GONE);
                viewHolder.line.setVisibility(View.GONE);
            } else {
                viewHolder.tvDescription.setVisibility(View.VISIBLE);
                viewHolder.line.setVisibility(View.VISIBLE);
                viewHolder.tvDescription.setText(addressDao.description);
            }
            viewHolder.rlAddressCopy.setOnClickListener(new NoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    if (StringTronUtil.isEmpty(addressDao.address)) {
                        return;
                    }
                    UIUtils.copy(addressDao.address);
                    IToast.getIToast().show(mContext.getResources().getString(R.string.already_copy));
                }
            });
        }
        if (this.mTypeFrom.equals(AddressBookActivity.TYPE_FROM_MY)) {
            viewHolder.rlAddressEdit.setVisibility(View.VISIBLE);
        } else {
            viewHolder.rlAddressEdit.setVisibility(View.INVISIBLE);
        }
        viewHolder.itemView.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (mTypeFrom.equals(AddressBookActivity.TYPE_FROM_MY)) {
                    AddAddressActivity.startForResult((Activity) mContext, AddAddressActivity.TYPE_ADDRESS_DETAILS, 2025, addressBookList, addressDao);
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra(TronConfig.ADDRESS_BOOK_SELECT, addressDao.address);
                ((Activity) mContext).setResult(-1, intent);
                ((Activity) mContext).finish();
            }
        });
        HashMap<String, Map<String, Boolean>> hashMap = this.map;
        if (hashMap == null || hashMap.isEmpty()) {
            viewHolder.ivScam.setVisibility(View.GONE);
        } else {
            Map<String, Boolean> map = this.map.get(addressDao.getAddress());
            if (map != null && !map.isEmpty() && map.get("risk") != null) {
                viewHolder.ivScam.setVisibility(map.get("risk").booleanValue() ? View.VISIBLE : View.GONE);
            }
        }
        viewHolder.ivScam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.tipsWindow = new MultiLineTextPopupView.Builder().setAnchor(viewHolder.ivScam).setPreferredShowUp(true).setRequiredWidth(UIUtils.dip2px(243.0f)).addKeyValue(mContext.getString(R.string.risk_account_tip), "").build(mContext);
                viewHolder.tipsWindow.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        List<AddressDao> list = this.addressBookList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void notify(List<AddressDao> list, String str) {
        this.addressBookList = list;
        this.mTypeFrom = str;
        notifyDataSetChanged();
    }

    public void notifyScamTag(HashMap<String, Map<String, Boolean>> hashMap) {
        this.map = hashMap;
        notifyDataSetChanged();
    }

    public class ViewHolder extends BaseHolder {
        private ItemAddressBookBinding binding;
        ImageView ivScam;
        View line;
        private Context mContext;
        RelativeLayout rlAddress;
        RelativeLayout rlAddressCopy;
        View rlAddressEdit;
        private BasePopupView tipsWindow;
        TextView tvAddress;
        TextView tvAddressName;
        TextView tvDescription;

        public ViewHolder(View view) {
            super(view);
            mappingId(view);
        }

        public void mappingId(View view) {
            ItemAddressBookBinding bind = ItemAddressBookBinding.bind(view);
            this.tvAddressName = bind.tvAddressName;
            this.rlAddressEdit = bind.rlAddressEdit;
            this.rlAddressCopy = bind.rlAddressCopy;
            this.rlAddress = bind.rlAddress;
            this.tvDescription = bind.tvDescription;
            this.line = bind.ivLineBottom;
            this.ivScam = bind.ivScam;
            this.tvAddress = bind.tvAddress;
        }
    }
}
