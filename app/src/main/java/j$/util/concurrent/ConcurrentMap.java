package j$.util.concurrent;

import j$.util.Map;
import j$.util.Objects;
import j$.util.concurrent.ConcurrentMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
public interface ConcurrentMap<K, V> extends Map<K, V> {

    public final class -CC {
        public static Object $default$compute(java.util.concurrent.ConcurrentMap concurrentMap, Object obj, BiFunction biFunction) {
            while (true) {
                Object obj2 = concurrentMap.get(obj);
                while (true) {
                    Object apply = biFunction.apply(obj, obj2);
                    if (apply != null) {
                        if (obj2 == null) {
                            obj2 = concurrentMap.putIfAbsent(obj, apply);
                            if (obj2 == null) {
                                return apply;
                            }
                        } else if (concurrentMap.replace(obj, obj2, apply)) {
                            return apply;
                        }
                    } else if (obj2 == null || concurrentMap.remove(obj, obj2)) {
                        return null;
                    }
                }
            }
        }

        public static Object $default$computeIfAbsent(java.util.concurrent.ConcurrentMap concurrentMap, Object obj, Function function) {
            Object apply;
            Objects.requireNonNull(function);
            Object obj2 = concurrentMap.get(obj);
            return (obj2 == null && (apply = function.apply(obj)) != null && (obj2 = concurrentMap.putIfAbsent(obj, apply)) == null) ? apply : obj2;
        }

        public static Object $default$computeIfPresent(java.util.concurrent.ConcurrentMap concurrentMap, Object obj, BiFunction biFunction) {
            Object apply;
            Objects.requireNonNull(biFunction);
            while (true) {
                Object obj2 = concurrentMap.get(obj);
                if (obj2 == null) {
                    return null;
                }
                apply = biFunction.apply(obj, obj2);
                if (apply == null) {
                    if (concurrentMap.remove(obj, obj2)) {
                        break;
                    }
                } else if (concurrentMap.replace(obj, obj2, apply)) {
                    break;
                }
            }
            return apply;
        }

        public static void $default$forEach(java.util.concurrent.ConcurrentMap concurrentMap, BiConsumer biConsumer) {
            Objects.requireNonNull(biConsumer);
            for (Map.Entry<K, V> entry : concurrentMap.entrySet()) {
                try {
                    biConsumer.accept(entry.getKey(), entry.getValue());
                } catch (IllegalStateException unused) {
                }
            }
        }

        public static Object $default$merge(java.util.concurrent.ConcurrentMap concurrentMap, Object obj, Object obj2, BiFunction biFunction) {
            Objects.requireNonNull(biFunction);
            Objects.requireNonNull(obj2);
            while (true) {
                Object obj3 = concurrentMap.get(obj);
                while (obj3 == null) {
                    obj3 = concurrentMap.putIfAbsent(obj, obj2);
                    if (obj3 == null) {
                        return obj2;
                    }
                }
                Object apply = biFunction.apply(obj3, obj2);
                if (apply != null) {
                    if (concurrentMap.replace(obj, obj3, apply)) {
                        return apply;
                    }
                } else if (concurrentMap.remove(obj, obj3)) {
                    return null;
                }
            }
        }

        public static void $default$replaceAll(final java.util.concurrent.ConcurrentMap concurrentMap, final BiFunction biFunction) {
            Objects.requireNonNull(biFunction);
            -EL.forEach(concurrentMap, new BiConsumer() {
                @Override
                public final void accept(Object obj, Object obj2) {
                    ConcurrentMap.-CC.$private$lambda$replaceAll$0(concurrentMap, biFunction, obj, obj2);
                }

                public BiConsumer andThen(BiConsumer biConsumer) {
                    return Objects.requireNonNull(biConsumer);
                }
            });
        }

        public static void $private$lambda$replaceAll$0(java.util.concurrent.ConcurrentMap concurrentMap, BiFunction biFunction, Object obj, Object obj2) {
            while (!concurrentMap.replace(obj, obj2, biFunction.apply(obj, obj2)) && (obj2 = concurrentMap.get(obj)) != null) {
            }
        }
    }

    public abstract class -EL {
        public static void forEach(java.util.concurrent.ConcurrentMap concurrentMap, BiConsumer biConsumer) {
            if (concurrentMap instanceof ConcurrentMap) {
                ((ConcurrentMap) concurrentMap).forEach(biConsumer);
            } else {
                -CC.$default$forEach(concurrentMap, biConsumer);
            }
        }
    }

    @Override
    V compute(K k, BiFunction<? super K, ? super V, ? extends V> biFunction);

    @Override
    V computeIfAbsent(K k, Function<? super K, ? extends V> function);

    @Override
    V computeIfPresent(K k, BiFunction<? super K, ? super V, ? extends V> biFunction);

    @Override
    void forEach(BiConsumer<? super K, ? super V> biConsumer);

    @Override
    V getOrDefault(Object obj, V v);

    @Override
    V merge(K k, V v, BiFunction<? super V, ? super V, ? extends V> biFunction);

    @Override
    void replaceAll(BiFunction<? super K, ? super V, ? extends V> biFunction);
}
