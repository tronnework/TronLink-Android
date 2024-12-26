package com.tron.wallet.business.stakev2.stake.pop.stakev1;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BottomPopupView;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.mutil.MultiSelectAddressActivity;
import com.tron.wallet.business.mutil.bean.MultiSignPermissionData;
import com.tron.wallet.business.mutil.bean.MultiSourcePageEnum;
import com.tron.wallet.business.stakev2.stake.pop.stakev1.StakeDetailListBottomPopup;
import com.tron.wallet.business.stakev2.stake.pop.stakev1.StakeDetailResourceAdapter;
import com.tron.wallet.business.stakev2.stake.pop.stakev1.StakeDetailResourceBean;
import com.tron.wallet.business.stakev2.stake.pop.stakev1.StakeV2PopContract;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AccountUtils;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.OwnerPermissionCheckUtils;
import com.tron.wallet.common.utils.ToastUtil;
import com.tron.wallet.databinding.PopupStakeDetailBottomListBinding;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tron.wallet.ledger.blemodule.utils.Constants;
import com.tronlinkpro.wallet.R;
import io.reactivex.disposables.Disposable;
import j$.util.Objects;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class StakeDetailListBottomPopup extends BottomPopupView implements StakeV2PopContract.IView {
    private static final String ERR_NULL_ACCOUNT = "null account";
    private StakeDetailResourceAdapter adapter;
    private PopupStakeDetailBottomListBinding binding;
    private Protocol.Account controllerAccount;
    private String controllerAddress;
    private boolean fromMultiSign;
    ImageView ivLoading;
    View llRoot;
    private Context mContext;
    private StakeV2PopContract.Presenter mPresenter;
    NoNetView noDataView;
    private OnDismissListener onDismissListener;
    private MultiSignPermissionData permissionData;
    RecyclerView rvList;

    public interface OnClickListener {
        void onClick(Wallet wallet);
    }

    public interface OnDismissListener {
        void onDismiss();
    }

    @Override
    public int getImplLayoutId() {
        return R.layout.popup_stake_detail_bottom_list;
    }

    public StakeDetailListBottomPopup(Context context, boolean z, OnDismissListener onDismissListener, Protocol.Account account, String str, MultiSignPermissionData multiSignPermissionData) {
        super(context);
        this.mContext = context;
        this.fromMultiSign = z;
        this.onDismissListener = onDismissListener;
        this.controllerAccount = account;
        this.controllerAddress = str;
        this.permissionData = multiSignPermissionData;
        this.mPresenter = new StakeV2PopPresenter();
        StakeV2PopModel stakeV2PopModel = new StakeV2PopModel();
        ((StakeV2PopPresenter) this.mPresenter).onStart();
        this.mPresenter.setVM(this, stakeV2PopModel);
    }

    public static StakeDetailListBottomPopup showPopup(Context context, boolean z, OnDismissListener onDismissListener, Protocol.Account account, String str, MultiSignPermissionData multiSignPermissionData) {
        StakeDetailListBottomPopup stakeDetailListBottomPopup = (StakeDetailListBottomPopup) new XPopup.Builder(context).enableDrag(false).asCustom(new StakeDetailListBottomPopup(context, z, onDismissListener, account, str, multiSignPermissionData));
        stakeDetailListBottomPopup.show();
        AnalyticsHelper.StakeHomePage.logMultiEvent(AnalyticsHelper.StakeHomePage.STAKE_V1_UNSTAKE_POP_SHOW, z);
        return stakeDetailListBottomPopup;
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
        this.mPresenter.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        requestData(false);
        StakeDetailResourceAdapter stakeDetailResourceAdapter = new StakeDetailResourceAdapter();
        this.adapter = stakeDetailResourceAdapter;
        stakeDetailResourceAdapter.bindToRecyclerView(this.rvList);
        RecyclerView.ItemAnimator itemAnimator = this.rvList.getItemAnimator();
        if (itemAnimator != null) {
            itemAnimator.setChangeDuration(0L);
        }
        this.adapter.setItemClickCallback(new fun1());
        this.rvList.setLayoutManager(new WrapContentLinearLayoutManager(getContext()));
    }

    public class fun1 implements StakeDetailResourceAdapter.OnUnFreezeBtnClickListener {
        fun1() {
        }

        @Override
        public void onUnfreezeClick(StakeDetailResourceBean stakeDetailResourceBean) {
            if (!TronConfig.hasNet) {
                ToastUtil.getInstance().showToast(mContext, getResources().getString(R.string.net_error));
            } else if (!fromMultiSign && !OwnerPermissionCheckUtils.checkHasOwnerPermission(controllerAccount)) {
                OwnerPermissionCheckUtils.showMultiSignDialog(mContext, R.string.unstake_lack_owner_permission, R.string.unstake_multi_sign, new Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        StakeDetailListBottomPopup.1.this.lambda$onUnfreezeClick$0((Void) obj);
                    }

                    public Consumer andThen(Consumer consumer) {
                        return Objects.requireNonNull(consumer);
                    }
                });
            } else if (permissionData != null && !permissionData.isUnStakePermission()) {
                ToastUtil.getInstance().showToast(mContext, getResources().getString(R.string.do_not_have_multi_permission, getResources().getString(R.string.unstake_1)));
            } else {
                mPresenter.enterConfirm(fromMultiSign, stakeDetailResourceBean);
                if (stakeDetailResourceBean.group.equals(StakeDetailResourceBean.Group.SELF)) {
                    if (stakeDetailResourceBean.getType() == StakeDetailResourceBean.Type.ENERGY) {
                        AnalyticsHelper.logEvent(AnalyticsHelper.StakeHomePage.STAKE_V1_UNSTAKE_MY_ENERGY_POP_CLICK);
                    } else if (stakeDetailResourceBean.getType() == StakeDetailResourceBean.Type.BANDWIDTH) {
                        AnalyticsHelper.logEvent(AnalyticsHelper.StakeHomePage.STAKE_V1_UNSTAKE_MY_BAND_POP_CLICK);
                    }
                } else if (stakeDetailResourceBean.getType() == StakeDetailResourceBean.Type.ENERGY) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.StakeHomePage.STAKE_V1_UNSTAKE_OTHERS_ENERGY_POP_CLICK);
                } else if (stakeDetailResourceBean.getType() == StakeDetailResourceBean.Type.BANDWIDTH) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.StakeHomePage.STAKE_V1_UNSTAKE_OTHERS_BAND_POP_CLICK);
                }
            }
        }

        public void lambda$onUnfreezeClick$0(Void r2) {
            MultiSelectAddressActivity.start(getIContext(), MultiSourcePageEnum.StakeV2);
        }
    }

    @Override
    public void addInnerContent() {
        View inflate = LayoutInflater.from(this.mContext).inflate(getImplLayoutId(), (ViewGroup) this.bottomPopupContainer, false);
        this.binding = PopupStakeDetailBottomListBinding.bind(inflate);
        mappingId();
        setClick();
        this.bottomPopupContainer.addView(inflate);
    }

    public void setClick() {
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id == R.id.iv_common_right) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.SwitchWalletPage.CLICK_CLOSE);
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

    public void mappingId() {
        this.llRoot = this.binding.root;
        this.rvList = this.binding.rvList;
        this.ivLoading = this.binding.ivLoading;
        this.noDataView = this.binding.noDataView;
    }

    @Override
    public Context getIContext() {
        return getContext();
    }

    @Override
    public void updateResourceResult(boolean z, boolean z2, List<StakeDetailResourceBean> list) {
        updateData(z, z2, list, new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public final void onLoadMoreRequested() {
                lambda$updateResourceResult$0();
            }
        });
    }

    public void lambda$updateResourceResult$0() {
        this.mPresenter.requestMore();
    }

    public void updateData(boolean z, boolean z2, List<StakeDetailResourceBean> list, BaseQuickAdapter.RequestLoadMoreListener requestLoadMoreListener) {
        LogUtils.e("updateData", list.toString());
        if (this.adapter == null) {
            this.noDataView.setVisibility(View.VISIBLE);
            this.ivLoading.setVisibility(View.GONE);
            this.rvList.setVisibility(View.GONE);
        } else if (!list.isEmpty()) {
            this.rvList.setVisibility(View.VISIBLE);
            this.ivLoading.setVisibility(View.GONE);
            this.noDataView.setVisibility(View.GONE);
            if (z) {
                this.adapter.addData(0, list, list.size());
                return;
            }
            if (requestLoadMoreListener != null && this.rvList != null && !this.adapter.isLoadMoreEnable()) {
                this.adapter.setOnLoadMoreListener(requestLoadMoreListener, this.rvList);
            }
            this.adapter.addData((Collection) list);
            this.adapter.loadMoreComplete();
        } else {
            boolean z3 = true;
            this.adapter.loadMoreEnd(true);
            z3 = (z2 && this.adapter.getData().isEmpty()) ? false : false;
            this.noDataView.setVisibility(z3 ? View.VISIBLE : View.GONE);
            this.rvList.setVisibility(z3 ? View.GONE : View.VISIBLE);
            if (z2) {
                this.ivLoading.setVisibility(View.GONE);
            }
        }
    }

    private void requestData(boolean z) {
        this.mPresenter.ensureAccount(z, this.controllerAccount, this.controllerAddress).compose(RxSchedulers2.io_main()).subscribe(new IObserver(getAccountCallback(), "ensureAccount"));
    }

    private ICallback<Protocol.Account> getAccountCallback() {
        return new ICallback<Protocol.Account>() {
            @Override
            public void onResponse(String str, Protocol.Account account) {
                if (AccountUtils.isNullAccount(account)) {
                    onFailure(Constants.BluetoothLogLevel.ERROR, StakeDetailListBottomPopup.ERR_NULL_ACCOUNT);
                }
                controllerAccount = account;
                mPresenter.requestStakeResource(account);
            }

            @Override
            public void onFailure(String str, String str2) {
                if (TextUtils.equals(str2, StakeDetailListBottomPopup.ERR_NULL_ACCOUNT)) {
                    return;
                }
                ToastUtil.getInstance().show(getIContext(), getIContext().getResources().getString(R.string.time_out));
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mPresenter.addDisposable(disposable);
            }
        };
    }
}
