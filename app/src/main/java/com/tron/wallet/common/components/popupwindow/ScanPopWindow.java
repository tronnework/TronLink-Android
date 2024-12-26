package com.tron.wallet.common.components.popupwindow;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tron.wallet.common.config.M;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.PopScanBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
public class ScanPopWindow extends PopupWindow {
    PopScanBinding binding;
    Button copy;
    SimpleDraweeView image;
    private boolean isClick;
    private View mMenuView;
    LinearLayout popLayout;
    ImageView qr;
    View top;
    TextView tvAddress;
    TextView tvtitle;

    public void setTitle(String str) {
        this.tvtitle.setVisibility(View.VISIBLE);
        this.tvtitle.setText(str);
    }

    public ScanPopWindow(Activity activity, String str, final String str2) {
        super(activity);
        this.isClick = false;
        if (StringTronUtil.isEmpty(str2)) {
            return;
        }
        View inflate = ((LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.pop_scan, (ViewGroup) null);
        this.mMenuView = inflate;
        this.binding = PopScanBinding.bind(inflate);
        mappingIds();
        if (M.M_TRX.equals(str)) {
            this.image.setBackgroundResource(R.mipmap.trx);
        } else if (M.M_TRC10.equals(str)) {
            this.image.setImageResource(R.mipmap.ic_token_default);
        } else {
            this.image.setImageResource(R.mipmap.ic_token_default);
        }
        this.tvAddress.setText(str2);
        this.qr.setImageBitmap(WalletUtils.strToQR(str2, UIUtils.dip2px(200.0f), UIUtils.dip2px(200.0f), null));
        this.copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$new$0(str2, view);
            }
        });
        setContentView(this.mMenuView);
        setWidth(-1);
        setHeight(-1);
        setFocusable(true);
        setAnimationStyle(R.style.AnimBottom);
        new ColorDrawable(-1342177280);
        setBackgroundDrawable(null);
        this.mMenuView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int top = mMenuView.findViewById(R.id.pop_layout).getTop();
                int bottom = mMenuView.findViewById(R.id.pop_layout).getBottom();
                int y = (int) motionEvent.getY();
                if (motionEvent.getAction() == 1 && (y < top || y > bottom)) {
                    dismiss();
                }
                return true;
            }
        });
    }

    public void lambda$new$0(String str, View view) {
        if (this.isClick) {
            return;
        }
        UIUtils.copy(str);
        this.copy.setBackgroundResource(R.drawable.roundborder_c2c8d5);
        this.copy.setText(R.string.already_copy);
    }

    private void mappingIds() {
        this.image = this.binding.image;
        this.tvAddress = this.binding.address;
        this.tvtitle = this.binding.address;
        this.qr = this.binding.qr;
        this.top = this.binding.top;
        this.copy = this.binding.copy;
        this.popLayout = this.binding.popLayout;
    }

    public void setTopbg(int i) {
        this.top.setBackgroundResource(i);
    }

    public void setImagebg(String str) {
        this.image.setImageURI(str);
    }
}
