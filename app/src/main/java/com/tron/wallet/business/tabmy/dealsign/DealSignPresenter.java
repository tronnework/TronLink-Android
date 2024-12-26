package com.tron.wallet.business.tabmy.dealsign;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.gson.Gson;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.business.tabmy.dealsign.DealSignContract;
import com.tron.wallet.business.tabmy.dealsign.signfailure.SignFailureFragment;
import com.tron.wallet.business.tabmy.dealsign.signwait.SignWaitFragment;
import com.tron.wallet.common.bean.DealSignOutput;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.M;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.SocketManager;
import com.tronlinkpro.wallet.R;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Response;
import okhttp3.WebSocket;
public class DealSignPresenter extends DealSignContract.Presenter {
    private static final String TAG = "DealSignPresenter";
    private long enterTime;
    private Gson gson;
    private FragmentManager mFragmentManager;
    private SignWaitFragment signAlreadyFragment;
    private List<DealSignOutput.DataBeanX.DataBean> signAlreadyList;
    private SignFailureFragment signFailureFragment;
    private SignFailureFragment signSuccessFragment;
    private SignWaitFragment signWaitFragment;
    private List<DealSignOutput.DataBeanX.DataBean> signWaitList;
    private SignSocketListener socketListener;

    @Override
    protected void onStart() {
        this.signWaitList = new ArrayList();
        this.signAlreadyList = new ArrayList();
        this.gson = new Gson();
        this.socketListener = new SignSocketListener();
    }

