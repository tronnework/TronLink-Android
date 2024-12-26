package com.tron.wallet.common.adapter.nft;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.bean.nft.NftInfoOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftItemInfo;
import com.tron.wallet.business.addassets2.bean.nft.NftTypeInfo;
import com.tron.wallet.business.nft.NftTokenItemDetailActivity;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.CustomLoadMoreView;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.UIUtils;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
public class NftListAdapter extends BaseQuickAdapter<NftItemInfo, BaseViewHolder> {
    private static final int TYPE_HEADER = 10000;
    private static final int TYPE_ITEM = 10001;
    private final Context context;
    private CopyClickListener copyClickListener;
    private List<String> customTokenNoFunctions;
    private final String homePage;
    private NoNetView noDataView;
    private NftInfoOutput result;
    private final String shortName;
    private TokenBean tokenBean;
    private final String walletAddress;

    public interface CopyClickListener {
        void arrowClick();

        void copy(String str);
    }

    public void setTokenBean(TokenBean tokenBean) {
        this.tokenBean = tokenBean;
    }

    public NftListAdapter(Context context, final String str, String str2, final String str3, CopyClickListener copyClickListener) {
        super(R.layout.nft_list_item, new ArrayList());
        this.context = context;
        this.walletAddress = str;
        this.homePage = str2;
        this.shortName = str3;
        this.copyClickListener = copyClickListener;
        addHeaderView(View.inflate(context, R.layout.nft_list_header, null), 0);
        setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                lambda$new$0(str, str3, baseQuickAdapter, view, i);
            }
        });
        CustomLoadMoreView customLoadMoreView = new CustomLoadMoreView();
        customLoadMoreView.setFailOnFixedTime(false);
        setLoadMoreView(customLoadMoreView);
        addNoDataView(context);
        setHeaderAndEmpty(true);
    }

    public void lambda$new$0(String str, String str2, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        String str3;
        String str4;
        NftItemInfo nftItemInfo = (NftItemInfo) baseQuickAdapter.getItem(i);
        if (nftItemInfo == null) {
            return;
        }
        NftInfoOutput nftInfoOutput = this.result;
        if (nftInfoOutput == null || nftInfoOutput.getData() == null || this.result.getData().getLogoUrl() == null) {
            str3 = "";
            str4 = str3;
        } else {
            str4 = this.result.getData().getLogoUrl();
            str3 = this.result.getData().getFullName();
        }
        NftTokenItemDetailActivity.start(view.getContext(), nftItemInfo, str, str2, str3, this.tokenBean, str4);
        AnalyticsHelper.logEvent(AnalyticsHelper.TokenDetailsPage.CLICK_NFT_DETAIL_PAGE_ITEM);
    }

    public void updateData(NftInfoOutput nftInfoOutput, boolean z) {
        this.result = nftInfoOutput;
        if (nftInfoOutput == null || !nftInfoOutput.isValidResponse()) {
            return;
        }
        List<NftItemInfo> collectionInfoList = nftInfoOutput.getData().getCollectionInfoList();
        if (getData().isEmpty() || z) {
            setNewData(collectionInfoList);
            LogUtils.w("NftListAdapter", "updateData->setNewData\n" + nftInfoOutput.toString());
            return;
        }
        addData((Collection) collectionInfoList);
        LogUtils.w("NftListAdapter", "updateData->addData\n" + nftInfoOutput.toString());
    }

    public void setCustomTokenNoFunctions(List<String> list) {
        this.customTokenNoFunctions = list;
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder createBaseViewHolder(View view) {
        if ((view instanceof LinearLayout) || view.equals(getHeaderLayout())) {
            return new NftHeaderHolder(getHeaderLayout());
        }
        return super.createBaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder baseViewHolder, int i) {
        NftInfoOutput nftInfoOutput;
        if (baseViewHolder.getItemViewType() == 273 && (baseViewHolder instanceof NftHeaderHolder) && (nftInfoOutput = this.result) != null) {
            NftHeaderHolder nftHeaderHolder = (NftHeaderHolder) baseViewHolder;
            nftHeaderHolder.onBind(nftInfoOutput.getData(), this.tokenBean, this.homePage, this.customTokenNoFunctions);
            nftHeaderHolder.ivCopy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    copyClickListener.copy(result.getData().getTokenAddress());
                }
            });
            nftHeaderHolder.ivArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    copyClickListener.arrowClick();
                }
            });
            return;
        }
        super.onBindViewHolder((NftListAdapter) baseViewHolder, i);
    }

    @Override
    public BaseViewHolder onCreateDefViewHolder(ViewGroup viewGroup, int i) {
        return new NftListHolder(getItemView(this.mLayoutResId, viewGroup));
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder, NftItemInfo nftItemInfo) {
        if (baseViewHolder instanceof NftListHolder) {
            NftTypeInfo data = this.result.getData();
            ((NftListHolder) baseViewHolder).onBind(nftItemInfo, data == null ? "" : data.getFullName());
        }
    }

    private void addNoDataView(Context context) {
        if (this.noDataView == null) {
            NoNetView noNetView = new NoNetView(context);
            this.noDataView = noNetView;
            noNetView.setReloadable(false);
            this.noDataView.setIcon(R.mipmap.no_collectibles);
            this.noDataView.setReloadDescription(R.string.no_collectibles);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.topMargin = UIUtils.dip2px(35.0f);
            this.noDataView.setLayoutParams(layoutParams);
        }
        setEmptyView(this.noDataView);
    }
}
