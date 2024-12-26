package com.tron.wallet.business.confirm.fg.component;

import android.content.Context;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.GravityCompat;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.fg.BaseConfirmFragment;
import com.tron.wallet.business.confirm.fg.bean.SwapApproveParam;
import com.tron.wallet.business.confirm.fg.component.GlobalFeeResourceView;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.TransferParam;
import com.tron.wallet.business.pull.dappconfirm.content.DeepLinkDappConfirmActivity;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.DappConfirmNewActivity;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.PopupWindowUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.addressscam.ScamAddressPopWindowCallback;
import com.tron.wallet.databinding.LayoutGlobalConfirmButtonBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class GlobalConfirmButton extends FrameLayout implements BaseConfirmFragment.AccountCallback, GlobalFeeResourceView.FeeResourceCallback {
    public static final int ENERGY_THRESHOLD = 225;
    String address;
    RotateAnimation animation;
    private LayoutGlobalConfirmButtonBinding binding;
    Button btnConfirm;
    public CountDownTimer countDownTimer;
    boolean finishGetFee;
    boolean finishGetScam;
    boolean isScam;
    ImageView ivErrorBottom;
    public ImageView ivLoading;
    public LinearLayout llErrorBottom;
    boolean loading;
    public BaseParam param;
    TextView tvConfirm;
    public TextView tvErrorBottom;

    @Override
    public void onRefreshAccountComplete(boolean z, Protocol.Transaction transaction, Pair<Protocol.Account, GrpcAPI.AccountResourceMessage> pair) {
    }

    public GlobalConfirmButton(Context context) {
        this(context, null);
    }

    public void mappingId(View view) {
        LayoutGlobalConfirmButtonBinding bind = LayoutGlobalConfirmButtonBinding.bind(view);
        this.binding = bind;
        this.btnConfirm = bind.btnConfirm;
        this.tvConfirm = this.binding.tvBtnText;
        this.ivErrorBottom = this.binding.ivError;
        this.ivLoading = this.binding.ivFeeLoading;
        this.llErrorBottom = this.binding.llError;
        this.tvErrorBottom = this.binding.tvErrorBottom;
    }

    public GlobalConfirmButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GlobalConfirmButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_global_confirm_button, (ViewGroup) this, false);
        addView(inflate, new FrameLayout.LayoutParams(-1, -2));
        mappingId(inflate);
    }

    public void onBind(BaseParam baseParam) {
        onBind(baseParam, false, "");
    }

    public void onBind(BaseParam baseParam, boolean z, String str) {
        this.address = str;
        this.loading = z;
        if (z) {
            this.btnConfirm.setText("");
            this.tvConfirm.setVisibility(View.VISIBLE);
        } else {
            this.tvConfirm.setText("");
        }
        setLoadingButton();
        if (baseParam == null) {
            return;
        }
        this.param = baseParam;
        try {
            Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
            int createType = selectedPublicWallet.getCreateType();
            if (createType == 8 && baseParam.hasOwnerPermission()) {
                this.tvConfirm.setText(R.string.request_from_ledger);
            } else if (createType == 7 && baseParam.hasOwnerPermission()) {
                this.tvConfirm.setText(R.string.confirm);
            } else if (selectedPublicWallet.isWatchOnly() && baseParam.hasOwnerPermission()) {
                this.tvConfirm.setText(R.string.create_transcation_qr);
            } else if (baseParam instanceof SwapApproveParam) {
                this.tvConfirm.setText(R.string.dapp_approve);
            } else {
                this.tvConfirm.setText(R.string.confirm);
            }
            setupBottomWarning(baseParam);
            this.btnConfirm.setEnabled(false);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void setupConfirmButtonEvent(final BaseParam baseParam) {
        if (baseParam.getTransactionBean().getBytes().isEmpty()) {
            this.btnConfirm.setEnabled(false);
        } else {
            this.btnConfirm.setOnClickListener(new NoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    GlobalConfirmButton globalConfirmButton = GlobalConfirmButton.this;
                    globalConfirmButton.enterConfirm(globalConfirmButton.getContext(), baseParam);
                }
            });
        }
    }

    public void setupCustomConfirmButtonEvent(NoDoubleClickListener noDoubleClickListener) {
        this.btnConfirm.setOnClickListener(noDoubleClickListener);
    }

    public void updateClickEvent(final boolean z, final double d, final double d2, final double d3) {
        BaseParam baseParam = this.param;
        if (baseParam == null || baseParam.getTransactionBean().getBytes().isEmpty()) {
            return;
        }
        this.finishGetFee = true;
        LogUtils.e("setLoadingView", "onGetFee");
        if (this.finishGetScam || !this.loading) {
            LogUtils.e("setLoadingView", "in");
            this.animation.cancel();
            this.ivLoading.clearAnimation();
            this.countDownTimer.cancel();
            this.ivLoading.setVisibility(View.GONE);
            if (this.isScam) {
                this.btnConfirm.setBackgroundResource(R.drawable.selector_bg_red);
            }
            this.btnConfirm.setEnabled(true);
            Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
            int createType = selectedPublicWallet.getCreateType();
            if (createType == 8 && this.param.hasOwnerPermission()) {
                this.tvConfirm.setText(R.string.request_from_ledger);
            } else if (createType == 7 && this.param.hasOwnerPermission()) {
                this.tvConfirm.setText(R.string.confirm);
            } else if (selectedPublicWallet.isWatchOnly() && this.param.hasOwnerPermission()) {
                this.tvConfirm.setText(R.string.create_transcation_qr);
            } else if (this.param instanceof SwapApproveParam) {
                this.tvConfirm.setText(R.string.dapp_approve);
            } else {
                this.tvConfirm.setText(R.string.confirm);
            }
        }
        this.btnConfirm.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (isScam) {
                    PopupWindowUtil.showScamAddressPopWindow(getContext(), address, new ScamAddressPopWindowCallback() {
                        @Override
                        public void cancel() {
                        }

                        @Override
                        public void continueDo() {
                            doTransation(z, d, d2, d3);
                        }
                    });
                } else {
                    doTransation(z, d, d2, d3);
                }
            }
        });
    }

    public void doTransation(boolean z, double d, double d2, double d3) {
        cheatTokenConfirmEvent(this.param);
        if (!z) {
            showFeeWarningDialog(R.string.confirm_fee_warning_title, R.string.confirm_fee_warning_consume_trx, this.param);
        } else if (BigDecimalUtils.moreThanOrEqual(Double.valueOf(BigDecimalUtils.sum(d, d2)), Double.valueOf(d3))) {
            Context context = getContext();
            showFeeWarningDialog(R.string.confirm_fee_warning_title, context.getString(R.string.confirm_fee_warning_over_cost, StringTronUtil.formatNumber0Truncate(BigDecimalUtils.toBigDecimal(Double.valueOf(d3))) + " TRX"), this.param);
        } else {
            enterConfirm(getContext(), this.param);
        }
    }

    private void cheatTokenConfirmEvent(BaseParam baseParam) {
        try {
            if ((baseParam instanceof TransferParam) && ((TransferParam) baseParam).getTokenBean() != null && ((TransferParam) baseParam).getTokenBean().isOfficial == -5) {
                logEvent(AnalyticsHelper.TransactionConfirmPopup.CLICK_CONFIRM_PAGE_CHEAT_TRADE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enterConfirm(Context context, BaseParam baseParam) {
        try {
            if (context instanceof ConfirmTransactionNewActivity) {
                enterConfirmNext((ConfirmTransactionNewActivity) context, baseParam);
            } else if (context instanceof DappConfirmNewActivity) {
                ((DappConfirmNewActivity) context).jumpToNext();
            } else if (context instanceof DeepLinkDappConfirmActivity) {
                ((DeepLinkDappConfirmActivity) context).jumpToNext();
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void showFeeWarningDialog(int i, int i2, final BaseParam baseParam) {
        ConfirmCustomPopupView.getBuilder(getContext()).setBtnStyle(0).setTitle(getContext().getString(i)).setInfo(getContext().getString(i2)).setConfirmText(getContext().getString(R.string.confirm)).setCancelText(getContext().getString(R.string.cancel)).setAutoDismissOutSide(false).setConfirmListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showFeeWarningDialog$0(baseParam, view);
            }
        }).build().show();
    }

    public void lambda$showFeeWarningDialog$0(BaseParam baseParam, View view) {
        enterConfirm(getContext(), baseParam);
    }

    public void showFeeWarningDialog(int i, String str, final BaseParam baseParam) {
        ConfirmCustomPopupView.getBuilder(getContext()).setBtnStyle(0).setTitle(getContext().getString(i)).setInfo(str).setConfirmText(getContext().getString(R.string.confirm)).setCancelText(getContext().getString(R.string.cancel)).setAutoDismissOutSide(false).setConfirmListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showFeeWarningDialog$1(baseParam, view);
            }
        }).build().show();
    }

    public void lambda$showFeeWarningDialog$1(BaseParam baseParam, View view) {
        enterConfirm(getContext(), baseParam);
    }

    public void enterConfirmNext(ConfirmTransactionNewActivity confirmTransactionNewActivity, BaseParam baseParam) {
        confirmTransactionNewActivity.dismissLoading();
        if (baseParam instanceof TransferParam) {
            TransferParam transferParam = (TransferParam) baseParam;
            if (baseParam.hasOwnerPermission() || transferParam.getIsFromDealSign() == 1) {
                confirmTransactionNewActivity.JumpkTo(3);
            } else {
                confirmTransactionNewActivity.JumpkTo(2);
            }
        } else if (baseParam.hasOwnerPermission()) {
            confirmTransactionNewActivity.JumpkTo(3);
        } else {
            confirmTransactionNewActivity.JumpkTo(2);
        }
    }

    public void setupBottomWarning(BaseParam baseParam) {
        if (TextUtils.isEmpty(baseParam.getErrorHint())) {
            this.llErrorBottom.setVisibility(View.GONE);
            return;
        }
        this.llErrorBottom.setVisibility(View.VISIBLE);
        this.tvErrorBottom.setText(baseParam.getErrorHint());
        boolean z = 1 == baseParam.getErrorHintType();
        this.tvErrorBottom.setTextColor(getContext().getResources().getColor(z ? R.color.orange_FF : R.color.blue_13));
        this.ivErrorBottom.setVisibility(z ? View.VISIBLE : View.GONE);
        if (z) {
            this.tvErrorBottom.setGravity(17);
        } else {
            this.tvErrorBottom.setGravity(GravityCompat.START);
        }
    }

    @Override
    public void setEnabled(boolean z) {
        this.btnConfirm.setEnabled(z);
    }

    @Override
    public void onGetFee(boolean z, double d, double d2, double d3) {
        LogUtils.e("setLoadingView", "onGetFeeout");
        updateClickEvent(z, d, d2, d3);
    }

    private void logEvent(String str) {
        try {
            AnalyticsHelper.logEventFormat(str, Integer.valueOf(Protocol.Transaction.parseFrom(this.param.getTransactionBean().getBytes().get(0)).getRawData().getContract(0).getType().equals(Protocol.Transaction.Contract.ContractType.TriggerSmartContract) ? 2 : 1));
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    private void setLoadingView() {
        this.ivLoading.setImageResource(R.mipmap.circle_loading_20);
        this.ivLoading.setScaleType(ImageView.ScaleType.FIT_CENTER);
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        this.animation = rotateAnimation;
        rotateAnimation.setFillAfter(true);
        this.animation.setInterpolator(new LinearInterpolator());
        this.animation.setDuration(1000L);
        this.animation.setRepeatCount(-1);
        this.ivLoading.startAnimation(this.animation);
    }

    public void setLoadingButton() {
        setLoadingView();
        CountDownTimer countDownTimer = new CountDownTimer(2000L, 1000L) {
            @Override
            public void onTick(long j) {
            }

            @Override
            public void onFinish() {
                finishGetScam = true;
                if (finishGetFee) {
                    LogUtils.e("setLoadingView", "onFinish");
                    ivLoading.clearAnimation();
                    ivLoading.setVisibility(View.GONE);
                    Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
                    int createType = selectedPublicWallet.getCreateType();
                    if (createType == 8 && param.hasOwnerPermission()) {
                        tvConfirm.setText(R.string.request_from_ledger);
                    } else if (createType == 7 && param.hasOwnerPermission()) {
                        tvConfirm.setText(R.string.confirm);
                    } else if (selectedPublicWallet.isWatchOnly() && param.hasOwnerPermission()) {
                        tvConfirm.setText(R.string.create_transcation_qr);
                    } else {
                        tvConfirm.setText(R.string.confirm);
                    }
                    btnConfirm.setEnabled(true);
                }
            }
        };
        this.countDownTimer = countDownTimer;
        countDownTimer.start();
    }

    public void setFinishGetScam(boolean z) {
        LogUtils.e("setLoadingView", "setFinishGetScam");
        this.isScam = z;
        if (this.finishGetScam) {
            return;
        }
        LogUtils.e("setLoadingView  ", "1");
        this.finishGetScam = true;
        LogUtils.e("setLoadingView  ", "finishGetFee=" + this.finishGetFee);
        if (this.finishGetFee) {
            LogUtils.e("setLoadingView  ", "2");
            this.animation.cancel();
            this.ivLoading.clearAnimation();
            this.ivLoading.setVisibility(View.GONE);
            Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
            int createType = selectedPublicWallet.getCreateType();
            if (createType == 8 && this.param.hasOwnerPermission()) {
                this.tvConfirm.setText(R.string.request_from_ledger);
            } else if (createType == 7 && this.param.hasOwnerPermission()) {
                this.tvConfirm.setText(R.string.confirm);
            } else if (selectedPublicWallet.isWatchOnly() && this.param.hasOwnerPermission()) {
                this.tvConfirm.setText(R.string.create_transcation_qr);
            } else {
                this.tvConfirm.setText(R.string.confirm);
            }
            if (z) {
                LogUtils.e("checkAddressIsScam onError", "3");
                this.btnConfirm.setBackgroundResource(R.drawable.selector_bg_red);
                this.btnConfirm.setEnabled(true);
            }
        }
    }

    public void setText(String str) {
        this.btnConfirm.setText(str);
    }

    public void cancelLoading() {
        this.animation.cancel();
        this.ivLoading.clearAnimation();
        this.countDownTimer.cancel();
        this.ivLoading.setVisibility(View.GONE);
    }
}
