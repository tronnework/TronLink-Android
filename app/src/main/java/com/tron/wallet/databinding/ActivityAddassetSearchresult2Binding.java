package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.NoNetView;
import com.tronlinkpro.wallet.R;
public final class ActivityAddassetSearchresult2Binding implements ViewBinding {
    public final RadioButton button10;
    public final RadioButton button20;
    public final RadioButton button721;
    public final RadioButton buttonAll;
    public final LinearLayout llAssetResult;
    public final LinearLayout llHotToken;
    public final LinearLayout llNoresult;
    public final LinearLayout llTopResult;
    public final NoNetView netErrorView;
    public final NoNetView noDataView;
    public final ImageView placeHolder;
    public final RadioGroup radioGroupId;
    public final RelativeLayout rlAssetsResultMenu;
    private final RelativeLayout rootView;
    public final RecyclerView rvAssetsResult;
    public final RecyclerView rvTopTokens;
    public final SearchAssetHeaderNewBinding searchHeader;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ActivityAddassetSearchresult2Binding(RelativeLayout relativeLayout, RadioButton radioButton, RadioButton radioButton2, RadioButton radioButton3, RadioButton radioButton4, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, NoNetView noNetView, NoNetView noNetView2, ImageView imageView, RadioGroup radioGroup, RelativeLayout relativeLayout2, RecyclerView recyclerView, RecyclerView recyclerView2, SearchAssetHeaderNewBinding searchAssetHeaderNewBinding) {
        this.rootView = relativeLayout;
        this.button10 = radioButton;
        this.button20 = radioButton2;
        this.button721 = radioButton3;
        this.buttonAll = radioButton4;
        this.llAssetResult = linearLayout;
        this.llHotToken = linearLayout2;
        this.llNoresult = linearLayout3;
        this.llTopResult = linearLayout4;
        this.netErrorView = noNetView;
        this.noDataView = noNetView2;
        this.placeHolder = imageView;
        this.radioGroupId = radioGroup;
        this.rlAssetsResultMenu = relativeLayout2;
        this.rvAssetsResult = recyclerView;
        this.rvTopTokens = recyclerView2;
        this.searchHeader = searchAssetHeaderNewBinding;
    }

    public static ActivityAddassetSearchresult2Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityAddassetSearchresult2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_addasset_searchresult2, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityAddassetSearchresult2Binding bind(View view) {
        int i = R.id.button_10;
        RadioButton radioButton = (RadioButton) ViewBindings.findChildViewById(view, R.id.button_10);
        if (radioButton != null) {
            i = R.id.button_20;
            RadioButton radioButton2 = (RadioButton) ViewBindings.findChildViewById(view, R.id.button_20);
            if (radioButton2 != null) {
                i = R.id.button_721;
                RadioButton radioButton3 = (RadioButton) ViewBindings.findChildViewById(view, R.id.button_721);
                if (radioButton3 != null) {
                    i = R.id.button_all;
                    RadioButton radioButton4 = (RadioButton) ViewBindings.findChildViewById(view, R.id.button_all);
                    if (radioButton4 != null) {
                        i = R.id.ll_asset_result;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_asset_result);
                        if (linearLayout != null) {
                            i = R.id.ll_hot_token;
                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_hot_token);
                            if (linearLayout2 != null) {
                                i = R.id.ll_noresult;
                                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_noresult);
                                if (linearLayout3 != null) {
                                    i = R.id.ll_top_result;
                                    LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_top_result);
                                    if (linearLayout4 != null) {
                                        i = R.id.net_error_view;
                                        NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.net_error_view);
                                        if (noNetView != null) {
                                            i = R.id.no_data_view;
                                            NoNetView noNetView2 = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_data_view);
                                            if (noNetView2 != null) {
                                                i = R.id.place_holder;
                                                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.place_holder);
                                                if (imageView != null) {
                                                    i = R.id.radioGroupId;
                                                    RadioGroup radioGroup = (RadioGroup) ViewBindings.findChildViewById(view, R.id.radioGroupId);
                                                    if (radioGroup != null) {
                                                        i = R.id.rl_assets_result_menu;
                                                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_assets_result_menu);
                                                        if (relativeLayout != null) {
                                                            i = R.id.rv_assets_result;
                                                            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_assets_result);
                                                            if (recyclerView != null) {
                                                                i = R.id.rv_top_tokens;
                                                                RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_top_tokens);
                                                                if (recyclerView2 != null) {
                                                                    i = R.id.search_header;
                                                                    View findChildViewById = ViewBindings.findChildViewById(view, R.id.search_header);
                                                                    if (findChildViewById != null) {
                                                                        return new ActivityAddassetSearchresult2Binding((RelativeLayout) view, radioButton, radioButton2, radioButton3, radioButton4, linearLayout, linearLayout2, linearLayout3, linearLayout4, noNetView, noNetView2, imageView, radioGroup, relativeLayout, recyclerView, recyclerView2, SearchAssetHeaderNewBinding.bind(findChildViewById));
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
