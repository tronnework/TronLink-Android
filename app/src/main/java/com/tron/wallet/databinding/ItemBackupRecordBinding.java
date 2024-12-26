package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemBackupRecordBinding implements ViewBinding {
    public final RelativeLayout rlMain;
    public final RelativeLayout rlTag;
    private final RelativeLayout rootView;
    public final TextView tvAddress;
    public final TextView tvAddressTitle;
    public final TextView tvName;
    public final TextView tvNameTitle;
    public final TextView tvNameType;
    public final TextView tvStamp;
    public final TextView tvStampTitle;
    public final TextView tvState;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemBackupRecordBinding(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8) {
        this.rootView = relativeLayout;
        this.rlMain = relativeLayout2;
        this.rlTag = relativeLayout3;
        this.tvAddress = textView;
        this.tvAddressTitle = textView2;
        this.tvName = textView3;
        this.tvNameTitle = textView4;
        this.tvNameType = textView5;
        this.tvStamp = textView6;
        this.tvStampTitle = textView7;
        this.tvState = textView8;
    }

    public static ItemBackupRecordBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemBackupRecordBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_backup_record, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemBackupRecordBinding bind(View view) {
        RelativeLayout relativeLayout = (RelativeLayout) view;
        int i = R.id.rl_tag;
        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_tag);
        if (relativeLayout2 != null) {
            i = R.id.tv_address;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address);
            if (textView != null) {
                i = R.id.tv_address_title;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address_title);
                if (textView2 != null) {
                    i = R.id.tv_name;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name);
                    if (textView3 != null) {
                        i = R.id.tv_name_title;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name_title);
                        if (textView4 != null) {
                            i = R.id.tv_name_type;
                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name_type);
                            if (textView5 != null) {
                                i = R.id.tv_stamp;
                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_stamp);
                                if (textView6 != null) {
                                    i = R.id.tv_stamp_title;
                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_stamp_title);
                                    if (textView7 != null) {
                                        i = R.id.tv_state;
                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_state);
                                        if (textView8 != null) {
                                            return new ItemBackupRecordBinding(relativeLayout, relativeLayout, relativeLayout2, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8);
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
