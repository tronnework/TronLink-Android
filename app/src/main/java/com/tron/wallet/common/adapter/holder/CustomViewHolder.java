package com.tron.wallet.common.adapter.holder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.common.adapter.CustomInnerAssetsAdapter;
import com.tron.wallet.common.adapter.RecyclerItemClickListener;
import com.tron.wallet.common.bean.Token;
import com.tron.wallet.common.bean.token.TRC20Output;
import com.tron.wallet.common.components.SwitchView;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.util.Iterator;
import java.util.List;
import org.tron.walletserver.Wallet;
public class CustomViewHolder extends BaseHolder {
    private Context context;
    private ViewGroup.LayoutParams layoutParams;
    View line;
    private final RxManager mRxManager;
    private TRC20Output output;
    RecyclerView rcList;
    RelativeLayout root;
    private Wallet selectedWallet;
    SwitchView switchview;
    private Token token;
    private CustomInnerAssetsAdapter trc10BigAdapter;
    private CustomInnerAssetsAdapter trc10SmallAdapter;
    private CustomInnerAssetsAdapter trc20BigAdapter;
    private CustomInnerAssetsAdapter trc20SmallAdapter;
    TextView tvHiddenPrompt;
    TextView tvTop;
    private int viewType;
    private String walletName;

    public CustomViewHolder(View view, int i, Context context) {
        super(view);
        this.viewType = i;
        this.context = context;
        this.rcList.setLayoutManager(new LinearLayoutManager(context, 1, false));
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        this.selectedWallet = selectedWallet;
        this.walletName = selectedWallet.getWalletName();
        this.mRxManager = new RxManager();
        this.tvTop = (TextView) view.findViewById(R.id.tv_top);
        this.switchview = (SwitchView) view.findViewById(R.id.switchview);
        this.rcList = (RecyclerView) view.findViewById(R.id.rc_list);
        this.root = (RelativeLayout) view.findViewById(R.id.root);
        this.line = view.findViewById(R.id.line);
        this.tvHiddenPrompt = (TextView) view.findViewById(R.id.tv_hidden_prompt);
    }

    public void updateWallsetName() {
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        this.selectedWallet = selectedWallet;
        this.walletName = selectedWallet.getWalletName();
    }

