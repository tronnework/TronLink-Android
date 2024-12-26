package com.tron.wallet.business.confirm.sign;

import android.content.Intent;
import android.graphics.Rect;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.sign.SignTransactionNewContract;
import com.tron.wallet.business.tabmy.myhome.SettingActivity;
import com.tron.wallet.business.walletmanager.pairwallet.PairColdWalletDialog;
import com.tron.wallet.common.adapter.SignTransactionBottomAdapter;
import com.tron.wallet.common.adapter.SignTransactionTopAdapter;
import com.tron.wallet.common.bean.QrBean;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.qr.ScannerActivity;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.interfaces.PermissionInterface;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.PermissionHelper;
import com.tron.wallet.common.utils.TextAndPictureUtil;
import com.tron.wallet.databinding.AcNewSignTransactionBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import org.tron.walletserver.Wallet;
public class SignTransactionNewActivity extends BaseActivity<SignTransactionNewPresenter, SignTransactionNewModel> implements PermissionInterface, SignTransactionNewContract.View {
    private AcNewSignTransactionBinding binding;
    private ActivityResultLauncher<Intent> launchQrSetting;
    View llTooBig;
    private PermissionHelper mPermissionHelper;
    RecyclerView rcBottom;
    RecyclerView rcTop;
    RelativeLayout rlTop;
    Button toScan;
    TextView tv350_1;
    TextView tv350_2;
    TextView tvBigTip;
    TextView tvSignV2Hint;
    TextView tvTitle;
    ViewPager2 viewpager;

    @Override
    public int getPermissionsRequestCode() {
        return 2001;
    }

    @Override
    public ViewPager2 getVP() {
        return this.viewpager;
    }

    public static void start(BaseActivity baseActivity, QrBean qrBean, TokenBean tokenBean, String str) {
        start2(baseActivity, qrBean, tokenBean, str, null);
    }

    public static void start2(BaseActivity baseActivity, QrBean qrBean, TokenBean tokenBean, String str, BaseParam.PageFrom pageFrom) {
        start2(baseActivity, qrBean, tokenBean, str, pageFrom, true);
    }

    public static void start2(final BaseActivity baseActivity, QrBean qrBean, TokenBean tokenBean, String str, BaseParam.PageFrom pageFrom, boolean z) {
        Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
        if ((z & TextUtils.equals(str, TronConfig.OB_W)) && selectedPublicWallet != null && selectedPublicWallet.isWatchNotPaired()) {
            PairColdWalletDialog.showUp(baseActivity, new PairColdWalletDialog.PairResultCallback() {
                @Override
                public void onPairColdResult(boolean z2) {
                    BaseActivity.this.finish();
                }
            });
            return;
        }
        Intent intent = new Intent(baseActivity, SignTransactionNewActivity.class);
        intent.putExtra(TronConfig.QR_CODE_DATA, qrBean);
        intent.putExtra(TronConfig.TOKEN_DATA, tokenBean);
        intent.putExtra(TronConfig.FROM_W, str);
        if (pageFrom != null) {
            intent.putExtra(TronConfig.PageFrom, pageFrom);
        }
        baseActivity.startActivityForResult(intent, TronConfig.TRANSACTION_SIGN_REQUEST_CODE);
    }

