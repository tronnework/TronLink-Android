package com.tron.wallet.business.tabmy.myhome.addressbook;

import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.components.dialog.CustomDialog;
import com.tron.wallet.common.components.qr.ScannerActivity;
import com.tron.wallet.common.interfaces.PermissionInterface;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.PermissionHelper;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.AcAddAddressBinding;
import com.tron.wallet.db.Controller.AddressController;
import com.tron.wallet.db.bean.AddressDao;
import com.tronlinkpro.wallet.R;
import java.io.Serializable;
import java.util.List;
public class AddAddressActivity extends BaseActivity<EmptyPresenter, EmptyModel> implements PermissionInterface {
    public static final int MAX_NAME_LENGTH = 20;
    public static final String TAG_ADDRESS_LIST = "add_address_list";
    public static final String TAG_FROM_TYPE = "tag_from_type";
    public static final String TAG_SELECT_ADDRESS = "tag_select_address";
    public static final String TYPE_ADDRESS_DETAILS = "type_address_details";
    public static final String TYPE_ADD_ADDRESS = "type_add_address";
    private List<AddressDao> addressList;
    private AcAddAddressBinding binding;
    Button btNext;
    private CustomDialog dialog;
    EditText etAddress;
    EditText etAddressName;
    EditText etDescription;
    private Intent intent;
    ImageView ivAddress;
    ImageView ivName;
    private PermissionHelper mPermissionHelper;
    private AddressDao mSelectAddress;
    RelativeLayout rlDeleteAddress;
    RelativeLayout rlErrorAddress;
    RelativeLayout rlErrorName;
    RelativeLayout rlScan;
    TextView tvAddressError;
    TextView tvNameError;
    private String mTypeFrom = "";
    private String oldName = "";
    private String oldAddress = "";
    private String oldDescription = "";

    @Override
    public int getPermissionsRequestCode() {
        return 2001;
    }

    @Override
    protected void setLayout() {
        AcAddAddressBinding inflate = AcAddAddressBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        setClick();
        setView(root, 1);
        Intent intent = getIntent();
        this.intent = intent;
        if (intent != null) {
            this.mTypeFrom = intent.getStringExtra(TAG_FROM_TYPE);
        }
        if (StringTronUtil.isEmpty(this.mTypeFrom)) {
            this.mTypeFrom = TYPE_ADD_ADDRESS;
        }
        if (this.mTypeFrom.equals(TYPE_ADD_ADDRESS)) {
            setHeaderBar(getString(R.string.add_address_title));
        } else {
            setHeaderBarAndRightImage(getString(R.string.address_details), R.mipmap.icon_delete_20_232c41);
        }
    }

    public void mappingId() {
        this.etAddressName = this.binding.etAddressName;
        this.ivName = this.binding.ivName;
        this.tvNameError = this.binding.tvNameError;
        this.rlErrorName = this.binding.rlErrorName;
        this.etAddress = this.binding.etAddress;
        this.rlScan = this.binding.rlScan;
        this.ivAddress = this.binding.ivAddress;
        this.tvAddressError = this.binding.tvAddressError;
        this.rlErrorAddress = this.binding.rlErrorAddress;
        this.etDescription = this.binding.etDescription;
        this.rlDeleteAddress = this.binding.rlDelete;
        this.btNext = this.binding.btNext;
    }

    @Override
    protected void processData() {
        Intent intent = this.intent;
        if (intent != null) {
            this.mSelectAddress = (AddressDao) intent.getParcelableExtra(TAG_SELECT_ADDRESS);
            this.addressList = (List) this.intent.getSerializableExtra(TAG_ADDRESS_LIST);
        }
        if (this.mTypeFrom.equals(TYPE_ADD_ADDRESS)) {
            this.rlScan.setVisibility(View.VISIBLE);
            this.rlDeleteAddress.setVisibility(View.GONE);
        } else {
            this.rlScan.setVisibility(View.GONE);
            this.rlDeleteAddress.setVisibility(View.VISIBLE);
            AddressDao addressDao = this.mSelectAddress;
            if (addressDao != null) {
                this.oldName = StringTronUtil.isEmpty(addressDao.name) ? "" : this.mSelectAddress.name;
                this.oldAddress = StringTronUtil.isEmpty(this.mSelectAddress.address) ? "" : this.mSelectAddress.address;
                this.oldDescription = StringTronUtil.isEmpty(this.mSelectAddress.description) ? "" : this.mSelectAddress.description;
                this.etAddressName.setText(this.oldName);
                this.etAddress.setText(this.oldAddress);
                this.etDescription.setText(this.oldDescription);
            }
        }
        initListener();
    }

