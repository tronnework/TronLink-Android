package com.tron.wallet.business.tabdapp.component;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.components.dialog.CustomDialog;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tronlinkpro.wallet.R;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.functions.Consumer;
public class DAppDialog {
    public static void showOutsideLinkWarning(final Context context, String str, final Consumer<Boolean> consumer) {
        final String host = StringTronUtil.getHost(str);
        checkAuthorizedUrl(host).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                DAppDialog.lambda$showOutsideLinkWarning$0(Consumer.this, context, host, (Boolean) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                ((Throwable) obj).printStackTrace();
            }
        });
    }

    public static void lambda$showOutsideLinkWarning$0(Consumer consumer, Context context, String str, Boolean bool) throws Exception {
        if (bool.booleanValue()) {
            try {
                consumer.accept(true);
                return;
            } catch (Exception e) {
                LogUtils.e(e);
                return;
            }
        }
        showConfirmDialog(context, str, consumer);
    }

    private static void showConfirmDialog(Context context, final String str, final Consumer<Boolean> consumer) {
        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        final CustomDialog build = builder.style(R.style.loading_dialog).cancelTouchout(true).heightDimenRes(-2).widthDimenRes(-1).setGravity(17).view(R.layout.dapp_dialog).build();
        View view = builder.getView();
        ((TextView) view.findViewById(R.id.tv_cancle)).setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view2) {
                DAppDialog.lambda$showConfirmDialog$2(Consumer.this, build, view2);
            }
        });
        ((TextView) view.findViewById(R.id.tv_ok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view2) {
                DAppDialog.lambda$showConfirmDialog$3(Consumer.this, str, build, view2);
            }
        });
        build.show();
    }

    public static void lambda$showConfirmDialog$2(Consumer consumer, CustomDialog customDialog, View view) {
        try {
            try {
                consumer.accept(false);
            } catch (Exception e) {
                LogUtils.e(e);
            }
        } finally {
            customDialog.dismiss();
        }
    }

    public static void lambda$showConfirmDialog$3(Consumer consumer, String str, CustomDialog customDialog, View view) {
        try {
            try {
                consumer.accept(true);
            } catch (Exception e) {
                LogUtils.e(e);
            }
        } finally {
            SpAPI.THIS.setDappName(str);
            SpAPI.THIS.setShowThirdAddressPopWindow(str);
            customDialog.dismiss();
        }
    }

    private static Single<Boolean> checkAuthorizedUrl(final String str) {
        return Single.create(new SingleOnSubscribe() {
            @Override
            public final void subscribe(SingleEmitter singleEmitter) {
                singleEmitter.onSuccess(Boolean.valueOf(SpAPI.THIS.getDappName(str)));
            }
        }).compose(RxSchedulers2.io_main_single());
    }
}
