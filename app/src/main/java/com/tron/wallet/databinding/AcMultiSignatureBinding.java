package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.tron.wallet.common.components.ErrorEdiTextLayout;
import com.tron.wallet.common.components.TrimEditText;
import com.tronlinkpro.wallet.R;
public final class AcMultiSignatureBinding implements ViewBinding {
    public final RelativeLayout backview;
    public final RelativeLayout backview2;
    public final View bottomLine;
    public final View bottomLine2;
    public final Button btConfirm;
    public final Button btGo;
    public final ErrorEdiTextLayout eetSelectAddress;
    public final TrimEditText etSelectAddress;
    public final ImageView ivCommonLeft;
    public final ImageView ivCommonLeft2;
    public final ImageView ivEdit;
    public final ImageView ivMore;
    public final ImageView ivOneDelete;
    public final ImageView ivQr;
    public final ImageView ivQr2;
    public final LinearLayout llCommonLeft;
    public final LinearLayout llCommonLeft2;
    public final LinearLayout llEdit;
    public final LinearLayout llEmpty;
    public final LinearLayout llIndicator;
    public final LinearLayout llMore;
    public final RelativeLayout rlAddressBook;
    public final RelativeLayout rlBack;
    public final RelativeLayout rlBackTwo;
    public final LinearLayout rlMiddle;
    public final RelativeLayout rlRight;
    public final RelativeLayout rlRight2;
    public final RelativeLayout rlSelect;
    public final RelativeLayout rlUc;
    private final FrameLayout rootView;
    public final LinearLayout statusbar;
    public final LinearLayout statusbar2;
    public final TextView tvAdd;
    public final TextView tvCommonTitle;
    public final TextView tvCommonTitle2;
    public final TextView tvEmptyAddpermissions;
    public final TextView tvEmptyDesc;
    public final TextView tvPermissionName;
    public final TextView tvSa;
    public final TextView tvUc;
    public final TextView tvUcAddress;
    public final View vLine;
    public final ViewPager2 viewpager;
    public final LinearLayout viewpagerLayout;

    @Override
    public FrameLayout getRoot() {
        return this.rootView;
    }

    private AcMultiSignatureBinding(FrameLayout frameLayout, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, View view, View view2, Button button, Button button2, ErrorEdiTextLayout errorEdiTextLayout, TrimEditText trimEditText, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, ImageView imageView7, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, LinearLayout linearLayout6, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, LinearLayout linearLayout7, RelativeLayout relativeLayout6, RelativeLayout relativeLayout7, RelativeLayout relativeLayout8, RelativeLayout relativeLayout9, LinearLayout linearLayout8, LinearLayout linearLayout9, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, View view3, ViewPager2 viewPager2, LinearLayout linearLayout10) {
        this.rootView = frameLayout;
        this.backview = relativeLayout;
        this.backview2 = relativeLayout2;
        this.bottomLine = view;
        this.bottomLine2 = view2;
        this.btConfirm = button;
        this.btGo = button2;
        this.eetSelectAddress = errorEdiTextLayout;
        this.etSelectAddress = trimEditText;
        this.ivCommonLeft = imageView;
        this.ivCommonLeft2 = imageView2;
        this.ivEdit = imageView3;
        this.ivMore = imageView4;
        this.ivOneDelete = imageView5;
        this.ivQr = imageView6;
        this.ivQr2 = imageView7;
        this.llCommonLeft = linearLayout;
        this.llCommonLeft2 = linearLayout2;
        this.llEdit = linearLayout3;
        this.llEmpty = linearLayout4;
        this.llIndicator = linearLayout5;
        this.llMore = linearLayout6;
        this.rlAddressBook = relativeLayout3;
        this.rlBack = relativeLayout4;
        this.rlBackTwo = relativeLayout5;
        this.rlMiddle = linearLayout7;
        this.rlRight = relativeLayout6;
        this.rlRight2 = relativeLayout7;
        this.rlSelect = relativeLayout8;
        this.rlUc = relativeLayout9;
        this.statusbar = linearLayout8;
        this.statusbar2 = linearLayout9;
        this.tvAdd = textView;
        this.tvCommonTitle = textView2;
        this.tvCommonTitle2 = textView3;
        this.tvEmptyAddpermissions = textView4;
        this.tvEmptyDesc = textView5;
        this.tvPermissionName = textView6;
        this.tvSa = textView7;
        this.tvUc = textView8;
        this.tvUcAddress = textView9;
        this.vLine = view3;
        this.viewpager = viewPager2;
        this.viewpagerLayout = linearLayout10;
    }

