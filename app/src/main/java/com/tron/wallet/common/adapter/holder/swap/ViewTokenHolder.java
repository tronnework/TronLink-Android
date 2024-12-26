package com.tron.wallet.common.adapter.holder.swap;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.business.tabswap.bean.SwapTokenBean;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.databinding.SwapHeaderViewTokenBinding;
import com.tronlinkpro.wallet.R;
public class ViewTokenHolder extends BaseSwapAdapterHolder {
    private Pair<SwapTokenBean, SwapTokenBean> tokens;
    TextView tvTokenFrom;
    TextView tvTokenTo;

    public ViewTokenHolder(Context context) {
        super(context, R.layout.swap_header_view_token);
        SwapHeaderViewTokenBinding bind = SwapHeaderViewTokenBinding.bind(this.itemView);
        this.tvTokenFrom = bind.tvTokenFrom;
        this.tvTokenTo = bind.tvTokenTo;
        this.tvTokenFrom.setOnClickListener(getOnClickListener());
        this.tvTokenTo.setOnClickListener(getOnClickListener());
    }

    private View.OnClickListener getOnClickListener() {
        return new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id == R.id.tv_token_from) {
                    if (tokens != null) {
                        ViewTokenHolder viewTokenHolder = ViewTokenHolder.this;
                        viewTokenHolder.viewTokenWeb((SwapTokenBean) viewTokenHolder.tokens.first);
                    }
                } else if (id == R.id.tv_token_to && tokens != null) {
                    ViewTokenHolder viewTokenHolder2 = ViewTokenHolder.this;
                    viewTokenHolder2.viewTokenWeb((SwapTokenBean) viewTokenHolder2.tokens.second);
                }
            }
        };
    }

    @Override
    public void onBind(Pair<SwapTokenBean, SwapTokenBean> pair) {
        super.onBind(pair);
        this.tokens = pair;
        if (pair == null) {
            return;
        }
        setTokenText(this.tvTokenFrom, (SwapTokenBean) pair.first);
        setTokenText(this.tvTokenTo, (SwapTokenBean) this.tokens.second);
    }

    private void setTokenText(TextView textView, SwapTokenBean swapTokenBean) {
        if (swapTokenBean == null) {
            return;
        }
        textView.setText(swapTokenBean.getSymbol());
        textView.setEnabled(!TextUtils.equals(swapTokenBean.getSymbol(), "TRX"));
    }

    public void viewTokenWeb(SwapTokenBean swapTokenBean) {
        if (swapTokenBean == null) {
            IToast.getIToast().show(R.string.wait_loading);
            return;
        }
        String tronscan20TokenIntroduceUrl = IRequest.getTronscan20TokenIntroduceUrl();
        if (TextUtils.isEmpty(tronscan20TokenIntroduceUrl) || TextUtils.isEmpty(swapTokenBean.getToken())) {
            IToast.getIToast().show(R.string.wait_loading);
            return;
        }
        String handleUrl = handleUrl(String.format("%s%s", tronscan20TokenIntroduceUrl, swapTokenBean.getToken()));
        LogUtils.w("SwapPresenter", handleUrl);
        CommonWebActivityV3.start(this.context, handleUrl, swapTokenBean.getSymbol(), 1, true);
    }

    private String handleUrl(String str) {
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return str + "?lang=zh";
        }
        return str + "?lang=en";
    }
}
