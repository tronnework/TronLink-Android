package j$.util.stream;
abstract class AbstractSpinedBuffer {
    protected int elementIndex;
    protected final int initialChunkPower;
    protected long[] priorElementCount;
    protected int spineIndex;

    public AbstractSpinedBuffer() {
        this.initialChunkPower = 4;
    }

    public AbstractSpinedBuffer(int i) {
        if (i >= 0) {
            this.initialChunkPower = Math.max(4, 32 - Integer.numberOfLeadingZeros(i - 1));
            return;
        }
        throw new IllegalArgumentException("Illegal Capacity: " + i);
    }

    public int chunkSize(int i) {
        return 1 << ((i == 0 || i == 1) ? this.initialChunkPower : Math.min((this.initialChunkPower + i) - 1, 30));
    }

    public abstract void clear();

    public long count() {
        int i = this.spineIndex;
        return i == 0 ? this.elementIndex : this.priorElementCount[i] + this.elementIndex;
    }
}
