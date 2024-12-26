package com.tron.wallet.business.walletmanager.importwallet;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.fastjson.JSON;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.create.creatwallet.AddWalletType;
import com.tron.wallet.business.migrate.MigrateActivityExternalSyntheticLambda2;
import com.tron.wallet.business.walletmanager.adapter.ChooseAddressActionProvider;
import com.tron.wallet.business.walletmanager.common.AddressItem;
import com.tron.wallet.business.walletmanager.common.CommonKey;
import com.tron.wallet.business.walletmanager.common.ImportType;
import com.tron.wallet.business.walletmanager.importwallet.SelectMnemonicAddressActivity;
import com.tron.wallet.business.walletmanager.importwallet.SelectMnemonicAddressContract;
import com.tron.wallet.business.walletmanager.importwallet.rv.AddressAdapter;
import com.tron.wallet.business.walletmanager.importwallet.rv.AddressItemHolder;
import com.tron.wallet.common.bean.AccountBalanceOutput;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.common.utils.UserIconRandom;
import com.tron.wallet.common.utils.WalletChecker;
import com.tron.wallet.common.utils.WalletNameGeneratorUtils;
import com.tron.wallet.databinding.AcSelectAddressBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.tron.common.bip39.BIP39;
import org.tron.common.bip39.ValidationException;
import org.tron.common.utils.ByteArray;
import org.tron.walletserver.I_TYPE;
import org.tron.walletserver.Wallet;
import org.tron.walletserver.WalletPath;
public class SelectMnemonicAddressActivity extends BaseActivity<SelectMnemonicAddressPresenter, SelectMnemonicAddressModel> implements SelectMnemonicAddressContract.View {
    public static Wallet currentWallet = null;
    public static String ledgerName = "";
    private AddressAdapter adapter;
    private List<AddressItem> addressItemList;
    private HashSet<String> addressList;
    private AddressItem addressShieldItem;
    View addressShieldView;
    private AddressItemHolder addressViewHolder;
    private AcSelectAddressBinding binding;
    Button btnNext;
    private Button btnRetry;
    CheckBox chkNonHD;
    AppCompatCheckBox ckTag;
    private String content;
    private ArrayList<AddressItem> currentSelectItems;
    View errorLayout;
    private int fromWhat;
    private boolean isNonHD = false;
    protected boolean isShield;
    View ivLoading;
    View loadFailView;
    private String name;
    private String password;
    RecyclerView recyclerView;
    RelativeLayout rlChkNonHD;
    TextView tvAddress;
    TextView tvBalance;
    TextView tvChangeAddress;
    TextView tvError;
    TextView tvFailTips;
    TextView tvIDX;
    TextView tvPath;
    View viewContent;
    private List<Wallet> walletList;

    @Override
    public String getContent() {
        return this.content;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public View getRootView() {
        return this.viewContent;
    }

    @Override
    public boolean isShield() {
        return this.isShield;
    }

    private void mappingId() {
        this.btnNext = this.binding.btnNext;
        this.errorLayout = this.binding.errorLayout.getRoot();
        this.tvError = this.binding.errorLayout.tvError;
        this.tvAddress = this.binding.addressItemShieldContainer.tvAddress;
        this.tvBalance = this.binding.addressItemShieldContainer.tvBalance;
        this.ckTag = this.binding.addressItemShieldContainer.ivSelectTag;
        this.tvPath = this.binding.addressItemShieldContainer.tvPath;
        this.tvIDX = this.binding.addressItemShieldContainer.ivIdx;
        this.tvChangeAddress = this.binding.tvChangeAddress;
        this.addressShieldView = this.binding.addressItemShieldContainer.getRoot();
        this.ivLoading = this.binding.ivLoading;
        this.loadFailView = this.binding.viewLoadFail.getRoot();
        this.tvFailTips = this.binding.viewLoadFail.tvTip;
        this.chkNonHD = this.binding.chbNonhd;
        this.rlChkNonHD = this.binding.rlChbNonhd;
        this.recyclerView = this.binding.rvList;
        this.viewContent = this.binding.viewContent;
        this.btnRetry = this.binding.viewLoadFail.btnRetry;
    }

    public static void start(Context context, int i, String str, String str2, String str3, boolean z) {
        context.startActivity(getBaseIntent(context, i, str, z, str2, str3));
    }

    public static Intent getBaseIntent(Context context, int i, String str, boolean z, String str2, String str3) {
        Intent intent = new Intent(context, SelectMnemonicAddressActivity.class);
        intent.putExtra(TronConfig.WALLET_TYPE, i);
        intent.putExtra(TronConfig.WALLET_NAME, str2);
        intent.putExtra(TronConfig.WALLET_PASSWORD, str3);
        intent.putExtra(TronConfig.IMPORT_CONTENT, str);
        intent.putExtra(AddWalletType.INTENT_KEY_SHIELD, z);
        intent.putExtra("from", ImportType.parseFromTronConfig(i));
        return intent;
    }

    @Override
    protected void setLayout() {
        AcSelectAddressBinding inflate = AcSelectAddressBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 1);
        setHeaderBar(getString(R.string.import_wallet));
        getHeaderHolder().tvCommonTitle.setTextSize(22.0f);
        mappingId();
    }

