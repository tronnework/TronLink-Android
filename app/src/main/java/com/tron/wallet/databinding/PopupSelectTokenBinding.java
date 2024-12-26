package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.ErrorView;
import com.tron.wallet.common.components.NoNetView;
import com.tronlinkpro.wallet.R;
public final class PopupSelectTokenBinding implements ViewBinding {
    public final RadioButton button10;
    public final RadioButton button20;
    public final RadioButton button721;
    public final RadioButton buttonAll;
    public final EditText etSearch;
    public final ImageView ivClear;
    public final ImageView ivCommonRight;
    public final ImageView ivSearch;
    public final NoNetView noDataView;
    public final NoNetView noNetView;
    public final ImageView placeHolder;
    public final RadioGroup radioGroupId;
    public final RelativeLayout rlAssetsResultMenu;
    public final RelativeLayout rlRoot;
    public final RelativeLayout rlSearch;
    public final RelativeLayout rlTitle;
    private final RelativeLayout rootView;
    public final RecyclerView rvTokenList;
    public final ErrorView tvMultiSignTip;
    public final TextView tvTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopupSelectTokenBinding(RelativeLayout relativeLayout, RadioButton radioButton, RadioButton radioButton2, RadioButton radioButton3, RadioButton radioButton4, EditText editText, ImageView imageView, ImageView imageView2, ImageView imageView3, NoNetView noNetView, NoNetView noNetView2, ImageView imageView4, RadioGroup radioGroup, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, RecyclerView recyclerView, ErrorView errorView, TextView textView) {
        this.rootView = relativeLayout;
        this.button10 = radioButton;
        this.button20 = radioButton2;
        this.button721 = radioButton3;
        this.buttonAll = radioButton4;
        this.etSearch = editText;
        this.ivClear = imageView;
        this.ivCommonRight = imageView2;
        this.ivSearch = imageView3;
        this.noDataView = noNetView;
        this.noNetView = noNetView2;
        this.placeHolder = imageView4;
        this.radioGroupId = radioGroup;
        this.rlAssetsResultMenu = relativeLayout2;
        this.rlRoot = relativeLayout3;
        this.rlSearch = relativeLayout4;
        this.rlTitle = relativeLayout5;
        this.rvTokenList = recyclerView;
        this.tvMultiSignTip = errorView;
        this.tvTitle = textView;
    }

    public static PopupSelectTokenBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopupSelectTokenBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.popup_select_token, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopupSelectTokenBinding bind(View view) {
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
                        i = R.id.et_search;
                        EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_search);
                        if (editText != null) {
                            i = R.id.iv_clear;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_clear);
                            if (imageView != null) {
                                i = R.id.iv_common_right;
                                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_common_right);
                                if (imageView2 != null) {
                                    i = R.id.iv_search;
                                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_search);
                                    if (imageView3 != null) {
                                        i = R.id.no_data_view;
                                        NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_data_view);
                                        if (noNetView != null) {
                                            i = R.id.no_net_view;
                                            NoNetView noNetView2 = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_net_view);
                                            if (noNetView2 != null) {
                                                i = R.id.place_holder;
                                                ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.place_holder);
                                                if (imageView4 != null) {
                                                    i = R.id.radioGroupId;
                                                    RadioGroup radioGroup = (RadioGroup) ViewBindings.findChildViewById(view, R.id.radioGroupId);
                                                    if (radioGroup != null) {
                                                        i = R.id.rl_assets_result_menu;
                                                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_assets_result_menu);
                                                        if (relativeLayout != null) {
                                                            RelativeLayout relativeLayout2 = (RelativeLayout) view;
                                                            i = R.id.rl_search;
                                                            RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_search);
                                                            if (relativeLayout3 != null) {
                                                                i = R.id.rl_title;
                                                                RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_title);
                                                                if (relativeLayout4 != null) {
                                                                    i = R.id.rv_token_list;
                                                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_token_list);
                                                                    if (recyclerView != null) {
                                                                        i = R.id.tv_multi_sign_tip;
                                                                        ErrorView errorView = (ErrorView) ViewBindings.findChildViewById(view, R.id.tv_multi_sign_tip);
                                                                        if (errorView != null) {
                                                                            i = R.id.tv_title;
                                                                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                                                            if (textView != null) {
                                                                                return new PopupSelectTokenBinding(relativeLayout2, radioButton, radioButton2, radioButton3, radioButton4, editText, imageView, imageView2, imageView3, noNetView, noNetView2, imageView4, radioGroup, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, recyclerView, errorView, textView);
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
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
