package com.tron.wallet.business.security.components;

import com.tron.wallet.business.security.environment.bean.ItemData;
import com.tronlinkpro.wallet.R;
public class HomeResource {
    public ItemData approveResource;
    public ItemData environmentResource;
    public ItemData tokenResource;

    public HomeResource() {
        ItemData itemData = new ItemData();
        this.environmentResource = itemData;
        itemData.setTitle(R.string.security_home_environment_checked);
        this.environmentResource.setErrorTitle(R.string.security_home_environment_detect_failed);
        this.environmentResource.setSafeDescribe(R.string.security_home_environment_checked_no_risk);
        this.environmentResource.setWaringDescribe(R.string.security_home_environment_checked_moderate_risk);
        this.environmentResource.setRiskDescribe(R.string.security_home_environment_checked_high_risk);
        ItemData itemData2 = new ItemData();
        this.tokenResource = itemData2;
        itemData2.setTitle(R.string.security_home_all_token_checked);
        this.tokenResource.setErrorTitle(R.string.security_home_token_detect_failed);
        this.tokenResource.setSafeDescribe(R.string.security_home_all_token_checked_no_risk);
        this.tokenResource.setWaringDescribe(R.string.security_home_all_token_checked_moderate_risk);
        this.tokenResource.setRiskDescribe(R.string.security_home_all_token_checked_high_risk);
        ItemData itemData3 = new ItemData();
        this.approveResource = itemData3;
        itemData3.setTitle(R.string.security_home_approval_checked);
        this.approveResource.setErrorTitle(R.string.security_home_approval_detect_failed);
        this.approveResource.setSafeDescribe(R.string.security_home_approval_checked_no_risk);
        this.approveResource.setRiskDescribe(R.string.security_home_approval_checked_high_risk);
    }
}
