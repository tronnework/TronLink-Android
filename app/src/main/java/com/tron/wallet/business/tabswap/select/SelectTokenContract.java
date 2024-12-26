package com.tron.wallet.business.tabswap.select;

import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.tabswap.bean.SwapWhiteListOutput;
import com.tron.wallet.common.components.NoNetView;
import io.reactivex.Observable;
import java.util.List;
public interface SelectTokenContract {

    public interface Model extends BaseModel {
        Observable<SwapWhiteListOutput> getWhiteListTokens();
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract void getTokens();
    }

    public interface View extends BaseView {
        android.view.View getHolderView();

        NoNetView getNonetView();

        RecyclerView getRecyclerView();

        TextView getTitleView();

        void showEmptyView(boolean z);

        void showNoNetError(boolean z);

        void updateUI(List<SwapWhiteListOutput.Data> list);
    }
}
