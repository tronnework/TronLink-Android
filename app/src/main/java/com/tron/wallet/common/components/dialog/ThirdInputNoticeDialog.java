package com.tron.wallet.common.components.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.DgThirdInputDialogBinding;
import com.tronlinkpro.wallet.R;
public class ThirdInputNoticeDialog {
    DgThirdInputDialogBinding binding;
    Button btnContinue;
    Button btnSwitchInput;
    CheckBox checkBox;
    CountDownTimer countDownTimer;
    private final Dialog dialog;
    boolean hasSystemInput;
    ImageView ivTitle;
    Context mContext;
    RelativeLayout top;
    TextView tvContent;
    TextView tvTitle;

    public ThirdInputNoticeDialog(final Context context, final boolean z) {
        this.mContext = context;
        this.hasSystemInput = z;
        View inflate = LayoutInflater.from(context).inflate(R.layout.dg_third_input_dialog, (ViewGroup) null);
        this.binding = DgThirdInputDialogBinding.bind(inflate);
        mappingIds();
        Dialog dialog = new Dialog(context, R.style.dialog_bright);
        this.dialog = dialog;
        setCanceledOnTouchOutside(true);
        int width = ((Activity) context).getWindowManager().getDefaultDisplay().getWidth();
        int screenHeight = UIUtils.getScreenHeight(context);
        dialog.requestWindowFeature(1);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.top.getLayoutParams();
        if (z) {
            layoutParams.topMargin = UIUtils.dip2px(180.0f);
        } else {
            layoutParams.topMargin = UIUtils.dip2px(220.0f);
        }
        dialog.setContentView(inflate, new ViewGroup.LayoutParams(width, screenHeight));
        if (z) {
            this.btnContinue.setVisibility(View.VISIBLE);
            this.btnContinue.setEnabled(false);
        } else {
            this.btnSwitchInput.setEnabled(false);
            this.btnContinue.setVisibility(View.GONE);
        }
        this.countDownTimer = new CountDownTimer(3000L, 1000L) {
            @Override
            public void onTick(long j) {
                if (j >= 0) {
                    if (z) {
                        Button button = btnContinue;
                        button.setText(context.getString(R.string.know_secure_risk_continue) + "(" + ((j / 1000) + 1) + ")");
                        return;
                    }
                    Button button2 = btnSwitchInput;
                    button2.setText(context.getString(R.string.know_secure_risk_continue) + "(" + ((j / 1000) + 1) + ")");
                }
            }

            @Override
            public void onFinish() {
                btnContinue.setTextColor(context.getResources().getColor(R.color.black_23));
                if (z) {
                    btnContinue.setEnabled(true);
                    btnContinue.setText(context.getString(R.string.know_secure_risk_continue));
                    return;
                }
                btnSwitchInput.setEnabled(true);
                btnSwitchInput.setText(context.getString(R.string.know_secure_risk_continue));
            }
        };
        this.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                AnalyticsHelper.logEvent(AnalyticsHelper.ThirdPartyInputAlert.ThirdPartyInputAlert_Not_remind);
            }
        });
    }

    private void mappingIds() {
        this.tvTitle = this.binding.title;
        this.tvContent = this.binding.tvContent;
        this.top = this.binding.top;
        this.ivTitle = this.binding.ivTitle;
        this.btnSwitchInput = this.binding.btnSwitchInput;
        this.btnContinue = this.binding.btnContinue;
        this.checkBox = this.binding.ck;
    }

    public ThirdInputNoticeDialog setTitle(String str) {
        this.tvTitle.setText(str);
        return this;
    }

    public ThirdInputNoticeDialog setTitle(int i) {
        this.tvTitle.setText(i);
        return this;
    }

    public ThirdInputNoticeDialog setContent(int i) {
        this.tvContent.setText(i);
        return this;
    }

    public ThirdInputNoticeDialog setContent(String str) {
        this.tvContent.setText(str);
        return this;
    }

    public ThirdInputNoticeDialog setBtFirstListener(String str, View.OnClickListener onClickListener) {
        this.btnSwitchInput.setText(str);
        this.btnSwitchInput.setOnClickListener(onClickListener);
        return this;
    }

    public ThirdInputNoticeDialog setBtSecondListener(String str, View.OnClickListener onClickListener) {
        this.btnContinue.setText(str);
        this.btnContinue.setOnClickListener(onClickListener);
        return this;
    }

    public ThirdInputNoticeDialog setCanceledOnTouchOutside(boolean z) {
        Dialog dialog = this.dialog;
        if (dialog != null) {
            dialog.setCanceledOnTouchOutside(z);
            this.dialog.setCancelable(z);
        }
        return this;
    }

    public void show() {
        if (this.dialog.isShowing()) {
            this.dialog.dismiss();
        }
        this.dialog.show();
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.start();
        }
    }

    public boolean isChecked() {
        CheckBox checkBox = this.checkBox;
        if (checkBox != null) {
            return checkBox.isChecked();
        }
        return false;
    }

    public void dismiss() {
        if (this.dialog.isShowing()) {
            this.dialog.dismiss();
        }
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
