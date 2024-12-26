package com.tron.wallet.business.tabmy.myhome;

import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.tabdapp.bean.DappLocalSearchBean;
import com.tron.wallet.business.tabmy.myhome.adapter.DappSearchHisAdapter;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.business.web.DappOptions;
import com.tron.wallet.business.web.WebOptions;
import com.tron.wallet.common.utils.ChainUtil;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.rb.RB;
import com.tron.wallet.databinding.AcDappdemoBinding;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
public class DappBrowserActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    private AcDappdemoBinding binding;
    private DappSearchHisAdapter dappSearchHisAdapter;
    EditText etUrl;
    private DappLocalSearchBean localDappSearch;
    RecyclerView rcContent;
    private RxManager rxManager;

    @Override
    protected void setLayout() {
        AcDappdemoBinding inflate = AcDappdemoBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        LinearLayout root = inflate.getRoot();
        mappingId();
        setClick();
        setView(root, 1);
        setHeaderBar(getString(R.string.dapptest));
    }

    public void mappingId() {
        this.etUrl = this.binding.etUrl;
        this.rcContent = this.binding.rcContent;
    }

    @Override
    protected void processData() {
        this.localDappSearch = SpAPI.THIS.getLocalDappSearch();
        updateLocalSearch();
        this.dappSearchHisAdapter = new DappSearchHisAdapter(this.localDappSearch.dataBean, this.mContext);
        this.rcContent.setLayoutManager(new LinearLayoutManager(this.mContext, 1, false));
        this.rcContent.setAdapter(this.dappSearchHisAdapter);
        RxManager rxManager = new RxManager();
        this.rxManager = rxManager;
        rxManager.on(RB.RB_DAPP_CLEAR, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$processData$0(obj);
            }
        });
        this.rxManager.on(RB.RB_DAPP_UPDATE, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$processData$2(obj);
            }
        });
        this.etUrl.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean lambda$processData$3;
                lambda$processData$3 = lambda$processData$3(textView, i, keyEvent);
                return lambda$processData$3;
            }
        });
    }

    public void lambda$processData$0(Object obj) throws Exception {
        DappLocalSearchBean dappLocalSearchBean = this.localDappSearch;
        if (dappLocalSearchBean != null && dappLocalSearchBean.dataBean != null) {
            this.localDappSearch.dataBean.clear();
        }
        updateLocalSearch();
        SpAPI.THIS.setLocalDappSearch(this.localDappSearch);
        this.dappSearchHisAdapter.notifyData(this.localDappSearch.dataBean);
    }

    public void lambda$processData$2(Object obj) throws Exception {
        int intValue;
        DappLocalSearchBean dappLocalSearchBean;
        if (!(obj instanceof Integer) || (intValue = ((Integer) obj).intValue()) == 0 || (dappLocalSearchBean = this.localDappSearch) == null || dappLocalSearchBean.dataBean == null || this.localDappSearch.dataBean.size() < intValue) {
            return;
        }
        DappLocalSearchBean.DataBean dataBean = this.localDappSearch.dataBean.get(intValue);
        this.localDappSearch.dataBean.remove(intValue);
        if (this.localDappSearch.dataBean.size() == 0) {
            this.localDappSearch.dataBean.add(0, dataBean);
        } else {
            this.localDappSearch.dataBean.add(1, dataBean);
        }
        SpAPI.THIS.setLocalDappSearch(this.localDappSearch);
        new Handler().postDelayed(new Runnable() {
            @Override
            public final void run() {
                lambda$processData$1();
            }
        }, 1500L);
    }

    public void lambda$processData$1() {
        this.dappSearchHisAdapter.notifyData(this.localDappSearch.dataBean);
    }

    public boolean lambda$processData$3(TextView textView, int i, KeyEvent keyEvent) {
        if (i == 4 || i == 6 || i == 5 || (keyEvent != null && 66 == keyEvent.getKeyCode() && keyEvent.getAction() == 0)) {
            toWeb();
            return false;
        }
        return false;
    }

    private void updateLocalSearch() {
        DappLocalSearchBean dappLocalSearchBean = this.localDappSearch;
        if (dappLocalSearchBean == null || dappLocalSearchBean.dataBean == null || this.localDappSearch.dataBean.size() == 0) {
            this.localDappSearch = new DappLocalSearchBean();
            addBean("http://dapp.tronlink.org/#/");
        }
    }

    private void addBean(String str) {
        DappLocalSearchBean.DataBean dataBean = new DappLocalSearchBean.DataBean();
        dataBean.url = str;
        if (this.localDappSearch.dataBean == null) {
            this.localDappSearch.dataBean = new ArrayList();
        }
        if (this.localDappSearch.dataBean.size() == 0) {
            this.localDappSearch.dataBean.add(0, dataBean);
        } else {
            this.localDappSearch.dataBean.add(1, dataBean);
        }
    }

    public void setClick() {
        this.binding.bt.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                toWeb();
            }
        });
    }

    public void toWeb() {
        String text = StringTronUtil.getText(this.etUrl);
        if (!StringTronUtil.isEmpty(text)) {
            updateLocalSearch();
            if (this.localDappSearch.dataBean.size() == 20) {
                this.localDappSearch.dataBean.remove(19);
            }
            addBean(text);
            if (!text.contains(ChainUtil.Request_HTTP) && !text.contains("https://")) {
                text = "https://" + text;
            }
            SpAPI.THIS.setLocalDappSearch(this.localDappSearch);
            this.dappSearchHisAdapter.notifyData(this.localDappSearch.dataBean);
            WebOptions build = new WebOptions.WebOptionsBuild().addCode(-2).build();
            if (build != null && IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.NILETEST) {
                build.setDappOptions(new DappOptions.DappOptionsBuild().addInjectZTron(true).build());
            }
            CommonWebActivityV3.start(this.mContext, text, "", true, build);
            return;
        }
        toast(getString(R.string.trx_empty));
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxManager rxManager = this.rxManager;
        if (rxManager != null) {
            rxManager.clear();
        }
        DappSearchHisAdapter dappSearchHisAdapter = this.dappSearchHisAdapter;
        if (dappSearchHisAdapter != null) {
            dappSearchHisAdapter.onDestroy();
        }
    }
}
