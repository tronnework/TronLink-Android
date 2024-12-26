package com.tron.wallet.business.tokendetail.mvp;

import com.google.gson.Gson;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.wallet.business.addassets2.bean.BaseOutput;
import com.tron.wallet.business.tokendetail.mvp.TokenReportContract;
import com.tronlinkpro.wallet.R;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
public class TokenReportPresenter extends TokenReportContract.Presenter {
    @Override
    protected void onStart() {
    }

    @Override
    public void report(String str, int i, String str2, String str3, String str4, String str5) {
        ((TokenReportContract.View) this.mView).showLoadingDialog();
        TokenReportBean tokenReportBean = new TokenReportBean();
        tokenReportBean.setTokenAddr(str);
        tokenReportBean.setTokenType(i + "");
        tokenReportBean.setTokenSymbol(str2);
        tokenReportBean.setReportList(str3);
        tokenReportBean.setContactInfo(str4);
        tokenReportBean.setDesc(str5);
        ((TokenReportContract.Model) this.mModel).requestTokenReport(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(tokenReportBean))).subscribe(new IObserver(new ICallback<BaseOutput>() {
            @Override
            public void onSubscribe(Disposable disposable) {
            }

            @Override
            public void onResponse(String str6, BaseOutput baseOutput) {
                if (baseOutput.getCode() == 0) {
                    ((TokenReportContract.View) mView).reportSuccess();
                } else {
                    ((TokenReportContract.View) mView).toast(((TokenReportContract.View) mView).getIContext().getString(R.string.token_report_submit_failed));
                }
            }

            @Override
            public void onFailure(String str6, String str7) {
                ((TokenReportContract.View) mView).toast(((TokenReportContract.View) mView).getIContext().getString(R.string.token_report_submit_failed));
            }
        }, "requestTokenReport"));
    }
}
