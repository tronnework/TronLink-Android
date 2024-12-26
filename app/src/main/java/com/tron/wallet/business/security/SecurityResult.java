package com.tron.wallet.business.security;

import com.tron.wallet.business.security.approvecheck.bean.ApproveListDatabeanOutput;
import com.tron.wallet.business.security.tokencheck.bean.TokenCheckResultBean;
import java.io.Serializable;
public class SecurityResult implements Serializable {
    private AuthorizationCheckData authorizationCheckData;
    private EnvironmentCheckData environmentCheckData;
    private ExecuteStatusEnum executeStatusEnum;
    private int riskNum;
    private TokenCheckResultBean tokenCheckData;
    private int waringNum;

    public AuthorizationCheckData getAuthorizationCheckData() {
        return this.authorizationCheckData;
    }

    public EnvironmentCheckData getEnvironmentCheckData() {
        return this.environmentCheckData;
    }

    public ExecuteStatusEnum getExecuteStatusEnum() {
        return this.executeStatusEnum;
    }

    public int getRiskNum() {
        return this.riskNum;
    }

    public TokenCheckResultBean getTokenCheckData() {
        return this.tokenCheckData;
    }

    public int getWaringNum() {
        return this.waringNum;
    }

    public void setAuthorizationCheckData(AuthorizationCheckData authorizationCheckData) {
        this.authorizationCheckData = authorizationCheckData;
    }

    public void setEnvironmentCheckData(EnvironmentCheckData environmentCheckData) {
        this.environmentCheckData = environmentCheckData;
    }

    public void setExecuteStatusEnum(ExecuteStatusEnum executeStatusEnum) {
        this.executeStatusEnum = executeStatusEnum;
    }

    public void setRiskNum(int i) {
        this.riskNum = i;
    }

    public void setTokenCheckData(TokenCheckResultBean tokenCheckResultBean) {
        this.tokenCheckData = tokenCheckResultBean;
    }

