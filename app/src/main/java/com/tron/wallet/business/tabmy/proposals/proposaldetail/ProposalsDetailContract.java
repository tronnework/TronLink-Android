package com.tron.wallet.business.tabmy.proposals.proposaldetail;

import android.content.Intent;
import com.google.protobuf.ByteString;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.common.components.ZFlowLayout;
import io.reactivex.Observable;
import java.util.HashMap;
import java.util.List;
import org.tron.protos.Protocol;
public interface ProposalsDetailContract {

    public interface Model extends BaseModel {
        Observable<WitnessOutput> getWitnessList();
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void addSome();

        public abstract void createProposalTranscation(long j, boolean z, HashMap<Long, String> hashMap);

        public abstract void deleteProposalTranscation(long j, HashMap<Long, String> hashMap);

        public abstract String getCurrentAddress();

        public abstract void getData();

        public abstract String getFromType();

        public abstract void initAccount();

        public abstract void initProposalList(ZFlowLayout zFlowLayout, List<ByteString> list, boolean z, String str);

        public abstract boolean isHasAgree();

        public abstract void showDialog(String str, String str2, HashMap<Long, String> hashMap);
    }

    public interface View extends BaseView {
        Intent getIIntent();

        HashMap<String, WitnessOutput.DataBean> getWitnessMap();

        void initView(Protocol.Account account, Protocol.Proposal proposal, List<WitnessOutput.DataBean> list);
    }
}
