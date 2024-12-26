package com.tron.wallet.common.components.mnemonicflowlayout;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.StringTronUtil;
import com.tron.wallet.R;
import com.tron.wallet.business.walletmanager.importwallet.ImportWalletActivity;
import com.tron.wallet.common.components.mnemonicflowlayout.MnemonicTagAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.tron.common.bip39.BIP39;
public class MnemonicTagFlowLayout extends FlowLayout implements MnemonicTagAdapter.OnDataChangedListener {
    private boolean attachInput;
    private Context context;
    private onEditContentListener editContentListener;
    private EditText editText;
    private onEmptyTagListener emptyTagListener;
    private View.OnFocusChangeListener focusChangeListener;
    private boolean isClickAssociational;
    private boolean isSelect;
    AssociationalListener mAssociationalListener;
    private Set<Integer> mSelectedItemPosSet;
    private MnemonicTagAdapter mTagAdapter;
    private int mistakeCount;
    String newStr;
    private TextView pasteTextView;
    private onRemoveItemListener removeItemListener;
    private int selectedIndex;

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

    public EditText getEditText() {
        return this.editText;
    }

    public onEmptyTagListener getEmptyTagListener() {
        return this.emptyTagListener;
    }

    public View.OnFocusChangeListener getFocusChangeListener() {
        return this.focusChangeListener;
    }

