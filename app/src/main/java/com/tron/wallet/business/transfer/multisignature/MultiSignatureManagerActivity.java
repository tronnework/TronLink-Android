package com.tron.wallet.business.transfer.multisignature;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewpager2.widget.ViewPager2;
import com.arasthel.asyncjob.AsyncJob;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.tron_base.frame.utils.StatusBarUtils;
import com.tron.tron_base.frame.utils.ThreadPoolManager;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.parambuilder.utils.ParamBuildUtils;
import com.tron.wallet.business.permission.DoPermissionActivity;
import com.tron.wallet.business.tabmy.myhome.addressbook.AddressBookActivity;
import com.tron.wallet.common.bean.PermissionGroupBean;
import com.tron.wallet.common.components.ErrorEdiTextLayout;
import com.tron.wallet.common.components.TrimEditText;
import com.tron.wallet.common.components.dialog.CustomDialog;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.ResultCodeContant;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.AssetsRawUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.SamsungMultisignUtils;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.ToastUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.AcMultiSignatureBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.LedgerWallet;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.functions.Consumer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.walletserver.ConnectErrorException;
import org.tron.walletserver.PermissionException;
import org.tron.walletserver.Wallet;
public class MultiSignatureManagerActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    public static final String INTENT_ALL_PERMISSIONS = "INTENT_ALL_PERMISSIONS";
    public static final String INTENT_ALL_PERMISSION_GROUPS = "INTENT_ALL_PERMISSIONGROUPS";
    public static final int PERMISSION_MAX_LENGTH = 9;
    private static final String TAG = "MultiSignatureManagerAc";
    private byte[] address;
    RelativeLayout backview;
    RelativeLayout backview2;
    AcMultiSignatureBinding binding;
    View bottomLine;
    View bottomLine2;
    Button btConfirm;
    ErrorEdiTextLayout eetSelectAddress;
    TrimEditText etSelectAddress;
    private boolean flag2;
    ImageView ivCommonLeft;
    ImageView ivCommonLeft2;
    ImageView ivEdit;
    ImageView ivMore;
    ImageView ivOneDelete;
    ImageView ivQr;
    ImageView ivQr2;
    LinearLayout llCommonLeft;
    LinearLayout llCommonLeft2;
    LinearLayout llEdit;
    LinearLayout llEmpty;
    LinearLayout llIndicator;
    LinearLayout llMore;
    private Protocol.Account mAccount;
    private List<Protocol.Permission> mAllPermissionList;
    TextView mEmptyAddPermissionsTv;
    TextView mEmptyDescTv;
    private boolean mPermissionIsEmpty;
    private PopupWindow popupWindow;
    private PopupWindow popupWindow2;
    RelativeLayout rlAddressBook;
    RelativeLayout rlBack;
    RelativeLayout rlBackTwo;
    LinearLayout rlMiddle;
    RelativeLayout rlRight;
    RelativeLayout rlRight2;
    RelativeLayout rlSelect;
    RelativeLayout rlUc;
    private RxManager rxManager;
    private Wallet selectedWallet;
    LinearLayout statusbar;
    LinearLayout statusbar2;
    TextView tvAdd;
    TextView tvCommonTitle;
    TextView tvCommonTitle2;
    TextView tvPermissionName;
    TextView tvSa;
    TextView tvUc;
    TextView tvUcAddress;
    View vLine;
    ViewPager2 viewpager;
    LinearLayout viewpagerLayout;
    private String walletName;
    public int mWitnessSize = 0;
    private List<MsManagerFragment> fragments = new ArrayList();
    private int mCurIndex = 0;
    private int intExtra = -1;
    private boolean isSelect = true;
    private boolean havePermission = false;
    private ArrayList<PermissionGroupBean.PermissionBean> mAllPermissionArray = new ArrayList<>();
    private ArrayList<PermissionGroupBean> mAllPermissionGroupArray = new ArrayList<>();
    MultiSignatureManagerAdapter mViewPagerAdapter = new MultiSignatureManagerAdapter(this);

    int getPermissionType() {
        int i = this.mCurIndex;
        if (i == 0) {
            return 0;
        }
        return (i == 1 && this.mWitnessSize == 1) ? 1 : 2;
    }

    public static void start(Context context, String str) {
        Intent intent = new Intent(context, MultiSignatureManagerActivity.class);
        intent.putExtra(TronConfig.WALLET_DATA, str);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        AcMultiSignatureBinding inflate = AcMultiSignatureBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        FrameLayout root = inflate.getRoot();
        mappingId();
        setClick();
        setView(root, 2);
    }

    private void setClick() {
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                switch (view.getId()) {
                    case R.id.bt_confirm:
                        if (StringTronUtil.getText(etSelectAddress).equals(tvUcAddress.getText().toString())) {
                            rlSelect.setVisibility(View.GONE);
                        } else {
                            btConfirm();
                        }
                        UIUtils.hideSoftKeyBoard(MultiSignatureManagerActivity.this);
                        return;
                    case R.id.iv_one_delete:
                        etSelectAddress.setText("");
                        return;
                    case R.id.ll_common_left:
                        finish();
                        return;
                    case R.id.ll_common_left2:
                        finish();
                        return;
                    case R.id.ll_edit:
                        if (selectedWallet.getCreateType() == 7) {
                            MultiSignatureManagerActivity multiSignatureManagerActivity = MultiSignatureManagerActivity.this;
                            multiSignatureManagerActivity.toast(multiSignatureManagerActivity.getString(R.string.no_samsung_to_shield));
                            return;
                        }
                        etSelectAddress.setText(tvUcAddress.getText().toString());
                        rlSelect.setVisibility(View.VISIBLE);
                        return;
                    case R.id.ll_more:
                        showPopWindow2();
                        return;
                    case R.id.rl_address_book:
                        AddressBookActivity.startForResult(MultiSignatureManagerActivity.this, AddressBookActivity.TYPE_SELECT_ADDRESS, TronConfig.ADDRESS_BOOK_SELECT_CODE);
                        return;
                    case R.id.rl_right:
                        showPopWindow(0);
                        return;
                    case R.id.rl_right2:
                        showPopWindow(1);
                        return;
                    case R.id.rl_select:
                        rlSelect.setVisibility(View.GONE);
                        return;
                    case R.id.tv_add:
                    case R.id.tv_empty_addpermissions:
                        if (mAccount == null || mAccount.toString().length() == 0) {
                            ToastUtil toastUtil = ToastUtil.getInstance();
                            MultiSignatureManagerActivity multiSignatureManagerActivity2 = MultiSignatureManagerActivity.this;
                            toastUtil.showToast((Activity) multiSignatureManagerActivity2, multiSignatureManagerActivity2.getString(R.string.not_activate));
                            return;
                        } else if (SamsungMultisignUtils.isSamsungWallet(selectedWallet.getAddress())) {
                            MultiSignatureManagerActivity multiSignatureManagerActivity3 = MultiSignatureManagerActivity.this;
                            multiSignatureManagerActivity3.toast(multiSignatureManagerActivity3.getString(R.string.no_samsung_to_shield));
                            return;
                        } else if (LedgerWallet.isLedger(selectedWallet) && !SpAPI.THIS.getCurrentChain().isMainChain) {
                            MultiSignatureManagerActivity multiSignatureManagerActivity4 = MultiSignatureManagerActivity.this;
                            multiSignatureManagerActivity4.toast(multiSignatureManagerActivity4.getString(R.string.ledger_not_support_on_dappchain));
                            return;
                        } else if (selectedWallet.isWatchOnly() && !SpAPI.THIS.getCurrentChain().isMainChain) {
                            MultiSignatureManagerActivity multiSignatureManagerActivity5 = MultiSignatureManagerActivity.this;
                            multiSignatureManagerActivity5.toast(multiSignatureManagerActivity5.getString(R.string.no_support));
                            return;
                        } else if (mAllPermissionList.size() == mWitnessSize + 9) {
                            MultiSignatureManagerActivity multiSignatureManagerActivity6 = MultiSignatureManagerActivity.this;
                            multiSignatureManagerActivity6.toast(multiSignatureManagerActivity6.getString(R.string.no_support_more));
                            return;
                        } else {
                            DoPermissionActivity.startActivity(MultiSignatureManagerActivity.this, DoPermissionActivity.PAGE_TYPE.TYPE_ADD, -2, mAccount.toByteArray(), walletName, tvUcAddress.getText().toString(), 2, mWitnessSize, selectedWallet.getAddress(), mPermissionIsEmpty);
                            return;
                        }
                    default:
                        return;
                }
            }
        };
        this.binding.rlRight.setOnClickListener(noDoubleClickListener2);
        this.binding.llMore.setOnClickListener(noDoubleClickListener2);
        this.binding.rlRight2.setOnClickListener(noDoubleClickListener2);
        this.binding.llCommonLeft.setOnClickListener(noDoubleClickListener2);
        this.binding.llCommonLeft2.setOnClickListener(noDoubleClickListener2);
        this.binding.llEdit.setOnClickListener(noDoubleClickListener2);
        this.binding.btConfirm.setOnClickListener(noDoubleClickListener2);
        this.binding.ivOneDelete.setOnClickListener(noDoubleClickListener2);
        this.binding.rlSelect.setOnClickListener(noDoubleClickListener2);
        this.binding.tvAdd.setOnClickListener(noDoubleClickListener2);
        this.binding.tvEmptyAddpermissions.setOnClickListener(noDoubleClickListener2);
        this.binding.rlAddressBook.setOnClickListener(noDoubleClickListener2);
    }

    private void mappingId() {
        this.statusbar = this.binding.statusbar;
        this.ivCommonLeft = this.binding.ivCommonLeft;
        this.llCommonLeft = this.binding.llCommonLeft;
        this.tvCommonTitle = this.binding.tvCommonTitle;
        this.ivQr = this.binding.ivQr;
        this.rlRight = this.binding.rlRight;
        this.bottomLine = this.binding.bottomLine;
        this.backview = this.binding.backview;
        this.rlBack = this.binding.rlBack;
        this.viewpagerLayout = this.binding.viewpagerLayout;
        this.viewpager = this.binding.viewpager;
        this.llIndicator = this.binding.llIndicator;
        this.ivMore = this.binding.ivMore;
        this.llMore = this.binding.llMore;
        this.tvPermissionName = this.binding.tvPermissionName;
        this.statusbar2 = this.binding.statusbar2;
        this.ivCommonLeft2 = this.binding.ivCommonLeft2;
        this.llCommonLeft2 = this.binding.llCommonLeft2;
        this.tvCommonTitle2 = this.binding.tvCommonTitle2;
        this.ivQr2 = this.binding.ivQr2;
        this.rlRight2 = this.binding.rlRight2;
        this.bottomLine2 = this.binding.bottomLine2;
        this.backview2 = this.binding.backview2;
        this.rlBackTwo = this.binding.rlBackTwo;
        this.llEmpty = this.binding.llEmpty;
        this.rlMiddle = this.binding.rlMiddle;
        this.tvUcAddress = this.binding.tvUcAddress;
        this.tvUc = this.binding.tvUc;
        this.ivEdit = this.binding.ivEdit;
        this.llEdit = this.binding.llEdit;
        this.rlUc = this.binding.rlUc;
        this.vLine = this.binding.vLine;
        this.tvSa = this.binding.tvSa;
        this.tvAdd = this.binding.tvAdd;
        this.etSelectAddress = this.binding.etSelectAddress;
        this.ivOneDelete = this.binding.ivOneDelete;
        this.eetSelectAddress = this.binding.eetSelectAddress;
        this.btConfirm = this.binding.btConfirm;
        this.rlSelect = this.binding.rlSelect;
        this.rlAddressBook = this.binding.rlAddressBook;
        this.mEmptyDescTv = this.binding.tvEmptyDesc;
        this.mEmptyAddPermissionsTv = this.binding.tvEmptyAddpermissions;
    }

    @Override
    protected void processData() {
        String stringExtra = getIntent().getStringExtra(TronConfig.WALLET_DATA);
        this.walletName = stringExtra;
        this.selectedWallet = WalletUtils.getWallet(stringExtra);
        initAllContractArray();
        this.tvUcAddress.setText(this.selectedWallet.getAddress());
        addManager();
        addListener();
        StatusBarUtils.setLightStatusBar(this, true);
        getAccount();
        this.viewpager.setPageTransformer(new CustPagerTransformer(this));
        this.viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrollStateChanged(int i) {
            }

            @Override
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override
            public void onPageSelected(int i) {
                mCurIndex = i;
                clearIndex();
                if (llIndicator.getChildAt(mCurIndex) != null) {
                    llIndicator.getChildAt(mCurIndex).setBackground(getResources().getDrawable(R.drawable.border_135dcd_circle));
                }
                refreshPermissionTitle();
                for (int i2 = 0; i2 < fragments.size(); i2++) {
                    if (i2 == i) {
                        ((MsManagerFragment) fragments.get(i2)).changeView(true);
                    } else {
                        ((MsManagerFragment) fragments.get(i2)).changeView(false);
                    }
                }
            }
        });
        this.tvPermissionName.setText(R.string.owner_permisson);
        this.etSelectAddress.setText(this.tvUcAddress.getText().toString());
        AnalyticsHelper.logEvent(AnalyticsHelper.WalletManagerPage.ENTER_WALLET_MANAGER_PERMISSION_PAGE);
    }

    private void initAllContractArray() {
        this.mAllPermissionArray.clear();
        try {
            ArrayList<PermissionGroupBean> arrayList = (ArrayList) new Gson().fromJson(AssetsRawUtils.getFromAssets(AppContextUtil.getContext(), "support_all_contract_list.json"), new TypeToken<List<PermissionGroupBean>>() {
            }.getType());
            this.mAllPermissionGroupArray = arrayList;
            if (arrayList == null || arrayList.size() <= 0) {
                return;
            }
            Iterator<PermissionGroupBean> it = this.mAllPermissionGroupArray.iterator();
            while (it.hasNext()) {
                List<PermissionGroupBean.PermissionBean> list = it.next().getList();
                if (list != null) {
                    this.mAllPermissionArray.addAll(list);
                }
            }
        } catch (IOException e) {
            LogUtils.e(e);
        }
    }

    public void refreshPermissionTitle() {
        int i = this.mCurIndex;
        if (i == 0) {
            this.tvPermissionName.setText(R.string.owner_permisson);
        } else if (i == 1 && this.mWitnessSize == 1) {
            this.tvPermissionName.setText(R.string.witness_permisson);
        } else {
            this.tvPermissionName.setText(R.string.active_permision);
        }
    }

    private void addListener() {
        this.etSelectAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                eetSelectAddress.hideError3();
                if (TextUtils.isEmpty(editable)) {
                    ivOneDelete.setVisibility(View.INVISIBLE);
                    btConfirm.setEnabled(false);
                    return;
                }
                ivOneDelete.setVisibility(View.VISIBLE);
                btConfirm.setEnabled(true);
            }
        });
    }

    private void addManager() {
        RxManager rxManager = new RxManager();
        this.rxManager = rxManager;
        rxManager.on(Event.UPDATESUCCESS, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$addManager$0(obj);
            }
        });
    }

    public void lambda$addManager$0(Object obj) throws Exception {
        if (obj instanceof Pair) {
            Pair pair = (Pair) obj;
            if (pair.first instanceof Integer) {
                this.intExtra = ((Integer) pair.first).intValue();
                new Handler(getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        IToast.getIToast().setImage(R.mipmap.broadcast_success).show(getString(R.string.transaction_submitted));
                    }
                });
                new Handler(getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getAccount();
                    }
                }, 2000L);
            }
        }
        this.intExtra = 0;
        new Handler(getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                IToast.getIToast().setImage(R.mipmap.broadcast_success).show(getString(R.string.transaction_submitted));
            }
        });
        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                getAccount();
            }
        }, 2000L);
    }

    public void clearIndex() {
        for (int i = 0; i < this.llIndicator.getChildCount(); i++) {
            this.llIndicator.getChildAt(i).setBackground(getResources().getDrawable(R.drawable.border_efefef_circle));
        }
    }

    public void btConfirm() {
        this.havePermission = false;
        final String text = StringTronUtil.getText(this.etSelectAddress);
        if (StringTronUtil.isAddressValid(StringTronUtil.decodeFromBase58Check(text))) {
            runOnThreeThread(new OnBackground() {
                @Override
                public final void doOnBackground() {
                    lambda$btConfirm$2(text);
                }
            });
            return;
        }
        this.eetSelectAddress.setTextError3(getString(R.string.error));
        this.eetSelectAddress.showError3();
    }

    public void lambda$btConfirm$2(String str) {
        try {
            final Protocol.Account queryAccount = TronAPI.queryAccount(StringTronUtil.decodeFromBase58Check(str), false);
            if (queryAccount != null && queryAccount.toString().length() != 0) {
                List<Protocol.Key> keysList = queryAccount.getOwnerPermission().getKeysList();
                for (int i = 0; i < keysList.size(); i++) {
                    if (StringTronUtil.encode58Check(keysList.get(i).getAddress().toByteArray()).equals(this.selectedWallet.getAddress())) {
                        this.havePermission = true;
                    }
                }
                if (!this.havePermission) {
                    LogUtils.d(TAG, "activeList.size()" + queryAccount.getActivePermissionList().size());
                    if (queryAccount.getActivePermissionList() != null && queryAccount.getActivePermissionList().size() > 0) {
                        for (Protocol.Permission permission : queryAccount.getActivePermissionList()) {
                            List<Protocol.Key> keysList2 = permission.getKeysList();
                            for (int i2 = 0; i2 < keysList2.size(); i2++) {
                                Protocol.Key key = keysList2.get(i2);
                                LogUtils.d(TAG, "address:" + StringTronUtil.encode58Check(key.getAddress().toByteArray()));
                                if (StringTronUtil.encode58Check(key.getAddress().toByteArray()).equals(this.selectedWallet.getAddress())) {
                                    this.havePermission = true;
                                }
                            }
                        }
                    }
                }
            }
            runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$btConfirm$1(queryAccount);
                }
            });
        } catch (ConnectErrorException e) {
            LogUtils.e(e);
        }
    }

    public void lambda$btConfirm$1(Protocol.Account account) {
        if (this.havePermission) {
            this.mAccount = account;
            this.rlSelect.setVisibility(View.GONE);
            this.tvUcAddress.setText(StringTronUtil.getText(this.etSelectAddress));
            doUpdateAddress(this.mAccount);
            return;
        }
        toast(getString(R.string.no_enough_owner));
        this.rlSelect.setVisibility(View.GONE);
    }

    public void showPopWindow2() {
        View inflate = getLayoutInflater().inflate(R.layout.ms_pop_more, (ViewGroup) null);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.ll_edit);
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.ll_delete);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.iv_delete);
        ImageView imageView2 = (ImageView) inflate.findViewById(R.id.iv_edit);
        if (this.popupWindow2 == null) {
            PopupWindow popupWindow = new PopupWindow(inflate, -2, -2, true);
            this.popupWindow2 = popupWindow;
            popupWindow.setOutsideTouchable(true);
        }
        int i = this.mCurIndex;
        if (i == 0) {
            linearLayout2.setVisibility(View.GONE);
        } else if (i == 1 && this.mWitnessSize == 1) {
            linearLayout2.setVisibility(View.GONE);
        } else {
            linearLayout2.setVisibility(View.VISIBLE);
        }
        this.popupWindow2.setContentView(inflate);
        if (this.popupWindow2.isShowing()) {
            this.popupWindow2.dismiss();
        } else {
            this.popupWindow2.getContentView().measure(0, 0);
            this.popupWindow2.showAsDropDown(this.ivMore, this.ivMore.getWidth() - this.popupWindow2.getContentView().getMeasuredWidth(), 20);
        }
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow2.dismiss();
                if (selectedWallet.getCreateType() == 7) {
                    MultiSignatureManagerActivity multiSignatureManagerActivity = MultiSignatureManagerActivity.this;
                    multiSignatureManagerActivity.toast(multiSignatureManagerActivity.getString(R.string.no_samsung_to_shield));
                } else if (LedgerWallet.isLedger(selectedWallet) && !SpAPI.THIS.getCurrentChain().isMainChain) {
                    MultiSignatureManagerActivity multiSignatureManagerActivity2 = MultiSignatureManagerActivity.this;
                    multiSignatureManagerActivity2.toast(multiSignatureManagerActivity2.getString(R.string.ledger_not_support_on_dappchain));
                } else if (selectedWallet.isWatchOnly() && !SpAPI.THIS.getCurrentChain().isMainChain) {
                    MultiSignatureManagerActivity multiSignatureManagerActivity3 = MultiSignatureManagerActivity.this;
                    multiSignatureManagerActivity3.toast(multiSignatureManagerActivity3.getString(R.string.no_support));
                } else {
                    DoPermissionActivity.startActivity(MultiSignatureManagerActivity.this, DoPermissionActivity.PAGE_TYPE.TYPE_MODIFY, mCurIndex, mAccount.toByteArray(), walletName, tvUcAddress.getText().toString(), getPermissionType(), mWitnessSize, selectedWallet.getAddress(), mPermissionIsEmpty);
                }
            }
        });
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow2.dismiss();
                if (selectedWallet.getCreateType() == 7) {
                    MultiSignatureManagerActivity multiSignatureManagerActivity = MultiSignatureManagerActivity.this;
                    multiSignatureManagerActivity.toast(multiSignatureManagerActivity.getString(R.string.no_samsung_to_shield));
                    return;
                }
                CustomDialog.Builder builder = new CustomDialog.Builder(MultiSignatureManagerActivity.this);
                final CustomDialog build = builder.style(R.style.loading_dialog).cancelTouchout(true).heightDimenRes(-2).widthDimenRes(-1).setGravity(17).view(R.layout.delete_dialog).build();
                View view2 = builder.getView();
                CheckBox checkBox = (CheckBox) view2.findViewById(R.id.ck);
                ((TextView) view2.findViewById(R.id.tv_cancle)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view3) {
                        build.dismiss();
                    }
                });
                ((TextView) view2.findViewById(R.id.tv_ok)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view3) {
                        build.dismiss();
                        if (LedgerWallet.isLedger(selectedWallet) && !SpAPI.THIS.getCurrentChain().isMainChain) {
                            toast(getString(R.string.ledger_not_support_on_dappchain));
                        } else if (selectedWallet.isWatchOnly() && !SpAPI.THIS.getCurrentChain().isMainChain) {
                            toast(getString(R.string.no_support));
                        } else if (mAllPermissionList.size() == mWitnessSize + 2) {
                            ToastUtil.getInstance().showToast((Activity) MultiSignatureManagerActivity.this, getString(R.string.delete4));
                        } else {
                            deleteActive();
                        }
                    }
                });
                build.show();
            }
        });
    }

    public void deleteActive() {
        ThreadPoolManager.newInstance().addExecuteTask(new Runnable() {
            @Override
            public void run() {
                if (mAccount.getBalance() / 1000000.0d <= 100.0d) {
                    ToastUtil.getInstance().showToast(MultiSignatureManagerActivity.this, R.string.no_trx);
                    return;
                }
                ArrayList arrayList = new ArrayList();
                if (mAllPermissionList != null && mAllPermissionList.size() > mWitnessSize + 1) {
                    arrayList.addAll(mAllPermissionList.subList(mWitnessSize + 1, mAllPermissionList.size()));
                }
                if (mCurIndex > 0) {
                    arrayList.remove((mCurIndex - 1) - mWitnessSize);
                }
                handleExtention(TronAPI.createAccountPermissionUpdateContract(mAccount.getAddress().toByteArray(), mAccount.getOwnerPermission(), mAccount.getWitnessPermission(), arrayList));
            }
        });
    }

    public void handleExtention(GrpcAPI.TransactionExtention transactionExtention) {
        if (transactionExtention != null && transactionExtention.hasResult() && transactionExtention.getTransaction().toString().length() > 0) {
            confirmTransaction(transactionExtention.getTransaction());
        } else if (transactionExtention != null && ResultCodeContant.ERROR_40002.equals(transactionExtention.getResult().getCode().toString())) {
            Toast(R.string.unsupport_operations);
        } else {
            Toast(R.string.net_error);
        }
    }

    private void confirmTransaction(Protocol.Transaction transaction) {
        boolean z;
        if (transaction == null || !transaction.hasRawData()) {
            return;
        }
        try {
            z = WalletUtils.checkHaveOwnerPermissions(this.selectedWallet.getAddress(), this.mAccount.getOwnerPermission());
        } catch (PermissionException e) {
            LogUtils.e(e);
            z = true;
        }
        ConfirmTransactionNewActivity.startActivity(getIContext(), ParamBuildUtils.getPermissionUpdateTransactionParamBuilder(z, this.walletName, transaction, -1, 2));
    }

    public void showPopWindow(int i) {
        View inflate = getLayoutInflater().inflate(R.layout.ms_pop_support, (ViewGroup) null);
        if (this.popupWindow == null) {
            PopupWindow popupWindow = new PopupWindow(inflate, -1, -2, true);
            this.popupWindow = popupWindow;
            popupWindow.setOutsideTouchable(true);
        }
        if (this.popupWindow.isShowing()) {
            this.popupWindow.dismiss();
            return;
        }
        this.popupWindow.getContentView().measure(0, 0);
        int width = this.ivQr.getWidth() - this.popupWindow.getContentView().getMeasuredWidth();
        if (i == 0) {
            this.popupWindow.showAsDropDown(this.ivQr, 0, 20);
        } else {
            this.popupWindow.showAsDropDown(this.ivQr2, width, 20);
        }
    }

    public void getAccount() {
        this.fragments.clear();
        this.flag2 = false;
        AsyncJob.doInBackground(new AsyncJob.OnBackgroundJob() {
            @Override
            public void doOnBackground() {
                try {
                    MultiSignatureManagerActivity multiSignatureManagerActivity = MultiSignatureManagerActivity.this;
                    multiSignatureManagerActivity.address = StringTronUtil.decodeFromBase58Check(multiSignatureManagerActivity.tvUcAddress.getText().toString());
                    MultiSignatureManagerActivity multiSignatureManagerActivity2 = MultiSignatureManagerActivity.this;
                    multiSignatureManagerActivity2.mAccount = TronAPI.queryAccount(multiSignatureManagerActivity2.address, false);
                } catch (Exception e) {
                    LogUtils.e(e);
                    SentryUtil.captureException(e);
                    runOnUIThread(new OnMainThread() {
                        @Override
                        public void doInUIThread() {
                            showErrorPage();
                        }
                    });
                    flag2 = true;
                }
                AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                    @Override
                    public void doInUIThread() {
                        if (!flag2) {
                            dismissLoadingPage();
                        }
                        doUpdateAddress(mAccount);
                    }
                });
            }
        });
    }

    public void doUpdateAddress(Protocol.Account account) {
        this.mWitnessSize = 0;
        if (account != null && account.toString().length() != 0) {
            this.mAllPermissionList = new ArrayList(account.getActivePermissionList());
            Protocol.Permission ownerPermission = account.getOwnerPermission();
            if (ownerPermission != null && ownerPermission.toString().length() != 0) {
                this.mAllPermissionList.add(0, ownerPermission);
                Protocol.Permission witnessPermission = account.getWitnessPermission();
                if (witnessPermission != null && witnessPermission.toString().length() != 0 && witnessPermission.getKeysCount() > 0) {
                    this.mAllPermissionList.add(1, witnessPermission);
                    this.mWitnessSize = 1;
                }
                if (this.mAllPermissionList.size() == 0) {
                    handleEmptyOrNotActivated();
                    this.llEmpty.setVisibility(View.VISIBLE);
                    this.rlBack.setVisibility(View.GONE);
                    StatusBarUtils.setLightStatusBar(this, true);
                    return;
                }
                this.llEmpty.setVisibility(View.GONE);
                this.rlBack.setVisibility(View.VISIBLE);
                StatusBarUtils.setLightStatusBar(this, false);
                if (this.mAllPermissionList.size() == 1) {
                    this.llIndicator.setVisibility(View.GONE);
                } else {
                    this.llIndicator.setVisibility(View.VISIBLE);
                }
                String str = new String(this.tvUcAddress.getText().toString());
                this.fragments.clear();
                int i = this.intExtra;
                if (i == -2) {
                    this.mCurIndex = this.mAllPermissionList.size() - 1;
                } else if (i == -1) {
                    this.mCurIndex = 0;
                } else {
                    this.mCurIndex = i;
                }
                int i2 = 0;
                while (i2 < this.mAllPermissionList.size()) {
                    Protocol.Permission permission = this.mAllPermissionList.get(i2);
                    Bundle bundle = new Bundle();
                    bundle.putByteArray("permission", permission.toByteArray());
                    bundle.putString("address", str);
                    bundle.putString(AppMeasurementSdk.ConditionalUserProperty.NAME, permission.getPermissionName());
                    bundle.putBoolean("isowner", i2 == 0);
                    if (i2 == this.mCurIndex) {
                        bundle.putBoolean("iscurrent", true);
                    }
                    bundle.putParcelableArrayList(INTENT_ALL_PERMISSIONS, this.mAllPermissionArray);
                    bundle.putParcelableArrayList(INTENT_ALL_PERMISSION_GROUPS, this.mAllPermissionGroupArray);
                    MsManagerFragment msManagerFragment = new MsManagerFragment();
                    msManagerFragment.setArguments(bundle);
                    this.fragments.add(msManagerFragment);
                    i2++;
                }
                if (this.viewpager.getAdapter() == null) {
                    this.viewpager.setAdapter(this.mViewPagerAdapter);
                }
                this.mViewPagerAdapter.updateFragments(this.fragments);
                this.llIndicator.removeAllViews();
                for (int i3 = 0; i3 < this.mAllPermissionList.size(); i3++) {
                    this.llIndicator.addView((TextView) LayoutInflater.from(this).inflate(R.layout.item_indicator, (ViewGroup) this.llIndicator, false));
                }
                clearIndex();
                if (this.llIndicator.getChildAt(0) != null) {
                    this.llIndicator.getChildAt(0).setBackground(getResources().getDrawable(R.drawable.border_135dcd_circle));
                }
                this.viewpager.setCurrentItem(this.mCurIndex);
                refreshPermissionTitle();
                return;
            }
            showEmptyPage();
            return;
        }
        showEmptyPage();
    }

    private void showEmptyPage() {
        StatusBarUtils.setLightStatusBar(this, true);
        this.llEmpty.setVisibility(View.VISIBLE);
        this.rlBack.setVisibility(View.GONE);
        this.viewpagerLayout.setVisibility(View.GONE);
        updateEmptyText();
    }

    private void handleEmptyOrNotActivated() {
        if (this.mAccount.getBalance() > 0) {
            this.mPermissionIsEmpty = true;
            updateEmptyText();
        } else if (this.mAccount != null) {
            updateEmptyText();
        } else {
            showNotActivated(R.string.permisson_not_activated, 8);
        }
    }

    private void updateEmptyText() {
        this.mEmptyDescTv.setText(R.string.permisson_empty);
        this.mEmptyAddPermissionsTv.setVisibility(View.VISIBLE);
    }

    private void showNotActivated(int i, int i2) {
        this.mEmptyDescTv.setText(i);
        this.mEmptyAddPermissionsTv.setVisibility(i2);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        RxManager rxManager = this.rxManager;
        if (rxManager != null) {
            rxManager.clear();
        }
        super.onDestroy();
    }

    @Override
    public void onReLoadButtonClick() {
        getAccount();
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 2027 && intent != null) {
            String stringExtra = intent.getStringExtra(TronConfig.ADDRESS_BOOK_SELECT);
            if (StringTronUtil.isEmpty(stringExtra)) {
                this.etSelectAddress.setText("");
            } else {
                this.etSelectAddress.setText(stringExtra);
            }
        }
    }
}
