package com.tron.wallet.business.stakev2.managementv2.pop;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BottomPopupView;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.stakev2.managementv2.detail.StakeManageDetailContract;
import com.tron.wallet.business.stakev2.managementv2.detail.StakeManageDetailModel;
import com.tron.wallet.business.stakev2.managementv2.detail.bean.StakeResourceDetailForMeOutput;
import com.tron.wallet.business.stakev2.managementv2.detail.bean.StakeResourceDetailOutput;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.common.utils.AddressMapUtils;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.databinding.PopupDelegateForMeBinding;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tronlinkpro.wallet.R;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.Collection;
import java.util.HashMap;
import org.tron.walletserver.Wallet;
public class DelegateForMePopup extends BottomPopupView implements StakeManageDetailContract.View {
    DelegateForMeAdapter adapter;
    private String address;
    private PopupDelegateForMeBinding binding;
    private boolean fromMultiSign;
    ImageView ivPlaceHolder;
    View llRoot;
    StakeManageDetailModel model;
    NoNetView noDataView;
    NoNetView noNetView;
    private OnDismissListener onDismissListener;
    RecyclerView recyclerView;
    private int resourceType;
    RxManager rxManager;
    private int stakeType;
    TextView tvTag;
    TextView tvTitle;

    public interface OnClickListener {
        void onClick(Wallet wallet);
    }

    public interface OnDismissListener {
        void onDismiss();
    }

    @Override
    public int getImplLayoutId() {
        return R.layout.popup_delegate_for_me;
    }

    @Override
    public void updateStakeForOtherList(long j, StakeResourceDetailOutput stakeResourceDetailOutput) {
    }

    @Override
    public void updateUI(long j, long j2) {
    }

    public DelegateForMePopup(Context context, String str, int i, int i2, boolean z, OnClickListener onClickListener, OnDismissListener onDismissListener) {
        super(context);
        this.onDismissListener = onDismissListener;
        this.resourceType = i;
        this.address = str;
        this.stakeType = i2;
        this.fromMultiSign = z;
        this.model = new StakeManageDetailModel();
    }

    public static void showPopup(Context context, String str, int i, int i2, boolean z, OnClickListener onClickListener, OnDismissListener onDismissListener) {
        ((DelegateForMePopup) new XPopup.Builder(context).enableDrag(false).asCustom(new DelegateForMePopup(context, str, i, i2, z, onClickListener, onDismissListener))).show();
    }

