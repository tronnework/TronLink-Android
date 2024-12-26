package com.tron.wallet.business.addassets2.adapter;

import android.content.Context;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.business.addassets2.adapter.BeanExtraData;
import com.tron.wallet.common.adapter.holder.NoMoreFooterHolder;
import com.tron.wallet.common.bean.Price;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.TokenLogoDraweeView;
import com.tron.wallet.common.task.PriceUpdater;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.ItemAssetsListBinding;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
public class AssetsListAdapter extends RecyclerView.Adapter<BaseHolder> {
    protected static final int ITEM_TYPE_NORMAL = 0;
    protected static final int ITEM_TYPE_NO_MORE = 1;
    protected static final int ITEM_TYPE_TRX = 2;
    private static Price trxPrice = PriceUpdater.getTRX_price();
    private Context context;
    protected ItemCallback itemCallback;
    private TagType showAssetTag;
    private boolean showNoMoreItem;
    protected List<TokenBean> tokens;
    private int[] touchLocation;

    public interface ItemCallback {

        public final class -CC {
            public static void $default$onItemLongClicked(ItemCallback _this, View view, TokenBean tokenBean, int[] iArr, int i) {
            }
        }

        void onItemClicked(TokenBean tokenBean, int i);

        void onItemLongClicked(View view, TokenBean tokenBean, int[] iArr, int i);

        void onItemSelected(TokenBean tokenBean, int i);
    }

    public enum TagType {
        DEFAULT,
        SHOW_721,
        SHOW_ALL
    }

    public List<TokenBean> getTokens() {
        return this.tokens;
    }

    public boolean isShowNoMoreItem() {
        return this.showNoMoreItem;
    }

    public void setShowNoMoreItem(boolean z) {
        this.showNoMoreItem = z;
    }

    public AssetsListAdapter(Context context, ItemCallback itemCallback) {
        this.showNoMoreItem = true;
        this.showAssetTag = TagType.DEFAULT;
        this.touchLocation = new int[2];
        this.context = context;
        this.tokens = new ArrayList();
        this.itemCallback = itemCallback;
    }

    public AssetsListAdapter(Context context, TagType tagType, ItemCallback itemCallback) {
        this.showNoMoreItem = true;
        this.showAssetTag = TagType.DEFAULT;
        this.touchLocation = new int[2];
        this.context = context;
        this.tokens = new ArrayList();
        this.itemCallback = itemCallback;
        this.showAssetTag = tagType;
    }

    public static String formatPrice(double d) {
        Object[] objArr = new Object[2];
        objArr[0] = SpAPI.THIS.isUsdPrice() ? "$" : Html.fromHtml("&yen;");
        objArr[1] = BigDecimalUtils.lessThanOrEqual(Double.valueOf(d), 0) ? StringTronUtil.formatNumber2(d) : StringTronUtil.formatNumber3Truncate(Double.valueOf(d));
        return String.format("%s%s", objArr);
    }

    public int getLastNewAssetsPosition() {
        List<TokenBean> list = this.tokens;
        int i = -1;
        if (list != null) {
            Iterator<TokenBean> it = list.iterator();
            while (it.hasNext() && it.next().isNewAsset()) {
                i++;
            }
        }
        return i;
    }

    public void notifyPriceChanged() {
        trxPrice = PriceUpdater.getTRX_price();
        notifyDataSetChanged();
    }

    public void notifyDataChanged(List<TokenBean> list) {
        this.tokens = list;
        if (list != null && list.size() <= 5) {
            setShowNoMoreItem(false);
        } else {
            setShowNoMoreItem(true);
        }
        notifyDataSetChanged();
    }

    public void notifyDataRemoved(TokenBean tokenBean, int i) {
        this.tokens.remove(tokenBean);
        notifyItemRemoved(i);
    }

    public void notifyDataChanged(TokenBean tokenBean, int i) {
        notifyItemChanged(i);
    }

    @Override
    public int getItemViewType(int i) {
        List<TokenBean> list = this.tokens;
        if (list == null || i >= list.size()) {
            return 1;
        }
        return this.tokens.get(i).getType() == 0 ? 2 : 0;
    }

