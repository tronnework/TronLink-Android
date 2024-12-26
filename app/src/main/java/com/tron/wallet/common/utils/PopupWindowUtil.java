package com.tron.wallet.common.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.animator.EmptyAnimator;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.core.CenterPopupView;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.interfaces.OnCancelListener;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.lxj.xpopup.util.XPopupUtils;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.wallet.business.addassets2.AssetsConfig;
import com.tron.wallet.business.addassets2.bean.TokenSortType;
import com.tron.wallet.business.assetshome.SortHelper;
import com.tron.wallet.business.assetshome.adapter.AssetSortAdapter;
import com.tron.wallet.business.ledger.manage.EquipmentFailListener;
import com.tron.wallet.business.ledger.manage.EquipmentRemoveListener;
import com.tron.wallet.business.ledger.manage.LedgerProgressView;
import com.tron.wallet.business.vote.adapter.VoteSortAdapter;
import com.tron.wallet.business.walletmanager.importwallet.base.repo.entity.BleRepoDevice;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.components.ClickUrlSpan;
import com.tron.wallet.common.components.EllipsizedTextView;
import com.tron.wallet.common.components.MultiLineTextPopupView;
import com.tron.wallet.common.components.SortIDPopupView;
import com.tron.wallet.common.components.popupwindow.AutoSizeTextPopupWindow;
import com.tron.wallet.common.interfaces.CloseClickListener;
import com.tron.wallet.common.interfaces.MultiImportListener;
import com.tron.wallet.common.interfaces.OnSeletedListener;
import com.tron.wallet.common.interfaces.PopWindowCallback;
import com.tron.wallet.common.interfaces.PopWindowSimpleButtonListener;
import com.tron.wallet.common.utils.addressscam.ScamAddressPopWindowCallback;
import com.tronlinkpro.wallet.R;
import java.text.NumberFormat;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
public class PopupWindowUtil {
    public static void showFreezePop(Context context, int i, ImageView imageView) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.pop_resource, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.content)).setText(i);
        PopupWindow popupWindow = new PopupWindow(inflate, -2, -2);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAsDropDown(imageView, 0, 0, 80);
    }

    public static void showShieldTips(Context context, View view, int i) {
        float f = context.getResources().getDisplayMetrics().density;
        TextView textView = new TextView(context);
        textView.setText(i);
        textView.setBackgroundResource(R.drawable.roundborder_021c31_90);
        textView.setTextSize(12.0f);
        int ceil = (int) Math.ceil(14.0f * f);
        textView.setPadding(ceil, ceil, ceil, ceil);
        textView.setTextColor(context.getResources().getColor(R.color.white));
        int i2 = (int) ((127.0f * f) + 0.5f);
        PopupWindow popupWindow = new PopupWindow(textView, i2, -2);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAsDropDown(view, -(i2 - view.getMeasuredWidth()), (int) ((f * 9.0f) + 0.5f));
    }

    public static PopupWindow showSortPop(Context context, int i, final OnSeletedListener.OnPopupSelectedListener onPopupSelectedListener) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.pop_vote_sort, (ViewGroup) null);
        final PopupWindow popupWindow = new PopupWindow(inflate, -1, -1) {
            @Override
            public void showAtLocation(View view, int i2, int i3, int i4) {
                if ((view.getContext() instanceof Activity) && !((Activity) view.getContext()).isDestroyed()) {
                    Window window = ((Activity) view.getContext()).getWindow();
                    WindowManager.LayoutParams attributes = window.getAttributes();
                    attributes.alpha = 0.6f;
                    window.setAttributes(attributes);
                    super.showAtLocation(view, i2, i3, i4);
                }
            }

            @Override
            public void dismiss() {
                if (getContentView().getContext() instanceof Activity) {
                    Activity activity = (Activity) getContentView().getContext();
                    if (activity.isDestroyed()) {
                        return;
                    }
                    Window window = activity.getWindow();
                    WindowManager.LayoutParams attributes = window.getAttributes();
                    attributes.alpha = 1.0f;
                    window.setAttributes(attributes);
                    super.dismiss();
                }
            }
        };
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, 1, false));
        VoteSortAdapter voteSortAdapter = new VoteSortAdapter(context, i);
        recyclerView.setAdapter(voteSortAdapter);
        voteSortAdapter.setItemClick(new VoteSortAdapter.itemClick() {
            @Override
            public final void onClick(int i2) {
                PopupWindowUtil.lambda$showSortPop$0(OnSeletedListener.OnPopupSelectedListener.this, popupWindow, i2);
            }
        });
        ((RelativeLayout) inflate.findViewById(R.id.rootview)).setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        return popupWindow;
    }

    public static void lambda$showSortPop$0(OnSeletedListener.OnPopupSelectedListener onPopupSelectedListener, PopupWindow popupWindow, int i) {
        if (onPopupSelectedListener != null) {
            onPopupSelectedListener.onSelected(popupWindow, i);
        }
    }

    public static void showVotePop(Context context, View view, int i, final OnSeletedListener onSeletedListener) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.pop_vote_select, (ViewGroup) null);
        final PopupWindow popupWindow = new PopupWindow(inflate, -2, -2);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        TextView textView = (TextView) inflate.findViewById(R.id.tv_all);
        TextView textView2 = (TextView) inflate.findViewById(R.id.tv_me);
        if (i == 0) {
            textView.setSelected(true);
            textView2.setSelected(false);
        } else {
            textView.setSelected(false);
            textView2.setSelected(true);
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                OnSeletedListener onSeletedListener2 = OnSeletedListener.this;
                if (onSeletedListener2 != null) {
                    onSeletedListener2.onSeleted(0);
                    PopupWindow popupWindow2 = popupWindow;
                    if (popupWindow2 != null) {
                        popupWindow2.dismiss();
                    }
                }
                PopupWindow popupWindow3 = popupWindow;
                if (popupWindow3 != null) {
                    popupWindow3.dismiss();
                }
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                OnSeletedListener onSeletedListener2 = OnSeletedListener.this;
                if (onSeletedListener2 != null) {
                    onSeletedListener2.onSeleted(1);
                }
                PopupWindow popupWindow2 = popupWindow;
                if (popupWindow2 != null) {
                    popupWindow2.dismiss();
                }
            }
        });
        popupWindow.showAsDropDown(view, 0, 0);
    }

    private static int makeDropDownMeasureSpec(int i) {
        return View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i), i == -2 ? 0 : MeasureSpec.AT_MOST);
    }

    public static PopupWindow showFavoriteWindow(Context context, View view, AutoSizeTextPopupWindow.OnPopupWindowClickListener onPopupWindowClickListener, int i) {
        int dip2px = UIUtils.dip2px(15.0f);
        int dip2px2 = UIUtils.dip2px(20.0f);
        AutoSizeTextPopupWindow autoSizeTextPopupWindow = new AutoSizeTextPopupWindow(context);
        autoSizeTextPopupWindow.setBackground(R.drawable.bg_vote_select);
        autoSizeTextPopupWindow.setTextSize(14.0f);
        autoSizeTextPopupWindow.setPadding(dip2px, dip2px2, dip2px, dip2px2);
        autoSizeTextPopupWindow.setTextColor(-1);
        autoSizeTextPopupWindow.setText(i);
        autoSizeTextPopupWindow.setOnPopupWindowClickListener(onPopupWindowClickListener);
        return autoSizeTextPopupWindow;
    }

    public static PopupWindow showFirstEnterVotePopupWindow(Context context, View view) {
        View inflate = View.inflate(context, R.layout.vote_first_enter_pop, null);
        final PopupWindow popupWindow = new PopupWindow(context);
        popupWindow.setContentView(inflate);
        popupWindow.setWidth(UIUtils.dip2px(216.0f));
        popupWindow.setHeight(UIUtils.dip2px(72.0f));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        View findViewById = inflate.findViewById(R.id.iv_close);
        int dip2px = UIUtils.dip2px(10.0f);
        TouchDelegateUtils.expandViewTouchDelegate(findViewById, dip2px, dip2px, dip2px, dip2px);
        findViewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view2) {
                popupWindow.dismiss();
            }
        });
        popupWindow.showAsDropDown(view, UIUtils.dip2px(200.0f) * (-1), UIUtils.dip2px(20.0f));
        return popupWindow;
    }

    public static PopupWindow showFirstEnterVoteSortPopupWindow(Context context, View view) {
        View inflate = View.inflate(context, R.layout.vote_sort_first_enter_pop, null);
        final PopupWindow popupWindow = new PopupWindow(context);
        popupWindow.setContentView(inflate);
        popupWindow.setWidth(UIUtils.dip2px(222.0f));
        popupWindow.setHeight(UIUtils.dip2px(54.0f));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        View findViewById = inflate.findViewById(R.id.iv_close);
        int dip2px = UIUtils.dip2px(10.0f);
        TouchDelegateUtils.expandViewTouchDelegate(findViewById, dip2px, dip2px, dip2px, dip2px);
        findViewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view2) {
                popupWindow.dismiss();
            }
        });
        popupWindow.showAsDropDown(view, UIUtils.dip2px(192.0f) * (-1), UIUtils.dip2px(8.0f));
        return popupWindow;
    }

    public static void showVoteNumberPopupWindow(Context context, View view, View view2) {
        SortIDPopupView sortIDPopupView = new SortIDPopupView(context);
        new XPopup.Builder(context).atView(view).hasShadowBg(false).customAnimator(new EmptyAnimator(sortIDPopupView, 200)).asCustom(sortIDPopupView).show();
    }

    public static PopupWindow showAssetSortPop(final Context context, int i, boolean z, boolean z2, final int i2, final SortHelper.OnSortChangedListener onSortChangedListener) {
        final AtomicInteger atomicInteger = new AtomicInteger(i);
        final AtomicBoolean atomicBoolean = new AtomicBoolean(z);
        final AtomicBoolean atomicBoolean2 = new AtomicBoolean(z2);
        View inflate = LayoutInflater.from(context).inflate(R.layout.pop_asset_sort, (ViewGroup) null);
        final PopupWindow popupWindow = new PopupWindow(inflate, -1, -1) {
            @Override
            public void showAtLocation(View view, int i3, int i4, int i5) {
                if ((view.getContext() instanceof Activity) && !((Activity) view.getContext()).isDestroyed()) {
                    ((Activity) view.getContext()).getWindow().setStatusBarColor(context.getResources().getColor(R.color.black_0E_60));
                    super.showAtLocation(view, i3, i4, i5);
                }
            }

            @Override
            public void dismiss() {
                if (getContentView().getContext() instanceof Activity) {
                    Activity activity = (Activity) getContentView().getContext();
                    if (activity.isDestroyed()) {
                        return;
                    }
                    activity.getWindow().setStatusBarColor(context.getResources().getColor(R.color.transparent));
                    super.dismiss();
                }
            }
        };
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.black_0E_60)));
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, 1, false));
        AssetSortAdapter assetSortAdapter = new AssetSortAdapter(context, i);
        recyclerView.setAdapter(assetSortAdapter);
        RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.rootview);
        assetSortAdapter.setItemClick(new AssetSortAdapter.itemClick() {
            @Override
            public final void onClick(int i3, boolean z3) {
                PopupWindowUtil.lambda$showAssetSortPop$4(SortHelper.OnSortChangedListener.this, popupWindow, i2, atomicBoolean, atomicBoolean2, atomicInteger, i3, z3);
            }
        });
        View findViewById = inflate.findViewById(R.id.li_hide_small_values);
        final ImageView imageView = (ImageView) inflate.findViewById(R.id.iv_select);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_hide_assets);
        ((ImageView) inflate.findViewById(R.id.iv_small_value_tips)).setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                MultiLineTextPopupView.Builder requiredWidth = new MultiLineTextPopupView.Builder().setPreferredShowUp(true).setAnchor(view).setRequiredWidth(UIUtils.dip2px(200.0f));
                String string = context.getString(R.string.assets_hide_0_token);
                requiredWidth.addKeyValue(String.format(string, "$" + NumberFormat.getInstance().format(AssetsConfig.getAssetValueLimit())), "").build(context).show();
            }
        });
        View findViewById2 = inflate.findViewById(R.id.li_scam_token);
        final ImageView imageView2 = (ImageView) inflate.findViewById(R.id.iv_select_scam);
        TextView textView2 = (TextView) inflate.findViewById(R.id.tv_confirm);
        ((TextView) inflate.findViewById(R.id.tv_cancle)).setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                popupWindow.dismiss();
            }
        });
        textView2.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                TokenSortType tokenSortType = TokenSortType.SORT_BY_USER;
                int i3 = atomicInteger.get();
                if (i3 == 0) {
                    tokenSortType = TokenSortType.SORT_BY_USER;
                } else if (i3 == 1) {
                    tokenSortType = TokenSortType.SORT_BY_VALUE;
                } else if (i3 == 2) {
                    tokenSortType = TokenSortType.SORT_BY_NAME;
                }
                onSortChangedListener.onSortChanged(popupWindow, tokenSortType, i2, atomicBoolean.get(), atomicBoolean2.get(), false);
            }
        });
        TextView textView3 = (TextView) inflate.findViewById(R.id.tv_hide_scam_assets);
        TokenItemUtil.initScamFlagTipsView(context, (ImageView) inflate.findViewById(R.id.iv_scam_token_tips), null, true);
        findViewById.setSelected(z);
        int i3 = R.mipmap.ic_check_selected;
        imageView.setImageResource(z ? R.mipmap.ic_check_selected : R.mipmap.ic_check_unselect);
        findViewById2.setSelected(z2);
        if (!z2) {
            i3 = R.mipmap.ic_check_unselect;
        }
        imageView2.setImageResource(i3);
        findViewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                PopupWindowUtil.lambda$showAssetSortPop$5(atomicBoolean, imageView, view);
            }
        });
        findViewById2.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                PopupWindowUtil.lambda$showAssetSortPop$6(atomicBoolean2, imageView2, view);
            }
        });
        return popupWindow;
    }

    public static void lambda$showAssetSortPop$4(SortHelper.OnSortChangedListener onSortChangedListener, PopupWindow popupWindow, int i, AtomicBoolean atomicBoolean, AtomicBoolean atomicBoolean2, AtomicInteger atomicInteger, int i2, boolean z) {
        if (z && i2 == 0) {
            TokenSortType tokenSortType = TokenSortType.SORT_BY_USER;
            if (i2 == -1 || i2 == 0) {
                tokenSortType = TokenSortType.SORT_BY_USER_MANUAL;
            } else if (i2 == 1) {
                tokenSortType = TokenSortType.SORT_BY_VALUE;
            } else if (i2 == 2) {
                tokenSortType = TokenSortType.SORT_BY_NAME;
            }
            onSortChangedListener.onSortChanged(popupWindow, tokenSortType, i, atomicBoolean.get(), atomicBoolean2.get(), true);
            return;
        }
        atomicInteger.set(i2);
    }

    public static void lambda$showAssetSortPop$5(AtomicBoolean atomicBoolean, ImageView imageView, View view) {
        boolean z = !view.isSelected();
        view.setSelected(z);
        atomicBoolean.set(z);
        imageView.setImageResource(z ? R.mipmap.ic_check_selected : R.mipmap.ic_check_unselect);
    }

    public static void lambda$showAssetSortPop$6(AtomicBoolean atomicBoolean, ImageView imageView, View view) {
        boolean z = !view.isSelected();
        view.setSelected(z);
        atomicBoolean.set(z);
        imageView.setImageResource(z ? R.mipmap.ic_check_selected : R.mipmap.ic_check_unselect);
    }

    public static PopupWindow showMyAssetSortPop(final Context context, boolean z, boolean z2, final int i, final SortHelper.OnSortChangedListener onSortChangedListener) {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(z);
        final AtomicBoolean atomicBoolean2 = new AtomicBoolean(z2);
        View inflate = LayoutInflater.from(context).inflate(R.layout.pop_my_asset_sort, (ViewGroup) null);
        final PopupWindow popupWindow = new PopupWindow(inflate, -1, -1) {
            @Override
            public void showAtLocation(View view, int i2, int i3, int i4) {
                if ((view.getContext() instanceof Activity) && !((Activity) view.getContext()).isDestroyed()) {
                    ((Activity) view.getContext()).getWindow().setStatusBarColor(context.getResources().getColor(R.color.black_0E_60));
                    super.showAtLocation(view, i2, i3, i4);
                }
            }

            @Override
            public void dismiss() {
                if (getContentView().getContext() instanceof Activity) {
                    Activity activity = (Activity) getContentView().getContext();
                    if (activity.isDestroyed()) {
                        return;
                    }
                    activity.getWindow().setStatusBarColor(context.getResources().getColor(R.color.transparent));
                    super.dismiss();
                }
            }
        };
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.black_0E_60)));
        View findViewById = inflate.findViewById(R.id.li_hide_small_values);
        final ImageView imageView = (ImageView) inflate.findViewById(R.id.iv_select);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_hide_assets);
        ((ImageView) inflate.findViewById(R.id.iv_small_value_tips)).setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                MultiLineTextPopupView.Builder requiredWidth = new MultiLineTextPopupView.Builder().setPreferredShowUp(true).setAnchor(view).setRequiredWidth(-2);
                String string = context.getString(R.string.assets_hide_0_token);
                requiredWidth.addKeyValue(String.format(string, "$" + NumberFormat.getInstance().format(AssetsConfig.getAssetValueLimit())), "").build(context).show();
            }
        });
        View findViewById2 = inflate.findViewById(R.id.li_scam_token);
        final ImageView imageView2 = (ImageView) inflate.findViewById(R.id.iv_select_scam);
        TextView textView2 = (TextView) inflate.findViewById(R.id.tv_confirm);
        ((TextView) inflate.findViewById(R.id.tv_cancle)).setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                popupWindow.dismiss();
            }
        });
        textView2.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                SortHelper.OnSortChangedListener.this.onSortChanged(popupWindow, TokenSortType.SORT_BY_USER, i, atomicBoolean.get(), atomicBoolean2.get(), false);
            }
        });
        TextView textView3 = (TextView) inflate.findViewById(R.id.tv_hide_scam_assets);
        TokenItemUtil.initScamFlagTipsView(context, (ImageView) inflate.findViewById(R.id.iv_scam_token_tips), null, true);
        findViewById.setSelected(z);
        int i2 = R.mipmap.ic_check_selected;
        imageView.setImageResource(z ? R.mipmap.ic_check_selected : R.mipmap.ic_check_unselect);
        findViewById2.setSelected(z2);
        if (!z2) {
            i2 = R.mipmap.ic_check_unselect;
        }
        imageView2.setImageResource(i2);
        findViewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                PopupWindowUtil.lambda$showMyAssetSortPop$7(atomicBoolean, imageView, view);
            }
        });
        findViewById2.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                PopupWindowUtil.lambda$showMyAssetSortPop$8(atomicBoolean2, imageView2, view);
            }
        });
        return popupWindow;
    }

    public static void lambda$showMyAssetSortPop$7(AtomicBoolean atomicBoolean, ImageView imageView, View view) {
        boolean z = !view.isSelected();
        view.setSelected(z);
        atomicBoolean.set(z);
        imageView.setImageResource(z ? R.mipmap.ic_check_selected : R.mipmap.ic_check_unselect);
    }

    public static void lambda$showMyAssetSortPop$8(AtomicBoolean atomicBoolean, ImageView imageView, View view) {
        boolean z = !view.isSelected();
        view.setSelected(z);
        atomicBoolean.set(z);
        imageView.setImageResource(z ? R.mipmap.ic_check_selected : R.mipmap.ic_check_unselect);
    }

    public static PopupWindow showFirstEnterAssetSortPopupWindow(Context context, View view, final CloseClickListener closeClickListener) {
        View inflate = View.inflate(context, R.layout.asset_sort_first_enter_pop, null);
        final PopupWindow popupWindow = new PopupWindow(context);
        popupWindow.setContentView(inflate);
        popupWindow.setWidth(UIUtils.dip2px(187.0f));
        if (LanguageUtils.languageZH(context)) {
            popupWindow.setHeight(UIUtils.dip2px(54.0f));
        } else {
            popupWindow.setHeight(UIUtils.dip2px(71.0f));
        }
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        View findViewById = inflate.findViewById(R.id.iv_close);
        int dip2px = UIUtils.dip2px(10.0f);
        TouchDelegateUtils.expandViewTouchDelegate(findViewById, dip2px, dip2px, dip2px, dip2px);
        findViewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                CloseClickListener.this.onClose();
                popupWindow.dismiss();
            }
        });
        popupWindow.showAsDropDown(view, UIUtils.dip2px(130.0f) * (-1), UIUtils.dip2px(8.0f));
        return popupWindow;
    }

    public static PopupWindow showFirstEnterNewAssetPopupWindow(Context context, View view, final CloseClickListener closeClickListener) {
        View inflate = View.inflate(context, R.layout.asset_new_first_enter_pop, null);
        final PopupWindow popupWindow = new PopupWindow(context);
        popupWindow.setContentView(inflate);
        popupWindow.setWidth(UIUtils.dip2px(221.0f));
        if (LanguageUtils.languageZH(context)) {
            popupWindow.setHeight(UIUtils.dip2px(54.0f));
        } else {
            popupWindow.setHeight(UIUtils.dip2px(71.0f));
        }
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        View findViewById = inflate.findViewById(R.id.iv_close);
        int dip2px = UIUtils.dip2px(10.0f);
        TouchDelegateUtils.expandViewTouchDelegate(findViewById, dip2px, dip2px, dip2px, dip2px);
        findViewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                CloseClickListener.this.onClose();
                popupWindow.dismiss();
            }
        });
        popupWindow.showAsDropDown(view, UIUtils.dip2px(15.0f), 0);
        return popupWindow;
    }

    public static PopupWindow showFirstEnterAllAssetPopupWindow(Context context, View view, final CloseClickListener closeClickListener) {
        View inflate = View.inflate(context, R.layout.asset_my_all_first_enter_pop, null);
        final PopupWindow popupWindow = new PopupWindow(context);
        popupWindow.setContentView(inflate);
        popupWindow.setWidth(UIUtils.dip2px(169.0f));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        View findViewById = inflate.findViewById(R.id.iv_close);
        int dip2px = UIUtils.dip2px(10.0f);
        TouchDelegateUtils.expandViewTouchDelegate(findViewById, dip2px, dip2px, dip2px, dip2px);
        findViewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                CloseClickListener.this.onClose();
                popupWindow.dismiss();
            }
        });
        popupWindow.showAsDropDown(view, UIUtils.dip2px(105.0f), UIUtils.dip2px(8.0f));
        return popupWindow;
    }

    public static PopupWindow showFirstEnterAddAssetPopupWindow(Context context, View view, final CloseClickListener closeClickListener) {
        View inflate = View.inflate(context, R.layout.asset_sort_add_first_enter_pop, null);
        final PopupWindow popupWindow = new PopupWindow(context);
        popupWindow.setContentView(inflate);
        popupWindow.setWidth(UIUtils.dip2px(208.0f));
        if (LanguageUtils.languageZH(context)) {
            popupWindow.setHeight(UIUtils.dip2px(54.0f));
        } else {
            popupWindow.setHeight(UIUtils.dip2px(71.0f));
        }
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        View findViewById = inflate.findViewById(R.id.iv_close);
        int dip2px = UIUtils.dip2px(10.0f);
        TouchDelegateUtils.expandViewTouchDelegate(findViewById, dip2px, dip2px, dip2px, dip2px);
        findViewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                CloseClickListener.this.onClose();
                popupWindow.dismiss();
            }
        });
        popupWindow.showAsDropDown(view, UIUtils.dip2px(135.0f) * (-1), UIUtils.dip2px(73.0f));
        return popupWindow;
    }

    public static PopupWindow showFirstEnterNoreminderPopupWindow(Context context, View view, final CloseClickListener closeClickListener) {
        View inflate = View.inflate(context, R.layout.asset_sort_no_reminder_first_enter_pop, null);
        final PopupWindow popupWindow = new PopupWindow(context);
        popupWindow.setContentView(inflate);
        popupWindow.setWidth(UIUtils.dip2px(234.0f));
        if (LanguageUtils.languageZH(context)) {
            popupWindow.setHeight(UIUtils.dip2px(54.0f));
        } else {
            popupWindow.setHeight(UIUtils.dip2px(71.0f));
        }
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        View findViewById = inflate.findViewById(R.id.iv_close);
        int dip2px = UIUtils.dip2px(10.0f);
        TouchDelegateUtils.expandViewTouchDelegate(findViewById, dip2px, dip2px, dip2px, dip2px);
        findViewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                CloseClickListener.this.onClose();
                popupWindow.dismiss();
            }
        });
        popupWindow.showAsDropDown(view, UIUtils.dip2px(161.0f) * (-1), UIUtils.dip2px(8.0f) - view.getPaddingBottom());
        return popupWindow;
    }

    public static PopupWindow showFirstEnterRemoveAssetPopupWindow(Context context, View view, final CloseClickListener closeClickListener) {
        View inflate = View.inflate(context, R.layout.asset_sort_remove_first_enter_pop, null);
        final PopupWindow popupWindow = new PopupWindow(context);
        popupWindow.setContentView(inflate);
        popupWindow.setWidth(UIUtils.dip2px(196.0f));
        if (LanguageUtils.languageZH(context)) {
            popupWindow.setHeight(UIUtils.dip2px(54.0f));
        } else {
            popupWindow.setHeight(UIUtils.dip2px(71.0f));
        }
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        View findViewById = inflate.findViewById(R.id.iv_close);
        int dip2px = UIUtils.dip2px(10.0f);
        TouchDelegateUtils.expandViewTouchDelegate(findViewById, dip2px, dip2px, dip2px, dip2px);
        findViewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                CloseClickListener.this.onClose();
                popupWindow.dismiss();
            }
        });
        popupWindow.showAsDropDown(view, UIUtils.dip2px(130.0f) * (-1), UIUtils.dip2px(152.0f));
        return popupWindow;
    }

    public static BasePopupView getConfirmCancelPopupView(Context context, CharSequence charSequence, int i, int i2, int i3, OnConfirmListener onConfirmListener, OnCancelListener onCancelListener) {
        return new XPopup.Builder(context).maxWidth(UIUtils.dip2px(300.0f)).dismissOnTouchOutside(false).asConfirm(charSequence, context.getString(i), context.getString(i2), context.getString(i3), onConfirmListener, onCancelListener, false, R.layout.confirm_cancel_popup_view);
    }

    public static BasePopupView getConfirmCancelPopupViewBlackWhite(Context context, CharSequence charSequence, CharSequence charSequence2, int i, int i2, OnConfirmListener onConfirmListener, OnCancelListener onCancelListener, boolean z) {
        if (z) {
            return new XPopup.Builder(context).maxWidth(XPopupUtils.getScreenWidth(context)).dismissOnTouchOutside(false).asConfirm(charSequence, charSequence2, context.getString(i), context.getString(i2), onConfirmListener, onCancelListener, true, R.layout.confirm_popup_view_black);
        }
        return new XPopup.Builder(context).maxWidth(XPopupUtils.getScreenWidth(context)).dismissOnTouchOutside(false).asConfirm(charSequence, charSequence2, context.getString(i), context.getString(i2), onConfirmListener, onCancelListener, false, R.layout.confirm_cancel_popup_view_black);
    }

    public static PopupWindow showRemovePop(final Context context, final int i, final BleRepoDevice bleRepoDevice, final EquipmentRemoveListener equipmentRemoveListener) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.pop_remove_equipment, (ViewGroup) null);
        final PopupWindow popupWindow = new PopupWindow(inflate, -1, -1) {
            @Override
            public void showAtLocation(View view, int i2, int i3, int i4) {
                if ((view.getContext() instanceof Activity) && !((Activity) view.getContext()).isDestroyed()) {
                    ((Activity) view.getContext()).getWindow().setStatusBarColor(context.getResources().getColor(R.color.black_0E_60));
                    super.showAtLocation(view, i2, i3, i4);
                }
            }

            @Override
            public void dismiss() {
                if (getContentView().getContext() instanceof Activity) {
                    Activity activity = (Activity) getContentView().getContext();
                    if (activity.isDestroyed()) {
                        return;
                    }
                    activity.getWindow().setStatusBarColor(context.getResources().getColor(R.color.transparent));
                    super.dismiss();
                }
            }
        };
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.black_0E_60)));
        ((TextView) inflate.findViewById(R.id.tv_name)).setText(bleRepoDevice.getName());
        ((Button) inflate.findViewById(R.id.remove)).setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                PopupWindowUtil.lambda$showRemovePop$9(EquipmentRemoveListener.this, i, bleRepoDevice, popupWindow, view);
            }
        });
        ((RelativeLayout) inflate.findViewById(R.id.rootview)).setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        return popupWindow;
    }

    public static void lambda$showRemovePop$9(EquipmentRemoveListener equipmentRemoveListener, int i, BleRepoDevice bleRepoDevice, PopupWindow popupWindow, View view) {
        equipmentRemoveListener.remove(i, bleRepoDevice);
        popupWindow.dismiss();
    }

    public static PopupWindow showLedgerLoadingPop(final Context context, String str, LedgerProgressView.STATUS status, final CloseClickListener closeClickListener) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.pop_loading_equipment, (ViewGroup) null);
        LedgerProgressView ledgerProgressView = (LedgerProgressView) inflate.findViewById(R.id.loading_view);
        ledgerProgressView.setEquipmentName(str);
        ledgerProgressView.setStatus(status);
        final PopupWindow popupWindow = new PopupWindow(inflate, -1, -1) {
            @Override
            public void showAtLocation(View view, int i, int i2, int i3) {
                if ((view.getContext() instanceof Activity) && !((Activity) view.getContext()).isDestroyed()) {
                    ((Activity) view.getContext()).getWindow().setStatusBarColor(context.getResources().getColor(R.color.black_0E_60));
                    super.showAtLocation(view, i, i2, i3);
                }
            }

            @Override
            public void dismiss() {
                if (getContentView().getContext() instanceof Activity) {
                    Activity activity = (Activity) getContentView().getContext();
                    if (activity.isDestroyed()) {
                        return;
                    }
                    activity.getWindow().setStatusBarColor(context.getResources().getColor(R.color.transparent));
                    super.dismiss();
                }
            }
        };
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.black_0E_60)));
        ((ImageView) inflate.findViewById(R.id.iv_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                CloseClickListener closeClickListener2 = closeClickListener;
                if (closeClickListener2 != null) {
                    closeClickListener2.onClose();
                }
            }
        });
        return popupWindow;
    }

    public static PopupWindow showFailPop(final Context context, final int i, final BleRepoDevice bleRepoDevice, final EquipmentFailListener equipmentFailListener) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.pop_fail_equipment, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.tv_content)).setText(String.format(context.getResources().getString(R.string.unable_connect_ledger), bleRepoDevice.getName()));
        final PopupWindow popupWindow = new PopupWindow(inflate, -1, -1) {
            @Override
            public void showAtLocation(View view, int i2, int i3, int i4) {
                Context context2 = context;
                if ((context2 instanceof Activity) && !((Activity) context2).isDestroyed()) {
                    ((Activity) view.getContext()).getWindow().setStatusBarColor(context.getResources().getColor(R.color.black_0E_60));
                    super.showAtLocation(view, i2, i3, i4);
                }
            }

            @Override
            public void dismiss() {
                Context context2 = context;
                if (context2 instanceof Activity) {
                    Activity activity = (Activity) context2;
                    if (activity.isDestroyed()) {
                        return;
                    }
                    activity.getWindow().setStatusBarColor(context.getResources().getColor(R.color.transparent));
                    super.dismiss();
                }
            }
        };
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.black_0E_60)));
        ((Button) inflate.findViewById(R.id.retry)).setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                PopupWindowUtil.lambda$showFailPop$11(EquipmentFailListener.this, i, bleRepoDevice, popupWindow, view);
            }
        });
        ((ImageView) inflate.findViewById(R.id.iv_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        return popupWindow;
    }

    public static void lambda$showFailPop$11(EquipmentFailListener equipmentFailListener, int i, BleRepoDevice bleRepoDevice, PopupWindow popupWindow, View view) {
        equipmentFailListener.retry(i, bleRepoDevice);
        popupWindow.dismiss();
    }

    public static PopupWindow showPopupText(Context context, CharSequence charSequence, View view, boolean z) {
        int height;
        int i;
        int abs;
        View inflate = LayoutInflater.from(context).inflate(R.layout.popup_text, (ViewGroup) null);
        boolean z2 = true;
        PopupWindow popupWindow = new PopupWindow(inflate, -2, -2, true);
        popupWindow.setOutsideTouchable(true);
        TextView textView = (TextView) popupWindow.getContentView().findViewById(R.id.tv_content);
        textView.setText(charSequence);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        ImageView imageView = (ImageView) popupWindow.getContentView().findViewById(R.id.iv_top_arrow);
        ImageView imageView2 = (ImageView) popupWindow.getContentView().findViewById(R.id.iv_bottom_arrow);
        inflate.measure(0, 0);
        int measuredHeight = inflate.getMeasuredHeight();
        int measuredWidth = inflate.getMeasuredWidth();
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i2 = 20;
        if (z) {
            if (iArr[1] + measuredHeight + UIUtils.dip2px(15.0f) > UIUtils.getScreenHeight(context)) {
                height = view.getHeight();
                i2 = -(height + measuredHeight + 20);
            }
            z2 = false;
        } else {
            if ((iArr[1] - measuredHeight) - UIUtils.dip2px(90.0f) >= 0) {
                height = view.getHeight();
                i2 = -(height + measuredHeight + 20);
            }
            z2 = false;
        }
        int measuredWidth2 = view.getMeasuredWidth();
        if (measuredWidth2 >= measuredWidth) {
            i = (measuredWidth2 - measuredWidth) / 2;
        } else {
            int i3 = (measuredWidth - measuredWidth2) / 2;
            int i4 = iArr[0];
            if (i3 > i4) {
                i3 = i4 - UIUtils.dip2px(15.0f);
            } else if ((i4 - i3) + measuredWidth + UIUtils.dip2px(15.0f) > UIUtils.getScreenWidth(context)) {
                i3 += (((iArr[0] - i3) + measuredWidth) + UIUtils.dip2px(15.0f)) - UIUtils.getScreenWidth(context);
            }
            i = -i3;
        }
        if (i > 0) {
            abs = (measuredWidth - imageView2.getMeasuredWidth()) / 2;
        } else {
            abs = (Math.abs(i) + (measuredWidth2 / 2)) - (imageView2.getMeasuredWidth() / 2);
        }
        if (z2) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView2.getLayoutParams();
            layoutParams.leftMargin = abs;
            imageView2.setLayoutParams(layoutParams);
            imageView.setVisibility(View.GONE);
        } else {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) imageView.getLayoutParams();
            layoutParams2.leftMargin = abs;
            imageView.setLayoutParams(layoutParams2);
            imageView2.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
        }
        popupWindow.showAsDropDown(view, i, i2);
        return popupWindow;
    }

    public static PopupWindow showPopupTextCenter(Context context, CharSequence charSequence, View view) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.popup_text, (ViewGroup) null, false);
        PopupWindow popupWindow = new PopupWindow(inflate, -2, -2, true);
        popupWindow.setOutsideTouchable(true);
        ((TextView) popupWindow.getContentView().findViewById(R.id.tv_content)).setText(charSequence);
        inflate.measure(0, 0);
        inflate.getMeasuredHeight();
        int measuredWidth = inflate.getMeasuredWidth();
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        popupWindow.showAsDropDown(view, iArr[0] - measuredWidth, UIUtils.dip2px(5.0f));
        return popupWindow;
    }

    public static BasePopupView showScamAddressPopWindow(final Context context, final String str, final ScamAddressPopWindowCallback scamAddressPopWindowCallback) {
        BasePopupView asCustom = new XPopup.Builder(context).maxWidth(XPopupUtils.getScreenWidth(context)).dismissOnTouchOutside(false).popupAnimation(PopupAnimation.NoAnimation).asCustom(new CenterPopupView(context) {
            @Override
            public int getImplLayoutId() {
                return R.layout.scam_address_warning_popwindow;
            }

            @Override
            public void onCreate() {
                super.onCreate();
                View findViewById = findViewById(R.id.address_view);
                PopupWindowUtil.setAddress(context, (EllipsizedTextView) findViewById(R.id.tv_address), str, findViewById);
                ((Button) findViewById(R.id.btn_continue)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        scamAddressPopWindowCallback.continueDo();
                        dismiss();
                    }
                });
                ((Button) findViewById(R.id.btn_cancel)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        scamAddressPopWindowCallback.cancel();
                        dismiss();
                    }
                });
                ((TextView) findViewById(R.id.tv_tronscan)).setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        CommonWebActivityV3.start(context, IRequest.getAccountUrl(str), "", -2, false);
                        dismiss();
                    }
                });
            }
        });
        asCustom.show();
        return asCustom;
    }

    public static void setAddress(final Context context, EllipsizedTextView ellipsizedTextView, final String str, View view) {
        if (StringTronUtil.isNullOrEmpty(str)) {
            return;
        }
        ellipsizedTextView.setText(str);
        view.setVisibility(View.VISIBLE);
        view.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view2) {
                UIUtils.copy(str);
                ToastUtil.getInstance().show(context, R.string.copy_susscess);
            }
        });
    }

    public static PopupWindow showMultiImport(final Context context, final MultiImportListener multiImportListener) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.pop_multi_import, (ViewGroup) null);
        View findViewById = inflate.findViewById(R.id.bt_know);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_url);
        String str = " " + context.getString(R.string.pop_import_detail_8);
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new ClickUrlSpan(context, IRequest.getImportPopMultiUrl()), 0, str.length(), 33);
        textView.append(spannableString);
        textView.setMovementMethod(new LinkMovementMethod() {
        });
        findViewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                PopupWindowUtil.lambda$showMultiImport$13(MultiImportListener.this, view);
            }
        });
        PopupWindow popupWindow = new PopupWindow(inflate, -1, -1) {
            @Override
            public void showAtLocation(View view, int i, int i2, int i3) {
                Context context2 = context;
                if ((context2 instanceof Activity) && !((Activity) context2).isDestroyed()) {
                    ((Activity) context).getWindow().setStatusBarColor(context.getResources().getColor(R.color.gray_232c41_60));
                    super.showAtLocation(view, i, i2, i3);
                }
            }

            @Override
            public void dismiss() {
                Context context2 = context;
                if (context2 instanceof Activity) {
                    Activity activity = (Activity) context2;
                    MultiImportListener multiImportListener2 = multiImportListener;
                    if (multiImportListener2 != null) {
                        multiImportListener2.dismiss();
                    }
                    if (activity.isDestroyed()) {
                        return;
                    }
                    activity.getWindow().setStatusBarColor(context.getResources().getColor(R.color.transparent));
                    super.dismiss();
                }
            }
        };
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.gray_232c41_60)));
        return popupWindow;
    }

    public static void lambda$showMultiImport$13(MultiImportListener multiImportListener, View view) {
        if (multiImportListener != null) {
            multiImportListener.success();
        }
    }

    public static BasePopupView jumpSecurityPopWindow(final Context context, final PopWindowCallback popWindowCallback) {
        BasePopupView asCustom = new XPopup.Builder(context).maxWidth(XPopupUtils.getScreenWidth(context)).dismissOnTouchOutside(false).popupAnimation(PopupAnimation.NoAnimation).asCustom(new CenterPopupView(context) {
            @Override
            public int getImplLayoutId() {
                return R.layout.jump_security_popup_view;
            }

            @Override
            public void onCreate() {
                super.onCreate();
                final TextView textView = (TextView) findViewById(R.id.btn_continue);
                View findViewById = findViewById(R.id.learn_layout);
                final ImageView imageView = (ImageView) findViewById(R.id.iv_learn);
                String string = context.getResources().getString(R.string.jump_security_popwindow_title);
                String string2 = context.getResources().getString(R.string.jump_security_popwindow_info);
                String string3 = context.getResources().getString(R.string.jump_security_pop_window_confirm);
                findViewById.setVisibility(View.VISIBLE);
                ((TextView) findViewById(R.id.title)).setText(string);
                ((TextView) findViewById(R.id.info)).setText(string2);
                textView.setText(string3);
                findViewById.setSelected(false);
                findViewById.setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        boolean z = !view.isSelected();
                        view.setSelected(z);
                        textView.setEnabled(z);
                        imageView.setImageResource(z ? R.mipmap.create_icon_learn_selected : R.mipmap.create_icon_learn_unselect);
                    }
                });
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popWindowCallback.continueDo();
                        dismiss();
                    }
                });
                ((TextView) findViewById(R.id.btn_cancel)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popWindowCallback.cancel();
                        dismiss();
                    }
                });
            }
        });
        asCustom.show();
        return asCustom;
    }

    public static PopupWindow showRootRisk(final Context context, final PopWindowSimpleButtonListener popWindowSimpleButtonListener) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.pop_root_risk, (ViewGroup) null);
        inflate.findViewById(R.id.bt_know).setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                PopWindowSimpleButtonListener.this.dismiss();
            }
        });
        PopupWindow popupWindow = new PopupWindow(inflate, -1, -1) {
            @Override
            public void showAtLocation(View view, int i, int i2, int i3) {
                Context context2 = context;
                if ((context2 instanceof Activity) && !((Activity) context2).isDestroyed()) {
                    ((Activity) context).getWindow().setStatusBarColor(context.getResources().getColor(R.color.gray_232c41_60));
                    super.showAtLocation(view, i, i2, i3);
                }
            }

            @Override
            public void dismiss() {
                Context context2 = context;
                if (context2 instanceof Activity) {
                    Activity activity = (Activity) context2;
                    if (activity.isDestroyed()) {
                        return;
                    }
                    activity.getWindow().setStatusBarColor(context.getResources().getColor(R.color.transparent));
                    super.dismiss();
                }
            }
        };
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.gray_232c41_60)));
        return popupWindow;
    }
}
