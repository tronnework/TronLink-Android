package com.polidea.rxandroidble2;

import android.content.ContentResolver;
import android.content.Context;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.ClientComponent;
public final class ClientComponent_ClientModule_ProvideContentResolverFactory implements Factory<ContentResolver> {
    private final Provider<Context> contextProvider;

    public ClientComponent_ClientModule_ProvideContentResolverFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    @Override
    public ContentResolver get() {
        return (ContentResolver) Preconditions.checkNotNull(ClientComponent.ClientModule.provideContentResolver(this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static ClientComponent_ClientModule_ProvideContentResolverFactory create(Provider<Context> provider) {
        return new ClientComponent_ClientModule_ProvideContentResolverFactory(provider);
    }

    public static ContentResolver proxyProvideContentResolver(Context context) {
        return (ContentResolver) Preconditions.checkNotNull(ClientComponent.ClientModule.provideContentResolver(context), "Cannot return null from a non-@Nullable @Provides method");
    }
}
