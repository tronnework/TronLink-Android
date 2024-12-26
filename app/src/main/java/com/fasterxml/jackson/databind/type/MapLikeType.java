package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import java.lang.reflect.TypeVariable;
import java.util.Map;
import kotlin.text.Typography;
public class MapLikeType extends TypeBase {
    private static final long serialVersionUID = 1;
    protected final JavaType _keyType;
    protected final JavaType _valueType;

    @Override
    public JavaType getContentType() {
        return this._valueType;
    }

    @Override
    public JavaType getKeyType() {
        return this._keyType;
    }

    @Override
    public boolean isContainerType() {
        return true;
    }

    @Override
    public boolean isMapLikeType() {
        return true;
    }

    public MapLikeType(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr, JavaType javaType2, JavaType javaType3, Object obj, Object obj2, boolean z) {
        super(cls, typeBindings, javaType, javaTypeArr, javaType2.hashCode() ^ javaType3.hashCode(), obj, obj2, z);
        this._keyType = javaType2;
        this._valueType = javaType3;
    }

    public MapLikeType(TypeBase typeBase, JavaType javaType, JavaType javaType2) {
        super(typeBase);
        this._keyType = javaType;
        this._valueType = javaType2;
    }

    public static MapLikeType upgradeFrom(JavaType javaType, JavaType javaType2, JavaType javaType3) {
        if (javaType instanceof TypeBase) {
            return new MapLikeType((TypeBase) javaType, javaType2, javaType3);
        }
        throw new IllegalArgumentException("Can not upgrade from an instance of " + javaType.getClass());
    }

    @Deprecated
    public static MapLikeType construct(Class<?> cls, JavaType javaType, JavaType javaType2) {
        TypeBindings emptyBindings;
        TypeVariable<Class<?>>[] typeParameters = cls.getTypeParameters();
        if (typeParameters == null || typeParameters.length != 2) {
            emptyBindings = TypeBindings.emptyBindings();
        } else {
            emptyBindings = TypeBindings.create(cls, javaType, javaType2);
        }
        return new MapLikeType(cls, emptyBindings, _bogusSuperClass(cls), null, javaType, javaType2, null, null, false);
    }

    @Override
    @Deprecated
    protected JavaType _narrow(Class<?> cls) {
        return new MapLikeType(cls, this._bindings, this._superClass, this._superInterfaces, this._keyType, this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    public MapLikeType withKeyType(JavaType javaType) {
        return javaType == this._keyType ? this : new MapLikeType(this._class, this._bindings, this._superClass, this._superInterfaces, javaType, this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override
    public JavaType withContentType(JavaType javaType) {
        return this._valueType == javaType ? this : new MapLikeType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType, javaType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override
    public MapLikeType withTypeHandler(Object obj) {
        return new MapLikeType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType, this._valueType, this._valueHandler, obj, this._asStatic);
    }

    @Override
    public MapLikeType withContentTypeHandler(Object obj) {
        return new MapLikeType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType, this._valueType.withTypeHandler(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override
    public MapLikeType withValueHandler(Object obj) {
        return new MapLikeType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType, this._valueType, obj, this._typeHandler, this._asStatic);
    }

    @Override
    public MapLikeType withContentValueHandler(Object obj) {
        return new MapLikeType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType, this._valueType.withValueHandler(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override
    public JavaType withHandlersFrom(JavaType javaType) {
        JavaType withHandlersFrom;
        JavaType withHandlersFrom2;
        JavaType withHandlersFrom3 = super.withHandlersFrom(javaType);
        JavaType keyType = javaType.getKeyType();
        if ((withHandlersFrom3 instanceof MapLikeType) && keyType != null && (withHandlersFrom2 = this._keyType.withHandlersFrom(keyType)) != this._keyType) {
            withHandlersFrom3 = ((MapLikeType) withHandlersFrom3).withKeyType(withHandlersFrom2);
        }
        JavaType contentType = javaType.getContentType();
        return (contentType == null || (withHandlersFrom = this._valueType.withHandlersFrom(contentType)) == this._valueType) ? withHandlersFrom3 : withHandlersFrom3.withContentType(withHandlersFrom);
    }

    @Override
    public MapLikeType withStaticTyping() {
        return this._asStatic ? this : new MapLikeType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType, this._valueType.withStaticTyping(), this._valueHandler, this._typeHandler, true);
    }

    @Override
    public JavaType refine(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        return new MapLikeType(cls, typeBindings, javaType, javaTypeArr, this._keyType, this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override
    protected String buildCanonicalName() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._class.getName());
        if (this._keyType != null) {
            sb.append(Typography.less);
            sb.append(this._keyType.toCanonical());
            sb.append(',');
            sb.append(this._valueType.toCanonical());
            sb.append(Typography.greater);
        }
        return sb.toString();
    }

    @Override
    public Object getContentValueHandler() {
        return this._valueType.getValueHandler();
    }

    @Override
    public Object getContentTypeHandler() {
        return this._valueType.getTypeHandler();
    }

    @Override
    public boolean hasHandlers() {
        return super.hasHandlers() || this._valueType.hasHandlers() || this._keyType.hasHandlers();
    }

    @Override
    public StringBuilder getErasedSignature(StringBuilder sb) {
        return _classSignature(this._class, sb, true);
    }

    @Override
    public StringBuilder getGenericSignature(StringBuilder sb) {
        _classSignature(this._class, sb, false);
        sb.append(Typography.less);
        this._keyType.getGenericSignature(sb);
        this._valueType.getGenericSignature(sb);
        sb.append(">;");
        return sb;
    }

    public MapLikeType withKeyTypeHandler(Object obj) {
        return new MapLikeType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType.withTypeHandler(obj), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    public MapLikeType withKeyValueHandler(Object obj) {
        return new MapLikeType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType.withValueHandler(obj), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    public boolean isTrueMapType() {
        return Map.class.isAssignableFrom(this._class);
    }

    @Override
    public String toString() {
        return String.format("[map-like type; class %s, %s -> %s]", this._class.getName(), this._keyType, this._valueType);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == getClass()) {
            MapLikeType mapLikeType = (MapLikeType) obj;
            return this._class == mapLikeType._class && this._keyType.equals(mapLikeType._keyType) && this._valueType.equals(mapLikeType._valueType);
        }
        return false;
    }
}
