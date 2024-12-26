package com.tron.wallet.business.migrate;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.SpUtils;
import com.tron.wallet.business.maintab.MainTabActivity;
import com.tron.wallet.business.migrate.Contract;
import com.tron.wallet.business.migrate.component.MigrateConfig;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.ActivityMigrateBinding;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tronlinkpro.wallet.R;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.functions.Consumer;
import java.io.File;
import java.io.FileInputStream;
public class MigrateActivity extends BaseActivity<MigratePresenter, MigrateModel> implements Contract.View {
    private ActivityMigrateBinding binding;
    Button btnMigrate;
    ImageView ivIcon;
    TextView tvAbout;
    TextView tvAboutTitle;
    View tvLabel;
    TextView tvNote0;
    TextView tvNote1;
    TextView tvNote2;
    TextView tvNote3;

    @Override
    protected void setLayout() {
        ActivityMigrateBinding inflate = ActivityMigrateBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 1);
        mappingId();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.btnMigrate = this.binding.btnMigrate;
        this.tvAbout = this.binding.tvAboutInfo;
        this.tvAboutTitle = this.binding.aboutMigrateTitle;
        this.tvNote0 = this.binding.tvNote0;
        this.tvNote1 = this.binding.tvNote1;
        this.tvNote2 = this.binding.tvNote2;
        this.tvNote3 = this.binding.tvNote3;
        this.ivIcon = this.binding.ivIcon;
        this.tvLabel = this.binding.tvLabel;
    }

    @Override
    protected void processData() {
        onGetPackageId(((MigratePresenter) this.mPresenter).getCurrentPackage());
        this.btnMigrate.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                ((MigratePresenter) mPresenter).checkTargetWalletApp(getIContext());
                if (((MigratePresenter) mPresenter).getCurrentPackage() == 10) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.OneKeyExport.CLICK_IMPORT_ACTION);
                } else {
                    AnalyticsHelper.logEvent(AnalyticsHelper.OneKeyExport.CLICK_EXPORT_ACTION);
                }
            }
        });
    }

    @Override
    public void onMigrateComplete(final boolean z) {
        if (((MigratePresenter) this.mPresenter).getCurrentPackage() == 10) {
            return;
        }
        new ConfirmCustomPopupView.Builder(this).setTitleSize(16).setIcon(z ? R.mipmap.ic_unstake_result_success : R.mipmap.ic_unstake_result_fail_all).setTitle(getString(z ? R.string.migrate_out_success : R.string.migrate_out_fail)).setInfo(getString(z ? R.string.migrate_out_success_info : R.string.migrate_out_fail_info)).setShowCancelBtn(false).setConfirmText(getString(R.string.migrate_confirm)).setConfirmListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (z) {
                    Intent intent = new Intent(getIContext(), MainTabActivity.class);
                    intent.putExtra(MigrateConfig.CALLED_FROM_MIGRATE, true);
                    intent.addFlags(67108864);
                    startActivity(intent);
                    finish();
                }
            }
        }).build().show();
        AnalyticsHelper.logEvent(z ? AnalyticsHelper.OneKeyExport.ENTER_SUB_SUCCESS : AnalyticsHelper.OneKeyExport.ENTER_SUB_FAIL);
    }

    @Override
    public void onGetPackageId(int i) {
        TextView textView = getHeaderHolder().tvCommonTitle;
        textView.setVisibility(View.VISIBLE);
        if (i == 10) {
            this.ivIcon.setImageResource(R.mipmap.ic_wallet_migrate_in);
            ViewGroup.LayoutParams layoutParams = this.ivIcon.getLayoutParams();
            if (layoutParams != null) {
                int dip2px = UIUtils.dip2px(60.0f);
                layoutParams.height = dip2px;
                layoutParams.width = dip2px;
                this.ivIcon.setLayoutParams(layoutParams);
            }
            this.tvLabel.setVisibility(View.VISIBLE);
            textView.setText(R.string.migrate_in_title);
            this.tvAbout.setText(R.string.about_migrate_in_intro);
            this.tvAboutTitle.setText(R.string.about_migrate_in);
            this.tvNote0.setText(R.string.migrate_in_note_0);
            this.tvNote1.setText(R.string.migrate_in_note_1);
            this.tvNote2.setText(R.string.migrate_in_note_2);
            this.tvNote3.setText(R.string.migrate_in_note_3);
            this.btnMigrate.setText(R.string.start_migrate_in);
        } else if (i == 11) {
            this.ivIcon.setImageResource(R.mipmap.img_migrate);
            ViewGroup.LayoutParams layoutParams2 = this.ivIcon.getLayoutParams();
            if (layoutParams2 != null) {
                layoutParams2.width = UIUtils.dip2px(252.0f);
                layoutParams2.height = UIUtils.dip2px(94.0f);
                this.ivIcon.setLayoutParams(layoutParams2);
            }
            this.tvLabel.setVisibility(View.GONE);
            textView.setText(R.string.migrate_out_title);
            this.tvAbout.setText(R.string.about_migrate_intro);
            this.tvAboutTitle.setText(R.string.about_migrate);
            this.tvNote0.setText(R.string.migrate_note_0);
            this.tvNote1.setText(R.string.migrate_note_1);
            this.tvNote2.setText(R.string.migrate_note_2);
            this.tvNote3.setText(R.string.migrate_note_3);
            this.btnMigrate.setText(R.string.start_migrate_out);
        }
    }

    @Override
    public void onLeftButtonClick() {
        finish();
    }

    private String getMigrateTutorial() {
        return "2".equals((String) SpUtils.getParam("f_TronKey", AppContextUtil.getContext(), "language_key", "1")) ? MigrateConfig.ZENDESK_ZH : MigrateConfig.ZENDESK_EN;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (this.mPresenter == 0) {
            return;
        }
        if (((MigratePresenter) this.mPresenter).getCurrentPackage() == 10) {
            AnalyticsHelper.logEvent(AnalyticsHelper.OneKeyExport.ENTER_IMPORT_PAGE);
            return;
        }
        final File file = new File(getCacheDir(), MigrateConfig.RESULT_FILE);
        if (!file.exists()) {
            AnalyticsHelper.logEvent(AnalyticsHelper.OneKeyExport.ENTER_EXPORT_PAGE);
            return;
        }
        final File file2 = new File(getCacheDir(), MigrateConfig.CACHE_FILE);
        ((MigratePresenter) this.mPresenter).addDisposable(Single.create(new SingleOnSubscribe() {
            @Override
            public final void subscribe(SingleEmitter singleEmitter) {
                MigrateActivity.lambda$onResume$0(file, file2, singleEmitter);
            }
        }).compose(RxSchedulers2.io_main_single()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                onMigrateComplete(((Boolean) obj).booleanValue());
            }
        }, new MigrateActivityExternalSyntheticLambda2()));
    }

    public static void lambda$onResume$0(File file, File file2, SingleEmitter singleEmitter) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bArr = new byte[10];
        int read = fileInputStream.read(bArr);
        fileInputStream.close();
        boolean z = false;
        for (int i = 0; !file.delete() && i < 100; i++) {
        }
        file2.delete();
        if (read > 0) {
            if (read == 1 && bArr[0] == 1) {
                z = true;
            }
            singleEmitter.onSuccess(Boolean.valueOf(z));
        }
    }
}
