package com.tron.wallet.common.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tronlinkpro.wallet.R;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.annotation.Nullable;
public class ImageUtils {
    private static int screenWidth;

    private static double FormetFileSize(long j, int i) {
        double d;
        double d2;
        if (i != 1) {
            if (i == 2) {
                d = j;
                d2 = 1024.0d;
            } else if (i == 3) {
                d = j;
                d2 = 1048576.0d;
            } else if (i != 4) {
                return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            } else {
                d = j;
                d2 = 1.073741824E9d;
            }
            return d / d2;
        }
        return j;
    }

    public static float getRatio(float f, float f2) {
        return (f2 / f) * 1.0f;
    }

    public static int getMeasureHeight(float f, float f2) {
        getWindowWidth();
        int width = (int) (getWidth(f, f2) * getRatio(f, f2));
        float dip2px = screenWidth - (UIUtils.dip2px(15.0f) * 2);
        int i = (int) (0.5f * dip2px);
        int i2 = (int) (dip2px * 1.2f);
        if (width < i) {
            width = i;
        } else if (width > i2) {
            width = i2;
        }
        if (width > 0) {
            return width;
        }
        return 0;
    }

    public static int getWidth(float f, float f2) {
        getWindowWidth();
        if (f > f2) {
            return ((screenWidth - (UIUtils.dip2px(15.0f) * 2)) * 2) / 3;
        }
        if (f < f2) {
            return (screenWidth - (UIUtils.dip2px(15.0f) * 2)) / 2;
        }
        return (screenWidth - (UIUtils.dip2px(15.0f) * 2)) / 2;
    }

    private static void getWindowWidth() {
        if (screenWidth == 0) {
            screenWidth = ((WindowManager) AppContextUtil.getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
        }
    }

    public static String saveImage(Context context, Bitmap bitmap, String str) {
        if (str == null) {
            try {
                str = com.tron.tron_base.frame.utils.FileUtils.getDir(context);
            } catch (IOException e) {
                LogUtils.e(e);
                return null;
            }
        }
        File file = new File(str);
        if (!file.exists()) {
            file.mkdir();
        }
        File file2 = new File(file, System.currentTimeMillis() + ".jpg");
        FileOutputStream fileOutputStream = new FileOutputStream(file2);
        boolean compress = bitmap.compress(Bitmap.CompressFormat.JPEG, 60, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        if (compress) {
            return file2.getAbsolutePath();
        }
        return null;
    }

    public static boolean saveImageToGallery(Context context, Bitmap bitmap) {
        String saveImage = saveImage(context, bitmap, null);
        if (saveImage != null) {
            context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(new File(saveImage))));
            return true;
        }
        return false;
    }

