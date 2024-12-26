package com.polidea.rxandroidble2.internal.util;

import android.os.ParcelUuid;
import com.polidea.rxandroidble2.scan.ScanRecord;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
@Deprecated
public class UUIDUtil {
    public static final ParcelUuid BASE_UUID = new ParcelUuid(ScanRecordParser.BASE_UUID);
    public static final int UUID_BYTES_128_BIT = 16;
    public static final int UUID_BYTES_16_BIT = 2;
    public static final int UUID_BYTES_32_BIT = 4;
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

    public ScanRecord parseFromBytes(byte[] bArr) {
        return this.parser.parseFromBytes(bArr);
    }
}
