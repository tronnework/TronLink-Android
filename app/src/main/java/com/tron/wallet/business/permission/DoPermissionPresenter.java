package com.tron.wallet.business.permission;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.ThreadPoolManager;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.parambuilder.utils.ParamBuildUtils;
import com.tron.wallet.business.permission.DoPermissionActivity;
import com.tron.wallet.business.permission.DoPermissionContract;
import com.tron.wallet.common.bean.PermissionGroupBean;
import com.tron.wallet.common.components.flowlayout.TagFlowLayout;
import com.tron.wallet.common.config.ResultCodeContant;
import com.tron.wallet.common.utils.AssetsRawUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.ToastUtil;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.walletserver.PermissionException;
public class DoPermissionPresenter extends DoPermissionContract.Presenter {
    private static final String TAG = "DoPermissionPresenter";
    private Protocol.Account mAccount;
    private int mActionType;
    private List<Integer> mHadOperationsIds;
    private Set<Integer> mHadUnSupportedOperationsIds;
    private int mModifyIndexInActive;
    private int mModifyIndexInAll;
    private String mModifyPermissionNameStr;
    private boolean mNotHasPermission;
    private Protocol.Permission mPermission;
    private String mPermissionNameStr;
    private int mPermissionType;
    private String mSelectedAddress;
    private String mSelectedWalletName;
    private long mThreshold;
    private String mWalletName;
    private int mWitnessCount;
    private ArrayList<PermissionGroupBean.PermissionBean> mAllPermissionArray = new ArrayList<>();
    private ArrayList<PermissionGroupBean> mAllPermissionGroupArray = new ArrayList<>();
    private List<Protocol.Key> mSelectedKeyList = new ArrayList();
    private String mControlAddress = "TS4a1Yb6WViKbBpCyLyreTc2cVVQ5RUuLJ";
    private List<PermissionGroupBean.PermissionBean> mSelectedPermissionList = new ArrayList();

    private boolean isOwnerOrWithness() {
        int i = this.mPermissionType;
        return i == 0 || i == 1;
    }

    @Override
    protected void onStart() {
        initData();
        refreshView();
    }

    private void refreshView() {
        ((DoPermissionContract.View) this.mView).initAdapter(this.mSelectedPermissionList);
        refreshViewForType(this.mActionType);
        ((DoPermissionContract.View) this.mView).refreshControlAddressValue(this.mControlAddress);
        ((DoPermissionContract.View) this.mView).refreshViewForPermissionType(this.mPermissionType, this.mNotHasPermission);
    }

    public void refreshViewForType(int i) {
        ((DoPermissionContract.View) this.mView).removeKeyContainerSubViews();
        ((DoPermissionContract.View) this.mView).addKeyViewToContainer(null, 0L, this.mPermissionType);
        if (i == DoPermissionActivity.PAGE_TYPE.TYPE_MODIFY.ordinal()) {
            refreshViewInModifyModel();
            ((DoPermissionContract.View) this.mView).setHeaderBar(((DoPermissionContract.View) this.mView).getIContext().getResources().getString(R.string.modify_permission));
            return;
        }
        ((DoPermissionContract.View) this.mView).setHeaderBar(((DoPermissionContract.View) this.mView).getIContext().getResources().getString(R.string.add_permission_title));
    }

    private void refreshViewInModifyModel() {
        ((DoPermissionContract.View) this.mView).refreshViewForPermissionNameThreshold(this.mPermissionNameStr, this.mThreshold);
        ((DoPermissionContract.View) this.mView).getTagAdapter().setSelectedList(getIndexForHadOperationsIds());
        ((DoPermissionContract.View) this.mView).adjustHasMoreForListener();
        ((DoPermissionContract.View) this.mView).getTagAdapter().notifyDataChanged();
        List<Protocol.Key> list = this.mSelectedKeyList;
        if (list != null || list.size() > 0) {
            ((DoPermissionContract.View) this.mView).removeKeyContainerSubViews();
            for (int i = 0; i < this.mSelectedKeyList.size(); i++) {
                Protocol.Key key = this.mSelectedKeyList.get(i);
                ((DoPermissionContract.View) this.mView).addKeyViewToContainer(StringTronUtil.encode58Check(key.getAddress().toByteArray()), key.getWeight(), this.mPermissionType);
            }
        }
        ((DoPermissionContract.View) this.mView).showPermissionClear();
    }

