package com.tron.wallet.ledger.bleclient;

import com.google.common.primitives.SignedBytes;
import java.util.Arrays;
public class LedgerDeviceModel {
    private int blockSize;
    private BluetoothSpec[] bluetoothSpec;
    private String id;
    private int legacyUsbProductId;
    private int memorySize;
    private byte productIdMM;
    private String productName;
    private boolean usbOnly;
    public static LedgerDeviceModel nanoXDeviceModel = buildNanoXDeviceModel();
    private static final String serviceUuid1 = "d973f2e0-b19e-11e2-9e96-0800200c9a66";
    private static final String serviceUuid2 = "13d63400-2c97-0004-0000-4c6564676572";
    public static String[] bluetoothServices = {serviceUuid1, serviceUuid2};

    public static String[] getBluetoothServiceUuids() {
        return bluetoothServices;
    }

    public int getBlockSize() {
        return this.blockSize;
    }

    public int getBlockSize(String str) {
        return 4096;
    }

    public BluetoothSpec[] getBluetoothSpec() {
        return this.bluetoothSpec;
    }

    public String getId() {
        return this.id;
    }

    public int getLegacyUsbProductId() {
        return this.legacyUsbProductId;
    }

    public int getMemorySize() {
        return this.memorySize;
    }

    public byte getProductIdMM() {
        return this.productIdMM;
    }

    public String getProductName() {
        return this.productName;
    }

    public boolean isUsbOnly() {
        return this.usbOnly;
    }

    public void setBlockSize(int i) {
        this.blockSize = i;
    }

