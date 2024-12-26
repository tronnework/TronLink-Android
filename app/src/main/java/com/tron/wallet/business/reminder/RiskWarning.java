package com.tron.wallet.business.reminder;

import android.content.Context;
import android.view.View;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.CenterPopupView;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.util.XPopupUtils;
import com.tron.wallet.business.reminder.RiskWarning;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tronlinkpro.wallet.R;
public class RiskWarning {
    private Context context;
    private NoDoubleClickListener listener;
    private int type;

    public RiskWarning(Context context, NoDoubleClickListener noDoubleClickListener) {
        this.context = context;
        this.listener = noDoubleClickListener;
    }

    public static void showBackupPopup(Context context, NoDoubleClickListener noDoubleClickListener) {
        new RiskWarning(context, noDoubleClickListener).show();
    }

    public void show() {
        new XPopup.Builder(this.context).maxWidth(XPopupUtils.getScreenWidth(this.context)).dismissOnTouchOutside(false).popupAnimation(PopupAnimation.NoAnimation).asCustom(new fun1(this.context)).show();
    }

    public class fun1 extends CenterPopupView {
        @Override
        public int getImplLayoutId() {
            return R.layout.popup_risk_warning;
        }

        fun1(Context context) {
            super(context);
        }

        @Override
        public void onCreate() {
            super.onCreate();
            View findViewById = findViewById(R.id.btn_confirm);
            View findViewById2 = findViewById(R.id.btn_cancel);
            findViewById.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onClick(null);
                    }
                    fun1.this.dismiss();
                }
            });
            findViewById2.setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    RiskWarning.1.this.lambda$onCreate$0(view);
                }
            });
        }

        public void lambda$onCreate$0(View view) {
            dismiss();
        }
    }
}
