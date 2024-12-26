package com.tron.wallet.business.tabswap.select;

import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.tabswap.bean.SwapWhiteListOutput;
import com.tron.wallet.business.tabswap.select.SelectTokenContract;
import com.tron.wallet.business.tabswap.utils.SwapCacheUtils;
import io.reactivex.disposables.Disposable;
import j$.util.Collection;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
public class SelectTokenPresenter extends SelectTokenContract.Presenter {
    private static final String TAG = "SelectTokenPresenter";

    @Override
    protected void onStart() {
    }

    @Override
    public void getTokens() {
        try {
            if (SwapCacheUtils.getInstance().isExpire(((SelectTokenContract.View) this.mView).getIContext())) {
                ((SelectTokenContract.Model) this.mModel).getWhiteListTokens().compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<SwapWhiteListOutput>() {
                    @Override
                    public void onResponse(String str, SwapWhiteListOutput swapWhiteListOutput) {
                        if (swapWhiteListOutput.getCode() == 0) {
                            ((SelectTokenContract.View) mView).getHolderView().setVisibility(View.GONE);
                            if (swapWhiteListOutput.getTokens() != null && swapWhiteListOutput.getTokens().size() > 0) {
                                ((SelectTokenContract.View) mView).showNoNetError(false);
                                ((SelectTokenContract.View) mView).showEmptyView(false);
                                SwapWhiteListOutput swapListWithTrx = SwapCacheUtils.getInstance().getSwapListWithTrx(((SelectTokenContract.View) mView).getIContext(), swapWhiteListOutput);
                                List<SwapWhiteListOutput.Data> tokens = swapListWithTrx.getTokens();
                                final ArrayList arrayList = new ArrayList(tokens.size());
                                Collection.-EL.stream(tokens).forEach(new Consumer() {
                                    @Override
                                    public final void accept(Object obj) {
                                        arrayList.add((SwapWhiteListOutput.Data) obj);
                                    }

                                    public Consumer andThen(Consumer consumer) {
                                        return Objects.requireNonNull(consumer);
                                    }
                                });
                                ((SelectTokenContract.View) mView).updateUI(arrayList);
                                SwapCacheUtils.getInstance().save(((SelectTokenContract.View) mView).getIContext(), swapListWithTrx);
                                return;
                            }
                            ((SelectTokenContract.View) mView).showEmptyView(true);
                            ((SelectTokenContract.View) mView).showNoNetError(false);
                            return;
                        }
                        ((SelectTokenContract.View) mView).getTitleView().setVisibility(View.GONE);
                        ((SelectTokenContract.View) mView).showEmptyView(false);
                    }

                    @Override
                    public void onFailure(String str, String str2) {
                        LogUtils.e(SelectTokenPresenter.TAG, "onResponse: " + str2);
                        ((SelectTokenContract.View) mView).getHolderView().setVisibility(View.GONE);
                        ((SelectTokenContract.View) mView).showNoNetError(true);
                        ((SelectTokenContract.View) mView).getNonetView().updateLoadingState(true);
                        ((SelectTokenContract.View) mView).showEmptyView(false);
                    }

                    @Override
                    public void onSubscribe(Disposable disposable) {
                        mRxManager.add(disposable);
                    }
                }, "tokens"));
            } else {
                ((SelectTokenContract.View) this.mView).getHolderView().setVisibility(View.GONE);
                ((SelectTokenContract.View) this.mView).updateUI(SwapCacheUtils.getInstance().read(((SelectTokenContract.View) this.mView).getIContext()).getTokens());
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }
}
