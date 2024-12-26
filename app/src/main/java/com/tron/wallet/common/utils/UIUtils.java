package com.tron.wallet.common.utils;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.InputMethodManager;
import com.google.zxing.common.BitMatrix;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.IRequestConstant;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.maintab.MainTabActivity;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.business.web.CommonWebTitleActivity;
import com.tron.wallet.business.welcome.WelcomeActivity;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
import java.util.Locale;
import org.tron.common.crypto.datatypes.Bool;
public class UIUtils {
    private static final int LANDSCAPE = 1;
    private static final int PORTRAIT = 0;
    private static volatile boolean mHasCheckAllScreen;
    private static volatile boolean mIsAllScreenDevice;
    private static volatile Point[] mRealSizes = new Point[2];

    public static int dip2px(float f) {
        return (int) ((f * AppContextUtil.getContext().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dip(float f) {
        return (int) ((f / AppContextUtil.getContext().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int getStatusBarHeight() {
        int identifier = AppContextUtil.getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return AppContextUtil.getContext().getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static int getNavigationBarHeight() {
        Resources resources = AppContextUtil.getContext().getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("navigation_bar_height", "dimen", "android"));
    }

    public static Bitmap drawable2Bitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        return createBitmap;
    }

    public static Drawable bitmap2Drawable(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        new BitmapDrawable(bitmap).setTargetDensity(bitmap.getDensity());
        return new BitmapDrawable(bitmap);
    }

    public static int getWindowHeight(Activity activity) {
        if (!isAllScreenDevice()) {
            return getScreenHeight(activity);
        }
        return getScreenRealHeight(activity);
    }

    public static int getScreenRealHeight(Context context) {
        int i;
        WindowManager windowManager;
        if (context != null) {
            i = context.getResources().getConfiguration().orientation;
        } else {
            i = AppContextUtil.getContext().getResources().getConfiguration().orientation;
        }
        char c = i == 1 ? (char) 0 : (char) 1;
        if (mRealSizes[c] == null) {
            try {
                if (context != null) {
                    windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                } else {
                    windowManager = (WindowManager) AppContextUtil.getContext().getSystemService(Context.WINDOW_SERVICE);
                }
                if (windowManager == null) {
                    return getScreenHeight(context);
                }
                Display defaultDisplay = windowManager.getDefaultDisplay();
                Point point = new Point();
                defaultDisplay.getRealSize(point);
                mRealSizes[c] = point;
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
        return mRealSizes[c].y;
    }

    public static int getScreenHeight(Context context) {
        if (context != null) {
            return context.getResources().getDisplayMetrics().heightPixels;
        }
        return 0;
    }

    public static int getScreenWidth(Context context) {
        if (context != null) {
            return context.getResources().getDisplayMetrics().widthPixels;
        }
        return 0;
    }

    public static boolean isAllScreenDevice() {
        float f;
        int i;
        if (mHasCheckAllScreen) {
            return mIsAllScreenDevice;
        }
        mHasCheckAllScreen = true;
        mIsAllScreenDevice = false;
        try {
            WindowManager windowManager = (WindowManager) AppContextUtil.getContext().getSystemService(Context.WINDOW_SERVICE);
            if (windowManager != null) {
                Display defaultDisplay = windowManager.getDefaultDisplay();
                Point point = new Point();
                defaultDisplay.getRealSize(point);
                if (point.x < point.y) {
                    f = point.x;
                    i = point.y;
                } else {
                    f = point.y;
                    i = point.x;
                }
                if (i / f >= 1.97f) {
                    mIsAllScreenDevice = true;
                }
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return mIsAllScreenDevice;
    }

    public static int getWindowWidth() {
        return ((WindowManager) AppContextUtil.getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
    }

    public static void hideSoftKeyBoard(Activity activity) {
        View peekDecorView;
        if (activity == null || activity.getWindow() == null || (peekDecorView = activity.getWindow().peekDecorView()) == null || peekDecorView.getWindowToken() == null) {
            return;
        }
        try {
            ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 2);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public static int getResource(String str) {
        return AppContextUtil.getContext().getResources().getIdentifier(str, "mipmap", AppContextUtil.getContext().getPackageName());
    }

    public static void copy(String str) {
        copy(str, false);
    }

    public static void copy(String str, boolean z) {
        ((ClipboardManager) AppContextUtil.getContext().getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText(null, str));
        if (z) {
            TaskHandler.getInstance().postClearPrimaryClipMessage();
        }
    }

    public static void clearPrimaryClip() {
        ClipboardManager clipboardManager = (ClipboardManager) AppContextUtil.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        if (Build.VERSION.SDK_INT >= 28) {
            clipboardManager.clearPrimaryClip();
        } else {
            clipboardManager.setPrimaryClip(ClipData.newPlainText(null, null));
        }
    }

    public static boolean checkDeviceHasNavigationBar(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("config_showNavigationBar", Bool.TYPE_NAME, "android");
        boolean z = false;
        boolean z2 = identifier > 0 ? resources.getBoolean(identifier) : false;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            String str = (String) cls.getMethod("get", String.class).invoke(cls, "qemu.hw.mainkeys");
            if (!"1".equals(str)) {
                z = "0".equals(str) ? true : z2;
            }
            return z;
        } catch (Exception unused) {
            return z2;
        }
    }

    public static void changeAppLanguage(Context context, Locale locale, boolean z) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        context.getResources().updateConfiguration(configuration, displayMetrics);
        context.getApplicationContext().getResources().updateConfiguration(configuration, displayMetrics);
        if (z) {
            Intent intent = new Intent(context, WelcomeActivity.class);
            intent.setFlags(268468224);
            context.startActivity(intent);
        }
    }

    public static void toContractDetailProtocol(Context context, String str) {
        String tronScanMainContract = SpAPI.THIS.getCurIsMainChain() ? IRequest.getTronScanMainContract() : IRequest.getTronScanDappContract();
        String resString = StringTronUtil.getResString(R.string.tronscan);
        CommonWebActivityV3.start(context, tronScanMainContract + str + "?" + LanguageUtils.getTronScanLang(), resString, -2, false);
    }

    public static void toAccountApproveAllDetailProtocol(Context context, String str) {
        String tronScanApproveAll = SpAPI.THIS.getCurIsMainChain() ? IRequest.getTronScanApproveAll() : IRequest.getTronScanDappApproveAll();
        String resString = StringTronUtil.getResString(R.string.tronscan);
        CommonWebActivityV3.start(context, tronScanApproveAll + str + "/approval?dapp=tronlink", resString, -2, true);
    }

    public static void toTrc10TokenProtocol(Context context, String str) {
        String tronScanMainToken10 = SpAPI.THIS.getCurIsMainChain() ? IRequest.getTronScanMainToken10() : IRequest.getTronScanDappToken10();
        String resString = StringTronUtil.getResString(R.string.tronscan);
        CommonWebActivityV3.start(context, tronScanMainToken10 + str + "?" + LanguageUtils.getTronScanLang(), resString, -2, false);
    }

    public static void toTrc20TokenDetail(Context context, String str) {
        String tronScanMainToken20 = SpAPI.THIS.getCurIsMainChain() ? IRequest.getTronScanMainToken20() : IRequest.getTronScanDAPPToken20();
        String resString = StringTronUtil.getResString(R.string.tronscan);
        CommonWebActivityV3.start(context, tronScanMainToken20 + str + "?" + LanguageUtils.getTronScanLang(), resString, -2, false);
    }

    public static void toTRXProtocol(Context context) {
        String tronScanMainToken10 = SpAPI.THIS.getCurIsMainChain() ? IRequest.getTronScanMainToken10() : IRequest.getTronScanDappToken10();
        String resString = StringTronUtil.getResString(R.string.tronscan);
        CommonWebActivityV3.start(context, tronScanMainToken10 + IRequestConstant.TRONSCAN_TRX_INTRODUCE + "?" + LanguageUtils.getTronScanLang(), resString, -2, false);
    }

    public static void toProtocol(Context context) {
        if ("2".equals(SpAPI.THIS.useLanguage())) {
            CommonWebActivityV3.start(context, TronConfig.HTML_protocolZH, "", -2, false);
        } else {
            CommonWebActivityV3.start(context, TronConfig.HTML_protocolEN, "", -2, false);
        }
    }

    public static void toHowSign(Context context) {
        if ("2".equals(SpAPI.THIS.useLanguage())) {
            CommonWebActivityV3.start(context, TronConfig.HTML_SignZH, "", -2, false);
        } else {
            CommonWebActivityV3.start(context, TronConfig.HTML_SignEN, "", -2, false);
        }
    }

    public static void toRatingRoles(Context context) {
        if (IRequest.isShasta()) {
            CommonWebActivityV3.start(context, TronConfig.HTML_RatingRoles_SHASTA, "", -2, false);
        } else if (IRequest.isNile()) {
            CommonWebActivityV3.start(context, TronConfig.HTML_RatingRoles_NILE, "", -2, false);
        } else {
            CommonWebActivityV3.start(context, TronConfig.HTML_RatingRoles_MAINNET, "", -2, false);
        }
    }

    public static void toColdWallet(Context context) {
        if ("2".equals(SpAPI.THIS.useLanguage())) {
            CommonWebTitleActivity.start((Activity) context, "file:///android_asset/cloudBrust_zh/coldWallet_zh.html", context.getResources().getString(R.string.cold_brust_title));
        } else {
            CommonWebTitleActivity.start((Activity) context, "file:///android_asset/cloudBrust_en/coldWallet_en.html", context.getResources().getString(R.string.cold_brust_title));
        }
    }

    public static void toHowGetUsdt(Context context) {
        if ("2".equals(SpAPI.THIS.useLanguage())) {
            CommonWebActivityV3.start(context, TronConfig.HTML_howGetUsdt_ZH, "", -2, false);
        } else {
            CommonWebActivityV3.start(context, TronConfig.HTML_howGetUsdt_EN, "", -2, false);
        }
    }

    public static void toHowGetWTRX(Context context) {
        if ("2".equals(SpAPI.THIS.useLanguage())) {
            CommonWebActivityV3.start(context, TronConfig.HTML_howGetWTRX_ZH, "JustSwap", -2, true);
        } else {
            CommonWebActivityV3.start(context, TronConfig.HTML_howGetWTRX_EN, "JustSwap", -2, true);
        }
    }

    public static void toHowGetPermission(Context context) {
        if ("2".equals(SpAPI.THIS.useLanguage())) {
            CommonWebActivityV3.start(context, TronConfig.HTML_mutli_sign_ZH, "", -2, false);
        } else {
            CommonWebActivityV3.start(context, TronConfig.HTML_mutli_sign_EN, "", -2, false);
        }
    }

    public static void startAnima(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "rotation", 0.0f, 360.0f);
        ofFloat.setDuration(2000L);
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(1);
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.start();
    }

    public static void toHowUseLedger(Context context) {
        if ("2".equals(SpAPI.THIS.useLanguage())) {
            CommonWebActivityV3.start(context, TronConfig.HTML_how_use_ledger_ZH, AppContextUtil.getContext().getString(R.string.help), -2, false);
        } else {
            CommonWebActivityV3.start(context, TronConfig.HTML_how_use_ledger_EN, AppContextUtil.getContext().getString(R.string.help), -2, false);
        }
    }

    public static void toCustomTokenDetails(Context context) {
        if ("2".equals(SpAPI.THIS.useLanguage())) {
            CommonWebActivityV3.start(context, TronConfig.HTML_TO_CUSTOM_TOKEN_ZH, AppContextUtil.getContext().getString(R.string.help), -2, false);
        } else {
            CommonWebActivityV3.start(context, TronConfig.HTML_TO_CUSTOM_TOKEN_EN, AppContextUtil.getContext().getString(R.string.help), -2, false);
        }
    }

    public static void toEnergyPenaltyDetails(Context context) {
        if ("2".equals(SpAPI.THIS.useLanguage())) {
            CommonWebActivityV3.start(context, TronConfig.HTML_TO_ENERGY_PENALTY_DETAIL_ZH, AppContextUtil.getContext().getString(R.string.help), -2, false);
        } else {
            CommonWebActivityV3.start(context, TronConfig.HTML_TO_ENERGY_PENALTY_DETAIL, AppContextUtil.getContext().getString(R.string.help), -2, false);
        }
    }

    public static BitMatrix deleteWhite(BitMatrix bitMatrix) {
        int[] enclosingRectangle = bitMatrix.getEnclosingRectangle();
        int i = enclosingRectangle[2] + 10;
        int i2 = enclosingRectangle[3] + 10;
        BitMatrix bitMatrix2 = new BitMatrix(i, i2);
        bitMatrix2.clear();
        for (int i3 = 10; i3 < i; i3++) {
            for (int i4 = 10; i4 < i2; i4++) {
                if (bitMatrix.get(enclosingRectangle[0] + i3, enclosingRectangle[1] + i4)) {
                    bitMatrix2.set(i3, i4);
                }
            }
        }
        return bitMatrix2;
    }

    public static void toMain(Context context) {
        Intent intent = new Intent(context, MainTabActivity.class);
        intent.setFlags(67108864);
        context.startActivity(intent);
    }

    public static void showOrHidePage(View view, View... viewArr) {
        view.setVisibility(View.VISIBLE);
        for (View view2 : viewArr) {
            view2.setVisibility(View.GONE);
        }
    }

    public static void toTronScanTokenReport(Context context) {
        String resString = StringTronUtil.getResString(R.string.tronscan);
        String tronScanTokenReport = IRequest.getTronScanTokenReport();
        if (StringTronUtil.isEmpty(tronScanTokenReport)) {
            tronScanTokenReport = TronConfig.HTML_TRANSCAN_TOKEN_REPORT_URL;
        }
        CommonWebActivityV3.start(context, tronScanTokenReport + "&" + LanguageUtils.getTronScanLang(), resString, -2, true);
    }

    public static void toApproveCheckDetail(Context context) {
        if ("2".equals(SpAPI.THIS.useLanguage())) {
            CommonWebActivityV3.start(context, TronConfig.HTML_APPROVE_CHECKDETAIL_ZH, AppContextUtil.getContext().getString(R.string.help), -2, false);
        } else {
            CommonWebActivityV3.start(context, TronConfig.HTML_APPROVE_CHECKDETAIL_EN, AppContextUtil.getContext().getString(R.string.help), -2, false);
        }
    }

    public static void toTokenCheckPage(Context context) {
        if ("2".equals(SpAPI.THIS.useLanguage())) {
            CommonWebActivityV3.start(context, TronConfig.HTML_TOKEN_CHECK_URL_ZH, AppContextUtil.getContext().getString(R.string.help), -2, false);
        } else {
            CommonWebActivityV3.start(context, TronConfig.HTML_TOKEN_CHECK_URL_EN, AppContextUtil.getContext().getString(R.string.help), -2, false);
        }
    }
}
