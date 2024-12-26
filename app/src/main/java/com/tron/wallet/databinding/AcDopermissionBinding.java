package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.flowlayout.TagFlowLayout;
import com.tronlinkpro.wallet.R;
public final class AcDopermissionBinding implements ViewBinding {
    public final TextView etControlAddress;
    public final EditText etPermissionName;
    public final EditText etThreshold;
    public final TextView etThresholdOwner;
    public final TagFlowLayout flOperations;
    public final ImageView ivClear;
    public final ImageView ivModifyOperations;
    public final LinearLayout llAddkeyDesc;
    public final LinearLayout llAddkeysContainer;
    public final LinearLayout llControlAddress;
    public final LinearLayout llControlAddressEt;
    public final LinearLayout llKeys;
    public final LinearLayout llMore;
    public final LinearLayout llOperations;
    public final LinearLayout llOperationsLabels;
    public final LinearLayout llPermissionName;
    public final LinearLayout llPermissionNameEt;
    public final LinearLayout llPermissionType;
    public final LinearLayout llPermissionTypeEt;
    public final LinearLayout llPermissionnameTitle;
    public final LinearLayout llThreshold;
    public final LinearLayout llThresholdTip;
    public final RelativeLayout rlAddKeyButton;
    public final RelativeLayout rlConfirmShadow;
    public final RelativeLayout rlKeys;
    public final RelativeLayout rlMore;
    public final RelativeLayout rlOperations;
    public final RelativeLayout rlThreshold;
    private final ScrollView rootView;
    public final TextView tvAddkeyDesc;
    public final Button tvConfirm;
    public final TextView tvOperationsDesc;
    public final TextView tvOperationsTip;
    public final TextView tvPermissionType;
    public final TextView tvPermissionnameTip;
    public final TextView tvThresholdTip;
    public final TextView tvWeightTip;
    public final ImageView viewKeysLight;
    public final View viewKeysNormal;
    public final ImageView viewOperationsLight;
    public final View viewOperationsNormal;
    public final ImageView viewPermissionnameLight;
    public final View viewPermissionnameNormal;
    public final ImageView viewThresholdLight;
    public final View viewThresholdNormal;

    @Override
    public ScrollView getRoot() {
        return this.rootView;
    }

    private AcDopermissionBinding(ScrollView scrollView, TextView textView, EditText editText, EditText editText2, TextView textView2, TagFlowLayout tagFlowLayout, ImageView imageView, ImageView imageView2, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, LinearLayout linearLayout6, LinearLayout linearLayout7, LinearLayout linearLayout8, LinearLayout linearLayout9, LinearLayout linearLayout10, LinearLayout linearLayout11, LinearLayout linearLayout12, LinearLayout linearLayout13, LinearLayout linearLayout14, LinearLayout linearLayout15, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, RelativeLayout relativeLayout6, TextView textView3, Button button, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, ImageView imageView3, View view, ImageView imageView4, View view2, ImageView imageView5, View view3, ImageView imageView6, View view4) {
        this.rootView = scrollView;
        this.etControlAddress = textView;
        this.etPermissionName = editText;
        this.etThreshold = editText2;
        this.etThresholdOwner = textView2;
        this.flOperations = tagFlowLayout;
        this.ivClear = imageView;
        this.ivModifyOperations = imageView2;
        this.llAddkeyDesc = linearLayout;
        this.llAddkeysContainer = linearLayout2;
        this.llControlAddress = linearLayout3;
        this.llControlAddressEt = linearLayout4;
        this.llKeys = linearLayout5;
        this.llMore = linearLayout6;
        this.llOperations = linearLayout7;
        this.llOperationsLabels = linearLayout8;
        this.llPermissionName = linearLayout9;
        this.llPermissionNameEt = linearLayout10;
        this.llPermissionType = linearLayout11;
        this.llPermissionTypeEt = linearLayout12;
        this.llPermissionnameTitle = linearLayout13;
        this.llThreshold = linearLayout14;
        this.llThresholdTip = linearLayout15;
        this.rlAddKeyButton = relativeLayout;
        this.rlConfirmShadow = relativeLayout2;
        this.rlKeys = relativeLayout3;
        this.rlMore = relativeLayout4;
        this.rlOperations = relativeLayout5;
        this.rlThreshold = relativeLayout6;
        this.tvAddkeyDesc = textView3;
        this.tvConfirm = button;
        this.tvOperationsDesc = textView4;
        this.tvOperationsTip = textView5;
        this.tvPermissionType = textView6;
        this.tvPermissionnameTip = textView7;
        this.tvThresholdTip = textView8;
        this.tvWeightTip = textView9;
        this.viewKeysLight = imageView3;
        this.viewKeysNormal = view;
        this.viewOperationsLight = imageView4;
        this.viewOperationsNormal = view2;
        this.viewPermissionnameLight = imageView5;
        this.viewPermissionnameNormal = view3;
        this.viewThresholdLight = imageView6;
        this.viewThresholdNormal = view4;
    }