    public void setBluetoothSpec(BluetoothSpec[] bluetoothSpecArr) {
        this.bluetoothSpec = bluetoothSpecArr;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setLegacyUsbProductId(int i) {
        this.legacyUsbProductId = i;
    }

    public void setMemorySize(int i) {
        this.memorySize = i;
    }

    public void setProductIdMM(byte b) {
        this.productIdMM = b;
    }

    public void setProductName(String str) {
        this.productName = str;
    }

    public void setUsbOnly(boolean z) {
        this.usbOnly = z;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof LedgerDeviceModel;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof LedgerDeviceModel) {
            LedgerDeviceModel ledgerDeviceModel = (LedgerDeviceModel) obj;
            if (ledgerDeviceModel.canEqual(this) && getProductIdMM() == ledgerDeviceModel.getProductIdMM() && getLegacyUsbProductId() == ledgerDeviceModel.getLegacyUsbProductId() && isUsbOnly() == ledgerDeviceModel.isUsbOnly() && getMemorySize() == ledgerDeviceModel.getMemorySize() && getBlockSize() == ledgerDeviceModel.getBlockSize()) {
                String id = getId();
                String id2 = ledgerDeviceModel.getId();
                if (id != null ? id.equals(id2) : id2 == null) {
                    String productName = getProductName();
                    String productName2 = ledgerDeviceModel.getProductName();
                    if (productName != null ? productName.equals(productName2) : productName2 == null) {
                        return Arrays.deepEquals(getBluetoothSpec(), ledgerDeviceModel.getBluetoothSpec());
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
        int productIdMM = ((((((((getProductIdMM() + 59) * 59) + getLegacyUsbProductId()) * 59) + (isUsbOnly() ? 79 : 97)) * 59) + getMemorySize()) * 59) + getBlockSize();
        String id = getId();
        int hashCode = (productIdMM * 59) + (id == null ? 43 : id.hashCode());
        String productName = getProductName();
        return (((hashCode * 59) + (productName != null ? productName.hashCode() : 43)) * 59) + Arrays.deepHashCode(getBluetoothSpec());
    }

    public String toString() {
        return "LedgerDeviceModel(id=" + getId() + ", productName=" + getProductName() + ", productIdMM=" + ((int) getProductIdMM()) + ", legacyUsbProductId=" + getLegacyUsbProductId() + ", usbOnly=" + isUsbOnly() + ", memorySize=" + getMemorySize() + ", blockSize=" + getBlockSize() + ", bluetoothSpec=" + Arrays.deepToString(getBluetoothSpec()) + ")";
    }

    public static LedgerDeviceModel buildNanoXDeviceModel() {
        LedgerDeviceModel ledgerDeviceModel = new LedgerDeviceModel();
        ledgerDeviceModel.setId("nanoX");
        ledgerDeviceModel.setProductName("Ledger Nano X");
        ledgerDeviceModel.setProductIdMM(SignedBytes.MAX_POWER_OF_TWO);
        ledgerDeviceModel.setLegacyUsbProductId(4);
        ledgerDeviceModel.setUsbOnly(false);
        ledgerDeviceModel.setMemorySize(2097152);
        ledgerDeviceModel.setBlockSize(4096);
        ledgerDeviceModel.setBluetoothSpec(new BluetoothSpec[]{new BluetoothSpec(serviceUuid1, "d973f2e1-b19e-11e2-9e96-0800200c9a66", "d973f2e2-b19e-11e2-9e96-0800200c9a66"), new BluetoothSpec(serviceUuid2, "13d63400-2c97-0004-0001-4c6564676572", "13d63400-2c97-0004-0002-4c6564676572")});
        return ledgerDeviceModel;
    }

    public static BluetoothSpec getBluetoothSpecForServiceUuid(String str) {
        BluetoothSpec[] bluetoothSpec;
        for (BluetoothSpec bluetoothSpec2 : nanoXDeviceModel.getBluetoothSpec()) {
            if (bluetoothSpec2.getServiceUuid().equals(str)) {
                return bluetoothSpec2;
            }
        }
        return null;
    }

    public static class BluetoothSpec {
        private String notifyUuid;
        private String serviceUuid;
        private String writeUuid;

        public String getNotifyUuid() {
            return this.notifyUuid;
        }

        public String getServiceUuid() {
            return this.serviceUuid;
        }

        public String getWriteUuid() {
            return this.writeUuid;
        }

        public void setNotifyUuid(String str) {
            this.notifyUuid = str;
        }

        public void setServiceUuid(String str) {
            this.serviceUuid = str;
        }

        public void setWriteUuid(String str) {
            this.writeUuid = str;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof BluetoothSpec;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof BluetoothSpec) {
                BluetoothSpec bluetoothSpec = (BluetoothSpec) obj;
                if (bluetoothSpec.canEqual(this)) {
                    String serviceUuid = getServiceUuid();
                    String serviceUuid2 = bluetoothSpec.getServiceUuid();
                    if (serviceUuid != null ? serviceUuid.equals(serviceUuid2) : serviceUuid2 == null) {
                        String notifyUuid = getNotifyUuid();
                        String notifyUuid2 = bluetoothSpec.getNotifyUuid();
                        if (notifyUuid != null ? notifyUuid.equals(notifyUuid2) : notifyUuid2 == null) {
                            String writeUuid = getWriteUuid();
                            String writeUuid2 = bluetoothSpec.getWriteUuid();
                            return writeUuid != null ? writeUuid.equals(writeUuid2) : writeUuid2 == null;
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
            String serviceUuid = getServiceUuid();
            int hashCode = serviceUuid == null ? 43 : serviceUuid.hashCode();
            String notifyUuid = getNotifyUuid();
            int hashCode2 = ((hashCode + 59) * 59) + (notifyUuid == null ? 43 : notifyUuid.hashCode());
            String writeUuid = getWriteUuid();
            return (hashCode2 * 59) + (writeUuid != null ? writeUuid.hashCode() : 43);
        }

        public String toString() {
            return "LedgerDeviceModel.BluetoothSpec(serviceUuid=" + getServiceUuid() + ", notifyUuid=" + getNotifyUuid() + ", writeUuid=" + getWriteUuid() + ")";
        }

        public BluetoothSpec(String str, String str2, String str3) {
            this.serviceUuid = str;
            this.notifyUuid = str2;
            this.writeUuid = str3;
        }
    }
}
