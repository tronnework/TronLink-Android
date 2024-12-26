package com.tron.wallet.business.ledger.manage;

import com.tron.wallet.business.walletmanager.importwallet.base.repo.entity.BleRepoDevice;
public interface EquipmentRemoveListener {
    void remove(int i, BleRepoDevice bleRepoDevice);
}
