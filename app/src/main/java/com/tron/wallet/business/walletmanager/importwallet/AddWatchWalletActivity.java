package com.tron.wallet.business.walletmanager.importwallet;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.widget.NestedScrollView;
import com.facebook.common.util.UriUtil;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.maintab.MainTabActivity;
import com.tron.wallet.common.components.CommonTitleDescriptionEditView;
import com.tron.wallet.common.components.SimpleDraweeViewGif;
import com.tron.wallet.common.components.dialog.CustomDialog;
import com.tron.wallet.common.components.qr.ScannerActivity;
import com.tron.wallet.common.interfaces.PermissionInterface;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.PermissionHelper;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.SoftHideKeyBoardUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.common.utils.UserIconRandom;
import com.tron.wallet.common.utils.WalletNameGeneratorUtils;
import com.tron.wallet.databinding.AcAddWatchWalletBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.util.HashSet;
import java.util.Set;
import org.tron.net.CipherException;
import org.tron.walletserver.DuplicateNameException;
import org.tron.walletserver.InvalidNameException;
import org.tron.walletserver.Wallet;
public class AddWatchWalletActivity extends BaseActivity<EmptyPresenter, EmptyModel> implements PermissionInterface {
    RelativeLayout addBottomLayout;
    private Set<String> allWalletsAddress = new HashSet();
    AppBarLayout appBar;
    private AcAddWatchWalletBinding binding;
    Button btnAddWatchWallet;
    View emptyPlaceholder;
    CommonTitleDescriptionEditView etAddWatchAddress;
    CommonTitleDescriptionEditView etAddWatchName;
    SimpleDraweeViewGif gifImage;
    LinearLayout liAddWatchWallet;
    LinearLayout llAddressError;
    LinearLayout llNameError;
    private PermissionHelper mPermissionHelper;
    NestedScrollView scrollView;
    CollapsingToolbarLayout toolBarLayout;
    Toolbar toolbar;
    TextView tvAddressError;
    TextView tvNameError;
    private String walletName;

    @Override
    public int getPermissionsRequestCode() {
        return 2000;
    }

    @Override
    protected void setLayout() {
        setBackground(getResources().getColor(R.color.white), 0);
        AcAddWatchWalletBinding inflate = AcAddWatchWalletBinding.inflate(getLayoutInflater());
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
        this.toolbar = this.binding.toolbar;
        this.toolBarLayout = this.binding.toolbarLayout;
        this.gifImage = this.binding.gifImage;
        this.appBar = this.binding.appBar;
        this.liAddWatchWallet = this.binding.contentAddWatchWallet.liAddWatchWallet;
        this.etAddWatchAddress = this.binding.contentAddWatchWallet.addWatchAddress;
        this.llAddressError = this.binding.contentAddWatchWallet.llAddressError;
        this.tvAddressError = this.binding.contentAddWatchWallet.tvAddressError;
        this.llNameError = this.binding.contentAddWatchWallet.llNameError;
        this.tvNameError = this.binding.contentAddWatchWallet.tvNameError;
        this.etAddWatchName = this.binding.contentAddWatchWallet.addWatchName;
        this.btnAddWatchWallet = this.binding.addWatchWallet;
        this.emptyPlaceholder = this.binding.contentAddWatchWallet.emptyPlaceholder;
        this.addBottomLayout = this.binding.addBottomLayout;
        this.scrollView = this.binding.contentAddWatchWallet.scrollAddWatchWallet;
    }

