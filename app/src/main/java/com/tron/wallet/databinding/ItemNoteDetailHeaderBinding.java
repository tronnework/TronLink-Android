package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemNoteDetailHeaderBinding implements ViewBinding {
    private final RelativeLayout rootView;
    public final TextView tvContent;
    public final TextView tvNode;
    public final TextView tvNum;
    public final TextView tvTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemNoteDetailHeaderBinding(RelativeLayout relativeLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = relativeLayout;
        this.tvContent = textView;
        this.tvNode = textView2;
        this.tvNum = textView3;
        this.tvTitle = textView4;
    }

    public static ItemNoteDetailHeaderBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemNoteDetailHeaderBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_note_detail_header, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemNoteDetailHeaderBinding bind(View view) {
        int i = R.id.tv_content;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_content);
        if (textView != null) {
            i = R.id.tv_node;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_node);
            if (textView2 != null) {
                i = R.id.tv_num;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_num);
                if (textView3 != null) {
                    i = R.id.tv_title;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                    if (textView4 != null) {
                        return new ItemNoteDetailHeaderBinding((RelativeLayout) view, textView, textView2, textView3, textView4);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
