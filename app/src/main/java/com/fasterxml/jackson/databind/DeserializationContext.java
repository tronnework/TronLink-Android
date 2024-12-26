package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualKeyDeserializer;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.deser.DeserializerCache;
import com.fasterxml.jackson.databind.deser.DeserializerFactory;
import com.fasterxml.jackson.databind.deser.UnresolvedForwardReference;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.deser.impl.TypeWrappedDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.LinkedNode;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.HttpUrl;
public abstract class DeserializationContext extends DatabindContext implements Serializable {
    private static final int MAX_ERROR_STR_LEN = 500;
    private static final long serialVersionUID = 1;
    protected transient ArrayBuilders _arrayBuilders;
    protected transient ContextAttributes _attributes;
    protected final DeserializerCache _cache;
    protected final DeserializationConfig _config;
    protected LinkedNode<JavaType> _currentType;
    protected transient DateFormat _dateFormat;
    protected final DeserializerFactory _factory;
    protected final int _featureFlags;
    protected final InjectableValues _injectableValues;
    protected transient ObjectBuffer _objectBuffer;
    protected transient JsonParser _parser;
    protected final Class<?> _view;

    public abstract void checkUnresolvedObjectId() throws UnresolvedForwardReference;

    public abstract JsonDeserializer<Object> deserializerInstance(Annotated annotated, Object obj) throws JsonMappingException;

    public abstract ReadableObjectId findObjectId(Object obj, ObjectIdGenerator<?> objectIdGenerator, ObjectIdResolver objectIdResolver);

    @Override
    public final Class<?> getActiveView() {
        return this._view;
    }

    @Override
    public DeserializationConfig getConfig() {
        return this._config;
    }

    public final int getDeserializationFeatures() {
        return this._featureFlags;
    }

    public DeserializerFactory getFactory() {
        return this._factory;
    }

    public final JsonParser getParser() {
        return this._parser;
    }

    public final boolean hasDeserializationFeatures(int i) {
        return (this._featureFlags & i) == i;
    }

    public final boolean hasSomeOfFeatures(int i) {
        return (i & this._featureFlags) != 0;
    }

    public abstract KeyDeserializer keyDeserializerInstance(Annotated annotated, Object obj) throws JsonMappingException;

    protected DeserializationContext(DeserializerFactory deserializerFactory) {
        this(deserializerFactory, (DeserializerCache) null);
    }

    public DeserializationContext(DeserializerFactory deserializerFactory, DeserializerCache deserializerCache) {
        if (deserializerFactory == null) {
            throw new IllegalArgumentException("Can not pass null DeserializerFactory");
        }
        this._factory = deserializerFactory;
        this._cache = deserializerCache == null ? new DeserializerCache() : deserializerCache;
        this._featureFlags = 0;
        this._config = null;
        this._injectableValues = null;
        this._view = null;
        this._attributes = null;
    }

    public DeserializationContext(DeserializationContext deserializationContext, DeserializerFactory deserializerFactory) {
        this._cache = deserializationContext._cache;
        this._factory = deserializerFactory;
        this._config = deserializationContext._config;
        this._featureFlags = deserializationContext._featureFlags;
        this._view = deserializationContext._view;
        this._parser = deserializationContext._parser;
        this._injectableValues = deserializationContext._injectableValues;
        this._attributes = deserializationContext._attributes;
    }

    public DeserializationContext(DeserializationContext deserializationContext, DeserializationConfig deserializationConfig, JsonParser jsonParser, InjectableValues injectableValues) {
        this._cache = deserializationContext._cache;
        this._factory = deserializationContext._factory;
        this._config = deserializationConfig;
        this._featureFlags = deserializationConfig.getDeserializationFeatures();
        this._view = deserializationConfig.getActiveView();
        this._parser = jsonParser;
        this._injectableValues = injectableValues;
        this._attributes = deserializationConfig.getAttributes();
    }

    public DeserializationContext(DeserializationContext deserializationContext) {
        this._cache = new DeserializerCache();
        this._factory = deserializationContext._factory;
        this._config = deserializationContext._config;
        this._featureFlags = deserializationContext._featureFlags;
        this._view = deserializationContext._view;
        this._injectableValues = null;
    }

    @Override
    public final boolean canOverrideAccessModifiers() {
        return this._config.canOverrideAccessModifiers();
    }

    @Override
    public final boolean isEnabled(MapperFeature mapperFeature) {
        return this._config.isEnabled(mapperFeature);
    }

