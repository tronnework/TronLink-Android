package com.tron.wallet.common.components;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.CenterPopupView;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.SpannableUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tronlinkpro.wallet.R;
public class TransferRiskWarningDialog extends CenterPopupView {
    private final Builder builder;

    @Override
    public int getImplLayoutId() {
        return R.layout.popup_transfer_risk_warning;
    }

    public TransferRiskWarningDialog(Context context, Builder builder) {
        super(context);
        this.builder = builder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        TextView textView = (TextView) findViewById(R.id.tv_enter_web);
        View findViewById = findViewById(R.id.btn_got_it);
        View findViewById2 = findViewById(R.id.btn_cancel);
        ((TextView) findViewById(R.id.tv_title)).setText(this.builder.titleResource);
        ((TextView) findViewById(R.id.tv_content)).setText(this.builder.contentResource);
        if (this.builder.targetWebResource > 0 && this.builder.targetWebPage > 0) {
            textView.setText(SpannableUtils.getTextColorSpannable(getContext().getString(this.builder.targetWebResource) + " >", getContext().getString(this.builder.targetWebPage), getContext().getResources().getColor(R.color.blue_3c)));
        }
        textView.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (TextUtils.isEmpty(builder.targetWebUrl)) {
                    return;
                }
                CommonWebActivityV3.start(view.getContext(), builder.targetWebUrl, "", 1, false);
            }
        });
        findViewById.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (builder.onClickNext != null) {
                    builder.onClickNext.onClick(view);
                }
                dismiss();
            }
        });
        findViewById2.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                dismiss();
            }
        });
    }

    @Override
    public int getPopupWidth() {
        return (int) (UIUtils.getScreenWidth(getContext()) * 0.9f);
    }

    @Override
    public int getMaxWidth() {
        return getPopupWidth();
    }

    public static void show(Context context, Builder builder) {
        new XPopup.Builder(context).dismissOnTouchOutside(false).asCustom(builder.build(context)).show();
    }

    public static class Builder {
        private int contentResource;
        private View.OnClickListener onClickNext;
        private int targetWebPage;
        private int targetWebResource;
        private String targetWebUrl;
        private int titleResource;

        public Builder setContentResource(int i) {
            this.contentResource = i;
            return this;
        }

        public Builder setOnClickNext(View.OnClickListener onClickListener) {
            this.onClickNext = onClickListener;
            return this;
        }

        public Builder setTargetWebPage(int i) {
            this.targetWebPage = i;
            return this;
        }

        public Builder setTargetWebResource(int i) {
            this.targetWebResource = i;
            return this;
        }

        public Builder setTargetWebUrl(String str) {
            this.targetWebUrl = str;
            return this;
        }

        public Builder setTitleResource(int i) {
            this.titleResource = i;
            return this;
        }

        public TransferRiskWarningDialog build(Context context) {
            return new TransferRiskWarningDialog(context, this);
        }
    }
}
