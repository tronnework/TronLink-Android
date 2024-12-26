package com.tron.wallet.business.vote.bean;

import com.tron.wallet.business.vote.bean.WitnessOutput;
import java.util.List;
public class FastAprBean {
    boolean alreadyVoted;
    double currentApr;
    double fastApr;
    List<WitnessOutput.DataBean> top3Witnesses;

    public double getCurrentApr() {
        return this.currentApr;
    }

    public double getFastApr() {
        return this.fastApr;
    }

    public List<WitnessOutput.DataBean> getTop3Witnesses() {
        return this.top3Witnesses;
    }

    public boolean isAlreadyVoted() {
        return this.alreadyVoted;
    }

    public void setAlreadyVoted(boolean z) {
        this.alreadyVoted = z;
    }

    public void setCurrentApr(double d) {
        this.currentApr = d;
    }

    public void setFastApr(double d) {
        this.fastApr = d;
    }

    public void setTop3Witnesses(List<WitnessOutput.DataBean> list) {
        this.top3Witnesses = list;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof FastAprBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FastAprBean) {
            FastAprBean fastAprBean = (FastAprBean) obj;
            if (fastAprBean.canEqual(this) && Double.compare(getCurrentApr(), fastAprBean.getCurrentApr()) == 0 && Double.compare(getFastApr(), fastAprBean.getFastApr()) == 0 && isAlreadyVoted() == fastAprBean.isAlreadyVoted()) {
                List<WitnessOutput.DataBean> top3Witnesses = getTop3Witnesses();
                List<WitnessOutput.DataBean> top3Witnesses2 = fastAprBean.getTop3Witnesses();
                return top3Witnesses != null ? top3Witnesses.equals(top3Witnesses2) : top3Witnesses2 == null;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(getCurrentApr());
        long doubleToLongBits2 = Double.doubleToLongBits(getFastApr());
        int i = ((((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + 59) * 59) + ((int) ((doubleToLongBits2 >>> 32) ^ doubleToLongBits2))) * 59) + (isAlreadyVoted() ? 79 : 97);
        List<WitnessOutput.DataBean> top3Witnesses = getTop3Witnesses();
        return (i * 59) + (top3Witnesses == null ? 43 : top3Witnesses.hashCode());
    }

    public String toString() {
        return "FastAprBean(currentApr=" + getCurrentApr() + ", fastApr=" + getFastApr() + ", alreadyVoted=" + isAlreadyVoted() + ", top3Witnesses=" + getTop3Witnesses() + ")";
    }

    public FastAprBean(double d, double d2, boolean z, List<WitnessOutput.DataBean> list) {
        this.currentApr = d;
        this.fastApr = d2;
        this.alreadyVoted = z;
        this.top3Witnesses = list;
    }
}
