package org.tron.common.crypto.cryptohash;
abstract class KeccakCore extends DigestEngine {
    private static final long[] RC = {1, 32898, -9223372036854742902L, -9223372034707259392L, 32907, 2147483649L, -9223372034707259263L, -9223372036854743031L, 138, 136, 2147516425L, 2147483658L, 2147516555L, -9223372036854775669L, -9223372036854742903L, -9223372036854743037L, -9223372036854743038L, -9223372036854775680L, 32778, -9223372034707292150L, -9223372034707259263L, -9223372036854742912L, 2147483649L, -9223372034707259384L};
    private long[] A;
    private byte[] tmpOut;

    public KeccakCore(String str) {
        super(str);
    }

    private static void encodeLELong(long j, byte[] bArr, int i) {
        bArr[i] = (byte) j;
        bArr[i + 1] = (byte) (j >>> 8);
        bArr[i + 2] = (byte) (j >>> 16);
        bArr[i + 3] = (byte) (j >>> 24);
        bArr[i + 4] = (byte) (j >>> 32);
        bArr[i + 5] = (byte) (j >>> 40);
        bArr[i + 6] = (byte) (j >>> 48);
        bArr[i + 7] = (byte) (j >>> 56);
    }

    private static long decodeLELong(byte[] bArr, int i) {
        return ((bArr[i + 7] & 255) << 56) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
    }

    @Override
    protected void engineReset() {
        doReset();
    }

