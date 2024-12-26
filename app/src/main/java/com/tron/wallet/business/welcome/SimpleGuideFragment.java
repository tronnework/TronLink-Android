package com.tron.wallet.business.welcome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.databinding.FgGuideBinding;
import com.tronlinkpro.wallet.R;
public class SimpleGuideFragment extends BaseFragment<EmptyPresenter, EmptyModel> {
    private FgGuideBinding binding;
    private int image1;
    private boolean isCold;
    ImageView ivImage;
    private int title1;
    private int title2;
    private int title3;
    TextView tvInnerTitle;
    TextView tvTitle;

    public void setRes(int i, int i2, int i3, int i4) {
        this.image1 = i;
        this.title1 = i2;
        this.title2 = i3;
        this.title3 = i4;
    }

    public static SimpleGuideFragment getInstance() {
        return new SimpleGuideFragment();
    }

    @Override
    protected void processData() {
        this.ivImage.setImageResource(this.image1);
        try {
            int i = this.title3;
            if (i > 0) {
                this.tvTitle.setText(i);
            } else {
                this.tvTitle.setText(String.valueOf(i));
            }
        } catch (Exception e) {
            SentryUtil.captureException(e);
            LogUtils.e(e);
        }
        try {
            int i2 = this.title2;
            if (i2 > 0) {
                this.tvInnerTitle.setText(i2);
            } else {
                this.tvInnerTitle.setText(String.valueOf(i2));
            }
        } catch (Exception e2) {
            SentryUtil.captureException(e2);
            LogUtils.e(e2);
        }
        try {
            if (this.isCold) {
                this.tvTitle.setTextColor(getResources().getColor(R.color.white));
            } else {
                this.tvTitle.setTextColor(getResources().getColor(R.color.black_23));
            }
        } catch (Exception e3) {
            SentryUtil.captureException(e3);
            LogUtils.e(e3);
        }
    }

    public void setCold(boolean z) {
        this.isCold = z;
        updateTitleColor();
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setType(0);
        FgGuideBinding inflate = FgGuideBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId() {
        this.ivImage = this.binding.image;
        this.tvTitle = this.binding.tvTitle;
        this.tvInnerTitle = this.binding.tvInnerTitle;
    }

    @Override
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void updateTitleColor() {
        TextView textView;
        if (!isAdded() || isDetached() || (textView = this.tvTitle) == null) {
            return;
        }
        if (this.isCold) {
            textView.setTextColor(getResources().getColor(R.color.white));
        } else {
            textView.setTextColor(getResources().getColor(R.color.black_23));
        }
    }
}
