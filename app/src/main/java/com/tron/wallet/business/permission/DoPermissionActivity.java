package com.tron.wallet.business.permission;

import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.SpannableString;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.permission.DoPermissionContract;
import com.tron.wallet.common.bean.PermissionGroupBean;
import com.tron.wallet.common.components.ShadowDrawable;
import com.tron.wallet.common.components.TrimEditText;
import com.tron.wallet.common.components.dialog.ModifyPermissionExitDialog;
import com.tron.wallet.common.components.flowlayout.FlowLayout;
import com.tron.wallet.common.components.flowlayout.TagAdapter;
import com.tron.wallet.common.components.flowlayout.TagFlowLayout;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.DensityUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.databinding.AcDopermissionBinding;
import com.tronlinkpro.wallet.R;
import io.reactivex.functions.Consumer;
import java.util.List;
import java.util.Set;
public class DoPermissionActivity extends BaseActivity<DoPermissionPresenter, EmptyModel> implements DoPermissionContract.View {
    public static final String INTENT_PARAM_ADDRESS = "intent_param_address";
    public static final String INTENT_PARAM_BYTE = "intent_param_byte";
    public static final String INTENT_PARAM_CONTROL_ADDRESS = "intent_param_control_address";
    public static final String INTENT_PARAM_INDEX = "intent_param_index";
    public static final String INTENT_PARAM_NAME = "intent_param_name";
    public static final String INTENT_PARAM_TYPE = "intent_param_type";
    public static final String INTENT_PARAM_WITNESS = "intent_param_witness";
    public static final String INTENT_PERMISSION_NOT_PERMISSION = "INTENT_PERMISSION_NOT_PERMISSION";
    public static final String INTENT_PERMISSION_TYPE = "INTENT_PERMISSION_TYPE";
    private static final String TAG = "DoPermissionActivity";
    private AcDopermissionBinding binding;
    View mAddKeyBtnView;
    ViewGroup mAddKeyContainerLayout;
    TextView mAddKeyTipTv;
    View mConfirmShadowView;
    TextView mControlAddressTv;
    private LayoutInflater mInflater;
    View mKeysLightView;
    View mKeysNormalView;
    View mMoreView;
    TextView mOperationsDescTv;
    TagFlowLayout mOperationsFlowLayout;
    View mOperationsLabels;
    View mOperationsLayout;
    View mOperationsLightView;
    View mOperationsModifyView;
    View mOperationsNormalView;
    View mOperationsTipView;
    View mPermissionNameClearView;
    EditText mPermissionNameEt;
    View mPermissionNameLightView;
    View mPermissionNameNormalView;
    TextView mPermissionNameTipView;
    View mPermissionNameTitleView;
    TextView mPermissionTypeTv;
    private RxManager mRxManager = new RxManager();
    private TagAdapter mTagAdapter;
    EditText mThresholdEt;
    View mThresholdLightView;
    View mThresholdNormalView;
    View mThresholdTipView;
    TextView mThresholdTv;
    View mWeightTipView;

    public enum PAGE_TYPE {
        TYPE_ADD,
        TYPE_MODIFY
    }

    @Override
    public TagAdapter getTagAdapter() {
        return this.mTagAdapter;
    }

