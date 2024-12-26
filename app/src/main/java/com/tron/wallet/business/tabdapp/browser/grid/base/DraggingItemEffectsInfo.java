package com.tron.wallet.business.tabdapp.browser.grid.base;

import android.view.animation.Interpolator;
public class DraggingItemEffectsInfo {
    public int durationMillis;
    public float scale = 1.0f;
    public float rotation = 0.0f;
    public float alpha = 1.0f;
    public Interpolator scaleInterpolator = null;
    public Interpolator rotationInterpolator = null;
    public Interpolator alphaInterpolator = null;

    public float getAlpha() {
        return this.alpha;
    }

    public Interpolator getAlphaInterpolator() {
        return this.alphaInterpolator;
    }

    public int getDurationMillis() {
        return this.durationMillis;
    }

    public float getRotation() {
        return this.rotation;
    }

    public Interpolator getRotationInterpolator() {
        return this.rotationInterpolator;
    }

    public float getScale() {
        return this.scale;
    }

    public Interpolator getScaleInterpolator() {
        return this.scaleInterpolator;
    }

    public void setAlpha(float f) {
        this.alpha = f;
    }

    public void setAlphaInterpolator(Interpolator interpolator) {
        this.alphaInterpolator = interpolator;
    }

    public void setDurationMillis(int i) {
        this.durationMillis = i;
    }

    public void setRotation(float f) {
        this.rotation = f;
    }

    public void setRotationInterpolator(Interpolator interpolator) {
        this.rotationInterpolator = interpolator;
    }

    public void setScale(float f) {
        this.scale = f;
    }

    public void setScaleInterpolator(Interpolator interpolator) {
        this.scaleInterpolator = interpolator;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof DraggingItemEffectsInfo;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DraggingItemEffectsInfo) {
            DraggingItemEffectsInfo draggingItemEffectsInfo = (DraggingItemEffectsInfo) obj;
            if (draggingItemEffectsInfo.canEqual(this) && getDurationMillis() == draggingItemEffectsInfo.getDurationMillis() && Float.compare(getScale(), draggingItemEffectsInfo.getScale()) == 0 && Float.compare(getRotation(), draggingItemEffectsInfo.getRotation()) == 0 && Float.compare(getAlpha(), draggingItemEffectsInfo.getAlpha()) == 0) {
                Interpolator scaleInterpolator = getScaleInterpolator();
                Interpolator scaleInterpolator2 = draggingItemEffectsInfo.getScaleInterpolator();
                if (scaleInterpolator != null ? scaleInterpolator.equals(scaleInterpolator2) : scaleInterpolator2 == null) {
                    Interpolator rotationInterpolator = getRotationInterpolator();
                    Interpolator rotationInterpolator2 = draggingItemEffectsInfo.getRotationInterpolator();
                    if (rotationInterpolator != null ? rotationInterpolator.equals(rotationInterpolator2) : rotationInterpolator2 == null) {
                        Interpolator alphaInterpolator = getAlphaInterpolator();
                        Interpolator alphaInterpolator2 = draggingItemEffectsInfo.getAlphaInterpolator();
                        return alphaInterpolator != null ? alphaInterpolator.equals(alphaInterpolator2) : alphaInterpolator2 == null;
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
        int durationMillis = ((((((getDurationMillis() + 59) * 59) + Float.floatToIntBits(getScale())) * 59) + Float.floatToIntBits(getRotation())) * 59) + Float.floatToIntBits(getAlpha());
        Interpolator scaleInterpolator = getScaleInterpolator();
        int hashCode = (durationMillis * 59) + (scaleInterpolator == null ? 43 : scaleInterpolator.hashCode());
        Interpolator rotationInterpolator = getRotationInterpolator();
        int hashCode2 = (hashCode * 59) + (rotationInterpolator == null ? 43 : rotationInterpolator.hashCode());
        Interpolator alphaInterpolator = getAlphaInterpolator();
        return (hashCode2 * 59) + (alphaInterpolator != null ? alphaInterpolator.hashCode() : 43);
    }

    public String toString() {
        return "DraggingItemEffectsInfo(durationMillis=" + getDurationMillis() + ", scale=" + getScale() + ", rotation=" + getRotation() + ", alpha=" + getAlpha() + ", scaleInterpolator=" + getScaleInterpolator() + ", rotationInterpolator=" + getRotationInterpolator() + ", alphaInterpolator=" + getAlphaInterpolator() + ")";
    }
}
