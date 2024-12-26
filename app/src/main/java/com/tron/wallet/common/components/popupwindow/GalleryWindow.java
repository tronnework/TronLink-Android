package com.tron.wallet.common.components.popupwindow;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.databinding.PopGalleryBinding;
import com.tronlinkpro.wallet.R;
public class GalleryWindow extends PopupWindow {
    PopGalleryBinding binding;
    RelativeLayout cancle;
    private View mMenuView;
    RelativeLayout save;

    public GalleryWindow(Activity activity) {
        super(activity);
        View inflate = ((LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.pop_gallery, (ViewGroup) null);
        this.mMenuView = inflate;
        this.binding = PopGalleryBinding.bind(inflate);
        mappingIds();
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
                int top = mMenuView.findViewById(R.id.save).getTop();
                int bottom = mMenuView.findViewById(R.id.cancle).getBottom();
                int y = (int) motionEvent.getY();
                if (motionEvent.getAction() == 1 && (y < top || y > bottom)) {
                    dismiss();
                }
                return true;
            }
        });
        this.save.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                dismiss();
            }
        });
    }

    private void mappingIds() {
        this.cancle = this.binding.cancle;
        this.save = this.binding.save;
    }

    public void setSaveListener(View.OnClickListener onClickListener) {
        this.save.setOnClickListener(onClickListener);
    }
}
