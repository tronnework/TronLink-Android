package com.tron.wallet.business.migrate.component;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.CenterPopupView;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.migrate.MigrateModel;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.components.WhiteEnergyProgressView;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.SpannableUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;
public class MigrateProgressDialog extends CenterPopupView {
    private CompositeDisposable disposables;
    private Uri fileUri;
    private MigrateModel model;
    private Consumer<Boolean> onClickConfirm;
    private WhiteEnergyProgressView progressView;
    private Uri resultFile;
    private TextView tvProgress;

    private void setOnClickConfirmListener(Consumer<Boolean> consumer) {
        this.onClickConfirm = consumer;
    }

    @Override
    public int getImplLayoutId() {
        return R.layout.dialog_migrate_progress;
    }

    public void setUri(Uri uri, Uri uri2) {
        this.fileUri = uri;
        this.resultFile = uri2;
    }

    public MigrateProgressDialog(Context context) {
        super(context);
    }

    public static void showUp(Context context, Uri uri, Uri uri2, Consumer<Boolean> consumer) {
        MigrateProgressDialog migrateProgressDialog = new MigrateProgressDialog(context);
        migrateProgressDialog.setUri(uri, uri2);
        migrateProgressDialog.setOnClickConfirmListener(consumer);
        new XPopup.Builder(context).dismissOnTouchOutside(false).dismissOnBackPressed(false).asCustom(migrateProgressDialog).show();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (this.fileUri == null) {
            return;
        }
        this.progressView = (WhiteEnergyProgressView) findViewById(R.id.progress_view);
        this.tvProgress = (TextView) findViewById(R.id.tv_progress);
        this.disposables = new CompositeDisposable();
        this.model = new MigrateModel();
        doMigrate();
    }

    private void doMigrate() {
        postDelayed(new Runnable() {
            @Override
            public final void run() {
                lambda$doMigrate$5();
            }
        }, 1000L);
    }

    public void lambda$doMigrate$5() {
        final AtomicReference atomicReference = new AtomicReference(Float.valueOf(0.0f));
        setProgressText(0.0f);
        this.progressView.setProgressValue(0.0f);
        this.disposables.add(this.model.readMigrateData(getContext(), this.fileUri, this.resultFile).doOnComplete(new Action() {
            @Override
            public final void run() {
                lambda$doMigrate$1(atomicReference);
            }
        }).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$doMigrate$2(atomicReference, (Float) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$doMigrate$4((Throwable) obj);
            }
        }));
    }

    public void lambda$doMigrate$1(final AtomicReference atomicReference) throws Exception {
        postDelayed(new Runnable() {
            @Override
            public final void run() {
                lambda$doMigrate$0(atomicReference);
            }
        }, 1000L);
    }

    public void lambda$doMigrate$0(AtomicReference atomicReference) {
        onMigrateComplete(((Float) atomicReference.get()).floatValue() >= 1.0f);
    }

    public void lambda$doMigrate$2(AtomicReference atomicReference, Float f) throws Exception {
        this.progressView.setProgressValue(f.floatValue());
        setProgressText(f.floatValue());
        atomicReference.set(f);
    }

    public void lambda$doMigrate$3() {
        onMigrateComplete(false);
    }

    public void lambda$doMigrate$4(Throwable th) throws Exception {
        postDelayed(new Runnable() {
            @Override
            public final void run() {
                lambda$doMigrate$3();
            }
        }, 1000L);
    }

    private void onMigrateComplete(final boolean z) {
        dismiss();
        ConfirmCustomPopupView.Builder dismissOnBackPressed = new ConfirmCustomPopupView.Builder(getContext()).setTitleSize(16).setTitle(getContext().getString(z ? R.string.migrate_in_success : R.string.migrate_in_fail)).setIcon(z ? R.mipmap.ic_unstake_result_success : R.mipmap.ic_unstake_result_fail_all).setBtnStyle(z ? 1 : 0).setShowCancelBtn(!z ? 1 : 0).setAutoDismissOutSide(false).setDismissOnBackPressed(false);
        Context context = getContext();
        int i = R.string.migrate_confirm;
        ConfirmCustomPopupView.Builder dismissOnBackPressed2 = dismissOnBackPressed.setCancelText(context.getString(R.string.migrate_confirm)).setDismissOnBackPressed(false);
        Context context2 = getContext();
        if (!z) {
            i = R.string.migrate_retry;
        }
        dismissOnBackPressed2.setConfirmText(context2.getString(i)).setConfirmListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                handleClickCallback(z);
                if (z || model == null) {
                    return;
                }
                model.startMigratePro(view.getContext());
                AnalyticsHelper.logEvent(AnalyticsHelper.OneKeyExport.CLICK_IMPORT_FAIL_RETRY);
            }
        }).setCancleListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                handleClickCallback(false);
                AnalyticsHelper.logEvent(AnalyticsHelper.OneKeyExport.CLICK_IMPORT_FAIL_OK);
            }
        }).build().show();
        AnalyticsHelper.logEvent(z ? AnalyticsHelper.OneKeyExport.ENTER_IMPORT_SUCCESS : AnalyticsHelper.OneKeyExport.ENTER_IMPORT_FAIL);
    }

    private void setProgressText(float f) {
        if (this.tvProgress == null) {
            return;
        }
        String string = getContext().getString(R.string.data_importing);
        String format = String.format(Locale.getDefault(), " (%.0f%%)", Float.valueOf(f * 100.0f));
        this.tvProgress.setText(SpannableUtils.getTextColorSpannable(string + format, format, getContext().getResources().getColor(R.color.blue_3c)));
    }

    @Override
    public void onDismiss() {
        super.onDismiss();
        CompositeDisposable compositeDisposable = this.disposables;
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

    public void handleClickCallback(boolean z) {
        Consumer<Boolean> consumer = this.onClickConfirm;
        if (consumer != null) {
            try {
                consumer.accept(Boolean.valueOf(z));
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
    }
}