    public static AcDopermissionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcDopermissionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_dopermission, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcDopermissionBinding bind(View view) {
        int i = R.id.et_control_address;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.et_control_address);
        if (textView != null) {
            i = R.id.et_permission_name;
            EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_permission_name);
            if (editText != null) {
                i = R.id.et_threshold;
                EditText editText2 = (EditText) ViewBindings.findChildViewById(view, R.id.et_threshold);
                if (editText2 != null) {
                    i = R.id.et_threshold_owner;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.et_threshold_owner);
                    if (textView2 != null) {
                        i = R.id.fl_operations;
                        TagFlowLayout tagFlowLayout = (TagFlowLayout) ViewBindings.findChildViewById(view, R.id.fl_operations);
                        if (tagFlowLayout != null) {
                            i = R.id.iv_clear;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_clear);
                            if (imageView != null) {
                                i = R.id.iv_modify_operations;
                                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_modify_operations);
                                if (imageView2 != null) {
                                    i = R.id.ll_addkey_desc;
                                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_addkey_desc);
                                    if (linearLayout != null) {
                                        i = R.id.ll_addkeys_container;
                                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_addkeys_container);
                                        if (linearLayout2 != null) {
                                            i = R.id.ll_control_address;
                                            LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_control_address);
                                            if (linearLayout3 != null) {
                                                i = R.id.ll_control_address_et;
                                                LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_control_address_et);
                                                if (linearLayout4 != null) {
                                                    i = R.id.ll_keys;
                                                    LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_keys);
                                                    if (linearLayout5 != null) {
                                                        i = R.id.ll_more;
                                                        LinearLayout linearLayout6 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_more);
                                                        if (linearLayout6 != null) {
                                                            i = R.id.ll_operations;
                                                            LinearLayout linearLayout7 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_operations);
                                                            if (linearLayout7 != null) {
                                                                i = R.id.ll_operations_labels;
                                                                LinearLayout linearLayout8 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_operations_labels);
                                                                if (linearLayout8 != null) {
                                                                    i = R.id.ll_permission_name;
                                                                    LinearLayout linearLayout9 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_permission_name);
                                                                    if (linearLayout9 != null) {
                                                                        i = R.id.ll_permission_name_et;
                                                                        LinearLayout linearLayout10 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_permission_name_et);
                                                                        if (linearLayout10 != null) {
                                                                            i = R.id.ll_permissionType;
                                                                            LinearLayout linearLayout11 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_permissionType);
                                                                            if (linearLayout11 != null) {
                                                                                i = R.id.ll_permissionType_et;
                                                                                LinearLayout linearLayout12 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_permissionType_et);
                                                                                if (linearLayout12 != null) {
                                                                                    i = R.id.ll_permissionname_title;
                                                                                    LinearLayout linearLayout13 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_permissionname_title);
                                                                                    if (linearLayout13 != null) {
                                                                                        i = R.id.ll_threshold;
                                                                                        LinearLayout linearLayout14 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_threshold);
                                                                                        if (linearLayout14 != null) {
                                                                                            i = R.id.ll_threshold_tip;
                                                                                            LinearLayout linearLayout15 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_threshold_tip);
                                                                                            if (linearLayout15 != null) {
                                                                                                i = R.id.rl_add_key_button;
                                                                                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_add_key_button);
                                                                                                if (relativeLayout != null) {
                                                                                                    i = R.id.rl_confirm_shadow;
                                                                                                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_confirm_shadow);
                                                                                                    if (relativeLayout2 != null) {
                                                                                                        i = R.id.rl_keys;
                                                                                                        RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_keys);
                                                                                                        if (relativeLayout3 != null) {
                                                                                                            i = R.id.rl_more;
                                                                                                            RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_more);
                                                                                                            if (relativeLayout4 != null) {
                                                                                                                i = R.id.rl_operations;
                                                                                                                RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_operations);
                                                                                                                if (relativeLayout5 != null) {
                                                                                                                    i = R.id.rl_threshold;
                                                                                                                    RelativeLayout relativeLayout6 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_threshold);
                                                                                                                    if (relativeLayout6 != null) {
                                                                                                                        i = R.id.tv_addkey_desc;
                                                                                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_addkey_desc);
                                                                                                                        if (textView3 != null) {
                                                                                                                            i = R.id.tv_confirm;
                                                                                                                            Button button = (Button) ViewBindings.findChildViewById(view, R.id.tv_confirm);
                                                                                                                            if (button != null) {
                                                                                                                                i = R.id.tv_operations_desc;
                                                                                                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_operations_desc);
                                                                                                                                if (textView4 != null) {
                                                                                                                                    i = R.id.tv_operations_tip;
                                                                                                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_operations_tip);
                                                                                                                                    if (textView5 != null) {
                                                                                                                                        i = R.id.tv_permissionType;
                                                                                                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_permissionType);
                                                                                                                                        if (textView6 != null) {
                                                                                                                                            i = R.id.tv_permissionname_tip;
                                                                                                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_permissionname_tip);
                                                                                                                                            if (textView7 != null) {
                                                                                                                                                i = R.id.tv_threshold_tip;
                                                                                                                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_threshold_tip);
                                                                                                                                                if (textView8 != null) {
                                                                                                                                                    i = R.id.tv_weight_tip;
                                                                                                                                                    TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_weight_tip);
                                                                                                                                                    if (textView9 != null) {
                                                                                                                                                        i = R.id.view_keys_light;
                                                                                                                                                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.view_keys_light);
                                                                                                                                                        if (imageView3 != null) {
                                                                                                                                                            i = R.id.view_keys_normal;
                                                                                                                                                            View findChildViewById = ViewBindings.findChildViewById(view, R.id.view_keys_normal);
                                                                                                                                                            if (findChildViewById != null) {
                                                                                                                                                                i = R.id.view_operations_light;
                                                                                                                                                                ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.view_operations_light);
                                                                                                                                                                if (imageView4 != null) {
                                                                                                                                                                    i = R.id.view_operations_normal;
                                                                                                                                                                    View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.view_operations_normal);
                                                                                                                                                                    if (findChildViewById2 != null) {
                                                                                                                                                                        i = R.id.view_permissionname_light;
                                                                                                                                                                        ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.view_permissionname_light);
                                                                                                                                                                        if (imageView5 != null) {
                                                                                                                                                                            i = R.id.view_permissionname_normal;
                                                                                                                                                                            View findChildViewById3 = ViewBindings.findChildViewById(view, R.id.view_permissionname_normal);
                                                                                                                                                                            if (findChildViewById3 != null) {
                                                                                                                                                                                i = R.id.view_threshold_light;
                                                                                                                                                                                ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, R.id.view_threshold_light);
                                                                                                                                                                                if (imageView6 != null) {
                                                                                                                                                                                    i = R.id.view_threshold_normal;
                                                                                                                                                                                    View findChildViewById4 = ViewBindings.findChildViewById(view, R.id.view_threshold_normal);
                                                                                                                                                                                    if (findChildViewById4 != null) {
                                                                                                                                                                                        return new AcDopermissionBinding((ScrollView) view, textView, editText, editText2, textView2, tagFlowLayout, imageView, imageView2, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6, linearLayout7, linearLayout8, linearLayout9, linearLayout10, linearLayout11, linearLayout12, linearLayout13, linearLayout14, linearLayout15, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5, relativeLayout6, textView3, button, textView4, textView5, textView6, textView7, textView8, textView9, imageView3, findChildViewById, imageView4, findChildViewById2, imageView5, findChildViewById3, imageView6, findChildViewById4);
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
