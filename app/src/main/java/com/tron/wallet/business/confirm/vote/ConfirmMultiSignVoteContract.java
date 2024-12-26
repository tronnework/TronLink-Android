package com.tron.wallet.business.confirm.vote;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import io.reactivex.Observable;
import java.util.HashMap;
import java.util.List;
public interface ConfirmMultiSignVoteContract {

    public interface Model extends BaseModel {
        Observable<WitnessOutput> getWitnessList();
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        protected abstract void addSome();

        protected abstract void getData();
    }

    public interface View extends BaseView {
        void gotoFragment(List<WitnessOutput.DataBean> list, HashMap<String, String> hashMap);

        void showLoading(boolean z);

        void showOrHideNoData(boolean z);

        void showOrHideNoNet(boolean z);
    }
}
