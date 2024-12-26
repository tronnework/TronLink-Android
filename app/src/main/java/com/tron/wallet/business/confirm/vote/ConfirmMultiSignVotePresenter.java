package com.tron.wallet.business.confirm.vote;

import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.StringTronUtil;
import com.tron.wallet.business.confirm.vote.ConfirmMultiSignVoteContract;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.common.config.TronConfig;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class ConfirmMultiSignVotePresenter extends ConfirmMultiSignVoteContract.Presenter {
    private List<WitnessOutput.DataBean> mWitnessesList;
    private HashMap<String, String> nameMap;

    @Override
    protected void onStart() {
    }

    @Override
    public void addSome() {
        this.mWitnessesList = new ArrayList();
        this.nameMap = new HashMap<>();
    }

    @Override
    public void getData() {
        ((ConfirmMultiSignVoteContract.Model) this.mModel).getWitnessList().subscribe(new IObserver(new ICallback<WitnessOutput>() {
            @Override
            public void onResponse(String str, WitnessOutput witnessOutput) {
                if (witnessOutput != null) {
                    ((ConfirmMultiSignVoteContract.View) mView).showLoading(false);
                    mWitnessesList.clear();
                    mWitnessesList.addAll(witnessOutput.getData());
                    for (int i = 0; i < mWitnessesList.size(); i++) {
                        String address = ((WitnessOutput.DataBean) mWitnessesList.get(i)).getAddress();
                        if (!StringTronUtil.isEmpty(address)) {
                            nameMap.put(address, ((WitnessOutput.DataBean) mWitnessesList.get(i)).getUrl());
                        }
                    }
                    gotoFragment();
                    return;
                }
                ((ConfirmMultiSignVoteContract.View) mView).showOrHideNoData(true);
            }

            @Override
            public void onFailure(String str, String str2) {
                ((ConfirmMultiSignVoteContract.View) mView).showLoading(false);
                if (TronConfig.hasNet) {
                    ((ConfirmMultiSignVoteContract.View) mView).showOrHideNoData(true);
                } else {
                    ((ConfirmMultiSignVoteContract.View) mView).showOrHideNoNet(true);
                }
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "111"));
    }

    public void gotoFragment() {
        ((ConfirmMultiSignVoteContract.View) this.mView).gotoFragment(this.mWitnessesList, this.nameMap);
    }
}
