package com.tron.wallet.business.mutil.bean;

import java.util.List;
public class MultiAddressOutput {
    private static final int CancelAllUnfreezeV2Contract = 59;
    private static final int DelegateResourceContract = 57;
    private static final int FreezeBalanceContractType = 11;
    private static final int FreezeBalanceV2Contract = 54;
    private static final int TransferAssetContractType = 2;
    private static final int TransferContractType = 1;
    private static final int TriggerSmartContractType = 31;
    private static final int UnDelegateResourceContract = 58;
    private static final int UnfreezeBalanceContractType = 12;
    private static final int UnfreezeBalanceV2Contract = 55;
    private static final int VoteWitnessContractType = 4;
    private static final int WithdrawBalanceContractType = 13;
    private static final int WithdrawExpireUnfreezeContract = 56;
    private List<PermissionOutput> activePermissions;
    private boolean hasCancelAllUnfreezePermission;
    private boolean hasDelegateResourcePermission;
    private boolean hasFreezeBalanceV2Permission;
    private boolean hasStakePermission;
    private boolean hasTransferAssetPermission;
    private boolean hasTransferTRXPermission;
    private boolean hasTriggerPermission;
    private boolean hasUnDelegateResourcePermission;
    private boolean hasUnStakePermission;
    private boolean hasUnfreezeBalanceV2Permission;
    private boolean hasVoteWitnessPermission;
    private boolean hasWithdrawBalancePermission;
    private boolean hasWithdrawExpireUnfreezePermission;
    private String ownerAddress;
    private PermissionOutput ownerPermission;

    public List<PermissionOutput> getActivePermissions() {
        return this.activePermissions;
    }

    public String getOwnerAddress() {
        return this.ownerAddress;
    }

    public PermissionOutput getOwnerPermission() {
        return this.ownerPermission;
    }

    public boolean isHasCancelAllUnfreezePermission() {
        return this.hasCancelAllUnfreezePermission;
    }

    public boolean isHasDelegateResourcePermission() {
        return this.hasDelegateResourcePermission;
    }

    public boolean isHasFreezeBalanceV2Permission() {
        return this.hasFreezeBalanceV2Permission;
    }

    public boolean isHasStakePermission() {
        return this.hasStakePermission;
    }

    public boolean isHasTransferAssetPermission() {
        return this.hasTransferAssetPermission;
    }

    public boolean isHasTransferTRXPermission() {
        return this.hasTransferTRXPermission;
    }

    public boolean isHasTriggerPermission() {
        return this.hasTriggerPermission;
    }

    public boolean isHasUnDelegateResourcePermission() {
        return this.hasUnDelegateResourcePermission;
    }

    public boolean isHasUnStakePermission() {
        return this.hasUnStakePermission;
    }

    public boolean isHasUnfreezeBalanceV2Permission() {
        return this.hasUnfreezeBalanceV2Permission;
    }

    public boolean isHasVoteWitnessPermission() {
        return this.hasVoteWitnessPermission;
    }

    public boolean isHasWithdrawBalancePermission() {
        return this.hasWithdrawBalancePermission;
    }

    public boolean isHasWithdrawExpireUnfreezePermission() {
        return this.hasWithdrawExpireUnfreezePermission;
    }

    public void setActivePermissions(List<PermissionOutput> list) {
        this.activePermissions = list;
    }

    public void setHasCancelAllUnfreezePermission(boolean z) {
        this.hasCancelAllUnfreezePermission = z;
    }

    public void setHasDelegateResourcePermission(boolean z) {
        this.hasDelegateResourcePermission = z;
    }

    public void setHasFreezeBalanceV2Permission(boolean z) {
        this.hasFreezeBalanceV2Permission = z;
    }

    public void setHasStakePermission(boolean z) {
        this.hasStakePermission = z;
    }

    public void setHasTransferAssetPermission(boolean z) {
        this.hasTransferAssetPermission = z;
    }

    public void setHasTransferTRXPermission(boolean z) {
        this.hasTransferTRXPermission = z;
    }

    public void setHasTriggerPermission(boolean z) {
        this.hasTriggerPermission = z;
    }

    public void setHasUnDelegateResourcePermission(boolean z) {
        this.hasUnDelegateResourcePermission = z;
    }

    public void setHasUnStakePermission(boolean z) {
        this.hasUnStakePermission = z;
    }

