package com.tron.wallet.business.security.components;

import com.tron.wallet.business.security.environment.bean.ItemData;
import com.tronlinkpro.wallet.R;
public class EnvironmentResource {
    public ItemData clipboardResource;
    public ItemData netResource;
    public ItemData officialResource;
    public ItemData rootResource;
    public ItemData screenResource;
    public ItemData simulatorResource;

    public EnvironmentResource() {
        ItemData itemData = new ItemData();
        this.rootResource = itemData;
        itemData.setTitle(R.string.security_environment_root_detection);
        this.rootResource.setSafeDescribe(R.string.security_environment_root_no_risk);
        this.rootResource.setRiskDescribe(R.string.security_environment_root_risk);
        ItemData itemData2 = new ItemData();
        this.simulatorResource = itemData2;
        itemData2.setTitle(R.string.security_environment_simulator_detection);
        this.simulatorResource.setSafeDescribe(R.string.security_environment_simulator_noRisk);
        this.simulatorResource.setWaringDescribe(R.string.security_environment_simulator_risk);
        ItemData itemData3 = new ItemData();
        this.netResource = itemData3;
        itemData3.setTitle(R.string.security_environment_network_detection);
        this.netResource.setSafeDescribe(R.string.security_environment_network_noRisk);
        this.netResource.setWaringDescribe(R.string.security_environment_network_risk);
        ItemData itemData4 = new ItemData();
        this.clipboardResource = itemData4;
        itemData4.setTitle(R.string.security_environment_clipboard_detection);
        this.clipboardResource.setSafeDescribe(R.string.security_environment_clipboard_noRisk);
        this.clipboardResource.setWaringDescribe(R.string.security_environment_clipboard_risk);
        ItemData itemData5 = new ItemData();
        this.screenResource = itemData5;
        itemData5.setTitle(R.string.security_environment_screenRecord_detection);
        this.screenResource.setSafeDescribe(R.string.security_environment_screenRecord_noRisk);
        this.screenResource.setRiskDescribe(R.string.security_environment_screenRecord_risk);
        ItemData itemData6 = new ItemData();
        this.officialResource = itemData6;
        itemData6.setTitle(R.string.security_environment_package_detection);
        this.officialResource.setRiskDescribe(R.string.security_environment_package_risk);
    }
}
