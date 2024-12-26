package com.tron.wallet.business.customtokens;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.wallet.business.customtokens.CustomTokensContract;
import com.tron.wallet.business.customtokens.bean.CustomTokenBean;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.ErrorEdiTextLayout;
import com.tron.wallet.common.components.TrimEditText;
import com.tron.wallet.common.components.qr.ScannerActivity;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.interfaces.PermissionInterface;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.PermissionHelper;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.AcCustomTokensBinding;
import com.tronlinkpro.wallet.R;
public class CustomTokensActivity extends BaseActivity<CustomTokensPresenter, CustomTokensModel> implements CustomTokensContract.View, PermissionInterface {
    private static final String TOKEN_ADDRESS = "tokenAddress";
    private AcCustomTokensBinding binding;
    Button btnNext;
    private CustomTokenBean customTokenBean;
    ErrorEdiTextLayout eetAddress;
    ErrorEdiTextLayout eetName;
    ErrorEdiTextLayout eetSymbol;
    TrimEditText etAddress;
    TrimEditText etDecimal;
    TrimEditText etName;
    TrimEditText etSymbol;
    TrimEditText etType;
    ImageView ivScan;
    View llEtName;
    View llEtSymbol;
    View llOthers;
    private PermissionHelper mPermissionHelper;
    private boolean showScanImage = true;
    private TokenBean tokenBean;
    TextView tvDetails;
    View viewRoot;

