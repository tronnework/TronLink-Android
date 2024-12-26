package com.tron.wallet.business.walletmanager.createaccount;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.reminder.BackupReminder;
import com.tron.wallet.business.walletmanager.common.AddressItem;
import com.tron.wallet.business.walletmanager.createaccount.CreateAccountContract;
import com.tron.wallet.business.walletmanager.importwallet.rv.AddressAdapter;
import com.tron.wallet.common.bean.AccountBalanceOutput;
import com.tron.wallet.common.components.CommonTitleDescriptionEditView;
import com.tron.wallet.common.components.ErrorBottomLayout;
import com.tron.wallet.common.components.SimpleDraweeViewGif;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.AcCreateAccountBinding;
import com.tron.wallet.databinding.ContentCreateAccountBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.util.List;
import org.tron.walletserver.InvalidNameException;
import org.tron.walletserver.Wallet;
public class CreateAccountActivity extends BaseActivity<CreateAccountPresenter, CreateAccountModel> implements CreateAccountContract.View {
    public static final int MAX_NAME_LENGTH = 28;
    AddressAdapter adapter;
    AppBarLayout appBar;
    private AcCreateAccountBinding binding;
    Button btCreate;
    TextView btSwitchAccount;
    View btSwitchWallet;
    View btTvRight;
    private int fromWhat;
    SimpleDraweeViewGif gifImage;
    CommonTitleDescriptionEditView inputWalletName;
    private boolean isHdWalletBackup = true;
    ErrorBottomLayout llErrAccount;
    View llMore;
    View llNameError;
    RecyclerView recyclerView;
    View root;
    NestedScrollView scrollCreateWallet;
    CollapsingToolbarLayout toolBarLayout;
    Toolbar toolbar;
    TextView tvHdAddress;
    TextView tvHdName;
    TextView tvNameError;
    private String walletName;

    @Override
    public String getInputWalletName() {
        return this.walletName;
    }