    @Override
    public void onDismiss() {
        OnDismissListener onDismissListener = this.onDismissListener;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
        super.onDismiss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxManager rxManager = this.rxManager;
        if (rxManager != null) {
            rxManager.cancle();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mappingId(getPopupImplView());
        setClick();
        if (this.resourceType == 1) {
            this.tvTag.setText(R.string.bandwidth);
            this.tvTag.setBackgroundResource(R.drawable.bg_resource_bandwidth_tag);
        } else {
            this.tvTag.setText(R.string.energy);
            this.tvTag.setBackgroundResource(R.drawable.bg_resource_energy_tag);
        }
        if (this.stakeType == 1) {
            this.tvTitle.setText(R.string.stake_1_from_other);
        } else {
            this.tvTitle.setText(R.string.stake_2_from_other);
        }
        if (this.stakeType == 1) {
            if (this.resourceType == 1) {
                AnalyticsHelper.ResourceDetailPage.logMultiEvent(AnalyticsHelper.ResourceDetailPage.RESOURCE_BANDWIDTH_HISAGENCY_SHOW, this.fromMultiSign);
            } else {
                AnalyticsHelper.ResourceDetailPage.logMultiEvent(AnalyticsHelper.ResourceDetailPage.RESOURCE_ENERGY_HISAGENCY_SHOW, this.fromMultiSign);
            }
        } else if (this.resourceType == 1) {
            AnalyticsHelper.ResourceDetailPage.logMultiEvent(AnalyticsHelper.ResourceDetailPage.RESOURCE_BANDWIDTH_GIVEME_SHOW, this.fromMultiSign);
        } else {
            AnalyticsHelper.ResourceDetailPage.logMultiEvent(AnalyticsHelper.ResourceDetailPage.RESOURCE_ENERGY_GIVEME_SHOW, this.fromMultiSign);
        }
        this.rxManager = new RxManager();
        showLoadingView();
        this.model.getAllAddress().compose(RxSchedulers2.io_main()).subscribe(new Consumer<HashMap<String, NameAddressExtraBean>>() {
            @Override
            public void accept(HashMap<String, NameAddressExtraBean> hashMap) throws Exception {
                AddressMapUtils.getInstance().setAddressMap(hashMap);
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
            }
        });
        getData();
        DelegateForMeAdapter delegateForMeAdapter = new DelegateForMeAdapter();
        this.adapter = delegateForMeAdapter;
        delegateForMeAdapter.bindToRecyclerView(this.recyclerView);
        this.recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getContext()));
        this.noNetView.setReloadable(true);
        this.noNetView.setOnReloadClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                getData();
            }
        });
    }

    public void mappingId(View view) {
        PopupDelegateForMeBinding bind = PopupDelegateForMeBinding.bind(view);
        this.binding = bind;
        this.llRoot = bind.root;
        this.ivPlaceHolder = this.binding.ivPlaceHolder;
        this.recyclerView = this.binding.rvList;
        this.noNetView = this.binding.noNetView;
        this.noDataView = this.binding.noDataView;
        this.tvTitle = this.binding.tvTitle;
        this.tvTag = this.binding.tvTitleTag;
    }

    public void getData() {
        this.model.getStakeResourceForMe(this.address, this.resourceType == 1 ? 0 : 1, this.stakeType).compose(RxSchedulers2.io_main()).subscribe(new IObserver(new ICallback<StakeResourceDetailForMeOutput>() {
            @Override
            public void onResponse(String str, StakeResourceDetailForMeOutput stakeResourceDetailForMeOutput) {
                updateStakeForMeList(stakeResourceDetailForMeOutput);
            }

            @Override
            public void onFailure(String str, String str2) {
                updateStakeListFail(-1L);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                rxManager.add(disposable);
            }
        }, "getStakeResourceForMe"));
    }

    private void showLoadingView() {
        this.recyclerView.setVisibility(View.GONE);
        this.ivPlaceHolder.setVisibility(View.VISIBLE);
        this.noNetView.setVisibility(View.GONE);
        this.noDataView.setVisibility(View.GONE);
    }

    private void showContentView() {
        this.recyclerView.setVisibility(View.VISIBLE);
        this.ivPlaceHolder.setVisibility(View.GONE);
        this.noNetView.setVisibility(View.GONE);
        this.noDataView.setVisibility(View.GONE);
    }

    private void showEmptyView() {
        this.recyclerView.setVisibility(View.GONE);
        this.ivPlaceHolder.setVisibility(View.GONE);
        this.noNetView.setVisibility(View.GONE);
        this.noDataView.setVisibility(View.VISIBLE);
    }

    private void showNoNetView() {
        this.recyclerView.setVisibility(View.GONE);
        this.ivPlaceHolder.setVisibility(View.GONE);
        this.noNetView.setVisibility(View.VISIBLE);
        this.noNetView.setReloadMarginTop(20.0f);
        this.noDataView.setVisibility(View.GONE);
    }

    public void setClick() {
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id == R.id.iv_common_right) {
                    dismiss();
                } else if (id != R.id.root) {
                } else {
                    dismiss();
                }
            }
        };
        this.binding.ivCommonRight.setOnClickListener(noDoubleClickListener2);
        this.binding.root.setOnClickListener(noDoubleClickListener2);
    }

    @Override
    public void updateStakeForMeList(StakeResourceDetailForMeOutput stakeResourceDetailForMeOutput) {
        if (stakeResourceDetailForMeOutput != null) {
            if (stakeResourceDetailForMeOutput.getData() != null && stakeResourceDetailForMeOutput.getData().size() > 0) {
                showContentView();
                this.adapter.addData((Collection) stakeResourceDetailForMeOutput.getData());
                return;
            }
            showEmptyView();
        }
    }

    @Override
    public void updateStakeListFail(long j) {
        showNoNetView();
    }

    @Override
    public void setAddressMap(HashMap<String, NameAddressExtraBean> hashMap) {
        AddressMapUtils.getInstance().setAddressMap(hashMap);
    }
}