    @Override
    public final JsonFormat.Value getDefaultPropertyFormat(Class<?> cls) {
        return this._config.getDefaultPropertyFormat(cls);
    }

    @Override
    public final AnnotationIntrospector getAnnotationIntrospector() {
        return this._config.getAnnotationIntrospector();
    }

    @Override
    public final TypeFactory getTypeFactory() {
        return this._config.getTypeFactory();
    }

    @Override
    public Locale getLocale() {
        return this._config.getLocale();
    }

    @Override
    public TimeZone getTimeZone() {
        return this._config.getTimeZone();
    }

    @Override
    public Object getAttribute(Object obj) {
        return this._attributes.getAttribute(obj);
    }

    @Override
    public DeserializationContext setAttribute(Object obj, Object obj2) {
        this._attributes = this._attributes.withPerCallAttribute(obj, obj2);
        return this;
    }

    public JavaType getContextualType() {
        LinkedNode<JavaType> linkedNode = this._currentType;
        if (linkedNode == null) {
            return null;
        }
        return linkedNode.value();
    }

    public final boolean isEnabled(DeserializationFeature deserializationFeature) {
        return (deserializationFeature.getMask() & this._featureFlags) != 0;
    }

    public final Object findInjectableValue(Object obj, BeanProperty beanProperty, Object obj2) {
        InjectableValues injectableValues = this._injectableValues;
        if (injectableValues == null) {
            throw new IllegalStateException("No 'injectableValues' configured, can not inject value with id [" + obj + "]");
        }
        return injectableValues.findInjectableValue(obj, this, beanProperty, obj2);
    }

    public final Base64Variant getBase64Variant() {
        return this._config.getBase64Variant();
    }

    public final JsonNodeFactory getNodeFactory() {
        return this._config.getNodeFactory();
    }

    public boolean hasValueDeserializerFor(JavaType javaType, AtomicReference<Throwable> atomicReference) {
        try {
            return this._cache.hasValueDeserializerFor(this, this._factory, javaType);
        } catch (JsonMappingException e) {
            if (atomicReference != null) {
                atomicReference.set(e);
                return false;
            }
            return false;
        } catch (RuntimeException e2) {
            if (atomicReference == null) {
                throw e2;
            }
            atomicReference.set(e2);
            return false;
        }
    }

    public final JsonDeserializer<Object> findContextualValueDeserializer(JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        JsonDeserializer<Object> findValueDeserializer = this._cache.findValueDeserializer(this, this._factory, javaType);
        return findValueDeserializer != null ? handleSecondaryContextualization(findValueDeserializer, beanProperty, javaType) : findValueDeserializer;
    }

    public final JsonDeserializer<Object> findNonContextualValueDeserializer(JavaType javaType) throws JsonMappingException {
        return this._cache.findValueDeserializer(this, this._factory, javaType);
    }

    public final JsonDeserializer<Object> findRootValueDeserializer(JavaType javaType) throws JsonMappingException {
        JsonDeserializer<Object> findValueDeserializer = this._cache.findValueDeserializer(this, this._factory, javaType);
        if (findValueDeserializer == null) {
            return null;
        }
        JsonDeserializer<?> handleSecondaryContextualization = handleSecondaryContextualization(findValueDeserializer, null, javaType);
        TypeDeserializer findTypeDeserializer = this._factory.findTypeDeserializer(this._config, javaType);
        return findTypeDeserializer != null ? new TypeWrappedDeserializer(findTypeDeserializer.forProperty(null), handleSecondaryContextualization) : handleSecondaryContextualization;
    }

    public final KeyDeserializer findKeyDeserializer(JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        KeyDeserializer findKeyDeserializer = this._cache.findKeyDeserializer(this, this._factory, javaType);
        return findKeyDeserializer instanceof ContextualKeyDeserializer ? ((ContextualKeyDeserializer) findKeyDeserializer).createContextual(this, beanProperty) : findKeyDeserializer;
    }

    public final JavaType constructType(Class<?> cls) {
        return this._config.constructType(cls);
    }

    public Class<?> findClass(String str) throws ClassNotFoundException {
        return getTypeFactory().findClass(str);
    }

    public final ObjectBuffer leaseObjectBuffer() {
        ObjectBuffer objectBuffer = this._objectBuffer;
        if (objectBuffer == null) {
            return new ObjectBuffer();
        }
        this._objectBuffer = null;
        return objectBuffer;
    }

