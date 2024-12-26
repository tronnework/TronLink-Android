package org.tron.common.crypto;

import android.os.Build;
import j$.util.Collection;
import j$.util.DesugarArrays;
import j$.util.Objects;
import j$.util.function.Predicate$-CC;
import j$.util.stream.Collectors;
import j$.util.stream.Stream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import okhttp3.HttpUrl;
import org.tron.common.crypto.TypeReference;
import org.tron.common.crypto.datatypes.DynamicArray;
import org.tron.common.crypto.datatypes.DynamicBytes;
import org.tron.common.crypto.datatypes.Fixed;
import org.tron.common.crypto.datatypes.Int;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.StaticStruct;
import org.tron.common.crypto.datatypes.StructType;
import org.tron.common.crypto.datatypes.Type;
import org.tron.common.crypto.datatypes.Ufixed;
import org.tron.common.crypto.datatypes.Uint;
import org.tron.common.crypto.datatypes.Utf8String;
public class Utils {
    public static TypeReference lambda$convert$0(TypeReference typeReference) {
        return typeReference;
    }

    private Utils() {
    }

    static <T extends Type> String getTypeName(TypeReference<T> typeReference) {
        try {
            java.lang.reflect.Type type = typeReference.getType();
            if (type instanceof ParameterizedType) {
                return getParameterizedTypeName(typeReference, (Class) ((ParameterizedType) type).getRawType());
            }
            return getSimpleTypeName(Class.forName(getTypeName(type)));
        } catch (ClassNotFoundException e) {
            


return;

//throw new UnsupportedOperationException(
Invalid class reference provided", e);
        }
    }

    public static String getSimpleTypeName(Class<?> cls) {
        String lowerCase = cls.getSimpleName().toLowerCase();
        if (!cls.equals(Uint.class) && !cls.equals(Int.class) && !cls.equals(Ufixed.class) && !cls.equals(Fixed.class)) {
            return cls.equals(Utf8String.class) ? Utf8String.TYPE_NAME : cls.equals(DynamicBytes.class) ? "bytes" : StructType.class.isAssignableFrom(cls) ? cls.getName() : lowerCase;
        }
        return lowerCase + "256";
    }

    static <T extends Type, U extends Type> String getParameterizedTypeName(TypeReference<T> typeReference, Class<?> cls) {
        try {
            if (cls.equals(DynamicArray.class)) {
                String simpleTypeName = getSimpleTypeName(getParameterizedTypeFromArray(typeReference));
                return simpleTypeName + HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
            } else if (cls.equals(StaticArray.class)) {
                String simpleTypeName2 = getSimpleTypeName(getParameterizedTypeFromArray(typeReference));
                return simpleTypeName2 + "[" + ((TypeReference.StaticArrayTypeReference) typeReference).getSize() + "]";
            } else {
                


return null;

//throw new UnsupportedOperationException(
Invalid type provided " + cls.getName());
            }
        } catch (ClassNotFoundException e) {
            


return null;

//throw new UnsupportedOperationException(
Invalid class reference provided", e);
        }
    }

    public static <T extends Type> Class<T> getParameterizedTypeFromArray(TypeReference typeReference) throws ClassNotFoundException {
        return (Class<T>) Class.forName(getTypeName(((ParameterizedType) typeReference.getType()).getActualTypeArguments()[0]));
    }

    public static List<TypeReference<Type>> convert(List<TypeReference<?>> list) {
        ArrayList arrayList = new ArrayList(list.size());
        arrayList.addAll((Collection) Collection.-EL.stream(list).map(new Function() {
            public Function andThen(Function function) {
                return Objects.requireNonNull(function);
            }

            @Override
            public final Object apply(Object obj) {
                return Utils.lambda$convert$0((TypeReference) obj);
            }

            public Function compose(Function function) {
                return Objects.requireNonNull(function);
            }
        }).collect(Collectors.toList()));
        return arrayList;
    }

