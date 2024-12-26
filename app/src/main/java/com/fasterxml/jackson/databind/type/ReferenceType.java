package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import kotlin.text.Typography;
public class ReferenceType extends SimpleType {
    private static final long serialVersionUID = 1;
    protected final JavaType _anchorType;
    protected final JavaType _referencedType;

    public JavaType getAnchorType() {
        return this._anchorType;
    }

    @Override
    public JavaType getContentType() {
        return this._referencedType;
    }

    @Override
    public JavaType getReferencedType() {
        return this._referencedType;
    }

    @Override
    public boolean hasContentType() {
        return true;
    }

    public boolean isAnchorType() {
        return this._anchorType == this;
    }

    @Override
    public boolean isReferenceType() {
        return true;
    }

    protected ReferenceType(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr, JavaType javaType2, JavaType javaType3, Object obj, Object obj2, boolean z) {
        super(cls, typeBindings, javaType, javaTypeArr, javaType2.hashCode(), obj, obj2, z);
        this._referencedType = javaType2;
        this._anchorType = javaType3 == null ? this : javaType3;
    }

    protected ReferenceType(TypeBase typeBase, JavaType javaType) {
        super(typeBase);
        this._referencedType = javaType;
        this._anchorType = this;
    }

    public static ReferenceType upgradeFrom(JavaType javaType, JavaType javaType2) {
        if (javaType2 == null) {
            throw new IllegalArgumentException("Missing referencedType");
        }
        if (javaType instanceof TypeBase) {
            return new ReferenceType((TypeBase) javaType, javaType2);
        }
        throw new IllegalArgumentException("Can not upgrade from an instance of " + javaType.getClass());
    }

    public static ReferenceType construct(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr, JavaType javaType2) {
        return new ReferenceType(cls, typeBindings, javaType, javaTypeArr, javaType2, null, null, null, false);
    }

    @Deprecated
    public static ReferenceType construct(Class<?> cls, JavaType javaType) {
        return new ReferenceType(cls, TypeBindings.emptyBindings(), null, null, null, javaType, null, null, false);
    }

    @Override
    public JavaType withContentType(JavaType javaType) {
        return this._referencedType == javaType ? this : new ReferenceType(this._class, this._bindings, this._superClass, this._superInterfaces, javaType, this._anchorType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override
    public ReferenceType withTypeHandler(Object obj) {
        return obj == this._typeHandler ? this : new ReferenceType(this._class, this._bindings, this._superClass, this._superInterfaces, this._referencedType, this._anchorType, this._valueHandler, obj, this._asStatic);
    }

    @Override
    public ReferenceType withContentTypeHandler(Object obj) {
        return obj == this._referencedType.getTypeHandler() ? this : new ReferenceType(this._class, this._bindings, this._superClass, this._superInterfaces, this._referencedType.withTypeHandler(obj), this._anchorType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override
    public ReferenceType withValueHandler(Object obj) {
        return obj == this._valueHandler ? this : new ReferenceType(this._class, this._bindings, this._superClass, this._superInterfaces, this._referencedType, this._anchorType, obj, this._typeHandler, this._asStatic);
    }

    @Override
    public ReferenceType withContentValueHandler(Object obj) {
        if (obj == this._referencedType.getValueHandler()) {
            return this;
        }
        return new ReferenceType(this._class, this._bindings, this._superClass, this._superInterfaces, this._referencedType.withValueHandler(obj), this._anchorType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override
    public ReferenceType withStaticTyping() {
        return this._asStatic ? this : new ReferenceType(this._class, this._bindings, this._superClass, this._superInterfaces, this._referencedType.withStaticTyping(), this._anchorType, this._valueHandler, this._typeHandler, true);
    }

    @Override
    public JavaType refine(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        return new ReferenceType(cls, this._bindings, javaType, javaTypeArr, this._referencedType, this._anchorType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override
    protected String buildCanonicalName() {
        return this._class.getName() + Typography.less + this._referencedType.toCanonical();
    }

    @Override
    @Deprecated
    protected JavaType _narrow(Class<?> cls) {
        return new ReferenceType(cls, this._bindings, this._superClass, this._superInterfaces, this._referencedType, this._anchorType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override
    public StringBuilder getErasedSignature(StringBuilder sb) {
        return _classSignature(this._class, sb, true);
    }

    @Override
    public StringBuilder getGenericSignature(StringBuilder sb) {
        _classSignature(this._class, sb, false);
        sb.append(Typography.less);
        StringBuilder genericSignature = this._referencedType.getGenericSignature(sb);
        genericSignature.append(">;");
        return genericSignature;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(40);
        sb.append("[reference type, class ");
        sb.append(buildCanonicalName());
        sb.append(Typography.less);
        sb.append(this._referencedType);
        sb.append(">]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == getClass()) {
            ReferenceType referenceType = (ReferenceType) obj;
            if (referenceType._class != this._class) {
                return false;
            }
            return this._referencedType.equals(referenceType._referencedType);
        }
        return false;
    }
}
