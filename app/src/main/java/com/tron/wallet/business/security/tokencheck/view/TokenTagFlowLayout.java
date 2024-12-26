package com.tron.wallet.business.security.tokencheck.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.R;
import com.tron.wallet.business.security.tokencheck.view.TokenTagAdapter;
import com.tron.wallet.common.components.mnemonicflowlayout.AssociationalListener;
import com.tron.wallet.common.components.mnemonicflowlayout.FlowLayout;
import com.tron.wallet.common.components.mnemonicflowlayout.TagView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class TokenTagFlowLayout extends FlowLayout implements TokenTagAdapter.OnDataChangedListener {
    private boolean attachInput;
    private onEditContentListener editContentListener;
    private onEmptyTagListener emptyTagListener;
    private View.OnFocusChangeListener focusChangeListener;
    private boolean isClickAssociational;
    AssociationalListener mAssociationalListener;
    private Context mContext;
    private Set<Integer> mSelectedItemPosSet;
    private TokenTagAdapter mTagAdapter;
    private int mistakeCount;
    private TextView pasteTextView;
    private onRemoveItemListener removeItemListener;

    public interface onEditContentListener {
        void onContentInput();
    }

    public interface onEmptyTagListener {
        void onLastTagDelete();
    }

    public interface onRemoveItemListener {
        void removeItem(int i);
    }

    public onEditContentListener getEditContentListener() {
        return this.editContentListener;
    }

    public onEmptyTagListener getEmptyTagListener() {
        return this.emptyTagListener;
    }

    public View.OnFocusChangeListener getFocusChangeListener() {
        return this.focusChangeListener;
    }

    public void setEditContentListener(onEditContentListener oneditcontentlistener) {
        this.editContentListener = oneditcontentlistener;
    }

    public void setEmptyTagListener(onEmptyTagListener onemptytaglistener) {
        this.emptyTagListener = onemptytaglistener;
    }

    public void setFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.focusChangeListener = onFocusChangeListener;
    }

    public void setRemoveItemListener(onRemoveItemListener onremoveitemlistener) {
        this.removeItemListener = onremoveitemlistener;
    }

    public TokenTagFlowLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.attachInput = true;
        this.mSelectedItemPosSet = new HashSet();
        this.mistakeCount = 0;
        this.isClickAssociational = false;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TagFlowLayout);
        this.mContext = context;
        obtainStyledAttributes.recycle();
    }

    public TokenTagFlowLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TokenTagFlowLayout(Context context) {
        this(context, null);
    }

    @Override
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    public void setAdapter(TokenTagAdapter tokenTagAdapter) {
        this.mTagAdapter = tokenTagAdapter;
        tokenTagAdapter.setOnDataChangedListener(this);
        this.mSelectedItemPosSet.clear();
        changeAdapter();
    }

    public ArrayList same(List<String> list) {
        HashMap hashMap = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            String str2 = (String) hashMap.get(str);
            if (str2 != null) {
                hashMap.put(str, str2 + "," + i);
            } else {
                hashMap.put(str, "" + i);
            }
        }
        ArrayList arrayList = new ArrayList();
        for (String str3 : hashMap.keySet()) {
            String str4 = (String) hashMap.get(str3);
            if (str4.indexOf(",") != -1) {
                arrayList.addAll(Arrays.asList(str4.split(",")));
            }
            LogUtils.e("repeatListIndex = ", arrayList.toString());
        }
        return arrayList;
    }

    private void changeAdapter() {
        removeAllViews();
        TokenTagAdapter tokenTagAdapter = this.mTagAdapter;
        for (int i = 0; i < tokenTagAdapter.getCount(); i++) {
            if (tokenTagAdapter.getCount() > 0) {
                View view = tokenTagAdapter.getView(this, i, tokenTagAdapter.getItem(i));
                view.setDuplicateParentStateEnabled(true);
                addView(view);
            }
        }
        if (this.attachInput) {
            TagView tagView = new TagView(getContext());
            ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-2, -2);
            marginLayoutParams.setMargins(dip2px(getContext(), 5.0f), dip2px(getContext(), 5.0f), dip2px(getContext(), 5.0f), dip2px(getContext(), 5.0f));
            tagView.setMinimumWidth(dip2px(getContext(), 20.0f));
            tagView.setLayoutParams(marginLayoutParams);
            addView(tagView);
        }
    }

    @Override
    public void onChanged() {
        this.mSelectedItemPosSet.clear();
        changeAdapter();
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
