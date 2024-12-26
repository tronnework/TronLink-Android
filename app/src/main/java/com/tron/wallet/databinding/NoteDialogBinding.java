package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class NoteDialogBinding implements ViewBinding {
    private final RelativeLayout rootView;
    public final TextView tvCancle;
    public final TextView tvNote;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private NoteDialogBinding(RelativeLayout relativeLayout, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.tvCancle = textView;
        this.tvNote = textView2;
    }

    public static NoteDialogBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static NoteDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.note_dialog, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static NoteDialogBinding bind(View view) {
        int i = R.id.tv_cancle;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cancle);
        if (textView != null) {
            i = R.id.tv_note;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_note);
            if (textView2 != null) {
                return new NoteDialogBinding((RelativeLayout) view, textView, textView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
