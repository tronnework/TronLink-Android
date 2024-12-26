package com.tron.wallet.business.ledger.manage;

import com.tron.wallet.business.walletmanager.importwallet.base.repo.entity.BleRepoDevice;
public interface EquipmentFailListener {
    void retry(int i, BleRepoDevice bleRepoDevice);
}