    public void setHasUnfreezeBalanceV2Permission(boolean z) {
        this.hasUnfreezeBalanceV2Permission = z;
    }

    public void setHasVoteWitnessPermission(boolean z) {
        this.hasVoteWitnessPermission = z;
    }

    public void setHasWithdrawBalancePermission(boolean z) {
        this.hasWithdrawBalancePermission = z;
    }

    public void setHasWithdrawExpireUnfreezePermission(boolean z) {
        this.hasWithdrawExpireUnfreezePermission = z;
    }

    public void setOwnerAddress(String str) {
        this.ownerAddress = str;
    }

    public void setOwnerPermission(PermissionOutput permissionOutput) {
        this.ownerPermission = permissionOutput;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof MultiAddressOutput;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MultiAddressOutput) {
            MultiAddressOutput multiAddressOutput = (MultiAddressOutput) obj;
            if (multiAddressOutput.canEqual(this) && isHasTransferTRXPermission() == multiAddressOutput.isHasTransferTRXPermission() && isHasTransferAssetPermission() == multiAddressOutput.isHasTransferAssetPermission() && isHasTriggerPermission() == multiAddressOutput.isHasTriggerPermission() && isHasStakePermission() == multiAddressOutput.isHasStakePermission() && isHasUnStakePermission() == multiAddressOutput.isHasUnStakePermission() && isHasVoteWitnessPermission() == multiAddressOutput.isHasVoteWitnessPermission() && isHasWithdrawBalancePermission() == multiAddressOutput.isHasWithdrawBalancePermission() && isHasFreezeBalanceV2Permission() == multiAddressOutput.isHasFreezeBalanceV2Permission() && isHasUnfreezeBalanceV2Permission() == multiAddressOutput.isHasUnfreezeBalanceV2Permission() && isHasWithdrawExpireUnfreezePermission() == multiAddressOutput.isHasWithdrawExpireUnfreezePermission() && isHasDelegateResourcePermission() == multiAddressOutput.isHasDelegateResourcePermission() && isHasUnDelegateResourcePermission() == multiAddressOutput.isHasUnDelegateResourcePermission() && isHasCancelAllUnfreezePermission() == multiAddressOutput.isHasCancelAllUnfreezePermission()) {
                String ownerAddress = getOwnerAddress();
                String ownerAddress2 = multiAddressOutput.getOwnerAddress();
                if (ownerAddress != null ? ownerAddress.equals(ownerAddress2) : ownerAddress2 == null) {
                    PermissionOutput ownerPermission = getOwnerPermission();
                    PermissionOutput ownerPermission2 = multiAddressOutput.getOwnerPermission();
                    if (ownerPermission != null ? ownerPermission.equals(ownerPermission2) : ownerPermission2 == null) {
                        List<PermissionOutput> activePermissions = getActivePermissions();
                        List<PermissionOutput> activePermissions2 = multiAddressOutput.getActivePermissions();
                        return activePermissions != null ? activePermissions.equals(activePermissions2) : activePermissions2 == null;
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
        int i = (((((((((((((((((((((((((isHasTransferTRXPermission() ? 79 : 97) + 59) * 59) + (isHasTransferAssetPermission() ? 79 : 97)) * 59) + (isHasTriggerPermission() ? 79 : 97)) * 59) + (isHasStakePermission() ? 79 : 97)) * 59) + (isHasUnStakePermission() ? 79 : 97)) * 59) + (isHasVoteWitnessPermission() ? 79 : 97)) * 59) + (isHasWithdrawBalancePermission() ? 79 : 97)) * 59) + (isHasFreezeBalanceV2Permission() ? 79 : 97)) * 59) + (isHasUnfreezeBalanceV2Permission() ? 79 : 97)) * 59) + (isHasWithdrawExpireUnfreezePermission() ? 79 : 97)) * 59) + (isHasDelegateResourcePermission() ? 79 : 97)) * 59) + (isHasUnDelegateResourcePermission() ? 79 : 97)) * 59) + (isHasCancelAllUnfreezePermission() ? 79 : 97);
        String ownerAddress = getOwnerAddress();
        int hashCode = (i * 59) + (ownerAddress == null ? 43 : ownerAddress.hashCode());
        PermissionOutput ownerPermission = getOwnerPermission();
        int hashCode2 = (hashCode * 59) + (ownerPermission == null ? 43 : ownerPermission.hashCode());
        List<PermissionOutput> activePermissions = getActivePermissions();
        return (hashCode2 * 59) + (activePermissions != null ? activePermissions.hashCode() : 43);
    }

    public String toString() {
        return "MultiAddressOutput(ownerAddress=" + getOwnerAddress() + ", ownerPermission=" + getOwnerPermission() + ", activePermissions=" + getActivePermissions() + ", hasTransferTRXPermission=" + isHasTransferTRXPermission() + ", hasTransferAssetPermission=" + isHasTransferAssetPermission() + ", hasTriggerPermission=" + isHasTriggerPermission() + ", hasStakePermission=" + isHasStakePermission() + ", hasUnStakePermission=" + isHasUnStakePermission() + ", hasVoteWitnessPermission=" + isHasVoteWitnessPermission() + ", hasWithdrawBalancePermission=" + isHasWithdrawBalancePermission() + ", hasFreezeBalanceV2Permission=" + isHasFreezeBalanceV2Permission() + ", hasUnfreezeBalanceV2Permission=" + isHasUnfreezeBalanceV2Permission() + ", hasWithdrawExpireUnfreezePermission=" + isHasWithdrawExpireUnfreezePermission() + ", hasDelegateResourcePermission=" + isHasDelegateResourcePermission() + ", hasUnDelegateResourcePermission=" + isHasUnDelegateResourcePermission() + ", hasCancelAllUnfreezePermission=" + isHasCancelAllUnfreezePermission() + ")";
    }

    public boolean hasTransferTRXPermission() {
        return checkPermission(1);
    }

    public boolean hasTransferAssetPermission() {
        return checkPermission(2);
    }

    public boolean hasTriggerPermission() {
        return checkPermission(31);
    }

    public boolean hasStakePermission() {
        return checkPermission(11);
    }

    public boolean hasUnStakePermission() {
        return checkPermission(12);
    }

    public boolean hasVoteWitnessPermission() {
        return checkPermission(4);
    }

    public boolean hasWithdrawBalancePermission() {
        return checkPermission(13);
    }

    public boolean hasFreezeBalanceV2Permission() {
        return checkPermission(54);
    }

    public boolean hasUnfreezeBalanceV2Permission() {
        return checkPermission(55);
    }

    public boolean hasWithdrawExpireUnfreezePermission() {
        return checkPermission(56);
    }

    public boolean hasDelegateResourcePermission() {
        return checkPermission(57);
    }

    public boolean hasUnDelegateResourcePermission() {
        return checkPermission(58);
    }

    public boolean hasCancelAllUnfreezePermission() {
        return checkPermission(59);
    }

    private boolean checkPermission(int r9) {
        


return true;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.mutil.bean.MultiAddressOutput.checkPermission(int):boolean");
    }

    public static class PermissionOutput {
        private boolean has;
        private String operations;
        private int threshold;
        private int weight;

        public String getOperations() {
            return this.operations;
        }

        public int getThreshold() {
            return this.threshold;
        }

        public int getWeight() {
            return this.weight;
        }

        public boolean isHas() {
            return this.has;
        }

        public void setHas(boolean z) {
            this.has = z;
        }

        public void setOperations(String str) {
            this.operations = str;
        }

        public void setThreshold(int i) {
            this.threshold = i;
        }

        public void setWeight(int i) {
            this.weight = i;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof PermissionOutput;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof PermissionOutput) {
                PermissionOutput permissionOutput = (PermissionOutput) obj;
                if (permissionOutput.canEqual(this) && isHas() == permissionOutput.isHas() && getThreshold() == permissionOutput.getThreshold() && getWeight() == permissionOutput.getWeight()) {
                    String operations = getOperations();
                    String operations2 = permissionOutput.getOperations();
                    return operations != null ? operations.equals(operations2) : operations2 == null;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            int threshold = (((((isHas() ? 79 : 97) + 59) * 59) + getThreshold()) * 59) + getWeight();
            String operations = getOperations();
            return (threshold * 59) + (operations == null ? 43 : operations.hashCode());
        }

        public String toString() {
            return "MultiAddressOutput.PermissionOutput(operations=" + getOperations() + ", has=" + isHas() + ", threshold=" + getThreshold() + ", weight=" + getWeight() + ")";
        }
    }
}
