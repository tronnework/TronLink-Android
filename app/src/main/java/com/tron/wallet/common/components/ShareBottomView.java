package com.tron.wallet.common.components;

import android.content.Context;
import android.view.View;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BottomPopupView;
import com.tron.wallet.common.utils.Action;
import com.tron.wallet.databinding.ShareBottomViewBinding;
import com.tronlinkpro.wallet.R;
public class ShareBottomView extends BottomPopupView implements View.OnClickListener {
    ShareBottomViewBinding binding;
    private Action<View> onClickImage;
    private Action<View> onClickUrl;

    @Override
    public int getImplLayoutId() {
        return R.layout.share_bottom_view;
    }

    public void setOnClickCallback(Action<View> action, Action<View> action2) {
        this.onClickUrl = action;
        this.onClickImage = action2;
    }

    public ShareBottomView(Context context) {
        super(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.binding = ShareBottomViewBinding.bind(getPopupImplView());
        click();
    }

    private void click() {
        this.binding.shareUrl.setOnClickListener(this);
        this.binding.shareImg.setOnClickListener(this);
        this.binding.tvCancel.setOnClickListener(this);
    }

    public static void showPop(Context context, Action<View> action, Action<View> action2) {
        ShareBottomView shareBottomView = (ShareBottomView) new XPopup.Builder(context).asCustom(new ShareBottomView(context));
        shareBottomView.setOnClickCallback(action, action2);
        shareBottomView.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.share_img:
                Action<View> action = this.onClickImage;
                if (action != null) {
                    action.apply(view, 1);
                }
                dismiss();
                return;
            case R.id.share_url:
                Action<View> action2 = this.onClickUrl;
                if (action2 != null) {
                    action2.apply(view, 0);
                }
                dismiss();
                return;
            case R.id.tv_cancel:
                dismiss();
                return;
            default:
                return;
        }
    }
}
