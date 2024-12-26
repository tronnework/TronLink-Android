package com.tron.wallet.business.tabmy.myhome.settings.bean;

import java.util.List;
public class NodeInfoBean {
    private int activeConnectCount;
    private int beginSyncNum;
    private String block;
    private CheatWitnessInfoMapBean cheatWitnessInfoMap;
    private ConfigNodeInfoBean configNodeInfo;
    private int currentConnectCount;
    private MachineInfoBean machineInfo;
    private int passiveConnectCount;
    private List<PeerListBean> peerList;
    private String solidityBlock;
    private int totalFlow;

    public int getActiveConnectCount() {
        return this.activeConnectCount;
    }

    public int getBeginSyncNum() {
        return this.beginSyncNum;
    }

    public String getBlock() {
        return this.block;
    }

    public CheatWitnessInfoMapBean getCheatWitnessInfoMap() {
        return this.cheatWitnessInfoMap;
    }

    public ConfigNodeInfoBean getConfigNodeInfo() {
        return this.configNodeInfo;
    }

    public int getCurrentConnectCount() {
        return this.currentConnectCount;
    }

    public MachineInfoBean getMachineInfo() {
        return this.machineInfo;
    }

    public int getPassiveConnectCount() {
        return this.passiveConnectCount;
    }

    public List<PeerListBean> getPeerList() {
        return this.peerList;
    }

    public String getSolidityBlock() {
        return this.solidityBlock;
    }

    public int getTotalFlow() {
        return this.totalFlow;
    }

    public void setActiveConnectCount(int i) {
        this.activeConnectCount = i;
    }

    public void setBeginSyncNum(int i) {
        this.beginSyncNum = i;
    }

    public void setBlock(String str) {
        this.block = str;
    }

    public void setCheatWitnessInfoMap(CheatWitnessInfoMapBean cheatWitnessInfoMapBean) {
        this.cheatWitnessInfoMap = cheatWitnessInfoMapBean;
    }

    public void setConfigNodeInfo(ConfigNodeInfoBean configNodeInfoBean) {
        this.configNodeInfo = configNodeInfoBean;
    }

    public void setCurrentConnectCount(int i) {
        this.currentConnectCount = i;
    }

    public void setMachineInfo(MachineInfoBean machineInfoBean) {
        this.machineInfo = machineInfoBean;
    }

    public void setPassiveConnectCount(int i) {
        this.passiveConnectCount = i;
    }

    public void setPeerList(List<PeerListBean> list) {
        this.peerList = list;
    }

    public void setSolidityBlock(String str) {
        this.solidityBlock = str;
    }

