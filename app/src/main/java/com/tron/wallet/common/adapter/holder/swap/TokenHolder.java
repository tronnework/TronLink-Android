package com.tron.wallet.common.adapter.holder.swap;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.tabswap.bean.SwapTokenBean;
import com.tron.wallet.business.tabswap.utils.AmountTextWatcher;
import com.tron.wallet.common.components.ErrorEdiTextLayout;
import com.tron.wallet.common.components.SelectionWatcherEditText;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.ImageUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.SwapHeaderTokenBinding;
import com.tronlinkpro.wallet.R;
import java.math.BigDecimal;
import java.util.List;
public class TokenHolder extends BaseSwapAdapterHolder implements AmountTextWatcher.IOnAmountChangedCallback {
    public static final String PLACE_HOLDER = "- -";
    public static final int TYPE_BOTH = 3;
    public static final int TYPE_FROM = 2;
    public static final int TYPE_TO = 1;
    public static final int UPDATE_AMOUNTS = 3;
    public static final int UPDATE_BALANCE = 1;
    ErrorEdiTextLayout errorLayout;
    SelectionWatcherEditText etAmountFrom;
    SelectionWatcherEditText etAmountTo;
    ImageView ivSelectFrom;
    ImageView ivSelectTo;
    ImageView ivShadowLeft;
    ImageView ivShadowRight;
    View ivSwap;
    SimpleDraweeView ivTokenFrom;
    SimpleDraweeView ivTokenTo;
    private OnAmountChangedListener onAmountChangedCallback;
    private OnTokenChangedCallback onTokenChangedCallback;
    View rlFrom;
    View rlTo;
    private AmountTextWatcher textWatcherFrom;
    private AmountTextWatcher textWatcherTo;
    Pair<SwapTokenBean, SwapTokenBean> tokens;
    TextView tvAmountFrom;
    TextView tvAmountTo;
    TextView tvTokenNameFrom;
    TextView tvTokenNameTo;

    public enum ERROR {
        NOT_ENOUGH,
        NO_LIQUIDITY,
        AMOUNT_NEGATIVE,
        NO_PERMISSION,
        NO_RESERVE,
        DEFAULT_NO_ERROR,
        NO_LIQUIDITY_TOO_SMALL
    }

    public interface OnAmountChangedListener {
        void onInputAmountChanged(int i, String str, boolean z);
    }

    public interface OnTokenChangedCallback {
        void onExchangeToken();

        void onSelectToken(int i);
    }

    public void setAmountChangedCallback(OnAmountChangedListener onAmountChangedListener) {
        this.onAmountChangedCallback = onAmountChangedListener;
    }

    public void setTokenChangedCallback(OnTokenChangedCallback onTokenChangedCallback) {
        this.onTokenChangedCallback = onTokenChangedCallback;
    }

