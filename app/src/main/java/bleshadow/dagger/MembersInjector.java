package bleshadow.dagger;
public interface MembersInjector<T> {
    void injectMembers(T instance);
}
