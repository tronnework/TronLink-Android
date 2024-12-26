package com.fasterxml.jackson.core.sym;

import java.util.Arrays;
public final class NameN extends Name {
    private final int[] q;
    private final int q1;
    private final int q2;
    private final int q3;
    private final int q4;
    private final int qlen;

    @Override
    public boolean equals(int i) {
        return false;
    }

    @Override
    public boolean equals(int i, int i2) {
        return false;
    }

    @Override
    public boolean equals(int i, int i2, int i3) {
        return false;
    }

    NameN(String str, int i, int i2, int i3, int i4, int i5, int[] iArr, int i6) {
        super(str, i);
        this.q1 = i2;
        this.q2 = i3;
        this.q3 = i4;
        this.q4 = i5;
        this.q = iArr;
        this.qlen = i6;
    }

    public static NameN construct(String str, int i, int[] iArr, int i2) {
        if (i2 < 4) {
            throw new IllegalArgumentException();
        }
        return new NameN(str, i, iArr[0], iArr[1], iArr[2], iArr[3], i2 + (-4) > 0 ? Arrays.copyOfRange(iArr, 4, i2) : null, i2);
    }

    @Override
    public boolean equals(int[] r7, int r8) {
        


return true;

//throw new UnsupportedOperationException(
Method not decompiled: com.fasterxml.jackson.core.sym.NameN.equals(int[], int):boolean");
    }

    private final boolean _equals2(int[] iArr) {
        int i = this.qlen - 4;
        for (int i2 = 0; i2 < i; i2++) {
            if (iArr[i2 + 4] != this.q[i2]) {
                return false;
            }
        }
        return true;
    }
}