    private void initListener() {
        this.btNext.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                save();
            }
        });
        this.etAddressName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                showOrHideNameError(false, "");
                AddAddressActivity addAddressActivity = AddAddressActivity.this;
                addAddressActivity.checkInputContent(addAddressActivity.etAddressName, 20);
            }
        });
        this.etAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                showOrHideAddressError(false, "");
                if (StringTronUtil.isEmpty(etAddress.getText().toString().trim())) {
                    rlDeleteAddress.setVisibility(View.GONE);
                    rlScan.setVisibility(View.VISIBLE);
                    return;
                }
                rlDeleteAddress.setVisibility(View.VISIBLE);
                rlScan.setVisibility(View.GONE);
            }
        });
        this.etDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                AddAddressActivity addAddressActivity = AddAddressActivity.this;
                addAddressActivity.checkInputContent(addAddressActivity.etDescription, 60);
            }
        });
        this.etAddressName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    etAddressName.setBackgroundResource(R.drawable.roundborder_232c41_6);
                } else {
                    etAddressName.setBackgroundResource(R.drawable.roundborder_ebedf0_6);
                }
            }
        });
        this.etAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    etAddress.setBackgroundResource(R.drawable.roundborder_232c41_6);
                } else {
                    etAddress.setBackgroundResource(R.drawable.roundborder_ebedf0_6);
                }
            }
        });
        this.etDescription.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    etDescription.setBackgroundResource(R.drawable.roundborder_232c41_6);
                } else {
                    etDescription.setBackgroundResource(R.drawable.roundborder_ebedf0_6);
                }
            }
        });
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }

    @Override
    public void onRightButtonClick() {
        super.onRightTextClick();
        if (!this.mTypeFrom.equals(TYPE_ADDRESS_DETAILS) || this.mSelectAddress == null) {
            return;
        }
        showDialog();
    }

    public void save() {
        boolean z;
        final String trim = this.etAddressName.getText().toString().trim();
        final String trim2 = this.etAddress.getText().toString().trim();
        final String trim3 = this.etDescription.getText().toString().trim();
        if (this.mTypeFrom.equals(TYPE_ADDRESS_DETAILS) && this.oldAddress.equals(trim) && this.oldAddress.equals(trim2) && this.oldDescription.equals(trim3)) {
            toast(getString(R.string.saved_successfully));
            setResult(-1);
            finish();
        } else if (StringTronUtil.isEmpty(trim)) {
            showOrHideNameError(true, getString(R.string.address_name_empty));
        } else if (StringTronUtil.isEmpty(trim2)) {
            showOrHideAddressError(true, getString(R.string.enter_address_empty));
        } else {
            List<AddressDao> list = this.addressList;
            if (list == null || list.size() == 0) {
                if (!StringTronUtil.isAddressValid(StringTronUtil.decodeFromBase58Check(trim2))) {
                    showOrHideAddressError(true, getString(R.string.enter_corret_address));
                    return;
                }
            } else {
                boolean isAddressValid = StringTronUtil.isAddressValid(StringTronUtil.decodeFromBase58Check(trim2));
                int i = 0;
                if (this.mTypeFrom.equals(TYPE_ADD_ADDRESS)) {
                    z = false;
                    int i2 = 0;
                    while (i < this.addressList.size()) {
                        AddressDao addressDao = this.addressList.get(i);
                        if (addressDao != null && !StringTronUtil.isEmpty(addressDao.name) && trim.equals(addressDao.name)) {
                            z = true;
                        }
                        if (isAddressValid && addressDao != null && !StringTronUtil.isEmpty(addressDao.address) && trim2.equals(addressDao.address)) {
                            i2 = 1;
                        }
                        i++;
                    }
                    i = i2;
                } else {
                    if (!this.oldName.equals(trim)) {
                        for (int i3 = 0; i3 < this.addressList.size(); i3++) {
                            AddressDao addressDao2 = this.addressList.get(i3);
                            if (addressDao2 != null && !StringTronUtil.isEmpty(addressDao2.name) && trim.equals(addressDao2.name)) {
                                z = true;
                                break;
                            }
                        }
                    }
                    z = false;
                    if (!this.oldAddress.equals(trim2)) {
                        int i4 = 0;
                        while (true) {
                            if (i4 >= this.addressList.size()) {
                                break;
                            }
                            AddressDao addressDao3 = this.addressList.get(i4);
                            if (isAddressValid && addressDao3 != null && !StringTronUtil.isEmpty(addressDao3.address) && trim2.equals(addressDao3.address)) {
                                i = 1;
                                break;
                            }
                            i4++;
                        }
                    }
                }
                if (z) {
                    showOrHideNameError(true, getString(R.string.name_exists));
                    return;
                } else if (!isAddressValid) {
                    showOrHideAddressError(true, getString(R.string.enter_corret_address));
                    return;
                } else if (i != 0) {
                    showOrHideAddressError(true, getString(R.string.address_exists));
                    return;
                }
            }
            runOnThreeThread(new OnBackground() {
                @Override
                public final void doOnBackground() {
                    lambda$save$2(trim, trim2, trim3);
                }
            });
        }
    }

    public void lambda$save$2(String str, String str2, String str3) {
        try {
            AddressDao addressDao = new AddressDao();
            addressDao.name = str;
            addressDao.address = str2;
            if (StringTronUtil.isEmpty(str3)) {
                str3 = "";
            }
            addressDao.description = str3;
            if (this.mTypeFrom.equals(TYPE_ADD_ADDRESS)) {
                AddressController.getInstance(this).insert(addressDao);
            } else {
                AddressDao addressDao2 = this.mSelectAddress;
                if (addressDao2 != null) {
                    addressDao.setId(addressDao2.getId());
                    AddressController.getInstance(this).update(addressDao);
                }
            }
            runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$save$0();
                }
            });
        } catch (Exception e) {
            LogUtils.e(e);
            runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$save$1();
                }
            });
        }
    }

    public void lambda$save$0() {
        toast(getString(R.string.saved_successfully));
        setResult(-1);
        finish();
    }

    public void lambda$save$1() {
        try {
            toast(getString(R.string.saved_failed));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startForResult(Activity activity, String str, int i, List<AddressDao> list, AddressDao addressDao) {
        Intent intent = new Intent(activity, AddAddressActivity.class);
        intent.putExtra(TAG_FROM_TYPE, str);
        intent.putExtra(TAG_SELECT_ADDRESS, addressDao);
        intent.putExtra(TAG_ADDRESS_LIST, (Serializable) list);
        activity.startActivityForResult(intent, i);
    }

    public void setClick() {
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id == R.id.rl_delete) {
                    etAddress.setText("");
                } else if (id != R.id.rl_scan) {
                } else {
                    scanClick();
                }
            }
        };
        this.binding.rlScan.setOnClickListener(noDoubleClickListener2);
        this.binding.rlDelete.setOnClickListener(noDoubleClickListener2);
    }

    private void showDialog() {
        CustomDialog.Builder builder = new CustomDialog.Builder(this);
        CustomDialog build = builder.style(R.style.loading_dialog).cancelTouchout(true).heightDimenRes(-2).widthDimenRes(-1).setGravity(17).view(R.layout.dg_address_delete).build();
        this.dialog = build;
        build.setCanceledOnTouchOutside(true);
        View view = builder.getView();
        ((RelativeLayout) view.findViewById(R.id.rl_root)).setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view2) {
                dialog.dismiss();
            }
        });
        ((TextView) view.findViewById(R.id.tv_cancle)).setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view2) {
                dialog.dismiss();
            }
        });
        ((TextView) view.findViewById(R.id.tv_confirm)).setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view2) {
                dialog.dismiss();
                delete();
            }
        });
        if (isFinishing()) {
            return;
        }
        this.dialog.show();
    }

    public void delete() {
        runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$delete$5();
            }
        });
    }

    public void lambda$delete$5() {
        try {
            AddressController.getInstance(this).delete(this.mSelectAddress.name);
            runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$delete$3();
                }
            });
        } catch (Exception e) {
            LogUtils.e(e);
            runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$delete$4();
                }
            });
        }
    }

    public void lambda$delete$3() {
        toast(getString(R.string.deleted_successfully));
        setResult(-1);
        finish();
    }

    public void lambda$delete$4() {
        try {
            toast(getString(R.string.deleted_failed));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkInputContent(EditText editText, int i) {
        try {
            Editable text = editText.getText();
            String trim = text.toString().trim();
            int selectionEnd = Selection.getSelectionEnd(text);
            int i2 = 0;
            for (int i3 = 0; i3 < trim.length(); i3++) {
                if (trim.charAt(i3) >= ' ') {
                }
                i2++;
                if (i2 > i) {
                    String substring = trim.substring(0, i3);
                    LogUtils.d("alex", "the string content is " + substring);
                    editText.setText(substring);
                    Editable text2 = editText.getText();
                    if (selectionEnd > text2.length()) {
                        selectionEnd = text2.length();
                    }
                    Selection.setSelection(text2, selectionEnd);
                    return;
                }
            }
        } catch (Exception e) {
            SentryUtil.captureException(e);
            LogUtils.e(e);
        }
    }

    public void showOrHideNameError(boolean z, String str) {
        if (z) {
            this.rlErrorName.setVisibility(View.VISIBLE);
            this.tvNameError.setText(str);
            return;
        }
        this.rlErrorName.setVisibility(View.GONE);
    }

    public void showOrHideAddressError(boolean z, String str) {
        if (z) {
            this.rlErrorAddress.setVisibility(View.VISIBLE);
            this.tvAddressError.setText(str);
            return;
        }
        this.rlErrorAddress.setVisibility(View.GONE);
    }

    @Override
    public String[] getPermissions() {
        return new String[]{"android.permission.CAMERA"};
    }

    @Override
    public void requestPermissionsFail() {
        ToastError(R.string.error_permission);
    }

    @Override
    public void requestPermissionsSuccess() {
        ScannerActivity.start(this);
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        try {
            if (this.mPermissionHelper == null) {
                this.mPermissionHelper = new PermissionHelper(this, this);
            }
            if (this.mPermissionHelper.requestPermissionsResult(i, strArr, iArr)) {
                return;
            }
            super.onRequestPermissionsResult(i, strArr, iArr);
        } catch (Exception e) {
            SentryUtil.captureException(e);
        }
    }

    public void scanClick() {
        PermissionHelper permissionHelper = new PermissionHelper(this, this);
        this.mPermissionHelper = permissionHelper;
        permissionHelper.requestPermissions();
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        IntentResult parseActivityResult = IntentIntegrator.parseActivityResult(i, i2, intent);
        if (parseActivityResult != null) {
            if (parseActivityResult.getContents() != null) {
                String stringExtra = intent.getStringExtra("SCAN_RESULT");
                if (StringTronUtil.isEmpty(stringExtra)) {
                    stringExtra = "";
                }
                this.etAddress.setText(stringExtra);
                return;
            }
            return;
        }
        super.onActivityResult(i, i2, intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CustomDialog customDialog = this.dialog;
        if (customDialog != null) {
            customDialog.dismiss();
        }
        this.binding = null;
    }
}
