package com.tron.wallet.common.components.flowlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.R;
import com.tron.wallet.common.components.flowlayout.TagAdapter;
import java.util.HashSet;
import java.util.Set;
public class TagFlowLayout extends FlowLayout implements TagAdapter.OnDataChangedListener {
    private static final String KEY_CHOOSE_POS = "key_choose_pos";
    private static final String KEY_DEFAULT = "key_default";
    private static final String TAG = "TagFlowLayout";
    private OnSelectListener mOnSelectListener;
    private OnTagClickListener mOnTagClickListener;
    private int mSelectedMax;
    private Set<Integer> mSelectedView;
    private TagAdapter mTagAdapter;

    public interface OnSelectListener {
        void onSelected(Set<Integer> set);
    }

    public interface OnTagClickListener {
        boolean onTagClick(View view, int i, FlowLayout flowLayout);
    }

    public TagAdapter getAdapter() {
        return this.mTagAdapter;
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        this.mOnSelectListener = onSelectListener;
    }

    public void setOnTagClickListener(OnTagClickListener onTagClickListener) {
        this.mOnTagClickListener = onTagClickListener;
    }

    public TagFlowLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mSelectedMax = -1;
        this.mSelectedView = new HashSet();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TagFlowLayout);
        this.mSelectedMax = obtainStyledAttributes.getInt(0, -1);
        obtainStyledAttributes.recycle();
    }

    public TagFlowLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TagFlowLayout(Context context) {
        this(context, null);
    }

    @Override
    public void onMeasure(int i, int i2) {
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            TagView tagView = (TagView) getChildAt(i3);
            if (tagView.getVisibility() != 8 && tagView.getTagView().getVisibility() == 8) {
                tagView.setVisibility(View.GONE);
            }
        }
        super.onMeasure(i, i2);
    }

    public void setAdapter(TagAdapter tagAdapter) {
        this.mTagAdapter = tagAdapter;
        tagAdapter.setOnDataChangedListener(this);
        this.mSelectedView.clear();
        changeAdapter();
    }

    private void changeAdapter() {
        removeAllViews();
        TagAdapter tagAdapter = this.mTagAdapter;
        HashSet<Integer> preCheckedList = tagAdapter.getPreCheckedList();
        for (final int i = 0; i < tagAdapter.getCount(); i++) {
            View view = tagAdapter.getView(this, i, tagAdapter.getItem(i));
            final TagView tagView = new TagView(getContext());
            view.setDuplicateParentStateEnabled(true);
            if (view.getLayoutParams() != null) {
                tagView.setLayoutParams(view.getLayoutParams());
            } else {
                ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-2, -2);
                marginLayoutParams.setMargins(dip2px(getContext(), 5.0f), dip2px(getContext(), 5.0f), dip2px(getContext(), 5.0f), dip2px(getContext(), 5.0f));
                tagView.setLayoutParams(marginLayoutParams);
            }
            view.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            tagView.addView(view);
            addView(tagView);
            if (preCheckedList.contains(Integer.valueOf(i))) {
                setChildChecked(i, tagView);
            }
            if (this.mTagAdapter.setSelected(i, tagAdapter.getItem(i))) {
                setChildChecked(i, tagView);
            }
            view.setClickable(false);
            tagView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    doSelect(tagView, i);
                    if (mOnTagClickListener != null) {
                        mOnTagClickListener.onTagClick(tagView, i, TagFlowLayout.this);
                    }
                }
            });
        }
        this.mSelectedView.addAll(preCheckedList);
    }

    public void setMaxSelectCount(int i) {
        if (this.mSelectedView.size() > i) {
            LogUtils.w(TAG, "you has already select more than " + i + " views , so it will be clear .");
            this.mSelectedView.clear();
        }
        this.mSelectedMax = i;
    }

    public Set<Integer> getSelectedList() {
        return new HashSet(this.mSelectedView);
    }

    private void setChildChecked(int i, TagView tagView) {
        tagView.setChecked(true);
        this.mTagAdapter.onSelected(i, tagView.getTagView());
    }

    private void setChildUnChecked(int i, TagView tagView) {
        tagView.setChecked(false);
        this.mTagAdapter.unSelected(i, tagView.getTagView());
    }

    public void doSelect(TagView tagView, int i) {
        if (!tagView.isChecked()) {
            if (this.mSelectedMax == 1 && this.mSelectedView.size() == 1) {
                Integer next = this.mSelectedView.iterator().next();
                setChildUnChecked(next.intValue(), (TagView) getChildAt(next.intValue()));
                setChildChecked(i, tagView);
                this.mSelectedView.remove(next);
                this.mSelectedView.add(Integer.valueOf(i));
            } else if (this.mSelectedMax > 0 && this.mSelectedView.size() >= this.mSelectedMax) {
                return;
            } else {
                setChildChecked(i, tagView);
                this.mSelectedView.add(Integer.valueOf(i));
            }
        } else {
            setChildUnChecked(i, tagView);
            this.mSelectedView.remove(Integer.valueOf(i));
        }
        OnSelectListener onSelectListener = this.mOnSelectListener;
        if (onSelectListener != null) {
            onSelectListener.onSelected(new HashSet(this.mSelectedView));
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_DEFAULT, super.onSaveInstanceState());
        String str = "";
        if (this.mSelectedView.size() > 0) {
            for (Integer num : this.mSelectedView) {
                str = str + num.intValue() + "|";
            }
            str = str.substring(0, str.length() - 1);
        }
        bundle.putString(KEY_CHOOSE_POS, str);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            String string = bundle.getString(KEY_CHOOSE_POS);
            if (!TextUtils.isEmpty(string)) {
                for (String str : string.split("\\|")) {
                    int parseInt = Integer.parseInt(str);
                    this.mSelectedView.add(Integer.valueOf(parseInt));
                    TagView tagView = (TagView) getChildAt(parseInt);
                    if (tagView != null) {
                        setChildChecked(parseInt, tagView);
                    }
                }
            }
            super.onRestoreInstanceState(bundle.getParcelable(KEY_DEFAULT));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    @Override
    public void onChanged() {
        this.mSelectedView.clear();
        changeAdapter();
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
