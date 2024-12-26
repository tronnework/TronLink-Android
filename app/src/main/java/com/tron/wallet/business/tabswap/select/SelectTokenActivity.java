package com.tron.wallet.business.tabswap.select;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.wallet.business.tabswap.bean.SwapSelectBean;
import com.tron.wallet.business.tabswap.bean.SwapTokenBean;
import com.tron.wallet.business.tabswap.bean.SwapWhiteListOutput;
import com.tron.wallet.business.tabswap.select.SelectTokenContract;
import com.tron.wallet.common.adapter.SelectTokenAdapter;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.databinding.AcSelectTokenBinding;
import com.tronlinkpro.wallet.R;
import java.util.Iterator;
import java.util.List;
public class SelectTokenActivity extends BaseActivity<SelectTokenPresenter, SelectTokenModel> implements SelectTokenContract.View {
    public static final String KEY_CURRENT_TOKEN = "current_token";
    public static final String KEY_OTHERSIDE_TOKEN = "other_token";
    public static final String KEY_POSITION = "key_position";
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    private AcSelectTokenBinding binding;
    SwapWhiteListOutput.Data currentToken;
    View llNoDataView;
    View mHolderView;
    NoNetView mNoNetView;
    RecyclerView mRecyclerView;
    int mSwapPosition;
    View noNetContainer;
    SwapWhiteListOutput.Data otherSideToken;
    TextView tvNoData;
    TextView tvTitle;

    @Override
    public View getHolderView() {
        return this.mHolderView;
    }

    @Override
    public NoNetView getNonetView() {
        return this.mNoNetView;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return this.mRecyclerView;
    }

    @Override
    public TextView getTitleView() {
        return this.tvTitle;
    }

    @Override
    public void onLeftButtonClick() {
        finish();
    }

    public static void start(Context context, SwapTokenBean swapTokenBean, SwapTokenBean swapTokenBean2, int i) {
        Intent intent = new Intent(context, SelectTokenActivity.class);
        intent.putExtra(KEY_CURRENT_TOKEN, swapTokenBean);
        intent.putExtra(KEY_OTHERSIDE_TOKEN, swapTokenBean2);
        intent.putExtra(KEY_POSITION, i);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        AcSelectTokenBinding inflate = AcSelectTokenBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 1);
        setHeaderBar(getString(R.string.select_a_token));
        mappingId();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.tvTitle = this.binding.tokenNameTitle;
        this.mRecyclerView = this.binding.tokenList;
        this.mHolderView = this.binding.rcHolderList;
        this.noNetContainer = this.binding.tvNoNet;
        this.mNoNetView = this.binding.noNetView;
        this.tvNoData = this.binding.tvNoData;
        this.llNoDataView = this.binding.llNodata;
    }

    @Override
    protected void processData() {
        Intent intent = getIntent();
        this.currentToken = SwapWhiteListOutput.Data.fromSwapTokenBean((SwapTokenBean) intent.getSerializableExtra(KEY_CURRENT_TOKEN));
        this.otherSideToken = SwapWhiteListOutput.Data.fromSwapTokenBean((SwapTokenBean) intent.getSerializableExtra(KEY_OTHERSIDE_TOKEN));
        this.mSwapPosition = intent.getIntExtra(KEY_POSITION, 0);
        getHolderView().setVisibility(View.VISIBLE);
        ((SelectTokenPresenter) this.mPresenter).getTokens();
        this.mNoNetView.setOnReloadClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$processData$0(view);
            }
        });
    }

    public void lambda$processData$0(View view) {
        ((SelectTokenPresenter) this.mPresenter).getTokens();
    }

    private boolean isTrxToWtrx(SwapWhiteListOutput.Data data, SwapWhiteListOutput.Data data2) {
        if (data == null || data2 == null) {
            return false;
        }
        return ("TRX".equalsIgnoreCase(data.getSymbol()) && "WTRX".equalsIgnoreCase(data2.getSymbol())) || ("TRX".equalsIgnoreCase(data2.getSymbol()) && "WTRX".equalsIgnoreCase(data.getSymbol()));
    }

    @Override
    public void updateUI(final List<SwapWhiteListOutput.Data> list) {
        if (list != null && list.size() > 0) {
            this.tvTitle.setVisibility(View.VISIBLE);
            if (this.otherSideToken != null) {
                Iterator<SwapWhiteListOutput.Data> it = list.iterator();
                while (it.hasNext()) {
                    SwapWhiteListOutput.Data next = it.next();
                    if ((TextUtils.isEmpty(this.otherSideToken.getAddress()) && TextUtils.equals(this.otherSideToken.getAddress(), next.getAddress())) || TextUtils.equals(this.otherSideToken.getSymbol(), next.getSymbol()) || isTrxToWtrx(next, this.otherSideToken)) {
                        it.remove();
                    }
                }
            }
            this.mRecyclerView.setHasFixedSize(true);
            this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            final SelectTokenAdapter selectTokenAdapter = new SelectTokenAdapter(list, this.currentToken);
            this.mRecyclerView.setAdapter(selectTokenAdapter);
            selectTokenAdapter.setOnItemClickLitener(new SelectTokenAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int i) {
                    List list2 = list;
                    if (list2 == null || i >= list2.size()) {
                        return;
                    }
                    selectTokenAdapter.setSelection(i);
                    ((SelectTokenPresenter) mPresenter).mRxManager.post(Event.SWAP_SELECTED_TOKEN, new SwapSelectBean((SwapWhiteListOutput.Data) list.get(i), mSwapPosition));
                    finish();
                }
            });
            this.mRecyclerView.setVisibility(View.VISIBLE);
            return;
        }
        this.tvTitle.setVisibility(View.GONE);
        showEmptyView(true);
    }

    @Override
    public void showNoNetError(boolean z) {
        if (z) {
            if (this.mRecyclerView.getVisibility() == 8) {
                this.noNetContainer.setVisibility(View.VISIBLE);
                return;
            }
            return;
        }
        this.noNetContainer.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyView(boolean z) {
        if (z) {
            this.noNetContainer.setVisibility(View.GONE);
            this.llNoDataView.setVisibility(View.VISIBLE);
            return;
        }
        this.llNoDataView.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ((SelectTokenPresenter) this.mPresenter).mRxManager.post(Event.SWAP_SELECTED_TOKEN, 0);
    }
}
