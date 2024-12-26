package com.tron.wallet.common.components;

import android.content.Context;
import android.util.AttributeSet;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.tron.wallet.common.bean.token.TokenBean;
public class TokenLogoDraweeView extends CircularImageDraweeView {
    public TokenLogoDraweeView(Context context, GenericDraweeHierarchy genericDraweeHierarchy) {
        super(context, genericDraweeHierarchy);
    }

    public TokenLogoDraweeView(Context context) {
        super(context);
    }

    public TokenLogoDraweeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TokenLogoDraweeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setTokenImage(TokenBean tokenBean) {
        setCircularImage(tokenBean.getLogoUrl());
    }
}
