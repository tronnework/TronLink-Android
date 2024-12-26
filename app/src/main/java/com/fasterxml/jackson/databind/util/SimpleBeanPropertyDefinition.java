package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import java.util.Collections;
import java.util.Iterator;
public class SimpleBeanPropertyDefinition extends BeanPropertyDefinition {
    protected final PropertyName _fullName;
    protected final JsonInclude.Value _inclusion;
    protected final AnnotationIntrospector _introspector;
    protected final AnnotatedMember _member;
    protected final PropertyMetadata _metadata;
    @Deprecated
    protected final String _name;

    @Override
    public JsonInclude.Value findInclusion() {
        return this._inclusion;
    }

    @Override
    public PropertyName getFullName() {
        return this._fullName;
    }

    @Override
    public PropertyMetadata getMetadata() {
        return this._metadata;
    }

    @Override
    public AnnotatedMember getPrimaryMember() {
        return this._member;
    }

    @Override
    public boolean isExplicitlyIncluded() {
        return false;
    }

    @Override
    public boolean isExplicitlyNamed() {
        return false;
    }

    protected SimpleBeanPropertyDefinition(AnnotatedMember annotatedMember, PropertyName propertyName, AnnotationIntrospector annotationIntrospector, PropertyMetadata propertyMetadata, JsonInclude.Include include) {
        this(annotatedMember, propertyName, annotationIntrospector, propertyMetadata, (include == null || include == JsonInclude.Include.USE_DEFAULTS) ? EMPTY_INCLUDE : JsonInclude.Value.construct(include, null));
    }

    protected SimpleBeanPropertyDefinition(AnnotatedMember annotatedMember, PropertyName propertyName, AnnotationIntrospector annotationIntrospector, PropertyMetadata propertyMetadata, JsonInclude.Value value) {
        this._introspector = annotationIntrospector;
        this._member = annotatedMember;
        this._fullName = propertyName;
        this._name = propertyName.getSimpleName();
        this._metadata = propertyMetadata == null ? PropertyMetadata.STD_OPTIONAL : propertyMetadata;
        this._inclusion = value;
    }

    @Deprecated
    protected SimpleBeanPropertyDefinition(AnnotatedMember annotatedMember, String str, AnnotationIntrospector annotationIntrospector) {
        this(annotatedMember, new PropertyName(str), annotationIntrospector, (PropertyMetadata) null, EMPTY_INCLUDE);
    }

    public static SimpleBeanPropertyDefinition construct(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember) {
        return new SimpleBeanPropertyDefinition(annotatedMember, PropertyName.construct(annotatedMember.getName()), mapperConfig == null ? null : mapperConfig.getAnnotationIntrospector(), (PropertyMetadata) null, EMPTY_INCLUDE);
    }

    @Deprecated
    public static SimpleBeanPropertyDefinition construct(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, String str) {
        return new SimpleBeanPropertyDefinition(annotatedMember, PropertyName.construct(str), mapperConfig == null ? null : mapperConfig.getAnnotationIntrospector(), (PropertyMetadata) null, EMPTY_INCLUDE);
    }

    public static SimpleBeanPropertyDefinition construct(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, PropertyName propertyName) {
        return construct(mapperConfig, annotatedMember, propertyName, (PropertyMetadata) null, EMPTY_INCLUDE);
    }

    public static SimpleBeanPropertyDefinition construct(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, PropertyName propertyName, PropertyMetadata propertyMetadata, JsonInclude.Include include) {
        return new SimpleBeanPropertyDefinition(annotatedMember, propertyName, mapperConfig == null ? null : mapperConfig.getAnnotationIntrospector(), propertyMetadata, include);
    }

    public static SimpleBeanPropertyDefinition construct(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, PropertyName propertyName, PropertyMetadata propertyMetadata, JsonInclude.Value value) {
        return new SimpleBeanPropertyDefinition(annotatedMember, propertyName, mapperConfig == null ? null : mapperConfig.getAnnotationIntrospector(), propertyMetadata, value);
    }

    @Deprecated
    public BeanPropertyDefinition withName(String str) {
        return withSimpleName(str);
    }

