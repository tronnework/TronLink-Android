package com.polidea.rxandroidble2.internal.connection;
class ConstantPayloadSizeLimit implements PayloadSizeLimitProvider {
    private final int limit;

    @Override
    public int getPayloadSizeLimit() {
        return this.limit;
    }

    public ConstantPayloadSizeLimit(int i) {
        this.limit = i;
    }
}
