package com.tron.wallet.common.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.wallet.business.stakev2.ResState;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.databinding.ItemPercentBinding;
import com.tronlinkpro.wallet.R;
public class StakePercentView extends RelativeLayout {
    ItemPercentBinding binding;
    private NoDoubleClickListener2 noDoubleClickListener;
    private OnClickPercentListener onClickPercentListener;
    private ResState resState;
    private int selectedRes;
    TextView tvPercent100;
    TextView tvPercent25;
    TextView tvPercent50;
    TextView tvPercent75;

    public interface OnClickPercentListener {
        void onClickPercent(float f, TextView textView, Percent percent);
    }

    public void setOnClickPercentListener(OnClickPercentListener onClickPercentListener) {
        this.onClickPercentListener = onClickPercentListener;
    }

    public void setSelectedRes(int i) {
        this.selectedRes = i;
    }

    public void setPowerState(ResState resState) {
        this.resState = resState;
        if (resState == ResState.Bandwidth) {
            this.selectedRes = R.drawable.roundborder_57bfad;
        } else if (this.resState == ResState.Energy) {
            this.selectedRes = R.drawable.roundborder_e2b380;
        }
        resetSelectorBackground();
    }

    public StakePercentView(Context context) {
        super(context);
        this.resState = ResState.Energy;
        this.selectedRes = R.drawable.roundborder_e2b380;
        this.noDoubleClickListener = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                resetSelectorBackground();
                switch (id) {
                    case R.id.amount_percent_100:
                        tvPercent100.setBackgroundResource(selectedRes);
                        tvPercent100.setTextColor(getResources().getColor(R.color.white));
                        if (onClickPercentListener != null) {
                            onClickPercentListener.onClickPercent(1.0f, tvPercent100, Percent.PERCENT100);
                            return;
                        }
                        return;
                    case R.id.amount_percent_25:
                        tvPercent25.setBackgroundResource(selectedRes);
                        tvPercent25.setTextColor(getResources().getColor(R.color.white));
                        if (onClickPercentListener != null) {
                            onClickPercentListener.onClickPercent(0.25f, tvPercent25, Percent.PERCENT25);
                            return;
                        }
                        return;
                    case R.id.amount_percent_50:
                        tvPercent50.setBackgroundResource(selectedRes);
                        tvPercent50.setTextColor(getResources().getColor(R.color.white));
                        if (onClickPercentListener != null) {
                            onClickPercentListener.onClickPercent(0.5f, tvPercent50, Percent.PERCENT50);
                            return;
                        }
                        return;
                    case R.id.amount_percent_75:
                        tvPercent75.setBackgroundResource(selectedRes);
                        tvPercent75.setTextColor(getResources().getColor(R.color.white));
                        if (onClickPercentListener != null) {
                            onClickPercentListener.onClickPercent(0.75f, tvPercent75, Percent.PERCENT75);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        };
        addPercentView(context);
    }

    public StakePercentView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.resState = ResState.Energy;
        this.selectedRes = R.drawable.roundborder_e2b380;
        this.noDoubleClickListener = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                resetSelectorBackground();
                switch (id) {
                    case R.id.amount_percent_100:
                        tvPercent100.setBackgroundResource(selectedRes);
                        tvPercent100.setTextColor(getResources().getColor(R.color.white));
                        if (onClickPercentListener != null) {
                            onClickPercentListener.onClickPercent(1.0f, tvPercent100, Percent.PERCENT100);
                            return;
                        }
                        return;
                    case R.id.amount_percent_25:
                        tvPercent25.setBackgroundResource(selectedRes);
                        tvPercent25.setTextColor(getResources().getColor(R.color.white));
                        if (onClickPercentListener != null) {
                            onClickPercentListener.onClickPercent(0.25f, tvPercent25, Percent.PERCENT25);
                            return;
                        }
                        return;
                    case R.id.amount_percent_50:
                        tvPercent50.setBackgroundResource(selectedRes);
                        tvPercent50.setTextColor(getResources().getColor(R.color.white));
                        if (onClickPercentListener != null) {
                            onClickPercentListener.onClickPercent(0.5f, tvPercent50, Percent.PERCENT50);
                            return;
                        }
                        return;
                    case R.id.amount_percent_75:
                        tvPercent75.setBackgroundResource(selectedRes);
                        tvPercent75.setTextColor(getResources().getColor(R.color.white));
                        if (onClickPercentListener != null) {
                            onClickPercentListener.onClickPercent(0.75f, tvPercent75, Percent.PERCENT75);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        };
        addPercentView(context);
    }

    public StakePercentView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.resState = ResState.Energy;
        this.selectedRes = R.drawable.roundborder_e2b380;
        this.noDoubleClickListener = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                resetSelectorBackground();
                switch (id) {
                    case R.id.amount_percent_100:
                        tvPercent100.setBackgroundResource(selectedRes);
                        tvPercent100.setTextColor(getResources().getColor(R.color.white));
                        if (onClickPercentListener != null) {
                            onClickPercentListener.onClickPercent(1.0f, tvPercent100, Percent.PERCENT100);
                            return;
                        }
                        return;
                    case R.id.amount_percent_25:
                        tvPercent25.setBackgroundResource(selectedRes);
                        tvPercent25.setTextColor(getResources().getColor(R.color.white));
                        if (onClickPercentListener != null) {
                            onClickPercentListener.onClickPercent(0.25f, tvPercent25, Percent.PERCENT25);
                            return;
                        }
                        return;
                    case R.id.amount_percent_50:
                        tvPercent50.setBackgroundResource(selectedRes);
                        tvPercent50.setTextColor(getResources().getColor(R.color.white));
                        if (onClickPercentListener != null) {
                            onClickPercentListener.onClickPercent(0.5f, tvPercent50, Percent.PERCENT50);
                            return;
                        }
                        return;
                    case R.id.amount_percent_75:
                        tvPercent75.setBackgroundResource(selectedRes);
                        tvPercent75.setTextColor(getResources().getColor(R.color.white));
                        if (onClickPercentListener != null) {
                            onClickPercentListener.onClickPercent(0.75f, tvPercent75, Percent.PERCENT75);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        };
        addPercentView(context);
    }

    public enum Percent {
        PERCENT25(25),
        PERCENT50(50),
        PERCENT75(75),
        PERCENT100(100);
        
        private int code;

        public int getCode() {
            return this.code;
        }

        Percent(int i) {
            this.code = i;
        }
    }

    private void addPercentView(Context context) {
        View inflate = View.inflate(context, R.layout.item_percent, null);
        this.binding = ItemPercentBinding.bind(inflate);
        mappingId();
        this.tvPercent25.setOnClickListener(this.noDoubleClickListener);
        this.tvPercent50.setOnClickListener(this.noDoubleClickListener);
        this.tvPercent75.setOnClickListener(this.noDoubleClickListener);
        this.tvPercent100.setOnClickListener(this.noDoubleClickListener);
        addView(inflate, -1, -1);
    }

    private void mappingId() {
        this.tvPercent25 = this.binding.amountPercent25;
        this.tvPercent50 = this.binding.amountPercent50;
        this.tvPercent75 = this.binding.amountPercent75;
        this.tvPercent100 = this.binding.amountPercent100;
    }

    public void resetSelectorBackground() {
        this.tvPercent25.setBackgroundResource(R.drawable.roundborder_cdd1da_r4);
        this.tvPercent25.setTextColor(getResources().getColor(R.color.black_02));
        this.tvPercent50.setBackgroundResource(R.drawable.roundborder_cdd1da_r4);
        this.tvPercent50.setTextColor(getResources().getColor(R.color.black_02));
        this.tvPercent75.setBackgroundResource(R.drawable.roundborder_cdd1da_r4);
        this.tvPercent75.setTextColor(getResources().getColor(R.color.black_02));
        this.tvPercent100.setBackgroundResource(R.drawable.roundborder_cdd1da_r4);
        this.tvPercent100.setTextColor(getResources().getColor(R.color.black_02));
    }
}
