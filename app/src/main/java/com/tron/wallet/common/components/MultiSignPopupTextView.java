package com.tron.wallet.common.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatTextView;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.util.XPopupUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.components.MultiLineTextPopupView;
import com.tron.wallet.common.utils.AccountPermissionUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import java.lang.ref.WeakReference;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class MultiSignPopupTextView extends AppCompatTextView {
    private static WeakReference<BasePopupView> currentPopupRef;
    private Wallet sw;

    public void setWallet(Wallet wallet) {
        this.sw = wallet;
    }

    public MultiSignPopupTextView(Context context) {
        this(context, null);
    }

    public MultiSignPopupTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MultiSignPopupTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (sw == null) {
                    sw = WalletUtils.getSelectedPublicWallet();
                }
                MultiSignPopupTextView.currentPopupRef = new WeakReference(new MultiLineTextPopupView.Builder().setAnchor(view).setPreferredShowUp(true).setRequiredWidth((int) (XPopupUtils.getScreenWidth(getContext()) * 0.58f)).addKeyValue(getContext().getString((sw == null || !sw.isWatchNotPaired()) ? R.string.multi_sign_flag_tips : R.string.multi_sign_flag_tips_observe), "").build(getContext()));
                ((BasePopupView) MultiSignPopupTextView.currentPopupRef.get()).show();
            }
        });
    }

    public static void hideCurrentPopup() {
        WeakReference<BasePopupView> weakReference = currentPopupRef;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        currentPopupRef.get().dismiss();
    }

    public void attachAccount(Protocol.Account account, final Consumer<String> consumer) {
        try {
            AccountPermissionUtils.isAccountPermissionMultiSign(getContext(), account, new BiConsumer() {
                @Override
                public final void accept(Object obj, Object obj2) {
                    lambda$attachAccount$0(consumer, (Boolean) obj, (String) obj2);
                }
            });
        } catch (Exception e) {
            LogUtils.e(e);
            setVisibility(View.GONE);
        }
    }

    public void lambda$attachAccount$0(Consumer consumer, Boolean bool, String str) throws Exception {
        setVisibility(bool.booleanValue() ? View.VISIBLE : View.GONE);
        if (consumer == null || !bool.booleanValue()) {
            return;
        }
        consumer.accept(str);
    }
}
