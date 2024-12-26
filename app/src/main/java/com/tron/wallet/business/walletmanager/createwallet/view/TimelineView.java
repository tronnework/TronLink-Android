package com.tron.wallet.business.walletmanager.createwallet.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tronlinkpro.wallet.R;
public class TimelineView extends RelativeLayout {
    public static final int BEFORE_STATUS = 0;
    public static final int FINISHED_STATUS = 2;
    public static final int FINISHING_STATUS = 1;
    View ivLineOne;
    View ivLineTwo;
    ImageView ivStepOne;
    ImageView ivStepThree;
    ImageView ivStepTwo;
    View root;
    TextView tvStepOne;
    TextView tvStepThree;
    TextView tvStepTwo;

    private void mappingId(View view) {
        this.ivLineOne = view.findViewById(R.id.step_line_one);
        this.ivLineTwo = view.findViewById(R.id.step_line_two);
        this.ivStepOne = (ImageView) view.findViewById(R.id.step_one);
        this.ivStepTwo = (ImageView) view.findViewById(R.id.step_two);
        this.ivStepThree = (ImageView) view.findViewById(R.id.step_three);
        this.tvStepOne = (TextView) view.findViewById(R.id.step_text_one);
        this.tvStepTwo = (TextView) view.findViewById(R.id.step_text_two);
        this.tvStepThree = (TextView) view.findViewById(R.id.step_text_three);
        this.root = view.findViewById(R.id.root);
    }

    public TimelineView(Context context) {
        super(context);
        addTimeline(context);
    }

    public TimelineView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        addTimeline(context);
    }

    public TimelineView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        addTimeline(context);
    }

    private void addTimeline(Context context) {
        View inflate = View.inflate(context, R.layout.time_line_view, null);
        addView(inflate);
        mappingId(inflate);
    }

    public void setStepOne() {
        this.ivLineOne.setBackgroundColor(getResources().getColor(R.color.gray_9B));
        this.ivLineTwo.setBackgroundColor(getResources().getColor(R.color.gray_9B));
        this.ivStepOne.setBackgroundResource(R.mipmap.step_one_ing);
        this.ivStepTwo.setBackgroundResource(R.mipmap.step_two_un_finish);
        this.ivStepThree.setBackgroundResource(R.mipmap.step_three_un_finish);
        this.tvStepOne.setTextColor(getResources().getColor(R.color.black_23));
        this.tvStepTwo.setTextColor(getResources().getColor(R.color.gray_9B));
        this.tvStepThree.setTextColor(getResources().getColor(R.color.gray_9B));
    }

    public void setStepTwo() {
        this.ivLineOne.setBackgroundColor(getResources().getColor(R.color.black_23));
        this.ivLineTwo.setBackgroundColor(getResources().getColor(R.color.gray_9B));
        this.ivStepOne.setBackgroundResource(R.mipmap.step_one_finish);
        this.ivStepTwo.setBackgroundResource(R.mipmap.step_two_ing);
        this.ivStepThree.setBackgroundResource(R.mipmap.step_three_un_finish);
        this.tvStepOne.setTextColor(getResources().getColor(R.color.black_23));
        this.tvStepTwo.setTextColor(getResources().getColor(R.color.black_23));
        this.tvStepThree.setTextColor(getResources().getColor(R.color.gray_9B));
    }

    public void setStepTwoFinished() {
        this.ivLineOne.setBackgroundColor(getResources().getColor(R.color.black_23));
        this.ivLineTwo.setBackgroundColor(getResources().getColor(R.color.gray_9B));
        this.ivStepOne.setBackgroundResource(R.mipmap.step_one_finish);
        this.ivStepTwo.setBackgroundResource(R.mipmap.step_two_finish);
        this.ivStepThree.setBackgroundResource(R.mipmap.step_three_un_finish);
        this.tvStepOne.setTextColor(getResources().getColor(R.color.black_23));
        this.tvStepTwo.setTextColor(getResources().getColor(R.color.black_23));
        this.tvStepThree.setTextColor(getResources().getColor(R.color.gray_9B));
    }

    public void setWhiteBg() {
        this.root.setBackgroundResource(R.color.white);
    }
}
