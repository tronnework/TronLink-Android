package com.tron.wallet.common.utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.gson.Gson;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.common.bean.FailBean;
import com.tron.wallet.common.components.dialog.CustomDialog;
import com.tron.wallet.common.config.ResultCodeContant;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.HttpAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
public class FailUtils {
    public static void sendFail(GrpcAPI.Return r3, Protocol.Transaction transaction, String str) {
        try {
            FailBean failBean = new FailBean();
            failBean.setOwnerAddress(str);
            if (transaction != null && transaction.toString().length() > 0) {
                failBean.setTransaction(WalletUtils.printTransactionToJSONs(transaction).toJSONString());
            } else {
                failBean.setTransaction("");
            }
            if (r3 == null) {
                failBean.setFailLog("validTransactionError");
                failBean.setStatus("0");
            } else {
                failBean.setFailLog(WalletUtils.printReturnToJSONs(r3));
                failBean.setStatus(r3.getCode() + "");
            }
            String json = new Gson().toJson(failBean);
            LogUtils.e("qys", "sendFail: " + json);
            transaction_fail(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)).subscribe(new IObserver(new ICallback<Object>() {
                @Override
                public void onFailure(String str2, String str3) {
                }

                @Override
                public void onResponse(String str2, Object obj) {
                }

                @Override
                public void onSubscribe(Disposable disposable) {
                }
            }, "Tag"));
        } catch (Exception e) {
            LogUtils.e(e);
            SentryUtil.captureException(e);
        }
    }

    public static void sendFail(String str, String str2, String str3, String str4) {
        FailBean failBean = new FailBean();
        failBean.setOwnerAddress(str3);
        failBean.setTransaction(str2);
        failBean.setStatus(str);
        failBean.setFailLog(str4);
        String json = new Gson().toJson(failBean);
        LogUtils.e("qys", "sendFail-node: " + json);
        transaction_fail(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)).subscribe(new IObserver(new ICallback<Object>() {
            @Override
            public void onFailure(String str5, String str6) {
            }

            @Override
            public void onResponse(String str5, Object obj) {
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                new RxManager().add(disposable);
            }
        }, "NodeTag"));
    }

    public static Observable<Object> transaction_fail(RequestBody requestBody) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).fail_transfer(requestBody).compose(RxSchedulers.io_main());
    }

    public static void showFailDialog(Activity activity, GrpcAPI.Return r2) {
        showFailDialog(activity, r2, -1);
    }

    public static void showErrorCodeDialog(final BaseActivity baseActivity, int i, int i2, String str, String str2) {
        CustomDialog.Builder builder = new CustomDialog.Builder(baseActivity);
        final CustomDialog build = builder.style(R.style.loading_dialog).cancelTouchout(true).heightDimenRes(-2).widthDimenRes(-1).setGravity(17).view(R.layout.result_code_dialog).build();
        build.setCanceledOnTouchOutside(false);
        View view = builder.getView();
        TextView textView = (TextView) view.findViewById(R.id.tv_subtitle);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_ok2);
        ((TextView) view.findViewById(R.id.tv_error_code)).setText(str);
        ((TextView) view.findViewById(R.id.tv_content)).setText(i2);
        if (!TextUtils.isEmpty(str2)) {
            textView.setText(str2);
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.GONE);
        }
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                CustomDialog.this.dismiss();
                baseActivity.exit();
            }
        });
        build.show();
    }

    public static void showFailDialog(final android.app.Activity r10, org.tron.api.GrpcAPI.Return r11, int r12) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.common.utils.FailUtils.showFailDialog(android.app.Activity, org.tron.api.GrpcAPI$Return, int):void");
    }

    public static void showMsendErrorDialog(int i, Context context) {
        if (i == 20304) {
            showErrorCodeDialog((BaseActivity) context, ResultCodeContant.CODE_20304, R.string.sign_fail, ResultCodeContant.ERROR_20304, "");
        } else if (i == 20302) {
            showErrorCodeDialog((BaseActivity) context, ResultCodeContant.CODE_20302, R.string.transcation_sign_fail, ResultCodeContant.ERROR_20302, "");
        } else if (i == 20303) {
            showErrorCodeDialog((BaseActivity) context, ResultCodeContant.CODE_20303, R.string.sign_fail, ResultCodeContant.ERROR_20303, "");
        } else if (i == 20305) {
            showErrorCodeDialog((BaseActivity) context, ResultCodeContant.CODE_20305, R.string.sign_fail, ResultCodeContant.ERROR_20305, "");
        } else if (i == 20320) {
            showErrorCodeDialog((BaseActivity) context, ResultCodeContant.CODE_20320, R.string.sign_fail, ResultCodeContant.ERROR_20320, "");
        } else if (i == 10001) {
            showErrorCodeDialog((BaseActivity) context, 10001, R.string.sign_fail, ResultCodeContant.ERROR_10001, "");
        } else if (i == 40001) {
            showErrorCodeDialog((BaseActivity) context, ResultCodeContant.CODE_40001, R.string.sign_error, ResultCodeContant.ERROR_40001, "");
        } else if (i == 40002) {
            showErrorCodeDialog((BaseActivity) context, ResultCodeContant.CODE_40002, R.string.contract_verification_error, ResultCodeContant.ERROR_40002, "");
        } else if (i == 40003) {
            showErrorCodeDialog((BaseActivity) context, ResultCodeContant.CODE_40003, R.string.contract_execution_failed, ResultCodeContant.ERROR_40003, "");
        } else if (i == 40004) {
            showErrorCodeDialog((BaseActivity) context, ResultCodeContant.CODE_40004, R.string.insufficient_bandwidth_tips, ResultCodeContant.ERROR_40004, "");
        } else if (i == 40005) {
            showErrorCodeDialog((BaseActivity) context, ResultCodeContant.CODE_40005, R.string.dulipcated_transaction, ResultCodeContant.ERROR_40005, "");
        } else if (i == 40006) {
            showErrorCodeDialog((BaseActivity) context, ResultCodeContant.CODE_40006, R.string.transaction_broadcast_fail, ResultCodeContant.ERROR_40006, "");
        } else if (i == 40007) {
            showErrorCodeDialog((BaseActivity) context, ResultCodeContant.CODE_40007, R.string.data_too_large, ResultCodeContant.ERROR_40007, "");
        } else if (i == 40008) {
            showErrorCodeDialog((BaseActivity) context, ResultCodeContant.CODE_40008, R.string.broadcast_expired, ResultCodeContant.ERROR_40008, "");
        } else if (i == 40009) {
            showErrorCodeDialog((BaseActivity) context, ResultCodeContant.CODE_40009, R.string.sign_fail, ResultCodeContant.ERROR_40009, "");
        } else if (i == 40010) {
            showErrorCodeDialog((BaseActivity) context, ResultCodeContant.CODE_40010, R.string.node_connection_failed, ResultCodeContant.ERROR_40010, "");
        } else if (i == 40011) {
            showErrorCodeDialog((BaseActivity) context, ResultCodeContant.CODE_40011, R.string.broadcast_no_enough, ResultCodeContant.ERROR_40011, "");
        } else {
            showErrorCodeDialog((BaseActivity) context, ResultCodeContant.CODE_40012, R.string.other_error, ResultCodeContant.ERROR_40012, "");
        }
    }

    public static void showSamsungKeystoreUnAvailableDialog(final Activity activity, int i) {
        if (i == 2) {
            IToast.getIToast().show(R.string.transaction_type_not_support);
            activity.finish();
        } else if (i == 1) {
            IToast.getIToast().show(R.string.keypair_not_match);
            activity.finish();
        } else {
            CustomDialog.Builder builder = new CustomDialog.Builder(activity);
            final CustomDialog build = builder.style(R.style.loading_dialog).cancelTouchout(true).heightDimenRes(-2).widthDimenRes(-1).setGravity(17).view(R.layout.fail_dialog).build();
            build.setCanceledOnTouchOutside(false);
            View view = builder.getView();
            TextView textView = (TextView) view.findViewById(R.id.tv_cancle);
            TextView textView2 = (TextView) view.findViewById(R.id.tv_ok);
            TextView textView3 = (TextView) view.findViewById(R.id.tv_content);
            TextView textView4 = (TextView) view.findViewById(R.id.tv_ok2);
            ((LinearLayout) view.findViewById(R.id.ll_double)).setVisibility(View.GONE);
            textView4.setVisibility(View.VISIBLE);
            if (i == 1) {
                textView3.setText(R.string.keypair_not_match);
            } else if (i == 2) {
                textView3.setText(R.string.keypair_not_match);
            } else if (i == 3) {
                textView3.setText(R.string.samsung_wallet_existed);
            }
            textView4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    CustomDialog.this.dismiss();
                    activity.finish();
                }
            });
            build.show();
        }
    }

    public static String getFailMessages(GrpcAPI.Return r2) {
        if (r2 == null) {
            return "";
        }
        String response_codeVar = r2.getCode().toString();
        response_codeVar.hashCode();
        char c = 65535;
        switch (response_codeVar.hashCode()) {
            case -1736558300:
                if (response_codeVar.equals(ResultCodeContant.ERROR_40004)) {
                    c = 0;
                    break;
                }
                break;
            case -1125284788:
                if (response_codeVar.equals(ResultCodeContant.ERROR_40002)) {
                    c = 1;
                    break;
                }
                break;
            case 698614713:
                if (response_codeVar.equals(ResultCodeContant.ERROR_40008)) {
                    c = 2;
                    break;
                }
                break;
            case 1086545879:
                if (response_codeVar.equals(ResultCodeContant.ERROR_40001)) {
                    c = 3;
                    break;
                }
                break;
            case 1446901447:
                if (response_codeVar.equals(ResultCodeContant.ERROR_40005)) {
                    c = 4;
                    break;
                }
                break;
            case 2089262266:
                if (response_codeVar.equals("BANDWIDTH_ERROR")) {
                    c = 5;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 5:
                return AppContextUtil.getContext().getString(R.string.BANDWIDTH_ERROR);
            case 1:
                return AppContextUtil.getContext().getString(R.string.CONTRACT_VALIDATE_ERROR);
            case 2:
                return AppContextUtil.getContext().getString(R.string.TRANSACTION_EXPIRATION_ERROR);
            case 3:
                return AppContextUtil.getContext().getString(R.string.SIGERROR);
            case 4:
                return AppContextUtil.getContext().getString(R.string.DUP_TRANSACTION_ERROR);
            default:
                return AppContextUtil.getContext().getString(R.string.broadcast_fail);
        }
    }
}