    private HashSet<Integer> getIndexForHadOperationsIds() {
        List<Integer> list = this.mHadOperationsIds;
        if (list == null || list.size() == 0) {
            return null;
        }
        HashSet<Integer> hashSet = new HashSet<>();
        for (Integer num : this.mHadOperationsIds) {
            int i = 0;
            while (true) {
                if (i >= this.mAllPermissionArray.size()) {
                    break;
                } else if (num.intValue() == this.mAllPermissionArray.get(i).getId()) {
                    hashSet.add(Integer.valueOf(i));
                    break;
                } else {
                    i++;
                }
            }
        }
        return hashSet;
    }

    private void initData() {
        this.mActionType = ((DoPermissionActivity) this.mView).getIntent().getIntExtra(DoPermissionActivity.INTENT_PARAM_TYPE, 0);
        this.mWalletName = ((DoPermissionActivity) this.mView).getIntent().getStringExtra(DoPermissionActivity.INTENT_PARAM_NAME);
        this.mControlAddress = ((DoPermissionActivity) this.mView).getIntent().getStringExtra(DoPermissionActivity.INTENT_PARAM_CONTROL_ADDRESS);
        this.mPermissionType = ((DoPermissionActivity) this.mView).getIntent().getIntExtra(DoPermissionActivity.INTENT_PERMISSION_TYPE, 0);
        byte[] byteArrayExtra = ((DoPermissionActivity) this.mView).getIntent().getByteArrayExtra(DoPermissionActivity.INTENT_PARAM_BYTE);
        this.mWitnessCount = ((DoPermissionActivity) this.mView).getIntent().getIntExtra(DoPermissionActivity.INTENT_PARAM_WITNESS, 0);
        this.mSelectedAddress = ((DoPermissionActivity) this.mView).getIntent().getStringExtra(DoPermissionActivity.INTENT_PARAM_ADDRESS);
        this.mNotHasPermission = ((DoPermissionActivity) this.mView).getIntent().getBooleanExtra(DoPermissionActivity.INTENT_PERMISSION_NOT_PERMISSION, false);
        try {
            this.mAccount = Protocol.Account.parseFrom(byteArrayExtra);
        } catch (InvalidProtocolBufferException e) {
            LogUtils.e(e);
        }
        initAllContractArray();
        if (this.mActionType == DoPermissionActivity.PAGE_TYPE.TYPE_MODIFY.ordinal()) {
            int intExtra = ((DoPermissionActivity) this.mView).getIntent().getIntExtra(DoPermissionActivity.INTENT_PARAM_INDEX, 0);
            this.mModifyIndexInAll = intExtra;
            this.mModifyIndexInActive = (intExtra - 1) - this.mWitnessCount;
            initModifyData();
            return;
        }
        this.mModifyIndexInAll = -1;
        addPlaceholderPermissionData();
    }

    private void addPlaceholderPermissionData() {
        PermissionGroupBean.PermissionBean permissionBean = new PermissionGroupBean.PermissionBean();
        permissionBean.setId(-1);
        this.mSelectedPermissionList.add(permissionBean);
    }

