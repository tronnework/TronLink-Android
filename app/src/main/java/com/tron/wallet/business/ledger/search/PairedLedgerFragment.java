package com.tron.wallet.business.ledger.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.ledger.manage.EquipmentAdapter;
import com.tron.wallet.business.ledger.manage.EquipmentBean;
import com.tron.wallet.business.ledger.manage.EquipmentHelper;
import com.tron.wallet.business.ledger.manage.EquipmentItemHolder;
import com.tron.wallet.business.ledger.manage.LedgerProgressView;
import com.tron.wallet.business.tabmy.joincommunity.JoinCommunityActivity;
import com.tron.wallet.business.walletmanager.importwallet.base.SelectAddressActivity;
import com.tron.wallet.business.walletmanager.importwallet.base.UnableGetAddressActivity;
import com.tron.wallet.common.interfaces.CloseClickListener;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.databinding.AcLedgerPairedBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.ledger.bleclient.BleClientManager;
import com.tron.wallet.ledger.bleclient.LedgerTrx;
import com.tron.wallet.ledger.bleclient.OnBleError;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tron.wallet.ledger.blemodule.errors.BleError;
import com.tronlinkpro.wallet.R;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
public class PairedLedgerFragment extends BaseFragment<EmptyPresenter, EmptyModel> {
    private AcLedgerPairedBinding binding;
    Button btNext;
    private EquipmentBean equipmentBean;
    private EquipmentItemHolder itemHolder;
    ImageView ivState;
    View llItem;
    View ll_root;
    private int nextAction;
    private Disposable openDisposable;
    TextView tvHelp;
    TextView tvState;
    TextView tvStateTips;

    public static PairedLedgerFragment newInstance(EquipmentBean equipmentBean, int i) {
        PairedLedgerFragment pairedLedgerFragment = new PairedLedgerFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("bean", equipmentBean);
        bundle.putInt("from", i);
        pairedLedgerFragment.setArguments(bundle);
        return pairedLedgerFragment;
    }

    @Override
    protected void processData() {
        Bundle arguments = getArguments();
        this.equipmentBean = (EquipmentBean) arguments.getSerializable("bean");
        this.nextAction = arguments.getInt("from");
        EquipmentItemHolder equipmentItemHolder = new EquipmentItemHolder(this.llItem);
        this.itemHolder = equipmentItemHolder;
        equipmentItemHolder.onBind(getIContext(), this.equipmentBean, EquipmentAdapter.TYPE.SEARCH);
        if (this.nextAction == 1) {
            this.btNext.setText(getResources().getString(R.string.back_to_sign));
        }
        if (this.equipmentBean.isConnected()) {
            this.tvStateTips.setText(String.format(getResources().getString(R.string.paired_success_info), this.equipmentBean.getDevice().getName()));
            this.btNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    lambda$processData$2(view);
                }
            });
        } else {
            this.ivState.setImageResource(R.mipmap.red_result);
            this.tvState.setText(getResources().getString(R.string.paired_fail));
            this.tvStateTips.setText(String.format(getResources().getString(R.string.paired_fail_info), this.equipmentBean.getDevice().getName()));
            this.llItem.setVisibility(View.GONE);
            this.btNext.setText(getResources().getString(R.string.try_again));
            this.btNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    lambda$processData$3(view);
                }
            });
            this.tvHelp.setVisibility(View.VISIBLE);
            TouchDelegateUtils.expandViewTouchDelegate(this.tvHelp, 10, 10, 10, 10);
            this.tvHelp.setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    lambda$processData$4(view);
                }
            });
        }
        AnalyticsHelper.logEvent(AnalyticsHelper.LedgerPage.ENTER_LEDGER_SELECT_ADDRESS_PAGE);
    }

    public void lambda$processData$2(View view) {
        if (this.nextAction == 1) {
            getActivity().finish();
            return;
        }
        final PopupWindow showLoadingDialog = EquipmentHelper.get().showLoadingDialog(getIContext(), this.ll_root, this.equipmentBean.getDevice().getName(), LedgerProgressView.STATUS.OPEN, new CloseClickListener() {
            @Override
            public final void onClose() {
                lambda$processData$0();
            }
        });
        try {
            this.openDisposable = new LedgerTrx(BleClientManager.getInstance().getTransport(this.equipmentBean.getDevice().getMac())).openApp().compose(RxSchedulers2.io_main()).subscribe(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    lambda$processData$1(showLoadingDialog, (Boolean) obj);
                }
            }, new OnBleError() {
                @Override
                public void accept(Object obj) {
                    accept((Throwable) obj);
                }

                @Override
                public void accept(Throwable th) {
                    OnBleError.-CC.$default$accept((OnBleError) this, th);
                }

                @Override
                public void onError(BleError bleError) {
                    showLoadingDialog.dismiss();
                    LogUtils.e(bleError);
                    UnableGetAddressActivity.start(getIContext(), equipmentBean.getDevice().getName(), true);
                }
            });
        } catch (BleError e) {
            showLoadingDialog.dismiss();
            e.printStackTrace();
        }
    }

    public void lambda$processData$0() {
        Disposable disposable = this.openDisposable;
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.openDisposable.dispose();
    }

    public void lambda$processData$1(PopupWindow popupWindow, Boolean bool) throws Exception {
        popupWindow.dismiss();
        SelectAddressActivity.startForLedger(getIContext(), this.equipmentBean.getDevice().getMac(), this.equipmentBean.getDevice().getName(), true);
    }

    public void lambda$processData$3(View view) {
        ((SearchLedgerActivity) getIContext()).toSearchView();
    }

    public void lambda$processData$4(View view) {
        if (IRequest.isShasta()) {
            Toast(R.string.not_support_shasta);
        } else if (SpAPI.THIS.isCold()) {
            Toast(R.string.not_support_cold_wallet);
        } else {
            go(JoinCommunityActivity.class);
        }
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        AcLedgerPairedBinding inflate = AcLedgerPairedBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        ConstraintLayout root = inflate.getRoot();
        mappingId();
        return root;
    }

    public void mappingId() {
        this.ll_root = this.binding.pairedRoot;
        this.ivState = this.binding.rlState;
        this.tvState = this.binding.tvLedgerState;
        this.tvStateTips = this.binding.tvLedgerStateTips;
        this.llItem = this.binding.rlSelectedLedger.getRoot();
        this.btNext = this.binding.btGo;
        this.tvHelp = this.binding.tvHelp;
    }

    @Override
    public void onDestroyView() {
        Disposable disposable = this.openDisposable;
        if (disposable != null && !disposable.isDisposed()) {
            this.openDisposable.dispose();
        }
        this.binding = null;
        super.onDestroyView();
    }
}
