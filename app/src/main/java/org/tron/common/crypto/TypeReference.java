package org.tron.common.crypto;

import java.lang.reflect.ParameterizedType;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.tron.common.crypto.datatypes.AbiTypes;
import org.tron.common.crypto.datatypes.DynamicArray;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.Type;
public abstract class TypeReference<T extends Type> implements Comparable<TypeReference<T>> {
    protected static Pattern ARRAY_SUFFIX = Pattern.compile("\\[(\\d*)]");
    private final boolean indexed;
    private final java.lang.reflect.Type type;

    public int compareTo(TypeReference<T> typeReference) {
        return 0;
    }

    public TypeReference getSubTypeReference() {
        return null;
    }

    public java.lang.reflect.Type getType() {
        return this.type;
    }

    public boolean isIndexed() {
        return this.indexed;
    }

    @Override
    public int compareTo(Object obj) {
        return compareTo((TypeReference) ((TypeReference) obj));
    }

    public TypeReference() {
        this(false);
    }

    protected TypeReference(boolean z) {
        java.lang.reflect.Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        this.type = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
        this.indexed = z;
    }

    public Class<T> getClassType() throws ClassNotFoundException {
        java.lang.reflect.Type type = getType();
        if (getType() instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) type).getRawType();
        }
        return (Class<T>) Class.forName(Utils.getTypeName(type));
    }

    public static <T extends Type> TypeReference<T> create(Class<T> cls) {
        return create(cls, false);
    }

    public static <T extends Type> TypeReference<T> create(final Class<T> cls, boolean z) {
        return (TypeReference<T>) new TypeReference<T>(z) {
            @Override
            public java.lang.reflect.Type getType() {
                return cls;
            }

            @Override
            public int compareTo(Object obj) {
                return super.compareTo((TypeReference) ((TypeReference) obj));
            }
        };
    }

    protected static Class<? extends Type> getAtomicTypeClass(String str, boolean z) throws ClassNotFoundException {
        if (ARRAY_SUFFIX.matcher(str).find()) {
            throw new ClassNotFoundException("getAtomicTypeClass does not work with array types. See makeTypeReference()");
        }
        return AbiTypes.getType(str, z);
    }

    public static abstract class StaticArrayTypeReference<T extends Type> extends TypeReference<T> {
        private final int size;

        public int getSize() {
            return this.size;
        }

        @Override
        public int compareTo(Object obj) {
            return super.compareTo((TypeReference) ((TypeReference) obj));
        }

        protected StaticArrayTypeReference(int i) {
            this.size = i;
        }
    }

    public static TypeReference makeTypeReference(String str) throws ClassNotFoundException {
        return makeTypeReference(str, false, false);
    }

    public static TypeReference makeTypeReference(String str, final boolean z, boolean z2) throws ClassNotFoundException {
        final Class cls;
        Matcher matcher = ARRAY_SUFFIX.matcher(str);
        if (!matcher.find()) {
            return create(getAtomicTypeClass(str, z2), z);
        }
        int start = matcher.start();
        final TypeReference<DynamicArray> create = create(getAtomicTypeClass(str.substring(0, start), z2), z);
        int length = str.length();
        while (start < length) {
            String group = matcher.group(1);
            if (group == null || group.equals("")) {
                create = new TypeReference<DynamicArray>(z) {
                    @Override
                    TypeReference getSubTypeReference() {
                        return create;
                    }

                    @Override
                    public int compareTo(Object obj) {
                        return super.compareTo((TypeReference) ((TypeReference) obj));
                    }

                    @Override
                    public java.lang.reflect.Type getType() {
                        return new ParameterizedType() {
                            @Override
                            public java.lang.reflect.Type[] getActualTypeArguments() {
                                return new java.lang.reflect.Type[]{create.getType()};
                            }

                            @Override
                            public java.lang.reflect.Type getRawType() {
                                return DynamicArray.class;
                            }

                            @Override
                            public java.lang.reflect.Type getOwnerType() {
                                return Class.class;
                            }
                        };
                    }
                };
            } else {
                int parseInt = Integer.parseInt(group);
                if (parseInt <= 32) {
                    cls = Class.forName("org.web3j.abi.datatypes.generated.StaticArray" + group);
                } else {
                    cls = StaticArray.class;
                }
                create = new StaticArrayTypeReference<StaticArray>(parseInt) {
                    @Override
                    TypeReference getSubTypeReference() {
                        return create;
                    }

                    @Override
                    public boolean isIndexed() {
                        return z;
                    }

                    @Override
                    public java.lang.reflect.Type getType() {
                        return new ParameterizedType() {
                            @Override
                            public java.lang.reflect.Type[] getActualTypeArguments() {
                                return new java.lang.reflect.Type[]{create.getType()};
                            }

                            @Override
                            public java.lang.reflect.Type getRawType() {
                                return cls;
                            }

                            @Override
                            public java.lang.reflect.Type getOwnerType() {
                                return Class.class;
                            }
                        };
                    }
                };
            }
            start = matcher.end();
            matcher = ARRAY_SUFFIX.matcher(str);
            if (!matcher.find(start) && start != length) {
                throw new ClassNotFoundException("Unable to make TypeReference from " + str);
            }
        }
        return create;
    }
}
