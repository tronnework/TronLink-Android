package com.tron.wallet.business.tabmy.myhome;

import android.content.Intent;
import android.os.Handler;
import android.os.Process;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.welcome.WelcomeActivity;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.databinding.AcSwitchVersionBinding;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
public class SwitchVersionActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    private AcSwitchVersionBinding binding;
    private int currentSelVers;
    private RxManager mRxManager;
    RelativeLayout rlNile;
    RelativeLayout rlOnline;
    RelativeLayout rlPre;
    RelativeLayout rlShasta;
    RelativeLayout rlTest;
    ImageView selectNile;
    ImageView selectOnline;
    ImageView selectPre;
    ImageView selectShasta;
    ImageView selectTest;

    @Override
    protected void setLayout() {
        AcSwitchVersionBinding inflate = AcSwitchVersionBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        LinearLayout root = inflate.getRoot();
        mappingId();
        setClick();
        setView(root, 1);
        setHeaderBar(getString(R.string.setting));
    }

    public void mappingId() {
        this.selectOnline = this.binding.selectOnline;
        this.rlOnline = this.binding.rlOnline;
        this.selectPre = this.binding.selectPre;
        this.rlPre = this.binding.rlPre;
        this.selectTest = this.binding.selectTest;
        this.rlTest = this.binding.rlTest;
        this.rlNile = this.binding.rlNile;
        this.rlShasta = this.binding.rlShasta;
        this.selectNile = this.binding.selectNile;
        this.selectShasta = this.binding.selectShasta;
    }

    @Override
    protected void processData() {
        this.mRxManager = new RxManager();
        this.rlPre.setVisibility(View.GONE);
        this.rlTest.setVisibility(View.GONE);
        this.rlShasta.setVisibility(View.GONE);
        int testVersion = SpAPI.THIS.getTestVersion();
        this.currentSelVers = testVersion;
        updateUi(testVersion);
    }

    public void updateUi(int i) {
        if (i == 1) {
            this.selectOnline.setBackgroundResource(R.mipmap.chain_select);
            this.selectPre.setBackgroundResource(R.mipmap.chain_unselect);
            this.selectTest.setBackgroundResource(R.mipmap.chain_unselect);
            this.selectNile.setBackgroundResource(R.mipmap.chain_unselect);
            SpAPI.THIS.setShasta(false);
        } else if (i == 2) {
            this.selectOnline.setBackgroundResource(R.mipmap.chain_unselect);
            this.selectPre.setBackgroundResource(R.mipmap.chain_select);
            this.selectTest.setBackgroundResource(R.mipmap.chain_unselect);
            this.selectNile.setBackgroundResource(R.mipmap.chain_unselect);
            SpAPI.THIS.setShasta(false);
        } else if (i == 3) {
            this.selectOnline.setBackgroundResource(R.mipmap.chain_unselect);
            this.selectPre.setBackgroundResource(R.mipmap.chain_unselect);
            this.selectTest.setBackgroundResource(R.mipmap.chain_select);
            this.selectNile.setBackgroundResource(R.mipmap.chain_unselect);
            SpAPI.THIS.setShasta(false);
        } else if (i == 4) {
            this.selectOnline.setBackgroundResource(R.mipmap.chain_unselect);
            this.selectPre.setBackgroundResource(R.mipmap.chain_unselect);
            this.selectTest.setBackgroundResource(R.mipmap.chain_unselect);
            this.selectNile.setBackgroundResource(R.mipmap.chain_select);
            SpAPI.THIS.setShasta(false);
        } else if (i != 5) {
        } else {
            this.selectOnline.setBackgroundResource(R.mipmap.chain_unselect);
            this.selectPre.setBackgroundResource(R.mipmap.chain_unselect);
            this.selectTest.setBackgroundResource(R.mipmap.chain_unselect);
            this.selectNile.setBackgroundResource(R.mipmap.chain_unselect);
            this.selectShasta.setBackgroundResource(R.mipmap.chain_select);
            SpAPI.THIS.setShasta(true);
        }
    }

    public void setClick() {
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                switch (view.getId()) {
                    case R.id.rl_nile:
                        if (currentSelVers != 4) {
                            SpAPI.THIS.setTestVersion(4);
                            updateUi(4);
                            SpAPI.THIS.setCurrentChainName("MainChain");
                            resetNodeInfo();
                            reStartApp();
                            return;
                        }
                        return;
                    case R.id.rl_online:
                        if (currentSelVers != 1) {
                            SpAPI.THIS.setTestVersion(1);
                            updateUi(1);
                            SpAPI.THIS.setCurrentChainName("MainChain");
                            resetNodeInfo();
                            reStartApp();
                            return;
                        }
                        return;
                    case R.id.rl_pre:
                        if (currentSelVers != 2) {
                            SpAPI.THIS.setTestVersion(2);
                            updateUi(2);
                            SpAPI.THIS.setCurrentChainName("MainChain");
                            resetNodeInfo();
                            reStartApp();
                            return;
                        }
                        return;
                    case R.id.rl_shasta:
                        if (currentSelVers != 5) {
                            SpAPI.THIS.setTestVersion(5);
                            updateUi(5);
                            SpAPI.THIS.setCurrentChainName("MainChain");
                            resetNodeInfo();
                            reStartApp();
                            return;
                        }
                        return;
                    case R.id.rl_test:
                        if (currentSelVers != 3) {
                            SpAPI.THIS.setTestVersion(3);
                            updateUi(3);
                            SpAPI.THIS.setCurrentChainName("MainChain");
                            resetNodeInfo();
                            reStartApp();
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        };
        this.binding.rlOnline.setOnClickListener(noDoubleClickListener2);
        this.binding.rlTest.setOnClickListener(noDoubleClickListener2);
        this.binding.rlPre.setOnClickListener(noDoubleClickListener2);
        this.binding.rlNile.setOnClickListener(noDoubleClickListener2);
        this.binding.rlShasta.setOnClickListener(noDoubleClickListener2);
    }

    public void resetNodeInfo() {
        SpAPI.THIS.setIsCustomFull(false);
        SpAPI.THIS.setIsDappCustomSol(false);
        SpAPI.THIS.setAllChainJson(null);
        SpAPI.THIS.setCurrentChainName("");
        SpAPI.THIS.setNotNileChainNoticeTimes(0);
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }

    public void reStartApp() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SwitchVersionActivity.this, WelcomeActivity.class);
                intent.setFlags(268468224);
                startActivity(intent);
                Process.killProcess(Process.myPid());
            }
        }, 500L);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxManager rxManager = this.mRxManager;
        if (rxManager != null) {
            rxManager.clear();
        }
    }
}
