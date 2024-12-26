package com.tron.wallet.business.walletmanager.pairwallet;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.util.Pair;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanIntentResult;
import com.journeyapps.barcodescanner.ScanOptions;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.WatchWalletVerifier;
import com.tron.wallet.business.maintab.MainTabActivity;
import com.tron.wallet.business.vote.util.BaseTextWatcher;
import com.tron.wallet.business.walletmanager.importwallet.ImportWalletModel;
import com.tron.wallet.common.components.CommonTitleDescriptionEditView;
import com.tron.wallet.common.components.SimpleDraweeViewGif;
import com.tron.wallet.common.components.behavior.AppbarViewBehavior;
import com.tron.wallet.common.components.dialog.CustomDialog;
import com.tron.wallet.common.components.qr.ScannerActivity;
import com.tron.wallet.common.config.CommonBundleKeys;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.interfaces.PermissionInterface;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.KeyboardUtil;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.PermissionHelper;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UserIconRandom;
import com.tron.wallet.common.utils.WalletNameGeneratorUtils;
import com.tron.wallet.databinding.ActivityPairColdWalletBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.functions.Consumer;
import j$.util.Objects;
import org.tron.net.CipherException;
import org.tron.walletserver.DuplicateNameException;
import org.tron.walletserver.InvalidNameException;
import org.tron.walletserver.Wallet;
public class PairColdWalletActivity extends BaseActivity<EmptyPresenter, EmptyModel> implements PermissionInterface {
    public static final int FROM_EMPTY_PAGE = 0;
    View addressErrorView;
    private ActivityPairColdWalletBinding binding;
    Button btnNext;
    CommonTitleDescriptionEditView etInputAddress;
    CommonTitleDescriptionEditView etInputName;
    private int from;
    View gifContainerView;
    SimpleDraweeViewGif gifImage;
    private final boolean[] inputValid = {false, true};
    View nameError;
    private PermissionHelper permissionHelper;
    private ActivityResultLauncher<ScanOptions> scannerLauncher;
    Toolbar toolbar;
    TextView tvAddressError;
    TextView tvNameError;
    private String walletName;

    @Override
    public int getPermissionsRequestCode() {
        return 50000;
    }

    @Override
    public void requestPermissionsFail() {
    }

