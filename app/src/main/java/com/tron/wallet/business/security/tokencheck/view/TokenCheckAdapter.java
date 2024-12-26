package com.tron.wallet.business.security.tokencheck.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.AssetsConfig;
import com.tron.wallet.business.security.tokencheck.bean.TokenCheckBean;
import com.tron.wallet.business.security.tokencheck.result.risktoken.RiskTokenListFragment;
import com.tron.wallet.business.security.tokencheck.view.TokenCheckAdapter;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.components.TokenLogoDraweeView;
import com.tron.wallet.common.components.mnemonicflowlayout.FlowLayout;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.NumUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.cli.HelpFormatter;
public class TokenCheckAdapter extends BaseQuickAdapter<TokenCheckBean, BaseViewHolder> {
    private List<TokenCheckBean> filteredItems;
    protected ItemCallback itemCallback;
    Context mContext;
    private List<TokenCheckBean> tokenCheckBeanList;
    private int[] touchLocation;

    public interface ItemCallback {
        void onItemClicked(TokenCheckBean tokenCheckBean, int i);

        void onItemLongClicked(View view, TokenCheckBean tokenCheckBean, int[] iArr, int i);

        void onItemSelected(TokenCheckBean tokenCheckBean, int i);
    }

    public TokenCheckAdapter(Context context, int i, List<TokenCheckBean> list, ItemCallback itemCallback) {
        super(R.layout.item_token_check_risk_search_result_list, list);
        this.touchLocation = new int[2];
        this.mContext = context;
        this.tokenCheckBeanList = list;
        this.filteredItems = new ArrayList(list);
        this.itemCallback = itemCallback;
        setEmptyView(createEmptyView(context, i));
    }

    private View createEmptyView(Context context, int i) {
        int i2 = i == RiskTokenListFragment.TYPE_MIDDLE_RISK ? R.string.token_check_risk_general_empty : R.string.token_check_risk_high_empty;
        NoNetView noNetView = new NoNetView(context);
        noNetView.setIcon(R.mipmap.ic_no_data_new).setReloadDescription(i2).setReloadable(false);
        noNetView.setPadding(0, UIUtils.dip2px(60.0f), 0, 0);
        return noNetView;
    }

    public void filterList(String str) {
        if (str.isEmpty()) {
            this.filteredItems = new ArrayList(this.tokenCheckBeanList);
        } else {
            this.filteredItems.clear();
            for (TokenCheckBean tokenCheckBean : this.tokenCheckBeanList) {
                if (tokenCheckBean.getShortName().toLowerCase().contains(str.toLowerCase())) {
                    this.filteredItems.add(tokenCheckBean);
                } else if (StringTronUtil.equals(tokenCheckBean.getContractAddress(), str)) {
                    this.filteredItems.add(tokenCheckBean);
                } else if (StringTronUtil.equals(tokenCheckBean.getTokenId(), str)) {
                    this.filteredItems.add(tokenCheckBean);
                }
            }
        }
        setNewData(this.filteredItems);
        notifyDataSetChanged();
    }