    private void initModifyData() {
        int i = this.mPermissionType;
        if (i == 0) {
            this.mPermission = this.mAccount.getOwnerPermission();
        } else if (i == 1) {
            this.mPermission = this.mAccount.getWitnessPermission();
        } else {
            this.mPermission = this.mAccount.getActivePermission(this.mModifyIndexInActive);
        }
        String permissionName = this.mPermission.getPermissionName();
        this.mPermissionNameStr = permissionName;
        this.mModifyPermissionNameStr = permissionName;
        try {
            List<Integer> permissionOperations = WalletUtils.getPermissionOperations(this.mPermission);
            this.mHadOperationsIds = permissionOperations;
            getPermissionBeanForIds(permissionOperations);
        } catch (PermissionException e) {
            LogUtils.e(e);
        }
        this.mHadUnSupportedOperationsIds = getUnSupportOperationIds();
        this.mThreshold = this.mPermission.getThreshold();
        this.mSelectedKeyList.clear();
        this.mSelectedKeyList.addAll(this.mPermission.getKeysList());
    }

    private void getPermissionBeanForIds(List<Integer> list) {
        this.mSelectedPermissionList.clear();
        for (Integer num : list) {
            Iterator<PermissionGroupBean.PermissionBean> it = this.mAllPermissionArray.iterator();
            while (true) {
                if (it.hasNext()) {
                    PermissionGroupBean.PermissionBean next = it.next();
                    if (num.intValue() == next.getId()) {
                        this.mSelectedPermissionList.add(next);
                        break;
                    }
                }
            }
        }
        if (this.mSelectedPermissionList.size() == 0) {
            addPlaceholderPermissionData();
        }
    }

    private void printAllOperations() {
        List<Integer> list = this.mHadOperationsIds;
        if (list == null || list.size() <= 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Integer num : this.mHadOperationsIds) {
            sb.append(num.intValue());
            sb.append(" ");
        }
        ToastUtil.getInstance().showToast(((DoPermissionContract.View) this.mView).getIContext(), sb.toString());
    }

