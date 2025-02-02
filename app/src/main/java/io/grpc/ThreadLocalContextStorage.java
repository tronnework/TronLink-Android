package io.grpc;

import io.grpc.Context;
import java.util.logging.Level;
import java.util.logging.Logger;
public final class ThreadLocalContextStorage extends Context.Storage {
    private static final Logger log = Logger.getLogger(ThreadLocalContextStorage.class.getName());
    static final ThreadLocal<Context> localContext = new ThreadLocal<>();

    @Override
    public Context doAttach(Context context) {
        Context current = current();
        localContext.set(context);
        return current;
    }

    @Override
    public void detach(Context context, Context context2) {
        if (current() != context) {
            log.log(Level.SEVERE, "Context was not attached when detaching", new Throwable().fillInStackTrace());
        }
        if (context2 != Context.ROOT) {
            localContext.set(context2);
        } else {
            localContext.set(null);
        }
    }

    @Override
    public Context current() {
        Context context = localContext.get();
        return context == null ? Context.ROOT : context;
    }
}
