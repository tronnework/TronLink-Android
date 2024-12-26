package org.tron.common.crypto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import org.tron.common.crypto.datatypes.Address;
import org.tron.common.crypto.datatypes.generated.Uint256;
public class StructuredData {

    static class Entry {
        private final String name;
        private final String type;

        public String getName() {
            return this.name;
        }

        public String getType() {
            return this.type;
        }

        @JsonCreator
        public Entry(@JsonProperty("name") String str, @JsonProperty("type") String str2) {
            this.name = str;
            this.type = str2;
        }
    }

    static class EIP712Domain {
        private final Uint256 chainId;
        private final String name;
        private final String salt;
        private final Address verifyingContract;
        private final String version;

        public Uint256 getChainId() {
            return this.chainId;
        }

        public String getName() {
            return this.name;
        }

        public String getSalt() {
            return this.salt;
        }

        public Address getVerifyingContract() {
            return this.verifyingContract;
        }

        public String getVersion() {
            return this.version;
        }

        @JsonCreator
        public EIP712Domain(@JsonProperty("name") String str, @JsonProperty("version") String str2, @JsonProperty("chainId") String str3, @JsonProperty("verifyingContract") Address address, @JsonProperty("salt") String str4) {
            this.name = str;
            this.version = str2;
            this.chainId = str3 != null ? new Uint256(new BigInteger(str3)) : null;
            this.verifyingContract = address;
            this.salt = str4;
        }
    }

    static class EIP712Message {
        private final EIP712Domain domain;
        private final Object message;
        private final String primaryType;
        private final HashMap<String, List<Entry>> types;

        public EIP712Domain getDomain() {
            return this.domain;
        }

        public Object getMessage() {
            return this.message;
        }

        public String getPrimaryType() {
            return this.primaryType;
        }

        public HashMap<String, List<Entry>> getTypes() {
            return this.types;
        }

        @JsonCreator
        public EIP712Message(@JsonProperty("types") HashMap<String, List<Entry>> hashMap, @JsonProperty("primaryType") String str, @JsonProperty("message") Object obj, @JsonProperty("domain") EIP712Domain eIP712Domain) {
            this.types = hashMap;
            this.primaryType = str;
            this.message = obj;
            this.domain = eIP712Domain;
        }

        public String toString() {
            return "EIP712Message{primaryType='" + this.primaryType + "', message='" + this.message + "'}";
        }
    }
}
