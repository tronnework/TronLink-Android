package com.tron.wallet.business.welcome;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Process;
import android.view.WindowManager;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.gson.Gson;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.upgraded.bean.UpgradeHdBean;
import com.tron.wallet.business.upgraded.confirm.HdUpdateConfirmModel;
import com.tron.wallet.business.welcome.WelcomeContract;
import com.tron.wallet.common.bean.AccountBalanceOutput;
import com.tron.wallet.common.bean.user.SplashOutput;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.config.UpgradeParamSetting;
import com.tron.wallet.common.utils.DownOfHttpUtil;
import com.tron.wallet.common.utils.NodeUtils;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.SignCheck;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.db.Controller.HDTronWalletController;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.bean.HdTronRelationshipBean;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
public class WelcomePresenter extends WelcomeContract.Presenter {
    private static final String TAG = "WelcomePresenter";
    private String default_url;
    private Gson gson;
    private double screenWidth;
    private long startTime;
    private Timer timer;
    private String default_test_url = "https://static.tronlink.org/production_test/wallet/20190723080257.png";
    private String default_online_url = "https://static.tronlink.org/production_test/wallet/20190723080257.png";
    private HdUpdateConfirmModel hdUpdateConfirmModel = new HdUpdateConfirmModel();
    private int counts = 0;
    private boolean HdUpgradeNetDoNext = false;

    @Override
    public void onStart() {
        this.gson = new Gson();
    }

    @Override
    public void getNodes() {
        NodeUtils.nodeRequest();
    }

    @Override
    public void init() {
        try {
            if (new SignCheck(((WelcomeContract.View) this.mView).getIContext(), TronConfig.cerSha1).check()) {
                return;
            }
            Process.killProcess(Process.myPid());
            System.exit(1);
            System.gc();
        } catch (Exception e) {
            SentryUtil.captureException(e);
            LogUtils.e(e);
        }
    }

