package org.slf4j.helpers;
public class NOPLogger extends MarkerIgnoringBase {
    public static final NOPLogger NOP_LOGGER = new NOPLogger();
    private static final long serialVersionUID = -517220405410904473L;

    @Override
    public final void debug(String str) {
    }

    @Override
    public final void debug(String str, Object obj) {
    }

    @Override
    public final void debug(String str, Object obj, Object obj2) {
    }

    @Override
    public final void debug(String str, Throwable th) {
    }

    @Override
    public final void debug(String str, Object... objArr) {
    }

    @Override
    public final void error(String str) {
    }

    @Override
    public final void error(String str, Object obj) {
    }

    @Override
    public final void error(String str, Object obj, Object obj2) {
    }

    @Override
    public final void error(String str, Throwable th) {
    }

    @Override
    public final void error(String str, Object... objArr) {
    }

    @Override
    public String getName() {
        return "NOP";
    }

    @Override
    public final void info(String str) {
    }

    @Override
    public final void info(String str, Object obj) {
    }

    @Override
    public final void info(String str, Object obj, Object obj2) {
    }

    @Override
    public final void info(String str, Throwable th) {
    }

    @Override
    public final void info(String str, Object... objArr) {
    }

    @Override
    public final boolean isDebugEnabled() {
        return false;
    }

    @Override
    public final boolean isErrorEnabled() {
        return false;
    }

    @Override
    public final boolean isInfoEnabled() {
        return false;
    }

    @Override
    public final boolean isTraceEnabled() {
        return false;
    }

    @Override
    public final boolean isWarnEnabled() {
        return false;
    }

    @Override
    public final void trace(String str) {
    }

    @Override
    public final void trace(String str, Object obj) {
    }

    @Override
    public final void trace(String str, Object obj, Object obj2) {
    }

    @Override
    public final void trace(String str, Throwable th) {
    }

    @Override
    public final void trace(String str, Object... objArr) {
    }

    @Override
    public final void warn(String str) {
    }

    @Override
    public final void warn(String str, Object obj) {
    }

    @Override
    public final void warn(String str, Object obj, Object obj2) {
    }

    @Override
    public final void warn(String str, Throwable th) {
    }

    @Override
    public final void warn(String str, Object... objArr) {
    }

    protected NOPLogger() {
    }
}
