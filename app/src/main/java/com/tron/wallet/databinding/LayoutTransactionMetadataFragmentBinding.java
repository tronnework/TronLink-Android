package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class LayoutTransactionMetadataFragmentBinding implements ViewBinding {
    public final TextView hexadecimalDataContent;
    public final TextView hexadecimalDataTitle;
    public final View line;
    public final RelativeLayout rlFunction;
    public final RelativeLayout rlFunctionParam;
    public final RecyclerView rlParams;
    private final RelativeLayout rootView;
    public final TextView tvCopyHex;
    public final TextView tvFunctionContractName;
    public final TextView tvFunctionParam;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private LayoutTransactionMetadataFragmentBinding(RelativeLayout relativeLayout, TextView textView, TextView textView2, View view, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RecyclerView recyclerView, TextView textView3, TextView textView4, TextView textView5) {
        this.rootView = relativeLayout;
        this.hexadecimalDataContent = textView;
        this.hexadecimalDataTitle = textView2;
        this.line = view;
        this.rlFunction = relativeLayout2;
        this.rlFunctionParam = relativeLayout3;
        this.rlParams = recyclerView;
        this.tvCopyHex = textView3;
        this.tvFunctionContractName = textView4;
        this.tvFunctionParam = textView5;
    }

    public static LayoutTransactionMetadataFragmentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutTransactionMetadataFragmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_transaction_metadata_fragment, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutTransactionMetadataFragmentBinding bind(View view) {
        int i = R.id.hexadecimal_data_content;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.hexadecimal_data_content);
        if (textView != null) {
            i = R.id.hexadecimal_data_title;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.hexadecimal_data_title);
            if (textView2 != null) {
                i = R.id.line;
                View findChildViewById = ViewBindings.findChildViewById(view, R.id.line);
                if (findChildViewById != null) {
                    i = R.id.rl_function;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_function);
                    if (relativeLayout != null) {
                        i = R.id.rl_function_param;
                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_function_param);
                        if (relativeLayout2 != null) {
                            i = R.id.rl_params;
                            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rl_params);
                            if (recyclerView != null) {
                                i = R.id.tv_copy_hex;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_copy_hex);
                                if (textView3 != null) {
                                    i = R.id.tv_function_contract_name;
                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_function_contract_name);
                                    if (textView4 != null) {
                                        i = R.id.tv_function_param;
                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_function_param);
                                        if (textView5 != null) {
                                            return new LayoutTransactionMetadataFragmentBinding((RelativeLayout) view, textView, textView2, findChildViewById, relativeLayout, relativeLayout2, recyclerView, textView3, textView4, textView5);
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
