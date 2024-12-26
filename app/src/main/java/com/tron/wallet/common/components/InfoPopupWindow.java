package com.tron.wallet.common.components;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.animator.EmptyAnimator;
import com.lxj.xpopup.core.AttachPopupView;
import com.tron.wallet.common.utils.UIUtils;
import com.tronlinkpro.wallet.R;
public class InfoPopupWindow extends AttachPopupView {
    private final int stringRes;
    private TextView tvContent;

    @Override
    public int getImplLayoutId() {
        return R.layout.info_popup;
    }

    public InfoPopupWindow(Context context, int i) {
        super(context);
        this.stringRes = i;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        TextView textView = (TextView) findViewById(R.id.tv_content);
        this.tvContent = textView;
        int i = this.stringRes;
        if (i > 0) {
            textView.setText(i);
        }
    }

    public static void showPop(View view, int i) {
        InfoPopupWindow infoPopupWindow = new InfoPopupWindow(view.getContext(), i);
        new XPopup.Builder(view.getContext()).atView(view).hasShadowBg(false).offsetX(UIUtils.dip2px(16.0f) * (-1)).customAnimator(new EmptyAnimator(infoPopupWindow, 200)).asCustom(infoPopupWindow).show();
    }
}
