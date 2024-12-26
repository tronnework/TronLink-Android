package com.tron.wallet.business.tronpower.stake;

import com.tron.wallet.common.bean.NameAddressExtraBean;
import j$.util.Objects;
import java.util.function.Consumer;
public final class SelectStakeReceiveAccountActvitiyExternalSyntheticLambda3 implements Consumer {
    public final SelectStakeReceiveAccountActvitiy f$0;

    @Override
    public final void accept(Object obj) {
        this.f$0.setSelectBeanToUI((NameAddressExtraBean) obj);
    }

    public Consumer andThen(Consumer consumer) {
        return Objects.requireNonNull(consumer);
    }
}