    @Override
    protected void processData() {
        this.fromWhat = getIntent().getIntExtra("from", 0);
        this.name = getIntent().getStringExtra(TronConfig.WALLET_NAME);
        this.isShield = getIntent().getBooleanExtra(AddWalletType.INTENT_KEY_SHIELD, false);
        this.password = getIntent().getStringExtra(TronConfig.WALLET_PASSWORD);
        this.content = getIntent().getStringExtra(TronConfig.IMPORT_CONTENT);
        if (this.fromWhat == 1) {
            ledgerName = getIntent().getStringExtra(ImportType.KEY_LEDGER_NAME);
            setupIntroView();
            AnalyticsHelper.logEvent(AnalyticsHelper.LedgerPage.ENTER_LEDGER_FLOW_IMPORT_ADDRESS);
        }
        this.currentSelectItems = new ArrayList<>();
        initViews();
        ((SelectMnemonicAddressPresenter) this.mPresenter).setLoadAddressAction(ChooseAddressActionProvider.provide(this.fromWhat));
        if (this.fromWhat == 0) {
            this.btnNext.setText(R.string.import_mnemonic);
        } else {
            this.btnNext.setText(R.string.import_ledger);
        }
        List<Wallet> allWallets = WalletUtils.getAllWallets();
        this.addressList = new HashSet<>();
        for (int i = 0; i < allWallets.size(); i++) {
            Wallet wallet = allWallets.get(i);
            if (wallet != null) {
                this.addressList.add(wallet.getAddress());
            }
        }
        this.chkNonHD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    tvChangeAddress.setVisibility(View.GONE);
                    String str = content;
                    if (!StringTronUtil.isEmpty(str)) {
                        String[] split = str.split("\\s+");
                        if (split != null && split.length == 24) {
                            try {
                                String hexString = ByteArray.toHexString(BIP39.decode(str, "pass"));
                                if (!StringTronUtil.isEmpty(hexString)) {
                                    Wallet wallet2 = new Wallet(I_TYPE.PRIVATE, hexString);
                                    String address = wallet2.getAddress();
                                    onCreateWallet(wallet2, true);
                                    isNonHD = true;
                                    if (addressList.contains(address)) {
                                        btnNext.setEnabled(false);
                                    }
                                }
                            } catch (ValidationException e) {
                                ToastAsBottom(R.string.convert6);
                                chkNonHD.setChecked(false);
                                isNonHD = false;
                                LogUtils.e(e);
                            }
                        } else {
                            ToastAsBottom(R.string.convert_mnemon_error);
                            chkNonHD.setChecked(false);
                            isNonHD = false;
                        }
                    } else {
                        ToastAsBottom(R.string.trx_empty);
                        chkNonHD.setChecked(false);
                        isNonHD = false;
                    }
                    SelectMnemonicAddressActivity selectMnemonicAddressActivity = SelectMnemonicAddressActivity.this;
                    selectMnemonicAddressActivity.changeToNonHD(selectMnemonicAddressActivity.isNonHD);
                    return;
                }
                tvChangeAddress.setVisibility(View.VISIBLE);
                isNonHD = false;
                refresh();
                SelectMnemonicAddressActivity selectMnemonicAddressActivity2 = SelectMnemonicAddressActivity.this;
                selectMnemonicAddressActivity2.changeToNonHD(selectMnemonicAddressActivity2.isNonHD);
            }
        });
        String[] split = this.content.split(" ");
        if (this.isShield) {
            this.tvChangeAddress.setVisibility(View.GONE);
            this.chkNonHD.setVisibility(View.GONE);
            this.rlChkNonHD.setVisibility(View.GONE);
            this.tvPath.setVisibility(View.GONE);
            this.tvBalance.setVisibility(View.GONE);
            this.tvIDX.setVisibility(View.GONE);
            this.tvBalance.setVisibility(View.GONE);
            refresh();
        } else if (split.length == 24) {
            this.rlChkNonHD.setVisibility(View.VISIBLE);
            this.chkNonHD.setChecked(true);
            this.tvChangeAddress.setVisibility(View.GONE);
            this.isNonHD = true;
            initNonHD();
        } else {
            this.rlChkNonHD.setVisibility(View.GONE);
            refresh();
        }
    }

    private void setupIntroView() {
        try {
            getHeaderHolder().tvCommonRight.setText(R.string.tutorial);
            getHeaderHolder().tvCommonRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    lambda$setupIntroView$0(view);
                }
            });
            getHeaderHolder().tvCommonRight.setTextColor(getResources().getColor(R.color.blue_13));
            getHeaderHolder().tvCommonRight.setVisibility(View.GONE);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void lambda$setupIntroView$0(View view) {
        UIUtils.toHowUseLedger(this);
        AnalyticsHelper.logEvent(AnalyticsHelper.LedgerPage.CLICK_LEDGER_FLOW_TUTORIAL);
    }

    @Override
    public void onCreateWallet(Wallet wallet, boolean z) {
        if (wallet != null && wallet.isShieldedWallet()) {
            this.addressShieldView.setVisibility(View.VISIBLE);
            if (this.ivLoading.getAnimation() != null) {
                this.ivLoading.getAnimation().cancel();
            }
            this.ivLoading.setVisibility(View.GONE);
            this.addressShieldItem = AddressItem.fromWallet(wallet);
            updateShieldItem();
            return;
        }
        this.walletList = new ArrayList();
        this.addressItemList = new ArrayList();
        if (this.ivLoading.getAnimation() != null) {
            this.ivLoading.getAnimation().cancel();
        }
        this.ivLoading.setVisibility(View.GONE);
        AddressItem fromWallet = AddressItem.fromWallet(wallet);
        if (wallet != null && this.addressList.contains(wallet.getAddress())) {
            fromWallet.setExisted(true);
        }
        wallet.setWalletName(this.name);
        fromWallet.setSelected(true);
        this.walletList.add(wallet);
        this.addressItemList.add(fromWallet);
        updateNewItem();
        this.loadFailView.setVisibility(View.GONE);
    }

    private void updateShieldItem() {
        this.addressShieldItem.setSelected(true);
        this.tvBalance.setVisibility(View.GONE);
        AddressItem addressItem = this.addressShieldItem;
        if (addressItem != null) {
            this.tvAddress.setText(addressItem.getAddress());
        }
    }

    private void updateNewItem() {
        this.currentSelectItems.clear();
        this.currentSelectItems.addAll(this.addressItemList);
        this.adapter.setNewData(this.addressItemList);
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < this.walletList.size(); i++) {
            arrayList.add(this.walletList.get(i).getAddress());
        }
        ((SelectMnemonicAddressPresenter) this.mPresenter).getBalances(arrayList);
    }

    @Override
    public void onCreateWalletFailed(Throwable th) {
        if (this.fromWhat != 1) {
            return;
        }
        this.loadFailView.setVisibility(View.VISIBLE);
        this.tvFailTips.setText(String.format(getString(R.string.unable_find_ledger), ledgerName));
        getHeaderHolder().tvCommonRight.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGetBalance(AccountBalanceOutput accountBalanceOutput) {
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
        if (this.isNonHD) {
            changeToNonHD(true);
        } else {
            changeToNonHD(false);
        }
        if (this.addressItemList.size() > 0) {
            this.btnNext.setEnabled(true);
        } else {
            this.btnNext.setEnabled(false);
        }
        this.adapter.setNewData(data);
    }

    public void changeToNonHD(boolean z) {
        AddressItemHolder addressItemHolder = this.addressViewHolder;
        if (addressItemHolder != null) {
            addressItemHolder.changeToNonHD(z);
        }
        if (this.adapter.getItemCount() == 1) {
            this.adapter.getData().get(0).setNonHd(z);
            this.adapter.notifyDataSetChanged();
        }
    }

    @Override
    public Intent getViewIntent() {
        return getIntent();
    }

    @Override
    public boolean isCheckNoHD() {
        return this.chkNonHD.isChecked();
    }

    public class fun2 extends NoDoubleClickListener2 {
        fun2() {
        }

        @Override
        protected void onNoDoubleClick(View view) {
            int id = view.getId();
            if (id == R.id.btn_next) {
                ((SelectMnemonicAddressPresenter) mPresenter).addDisposable(Observable.create(new ObservableOnSubscribe() {
                    @Override
                    public final void subscribe(ObservableEmitter observableEmitter) {
                        SelectMnemonicAddressActivity.2.this.lambda$onNoDoubleClick$1(observableEmitter);
                    }
                }).subscribe(new Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        SelectMnemonicAddressActivity.2.this.lambda$onNoDoubleClick$2((Boolean) obj);
                    }
                }));
            } else if (id == R.id.btn_retry) {
                refresh();
            } else if (id != R.id.tv_change_address) {
            } else {
                if (addressItemList.get(0) != null) {
                    ((SelectMnemonicAddressPresenter) mPresenter).onClickAddress(((AddressItem) addressItemList.get(0)).getMnemonicPath(), true);
                }
                AnalyticsHelper.logEvent(AnalyticsHelper.LedgerPage.CLICK_LEDGER_FLOW_CHANGE_ADDRESS);
            }
        }

        public void lambda$onNoDoubleClick$1(final ObservableEmitter observableEmitter) throws Exception {
            if (fromWhat == 0) {
                observableEmitter.onNext(false);
                observableEmitter.onComplete();
                return;
            }
            ((SelectMnemonicAddressPresenter) mPresenter).addDisposable(((SelectMnemonicAddressPresenter) mPresenter).action.getAllExistsWalletAddress().compose(RxSchedulers2.io_main()).subscribe(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    SelectMnemonicAddressActivity.2.this.lambda$onNoDoubleClick$0(observableEmitter, (Set) obj);
                }
            }, new MigrateActivityExternalSyntheticLambda2()));
        }

        public void lambda$onNoDoubleClick$0(ObservableEmitter observableEmitter, Set set) throws Exception {
            if (addressItemList.get(0) == null) {
                observableEmitter.onNext(false);
            } else {
                observableEmitter.onNext(Boolean.valueOf(WalletChecker.hasImported(set, ((AddressItem) addressItemList.get(0)).getAddress())));
            }
            observableEmitter.onComplete();
        }

        public void lambda$onNoDoubleClick$2(Boolean bool) throws Exception {
            if (bool.booleanValue()) {
                tvError.setText(R.string.error_wallet_already_exist_ledger);
                errorLayout.setVisibility(View.VISIBLE);
                return;
            }
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                extras = new Bundle();
            }
            ((SelectMnemonicAddressPresenter) mPresenter).onClickNext(extras);
        }
    }

    private View.OnClickListener getOnClickListener() {
        return new fun2();
    }

    @Override
    public void showUnCanceledLoading(String str) {
        showLoadingDialog(false, true, str);
    }

    @Override
    public void onLeftButtonClick() {
        finish();
    }

    private void initViews() {
        this.loadFailView.setVisibility(View.GONE);
        this.tvChangeAddress.setVisibility(this.isShield ? View.GONE : View.VISIBLE);
        this.tvChangeAddress.setEnabled(!this.isShield);
        startLoadingAnimation();
        AddressAdapter addressAdapter = new AddressAdapter(this.fromWhat);
        this.adapter = addressAdapter;
        addressAdapter.setOnCheckChangedListener(new AddressAdapter.OnCheckedChangedListener() {
            @Override
            public void onCheckChanged(CompoundButton compoundButton, int i, boolean z) {
                if (i < 0 || i >= adapter.getItemCount()) {
                    return;
                }
                compoundButton.setChecked(true);
                if (z) {
                    adapter.getItem(i);
                    SelectMnemonicAddressActivity.currentWallet = (Wallet) walletList.get(i);
                    if (!currentSelectItems.contains(adapter.getItem(i))) {
                        currentSelectItems.add(adapter.getItem(i));
                    }
                }
                if (currentSelectItems.size() > 0) {
                    btnNext.setEnabled(true);
                } else {
                    btnNext.setEnabled(false);
                }
            }
        });
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getIContext(), 1, false));
        this.recyclerView.setAdapter(this.adapter);
        this.tvChangeAddress.setOnClickListener(getOnClickListener());
        this.btnNext.setOnClickListener(getOnClickListener());
        this.btnRetry.setOnClickListener(getOnClickListener());
    }

    private void startLoadingAnimation() {
        this.ivLoading.setVisibility(View.VISIBLE);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.5f, 1.0f);
        alphaAnimation.setDuration(800L);
        alphaAnimation.setRepeatMode(2);
        alphaAnimation.setRepeatCount(Integer.MAX_VALUE);
        this.ivLoading.startAnimation(alphaAnimation);
    }

    private void initNonHD() {
        String str = this.content;
        if (!StringTronUtil.isEmpty(str)) {
            String[] split = str.split("\\s+");
            if (split != null && split.length == 24) {
                try {
                    String hexString = ByteArray.toHexString(BIP39.decode(str, "pass"));
                    if (!StringTronUtil.isEmpty(hexString)) {
                        Wallet wallet = new Wallet(I_TYPE.PRIVATE, hexString);
                        wallet.getAddress();
                        onCreateWallet(wallet, true);
                        this.isNonHD = true;
                    }
                } catch (ValidationException e) {
                    this.chkNonHD.setChecked(false);
                    this.isNonHD = false;
                    LogUtils.e(e);
                }
            } else {
                this.chkNonHD.setChecked(false);
                this.isNonHD = false;
            }
        } else {
            this.chkNonHD.setChecked(false);
            this.isNonHD = false;
        }
        if (this.isNonHD) {
            return;
        }
        refresh();
    }

    public void refresh() {
        if (this.isNonHD) {
            String str = this.content;
            if (!StringTronUtil.isEmpty(str)) {
                String[] split = str.split("\\s+");
                if (split != null && split.length == 24) {
                    try {
                        String hexString = ByteArray.toHexString(BIP39.decode(str, "pass"));
                        if (StringTronUtil.isEmpty(hexString)) {
                            return;
                        }
                        Wallet wallet = new Wallet(I_TYPE.PRIVATE, hexString);
                        wallet.getAddress();
                        onCreateWallet(wallet, true);
                        this.isNonHD = true;
                        return;
                    } catch (ValidationException e) {
                        ToastAsBottom(R.string.convert6);
                        this.chkNonHD.setChecked(false);
                        this.isNonHD = false;
                        LogUtils.e(e);
                        return;
                    }
                }
                ToastAsBottom(R.string.convert_mnemon_error);
                this.chkNonHD.setChecked(false);
                this.isNonHD = false;
                return;
            }
            ToastAsBottom(R.string.trx_empty);
            this.chkNonHD.setChecked(false);
            this.isNonHD = false;
            return;
        }
        this.loadFailView.setVisibility(View.GONE);
        getHeaderHolder().tvCommonRight.setVisibility(View.GONE);
        if (this.mPresenter != 0) {
            List<AddressItem> list = this.addressItemList;
            if (list == null || list.get(0) == null || this.addressItemList.get(0).getMnemonicPath() == null) {
                ((SelectMnemonicAddressPresenter) this.mPresenter).createWallet(WalletPath.createDefault(), true);
            } else {
                ((SelectMnemonicAddressPresenter) this.mPresenter).createWallet(this.addressItemList.get(0).getMnemonicPath(), true);
            }
        }
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        ArrayList parcelableArrayListExtra;
        super.onActivityResult(i, i2, intent);
        if (i != CommonKey.REQUEST_CODE_CHANGE_ADDRESS || intent == null) {
            return;
        }
        int i3 = 8;
        if (intent.hasExtra("key_data")) {
            Serializable serializableExtra = intent.getSerializableExtra("key_data");
            if (serializableExtra instanceof AddressItem) {
                this.walletList.clear();
                this.addressItemList.clear();
                AddressItem addressItem = (AddressItem) serializableExtra;
                WalletPath mnemonicPath = addressItem.getMnemonicPath();
                Wallet wallet = new Wallet(this.content, mnemonicPath);
                String generateForAccountName = WalletNameGeneratorUtils.generateForAccountName(mnemonicPath.accountIndex);
                int i4 = this.fromWhat;
                if (i4 == 0) {
                    i3 = 5;
                } else if (i4 != 1) {
                    i3 = 1;
                }
                wallet.setCreateType(i3);
                wallet.setWalletName(generateForAccountName);
                wallet.setCreateTime(System.currentTimeMillis());
                wallet.setIconRes(UserIconRandom.THIS.random());
                wallet.setMnemonicPath(JSON.toJSONString(mnemonicPath));
                wallet.setMnemonic(this.content);
                wallet.setBackUp(false);
                wallet.setMnemonicLength(this.content.trim().split("\\s+").length);
                this.addressItemList.add(addressItem);
                this.walletList.add(wallet);
                updateNewItem();
            }
        } else if (intent.hasExtra(CommonKey.KEY_DATA_ARRAY)) {
            if (Build.VERSION.SDK_INT >= 33) {
                parcelableArrayListExtra = intent.getParcelableArrayListExtra(CommonKey.KEY_DATA_ARRAY, AddressItem.class);
                this.addressItemList = parcelableArrayListExtra;
            } else {
                this.addressItemList = intent.getParcelableArrayListExtra(CommonKey.KEY_DATA_ARRAY);
            }
            this.walletList.clear();
            if (this.addressItemList == null) {
                return;
            }
            for (int i5 = 0; i5 < this.addressItemList.size(); i5++) {
                AddressItem addressItem2 = this.addressItemList.get(i5);
                WalletPath mnemonicPath2 = addressItem2.getMnemonicPath();
                Wallet wallet2 = new Wallet(this.content, mnemonicPath2);
                String generateForAccountName2 = WalletNameGeneratorUtils.generateForAccountName(mnemonicPath2.accountIndex);
                wallet2.setAddress(addressItem2.getAddress());
                int i6 = this.fromWhat;
                wallet2.setCreateType(i6 == 0 ? 5 : i6 == 1 ? 8 : 1);
                wallet2.setWalletName(generateForAccountName2);
                wallet2.setCreateTime(System.currentTimeMillis());
                wallet2.setIconRes(UserIconRandom.THIS.random());
                wallet2.setMnemonicPath(JSON.toJSONString(mnemonicPath2));
                wallet2.setMnemonic(this.content);
                wallet2.setBackUp(false);
                wallet2.setMnemonicLength(this.content.trim().split("\\s+").length);
                this.walletList.add(wallet2);
            }
            updateNewItem();
        }
    }

    @Override
    public ArrayList<AddressItem> getAddressItems() {
        if (this.isShield) {
            ArrayList<AddressItem> arrayList = new ArrayList<>();
            arrayList.add(this.addressShieldItem);
            return arrayList;
        }
        return (ArrayList) this.addressItemList;
    }

    @Override
    public ArrayList<Wallet> getWalletLists() {
        return (ArrayList) this.walletList;
    }

    @Override
    public void onResume() {
        super.onResume();
        View view = this.errorLayout;
        if (view != null) {
            view.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroy() {
        dismissLoadingDialog();
        super.onDestroy();
        this.binding = null;
    }
}