    public void bindTrc20Small(final List<TRC20Output> list) {
        this.tvTop.setText(R.string.custom_5);
        this.tvHiddenPrompt.setVisibility(View.VISIBLE);
        this.switchview.setVisibility(View.VISIBLE);
        this.switchview.setOpened(SpAPI.THIS.getTrc20All(this.walletName));
        this.switchview.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$bindTrc20Small$0(list, view);
            }
        });
        if (list.size() == 0) {
            setVisibility(false);
            return;
        }
        setVisibility(true);
        CustomInnerAssetsAdapter customInnerAssetsAdapter = new CustomInnerAssetsAdapter(this.viewType, null, list);
        this.trc20SmallAdapter = customInnerAssetsAdapter;
        this.rcList.setAdapter(customInnerAssetsAdapter);
        this.rcList.addOnItemTouchListener(new RecyclerItemClickListener(this.context, this.rcList, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onLongItemClick(View view, int i) {
            }

            @Override
            public void onItemClick(View view, int i) {
                output = (TRC20Output) list.get(i);
                setOrRemove20();
                output.isSelected = !output.isSelected;
                trc20SmallAdapter.setTrc20List(list);
                if (switchview.isOpened()) {
                    return;
                }
                switchview.setOpened(true);
            }
        }));
    }

    public void lambda$bindTrc20Small$0(List list, View view) {
        SpAPI.THIS.setTrc20All(this.walletName, this.switchview.isOpened());
        List<String> trc20List = SpAPI.THIS.getTrc20List(this.walletName);
        if (!this.switchview.isOpened()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                TRC20Output tRC20Output = (TRC20Output) it.next();
                tRC20Output.isSelected = false;
                if (!trc20List.contains(tRC20Output.contract_address)) {
                    trc20List.add(tRC20Output.contract_address);
                }
            }
        } else {
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                TRC20Output tRC20Output2 = (TRC20Output) it2.next();
                tRC20Output2.isSelected = true;
                if (trc20List.contains(tRC20Output2.contract_address)) {
                    trc20List.remove(tRC20Output2.contract_address);
                }
            }
        }
        SpAPI.THIS.setTrc20List(this.walletName, trc20List);
        this.mRxManager.post(Event.CUSTOM3, "111");
        this.trc20SmallAdapter.setTrc20List(list);
    }

    public void bindTrc20Big(final List<TRC20Output> list) {
        this.tvTop.setText(R.string.custom_4);
        this.tvHiddenPrompt.setVisibility(View.GONE);
        if (list.size() == 0) {
            setVisibility(false);
            return;
        }
        setVisibility(true);
        CustomInnerAssetsAdapter customInnerAssetsAdapter = new CustomInnerAssetsAdapter(this.viewType, null, list);
        this.trc20BigAdapter = customInnerAssetsAdapter;
        this.rcList.setAdapter(customInnerAssetsAdapter);
        this.rcList.addOnItemTouchListener(new RecyclerItemClickListener(this.context, this.rcList, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onLongItemClick(View view, int i) {
            }

            @Override
            public void onItemClick(View view, int i) {
                output = (TRC20Output) list.get(i);
                setOrRemove20();
                output.isSelected = !output.isSelected;
                trc20BigAdapter.setTrc20List(list);
            }
        }));
    }

    public void setOrRemove20() {
        if (this.output.isSelected) {
            SpAPI.THIS.setTrc20List(this.walletName, this.output.contract_address);
        } else {
            SpAPI.THIS.removeTrc20(this.walletName, this.output.contract_address);
        }
    }

    public void bindTrc10Small(final List<Token> list) {
        this.tvTop.setText(R.string.custom_3);
        this.tvHiddenPrompt.setVisibility(View.VISIBLE);
        this.switchview.setVisibility(View.VISIBLE);
        this.switchview.setOpened(SpAPI.THIS.getTrc10All(this.walletName));
        this.switchview.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$bindTrc10Small$1(list, view);
            }
        });
        if (list.size() == 0) {
            setVisibility(false);
            return;
        }
        setVisibility(true);
        CustomInnerAssetsAdapter customInnerAssetsAdapter = new CustomInnerAssetsAdapter(this.viewType, list, null);
        this.trc10SmallAdapter = customInnerAssetsAdapter;
        this.rcList.setAdapter(customInnerAssetsAdapter);
        this.rcList.addOnItemTouchListener(new RecyclerItemClickListener(this.context, this.rcList, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onLongItemClick(View view, int i) {
            }

            @Override
            public void onItemClick(View view, int i) {
                token = (Token) list.get(i);
                setOrRemove10();
                token.setSelected(!token.isSelected());
                trc10SmallAdapter.setTrc10List(list);
                if (switchview.isOpened()) {
                    return;
                }
                switchview.setOpened(true);
            }
        }));
    }

    public void lambda$bindTrc10Small$1(List list, View view) {
        SpAPI.THIS.setTrc10All(this.walletName, this.switchview.isOpened());
        List<String> trc10List = SpAPI.THIS.getTrc10List(this.walletName);
        if (!this.switchview.isOpened()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Token token = (Token) it.next();
                token.setSelected(false);
                if (!trc10List.contains(token.getId() + "")) {
                    trc10List.add(token.getId() + "");
                }
            }
        } else {
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                Token token2 = (Token) it2.next();
                token2.setSelected(true);
                if (trc10List.contains(token2.getId() + "")) {
                    trc10List.remove(token2.getId() + "");
                }
            }
        }
        SpAPI.THIS.setTrc10List(this.walletName, trc10List);
        this.mRxManager.post(Event.CUSTOM4, "111");
        this.trc10SmallAdapter.setTrc10List(list);
    }

    public void bindTrc10Big(final List<Token> list) {
        this.tvTop.setText(R.string.custom_2);
        this.tvHiddenPrompt.setVisibility(View.GONE);
        if (list.size() == 0) {
            setVisibility(false);
            return;
        }
        setVisibility(true);
        CustomInnerAssetsAdapter customInnerAssetsAdapter = new CustomInnerAssetsAdapter(this.viewType, list, null);
        this.trc10BigAdapter = customInnerAssetsAdapter;
        this.rcList.setAdapter(customInnerAssetsAdapter);
        this.rcList.addOnItemTouchListener(new RecyclerItemClickListener(this.context, this.rcList, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onLongItemClick(View view, int i) {
            }

            @Override
            public void onItemClick(View view, int i) {
                token = (Token) list.get(i);
                setOrRemove10();
                token.setSelected(!token.isSelected());
                trc10BigAdapter.setTrc10List(list);
            }
        }));
    }

    public void setOrRemove10() {
        if (this.token.isSelected()) {
            SpAPI spAPI = SpAPI.THIS;
            String str = this.walletName;
            spAPI.setTrc10List(str, this.token.getId() + "");
            return;
        }
        SpAPI spAPI2 = SpAPI.THIS;
        String str2 = this.walletName;
        spAPI2.removeTrc10(str2, this.token.getId() + "");
    }

    public void setVisibility(boolean z) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) this.itemView.getLayoutParams();
        if (z) {
            layoutParams.height = -2;
            layoutParams.width = -1;
            this.itemView.setVisibility(View.VISIBLE);
        } else {
            this.itemView.setVisibility(View.GONE);
            layoutParams.height = 0;
            layoutParams.width = 0;
        }
        this.itemView.setLayoutParams(layoutParams);
    }

    public void showLine() {
        ViewGroup.LayoutParams layoutParams = this.line.getLayoutParams();
        this.layoutParams = layoutParams;
        layoutParams.height = UIUtils.dip2px(4.0f);
        this.layoutParams.width = -1;
        this.line.setLayoutParams(this.layoutParams);
    }
}
