package org.tron.common.crypto;
public interface SignatureInterface {
    byte[] toByteArray();

    boolean validateComponents();
}