    @Override
    public BeanPropertyDefinition withSimpleName(String str) {
        return (!this._fullName.hasSimpleName(str) || this._fullName.hasNamespace()) ? new SimpleBeanPropertyDefinition(this._member, new PropertyName(str), this._introspector, this._metadata, this._inclusion) : this;
    }

    @Override
    public BeanPropertyDefinition withName(PropertyName propertyName) {
        return this._fullName.equals(propertyName) ? this : new SimpleBeanPropertyDefinition(this._member, propertyName, this._introspector, this._metadata, this._inclusion);
    }

    public BeanPropertyDefinition withMetadata(PropertyMetadata propertyMetadata) {
        return propertyMetadata.equals(this._metadata) ? this : new SimpleBeanPropertyDefinition(this._member, this._fullName, this._introspector, propertyMetadata, this._inclusion);
    }

    public BeanPropertyDefinition withInclusion(JsonInclude.Value value) {
        return this._inclusion == value ? this : new SimpleBeanPropertyDefinition(this._member, this._fullName, this._introspector, this._metadata, value);
    }

    @Override
    public String getName() {
        return this._fullName.getSimpleName();
    }

    @Override
    public boolean hasName(PropertyName propertyName) {
        return this._fullName.equals(propertyName);
    }

    @Override
    public String getInternalName() {
        return getName();
    }

    @Override
    public PropertyName getWrapperName() {
        AnnotationIntrospector annotationIntrospector = this._introspector;
        if (annotationIntrospector != null || this._member == null) {
            return annotationIntrospector.findWrapperName(this._member);
        }
        return null;
    }

    @Override
    public boolean hasGetter() {
        return getGetter() != null;
    }

    @Override
    public boolean hasSetter() {
        return getSetter() != null;
    }

    @Override
    public boolean hasField() {
        return this._member instanceof AnnotatedField;
    }

    @Override
    public boolean hasConstructorParameter() {
        return this._member instanceof AnnotatedParameter;
    }

    @Override
    public AnnotatedMethod getGetter() {
        AnnotatedMember annotatedMember = this._member;
        if ((annotatedMember instanceof AnnotatedMethod) && ((AnnotatedMethod) annotatedMember).getParameterCount() == 0) {
            return (AnnotatedMethod) this._member;
        }
        return null;
    }

    @Override
    public AnnotatedMethod getSetter() {
        AnnotatedMember annotatedMember = this._member;
        if ((annotatedMember instanceof AnnotatedMethod) && ((AnnotatedMethod) annotatedMember).getParameterCount() == 1) {
            return (AnnotatedMethod) this._member;
        }
        return null;
    }

    @Override
    public AnnotatedField getField() {
        AnnotatedMember annotatedMember = this._member;
        if (annotatedMember instanceof AnnotatedField) {
            return (AnnotatedField) annotatedMember;
        }
        return null;
    }

    @Override
    public AnnotatedParameter getConstructorParameter() {
        AnnotatedMember annotatedMember = this._member;
        if (annotatedMember instanceof AnnotatedParameter) {
            return (AnnotatedParameter) annotatedMember;
        }
        return null;
    }

    @Override
    public Iterator<AnnotatedParameter> getConstructorParameters() {
        AnnotatedParameter constructorParameter = getConstructorParameter();
        if (constructorParameter == null) {
            return ClassUtil.emptyIterator();
        }
        return Collections.singleton(constructorParameter).iterator();
    }

    @Override
    public AnnotatedMember getAccessor() {
        AnnotatedMethod getter = getGetter();
        return getter == null ? getField() : getter;
    }

    @Override
    public AnnotatedMember getMutator() {
        AnnotatedParameter constructorParameter = getConstructorParameter();
        if (constructorParameter == null) {
            AnnotatedMethod setter = getSetter();
            return setter == null ? getField() : setter;
        }
        return constructorParameter;
    }

    @Override
    public AnnotatedMember getNonConstructorMutator() {
        AnnotatedMethod setter = getSetter();
        return setter == null ? getField() : setter;
    }
}
