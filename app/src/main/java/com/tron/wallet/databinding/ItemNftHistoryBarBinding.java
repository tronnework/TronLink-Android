package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.tron.wallet.common.components.CommonTabLayout;
import com.tronlinkpro.wallet.R;
public final class ItemNftHistoryBarBinding implements ViewBinding {
    public final RelativeLayout contentRoot;
    public final RelativeLayout liTablayout;
    public final LinearLayout llShasta;
    private final RelativeLayout rootView;
    public final ViewPager2 vpContent;
    public final CommonTabLayout xTablayout;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemNftHistoryBarBinding(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, LinearLayout linearLayout, ViewPager2 viewPager2, CommonTabLayout commonTabLayout) {
        this.rootView = relativeLayout;
        this.contentRoot = relativeLayout2;
        this.liTablayout = relativeLayout3;
        this.llShasta = linearLayout;
        this.vpContent = viewPager2;
        this.xTablayout = commonTabLayout;
    }

    public static ItemNftHistoryBarBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemNftHistoryBarBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_nft_history_bar, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemNftHistoryBarBinding bind(View view) {
        RelativeLayout relativeLayout = (RelativeLayout) view;
        int i = R.id.li_tablayout;
        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.li_tablayout);
        if (relativeLayout2 != null) {
            i = R.id.ll_shasta;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_shasta);
            if (linearLayout != null) {
                i = R.id.vp_content;
                ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, R.id.vp_content);
                if (viewPager2 != null) {
                    i = R.id.xTablayout;
                    CommonTabLayout commonTabLayout = (CommonTabLayout) ViewBindings.findChildViewById(view, R.id.xTablayout);
                    if (commonTabLayout != null) {
                        return new ItemNftHistoryBarBinding(relativeLayout, relativeLayout, relativeLayout2, linearLayout, viewPager2, commonTabLayout);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
