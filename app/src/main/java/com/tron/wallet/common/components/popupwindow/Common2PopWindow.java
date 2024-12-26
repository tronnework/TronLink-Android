package com.tron.wallet.common.components.popupwindow;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import com.tron.wallet.databinding.PopSeleted1Binding;
import com.tronlinkpro.wallet.R;
public class Common2PopWindow extends PopupWindow implements View.OnClickListener {
    PopSeleted1Binding binding;
    LinearLayout content;
    private Activity context;
    private OnItemClickListener listener;
    View mDebugLayout;
    private View mMenuView;

    public interface OnItemClickListener {

        public final class -CC {
            public static void $default$onItemCancelClick(OnItemClickListener _this) {
            }
        }

        void onItem0Click();

        void onItem1Click();

        void onItem2Click();

        void onItemCancelClick();
    }

    public void setListener(OnItemClickListener onItemClickListener) {
        this.listener = onItemClickListener;
    }

    public Common2PopWindow(Activity activity, boolean z) {
        super(activity);
        this.context = activity;
        View inflate = ((LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.pop_seleted1, (ViewGroup) null);
        this.mMenuView = inflate;
        this.binding = PopSeleted1Binding.bind(inflate);
        mappingIds();
        initClickListener();
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
                int top = mMenuView.findViewById(R.id.content).getTop();
                int y = (int) motionEvent.getY();
                if (motionEvent.getAction() == 1 && y < top) {
                    dismiss();
                }
                return true;
            }
        });
        if (!z) {
            this.mDebugLayout.setVisibility(View.GONE);
        } else {
            this.mDebugLayout.setVisibility(View.VISIBLE);
        }
    }

    private void initClickListener() {
        this.binding.llCopyUrl.setOnClickListener(this);
        this.binding.llSafariOpen.setOnClickListener(this);
        this.binding.llCancle.setOnClickListener(this);
        this.binding.llDebug.setOnClickListener(this);
    }

    private void mappingIds() {
        this.content = this.binding.content;
        this.mDebugLayout = this.binding.llDebug;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_cancle:
                OnItemClickListener onItemClickListener = this.listener;
                if (onItemClickListener != null) {
                    onItemClickListener.onItemCancelClick();
                }
                dismiss();
                return;
            case R.id.ll_copy_url:
                OnItemClickListener onItemClickListener2 = this.listener;
                if (onItemClickListener2 != null) {
                    onItemClickListener2.onItem0Click();
                    return;
                }
                return;
            case R.id.ll_debug:
                OnItemClickListener onItemClickListener3 = this.listener;
                if (onItemClickListener3 != null) {
                    onItemClickListener3.onItem2Click();
                    return;
                }
                return;
            case R.id.ll_safari_open:
                OnItemClickListener onItemClickListener4 = this.listener;
                if (onItemClickListener4 != null) {
                    onItemClickListener4.onItem1Click();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
