package com.tron.wallet.common.bean;

import android.text.SpannableString;
public class SpannableConfirmExtraTextClickable extends ConfirmExtraTextClickable {
    private SpannableString left;
    private SpannableString right;

    @Override
    public SpannableString getLeft() {
        return this.left;
    }

    @Override
    public SpannableString getRight() {
        return this.right;
    }

    public void setLeft(SpannableString spannableString) {
        this.left = spannableString;
    }

    public void setRight(SpannableString spannableString) {
        this.right = spannableString;
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof SpannableConfirmExtraTextClickable;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SpannableConfirmExtraTextClickable) {
            SpannableConfirmExtraTextClickable spannableConfirmExtraTextClickable = (SpannableConfirmExtraTextClickable) obj;
            if (spannableConfirmExtraTextClickable.canEqual(this)) {
                SpannableString left = getLeft();
                SpannableString left2 = spannableConfirmExtraTextClickable.getLeft();
                if (left != null ? left.equals(left2) : left2 == null) {
                    SpannableString right = getRight();
                    SpannableString right2 = spannableConfirmExtraTextClickable.getRight();
                    return right != null ? right.equals(right2) : right2 == null;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        SpannableString left = getLeft();
        int hashCode = left == null ? 43 : left.hashCode();
        SpannableString right = getRight();
        return ((hashCode + 59) * 59) + (right != null ? right.hashCode() : 43);
    }

    @Override
    public String toString() {
        return "SpannableConfirmExtraTextClickable(left=" + ((Object) getLeft()) + ", right=" + ((Object) getRight()) + ")";
    }

    public SpannableConfirmExtraTextClickable() {
    }

    public SpannableConfirmExtraTextClickable(SpannableString spannableString, SpannableString spannableString2) {
        this.left = spannableString;
        this.right = spannableString2;
    }
}
