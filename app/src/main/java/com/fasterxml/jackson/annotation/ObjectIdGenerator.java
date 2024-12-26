package com.fasterxml.jackson.annotation;

import java.io.Serializable;
public abstract class ObjectIdGenerator<T> implements Serializable {
    public abstract boolean canUseFor(ObjectIdGenerator<?> objectIdGenerator);

    public abstract ObjectIdGenerator<T> forScope(Class<?> cls);

    public abstract T generateId(Object obj);

    public abstract Class<?> getScope();

    public boolean isValidReferencePropertyName(String str, Object obj) {
        return false;
    }

    public abstract IdKey key(Object obj);

    public boolean maySerializeAsObject() {
        return false;
    }

    public abstract ObjectIdGenerator<T> newForSerialization(Object obj);

    public static final class IdKey implements Serializable {
        private static final long serialVersionUID = 1;
        private final int hashCode;
        public final Object key;
        public final Class<?> scope;
        public final Class<?> type;

        public int hashCode() {
            return this.hashCode;
        }

        public IdKey(Class<?> cls, Class<?> cls2, Object obj) {
            if (obj == null) {
                throw new IllegalArgumentException("Can not construct IdKey for null key");
            }
            this.type = cls;
            this.scope = cls2;
            this.key = obj;
            int hashCode = obj.hashCode() + cls.getName().hashCode();
            this.hashCode = cls2 != null ? hashCode ^ cls2.getName().hashCode() : hashCode;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj != null && obj.getClass() == getClass()) {
                IdKey idKey = (IdKey) obj;
                return idKey.key.equals(this.key) && idKey.type == this.type && idKey.scope == this.scope;
            }
            return false;
        }

        public String toString() {
            Object[] objArr = new Object[3];
            objArr[0] = this.key;
            Class<?> cls = this.type;
            objArr[1] = cls == null ? "NONE" : cls.getName();
            Class<?> cls2 = this.scope;
            objArr[2] = cls2 != null ? cls2.getName() : "NONE";
            return String.format("[ObjectId: key=%s, type=%s, scope=%s]", objArr);
        }
    }
}