    public final void returnObjectBuffer(ObjectBuffer objectBuffer) {
        if (this._objectBuffer == null || objectBuffer.initialCapacity() >= this._objectBuffer.initialCapacity()) {
            this._objectBuffer = objectBuffer;
        }
    }

    public final ArrayBuilders getArrayBuilders() {
        if (this._arrayBuilders == null) {
            this._arrayBuilders = new ArrayBuilders();
        }
        return this._arrayBuilders;
    }

    public JsonDeserializer<?> handlePrimaryContextualization(JsonDeserializer<?> jsonDeserializer, BeanProperty beanProperty, JavaType javaType) throws JsonMappingException {
        if (jsonDeserializer instanceof ContextualDeserializer) {
            this._currentType = new LinkedNode<>(javaType, this._currentType);
            try {
                jsonDeserializer = ((ContextualDeserializer) jsonDeserializer).createContextual(this, beanProperty);
            } finally {
                this._currentType = this._currentType.next();
            }
        }
        return jsonDeserializer;
    }

    public JsonDeserializer<?> handleSecondaryContextualization(JsonDeserializer<?> jsonDeserializer, BeanProperty beanProperty, JavaType javaType) throws JsonMappingException {
        if (jsonDeserializer instanceof ContextualDeserializer) {
            this._currentType = new LinkedNode<>(javaType, this._currentType);
            try {
                jsonDeserializer = ((ContextualDeserializer) jsonDeserializer).createContextual(this, beanProperty);
            } finally {
                this._currentType = this._currentType.next();
            }
        }
        return jsonDeserializer;
    }

    @Deprecated
    public JsonDeserializer<?> handlePrimaryContextualization(JsonDeserializer<?> jsonDeserializer, BeanProperty beanProperty) throws JsonMappingException {
        return handlePrimaryContextualization(jsonDeserializer, beanProperty, TypeFactory.unknownType());
    }

    @Deprecated
    public JsonDeserializer<?> handleSecondaryContextualization(JsonDeserializer<?> jsonDeserializer, BeanProperty beanProperty) throws JsonMappingException {
        return jsonDeserializer instanceof ContextualDeserializer ? ((ContextualDeserializer) jsonDeserializer).createContextual(this, beanProperty) : jsonDeserializer;
    }

    public Date parseDate(String str) throws IllegalArgumentException {
        try {
            return getDateFormat().parse(str);
        } catch (ParseException e) {
            throw new IllegalArgumentException(String.format("Failed to parse Date value '%s': %s", str, e.getMessage()));
        }
    }

    public Calendar constructCalendar(Date date) {
        Calendar calendar = Calendar.getInstance(getTimeZone());
        calendar.setTime(date);
        return calendar;
    }

    public <T> T readValue(JsonParser jsonParser, Class<T> cls) throws IOException {
        return (T) readValue(jsonParser, getTypeFactory().constructType(cls));
    }

    public <T> T readValue(JsonParser jsonParser, JavaType javaType) throws IOException {
        JsonDeserializer<Object> findRootValueDeserializer = findRootValueDeserializer(javaType);
        if (findRootValueDeserializer == null) {
            reportMappingException("Could not find JsonDeserializer for type %s", javaType);
        }
        return (T) findRootValueDeserializer.deserialize(jsonParser, this);
    }

    public <T> T readPropertyValue(JsonParser jsonParser, BeanProperty beanProperty, Class<T> cls) throws IOException {
        return (T) readPropertyValue(jsonParser, beanProperty, getTypeFactory().constructType(cls));
    }

    public <T> T readPropertyValue(JsonParser jsonParser, BeanProperty beanProperty, JavaType javaType) throws IOException {
        String str;
        JsonDeserializer<Object> findContextualValueDeserializer = findContextualValueDeserializer(javaType, beanProperty);
        if (findContextualValueDeserializer == null) {
            if (beanProperty == null) {
                str = "NULL";
            } else {
                str = "'" + beanProperty.getName() + "'";
            }
            reportMappingException("Could not find JsonDeserializer for type %s (via property %s)", javaType, str);
        }
        return (T) findContextualValueDeserializer.deserialize(jsonParser, this);
    }