    public void setAssociationalListener(AssociationalListener associationalListener) {
        this.mAssociationalListener = associationalListener;
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

    public MnemonicTagFlowLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.attachInput = true;
        this.mSelectedItemPosSet = new HashSet();
        this.mistakeCount = 0;
        this.isClickAssociational = false;
        this.newStr = "";
        this.selectedIndex = -1;
        this.isSelect = false;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TagFlowLayout);
        this.context = context;
        obtainStyledAttributes.recycle();
    }

    public void requestEditFocus() {
        this.editText.requestFocus();
        ((InputMethodManager) this.context.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(this.editText, 1);
        this.editText.setCursorVisible(true);
    }

    public MnemonicTagFlowLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MnemonicTagFlowLayout(Context context) {
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

    public void setAdapter(MnemonicTagAdapter mnemonicTagAdapter) {
        this.mTagAdapter = mnemonicTagAdapter;
        mnemonicTagAdapter.setOnDataChangedListener(this);
        this.mSelectedItemPosSet.clear();
        changeAdapter();
    }

    public void removeItem(int i) {
        if (!checkWord(this.mTagAdapter.getItem(i))) {
            this.mistakeCount--;
        }
        MnemonicTagAdapter mnemonicTagAdapter = this.mTagAdapter;
        mnemonicTagAdapter.removeWrongData(mnemonicTagAdapter.getItem(i));
        this.mTagAdapter.remove(i);
        this.mSelectedItemPosSet.clear();
        this.mAssociationalListener.onTagChanged();
        this.mAssociationalListener.hasMistake(this.mistakeCount);
        removeItemView(i);
        onRemoveItemListener onremoveitemlistener = this.removeItemListener;
        if (onremoveitemlistener != null) {
            onremoveitemlistener.removeItem(i);
        }
    }

    private void checkSameWord() {
        ArrayList arrayList = (ArrayList) this.mTagAdapter.getDatas();
        ArrayList same = same(arrayList);
        for (int i = 0; i < arrayList.size(); i++) {
            TextView textView = (TextView) ((TagView) getChildAt(i)).getChildAt(0);
            if (same.contains(String.valueOf(i))) {
                textView.setTextColor(this.context.getResources().getColor(com.tronlinkpro.wallet.R.color.orange_FF));
            } else if (checkWord(textView.getText().toString())) {
                textView.setTextColor(this.context.getResources().getColor(com.tronlinkpro.wallet.R.color.black_02));
            } else {
                textView.setTextColor(this.context.getResources().getColor(com.tronlinkpro.wallet.R.color.orange_FF));
            }
        }
        this.mAssociationalListener.hasMistake(same.size() > 0 ? 1 : this.mistakeCount);
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

    public void addItem(String str) {
        if (!this.isClickAssociational) {
            Context context = this.context;
            String firstAssociationalWord = context instanceof ImportWalletActivity ? ((ImportWalletActivity) context).getFirstAssociationalWord() : "";
            MnemonicTagAdapter mnemonicTagAdapter = this.mTagAdapter;
            if (!StringTronUtil.isEmpty(firstAssociationalWord)) {
                str = firstAssociationalWord;
            }
            mnemonicTagAdapter.add(str);
        } else {
            this.mTagAdapter.add(str);
        }
        this.isClickAssociational = false;
        this.mSelectedItemPosSet.clear();
        this.mAssociationalListener.onTagChanged();
        addItemView();
    }

    public void hiddenPasteTextView() {
        ((TagView) getChildAt(getChildCount() - 1)).setVisibility(View.GONE);
        this.editText.setHint("");
    }

    private void showPasteTextView() {
        if (getChildCount() > 2) {
            return;
        }
        ((TagView) getChildAt(getChildCount() - 1)).setVisibility(View.VISIBLE);
        this.editText.setHint(getResources().getString(com.tronlinkpro.wallet.R.string.please_input_content));
    }

    private boolean checkSameWord(String str) {
        ArrayList arrayList = (ArrayList) this.mTagAdapter.getDatas();
        if (arrayList == null || arrayList.size() == 1) {
            return false;
        }
        boolean z = false;
        for (int i = 0; i < this.mTagAdapter.getDatas().size() - 1; i++) {
            if (this.mTagAdapter.getDatas().get(i).equals(str)) {
                ((TextView) ((TagView) getChildAt(i)).getChildAt(0)).setTextColor(this.context.getResources().getColor(com.tronlinkpro.wallet.R.color.orange_FF));
                z = true;
            }
        }
        return z;
    }

    private void changeAdapter() {
        removeAllViews();
        final MnemonicTagAdapter mnemonicTagAdapter = this.mTagAdapter;
        for (int i = 0; i < mnemonicTagAdapter.getCount(); i++) {
            if (mnemonicTagAdapter.getCount() > 0) {
                TextView view = mnemonicTagAdapter.getView(this, i, mnemonicTagAdapter.getItem(i));
                view.setBackgroundResource(com.tronlinkpro.wallet.R.drawable.mnemonic_tagbg);
                TagView tagView = new TagView(getContext());
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
                view.setClickable(false);
            }
        }
        if (this.attachInput) {
            this.editText = (EditText) mnemonicTagAdapter.getInputView(this);
            TagView tagView2 = new TagView(getContext());
            this.editText.setDuplicateParentStateEnabled(true);
            if (this.editText.getLayoutParams() != null) {
                tagView2.setLayoutParams(this.editText.getLayoutParams());
            } else {
                ViewGroup.MarginLayoutParams marginLayoutParams2 = new ViewGroup.MarginLayoutParams(-2, -2);
                marginLayoutParams2.setMargins(dip2px(getContext(), 5.0f), dip2px(getContext(), 5.0f), dip2px(getContext(), 5.0f), dip2px(getContext(), 5.0f));
                tagView2.setMinimumWidth(dip2px(getContext(), 20.0f));
                tagView2.setLayoutParams(marginLayoutParams2);
            }
            this.editText.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            tagView2.addView(this.editText);
            addView(tagView2);
            this.editText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    editText.setCursorVisible(true);
                    hiddenPasteTextView();
                }
            });
            this.editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view2, boolean z) {
                    if (focusChangeListener != null) {
                        focusChangeListener.onFocusChange(view2, z);
                    }
                }
            });
            this.editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                    if (editContentListener != null) {
                        editContentListener.onContentInput();
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if (editable == null || " ".equals(editable.toString()) || StringUtils.LF.equals(editable.toString())) {
                        editable.clear();
                    } else if (editable.toString().split("\\s+").length > 1) {
                        pasteText(editable.toString());
                        editable.clear();
                    } else {
                        if (editable.toString().length() > 0) {
                            hiddenPasteTextView();
                        }
                        if ((editable.toString().contains(" ") || editable.toString().contains(StringUtils.LF)) && editable.length() > 1) {
                            String substring = editable.toString().substring(0, editable.length() - 1);
                            if (substring.trim().equals("")) {
                                editText.setText("");
                                return;
                            }
                            addItem(substring);
                            editText.setText("");
                            editText.setMinWidth(MnemonicTagFlowLayout.dip2px(getContext(), 28.0f));
                            return;
                        }
                        MnemonicTagFlowLayout mnemonicTagFlowLayout = MnemonicTagFlowLayout.this;
                        mnemonicTagFlowLayout.doUnSelect((TagView) mnemonicTagFlowLayout.getChildAt(mnemonicTagFlowLayout.getChildCount() - 3), getChildCount() - 3);
                        updateAssociational(editable.toString().toLowerCase());
                    }
                }
            });
            this.editText.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view2, int i2, KeyEvent keyEvent) {
                    if (i2 == 67 && keyEvent.getAction() == 1) {
                        if (isSelect) {
                            MnemonicTagFlowLayout mnemonicTagFlowLayout = MnemonicTagFlowLayout.this;
                            mnemonicTagFlowLayout.removeItem(mnemonicTagFlowLayout.selectedIndex);
                            isSelect = false;
                            if (StringTronUtil.isEmpty(mnemonicTagAdapter.formatDataString())) {
                                if (emptyTagListener != null) {
                                    emptyTagListener.onLastTagDelete();
                                }
                            } else {
                                editText.requestFocus();
                                editText.setCursorVisible(true);
                            }
                        } else if (editText.length() == 0) {
                            MnemonicTagFlowLayout mnemonicTagFlowLayout2 = MnemonicTagFlowLayout.this;
                            mnemonicTagFlowLayout2.doSelect((TagView) mnemonicTagFlowLayout2.getChildAt(mnemonicTagFlowLayout2.getChildCount() - 3), getChildCount() - 3);
                        }
                    }
                    return false;
                }
            });
            this.editText.setFilters(new InputFilter[]{new InputFilter() {
                @Override
                public CharSequence filter(CharSequence charSequence, int i2, int i3, Spanned spanned, int i4, int i5) {
                    return (!isClickAssociational && Pattern.compile("[^a-zA-Z\\s\\n]").matcher(charSequence).matches()) ? " " : charSequence;
                }
            }});
        }
        addPasteButton();
    }

    public void addPasteButton() {
        this.editText.setHint(getResources().getString(com.tronlinkpro.wallet.R.string.please_input_content));
        this.editText.setHintTextColor(getResources().getColor(com.tronlinkpro.wallet.R.color.gray_9999));
        TagView tagView = new TagView(getContext());
        TextView textView = new TextView(this.context);
        this.pasteTextView = textView;
        textView.setText(getResources().getString(com.tronlinkpro.wallet.R.string.paste));
        this.pasteTextView.setVisibility(View.INVISIBLE);
        this.pasteTextView.setTextColor(getResources().getColor(com.tronlinkpro.wallet.R.color.black_02));
        this.pasteTextView.setBackgroundResource(com.tronlinkpro.wallet.R.drawable.paste_tag_selected_bg);
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-2, -2);
        marginLayoutParams.setMargins(dip2px(getContext(), 0.0f), dip2px(getContext(), 5.0f), dip2px(getContext(), 5.0f), dip2px(getContext(), 5.0f));
        tagView.setLayoutParams(marginLayoutParams);
        this.pasteTextView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.pasteTextView.setPadding(dip2px(getContext(), 6.0f), dip2px(getContext(), 3.0f), dip2px(getContext(), 6.0f), dip2px(getContext(), 3.0f));
        tagView.addView(this.pasteTextView);
        addView(tagView);
        this.pasteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                if (clipboardManager == null || clipboardManager.getPrimaryClip() == null || clipboardManager.getPrimaryClip().getItemCount() <= 0) {
                    return;
                }
                pasteText(clipboardManager.getPrimaryClip().getItemAt(0).getText().toString());
            }
        });
    }

    private void showInput(final EditText editText) {
        editText.requestFocus();
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                ((InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(editText, 1);
            }
        });
    }

    public void hideSoftKeyBoard(Activity activity) {
        View peekDecorView;
        if (activity == null || activity.getWindow() == null || (peekDecorView = activity.getWindow().peekDecorView()) == null || peekDecorView.getWindowToken() == null) {
            return;
        }
        try {
            ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 2);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    private void setChildChecked(TagView tagView) {
        tagView.setChecked(true);
    }

    private void setChildUnChecked(TagView tagView) {
        tagView.setChecked(false);
    }

    public void doUnSelect(TagView tagView, int i) {
        if (tagView == null || i < 0 || !tagView.isChecked()) {
            return;
        }
        setChildUnChecked(tagView);
        this.mSelectedItemPosSet.remove(Integer.valueOf(i));
        this.isSelect = false;
    }

    public void doSelect(TagView tagView, int i) {
        if (tagView == null || i < 0) {
            return;
        }
        if (!tagView.isChecked()) {
            if (this.mSelectedItemPosSet.size() == 1) {
                Integer next = this.mSelectedItemPosSet.iterator().next();
                setChildUnChecked((TagView) getChildAt(next.intValue()));
                setChildChecked(tagView);
                this.mSelectedItemPosSet.remove(next);
                this.mSelectedItemPosSet.add(Integer.valueOf(i));
            } else if (this.mSelectedItemPosSet.size() >= 1) {
                return;
            } else {
                setChildChecked(tagView);
                this.mSelectedItemPosSet.add(Integer.valueOf(i));
            }
            if (this.attachInput) {
                showInput(this.editText);
            }
            this.selectedIndex = i;
            this.isSelect = true;
            return;
        }
        setChildUnChecked(tagView);
        this.mSelectedItemPosSet.remove(Integer.valueOf(i));
        this.isSelect = false;
    }

    @Override
    public void onChanged() {
        this.mSelectedItemPosSet.clear();
        changeAdapter();
    }

    @Override
    public void addItemView() {
        MnemonicTagAdapter mnemonicTagAdapter = this.mTagAdapter;
        TagView tagView = new TagView(getContext());
        TextView view = mnemonicTagAdapter.getView(this, mnemonicTagAdapter.getCount() - 1, mnemonicTagAdapter.getItem(mnemonicTagAdapter.getCount() - 1));
        view.setBackgroundResource(com.tronlinkpro.wallet.R.drawable.mnemonic_tagbg);
        if (!checkWord(view.getText().toString())) {
            view.setTextColor(this.context.getResources().getColor(com.tronlinkpro.wallet.R.color.red_ec));
            int i = this.mistakeCount + 1;
            this.mistakeCount = i;
            this.mAssociationalListener.hasMistake(i);
        } else {
            this.mAssociationalListener.hasMistake(this.mistakeCount);
            view.setTextColor(this.context.getResources().getColor(com.tronlinkpro.wallet.R.color.black_02));
        }
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
        addView(tagView, mnemonicTagAdapter.getCount() - 1);
    }

    @Override
    public void removeItemView(int i) {
        if (getChildCount() <= i || getChildAt(i) == null) {
            return;
        }
        removeViewAt(i);
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public void updateAssociational(String str) {
        String[] strArr;
        ArrayList arrayList = new ArrayList();
        for (String str2 : BIP39.english) {
            if (arrayList.size() > 4 || StringTronUtil.isEmpty(str)) {
                break;
            }
            if (str2.startsWith(str)) {
                arrayList.add(str2);
            }
        }
        this.mAssociationalListener.update(str, arrayList);
    }

    public boolean checkWord(String str) {
        for (String str2 : BIP39.english) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public void setEditText(String str, boolean z) {
        this.isClickAssociational = z;
        EditText editText = this.editText;
        editText.setText(str + " ");
    }

    public void pasteText(String str) {
        String[] split;
        this.mTagAdapter.clearWrongDatas();
        this.editText.setText("");
        hiddenPasteTextView();
        for (String str2 : str.replaceAll("[^a-zA-Z\\s\\n]", " ").trim().split("\\s+")) {
            if (!str2.equals("")) {
                addItem(str2);
            }
        }
    }

    public void clearAllWords() {
        this.mSelectedItemPosSet.clear();
        this.mTagAdapter.clearWrongDatas();
        this.mTagAdapter.resetData(new ArrayList());
    }

    public int getMistakeCount() {
        MnemonicTagAdapter mnemonicTagAdapter = this.mTagAdapter;
        int i = 0;
        if (mnemonicTagAdapter != null && mnemonicTagAdapter.getDatas() != null) {
            for (String str : this.mTagAdapter.getDatas()) {
                if (!checkWord(str)) {
                    i++;
                }
            }
        }
        return i;
    }
}
