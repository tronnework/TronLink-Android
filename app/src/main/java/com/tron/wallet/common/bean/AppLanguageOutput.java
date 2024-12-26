package com.tron.wallet.common.bean;

import java.util.List;
public class AppLanguageOutput {
    private List<String> currency;
    private List<String> language;

    public List<String> getCurrency() {
        return this.currency;
    }

    public List<String> getLanguage() {
        return this.language;
    }

    public void setCurrency(List<String> list) {
        this.currency = list;
    }

    public void setLanguage(List<String> list) {
        this.language = list;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof AppLanguageOutput;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AppLanguageOutput) {
            AppLanguageOutput appLanguageOutput = (AppLanguageOutput) obj;
            if (appLanguageOutput.canEqual(this)) {
                List<String> currency = getCurrency();
                List<String> currency2 = appLanguageOutput.getCurrency();
                if (currency != null ? currency.equals(currency2) : currency2 == null) {
                    List<String> language = getLanguage();
                    List<String> language2 = appLanguageOutput.getLanguage();
                    return language != null ? language.equals(language2) : language2 == null;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        List<String> currency = getCurrency();
        int hashCode = currency == null ? 43 : currency.hashCode();
        List<String> language = getLanguage();
        return ((hashCode + 59) * 59) + (language != null ? language.hashCode() : 43);
    }

    public String toString() {
        return "AppLanguageOutput(currency=" + getCurrency() + ", language=" + getLanguage() + ")";
    }
}
