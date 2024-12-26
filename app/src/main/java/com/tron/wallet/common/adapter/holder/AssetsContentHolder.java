package com.tron.wallet.common.adapter.holder;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.AssetsConfig;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.TokenLogoDraweeView;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.NumUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.ItemAssetsAdapterBinding;
import com.tron.wallet.db.SpAPI;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
public class AssetsContentHolder extends BaseViewHolder {
    private static final String APPROXIMATE = "≈";
    private static final String CNY_STR = "CNY";
    private static final String TRX_STR = "TRX";
    private static final String TRZ_STR = "TRZ";
    private static final String USD_STR = "USD";
    TextView assetsCount;
    TextView assetsCountHiddenView;
    TextView assetsName;
    TextView assetsPrice;
    TextView assetsPriceHiddenView;
    TextView assetsSubName;
    View innerLayout;
    ImageView ivDefiType;
    TokenLogoDraweeView ivLogo;
    ImageView ivNationalImage;
    View ivOfficialImage;
    ImageView ivScam;
    private final Context mContext;
    private boolean mIsHidden;
    private final NumberFormat mNumberFormat;
    View renzhengView;
    RelativeLayout rlStakeAvail;
    View rootView;
    TextView tvAvailableTrx;
    TextView tvPrice;
    TextView tvStakedTrx;

    public AssetsContentHolder(Context context, View view, boolean z) {
        super(view);
        ItemAssetsAdapterBinding bind = ItemAssetsAdapterBinding.bind(view);
        this.mContext = context;
        this.mIsHidden = z;
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.mNumberFormat = numberInstance;
        numberInstance.setMaximumFractionDigits(6);
        this.ivLogo = bind.ivLogo;
        this.assetsName = bind.assetsName;
        this.assetsSubName = bind.assetsSubName;
        this.rlStakeAvail = bind.rlStakeAvailable;
        this.tvPrice = bind.tvPriceAmount;
        this.tvStakedTrx = bind.tvStakedTrx;
        this.tvAvailableTrx = bind.tvAvailableTrx;
        this.assetsCount = bind.assetsCount;
        this.assetsPrice = bind.assetsPrice;
        this.assetsCountHiddenView = bind.assetsCountHiddenView;
        this.assetsPriceHiddenView = bind.assetsPriceHiddenView;
        this.renzhengView = bind.ivRenzheng;
        this.rootView = bind.rlMain;
        this.innerLayout = bind.rlInner;
        this.ivOfficialImage = bind.ivOfficialImage;
        this.ivScam = bind.ivScam;
        this.ivNationalImage = bind.ivNational;
        this.ivDefiType = bind.ivDefitype;
    }

    public AssetsContentHolder(View view) {
        this(view.getContext(), view, false);
    }

