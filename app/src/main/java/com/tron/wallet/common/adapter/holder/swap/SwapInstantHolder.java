package com.tron.wallet.common.adapter.holder.swap;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.common.config.Event;
import com.tronlinkpro.wallet.R;
import io.reactivex.functions.Consumer;
public class SwapInstantHolder extends BaseSwapAdapterHolder {
    Button btnSwap;

    public SwapInstantHolder(Context context, int i) {
        super(context, i);
        this.btnSwap = (Button) this.itemView.findViewById(R.id.btn_swap_instant);
    }

    @Override
    public void setRxManager(RxManager rxManager) {
        super.setRxManager(rxManager);
        if (rxManager != null) {
            rxManager.on(Event.SWAP_SUBMIT_DIALOG, new Consumer() {
                @Override
                public final void accept(Object obj) {
                    lambda$setRxManager$0(obj);
                }
            });
        }
    }

    public void lambda$setRxManager$0(Object obj) throws Exception {
        Button button = this.btnSwap;
        if (button != null) {
            button.setEnabled(((Boolean) obj).booleanValue());
        }
    }

    @Override
    public void onBindButton(boolean z, View.OnClickListener onClickListener) {
        updateEnabledState(z);
        Button button = this.btnSwap;
        if (button == null || onClickListener == null) {
            return;
        }
        button.setOnClickListener(onClickListener);
    }

    public void updateEnabledState(boolean z) {
        Button button = this.btnSwap;
        if (button != null) {
            button.setEnabled(z);
        }
    }
}
