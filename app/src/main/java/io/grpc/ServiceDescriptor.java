package io.grpc;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import javax.annotation.Nullable;
public final class ServiceDescriptor {
    private final Collection<MethodDescriptor<?, ?>> methods;
    private final String name;
    private final Object schemaDescriptor;

    public Collection<MethodDescriptor<?, ?>> getMethods() {
        return this.methods;
    }

    public String getName() {
        return this.name;
    }

    @Nullable
    public Object getSchemaDescriptor() {
        return this.schemaDescriptor;
    }

    public ServiceDescriptor(String str, MethodDescriptor<?, ?>... methodDescriptorArr) {
        this(str, Arrays.asList(methodDescriptorArr));
    }

    public ServiceDescriptor(String str, Collection<MethodDescriptor<?, ?>> collection) {
        this(newBuilder(str).addAllMethods((Collection) Preconditions.checkNotNull(collection, "methods")));
    }

    private ServiceDescriptor(Builder builder) {
        String str = builder.name;
        this.name = str;
        validateMethodNames(str, builder.methods);
        this.methods = Collections.unmodifiableList(new ArrayList(builder.methods));
        this.schemaDescriptor = builder.schemaDescriptor;
    }

    static void validateMethodNames(String str, Collection<MethodDescriptor<?, ?>> collection) {
        HashSet hashSet = new HashSet(collection.size());
        for (MethodDescriptor<?, ?> methodDescriptor : collection) {
            Preconditions.checkNotNull(methodDescriptor, FirebaseAnalytics.Param.METHOD);
            String serviceName = methodDescriptor.getServiceName();
            Preconditions.checkArgument(str.equals(serviceName), "service names %s != %s", serviceName, str);
            Preconditions.checkArgument(hashSet.add(methodDescriptor.getFullMethodName()), "duplicate name %s", methodDescriptor.getFullMethodName());
        }
    }

    public static Builder newBuilder(String str) {
        return new Builder(str);
    }

    public static final class Builder {
        private List<MethodDescriptor<?, ?>> methods;
        private String name;
        private Object schemaDescriptor;

        public Builder setSchemaDescriptor(@Nullable Object obj) {
            this.schemaDescriptor = obj;
            return this;
        }

        private Builder(String str) {
            this.methods = new ArrayList();
            setName(str);
        }

        public Builder setName(String str) {
            this.name = (String) Preconditions.checkNotNull(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
            return this;
        }

        public Builder addMethod(MethodDescriptor<?, ?> methodDescriptor) {
            this.methods.add(Preconditions.checkNotNull(methodDescriptor, FirebaseAnalytics.Param.METHOD));
            return this;
        }

        public Builder addAllMethods(Collection<MethodDescriptor<?, ?>> collection) {
            this.methods.addAll(collection);
            return this;
        }

        public ServiceDescriptor build() {
            return new ServiceDescriptor(this);
        }
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add(AppMeasurementSdk.ConditionalUserProperty.NAME, this.name).add("schemaDescriptor", this.schemaDescriptor).add("methods", this.methods).omitNullValues().toString();
    }
}
