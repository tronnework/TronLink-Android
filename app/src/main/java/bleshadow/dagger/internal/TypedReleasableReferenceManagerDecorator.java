package bleshadow.dagger.internal;

import bleshadow.dagger.releasablereferences.ReleasableReferenceManager;
import bleshadow.dagger.releasablereferences.TypedReleasableReferenceManager;
import java.lang.annotation.Annotation;
public final class TypedReleasableReferenceManagerDecorator<M extends Annotation> implements TypedReleasableReferenceManager<M> {
    private final ReleasableReferenceManager delegate;
    private final M metadata;

    @Override
    public M metadata() {
        return this.metadata;
    }

    public TypedReleasableReferenceManagerDecorator(ReleasableReferenceManager delegate, M metadata) {
        this.delegate = (ReleasableReferenceManager) Preconditions.checkNotNull(delegate);
        this.metadata = (M) Preconditions.checkNotNull(metadata);
    }

    @Override
    public Class<? extends Annotation> scope() {
        return this.delegate.scope();
    }

    @Override
    public void releaseStrongReferences() {
        this.delegate.releaseStrongReferences();
    }

    @Override
    public void restoreStrongReferences() {
        this.delegate.restoreStrongReferences();
    }
}
