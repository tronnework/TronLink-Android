package com.tron.wallet.business.migrate;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Pair;
import android.view.View;
import androidx.activity.ComponentActivity;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.migrate.Contract;
import com.tron.wallet.business.migrate.component.MigrateConfig;
import com.tron.wallet.business.migrate.component.MigrateConfirmDialog;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.config.TronSetting;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tronlinkpro.wallet.R;
import io.reactivex.functions.Consumer;
public class MigratePresenter extends Contract.Presenter {
    int packageId = -1;

    @Override
    protected void onStart() {
    }

    @Override
    void registerLaunchActivityCallback(ComponentActivity componentActivity) {
    }

    @Override
    public int getCurrentPackage() {
        if (this.packageId < 0) {
            this.packageId = ((Contract.Model) this.mModel).getCurrentPackageId();
        }
        return this.packageId;
    }

    @Override
    public void checkTargetWalletApp(Context context) {
        final int checkTargetWalletApp = ((Contract.Model) this.mModel).checkTargetWalletApp(context);
        int i = this.packageId;
        int i2 = R.string.migrate_deprecated_watch_cold;
        int i3 = R.string.migrate_upgrade_global;
        boolean z = true;
        if (i == 11) {
            if (checkTargetWalletApp == 2 || checkTargetWalletApp == 4) {
                ConfirmCustomPopupView.Builder confirmText = new ConfirmCustomPopupView.Builder(((Contract.View) this.mView).getIContext()).setTitleSize(16).setBtnStyle(0).setConfirmText(context.getString(R.string.migrate_upgrade_global));
                if (checkTargetWalletApp == 2) {
                    i2 = R.string.migrate_global_version_deprecated;
                }
                confirmText.setTitle(context.getString(i2)).setAutoDismissOutSide(false).setShowCancelBtn(true).setConfirmListener(new NoDoubleClickListener2() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        ((Contract.Model) mModel).installFromGoogle(view.getContext(), getCurrentPackage());
                    }
                }).build().show();
                return;
            }
            MigrateConfirmDialog.showUp(((Contract.View) this.mView).getIContext(), checkTargetWalletApp, new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    lambda$checkTargetWalletApp$1(view);
                }
            }, new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    lambda$checkTargetWalletApp$2(view);
                }
            });
            return;
        }
        if (checkTargetWalletApp == 0) {
            i2 = R.string.migrate_in_subtitle;
            i3 = R.string.enter_to_migrate;
        } else {
            if (checkTargetWalletApp == 1) {
                i2 = R.string.app_not_installed;
                i3 = R.string.migrate_ok;
            } else if (checkTargetWalletApp == 2) {
                i2 = R.string.app_pro_version_deprecated;
                i3 = R.string.go_update_pro;
            } else if (checkTargetWalletApp != 4) {
                i2 = 0;
                i3 = 0;
            }
            z = false;
        }
        if (i2 <= 0) {
            return;
        }
        new ConfirmCustomPopupView.Builder(((Contract.View) this.mView).getIContext()).setBtnStyle(0).setTitleSize(16).setConfirmText(context.getString(i3)).setTitle(context.getString(i2)).setAutoDismissOutSide(false).setShowCancelBtn(z).setConfirmListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                int i4 = checkTargetWalletApp;
                if (i4 == 0) {
                    ((Contract.Model) mModel).startMigratePro(((Contract.View) mView).getIContext());
                } else if (i4 != 2) {
                    if (i4 == 4) {
                        ((Contract.Model) mModel).installFromGoogle(((Contract.View) mView).getIContext(), MigrateConfig.APP_ID_GLOBAL);
                    }
                } else if (13 == ((Contract.Model) mModel).getChannelPro(view.getContext())) {
                    ((Contract.Model) mModel).installFromGoogle(((Contract.View) mView).getIContext(), getCurrentPackage());
                } else {
                    try {
                        Intent intent = new Intent("android.intent.action.VIEW");
                        intent.setData(Uri.parse(TronSetting.TRON_URL));
                        view.getContext().startActivity(intent);
                    } catch (Exception e) {
                        LogUtils.e(e);
                    }
                }
            }
        }).build().show();
    }

    public void lambda$checkTargetWalletApp$1(final View view) {
        addDisposable(((Contract.Model) this.mModel).writeMigrateData(view.getContext()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$checkTargetWalletApp$0(view, (Pair) obj);
            }
        }, new MigrateActivityExternalSyntheticLambda2()));
    }

    public void lambda$checkTargetWalletApp$0(View view, Pair pair) throws Exception {
        ((Contract.Model) this.mModel).startMigrateGlobal(view.getContext(), (Uri) pair.first, (Uri) pair.second);
    }

    public void lambda$checkTargetWalletApp$2(View view) {
        ((Contract.Model) this.mModel).installFromGoogle(view.getContext(), getCurrentPackage());
    }
}
