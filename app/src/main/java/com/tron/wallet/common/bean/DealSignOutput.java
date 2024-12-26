package com.tron.wallet.common.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.tron.wallet.common.bean.AllTransferOutput;
import java.io.Serializable;
import java.util.List;
public class DealSignOutput {
    public int code;
    public DataBeanX data;
    public String message;

    public static class DataBeanX {
        public List<DataBean> data;
        public int total;

        public static class DataBean implements Serializable {
            public String callValue;
            public ContractDataBean contractData;
            public String contractType;
            public CurrentTransactionBean currentTransaction;
            public String currentTransaction2;
            public int currentWeight;
            public long expireTime;
            public String functionSelector;
            public String hash;
            public boolean isOpen;
            public int isSign;
            public String originatorAddress;
            public List<SignatureProgressBean> signatureProgress;
            public int threshold;
            public Trc20InfoBean trc20Info;

            public static class ContractDataBean {
                public String account_address;
                public String account_name;
                public long amount;
                public String asset_name;
                public double balance;
                public String contract_address;
                public long count;
                public String data;
                public long frozen_balance;
                public long frozen_duration;
                public String name;
                public String owner_address;
                public String proposal_id;
                public String receiver_address;
                public String resource;
                public String to_address;
                public String token_id;
                public int total_supply;
                public int type;
                public double unfreeze_balance;
                public String update_url;
                public String url;
                public long vote_count;
                public List<AllTransferOutput.DataBean.ContractDataBean.VotesBean> votes;

                public static class VotesBean {
                    public String vote_address;
                    public int vote_count;
                }
            }

            public static class CurrentTransactionBean {
                public RawDataBean raw_data;
                public List<String> signature;

                public static class RawDataBean {
                    public List<ContractBean> contract;
                    public String data;
                    public long expiration;
                    public String ref_block_bytes;
                    public String ref_block_hash;
                    public long timestamp;

                    public static class ContractBean {
                        public int Permission_id;
                        public ParameterBean parameter;
                        public String type;

                        public static class ParameterBean {
                            public String type_url;
                            public String value;
                        }
                    }
                }
            }

            public static class Trc20InfoBean {
                public int decimals;
                public String name;
                public String symbol;
            }

            public static class SignatureProgressBean implements Parcelable {
                public static final Parcelable.Creator<SignatureProgressBean> CREATOR = new Parcelable.Creator<SignatureProgressBean>() {
                    @Override
                    public SignatureProgressBean createFromParcel(Parcel parcel) {
                        return new SignatureProgressBean(parcel);
                    }

                    @Override
                    public SignatureProgressBean[] newArray(int i) {
                        return new SignatureProgressBean[i];
                    }
                };
                public String address;
                public int isSign;
                public int weight;

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel parcel, int i) {
                    parcel.writeString(this.address);
                    parcel.writeInt(this.weight);
                    parcel.writeInt(this.isSign);
                }

                public SignatureProgressBean() {
                }

                protected SignatureProgressBean(Parcel parcel) {
                    this.address = parcel.readString();
                    this.weight = parcel.readInt();
                    this.isSign = parcel.readInt();
                }
            }
        }
    }
}