    @Override
    public void downLoadImage() {
        if (this.screenWidth == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            this.screenWidth = ((WindowManager) AppContextUtil.getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
        }
        ((WelcomeContract.Model) this.mModel).getSplashPage(String.valueOf(this.screenWidth), String.valueOf(UIUtils.getScreenHeight(((WelcomeContract.View) this.mView).getIContext()))).subscribe(new IObserver(new ICallback<SplashOutput>() {
            @Override
            public void onResponse(String str, SplashOutput splashOutput) {
                if (splashOutput == null || !"0".equals(splashOutput.code) || splashOutput.data == null || StringTronUtil.isEmpty(splashOutput.data.url)) {
                    return;
                }
                if (StringTronUtil.equals(splashOutput.data.url, default_url)) {
                    SpAPI.THIS.setSplashPng("");
                    return;
                }
                SplashOutput.DataBean dataBean = !StringTronUtil.isEmpty(SpAPI.THIS.getSplashPng()) ? (SplashOutput.DataBean) gson.fromJson(SpAPI.THIS.getSplashPng(),  SplashOutput.DataBean.class) : null;
                if (dataBean == null || !splashOutput.data.url.equals(dataBean.url)) {
                    downLoadImage(splashOutput.data);
                }
            }

            @Override
            public void onFailure(String str, String str2) {
                LogUtils.e("downLoadImage", "getSplashPageonFailure");
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "111"));
    }

    @Override
    public void setImage() {
        String splashPng = SpAPI.THIS.getSplashPng();
        if (IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.NILETEST) {
            this.default_url = this.default_test_url;
        } else {
            this.default_url = this.default_online_url;
        }
        if (!StringTronUtil.isEmpty(splashPng)) {
            LogUtils.e("splashPngEmpty", "not");
            try {
                SplashOutput.DataBean dataBean = (SplashOutput.DataBean) this.gson.fromJson(splashPng,  SplashOutput.DataBean.class);
                if (StringTronUtil.equals(dataBean.url, this.default_url)) {
                    setGif();
                    return;
                } else if (new File(dataBean.imagePath).exists()) {
                    Bitmap decodeFile = BitmapFactory.decodeFile(dataBean.imagePath);
                    if (decodeFile != null) {
                        try {
                            ((WelcomeContract.View) this.mView).getRoot().setBackground(new BitmapDrawable(((WelcomeContract.View) this.mView).getIContext().getResources(), decodeFile));
                            ((WelcomeContract.View) this.mView).getRoot().setVisibility(View.VISIBLE);
                            ((WelcomeContract.View) this.mView).getSimpleDraweeView().setVisibility(View.GONE);
                            ((WelcomeContract.View) this.mView).getSloganTextView().setVisibility(View.GONE);
                            return;
                        } catch (Exception e) {
                            SentryUtil.captureException(e);
                            LogUtils.e(e);
                            return;
                        }
                    }
                    return;
                } else {
                    downLoadImage(dataBean);
                    setGif();
                    return;
                }
            } catch (Exception e2) {
                SentryUtil.captureException(e2);
                setGif();
                return;
            }
        }
        LogUtils.e("splashPngEmpty", "true");
        setGif();
    }

    private void setGif() {
        try {
            if (this.mView != 0) {
                ((WelcomeContract.View) this.mView).showDefaultGif();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void downLoadImage(final SplashOutput.DataBean dataBean) {
        DownOfHttpUtil downOfHttpUtil = new DownOfHttpUtil();
        downOfHttpUtil.deleted("/tron/startPage/");
        downOfHttpUtil.getDownFileLocalPath(dataBean.url, System.currentTimeMillis() + "image." + dataBean.picType, "/tron/startPage/", false, new DownOfHttpUtil.downLoadCallBack() {
            @Override
            public void LoadFail() {
            }

            @Override
            public void showProgress(int i) {
            }

            @Override
            public void LoadSuccess(String str, String str2) {
                dataBean.timestamp = System.currentTimeMillis();
                dataBean.imagePath = str2;
                SpAPI.THIS.setSplashPng(gson.toJson(dataBean));
            }
        });
    }

    public void upgradeHd() {
        Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$upgradeHd$0(observableEmitter);
            }
        }).compose(RxSchedulers.io_io()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$upgradeHd$1((List) obj);
            }
        });
    }

    public void lambda$upgradeHd$0(ObservableEmitter observableEmitter) throws Exception {
        this.startTime = System.currentTimeMillis();
        observableEmitter.onNext(HDTronWalletController.queryAllRelationFilterNonHdBeans());
        observableEmitter.onComplete();
    }

    public void lambda$upgradeHd$1(final List<HdTronRelationshipBean> list) {
        if (list == null || (list != null && HDTronWalletController.queryHdWalletCounts(list) <= 1)) {
            ((WelcomeContract.View) this.mView).doNext(true, this.startTime);
            return;
        }
        this.timer = new Timer();
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!HdUpgradeNetDoNext) {
                    ((WelcomeContract.View) mView).showProgress(counts);
                    counts += 25;
                    if (counts == 100) {
                        timer.cancel();
                        HdUpgradeNetDoNext = true;
                        doNext(list, null);
                        return;
                    }
                    return;
                }
                timer.cancel();
            }
        }, 0L, 1000L);
        this.hdUpdateConfirmModel.getBalances(list).compose(RxSchedulers.io_io()).subscribe(new IObserver(new ICallback<AccountBalanceOutput>() {
            @Override
            public void onResponse(String str, AccountBalanceOutput accountBalanceOutput) {
                if (HdUpgradeNetDoNext) {
                    return;
                }
                ((WelcomeContract.View) mView).showProgress(100);
                HdUpgradeNetDoNext = true;
                if (accountBalanceOutput != null && accountBalanceOutput.getData() != null && accountBalanceOutput.getData().getBalanceList() != null && accountBalanceOutput.getData().getBalanceList().size() != 0) {
                    doNext(list, hdUpdateConfirmModel.toMap(accountBalanceOutput.getData().getBalanceList()));
                    return;
                }
                doNext(list, null);
            }

            @Override
            public void onFailure(String str, String str2) {
                if (HdUpgradeNetDoNext) {
                    return;
                }
                HdUpgradeNetDoNext = true;
                ((WelcomeContract.View) mView).showProgress(100);
                doNext(list, null);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "getBalances"));
    }

    public void doNext(List<HdTronRelationshipBean> list, Map<String, AccountBalanceOutput.DataBean.BalanceListBean> map) {
        List<UpgradeHdBean> sortWallet = this.hdUpdateConfirmModel.sortWallet(list, map, SpAPI.THIS.isCold() ? HdUpdateConfirmModel.SortType.ChildNum : HdUpdateConfirmModel.SortType.Assets, false);
        if (sortWallet != null && sortWallet.size() > 0) {
            this.hdUpdateConfirmModel.refreshDb(sortWallet.get(0));
            UpgradeParamSetting.setStatusShow();
        }
        ((WelcomeContract.View) this.mView).runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$doNext$2();
            }
        });
    }

    public void lambda$doNext$2() {
        ((WelcomeContract.View) this.mView).doNext();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timer timer = this.timer;
        if (timer != null) {
            timer.cancel();
        }
    }
}
