package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.deser.CreatorProperty;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.std.StdValueInstantiator;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import com.fasterxml.jackson.databind.introspect.AnnotationMap;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.tron.wallet.business.tabmy.advancedfeatures.export.bean.ExportWalletBean;
import java.io.Serializable;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
public class CreatorCollector {
    protected static final int C_ARRAY_DELEGATE = 8;
    protected static final int C_BOOLEAN = 5;
    protected static final int C_DEFAULT = 0;
    protected static final int C_DELEGATE = 6;
    protected static final int C_DOUBLE = 4;
    protected static final int C_INT = 2;
    protected static final int C_LONG = 3;
    protected static final int C_PROPS = 7;
    protected static final int C_STRING = 1;
    protected static final String[] TYPE_DESCS = {ExportWalletBean.md5Default, "from-String", "from-int", "from-long", "from-double", "from-boolean", "delegate", "property-based"};
    protected SettableBeanProperty[] _arrayDelegateArgs;
    protected final BeanDescription _beanDesc;
    protected final boolean _canFixAccess;
    protected SettableBeanProperty[] _delegateArgs;
    protected final boolean _forceAccess;
    protected AnnotatedParameter _incompleteParameter;
    protected SettableBeanProperty[] _propertyBasedArgs;
    protected final AnnotatedWithParams[] _creators = new AnnotatedWithParams[9];
    protected int _explicitCreators = 0;
    protected boolean _hasNonDefaultCreator = false;

    public void addIncompeteParameter(AnnotatedParameter annotatedParameter) {
        if (this._incompleteParameter == null) {
            this._incompleteParameter = annotatedParameter;
        }
    }

