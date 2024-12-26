package org.tron.common.crypto.datatypes;

import j$.util.Collection;
import j$.util.Objects;
import j$.util.function.Predicate$-CC;
import j$.util.stream.Collectors;
import java.util.List;
import java.util.function.Predicate;
import org.tron.common.crypto.TypeReference;
import org.tron.common.crypto.Utils;
public class Event {
    private String name;
    private List<TypeReference<Type>> parameters;

    public String getName() {
        return this.name;
    }

    public List<TypeReference<Type>> getParameters() {
        return this.parameters;
    }

    public Event(String str, List<TypeReference<?>> list) {
        this.name = str;
        this.parameters = Utils.convert(list);
    }

    public List<TypeReference<Type>> getIndexedParameters() {
        return (List) Collection.-EL.stream(this.parameters).filter(new Predicate() {
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
                return ((TypeReference) obj).isIndexed();
            }
        }).collect(Collectors.toList());
    }

    public static boolean lambda$getNonIndexedParameters$0(TypeReference typeReference) {
        return !typeReference.isIndexed();
    }

    public List<TypeReference<Type>> getNonIndexedParameters() {
        return (List) Collection.-EL.stream(this.parameters).filter(new Predicate() {
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
                return Event.lambda$getNonIndexedParameters$0((TypeReference) obj);
            }
        }).collect(Collectors.toList());
    }
}
