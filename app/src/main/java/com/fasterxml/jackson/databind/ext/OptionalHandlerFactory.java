package com.fasterxml.jackson.databind.ext;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.ser.Serializers;
import java.io.Serializable;
public class OptionalHandlerFactory implements Serializable {
    private static final Class<?> CLASS_DOM_DOCUMENT;
    private static final Class<?> CLASS_DOM_NODE;
    private static final String DESERIALIZERS_FOR_JAVAX_XML = "com.fasterxml.jackson.databind.ext.CoreXMLDeserializers";
    private static final String DESERIALIZER_FOR_DOM_DOCUMENT = "com.fasterxml.jackson.databind.ext.DOMDeserializer$DocumentDeserializer";
    private static final String DESERIALIZER_FOR_DOM_NODE = "com.fasterxml.jackson.databind.ext.DOMDeserializer$NodeDeserializer";
    private static final String PACKAGE_PREFIX_JAVAX_XML = "javax.xml.";
    private static final String SERIALIZERS_FOR_JAVAX_XML = "com.fasterxml.jackson.databind.ext.CoreXMLSerializers";
    private static final String SERIALIZER_FOR_DOM_NODE = "com.fasterxml.jackson.databind.ext.DOMSerializer";
    private static final Java7Support _jdk7Helper;
    public static final OptionalHandlerFactory instance;
    private static final long serialVersionUID = 1;

    static {
        


return 1;

//throw new UnsupportedOperationException(
Method not decompiled: com.fasterxml.jackson.databind.ext.OptionalHandlerFactory.<clinit>():void");
    }

    protected OptionalHandlerFactory() {
    }

    public JsonSerializer<?> findSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription) {
        Object instantiate;
        JsonSerializer<?> serializerForJavaNioFilePath;
        Class<?> rawClass = javaType.getRawClass();
        Java7Support java7Support = _jdk7Helper;
        if (java7Support == null || (serializerForJavaNioFilePath = java7Support.getSerializerForJavaNioFilePath(rawClass)) == null) {
            Class<?> cls = CLASS_DOM_NODE;
            if (cls != null && cls.isAssignableFrom(rawClass)) {
                return (JsonSerializer) instantiate(SERIALIZER_FOR_DOM_NODE);
            }
            if ((rawClass.getName().startsWith(PACKAGE_PREFIX_JAVAX_XML) || hasSuperClassStartingWith(rawClass, PACKAGE_PREFIX_JAVAX_XML)) && (instantiate = instantiate(SERIALIZERS_FOR_JAVAX_XML)) != null) {
                return ((Serializers) instantiate).findSerializer(serializationConfig, javaType, beanDescription);
            }
            return null;
        }
        return serializerForJavaNioFilePath;
    }

    public JsonDeserializer<?> findDeserializer(JavaType javaType, DeserializationConfig deserializationConfig, BeanDescription beanDescription) throws JsonMappingException {
        Object instantiate;
        JsonDeserializer<?> deserializerForJavaNioFilePath;
        Class<?> rawClass = javaType.getRawClass();
        Java7Support java7Support = _jdk7Helper;
        if (java7Support == null || (deserializerForJavaNioFilePath = java7Support.getDeserializerForJavaNioFilePath(rawClass)) == null) {
            Class<?> cls = CLASS_DOM_NODE;
            if (cls != null && cls.isAssignableFrom(rawClass)) {
                return (JsonDeserializer) instantiate(DESERIALIZER_FOR_DOM_NODE);
            }
            Class<?> cls2 = CLASS_DOM_DOCUMENT;
            if (cls2 != null && cls2.isAssignableFrom(rawClass)) {
                return (JsonDeserializer) instantiate(DESERIALIZER_FOR_DOM_DOCUMENT);
            }
            if ((rawClass.getName().startsWith(PACKAGE_PREFIX_JAVAX_XML) || hasSuperClassStartingWith(rawClass, PACKAGE_PREFIX_JAVAX_XML)) && (instantiate = instantiate(DESERIALIZERS_FOR_JAVAX_XML)) != null) {
                return ((Deserializers) instantiate).findBeanDeserializer(javaType, deserializationConfig, beanDescription);
            }
            return null;
        }
        return deserializerForJavaNioFilePath;
    }

    private Object instantiate(String str) {
        try {
            return Class.forName(str).newInstance();
        } catch (Exception | LinkageError unused) {
            return null;
        }
    }

    private boolean hasSuperClassStartingWith(Class<?> cls, String str) {
        do {
            cls = cls.getSuperclass();
            if (cls == null || cls == Object.class) {
                return false;
            }
        } while (!cls.getName().startsWith(str));
        return true;
    }
}
