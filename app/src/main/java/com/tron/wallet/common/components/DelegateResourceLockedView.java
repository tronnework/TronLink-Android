package com.tron.wallet.common.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.wallet.common.components.MultiLineTextPopupView;
import com.tron.wallet.common.components.pickerview.pickerview.builder.OptionsPickerBuilder;
import com.tron.wallet.common.components.pickerview.pickerview.listener.OnOptionsSelectListener;
import com.tron.wallet.common.components.pickerview.pickerview.view.OptionsPickerView;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.config.TronSetting;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.DateUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TimeUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.DelegateResourceLockViewBinding;
import com.tronlinkpro.wallet.R;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
public class DelegateResourceLockedView extends RelativeLayout {
    DelegateResourceLockViewBinding binding;
    private ArrayList<String> days;
    TextView errorTextView;
    View errorView;
    TextView frozenResource;
    private boolean hasError;
    private boolean hasLockTime;
    private ArrayList<String> hours;
    private long leftTime;
    View lockLayout;
    TextView lockTime;
    View lockTimeLayout;
    View lockTipsView;
    private Context mContext;
    private long mFrozenBalance;
    private long maxDaysOnChain;
    private ArrayList<String> minites;
    private OnDelegateResourceLockedListener onDelegateResourceLockedListener;
    private OptionsPickerView pvNoLinkOptions;
    TextView remainingTimeTextView;
    private long resource;
    View resourceLockedView;
    TextView resourceNewTextView;
    TextView resourceOldTextView;
    private int resourceType;
    SwitchButton switchButton;
    View timeLockedView;
    private long timeSelect;
    View timeTipsView;
    TextView unDelegateTextView;
    TextView unDelegateTimeNewTextView;
    TextView unDelegateTimeOldTextView;
    String unit;

    public interface OnDelegateResourceLockedListener {
        void onError(boolean z);

        void onPickedShowCallback();

        void onTimeUpdate(long j);
    }

    public long getFrozenBalance() {
        return this.mFrozenBalance;
    }

    public long getLeftTime() {
        return this.leftTime;
    }

    public long getResource() {
        return this.resource;
    }

    public int getResourceType() {
        return this.resourceType;
    }

    public long getTimeSelect() {
        return this.timeSelect;
    }

    public boolean isHasError() {
        return this.hasError;
    }

    public boolean isHasLockTime() {
        return this.hasLockTime;
    }

    public void setFrozenBalance(long j) {
        this.mFrozenBalance = j;
    }

    public void setHasError(boolean z) {
        this.hasError = z;
    }

    public void setHasLockTime(boolean z) {
        this.hasLockTime = z;
    }

    public void setLeftTime(long j) {
        this.leftTime = j;
    }

    public void setOnDelegateResourceLockedListener(OnDelegateResourceLockedListener onDelegateResourceLockedListener) {
        this.onDelegateResourceLockedListener = onDelegateResourceLockedListener;
    }

    public void setResource(long j) {
        this.resource = j;
    }

    public DelegateResourceLockedView(Context context) {
        super(context);
        this.days = new ArrayList<>();
        this.hours = new ArrayList<>();
        this.minites = new ArrayList<>();
        this.unit = "";
        init(context);
    }

