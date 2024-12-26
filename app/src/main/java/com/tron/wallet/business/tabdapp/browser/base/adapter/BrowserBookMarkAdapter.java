package com.tron.wallet.business.tabdapp.browser.base.adapter;

import android.content.Context;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserBookMarkBean;
import com.tron.wallet.common.adapter.holder.NoMoreFooterHolder;
import com.tron.wallet.common.components.RVItemTouchHelper;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.ItemBrowserBookmarkBinding;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class BrowserBookMarkAdapter extends BaseBrowserAdapter<BrowserBookMarkBean> implements RVItemTouchHelper.ItemTouchListener {
    public static final String TAG = "BrowserBookMarkAdapter";
    private boolean isFishedTouch;
    private boolean isSortChanged;
    private ItemCallback itemCallback;
    private int[] touchLocation;
    private RVItemTouchHelper touchhelper;

    public interface ItemCallback {

        public final class -CC {
            public static void $default$onItemLongClicked(ItemCallback _this, View view, BrowserBookMarkBean browserBookMarkBean, int[] iArr, int i) {
            }
        }

        void onItemClicked(View view, BrowserBookMarkBean browserBookMarkBean, int i);

        void onItemLongClicked(View view, BrowserBookMarkBean browserBookMarkBean, int[] iArr, int i);

        void onItemSelected(View view, BrowserBookMarkBean browserBookMarkBean, int i);
    }

    public RVItemTouchHelper getTouchhelper() {
        return this.touchhelper;
    }

    public boolean isSortChanged() {
        return this.isSortChanged;
    }

    public void resetSortChanged() {
        this.isSortChanged = false;
    }

    public void setTouchhelper(RVItemTouchHelper rVItemTouchHelper) {
        this.touchhelper = rVItemTouchHelper;
    }

    public BrowserBookMarkAdapter(Context context, ItemCallback itemCallback) {
        super(context, null);
        this.isSortChanged = false;
        this.touchLocation = new int[2];
        this.isFishedTouch = true;
        this.itemCallback = itemCallback;
    }

    @Override
    public void onMove(RecyclerView recyclerView, int i, int i2) {
        LogUtils.d("BitmapUtils", "onMove  " + i + "  --->   " + i2);
        if (i >= this.datas.size() || i2 >= this.datas.size()) {
            return;
        }
        if (i != i2) {
            this.isSortChanged = true;
        }
        AnalyticsHelper.logEvent(AnalyticsHelper.DAppBookMarkEvent.CLICK_MOVE);
        notifyItemMoved(i, i2);
        BrowserBookMarkBean browserBookMarkBean = (BrowserBookMarkBean) this.datas.remove(i);
        if (i2 < this.datas.size()) {
            this.datas.add(i2, browserBookMarkBean);
        } else {
            this.datas.add(browserBookMarkBean);
        }
    }

    @Override
    public void onSelect(RecyclerView.ViewHolder viewHolder, boolean z) {
        View view = viewHolder.itemView;
        if (viewHolder instanceof BrowserBookMarkHolder) {
            ((BrowserBookMarkHolder) viewHolder).switchIv.setImageResource(z ? R.mipmap.ic_manual_sort : R.mipmap.ic_browser_bookmark_sort);
        }
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
    public BaseHolder getViewHolder(ViewGroup viewGroup, int i) {
        return new BrowserBookMarkHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_browser_bookmark, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(final BaseHolder baseHolder, int i) {
        int itemViewType = getItemViewType(i);
        if (itemViewType == 0 || itemViewType == 2) {
            ((BrowserBookMarkHolder) baseHolder).onBind(this.mContext, (BrowserBookMarkBean) this.datas.get(i), i, this.datas.size());
            baseHolder.itemView.setOnClickListener(new NoDoubleClickListener2() {
                @Override
                protected void onNoDoubleClick(View view) {
                    if (itemCallback != null) {
                        itemCallback.onItemClicked(view, (BrowserBookMarkBean) datas.get(baseHolder.getAdapterPosition()), baseHolder.getAdapterPosition());
                    }
                }
            });
            baseHolder.itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent != null && motionEvent.getAction() == 0) {
                        touchLocation[0] = (int) motionEvent.getX();
                        touchLocation[1] = (int) motionEvent.getY();
                        LogUtils.d(BrowserBookMarkAdapter.TAG, "touchLocation:  " + touchLocation[0] + "  width: " + baseHolder.itemView.getWidth());
                        if (touchLocation[0] <= baseHolder.itemView.getWidth() * 0.75d && touchhelper != null) {
                            touchhelper.setDraggable(false);
                        }
                    } else if (motionEvent != null && ((motionEvent.getAction() == 1 || motionEvent.getAction() == 3) && touchhelper != null)) {
                        touchhelper.setDraggable(true);
                    }
                    return false;
                }
            });
            baseHolder.itemView.setOnDragListener(new View.OnDragListener() {
                @Override
                public boolean onDrag(View view, DragEvent dragEvent) {
                    return false;
                }
            });
            baseHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public final boolean onLongClick(View view) {
                    boolean lambda$onBindViewHolder$0;
                    lambda$onBindViewHolder$0 = lambda$onBindViewHolder$0(baseHolder, view);
                    return lambda$onBindViewHolder$0;
                }
            });
            return;
        }
        ((NoMoreFooterHolder) baseHolder).bindHolder(this.datas != null && this.datas.size() > 5);
    }

    public boolean lambda$onBindViewHolder$0(BaseHolder baseHolder, View view) {
        BrowserBookMarkBean browserBookMarkBean = (BrowserBookMarkBean) this.datas.get(baseHolder.getAdapterPosition());
        if (this.itemCallback != null && this.touchLocation[0] <= baseHolder.itemView.getWidth() * 0.75d) {
            AnalyticsHelper.logEvent(AnalyticsHelper.DAppBookMarkEvent.CLICK_LONG_CLICK);
            this.itemCallback.onItemLongClicked(view, browserBookMarkBean, this.touchLocation, baseHolder.getAdapterPosition());
            return true;
        }
        this.itemCallback.onItemSelected(view, browserBookMarkBean, baseHolder.getAdapterPosition());
        return false;
    }

    @Override
    public int getItemViewType(int i) {
        if (this.datas == null || i >= this.datas.size()) {
            return 1;
        }
        return ((BrowserBookMarkBean) this.datas.get(i)).getType() == 1 ? 2 : 0;
    }

    public List<BrowserBookMarkBean> getDatas() {
        return this.datas;
    }

    @Override
    public int getItemCount() {
        if (this.datas == null || this.datas.size() == 0) {
            return 0;
        }
        return this.showNoMoreItem ? this.datas.size() + 1 : this.datas.size();
    }

    public void remove(BrowserBookMarkBean browserBookMarkBean, int i) {
        this.datas.remove(i);
        if (this.datas.size() == 5) {
            notifyDataSetChanged();
        } else {
            notifyItemRemoved(i);
        }
    }

    public static class BrowserBookMarkHolder extends BaseHolder {
        public LinearLayout liLeft;
        public SimpleDraweeView simpleDraweeView;
        public ImageView switchIv;
        public TextView tvSubtitle;
        public TextView tvTitle;

        public BrowserBookMarkHolder(View view) {
            super(view);
            mappingId(view);
        }

        public void mappingId(View view) {
            ItemBrowserBookmarkBinding bind = ItemBrowserBookmarkBinding.bind(view);
            this.simpleDraweeView = bind.image;
            this.liLeft = bind.liLeft;
            this.tvTitle = bind.tvTitle;
            this.tvSubtitle = bind.tvSubtitle;
            this.switchIv = bind.ivSwitch;
        }

        public void onBind(Context context, BrowserBookMarkBean browserBookMarkBean, int i, int i2) {
            if (StringTronUtil.isEmpty(browserBookMarkBean.getTitle())) {
                this.tvTitle.setVisibility(View.GONE);
            } else {
                this.tvTitle.setVisibility(View.VISIBLE);
                this.tvTitle.setText(browserBookMarkBean.getTitle());
            }
            this.tvSubtitle.setText(browserBookMarkBean.getUrl());
            this.simpleDraweeView.setImageURI(browserBookMarkBean.getIconUrl());
        }
    }
}
