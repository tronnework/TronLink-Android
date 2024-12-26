package com.tron.wallet.business.tabdapp.component;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.enums.PopupAnimation;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.UIUtils;
import com.tronlinkpro.wallet.R;
public class BrowserLongClickPopupView extends BasePopupView {
    private Bitmap bitmap;
    private String content;
    private View.OnClickListener onClickCopy;
    private View.OnClickListener onClickNewTab;
    private View.OnClickListener onClickOpenOutside;
    private View.OnClickListener onOpenNew;
    private boolean showOpenNewTab;

    @Override
    public int getImplLayoutId() {
        return R.layout.pop_browser_long_press;
    }

    @Override
    protected int getInnerLayoutId() {
        return R.layout.pop_browser_long_press;
    }

    public BrowserLongClickPopupView setClickCopyListener(View.OnClickListener onClickListener) {
        this.onClickCopy = onClickListener;
        return this;
    }

    public BrowserLongClickPopupView setClickNewTabListener(View.OnClickListener onClickListener) {
        this.onClickNewTab = onClickListener;
        return this;
    }

    public BrowserLongClickPopupView setClickOpenNewListener(View.OnClickListener onClickListener) {
        this.onOpenNew = onClickListener;
        return this;
    }

    public BrowserLongClickPopupView setClickOpenOutsideListener(View.OnClickListener onClickListener) {
        this.onClickOpenOutside = onClickListener;
        return this;
    }

    public BrowserLongClickPopupView setContent(String str) {
        if (str == null) {
            str = "";
        }
        this.content = str;
        return this;
    }

    public BrowserLongClickPopupView setShowOpenNewTab(boolean z) {
        this.showOpenNewTab = z;
        return this;
    }

    public BrowserLongClickPopupView(Context context, Bitmap bitmap) {
        super(context);
        this.showOpenNewTab = true;
        this.bitmap = bitmap;
    }

    public static BrowserLongClickPopupView create(FragmentActivity fragmentActivity) {
        Display defaultDisplay = fragmentActivity.getWindowManager().getDefaultDisplay();
        Bitmap.createBitmap(defaultDisplay.getWidth(), defaultDisplay.getHeight(), Bitmap.Config.ARGB_8888);
        View decorView = fragmentActivity.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        BrowserLongClickPopupView browserLongClickPopupView = new BrowserLongClickPopupView(fragmentActivity, decorView.getDrawingCache());
        browserLongClickPopupView.setFocusable(true);
        browserLongClickPopupView.setSystemUiVisibility(4);
        browserLongClickPopupView.setClickOpenOutsideListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        new XPopup.Builder(fragmentActivity).maxWidth(UIUtils.getScreenWidth(fragmentActivity)).maxHeight(UIUtils.getScreenHeight(fragmentActivity)).dismissOnTouchOutside(true).dismissOnBackPressed(true).popupAnimation(PopupAnimation.NoAnimation).offsetX(UIUtils.dip2px(0.0f)).offsetY(UIUtils.dip2px(0.0f)).hasBlurBg(true).asCustom(browserLongClickPopupView);
        return browserLongClickPopupView;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ImageView imageView = (ImageView) findViewById(R.id.iv_bg_image);
        View findViewById = findViewById(R.id.li_content);
        View findViewById2 = findViewById(R.id.li_browser_url);
        View findViewById3 = findViewById(R.id.li_browser_copylink);
        ((TextView) findViewById(R.id.tv_browser_longpress_content)).setText(this.content);
        View findViewById4 = findViewById(R.id.li_browser_open_direction);
        View findViewById5 = findViewById(R.id.li_browser_in_new_tab);
        View findViewById6 = findViewById(R.id.li_browser_outside);
        if (TextUtils.isEmpty(this.content)) {
            findViewById2.setVisibility(View.GONE);
        }
        findViewById5.setVisibility(this.showOpenNewTab ? View.VISIBLE : View.GONE);
        findViewById3.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (onClickCopy != null) {
                    onClickCopy.onClick(view);
                }
                dismiss();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        findViewById4.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (onOpenNew != null) {
                    onOpenNew.onClick(view);
                }
                dismiss();
            }
        });
        findViewById5.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (onClickNewTab != null) {
                    onClickNewTab.onClick(view);
                }
                dismiss();
            }
        });
        findViewById6.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (onClickOpenOutside != null) {
                    onClickOpenOutside.onClick(view);
                }
                dismiss();
            }
        });
        findViewById.requestFocus();
    }
}
