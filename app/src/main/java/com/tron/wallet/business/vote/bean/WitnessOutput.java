package com.tron.wallet.business.vote.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.List;
public class WitnessOutput implements Parcelable {
    public static final Parcelable.Creator<WitnessOutput> CREATOR = new Parcelable.Creator<WitnessOutput>() {
        @Override
        public WitnessOutput createFromParcel(Parcel parcel) {
            return new WitnessOutput(parcel);
        }

        @Override
        public WitnessOutput[] newArray(int i) {
            return new WitnessOutput[i];
        }
    };
    private List<DataBean> data;
    private long total;
    private long totalVotes;

    @Override
    public int describeContents() {
        return 0;
    }

    public List<DataBean> getData() {
        return this.data;
    }

    public long getTotal() {
        return this.total;
    }

    public long getTotalVotes() {
        return this.totalVotes;
    }

    public void setData(List<DataBean> list) {
        this.data = list;
    }

    public void setTotal(long j) {
        this.total = j;
    }

    public void setTotalVotes(long j) {
        this.totalVotes = j;
    }

    public WitnessOutput() {
    }

    public WitnessOutput(Parcel parcel) {
        this.total = parcel.readLong();
        this.totalVotes = parcel.readLong();
        this.data = parcel.createTypedArrayList(DataBean.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.total);
        parcel.writeLong(this.totalVotes);
        parcel.writeTypedList(this.data);
    }

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
        private String address;
        private double annualized_income;
        private int brokerage;
        private long changeVotes;
        private long change_cycle;
        private boolean hasChanged;
        private boolean hasPage;
        private int id;
        private transient boolean isSelected;
        private long lastCycleVotes;
        private long lastRanking;
        private String minApy;
        private String name;
        private double producedEfficiency;
        private long producedTotal;
        private long ranking;
        private long realTimeVotes;
        private String url;
        private String voted;
        private double votesPercentage;

        public static Parcelable.Creator<DataBean> getCREATOR() {
            return CREATOR;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public String getAddress() {
            return this.address;
        }

        public double getAnnualized_income() {
            return this.annualized_income;
        }

        public int getBrokerage() {
            return this.brokerage;
        }

        public long getChangeVotes() {
            return this.changeVotes;
        }

        public long getChange_cycle() {
            return this.change_cycle;
        }

        public int getId() {
            return this.id;
        }

        public long getLastCycleVotes() {
            return this.lastCycleVotes;
        }

        public long getLastRanking() {
            return this.lastRanking;
        }

        public String getMinApy() {
            return this.minApy;
        }

        public String getName() {
            return this.name;
        }

        public double getProducedEfficiency() {
            return this.producedEfficiency;
        }

        public long getProduced_total() {
            return this.producedTotal;
        }

        public long getRealTimeRanking() {
            return this.ranking;
        }

        public long getRealTimeVotes() {
            return this.realTimeVotes;
        }

        public String getUrl() {
            return this.url;
        }

        public String getVoted() {
            return this.voted;
        }

        public double getVotesPercentage() {
            return this.votesPercentage;
        }

        public boolean isHasChanged() {
            return this.hasChanged;
        }

        public boolean isHasPage() {
            return this.hasPage;
        }

        public boolean isSelected() {
            return this.isSelected;
        }

        public void setAddress(String str) {
            this.address = str;
        }

        public void setAnnualized_income(double d) {
            this.annualized_income = d;
        }

        public void setBrokerage(int i) {
            this.brokerage = i;
        }

        public void setChangeVotes(long j) {
            this.changeVotes = j;
        }

        public void setChange_cycle(long j) {
            this.change_cycle = j;
        }

        public void setHasChanged(boolean z) {
            this.hasChanged = z;
        }

        public void setHasPage(boolean z) {
            this.hasPage = z;
        }

        public void setId(int i) {
            this.id = i;
        }

        public void setLastCycleVotes(long j) {
            this.lastCycleVotes = j;
        }

        public void setLastRanking(long j) {
            this.lastRanking = j;
        }

        public void setMinApy(String str) {
            this.minApy = str;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setProducedEfficiency(double d) {
            this.producedEfficiency = d;
        }

        public void setProduced_total(long j) {
            this.producedTotal = j;
        }

        public void setRealTimeRanking(long j) {
            this.ranking = j;
        }

        public void setRealTimeVotes(long j) {
            this.realTimeVotes = j;
        }

        public void setSelected(boolean z) {
            this.isSelected = z;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public void setVoted(String str) {
            this.voted = str;
        }

        public void setVotesPercentage(double d) {
            this.votesPercentage = d;
        }

        public boolean equals(Object obj) {
            if (obj instanceof DataBean) {
                return TextUtils.equals(((DataBean) obj).address, this.address);
            }
            return false;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.id);
            parcel.writeLong(this.lastRanking);
            parcel.writeLong(this.ranking);
            parcel.writeString(this.address);
            parcel.writeString(this.name);
            parcel.writeString(this.url);
            parcel.writeByte(this.hasPage ? (byte) 1 : (byte) 0);
            parcel.writeLong(this.lastCycleVotes);
            parcel.writeLong(this.realTimeVotes);
            parcel.writeLong(this.changeVotes);
            parcel.writeDouble(this.votesPercentage);
            parcel.writeLong(this.change_cycle);
            parcel.writeInt(this.brokerage);
            parcel.writeDouble(this.annualized_income);
            parcel.writeLong(this.producedTotal);
            parcel.writeDouble(this.producedEfficiency);
            parcel.writeString(this.voted);
            parcel.writeByte(this.hasChanged ? (byte) 1 : (byte) 0);
            parcel.writeString(this.minApy);
        }

        public DataBean() {
        }

        protected DataBean(Parcel parcel) {
            this.id = parcel.readInt();
            this.lastRanking = parcel.readLong();
            this.ranking = parcel.readLong();
            this.address = parcel.readString();
            this.name = parcel.readString();
            this.url = parcel.readString();
            this.hasPage = parcel.readByte() != 0;
            this.lastCycleVotes = parcel.readLong();
            this.realTimeVotes = parcel.readLong();
            this.changeVotes = parcel.readLong();
            this.votesPercentage = parcel.readDouble();
            this.change_cycle = parcel.readLong();
            this.brokerage = parcel.readInt();
            this.annualized_income = parcel.readDouble();
            this.producedTotal = parcel.readLong();
            this.producedEfficiency = parcel.readDouble();
            this.voted = parcel.readString();
            this.hasChanged = parcel.readByte() != 0;
            this.minApy = parcel.readString();
        }
    }
}
