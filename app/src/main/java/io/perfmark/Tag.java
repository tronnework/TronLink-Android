package io.perfmark;

import javax.annotation.Nullable;
public final class Tag {
    final long tagId;
    @Nullable
    final String tagName;

    public Tag(@Nullable String str, long j) {
        this.tagName = str;
        this.tagId = j;
    }
}
