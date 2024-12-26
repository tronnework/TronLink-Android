package com.tron.wallet.business.pull.component;

import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.gson.Gson;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.pull.DeepLinkCheck;
import com.tron.wallet.business.pull.PullAction;
import com.tron.wallet.business.pull.PullActivity;
import com.tron.wallet.business.pull.PullConstants;
import com.tron.wallet.business.pull.PullParam;
import com.tron.wallet.business.pull.PullResult;
import com.tron.wallet.business.pull.PullResultCode;
import com.tron.wallet.business.pull.PullResultHelper;
import com.tron.wallet.business.pull.component.PullDetailContract;
import com.tron.wallet.business.pull.component.PullDetailPresenter;
import com.tron.wallet.business.pull.login.LoginParam;
import com.tron.wallet.business.pull.sign.SignParam;
import com.tron.wallet.business.pull.transfer.TransferParam;
import com.tron.wallet.business.pull.transfer.TransferView;
import com.tron.wallet.common.components.ErrorView;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.AcPullDetailBinding;
import com.tronlinkpro.wallet.R;
public class PullDetailActivity extends BaseActivity<PullDetailPresenter, PullDetailModel> implements PullDetailContract.View {
    public static final String TAG = "PullDetailActivity";
    private AcPullDetailBinding binding;
    TextView btnCancel;
    TextView btnConfirm;
    ImageView ivHeader;
    SimpleDraweeView ivImage;
    ViewGroup llActionId;
    FrameLayout llContentContainer;
    FrameLayout llHeaderContainer;
    private PullAction pullAction;
    private PullDetailView pullDetailView;
    private PullParam pullParam;
    NestedScrollView rlContent;
    RelativeLayout rlInvalidFromAccount;
    TextView tvActionId;
    TextView tvActionType;
    ErrorView tvContentTip;
    ErrorView tvHeaderTip;
    TextView tvInvalidFromAddress;
    TextView tvName;
    TextView tvNetType;
    TextView tvRight;
    TextView tvTitle;

