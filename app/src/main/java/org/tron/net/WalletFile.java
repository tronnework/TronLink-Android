package org.tron.net;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
public class WalletFile {
    protected static final String AES_128_CTR = "pbkdf2";
    protected static final String SCRYPT = "scrypt";
    private String address;
    private Crypto crypto;
    private String id;
    private int version;

    public interface KdfParams {
        int getDklen();

        String getSalt();
    }

    public String getAddress() {
        return this.address;
    }

    public Crypto getCrypto() {
        return this.crypto;
    }

    public String getId() {
        return this.id;
    }

    public int getVersion() {
        return this.version;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    @JsonSetter("crypto")
    public void setCrypto(Crypto crypto) {
        this.crypto = crypto;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setVersion(int i) {
        this.version = i;
    }

    @JsonSetter("Crypto")
    public void setCryptoV1(Crypto crypto) {
        setCrypto(crypto);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && obj.getClass() == getClass()) {
            WalletFile walletFile = (WalletFile) obj;
            if (getAddress() == null ? walletFile.getAddress() == null : getAddress().equals(walletFile.getAddress())) {
                if (getCrypto() == null ? walletFile.getCrypto() == null : getCrypto().equals(walletFile.getCrypto())) {
                    if (getId() == null ? walletFile.getId() == null : getId().equals(walletFile.getId())) {
                        return this.version == walletFile.version;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        return ((((((getAddress() != null ? getAddress().hashCode() : 0) * 31) + (getCrypto() != null ? getCrypto().hashCode() : 0)) * 31) + (getId() != null ? getId().hashCode() : 0)) * 31) + this.version;
    }

    public static class Crypto {
        private String cipher;
        private CipherParams cipherparams;
        private String ciphertext;
        private String kdf;
        private KdfParams kdfparams;
        private String mac;

        public String getCipher() {
            return this.cipher;
        }

        public CipherParams getCipherparams() {
            return this.cipherparams;
        }

        public String getCiphertext() {
            return this.ciphertext;
        }

        public String getKdf() {
            return this.kdf;
        }

        public KdfParams getKdfparams() {
            return this.kdfparams;
        }

        public String getMac() {
            return this.mac;
        }

        public void setCipher(String str) {
            this.cipher = str;
        }

        public void setCipherparams(CipherParams cipherParams) {
            this.cipherparams = cipherParams;
        }

        public void setCiphertext(String str) {
            this.ciphertext = str;
        }

        public void setKdf(String str) {
            this.kdf = str;
        }

        @JsonSubTypes({@JsonSubTypes.Type(name = WalletFile.AES_128_CTR, value = Aes128CtrKdfParams.class), @JsonSubTypes.Type(name = WalletFile.SCRYPT, value = ScryptKdfParams.class)})
        @JsonTypeInfo(include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "kdf", use = JsonTypeInfo.Id.NAME)
        public void setKdfparams(KdfParams kdfParams) {
            this.kdfparams = kdfParams;
        }

        public void setMac(String str) {
            this.mac = str;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && obj.getClass() == getClass()) {
                Crypto crypto = (Crypto) obj;
                if (getCipher() == null ? crypto.getCipher() == null : getCipher().equals(crypto.getCipher())) {
                    if (getCiphertext() == null ? crypto.getCiphertext() == null : getCiphertext().equals(crypto.getCiphertext())) {
                        if (getCipherparams() == null ? crypto.getCipherparams() == null : getCipherparams().equals(crypto.getCipherparams())) {
                            if (getKdf() == null ? crypto.getKdf() == null : getKdf().equals(crypto.getKdf())) {
                                if (getKdfparams() == null ? crypto.getKdfparams() == null : getKdfparams().equals(crypto.getKdfparams())) {
                                    if (getMac() != null) {
                                        return getMac().equals(crypto.getMac());
                                    }
                                    return crypto.getMac() == null;
                                }
                                return false;
                            }
                            return false;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            return ((((((((((getCipher() != null ? getCipher().hashCode() : 0) * 31) + (getCiphertext() != null ? getCiphertext().hashCode() : 0)) * 31) + (getCipherparams() != null ? getCipherparams().hashCode() : 0)) * 31) + (getKdf() != null ? getKdf().hashCode() : 0)) * 31) + (getKdfparams() != null ? getKdfparams().hashCode() : 0)) * 31) + (getMac() != null ? getMac().hashCode() : 0);
        }
    }

    public static class CipherParams {
        private String iv;

        public String getIv() {
            return this.iv;
        }

        public void setIv(String str) {
            this.iv = str;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && obj.getClass() == getClass()) {
                CipherParams cipherParams = (CipherParams) obj;
                if (getIv() != null) {
                    return getIv().equals(cipherParams.getIv());
                }
                return cipherParams.getIv() == null;
            }
            return false;
        }

        public int hashCode() {
            if (getIv() != null) {
                return getIv().hashCode();
            }
            return 0;
        }
    }

    public static class Aes128CtrKdfParams implements KdfParams {
        private int c;
        private int dklen;
        private String prf;
        private String salt;

        public int getC() {
            return this.c;
        }

        @Override
        public int getDklen() {
            return this.dklen;
        }

        public String getPrf() {
            return this.prf;
        }

        @Override
        public String getSalt() {
            return this.salt;
        }

        public void setC(int i) {
            this.c = i;
        }

        public void setDklen(int i) {
            this.dklen = i;
        }

        public void setPrf(String str) {
            this.prf = str;
        }

        public void setSalt(String str) {
            this.salt = str;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && obj.getClass() == getClass()) {
                Aes128CtrKdfParams aes128CtrKdfParams = (Aes128CtrKdfParams) obj;
                if (this.dklen == aes128CtrKdfParams.dklen && this.c == aes128CtrKdfParams.c) {
                    if (getPrf() == null ? aes128CtrKdfParams.getPrf() == null : getPrf().equals(aes128CtrKdfParams.getPrf())) {
                        if (getSalt() != null) {
                            return getSalt().equals(aes128CtrKdfParams.getSalt());
                        }
                        return aes128CtrKdfParams.getSalt() == null;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            return (((((this.dklen * 31) + this.c) * 31) + (getPrf() != null ? getPrf().hashCode() : 0)) * 31) + (getSalt() != null ? getSalt().hashCode() : 0);
        }
    }

    public static class ScryptKdfParams implements KdfParams {
        private int dklen;
        private int n;
        private int p;
        private int r;
        private String salt;

        @Override
        public int getDklen() {
            return this.dklen;
        }

        public int getN() {
            return this.n;
        }

        public int getP() {
            return this.p;
        }

        public int getR() {
            return this.r;
        }

        @Override
        public String getSalt() {
            return this.salt;
        }

        public void setDklen(int i) {
            this.dklen = i;
        }

        public void setN(int i) {
            this.n = i;
        }

        public void setP(int i) {
            this.p = i;
        }

        public void setR(int i) {
            this.r = i;
        }

        public void setSalt(String str) {
            this.salt = str;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && obj.getClass() == getClass()) {
                ScryptKdfParams scryptKdfParams = (ScryptKdfParams) obj;
                if (this.dklen == scryptKdfParams.dklen && this.n == scryptKdfParams.n && this.p == scryptKdfParams.p && this.r == scryptKdfParams.r) {
                    if (getSalt() != null) {
                        return getSalt().equals(scryptKdfParams.getSalt());
                    }
                    return scryptKdfParams.getSalt() == null;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            return (((((((this.dklen * 31) + this.n) * 31) + this.p) * 31) + this.r) * 31) + (getSalt() != null ? getSalt().hashCode() : 0);
        }
    }

    static class KdfParamsDeserialiser extends JsonDeserializer<KdfParams> {
        KdfParamsDeserialiser() {
        }

        @Override
        public KdfParams deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            ObjectMapper objectMapper = (ObjectMapper) jsonParser.getCodec();
            ObjectNode objectNode = (ObjectNode) objectMapper.readTree(jsonParser);
            if (objectNode.get("n") == null) {
                return (KdfParams) objectMapper.convertValue(objectNode, Aes128CtrKdfParams.class);
            }
            return (KdfParams) objectMapper.convertValue(objectNode, ScryptKdfParams.class);
        }
    }
}
