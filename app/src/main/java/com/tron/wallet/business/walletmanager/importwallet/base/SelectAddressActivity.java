package com.tron.wallet.business.walletmanager.importwallet.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatCheckBox;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.create.creatwallet.AddWalletType;
import com.tron.wallet.business.migrate.MigrateActivityExternalSyntheticLambda2;
import com.tron.wallet.business.vote.util.BaseTextWatcher;
import com.tron.wallet.business.walletmanager.adapter.ChooseAddressActionProvider;
import com.tron.wallet.business.walletmanager.adapter.LoadAddressAction;
import com.tron.wallet.business.walletmanager.common.AddressItem;
import com.tron.wallet.business.walletmanager.common.CommonKey;
import com.tron.wallet.business.walletmanager.common.ImportType;
import com.tron.wallet.business.walletmanager.importwallet.base.SelectAddressActivity;
import com.tron.wallet.business.walletmanager.importwallet.base.SelectAddressContract;
import com.tron.wallet.business.walletmanager.importwallet.rv.AddressItemHolder;
import com.tron.wallet.common.bean.AccountBalanceOutput;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.common.utils.WalletChecker;
import com.tron.wallet.common.utils.WalletNameGeneratorUtils;
import com.tron.wallet.databinding.ActivityChooseAddressBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import java.io.Serializable;
import java.util.Set;
import org.tron.walletserver.Wallet;
import org.tron.walletserver.WalletPath;
public class SelectAddressActivity extends BaseActivity<SelectAddressPresenter, EmptyModel> implements SelectAddressContract.View {
    public static Wallet currentWallet = null;
    public static String ledgerName = "";
    private AddressItem addressItem;
    View addressView;
    private AddressItemHolder addressViewHolder;
    private ActivityChooseAddressBinding binding;
    Button btnNext;
    private Button btnRetry;
    AppCompatCheckBox ckTag;
    View errorLayout;
    EditText etName;
    private int fromWhat;
    protected boolean isShield;
    View ivLoading;
    CompoundButton.OnCheckedChangeListener l = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            lambda$new$0(compoundButton, z);
        }
    };
    View loadFailView;
    TextView tvAddress;
    TextView tvBalance;
    TextView tvChangeAddress;
    TextView tvError;
    TextView tvFailTips;
    TextView tvPath;

    private void mappingId() {
        this.tvAddress = this.binding.addressItemContainer.tvAddress;
        this.tvBalance = this.binding.addressItemContainer.tvBalance;
        this.etName = this.binding.etName;
        this.btnNext = this.binding.btnNext;
        this.errorLayout = this.binding.errorLayout.getRoot();
        this.tvError = this.binding.errorLayout.tvError;
        this.ckTag = this.binding.addressItemContainer.ivSelectTag;
        this.tvPath = this.binding.addressItemContainer.tvPath;
        this.tvChangeAddress = this.binding.tvChangeAddress;
        this.addressView = this.binding.addressItemContainer.getRoot();
        this.ivLoading = this.binding.ivLoading;
        this.loadFailView = this.binding.viewLoadFail.getRoot();
        this.tvFailTips = this.binding.viewLoadFail.tvTip;
        this.btnRetry = this.binding.viewLoadFail.btnRetry;
    }

    public void lambda$new$0(CompoundButton compoundButton, boolean z) {
        boolean z2 = false;
        if (!z) {
            this.btnNext.setEnabled(false);
            return;
        }
        if (this.errorLayout.getVisibility() == 8 && !TextUtils.isEmpty(this.etName.getText().toString())) {
            z2 = true;
        }
        this.btnNext.setEnabled(z2);
    }

    public static void start(Context context, int i, String str, boolean z) {
        context.startActivity(getBaseIntent(context, i, str, z));
    }

    public static void startForLedger(Context context, String str, String str2, boolean z) {
        Intent baseIntent = getBaseIntent(context, 8, str, false);
        baseIntent.putExtra(ImportType.KEY_SHOW_STEP, z);
        baseIntent.putExtra(ImportType.KEY_LEDGER_NAME, str2);
        context.startActivity(baseIntent);
    }

    public static Intent getBaseIntent(Context context, int i, String str, boolean z) {
        Intent intent = new Intent(context, SelectAddressActivity.class);
        intent.putExtra(TronConfig.WALLET_TYPE, i);
        intent.putExtra(TronConfig.IMPORT_CONTENT, str);
        intent.putExtra(AddWalletType.INTENT_KEY_SHIELD, z);
        intent.putExtra("from", ImportType.parseFromTronConfig(i));
        return intent;
    }

    @Override
    protected void setLayout() {
        ActivityChooseAddressBinding inflate = ActivityChooseAddressBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 1);
        getHeaderHolder().tvCommonTitle.setTextSize(22.0f);
        mappingId();
    }

    @Override
    protected void processData() {
        initViews();
        int intExtra = getIntent().getIntExtra("from", 0);
        this.fromWhat = intExtra;
        if (intExtra == 1) {
            ledgerName = getIntent().getStringExtra(ImportType.KEY_LEDGER_NAME);
            setupIntroView();
            AnalyticsHelper.logEvent(AnalyticsHelper.LedgerPage.ENTER_LEDGER_FLOW_IMPORT_ADDRESS);
        }
        LoadAddressAction<? extends Wallet> provide = ChooseAddressActionProvider.provide(this.fromWhat);
        ((SelectAddressPresenter) this.mPresenter).setLoadAddressAction(provide);
        provide.adaptUI(this);
        refresh();
    }

    private void setupIntroView() {
        try {
            getHeaderHolder().tvCommonRight.setText(R.string.tutorial);
            getHeaderHolder().tvCommonRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    lambda$setupIntroView$1(view);
                }
            });
            getHeaderHolder().tvCommonRight.setTextColor(getResources().getColor(R.color.blue_13));
            getHeaderHolder().tvCommonRight.setVisibility(View.GONE);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void lambda$setupIntroView$1(View view) {
        UIUtils.toHowUseLedger(this);
        AnalyticsHelper.logEvent(AnalyticsHelper.LedgerPage.CLICK_LEDGER_FLOW_TUTORIAL);
    }

    @Override
    public void onCreateWallet(Wallet wallet, boolean z) {
        this.addressView.setVisibility(View.VISIBLE);
        if (this.ivLoading.getAnimation() != null) {
            this.ivLoading.getAnimation().cancel();
        }
        this.ivLoading.setVisibility(View.GONE);
        this.addressItem = AddressItem.fromWallet(wallet);
        if (SpAPI.THIS.getAllWallets().contains(wallet.getAddress())) {
            this.addressItem.setExisted(true);
        }
        updateNewItem(this.addressItem, z);
        this.loadFailView.setVisibility(View.GONE);
        this.btnNext.setEnabled(true);
    }

    private void updateNewItem(AddressItem addressItem, boolean z) {
        addressItem.setSelected(z);
        if (SpAPI.THIS.getAllWallets().contains(addressItem.getAddress())) {
            addressItem.setExisted(true);
        }
        AddressItemHolder addressItemHolder = this.addressViewHolder;
        if (addressItemHolder != null) {
            addressItemHolder.onBind(addressItem);
            this.addressViewHolder.cbCheck.setOnCheckedChangeListener(this.l);
            this.addressViewHolder.cbCheck.setChecked(z);
        }
        ((SelectAddressPresenter) this.mPresenter).getBalance(addressItem.getAddress());
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
        double balance = accountBalanceOutput.getData().getBalanceList().get(0).getBalance();
        AddressItem addressItem = this.addressItem;
        if (addressItem != null) {
            addressItem.setBalance(balance);
            AddressItemHolder addressItemHolder = this.addressViewHolder;
            if (addressItemHolder != null) {
                addressItemHolder.onBind(this.addressItem);
                this.addressViewHolder.cbCheck.setOnCheckedChangeListener(this.l);
            }
        }
    }

    @Override
    public Intent getViewIntent() {
        return getIntent();
    }

    public class fun1 extends NoDoubleClickListener2 {
        fun1() {
        }

        @Override
        protected void onNoDoubleClick(View view) {
            int id = view.getId();
            if (id == R.id.btn_next) {
                final String trim = etName.getText().toString().trim();
                if (!WalletChecker.isCharValid(trim)) {
                    tvError.setText(R.string.error_name2);
                    errorLayout.setVisibility(View.VISIBLE);
                    return;
                }
                ((SelectAddressPresenter) mPresenter).addDisposable(Observable.create(new ObservableOnSubscribe() {
                    @Override
                    public final void subscribe(ObservableEmitter observableEmitter) {
                        SelectAddressActivity.1.this.lambda$onNoDoubleClick$1(observableEmitter);
                    }
                }).subscribe(new Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        SelectAddressActivity.1.this.lambda$onNoDoubleClick$2(trim, (Boolean) obj);
                    }
                }));
            } else if (id == R.id.btn_retry) {
                refresh();
            } else if (id != R.id.tv_change_address) {
            } else {
                if (addressItem != null) {
                    ((SelectAddressPresenter) mPresenter).onClickAddress(addressItem.getMnemonicPath(), addressItem, ckTag.isChecked());
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
            ((SelectAddressPresenter) mPresenter).addDisposable(((SelectAddressPresenter) mPresenter).action.getAllExistsWalletAddress().compose(RxSchedulers2.io_main()).subscribe(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    SelectAddressActivity.1.this.lambda$onNoDoubleClick$0(observableEmitter, (Set) obj);
                }
            }, new MigrateActivityExternalSyntheticLambda2()));
        }

        public void lambda$onNoDoubleClick$0(ObservableEmitter observableEmitter, Set set) throws Exception {
            if (addressItem == null) {
                observableEmitter.onNext(false);
            } else {
                observableEmitter.onNext(Boolean.valueOf(WalletChecker.hasImported(set, addressItem.getAddress())));
            }
            observableEmitter.onComplete();
        }

        public void lambda$onNoDoubleClick$2(String str, Boolean bool) throws Exception {
            if (bool.booleanValue()) {
                tvError.setText(R.string.error_wallet_already_exist_ledger);
                errorLayout.setVisibility(View.VISIBLE);
                return;
            }
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                extras = new Bundle();
            }
            extras.putString(TronConfig.IMPORT_NAME, str);
            ((SelectAddressPresenter) mPresenter).onClickNext(extras);
        }
    }

    private View.OnClickListener getOnClickListener() {
        return new fun1();
    }

    @Override
    public void onLeftButtonClick() {
        finish();
    }

    private void initViews() {
        this.isShield = getIntent().getBooleanExtra(AddWalletType.INTENT_KEY_SHIELD, false);
        this.addressView.setVisibility(View.GONE);
        this.loadFailView.setVisibility(View.GONE);
        this.tvChangeAddress.setVisibility(this.isShield ? View.GONE : View.VISIBLE);
        this.tvChangeAddress.setEnabled(!this.isShield);
        startLoadingAnimation();
        this.addressViewHolder = new AddressItemHolder(this.addressView);
        this.etName.addTextChangedListener(new BaseTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                errorLayout.setVisibility(View.GONE);
                String trim = WalletChecker.truncateIfNeed(editable).toString().trim();
                boolean isChecked = (!TextUtils.isEmpty(trim)) & ckTag.isChecked();
                if (WalletChecker.duplicateName(trim)) {
                    tvError.setText(R.string.error_existwallet);
                    isChecked = false;
                    errorLayout.setVisibility(View.VISIBLE);
                }
                btnNext.setEnabled(isChecked);
            }
        });
        this.etName.setText(WalletNameGeneratorUtils.generateWalletName(8, false));
        this.btnNext.setEnabled(false);
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

    public void refresh() {
        this.loadFailView.setVisibility(View.GONE);
        getHeaderHolder().tvCommonRight.setVisibility(View.GONE);
        if (this.mPresenter != 0) {
            ((SelectAddressPresenter) this.mPresenter).createWallet(WalletPath.createDefault(), true);
        }
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == CommonKey.REQUEST_CODE_CHANGE_ADDRESS && intent != null) {
            Serializable serializableExtra = intent.getSerializableExtra("key_data");
            if (serializableExtra instanceof AddressItem) {
                AddressItem addressItem = (AddressItem) serializableExtra;
                this.addressItem = addressItem;
                if (currentWallet != null) {
                    updateNewItem(addressItem, addressItem.isSelected());
                    if (this.mPresenter != 0) {
                        ((SelectAddressPresenter) this.mPresenter).mWallet = currentWallet;
                    }
                } else if (this.mPresenter != 0) {
                    ((SelectAddressPresenter) this.mPresenter).createWallet(this.addressItem.getMnemonicPath(), this.addressItem.isSelected());
                }
            }
        }
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
        super.onDestroy();
        this.binding = null;
    }
}
