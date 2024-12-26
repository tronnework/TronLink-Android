package io.grpc.internal;

import io.grpc.HandlerRegistry;
import io.grpc.ServerMethodDefinition;
import io.grpc.ServerServiceDefinition;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
final class InternalHandlerRegistry extends HandlerRegistry {
    private final Map<String, ServerMethodDefinition<?, ?>> methods;
    private final List<ServerServiceDefinition> services;

    @Override
    public List<ServerServiceDefinition> getServices() {
        return this.services;
    }

    private InternalHandlerRegistry(List<ServerServiceDefinition> list, Map<String, ServerMethodDefinition<?, ?>> map) {
        this.services = list;
        this.methods = map;
    }

    @Override
    @Nullable
    public ServerMethodDefinition<?, ?> lookupMethod(String str, @Nullable String str2) {
        return this.methods.get(str);
    }

    public static final class Builder {
        private final HashMap<String, ServerServiceDefinition> services = new LinkedHashMap();

        public Builder addService(ServerServiceDefinition serverServiceDefinition) {
            this.services.put(serverServiceDefinition.getServiceDescriptor().getName(), serverServiceDefinition);
            return this;
        }

        public InternalHandlerRegistry build() {
            HashMap hashMap = new HashMap();
            for (ServerServiceDefinition serverServiceDefinition : this.services.values()) {
                for (ServerMethodDefinition<?, ?> serverMethodDefinition : serverServiceDefinition.getMethods()) {
                    hashMap.put(serverMethodDefinition.getMethodDescriptor().getFullMethodName(), serverMethodDefinition);
                }
            }
            return new InternalHandlerRegistry(Collections.unmodifiableList(new ArrayList(this.services.values())), Collections.unmodifiableMap(hashMap));
        }
    }
}