    public CreatorCollector(BeanDescription beanDescription, MapperConfig<?> mapperConfig) {
        this._beanDesc = beanDescription;
        this._canFixAccess = mapperConfig.canOverrideAccessModifiers();
        this._forceAccess = mapperConfig.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS);
    }

    public ValueInstantiator constructValueInstantiator(DeserializationConfig deserializationConfig) {
        JavaType _computeDelegateType = _computeDelegateType(this._creators[6], this._delegateArgs);
        JavaType _computeDelegateType2 = _computeDelegateType(this._creators[8], this._arrayDelegateArgs);
        JavaType type = this._beanDesc.getType();
        AnnotatedWithParams tryToOptimize = StdTypeConstructor.tryToOptimize(this._creators[0]);
        StdValueInstantiator stdValueInstantiator = new StdValueInstantiator(deserializationConfig, type);
        AnnotatedWithParams[] annotatedWithParamsArr = this._creators;
        stdValueInstantiator.configureFromObjectSettings(tryToOptimize, annotatedWithParamsArr[6], _computeDelegateType, this._delegateArgs, annotatedWithParamsArr[7], this._propertyBasedArgs);
        stdValueInstantiator.configureFromArraySettings(this._creators[8], _computeDelegateType2, this._arrayDelegateArgs);
        stdValueInstantiator.configureFromStringCreator(this._creators[1]);
        stdValueInstantiator.configureFromIntCreator(this._creators[2]);
        stdValueInstantiator.configureFromLongCreator(this._creators[3]);
        stdValueInstantiator.configureFromDoubleCreator(this._creators[4]);
        stdValueInstantiator.configureFromBooleanCreator(this._creators[5]);
        stdValueInstantiator.configureIncompleteParameter(this._incompleteParameter);
        return stdValueInstantiator;
    }

    public void setDefaultCreator(AnnotatedWithParams annotatedWithParams) {
        this._creators[0] = (AnnotatedWithParams) _fixAccess(annotatedWithParams);
    }

    public void addStringCreator(AnnotatedWithParams annotatedWithParams, boolean z) {
        verifyNonDup(annotatedWithParams, 1, z);
    }

    public void addIntCreator(AnnotatedWithParams annotatedWithParams, boolean z) {
        verifyNonDup(annotatedWithParams, 2, z);
    }

    public void addLongCreator(AnnotatedWithParams annotatedWithParams, boolean z) {
        verifyNonDup(annotatedWithParams, 3, z);
    }

    public void addDoubleCreator(AnnotatedWithParams annotatedWithParams, boolean z) {
        verifyNonDup(annotatedWithParams, 4, z);
    }

    public void addBooleanCreator(AnnotatedWithParams annotatedWithParams, boolean z) {
        verifyNonDup(annotatedWithParams, 5, z);
    }

    public void addDelegatingCreator(AnnotatedWithParams annotatedWithParams, boolean z, SettableBeanProperty[] settableBeanPropertyArr) {
        if (annotatedWithParams.getParameterType(0).isCollectionLikeType()) {
            verifyNonDup(annotatedWithParams, 8, z);
            this._arrayDelegateArgs = settableBeanPropertyArr;
            return;
        }
        verifyNonDup(annotatedWithParams, 6, z);
        this._delegateArgs = settableBeanPropertyArr;
    }

    public void addPropertyCreator(AnnotatedWithParams annotatedWithParams, boolean z, SettableBeanProperty[] settableBeanPropertyArr) {
        Integer num;
        verifyNonDup(annotatedWithParams, 7, z);
        if (settableBeanPropertyArr.length > 1) {
            HashMap hashMap = new HashMap();
            int length = settableBeanPropertyArr.length;
            for (int i = 0; i < length; i++) {
                String name = settableBeanPropertyArr[i].getName();
                if ((name.length() != 0 || settableBeanPropertyArr[i].getInjectableValueId() == null) && (num = (Integer) hashMap.put(name, Integer.valueOf(i))) != null) {
                    throw new IllegalArgumentException(String.format("Duplicate creator property \"%s\" (index %s vs %d)", name, num, Integer.valueOf(i)));
                }
            }
        }
        this._propertyBasedArgs = settableBeanPropertyArr;
    }

    @Deprecated
    public void addStringCreator(AnnotatedWithParams annotatedWithParams) {
        addStringCreator(annotatedWithParams, false);
    }

    @Deprecated
    public void addIntCreator(AnnotatedWithParams annotatedWithParams) {
        addBooleanCreator(annotatedWithParams, false);
    }

    @Deprecated
    public void addLongCreator(AnnotatedWithParams annotatedWithParams) {
        addBooleanCreator(annotatedWithParams, false);
    }

    @Deprecated
    public void addDoubleCreator(AnnotatedWithParams annotatedWithParams) {
        addBooleanCreator(annotatedWithParams, false);
    }

    @Deprecated
    public void addBooleanCreator(AnnotatedWithParams annotatedWithParams) {
        addBooleanCreator(annotatedWithParams, false);
    }

    @Deprecated
    public void addDelegatingCreator(AnnotatedWithParams annotatedWithParams, CreatorProperty[] creatorPropertyArr) {
        addDelegatingCreator(annotatedWithParams, false, creatorPropertyArr);
    }

    @Deprecated
    public void addPropertyCreator(AnnotatedWithParams annotatedWithParams, CreatorProperty[] creatorPropertyArr) {
        addPropertyCreator(annotatedWithParams, false, creatorPropertyArr);
    }

    public boolean hasDefaultCreator() {
        return this._creators[0] != null;
    }

    public boolean hasDelegatingCreator() {
        return this._creators[6] != null;
    }

    public boolean hasPropertyBasedCreator() {
        return this._creators[7] != null;
    }

    private JavaType _computeDelegateType(AnnotatedWithParams annotatedWithParams, SettableBeanProperty[] settableBeanPropertyArr) {
        if (!this._hasNonDefaultCreator || annotatedWithParams == null) {
            return null;
        }
        int i = 0;
        if (settableBeanPropertyArr != null) {
            int length = settableBeanPropertyArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                } else if (settableBeanPropertyArr[i2] == null) {
                    i = i2;
                    break;
                } else {
                    i2++;
                }
            }
        }
        return annotatedWithParams.getParameterType(i);
    }

    private <T extends AnnotatedMember> T _fixAccess(T t) {
        if (t != null && this._canFixAccess) {
            ClassUtil.checkAndFixAccess((Member) t.getAnnotated(), this._forceAccess);
        }
        return t;
    }

    protected void verifyNonDup(com.fasterxml.jackson.databind.introspect.AnnotatedWithParams r7, int r8, boolean r9) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.fasterxml.jackson.databind.deser.impl.CreatorCollector.verifyNonDup(com.fasterxml.jackson.databind.introspect.AnnotatedWithParams, int, boolean):void");
    }

    protected boolean _isEnumValueOf(AnnotatedWithParams annotatedWithParams) {
        return annotatedWithParams.getDeclaringClass().isEnum() && "valueOf".equals(annotatedWithParams.getName());
    }

    protected static final class StdTypeConstructor extends AnnotatedWithParams implements Serializable {
        public static final int TYPE_ARRAY_LIST = 1;
        public static final int TYPE_HASH_MAP = 2;
        public static final int TYPE_LINKED_HASH_MAP = 3;
        private static final long serialVersionUID = 1;
        private final AnnotatedWithParams _base;
        private final int _type;

        @Override
        public boolean equals(Object obj) {
            return obj == this;
        }

        public StdTypeConstructor(AnnotatedWithParams annotatedWithParams, int i) {
            super(annotatedWithParams, null);
            this._base = annotatedWithParams;
            this._type = i;
        }

        public static AnnotatedWithParams tryToOptimize(AnnotatedWithParams annotatedWithParams) {
            if (annotatedWithParams != null) {
                Class<?> declaringClass = annotatedWithParams.getDeclaringClass();
                if (declaringClass == List.class || declaringClass == ArrayList.class) {
                    return new StdTypeConstructor(annotatedWithParams, 1);
                }
                if (declaringClass == LinkedHashMap.class) {
                    return new StdTypeConstructor(annotatedWithParams, 3);
                }
                if (declaringClass == HashMap.class) {
                    return new StdTypeConstructor(annotatedWithParams, 2);
                }
            }
            return annotatedWithParams;
        }

        protected final Object _construct() {
            int i = this._type;
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        return new LinkedHashMap();
                    }
                    throw new IllegalStateException("Unknown type " + this._type);
                }
                return new HashMap();
            }
            return new ArrayList();
        }

        @Override
        public int getParameterCount() {
            return this._base.getParameterCount();
        }

        @Override
        public Class<?> getRawParameterType(int i) {
            return this._base.getRawParameterType(i);
        }

        @Override
        public JavaType getParameterType(int i) {
            return this._base.getParameterType(i);
        }

        @Override
        @Deprecated
        public Type getGenericParameterType(int i) {
            return this._base.getGenericParameterType(i);
        }

        @Override
        public Object call() throws Exception {
            return _construct();
        }

        @Override
        public Object call(Object[] objArr) throws Exception {
            return _construct();
        }

        @Override
        public Object call1(Object obj) throws Exception {
            return _construct();
        }

        @Override
        public Class<?> getDeclaringClass() {
            return this._base.getDeclaringClass();
        }

        @Override
        public Member getMember() {
            return this._base.getMember();
        }

        @Override
        public void setValue(Object obj, Object obj2) throws UnsupportedOperationException, IllegalArgumentException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object getValue(Object obj) throws UnsupportedOperationException, IllegalArgumentException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Annotated withAnnotations(AnnotationMap annotationMap) {
            throw new UnsupportedOperationException();
        }

        @Override
        public AnnotatedElement getAnnotated() {
            return this._base.getAnnotated();
        }

        @Override
        public int getModifiers() {
            return this._base.getMember().getModifiers();
        }

        @Override
        public String getName() {
            return this._base.getName();
        }

        @Override
        public JavaType getType() {
            return this._base.getType();
        }

        @Override
        public Class<?> getRawType() {
            return this._base.getRawType();
        }

        @Override
        public int hashCode() {
            return this._base.hashCode();
        }

        @Override
        public String toString() {
            return this._base.toString();
        }
    }
}
