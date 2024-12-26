package j$.util.stream;

import j$.util.Map;
import j$.util.Objects;
import j$.util.StringJoiner;
import j$.util.stream.Collector;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
public final class Collectors {
    static final Set CH_CONCURRENT_ID;
    static final Set CH_CONCURRENT_NOID;
    static final Set CH_ID;
    static final Set CH_NOID;
    static final Set CH_UNORDERED_ID;
    static final Set CH_UNORDERED_NOID;

    public static class CollectorImpl implements Collector {
        private final BiConsumer accumulator;
        private final Set characteristics;
        private final BinaryOperator combiner;
        private final Function finisher;
        private final Supplier supplier;

        CollectorImpl(Supplier supplier, BiConsumer biConsumer, BinaryOperator binaryOperator, Set set) {
            this(supplier, biConsumer, binaryOperator, Collectors.castingIdentity(), set);
        }

        CollectorImpl(Supplier supplier, BiConsumer biConsumer, BinaryOperator binaryOperator, Function function, Set set) {
            this.supplier = supplier;
            this.accumulator = biConsumer;
            this.combiner = binaryOperator;
            this.finisher = function;
            this.characteristics = set;
        }

        @Override
        public BiConsumer accumulator() {
            return this.accumulator;
        }

        @Override
        public Set characteristics() {
            return this.characteristics;
        }

        @Override
        public BinaryOperator combiner() {
            return this.combiner;
        }

        @Override
        public Function finisher() {
            return this.finisher;
        }

        @Override
        public Supplier supplier() {
            return this.supplier;
        }
    }

    static {
        Collector.Characteristics characteristics = Collector.Characteristics.CONCURRENT;
        Collector.Characteristics characteristics2 = Collector.Characteristics.UNORDERED;
        Collector.Characteristics characteristics3 = Collector.Characteristics.IDENTITY_FINISH;
        CH_CONCURRENT_ID = Collections.unmodifiableSet(EnumSet.of(characteristics, characteristics2, characteristics3));
        CH_CONCURRENT_NOID = Collections.unmodifiableSet(EnumSet.of(characteristics, characteristics2));
        CH_ID = Collections.unmodifiableSet(EnumSet.of(characteristics3));
        CH_UNORDERED_ID = Collections.unmodifiableSet(EnumSet.of(characteristics2, characteristics3));
        CH_NOID = Collections.emptySet();
        CH_UNORDERED_NOID = Collections.unmodifiableSet(EnumSet.of(characteristics2));
    }

    public static Function castingIdentity() {
        return new Function() {
            public Function andThen(Function function) {
                return Objects.requireNonNull(function);
            }

            @Override
            public final Object apply(Object obj) {
                return Collectors.lambda$castingIdentity$2(obj);
            }

            public Function compose(Function function) {
                return Objects.requireNonNull(function);
            }
        };
    }

    public static double computeFinalSum(double[] dArr) {
        double d = dArr[0] + dArr[1];
        double d2 = dArr[dArr.length - 1];
        return (Double.isNaN(d) && Double.isInfinite(d2)) ? d2 : d;
    }

    public static <T, K> Collector<T, ?, Map<K, List<T>>> groupingBy(Function<? super T, ? extends K> function) {
        return groupingBy(function, toList());
    }

    public static Collector groupingBy(Function function, Collector collector) {
        return groupingBy(function, new Supplier() {
            @Override
            public final Object get() {
                return new HashMap();
            }
        }, collector);
    }

