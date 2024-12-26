package com.tron.wallet.business.vote.fg;

import android.widget.EditText;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.vote.bean.SearchWitnessResult;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import io.reactivex.Observable;
import java.util.List;
public interface VoteContentContract {

    public interface Model extends BaseModel {
        Observable<WitnessOutput> getAllFromTron();

        Observable<WitnessOutput> getWitnessList(int i, int i2, int i3, int i4);

        Observable<SearchWitnessResult> search(String str, int i);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public static int STATE_IDLE = 0;
        public static int STATE_LOADING = 1;

        public interface SORT {
            public static final int VOTE_ASCEND = 4;
            public static final int VOTE_DSCEND = 3;
            public static final int YIELD_ASCEND = 2;
            public static final int YIELD_DCEND = 1;
        }

        public abstract String getCurrentAddress();

        abstract void getData(int i, int i2);

        public abstract int getState();

        abstract void loadMore();

        abstract void refresh();

        public abstract void setState(int i);

        abstract void sort(boolean z, int i);
    }

    public interface View extends BaseView {
        void beforeLoad();

        RecyclerView getRecycleView();

        EditText getSearchEt();

        void showNoNetError(boolean z);

        void updateSearchUI(boolean z, boolean z2);

        void updateSelectAddress(String str);

        void updateUI(boolean z, boolean z2, List<WitnessOutput.DataBean> list, List<WitnessOutput.DataBean> list2);

        boolean visible();
    }
}
