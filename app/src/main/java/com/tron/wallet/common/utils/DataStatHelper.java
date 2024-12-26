package com.tron.wallet.common.utils;

import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.FreezeUnFreezeParam;
import com.tron.wallet.business.confirm.parambuilder.bean.VoteParam;
import com.tron.wallet.common.bean.StatInput;
import com.tron.wallet.common.config.FeeReporting;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.disposables.Disposable;
import java.io.Serializable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
import org.tron.protos.contract.BalanceContract;
import org.tron.protos.contract.WitnessContract;
public class DataStatHelper {

    public enum From {
        None,
        Stake,
        Vote
    }

    public static class StatBean {
        private String address;
        private String amount;
        private int contractType;
        private long createTime;
        private String tokenId;

        public String getAddress() {
            return this.address;
        }

        public String getAmount() {
            return this.amount;
        }

        public int getContractType() {
            return this.contractType;
        }

        public long getCreateTime() {
            return this.createTime;
        }

        public String getTokenId() {
            return this.tokenId;
        }

        public void setAddress(String str) {
            this.address = str;
        }

        public void setAmount(String str) {
            this.amount = str;
        }

        public void setContractType(int i) {
            this.contractType = i;
        }

        public void setCreateTime(long j) {
            this.createTime = j;
        }

