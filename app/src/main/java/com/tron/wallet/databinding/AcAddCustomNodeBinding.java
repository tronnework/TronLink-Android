package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.ErrorBottomLayout;
import com.tronlinkpro.wallet.R;
public final class AcAddCustomNodeBinding implements ViewBinding {
    public final Button btDelete;
    public final Button btNext;
    public final ErrorBottomLayout eetNodeIp;
    public final ErrorBottomLayout eetNodePort;
    public final EditText etNodeIp;
    public final EditText etNodePort;
    public final ImageView ivArrow;
    public final RecyclerView nodeTypeList;
    public final RelativeLayout rlNodeTypeSelect;
    private final RelativeLayout rootView;
    public final TextView tvNodePortDesc;
    public final TextView tvSelectedName;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcAddCustomNodeBinding(RelativeLayout relativeLayout, Button button, Button button2, ErrorBottomLayout errorBottomLayout, ErrorBottomLayout errorBottomLayout2, EditText editText, EditText editText2, ImageView imageView, RecyclerView recyclerView, RelativeLayout relativeLayout2, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.btDelete = button;
        this.btNext = button2;
        this.eetNodeIp = errorBottomLayout;
        this.eetNodePort = errorBottomLayout2;
        this.etNodeIp = editText;
        this.etNodePort = editText2;
        this.ivArrow = imageView;
        this.nodeTypeList = recyclerView;
        this.rlNodeTypeSelect = relativeLayout2;
        this.tvNodePortDesc = textView;
        this.tvSelectedName = textView2;
    }

    public static AcAddCustomNodeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcAddCustomNodeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_add_custom_node, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcAddCustomNodeBinding bind(View view) {
        int i = R.id.bt_delete;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_delete);
        if (button != null) {
            i = R.id.bt_next;
            Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.bt_next);
            if (button2 != null) {
                i = R.id.eet_node_ip;
                ErrorBottomLayout errorBottomLayout = (ErrorBottomLayout) ViewBindings.findChildViewById(view, R.id.eet_node_ip);
                if (errorBottomLayout != null) {
                    i = R.id.eet_node_port;
                    ErrorBottomLayout errorBottomLayout2 = (ErrorBottomLayout) ViewBindings.findChildViewById(view, R.id.eet_node_port);
                    if (errorBottomLayout2 != null) {
                        i = R.id.et_node_ip;
                        EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_node_ip);
                        if (editText != null) {
                            i = R.id.et_node_port;
                            EditText editText2 = (EditText) ViewBindings.findChildViewById(view, R.id.et_node_port);
                            if (editText2 != null) {
                                i = R.id.iv_arrow;
                                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_arrow);
                                if (imageView != null) {
                                    i = R.id.node_type_list;
                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.node_type_list);
                                    if (recyclerView != null) {
                                        i = R.id.rl_node_type_select;
                                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_node_type_select);
                                        if (relativeLayout != null) {
                                            i = R.id.tv_node_port_desc;
                                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_node_port_desc);
                                            if (textView != null) {
                                                i = R.id.tv_selected_name;
                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_selected_name);
                                                if (textView2 != null) {
                                                    return new AcAddCustomNodeBinding((RelativeLayout) view, button, button2, errorBottomLayout, errorBottomLayout2, editText, editText2, imageView, recyclerView, relativeLayout, textView, textView2);
                                                }
                                            }
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
