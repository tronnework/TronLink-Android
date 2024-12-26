package com.tron.wallet.business.vote.component;

import android.widget.EditText;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.vote.bean.SearchWitnessResult;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.common.utils.DataStatHelper;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;
import org.tron.protos.Protocol;
public interface VoteSRSearchContract {
    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final int FILTER_MY_VOTES = 1;
    public static final int NO_FILTER = 0;

    public interface Model extends BaseModel {
        Observable<WitnessOutput> getAllFromTron();

        Observable<WitnessOutput> getWitnessList(int i, int i2, int i3, int i4, String str);

        Observable<SearchWitnessResult> search(String str, int i, String str2);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {

        public enum State {
            STATE_IDLE,
            STATE_LOADING,
            NORMAL,
            ERROR,
            NO_DATA,
            PLACE_HOLDER,
            ALL_GONE
        }

        public abstract void addSome(Protocol.Account account, String str, ArrayList<WitnessOutput.DataBean> arrayList, boolean z);

        public abstract String getCurrentAddress();

        abstract void getData(int i, int i2);

        public abstract State getState();

        abstract void loadMore();

        abstract void refresh();

        public abstract void setState(State state);
    }

    public interface SortType {
        public static final int ALL_VOTES = 6;
        public static final int APR = 5;
        public static final int VOTE_ASCEND = 4;
        public static final int VOTE_DSCEND = 3;
        public static final int YIELD_ASCEND = 2;
        public static final int YIELD_DCEND = 1;
    }

    public interface View extends BaseView {
        void beforeLoad();

        ImageView getIvClear();

        RecyclerView getRecycleView();

        EditText getSearchEt();

        DataStatHelper.StatAction getStatAction();

        RecyclerView getTopSRRecycleView();

        void showNoNetError(boolean z);

        void updateSearchUI(boolean z, boolean z2);

        void updateState(Presenter.State state);

        void updateUI(boolean z, boolean z2, List<WitnessOutput.DataBean> list, List<WitnessOutput.DataBean> list2);

        boolean visible();
    }
}
