package com.tron.wallet.common.components;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.CenterPopupView;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.utils.SpannableUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tronlinkpro.wallet.R;
import j$.util.DesugarArrays;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntConsumer;
public class CenterPopupInstructionView extends CenterPopupView {
    private static boolean isShow = false;
    private static final int[] strRes = {R.string.vote_instruction_0, R.string.vote_instruction_1, R.string.vote_instruction_2, R.string.vote_instruction_3, R.string.vote_instruction_4};

    @Override
    public int getImplLayoutId() {
        return R.layout.popup_instruction_list;
    }

    public static void showUp(Context context) {
        if (isShow) {
            return;
        }
        isShow = true;
        new XPopup.Builder(context).isCenterHorizontal(true).maxWidth(UIUtils.getScreenWidth(context) - (UIUtils.dip2px(context, 15.0f) << 1)).maxHeight((int) (UIUtils.getScreenHeight(context) * 0.6f)).dismissOnTouchOutside(false).asCustom(new CenterPopupInstructionView(context)).show();
    }

    public CenterPopupInstructionView(Context context) {
        super(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getContext()));
        final ArrayList arrayList = new ArrayList();
        DesugarArrays.stream(strRes).forEach(new IntConsumer() {
            @Override
            public final void accept(int i) {
                arrayList.add(Integer.valueOf(i));
            }

            public IntConsumer andThen(IntConsumer intConsumer) {
                return Objects.requireNonNull(intConsumer);
            }
        });
        recyclerView.setAdapter(new SimpleListAdapter(arrayList));
        findViewById(R.id.btn_got).setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$onCreate$0(view);
            }
        });
    }

    public void lambda$onCreate$0(View view) {
        if (isShow()) {
            dismiss();
        }
    }

    @Override
    public void onDismiss() {
        super.onDismiss();
        isShow = false;
    }

    private static final class SimpleItemHolder extends BaseViewHolder {
        public SimpleItemHolder(View view) {
            super(view);
        }
    }

    private static final class SimpleListAdapter extends BaseQuickAdapter<Integer, SimpleItemHolder> {
        public SimpleListAdapter(List<Integer> list) {
            super(R.layout.item_vote_instruction, list);
        }

        @Override
        public void convert(SimpleItemHolder simpleItemHolder, Integer num) {
            try {
                Drawable drawable = AppCompatResources.getDrawable(simpleItemHolder.itemView.getContext(), R.drawable.black_dot);
                int dip2px = UIUtils.dip2px(4.0f);
                ((TextView) simpleItemHolder.itemView).setText(SpannableUtils.getCompatImageSpan(drawable, 0, 2, dip2px, dip2px, StringTronUtil.getResString(num.intValue())));
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
    }
}