    public static void start(Context context, String str, String str2, String str3, boolean z) {
        Intent intent = new Intent(context, CreateAccountActivity.class);
        intent.putExtra(TronConfig.WALLET_extra, str);
        intent.putExtra(TronConfig.WALLET_DATA, str2);
        intent.putExtra(TronConfig.WALLET_DATA2, z);
        intent.putExtra(TronConfig.WALLET_PASSWORD, str3);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        setBackground(getResources().getColor(R.color.white), R.mipmap.bg_createwallet);
        AcCreateAccountBinding inflate = AcCreateAccountBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId(this.binding.contentCreateAccount);
        setView(root, 0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId(ContentCreateAccountBinding contentCreateAccountBinding) {
        this.btCreate = this.binding.btCreate;
        this.scrollCreateWallet = contentCreateAccountBinding.scrollCreateWallet;
        this.toolbar = this.binding.toolbar;
        this.toolBarLayout = this.binding.toolbarLayout;
        this.gifImage = this.binding.gifImage;
        this.appBar = this.binding.appBar;
        this.root = this.binding.rlRoot;
        this.inputWalletName = contentCreateAccountBinding.inputWalletName;
        this.btSwitchAccount = contentCreateAccountBinding.tvSwitchAccount;
        this.btSwitchWallet = contentCreateAccountBinding.tvSwitchHd;
        this.btTvRight = this.binding.tvRight;
        this.tvHdName = contentCreateAccountBinding.tvHdName;
        this.tvHdAddress = contentCreateAccountBinding.tvHdAddress;
        this.tvNameError = contentCreateAccountBinding.tvNameError;
        this.llNameError = contentCreateAccountBinding.llNameError;
        this.llMore = contentCreateAccountBinding.llMore;
        this.recyclerView = contentCreateAccountBinding.rvList;
        this.llErrAccount = contentCreateAccountBinding.llErrAccount;
    }

    @Override
    protected void processData() {
        setSupportActionBar(this.toolbar);
        this.toolBarLayout.setTitle(getString(R.string.create_account));
        this.gifImage.setGif(R.drawable.address_2, 1);
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ((CreateAccountPresenter) this.mPresenter).init();
        setBtCreateState(false);
        addListener();
        this.btCreate.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                ((CreateAccountPresenter) mPresenter).create();
            }
        });
        this.btTvRight.setOnClickListener(((CreateAccountPresenter) this.mPresenter).btTvRightListener());
        this.btSwitchAccount.setOnClickListener(((CreateAccountPresenter) this.mPresenter).btSwitchAccountListener());
        this.btSwitchWallet.setVisibility(View.GONE);
        this.fromWhat = getIntent().getIntExtra("from", 0);
        this.adapter = new AddressAdapter(this.fromWhat);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getIContext(), 1, false));
        this.recyclerView.setAdapter(this.adapter);
        AnalyticsHelper.logEvent(AnalyticsHelper.CreateAccountPage.ENTER_CREATE_ACCOUNT_PAGE);
    }

    public void addListener() {
        this.inputWalletName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                walletName = editable.toString().trim();
                llNameError.setVisibility(View.GONE);
                if (TextUtils.isEmpty(walletName)) {
                    CreateAccountActivity createAccountActivity = CreateAccountActivity.this;
                    createAccountActivity.showNameError(createAccountActivity.getResources().getString(R.string.error_name3));
                    setBtCreateState(false);
                    changeAddressRightButton(false);
                    return;
                }
                checkInputName();
                changeAddressRightButton(true);
            }
        });
        this.inputWalletName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean z) {
                changeAddressRightButton(z);
            }
        });
        this.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputWalletName.clearFocus();
            }
        });
    }

    public void changeAddressRightButton(boolean z) {
        if (z) {
            this.inputWalletName.setRightImageResId(R.mipmap.delete_gray);
            this.inputWalletName.setRightDrawableClick(new CommonTitleDescriptionEditView.RightDrawableClick() {
                @Override
                public final void onRightDrawableClick(View view) {
                    lambda$changeAddressRightButton$0(view);
                }
            });
            return;
        }
        this.inputWalletName.setRightImage(0);
        this.inputWalletName.setRightDrawableClick(null);
    }

    public void lambda$changeAddressRightButton$0(View view) {
        this.inputWalletName.setText("");
    }

    @Override
    public void showNameError(final String str) {
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$showNameError$1(str);
            }
        });
    }

    public void lambda$showNameError$1(String str) {
        this.llNameError.setVisibility(View.VISIBLE);
        this.tvNameError.setText(str);
    }

    @Override
    public void hideSwitchHdButton() {
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$hideSwitchHdButton$2();
            }
        });
    }

    public void lambda$hideSwitchHdButton$2() {
        this.btSwitchWallet.setVisibility(View.GONE);
        this.btSwitchWallet.setEnabled(false);
    }

    public void checkInputName() {
        if (StringTronUtil.isEmpty(this.walletName)) {
            setBtCreateState(false);
            showNameError(getResources().getString(R.string.error_name3));
            return;
        }
        try {
            String trimEnd = StringTronUtil.trimEnd(this.walletName.toCharArray());
            this.walletName = trimEnd;
            if (!StringTronUtil.validataLegalString2(trimEnd)) {
                setBtCreateState(false);
                showNameError(getResources().getString(R.string.error_name2));
            } else if (WalletUtils.existWallet(this.walletName)) {
                setBtCreateState(false);
                showNameError(getResources().getString(R.string.exist_wallet_name));
            } else {
                try {
                    Editable text = this.inputWalletName.getText();
                    String trim = text.toString().trim();
                    int selectionEnd = Selection.getSelectionEnd(text);
                    int i = 0;
                    for (int i2 = 0; i2 < trim.length(); i2++) {
                        if (trim.charAt(i2) >= ' ') {
                        }
                        i++;
                        if (i > 28) {
                            this.inputWalletName.setText(trim.substring(0, i2));
                            Editable text2 = this.inputWalletName.getText();
                            if (selectionEnd > text2.length()) {
                                selectionEnd = text2.length();
                            }
                            Selection.setSelection(text2, selectionEnd);
                        }
                    }
                    setBtCreateState(true);
                } catch (Exception e) {
                    SentryUtil.captureException(e);
                    LogUtils.e(e);
                    setBtCreateState(false);
                }
            }
        } catch (InvalidNameException e2) {
            LogUtils.e(e2);
            setBtCreateState(false);
            showNameError(getResources().getString(R.string.error_name2));
        }
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        ((CreateAccountPresenter) this.mPresenter).onActivityResult(i, i2, intent);
    }

    @Override
    public void generateWalletName(String str) {
        this.inputWalletName.setText(str);
        checkInputName();
        changeAddressRightButton(false);
    }

    @Override
    public void updateButtonEnable(boolean z) {
        setBtCreateState(z);
    }

    public void setBtCreateState(boolean z) {
        this.btCreate.setEnabled(z);
        if (this.isHdWalletBackup) {
            return;
        }
        this.btCreate.setEnabled(false);
    }

    @Override
    public Intent _getIntent() {
        return getIntent();
    }

    @Override
    public void updateHDUI(final Wallet wallet) {
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$updateHDUI$3(wallet);
            }
        });
    }

    public void lambda$updateHDUI$3(Wallet wallet) {
        this.isHdWalletBackup = true;
        this.llErrAccount.clearError();
        this.btSwitchAccount.setTextColor(getResources().getColor(R.color.blue_3c));
        this.btSwitchAccount.setEnabled(true);
        if (wallet == null) {
            return;
        }
        try {
            boolean isWalletBackup = BackupReminder.isWalletBackup(wallet);
            this.isHdWalletBackup = isWalletBackup;
            if (!isWalletBackup) {
                this.llErrAccount.setError(ErrorBottomLayout.ErrorType.ERROR, getResources().getString(R.string.wallet_not_backup_fail_to_create_new));
                this.btSwitchAccount.setTextColor(getResources().getColor(R.color.gray_9B));
                this.btSwitchAccount.setEnabled(false);
            }
            this.tvHdName.setText(wallet.getWalletName());
            this.tvHdAddress.setText(wallet.getAddress());
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    @Override
    public void updateItems(final List<AddressItem> list) {
        runOnUIThread(new OnMainThread() {
            @Override
            public void doInUIThread() {
                adapter.setNewData(list);
            }
        });
    }

    @Override
    public void updateBalances(AccountBalanceOutput accountBalanceOutput) {
        if (accountBalanceOutput == null || accountBalanceOutput.getData() == null || accountBalanceOutput.getData().getBalanceList() == null) {
            return;
        }
        List<AddressItem> data = this.adapter.getData();
        for (int i = 0; i < accountBalanceOutput.getData().getBalanceList().size(); i++) {
            AccountBalanceOutput.DataBean.BalanceListBean balanceListBean = accountBalanceOutput.getData().getBalanceList().get(i);
            for (int i2 = 0; i2 < data.size(); i2++) {
                if (balanceListBean.getAddress().equals(data.get(i2).getAddress())) {
                    data.get(i2).setBalance(balanceListBean.getBalance());
                    LogUtils.d("CreateAccount", "balanceItem:  " + balanceListBean.getAddress() + balanceListBean.getBalance());
                }
            }
        }
        this.adapter.setNewData(data);
    }

    @Override
    public void updateBalance(double d) {
        if (BigDecimalUtils.LessThan((Object) Double.valueOf(d), (Object) 0)) {
            return;
        }
        StringTronUtil.formatNumber6Truncate(BigDecimalUtils.toBigDecimal(Double.valueOf(d)));
    }
}