    public void setWaringNum(int i) {
        this.waringNum = i;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof SecurityResult;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecurityResult) {
            SecurityResult securityResult = (SecurityResult) obj;
            if (securityResult.canEqual(this) && getWaringNum() == securityResult.getWaringNum() && getRiskNum() == securityResult.getRiskNum()) {
                ExecuteStatusEnum executeStatusEnum = getExecuteStatusEnum();
                ExecuteStatusEnum executeStatusEnum2 = securityResult.getExecuteStatusEnum();
                if (executeStatusEnum != null ? executeStatusEnum.equals(executeStatusEnum2) : executeStatusEnum2 == null) {
                    EnvironmentCheckData environmentCheckData = getEnvironmentCheckData();
                    EnvironmentCheckData environmentCheckData2 = securityResult.getEnvironmentCheckData();
                    if (environmentCheckData != null ? environmentCheckData.equals(environmentCheckData2) : environmentCheckData2 == null) {
                        TokenCheckResultBean tokenCheckData = getTokenCheckData();
                        TokenCheckResultBean tokenCheckData2 = securityResult.getTokenCheckData();
                        if (tokenCheckData != null ? tokenCheckData.equals(tokenCheckData2) : tokenCheckData2 == null) {
                            AuthorizationCheckData authorizationCheckData = getAuthorizationCheckData();
                            AuthorizationCheckData authorizationCheckData2 = securityResult.getAuthorizationCheckData();
                            return authorizationCheckData != null ? authorizationCheckData.equals(authorizationCheckData2) : authorizationCheckData2 == null;
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
        int waringNum = ((getWaringNum() + 59) * 59) + getRiskNum();
        ExecuteStatusEnum executeStatusEnum = getExecuteStatusEnum();
        int hashCode = (waringNum * 59) + (executeStatusEnum == null ? 43 : executeStatusEnum.hashCode());
        EnvironmentCheckData environmentCheckData = getEnvironmentCheckData();
        int hashCode2 = (hashCode * 59) + (environmentCheckData == null ? 43 : environmentCheckData.hashCode());
        TokenCheckResultBean tokenCheckData = getTokenCheckData();
        int hashCode3 = (hashCode2 * 59) + (tokenCheckData == null ? 43 : tokenCheckData.hashCode());
        AuthorizationCheckData authorizationCheckData = getAuthorizationCheckData();
        return (hashCode3 * 59) + (authorizationCheckData != null ? authorizationCheckData.hashCode() : 43);
    }

    public String toString() {
        return "SecurityResult(waringNum=" + getWaringNum() + ", riskNum=" + getRiskNum() + ", executeStatusEnum=" + getExecuteStatusEnum() + ", environmentCheckData=" + getEnvironmentCheckData() + ", tokenCheckData=" + getTokenCheckData() + ", authorizationCheckData=" + getAuthorizationCheckData() + ")";
    }

    public static class EnvironmentCheckData implements Serializable {
        private ResultStatusEnum clipboardCheck;
        private ResultStatusEnum netCheck;
        private ResultStatusEnum officialCheck;
        private ResultStatusEnum rootCheck;
        private ResultStatusEnum simulatorCheck;

        public ResultStatusEnum getClipboardCheck() {
            return this.clipboardCheck;
        }

        public ResultStatusEnum getNetCheck() {
            return this.netCheck;
        }

        public ResultStatusEnum getOfficialCheck() {
            return this.officialCheck;
        }

        public ResultStatusEnum getRootCheck() {
            return this.rootCheck;
        }

        public ResultStatusEnum getSimulatorCheck() {
            return this.simulatorCheck;
        }

        public void setClipboardCheck(ResultStatusEnum resultStatusEnum) {
            this.clipboardCheck = resultStatusEnum;
        }

        public void setNetCheck(ResultStatusEnum resultStatusEnum) {
            this.netCheck = resultStatusEnum;
        }

        public void setOfficialCheck(ResultStatusEnum resultStatusEnum) {
            this.officialCheck = resultStatusEnum;
        }

        public void setRootCheck(ResultStatusEnum resultStatusEnum) {
            this.rootCheck = resultStatusEnum;
        }

        public void setSimulatorCheck(ResultStatusEnum resultStatusEnum) {
            this.simulatorCheck = resultStatusEnum;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof EnvironmentCheckData;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof EnvironmentCheckData) {
                EnvironmentCheckData environmentCheckData = (EnvironmentCheckData) obj;
                if (environmentCheckData.canEqual(this)) {
                    ResultStatusEnum rootCheck = getRootCheck();
                    ResultStatusEnum rootCheck2 = environmentCheckData.getRootCheck();
                    if (rootCheck != null ? rootCheck.equals(rootCheck2) : rootCheck2 == null) {
                        ResultStatusEnum simulatorCheck = getSimulatorCheck();
                        ResultStatusEnum simulatorCheck2 = environmentCheckData.getSimulatorCheck();
                        if (simulatorCheck != null ? simulatorCheck.equals(simulatorCheck2) : simulatorCheck2 == null) {
                            ResultStatusEnum netCheck = getNetCheck();
                            ResultStatusEnum netCheck2 = environmentCheckData.getNetCheck();
                            if (netCheck != null ? netCheck.equals(netCheck2) : netCheck2 == null) {
                                ResultStatusEnum clipboardCheck = getClipboardCheck();
                                ResultStatusEnum clipboardCheck2 = environmentCheckData.getClipboardCheck();
                                if (clipboardCheck != null ? clipboardCheck.equals(clipboardCheck2) : clipboardCheck2 == null) {
                                    ResultStatusEnum officialCheck = getOfficialCheck();
                                    ResultStatusEnum officialCheck2 = environmentCheckData.getOfficialCheck();
                                    return officialCheck != null ? officialCheck.equals(officialCheck2) : officialCheck2 == null;
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
            ResultStatusEnum rootCheck = getRootCheck();
            int hashCode = rootCheck == null ? 43 : rootCheck.hashCode();
            ResultStatusEnum simulatorCheck = getSimulatorCheck();
            int hashCode2 = ((hashCode + 59) * 59) + (simulatorCheck == null ? 43 : simulatorCheck.hashCode());
            ResultStatusEnum netCheck = getNetCheck();
            int hashCode3 = (hashCode2 * 59) + (netCheck == null ? 43 : netCheck.hashCode());
            ResultStatusEnum clipboardCheck = getClipboardCheck();
            int hashCode4 = (hashCode3 * 59) + (clipboardCheck == null ? 43 : clipboardCheck.hashCode());
            ResultStatusEnum officialCheck = getOfficialCheck();
            return (hashCode4 * 59) + (officialCheck != null ? officialCheck.hashCode() : 43);
        }

        public String toString() {
            return "SecurityResult.EnvironmentCheckData(rootCheck=" + getRootCheck() + ", simulatorCheck=" + getSimulatorCheck() + ", netCheck=" + getNetCheck() + ", clipboardCheck=" + getClipboardCheck() + ", officialCheck=" + getOfficialCheck() + ")";
        }
    }

    public static class TokenCheckData implements Serializable {
        private TokenCheckResultBean tokenCheckResultBean;

        public TokenCheckResultBean getTokenCheckResultBean() {
            return this.tokenCheckResultBean;
        }

        public void setTokenCheckResultBean(TokenCheckResultBean tokenCheckResultBean) {
            this.tokenCheckResultBean = tokenCheckResultBean;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof TokenCheckData;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof TokenCheckData) {
                TokenCheckData tokenCheckData = (TokenCheckData) obj;
                if (tokenCheckData.canEqual(this)) {
                    TokenCheckResultBean tokenCheckResultBean = getTokenCheckResultBean();
                    TokenCheckResultBean tokenCheckResultBean2 = tokenCheckData.getTokenCheckResultBean();
                    return tokenCheckResultBean != null ? tokenCheckResultBean.equals(tokenCheckResultBean2) : tokenCheckResultBean2 == null;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            TokenCheckResultBean tokenCheckResultBean = getTokenCheckResultBean();
            return 59 + (tokenCheckResultBean == null ? 43 : tokenCheckResultBean.hashCode());
        }

        public String toString() {
            return "SecurityResult.TokenCheckData(tokenCheckResultBean=" + getTokenCheckResultBean() + ")";
        }
    }

    public static class AuthorizationCheckData implements Serializable {
        private ApproveListDatabeanOutput.ApproveListDatabean databean;

        public ApproveListDatabeanOutput.ApproveListDatabean getDatabean() {
            return this.databean;
        }

        public void setDatabean(ApproveListDatabeanOutput.ApproveListDatabean approveListDatabean) {
            this.databean = approveListDatabean;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof AuthorizationCheckData;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof AuthorizationCheckData) {
                AuthorizationCheckData authorizationCheckData = (AuthorizationCheckData) obj;
                if (authorizationCheckData.canEqual(this)) {
                    ApproveListDatabeanOutput.ApproveListDatabean databean = getDatabean();
                    ApproveListDatabeanOutput.ApproveListDatabean databean2 = authorizationCheckData.getDatabean();
                    return databean != null ? databean.equals(databean2) : databean2 == null;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            ApproveListDatabeanOutput.ApproveListDatabean databean = getDatabean();
            return 59 + (databean == null ? 43 : databean.hashCode());
        }

        public String toString() {
            return "SecurityResult.AuthorizationCheckData(databean=" + getDatabean() + ")";
        }

        public AuthorizationCheckData(ApproveListDatabeanOutput approveListDatabeanOutput) {
            this.databean = approveListDatabeanOutput.getData();
        }
    }
}
