package com.tron.wallet.business.confirm.core.samsung;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.google.protobuf.InvalidProtocolBufferException;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.core.ConfirmTransactionContract;
import com.tron.wallet.business.confirm.core.ConfirmTransactionModel;
import com.tron.wallet.business.confirm.core.ConfirmTransactionPresenter;
import com.tron.wallet.business.confirm.core.pending.ConfirmPendingFragment;
import com.tron.wallet.business.confirm.core.pending.State;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.FreezeUnFreezeParam;
import com.tron.wallet.business.confirm.parambuilder.bean.TransferParam;
import com.tron.wallet.business.samsung.SamsungSDKWrapper;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.FailUtils;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.ToastUtil;
import com.tron.wallet.databinding.FgConfirmSamsungBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.List;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class SamsungConfirmFragment extends BaseFragment<ConfirmTransactionPresenter, ConfirmTransactionModel> implements ConfirmTransactionContract.View {
    BaseParam baseParam;
    private FgConfirmSamsungBinding binding;
    private ConfirmPendingFragment confirmPendingFragment;
    Wallet mWallet;
    View pendingView;

    @Override
    public BaseParam getBaseParam() {
        return this.baseParam;
    }

    @Override
    public String getpassword() {
        return ConfirmTransactionContract.View.-CC.$default$getpassword(this);
    }

    @Override
    public void setButtonEnable(boolean z) {
        ConfirmTransactionContract.View.-CC.$default$setButtonEnable(this, z);
    }

    public void setParam(BaseParam baseParam) {
        this.baseParam = baseParam;
    }

    @Override
    public void setRootV(boolean z) {
    }

    @Override
    public void showErrorText() {
        ConfirmTransactionContract.View.-CC.$default$showErrorText(this);
    }

    @Override
    public void showErrorText(String str) {
        ConfirmTransactionContract.View.-CC.$default$showErrorText(this, str);
    }

    @Override
    public void updateUI() {
        ConfirmTransactionContract.View.-CC.$default$updateUI(this);
    }

    @Override
    protected void processData() {
        BaseParam baseParam;
        if (this.mPresenter != 0) {
            ((ConfirmTransactionPresenter) this.mPresenter).onStart();
            this.mWallet = ((ConfirmTransactionPresenter) this.mPresenter).getCurrentWallet();
        }
        if (this.mWallet == null || (baseParam = this.baseParam) == null) {
            StringBuilder sb = new StringBuilder("ConfirmLedgerFragment: ");
            sb.append(this.mWallet);
            SentryUtil.captureMessage(sb.toString() == null ? "mWallet == null" : "baseParam == null");
            exit();
            return;
        }
        int type = baseParam.getType();
        BaseParam baseParam2 = this.baseParam;
        if (baseParam2 instanceof FreezeUnFreezeParam) {
            type = ((FreezeUnFreezeParam) baseParam2).doFreezeType;
        }
        goToSamsung(type, this.baseParam.getTransactionBean().getBytes(), makeAddressFromParam(this.baseParam));
    }

    private void goToSamsung(int i, List<byte[]> list, String str) {
        if (StringTronUtil.isEmpty(SamsungSDKWrapper.checkSeedHashEmpty(this.mContext, false))) {
            FailUtils.showSamsungKeystoreUnAvailableDialog(this.mContext, 1);
        } else {
            startSignSamsung(i, 0, list, str, new ArrayList<>());
        }
    }

    private String makeAddressFromParam(BaseParam baseParam) {
        if (baseParam instanceof TransferParam) {
            return ((TransferParam) baseParam).getFromAddress();
        }
        return WalletUtils.getSelectedWallet().getAddress();
    }

    public void startSignSamsung(final int i, final int i2, final List<byte[]> list, final String str, final ArrayList<Protocol.Transaction> arrayList) {
        byte[] bArr = list.get(i2);
        try {
            WalletUtils.printTransaction(Protocol.Transaction.parseFrom(bArr));
        } catch (InvalidProtocolBufferException e) {
            LogUtils.e(e);
        }
        SamsungSDKWrapper.startSign(this.mContext, str, bArr, new SamsungSDKWrapper.SignCallBack() {
            @Override
            public void onSignSuccess(byte[] bArr2) {
                try {
                    if (i2 == list.size() - 1) {
                        if (mPresenter != 0) {
                            arrayList.add(Protocol.Transaction.parseFrom(bArr2));
                            ((ConfirmTransactionPresenter) mPresenter).mRxManager.post(Event.SWAP_CONFIRM_SWAP, "");
                            ((ConfirmTransactionPresenter) mPresenter).broadcastTransaction(0, arrayList, i);
                        }
                        if (mPresenter == 0 || ((ConfirmTransactionPresenter) mPresenter).mRxManager == null) {
                            return;
                        }
                        ((ConfirmTransactionPresenter) mPresenter).mRxManager.post(Event.SAMSUNG_KEYSTORE_BROADCAST_TRANSACTION, "");
                        return;
                    }
                    arrayList.add(Protocol.Transaction.parseFrom(bArr2));
                    startSignSamsung(i, i2 + 1, list, str, arrayList);
                } catch (Exception e2) {
                    LogUtils.e(e2);
                }
            }

            @Override
            public void onFailure(String str2) {
                LogUtils.d("ConfirmTransaction", "startVerifySeed error:" + str2);
                if (str2 != null) {
                    ToastUtil.getInstance().showToast(getIContext(), getIContext().getString(R.string.sign_fail));
                }
                exit();
            }
        });
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgConfirmSamsungBinding inflate = FgConfirmSamsungBinding.inflate(layoutInflater, viewGroup, false);
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
        this.pendingView = this.binding.flMain;
    }

    @Override
    public Intent getIIntent() {
        return this.mContext.getIntent();
    }

    @Override
    public boolean isActives() {
        return this.baseParam.isActives();
    }

    @Override
    public void showLoadingFragment() {
        runOnUIThread(new OnMainThread() {
            @Override
            public void doInUIThread() {
                pendingView.setVisibility(View.VISIBLE);
                SamsungConfirmFragment samsungConfirmFragment = SamsungConfirmFragment.this;
                samsungConfirmFragment.confirmPendingFragment = ConfirmPendingFragment.getInstance(samsungConfirmFragment.baseParam);
                if (confirmPendingFragment.isAdded()) {
                    getChildFragmentManager().beginTransaction().show(confirmPendingFragment).commitAllowingStateLoss();
                } else {
                    getChildFragmentManager().beginTransaction().add(R.id.fl_main, confirmPendingFragment).show(confirmPendingFragment).commitAllowingStateLoss();
                }
            }
        });
    }

    @Override
    public void updateLoadingFragment(final GrpcAPI.Return r2, final State state, final int i) {
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$updateLoadingFragment$0(state, r2, i);
            }
        });
    }

    public void lambda$updateLoadingFragment$0(State state, GrpcAPI.Return r4, int i) {
        ConfirmPendingFragment confirmPendingFragment = this.confirmPendingFragment;
        if (confirmPendingFragment != null) {
            confirmPendingFragment.update(state, r4.toByteArray(), this.baseParam, i);
        }
    }

    public void update() {
        if (this.mPresenter != 0) {
            ((ConfirmTransactionPresenter) this.mPresenter).onStart();
        }
    }

    @Override
    public void onKeyDownChild(int i, KeyEvent keyEvent) {
        ConfirmPendingFragment confirmPendingFragment = this.confirmPendingFragment;
        if (confirmPendingFragment != null) {
            confirmPendingFragment.onKeyDownChild(i, keyEvent);
        }
    }
}
