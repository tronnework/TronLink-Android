package com.tron.wallet.common.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.MessageCenterHeaderViewBinding;
import com.tronlinkpro.wallet.R;
public class MessageHeaderView extends RelativeLayout {
    MessageCenterHeaderViewBinding binding;
    public ImageView ivCommonLeft;
    public ImageView ivQuestion;
    NoDoubleClickListener listener;
    public View llCommonLeft;
    private OnHeaderClickListener onHeaderClickListener;
    TextView tvTitle;

    public interface OnHeaderClickListener {

        public final class -CC {
            public static void $default$onLeftClick(OnHeaderClickListener _this) {
            }

            public static void $default$onQuestion(OnHeaderClickListener _this) {
            }
        }

        void onLeftClick();

        void onQuestion();
    }

    public void setOnHeaderClickListener(OnHeaderClickListener onHeaderClickListener) {
        this.onHeaderClickListener = onHeaderClickListener;
    }

    public void showQuestion() {
        this.ivQuestion.setVisibility(View.VISIBLE);
    }

    public void setHeader(String str, String str2, String str3) {
        if (StringTronUtil.isEmpty(str)) {
            return;
        }
        this.tvTitle.setText(str);
    }

    public MessageHeaderView(Context context) {
        super(context);
        this.listener = new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id != R.id.iv_question) {
                    if (id == R.id.ll_common_left && onHeaderClickListener != null) {
                        onHeaderClickListener.onLeftClick();
                    }
                } else if (onHeaderClickListener != null) {
                    onHeaderClickListener.onQuestion();
                }
            }
        };
        addHeader(context);
    }

    public MessageHeaderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.listener = new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id != R.id.iv_question) {
                    if (id == R.id.ll_common_left && onHeaderClickListener != null) {
                        onHeaderClickListener.onLeftClick();
                    }
                } else if (onHeaderClickListener != null) {
                    onHeaderClickListener.onQuestion();
                }
            }
        };
        addHeader(context);
    }

    public MessageHeaderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.listener = new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id != R.id.iv_question) {
                    if (id == R.id.ll_common_left && onHeaderClickListener != null) {
                        onHeaderClickListener.onLeftClick();
                    }
                } else if (onHeaderClickListener != null) {
                    onHeaderClickListener.onQuestion();
                }
            }
        };
        addHeader(context);
    }

    private void addHeader(Context context) {
        View inflate = View.inflate(context, R.layout.message_center_header_view, null);
        this.binding = MessageCenterHeaderViewBinding.bind(inflate);
        mappingId();
        this.ivQuestion.setOnClickListener(this.listener);
        this.llCommonLeft.setOnClickListener(this.listener);
        addView(inflate);
    }

    private void mappingId() {
        this.tvTitle = this.binding.tvCommonTitle;
        this.llCommonLeft = this.binding.llCommonLeft;
        this.ivQuestion = this.binding.ivQuestion;
        this.ivCommonLeft = this.binding.ivCommonLeft;
    }

    public void setStyleLight() {
        this.tvTitle.setTextColor(getResources().getColor(R.color.white));
        this.ivCommonLeft.setBackgroundResource(R.mipmap.ic_back_white);
    }
}