    public boolean handleUnknownProperty(JsonParser jsonParser, JsonDeserializer<?> jsonDeserializer, Object obj, String str) throws IOException {
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            if (problemHandlers.value().handleUnknownProperty(this, jsonParser, jsonDeserializer, obj, str)) {
                return true;
            }
        }
        if (!isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)) {
            jsonParser.skipChildren();
            return true;
        }
        throw UnrecognizedPropertyException.from(this._parser, obj, str, jsonDeserializer == null ? null : jsonDeserializer.getKnownPropertyNames());
    }

    public Object handleWeirdKey(Class<?> cls, String str, String str2, Object... objArr) throws IOException {
        if (objArr.length > 0) {
            str2 = String.format(str2, objArr);
        }
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            Object handleWeirdKey = problemHandlers.value().handleWeirdKey(this, cls, str, str2);
            if (handleWeirdKey != DeserializationProblemHandler.NOT_HANDLED) {
                if (handleWeirdKey == null || cls.isInstance(handleWeirdKey)) {
                    return handleWeirdKey;
                }
                throw weirdStringException(str, cls, String.format("DeserializationProblemHandler.handleWeirdStringValue() for type %s returned value of type %s", cls, handleWeirdKey.getClass()));
            }
        }
        throw weirdKeyException(cls, str, str2);
    }

    public Object handleWeirdStringValue(Class<?> cls, String str, String str2, Object... objArr) throws IOException {
        if (objArr.length > 0) {
            str2 = String.format(str2, objArr);
        }
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            Object handleWeirdStringValue = problemHandlers.value().handleWeirdStringValue(this, cls, str, str2);
            if (handleWeirdStringValue != DeserializationProblemHandler.NOT_HANDLED) {
                if (handleWeirdStringValue == null || cls.isInstance(handleWeirdStringValue)) {
                    return handleWeirdStringValue;
                }
                throw weirdStringException(str, cls, String.format("DeserializationProblemHandler.handleWeirdStringValue() for type %s returned value of type %s", cls, handleWeirdStringValue.getClass()));
            }
        }
        throw weirdStringException(str, cls, str2);
    }

    public Object handleWeirdNumberValue(Class<?> cls, Number number, String str, Object... objArr) throws IOException {
        if (objArr.length > 0) {
            str = String.format(str, objArr);
        }
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            Object handleWeirdNumberValue = problemHandlers.value().handleWeirdNumberValue(this, cls, number, str);
            if (handleWeirdNumberValue != DeserializationProblemHandler.NOT_HANDLED) {
                if (handleWeirdNumberValue == null || cls.isInstance(handleWeirdNumberValue)) {
                    return handleWeirdNumberValue;
                }
                throw weirdNumberException(number, cls, String.format("DeserializationProblemHandler.handleWeirdNumberValue() for type %s returned value of type %s", cls, handleWeirdNumberValue.getClass()));
            }
        }
        throw weirdNumberException(number, cls, str);
    }

    public Object handleMissingInstantiator(Class<?> cls, JsonParser jsonParser, String str, Object... objArr) throws IOException {
        if (objArr.length > 0) {
            str = String.format(str, objArr);
        }
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            Object handleMissingInstantiator = problemHandlers.value().handleMissingInstantiator(this, cls, jsonParser, str);
            if (handleMissingInstantiator != DeserializationProblemHandler.NOT_HANDLED) {
                if (handleMissingInstantiator == null || cls.isInstance(handleMissingInstantiator)) {
                    return handleMissingInstantiator;
                }
                throw instantiationException(cls, String.format("DeserializationProblemHandler.handleMissingInstantiator() for type %s returned value of type %s", cls, handleMissingInstantiator.getClass()));
            }
        }
        throw instantiationException(cls, str);
    }

    public Object handleInstantiationProblem(Class<?> cls, Object obj, Throwable th) throws IOException {
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            Object handleInstantiationProblem = problemHandlers.value().handleInstantiationProblem(this, cls, obj, th);
            if (handleInstantiationProblem != DeserializationProblemHandler.NOT_HANDLED) {
                if (handleInstantiationProblem == null || cls.isInstance(handleInstantiationProblem)) {
                    return handleInstantiationProblem;
                }
                throw instantiationException(cls, String.format("DeserializationProblemHandler.handleInstantiationProblem() for type %s returned value of type %s", cls, handleInstantiationProblem.getClass()));
            }
        }
        if (th instanceof IOException) {
            throw ((IOException) th);
        }
        throw instantiationException(cls, th);
    }

    public Object handleUnexpectedToken(Class<?> cls, JsonParser jsonParser) throws IOException {
        return handleUnexpectedToken(cls, jsonParser.getCurrentToken(), jsonParser, null, new Object[0]);
    }

    public Object handleUnexpectedToken(Class<?> cls, JsonToken jsonToken, JsonParser jsonParser, String str, Object... objArr) throws IOException {
        if (objArr.length > 0) {
            str = String.format(str, objArr);
        }
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            Object handleUnexpectedToken = problemHandlers.value().handleUnexpectedToken(this, cls, jsonToken, jsonParser, str);
            if (handleUnexpectedToken != DeserializationProblemHandler.NOT_HANDLED) {
                if (handleUnexpectedToken == null || cls.isInstance(handleUnexpectedToken)) {
                    return handleUnexpectedToken;
                }
                reportMappingException("DeserializationProblemHandler.handleUnexpectedToken() for type %s returned value of type %s", cls, handleUnexpectedToken.getClass());
            }
        }
        if (str == null) {
            str = jsonToken == null ? String.format("Unexpected end-of-input when binding data into %s", _calcName(cls)) : String.format("Can not deserialize instance of %s out of %s token", _calcName(cls), jsonToken);
        }
        reportMappingException(str, new Object[0]);
        return null;
    }

    public JavaType handleUnknownTypeId(JavaType javaType, String str, TypeIdResolver typeIdResolver, String str2) throws IOException {
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            JavaType handleUnknownTypeId = problemHandlers.value().handleUnknownTypeId(this, javaType, str, typeIdResolver, str2);
            if (handleUnknownTypeId != null) {
                if (handleUnknownTypeId.hasRawClass(Void.class)) {
                    return null;
                }
                if (handleUnknownTypeId.isTypeOrSubTypeOf(javaType.getRawClass())) {
                    return handleUnknownTypeId;
                }
                throw unknownTypeIdException(javaType, str, "problem handler tried to resolve into non-subtype: " + handleUnknownTypeId);
            }
        }
        if (isEnabled(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE)) {
            throw unknownTypeIdException(javaType, str, str2);
        }
        return null;
    }

    public void reportWrongTokenException(JsonParser jsonParser, JsonToken jsonToken, String str, Object... objArr) throws JsonMappingException {
        if (str != null && objArr.length > 0) {
            str = String.format(str, objArr);
        }
        throw wrongTokenException(jsonParser, jsonToken, str);
    }

    @Deprecated
    public void reportUnknownProperty(Object obj, String str, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        if (isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)) {
            throw UnrecognizedPropertyException.from(this._parser, obj, str, jsonDeserializer == null ? null : jsonDeserializer.getKnownPropertyNames());
        }
    }

    public void reportMappingException(String str, Object... objArr) throws JsonMappingException {
        if (objArr.length > 0) {
            str = String.format(str, objArr);
        }
        throw JsonMappingException.from(getParser(), str);
    }

    public void reportMissingContent(String str, Object... objArr) throws JsonMappingException {
        if (str == null) {
            str = "No content to map due to end-of-input";
        } else if (objArr.length > 0) {
            str = String.format(str, objArr);
        }
        throw JsonMappingException.from(getParser(), str);
    }

    public void reportUnresolvedObjectId(ObjectIdReader objectIdReader, Object obj) throws JsonMappingException {
        throw JsonMappingException.from(getParser(), String.format("No Object Id found for an instance of %s, to assign to property '%s'", obj.getClass().getName(), objectIdReader.propertyName));
    }

    public <T> T reportBadTypeDefinition(BeanDescription beanDescription, String str, Object... objArr) throws JsonMappingException {
        if (objArr != null && objArr.length > 0) {
            str = String.format(str, objArr);
        }
        throw mappingException("Invalid type definition for type %s: %s", beanDescription == null ? "N/A" : _desc(beanDescription.getType().getGenericSignature()), str);
    }

    public <T> T reportBadPropertyDefinition(BeanDescription beanDescription, BeanPropertyDefinition beanPropertyDefinition, String str, Object... objArr) throws JsonMappingException {
        if (objArr != null && objArr.length > 0) {
            str = String.format(str, objArr);
        }
        throw mappingException("Invalid definition for property %s (of type %s): %s", beanPropertyDefinition == null ? "N/A" : _quotedString(beanPropertyDefinition.getName()), beanDescription != null ? _desc(beanDescription.getType().getGenericSignature()) : "N/A", str);
    }

    public JsonMappingException mappingException(String str) {
        return JsonMappingException.from(getParser(), str);
    }

    public JsonMappingException mappingException(String str, Object... objArr) {
        if (objArr != null && objArr.length > 0) {
            str = String.format(str, objArr);
        }
        return JsonMappingException.from(getParser(), str);
    }

    @Deprecated
    public JsonMappingException mappingException(Class<?> cls) {
        return mappingException(cls, this._parser.getCurrentToken());
    }

    @Deprecated
    public JsonMappingException mappingException(Class<?> cls, JsonToken jsonToken) {
        return JsonMappingException.from(this._parser, String.format("Can not deserialize instance of %s out of %s", _calcName(cls), jsonToken == null ? "<end of input>" : String.format("%s token", jsonToken)));
    }

    public JsonMappingException wrongTokenException(JsonParser jsonParser, JsonToken jsonToken, String str) {
        String format = String.format("Unexpected token (%s), expected %s", jsonParser.getCurrentToken(), jsonToken);
        if (str != null) {
            format = format + ": " + str;
        }
        return JsonMappingException.from(jsonParser, format);
    }

    public JsonMappingException weirdKeyException(Class<?> cls, String str, String str2) {
        return InvalidFormatException.from(this._parser, String.format("Can not deserialize Map key of type %s from String %s: %s", cls.getName(), _quotedString(str), str2), str, cls);
    }

    public JsonMappingException weirdStringException(String str, Class<?> cls, String str2) {
        return InvalidFormatException.from(this._parser, String.format("Can not deserialize value of type %s from String %s: %s", cls.getName(), _quotedString(str), str2), str, cls);
    }

    public JsonMappingException weirdNumberException(Number number, Class<?> cls, String str) {
        return InvalidFormatException.from(this._parser, String.format("Can not deserialize value of type %s from number %s: %s", cls.getName(), String.valueOf(number), str), number, cls);
    }

    public JsonMappingException instantiationException(Class<?> cls, Throwable th) {
        return JsonMappingException.from(this._parser, String.format("Can not construct instance of %s, problem: %s", cls.getName(), th.getMessage()), th);
    }

    public JsonMappingException instantiationException(Class<?> cls, String str) {
        return JsonMappingException.from(this._parser, String.format("Can not construct instance of %s: %s", cls.getName(), str));
    }

    public JsonMappingException unknownTypeIdException(JavaType javaType, String str, String str2) {
        String format = String.format("Could not resolve type id '%s' into a subtype of %s", str, javaType);
        if (str2 != null) {
            format = format + ": " + str2;
        }
        return InvalidTypeIdException.from(this._parser, format, javaType, str);
    }

    @Deprecated
    public JsonMappingException unknownTypeException(JavaType javaType, String str, String str2) {
        String format = String.format("Could not resolve type id '%s' into a subtype of %s", str, javaType);
        if (str2 != null) {
            format = format + ": " + str2;
        }
        return JsonMappingException.from(this._parser, format);
    }

    @Deprecated
    public JsonMappingException endOfInputException(Class<?> cls) {
        JsonParser jsonParser = this._parser;
        return JsonMappingException.from(jsonParser, "Unexpected end-of-input when trying to deserialize a " + cls.getName());
    }

    protected DateFormat getDateFormat() {
        DateFormat dateFormat = this._dateFormat;
        if (dateFormat != null) {
            return dateFormat;
        }
        DateFormat dateFormat2 = (DateFormat) this._config.getDateFormat().clone();
        this._dateFormat = dateFormat2;
        return dateFormat2;
    }

    protected String determineClassName(Object obj) {
        return ClassUtil.getClassDescription(obj);
    }

    protected String _calcName(Class<?> cls) {
        if (cls.isArray()) {
            return _calcName(cls.getComponentType()) + HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
        }
        return cls.getName();
    }

    protected String _valueDesc() {
        try {
            return _desc(this._parser.getText());
        } catch (Exception unused) {
            return "[N/A]";
        }
    }

    protected String _desc(String str) {
        if (str == null) {
            return "[N/A]";
        }
        if (str.length() > 500) {
            return str.substring(0, 500) + "]...[" + str.substring(str.length() - 500);
        }
        return str;
    }

    protected String _quotedString(String str) {
        if (str == null) {
            return "[N/A]";
        }
        if (str.length() > 500) {
            return String.format("\"%s]...[%s\"", str.substring(0, 500), str.substring(str.length() - 500));
        }
        return "\"" + str + "\"";
    }
}