    public BaseHolder getViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_assets_list, viewGroup, false));
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new NoMoreFooterHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_assets_no_more, viewGroup, false), viewGroup.getContext());
        }
        BaseHolder viewHolder = getViewHolder(viewGroup, i);
        if (i == 2) {
            viewHolder.itemView.getLayoutParams().height = UIUtils.dip2px(70.0f);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final BaseHolder baseHolder, int i) {
        int itemViewType = getItemViewType(i);
        if (itemViewType == 0 || itemViewType == 2) {
            ViewHolder viewHolder = (ViewHolder) baseHolder;
            viewHolder.onBind(this.context, this.tokens.get(i), i, this.tokens.size(), this.showAssetTag);
            baseHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    lambda$onBindViewHolder$0(baseHolder, view);
                }
            });
            baseHolder.itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    boolean lambda$onBindViewHolder$1;
                    lambda$onBindViewHolder$1 = lambda$onBindViewHolder$1(view, motionEvent);
                    return lambda$onBindViewHolder$1;
                }
            });
            baseHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public final boolean onLongClick(View view) {
                    boolean lambda$onBindViewHolder$2;
                    lambda$onBindViewHolder$2 = lambda$onBindViewHolder$2(baseHolder, view);
                    return lambda$onBindViewHolder$2;
                }
            });
            viewHolder.switchIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    lambda$onBindViewHolder$3(baseHolder, view);
                }
            });
            return;
        }
        NoMoreFooterHolder noMoreFooterHolder = (NoMoreFooterHolder) baseHolder;
        List<TokenBean> list = this.tokens;
        noMoreFooterHolder.bindHolder(list != null && list.size() > 5);
    }

    public void lambda$onBindViewHolder$0(BaseHolder baseHolder, View view) {
        ItemCallback itemCallback = this.itemCallback;
        if (itemCallback != null) {
            itemCallback.onItemClicked(this.tokens.get(baseHolder.getAdapterPosition()), baseHolder.getAdapterPosition());
        }
    }

    public boolean lambda$onBindViewHolder$1(View view, MotionEvent motionEvent) {
        if (motionEvent != null && motionEvent.getAction() == 0) {
            this.touchLocation[0] = (int) motionEvent.getX();
            this.touchLocation[1] = (int) motionEvent.getY();
        }
        return false;
    }

    public boolean lambda$onBindViewHolder$2(BaseHolder baseHolder, View view) {
        TokenBean tokenBean = this.tokens.get(baseHolder.getAdapterPosition());
        if (this.itemCallback == null || tokenBean.getType() == 0 || tokenBean.getTop() == 2) {
            return false;
        }
        this.itemCallback.onItemLongClicked(view, tokenBean, this.touchLocation, baseHolder.getAdapterPosition());
        return true;
    }

    public void lambda$onBindViewHolder$3(BaseHolder baseHolder, View view) {
        if (this.itemCallback == null || view.getVisibility() != 0 || baseHolder.getAdapterPosition() == -1) {
            return;
        }
        this.itemCallback.onItemSelected(this.tokens.get(baseHolder.getAdapterPosition()), baseHolder.getAdapterPosition());
    }

    @Override
    public int getItemCount() {
        List<TokenBean> list = this.tokens;
        if (list == null || list.size() == 0) {
            return 0;
        }
        return this.showNoMoreItem ? this.tokens.size() + 1 : this.tokens.size();
    }

    public static class ViewHolder extends BaseHolder {
        public static final int TARGET_ASSETS = 0;
        public static final int TARGET_CUSTOM_TOKENS = 1;
        TextView assetIdTv;
        TextView assetsCountTv;
        TextView assetsNameTv;
        TextView assetsSubNameTv;
        TextView assetsTag;
        TextView assetsValue;
        private ItemAssetsListBinding binding;
        View contentLayout;
        View dividerView;
        private ForegroundColorSpan foregroundColorSpan;
        View innerLayout;
        private boolean isPopSelect;
        View ivAssetsOfficial;
        ImageView ivDefiType;
        ImageView ivNational;
        View ivOfficial;
        ImageView ivScam;
        View layoutAssetsTag;
        TokenLogoDraweeView logoIv;
        private NumberFormat mNumberFormat;
        View newAssetsTip;
        RelativeLayout rlCheat;
        ImageView switchIv;
        View vDisable;

        public ViewHolder(View view, boolean z) {
            this(view);
            this.isPopSelect = z;
        }

        public void mappingId(View view) {
            ItemAssetsListBinding bind = ItemAssetsListBinding.bind(view);
            this.binding = bind;
            this.dividerView = bind.ivDivider;
            this.logoIv = this.binding.ivLogo;
            this.assetsNameTv = this.binding.assetsName;
            this.assetsSubNameTv = this.binding.assetsSubname;
            this.assetsCountTv = this.binding.assetsCount;
            this.assetsValue = this.binding.assetsValue;
            this.assetIdTv = this.binding.assetsId;
            this.switchIv = this.binding.ivSwitch;
            this.innerLayout = this.binding.rlInner;
            this.contentLayout = this.binding.llContent;
            this.newAssetsTip = this.binding.vNewAssetsTip;
            this.assetsTag = this.binding.assetsTag;
            this.layoutAssetsTag = this.binding.llAssetTag;
            this.ivAssetsOfficial = this.binding.ivAssetsOfficial;
            this.rlCheat = this.binding.rlCheat;
            this.vDisable = this.binding.vDisable;
            this.ivOfficial = this.binding.ivOfficialImage;
            this.ivNational = this.binding.ivNational;
            this.ivDefiType = this.binding.ivDefitype;
            this.ivScam = this.binding.ivScam;
        }

        public ViewHolder(View view) {
            super(view);
            this.isPopSelect = false;
            mappingId(view);
            NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
            this.mNumberFormat = numberInstance;
            numberInstance.setMaximumFractionDigits(6);
            this.foregroundColorSpan = new ForegroundColorSpan(view.getContext().getResources().getColor(R.color.blue_3c));
        }

        public void setupToolView(ImageView imageView, TokenBean tokenBean) {
            imageView.setVisibility((tokenBean.type == 0 || tokenBean.getTop() == 2) ? View.GONE : View.VISIBLE);
            if (imageView.getVisibility() == 8) {
                return;
            }
            if (tokenBean.isSelected) {
                imageView.setImageResource(R.mipmap.added_asset);
            } else {
                imageView.setImageResource(R.mipmap.asset_add_icon);
            }
            TouchDelegateUtils.expandViewTouchDelegate(imageView, 20, 20, 20, 20);
        }

        public void onBind(Context context, TokenBean tokenBean, int i, int i2) {
            onBind(context, tokenBean, i, i2, TagType.DEFAULT);
        }

        public void onBind(Context context, TokenBean tokenBean, int i, int i2, TagType tagType) {
            onBind(context, tokenBean, i, i2, tagType, 0);
        }

        private void updateSymbol(Context context, TokenBean tokenBean, BeanExtraData beanExtraData) {
            SpannableString spannableString;
            if (TextUtils.isEmpty(tokenBean.getShortName())) {
                this.assetsNameTv.setText(tokenBean.getName());
            } else if (beanExtraData != null && beanExtraData.getType() == BeanExtraData.Type.SYMBOL) {
                String ellipsizedText = getEllipsizedText(this.assetsNameTv, tokenBean.getShortName());
                int indexOf = ellipsizedText.toLowerCase().indexOf(beanExtraData.getKeyword());
                if (indexOf != -1) {
                    spannableString = new SpannableString(ellipsizedText);
                    spannableString.setSpan(this.foregroundColorSpan, indexOf, beanExtraData.getKeyword().length() + indexOf, 33);
                } else {
                    SpannableString spannableString2 = new SpannableString(tokenBean.getShortName());
                    spannableString2.setSpan(this.foregroundColorSpan, 0, tokenBean.getShortName().length(), 33);
                    spannableString = spannableString2;
                }
                this.assetsNameTv.setText(spannableString);
            } else {
                this.assetsNameTv.setText(tokenBean.getShortName());
            }
        }

        private void updateName(Context context, TokenBean tokenBean, BeanExtraData beanExtraData) {
            SpannableString spannableString;
            if (TextUtils.isEmpty(tokenBean.getName())) {
                this.assetsSubNameTv.setText("");
                return;
            }
            String str = "(" + tokenBean.getName() + ")";
            if (beanExtraData != null && beanExtraData.getType() == BeanExtraData.Type.NAME) {
                String ellipsizedText = getEllipsizedText(this.assetsSubNameTv, str);
                int indexOf = ellipsizedText.toLowerCase().indexOf(beanExtraData.getKeyword());
                if (indexOf != -1) {
                    spannableString = new SpannableString(ellipsizedText);
                    spannableString.setSpan(this.foregroundColorSpan, indexOf, beanExtraData.getKeyword().length() + indexOf, 33);
                } else {
                    SpannableString spannableString2 = new SpannableString(str);
                    spannableString2.setSpan(this.foregroundColorSpan, 1, tokenBean.getName().length() + 1, 33);
                    spannableString = spannableString2;
                }
                this.assetsSubNameTv.setText(spannableString);
                return;
            }
            this.assetsSubNameTv.setText(str);
        }

        public void onBind(android.content.Context r17, com.tron.wallet.common.bean.token.TokenBean r18, int r19, int r20, com.tron.wallet.business.addassets2.adapter.AssetsListAdapter.TagType r21, int r22) {
            


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.addassets2.adapter.AssetsListAdapter.ViewHolder.onBind(android.content.Context, com.tron.wallet.common.bean.token.TokenBean, int, int, com.tron.wallet.business.addassets2.adapter.AssetsListAdapter$TagType, int):void");
        }

        private String getEllipsizedText(TextView textView, String str) {
            return TextUtils.ellipsize(str, textView.getPaint(), UIUtils.dip2px(100.0f), textView.getEllipsize()).toString();
        }
    }

    public static class fun1 {
        static final int[] $SwitchMap$com$tron$wallet$business$addassets2$adapter$AssetsListAdapter$TagType;

        static {
            int[] iArr = new int[TagType.values().length];
            $SwitchMap$com$tron$wallet$business$addassets2$adapter$AssetsListAdapter$TagType = iArr;
            try {
                iArr[TagType.DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$addassets2$adapter$AssetsListAdapter$TagType[TagType.SHOW_721.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$addassets2$adapter$AssetsListAdapter$TagType[TagType.SHOW_ALL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
