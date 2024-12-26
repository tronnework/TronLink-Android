package io.perfmark;
public final class PerfMark {
    private static final Impl impl;

    static {
        


return null;

//throw new UnsupportedOperationException(
Method not decompiled: io.perfmark.PerfMark.<clinit>():void");
    }

    public static void setEnabled(boolean z) {
        impl.setEnabled(z);
    }

    public static void startTask(String str, Tag tag) {
        impl.startTask(str, tag);
    }

    public static void startTask(String str) {
        impl.startTask(str);
    }

    public static <T> void startTask(T t, StringFunction<? super T> stringFunction) {
        impl.startTask((Impl) t, (StringFunction<? super Impl>) stringFunction);
    }

    public static void startTask(String str, String str2) {
        impl.startTask(str, str2);
    }

    public static TaskCloseable traceTask(String str) {
        impl.startTask(str);
        return TaskCloseable.INSTANCE;
    }

    public static <T> TaskCloseable traceTask(T t, StringFunction<? super T> stringFunction) {
        impl.startTask((Impl) t, (StringFunction<? super Impl>) stringFunction);
        return TaskCloseable.INSTANCE;
    }

    public static void event(String str, Tag tag) {
        impl.event(str, tag);
    }

    public static void event(String str) {
        impl.event(str);
    }

    public static void event(String str, String str2) {
        impl.event(str, str2);
    }

    public static void stopTask() {
        impl.stopTask();
    }

    public static void stopTask(String str, Tag tag) {
        impl.stopTask(str, tag);
    }

    public static void stopTask(String str) {
        impl.stopTask(str);
    }

    public static void stopTask(String str, String str2) {
        impl.stopTask(str, str2);
    }

    public static Tag createTag() {
        return Impl.NO_TAG;
    }

    public static Tag createTag(long j) {
        return impl.createTag("", j);
    }

    public static Tag createTag(String str) {
        return impl.createTag(str, Long.MIN_VALUE);
    }

    public static Tag createTag(String str, long j) {
        return impl.createTag(str, j);
    }

    @Deprecated
    public static Link link() {
        return Impl.NO_LINK;
    }

    public static Link linkOut() {
        return impl.linkOut();
    }

    public static void linkIn(Link link) {
        impl.linkIn(link);
    }

    public static void attachTag(Tag tag) {
        impl.attachTag(tag);
    }

    public static void attachTag(String str, String str2) {
        impl.attachTag(str, str2);
    }

    public static void attachTag(String str, long j) {
        impl.attachTag(str, j);
    }

    public static void attachTag(String str, long j, long j2) {
        impl.attachTag(str, j, j2);
    }

    public static <T> void attachTag(String str, T t, StringFunction<? super T> stringFunction) {
        impl.attachTag(str, (String) t, (StringFunction<? super String>) stringFunction);
    }

    private PerfMark() {
    }
}