    @Override
    public int getPermissionsRequestCode() {
        return IntentIntegrator.REQUEST_CODE;
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, CustomTokensActivity.class);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, String str) {
        Intent intent = new Intent();
        intent.putExtra(TOKEN_ADDRESS, str);
        intent.setClass(context, CustomTokensActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        AcCustomTokensBinding inflate = AcCustomTokensBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 1);
        mappingId();
        setClick();
        setTitle(getResources().getString(R.string.custom_tokens));
        setCommonTitle2(getString(R.string.step_1_2));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.viewRoot = this.binding.root;
        this.btnNext = this.binding.btnNext;
        this.llOthers = this.binding.llOthers;
        this.tvDetails = this.binding.tvDetails;
        this.eetAddress = this.binding.eetTokenAddress;
        this.etAddress = this.binding.etTokenAddress;
        this.ivScan = this.binding.ivScan;
        this.eetSymbol = this.binding.eetTokenSymbol;
        this.llEtSymbol = this.binding.llEtTokenSymbol;
        this.etSymbol = this.binding.etTokenSymbol;
        this.eetName = this.binding.eetTokenName;
        this.llEtName = this.binding.llEtTokenName;
        this.etName = this.binding.etTokenName;
        this.etDecimal = this.binding.etTokenDecimal;
        this.etType = this.binding.etTokenType;
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }

    @Override
    protected void processData() {
        this.mPermissionHelper = new PermissionHelper(this, this);
        setTokenDetailsTextView();
        setViewListener();
        String stringExtra = getIntent().getStringExtra(TOKEN_ADDRESS);
        if (StringTronUtil.isEmpty(stringExtra)) {
            return;
        }
        this.etAddress.setText(stringExtra);
    }

    private void setTokenDetailsTextView() {
        String string = getResources().getString(R.string.custom_tokens_detail);
        String string2 = getResources().getString(R.string.custom_tokens_detail_end);
        SpannableString spannableString = new SpannableString(string2);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.blue_3c)), 0, string2.length(), 33);
        spannableString.setSpan(new ClickableSpanNoUnderLine() {
            @Override
            public void onClick(View view) {
                UIUtils.toCustomTokenDetails(CustomTokensActivity.this);
            }
        }, 0, string2.length(), 33);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
        spannableStringBuilder.append((CharSequence) spannableString);
        this.tvDetails.setText(spannableStringBuilder);
        this.tvDetails.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void setViewListener() {
        this.viewRoot.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            private int preHeight;

            @Override
            public void onGlobalLayout() {
                if (this.preHeight == 0) {
                    this.preHeight = viewRoot.getHeight();
                    return;
                }
                Rect rect = new Rect();
                viewRoot.getWindowVisibleDisplayFrame(rect);
                int i = rect.bottom - rect.top;
                if (i - this.preHeight > UIUtils.dip2px(100.0f) && etAddress.hasFocus()) {
                    refreshTokenInfo(true);
                }
                this.preHeight = i;
            }
        });
        this.etAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public final void onFocusChange(View view, boolean z) {
                lambda$setViewListener$0(view, z);
            }
        });
        this.etAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                refreshTokenInfo(false);
            }
        });
        this.etSymbol.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public final void onFocusChange(View view, boolean z) {
                CustomTokensActivity.lambda$setViewListener$1(view, z);
            }
        });
        this.etSymbol.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkAndSetNextButtonEnableState();
            }
        });
        this.etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public final void onFocusChange(View view, boolean z) {
                CustomTokensActivity.lambda$setViewListener$2(view, z);
            }
        });
    }

    public void lambda$setViewListener$0(View view, boolean z) {
        if (z) {
            AnalyticsHelper.logEvent(AnalyticsHelper.CustomTokenPage.CLICK_CUSTOM_TOKEN_PAGE_EDIT_CONTRACT_ADDRESS);
        } else {
            refreshTokenInfo(true);
        }
    }

    public static void lambda$setViewListener$1(View view, boolean z) {
        if (z) {
            AnalyticsHelper.logEvent(AnalyticsHelper.CustomTokenPage.CLICK_CUSTOM_TOKEN_PAGE_EDIT_SYMBOL);
        }
    }

    public static void lambda$setViewListener$2(View view, boolean z) {
        if (z) {
            AnalyticsHelper.logEvent(AnalyticsHelper.CustomTokenPage.CLICK_CUSTOM_TOKEN_PAGE_EDIT_NAME);
        }
    }

    public void setClick() {
        NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id == R.id.btn_next) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.CustomTokenPage.CLICK_CUSTOM_TOKEN_PAGE_NEXT);
                    tokenBean.setName(etName.getText().toString().trim());
                    tokenBean.setShortName(etSymbol.getText().toString().trim());
                    CustomTokensActivity customTokensActivity = CustomTokensActivity.this;
                    CustomTokensConfirmActivity.startActivity(customTokensActivity, customTokensActivity.customTokenBean);
                } else if (id != R.id.iv_scan) {
                    if (id != R.id.tv_details) {
                        return;
                    }
                    AnalyticsHelper.logEvent(AnalyticsHelper.CustomTokenPage.CLICK_CUSTOM_TOKEN_PAGE_DETAILS);
                    UIUtils.toCustomTokenDetails(CustomTokensActivity.this);
                } else if (showScanImage) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.CustomTokenPage.CLICK_CUSTOM_TOKEN_PAGE_SCAN);
                    CustomTokensActivity customTokensActivity2 = CustomTokensActivity.this;
                    CustomTokensActivity customTokensActivity3 = CustomTokensActivity.this;
                    customTokensActivity2.mPermissionHelper = new PermissionHelper(customTokensActivity3, customTokensActivity3);
                    mPermissionHelper.requestPermissions();
                } else {
                    etAddress.setText("");
                }
            }
        };
        this.binding.btnNext.setOnClickListener(noDoubleClickListener);
        this.binding.tvDetails.setOnClickListener(noDoubleClickListener);
        this.binding.ivScan.setOnClickListener(noDoubleClickListener);
    }

    private void setEditViewEnable(View view, TrimEditText trimEditText, boolean z) {
        trimEditText.setEnabled(z);
        view.setBackground(getResources().getDrawable(z ? R.drawable.roundborder_eeeeee : R.drawable.roundborder_f7f8fa_5));
    }

    private void updateTokenDetailsView() {
        this.eetName.hideError3();
        this.eetSymbol.hideError3();
        TokenBean tokenBean = this.tokenBean;
        if (tokenBean != null) {
            this.etName.setText(tokenBean.getName());
            setEditViewEnable(this.llEtName, this.etName, this.customTokenBean.getStatus() == 0 && StringTronUtil.isEmpty(this.tokenBean.getName()));
            TrimEditText trimEditText = this.etName;
            trimEditText.setTextColor(trimEditText.isEnabled() ? getResources().getColor(R.color.black_23) : getResources().getColor(R.color.gray_9B));
            InputFilter[] inputFilterArr = {new InputFilter.LengthFilter(30)};
            TrimEditText trimEditText2 = this.etName;
            if (!trimEditText2.isEnabled()) {
                inputFilterArr = new InputFilter[0];
            }
            trimEditText2.setFilters(inputFilterArr);
            this.etSymbol.setText(this.tokenBean.getShortName());
            setEditViewEnable(this.llEtSymbol, this.etSymbol, this.customTokenBean.getStatus() == 0 && StringTronUtil.isEmpty(this.tokenBean.getShortName()));
            TrimEditText trimEditText3 = this.etSymbol;
            trimEditText3.setTextColor(trimEditText3.isEnabled() ? getResources().getColor(R.color.black_23) : getResources().getColor(R.color.gray_9B));
            InputFilter[] inputFilterArr2 = {new InputFilter.LengthFilter(10)};
            TrimEditText trimEditText4 = this.etSymbol;
            if (!trimEditText4.isEnabled()) {
                inputFilterArr2 = new InputFilter[0];
            }
            trimEditText4.setFilters(inputFilterArr2);
            TrimEditText trimEditText5 = this.etDecimal;
            trimEditText5.setText("" + this.tokenBean.getPrecision());
            this.etType.setText(this.tokenBean.getType() == 2 ? TronConfig.TRC20 : "TRC721");
            return;
        }
        this.etName.setText("");
        setEditViewEnable(this.llEtName, this.etName, false);
        this.etSymbol.setText("");
        setEditViewEnable(this.llEtSymbol, this.etSymbol, false);
        this.etDecimal.setText("");
        this.etType.setText("");
    }

    public void checkAndSetNextButtonEnableState() {
        CustomTokenBean customTokenBean = this.customTokenBean;
        boolean z = false;
        boolean z2 = customTokenBean != null && this.tokenBean != null && customTokenBean.getStatus() == 0 && StringTronUtil.equals(this.etAddress.getText().toString().trim(), this.tokenBean.getContractAddress());
        if (!z2 || !StringTronUtil.isEmpty(this.etSymbol.getText().toString().trim())) {
            z = z2;
        }
        this.btnNext.setEnabled(z);
    }

    private boolean hasShowDetailsView() {
        return this.llOthers.getVisibility() == 0;
    }

    @Override
    public void updateToken(CustomTokenBean customTokenBean, String str) {
        if (StringTronUtil.equals(this.etAddress.getText().toString().trim(), str)) {
            TokenBean assetInfo = customTokenBean.getAssetInfo();
            this.customTokenBean = customTokenBean;
            this.tokenBean = assetInfo;
            int status = customTokenBean.getStatus();
            int i = 0;
            if (status != 0) {
                if (status == 1) {
                    i = R.string.token_address_invalid;
                } else if (status == 2) {
                    i = R.string.not_contract_address;
                } else if (status == 3) {
                    i = R.string.token_recorded;
                } else if (status == 4) {
                    i = R.string.token_added;
                } else if (status == 5 && (!customTokenBean.isBalanceFunction() || !customTokenBean.isTransferEvent())) {
                    i = R.string.contract_address_not_token;
                }
            } else if (this.tokenBean != null) {
                this.llOthers.setVisibility(View.VISIBLE);
            }
            if (i != 0) {
                this.eetAddress.setTextError3(getResources().getString(i));
                this.eetAddress.showError3WithOutSetBG();
            } else {
                this.eetAddress.hideError3();
            }
            if (hasShowDetailsView()) {
                updateTokenDetailsView();
            }
            checkAndSetNextButtonEnableState();
        }
    }

    @Override
    public void showNetError(String str) {
        if (StringTronUtil.equals(this.etAddress.getText().toString().trim(), str)) {
            ToastError(getResources().getString(R.string.time_out));
        }
    }

    private void resetTokenDetailsView() {
        this.customTokenBean = null;
        this.tokenBean = null;
        if (hasShowDetailsView()) {
            updateTokenDetailsView();
        }
        checkAndSetNextButtonEnableState();
    }

    public void refreshTokenInfo(boolean z) {
        String trim = this.etAddress.getText().toString().trim();
        boolean isEmpty = StringTronUtil.isEmpty(trim);
        this.showScanImage = isEmpty;
        this.ivScan.setImageResource(isEmpty ? R.mipmap.qr_scan_import_wallet : R.mipmap.ic_delete_gray);
        if (StringTronUtil.isEmpty(trim)) {
            this.eetAddress.hideError3();
            resetTokenDetailsView();
        } else if (!StringTronUtil.isAddressValid(trim)) {
            if (z) {
                this.eetAddress.setTextError3(getString(R.string.token_address_invalid));
                this.eetAddress.showError3WithOutSetBG();
                resetTokenDetailsView();
            }
        } else {
            this.eetAddress.hideError3();
            CustomTokenBean customTokenBean = this.customTokenBean;
            if (customTokenBean != null && StringTronUtil.equals(customTokenBean.getTokenAddress(), trim)) {
                updateToken(this.customTokenBean, trim);
            } else {
                ((CustomTokensPresenter) this.mPresenter).getToken(trim);
            }
        }
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 49374) {
            IntentResult parseActivityResult = IntentIntegrator.parseActivityResult(i, i2, intent);
            if (parseActivityResult != null && parseActivityResult.getContents() != null) {
                String stringExtra = intent.getStringExtra("SCAN_RESULT");
                this.etAddress.setText(stringExtra);
                this.etAddress.setSelection(stringExtra.length());
            }
            this.etAddress.requestFocus();
        }
    }

    @Override
    public String[] getPermissions() {
        return new String[]{"android.permission.CAMERA"};
    }

    @Override
    public void requestPermissionsSuccess() {
        ScannerActivity.start(this);
    }

    @Override
    public void requestPermissionsFail() {
        ToastError(R.string.error_permission);
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        try {
            this.mPermissionHelper.requestPermissionsResult(i, strArr, iArr);
        } catch (Exception e) {
            SentryUtil.captureException(e);
        }
    }

    private abstract class ClickableSpanNoUnderLine extends ClickableSpan {
        private ClickableSpanNoUnderLine() {
        }

        @Override
        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            textPaint.setColor(textPaint.linkColor);
            textPaint.setUnderlineText(false);
        }
    }
}
