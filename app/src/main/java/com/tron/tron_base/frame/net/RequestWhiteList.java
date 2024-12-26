package com.tron.tron_base.frame.net;

import com.tron.tron_base.frame.net.IRequest;
import j$.util.DesugarArrays;
import j$.util.Objects;
import j$.util.function.Predicate$-CC;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Predicate;
import okhttp3.Request;
public class RequestWhiteList {
    private static final String[] permittedApis = {"api/wallet/v2/getDelegatedResource", "api/transaction-info", "api/stake/delegate", "api/stake/total", "api/stake/from", "api/web/v1/tronweb"};

    public static boolean permit(Request request) {
        if (IRequest.ENVIRONMENT != IRequest.NET_ENVIRONMENT.SHASTA) {
            return true;
        }
        if (request == null) {
            return false;
        }
        final String httpUrl = request.url().toString();
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        DesugarArrays.stream(permittedApis).filter(new Predicate() {
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
                boolean startsWith;
                startsWith = httpUrl.startsWith(IRequestConstant.HOME_SHASTA_HOST + ((String) obj));
                return startsWith;
            }
        }).findFirst().ifPresent(new Consumer() {
            @Override
            public final void accept(Object obj) {
                String str = (String) obj;
                atomicBoolean.set(true);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        return atomicBoolean.get();
    }
}
