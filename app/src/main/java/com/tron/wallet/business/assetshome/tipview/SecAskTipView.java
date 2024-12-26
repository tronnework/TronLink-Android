package com.tron.wallet.business.assetshome.tipview;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.business.tabdapp.home.utils.DAppUrlUtils;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.business.web.WebConstant;
import com.tron.wallet.business.web.WebOptions;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.databinding.LlSigndealTipBinding;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.functions.Consumer;
import java.util.UUID;
import org.tron.walletserver.Wallet;
public class SecAskTipView implements ITipView {
    private LlSigndealTipBinding binding;
    View btnGo;
    private Context context;
    View ivClose;
    private View.OnClickListener onClose;
    private View.OnClickListener onGo;
    private RxManager rxManager;
    private int state = 0;
    private View tipView;
    TextView tvDesc;
    TextView tvGo;
    TextView tvTitle;

    @Override
    public int getState() {
        return this.state;
    }

    @Override
    public void onWalletChanged(Wallet wallet) {
    }

    @Override
    public void setState(int i, String... strArr) {
        this.state = i;
    }

    public SecAskTipView(Context context, View view, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        mappingId(view);
        this.context = context;
        this.tipView = view;
        this.onClose = onClickListener;
        this.onGo = onClickListener2;
        RxManager rxManager = new RxManager();
        this.rxManager = rxManager;
        rxManager.on(Event.EVENT_SEC_ASK_DONE, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$new$0(obj);
            }
        });
    }

    public void lambda$new$0(Object obj) throws Exception {
        if ((obj instanceof String) && "true".equals((String) obj)) {
            hide();
            SpAPI.THIS.setSecAskDone();
        }
    }

    public void mappingId(View view) {
        LlSigndealTipBinding bind = LlSigndealTipBinding.bind(view);
        this.binding = bind;
        this.tvTitle = bind.tvSignTitle;
        this.ivClose = this.binding.ivSignClose;
        this.tvDesc = this.binding.tvSignDesc;
        this.btnGo = this.binding.llSignArrow;
        this.tvGo = this.binding.tvGoNow;
    }

    public static boolean isVisibility() {
        if (SpAPI.THIS.getSecAskDone()) {
            return false;
        }
        return System.currentTimeMillis() - SpAPI.THIS.getSecAskCloseTime() > 259200000;
    }

    private void initUI() {
        this.tipView.setVisibility(View.VISIBLE);
        this.tvTitle.setText(R.string.sec_ask_title);
        this.tvDesc.setText(R.string.sec_ask_info);
        this.tvGo.setText(R.string.sec_go_to_view);
        this.ivClose.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                SpAPI.THIS.setSecAskCloseTime(System.currentTimeMillis());
                hide();
                IToast.getIToast().show(R.string.sec_ask_close_toast);
            }
        });
        this.btnGo.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.AssetPage.SEC_ASK_PAGE_SHOW);
                CommonWebActivityV3.start(context, getUrl(), "", true, new WebOptions.WebOptionsBuild().addCode(-2).addFrom(WebConstant.FROM_SEC_ASK).build());
            }
        });
    }

    public String getUrl() {
        return DAppUrlUtils.addQueryParameter(IRequest.getSecAskUrl(), "uuid", UUID.randomUUID().toString());
    }

    @Override
    public String getId() {
        return TipViewType.SEC_ASK.getId();
    }

    @Override
    public int getPriority() {
        return TipViewType.SEC_ASK.getPriority();
    }

    @Override
    public void show() {
        if (this.state == 3) {
            this.state = 4;
            initUI();
        }
    }

    @Override
    public void hide() {
        if (this.state != 4) {
            this.state = 2;
            return;
        }
        this.state = 5;
        this.tipView.setVisibility(View.GONE);
    }
}
