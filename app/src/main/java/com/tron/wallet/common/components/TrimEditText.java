package com.tron.wallet.common.components;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatEditText;
import java.util.function.Consumer;
import java.util.regex.Pattern;
public class TrimEditText extends AppCompatEditText {
    private Consumer<Integer> pasteCallback;

    public void setPasteCallback(Consumer<Integer> consumer) {
        this.pasteCallback = consumer;
    }

    public TrimEditText(Context context) {
        super(context);
    }

    public TrimEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TrimEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override
    public boolean onTextContextMenuItem(int i) {
        ClipboardManager clipboardManager;
        if (i == 16908322 && (clipboardManager = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE)) != null && clipboardManager.getPrimaryClip() != null && clipboardManager.getPrimaryClip().getItemCount() > 0 && clipboardManager.getPrimaryClip().getItemAt(0).getText() != null) {
            clipboardManager.setPrimaryClip(ClipData.newPlainText(null, replaceBlank(clipboardManager.getPrimaryClip().getItemAt(0).getText().toString())));
        }
        boolean onTextContextMenuItem = super.onTextContextMenuItem(i);
        Consumer<Integer> consumer = this.pasteCallback;
        if (consumer != null && i == 16908322) {
            consumer.accept(Integer.valueOf(i));
        }
        return onTextContextMenuItem;
    }

    private String replaceBlank(String str) {
        if (str != null) {
            return Pattern.compile("\\s*|\t|\r|\n").matcher(str).replaceAll("");
        }
        return null;
    }
}
