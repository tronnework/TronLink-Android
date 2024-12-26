package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class PullItemTriggerSmartContractBinding implements ViewBinding {
    public final LinearLayout hashLayout;
    public final TextView hexadecimalDataContent;
    public final LinearLayout noteLayout;
    private final LinearLayout rootView;
    public final TextView tvContractAddress;
    public final TextView tvContractAddressTitle;
    public final TextView tvHash;
    public final TextView tvNote;
    public final TextView tvPullAddress;
    public final TextView tvPullAddressName;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private PullItemTriggerSmartContractBinding(LinearLayout linearLayout, LinearLayout linearLayout2, TextView textView, LinearLayout linearLayout3, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7) {
        this.rootView = linearLayout;
        this.hashLayout = linearLayout2;
        this.hexadecimalDataContent = textView;
        this.noteLayout = linearLayout3;
        this.tvContractAddress = textView2;
        this.tvContractAddressTitle = textView3;
        this.tvHash = textView4;
        this.tvNote = textView5;
        this.tvPullAddress = textView6;
        this.tvPullAddressName = textView7;
    }

    public static PullItemTriggerSmartContractBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PullItemTriggerSmartContractBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pull_item_trigger_smart_contract, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PullItemTriggerSmartContractBinding bind(View view) {
        int i = R.id.hash_layout;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.hash_layout);
        if (linearLayout != null) {
            i = R.id.hexadecimal_data_content;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.hexadecimal_data_content);
            if (textView != null) {
                i = R.id.note_layout;
                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.note_layout);
                if (linearLayout2 != null) {
                    i = R.id.tv_contract_address;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_contract_address);
                    if (textView2 != null) {
                        i = R.id.tv_contract_address_title;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_contract_address_title);
                        if (textView3 != null) {
                            i = R.id.tv_hash;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_hash);
                            if (textView4 != null) {
                                i = R.id.tv_note;
                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_note);
                                if (textView5 != null) {
                                    i = R.id.tv_pull_address;
                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_pull_address);
                                    if (textView6 != null) {
                                        i = R.id.tv_pull_address_name;
                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_pull_address_name);
                                        if (textView7 != null) {
                                            return new PullItemTriggerSmartContractBinding((LinearLayout) view, linearLayout, textView, linearLayout2, textView2, textView3, textView4, textView5, textView6, textView7);
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
