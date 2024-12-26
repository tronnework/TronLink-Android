package com.tron.wallet.business.finance.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tron.wallet.business.addassets2.bean.BaseOutput;
import com.tron.wallet.common.config.TronConfig;
import java.util.List;
public class FinanceTokenListBean extends BaseOutput {
    @JsonProperty("data")
    private List<FinanceTokenListDataBean> data;

    public List<FinanceTokenListDataBean> getData() {
        return this.data;
    }

    @JsonProperty("data")
    public void setData(List<FinanceTokenListDataBean> list) {
        this.data = list;
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof FinanceTokenListBean;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FinanceTokenListBean) {
            FinanceTokenListBean financeTokenListBean = (FinanceTokenListBean) obj;
            if (financeTokenListBean.canEqual(this)) {
                List<FinanceTokenListDataBean> data = getData();
                List<FinanceTokenListDataBean> data2 = financeTokenListBean.getData();
                return data != null ? data.equals(data2) : data2 == null;
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        List<FinanceTokenListDataBean> data = getData();
        return 59 + (data == null ? 43 : data.hashCode());
    }

    @Override
    public String toString() {
        return "FinanceTokenListBean(data=" + getData() + ")";
    }

    public static class FinanceTokenListDataBean {
        @JsonProperty("apy")
        private String apy;
        @JsonProperty("balance")
        private String balance;
        @JsonProperty("hot")
        private String hot;
        @JsonProperty(TronConfig.PRECISION)
        private String precision;
        @JsonProperty("projectList")
        private List<ProjectListDTO> projectList;
        @JsonProperty("tokenIcon")
        private String tokenIcon;
        @JsonProperty("tokenId")
        private String tokenId;
        @JsonProperty("tokenName")
        private String tokenName;

        public String getApy() {
            return this.apy;
        }

        public String getBalance() {
            return this.balance;
        }

        public String getHot() {
            return this.hot;
        }

        public String getPrecision() {
            return this.precision;
        }

        public List<ProjectListDTO> getProjectList() {
            return this.projectList;
        }

        public String getTokenIcon() {
            return this.tokenIcon;
        }

        public String getTokenId() {
            return this.tokenId;
        }

        public String getTokenName() {
            return this.tokenName;
        }

        @JsonProperty("apy")
        public void setApy(String str) {
            this.apy = str;
        }

        @JsonProperty("balance")
        public void setBalance(String str) {
            this.balance = str;
        }

        @JsonProperty("hot")
        public void setHot(String str) {
            this.hot = str;
        }

        @JsonProperty(TronConfig.PRECISION)
        public void setPrecision(String str) {
            this.precision = str;
        }

        @JsonProperty("projectList")
        public void setProjectList(List<ProjectListDTO> list) {
            this.projectList = list;
        }

        @JsonProperty("tokenIcon")
        public void setTokenIcon(String str) {
            this.tokenIcon = str;
        }

        @JsonProperty("tokenId")
        public void setTokenId(String str) {
            this.tokenId = str;
        }

        @JsonProperty("tokenName")
        public void setTokenName(String str) {
            this.tokenName = str;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof FinanceTokenListDataBean;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof FinanceTokenListDataBean) {
                FinanceTokenListDataBean financeTokenListDataBean = (FinanceTokenListDataBean) obj;
                if (financeTokenListDataBean.canEqual(this)) {
                    String tokenId = getTokenId();
                    String tokenId2 = financeTokenListDataBean.getTokenId();
                    if (tokenId != null ? tokenId.equals(tokenId2) : tokenId2 == null) {
                        String tokenName = getTokenName();
                        String tokenName2 = financeTokenListDataBean.getTokenName();
                        if (tokenName != null ? tokenName.equals(tokenName2) : tokenName2 == null) {
                            String tokenIcon = getTokenIcon();
                            String tokenIcon2 = financeTokenListDataBean.getTokenIcon();
                            if (tokenIcon != null ? tokenIcon.equals(tokenIcon2) : tokenIcon2 == null) {
                                String precision = getPrecision();
                                String precision2 = financeTokenListDataBean.getPrecision();
                                if (precision != null ? precision.equals(precision2) : precision2 == null) {
                                    String hot = getHot();
                                    String hot2 = financeTokenListDataBean.getHot();
                                    if (hot != null ? hot.equals(hot2) : hot2 == null) {
                                        String apy = getApy();
                                        String apy2 = financeTokenListDataBean.getApy();
                                        if (apy != null ? apy.equals(apy2) : apy2 == null) {
                                            String balance = getBalance();
                                            String balance2 = financeTokenListDataBean.getBalance();
                                            if (balance != null ? balance.equals(balance2) : balance2 == null) {
                                                List<ProjectListDTO> projectList = getProjectList();
                                                List<ProjectListDTO> projectList2 = financeTokenListDataBean.getProjectList();
                                                return projectList != null ? projectList.equals(projectList2) : projectList2 == null;
                                            }
                                            return false;
                                        }
                                        return false;
                                    }
                                    return false;
                                }
                                return false;
                            }
                            return false;
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
            String tokenId = getTokenId();
            int hashCode = tokenId == null ? 43 : tokenId.hashCode();
            String tokenName = getTokenName();
            int hashCode2 = ((hashCode + 59) * 59) + (tokenName == null ? 43 : tokenName.hashCode());
            String tokenIcon = getTokenIcon();
            int hashCode3 = (hashCode2 * 59) + (tokenIcon == null ? 43 : tokenIcon.hashCode());
            String precision = getPrecision();
            int hashCode4 = (hashCode3 * 59) + (precision == null ? 43 : precision.hashCode());
            String hot = getHot();
            int hashCode5 = (hashCode4 * 59) + (hot == null ? 43 : hot.hashCode());
            String apy = getApy();
            int hashCode6 = (hashCode5 * 59) + (apy == null ? 43 : apy.hashCode());
            String balance = getBalance();
            int hashCode7 = (hashCode6 * 59) + (balance == null ? 43 : balance.hashCode());
            List<ProjectListDTO> projectList = getProjectList();
            return (hashCode7 * 59) + (projectList != null ? projectList.hashCode() : 43);
        }

        public String toString() {
            return "FinanceTokenListBean.FinanceTokenListDataBean(tokenId=" + getTokenId() + ", tokenName=" + getTokenName() + ", tokenIcon=" + getTokenIcon() + ", precision=" + getPrecision() + ", hot=" + getHot() + ", apy=" + getApy() + ", balance=" + getBalance() + ", projectList=" + getProjectList() + ")";
        }

        public static class ProjectListDTO {
            @JsonProperty("apy")
            private String apy;
            @JsonProperty("contractAddress")
            private String contractAddress;
            @JsonProperty("projectIcon")
            private String projectIcon;
            @JsonProperty("projectId")
            private String projectId;
            @JsonProperty("projectName")
            private String projectName;

            public String getApy() {
                return this.apy;
            }

            public String getContractAddress() {
                return this.contractAddress;
            }

            public String getProjectIcon() {
                return this.projectIcon;
            }

            public String getProjectId() {
                return this.projectId;
            }

            public String getProjectName() {
                return this.projectName;
            }

            @JsonProperty("apy")
            public void setApy(String str) {
                this.apy = str;
            }

            @JsonProperty("contractAddress")
            public void setContractAddress(String str) {
                this.contractAddress = str;
            }

            @JsonProperty("projectIcon")
            public void setProjectIcon(String str) {
                this.projectIcon = str;
            }

            @JsonProperty("projectId")
            public void setProjectId(String str) {
                this.projectId = str;
            }

            @JsonProperty("projectName")
            public void setProjectName(String str) {
                this.projectName = str;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof ProjectListDTO;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (obj instanceof ProjectListDTO) {
                    ProjectListDTO projectListDTO = (ProjectListDTO) obj;
                    if (projectListDTO.canEqual(this)) {
                        String projectName = getProjectName();
                        String projectName2 = projectListDTO.getProjectName();
                        if (projectName != null ? projectName.equals(projectName2) : projectName2 == null) {
                            String projectIcon = getProjectIcon();
                            String projectIcon2 = projectListDTO.getProjectIcon();
                            if (projectIcon != null ? projectIcon.equals(projectIcon2) : projectIcon2 == null) {
                                String projectId = getProjectId();
                                String projectId2 = projectListDTO.getProjectId();
                                if (projectId != null ? projectId.equals(projectId2) : projectId2 == null) {
                                    String apy = getApy();
                                    String apy2 = projectListDTO.getApy();
                                    if (apy != null ? apy.equals(apy2) : apy2 == null) {
                                        String contractAddress = getContractAddress();
                                        String contractAddress2 = projectListDTO.getContractAddress();
                                        return contractAddress != null ? contractAddress.equals(contractAddress2) : contractAddress2 == null;
                                    }
                                    return false;
                                }
                                return false;
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
                String projectName = getProjectName();
                int hashCode = projectName == null ? 43 : projectName.hashCode();
                String projectIcon = getProjectIcon();
                int hashCode2 = ((hashCode + 59) * 59) + (projectIcon == null ? 43 : projectIcon.hashCode());
                String projectId = getProjectId();
                int hashCode3 = (hashCode2 * 59) + (projectId == null ? 43 : projectId.hashCode());
                String apy = getApy();
                int hashCode4 = (hashCode3 * 59) + (apy == null ? 43 : apy.hashCode());
                String contractAddress = getContractAddress();
                return (hashCode4 * 59) + (contractAddress != null ? contractAddress.hashCode() : 43);
            }

            public String toString() {
                return "FinanceTokenListBean.FinanceTokenListDataBean.ProjectListDTO(projectName=" + getProjectName() + ", projectIcon=" + getProjectIcon() + ", projectId=" + getProjectId() + ", apy=" + getApy() + ", contractAddress=" + getContractAddress() + ")";
            }
        }
    }
}