    public void bindHolder(android.content.Context r23, java.util.List<com.tron.wallet.common.bean.token.TokenBean> r24, int r25, double r26, boolean r28, org.tron.protos.Protocol.Account r29) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.common.adapter.holder.AssetsContentHolder.bindHolder(android.content.Context, java.util.List, int, double, boolean, org.tron.protos.Protocol$Account):void");
    }

    private void setAssetsPrice(TokenBean tokenBean, double d) {
        String tokenTotalPrice = AssetsConfig.getTokenTotalPrice(tokenBean, d);
        if (isNotEllipsized(this.assetsPrice, tokenTotalPrice)) {
            BigDecimal tokenTotalPriceNumber = AssetsConfig.getTokenTotalPriceNumber(tokenBean, d);
            if (BigDecimalUtils.MoreThan("0.001", tokenTotalPriceNumber) && !BigDecimalUtils.equalsZero((Number) tokenTotalPriceNumber)) {
                this.assetsPrice.setText(StringTronUtil.formatPriceLessThan(Double.valueOf(0.001d)));
                return;
            } else {
                this.assetsPrice.setText(tokenTotalPrice);
                return;
            }
        }
        BigDecimal tokenTotalPriceNumber2 = AssetsConfig.getTokenTotalPriceNumber(tokenBean, d);
        String formatPrice0 = StringTronUtil.formatPrice0(tokenTotalPriceNumber2);
        if (isNotEllipsized(this.assetsPrice, formatPrice0)) {
            this.assetsPrice.setText(formatPrice0);
            return;
        }
        try {
            TextView textView = this.assetsPrice;
            Object[] objArr = new Object[2];
            objArr[0] = SpAPI.THIS.isUsdPrice() ? "$" : Html.fromHtml("&yen;");
            objArr[1] = NumUtils.amountConversion(tokenTotalPriceNumber2.doubleValue());
            textView.setText(String.format("%s%s", objArr));
        } catch (Throwable th) {
            LogUtils.e(th);
            TextView textView2 = this.assetsPrice;
            Object[] objArr2 = new Object[2];
            objArr2[0] = SpAPI.THIS.isUsdPrice() ? "$" : Html.fromHtml("&yen;");
            objArr2[1] = NumUtils.amountConversion(tokenBean.trxCount * d);
            textView2.setText(String.format("%s%s", objArr2));
        }
    }

    private void setAssetsCount(TokenBean tokenBean) {
        if (isNotEllipsized(this.assetsCount, StringTronUtil.formatNumber6Truncate(BigDecimalUtils.toBigDecimal(tokenBean.getType() == 0 ? Double.valueOf(tokenBean.totalBalance) : tokenBean.balanceStr)))) {
            this.assetsCount.setText(StringTronUtil.formatNumber6Truncate(BigDecimalUtils.toBigDecimal(tokenBean.getType() == 0 ? Double.valueOf(tokenBean.totalBalance) : tokenBean.balanceStr)));
            return;
        }
        if (isNotEllipsized(this.assetsCount, StringTronUtil.formatNumber0Truncate(BigDecimalUtils.toBigDecimal(tokenBean.getType() == 0 ? Double.valueOf(tokenBean.totalBalance) : tokenBean.balanceStr)))) {
            this.assetsCount.setText(StringTronUtil.formatNumber0Truncate(BigDecimalUtils.toBigDecimal(tokenBean.getType() == 0 ? Double.valueOf(tokenBean.totalBalance) : tokenBean.balanceStr)));
            return;
        }
        LogUtils.e("amountConversion", "------------------------------");
        LogUtils.e("amountConversion", "" + tokenBean.totalBalance);
        LogUtils.e("amountConversion", "" + tokenBean.balanceStr);
        LogUtils.e("amountConversion", "" + NumUtils.amountConversion(tokenBean.totalBalance, false, tokenBean.balanceStr));
        LogUtils.e("amountConversion", "------------------------------");
        this.assetsCount.setText(NumUtils.amountConversion(tokenBean.totalBalance, false, tokenBean.balanceStr));
    }

    private void setAssetPriceCountVisible(int i) {
        this.assetsCount.setVisibility(i);
        this.assetsPrice.setVisibility(i);
    }

    private String[] toHhmmss(int i) {
        String str;
        String str2;
        String str3;
        String[] strArr = new String[4];
        int i2 = i / 86400;
        int i3 = i % 86400;
        int i4 = i3 / 3600;
        int i5 = i3 % 3600;
        int i6 = i5 / 60;
        int i7 = i5 % 60;
        String str4 = i2 + "";
        if (i4 >= 0 && i4 < 10) {
            str = "0" + i4;
        } else {
            str = i4 + "";
        }
        if (i6 >= 0 && i6 < 10) {
            str2 = "0" + i6;
        } else {
            str2 = "" + i6;
        }
        if (i7 >= 0 && i7 < 10) {
            str3 = "0" + i7;
        } else {
            str3 = "" + i7;
        }
        strArr[0] = str4;
        strArr[1] = str;
        strArr[2] = str2;
        strArr[3] = str3;
        return strArr;
    }

    private boolean isNotEllipsized(TextView textView, String str) {
        return StringTronUtil.equals(str, getEllipsizedText(textView, str));
    }

    private String getEllipsizedText(TextView textView, String str) {
        return TextUtils.ellipsize(str, textView.getPaint(), UIUtils.dip2px(180.0f), textView.getEllipsize()).toString();
    }
}
