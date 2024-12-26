package com.tron.wallet.business.tabmy.myhome.addressbook;

import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.db.bean.AddressDao;
import java.util.List;
public interface AddressBookContract {

    public interface Model extends BaseModel {
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void addSome();

        abstract void checkAddressIsScam(List<AddressDao> list);

        public abstract void clickAddAddress();

        public abstract void getData();
    }

    public interface View extends BaseView {
        Intent getIIntent();

        RecyclerView getRcList();

        void showNoData(boolean z);
    }
}
