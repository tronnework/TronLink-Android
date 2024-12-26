package com.tron.wallet.business.welcome;

import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.tabmy.myhome.settings.bean.MainNodeOutput;
import com.tron.wallet.business.tabmy.myhome.settings.bean.NodeOutput;
import com.tron.wallet.common.bean.user.SplashOutput;
import io.reactivex.Observable;
import okhttp3.RequestBody;
public interface WelcomeContract {

    public interface Model extends BaseModel {
        Observable<MainNodeOutput> getMainNodes(String str, RequestBody requestBody);

        Observable<NodeOutput> getNodes(RequestBody requestBody);

        Observable<SplashOutput> getSplashPage(String str, String str2);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract void downLoadImage();

        abstract void getNodes();

        abstract void init();

        abstract void setImage();
    }

    public interface View extends BaseView {
        void doNext();

        void doNext(boolean z, long j);

        ImageView getRoot();

        SimpleDraweeView getSimpleDraweeView();

        TextView getSloganTextView();

        void showDefaultGif();

        void showProgress(int i);
    }
}
