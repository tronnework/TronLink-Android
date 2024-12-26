package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
public class ResolvedRecursiveType extends TypeBase {
    private static final long serialVersionUID = 1;
    protected JavaType _referencedType;

    @Override
    @Deprecated
    protected JavaType _narrow(Class<?> cls) {
        return this;
    }

    public JavaType getSelfReferencedType() {
        return this._referencedType;
    }

    @Override
    public boolean isContainerType() {
        return false;
    }

    @Override
    public JavaType refine(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        return null;
    }

    @Override
    public JavaType withContentType(JavaType javaType) {
        return this;
    }

    @Override
    public JavaType withContentTypeHandler(Object obj) {
        return this;
    }

    @Override
    public JavaType withContentValueHandler(Object obj) {
        return this;
    }

    @Override
    public JavaType withStaticTyping() {
        return this;
    }

    @Override
    public JavaType withTypeHandler(Object obj) {
        return this;
    }

    @Override
    public JavaType withValueHandler(Object obj) {
        return this;
    }

    public ResolvedRecursiveType(Class<?> cls, TypeBindings typeBindings) {
        super(cls, typeBindings, null, null, 0, null, null, false);
    }

    public void setReference(JavaType javaType) {
        if (this._referencedType == null) {
            this._referencedType = javaType;
            return;
        }
        throw new IllegalStateException("Trying to re-set self reference; old value = " + this._referencedType + ", new = " + javaType);
    }

    @Override
    public StringBuilder getGenericSignature(StringBuilder sb) {
        return this._referencedType.getGenericSignature(sb);
    }

    @Override
    public StringBuilder getErasedSignature(StringBuilder sb) {
        return this._referencedType.getErasedSignature(sb);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(40);
        sb.append("[recursive type; ");
        JavaType javaType = this._referencedType;
        if (javaType == null) {
            sb.append("UNRESOLVED");
        } else {
            sb.append(javaType.getRawClass().getName());
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj != null && this._referencedType != null && obj.getClass() == getClass() && this._referencedType.equals(((ResolvedRecursiveType) obj).getSelfReferencedType());
    }
}
