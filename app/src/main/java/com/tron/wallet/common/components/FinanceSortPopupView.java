package com.tron.wallet.common.components;

import android.content.Context;
import android.widget.RadioGroup;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BottomPopupView;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tronlinkpro.wallet.R;
public class FinanceSortPopupView extends BottomPopupView {
    private static boolean isShow = false;
    private final int checkedIndex;
    private RadioGroup group;
    private OnSelectChangedListener listener;

    public interface OnSelectChangedListener {
        void onSelectChanged(int i);
    }

    public enum SortIndex {
        SUGGEST,
        APR,
        VALUE,
        TVL
    }

    private int parseIdFromIndex(int i) {
        return i == 1 ? R.id.rb_apr : i == 2 ? R.id.rb_value : i == 3 ? R.id.rb_tvl : R.id.rb_suggest;
    }

    @Override
    public int getImplLayoutId() {
        return R.layout.popup_finance_sort;
    }

    void setOnSortChangedListener(OnSelectChangedListener onSelectChangedListener) {
        this.listener = onSelectChangedListener;
    }

    public static void showUp(Context context, int i, OnSelectChangedListener onSelectChangedListener) {
        if (isShow) {
            return;
        }
        isShow = true;
        FinanceSortPopupView financeSortPopupView = new FinanceSortPopupView(context, i);
        financeSortPopupView.setOnSortChangedListener(onSelectChangedListener);
        new XPopup.Builder(context).asCustom(financeSortPopupView).show();
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

    public FinanceSortPopupView(Context context, int i) {
        super(context);
        this.checkedIndex = parseIndexByType(i);
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
        AnalyticsHelper.logEvent(AnalyticsHelper.FinancePage.FINANCE_PAGE_SORT_POPUP_SHOW);
    }

    public void lambda$onCreate$0(RadioGroup radioGroup, int i) {
        if (this.listener != null) {
            this.listener.onSelectChanged(parseSelectIndexById(i));
        }
        dismiss();
    }

    private int parseSelectIndexById(int i) {
        if (i == R.id.rb_suggest) {
            AnalyticsHelper.logEvent(AnalyticsHelper.FinancePage.FINANCE_PAGE_SORT_POPUP_RECOMMEND_CLICK);
            return 1;
        } else if (i == R.id.rb_apr) {
            AnalyticsHelper.logEvent(AnalyticsHelper.FinancePage.FINANCE_PAGE_SORT_POPUP_APY_CLICK);
            return 2;
        } else if (i == R.id.rb_value) {
            AnalyticsHelper.logEvent(AnalyticsHelper.FinancePage.FINANCE_PAGE_SORT_POPUP_ASSETS_CLICK);
            return 3;
        } else if (i == R.id.rb_tvl) {
            AnalyticsHelper.logEvent(AnalyticsHelper.FinancePage.FINANCE_PAGE_SORT_POPUP_TVL_CLICK);
            return 4;
        } else {
            return 0;
        }
    }

    private int parseIndexByType(int i) {
        if (i == 4) {
            return SortIndex.TVL.ordinal();
        }
        if (i == 3) {
            return SortIndex.VALUE.ordinal();
        }
        if (i == 2) {
            return SortIndex.APR.ordinal();
        }
        return SortIndex.SUGGEST.ordinal();
    }
}
