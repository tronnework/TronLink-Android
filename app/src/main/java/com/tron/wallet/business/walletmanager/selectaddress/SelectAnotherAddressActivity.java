package com.tron.wallet.business.walletmanager.selectaddress;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.core.ledger.LedgerErrorToast;
import com.tron.wallet.business.create.creatwallet.AddWalletType;
import com.tron.wallet.business.reminder.RiskWarning;
import com.tron.wallet.business.walletmanager.adapter.ChooseAddressActionProvider;
import com.tron.wallet.business.walletmanager.common.AddressItem;
import com.tron.wallet.business.walletmanager.common.CommonKey;
import com.tron.wallet.business.walletmanager.importwallet.base.SelectAddressActivity;
import com.tron.wallet.business.walletmanager.importwallet.rv.AddressAdapter;
import com.tron.wallet.business.walletmanager.selectaddress.SelectAddressContract;
import com.tron.wallet.common.bean.AccountBalanceOutput;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.AcSelectAnotherAddressBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.tron.walletserver.Wallet;
import org.tron.walletserver.WalletPath;
public class SelectAnotherAddressActivity extends BaseActivity<SelectAddressPresenter, SelectAddressModel> implements SelectAddressContract.View {
    public static final int MAX_COUNT = 100;
    private static final int SETCUSTOMPATH = 17;
    private AddressAdapter adapter;
    private AcSelectAnotherAddressBinding binding;
    Button btnNext;
    private Button btnRetry;
    private AddressItem currentSelectItem;
    private ArrayList<AddressItem> currentSelectItems;
    private Wallet currentWallet;
    private int fromWhat;
    private boolean isFirstImport;
    private boolean isShielded;
    private boolean isTopOneSelected;
    private ImageView ivCloseSelect;
    ImageView ivPlaceHolder;
    View loadFailView;
    private String mnemonic;
    private String nextWalletName;
    private String password;
    RecyclerView rvList;
    TextView tvFailTips;
    TextView tvSetCustomPath;
    TextView tvTitle;
    View viewContent;
    private int start = 0;
    private int limit = 10;
    private boolean loadMoreDone = false;
    private final List<Wallet> cachedWallets = new ArrayList();
    private boolean isOnlyLocalHDWallet = false;

    @Override
    public View getRootView() {
        return this.viewContent;
    }

    public static void start(Context context, String str, ArrayList<AddressItem> arrayList, boolean z, boolean z2, int i, int i2, boolean z3, String str2, String str3, boolean z4) {
        Intent intent = new Intent(context, SelectAnotherAddressActivity.class);
        intent.putExtra(CommonKey.KEY_MNEMONIC, str);
        intent.putExtra(CommonKey.KEY_DATA_ARRAY, arrayList);
        intent.putExtra(CommonKey.KEY_SELECTED, z);
        intent.putExtra(CommonKey.KEY_WALLET_NAME, str2);
        intent.putExtra(AddWalletType.INTENT_KEY_SHIELD, z2);
        intent.putExtra("from", i2);
        intent.putExtra(CommonKey.IS_FIRST_IMPORT, z3);
        intent.putExtra(CommonKey.KEY_WALLET_PASSWORD, str3);
        intent.putExtra(CommonKey.IS_ONLY_LOCAL_HDWALLET, z4);
        if (context instanceof Activity) {
            ((Activity) context).startActivityForResult(intent, i);
        } else {
            context.startActivity(intent);
        }
    }

    @Override
    public void onCreate1(Bundle bundle) {
        super.onCreate1(bundle);
        if (Build.VERSION.SDK_INT == 26 || Build.VERSION.SDK_INT == 27) {
            setRequestedOrientation(2);
        } else {
            setRequestedOrientation(1);
        }
    }

