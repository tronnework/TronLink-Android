package com.tron.wallet.business.mutil;

import android.widget.EditText;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.mutil.ResStringProvider;
import com.tron.wallet.business.mutil.bean.MultiAddressOutput;
import com.tron.wallet.business.mutil.bean.MultiSourcePageEnum;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.common.bean.Result2;
import io.reactivex.Observable;
import java.util.List;
import java.util.Map;
public interface MultiSelectAddressContract {

    public interface Model extends BaseModel {
        boolean checkCurrentAccountPermission(String str, String str2);

        Observable<Map<String, String>> getAddressName();

        Observable<Result2<List<MultiAddressOutput>>> getMultiAddress(String str);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void addTextChangedListener(EditText editText);

        public abstract NameAddressExtraBean createNameAddressExtraBean(String str);

        public abstract void getMultiAddress();

        public abstract MultiAddressOutput getMultiAddressOutputByAddress(String str);

        public abstract String getPasteString();
    }

    public interface View extends BaseView {
        String getInputAddress(String str);

        MultiSourcePageEnum getMultiSourcePageEnum();

        NameAddressExtraBean getRecentNameAddressExtraBean();

        void setButtonStatus(boolean z);

        void showDataView();

        void showDataView(List<NameAddressExtraBean> list);

        void showEdiTextError(boolean z);

        void showEdiTextError(boolean z, ResStringProvider.ErrorViewText errorViewText);

        void showEdiTextError(boolean z, MultiAddressOutput multiAddressOutput);

        void showSearchView(List<NameAddressExtraBean> list);

        void updateEdiTextStatus(String str);
    }
}