    @Override
    public void convert(final BaseViewHolder baseViewHolder, TokenCheckBean tokenCheckBean) {
        baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$convert$0(baseViewHolder, view);
            }
        });
        baseViewHolder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                boolean lambda$convert$1;
                lambda$convert$1 = lambda$convert$1(view, motionEvent);
                return lambda$convert$1;
            }
        });
        baseViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public final boolean onLongClick(View view) {
                boolean lambda$convert$2;
                lambda$convert$2 = lambda$convert$2(baseViewHolder, view);
                return lambda$convert$2;
            }
        });
        ((TextView) baseViewHolder.getView(R.id.assets_name)).setText(tokenCheckBean.getShortName());
        setLogo((TokenLogoDraweeView) baseViewHolder.getView(R.id.iv_logo), tokenCheckBean);
        setAssetTag((TextView) baseViewHolder.getView(R.id.assets_tag), baseViewHolder.getView(R.id.ll_asset_tag), tokenCheckBean.getType());
        TokenTagFlowLayout tokenTagFlowLayout = (TokenTagFlowLayout) baseViewHolder.getView(R.id.tags);
        TokenTagAdapter tokenTagAdapter = getTokenTagAdapter(this.mContext, LayoutInflater.from(this.mContext), tokenCheckBean);
        if (tokenTagAdapter != null) {
            tokenTagFlowLayout.setAdapter(tokenTagAdapter);
        } else {
            tokenTagFlowLayout.setVisibility(View.INVISIBLE);
        }
        TextView textView = (TextView) baseViewHolder.getView(R.id.assets_count);
        TextView textView2 = (TextView) baseViewHolder.getView(R.id.assets_price);
        TextView textView3 = (TextView) baseViewHolder.getView(R.id.price);
        if (tokenCheckBean.getType().intValue() != 5) {
            setPrice(textView3, tokenCheckBean);
            textView3.setVisibility(View.VISIBLE);
            setAssetsPrice(textView2, tokenCheckBean, SpAPI.THIS.getPrice());
            textView2.setVisibility(View.VISIBLE);
            setAssetsCount(textView, tokenCheckBean);
            textView.setVisibility(View.VISIBLE);
            return;
        }
        textView3.setText(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
        textView2.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);
    }

    public void lambda$convert$0(BaseViewHolder baseViewHolder, View view) {
        ItemCallback itemCallback = this.itemCallback;
        if (itemCallback != null) {
            itemCallback.onItemClicked(getData().get(baseViewHolder.getAdapterPosition()), baseViewHolder.getAdapterPosition());
        }
    }

    public boolean lambda$convert$1(View view, MotionEvent motionEvent) {
        if (motionEvent != null && motionEvent.getAction() == 0) {
            this.touchLocation[0] = (int) motionEvent.getX();
            this.touchLocation[1] = (int) motionEvent.getY();
        }
        return false;
    }

    public boolean lambda$convert$2(BaseViewHolder baseViewHolder, View view) {
        this.itemCallback.onItemLongClicked(view, this.filteredItems.get(baseViewHolder.getAdapterPosition()), this.touchLocation, baseViewHolder.getAdapterPosition());
        return true;
    }

    private void setLogo(TokenLogoDraweeView tokenLogoDraweeView, TokenCheckBean tokenCheckBean) {
        if (!TextUtils.isEmpty(tokenCheckBean.getLogoUrl())) {
            tokenLogoDraweeView.setCircularImage(tokenCheckBean.getLogoUrl());
        } else {
            tokenLogoDraweeView.setImageResource(AssetsConfig.getTokenDefaultLogoIconId(new TokenBean(tokenCheckBean)));
        }
    }

    private void setPrice(TextView textView, TokenCheckBean tokenCheckBean) {
        String usdPrice = SpAPI.THIS.isUsdPrice() ? tokenCheckBean.getUsdPrice() : tokenCheckBean.getCnyPrice();
        if (StringTronUtil.isEmpty(usdPrice)) {
            textView.setText(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
            return;
        }
        if (StringTronUtil.isEmpty(usdPrice) || tokenCheckBean.getLevel().equals(AnalyticsHelper.SelectSendAddress.CLICK_RECENT)) {
            StringBuilder sb = new StringBuilder();
            sb.append(SpAPI.THIS.isUsdPrice() ? "$" : Html.fromHtml("&yen;"));
            sb.append("0");
            textView.setText(sb.toString());
            return;
        }
        double doubleValue = new BigDecimal(usdPrice).doubleValue();
        if (doubleValue < 1.0E-6d) {
            StringBuilder sb2 = new StringBuilder("<");
            sb2.append(SpAPI.THIS.isUsdPrice() ? "$" : Html.fromHtml("&yen;"));
            sb2.append("0.000001");
            textView.setText(sb2.toString());
            return;
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append(SpAPI.THIS.isUsdPrice() ? "$" : Html.fromHtml("&yen;"));
        sb3.append("");
        sb3.append(StringTronUtil.formatNumber6TruncatePlainScientific(doubleValue));
        textView.setText(sb3.toString());
    }

    private void setAssetTag(TextView textView, View view, Integer num) {
        if (num.intValue() == 1) {
            view.setVisibility(View.VISIBLE);
            view.setBackground(this.mContext.getResources().getDrawable(R.drawable.roundborder_153c_2));
            textView.setText(TronConfig.TRC10);
            textView.setTextColor(this.mContext.getResources().getColor(R.color.blue_3c));
        } else if (num.intValue() == 2) {
            view.setVisibility(View.VISIBLE);
            view.setBackground(this.mContext.getResources().getDrawable(R.drawable.roundborder_1557_radius_2));
            textView.setText(TronConfig.TRC20);
            textView.setTextColor(this.mContext.getResources().getColor(R.color.green_89));
        } else if (num.intValue() == 5) {
            view.setVisibility(View.VISIBLE);
            view.setBackground(this.mContext.getResources().getDrawable(R.drawable.roundborder_15ffa9_radius_2));
            textView.setText("TRC721");
            textView.setTextColor(this.mContext.getResources().getColor(R.color.orange_FFC3));
        } else {
            view.setVisibility(View.GONE);
        }
    }

    private static TokenTagAdapter getTokenTagAdapter(final Context context, final LayoutInflater layoutInflater, TokenCheckBean tokenCheckBean) {
        List<TokenCheckTag> handleTags = handleTags(context, tokenCheckBean);
        if (handleTags == null || handleTags.size() == 0) {
            return null;
        }
        return new TokenTagAdapter(handleTags, null) {
            @Override
            public TextView getView(FlowLayout flowLayout, int i, TokenCheckTag tokenCheckTag) {
                int i2;
                int i3;
                int i4;
                TextView textView = (TextView) layoutInflater.inflate(R.layout.token_check_tag_item, (ViewGroup) flowLayout, false);
                textView.setText(tokenCheckTag.content);
                if (tokenCheckTag.getType() == 0) {
                    i2 = R.mipmap.token_tag_arrow_red;
                    i3 = R.color.red_ec;
                    i4 = R.drawable.token_tag_bg_shape_red;
                } else {
                    i2 = R.mipmap.token_tag_arrow_yellow;
                    i3 = R.color.orange_FFA9;
                    i4 = R.drawable.token_tag_bg_shape_yellow;
                }
                textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, context.getResources().getDrawable(i2), (Drawable) null);
                textView.setTextColor(context.getResources().getColor(i3));
                textView.setBackground(context.getResources().getDrawable(i4));
                textView.setOnClickListener(new fun1(tokenCheckTag));
                return textView;
            }

            class fun1 extends NoDoubleClickListener2 {
                final TokenCheckTag val$tokenCheckTag;

                public static void lambda$onNoDoubleClick$0(View view) {
                }

                fun1(TokenCheckTag tokenCheckTag) {
                    this.val$tokenCheckTag = tokenCheckTag;
                }

                @Override
                protected void onNoDoubleClick(View view) {
                    if (!StringTronUtil.isEmpty(this.val$tokenCheckTag.getLogEvent())) {
                        AnalyticsHelper.logEvent(this.val$tokenCheckTag.getLogEvent());
                    }
                    ConfirmCustomPopupView.getBuilder(context).setTitle(this.val$tokenCheckTag.getContent()).setInfo(this.val$tokenCheckTag.getPopInfo()).setBtnStyle(1).setShowCancelBtn(false).setConfirmListener(new View.OnClickListener() {
                        @Override
                        public final void onClick(View view2) {
                            TokenCheckAdapter.1.1.lambda$onNoDoubleClick$0(view2);
                        }
                    }).setConfirmText(context.getString(R.string.token_check_opensource_confirm_btn)).build().show();
                }
            }
        };
    }

    private static List<TokenCheckTag> handleTags(Context context, TokenCheckBean tokenCheckBean) {
        return handlerOpenSource(context, handlerProxy(context, handlerIncreaseTotalSupply(context, handlerBlackListType(context, handlerLevel(context, new ArrayList(), tokenCheckBean.getLevel()), tokenCheckBean.getBlackListType()), tokenCheckBean.getIncreaseTotalSupply()), tokenCheckBean.getIsProxy()), tokenCheckBean.getOpenSource());
    }

    private static ArrayList<TokenCheckTag> handlerBlackListType(Context context, ArrayList<TokenCheckTag> arrayList, Integer num) {
        if (num != null && num.intValue() == 1) {
            TokenCheckTag tokenCheckTag = new TokenCheckTag();
            tokenCheckTag.setContent(context.getResources().getString(R.string.token_check_tag_black_list));
            tokenCheckTag.setPopInfo(context.getResources().getString(R.string.token_check_tag_black_list_info));
            tokenCheckTag.setLogEvent(AnalyticsHelper.SecurityTokenCheckPage.SECURITY_TOKEN_CHECK_CLICK_TAG_HAVE_BLACK_LIST);
            tokenCheckTag.setType(1);
            arrayList.add(tokenCheckTag);
        }
        return arrayList;
    }

    private static ArrayList<TokenCheckTag> handlerProxy(Context context, ArrayList<TokenCheckTag> arrayList, Integer num) {
        if (num != null && num.intValue() == 1) {
            TokenCheckTag tokenCheckTag = new TokenCheckTag();
            tokenCheckTag.setContent(context.getResources().getString(R.string.token_check_tag_isproxy));
            tokenCheckTag.setPopInfo(context.getResources().getString(R.string.token_check_tag_isproxy_info));
            tokenCheckTag.setLogEvent(AnalyticsHelper.SecurityTokenCheckPage.SECURITY_TOKEN_CHECK_CLICK_TAG_HAVE_PROXY_CONTRACT);
            tokenCheckTag.setType(1);
            arrayList.add(tokenCheckTag);
        }
        return arrayList;
    }

    private static ArrayList<TokenCheckTag> handlerOpenSource(Context context, ArrayList<TokenCheckTag> arrayList, Integer num) {
        if (num != null && num.intValue() == 0) {
            TokenCheckTag tokenCheckTag = new TokenCheckTag();
            tokenCheckTag.setContent(context.getResources().getString(R.string.token_check_tag_not_opensource));
            tokenCheckTag.setPopInfo(context.getResources().getString(R.string.token_check_tag_not_opensource_info));
            tokenCheckTag.setLogEvent(AnalyticsHelper.SecurityTokenCheckPage.SECURITY_TOKEN_CHECK_CLICK_TAG_CLOSE_OPEN_SOURCE);
            tokenCheckTag.setType(1);
            arrayList.add(tokenCheckTag);
        }
        return arrayList;
    }

    private static ArrayList<TokenCheckTag> handlerIncreaseTotalSupply(Context context, ArrayList<TokenCheckTag> arrayList, Integer num) {
        if (num != null && num.intValue() == 1) {
            TokenCheckTag tokenCheckTag = new TokenCheckTag();
            tokenCheckTag.setContent(context.getResources().getString(R.string.token_check_tag_increase_total_supply));
            tokenCheckTag.setPopInfo(context.getResources().getString(R.string.token_check_tag_increase_total_supply_info));
            tokenCheckTag.setLogEvent(AnalyticsHelper.SecurityTokenCheckPage.SECURITY_TOKEN_CHECK_CLICK_TAG_SUPPORT_SELF_MINTING);
            tokenCheckTag.setType(1);
            arrayList.add(tokenCheckTag);
        }
        return arrayList;
    }

    private static ArrayList<TokenCheckTag> handlerLevel(Context context, ArrayList<TokenCheckTag> arrayList, String str) {
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        TokenCheckTag tokenCheckTag = new TokenCheckTag();
        int parseInt = Integer.parseInt(str);
        if (parseInt == 3) {
            tokenCheckTag.setContent(context.getResources().getString(R.string.token_check_tag_risk));
            tokenCheckTag.setType(0);
            tokenCheckTag.setPopInfo(context.getResources().getString(R.string.token_check_tag_risk_info));
            tokenCheckTag.setLogEvent(AnalyticsHelper.SecurityTokenCheckPage.SECURITY_TOKEN_CHECK_CLICK_TAG_SUSPICIOUS);
            arrayList.add(tokenCheckTag);
        } else if (parseInt == 4) {
            tokenCheckTag.setContent(context.getResources().getString(R.string.token_check_tag_scam));
            tokenCheckTag.setType(0);
            tokenCheckTag.setPopInfo(context.getResources().getString(R.string.token_check_tag_scam_info));
            tokenCheckTag.setLogEvent(AnalyticsHelper.SecurityTokenCheckPage.SECURITY_TOKEN_CHECK_CLICK_TAG_UNSAFE);
            arrayList.add(tokenCheckTag);
        }
        return arrayList;
    }

    private void setAssetsCount(TextView textView, TokenCheckBean tokenCheckBean) {
        if (isNotEllipsized(textView, StringTronUtil.formatNumber6Truncate(BigDecimalUtils.toBigDecimal(tokenCheckBean.getBalanceStr())))) {
            textView.setText(StringTronUtil.formatNumber6Truncate(BigDecimalUtils.toBigDecimal(tokenCheckBean.getBalanceStr())));
        } else if (isNotEllipsized(textView, StringTronUtil.formatNumber0Truncate(BigDecimalUtils.toBigDecimal(tokenCheckBean.getBalanceStr())))) {
            textView.setText(StringTronUtil.formatNumber0Truncate(BigDecimalUtils.toBigDecimal(tokenCheckBean.getBalanceStr())));
        } else {
            textView.setText(NumUtils.amountConversion(Double.parseDouble(tokenCheckBean.getBalanceStr()), false, tokenCheckBean.getBalanceStr()));
        }
    }

    private void setAssetsPrice(TextView textView, TokenCheckBean tokenCheckBean, double d) {
        if (StringTronUtil.isEmpty(SpAPI.THIS.isUsdPrice() ? tokenCheckBean.getUsdPrice() : tokenCheckBean.getCnyPrice())) {
            textView.setText(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
            return;
        }
        String tokenTotalPrice = CheckTokenTotalPriceUtils.getTokenTotalPrice(tokenCheckBean);
        if (isNotEllipsized(textView, tokenTotalPrice)) {
            BigDecimal tokenTotalPriceNumber = CheckTokenTotalPriceUtils.getTokenTotalPriceNumber(tokenCheckBean);
            if (BigDecimalUtils.MoreThan("0.001", tokenTotalPriceNumber) && !BigDecimalUtils.equalsZero((Number) tokenTotalPriceNumber)) {
                textView.setText(StringTronUtil.formatPriceLessThan(Double.valueOf(0.001d)));
                return;
            } else {
                textView.setText(tokenTotalPrice);
                return;
            }
        }
        BigDecimal tokenTotalPriceNumber2 = CheckTokenTotalPriceUtils.getTokenTotalPriceNumber(tokenCheckBean);
        String formatPrice0 = StringTronUtil.formatPrice0(tokenTotalPriceNumber2);
        if (isNotEllipsized(textView, formatPrice0)) {
            textView.setText(formatPrice0);
            return;
        }
        try {
            Object[] objArr = new Object[2];
            objArr[0] = SpAPI.THIS.isUsdPrice() ? "$" : Html.fromHtml("&yen;");
            objArr[1] = NumUtils.amountConversion(tokenTotalPriceNumber2.doubleValue());
            textView.setText(String.format("%s%s", objArr));
        } catch (Throwable th) {
            LogUtils.e(th);
        }
    }

    private boolean isNotEllipsized(TextView textView, String str) {
        return StringTronUtil.equals(str, getEllipsizedText(textView, str));
    }

    private String getEllipsizedText(TextView textView, String str) {
        return TextUtils.ellipsize(str, textView.getPaint(), UIUtils.dip2px(180.0f), textView.getEllipsize()).toString();
    }
}
