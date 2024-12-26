package com.tron.wallet.common.utils;

import com.tron.tron_base.frame.utils.LogUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
public class CloneUtils {
    public static <T extends Serializable> T clone(T t) {
        Exception e;
        T t2;
        ObjectInputStream objectInputStream;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(t);
            objectOutputStream.close();
            objectInputStream = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
            t2 = (T) objectInputStream.readObject();
        } catch (Exception e2) {
            e = e2;
            t2 = null;
        }
        try {
            objectInputStream.close();
        } catch (Exception e3) {
            e = e3;
            LogUtils.e(e);
            return t2;
        }
        return t2;
    }
}
