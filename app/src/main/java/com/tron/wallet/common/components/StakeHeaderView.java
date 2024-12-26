package com.tron.wallet.common.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.HeaderViewBinding;
import com.tronlinkpro.wallet.R;
public class StakeHeaderView extends RelativeLayout {
    HeaderViewBinding binding;
    public ImageView ivCommonLeft;
    public ImageView ivIconV2;
    public ImageView ivQuestion;
    NoDoubleClickListener listener;
    View llCommonLeft;
    private OnHeaderClickListener onHeaderClickListener;
    TextView tvMulti;
    TextView tvTitle;
    TextView tvTitle2;

    public interface OnHeaderClickListener {

        public final class -CC {
            public static void $default$onLeftClick(OnHeaderClickListener _this) {
            }

            public static void $default$onQuestion(OnHeaderClickListener _this) {
            }

            public static void $default$onRightClick(OnHeaderClickListener _this) {
            }
        }

        void onLeftClick();

        void onQuestion();

        void onRightClick();
    }

    public void setOnHeaderClickListener(OnHeaderClickListener onHeaderClickListener) {
        this.onHeaderClickListener = onHeaderClickListener;
    }

    public void showQuestion() {
        this.ivQuestion.setVisibility(View.VISIBLE);
    }

    public void hideIconV2() {
        this.ivIconV2.setVisibility(View.GONE);
    }

    public void setHeader(String str, String str2, String str3) {
        if (!StringTronUtil.isEmpty(str)) {
            this.tvTitle.setText(str);
        }
        if (!StringTronUtil.isEmpty(str2)) {
            this.tvTitle2.setVisibility(View.VISIBLE);
            this.tvTitle2.setText(str2);
        } else {
            this.tvTitle2.setVisibility(View.GONE);
        }
        if (!StringTronUtil.isEmpty(str3)) {
            this.tvMulti.setVisibility(View.VISIBLE);
            this.tvMulti.setText(str3);
            return;
        }
        this.tvMulti.setVisibility(View.GONE);
    }

    public StakeHeaderView(Context context) {
        super(context);
        this.listener = new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id == R.id.iv_question) {
                    if (onHeaderClickListener != null) {
                        onHeaderClickListener.onQuestion();
                    }
                } else if (id == R.id.ll_common_left) {
                    if (onHeaderClickListener != null) {
                        onHeaderClickListener.onLeftClick();
                    }
                } else if (id == R.id.tv_multi && onHeaderClickListener != null) {
                    onHeaderClickListener.onRightClick();
                }
            }
        };
        addHeader(context);
    }

    public StakeHeaderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.listener = new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id == R.id.iv_question) {
                    if (onHeaderClickListener != null) {
                        onHeaderClickListener.onQuestion();
                    }
                } else if (id == R.id.ll_common_left) {
                    if (onHeaderClickListener != null) {
                        onHeaderClickListener.onLeftClick();
                    }
                } else if (id == R.id.tv_multi && onHeaderClickListener != null) {
                    onHeaderClickListener.onRightClick();
                }
            }
        };
        addHeader(context);
    }

    public StakeHeaderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.listener = new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id == R.id.iv_question) {
                    if (onHeaderClickListener != null) {
                        onHeaderClickListener.onQuestion();
                    }
                } else if (id == R.id.ll_common_left) {
                    if (onHeaderClickListener != null) {
                        onHeaderClickListener.onLeftClick();
                    }
                } else if (id == R.id.tv_multi && onHeaderClickListener != null) {
                    onHeaderClickListener.onRightClick();
                }
            }
        };
        addHeader(context);
    }

    private void addHeader(Context context) {
        View inflate = View.inflate(context, R.layout.header_view, null);
        this.binding = HeaderViewBinding.bind(inflate);
        mappingId();
        this.tvMulti.setOnClickListener(this.listener);
        this.ivQuestion.setOnClickListener(this.listener);
        this.llCommonLeft.setOnClickListener(this.listener);
        addView(inflate);
    }

    private void mappingId() {
        this.tvTitle = this.binding.tvCommonTitle;
        this.tvTitle2 = this.binding.tvCommonTitle2;
        this.tvMulti = this.binding.tvMulti;
        this.llCommonLeft = this.binding.llCommonLeft;
        this.ivQuestion = this.binding.ivQuestion;
        this.ivIconV2 = this.binding.ivStakeV2;
        this.ivCommonLeft = this.binding.ivCommonLeft;
    }

    public void setTvMultiGone() {
        this.tvMulti.setVisibility(View.GONE);
    }

    public void setStyleLight() {
        this.tvTitle.setTextColor(getResources().getColor(R.color.white));
        this.ivCommonLeft.setBackgroundResource(R.mipmap.ic_back_white);
        this.tvMulti.setTextColor(getResources().getColor(R.color.white));
    }
}
