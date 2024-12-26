package com.tron.wallet.business.tokendetail.mvp;

import androidx.core.util.Pair;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.StringTronUtil;
import com.tron.wallet.business.tokendetail.mvp.ProjectIntroductionContract;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.bean.token.Trc20DetailBean;
import com.tron.wallet.common.config.M;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
public class ProjectIntroductionPresenter extends ProjectIntroductionContract.Presenter {
    @Override
    protected void onStart() {
    }

    @Override
    public void requestTokenInfo(final String str, TokenBean tokenBean, long j) {
        String str2;
        int i;
        if (M.M_TRC20.equals(str)) {
            if (tokenBean != null) {
                str2 = tokenBean.getContractAddress();
                i = tokenBean.getType();
            } else {
                str2 = j + "";
                i = 2;
            }
        } else if (tokenBean != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(StringTronUtil.isEmpty(tokenBean.getId()) ? "0" : tokenBean.getId());
            sb.append("");
            str2 = sb.toString();
            i = !StringTronUtil.isEmpty(tokenBean.getId()) ? 1 : 0;
        } else {
            str2 = j + "";
            i = 1;
        }
        Observable.zip(((ProjectIntroductionContract.Model) this.mModel).getTokenSecurityInfo(str2, i), ((ProjectIntroductionContract.Model) this.mModel).getTokenDetail(str, str2), new BiFunction() {
            @Override
            public final Object apply(Object obj, Object obj2) {
                return ProjectIntroductionPresenter.lambda$requestTokenInfo$0((TokenSecureInfoOutput) obj, (Trc20DetailBean) obj2);
            }
        }).compose(RxSchedulers2.io_main()).subscribe(new IObserver(new ICallback<Pair<TokenSecureInfoOutput, Trc20DetailBean>>() {
            @Override
            public void onResponse(String str3, Pair<TokenSecureInfoOutput, Trc20DetailBean> pair) {
                if (pair.first == null || pair.second == null) {
                    return;
                }
                Trc20DetailBean trc20DetailBean = pair.second;
                ((ProjectIntroductionContract.View) mView).hideLoadingPageDialog();
                ((ProjectIntroductionContract.View) mView).updateSecureInfo(pair.first.getData());
                if (M.M_TRC20.equals(str)) {
                    if (trc20DetailBean.getTrc20_tokens() != null && trc20DetailBean.getTrc20_tokens().size() > 0) {
                        ((ProjectIntroductionContract.View) mView).updateByTRC20TokenBean(trc20DetailBean.getTrc20_tokens().get(0));
                    } else {
                        ((ProjectIntroductionContract.View) mView).updateByTRC20TokenBean(null);
                    }
                } else if (trc20DetailBean.getData() != null && trc20DetailBean.getData().size() > 0) {
                    ((ProjectIntroductionContract.View) mView).updateTrc10(trc20DetailBean.getData().get(0));
                } else {
                    ((ProjectIntroductionContract.View) mView).updateTrc10(null);
                }
            }

            @Override
            public void onFailure(String str3, String str4) {
                LogUtils.e("ProjectIntroductionPresenter", str3 + "    " + str4);
                ((ProjectIntroductionContract.View) mView).updateSecureInfo(null);
                if (M.M_TRC20.equals(str)) {
                    ((ProjectIntroductionContract.View) mView).updateByTRC20TokenBean(null);
                } else {
                    ((ProjectIntroductionContract.View) mView).updateTrc10(null);
                }
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "requestTokenInfo"));
    }

    public static Pair lambda$requestTokenInfo$0(TokenSecureInfoOutput tokenSecureInfoOutput, Trc20DetailBean trc20DetailBean) throws Exception {
        return new Pair(tokenSecureInfoOutput, trc20DetailBean);
    }
}
