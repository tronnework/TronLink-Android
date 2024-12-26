package com.tron.tron_base.frame.view.itoast;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
public class ToastStrategy extends Handler implements IToastStrategy {
    private static final int DELAY_TIMEOUT = 300;
    private static final int MAX_TOAST_CAPACITY = 3;
    private static final int TYPE_CANCEL = 3;
    private static final int TYPE_CONTINUE = 2;
    private static final int TYPE_SHOW = 1;
    private volatile boolean isShow;
    private volatile Queue<CharSequence> mQueue;
    private Toast mToast;

    @Override
    public void bind(Toast toast) {
        this.mToast = toast;
    }

    public ToastStrategy() {
        super(Looper.getMainLooper());
        this.mQueue = getToastQueue();
    }

    @Override
    public void show(CharSequence charSequence) {
        if ((this.mQueue.isEmpty() || !this.mQueue.contains(charSequence)) && !this.mQueue.offer(charSequence)) {
            this.mQueue.poll();
            this.mQueue.offer(charSequence);
        }
        if (this.isShow) {
            return;
        }
        this.isShow = true;
        sendEmptyMessageDelayed(1, 300L);
    }

    @Override
    public void forceShowAgain(CharSequence charSequence) {
        this.mQueue.poll();
        this.mQueue.offer(charSequence);
        sendEmptyMessageDelayed(1, 300L);
    }

    @Override
    public void cancel() {
        if (this.isShow) {
            this.isShow = false;
            sendEmptyMessage(3);
        }
    }

    @Override
    public void handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            CharSequence peek = this.mQueue.peek();
            if (peek == null) {
                this.isShow = false;
                return;
            }
            this.mToast.setText(peek);
            this.mToast.show();
            sendEmptyMessageDelayed(2, getToastDuration(peek) + 300);
        } else if (i != 2) {
            if (i != 3) {
                return;
            }
            this.isShow = false;
            this.mQueue.clear();
            this.mToast.cancel();
        } else {
            this.mQueue.poll();
            if (this.mQueue.isEmpty()) {
                this.isShow = false;
            } else {
                sendEmptyMessage(1);
            }
        }
    }

    public Queue<CharSequence> getToastQueue() {
        return new ArrayBlockingQueue(3);
    }

    public int getToastDuration(CharSequence charSequence) {
        if (charSequence.length() > 20) {
            return IToastStrategy.LONG_DURATION_TIMEOUT;
        }
        return 2000;
    }
}