    public void setTotalFlow(int i) {
        this.totalFlow = i;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof NodeInfoBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof NodeInfoBean) {
            NodeInfoBean nodeInfoBean = (NodeInfoBean) obj;
            if (nodeInfoBean.canEqual(this) && getActiveConnectCount() == nodeInfoBean.getActiveConnectCount() && getBeginSyncNum() == nodeInfoBean.getBeginSyncNum() && getCurrentConnectCount() == nodeInfoBean.getCurrentConnectCount() && getPassiveConnectCount() == nodeInfoBean.getPassiveConnectCount() && getTotalFlow() == nodeInfoBean.getTotalFlow()) {
                String block = getBlock();
                String block2 = nodeInfoBean.getBlock();
                if (block != null ? block.equals(block2) : block2 == null) {
                    CheatWitnessInfoMapBean cheatWitnessInfoMap = getCheatWitnessInfoMap();
                    CheatWitnessInfoMapBean cheatWitnessInfoMap2 = nodeInfoBean.getCheatWitnessInfoMap();
                    if (cheatWitnessInfoMap != null ? cheatWitnessInfoMap.equals(cheatWitnessInfoMap2) : cheatWitnessInfoMap2 == null) {
                        ConfigNodeInfoBean configNodeInfo = getConfigNodeInfo();
                        ConfigNodeInfoBean configNodeInfo2 = nodeInfoBean.getConfigNodeInfo();
                        if (configNodeInfo != null ? configNodeInfo.equals(configNodeInfo2) : configNodeInfo2 == null) {
                            MachineInfoBean machineInfo = getMachineInfo();
                            MachineInfoBean machineInfo2 = nodeInfoBean.getMachineInfo();
                            if (machineInfo != null ? machineInfo.equals(machineInfo2) : machineInfo2 == null) {
                                String solidityBlock = getSolidityBlock();
                                String solidityBlock2 = nodeInfoBean.getSolidityBlock();
                                if (solidityBlock != null ? solidityBlock.equals(solidityBlock2) : solidityBlock2 == null) {
                                    List<PeerListBean> peerList = getPeerList();
                                    List<PeerListBean> peerList2 = nodeInfoBean.getPeerList();
                                    return peerList != null ? peerList.equals(peerList2) : peerList2 == null;
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
        int activeConnectCount = ((((((((getActiveConnectCount() + 59) * 59) + getBeginSyncNum()) * 59) + getCurrentConnectCount()) * 59) + getPassiveConnectCount()) * 59) + getTotalFlow();
        String block = getBlock();
        int hashCode = (activeConnectCount * 59) + (block == null ? 43 : block.hashCode());
        CheatWitnessInfoMapBean cheatWitnessInfoMap = getCheatWitnessInfoMap();
        int hashCode2 = (hashCode * 59) + (cheatWitnessInfoMap == null ? 43 : cheatWitnessInfoMap.hashCode());
        ConfigNodeInfoBean configNodeInfo = getConfigNodeInfo();
        int hashCode3 = (hashCode2 * 59) + (configNodeInfo == null ? 43 : configNodeInfo.hashCode());
        MachineInfoBean machineInfo = getMachineInfo();
        int hashCode4 = (hashCode3 * 59) + (machineInfo == null ? 43 : machineInfo.hashCode());
        String solidityBlock = getSolidityBlock();
        int hashCode5 = (hashCode4 * 59) + (solidityBlock == null ? 43 : solidityBlock.hashCode());
        List<PeerListBean> peerList = getPeerList();
        return (hashCode5 * 59) + (peerList != null ? peerList.hashCode() : 43);
    }

    public String toString() {
        return "NodeInfoBean(activeConnectCount=" + getActiveConnectCount() + ", beginSyncNum=" + getBeginSyncNum() + ", block=" + getBlock() + ", cheatWitnessInfoMap=" + getCheatWitnessInfoMap() + ", configNodeInfo=" + getConfigNodeInfo() + ", currentConnectCount=" + getCurrentConnectCount() + ", machineInfo=" + getMachineInfo() + ", passiveConnectCount=" + getPassiveConnectCount() + ", solidityBlock=" + getSolidityBlock() + ", totalFlow=" + getTotalFlow() + ", peerList=" + getPeerList() + ")";
    }

    public static class CheatWitnessInfoMapBean {
        public int hashCode() {
            return 1;
        }

        public String toString() {
            return "NodeInfoBean.CheatWitnessInfoMapBean()";
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof CheatWitnessInfoMapBean;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return (obj instanceof CheatWitnessInfoMapBean) && ((CheatWitnessInfoMapBean) obj).canEqual(this);
        }
    }

    public static class ConfigNodeInfoBean {
        private int activeNodeSize;
        private int allowAdaptiveEnergy;
        private int allowCreationOfContracts;
        private int backupListenPort;
        private int backupMemberSize;
        private int backupPriority;
        private String codeVersion;
        private int dbVersion;
        private boolean discoverEnable;
        private int listenPort;
        private int maxConnectCount;
        private int maxTimeRatio;
        private int minParticipationRate;
        private int minTimeRatio;
        private String p2pVersion;
        private int passiveNodeSize;
        private int sameIpMaxConnectCount;
        private int sendNodeSize;
        private boolean supportConstant;
        private String versionName;
        private String versionNum;

        public int getActiveNodeSize() {
            return this.activeNodeSize;
        }

        public int getAllowAdaptiveEnergy() {
            return this.allowAdaptiveEnergy;
        }

        public int getAllowCreationOfContracts() {
            return this.allowCreationOfContracts;
        }

        public int getBackupListenPort() {
            return this.backupListenPort;
        }

        public int getBackupMemberSize() {
            return this.backupMemberSize;
        }

        public int getBackupPriority() {
            return this.backupPriority;
        }

        public String getCodeVersion() {
            return this.codeVersion;
        }

        public int getDbVersion() {
            return this.dbVersion;
        }

        public int getListenPort() {
            return this.listenPort;
        }

        public int getMaxConnectCount() {
            return this.maxConnectCount;
        }

        public int getMaxTimeRatio() {
            return this.maxTimeRatio;
        }

        public int getMinParticipationRate() {
            return this.minParticipationRate;
        }

        public int getMinTimeRatio() {
            return this.minTimeRatio;
        }

        public String getP2pVersion() {
            return this.p2pVersion;
        }

        public int getPassiveNodeSize() {
            return this.passiveNodeSize;
        }

        public int getSameIpMaxConnectCount() {
            return this.sameIpMaxConnectCount;
        }

        public int getSendNodeSize() {
            return this.sendNodeSize;
        }

        public String getVersionName() {
            return this.versionName;
        }

        public String getVersionNum() {
            return this.versionNum;
        }

        public boolean isDiscoverEnable() {
            return this.discoverEnable;
        }

        public boolean isSupportConstant() {
            return this.supportConstant;
        }

        public void setActiveNodeSize(int i) {
            this.activeNodeSize = i;
        }

        public void setAllowAdaptiveEnergy(int i) {
            this.allowAdaptiveEnergy = i;
        }

        public void setAllowCreationOfContracts(int i) {
            this.allowCreationOfContracts = i;
        }

        public void setBackupListenPort(int i) {
            this.backupListenPort = i;
        }

        public void setBackupMemberSize(int i) {
            this.backupMemberSize = i;
        }

        public void setBackupPriority(int i) {
            this.backupPriority = i;
        }

        public void setCodeVersion(String str) {
            this.codeVersion = str;
        }

        public void setDbVersion(int i) {
            this.dbVersion = i;
        }

        public void setDiscoverEnable(boolean z) {
            this.discoverEnable = z;
        }

        public void setListenPort(int i) {
            this.listenPort = i;
        }

        public void setMaxConnectCount(int i) {
            this.maxConnectCount = i;
        }

        public void setMaxTimeRatio(int i) {
            this.maxTimeRatio = i;
        }

        public void setMinParticipationRate(int i) {
            this.minParticipationRate = i;
        }

        public void setMinTimeRatio(int i) {
            this.minTimeRatio = i;
        }

        public void setP2pVersion(String str) {
            this.p2pVersion = str;
        }

        public void setPassiveNodeSize(int i) {
            this.passiveNodeSize = i;
        }

        public void setSameIpMaxConnectCount(int i) {
            this.sameIpMaxConnectCount = i;
        }

        public void setSendNodeSize(int i) {
            this.sendNodeSize = i;
        }

        public void setSupportConstant(boolean z) {
            this.supportConstant = z;
        }

        public void setVersionName(String str) {
            this.versionName = str;
        }

        public void setVersionNum(String str) {
            this.versionNum = str;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof ConfigNodeInfoBean;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof ConfigNodeInfoBean) {
                ConfigNodeInfoBean configNodeInfoBean = (ConfigNodeInfoBean) obj;
                if (configNodeInfoBean.canEqual(this) && getActiveNodeSize() == configNodeInfoBean.getActiveNodeSize() && getAllowAdaptiveEnergy() == configNodeInfoBean.getAllowAdaptiveEnergy() && getAllowCreationOfContracts() == configNodeInfoBean.getAllowCreationOfContracts() && getBackupListenPort() == configNodeInfoBean.getBackupListenPort() && getBackupMemberSize() == configNodeInfoBean.getBackupMemberSize() && getBackupPriority() == configNodeInfoBean.getBackupPriority() && getDbVersion() == configNodeInfoBean.getDbVersion() && isDiscoverEnable() == configNodeInfoBean.isDiscoverEnable() && getListenPort() == configNodeInfoBean.getListenPort() && getMaxConnectCount() == configNodeInfoBean.getMaxConnectCount() && getMaxTimeRatio() == configNodeInfoBean.getMaxTimeRatio() && getMinParticipationRate() == configNodeInfoBean.getMinParticipationRate() && getMinTimeRatio() == configNodeInfoBean.getMinTimeRatio() && getPassiveNodeSize() == configNodeInfoBean.getPassiveNodeSize() && getSameIpMaxConnectCount() == configNodeInfoBean.getSameIpMaxConnectCount() && getSendNodeSize() == configNodeInfoBean.getSendNodeSize() && isSupportConstant() == configNodeInfoBean.isSupportConstant()) {
                    String codeVersion = getCodeVersion();
                    String codeVersion2 = configNodeInfoBean.getCodeVersion();
                    if (codeVersion != null ? codeVersion.equals(codeVersion2) : codeVersion2 == null) {
                        String p2pVersion = getP2pVersion();
                        String p2pVersion2 = configNodeInfoBean.getP2pVersion();
                        if (p2pVersion != null ? p2pVersion.equals(p2pVersion2) : p2pVersion2 == null) {
                            String versionName = getVersionName();
                            String versionName2 = configNodeInfoBean.getVersionName();
                            if (versionName != null ? versionName.equals(versionName2) : versionName2 == null) {
                                String versionNum = getVersionNum();
                                String versionNum2 = configNodeInfoBean.getVersionNum();
                                return versionNum != null ? versionNum.equals(versionNum2) : versionNum2 == null;
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
            int activeNodeSize = (((((((((((((((((((((((((((((((getActiveNodeSize() + 59) * 59) + getAllowAdaptiveEnergy()) * 59) + getAllowCreationOfContracts()) * 59) + getBackupListenPort()) * 59) + getBackupMemberSize()) * 59) + getBackupPriority()) * 59) + getDbVersion()) * 59) + (isDiscoverEnable() ? 79 : 97)) * 59) + getListenPort()) * 59) + getMaxConnectCount()) * 59) + getMaxTimeRatio()) * 59) + getMinParticipationRate()) * 59) + getMinTimeRatio()) * 59) + getPassiveNodeSize()) * 59) + getSameIpMaxConnectCount()) * 59) + getSendNodeSize()) * 59;
            int i = isSupportConstant() ? 79 : 97;
            String codeVersion = getCodeVersion();
            int hashCode = ((activeNodeSize + i) * 59) + (codeVersion == null ? 43 : codeVersion.hashCode());
            String p2pVersion = getP2pVersion();
            int hashCode2 = (hashCode * 59) + (p2pVersion == null ? 43 : p2pVersion.hashCode());
            String versionName = getVersionName();
            int hashCode3 = (hashCode2 * 59) + (versionName == null ? 43 : versionName.hashCode());
            String versionNum = getVersionNum();
            return (hashCode3 * 59) + (versionNum != null ? versionNum.hashCode() : 43);
        }

        public String toString() {
            return "NodeInfoBean.ConfigNodeInfoBean(activeNodeSize=" + getActiveNodeSize() + ", allowAdaptiveEnergy=" + getAllowAdaptiveEnergy() + ", allowCreationOfContracts=" + getAllowCreationOfContracts() + ", backupListenPort=" + getBackupListenPort() + ", backupMemberSize=" + getBackupMemberSize() + ", backupPriority=" + getBackupPriority() + ", codeVersion=" + getCodeVersion() + ", dbVersion=" + getDbVersion() + ", discoverEnable=" + isDiscoverEnable() + ", listenPort=" + getListenPort() + ", maxConnectCount=" + getMaxConnectCount() + ", maxTimeRatio=" + getMaxTimeRatio() + ", minParticipationRate=" + getMinParticipationRate() + ", minTimeRatio=" + getMinTimeRatio() + ", p2pVersion=" + getP2pVersion() + ", passiveNodeSize=" + getPassiveNodeSize() + ", sameIpMaxConnectCount=" + getSameIpMaxConnectCount() + ", sendNodeSize=" + getSendNodeSize() + ", supportConstant=" + isSupportConstant() + ", versionName=" + getVersionName() + ", versionNum=" + getVersionNum() + ")";
        }
    }

    public static class MachineInfoBean {
        private int cpuCount;
        private double cpuRate;
        private int deadLockThreadCount;
        private List<?> deadLockThreadInfoList;
        private long freeMemory;
        private String javaVersion;
        private long jvmFreeMemory;
        private long jvmTotalMemoery;
        private List<MemoryDescInfoListBean> memoryDescInfoList;
        private String osName;
        private double processCpuRate;
        private int threadCount;
        private long totalMemory;

        public int getCpuCount() {
            return this.cpuCount;
        }

        public double getCpuRate() {
            return this.cpuRate;
        }

        public int getDeadLockThreadCount() {
            return this.deadLockThreadCount;
        }

        public List<?> getDeadLockThreadInfoList() {
            return this.deadLockThreadInfoList;
        }

        public long getFreeMemory() {
            return this.freeMemory;
        }

        public String getJavaVersion() {
            return this.javaVersion;
        }

        public long getJvmFreeMemory() {
            return this.jvmFreeMemory;
        }

        public long getJvmTotalMemoery() {
            return this.jvmTotalMemoery;
        }

        public List<MemoryDescInfoListBean> getMemoryDescInfoList() {
            return this.memoryDescInfoList;
        }

        public String getOsName() {
            return this.osName;
        }

        public double getProcessCpuRate() {
            return this.processCpuRate;
        }

        public int getThreadCount() {
            return this.threadCount;
        }

        public long getTotalMemory() {
            return this.totalMemory;
        }

        public void setCpuCount(int i) {
            this.cpuCount = i;
        }

        public void setCpuRate(double d) {
            this.cpuRate = d;
        }

        public void setDeadLockThreadCount(int i) {
            this.deadLockThreadCount = i;
        }

        public void setDeadLockThreadInfoList(List<?> list) {
            this.deadLockThreadInfoList = list;
        }

        public void setFreeMemory(long j) {
            this.freeMemory = j;
        }

        public void setJavaVersion(String str) {
            this.javaVersion = str;
        }

        public void setJvmFreeMemory(long j) {
            this.jvmFreeMemory = j;
        }

        public void setJvmTotalMemoery(long j) {
            this.jvmTotalMemoery = j;
        }

        public void setMemoryDescInfoList(List<MemoryDescInfoListBean> list) {
            this.memoryDescInfoList = list;
        }

        public void setOsName(String str) {
            this.osName = str;
        }

        public void setProcessCpuRate(double d) {
            this.processCpuRate = d;
        }

        public void setThreadCount(int i) {
            this.threadCount = i;
        }

        public void setTotalMemory(long j) {
            this.totalMemory = j;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof MachineInfoBean;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof MachineInfoBean) {
                MachineInfoBean machineInfoBean = (MachineInfoBean) obj;
                if (machineInfoBean.canEqual(this) && getCpuCount() == machineInfoBean.getCpuCount() && Double.compare(getCpuRate(), machineInfoBean.getCpuRate()) == 0 && getDeadLockThreadCount() == machineInfoBean.getDeadLockThreadCount() && getFreeMemory() == machineInfoBean.getFreeMemory() && getJvmFreeMemory() == machineInfoBean.getJvmFreeMemory() && getJvmTotalMemoery() == machineInfoBean.getJvmTotalMemoery() && Double.compare(getProcessCpuRate(), machineInfoBean.getProcessCpuRate()) == 0 && getThreadCount() == machineInfoBean.getThreadCount() && getTotalMemory() == machineInfoBean.getTotalMemory()) {
                    String javaVersion = getJavaVersion();
                    String javaVersion2 = machineInfoBean.getJavaVersion();
                    if (javaVersion != null ? javaVersion.equals(javaVersion2) : javaVersion2 == null) {
                        String osName = getOsName();
                        String osName2 = machineInfoBean.getOsName();
                        if (osName != null ? osName.equals(osName2) : osName2 == null) {
                            List<?> deadLockThreadInfoList = getDeadLockThreadInfoList();
                            List<?> deadLockThreadInfoList2 = machineInfoBean.getDeadLockThreadInfoList();
                            if (deadLockThreadInfoList != null ? deadLockThreadInfoList.equals(deadLockThreadInfoList2) : deadLockThreadInfoList2 == null) {
                                List<MemoryDescInfoListBean> memoryDescInfoList = getMemoryDescInfoList();
                                List<MemoryDescInfoListBean> memoryDescInfoList2 = machineInfoBean.getMemoryDescInfoList();
                                return memoryDescInfoList != null ? memoryDescInfoList.equals(memoryDescInfoList2) : memoryDescInfoList2 == null;
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
            long doubleToLongBits = Double.doubleToLongBits(getCpuRate());
            int cpuCount = ((((getCpuCount() + 59) * 59) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 59) + getDeadLockThreadCount();
            long freeMemory = getFreeMemory();
            long jvmFreeMemory = getJvmFreeMemory();
            long jvmTotalMemoery = getJvmTotalMemoery();
            long doubleToLongBits2 = Double.doubleToLongBits(getProcessCpuRate());
            int threadCount = (((((((((cpuCount * 59) + ((int) (freeMemory ^ (freeMemory >>> 32)))) * 59) + ((int) (jvmFreeMemory ^ (jvmFreeMemory >>> 32)))) * 59) + ((int) (jvmTotalMemoery ^ (jvmTotalMemoery >>> 32)))) * 59) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 59) + getThreadCount();
            long totalMemory = getTotalMemory();
            String javaVersion = getJavaVersion();
            int hashCode = (((threadCount * 59) + ((int) (totalMemory ^ (totalMemory >>> 32)))) * 59) + (javaVersion == null ? 43 : javaVersion.hashCode());
            String osName = getOsName();
            int hashCode2 = (hashCode * 59) + (osName == null ? 43 : osName.hashCode());
            List<?> deadLockThreadInfoList = getDeadLockThreadInfoList();
            int hashCode3 = (hashCode2 * 59) + (deadLockThreadInfoList == null ? 43 : deadLockThreadInfoList.hashCode());
            List<MemoryDescInfoListBean> memoryDescInfoList = getMemoryDescInfoList();
            return (hashCode3 * 59) + (memoryDescInfoList != null ? memoryDescInfoList.hashCode() : 43);
        }

        public String toString() {
            return "NodeInfoBean.MachineInfoBean(cpuCount=" + getCpuCount() + ", cpuRate=" + getCpuRate() + ", deadLockThreadCount=" + getDeadLockThreadCount() + ", freeMemory=" + getFreeMemory() + ", javaVersion=" + getJavaVersion() + ", jvmFreeMemory=" + getJvmFreeMemory() + ", jvmTotalMemoery=" + getJvmTotalMemoery() + ", osName=" + getOsName() + ", processCpuRate=" + getProcessCpuRate() + ", threadCount=" + getThreadCount() + ", totalMemory=" + getTotalMemory() + ", deadLockThreadInfoList=" + getDeadLockThreadInfoList() + ", memoryDescInfoList=" + getMemoryDescInfoList() + ")";
        }
    }

    public static class MemoryDescInfoListBean {
        private long initSize;
        private long maxSize;
        private String name;
        private double useRate;
        private long useSize;

        public long getInitSize() {
            return this.initSize;
        }

        public long getMaxSize() {
            return this.maxSize;
        }

        public String getName() {
            return this.name;
        }

        public double getUseRate() {
            return this.useRate;
        }

        public long getUseSize() {
            return this.useSize;
        }

        public void setInitSize(long j) {
            this.initSize = j;
        }

        public void setMaxSize(long j) {
            this.maxSize = j;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setUseRate(double d) {
            this.useRate = d;
        }

        public void setUseSize(long j) {
            this.useSize = j;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof MemoryDescInfoListBean;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof MemoryDescInfoListBean) {
                MemoryDescInfoListBean memoryDescInfoListBean = (MemoryDescInfoListBean) obj;
                if (memoryDescInfoListBean.canEqual(this) && getInitSize() == memoryDescInfoListBean.getInitSize() && getMaxSize() == memoryDescInfoListBean.getMaxSize() && Double.compare(getUseRate(), memoryDescInfoListBean.getUseRate()) == 0 && getUseSize() == memoryDescInfoListBean.getUseSize()) {
                    String name = getName();
                    String name2 = memoryDescInfoListBean.getName();
                    return name != null ? name.equals(name2) : name2 == null;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            long initSize = getInitSize();
            long maxSize = getMaxSize();
            long doubleToLongBits = Double.doubleToLongBits(getUseRate());
            long useSize = getUseSize();
            String name = getName();
            return ((((((((((int) (initSize ^ (initSize >>> 32))) + 59) * 59) + ((int) (maxSize ^ (maxSize >>> 32)))) * 59) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 59) + ((int) ((useSize >>> 32) ^ useSize))) * 59) + (name == null ? 43 : name.hashCode());
        }

        public String toString() {
            return "NodeInfoBean.MemoryDescInfoListBean(initSize=" + getInitSize() + ", maxSize=" + getMaxSize() + ", name=" + getName() + ", useRate=" + getUseRate() + ", useSize=" + getUseSize() + ")";
        }
    }

    public static class PeerListBean {
        private boolean active;
        private double avgLatency;
        private int blockInPorcSize;
        private long connectTime;
        private int disconnectTimes;
        private int headBlockTimeWeBothHave;
        private String headBlockWeBothHave;
        private String host;
        private int inFlow;
        private long lastBlockUpdateTime;
        private String lastSyncBlock;
        private String localDisconnectReason;
        private boolean needSyncFromPeer;
        private boolean needSyncFromUs;
        private int nodeCount;
        private String nodeId;
        private int port;
        private int remainNum;
        private String remoteDisconnectReason;
        private int score;
        private int syncBlockRequestedSize;
        private boolean syncFlag;
        private int syncToFetchSize;
        private int syncToFetchSizePeekNum;
        private int unFetchSynNum;

        public double getAvgLatency() {
            return this.avgLatency;
        }

        public int getBlockInPorcSize() {
            return this.blockInPorcSize;
        }

        public long getConnectTime() {
            return this.connectTime;
        }

        public int getDisconnectTimes() {
            return this.disconnectTimes;
        }

        public int getHeadBlockTimeWeBothHave() {
            return this.headBlockTimeWeBothHave;
        }

        public String getHeadBlockWeBothHave() {
            return this.headBlockWeBothHave;
        }

        public String getHost() {
            return this.host;
        }

        public int getInFlow() {
            return this.inFlow;
        }

        public long getLastBlockUpdateTime() {
            return this.lastBlockUpdateTime;
        }

        public String getLastSyncBlock() {
            return this.lastSyncBlock;
        }

        public String getLocalDisconnectReason() {
            return this.localDisconnectReason;
        }

        public int getNodeCount() {
            return this.nodeCount;
        }

        public String getNodeId() {
            return this.nodeId;
        }

        public int getPort() {
            return this.port;
        }

        public int getRemainNum() {
            return this.remainNum;
        }

        public String getRemoteDisconnectReason() {
            return this.remoteDisconnectReason;
        }

        public int getScore() {
            return this.score;
        }

        public int getSyncBlockRequestedSize() {
            return this.syncBlockRequestedSize;
        }

        public int getSyncToFetchSize() {
            return this.syncToFetchSize;
        }

        public int getSyncToFetchSizePeekNum() {
            return this.syncToFetchSizePeekNum;
        }

        public int getUnFetchSynNum() {
            return this.unFetchSynNum;
        }

        public boolean isActive() {
            return this.active;
        }

        public boolean isNeedSyncFromPeer() {
            return this.needSyncFromPeer;
        }

        public boolean isNeedSyncFromUs() {
            return this.needSyncFromUs;
        }

        public boolean isSyncFlag() {
            return this.syncFlag;
        }

        public void setActive(boolean z) {
            this.active = z;
        }

        public void setAvgLatency(double d) {
            this.avgLatency = d;
        }

        public void setBlockInPorcSize(int i) {
            this.blockInPorcSize = i;
        }

        public void setConnectTime(long j) {
            this.connectTime = j;
        }

        public void setDisconnectTimes(int i) {
            this.disconnectTimes = i;
        }

        public void setHeadBlockTimeWeBothHave(int i) {
            this.headBlockTimeWeBothHave = i;
        }

        public void setHeadBlockWeBothHave(String str) {
            this.headBlockWeBothHave = str;
        }

        public void setHost(String str) {
            this.host = str;
        }

        public void setInFlow(int i) {
            this.inFlow = i;
        }

        public void setLastBlockUpdateTime(long j) {
            this.lastBlockUpdateTime = j;
        }

        public void setLastSyncBlock(String str) {
            this.lastSyncBlock = str;
        }

        public void setLocalDisconnectReason(String str) {
            this.localDisconnectReason = str;
        }

        public void setNeedSyncFromPeer(boolean z) {
            this.needSyncFromPeer = z;
        }

        public void setNeedSyncFromUs(boolean z) {
            this.needSyncFromUs = z;
        }

        public void setNodeCount(int i) {
            this.nodeCount = i;
        }

        public void setNodeId(String str) {
            this.nodeId = str;
        }

        public void setPort(int i) {
            this.port = i;
        }

        public void setRemainNum(int i) {
            this.remainNum = i;
        }

        public void setRemoteDisconnectReason(String str) {
            this.remoteDisconnectReason = str;
        }

        public void setScore(int i) {
            this.score = i;
        }

        public void setSyncBlockRequestedSize(int i) {
            this.syncBlockRequestedSize = i;
        }

        public void setSyncFlag(boolean z) {
            this.syncFlag = z;
        }

        public void setSyncToFetchSize(int i) {
            this.syncToFetchSize = i;
        }

        public void setSyncToFetchSizePeekNum(int i) {
            this.syncToFetchSizePeekNum = i;
        }

        public void setUnFetchSynNum(int i) {
            this.unFetchSynNum = i;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof PeerListBean;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof PeerListBean) {
                PeerListBean peerListBean = (PeerListBean) obj;
                if (peerListBean.canEqual(this) && isActive() == peerListBean.isActive() && Double.compare(getAvgLatency(), peerListBean.getAvgLatency()) == 0 && getBlockInPorcSize() == peerListBean.getBlockInPorcSize() && getConnectTime() == peerListBean.getConnectTime() && getDisconnectTimes() == peerListBean.getDisconnectTimes() && getHeadBlockTimeWeBothHave() == peerListBean.getHeadBlockTimeWeBothHave() && getInFlow() == peerListBean.getInFlow() && getLastBlockUpdateTime() == peerListBean.getLastBlockUpdateTime() && isNeedSyncFromPeer() == peerListBean.isNeedSyncFromPeer() && isNeedSyncFromUs() == peerListBean.isNeedSyncFromUs() && getNodeCount() == peerListBean.getNodeCount() && getPort() == peerListBean.getPort() && getRemainNum() == peerListBean.getRemainNum() && getScore() == peerListBean.getScore() && getSyncBlockRequestedSize() == peerListBean.getSyncBlockRequestedSize() && isSyncFlag() == peerListBean.isSyncFlag() && getSyncToFetchSize() == peerListBean.getSyncToFetchSize() && getSyncToFetchSizePeekNum() == peerListBean.getSyncToFetchSizePeekNum() && getUnFetchSynNum() == peerListBean.getUnFetchSynNum()) {
                    String headBlockWeBothHave = getHeadBlockWeBothHave();
                    String headBlockWeBothHave2 = peerListBean.getHeadBlockWeBothHave();
                    if (headBlockWeBothHave != null ? headBlockWeBothHave.equals(headBlockWeBothHave2) : headBlockWeBothHave2 == null) {
                        String host = getHost();
                        String host2 = peerListBean.getHost();
                        if (host != null ? host.equals(host2) : host2 == null) {
                            String lastSyncBlock = getLastSyncBlock();
                            String lastSyncBlock2 = peerListBean.getLastSyncBlock();
                            if (lastSyncBlock != null ? lastSyncBlock.equals(lastSyncBlock2) : lastSyncBlock2 == null) {
                                String localDisconnectReason = getLocalDisconnectReason();
                                String localDisconnectReason2 = peerListBean.getLocalDisconnectReason();
                                if (localDisconnectReason != null ? localDisconnectReason.equals(localDisconnectReason2) : localDisconnectReason2 == null) {
                                    String nodeId = getNodeId();
                                    String nodeId2 = peerListBean.getNodeId();
                                    if (nodeId != null ? nodeId.equals(nodeId2) : nodeId2 == null) {
                                        String remoteDisconnectReason = getRemoteDisconnectReason();
                                        String remoteDisconnectReason2 = peerListBean.getRemoteDisconnectReason();
                                        return remoteDisconnectReason != null ? remoteDisconnectReason.equals(remoteDisconnectReason2) : remoteDisconnectReason2 == null;
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
            int i = isActive() ? 79 : 97;
            long doubleToLongBits = Double.doubleToLongBits(getAvgLatency());
            int blockInPorcSize = ((((i + 59) * 59) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 59) + getBlockInPorcSize();
            long connectTime = getConnectTime();
            int disconnectTimes = (((((((blockInPorcSize * 59) + ((int) (connectTime ^ (connectTime >>> 32)))) * 59) + getDisconnectTimes()) * 59) + getHeadBlockTimeWeBothHave()) * 59) + getInFlow();
            long lastBlockUpdateTime = getLastBlockUpdateTime();
            int nodeCount = (((((((((((((((((((((((disconnectTimes * 59) + ((int) (lastBlockUpdateTime ^ (lastBlockUpdateTime >>> 32)))) * 59) + (isNeedSyncFromPeer() ? 79 : 97)) * 59) + (isNeedSyncFromUs() ? 79 : 97)) * 59) + getNodeCount()) * 59) + getPort()) * 59) + getRemainNum()) * 59) + getScore()) * 59) + getSyncBlockRequestedSize()) * 59) + (isSyncFlag() ? 79 : 97)) * 59) + getSyncToFetchSize()) * 59) + getSyncToFetchSizePeekNum()) * 59) + getUnFetchSynNum();
            String headBlockWeBothHave = getHeadBlockWeBothHave();
            int hashCode = (nodeCount * 59) + (headBlockWeBothHave == null ? 43 : headBlockWeBothHave.hashCode());
            String host = getHost();
            int hashCode2 = (hashCode * 59) + (host == null ? 43 : host.hashCode());
            String lastSyncBlock = getLastSyncBlock();
            int hashCode3 = (hashCode2 * 59) + (lastSyncBlock == null ? 43 : lastSyncBlock.hashCode());
            String localDisconnectReason = getLocalDisconnectReason();
            int hashCode4 = (hashCode3 * 59) + (localDisconnectReason == null ? 43 : localDisconnectReason.hashCode());
            String nodeId = getNodeId();
            int hashCode5 = (hashCode4 * 59) + (nodeId == null ? 43 : nodeId.hashCode());
            String remoteDisconnectReason = getRemoteDisconnectReason();
            return (hashCode5 * 59) + (remoteDisconnectReason != null ? remoteDisconnectReason.hashCode() : 43);
        }

        public String toString() {
            return "NodeInfoBean.PeerListBean(active=" + isActive() + ", avgLatency=" + getAvgLatency() + ", blockInPorcSize=" + getBlockInPorcSize() + ", connectTime=" + getConnectTime() + ", disconnectTimes=" + getDisconnectTimes() + ", headBlockTimeWeBothHave=" + getHeadBlockTimeWeBothHave() + ", headBlockWeBothHave=" + getHeadBlockWeBothHave() + ", host=" + getHost() + ", inFlow=" + getInFlow() + ", lastBlockUpdateTime=" + getLastBlockUpdateTime() + ", lastSyncBlock=" + getLastSyncBlock() + ", localDisconnectReason=" + getLocalDisconnectReason() + ", needSyncFromPeer=" + isNeedSyncFromPeer() + ", needSyncFromUs=" + isNeedSyncFromUs() + ", nodeCount=" + getNodeCount() + ", nodeId=" + getNodeId() + ", port=" + getPort() + ", remainNum=" + getRemainNum() + ", remoteDisconnectReason=" + getRemoteDisconnectReason() + ", score=" + getScore() + ", syncBlockRequestedSize=" + getSyncBlockRequestedSize() + ", syncFlag=" + isSyncFlag() + ", syncToFetchSize=" + getSyncToFetchSize() + ", syncToFetchSizePeekNum=" + getSyncToFetchSizePeekNum() + ", unFetchSynNum=" + getUnFetchSynNum() + ")";
        }
    }
}
