package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemEquipmentBinding implements ViewBinding {
    public final ImageView editIcon;
    public final RelativeLayout equipmentEdit;
    public final ImageView equipmentIcon;
    public final TextView equipmentName;
    public final RelativeLayout rlContent;
    public final RelativeLayout root;
    private final RelativeLayout rootView;
    public final LinearLayout setting;
    public final TextView statusTag;
    public final TextView tvDisconnect;
    public final TextView tvImportAddress;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemEquipmentBinding(RelativeLayout relativeLayout, ImageView imageView, RelativeLayout relativeLayout2, ImageView imageView2, TextView textView, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, LinearLayout linearLayout, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = relativeLayout;
        this.editIcon = imageView;
        this.equipmentEdit = relativeLayout2;
        this.equipmentIcon = imageView2;
        this.equipmentName = textView;
        this.rlContent = relativeLayout3;
        this.root = relativeLayout4;
        this.setting = linearLayout;
        this.statusTag = textView2;
        this.tvDisconnect = textView3;
        this.tvImportAddress = textView4;
    }

    public static ItemEquipmentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemEquipmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_equipment, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemEquipmentBinding bind(View view) {
        int i = R.id.edit_icon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.edit_icon);
        if (imageView != null) {
            i = R.id.equipment_edit;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.equipment_edit);
            if (relativeLayout != null) {
                i = R.id.equipment_icon;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.equipment_icon);
                if (imageView2 != null) {
                    i = R.id.equipment_name;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.equipment_name);
                    if (textView != null) {
                        i = R.id.rl_content;
                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_content);
                        if (relativeLayout2 != null) {
                            RelativeLayout relativeLayout3 = (RelativeLayout) view;
                            i = R.id.setting;
                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.setting);
                            if (linearLayout != null) {
                                i = R.id.status_tag;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.status_tag);
                                if (textView2 != null) {
                                    i = R.id.tv_disconnect;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_disconnect);
                                    if (textView3 != null) {
                                        i = R.id.tv_import_address;
                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_import_address);
                                        if (textView4 != null) {
                                            return new ItemEquipmentBinding(relativeLayout3, imageView, relativeLayout, imageView2, textView, relativeLayout2, relativeLayout3, linearLayout, textView2, textView3, textView4);
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
