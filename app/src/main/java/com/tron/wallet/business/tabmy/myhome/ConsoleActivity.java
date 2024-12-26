package com.tron.wallet.business.tabmy.myhome;

import android.os.Handler;
import android.os.Looper;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.utils.ThreadPoolManager;
import com.tron.wallet.common.adapter.ConsoleAdapter;
import com.tron.wallet.common.bean.ColdFailLogBean;
import com.tron.wallet.databinding.AcConsoleBinding;
import com.tron.wallet.db.Controller.ColdFailLogBeanDaoManager;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class ConsoleActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    private static final String TAG = "ConsoleActivity";
    private AcConsoleBinding binding;
    private ConsoleAdapter consoleAdapter;
    RecyclerView rlContent;

    @Override
    protected void setLayout() {
        AcConsoleBinding inflate = AcConsoleBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        setView(root, 3);
        setHeaderBar(getString(R.string.console));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.rlContent = this.binding.rlContent;
    }

    @Override
    protected void processData() {
        this.rlContent.setLayoutManager(new LinearLayoutManager(this.mContext, 1, false));
        ThreadPoolManager.newInstance().addExecuteTask(new Runnable() {
            @Override
            public final void run() {
                lambda$processData$0();
            }
        });
    }

    public void lambda$processData$0() {
        final List<ColdFailLogBean> allFailData = ColdFailLogBeanDaoManager.getAllFailData();
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                dismissLoadingPage();
                List list = allFailData;
                if (list == null || list.size() == 0) {
                    showNoDatasPage();
                } else if (consoleAdapter == null) {
                    consoleAdapter = new ConsoleAdapter(ConsoleActivity.this, allFailData);
                    rlContent.setAdapter(consoleAdapter);
                } else {
                    consoleAdapter.notify(allFailData);
                }
            }
        });
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }
}
