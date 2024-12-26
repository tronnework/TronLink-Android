package com.tron.wallet.business.confirm.core;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.core.ConfirmTransactionContract;
import com.tron.wallet.business.confirm.core.pending.ConfirmPendingFragment;
import com.tron.wallet.business.confirm.core.pending.State;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.DappParam;
import com.tron.wallet.common.components.ErrorEdiTextLayout;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.SoftHideKeyBoardUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.common.utils.keyboard.KeyboardHeightObserver;
import com.tron.wallet.common.utils.keyboard.KeyboardHeightProvider;
import com.tron.wallet.databinding.FgConfirmTransactionBinding;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
import org.tron.api.GrpcAPI;
public class ConfirmTransactionFragment extends BaseFragment<ConfirmTransactionPresenter, ConfirmTransactionModel> implements ConfirmTransactionContract.View, KeyboardHeightObserver {
    BaseParam baseParam;
    private FgConfirmTransactionBinding binding;
    Button btCancel;
    Button btSend;
    private ConfirmPendingFragment confirmPendingFragment;
    ErrorEdiTextLayout eetContent;
    EditText etNewPassword;
    View flMain;
    private KeyboardHeightProvider keyboardHeightProvider;
    private int oriBottomMargin;
    View rlBottom;
    View rlPassword;
    View root;
    TextView tvTitle;

    @Override
    public BaseParam getBaseParam() {
        return this.baseParam;
    }

    public void setParam(BaseParam baseParam) {
        this.baseParam = baseParam;
    }

    @Override
    public void onKeyboardHeightChanged(int i, int i2) {
        if (i > 0) {
            this.oriBottomMargin = i + UIUtils.dip2px(50.0f);
            setDialogBottom();
            SpAPI.THIS.setKeyBoardMarginHeight(this.oriBottomMargin);
        }
    }

    public void setDialogBottom() {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.rlBottom.getLayoutParams();
        layoutParams.bottomMargin = this.oriBottomMargin;
        this.rlBottom.setLayoutParams(layoutParams);
    }

    @Override
    protected void processData() {
        this.btSend.setEnabled(false);
        int keyBoardMarginHeight = SpAPI.THIS.getKeyBoardMarginHeight();
        this.oriBottomMargin = keyBoardMarginHeight;
        if (keyBoardMarginHeight > 0) {
            setDialogBottom();
        }
        KeyboardHeightProvider keyboardHeightProvider = new KeyboardHeightProvider(getActivity());
        this.keyboardHeightProvider = keyboardHeightProvider;
        keyboardHeightProvider.setKeyboardHeightObserver(this);
        this.root.post(new Runnable() {
            @Override
            public final void run() {
                lambda$processData$0();
            }
        });
        updateUI();
    }

    public void lambda$processData$0() {
        this.keyboardHeightProvider.start();
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setType(0);
        FgConfirmTransactionBinding inflate = FgConfirmTransactionBinding.inflate(layoutInflater, viewGroup, false);
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
        this.tvTitle = this.binding.tvTitle;
        this.etNewPassword = this.binding.etNewPassword;
        this.eetContent = this.binding.eetContent;
        this.btSend = this.binding.btSend;
        this.btCancel = this.binding.tvCancle;
        this.rlPassword = this.binding.rlPassword;
        this.root = this.binding.getRoot().findViewById(R.id.root);
        this.rlBottom = this.binding.rlBottom;
        this.flMain = this.binding.flMain;
    }

