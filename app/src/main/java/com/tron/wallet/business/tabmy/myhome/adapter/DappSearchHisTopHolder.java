package com.tron.wallet.business.tabmy.myhome.adapter;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.tabdapp.bean.DappLocalSearchBean;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.business.web.DappOptions;
import com.tron.wallet.business.web.WebOptions;
import com.tron.wallet.common.adapter.RecyclerItemClickListener;
import com.tron.wallet.common.utils.ChainUtil;
import com.tron.wallet.common.utils.rb.RB;
import com.tron.wallet.databinding.ItemDappSearchTopBinding;
import java.util.ArrayList;
import java.util.List;
public class DappSearchHisTopHolder extends BaseHolder {
    private ItemDappSearchTopBinding binding;
    private DappSearchHisInnerAdapter dappSearchHisInnerAdapter;
    private List<DappLocalSearchBean.DataBean> lists;
    private Context mContext;
    RecyclerView rcInner;
    private final RxManager rxManager;

    public DappSearchHisTopHolder(View view, Context context) {
        super(view);
        this.mContext = context;
        mappingId(view);
        this.rxManager = new RxManager();
        this.lists = new ArrayList();
    }

    public void mappingId(View view) {
        ItemDappSearchTopBinding bind = ItemDappSearchTopBinding.bind(view);
        this.binding = bind;
        this.rcInner = bind.rcInner;
    }

    public void onBind(List<DappLocalSearchBean.DataBean> list) {
        this.lists = list;
        DappSearchHisInnerAdapter dappSearchHisInnerAdapter = this.dappSearchHisInnerAdapter;
        if (dappSearchHisInnerAdapter == null) {
            this.dappSearchHisInnerAdapter = new DappSearchHisInnerAdapter(list);
            this.rcInner.setLayoutManager(new LinearLayoutManager(this.mContext, 1, false));
            this.rcInner.addOnItemTouchListener(new RecyclerItemClickListener(this.mContext, this.rcInner, new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onLongItemClick(View view, int i) {
                }

                @Override
                public void onItemClick(View view, int i) {
                    String str;
                    if (lists.size() > i) {
                        DappLocalSearchBean.DataBean dataBean = (DappLocalSearchBean.DataBean) lists.get(i);
                        if (dataBean.url.contains(ChainUtil.Request_HTTP) || dataBean.url.contains("https://")) {
                            str = dataBean.url;
                        } else {
                            str = ChainUtil.Request_HTTP + dataBean.url;
                        }
                        rxManager.post(RB.RB_DAPP_UPDATE, Integer.valueOf(i));
                        WebOptions build = new WebOptions.WebOptionsBuild().addCode(-2).build();
                        if (build != null && IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.NILETEST) {
                            build.setDappOptions(new DappOptions.DappOptionsBuild().addInjectZTron(true).build());
                        }
                        CommonWebActivityV3.start(mContext, str, "", true, build);
                    }
                }
            }));
            this.rcInner.setAdapter(this.dappSearchHisInnerAdapter);
            return;
        }
        dappSearchHisInnerAdapter.notifyData(list);
    }

    public void onDestroy() {
        RxManager rxManager = this.rxManager;
        if (rxManager != null) {
            rxManager.clear();
        }
    }
}
