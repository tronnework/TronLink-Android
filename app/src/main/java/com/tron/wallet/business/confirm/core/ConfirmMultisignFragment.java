package com.tron.wallet.business.confirm.core;

import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.protobuf.InvalidProtocolBufferException;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.AddressWeightAdapter;
import com.tron.wallet.business.confirm.PermissionListAdapter;
import com.tron.wallet.business.confirm.parambuilder.BaseConfirmTransParamBuilder;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.ManagePermissionGroupParam;
import com.tron.wallet.business.confirm.parambuilder.bean.ParticipateMultisignParam;
import com.tron.wallet.common.components.ErrorEdiTextLayout;
import com.tron.wallet.common.components.SignatureProgressView;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.ToastUtil;
import com.tron.wallet.databinding.FgConfirmMultisignBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
import org.tron.walletserver.ConnectErrorException;
import org.tron.walletserver.PermissionException;
import org.tron.walletserver.Wallet;
public class ConfirmMultisignFragment extends BaseFragment<EmptyPresenter, EmptyModel> {
    private FgConfirmMultisignBinding binding;
    private Protocol.Account mAccount;
    private AddressWeightAdapter mAddressWeightAdapter;
    RecyclerView mAddressWeightRv;
    ImageView mArrowIv;
    View mBottomBtn;
    private Protocol.Transaction.Contract mContract;
    private int mContractId;
    private Wallet mCurDoWallet;
    EditText mInvalidTimeEt;
    private int mInvalidTimeH;
    ErrorEdiTextLayout mInvalidTimeLayout;
    private BaseParam mParam;
    private float mPercent;
    private PermissionListAdapter mPermissionListAdapter;
    RecyclerView mPermissionListRv;
    private Protocol.Permission mSelectedPermission;
    TextView mSelectedPermissionNameTv;
    SignatureProgressView mSignatureProgressView;
    TextView mSignatureValueTv;
    TextView tvBottomNext;
    TextView tvFailureTimeTitle;
    private ArrayList<Protocol.Key> mAddressWeightList = new ArrayList<>();
    private ArrayList<Protocol.Permission> mPermissionListOrdered = new ArrayList<>();
    private HashMap<String, Boolean> mPermissionEnableMap = new HashMap<>();

