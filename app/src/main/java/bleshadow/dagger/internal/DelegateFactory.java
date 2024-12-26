package bleshadow.dagger.internal;

import bleshadow.javax.inject.Provider;
public final class DelegateFactory<T> implements Factory<T> {
    private Provider<T> delegate;

    @Override
    public T get() {
        Provider<T> provider = this.delegate;
        if (provider == null) {
            throw new IllegalStateException();
        }
        return provider.get();
    }

    public void setDelegatedProvider(Provider<T> delegate) {
        if (delegate == null) {
            throw new IllegalArgumentException();
        }
        if (this.delegate != null) {
            throw new IllegalStateException();
        }
        this.delegate = delegate;
    }
}
