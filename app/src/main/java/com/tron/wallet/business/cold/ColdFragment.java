package com.tron.wallet.business.cold;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.StatusBarUtils;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.business.addwallet.AddWalletActivityV2;
import com.tron.wallet.business.cold.ColdContract;
import com.tron.wallet.business.create.creatwallet.AddWalletType;
import com.tron.wallet.business.walletmanager.detail.WalletDetailActivity;
import com.tron.wallet.business.walletmanager.selectwallet.SelectWalletActivity;
import com.tron.wallet.common.components.qr.ScannerActivity;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.interfaces.PermissionInterface;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.PermissionHelper;
import com.tron.wallet.common.utils.PopupWindowUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.FgColdBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import org.tron.walletserver.Wallet;
public class ColdFragment extends BaseFragment<ColdPresenter, EmptyModel> implements ColdContract.View, PermissionInterface {
    private static final String TAG = "ColdFragment";
    private FgColdBinding binding;
    private String mAddressStr;
    TextView mAddressTv;
    View mCloseView;
    RelativeLayout mColdtip;
    ImageView mIvAdd;
    ImageView mIvShield;
    TextView mNoNetDescTv;
    View mNoNetTipView;
    View mOfflineSignFrameLayout;
    ImageView mOfflineSignIv;
    ImageView mOfflineSignShastaIv;
    TextView mOfflineSignTv;
    private PermissionHelper mPermissionHelper;
    ImageView mQrImageView;
    ImageView mReceiveIv;
    ImageView mReceiveShastaIv;
    TextView mReceiveTv;
    View mReceiveViewFrameLayout;
    ImageView mSafeCloseView;
    View mSafeTipView;
    View mTopViewLayout;
    TextView mWalletNameTv;

    @Override
    public int getPermissionsRequestCode() {
        return 2009;
    }

    @Override
    protected void processData() {
        TouchDelegateUtils.expandViewTouchDelegate(this.mCloseView, UIUtils.dip2px(5.0f), UIUtils.dip2px(5.0f), UIUtils.dip2px(5.0f), UIUtils.dip2px(5.0f));
        TouchDelegateUtils.expandViewTouchDelegate(this.mSafeCloseView, UIUtils.dip2px(5.0f), UIUtils.dip2px(5.0f), UIUtils.dip2px(5.0f), UIUtils.dip2px(5.0f));
        TouchDelegateUtils.expandViewTouchDelegate(this.mWalletNameTv, 10, 10, 10, 10);
        TouchDelegateUtils.expandViewTouchDelegate(this.mIvAdd, 10, 10, 10, 10);
        this.mNoNetDescTv.setClickable(true);
        showOfflineTab(false);
    }

    @Override
    public void refreshOfflineWallet(Wallet wallet) {
        if (wallet != null) {
            String address = wallet.getAddress();
            this.mAddressStr = address;
            this.mAddressTv.setText(address);
            this.mQrImageView.setImageBitmap(WalletUtils.strToQR(this.mAddressStr, UIUtils.dip2px(200.0f), UIUtils.dip2px(200.0f), null));
            this.mWalletNameTv.setText(wallet.getWalletName());
            this.mIvShield.setVisibility(wallet.isShieldedWallet() ? View.VISIBLE : View.GONE);
            TouchDelegateUtils.expandViewTouchDelegate(this.mIvShield, 10, 10, 10, 10);
        }
    }

    @Override
    public void showOrHidenSafeTipView(boolean z) {
        if (z) {
            if (this.mColdtip.getVisibility() == 0) {
                this.mSafeTipView.setVisibility(View.GONE);
                return;
            } else {
                this.mSafeTipView.setVisibility(View.VISIBLE);
                return;
            }
        }
        this.mSafeTipView.setVisibility(View.GONE);
    }

    @Override
    public void showNoNetTipView(boolean z) {
        if (z) {
            ((RelativeLayout.LayoutParams) this.mTopViewLayout.getLayoutParams()).topMargin = UIUtils.getStatusBarHeight();
            this.mNoNetTipView.setVisibility(View.VISIBLE);
            return;
        }
        ((RelativeLayout.LayoutParams) this.mTopViewLayout.getLayoutParams()).topMargin = 0;
        this.mNoNetTipView.setVisibility(View.GONE);
    }