    @Override
    protected void setLayout() {
        AcDopermissionBinding inflate = AcDopermissionBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 1);
        this.mInflater = LayoutInflater.from(this);
        mappingId();
    }

    public void mappingId() {
        this.mOperationsFlowLayout = this.binding.flOperations;
        this.mPermissionNameEt = this.binding.etPermissionName;
        this.mPermissionNameClearView = this.binding.ivClear;
        this.mPermissionNameTipView = this.binding.tvPermissionnameTip;
        this.mOperationsTipView = this.binding.tvOperationsTip;
        this.mAddKeyContainerLayout = this.binding.llAddkeysContainer;
        this.mAddKeyBtnView = this.binding.rlAddKeyButton;
        this.mThresholdEt = this.binding.etThreshold;
        this.mThresholdTv = this.binding.etThresholdOwner;
        this.mThresholdTipView = this.binding.tvThresholdTip;
        this.mWeightTipView = this.binding.tvWeightTip;
        this.mConfirmShadowView = this.binding.rlConfirmShadow;
        this.mPermissionNameLightView = this.binding.viewPermissionnameLight;
        this.mPermissionNameNormalView = this.binding.viewPermissionnameNormal;
        this.mThresholdLightView = this.binding.viewThresholdLight;
        this.mThresholdNormalView = this.binding.viewThresholdNormal;
        this.mOperationsLightView = this.binding.viewOperationsLight;
        this.mOperationsNormalView = this.binding.viewOperationsNormal;
        this.mKeysLightView = this.binding.viewKeysLight;
        this.mKeysNormalView = this.binding.viewKeysNormal;
        this.mPermissionNameTitleView = this.binding.llPermissionnameTitle;
        this.mControlAddressTv = this.binding.etControlAddress;
        this.mOperationsLayout = this.binding.rlOperations;
        this.mOperationsLabels = this.binding.llOperationsLabels;
        this.mPermissionTypeTv = this.binding.tvPermissionType;
        this.mAddKeyTipTv = this.binding.tvAddkeyDesc;
        this.mMoreView = this.binding.rlMore;
        this.mOperationsDescTv = this.binding.tvOperationsDesc;
        this.mOperationsModifyView = this.binding.ivModifyOperations;
    }

    @Override
    protected void processData() {
        setClick();
        SpannableString spannableString = new SpannableString(getResources().getString(R.string.threshold));
        spannableString.setSpan(new AbsoluteSizeSpan(12, true), 0, spannableString.length(), 33);
        this.mThresholdEt.setHint(new SpannedString(spannableString));
        initOperationsFlowLayout();
        initListener();
        initLightClickListener();
        initRxEvent();
        ShadowDrawable.setShadowDrawable(this.mConfirmShadowView, DensityUtils.dp2px(this, 10.0f), 856176839, 20, 0, DensityUtils.dp2px(this, 2.0f));
    }

    @Override
    public void adjustHasMoreForListener() {
        this.mOperationsFlowLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mOperationsFlowLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                if (mOperationsFlowLayout.isBeyondLimitLines) {
                    mMoreView.setVisibility(View.VISIBLE);
                } else {
                    mMoreView.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void refreshViewForPermissionNameThreshold(String str, long j) {
        this.mPermissionNameEt.setText(str);
        this.mPermissionNameEt.setSelection(str.length());
        this.mThresholdEt.setText(String.valueOf(j));
        this.mThresholdTv.setText(String.valueOf(j));
    }

    @Override
    public void removeKeyContainerSubViews() {
        this.mAddKeyContainerLayout.removeAllViews();
    }

    public static void startActivity(Activity activity, PAGE_TYPE page_type, int i, byte[] bArr, String str, String str2, int i2, int i3, String str3, boolean z) {
        Intent intent = new Intent();
        intent.putExtra(INTENT_PARAM_TYPE, page_type.ordinal());
        intent.putExtra(INTENT_PARAM_INDEX, i);
        intent.putExtra(INTENT_PARAM_WITNESS, i3);
        intent.putExtra(INTENT_PARAM_BYTE, bArr);
        intent.putExtra(INTENT_PARAM_NAME, str);
        intent.putExtra(INTENT_PARAM_CONTROL_ADDRESS, str2);
        intent.putExtra(INTENT_PERMISSION_TYPE, i2);
        intent.putExtra(INTENT_PARAM_ADDRESS, str3);
        intent.putExtra(INTENT_PERMISSION_NOT_PERMISSION, z);
        intent.setClass(activity, DoPermissionActivity.class);
        activity.startActivityForResult(intent, 2001);
    }

    private void initRxEvent() {
        this.mRxManager.on(Event.BroadcastSuccess, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRxEvent$0(obj);
            }
        });
    }

    public void lambda$initRxEvent$0(Object obj) throws Exception {
        Intent intent = new Intent();
        intent.putExtra(INTENT_PARAM_TYPE, getIntent().getIntExtra(INTENT_PARAM_TYPE, 0));
        setResult(-1, intent);
        this.mRxManager.clear();
        finish();
    }

    @Override
    public void initAdapter(List<PermissionGroupBean.PermissionBean> list) {
        this.mTagAdapter = new TagAdapter<PermissionGroupBean.PermissionBean>(list) {
            @Override
            public View getView(FlowLayout flowLayout, int i, PermissionGroupBean.PermissionBean permissionBean) {
                TextView textView = (TextView) mInflater.inflate(R.layout.flowlayout_operations, (ViewGroup) flowLayout, false);
                if (permissionBean.getId() == -1) {
                    showPlaceHolder(textView);
                } else {
                    String display_name_zh = permissionBean.getDisplay_name_zh();
                    if (LanguageUtils.languageEN(AppContextUtil.getContext())) {
                        display_name_zh = permissionBean.getDisplay_name_en();
                    }
                    textView.setText(display_name_zh);
                    textView.setBackground(getResources().getDrawable(R.drawable.ripple_blue135_6_blue135));
                }
                return textView;
            }

            private void showPlaceHolder(TextView textView) {
                textView.setText(getResources().getString(R.string.add_permissions));
                textView.setBackground(getResources().getDrawable(R.drawable.ripple_blue135_10));
            }
        };
    }

    private void initListener() {
        this.mPermissionNameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable.toString())) {
                    mPermissionNameClearView.setVisibility(View.INVISIBLE);
                } else {
                    mPermissionNameClearView.setVisibility(View.VISIBLE);
                }
                ((DoPermissionPresenter) mPresenter).validatePermissionName(mPermissionNameEt, mPermissionNameTipView, mPermissionNameTitleView);
            }
        });
        this.mPermissionNameClearView.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$initListener$1(view);
            }
        });
        this.mPermissionNameEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    return;
                }
                ((DoPermissionPresenter) mPresenter).validatePermissionName(mPermissionNameEt, mPermissionNameTipView, mPermissionNameTitleView);
            }
        });
        this.mThresholdEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                ((DoPermissionPresenter) mPresenter).validateThreshold(mThresholdEt.getText().toString());
            }
        });
        findViewById(R.id.tv_confirm).setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                ((DoPermissionPresenter) mPresenter).confirm(mPermissionNameEt, mPermissionNameTipView, mThresholdEt, mOperationsFlowLayout, mOperationsTipView, mAddKeyContainerLayout, mPermissionNameTitleView);
            }
        });
    }

    public void lambda$initListener$1(View view) {
        this.mPermissionNameEt.setText("");
    }

    private void initOperationsFlowLayout() {
        this.mOperationsFlowLayout.setAdapter(this.mTagAdapter);
        this.mOperationsFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> set) {
                ((DoPermissionPresenter) mPresenter).clickPermissionFlowLayout();
            }
        });
    }

    @Override
    public void notifPermissionAdapter() {
        this.mTagAdapter.notifyDataChanged();
    }

    public void setClick() {
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id != R.id.iv_modify_operations) {
                    if (id == R.id.rl_add_key_button) {
                        addKeyViewToContainer(null, 0L, 0);
                        switchLightPoint(3);
                        return;
                    } else if (id != R.id.rl_more) {
                        return;
                    }
                }
                ((DoPermissionPresenter) mPresenter).clickPermissionFlowLayout();
            }
        };
        this.binding.rlAddKeyButton.setOnClickListener(noDoubleClickListener2);
        this.binding.etControlAddress.setOnClickListener(noDoubleClickListener2);
        this.binding.ivModifyOperations.setOnClickListener(noDoubleClickListener2);
        this.binding.rlMore.setOnClickListener(noDoubleClickListener2);
    }

    @Override
    public void setThresholdTipViewVisiblity(int i) {
        this.mThresholdTipView.setVisibility(i);
        if (i == 0) {
            this.mThresholdEt.setSelected(true);
        } else {
            this.mThresholdEt.setSelected(false);
        }
    }

    @Override
    public void setWeightTipViewVisiblity(int i) {
        this.mWeightTipView.setVisibility(i);
    }

    @Override
    public void showPermissionClear() {
        this.mPermissionNameClearView.setVisibility(View.VISIBLE);
    }

    @Override
    public void addKeyViewToContainer(String str, long j, int i) {
        if (checkContainerIsFull()) {
            return;
        }
        final View inflate = this.mInflater.inflate(R.layout.layout_add_key, this.mAddKeyContainerLayout, false);
        inflate.findViewById(R.id.iv_del_keys).setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$addKeyViewToContainer$2(inflate, view);
            }
        });
        if (i == 1) {
            inflate.findViewById(R.id.iv_del_keys).setVisibility(View.GONE);
            inflate.findViewById(R.id.view_border).setVisibility(View.GONE);
        }
        initAddressEt(str, inflate);
        initWeightEt(j, inflate, i);
        this.mAddKeyContainerLayout.addView(inflate);
        adjustContainerLogic();
    }

    public void lambda$addKeyViewToContainer$2(View view, View view2) {
        this.mAddKeyContainerLayout.removeView(view);
        adjustContainerLogic();
    }

    private void initWeightEt(long j, View view, int i) {
        EditText editText = (EditText) view.findViewById(R.id.et_weight);
        if (j != 0) {
            editText.setText(String.valueOf(j));
        }
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public final boolean onTouch(View view2, MotionEvent motionEvent) {
                boolean lambda$initWeightEt$3;
                lambda$initWeightEt$3 = lambda$initWeightEt$3(view2, motionEvent);
                return lambda$initWeightEt$3;
            }
        });
        if (i == 1) {
            editText.setTextColor(getResources().getColor(R.color.gray_02_50));
            editText.setEnabled(false);
            editText.setFocusable(false);
            editText.setKeyListener(null);
            return;
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                ((DoPermissionPresenter) mPresenter).validateKey(mAddKeyContainerLayout);
            }
        });
    }

    public boolean lambda$initWeightEt$3(View view, MotionEvent motionEvent) {
        switchLightPoint(3);
        return false;
    }

    @Override
    public void refreshControlAddressValue(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mControlAddressTv.setText(str);
    }

    @Override
    public void refreshViewForPermissionType(int i, boolean z) {
        if (i != 1 && i != 0) {
            if (z) {
                this.mAddKeyTipTv.setText(getResources().getString(R.string.add_key_desc) + getResources().getString(R.string.add_key_desc_nothas_permission));
                return;
            }
            return;
        }
        this.mOperationsDescTv.setVisibility(View.VISIBLE);
        this.mOperationsModifyView.setVisibility(View.GONE);
        if (i != 1) {
            if (i == 0) {
                this.mPermissionTypeTv.setText(R.string.permission_type_owner);
                this.mOperationsDescTv.setText(getResources().getString(R.string.operstions_desc_owner));
                return;
            }
            return;
        }
        this.mThresholdTv.setVisibility(View.VISIBLE);
        this.mThresholdEt.setVisibility(View.GONE);
        this.mPermissionTypeTv.setText(R.string.permission_type_witness);
        this.mOperationsDescTv.setText(getResources().getString(R.string.operstions_desc_witness));
        this.mAddKeyTipTv.setText(R.string.add_key_desc_witness);
        this.mAddKeyBtnView.setVisibility(View.GONE);
    }

    private void initAddressEt(String str, View view) {
        TrimEditText trimEditText = (TrimEditText) view.findViewById(R.id.et_key_address);
        if (!TextUtils.isEmpty(str)) {
            trimEditText.setText(str);
        }
        trimEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                ((DoPermissionPresenter) mPresenter).validateKey(mAddKeyContainerLayout);
            }
        });
        trimEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public final void onFocusChange(View view2, boolean z) {
                lambda$initAddressEt$4(view2, z);
            }
        });
        trimEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public final boolean onTouch(View view2, MotionEvent motionEvent) {
                boolean lambda$initAddressEt$5;
                lambda$initAddressEt$5 = lambda$initAddressEt$5(view2, motionEvent);
                return lambda$initAddressEt$5;
            }
        });
    }

    public void lambda$initAddressEt$4(View view, boolean z) {
        if (z || this.mPresenter == 0) {
            return;
        }
        ((DoPermissionPresenter) this.mPresenter).validateKey(this.mAddKeyContainerLayout);
    }

    public boolean lambda$initAddressEt$5(View view, MotionEvent motionEvent) {
        switchLightPoint(3);
        return false;
    }

    private void adjustContainerLogic() {
        if (checkContainerIsFull()) {
            this.mAddKeyBtnView.setVisibility(View.GONE);
        } else {
            this.mAddKeyBtnView.setVisibility(View.VISIBLE);
        }
    }

    private boolean checkContainerIsFull() {
        return this.mAddKeyContainerLayout.getChildCount() >= 5;
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        showExitDialog();
    }

    public void switchLightPoint(int i) {
        allShowNormal();
        if (i == 0) {
            this.mPermissionNameLightView.setVisibility(View.VISIBLE);
            this.mPermissionNameNormalView.setVisibility(View.INVISIBLE);
        } else if (i == 1) {
            this.mOperationsLightView.setVisibility(View.VISIBLE);
            this.mOperationsNormalView.setVisibility(View.INVISIBLE);
        } else if (i == 2) {
            this.mThresholdLightView.setVisibility(View.VISIBLE);
            this.mThresholdNormalView.setVisibility(View.INVISIBLE);
        } else if (i != 3) {
        } else {
            this.mKeysLightView.setVisibility(View.VISIBLE);
            this.mKeysNormalView.setVisibility(View.INVISIBLE);
        }
    }

    private void allShowNormal() {
        this.mPermissionNameLightView.setVisibility(View.GONE);
        this.mPermissionNameNormalView.setVisibility(View.VISIBLE);
        this.mKeysLightView.setVisibility(View.GONE);
        this.mKeysNormalView.setVisibility(View.VISIBLE);
        this.mThresholdLightView.setVisibility(View.GONE);
        this.mThresholdNormalView.setVisibility(View.VISIBLE);
        this.mOperationsLightView.setVisibility(View.GONE);
        this.mOperationsNormalView.setVisibility(View.VISIBLE);
    }

    private void initClickPermissionListener() {
        this.mPermissionNameEt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                boolean lambda$initClickPermissionListener$6;
                lambda$initClickPermissionListener$6 = lambda$initClickPermissionListener$6(view, motionEvent);
                return lambda$initClickPermissionListener$6;
            }
        });
    }

    public boolean lambda$initClickPermissionListener$6(View view, MotionEvent motionEvent) {
        switchLightPoint(0);
        return false;
    }

    private void initClickThresholdListener() {
        this.mThresholdEt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                boolean lambda$initClickThresholdListener$7;
                lambda$initClickThresholdListener$7 = lambda$initClickThresholdListener$7(view, motionEvent);
                return lambda$initClickThresholdListener$7;
            }
        });
    }

    public boolean lambda$initClickThresholdListener$7(View view, MotionEvent motionEvent) {
        switchLightPoint(2);
        return false;
    }

    private void initLightClickListener() {
        initClickPermissionListener();
        initClickThresholdListener();
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        ((DoPermissionPresenter) this.mPresenter).onActivityResult(i, i2, intent);
        if (i2 == -1 && intent != null && i == 10010) {
            ((DoPermissionPresenter) this.mPresenter).validateOperations(this.mOperationsFlowLayout, this.mOperationsTipView);
        }
        super.onActivityResult(i, i2, intent);
    }

    @Override
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i == 4 && keyEvent.getAction() == 1) {
            showExitDialog();
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    private void showExitDialog() {
        new ModifyPermissionExitDialog(this.mContext).setOnExitListener(new ModifyPermissionExitDialog.OnExitListener() {
            @Override
            public void onClickExit(View view) {
                finish();
            }
        }).show();
    }
}
