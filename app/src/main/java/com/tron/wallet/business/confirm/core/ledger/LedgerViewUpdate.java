package com.tron.wallet.business.confirm.core.ledger;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.ledger.manage.EquipmentAdapter;
import com.tron.wallet.business.ledger.manage.EquipmentBean;
import com.tron.wallet.business.ledger.manage.LedgerProgressView;
import com.tron.wallet.business.ledger.search.SearchLedgerActivity;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.FgConfirmLedgerBinding;
import com.tron.wallet.ledger.bleclient.BleErrorHelper;
import com.tron.wallet.ledger.blemodule.errors.BleError;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class LedgerViewUpdate {
    private FgConfirmLedgerBinding binding;
    private String equipmentName;
    View flPending;
    ImageView ivCloseForAddressError;
    ImageView ivCloseForConnectError;
    ImageView ivCloseForGif;
    ImageView ivCloseForHardwarelist;
    LedgerProgressView ledgerGifView;
    View llHash;
    private Context mContext;
    RecyclerView rcHardware;
    RelativeLayout rlConnectFail;
    RelativeLayout rlDeviceNotMatched;
    RelativeLayout rlHardwareList;
    RelativeLayout rlLedgerGif;
    View rlLedgerView;
    private boolean searchingBle;
    TextView tvConnectFailContent;
    TextView tvHash;
    private View view;

    enum Status {
        GifStart,
        List,
        AddressError,
        ConnectError
    }

    public boolean isSearchingBle() {
        return this.searchingBle;
    }

    public void unBind() {
        this.binding = null;
    }

    public void setClick() {
        NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id != R.id.il_pop_hardware_connect_fail) {
                    switch (id) {
                        case R.id.iv_close_for_address_error:
                        case R.id.iv_close_for_connect_error:
                        case R.id.iv_close_for_gif:
                        case R.id.iv_close_for_hardwarelist:
                            break;
                        default:
                            return;
                    }
                }
                ((Activity) mContext).finish();
            }
        };
        this.ivCloseForConnectError.setOnClickListener(noDoubleClickListener);
        this.ivCloseForHardwarelist.setOnClickListener(noDoubleClickListener);
        this.ivCloseForGif.setOnClickListener(noDoubleClickListener);
        this.ivCloseForAddressError.setOnClickListener(noDoubleClickListener);
    }

    public LedgerViewUpdate(Context context, View view) {
        this.view = view;
        this.mContext = context;
        mappingId(view);
        setClick();
    }

    public void setLocalDeviceList(LayoutInflater layoutInflater, EquipmentAdapter equipmentAdapter, List<EquipmentBean> list) {
        if (list.size() > 5) {
            this.rcHardware.getLayoutParams().height = (UIUtils.getScreenHeight(this.mContext) * 2) / 3;
        }
        this.rcHardware.setLayoutManager(new WrapContentLinearLayoutManager(this.mContext, 1, false));
        equipmentAdapter.setFooterView(getFooterView(layoutInflater), 0);
        equipmentAdapter.setNewData(list);
        this.rcHardware.setAdapter(equipmentAdapter);
    }

    private View getFooterView(LayoutInflater layoutInflater) {
        View inflate = layoutInflater.inflate(R.layout.equipment_footer_view, (ViewGroup) this.rcHardware.getParent(), false);
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.LedgerPage.CLICK_LEDGER_CONFIRM_TRANSACTION_ADD_NEW_DEVICE);
                SearchLedgerActivity.start(mContext, 1);
                searchingBle = true;
                ((Activity) mContext).finish();
            }
        });
        return inflate;
    }

    public void setStatus(Status status) {
        int i = fun4.$SwitchMap$com$tron$wallet$business$confirm$core$ledger$LedgerViewUpdate$Status[status.ordinal()];
        if (i == 1) {
            this.ledgerGifView.setEquipmentName(this.equipmentName);
            setVisibility(this.rlLedgerGif, this.rlDeviceNotMatched, this.rlHardwareList, this.rlConnectFail);
            this.ledgerGifView.setStatus(LedgerProgressView.STATUS.LOADING);
            TouchDelegateUtils.expandViewTouchDelegate(this.ivCloseForGif, UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f));
        } else if (i == 2) {
            setVisibility(this.rlHardwareList, this.rlDeviceNotMatched, this.rlLedgerGif, this.rlConnectFail);
            TouchDelegateUtils.expandViewTouchDelegate(this.ivCloseForHardwarelist, UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f));
        } else if (i == 3) {
            setVisibility(this.rlConnectFail, this.rlDeviceNotMatched, this.rlHardwareList, this.rlLedgerGif);
            TouchDelegateUtils.expandViewTouchDelegate(this.ivCloseForConnectError, UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f));
        } else if (i != 4) {
        } else {
            setVisibility(this.rlDeviceNotMatched, this.rlHardwareList, this.rlConnectFail, this.rlLedgerGif);
            TouchDelegateUtils.expandViewTouchDelegate(this.ivCloseForAddressError, UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f));
        }
    }

    public void setHash(final String str) {
        this.view.post(new Runnable() {
            @Override
            public final void run() {
                lambda$setHash$0(str);
            }
        });
    }

    public void lambda$setHash$0(String str) {
        this.llHash.setVisibility(View.VISIBLE);
        this.tvHash.setText(str);
    }

    public static class fun4 {
        static final int[] $SwitchMap$com$tron$wallet$business$confirm$core$ledger$LedgerViewUpdate$Status;
        static final int[] $SwitchMap$com$tron$wallet$business$ledger$manage$LedgerProgressView$STATUS;

        static {
            int[] iArr = new int[LedgerProgressView.STATUS.values().length];
            $SwitchMap$com$tron$wallet$business$ledger$manage$LedgerProgressView$STATUS = iArr;
            try {
                iArr[LedgerProgressView.STATUS.OPEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$ledger$manage$LedgerProgressView$STATUS[LedgerProgressView.STATUS.CONFIRM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[Status.values().length];
            $SwitchMap$com$tron$wallet$business$confirm$core$ledger$LedgerViewUpdate$Status = iArr2;
            try {
                iArr2[Status.GifStart.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$confirm$core$ledger$LedgerViewUpdate$Status[Status.List.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$confirm$core$ledger$LedgerViewUpdate$Status[Status.ConnectError.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$confirm$core$ledger$LedgerViewUpdate$Status[Status.AddressError.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public void setGifStatus(LedgerProgressView.STATUS status) {
        int i = fun4.$SwitchMap$com$tron$wallet$business$ledger$manage$LedgerProgressView$STATUS[status.ordinal()];
        if (i == 1) {
            this.ledgerGifView.setStatus(LedgerProgressView.STATUS.OPEN);
        } else if (i != 2) {
        } else {
            this.ledgerGifView.setStatus(LedgerProgressView.STATUS.CONFIRM);
        }
    }

    public void setEquipmentName(String str) {
        this.equipmentName = str;
        this.tvConnectFailContent.setText(this.mContext.getResources().getString(R.string.unable_connect_ledger, str));
    }

    public BleErrorHelper.OnGetAddressErrorHandler ToastGetAddress() {
        return LedgerErrorToast.getInstance(true).ToastGetAddress(this.mContext, null);
    }

    public BleErrorHelper.OnConnectErrorHandler ToastOpen() {
        return new BleErrorHelper.OnConnectErrorHandler(new BleErrorHelper.OnConnectErrorCallback() {
            @Override
            public void onPreError(BleError bleError) {
                LogUtils.e("Ble", bleError);
            }

            @Override
            public void onDeviceNotFound(BleError bleError) {
                setStatus(Status.ConnectError);
            }

            @Override
            public void onConnectionDisconnected(BleError bleError) {
                setStatus(Status.ConnectError);
            }

            @Override
            public void onTimeout(BleError bleError) {
                setStatus(Status.ConnectError);
            }

            @Override
            public void onUnKnowError(BleError bleError) {
                setStatus(Status.ConnectError);
            }
        });
    }

    public BleErrorHelper.OnOpenTronAppErrorHandler ToastOpenTronApp() {
        return LedgerErrorToast.getInstance(true).ToastOpenTronApp(this.mContext, null);
    }

    public BleErrorHelper.OnSignErrorHandler ToastSign() {
        return LedgerErrorToast.getInstance(true).ToastSign(this.mContext, null);
    }

    public void showLoadingFragment() {
        setVisibility(this.flPending, this.rlLedgerView);
    }

    private void setVisibility(View view, View... viewArr) {
        view.setVisibility(View.VISIBLE);
        for (View view2 : viewArr) {
            view2.setVisibility(View.GONE);
        }
    }

    public void mappingId(View view) {
        FgConfirmLedgerBinding bind = FgConfirmLedgerBinding.bind(view);
        this.binding = bind;
        this.rcHardware = bind.ilPopHardwarelist.rcHardware;
        this.ivCloseForConnectError = this.binding.ilPopHardwareConnectFail.ivCloseForConnectError;
        this.ivCloseForHardwarelist = this.binding.ilPopHardwarelist.ivCloseForHardwarelist;
        this.ivCloseForGif = this.binding.ilPopLedgerConfirmGif.ivCloseForGif;
        this.ivCloseForAddressError = this.binding.ilPopHardwareAddressFail.ivCloseForAddressError;
        this.rlDeviceNotMatched = this.binding.ilPopHardwareAddressFail.rlDeviceNotMatched;
        this.rlConnectFail = this.binding.ilPopHardwareConnectFail.rlConnectFail;
        this.rlHardwareList = this.binding.ilPopHardwarelist.rlHardwarelist;
        this.rlLedgerGif = this.binding.ilPopLedgerConfirmGif.rlLedgerGif;
        this.ledgerGifView = this.binding.ilPopLedgerConfirmGif.loadingView;
        this.tvConnectFailContent = this.binding.ilPopHardwareConnectFail.tvConnectFailContent;
        this.llHash = this.binding.ilPopLedgerConfirmGif.llHash;
        this.tvHash = this.binding.ilPopLedgerConfirmGif.tvHash;
        this.rlLedgerView = this.binding.ledgerView;
        this.flPending = this.binding.flMain;
    }
}
