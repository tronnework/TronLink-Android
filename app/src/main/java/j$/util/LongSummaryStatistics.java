package j$.util;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
public class LongSummaryStatistics implements LongConsumer, IntConsumer {
    private long count;
    private long sum;
    private long min = Long.MAX_VALUE;
    private long max = Long.MIN_VALUE;

    @Override
    public void accept(int i) {
        accept(i);
    }

    @Override
    public void accept(long j) {
        this.count++;
        this.sum += j;
        this.min = Math.min(this.min, j);
        this.max = Math.max(this.max, j);
    }

    public IntConsumer andThen(IntConsumer intConsumer) {
        return Objects.requireNonNull(intConsumer);
    }

    public LongConsumer andThen(LongConsumer longConsumer) {
        return Objects.requireNonNull(longConsumer);
    }

    public void combine(LongSummaryStatistics longSummaryStatistics) {
        this.count += longSummaryStatistics.count;
        this.sum += longSummaryStatistics.sum;
        this.min = Math.min(this.min, longSummaryStatistics.min);
        this.max = Math.max(this.max, longSummaryStatistics.max);
    }

    public final double getAverage() {
        return getCount() > 0 ? getSum() / getCount() : FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    public final long getCount() {
        return this.count;
    }

    public final long getMax() {
        return this.max;
    }

    public final long getMin() {
        return this.min;
    }

    public final long getSum() {
        return this.sum;
    }

    public String toString() {
        return String.format("%s{count=%d, sum=%d, min=%d, average=%f, max=%d}", getClass().getSimpleName(), Long.valueOf(getCount()), Long.valueOf(getSum()), Long.valueOf(getMin()), Double.valueOf(getAverage()), Long.valueOf(getMax()));
    }
}
