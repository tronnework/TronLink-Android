package com.tron.wallet.business.instructions;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.AcInstructionBinding;
import com.tronlinkpro.wallet.R;
public class TrxInstructionsActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    private static final String IS_STAKEV2 = "is_stakev2";
    private AcInstructionBinding binding;
    Button btnKnowIt;
    TextView content;
    TextView doc0Spe;
    TextView doc1Spe;
    TextView doc2Spe;
    TextView doc3Spe;
    TextView doc4Spe;
    TextView doc5Spe;
    RelativeLayout rlContent0Spe;
    RelativeLayout rlContent1Spe;
    RelativeLayout rlContent2;
    RelativeLayout rlContent2Spe;
    RelativeLayout rlContent3Spe;
    RelativeLayout rlContent4Spe;
    RelativeLayout rlContent5Spe;
    LinearLayout rlContentSpe;
    TextView tvDoc1;
    TextView tvDoc2;
    TextView tvTitle;
    private String title = "";
    private String doc1 = "";
    private String doc2 = "";
    private String content0 = "";
    private String content1 = "";
    private String content2 = "";
    private String content3 = "";
    private String content4 = "";
    private String content5 = "";
    private boolean isStakeV2 = false;

    public static void start(Context context, String str, String str2, String str3) {
        Intent intent = new Intent(context, TrxInstructionsActivity.class);
        intent.putExtra(TronConfig.DOC_Title, str);
        intent.putExtra(TronConfig.DOC_1, str2);
        intent.putExtra(TronConfig.DOC_2, str3);
        context.startActivity(intent);
    }

    public static void start(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, boolean z, boolean z2) {
        Intent intent = new Intent(context, TrxInstructionsActivity.class);
        intent.putExtra(TronConfig.DOC_Title, str);
        intent.putExtra(TronConfig.TRX_DOC_0, str2);
        intent.putExtra(TronConfig.TRX_DOC_1, str3);
        intent.putExtra(TronConfig.TRX_DOC_2, str4);
        intent.putExtra(TronConfig.TRX_DOC_3, str5);
        intent.putExtra(TronConfig.TRX_DOC_4, str6);
        intent.putExtra(TronConfig.TRX_DOC_5, str7);
        intent.putExtra(TronConfig.TRX_DOC_SPECIAL, z);
        intent.putExtra(IS_STAKEV2, z2);
        context.startActivity(intent);
    }

    public static void start(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, boolean z) {
        start(context, str, str2, str3, str4, str5, str6, str7, z, false);
    }

    @Override
    public void onCreate1(Bundle bundle) {
        super.onCreate1(bundle);
        if (Build.VERSION.SDK_INT == 26 || Build.VERSION.SDK_INT == 27) {
            setRequestedOrientation(2);
        } else {
            setRequestedOrientation(1);
        }
    }

    @Override
    protected void setLayout() {
        AcInstructionBinding inflate = AcInstructionBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 0);
        mappingId();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.tvTitle = this.binding.tvTitle;
        this.tvDoc1 = this.binding.doc1;
        this.tvDoc2 = this.binding.doc2;
        this.content = this.binding.content;
        this.doc0Spe = this.binding.doc0Spe;
        this.rlContent0Spe = this.binding.rlContent0Spe;
        this.doc1Spe = this.binding.doc1Spe;
        this.rlContent1Spe = this.binding.rlContent1Spe;
        this.doc2Spe = this.binding.doc2Spe;
        this.rlContent2Spe = this.binding.rlContent2Spe;
        this.doc3Spe = this.binding.doc3Spe;
        this.rlContent3Spe = this.binding.rlContent3Spe;
        this.doc4Spe = this.binding.doc4Spe;
        this.rlContent4Spe = this.binding.rlContent4Spe;
        this.doc5Spe = this.binding.doc5Spe;
        this.rlContent5Spe = this.binding.rlContent5Spe;
        this.rlContentSpe = this.binding.rlContentSpe;
        this.rlContent2 = this.binding.rlContent2;
        this.btnKnowIt = this.binding.btnKnowIt;
    }

    @Override
    protected void processData() {
        Intent intent = getIntent();
        if (intent != null) {
            this.title = intent.getStringExtra(TronConfig.DOC_Title);
            if (intent.getBooleanExtra(TronConfig.TRX_DOC_SPECIAL, false)) {
                this.content0 = intent.getStringExtra(TronConfig.TRX_DOC_0);
                this.content1 = intent.getStringExtra(TronConfig.TRX_DOC_1);
                this.content2 = intent.getStringExtra(TronConfig.TRX_DOC_2);
                this.content3 = intent.getStringExtra(TronConfig.TRX_DOC_3);
                this.content4 = intent.getStringExtra(TronConfig.TRX_DOC_4);
                this.content5 = intent.getStringExtra(TronConfig.TRX_DOC_5);
                this.isStakeV2 = intent.getBooleanExtra(IS_STAKEV2, false);
                this.rlContentSpe.setVisibility(View.VISIBLE);
                this.content.setVisibility(View.GONE);
                this.tvDoc2.setVisibility(View.GONE);
                if (!StringTronUtil.isEmpty(this.content0)) {
                    this.doc0Spe.setText(this.content0);
                    this.rlContent0Spe.setVisibility(View.VISIBLE);
                }
                if (!StringTronUtil.isEmpty(this.content1)) {
                    this.rlContent1Spe.setVisibility(View.VISIBLE);
                    this.doc1Spe.setText(this.content1);
                }
                if (!StringTronUtil.isEmpty(this.content2)) {
                    this.doc2Spe.setText(this.content2);
                    this.rlContent2Spe.setVisibility(View.VISIBLE);
                }
                if (!StringTronUtil.isEmpty(this.content3)) {
                    this.doc3Spe.setText(this.content3);
                    this.rlContent3Spe.setVisibility(View.VISIBLE);
                }
                if (!StringTronUtil.isEmpty(this.content4)) {
                    this.doc4Spe.setText(this.content4);
                    this.rlContent4Spe.setVisibility(View.VISIBLE);
                }
                if (!StringTronUtil.isEmpty(this.content5)) {
                    this.doc5Spe.setText(this.content5);
                    this.rlContent5Spe.setVisibility(View.VISIBLE);
                }
            } else {
                this.doc1 = intent.getStringExtra(TronConfig.DOC_1);
                this.doc2 = intent.getStringExtra(TronConfig.DOC_2);
                this.rlContentSpe.setVisibility(View.GONE);
                if (!StringTronUtil.isEmpty(this.doc1)) {
                    this.content.setVisibility(View.VISIBLE);
                    this.content.setText(this.doc1);
                }
                if (!StringTronUtil.isEmpty(this.doc2)) {
                    this.rlContent2.setVisibility(View.VISIBLE);
                    this.tvDoc2.setVisibility(View.VISIBLE);
                    this.tvDoc2.setText(this.doc2);
                }
            }
        }
        if (this.isStakeV2) {
            AnalyticsHelper.logEvent(AnalyticsHelper.ResourceDetailPage.STAKE2ACTION_HEADEDU_POP_SHOW);
        }
        if (!StringTronUtil.isEmpty(this.title)) {
            this.tvTitle.setText(this.title);
        }
        this.btnKnowIt.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (isStakeV2) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.ResourceDetailPage.STAKE2ACTION_HEADEDU_POP_CLICK_KNOW);
                }
                finish();
                overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
            }
        });
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }
}
