package com.tron.wallet.business.pull;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import com.google.gson.Gson;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.maintab.MainTabActivity;
import com.tron.wallet.business.pull.component.PullDetailActivity;
import com.tron.wallet.business.pull.sign.SignParam;
import com.tron.wallet.business.pull.transfer.TransferParam;
import com.tron.wallet.business.welcome.WelcomeActivity;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.Controller.DappBlackListController;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
import org.apache.commons.cli.HelpFormatter;
public class PullActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    public static final String ACTION = "pull.activity";
    private static final String HOST = "pull.activity";
    private static final String PARAM = "param";

    @Override
    protected void setLayout() {
    }

    public static String handlePullActivityNewIntent(Activity activity, Intent intent) {
        if (SpAPI.THIS.isCold()) {
            return null;
        }
        if (!SpAPI.THIS.getCurIsMainChain()) {
            showNetTypeErrorPopup(activity, null);
            try {
                PullResult pullResult = new PullResult();
                PullParam pullParam = (PullParam) new Gson().fromJson(intent.getStringExtra(PullConstants.PARAMS),  PullParam.class);
                if (DeepLinkCheck.isValidDAppUrl(pullParam.getCallbackUrl())) {
                    pullResult.setActionId(pullParam.getActionId());
                    pullResult.setCode(PullResultCode.FAIL_NOT_SUPPORT_NET.getCode());
                    pullResult.setMessage(PullResultCode.FAIL_NOT_SUPPORT_NET.getMessage());
                    PullResultHelper.callBackToDApp(pullParam.getCallbackUrl(), pullResult);
                    return null;
                }
                return null;
            } catch (Exception e) {
                SentryUtil.captureException(e);
                return null;
            }
        }
        String stringExtra = intent.getStringExtra(PullConstants.ACTION);
        if (PullAction.NOT_SUPPORT.getAction().equals(stringExtra)) {
            try {
                int intExtra = intent.getIntExtra("code", -1);
                String stringExtra2 = intent.getStringExtra(PullConstants.RESULT_MESSAGE);
                LogUtils.d("Pull.Activity", "code:" + intExtra + " message:" + stringExtra2);
                if (PullResultCode.FAIL_NOT_SUPPORT_NET.getCode() == intExtra) {
                    showNetTypeErrorPopup(activity, null);
                } else {
                    showDataErrorPopup(activity, null);
                }
                PullResult pullResult2 = new PullResult();
                PullParam pullParam2 = (PullParam) new Gson().fromJson(intent.getStringExtra(PullConstants.PARAMS),  PullParam.class);
                if (DeepLinkCheck.isValidDAppUrl(pullParam2.getCallbackUrl())) {
                    pullResult2.setActionId(pullParam2.getActionId());
                    pullResult2.setCode(intExtra);
                    pullResult2.setMessage(stringExtra2);
                    PullResultHelper.callBackToDApp(pullParam2.getCallbackUrl(), pullResult2);
                    return null;
                }
                return null;
            } catch (Exception e2) {
                SentryUtil.captureException(e2);
                return null;
            }
        } else if (PullAction.ACTION_OPEN_DAPP.getAction().equals(stringExtra)) {
            return intent.getStringExtra("url");
        } else {
            Intent intent2 = new Intent(intent);
            intent2.setClass(activity, PullDetailActivity.class);
            activity.startActivity(intent2);
            return null;
        }
    }

    public static void showNetTypeErrorPopup(Activity activity, final View.OnClickListener onClickListener) {
        AnalyticsHelper.logEvent(AnalyticsHelper.DeepLinkPage.DEEPLINK_POP_SWITCH_NET);
        ConfirmCustomPopupView.getBuilder(activity).setTitle(activity.getString(R.string.pull_net_error_title)).setTitleSize(16).setInfo(activity.getString(R.string.pull_net_error_info)).setConfirmText(activity.getResources().getString(R.string.got_it)).setConfirmListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                PullActivity.lambda$showNetTypeErrorPopup$0(onClickListener, view);
            }
        }).setShowCancelBtn(false).setAutoDismissOutSide(false).build().show();
    }

    public static void lambda$showNetTypeErrorPopup$0(View.OnClickListener onClickListener, View view) {
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    public static void showDataErrorPopup(Activity activity, final View.OnClickListener onClickListener) {
        AnalyticsHelper.logEvent(AnalyticsHelper.DeepLinkPage.DEEPLINK_POP_ERROR_DATA);
        ConfirmCustomPopupView.getBuilder(activity).setTitle(activity.getString(R.string.pull_data_error_title)).setTitleSize(16).setInfo(activity.getString(R.string.pull_data_error_info)).setConfirmText(activity.getResources().getString(R.string.got_it)).setConfirmListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                PullActivity.lambda$showDataErrorPopup$1(onClickListener, view);
            }
        }).setShowCancelBtn(false).setAutoDismissOutSide(false).build().show();
    }

    public static void lambda$showDataErrorPopup$1(View.OnClickListener onClickListener, View view) {
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    @Override
    protected void processData() {
        Intent intent = getIntent();
        if (intent != null && intent.getData() != null) {
            Uri data = intent.getData();
            String scheme = data.getScheme();
            String host = data.getHost();
            String queryParameter = data.getQueryParameter(PARAM);
            LogUtils.d("pull.activity", scheme + HelpFormatter.DEFAULT_OPT_PREFIX + host + HelpFormatter.DEFAULT_OPT_PREFIX + queryParameter);
            if (PullConstants.SCHEME.equals(scheme) && "pull.activity".equals(host) && !StringTronUtil.isEmpty(queryParameter)) {
                parseParam(queryParameter);
                return;
            } else {
                finish();
                return;
            }
        }
        finish();
    }

    private void parseParam(final String str) {
        PullAction fromAction;
        final Gson gson = new Gson();
        try {
            final PullParam pullParam = (PullParam) gson.fromJson(str,  PullParam.class);
            if (StringTronUtil.isEmpty(pullParam.getAction()) && !StringTronUtil.isEmpty(pullParam.getUrl())) {
                fromAction = PullAction.ACTION_OPEN_DAPP;
            } else {
                fromAction = PullAction.fromAction(pullParam.getAction());
            }
            final PullAction pullAction = fromAction;
            if (pullParam != null && pullParam.getUrl() != null && IRequest.isRelease()) {
                final String host = StringTronUtil.getHost(pullParam.getUrl());
                runOnThreeThread(new OnBackground() {
                    @Override
                    public final void doOnBackground() {
                        lambda$parseParam$4(host, str, gson, pullAction, pullParam);
                    }
                });
                return;
            }
            next(str, gson, pullAction, pullParam);
            finish();
        } catch (Throwable th) {
            SentryUtil.captureException(th);
            startActivity(newIntent(str, PullAction.NOT_SUPPORT, PullResultCode.FAIL_TO_PARSE_JSON));
            finish();
        }
    }

    public void lambda$parseParam$4(String str, final String str2, final Gson gson, final PullAction pullAction, final PullParam pullParam) {
        final boolean isBlack = DappBlackListController.getInstance().isBlack(str);
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$parseParam$3(isBlack, str2, gson, pullAction, pullParam);
            }
        });
    }

    public void lambda$parseParam$3(boolean z, String str, Gson gson, PullAction pullAction, PullParam pullParam) {
        if (!z) {
            next(str, gson, pullAction, pullParam);
            finish();
            return;
        }
        showDataErrorPopup(this, new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$parseParam$2(view);
            }
        });
    }

    public void lambda$parseParam$2(View view) {
        finish();
    }

    private void next(String str, Gson gson, PullAction pullAction, PullParam pullParam) {
        if (pullAction != PullAction.ACTION_OPEN_DAPP) {
            PullResultCode checkParams = DeepLinkCheck.checkParams(pullParam);
            if (checkParams == PullResultCode.SUCCESS) {
                int i = fun1.$SwitchMap$com$tron$wallet$business$pull$PullAction[pullAction.ordinal()];
                if (i == 1) {
                    checkParams = DeepLinkCheck.checkTransferParam((TransferParam) gson.fromJson(str,  TransferParam.class));
                } else if (i == 2) {
                    checkParams = DeepLinkCheck.checkSignParam((SignParam) gson.fromJson(str,  SignParam.class));
                }
            }
            if (checkParams != PullResultCode.SUCCESS) {
                startActivity(newIntent(str, PullAction.NOT_SUPPORT, checkParams));
                return;
            }
        }
        int i2 = fun1.$SwitchMap$com$tron$wallet$business$pull$PullAction[pullAction.ordinal()];
        if (i2 == 1) {
            transfer(str, pullParam);
        } else if (i2 == 2) {
            sign(str, pullParam);
        } else if (i2 == 3) {
            openDApp(str, pullParam);
        } else if (i2 == 4) {
            login(str, pullParam);
        } else {
            startActivity(newIntent(str, PullAction.NOT_SUPPORT, PullResultCode.FAIL_NOT_SUPPORT_ACTION));
        }
    }

    public static class fun1 {
        static final int[] $SwitchMap$com$tron$wallet$business$pull$PullAction;

        static {
            int[] iArr = new int[PullAction.values().length];
            $SwitchMap$com$tron$wallet$business$pull$PullAction = iArr;
            try {
                iArr[PullAction.ACTION_TRANSFER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$pull$PullAction[PullAction.ACTION_SIGN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$pull$PullAction[PullAction.ACTION_OPEN_DAPP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$pull$PullAction[PullAction.ACTION_LOGIN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$pull$PullAction[PullAction.NOT_SUPPORT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private Intent newIntent(String str, PullAction pullAction, PullResultCode pullResultCode) {
        Intent intent;
        if (isAppRunning()) {
            intent = new Intent(this, MainTabActivity.class);
        } else {
            intent = new Intent(this, WelcomeActivity.class);
        }
        intent.addFlags(67108864);
        intent.setAction("pull.activity");
        intent.putExtra(PullConstants.ACTION, pullAction.getAction());
        intent.putExtra(PullConstants.PARAMS, str);
        if (pullResultCode != null) {
            intent.putExtra("code", pullResultCode.getCode());
            intent.putExtra(PullConstants.RESULT_MESSAGE, pullResultCode.getMessage());
        }
        return intent;
    }

    private void openDApp(String str, PullParam pullParam) {
        Intent newIntent = newIntent(str, PullAction.ACTION_OPEN_DAPP, null);
        newIntent.putExtra("url", pullParam.getUrl());
        startActivity(newIntent);
    }

    private void login(String str, PullParam pullParam) {
        startActivity(newIntent(str, PullAction.ACTION_LOGIN, null));
    }

    private void sign(String str, PullParam pullParam) {
        startActivity(newIntent(str, PullAction.ACTION_SIGN, null));
    }

    private void transfer(String str, PullParam pullParam) {
        startActivity(newIntent(str, PullAction.ACTION_TRANSFER, null));
    }

    private boolean isAppRunning() {
        int i;
        try {
            ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            if (Build.VERSION.SDK_INT >= 23) {
                i = activityManager.getAppTasks().get(0).getTaskInfo().numActivities;
            } else {
                i = activityManager.getRunningTasks(1).get(0).numActivities;
            }
            return i > 1;
        } catch (Exception e) {
            LogUtils.e(e);
            return false;
        }
    }
}
