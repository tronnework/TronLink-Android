package com.tron.wallet.common.adapter.holder.swap;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tron.wallet.business.tabswap.bean.SwapInfoOutput;
import com.tron.wallet.business.tabswap.bean.SwapTokenBean;
import com.tron.wallet.business.tabswap.utils.SwapTokenIconHelper;
import com.tron.wallet.business.tabswap.utils.SwapTokenSymbolHelper;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.components.InfoPopupWindow;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.SwapTokenInfoBinding;
import com.tronlinkpro.wallet.R;
import j$.util.Collection;
import j$.util.Objects;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
public class TokenInfoHolder extends BaseSwapAdapterHolder {
    private static final String JUST_SWAP_HOME = "https://sun.io/?lang=zh-CN#/home";
    private static final String JUST_SWAP_TITLE = "SunSwap";
    public static final int PAYLOAD_INFO_VISIBLE = 1;
    public static final int PAYLOAD_LOADING_STATE = 2;
    View enterSwapRouter;
    ImageView ivHelpFee;
    ImageView ivHelpMinReceived;
    ImageView ivHelpPriceImpact;
    View ivJustSwapLogo;
    View llRoot;
    ViewGroup llSwapRouter;
    ImageView loadingView;
    View rlContainer;
    private Pair<SwapTokenBean, SwapTokenBean> tokens;
    TextView tvFee;
    TextView tvMinRec;
    TextView tvMinRecTips;
    TextView tvPriceImpact;
    TextView tvRate;

