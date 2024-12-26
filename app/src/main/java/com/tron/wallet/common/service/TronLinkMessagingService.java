package com.tron.wallet.common.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxBus;
import com.tron.wallet.TronApplicationExternalSyntheticApiModelOutline0;
import com.tron.wallet.business.maintab.MainTabActivity;
import com.tron.wallet.business.message.MessageCenterActivity;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
public class TronLinkMessagingService extends FirebaseMessagingService {
    private static final String TAG = "TronLinkMessaging";

    private void handleNow() {
    }

    private void scheduleJob() {
    }

    private void sendRegistrationToServer(String str) {
    }

    @Override
    public void onCreate() {
        LogUtils.d(TAG, "onCreate");
        super.onCreate();
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(Task<String> task) {
                if (!task.isSuccessful()) {
                    LogUtils.w(TronLinkMessagingService.TAG, "Fetching FCM registration token failed" + task.getException());
                    return;
                }
                String result = task.getResult();
                LogUtils.d(TronLinkMessagingService.TAG, "TOKEN： " + result);
                if (SpAPI.THIS.getFireBaseToken().equals(result)) {
                    return;
                }
                SpAPI.THIS.setFireBaseToken(result);
            }
        });
    }

    @Override
    public void onNewToken(String str) {
        LogUtils.d(TAG, "Refreshed token: " + str);
        RxBus.getInstance().postSticky(Event.MESSAGING_FIREBASE_TOKEN, str);
        SpAPI.THIS.setFireBaseToken(str);
        sendRegistrationToServer(str);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        LogUtils.d(TAG, "onMessageReceived: " + remoteMessage.getMessageId() + remoteMessage.getMessageType());
        StringBuilder sb = new StringBuilder("From: ");
        sb.append(remoteMessage.getFrom());
        LogUtils.d(TAG, sb.toString());
        RemoteMessage.Notification notification = remoteMessage.getNotification();
        if (notification != null) {
            LogUtils.d(TAG, "RemoteMessage.Notification: " + notification.getChannelId() + "  " + notification.getTitle() + "  " + notification.getBody());
        }
        if (remoteMessage.getData().size() > 0) {
            LogUtils.d(TAG, "Message data payload: " + remoteMessage.getData());
            scheduleJob();
        }
        if (remoteMessage.getNotification() != null) {
            LogUtils.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(Task<InstanceIdResult> task) {
                if (!task.isSuccessful()) {
                    LogUtils.w(TronLinkMessagingService.TAG, "getInstanceId failed" + task.getException());
                    return;
                }
                LogUtils.e("Google_token", task.getResult().getToken());
            }
        });
        if (remoteMessage.getNotification() != null && remoteMessage.getNotification().getBody() != null) {
            sendNotification(getApplicationContext(), remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        } else {
            sendNotification(getApplicationContext(), remoteMessage.getData().get("title"), remoteMessage.getData().get("body"));
        }
    }

    @Override
    public void onMessageSent(String str) {
        super.onMessageSent(str);
    }

    @Override
    public void onSendError(String str, Exception exc) {
        super.onSendError(str, exc);
    }

    private void sendNotification(Context context, String str, String str2) {
        Intent intent;
        PendingIntent activity;
        if (SpAPI.THIS.isShasta() || !SpAPI.THIS.getCurIsMainChain() || IRequest.isNile()) {
            intent = new Intent(this, MainTabActivity.class);
        } else {
            intent = new Intent(this, MessageCenterActivity.class);
        }
        intent.addFlags(67108864);
        intent.putExtra(TronConfig.NOTIFICATION_IN, true);
        if (Build.VERSION.SDK_INT >= 23) {
            activity = PendingIntent.getActivity(this, 0, intent, 67108864);
        } else {
            activity = PendingIntent.getActivity(this, 0, intent, 134217728);
        }
        String string = getString(R.string.default_notification_channel_id);
        NotificationCompat.Builder contentIntent = new NotificationCompat.Builder(this, string).setTicker(str).setSmallIcon(R.mipmap.ic_launcher).setContentTitle(str).setContentText(str2).setAutoCancel(true).setSound(RingtoneManager.getDefaultUri(2)).setContentIntent(activity);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= 26) {
            notificationManager.createNotificationChannel(TronApplicationExternalSyntheticApiModelOutline0.m(string, str, 3));
        }
        int firebaseNotificationID = SpAPI.THIS.getFirebaseNotificationID();
        notificationManager.notify(firebaseNotificationID, contentIntent.build());
        SpAPI.THIS.setFirebaseNotificationID(firebaseNotificationID + 1);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.w(TAG, "onDestroy");
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
        LogUtils.d(TAG, "onDeletedMessages  ");
    }
}
