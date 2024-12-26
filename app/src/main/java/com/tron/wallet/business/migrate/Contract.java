package com.tron.wallet.business.migrate;

import android.content.Context;
import android.net.Uri;
import android.util.Pair;
import androidx.activity.ComponentActivity;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import io.reactivex.Observable;
import io.reactivex.Single;
public interface Contract {

    public interface CheckCode {
        public static final int APP_GLOBAL = 10;
        public static final int APP_PRO = 11;
        public static final int CHANNEL_GOOGLE_PLAY = 13;
        public static final int CHANNEL_OFFICIAL = 12;
        public static final int ERR_DEPRECATED_WATCH_COLD = 4;
        public static final int ERR_HAS_INITIALIZED = 3;
        public static final int ERR_NOT_INSTALLED = 1;
        public static final int ERR_VERSION_DEPRECATED = 2;
        public static final int SUCCESS = 0;
    }

    public interface Model extends BaseModel {
        int checkTargetWalletApp(Context context);

        int getChannelPro(Context context);

        int getCurrentPackageId();

        void installFromGoogle(Context context, int i);

        void installFromGoogle(Context context, String str);

        Observable<Float> readMigrateData(Context context, Uri uri, Uri uri2);

        void startMigrateGlobal(Context context, Uri uri, Uri uri2);

        void startMigratePro(Context context);

        Single<Pair<Uri, Uri>> writeMigrateData(Context context);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract void checkTargetWalletApp(Context context);

        abstract int getCurrentPackage();

        abstract void registerLaunchActivityCallback(ComponentActivity componentActivity);
    }

    public interface View extends BaseView {
        void onGetPackageId(int i);

        void onMigrateComplete(boolean z);
    }
}
