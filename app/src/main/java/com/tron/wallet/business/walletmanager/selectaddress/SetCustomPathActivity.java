package com.tron.wallet.business.walletmanager.selectaddress;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatCheckBox;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.util.XPopupUtils;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.create.creatwallet.AddWalletType;
import com.tron.wallet.business.maintab.MainTabActivity;
import com.tron.wallet.business.vote.util.BaseTextWatcher;
import com.tron.wallet.business.walletmanager.adapter.ChooseAddressActionProvider;
import com.tron.wallet.business.walletmanager.common.AddressItem;
import com.tron.wallet.business.walletmanager.common.CommonKey;
import com.tron.wallet.business.walletmanager.importwallet.rv.AddressItemHolder;
import com.tron.wallet.business.walletmanager.selectaddress.SetCustomPathActivity;
import com.tron.wallet.common.bean.AccountBalanceOutput;
import com.tron.wallet.common.components.MultiLineTextPopupView;
import com.tron.wallet.common.interfaces.MultiImportListener;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.PopupWindowUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.AcSetCustomPathBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.tron.walletserver.Wallet;
import org.tron.walletserver.WalletPath;
public class SetCustomPathActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    private AddressItem addressItem;
    private HashSet<String> addressList;
    View addressView;
    private AddressItemHolder addressViewHolder;
    private AcSetCustomPathBinding binding;
    Button btnNext;
    AppCompatCheckBox cbTag;
    private Wallet currentWallet;
    EditText etAccount;
    EditText etChange;
    EditText etIndex;
    private int fromWhat;
    private Handler handler;
    private boolean importDone;
    private boolean isFirstImport;
    private boolean isOnlyLocalHDWallet;
    private boolean isShielded;
    ImageView ivInfo;
    private String mnemonic;
    private PopupWindow multiImportPop;
    private String nextWalletName;
    private String password;
    private BasePopupView promotingPopupView;
    View root;
    TextView tvPath;
    TextView tvTips;
    private List<String> generateAddressList = new ArrayList();
    private final BaseTextWatcher textWatcher = new fun3();

    public static void start(Context context, Intent intent) {
        if (intent != null) {
            intent.setClass(context, SetCustomPathActivity.class);
            context.startActivity(intent);
        }
    }

    @Deprecated
    public static void start(Context context, String str, int i, int i2) {
        Intent intent = new Intent(context, SetCustomPathActivity.class);
        intent.putExtra(CommonKey.KEY_MNEMONIC, str);
        intent.putExtra("from", i);
        intent.putExtra(CommonKey.REQUEST_CODE_SET_CUSTOM_PATH, i2);
        if (context instanceof Activity) {
            ((Activity) context).startActivityForResult(intent, i2);
        } else {
            context.startActivity(intent);
        }
    }

    @Override
    protected void setLayout() {
        AcSetCustomPathBinding inflate = AcSetCustomPathBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 1);
        setHeaderBar(getString(R.string.set_custom_path));
        mappingId();
    }

    public void mappingId() {
        this.etAccount = this.binding.li1.etAccount;
        this.etChange = this.binding.li1.etChange;
        this.etIndex = this.binding.li1.etIndex;
        this.btnNext = this.binding.btnNext;
        this.addressView = this.binding.addressView.getRoot();
        this.cbTag = this.binding.addressView.ivSelectTag;
        this.tvPath = this.binding.addressView.tvPath;
        this.tvTips = this.binding.tvSetPathTips;
        this.ivInfo = this.binding.ivInfo;
        this.root = this.binding.root;
    }

    @Override
    protected void processData() {
        this.handler = new Handler(Looper.getMainLooper());
        int dip2px = UIUtils.dip2px(10.0f);
        TouchDelegateUtils.expandViewTouchDelegate(this.ivInfo, dip2px, dip2px, dip2px, dip2px);
        Intent intent = getIntent();
        if (intent != null) {
            this.mnemonic = intent.getStringExtra(CommonKey.KEY_MNEMONIC);
            this.fromWhat = intent.getIntExtra("from", 0);
            this.isFirstImport = intent.getBooleanExtra(CommonKey.IS_FIRST_IMPORT, false);
            this.isShielded = intent.getBooleanExtra(AddWalletType.INTENT_KEY_SHIELD, false);
            this.nextWalletName = intent.getStringExtra(CommonKey.KEY_WALLET_NAME);
            this.password = intent.getStringExtra(CommonKey.KEY_WALLET_PASSWORD);
            this.isOnlyLocalHDWallet = intent.getBooleanExtra(CommonKey.IS_ONLY_LOCAL_HDWALLET, false);
        }
        this.etAccount.addTextChangedListener(this.textWatcher);
        this.etChange.addTextChangedListener(this.textWatcher);
        this.etIndex.addTextChangedListener(this.textWatcher);
        this.etAccount.addTextChangedListener(new InputTextWatcher(this.etAccount));
        this.etChange.addTextChangedListener(new InputTextWatcher(this.etChange));
        this.etIndex.addTextChangedListener(new InputTextWatcher(this.etIndex));
        this.btnNext.setOnClickListener(getOnClickListener());
        this.ivInfo.setOnClickListener(getOnClickListener());
        if (this.isFirstImport) {
            this.btnNext.setText(R.string.set_mnemonic_path_normal_confirm);
        } else {
            this.btnNext.setText(R.string.set_mnemonic_path_hd_confirm);
        }
        List<Wallet> allWallets = WalletUtils.getAllWallets();
        this.addressList = new HashSet<>();
        for (int i = 0; i < allWallets.size(); i++) {
            Wallet wallet = allWallets.get(i);
            if (wallet != null) {
                this.addressList.add(wallet.getAddress());
            }
        }
        AnalyticsHelper.logEvent(AnalyticsHelper.ChoosePathPage.ENTER_CUSTOM_PATH_PAGE);
    }

    private View.OnClickListener getOnClickListener() {
        return new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (view.getId() == R.id.iv_info) {
                    showPopupView();
                } else if (view.getId() == R.id.btn_next) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.ChoosePathPage.CLICK_CUSTOM_PATH_PAGE_CONFIRM);
                    onClickNext();
                }
            }
        };
    }

    public void onClickNext() {
        Wallet wallet = this.currentWallet;
        if (wallet == null) {
            return;
        }
        String mnemonicPathString = wallet.getMnemonicPathString();
        if (!TextUtils.isEmpty(mnemonicPathString)) {
            setWalletPath(mnemonicPathString);
        }
        if (this.addressItem == null) {
            this.addressItem = AddressItem.fromWallet(this.currentWallet);
        }
        this.addressItem.setSelected(true);
        if (this.addressItem == null) {
            return;
        }
        final ArrayList arrayList = new ArrayList();
        arrayList.add(this.addressItem);
        try {
            showLoadingDialog(false, true, getString(R.string.start_improt));
            runOnThreeThread(new OnBackground() {
                @Override
                public final void doOnBackground() {
                    lambda$onClickNext$1(arrayList);
                }
            });
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void lambda$onClickNext$1(java.util.ArrayList r17) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.walletmanager.selectaddress.SetCustomPathActivity.lambda$onClickNext$1(java.util.ArrayList):void");
    }

    public void lambda$onClickNext$0() {
        dismissLoadingDialog();
    }

    private void toMain(final ArrayList<Wallet> arrayList, final boolean z) {
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$toMain$2(arrayList, z);
            }
        });
    }

    public void lambda$toMain$2(ArrayList arrayList, boolean z) {
        dismissLoadingDialog();
        WalletUtils.setSelectedWallet(((Wallet) arrayList.get(0)).getWalletName());
        if (SpAPI.THIS.isCold()) {
            SpAPI.THIS.setColdWalletSelected(((Wallet) arrayList.get(0)).getWalletName());
        }
        if (z) {
            showPop();
        } else {
            successNext();
        }
    }

    public void successNext() {
        Intent intent = new Intent(this.mContext, MainTabActivity.class);
        intent.setFlags(67108864);
        go(intent);
    }

    public void showPop() {
        this.importDone = true;
        if (this.multiImportPop == null) {
            this.multiImportPop = PopupWindowUtil.showMultiImport(this, new MultiImportListener() {
                @Override
                public void dismiss() {
                }

                @Override
                public void success() {
                    if (multiImportPop != null) {
                        multiImportPop.dismiss();
                    }
                    successNext();
                }
            });
        }
        this.multiImportPop.showAtLocation(this.root, 17, 0, 0);
    }

    public void showPopupView() {
        new MultiLineTextPopupView.Builder().setAnchor(this.ivInfo).setRequiredWidth(XPopupUtils.getScreenWidth(getIContext()) - (UIUtils.dip2px(15.0f) * 2)).addKeyValue(getString(R.string.mnemonic_tips), "").build(getIContext()).show();
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }

    public class fun3 extends BaseTextWatcher {
        fun3() {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            boolean isEmpty = StringTronUtil.isEmpty(etAccount.getText().toString(), etChange.getText().toString(), etIndex.getText().toString());
            btnNext.setEnabled(!isEmpty);
            if (isEmpty) {
                return;
            }
            handler.post(new Runnable() {
                @Override
                public final void run() {
                    SetCustomPathActivity.3.this.lambda$afterTextChanged$0();
                }
            });
        }

        public void lambda$afterTextChanged$0() {
            onPathInputDone();
        }
    }

    static class InputTextWatcher extends BaseTextWatcher {
        private final EditText host;

        InputTextWatcher(EditText editText) {
            this.host = editText;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (TextUtils.isEmpty(editable.toString())) {
                return;
            }
            String obj = editable.toString();
            if (BigDecimalUtils.MoreThan((Object) obj, (Object) Integer.MAX_VALUE)) {
                String substring = obj.substring(0, obj.length() - 1);
                this.host.setText(substring);
                this.host.setSelection(substring.length());
            }
        }
    }

    public void setWalletPath(String str) {
        try {
            SpAPI.THIS.setCustomWalletPath(str);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void onPathInputDone() {
        WalletPath walletPath = new WalletPath();
        try {
            walletPath.setAccount(Integer.parseInt(this.etAccount.getText().toString().trim()));
            walletPath.setChange(Integer.parseInt(this.etChange.getText().toString().trim()));
            walletPath.setAccountIndex(Integer.parseInt(this.etIndex.getText().toString().trim()));
        } catch (Exception e) {
            LogUtils.e(e);
        }
        Wallet wallet = new Wallet(this.mnemonic, walletPath);
        this.currentWallet = wallet;
        updateAddressView(wallet, -1.0d);
        requestBalance(this.currentWallet);
    }

    private void requestBalance(Wallet wallet) {
        ChooseAddressActionProvider.provide(0).getBalance(wallet.getAddress()).subscribe(new IObserver(new ICallback<AccountBalanceOutput>() {
            @Override
            public void onFailure(String str, String str2) {
            }

            @Override
            public void onSubscribe(Disposable disposable) {
            }

            @Override
            public void onResponse(String str, AccountBalanceOutput accountBalanceOutput) {
                if (isDestroyed() || accountBalanceOutput == null || accountBalanceOutput.getData() == null || accountBalanceOutput.getData().getBalanceList().isEmpty()) {
                    return;
                }
                updateBalance(accountBalanceOutput);
            }
        }, "SetPathFragment-getBalance"));
    }

    public void updateBalance(AccountBalanceOutput accountBalanceOutput) {
        AccountBalanceOutput.DataBean.BalanceListBean balanceListBean = accountBalanceOutput.getData().getBalanceList().get(0);
        if (TextUtils.equals(balanceListBean.getAddress(), this.currentWallet.getAddress())) {
            updateAddressView(this.currentWallet, balanceListBean.getBalance());
        }
    }

    private void updateAddressView(Wallet wallet, double d) {
        this.addressView.setVisibility(View.VISIBLE);
        if (this.addressViewHolder == null) {
            this.addressViewHolder = new AddressItemHolder(this.addressView);
        }
        AddressItem fromWallet = AddressItem.fromWallet(wallet);
        this.addressItem = fromWallet;
        fromWallet.setBalance(d);
        this.addressItem.setSelected(true);
        if (this.addressList.contains(wallet.getAddress())) {
            this.addressItem.setExisted(true);
        }
        this.addressViewHolder.onBind(this.addressItem);
        if (this.addressItem.isExisted()) {
            this.btnNext.setEnabled(false);
        } else {
            this.cbTag.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    lambda$updateAddressView$3(compoundButton, z);
                }
            });
        }
    }

    public void lambda$updateAddressView$3(CompoundButton compoundButton, boolean z) {
        this.btnNext.setEnabled(z);
    }

    @Override
    public void onDestroy() {
        dismissLoadingDialog();
        this.binding = null;
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (this.importDone) {
            successNext();
        } else {
            super.onBackPressed();
        }
    }
}