    public static AcMultiSignatureBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcMultiSignatureBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_multi_signature, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcMultiSignatureBinding bind(View view) {
        int i = R.id.backview;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.backview);
        if (relativeLayout != null) {
            i = R.id.backview2;
            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.backview2);
            if (relativeLayout2 != null) {
                i = R.id.bottom_line;
                View findChildViewById = ViewBindings.findChildViewById(view, R.id.bottom_line);
                if (findChildViewById != null) {
                    i = R.id.bottom_line2;
                    View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.bottom_line2);
                    if (findChildViewById2 != null) {
                        i = R.id.bt_confirm;
                        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_confirm);
                        if (button != null) {
                            i = R.id.bt_go;
                            Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.bt_go);
                            if (button2 != null) {
                                i = R.id.eet_select_address;
                                ErrorEdiTextLayout errorEdiTextLayout = (ErrorEdiTextLayout) ViewBindings.findChildViewById(view, R.id.eet_select_address);
                                if (errorEdiTextLayout != null) {
                                    i = R.id.et_select_address;
                                    TrimEditText trimEditText = (TrimEditText) ViewBindings.findChildViewById(view, R.id.et_select_address);
                                    if (trimEditText != null) {
                                        i = R.id.iv_common_left;
                                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_common_left);
                                        if (imageView != null) {
                                            i = R.id.iv_common_left2;
                                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_common_left2);
                                            if (imageView2 != null) {
                                                i = R.id.iv_edit;
                                                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_edit);
                                                if (imageView3 != null) {
                                                    i = R.id.iv_more;
                                                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_more);
                                                    if (imageView4 != null) {
                                                        i = R.id.iv_one_delete;
                                                        ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_one_delete);
                                                        if (imageView5 != null) {
                                                            i = R.id.iv_qr;
                                                            ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_qr);
                                                            if (imageView6 != null) {
                                                                i = R.id.iv_qr2;
                                                                ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_qr2);
                                                                if (imageView7 != null) {
                                                                    i = R.id.ll_common_left;
                                                                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_common_left);
                                                                    if (linearLayout != null) {
                                                                        i = R.id.ll_common_left2;
                                                                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_common_left2);
                                                                        if (linearLayout2 != null) {
                                                                            i = R.id.ll_edit;
                                                                            LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_edit);
                                                                            if (linearLayout3 != null) {
                                                                                i = R.id.ll_empty;
                                                                                LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_empty);
                                                                                if (linearLayout4 != null) {
                                                                                    i = R.id.ll_indicator;
                                                                                    LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_indicator);
                                                                                    if (linearLayout5 != null) {
                                                                                        i = R.id.ll_more;
                                                                                        LinearLayout linearLayout6 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_more);
                                                                                        if (linearLayout6 != null) {
                                                                                            i = R.id.rl_address_book;
                                                                                            RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_address_book);
                                                                                            if (relativeLayout3 != null) {
                                                                                                i = R.id.rl_back;
                                                                                                RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_back);
                                                                                                if (relativeLayout4 != null) {
                                                                                                    i = R.id.rl_back_two;
                                                                                                    RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_back_two);
                                                                                                    if (relativeLayout5 != null) {
                                                                                                        i = R.id.rl_middle;
                                                                                                        LinearLayout linearLayout7 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.rl_middle);
                                                                                                        if (linearLayout7 != null) {
                                                                                                            i = R.id.rl_right;
                                                                                                            RelativeLayout relativeLayout6 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_right);
                                                                                                            if (relativeLayout6 != null) {
                                                                                                                i = R.id.rl_right2;
                                                                                                                RelativeLayout relativeLayout7 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_right2);
                                                                                                                if (relativeLayout7 != null) {
                                                                                                                    i = R.id.rl_select;
                                                                                                                    RelativeLayout relativeLayout8 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_select);
                                                                                                                    if (relativeLayout8 != null) {
                                                                                                                        i = R.id.rl_uc;
                                                                                                                        RelativeLayout relativeLayout9 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_uc);
                                                                                                                        if (relativeLayout9 != null) {
                                                                                                                            i = R.id.statusbar;
                                                                                                                            LinearLayout linearLayout8 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.statusbar);
                                                                                                                            if (linearLayout8 != null) {
                                                                                                                                i = R.id.statusbar_2;
                                                                                                                                LinearLayout linearLayout9 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.statusbar_2);
                                                                                                                                if (linearLayout9 != null) {
                                                                                                                                    i = R.id.tv_add;
                                                                                                                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_add);
                                                                                                                                    if (textView != null) {
                                                                                                                                        i = R.id.tv_common_title;
                                                                                                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_common_title);
                                                                                                                                        if (textView2 != null) {
                                                                                                                                            i = R.id.tv_common_title2;
                                                                                                                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_common_title2);
                                                                                                                                            if (textView3 != null) {
                                                                                                                                                i = R.id.tv_empty_addpermissions;
                                                                                                                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_empty_addpermissions);
                                                                                                                                                if (textView4 != null) {
                                                                                                                                                    i = R.id.tv_empty_desc;
                                                                                                                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_empty_desc);
                                                                                                                                                    if (textView5 != null) {
                                                                                                                                                        i = R.id.tv_permission_name;
                                                                                                                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_permission_name);
                                                                                                                                                        if (textView6 != null) {
                                                                                                                                                            i = R.id.tv_sa;
                                                                                                                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_sa);
                                                                                                                                                            if (textView7 != null) {
                                                                                                                                                                i = R.id.tv_uc;
                                                                                                                                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_uc);
                                                                                                                                                                if (textView8 != null) {
                                                                                                                                                                    i = R.id.tv_uc_address;
                                                                                                                                                                    TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_uc_address);
                                                                                                                                                                    if (textView9 != null) {
                                                                                                                                                                        i = R.id.v_line;
                                                                                                                                                                        View findChildViewById3 = ViewBindings.findChildViewById(view, R.id.v_line);
                                                                                                                                                                        if (findChildViewById3 != null) {
                                                                                                                                                                            i = R.id.viewpager;
                                                                                                                                                                            ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, R.id.viewpager);
                                                                                                                                                                            if (viewPager2 != null) {
                                                                                                                                                                                i = R.id.viewpager_layout;
                                                                                                                                                                                LinearLayout linearLayout10 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.viewpager_layout);
                                                                                                                                                                                if (linearLayout10 != null) {
                                                                                                                                                                                    return new AcMultiSignatureBinding((FrameLayout) view, relativeLayout, relativeLayout2, findChildViewById, findChildViewById2, button, button2, errorEdiTextLayout, trimEditText, imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6, relativeLayout3, relativeLayout4, relativeLayout5, linearLayout7, relativeLayout6, relativeLayout7, relativeLayout8, relativeLayout9, linearLayout8, linearLayout9, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, findChildViewById3, viewPager2, linearLayout10);
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
