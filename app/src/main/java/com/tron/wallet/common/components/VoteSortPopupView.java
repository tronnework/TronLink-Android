package com.tron.wallet.common.components;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BottomPopupView;
import com.tronlinkpro.wallet.R;
public class VoteSortPopupView extends BottomPopupView {
    private static boolean isShow = false;
    private final int checkedIndex;
    private final boolean filterToggleOn;
    private RadioGroup group;
    private ImageView ivToggle;
    private OnSelectChangedListener listener;
    private final long myVoteCount;

    public interface OnSelectChangedListener {
        void onSelectChanged(boolean z, int i);
    }

    public enum SortIndex {
        MY_VOTES,
        APR,
        VOTES_COUNT
    }

    private int parseIdFromIndex(int i) {
        return i == 1 ? R.id.rb_apr : i == 2 ? R.id.rb_voted_count : R.id.rb_my_vote;
    }

    private int parseSortTypeByIndex(int i) {
        if (i == 1) {
            return 6;
        }
        return i == 2 ? 7 : 5;
    }

    @Override
    public int getImplLayoutId() {
        return R.layout.popup_vote_sort_new;
    }

    void setOnSortChangedListener(OnSelectChangedListener onSelectChangedListener) {
        this.listener = onSelectChangedListener;
    }

    public static void showUp(Context context, int i, boolean z, long j, OnSelectChangedListener onSelectChangedListener) {
        if (isShow) {
            return;
        }
        isShow = true;
        VoteSortPopupView voteSortPopupView = new VoteSortPopupView(context, z, i, j);
        voteSortPopupView.setOnSortChangedListener(onSelectChangedListener);
        new XPopup.Builder(context).asCustom(voteSortPopupView).show();
    }

    @Override
    public void onShow() {
        super.onShow();
    }

    @Override
    public void onDismiss() {
        super.onDismiss();
        isShow = false;
    }

    public VoteSortPopupView(Context context, boolean z, int i, long j) {
        super(context);
        this.filterToggleOn = z;
        this.checkedIndex = parseIndexByType(i);
        this.myVoteCount = j;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.group_sort_item);
        this.group = radioGroup;
        int i = this.checkedIndex;
        if (i >= 0) {
            radioGroup.check(parseIdFromIndex(i));
        }
        this.group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public final void onCheckedChanged(RadioGroup radioGroup2, int i2) {
                lambda$onCreate$0(radioGroup2, i2);
            }
        });
        int i2 = this.myVoteCount > 0 ? 0 : 8;
        ImageView imageView = (ImageView) findViewById(R.id.iv_toggle);
        this.ivToggle = imageView;
        imageView.setVisibility(i2);
        findViewById(R.id.tv_my_voted).setVisibility(i2);
        this.ivToggle.setSelected(this.filterToggleOn);
        this.ivToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$onCreate$1(view);
            }
        });
    }

    public void lambda$onCreate$0(RadioGroup radioGroup, int i) {
        if (this.listener != null) {
            int parseSelectIndexById = parseSelectIndexById(i);
            ImageView imageView = this.ivToggle;
            this.listener.onSelectChanged(imageView != null && imageView.isSelected(), parseSortTypeByIndex(parseSelectIndexById));
        }
        dismiss();
    }

    public void lambda$onCreate$1(View view) {
        view.setSelected(!view.isSelected());
        if (this.listener != null) {
            this.listener.onSelectChanged(view.isSelected(), parseSortTypeByIndex(parseSelectIndexById(this.group.getCheckedRadioButtonId())));
        }
        dismiss();
    }

    private int parseSelectIndexById(int i) {
        if (i == R.id.rb_my_vote) {
            return SortIndex.MY_VOTES.ordinal();
        }
        if (i == R.id.rb_apr) {
            return SortIndex.APR.ordinal();
        }
        if (i == R.id.rb_voted_count) {
            return SortIndex.VOTES_COUNT.ordinal();
        }
        return 0;
    }

    private int parseIndexByType(int i) {
        if (i == 7) {
            return SortIndex.VOTES_COUNT.ordinal();
        }
        if (i == 6) {
            return SortIndex.APR.ordinal();
        }
        return SortIndex.MY_VOTES.ordinal();
    }
}
