package com.tron.wallet.common.bean;

import android.view.View;
import androidx.core.view.GravityCompat;
public class ConfirmExtraTextClickable {
    private boolean clickable;
    private CharSequence left;
    private View.OnClickListener onItemClickListener;
    private CharSequence right;
    private int rightActionIcon;
    private View.OnClickListener rightIconClickListener;
    private int textGravity;

    public CharSequence getLeft() {
        return this.left;
    }

    public View.OnClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public CharSequence getRight() {
        return this.right;
    }

    public int getRightActionIcon() {
        return this.rightActionIcon;
    }

    public View.OnClickListener getRightIconClickListener() {
        return this.rightIconClickListener;
    }

    public int getTextGravity() {
        return this.textGravity;
    }

    public boolean isClickable() {
        return this.clickable;
    }

    public void setClickable(boolean z) {
        this.clickable = z;
    }

    public void setLeft(CharSequence charSequence) {
        this.left = charSequence;
    }

    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        this.onItemClickListener = onClickListener;
    }

    public void setRight(CharSequence charSequence) {
        this.right = charSequence;
    }

    public void setRightActionIcon(int i) {
        this.rightActionIcon = i;
    }

    public void setRightIconClickListener(View.OnClickListener onClickListener) {
        this.rightIconClickListener = onClickListener;
    }

    public void setTextGravity(int i) {
        this.textGravity = i;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof ConfirmExtraTextClickable;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ConfirmExtraTextClickable) {
            ConfirmExtraTextClickable confirmExtraTextClickable = (ConfirmExtraTextClickable) obj;
            if (confirmExtraTextClickable.canEqual(this) && isClickable() == confirmExtraTextClickable.isClickable() && getRightActionIcon() == confirmExtraTextClickable.getRightActionIcon() && getTextGravity() == confirmExtraTextClickable.getTextGravity()) {
                CharSequence left = getLeft();
                CharSequence left2 = confirmExtraTextClickable.getLeft();
                if (left != null ? left.equals(left2) : left2 == null) {
                    CharSequence right = getRight();
                    CharSequence right2 = confirmExtraTextClickable.getRight();
                    if (right != null ? right.equals(right2) : right2 == null) {
                        View.OnClickListener rightIconClickListener = getRightIconClickListener();
                        View.OnClickListener rightIconClickListener2 = confirmExtraTextClickable.getRightIconClickListener();
                        if (rightIconClickListener != null ? rightIconClickListener.equals(rightIconClickListener2) : rightIconClickListener2 == null) {
                            View.OnClickListener onItemClickListener = getOnItemClickListener();
                            View.OnClickListener onItemClickListener2 = confirmExtraTextClickable.getOnItemClickListener();
                            return onItemClickListener != null ? onItemClickListener.equals(onItemClickListener2) : onItemClickListener2 == null;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int rightActionIcon = (((((isClickable() ? 79 : 97) + 59) * 59) + getRightActionIcon()) * 59) + getTextGravity();
        CharSequence left = getLeft();
        int hashCode = (rightActionIcon * 59) + (left == null ? 43 : left.hashCode());
        CharSequence right = getRight();
        int hashCode2 = (hashCode * 59) + (right == null ? 43 : right.hashCode());
        View.OnClickListener rightIconClickListener = getRightIconClickListener();
        int hashCode3 = (hashCode2 * 59) + (rightIconClickListener == null ? 43 : rightIconClickListener.hashCode());
        View.OnClickListener onItemClickListener = getOnItemClickListener();
        return (hashCode3 * 59) + (onItemClickListener != null ? onItemClickListener.hashCode() : 43);
    }

    public String toString() {
        return "ConfirmExtraTextClickable(left=" + ((Object) getLeft()) + ", right=" + ((Object) getRight()) + ", clickable=" + isClickable() + ", rightActionIcon=" + getRightActionIcon() + ", rightIconClickListener=" + getRightIconClickListener() + ", onItemClickListener=" + getOnItemClickListener() + ", textGravity=" + getTextGravity() + ")";
    }

    public ConfirmExtraTextClickable() {
        this.clickable = false;
        this.rightActionIcon = 0;
        this.textGravity = GravityCompat.END;
    }

    public ConfirmExtraTextClickable(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, View.OnClickListener onClickListener, View.OnClickListener onClickListener2, int i2) {
        this.left = charSequence;
        this.right = charSequence2;
        this.clickable = z;
        this.rightActionIcon = i;
        this.rightIconClickListener = onClickListener;
        this.onItemClickListener = onClickListener2;
        this.textGravity = i2;
    }
}
