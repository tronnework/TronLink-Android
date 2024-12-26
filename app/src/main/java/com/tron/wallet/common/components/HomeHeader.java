package com.tron.wallet.common.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.wallet.databinding.ItemAssetsHBinding;
import com.tronlinkpro.wallet.R;
public class HomeHeader extends FrameLayout implements View.OnClickListener {
    ItemAssetsHBinding binding;
    RelativeLayout center;
    TextView centerText;
    private int gray_99;
    private int index;
    RelativeLayout left;
    TextView leftText;
    View line1;
    View line2;
    View line3;
    OnHeaderClick onHeaderClick;
    private int red_c5;
    RelativeLayout right;
    TextView rightText;

    public interface OnHeaderClick {
        void selectIndex(int i);
    }

    public int getIndex() {
        return this.index;
    }

    public void setOnHeaderClick(OnHeaderClick onHeaderClick) {
        this.onHeaderClick = onHeaderClick;
    }

    void left() {
        selectIndex(0);
        OnHeaderClick onHeaderClick = this.onHeaderClick;
        if (onHeaderClick != null) {
            onHeaderClick.selectIndex(0);
        }
    }

    void right() {
        selectIndex(2);
        OnHeaderClick onHeaderClick = this.onHeaderClick;
        if (onHeaderClick != null) {
            onHeaderClick.selectIndex(2);
        }
    }

    void center() {
        selectIndex(1);
        OnHeaderClick onHeaderClick = this.onHeaderClick;
        if (onHeaderClick != null) {
            onHeaderClick.selectIndex(1);
        }
    }

    public HomeHeader(Context context) {
        this(context, null);
    }

    public HomeHeader(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HomeHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.index = 0;
        init();
    }

    private void init() {
        this.binding = ItemAssetsHBinding.bind(LayoutInflater.from(getContext()).inflate(R.layout.item_assets_h, (ViewGroup) this, true));
        mappingId();
        initClick();
        this.red_c5 = getContext().getResources().getColor(R.color.blue_72);
        this.gray_99 = getContext().getResources().getColor(R.color.gray_99);
    }

    private void initClick() {
        this.binding.left.setOnClickListener(this);
        this.binding.right.setOnClickListener(this);
        this.binding.center.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.center) {
            center();
        } else if (id == R.id.left) {
            left();
        } else if (id != R.id.right) {
        } else {
            right();
        }
    }

    private void mappingId() {
        this.left = this.binding.left;
        this.right = this.binding.right;
        this.leftText = this.binding.leftText;
        this.rightText = this.binding.rightText;
        this.line1 = this.binding.line1;
        this.line2 = this.binding.line2;
        this.centerText = this.binding.centerText;
        this.center = this.binding.center;
        this.line3 = this.binding.line3;
    }

    public void selectIndex(int i) {
        if (i == 0) {
            this.leftText.setTextColor(this.red_c5);
            this.centerText.setTextColor(this.gray_99);
            this.rightText.setTextColor(this.gray_99);
            this.line1.setVisibility(View.VISIBLE);
            this.line2.setVisibility(View.GONE);
            this.line3.setVisibility(View.GONE);
        } else if (i == 1) {
            this.centerText.setTextColor(this.red_c5);
            this.leftText.setTextColor(this.gray_99);
            this.rightText.setTextColor(this.gray_99);
            this.line2.setVisibility(View.VISIBLE);
            this.line1.setVisibility(View.GONE);
            this.line3.setVisibility(View.GONE);
        } else if (i != 2) {
        } else {
            this.rightText.setTextColor(this.red_c5);
            this.leftText.setTextColor(this.gray_99);
            this.centerText.setTextColor(this.gray_99);
            this.line3.setVisibility(View.VISIBLE);
            this.line1.setVisibility(View.GONE);
            this.line2.setVisibility(View.GONE);
        }
    }
}
