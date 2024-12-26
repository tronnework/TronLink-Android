package com.tron.wallet.business.security.approvecheck;

import com.tron.wallet.business.confirm.fg.bean.CancelApproveParam;
import com.tron.wallet.business.security.approvecheck.bean.ApproveListDatabeanOutput;
import com.tron.wallet.common.utils.BigDecimalUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
public class ApproveListManager {
    private static ApproveListManager instance;
    private ApproveListDatabeanOutput databean;

    public void clearData() {
        this.databean = null;
    }

    public ApproveListDatabeanOutput getData() {
        return this.databean;
    }

    public void initData(ApproveListDatabeanOutput approveListDatabeanOutput) {
        this.databean = approveListDatabeanOutput;
    }

    public void removeApproveItem(ApproveListDatabeanOutput.ApproveListDatabean.ProjectsBean.TokenListBean tokenListBean) {
    }

    public void removeApproveItem(ApproveListDatabeanOutput.ApproveListDatabean.TokensBean.ProjectListBean projectListBean) {
    }

    private ApproveListManager() {
    }

    public static synchronized ApproveListManager getInstance() {
        ApproveListManager approveListManager;
        synchronized (ApproveListManager.class) {
            if (instance == null) {
                instance = new ApproveListManager();
            }
            approveListManager = instance;
        }
        return approveListManager;
    }

    public void updateProjectData(ApproveListDatabeanOutput.ApproveListDatabean approveListDatabean) {
        if (approveListDatabean == null || approveListDatabean.getProjects() == null) {
            return;
        }
        if (this.databean == null) {
            this.databean = new ApproveListDatabeanOutput();
        }
        this.databean.getData().setProjects(approveListDatabean.getProjects());
    }

    public boolean removeApproveItemByParam(CancelApproveParam cancelApproveParam) {
        boolean z;
        boolean z2;
        ApproveListDatabeanOutput approveListDatabeanOutput = this.databean;
        if (approveListDatabeanOutput == null || approveListDatabeanOutput.getData() == null || cancelApproveParam == null) {
            z = false;
            z2 = false;
        } else {
            Iterator<ApproveListDatabeanOutput.ApproveListDatabean.TokensBean> it = this.databean.getData().getTokens().iterator();
            z = false;
            while (it.hasNext()) {
                ApproveListDatabeanOutput.ApproveListDatabean.TokensBean next = it.next();
                if (next.getTokenAddress().equals(cancelApproveParam.getTokenAddress())) {
                    Iterator<ApproveListDatabeanOutput.ApproveListDatabean.TokensBean.ProjectListBean> it2 = next.getProjectList().iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            ApproveListDatabeanOutput.ApproveListDatabean.TokensBean.ProjectListBean next2 = it2.next();
                            if (next2.getProjectAddress().equals(cancelApproveParam.getProjectAddress())) {
                                if (ApprovedListFragment.APPROVE_ADDRESS_TYPE_ACCOUNT.equals(next2.getApproveAddressType())) {
                                    ApproveListDatabeanOutput.ApproveListDatabean data = this.databean.getData();
                                    data.setApproveAddrCount((Integer.parseInt(this.databean.getData().getApproveAddrCount()) - 1) + "");
                                } else {
                                    ApproveListDatabeanOutput.ApproveListDatabean data2 = this.databean.getData();
                                    data2.setApproveContractCount((Integer.parseInt(this.databean.getData().getApproveContractCount()) - 1) + "");
                                }
                                BigDecimal sub_ = BigDecimalUtils.sub_(new BigDecimal(this.databean.getData().getTotalCnyCount()), new BigDecimal(next2.getCnyCount()));
                                BigDecimal sub_2 = BigDecimalUtils.sub_(new BigDecimal(this.databean.getData().getTotalUsdCount()), new BigDecimal(next2.getUsdCount()));
                                this.databean.getData().setTotalCnyCount(sub_.toPlainString());
                                this.databean.getData().setTotalUsdCount(sub_2.toPlainString());
                                if (next.getProjectList().size() == 1) {
                                    it.remove();
                                } else {
                                    it2.remove();
                                    BigDecimal sub_3 = BigDecimalUtils.sub_(new BigDecimal(next.getTotalCnyCount()), new BigDecimal(next2.getCnyCount()));
                                    BigDecimal sub_4 = BigDecimalUtils.sub_(new BigDecimal(next.getTotalUsdCount()), new BigDecimal(next2.getUsdCount()));
                                    next.setTotalCnyCount(sub_3.toPlainString());
                                    next.setTotalUsdCount(sub_4.toPlainString());
                                }
                                z = true;
                            }
                        }
                    }
                }
            }
            Iterator<ApproveListDatabeanOutput.ApproveListDatabean.ProjectsBean> it3 = this.databean.getData().getProjects().iterator();
            z2 = false;
            while (it3.hasNext()) {
                ApproveListDatabeanOutput.ApproveListDatabean.ProjectsBean next3 = it3.next();
                if (next3.getProjectAddress().equals(cancelApproveParam.getProjectAddress())) {
                    Iterator<ApproveListDatabeanOutput.ApproveListDatabean.ProjectsBean.TokenListBean> it4 = next3.getTokenList().iterator();
                    while (true) {
                        if (it4.hasNext()) {
                            ApproveListDatabeanOutput.ApproveListDatabean.ProjectsBean.TokenListBean next4 = it4.next();
                            if (next4.getTokenAddress().equals(cancelApproveParam.getTokenAddress())) {
                                if (next3.getTokenList().size() == 1) {
                                    it3.remove();
                                } else {
                                    it4.remove();
                                    BigDecimal sub_5 = BigDecimalUtils.sub_(new BigDecimal(next3.getTotalCnyCount()), new BigDecimal(next4.getCnyCount()));
                                    BigDecimal sub_6 = BigDecimalUtils.sub_(new BigDecimal(next3.getTotalUsdCount()), new BigDecimal(next4.getUsdCount()));
                                    next3.setTotalCnyCount(sub_5.toPlainString());
                                    next3.setTotalUsdCount(sub_6.toPlainString());
                                }
                                z2 = true;
                            }
                        }
                    }
                }
            }
        }
        checkContractAndAccountAmount();
        return z || z2;
    }

    private void checkContractAndAccountAmount() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (ApproveListDatabeanOutput.ApproveListDatabean.TokensBean tokensBean : this.databean.getData().getTokens()) {
            for (ApproveListDatabeanOutput.ApproveListDatabean.TokensBean.ProjectListBean projectListBean : tokensBean.getProjectList()) {
                if (ApprovedListFragment.APPROVE_ADDRESS_TYPE_ACCOUNT.equals(projectListBean.getApproveAddressType())) {
                    if (!arrayList2.contains(projectListBean.getProjectAddress())) {
                        arrayList2.add(projectListBean.getProjectAddress());
                    }
                } else if (!arrayList.contains(projectListBean.getProjectAddress())) {
                    arrayList.add(projectListBean.getProjectAddress());
                }
            }
        }
        if (Integer.parseInt(this.databean.getData().getApproveAddrCount()) != arrayList2.size()) {
            ApproveListDatabeanOutput.ApproveListDatabean data = this.databean.getData();
            data.setApproveAddrCount(arrayList2.size() + "");
        }
        if (Integer.parseInt(this.databean.getData().getApproveContractCount()) != arrayList.size()) {
            ApproveListDatabeanOutput.ApproveListDatabean data2 = this.databean.getData();
            data2.setApproveContractCount(arrayList.size() + "");
        }
    }
}
