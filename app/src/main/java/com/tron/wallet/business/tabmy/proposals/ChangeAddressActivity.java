package com.tron.wallet.business.tabmy.proposals;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.tabmy.myhome.addressbook.AddressBookActivity;
import com.tron.wallet.common.components.ErrorEdiTextLayout;
import com.tron.wallet.common.components.TrimEditText;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.AcChangeAddressBinding;
import com.tronlinkpro.wallet.R;
public class ChangeAddressActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    public static final String CHANGE_ADDRESS = "change_address";
    public static final String CHANGE_ADDRESS_NEW = "change_address_new";
    public static final String CHANGE_ADDRESS_TITLE = "change_address_title";
    public static final String CHANGE_DESCRIPTION = "change_description";
    public static final String CHANGE_TITLE = "change_title";
    private static final String TAG = "ChangeAddressActivity";
    private AcChangeAddressBinding binding;
    private String content;
    ErrorEdiTextLayout eetAddress;
    TrimEditText etAddress;
    private String mAddressTitle;
    private String mDescription;
    private String mTitle;
    private String oldAddress;
    RelativeLayout rlAddressBook;
    TextView tvAddressTitle;
    TextView tvDescription;

    @Override
    protected void setLayout() {
        AcChangeAddressBinding inflate = AcChangeAddressBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        LinearLayout root = inflate.getRoot();
        mappingId();
        setClick();
        setView(root, 1);
        Intent intent = getIntent();
        if (intent != null) {
            this.mTitle = intent.getStringExtra(CHANGE_TITLE);
            this.mAddressTitle = intent.getStringExtra(CHANGE_ADDRESS_TITLE);
            this.mDescription = intent.getStringExtra(CHANGE_DESCRIPTION);
            this.oldAddress = intent.getStringExtra(CHANGE_ADDRESS);
        }
        if (!StringTronUtil.isEmpty(this.mTitle)) {
            setHeaderBar(this.mTitle);
        } else {
            setHeaderBar(getString(R.string.change_address));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.tvAddressTitle = this.binding.tvAddressTitle;
        this.etAddress = this.binding.etAddress;
        this.tvDescription = this.binding.tvDescription;
        this.eetAddress = this.binding.eetAddress;
        this.rlAddressBook = this.binding.rlAddressBook;
    }

    @Override
    protected void processData() {
        if (!StringTronUtil.isEmpty(this.mAddressTitle)) {
            this.tvAddressTitle.setText(this.mAddressTitle);
        } else {
            this.tvAddressTitle.setText(R.string.address);
        }
        if (!StringTronUtil.isEmpty(this.mDescription)) {
            this.tvDescription.setText(this.mDescription);
            this.tvDescription.setVisibility(View.VISIBLE);
        } else {
            this.tvDescription.setVisibility(View.GONE);
        }
        if (!StringTronUtil.isEmpty(this.oldAddress)) {
            this.etAddress.setText(this.oldAddress);
        }
        this.eetAddress.setTextError3(getString(R.string.incor_address_format));
        this.etAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                eetAddress.hideError3();
            }
        });
        this.rlAddressBook.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                AddressBookActivity.startForResult(ChangeAddressActivity.this, AddressBookActivity.TYPE_SELECT_ADDRESS, TronConfig.ADDRESS_BOOK_SELECT_CODE);
            }
        });
    }

    public static void start(Context context, BaseFragment baseFragment, String str, String str2, String str3, String str4) {
        Intent intent = new Intent(context, ChangeAddressActivity.class);
        intent.putExtra(CHANGE_TITLE, str);
        intent.putExtra(CHANGE_ADDRESS_TITLE, str2);
        intent.putExtra(CHANGE_DESCRIPTION, str3);
        intent.putExtra(CHANGE_ADDRESS, str4);
        baseFragment.goForResult(intent, TronConfig.CHANGE_ADDRESS_CODE);
    }

    public static void start(BaseActivity baseActivity, String str, String str2, String str3, String str4) {
        Intent intent = new Intent(baseActivity, ChangeAddressActivity.class);
        intent.putExtra(CHANGE_TITLE, str);
        intent.putExtra(CHANGE_ADDRESS_TITLE, str2);
        intent.putExtra(CHANGE_DESCRIPTION, str3);
        intent.putExtra(CHANGE_ADDRESS, str4);
        baseActivity.goForResult(intent, TronConfig.CHANGE_ADDRESS_CODE);
    }

    public void setClick() {
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id != R.id.confirm) {
                    if (id != R.id.rl_clear) {
                        return;
                    }
                    etAddress.setText("");
                    return;
                }
                try {
                    ChangeAddressActivity changeAddressActivity = ChangeAddressActivity.this;
                    changeAddressActivity.content = StringTronUtil.getText(changeAddressActivity.etAddress);
                    if (!StringTronUtil.isAddressValid(StringTronUtil.decodeFromBase58Check(content))) {
                        eetAddress.showError3();
                    } else {
                        Intent intent = new Intent();
                        intent.putExtra(ChangeAddressActivity.CHANGE_ADDRESS_NEW, content);
                        setResult(TronConfig.CHANGE_ADDRESS_CODE, intent);
                        LogUtils.i(ChangeAddressActivity.TAG, "confirm-Address" + content);
                        finish();
                    }
                } catch (Exception e) {
                    LogUtils.e(e);
                    eetAddress.showError3();
                }
            }
        };
        this.binding.confirm.setOnClickListener(noDoubleClickListener2);
        this.binding.rlClear.setOnClickListener(noDoubleClickListener2);
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 2027 && intent != null) {
            String stringExtra = intent.getStringExtra(TronConfig.ADDRESS_BOOK_SELECT);
            if (StringTronUtil.isEmpty(stringExtra)) {
                this.etAddress.setText("");
            } else {
                this.etAddress.setText(stringExtra);
            }
        }
    }
}
