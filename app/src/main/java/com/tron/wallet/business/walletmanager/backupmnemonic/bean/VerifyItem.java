package com.tron.wallet.business.walletmanager.backupmnemonic.bean;

import java.util.List;
public class VerifyItem {
    private String answer;
    private int num;
    private List<MnemonicWord> words;

    public String getAnswer() {
        return this.answer;
    }

    public int getNum() {
        return this.num;
    }

    public List<MnemonicWord> getWords() {
        return this.words;
    }

    public void setAnswer(String str) {
        this.answer = str;
    }

    public void setNum(int i) {
        this.num = i;
    }

    public void setWords(List<MnemonicWord> list) {
        this.words = list;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof VerifyItem;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof VerifyItem) {
            VerifyItem verifyItem = (VerifyItem) obj;
            if (verifyItem.canEqual(this) && getNum() == verifyItem.getNum()) {
                String answer = getAnswer();
                String answer2 = verifyItem.getAnswer();
                if (answer != null ? answer.equals(answer2) : answer2 == null) {
                    List<MnemonicWord> words = getWords();
                    List<MnemonicWord> words2 = verifyItem.getWords();
                    return words != null ? words.equals(words2) : words2 == null;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        String answer = getAnswer();
        int num = ((getNum() + 59) * 59) + (answer == null ? 43 : answer.hashCode());
        List<MnemonicWord> words = getWords();
        return (num * 59) + (words != null ? words.hashCode() : 43);
    }

    public String toString() {
        return "VerifyItem(num=" + getNum() + ", answer=" + getAnswer() + ", words=" + getWords() + ")";
    }
}
