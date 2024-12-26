package com.tron.wallet.business.receive;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.StatusBarUtils;
import com.tron.tron_base.frame.utils.StringTronUtil;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.business.transfer.share.ShareHelper;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.config.TronSetting;
import com.tron.wallet.common.interfaces.PermissionInterface;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.PermissionHelper;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.AcReceiveBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.tron.walletserver.Wallet;
public class ReceiveActivity extends BaseActivity<EmptyPresenter, EmptyModel> implements PermissionInterface {
    private String addRess;
    private AcReceiveBinding binding;
    private String bitmapCachePath;
    ImageView copy;
    private View copyView;
    LinearLayout liSave;
    LinearLayout liShare;
    private String name;
    ImageView qr;
    RelativeLayout rlTop;
    private TokenBean trc20Output;
    TextView tvAddress;
    TextView tvScanPay;
    TextView tvWalletName;
    TextView tvWatchOnly;
    private boolean isClick = false;
    private final PermissionHelper mPermissionHelper = new PermissionHelper(this, this);
    private final AtomicReference<Ops> ops = new AtomicReference<>(Ops.SAVE);

    public enum Ops {
        SAVE,
        SHARE
    }

    @Override
    public int getPermissionsRequestCode() {
        return 2004;
    }

    @Override
    protected void setLayout() {
        setBackground(0, R.mipmap.ic_bg_receive);
        AcReceiveBinding inflate = AcReceiveBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 1);
        mappingId();
        StatusBarUtils.setLightStatusBar(this, false);
        setHeaderBar(getResources().getString(R.string.receive_asset));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.tvWatchOnly = this.binding.tvReceiveWatchonly;
        this.tvWalletName = this.binding.tvWalletName;
        this.tvAddress = this.binding.tvAddress;
        this.tvScanPay = this.binding.tvScanQrAndPay;
        this.qr = this.binding.qr;
        this.liSave = this.binding.liSave;
        this.liShare = this.binding.liShare;
        this.rlTop = this.binding.rlTop;
        this.copy = this.binding.copy;
    }

    private void shareOrSaveContentImage(final Ops ops) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(!TextUtils.isEmpty(this.bitmapCachePath));
        if (atomicBoolean.get()) {
            File file = new File(this.bitmapCachePath);
            atomicBoolean.set(file.exists() && file.length() > 0);
        }
        if (atomicBoolean.get() && ops == Ops.SHARE) {
            LogUtils.w("ReceiveActivity", String.format("has saved the image file %s", this.bitmapCachePath));
            Observable.just(this.bitmapCachePath).subscribe(new IObserver(getCallback(ops), "shareImage"));
            return;
        }
        LogUtils.w("ReceiveActivity", "no cache file found , create new one");
        makeMockView();
        this.copyView.post(new Runnable() {
            @Override
            public final void run() {
                lambda$shareOrSaveContentImage$0(ops);
            }
        });
    }

    public void lambda$shareOrSaveContentImage$0(Ops ops) {
        if (ops == Ops.SHARE) {
            ShareHelper shareHelper = ShareHelper.getInstance();
            View view = this.copyView;
            shareHelper.cacheBitmapAndSave(this, view, view).subscribe(new IObserver(getCallback(ops), "shareImage"));
            return;
        }
        ShareHelper shareHelper2 = ShareHelper.getInstance();
        View view2 = this.copyView;
        shareHelper2.cacheBitmapSave(this, view2, view2).subscribe(new IObserver(getCallback(ops), "saveImage"));
    }

    private ICallback<String> getCallback(final Ops ops) {
        return new ICallback<String>() {
            @Override
            public void onResponse(String str, String str2) {
                bitmapCachePath = str2;
                if (Ops.SAVE.equals(ops)) {
                    ToastAsBottom(R.string.save_success);
                } else if (Ops.SHARE.equals(ops)) {
                    ShareHelper shareHelper = ShareHelper.getInstance();
                    ReceiveActivity receiveActivity = ReceiveActivity.this;
                    shareHelper.shareImageActual(receiveActivity, receiveActivity.bitmapCachePath);
                }
            }

            @Override
            public void onFailure(String str, String str2) {
                ToastAsBottom(R.string.save_fail);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                if (mPresenter != 0) {
                    ((EmptyPresenter) mPresenter).addDisposable(disposable);
                }
            }
        };
    }

    private View makeMockView() {
        ViewGroup viewGroup = (ViewGroup) findViewById(16908290);
        if (this.copyView == null) {
            View inflate = LayoutInflater.from(getIContext()).inflate(R.layout.ac_receive_copy_share, (ViewGroup) null);
            this.copyView = inflate;
            inflate.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
            viewGroup.addView(this.copyView, 0);
        }
        ((ImageView) this.copyView.findViewById(R.id.qr2)).setImageDrawable(this.qr.getDrawable());
        ((TextView) this.copyView.findViewById(R.id.tv_address2)).setText(this.tvAddress.getText());
        ((TextView) this.copyView.findViewById(R.id.tv_wallet_name2)).setText(this.tvWalletName.getText());
        ((ImageView) this.copyView.findViewById(R.id.iv_receive_logo_qr)).setImageBitmap(WalletUtils.strToQR(TronSetting.TRON_URL, UIUtils.dip2px(50.0f), UIUtils.dip2px(50.0f), null));
        return this.copyView;
    }

    @Override
    protected void processData() {
        Intent intent = getIntent();
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        this.addRess = selectedWallet.getAddress();
        this.tvWalletName.setText(selectedWallet.getWalletName());
        this.trc20Output = (TokenBean) intent.getParcelableExtra("trc20Output");
        init();
        if (selectedWallet.isWatchOnly() && !selectedWallet.isSamsungWallet() && !selectedWallet.isLedgerHDWallet()) {
            if (selectedWallet.isWatchCold()) {
                this.tvWatchOnly.setText(R.string.cold_wallet);
            }
            this.tvWatchOnly.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.tvScanPay.getLayoutParams();
            layoutParams.topMargin = UIUtils.dip2px(12.0f);
            this.tvScanPay.setLayoutParams(layoutParams);
        } else {
            this.tvWatchOnly.setVisibility(View.GONE);
        }
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            this.tvWatchOnly.setBackgroundResource(R.mipmap.ic_receive_watchonly_short);
        } else {
            this.tvWatchOnly.setBackgroundResource(R.mipmap.ic_receive_watchonly);
        }
        AnalyticsHelper.logEvent(AnalyticsHelper.OtherPage.ENTER_RECEIVE_PAGE);
    }

    private void init() {
        if (StringTronUtil.isEmpty(this.addRess)) {
            return;
        }
        this.tvAddress.setText(this.addRess);
        this.qr.setImageBitmap(WalletUtils.strToQR(this.addRess, UIUtils.dip2px(180.0f), UIUtils.dip2px(180.0f), ((BitmapDrawable) getResources().getDrawable(R.mipmap.ic_receive_center)).getBitmap()));
        this.copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$init$1(view);
            }
        });
        TouchDelegateUtils.expandViewTouchDelegate(this.copy, UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f));
        this.liSave.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                ops.set(Ops.SAVE);
                if (Build.VERSION.SDK_INT < 33) {
                    mPermissionHelper.requestPermissions();
                } else {
                    requestPermissionsSuccess();
                }
                AnalyticsHelper.logEvent(AnalyticsHelper.ReceivePage.CLICK_SAVE_QR);
            }
        });
        this.liShare.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                ops.set(Ops.SHARE);
                if (Build.VERSION.SDK_INT < 33) {
                    mPermissionHelper.requestPermissions();
                } else {
                    requestPermissionsSuccess();
                }
                AnalyticsHelper.logEvent(AnalyticsHelper.ReceivePage.CLICK_SHARE_QR);
            }
        });
    }

    public void lambda$init$1(View view) {
        if (this.isClick) {
            return;
        }
        UIUtils.copy(this.addRess);
        IToast.getIToast().show(getString(R.string.already_copy));
        AnalyticsHelper.logEvent(AnalyticsHelper.ReceivePage.CLICK_ADDRESS_COPY);
    }

    @Override
    public void onLeftButtonClick() {
        finish();
    }

    @Override
    public String[] getPermissions() {
        return new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    }

    @Override
    public void requestPermissionsSuccess() {
        shareOrSaveContentImage(this.ops.get());
    }

    @Override
    public void requestPermissionsFail() {
        ToastError(R.string.error_permission2);
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        PermissionHelper permissionHelper = this.mPermissionHelper;
        if (permissionHelper == null || !permissionHelper.requestPermissionsResult(i, strArr, iArr)) {
            super.onRequestPermissionsResult(i, strArr, iArr);
        }
    }
}