    @Override
    protected void setLayout() {
        AcNewSignTransactionBinding inflate = AcNewSignTransactionBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 6);
        setHeaderBar(getString(R.string.scan_signed_title));
        mappingId();
        setClick();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.toScan = this.binding.toscan;
        this.tvTitle = this.binding.tvTitle;
        this.rcTop = this.binding.rcTop;
        this.viewpager = this.binding.viewpager;
        this.rcBottom = this.binding.rcBottom;
        this.tv350_1 = this.binding.tv3501;
        this.tv350_2 = this.binding.tv3502;
        this.rlTop = this.binding.rlTop;
        this.llTooBig = this.binding.llTooBig;
        this.tvBigTip = this.binding.tvBigTip;
        this.tvSignV2Hint = this.binding.tvSignV2Hint;
    }

    @Override
    protected void processData() {
        uiInit();
        this.launchQrSetting = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult activityResult) {
                if (SpAPI.THIS.getQRMultiImageIsOpen()) {
                    ((SignTransactionNewPresenter) mPresenter).init();
                }
            }
        });
        ((SignTransactionNewPresenter) this.mPresenter).init();
        ((SignTransactionNewPresenter) this.mPresenter).addListener(this.rcBottom);
    }

    public void uiInit() {
        this.rcTop.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.rcTop.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
                rect.set(10, 0, 10, 0);
            }
        });
        this.rcBottom.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.rcBottom.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
                rect.set(10, 0, 10, 0);
            }
        });
        this.viewpager.setUserInputEnabled(false);
        String str = getResources().getString(R.string.transaction_data_too_big) + getResources().getString(R.string.turn_on_now);
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.blue_13)), getResources().getString(R.string.transaction_data_too_big).length(), str.length(), 33);
        this.tvBigTip.setText(spannableString);
        this.llTooBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$uiInit$0(view);
            }
        });
    }

    public void lambda$uiInit$0(View view) {
        this.launchQrSetting.launch(new Intent(this, SettingActivity.class));
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        cancle();
    }

    @Override
    public void cancle() {
        Intent intent = new Intent();
        intent.putExtra("cancle", true);
        setResult(TronConfig.TRANSACTION_SIGN_REQUEST_CODE, intent);
        finish();
    }

    @Override
    public void setTopAdapter(SignTransactionTopAdapter signTransactionTopAdapter) {
        this.rcTop.setAdapter(signTransactionTopAdapter);
    }

    @Override
    public void setBottomAdapter(SignTransactionBottomAdapter signTransactionBottomAdapter) {
        this.rcBottom.setAdapter(signTransactionBottomAdapter);
    }

    @Override
    public Intent getIIntent() {
        return getIntent();
    }

    @Override
    public void setResult(TokenBean tokenBean, String str) {
        Intent intent = new Intent();
        intent.putExtra(TronConfig.QR_CODE_DATA, str);
        intent.putExtra(TronConfig.TOKEN_DATA, tokenBean);
        setResult(TronConfig.TRANSACTION_SIGN_REQUEST_CODE, intent);
        finish();
    }

    @Override
    public void updateUI(int i, String str, boolean z) {
        SpannableString text;
        if (i > 1) {
            this.tv350_1.setVisibility(View.VISIBLE);
            if (!((SignTransactionNewPresenter) this.mPresenter).isSignMessageV2()) {
                this.tv350_2.setVisibility(View.VISIBLE);
            }
            this.llTooBig.setVisibility(View.GONE);
            this.rcTop.setVisibility(View.VISIBLE);
            this.rcBottom.setVisibility(View.VISIBLE);
        } else {
            updateButtonText(R.string.qr_detail_15);
            if (z && !SpAPI.THIS.isCold()) {
                this.llTooBig.setVisibility(View.VISIBLE);
            }
            this.rcTop.setVisibility(View.GONE);
            this.rcBottom.setVisibility(View.GONE);
        }
        if (TronConfig.COLD_W.equals(str)) {
            setHeaderBar(getString(R.string.qr_detail_8));
            this.tvTitle.setText(R.string.qr_watch_sign_new);
            if (i > 1) {
                this.toScan.setText(R.string.scan_wallet_sign_next);
            }
        } else {
            this.tvTitle.setText(R.string.qr_detail_13);
            setHeaderBar(getString(R.string.scan_signed_title));
        }
        if (TronConfig.OB_W.equals(str)) {
            if (((SignTransactionNewPresenter) this.mPresenter).checkStakeV2()) {
                text = TextAndPictureUtil.getText(this.mContext, getString(R.string.stake_v2_cold_hint), R.mipmap.error_yellow);
            } else {
                text = ((SignTransactionNewPresenter) this.mPresenter).isSignMessageV2() ? TextAndPictureUtil.getText(this.mContext, getString(R.string.sign_message_v2_hint), R.mipmap.error_yellow) : null;
            }
            if (text != null) {
                this.tvSignV2Hint.setText(text);
                this.tvSignV2Hint.setVisibility(View.VISIBLE);
                return;
            }
            this.tvSignV2Hint.setVisibility(View.GONE);
            return;
        }
        this.tvSignV2Hint.setVisibility(View.GONE);
    }

    @Override
    public void requestPermissionToScan() {
        PermissionHelper permissionHelper = new PermissionHelper(this, this);
        this.mPermissionHelper = permissionHelper;
        permissionHelper.requestPermissions();
    }

    @Override
    public void updateButtonText(int i) {
        this.toScan.setText(i);
    }

    @Override
    public void scanedFinish() {
        setResult(-1, new Intent());
        finish();
    }

    public void setClick() {
        this.binding.toscan.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                ((SignTransactionNewPresenter) mPresenter).buttonNext();
            }
        });
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        try {
            if (intent != null) {
                ((SignTransactionNewPresenter) this.mPresenter).onActivityResult(i, i2, intent);
            } else {
                super.onActivityResult(i, i2, intent);
            }
        } catch (Exception unused) {
            IToast.getIToast().showAsBottomn(R.string.qr_detail_7);
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
        ScannerActivity.start(this);
    }

    @Override
    public void requestPermissionsFail() {
        ToastError(R.string.error_permission);
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (this.mPermissionHelper.requestPermissionsResult(i, strArr, iArr)) {
            return;
        }
        super.onRequestPermissionsResult(i, strArr, iArr);
    }
}