    public static Collector groupingBy(final Function function, Supplier supplier, Collector collector) {
        final Supplier supplier2 = collector.supplier();
        final BiConsumer accumulator = collector.accumulator();
        BiConsumer biConsumer = new BiConsumer() {
            @Override
            public final void accept(Object obj, Object obj2) {
                accumulator.accept(Map.-EL.computeIfAbsent((java.util.Map) obj, Objects.requireNonNull(Function.this.apply(obj2), "element cannot be mapped to a null key"), new Function() {
                    public Function andThen(Function function2) {
                        return Objects.requireNonNull(function2);
                    }

                    @Override
                    public final Object apply(Object obj3) {
                        Object obj4;
                        obj4 = Supplier.this.get();
                        return obj4;
                    }

                    public Function compose(Function function2) {
                        return Objects.requireNonNull(function2);
                    }
                }), obj2);
            }

            public BiConsumer andThen(BiConsumer biConsumer2) {
                return Objects.requireNonNull(biConsumer2);
            }
        };
        BinaryOperator mapMerger = mapMerger(collector.combiner());
        if (collector.characteristics().contains(Collector.Characteristics.IDENTITY_FINISH)) {
            return new CollectorImpl(supplier, biConsumer, mapMerger, CH_ID);
        }
        final Function finisher = collector.finisher();
        return new CollectorImpl(supplier, biConsumer, mapMerger, new Function() {
            public Function andThen(Function function2) {
                return Objects.requireNonNull(function2);
            }

            @Override
            public final Object apply(Object obj) {
                return Map.-EL.replaceAll((java.util.Map) obj, new BiFunction() {
                    public BiFunction andThen(Function function2) {
                        return Objects.requireNonNull(function2);
                    }

                    @Override
                    public final Object apply(Object obj2, Object obj3) {
                        Object apply;
                        apply = Function.this.apply(obj3);
                        return apply;
                    }
                });
            }

            public Function compose(Function function2) {
                return Objects.requireNonNull(function2);
            }
        }, CH_NOID);
    }

    public static Collector<CharSequence, ?, String> joining(CharSequence charSequence) {
        return joining(charSequence, "", "");
    }

    public static Collector joining(final CharSequence charSequence, final CharSequence charSequence2, final CharSequence charSequence3) {
        return new CollectorImpl(new Supplier() {
            @Override
            public final Object get() {
                return Collectors.lambda$joining$11(charSequence, charSequence2, charSequence3);
            }
        }, new BiConsumer() {
            @Override
            public final void accept(Object obj, Object obj2) {
                ((StringJoiner) obj).add((CharSequence) obj2);
            }

            public BiConsumer andThen(BiConsumer biConsumer) {
                return Objects.requireNonNull(biConsumer);
            }
        }, new BinaryOperator() {
            public BiFunction andThen(Function function) {
                return Objects.requireNonNull(function);
            }

            @Override
            public final Object apply(Object obj, Object obj2) {
                return ((StringJoiner) obj).merge((StringJoiner) obj2);
            }
        }, new Function() {
            public Function andThen(Function function) {
                return Objects.requireNonNull(function);
            }

            @Override
            public final Object apply(Object obj) {
                return ((StringJoiner) obj).toString();
            }

            public Function compose(Function function) {
                return Objects.requireNonNull(function);
            }
        }, CH_NOID);
    }

    public static Object lambda$castingIdentity$2(Object obj) {
        return obj;
    }

    public static StringJoiner lambda$joining$11(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        return new StringJoiner(charSequence, charSequence2, charSequence3);
    }

    public static java.util.Map lambda$mapMerger$12(BinaryOperator binaryOperator, java.util.Map map, java.util.Map map2) {
        for (Map.Entry entry : map2.entrySet()) {
            Map.-EL.merge(map, entry.getKey(), entry.getValue(), binaryOperator);
        }
        return map;
    }

    private static BinaryOperator mapMerger(final BinaryOperator binaryOperator) {
        return new BinaryOperator() {
            public BiFunction andThen(Function function) {
                return Objects.requireNonNull(function);
            }

            @Override
            public final Object apply(Object obj, Object obj2) {
                return Collectors.lambda$mapMerger$12(BinaryOperator.this, (java.util.Map) obj, (java.util.Map) obj2);
            }
        };
    }

    public static double[] sumWithCompensation(double[] dArr, double d) {
        double d2 = d - dArr[1];
        double d3 = dArr[0];
        double d4 = d3 + d2;
        dArr[1] = (d4 - d3) - d2;
        dArr[0] = d4;
        return dArr;
    }

    public static <T> Collector<T, ?, List<T>> toList() {
        return new CollectorImpl(new Supplier() {
            @Override
            public final Object get() {
                return new ArrayList();
            }
        }, new BiConsumer() {
            @Override
            public final void accept(Object obj, Object obj2) {
                ((List) obj).add(obj2);
            }

            public BiConsumer andThen(BiConsumer biConsumer) {
                return Objects.requireNonNull(biConsumer);
            }
        }, new BinaryOperator() {
            public BiFunction andThen(Function function) {
                return Objects.requireNonNull(function);
            }

            @Override
            public final Object apply(Object obj, Object obj2) {
                return ((List) obj).addAll((List) obj2);
            }
        }, CH_ID);
    }
}
