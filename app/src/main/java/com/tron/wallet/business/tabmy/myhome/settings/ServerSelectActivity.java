package com.tron.wallet.business.tabmy.myhome.settings;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxBus;
import com.tron.wallet.business.tabmy.myhome.settings.ServerSelectActivity;
import com.tron.wallet.common.components.SwitchButton;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.databinding.ActivityServerSelectBinding;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
import java.io.IOException;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.cli.HelpFormatter;
public class ServerSelectActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    private ActivityServerSelectBinding binding;
    LinearLayout chooseServerLayout;
    private boolean isSIGAvailable;
    private boolean isUSAvailable;
    ImageView ivSelectSIGAPORE;
    ImageView ivSelectUSA;
    private OkHttpClient okHttpClient;
    RelativeLayout rlSigapore;
    RelativeLayout rlUsa;
    private int serverId;
    private boolean stopped;
    SwitchButton switchButton;
    private ScheduledFuture<?> taskFuture;
    TextView time1;
    TextView time2;
    TextView tvSingapore;
    TextView tvTips;
    TextView tvUSA;

    @Override
    protected void setLayout() {
        ActivityServerSelectBinding inflate = ActivityServerSelectBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        LinearLayout root = inflate.getRoot();
        mappingId();
        setClick();
        setView(root, 1);
        setHeaderBar(getString(R.string.server_setting));
    }

    public void mappingId() {
        this.rlUsa = this.binding.usaLayout;
        this.rlSigapore = this.binding.sigaporLayout;
        this.ivSelectUSA = this.binding.ivSelect1;
        this.ivSelectSIGAPORE = this.binding.ivSelect2;
        this.tvUSA = this.binding.tvNodeName;
        this.tvSingapore = this.binding.tvNodeName2;
        this.time1 = this.binding.delaytime1;
        this.time2 = this.binding.delaytime2;
        this.switchButton = this.binding.switchButton;
        this.tvTips = this.binding.tips;
        this.chooseServerLayout = this.binding.llChooseServer;
    }

    @Override
    protected void processData() {
        initAutoSwitchView();
        this.okHttpClient = new OkHttpClient();
        this.taskFuture = Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateUSASpeed();
                updateSigaporeSpeed();
            }
        }, 0L, 2000L, TimeUnit.MILLISECONDS);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (SpAPI.THIS.isServerAuto()) {
            int i = this.serverId;
            if (i == 0 && !this.isUSAvailable && this.isSIGAvailable) {
                SpAPI.THIS.setServerUserPrefer(1);
                RxBus.getInstance().post(Event.SWITCH_SERVER, 3);
            } else if (i == 1 && !this.isSIGAvailable && this.isUSAvailable) {
                SpAPI.THIS.setServerUserPrefer(0);
                RxBus.getInstance().post(Event.SWITCH_SERVER, 3);
            } else {
                RxBus.getInstance().post(Event.SWITCH_SERVER, 4);
            }
        }
    }

    private void initAutoSwitchView() {
        this.switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton switchButton, boolean z) {
                SpAPI.THIS.setServerAuto(z);
                updateUI();
            }
        });
        updateUI();
    }

    public void updateUI() {
        this.serverId = SpAPI.THIS.getServerUserPrefer();
        if (SpAPI.THIS.isServerAuto()) {
            this.switchButton.setChecked(true);
            enableClick(false);
            this.tvTips.setText(R.string.open_tips);
            if (this.serverId == 0) {
                this.ivSelectUSA.setBackgroundResource(R.mipmap.icon_server_selected);
                this.ivSelectUSA.setVisibility(View.VISIBLE);
                this.ivSelectSIGAPORE.setVisibility(View.INVISIBLE);
                return;
            }
            this.ivSelectSIGAPORE.setBackgroundResource(R.mipmap.icon_server_selected);
            this.ivSelectSIGAPORE.setVisibility(View.VISIBLE);
            this.ivSelectUSA.setVisibility(View.INVISIBLE);
            return;
        }
        this.switchButton.setChecked(false);
        enableClick(true);
        this.tvTips.setText(R.string.close_tips);
        this.ivSelectSIGAPORE.setVisibility(View.VISIBLE);
        this.ivSelectUSA.setVisibility(View.VISIBLE);
        if (this.serverId == 0) {
            this.ivSelectUSA.setBackgroundResource(R.mipmap.ic_check_selected);
            this.ivSelectSIGAPORE.setBackgroundResource(R.mipmap.ic_check_unselect);
            return;
        }
        this.ivSelectUSA.setBackgroundResource(R.mipmap.ic_check_unselect);
        this.ivSelectSIGAPORE.setBackgroundResource(R.mipmap.ic_check_selected);
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        AnalyticsHelper.logEvent(AnalyticsHelper.ChangeServer.CLICK_CHANGE_SERVER_CLICK_BACK);
        finish();
    }

    public void setClick() {
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id == R.id.sigapor_layout) {
                    userSelect(1);
                } else if (id != R.id.usa_layout) {
                } else {
                    userSelect(0);
                }
            }
        };
        this.binding.usaLayout.setOnClickListener(noDoubleClickListener2);
        this.binding.sigaporLayout.setOnClickListener(noDoubleClickListener2);
    }

    public void userSelect(int i) {
        SpAPI.THIS.setServerUserPrefer(i);
        if (this.serverId != i) {
            RxBus.getInstance().post(Event.SWITCH_SERVER, 3);
        }
        this.serverId = i;
        if (i == 0) {
            this.ivSelectUSA.setBackgroundResource(R.mipmap.ic_check_selected);
            this.ivSelectSIGAPORE.setBackgroundResource(R.mipmap.ic_check_unselect);
            return;
        }
        this.ivSelectUSA.setBackgroundResource(R.mipmap.ic_check_unselect);
        this.ivSelectSIGAPORE.setBackgroundResource(R.mipmap.ic_check_selected);
    }

    public void updateUSASpeed() {
        FirebasePerfOkHttpClient.enqueue(this.okHttpClient.newCall(new Request.Builder().url("https://list.tronlink.org/api/v1/wallet/getCoinCapTrxPrice").get().build()), new fun4(System.currentTimeMillis()));
    }

    public class fun4 implements Callback {
        final long val$startTime;

        fun4(long j) {
            this.val$startTime = j;
        }

        @Override
        public void onFailure(Call call, IOException iOException) {
            runOnUiThread(new Runnable() {
                @Override
                public final void run() {
                    ServerSelectActivity.4.this.lambda$onFailure$0();
                }
            });
            isUSAvailable = false;
        }

        public void lambda$onFailure$0() {
            time1.setText(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            isUSAvailable = true;
            try {
                response.close();
            } catch (Exception e) {
                LogUtils.e(e);
            }
            if (stopped) {
                return;
            }
            final long currentTimeMillis = System.currentTimeMillis();
            ServerSelectActivity serverSelectActivity = ServerSelectActivity.this;
            final long j = this.val$startTime;
            serverSelectActivity.runOnUiThread(new Runnable() {
                @Override
                public final void run() {
                    ServerSelectActivity.4.this.lambda$onResponse$1(currentTimeMillis, j);
                }
            });
        }

        public void lambda$onResponse$1(long j, long j2) {
            TextView textView = time1;
            textView.setText((j - j2) + " ms");
        }
    }

    public void updateSigaporeSpeed() {
        FirebasePerfOkHttpClient.enqueue(this.okHttpClient.newCall(new Request.Builder().url("http://52.76.86.154:8080/api/v1/wallet/getCoinCapTrxPrice").get().build()), new fun5(System.currentTimeMillis()));
    }

    public class fun5 implements Callback {
        final long val$startTime;

        fun5(long j) {
            this.val$startTime = j;
        }

        @Override
        public void onFailure(Call call, IOException iOException) {
            runOnUiThread(new Runnable() {
                @Override
                public final void run() {
                    ServerSelectActivity.5.this.lambda$onFailure$0();
                }
            });
            isSIGAvailable = false;
        }

        public void lambda$onFailure$0() {
            time2.setText(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            try {
                response.close();
            } catch (Exception e) {
                LogUtils.e(e);
            }
            if (stopped) {
                return;
            }
            isSIGAvailable = true;
            final long currentTimeMillis = System.currentTimeMillis();
            ServerSelectActivity serverSelectActivity = ServerSelectActivity.this;
            final long j = this.val$startTime;
            serverSelectActivity.runOnUiThread(new Runnable() {
                @Override
                public final void run() {
                    ServerSelectActivity.5.this.lambda$onResponse$1(currentTimeMillis, j);
                }
            });
        }

        public void lambda$onResponse$1(long j, long j2) {
            TextView textView = time2;
            textView.setText((j - j2) + " ms");
        }
    }

    private void stopTimer() {
        this.stopped = true;
        ScheduledFuture<?> scheduledFuture = this.taskFuture;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
    }

    public void enableClick(boolean z) {
        this.rlSigapore.setEnabled(z);
        this.rlUsa.setEnabled(z);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopTimer();
    }
}
