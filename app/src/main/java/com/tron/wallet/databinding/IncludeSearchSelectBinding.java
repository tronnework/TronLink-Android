package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class IncludeSearchSelectBinding implements ViewBinding {
    public final EditText etSearch;
    public final ImageView ivSearch;
    public final LinearLayout llVoteSelect;
    public final RelativeLayout rlSearch;
    public final RelativeLayout rlSearchContent;
    private final RelativeLayout rootView;
    public final TextView tvVoteRole;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private IncludeSearchSelectBinding(RelativeLayout relativeLayout, EditText editText, ImageView imageView, LinearLayout linearLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, TextView textView) {
        this.rootView = relativeLayout;
        this.etSearch = editText;
        this.ivSearch = imageView;
        this.llVoteSelect = linearLayout;
        this.rlSearch = relativeLayout2;
        this.rlSearchContent = relativeLayout3;
        this.tvVoteRole = textView;
    }

    public static IncludeSearchSelectBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static IncludeSearchSelectBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.include_search_select, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static IncludeSearchSelectBinding bind(View view) {
        int i = R.id.et_search;
        EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_search);
        if (editText != null) {
            i = R.id.iv_search;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_search);
            if (imageView != null) {
                i = R.id.ll_vote_select;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_vote_select);
                if (linearLayout != null) {
                    i = R.id.rl_search;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_search);
                    if (relativeLayout != null) {
                        i = R.id.rl_search_content;
                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_search_content);
                        if (relativeLayout2 != null) {
                            i = R.id.tv_vote_role;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_vote_role);
                            if (textView != null) {
                                return new IncludeSearchSelectBinding((RelativeLayout) view, editText, imageView, linearLayout, relativeLayout, relativeLayout2, textView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