    @Override
    protected void processData() {
        setHeaderBar(getResources().getString(R.string.confirm_sign_title));
        runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$processData$1();
            }
        });
    }

    public void lambda$processData$1() {
        initIntentParam();
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$processData$0();
            }
        });
    }

    public void lambda$processData$0() {
        refreshLedgerView();
        refreshPermissionView();
        initListener();
        initPermissionRecyclerView();
        initAddressWeightRecyclerView();
        refreshViewForSelectedPermission();
    }

    private void initListener() {
        this.mInvalidTimeEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                validateTime();
            }
        });
    }

    public boolean validateTime() {
        try {
            int parseInt = Integer.parseInt(this.mInvalidTimeEt.getText().toString());
            this.mInvalidTimeH = parseInt;
            if (parseInt < 1 || parseInt > 24) {
                this.mInvalidTimeLayout.setTextError3(R.string.input_invalid_time);
                this.mInvalidTimeLayout.showError3();
                return false;
            }
            this.mInvalidTimeLayout.hideError3();
            return true;
        } catch (Exception unused) {
            this.mInvalidTimeLayout.setTextError3(R.string.input_invalid_time);
            this.mInvalidTimeLayout.showError3();
            return false;
        }
    }

    private void refreshLedgerView() {
        try {
            int createType = WalletUtils.getSelectedPublicWallet().getCreateType();
            if (createType == 8) {
                this.tvBottomNext.setText(R.string.request_from_ledger);
            } else if (WalletUtils.getSelectedPublicWallet().isWatchOnly() && createType != 7 && createType != 8) {
                this.tvBottomNext.setText(R.string.create_transcation_qr);
            } else {
                this.tvBottomNext.setText(R.string.confirm);
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    private void refreshPermissionView() {
        if (this.mPermissionListOrdered.size() <= 1) {
            this.mArrowIv.setVisibility(View.GONE);
        }
        if (this.mPermissionListOrdered.size() <= 0 || this.mPermissionListOrdered.get(0) == null || !this.mPermissionEnableMap.get(String.valueOf(this.mPermissionListOrdered.get(0).getId())).booleanValue()) {
            return;
        }
        Protocol.Permission permission = this.mPermissionListOrdered.get(0);
        this.mSelectedPermission = permission;
        this.mSelectedPermissionNameTv.setText(permission.getPermissionName());
    }

    public void refreshViewForSelectedPermission() {
        if (this.mSelectedPermission != null) {
            this.mAddressWeightList.clear();
            this.mAddressWeightList.addAll(this.mSelectedPermission.getKeysList());
            this.mAddressWeightAdapter.notifyDataSetChanged();
            long curPermissionWeight = getCurPermissionWeight();
            try {
                SpannableString spannableString = new SpannableString(String.format(getResources().getString(R.string.signature_progress_value), Long.valueOf(getCurPermissionWeight()), Long.valueOf(this.mSelectedPermission.getThreshold())));
                spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.black_23)), 1, String.valueOf(curPermissionWeight).length() + 1, 17);
                this.mSignatureValueTv.setText(spannableString);
            } catch (IllegalStateException e) {
                SentryUtil.captureException(e);
                LogUtils.e(e);
            }
            float floatValue = Float.valueOf((float) curPermissionWeight).floatValue() / Float.valueOf((float) this.mSelectedPermission.getThreshold()).floatValue();
            this.mPercent = floatValue;
            this.mSignatureProgressView.setProgressValue(floatValue);
        }
    }

    private void initIntentParam() {
        BaseParam baseParam = (BaseParam) getActivity().getIntent().getParcelableExtra(BaseConfirmTransParamBuilder.INTENT_PARAM);
        this.mParam = baseParam;
        if (baseParam == null || baseParam.getTransactionBean() == null || this.mParam.getTransactionBean().getBytes() == null || this.mParam.getTransactionBean().getBytes().size() <= 0) {
            return;
        }
        try {
            Protocol.Transaction parseFrom = Protocol.Transaction.parseFrom(this.mParam.getTransactionBean().getBytes().get(0));
            String owner = TransactionUtils.getOwner(parseFrom);
            this.mContract = parseFrom.getRawData().getContract(0);
            this.mContractId = parseFrom.getRawData().getContract(0).getType().getNumber();
            try {
                this.mAccount = TronAPI.queryAccount(StringTronUtil.decodeFromBase58Check(owner), false);
            } catch (ConnectErrorException e) {
                LogUtils.e(e);
            }
            initWallet();
            initPermissionListOrdered();
        } catch (InvalidProtocolBufferException e2) {
            LogUtils.e(e2);
        }
    }

    private void initWallet() {
        BaseParam baseParam = this.mParam;
        if (baseParam instanceof ParticipateMultisignParam) {
            this.mCurDoWallet = WalletUtils.getWallet(((ParticipateMultisignParam) baseParam).getWalletName());
        } else if (baseParam instanceof ManagePermissionGroupParam) {
            this.mCurDoWallet = WalletUtils.getWallet(((ManagePermissionGroupParam) baseParam).getWalletName());
        } else {
            this.mCurDoWallet = WalletUtils.getSelectedWallet();
        }
        Wallet wallet = this.mCurDoWallet;
        if (wallet == null || TextUtils.isEmpty(wallet.getWalletName())) {
            ToastError(R.string.no_wallet_selected);
        }
    }

    private void initPermissionListOrdered() {
        boolean z;
        ArrayList<Protocol.Permission> arrayList = new ArrayList();
        Protocol.Account account = this.mAccount;
        if (account != null) {
            Protocol.Permission ownerPermission = account.getOwnerPermission();
            if (ownerPermission != null && ownerPermission.toString().length() != 0) {
                arrayList.add(ownerPermission);
            }
            arrayList.addAll(this.mAccount.getActivePermissionList());
        }
        this.mPermissionListOrdered.clear();
        for (Protocol.Permission permission : arrayList) {
            try {
                z = WalletUtils.inPermissionGroup(this.mCurDoWallet.getAddress(), permission, this.mContract);
            } catch (PermissionException e) {
                LogUtils.e(e);
                z = false;
            }
            if (z) {
                this.mPermissionListOrdered.add(0, permission);
            } else {
                try {
                    if (WalletUtils.checkHavePermissions(this.mCurDoWallet.getAddress(), permission, this.mContractId)) {
                        this.mPermissionListOrdered.add(permission);
                    }
                } catch (PermissionException e2) {
                    LogUtils.e(e2);
                }
            }
            this.mPermissionEnableMap.put(String.valueOf(permission.getId()), Boolean.valueOf(z));
        }
    }

    private void initPermissionRecyclerView() {
        this.mPermissionListAdapter = new PermissionListAdapter(this.mPermissionListOrdered, this.mPermissionEnableMap);
        this.mPermissionListRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.mPermissionListRv.setAdapter(this.mPermissionListAdapter);
        this.mPermissionListAdapter.setItemClickListener(new PermissionListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                if (mPermissionListOrdered.size() > i) {
                    ConfirmMultisignFragment confirmMultisignFragment = ConfirmMultisignFragment.this;
                    confirmMultisignFragment.mSelectedPermission = (Protocol.Permission) confirmMultisignFragment.mPermissionListOrdered.get(i);
                    mSelectedPermissionNameTv.setText(mSelectedPermission.getPermissionName());
                    refreshViewForSelectedPermission();
                }
                hidenPermissionLayout();
            }
        });
    }

    private long getCurPermissionWeight() {
        Iterator<Protocol.Key> it = this.mAddressWeightList.iterator();
        while (it.hasNext()) {
            Protocol.Key next = it.next();
            String encode58Check = StringTronUtil.encode58Check(next.getAddress().toByteArray());
            Wallet wallet = this.mCurDoWallet;
            if (wallet != null && encode58Check != null && encode58Check.equals(wallet.getAddress())) {
                return next.getWeight();
            }
        }
        return 0L;
    }

    private void initAddressWeightRecyclerView() {
        this.mAddressWeightAdapter = new AddressWeightAdapter(this.mAddressWeightList, this.mCurDoWallet.getAddress());
        this.mAddressWeightRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.mAddressWeightRv.setAdapter(this.mAddressWeightAdapter);
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setType(1);
        FgConfirmMultisignBinding inflate = FgConfirmMultisignBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        setClick();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId() {
        this.mPermissionListRv = this.binding.rlPermissionList;
        this.mArrowIv = this.binding.ivArrow;
        this.mAddressWeightRv = this.binding.rlAddressWeight;
        this.mSelectedPermissionNameTv = this.binding.tvSelectedName;
        this.tvFailureTimeTitle = this.binding.tvFailureTimeTitle;
        this.mInvalidTimeLayout = this.binding.errorEtInvalidTime;
        this.mInvalidTimeEt = this.binding.tvInvalidTime;
        this.mSignatureValueTv = this.binding.tvSignatureValue;
        this.mSignatureProgressView = this.binding.progressSignature;
        this.mBottomBtn = this.binding.rlBottomNext;
        this.tvBottomNext = this.binding.tvBottomNext;
    }

    public void setClick() {
        NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                Protocol.Transaction parseFrom;
                Protocol.Transaction.Builder builder;
                Protocol.Transaction.raw.Builder builder2;
                int id = view.getId();
                if (id != R.id.rl_bottom_next) {
                    if (id != R.id.rl_permission_select) {
                        return;
                    }
                    taggleShowPermissionLayout();
                } else if (mSelectedPermission == null || !((Boolean) mPermissionEnableMap.get(String.valueOf(mSelectedPermission.getId()))).booleanValue()) {
                    if (mCurDoWallet == null || TextUtils.isEmpty(mCurDoWallet.getWalletName()) || !mCurDoWallet.getWalletName().equals(WalletUtils.getSelectedWallet().getWalletName())) {
                        ToastUtil.getInstance().showToast(mContext, getResources().getString(R.string.insufficient_permissions_not_currentwallet));
                    } else {
                        ToastUtil.getInstance().showToast(mContext, getResources().getString(R.string.insufficient_permissions));
                    }
                } else if (validateTime()) {
                    ArrayList arrayList = new ArrayList(mParam.getTransactionBean().getBytes());
                    if (arrayList.size() > 0) {
                        mParam.clear();
                    }
                    for (int i = 0; i < arrayList.size(); i++) {
                        try {
                            parseFrom = Protocol.Transaction.parseFrom((byte[]) arrayList.get(i));
                            builder = parseFrom.toBuilder();
                            builder2 = parseFrom.getRawData().toBuilder();
                        } catch (InvalidProtocolBufferException e) {
                            LogUtils.e(e);
                        }
                        if (parseFrom.getRawData().getContractCount() < 1) {
                            ConfirmMultisignFragment confirmMultisignFragment = ConfirmMultisignFragment.this;
                            confirmMultisignFragment.toast(confirmMultisignFragment.getString(R.string.transaction_fail));
                            break;
                        }
                        Protocol.Transaction.Contract.Builder builder3 = parseFrom.getRawData().getContract(0).toBuilder();
                        builder3.setPermissionId(mSelectedPermission.getId());
                        builder2.setContract(0, builder3.build());
                        builder2.setExpiration(System.currentTimeMillis() + (mInvalidTimeH * 3600000));
                        builder.setRawData(builder2.build());
                        mParam.addTransaction(builder.build());
                    }
                    FirebaseAnalytics.getInstance(getActivity()).logEvent("MultiSign_sign", null);
                    ConfirmTransactionNewActivity confirmTransactionNewActivity = (ConfirmTransactionNewActivity) getActivity();
                    mParam.setActives(true);
                    mParam.setOverThreshold(mPercent >= 1.0f);
                    confirmTransactionNewActivity.updateParam(mParam);
                    confirmTransactionNewActivity.JumpkTo(3);
                }
            }
        };
        this.binding.rlPermissionSelect.setOnClickListener(noDoubleClickListener);
        this.binding.rlBottomNext.setOnClickListener(noDoubleClickListener);
    }

    public void taggleShowPermissionLayout() {
        if (this.mPermissionListOrdered.size() <= 1) {
            return;
        }
        if (this.mPermissionListRv.getVisibility() == 0) {
            hidenPermissionLayout();
            return;
        }
        this.mPermissionListRv.setVisibility(View.VISIBLE);
        this.mArrowIv.setImageResource(R.mipmap.ic_mutilsign_arrow_up);
    }

    public void hidenPermissionLayout() {
        this.mPermissionListRv.setVisibility(View.GONE);
        this.mArrowIv.setImageResource(R.mipmap.ic_mutilsign_arrow_down);
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        ((ConfirmTransactionNewActivity) getActivity()).backUp(2);
    }
}
