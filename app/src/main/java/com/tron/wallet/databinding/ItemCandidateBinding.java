package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemCandidateBinding implements ViewBinding {
    public final ImageView divider;
    public final EditText etInput;
    public final TextView id;
    public final RelativeLayout middle;
    public final TextView rewardRatio;
    public final RelativeLayout rlInput;
    public final RelativeLayout root;
    private final RelativeLayout rootView;
    public final RelativeLayout top;
    public final TextView tvPersonName;
    public final TextView voteCount;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemCandidateBinding(RelativeLayout relativeLayout, ImageView imageView, EditText editText, TextView textView, RelativeLayout relativeLayout2, TextView textView2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, TextView textView3, TextView textView4) {
        this.rootView = relativeLayout;
        this.divider = imageView;
        this.etInput = editText;
        this.id = textView;
        this.middle = relativeLayout2;
        this.rewardRatio = textView2;
        this.rlInput = relativeLayout3;
        this.root = relativeLayout4;
        this.top = relativeLayout5;
        this.tvPersonName = textView3;
        this.voteCount = textView4;
    }

    public static ItemCandidateBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemCandidateBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_candidate, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemCandidateBinding bind(View view) {
        int i = R.id.divider;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.divider);
        if (imageView != null) {
            i = R.id.et_input;
            EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_input);
            if (editText != null) {
                i = R.id.id;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.id);
                if (textView != null) {
                    i = R.id.middle;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.middle);
                    if (relativeLayout != null) {
                        i = R.id.reward_ratio;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.reward_ratio);
                        if (textView2 != null) {
                            i = R.id.rl_input;
                            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_input);
                            if (relativeLayout2 != null) {
                                RelativeLayout relativeLayout3 = (RelativeLayout) view;
                                i = R.id.top;
                                RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.top);
                                if (relativeLayout4 != null) {
                                    i = R.id.tv_person_name;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_person_name);
                                    if (textView3 != null) {
                                        i = R.id.vote_count;
                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.vote_count);
                                        if (textView4 != null) {
                                            return new ItemCandidateBinding(relativeLayout3, imageView, editText, textView, relativeLayout, textView2, relativeLayout2, relativeLayout3, relativeLayout4, textView3, textView4);
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
