package com.tron.wallet.business.pull.messagesign;

import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tronlinkpro.wallet.R;
import j$.util.Collection;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import org.tron.common.utils.ByteArray;
public class TypeDataParser {
    private List<String> addressParseList;
    private ForegroundColorSpan span_232c41 = new ForegroundColorSpan(AppContextUtil.getContext().getResources().getColor(R.color.gray_232c41));
    private StyleSpan span_bold = new StyleSpan(1);
    private List<TextView> views = new ArrayList();

    private void appendStr(String str, String str2, int i) {
        if (StringTronUtil.isEmpty(str) && StringTronUtil.isEmpty(str2)) {
            return;
        }
        LogUtils.i("layer:" + str + i);
        TextView newTextView = getNewTextView();
        String str3 = StringTronUtil.isEmpty(str) ? "" : str + ": ";
        for (int i2 = 0; i2 < i; i2++) {
            int dip2px = UIUtils.dip2px(10.0f);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(dip2px * i2, 0, dip2px, 0);
            newTextView.setLayoutParams(layoutParams);
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) str3);
        spannableStringBuilder.setSpan(this.span_232c41, 0, str3.length(), 33);
        spannableStringBuilder.setSpan(this.span_bold, 0, str3.length(), 33);
        if (!StringTronUtil.isEmpty(str2)) {
            spannableStringBuilder.append((CharSequence) str2);
        }
        newTextView.setText(spannableStringBuilder);
        this.views.add(newTextView);
    }

    public TextView getNewTextView() {
        TextView textView = new TextView(AppContextUtil.getContext());
        textView.setTextColor(AppContextUtil.getContext().getResources().getColor(R.color.gray_6D778C));
        textView.setTextSize(12.0f);
        textView.setGravity(3);
        return textView;
    }

    public List<TextView> parserString(String str, List<String> list) {
        this.addressParseList = list;
        try {
            Collection.-EL.stream(JsonParser.parseString(str).getAsJsonObject().entrySet()).forEach(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    lambda$parserString$0((Map.Entry) obj);
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return this.views;
    }

    public void lambda$parserString$0(Map.Entry entry) {
        _parseString((String) entry.getKey(), (JsonElement) entry.getValue(), 1, false);
    }

    private void _parseString(String str, JsonElement jsonElement, final int i, final boolean z) {
        try {
            if (jsonElement.isJsonObject()) {
                appendStr(str, "", i);
                Collection.-EL.stream(jsonElement.getAsJsonObject().entrySet()).forEach(new Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        lambda$_parseString$1(z, i, (Map.Entry) obj);
                    }

                    public Consumer andThen(Consumer consumer) {
                        return Objects.requireNonNull(consumer);
                    }
                });
            } else if (jsonElement.isJsonArray()) {
                appendStr(str, "", i);
                JsonArray asJsonArray = jsonElement.getAsJsonArray();
                for (int i2 = 0; i2 < asJsonArray.size(); i2++) {
                    _parseString(null, asJsonArray.get(i2), i + 1, true);
                }
            } else if (jsonElement.isJsonPrimitive()) {
                String asString = jsonElement.getAsString();
                List<String> list = this.addressParseList;
                if (list != null && list.contains(asString)) {
                    try {
                        asString = StringTronUtil.encode58Check(ByteArray.fromHexString(asString));
                    } catch (Exception e) {
                        LogUtils.e(e);
                    }
                }
                if (z) {
                    appendStr("", asString, i);
                } else {
                    appendStr(str, asString, i);
                }
            }
        } catch (Exception e2) {
            LogUtils.e(e2);
        }
    }

    public void lambda$_parseString$1(boolean z, int i, Map.Entry entry) {
        String str = (String) entry.getKey();
        JsonElement jsonElement = (JsonElement) entry.getValue();
        if (z) {
            _parseString(str, jsonElement, i, false);
        } else {
            _parseString(str, jsonElement, i + 1, false);
        }
    }
}
