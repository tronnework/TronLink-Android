package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class PopupFinanceSortBinding implements ViewBinding {
    public final RadioGroup groupSortItem;
    public final RadioButton rbApr;
    public final RadioButton rbSuggest;
    public final RadioButton rbTvl;
    public final RadioButton rbValue;
    public final RelativeLayout rlContent;
    public final RelativeLayout rlTop;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopupFinanceSortBinding(RelativeLayout relativeLayout, RadioGroup radioGroup, RadioButton radioButton, RadioButton radioButton2, RadioButton radioButton3, RadioButton radioButton4, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3) {
        this.rootView = relativeLayout;
        this.groupSortItem = radioGroup;
        this.rbApr = radioButton;
        this.rbSuggest = radioButton2;
        this.rbTvl = radioButton3;
        this.rbValue = radioButton4;
        this.rlContent = relativeLayout2;
        this.rlTop = relativeLayout3;
    }

    public static PopupFinanceSortBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopupFinanceSortBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.popup_finance_sort, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopupFinanceSortBinding bind(View view) {
        int i = R.id.group_sort_item;
        RadioGroup radioGroup = (RadioGroup) ViewBindings.findChildViewById(view, R.id.group_sort_item);
        if (radioGroup != null) {
            i = R.id.rb_apr;
            RadioButton radioButton = (RadioButton) ViewBindings.findChildViewById(view, R.id.rb_apr);
            if (radioButton != null) {
                i = R.id.rb_suggest;
                RadioButton radioButton2 = (RadioButton) ViewBindings.findChildViewById(view, R.id.rb_suggest);
                if (radioButton2 != null) {
                    i = R.id.rb_tvl;
                    RadioButton radioButton3 = (RadioButton) ViewBindings.findChildViewById(view, R.id.rb_tvl);
                    if (radioButton3 != null) {
                        i = R.id.rb_value;
                        RadioButton radioButton4 = (RadioButton) ViewBindings.findChildViewById(view, R.id.rb_value);
                        if (radioButton4 != null) {
                            RelativeLayout relativeLayout = (RelativeLayout) view;
                            i = R.id.rl_top;
                            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_top);
                            if (relativeLayout2 != null) {
                                return new PopupFinanceSortBinding(relativeLayout, radioGroup, radioButton, radioButton2, radioButton3, radioButton4, relativeLayout, relativeLayout2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
