package com.tron.wallet.common.components;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.CenterPopupView;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.interfaces.XPopupCallback;
import com.lxj.xpopup.util.XPopupUtils;
import com.tron.wallet.common.utils.CustomFontUtils;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.databinding.ConfirmCustomPopupViewBinding;
import com.tronlinkpro.wallet.R;
public class ConfirmCustomPopupView extends CenterPopupView implements View.OnClickListener {
    ConfirmCustomPopupViewBinding binding;
    Button btnCancel2;
    Button btnConfirm2;
    TextView cancelView;
    private Config config;
    TextView confirmView;
    public CountDownTimer countDownTimer;
    View ivClose;
    ImageView ivIcon;
    View llAction;
    View llAction2;
    View llTip;
    TextView tvInfo;
    TextView tvInfo1;
    TextView tvTitle;

    public static class Config {
        public String cancelText;
        public View.OnClickListener cancleListener;
        public int confirmBtnResId;
        public View.OnClickListener confirmListener;
        public String confirmText;
        public Context context;
        public String info;
        public CharSequence info1;
        public String title;
        public int titleDpSize;
        public XPopupCallback xPopupCallback;
        public boolean titleBold = true;
        public int infoGravity = 17;
        public boolean showCancelBtn = true;
        public boolean showTip = false;
        public boolean showClose = false;
        public boolean autoDismissOutSide = true;
        public boolean dismissOnBackPressed = true;
        public boolean hasShadowBg = true;
        public int btnStyle = 1;
        public boolean showTitle = true;
        public int iconRes = 0;
        public boolean supportCountDownConfirm = false;
        public int countDownConfirmSeconds = 5;
    }

    @Override
    public int getImplLayoutId() {
        return R.layout.confirm_custom_popup_view;
    }

    public ConfirmCustomPopupView(Context context, Config config) {
        super(context);
        this.config = config;
    }

    public static Builder getBuilder(Context context) {
        return new Builder(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.binding = ConfirmCustomPopupViewBinding.bind(getPopupImplView());
        mappingId();
        initClick();
        if (!this.config.showTitle) {
            this.tvTitle.setVisibility(View.GONE);
        } else {
            this.tvTitle.setText(this.config.title);
            if (this.config.titleDpSize > 0) {
                this.tvTitle.setTextSize(1, this.config.titleDpSize);
            }
            if (!this.config.titleBold) {
                this.tvTitle.setTypeface(CustomFontUtils.getTypeface(getContext(), 0));
            }
        }
        if (this.config.info != null) {
            this.tvInfo.setText(this.config.info);
            this.tvInfo.setGravity(this.config.infoGravity);
        } else {
            this.tvInfo.setVisibility(View.GONE);
        }
        if (this.config.info1 != null) {
            this.tvInfo1.setVisibility(View.VISIBLE);
            this.tvInfo1.setText(this.config.info1);
        }
        if (this.config.btnStyle == 1) {
            this.llAction.setVisibility(View.VISIBLE);
            this.llAction2.setVisibility(View.GONE);
            if (this.config.confirmText != null) {
                this.confirmView.setText(this.config.confirmText);
            }
            if (this.config.cancelText != null) {
                this.cancelView.setText(this.config.cancelText);
            }
        } else {
            this.llAction.setVisibility(View.GONE);
            this.llAction2.setVisibility(View.VISIBLE);
            if (this.config.confirmText != null) {
                this.btnConfirm2.setText(this.config.confirmText);
            }
            if (this.config.cancelText != null) {
                this.btnCancel2.setText(this.config.cancelText);
            }
        }
        this.cancelView.setVisibility(this.config.showCancelBtn ? View.VISIBLE : View.GONE);
        this.btnCancel2.setVisibility(this.config.showCancelBtn ? View.VISIBLE : View.GONE);
        this.llTip.setVisibility(this.config.showTip ? View.VISIBLE : View.GONE);
        this.ivClose.setVisibility(this.config.showClose ? View.VISIBLE : View.GONE);
        TouchDelegateUtils.expandViewTouchDelegate(this.ivClose, 10, 10, 10, 10);
        if (this.config.iconRes > 0) {
            this.ivIcon.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.tvTitle.getLayoutParams();
            layoutParams.addRule(3, this.ivIcon.getId());
            layoutParams.topMargin = XPopupUtils.dp2px(getContext(), 10.0f);
            this.tvTitle.setLayoutParams(layoutParams);
            this.ivIcon.setImageResource(this.config.iconRes);
        }
        if (this.config.supportCountDownConfirm) {
            setCountDownTimerConfirmButton();
        }
    }

    private void initClick() {
        this.binding.btnCancel.setOnClickListener(this);
        this.binding.confirm.setOnClickListener(this);
        this.binding.btnConfirm2.setOnClickListener(this);
        this.binding.btnCancel2.setOnClickListener(this);
    }

    private void mappingId() {
        this.tvTitle = this.binding.title;
        this.tvInfo = this.binding.info;
        this.tvInfo1 = this.binding.info1;
        this.confirmView = this.binding.confirm;
        this.cancelView = this.binding.btnCancel;
        this.btnConfirm2 = this.binding.btnConfirm2;
        this.btnCancel2 = this.binding.btnCancel2;
        this.llTip = this.binding.llTip;
        this.ivClose = this.binding.ivClose;
        this.llAction = this.binding.llAction;
        this.llAction2 = this.binding.llAction2;
        this.ivIcon = this.binding.ivIcon;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
            case R.id.btn_cancel_2:
                if (this.config.cancleListener != null) {
                    this.config.cancleListener.onClick(view);
                }
                dismiss();
                return;
            case R.id.btn_confirm_2:
            case R.id.confirm:
                if (this.config.confirmListener != null) {
                    this.config.confirmListener.onClick(view);
                }
                dismiss();
                return;
            default:
                return;
        }
    }

