package com.tron.wallet.common.components.popupwindow;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.common.adapter.RecyclerItemClickListener;
import com.tron.wallet.common.interfaces.OnSeleted2Listener;
import com.tron.wallet.databinding.ItemPopAccountBinding;
import com.tron.wallet.databinding.PopAccountBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.List;
import org.tron.walletserver.Wallet;
public class SelectedAccountWindow extends PopupWindow {
    PopAccountBinding binding;
    private List<Wallet> mLists;
    private View mMenuView;
    private final MyAdapter myAdapter;
    RecyclerView rcList;
    private String walletName;

    public SelectedAccountWindow(Activity activity, final OnSeleted2Listener onSeleted2Listener) {
        super(activity);
        View inflate = ((LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.pop_account, (ViewGroup) null);
        this.mMenuView = inflate;
        this.binding = PopAccountBinding.bind(inflate);
        mappingIds();
        this.mLists = new ArrayList();
        for (String str : WalletUtils.getWalletNames()) {
            Wallet wallet = WalletUtils.getWallet(str);
            if (wallet != null && !wallet.isShieldedWallet()) {
                this.mLists.add(wallet);
            }
        }
        setContentView(this.mMenuView);
        setWidth(-1);
        setHeight(-1);
        setFocusable(true);
        setAnimationStyle(R.style.AnimBottom);
        new ColorDrawable(-1342177280);
        setBackgroundDrawable(null);
        this.mMenuView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int top = mMenuView.findViewById(R.id.pop_layout).getTop();
                int bottom = mMenuView.findViewById(R.id.pop_layout).getBottom();
                int y = (int) motionEvent.getY();
                if (motionEvent.getAction() == 1 && (y < top || y > bottom)) {
                    dismiss();
                }
                return true;
            }
        });
        this.rcList.setLayoutManager(new LinearLayoutManager(activity, 1, false));
        MyAdapter myAdapter = new MyAdapter();
        this.myAdapter = myAdapter;
        this.rcList.setAdapter(myAdapter);
        RecyclerView recyclerView = this.rcList;
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(activity, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onLongItemClick(View view, int i) {
            }

            @Override
            public void onItemClick(View view, int i) {
                onSeleted2Listener.onSeleted((Wallet) mLists.get(i));
            }
        }));
    }

    private void mappingIds() {
        this.rcList = this.binding.rcList;
    }

    public void show(View view, String str) {
        this.walletName = str;
        MyAdapter myAdapter = this.myAdapter;
        if (myAdapter != null) {
            myAdapter.notifyDataSetChanged();
        }
        showAtLocation(view, 81, 0, 0);
    }

    class MyAdapter extends RecyclerView.Adapter<ViewHolder> {
        MyAdapter() {
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pop_account, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            Wallet wallet = (Wallet) mLists.get(i);
            if (wallet.getWalletName().equals(walletName)) {
                viewHolder.selected.setBackgroundResource(R.mipmap.setting_select);
            } else {
                viewHolder.selected.setBackgroundResource(R.mipmap.setting_unselect);
            }
            viewHolder.tvName.setText(wallet.getWalletName());
        }

        @Override
        public int getItemCount() {
            if (mLists == null) {
                return 0;
            }
            return mLists.size();
        }

        public class ViewHolder extends BaseHolder {
            ItemPopAccountBinding binding;
            ImageView selected;
            TextView tvName;

            public ViewHolder(View view) {
                super(view);
                ItemPopAccountBinding bind = ItemPopAccountBinding.bind(view);
                this.binding = bind;
                this.tvName = bind.tvName;
                this.selected = this.binding.selected;
            }
        }
    }
}
