package com.tron.wallet.common.components;

import android.content.Context;
import android.widget.TextView;
import com.lxj.xpopup.core.AttachPopupView;
import com.tron.wallet.common.utils.UIUtils;
import com.tronlinkpro.wallet.R;
public class SortIDPopupView extends AttachPopupView {
    TextView tv;

    @Override
    public int getImplLayoutId() {
        return R.layout.vote_number_pop;
    }

    public SortIDPopupView(Context context) {
        super(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.tv = (TextView) findViewById(R.id.tv_sort_pop);
    }

    @Override
    public void doAttach() {
        super.doAttach();
        if (isShowUpToTarget()) {
            this.tv.setBackgroundResource(R.mipmap.vote_number_revert);
            this.tv.setPadding(0, UIUtils.dip2px(15.0f), 0, UIUtils.dip2px(21.0f));
            return;
        }
        this.tv.setBackgroundResource(R.mipmap.bg_vote_id_pop);
        this.tv.setPadding(0, UIUtils.dip2px(21.0f), 0, UIUtils.dip2px(15.0f));
    }
}
