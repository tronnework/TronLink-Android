package com.tron.wallet.business.maintab;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentManager;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.maintab.bean.DeviceInfoBean;
import com.tron.wallet.business.tabdapp.jsbridge.BlackResultListBean;
import com.tron.wallet.common.bean.DappJsOutput;
import com.tron.wallet.common.bean.InfoBean;
import com.tron.wallet.common.bean.NoticeRemindBean;
import com.tron.wallet.common.bean.Result;
import com.tron.wallet.common.bean.update.UpdateOutput;
import com.tron.wallet.common.bean.user.SystemConfigOutput;
import com.tron.wallet.common.components.MainTabView;
import com.tron.wallet.db.bean.DappAuthorizedProjectBean;
import io.reactivex.Observable;
import java.util.List;
import okhttp3.RequestBody;
public interface MainTabContract {

    public interface Model extends BaseModel {
        Observable<InfoBean> deal(RequestBody requestBody);

        Observable<BlackResultListBean> getBlackList();

        Observable<Result<List<DappAuthorizedProjectBean>>> getDappAuthorizedProject();

        Observable<DappJsOutput> getDappJs();

        Observable<InfoBean> getInfo();

        Observable<NoticeRemindBean> getNoticeRemind();

        Observable<SystemConfigOutput> getSystemConfig();

        Observable<DeviceInfoBean> sendDeviceInfo(String str, String str2, String str3, String str4);

        Observable<UpdateOutput> update();
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        protected abstract void addSome();

        protected abstract void getAssetsList();

        public abstract void getBlackList();

        abstract void getDappAuthorizedProject();

        abstract void getDappJs();

        abstract boolean isSwapMode();

        protected abstract void onActivityResult(int i, int i2, Intent intent);

        protected abstract void onCreate2(Bundle bundle, FragmentManager fragmentManager);

        abstract void onNewIntent(Intent intent);

        protected abstract void onRequestPermissionsResult(int i, String[] strArr, int[] iArr);

        abstract void onResume();

        protected abstract void onSaveInstanceState(Bundle bundle);

        protected abstract void resetNodeInfo();

        protected abstract void setOnClickListener(FragmentManager fragmentManager);

        protected abstract void update();
    }

    public interface View extends BaseView {
        ViewGroup getRoot();

        MainTabView getTabViews();

        void refreshMarketTab(boolean z);

        void showTabView(double d);
    }
}
