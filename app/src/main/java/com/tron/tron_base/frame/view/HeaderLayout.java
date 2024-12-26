package com.tron.tron_base.frame.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.tron.tron_base.R;
import com.tron.tron_base.databinding.HeaderBarBinding;
import com.tron.tron_base.databinding.ProgressPageBinding;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.tron_base.frame.interfaces.OnHeaderClickListener;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.StringTronUtil;
public class HeaderLayout extends RelativeLayout {
    private static final int TYPE_BLUE_HEADER = 6;
    private static final int TYPE_HEADER = 1;
    private static final int TYPE_HEADER_PROGRESS = 3;
    private static final int TYPE_NOHEADER_STATUSBAR = 4;
    private static final int TYPE_NOHEADER_STATUSBAR_PROGRESS = 5;
    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_PROGRESS = 2;
    private View headerBar;
    private HeaderHolder headerHolder;
    private LayoutInflater layoutInflater;
    private OnHeaderClickListener mListener;
    private View.OnClickListener onClicker;
    private ProgressHolder progressHolder;
    private View progressPageView;
    private int statusBarHeight;

    public View getHeadBar() {
        return this.headerBar;
    }

    public HeaderHolder getHeaderHolder() {
        return this.headerHolder;
    }

    public void setHeaderClickListener(OnHeaderClickListener onHeaderClickListener) {
        this.mListener = onHeaderClickListener;
    }