    @Override
    public void onAmountChanged(int i, String str) {
        if (this.tokens == null) {
            return;
        }
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            updateAmounts(i, "", "");
            showError(ERROR.DEFAULT_NO_ERROR);
            OnAmountChangedListener onAmountChangedListener = this.onAmountChangedCallback;
            if (onAmountChangedListener != null) {
                onAmountChangedListener.onInputAmountChanged(i, str, false);
                return;
            }
            return;
        }
        if (i == 2) {
            if (this.tokens.first != null) {
                ((SwapTokenBean) this.tokens.first).setInputAmount(str);
                z = checkAmount((SwapTokenBean) this.tokens.first, (SwapTokenBean) this.tokens.second, str, true, true, false);
            }
        } else if (this.tokens.second != null) {
            ((SwapTokenBean) this.tokens.second).setInputAmount(str);
            z = checkAmount((SwapTokenBean) this.tokens.second, (SwapTokenBean) this.tokens.first, str, true, false, true);
        }
        OnAmountChangedListener onAmountChangedListener2 = this.onAmountChangedCallback;
        if (onAmountChangedListener2 != null) {
            onAmountChangedListener2.onInputAmountChanged(i, str, z);
        }
    }

    public TokenHolder(Context context) {
        super(context, R.layout.swap_header_token);
        this.tokens = new Pair<>(new SwapTokenBean(), new SwapTokenBean());
        SwapHeaderTokenBinding bind = SwapHeaderTokenBinding.bind(this.itemView);
        this.ivTokenFrom = bind.iconTokenFrom;
        this.ivTokenTo = bind.iconTokenTo;
        this.tvTokenNameFrom = bind.tvTokenNameFrom;
        this.tvTokenNameTo = bind.tvTokenNameTo;
        this.ivSelectFrom = bind.ivSelectFrom;
        this.ivSelectTo = bind.ivSelectTo;
        this.tvAmountFrom = bind.tvAmountFrom;
        this.tvAmountTo = bind.tvAmountTo;
        this.etAmountFrom = bind.etAmountFrom;
        this.etAmountTo = bind.etAmountTo;
        this.ivSwap = bind.divider;
        this.errorLayout = bind.errLayout;
        this.ivShadowRight = bind.ivTextEnd;
        this.ivShadowLeft = bind.ivTextEndFrom;
        this.rlTo = bind.rlTo;
        this.rlFrom = bind.rlFrom;
        setupTextWatcher();
        this.ivSwap.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (onTokenChangedCallback != null) {
                    onTokenChangedCallback.onExchangeToken();
                }
            }
        });
        this.rlFrom.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (onTokenChangedCallback != null) {
                    onTokenChangedCallback.onSelectToken(0);
                }
            }
        });
        this.rlTo.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (onTokenChangedCallback != null) {
                    onTokenChangedCallback.onSelectToken(1);
                }
            }
        });
    }

    private void setupTextWatcher() {
        this.textWatcherFrom = new AmountTextWatcher(2, this.etAmountFrom);
        this.textWatcherTo = new AmountTextWatcher(1, this.etAmountTo);
        this.textWatcherFrom.setCallback(this);
        this.textWatcherTo.setCallback(this);
        this.etAmountFrom.addTextChangedListener(this.textWatcherFrom);
        this.etAmountTo.addTextChangedListener(this.textWatcherTo);
        this.etAmountTo.setOnSelectionChangedCallback(new SelectionWatcherEditText.IOnSelectionChangedCallback() {
            @Override
            public final void onSelectionChanged(int i, int i2) {
                lambda$setupTextWatcher$0(i, i2);
            }
        });
        this.etAmountFrom.setOnSelectionChangedCallback(new SelectionWatcherEditText.IOnSelectionChangedCallback() {
            @Override
            public final void onSelectionChanged(int i, int i2) {
                lambda$setupTextWatcher$1(i, i2);
            }
        });
    }

    public void lambda$setupTextWatcher$0(int i, int i2) {
        updateShadowVisibility(this.ivShadowRight, this.etAmountTo, i2 - i);
    }

    public void lambda$setupTextWatcher$1(int i, int i2) {
        updateShadowVisibility(this.ivShadowLeft, this.etAmountFrom, i2 - i);
    }

    private void updateShadowVisibility(ImageView imageView, EditText editText, int i) {
        if (TextUtils.isEmpty(editText.getText())) {
            imageView.setVisibility(View.GONE);
        } else if (editText.getPaint().measureText(editText.getText().toString()) <= editText.getMaxWidth()) {
            imageView.setVisibility(View.GONE);
        } else if (i < 0) {
            imageView.setVisibility(View.GONE);
        } else {
            imageView.setVisibility(View.VISIBLE);
        }
    }

    private String updateETAmount(EditText editText, AmountTextWatcher amountTextWatcher, String str) {
        editText.removeTextChangedListener(amountTextWatcher);
        editText.clearFocus();
        if (!TextUtils.isEmpty(str) && BigDecimalUtils.lessThanOrEqual(str, 0)) {
            editText.setText("");
        } else {
            if (!amountTextWatcher.validFraction(str)) {
                str = amountTextWatcher.getFractionString(str);
            }
            editText.setTextKeepState(str);
            editText.setSelection(editText.equals(this.etAmountTo) ? 0 : str.length());
        }
        editText.addTextChangedListener(amountTextWatcher);
        return str;
    }

    @Override
    public void onBind(Pair<SwapTokenBean, SwapTokenBean> pair) {
        super.onBind(pair);
        this.tokens = pair;
        bindTokenInfo();
    }

    private void bindTokenInfo() {
        showError(ERROR.DEFAULT_NO_ERROR);
        Pair<SwapTokenBean, SwapTokenBean> pair = this.tokens;
        if (pair == null || pair.first == null || this.tokens.second == null) {
            return;
        }
        setTokenLogo((SwapTokenBean) this.tokens.first, this.ivTokenFrom);
        setTokenLogo((SwapTokenBean) this.tokens.second, this.ivTokenTo);
        setBalance(this.tvAmountFrom, ((SwapTokenBean) this.tokens.first).getBalanceStr());
        setBalance(this.tvAmountTo, ((SwapTokenBean) this.tokens.second).getBalanceStr());
        setTokenName((SwapTokenBean) this.tokens.first, this.tvTokenNameFrom);
        setTokenName((SwapTokenBean) this.tokens.second, this.tvTokenNameTo);
        try {
            this.textWatcherFrom.setFraction(((SwapTokenBean) this.tokens.first).getDecimal());
            this.textWatcherTo.setFraction(((SwapTokenBean) this.tokens.second).getDecimal());
        } catch (Exception e) {
            LogUtils.e(e);
        }
        ((SwapTokenBean) this.tokens.first).setInputAmount(updateETAmount(this.etAmountFrom, this.textWatcherFrom, ((SwapTokenBean) this.tokens.first).getInputAmount()));
        ((SwapTokenBean) this.tokens.second).setInputAmount(updateETAmount(this.etAmountTo, this.textWatcherTo, ((SwapTokenBean) this.tokens.second).getInputAmount()));
        this.etAmountTo.clearFocus();
        this.etAmountFrom.clearFocus();
    }

    private void setTokenName(SwapTokenBean swapTokenBean, TextView textView) {
        textView.setText(swapTokenBean.getSymbol());
    }

    private void setTokenLogo(SwapTokenBean swapTokenBean, SimpleDraweeView simpleDraweeView) {
        if (!TextUtils.isEmpty(swapTokenBean.getLogo())) {
            ImageUtils.loadAsCircle(simpleDraweeView, swapTokenBean.getLogo());
        } else if (swapTokenBean.isTrx() && TextUtils.equals(swapTokenBean.getName(), "TRX") && TextUtils.equals(swapTokenBean.getSymbol(), "TRX")) {
            ImageUtils.loadAsCircleResource(simpleDraweeView, R.mipmap.trx);
        } else {
            simpleDraweeView.setImageResource(R.mipmap.ic_swap_default_logo);
        }
    }

    public void notifyPayloads(List<Object> list) {
        List list2;
        if (!(list.get(0) instanceof List) || (list2 = (List) list.get(0)) == null || list2.isEmpty()) {
            return;
        }
        Object obj = list2.get(0);
        if (obj instanceof Integer) {
            Integer num = (Integer) obj;
            if (3 == num.intValue()) {
                updateAmounts(((Integer) list2.get(1)).intValue(), (String) list2.get(2), (String) list2.get(3));
            } else if (1 == num.intValue()) {
                setBalance(this.tvAmountFrom, (String) list2.get(1));
                setBalance(this.tvAmountTo, (String) list2.get(2));
                Pair<SwapTokenBean, SwapTokenBean> pair = this.tokens;
                if (pair == null) {
                    return;
                }
                if (pair.first != null) {
                    ((SwapTokenBean) this.tokens.first).setBalanceStr((String) list2.get(1));
                }
                if (this.tokens.second != null) {
                    ((SwapTokenBean) this.tokens.second).setBalanceStr((String) list2.get(2));
                }
            }
        }
    }

    public static class fun4 {
        static final int[] $SwitchMap$com$tron$wallet$common$adapter$holder$swap$TokenHolder$ERROR;

        static {
            int[] iArr = new int[ERROR.values().length];
            $SwitchMap$com$tron$wallet$common$adapter$holder$swap$TokenHolder$ERROR = iArr;
            try {
                iArr[ERROR.NOT_ENOUGH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tron$wallet$common$adapter$holder$swap$TokenHolder$ERROR[ERROR.NO_LIQUIDITY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tron$wallet$common$adapter$holder$swap$TokenHolder$ERROR[ERROR.NO_RESERVE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$tron$wallet$common$adapter$holder$swap$TokenHolder$ERROR[ERROR.NO_PERMISSION.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$tron$wallet$common$adapter$holder$swap$TokenHolder$ERROR[ERROR.AMOUNT_NEGATIVE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$tron$wallet$common$adapter$holder$swap$TokenHolder$ERROR[ERROR.NO_LIQUIDITY_TOO_SMALL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$tron$wallet$common$adapter$holder$swap$TokenHolder$ERROR[ERROR.DEFAULT_NO_ERROR.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private void showError(ERROR error) {
        switch (fun4.$SwitchMap$com$tron$wallet$common$adapter$holder$swap$TokenHolder$ERROR[error.ordinal()]) {
            case 1:
                updateErrorView(true, R.string.err_swap_not_enough);
                return;
            case 2:
            case 3:
                updateErrorView(true, R.string.err_swap_no_liquidity);
                return;
            case 4:
                updateErrorView(true, R.string.err_swap_no_permission);
                return;
            case 5:
                updateErrorView(true, R.string.err_swap_negative);
                return;
            case 6:
                updateErrorView(true, R.string.err_swap_too_small);
                return;
            default:
                updateErrorView(false, 0);
                return;
        }
    }

    private void captureOnlineError(SwapTokenBean swapTokenBean, String str, SwapTokenBean swapTokenBean2, String str2) {
        try {
            String str3 = "trx";
            String exchange = swapTokenBean.isTrx() ? "trx" : swapTokenBean.getExchange();
            if (!swapTokenBean2.isTrx()) {
                str3 = swapTokenBean2.getExchange();
            }
            SentryUtil.captureMessage(String.format("Liquidity error when swap [ name: %s, address: %s, reserve: %s => name: %s, address: %s, reserve: %s ]", swapTokenBean.getName(), exchange, str, swapTokenBean2.getName(), str3, str2));
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    private void updateAmounts(int i, String str, String str2) {
        Pair<SwapTokenBean, SwapTokenBean> pair = this.tokens;
        if (pair == null || pair.first == null || this.tokens.second == null) {
            return;
        }
        ((SwapTokenBean) this.tokens.first).setInputAmount(str);
        ((SwapTokenBean) this.tokens.second).setInputAmount(str2);
        if (TextUtils.isEmpty(str)) {
            showError(ERROR.DEFAULT_NO_ERROR);
        } else {
            boolean checkAmount = checkAmount((SwapTokenBean) this.tokens.first, (SwapTokenBean) this.tokens.second, str, false, true, false);
            if (checkAmount) {
                checkAmount = checkAmount((SwapTokenBean) this.tokens.second, (SwapTokenBean) this.tokens.first, str2, false, false, true);
            }
            postRxEvent(Event.SWAP_SUBMIT_DIALOG, Boolean.valueOf(checkAmount));
        }
        if ((i & 2) == 2) {
            updateETAmountAndUI(this.etAmountTo, this.textWatcherTo, this.ivShadowRight, str2);
        }
        if ((i & 1) == 1) {
            updateETAmountAndUI(this.etAmountFrom, this.textWatcherFrom, this.ivShadowLeft, str);
        }
        if (TextUtils.isEmpty(this.etAmountFrom.getText()) && TextUtils.isEmpty(this.etAmountTo.getText())) {
            showError(ERROR.DEFAULT_NO_ERROR);
        }
    }

    private void updateETAmountAndUI(EditText editText, AmountTextWatcher amountTextWatcher, ImageView imageView, String str) {
        String updateETAmount = updateETAmount(editText, amountTextWatcher, str);
        imageView.setVisibility((TextUtils.isEmpty(updateETAmount) || editText.getPaint().measureText(updateETAmount) <= ((float) editText.getMaxWidth())) ? View.GONE : View.VISIBLE);
    }

    private void setBalance(TextView textView, String str) {
        if (TextUtils.equals(PLACE_HOLDER, str)) {
            textView.setText(PLACE_HOLDER);
        } else if (TextUtils.isEmpty(str)) {
            textView.setText("0");
        } else {
            if (BigDecimalUtils.toBigDecimal(fixBalanceString(str)).compareTo(new BigDecimal("0.000001")) < 0 && !BigDecimalUtils.Equal((Object) BigDecimalUtils.toBigDecimal(fixBalanceString(str)), (Object) 0)) {
                textView.setText("<0.000001");
            } else {
                textView.setText(StringTronUtil.formatNumber6TruncateNoDots(BigDecimalUtils.toBigDecimal(fixBalanceString(str))));
            }
        }
    }

    private String fixBalanceString(String str) {
        return TextUtils.equals(PLACE_HOLDER, str) ? "" : str;
    }

    private boolean checkAmount(SwapTokenBean swapTokenBean, SwapTokenBean swapTokenBean2, String str, boolean z, boolean z2, boolean z3) {
        if (swapTokenBean == null) {
            return false;
        }
        if (z && BigDecimalUtils.lessThanOrEqual(str, 0)) {
            showError(ERROR.AMOUNT_NEGATIVE);
            return false;
        }
        String valueOf = String.valueOf(swapTokenBean.getDecimal());
        if (TextUtils.isEmpty(valueOf) || BigDecimalUtils.LessThan(str, getMinValue(valueOf))) {
            showError(ERROR.NO_LIQUIDITY_TOO_SMALL);
            return false;
        }
        String balanceStr = swapTokenBean.getBalanceStr();
        if (z2 && (TextUtils.isEmpty(balanceStr) || BigDecimalUtils.MoreThan(str, balanceStr))) {
            showError(ERROR.NOT_ENOUGH);
            return false;
        }
        showError(ERROR.DEFAULT_NO_ERROR);
        return true;
    }

    private String getMinValue(String str) {
        return getAmountWithoutDecimal("1", str);
    }

    private String getAmountWithoutDecimal(String str, String str2) {
        try {
            return BigDecimalUtils.div_(str, Double.valueOf(Math.pow(10.0d, Integer.parseInt(str2)))).toPlainString();
        } catch (Exception unused) {
            return "";
        }
    }

    private String getReserveValue(SwapTokenBean swapTokenBean, SwapTokenBean swapTokenBean2) {
        if (swapTokenBean == null) {
            return "";
        }
        if (!swapTokenBean.isTrx()) {
            return (swapTokenBean.getReserve() == null || swapTokenBean.getReserve().size() != 2 || swapTokenBean.getDecimal() <= 0) ? "" : getAmountWithoutDecimal(swapTokenBean.getReserve().get(1), String.valueOf(swapTokenBean.getDecimal()));
        } else if (swapTokenBean2 == null || swapTokenBean2.getReserve() == null || swapTokenBean2.getReserve().isEmpty()) {
            return String.valueOf(Double.MAX_VALUE);
        } else {
            return getAmountWithoutDecimal(swapTokenBean2.getReserve().get(0), String.valueOf(swapTokenBean.getDecimal()));
        }
    }

    private void postRxEvent(String str, Object obj) {
        if (this.rxManager == null) {
            return;
        }
        this.rxManager.post(str, obj);
    }

    private void updateErrorView(boolean z, int i) {
        if (!z) {
            this.errorLayout.setVisibility(View.GONE);
            return;
        }
        this.errorLayout.setTextError3(i);
        this.errorLayout.showError3WithoutBackground();
        this.errorLayout.setVisibility(View.VISIBLE);
    }
}
