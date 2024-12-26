package bleshadow.dagger.internal;

import bleshadow.javax.inject.Provider;
import java.lang.ref.WeakReference;
public final class ReferenceReleasingProvider<T> implements Provider<T> {
    static final boolean $assertionsDisabled = false;
    private static final Object NULL = new Object();
    private final Provider<T> provider;
    private volatile Object strongReference;
    private volatile WeakReference<T> weakReference;

    private ReferenceReleasingProvider(Provider<T> provider) {
        this.provider = provider;
    }

    public void releaseStrongReference() {
        Object obj = this.strongReference;
        if (obj == null || obj == NULL) {
            return;
        }
        synchronized (this) {
            this.weakReference = new WeakReference<>(obj);
            this.strongReference = null;
        }
    }

    public void restoreStrongReference() {
        T t;
        Object obj = this.strongReference;
        if (this.weakReference == null || obj != null) {
            return;
        }
        synchronized (this) {
            Object obj2 = this.strongReference;
            if (this.weakReference != null && obj2 == null && (t = this.weakReference.get()) != null) {
                this.strongReference = t;
                this.weakReference = null;
            }
        }
    }

    @Override
    public T get() {
        T t = (T) currentValue();
        if (t == null) {
            synchronized (this) {
                t = currentValue();
                if (t == null) {
                    t = this.provider.get();
                    if (t == null) {
                        t = (T) NULL;
                    }
                    this.strongReference = t;
                }
            }
        }
        if (t == NULL) {
            return null;
        }
        return t;
    }

    private Object currentValue() {
        Object obj = this.strongReference;
        if (obj != null) {
            return obj;
        }
        if (this.weakReference != null) {
            return this.weakReference.get();
        }
        return null;
    }

    public static <T> ReferenceReleasingProvider<T> create(Provider<T> delegate, ReferenceReleasingProviderManager references) {
        ReferenceReleasingProvider<T> referenceReleasingProvider = new ReferenceReleasingProvider<>((Provider) Preconditions.checkNotNull(delegate));
        references.addProvider(referenceReleasingProvider);
        return referenceReleasingProvider;
    }
}
