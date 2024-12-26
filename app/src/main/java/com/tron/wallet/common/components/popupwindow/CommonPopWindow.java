package com.tron.wallet.common.components.popupwindow;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.common.adapter.RecyclerItemClickListener;
import com.tron.wallet.common.bean.Item;
import com.tron.wallet.databinding.ItemPopCommonBinding;
import com.tron.wallet.databinding.PopSeletedBinding;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class CommonPopWindow extends PopupWindow {
    PopSeletedBinding binding;
    private Activity context;
    LinearLayout llContent;
    private List<Item> mLists;
    private View mMenuView;
    RecyclerView rcList;

    public interface OnItemClickListener {
        void onItem(int i);
    }

    public CommonPopWindow(Activity activity, List<Item> list) {
        super(activity);
        this.mLists = list;
        this.context = activity;
        View inflate = ((LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.pop_seleted, (ViewGroup) null);
        this.mMenuView = inflate;
        this.binding = PopSeletedBinding.bind(inflate);
        mappingIds();
        setContentView(this.mMenuView);
        setWidth(-1);
        setHeight(-1);
        setFocusable(true);
        setBackgroundDrawable(null);
        initData(activity);
        this.mMenuView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int top = mMenuView.findViewById(R.id.ll_content).getTop();
                int y = (int) motionEvent.getY();
                if (motionEvent.getAction() == 1 && y < top) {
                    dismiss();
                }
                return true;
            }
        });
    }

    private void mappingIds() {
        this.rcList = this.binding.rcList;
        this.llContent = this.binding.llContent;
    }

    public void setListener(final OnItemClickListener onItemClickListener) {
        RecyclerView recyclerView = this.rcList;
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this.context, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onLongItemClick(View view, int i) {
            }

            @Override
            public void onItemClick(View view, int i) {
                OnItemClickListener onItemClickListener2 = onItemClickListener;
                if (onItemClickListener2 != null) {
                    onItemClickListener2.onItem(i);
                }
            }
        }));
        this.binding.tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    private void initData(Activity activity) {
        this.rcList.setLayoutManager(new LinearLayoutManager(activity, 1, false));
        this.rcList.setAdapter(new InnerAdapter());
    }

    public class InnerAdapter extends RecyclerView.Adapter<ViewHolder> {
        private Item item;

        public InnerAdapter() {
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pop_common, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            this.item = (Item) mLists.get(i);
            if (i == mLists.size() - 1) {
                viewHolder.line.setVisibility(View.GONE);
            } else {
                viewHolder.line.setVisibility(View.VISIBLE);
            }
            viewHolder.icon.setBackgroundResource(this.item.res);
            viewHolder.title.setText(this.item.title);
            viewHolder.selected.setBackgroundResource(this.item.isSelected ? R.mipmap.setting_select : R.mipmap.setting_unselect);
        }

        @Override
        public int getItemCount() {
            if (mLists == null) {
                return 0;
            }
            return mLists.size();
        }

        public class ViewHolder extends BaseHolder {
            ItemPopCommonBinding binding;
            ImageView icon;
            View line;
            ImageView selected;
            TextView title;

            public ViewHolder(View view) {
                super(view);
                ItemPopCommonBinding bind = ItemPopCommonBinding.bind(view);
                this.binding = bind;
                this.icon = bind.icon;
                this.title = this.binding.title;
                this.selected = this.binding.selected;
                this.line = this.binding.line;
            }
        }
    }
}
