package com.tron.wallet.business.upgraded.confirm;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.maintab.MainTabActivity;
import com.tron.wallet.business.upgraded.bean.UpgradeHdBean;
import com.tron.wallet.business.upgraded.confirm.HdUpdateConfirmContract;
import com.tron.wallet.business.upgraded.confirm.HdUpdateConfirmModel;
import com.tron.wallet.common.bean.AccountBalanceOutput;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.UpgradeParamSetting;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.bean.HdTronRelationshipBean;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.List;
import java.util.Map;
public class HdUpdateConfirmPresenter extends HdUpdateConfirmContract.Presenter {
    private List<HdTronRelationshipBean> hdAddressList;
    private ConfirmCustomPopupView popupView;

    @Override
    protected void onStart() {
    }

    public void getData() {
        Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$getData$0(observableEmitter);
            }
        }).compose(RxSchedulers.io_io()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$getData$1((List) obj);
            }
        });
    }

    public void lambda$getData$0(ObservableEmitter observableEmitter) throws Exception {
        List<HdTronRelationshipBean> hdAddressList = ((HdUpdateConfirmContract.Model) this.mModel).getHdAddressList();
        this.hdAddressList = hdAddressList;
        observableEmitter.onNext(hdAddressList);
        observableEmitter.onComplete();
    }

    public void lambda$getData$1(final List<HdTronRelationshipBean> list) {
        ((HdUpdateConfirmContract.Model) this.mModel).getBalances(list).compose(RxSchedulers.io_io()).subscribe(new IObserver(new ICallback<AccountBalanceOutput>() {
            @Override
            public void onResponse(String str, AccountBalanceOutput accountBalanceOutput) {
                if (accountBalanceOutput != null && accountBalanceOutput.getData() != null && accountBalanceOutput.getData().getBalanceList() != null && accountBalanceOutput.getData().getBalanceList().size() != 0) {
                    refreshData(list, ((HdUpdateConfirmContract.Model) mModel).toMap(accountBalanceOutput.getData().getBalanceList()));
                    return;
                }
                refreshData(list, null);
            }

            @Override
            public void onFailure(String str, String str2) {
                refreshData(list, null);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "getBalances"));
    }

    public void refreshData(List<HdTronRelationshipBean> list, Map<String, AccountBalanceOutput.DataBean.BalanceListBean> map) {
        final List<UpgradeHdBean> sortWallet = ((HdUpdateConfirmContract.Model) this.mModel).sortWallet(list, map, HdUpdateConfirmModel.SortType.Time, true);
        ((HdUpdateConfirmContract.View) this.mView).runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$refreshData$2(sortWallet);
            }
        });
    }

    public void lambda$refreshData$2(List list) {
        ((HdUpdateConfirmContract.View) this.mView).refreshData(list);
    }

    @Override
    public void onNext(UpgradeHdBean upgradeHdBean, UpgradeHdBean upgradeHdBean2) {
        showConfirmChangeHdPopWindow(upgradeHdBean2.getTitle(), upgradeHdBean2);
    }

    public void showConfirmChangeHdPopWindow(String str, final UpgradeHdBean upgradeHdBean) {
        ConfirmCustomPopupView build = ConfirmCustomPopupView.getBuilder(((HdUpdateConfirmContract.View) this.mView).getIContext()).setTitle(StringTronUtil.getResString(R.string.confirm_change_hd_title)).setInfo(String.format(StringTronUtil.getResString(R.string.confirm_change_hd_info), str)).setCancelText(StringTronUtil.getResString(R.string.need_to_consider)).setConfirmText(StringTronUtil.getResString(R.string.confirm_to_change)).setAutoDismissOutSide(false).setDismissOnBackPressed(false).setConfirmListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showConfirmChangeHdPopWindow$3(upgradeHdBean, view);
            }
        }).setCancleListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showConfirmChangeHdPopWindow$4(view);
            }
        }).build();
        this.popupView = build;
        build.show();
    }

    public void lambda$showConfirmChangeHdPopWindow$3(UpgradeHdBean upgradeHdBean, View view) {
        this.popupView.dismiss();
        doNext(upgradeHdBean);
    }

    public void lambda$showConfirmChangeHdPopWindow$4(View view) {
        ((HdUpdateConfirmContract.View) this.mView).enabledButton(true);
    }

    private void doNext(final UpgradeHdBean upgradeHdBean) {
        Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$doNext$5(upgradeHdBean, observableEmitter);
            }
        }).compose(RxSchedulers.io_main()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$doNext$6((Boolean) obj);
            }
        });
    }

    public void lambda$doNext$5(UpgradeHdBean upgradeHdBean, ObservableEmitter observableEmitter) throws Exception {
        observableEmitter.onNext(Boolean.valueOf(((HdUpdateConfirmContract.Model) this.mModel).refreshDb(upgradeHdBean)));
        observableEmitter.onComplete();
    }

    public void lambda$doNext$6(Boolean bool) throws Exception {
        if (bool.booleanValue()) {
            UpgradeParamSetting.setStatusHide();
            showChangeHdSuccessPopWindow();
            return;
        }
        ((HdUpdateConfirmContract.View) this.mView).dismissLoadingDialog();
        ((HdUpdateConfirmContract.View) this.mView).enabledButton(true);
        showChangeHdFailedPopWindow();
    }

    public void showChangeHdSuccessPopWindow() {
        AnalyticsHelper.logEvent(AnalyticsHelper.HDUpgradePage.CHANGE_HD_SUCCESS_SHOW);
        ConfirmCustomPopupView.getBuilder(((HdUpdateConfirmContract.View) this.mView).getIContext()).setTitle(StringTronUtil.getResString(R.string.change_hd_success_title)).setIcon(R.mipmap.change_hd_success_icon).setConfirmText(StringTronUtil.getResString(R.string.wallet_start_use)).setShowCancelBtn(false).setConfirmListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showChangeHdSuccessPopWindow$7(view);
            }
        }).build().show();
    }

    public void lambda$showChangeHdSuccessPopWindow$7(View view) {
        AnalyticsHelper.logEvent(AnalyticsHelper.HDUpgradePage.CHANGE_HD_SUCCESS_CLICK);
        this.mRxManager.post(Event.RESET_TAB, Event.RESET_TAB);
        Intent intent = new Intent(((HdUpdateConfirmContract.View) this.mView).getIContext(), MainTabActivity.class);
        intent.setFlags(67108864);
        ((HdUpdateConfirmContract.View) this.mView).go(intent);
        ((HdUpdateConfirmContract.View) this.mView).exit();
    }

    public void showChangeHdFailedPopWindow() {
        AnalyticsHelper.logEvent(AnalyticsHelper.HDUpgradePage.CHANGE_HD_FAILURE_SHOW);
        ConfirmCustomPopupView.getBuilder(((HdUpdateConfirmContract.View) this.mView).getIContext()).setTitle(StringTronUtil.getResString(R.string.change_hd_failed_title)).setIcon(R.mipmap.change_hd_failed_icon).setCancelText(StringTronUtil.getResString(R.string.cancel)).setConfirmText(StringTronUtil.getResString(R.string.change_once_again)).setConfirmListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showChangeHdFailedPopWindow$8(view);
            }
        }).setCancleListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showChangeHdFailedPopWindow$9(view);
            }
        }).build().show();
    }

    public void lambda$showChangeHdFailedPopWindow$8(View view) {
        AnalyticsHelper.logEvent(AnalyticsHelper.HDUpgradePage.CHANGE_HD_FAILURE_CLICK_AGAIN);
        ((HdUpdateConfirmContract.View) this.mView).exit();
    }

    public void lambda$showChangeHdFailedPopWindow$9(View view) {
        AnalyticsHelper.logEvent(AnalyticsHelper.HDUpgradePage.CHANGE_HD_FAILURE_CLICK_CANCEL);
        ((Activity) ((HdUpdateConfirmContract.View) this.mView).getIContext()).setResult(-1, new Intent());
        ((HdUpdateConfirmContract.View) this.mView).exit();
    }
}
