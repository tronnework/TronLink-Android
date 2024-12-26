package com.tron.wallet.business.ledger.manage;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.wallet.business.ledger.manage.EquipmentAdapter;
import com.tron.wallet.business.ledger.manage.EquipmentContract;
import com.tron.wallet.business.ledger.manage.LedgerProgressView;
import com.tron.wallet.business.ledger.search.PermissionLedgerHelper;
import com.tron.wallet.business.ledger.search.SearchLedgerActivity;
import com.tron.wallet.business.walletmanager.importwallet.base.repo.entity.BleRepoDevice;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.common.interfaces.CloseClickListener;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.databinding.FgEquipmentBinding;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class EquipmentFragment extends BaseFragment<EquipmentPresenter, EmptyModel> implements EquipmentContract.View {
    private EquipmentAdapter adapter;
    private FgEquipmentBinding binding;
    View footerView;
    private List<EquipmentBean> list;
    RecyclerView mEquipmentRecyclerView;
    RelativeLayout noDeviceView;
    private PermissionLedgerHelper permissionHelper;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.permissionHelper = new PermissionLedgerHelper(this);
    }

    @Override
    protected void processData() {
        mappingId();
        ((EquipmentPresenter) this.mPresenter).getDevices();
        this.adapter = new EquipmentAdapter(getIContext(), EquipmentAdapter.TYPE.MANAGE, new EquipmentItemListener() {
            @Override
            public void itemClick(final int i, final EquipmentBean equipmentBean) {
                AnalyticsHelper.logEvent(AnalyticsHelper.LedgerPage.CLICK_LEDGER_MANAGE_NAME);
                permissionHelper.checkPermissions(new PermissionLedgerHelper.OnPermissionResultCallback() {
                    @Override
                    public void onSuccess() {
                        if (!equipmentBean.isConnected()) {
                            ((EquipmentPresenter) mPresenter).connectEquipment(equipmentBean.getDevice(), i);
                        }
                        if (list.size() > 1) {
                            for (int i2 = 0; i2 < list.size(); i2++) {
                                if (i2 != i && ((EquipmentBean) list.get(i2)).isConnected()) {
                                    ((EquipmentPresenter) mPresenter).disconnectEquipment(((EquipmentBean) list.get(i2)).getDevice(), i2);
                                }
                            }
                        }
                    }

                    @Override
                    public void onBluetoothFail() {
                        toast(getResources().getString(R.string.bluetooth_not_enable_toast));
                    }

                    @Override
                    public void onLocationFail() {
                        toast(getResources().getString(R.string.access_location_toast));
                    }
                });
            }

            @Override
            public void disconnectEquipment(BleRepoDevice bleRepoDevice, int i) {
                AnalyticsHelper.logEvent(AnalyticsHelper.LedgerPage.CLICK_LEDGER_MANAGE_DISCONNECT);
                ((EquipmentPresenter) mPresenter).disconnectEquipment(bleRepoDevice, i);
            }

            @Override
            public void importAddress(BleRepoDevice bleRepoDevice) {
                AnalyticsHelper.logEvent(AnalyticsHelper.LedgerPage.CLICK_LEDGER_MANAGE_IMPORT_ADDRESS);
                ((EquipmentPresenter) mPresenter).importAddress(bleRepoDevice);
            }

            @Override
            public void edit(int i) {
                AnalyticsHelper.logEvent(AnalyticsHelper.LedgerPage.CLICK_LEDGER_MANAGE_EDIT_REMOVE);
                EquipmentHelper.get().showRemoveDialog(getIContext(), mEquipmentRecyclerView, i, ((EquipmentBean) list.get(i)).getDevice(), new EquipmentRemoveListener() {
                    @Override
                    public void remove(int i2, BleRepoDevice bleRepoDevice) {
                        AnalyticsHelper.logEvent(AnalyticsHelper.LedgerPage.CLICK_LEDGER_MANAGE_REMOVE);
                        removeEquipment(i2, bleRepoDevice);
                    }
                });
            }
        });
        this.mEquipmentRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getIContext(), 1, false));
        this.mEquipmentRecyclerView.setAdapter(this.adapter);
        this.footerView = getFooterView(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.LedgerPage.CLICK_LEDGER_MANAGE_ADD_NEW_DEVICE);
                permissionHelper.checkPermissions(new PermissionLedgerHelper.OnPermissionResultCallback() {
                    @Override
                    public void onSuccess() {
                        SearchLedgerActivity.start(mContext);
                    }

                    @Override
                    public void onBluetoothFail() {
                        toast(getResources().getString(R.string.bluetooth_not_enable_toast));
                    }

                    @Override
                    public void onLocationFail() {
                        toast(getResources().getString(R.string.access_location_toast));
                    }
                });
            }
        });
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgEquipmentBinding inflate = FgEquipmentBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId() {
        this.mEquipmentRecyclerView = this.binding.equipmentList;
        this.noDeviceView = this.binding.noDeviceLayout.getRoot();
    }

    private View getFooterView(final View.OnClickListener onClickListener) {
        View inflate = getLayoutInflater().inflate(R.layout.equipment_footer_view, (ViewGroup) this.mEquipmentRecyclerView.getParent(), false);
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onClick(view);
            }
        });
        return inflate;
    }

    @Override
    public void updateEquipmentList(List<EquipmentBean> list) {
        List<EquipmentBean> list2 = this.list;
        if (list2 == null || list2.size() != list.size()) {
            this.adapter.removeAllFooterView();
            if (list != null && list.size() > 0) {
                this.noDeviceView.setVisibility(View.GONE);
            }
            this.list = list;
            this.adapter.setNewData(list);
            this.adapter.notifyDataSetChanged();
            if (this.adapter.getFooterLayoutCount() == 0) {
                this.adapter.addFooterView(this.footerView, 0);
            }
        }
    }

    public void removeEquipment(int i, BleRepoDevice bleRepoDevice) {
        EquipmentHelper.get().removeDevice(getIContext(), i, bleRepoDevice, new EquipmentRemoveListener() {
            @Override
            public void remove(int i2, BleRepoDevice bleRepoDevice2) {
                list.remove(i2);
                adapter.notifyItemRemoved(i2);
                ((EquipmentPresenter) mPresenter).removeEquipment(bleRepoDevice2, i2);
                if (list.size() == 0) {
                    noDeviceView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void connectedFail(int i, BleRepoDevice bleRepoDevice) {
        EquipmentHelper.get().showConnectDeviceFailed(getIContext(), this.mEquipmentRecyclerView, i, bleRepoDevice, new EquipmentFailListener() {
            @Override
            public void retry(int i2, BleRepoDevice bleRepoDevice2) {
                ((EquipmentPresenter) mPresenter).connectEquipment(bleRepoDevice2, i2);
            }
        });
    }

    @Override
    public PopupWindow showLoadingPop(BleRepoDevice bleRepoDevice, LedgerProgressView.STATUS status, CloseClickListener closeClickListener) {
        return EquipmentHelper.get().showLoadingDialog(getIContext(), this.mEquipmentRecyclerView, bleRepoDevice.getName(), status, closeClickListener);
    }

    @Override
    public void updateItemStatus(int i, boolean z) {
        EquipmentBean equipmentBean = this.list.get(i);
        equipmentBean.setConnected(z);
        this.list.set(i, equipmentBean);
        this.adapter.notifyItemChanged(i);
    }

    public void onRequestPermissionResult(int i, String[] strArr, int[] iArr) {
        PermissionLedgerHelper permissionLedgerHelper = this.permissionHelper;
        if (permissionLedgerHelper == null || permissionLedgerHelper.getPermissionHelper() == null) {
            return;
        }
        this.permissionHelper.getPermissionHelper().requestPermissionsResult(i, strArr, iArr);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((EquipmentPresenter) this.mPresenter).refresh();
    }
}
