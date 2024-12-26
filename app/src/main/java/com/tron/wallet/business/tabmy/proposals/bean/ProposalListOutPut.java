package com.tron.wallet.business.tabmy.proposals.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
public class ProposalListOutPut {
    public List<DataBean> data;
    public int total;

    public static class DataBean implements Parcelable {
        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel parcel) {
                return new DataBean(parcel);
            }

            @Override
            public DataBean[] newArray(int i) {
                return new DataBean[i];
            }
        };
        public List<ApprovalsBean> approvals;
        public long createTime;
        public long expirationTime;
        public List<ParamtersBean> paramters;
        public long proposalId;
        public ProposerBean proposer;
        public String state;

        @Override
        public int describeContents() {
            return 0;
        }

        public static class ProposerBean implements Parcelable {
            public static final Parcelable.Creator<ProposerBean> CREATOR = new Parcelable.Creator<ProposerBean>() {
                @Override
                public ProposerBean createFromParcel(Parcel parcel) {
                    return new ProposerBean(parcel);
                }

                @Override
                public ProposerBean[] newArray(int i) {
                    return new ProposerBean[i];
                }
            };
            public String address;
            public double latestBlockNumber;
            public double latestSlotNumber;
            public double missedTotal;
            public String name;
            public double producePercentage;
            public double producedTotal;
            public long producedTrx;
            public boolean producer;
            public String url;
            public long votes;
            public double votesPercentage;

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeString(this.address);
                parcel.writeString(this.name);
                parcel.writeString(this.url);
                parcel.writeByte(this.producer ? (byte) 1 : (byte) 0);
                parcel.writeDouble(this.latestBlockNumber);
                parcel.writeDouble(this.latestSlotNumber);
                parcel.writeDouble(this.missedTotal);
                parcel.writeDouble(this.producedTotal);
                parcel.writeLong(this.producedTrx);
                parcel.writeLong(this.votes);
                parcel.writeDouble(this.producePercentage);
                parcel.writeDouble(this.votesPercentage);
            }

            public ProposerBean() {
            }

            protected ProposerBean(Parcel parcel) {
                this.address = parcel.readString();
                this.name = parcel.readString();
                this.url = parcel.readString();
                this.producer = parcel.readByte() != 0;
                this.latestBlockNumber = parcel.readDouble();
                this.latestSlotNumber = parcel.readDouble();
                this.missedTotal = parcel.readDouble();
                this.producedTotal = parcel.readDouble();
                this.producedTrx = parcel.readLong();
                this.votes = parcel.readLong();
                this.producePercentage = parcel.readDouble();
                this.votesPercentage = parcel.readDouble();
            }
        }

        public static class ParamtersBean implements Parcelable {
            public static final Parcelable.Creator<ParamtersBean> CREATOR = new Parcelable.Creator<ParamtersBean>() {
                @Override
                public ParamtersBean createFromParcel(Parcel parcel) {
                    return new ParamtersBean(parcel);
                }

                @Override
                public ParamtersBean[] newArray(int i) {
                    return new ParamtersBean[i];
                }
            };
            public int key;
            public long value;

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeLong(this.value);
                parcel.writeInt(this.key);
            }

            public ParamtersBean() {
            }

            protected ParamtersBean(Parcel parcel) {
                this.value = parcel.readLong();
                this.key = parcel.readInt();
            }
        }

        public static class ApprovalsBean implements Parcelable {
            public static final Parcelable.Creator<ApprovalsBean> CREATOR = new Parcelable.Creator<ApprovalsBean>() {
                @Override
                public ApprovalsBean createFromParcel(Parcel parcel) {
                    return new ApprovalsBean(parcel);
                }

                @Override
                public ApprovalsBean[] newArray(int i) {
                    return new ApprovalsBean[i];
                }
            };
            public String address;
            public double latestBlockNumber;
            public double latestSlotNumber;
            public double missedTotal;
            public String name;
            public double producePercentage;
            public double producedTotal;
            public long producedTrx;
            public boolean producer;
            public String url;
            public long votes;
            public double votesPercentage;

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeString(this.address);
                parcel.writeString(this.name);
                parcel.writeString(this.url);
                parcel.writeByte(this.producer ? (byte) 1 : (byte) 0);
                parcel.writeDouble(this.latestBlockNumber);
                parcel.writeDouble(this.latestSlotNumber);
                parcel.writeDouble(this.missedTotal);
                parcel.writeDouble(this.producedTotal);
                parcel.writeLong(this.producedTrx);
                parcel.writeLong(this.votes);
                parcel.writeDouble(this.producePercentage);
                parcel.writeDouble(this.votesPercentage);
            }

            public ApprovalsBean() {
            }

            protected ApprovalsBean(Parcel parcel) {
                this.address = parcel.readString();
                this.name = parcel.readString();
                this.url = parcel.readString();
                this.producer = parcel.readByte() != 0;
                this.latestBlockNumber = parcel.readDouble();
                this.latestSlotNumber = parcel.readDouble();
                this.missedTotal = parcel.readDouble();
                this.producedTotal = parcel.readDouble();
                this.producedTrx = parcel.readLong();
                this.votes = parcel.readLong();
                this.producePercentage = parcel.readDouble();
                this.votesPercentage = parcel.readDouble();
            }
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(this.proposalId);
            parcel.writeParcelable(this.proposer, i);
            parcel.writeLong(this.expirationTime);
            parcel.writeLong(this.createTime);
            parcel.writeString(this.state);
            parcel.writeTypedList(this.paramters);
            parcel.writeTypedList(this.approvals);
        }

        public DataBean() {
        }

        protected DataBean(Parcel parcel) {
            this.proposalId = parcel.readLong();
            this.proposer = (ProposerBean) parcel.readParcelable(ProposerBean.class.getClassLoader());
            this.expirationTime = parcel.readLong();
            this.createTime = parcel.readLong();
            this.state = parcel.readString();
            this.paramters = parcel.createTypedArrayList(ParamtersBean.CREATOR);
            this.approvals = parcel.createTypedArrayList(ApprovalsBean.CREATOR);
        }
    }
}