        public void setTokenId(String str) {
            this.tokenId = str;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof StatBean;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof StatBean) {
                StatBean statBean = (StatBean) obj;
                if (statBean.canEqual(this) && getCreateTime() == statBean.getCreateTime() && getContractType() == statBean.getContractType()) {
                    String address = getAddress();
                    String address2 = statBean.getAddress();
                    if (address != null ? address.equals(address2) : address2 == null) {
                        String amount = getAmount();
                        String amount2 = statBean.getAmount();
                        if (amount != null ? amount.equals(amount2) : amount2 == null) {
                            String tokenId = getTokenId();
                            String tokenId2 = statBean.getTokenId();
                            return tokenId != null ? tokenId.equals(tokenId2) : tokenId2 == null;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            long createTime = getCreateTime();
            int contractType = ((((int) (createTime ^ (createTime >>> 32))) + 59) * 59) + getContractType();
            String address = getAddress();
            int hashCode = (contractType * 59) + (address == null ? 43 : address.hashCode());
            String amount = getAmount();
            int hashCode2 = (hashCode * 59) + (amount == null ? 43 : amount.hashCode());
            String tokenId = getTokenId();
            return (hashCode2 * 59) + (tokenId != null ? tokenId.hashCode() : 43);
        }

        public String toString() {
            return "DataStatHelper.StatBean(address=" + getAddress() + ", createTime=" + getCreateTime() + ", amount=" + getAmount() + ", tokenId=" + getTokenId() + ", contractType=" + getContractType() + ")";
        }
    }

    public enum StatAction implements Serializable {
        None(0),
        Stake(3),
        FinanceStake(4),
        Vote(5),
        FinanceVote(6),
        APP(11),
        DApp(12);
        
        private int actionType;

        public int getActionType() {
            return this.actionType;
        }

        StatAction(int i) {
            this.actionType = i;
        }
    }

    public static synchronized void uploadStake(FreezeUnFreezeParam freezeUnFreezeParam) {
        synchronized (DataStatHelper.class) {
            if (freezeUnFreezeParam == null) {
                return;
            }
            try {
                StatBean parseTransaction = parseTransaction(freezeUnFreezeParam, From.Stake);
                if (parseTransaction != null) {
                    parseTransaction.setTokenId("_");
                    StatAction statAction = freezeUnFreezeParam.getStatAction();
                    if (statAction == null) {
                        statAction = StatAction.Stake;
                    }
                    upload(parseTransaction, statAction);
                }
            } catch (Exception e) {
                LogUtils.e(e);
                SentryUtil.captureException(e);
            }
        }
    }

    public static synchronized void uploadVote(VoteParam voteParam) {
        synchronized (DataStatHelper.class) {
            if (voteParam == null) {
                return;
            }
            try {
                StatBean parseTransaction = parseTransaction(voteParam, From.Vote);
                if (parseTransaction != null) {
                    parseTransaction.setTokenId("_");
                    StatAction statAction = voteParam.getStatAction();
                    if (statAction == null) {
                        statAction = StatAction.Vote;
                    }
                    upload(parseTransaction, statAction);
                }
            } catch (Exception e) {
                LogUtils.e(e);
                SentryUtil.captureException(e);
            }
        }
    }

    public static synchronized void transactionReportingApp(Protocol.Transaction transaction, boolean z) {
        synchronized (DataStatHelper.class) {
            try {
                String hash = TransactionUtils.getHash(transaction);
                Protocol.Transaction.Contract.ContractType type = transaction.getRawData().getContract(0).getType();
                StatBean statBean = new StatBean();
                statBean.setContractType(type.getNumber());
                statBean.setAmount(String.valueOf(BigDecimalUtils.mul_(Double.valueOf(FeeReporting.getFee(hash)), Double.valueOf(Math.pow(10.0d, 6.0d))).longValue()));
                statBean.setAddress(TransactionUtils.getOwner(transaction));
                upload(statBean, z ? StatAction.DApp : StatAction.APP);
                FeeReporting.removeItem(hash);
                LogUtils.i("DataStatHelper", "TransactionReportingApp:" + hash);
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
    }

    private static StatBean parseTransaction(BaseParam baseParam, From from) {
        String str;
        String str2;
        if (baseParam != null && from != From.None && baseParam.getTransactionBean() != null && baseParam.getTransactionBean().getBytes() != null && !baseParam.getTransactionBean().getBytes().isEmpty()) {
            try {
                Protocol.Transaction parseFrom = Protocol.Transaction.parseFrom(baseParam.getTransactionBean().getBytes().get(0));
                if (from == From.Vote) {
                    WitnessContract.VoteWitnessContract voteWitnessContract = (WitnessContract.VoteWitnessContract) TransactionUtils.unpackContract(parseFrom.getRawData().getContract(0), WitnessContract.VoteWitnessContract.class);
                    long j = 0;
                    for (int i = 0; i < voteWitnessContract.getVotesList().size(); i++) {
                        j += voteWitnessContract.getVotesList().get(i).getVoteCount();
                    }
                    str2 = BigDecimalUtils.toString(Long.valueOf(j));
                    str = StringTronUtil.encode58Check(voteWitnessContract.getOwnerAddress().toByteArray());
                } else if (from == From.Stake) {
                    BalanceContract.FreezeBalanceContract freezeBalanceContract = (BalanceContract.FreezeBalanceContract) TransactionUtils.unpackContract(parseFrom.getRawData().getContract(0), BalanceContract.FreezeBalanceContract.class);
                    String encode58Check = StringTronUtil.encode58Check(freezeBalanceContract.getOwnerAddress().toByteArray());
                    str2 = BigDecimalUtils.toString(Long.valueOf(freezeBalanceContract.getFrozenBalance()));
                    str = encode58Check;
                } else {
                    str = null;
                    str2 = null;
                }
                long timestamp = parseFrom.getRawData().getTimestamp();
                StatBean statBean = new StatBean();
                statBean.setAmount(str2);
                statBean.setCreateTime(timestamp);
                statBean.setAddress(str);
                return statBean;
            } catch (Exception e) {
                LogUtils.e(e);
                SentryUtil.captureException(e);
            }
        }
        return null;
    }

    private static synchronized void upload(StatBean statBean, StatAction statAction) {
        long createTime;
        String address;
        String tokenId;
        int contractType;
        synchronized (DataStatHelper.class) {
            String str = "";
            if (statBean != null) {
                str = statBean.getAmount();
                createTime = statBean.getCreateTime();
                address = statBean.getAddress();
                tokenId = statBean.getTokenId();
                contractType = statBean.getContractType();
            } else {
                contractType = -1;
                address = "";
                tokenId = "";
                createTime = 0;
            }
            if (statAction == StatAction.APP || statAction == StatAction.DApp || !(createTime == 0 || StringTronUtil.isEmpty(str) || StringTronUtil.isEmpty(address))) {
                StatInput statInput = new StatInput();
                int actionType = statAction.getActionType();
                statInput.setAddress(address);
                statInput.setActionTime(createTime);
                statInput.setTokenId(tokenId);
                statInput.setActionType(actionType);
                statInput.setAmount(str);
                statInput.setContractType(contractType);
                ((HttpAPI) IRequest.getAPI(HttpAPI.class)).dataStat(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), GsonUtils.toGsonString(statInput))).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<String>() {
                    @Override
                    public void onFailure(String str2, String str3) {
                    }

                    @Override
                    public void onResponse(String str2, String str3) {
                    }

                    @Override
                    public void onSubscribe(Disposable disposable) {
                    }
                }, "dataStat"));
                LogUtils.i("DataStatHelper", "dataStat-success:");
            }
        }
    }
}