    public TokenInfoHolder(Context context) {
        super(context, R.layout.swap_token_info);
        SwapTokenInfoBinding bind = SwapTokenInfoBinding.bind(this.itemView);
        this.ivJustSwapLogo = bind.ivJustSwapLogo;
        this.tvRate = bind.tvRateNumber;
        this.tvFee = bind.tvFeeNumber;
        this.tvPriceImpact = bind.tvPriceImpactNumber;
        this.tvMinRec = bind.tvMinReceivedNumber;
        this.tvMinRecTips = bind.tvMinReceived;
        this.ivHelpFee = bind.ivHelpFee;
        this.ivHelpPriceImpact = bind.ivHelpPriceImpact;
        this.ivHelpMinReceived = bind.ivHelpMinReceived;
        this.rlContainer = bind.rlContainer;
        this.llRoot = bind.llRoot;
        this.loadingView = bind.ivLoading;
        this.enterSwapRouter = bind.ivEnterSwapRouter;
        this.llSwapRouter = bind.llSwapRouterContent;
        Collection.-EL.stream(Arrays.asList(this.ivJustSwapLogo, this.ivHelpFee, this.ivHelpMinReceived, this.ivHelpPriceImpact)).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$new$0((View) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        int dip2px = UIUtils.dip2px(20.0f);
        TouchDelegateUtils.expandViewTouchDelegate(this.ivHelpFee, dip2px, dip2px, dip2px, dip2px);
        TouchDelegateUtils.expandViewTouchDelegate(this.ivHelpMinReceived, dip2px, dip2px, dip2px, dip2px);
        TouchDelegateUtils.expandViewTouchDelegate(this.ivHelpPriceImpact, dip2px, dip2px, dip2px, dip2px);
    }

    public void lambda$new$0(View view) {
        view.setOnClickListener(getOnClickListener());
    }

    private View.OnClickListener getOnClickListener() {
        return new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                switch (view.getId()) {
                    case R.id.iv_help_fee:
                        InfoPopupWindow.showPop(ivHelpFee, R.string.swap_fee_tip);
                        return;
                    case R.id.iv_help_min_received:
                        InfoPopupWindow.showPop(ivHelpMinReceived, R.string.swap_question_min_received);
                        return;
                    case R.id.iv_help_price_impact:
                        InfoPopupWindow.showPop(ivHelpPriceImpact, R.string.swap_question_price_impact);
                        return;
                    case R.id.iv_just_swap_logo:
                        enterJustSwapWeb();
                        return;
                    default:
                        return;
                }
            }
        };
    }

    public void enterJustSwapWeb() {
        CommonWebActivityV3.start(this.context, JUST_SWAP_HOME, JUST_SWAP_TITLE, 1, true);
        AnalyticsHelper.logEvent(AnalyticsHelper.MarketPage.CLICK_MARKET_PAGE_SWAP_BANNER_SUN);
    }

    @Override
    public void onBind(android.util.Pair<com.tron.wallet.business.tabswap.bean.SwapTokenBean, com.tron.wallet.business.tabswap.bean.SwapTokenBean> r7, com.tron.wallet.business.tabswap.bean.SwapTokenInfoBean r8, java.util.List<com.tron.wallet.business.tabswap.bean.SwapInfoOutput.InfoData> r9, int r10, android.view.View.OnClickListener r11, boolean r12) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.common.adapter.holder.swap.TokenInfoHolder.onBind(android.util.Pair, com.tron.wallet.business.tabswap.bean.SwapTokenInfoBean, java.util.List, int, android.view.View$OnClickListener, boolean):void");
    }

    public void updateTokenInfo(String str, String str2, String str3, String str4, SwapInfoOutput.InfoData infoData) {
        setRate(str, ((SwapTokenBean) this.tokens.second).getDecimal(), ((SwapTokenBean) this.tokens.first).getSymbol(), ((SwapTokenBean) this.tokens.second).getSymbol());
        setTextInfo(R.string.swap_min_received, str3, ((SwapTokenBean) this.tokens.second).getDecimal(), ((SwapTokenBean) this.tokens.second).getSymbol());
        if (!TextUtils.isEmpty(str2)) {
            this.tvFee.setText(String.format("%s %s", StringTronUtil.formatNumberTruncateNoDots(BigDecimalUtils.toBigDecimal(str2), ((SwapTokenBean) this.tokens.first).getDecimal()), ((SwapTokenBean) this.tokens.first).getSymbol()));
        }
        if (!TextUtils.isEmpty(str4)) {
            TextView textView = this.tvPriceImpact;
            textView.setText(new BigDecimal(str4).multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString() + "%");
        }
        if (infoData != null) {
            buildSwapRouter(infoData);
        }
        updateLoadingViewState(false);
    }

    private void buildSwapRouter(SwapInfoOutput.InfoData infoData) {
        this.llSwapRouter.removeAllViews();
        Context context = this.itemView.getContext();
        List<String> tokens = infoData.getTokens();
        for (int i = 0; i < tokens.size(); i++) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.swap_router_simple_tag, this.llSwapRouter, false);
            View findViewById = inflate.findViewById(R.id.iv_arrow_right);
            String str = infoData.getSymbols().get(i);
            setSwapRouterTokenIcon((SimpleDraweeView) inflate.findViewById(R.id.iv_logo), infoData.getTokens().get(i), str);
            ((TextView) inflate.findViewById(R.id.tv_symbol)).setText(SwapTokenSymbolHelper.getSymbol(str, tokens.get(i)));
            if (i < tokens.size() - 1) {
                findViewById.setVisibility(View.VISIBLE);
            } else {
                findViewById.setVisibility(View.GONE);
            }
            this.llSwapRouter.addView(inflate);
        }
    }

    private void setSwapRouterTokenIcon(SimpleDraweeView simpleDraweeView, String str, String str2) {
        Object findTokenIcon = SwapTokenIconHelper.getInstance().findTokenIcon(this.itemView.getContext(), str2, str);
        if (findTokenIcon instanceof Integer) {
            simpleDraweeView.setImageResource(((Integer) findTokenIcon).intValue());
            return;
        }
        if (findTokenIcon instanceof String) {
            String str3 = (String) findTokenIcon;
            if (!TextUtils.isEmpty(str3)) {
                simpleDraweeView.setImageURI(Uri.parse(str3));
                return;
            }
        }
        simpleDraweeView.setImageResource(R.mipmap.ic_token_default);
    }

    private void setRate(String str, int i, String str2, String str3) {
        if (TextUtils.equals(TokenHolder.PLACE_HOLDER, str)) {
            this.tvRate.setText(String.format("1 %s = %s %s", str2, str, str3));
        } else if (!TextUtils.isEmpty(str) && BigDecimalUtils.moreThanOrEqual(str, 0)) {
            double pow = Math.pow(10.0d, i * (-1));
            if (BigDecimalUtils.moreThanOrEqual(str, Double.valueOf(pow))) {
                this.tvRate.setText(String.format("1 %s = %s %s", str2, StringTronUtil.plainScientificNotation(BigDecimalUtils.toBigDecimal(str), i), str3));
            } else {
                this.tvRate.setText(String.format("1 %s < %s %s", str2, StringTronUtil.plainScientificNotation(Double.valueOf(pow)), str3));
            }
        } else {
            this.tvRate.setText(String.format("1 %s = %s %s", str2, TokenHolder.PLACE_HOLDER, str3));
        }
    }

    private void setTextInfo(int i, String str, int i2, String str2) {
        this.tvMinRecTips.setText(i);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.tvMinRec.setText(String.format("%s %s", StringTronUtil.plainScientificNotation(BigDecimalUtils.toBigDecimal(str), i2), str2));
    }

    public void updateInfoVisibility(boolean z) {
        this.rlContainer.setVisibility(z ? View.VISIBLE : View.GONE);
    }

    public void notifyPayLoads(List<Object> list) {
        if (list.get(0) instanceof List) {
            List list2 = (List) list.get(0);
            if (list2.isEmpty() || list2.size() != 2) {
                return;
            }
            int intValue = ((Integer) list2.get(0)).intValue();
            if (intValue == 1) {
                updateInfoVisibility(((Boolean) list2.get(1)).booleanValue());
            } else if (intValue == 2) {
                updateLoadingViewState(((Boolean) list2.get(1)).booleanValue());
            }
        }
    }

    private void updateLoadingViewState(boolean z) {
        if (z) {
            this.loadingView.setVisibility(View.VISIBLE);
            RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
            rotateAnimation.setDuration(1000L);
            rotateAnimation.setRepeatCount(-1);
            rotateAnimation.setRepeatMode(1);
            this.loadingView.startAnimation(rotateAnimation);
            this.loadingView.postDelayed(new Runnable() {
                @Override
                public final void run() {
                    lambda$updateLoadingViewState$1();
                }
            }, 1000L);
            return;
        }
        this.loadingView.setVisibility(View.GONE);
    }

    public void lambda$updateLoadingViewState$1() {
        this.loadingView.setVisibility(View.GONE);
    }
}
