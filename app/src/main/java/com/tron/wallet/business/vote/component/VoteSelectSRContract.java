package com.tron.wallet.business.vote.component;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.vote.bean.SearchWitnessResult;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayout;
import com.tron.wallet.common.utils.DataStatHelper;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;
import org.tron.protos.Protocol;
public interface VoteSelectSRContract {

    public interface Model extends BaseModel {
        Observable<WitnessOutput> getWitnessList(String str, int i, int i2, int i3, int i4, boolean z);

        Observable<SearchWitnessResult> search(String str, int i, String str2);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        protected abstract void addSome(String str, Protocol.Account account, ArrayList<WitnessOutput.DataBean> arrayList);

        protected abstract void clear();

        protected abstract void getData();

        protected abstract long getMyAvailableVotes();

        protected abstract String getSelectAddress();

        public abstract void getTop3Data();

        protected abstract long getTotalVotes();

        protected abstract void getVoteReward(String str);

        public abstract ArrayList<WitnessOutput.DataBean> getVotedWitnesses();

        public abstract boolean isLedger();

        protected abstract long myVotedCount();

        protected abstract void onActivityResult(int i, int i2, Intent intent);

        protected abstract void reset();

        public abstract void search(String str);

        protected abstract void showSelectRole();

        protected abstract void updateRemain();

        protected abstract void vote();
    }

    public interface View extends BaseView {
        Context getContext();

        EditText getEtSearch();

        ImageView getIvClear();

        ImageView getIvSort();

        PtrHTFrameLayout getPl();

        RecyclerView getRv();

        DataStatHelper.StatAction getStatAction();

        TextView getTotalVotes();

        TextView getTvCancelSearch();

        TextView getVoteSRCount();

        void onSRSelectedChanged(int i);

        void showLoading(boolean z);

        void showNoEnoughVotes(boolean z);

        void showOrHideNoData(boolean z);

        void showOrHideNoNet(boolean z);

        void showPlaceHolder(boolean z);

        @Override
        void toast(String str);

        void updateAlreadyVotedList();

        void updateBtnEnabled(boolean z);

        void updateTop3ApyList(List<WitnessOutput.DataBean> list);
    }
}