    @Override
    protected void processBlock(byte[] bArr) {
        KeccakCore keccakCore = this;
        char c = 0;
        for (int i = 0; i < bArr.length; i += 8) {
            long[] jArr = keccakCore.A;
            int i2 = i >>> 3;
            jArr[i2] = jArr[i2] ^ decodeLELong(bArr, i);
        }
        int i3 = 0;
        while (i3 < 24) {
            long[] jArr2 = keccakCore.A;
            long j = jArr2[1];
            long j2 = jArr2[6];
            long j3 = jArr2[11];
            long j4 = jArr2[16];
            long j5 = jArr2[21];
            long j6 = (j ^ j2) ^ (j5 ^ (j3 ^ j4));
            long j7 = jArr2[4];
            long j8 = jArr2[9];
            long j9 = jArr2[14];
            long j10 = jArr2[19];
            long j11 = jArr2[24];
            long j12 = (((j6 << 1) | (j6 >>> 63)) ^ j11) ^ ((j7 ^ j8) ^ (j9 ^ j10));
            long j13 = jArr2[2];
            long j14 = jArr2[7];
            long j15 = jArr2[12];
            long j16 = jArr2[17];
            long j17 = jArr2[22];
            long j18 = (j13 ^ j14) ^ (j17 ^ (j15 ^ j16));
            long j19 = jArr2[c];
            long j20 = jArr2[5];
            long j21 = jArr2[10];
            long j22 = jArr2[15];
            long j23 = jArr2[20];
            long j24 = (((j18 << 1) | (j18 >>> 63)) ^ j23) ^ ((j19 ^ j20) ^ (j21 ^ j22));
            long j25 = jArr2[3];
            long j26 = jArr2[8];
            long j27 = jArr2[13];
            long j28 = jArr2[18];
            long j29 = jArr2[23];
            long j30 = (j25 ^ j26) ^ (j29 ^ (j27 ^ j28));
            long j31 = (((j30 << 1) | (j30 >>> 63)) ^ j5) ^ ((j ^ j2) ^ (j3 ^ j4));
            long j32 = (j7 ^ j8) ^ (j11 ^ (j9 ^ j10));
            long j33 = (((j32 << 1) | (j32 >>> 63)) ^ j17) ^ ((j13 ^ j14) ^ (j15 ^ j16));
            long j34 = (j19 ^ j20) ^ (j23 ^ (j21 ^ j22));
            long j35 = (((j34 << 1) | (j34 >>> 63)) ^ j29) ^ ((j25 ^ j26) ^ (j27 ^ j28));
            long j36 = j19 ^ j12;
            jArr2[c] = j36;
            long j37 = j20 ^ j12;
            jArr2[5] = j37;
            long j38 = j21 ^ j12;
            jArr2[10] = j38;
            long j39 = j22 ^ j12;
            jArr2[15] = j39;
            long j40 = j23 ^ j12;
            jArr2[20] = j40;
            long j41 = j ^ j24;
            jArr2[1] = j41;
            long j42 = j2 ^ j24;
            jArr2[6] = j42;
            long j43 = j3 ^ j24;
            jArr2[11] = j43;
            long j44 = j4 ^ j24;
            jArr2[16] = j44;
            long j45 = j5 ^ j24;
            jArr2[21] = j45;
            long j46 = j13 ^ j31;
            jArr2[2] = j46;
            long j47 = j14 ^ j31;
            jArr2[7] = j47;
            long j48 = j15 ^ j31;
            jArr2[12] = j48;
            long j49 = j16 ^ j31;
            jArr2[17] = j49;
            long j50 = j17 ^ j31;
            jArr2[22] = j50;
            long j51 = j25 ^ j33;
            jArr2[3] = j51;
            long j52 = j26 ^ j33;
            jArr2[8] = j52;
            long j53 = j27 ^ j33;
            jArr2[13] = j53;
            long j54 = j28 ^ j33;
            jArr2[18] = j54;
            long j55 = j29 ^ j33;
            jArr2[23] = j55;
            long j56 = j7 ^ j35;
            jArr2[4] = j56;
            long j57 = j8 ^ j35;
            jArr2[9] = j57;
            long j58 = j9 ^ j35;
            jArr2[14] = j58;
            long j59 = j10 ^ j35;
            jArr2[19] = j59;
            long j60 = j11 ^ j35;
            jArr2[24] = j60;
            long j61 = (j37 << 36) | (j37 >>> 28);
            jArr2[5] = j61;
            long j62 = (j38 << 3) | (j38 >>> 61);
            jArr2[10] = j62;
            long j63 = (j39 << 41) | (j39 >>> 23);
            jArr2[15] = j63;
            long j64 = (j40 << 18) | (j40 >>> 46);
            jArr2[20] = j64;
            long j65 = (j41 << 1) | (j41 >>> 63);
            jArr2[1] = j65;
            long j66 = (j42 << 44) | (j42 >>> 20);
            jArr2[6] = j66;
            long j67 = (j43 << 10) | (j43 >>> 54);
            jArr2[11] = j67;
            long j68 = (j44 << 45) | (j44 >>> 19);
            jArr2[16] = j68;
            long j69 = (j45 << 2) | (j45 >>> 62);
            jArr2[21] = j69;
            long j70 = (j46 << 62) | (j46 >>> 2);
            jArr2[2] = j70;
            long j71 = (j47 << 6) | (j47 >>> 58);
            jArr2[7] = j71;
            long j72 = (j48 << 43) | (j48 >>> 21);
            jArr2[12] = j72;
            long j73 = (j49 << 15) | (j49 >>> 49);
            jArr2[17] = j73;
            long j74 = (j50 << 61) | (j50 >>> 3);
            jArr2[22] = j74;
            long j75 = (j51 << 28) | (j51 >>> 36);
            jArr2[3] = j75;
            long j76 = (j52 << 55) | (j52 >>> 9);
            jArr2[8] = j76;
            long j77 = (j53 << 25) | (j53 >>> 39);
            jArr2[13] = j77;
            long j78 = (j54 << 21) | (j54 >>> 43);
            jArr2[18] = j78;
            long j79 = (j55 << 56) | (j55 >>> 8);
            jArr2[23] = j79;
            long j80 = (j56 << 27) | (j56 >>> 37);
            jArr2[4] = j80;
            long j81 = (j57 << 20) | (j57 >>> 44);
            jArr2[9] = j81;
            long j82 = (j58 << 39) | (j58 >>> 25);
            jArr2[14] = j82;
            long j83 = (j59 << 8) | (j59 >>> 56);
            jArr2[19] = j83;
            long j84 = (j60 << 14) | (j60 >>> 50);
            jArr2[24] = j84;
            long j85 = j36 ^ (j66 | j72);
            long j86 = j66 ^ ((~j72) | j78);
            long j87 = j72 ^ (j78 & j84);
            long j88 = j78 ^ (j84 | j36);
            long j89 = j84 ^ (j36 & j66);
            jArr2[c] = j85;
            jArr2[6] = j86;
            jArr2[12] = j87;
            jArr2[18] = j88;
            jArr2[24] = j89;
            long j90 = j75 ^ (j81 | j62);
            long j91 = j81 ^ (j62 & j68);
            long j92 = j62 ^ (j68 | (~j74));
            long j93 = j68 ^ (j74 | j75);
            long j94 = j74 ^ (j75 & j81);
            jArr2[3] = j90;
            jArr2[9] = j91;
            jArr2[10] = j92;
            jArr2[16] = j93;
            jArr2[22] = j94;
            long j95 = ~j83;
            long j96 = j65 ^ (j71 | j77);
            long j97 = j71 ^ (j77 & j83);
            long j98 = j77 ^ (j95 & j64);
            long j99 = j95 ^ (j64 | j65);
            long j100 = j64 ^ (j65 & j71);
            jArr2[1] = j96;
            jArr2[7] = j97;
            jArr2[13] = j98;
            jArr2[19] = j99;
            jArr2[20] = j100;
            long j101 = ~j73;
            long j102 = j80 ^ (j61 & j67);
            long j103 = j61 ^ (j67 | j73);
            long j104 = j67 ^ (j101 | j79);
            long j105 = j101 ^ (j79 & j80);
            long j106 = j79 ^ (j80 | j61);
            jArr2[4] = j102;
            jArr2[5] = j103;
            jArr2[11] = j104;
            jArr2[17] = j105;
            jArr2[23] = j106;
            long j107 = ~j76;
            long j108 = j70 ^ (j107 & j82);
            long j109 = j107 ^ (j82 | j63);
            long j110 = j82 ^ (j63 & j69);
            long j111 = j63 ^ (j69 | j70);
            long j112 = j69 ^ (j70 & j76);
            jArr2[2] = j108;
            jArr2[8] = j109;
            jArr2[14] = j110;
            jArr2[15] = j111;
            jArr2[21] = j112;
            long[] jArr3 = RC;
            long j113 = j85 ^ jArr3[i3];
            jArr2[0] = j113;
            long j114 = (j86 ^ j91) ^ (j109 ^ (j97 ^ j103));
            long j115 = (((j114 << 1) | (j114 >>> 63)) ^ j112) ^ ((j89 ^ j94) ^ (j100 ^ j106));
            long j116 = (j87 ^ j92) ^ (j110 ^ (j98 ^ j104));
            long j117 = (((j116 << 1) | (j116 >>> 63)) ^ j108) ^ ((j113 ^ j90) ^ (j96 ^ j102));
            long j118 = (j88 ^ j93) ^ (j111 ^ (j99 ^ j105));
            long j119 = (((j118 << 1) | (j118 >>> 63)) ^ j109) ^ ((j86 ^ j91) ^ (j97 ^ j103));
            long j120 = (j89 ^ j94) ^ (j112 ^ (j100 ^ j106));
            long j121 = (((j120 << 1) | (j120 >>> 63)) ^ j110) ^ ((j87 ^ j92) ^ (j98 ^ j104));
            long j122 = (j113 ^ j90) ^ (j108 ^ (j96 ^ j102));
            long j123 = (((j122 << 1) | (j122 >>> 63)) ^ j111) ^ ((j88 ^ j93) ^ (j99 ^ j105));
            long j124 = j113 ^ j115;
            jArr2[0] = j124;
            long j125 = j90 ^ j115;
            jArr2[3] = j125;
            long j126 = j96 ^ j115;
            jArr2[1] = j126;
            long j127 = j102 ^ j115;
            jArr2[4] = j127;
            long j128 = j108 ^ j115;
            jArr2[2] = j128;
            long j129 = j86 ^ j117;
            jArr2[6] = j129;
            long j130 = j91 ^ j117;
            jArr2[9] = j130;
            long j131 = j97 ^ j117;
            jArr2[7] = j131;
            long j132 = j103 ^ j117;
            jArr2[5] = j132;
            long j133 = j109 ^ j117;
            jArr2[8] = j133;
            long j134 = j87 ^ j119;
            jArr2[12] = j134;
            long j135 = j92 ^ j119;
            jArr2[10] = j135;
            long j136 = j98 ^ j119;
            jArr2[13] = j136;
            long j137 = j104 ^ j119;
            jArr2[11] = j137;
            long j138 = j110 ^ j119;
            jArr2[14] = j138;
            long j139 = j88 ^ j121;
            jArr2[18] = j139;
            long j140 = j93 ^ j121;
            jArr2[16] = j140;
            long j141 = j99 ^ j121;
            jArr2[19] = j141;
            long j142 = j105 ^ j121;
            jArr2[17] = j142;
            long j143 = j111 ^ j121;
            jArr2[15] = j143;
            long j144 = j89 ^ j123;
            jArr2[24] = j144;
            long j145 = j94 ^ j123;
            jArr2[22] = j145;
            long j146 = j100 ^ j123;
            jArr2[20] = j146;
            long j147 = j106 ^ j123;
            jArr2[23] = j147;
            long j148 = j112 ^ j123;
            jArr2[21] = j148;
            long j149 = (j125 << 36) | (j125 >>> 28);
            jArr2[3] = j149;
            long j150 = (j126 << 3) | (j126 >>> 61);
            jArr2[1] = j150;
            long j151 = (j127 << 41) | (j127 >>> 23);
            jArr2[4] = j151;
            long j152 = (j128 << 18) | (j128 >>> 46);
            jArr2[2] = j152;
            long j153 = (j129 << 1) | (j129 >>> 63);
            jArr2[6] = j153;
            long j154 = (j130 << 44) | (j130 >>> 20);
            jArr2[9] = j154;
            long j155 = (j131 << 10) | (j131 >>> 54);
            jArr2[7] = j155;
            long j156 = (j132 << 45) | (j132 >>> 19);
            jArr2[5] = j156;
            long j157 = (j133 << 2) | (j133 >>> 62);
            jArr2[8] = j157;
            long j158 = (j134 << 62) | (j134 >>> 2);
            jArr2[12] = j158;
            long j159 = (j135 << 6) | (j135 >>> 58);
            jArr2[10] = j159;
            long j160 = (j136 << 43) | (j136 >>> 21);
            jArr2[13] = j160;
            int i4 = i3;
            long j161 = (j137 << 15) | (j137 >>> 49);
            jArr2[11] = j161;
            long j162 = (j138 << 61) | (j138 >>> 3);
            jArr2[14] = j162;
            long j163 = (j139 << 28) | (j139 >>> 36);
            jArr2[18] = j163;
            long j164 = (j140 << 55) | (j140 >>> 9);
            jArr2[16] = j164;
            long j165 = (j141 << 25) | (j141 >>> 39);
            jArr2[19] = j165;
            long j166 = (j142 << 21) | (j142 >>> 43);
            jArr2[17] = j166;
            long j167 = (j143 << 56) | (j143 >>> 8);
            jArr2[15] = j167;
            long j168 = (j144 << 27) | (j144 >>> 37);
            jArr2[24] = j168;
            long j169 = (j145 << 20) | (j145 >>> 44);
            jArr2[22] = j169;
            long j170 = (j146 << 39) | (j146 >>> 25);
            jArr2[20] = j170;
            long j171 = (j147 << 8) | (j147 >>> 56);
            jArr2[23] = j171;
            long j172 = (j148 << 14) | (j148 >>> 50);
            jArr2[21] = j172;
            long j173 = j124 ^ (j154 | j160);
            long j174 = j154 ^ ((~j160) | j166);
            long j175 = j160 ^ (j166 & j172);
            long j176 = j166 ^ (j172 | j124);
            long j177 = j172 ^ (j124 & j154);
            jArr2[0] = j173;
            jArr2[9] = j174;
            jArr2[13] = j175;
            jArr2[17] = j176;
            jArr2[21] = j177;
            long j178 = j163 ^ (j169 | j150);
            long j179 = j169 ^ (j150 & j156);
            long j180 = j150 ^ ((~j162) | j156);
            long j181 = j156 ^ (j162 | j163);
            long j182 = j162 ^ (j163 & j169);
            jArr2[18] = j178;
            jArr2[22] = j179;
            jArr2[1] = j180;
            jArr2[5] = j181;
            jArr2[14] = j182;
            long j183 = ~j171;
            long j184 = (j159 | j165) ^ j153;
            long j185 = j159 ^ (j165 & j171);
            long j186 = j165 ^ (j183 & j152);
            long j187 = j183 ^ (j152 | j153);
            long j188 = j152 ^ (j153 & j159);
            jArr2[6] = j184;
            jArr2[10] = j185;
            jArr2[19] = j186;
            jArr2[23] = j187;
            jArr2[2] = j188;
            long j189 = ~j161;
            long j190 = j168 ^ (j149 & j155);
            long j191 = j149 ^ (j155 | j161);
            long j192 = j155 ^ (j189 | j167);
            long j193 = j189 ^ (j167 & j168);
            long j194 = j167 ^ (j168 | j149);
            jArr2[24] = j190;
            jArr2[3] = j191;
            jArr2[7] = j192;
            jArr2[11] = j193;
            jArr2[15] = j194;
            long j195 = ~j164;
            long j196 = j158 ^ (j195 & j170);
            long j197 = j195 ^ (j170 | j151);
            long j198 = j170 ^ (j151 & j157);
            long j199 = j151 ^ (j157 | j158);
            long j200 = j157 ^ (j158 & j164);
            jArr2[12] = j196;
            jArr2[16] = j197;
            jArr2[20] = j198;
            jArr2[4] = j199;
            jArr2[8] = j200;
            jArr2[0] = j173 ^ jArr3[i4 + 1];
            jArr2[5] = j178;
            jArr2[18] = j193;
            jArr2[11] = j185;
            jArr2[10] = j184;
            jArr2[6] = j179;
            jArr2[22] = j198;
            jArr2[20] = j196;
            jArr2[12] = j186;
            jArr2[19] = j194;
            jArr2[15] = j190;
            jArr2[24] = j200;
            jArr2[8] = j181;
            jArr2[1] = j174;
            jArr2[9] = j182;
            jArr2[14] = j188;
            jArr2[2] = j175;
            jArr2[13] = j187;
            jArr2[23] = j199;
            jArr2[4] = j177;
            jArr2[21] = j197;
            jArr2[16] = j191;
            jArr2[3] = j176;
            jArr2[17] = j192;
            jArr2[7] = j180;
            i3 = i4 + 2;
            keccakCore = this;
            c = 0;
        }
    }