    @Override
    protected void processData() {
        setSupportActionBar(this.toolbar);
        this.toolBarLayout.setTitle(getString(R.string.add_watch_wallet_only));
        this.scrollView.setMinimumHeight(UIUtils.dip2px(300.0f));
        SoftHideKeyBoardUtil.assistActivity(this, new SoftHideKeyBoardUtil.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int i) {
                if (i > 0) {
                    addBottomLayout.setVisibility(View.GONE);
                }
                if (etAddWatchName.isFocused()) {
                    scrollView.scrollTo(0, etAddWatchName.getTop());
                } else if (etAddWatchAddress.isFocused()) {
                    scrollView.scrollTo(0, etAddWatchAddress.getTop());
                }
            }

            @Override
            public void keyBoardHide(int i) {
                if (i > 0) {
                    addBottomLayout.setVisibility(View.VISIBLE);
                }
                checkhideKeyBoardEnable();
            }
        });
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        this.gifImage.setGif(R.drawable.watch_2, 1);
        UIUtils.getStatusBarHeight();
        SpAPI.THIS.getWatchWalletIndex();
        this.etAddWatchName.setText(WalletNameGeneratorUtils.generateWalletName(4, false));
        changeAddressRightButton(0);
        this.etAddWatchName.setRightDrawableClick(new CommonTitleDescriptionEditView.RightDrawableClick() {
            @Override
            public void onRightDrawableClick(View view) {
                etAddWatchName.setText("");
            }
        });
        this.toolbar.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                finish();
            }
        });
        this.btnAddWatchWallet.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                addWatchWallet();
            }
        });
        this.etAddWatchAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                llAddressError.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String trim = editable.toString().trim();
                if (!TextUtils.isEmpty(trim)) {
                    if (StringTronUtil.isAddressValid(trim)) {
                        llAddressError.setVisibility(View.GONE);
                    } else if (trim.length() >= 34) {
                        llAddressError.setVisibility(View.VISIBLE);
                    }
                    checkBtnEnable();
                }
                changeAddressRightButton(!TextUtils.isEmpty(editable.toString().trim()) ? 1 : 0);
            }
        });
        this.etAddWatchAddress.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 6) {
                    checkhideKeyBoardEnable();
                    return false;
                }
                return false;
            }
        });
        this.etAddWatchAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    expandedBar();
                } else if (StringTronUtil.isAddressValid(etAddWatchAddress.getText().toString())) {
                    llAddressError.setVisibility(View.GONE);
                } else {
                    llAddressError.setVisibility(View.VISIBLE);
                }
                checkBtnEnable();
            }
        });
        this.etAddWatchName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    expandedBar();
                    etAddWatchName.setRightImageResId(R.mipmap.delete_gray);
                    return;
                }
                etAddWatchName.setRightImageResId(0);
                checkInputName();
            }
        });
        this.etAddWatchName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                llNameError.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                walletName = editable.toString().trim();
                llNameError.setVisibility(View.GONE);
                if (TextUtils.isEmpty(walletName)) {
                    AddWatchWalletActivity addWatchWalletActivity = AddWatchWalletActivity.this;
                    addWatchWalletActivity.showNameError(addWatchWalletActivity.getResources().getString(R.string.error_name3));
                    etAddWatchName.setRightImageResId(0);
                } else {
                    checkInputName();
                    etAddWatchName.setRightImageResId(R.mipmap.delete_gray);
                }
                checkBtnEnable();
            }
        });
        runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$processData$0();
            }
        });
    }

    public void lambda$processData$0() {
        this.allWalletsAddress = WalletUtils.getAllWalletsAddress();
    }

    public void changeAddressRightButton(int i) {
        if (i == 0) {
            this.etAddWatchAddress.setRightImageResId(R.mipmap.qr_scan_black_2);
            this.etAddWatchAddress.setRightDrawableClick(new CommonTitleDescriptionEditView.RightDrawableClick() {
                @Override
                public void onRightDrawableClick(View view) {
                    requestPermissions();
                }
            });
            return;
        }
        this.etAddWatchAddress.setRightImageResId(R.mipmap.delete_gray);
        this.etAddWatchAddress.setRightDrawableClick(new CommonTitleDescriptionEditView.RightDrawableClick() {
            @Override
            public void onRightDrawableClick(View view) {
                etAddWatchAddress.setText("");
            }
        });
    }

    public void expandedBar() {
        this.appBar.setExpanded(false);
    }

    public boolean checkInputName() {
        String trim = this.etAddWatchName.getText().toString().trim();
        this.walletName = trim;
        if (StringTronUtil.isEmpty(trim)) {
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
                try {
                    Editable text = this.etAddWatchName.getText();
                    String trim2 = text.toString().trim();
                    int selectionEnd = Selection.getSelectionEnd(text);
                    int i = 0;
                    for (int i2 = 0; i2 < trim2.length(); i2++) {
                        char charAt = trim2.charAt(i2);
                        i = (charAt < ' ' || charAt > 'z') ? i + 2 : i + 1;
                        if (i > 28) {
                            this.etAddWatchName.setText(trim2.substring(0, i2));
                            Editable text2 = this.etAddWatchName.getText();
                            if (selectionEnd > text2.length()) {
                                selectionEnd = text2.length();
                            }
                            Selection.setSelection(text2, selectionEnd);
                        }
                    }
                    return true;
                } catch (Exception e) {
                    SentryUtil.captureException(e);
                    LogUtils.e(e);
                    return true;
                }
            } catch (InvalidNameException e2) {
                LogUtils.e(e2);
                showNameError(getResources().getString(R.string.error_name2));
                return false;
            }
        }
    }

    public void showNameError(String str) {
        this.llNameError.setVisibility(View.VISIBLE);
        this.tvNameError.setText(str);
    }

    public void checkhideKeyBoardEnable() {
        String obj = this.etAddWatchAddress.getText().toString();
        this.etAddWatchName.getText().toString();
        if (StringTronUtil.isAddressValid(obj)) {
            if (checkInputName()) {
                this.btnAddWatchWallet.setEnabled(true);
                return;
            } else {
                this.btnAddWatchWallet.setEnabled(false);
                return;
            }
        }
        this.btnAddWatchWallet.setEnabled(false);
        if (this.etAddWatchAddress.isFocused()) {
            this.llAddressError.setVisibility(View.VISIBLE);
        }
        if (this.etAddWatchName.isFocused()) {
            checkInputName();
        }
    }

    public void checkBtnEnable() {
        String obj = this.etAddWatchAddress.getText().toString();
        this.etAddWatchName.getText().toString();
        if (StringTronUtil.isAddressValid(obj)) {
            if (checkInputName()) {
                this.btnAddWatchWallet.setEnabled(true);
                return;
            } else {
                this.btnAddWatchWallet.setEnabled(false);
                return;
            }
        }
        this.btnAddWatchWallet.setEnabled(false);
    }

    public void addWatchWallet() {
        final String obj = this.etAddWatchAddress.getText().toString();
        String obj2 = this.etAddWatchName.getText().toString();
        if (this.allWalletsAddress.isEmpty()) {
            this.allWalletsAddress = WalletUtils.getAllWalletsAddress();
        }
        if (WalletUtils.existWallet(obj2) || this.allWalletsAddress.contains(obj)) {
            Toast(StringTronUtil.getResString(R.string.error_existwallet));
        } else if (!StringTronUtil.isAddressValid(obj)) {
            this.btnAddWatchWallet.setEnabled(false);
            this.llAddressError.setVisibility(View.VISIBLE);
        } else if (checkInputName()) {
            if (!StringTronUtil.isEmpty(obj)) {
                showLoading(getString(R.string.improting));
                final Bundle bundle = new Bundle();
                runOnThreeThread(new OnBackground() {
                    @Override
                    public final void doOnBackground() {
                        lambda$addWatchWallet$2(obj, bundle);
                    }
                });
                return;
            }
            importFailureDialog();
        }
    }

    public void lambda$addWatchWallet$2(String str, final Bundle bundle) {
        final boolean saveWalletWithObserve = saveWalletWithObserve(this.walletName, str);
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$addWatchWallet$1(saveWalletWithObserve, bundle);
            }
        });
    }

    public void lambda$addWatchWallet$1(boolean z, Bundle bundle) {
        dismissLoading();
        if (z) {
            toMain();
            bundle.putString(NotificationCompat.CATEGORY_EVENT, "Import Watch Wallet");
            FirebaseAnalytics.getInstance(this).logEvent("Add_Wallet", bundle);
            return;
        }
        importFailureDialog(ImportWalletModel.ERR);
    }

    private boolean saveWalletWithObserve(String str, String str2) {
        Wallet wallet = new Wallet();
        wallet.setWalletName(str);
        wallet.setAddress(str2);
        wallet.setCreateTime(System.currentTimeMillis());
        wallet.setWatchOnly(true);
        wallet.setCreateType(4);
        wallet.setIconRes(UserIconRandom.THIS.random());
        try {
            WalletUtils.saveWatchOnly(wallet);
            WalletUtils.setSelectedWallet(str);
            WalletNameGeneratorUtils.finish(4, false);
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

    private void toMain() {
        Intent intent = new Intent(this, MainTabActivity.class);
        intent.setFlags(67108864);
        go(intent);
        exit();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private Uri getUriFromDrawableRes(int i) {
        return new Uri.Builder().scheme(UriUtil.LOCAL_RESOURCE_SCHEME).path(String.valueOf(i)).build();
    }

    protected void requestPermissions() {
        PermissionHelper permissionHelper = new PermissionHelper(this, this);
        this.mPermissionHelper = permissionHelper;
        permissionHelper.requestPermissions();
    }

    @Override
    public String[] getPermissions() {
        return new String[]{"android.permission.CAMERA"};
    }

    @Override
    public void requestPermissionsSuccess() {
        toScan();
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
    public void onActivityResult(int i, int i2, Intent intent) {
        IntentResult parseActivityResult = IntentIntegrator.parseActivityResult(i, i2, intent);
        if (parseActivityResult != null) {
            if (parseActivityResult.getContents() != null) {
                String trim = intent.getStringExtra("SCAN_RESULT").trim();
                if (StringTronUtil.isAddressValid(trim)) {
                    this.etAddWatchAddress.setText(trim.trim());
                    this.etAddWatchAddress.setSelection(trim.trim().length());
                    return;
                }
                toast(getString(R.string.scan_formated_address));
                return;
            }
            return;
        }
        super.onActivityResult(i, i2, intent);
    }

    private void toScan() {
        ScannerActivity.start(this);
    }

    private void importFailureDialog() {
        try {
            CustomDialog.Builder builder = new CustomDialog.Builder(this);
            final CustomDialog build = builder.style(R.style.loading_dialog).cancelTouchout(true).heightDimenRes(-2).widthDimenRes(-1).setGravity(17).view(R.layout.fail_dialog).build();
            View view = builder.getView();
            TextView textView = (TextView) view.findViewById(R.id.tv_ok2);
            ((LinearLayout) view.findViewById(R.id.ll_double)).setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.tv_content)).setText(R.string.import_failure);
            textView.setOnClickListener(new View.OnClickListener() {
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
}
