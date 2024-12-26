package com.tron.wallet.business.ledger.search;

import android.app.Activity;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.ledger.manage.EquipmentAdapter;
import com.tron.wallet.business.ledger.manage.EquipmentBean;
import com.tron.wallet.business.ledger.manage.EquipmentItemListener;
import com.tron.wallet.business.ledger.search.PermissionLedgerHelper;
import com.tron.wallet.business.ledger.search.SearchLedgerContract;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.databinding.AcLedgerSearchBinding;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.util.List;
import java.util.concurrent.TimeUnit;
public class SearchLedgerFragment extends BaseFragment<SearchLedgerPresenter, SearchLedgerModel> implements PermissionLedgerHelper.OnPermissionResultCallback, SearchLedgerContract.View {
    private static final String TAG = "BleSearchLedgerFragment";
    private EquipmentAdapter adapter;
    private AcLedgerSearchBinding binding;
    View llError;
    View llNoDataTips;
    View llSearch;
    private PermissionLedgerHelper permissionHelper;
    private BluetoothListenerReceiver receiver;
    RecyclerView recyclerView;
    View retry;
    View searchTip;
    SimpleDraweeView searchView;
    private AbstractDraweeController searchViewController;
    private boolean startSearchFlag;
    TextView tip1;
    TextView tip2;
    TextView tip3;
    private Disposable tipsDisposable;

    public static SearchLedgerFragment newInstance() {
        return new SearchLedgerFragment();
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        AcLedgerSearchBinding inflate = AcLedgerSearchBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        ConstraintLayout root = inflate.getRoot();
        mappingId();
        return root;
    }

    public void mappingId() {
        this.llSearch = this.binding.llSearch;
        this.searchView = this.binding.searchView;
        this.recyclerView = this.binding.rvSearchLedgerList;
        this.searchTip = this.binding.tvSearchLedgerTip;
        this.llNoDataTips = this.binding.llSearchTimeoutTip;
        this.tip1 = this.binding.tvSearchLedgerTip1;
        this.tip2 = this.binding.tvSearchLedgerTip2;
        this.tip3 = this.binding.tvSearchLedgerTip3;
        this.llError = this.binding.llError;
        this.retry = this.binding.btnRetry;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.permissionHelper = new PermissionLedgerHelper(this);
    }

    @Override
    protected void processData() {
        this.adapter = new EquipmentAdapter(getIContext(), EquipmentAdapter.TYPE.SEARCH, new EquipmentItemListener() {
            @Override
            public void itemClick(final int i, final EquipmentBean equipmentBean) {
                permissionHelper.checkPermissions(new PermissionLedgerHelper.OnPermissionResultCallback() {
                    @Override
                    public void onSuccess() {
                        ((SearchLedgerPresenter) mPresenter).connect(equipmentBean, i);
                    }

                    @Override
                    public void onBluetoothFail() {
                        showErrorView();
                    }

                    @Override
                    public void onLocationFail() {
                        showErrorView();
                    }
                });
            }
        });
        this.recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getIContext(), 1, false));
        this.recyclerView.setAdapter(this.adapter);
        this.retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$processData$0(view);
            }
        });
        initReceiver();
        startSearch();
        this.permissionHelper.checkPermissions(this);
        AnalyticsHelper.logEvent(AnalyticsHelper.LedgerPage.ENTER_LEDGER_SEARCH_PAGE);
    }

    public void lambda$processData$0(View view) {
        showSearchView();
        this.permissionHelper.checkPermissions(this);
    }

    private void initReceiver() {
        this.receiver = new BluetoothListenerReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
        if (Build.VERSION.SDK_INT >= 26) {
            getIContext().registerReceiver(this.receiver, intentFilter, 2);
        } else {
            getIContext().registerReceiver(this.receiver, intentFilter);
        }
        ((SearchLedgerPresenter) this.mPresenter).mRxManager.on(Event.BlueToothOff, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initReceiver$1(obj);
            }
        });
    }

    public void lambda$initReceiver$1(Object obj) throws Exception {
        showErrorView();
    }

    private void showSearchView() {
        this.llNoDataTips.setVisibility(View.GONE);
        this.tip2.setVisibility(View.GONE);
        this.tip3.setVisibility(View.GONE);
        this.searchTip.setVisibility(View.VISIBLE);
        this.llError.setVisibility(View.GONE);
        this.llSearch.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorView() {
        this.llError.setVisibility(View.VISIBLE);
        this.llSearch.setVisibility(View.GONE);
        ((SearchLedgerPresenter) this.mPresenter).stopScan();
    }

    private void startScan() {
        this.startSearchFlag = true;
        startSearch();
    }

    private Uri getUriFromDrawableRes(int i) {
        return new Uri.Builder().scheme(UriUtil.LOCAL_RESOURCE_SCHEME).path(String.valueOf(i)).build();
    }

    private void startSearch() {
        if (this.searchView == null || this.mPresenter == 0) {
            return;
        }
        if (this.startSearchFlag) {
            this.searchViewController = Fresco.newDraweeControllerBuilder().setUri(getUriFromDrawableRes(R.mipmap.search_ledger)).setAutoPlayAnimations(true).build();
            ((SearchLedgerPresenter) this.mPresenter).startScan();
        } else {
            this.searchViewController = Fresco.newDraweeControllerBuilder().setUri(getUriFromDrawableRes(R.mipmap.search_ledger)).build();
        }
        this.searchView.setController(this.searchViewController);
    }

    @Override
    public void showNoDataTips() {
        this.llNoDataTips.setVisibility(View.VISIBLE);
        this.tipsDisposable = Observable.just(this.tip2, this.tip3).delay(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$showNoDataTips$2;
                lambda$showNoDataTips$2 = lambda$showNoDataTips$2((TextView) obj);
                return lambda$showNoDataTips$2;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                ((TextView) obj).setVisibility(View.VISIBLE);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                LogUtils.e((Throwable) obj);
            }
        });
    }

    public ObservableSource lambda$showNoDataTips$2(TextView textView) throws Exception {
        return Observable.timer(textView == this.tip2 ? 1L : 2L, TimeUnit.SECONDS);
    }

    @Override
    public void updateData(List<EquipmentBean> list) {
        this.llNoDataTips.setVisibility(View.GONE);
        this.searchTip.setVisibility(View.VISIBLE);
        this.recyclerView.setVisibility(View.VISIBLE);
        this.adapter.setNewData(list);
    }

    @Override
    public void onDestroyView() {
        Disposable disposable = this.tipsDisposable;
        if (disposable != null && !disposable.isDisposed()) {
            this.tipsDisposable.dispose();
        }
        if (this.receiver != null) {
            this.mContext.unregisterReceiver(this.receiver);
        }
        ((SearchLedgerPresenter) this.mPresenter).stopScan();
        this.binding = null;
        super.onDestroyView();
    }

    public void onRequestPermissionResult(int i, String[] strArr, int[] iArr) {
        PermissionLedgerHelper permissionLedgerHelper = this.permissionHelper;
        if (permissionLedgerHelper == null || permissionLedgerHelper.getPermissionHelper() == null) {
            return;
        }
        this.permissionHelper.getPermissionHelper().requestPermissionsResult(i, strArr, iArr);
    }

    @Override
    public void onSuccess() {
        startScan();
    }

    @Override
    public void onBluetoothFail() {
        showErrorView();
    }

    @Override
    public void onLocationFail() {
        showErrorView();
    }
}
