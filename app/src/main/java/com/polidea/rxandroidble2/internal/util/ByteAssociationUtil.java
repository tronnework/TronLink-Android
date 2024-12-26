package com.polidea.rxandroidble2.internal.util;

import android.bluetooth.BluetoothGattDescriptor;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import java.util.UUID;
public class ByteAssociationUtil {
    private ByteAssociationUtil() {
    }

    public static Predicate<? super ByteAssociation<UUID>> characteristicUUIDPredicate(final UUID uuid) {
        return new Predicate<ByteAssociation<UUID>>() {
            @Override
            public boolean test(ByteAssociation<UUID> byteAssociation) {
                return byteAssociation.first.equals(uuid);
            }
        };
    }

    public static Function<ByteAssociation<?>, byte[]> getBytesFromAssociation() {
        return new Function<ByteAssociation<?>, byte[]>() {
            @Override
            public byte[] apply(ByteAssociation<?> byteAssociation) {
                return byteAssociation.second;
            }
        };
    }

    public static Predicate<? super ByteAssociation<BluetoothGattDescriptor>> descriptorPredicate(final BluetoothGattDescriptor bluetoothGattDescriptor) {
        return new Predicate<ByteAssociation<BluetoothGattDescriptor>>() {
            @Override
            public boolean test(ByteAssociation<BluetoothGattDescriptor> byteAssociation) {
                return byteAssociation.first.equals(bluetoothGattDescriptor);
            }
        };
    }
}