    @Override
    protected void doPadding(byte[] bArr, int i) {
        int flush = flush();
        byte[] blockBuffer = getBlockBuffer();
        int i2 = flush + 1;
        if (i2 == blockBuffer.length) {
            blockBuffer[flush] = -127;
        } else {
            blockBuffer[flush] = 1;
            while (i2 < blockBuffer.length - 1) {
                blockBuffer[i2] = 0;
                i2++;
            }
            blockBuffer[blockBuffer.length - 1] = Byte.MIN_VALUE;
        }
        processBlock(blockBuffer);
        long[] jArr = this.A;
        jArr[1] = ~jArr[1];
        jArr[2] = ~jArr[2];
        jArr[8] = ~jArr[8];
        jArr[12] = ~jArr[12];
        jArr[17] = ~jArr[17];
        jArr[20] = ~jArr[20];
        int engineGetDigestLength = engineGetDigestLength();
        for (int i3 = 0; i3 < engineGetDigestLength; i3 += 8) {
            encodeLELong(this.A[i3 >>> 3], this.tmpOut, i3);
        }
        System.arraycopy(this.tmpOut, 0, bArr, i, engineGetDigestLength);
    }

    @Override
    protected void doInit() {
        this.A = new long[25];
        this.tmpOut = new byte[(engineGetDigestLength() + 7) & (-8)];
        doReset();
    }

    public int getBlockLength() {
        return 200 - (engineGetDigestLength() * 2);
    }

    private final void doReset() {
        for (int i = 0; i < 25; i++) {
            this.A[i] = 0;
        }
        long[] jArr = this.A;
        jArr[1] = -1;
        jArr[2] = -1;
        jArr[8] = -1;
        jArr[12] = -1;
        jArr[17] = -1;
        jArr[20] = -1;
    }

    public Digest copyState(KeccakCore keccakCore) {
        System.arraycopy(this.A, 0, keccakCore.A, 0, 25);
        return super.copyState((DigestEngine) keccakCore);
    }

    @Override
    public String toString() {
        return "Keccak-" + (engineGetDigestLength() << 3);
    }
}
