package io.perfmark;

import javax.annotation.Nullable;
public class Impl {
    private static final long NO_LINK_ID = Long.MIN_VALUE;
    static final long NO_TAG_ID = Long.MIN_VALUE;
    static final String NO_TAG_NAME = "";
    static final Tag NO_TAG = new Tag("", Long.MIN_VALUE);
    static final Link NO_LINK = new Link(Long.MIN_VALUE);

    public void attachTag(Tag tag) {
    }

    public void attachTag(String str, long j) {
    }

    public void attachTag(String str, long j, long j2) {
    }

    public <T> void attachTag(String str, T t, StringFunction<? super T> stringFunction) {
    }

    public void attachTag(String str, String str2) {
    }

    public Tag createTag(@Nullable String str, long j) {
        return NO_TAG;
    }

    public void event(String str) {
    }

    public void event(String str, Tag tag) {
    }

    public void event(String str, String str2) {
    }

    public void linkIn(Link link) {
    }

    public Link linkOut() {
        return NO_LINK;
    }

    public void setEnabled(boolean z) {
    }

    public <T> void startTask(T t, StringFunction<? super T> stringFunction) {
    }

    public void startTask(String str) {
    }

    public void startTask(String str, Tag tag) {
    }

    public void startTask(String str, String str2) {
    }

    public void stopTask() {
    }

    public void stopTask(String str) {
    }

    public void stopTask(String str, Tag tag) {
    }

    public void stopTask(String str, String str2) {
    }

    public Impl(Tag tag) {
        if (tag != NO_TAG) {
            throw new AssertionError("nope");
        }
    }

    @Nullable
    protected static String unpackTagName(Tag tag) {
        return tag.tagName;
    }

    protected static long unpackTagId(Tag tag) {
        return tag.tagId;
    }

    protected static long unpackLinkId(Link link) {
        return link.linkId;
    }

    protected static Tag packTag(@Nullable String str, long j) {
        return new Tag(str, j);
    }

    protected static Link packLink(long j) {
        return new Link(j);
    }
}
