package com.tron.wallet.business.tabmy.allhistory;

import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.common.bean.AllTransferOutput;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayoutV2;
import io.reactivex.Observable;
public interface TrxAllHistoryContract {

    public interface Model extends BaseModel {
        Observable<AllTransferOutput> getAllTRXTransfer(String str, String str2, String str3, int i, int i2);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void addSome();

        public abstract void getData();

        public abstract void onRefresh(String str);
    }

    public interface View extends BaseView {
        String getAddress();

        int getPosition();

        PtrHTFrameLayoutV2 getRcFrame();

        RecyclerView getRcList();

        void showContent();

        void showError(boolean z);
    }
}
