package com.tron.wallet.business.assetshome.tipview;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.databinding.LlSigndealTipBinding;
import org.tron.walletserver.Wallet;
public class MultiSignTipView implements ITipView {
    private LlSigndealTipBinding binding;
    View btnGo;
    private String content;
    View ivClose;
    private View.OnClickListener onClose;
    private View.OnClickListener onGo;
    private int state = 1;
    private View tipView;
    TextView tvDesc;
    TextView tvGo;
    TextView tvTitle;

    @Override
    public int getState() {
        return this.state;
    }

    public MultiSignTipView(Context context, View view, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        mappingId(view);
        this.tipView = view;
        this.onClose = onClickListener;
        this.onGo = onClickListener2;
    }

    public void mappingId(View view) {
        LlSigndealTipBinding bind = LlSigndealTipBinding.bind(view);
        this.binding = bind;
        this.tvTitle = bind.tvSignTitle;
        this.ivClose = this.binding.ivSignClose;
        this.tvDesc = this.binding.tvSignDesc;
        this.btnGo = this.binding.llSignArrow;
        this.tvGo = this.binding.tvGoNow;
    }

    private void initUI() {
        this.tipView.setVisibility(View.VISIBLE);
        TextView textView = this.tvDesc;
        String str = this.content;
        if (str == null) {
            str = "";
        }
        textView.setText(str);
        this.ivClose.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (onClose != null) {
                    onClose.onClick(tipView);
                }
                hide();
            }
        });
        this.btnGo.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (onGo != null) {
                    onGo.onClick(tipView);
                } else {
                    hide();
                }
            }
        });
    }

    @Override
    public String getId() {
        return TipViewType.MULTI_SIGN.getId();
    }

    @Override
    public int getPriority() {
        return TipViewType.MULTI_SIGN.getPriority();
    }

    @Override
    public void setState(int i, String... strArr) {
        if (i == 3 && strArr != null && strArr.length == 1) {
            this.content = strArr[0];
            this.state = 3;
            return;
        }
        this.state = 2;
        this.content = null;
    }

    @Override
    public void show() {
        if (this.state == 3) {
            this.state = 4;
            initUI();
        }
    }

    @Override
    public void hide() {
        if (this.state != 4) {
            this.state = 2;
            return;
        }
        this.state = 5;
        this.tipView.setVisibility(View.GONE);
    }

    @Override
    public void onWalletChanged(Wallet wallet) {
        if (this.state == 4) {
            this.tipView.setVisibility(View.GONE);
        }
        this.state = 1;
    }
}
