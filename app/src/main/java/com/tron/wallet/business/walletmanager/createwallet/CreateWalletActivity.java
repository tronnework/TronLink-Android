package com.tron.wallet.business.walletmanager.createwallet;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.wallet.business.walletmanager.createwallet.CreateWalletContract;
import com.tron.wallet.business.walletmanager.createwallet.success.CreateWalletSuccessFragment;
import com.tron.wallet.business.walletmanager.createwallet.view.TimelineView;
import com.tron.wallet.business.walletmanager.input.DataChangeListener;
import com.tron.wallet.business.walletmanager.input.InputWalletDetailFragment;
import com.tron.wallet.common.components.SimpleDraweeViewGif;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.interfaces.PopWindowCallback;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.PopupWindowUtil;
import com.tron.wallet.common.utils.SpannableUtils;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.common.utils.WalletNameGeneratorUtils;
import com.tron.wallet.databinding.AcCreateBinding;
import com.tronlinkpro.wallet.R;
public class CreateWalletActivity extends BaseActivity<CreateWalletPresenter, CreateWalletModel> implements CreateWalletContract.View {
    private String _password;
    private String _walletName;
    View actionLayout;
    AppBarLayout appBar;
    private AcCreateBinding binding;
    Button btCreate;
    View btnJump;
    private TextView btnSecurity;
    View createBottomLayout;
    private CreateWalletSuccessFragment createWalletSuccessFragment;
    SimpleDraweeViewGif gifImage;
    private InputWalletDetailFragment inputWalletDetailFragment;
    private boolean isLearnSelected;
    private boolean isShielded;
    ImageView ivLearn;
    View learnLayout;
    private boolean mButtonClickable = false;
    private boolean mIsCreateMode = true;
    TimelineView mTimeLineView;
    NestedScrollView scrollCreateWallet;
    CollapsingToolbarLayout toolBarLayout;
    Toolbar toolbar;
    TextView tvLearnTips;
    TextView tvSecurityTips;

    public static void start(Context context, boolean z) {
        Intent intent = new Intent(context, CreateWalletActivity.class);
        intent.putExtra(TronConfig.WALLET_extra, z);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        AcCreateBinding inflate = AcCreateBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        setView(root, 0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.btCreate = this.binding.btCreate;
        this.scrollCreateWallet = this.binding.contentCreateWallet.scrollCreateWallet;
        this.toolbar = this.binding.toolbar;
        this.toolBarLayout = this.binding.toolbarLayout;
        this.gifImage = this.binding.gifImage;
        this.appBar = this.binding.appBar;
        this.ivLearn = this.binding.ivLearn;
        this.tvLearnTips = this.binding.tvCreateWalletTips;
        this.learnLayout = this.binding.learnLayout;
        this.mTimeLineView = this.binding.contentCreateWallet.timeLineLayout;
        this.actionLayout = this.binding.llAction;
        this.createBottomLayout = this.binding.createLayout;
        this.tvSecurityTips = this.binding.securityTips;
        this.btnJump = this.binding.btnJump;
        this.btnSecurity = this.binding.btnSecurity;
    }

    @Override
    protected void processData() {
        this.isShielded = getIntent().getBooleanExtra(TronConfig.WALLET_extra, false);
        setSupportActionBar(this.toolbar);
        this.toolBarLayout.setTitle(getString(R.string.creat_wallet));
        this.gifImage.setGif(R.drawable.wallet_2, 1);
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        this.mTimeLineView.setStepOne();
        String generateWalletName = WalletNameGeneratorUtils.generateWalletName(0, this.isShielded);
        this.inputWalletDetailFragment = InputWalletDetailFragment.getInstance(generateWalletName);
        this.createWalletSuccessFragment = CreateWalletSuccessFragment.getInstance(generateWalletName);
        this.inputWalletDetailFragment.setOnDataChangeListener(new DataChangeListener() {
            @Override
            public void onDataChangeListener(Boolean bool, String str, String str2) {
                mButtonClickable = bool.booleanValue();
                btCreate.setEnabled(isLearnSelected && bool.booleanValue());
                _walletName = str;
                _password = str2;
            }
        });
        this.btCreate.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                ((CreateWalletPresenter) mPresenter).create(isShielded, _walletName, _password);
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, this.inputWalletDetailFragment).commit();
        this.learnLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateWalletActivity createWalletActivity = CreateWalletActivity.this;
                boolean z = true;
                createWalletActivity.isLearnSelected = !createWalletActivity.isLearnSelected;
                ivLearn.setImageResource(isLearnSelected ? R.mipmap.create_icon_learn_selected : R.mipmap.create_icon_learn_unselect);
                btCreate.setEnabled((isLearnSelected && mButtonClickable) ? false : false);
            }
        });
        TouchDelegateUtils.expandViewTouchDelegate(this.learnLayout, 10, 10, 10, 10);
        this.tvSecurityTips.setText(SpannableUtils.getImageSpannable(AppCompatResources.getDrawable(this, R.mipmap.ic_warning_text), 0, 1, UIUtils.dip2px(14.0f), UIUtils.dip2px(14.0f), getString(R.string.create_wallet_security_content_5)));
        this.btnJump.setOnClickListener(getOnClickListener());
        this.btnSecurity.setOnClickListener(getOnClickListener());
    }

    public void expandedBar() {
        this.appBar.setExpanded(false);
    }

    @Override
    public void showSecurityLayout() {
        boolean z = !this.mIsCreateMode;
        this.mIsCreateMode = z;
        if (z) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, this.inputWalletDetailFragment).commit();
            this.mTimeLineView.setStepOne();
            this.createBottomLayout.setVisibility(View.VISIBLE);
            this.actionLayout.setVisibility(View.GONE);
            this.learnLayout.setVisibility(View.VISIBLE);
            return;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, this.createWalletSuccessFragment).commit();
        this.mTimeLineView.setStepTwo();
        this.createBottomLayout.setVisibility(View.GONE);
        this.actionLayout.setVisibility(View.VISIBLE);
        this.learnLayout.setVisibility(View.GONE);
        this.appBar.setExpanded(true);
        this.scrollCreateWallet.scrollTo(0, 0);
    }

    private View.OnClickListener getOnClickListener() {
        return new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id == R.id.btn_jump) {
                    jumpSecurityPopWindow();
                } else if (id != R.id.btn_security) {
                } else {
                    ((CreateWalletPresenter) mPresenter).setSecurityAction();
                }
            }
        };
    }

    public void jumpSecurityPopWindow() {
        PopupWindowUtil.jumpSecurityPopWindow(this, new PopWindowCallback() {
            @Override
            public void cancel() {
            }

            @Override
            public void continueDo() {
                ((CreateWalletPresenter) mPresenter).jumpSecurity();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (!this.mIsCreateMode) {
            jumpSecurityPopWindow();
        } else {
            exit();
        }
    }
}