    @Override
    protected void setLayout() {
        AcSelectAnotherAddressBinding inflate = AcSelectAnotherAddressBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 0);
        mappingId();
    }

    public void mappingId() {
        this.rvList = this.binding.rvList;
        this.btnNext = this.binding.btnNext;
        this.ivPlaceHolder = this.binding.ivPlaceHolder;
        this.tvSetCustomPath = this.binding.tvSelectSetPath;
        this.loadFailView = this.binding.viewLoadFail.getRoot();
        this.tvFailTips = this.binding.viewLoadFail.tvTip;
        this.tvTitle = this.binding.tvTitle;
        this.viewContent = this.binding.viewContent;
        this.ivCloseSelect = this.binding.ivCloseSelect;
        this.btnRetry = this.binding.viewLoadFail.btnRetry;
    }

    private void setTilte(String str) {
        this.tvTitle.setText(str);
    }

    @Override
    protected void processData() {
        parseIntentArgs();
        WalletUtils.getAllWallets();
        ArrayList parcelableArrayListExtra = getIntent().getParcelableArrayListExtra(CommonKey.KEY_DATA_ARRAY);
        ArrayList<AddressItem> arrayList = new ArrayList<>();
        this.currentSelectItems = arrayList;
        arrayList.addAll(parcelableArrayListExtra);
        initViews();
        ((SelectAddressPresenter) this.mPresenter).setLoadAddressAction(ChooseAddressActionProvider.provide(this.fromWhat));
        ((SelectAddressPresenter) this.mPresenter).deriveAddress(this.mnemonic, this.start, this.limit);
        AnalyticsHelper.logEvent(AnalyticsHelper.ChoosePathPage.ENTER_CHOOSE_ADDRESS_PAGE);
    }

    private void parseIntentArgs() {
        Intent intent = getIntent();
        if (intent != null) {
            this.mnemonic = intent.getStringExtra(CommonKey.KEY_MNEMONIC);
            this.isTopOneSelected = intent.getBooleanExtra(CommonKey.KEY_SELECTED, true);
            this.fromWhat = intent.getIntExtra("from", 0);
            this.isFirstImport = intent.getBooleanExtra(CommonKey.IS_FIRST_IMPORT, false);
            this.isShielded = intent.getBooleanExtra(AddWalletType.INTENT_KEY_SHIELD, false);
            this.nextWalletName = intent.getStringExtra(CommonKey.KEY_WALLET_NAME);
            this.password = intent.getStringExtra(CommonKey.KEY_WALLET_PASSWORD);
            this.isOnlyLocalHDWallet = intent.getBooleanExtra(CommonKey.IS_ONLY_LOCAL_HDWALLET, false);
        }
        this.limit = this.fromWhat == 1 ? 6 : 10;
    }

    public void showNoticeDialog() {
        RiskWarning.showBackupPopup(this, new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (getIntent() != null) {
                    SelectAnotherAddressActivity selectAnotherAddressActivity = SelectAnotherAddressActivity.this;
                    SetCustomPathActivity.start(selectAnotherAddressActivity, selectAnotherAddressActivity.getIntent());
                }
            }
        });
    }

    public void onClickNext() {
        ArrayList<AddressItem> arrayList = this.currentSelectItems;
        if (arrayList == null || arrayList.size() == 0 || this.mPresenter == 0) {
            return;
        }
        ArrayList<Wallet> arrayList2 = new ArrayList<>();
        if (this.fromWhat == 1) {
            ((SelectAddressPresenter) this.mPresenter).onAddressSelected(this.currentSelectItems, this.currentWallet);
        } else {
            ((SelectAddressPresenter) this.mPresenter).createWallets(this.currentSelectItems, arrayList2, this.nextWalletName, this.password, this.isShielded, this.isOnlyLocalHDWallet, this.isFirstImport);
        }
    }

    @Override
    public void onAddressCreated(Set<String> set, List<? extends Wallet> list) {
        LogUtils.d("CreateAccount", "onAddressCreated");
        this.loadFailView.setVisibility(View.GONE);
        this.ivPlaceHolder.setVisibility(View.GONE);
        List<AddressItem> data = this.adapter.getData();
        if (data.isEmpty()) {
            for (int i = 0; i < this.currentSelectItems.size(); i++) {
                if (!data.contains(this.currentSelectItems.get(i))) {
                    data.add(this.currentSelectItems.get(i));
                }
            }
        } else {
            for (int i2 = 0; i2 < data.size(); i2++) {
                if (set.contains(data.get(i2).getAddress())) {
                    data.get(i2).setExisted(true);
                }
            }
        }
        LogUtils.d("CreateAccount", "currentSelectItems.size " + this.currentSelectItems.size());
        LogUtils.d("CreateAccount", "adapterData.size " + data.size());
        LogUtils.d("CreateAccount", "created.size " + list.size());
        for (int i3 = 0; i3 < list.size(); i3++) {
            Wallet wallet = list.get(i3);
            LogUtils.d("CreateAccount", "created.get " + wallet.getAddress());
            AddressItem fromWallet = AddressItem.fromWallet(wallet);
            boolean z = false;
            for (int i4 = 0; i4 < data.size(); i4++) {
                if (StringTronUtil.isEmpty(fromWallet.getAddress()) || data.get(i4).getAddress().equals(fromWallet.getAddress())) {
                    z = true;
                }
            }
            boolean z2 = set != null && set.contains(fromWallet.getAddress()) && this.fromWhat == 1;
            fromWallet.setHasImported(z2);
            fromWallet.setExisted(z2);
            if (set.contains(fromWallet.getAddress())) {
                fromWallet.setExisted(true);
            }
            if (!z) {
                LogUtils.d("CreateAccount", "add addressItem: " + fromWallet.getAddress());
                data.add(fromWallet);
            }
        }
        LogUtils.d("CreateAccount", "adapter addData");
        if (this.adapter.getData().isEmpty()) {
            this.adapter.setNewData(data);
            this.cachedWallets.clear();
        } else {
            this.adapter.setNewData(data);
        }
        this.cachedWallets.addAll(list);
        boolean z3 = false;
        for (int i5 = 0; i5 < data.size(); i5++) {
            if (data.get(i5).isSelected()) {
                z3 = true;
            }
        }
        if (z3) {
            this.btnNext.setEnabled(true);
        } else {
            this.btnNext.setEnabled(false);
        }
        int i6 = this.start + this.limit;
        this.start = i6;
        boolean z4 = i6 >= 99;
        this.loadMoreDone = z4;
        if (z4) {
            this.adapter.loadMoreEnd(false);
        } else {
            this.adapter.loadMoreComplete();
        }
    }

    @Override
    public void onGetBalances(AccountBalanceOutput accountBalanceOutput) {
        List<AccountBalanceOutput.DataBean.BalanceListBean> balanceList;
        if (this.adapter == null || accountBalanceOutput == null || accountBalanceOutput.getData() == null || (balanceList = accountBalanceOutput.getData().getBalanceList()) == null) {
            return;
        }
        List<AddressItem> data = this.adapter.getData();
        for (AddressItem addressItem : data) {
            Iterator<AccountBalanceOutput.DataBean.BalanceListBean> it = balanceList.iterator();
            while (true) {
                if (it.hasNext()) {
                    AccountBalanceOutput.DataBean.BalanceListBean next = it.next();
                    if (TextUtils.equals(addressItem.getAddress(), next.getAddress())) {
                        addressItem.setBalance(next.getBalance());
                        break;
                    }
                }
            }
        }
        this.adapter.setNewData(data);
    }

    @Override
    public WalletPath getArgumentPath() {
        if (getIntent() == null) {
            return null;
        }
        Serializable serializableExtra = getIntent().getSerializableExtra(TronConfig.IMPORT_MNEMONIC_PATH);
        if (serializableExtra instanceof WalletPath) {
            return (WalletPath) serializableExtra;
        }
        return null;
    }

    @Override
    public void onAddressCreateFail(Throwable th) {
        boolean z = this.fromWhat == 1;
        AddressAdapter addressAdapter = this.adapter;
        if (addressAdapter != null && !addressAdapter.getData().isEmpty()) {
            this.adapter.loadMoreFail();
            LedgerErrorToast.getInstance(false).Toast(getIContext(), th);
        } else if (z) {
            this.loadFailView.setVisibility(View.VISIBLE);
            this.tvFailTips.setText(String.format(getString(R.string.unable_find_ledger), SelectAddressActivity.ledgerName));
        }
    }

    private void initViews() {
        if (this.isFirstImport) {
            this.btnNext.setText(R.string.set_mnemonic_path_normal_confirm);
            this.tvTitle.setText(R.string.change_wallet_address);
        } else if (this.fromWhat != 1) {
            this.tvTitle.setText(R.string.change_account_title);
            this.btnNext.setText(R.string.set_mnemonic_path_hd_confirm);
        }
        this.tvSetCustomPath.setVisibility(this.fromWhat == 1 ? View.GONE : View.VISIBLE);
        this.adapter = new AddressAdapter(this.fromWhat);
        this.rvList.setLayoutManager(new WrapContentLinearLayoutManager(getIContext(), 1, false));
        this.rvList.setAdapter(this.adapter);
        this.adapter.setOnCheckChangedListener(new AddressAdapter.OnCheckedChangedListener() {
            @Override
            public final void onCheckChanged(CompoundButton compoundButton, int i, boolean z) {
                lambda$initViews$0(compoundButton, i, z);
            }
        });
        this.adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public final void onLoadMoreRequested() {
                lambda$initViews$1();
            }
        }, this.rvList);
        this.tvSetCustomPath.setOnClickListener(getOnClickListener());
        this.btnNext.setOnClickListener(getOnClickListener());
        this.ivCloseSelect.setOnClickListener(getOnClickListener());
        this.btnRetry.setOnClickListener(getOnClickListener());
    }

    public void lambda$initViews$0(CompoundButton compoundButton, int i, boolean z) {
        ArrayList<AddressItem> arrayList;
        if (i < 0 || i >= this.adapter.getItemCount()) {
            return;
        }
        if (this.fromWhat == 1) {
            this.currentSelectItems.clear();
            this.isTopOneSelected = i == 0 && z;
            this.btnNext.setEnabled(z);
            for (int i2 = 0; i2 < this.adapter.getData().size(); i2++) {
                this.adapter.getItem(i2).setSelected(false);
            }
            this.adapter.getItem(i).setSelected(z);
            if (z) {
                this.currentSelectItem = this.adapter.getItem(i);
                this.currentWallet = this.cachedWallets.get(i);
                this.currentSelectItems.add(this.adapter.getItem(i));
            } else {
                this.currentSelectItem = null;
                this.currentSelectItems.clear();
            }
            this.adapter.notifyDataSetChanged();
        } else {
            ArrayList<AddressItem> arrayList2 = this.currentSelectItems;
            if (arrayList2 != null && arrayList2.size() <= 1 && !z) {
                return;
            }
            if (z && (arrayList = this.currentSelectItems) != null && arrayList.size() >= 10) {
                compoundButton.setChecked(false);
                if (this.isFirstImport) {
                    toast(getString(R.string.import_maximum_wallets));
                    return;
                } else {
                    toast(getString(R.string.generate_maximum_wallets));
                    return;
                }
            }
            AnalyticsHelper.logEvent(AnalyticsHelper.ChoosePathPage.CLICK_CHOOSE_ADDRESS_PAGE_SELECT);
            this.isTopOneSelected = i == 0 && z;
            this.adapter.getItem(i).setSelected(z);
            if (z) {
                this.currentSelectItem = this.adapter.getItem(i);
                this.currentWallet = this.cachedWallets.get(i);
                if (!this.currentSelectItems.contains(this.adapter.getItem(i))) {
                    this.currentSelectItems.add(this.adapter.getItem(i));
                }
            } else {
                this.currentSelectItem = null;
                if (this.currentSelectItems.contains(this.adapter.getItem(i))) {
                    this.currentSelectItems.remove(this.adapter.getItem(i));
                }
            }
        }
        if (this.currentSelectItems.size() > 0) {
            this.btnNext.setEnabled(true);
        } else {
            this.btnNext.setEnabled(false);
        }
    }

    public void lambda$initViews$1() {
        if (this.loadMoreDone) {
            this.adapter.loadMoreEnd(false);
            return;
        }
        int i = this.start;
        if (this.limit + i > 100) {
            this.limit = 100 - i;
        }
        ((SelectAddressPresenter) this.mPresenter).deriveAddress(this.mnemonic, this.start, this.limit);
    }

    private View.OnClickListener getOnClickListener() {
        return new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (view.getId() == R.id.tv_select_set_path) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.ChoosePathPage.CLICK_CHOOSE_ADDRESS_PAGE_SET_CUSTOM_PATH);
                    showNoticeDialog();
                } else if (view.getId() == R.id.btn_next) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.ChoosePathPage.CLICK_CHOOSE_ADDRESS_PAGE_CONFIRM);
                    LogUtils.d("CreateAccount", "btn_next: " + currentSelectItems.size());
                    onClickNext();
                } else if (view.getId() == R.id.iv_close_select) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.ChoosePathPage.CLICK_CHOOSE_ADDRESS_PAGE_CLOSE);
                    finish();
                } else if (view.getId() != R.id.btn_retry || mPresenter == 0 || TextUtils.isEmpty(mnemonic)) {
                } else {
                    loadFailView.setVisibility(View.GONE);
                    ((SelectAddressPresenter) mPresenter).deriveAddress(mnemonic, start, limit);
                }
            }
        };
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 17) {
            setResult(-1, intent);
            finish();
        }
    }

    @Override
    public void showUnCancelableLoadingDialog() {
        showLoadingDialog(false, true, getString(R.string.start_improt));
    }

    @Override
    public void onDestroy() {
        dismissLoadingDialog();
        this.binding = null;
        super.onDestroy();
    }
}
