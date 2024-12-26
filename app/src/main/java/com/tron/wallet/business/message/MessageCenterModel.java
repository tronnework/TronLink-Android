package com.tron.wallet.business.message;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.wallet.business.message.MessageCenterContract;
import com.tron.wallet.business.message.db.TransactionMessageManager;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import java.util.List;
public class MessageCenterModel implements MessageCenterContract.Model {
    private final HttpAPI httpAPI = (HttpAPI) IRequest.getAPI(HttpAPI.class);

    @Override
    public Observable<List<TransactionMessage>> getMessageData(final int i, final int i2) {
        return Observable.unsafeCreate(new ObservableSource() {
            @Override
            public final void subscribe(Observer observer) {
                observer.onNext(TransactionMessageManager.getInstance().query(i, i2));
            }
        });
    }
}