    private HashSet<Integer> getUnSupportOperationIds() {
        List<Integer> list = this.mHadOperationsIds;
        if (list == null || list.size() == 0) {
            return null;
        }
        HashSet<Integer> hashSet = new HashSet<>();
        for (Integer num : this.mHadOperationsIds) {
            Iterator<PermissionGroupBean.PermissionBean> it = this.mAllPermissionArray.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (num.intValue() == it.next().getId()) {
                        break;
                    }
                } else {
                    hashSet.add(num);
                    break;
                }
            }
        }
        return hashSet;
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
                Iterator<PermissionGroupBean.PermissionBean> it2 = list.iterator();
                while (it2.hasNext()) {
                    LogUtils.d(TAG, "name:" + it2.next().getDisplay_name_zh());
                }
            }
        } catch (IOException e) {
            LogUtils.e(e);
        }
    }

    @Override
    public boolean validateThreshold(String str) {
        try {
            this.mThreshold = Integer.parseInt(str);
        } catch (Exception unused) {
            this.mThreshold = 0L;
        }
        if (this.mPermissionType == 1) {
            return false;
        }
        long j = this.mThreshold;
        if (j <= 0 || j > 100) {
            ((DoPermissionContract.View) this.mView).setThresholdTipViewVisiblity(0);
            return true;
        }
        ((DoPermissionContract.View) this.mView).setThresholdTipViewVisiblity(8);
        return false;
    }

    @Override
    public boolean validateKey(ViewGroup viewGroup) {
        int i;
        int i2;
        long j;
        this.mSelectedKeyList.clear();
        int childCount = viewGroup.getChildCount();
        String string = ((DoPermissionContract.View) this.mView).getIContext().getResources().getString(R.string.please_input_correct_address);
        boolean z = true;
        boolean z2 = true;
        int i3 = 0;
        boolean z3 = false;
        while (i3 < childCount) {
            View childAt = viewGroup.getChildAt(i3);
            View findViewById = childAt.findViewById(R.id.rl_address);
            EditText editText = (EditText) childAt.findViewById(R.id.et_weight);
            TextView textView = (TextView) childAt.findViewById(R.id.tv_addkey_tip);
            String obj = ((EditText) childAt.findViewById(R.id.et_key_address)).getText().toString();
            if (!TextUtils.isEmpty(obj)) {
                byte[] decodeFromBase58Check = StringTronUtil.decodeFromBase58Check(obj);
                if (!StringTronUtil.isAddressValid(decodeFromBase58Check)) {
                    textView.setText(string);
                    textView.setVisibility(View.VISIBLE);
                    findViewById.setSelected(z);
                    i2 = i3;
                    z3 = true;
                } else {
                    try {
                        i2 = i3;
                        j = Long.parseLong(editText.getText().toString());
                    } catch (Exception e) {
                        LogUtils.e(e);
                        i2 = i3;
                        j = 0;
                    }
                    if (j != 0) {
                        this.mSelectedKeyList.add(Protocol.Key.newBuilder().setAddress(ByteString.copyFrom(decodeFromBase58Check)).setWeight(j).build());
                    }
                    textView.setVisibility(View.GONE);
                    findViewById.setSelected(false);
                }
                z2 = false;
            } else {
                i2 = i3;
                textView.setVisibility(View.GONE);
                findViewById.setSelected(false);
            }
            i3 = i2 + 1;
            z = true;
        }
        if (childCount <= 0 || !z2) {
            i = 1;
        } else {
            View childAt2 = viewGroup.getChildAt(0);
            TextView textView2 = (TextView) childAt2.findViewById(R.id.tv_addkey_tip);
            View findViewById2 = childAt2.findViewById(R.id.rl_address);
            textView2.setText(string);
            textView2.setVisibility(View.VISIBLE);
            i = 1;
            findViewById2.setSelected(true);
            z3 = true;
        }
        boolean z4 = this.mPermissionType != i ? z3 : false;
        if (!z4) {
            return validateAddressRepeat(validateWeight(z4), viewGroup);
        }
        ((DoPermissionContract.View) this.mView).setWeightTipViewVisiblity(8);
        return z4;
    }

    private boolean validateWeight(boolean z) {
        int i = 0;
        for (Protocol.Key key : this.mSelectedKeyList) {
            i = (int) (i + key.getWeight());
        }
        if (i < this.mThreshold) {
            ((DoPermissionContract.View) this.mView).setWeightTipViewVisiblity(0);
            return true;
        }
        ((DoPermissionContract.View) this.mView).setWeightTipViewVisiblity(8);
        return z;
    }

    private boolean validateAddressRepeat(boolean z, ViewGroup viewGroup) {
        HashMap hashMap = new HashMap();
        for (int i = 0; i < this.mSelectedKeyList.size(); i++) {
            Protocol.Key key = this.mSelectedKeyList.get(i);
            List list = (List) hashMap.get(key.getAddress());
            if (list == null) {
                list = new ArrayList();
            }
            list.add(key);
            hashMap.put(key.getAddress(), list);
        }
        for (ByteString byteString : hashMap.keySet()) {
            List list2 = (List) hashMap.get(byteString);
            if (list2 != null && list2.size() > 1) {
                showRepeatTip(StringTronUtil.encode58Check(byteString.toByteArray()), viewGroup);
                z = true;
            } else {
                hideRepeatTip(StringTronUtil.encode58Check(byteString.toByteArray()), viewGroup);
            }
        }
        return z;
    }

    private void showRepeatTip(String str, ViewGroup viewGroup) {
        String string = ((DoPermissionContract.View) this.mView).getIContext().getResources().getString(R.string.address_repetition);
        int childCount = viewGroup.getChildCount();
        boolean z = true;
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            TextView textView = (TextView) childAt.findViewById(R.id.tv_addkey_tip);
            View findViewById = childAt.findViewById(R.id.rl_address);
            if (str.equals(((EditText) childAt.findViewById(R.id.et_key_address)).getText().toString())) {
                if (z) {
                    textView.setVisibility(View.GONE);
                    findViewById.setSelected(false);
                    z = false;
                } else {
                    textView.setVisibility(View.VISIBLE);
                    textView.setText(string);
                    findViewById.setSelected(true);
                }
            }
        }
    }

    private void hideRepeatTip(String str, ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            TextView textView = (TextView) childAt.findViewById(R.id.tv_addkey_tip);
            View findViewById = childAt.findViewById(R.id.rl_address);
            if (str.equals(((EditText) childAt.findViewById(R.id.et_key_address)).getText())) {
                textView.setVisibility(View.GONE);
                findViewById.setSelected(false);
            }
        }
    }

    @Override
    public boolean validatePermissionName(EditText editText, TextView textView, View view) {
        int i;
        String obj = editText.getText().toString();
        this.mPermissionNameStr = obj;
        boolean z = TextUtils.isEmpty(obj) || this.mPermissionNameStr.length() < 1 || this.mPermissionNameStr.length() > 32;
        String string = ((DoPermissionContract.View) this.mView).getIContext().getResources().getString(R.string.permission_name_error);
        if (!z) {
            String str = this.mModifyPermissionNameStr;
            if ((str != null && str.equals(this.mPermissionNameStr)) || (i = this.mPermissionType) == 0 || i == 1) {
                z = false;
            } else {
                ArrayList arrayList = new ArrayList();
                List<Protocol.Permission> activePermissionList = this.mAccount.getActivePermissionList();
                if (activePermissionList != null && activePermissionList.size() > 0) {
                    arrayList.addAll(activePermissionList);
                }
                if (arrayList.size() > 0) {
                    Iterator it = arrayList.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        } else if (this.mPermissionNameStr.equals(((Protocol.Permission) it.next()).getPermissionName())) {
                            string = ((DoPermissionContract.View) this.mView).getIContext().getResources().getString(R.string.permissionname_repeat);
                            z = true;
                            break;
                        }
                    }
                }
            }
        }
        if (!z) {
            textView.setVisibility(View.GONE);
            view.setSelected(false);
        } else {
            textView.setText(string);
            textView.setVisibility(View.VISIBLE);
            view.setSelected(true);
        }
        return z;
    }

    @Override
    public boolean validateOperations(TagFlowLayout tagFlowLayout, View view) {
        if (this.mSelectedPermissionList.size() <= 0 || (this.mSelectedPermissionList.size() == 1 && this.mSelectedPermissionList.get(0).getId() == -1)) {
            view.setVisibility(View.VISIBLE);
            return true;
        }
        view.setVisibility(View.GONE);
        return false;
    }

    @Override
    public void confirm(EditText editText, TextView textView, EditText editText2, TagFlowLayout tagFlowLayout, View view, ViewGroup viewGroup, View view2) {
        if (this.mAccount.getBalance() / 1000000.0d <= 100.0d) {
            ToastUtil.getInstance().showToast(((DoPermissionContract.View) this.mView).getIContext(), R.string.no_trx);
            return;
        }
        int i = this.mPermissionType;
        if (i == 0) {
            confirmOwner(editText, textView, editText2, tagFlowLayout, view, viewGroup, view2);
        } else if (i == 1) {
            confirmWithness(editText, textView, editText2, tagFlowLayout, view, viewGroup, view2);
        } else {
            confirmActivePermission(editText, textView, editText2, tagFlowLayout, view, viewGroup, view2);
        }
    }

    @Override
    public void clickPermissionFlowLayout() {
        List list = this.mSelectedPermissionList;
        if (list.size() == 1 && this.mSelectedPermissionList.get(0).getId() == -1) {
            list = new ArrayList();
        }
        SelectPermissionActivity.startActivity((Activity) ((DoPermissionContract.View) this.mView).getIContext(), list, this.mAllPermissionGroupArray);
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1 && intent != null && i == 10010) {
            this.mSelectedPermissionList.clear();
            this.mSelectedPermissionList.addAll(intent.getParcelableArrayListExtra("INTENT_PARAM_SELECTED_PERMISSION"));
            if (this.mSelectedPermissionList.size() == 0) {
                addPlaceholderPermissionData();
            }
            ((DoPermissionContract.View) this.mView).adjustHasMoreForListener();
            ((DoPermissionContract.View) this.mView).getTagAdapter().notifyDataChanged();
        }
    }

    private void confirmActivePermission(EditText editText, TextView textView, EditText editText2, TagFlowLayout tagFlowLayout, View view, ViewGroup viewGroup, View view2) {
        if (this.mAccount.getAddress() == null || this.mAccount.getAddress().size() <= 0) {
            ToastUtil.getInstance().showToast(((DoPermissionContract.View) this.mView).getIContext(), ((DoPermissionContract.View) this.mView).getIContext().getResources().getString(R.string.not_activate));
            return;
        }
        boolean validatePermissionName = validatePermissionName(editText, textView, view2);
        boolean validateOperations = validateOperations(tagFlowLayout, view);
        boolean validateThreshold = validateThreshold(editText2.getText().toString());
        boolean validateKey = validateKey(viewGroup);
        if (validatePermissionName || validateOperations || validateThreshold || validateKey) {
            return;
        }
        ThreadPoolManager.newInstance().addExecuteTask(new Runnable() {
            @Override
            public final void run() {
                lambda$confirmActivePermission$0();
            }
        });
    }

    public void lambda$confirmActivePermission$0() {
        List<Protocol.Permission> arrayList = new ArrayList<>();
        List<Protocol.Permission> activePermissionList = this.mAccount.getActivePermissionList();
        if (activePermissionList != null && activePermissionList.size() > 0) {
            arrayList.addAll(activePermissionList);
        }
        if (this.mActionType == DoPermissionActivity.PAGE_TYPE.TYPE_ADD.ordinal()) {
            addActivePermissionList(arrayList);
        } else {
            modifyActivePermissionList(arrayList);
        }
        handleExtention(TronAPI.createAccountPermissionUpdateContract(this.mAccount.getAddress().toByteArray(), getOwnerValue(), getWitnessValue(), arrayList));
    }

    private void handleExtention(GrpcAPI.TransactionExtention transactionExtention) {
        if (transactionExtention != null && transactionExtention.hasResult() && transactionExtention.getTransaction().toString().length() > 0) {
            confirmTransaction(transactionExtention.getTransaction());
        } else if (transactionExtention != null && ResultCodeContant.ERROR_40002.equals(transactionExtention.getResult().getCode().toString())) {
            ((DoPermissionContract.View) this.mView).ToastError(R.string.unsupport_operations);
        } else {
            ((DoPermissionContract.View) this.mView).ToastError(R.string.net_error);
        }
    }

    private boolean adjustMultipleSign() {
        boolean z = false;
        if (this.mAccount.getOwnerPermission() != null && this.mAccount.getOwnerPermission().getKeysList().size() > 1) {
            for (Protocol.Key key : this.mAccount.getOwnerPermission().getKeysList()) {
                if (StringTronUtil.encode58Check(this.mAccount.getAddress().toByteArray()).equals(StringTronUtil.encode58Check(key.getAddress().toByteArray())) && key.getWeight() < this.mAccount.getOwnerPermission().getThreshold()) {
                    z = true;
                }
            }
        }
        return z;
    }

    public void confirmOwner(EditText editText, TextView textView, final EditText editText2, TagFlowLayout tagFlowLayout, View view, ViewGroup viewGroup, View view2) {
        if (this.mAccount.getAddress() == null || this.mAccount.getAddress().size() <= 0) {
            ToastUtil.getInstance().showToast(((DoPermissionContract.View) this.mView).getIContext(), ((DoPermissionContract.View) this.mView).getIContext().getResources().getString(R.string.not_activate));
            return;
        }
        boolean validatePermissionName = validatePermissionName(editText, textView, view2);
        boolean validateOperations = isOwnerOrWithness() ? false : validateOperations(tagFlowLayout, view);
        boolean validateThreshold = validateThreshold(editText2.getText().toString());
        boolean validateKey = validateKey(viewGroup);
        if (validatePermissionName || validateOperations || validateThreshold || validateKey) {
            return;
        }
        ThreadPoolManager.newInstance().addExecuteTask(new Runnable() {
            @Override
            public final void run() {
                lambda$confirmOwner$1(editText2);
            }
        });
    }

    public void lambda$confirmOwner$1(EditText editText) {
        ArrayList arrayList = new ArrayList();
        List<Protocol.Permission> activePermissionList = this.mAccount.getActivePermissionList();
        if (activePermissionList != null && activePermissionList.size() > 0) {
            arrayList.addAll(activePermissionList);
        }
        handleExtention(TronAPI.createAccountPermissionUpdateContract(this.mAccount.getAddress().toByteArray(), getOwnerValueForOwner(editText), getWitnessValue(), arrayList));
    }

    private void confirmTransaction(Protocol.Transaction transaction) {
        if (transaction == null || !transaction.hasRawData()) {
            return;
        }
        boolean z = true;
        if (!this.mNotHasPermission) {
            try {
                z = WalletUtils.checkHaveOwnerPermissions(this.mSelectedAddress, this.mAccount.getOwnerPermission());
            } catch (PermissionException e) {
                LogUtils.e(e);
            }
        }
        ConfirmTransactionNewActivity.startActivity(((DoPermissionContract.View) this.mView).getIContext(), ParamBuildUtils.getPermissionUpdateTransactionParamBuilder(z, this.mWalletName, transaction, this.mModifyIndexInAll, this.mPermissionType));
    }

    public void confirmWithness(EditText editText, TextView textView, final EditText editText2, TagFlowLayout tagFlowLayout, View view, ViewGroup viewGroup, View view2) {
        if (this.mAccount.getAddress() == null || this.mAccount.getAddress().size() <= 0) {
            ToastUtil.getInstance().showToast(((DoPermissionContract.View) this.mView).getIContext(), ((DoPermissionContract.View) this.mView).getIContext().getResources().getString(R.string.not_activate));
            return;
        }
        boolean validatePermissionName = validatePermissionName(editText, textView, view2);
        boolean validateOperations = isOwnerOrWithness() ? false : validateOperations(tagFlowLayout, view);
        boolean validateThreshold = validateThreshold(editText2.getText().toString());
        boolean validateKey = validateKey(viewGroup);
        if (validatePermissionName || validateOperations || validateThreshold || validateKey) {
            return;
        }
        ThreadPoolManager.newInstance().addExecuteTask(new Runnable() {
            @Override
            public final void run() {
                lambda$confirmWithness$2(editText2);
            }
        });
    }

    public void lambda$confirmWithness$2(EditText editText) {
        ArrayList arrayList = new ArrayList();
        List<Protocol.Permission> activePermissionList = this.mAccount.getActivePermissionList();
        if (activePermissionList != null && activePermissionList.size() > 0) {
            arrayList.addAll(activePermissionList);
        }
        handleExtention(TronAPI.createAccountPermissionUpdateContract(this.mAccount.getAddress().toByteArray(), getOwnerValue(), getWitnessValueForWithness(editText), arrayList));
    }

    private void addActivePermissionList(List<Protocol.Permission> list) {
        Protocol.Permission permission;
        try {
            permission = Protocol.Permission.newBuilder().setPermissionName(this.mPermissionNameStr).setThreshold(this.mThreshold).setType(Protocol.Permission.PermissionType.Active).setOperations(WalletUtils.getPermissionOperationsBytes(getIdsForIndex())).addAllKeys(this.mSelectedKeyList).build();
        } catch (PermissionException e) {
            LogUtils.e(e);
            permission = null;
        }
        list.add(permission);
    }

    private void modifyActivePermissionList(List<Protocol.Permission> list) {
        Protocol.Permission permission;
        try {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(getIdsForIndex());
            arrayList.addAll(this.mHadUnSupportedOperationsIds);
            permission = Protocol.Permission.newBuilder().setPermissionName(this.mPermissionNameStr).setThreshold(this.mThreshold).setType(Protocol.Permission.PermissionType.Active).setOperations(WalletUtils.getPermissionOperationsBytes(arrayList)).addAllKeys(this.mSelectedKeyList).build();
        } catch (PermissionException e) {
            LogUtils.e(e);
            permission = null;
        }
        list.remove(this.mModifyIndexInActive);
        list.add(this.mModifyIndexInActive, permission);
    }

    private Protocol.Permission getOwnerValue() {
        Protocol.Permission ownerPermission = this.mAccount.getOwnerPermission();
        if (ownerPermission == null || TextUtils.isEmpty(ownerPermission.getPermissionName())) {
            return Protocol.Permission.newBuilder().setPermissionName("Owner").setThreshold(1L).setType(Protocol.Permission.PermissionType.Owner).addKeys(Protocol.Key.newBuilder().setAddress(this.mAccount.getAddress()).setWeight(1L).build()).build();
        }
        return ownerPermission;
    }

    private Protocol.Permission getOwnerValueForOwner(EditText editText) {
        this.mAccount.getOwnerPermission();
        ArrayList arrayList = new ArrayList();
        List<Protocol.Key> list = this.mSelectedKeyList;
        if (list != null) {
            arrayList.addAll(list);
        }
        try {
            this.mThreshold = Integer.parseInt(editText.getText().toString());
        } catch (Exception unused) {
        }
        return Protocol.Permission.newBuilder().setPermissionName(this.mPermissionNameStr).setThreshold(this.mThreshold).setType(Protocol.Permission.PermissionType.Owner).addAllKeys(arrayList).build();
    }

    private Protocol.Permission getWitnessValueForWithness(EditText editText) {
        this.mAccount.getWitnessPermission();
        ArrayList arrayList = new ArrayList();
        List<Protocol.Key> list = this.mSelectedKeyList;
        if (list != null) {
            arrayList.addAll(list);
        }
        try {
            this.mThreshold = Integer.parseInt(editText.getText().toString());
        } catch (Exception unused) {
        }
        return Protocol.Permission.newBuilder().setPermissionName(this.mPermissionNameStr).setThreshold(this.mThreshold).setType(Protocol.Permission.PermissionType.Witness).addAllKeys(arrayList).build();
    }

    private Protocol.Permission getWitnessValue() {
        GrpcAPI.WitnessList listWitnesses = TronAPI.listWitnesses();
        String encode58Check = StringTronUtil.encode58Check(this.mAccount.getAddress().toByteArray());
        if (listWitnesses != null) {
            boolean z = false;
            for (Protocol.Witness witness : listWitnesses.getWitnessesList()) {
                String encode58Check2 = StringTronUtil.encode58Check(witness.getAddress().toByteArray());
                if (encode58Check2 != null && encode58Check2.equals(encode58Check)) {
                    z = true;
                }
            }
            if (z) {
                Protocol.Permission witnessPermission = this.mAccount.getWitnessPermission();
                if (witnessPermission == null || TextUtils.isEmpty(witnessPermission.getPermissionName())) {
                    return Protocol.Permission.newBuilder().setPermissionName("Witness").setThreshold(1L).setType(Protocol.Permission.PermissionType.Witness).addKeys(Protocol.Key.newBuilder().setAddress(this.mAccount.getAddress()).setWeight(1L).build()).build();
                }
                return witnessPermission;
            }
            return null;
        }
        return null;
    }

    private List<Integer> getIdsForIndex() {
        ArrayList arrayList = new ArrayList();
        for (PermissionGroupBean.PermissionBean permissionBean : this.mSelectedPermissionList) {
            arrayList.add(Integer.valueOf(permissionBean.getId()));
        }
        return arrayList;
    }
}
