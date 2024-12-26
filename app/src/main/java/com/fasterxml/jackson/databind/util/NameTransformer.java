package com.fasterxml.jackson.databind.util;

import java.io.Serializable;
public abstract class NameTransformer {
    public static final NameTransformer NOP = new NopTransformer();

    public abstract String reverse(String str);

    public abstract String transform(String str);

    protected static final class NopTransformer extends NameTransformer implements Serializable {
        private static final long serialVersionUID = 1;

        @Override
        public String reverse(String str) {
            return str;
        }

        @Override
        public String transform(String str) {
            return str;
        }

        protected NopTransformer() {
        }
    }

    protected NameTransformer() {
    }

    public static NameTransformer simpleTransformer(final String str, final String str2) {
        boolean z = true;
        boolean z2 = str != null && str.length() > 0;
        z = (str2 == null || str2.length() <= 0) ? false : false;
        if (!z2) {
            return z ? new NameTransformer() {
                @Override
                public String transform(String str3) {
                    return str3 + str2;
                }

                @Override
                public String reverse(String str3) {
                    if (str3.endsWith(str2)) {
                        return str3.substring(0, str3.length() - str2.length());
                    }
                    return null;
                }

                public String toString() {
                    return "[SuffixTransformer('" + str2 + "')]";
                }
            } : NOP;
        } else if (z) {
            return new NameTransformer() {
                @Override
                public String transform(String str3) {
                    return str + str3 + str2;
                }

                @Override
                public String reverse(String str3) {
                    if (str3.startsWith(str)) {
                        String substring = str3.substring(str.length());
                        if (substring.endsWith(str2)) {
                            return substring.substring(0, substring.length() - str2.length());
                        }
                        return null;
                    }
                    return null;
                }

                public String toString() {
                    return "[PreAndSuffixTransformer('" + str + "','" + str2 + "')]";
                }
            };
        } else {
            return new NameTransformer() {
                @Override
                public String transform(String str3) {
                    return str + str3;
                }

                @Override
                public String reverse(String str3) {
                    if (str3.startsWith(str)) {
                        return str3.substring(str.length());
                    }
                    return null;
                }

                public String toString() {
                    return "[PrefixTransformer('" + str + "')]";
                }
            };
        }
    }

    public static NameTransformer chainedTransformer(NameTransformer nameTransformer, NameTransformer nameTransformer2) {
        return new Chained(nameTransformer, nameTransformer2);
    }

    public static class Chained extends NameTransformer implements Serializable {
        private static final long serialVersionUID = 1;
        protected final NameTransformer _t1;
        protected final NameTransformer _t2;

        public Chained(NameTransformer nameTransformer, NameTransformer nameTransformer2) {
            this._t1 = nameTransformer;
            this._t2 = nameTransformer2;
        }

        @Override
        public String transform(String str) {
            return this._t1.transform(this._t2.transform(str));
        }

        @Override
        public String reverse(String str) {
            String reverse = this._t1.reverse(str);
            return reverse != null ? this._t2.reverse(reverse) : reverse;
        }

        public String toString() {
            return "[ChainedTransformer(" + this._t1 + ", " + this._t2 + ")]";
        }
    }
}
