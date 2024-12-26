package com.tron.wallet.business.stakev2.unstake;

import android.view.View;
import android.widget.TextView;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.wallet.business.stakev2.ResState;
import com.tron.wallet.common.utils.CustomFontUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tronlinkpro.wallet.R;
public class ResSwitch {
    private OnResSwitchListener onResSwitchListener;
    private View rlTabBandwidth;
    private View rlTabEnergy;
    private TextView tvTabBandwidth;
    private TextView tvTabEnergy;
    private int res_white = R.drawable.roundborder_ffffff_8;
    private int res_gray_left = R.drawable.roundborder_ebedf0_8_3;
    private int res_gray_right = R.drawable.roundborder_ebedf0_8_2;
    private int unSelected_color = AppContextUtil.getContext().getResources().getColor(R.color.gray_6D778C);
    private int selected_color = AppContextUtil.getContext().getResources().getColor(R.color.black_23);

    public interface OnResSwitchListener {
        void onResSwitch(ResState resState);
    }

    public void setOnResSwitchListener(OnResSwitchListener onResSwitchListener) {
        this.onResSwitchListener = onResSwitchListener;
    }

    public ResSwitch(final View view, final View view2, final TextView textView, final TextView textView2) {
        this.rlTabEnergy = view;
        this.rlTabBandwidth = view2;
        this.tvTabEnergy = textView;
        this.tvTabBandwidth = textView2;
        textView.setTypeface(CustomFontUtils.getTypeface(textView.getContext(), 1));
        view.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view3) {
                view.setBackgroundResource(res_white);
                view2.setBackgroundResource(res_gray_right);
                textView.setTextColor(selected_color);
                TextView textView3 = textView;
                textView3.setTypeface(CustomFontUtils.getTypeface(textView3.getContext(), 1));
                textView2.setTextColor(unSelected_color);
                TextView textView4 = textView2;
                textView4.setTypeface(CustomFontUtils.getTypeface(textView4.getContext(), 0));
                if (onResSwitchListener != null) {
                    onResSwitchListener.onResSwitch(ResState.Energy);
                }
            }
        });
        view2.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view3) {
                view2.setBackgroundResource(res_white);
                view.setBackgroundResource(res_gray_left);
                textView2.setTextColor(selected_color);
                TextView textView3 = textView2;
                textView3.setTypeface(CustomFontUtils.getTypeface(textView3.getContext(), 1));
                textView.setTextColor(unSelected_color);
                TextView textView4 = textView;
                textView4.setTypeface(CustomFontUtils.getTypeface(textView4.getContext(), 0));
                if (onResSwitchListener != null) {
                    onResSwitchListener.onResSwitch(ResState.Bandwidth);
                }
            }
        });
    }
}
