package com.tron.wallet.common.components.mnemonicflowlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;
import java.util.Timer;
import java.util.TimerTask;
public class TagScrollView extends ScrollView {
    boolean isLongClickModule;
    float startX;
    float startY;
    Timer timer;
    TouchListener touchListener;

    public interface TouchListener {
        void longTouch();

        void touch();
    }

    @Override
    public boolean canScrollVertically(int i) {
        return true;
    }

    public void setTouchListener(TouchListener touchListener) {
        this.touchListener = touchListener;
    }

    public TagScrollView(Context context) {
        super(context);
        this.isLongClickModule = false;
    }

    public TagScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isLongClickModule = false;
    }

    public TagScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isLongClickModule = false;
    }

    public TagScrollView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.isLongClickModule = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        Timer timer;
        int action = motionEvent.getAction();
        if (action == 0) {
            TouchListener touchListener = this.touchListener;
            if (touchListener != null) {
                touchListener.touch();
            }
            this.startX = motionEvent.getX();
            this.startY = motionEvent.getY();
            Timer timer2 = new Timer();
            this.timer = timer2;
            timer2.schedule(new TimerTask() {
                @Override
                public void run() {
                    isLongClickModule = true;
                    if (touchListener != null) {
                        touchListener.longTouch();
                    }
                }
            }, 500L);
        } else if (action == 1) {
            this.isLongClickModule = false;
        } else if (action == 2) {
            if (Math.sqrt(((motionEvent.getX() - this.startX) * (motionEvent.getX() - this.startX)) + ((motionEvent.getY() - this.startY) * (motionEvent.getY() - this.startY))) > 10.0d && (timer = this.timer) != null) {
                timer.cancel();
                this.timer = null;
            }
            if (this.isLongClickModule) {
                super.onTouchEvent(motionEvent);
            }
        }
        return super.onTouchEvent(motionEvent);
    }
}
