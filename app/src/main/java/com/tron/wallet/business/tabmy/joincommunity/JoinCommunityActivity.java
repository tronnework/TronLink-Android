package com.tron.wallet.business.tabmy.joincommunity;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.tabmy.bean.CommunityOutput;
import com.tron.wallet.business.tabmy.joincommunity.JoinCommunityContract;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.AcJoincommunityBinding;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
public class JoinCommunityActivity extends BaseActivity<JoinCommunityPresenter, JoinCommunityModel> implements JoinCommunityContract.View {
    private AcJoincommunityBinding binding;
    private String enTelUrl;
    NoNetView noNetView;
    TextView tvEn;
    TextView tvTwitter;
    TextView tvWechat;
    TextView tvZh;
    private String twitterUrl;
    private String weChatUrl;
    private String zhTelUrl;

    @Override
    protected void setLayout() {
        AcJoincommunityBinding inflate = AcJoincommunityBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        setClick();
        setView(root, 3);
        setHeaderBar(getString(R.string.join_community));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.tvEn = this.binding.tvEn;
        this.tvZh = this.binding.tvZh;
        this.tvTwitter = this.binding.tvTwitter;
        this.tvWechat = this.binding.tvWechat;
        this.noNetView = this.binding.noNetView;
    }

    @Override
    protected void processData() {
        ((JoinCommunityPresenter) this.mPresenter).getCommunityContent();
        this.noNetView.setOnReloadClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$processData$1(view);
            }
        });
    }

    public void lambda$processData$1(View view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public final void run() {
                lambda$processData$0();
            }
        }, 1000L);
    }

    public void lambda$processData$0() {
        ((JoinCommunityPresenter) this.mPresenter).getCommunityContent();
    }

    public void setClick() {
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                switch (view.getId()) {
                    case R.id.rl_en_arrow:
                        JoinCommunityActivity joinCommunityActivity = JoinCommunityActivity.this;
                        joinCommunityActivity.goIntent(joinCommunityActivity.enTelUrl);
                        return;
                    case R.id.rl_en_copy:
                        JoinCommunityActivity joinCommunityActivity2 = JoinCommunityActivity.this;
                        joinCommunityActivity2.copyStr(joinCommunityActivity2.enTelUrl);
                        return;
                    case R.id.rl_twitter_arrow:
                        JoinCommunityActivity joinCommunityActivity3 = JoinCommunityActivity.this;
                        joinCommunityActivity3.goIntent(joinCommunityActivity3.twitterUrl);
                        return;
                    case R.id.rl_twitter_copy:
                        JoinCommunityActivity joinCommunityActivity4 = JoinCommunityActivity.this;
                        joinCommunityActivity4.copyStr(joinCommunityActivity4.twitterUrl);
                        return;
                    case R.id.rl_wechat_copy:
                        JoinCommunityActivity joinCommunityActivity5 = JoinCommunityActivity.this;
                        joinCommunityActivity5.copyStr(joinCommunityActivity5.weChatUrl);
                        return;
                    case R.id.rl_zh_arrow:
                        JoinCommunityActivity joinCommunityActivity6 = JoinCommunityActivity.this;
                        joinCommunityActivity6.goIntent(joinCommunityActivity6.zhTelUrl);
                        return;
                    case R.id.rl_zh_copy:
                        JoinCommunityActivity joinCommunityActivity7 = JoinCommunityActivity.this;
                        joinCommunityActivity7.copyStr(joinCommunityActivity7.zhTelUrl);
                        return;
                    default:
                        return;
                }
            }
        };
        this.binding.rlEnArrow.setOnClickListener(noDoubleClickListener2);
        this.binding.rlEnCopy.setOnClickListener(noDoubleClickListener2);
        this.binding.rlZhArrow.setOnClickListener(noDoubleClickListener2);
        this.binding.rlZhCopy.setOnClickListener(noDoubleClickListener2);
        this.binding.rlTwitterArrow.setOnClickListener(noDoubleClickListener2);
        this.binding.rlTwitterCopy.setOnClickListener(noDoubleClickListener2);
        this.binding.rlWechatCopy.setOnClickListener(noDoubleClickListener2);
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }

    @Override
    public void updateUi(CommunityOutput.DataBean dataBean) {
        this.noNetView.setVisibility(View.GONE);
        if (dataBean != null) {
            this.zhTelUrl = dataBean.telegram_cn;
            this.enTelUrl = dataBean.telegram_en;
            this.twitterUrl = dataBean.twitter;
            this.weChatUrl = dataBean.WeChat;
        } else {
            this.zhTelUrl = SpAPI.THIS.getZhTeleUrl();
            this.enTelUrl = SpAPI.THIS.getEnTeleUrl();
            this.twitterUrl = SpAPI.THIS.getTwitterUrl();
            this.weChatUrl = SpAPI.THIS.getWechatUrl();
        }
        if (TextUtils.isEmpty(this.zhTelUrl) || TextUtils.isEmpty(this.enTelUrl) || TextUtils.isEmpty(this.twitterUrl) || TextUtils.isEmpty(this.weChatUrl)) {
            showErrorPage();
            ToastAsBottom(R.string.dapp_error);
            return;
        }
        this.tvEn.setText(this.enTelUrl);
        this.tvZh.setText(this.zhTelUrl);
        this.tvTwitter.setText(this.twitterUrl);
        this.tvWechat.setText(this.weChatUrl);
    }

    @Override
    public void showErrorPage() {
        if (TronConfig.hasNet) {
            this.noNetView.setReloadDescription(R.string.web_unaccess);
            this.noNetView.setIcon(R.mipmap.ic_invalid_url);
            this.noNetView.setReloadable(false);
        } else {
            this.noNetView.setReloadDescription(R.string.net_error);
            this.noNetView.setIcon(R.mipmap.ic_no_net);
            this.noNetView.setReloadText(R.string.reload);
            this.noNetView.setReloadable(true);
        }
        this.noNetView.setInnerTopMargin(UIUtils.dip2px(80.0f));
        this.noNetView.setVisibility(View.VISIBLE);
    }

    @Override
    public void goIntent(String str) {
        try {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } catch (Exception e) {
            LogUtils.e(e);
            SentryUtil.captureException(e);
        }
    }

    @Override
    public void copyStr(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        UIUtils.copy(str);
        Toast(getString(R.string.already_copy));
    }
}
