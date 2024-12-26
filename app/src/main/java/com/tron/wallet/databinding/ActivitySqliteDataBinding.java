package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ActivitySqliteDataBinding implements ViewBinding {
    public final TextView changeData;
    public final TextView insertData;
    public final TextView readData;
    private final LinearLayout rootView;
    public final TextView socketTest;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ActivitySqliteDataBinding(LinearLayout linearLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = linearLayout;
        this.changeData = textView;
        this.insertData = textView2;
        this.readData = textView3;
        this.socketTest = textView4;
    }

    public static ActivitySqliteDataBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivitySqliteDataBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_sqlite_data, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivitySqliteDataBinding bind(View view) {
        int i = R.id.change_data;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.change_data);
        if (textView != null) {
            i = R.id.insert_data;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.insert_data);
            if (textView2 != null) {
                i = R.id.read_data;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.read_data);
                if (textView3 != null) {
                    i = R.id.socket_test;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.socket_test);
                    if (textView4 != null) {
                        return new ActivitySqliteDataBinding((LinearLayout) view, textView, textView2, textView3, textView4);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