    public static void start(Context context, int i) {
        Intent intent = new Intent(context, PairColdWalletActivity.class);
        intent.putExtra(CommonBundleKeys.KEY_FROM, i);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        setBackground(getResources().getColor(R.color.white), 0);
        ActivityPairColdWalletBinding inflate = ActivityPairColdWalletBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 0);
        mappingId();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.etInputAddress = this.binding.inputAddress;
        this.etInputName = this.binding.inputName;
        this.addressErrorView = this.binding.llAddressError;
        this.nameError = this.binding.llNameError;
        this.tvNameError = this.binding.tvNameError;
        this.btnNext = this.binding.btnNext;
        this.toolbar = this.binding.toolbar;
        this.gifImage = this.binding.gifImage;
        this.tvAddressError = this.binding.tvAddressError;
        this.gifContainerView = this.binding.gifContainer;
    }

    @Override
    protected void processData() {
        AnalyticsHelper.logEvent(AnalyticsHelper.PairWatchColdWallet.ADD_COLD_WALLET_PAGE_SHOW);
        this.gifImage.setGif(R.mipmap.ic_gif_watch_cold, 1);
        ViewGroup.LayoutParams layoutParams = this.gifContainerView.getLayoutParams();
        if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
            CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior();
            if (behavior instanceof AppbarViewBehavior) {
                LogUtils.w("PairTest", "find behaviour");
                ((AppbarViewBehavior) behavior).setTop(33.0f);
            }
        }
        this.from = getIntent().getIntExtra(CommonBundleKeys.KEY_FROM, 0);
        this.toolbar.setNavigationOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                finish();
            }
        });
        bindRxEvent();
        this.scannerLauncher = registerForActivityResult(new ScanContract(), new ActivityResultCallback() {
            @Override
            public final void onActivityResult(Object obj) {
                handleScannerResult((ScanIntentResult) obj);
            }
        });
        this.etInputName.setText(WalletNameGeneratorUtils.generateWalletName(9, false));
        if (this.etInputName.getText() != null) {
            CommonTitleDescriptionEditView commonTitleDescriptionEditView = this.etInputName;
            commonTitleDescriptionEditView.setSelection(commonTitleDescriptionEditView.getText().length());
        }
        setEditText();
        KeyboardUtil.assistActivity(this, new KeyboardUtil.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int i) {
            }

            @Override
            public void keyBoardHide(int i) {
                etInputAddress.clearFocus();
                etInputName.clearFocus();
            }
        });
        this.btnNext.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.PairWatchColdWallet.ADD_COLD_WALLET_PAGE_CLICK_NEXT);
                signAddress();
            }
        });
    }

    private void bindRxEvent() {
        ((EmptyPresenter) this.mPresenter).mRxManager.on(Event.WATCH_WALLET_VERIFY_RESULT, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$bindRxEvent$0(obj);
            }
        });
    }

    public void lambda$bindRxEvent$0(Object obj) throws Exception {
        if (!(obj instanceof WatchWalletVerifier.WalletVerifyResult) || TextUtils.isEmpty(this.etInputName.getText()) || TextUtils.isEmpty(this.etInputAddress.getText())) {
            return;
        }
        WatchWalletVerifier.WalletVerifyResult walletVerifyResult = (WatchWalletVerifier.WalletVerifyResult) obj;
        if (walletVerifyResult.isResult()) {
            if (walletVerifyResult.getAddress() == null || !TextUtils.equals(this.etInputAddress.getText(), walletVerifyResult.getAddress())) {
                toast(getString(R.string.no_address));
                return;
            }
            Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
            if (selectedPublicWallet != null && TextUtils.equals(selectedPublicWallet.getAddress(), this.etInputAddress.getText())) {
                if (selectedPublicWallet.isWatchOnly()) {
                    WalletUtils.updateWatchWalletType(selectedPublicWallet.getWalletName(), 9);
                    toMain();
                }
            } else if (saveWallet(this.etInputName.getText().toString(), this.etInputAddress.getText().toString())) {
                toMain();
            } else {
                importFailureDialog(ImportWalletModel.ERR);
            }
        }
    }

    private void toMain() {
        Intent intent = new Intent(this, MainTabActivity.class);
        intent.setFlags(67108864);
        go(intent);
        exit();
    }

    private void importFailureDialog(int i) {
        try {
            CustomDialog.Builder builder = new CustomDialog.Builder(this);
            final CustomDialog build = builder.style(R.style.loading_dialog).cancelTouchout(true).heightDimenRes(-2).widthDimenRes(-1).setGravity(17).view(R.layout.fail_dialog).build();
            View view = builder.getView();
            TextView textView = (TextView) view.findViewById(R.id.tv_content);
            TextView textView2 = (TextView) view.findViewById(R.id.tv_ok2);
            ((LinearLayout) view.findViewById(R.id.ll_double)).setVisibility(View.GONE);
            textView2.setVisibility(View.VISIBLE);
            textView.setText(i == ImportWalletModel.ADDR_EXISTS ? R.string.shield_address_already_exists : R.string.import_failure);
            textView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    build.dismiss();
                }
            });
            build.show();
            build.setCanceledOnTouchOutside(false);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    private boolean saveWallet(String str, String str2) {
        Wallet wallet = new Wallet();
        wallet.setWalletName(str);
        wallet.setAddress(str2);
        wallet.setCreateTime(System.currentTimeMillis());
        wallet.setWatchOnly(true);
        wallet.setCreateType(9);
        wallet.setIconRes(UserIconRandom.THIS.random());
        try {
            WalletUtils.saveWatchOnly(wallet);
            WalletUtils.setSelectedWallet(str);
            WalletNameGeneratorUtils.finish(9, false);
            return true;
        } catch (CipherException e) {
            LogUtils.e(e);
            return false;
        } catch (DuplicateNameException e2) {
            LogUtils.e(e2);
            return false;
        } catch (InvalidNameException e3) {
            LogUtils.e(e3);
            return false;
        }
    }

    private void setEditText() {
        setInputAddressState();
        this.etInputAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public final void onFocusChange(View view, boolean z) {
                lambda$setEditText$1(view, z);
            }
        });
        this.etInputAddress.addTextChangedListener(new BaseTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                setInputAddressState();
                if (TextUtils.isEmpty(etInputAddress.getText())) {
                    addressErrorView.setVisibility(View.GONE);
                } else if (etInputAddress.getText().length() == 34) {
                    PairColdWalletActivity pairColdWalletActivity = PairColdWalletActivity.this;
                    pairColdWalletActivity.checkInputAddress(pairColdWalletActivity.etInputAddress.getText().toString().trim());
                }
            }
        });
        this.etInputName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public final void onFocusChange(View view, boolean z) {
                lambda$setEditText$2(view, z);
            }
        });
        this.etInputName.addTextChangedListener(new BaseTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                setInputNameState();
            }
        });
    }

    public void lambda$setEditText$1(View view, boolean z) {
        this.etInputAddress.setCursorVisible(z);
        if (z) {
            this.addressErrorView.setVisibility(View.GONE);
            return;
        }
        Editable text = this.etInputAddress.getText();
        String obj = text == null ? "" : text.toString();
        if (obj.length() != 34) {
            checkInputAddress(obj);
        }
    }

    public void lambda$setEditText$2(View view, boolean z) {
        this.etInputName.setCursorVisible(z);
        setInputNameState();
        if (z) {
            this.nameError.setVisibility(View.GONE);
            return;
        }
        boolean checkInputName = checkInputName();
        boolean[] zArr = this.inputValid;
        zArr[1] = checkInputName;
        this.btnNext.setEnabled(checkInputName & zArr[0]);
    }

    public void setInputAddressState() {
        setStatedEditText(this.etInputAddress, new Pair<>(Integer.valueOf((int) R.mipmap.qr_scan_black_2), new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                scanAddressQrCode();
            }
        }), new Pair<>(Integer.valueOf((int) R.mipmap.delete_gray), new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                etInputAddress.setText("");
            }
        }));
    }

    public void checkInputAddress(String str) {
        boolean z;
        if (TextUtils.isEmpty(str)) {
            this.addressErrorView.setVisibility(View.GONE);
        } else if (StringTronUtil.isAddressValid(str)) {
            if (WalletUtils.getWalletForAddress(str) != null) {
                this.tvAddressError.setText(R.string.error_existwallet);
                this.addressErrorView.setVisibility(View.VISIBLE);
            } else {
                this.addressErrorView.setVisibility(View.GONE);
                z = true;
                boolean[] zArr = this.inputValid;
                zArr[0] = z;
                this.btnNext.setEnabled(z & zArr[1]);
            }
        } else if (TextUtils.isEmpty(this.etInputAddress.getText())) {
            toast(getString(R.string.invalid_wallet_address));
            this.addressErrorView.setVisibility(View.GONE);
        } else {
            this.tvAddressError.setText(R.string.invalid_wallet_address);
            this.addressErrorView.setVisibility(View.VISIBLE);
        }
        z = false;
        boolean[] zArr2 = this.inputValid;
        zArr2[0] = z;
        this.btnNext.setEnabled(z & zArr2[1]);
    }

    public void signAddress() {
        if (this.etInputAddress.getText() != null) {
            WatchWalletVerifier.getInstance().confirmVerifyDirectly(this, this.etInputAddress.getText().toString());
        }
    }

    public void scanAddressQrCode() {
        if (this.permissionHelper == null) {
            this.permissionHelper = new PermissionHelper(this, this);
        }
        this.permissionHelper.requestPermissions();
    }

    public void handleScannerResult(ScanIntentResult scanIntentResult) {
        String contents = scanIntentResult.getContents();
        if (contents == null || this.etInputAddress == null) {
            return;
        }
        String trim = contents.trim();
        checkInputAddress(trim);
        if (this.inputValid[0] || StringTronUtil.isAddressValid(trim)) {
            this.etInputAddress.setText(trim);
            this.etInputAddress.setSelection(trim.length());
        }
    }

    public void setInputNameState() {
        setStatedEditText(this.etInputName, new Pair<>(0, null), new Pair<>(Integer.valueOf((int) R.mipmap.delete_gray), new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                etInputName.setText("");
            }
        }));
        try {
            checkNameLength();
        } catch (Exception e) {
            SentryUtil.captureException(e);
        }
    }

    private void setStatedEditText(CommonTitleDescriptionEditView commonTitleDescriptionEditView, Pair<Integer, View.OnClickListener> pair, Pair<Integer, View.OnClickListener> pair2) {
        if (commonTitleDescriptionEditView == null || commonTitleDescriptionEditView.getText() == null) {
            return;
        }
        if (TextUtils.isEmpty(commonTitleDescriptionEditView.getText().toString())) {
            if (pair.first.intValue() > 0) {
                commonTitleDescriptionEditView.setRightImageResId(pair.first.intValue());
                commonTitleDescriptionEditView.setShowRightImage(true);
                if (pair.second != null) {
                    final View.OnClickListener onClickListener = pair.second;
                    Objects.requireNonNull(onClickListener);
                    commonTitleDescriptionEditView.setRightDrawableClick(new CommonTitleDescriptionEditView.RightDrawableClick() {
                        @Override
                        public final void onRightDrawableClick(View view) {
                            onClickListener.onClick(view);
                        }
                    });
                    return;
                }
                return;
            }
            commonTitleDescriptionEditView.setShowRightImage(false);
        } else if (pair2.first.intValue() > 0) {
            commonTitleDescriptionEditView.setRightImageResId(pair2.first.intValue());
            if (pair2.second != null) {
                final View.OnClickListener onClickListener2 = pair2.second;
                Objects.requireNonNull(onClickListener2);
                commonTitleDescriptionEditView.setRightDrawableClick(new CommonTitleDescriptionEditView.RightDrawableClick() {
                    @Override
                    public final void onRightDrawableClick(View view) {
                        onClickListener2.onClick(view);
                    }
                });
            }
            commonTitleDescriptionEditView.setShowRightImage(true);
        } else {
            commonTitleDescriptionEditView.setShowRightImage(false);
        }
    }

    public void showNameError(String str) {
        this.nameError.setVisibility(View.VISIBLE);
        this.tvNameError.setText(str);
    }

    private boolean checkInputName() {
        String trim = this.etInputName.getText().toString().trim();
        this.walletName = trim;
        if (StringTronUtil.isEmpty(trim)) {
            if (TextUtils.isEmpty(this.etInputAddress.getText())) {
                return true;
            }
            showNameError(getResources().getString(R.string.error_name3));
            return false;
        } else if (!StringTronUtil.validataLegalString2(this.walletName)) {
            showNameError(getResources().getString(R.string.error_name2));
            return false;
        } else if (WalletUtils.existWallet(this.walletName)) {
            showNameError(getResources().getString(R.string.exist_wallet_name));
            return false;
        } else {
            try {
                this.walletName = StringTronUtil.trimEnd(this.walletName.toCharArray());
                return true;
            } catch (InvalidNameException e) {
                LogUtils.e(e);
                showNameError(getResources().getString(R.string.error_name2));
                return false;
            }
        }
    }

    private void checkNameLength() {
        try {
            Editable text = this.etInputName.getText();
            String trim = text.toString().trim();
            String str = "";
            int selectionEnd = Selection.getSelectionEnd(text);
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i >= trim.length()) {
                    break;
                }
                char charAt = trim.charAt(i);
                i2 = (charAt < ' ' || charAt > 'z') ? i2 + 2 : i2 + 1;
                if (i2 > 28) {
                    str = trim.substring(0, i);
                    if (selectionEnd > str.length()) {
                        selectionEnd = str.length();
                    }
                } else {
                    i++;
                }
            }
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.etInputName.setText(str);
            Selection.setSelection(this.etInputName.getText(), selectionEnd);
        } catch (Exception e) {
            SentryUtil.captureException(e);
            LogUtils.e(e);
        }
    }

    @Override
    public String[] getPermissions() {
        return new String[]{"android.permission.CAMERA"};
    }

    @Override
    public void requestPermissionsSuccess() {
        ScannerActivity.start(this.scannerLauncher);
    }
}