    public HeaderLayout(Context context, View view, int i, int i2, int i3) {
        super(context);
        this.onClicker = new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                if (view2.getId() == R.id.root_progress_page && progressHolder.rlNodata.getVisibility() == 0 && progressHolder.rlReload.getVisibility() == 0) {
                    progressHolder.tvReload.setVisibility(View.GONE);
                    progressHolder.btLoadding.setVisibility(View.VISIBLE);
                    HeaderLayout headerLayout = HeaderLayout.this;
                    headerLayout.startAnima(headerLayout.progressHolder.btLoadding);
                    mListener.onClickReLoadButton();
                }
            }
        };
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.statusBarHeight = getStatusBarHeight(context);
        setHeaderLayout(context, view, i, i2, i3);
    }

    public HeaderLayout(Context context, LayoutInflater layoutInflater, View view, int i) {
        super(context);
        this.onClicker = new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                if (view2.getId() == R.id.root_progress_page && progressHolder.rlNodata.getVisibility() == 0 && progressHolder.rlReload.getVisibility() == 0) {
                    progressHolder.tvReload.setVisibility(View.GONE);
                    progressHolder.btLoadding.setVisibility(View.VISIBLE);
                    HeaderLayout headerLayout = HeaderLayout.this;
                    headerLayout.startAnima(headerLayout.progressHolder.btLoadding);
                    mListener.onClickReLoadButton();
                }
            }
        };
        this.layoutInflater = layoutInflater;
        this.statusBarHeight = getStatusBarHeight(context);
        setHeaderLayout(context, view, i, 0, ViewCompat.MEASURED_SIZE_MASK);
    }

    public static int getStatusBarHeight(Context context) {
        if (context != null) {
            return context.getResources().getDimensionPixelSize(context.getResources().getIdentifier("status_bar_height", "dimen", "android"));
        }
        return 0;
    }

    private void setHeaderLayout(Context context, View view, int i, int i2, int i3) {
        HeaderHolder headerHolder;
        addBackground(i2, i3);
        switch (i) {
            case 1:
                addHeaderBar();
                break;
            case 2:
                addProgressPage();
                break;
            case 3:
                addHeaderBar();
                addProgressPage();
                break;
            case 4:
                addHeaderBar();
                this.headerHolder.backview.setVisibility(View.GONE);
                break;
            case 5:
                addHeaderBar();
                this.headerHolder.backview.setVisibility(View.GONE);
                addProgressPage();
                break;
            case 6:
                addHeaderBar();
                this.headerHolder.statusbar.setVisibility(View.INVISIBLE);
                this.headerHolder.view.setBackgroundResource(R.mipmap.header_top_bg);
                this.headerHolder.ivCommonLeft.setBackgroundResource(R.mipmap.ic_left_white);
                this.headerHolder.tvCommonTitle.setTextColor(getResources().getColor(R.color.white));
                break;
        }
        if (i2 != 0 && (headerHolder = this.headerHolder) != null && headerHolder.rootView != null) {
            this.headerHolder.rootView.setBackground(getResources().getDrawable(R.color.transparent));
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        if (this.progressPageView != null) {
            layoutParams.addRule(3, R.id.root_progress_page);
        } else {
            layoutParams.addRule(3, R.id.root_header_bar);
        }
        addView(view, layoutParams);
    }

    protected void addHeaderBar() {
        this.headerBar = this.layoutInflater.inflate(R.layout.header_bar, (ViewGroup) null);
        this.headerHolder = new HeaderHolder(this.headerBar);
        addView(this.headerBar, new RelativeLayout.LayoutParams(-1, -2));
    }

    private void addBackground(int i, int i2) {
        if (i <= 0) {
            return;
        }
        setBackgroundColor(i2);
        ImageView imageView = new ImageView(getContext());
        imageView.setBackground(getResources().getDrawable(i));
        addView(imageView, new RelativeLayout.LayoutParams(-1, (int) ((getResources().getDisplayMetrics().density * 540.0f) + 0.5f)));
    }

    protected void addProgressPage() {
        this.progressPageView = this.layoutInflater.inflate(R.layout.progress_page, (ViewGroup) null);
        this.progressHolder = new ProgressHolder(this.progressPageView);
        this.progressPageView.setOnClickListener(this.onClicker);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(3, R.id.root_header_bar);
        addView(this.progressPageView, layoutParams);
        showLoadingPage();
    }

    public void lambda$showOrHideHeader$0() {
        this.headerHolder.backview.setVisibility(View.VISIBLE);
    }

    public void showOrHideHeader(boolean z) {
        HeaderHolder headerHolder = this.headerHolder;
        if (headerHolder == null) {
            return;
        }
        if (z) {
            headerHolder.backview.post(new Runnable() {
                @Override
                public final void run() {
                    lambda$showOrHideHeader$0();
                }
            });
        } else {
            headerHolder.backview.post(new Runnable() {
                @Override
                public final void run() {
                    lambda$showOrHideHeader$1();
                }
            });
        }
    }

    public void lambda$showOrHideHeader$1() {
        this.headerHolder.backview.setVisibility(View.GONE);
    }

    public void setHeaderBar(String str) {
        this.headerHolder.ivCommonLeft.setVisibility(View.VISIBLE);
        this.headerHolder.tvCommonTitle.setVisibility(View.VISIBLE);
        this.headerHolder.tvCommonRight.setVisibility(View.GONE);
        this.headerHolder.tvCommonTitle.setText(str);
    }

    public void setHeaderBar(String str, String str2, String str3) {
        this.headerHolder.ivCommonLeft.setVisibility(View.VISIBLE);
        this.headerHolder.tvCommonTitle.setVisibility(View.VISIBLE);
        this.headerHolder.tvCommonRight.setVisibility(View.VISIBLE);
        this.headerHolder.tvCommonRight.setText(str3);
        this.headerHolder.tvCommonTitle.setText(str2);
    }

    public void setHeaderBarSpe(String str, int i) {
        this.headerHolder.ivCommonLeft.setVisibility(View.VISIBLE);
        this.headerHolder.tvCommonTitle.setVisibility(View.VISIBLE);
        this.headerHolder.tvCommonRight.setVisibility(View.VISIBLE);
        this.headerHolder.ivQr.setVisibility(View.VISIBLE);
        this.headerHolder.tvCommonRight.setVisibility(View.GONE);
        if (i != 0) {
            this.headerHolder.ivQr.setBackground(null);
            this.headerHolder.ivQr.setImageResource(i);
        }
        this.headerHolder.tvCommonTitle.setText(str);
    }

    public void setHeaderBar(String str, int i) {
        this.headerHolder.rlRight.setVisibility(View.VISIBLE);
        this.headerHolder.ivCommonLeft.setVisibility(View.VISIBLE);
        this.headerHolder.tvCommonTitle.setVisibility(View.VISIBLE);
        this.headerHolder.tvCommonRight.setVisibility(View.VISIBLE);
        this.headerHolder.ivQr.setVisibility(View.VISIBLE);
        this.headerHolder.tvCommonRight.setVisibility(View.GONE);
        if (i != 0) {
            this.headerHolder.ivQr.setBackground(null);
            this.headerHolder.ivQr.setImageResource(i);
        }
        this.headerHolder.tvCommonTitle.setText(str);
    }

    public void setRightBar(int i) {
        this.headerHolder.rlIconRight.setVisibility(View.VISIBLE);
        if (i != 0) {
            this.headerHolder.ivRefresh.setBackground(null);
            this.headerHolder.ivRefresh.setImageResource(i);
        }
        expandViewTouchDelegate(this.headerHolder.rlIconRight, dip2px(15), dip2px(10), 0, dip2px(10));
    }

    public void setRightBarFavorite(int i) {
        this.headerHolder.rlFavorite.setVisibility(View.VISIBLE);
        if (i != 0) {
            this.headerHolder.ivFavorite.setBackground(null);
            this.headerHolder.ivFavorite.setImageResource(i);
        }
        expandViewTouchDelegate(this.headerHolder.rlFavorite, dip2px(15), dip2px(10), 0, dip2px(10));
    }

    public void setHeaderBarAndRightTv(String str, String str2) {
        this.headerHolder.ivCommonLeft.setVisibility(View.VISIBLE);
        this.headerHolder.tvCommonTitle.setVisibility(View.VISIBLE);
        this.headerHolder.tvCommonRight.setVisibility(View.GONE);
        this.headerHolder.ivQr.setVisibility(View.GONE);
        if (!StringTronUtil.isEmpty(str2)) {
            this.headerHolder.tvBgRight.setVisibility(View.VISIBLE);
            this.headerHolder.tvBgRight.setText(str2);
        }
        this.headerHolder.tvCommonTitle.setText(str);
    }

    public void setRightTextShow(boolean z) {
        this.headerHolder.tvBgRight.setVisibility(z ? View.VISIBLE : View.GONE);
    }

    public void setLeftBar(int i) {
        this.headerHolder.llClose.setVisibility(View.VISIBLE);
        this.headerHolder.ivClose.setVisibility(View.VISIBLE);
        if (i != 0) {
            this.headerHolder.ivClose.setBackground(null);
            this.headerHolder.ivClose.setImageResource(i);
        }
    }

    public void setHeaderBar(String str, String str2) {
        this.headerHolder.ivCommonLeft.setVisibility(View.VISIBLE);
        this.headerHolder.tvCommonTitle.setVisibility(View.VISIBLE);
        this.headerHolder.tvCommonRight.setVisibility(View.VISIBLE);
        this.headerHolder.ivQr.setVisibility(View.GONE);
        this.headerHolder.tvCommonRight.setVisibility(View.VISIBLE);
        if (!StringTronUtil.isEmpty(str2)) {
            this.headerHolder.tvCommonRight.setText(str2);
        }
        this.headerHolder.tvCommonTitle.setText(str);
    }

    public void setWhiteHeaderBar(String str, String str2) {
        this.headerHolder.ivCommonLeft.setVisibility(View.VISIBLE);
        this.headerHolder.tvCommonTitle.setVisibility(View.VISIBLE);
        this.headerHolder.tvCommonRight.setVisibility(View.VISIBLE);
        this.headerHolder.ivQr.setVisibility(View.GONE);
        this.headerHolder.tvCommonRight.setVisibility(View.VISIBLE);
        if (!StringTronUtil.isEmpty(str2)) {
            this.headerHolder.tvCommonRight.setText(str2);
        }
        this.headerHolder.tvCommonTitle.setText(str);
    }

    public String getTitle() {
        return this.headerHolder.tvCommonTitle != null ? this.headerHolder.tvCommonTitle.getText().toString() : "";
    }

    public void setTitle(String str) {
        if (this.headerHolder.tvCommonTitle != null) {
            this.headerHolder.tvCommonTitle.setText(str);
        }
    }

    public void setCommonTitle2(String str) {
        if (this.headerHolder.tvCommonTitle2 != null) {
            this.headerHolder.tvCommonTitle2.setVisibility(View.VISIBLE);
            this.headerHolder.tvCommonTitle2.setText(str);
        }
    }

    public void setMiddleTitle(String str) {
        if (this.headerHolder.tvMiddleTitle != null) {
            this.headerHolder.tvMiddleTitle.setVisibility(View.VISIBLE);
            this.headerHolder.tvMiddleTitle.setText(str);
        }
    }

    public void setCommonRightTitle2(String str) {
        if (this.headerHolder.tvCommonRight2 != null) {
            this.headerHolder.rlRightshare.setVisibility(View.VISIBLE);
            this.headerHolder.tvCommonRight2.setVisibility(View.VISIBLE);
            this.headerHolder.tvCommonRight2.setText(str);
            this.headerHolder.ivShare.setVisibility(View.GONE);
        }
    }

    public void setRightBold() {
        this.headerHolder.tvCommonRight.setTypeface(Typeface.SANS_SERIF, 1);
    }

    public void hideHeaderLeftButton() {
        this.headerHolder.ivCommonLeft.setVisibility(View.GONE);
    }

    public void showLoadingPage() {
        showLoadingPage(StringTronUtil.getResString(R.string.loading));
    }

    public void showLoadingPage(String str) {
        View view = this.progressPageView;
        if (view != null) {
            view.setVisibility(View.VISIBLE);
            this.progressHolder.rlLoading.setVisibility(View.VISIBLE);
            this.progressHolder.llDialog.setVisibility(View.VISIBLE);
            this.progressHolder.rlNodata.setVisibility(View.GONE);
            this.progressHolder.loadingView.setVisibility(View.VISIBLE);
            startAnima(this.progressHolder.loadingView);
            this.progressHolder.ivLoaderror.setVisibility(View.GONE);
            this.progressHolder.tvLoading.setText(str);
        }
    }

    public void startAnima(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "rotation", 0.0f, 360.0f);
        ofFloat.setDuration(600L);
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(1);
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.start();
    }

    public void hideLoadingPageDialog() {
        if (this.progressPageView != null) {
            this.progressHolder.llDialog.setVisibility(View.GONE);
        }
    }

    public void showLoadingPageDialog() {
        if (this.progressPageView != null) {
            this.progressHolder.llDialog.setVisibility(View.GONE);
        }
    }

    public void dismissLoadingPage() {
        View view = this.progressPageView;
        if (view != null) {
            view.setVisibility(View.GONE);
        }
    }

    public void showErrorPage() {
        showErrorPage(R.mipmap.ic_no_net, StringTronUtil.getResString(R.string.net_error));
    }

    public void showErrorPage(int i, String str) {
        View view = this.progressPageView;
        if (view != null) {
            view.setVisibility(View.VISIBLE);
            this.progressHolder.rlLoading.setVisibility(View.GONE);
            this.progressHolder.rlNodata.setVisibility(View.VISIBLE);
            this.progressHolder.rlReload.setVisibility(View.VISIBLE);
            this.progressHolder.tvReload.setVisibility(View.VISIBLE);
            this.progressHolder.btLoadding.setVisibility(View.GONE);
            if (i != 0) {
                this.progressHolder.ivNodata.setImageResource(i);
            }
            if (StringTronUtil.isNullOrEmpty(str)) {
                return;
            }
            this.progressHolder.tvMsg.setText(str);
        }
    }

    public void showNoDatasPage() {
        showNoDatasPage(R.mipmap.ic_no_data_new, StringTronUtil.getResString(R.string.nodata));
    }

    public void showNoDatasPage(int i, String str) {
        View view = this.progressPageView;
        if (view != null) {
            view.setVisibility(View.VISIBLE);
            this.progressHolder.rlLoading.setVisibility(View.GONE);
            this.progressHolder.rlNodata.setVisibility(View.VISIBLE);
            this.progressHolder.rlReload.setVisibility(View.GONE);
            if (i != 0) {
                this.progressHolder.ivNodata.setImageResource(i);
            }
            if (StringTronUtil.isNullOrEmpty(str)) {
                return;
            }
            this.progressHolder.tvMsg.setText(str);
        }
    }

    public static class ProgressHolder extends BaseHolder {
        ProgressPageBinding binding;
        ImageView btLoadding;
        ImageView ivLoaderror;
        ImageView ivNodata;
        LinearLayout llDialog;
        ImageView loadingView;
        RelativeLayout rlLoading;
        RelativeLayout rlNodata;
        RelativeLayout rlReload;
        TextView tvLoading;
        TextView tvMsg;
        TextView tvReload;

        ProgressHolder(View view) {
            super(view);
            this.binding = ProgressPageBinding.bind(view);
            mappingId();
        }

        private void mappingId() {
            this.loadingView = this.binding.loadingview;
            this.ivLoaderror = this.binding.ivLoaderror;
            this.tvLoading = this.binding.tvLoading;
            this.llDialog = this.binding.llDialog;
            this.rlLoading = this.binding.rlLoading;
            this.ivNodata = this.binding.ivNodata;
            this.tvMsg = this.binding.tvMsg;
            this.tvReload = this.binding.tvReload;
            this.rlNodata = this.binding.rlNodata;
            this.rlReload = this.binding.rlReload;
            this.btLoadding = this.binding.btLoadding;
        }
    }

    public class HeaderHolder extends BaseHolder implements View.OnClickListener {
        public RelativeLayout backview;
        HeaderBarBinding binding;
        public ImageView ivClose;
        public ImageView ivCommonLeft;
        public ImageView ivCommonTitle2;
        public ImageView ivCommonTitle3;
        public ImageView ivFavorite;
        public ImageView ivQr;
        public ImageView ivRefresh;
        public ImageView ivShare;
        public LinearLayout llClose;
        public LinearLayout llCommonLeft;
        public RelativeLayout rlFavorite;
        public RelativeLayout rlIconRight;
        public RelativeLayout rlRight;
        public RelativeLayout rlRightshare;
        View rootView;
        public LinearLayout statusbar;
        public TextView tvBgRight;
        public TextView tvCommonRight;
        public TextView tvCommonRight2;
        public TextView tvCommonTitle;
        public TextView tvCommonTitle2;
        public TextView tvMiddleTitle;
        public View view;

        @Override
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.ll_common_left) {
                if (mListener != null) {
                    mListener.onClickLeftButton();
                }
            } else if (id == R.id.iv_qr || id == R.id.tv_common_right) {
                if (mListener != null) {
                    mListener.onClickRightButton();
                }
            } else if (id == R.id.ll_close) {
                if (mListener != null) {
                    mListener.onClickCloseButton();
                }
            } else if (id == R.id.rl_icon_right) {
                if (mListener != null) {
                    mListener.onClickRefreshButton();
                }
            } else if (id != R.id.tv_bg_right || mListener == null) {
            } else {
                mListener.onClickRightTv();
            }
        }

        public HeaderHolder(View view) {
            super(view);
            this.view = view;
            this.binding = HeaderBarBinding.bind(view);
            mappingId();
            initClick();
            expandViewTouchDelegate(this.ivQr, HeaderLayout.dip2px(20), HeaderLayout.dip2px(10), HeaderLayout.dip2px(10), HeaderLayout.dip2px(10));
        }

        private void initClick() {
            this.binding.llCommonLeft.setOnClickListener(this);
            this.binding.tvCommonRight.setOnClickListener(this);
            this.binding.ivQr.setOnClickListener(this);
            this.binding.llClose.setOnClickListener(this);
            this.binding.rlIconRight.setOnClickListener(this);
            this.binding.tvBgRight.setOnClickListener(this);
        }

        private void mappingId() {
            this.rootView = this.binding.rootHeaderBar;
            this.ivCommonLeft = this.binding.ivCommonLeft;
            this.llCommonLeft = this.binding.llCommonLeft;
            this.tvCommonTitle = this.binding.tvCommonTitle;
            this.tvCommonRight = this.binding.tvCommonRight;
            this.statusbar = this.binding.statusbar;
            this.ivQr = this.binding.ivQr;
            this.tvMiddleTitle = this.binding.middleTitle;
            this.backview = this.binding.backview;
            this.llClose = this.binding.llClose;
            this.rlIconRight = this.binding.rlIconRight;
            this.ivRefresh = this.binding.ivRefresh;
            this.rlFavorite = this.binding.rlIconFavorite;
            this.ivFavorite = this.binding.ivFavorite;
            this.ivClose = this.binding.ivClose;
            this.tvBgRight = this.binding.tvBgRight;
            this.tvCommonTitle2 = this.binding.tvCommonTitle2;
            this.ivCommonTitle2 = this.binding.ivCommonTitle2;
            this.ivCommonTitle3 = this.binding.ivCommonTitle3;
            this.tvCommonRight2 = this.binding.tvCommonRight2;
            this.ivShare = this.binding.ivShare;
            this.rlRightshare = this.binding.rlRightShare;
            this.rlRight = this.binding.rlRight;
        }
    }

    public void expandViewTouchDelegate(final View view, final int i, final int i2, final int i3, final int i4) {
        ((View) view.getParent()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Rect rect = new Rect();
                view.setEnabled(true);
                view.getHitRect(rect);
                rect.top -= HeaderLayout.dip2px(i2);
                rect.bottom += HeaderLayout.dip2px(i4);
                rect.left -= HeaderLayout.dip2px(i);
                rect.right += HeaderLayout.dip2px(i3);
                TouchDelegate touchDelegate = new TouchDelegate(rect, view);
                if (View.class.isInstance(view.getParent())) {
                    ((View) view.getParent()).setTouchDelegate(touchDelegate);
                }
            }
        }, 1000L);
    }

    public static int dip2px(int i) {
        return (int) ((i * AppContextUtil.getContext().getResources().getDisplayMetrics().density) + 0.5f);
    }
}
