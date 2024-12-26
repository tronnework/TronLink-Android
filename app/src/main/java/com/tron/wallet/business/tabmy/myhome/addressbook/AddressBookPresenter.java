package com.tron.wallet.business.tabmy.myhome.addressbook;

import android.app.Activity;
import android.content.Intent;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.wallet.business.tabmy.myhome.addressbook.AddressBookContract;
import com.tron.wallet.business.tabmy.myhome.addressbook.adapter.AddressBookAdapter;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.addressscam.AddressManager;
import com.tron.wallet.db.Controller.AddressController;
import com.tron.wallet.db.bean.AddressDao;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class AddressBookPresenter extends AddressBookContract.Presenter {
    private AddressBookAdapter addressAdapter;
    private List<AddressDao> addressList;
    private String mFromType;
    private LinearLayoutManager manager;

    @Override
    protected void onStart() {
        this.addressList = new ArrayList();
    }

    @Override
    public void getData() {
        ((AddressBookContract.View) this.mView).runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$getData$1();
            }
        });
    }

    public void lambda$getData$1() {
        this.addressList = AddressController.getInstance(((AddressBookContract.View) this.mView).getIContext()).searchAll();
        ((AddressBookContract.View) this.mView).runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$getData$0();
            }
        });
    }

    public void lambda$getData$0() {
        ((AddressBookContract.View) this.mView).dismissLoadingPage();
        List<AddressDao> list = this.addressList;
        if (list == null || list.size() == 0) {
            ((AddressBookContract.View) this.mView).showNoData(true);
            return;
        }
        ((AddressBookContract.View) this.mView).showNoData(false);
        AddressBookAdapter addressBookAdapter = this.addressAdapter;
        if (addressBookAdapter == null) {
            this.addressAdapter = new AddressBookAdapter(((AddressBookContract.View) this.mView).getIContext(), this.addressList, this.mFromType);
            ((AddressBookContract.View) this.mView).getRcList().setAdapter(this.addressAdapter);
            checkAddressIsScam(this.addressList);
            return;
        }
        addressBookAdapter.notify(this.addressList, this.mFromType);
    }

    @Override
    public void addSome() {
        Intent iIntent = ((AddressBookContract.View) this.mView).getIIntent();
        if (iIntent != null) {
            this.mFromType = iIntent.getStringExtra(AddressBookActivity.TAG_FROM_TYPE);
        }
        if (StringTronUtil.isEmpty(this.mFromType)) {
            this.mFromType = AddressBookActivity.TYPE_FROM_MY;
        }
        this.manager = new LinearLayoutManager(((AddressBookContract.View) this.mView).getIContext(), 1, false);
        ((AddressBookContract.View) this.mView).getRcList().setLayoutManager(this.manager);
        getData();
    }

    @Override
    public void clickAddAddress() {
        List<AddressDao> list = this.addressList;
        if (list != null && list.size() >= 300) {
            ((AddressBookContract.View) this.mView).toast(((AddressBookContract.View) this.mView).getIContext().getResources().getString(R.string.address_no_add));
        } else {
            AddAddressActivity.startForResult((Activity) ((AddressBookContract.View) this.mView).getIContext(), AddAddressActivity.TYPE_ADD_ADDRESS, 2025, this.addressList, null);
        }
    }

    @Override
    void checkAddressIsScam(List<AddressDao> list) {
        ArrayList arrayList = new ArrayList();
        for (AddressDao addressDao : list) {
            arrayList.add(addressDao.address);
        }
        AddressManager.checkAddressIsScam(arrayList, new AddressManager.AddressScamCall() {
            @Override
            public void error() {
            }

            @Override
            public void callback(HashMap<String, Map<String, Boolean>> hashMap) {
                addressAdapter.notifyScamTag(hashMap);
            }
        });
    }
}
