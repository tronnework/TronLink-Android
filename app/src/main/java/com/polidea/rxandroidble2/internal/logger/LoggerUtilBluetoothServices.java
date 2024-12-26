package com.polidea.rxandroidble2.internal.logger;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble2.RxBleDeviceServices;
import com.polidea.rxandroidble2.internal.RxBleLog;
import com.polidea.rxandroidble2.internal.util.CharacteristicPropertiesParser;
import com.polidea.rxandroidble2.utils.StandardUUIDsParser;
import org.apache.commons.lang3.StringUtils;
public class LoggerUtilBluetoothServices {
    private final CharacteristicPropertiesParser characteristicPropertiesParser;

    @Inject
    public LoggerUtilBluetoothServices(CharacteristicPropertiesParser characteristicPropertiesParser) {
        this.characteristicPropertiesParser = characteristicPropertiesParser;
    }

    public void log(RxBleDeviceServices rxBleDeviceServices, BluetoothDevice bluetoothDevice) {
        if (RxBleLog.isAtLeast(2)) {
            RxBleLog.v("Preparing services description", new Object[0]);
            RxBleLog.v(prepareServicesDescription(rxBleDeviceServices, bluetoothDevice), new Object[0]);
        }
    }

    private String prepareServicesDescription(RxBleDeviceServices rxBleDeviceServices, BluetoothDevice bluetoothDevice) {
        StringBuilder sb = new StringBuilder();
        appendDeviceHeader(bluetoothDevice, sb);
        for (BluetoothGattService bluetoothGattService : rxBleDeviceServices.getBluetoothGattServices()) {
            sb.append('\n');
            appendServiceDescription(sb, bluetoothGattService);
        }
        sb.append("\n--------------- ====== Finished peripheral content ====== ---------------");
        return sb.toString();
    }

    private void appendServiceDescription(StringBuilder sb, BluetoothGattService bluetoothGattService) {
        appendServiceHeader(sb, bluetoothGattService);
        sb.append("-> Characteristics:");
        for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGattService.getCharacteristics()) {
            appendCharacteristicNameHeader(sb, bluetoothGattCharacteristic);
            appendCharacteristicProperties(sb, bluetoothGattCharacteristic);
            appendDescriptors(sb, bluetoothGattCharacteristic);
        }
    }

    private static void appendDescriptors(StringBuilder sb, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getDescriptors().isEmpty()) {
            return;
        }
        appendDescriptorsHeader(sb);
        for (BluetoothGattDescriptor bluetoothGattDescriptor : bluetoothGattCharacteristic.getDescriptors()) {
            appendDescriptorNameHeader(sb, bluetoothGattDescriptor);
        }
    }

    private static void appendDescriptorsHeader(StringBuilder sb) {
        sb.append('\n');
        sb.append('\t');
        sb.append("  ");
        sb.append("-> Descriptors: ");
    }

    private static void appendCharacteristicNameHeader(StringBuilder sb, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        sb.append('\n');
        sb.append('\t');
        sb.append("* ");
        sb.append(createCharacteristicName(bluetoothGattCharacteristic));
        sb.append(" (");
        sb.append(LoggerUtil.getUuidToLog(bluetoothGattCharacteristic.getUuid()));
        sb.append(")");
    }

    private static void appendDescriptorNameHeader(StringBuilder sb, BluetoothGattDescriptor bluetoothGattDescriptor) {
        sb.append('\n');
        sb.append('\t');
        sb.append('\t');
        sb.append("* ");
        sb.append(createDescriptorName(bluetoothGattDescriptor));
        sb.append(" (");
        sb.append(LoggerUtil.getUuidToLog(bluetoothGattDescriptor.getUuid()));
        sb.append(")");
    }

    private static String createDescriptorName(BluetoothGattDescriptor bluetoothGattDescriptor) {
        String descriptorName = StandardUUIDsParser.getDescriptorName(bluetoothGattDescriptor.getUuid());
        return descriptorName == null ? "Unknown descriptor" : descriptorName;
    }

    private void appendCharacteristicProperties(StringBuilder sb, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        sb.append('\n');
        sb.append('\t');
        sb.append("  ");
        sb.append("Properties: ");
        sb.append(this.characteristicPropertiesParser.propertiesIntToString(bluetoothGattCharacteristic.getProperties()));
    }

    private static String createCharacteristicName(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        String characteristicName = StandardUUIDsParser.getCharacteristicName(bluetoothGattCharacteristic.getUuid());
        return characteristicName == null ? "Unknown characteristic" : characteristicName;
    }

    private static void appendDeviceHeader(BluetoothDevice bluetoothDevice, StringBuilder sb) {
        sb.append("--------------- ====== Printing peripheral content ====== ---------------\n");
        sb.append(LoggerUtil.commonMacMessage(bluetoothDevice.getAddress()));
        sb.append('\n');
        sb.append("PERIPHERAL NAME: ");
        sb.append(bluetoothDevice.getName());
        sb.append('\n');
        sb.append("-------------------------------------------------------------------------");
    }

    private static void appendServiceHeader(StringBuilder sb, BluetoothGattService bluetoothGattService) {
        sb.append(StringUtils.LF);
        sb.append(createServiceType(bluetoothGattService));
        sb.append(" - ");
        sb.append(createServiceName(bluetoothGattService));
        sb.append(" (");
        sb.append(LoggerUtil.getUuidToLog(bluetoothGattService.getUuid()));
        sb.append(")\n");
        sb.append("Instance ID: ");
        sb.append(bluetoothGattService.getInstanceId());
        sb.append('\n');
    }

    private static String createServiceName(BluetoothGattService bluetoothGattService) {
        String serviceName = StandardUUIDsParser.getServiceName(bluetoothGattService.getUuid());
        return serviceName == null ? "Unknown service" : serviceName;
    }

    private static String createServiceType(BluetoothGattService bluetoothGattService) {
        return bluetoothGattService.getType() == 0 ? "Primary Service" : "Secondary Service";
    }
}
