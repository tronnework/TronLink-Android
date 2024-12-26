package com.tron.wallet.business.transfer.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import androidx.core.content.FileProvider;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AppUtils;
import com.tron.wallet.common.utils.ImageUtils;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.io.File;
public class ShareHelper {
    private String sharedPath;

    private ShareHelper() {
    }

    public static ShareHelper getInstance() {
        return Nested.instance;
    }

    public static class Nested {
        static ShareHelper instance = new ShareHelper();

        private Nested() {
        }
    }

    public Observable<String> cacheBitmapAndSave(final Activity activity, final View view, final View view2) {
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$cacheBitmapAndSave$0(view, view2, activity, observableEmitter);
            }
        }).compose(RxSchedulers2.io_main());
    }

    public void lambda$cacheBitmapAndSave$0(View view, View view2, Activity activity, ObservableEmitter observableEmitter) throws Exception {
        Exception exc = new Exception("Fail to generate image.");
        Bitmap takeContentBitmap = takeContentBitmap(view, view2);
        if (takeContentBitmap != null) {
            ImageUtils.saveBitmapPhoto(activity, takeContentBitmap);
            String saveImage = ImageUtils.saveImage(view.getContext(), takeContentBitmap, Build.VERSION.SDK_INT >= 24 ? activity.getCacheDir().getAbsolutePath() : null);
            if (saveImage == null) {
                observableEmitter.onError(exc);
            } else {
                observableEmitter.onNext(saveImage);
            }
        } else {
            observableEmitter.onError(exc);
        }
        observableEmitter.onComplete();
    }

    public Observable<String> cacheBitmapSave(final Activity activity, final View view, final View view2) {
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$cacheBitmapSave$1(view, view2, activity, observableEmitter);
            }
        }).compose(RxSchedulers2.io_main());
    }

    public void lambda$cacheBitmapSave$1(View view, View view2, Activity activity, ObservableEmitter observableEmitter) throws Exception {
        Exception exc = new Exception("Fail to generate image.");
        Bitmap takeContentBitmap = takeContentBitmap(view, view2);
        if (takeContentBitmap != null) {
            if (!ImageUtils.saveBitmapPhoto(activity, takeContentBitmap)) {
                observableEmitter.onError(exc);
            } else {
                observableEmitter.onNext("");
            }
        } else {
            observableEmitter.onError(exc);
        }
        observableEmitter.onComplete();
    }

    public void shareImage(final Activity activity, View view, View view2, final Action action) {
        cacheBitmapAndSave(activity, view, view2).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$shareImage$2(activity, (String) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                ShareHelper.lambda$shareImage$3(Action.this, (Throwable) obj);
            }
        });
    }

    public void lambda$shareImage$2(Activity activity, String str) throws Exception {
        shareImageActual(activity, str);
        this.sharedPath = str;
    }

    public static void lambda$shareImage$3(Action action, Throwable th) throws Exception {
        if (action != null) {
            action.run();
        }
        LogUtils.e(th);
    }

    public void shareImageActual(Activity activity, String str) {
        Uri fromFile;
        Intent intent = new Intent("android.intent.action.SEND");
        File file = new File(str);
        if (Build.VERSION.SDK_INT >= 24) {
            intent.setFlags(1);
            fromFile = FileProvider.getUriForFile(activity, AppUtils.getAppPackageName() + ".fileprovider", file);
        } else {
            fromFile = Uri.fromFile(file);
        }
        intent.setType("image/*");
        intent.putExtra("android.intent.extra.STREAM", fromFile);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivityForResult(Intent.createChooser(intent, activity.getResources().getString(R.string.user_guide7)), 10001);
    }

    public void handleActivityResult(int i, int i2, Intent intent) {
        if (i != 10001 || this.sharedPath == null) {
            return;
        }
        try {
            File file = new File(this.sharedPath);
            if (file.exists()) {
                file.delete();
                LogUtils.w("ShareHelper", "handleActivityResult: delete file: " + this.sharedPath);
            }
        } catch (Exception e) {
            SentryUtil.captureException(e);
        }
    }

    public void shareUrl(Context context, String str) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.TEXT", str);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setType("text/plain");
        context.startActivity(Intent.createChooser(intent, context.getResources().getString(R.string.user_guide7)));
    }

    public void shareUrlWithDecor(Context context, String str, String str2) {
        Uri build = Uri.parse(TronConfig.SHARE_URL).buildUpon().appendQueryParameter("dapp_url", str).appendQueryParameter("dapp_name", str2).build();
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.TEXT", build.toString());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setType("text/plain");
        context.startActivity(Intent.createChooser(intent, context.getResources().getString(R.string.user_guide7)));
    }

    private Bitmap takeContentBitmap(View view, View view2) {
        Bitmap bitmap = null;
        try {
            bitmap = Bitmap.createBitmap(view2.getWidth(), view2.getHeight(), Bitmap.Config.ARGB_8888);
            view.draw(new Canvas(bitmap));
            return bitmap;
        } catch (Throwable th) {
            SentryUtil.captureException(th);
            LogUtils.e(th);
            return bitmap;
        }
    }
}