    @Override
    protected void setLayout() {
        AcPullDetailBinding inflate = AcPullDetailBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 0);
        mappingId();
    }

    public void mappingId() {
        this.rlContent = this.binding.scrollView;
        this.tvRight = this.binding.tvRight;
        this.ivHeader = this.binding.ivHeader;
        this.tvTitle = this.binding.tvTitle;
        this.llHeaderContainer = this.binding.llHeaderCustomContainer;
        this.tvHeaderTip = this.binding.tvHeaderTip;
        this.ivImage = this.binding.ivImage;
        this.tvName = this.binding.tvName;
        this.tvActionType = this.binding.tvActionType;
        this.tvNetType = this.binding.tvNetType;
        this.llContentContainer = this.binding.llContentCustomContainer;
        this.llActionId = this.binding.llActionId;
        this.tvActionId = this.binding.tvActionId;
        this.tvContentTip = this.binding.tvContentTip;
        this.btnConfirm = this.binding.btnConfirm;
        this.btnCancel = this.binding.btnCancel;
        this.rlInvalidFromAccount = this.binding.rlInvalidFromAccount;
        this.tvInvalidFromAddress = this.binding.tvInvalidFromAddress;
    }

    @Override
    protected void processData() {
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        showPullLoading(true);
        this.pullAction = PullAction.fromAction(intent.getStringExtra(PullConstants.ACTION));
        String stringExtra = intent.getStringExtra(PullConstants.PARAMS);
        if (this.pullAction != PullAction.NOT_SUPPORT) {
            try {
                Gson gson = new Gson();
                int i = fun1.$SwitchMap$com$tron$wallet$business$pull$PullAction[this.pullAction.ordinal()];
                if (i == 1) {
                    this.pullParam = (PullParam) gson.fromJson(stringExtra,  LoginParam.class);
                } else if (i == 2) {
                    this.pullParam = (PullParam) gson.fromJson(stringExtra,  TransferParam.class);
                } else if (i == 3) {
                    this.pullParam = ((PullDetailPresenter) this.mPresenter).parseSignParam(stringExtra);
                } else {
                    finish();
                }
                ((PullDetailPresenter) this.mPresenter).setParam(this.pullAction, this.pullParam);
                return;
            } catch (Throwable th) {
                SentryUtil.captureException(th);
                showErrorInfo(stringExtra);
                return;
            }
        }
        showErrorInfo(stringExtra);
    }

    static class fun1 {
        static final int[] $SwitchMap$com$tron$wallet$business$pull$PullAction;

        static {
            int[] iArr = new int[PullAction.values().length];
            $SwitchMap$com$tron$wallet$business$pull$PullAction = iArr;
            try {
                iArr[PullAction.ACTION_LOGIN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$pull$PullAction[PullAction.ACTION_TRANSFER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$pull$PullAction[PullAction.ACTION_SIGN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        PullDetailView pullDetailView;
        if (i == 4 && (pullDetailView = this.pullDetailView) != null) {
            if (pullDetailView instanceof TransferView) {
                ((TransferView) pullDetailView).requestQuit(this.pullParam);
                return true;
            }
            pullDetailView.requestQuit(this.pullParam);
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override
    public void showPullLoading(boolean z) {
        if (z) {
            showLoadingDialog();
        } else {
            dismissLoading();
        }
    }

    private void showErrorInfo(String str) {
        PullActivity.showDataErrorPopup(this, new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showErrorInfo$0(view);
            }
        });
        PullParam pullParam = (PullParam) new Gson().fromJson(str,  PullParam.class);
        PullResult pullResult = new PullResult();
        pullResult.setActionId(pullParam.getActionId());
        pullResult.setCode(PullResultCode.FAIL_TO_PARSE_JSON.getCode());
        pullResult.setMessage(PullResultCode.FAIL_TO_PARSE_JSON.getMessage());
        PullResultHelper.callBackToDApp(pullParam.getCallbackUrl(), pullResult);
    }

    public void lambda$showErrorInfo$0(View view) {
        finish();
    }

    @Override
    public void showInvalidAccountView(String str) {
        this.rlContent.setVisibility(View.GONE);
        this.rlInvalidFromAccount.setVisibility(View.VISIBLE);
        this.tvInvalidFromAddress.setText(str);
    }

    @Override
    public void setDetailView(PullDetailView pullDetailView) {
        this.rlContent.setVisibility(View.VISIBLE);
        this.rlInvalidFromAccount.setVisibility(View.GONE);
        showPullLoading(false);
        PullResultCode checkDataValid = pullDetailView.checkDataValid();
        if (checkDataValid != PullResultCode.SUCCESS) {
            PullActivity.showDataErrorPopup(this, new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    lambda$setDetailView$1(view);
                }
            });
            LogUtils.d("Pull.Activity", "code:" + checkDataValid.getCode() + " message:" + checkDataValid.getMessage());
            PullResult pullResult = new PullResult();
            if (DeepLinkCheck.isValidDAppUrl(this.pullParam.getCallbackUrl())) {
                pullResult.setActionId(this.pullParam.getActionId());
                pullResult.setCode(checkDataValid.getCode());
                pullResult.setMessage(checkDataValid.getMessage());
                PullResultHelper.callBackToDApp(this.pullParam.getCallbackUrl(), pullResult);
                return;
            }
            return;
        }
        this.pullDetailView = pullDetailView;
        pullDetailView.initHeader(this.tvRight, this.ivHeader, this.tvTitle, this.llHeaderContainer, this.tvHeaderTip);
        pullDetailView.initContent(this.llContentContainer, this.tvContentTip);
        pullDetailView.initAction(this.btnConfirm, this.btnCancel);
        pullDetailView.init();
        this.tvNetType.setText(getString(R.string.tron_main_net));
        int i = fun1.$SwitchMap$com$tron$wallet$business$pull$PullAction[this.pullAction.ordinal()];
        if (i == 1) {
            LoginParam loginParam = (LoginParam) this.pullParam;
            if (!StringTronUtil.isEmpty(loginParam.getDappIcon())) {
                this.ivImage.setImageURI(loginParam.getDappIcon());
            }
            this.tvName.setText(loginParam.getDappName());
            this.llActionId.setVisibility(View.GONE);
            this.tvActionType.setText(getString(R.string.pull_action_login));
        } else if (i == 2) {
            setTransferDetailView((TransferParam) this.pullParam);
        } else if (i != 3) {
        } else {
            if (((PullDetailPresenter) this.mPresenter).getSignTransactionParseResult() == PullDetailPresenter.SignTransactionParseResult.SignTransfer) {
                setTransferDetailView((TransferParam) this.pullParam);
            } else if (((PullDetailPresenter) this.mPresenter).getSignTransactionParseResult() == PullDetailPresenter.SignTransactionParseResult.SignTrigger) {
                this.tvActionType.setText(getString(R.string.pull_trigger_smart_contract));
            } else if (((PullDetailPresenter) this.mPresenter).getSignTransactionParseResult() == PullDetailPresenter.SignTransactionParseResult.Other) {
                PullParam pullParam = this.pullParam;
                if (pullParam instanceof SignParam) {
                    SignParam signParam = (SignParam) pullParam;
                    if (PullConstants.SIGN_STR.equals(signParam.getSignType())) {
                        this.tvActionType.setText(getString(R.string.pull_sign_hex_type));
                    } else if (PullConstants.SIGN_TYPED_DATA.equals(signParam.getSignType())) {
                        this.tvActionType.setText(getString(R.string.pull_sign_712_type));
                    }
                }
            }
            if (!StringTronUtil.isEmpty(this.pullParam.getDappIcon())) {
                this.ivImage.setImageURI(this.pullParam.getDappIcon());
            }
            this.tvName.setText(this.pullParam.getDappName());
            if (TextUtils.isEmpty(this.pullParam.getActionId())) {
                return;
            }
            this.llActionId.setVisibility(View.VISIBLE);
            this.tvActionId.setVisibility(View.VISIBLE);
            this.tvActionId.setText(this.pullParam.getActionId());
        }
    }

    public void lambda$setDetailView$1(View view) {
        finish();
    }

    private void setTransferDetailView(TransferParam transferParam) {
        if (transferParam != null) {
            if (!StringTronUtil.isEmpty(transferParam.getDappIcon())) {
                this.ivImage.setImageURI(transferParam.getDappIcon());
            }
            this.tvName.setText(transferParam.getDappName());
            if (TextUtils.isEmpty(transferParam.getTokenId()) && TextUtils.isEmpty(transferParam.getContract())) {
                this.tvActionType.setText(getString(R.string.pull_transfer_trx));
            } else if (TextUtils.isEmpty(transferParam.getContract()) && Double.parseDouble(transferParam.getTokenId()) > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                this.tvActionType.setText(getString(R.string.pull_request_trc10));
            } else {
                this.tvActionType.setText(getString(R.string.pull_transfer_trc20));
            }
            if (TextUtils.isEmpty(transferParam.getActionId())) {
                return;
            }
            this.llActionId.setVisibility(View.VISIBLE);
            this.tvActionId.setVisibility(View.VISIBLE);
            this.tvActionId.setText(transferParam.getActionId());
        }
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.mPresenter != 0) {
            ((PullDetailPresenter) this.mPresenter).onActivityResult(i2, i2, intent);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        PullDetailView pullDetailView = this.pullDetailView;
        if (pullDetailView != null) {
            pullDetailView.deInit();
        }
    }
}
