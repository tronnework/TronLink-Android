package com.polidea.rxandroidble2.helpers;

import com.polidea.rxandroidble2.internal.util.ScanRecordParser;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
public class AdvertisedServiceUUIDExtractor {
    private final ScanRecordParser parser = new ScanRecordParser();

    public List<UUID> extractUUIDs(byte[] bArr) {
        return this.parser.extractUUIDs(bArr);
    }

    public Set<UUID> toDistinctSet(UUID[] uuidArr) {
        if (uuidArr == null) {
            uuidArr = new UUID[0];
        }
        return new HashSet(Arrays.asList(uuidArr));
    }
}