    public void setCountDownTimerConfirmButton() {
        this.countDownTimer = new CountDownTimer(this.config.countDownConfirmSeconds * 1000, 1000L) {
            @Override
            public void onTick(long j) {
                if (config.btnStyle == 1) {
                    binding.confirm.setText(String.format(getResources().getString(R.string.pop_count_down_second_confirm), Long.valueOf((j / 1000) + 1)));
                } else {
                    binding.btnConfirm2.setText(String.format(getResources().getString(R.string.pop_count_down_second_confirm), Long.valueOf((j / 1000) + 1)));
                }
            }

            @Override
            public void onFinish() {
                if (config.btnStyle == 1) {
                    binding.confirm.setEnabled(true);
                    binding.confirm.setText(config.confirmBtnResId);
                    return;
                }
                binding.btnConfirm2.setEnabled(true);
                binding.confirm.setText(config.confirmBtnResId);
            }
        };
        this.binding.confirm.setEnabled(false);
        this.countDownTimer.start();
    }

    public static class Builder {
        private Config config;

        public Builder(Context context) {
            Config config = new Config();
            this.config = config;
            config.context = context;
        }

        public Builder setBtnStyle(int i) {
            this.config.btnStyle = i;
            return this;
        }

        public Builder setTitle(String str) {
            this.config.title = str;
            return this;
        }

        public Builder setTitleSize(int i) {
            this.config.titleDpSize = i;
            return this;
        }

        public Builder setTitleBold(boolean z) {
            this.config.titleBold = z;
            return this;
        }

        public Builder setInfo(String str) {
            this.config.info = str;
            return this;
        }

        public Builder setInfoGravity(int i) {
            this.config.infoGravity = i;
            return this;
        }

        public Builder setInfo1(CharSequence charSequence) {
            this.config.info1 = charSequence;
            return this;
        }

        public Builder setShowTip(boolean z) {
            this.config.showTip = z;
            return this;
        }

        public Builder setShowClose(boolean z) {
            this.config.showClose = z;
            return this;
        }

        public Builder setAutoDismissOutSide(boolean z) {
            this.config.autoDismissOutSide = z;
            return this;
        }

        public Builder setConfirmText(String str) {
            this.config.confirmText = str;
            return this;
        }

        public Builder setCancelText(String str) {
            this.config.cancelText = str;
            return this;
        }

        public Builder setConfirmListener(View.OnClickListener onClickListener) {
            this.config.confirmListener = onClickListener;
            return this;
        }

        public Builder setCancleListener(View.OnClickListener onClickListener) {
            this.config.cancleListener = onClickListener;
            return this;
        }

        public Builder setShowCancelBtn(boolean z) {
            this.config.showCancelBtn = z;
            return this;
        }

        public Builder setPopupCallback(XPopupCallback xPopupCallback) {
            this.config.xPopupCallback = xPopupCallback;
            return this;
        }

        public Builder setHasShadowBg(boolean z) {
            this.config.hasShadowBg = z;
            return this;
        }

        public Builder setShowTitle(boolean z) {
            this.config.showTitle = z;
            return this;
        }

        public Builder setIcon(int i) {
            this.config.iconRes = i;
            return this;
        }

        public Builder setSupportConfirmBtnCountDown(boolean z, int i) {
            this.config.supportCountDownConfirm = z;
            this.config.confirmBtnResId = i;
            return this;
        }

        public Builder setConfirmBtnCountDownSecond(int i) {
            this.config.countDownConfirmSeconds = i;
            return this;
        }

        public Builder setDismissOnBackPressed(boolean z) {
            this.config.dismissOnBackPressed = z;
            return this;
        }

        public ConfirmCustomPopupView build() {
            return (ConfirmCustomPopupView) new XPopup.Builder(this.config.context).maxWidth(XPopupUtils.getScreenWidth(this.config.context)).setPopupCallback(this.config.xPopupCallback).dismissOnBackPressed(Boolean.valueOf(this.config.dismissOnBackPressed)).dismissOnTouchOutside(Boolean.valueOf(this.config.autoDismissOutSide)).popupAnimation(PopupAnimation.NoAnimation).hasShadowBg(Boolean.valueOf(this.config.hasShadowBg)).asCustom(new ConfirmCustomPopupView(this.config.context, this.config));
        }
    }
}
