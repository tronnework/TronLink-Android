package com.polidea.rxandroidble2.internal.util;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.content.Context;
import android.os.Build;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble2.internal.RxBleLog;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
public class BleConnectionCompat {
    private final Context context;

    @Inject
    public BleConnectionCompat(Context context) {
        this.context = context;
    }

    public BluetoothGatt connectGatt(BluetoothDevice bluetoothDevice, boolean z, BluetoothGattCallback bluetoothGattCallback) {
        if (bluetoothDevice == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 24 || !z) {
            return connectGattCompat(bluetoothGattCallback, bluetoothDevice, z);
        }
        try {
            RxBleLog.v("Trying to connectGatt using reflection.", new Object[0]);
            Object iBluetoothGatt = getIBluetoothGatt(getIBluetoothManager());
            if (iBluetoothGatt == null) {
                RxBleLog.w("Couldn't get iBluetoothGatt object", new Object[0]);
                return connectGattCompat(bluetoothGattCallback, bluetoothDevice, true);
            }
            BluetoothGatt createBluetoothGatt = createBluetoothGatt(iBluetoothGatt, bluetoothDevice);
            if (createBluetoothGatt == null) {
                RxBleLog.w("Couldn't create BluetoothGatt object", new Object[0]);
                return connectGattCompat(bluetoothGattCallback, bluetoothDevice, true);
            }
            if (!connectUsingReflection(createBluetoothGatt, bluetoothGattCallback, true)) {
                RxBleLog.w("Connection using reflection failed, closing gatt", new Object[0]);
                createBluetoothGatt.close();
            }
            return createBluetoothGatt;
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException e) {
            RxBleLog.w(e, "Error while trying to connect via reflection", new Object[0]);
            return connectGattCompat(bluetoothGattCallback, bluetoothDevice, true);
        }
    }

    private BluetoothGatt connectGattCompat(BluetoothGattCallback bluetoothGattCallback, BluetoothDevice bluetoothDevice, boolean z) {
        BluetoothGatt connectGatt;
        RxBleLog.v("Connecting without reflection", new Object[0]);
        if (Build.VERSION.SDK_INT >= 23) {
            connectGatt = bluetoothDevice.connectGatt(this.context, z, bluetoothGattCallback, 2);
            return connectGatt;
        }
        return bluetoothDevice.connectGatt(this.context, z, bluetoothGattCallback);
    }

    private static boolean connectUsingReflection(BluetoothGatt bluetoothGatt, BluetoothGattCallback bluetoothGattCallback, boolean z) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        RxBleLog.v("Connecting using reflection", new Object[0]);
        setAutoConnectValue(bluetoothGatt, z);
        Method declaredMethod = bluetoothGatt.getClass().getDeclaredMethod("connect", Boolean.class, BluetoothGattCallback.class);
        declaredMethod.setAccessible(true);
        return ((Boolean) declaredMethod.invoke(bluetoothGatt, true, bluetoothGattCallback)).booleanValue();
    }

    private BluetoothGatt createBluetoothGatt(Object obj, BluetoothDevice bluetoothDevice) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<?> constructor = BluetoothGatt.class.getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        RxBleLog.v("Found constructor with args count = " + constructor.getParameterTypes().length, new Object[0]);
        return constructor.getParameterTypes().length == 4 ? (BluetoothGatt) constructor.newInstance(this.context, obj, bluetoothDevice, 2) : (BluetoothGatt) constructor.newInstance(this.context, obj, bluetoothDevice);
    }

    private static Object getIBluetoothGatt(Object obj) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (obj == null) {
            return null;
        }
        return getMethodFromClass(obj.getClass(), "getBluetoothGatt").invoke(obj, new Object[0]);
    }

    private static Object getIBluetoothManager() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null) {
            return null;
        }
        return getMethodFromClass(defaultAdapter.getClass(), "getBluetoothManager").invoke(defaultAdapter, new Object[0]);
    }

    private static Method getMethodFromClass(Class<?> cls, String str) throws NoSuchMethodException {
        Method declaredMethod = cls.getDeclaredMethod(str, new Class[0]);
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }

    private static void setAutoConnectValue(BluetoothGatt bluetoothGatt, boolean z) throws NoSuchFieldException, IllegalAccessException {
        Field declaredField = bluetoothGatt.getClass().getDeclaredField("mAutoConnect");
        declaredField.setAccessible(true);
        declaredField.setBoolean(bluetoothGatt, z);
    }
}