    public static <T, R extends Type<T>, E extends Type<T>> List<E> typeMap(List<List<T>> list, Class<E> cls, Class<R> cls2) {
        ArrayList arrayList = new ArrayList();
        try {
            Constructor<E> declaredConstructor = cls.getDeclaredConstructor(Class.class, List.class);
            Iterator<List<T>> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(declaredConstructor.newInstance(cls2, typeMap(it.next(), cls2)));
            }
            return arrayList;
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new TypeMappingException(e);
        }
    }

    public static <T, R extends Type<T>> List<R> typeMap(List<T> list, Class<R> cls) throws TypeMappingException {
        ArrayList arrayList = new ArrayList(list.size());
        if (!list.isEmpty()) {
            try {
                Constructor<R> declaredConstructor = cls.getDeclaredConstructor(list.get(0).getClass());
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(declaredConstructor.newInstance(it.next()));
                }
            } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                throw new TypeMappingException(e);
            }
        }
        return arrayList;
    }

    public static List<Field> staticStructNestedPublicFieldsFlatList(Class<Type> cls) {
        return (List) Collection.-EL.stream(staticStructsNestedFieldsFlatList(cls)).filter(new Predicate() {
            public Predicate and(Predicate predicate) {
                return Objects.requireNonNull(predicate);
            }

            public Predicate negate() {
                return Predicate$-CC.$default$negate(this);
            }

            public Predicate or(Predicate predicate) {
                return Objects.requireNonNull(predicate);
            }

            @Override
            public final boolean test(Object obj) {
                boolean isPublic;
                isPublic = Modifier.isPublic(((Field) obj).getModifiers());
                return isPublic;
            }
        }).collect(Collectors.toList());
    }

    public static List<Field> staticStructsNestedFieldsFlatList(Class<Type> cls) {
        return (List) Stream.-CC.concat(Collection.-EL.stream((List) DesugarArrays.stream(cls.getDeclaredFields()).filter(new Predicate() {
            public Predicate and(Predicate predicate) {
                return Objects.requireNonNull(predicate);
            }

            public Predicate negate() {
                return Predicate$-CC.$default$negate(this);
            }

            public Predicate or(Predicate predicate) {
                return Objects.requireNonNull(predicate);
            }

            @Override
            public final boolean test(Object obj) {
                return Utils.lambda$staticStructsNestedFieldsFlatList$2((Field) obj);
            }
        }).collect(Collectors.toList())), Collection.-EL.stream((List) DesugarArrays.stream(cls.getDeclaredFields()).filter(new Predicate() {
            public Predicate and(Predicate predicate) {
                return Objects.requireNonNull(predicate);
            }

            public Predicate negate() {
                return Predicate$-CC.$default$negate(this);
            }

            public Predicate or(Predicate predicate) {
                return Objects.requireNonNull(predicate);
            }

            @Override
            public final boolean test(Object obj) {
                boolean isAssignableFrom;
                isAssignableFrom = StaticStruct.class.isAssignableFrom(((Field) obj).getType());
                return isAssignableFrom;
            }
        }).map(new Function() {
            public Function andThen(Function function) {
                return Objects.requireNonNull(function);
            }

            @Override
            public final Object apply(Object obj) {
                List staticStructsNestedFieldsFlatList;
                staticStructsNestedFieldsFlatList = Utils.staticStructsNestedFieldsFlatList(((Field) obj).getType());
                return staticStructsNestedFieldsFlatList;
            }

            public Function compose(Function function) {
                return Objects.requireNonNull(function);
            }
        }).flatMap(new Function() {
            public Function andThen(Function function) {
                return Objects.requireNonNull(function);
            }

            @Override
            public final Object apply(Object obj) {
                return Collection.-EL.stream((List) obj);
            }

            public Function compose(Function function) {
                return Objects.requireNonNull(function);
            }
        }).collect(Collectors.toList()))).collect(Collectors.toList());
    }

    public static boolean lambda$staticStructsNestedFieldsFlatList$2(Field field) {
        return !StaticStruct.class.isAssignableFrom(field.getType());
    }

    public static String getTypeName(java.lang.reflect.Type type) {
        String typeName;
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                typeName = type.getTypeName();
                return typeName;
            }
            return getClassName((Class) type);
        } catch (NoSuchMethodError unused) {
            return getClassName((Class) type);
        }
    }

    private static String getClassName(Class cls) {
        if (cls.isArray()) {
            int i = 0;
            Class cls2 = cls;
            while (cls2.isArray()) {
                try {
                    i++;
                    cls2 = cls2.getComponentType();
                } catch (Throwable unused) {
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append(cls2.getName());
            for (int i2 = 0; i2 < i; i2++) {
                sb.append(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
            }
            return sb.toString();
        }
        return cls.getName();
    }
}