    public void setClick() {
        NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id != R.id.bt_send) {
                    if (id == R.id.iv_close_two || id == R.id.tv_cancle) {
                        etNewPassword.setText("");
                        SoftHideKeyBoardUtil.closeKeybord(getActivity());
                        ((ConfirmTransactionNewActivity) getActivity()).backUp(3);
                        return;
                    }
                    return;
                }
                if (baseParam != null && baseParam.getType() == 21) {
                    FirebaseAnalytics.getInstance(getIContext()).logEvent("nft_transfer_confirm", null);
                }
                if (!SpAPI.THIS.isCold() && !TronConfig.hasNet) {
                    ConfirmTransactionFragment confirmTransactionFragment = ConfirmTransactionFragment.this;
                    confirmTransactionFragment.toast(confirmTransactionFragment.getString(R.string.network_unusable));
                    getActivity().finish();
                    return;
                }
                ((ConfirmTransactionPresenter) mPresenter).send();
            }
        };
        this.binding.ivCloseTwo.setOnClickListener(noDoubleClickListener);
        this.binding.tvCancle.setOnClickListener(noDoubleClickListener);
        this.binding.btSend.setOnClickListener(noDoubleClickListener);
        this.binding.root.setOnClickListener(noDoubleClickListener);
    }

    @Override
    public Intent getIIntent() {
        return this.mContext.getIntent();
    }

    @Override
    public String getpassword() {
        return this.etNewPassword.getText().toString().trim();
    }

    @Override
    public void setButtonEnable(boolean z) {
        this.btSend.setEnabled(z);
    }

    @Override
    public void updateUI() {
        String str;
        try {
            if (this.baseParam == null) {
                return;
            }
            this.etNewPassword.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if (StringTronUtil.isNullOrEmpty(editable.toString().trim())) {
                        btSend.setEnabled(false);
                    } else {
                        btSend.setEnabled(true);
                    }
                    eetContent.hideError3WithOutSetBG();
                }
            });
            BaseParam baseParam = this.baseParam;
            if (baseParam instanceof DappParam) {
                str = ((DappParam) baseParam).getPassword();
                if (!StringTronUtil.isEmpty(str)) {
                    this.tvTitle.setText(getResources().getString(R.string.dapp_fast_mode_confirm));
                    this.tvTitle.setTextSize(1, 16.0f);
                    this.eetContent.setVisibility(View.GONE);
                }
            } else {
                str = null;
            }
            if (StringTronUtil.isEmpty(str)) {
                SoftHideKeyBoardUtil.showSoftInput(getActivity(), this.etNewPassword);
            } else {
                this.etNewPassword.setText(str);
            }
        } catch (Exception e) {
            LogUtils.e(e);
            SentryUtil.captureException(e);
        }
    }

    @Override
    public void showErrorText() {
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$showErrorText$1();
            }
        });
    }

    public void lambda$showErrorText$1() {
        this.eetContent.showError3WithOutSetBG();
    }

    @Override
    public void showErrorText(final String str) {
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$showErrorText$2(str);
            }
        });
    }

    public void lambda$showErrorText$2(String str) {
        this.eetContent.setTextError3(str);
        this.eetContent.showError3WithOutSetBG();
        if (this.eetContent.getErrorVisibity() == 8) {
            this.eetContent.showError3WithOutSetBG();
        }
    }

    @Override
    public boolean isActives() {
        return this.baseParam.isActives();
    }

    @Override
    public void setRootV(boolean z) {
        this.root.setVisibility(z ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showLoadingFragment() {
        this.rlBottom.setVisibility(View.GONE);
        this.flMain.setVisibility(View.VISIBLE);
        ConfirmPendingFragment confirmPendingFragment = ConfirmPendingFragment.getInstance(this.baseParam);
        this.confirmPendingFragment = confirmPendingFragment;
        if (confirmPendingFragment.isAdded()) {
            getChildFragmentManager().beginTransaction().show(this.confirmPendingFragment).commitAllowingStateLoss();
        } else {
            getChildFragmentManager().beginTransaction().add(R.id.fl_main, this.confirmPendingFragment).show(this.confirmPendingFragment).commitAllowingStateLoss();
        }
    }

    @Override
    public void updateLoadingFragment(final GrpcAPI.Return r2, final State state, final int i) {
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$updateLoadingFragment$3(state, r2, i);
            }
        });
    }

    public void lambda$updateLoadingFragment$3(State state, GrpcAPI.Return r4, int i) {
        ConfirmPendingFragment confirmPendingFragment = this.confirmPendingFragment;
        if (confirmPendingFragment != null) {
            confirmPendingFragment.update(state, r4 == null ? null : r4.toByteArray(), this.baseParam, i);
        }
    }

    public void update() {
        if (this.mPresenter != 0) {
            ((ConfirmTransactionPresenter) this.mPresenter).onStart();
        }
        updateUI();
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        ((ConfirmTransactionPresenter) this.mPresenter).onActivityResult(i, i2, intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        KeyboardHeightProvider keyboardHeightProvider = this.keyboardHeightProvider;
        if (keyboardHeightProvider != null) {
            keyboardHeightProvider.close();
        }
    }

    @Override
    public void onKeyDownChild(int i, KeyEvent keyEvent) {
        ConfirmPendingFragment confirmPendingFragment = this.confirmPendingFragment;
        if (confirmPendingFragment != null) {
            confirmPendingFragment.onKeyDownChild(i, keyEvent);
        }
    }
}