    @Override
    public void onPause() {
        LogUtils.i(TAG, "onPause");
        super.onPause();
    }

    @Override
    public void onResume() {
        LogUtils.i(TAG, "onResume");
        super.onResume();
        ((ColdPresenter) this.mPresenter).updateSelectedWallet();
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setType(0);
        FgColdBinding inflate = FgColdBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        setClick();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId() {
        this.mSafeTipView = this.binding.llBackTip.rlSafeTip;
        this.mCloseView = this.binding.llBackTip.ivClose;
        this.mNoNetTipView = this.binding.llNonetTip.rlNonetTip;
        this.mNoNetDescTv = this.binding.llNonetTip.tvNonetDesc;
        this.mReceiveViewFrameLayout = this.binding.flReceive;
        this.mOfflineSignFrameLayout = this.binding.flOfflineSign;
        this.mReceiveTv = this.binding.tvReceive;
        this.mReceiveIv = this.binding.ivReceive;
        this.mOfflineSignTv = this.binding.tvOfflineSign;
        this.mColdtip = this.binding.llCodeTip.rlColdTip;
        this.mOfflineSignIv = this.binding.ivOfflineSign;
        this.mAddressTv = this.binding.tvAddress;
        this.mQrImageView = this.binding.ivQr;
        this.mWalletNameTv = this.binding.tvWalletname;
        this.mIvAdd = this.binding.ivWalletManager;
        this.mReceiveShastaIv = this.binding.ivReceiveShasta;
        this.mOfflineSignShastaIv = this.binding.ivOfflineSignShasta;
        this.mTopViewLayout = this.binding.rlHeaderInner;
        this.mSafeCloseView = this.binding.llCodeTip.ivColdClose;
        this.mIvShield = this.binding.ivShieldIcon;
    }

    public void setClick() {
        NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                switch (view.getId()) {
                    case R.id.bt_copy:
                        UIUtils.copy(mAddressStr);
                        IToast.getIToast().show(getResources().getString(R.string.copy_susscess));
                        return;
                    case R.id.iv_close:
                        mSafeTipView.setVisibility(View.GONE);
                        return;
                    case R.id.iv_cold_close:
                    case R.id.tv_cold_backup:
                        mColdtip.setVisibility(View.GONE);
                        return;
                    case R.id.iv_nonet_arrow:
                    case R.id.tv_nonet_desc:
                        UIUtils.toColdWallet(mContext);
                        return;
                    case R.id.iv_nonet_tip_close:
                        SpAPI.THIS.setNoNetIsClosed(true);
                        mNoNetTipView.setVisibility(View.GONE);
                        return;
                    case R.id.iv_offline_sign_qr:
                    case R.id.tv_offline_sign_desc:
                        if (!SpAPI.THIS.getCurIsMainChain()) {
                            LogUtils.d(ColdFragment.TAG, "cold wallet not support side chain");
                        }
                        if (!((ColdPresenter) mPresenter).checkWalletWatchOnly()) {
                            scan();
                            return;
                        } else {
                            ToastError(StringTronUtil.getResString(R.string.watch_only_tip));
                            return;
                        }
                    case R.id.iv_shield_icon:
                        if (mIvShield.getVisibility() == 0) {
                            PopupWindowUtil.showShieldTips(mContext, mIvShield, R.string.this_means_shield_wallet);
                            return;
                        }
                        return;
                    case R.id.iv_wallet_manager:
                        Intent intent = new Intent(mContext, AddWalletActivityV2.class);
                        if (((ColdPresenter) mPresenter).getWallet().isShieldedWallet()) {
                            intent.putExtra(AddWalletType.INTENT_KEY_WALLET_TYPE, 1);
                        }
                        startActivity(intent);
                        return;
                    case R.id.ll_offline_sign:
                        showOfflineTab(false);
                        return;
                    case R.id.ll_receive:
                        showOfflineTab(true);
                        return;
                    case R.id.rl_header_inner:
                        Wallet wallet = ((ColdPresenter) mPresenter).getWallet();
                        WalletDetailActivity.startActivity(getActivity(), wallet != null ? wallet.getWalletName() : null, wallet != null ? wallet.isShieldedWallet() : false);
                        return;
                    case R.id.rl_safe_tip:
                        ((ColdPresenter) mPresenter).backup();
                        return;
                    case R.id.tv_backup:
                        ((ColdPresenter) mPresenter).backup();
                        return;
                    case R.id.tv_walletname:
                        SelectWalletActivity.startActivity(getActivity());
                        return;
                    default:
                        return;
                }
            }
        };
        this.binding.llNonetTip.tvNonetDesc.setOnClickListener(noDoubleClickListener);
        this.binding.getRoot().findViewById(R.id.iv_nonet_arrow).setOnClickListener(noDoubleClickListener);
        this.binding.getRoot().findViewById(R.id.tv_backup).setOnClickListener(noDoubleClickListener);
        this.binding.llBackTip.ivClose.setOnClickListener(noDoubleClickListener);
        this.binding.llReceive.setOnClickListener(noDoubleClickListener);
        this.binding.llOfflineSign.setOnClickListener(noDoubleClickListener);
        this.binding.ivWalletManager.setOnClickListener(noDoubleClickListener);
        this.binding.btCopy.setOnClickListener(noDoubleClickListener);
        this.binding.ivOfflineSignQr.setOnClickListener(noDoubleClickListener);
        this.binding.tvWalletname.setOnClickListener(noDoubleClickListener);
        this.binding.tvOfflineSignDesc.setOnClickListener(noDoubleClickListener);
        this.binding.llBackTip.rlSafeTip.setOnClickListener(noDoubleClickListener);
        this.binding.getRoot().findViewById(R.id.iv_nonet_tip_close).setOnClickListener(noDoubleClickListener);
        this.binding.llCodeTip.ivColdClose.setOnClickListener(noDoubleClickListener);
        this.binding.getRoot().findViewById(R.id.tv_cold_backup).setOnClickListener(noDoubleClickListener);
        this.binding.ivShieldIcon.setOnClickListener(noDoubleClickListener);
        this.binding.rlHeaderInner.setOnClickListener(noDoubleClickListener);
    }

    public void showOfflineTab(boolean z) {
        if (z) {
            this.mReceiveViewFrameLayout.setVisibility(View.VISIBLE);
            this.mOfflineSignFrameLayout.setVisibility(View.GONE);
            this.mReceiveTv.setSelected(true);
            this.mOfflineSignTv.setSelected(false);
            this.mReceiveIv.setSelected(true);
            this.mOfflineSignIv.setSelected(false);
            return;
        }
        this.mOfflineSignFrameLayout.setVisibility(View.VISIBLE);
        this.mReceiveViewFrameLayout.setVisibility(View.GONE);
        this.mReceiveTv.setSelected(false);
        this.mOfflineSignTv.setSelected(true);
        this.mReceiveIv.setSelected(false);
        this.mOfflineSignIv.setSelected(true);
    }

    @Override
    public void onHiddenChanged(boolean z) {
        LogUtils.i(TAG, "onHiddenChanged: " + z);
        super.onHiddenChanged(z);
        if (z) {
            return;
        }
        StatusBarUtils.setLightStatusBar(getActivity(), false);
    }

    public void scan() {
        PermissionHelper permissionHelper = new PermissionHelper(this.mContext, this);
        this.mPermissionHelper = permissionHelper;
        permissionHelper.requestPermissions();
    }

    @Override
    public void requestPermissionsFail() {
        ToastError(R.string.error_permission);
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        PermissionHelper permissionHelper = this.mPermissionHelper;
        if (permissionHelper == null || !permissionHelper.requestPermissionsResult(i, strArr, iArr)) {
            super.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    @Override
    public String[] getPermissions() {
        return new String[]{"android.permission.CAMERA"};
    }

    @Override
    public void requestPermissionsSuccess() {
        toScan();
    }

    private void toScan() {
        ScannerActivity.startFromFragment(this);
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        ((ColdPresenter) this.mPresenter).onActivityResult(i, i2, intent);
    }

    @Override
    public void refreshOfflineShastaView() {
        this.mReceiveShastaIv.setVisibility(View.GONE);
        this.mOfflineSignShastaIv.setVisibility(View.GONE);
    }

    @Override
    public void updateColdView() {
        if (TronConfig.hasNet && SpAPI.THIS.isCold()) {
            this.mSafeTipView.setVisibility(View.GONE);
            this.mColdtip.setVisibility(View.VISIBLE);
            return;
        }
        this.mColdtip.setVisibility(View.GONE);
    }
}
