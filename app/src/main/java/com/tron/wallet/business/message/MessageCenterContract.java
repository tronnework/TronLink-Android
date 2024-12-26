package com.tron.wallet.business.message;

import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import io.reactivex.Observable;
import java.util.List;
public interface MessageCenterContract {

    public interface Model extends BaseModel {
        Observable<List<TransactionMessage>> getMessageData(int i, int i2);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getData();

        abstract void getMessages();

        public abstract void updateAllReadStatus();

        abstract void updateUI(List<TransactionMessage> list);
    }

    public interface View extends BaseView {
        android.view.View getHolderView();

        RecyclerView getRecyclerView();

        void showEmptyView(boolean z);

        void showNoNetError(boolean z);

        void updateUI(List<TransactionMessage> list);
    }
}
