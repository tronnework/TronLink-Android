package com.tron.wallet.business.tronpower.stake;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BottomPopupView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.ThreadPoolManager;
import com.tron.wallet.business.tabmy.myhome.addressbook.AddressBookActivity;
import com.tron.wallet.business.tronpower.stake.adapter.SelectAddressAdapter;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.PopviewSelectAddressFromAddressbookBinding;
import com.tron.wallet.db.Controller.AddressController;
import com.tron.wallet.db.bean.AddressDao;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class SelectAddressPopView extends BottomPopupView {
    private SelectAddressAdapter adapter;
    private PopviewSelectAddressFromAddressbookBinding binding;
    Button btnNext;
    private AddressDao currentSelectItem;
    ImageView ivClose;
    ImageView ivPlaceholder;
    private List<AddressDao> list;
    private Context mContext;
    RelativeLayout rlEmpty;
    RecyclerView rvList;
    private String selectAddress;
    private SelectAddressCallback selectAddressCallback;
    TextView tvManage;
    TextView tvTitle;

    public interface SelectAddressCallback {
        void onAddressSelected(String str, String str2, String str3);
    }

    @Override
    public int getImplLayoutId() {
        return R.layout.popview_select_address_from_addressbook;
    }

    public SelectAddressPopView(Context context, String str, SelectAddressCallback selectAddressCallback) {
        super(context);
        this.mContext = context;
        this.selectAddress = str;
        this.selectAddressCallback = selectAddressCallback;
    }

    public static void showBottom(Context context, SelectAddressCallback selectAddressCallback, String str) {
        new XPopup.Builder(context).asCustom(new SelectAddressPopView(context, str, selectAddressCallback)).show();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mappingId();
        getData();
        this.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectAddressCallback == null || currentSelectItem == null) {
                    return;
                }
                selectAddressCallback.onAddressSelected(currentSelectItem.getAddress(), currentSelectItem.getName(), currentSelectItem.getDescription());
                dismiss();
            }
        });
        this.tvManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, AddressBookActivity.class));
                dismiss();
            }
        });
        this.ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    private void mappingId() {
        PopviewSelectAddressFromAddressbookBinding bind = PopviewSelectAddressFromAddressbookBinding.bind(getPopupImplView());
        this.binding = bind;
        this.tvTitle = bind.tvTitle;
        this.tvManage = this.binding.tvManage;
        this.ivPlaceholder = this.binding.ivPlaceHolder;
        this.rlEmpty = this.binding.rlEmpty;
        this.ivClose = this.binding.ivCloseSelect;
        this.rvList = this.binding.rvList;
        this.btnNext = this.binding.btnNext;
    }

    private void getData() {
        ThreadPoolManager.newInstance().addExecuteTask(new Runnable() {
            @Override
            public void run() {
                list = AddressController.getInstance().searchAll();
                final int size = list == null ? 0 : list.size();
                if (StringTronUtil.isEmpty(selectAddress)) {
                    for (int i = 0; i < size; i++) {
                        ((AddressDao) list.get(i)).setSelected(false);
                    }
                } else {
                    for (int i2 = 0; i2 < size; i2++) {
                        if (((AddressDao) list.get(i2)).getAddress().equals(selectAddress)) {
                            ((AddressDao) list.get(i2)).setSelected(true);
                            SelectAddressPopView selectAddressPopView = SelectAddressPopView.this;
                            selectAddressPopView.currentSelectItem = (AddressDao) selectAddressPopView.list.get(i2);
                        } else {
                            ((AddressDao) list.get(i2)).setSelected(false);
                        }
                    }
                }
                if (currentSelectItem == null) {
                    currentSelectItem = new AddressDao();
                    currentSelectItem.setSelected(true);
                    currentSelectItem.setAddress(selectAddress);
                    currentSelectItem.setName("");
                }
                if (currentSelectItem == null) {
                    currentSelectItem = new AddressDao();
                    currentSelectItem.setSelected(true);
                    currentSelectItem.setAddress(selectAddress);
                    currentSelectItem.setName("");
                }
                ((BaseActivity) mContext).runOnUIThread(new OnMainThread() {
                    @Override
                    public void doInUIThread() {
                        initAdapter();
                        for (int i3 = 0; i3 < size; i3++) {
                            if (((AddressDao) list.get(i3)).isSelected()) {
                                btnNext.setEnabled(true);
                            }
                        }
                        if (list == null || list.size() == 0) {
                            ivPlaceholder.setVisibility(View.GONE);
                            rlEmpty.setVisibility(View.VISIBLE);
                            return;
                        }
                        rlEmpty.setVisibility(View.GONE);
                    }
                });
            }
        });
    }

    public void initAdapter() {
        this.adapter = new SelectAddressAdapter(this.list);
        this.rvList.setLayoutManager(new WrapContentLinearLayoutManager(this.mContext, 1, false));
        this.rvList.setAdapter(this.adapter);
        this.adapter.setOnCheckChangedListener(new SelectAddressAdapter.OnCheckedChangedListener() {
            @Override
            public final void onCheckChanged(int i, boolean z) {
                lambda$initAdapter$0(i, z);
            }
        });
        this.ivPlaceholder.setVisibility(View.GONE);
        this.rvList.setVisibility(View.VISIBLE);
    }

    public void lambda$initAdapter$0(int i, boolean z) {
        if (i < 0 || i >= this.adapter.getItemCount()) {
            return;
        }
        if (z) {
            this.currentSelectItem = this.adapter.getItem(i);
            this.btnNext.setEnabled(true);
            this.adapter.notifyDataSetChanged();
            return;
        }
        this.currentSelectItem = null;
        this.btnNext.setEnabled(false);
        this.adapter.notifyDataSetChanged();
    }
}