    @Override
    public void addSome(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
        this.enterTime = System.currentTimeMillis();
        initRx();
        this.mRxManager.on(Event.RequestMutilSocketReConnect, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$addSome$0(obj);
            }
        });
    }

    public void lambda$addSome$0(Object obj) throws Exception {
        try {
            if (this.socketListener != null) {
                SocketManager.getInstance().removeListener(this.socketListener);
            }
            SocketManager.getInstance().start(WalletUtils.getWallet(((DealSignContract.View) this.mView).getWalletName()).getAddress(), 0, true);
            SocketManager.getInstance().addListenter(this.socketListener);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startSocketConnectionTimeOut();
                }
            }, 16000L);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    private void initRx() {
        if (WalletUtils.getWallet(((DealSignContract.View) this.mView).getWalletName()) == null) {
            SentryUtil.captureMessage("mView.getWalletName() " + ((DealSignContract.View) this.mView).getWalletName() + "  get Wallet Null");
            return;
        }
        SocketManager.getInstance().start(WalletUtils.getWallet(((DealSignContract.View) this.mView).getWalletName()).getAddress(), 0, true);
        SocketManager.getInstance().addListenter(this.socketListener);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startSocketConnectionTimeOut();
            }
        }, 16000L);
    }

    public void startSocketConnectionTimeOut() {
        ((DealSignContract.View) this.mView).runOnUIThread(new OnMainThread() {
            @Override
            public void doInUIThread() {
                LogUtils.d("alex", "startSocketConnection TimeOut  isSocketConnected:  " + ((DealSignContract.View) mView).getSocketConnect());
                if (signWaitFragment.isVisible() || signAlreadyFragment.isVisible()) {
                    ((DealSignContract.View) mView).dismissLoading();
                    ((DealSignContract.View) mView).dismissLoadingPage();
                    if (signWaitList != null && signWaitList.size() > 0) {
                        if (signWaitFragment != null) {
                            signWaitFragment.refreshData(signWaitList);
                        }
                    } else if (!((DealSignContract.View) mView).getSocketConnect()) {
                        IToast.getIToast().show(R.string.time_out);
                        signWaitFragment.dismissLoadingPage();
                        signWaitFragment.showErrorPage();
                    }
                    if (signAlreadyList != null && signAlreadyList.size() > 0) {
                        if (signAlreadyFragment != null) {
                            signAlreadyFragment.refreshData(signAlreadyList);
                        }
                    } else if (((DealSignContract.View) mView).getSocketConnect()) {
                    } else {
                        IToast.getIToast().show(R.string.time_out);
                        signAlreadyFragment.dismissLoadingPage();
                        signAlreadyFragment.showErrorPage();
                    }
                }
            }
        });
    }

    public void processData(String str) {
        DealSignOutput.DataBeanX dataBeanX;
        this.signWaitList.clear();
        this.signAlreadyList.clear();
        LogUtils.i(TAG, "*************** result: " + str);
        LogUtils.d("alex", "processData: " + ((DealSignContract.View) this.mView).getSocketConnect());
        int i = 0;
        if (!TextUtils.isEmpty(str) && (dataBeanX = (DealSignOutput.DataBeanX) this.gson.fromJson(str,  DealSignOutput.DataBeanX.class)) != null && dataBeanX.data != null && dataBeanX.data.size() > 0) {
            int i2 = 0;
            while (i < dataBeanX.data.size()) {
                if (dataBeanX.data.get(i).isSign == 0) {
                    i2++;
                    this.signWaitList.add(dataBeanX.data.get(i));
                } else {
                    this.signAlreadyList.add(dataBeanX.data.get(i));
                }
                i++;
            }
            i = i2;
        }
        ((DealSignContract.View) this.mView).showOrHideWaitSignCount(i);
        SignWaitFragment signWaitFragment = this.signWaitFragment;
        if (signWaitFragment != null) {
            signWaitFragment.refreshData(this.signWaitList);
        }
        SignWaitFragment signWaitFragment2 = this.signAlreadyFragment;
        if (signWaitFragment2 != null) {
            signWaitFragment2.refreshData(this.signAlreadyList);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        SignWaitFragment signWaitFragment = this.signWaitFragment;
        if (signWaitFragment != null) {
            this.mFragmentManager.putFragment(bundle, M.M_SIGN_WAIT, signWaitFragment);
        }
        SignWaitFragment signWaitFragment2 = this.signAlreadyFragment;
        if (signWaitFragment2 != null) {
            this.mFragmentManager.putFragment(bundle, M.M_SIGN_ALREADY, signWaitFragment2);
        }
        SignFailureFragment signFailureFragment = this.signSuccessFragment;
        if (signFailureFragment != null) {
            this.mFragmentManager.putFragment(bundle, M.M_SIGN_SUCCESS, signFailureFragment);
        }
        SignFailureFragment signFailureFragment2 = this.signFailureFragment;
        if (signFailureFragment2 != null) {
            this.mFragmentManager.putFragment(bundle, M.M_SIGN_FAILURE, signFailureFragment2);
        }
    }

    @Override
    public void onCreate2(Bundle bundle, FragmentManager fragmentManager) {
        if (bundle != null) {
            this.signWaitFragment = (SignWaitFragment) fragmentManager.getFragment(bundle, M.M_SIGN_WAIT);
            this.signAlreadyFragment = (SignWaitFragment) fragmentManager.getFragment(bundle, M.M_SIGN_ALREADY);
            this.signFailureFragment = (SignFailureFragment) fragmentManager.getFragment(bundle, M.M_SIGN_FAILURE);
            this.signSuccessFragment = (SignFailureFragment) fragmentManager.getFragment(bundle, M.M_SIGN_SUCCESS);
        }
        if (this.signWaitFragment == null) {
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag(M.M_SIGN_WAIT);
            if (findFragmentByTag != null) {
                this.signWaitFragment = (SignWaitFragment) findFragmentByTag;
            } else {
                this.signWaitFragment = SignWaitFragment.newInstance(1, ((DealSignContract.View) this.mView).getSocketConnect(), ((DealSignContract.View) this.mView).getWalletName());
            }
        }
        if (this.signAlreadyFragment == null) {
            Fragment findFragmentByTag2 = fragmentManager.findFragmentByTag(M.M_SIGN_ALREADY);
            if (findFragmentByTag2 != null) {
                this.signAlreadyFragment = (SignWaitFragment) findFragmentByTag2;
            } else {
                this.signAlreadyFragment = SignWaitFragment.newInstance(2, ((DealSignContract.View) this.mView).getSocketConnect(), ((DealSignContract.View) this.mView).getWalletName());
            }
        }
        if (this.signFailureFragment == null) {
            Fragment findFragmentByTag3 = fragmentManager.findFragmentByTag(M.M_SIGN_FAILURE);
            if (findFragmentByTag3 != null) {
                this.signFailureFragment = (SignFailureFragment) findFragmentByTag3;
            } else {
                this.signFailureFragment = SignFailureFragment.newInstance(4, ((DealSignContract.View) this.mView).getWalletName());
            }
        }
        if (this.signSuccessFragment == null) {
            Fragment findFragmentByTag4 = fragmentManager.findFragmentByTag(M.M_SIGN_SUCCESS);
            if (findFragmentByTag4 != null) {
                this.signSuccessFragment = (SignFailureFragment) findFragmentByTag4;
            } else {
                this.signSuccessFragment = SignFailureFragment.newInstance(3, ((DealSignContract.View) this.mView).getWalletName());
            }
        }
    }

    @Override
    public void onTabClick(int i) {
        if (i == 0) {
            if (this.signWaitFragment.isAdded()) {
                this.mFragmentManager.beginTransaction().show(this.signWaitFragment).hide(this.signAlreadyFragment).hide(this.signSuccessFragment).hide(this.signFailureFragment).commit();
                return;
            }
            this.mFragmentManager.beginTransaction().remove(this.signWaitFragment).commitAllowingStateLoss();
            this.mFragmentManager.beginTransaction().add(R.id.content, this.signWaitFragment, M.M_SIGN_WAIT).show(this.signWaitFragment).hide(this.signAlreadyFragment).hide(this.signSuccessFragment).hide(this.signFailureFragment).commitAllowingStateLoss();
        } else if (i == 1) {
            if (this.signAlreadyFragment.isAdded()) {
                this.mFragmentManager.beginTransaction().hide(this.signWaitFragment).show(this.signAlreadyFragment).hide(this.signSuccessFragment).hide(this.signFailureFragment).commit();
                return;
            }
            this.mFragmentManager.beginTransaction().remove(this.signAlreadyFragment).commitAllowingStateLoss();
            this.mFragmentManager.beginTransaction().add(R.id.content, this.signAlreadyFragment, M.M_SIGN_ALREADY).hide(this.signWaitFragment).show(this.signAlreadyFragment).hide(this.signSuccessFragment).hide(this.signFailureFragment).commitAllowingStateLoss();
        } else if (i == 2) {
            if (this.signSuccessFragment.isAdded()) {
                this.mFragmentManager.beginTransaction().hide(this.signWaitFragment).hide(this.signAlreadyFragment).show(this.signSuccessFragment).hide(this.signFailureFragment).commit();
                return;
            }
            this.mFragmentManager.beginTransaction().remove(this.signSuccessFragment).commitAllowingStateLoss();
            this.mFragmentManager.beginTransaction().add(R.id.content, this.signSuccessFragment, M.M_SIGN_SUCCESS).hide(this.signWaitFragment).hide(this.signAlreadyFragment).show(this.signSuccessFragment).hide(this.signFailureFragment).commitAllowingStateLoss();
        } else if (i != 3) {
        } else {
            if (this.signFailureFragment.isAdded()) {
                this.mFragmentManager.beginTransaction().hide(this.signWaitFragment).hide(this.signAlreadyFragment).hide(this.signSuccessFragment).show(this.signFailureFragment).commit();
                return;
            }
            this.mFragmentManager.beginTransaction().remove(this.signFailureFragment).commitAllowingStateLoss();
            this.mFragmentManager.beginTransaction().add(R.id.content, this.signFailureFragment, M.M_SIGN_FAILURE).hide(this.signWaitFragment).hide(this.signAlreadyFragment).hide(this.signSuccessFragment).show(this.signFailureFragment).commitAllowingStateLoss();
        }
    }

    public class SignSocketListener implements SocketManager.SocketListener {
        public SignSocketListener() {
        }

        @Override
        public void onMessage(WebSocket webSocket, final String str) {
            ((DealSignContract.View) mView).setSocketConnect(true);
            ((BaseActivity) ((DealSignContract.View) mView).getIContext()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    LogUtils.i(DealSignPresenter.TAG, "*************** RB_DEAL_SIGN: " + Thread.currentThread().getName());
                    if (!TextUtils.isEmpty(str)) {
                        processData(str);
                        return;
                    }
                    LogUtils.d("alex", "socket call back content empty");
                    if (signWaitFragment != null) {
                        signWaitFragment.refreshData(null);
                    }
                    if (signAlreadyFragment != null) {
                        signAlreadyFragment.refreshData(null);
                    }
                }
            });
        }

        @Override
        public void onClosed(WebSocket webSocket, int i, String str) {
            ((DealSignContract.View) mView).setSocketConnect(false);
        }

        @Override
        public void onClosing(WebSocket webSocket, int i, String str) {
            ((DealSignContract.View) mView).setSocketConnect(false);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable th, Response response) {
            ((DealSignContract.View) mView).setSocketConnect(false);
            ((BaseActivity) ((DealSignContract.View) mView).getIContext()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    LogUtils.d("alex", "socket showErrorPage onFailure  SocketConnect: " + ((DealSignContract.View) mView).getSocketConnect());
                    LogUtils.d("alex", "enterTime: " + enterTime + "   CurrentTime:  " + System.currentTimeMillis() + " timeInterval:    " + (System.currentTimeMillis() - enterTime));
                    if (signWaitList == null) {
                        ((DealSignContract.View) mView).showOrHideWaitSignCount(0);
                    } else {
                        ((DealSignContract.View) mView).showOrHideWaitSignCount(signWaitList.size());
                    }
                    if (signWaitList != null && signWaitList.size() > 0) {
                        if (signWaitFragment != null) {
                            signWaitFragment.refreshData(signWaitList);
                        }
                    } else if (signWaitFragment != null && signWaitFragment.isVisible() && System.currentTimeMillis() - enterTime > 300) {
                        signWaitFragment.dismissLoadingPage();
                        signWaitFragment.showErrorPage();
                    }
                    if (signAlreadyList != null && signAlreadyList.size() > 0) {
                        if (signAlreadyFragment != null) {
                            signAlreadyFragment.refreshData(signAlreadyList);
                        }
                    } else if (signAlreadyFragment == null || !signAlreadyFragment.isVisible() || System.currentTimeMillis() - enterTime <= 300) {
                    } else {
                        signAlreadyFragment.dismissLoadingPage();
                        signAlreadyFragment.showErrorPage();
                    }
                }
            });
        }

        @Override
        public void open(WebSocket webSocket, Response response) {
            ((DealSignContract.View) mView).setSocketConnect(true);
        }
    }

    @Override
    public void onDestroy() {
        try {
            if (this.socketListener != null) {
                SocketManager.getInstance().removeListener(this.socketListener);
                this.socketListener = null;
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
        super.onDestroy();
    }
}