    public static boolean saveBitmapPhoto(Context context, Bitmap bitmap) {
        if (Build.VERSION.SDK_INT < 29) {
            return saveImageToGallery(context, bitmap);
        }
        ContentResolver contentResolver = context.getApplicationContext().getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_display_name", System.currentTimeMillis() + ".jpg");
        contentValues.put("mime_type", "image/jpeg");
        contentValues.put("date_added", Long.valueOf(System.currentTimeMillis() / 1000));
        contentValues.put("date_modified", Long.valueOf(System.currentTimeMillis() / 1000));
        Uri insert = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        if (insert != null) {
            try {
                OutputStream openOutputStream = contentResolver.openOutputStream(insert);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, openOutputStream);
                openOutputStream.flush();
                openOutputStream.close();
                insert.getPath();
                return true;
            } catch (FileNotFoundException e) {
                LogUtils.e(e);
                return false;
            } catch (IOException e2) {
                LogUtils.e(e2);
                return false;
            }
        }
        return false;
    }

    public static void imageLoad_Fresco(SimpleDraweeView simpleDraweeView, String str) {
        try {
            simpleDraweeView.setImageURI(Uri.parse(str));
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public static void imageLoad_background(final SimpleDraweeView simpleDraweeView, String str) {
        final GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(AppContextUtil.getContext().getResources().getColor(R.color.gray_e));
        gradientDrawable.setCornerRadius(UIUtils.dip2px(10.0f));
        simpleDraweeView.getHierarchy().setBackgroundImage(gradientDrawable);
        simpleDraweeView.setController(Fresco.newDraweeControllerBuilder().setControllerListener(new BaseControllerListener<ImageInfo>() {
            @Override
            public void onIntermediateImageSet(String str2, ImageInfo imageInfo) {
            }

            @Override
            public void onFinalImageSet(String str2, ImageInfo imageInfo, Animatable animatable) {
                if (imageInfo == null) {
                    return;
                }
                GradientDrawable gradientDrawable2 = new GradientDrawable();
                gradientDrawable2.setColor(AppContextUtil.getContext().getResources().getColor(R.color.white));
                gradientDrawable2.setCornerRadius(UIUtils.dip2px(10.0f));
                SimpleDraweeView.this.getHierarchy().setBackgroundImage(gradientDrawable2);
                RoundingParams roundingParams = new RoundingParams();
                roundingParams.setCornersRadius(UIUtils.dip2px(10.0f));
                SimpleDraweeView.this.getHierarchy().setRoundingParams(roundingParams);
            }

            @Override
            public void onFailure(String str2, Throwable th) {
                SimpleDraweeView.this.getHierarchy().setBackgroundImage(gradientDrawable);
            }
        }).setUri(str).build());
    }

    public static double getFileSize(String str, int i) {
        try {
            return FormetFileSize(getFileSize(new File(str)), i);
        } catch (Exception e) {
            LogUtils.e(e);
            return 1000.0d;
        }
    }

    public static void imageLoad_corner(final SimpleDraweeView simpleDraweeView, String str, final int i) {
        final GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(AppContextUtil.getContext().getResources().getColor(R.color.gray_e));
        gradientDrawable.setCornerRadius(UIUtils.dip2px(i));
        simpleDraweeView.getHierarchy().setBackgroundImage(gradientDrawable);
        simpleDraweeView.setController(Fresco.newDraweeControllerBuilder().setControllerListener(new BaseControllerListener<ImageInfo>() {
            @Override
            public void onIntermediateImageSet(String str2, ImageInfo imageInfo) {
            }

            @Override
            public void onFinalImageSet(String str2, ImageInfo imageInfo, Animatable animatable) {
                if (imageInfo == null) {
                    return;
                }
                GradientDrawable gradientDrawable2 = new GradientDrawable();
                gradientDrawable2.setColor(AppContextUtil.getContext().getResources().getColor(R.color.white));
                gradientDrawable2.setCornerRadius(UIUtils.dip2px(i));
                simpleDraweeView.getHierarchy().setBackgroundImage(gradientDrawable2);
                RoundingParams roundingParams = new RoundingParams();
                roundingParams.setCornersRadius(UIUtils.dip2px(i));
                simpleDraweeView.getHierarchy().setRoundingParams(roundingParams);
                ViewGroup.LayoutParams layoutParams = simpleDraweeView.getLayoutParams();
                int screenWidth2 = UIUtils.getScreenWidth(simpleDraweeView.getContext()) - UIUtils.dip2px(20.0f);
                layoutParams.width = screenWidth2;
                layoutParams.height = (int) (screenWidth2 * (imageInfo.getHeight() / imageInfo.getWidth()));
                simpleDraweeView.setLayoutParams(layoutParams);
            }

            @Override
            public void onFailure(String str2, Throwable th) {
                simpleDraweeView.getHierarchy().setBackgroundImage(gradientDrawable);
            }
        }).setUri(str).build());
    }

    private static long getFileSize(File file) throws Exception {
        if (file.exists()) {
            return file.length();
        }
        file.createNewFile();
        return 0L;
    }

    public static void loadAsCircle(SimpleDraweeView simpleDraweeView, String str) {
        simpleDraweeView.getHierarchy().setRoundingParams(RoundingParams.asCircle());
        simpleDraweeView.setImageURI(str);
    }

    public static void loadAsCircleByRadius(SimpleDraweeView simpleDraweeView, String str, float f) {
        RoundingParams asCircle = RoundingParams.asCircle();
        asCircle.setCornersRadius(f);
        simpleDraweeView.getHierarchy().setRoundingParams(asCircle);
        simpleDraweeView.setImageURI(str);
    }

    public static void loadAsCircleResource(SimpleDraweeView simpleDraweeView, int i) {
        simpleDraweeView.getHierarchy().setRoundingParams(RoundingParams.asCircle());
        simpleDraweeView.setImageResource(i);
    }

    public static void loadImageWithHeightThreshold(final SimpleDraweeView simpleDraweeView, String str, final int i, final int i2) {
        simpleDraweeView.setController(Fresco.newDraweeControllerBuilder().setControllerListener(new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(String str2, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable) {
                if (imageInfo == null) {
                    return;
                }
                int min = Math.min(Math.max(i, imageInfo.getHeight()), i2);
                ViewGroup.LayoutParams layoutParams = simpleDraweeView.getLayoutParams();
                layoutParams.height = min;
                simpleDraweeView.setLayoutParams(layoutParams);
                if (animatable != null) {
                    animatable.start();
                }
            }
        }).setUri(str).build());
    }
}