    public DelegateResourceLockedView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.days = new ArrayList<>();
        this.hours = new ArrayList<>();
        this.minites = new ArrayList<>();
        this.unit = "";
        init(context);
    }

    public DelegateResourceLockedView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.days = new ArrayList<>();
        this.hours = new ArrayList<>();
        this.minites = new ArrayList<>();
        this.unit = "";
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        View inflate = View.inflate(context, R.layout.delegate_resource_lock_view, null);
        this.binding = DelegateResourceLockViewBinding.bind(inflate);
        mappingId();
        addView(inflate);
        this.lockTimeLayout.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                pvNoLinkOptions.setSelectOptions(timeSelect);
                pvNoLinkOptions.show();
                onDelegateResourceLockedListener.onPickedShowCallback();
            }
        });
        this.maxDaysOnChain = getMaxDaysOnChain();
        getNoLinkData();
        initNoLinkOptionsPicker();
        initTipsView();
    }

    private void mappingId() {
        this.switchButton = this.binding.switchButton;
        this.lockTipsView = this.binding.ivDelegateResourceLock;
        this.timeTipsView = this.binding.ivTimeUndelegateTips;
        this.lockLayout = this.binding.resourceLockLayout;
        this.lockTimeLayout = this.binding.lockTimeRl;
        this.lockTime = this.binding.lockTime;
        this.remainingTimeTextView = this.binding.remainingTime;
        this.frozenResource = this.binding.tvFrozenResource;
        this.unDelegateTextView = this.binding.tvTimeUndelegate;
        this.resourceOldTextView = this.binding.tvResourceLockedOld;
        this.resourceNewTextView = this.binding.tvResourceLockedNew;
        this.unDelegateTimeOldTextView = this.binding.tvTimeUndelegateOld;
        this.unDelegateTimeNewTextView = this.binding.tvTimeUndelegateNew;
        this.resourceLockedView = this.binding.rlResourceLockedNew;
        this.timeLockedView = this.binding.rlTimeUndelegateLocked;
        this.errorView = this.binding.llError;
        this.errorTextView = this.binding.tvError;
    }

    public void setTime(String str) {
        this.lockTime.setText(str);
    }

    public void setTime(long j) {
        this.lockTime.setText(TimeUtil.formatSeconds2DayWithDefaultThreeDays(j - System.currentTimeMillis()) + TimeUtil.dayString);
    }

    public void setRemainingTimeTextView(long j) {
        String str;
        if (j > System.currentTimeMillis()) {
            str = TimeUtil.formatTime(this.mContext, j - System.currentTimeMillis());
        } else {
            this.hasLockTime = false;
            str = "";
        }
        if (this.hasLockTime) {
            TextView textView = this.remainingTimeTextView;
            textView.setText("(" + this.mContext.getString(R.string.time_format_left) + " " + str + ")");
            this.remainingTimeTextView.setVisibility(View.VISIBLE);
            return;
        }
        this.remainingTimeTextView.setVisibility(View.GONE);
    }

    public void setResourceNew(long j) {
        if (this.hasLockTime) {
            TextView textView = this.resourceNewTextView;
            textView.setText(StringTronUtil.formatNumber0(BigDecimalUtils.sum_(this.frozenResource, Long.valueOf(j)).longValue()) + " " + this.unit);
            return;
        }
        TextView textView2 = this.frozenResource;
        textView2.setText(StringTronUtil.formatNumber0(j) + " " + this.unit);
    }

    public void setRemainingTimeGone() {
        this.remainingTimeTextView.setVisibility(View.GONE);
    }

    public void showLockedInfoView(boolean z) {
        this.lockLayout.setVisibility(z ? View.VISIBLE : View.GONE);
    }

    public void setLockedResource(long j, long j2, String str) {
        if (this.hasLockTime) {
            this.frozenResource.setVisibility(View.GONE);
            this.resourceLockedView.setVisibility(View.VISIBLE);
            TextView textView = this.resourceOldTextView;
            textView.setText(StringTronUtil.formatNumber0(j) + " " + this.unit);
            if (!StringTronUtil.isNullOrEmpty(str)) {
                BigDecimal sum_ = BigDecimalUtils.sum_(Long.valueOf(j), str);
                TextView textView2 = this.resourceNewTextView;
                textView2.setText(StringTronUtil.formatNumber0(sum_.longValue()) + " " + this.unit);
                return;
            }
            TextView textView3 = this.resourceNewTextView;
            textView3.setText(StringTronUtil.formatNumber0(j2) + " " + this.unit);
            return;
        }
        this.frozenResource.setVisibility(View.VISIBLE);
        this.resourceLockedView.setVisibility(View.GONE);
        if (!StringTronUtil.isNullOrEmpty(str)) {
            BigDecimal bigDecimal = BigDecimalUtils.toBigDecimal(str);
            TextView textView4 = this.frozenResource;
            textView4.setText(StringTronUtil.formatNumber0(bigDecimal.longValue()) + " " + this.unit);
            return;
        }
        TextView textView5 = this.frozenResource;
        textView5.setText(StringTronUtil.formatNumber0(j2) + " " + this.unit);
    }

    public void setUnDelegateTimeTextView(long j) {
        if (this.hasLockTime) {
            this.unDelegateTextView.setVisibility(View.GONE);
            this.timeLockedView.setVisibility(View.VISIBLE);
            this.unDelegateTimeOldTextView.setText(DateUtils.diffLanguageDateToString(new Date(this.leftTime), DateUtils.DATE_TO_STRING_DETAIAL_PATTERN_default_s));
            this.unDelegateTimeNewTextView.setText(DateUtils.diffLanguageDateToString(new Date(System.currentTimeMillis() + this.timeSelect), DateUtils.DATE_TO_STRING_DETAIAL_PATTERN_default_s));
            return;
        }
        this.unDelegateTextView.setVisibility(View.VISIBLE);
        this.timeLockedView.setVisibility(View.GONE);
        this.unDelegateTextView.setText(DateUtils.diffLanguageDateToString(new Date(j), DateUtils.DATE_TO_STRING_DETAIAL_PATTERN_default_s));
    }

    private void initNoLinkOptionsPicker() {
        OptionsPickerView build = new OptionsPickerBuilder(this.mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int i, int i2, int i3, View view) {
                timeSelect = BigDecimalUtils.mul_((Object) Integer.valueOf((i * 86400) + (i2 * 3600) + (i3 * 60)), (Object) 1000).longValue();
                DelegateResourceLockedView delegateResourceLockedView = DelegateResourceLockedView.this;
                delegateResourceLockedView.setTime(TimeUtil.formatTime(delegateResourceLockedView.mContext, timeSelect));
                setRemainingTimeGone();
                DelegateResourceLockedView delegateResourceLockedView2 = DelegateResourceLockedView.this;
                delegateResourceLockedView2.setUnDelegateTimeTextView(BigDecimalUtils.sum_(Long.valueOf(delegateResourceLockedView2.timeSelect), Long.valueOf(System.currentTimeMillis())).longValue());
                checkTheTime();
            }
        }).setItemVisibleCount(7).build();
        this.pvNoLinkOptions = build;
        build.setNPicker(this.days, this.hours, this.minites);
        this.pvNoLinkOptions.setSelectOptions(0, 1, 1);
    }

    public void checkTheTime() {
        if (this.hasLockTime) {
            cheTheTimeErrorOrNot(this.leftTime - System.currentTimeMillis());
        } else {
            cheTheTimeErrorOrNot(TimeUnit.MINUTES.toMillis(30L));
        }
    }

    private void cheTheTimeErrorOrNot(long j) {
        long j2 = this.timeSelect;
        if (j2 < j || j2 > TimeUtil.days2Ms(this.maxDaysOnChain)) {
            this.errorView.setVisibility(View.VISIBLE);
            this.errorTextView.setText(String.format(this.mContext.getResources().getString(R.string.selected_lock_time_error_format), TimeUtil.formatTime(this.mContext, j), TimeUtil.fromDay2Day(this.mContext, this.maxDaysOnChain)));
            setHasError(true);
            this.onDelegateResourceLockedListener.onError(true);
            return;
        }
        setHasError(false);
        this.onDelegateResourceLockedListener.onError(false);
        this.errorTextView.setText("");
        this.errorView.setVisibility(View.GONE);
    }

    public void hideErrorView() {
        this.errorView.setVisibility(View.GONE);
    }

    private void getNoLinkData() {
        for (int i = 0; i < this.maxDaysOnChain + 1; i++) {
            ArrayList<String> arrayList = this.days;
            arrayList.add("" + i + this.mContext.getString(R.string.day));
        }
        for (int i2 = 0; i2 < 24; i2++) {
            ArrayList<String> arrayList2 = this.hours;
            arrayList2.add("" + i2 + this.mContext.getString(R.string.hour));
        }
        for (int i3 = 0; i3 < 60; i3++) {
            ArrayList<String> arrayList3 = this.minites;
            arrayList3.add("" + i3 + this.mContext.getString(R.string.minute));
        }
    }

    public void setTimeSelect(long j) {
        long formatMsWithDefaultThreeDaysOfMs = TimeUtil.formatMsWithDefaultThreeDaysOfMs(j - System.currentTimeMillis());
        this.timeSelect = formatMsWithDefaultThreeDaysOfMs;
        this.pvNoLinkOptions.setSelectOptions(formatMsWithDefaultThreeDaysOfMs);
    }

    public long getMaxDaysOnChain() {
        if (TronSetting.maxDelegateLockPeriodDays == 0) {
            if (IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.RELEASE) {
                TronSetting.maxDelegateLockPeriodDays = 30L;
            } else if (IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.NILETEST) {
                TronSetting.maxDelegateLockPeriodDays = 5L;
            } else if (IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.SHASTA) {
                TronSetting.maxDelegateLockPeriodDays = 3L;
            }
        }
        return TronSetting.maxDelegateLockPeriodDays;
    }

    public void setResourceType(int i) {
        this.resourceType = i;
        if (i == TronConfig.RESOURCE_BANDWIDTH) {
            this.unit = this.mContext.getString(R.string.bandwidth);
        } else if (i == TronConfig.RESOURCE_ENERGY) {
            this.unit = this.mContext.getString(R.string.energy);
        }
    }

    public void initTipsView() {
        this.lockTipsView.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                new MultiLineTextPopupView.Builder().setAnchor(lockTipsView).setRequiredWidth(UIUtils.dip2px(264.0f)).addKeyValue(mContext.getString(R.string.resource_lock_tips), "").build(mContext).show();
            }
        });
        this.timeTipsView.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                new MultiLineTextPopupView.Builder().setAnchor(timeTipsView).setRequiredWidth(UIUtils.dip2px(264.0f)).addKeyValue(mContext.getString(R.string.recycle_time_tips), "").build(mContext).show();
            }
        });
    }
}
