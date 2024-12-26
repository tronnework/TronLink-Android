package com.tron.wallet.common.utils;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.wallet.common.bean.SpannableConfirmExtraTextClickable;
import com.tron.wallet.common.bean.token.OfficialType;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.CenterSpaceImageSpan;
import com.tron.wallet.common.components.ItemWarningTagView;
import com.tron.wallet.common.components.MultiLineTextPopupView;
import com.tron.wallet.common.utils.TokenItemUtil;
import com.tronlinkpro.wallet.R;
public class TokenItemUtil {
    public static void initNationalFlagView(Context context, ImageView imageView, String str, String str2) {
        initNationalFlagView(context, imageView, str, str2, false);
    }

    public static void initNationalFlagView(final Context context, ImageView imageView, String str, final String str2, final boolean z) {
        if (StringTronUtil.isEmpty(str) || str.split(",").length == 0) {
            imageView.setVisibility(View.GONE);
            return;
        }
        TouchDelegateUtils.expandViewTouchDelegate(imageView, 6, 8, 20, 8);
        if (str.contains("DM")) {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(R.mipmap.ic_token_national_dm);
            imageView.setOnClickListener(new NoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    new MultiLineTextPopupView.Builder().setAnchor(view).setPreferredShowUp(z).setRequiredWidth(UIUtils.dip2px(288.0f)).addKeyValue(String.format(context.getString(R.string.asset_national_tip), str2), "").build(context).show();
                }
            });
        }
    }

    public static void initScamFlagView(Context context, ImageView imageView, TokenBean tokenBean, boolean z) {
        TouchDelegateUtils.expandViewTouchDelegate(imageView, 6, 8, 20, 8);
        if (OfficialType.isScamOrUnSafeToken(tokenBean)) {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(R.mipmap.ic_asset_scam);
            imageView.setOnClickListener(new fun2(context, tokenBean, z));
            return;
        }
        imageView.setVisibility(View.GONE);
    }

    class fun2 extends NoDoubleClickListener {
        final Context val$context;
        final TokenBean val$tokenBean;
        final boolean val$upToAnchor;

        fun2(Context context, TokenBean tokenBean, boolean z) {
            this.val$context = context;
            this.val$tokenBean = tokenBean;
            this.val$upToAnchor = z;
        }

        @Override
        protected void onNoDoubleClick(View view) {
            SpannableConfirmExtraTextClickable spannableConfirmExtraTextClickable = new SpannableConfirmExtraTextClickable();
            String string = this.val$context.getString(R.string.asset_evaluate_type_scam);
            if (OfficialType.isAtRiskToken(this.val$tokenBean)) {
                string = this.val$context.getString(R.string.asset_evaluate_type_scam);
            } else if (OfficialType.isUnSafeToken(this.val$tokenBean)) {
                string = this.val$context.getString(R.string.asset_evaluate_type_risk);
            }
            String format = String.format(this.val$context.getString(R.string.asset_scam_tip), string);
            SpannableString spannableString = new SpannableString(format);
            spannableString.setSpan(new ForegroundColorSpan(this.val$context.getResources().getColor(R.color.blue_3c)), format.indexOf(this.val$context.getString(R.string.asset_display)), spannableString.length(), 33);
            spannableConfirmExtraTextClickable.setRight(spannableString);
            final TokenBean tokenBean = this.val$tokenBean;
            final Context context = this.val$context;
            spannableConfirmExtraTextClickable.setOnItemClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view2) {
                    TokenItemUtil.2.lambda$onNoDoubleClick$0(TokenBean.this, context, view2);
                }
            });
            spannableConfirmExtraTextClickable.setTextGravity(3);
            new MultiLineTextPopupView.Builder().setAnchor(view).setPreferredShowUp(this.val$upToAnchor).setRequiredWidth(UIUtils.dip2px(288.0f)).setItemPadding(UIUtils.dip2px(5.0f)).addItem(spannableConfirmExtraTextClickable).build(this.val$context).show();
        }

        public static void lambda$onNoDoubleClick$0(TokenBean tokenBean, Context context, View view) {
            if (tokenBean == null) {
                return;
            }
            if (tokenBean.type == 1) {
                UIUtils.toTrc10TokenProtocol(context, tokenBean.getId() + "");
            } else if (tokenBean != null && tokenBean.type == 2) {
                UIUtils.toTrc20TokenDetail(context, tokenBean.getContractAddress());
            } else {
                UIUtils.toTrc20TokenDetail(context, tokenBean.getContractAddress());
            }
        }
    }

    public static void initUnKnownScamFlagView(Context context, ImageView imageView, TokenBean tokenBean, boolean z) {
        TouchDelegateUtils.expandViewTouchDelegate(imageView, 6, 8, 20, 8);
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageResource(R.mipmap.ic_asset_scam);
        imageView.setOnClickListener(new fun3(context, tokenBean, z));
    }

    class fun3 extends NoDoubleClickListener {
        final Context val$context;
        final TokenBean val$tokenBean;
        final boolean val$upToAnchor;

        fun3(Context context, TokenBean tokenBean, boolean z) {
            this.val$context = context;
            this.val$tokenBean = tokenBean;
            this.val$upToAnchor = z;
        }

        @Override
        protected void onNoDoubleClick(View view) {
            SpannableConfirmExtraTextClickable spannableConfirmExtraTextClickable = new SpannableConfirmExtraTextClickable();
            String string = this.val$context.getString(R.string.asset_evaluate_type_risk);
            if (OfficialType.isAtRiskToken(this.val$tokenBean)) {
                string = this.val$context.getString(R.string.asset_evaluate_type_scam);
            } else if (OfficialType.isUnSafeToken(this.val$tokenBean)) {
                string = this.val$context.getString(R.string.asset_evaluate_type_risk);
            }
            String format = String.format(this.val$context.getString(R.string.asset_scam_tip), string);
            SpannableString spannableString = new SpannableString(format);
            spannableString.setSpan(new ForegroundColorSpan(this.val$context.getResources().getColor(R.color.blue_3c)), format.indexOf(this.val$context.getString(R.string.asset_display)), spannableString.length(), 33);
            spannableConfirmExtraTextClickable.setRight(spannableString);
            final TokenBean tokenBean = this.val$tokenBean;
            final Context context = this.val$context;
            spannableConfirmExtraTextClickable.setOnItemClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view2) {
                    TokenItemUtil.3.lambda$onNoDoubleClick$0(TokenBean.this, context, view2);
                }
            });
            spannableConfirmExtraTextClickable.setTextGravity(3);
            new MultiLineTextPopupView.Builder().setAnchor(view).setPreferredShowUp(this.val$upToAnchor).setRequiredWidth(UIUtils.dip2px(288.0f)).setItemPadding(UIUtils.dip2px(5.0f)).addItem(spannableConfirmExtraTextClickable).build(this.val$context).show();
        }

        public static void lambda$onNoDoubleClick$0(TokenBean tokenBean, Context context, View view) {
            if (tokenBean == null) {
                return;
            }
            if (tokenBean.type == 1) {
                UIUtils.toTrc10TokenProtocol(context, tokenBean.getId() + "");
            } else if (tokenBean != null && tokenBean.type == 2) {
                UIUtils.toTrc20TokenDetail(context, tokenBean.getContractAddress());
            } else {
                UIUtils.toTrc20TokenDetail(context, tokenBean.getContractAddress());
            }
        }
    }

    public static void initScamFlagTipsView(Context context, ImageView imageView, TokenBean tokenBean, boolean z) {
        TouchDelegateUtils.expandViewTouchDelegate(imageView, 6, 8, 20, 8);
        imageView.setVisibility(View.VISIBLE);
        imageView.setOnClickListener(new fun4(context, z));
    }

    class fun4 extends NoDoubleClickListener {
        final Context val$context;
        final boolean val$upToAnchor;

        fun4(Context context, boolean z) {
            this.val$context = context;
            this.val$upToAnchor = z;
        }

        @Override
        protected void onNoDoubleClick(View view) {
            SpannableConfirmExtraTextClickable spannableConfirmExtraTextClickable = new SpannableConfirmExtraTextClickable();
            String string = this.val$context.getString(R.string.assets_hide_scam_token_tips);
            SpannableString spannableString = new SpannableString(string);
            int indexOf = string.indexOf(this.val$context.getString(R.string.assets_hide_scam_token_tips_op));
            if (indexOf > 0) {
                spannableString.setSpan(new ForegroundColorSpan(this.val$context.getResources().getColor(R.color.blue_3c)), indexOf, spannableString.length(), 33);
            }
            spannableConfirmExtraTextClickable.setRight(spannableString);
            final Context context = this.val$context;
            spannableConfirmExtraTextClickable.setOnItemClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view2) {
                    UIUtils.toRatingRoles(context);
                }
            });
            spannableConfirmExtraTextClickable.setTextGravity(3);
            new MultiLineTextPopupView.Builder().setAnchor(view).setPreferredShowUp(this.val$upToAnchor).setRequiredWidth(UIUtils.dip2px(288.0f)).addItem(spannableConfirmExtraTextClickable).build(this.val$context).show();
        }
    }

    public static void initScamToDetailView(final Context context, ItemWarningTagView itemWarningTagView, final TokenBean tokenBean) {
        if (itemWarningTagView != null && OfficialType.isScamOrUnSafeToken(tokenBean)) {
            itemWarningTagView.setVisibility(View.VISIBLE);
            String string = context.getString(R.string.asset_evaluate_type_scam);
            if (OfficialType.isAtRiskToken(tokenBean)) {
                string = context.getString(R.string.asset_evaluate_type_scam);
            } else if (OfficialType.isUnSafeToken(tokenBean)) {
                string = context.getString(R.string.asset_evaluate_type_risk);
            }
            String str = "  " + String.format(context.getString(R.string.asset_scam_tip), string);
            SpannableString spannableString = new SpannableString(str);
            int indexOf = str.indexOf(context.getString(R.string.asset_display));
            spannableString.setSpan(new CenterSpaceImageSpan(context, R.mipmap.ic_asset_scam, 2, UIUtils.dip2px(4.0f), UIUtils.dip2px(4.0f)), 0, 2, 33);
            if (indexOf > 0) {
                spannableString.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.blue_3c)), indexOf, spannableString.length(), 33);
                spannableString.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.red_EC)), 0, indexOf, 33);
            } else {
                spannableString.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.red_EC)), 2, spannableString.length(), 33);
            }
            spannableString.setSpan(new ClickableSpanNoUnderLine() {
                {
                    super();
                }

                @Override
                public void onClick(View view) {
                    TokenBean tokenBean2 = TokenBean.this;
                    if (tokenBean2 == null) {
                        return;
                    }
                    if (tokenBean2.type == 1) {
                        Context context2 = context;
                        UIUtils.toTrc10TokenProtocol(context2, TokenBean.this.getId() + "");
                        return;
                    }
                    TokenBean tokenBean3 = TokenBean.this;
                    if (tokenBean3 != null && tokenBean3.type == 2) {
                        UIUtils.toTrc20TokenDetail(context, TokenBean.this.getContractAddress());
                    } else {
                        UIUtils.toTrc20TokenDetail(context, TokenBean.this.getContractAddress());
                    }
                }
            }, indexOf, spannableString.length(), 33);
            itemWarningTagView.setInfo(spannableString, context.getResources().getColor(R.color.white_90), null, context.getResources().getDrawable(R.color.red_fdeaed_50), false);
            return;
        }
        itemWarningTagView.setVisibility(View.GONE);
    }

    public static void initDefiTypView(final Context context, ImageView imageView, TokenBean tokenBean, final boolean z) {
        final String string;
        if (tokenBean == null) {
            return;
        }
        if (tokenBean.getDefiType() == 1 || tokenBean.getDefiType() == 2) {
            TouchDelegateUtils.expandViewTouchDelegate(imageView, 6, 8, 20, 8);
            imageView.setVisibility(View.VISIBLE);
            if (tokenBean.getDefiType() == 1) {
                String shortName = tokenBean.getShortName();
                string = context.getString(R.string.jtoken_help_tip, shortName, (StringTronUtil.isEmpty(shortName) || !shortName.startsWith("j")) ? shortName : shortName.substring(1), shortName);
            } else {
                string = context.getString(R.string.lptoken_help_tip);
            }
            imageView.setOnClickListener(new NoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    new MultiLineTextPopupView.Builder().setPreferredShowUp(z).setAnchor(view).setRequiredWidth(UIUtils.getScreenWidth(context) - (UIUtils.dip2px(35.0f) * 2)).addKeyValue(string, "").build(context).show();
                }
            });
            return;
        }
        imageView.setVisibility(View.GONE);
        imageView.setOnClickListener(null);
    }

    public static void initDefiTypView(Context context, ImageView imageView, TokenBean tokenBean) {
        initDefiTypView(context, imageView, tokenBean, false);
    }

    class fun7 extends NoDoubleClickListener {
        final Context val$context;
        final boolean val$upToAnchor;

        fun7(Context context, boolean z) {
            this.val$context = context;
            this.val$upToAnchor = z;
        }

        @Override
        protected void onNoDoubleClick(View view) {
            SpannableConfirmExtraTextClickable spannableConfirmExtraTextClickable = new SpannableConfirmExtraTextClickable();
            String string = this.val$context.getString(R.string.asset_tronscan_token_type);
            SpannableString spannableString = new SpannableString(string);
            int indexOf = string.indexOf(this.val$context.getString(R.string.asset_scam_sort_op));
            if (indexOf >= 0) {
                spannableString.setSpan(new ForegroundColorSpan(this.val$context.getResources().getColor(R.color.blue_3c)), indexOf, spannableString.length(), 33);
            }
            spannableConfirmExtraTextClickable.setRight(spannableString);
            final Context context = this.val$context;
            spannableConfirmExtraTextClickable.setOnItemClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view2) {
                    UIUtils.toRatingRoles(context);
                }
            });
            spannableConfirmExtraTextClickable.setTextGravity(3);
            new MultiLineTextPopupView.Builder().setAnchor(view).setPreferredShowUp(this.val$upToAnchor).setShowArrow(true).setRequiredWidth(UIUtils.dip2px(288.0f)).addItem(spannableConfirmExtraTextClickable).build(this.val$context).show();
        }
    }

    public static void initAssetsCreditRadingView(Context context, View view, TokenBean tokenBean, boolean z) {
        view.setVisibility(View.VISIBLE);
        view.setOnClickListener(new fun7(context, z));
    }

    private static abstract class ClickableSpanNoUnderLine extends ClickableSpan {
        private ClickableSpanNoUnderLine() {
        }

        @Override
        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            textPaint.setColor(AppContextUtil.getContext().getResources().getColor(R.color.blue_3c));
            textPaint.setUnderlineText(false);
        }
    }
}
